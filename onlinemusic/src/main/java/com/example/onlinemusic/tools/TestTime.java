package com.example.onlinemusic.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

// 时间日期测试
public class TestTime {
    public static void main(String[] args) {
        // 年月日
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = simpleDateFormat.format(new Date());
        System.out.println(time);

        // 年月日时分秒
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat1.format(new Date());
        System.out.println(format);
    }
}
