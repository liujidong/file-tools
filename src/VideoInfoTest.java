import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

public class VideoInfoTest {
	public static void main(String[] args) {
		String dir = "D:\\WeChat_Files\\WeChat Files\\wxid_f5muak2c9z8b22\\FileStorage\\Video\\";
		List<String> lines = new ArrayList<String>();
		try {
			FileReader fr = new FileReader(dir+"list1.txt");
			BufferedReader bf = new BufferedReader(fr);
			String line;
			while((line = bf.readLine()) != null) {
				lines.add(line);
			}
			bf.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String filePath;
		for (String line : lines) {
			if(line.length()==0) {continue;}
			//System.out.println(line);
			filePath = line.replaceAll("#*file\\s+", Matcher.quoteReplacement(dir));
			//System.out.println(filePath);
			File source = new File(filePath);
			Encoder encoder = new Encoder();
			try {
				MultimediaInfo m = encoder.getInfo(source);
				int height = m.getVideo().getSize().getHeight();
				int width = m.getVideo().getSize().getWidth();
				
				long duration = m.getDuration() / 1000;
				System.out.println(line + " "+width+"x"+height+" "+duration+"s");
			}catch (Exception e) {
				System.out.println("error at "+line);
				e.printStackTrace();
			}
			
		}
	}
}
