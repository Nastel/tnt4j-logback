# tnt4j-logback

Logback Appender for TNT4J

### Logback Appender

All Logback messages can be routed to TNT4J event sinks via `com.jkoolcloud.tnt4j.logger.logback.TNT4JAppender`, which allows developers to
send event messages to TNT4J.

Developers may also enrich event messages and pass context to TNT4J using hashtag enrichment scheme. Hashtags are used to decorate event
messages with important metadata about each log message. This metadata is used to generate TNT4J tracking events:

```java
logger.info("Starting a TNT4J activity #beg=Test");
logger.warn("First log message #app=" + LogbackTest.class.getName() + " #msg='1 Test warning message'");
logger.error("Second log message #app=" + LogbackTest.class.getName() + " #msg='2 Test error message'", new Exception("test exception"));
logger.info("Ending a TNT4J activity #end= #app=" + LogbackTest.class.getName());
```

Above example groups messages between first and last into a related logical collection called `Activity`. Activity is a collection of
logically related events/messages. Hashtags `#beg`, `#end` are used to demarcate activity boundaries. This method also supports nested
activities.

User defined fields can be reported using `#[data-type][:value-type]/your-metric-name=your-value` convention (e.g. `#%i/order-no=62627`
or `#%d:currency/amount=50.45`).
`TNT4JAppender` supports the following optional `data-type` qualifiers:

```
	%i/ -- integer
	%l/ -- long
	%d/ -- double
	%f/ -- float
	%b/ -- boolean
	%n/ -- number
	%s/ -- string
```

All `value-type` qualifiers are defined in `com.jkoolcloud.tnt4j.core.ValueTypes`. Examples:

```
	currency 	-- generic currency
	flag 		-- boolean flag
	age 		-- age in time units
	guid 		-- globally unique identifier
	guage		-- numeric gauge
	counter		-- numeric counter
	percent		-- percent
	timestamp	-- timestamp
	addr 		-- generic address
```

Not specifying a qualifier defaults to auto-detection of type by `TNT4JAppender`. First `number` qualifier is tested and defaults
to `string` if the test fails (e.g. `#order-no=62627`). User defined fields are reported as a TNT4J snapshot with `Logback` category and
snapshot name set to activity name set by `#beg`, `#end`, `#opn` tags.

Below are Logback appender configuration attributes with defaults:

```
appender.class=com.jkoolcloud.tnt4j.logger.logback.TNT4JAppender
SourceName=com.logback
SourceType=APPL
MetricsOnException=true
MetricsFrequency=60
```

TNT4J Command line options
===============================================

**Command line arguments:**

* `-Dtnt4j.config=config/tnt4j.properties` -- TNT4J configuration used by Logback appender
* `-Dtnt4j.dump.on.vm.shutdown=true` java property allows application state dumps generated automatically upon VM shutdown.
* `-Dtnt4j.dump.provider.default=true` java property registers all default dump providers (memory, stack, logging stats).
* `-Dtnt4j.formatter.json.newline=true` java property directs `JSONFormatter` to append new line when formatting log entries.

See `<timestamp>.log` and `<vmid>.dump` file for output produced by running your applications with this appender.

See `config/tnt4j.properties` for TNT4J configuration: factories, formatters, listeners, etc.

How to Build tnt4j-logback
=========================================
Requirements

* JDK 1.8+

`tnt4j-logback` depends on the following external packages:

* [TNT4J-API](http://nastel.github.io/TNT4J/)
* [Logback Project](http://logback.qos.ch/)

Please use JCenter or Maven and these dependencies will be downloaded automatically.

`tnt4j-logback` requires TNT4J. You will therefore need to point TNT4J to it's property file via the `-Dtnt4j.config` argument. This 
property file is located here in GitHub under the /config directory. If using JCenter or Maven, it can be found in the zip assembly along with the 
source code and javadoc.
