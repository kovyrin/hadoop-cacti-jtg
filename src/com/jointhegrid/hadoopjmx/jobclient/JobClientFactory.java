/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jointhegrid.hadoopjmx.jobclient;

import com.jointhegrid.hadoopjmx.jobclient.hadoop_0_19.JobClientWrapper_0_19;
import com.jointhegrid.hadoopjmx.jobclient.hadoop_0_20.JobClientWrapper_0_20;

/**
 *
 * @author ecapriolo
 */
public class JobClientFactory {

  public static JobClientWrapper getJobClientWrapper(String version, String[] configs) {
    if (version.equals("0_20")) {
      return new JobClientWrapper_0_20(configs);
    }
    if (version.equals("0_19")) {
      return new JobClientWrapper_0_19(configs);
    }
    throw new UnsupportedOperationException("Could not find matching version");
  }

  public static void main(String[] args) {
    String[] configs = null;
    if (args.length == 0) {
      System.err.println("wrong number of args " + " 0_19 confFile1 [conffile2...]");
      //System.exit(1);
    } else {
      configs = new String[args.length - 1];
      for (int i = 1; i < args.length; ++i) {
        configs[i - 1] = args[i];
      }
    }
    String[] confs = new String[]{"/home/ecapriolo/hadoop-0.20.0/conf/mapred-site.xml"};
    //JobClientWrapper wrap = JobClientFactory.getJobClientWrapper("0_20", confs);
    JobClientWrapper wrap = JobClientFactory.getJobClientWrapper(args[0], configs);
    //ClusterStatusWrapper csw = wrap.getClusterStatus();
    //csw.output();
    wrap.outputClusterStatus();
  }
}
