/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-31 ����01:53:33 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybjg.BjxqybjgForm;
import com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybjg.BjxqybjgService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �༶ѧ���±�����ģ��
 * @�๦������: TODO(������ҽҩ��ѧ_ѧ���±�_�༶ѧ�����) 
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-3-31 ����01:53:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BjxqybshService extends
		SuperServiceImpl<BjxqybshForm, BjxqybshDao> {
	
	private BjxqybshDao dao = new BjxqybshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����_�༶ѧ����ˡ��༶ѧ�������дҳ������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-6 ����08:54:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getBjxqybshInfo(User user,BjxqybshForm model){
		
		return dao.getBjxqybshInfo(user, model);
	}
	
	public boolean saveSh(BjxqybshForm form,User user){
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
		model.setYwid(form.getSqid());
		model.setSqrid(form.getTxr());
		model.setTzlj("rcsw_xqybgl_bjxqybgl_bjxqybsh.do");
		model.setTzljsq("rcsw_xqybgl_bjxqybgl_bjxqybsq.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			BjxqybshForm upForm = new BjxqybshForm();
			upForm.setSqid(form.getSqid());			
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdate(upForm, form.getSqid());
			//���״̬Ϊͨ�������ճ���Ϊ������б����������
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){				
				BjxqybjgForm bjxqybjgForm = new BjxqybjgForm();
				BjxqybjgService bjxqybjgService = new BjxqybjgService();
				form = getModel(form);
        		BeanUtils.copyProperties(bjxqybjgForm, StringUtils.formatData(form));       		
        		bjxqybjgForm.setLylcywid(form.getSqid());
        		//bjxqybjgForm.setJgid(form.getSqid());
        		bjxqybjgForm.setSjly("1");       		
        		bjxqybjgService.deleteExist(bjxqybjgForm);
        		bjxqybjgService.saveBjxqybjg(bjxqybjgForm,user);        		        	
        		
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����_�༶ѧ����ˡ�����ѧ���±����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-12 ����02:17:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean newCancelSh(BjxqybshForm model){
		boolean resultBjxqybsq = false;
		boolean resultBjxqybjg = false;
		try {
			//���°༶ѧ���±�����
			resultBjxqybsq = dao.updateBjxqybsq(model.getSqid(), Constants.YW_SHZ);
			if(resultBjxqybsq){
				String shzt = model.getShzt();
				if(shzt != null && shzt.equals("2")){
					resultBjxqybjg = true;
				}else{
					//ɾ���༶ѧ���±����
					resultBjxqybjg = dao.deleteBjxqybjg(model.getSqid());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultBjxqybjg;
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����_�༶ѧ����ˡ�������˱���)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-11 ����04:55:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String savePlsh(BjxqybshForm t, User user) {
			
			String[] ids = t.getId();
			String[] gwid = t.getGwids();
			String[] bjdms = t.getBjdms();
			String[] splcs = t.getSplcs();
	
			List<String> failBjdms = new ArrayList<String>();
	
			for (int i = 0, n = ids.length; i < n; i++) {			
				BjxqybshForm model = new BjxqybshForm();
				model.setSplc(splcs[i]);
				model.setYwid(ids[i]);
				model.setGwid(gwid[i]);
				model.setSqid(ids[i]);
				model.setShyj(t.getShyj());
				model.setShjg(t.getShzt());
				model.setBjdm(bjdms[i]);			
				boolean isSuccess = saveSh(model, user);
	
				if (!isSuccess) {
					failBjdms.add(bjdms[i]);
				}
			}
	
			JSONArray json = JSONArray.fromObject(failBjdms);
	
			if (failBjdms.isEmpty()) {
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
