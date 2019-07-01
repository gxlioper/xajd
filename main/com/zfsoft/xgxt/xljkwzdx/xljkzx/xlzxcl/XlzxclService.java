/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-29 ����03:06:03 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxcl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ���´�-��������ѯ -������ѯ����
 * @�๦������: 
 * @���ߣ� ��־��[����:1060]
 * @ʱ�䣺 2014-4-29 ����03:06:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XlzxclService extends SuperServiceImpl<XlzxclForm, XlzxclDao>{

	public XlzxclService() {
		super.setDao(new XlzxclDao());
	}
	
	/**
	 * ��ѯ��ѯʦ��Ϣ
	 * @param zgh  ְ����
	 * @return
	 */
	public HashMap<String,String> getZxsxx(String zgh){
		return dao.getZxsxx(zgh);
	}
	
	/**
	 * ����ѧ��������ѯԤԼ����Ų�ѯ������ѯ����
	 * @param sqid 
	 * @return
	 */
	public HashMap<String,String> getXlzxclInfo(String sqid){
		return dao.getXlzxclInfo(sqid);
	}
	
	/**
	 * ����ѧ��������ѯԤԼ�����ɾ��������ѯ����
	 * @param sqid 
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteXlzxclBySqid(String sqid) throws Exception{
		return dao.deleteXlzxclBySqid(sqid);
	}
	
	/**
	 * 
	 * @����:��ѯ��������
	 * @���ߣ���־��
	 * @���ڣ�2014-5-8 ����15:58:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 */
	public List<HashMap<String, String>> getAllXlzxclList(XlzxclForm model)
			throws Exception {
		return dao.getAllXlzxclList(model);
	}
}
