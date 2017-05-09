package com.origin.common.plugin;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
/**
 * 生成二维码
 * @author Eric
 *
 */
public class QRCode4J {
	private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;
    // 二维码写码器  
    private static MultiFormatWriter mutiWriter = new MultiFormatWriter();
    /**
     * 生成二维码缓冲图
     * @param content 二维码内容
     * @param width 二维码宽度
     * @param height 二维码高度
     * @param minWhite 空白最小单位大小(若不需要空白,请填0)
     * @return
     * @throws WriterException
     */
    public static BufferedImage toBufferedImage(String content,Integer width,Integer height,int minWhite) throws WriterException{
    	Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   // 内容所使用字符集编码 
		BitMatrix bitMatrix = mutiWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
    	return toBufferedImage(bitMatrix,minWhite);
    }
    /**
     * 生成二维码图片缓存
     * @param matrix 二维码位阵
     * @param minWhite 空白最小单位大小(若不需要空白,请填0)
     * @return
     */
    public static BufferedImage toBufferedImage(BitMatrix matrix,int minWhite) {
    	matrix = deleteWhite(matrix,minWhite);
        int width = matrix.getWidth();  
        int height = matrix.getHeight();  
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {  
            for (int y = 0; y < height; y++) {  
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);  
            }  
        }  
        return image;  
    }  
    /**
     * 将二维码图片写到指定文件
     * @param matrix 二维码位阵
     * @param format 文件后缀(注意:不能带".")
     * @param file 需要存放的文件对象
     * @param minWhite 空白最小单位大小(若不需要空白,请填0)
     * @throws IOException
     */
    public static void writeToFile(BitMatrix matrix, String format, File file,int minWhite) throws IOException {  
        BufferedImage image = toBufferedImage(matrix,minWhite);  
        if (!ImageIO.write(image, format, file)) {  
            throw new IOException("Could not write an image of format " + format + " to " + file);  
        }  
    }  
    /**
     * 将二维码写入流
     * @param matrix 二维码位阵
     * @param format 图片后缀
     * @param stream 流对象
     * @param minWhite 空白最小单位大小(若不需要空白,请填0)
     * @throws IOException
     */
    public static void writeToStream(BitMatrix matrix, String format, OutputStream stream,int minWhite) throws IOException {  
        BufferedImage image = toBufferedImage(matrix,minWhite);  
        if (!ImageIO.write(image, format, stream)) {  
            throw new IOException("Could not write an image of format " + format);  
        }  
    }
    /**
     * 删除二维码空白区域
     * @param matrix 二维码位阵
     * @param whiteMin 最小空白单位(若不需要则填0)
     * @return
     */
    private static BitMatrix deleteWhite(BitMatrix matrix,int whiteMin) {
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1+(2*whiteMin);
        int resHeight = rec[3] + 1+(2*whiteMin);

        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (matrix.get(i + rec[0]-whiteMin, j + rec[1]-whiteMin))
                    resMatrix.set(i, j);
            }
        }
        return resMatrix;
    }
    public static void main(String[] args) {
    	 try {
			String text = "老宁是狗，汪汪汪"; // 二维码内容  
			 int width = 200; // 二维码图片宽度  
			 int height = 200; // 二维码图片高度  
			 String format = "gif";// 二维码的图片格式  
			   
			 Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  
			 hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   // 内容所使用字符集编码  
			   
			 BitMatrix bitMatrix = mutiWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
			 // 生成二维码  
			 File outputFile = new File("d:/new1.gif");  
			 QRCode4J.writeToFile(bitMatrix,format,outputFile,10);
			 
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
