package com.zfsoft.xgxt.rcsw.ylbx.ylbxgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ҽ�Ʊ��չ���
 */
public class YlbxglService extends SuperServiceImpl<YlbxglForm, YlbxglDao> {
	
	private YlbxglDao dao = new YlbxglDao();
	public static String _BCZSCID="-1";
	
	public YlbxglService() {
		super.setDao(dao);
	}
	
	/**
	 * Ψһ���ж�
	 */
	public boolean isExist(YlbxglForm model) throws Exception {
		return dao.isExist(model);
	}
	
	/**
	 * Ψһ���жϡ����ݲ�ѯ���������������ר�á�
	 */
	public boolean isExistPl(YlbxglForm model, List<HashMap<String,String>> resultList) throws Exception {
		return dao.isExistPl(model, resultList);
	}
	
	/**
	 * ����
	 */
	public boolean insertYlbxgl(YlbxglForm model) throws Exception {
		boolean insertResult = super.runInsert(model);
		return insertResult;
	}	
	
	/**
	 * �޸�
	 */
	public boolean updateYlbxgl(YlbxglForm model) throws Exception {
		boolean updateResult = super.runUpdate(model);
		return updateResult;
	}
	
	/**
	 * �鿴
	 */
	public Map<String, String> viewOneYlbxglList(String xh, String jgid) throws Exception {
		return dao.viewOneYlbxglList(xh, jgid);
	}
	
	/**
	 * ���޸�ҳ��ר�á��ų���ǰ��¼����ѯӦ������
	 */
	public String viewYjnum(String xh, String jgid) {
		return dao.viewYjnum(xh, jgid);
	}
	
	/**
	 * ���鿴ҳ��ר�á��ų���ǰ��¼����ѯ��ʷ����
	 */
	public List<HashMap<String, String>> viewLsList(String xh) {
		return dao.viewLsList(xh);
	}
	public List<HashMap<String, String>> viewOneList(String xh,String jgid) {
		return dao.viewOneList(xh,jgid);
	}

}
