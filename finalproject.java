//Ian Royer
//Runner class for Multiagent Q-learning 
//Created: 8/24/2020
//Updated: 8/26/2020

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

    //Set up two agents to use
    //World state should be the same for each, so update world state
    //    with agent 1
    AgentHost agenthost1 = new AgentHost();
    AgentHost agenthost2 = new AgentHost();
    MissionSpec mymissionspec = new MissionSpec(scan.next(), true);
    MissionRecordSpec mymissionrecord = new MissionRecordSpec("./missiondata.tgz");
    mymissionrecord.recordCommands();
    mymissionrecord.recordObservations();
    WorldState curworldstate;

    //Try to start the mission
    try{
      agenthost1.startMission(mymissionspec, mymissionrecord);
      agenthost2.startMission(mymissionspec, mymissionrecord);
    } catch(MissionException e) {
      System.err.println("Error in starting: " + e.getMessage());
      System.err.println("Error code: " + e.getMissionErrorCode());

      if (e.getMissionErrorCode() == MissionException.MissionErrorCode.MISSION_INSUFFICIENT_CLIENTS_AVAILABLE) {
        System.err.println("No client running");
      }
      System.exit(0);
    }

    //Wait for the mission to start
    System.out.print("Mission is starting");
    do{
      System.out.print(".");
      try{
        thread.sleep(1000);
      } catch(InterruptedException ex) {
        System.err.println("User stopped the mission from starting");
        return;
      }

      curworldstate = agenthost1.getWorldState();
      for(int i=0, i<curworldstate.getErrors().size(); i++) {
        System.err.println("Error: " + curworldstate.getErrors().get(i).getText());
      }
    } while(!curworldstate.getIsMissionRunning());
    System.out.println("");

    System.out.println("Mission has started");

    //Rest of the actions go below here
    //Or take out the loop
    do{

    }while(curworldstate.getIsMissionRunning());

    System.our.println("The mission is done");
  }


}