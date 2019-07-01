package com.zfsoft.xgxt.jskp.dmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class DmwhService extends SuperServiceImpl<DmwhForm,DmwhDao> {
	/**
	 * 
	 * @����: ��Ŀ���List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-6 ����03:46:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlbList(){
		return dao.getXmlbList();
	}
	
	/**
	 * 
	 * @����: ��֤�����Ƿ��ѱ�ʹ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-21 ����03:15:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsDmIsNotUserd(String[] xmlbdms){
		return dao.checkIsDmIsNotUserd(xmlbdms);
	}
	
	/**
	 * 
	 * @����: ��֤��Ŀ��������Ƿ�ʹ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-21 ����04:14:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmlbmc
	 * @param xmlbdm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkMcIsNotUserd(String xmlbmc,String xmlbdm){
		return dao.checkMcIsNotUserd(xmlbmc, xmlbdm);
	}
	
	/**
	 * @����: ��Ŀ�������-����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-29 ����04:00:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlbListByAll(){
		return dao.getXmlbListByAll();
	}
	
	/**
	 * @����: ��Ŀ�������-��������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-29 ����04:00:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlbListByNlsy(){
		return dao.getXmlbListByNlsy();
	}
	
	
	/**
	 * @����: ��Ŀ�������-˼����������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-29 ����04:00:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlbListBySzsz(){
		return dao.getXmlbListBySzsz();
	}
}
