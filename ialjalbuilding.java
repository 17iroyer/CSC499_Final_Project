//Ian Royer
//class for an IAL/JAL mission
//Created: 9/2/20
//Updated: 10/20/20

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
      rmov0_0 = new float[]{-10, 10, -1, 5, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
      rmov1_0 = new float[]{1, -10, -1, 10, 5, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
      rmov0_1 = new float[]{1, 1, -1, -10, 10, -1, -1, -1, -1, 5, -1, -1, -1, -1, -1, -1, -1, -1};
      rmov1_1 = new float[]{1, 1, -1, 1, -10, -1, -1, -1, -1, 10, 5, -1, -1, -1, -1, -1, -1, -1};
    }
    if(buildSize == 3) {
      rmov0_0 =  new float[]{-10, 10, 5, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
      rmov1_0 =  new float[]{1, -10, 10, 5, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
      rmov2_0 =  new float[]{1, 1, -10, 10, 5, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
      rmov0_1 =  new float[]{1, 1, 1, -10, 10, 5, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
      rmov1_1 =  new float[]{1, 1, 1, 1, -10, 10, 5, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
      rmov2_1 =  new float[]{1, 1, 1, 1, 1, -10, 10, 5, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
      rmov0_2 =  new float[]{1, 1, 1, 1, 1, 1, -10, 10, 5, -1, -1, -1, -1, -1, -1, -1, -1, -1};
      rmov1_2 =  new float[]{1, 1, 1, 1, 1, 1, 1, -10, 10, 5, -1, -1, -1, -1, -1, -1, -1, -1};
      rmov2_2 =  new float[]{1, 1, 1, 1, 1, 1, 1, 1, -10, 10, 5, -1, -1, -1, -1, -1, -1, -1};
    }
    if(buildHeight == 1 && buildSize == 2) {
      rmovu0_0 = new float[]{1, 1, -1, 1, 1, -1, -1, -1, -1, -10, 10, -1, 5, -1, -1, -1, -1, -1};
      rmovu1_0 = new float[]{1, 1, -1, 1, 1, -1, -1, -1, -1, 1, -10, -1, 10, 5, -1, -1, -1, -1};
      rmovu0_1 = new float[]{5, 1, -1, 1, 1, -1, -1, -1, -1, 1, 1, -1, -10, 10, -1, -1, -1, -1};
      rmovu1_1 = new float[]{10, 5, -1, 1, 1, -1, -1, -1, -1, 1, 1, -1, 1, -10, -1, -1, -1, -1};
    } 
    if(buildHeight == 1 && buildSize == 3) {
      rmovu0_0 = new float[]{1, 1, 1, 1, 1, 1, 1, 1, 1, -10, 10, 5, -1, -1, -1, -1, -1, -1};
      rmovu1_0 = new float[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -10, 10, 5, -1, -1, -1, -1, -1};
      rmovu2_0 = new float[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -10, 10, 5, -1, -1, -1, -1};
      rmovu0_1 = new float[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -10, 10, 5, -1, -1, -1};
      rmovu1_1 = new float[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -10, 10, 5, -1, -1};
      rmovu2_1 = new float[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -10, 10, 5, -1};
      rmovu0_2 = new float[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -10, 10, 5};
      rmovu1_2 = new float[]{5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -10, 10};
      rmovu2_2 = new float[]{10, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -10};
    }
  }


  //Performs the learning trials to populate
  // each agent's tables
  public void doLearnTrials() {
    //clearAreaReset();

    int action = 0;
    float[] checkTable = getRplaceTable(myIal);

    for(int trial = 0; trial < learnTrials; trial++) {      //For each of the learning trials.....
      //Do an IAL action ***************************************************************************************************************************************
      //Given the current state... do a random action
      action = getRandAction();

      if(myIal.myState == Agent.State.PLACE) {
        checkTable = myIal.getPlaceTable(myIal.locx, myIal.locy, myIal.locz);
        if(getMaxValue(checkTable) > 0) {
          action = getMaxIndex(checkTable);
        }
        myIal.updateQtable(myIal.getPlaceTable(myIal.locx, myIal.locy, myIal.locz), 
                           action, getRplaceTable(myIal));                  //Ial updates table
        myJal.updateQtable(myJal.getPlaceTable(myIal.locx, myIal.locy, myIal.locz),
                           action, getRplaceTable(myIal));                  //Update JAL table too
         if (getRplaceTable(myIal)[action] > 0) {
           myIal.myState = Agent.State.MOVE;
         }
  
      } else if(myIal.myState == Agent.State.MOVE) {
        checkTable = myIal.getMoveTable(myIal.locx, myIal.locy, myIal.locz);
        if(getMaxValue(checkTable) > 0) {
          action = getMaxIndex(checkTable);
        }
        myIal.updateQtable(myIal.getMoveTable(myIal.locx, myIal.locy, myIal.locz), 
                          action, getRmoveTable(myIal));                    //Ial updates table
        myJal.updateQtable(myJal.getMoveTable(myIal.locx, myIal.locy, myIal.locz), 
                          action, getRmoveTable(myIal));                    //Update JAL table too
        myIal.doAction(action);                                               //Ial does an action
        myIal.myState = Agent.State.PLACE;
      }
      
      //myJal.wait(100);
      //********************************************************************************************************************************************************
      //Do a JAL action
      //Given current state... do an action
      action = getRandAction();
      
      if(myJal.lookBlock(myJal.locx, myJal.locy, myJal.locz).equals("air")) {      //Assess current location and change state accordingly
        myJal.myState = Agent.State.PLACE;
      } else {
        myJal.myState = Agent.State.MOVE;
      }

      if(myJal.myState == Agent.State.PLACE) {
        checkTable = myJal.getPlaceTable(myJal.locx, myJal.locy, myJal.locz);
        if(getMaxValue(checkTable) > 0) {
          action = getMaxIndex(checkTable);
        }
        if(getRplaceTable(myJal)[action] > 0) {
          action = getMaxIndex(checkTable);
        }
        myJal.updateQtable(myJal.getPlaceTable(myJal.locx, myJal.locy, myJal.locz),
                          action, getRplaceTable(myJal));                           //Jal updates table
      } else if(myJal.myState == Agent.State.MOVE) {
        checkTable = myJal.getMoveTable(myJal.locx, myJal.locy, myJal.locz);
        if(getMaxValue(checkTable) > 0) {
          action = getMaxIndex(checkTable);
        }
        if(getRplaceTable(myJal)[action] > 0) {
          action = getMaxIndex(checkTable);
        }
        myJal.updateQtable(myJal.getMoveTable(myJal.locx, myJal.locy, myJal.locz), 
                          action, getRmoveTable(myJal));                            //Jal updates table
        myJal.doAction(action);                                                       //Jal does an action
        myJal.myState = Agent.State.PLACE;
      }
      
    }
  }

  //Performs using the complete tables
  public void doPerform() {
    int noAct = 0;
    float[] curTable = myIal.getPlaceTable(0, 0, 0);
  
    while(checkNotDone()) {
      if(noAct > 3) {
        return;
      }
      myIal.wait(150);
      //Do an IAL action *****************************************************************************************************************
      if(myIal.lookBlock(myIal.locx, myIal.locy, myIal.locz).equals("air")) {      //Assess current location and change state accordingly
        myIal.myState = Agent.State.PLACE;
      } else {
        myIal.myState = Agent.State.MOVE;
      }

      if(myIal.myState == Agent.State.PLACE) {
        curTable = myIal.getPlaceTable(myIal.locx, myIal.locy, myIal.locz);
        noAct = 0;
      }
      else if(myIal.myState == Agent.State.MOVE) {
        curTable = myIal.getMoveTable(myIal.locx, myIal.locy, myIal.locz);
        noAct++;
      }
      
      myIal.doAction(getMaxIndex(curTable));
      //System.out.println("IAL action done");


      myJal.wait(150);
      //Do a JAL action *****************************************************************************************************************
      if(myJal.lookBlock(myJal.locx, myJal.locy, myJal.locz).equals("air")) {      //Assess current location and change state accordingly
        myJal.myState = Agent.State.PLACE;
        //System.out.print("Placing...");
      } else {
        myJal.myState = Agent.State.MOVE;
        //System.out.print("Moving...");
      }

      if(myJal.myState == Agent.State.PLACE) {
        curTable = myJal.getPlaceTable(myJal.locx, myJal.locy, myJal.locz);
        noAct = 0;
      }
      else if(myJal.myState == Agent.State.MOVE) { 
        curTable = myJal.getMoveTable(myJal.locx, myJal.locy, myJal.locz);
        noAct++;
      }
      
      myJal.doAction(getMaxIndex(curTable));
    }

    System.out.println("Building is done :)");
  }

  //Checks to see if no holes are filled 
  public boolean checkNotDone() {
    boolean notDone = false;
    blocksPut = 0;

    for(int y = 0; y < buildHeight+1; y++) {
      for(int z = 0; z < buildSize; z++) {
        for(int x = 0; x < buildSize; x++) {
          if(myIal.lookBlock(x, y, z).equals("air"))
            notDone = true;
          else 
            blocksPut++;
        }
      }
    }

    return notDone;
  }

  //Will clean area of the environment
  //Also resets the agents currently observed location
  public void clearAreaReset() {
    for(int x = 0; x < buildSize; x++) {
      for(int y = 4; y < 7; y++) {
        for(int z = 0; z < buildSize; z++) {
          myIal.removeBlock(x, y, z);
        }
      }
    }

    myIal.locx = 0; myIal.locy = 0; myIal.locz = 0;
    myJal.locx = 0; myJal.locy = 0; myJal.locz = 0;
  }
  

}