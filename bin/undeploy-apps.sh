#!/bin/bash

KUZUMEJI_HOME=`dirname $0`/..
KUZUMEJI_VER=0.1.0-SNAPSHOT

cygwin=false
case `uname` in
    CYGWIN*) cygwin=true;;
esac

for d in kuzumeji-template-backend kuzumeji-template-frontend
do
    if $cygwin; then
		cmd /c asadmin undeploy "$d-$KUZUMEJI_VER";
    else
		asadmin undeploy "$d-$KUZUMEJI_VER";
    fi
done;
