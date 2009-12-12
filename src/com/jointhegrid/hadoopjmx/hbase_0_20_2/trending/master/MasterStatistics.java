/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jointhegrid.hadoopjmx.hbase_0_20_2.trending.master;

import com.jointhegrid.hadoopjmx.JMXBase;
import com.jointhegrid.hadoopjmx.JMXBaseException;

/**
 *
 * @author ecapriolo
 */
public class MasterStatistics extends JMXBase {

  public MasterStatistics(){
    super();
    this.objectName = "hadoop:service=Master,name=MasterStatistics";
    this.wantedVariables = new String [] { "cluster_requests" };
  }

  public static void main (String [] args) throws JMXBaseException{
    MasterStatistics nnc = new MasterStatistics();
    nnc.setup(args);
    nnc.fetch();
    nnc.output();
  }
}
