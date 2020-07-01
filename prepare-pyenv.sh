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

set -x

set_svc_home
cd "$SVC_HOME"

env PYTHON_CONFIGURE_OPTS="--enable-shared" pyenv install 2.7.18 -s
pyenv uninstall pycamel-dev-2.7.18
pyenv virtualenv 2.7.18 pycamel-dev-2.7.18
pyenv local pycamel-dev-2.7.18