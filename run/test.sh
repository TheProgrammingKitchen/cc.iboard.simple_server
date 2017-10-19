#!/usr/bin/env bash

JAVA="/Library/Java/JavaVirtualMachines/jdk-9.jdk/Contents/Home/bin/java"
PROJECT="./out/production/cc.iboard.simple_server:./out/test/cc.iboard.simple_server"

CLASSPATH="./:$PROJECT"

# run all tests
$JAVA -jar ./lib/junit-platform-console-standalone-1.0.0-M5.jar \
	--class-path ${CLASSPATH} \
	--scan-class-path