/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-23 ����03:23:10 
 */
package com.zfsoft.xgxt.rcsw.txhd.xmsz;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import xgxt.action.base.BaseDAO;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.txhd.xmsh.TxhdXmShDao;
import com.zfsoft.xgxt.rcsw.txhd.xmsq.js.TxhdXmsqJsDao;


/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ����񡪡�ѧ�Ż
 * @�๦������: ѧ�Żservice
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-6-23 ����03:23:10
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class TxhdXmszService extends
		SuperServiceImpl<TxhdXmszForm, TxhdXmszDao> {

	/**
	 * 
	 * @����:��������Ψһ���ж�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-24 ����01:47:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean isExistByXmwh(TxhdXmszForm from) throws Exception {

		String num = dao.checkExistForSave(from);
		return Integer.valueOf(num) > 0;

	}

	/** 
	 * @����:�ж���������Ƿ��Ѵ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-24 ����07:37:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExistByXmsq(String values)throws Exception {
		return dao.isRalate(values);
	}
	
	/**
	 * 
	 * @����:������Ŀ��������Ŀ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-24 ����07:54:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getNameById(String xmdm) throws Exception {
		return dao.getNameById(xmdm);
	}
	
	@Override
	public TxhdXmszForm getModel(TxhdXmszForm t) throws Exception {
		t = super.getModel(t);
		if(t!=null){
			// ��ѧ�ڴ���ת��Ϊѧ������
			t.setXqmc(BaseDAO.getInstance().getXqmcForXqdm(t.getXq()));
		}
		return t;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:�ж����롢��������Ƿ񳬹���������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-28 ����01:31:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * void �������� 
	 * @throws
	 */
	public void sqrssx(TxhdXmszForm myForm) throws Exception{
		
		//�ж����������Ƿ񳬹�������������
		if(!StringUtils.isBlank(myForm.getSqrssx())){
			//�鿴����������
			TxhdXmsqJsDao txhdXmsqJsDao = new TxhdXmsqJsDao();
			String ysqrs = txhdXmsqJsDao.getSqrs(myForm.getXmdm());
			if(!StringUtils.isBlank(ysqrs)&&Integer.valueOf(myForm.getSqrssx())<Integer.valueOf(ysqrs)){
				throw new SystemException(MessageKey.RCSW_TXHD_RSKZ_FAIL_SQ,ysqrs);
			}
		}
		
		//�ж���������Ƿ񳬹������������
		
		if(!StringUtils.isBlank(myForm.getShrssx())){
			//�鿴�����ͨ������
			TxhdXmShDao txhdXmShDao = new TxhdXmShDao();
			String yshrs = txhdXmShDao.getTgrsByXmdm(myForm.getXmdm(), myForm.getRskzjb());
			if(!StringUtils.isBlank(yshrs)&&Integer.valueOf(myForm.getShrssx())<Integer.valueOf(yshrs)){
				throw new SystemException(MessageKey.RCSW_TXHD_RSKZ_FAIL_SH,yshrs);
			}
			
		}
		
	}
	
	
	/**
	 * 
	 * @����:������Ŀ��Ŀ���Ʋ�ѯ��Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-30 ����09:34:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmmc
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getDateByName(String xmmc) throws Exception{
		return dao.getDateByName(xmmc);
	}

}
