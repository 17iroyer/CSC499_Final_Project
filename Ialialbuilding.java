//Ian Royer
//Made: 10-5-2020
//Mission for building with two Ial agents

public class Ialialbuilding extends Buildmission {

  private Ial myIal;
  private Ial my2Ial;

  //Constructor assigning agents to the mission
  public Ialialbuilding(Ial myIal, Ial my2Ial, int buildSize, int buildHeight) {
    this.myIal = myIal;
    this.my2Ial = my2Ial;
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

      //myIal.wait(500);
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
        if(getRplaceTable(myIal)[action] > 0) {
          myIal.myState = Agent.State.MOVE;
        }
      }
      else if(myIal.myState == Agent.State.MOVE) {
        checkTable = myIal.getMoveTable(myIal.locx, myIal.locy, myIal.locz);
        if(getMaxValue(checkTable) > 0) {
          action = getMaxIndex(checkTable);
        }
        myIal.updateQtable(myIal.getMoveTable(myIal.locx, myIal.locy, myIal.locz), 
                          action, getRmoveTable(myIal));                    //Ial updates table
        myIal.doAction(action);
        myIal.myState = Agent.State.PLACE;
      }
      
      //my2Ial.wait(500);
      //********************************************************************************************************************************************************
      //Do a 2IAL action
      //Given current state... do an action
      action = getRandAction();
      if(my2Ial.myState == Agent.State.PLACE) {
        checkTable = my2Ial.getPlaceTable(my2Ial.locx, my2Ial.locy, my2Ial.locz);
        if(getMaxValue(checkTable) > 0) {
          action = getMaxIndex(checkTable);
        }
        my2Ial.updateQtable(my2Ial.getPlaceTable(my2Ial.locx, my2Ial.locy, my2Ial.locz),
                          action, getRplaceTable(my2Ial));                           //2Ial updates table
        if(getRplaceTable(my2Ial)[action] > 0) {
          my2Ial.myState = Agent.State.MOVE;
        }
      } else if(my2Ial.myState == Agent.State.MOVE) {
        checkTable = my2Ial.getPlaceTable(my2Ial.locx, my2Ial.locy, my2Ial.locz);
        if(getMaxValue(checkTable) > 0) {
          action = getMaxIndex(checkTable);
        }
        my2Ial.updateQtable(my2Ial.getMoveTable(my2Ial.locx, my2Ial.locy, my2Ial.locz), 
                          action, getRmoveTable(my2Ial));                            //2Ial updates table
        my2Ial.doAction(action);
        my2Ial.myState = Agent.State.PLACE;
      }
    }
  }

  //Performs using the complete tables
  public void doPerform() {
    int noAct = 0;
    float[] curTable = myIal.getPlaceTable(0, 0, 0);
    
    while(checkNotDone()) {
      if(noAct > 3)
        return;

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

      my2Ial.wait(150);
      //Do a 2IAL action *****************************************************************************************************************
      if(my2Ial.lookBlock(my2Ial.locx, my2Ial.locy, my2Ial.locz) == "air") {      //Assess current location and change state accordingly
        my2Ial.myState = Agent.State.PLACE;
      } else {
        my2Ial.myState = Agent.State.MOVE;
      }
      //System.out.println("ysysy");
      if(my2Ial.myState == Agent.State.PLACE) {
        
        curTable = my2Ial.getPlaceTable(my2Ial.locx, my2Ial.locy, my2Ial.locz);
        noAct = 0;
      }
      else if(my2Ial.myState == Agent.State.MOVE) {
        curTable = my2Ial.getMoveTable(my2Ial.locx, my2Ial.locy, my2Ial.locz);
        noAct++;
      }
      
      my2Ial.doAction(getMaxIndex(curTable));
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
          if(myIal.lookBlock(x, y, z).equals("air")) {
            notDone = true;
          } else {
            blocksPut++;
          }
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
    my2Ial.locx = 0; my2Ial.locy = 0; my2Ial.locz = 0;
  }

}