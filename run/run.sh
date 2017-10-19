#!/usr/bin/env bash

if test -z ${JAVA}
then
  JAVA="/usr/bin/java"
else
  JAVA=${JAVA}
fi

OPTS="-Dfile.encoding=UTF-8"
CLASSPATH="./:./out/production/cc.iboard.simple_server"

$JAVA -classpath $CLASSPATH $OPTS cc.iboard.Application

