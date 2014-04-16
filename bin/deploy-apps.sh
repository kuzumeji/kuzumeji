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
		(cd "$KUZUMEJI_HOME/kuzumeji-template/";cmd /c asadmin deploy "$d/target/$d-$KUZUMEJI_VER.ear");
    else
		(cd "$KUZUMEJI_HOME/kuzumeji-template/";asadmin deploy "$d/target/$d-$KUZUMEJI_VER.ear");
    fi
done;
