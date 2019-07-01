/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-1 ����11:37:38 
 */  
package com.zfsoft.xgxt.gygl.zzdgl.sh;

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
import com.zfsoft.xgxt.gygl.zzdgl.jg.ZzdjgDao;
import com.zfsoft.xgxt.gygl.zzdgl.jg.ZzdjgForm;
import com.zfsoft.xgxt.gygl.zzdgl.jg.ZzdjgService;
import com.zfsoft.xgxt.gygl.zzdgl.sq.ZzdsqDao;
import com.zfsoft.xgxt.gygl.zzdgl.sq.ZzdsqForm;
import com.zfsoft.xgxt.zxdk.ypzl.jg.YpzljgDao;
import com.zfsoft.xgxt.zxdk.ypzl.jg.YpzljgForm;
import com.zfsoft.xgxt.zxdk.ypzl.jg.YpzljgService;
import com.zfsoft.xgxt.zxdk.ypzl.sh.YpzlshForm;
import com.zfsoft.xgxt.zxdk.ypzl.sq.YpzlSqDao;
import com.zfsoft.xgxt.zxdk.ypzl.sq.YpzlSqForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-1 ����11:37:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZzdshService extends SuperServiceImpl<ZzdshForm, ZzdshDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	private ZzdshDao dao = new ZzdshDao();
	
	/** 
	 * @����:�������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-1 ����04:05:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveSh(ZzdshForm form, User user) {
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
		model.setYwid(form.getZzdid());
		model.setSqrid(user.getUserName());
		model.setTzlj("xgygl_zzdgl_zdsh.do");
		model.setTzljsq("xgygl_zzdgl_zdsq.do");
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			ZzdshForm sbshForm = new ZzdshForm();
			sbshForm.setZzdid(form.getZzdid());
			sbshForm.setShzt(zhzt);
			result = dao.runUpdate(sbshForm, form.getZzdid());
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				ZzdjgForm zzdjgForm = new ZzdjgForm();
				ZzdjgService zzdjgService = new ZzdjgService();
				ZzdsqForm zzdsqForm = new ZzdsqDao().getModel(form.getZzdid());
				BeanUtils.copyProperties(zzdjgForm, StringUtils.formatData(zzdsqForm));
				zzdjgForm.setSjly("1");
				zzdjgForm.setCzy(user.getUserName());
				if(zzdjgService.isHaveRecordForjg(zzdjgForm)){
					//���������д������ݣ���ɾ���ٲ���
					new ZzdjgDao().deleteForSq(zzdjgForm);
					zzdjgService.runInsert(zzdjgForm);
				}else{
					zzdjgService.runInsert(zzdjgForm);
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
	 * @���ڣ�2016-3-1 ����04:07:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws 
	 */
	public String cxshnew(String ywid, ZzdshForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getSplcid());
		dao.updateSqjl(ywid, shzt);
		return cancelFlag;

	}
	
	/** 
	 * @����:���һ������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-1 ����04:26:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean cancel(ZzdshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getZzdid());
			// ɾ��������е�����
			ZzdjgDao zzdjgDao = new ZzdjgDao();
			result = zzdjgDao.delByZzdid(myForm.getZzdid());
		return result;
	}
	
	/** 
	 * @����:������˱���
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-1 ����05:19:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws 
	 */
	public String savePlsh(ZzdshForm t, User user) throws Exception{
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		List<String> failXms = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			ZzdshForm form = dao.getModel(ids[i]);
			ZzdshForm model = new ZzdshForm();
			model.setSplcid(form.getSplcid());
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setZzdid(ids[i]);
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
