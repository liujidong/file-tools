import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class BufferedImageUtil {
    public static void main(String[] args) throws Exception{
        BufferedImage img =  ImageIO.read(new File("D:\\data\\PANO\\out\\PANO_20220128_154x.jpg"));
        long rs = 0,gs=0,bs=0;
        int width = img.getWidth();
        for(int y = 0; y < img.getHeight(); y++) {
            for(int x = 0; x < img.getWidth(); x++) {
                int pixel = img.getRGB(x, y);//获取颜色
                int alpha = pixel >> 24 & 0xff;//获取alpha
                int red = pixel & 0xff0000 >> 16;//获取红色
                int green = pixel & 0xff00 >> 8;//获取绿色
                int blue = pixel & 0xff;//获取蓝色
                //System.out.print(img.getRGB(x,y)+" ");
                if(red<0 || green < 0 || blue<0){
                    System.out.printf("(%d,%d,%d,%d)",red,green,blue,alpha);//(255,255,255,0)
                }
                rs += red;
                gs += green;
                bs += blue;
            }
            //System.out.println();
        }
        if(rs<0 || rs < 0 || rs<0) {
            System.out.printf("(%d,%d,%d)\n", rs, gs, bs);
        }
        int r0 = (int)(rs/(img.getHeight()*width));
        int g0 = (int)(gs/(img.getHeight()*width));
        int b0 = (int)(bs/(img.getHeight()*width));
        System.out.printf("(%d,%d,%d)",r0,g0,b0);
//        BufferedImage imgOut =  new BufferedImage(100, 100,BufferedImage.TYPE_INT_RGB);// 1.创建空白图片
//        for(int y = 0; y < imgOut.getHeight(); y++) {
//            for (int x = 0; x < imgOut.getWidth(); x++) {
//                //int color = (0 << 24) | (255 << 16) | (255 << 8) | 255;//将argb还原成整数 16777215
//                //int color = (255 << 16) | (255 << 8) | 255;//将rgb还原成整数
//                //System.out.print(color+" ");
//                imgOut.setRGB(x,y,16777215);
//            }
//        }
//        ImageIO.write(imgOut, "jpg", new File("d:/data/","out.jpg"));
//        System.out.println("write ok");
    }
    public static BufferedImage initBufferedImage(BufferedImage bi){
        long rs = 0,gs=0,bs=0;
        int width = bi.getWidth();
        for(int y = 0; y < bi.getHeight(); y++) {
            for(int x = 0; x < width; x++) {
                int pixel = bi.getRGB(x, y);//获取颜色
                //int alpha = pixel >> 24 & 0xff;//获取alpha
                int red = pixel & 0xff0000 >> 16;//获取红色
                int green = pixel & 0xff00 >> 8;//获取绿色
                int blue = pixel & 0xff;//获取蓝色
                rs += red;
                gs += green;
                bs += blue;
            }
        }
        int r0 = (int)(rs/(bi.getHeight()*width));
        int g0 = (int)(gs/(bi.getHeight()*width));
        int b0 = (int)(bs/(bi.getHeight()*width));
        System.out.printf("(%d,%d,%d)",r0,g0,b0);
        if(width % 2 > 0 ) {width++;};
        BufferedImage imgOut =  new BufferedImage(width, width/2,BufferedImage.TYPE_INT_RGB);// 1.创建空白图片
        for(int y = 0; y < imgOut.getHeight(); y++) {
            for (int x = 0; x < imgOut.getWidth(); x++) {
                int color = (r0 << 16) | (g0 << 8) | b0;//将rgb还原成整数
                imgOut.setRGB(x,y,color);
            }
        }
        return imgOut;
    }
}
