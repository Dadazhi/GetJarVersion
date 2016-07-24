Jar file compiled java version finder

GetJarVersion is a tool that identifies the version of the JDK the jar file was built in. 

How to use the JAR?

Approach1:

Step1: Go to JRE using command prompt.                                         
Step2: Run the below command by passing command line argument.                                                                                             
       "java -jar B:\test\getJarVersion.jar B:\test\hibernate3.jar"

Approach2:

Step1: Place the getJarVersion.jar in class path.                                      
Step2: Use the below code snippet to run as a java program.                                         
       "GetJarVersion.getVersion("B:\\test\\hibernate3.jar");"
       
       
       
"B:\test\hibernate3.jar" is the jar file path which need to be tested.                                      
"B:\test\getJarVersion.jar" is the tool which identifies the version of the JDK.
