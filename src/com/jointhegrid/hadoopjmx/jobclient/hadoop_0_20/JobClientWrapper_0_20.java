/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jointhegrid.hadoopjmx.jobclient.hadoop_0_20;

import com.jointhegrid.hadoopjmx.jobclient.ClusterStatusWrapper;
import com.jointhegrid.hadoopjmx.jobclient.JobClientWrapper;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 *
 * @author ecapriolo
 */
public class JobClientWrapper_0_20 extends JobClientWrapper {

  protected Object managedConfiguration;
  protected Object managedJobConf;

  public JobClientWrapper_0_20(String[] s) {

    this.resources = s;
    //Configuration conf = new Configuration();

    try {
      managedConfiguration = Class.forName("org.apache.hadoop.conf.Configuration").newInstance();
    } catch (Exception ex) {
      throw new UnsupportedOperationException("Could not create configuration", ex);
    }

    //conf.addResource("/home/ecapriolo/hadoop-0.20.0/conf/hdfs-site.xml");
    try {
      for (String res : this.resources) {
        try {
          Method j = managedConfiguration.getClass().getMethod("addResource", new Class[]{String.class});
          j.invoke(managedConfiguration, new Object[]{res});
        } catch (Exception ex) {
          throw new UnsupportedOperationException(ex);
        }
      }
    } catch (Exception ex) {
      throw new UnsupportedOperationException("add resource", ex);
    }

    //JobConf job = new JobConf(conf);
    Constructor confCon = null;
    try {
      confCon = Class.forName("org.apache.hadoop.mapred.JobConf")
              .getConstructor(new Class[]{managedConfiguration.getClass()});
    } catch (Exception ex) {
      throw new UnsupportedOperationException("jobConfConstructor", ex);
    }

    try {
      managedJobConf = confCon.newInstance(new Object[]{managedConfiguration});
    } catch (Exception ex) {
      throw new UnsupportedOperationException("managedJobConf", ex);
    }

    Constructor jcCon = null;
    try {
      jcCon = Class.forName("org.apache.hadoop.mapred.JobClient")
            .getConstructor(new Class[]{ managedJobConf.getClass() } );

    }catch (Exception ex) {
      throw new UnsupportedOperationException("jobClient", ex);
    }

    try {
      this.managedJobClient = jcCon.newInstance(new Object [] {this.managedJobConf});
    } catch (Exception ex) {
      throw new UnsupportedOperationException("jobClient", ex);
    }
    
  }

  @Override
  public ClusterStatusWrapper getClusterStatus() {
    try {
      Method m = this.getManagedJobClient().getClass().getMethod("getClusterStatus", new Class[]{});
      Object retObj = m.invoke(this.managedJobClient, new Object[]{});
      ClusterStatusWrapper csw = new ClusterStatusWrapper_0_20();
      csw.setManagedClusterStatus(retObj);
      return csw;
    } catch (Exception ex) {
      throw new UnsupportedOperationException(ex);
    }
  }
}
