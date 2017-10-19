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

CLASSPATH="\
./out/production/cc.iboard.simple_server:\
./out/test/cc.iboard.simple_server\
"

# run all tests

$JAVA -jar ./lib/junit-platform-console-standalone-1.0.0-M5.jar \
	--class-path ${CLASSPATH} \
	--scan-class-path