#!/usr/bin/env sh

GRADLE_HOME=$(dirname "$0")/gradle/wrapper
GRADLE_JAR="$GRADLE_HOME/gradle-wrapper.jar"

if [ ! -f "$GRADLE_JAR" ]; then
    echo "Error: gradle-wrapper.jar not found at $GRADLE_JAR"
    exit 1
fi

# Determine the Java command to use to start the JVM.
if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        JAVACMD="$JAVA_HOME/jre/sh/java"
    else
        JAVACMD="$JAVA_HOME/bin/java"
    fi
else
    JAVACMD="java"
fi

exec "$JAVACMD" -Xmx64m -Xms64m -classpath "$GRADLE_JAR" org.gradle.wrapper.GradleWrapperMain "$@"
