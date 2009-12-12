/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jointhegrid.hadoopjmx.hbase_0_20_2.trending.hbase;

import com.jointhegrid.hadoopjmx.JMXBase;
import com.jointhegrid.hadoopjmx.JMXBaseException;

/**
 *
 * @author ecapriolo
 */
public class RpcStatistics extends JMXBase{

  public RpcStatistics(){
    super();
    this.wantedVariables = new String [] {
       "RpcProcessingTimeMaxTime", "RpcProcessingTimeAvgTime",
    "RpcProcessingTimeMinTime", "RpcProcessingTimeNumOps" ,
    "RpcQueueTimeAvgTime", "RpcQueueTimeMaxTime",
    "RpcQueueTimeMinTime" , "RpcQueueTimeNumOps"
    };
  }

  public static void main (String [] args) throws JMXBaseException {
        RpcStatistics state = new RpcStatistics();
    String [] testargs = new String [] {
      "service:jmx:rmi:///jndi/rmi://localhost:10001/jmxrmi" ,
      "controlRole",
      "password",
      "hadoop:service=NameNode,name=RpcActivityForPort9000" };
    state.setup(args);
    state.fetch();
    state.output();
  }
}
