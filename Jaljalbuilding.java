//Ian Royer
//Made: 10-5-2020
//Mission for building with two JAL agents

public class Jaljalbuilding extends Buildmission {

  private Jal my2Jal;
  private Jal myJal;

  //Constructor assigning agents to the mission
  public Jaljalbuilding(Jal my2Jal, Jal myJal, int buildSize, int buildHeight) {
    this.my2Jal = my2Jal;
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
    float[] checkTable = getRplaceTable(my2Jal);

    for(int trial = 0; trial < learnTrials; trial++) {      //For each of the learning trials.....
      //Do an 2JAL action ***************************************************************************************************************************************
      //Given the current state... do a random action
      action = getRandAction();
      if(my2Jal.myState == Agent.State.PLACE) {
        checkTable = my2Jal.getPlaceTable(my2Jal.locx, my2Jal.locy, my2Jal.locz);
        if(getMaxValue(checkTable) > 0) {
          action = getMaxIndex(checkTable);
        }
        my2Jal.updateQtable(my2Jal.getPlaceTable(my2Jal.locx, my2Jal.locy, my2Jal.locz), 
                           action, getRplaceTable(my2Jal));                  //2Jal updates table
        myJal.updateQtable(myJal.getPlaceTable(my2Jal.locx, my2Jal.locy, my2Jal.locz),
                           action, getRplaceTable(my2Jal));                  //Update JAL table too
        if(checkTable[action] > 0) {
          my2Jal.myState = Agent.State.MOVE;
        }
      }
      else if(my2Jal.myState == Agent.State.MOVE) {
        checkTable = my2Jal.getMoveTable(my2Jal.locx, my2Jal.locy, my2Jal.locz);
        if(getMaxValue(checkTable) > 0) {
          action = getMaxIndex(checkTable);
        }
        my2Jal.updateQtable(my2Jal.getMoveTable(my2Jal.locx, my2Jal.locy, my2Jal.locz), 
                          action, getRmoveTable(my2Jal));                    //2Jal updates table
        myJal.updateQtable(myJal.getMoveTable(my2Jal.locx, my2Jal.locy, my2Jal.locz), 
                          action, getRmoveTable(my2Jal));                    //Update JAL table too
        my2Jal.doAction(action);
        my2Jal.myState = Agent.State.PLACE;
      }

      //********************************************************************************************************************************************************
      //Do a JAL action
      //Given current state... do an action
      action = getRandAction();
      if(myJal.myState == Agent.State.PLACE) {
        checkTable = myJal.getPlaceTable(myJal.locx, myJal.locy, myJal.locz);
        if(getMaxValue(checkTable) > 0) {
          action = getMaxIndex(checkTable);
        }
        myJal.updateQtable(myJal.getPlaceTable(myJal.locx, myJal.locy, myJal.locz),
                          action, getRplaceTable(myJal));                           //Jal updates table
        my2Jal.updateQtable(my2Jal.getPlaceTable(myJal.locx, myJal.locy, myJal.locz),
                              action, getRplaceTable(myJal));                      //Update 2Jal table too
        if(checkTable[action] > 0) {
          myJal.myState = Agent.State.MOVE;
        }
      } else if(myJal.myState == Agent.State.MOVE) {
        checkTable = myJal.getMoveTable(myJal.locx, myJal.locy, myJal.locz);
        if(getMaxValue(checkTable) > 0) {
          action = getMaxIndex(checkTable);
        }
        myJal.updateQtable(myJal.getMoveTable(myJal.locx, myJal.locy, myJal.locz), 
                          action, getRmoveTable(myJal));                            //Jal updates table
        my2Jal.updateQtable(my2Jal.getPlaceTable(myJal.locx, myJal.locy, myJal.locz),
                            action, getRplaceTable(myJal));                       //Update 2Jal table too
        myJal.doAction(action);
        myJal.myState = Agent.State.PLACE;
      }
    }
  }

  //Performs using the complete tables
  public void doPerform() {
    int noAct = 0;
    float[] curTable = myJal.getPlaceTable(0, 0, 0);
    
    while(checkNotDone()) {
      if(noAct > 5) {
        return;
      }

      my2Jal.wait(150);
      //Do a 2JAL action *****************************************************************************************************************
      if(my2Jal.lookBlock(my2Jal.locx, my2Jal.locy, my2Jal.locz).equals("air")) {      //Assess current location and change state accordingly
        my2Jal.myState = Agent.State.PLACE;
      } else {
        my2Jal.myState = Agent.State.MOVE;
      }

      if(my2Jal.myState == Agent.State.PLACE) {
        curTable = my2Jal.getPlaceTable(my2Jal.locx, my2Jal.locy, my2Jal.locz);
        noAct = 0;
      } else if(my2Jal.myState == Agent.State.MOVE) {
        curTable = my2Jal.getMoveTable(my2Jal.locx, my2Jal.locy, my2Jal.locz);
        noAct++;
      }

      my2Jal.doAction(getMaxIndex(curTable));
      //System.out.println("2JAL action done");

      myJal.wait(150);
      //Do a JAL action *****************************************************************************************************************
      if(myJal.lookBlock(myJal.locx, myJal.locy, myJal.locz).equals("air")) {      //Assess current location and change state accordingly
        myJal.myState = Agent.State.PLACE;
      } else {
        myJal.myState = Agent.State.MOVE;
      }

      if(myJal.myState == Agent.State.PLACE) {
        curTable = myJal.getPlaceTable(my2Jal.locx, my2Jal.locy, my2Jal.locz);
        noAct = 0;
      } else if(myJal.myState == Agent.State.MOVE) {
        curTable = myJal.getMoveTable(myJal.locx, myJal.locy, myJal.locz);
        noAct++;
      }
      myJal.doAction(getMaxIndex(curTable));
      //System.out.println("JAL action done");
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
          if(my2Jal.lookBlock(x, y, z).equals("air")) 
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
          my2Jal.removeBlock(x, y, z);
        }
      }
    }

    my2Jal.locx = 0; my2Jal.locy = 0; my2Jal.locz = 0;
    myJal.locx = 0; myJal.locy = 0; myJal.locz = 0;
  }

}