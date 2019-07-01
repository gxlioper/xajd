package xgxt.utils.rar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;
import org.apache.tools.zip.ZipEntry;




/**
 * <p>
 * Title: 解压缩文件
 * </p>
 * <p>
 * Description: 通过apache的zip工具实现解压缩
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 */
public class CompressFile {
	private static CompressFile instance = new CompressFile();

	private CompressFile() {
	}

	public static CompressFile getInstance() {
		return instance;
	}


	/**
	 * ~~~~~~~~~~~~这个方法还没测试过~~~~~~~~~~~~
	 * 
	 * 解压zip或者rar包的内容到指定的目录下，可以处理其文件夹下包含子文件夹的情况
	 * 
	 * @param zipFilename
	 *            要解压的zip或者rar包文件
	 * @param outputDirectory
	 *            解压后存放的目录
	 */
	public synchronized void unzip(String zipFilename, String outputDirectory)
			throws IOException {
		File outFile = new File(outputDirectory);
		if (!outFile.exists()) {
			outFile.mkdirs();
		}

		ZipFile zipFile = new ZipFile(zipFilename);
		Enumeration en = zipFile.getEntries();
		ZipEntry zipEntry = null;
		while (en.hasMoreElements()) {
			zipEntry = (ZipEntry) en.nextElement();
			if (zipEntry.isDirectory()) {
				// mkdir directory
				String dirName = zipEntry.getName();
				// System.out.println("=dirName is:=" + dirName + "=end=");
				dirName = dirName.substring(0, dirName.length() - 1);
				File f = new File(outFile.getPath() + File.separator + dirName);
				f.mkdirs();
			} else {
				// unzip file
				String strFilePath = outFile.getPath() + File.separator
						+ zipEntry.getName();
				File f = new File(strFilePath);

				// the codes remedified by can_do on 2010-07-02 =begin=
				// /////begin/////
				// 判断文件不存在的话，就创建该文件所在文件夹的目录
				if (!f.exists()) {
					String[] arrFolderName = zipEntry.getName().split("/");
					String strRealFolder = "";
					for (int i = 0; i < (arrFolderName.length - 1); i++) {
						strRealFolder += arrFolderName[i] + File.separator;
					}
					strRealFolder = outFile.getPath() + File.separator
							+ strRealFolder;
					File tempDir = new File(strRealFolder);
					// 此处使用.mkdirs()方法，而不能用.mkdir()
					tempDir.mkdirs();
				}
				// /////end///
				// the codes remedified by can_do on 2010-07-02 =end=
				f.createNewFile();
				InputStream in = zipFile.getInputStream(zipEntry);
				FileOutputStream out = new FileOutputStream(f);
				try {
					int c;
					byte[] by = new byte[BUFFEREDSIZE];
					while ((c = in.read(by)) != -1) {
						out.write(by, 0, c);
					}
					// out.flush();
				} catch (IOException e) {
					throw e;
				} finally {
					out.close();
					in.close();
				}
			}
		}
	}

	private static final int BUFFEREDSIZE = 1024;

	/**
	 * 创建压缩文件，加入文件到压缩包
	 * @param fileNames
	 * @param zipFileName
	 */
	public synchronized void zip(String[] fileNames,String zipFileName) {

		File zipFile = new File(zipFileName);
		try {
			zipFile.createNewFile();//创建压缩文件
			//创建压缩包输出流
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
					zipFile));
			//压缩文件
			for (int i = 0; i < fileNames.length; i++) {
				File file = new File(fileNames[i]);
				zipFile(zos, file);
			}
			zos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
	/**
	 * 压缩文件
	 * @param zos
	 * @param file
	 */
	public synchronized void zipFile(ZipOutputStream zos, File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			zos.putNextEntry(new ZipEntry(file.getName()));
			int len;
			while ((len = fis.read()) != -1) {
				zos.write(len);
			}
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
