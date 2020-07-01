#!/bin/bash


function set_svc_home {
  local SOURCE="${BASH_SOURCE[0]}"
  while [[ -h "$SOURCE" ]]; do # resolve $SOURCE until the file is no longer a symlink
    SVC_HOME="$( cd -P "$( dirname "$SOURCE" )" >/dev/null 2>&1 && pwd )"
    SOURCE="$(readlink "$SOURCE")"
    [[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE" # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
  done
  SVC_HOME="$( cd -P "$( dirname "$SOURCE" )" >/dev/null 2>&1 && pwd )"
}

set -ex

set_svc_home
cd "$SVC_HOME"
rm -rf jpy
git clone https://github.com/bcdev/jpy.git
cd jpy
git checkout 0.9.0
pip install -U setuptools
pip install -U pip
#python get-pip.py
python setup.py install
mvn install -DskipTests

