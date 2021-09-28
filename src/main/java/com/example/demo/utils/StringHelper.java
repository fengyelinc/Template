package com.example.demo.utils;

import java.util.UUID;

public class StringHelper {
    /**
     * 首字母变小写
     */
    public static String firstCharToLowerCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] += ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    /**
     * 首字母变大写
     */
    public static String firstCharToUpperCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'a' && firstChar <= 'z') {
            char[] arr = str.toCharArray();
            arr[0] -= ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    /**
     * 字符串为 null 或者内部字符全部为 ' ' '\t' '\n' '\r' 这四类字符时返回 true
     */
    public static boolean isBlank(String str) {
        if (str == null) {
            return true;
        }
        int len = str.length();
        if (len == 0) {
            return true;
        }
        for (int i = 0; i < len; i++) {
            switch (str.charAt(i)) {
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                    // case '\b':
                    // case '\f':
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    public static boolean notBlank(String str) {
        return !isBlank(str);
    }

    public static boolean notBlank(String... strings) {
        if (strings == null) {
            return false;
        }
        for (String str : strings) {
            if (isBlank(str)) {
                return false;
            }
        }
        return true;
    }

    public static boolean notNull(Object... paras) {
        if (paras == null) {
            return false;
        }
        for (Object obj : paras) {
            if (obj == null) {
                return false;
            }
        }
        return true;
    }

    public static String toCamelCase(String stringWithUnderline) {
        if (stringWithUnderline.indexOf('_') == -1) {
            return stringWithUnderline;
        }

        stringWithUnderline = stringWithUnderline.toLowerCase();
        char[] fromArray = stringWithUnderline.toCharArray();
        char[] toArray = new char[fromArray.length];
        int j = 0;
        for (int i=0; i<fromArray.length; i++) {
            if (fromArray[i] == '_') {
                // 当前字符为下划线时，将指针后移一位，将紧随下划线后面一个字符转成大写并存放
                i++;
                if (i < fromArray.length) {
                    toArray[j++] = Character.toUpperCase(fromArray[i]);
                }
            }
            else {
                toArray[j++] = fromArray[i];
            }
        }
        return new String(toArray, 0, j);
    }

    public static String join(String[] stringArray) {
        StringBuilder sb = new StringBuilder();
        for (String s : stringArray) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static String join(String[] stringArray, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<stringArray.length; i++) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(stringArray[i]);
        }
        return sb.toString();
    }


    /**
     * 左边补零；param转化成字符串，位数不够左边用零补足
     * @param param 被补零参数
     * @param reference 返回的字符串长度
     * */
    public static String firstZeroFill(int param,int reference){
        if((param/reference)>0){
            return param+"";
        }else{
            return "0"+param;
        }
    }

    public static String getRandomUUID() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }

    public static String[] toArray(String str,String regex) {
       if(isBlank(str)){
           return new String[]{};
       }
       return str.split(regex);
    }

    /**
     * UUID生成8位随机数
     */
    public static String uuid6() {
        String[] chars = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuffer shortBuffer = null;
        for (int i = 0; i < 10; i++) {
            shortBuffer = new StringBuffer();
            String uuid = UUID.randomUUID().toString().replace("-", "");
            for (int j = 0; j < 6; j++) {
                String str = uuid.substring(j * 4, j * 4 + 4);
                int par = Integer.parseInt(str, 16);
                shortBuffer.append(chars[par % 10]);
            }

        }
        return shortBuffer.toString();
    }
}
