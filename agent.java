//Ian Royer
//Basic agent class used by IAL and JAL
//Created: 8/31/2020
//Updated: 9/2/2020

import com.microsoft.msr.malmo.*;

public class Agent {

  protected String name;
  public AgentHost host;
  public WorldState myWorld;
  protected int startx, starty, startz;
  protected int buildSize, buildHeight;
  protected float[] mov0_0, mov1_0, mov2_0,
                  mov0_1, mov1_1, mov2_1,
                  mov0_2, mov1_2, mov2_2,
                  movu0_0, movu1_0, movu2_0,
                  movu0_1, movu1_1, movu2_1,
                  movu0_2, movu1_2, movu2_2;
  public float[] pla0_0, pla1_0, pla2_0,
                  pla0_1, pla1_1, pla2_1,
                  pla0_2, pla1_2, pla2_2,
                  plau0_0, plau1_0, plau2_0,
                  plau0_1, plau1_1, plau2_1,
                  plau0_2, plau1_2, plau2_2;
  protected float alpha = (float) 0.8;
  public State myState = State.PLACE;
  public float[] myTable;
  public int locx = 0;
  public int locy = 0;
  public int locz = 0;

  enum State {
    PLACE, MOVE;
  }

  //Places currently held block at location
  public void placeBlock(int x, int z){
    host.sendCommand("tp " + x + ".5 5 " + z + ".5");
    wait(500);
    host.sendCommand("jumpuse");
    wait(350);
    resetPos();
    //System.out.println("Made to placeblock");
  }

  //Looks at the block at relative location and returns name of it
  //Level 0 = first layer, 1 = second
  public String lookBlock(int x, int level, int z) {
    waitForObs();
    String observ = myWorld.getObservations().get(0).getText();
    
    String grid = observ.substring(observ.indexOf("\"build\":[") + 10);     //Find grid of blocks
    grid = grid.substring(0, grid.length() - 3);

    String[] blockArray = new String[18];                 //Split Json string into array of blocks
    blockArray = grid.split("\",\"");
    
    int arrayPos = (x + (3*z)) + (level*9);         //Calculate array position based on input

    return blockArray[arrayPos];                        //Return name of block in position
  }

  //Returns the name of the agent
  public String getName() {
    return name;
  }

  //Included for testing and future development
  public void sendCommand(String command) {
    host.sendCommand(command);
  }

  //Given an action, perform based  on current state
  public void doAction(int action) {
    if(myState == State.PLACE) {

      switch(action) {
        case 0:
          moveToHand(searchHandWool("WHITE"));
          placeBlock(locx, locz); break;
        case 1:
          moveToHand(searchHandWool("ORANGE"));
          placeBlock(locx, locz); break;
        case 2:
          moveToHand(searchHandWool("MAGENTA"));
          placeBlock(locx, locz); break;
        case 3:
          moveToHand(searchHandWool("LIGHT_BLUE"));
          placeBlock(locx, locz); break;
        case 4:
          moveToHand(searchHandWool("YELLOW"));
          placeBlock(locx, locz); break;
        case 5:
          moveToHand(searchHandWool("LIME"));
          placeBlock(locx, locz); break;
        case 6:
          moveToHand(searchHandWool("PINK"));
          placeBlock(locx, locz); break;
        case 7:
          moveToHand(searchHandWool("GRAY"));
          placeBlock(locx, locz); break;
        case 8:
          moveToHand(searchHandWool("SILVER"));
          placeBlock(locx, locz); break;
        case 9:
          moveToHand(searchHandWool("CYAN"));
          placeBlock(locx, locz); break;
        case 10:
          moveToHand(searchHandWool("PURPLE"));
          placeBlock(locx, locz); break;
        case 11:
          moveToHand(searchHandWool("BLUE"));
          placeBlock(locx, locz); break;
        case 12:
          moveToHand(searchHandWool("BROWN"));
          placeBlock(locx, locz); break;
        case 13:
          moveToHand(searchHandWool("GREEN"));
          placeBlock(locx, locz); break;
        case 14:
          moveToHand(searchHandWool("RED"));
          placeBlock(locx, locz); break;
        case 15:
          moveToHand(searchHandWool("BLACK"));
          placeBlock(locx, locz); break;
        case 16:
          moveToHand(searchHandWood("oak"));
          placeBlock(locx, locz); break;
        case 17:
          moveToHand(searchHandWood("spruce"));
          placeBlock(locx, locz); break;
        default:
          break;
      }

    } else if(myState == State.MOVE) {

      switch(action) {
        case 0:
          locx = 0; locy = 0; locz = 0; break;
        case 1:
          locx = 1; locy = 0; locz = 0; break;
        case 2:
          locx = 2; locy = 0; locz = 0; break;
        case 3:
          locx = 0; locy = 0; locz = 1; break;
        case 4:
          locx = 1; locy = 0; locz = 1; break;
        case 5:
          locx = 2; locy = 0; locz = 1; break;
        case 6:
          locx = 0; locy = 0; locz = 2; break;
        case 7:
          locx = 1; locy = 0; locz = 2; break;
        case 8:
          locx = 2; locy = 0; locz = 2; break;
        case 9:
          locx = 0; locy = 1; locz = 0; break;
        case 10:
          locx = 1; locy = 1; locz = 0; break;
        case 11:
          locx = 2; locy = 1; locz = 0; break;
        case 12:
          locx = 0; locy = 1; locz = 1; break;
        case 13:
          locx = 1; locy = 1; locz = 1; break;
        case 14:
          locx = 2; locy = 1; locz = 1; break;
        case 15:
          locx = 0; locy = 1; locz = 2; break;
        case 16:
          locx = 1; locy = 1; locz = 2; break;
        case 17:
          locx = 2; locy = 1; locz = 2; break;
        default:
          break;
      }

    }
  }

  //Moves the agent back to it's designated starting position
  public void resetPos() {
    host.sendCommand("tp " + startx + " " + starty + " " + startz);
  }

  //Moves item from pos into position 0 of inventory
  //which will be designated for the hand/items to be placed. 
  public void moveToHand(int pos) {
    host.sendCommand("swapInventoryItems " + pos + " 0");
  }

  //Search the agent's inventory for the respective block,
  // and return the inventory number it is in
  // (Should be used with movetohand)
  public int searchHandWool(String colour) {
    
    waitForObs();
    String observ = myWorld.getObservations().get(0).getText();

    String inventorystr = observ.substring(observ.indexOf(colour) + colour.length() + 24);
    inventorystr = inventorystr.substring(0, inventorystr.indexOf(","));

    return Integer.parseInt(inventorystr);
  }

  //Will search the agent's inventory space for a wood block
  //  of a specified type
  public int searchHandWood(String type) {
    waitForObs();
    String observ = myWorld.getObservations().get(0).getText();

    String inventorystr = observ.substring(observ.indexOf(type) + type.length() + 26);
    inventorystr = inventorystr.substring(0, inventorystr.indexOf(","));

    return Integer.parseInt(inventorystr);
  }

  //Waits while the agent has time to process an observation if there isn't one ready
  public void waitForObs() {
    while(host.peekWorldState().getNumberOfObservationsSinceLastState() == 0) {
      wait(50);
    }
    myWorld = host.getWorldState();
  }

  //Remove a block
  public void removeBlock(int x, int y, int z) {
    host.sendCommand("chat /setblock "+x+" "+y+" "+z+" air");
  }

  //Calls thread.sleep() for readability
  public void wait(int ms) {
    try {
      Thread.sleep(ms);
    } catch(Exception e) {
      System.out.println("Interrupted");
    }
  }

  //returns the correct placement table for the current location
  public float[] getPlaceTable(int locx, int locy, int locz) {
    switch(locx) {
      case 0:
        switch(locy) {
          case 0:
            switch(locz)
            {
              case 0: return pla0_0; 
              case 1: return pla0_1;
              case 2: return pla0_2;
            }
          break;
          case 1:
            switch(locz)
            {
              case 0: return plau0_0;
              case 1: return plau0_1;
              case 2: return plau0_2;
            }
          break;
        }
      break;
      case 1:
        switch(locy) {
          case 0:
            switch(locz)
            {
              case 0: return pla1_0;
              case 1: return pla1_1;
              case 2: return pla1_2;
            }
          break;
          case 1:
            switch(locz)
            {
              case 0: return plau1_0;
              case 1: return plau1_1;
              case 2: return plau1_2;
            }
          break;
        }
      break;
      case 2:
        switch(locy) {
          case 0:
            switch(locz)
            {
              case 0: return pla2_0;
              case 1: return pla2_1;
              case 2: return pla2_2;
            }
          break;
          case 1:
             switch(locz)
            {
              case 0: return plau2_0;
              case 1: return plau2_1;
              case 2: return plau2_2;
            }
          break;
        }
      break;
    }
    //System.out.println("Zoingo");
    return null;
  }
  
  //Returns the correct movement table for the current location 
  public float[] getMoveTable(int locx, int locy, int locz) {
    switch(locx) {
      case 0:
        switch(locy) {
          case 0:
            switch(locz)
            {
              case 0: return mov0_0;
              case 1: return mov0_1;
              case 2: return mov0_2;
            }
          break;
          case 1:
            switch(locz)
            {
              case 0: return movu0_0;
              case 1: return movu0_1;
              case 2: return movu0_2;
            }
          break;
        }
      break;
      case 1:
        switch(locy) {
          case 0:
            switch(locz)
            {
              case 0: return mov1_0;
              case 1: return mov1_1;
              case 2: return mov1_2;
            }
          break;
          case 1:
            switch(locz)
            {
              case 0: return movu1_0;
              case 1: return movu1_1;
              case 2: return movu1_2;
            }
          break;
        }
      break;
      case 2:
        switch(locy) {
          case 0:
            switch(locz)
            {
              case 0: return mov2_0;
              case 1: return mov2_1;
              case 2: return mov2_2;
            }
          break;
          case 1:
            switch(locz)
            {
              case 0: return movu2_0;
              case 1: return movu2_1;
              case 2: return movu2_2;
            }
          break;
        }
      break;
    }
    //System.out.println("Zoingo");
    return null;
  }
}