# TookitakiDevChallenge
Please run the following command to get the output.
java -jar exec.jar(abosulte path of file)  input.txt(absolute path of file)
Example: java -jar /home/dir/exec.jar /home/input/test.txt



Optional: To build from source

In Terminal, execute following commands:

unzip source_code.zip
cd source_code
javac *.java
jar -cvfm exec.jar manifest.txt *.class
java -jar exec.jar $absolute_path_to_input_file
