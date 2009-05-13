/*
* Copyright 2009 Edward Capriolo
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*   http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
 */
package com.jointhegrid.hadoopjmx;
/**
 *
 * @author ecapriolo
 */
public class RpcStatisticsForPort  extends JMXBase {
  
  public RpcStatisticsForPort() {
    wantedVariables = new String[]{
              "NumOpenConnections",
              "CallQueueLen",
              "RpcOpsAvgProcessingTime",
              "RpcOpsAvgProcessingTimeMin",
              "RpcOpsAvgProcessingTimeMax",
              "RpcOpsAvgQueueTime",
              "RpcOpsAvgQueueTimeMin",
              "RpcOpsAvgQueueTimeMax",
              "RpcOpsNumber"            
            };
    wantedOperations = new String[]{};
  }

  public static void main (String [] args){

    RpcStatisticsForPort nnc = new RpcStatisticsForPort();
    if (args.length!=4){
      System.err.println("Wrong number of arguments. ");
      return;
      // nnc.setObjectName("name=RpcStatisticsForPort54310,service=NameNode");
    } else {
      nnc.setJmxURL(args[0]);
      nnc.setUser(args[1]);
      nnc.setPass(args[2]);
      nnc.setObjectName(args[3]);
    }
    nnc.output();
  }
}