//Ian Royer
//Runner class for Multiagent Q-learning 
//Created: 8/24/2020
//Updated: 9/2/2020

//Compile:   javac -cp malmojavajar.jar finalproject.java
//Run:       java -cp malmojavajar.jar;. finalproject

import com.microsoft.msr.malmo.*;
import java.io.*;
import java.nio.file.*;
import java.util.stream.Stream;
import java.util.Scanner;

public class finalproject {
  static {
    System.loadLibrary("MalmoJava");
  }
  
  public static void safeMissionStart(AgentHost theagent, MissionSpec themission, ClientPool thepool, MissionRecordSpec therecord, int therole, String expid) {
    int starttries = 0;
    int maxtries = 5;

    while(starttries < maxtries) {
      try {
        theagent.startMission(themission, thepool, therecord, therole, expid);
        break;
      } catch(MissionException e) {
        if(e.getMissionErrorCode() == MissionException.MissionErrorCode.MISSION_SERVER_WARMING_UP) {
          System.out.println("Server is warming up. We'll wait...");
          try {
            Thread.sleep(1000);
          } catch(InterruptedException ex) {
            System.out.println("Interrupted");
          }
        }
        else if(e.getMissionErrorCode() == MissionException.MissionErrorCode.MISSION_INSUFFICIENT_CLIENTS_AVAILABLE) {
          System.out.println("Not enough Minecraft instances running");
          starttries++;
          if(starttries < maxtries) {
            System.out.println("Waiting to see if they're warming up...");
            try{
              Thread.sleep(1000);
            } catch(InterruptedException ex) {
              System.out.println("Interrupted");
            }
          }
        }
        else if(e.getMissionErrorCode() == MissionException.MissionErrorCode.MISSION_SERVER_NOT_FOUND) {
          System.out.println("Server not found. Mission w/ role 0 hasn't been started?");
          starttries++;
          if(starttries < maxtries) {
            System.out.println("Waiting and will try again");
            try {
              Thread.sleep(1000);
            } catch(InterruptedException ex) {
              System.out.println("Interrupted");
            }
          }
        }
        else {
          System.out.println("other error: " + e.getMessage());
          System.exit(0);
        }
      }

      if(starttries > maxtries) {
        System.out.println("All tries used, exiting");
        System.exit(0);
      }
    }

    System.out.println("StartMission Called...");
  }

  public static void main(final String argv[]) throws Exception {
      
    //Sets the mission XML file
    Scanner scan = new Scanner(new File("./mymission.xml"));
    scan.useDelimiter("\\Z");

    //Set up two agents to use
    //World state should be the same for each, so update world state
    //    with agent 1
    AgentHost agenthost1 = new AgentHost();
    AgentHost agenthost2 = new AgentHost();
    MissionSpec mymissionspec = new MissionSpec(scan.next(), true);
    scan.close();

    ClientPool mypool = new ClientPool();
    ClientInfo info1 = new ClientInfo("127.0.0.1", 10000);
    ClientInfo info2 = new ClientInfo("127.0.0.1", 10001);
    mypool.add(info1);
    mypool.add(info2);

    MissionRecordSpec mymissionrecord1 = new MissionRecordSpec("./missiondata1.tgz");
    mymissionrecord1.recordCommands();
    mymissionrecord1.recordObservations();

    MissionRecordSpec mymissionrecord2 = new MissionRecordSpec("./missiondata2.tgz");
    mymissionrecord2.recordCommands();
    mymissionrecord2.recordObservations();

    WorldState curworldstate1;
    WorldState curworldstate2;

    //Try to start the mission
    safeMissionStart(agenthost1, mymissionspec, mypool, mymissionrecord1, 0, "");
    safeMissionStart(agenthost2, mymissionspec, mypool, mymissionrecord2, 1, "");

    curworldstate1 = agenthost1.getWorldState();
    curworldstate2 = agenthost2.getWorldState();
    for(int i=0; i<curworldstate1.getErrors().size(); i++) {
      System.err.println("Error: " + curworldstate1.getErrors().get(i).getText());
    }
    for(int i=0; i<curworldstate2.getErrors().size(); i++) {
      System.err.println("Error: " + curworldstate2.getErrors().get(i).getText());
    }

    //Wait for the mission to start
    System.out.print("Mission is starting");
    do{
      System.out.print(".");
      try{
        Thread.sleep(1000);
      } catch(InterruptedException ex) {
        System.err.println("User stopped the mission from starting");
        return;
      }
      curworldstate1 = agenthost1.getWorldState();
      curworldstate2 = agenthost2.getWorldState();
    } while(!curworldstate1.getIsMissionRunning() && !curworldstate2.getIsMissionRunning());
    System.out.println("");      

    System.out.println("Mission has started");

    //Rest of the actions go below here
    Ial myIal = new Ial("The IAL", -1, 8, -1, 2, 1, agenthost1);
    Jal myJal = new Jal("The JAL", -2, 8, -1, 2, 1, agenthost2);
    Ialjalbuilding buildmission = new Ialjalbuilding(myIal, myJal, 2, 1); 

    myIal.resetPos();
    myJal.resetPos();

    myIal.sendCommand("setPitch 90");
    myJal.sendCommand("setPitch 90");

    long start = System.nanoTime();

    //Put things here

    long end = System.nanoTime();
    System.out.println("Elapsed time (ns): " + (end-start));

    agenthost1.sendCommand("setPitch 0");
    agenthost2.sendCommand("setPitch 0");

    System.out.println("The mission is done");
  }

}