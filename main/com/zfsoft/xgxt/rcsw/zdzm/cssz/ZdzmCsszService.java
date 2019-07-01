/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-3 ����03:42:35 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.cssz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.OptionUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: �ڶ�֤�� SERVICE�� 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-3 ����03:42:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZdzmCsszService extends SuperServiceImpl<ZdzmCsszForm, ZdzmCsszDao> {
	
	
	
	/**
	 * @���� ����ʼ��DAO
	 */
	public ZdzmCsszService() {
		super();
		super.setDao(new ZdzmCsszDao());
	}

	/**
	 * 
	 * @����:��ȡ���뿪���б�
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-4 ����10:23:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getSqkgList(){
		/**
		 *  �̶�ѡ�����
		 */
		OptionUtil optionUtil = new OptionUtil();
		
		List<HashMap<String , String>> sqkgList = optionUtil.getOptions(OptionUtil.ONOFF);
		
		return sqkgList;
	}
	
	/**
	 * 
	 * @����:��ȡ���ؿ����б�
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-4 ����10:23:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getXzkgList(){
		/**
		 *  �̶�ѡ�����
		 */
		OptionUtil optionUtil = new OptionUtil();
		
		List<HashMap<String , String>> xzkgList = optionUtil.getOptions(OptionUtil.ONOFF);
		
		return xzkgList;
	}
	
	
	/**
	 * 
	 * @����:��ȡ���ؿ����б�
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-4 ����10:23:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getXzkzList(){
		/**
		 *  �̶�ѡ�����
		 */
		OptionUtil optionUtil = new OptionUtil();
		
		List<HashMap<String , String>> xzkzList = optionUtil.getOptions(OptionUtil.RCSW_XZKG);
		
		return xzkzList;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:��ȡ��������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-4 ����10:53:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 *  �������� ZdzmCsszForm
	 * @throws
	 */
	public ZdzmCsszForm getCssz() throws Exception{
		return dao.getCssz();
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:�����������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-4 ����11:48:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveCssz(ZdzmCsszForm model) throws Exception{
		return dao.saveCssz(model);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:��ѯ��������޸ģ�
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-4 ����01:43:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getShlcxx() throws Exception{
		return dao.getShlcxx();
	}

	/**
	 * 
	 * @����:�ر����������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-4 ����02:38:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean closeCssz(ZdzmCsszForm model) throws Exception{
		return dao.closeCssz(model);
	}
}
