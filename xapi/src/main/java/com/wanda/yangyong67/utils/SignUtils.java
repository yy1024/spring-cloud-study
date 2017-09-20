package com.wanda.yangyong67.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by yangyong on 2017/7/24.
 */
public class SignUtils {


    public static String getSignString(Map<String, String[]> paramMap, String secret) {
        List<String> keys = new ArrayList<String>(paramMap.keySet());
        // sign参数不参与签名
        keys.remove("sign");
        // 参数按照key的字典顺序升序排列
        Collections.sort(keys);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String[] value = paramMap.get(keys.get(i));
            if(value == null || value.length == 0) {
                continue;
            }
            sb.append(keys.get(i) + StringUtils.join(value, ","));
        }
        sb.append("secret" + secret);
        return Md5Utils.getMD5Str(sb.toString()).toUpperCase();
    }
}
