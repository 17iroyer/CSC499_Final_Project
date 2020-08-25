//Ian Royer
//Multiagent Q-learning with 2 JAL agents
//Created: 8/24/2020
//Updated: 8/24/2020

//Compile:   javac -cp MalmoJavaJar.jar myjalandjal.java
//Run:       java -cp MalmoJavaJar.jar;. myjalandjal

import com.microsoft.msr.malmo.*;
import java.io.*;
import java.nio.file.*;
import java.util.stream.Stream;
import java.util.Scanner;

public class mayialandjal {
    static {
        System.loadLibrary("MalmoJava");
    }

    public static void main(final String argv[]) throws Exception {
        
        //Sets the mission XML file
        Scanner scan = new Scanner(new File("./mymission.xml"));
        scan.useDelimeter("\\Z");
    }
}