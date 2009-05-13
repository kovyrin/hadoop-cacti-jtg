/*
* Copyright 2009 Edward Capriolo
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*   http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
 */
package com.jointhegrid.hadoopjmx;

import java.util.HashMap;
import java.util.Map;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 *
 * @author ecapriolo
 */
public class NameNodeStatistics {
  private String jmxURL;
  private String user;
  private String pass;

  private String objectName="hadoop.dfs:name=NameNodeStatistics,service=NameNode";
  private String [] wantedVariables = new String[] {
"BlockReportAverageTime",
"BlockReportMaxTime",
"BlockReportMinTime",
"BlockReportNum",
"JournalTransactionAverageTime",
"JournalTransactionNum",
"JournalTransactionMaxTime",
"JournalTransactionMinTime",
"JournalSyncAverageTime",
"JournalSyncMaxTime",
"JournalSyncMinTime",
"JournalSyncNum",
"SafemodeTime",
"FSImageLoadTime",
"NumFilesCreated",
"NumFilesListed",
"NumGetBlockLocations",
"NumFilesRenamed"

  };
  private String [] wantedOperations = new String[] { };

  public NameNodeStatistics(){}

  public static void main (String [] args){

    NameNodeStatistics nnc = new NameNodeStatistics();
    if (args.length!=3){
      System.err.println("Wrong number of arguments. ");
      return;
      //nnc.setJmxURL("service:jmx:rmi:///jndi/rmi://hadoop1:10001/jmxrmi");
    } else {
      nnc.setJmxURL(args[0]);
      nnc.setUser(args[1]);
      nnc.setPass(args[2]);
    }
    nnc.output();
  }

  public void output(){

    try {
      //connect
      JMXServiceURL jmxUrl = new JMXServiceURL(this.jmxURL);
      Map m = new HashMap();
      m.put(JMXConnector.CREDENTIALS,new String[] { this.user,this.pass});
      JMXConnector connector = JMXConnectorFactory.connect(jmxUrl,m);
      MBeanServerConnection connection = connector.getMBeanServerConnection();

      //locate object
      ObjectName on = new ObjectName(this.objectName);
      MBeanInfo info = connection.getMBeanInfo( on );

      for (String var: this.wantedVariables){
        Object attr2 = connection.getAttribute(on, var);
        System.out.print(var+":"+(attr2.toString())+" ");
      }

      // operations
      for (String op:wantedOperations){
        Object result= connection.invoke(on, op, new Object []{},new String []{});
        System.out.print(op+":"+result+" ");
      }

      //close
      connector.close();
    } catch (Exception ex){System.err.println(ex);}
  }
  public String getJmxURL() {
    return jmxURL;
  }

  public void setJmxURL(String jmxURL) {
    this.jmxURL = jmxURL;
  }

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

}
