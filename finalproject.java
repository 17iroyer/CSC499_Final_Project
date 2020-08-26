//Ian Royer
//Runner class for Multiagent Q-learning
//Created: 8/24/2020
//Updated: 8/25/2020

//Compile:   javac -cp MalmoJavaJar.jar finalproject.java
//Run:       java -cp MalmoJavaJar.jar;. finalproject

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

    //Copy rest from researchbasicmission
  }
}