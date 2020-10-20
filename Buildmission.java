//Ian Royer
//Basic mission class for building
//Made: 10-5-2020

import com.microsoft.msr.malmo.*;
import java.util.Random;

public class Buildmission {

  public int learnTrials = 50;
  public int buildSize;
  public int buildHeight;
  
  //Reward table for moving the agents
  public float[] rmov0_0, rmov1_0, rmov2_0, rmov0_1,
                 rmov1_1, rmov2_1, rmov0_2, rmov1_2,
                 rmov2_2, rmovu0_0, rmovu1_0, rmovu2_0,
                 rmovu0_1, rmovu1_1, rmovu2_1, rmovu0_2,
                 rmovu1_2, rmovu2_2;
  
  //Reward table for placing blocks as the agent
  public float[] rpla0_0 =  {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  public float[] rpla1_0 =  {0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  public float[] rpla2_0 =  {0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  public float[] rpla0_1 =  {0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  public float[] rpla1_1 =  {0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  public float[] rpla2_1 =  {0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  public float[] rpla0_2 =  {0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  public float[] rpla1_2 =  {0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  public float[] rpla2_2 =  {0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  public float[] rplau0_0 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0};
  public float[] rplau1_0 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0};
  public float[] rplau2_0 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0};
  public float[] rplau0_1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0};
  public float[] rplau1_1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0};
  public float[] rplau2_1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0};
  public float[] rplau0_2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0};
  public float[] rplau1_2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0};
  public float[] rplau2_2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10};

  //Returns the correct R-table for the agent.
  public float[] getRmoveTable(Agent myAgent) {
    switch(myAgent.locx) {
      case 0:
        switch(myAgent.locy) {
          case 0:
            switch(myAgent.locz)
            {
              case 0: return rmov0_0;
              case 1: return rmov0_1;
              case 2: return rmov0_2;
            }
          break;
          case 1:
            switch(myAgent.locz)
            {
              case 0: return rmovu0_0;
              case 1: return rmovu0_1;
              case 2: return rmovu0_2;
            }
          break;
        }
      break;
      case 1:
        switch(myAgent.locy) {
          case 0:
            switch(myAgent.locz)
            {
              case 0: return rmov1_0;
              case 1: return rmov1_1;
              case 2: return rmov1_2;
            }
          break;
          case 1:
            switch(myAgent.locz)
            {
              case 0: return rmovu1_0;
              case 1: return rmovu1_1;
              case 2: return rmovu1_2;
            }
          break;
        }
      break;
      case 2:
        switch(myAgent.locy) {
          case 0:
            switch(myAgent.locz)
            {
              case 0: return rmov2_0;
              case 1: return rmov2_1;
              case 2: return rmov2_2;
            }
          break;
          case 1:
            switch(myAgent.locz)
            {
              case 0: return rmovu2_0;
              case 1: return rmovu2_1;
              case 2: return rmovu2_2;
            }
          break;
        }
      break;
    }
     return null;
  }

  //Returns the correct R-table for the agent
  public float[] getRplaceTable(Agent myAgent) {
    switch(myAgent.locx) {
      case 0:
        switch(myAgent.locy) {
          case 0:
            switch(myAgent.locz)
            {
              case 0: return rpla0_0; 
              case 1: return rpla0_1;
              case 2: return rpla0_2;
            }
          break;
          case 1:
            switch(myAgent.locz)
            {
              case 0: return rplau0_0;
              case 1: return rplau0_1;
              case 2: return rplau0_2;
            }
          break;
        }
      break;
      case 1:
        switch(myAgent.locy) {
          case 0:
            switch(myAgent.locz)
            {
              case 0: return rpla1_0;
              case 1: return rpla1_1;
              case 2: return rpla1_2;
            }
          break;
          case 1:
            switch(myAgent.locz)
            {
              case 0: return rplau1_0;
              case 1: return rplau1_1;
              case 2: return rplau1_2;
            }
          break;
        }
      break;
      case 2:
        switch(myAgent.locy) {
          case 0:
            switch(myAgent.locz)
            {
              case 0: return rpla2_0;
              case 1: return rpla2_1;
              case 2: return rpla2_2;
            }
          break;
          case 1:
             switch(myAgent.locz)
            {
              case 0: return rplau2_0;
              case 1: return rplau2_1;
              case 2: return rplau2_2;
            }
          break;
        }
      break;
    }
     return null;
  }

  
  //Returns a random action (ie random array location)
  public int getRandAction() {
    Random r = new Random();
    int action = 0;

    if(buildSize == 2) {
      action = r.nextInt(2);
      if(r.nextInt(2) == 1)
        action += 3;
    } else if(buildSize == 3) {
      action = r.nextInt(9);
    }

    
    if(buildHeight == 1 && r.nextInt(2) == 1) 
      action += 9;

    return action;
  }

  //Returns the max index of an array
  //(Used when using populated Q-tables)
  public int getMaxIndex(float[] table) {
    int maxInd = 0;
    float maxVal = (float) 0.0;
    for(int i = 0; i < 18; i++) {
      if(table[i] > maxVal) {
        maxInd = i;
        maxVal = table[i];
      }
    }

    return maxInd;
  }
}