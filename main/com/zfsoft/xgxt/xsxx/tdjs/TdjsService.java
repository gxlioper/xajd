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
 * �Ŷӽ���service
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
	 * ���� Javadoc��
	 * @see xsgzgl.comm.service.impl.SuperServiceImpl#runUpdate(java.lang.Object)
	 */
	public boolean runUpdate(TdjsForm t) throws Exception {
		
		//�Ŷ���Ϣ�޸�
		boolean isSucess = super.runUpdate(t);
		//ָ����ʦ�޸�(��ɾ����)
		if (isSucess){
			dao.delZdls(t.getBjdm());
			isSucess = dao.saveZdls(t);
		}
		
		return isSucess;
	}

	
	/*
	 * ���� Javadoc��
	 * @see xsgzgl.comm.service.impl.SuperServiceImpl#runDelete(java.lang.String[])
	 */
	public int runDelete(String[] values) throws Exception {
		//�ŶӶ�Ӧָ����ʦɾ��
		dao.delZdls(values);
		//�Ŷ�ɾ��
		return dao.runDelete(values);
	}
	
	
	/**
	 * ����ָ����ʦ��Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getZdlsInfo(TdjsForm model){
		
		return dao.getZdlsInfo(model);
	}
	
	
	/**
	 * �����Ŷ���Ϣ
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
	 * ��ȡרҵ�б�
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
	 * ���������Ŷ�
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
	 * ѧԺ�б�
	 * @return
	 */
	public List<HashMap<String,String>> getXyList(){
		
		return dao.getXyList();
	}
	
	
	/**
	 * רҵ�б�
	 * @return
	 */
	public List<HashMap<String,String>> getZyList(){
		
		return dao.getZyList();
	}
	
	
	/**
	 * רҵ�б�
	 * @return
	 */
	public List<HashMap<String,String>> getBjList(){
		
		return dao.getBjList();
	}
	
	
	
	/**
	 * �꼶�б�
	 * @return
	 */
	public List<HashMap<String,String>> getNjList(){
		
		return dao.getNjList();
	}



	/**
	 * ���������Ŷ���Ϣ
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
	 * ����ѧ������
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
	 * @����: ��ѯ�Ŷ�ѧ��
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-10 ����01:59:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getStudents(TdjsForm model) throws Exception{
		return dao.getStudentsByBjdm(model);
	}
}
