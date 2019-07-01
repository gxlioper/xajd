package com.zfsoft.xgxt.xsxx.tdjs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/**
 * 
 * 团队建设service
 * 
 * @author qph
 * 213-4-8
 */
public class TdjsService extends SuperServiceImpl<TdjsForm, TdjsDao>{

	private TdjsDao dao = new TdjsDao();
	
	public TdjsService(){
		super.setDao(dao);
	}

	/*
	 * （非 Javadoc）
	 * @see xsgzgl.comm.service.impl.SuperServiceImpl#runUpdate(java.lang.Object)
	 */
	public boolean runUpdate(TdjsForm t) throws Exception {
		
		//团队信息修改
		boolean isSucess = super.runUpdate(t);
		//指导老师修改(先删后增)
		if (isSucess){
			dao.delZdls(t.getBjdm());
			isSucess = dao.saveZdls(t);
		}
		
		return isSucess;
	}

	
	/*
	 * （非 Javadoc）
	 * @see xsgzgl.comm.service.impl.SuperServiceImpl#runDelete(java.lang.String[])
	 */
	public int runDelete(String[] values) throws Exception {
		//团队对应指导老师删除
		dao.delZdls(values);
		//团队删除
		return dao.runDelete(values);
	}
	
	
	/**
	 * 加载指导老师信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getZdlsInfo(TdjsForm model){
		
		return dao.getZdlsInfo(model);
	}
	
	
	/**
	 * 生成团队信息
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> sctdInfo(TdjsForm model){
		
		String zgh = model.getZgh();
		
		if (!StringUtil.isNull(zgh)){
			String[] zghs = zgh.replaceAll("\r", " ").replaceAll("\n", " ").split(" ");
			List<String> zghList = new ArrayList<String>();
			
			for (int i = 0 , n = zghs.length ; i < n ; i++){
				if (!StringUtil.isNull(zghs[i])){
					zghList.add(zghs[i].trim());
				}
			}
			
			if(!zghList.isEmpty()){
				return dao.sctdInfo(zghList);
			}
		}
		return null;
	}
	
	
	/**
	 * 获取专业列表
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getZyList(TdjsForm model){
		
		String xydm = model.getBmdm();
		
		if (!StringUtil.isNull(xydm)){
			return dao.getZyList(xydm);
		} else {
			throw new NullPointerException();
		}
	}
	
	
	
	/**
	 * 批量生成团队
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	public boolean saveTdinfo(TdjsForm model) throws SQLException{
		
		String[] zdlsArray = model.getZdlsArray();
		String[] tdArray = model.getTdArray();
		String[] njArray = model.getNjArray();
		String[] xyArray = model.getXyArray();
		String[] zyArray = model.getZyArray();
		
		if (null == zdlsArray || null == tdArray 
				|| null == njArray || null == xyArray 
				|| null == zyArray || zdlsArray.length == 0) {
			throw new SystemException(MessageKey.XSXX_TDJS_FAIL);
		}
		
		List<String[]> params = new ArrayList<String[]>();
		List<String[]> dbParams = new ArrayList<String[]>();
		
		for (int i = 0 ; i < zdlsArray.length; i++){
			int bjdmId = dao.getNewBjdmID();
			String bjdm = "ZFSOFT"+bjdmId;
			String[] param = new String[]{
					njArray[i],
					xyArray[i],
					zyArray[i],
					tdArray[i],
					bjdm
			};
			params.add(param);
			
			
			String[] dbParam = new String[]{
					zdlsArray[i],
					bjdm
			};
			dbParams.add(dbParam);
		}
		
		
		boolean isSuccess = dao.saveTdinfo(params);
		
		if (isSuccess){
			return dao.saveDbInfo(dbParams);
		}
		
		return isSuccess;
	}



	/**
	 * 学院列表
	 * @return
	 */
	public List<HashMap<String,String>> getXyList(){
		
		return dao.getXyList();
	}
	
	
	/**
	 * 专业列表
	 * @return
	 */
	public List<HashMap<String,String>> getZyList(){
		
		return dao.getZyList();
	}
	
	
	/**
	 * 专业列表
	 * @return
	 */
	public List<HashMap<String,String>> getBjList(){
		
		return dao.getBjList();
	}
	
	
	
	/**
	 * 年级列表
	 * @return
	 */
	public List<HashMap<String,String>> getNjList(){
		
		return dao.getNjList();
	}



	/**
	 * 批量查找团队信息
	 * @param values
	 * @return
	 */
	public List<HashMap<String,String>> getTdinfoList(String[] values){
		
		if (values != null && values.length > 0){
			return dao.getTdinfoList(values);
		}
		
		return null;
	}




	/**
	 * 保存学生分配
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveFpxs(TdjsForm model) throws Exception{
		
		String[] xhArray = model.getXhArray();
		String[] bjArray = model.getTdArray();
		
		if (xhArray == null || bjArray == null 
				|| xhArray.length == 0
				|| bjArray.length == 0) {
			return false;
		}
		
		
		for (int i = 0 , n = bjArray.length ; i < n ; i++){
			List<String> param = new ArrayList<String>();
			
			String xhs = xhArray[i];
			
			if (!StringUtil.isNull(xhs)){
				String[] xhList = xhs.replaceAll("\r", " ").replaceAll("\n", " ").split(" ");
				for (String xh : xhList){
					
					if (!StringUtil.isNull(xh.trim())){
						param.add(xh);
					}
				}
			}
			
			
			if (param.size() == 0){
				throw new SystemException(MessageKey.XSXX_TDJS_XHNULL);
			}
			
			dao.batchUpdateBjdm(bjArray[i], param);
		}
		
		return true;
	}
	
	
	
	/**
	 * 
	 * @描述: 查询团队学生
	 * @作者：Penghui.Qu
	 * @日期：2013-5-10 下午01:59:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getStudents(TdjsForm model) throws Exception{
		return dao.getStudentsByBjdm(model);
	}
}
