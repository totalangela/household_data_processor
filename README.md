# household_data_processor
Process household data from console or file system
Household Data Processor Design and Implementation

Angela Li (UC Berkeley ‘24)


**Synopsis**

My household data processing project takes household person data either from input strings in CSV (comma separated values) format or from a file, deserializes the strings to a list of Person object and processes the data based on the business logic requirements (grouping, sorting and filtering), and either prints the result to console or outputs the result to a file.


**How to compile the source code from a Windows machine**

Unzip the source code from the zip file, and then open a command prompt (Windows + R => cmd) and cd to the parent of the src directory and run the following command:


for /r src %i in (*.java) do javac -classpath src -d <folder to store the compiled result> %i

mvn exec:java or is it mvn clean package?


**How to run the program**

cd to the <folder which stores the compiled result> and run the following command:


java com.expeditors.codingexercises.HouseholdProcessorMain -inputFile <input file name and path> -outputConsole

mvn jetty:run


 or


java com.expeditors.codingexercises.HouseholdProcessorMain -inputFile <input file name and path> -outputFile <output file name and path>


**Architecture**

The design consists of data models, business logic processors, common utilities and driver class.
 
 
**Design pattern**

I used dependency injection to inject the HouseholdProcessor in both ConsoleBasedProcessor and FileBasedProcessor to separate the concerns of constructing objects and facilitate code reuse.


**Time complexity**

The overall time complexity will be O(n * log n) where n is the number of data entries about household persons. The reason is my algorithm is using Java Stream API to do grouping by household address and sorting by person's last name and first name.

**Space complexity**

The overall space complexity is O(n) where n is the number of data entries in the input because of the grouping and sorting of the household data. The space complexity can be improved by using MapReduce paradigm and/or distributed system as future work.

**Error handling**

For simplicity, all errors such as IO exceptions or issues with the input data will cause the program to throw RuntimeException and the program will print the error message to the console and exit.

**Unit test**

I created a simple junit test class which tests the ConsoleBasedProcessor with no error case and with error handling case.

**Platform tested**

Windows 11 x64

