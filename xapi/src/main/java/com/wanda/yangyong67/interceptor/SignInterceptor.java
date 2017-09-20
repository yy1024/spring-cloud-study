package com.wanda.yangyong67.interceptor;

import com.wanda.yangyong67.exception.ParamValidException;
import com.wanda.yangyong67.utils.SignUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * Created by yangyong on 2017/7/24.
 */
public class SignInterceptor implements HandlerInterceptor{

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String appid = httpServletRequest.getParameter("appid");
        String timeStamp = httpServletRequest.getParameter("timeStamp");
        if(StringUtils.isBlank(appid)) {
            throw new ParamValidException(4002, "缺少参数appid");
        }
        // TODO 校验时间在五分钟之内
        verifyTime(timeStamp);

        Map<String,String[]> paramMap = httpServletRequest.getParameterMap();
        if(!verifySign(paramMap)) {
            throw new ParamValidException(4002, "数字签名sign失败");
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


    private boolean verifySign(Map<String,String[]> paramMap) {
        String appid = StringUtils.join(paramMap.get("appid"));
        //TODO 通过appid 获取secret
        String secret = "123456";
        String signStr = SignUtils.getSignString(paramMap,secret);
        return signStr.equals(StringUtils.join(paramMap.get("sign")));
    }

    private void verifyTime(String timeStamp) {
        if(StringUtils.isBlank(timeStamp)) {
            throw new ParamValidException(4002, "缺少参数timeStamp");
        }
        String validTime = (new Date().getTime()/1000 - 5 * 60) + "";
        if(timeStamp.compareTo(validTime) <= 0) {
            throw new ParamValidException(4002, "数字签名sign失效");
        }
    }

}
