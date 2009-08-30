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

import com.jointhegrid.hadoopjmx.JMXBase;
import com.jointhegrid.hadoopjmx.JMXBaseException;

/**
 *
 * @author ecapriolo
 */
public class NameNodeStatistics extends JMXBase{
  
  public NameNodeStatistics() {
    super();
    objectName = "hadoop.dfs:name=NameNodeStatistics,service=NameNode";
    wantedVariables = new String[]{
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
    wantedOperations = new String[]{};

  }

  public static void main (String [] args) throws JMXBaseException{

    NameNodeStatistics nnc = new NameNodeStatistics();
    nnc.setup(args);
    nnc.fetch();
    nnc.output();
  }

}