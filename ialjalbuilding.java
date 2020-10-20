//Ian Royer
//class for an IAL/JAL mission
//Created: 9/2/20
//Updated: 9/29/20

public class Ialjalbuilding extends Buildmission {

  private Ial myIal;
  private Jal myJal;

  //Constructor assigning agents to the mission
  public Ialjalbuilding(Ial myIal, Jal myJal, int buildSize, int buildHeight) {
    this.myIal = myIal;
    this.myJal = myJal;
    this.buildSize = buildSize;
    this.buildHeight = buildHeight;

    if(buildSize == 2) {
      rmov0_0 = new float[]{-10, 10, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      rmov1_0 = new float[]{1, -10, 0, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      rmov0_1 = new float[]{1, 1, 0, -10, 10, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0};
      rmov1_1 = new float[]{1, 1, 0, 1, -10, 0, 0, 0, 0, 10, 5, 0, 0, 0, 0, 0, 0, 0};
    }
    if(buildSize == 3) {
      rmov0_0 =  new float[]{-10, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      rmov1_0 =  new float[]{1, -10, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      rmov2_0 =  new float[]{1, 1, -10, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      rmov0_1 =  new float[]{1, 1, 1, -10, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      rmov1_1 =  new float[]{1, 1, 1, 1, -10, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      rmov2_1 =  new float[]{1, 1, 1, 1, 1, -10, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      rmov0_2 =  new float[]{1, 1, 1, 1, 1, 1, -10, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      rmov1_2 =  new float[]{1, 1, 1, 1, 1, 1, 1, -10, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0};
      rmov2_2 =  new float[]{1, 1, 1, 1, 1, 1, 1, 1, -10, 10, 5, 0, 0, 0, 0, 0, 0, 0};
    }
    if(buildHeight == 1 && buildSize == 2) {
      rmovu0_0 = new float[]{1, 1, 0, 1, 1, 0, 0, 0, 0, -10, 10, 0, 5, 0, 0, 0, 0, 0};
      rmovu1_0 = new float[]{1, 1, 0, 1, 1, 0, 0, 0, 0, 1, -10, 0, 10, 5, 0, 0, 0, 0};
      rmovu0_1 = new float[]{5, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, -10, 10, 0, 0, 0, 0};
      rmovu1_1 = new float[]{10, 5, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, -10, 0, 0, 0, 0};
    } 
    if(buildHeight == 1 && buildSize == 3) {
      rmovu0_0 = new float[]{1, 1, 1, 1, 1, 1, 1, 1, 1, -10, 10, 5, 0, 0, 0, 0, 0, 0};
      rmovu1_0 = new float[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -10, 10, 5, 0, 0, 0, 0, 0};
      rmovu2_0 = new float[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -10, 10, 5, 0, 0, 0, 0};
      rmovu0_1 = new float[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -10, 10, 5, 0, 0, 0};
      rmovu1_1 = new float[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -10, 10, 5, 0, 0};
      rmovu2_1 = new float[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -10, 10, 5, 0};
      rmovu0_2 = new float[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -10, 10, 5};
      rmovu1_2 = new float[]{5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -10, 10};
      rmovu2_2 = new float[]{10, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -10};
    }
  }


  //Performs the learning trials to populate
  // each agent's tables
  public void doLearnTrials() {
    clearAreaReset();

    int action = 0;
    float[] checkTable = getRplaceTable(myIal);

    for(int trial = 0; trial < learnTrials; trial++) {      //For each of the learning trials.....

      myIal.wait(500);
      //Do an IAL action ***************************************************************************************************************************************
      if(myIal.lookBlock(myIal.locx, myIal.locy, myIal.locz).equals("air")) {      //Assess current location and change state accordingly
        myIal.myState = Agent.State.PLACE;
      } else {
        myIal.myState = Agent.State.MOVE;
      }

      System.out.println("Checked Spot IAL");

      //Given the current state... do a random action
      action = getRandAction();
      System.out.println("IAL action: " + action);
      System.out.println("IAL at:" + myIal.locx + " " + myIal.locy + " " + myIal.locz);
      if(myIal.myState == Agent.State.PLACE) {
        myIal.updateQtable(myIal.getPlaceTable(myIal.locx, myIal.locy, myIal.locz), 
                           action, getRplaceTable(myIal));                  //Ial updates table
        checkTable = getRplaceTable(myIal);
        myJal.updateQtable(myJal.getPlaceTable(myIal.locx, myIal.locy, myIal.locz),
                           action, getRplaceTable(myIal));                  //Update JAL table too

        System.out.println("Tried to update place table");        
      }
      else if(myIal.myState == Agent.State.MOVE) {
        myIal.updateQtable(myIal.getMoveTable(myIal.locx, myIal.locy, myIal.locz), 
                          action, getRmoveTable(myIal));                    //Ial updates table
        myJal.updateQtable(myJal.getMoveTable(myIal.locx, myIal.locy, myIal.locz), 
                          action, getRmoveTable(myIal));                    //Update JAL table too
        System.out.println("Tried to update move table");
      }

      myIal.doAction(action);                                               //Ial does an action
      if(myIal.myState == Agent.State.PLACE && checkTable[action] < 10) {     //Check if placement was wrong, if so, remove it
        myIal.wait(250);
        myIal.removeBlock(myIal.locx, 4 + (myIal.locy), myIal.locz);
        System.out.println("Tried to remove a block");
      }
      if(myIal.myState == Agent.State.PLACE && !checkNotDone())
          clearAreaReset();
      
      myJal.wait(500);
      //********************************************************************************************************************************************************
      //Do a JAL action
      if(myJal.lookBlock(myJal.locx, myJal.locy, myJal.locz).equals("air")) {      //Assess current location and change state accordingly
        myJal.myState = Agent.State.PLACE;
      } else {
        myJal.myState = Agent.State.MOVE;
      }

      System.out.println("Checked spot JAL");

      //Given current state... do an action
      action = getRandAction();
      System.out.println("JAL action: " + action);
      System.out.println("JAL at:" + myJal.locx + " " + myJal.locy + " " + myJal.locz);
      if(myJal.myState == Agent.State.PLACE) {
        myJal.updateQtable(myJal.getPlaceTable(myJal.locx, myJal.locy, myJal.locz),
                          action, getRplaceTable(myJal));                           //Jal updates table
        checkTable = getRplaceTable(myJal);
        System.out.println("Tried to update place table");
        
      } else if(myJal.myState == Agent.State.MOVE) {
        myJal.updateQtable(myJal.getMoveTable(myJal.locx, myJal.locy, myJal.locz), 
                          action, getRmoveTable(myJal));                            //Jal updates table
        System.out.println("Tried to update move table");
      }

      myJal.doAction(action);                                                       //Jal does an action
      if(myJal.myState == Agent.State.PLACE && checkTable[action] < 5) {        //Check if placement was wrong, if so, remove it
        myJal.wait(250);                                    
        myJal.removeBlock(myJal.locx, 4 + (myJal.locy), myJal.locz);
        System.out.println("Tried to remove a block");
      }
      if(myJal.myState == Agent.State.PLACE && !checkNotDone())
          clearAreaReset();
      
    }
  }

  //Performs using the complete tables
  public void doPerform() {
    
    float[] curTable = myIal.getPlaceTable(0, 0, 0);
  
    while(checkNotDone()) {
      System.out.println("Yeet");
      myIal.wait(350);
      //Do an IAL action *****************************************************************************************************************
      if(myIal.lookBlock(myIal.locx, myIal.locy, myIal.locz).equals("air")) {      //Assess current location and change state accordingly
        myIal.myState = Agent.State.PLACE;
        System.out.print("Placing...");
      } else {
        myIal.myState = Agent.State.MOVE;
        System.out.print("Moving...");
      }

      System.out.print(myIal.locx + " " + myIal.locy + " " + myIal.locz);

      if(myIal.myState == Agent.State.PLACE) 
        curTable = myIal.getPlaceTable(myIal.locx, myIal.locy, myIal.locz);
      else if(myIal.myState == Agent.State.MOVE) 
        curTable = myIal.getMoveTable(myIal.locx, myIal.locy, myIal.locz);
      
      myIal.doAction(getMaxIndex(curTable));
      System.out.println("IAL action done");


      myJal.wait(350);
      //Do a JAL action *****************************************************************************************************************
      if(myJal.lookBlock(myJal.locx, myJal.locy, myJal.locz).equals("air")) {      //Assess current location and change state accordingly
        myJal.myState = Agent.State.PLACE;
        System.out.print("Placing...");
      } else {
        myJal.myState = Agent.State.MOVE;
        System.out.print("Moving...");
      }

      System.out.print(myJal.locx + " " + myJal.locy + " " + myJal.locz);

      if(myJal.myState == Agent.State.PLACE) 
        curTable = myJal.getPlaceTable(myJal.locx, myJal.locy, myJal.locz);
      else if(myJal.myState == Agent.State.MOVE) 
        curTable = myJal.getMoveTable(myJal.locx, myJal.locy, myJal.locz);
      
      myJal.doAction(getMaxIndex(curTable));
      System.out.println("JAL action done");
    }

    System.out.println("Building is done :)");
  }

  //Checks to see if no holes are filled 
  public boolean checkNotDone() {

    for(int y = 0; y < buildHeight+1; y++) {
      for(int z = 0; z < buildSize; z++) {
        for(int x = 0; x < buildSize; x++) {
          System.out.println("Yote");
          if(myIal.lookBlock(x, y, z).equals("air")) {
            System.out.println("Sming");
            return true;
          }
        }
      }
    }

    return false;
  }

  //Will clean area of the environment
  //Also resets the agents currently observed location
  public void clearAreaReset() {
    for(int x = 0; x < 5; x++) {
      for(int y = 4; y < 10; y++) {
        for(int z = 0; z < 5; z++) {
          myIal.removeBlock(x, y, z);
        }
      }
    }

    myIal.locx = 0; myIal.locy = 0; myIal.locz = 0;
    myJal.locx = 0; myJal.locy = 0; myJal.locz = 0;
  }
  

}