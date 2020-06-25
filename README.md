# CSX42: Assignment 2
**Name:**

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [channelpopularity/src](./channelpopularity/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile channelpopularity/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile channelpopularity/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile channelpopularity/src/build.xml run -Dinput="input.txt" -Doutput="output.txt" -Dmetrics="metrics.txt"
```
Note: Arguments accept the absolute path of the files.


## Description:
A program implementing State Pattern and Factory Pattern, to help understand how these pattern
can be mapped to real world problem statements.

A Youtube Channel exists, which lets the user upload videos, and some stats like Views, Likes and Dislikes
are updated to the channel at random intervals. These stats on a video help determine the popularity score
of that video, and the average popularity score of all videos is the popularity score of the channel.
The average popularity score of the channel determines whether the channel is Unpopular, Mildly Popular,
Highly Popular or Ultra Popular.

## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [ADD_DATE_HERE]


