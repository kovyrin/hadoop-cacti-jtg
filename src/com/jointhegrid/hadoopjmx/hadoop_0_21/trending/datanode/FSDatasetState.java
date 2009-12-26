/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jointhegrid.hadoopjmx.hadoop_0_21.trending.datanode;

import com.jointhegrid.hadoopjmx.JMXBase;
import com.jointhegrid.hadoopjmx.JMXBaseException;
import java.io.IOException;
import java.util.Set;
import javax.management.ObjectName;

/**
 *
 * @author ecapriolo
 */
public class FSDatasetState extends JMXBase{
  public FSDatasetState(){
    super();
    this.wantedVariables = new String[] {
    "Capacity","DfsUsed","Remaining", "StorageInfo"
    };
    
  }
  public void fetch() throws JMXBaseException{
    if (connection == null){
      makeConnection();
    }
    //we need this because datanode on's have a ID like
    //hadoop:service=DataNode,name=DataNodeActivity-DS-1485738281-127.0.0.1-50010-1250648745469
    if (this.objectName.equalsIgnoreCase("AUTO")) {
      String dnbase = "hadoop:service=DataNode,name=FSDatasetState";
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
    FSDatasetState state = new FSDatasetState();
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
