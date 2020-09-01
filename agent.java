//Ian Royer
//Basic agent class used by IAL and JAL
//Created: 8-31-2020
//Updated: 8-31-2020

public class Agent {

  private String name;
  public AgentHost host;
  private int startx, starty, startz;

  //Constructor for a basic agent
  public jal(String name, int x, int y, int z, AgentHost host) {
      this.name = name;
      this.startx = x;
      this.starty = y;
      this.startz = z;
      this.host = host;
  }

  //Places currently held block at location
  public Bool placeBlock(int x, int z){
    host.sendCommand("tp " + x + " 8 " + z);
    host.sendCommand("hotbar.0 1");     //Make sure the hotbar is in position 0
    host.sendCommand("hotbar.0 0");
    host.sendCommand("use");
  }

  //Looks at the block at location and returns name of it
  public String lookBlock(int x, int z) {

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
}