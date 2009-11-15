/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jointhegrid.hadoopjmx.jobclient.hadoop_0_19;

import com.jointhegrid.hadoopjmx.jobclient.ClusterStatusWrapper;

/**
 *
 * @author ecapriolo
 */
public class ClusterStatusWrapper_0_19 extends ClusterStatusWrapper {

    public void output(){
    System.out.print("mapTasks:"+this.getMapTasks()+" ");
    System.out.print("maxMapTasks:"+this.getMaxMapTasks()+" ");
    System.out.print("reduceTasks:"+this.getReduceTasks()+" ");
    System.out.print("maxReduceTasks:"+this.getMaxReduceTasks()+" ");
  }
    
}
