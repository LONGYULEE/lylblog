package com.liyulong.blog.main.common.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class QiNiuUtil{

    private static final String ACCESSKEY = "HH52UeWrsvYD2Ktlrms2VkqQVXwAEpbDlgaFMYLC";
    private static final String SECRETKEY = "s2VhE_C0XFEyRcncTejjaSNZM2EPkoY5e3aaF6-_";
    private static final String BUCKET = "lihanlumyblog";
    private static final Long EXPIRETIME = -1L;

    /**
     * 上传本地文件
     * @param localFilePath 本地文件完整路径
     * @param cloudFileName 文件云端存储的名称
     * @param override 是否覆盖同名同位置文件
     * @return
     */
    public static boolean upload(String localFilePath, String cloudFileName, boolean override){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
//        String accessKey = "HH52UeWrsvYD2Ktlrms2VkqQVXwAEpbDlgaFMYLC";
//        String secretKey = "s2VhE_C0XFEyRcncTejjaSNZM2EPkoY5e3aaF6-_";
//        String bucket = "lihanlumyblog";
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        //String localFilePath = "/home/qiniu/test.png";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        //String key = null;
        Auth auth = Auth.create(ACCESSKEY, SECRETKEY);
        //String upToken = auth.uploadToken(bucket);
        String upToken;
        if(override){
            upToken = auth.uploadToken(BUCKET, cloudFileName);//覆盖上传凭证
        }else{
            upToken = auth.uploadToken(BUCKET);
        }
        try {
            Response response = uploadManager.put(localFilePath, cloudFileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            //System.out.println(putRet.key);
            //System.out.println(putRet.hash);
            return true;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
                return false;
            } catch (QiniuException ex2) {
                //ignore
                return false;
            }
        }
    }

    /**
     * 获取文件访问地址
     * @param cloudFileName 文件云端存储的名称
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String fileUrl(String cloudFileName) throws UnsupportedEncodingException {
        String encodedFileName = URLEncoder.encode(cloudFileName, "utf-8");
        String publicUrl = String.format("%s/%s", BUCKET, encodedFileName);
        Auth auth = Auth.create(ACCESSKEY, SECRETKEY);
        long expireInSeconds = EXPIRETIME;
        if(-1 == expireInSeconds){
            return auth.privateDownloadUrl(publicUrl);
        }
        return auth.privateDownloadUrl(publicUrl, expireInSeconds);
    }

    /**
     * 上传MultipartFile
     * @param file
     * @param key
     * @param override
     * @return
     * @throws IOException
     */
//    public static boolean uploadMultipartFile(MultipartFile file, String key, boolean override) {
//        //构造一个带指定Zone对象的配置类
//        Configuration cfg = new Configuration(QiNiuConfig.getInstance().getZone());
//        //...其他参数参考类注释
//        UploadManager uploadManager = new UploadManager(cfg);
//
//        //把文件转化为字节数组
//        InputStream is = null;
//        ByteArrayOutputStream bos = null;
//
//        try {
//            is = file.getInputStream();
//            bos = new ByteArrayOutputStream();
//            byte[] b = new byte[1024];
//            int len = -1;
//            while ((len = is.read(b)) != -1){
//                bos.write(b, 0, len);
//            }
//            byte[] uploadBytes= bos.toByteArray();
//
//            Auth auth = getAuth();
//            String upToken;
//            if(override){
//                upToken = auth.uploadToken(QiNiuConfig.getInstance().getBucket(), key);//覆盖上传凭证
//            }else{
//                upToken = auth.uploadToken(QiNiuConfig.getInstance().getBucket());
//            }
//            //默认上传接口回复对象
//            DefaultPutRet putRet;
//            //进行上传操作，传入文件的字节数组，文件名，上传空间，得到回复对象
//            Response response = uploadManager.put(uploadBytes, key, upToken);
//            putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//            System.out.println(putRet.key);//key 文件名
//            System.out.println(putRet.hash);//hash 七牛返回的文件存储的地址，可以使用这个地址加七牛给你提供的前缀访问到这个视频。
//            return true;
//        }catch (QiniuException e){
//            e.printStackTrace();
//            return false;
//        }catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

}