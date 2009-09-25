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
package com.jointhegrid.jmxutil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.QueryExp;
import javax.management.ReflectionException;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JWalker {

  private String url;
  private String user;
  private String pass;

  public JWalker() {
  }

  public static void main(String[] args) {
    JWalker j = new JWalker();
    String [] testargs = new String [] {
      "service:jmx:rmi:///jndi/rmi://localhost:10001/jmxrmi" ,
      "controlRole",
      "password",
       };
    j.url = args[0];
    j.user = args[1];
    j.pass = args[2];
    j.walk();
  }

  public void walk() {

    JMXServiceURL jmxUrl = null;
    try {
      jmxUrl = new JMXServiceURL(url);
    } catch (MalformedURLException ex) {
      System.out.println("Bad JMX URL." + ex.toString());
      System.exit(1);
    }
    Map m = new HashMap();
    m.put(JMXConnector.CREDENTIALS, new String[]{user, pass});
    JMXConnector connector = null;
    MBeanServerConnection connection = null;
    try {
      connector = JMXConnectorFactory.connect(jmxUrl, m);
      connection = connector.getMBeanServerConnection();

      System.out.println("JMX Domains");
      for (String domain : connection.getDomains()) {
        System.out.println("domain " + domain);
      }

        Set<ObjectName> names = connection.queryNames(null, null);

        for (ObjectName on : names) {
          System.out.println("object name " + on.getCanonicalName());
          MBeanInfo info = connection.getMBeanInfo(on);
          for (MBeanAttributeInfo mbi : info.getAttributes()) {
            System.out.println("--mbi name " + mbi.getName());
            System.out.println("--mbi type " + mbi.getClass());
            try {
              Object attr2 = connection.getAttribute(on, mbi.getName());
              if (attr2 instanceof CompositeDataSupport) {
                CompositeDataSupport cds2 = (CompositeDataSupport) attr2;
                Set<String> keys = cds2.getCompositeType().keySet();
                for (String key : keys) {
                  System.out.println("--mbi value " + key + " " + cds2.get(key));
                }
              } else {
                if (attr2.getClass().isArray()) {
                  Object[] a = (Object[]) attr2;
                  for (int i = 0; i < a.length; i++) {
                    System.out.println("--mbi value[" + i + "] " + a[i]);
                  }
                } else {
                  System.out.println("--mbi value " + attr2.toString());
                }
              }
            } catch (Exception ex) {
              System.err.println("problem with " + mbi.getName());
            }
          }

        


      }
      connector.close();
    } catch (IOException ex) {
      System.out.println("Connection problem " + ex.toString());
      System.exit(2);
    } catch (InstanceNotFoundException ex) {
      System.out.println("InstanceNotFound " + ex.toString());
      System.exit(2);
    } catch (IntrospectionException ex) {
      System.out.println("InstanceNotFound " + ex.toString());
      System.exit(2);
    } catch (ReflectionException ex) {
      System.out.println("ReflectionException " + ex.toString());
      System.exit(2);
    }
  }
}
