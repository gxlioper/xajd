/**
 * @部门:学工产品事业部
 * @日期：2017-7-13 上午09:46:34 
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
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 青年志愿活动service(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2017-7-13 上午09:46:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QnzyhdService extends SuperServiceImpl<QnzyhdForm, QnzyhdDao>{
	private String default_path = "default_dekt.jpg";

	private QnzyhdDao dao = new QnzyhdDao();
	
	
	/** 
	 * @描述:获取服务类型列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-17 下午03:42:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getFwlxList(){
		return dao.getFwlxList();
	}
	
	/** 
	 * @描述:异步上传文件(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-18 上午11:27:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param file
	 * @param path
	 * @return
	 * @throws Exception
	 * String 返回类型 
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
		if(StringUtils.isNotNull(lastPath)){//如果有已经上传的附件图片
			//如果不为默认上传路径删除上传文件
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
	 * @描述:判断文件文件大小(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-18 上午11:47:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
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
	 * @描述:判断文件后缀名(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-18 上午11:54:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param file
	 * @return
	 * boolean 返回类型 
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
	 * @描述:获取青年志愿活动信息(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-19 上午09:32:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param hdid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String,String> getQnzyhdInfo(String hdid) throws Exception{
		return dao.getQnzyhdInfo(hdid);
	}
	
	/**
	 * @throws Exception  
	 * @描述:获取已发布活动列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-20 下午02:40:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getYfbhdList(QnzyhdForm form,User user) throws Exception{
		return dao.getYfbhdList(form, user);
	}
	
	/** 
	 * @描述:获取已参加活动list(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-24 下午03:09:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getYcjhdList(QnzyhdForm t, User user) throws Exception{
		return dao.getYcjhdList(t, user);
	}
	
	/*
	 改写更改保存方法
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
	 * @描述:更新发布审核状态(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：982]
	 * @日期：2017-9-29 下午06:57:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateFbShzt(QnzyhdForm model)throws Exception{
		return dao.runUpdate(model);
	}
	
	/** 
	 * @描述:改写增加保存方法(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-28 上午11:23:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
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
	 * @描述:获取关联学院(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-28 下午02:52:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws SQLException
	 * List<String> 返回类型 
	 * @throws 
	 */
	public List<String> getGlXy(QnzyhdForm t) throws SQLException{
		return dao.getGlXy(t);
	}
	
	/**
	 * @throws Exception  
	 * @描述:获取发布审核list(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-29 下午04:17:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getfbshList(QnzyhdForm t,User user) throws Exception{
		return dao.getfbshList(t,user);
	}
	
	/** 
	 * @描述:撤销发布审核(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-29 下午07:10:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean cxFbsh(String[] ids) throws Exception{
		return dao.cxFbsh(ids);
	}
	
	/**
	 * @throws Exception  
	 * @描述:批量审核发布(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-30 上午08:33:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean plshFb(QnzyhdForm t) throws Exception{
		String[] ids = t.getIds();
		return dao.plshFb(ids, t);
	}
	
	
}
