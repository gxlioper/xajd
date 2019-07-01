/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-30 ����06:39:24 
 */  
package com.zfsoft.xgxt.wjcf.cfjcsh;

import com.zfsoft.xgxt.wjcf.cfjcsq.CfjcsqDao;
import com.zfsoft.xgxt.wjcf.cfjcsq.CfjcsqForm;
import xgxt.form.User;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.wjcf.cfsb.CfsbglForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�͹���ģ��
 * @�๦������: (���ֽ�����) 
 * @���ߣ� ������[����:913]
 * @ʱ�䣺 2013-10-30 ����06:36:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfjcshService extends SuperServiceImpl<CfjcshForm, CfjcshDao> {
	
	private CfjcshDao dao=new CfjcshDao();
	private CfjcsqDao cfjcsqDao = new CfjcsqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public CfjcshService(){
		this.setDao(dao);
	}

	/** 
	 * @����:(�������)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-28 ����04:46:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	@TransactionControl
	public boolean jcsh(CfjcshForm form, User user) throws Exception{
		
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getSplcid());
		// �����
		model.setShr(user.getUserName());
		// ������
		model.setShyj(form.getShyj());
		// ���״̬
		model.setShzt(form.getShzt());
		// ��˸�λID
		model.setGwid(form.getGwid());
		model.setThgw(form.getThgw());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getYwid());
		model.setSqrid(form.getXh());
		model.setTzlj("wjcf_cfjcsh.do?method=cxCfjcshList");
		model.setTzljsq("wjcf_cfjcsq.do?method=cxCfjcsqList");
		
		String zhzt = shlc.runAuditingNotCommit(model);
		CfjcshForm shForm=new CfjcshForm();
		shForm.setYwid(form.getYwid());
		shForm.setSqjg(zhzt);
		boolean result=dao.runUpdateNotCommit(shForm, shForm.getYwid());
		if(result){
			//���ô�����Ϣ
			/*BaseDbcz dbcz=new BaseDbcz();
			dbcz.setShPath("wjcf_cfjcsh.do?method=cxCfjcshList");
			dbcz.setSqPath("wjcf_cfjcsq.do?method=cxCfjcsqList");
			dbcz.setGnmkMc("���ֽ�����");
			dbcz.setXmmc("������");
			dbcz.shPush(form.getYwid(), form.getSplcid());*/
		}
		if(result && zhzt.equals(Constants.TG)){
			CfjcsqForm cfjcsqForm =  cfjcsqDao.getModel(form.getYwid());
			form.setFilepath(cfjcsqForm.getFilepath());
			form.setFilepath2(cfjcsqForm.getFilepath2());
			result= dao.insertWjjgk(form);
		}
		return result;
	}
	
	/** 
	 * @����:(���һ����˻ع�)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-29 ����10:41:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splcid
	 * @param string
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean cancel(CfjcshForm model) throws Exception{
		CfjcshForm shForm=new CfjcshForm();
		shForm.setYwid(model.getYwid());
		shForm.setSqjg(Constants.SHZ);
		boolean result=dao.runUpdate(shForm, shForm.getYwid());
		if(result){
			int count=dao.udateJgk(model.getCfid());  //�޸Ľ����
			if(count>=0){
				//���ô�����Ϣ
				/*BaseDbcz dbcz=new BaseDbcz();
				dbcz.setShPath("wjcf_cfjcsh.do?method=cxCfjcshList");
				dbcz.setSqPath("wjcf_cfjcsq.do?method=cxCfjcsqList");
				dbcz.setGnmkMc("���ֽ�����");
				dbcz.setXmmc("������");
				dbcz.shPush(model.getYwid(), model.getSplcid());*/
			}else{
				return false;
			}
		}
		
		return result;
	}
	
	public String getJcLsh(CfjcshForm model) throws Exception{
		return dao.getJcLsh(model);
	}
	/**
	 * 
	 * @����:��֤��������ĺ��Ƿ����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-30 ����09:49:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkExistJcwh(CfjcshForm myForm) {
		String num = dao.checkExistJcwh(myForm);
		return Integer.valueOf(num) > 0;
	}
	

}
