/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jointhegrid.hadoopjmx;

/**
 *
 * @author ecapriolo
 */
public class FSDatasetStatus extends JMXBase{

  public FSDatasetStatus(){
    wantedVariables = new String[] {
    "Remaining",
    "Capacity",
    "DfsUsed" };
    wantedOperations = new String [] {};
  }

  public static void main(String[] args) {

    FSDatasetStatus fsd = new FSDatasetStatus();
    if (args.length != 4) {
      System.err.println("Wrong number of arguments. ");
      return;
    } else {
      fsd.setJmxURL(args[0]);
      fsd.setUser(args[1]);
      fsd.setPass(args[2]);
      fsd.setObjectName(args[3]);
    }
    fsd.output();
  }
  
}