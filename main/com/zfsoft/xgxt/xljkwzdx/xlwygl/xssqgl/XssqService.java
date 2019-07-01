/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-23 ����10:02:52 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xssqgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ȩ����
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-5-23 ����10:02:52 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XssqService extends SuperServiceImpl<XssqForm, XssqDao> {

	public XssqService(){
		setDao(new XssqDao());
	}
	
	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-26 ����10:00:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pks
	 * @return
	 * int �������� 
	 * @throws
	 */
	public int del(String[] pks){
		if(pks == null || pks.length == 0){
			return 0;
		}
		List<String[]> inptVal = new ArrayList<String[]>();
		for (String s : pks) {
			String xh = s.split("@@")[0];
			String lx = s.split("@@")[1];
			inptVal.add(new String[]{xh , lx});
		}
		try {
			int[] v = dao.del(inptVal);
			return pks.length;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 
	 * @����:��ѯ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-26 ����10:54:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param lx
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getModelData(String xh , String lx){
		return dao.getModelData(xh, lx);
	}
	
	
	/**
	 * 
	 * @����:��ѯ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-26 ����10:54:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param lx
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public XssqForm getModel(String xh , String lx){
		HashMap<String,String> data = dao.getModelData(xh, lx);
		XssqForm model = new XssqForm();
		model.setLx(data.get("lx"));
		model.setRzjsrq(data.get("rzjsrq"));
		model.setRzksrq(data.get("rzksrq"));
		model.setSfxypssb(data.get("sfxypssb"));
		model.setXh(data.get("xh"));
		return model;
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @����:������ݿ��Ƿ����
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-26 ����10:54:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param lx
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public boolean checkExist(String xh , String lx) throws SQLException{
		int i = dao.checkExist(xh, lx);
		return i >= 1;
	}
	
	/**
	 * 1.�Ƿ�Ϊ�༶����ίԱ
	 * 2.¥��/�㳤
	 * 3.��Ȩѧ��
	 * @����:��ȡѧ����Ȩ���
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-27 ����08:33:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> xssqCheck(String xh){
		return dao.xssqCheck(xh);
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-26 ����11:25:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateModel(XssqForm  model) throws Exception{
		return dao.updateModel(model);
	}
}
