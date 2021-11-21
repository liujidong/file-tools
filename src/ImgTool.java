import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImgTool {
	public static void main(String[] args) throws Exception{
		//imageSub();
		imgBG("C:/Users/USER/Pictures/PANO/todo");
		//imgWidth("C:\\Users\\USER\\Pictures\\PANO\\PANO_20211001_083357.jpg", 45);
		//imgWidth("C:\\Users\\USER\\Pictures\\PANO\\PANO_20211001_083302.jpg", 45);
	}
	//剪切图片
	public static void imageSub(){
		for (int i = 2795; i <= 2825; i++) {
			File f = new File("C:\\Users\\USER\\Pictures\\Screenshots\\屏幕截图("+i+").png");
			try {
				String fileName = f.getName();
				String extension=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
				String name = fileName.substring(0,fileName.lastIndexOf("."));		
				System.out.println(fileName);
//			System.out.println(name);
//			System.out.println(extension);
				//if(true){ return;}
				BufferedImage bufImage = ImageIO.read(f);
				BufferedImage subImage = bufImage.getSubimage (393,163, 1467, 829);

				File fo = new File(f.getParent(),name+".sub."+extension);
				ImageIO.write(subImage, extension, fo);
			} catch (IOException e) {
				System.err.println("error at:"+f.getAbsolutePath());
				//e.printStackTrace();
			}				
		}

	}
	//补全背景(补全为2：1的全景)
	public static void imgBG(String dirPath) throws IOException{
		 /** 
         * 要处理的图片目录 
         */ 
        File dir = new File(dirPath);
        /** 
         * 列出目录中的图片，得到数组 
         */ 
        File[] files = dir.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				//return Arrays.asList(".png",".jpg").contains(name);
				return name.endsWith(".jpg") || name.endsWith(".png");
			}
		});
        //创建输出目录
        File dirOut = new File(dir,"out");        
        if(dirOut.exists()==false&& files.length>0) {
        	dirOut.mkdir();
        }
        /** 
         * 遍历数组 
         */ 
        for(int x=0;x<files.length;x++){
	         /** 
	          * 定义一个RGB的数组，因为图片的RGB模式是由三个 0-255来表示的 比如白色就是(255,255,255) 
	          */ 
	         int[] rgb = new int[3]; 
	         /** 
	          * 用来处理图片的缓冲流 
	          */ 
	         BufferedImage bi = null; 
	         try { 
	          /** 
	           * 用ImageIO将图片读入到缓冲中 
	           */ 
	          bi = ImageIO.read(files[x]); 
	         } catch (Exception e) { 
	          e.printStackTrace(); 
	         } 
	         /** 
	          * 得到图片的长宽 
	          */ 
	         int width = bi.getWidth(); 
	         int height = bi.getHeight();
	         if(width/height<=2) {
	        	 System.out.println("w:h is ["+width/height+":1]	"+files[x].getName());
	        	 continue;
	        }
	         if(width/height<4) {
	        	 System.out.println("w:h is "+width/height+":1	"+files[x].getName());
	         }
//	         int minx = bi.getMinX(); 
//	         int miny = bi.getMinY(); 
	         String fileName = files[x].getName();
	         System.out.println("正在处理："+fileName);
	         //System.out.println(width+","+minx+","+miny);
	         /** 
	            * 这里是遍历图片的像素，因为要处理图片的背色，所以要把指定像素上的颜色换成目标颜色 
	            * 这里 是一个二层循环，遍历长和宽上的每个像素 
	            */
//	           for (int i = minx; i < width; i++) { 
//	            for (int j = miny; j < height; j++) { 
//	             // System.out.print(bi.getRGB(jw, ih)); 
//	             /** 
//	              * 得到指定像素（i,j)上的RGB值， 
//	              */ 
//	             int pixel = bi.getRGB(i, j); 
//	             /** 
//	              * 分别进行位操作得到 r g b上的值 
//	              */ 
//	             rgb[0] = (pixel & 0xff0000) >> 16; 
//	             rgb[1] = (pixel & 0xff00) >> 8; 
//	             rgb[2] = (pixel & 0xff); 
//	             /** 
//	              * 进行换色操作，我这里是要把蓝底换成白底，那么就判断图片中rgb值是否在蓝色范围的像素 
//	              */ 
//	            // if(rgb[0]<140&&rgb[0]>100 && rgb[1]<80&&rgb[1]>50 && rgb[2]<170&&rgb[2]>150 ){ 
//	             if(rgb[0]<256&&rgb[0]>230 && rgb[1]<256&&rgb[1]>230 && rgb[2]<256&&rgb[2]>230 ){ 
//	              /** 
//	               * 这里是判断通过，则把该像素换成白色 
//	               */ 
//	              bi.setRGB(i, j, 0x000000); 
//	             } 
//	              if(rgb[0]<140&&rgb[0]>100 && rgb[1]<80&&rgb[1]>50 && rgb[2]<170&&rgb[2]>150 ){ 
//	              /** 
//	               * 这里是判断通过，则把该像素换成白色 
//	               */ 
//	              bi.setRGB(i, j, 0xffffff); 
//	             } 
//	              
//	            } 
//	           } 
	         if(width % 2 > 0 ) {width++;};
	       BufferedImage outImage =  new BufferedImage(width, width/2,BufferedImage.TYPE_INT_RGB);// 1.创建空白图片
	       Graphics2D graphics2D = outImage.createGraphics();// 2.获取图片画笔
//	       outImage = graphics2D.getDeviceConfiguration().createCompatibleImage(width, width/2, Transparency.TRANSLUCENT);
//	       		graphics2D.dispose();
//	       graphics2D = outImage.createGraphics();

	       // 设置图片居中显示
	       graphics2D.drawImage(bi, 0,(width/2 - height) / 2, null);
	       graphics2D.dispose();
	       //生成新的图片
	       String extension=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
	       //String fname = fileName.substring(0,fileName.lastIndexOf("."));
	       //ImageIO.write(outImage, extension, new File(dir,fname+"_720."+extension));
	       ImageIO.write(outImage, extension, new File(dirOut,fileName));
         
	           System.out.println("\t处理完毕："+fileName); 
	           System.out.println(); 
	           /** 
	            * 将缓冲对象保存到新文件中 
	            */ 
//	           FileOutputStream ops = new FileOutputStream(new File("d:/test/11/"+x+".jpg")); 
//	           ImageIO.write(bi,"jpg", ops); 
//	           ops.flush(); 
//	           ops.close(); 	         
        }
	}
	//通过角度拓展图片宽度（0,360）
    public static void imgWidth(String filePath,int degree) throws IOException{
    	if(degree<=0||degree>=360) {
    		System.out.println("angle mast in (0,360)");
    		System.exit(0);
    	}
    	File file = new File(filePath);
        BufferedImage bi = ImageIO.read(file); 
        int width = bi.getWidth(); 
        int widthNew = 360*width/(360-degree);
        System.out.println(widthNew);
        int height = bi.getHeight();
       BufferedImage outImage =  new BufferedImage(widthNew, height,BufferedImage.TYPE_INT_RGB);// 1.创建空白图片
       Graphics2D graphics2D = outImage.createGraphics();// 2.获取图片画笔 
       // 设置图片位置
       graphics2D.drawImage(bi, 0,0, null);
       graphics2D.dispose();
       //生成新的图片
       String extension=filePath.substring(filePath.lastIndexOf(".")+1,filePath.length()); 
       //创建输出目录
       File dirOut = new File(file.getParent(),"out");        
       if(dirOut.exists()==false) {
       	dirOut.mkdir();
       }
       String fileName = file.getName();
       ImageIO.write(outImage, extension, new File(dirOut,fileName));
       
       System.out.println("\t处理完毕："+fileName); 
       System.out.println(); 

    }
}
