package com.xiaostudy.util;

import com.sun.deploy.util.StringUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.util.StringUtil;
import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * 生成文件缩略图
 *
 * @author 同略研发部_zj
 * @version V1.0
 * @Title: FileThumbnailServiceImpl.java
 * @Package com.frame.filepreview.service.impl
 * @Description: 生成文件缩略图
 * @date 2019-02-18 15:43
 */
public final class PDFUtil {

    private static final String PDF = "pdf";
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1400;
    private static final String OUTPUT_FORMAT = "jpg";

    private static String[] simText = {"txt", "html", "xml", "java", "properties", "sql"};

    private static String[] picture = {"JPG", "JPEG", "PNG", "GIF", "BMP", "ICO", "RAW"};

    private static String[] officetype = {"docx", "doc", "xls", "xlsx", "ppt", "pptx"};

    private PDFUtil(){}

    public static String getThumbnailPath(String filePath, String tempPath) {
    	if(null == filePath || filePath.trim().length() <= 0) {
    		return "文件不存在";
    	}
    	
        File file = new File(filePath);
        if (!file.exists()) {
            return "无效文件";
        }
        
        String suffix = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
        if (Arrays.asList(picture).contains(suffix)) {
            return getImageThumbnailPath(file, tempPath);
        } else if (Arrays.asList(officetype).contains(suffix) || Arrays.asList(simText).contains(suffix)) {
            return getOfficeThumbnailPath(file, tempPath);
        } else if (PDF.equalsIgnoreCase(suffix)) {
            return getPdfThumbnailPath(file, tempPath);
        } else {
            return null;
        }
    }

    
    /*public static String getThumbnailPath(String fileFullPath, String thumbFullPath) {
        	File file = new File(fileFullPath);
	        if (!file.exists()) {
	            return "无效文件";
	        }
	        thumbnail(file, thumbFullPath);
	        return thumbFullPath;
    }*/

    /**
     * 获取office文档缩略图
     *
     * @param file      office格式文件
     * @param tempPath 缩略图存放路径
     * @return
     */
    private static String getOfficeThumbnailPath(File file, String tempPath) {
    	if(null == file || !file.exists()) {
    		return "";
    	}
    	
    	if(null == tempPath || tempPath.trim().length() <= 0) {
            tempPath = "C:" + File.separator + "temp";
        }

        String inputFile = file.getAbsolutePath();
        Boolean b = FileUtil.checkDir(tempPath);
        if(!b) {
            return "";
        }
        String outputFile = ((tempPath.endsWith(File.separator)) ? tempPath : tempPath + File.separator) + file.getName() + "." + PDF;
//        String outputFile = rootPath + file.getName() + "." + PDF;
        /*if (officeToPdf.office2pdf(inputFile, outputFile)) {
            return getPdfThumbnailPath(new File(outputFile), thumbPath, rootPath, true);
        }*/
        
        return "";
    }

    /**
     * 获取pdf缩略图
     *
     * @param file      pdf文件
     * @param tempPath 缩略图存放路径
     * @return
     */
    private static String getPdfThumbnailPath(File file, String tempPath) {
    	if(null == file || !file.exists()) {
    		return "";
    	}
    	
        BufferedImage bufferedImage = pdfToJPG(file);
        
        if(null == bufferedImage) {
        	return "";
        }
        
        if(null == tempPath || tempPath.trim().length() <= 0) {
            tempPath = "C:" + File.separator + "temp";
        }

        String fileName = file.getName() + "." + OUTPUT_FORMAT;
        Boolean b = FileUtil.checkDir(tempPath);
        if(!b) {
            return "";
        }

        String fileNamePath = (tempPath.endsWith(File.separator) ? (tempPath + fileName) : (tempPath + File.separator + fileName));
        thumbnail(bufferedImage, fileNamePath);

        return fileNamePath;
    }

    /**
     * PDF首页转JPG
     *
     * @param file pdf文件
     * @return
     */
    public static BufferedImage pdfToJPG(File file) {
    	if(null == file || !file.exists()) {
    		return null;
    	}
    	
    	int pagen = 0;
    	BufferedImage bufferedImage = null;
        
        try {
            PDDocument doc = PDDocument.load(file);
            if(null != doc) {
            	int pageCount = doc.getNumberOfPages();
            	if(pageCount > 0) {
            		PDFRenderer pdfRenderer = new PDFRenderer(doc);
            		bufferedImage = pdfRenderer.renderImage(pagen);
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return bufferedImage;
    }

    /**
     * 获取图片缩略图
     *
     * @param file      图片文件
     * @param tempPath 缩略图存放路径
     * @return
     */
    private static String getImageThumbnailPath(File file, String tempPath) {
        Boolean b = FileUtil.checkDir(tempPath);
        if(!b) {
            return "";
        }

        String fileName = file.getName() + "." + OUTPUT_FORMAT;
//        thumbnail(file, tempPath + fileName);
        return tempPath + fileName;
    }

    /**
     * 图片压缩成指定格式
     *
     * @param bufferedImage
     * @param thumbPath     缩略图存放路径
     * @return
     */
    private static void thumbnail(BufferedImage bufferedImage, String thumbPath) {
        try {
            //指定大小进行缩放
//            Thumbnails.of(bufferedImage).size(WIDTH, HEIGHT).outputFormat(OUTPUT_FORMAT).toFile(new File(thumbPath));
            //按照比例进行缩放
//            Thumbnails.of(bufferedImage).scale(1).toFile(new File(thumbPath));
            Thumbnails.of(bufferedImage).scale(1f).outputQuality(1f).toFile(thumbPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 首页pdf转图片jpg高清
     * @param strFile
     * @param toStrFile
     * @return
     */
    public static Boolean pdfToJPG(String strFile, String toStrFile) {
        return pdfToPicture(strFile, toStrFile, "jpg", 0);
    }

    /**
     * 首页pdf转图片png高清
     * @param strFile
     * @param toStrFile
     * @return
     */
    public static Boolean pdfToPNG(String strFile, String toStrFile) {
        return pdfToPicture(strFile, toStrFile, "png", 0);
    }

    /**
     * pdf转高清图片
     * @param strFile pdf文件
     * @param toStrFile 存放目标文件夹
     * @param strFormat 图片格式
     * @param integer 第几张
     * @return
     */
    public static Boolean pdfToPicture(String strFile, String toStrFile, String strFormat, Integer integer) {
        if(null == strFile || strFile.trim().length() <= 0 || null == toStrFile || toStrFile.trim().length() <= 0 || null == integer || integer < 0) {
            return false;
        }

        if(!Arrays.asList(picture).contains(strFormat.toUpperCase())) {
            return false;
        }

        File file = new File(strFile);
        if(!file.exists() || !file.isFile()) {
            return false;
        }

        Document document = new Document();
        try {
            document.setFile(strFile);
        } catch (PDFException e) {
            e.printStackTrace();
            return false;
        } catch (PDFSecurityException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if(document.getNumberOfPages() <= integer) {
            return false;
        }

        //缩放比例
        float scale = 8.5f;
        //旋转角度
        float rotation = 0f;
        BufferedImage bufferedImage  = null;
        try {
            bufferedImage = (BufferedImage)document.getPageImage(integer, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
            String toFilePath  = (toStrFile.endsWith(File.separator) ? toStrFile : toStrFile + File.separator) + file.getName().substring(0, file.getName().lastIndexOf(".")) + integer  + "." + strFormat;
            File toFile = new File(toFilePath);
            Boolean b = pdfToPicture(document, toFile, strFormat, integer);
            return b;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * pdf转高清图片
     * @param document pdf文件
     * @param toFile 存放目标文件夹
     * @param strFormat 图片格式
     * @param integer 第几张
     * @return
     */
    public static Boolean pdfToPicture(Document document, File toFile, String strFormat, Integer integer) {
        if(null == document || null == toFile || null == integer || integer < 0) {
            return false;
        }

        if(!toFile.isDirectory()) {
            toFile.mkdirs();
        }

        if(!Arrays.asList(picture).contains(strFormat.toUpperCase())) {
            return false;
        }

        if(document.getNumberOfPages() <= integer) {
            return false;
        }

        //缩放比例
        float scale = 8.5f;
        //旋转角度
        float rotation = 0f;
        BufferedImage bufferedImage  = null;
        try {
            bufferedImage = (BufferedImage)document.getPageImage(integer, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
            ImageIO.write(bufferedImage, strFormat, toFile);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static void main(String[] args) {
//        String thumbnailPath = getThumbnailPath("E:\\test\\Activiti5.4 用户指南（中文版） .pdf", "D:\\temp\\images");
//        System.out.println("path" + thumbnailPath);
//        Boolean b = pdfToPNG("E:\\test\\Activiti5.4 用户指南（中文版） .pdf", "D:\\temp\\images");
//        System.out.println("是否转换成功：" + b);

        try
        {
            String pdfFile = "E:\\test\\Activiti5.4 用户指南（中文版） .pdf";
            PDDocument doc = PDDocument.load(new File(pdfFile));
            int pagenumber = doc.getNumberOfPages();
            pdfFile = pdfFile.substring(0, pdfFile.lastIndexOf("."));
            String fileName = pdfFile + ".doc";
            File file = new File(fileName);
            if (!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(fileName);
            Writer writer = new OutputStreamWriter(fos, "UTF-8");
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setSortByPosition(true);// 排序
            stripper.setStartPage(1);// 设置转换的开始页
            stripper.setEndPage(pagenumber);// 设置转换的结束页
            stripper.writeText(doc, writer);
            writer.close();
            doc.close();
            System.out.println("pdf转换word成功！");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}