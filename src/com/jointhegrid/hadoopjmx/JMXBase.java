/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jointhegrid.hadoopjmx;

import java.util.HashMap;
import java.util.Map;
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
public abstract class JMXBase {
  protected String jmxURL;
  protected String user;
  protected String pass;
  protected String objectName;
  protected String [] wantedVariables = null;
  protected String [] wantedOperations = null;
  
  public JMXBase(){
    
  }
  
  public void output(){
      try {
      //connect
      JMXServiceURL jmxUrl = new JMXServiceURL(this.jmxURL);
      Map m = new HashMap();
      m.put(JMXConnector.CREDENTIALS, new String[]{this.user, this.pass});
      JMXConnector connector = JMXConnectorFactory.connect(jmxUrl, m);
      MBeanServerConnection connection = connector.getMBeanServerConnection();

      //locate object
      ObjectName on = new ObjectName(this.objectName);
      MBeanInfo info = connection.getMBeanInfo(on);

      for (String var : this.wantedVariables) {
        Object attr2 = connection.getAttribute(on, var);
        System.out.print(var + ":" + (attr2.toString()) + " ");
      }

      // operations
      for (String op : wantedOperations) {
        Object result = connection.invoke(on, op, new Object[]{}, new String[]{});
        System.out.print(op + ":" + result + " ");
      }

      //close
      connector.close();
    } catch (Exception ex) {
      System.err.println(ex);
    }
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

  public String getObjectName() {
    return objectName;
  }

  public void setObjectName(String objectName) {
    this.objectName = objectName;
  }

}
