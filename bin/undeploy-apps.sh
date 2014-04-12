#!/bin/bash

KUZUMEJI_HOME=`dirname $0`/..
KUZUMEJI_VER=0.1.0-SNAPSHOT

cygwin=false
case `uname` in
    CYGWIN*) cygwin=true;;
esac

for d in kuzumeji-template-application-provider kuzumeji-template-application-consumer
do
    if $cygwin; then
	(cd "$KUZUMEJI_HOME/kuzumeji-template/kuzumeji-template-application/";cmd /c asadmin undeploy "$d-$KUZUMEJI_VER");
    else
	(cd "$KUZUMEJI_HOME/kuzumeji-template/kuzumeji-template-application/";asadmin undeploy "$d-$KUZUMEJI_VER");
    fi
done;
