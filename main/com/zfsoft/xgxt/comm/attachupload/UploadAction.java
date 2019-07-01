/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-30 ����02:53:44 
 */  
package com.zfsoft.xgxt.comm.attachupload;

import com.zfsoft.ms.mail.util.CollectionUtils;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.Office2PdfUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.attachupload.model.FileInfo;
import com.zfsoft.xgxt.comm.attachupload.service.FileInfoService;
import com.zfsoft.xgxt.comm.attachupload.utils.FileToolkit;
import com.zfsoft.xgxt.comm.attachupload.utils.StringToolkit;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import xgxt.utils.date.DateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.ResourceBundle;

/** 
 * 
 */

public class UploadAction extends SuperAction {

	/**
	 * �ļ�����service
	 */
	private FileInfoService service  = new FileInfoService();
	
	private static ResourceBundle resource = ResourceBundle.getBundle("config/fileUploadConfig");
	
	private String basePath = null;
	
	private long maxszie = Long.valueOf(resource.getString("filesys.maxsize"));

	private static final String PRIFEX_ZF = "ZFXG_UPLOADFILES";
	private static final String CONVERT = "convert_pdf";

	private Log logger = LogFactory.getLog(UploadAction.class);
	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 */
	public UploadAction() {
		super();
		try {
			basePath = resource.getString("filesys.local.dir");
			//���û�и����ļ��洢·������Ĭ�ϸ�ϵͳ�û�Ŀ¼
			//Windows �û�Ŀ¼��
			if(StringUtils.isBlank(basePath)){
				basePath = System.getProperty("user.home") +File.separator + PRIFEX_ZF;
			}
			logger.info("�ϴ�����·��:---" + basePath + "---");
		} catch (Exception e) {
			e.printStackTrace();
			basePath = System.getProperty("user.home") +File.separator + PRIFEX_ZF;
			logger.info("�ϴ�����·��:---" + basePath + "---");
		}

	}

	/**
	 * 
	 * @����:�첽�ϴ�����
	 * @���ߣ���С��[���ţ�1036]
	 */
	public ActionForward asyncUpload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UploadForm uploadForm = (UploadForm)form;
		
		JSONObject j = new JSONObject();
		
		FormFile file = uploadForm.getUploadFile();
		
		//�ļ��ϴ�ʧ��
		if(file == null){
			j.put("result", "-1");
			response.getWriter().print(j);
			return null;
		}
		
		long maxFileSize = getMaxFileSize(uploadForm);
		
		int maxcount = StringUtils.isNumeric(uploadForm.getMaxcount()) ? Integer.valueOf(uploadForm.getMaxcount()) : 20;
		//��id
		String gid = uploadForm.getGid();
		
		List<FileInfo> infoList = service.getFileInfoList(gid);
		
		if(infoList.size() >= maxcount){
			j.put("result", "-2");
			response.getWriter().print(j);
			return null;
		}

		//�������20M
		if(file.getFileSize() > maxFileSize ){
			j.put("result", "-1");
			response.getWriter().print(j);
			return null;
		}
		
//		//�������20M
//		if(file.getFileSize() > maxszie ){
//			j.put("result", "-1");
//			response.getWriter().print(j);
//			return null;
//		}
//		
//		//�ж��ܵ��ļ���С
//		if(StringUtils.isNotBlank(gid)){
//			long curfilesize = file.getFileSize();
//			for (FileInfo fileInfo : infoList) {
//				curfilesize += Long.valueOf(fileInfo.getFilesize());
//			}
//			if(curfilesize > maxFileSize){
//				j.put("result", "-1");
//				response.getWriter().print(j);
//				return null;
//			}
//		}
		
		FileInfo info = FormFileToFileInfo(file, uploadForm.getGid());

		OutputStream fos = null;

		//�ж��ļ����Ƿ����
		if(StringUtils.isBlank(basePath)){
			j.put("result", "-99");
			response.getWriter().print(j);
			logger.info("�ϴ��ļ�·��Ϊ��!!!");
			return null;
		}else{
			//File filePath = new File(basePath);
			//boolean checkExist = filePath.exists() || filePath.isDirectory();
			boolean checkExist = FileToolkit.isExist(basePath);
			logger.info("�ļ����Ƿ����:"+checkExist);
			if(!checkExist){
				boolean mk = new File(basePath).mkdirs();
				if(mk){
					fos = new FileOutputStream(new File(basePath + File.separator + info.getGeneratename()));
				}else{
					logger.info("�����ϴ��ļ�·��ʧ��!!!");
					j.put("result", "-99");
					response.getWriter().print(j);
					return null;
				}
			}else{
				fos = new FileOutputStream(new File(basePath + File.separator + info.getGeneratename()));
			}
		}
		
		InputStream fis = file.getInputStream();
		
		try {
			IOUtils.copy(fis , fos);
		} finally{
			fos.close();
			fis.close();
		}

		service.runInsert(info);
		
		j.put("gid", info.getGid());
		j.put("fid", info.getFid());
		j.put("result", '0');
		j.put("canPreview",info.isCanpreview());
		response.getWriter().print(j);
		
		return null;
	}
	
	/**
	 * 
	 * @����:�첽��ȡ������Ϣ�б�JSON
	 * @���ߣ���С��[���ţ�1036]
	 */
	public ActionForward asyncQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String gid = request.getParameter("gid");

		if(StringUtils.isNotBlank(gid)){
			List<FileInfo> infoList = service.getFileInfoList(gid,resource);
			JSONArray dataList = JSONArray.fromCollection(infoList);
			response.getWriter().print(dataList);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @����:ɾ���ļ�
	 * @���ߣ���С��[���ţ�1036]
	 */
	public ActionForward asyncDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fid = request.getParameter("fid");
		
		if(StringUtils.isNotBlank(fid)){
			FileInfo info = service.getModel(fid);

			String targetPath = basePath + File.separator + info.getGeneratename();
			//����ļ������ڣ�ȥ�û���Ŀ¼����
			if(!FileToolkit.isExist(basePath + File.separator + info.getGeneratename())
					&&
					!StringUtils.equals(System.getProperty("user.home") +File.separator + PRIFEX_ZF, basePath)
			){
				targetPath = System.getProperty("user.home") +File.separator + PRIFEX_ZF + File.separator + info.getGeneratename();
			}
			service.runDelete(new String[]{fid});
			
			FileToolkit.deleteFile(targetPath);
			
			response.getWriter().print(getJsonMessage(SAVE_SUCCESS));
		}
		
		return null;
	}
	
	/**
	 * 
	 * @����:����
	 */
	public ActionForward asyncDownload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fid = request.getParameter("fid");
		
		if(StringUtils.isNotBlank(fid)){
			FileInfo info = service.getModel(fid);
			if(info != null){

				InputStream is;
				try {
					is = new FileInputStream(new File(basePath + File.separator + info.getGeneratename()));
					//����ļ������ڣ�ȥϵͳ�û�Ĭ��·���²���
				} catch (FileNotFoundException e) {
					
					if(!StringUtils.equals(System.getProperty("user.home") +File.separator + PRIFEX_ZF, basePath)){
						is = new FileInputStream(new File(System.getProperty("user.home") +File.separator + PRIFEX_ZF + File.separator + info.getGeneratename()));
					}else{
						throw new Exception(e);
					}
					
				}
				
				byte[] b = new byte[2048];
				
				response.setHeader("Content-Disposition", "attachment;filename="
						+ encodingFileName(info.getOriginalname()));
//				response.setContentType("image/jpeg");

				OutputStream os = response.getOutputStream();
				int len;
				while ((len = is.read(b)) > 0) {
					os.write(b, 0, len);
				}
				os.flush();
				os.close();
				is.close();
			}
		}
		
		return null;
	}

	public ActionForward asyncDownload2(ActionMapping mapping, ActionForm form,
									   HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String gid = request.getParameter("gid");

		if(StringUtils.isNotBlank(gid)){
			List<FileInfo> infos = service.getFileInfoList(gid);
			if(!CollectionUtils.isEmpty(infos)){

				InputStream is;
				try {
					is = new FileInputStream(new File(basePath + File.separator + infos.get(0).getGeneratename()));
					//����ļ������ڣ�ȥϵͳ�û�Ĭ��·���²���
				} catch (FileNotFoundException e) {

					if(!StringUtils.equals(System.getProperty("user.home") +File.separator + PRIFEX_ZF, basePath)){
						is = new FileInputStream(new File(System.getProperty("user.home") +File.separator + PRIFEX_ZF + File.separator + infos.get(0).getGeneratename()));
					}else{
						throw new Exception(e);
					}

				}

				byte[] b = new byte[2048];

				response.setHeader("Content-Disposition", "attachment;filename="
						+ encodingFileName(infos.get(0).getOriginalname()));
//				response.setContentType("image/jpeg");

				OutputStream os = response.getOutputStream();
				int len;
				while ((len = is.read(b)) > 0) {
					os.write(b, 0, len);
				}
				os.flush();
				os.close();
				is.close();
			}
		}

		return null;
	}
	/**
	 * 
	 * @����:ת��
	 */
	private static FileInfo FormFileToFileInfo(FormFile file , String gid){
		FileInfo info = new FileInfo();
		
		if(StringUtils.isBlank(gid) || "undefined".equals(gid))
			gid = UniqID.getInstance().getUniqIDHash().toUpperCase();
		
		String curtime = DateUtils.getCurrTime();
		String fid = UniqID.getInstance().getUniqIDHash().toUpperCase();
		String originalname = file.getFileName();
		String ext = StringToolkit.getLastName(originalname);
		String generatename = fid + '.' + ext;
		String filesize = file.getFileSize() + "";
		
		info.setExt(ext);
		info.setFid(fid);
		info.setFilesize(filesize);
		info.setGeneratename(generatename);
		info.setGid(gid);
		info.setOriginalname(originalname);
		info.setUploadtime(curtime);
		info.setCanpreview(resource.containsKey(ext));
		return info;
	}
	/**
	 * 
	 * @����:��ȡ�ļ���С
	 */
	private static long getMaxFileSize(UploadForm uploadForm){
		return Long.valueOf(uploadForm.getMaxsize()) * 1024 * 1024;
	}
	
	/**
	 * 
	 * @����:response.setHeader()���������ļ������봦��
	 * @���ߣ�taogj[���ţ�1075]
	 * @���ڣ�2016-11-29 ����08:14:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param fileName
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	private String encodingFileName(String fileName) throws Exception {

		String returnFileName = "";

		try {
			returnFileName = URLEncoder.encode(fileName, "UTF-8");
			returnFileName = StringUtils.replace(returnFileName, "+", "%20");
			if (returnFileName.length() > 150) {
				returnFileName = new String(fileName.getBytes("GB2312"), "ISO8859-1");
				returnFileName = StringUtils.replace(returnFileName, " ", "%20");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}

		return returnFileName;
	}

	/**
	 *  Ԥ��.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-01-22 11:19
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw
	 */
	public ActionForward preview(ActionMapping mapping, ActionForm form,
									   HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String fid = request.getParameter("fid");

		if(StringUtils.isNotBlank(fid)){
			FileInfo info = service.getModel(fid);
			if(info != null){
				String ext = info.getExt();
				//��Ҫ�ж�ext�Ƿ�֧��Ԥ��
				if(resource.containsKey(ext)){
					String cvt = resource.getString(ext);
					request.setAttribute("fileInfo",info);
					if("pdf".equals(cvt)){
						return mapping.findForward("previewPdf");
					}else{
						//��������ͼƬ��ʽ
						return mapping.findForward("previewImage");
					}
				}
			}
		}

		return null;
	}

	public ActionForward getPdfFile(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String fid = request.getParameter("fid");

		if(StringUtils.isNotBlank(fid)){
			FileInfo info = service.getModel(fid);
			if(info != null){
				String ext = info.getExt();
				String cvt = resource.getString(ext);
				InputStream is = null;
				if("pdf".equals(ext)){
					//����ת��
					try {
						is = new FileInputStream(new File(basePath + File.separator + info.getGeneratename()));
						//����ļ������ڣ�ȥϵͳ�û�Ĭ��·���²���
					} catch (FileNotFoundException e) {

						if(!StringUtils.equals(System.getProperty("user.home") +File.separator + PRIFEX_ZF, basePath)){
							is = new FileInputStream(new File(System.getProperty("user.home") +File.separator + PRIFEX_ZF + File.separator + info.getGeneratename()));
						}else{
							throw new Exception(e);
						}

					}

				}else if("pdf".equals(cvt)){
					//��Ҫת��Ϊpdf
					File baseDir = new File(basePath);
					if(!baseDir.exists()){
						if(!StringUtils.equals(System.getProperty("user.home") +File.separator + PRIFEX_ZF, basePath)){
							baseDir = new File(System.getProperty("user.home") +File.separator + PRIFEX_ZF );
						}else{
							throw new Exception("�ļ�·��������");
						}
					}
					File file = new File(baseDir.getAbsolutePath(),info.getGeneratename());
					File pdfFile = new File(baseDir.getAbsolutePath()+File.separator+CONVERT, info.getFid()+".pdf");
					if(!pdfFile.getParentFile().exists()){
						pdfFile.getParentFile().mkdir();
					}else {
						//ת��ǰ�ж� ת���ļ����ļ����Ƿ�����
						String convertCacheNum = resource.getString("convertCacheNum");
						if(StringUtils.isNotBlank(convertCacheNum)){
							int n = Integer.parseInt(convertCacheNum);
							if(pdfFile.getParentFile().listFiles().length > n){
								FileUtil.delAllFile(pdfFile.getParent());
							}
						}
					}
					//ת������
					if(!pdfFile.exists()&&file.exists()){
						//Linux��������ҪofficeHomeֵ����Ϊ�գ������������ļ�������
						//Windows������officeHomeֵΪ�ռ��ɣ������������ļ������ø���
						String officeHome = resource.getString("officeHome");
						Office2PdfUtil.office2pdf(file,pdfFile,officeHome);
					}
					is = new FileInputStream(pdfFile);
				}

				if(is != null){
					byte[] b = new byte[2048];
					response.setHeader("Content-Disposition", "attachment;filename="
							+ encodingFileName(info.getOriginalname()));

					OutputStream os = response.getOutputStream();
					int len;
					while ((len = is.read(b)) > 0) {
						os.write(b, 0, len);
					}
					os.flush();
					os.close();
					is.close();
				}
			}
		}

		return null;
	}

}
