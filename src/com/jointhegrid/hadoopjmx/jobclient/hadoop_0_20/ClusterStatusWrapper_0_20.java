/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jointhegrid.hadoopjmx.jobclient.hadoop_0_20;

import com.jointhegrid.hadoopjmx.jobclient.ClusterStatusWrapper;
import java.lang.reflect.Method;

/**
 *
 * @author ecapriolo
 */
public class ClusterStatusWrapper_0_20 extends ClusterStatusWrapper {




@Override
   public int getTaskTrackers(){
    try {
      Method m = this.managedClusterStatus.getClass().getMethod("getTaskTrackers", new Class[]{} );
      Object retObj = m.invoke(this.managedClusterStatus, new Object[]{} );
      if (retObj instanceof Integer){
        return ((Integer) retObj).intValue();
      }
    } catch (Exception ex){
      throw new UnsupportedOperationException(ex);
    }
    return -77;
  }
@Override
  public int getBlacklistedTrackers(){
    try {
      Method m = this.managedClusterStatus.getClass().getMethod("getBlacklistedTrackers", new Class[]{} );
      Object retObj = m.invoke(this.managedClusterStatus, new Object[]{} );
      if (retObj instanceof Integer){
        return ((Integer) retObj).intValue();
      }
    } catch (Exception ex){
      throw new UnsupportedOperationException(ex);
    }
    return -77;
  }

  public void output(){
    System.out.print("mapTasks:"+this.getMapTasks()+" ");
    System.out.print("maxMapTasks:"+this.getMaxMapTasks()+" ");
    System.out.print("reduceTasks:"+this.getReduceTasks()+" ");
    System.out.print("maxReduceTasks:"+this.getMaxReduceTasks()+" ");
    System.out.print("blacklistedTrackers:"+this.getBlacklistedTrackers()+" ");
    System.out.print("taskTrackers:"+this.getTaskTrackers()+" ");
  }
}