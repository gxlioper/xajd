/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-25 ����09:39:23 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.lstd.lstdjg.LstdjgForm;
import com.zfsoft.xgxt.rcsw.lstd.lstdjg.LstdjgService;
import com.zfsoft.xgxt.rcsw.lstd.lstdsq.LstdsqDao;
import com.zfsoft.xgxt.rcsw.lstd.lstdsq.LstdsqForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-11-25 ����09:39:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LstdshService extends SuperServiceImpl<LstdshForm, LstdshDao> {
	
	
	private LstdshDao dao = new LstdshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public LstdshService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:��ѯ������Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-26 ����09:16:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getLstdshInfo(LstdshForm model) {
		if (StringUtil.isNull(model.getSqid())) {
			logger.error("����ID����Ϊ�գ�");
			throw new NullPointerException();
		}
		return dao.getLstdshInfo(model);
	}
	
	/**
	 * 
	 * @����:��ɫͨ�����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-26 ����09:17:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSh(LstdshForm form,User user){
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
		model.setSqrid(form.getXh());
		model.setTzlj("rcsw_lstd_sh.do");
		model.setTzljsq("rcsw_lstd_sq.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			LstdshForm upForm = new LstdshForm();
			upForm.setSqid(form.getSqid());
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdate(upForm, form.getSqid());
			//���浽�����
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				LstdjgForm lstdjgForm = new LstdjgForm();
				LstdjgService lstdjgService = new LstdjgService();
				//����ID��ѯ������Ϣ
				LstdsqForm lstdsqForm = new LstdsqDao().getModel(form.getSqid());
				//����ѧ�š�ѧ�ꡢѧ��ɾ��������е�����
				lstdjgService.delForXhxnxq(lstdsqForm.getXh(),lstdsqForm.getXn(),lstdsqForm.getXq());
        		BeanUtils.copyProperties(lstdjgForm, StringUtils.formatData(lstdsqForm));
        		lstdjgForm.setJgid(lstdsqForm.getSqid());
        		lstdjgForm.setSjly("1");
        		lstdjgForm.setCzyh(user.getUserName());
        		lstdjgService.saveSqjg(lstdjgForm);	
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	
	/**
	 * 
	 * @����:����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-26 ����09:22:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean newCancelSh(LstdshForm model){
		boolean resultRcww = false;
		boolean resultRcxwjg = false;
		try {
			//�����ճ���Ϊ��Ϣά��
			resultRcww = dao.updateLstdsq(model.getSqid(), Constants.YW_SHZ);
			if(resultRcww){
				String shzt = model.getShzt();
				if(shzt != null && shzt.equals("2")){
					resultRcxwjg = true;
				}else{
					//ɾ���ճ���Ϊ����еļ�¼
					resultRcxwjg = dao.deleteLstdsq(model.getSqid());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultRcxwjg;
	}

	/**
	 * 
	 * @����:��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-26 ����09:23:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String savePlsh(LstdshForm t, User user) {
		
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			
			//�õ���ID��Ӧ����������ID
			LstdshDao lstdshdao = new LstdshDao();
			Map<String, String> resultList = lstdshdao.getOneLstdsqInfo(ids[i]);
			
			LstdshForm model = new LstdshForm();
			model.setSplc(resultList.get("splc"));
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			model.setXn(t.getXn());
			model.setXq(t.getXq());

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
