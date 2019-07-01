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
	 * 文件上传
	 * 
	 * @param file
	 * @param dirPath
	 *            根路径，例“/upload”
	 * @param max
	 *            最大几M
	 * @return map,如果上传成功返回文件名和文件路径
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
			}// 创建文件夹
			String guid = StringUtils.getGuid();// 获取随机ID
			int size = file.getFileSize();// 文件大小
			if (size > 1048576 * max) {
				// 超过1M不上传并发送errMsg
				map.put("errMsg", "   文件大小不能超过" + max + "MB!");
			} else {
				String fName = guid + file.getFileName();// 创建文件名
				InputStream in = file.getInputStream();// 获取输入流
				String path = dirPath + "/" + fName;// 创建文件绝对路径加文件名
				map.put("fName", file.getFileName());
				map.put("filePath", path);
				OutputStream out = new FileOutputStream(path);// 创建输出流
				int data = 0;
				byte[] buffer = new byte[size];
				while ((data = in.read(buffer, 0, size)) != -1) {
					// 写文件到本地磁盘
					out.write(buffer, 0, data);
				}
				out.close();
				in.close();
			}
		}
		return map;
	}

	/**
	 * 保存文件相关信息到数据库 与李容写的通用结合使用
	 * 
	 * @param tableName
	 *            表名
	 * @param fileNameColumn
	 *            文件名在数据库中对应的列名
	 * @param filePathColumn
	 *            文件路径在数据库中对应的列名
	 * @param pkKey
	 *            主键字段
	 * @param pkValue
	 *            主键值
	 * @param map
	 *            上传文件时返回的map
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
	 * 保存文件相关信息 （用于修改，先把旧文件删除，再把新上传的文件信息保存到数据库）
	 * 
	 * @param tableName
	 *            表名
	 * @param fileNameColumn
	 *            文件名在数据库中对应的列名
	 * @param filePathColumn
	 *            文件路径在数据库中对应的列名
	 * @param pkKey
	 *            主键字段
	 * @param pkValue
	 *            主键值
	 * @param oldPkValue
	 *            旧的主键值
	 * @param map
	 *            上传文件时返回的MAP
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
	 * 单个文件删除后把文件名和文件路径置为空
	 * 
	 * @param tableName
	 *            表名
	 * @param fileNameColumn
	 *            文件名在数据库中对应的列名
	 * @param filePathColumn
	 *            文件路径在数据库中对应的列名
	 * @param pkKey
	 *            主键
	 * @param pkValue
	 *            主键值
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
	 * 文件下载
	 * 
	 * @param filePath
	 *            文件路径
	 * @param fileName
	 *            文件名
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
	 * 批量删除文件及数据库里的数据
	 * 
	 * @param tableName
	 *            表名
	 * @param pkKey
	 *            主键
	 * @param pkValue
	 *            主键值
	 * @param filePathColumn
	 *            文件路径在数据库中对应的列
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
	 * 批量删除文件
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
	 * 服务器上存放上传文件的路径
	 * @param request
	 * @param mkmc
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getRootPath(HttpServletRequest request,String mkmc){
		String rootPath = request.getRealPath("")+".upload";
		//服务器根目录下的文件夹
		File f = new File(rootPath);
	    if (!f.exists()) {
		   f.mkdir();
	    }//创建文件夹
	    
	    rootPath+="\\"+mkmc;
		f = new File(rootPath);
	    if (!f.exists()) {
		   f.mkdir();
	    }	
	    return rootPath;
	}
}
