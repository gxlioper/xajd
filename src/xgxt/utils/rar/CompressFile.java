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
 * Title: ��ѹ���ļ�
 * </p>
 * <p>
 * Description: ͨ��apache��zip����ʵ�ֽ�ѹ��
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
	 * ~~~~~~~~~~~~���������û���Թ�~~~~~~~~~~~~
	 * 
	 * ��ѹzip����rar�������ݵ�ָ����Ŀ¼�£����Դ������ļ����°������ļ��е����
	 * 
	 * @param zipFilename
	 *            Ҫ��ѹ��zip����rar���ļ�
	 * @param outputDirectory
	 *            ��ѹ���ŵ�Ŀ¼
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
				// �ж��ļ������ڵĻ����ʹ������ļ������ļ��е�Ŀ¼
				if (!f.exists()) {
					String[] arrFolderName = zipEntry.getName().split("/");
					String strRealFolder = "";
					for (int i = 0; i < (arrFolderName.length - 1); i++) {
						strRealFolder += arrFolderName[i] + File.separator;
					}
					strRealFolder = outFile.getPath() + File.separator
							+ strRealFolder;
					File tempDir = new File(strRealFolder);
					// �˴�ʹ��.mkdirs()��������������.mkdir()
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
	 * ����ѹ���ļ��������ļ���ѹ����
	 * @param fileNames
	 * @param zipFileName
	 */
	public synchronized void zip(String[] fileNames,String zipFileName) {

		File zipFile = new File(zipFileName);
		try {
			zipFile.createNewFile();//����ѹ���ļ�
			//����ѹ���������
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
					zipFile));
			//ѹ���ļ�
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
	 * ѹ���ļ�
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
