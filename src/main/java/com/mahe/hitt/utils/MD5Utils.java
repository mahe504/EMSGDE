package com.mahe.hitt.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Author 马鹤
 * @Date 2019/7/7--20:43
 * @Description 设置MD5
 **/
public class MD5Utils {
    public static String getMD5(String source , String salt , int hashIterations){
        String hashAlgorithName = "MD5";
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        String obj = String.valueOf(new SimpleHash(hashAlgorithName, source, credentialsSalt, hashIterations));
        return obj;
    }

}
