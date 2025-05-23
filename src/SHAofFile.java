import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;

/**
 * SHA加密工具类
 * @author Administrator
 */
public class SHAofFile {

	public static void main(String[] args) throws Exception{
		if(args.length < 2){
			System.err.println("Usage:SHAofFile SHA-n file");
			System.exit(-1);
		}
		//hashType  加密类型 （MD5 和 SHA）
		byte[] mds= MessageDigest.getInstance(args[0]).digest(Files.readAllBytes(Paths.get(args[1])));
        StringBuffer strHexString = new StringBuffer();  
        // 遍歷 byte buffer  
        for (int i = 0; i < mds.length; i++)  
        {  
          String hex = Integer.toHexString(0xff & mds[i]);  
          if (hex.length() == 1)  
          {  
            strHexString.append('0');  
          }  
          strHexString.append(hex);  
        } 
        System.out.println(strHexString);
	}

}
