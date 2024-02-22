package com.syc.common.domain;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.syc.common.enums.BizCodeEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author xq su
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R setData(Object data) {
        put("data", data);
        return this;
    }

    /**
     * 逆转
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getData(String key, Class<T> clazz) {
        //默认是map
        Object data = get(key);
        String s = JSONUtil.toJsonStr(data);
        return JSONUtil.toBean(s, clazz);
    }

    /**
     * 逆转
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getData(Class<T> clazz) {
        Object data = get("data");
        String s = JSONUtil.toJsonStr(data);
        return JSONUtil.toBean(s, clazz);
    }


    public R() {
        put("code", 200);
        put("msg", "success");
    }

    public static R error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R error(BizCodeEnum bizCodeEnum) {
        R r = new R();
        r.put("code", bizCodeEnum.getCode());
        r.put("msg", bizCodeEnum.getMsg());
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Object data) {
        R r = new R();
        r.put("data", data);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public R put(Object value) {
        super.put("data", value);
        return this;
    }

    public R put(PageResult pageResult) {
        super.put("rows", pageResult.getRows());
        super.put("total", pageResult.getTotal());
        return this;
    }

    public R put(IPage page) {
        super.put("rows", page.getRecords());
        super.put("total", page.getTotal());
        return this;
    }


    public Integer getCode() {
        return (Integer) this.get("code");
    }
}
