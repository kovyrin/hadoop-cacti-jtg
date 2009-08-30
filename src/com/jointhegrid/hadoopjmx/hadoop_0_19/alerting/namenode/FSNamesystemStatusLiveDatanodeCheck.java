/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jointhegrid.hadoopjmx.hadoop_0_19.alerting.namenode;

import com.jointhegrid.hadoopjmx.hadoop_0_19.trending.namenode.FSNamesystemStatus;
import com.jointhegrid.hadoopjmx.JMXBaseException;

/**
 *
 * @author ecapriolo
 */
public class FSNamesystemStatusLiveDatanodeCheck extends FSNamesystemStatus {

  private long live;
  
  public FSNamesystemStatusLiveDatanodeCheck() {
    super();
  }

  public static void main(String[] args) throws JMXBaseException {
    FSNamesystemStatusLiveDatanodeCheck c = new FSNamesystemStatusLiveDatanodeCheck();

    if (args.length != 4) {
      System.err.println("Wrong number of arguments. jmxurl user pass livethresh ");
      return;
    }
    c.setJmxURL(args[0]);
    c.setUser(args[1]);
    c.setPass(args[2]);
    c.live = Long.parseLong(args[3]);
    
    c.fetch();
    
    long livenodes= ((Long) c.getWantedOperResultsRO().get("NumLiveDataNodes"));

    if ( livenodes < c.live ) {
      System.out.println("NumLiveDataNodes: FAILED threshold:"+c.live+" current:"+livenodes);
      System.exit(1);
    } else {
      System.out.println("NumLiveDataNodes: OK threshold:"+c.live+" current:"+livenodes);
      System.exit(0);
    }

  }
}
