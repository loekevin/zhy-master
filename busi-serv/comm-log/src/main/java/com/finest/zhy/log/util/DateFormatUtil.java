package com.finest.zhy.log.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Administrator on 2018/6/19 0019.
 */


public class DateFormatUtil {

    private static String SYSMTE_DATE=null;

    public static final ThreadLocal<SimpleDateFormat> YMD = new ThreadLocal() {
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy/MM/dd");
        }
    };
    public static final ThreadLocal<SimpleDateFormat> YY_MM_DDHHMM = new ThreadLocal() {
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm");
            sdf.setLenient(false);
            return sdf;
        }
    };
    public static final ThreadLocal<SimpleDateFormat> YY_MM_DD = new ThreadLocal() {
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
            sdf.setLenient(false);
            return sdf;
        }
    };
    public static final ThreadLocal<SimpleDateFormat> YYYY_MM_DDHHMM = new ThreadLocal() {
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            sdf.setLenient(false);
            return sdf;
        }
    };
    public static final ThreadLocal<SimpleDateFormat> YYYY_MM_DDHH24MISS = new ThreadLocal() {
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    public static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD = new ThreadLocal() {
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            return sdf;
        }
    };
    public static final ThreadLocal<SimpleDateFormat> YYYY = new ThreadLocal() {
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            sdf.setLenient(false);
            return sdf;
        }
    };
    public static final ThreadLocal<SimpleDateFormat> YY = new ThreadLocal() {
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat sdf = new SimpleDateFormat("yy");
            sdf.setLenient(false);
            return sdf;
        }
    };
    public static final ThreadLocal<SimpleDateFormat> MM = new ThreadLocal() {
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat sdf = new SimpleDateFormat("MM");
            sdf.setLenient(false);
            return sdf;
        }
    };
    public static final ThreadLocal<SimpleDateFormat> DD = new ThreadLocal() {
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd");
            sdf.setLenient(false);
            return sdf;
        }
    };
    public static final ThreadLocal<SimpleDateFormat> YYMMDD = new ThreadLocal() {
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
            sdf.setLenient(false);
            return sdf;
        }
    };
    public static final ThreadLocal<SimpleDateFormat> YYYYMMDD = new ThreadLocal() {
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            sdf.setLenient(false);
            return sdf;
        }
    };
    public static final ThreadLocal<SimpleDateFormat> YYYYMMDDHHMMSS = new ThreadLocal() {
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            sdf.setLenient(false);
            return sdf;
        }
    };
    public static final ThreadLocal<SimpleDateFormat> YYYYMMDDHHMM = new ThreadLocal() {
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
            sdf.setLenient(false);
            return sdf;
        }
    };
    public static final ThreadLocal<SimpleDateFormat> YYYYCNMMCNDDCN = new ThreadLocal() {
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            sdf.setLenient(false);
            return sdf;
        }
    };
    public static final ThreadLocal<SimpleDateFormat> YYYYMM = new ThreadLocal() {
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            sdf.setLenient(false);
            return sdf;
        }
    };
    public static final ThreadLocal<SimpleDateFormat> HHMM = new ThreadLocal() {
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm");
        }
    };

    public static final ThreadLocal<Calendar> CAL=new ThreadLocal<Calendar>(){
        @Override
        protected Calendar initialValue() {
            return Calendar.getInstance();
        }
    };


    private DateFormatUtil() {


    }


    public static Date getCurrentDate() {
        if(SYSMTE_DATE!=null){
            try {
                return YYYY_MM_DDHH24MISS.get().parse(SYSMTE_DATE);
            } catch (ParseException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }else{
            return new Date();
        }

    }

    public static Date beforeDay(){
        Calendar ca=CAL.get();
        ca.setTime(getCurrentDate());
        ca.add(Calendar.DATE,-1);
        return ca.getTime();
    }

    public static Date beforeDay(Date date){
        Calendar ca=CAL.get();
        ca.setTime(date);
        ca.add(Calendar.DATE,-1);
        return ca.getTime();
    }

    public static Date nextDay(){
        Calendar ca=CAL.get();
        ca.setTime(getCurrentDate());
        ca.add(Calendar.DATE,1);
        return ca.getTime();
    }

    public static Date nextDay(Date date){
        Calendar ca=CAL.get();
        ca.setTime(date);
        ca.add(Calendar.DATE,1);
        return ca.getTime();
    }




    public static String getDdFromLong(Date time) {
        return time == null?"":((SimpleDateFormat)DD.get()).format(time);
    }

    public static String getMmFromLong(Date time) {
        return time == null?"":((SimpleDateFormat)MM.get()).format(time);
    }

    public static String getYyyyFromLong(Date time) {
        return time == null?"":((SimpleDateFormat)YYYY.get()).format(time);
    }

    public static String getYyyyMmFromLong(Date time) {
        return time == null?"":((SimpleDateFormat)YYYYMM.get()).format(time);
    }

    public static String long2YyyyMmDd(Date time) {
        return time == null?"":((SimpleDateFormat)YYYYMMDD.get()).format(time);
    }

    public static String long2YyyyMmDdHHmmss(Date time) {
        return time == null?"":((SimpleDateFormat)YYYYMMDDHHMMSS.get()).format(time);
    }

    public static String long2YyyyMmDdHHmm(Date time) {
        return time == null?"":((SimpleDateFormat)YYYYMMDDHHMM.get()).format(time);
    }

    public static String long2YyyyMmDdHH24miss(Date time) {
        return time == null?"":((SimpleDateFormat)YYYY_MM_DDHH24MISS.get()).format(time);
    }

    public static String longYyyyMmDd(Date time) {
        return time == null?"":((SimpleDateFormat)YYYY_MM_DD.get()).format(time);
    }

    public static String string2YyyyMmDdHHmm(Date time) {
        return time == null?"":((SimpleDateFormat)YYYY_MM_DDHHMM.get()).format(time);
    }

    public static String long2YyyycnMMcnDDcn(Date time) {
        return time == null?"":((SimpleDateFormat)YYYYCNMMCNDDCN.get()).format(time);
    }

    public static Timestamp yyyyMmDdTimestamp(String yyyymmdd) {
        try {
            return new Timestamp(((SimpleDateFormat)YYYYMMDD.get()).parse(yyyymmdd).getTime());
        } catch (ParseException var2) {
            throw new IllegalArgumentException("Formatter Date" + yyyymmdd + " ERROR." + var2.getMessage());
        }
    }

    public static Timestamp yyyyMmDd2Timestamp(String yyyymmdd) {
        try {
            return new Timestamp(((SimpleDateFormat)YYYY_MM_DD.get()).parse(yyyymmdd).getTime());
        } catch (ParseException var2) {
            throw new IllegalArgumentException("Formatter Date" + yyyymmdd + " ERROR." + var2.getMessage());
        }
    }

    public static Timestamp yyyycnMMcnDDcn2Timestamp(String yyyycnMMcnDDcn) {
        try {
            return new Timestamp(((SimpleDateFormat)YYYYCNMMCNDDCN.get()).parse(yyyycnMMcnDDcn).getTime());
        } catch (ParseException var2) {
            throw new IllegalArgumentException("Formatter Date" + yyyycnMMcnDDcn + " ERROR." + var2.getMessage());
        }
    }

    public static Date yyyycnMMcnDDcn2Date(String yyyycnMMcnDDcn) {
        try {
            return ((SimpleDateFormat)YYYYCNMMCNDDCN.get()).parse(yyyycnMMcnDDcn);
        } catch (ParseException var2) {
            throw new IllegalArgumentException("Formatter Date" + yyyycnMMcnDDcn + " ERROR." + var2.getMessage());
        }
    }

    public static Date yyyyMmDdDate(String yyyyMmDd) {
        try {
            return new Date(((SimpleDateFormat)YYYYMMDD.get()).parse(yyyyMmDd).getTime());
        } catch (ParseException var2) {
            throw new IllegalArgumentException("Formatter Date" + yyyyMmDd + " ERROR." + var2.getMessage());
        }
    }

    public static Date yyyyMmDd2Date(String yyyyMmDd) {
        try {
            return new Date(((SimpleDateFormat)YYYY_MM_DD.get()).parse(yyyyMmDd).getTime());
        } catch (ParseException var2) {
            throw new IllegalArgumentException("Formatter Date" + yyyyMmDd + " ERROR." + var2.getMessage());
        }
    }

    public static Date ymd(String yyyyMmDd) {
        try {
            return new Date(((SimpleDateFormat)YMD.get()).parse(yyyyMmDd).getTime());
        } catch (ParseException var2) {
            throw new IllegalArgumentException("Formatter Date" + yyyyMmDd + " ERROR." + var2.getMessage());
        }
    }

    public static long yyyycnMMcnDDcn2Long(String yyyycnMMcnDDcn) {
        try {
            return ((SimpleDateFormat)YYYYCNMMCNDDCN.get()).parse(yyyycnMMcnDDcn).getTime();
        } catch (ParseException var2) {
            throw new IllegalArgumentException("Formatter Date" + yyyycnMMcnDDcn + " ERROR." + var2.getMessage());
        }
    }

    public static long yyyyMmDdHH24miss2Long(String yyyyMmDdHH24miss) {
        try {
            return ((SimpleDateFormat)YYYY_MM_DDHH24MISS.get()).parse(yyyyMmDdHH24miss).getTime();
        } catch (ParseException var2) {
            throw new IllegalArgumentException("Formatter Date" + yyyyMmDdHH24miss + " ERROR." + var2.getMessage());
        }
    }

    public static String long2YyMmDd(Date time) {
        return time == null?"":((SimpleDateFormat)YYMMDD.get()).format(time);
    }

    public static String hhMm(Date time) {
        return time == null?"":((SimpleDateFormat)HHMM.get()).format(time);
    }

    public static Date yyyyMMddhh24miss(String yyyymmddhh24miss) {
        try {
            return new Date(((SimpleDateFormat)YYYY_MM_DDHH24MISS.get()).parse(yyyymmddhh24miss).getTime());
        } catch (ParseException var2) {
            throw new IllegalArgumentException("Formatter Date" + yyyymmddhh24miss + " ERROR." + var2.getMessage());
        }
    }

    public static Date yyyyMmDdHhMm(String yyyymmddhh24miss) {
        try {
            return new Date(((SimpleDateFormat)YYYYMMDDHHMM.get()).parse(yyyymmddhh24miss).getTime());
        } catch (ParseException var2) {
            throw new IllegalArgumentException("Formatter Date" + yyyymmddhh24miss + " ERROR." + var2.getMessage());
        }
    }
}
