#!/bin/bash

cygwin=false
case `uname` in
    CYGWIN*) cygwin=true;;
esac

if $cygwin; then
    cmd /c asadmin list-applications;
else
    asadmin list-applications;
fi

