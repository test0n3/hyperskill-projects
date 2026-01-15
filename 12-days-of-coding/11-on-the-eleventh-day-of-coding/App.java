import java.util.*;
import java.util.stream.Collectors;

class App {
    static class Task {
        int id;
        int duration;
        List<Integer> dependencies = new ArrayList<>();
        List<Integer> dependents = new ArrayList<>(); // Reverse dependencies
        int criticalPathLength = -1;
        int remainingDuration;
        
        // For scheduling state
        int unsatisfiedDependencies;

        Task(int id, int duration) {
            this.id = id;
            this.duration = duration;
            this.remainingDuration = duration;
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java App <input_file>");
            return;
        }

        String filePath = args[0];
        String content = ReadFile.readContent(filePath);
        if (content == null) return;

        Map<Integer, Task> tasks = parseTasks(content);
        calculateCriticalPaths(tasks);
        int result = scheduleTasks(tasks, 11);
        
        System.out.println(result);
    }

    private static Map<Integer, Task> parseTasks(String content) {
        Map<Integer, Task> tasks = new HashMap<>();
        String[] lines = content.split("\\R");

        // First pass: create tasks
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            // Parse CSV respecting quotes if present, but the format is simple enough:
            // id,duration,deps
            // deps can be "none" or "1" or "1,2", sometimes quoted "1,2"
            
            // Because dependencies might contain commas, we should be careful using split(",")
            // Let's use a regex or manual parsing.
            // Format: ID,Duration,Dependencies
            // Dependencies part is everything after the second comma
            
            int firstComma = line.indexOf(',');
            int secondComma = line.indexOf(',', firstComma + 1);
            
            int id = Integer.parseInt(line.substring(0, firstComma).trim());
            int duration = Integer.parseInt(line.substring(firstComma + 1, secondComma).trim());
            String depsStr = line.substring(secondComma + 1).trim();

            Task task = new Task(id, duration);
            tasks.put(id, task);
            
            if (!depsStr.equals("none")) {
                // Remove quotes if present
                depsStr = depsStr.replace("\"", "");
                if (!depsStr.isEmpty()) {
                    String[] depIds = depsStr.split("[:,]"); // The description says ':' but example input uses ',' inside quotes sometimes?
                    // MD file says: "Colon-separated list... Example: 5,20,2:3"
                    // But test.csv shows: 4,9,"2,0,1" or 3,12,"0,2" AND ALSO 2,22,1
                    // Wait, let's look at the actual file provided: hyperskill-dataset-119214796.txt
                    // Example lines from dataset: "3,12,0:2", "4,9,2:0:1"
                    // So in the provided dataset it IS colon separated.
                    // BUT test.csv had commas in quotes?
                    // 4,9,"2,0,1"
                    // Let's support both just in case, or stick to the dataset format which is ':'
                    
                    // Actually, looking at `hyperskill-dataset-119214796.txt` via view_file output:
                    // 4,9,2:0:1
                    // So it uses colons. 
                    // Let's strictly follow the description which says colon-separated.
                    // But I will split by [:,] to be safe if I encounter the test.csv format variation.
                    
                    for (String depIdStr : depIds) {
                         if (!depIdStr.trim().isEmpty()) {
                            task.dependencies.add(Integer.parseInt(depIdStr.trim()));
                         }
                    }
                }
            }
        }
        
        // Second pass: link dependents and count unsatisfied deps
        for (Task task : tasks.values()) {
            task.unsatisfiedDependencies = task.dependencies.size();
            for (int depId : task.dependencies) {
                if (tasks.containsKey(depId)) {
                    tasks.get(depId).dependents.add(task.id);
                }
            }
        }
        
        return tasks;
    }

    // Identify CP: Longest path from this node to the end of the graph + this node's duration
    private static void calculateCriticalPaths(Map<Integer, Task> tasks) {
        for (Task task : tasks.values()) {
            getCriticalPathLength(task, tasks);
        }
    }

    private static int getCriticalPathLength(Task task, Map<Integer, Task> tasks) {
        if (task.criticalPathLength != -1) {
            return task.criticalPathLength;
        }

        int maxPath = 0;
        for (int dependentId : task.dependents) {
            maxPath = Math.max(maxPath, getCriticalPathLength(tasks.get(dependentId), tasks));
        }

        task.criticalPathLength = task.duration + maxPath;
        return task.criticalPathLength;
    }

    private static int scheduleTasks(Map<Integer, Task> tasks, int numWorkers) {
        PriorityQueue<Task> readyQueue = new PriorityQueue<>((a, b) -> {
            // Prioritize tasks with LONGER critical path (CP Scheduling)
            return Integer.compare(b.criticalPathLength, a.criticalPathLength);
        });

        // Add initial tasks
        for (Task task : tasks.values()) {
            if (task.unsatisfiedDependencies == 0) {
                readyQueue.add(task);
            }
        }

        // Active workers processing tasks: valid_until -> task
        // We use a PriorityQueue for finish times to jump time
        PriorityQueue<Event> eventQueue = new PriorityQueue<>(Comparator.comparingInt(e -> e.time));
        
        int currentTime = 0;
        int activeWorkers = 0;

        // While there is work to do
        while (!readyQueue.isEmpty() || activeWorkers > 0) {
            
            // Assign workers if available
            while (activeWorkers < numWorkers && !readyQueue.isEmpty()) {
                Task task = readyQueue.poll();
                activeWorkers++;
                eventQueue.add(new Event(currentTime + task.duration, task.id));
            }

            // If no active workers (and queue empty - wait, loop condition handles that), break
            if (activeWorkers == 0 && readyQueue.isEmpty()) {
                break;
            }

            // Fast forward to next event
            Event nextEvent = eventQueue.poll();
            if (nextEvent == null) break; // Should not happen given logic

            currentTime = nextEvent.time;

            // Process finished task
            activeWorkers--;
            Task finishedTask = tasks.get(nextEvent.taskId);
            
            // Release dependents
            for (int dependentId : finishedTask.dependents) {
                Task dependent = tasks.get(dependentId);
                dependent.unsatisfiedDependencies--;
                if (dependent.unsatisfiedDependencies == 0) {
                    readyQueue.add(dependent);
                }
            }
            
            // Process simultaneous events (tasks finishing at the exact same time)
            while (!eventQueue.isEmpty() && eventQueue.peek().time == currentTime) {
                Event e = eventQueue.poll();
                activeWorkers--;
                Task t = tasks.get(e.taskId);
                for (int depId : t.dependents) {
                    Task d = tasks.get(depId);
                    d.unsatisfiedDependencies--;
                    if (d.unsatisfiedDependencies == 0) {
                        readyQueue.add(d);
                    }
                }
            }
        }

        return currentTime;
    }

    static class Event {
        int time;
        int taskId;

        Event(int time, int taskId) {
            this.time = time;
            this.taskId = taskId;
        }
    }
}
