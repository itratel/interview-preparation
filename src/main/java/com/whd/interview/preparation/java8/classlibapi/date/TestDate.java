package com.whd.interview.preparation.java8.classlibapi.date;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.TimeZone;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/2 14:36
 * @apiNote TestDate
 * @see Clock
 */
public class TestDate {

    public static void main(String[] args) {

        //默认Clock类
        Clock clock = Clock.systemUTC();
        //获取Asia/Taipei的时间
        Clock taipeiLock = Clock.system(ZoneId.of("Asia/Taipei"));
        System.out.println("taipeiLock.instant() = " + taipeiLock.instant());
        System.out.println("clock.instant() = " + clock.instant());
        System.out.println("taipeiLock.millis() = " + taipeiLock.millis());
        System.out.println("clock.millis() = " + clock.millis());
        System.out.println(System.currentTimeMillis());

        //获取当前时间
        LocalDate localDate = LocalDate.now(clock);
        System.out.println("localDate = " + localDate);
        //从现在时间减去3天
        LocalDate minusDays = localDate.minusDays(3);
        System.out.println("minusDays = " + minusDays);
        LocalDate minus = localDate.minus(4, ChronoUnit.DAYS);
        System.out.println("minus = " + minus);
        //从现在时间加1天
        LocalDate plusDays = localDate.plusDays(1);
        System.out.println("plusDays = " + plusDays);
        LocalDate plus = localDate.plus(5, ChronoUnit.DAYS);
        System.out.println("plus = " + plus);
        LocalDate plus1 = localDate.plus(Period.ofDays(2));
        System.out.println("plus1 = " + plus1);

        //LocalTime
        LocalTime localTime = LocalTime.now(clock);
        System.out.println("localTime = " + localTime);
        LocalTime minusTime = localTime.minus(10, ChronoUnit.MINUTES);
        System.out.println("minusTime = " + minusTime);
        LocalTime minusHours = localTime.minus(Duration.ofHours(2));
        System.out.println("minusHours = " + minusHours);

        //LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now(clock);
        System.out.println("localDateTime = " + localDateTime);

        //获取北京所在时区的现在时间
        LocalDateTime shanghaiTime = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println("shanghaiTime = " + shanghaiTime);

        //获取重庆所在时区的现在时间
        LocalDateTime chongqingTime = LocalDateTime.now(TimeZone.getTimeZone("Asia/Chongqing").toZoneId());
        System.out.println("chongqingTime = " + chongqingTime);


        //获取可用的时区的第一种方法
        System.out.println("获取可用的时区方法1");
        ZoneId.getAvailableZoneIds().forEach(t-> System.out.println("t = " + t));

        //获取可用的时区第二种方法
        System.out.println("获取可用的时区方法2");
        String[] availableIDs = TimeZone.getAvailableIDs();
        for (String availableID : availableIDs) {
            System.out.println("availableID = " + availableID);
        }

        ZonedDateTime zonedDatetime = ZonedDateTime.now();
        ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now(clock);
        ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now( ZoneId.of( "America/Los_Angeles" ) );

        System.out.println("zonedDatetime = " + zonedDatetime);
        System.out.println("zonedDatetimeFromZone = " + zonedDatetimeFromZone);
        System.out.println("zonedDatetimeFromClock = " + zonedDatetimeFromClock);

    }
}
