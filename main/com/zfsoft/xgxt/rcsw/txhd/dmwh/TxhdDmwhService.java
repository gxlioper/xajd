/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-20 ����11:21:53 
 */
package com.zfsoft.xgxt.rcsw.txhd.dmwh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����-ѧ�Ż
 * @�๦������: ѧ�ŻService
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-6-20 ����11:21:53
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class TxhdDmwhService extends
		SuperServiceImpl<TxhdDmwhForm, TxhdDmwhDao> {

	/**
	 * 
	 * @����:�����һ��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-20 ����02:41:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws SQLException int ��������
	 * @throws
	 */
	public int getNextLbdm() throws SQLException {

		int maxLbdm = 0;
		maxLbdm = dao.getMaxLbdm() + 1;

		return maxLbdm;
	}

	/**
	 * 
	 * @����:�ж��������ڽ�������Ƿ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-19 ����04:13:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 *             String ��������
	 * @throws
	 */
	public String checkLbForJg(String value) throws Exception {

		String resultLbmc = "";
		String[] lbmc = dao.lbCheckExistForJg(value);
		for (int i = 0; i < lbmc.length; i++) {
			if (i == lbmc.length - 1) {
				resultLbmc += lbmc[i];
			} else {
				resultLbmc += lbmc[i] + ",";
			}
		}
		return resultLbmc;
	}

	/**
	 * 
	 * @����:�ж�����������Ŀά�������Ƿ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-20 ����02:48:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 *             String ��������
	 * @throws
	 */
	public String checkLbForXmwh(String value) throws Exception {

		String resultLbmc = "";
		String[] lbmc = dao.lbCheckExistForXmwh(value);
		for (int i = 0; i < lbmc.length; i++) {
			if (i == lbmc.length - 1) {
				resultLbmc += lbmc[i];
			} else {
				resultLbmc += lbmc[i] + ",";
			}
		}
		return resultLbmc;
	}

	/**
	 * 
	 * @����:�ж���������Ƿ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-20 ����02:54:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return boolean ��������
	 * @throws
	 */
	public boolean isExistByLbmc(TxhdDmwhForm form) {

		boolean flag = false;

		String num = dao.lbmcCheckExist(form);
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}

		return flag;
	}
	
	
	/**
	 * 
	 * @����:��ȡ���list
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-24 ����11:59:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getLblist() throws Exception {
		return dao.getLblist();
	}
	
	public List<HashMap<String, String>> getHdggList(TxhdDmwhForm t)
	   throws Exception {
		return dao.getHdggList(t);
    }
	
	//�����ظ���֤
	public boolean checkIsExits(TxhdDmwhForm t){
		return dao.checkIsExits(t);
	}
	
	//������������
	public boolean saveHdgg(TxhdDmwhForm t) throws Exception{
		return dao.saveHdgg(t);
	}
	
	//������������
	public boolean saveUpdateHdgg(TxhdDmwhForm t) throws Exception{
		return dao.saveUpdateHdgg(t);
	}
	
	//ɾ��
	public int delHdgg(String[] hdggdms) throws Exception{
		return dao.delHdgg(hdggdms);
	}

	//��ȡ�����б�
	public List<HashMap<String, String>> getHdggList()  throws Exception{
		return dao.getHdggList();
	}
	

	//��ȡ��������
	public String getHdggmc(String hdggdm){
		return dao.getHdggmc(hdggdm);
	}

}
