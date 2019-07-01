/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-25 ����10:13:35 
 */
package com.zfsoft.xgxt.rcsw.txhd.xmsq.js;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.txhd.xmsz.TxhdXmszDao;
import com.zfsoft.xgxt.rcsw.txhd.xmsz.TxhdXmszForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ����񡪡���ѧ�
 * @�๦������: ��ѧ�Service
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-6-25 ����10:13:35
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class TxhdXmsqJsService extends
		SuperServiceImpl<TxhdXmsqJsForm, TxhdXmsqJsDao> implements Constants{
	
	private ShlcInterface shlc = new CommShlcImpl();
	
	
	/**
	 * 
	 * @����:������Ŀ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-25 ����02:08:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param xmdmArray
	 * @param ylzd1
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveXmsq(TxhdXmsqJsForm form, String[] xmdmArray, User user) throws Exception {
		
		if (xmdmArray == null || xmdmArray.length == 0) {
			logger.error("δѡ����Ŀ��");
			throw new NullPointerException();
		}

		form.setSqsj(GetTime.getTimeByFormat("YYYY-MM-DD hh24:mi:ss"));
		if (form.getType().equals("submit")) {
			form.setShzt(YW_SHZ);
		} else {
			form.setShzt(YW_WTJ);
		}

		for (int i = 0, n = xmdmArray.length; i < n; i++) {
			
			String sqid = UniqID.getInstance().getUniqIDHash();
			
			form.setXmdm(xmdmArray[i]);
			// ������Ŀ��Ϣ
			TxhdXmszDao txhdXmszDao = new TxhdXmszDao();
			TxhdXmszForm txhdXmszForm = new TxhdXmszForm();
			txhdXmszForm.setXmdm(xmdmArray[i]);
			TxhdXmszForm xmInfo = txhdXmszDao.getModel(txhdXmszForm);
			
			if (null != xmInfo && !StringUtil.isNull(xmInfo.getShlc())){
				String shlc = xmInfo.getShlc();
				form.setSplc(shlc);
				form.setXn(Base.currXn);
				form.setXq(Base.currXq);
				form.setSqid(sqid);
				form.setSqr(user.getUserName());
			}
			//��������
			boolean isSuccess = dao.runInsert(form);
			
			if (isSuccess){
				if(form.getType().equals("submit")){
					//���칤��
					isSuccess = shlc.runSubmit(sqid, form.getSplc(), form.getXh(), "rcsw_txhd_xmsh.do", "rcsw_txhd_xmsq_js.do");
				}
			}
		}
		
		return true;
	}

	/** 
	 * @����: ѡ����Ŀ����ҳ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-25 ����02:55:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	
	public List<HashMap<String,String>> getXmsqInfoList(String xh){
		
		return dao.getXmsqInfoList(xh);
	}

	/** 
	 * @����: ������Ŀ�޸�����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-27 ����11:25:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean bcxgXmsq(TxhdXmsqJsForm model)throws Exception{
		
		if(model.getType().equals("submit")){
			model.setShzt(YW_SHZ);
		}else{
			model.setShzt(YW_WTJ);
		}
		
		boolean isSuccess = dao.runUpdate(model);
		if (isSuccess){
			if(model.getType().equals("submit")){
				//���칤��
				isSuccess = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "rcsw_txhd_xmsq_js.do", "rcsw_txhd_xmsh.do");
			}
		}
		return isSuccess;
	}
	
	
	/**
	 * 
	 * @����:�ύ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-27 ����11:59:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pkValue
	 * @param lcid
	 * @param xh
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitRecord(String pkValue,String lcid,String xh) throws Exception {
		return shlc.runSubmit(pkValue, lcid, xh, "rcsw_txhd_xmsq_js.do", "rcsw_txhd_xmsh.do");
	}
	
	
	/**
	 * 
	 * @����:����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-27 ����12:20:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateModel(TxhdXmsqJsForm model) throws Exception {
		return super.runUpdate(model);
	}

	
	
	/** 
	 * @����:����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-27 ����01:28:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @param lcid
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean cancleRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
	
	/**
	 * 
	 * @����:������Ŀ�����ѯʣ������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-27 ����02:54:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public boolean getSymeForXmdm(String xmdmArray, String type) throws Exception{
		
		if (xmdmArray == null || xmdmArray == "") {
			logger.error("δѡ����Ŀ��");
			throw new NullPointerException();
		}
		
		if(type.equalsIgnoreCase("submit")){
			
			String syme = dao.getSymeForXmdm(xmdmArray);
			if(!StringUtils.isBlank(syme)&& Integer.valueOf(syme)<= 0){
				return false;
			}
		}
		
		return true;
	}

}
