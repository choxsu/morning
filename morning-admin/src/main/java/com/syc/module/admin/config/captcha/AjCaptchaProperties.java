package com.syc.module.admin.config.captcha;

import com.xingyuv.captcha.model.common.CaptchaTypeEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xq.su
 * created on 2024/2/29
 */
@ConfigurationProperties("aj.captcha")
public class AjCaptchaProperties {
    public static final String PREFIX = "aj.captcha";
    private CaptchaTypeEnum type;
    private String jigsaw;
    private String picClick;
    private String waterMark;
    private String waterFont;
    private String fontType;
    private String slipOffset;
    private Boolean aesStatus;
    private String interferenceOptions;
    private String cacheNumber;
    private String timingClear;
    private AjCaptchaProperties.StorageType cacheType;
    private boolean historyDataClearEnable;
    private boolean reqFrequencyLimitEnable;
    private int reqGetLockLimit;
    private int reqGetLockSeconds;
    private int reqGetMinuteLimit;
    private int reqCheckMinuteLimit;
    private int reqVerifyMinuteLimit;
    private int fontStyle;
    private int fontSize;
    private int clickWordCount;

    public AjCaptchaProperties() {
        this.type = CaptchaTypeEnum.DEFAULT;
        this.jigsaw = "";
        this.picClick = "";
        this.waterMark = "captcha-plus";
        this.waterFont = "WenQuanZhengHei.ttf";
        this.fontType = "WenQuanZhengHei.ttf";
        this.slipOffset = "5";
        this.aesStatus = true;
        this.interferenceOptions = "0";
        this.cacheNumber = "1000";
        this.timingClear = "180";
        this.cacheType = AjCaptchaProperties.StorageType.local;
        this.historyDataClearEnable = false;
        this.reqFrequencyLimitEnable = false;
        this.reqGetLockLimit = 5;
        this.reqGetLockSeconds = 300;
        this.reqGetMinuteLimit = 100;
        this.reqCheckMinuteLimit = 100;
        this.reqVerifyMinuteLimit = 100;
        this.fontStyle = 1;
        this.fontSize = 25;
        this.clickWordCount = 4;
    }

    public int getFontStyle() {
        return this.fontStyle;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }

    public int getFontSize() {
        return this.fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getClickWordCount() {
        return this.clickWordCount;
    }

    public void setClickWordCount(int clickWordCount) {
        this.clickWordCount = clickWordCount;
    }

    public boolean isHistoryDataClearEnable() {
        return this.historyDataClearEnable;
    }

    public void setHistoryDataClearEnable(boolean historyDataClearEnable) {
        this.historyDataClearEnable = historyDataClearEnable;
    }

    public boolean isReqFrequencyLimitEnable() {
        return this.reqFrequencyLimitEnable;
    }

    public boolean getReqFrequencyLimitEnable() {
        return this.reqFrequencyLimitEnable;
    }

    public void setReqFrequencyLimitEnable(boolean reqFrequencyLimitEnable) {
        this.reqFrequencyLimitEnable = reqFrequencyLimitEnable;
    }

    public int getReqGetLockLimit() {
        return this.reqGetLockLimit;
    }

    public void setReqGetLockLimit(int reqGetLockLimit) {
        this.reqGetLockLimit = reqGetLockLimit;
    }

    public int getReqGetLockSeconds() {
        return this.reqGetLockSeconds;
    }

    public void setReqGetLockSeconds(int reqGetLockSeconds) {
        this.reqGetLockSeconds = reqGetLockSeconds;
    }

    public int getReqGetMinuteLimit() {
        return this.reqGetMinuteLimit;
    }

    public void setReqGetMinuteLimit(int reqGetMinuteLimit) {
        this.reqGetMinuteLimit = reqGetMinuteLimit;
    }

    public int getReqCheckMinuteLimit() {
        return this.reqGetMinuteLimit;
    }

    public void setReqCheckMinuteLimit(int reqCheckMinuteLimit) {
        this.reqCheckMinuteLimit = reqCheckMinuteLimit;
    }

    public int getReqVerifyMinuteLimit() {
        return this.reqVerifyMinuteLimit;
    }

    public void setReqVerifyMinuteLimit(int reqVerifyMinuteLimit) {
        this.reqVerifyMinuteLimit = reqVerifyMinuteLimit;
    }

    public static String getPrefix() {
        return "aj.captcha";
    }

    public CaptchaTypeEnum getType() {
        return this.type;
    }

    public void setType(CaptchaTypeEnum type) {
        this.type = type;
    }

    public String getJigsaw() {
        return this.jigsaw;
    }

    public void setJigsaw(String jigsaw) {
        this.jigsaw = jigsaw;
    }

    public String getPicClick() {
        return this.picClick;
    }

    public void setPicClick(String picClick) {
        this.picClick = picClick;
    }

    public String getWaterMark() {
        return this.waterMark;
    }

    public void setWaterMark(String waterMark) {
        this.waterMark = waterMark;
    }

    public String getWaterFont() {
        return this.waterFont;
    }

    public void setWaterFont(String waterFont) {
        this.waterFont = waterFont;
    }

    public String getFontType() {
        return this.fontType;
    }

    public void setFontType(String fontType) {
        this.fontType = fontType;
    }

    public String getSlipOffset() {
        return this.slipOffset;
    }

    public void setSlipOffset(String slipOffset) {
        this.slipOffset = slipOffset;
    }

    public Boolean getAesStatus() {
        return this.aesStatus;
    }

    public void setAesStatus(Boolean aesStatus) {
        this.aesStatus = aesStatus;
    }

    public AjCaptchaProperties.StorageType getCacheType() {
        return this.cacheType;
    }

    public void setCacheType(AjCaptchaProperties.StorageType cacheType) {
        this.cacheType = cacheType;
    }

    public String getInterferenceOptions() {
        return this.interferenceOptions;
    }

    public void setInterferenceOptions(String interferenceOptions) {
        this.interferenceOptions = interferenceOptions;
    }

    public String getCacheNumber() {
        return this.cacheNumber;
    }

    public void setCacheNumber(String cacheNumber) {
        this.cacheNumber = cacheNumber;
    }

    public String getTimingClear() {
        return this.timingClear;
    }

    public void setTimingClear(String timingClear) {
        this.timingClear = timingClear;
    }

    @Override
    public String toString() {
        return "\nAjCaptchaProperties{type=" + this.type + ", jigsaw='" + this.jigsaw + '\'' + ", picClick='" + this.picClick + '\'' + ", waterMark='" + this.waterMark + '\'' + ", waterFont='" + this.waterFont + '\'' + ", fontType='" + this.fontType + '\'' + ", slipOffset='" + this.slipOffset + '\'' + ", aesStatus=" + this.aesStatus + ", interferenceOptions='" + this.interferenceOptions + '\'' + ", cacheNumber='" + this.cacheNumber + '\'' + ", timingClear='" + this.timingClear + '\'' + ", cacheType=" + this.cacheType + ", reqFrequencyLimitEnable=" + this.reqFrequencyLimitEnable + ", reqGetLockLimit=" + this.reqGetLockLimit + ", reqGetLockSeconds=" + this.reqGetLockSeconds + ", reqGetMinuteLimit=" + this.reqGetMinuteLimit + ", reqCheckMinuteLimit=" + this.reqCheckMinuteLimit + ", reqVerifyMinuteLimit=" + this.reqVerifyMinuteLimit + '}';
    }

    public static enum StorageType {
        local,
        redis,
        other;

        private StorageType() {
        }
    }
}



