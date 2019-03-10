#!/bin/bash
JAVA_OPTS=" -server -Xmx4096m -Xms4096m -Xmn2048m -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:MaxTenuringThreshold=6 -XX:CMSInitiatingOccupancyFraction=70 -XX:+UseCMSInitiatingOccupancyOnly -XX:SurvivorRatio=8 -XX:+UseFastAccessorMethods -XX:-UseAdaptiveSizePolicy -XX:+AlwaysPreTouch -XX:+DisableExplicitGC -XX:+HeapDumpOnOutOfMemoryError "
GC_OPTS="-Xloggc:gc.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -XX:+PrintTenuringDistribution -XX:+PrintGCApplicationStoppedTime -XX:+PrintReferenceGC "
JMX_OPTS="-Dcom.sun.management.jmxremote=true -Djava.rmi.server.hostname=127.0.0.1 -Dcom.sun.management.jmxremote.port=18998 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "
nohup java $JAVA_OPTS $GC_OPTS $JMX_OPTS -jar SpringBootDemo.jar >/log 2>&1 &