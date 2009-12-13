/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jointhegrid.hadoopjmx.hbase_0_20_2.trending.regionserver;

import com.jointhegrid.hadoopjmx.JMXBase;
import com.jointhegrid.hadoopjmx.JMXBaseException;

/**
 *
 * @author ecapriolo
 */
public class RegionServerStatistics extends JMXBase{

  public RegionServerStatistics(){
    super();
    this.objectName = "hadoop:service=RegionServer,name=RegionServerStatistics";
    this.wantedVariables = new String [] {
    "storefiles",
    "blockCacheFree",
    "storefileIndexSizeMB",
    "memstoreSizeMB",
    "stores",
    "blockCacheCount",
    "regions",
    "blockCacheHitRatio",
    "blockCacheSize",
    "atomicIncrementTimeNumOps",
    "atomicIncrementTimeAvgTime",
    "atomicIncrementTimeMinTime",
    "atomicIncrementTimeMaxTime"
    };
  }

  public static void main (String [] args) throws JMXBaseException{
    RegionServerStatistics nnc = new RegionServerStatistics();
    nnc.setup(args);
    nnc.fetch();
    nnc.output();
  }
}
