/**
 * @����: ѧ����Ʒ(1)��
 * @���ڣ� 2018-7-13 ����10:25:05 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.jdsz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���������Ź���ģ��
 * @�๦������: ���ɼ������
 * @���ߣ� MengWei[����:1186]
 * @ʱ�䣺 2018-7-13 ����10:24:14 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JdszService extends SuperServiceImpl<JdszForm,JdszDao>{
	private JdszDao dao = new JdszDao();
	
	public JdszService(){
		super.setDao(dao);
	}
	
	/**
	 * @����: ������Ŀ��������Ŀ����
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-13 ����02:34:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getXmmcByXmdm(String xmdm) throws Exception {
		return dao.getXmmcByXmdm(xmdm);
	}
	
	/**
	 * @����: ����xmdm��ѯ���õļ�¼
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-13 ����03:14:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param xn
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getByXmdm(String xmdm, String xn) throws Exception{
		return dao.getByXmdm(xmdm, xn);
	}
	
	/**
	 * @����: ����xmdm��ѯ����Ŀ�Ƿ�����
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-13 ����03:23:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistsFlowData(String xmdm) throws Exception{
		return Integer.valueOf(dao.getFlowData(xmdm)) > 0;
	}
	
	/**
	 * @����: ��ȡ��ѡ��Ŀ�����������Ŀ(��ͬ�����ڵ�)
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-13 ����05:51:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getOthers(String xmdm) throws Exception{
		CsszService CsszService = new CsszService();
		String xn = CsszService.getCszzInfo().get("xn");
		return dao.getOthers(xmdm, xn);
	}
	
	/**
	 * @����: ������ñ���
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-14 ����11:38:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param xmdms
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean jdsz(String xmdm,String xmdms) throws Exception{
		return dao.runJdsz(xmdm,xmdms);
	}
}
