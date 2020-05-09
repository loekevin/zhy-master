package com.finest.zhy.log.annotation;


import java.lang.annotation.*;

/**
 * Created by YangH on 2018/06/26.
 *
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemControllerLog {
    /** 描述 **/
    String value() default "";
    /** 接口创建者 **/
    String creator() default "";

}
