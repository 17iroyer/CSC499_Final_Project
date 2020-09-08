//Ian Royer
//JAL Agent class
//Created: 8/24/2020
//Updated: 9/2/2020

import com.microsoft.msr.malmo.*;

public class Jal extends Agent {
  
  //Constructor for a basic agent
  public Jal(String name, int x, int y, int z, int buildSize, int buildHeight, AgentHost host) {
    this.name = name;
    this.startx = x;
    this.starty = y;
    this.startz = z;
    this.buildSize = buildSize;
    this.buildHeight = buildHeight;
    this.host = host;

    //Initialize the smallest possible size of structure possible
    mov0_0 = new float[18]; mov1_0 = new float[18]; mov0_1 = new float[18]; mov1_1 = new float[18];
    pla0_0 = new float[18]; pla1_0 = new float[18]; pla0_1 = new float[18]; pla1_1 = new float[18];
    
    //Initialize more if needed
    if(buildSize == 3) {
      mov2_0 = new float[18]; mov2_1 = new float[18]; mov0_2 = new float[18];
      mov1_2 = new float[18]; mov2_2 = new float[18];
      pla2_0 = new float[18]; pla2_1 = new float[18]; pla0_2 = new float[18];
      pla1_2 = new float[18]; pla2_2 = new float[18];
    }
    if(buildSize == 2 && buildHeight == 2) {
      movu0_0 = new float[18]; movu1_0 = new float[18]; movu0_1 = new float[18]; movu1_1 = new float[18];
      plau0_0 = new float[18]; plau1_0 = new float[18]; plau0_1 = new float[18]; plau1_1 = new float[18];
    }
    if(buildSize == 3 && buildHeight == 2) {
      movu2_0 = new float[18]; movu2_1 = new float[18]; movu0_2 = new float[18];
      movu1_2 = new float[18]; movu2_2 = new float[18];
      plau2_0 = new float[18]; plau2_1 = new float[18]; plau0_2 = new float[18];
      plau1_2 = new float[18]; plau2_2 = new float[18];
    }
  }

  //Will update the table according to the appropriate action taken and reward
  public void updateQtable(float[] table, int action, float reward) {

  }
}