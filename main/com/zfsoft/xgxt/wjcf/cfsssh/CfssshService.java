/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-30 ����09:29:41 
 */  
package com.zfsoft.xgxt.wjcf.cfsssh;

import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import xgxt.form.User;
import xsgzgl.wjcf.jcsz.WjcfJcszDao;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�͹���ģ��
 * @�๦������: (�������) 
 * @���ߣ�������[����:913]
 * @ʱ�䣺 2013-10-30 ����09:23:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfssshService extends SuperServiceImpl<CfssshForm, CfssshDao> {
	
	private CfssshDao dao=new CfssshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public CfssshService(){
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
	public boolean sssh(CfssshForm form, User user) throws Exception{
		
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
		model.setTzlj("wjcf_cfsssh.do?method=cxCfssshList");
		model.setTzljsq("wjcf_cfsssq.do?method=cxCfsssqList");
		
		String zhzt = shlc.runAuditingNotCommit(model);
		
		CfssshForm shForm=new CfssshForm();
		shForm.setYwid(form.getYwid());
		shForm.setSsjg(zhzt);
		boolean result=dao.runUpdateNotCommit(shForm, shForm.getYwid());
		if(result){
			//���ô�����Ϣ
			/*BaseDbcz dbcz=new BaseDbcz();
			dbcz.setShPath("wjcf_cfsssh.do?method=cxCfssshList");
			dbcz.setSqPath("wjcf_cfsssq.do?method=cxCfsssqList");
			dbcz.setGnmkMc("�����������");
			dbcz.setXmmc("�������");
			dbcz.shPush(form.getYwid(), form.getSplcid());*/
		}
		if(result && zhzt.equals(Constants.TG)){
			shForm = dao.getModel(shForm);
			form.setSsfilepath(shForm.getSsfilepath());
			result= insertWjjgk(form);
		}
		
		
		return result;
	}

	/** 
	 * @����:(����ͨ����������)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-30 ����03:11:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	private boolean insertWjjgk(CfssshForm form) throws Exception{
		if(!"���Ĵ���".equalsIgnoreCase(form.getZzssjg())){
			form.setCfggw("");
		}
		return dao.insertWjjgk(form);
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
	public boolean cancel(CfssshForm model) throws Exception{
		CfssshForm shForm=new CfssshForm();
		shForm.setYwid(model.getYwid());
		shForm.setSsjg(Constants.SHZ);
		boolean result=dao.runUpdate(shForm, shForm.getYwid());
		if(result){
			int count=dao.udateJgk(model.getCfid());  //�޸Ľ����
			if(count>=0){
				//���ô�����Ϣ
				/*BaseDbcz dbcz=new BaseDbcz();
				dbcz.setShPath("wjcf_cfsssh.do?method=cxCfssshList");
				dbcz.setSqPath("wjcf_cfsssq.do?method=cxCfsssqList");
				dbcz.setGnmkMc("�����������");
				dbcz.setXmmc("�������");
				dbcz.shPush(model.getYwid(), model.getSplcid());*/
			}else{
				return false;
			}
		}
		
		return result;
	}

	/**
	 * ��ѯ������Ϣ
	 * @param form
	 * @return
	 */
	public InputStream fjCx(CfssshForm form) throws Exception {
		Blob blob = dao.fjCx("select fj from xg_wjcf_wjcfssb where ssid = ?", new String[]{form.getYwid()}, "fj");
		return blob.getBinaryStream();
	}

	
	
	/**
	 * @throws Exception  
	 * @����:������˱���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-24 ����01:55:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * String �������� 
	 * @throws 
	 */
	@TransactionControl
	public String savePlsh(CfssshForm t, User user) throws Exception {
		
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();

		List<String> failXhs = new ArrayList<String>();
		//�õ���Ҫ������˵����ݼ�
		WjcfJcszDao wjcfjcszdao = new WjcfJcszDao();
		HashMap<String, String> resultList = wjcfjcszdao.ssjcsplCx(null);

		for (int i = 0, n = ids.length; i < n; i++) {
			CfssshForm model = new CfssshForm();
			model.setSplcid(resultList.get("ssspl"));
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setShyj(t.getShyj());
			model.setShzt(t.getShzt());
			model.setXh(xhs[i]);

			boolean isSuccess = sssh(model, user);

			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}
		

		JSONArray json = JSONArray.fromObject(failXhs);

		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json
					.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json
					.toString());
		}
	}
}
