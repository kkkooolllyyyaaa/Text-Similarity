# Lewenstein Distance Algorithm

## Task Description ##

input.txt

N - number of lines of first part

line1

line2

...

lineN

M - number of lines of second part

line1

line2

...

lineM

It is required to distribute the most similar rows into pairs

output.txt

min(N, M) rows of

linei : linej

max(N, M) - min (N, M) rows of

linei : ?

# Example #
## input.txt ##
3

distance

life is a box of chocolates

hello everybody

4

editing

hello world

chocolate gift box

never gonna give you up, never gonna break you down

## output.txt ##
distance : editing

life is a box of chocolates : chocolate gift box

hello everybody : hello world

never gonna give you up, never gonna break you down : ?
