package com.zfsoft.xgxt.base.editor;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import net.sf.json.JSONObject;

import xgxt.action.BaseAction;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: editor���
 * @�๦������: editor����ϴ��ļ�
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-11-19 ����04:23:43
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class EditorAction extends BaseAction {
	private String mypath="/comm/editor/attached/";
	// �ļ�����Ŀ¼·��
	private String savePath;
	// �ļ�����Ŀ¼URL
	private String saveUrl;

	public ActionForward upload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		savePath = servlet.getServletContext().getRealPath(mypath);
		saveUrl = request.getContextPath() + mypath;
		PrintWriter out = response.getWriter();
		// ���������ϴ����ļ���չ��
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,pdf");

		MessageResources message = getResources(request);
		// ����ļ���С
		long maxSize = new Long(message.getMessage("ke.maxSize")).longValue();
		//long maxSize = 30000000;

		response.setContentType("text/html; charset=UTF-8");

		if (!ServletFileUpload.isMultipartContent(request)) {
			out.println(getError("��ѡ���ļ���"));
			return null;
		}
		// ���Ŀ¼
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			out.println(getError("�ϴ�Ŀ¼�����ڡ�"));
			return null;
		}
		// ���Ŀ¼дȨ��
		if (!uploadDir.canWrite()) {
			out.println(getError("�ϴ�Ŀ¼û��дȨ�ޡ�"));
			return null;
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if (!extMap.containsKey(dirName)) {
			out.println(getError("Ŀ¼������ȷ��"));
			return null;
		}
		// �����ļ���
		savePath += "/" + dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List items = upload.parseRequest(request);
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			String fileName = item.getName();
			long fileSize = item.getSize();
			if (!item.isFormField()) {
				// ����ļ���С
				if (item.getSize() > maxSize) {
					out.println(getError("�ϴ��ļ���С�������ơ�"));
					return null;
				}
				// �����չ��
				String fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.<String> asList(extMap.get(dirName).split(","))
						.contains(fileExt)) {
					out.println(getError("�ϴ��ļ���չ���ǲ��������չ����\nֻ����"
							+ extMap.get(dirName) + "��ʽ��"));
					return null;
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_"
						+ new Random().nextInt(1000) + "." + fileExt;
				try {
					File uploadedFile = new File(savePath, newFileName);
					item.write(uploadedFile);
				} catch (Exception e) {
					out.println(getError("�ϴ��ļ�ʧ�ܡ�"));
					return null;
				}

				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", saveUrl + newFileName);
				out.println(obj.toString());
			}
		}
		return null;
	}

	public ActionForward showUpload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		// ��Ŀ¼·��������ָ������·�������� /var/www/attached/
		String rootPath = servlet.getServletContext().getRealPath("/")
				+ mypath;
		// ��Ŀ¼URL������ָ������·�������� http://www.yoursite.com/attached/
		String rootUrl = request.getContextPath() + mypath;
		// ͼƬ��չ��
		String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };

		String dirName = request.getParameter("dir");
		if (dirName != null) {
			if (!Arrays.<String> asList(
					new String[] { "image", "flash", "media", "file" })
					.contains(dirName)) {
				out.println("Invalid Directory name.");
				return null;
			}
			rootPath += dirName + "/";
			rootUrl += dirName + "/";
			File saveDirFile = new File(rootPath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
		}
		// ����path���������ø�·����URL
		String path = request.getParameter("path") != null ? request
				.getParameter("path") : "";
		String currentPath = rootPath + path;
		String currentUrl = rootUrl + path;
		String currentDirPath = path;
		String moveupDirPath = "";
		if (!"".equals(path)) {
			String str = currentDirPath.substring(0,
					currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str
					.lastIndexOf("/") + 1) : "";
		}

		// ������ʽ��name or size or type
		String order = request.getParameter("order") != null ? request
				.getParameter("order").toLowerCase() : "name";

		// ������ʹ��..�ƶ�����һ��Ŀ¼
		if (path.indexOf("..") >= 0) {
			out.println("Access is not allowed.");
			return null;
		}
		// ���һ���ַ�����/
		if (!"".equals(path) && !path.endsWith("/")) {
			out.println("Parameter is not valid.");
			return null;
		}
		// Ŀ¼�����ڻ���Ŀ¼
		File currentPathFile = new File(currentPath);
		if (!currentPathFile.isDirectory()) {
			out.println("Directory does not exist.");
			return null;
		}

		// ����Ŀ¼ȡ���ļ���Ϣ
		List<Hashtable> fileList = new ArrayList<Hashtable>();
		if (currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if (file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if (file.isFile()) {
					String fileExt = fileName.substring(
							fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String> asList(fileTypes)
							.contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime",
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file
								.lastModified()));
				fileList.add(hash);
			}
		}

		/*
		 * if ("size".equals(order)) { Collections.sort(fileList, new
		 * SizeComparator()); } else if ("type".equals(order)) {
		 * Collections.sort(fileList, new TypeComparator()); } else {
		 * Collections.sort(fileList, new NameComparator()); }
		 */
		JSONObject result = new JSONObject();
		result.put("moveup_dir_path", moveupDirPath);
		result.put("current_dir_path", currentDirPath);
		result.put("current_url", currentUrl);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);

		response.setContentType("application/json; charset=UTF-8");
		out.println(result.toString());
		return null;
	}

	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toString();
	}
}
