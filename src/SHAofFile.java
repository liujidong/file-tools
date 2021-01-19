import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;

public class SHAofFile {

	public static void main(String[] args) throws Exception{
		if(args.length < 2){
			System.err.println("Usage:SHAofFile SHA-n file");
			System.exit(-1);
		}
		//hashType  �������� ��MD5 �� SHA��
		byte[] mds= MessageDigest.getInstance(args[0]).digest(Files.readAllBytes(Paths.get(args[1])));
        StringBuffer strHexString = new StringBuffer();  
        // ��v byte buffer  
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
