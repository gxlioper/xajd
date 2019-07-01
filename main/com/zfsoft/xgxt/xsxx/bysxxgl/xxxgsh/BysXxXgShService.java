/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-7-10 ����11:15:29 
 */
package com.zfsoft.xgxt.xsxx.bysxxgl.xxxgsh;

import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.bysxxgl.xxxgsq.BysXxXgSqForm;
import com.zfsoft.xgxt.xsxx.bysxxgl.xxxgsq.BysXxXgSqService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2014-7-10 ����11:15:29
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BysXxXgShService extends
		SuperServiceImpl<BysXxXgShForm, BysXxXgShDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	private BysXxXgSqService bysxxxgService = new BysXxXgSqService();
	private XsxxglService service = new XsxxglService();

	// private BaseDbcz dbcz = new BaseDbcz();
	/**
	 * 
	 * @����:TODO��˱���
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-10 ����03:58:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean saveSqSh(BysXxXgShForm form, User user) throws Exception {
		boolean result = true;
		// ��˲���Model��ʼ��

		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getSplc());
		// �����
		model.setShr(user.getUserName());
		// ������
		model.setShyj(form.getShyj());
		// ���״̬
		model.setShzt(form.getShjg());
		// ��˸�λID
		model.setGwid(form.getGwid());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getSqid());
		model.setSqrid(form.getXh());
		model.setTzlj("xsxx_new_bysxx_xxxgsh.do");
		model.setTzljsq("xsxx_new_bysxx_xxxgsq.do");
		String shzt = shlc.runAuditing(model);
		BysXxXgSqForm myform = new BysXxXgSqForm();
		myform.setShjg(shzt);
		myform.setSqid(form.getSqid());
		result = bysxxxgService.updateXgSq(myform);
		if (shzt.equals(Constants.SH_TG)) {
			// �޸�ѧ����Ϣ
			String sqid = form.getSqid();
			result = service.updateRecordForStu(sqid, form.getXh(), false, true);
		}

		return result;

	}

	public boolean savePlXgSh(BysXxXgShForm form,String dataJson, User user) throws Exception {
		boolean result=true;
		// ��˲���Model��ʼ��
		
		ShxxModel model = new ShxxModel();
		// �������ID
		// �����
		model.setShr(user.getUserName());
		// ������
		model.setShyj(form.getShyj());
		// ���״̬
		model.setShzt(form.getShjg());
		// ҵ��ID(��Ϊ����ID)
		model.setTzlj("xsxx_new_bysxx_xxxgsh.do");
		model.setTzljsq("xsxx_new_bysxx_xxxgsq.do");
		BysXxXgSqForm myform = new BysXxXgSqForm();
		dataJson = "{data:" + dataJson + "}";
		List list = JsonUtil.jsonToList(dataJson);
		String gwid = null;
		String ywid = null;
		String xh = null;
		String lcid = null;
		System.out.println("size:"+list.size());
		for (Object object : list) {
			net.sf.ezmorph.bean.MorphDynaBean bean = (net.sf.ezmorph.bean.MorphDynaBean) object;
			gwid = (String) bean.get("gwid");
			ywid = (String) bean.get("ywid");
			lcid = (String) bean.get("lcid");
			xh = (String) bean.get("xh");
			model.setGwid(gwid);
			model.setYwid(ywid);
			model.setSqrid(xh);
			model.setShlc(lcid);
			String shzt = shlc.runAuditing(model);
			if (shzt != null) {
				myform.setSqid(model.getYwid());
				myform.setShjg(shzt);
				result = bysxxxgService.updateXgSq(myform);
				if (shzt.equals(Constants.SH_TG)) {
					// �޸�ѧ����Ϣ
					String sqid = model.getYwid();
					result = service.updateRecordForStu(sqid,xh, false,true);
			}
			}
		}
		
		return result;
	}

	/**
	 * 
	 * @����:TODO��˳����˻�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-11 ����09:59:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean CxXgsq(String sqid) throws Exception {
		BysXxXgSqForm model = new BysXxXgSqForm();
		model.setSqid(sqid);

		boolean result = service.updateRecordForStu(sqid, true);
		if (result) {
			model.setShjg(Constants.YW_SHZ);
			result = bysxxxgService.updateXgSq(model);
		}
		return result;
	}
}
