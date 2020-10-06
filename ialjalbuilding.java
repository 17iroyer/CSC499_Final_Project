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
        myJal.updateQtable(myJal.getPlaceTable(myIal.locx, myIal.locy, myIal.locz),
                           action, getRplaceTable(myJal));                  //Update JAL table too

        if((int) checkTable[action] != 10) {                                //Check if placement was wrong, if so, remove it
          myIal.removeBlock(myIal.locx, 4 + (myIal.locy), myIal.locz);
        }
      }
      else if(myIal.myState == Agent.State.MOVE) {
        myIal.updateQtable(myIal.getMoveTable(myIal.locx, myIal.locy, myIal.locz), 
                          action, getRmoveTable(myIal));                    //Ial updates table
        checkTable = getRmoveTable(myIal);
        myJal.updateQtable(myJal.getMoveTable(myIal.locx, myIal.locy, myIal.locz), 
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
        
        if((int) checkTable[action] != 10) {                                        //Check if placement was wrong, if so, remove it
          myJal.removeBlock(myJal.locx, 4 + (myJal.locy), myJal.locz);
        }
      } else if(myJal.myState == Agent.State.MOVE) {
        myJal.updateQtable(myJal.getMoveTable(myIal.locx, myIal.locy, myIal.locz), 
                          action, getRmoveTable(myJal));                            //Jal updates table
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


      //Do a JAL action *****************************************************************************************************************
      if(myJal.lookBlock(myJal.locx, myJal.locy, myJal.locz) == "air") {      //Assess current location and change state accordingly
        myJal.myState = Agent.State.PLACE;
      } else {
        myJal.myState = Agent.State.MOVE;
      }

      if(myJal.myState == Agent.State.PLACE) 
        curTable = myJal.getPlaceTable(myIal.locx, myIal.locy, myIal.locz);
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
    myJal.locx = 0; myJal.locy = 0; myJal.locz = 0;
  }
  

}