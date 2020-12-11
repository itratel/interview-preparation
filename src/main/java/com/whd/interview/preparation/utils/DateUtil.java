package com.whd.interview.preparation.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * <p>DateUtil<p/>
 * DateUtil <br>
 *
 * @author whd.java@gmail.com
 * @date 2020/12/11 16:28
 * @since 1.0.0
 */
public final class DateUtil {

    /***
     * 获取当前时间的字符串格式
     * @return {@link String}
     */
    public static String now() {
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormatter.format(now);
    }

}
