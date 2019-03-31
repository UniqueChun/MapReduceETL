package com.ruoze.hadoop.mapreduce;

import com.ruoze.hadoop.utils.LogUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class LogETLMapper  extends Mapper<LongWritable,Text,NullWritable,Text>{
    /**
     * 通过mapreduce框架的map方式进行数据清洗
     * 进来一条数据就按照我们的解析规则清洗完以后输出
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        int length = value.toString().split("\t").length;
        String traffic = value.toString().split("\t")[7];
        if(length == 8 && traffic != null) {

            LogUtils utils = new LogUtils();
            String result = utils.parse(value.toString());
            if(StringUtils.isNotBlank(result)) {
                context.write(NullWritable.get(), new Text(result));
            }
        }
    }
}
