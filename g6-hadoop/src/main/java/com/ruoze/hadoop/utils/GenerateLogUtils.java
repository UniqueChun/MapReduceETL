package com.ruoze.hadoop.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class GenerateLogUtils {
    public static void main(String[] args) {

        for (int i =0 ;i<300000; i++){
            generateLog();
        }

    }

    private static String generateLog() {
        try {
            Random rd = new Random();
            Date date = randomDate("2019-01-01", "2019-01-31");

            String[] domainStr = new String[]{
                    "v1.go2yd.com",
                    "v2.go2yd.com",
                    "v3.go2yd.com",
                    "v4.go2yd.com",
                    "v5.go2yd.com",
            };

            int domainNum = rd.nextInt(domainStr.length - 1);

            String[] trafficStr = new String[]{
                    "136662",
                    "785966",
                    "987422",
                    "975578",
                    "154851",
                    ""
            };

            int trafficNum = rd.nextInt(trafficStr.length - 1);

            StringBuilder builder = new StringBuilder();
            builder
                    .append("baidu").append("\t")
                    .append("CN").append("\t")
                    .append("2").append("\t")
                    .append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)).append("\t")
                    .append(getRandomIp()).append("\t")
                    .append(domainStr[domainNum]).append("\t")
                    .append("http://v1.go2yd.com/user_upload/1531633977627104fdecdc68fe7a2c4b96b2226fd3f4c.mp4_bd.mp4").append("\t")
                    .append(trafficStr[trafficNum]).append("\t");

            File file = new File("access.log");
            if (!file.exists()) {

                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getName(), true);
            fileWriter.write(builder.toString() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 随机生成时间
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    private static Date randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }


    /**
     * 随机生成IP-----------------------------------------------------
     *
     * @return
     */
    public static String getRandomIp() {

        // ip范围
        int[][] range = {{607649792, 608174079},// 36.56.0.0-36.63.255.255
                {1038614528, 1039007743},// 61.232.0.0-61.237.255.255
                {1783627776, 1784676351},// 106.80.0.0-106.95.255.255
                {2035023872, 2035154943},// 121.76.0.0-121.77.255.255
                {2078801920, 2079064063},// 123.232.0.0-123.235.255.255
                {-1950089216, -1948778497},// 139.196.0.0-139.215.255.255
                {-1425539072, -1425014785},// 171.8.0.0-171.15.255.255
                {-1236271104, -1235419137},// 182.80.0.0-182.92.255.255
                {-770113536, -768606209},// 210.25.0.0-210.47.255.255
                {-569376768, -564133889}, // 222.16.0.0-222.95.255.255
        };

        Random rdint = new Random();
        int index = rdint.nextInt(10);
        String ip = num2ip(range[index][0] + new Random().nextInt(range[index][1] - range[index][0]));
        return ip;
    }

    /*
     * 将十进制转换成ip地址
     */
    public static String num2ip(int ip) {
        int[] b = new int[4];
        String x = "";

        b[0] = (int) ((ip >> 24) & 0xff);
        b[1] = (int) ((ip >> 16) & 0xff);
        b[2] = (int) ((ip >> 8) & 0xff);
        b[3] = (int) (ip & 0xff);
        x = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "." + Integer.toString(b[2]) + "." + Integer.toString(b[3]);

        return x;
    }

}
