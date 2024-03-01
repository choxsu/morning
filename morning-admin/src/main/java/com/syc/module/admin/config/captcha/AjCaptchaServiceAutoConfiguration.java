package com.syc.module.admin.config.captcha;

import com.xingyuv.captcha.service.CaptchaService;
import com.xingyuv.captcha.service.impl.CaptchaServiceFactory;
import com.xingyuv.captcha.util.ImageUtils;
import com.xingyuv.captcha.util.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author xq.su
 * created on 2024/2/29
 */

@Configuration
public class AjCaptchaServiceAutoConfiguration {
    public AjCaptchaServiceAutoConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean
    public CaptchaService captchaService(AjCaptchaProperties prop) {
        Properties config = new Properties();
        config.put("captcha.cacheType", prop.getCacheType().name());
        config.put("captcha.water.mark", prop.getWaterMark());
        config.put("captcha.font.type", prop.getFontType());
        config.put("captcha.type", prop.getType().getCodeValue());
        config.put("captcha.interference.options", prop.getInterferenceOptions());
        config.put("captcha.captchaOriginalPath.jigsaw", prop.getJigsaw());
        config.put("captcha.captchaOriginalPath.pic-click", prop.getPicClick());
        config.put("captcha.slip.offset", prop.getSlipOffset());
        config.put("captcha.aes.status", String.valueOf(prop.getAesStatus()));
        config.put("captcha.water.font", prop.getWaterFont());
        config.put("captcha.cache.number", prop.getCacheNumber());
        config.put("captcha.timing.clear", prop.getTimingClear());
        config.put("captcha.history.data.clear.enable", prop.isHistoryDataClearEnable() ? "1" : "0");
        config.put("captcha.req.frequency.limit.enable", prop.getReqFrequencyLimitEnable() ? "1" : "0");
        config.put("captcha.req.get.lock.limit", prop.getReqGetLockLimit() + "");
        config.put("captcha.req.get.lock.seconds", prop.getReqGetLockSeconds() + "");
        config.put("captcha.req.get.minute.limit", prop.getReqGetMinuteLimit() + "");
        config.put("captcha.req.check.minute.limit", prop.getReqCheckMinuteLimit() + "");
        config.put("captcha.req.verify.minute.limit", prop.getReqVerifyMinuteLimit() + "");
        config.put("captcha.font.size", prop.getFontSize() + "");
        config.put("captcha.font.style", prop.getFontStyle() + "");
        config.put("captcha.word.count", prop.getClickWordCount() + "");
        boolean judge = StringUtils.isNotBlank(prop.getJigsaw()) && prop.getJigsaw().startsWith("classpath:") || StringUtils.isNotBlank(prop.getPicClick()) && prop.getPicClick().startsWith("classpath:");
        if (judge) {
            config.put("captcha.init.original", "true");
            initializeBaseMap(prop.getJigsaw(), prop.getPicClick());
        }

        return CaptchaServiceFactory.getInstance(config);
    }

    private static void initializeBaseMap(String jigsaw, String picClick) {
        ImageUtils.cacheBootImage(getResourcesImagesFile(jigsaw + "/original/*.png"), getResourcesImagesFile(jigsaw + "/slidingBlock/*.png"), getResourcesImagesFile(picClick + "/*.png"));
    }

    public static Map<String, String> getResourcesImagesFile(String path) {
        Map<String, String> imgMap = new HashMap();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        try {
            Resource[] resources = resolver.getResources(path);
            Resource[] var4 = resources;
            int var5 = resources.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Resource resource = var4[var6];
                byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
                String string = Base64Utils.encodeToString(bytes);
                String filename = resource.getFilename();
                imgMap.put(filename, string);
            }
        } catch (Exception var11) {
            var11.printStackTrace();
        }

        return imgMap;
    }
}

