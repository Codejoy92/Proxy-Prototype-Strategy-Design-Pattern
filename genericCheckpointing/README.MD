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

For simplicity of grading, generated random value only once per object and assigned it to all data types so that mismatch count can be traced easily.