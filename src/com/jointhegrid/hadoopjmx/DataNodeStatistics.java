/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jointhegrid.hadoopjmx;

/**
 *
 * @author ecapriolo
 */
public class DataNodeStatistics extends JMXBase {
  
  //private String objectName="hadoop.dfs:name=DataNode-DS-689833854-10.12.9.12-50010-1239640622251,service=DataNode";
  
  public DataNodeStatistics(){
    wantedOperations = new String [] {};
    wantedVariables = new String[] {

"BytesRead","BytesWritten","BlocksRead","BlocksRemoved",
"BlocksReplicated","BlocksWritten","BlockVerificationFailures", "BlocksVerified",
"ReadsFromLocalClient","ReadsFromRemoteClient", "WritesFromLocalClient", "WritesFromRemoteClient",
"ReadBlockOpAverageTime", "ReadBlockOpMaxTime", "ReadBlockOpMinTime", "ReadBlockOpNum",
"ReadMetadataOpAverageTime","ReadMetadataOpMaxTime", "ReadMetadataOpMinTime", "ReadMetadataOpNum",
"ReplaceBlockOpAverageTime", "ReplaceBlockOpMaxTime", "ReplaceBlockOpMinTime","ReplaceBlockOpNum",
"WriteBlockOpAverageTime", "WriteBlockOpMaxTime", "WriteBlockOpMinTime", "WriteBlockOpNum",
"CopyBlockOpAverageTime", "CopyBlockOpMaxTime", "CopyBlockOpMinTime", "CopyBlockOpNum",
"BlockReportsAverageTime","BlockReportsMaxTime","BlockReportsMinTime", "BlockReportsNum",
"HeartbeatsAverageTime", "HeartbeatsMaxTime", "HeartbeatsMinTime", "HeartbeatsNum"

    };
  }

  public static void main(String [] args){
    DataNodeStatistics dns = new DataNodeStatistics();
    if (args.length != 4) {
      System.err.println("Wrong number of arguments. ");
      return;
    } else {
      dns.setJmxURL(args[0]);
      dns.setUser(args[1]);
      dns.setPass(args[2]);
      dns.setObjectName(args[3]);
    }
    dns.output();
  }
  
}