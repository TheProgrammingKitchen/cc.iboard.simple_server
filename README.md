# cc.iboard – Simple Server Application

## Quick Start

### Eclipse

#### Run the tests

* Right click on path `test` in the project browser and choose `Run all tests`

#### Run the Server

* Right click on `Application` in `src` within the project browser and choose `Run`


### IntelliJ

* import project to IntelliJ
* run all tests

If you run `run.sh` and `test.sh` from within IntelliJ
change the working path to the project root.

Right click on `run.sh` and choose run usually starts the
script in directory `run` but the script is meant to run
from project root.


### Command line

#### Build/Compile

* run `./run/build.sh` from the project root path

TODO: Compiling the tests doesn't work at the moment.
Run the tests from Eclipse or Intellij first and then
run `run/test.sh` to execute tests from command-line.

#### Run the tests

* `./run/test.sh` to run all tests from command line

#### Run the server

* `./run/run.sh` and open `http://localhost:4000`

