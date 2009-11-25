/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jointhegrid.hadoopjmx.jobclient;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.HashMap;


/**
 *
 * @author ecapriolo
 */
public abstract class JobClientWrapper {

  /** This wil be an Instance of JobClient**/
  protected Object managedJobClient;
  /** a list of resources to be added from Classpath. Not a full URI!**/
  protected String[] resources;
  /** Polling interval. Default is 300 seconds */
  protected int pollingInterval=300;

  public JobClientWrapper() {
  }

  public JobClientWrapper(String[] s) {
    this.resources = s;
  }

  /*
   * Use this constructor if your NMS does not 
   * do 5 minute polling
   */
  public JobClientWrapper(String[] s, int interval) {
    this.resources = s;
    this.pollingInterval=interval;
  }

  public int getPollingInterval() {
    return pollingInterval;
  }

  public void setPollingInterval(int pollingInterval) {
    this.pollingInterval = pollingInterval;
  }

  public String[] getResources() {
    return resources;
  }

  public void setResources(String[] resources) {
    this.resources = resources;
  }

  public void setManagedJobClient(Object o) {
    managedJobClient = o;
  }

  public Object getManagedJobClient() {
    return managedJobClient;
  }

  public ClusterStatusWrapper getClusterStatus() {
    throw new UnsupportedOperationException();
  }

  /** jobsToComplete identical in 17-20 */
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
  
  public HashMap<String,Integer> jobsStatusForInterval(int secondsAgo){
    //KILLED,FAILED,SUCCEEDED
    long end = System.currentTimeMillis();
    long start = end-( ((long)secondsAgo)*1000L);
    HashMap<String,Integer> results = new HashMap<String,Integer>();
    try {
      Method m = this.managedJobClient.getClass().getMethod("getAllJobs", new Class[]{});
      Object arrayOfJobStatus = m.invoke(this.managedJobClient, new Object[]{});
      //could be null if no jobs yet submitted
      if (arrayOfJobStatus == null){
        results.put("KILLED", 0);
        results.put("SUCCEEDED", 0);
        results.put("FAILED", 0);
        results.put("PREP", 0);
        results.put("RUNNING", 0);
        return results;
      }

      int killedCount=0;
      int succCount=0;
      int failedCount=0;
      int prepCount=0;
      int runnCount=0;

      for (int i =0;i < Array.getLength(arrayOfJobStatus);i++){
        Object jobStatusObj = Array.get(arrayOfJobStatus, i);

        Integer killedConst = jobStatusObj.getClass().getField("KILLED").getInt(jobStatusObj);
        Integer succConst = jobStatusObj.getClass().getField("SUCCEEDED").getInt(jobStatusObj);
        Integer failedConst = jobStatusObj.getClass().getField("FAILED").getInt(jobStatusObj);
        Integer prepConst = jobStatusObj.getClass().getField("PREP").getInt(jobStatusObj);
        Integer runnConst = jobStatusObj.getClass().getField("RUNNING").getInt(jobStatusObj);

        Method startTime = jobStatusObj.getClass().getMethod("getStartTime", new Class[]{});
        Long longStartTime = (Long) startTime.invoke(jobStatusObj, new Object[]{});

        //startTime will be 0 if unstarted
        if ((longStartTime >=start && longStartTime < end) || longStartTime==0){
          Method getRunState = jobStatusObj.getClass().getMethod("getRunState", new Class[]{});
          Integer intRunState = (Integer) getRunState.invoke(jobStatusObj, new Object[]{});
          if (intRunState.intValue()==killedConst.intValue())
            killedCount++;
          if (intRunState.intValue()==succConst.intValue())
            succCount++;
          if (intRunState.intValue()==failedConst.intValue())
            failedCount++;
          if (intRunState.intValue()==prepConst.intValue())
            prepCount++;
          if (intRunState.intValue()==runnConst.intValue())
            runnCount++;
        }

        results.put("KILLED", killedCount);
        results.put("SUCCEEDED", succCount);
        results.put("FAILED", failedCount);
        results.put("PREP", prepCount);
        results.put("RUNNING", runnCount);

      }
    } catch (Exception ex){
      throw new UnsupportedOperationException(ex);
    }
    return results;
  }

  public void outputClusterStatus() {
    this.getClusterStatus().output();
    System.out.print("jobsToCompleteSize:" + this.jobsToCompleteSize()+" ");
    HashMap<String,Integer> status = this.jobsStatusForInterval(300);
    for (String key: status.keySet()){
      System.out.print(key+":"+status.get(key)+" ");
    }
  }
}