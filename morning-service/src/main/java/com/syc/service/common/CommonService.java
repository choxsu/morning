package com.syc.service.common;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class CommonService {

    /**
     * 七牛云上传demo
     * @param args
     */
    public static void main(String[] args) {
        //构造一个带指定Zone对象的配置类，
        /*
        华东	Zone.zone0()
        华北	Zone.zone1()
        华南	Zone.zone2()
        北美	Zone.zoneNa0()
        东南亚	Zone.zoneAs0()
         */
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);

        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "C:\\Users\\Administrator\\Desktop\\inter-cpu.png";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "IMG-" + System.currentTimeMillis();
        String accessKey = "access key";
        String secretKey = "secret key";
        String bucket = "bucket name";

        Auth auth = Auth.create(accessKey, secretKey);
        StringMap stringMap = new StringMap();
        //stringMap.put("callbackUrl", "http://api.example.com/qiniu/upload/callback");
        stringMap.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        long expireSeconds = 3600;
        String upToken = auth.uploadToken(bucket, null, expireSeconds, stringMap);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            System.out.println(response.bodyString());
            //解析上传成功的结果
//            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//            System.out.println(putRet.key);
//            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }


}
