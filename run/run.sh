#!/usr/bin/env bash


#
# This file must be started in project's root directory
# not inside `./run/` !
#

if test -z ${JAVA}
then
  JAVA="/usr/bin/java"
else
  JAVA=${JAVA}
fi

OPTS="-Dfile.encoding=UTF-8"
CLASSPATH="./:./out/production/cc.iboard.simple_server"

$JAVA -classpath $CLASSPATH $OPTS cc.iboard.Application

