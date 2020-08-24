#!/usr/bin/env bash
CMD=/mnt/spark-alone/spark-2.4.6-bin-hadoop2.6/bin/spark-submit
base=$( cd $(dirname $0); pwd)
nohup $CMD --master spark://name2:7077  \
    --deploy-mode client    \
    --class com.wk.MainStarter  \
    --supervise     \
    --jars  ${base}/../lib/fat-demo-1.0.0-uber.jar  \
        ${base}/../boot/first-demo-1.0.0-uber.jar  \
        ${base}/../config/kafkaConfig.properties   >>  ${base}/runtime.log 2>&1 &
