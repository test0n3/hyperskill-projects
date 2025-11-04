# The Kitchen Secret

By the time you reached the kitchen, you'd already walked past numerous rooms that all looked the same, yet unique in some uncanny way. Every door creaked like it wanted to be dramatic, every hallway made you nauseous with the smell of rotting wood, paper, cloth.

The kitchen was worse: vast, cold, and lit by a single flickering bulb that buzzed like a huge bumblebee. Pots hung from the ceiling like metallic bats, marked "...en soyl...". A single chair waited in the middle of the room, facing nothing in particular. One wall looked eerily empty. Blank and white. Fresh paint? A strange keypad on the wall with letters instead of digits. It looked like this:

```
A B C D
E F G H
I J K L
M N O P
```

You took a folded paper taped to the corner of the keypad. It was a note with instructions.

Apparently, whoever put this wall here wanted you to input an extremely long sequence on the keypad. But instead of giving you the code right away, they wrote down all movements of the finger you need to make in order to get to the proper button. `UP` means you need to put your finger one key up, `LEFT` one key left, `RIGHT` one key right, `DOWN` one key down.

Imagine the following example:

```
DOWN,LEFT,RIGHT,UP,UP,RIGHT
```

You always start at top left corner (on letter `A`) for each input. First, you go `DOWN` to `E`, then instruction wants you to go `LEFT`, but you can't, so you stay on `E`. Then you go `RIGHT` to `F`, `UP` to `B`, next `UP` is ignored because you can't get much higher. And finally, the last `RIGHT` puts you to `C`. So the first letter in the sequence is `C`.

After that you return back to A and start with the next instruction. Instructions are separated by \n.

"Good luck!" were the last words written in the note.

> To get data for this task, click "Download the dataset".

> You will need to submit only the right answer as plain text. The code should be in all capital letters without spaces or commas between them. You can write and run code in IDE of your choice or use our code playground (click on the Spacebot at the bottom right).

> If you feel stuck, join our Discord community and ask for hints in a special channel #fright-night-coding.
