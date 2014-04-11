#!/bin/sh

KUZUMEJI_HOME=`dirname $0`/..
for d in kuzumeji-root kuzumeji-framework kuzumeji-template;
do
    (cd $KUZUMEJI_HOME/$d/;mvn clean install -Dmaven.test.skip=true)
done;
