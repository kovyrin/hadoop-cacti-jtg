/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jointhegrid.hadoopjmx.hadoop_0_20.trending.datanode;

import com.jointhegrid.hadoopjmx.JMXBase;
import com.jointhegrid.hadoopjmx.JMXBaseException;
import java.io.IOException;
import java.util.Set;
import javax.management.ObjectName;

/**
 *
 * @author ecapriolo
 */
public class DataNodeActivity extends JMXBase {
  public DataNodeActivity(){
    super();
    this.wantedVariables = new String [] { 
    "bytes_written", "bytes_read",
    "block_verification_failures",
    "blocks_verified",
    "blocks_read",
    "blocks_written",
    "blocks_replicated",
    "blocks_removed",

    "blockChecksumOpAvgTime","blockChecksumOpMaxTime",
    "blockChecksumOpMinTime","blockChecksumOpNumOps",

    "blockReportOpAvgTime","blockReportOpMaxTime",
    "blockReportOpMinTime","blockReportOpNumOps",

    "copyBlockOpAvgTime","copyBlockOpMaxTime",
    "copyBlockOpMinTime","copyBlockOpNumOps",
    "heartBeatsAvgTime","heartBeatsMaxTime",
    "heartBeatsMinTime","heartBeatsNumOps",
    "readBlockOpAvgTime","readBlockOpMaxTime",
    "readBlockOpMinTime","readBlockOpNumOps",
    "readMetadataOpAvgTime","readMetadataOpMaxTime",
    "readMetadataOpMinTime","readMetadataOpNumOps",
    "reads_from_local_client","reads_from_remote_client",
    "replaceBlockOpAvgTime","replaceBlockOpMaxTime",
    "replaceBlockOpMinTime","replaceBlockOpNumOps",
    "writeBlockOpAvgTime","writeBlockOpMaxTime",
    "writeBlockOpMinTime","writeBlockOpNumOps",
    "writes_from_local_client","writes_from_remote_client"
    };
    //hadoop:service=DataNode,name=DataNodeActivity
  }
  public void fetch() throws JMXBaseException{
    if (connection == null){
      makeConnection();
    }
    //we need this because datanode on's have a ID like
    //hadoop:service=DataNode,name=DataNodeActivity-DS-1485738281-127.0.0.1-50010-1250648745469
    if (this.objectName.equalsIgnoreCase("AUTO")) {
      String dnbase = "hadoop:service=DataNode,name=DataNodeActivity";
      Set<ObjectName> names = null;
      try {
        names = connection.queryNames(null, null);
      } catch (IOException ex){ throw new JMXBaseException(ex); }
      for (ObjectName on:names ){
        if (on.toString().indexOf(dnbase)> -1 ){
          this.setObjectName(on.toString());
        }
      }
    }
    super.fetch();
  }
  public static void main (String [] args) throws JMXBaseException{
    DataNodeActivity state = new DataNodeActivity();
      String [] testargs = new String [] {
      "service:jmx:rmi:///jndi/rmi://localhost:10003/jmxrmi" ,
        "controlRole",
      "password",
      "AUTO" };
    state.setup(args);
    state.fetch();
    state.output();
  }
}
