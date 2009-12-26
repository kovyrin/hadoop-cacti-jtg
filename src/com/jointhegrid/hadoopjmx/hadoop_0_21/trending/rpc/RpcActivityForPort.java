/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jointhegrid.hadoopjmx.hadoop_0_21.trending.rpc;

import com.jointhegrid.hadoopjmx.hadoop_0_21.trending.rpc.*;
import com.jointhegrid.hadoopjmx.JMXBase;
import com.jointhegrid.hadoopjmx.JMXBaseException;

/**
 *
 * @author ecapriolo
 */
public class RpcActivityForPort extends JMXBase {
  public RpcActivityForPort(){
    this.wantedVariables = new String [] {
    "NumOpenConnections",
    "RpcProcessingTimeMaxTime", "RpcProcessingTimeAvgTime",
    "RpcProcessingTimeMinTime", "RpcProcessingTimeNumOps" ,
    "RpcQueueTimeAvgTime", "RpcQueueTimeMaxTime",
    "RpcQueueTimeMinTime" , "RpcQueueTimeNumOps",
    "callQueueLen"
    };
  }

  public static void main (String [] args) throws JMXBaseException {
    RpcActivityForPort state = new RpcActivityForPort();
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
