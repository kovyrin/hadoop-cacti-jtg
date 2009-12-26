/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jointhegrid.hadoopjmx.hadoop_0_21.trending.namenode;

import com.jointhegrid.hadoopjmx.JMXBase;
import com.jointhegrid.hadoopjmx.JMXBaseException;

/**
 *
 * @author ecapriolo
 */
public class FSNamesystemState extends JMXBase {

  public FSNamesystemState(){
    super();
    this.wantedVariables = new String[] {
      "BlocksTotal",
      "CapacityRemaining", 
      "CapacityTotal",
      "CapacityUsed" ,
      "FSState",
      "FilesTotal",
      "PendingReplicationBlocks",
      "ScheduledReplicationBlocks",
      "TotalLoad",
      "UnderReplicatedBlocks",
      "NumLiveDataNodes",
      "NumDeadDataNodes"
    };
  }

  public static void main (String [] args)
  throws JMXBaseException {
    FSNamesystemState state = new FSNamesystemState();
    String [] testargs = new String [] {
      "service:jmx:rmi:///jndi/rmi://localhost:10001/jmxrmi" ,
      "controlRole",
      "password",
      "hadoop:service=NameNode,name=FSNamesystemState" };
    state.setup(args);
    state.fetch();
    state.output();
  }
}
