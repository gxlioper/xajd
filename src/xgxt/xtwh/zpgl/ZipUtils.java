package xgxt.xtwh.zpgl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class ZipUtils {

	public boolean unzip(String zipFilePath, String targetPath)
			throws Exception {
		try {
			InputStream is;// = new FileInputStream(zipFile);
			ZipFile zipFile = new ZipFile(zipFilePath);// 获取zip文件
			ZipEntry entry = null;
			System.out.println("开始解压:...");
			for (Enumeration entries = zipFile.getEntries(); entries
					.hasMoreElements();) {// 循环解压zip文件中的压缩文件至一个统一的目录下
				entry = (ZipEntry) entries.nextElement();
				String zipPath;
				if (entry.getName().lastIndexOf("/") == -1) {
					zipPath = entry.getName();// 解压到根目录下
				} else {
					zipPath = entry.getName().substring(
							entry.getName().lastIndexOf("/"));// 解压到根目录下
				}

				try {

					if (entry.isDirectory()) {
						File zipFolder = new File(targetPath + File.separator
								+ zipPath);
						if (!zipFolder.exists()) {
							zipFolder.mkdirs();
						}
					} else {
						File file = new File(targetPath + File.separator
								+ zipPath);
						if (!file.exists()) {
							File pathDir = file.getParentFile();
							pathDir.mkdirs();
							file.createNewFile();
						}

						FileOutputStream fos = new FileOutputStream(file);
						int bread;
						is = zipFile.getInputStream(entry);
						while ((bread = is.read()) != -1) {
							fos.write(bread);
						}
						fos.close();// 关闭文件输出流
						is.close();// 关闭zip文件实体流
					}
				} catch (Exception e) {
					System.out.println("解压" + zipPath + "失败");
					e.printStackTrace();
					continue;
				}
			}
			zipFile.close();// 关闭打开的zip文件
			System.out.println("解压结束");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	boolean deleteFile(String parentPath) {
		// String parentPath = file.getParent();
		File fileOne = new File(parentPath);
		String[] fileNames = fileOne.list();
		for (String fn : fileNames) {
			File filetmp = new File(parentPath + "\\" + fn);
			if (filetmp.isDirectory()) {
				deleteFile(parentPath + "\\" + fn);
			}
			try {
				filetmp.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return false;
	}
}
