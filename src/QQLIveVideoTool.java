import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 腾讯视频缓存视频合成处理
 * 路径：此电脑\Honor V10\存储卡\Android\data\com.tencent.qqlive\files\videos_Lb0rS
 */
public class QQLIveVideoTool {
    public static void main(String[] args) throws IOException {
        //--------------文件夹预处理
        File dir = new File("E:\\videos_Lb0rS");
        File fileDir = new File(dir,"files");
        if(!fileDir.exists()){
            fileDir.mkdir();
        }
        File dirOut = new File(dir,"out");
        if(!dirOut.exists()) {
            dirOut.mkdir();
        }
        //生成file配置文件
        genFiles(dir,fileDir);
        //-------------生成bat脚本----------------------
        StringBuilder sbBat = new StringBuilder();
        for(File file:fileDir.listFiles()){
            sbBat.append("ffmpeg -f concat -safe 0 -i ").append(file.getAbsolutePath()).append(" -c copy out/"+file.getName().replace(".txt",".mp4\r\n"));
        }
        Files.write(Paths.get(dir.getAbsolutePath(), "auto.bat"), sbBat.toString().getBytes());
    }
    private static void genFiles(File dir,File fileDir) throws IOException{
        //---------------读取写入文件--------------------
        for(File dir1 : dir.listFiles()){
            if(dir1.isFile()){continue;}
            if(List.of("files","out").contains(dir1.getName())){
                continue;
            }
            StringBuffer bfTxt = new StringBuffer();
            for(File dir2: dir1.listFiles()){
                if(dir2.isFile()){continue;}
                System.out.println(dir2.getAbsolutePath());
                List<Integer> nums = new ArrayList<>();
                for(File ts:dir2.listFiles()){
                    if(ts.getName().endsWith(".ts")){
                        nums.add(Integer.parseInt(ts.getName().replace(".ts","")));
                    }
                }
                Collections.sort(nums);
                for(Integer num: nums){
                    //System.out.println(num);
                    bfTxt.append("file '").append(dir2.getAbsolutePath()).append(File.separator).append(num).append(".ts'\r\n");
                }
            }
            System.out.println(bfTxt);
            Path path = Paths.get(fileDir.getAbsolutePath(), dir1.getName() + ".txt");
            if(bfTxt.length()>0 && !path.toFile().exists()) { //输出file文件
                Files.write(path, bfTxt.toString().getBytes());
            }else{
                System.out.println("dir is empty! or file is exists!");
            }
        }
    }
}
