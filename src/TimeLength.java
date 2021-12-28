import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Dialogue: 0,1:23:42.10,1:23:44.46,Default,,0000,0000,0000,,*我们是正版文章，在这里说
 * Dialogue: 0,1:23:44.46,1:23:46.56,Default,,0000,0000,0000,,*为政府而努力，而不是行之有效
 * @author USER
 * 2021年12月28日
 */
public class TimeLength {

	public static void main(String[] args) throws Exception{
		if(args.length==0) {
			System.out.println("Usage:TimeLength filePath");
			System.exit(0);
		}
		FileReader fr = new FileReader(args[0]);
		BufferedReader bf = new BufferedReader(fr);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		Date d1,d2;
		String line;
		long diff;
		while((line=bf.readLine()) != null) {
			String[] parts = line.split(",");
			d1 = sdf.parse(parts[1]+"0");
			d2 = sdf.parse(parts[2]+"0");
			diff = d2.getTime()-d1.getTime();
			System.out.print(diff/1000.0+"	");	
			for(int i=0;i<diff/1000;i++) {
				System.out.print("|");
			}
			int msint = (int)Math.rint(diff%1000/100.0);
			for(int i = 0;i<msint;i++) {
				System.out.print(".");
			}
			System.out.println();
		}
		bf.close();
		fr.close();
	}

}
