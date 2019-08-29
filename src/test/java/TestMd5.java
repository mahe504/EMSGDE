import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

/**
 * @Author 马鹤
 * @Date 2019/7/5--
 * @Description
 **/
public class TestMd5 {
/*
*   md5测试方法
* */
    @Test
    public static void main(String[] aa) {
        String hashAlgorithName = "MD5";
        String password = "1";
        int hashIterations = 1024;
        ByteSource credentialsSalt = ByteSource.Util.bytes("super");
        Object obj = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations);
        System.out.println(obj);
    }
}