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
			ZipFile zipFile = new ZipFile(zipFilePath);// ��ȡzip�ļ�
			ZipEntry entry = null;
			System.out.println("��ʼ��ѹ:...");
			for (Enumeration entries = zipFile.getEntries(); entries
					.hasMoreElements();) {// ѭ����ѹzip�ļ��е�ѹ���ļ���һ��ͳһ��Ŀ¼��
				entry = (ZipEntry) entries.nextElement();
				String zipPath;
				if (entry.getName().lastIndexOf("/") == -1) {
					zipPath = entry.getName();// ��ѹ����Ŀ¼��
				} else {
					zipPath = entry.getName().substring(
							entry.getName().lastIndexOf("/"));// ��ѹ����Ŀ¼��
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
						fos.close();// �ر��ļ������
						is.close();// �ر�zip�ļ�ʵ����
					}
				} catch (Exception e) {
					System.out.println("��ѹ" + zipPath + "ʧ��");
					e.printStackTrace();
					continue;
				}
			}
			zipFile.close();// �رմ򿪵�zip�ļ�
			System.out.println("��ѹ����");
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
