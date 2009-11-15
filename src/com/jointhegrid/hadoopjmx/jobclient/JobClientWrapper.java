/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jointhegrid.hadoopjmx.jobclient;

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

  public void outputClusterStatus(){
    this.getClusterStatus().output();
  }
  
}
