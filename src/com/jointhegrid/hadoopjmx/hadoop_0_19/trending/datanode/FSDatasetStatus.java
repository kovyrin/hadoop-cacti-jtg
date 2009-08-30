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

package com.jointhegrid.hadoopjmx.hadoop_0_19.trending.datanode;

import com.jointhegrid.hadoopjmx.*;
import java.io.IOException;
import java.util.Set;
import javax.management.ObjectName;

/**
 *
 * @author ecapriolo
 */
public class FSDatasetStatus extends JMXBase{

  public FSDatasetStatus(){
    wantedVariables = new String[] {
    "Remaining",
    "Capacity",
    "DfsUsed" };
    wantedOperations = new String [] {};
  }

  public static void main(String[] args) throws JMXBaseException{

    FSDatasetStatus fsd = new FSDatasetStatus();
    fsd.setup(args);
    fsd.fetch();
    fsd.output();
  }

  public void fetch() throws JMXBaseException{
    if (connection == null){
      makeConnection();
    }
    //we need this because datanode on's have a ID like
    //hadoop:service=DataNode,name=DataNodeActivity-DS-1485738281-127.0.0.1-50010-1250648745469
    if (this.objectName.equalsIgnoreCase("AUTO")) {
      String dnbase = "hadoop:service=DataNode,name=FSDatasetStatus";
      Set<ObjectName> names = null;
      try {
        names = connection.queryNames(null, null);
      } catch (IOException ex){ throw new JMXBaseException(ex); }
      for (ObjectName on:names ){
        if (on.toString().indexOf(dnbase)> -1 ){
          this.setObjectName(on.toString());
        }
      }
    }
    super.fetch();
  }
}