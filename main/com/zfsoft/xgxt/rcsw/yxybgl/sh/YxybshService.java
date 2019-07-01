/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-24 ����02:10:15 
 */  
package com.zfsoft.xgxt.rcsw.yxybgl.sh;

import java.util.ArrayList;
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
import com.zfsoft.xgxt.rcsw.yxybgl.jg.YxybjgDao;
import com.zfsoft.xgxt.rcsw.yxybgl.jg.YxybjgForm;
import com.zfsoft.xgxt.rcsw.yxybgl.jg.YxybjgService;
import com.zfsoft.xgxt.rcsw.yxybgl.sq.YxybsqDao;
import com.zfsoft.xgxt.rcsw.yxybgl.sq.YxybsqForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-24 ����02:10:15 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YxybshService extends SuperServiceImpl<YxybshForm, YxybshDao>{
	private YxybshDao dao = new YxybshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	/** 
	 * @����:�������
	 * @���ߣ�����[���ţ�982]
	 * @���ڣ�2016-3-25 ����09:47:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveSh(YxybshForm form, User user) {
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
		model.setSqrid(form.getXydm());
		model.setTzlj("rcsw_yxybgl_sh.do");
		model.setTzljsq("rcsw_yxybgl_sq.do");
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			YxybshForm sbshForm = new YxybshForm();
			sbshForm.setSqid(form.getSqid());
			sbshForm.setShzt(zhzt);
			result = dao.runUpdate(sbshForm, form.getSqid());
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				YxybjgForm yxybjgForm = new YxybjgForm();
				YxybjgService yxybjgService = new YxybjgService();
				YxybsqForm yxybsqForm = new YxybsqDao().getModel(form.getSqid());
				BeanUtils.copyProperties(yxybjgForm, StringUtils.formatData(yxybsqForm));
				yxybjgForm.setLylcywid(yxybsqForm.getSqid());
				yxybjgForm.setSjly("1");
				if(yxybjgService.isHaveRecordForjg(yxybjgForm)){
					//���������д������ݣ���ɾ���ٲ���
					new YxybjgDao().deleteForSq(yxybjgForm);
					yxybjgService.runInsert(yxybjgForm);
				}else{
					yxybjgService.runInsert(yxybjgForm);
				}								
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** 
	 * @����:����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-25 ����11:06:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws 
	 */
	public String cxshnew(String ywid, YxybshForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getSplc());
		dao.updateSqjl(ywid, shzt);
		return cancelFlag;
	}
	
	/** 
	 * @����:������ɾ�����������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-25 ����11:29:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean cancel(YxybshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
			// ɾ��������е�����
			YxybjgDao yxybjgDao = new YxybjgDao();
			result = yxybjgDao.delByLclyywid(myForm.getSqid());
		return result;
	}
	
	/** 
	 * @����:�������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-25 ����11:43:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws 
	 */
	public String savePlsh(YxybshForm t, User user) throws Exception{
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		List<String> failXms = new ArrayList<String>();
		YxybshForm model = new YxybshForm();
		for (int i = 0, n = ids.length; i < n; i++) {
			YxybshForm form = dao.getModel(ids[i]);			
			model.setSplc(form.getSplc());
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXms.add(ids[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXms);
		if (failXms.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	
}
