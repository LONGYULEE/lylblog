package com.liyulong.blog.main.common.util;

public class QiNiuUtil{
    /**
     * 上传本地文件
     * @param localFilePath 本地文件完整路径
     * @param key 文件云端存储的名称
     * @param override 是否覆盖同名同位置文件
     * @return
     */
//    public static boolean upload(String localFilePath, String key, boolean override){
//        //构造一个带指定 Region 对象的配置类
//        Configuration cfg = new Configuration(Region.region2());
//        UploadManager uploadManager = new UploadManager(cfg);
//        //...生成上传凭证，然后准备上传
//        String accessKey = "HH52UeWrsvYD2Ktlrms2VkqQVXwAEpbDlgaFMYLC";
//        String secretKey = "s2VhE_C0XFEyRcncTejjaSNZM2EPkoY5e3aaF6-_";
//        String bucket = "lihanlumyblog";
//        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
//        String localFilePath = "/home/qiniu/test.png";
////默认不指定key的情况下，以文件内容的hash值作为文件名
//        String key = null;
//        Auth auth = Auth.create(accessKey, secretKey);
//        String upToken = auth.uploadToken(bucket);
//        try {
//            Response response = uploadManager.put(localFilePath, key, upToken);
//            //解析上传成功的结果
//            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//            System.out.println(putRet.key);
//            System.out.println(putRet.hash);
//        } catch (QiniuException ex) {
//            Response r = ex.response;
//            System.err.println(r.toString());
//            try {
//                System.err.println(r.bodyString());
//            } catch (QiniuException ex2) {
//                //ignore
//            }
//        }
//    }
}