#!/usr/bin/env bash
source ~/.bash_profile
process_date=20190329

echo -e "\033[36mstep1:MR ETL\033[0m"
hadoop jar /home/hadoop/lib/hadoop-1.0.jar com.ruoze.hadoop.mapreduce.LogETLDriver /g6/hadoop/accesslog/$process_date/ /g6/hadoop/access/output/day=$process_date


echo -e "\033[36mstep2:Mv Data to DW \033[0m"
hadoop fs -mv /g6/hadoop/access/output/day=$process_date /g6/hadoop/access/clear


echo -e "\033[36mstep3:Alter metadata\033[0m"
database=g6_hadoop
hive -e "use ${database}; alter table g6_access add if not exists partition(day=$process_date);"
