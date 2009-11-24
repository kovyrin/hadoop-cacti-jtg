/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jointhegrid.hadoopjmx.jobclient;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 *
 * @author ecapriolo
 */
public abstract class JobClientWrapper {

  protected Object managedJobClient;
  protected String[] resources;

  public JobClientWrapper() {
  }

  public JobClientWrapper(String[] s) {
    this.resources = s;
  }

  public ClusterStatusWrapper getClusterStatus() {
    throw new UnsupportedOperationException();
  }

  public void setManagedJobClient(Object o) {
    managedJobClient = o;
  }

  public Object getManagedJobClient() {
    return managedJobClient;
  }

  public void outputClusterStatus() {
    this.getClusterStatus().output();
    System.out.print("jobsToCompleteSize:" + this.jobsToCompleteSize());
  }

  public int jobsToCompleteSize() {

    try {
      Method m = this.managedJobClient.getClass().getMethod("jobsToComplete", new Class[]{});
      Object retObj = m.invoke(this.managedJobClient, new Object[]{});
      if (retObj==null){
        return 0;
      }
      if (retObj.getClass().isArray()) {
        return java.lang.reflect.Array.getLength(retObj);
      }
    } catch (NullPointerException ex) {
      return -77;
    } catch (Exception ex) {
      throw new UnsupportedOperationException(ex);
    }
    return -77;

  }
}
