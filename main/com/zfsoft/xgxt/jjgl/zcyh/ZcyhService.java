/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-28 ����11:09:23 
 */  
package com.zfsoft.xgxt.jjgl.zcyh;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-28 ����11:09:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcyhService extends SuperServiceImpl<ZcyhForm, ZcyhDao> {

	/**
	 * 
	 * @����:��ȡ��Ů��Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-28 ����11:16:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public HashMap<String , String> getZnxxMapById(String id) throws Exception{
		return dao.getZnxxMapById(id);
	}
	
	/**
	 * 
	 * @����:��ȡע���û���Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-28 ����11:27:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getZcyhMapById(String id) throws Exception{
		return dao.getZcyhMapById(id);
	}
	
	/**
	 * 
	 * @����:��ȡ������Ů��Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-28 ����11:19:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yhm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getZnxxMapByYhm(String yhm) throws Exception{
		return dao.getZnxxMapByYhm(yhm);
	}
	
	/**
	 * 
	 * @����:���ú�����
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-29 ����03:11:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yhm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean szHmd(ZcyhForm model)throws Exception{
		model.setHmdsj(DateUtils.getCurrTime());
		return dao.szHmd(model);
	}
	
	/**
	 * 
	 * @����:Cancel������
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-29 ����03:11:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yhm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelHmd(ZcyhForm model)throws Exception{
		return dao.cancelHmd(model);
	}

	/**
	 * �ҳ���Ϣ���ӵı���.
	 *
	 * @param zcyhForm
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-26 16:52
	 */
	public boolean jzxxSaveForAdd(ZcyhForm zcyhForm) throws Exception {

		zcyhForm.setZt("0");
		zcyhForm.setZcsj(DateUtils.getCurrDate());
		boolean result = dao.jzxxSaveForAdd(zcyhForm);
		if(result){
			List<ZnxxModel> znxxModelList = zcyhForm.getZnxxModelList();
			result = dao.saveZnxx(znxxModelList,zcyhForm.getYhm());
		}
		return result;
	}

	/**
	 * �ж��û����Ƿ��Ѿ���ע��.
	 *
	 * @param zcyhForm
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-27 09:14
	 */
	public boolean isYhmExist(ZcyhForm zcyhForm) {

		return dao.isYhmExist(zcyhForm.getYhm());
	}

	/**
	 * �ж����֤���Ƿ��Ѿ���ע��.
	 *
	 * @param zcyhForm
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-27 09:14
	 */
	public boolean isSfzhExist(ZcyhForm zcyhForm) {

		return dao.isSfzhExist(zcyhForm);
	}

	/**
	 * �ж���ϵ�绰�Ƿ��Ѿ���ע��.
	 *
	 * @param zcyhForm
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-27 09:14
	 */
	public boolean isLxdhExist(ZcyhForm zcyhForm) {

		return dao.isLxdhExist(zcyhForm);
	}

	/**
	 * �ҳ���Ϣ�޸ĵı���.
	 *
	 * @param zcyhForm
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-26 16:52
	 */
	public boolean jzxxSaveForEdit(ZcyhForm zcyhForm) throws Exception {

		zcyhForm.setZt("0");
		zcyhForm.setZcsj(DateUtils.getCurrDate());
		boolean result = dao.jzxxSaveForEdit(zcyhForm);
		if(result){
			List<ZnxxModel> znxxModelList = zcyhForm.getZnxxModelList();
			result = dao.saveZnxx(znxxModelList,zcyhForm.getYhm());
		}
		return result;
	}

	/**
	 * �ҳ���Ϣ��ɾ��������ɾ��������Ů��Ϣ.
	 *
	 * @param ids
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-27 17:06
	 */
	public int jzxxDel(String[] ids) throws Exception {

		int num = dao.runDelete(ids);
		if(num > 0){
			dao.delZnxx(ids);
		}
		return num;
	}

	/**
	 * ���ݼҳ��û�����ѯ��Ů��Ϣ�б�
	 * @param sqr
	 * @return
	 */
	public List<HashMap<String,String>> getZnxxListByPid(String sqr) {

		return dao.getZnxxListByPid(sqr);
	}

	public String getMaxYhm() throws SQLException {
		return dao.getMaxYhm();
	}

}
