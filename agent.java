//Ian Royer
//Basic agent class used by IAL and JAL
//Created: 8/31/2020
//Updated: 9/2/2020

import com.microsoft.msr.malmo.*;

public class Agent {

  protected String name;
  public AgentHost host;
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

  //Places currently held block at location
  public void placeBlock(int x, int z){
    host.sendCommand("tp " + x + " 8 " + z);
    host.sendCommand("hotbar.0 1");     //Make sure the hotbar is in position 0
    host.sendCommand("hotbar.0 0");
    host.sendCommand("use");
  }

  //Looks at the block at location and returns name of it
  public String lookBlock(int x, int z) {
    return "this is a block";
  }

  //Returns the name of the agent
  public String getName() {
    return name;
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
  public int searchHand(String block) {
    return 0;
  }
}