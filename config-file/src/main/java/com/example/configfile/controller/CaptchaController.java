package com.example.configfile.controller;

import com.google.code.kaptcha.Constants;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

// 验证码案例
@RequestMapping("/admin")
@RestController
public class CaptchaController {
    // 定义非法时间
    private final static long VALID_MILLIS_TIME = 60 * 1000;

    @RequestMapping("/check")
    public boolean checkHomeCaptcha(String inputCaptcha, HttpSession session) {
        // 错误校验
        if (!StringUtils.hasLength(inputCaptcha)) {
            return false;
        }

        // 获取yml配置文件kaptcha中的key和date
        String savedCaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        Date sessionDate = (Date) session.getAttribute(Constants.KAPTCHA_SESSION_DATE);

        // 忽略大小写
        if (inputCaptcha.equalsIgnoreCase(savedCaptcha)) {
            // 判断日期是否合法或超时
            if (sessionDate == null ||
                    System.currentTimeMillis() - sessionDate.getTime() < VALID_MILLIS_TIME) {
                return true;
            }
        }
        return false;
    }
}
