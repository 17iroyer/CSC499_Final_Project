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
  protected float[] pla0_0, pla1_0, pla2_0,
                  pla0_1, pla1_1, pla2_1,
                  pla0_2, pla1_2, pla2_2,
                  plau0_0, plau1_0, plau2_0,
                  plau0_1, plau1_1, plau2_1,
                  plau0_2, plau1_2, plau2_2;
  protected float alpha = (float) 0.8;

  enum BlockPlace {
    WHITE, ORANGE, MAGENTA, LIGHTB, YELLOW, LIME,
    PINK, GRAY, LIGHTG, CYAN, PURPLE, BLUE,
    BROWN, GREEN, RED, BLACK, OAK, SPRUCE;
  }

  //Places currently held block at location
  public void placeBlock(int x, int z){
    host.sendCommand("tp " + x + " 7 " + z);
    wait(500);
    host.sendCommand("jumpuse");
    resetPos();
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
    
    int arrayPos = ((x+1) * (z+1)) + (level*9);         //Calculate array position based on input

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
  private void wait(int ms) {
    try {
      Thread.sleep(ms);
    } catch(Exception e) {
      System.out.println("Interrupted");
    }
  }
}