/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-27 ����03:01:40 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.dazcsh;

import java.util.HashMap;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.dagl.xzmzdx.dazcjg.DazcjgDao;
import com.zfsoft.xgxt.dagl.xzmzdx.dazcjg.DazcjgForm;
import com.zfsoft.xgxt.dagl.xzmzdx.dazcjg.DazcjgService;
import com.zfsoft.xgxt.dagl.xzmzdx.dazcsq.DazcsqDao;
import com.zfsoft.xgxt.dagl.xzmzdx.dazcsq.DazcsqForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ����ģ��
 * @�๦������: ����ת��-���
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-5-14 ����03:28:27 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class DazcshService extends SuperServiceImpl<DazcshForm,DazcshDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * @����: ��˸�����Ϣ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-14 ����05:52:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getDazcshInfo(DazcshForm t) throws Exception{
		return dao.getDazcshInfo(t);
	}
	
	/**
	 * @����: ��˱���
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-14 ����06:57:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	@TransactionControl
	public boolean dazcshSingleSave(DazcshForm form,User user)throws Exception{
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getSplcid());
		// �����
		model.setShr(user.getUserName());
		// ������
		model.setShyj(form.getShyj());
		// ���״̬
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// ��˸�λID
		model.setGwid(form.getGwid());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getSqid());
		model.setSqrid(form.getXh());
		model.setTzlj("xsxx_dagl_dazcsh.do");
		model.setTzljsq("xsxx_dagl_dazcsq.do");
		boolean reuslt = false;
		try{
			String zhzt = shlc.runAuditingNotCommit(model);
			DazcshForm dazcshForm = new DazcshForm();
			dazcshForm.setSqid(form.getSqid());
			dazcshForm.setShzt(zhzt);
			reuslt = dao.runUpdateNotCommit(dazcshForm,form.getSqid());
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				DazcjgForm dazcjgForm = new DazcjgForm();
				DazcjgService dazcjgService = new DazcjgService();
				DazcsqForm dazcsqForm = new DazcsqDao().getModel(form.getSqid());
				BeanUtils.copyProperties(dazcjgForm, StringUtils.formatData(dazcsqForm));
				
				dazcjgForm.setYwid(dazcsqForm.getSqid());
				/*��ȡ��ǰ����ʱ��������У���ϲ����˹��ŷ�ֹ��ʦˣ��*/
				String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
				dazcjgForm.setSjlrsj(GetTime.getTimeByFormat(DATE_FORMAT));
				/*������Դ��1:������ˡ�2:������ӡ�3:���롿*/
				dazcjgForm.setSjly("1");
				/*����ת����Ϣ��1:�ѵǼǡ�2:��ת����3:δ�Ǽǡ�*/
				dazcjgForm.setDazcxx("1");
				reuslt = dazcjgService.runInsert(dazcjgForm);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return reuslt;
	}
	
	/**
	 * @����: ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-14 ����05:33:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancel(DazcshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
		if(result){
			DazcjgDao dazcjgDao = new DazcjgDao();
			dazcjgDao.delShjgById(myForm.getSqid());
		}
		return result;
	}
}
