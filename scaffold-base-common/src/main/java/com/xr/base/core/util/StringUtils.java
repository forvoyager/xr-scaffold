package com.xr.base.core.util;

import java.util.Random;

/**
 * <b>author</b>: forvoyager@outlook.com
 * <b>time</b>: 2019-06-18 08:05:38 <br>
 * <b>description</b>:
 * 字符操作工具类
 * 继承自 com.ms.base.comm.util.StringUtils
 * 可以自行扩展
 */
public final class StringUtils extends org.springframework.util.StringUtils {

    private StringUtils(){}

    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }

    public static String randomString() {
        return randomString(8);
    }

    public static String randomString(int length) {
        return (new RandomString(length)).nextString();
    }

}
class RandomString {
    public static final int DEFAULT_LENGTH = 8;
    private static final char[] SYMBOL;
    private static final int KEY_BITS;
    private final Random random;
    private final int length;

    public RandomString() {
        this(8);
    }

    public RandomString(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("A random string's length cannot be zero or negative");
        } else {
            this.length = length;
            this.random = new Random();
        }
    }

    public String nextString() {
        char[] buffer = new char[this.length];

        for(int index = 0; index < this.length; ++index) {
            buffer[index] = SYMBOL[this.random.nextInt(SYMBOL.length)];
        }

        return new String(buffer);
    }

    static {
        StringBuilder symbol = new StringBuilder();

        char character;
        for(character = '0'; character <= '9'; ++character) {
            symbol.append(character);
        }

        for(character = 'a'; character <= 'z'; ++character) {
            symbol.append(character);
        }

        for(character = 'A'; character <= 'Z'; ++character) {
            symbol.append(character);
        }

        SYMBOL = symbol.toString().toCharArray();
        int bits = 32 - Integer.numberOfLeadingZeros(SYMBOL.length);
        KEY_BITS = bits - (Integer.bitCount(SYMBOL.length) == bits ? 0 : 1);
    }
}