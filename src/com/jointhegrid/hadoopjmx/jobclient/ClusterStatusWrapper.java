/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jointhegrid.hadoopjmx.jobclient;

import java.lang.reflect.Method;

/**
 *
 * @author ecapriolo
 */
public abstract class ClusterStatusWrapper {
  protected Object managedClusterStatus;
  public int getMaxReduceTasks(){
    try {
      Method m = this.managedClusterStatus.getClass().getMethod("getMaxReduceTasks", new Class[]{} );
      Object retObj = m.invoke(this.managedClusterStatus, new Object[]{} );
      if (retObj instanceof Integer){
        return ((Integer) retObj).intValue();
      }
    } catch (Exception ex){
      throw new UnsupportedOperationException(ex);
    }
    return -77;
  }
  public int getMaxMapTasks() {
    try {
      Method m = this.managedClusterStatus.getClass().getMethod("getMaxMapTasks", new Class[]{} );
      Object retObj = m.invoke(this.managedClusterStatus, new Object[]{} );
      if (retObj instanceof Integer){
        return ((Integer) retObj).intValue();
      }
    } catch (Exception ex){
      throw new UnsupportedOperationException(ex);
    }
    return -77;
  }

  public int getMapTasks(){
      try {
      Method m = this.managedClusterStatus.getClass().getMethod("getMapTasks", new Class[]{} );
      Object retObj = m.invoke(this.managedClusterStatus, new Object[]{} );
      if (retObj instanceof Integer){
        return ((Integer) retObj).intValue();
      }
    } catch (Exception ex){
      throw new UnsupportedOperationException(ex);
    }
    return -77;
  }
  public int getReduceTasks(){
    try {
      Method m = this.managedClusterStatus.getClass().getMethod("getReduceTasks", new Class[]{} );
      Object retObj = m.invoke(this.managedClusterStatus, new Object[]{} );
      if (retObj instanceof Integer){
        return ((Integer) retObj).intValue();
      }
    } catch (Exception ex){
      throw new UnsupportedOperationException(ex);
    }
    return -77;
  }
  public void setManagedClusterStatus(Object o){
    managedClusterStatus=o;
  }
  public Object getManagedClusterStatus(){
    return managedClusterStatus;
  }

  public int getTaskTrackers(){
    throw new UnsupportedOperationException() ;
  }

  public int getBlacklistedTrackers(){
    throw new UnsupportedOperationException() ;
  }

  public abstract void output();
}
