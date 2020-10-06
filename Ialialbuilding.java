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
  }


  //Performs the learning trials to populate
  // each agent's tables
  public void doLearnTrials() {
    int action = 0;
    float[] checkTable = getRplaceTable(myIal);

    for(int trial = 0; trial < learnTrials; trial++) {      //For each of the learning trials.....

      //Do an IAL action ***************************************************************************************************************************************
      if(myIal.lookBlock(myIal.locx, myIal.locy, myIal.locz) == "air") {      //Assess current location and change state accordingly
        myIal.myState = Agent.State.PLACE;
      } else {
        myIal.myState = Agent.State.MOVE;
      }

      System.out.println("Checked Spot IAL");

      //Given the current state... do a random action
      action = getRandAction();
      myIal.doAction(action);                                               //Ial does an action
      if(myIal.myState == Agent.State.PLACE) {
        myIal.updateQtable(myIal.getPlaceTable(myIal.locx, myIal.locy, myIal.locz), 
                           action, getRplaceTable(myIal));                  //Ial updates table
        checkTable = getRplaceTable(myIal);

        if((int) checkTable[action] != 10) {                                //Check if placement was wrong, if so, remove it
          myIal.removeBlock(myIal.locx, 4 + (myIal.locy), myIal.locz);
        }
      }
      else if(myIal.myState == Agent.State.MOVE) {
        myIal.updateQtable(myIal.getMoveTable(myIal.locx, myIal.locy, myIal.locz), 
                          action, getRmoveTable(myIal));                    //Ial updates table
      }
      
      //********************************************************************************************************************************************************
      //Do a 2IAL action
      if(my2Ial.lookBlock(my2Ial.locx, my2Ial.locy, my2Ial.locz) == "air") {      //Assess current location and change state accordingly
        my2Ial.myState = Agent.State.PLACE;
      } else {
        my2Ial.myState = Agent.State.MOVE;
      }

      System.out.println("Checked spot JAL");

      //Given current state... do an action
      action = getRandAction();
      my2Ial.doAction(action);                                                       //2Ial does an action
      if(my2Ial.myState == Agent.State.PLACE) {
        my2Ial.updateQtable(my2Ial.getPlaceTable(my2Ial.locx, my2Ial.locy, my2Ial.locz),
                          action, getRplaceTable(my2Ial));                           //2Ial updates table
        checkTable = getRplaceTable(my2Ial);
        
        if((int) checkTable[action] != 10) {                                        //Check if placement was wrong, if so, remove it
          my2Ial.removeBlock(my2Ial.locx, 4 + (my2Ial.locy), my2Ial.locz);
        }
      } else if(my2Ial.myState == Agent.State.MOVE) {
        my2Ial.updateQtable(my2Ial.getMoveTable(myIal.locx, myIal.locy, myIal.locz), 
                          action, getRmoveTable(my2Ial));                            //2Ial updates table
      }

      if(checkDone()) {
        clearAreaReset();
      }
    }
  }

  //Performs using the complete tables
  public void doPerform() {
    
    float[] curTable = myIal.getPlaceTable(0, 0, 0);
    
    while(!checkDone()) {

      //Do an IAL action *****************************************************************************************************************
      if(myIal.lookBlock(myIal.locx, myIal.locy, myIal.locz) == "air") {      //Assess current location and change state accordingly
        myIal.myState = Agent.State.PLACE;
      } else {
        myIal.myState = Agent.State.MOVE;
      }

      if(myIal.myState == Agent.State.PLACE) 
        curTable = myIal.getPlaceTable(myIal.locx, myIal.locy, myIal.locz);
      else if(myIal.myState == Agent.State.MOVE) 
        curTable = myIal.getMoveTable(myIal.locx, myIal.locy, myIal.locz);
      
      myIal.doAction(getMaxIndex(curTable));
      System.out.println("IAL action done");


      //Do a 2IAL action *****************************************************************************************************************
      if(my2Ial.lookBlock(my2Ial.locx, my2Ial.locy, my2Ial.locz) == "air") {      //Assess current location and change state accordingly
        my2Ial.myState = Agent.State.PLACE;
      } else {
        my2Ial.myState = Agent.State.MOVE;
      }

      if(my2Ial.myState == Agent.State.PLACE) 
        curTable = my2Ial.getPlaceTable(myIal.locx, myIal.locy, myIal.locz);
      else if(my2Ial.myState == Agent.State.MOVE) 
        curTable = my2Ial.getMoveTable(my2Ial.locx, my2Ial.locy, my2Ial.locz);
      
      my2Ial.doAction(getMaxIndex(curTable));
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
          if(myIal.lookBlock(x, y, z) == "air")
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
          myIal.removeBlock(x, y, z);
        }
      }
    }

    myIal.locx = 0; myIal.locy = 0; myIal.locz = 0;
    my2Ial.locx = 0; my2Ial.locy = 0; my2Ial.locz = 0;
  }

}