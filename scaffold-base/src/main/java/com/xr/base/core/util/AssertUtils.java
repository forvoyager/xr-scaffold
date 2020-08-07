package com.xr.base.core.util;

import com.xr.base.core.enums.ResultCodeEnum;
import com.xr.base.core.exception.BaseException;

import java.util.Collection;
import java.util.Map;

/**
 * <b>author</b>: yang.changyan@foundbyte.com
 * <b>time</b>: 2019-06-18 08:05:38 <br>
 * <b>description</b>: 断言工具类
 */
public final class AssertUtils {

    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new BaseException(ResultCodeEnum.ILLEGAL_STATUS, message);
        }
    }
    
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new BaseException(ResultCodeEnum.ILLEGAL_STATUS, message);
        }
    }

    public static void notEmpty(String str, String message) {
        if (str == null || str.trim().length() == 0) {
            throw new BaseException(ResultCodeEnum.ILLEGAL_STATUS, message);
        }
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throw new BaseException(ResultCodeEnum.ILLEGAL_STATUS, message);
        }
    }

    public static void notEmpty(Map<?, ?> map, String message) {
        if (map == null || map.isEmpty()) {
            throw new BaseException(ResultCodeEnum.ILLEGAL_STATUS, message);
        }
    }

    public static void largeThanZero(Double a, String message) {
        notNull(a, "data must not be null");
        if( a <= 0){
            throw new BaseException(ResultCodeEnum.ILLEGAL_STATUS, message);
        }
    }

    public static void isNumber(Object obj, String message) {
        notNull(obj, "data must not be null");
        try {
            Double.valueOf(obj.toString());
        } catch (NumberFormatException nfe){
            throw new BaseException(ResultCodeEnum.ILLEGAL_STATUS, message);
        }
    }
    
    private AssertUtils(){}
}
