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
 * ѧ����Ƭ���������� 
 * 
 * lt add 2010.10.22
 */
public class ImageExportUtils {

	/** ѧ����Ƭ�������� */
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
			
			
			//String[] xmArray = {"������","���","�¼���","�ֽ�","��ϸ��","����","����","����","������","����","������","�����","������","���μ�","��ֲ��","����","����","��ӱ","�����","������","���","����"};
			
			for (int i = 0; i < xhArray.length; i++) {
				if (StringUtils.isNotNull(xhArray[i])) {
					//��ѯ��ѧ����ƬBLOB
					Blob blob = queryUserImageByPk(xhArray[i]);
					InputStream in2 = blob.getBinaryStream();
					//�����Ƭ��
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
	 * ��ѯ�û�����ƬBLOB ������ڶ����Ƭ��,�����޸��������,����ֻ�ǲ�ѯ����ѧ����Ƭ��  
	 * 
	 *  
	 * @param pk
	 * @param colName
	 * @return
	 */
	public Blob queryUserImageByPk(String pk) {
		//��ѯ����ѧ������Ƭ
		String sql = "select zp from zpb where xh=?";
		String colName = "zp";
		DAO dao = DAO.getInstance();
		return dao.getOneBlob(sql, new String[] { pk }, colName);
	}
	
	
	
	/**
	 * ͼƬ��ʽת��
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
