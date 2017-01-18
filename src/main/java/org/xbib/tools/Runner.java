/*
 * Copyright (C) 2015 Jörg Prante
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.xbib.tools;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {

    private final static Logger logger = LogManager.getLogger("importer.jdbc");
	
	private static Runner instance = null;
    
	private Runner() {}
   
	public static Runner getInstance() {
	   if(instance == null) {
			instance = new Runner();
		}
		return instance;
	}
   
	/**
	 * Index data into ES instance
	 * @param query (string) - ES JSON query
	 */
	public void indexData(String query) {
	   try {
          JDBCImporter jdbcImporter = new JDBCImporter();
          InputStream in = new ByteArrayInputStream(query.getBytes(Charset.defaultCharset()));
          jdbcImporter.run("args.json", in);
          in.close();
       } catch (Throwable e) {
    	   logger.error("Error in Runner: ", e);
           System.exit(1);
       }
       //System.exit(0);
	}
   
}