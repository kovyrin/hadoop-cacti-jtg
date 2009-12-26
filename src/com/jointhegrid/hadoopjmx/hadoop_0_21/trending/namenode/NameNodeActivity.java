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
public class NameNodeActivity extends JMXBase {
  public NameNodeActivity(){
    this.wantedVariables = new String [] {
    "AddBlockOps", 
    "BlocksCorrupted",
    "CreateFileOps",
    "DeleteFileOps" , 
    "FilesAppended" ,
    "FilesCreated",
    "FilesRenamed" , 
    "GetBlockLocations" ,
    "GetListingOps" ,
    "JournalTransactionsBatchedInSync",
    "SafemodeTime", 
    "fsImageLoadTime",
    "SyncsAvgTime",
    "SyncsMaxTime",
    "SyncsMinTime",
    "SyncsNumOps",
    "TransactionsAvgTime",
    "TransactionsMaxTime",
    "TransactionsMinTime",
    "TransactionsNumOps",
    "blockReportAvgTime",
    "blockReportMaxTime",
    "blockReportMinTime",
    "blockReportNumOps"
    };
  }

  public static void main (String [] args) throws JMXBaseException {
    NameNodeActivity state = new NameNodeActivity();
    String [] testargs = new String [] {
      "service:jmx:rmi:///jndi/rmi://localhost:10001/jmxrmi" ,
      "controlRole",
      "password",
      "hadoop:service=NameNode,name=NameNodeActivity" };
    state.setup(args);
    state.fetch();
    state.output();

  }
}
