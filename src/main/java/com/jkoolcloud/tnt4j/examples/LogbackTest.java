/*
 * Copyright 2014-2023 JKOOL, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jkoolcloud.tnt4j.examples;

import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import ch.qos.logback.classic.Logger;

/**
 * Simple app producing log entries having defined properties.
 * 
 * @version $Revision: 1 $
 */
public class LogbackTest {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(LogbackTest.class);

	public static void main(String[] args) {
		MDC.put("app", LogbackTest.class.getName());
		logger.info("Starting a TNT4J activity #beg=Test");
		logger.warn("First log message #app=" + LogbackTest.class.getName() + " #msg='1 Test warning message'");
		logger.error("Second log message #app=" + LogbackTest.class.getName() + " #msg='2 Test error message'",
				new Exception("test exception"));
		logger.info("Ending a TNT4J activity #end= #app=" + LogbackTest.class.getName());

		logger.debug("First datagram message #app=" + LogbackTest.class.getName() + " #msg='Test datagram message'");
		logger.trace("Second datagram message #app=" + LogbackTest.class.getName() + " #msg='Test datagram message'");
		logger.trace("Whole datagram message #rcd=" + 37128 + " #rsn=" + logger.getName());
	}
}
