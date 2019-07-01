/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-24 ����11:11:18 
 */  
package com.zfsoft.xgxt.zxdk.ypzl.sh;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.zxdk.ypzl.comm.YpzlUtil;
import com.zfsoft.xgxt.zxdk.ypzl.jg.YpzljgDao;
import com.zfsoft.xgxt.zxdk.ypzl.jg.YpzljgForm;
import com.zfsoft.xgxt.zxdk.ypzl.jg.YpzljgService;
import com.zfsoft.xgxt.zxdk.ypzl.sq.YpzlSqDao;
import com.zfsoft.xgxt.zxdk.ypzl.sq.YpzlSqForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-2-24 ����11:11:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YpzlshService extends SuperServiceImpl<YpzlshForm, YpzlshDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	private YpzlshDao dao = new YpzlshDao();
	
	/** 
	 * @����:�������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-24 ����11:13:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveSh(YpzlshForm form, User user) {
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
		model.setSqrid(user.getUserName());
		//����ֶλ�ʦ����Ի�
		if("10511".equals(Base.xxdm)){
			model.setZd1(form.getZd1());
			model.setZd3(form.getZd3());
		}
		model.setTzlj("zxdk_ypzl_ypzlsh.do");
		model.setTzljsq("zxdk_ypzl_ypzlsq.do");
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			YpzlshForm sbshForm = new YpzlshForm();
			sbshForm.setSqid(form.getSqid());
			sbshForm.setShzt(zhzt);
			result = dao.runUpdate(sbshForm, form.getSqid());
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				YpzljgForm ypzljgForm = new YpzljgForm();
				YpzljgService ypzljgService = new YpzljgService();
				YpzlSqForm ypzlSqForm = new YpzlSqDao().getModel(form.getSqid());
				BeanUtils.copyProperties(ypzljgForm, StringUtils.formatData(ypzlSqForm));
				ypzljgForm.setLylcywid(ypzlSqForm.getSqid());
				ypzljgForm.setSjly("1");
				//��ʦ����Ի��ж�
				if("10511".equals(Base.xxdm)){
					String sqrq = GetTime.getTimeByFormat("yyyy-mm-dd");
					if(new YpzlUtil().checkIsExistsSameDate(sqrq, ypzlSqForm.getXh(), "jg")){
						new YpzlUtil().delYpjgHsd(sqrq, ypzlSqForm.getXh());
					}
					ypzljgForm.setSqje(form.getZd3());
					ypzljgService.runInsert(ypzljgForm);
				}else{
					if(ypzljgService.isHaveRecordForjg(ypzljgForm)){
						//���������д������ݣ���ɾ���ٲ���
						new YpzljgDao().deleteForSq(ypzljgForm);
						ypzljgService.runInsert(ypzljgForm);
					}else{
						ypzljgService.runInsert(ypzljgForm);
					}
				}
				
								
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** 
	 * @����:���һ������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-24 ����02:55:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean cancel(YpzlshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
			// ɾ��������е�����
			YpzljgDao ypzljgDao = new YpzljgDao();
			result = ypzljgDao.delByLclyywid(myForm.getSqid());
		return result;
	}
	
	/** 
	 * @����:�������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-24 ����03:16:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String savePlsh(YpzlshForm t, User user) throws Exception{
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		List<String> failXms = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			YpzlshForm form = dao.getModel(ids[i]);
			YpzlshForm model = new YpzlshForm();
			model.setSplc(form.getSplc());
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setZd1(t.getZd1());
			model.setZd3(t.getZd3());
			
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
	
	public String cxshnew(String ywid, YpzlshForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getSplc());
		dao.updateSqjl(ywid, shzt);
		return cancelFlag;

	}
}
