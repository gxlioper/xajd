package xgxt.utils.img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

/**
 * 学生相片导出工具类 
 * 
 * lt add 2010.10.22
 */
public class ImageExportUtils {

	/** 学生照片批量导出 */
	public boolean stuImageExport(String[] xhArray, HttpServletResponse response) {

		ZipOutputStream zos = null;
		if (xhArray == null || xhArray.length == 0) {
			return false;
		}

		try {
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition",
					"attachment; filename=image.zip");
			zos = new ZipOutputStream(response.getOutputStream());
			
			
			//String[] xmArray = {"李续京","俞瑜","陈吉如","林杰","陈细珠","唐丽","赵亚","王林","潘琳琳","刘存","周文磊","杨洁心","郭巧鑫","马梦佳","李植鹏","张雅","宋燕","吴颖","李苗凤","方琳琳","彬彬","李哲"};
			
			for (int i = 0; i < xhArray.length; i++) {
				if (StringUtils.isNotNull(xhArray[i])) {
					//查询出学生相片BLOB
					Blob blob = queryUserImageByPk(xhArray[i]);
					InputStream in2 = blob.getBinaryStream();
					//输出相片流
					ZipEntry ze = new ZipEntry(xhArray[i]+ "" + ".jpg");
					zos.putNextEntry(ze);
					int blobsize = (int) blob.length();
					byte bu[] = new byte[blobsize];
					int bytesRead = 0;
					while ((bytesRead = in2.read(bu)) != -1) {
						zos.write(bu, 0, bytesRead);
					}
					in2.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				zos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	/**
	 * 查询用户的相片BLOB 如果存在多个相片表,可以修改这个方法,这里只是查询的是学生相片表  
	 * 
	 *  
	 * @param pk
	 * @param colName
	 * @return
	 */
	public Blob queryUserImageByPk(String pk) {
		//查询的是学生的相片
		String sql = "select zp from zpb where xh=?";
		String colName = "zp";
		DAO dao = DAO.getInstance();
		return dao.getOneBlob(sql, new String[] { pk }, colName);
	}
	
	
	
	/**
	 * 图片格式转换
	 * @param impFile
	 * @param expFile
	 * @throws IOException
	 */
	 public static void resetImgType(File impFile , File expFile) throws IOException {

		FileImageInputStream fiis = new FileImageInputStream(impFile);
		FileImageOutputStream fios = new FileImageOutputStream(expFile);

		ImageReader jpegReader = null;
		Iterator<ImageReader> it1 = ImageIO.getImageReadersByFormatName(impFile.getName().split(".")[1]);
		if (it1.hasNext()) {
			jpegReader = it1.next();
		}
		jpegReader.setInput(fiis);

		ImageWriter bmpWriter = null;
		Iterator<ImageWriter> it2 = ImageIO.getImageWritersByFormatName(expFile.getName().split(".")[1]);
		if (it2.hasNext()) {
			bmpWriter = it2.next();
		}
		bmpWriter.setOutput(fios);
		BufferedImage br = jpegReader.read(0);
		bmpWriter.write(br);
		fiis.close();
		fios.close();
	}   

	
}
