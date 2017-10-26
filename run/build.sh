#!/usr/bin/env bash

#
# This file must be started in project's root directory
# not inside `./run/` !
#

function compile {
  echo "COMPILING `basename $1` --> `basename $2` -cp $3 $4"
  cd $1
  FILES=`find . -name "*java"`
  javac -cp $3 -d $2 $4 ${FILES}
}

if test -z ${JAVA}
then
  JAVA="/usr/bin/java"
else
  JAVA=${JAVA}
fi

CURRENT=`pwd`

WORKDIR="${CURRENT}/out/production/cc.iboard.simple_server"
TESTOUTPUT="${CURRENT}/out/tests/cc.iboard.simple_server"
SRCPATH="${CURRENT}/src"
TESTPATH="${CURRENT}/tests"
NAME="Server.jar"
LIBPATH="${CURRENT}/lib"
REL_PATH="${CURRENT}/run"

echo "COMPILE ..."
mkdir -p ${WORKDIR}
compile ${SRCPATH} ${WORKDIR} .

echo ""
echo "COMPILE TESTS ..."
mkdir -p ${TESTOUTPUT}
compile ${TESTPATH} ${TESTOUTPUT} ${WORKDIR}:${TESTPATH}:${SRCPATH}:${LIBPATH} "--add-modules ALL-MODULE-PATH"

echo ""
echo "BUILD JAR ..."
cd ${WORKDIR} && jar cvfm ${REL_PATH}/Server.jar ${REL_PATH}/manifest.txt ./**/* > /dev/null


cd ${CURRENT}
echo ""
echo "DONE"
pwd
echo "Now you can start the server with"
echo "java -jar run/Server.jar"

