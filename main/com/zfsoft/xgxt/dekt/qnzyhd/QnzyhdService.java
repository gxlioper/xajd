/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-7-13 ����09:46:34 
 */  
package com.zfsoft.xgxt.dekt.qnzyhd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.struts.upload.FormFile;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ����־Ը�service(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2017-7-13 ����09:46:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QnzyhdService extends SuperServiceImpl<QnzyhdForm, QnzyhdDao>{
	private String default_path = "default_dekt.jpg";

	private QnzyhdDao dao = new QnzyhdDao();
	
	
	/** 
	 * @����:��ȡ���������б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-17 ����03:42:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getFwlxList(){
		return dao.getFwlxList();
	}
	
	/** 
	 * @����:�첽�ϴ��ļ�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-18 ����11:27:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param file
	 * @param path
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws 
	 */
	public String upLoadFile(FormFile file,String lastPath,String path,String size,String accept) throws Exception{
		if(!checkFileSize(file,size)){
			return QnzyhdAction.OVERSIZE;
		}
		if(!checkType(file,accept)){
			return QnzyhdAction.ERRORTYPE;
		}
		File dir = new File(path);
		if(!dir.exists()){
			dir.mkdir();
		}
		if(StringUtils.isNotNull(lastPath)){//������Ѿ��ϴ��ĸ���ͼƬ
			//�����ΪĬ���ϴ�·��ɾ���ϴ��ļ�
			if(!lastPath.equalsIgnoreCase(default_path)){				
				File lastFile = new File(dir,lastPath.substring(lastPath.lastIndexOf("/")));
				if(lastFile.exists()){				
					lastFile.delete();
				}
			}
		}
		String fileName = System.currentTimeMillis()+file.getFileName().substring(file.getFileName().lastIndexOf("."));
		File targetFile = new File(dir, fileName);		
		if(!targetFile.exists()){
			targetFile.createNewFile();
		}
		InputStream inStream = file.getInputStream();
		OutputStream oStream = new FileOutputStream(targetFile);
		IOUtils.copy(inStream , oStream);
	
		inStream.close();
		oStream.close();
	    String picPath = path.substring(path.lastIndexOf(File.separator))+"\\"+fileName;
	    return picPath.replaceAll("\\\\", "/");
	}
	
	/** 
	 * @����:�ж��ļ��ļ���С(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-18 ����11:47:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkFileSize(FormFile file,String size){
		if(file.getFileSize()> (Integer.valueOf(size))*1024*1024){
			return false;
		}else{
			return true;
		}
	}
	
	/** 
	 * @����:�ж��ļ���׺��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-18 ����11:54:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param file
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkType(FormFile file,String accept){
		String prefixName = file.getFileName().substring(file.getFileName().lastIndexOf(".")+1);
		String[] accepts = (accept.trim()).split(",");
		int n = 0;
		for(int i=0;i<accepts.length;i++){
			if(accepts[i].equalsIgnoreCase(prefixName)){
				n++;
				break;
			}
		}
		if(n<1){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * @throws Exception  
	 * @����:��ȡ����־Ը���Ϣ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-19 ����09:32:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param hdid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String,String> getQnzyhdInfo(String hdid) throws Exception{
		return dao.getQnzyhdInfo(hdid);
	}
	
	/**
	 * @throws Exception  
	 * @����:��ȡ�ѷ�����б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-20 ����02:40:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getYfbhdList(QnzyhdForm form,User user) throws Exception{
		return dao.getYfbhdList(form, user);
	}
	
	/** 
	 * @����:��ȡ�Ѳμӻlist(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-24 ����03:09:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getYcjhdList(QnzyhdForm t, User user) throws Exception{
		return dao.getYcjhdList(t, user);
	}
	
	/*
	 ��д���ı��淽��
	 */
	public boolean runUpdate(QnzyhdForm model) throws Exception{
		if(StringUtils.isNotNull(model.getOldPath())){
			if(!model.getOldPath().equalsIgnoreCase(default_path)){				
				File file = new File(model.getOldPath());
				if(file.exists()){
					file.delete();
				}
			}
		}
		boolean result = dao.scGlxy(model);
		if(result){			
			result = dao.runUpdate(model);
			if(result){
				result = dao.insertGlXy(model);
			}
				
		}
		return result;
	}
	
	/** 
	 * @����:���·������״̬(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�982]
	 * @���ڣ�2017-9-29 ����06:57:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateFbShzt(QnzyhdForm model)throws Exception{
		return dao.runUpdate(model);
	}
	
	/** 
	 * @����:��д���ӱ��淽��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-28 ����11:23:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean runInsert(QnzyhdForm model) throws Exception{
//		if(model.getFjpath() == null || model.getFjpath() == "" || model.getFjpath().equals("")){
//			model.setFjpath(default_path);
//		}
		boolean result = dao.runInsert(model);
		if(result){
			result = dao.insertGlXy(model);
		}
		return result;		
	}
	
	/** 
	 * @����:��ȡ����ѧԺ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-28 ����02:52:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws SQLException
	 * List<String> �������� 
	 * @throws 
	 */
	public List<String> getGlXy(QnzyhdForm t) throws SQLException{
		return dao.getGlXy(t);
	}
	
	/**
	 * @throws Exception  
	 * @����:��ȡ�������list(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-29 ����04:17:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getfbshList(QnzyhdForm t,User user) throws Exception{
		return dao.getfbshList(t,user);
	}
	
	/** 
	 * @����:�����������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-29 ����07:10:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean cxFbsh(String[] ids) throws Exception{
		return dao.cxFbsh(ids);
	}
	
	/**
	 * @throws Exception  
	 * @����:������˷���(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-30 ����08:33:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean plshFb(QnzyhdForm t) throws Exception{
		String[] ids = t.getIds();
		return dao.plshFb(ids, t);
	}
	
	
}
