#!/bin/sh

KUZUMEJI_HOME=`dirname $0`/..
KUZUMEJI_VER=0.1.0-SNAPSHOT

for d in kuzumeji-template-application-provider kuzumeji-template-application-consumer
do
    (cd "$KUZUMEJI_HOME/kuzumeji-template/kuzumeji-template-application/";cmd /c asadmin deploy "$d/target/$d-$KUZUMEJI_VER.ear")
done;
