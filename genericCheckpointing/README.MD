This assignment's submission is my own work and I did not discuss with any other past or current student, nor did I have access to a previous submission of this assignment by another student.
Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
ant -buildfile src/build.xml run -Darg0=deser -Darg1=10 -Darg2=input.txt

-----------------------------------------------------------------------
## To create javadocs
1. make sure you are inside src folder
2. run following command "ant javadocs"
3. Destination: inside genericCheckpointing

-----------------------------------------------------------------------
## To create tarball for submission
tar -cvf sujoy_das_assign_5.tar sujoy_das_assign_5/
gzip sujoy_das_assign_5.tar

-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense."

[Date: 05/05/2018]

-----------------------------------------------------------------------
Note:
github id : Codejoy92

As per class discussion and email clarification following are the points to be considered.

1.input file should be provided by command line argument
2.sample input file is not provided in the submission

3.For simplicity of grading, generated random value only once per object and assigned it to all data types so that mismatch count can be traced easily.
Although I have also tested the code with different values for different data types.
for example
if one of the value is less than 10 with respect to long, int, double
output gives mismatch for that respective object.

3.if defined data type is not found then program prints that respective setter method is not available but it deserialize other data types and provides with default values for data types which are not present in the checkpoint file.

4.in deser mode argument 2 which is no of objects shouldn't be considered and whole file should be parsed.
