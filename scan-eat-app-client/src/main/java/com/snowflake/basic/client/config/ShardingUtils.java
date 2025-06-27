package com.snowflake.basic.client.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * （CPR-DB-0002）DB分表工具类
 */
public class ShardingUtils {
    public static final String FORMAT_YYYY = "yyyy";
    public static final String FORMAT_YYYY_MM = "yyyy_MM";
    public static SimpleDateFormat sdfYear;
    public static SimpleDateFormat sdfYearMonth;
    public static DateTimeFormatter formatterYear;
    public static DateTimeFormatter formatterYearMonth;

    static {
        sdfYear = new SimpleDateFormat(FORMAT_YYYY);
        formatterYear = DateTimeFormatter.ofPattern(FORMAT_YYYY);
        sdfYearMonth = new SimpleDateFormat(FORMAT_YYYY_MM);
        formatterYearMonth = DateTimeFormatter.ofPattern(FORMAT_YYYY_MM);
    }


    public static Set<String> getSuffixListForRangeDb(String lowerSuffix, String upperSuffix) {
        Set<String> suffixList = new HashSet<>();
        if (lowerSuffix.equals(upperSuffix)) {
            suffixList.add(lowerSuffix);
        } else {
            //上下界不在同一张表  计算间隔的所有表
            String tempSuffix = lowerSuffix;
            while (!tempSuffix.equals(upperSuffix)) {
                suffixList.add(tempSuffix);
                LocalDate tempDate = parse(tempSuffix, sdfYear);
                tempSuffix = ShardingUtils.getSuffixByYearMonth(formatterYear, tempDate.plusYears(1));
            }
            suffixList.add(tempSuffix);
        }
        return suffixList;
    }

    public static Set<String> getSuffixListForRangeTable(String lowerSuffix, String upperSuffix) {
        Set<String> suffixList = new HashSet<>();
        if (lowerSuffix.equals(upperSuffix)) {
            //上下界在同一张表
            suffixList.add(lowerSuffix);
        } else {
            //上下界不在同一张表  计算间隔的所有表
            String tempSuffix = lowerSuffix;
            while (!tempSuffix.equals(upperSuffix)) {
                suffixList.add(tempSuffix);
                LocalDate tempDate = parse(tempSuffix, sdfYearMonth);
                tempSuffix = ShardingUtils.getSuffixByYearMonth(formatterYearMonth, tempDate.plusMonths(1));
            }
            suffixList.add(tempSuffix);
        }
        return suffixList;
    }

    public static String getSuffixByYearMonth(DateTimeFormatter formatter, LocalDate date) {
        return date.format(formatter);
    }

    public static LocalDate parse(String date, SimpleDateFormat sdf) {
        Date dateTime = null;
        try {
            dateTime = sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        LocalDate localDate = dateTime.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

}