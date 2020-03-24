package com.liyulong.blog.shiro.service.imp;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.liyulong.blog.main.common.constant.RedisConstant;
import com.liyulong.blog.main.common.exception.MyException;
import com.liyulong.blog.main.common.util.RedisUtil;
import com.liyulong.blog.shiro.service.KaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class KaptchaServiceImp implements KaptchaService {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String getKaptcha(String username) {
        if(StringUtils.isEmpty(username)){
            throw new MyException("用户名不能为空");
        }
        //生成文字验证码
        String code = defaultKaptcha.createText();
        //判断redis中是否存在有对应验证码
        if(!StringUtils.isEmpty(redisUtil.get(RedisConstant.LOGIN_KAPTCHA + username))){
            //不为空删除redis中对应key值
            redisUtil.delete(RedisConstant.LOGIN_KAPTCHA + username);
        }
        redisUtil.set(RedisConstant.LOGIN_KAPTCHA + username,code,60 * 5);

        //生成图片验证码
        BufferedImage image = defaultKaptcha.createImage(code);

        //将图片验证码转为base64
        try {
            //io流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //将生成的图片验证码写入流中
            ImageIO.write(image,"png",baos);
            //转换成字节
            byte[] bytes = baos.toByteArray();
            BASE64Encoder encoder = new BASE64Encoder();
            //转换为base64字符串
            String img_base64 = encoder.encodeBuffer(bytes).trim();
            //删除字符串中的 \r\n
            img_base64 = img_base64.replaceAll("\n","").replaceAll("\r","");
            return "data:image/png;base64," + img_base64;
        } catch (IOException e) {
            e.printStackTrace();
            throw new MyException("获取验证码失败");
        }
    }

    @Override
    public Boolean verifyKaptcha(String username, String code) {
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(code)){
            return false;
        }

        //从redis中取出验证码
        String redisCode = redisUtil.get(RedisConstant.LOGIN_KAPTCHA + username);
        if(code.equalsIgnoreCase(redisCode)){
            redisUtil.delete(RedisConstant.LOGIN_KAPTCHA + username);
            return true;
        }
        return false;
    }
}
