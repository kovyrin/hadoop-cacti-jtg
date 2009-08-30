/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jointhegrid.hadoopjmx.hadoop_0_19.alerting.namenode;

import com.jointhegrid.hadoopjmx.hadoop_0_19.trending.namenode.FSNamesystemStatus;
import com.jointhegrid.hadoopjmx.JMXBaseException;

/**
 *
 * @author ecapriolo
 */
public class FSNamesystemStatusDeadDatanodeCheck extends FSNamesystemStatus {

  private long dead;
  
  public FSNamesystemStatusDeadDatanodeCheck() {
    super();
  }

  public static void main(String[] args) throws JMXBaseException {
    FSNamesystemStatusDeadDatanodeCheck c = new FSNamesystemStatusDeadDatanodeCheck();

    if (args.length != 5) {
      System.err.println("Wrong number of arguments. jmxurl user pass deadthresh ");
      return;
    }
    c.setJmxURL(args[0]);
    c.setUser(args[1]);
    c.setPass(args[2]);
    c.dead = Long.parseLong(args[3]);
    
    c.fetch();
    
    long deadnodes= ((Long) c.getWantedOperResultsRO().get("NumDeadDataNodes"));

    if ( deadnodes > c.dead ) {
      System.out.println("NumDeadDataNodes: FAILED current "+deadnodes);
      System.exit(1);
    } else {
      System.out.println("NumDeadDataNodes: OK current "+deadnodes);
      System.exit(0);
    }

  }
}
