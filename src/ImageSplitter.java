import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * @Author 15833
 * @Desc
 * @Date 2025/5/19-21:47
 * @Copyright Uniorange
 */
public class ImageSplitter {
    /**
     * 分割图片为多个部分
     *
     * @param inputImagePath 输入图片路径
     * @param outputFolder   输出文件夹路径
     * @param splitCount     分割数量 (水平)
     * @param overlapRate    重叠率 [0.0, 1.0]
     * @return 分割后的图片文件列表
     */
    public static List<File> splitImage(String inputImagePath, String outputFolder, int splitCount, double overlapRate) throws IOException {
        File inputImageFile = new File(inputImagePath);
        BufferedImage originalImage = ImageIO.read(inputImageFile);
        // 旋转图像90度（可替换为任意角度）
        //originalImage = rotateImage90(originalImage);
        int imageWidth = originalImage.getWidth();
        int imageHeight = originalImage.getHeight();

        // 计算每个分割区域的大小
        int segmentWidth = imageWidth / splitCount;
        int overlapWidth = (int) (segmentWidth * overlapRate);

        List<File> outputFiles = new ArrayList<>();

        for (int i = 0; i < splitCount; i++) {
            int x = i * (segmentWidth - overlapWidth);
            int width = (i == splitCount - 1) ? imageWidth - x : segmentWidth;

            BufferedImage subImage = originalImage.getSubimage(x, 0, width, imageHeight);
            File outputFile = new File(outputFolder, "segment_" + i + ".png");
            ImageIO.write(subImage, "png", outputFile);
            outputFiles.add(outputFile);
        }

        return outputFiles;
    }
    /**
     * 分割图片为多个部分
     *
     * @param inputImagePath 输入图片路径
     * @param outputFolder   输出文件夹路径
     * @param splitCount     分割数量 (垂直)
     * @param overlapRate    重叠率 [0.0, 1.0]
     * @return 分割后的图片文件列表
     */
    public static List<File> splitImage2(String inputImagePath, String outputFolder, int splitCount, double overlapRate) throws IOException {
        File inputImageFile = new File(inputImagePath);
        BufferedImage originalImage = ImageIO.read(inputImageFile);
        // 旋转图像90度（可替换为任意角度）
        //originalImage = rotateImage90(originalImage);
        int imageWidth = originalImage.getWidth();
        int imageHeight = originalImage.getHeight();

        // 计算每个分割区域的大小
        int segmentHeight = imageHeight / splitCount;
        int overlapHeight = (int) (segmentHeight * overlapRate);

        List<File> outputFiles = new ArrayList<>();

        for (int i = 0; i < splitCount; i++) {
            int y = i * (segmentHeight - overlapHeight);
            //int height = (i == splitCount - 1) ? imageHeight - y : segmentHeight;
            int height = segmentHeight+overlapHeight;

            BufferedImage subImage = originalImage.getSubimage(0, y, imageWidth, height);
            File outputFile = new File(outputFolder, "segment_" + i + ".png");
            ImageIO.write(subImage, "png", outputFile);
            outputFiles.add(outputFile);
        }

        return outputFiles;
    }
    public static void main(String[] args) {
        try {
            String inputImagePath = "E:\\share\\全景素材\\IMG_20250429_153210.jpg";
            String outputFolder = inputImagePath.substring(0,inputImagePath.lastIndexOf("."));
            File outputFolderFile = new File(outputFolder);
            if(outputFolderFile.exists()==false){
                outputFolderFile.mkdir();
            }
            int splitCount = 3; // 水平分割成 4 部分
            double overlapRate = 0.5; // 20% 重合率

            List<File> result = splitImage2(inputImagePath, outputFolder, splitCount, overlapRate);
            System.out.println("分割完成，输出文件: ");
            for (File file : result) {
                System.out.println(file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
