#!/bin/bash

KUZUMEJI_HOME=`dirname $0`/..
(cd "$KUZUMEJI_HOME/kuzumeji-template"; mvn -pl kuzumeji-template-backend dbunit:operation -Dschema=public -Dsrc=src/test/resources/test-data.xls;)
