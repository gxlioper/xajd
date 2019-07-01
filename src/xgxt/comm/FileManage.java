package xgxt.comm;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.upload.FormFile;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;



public class FileManage {

	/**
	 * �ļ��ϴ�
	 * 
	 * @param file
	 * @param dirPath
	 *            ��·��������/upload��
	 * @param max
	 *            ���M
	 * @return map,����ϴ��ɹ������ļ������ļ�·��
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static HashMap<String, String> fileUpload(FormFile file,
			String dirPath, int max) throws FileNotFoundException, IOException {

		HashMap<String, String> map = new HashMap<String, String>();

		if (null != file && !Base.isNull(file.getFileName())) {
			File f = new File(dirPath);
			if (!f.exists()) {
				f.mkdir();
			}// �����ļ���
			String guid = StringUtils.getGuid();// ��ȡ���ID
			int size = file.getFileSize();// �ļ���С
			if (size > 1048576 * max) {
				// ����1M���ϴ�������errMsg
				map.put("errMsg", "   �ļ���С���ܳ���" + max + "MB!");
			} else {
				String fName = guid + file.getFileName();// �����ļ���
				InputStream in = file.getInputStream();// ��ȡ������
				String path = dirPath + "/" + fName;// �����ļ�����·�����ļ���
				map.put("fName", file.getFileName());
				map.put("filePath", path);
				OutputStream out = new FileOutputStream(path);// ���������
				int data = 0;
				byte[] buffer = new byte[size];
				while ((data = in.read(buffer, 0, size)) != -1) {
					// д�ļ������ش���
					out.write(buffer, 0, data);
				}
				out.close();
				in.close();
			}
		}
		return map;
	}

	/**
	 * �����ļ������Ϣ�����ݿ� ������д��ͨ�ý��ʹ��
	 * 
	 * @param tableName
	 *            ����
	 * @param fileNameColumn
	 *            �ļ��������ݿ��ж�Ӧ������
	 * @param filePathColumn
	 *            �ļ�·�������ݿ��ж�Ӧ������
	 * @param pkKey
	 *            �����ֶ�
	 * @param pkValue
	 *            ����ֵ
	 * @param map
	 *            �ϴ��ļ�ʱ���ص�map
	 * @return
	 * @throws Exception
	 */
	public static boolean saveFileInfo(String tableName, String fileNameColumn,
			String filePathColumn, String pkKey, String pkValue,
			HashMap<String, String> map) throws Exception {
			String[] inputValue = {};
		if (null != map && StringUtils.isNotNull(map.get("fName"))) {
			DAO dao = DAO.getInstance();
			StringBuilder sql = new StringBuilder();

			sql.append("update ");
			sql.append(tableName);
			sql.append(" set ");
			sql.append(filePathColumn);
			if(StringUtils.isNotNull(fileNameColumn)){
				sql.append("=?,");
				sql.append(fileNameColumn);
				sql.append("=? where ");
				inputValue = new String[] {map.get("filePath"),map.get("fName") ,pkValue};
			}else{
				sql.append("=?");
				sql.append(" where ");
				inputValue = new String[] {map.get("filePath"),pkValue};
			}
			
			sql.append(pkKey);
			sql.append("=?");
			return dao.runUpdate(sql.toString(),inputValue);
		} else {
			return false;
		}
		
		
	}

	/**
	 * �����ļ������Ϣ �������޸ģ��ȰѾ��ļ�ɾ�����ٰ����ϴ����ļ���Ϣ���浽���ݿ⣩
	 * 
	 * @param tableName
	 *            ����
	 * @param fileNameColumn
	 *            �ļ��������ݿ��ж�Ӧ������
	 * @param filePathColumn
	 *            �ļ�·�������ݿ��ж�Ӧ������
	 * @param pkKey
	 *            �����ֶ�
	 * @param pkValue
	 *            ����ֵ
	 * @param oldPkValue
	 *            �ɵ�����ֵ
	 * @param map
	 *            �ϴ��ļ�ʱ���ص�MAP
	 * @return
	 * @throws Exception
	 */
	public static boolean saveFileInfo(String tableName, String fileNameColumn,
			String filePathColumn, String pkKey, String pkValue,
			String oldPkValue, HashMap<String, String> map) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select * from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(pkKey);
		sql.append("=?");

		String filePath = dao.getOneRs(sql.toString(),
				new String[] { oldPkValue }, "filepath");

		if (StringUtils.isNotNull(filePath)) {
			File file = new File(filePath);
			if (file.exists()) {
				file.delete();
			}
		}

		return saveFileInfo(tableName, fileNameColumn, filePathColumn, pkKey,
				pkValue, map);
	}

	/**
	 * �����ļ�ɾ������ļ������ļ�·����Ϊ��
	 * 
	 * @param tableName
	 *            ����
	 * @param fileNameColumn
	 *            �ļ��������ݿ��ж�Ӧ������
	 * @param filePathColumn
	 *            �ļ�·�������ݿ��ж�Ӧ������
	 * @param pkKey
	 *            ����
	 * @param pkValue
	 *            ����ֵ
	 * @return
	 * @throws Exception
	 */
	public static boolean delFileInfo(String tableName, String fileNameColumn,
			String filePathColumn, String pkKey, String pkValue)
			throws Exception {

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();

		sql.append("update ");
		sql.append(tableName);
		sql.append(" set ");
		sql.append(filePathColumn);
		if(StringUtils.isNotNull(fileNameColumn)){
			sql.append("='',");
			sql.append(fileNameColumn);
			sql.append("='' where ");
		}else{
			sql.append("='' where ");
		}
		sql.append(pkKey);
		sql.append("=?");

		return dao.runUpdate(sql.toString(), new String[] { pkValue });
	}

	/**
	 * �ļ�����
	 * 
	 * @param filePath
	 *            �ļ�·��
	 * @param fileName
	 *            �ļ���
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public static boolean downLoadFile(String filePath, String fileName,
			HttpServletResponse response) throws IOException {

		byte b[] = new byte[500];
		File file = new File(StringUtils.isNotNull(filePath) ? filePath : "");

		if (file.exists()) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ DealString.toUtf8String(StringUtils.isNull(fileName) ? "" : fileName));
			InputStream in = new FileInputStream(file);
			in = new BufferedInputStream(in);
			while ((in.read(b)) != -1) {
				response.getOutputStream().write(b);
			}
			in.close();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ����ɾ���ļ������ݿ��������
	 * 
	 * @param tableName
	 *            ����
	 * @param pkKey
	 *            ����
	 * @param pkValue
	 *            ����ֵ
	 * @param filePathColumn
	 *            �ļ�·�������ݿ��ж�Ӧ����
	 * @return
	 * @throws Exception
	 */
	public static boolean bathDelFiles(String tableName, String pkKey,
			String[] pkValue, String filePathColumn) throws Exception {

		DAO dao = DAO.getInstance();

		if (null != pkValue && pkValue.length > 0) {

			StringBuilder sql = new StringBuilder();
			StringBuilder delSQL = new StringBuilder();

			sql.append("select * from ");
			sql.append(tableName);
			sql.append(" where ");
			sql.append(pkKey);
			sql.append(" in (");

			delSQL.append("delete from ");
			delSQL.append(tableName);
			delSQL.append(" where ");
			delSQL.append(pkKey);
			delSQL.append(" in (");

			for (int i = 0; i < pkValue.length; i++) {
				sql.append("'");
				sql.append(pkValue[i]);

				delSQL.append("'");
				delSQL.append(pkValue[i]);

				if (i != pkValue.length - 1) {
					sql.append("',");
					delSQL.append("',");
				} else {
					sql.append("')");
					delSQL.append("')");
				}
			}

			String[] filePaths = dao.getArray(sql.toString(), new String[] {},
					filePathColumn);
			delFiles(filePaths);
			return dao.runUpdate(delSQL.toString(), new String[] {});

		} else {
			return false;
		}
	}

	/**
	 * ����ɾ���ļ�
	 * 
	 * @param filePaths
	 */
	public static void delFiles(String[] filePaths) {

		if (null != filePaths && filePaths.length > 0) {
			for (int i = 0; i < filePaths.length; i++) {
				if (StringUtils.isNotNull(filePaths[i])) {
					File file = new File(filePaths[i]);
					if (file.exists()) {
						file.delete();
					}
				}
			}

		}
	}
	
	
	
	/**
	 * �������ϴ���ϴ��ļ���·��
	 * @param request
	 * @param mkmc
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getRootPath(HttpServletRequest request,String mkmc){
		String rootPath = request.getRealPath("")+".upload";
		//��������Ŀ¼�µ��ļ���
		File f = new File(rootPath);
	    if (!f.exists()) {
		   f.mkdir();
	    }//�����ļ���
	    
	    rootPath+="\\"+mkmc;
		f = new File(rootPath);
	    if (!f.exists()) {
		   f.mkdir();
	    }	
	    return rootPath;
	}
}
