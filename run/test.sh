#!/usr/bin/env bash

if test -z ${JAVA}
then
  JAVA="/usr/bin/java"
else
  JAVA=${JAVA}
fi

PROJECT="./out/production/cc.iboard.simple_server:./out/test/cc.iboard.simple_server"

CLASSPATH="./:$PROJECT"

# run all tests
$JAVA -jar ./lib/junit-platform-console-standalone-1.0.0-M5.jar \
	--class-path ${CLASSPATH} \
	--scan-class-path