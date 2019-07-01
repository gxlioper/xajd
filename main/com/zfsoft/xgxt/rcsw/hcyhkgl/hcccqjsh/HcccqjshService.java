/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ� 2013-12-18 ����08:52:03 
 */  
package com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjjg.HcccqjjgDao;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjjg.HcccqjjgForm;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjjg.HcccqjjgService;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �𳵳����������ģ��
 * @�๦������: TODO(�𳵳����������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-26 ����09:37:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class HcccqjshService extends SuperServiceImpl<HcccqjshForm, HcccqjshDao> {

	private HcccqjshDao dao = new HcccqjshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public HcccqjshService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:TODO(��ѯ��ȡ�𳵳˳�����������Ϣ)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-24 ����01:50:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getHcccqjshInfo(HcccqjshForm model) {
		if (StringUtil.isNull(model.getCcqjtxid())) {
			logger.error("����ID����Ϊ�գ�");
			throw new NullPointerException();
		}
		return dao.getHcccqjshInfo(model);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:TODO(����𳵳˳��������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-24 ����02:29:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	@TransactionControl
	public boolean saveSh(HcccqjshForm form,User user) throws Exception{
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getSplc());
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
		model.setYwid(form.getCcqjtxid());
		model.setSqrid(form.getXh());
		model.setTzlj("rcsw_hcyhk_hcccqjsh.do");
		model.setTzljsq("rcsw_hcyhk_hcccqjtx.do");
		boolean reuslt = false;
		
			String zhzt = shlc.runAuditingNotCommit(model);
			HcccqjshForm upForm = new HcccqjshForm();
			upForm.setCcqjtxid(form.getCcqjtxid());
			//upForm.setBbsqid(form.getCcqjtxid());
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdateNotCommit(upForm, form.getCcqjtxid());
			//���״̬Ϊͨ�������ճ���Ϊ������б����������
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				HcccqjjgForm hcccqjjgForm = new HcccqjjgForm();
				form = getModel(form);
        		BeanUtils.copyProperties(hcccqjjgForm, StringUtils.formatData(form));
        		hcccqjjgForm.setCcqjjgid(form.getCcqjtxid());
        		hcccqjjgForm.setSjly("1");
        		hcccqjjgForm.setCcqjtxid(form.getCcqjtxid());
        		HcccqjjgDao jgDao = new HcccqjjgDao();
        		jgDao.deleteExist(hcccqjjgForm);
        		if(Base.xxdm.equals("13011")){        			
        			hcccqjjgForm.setShwcsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
        		}
        		jgDao.runInsertNotCommit(hcccqjjgForm);	
			}	
		
		return reuslt;
	}
	
	
	/**
	 * 
	 * @����:TODO(����ѧ��֤�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-18 ����03:46:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean newCancelSh(HcccqjshForm model){
		boolean resultHcccqjtx = false;
		boolean resultHcccqjjg = false;
		try {
			//���»𳵳˳�������д
			resultHcccqjtx = dao.updateHcccqjtx(model.getCcqjtxid(), Constants.YW_SHZ);
			if(resultHcccqjtx){
				String shzt = model.getShzt();
				if(shzt != null && shzt.equals("2")){
					resultHcccqjjg = true;
				}else{
					//ɾ���𳵳˳��������ļ�¼
					resultHcccqjjg = dao.deleteHcccqjtx(model.getCcqjtxid());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultHcccqjjg;
	}

	
	/**
	 * @throws Exception  
	 * @����:������˱���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-25 ����02:53:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * String �������� 
	 * @throws 
	 */
	@TransactionControl
	public String savePlsh(HcccqjshForm t, User user) throws Exception {
		
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			
			
			HcccqjshForm model = new HcccqjshForm();
			model.setSplc(splcs[i]);
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setCcqjtxid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			
			boolean isSuccess = saveSh(model, user);

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
