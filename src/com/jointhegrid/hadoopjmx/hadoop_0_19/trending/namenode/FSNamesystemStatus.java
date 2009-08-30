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
package com.jointhegrid.hadoopjmx.hadoop_0_19.trending.namenode;

import com.jointhegrid.hadoopjmx.*;

/**
 *
 * @author ecapriolo
 */
public class FSNamesystemStatus extends JMXBase{

  public FSNamesystemStatus() {
    objectName = "hadoop.dfs:name=FSNamesystemStatus,service=NameNode";
    wantedVariables = new String[]{"CapacityTotal", "CapacityUsed", "CapacityRemaining", "BlocksTotal", "FilesTotal"};
    wantedOperations = new String[]{"numDeadDataNodes", "numLiveDataNodes"};
  }

  public static void main(String[] args) throws JMXBaseException {
    FSNamesystemStatus nnc = new FSNamesystemStatus();
    nnc.setup(args);
    nnc.fetch();
    nnc.output();
  }

}