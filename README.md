# About Me 

My name is Ryan Evans. I am a current senior at George Mason University graduating in the fall of 2018. I am studying Information Technology and concentrating in database and programming. While I am not a CS major, im going to try my best :).

# Set Up

To run this code you need to have Java SE 7 installed. If you do not have it installed, you can download it from, https://java.com/en/download/help/download_options.xml. You do not need to any IDE installed on your machine, everthing can be run from terminal.

# Commands Needed To Run

I have attached the test text file given in the assignment instructions, but feel free to use your own after looking at the assumptions section. 

1. Download or clone the git repo to your computer.
2. Drag the folder to terminal to open the directory.
3. To compile the program run , `javac Driver_Processor.java`.
4. To run the program with your own text file, `java Driver_Processor <text_file_name>`.

# Solution Implementation

When I first read this problem, I naturally thought of using three hashmaps becuase of the possiblity of duplication. Not only that, but of the fast insertion O(n) and lookup time O(n) the hashmap provides. Next, I thought of the possiblity of using Driver objects and creating an array of Trip objects inside the Driver class. I decided against that becuase the "Driver object" would only store a name and an array of trip objects. Also, the problem statement does not discuss scalablity for the application; so creating two different objects would be unnecessary. After I decided against both of those ideas, I decided to try using one main hashmap and using the name as the key for that hashmap. This will allow fast insertion and lookup. It will also deal with the possibility of duplicates.

# Assumptions

Assumptions that I made: 

1. I assumed that there will not be any blank lines in the text file (before or after).
2. I assumed that there will be no duplicate names with the "Driver" keyword.
3. I assumed that output can be displayed in console or terminal.
4. I assumed that a driver can be added from just a trip.

# Testing

Tests that were done on the program:

1. Tests that the code will account for duplicate trips.
2. Tests that the code will account for a driver with an average MPH of less than 5 MPH or greater than 10 MPH
3. Tests that the code displays correctly with no drivers or trips entered.
4. Tests that the code will add the miles if the driver has already taken a trip.
5. Tests that the code will average the miles if a driver has taken more than one trip.
6. Tests that the code will display based on no drivers entered.

I have included `.txt` files for all tests listed above. ** Note I was unable to add the blank test file **

# Problem Statement:

Let's write some code to track driving history for people.

The code will process an input file. You can either choose to accept the input via stdin (e.g. if you're using Ruby `cat input.txt | ruby yourcode.rb)`, or as a file name given on the command line (e.g. `ruby yourcode.rb input.txt`). You can use any programming language that you want. Please choose a language that allows you to best demonstrate your programming ability.

Each line in the input file will start with a command. There are two possible commands.

The first command is Driver, which will register a new Driver in the app. Example:

`Driver Dan`

The second command is Trip, which will record a trip attributed to a driver. The line will be space delimited with the following fields: the command (Trip), driver name, start time, stop time, miles driven. Times will be given in the format of hours:minutes. We'll use a 24-hour clock and will assume that drivers never drive past midnight (the start time will always be before the end time). Example:

`Trip Dan 07:15 07:45 17.3`

Discard any trips that average a speed of less than 5 mph or greater than 100 mph.

Generate a report containing each driver with total miles driven and average speed. Sort the output by most miles driven to least. Round miles and miles per hour to the nearest integer.

Example input:
```
Driver Dan
Driver Alex
Driver Bob
Trip Dan 07:15 07:45 17.3
Trip Dan 06:12 06:32 21.8
Trip Alex 12:01 13:16 42.0
```
Expected output:
```
Alex: 42 miles @ 34 mph
Dan: 39 miles @ 47 mph
Bob: 0 miles
```
