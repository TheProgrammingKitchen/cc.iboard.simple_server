#!/usr/bin/env bash

JAVA="/Library/Java/JavaVirtualMachines/jdk-9.jdk/Contents/Home/bin/java"

OPTS="-Dfile.encoding=UTF-8"
CLASSPATH="/Users/aa/dev/IdeaProjects/cc.iboard.simple_server/out/test/cc.iboard/"

$JAVA -classpath $CLASSPATH $OPTS cc.iboard.BackendTest $*

