# CS542: Assignment 2
**Name: Harshit Vadodaria**

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
Highly Popular or Ultra Popular (refer to the state diagram image in the repo).
This popularity adjective of a Channel can be represented as States, and the Channel can be considered
as Context, and thus the mapping between this problem and the state pattern.

Just like real life Youtube channels, the ChannelContext too has videos, and each video has Views, Likes and Dislikes.
Thus to represent this data, the following classes have been used:
- VideoMetrics: A POJO to hold Views, Likes and Dislikes of a video, and also calculates the video's Popularity Score
- VideoStore: A class used to store, retrieve, remove the videos, and update metrics. It implements the `VideoStoreI` interface,
				which is a very generic Video Store API, thus any implementation would work with this class. We can implement a
				store that internally uses MongoDB or SQL Server to store videos, or any other store. For simplicity, this class 
				currently stores videos in a HashMap, wherein video names are keys, mapped to VideoMetrics instances, as values
- SimpleStateFactory: A simple Factory-pattern compliant class that uses Java's reflection API to instantiate a StateI based on
				the provided StateName parameter
- AbstractState: A class containing various methods to perform the channel's actions, like addVideo, removeVideo etc.
				Various State classes like `Unpopular`, `MildlyPopular`, `HighlyPopular` and `UltraPopular`, inherit from this class
				
This project, being very generic and modular, can be used at a server, or with a TCP socket, or any other way of performing I/O.
For now, this project reads inputs from a file, wherein each line is a command to the Context, and uses an instance of the `Results`
class to write the output of each command to `stdout` and the output file.

## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [ADD_DATE_HERE]


