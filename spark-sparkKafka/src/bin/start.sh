#!/usr/bin/env bash
CMD=/mnt/spark-alone/spark-2.4.6-bin-hadoop2.6/bin/spark-submit
base=$( cd $(dirname $0); pwd)
function startFisrt(){
echo "starting first app" >> ${base}/runtime.log
nohup $CMD --master spark://name2:7077  \
    --deploy-mode client    \
    --class com.wk.MainStarter  \
    --supervise     \
    --jars ${base}/../lib/dep-demo-1.0.0-fat.jar    \
        ${base}/../boot/first-demo-1.0.0-uber.jar  \
        ${base}/../config/kafkaConfig.properties &>/dev/null  &
echo "complete start first app" >> ${base}/runtime.log

    #--conf spark.executor.extraClassPath=${base}/../lib/*  \
    #--conf spark.driver.extraClassPath=${base}/../lib/*    \
    #--jars ${base}/../lib/spark-java-parent.spark-sparkKafka.jar   \
}


function startMybatis(){
echo "starting mybatis app" >> ${base}/runtime.log
nohup $CMD --master spark://name2:7077  \
    --deploy-mode client    \
    --class com.wk.GetDataAndSave  \
    --supervise     \
    --jars ${base}/../lib/spark-java-parent.spark-sparkMybatis.jar  \
        ${base}/../boot/first-mybatis-1.0.0-uber.jar  \
        ${base}/../config/kafkaConfig.properties  &>/dev/null &
echo "complete start mybatis app" >> ${base}/runtime.log
}

function startMybatis(){
echo "starting redis app" >> ${base}/runtime.log
nohup $CMD --master spark://name2:7077  \
    --deploy-mode client    \
    --class com.wk.SetDataToRedis  \
    --supervise     \
    --jars ${base}/../lib/spark-java-parent.spark-sparkRedis.jar    \
        ${base}/../boot/first-redis-1.0.0-uber.jar  \
        ${base}/../config/kafkaConfig.properties  &>/dev/null &
echo "complete start redis app" >> ${base}/runtime.log
}


main(){
if [ "$#" -le 0 ]; then
    echo "please input param"
    exit 1
fi
 opt=$1
case $opt in
    first)
    startFisrt
    ;;

    mybatis)
    startMybatis
    ;;

    redis)
    startRedis
    ;;
    *)
    echo "invalid param"
    ;;
  esac
}
main $@
