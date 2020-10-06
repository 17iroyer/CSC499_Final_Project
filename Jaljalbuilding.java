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
  }


  //Performs the learning trials to populate
  // each agent's tables
  public void doLearnTrials() {
    int action = 0;
    float[] checkTable = getRplaceTable(my2Jal);

    for(int trial = 0; trial < learnTrials; trial++) {      //For each of the learning trials.....

      //Do an 2JAL action ***************************************************************************************************************************************
      if(my2Jal.lookBlock(my2Jal.locx, my2Jal.locy, my2Jal.locz) == "air") {      //Assess current location and change state accordingly
        my2Jal.myState = Agent.State.PLACE;
      } else {
        my2Jal.myState = Agent.State.MOVE;
      }

      System.out.println("Checked Spot IAL");

      //Given the current state... do a random action
      action = getRandAction();
      my2Jal.doAction(action);                                               //2Jal does an action
      if(my2Jal.myState == Agent.State.PLACE) {
        my2Jal.updateQtable(my2Jal.getPlaceTable(my2Jal.locx, my2Jal.locy, my2Jal.locz), 
                           action, getRplaceTable(my2Jal));                  //2Jal updates table
        checkTable = getRplaceTable(my2Jal);
        myJal.updateQtable(myJal.getPlaceTable(my2Jal.locx, my2Jal.locy, my2Jal.locz),
                           action, getRplaceTable(myJal));                  //Update JAL table too

        if((int) checkTable[action] != 10) {                                //Check if placement was wrong, if so, remove it
          my2Jal.removeBlock(my2Jal.locx, 4 + (my2Jal.locy), my2Jal.locz);
        }
      }
      else if(my2Jal.myState == Agent.State.MOVE) {
        my2Jal.updateQtable(my2Jal.getMoveTable(my2Jal.locx, my2Jal.locy, my2Jal.locz), 
                          action, getRmoveTable(my2Jal));                    //2Jal updates table
        checkTable = getRmoveTable(my2Jal);
        myJal.updateQtable(myJal.getMoveTable(my2Jal.locx, my2Jal.locy, my2Jal.locz), 
                          action, getRmoveTable(myJal));                    //Update JAL table too
      }
      
      //********************************************************************************************************************************************************
      //Do a JAL action
      if(myJal.lookBlock(myJal.locx, myJal.locy, myJal.locz) == "air") {      //Assess current location and change state accordingly
        myJal.myState = Agent.State.PLACE;
      } else {
        myJal.myState = Agent.State.MOVE;
      }

      System.out.println("Checked spot JAL");

      //Given current state... do an action
      action = getRandAction();
      myJal.doAction(action);                                                       //Jal does an action
      if(myJal.myState == Agent.State.PLACE) {
        myJal.updateQtable(myJal.getPlaceTable(myJal.locx, myJal.locy, myJal.locz),
                          action, getRplaceTable(myJal));                           //Jal updates table
        checkTable = getRplaceTable(myJal);
        my2Jal.updateQtable(my2Jal.getPlaceTable(my2Jal.locx, my2Jal.locy, my2Jal.locz),
                              action, getRplaceTable(my2Jal));                      //Update 2Jal table too

        if((int) checkTable[action] != 10) {                                        //Check if placement was wrong, if so, remove it
          myJal.removeBlock(myJal.locx, 4 + (myJal.locy), myJal.locz);
        }
      } else if(myJal.myState == Agent.State.MOVE) {
        myJal.updateQtable(myJal.getMoveTable(my2Jal.locx, my2Jal.locy, my2Jal.locz), 
                          action, getRmoveTable(myJal));                            //Jal updates table
        checkTable = getRplaceTable(myJal);
        my2Jal.updateQtable(my2Jal.getPlaceTable(my2Jal.locx, my2Jal.locy, my2Jal.locz),
                            action, getRplaceTable(my2Jal));                       //Update 2Jal table too
      }

      if(checkDone()) {
        clearAreaReset();
      }
    }
  }

  //Performs using the complete tables
  public void doPerform() {
    
    float[] curTable = myJal.getPlaceTable(0, 0, 0);
    
    while(!checkDone()) {

      //Do a 2JAL action *****************************************************************************************************************
      if(my2Jal.lookBlock(my2Jal.locx, my2Jal.locy, my2Jal.locz) == "air") {      //Assess current location and change state accordingly
        my2Jal.myState = Agent.State.PLACE;
      } else {
        my2Jal.myState = Agent.State.MOVE;
      }

      if(my2Jal.myState == Agent.State.PLACE) 
        curTable = my2Jal.getPlaceTable(my2Jal.locx, my2Jal.locy, my2Jal.locz);
      else if(my2Jal.myState == Agent.State.MOVE) 
        curTable = my2Jal.getMoveTable(my2Jal.locx, my2Jal.locy, my2Jal.locz);
      
      my2Jal.doAction(getMaxIndex(curTable));
      System.out.println("2JAL action done");


      //Do a JAL action *****************************************************************************************************************
      if(myJal.lookBlock(myJal.locx, myJal.locy, myJal.locz) == "air") {      //Assess current location and change state accordingly
        myJal.myState = Agent.State.PLACE;
      } else {
        myJal.myState = Agent.State.MOVE;
      }

      if(myJal.myState == Agent.State.PLACE) 
        curTable = myJal.getPlaceTable(my2Jal.locx, my2Jal.locy, my2Jal.locz);
      else if(myJal.myState == Agent.State.MOVE) 
        curTable = myJal.getMoveTable(myJal.locx, myJal.locy, myJal.locz);
      
      myJal.doAction(getMaxIndex(curTable));
      System.out.println("JAL action done");
    }

    System.out.println("Building is done :)");
  }

  //Checks to see if no holes are filled 
  public boolean checkDone() {
    boolean done = true;

    for(int y = 0; y < buildHeight; y++) {
      for(int z = 0; z < buildSize; z++) {
        for(int x = 0; x < buildSize; x++) {
          if(my2Jal.lookBlock(x, y, z) == "air")
            done = false;
        }
      }
    }

    return done;
  }

  //Will clean area of the environment
  //Also resets the agents currently observed location
  public void clearAreaReset() {
    for(int x = 0; x < 3; x++) {
      for(int y = 4; y < 6; y++) {
        for(int z = 0; z < 3; z++) {
          my2Jal.removeBlock(x, y, z);
        }
      }
    }

    my2Jal.locx = 0; my2Jal.locy = 0; my2Jal.locz = 0;
    myJal.locx = 0; myJal.locy = 0; myJal.locz = 0;
  }

}