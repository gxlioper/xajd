/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-26 ����02:02:28 
 */  
package xsgzgl.gygl.xyzsgl.sh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglDao;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.jg.XyzsglService;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;
import xsgzgl.qgzx.mrgzkh.khjg.GzkhKhjgDao;
import xsgzgl.qgzx.mrgzkh.khjg.GzkhKhjgForm;
import xsgzgl.qgzx.mrgzkh.khjg.GzkhKhjgService;
import xsgzgl.qgzx.mrgzkh.khsh.GzkhKhshForm;
import xsgzgl.qgzx.mrgzkh.khsq.GzkhKhsqDao;
import xsgzgl.qgzx.mrgzkh.khsq.GzkhKhsqForm;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2015-5-26 ����02:02:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XyzsShService extends SuperServiceImpl<XyzsShForm, XyzsshDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 *��˱���
	 */
	public boolean saveSh(XyzsShForm form, User user) {
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
//		model.setZd1("��Ч��ʱ");
//		model.setZd2(form.getGs());
//		model.setZd3(form.getYxgs());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getSqbh());
		model.setSqrid(form.getXh());
		model.setTzlj("gygl_xyzssh.do");
		model.setTzljsq("gygl_xyzssq.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			XyzsShForm zsshForm = new XyzsShForm();
			zsshForm.setSqbh(form.getSqbh());
			zsshForm.setShzt(zhzt);
			reuslt = dao.runUpdate(zsshForm, form.getSqbh());
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				XyzsglForm zsjgForm = new XyzsglForm();
				XyzsglService zsjgService = new XyzsglService();
				XyzsSqForm zssqForm = new XyzsSqDao().getModel(form.getSqbh());
				BeanUtils.copyProperties(zsjgForm, StringUtils.formatData(zssqForm));
//				khjgService.Cjcl(khjgForm);
				
				//�������������Ƿ���ͬѧ��ͬѧ�ŵ�������������У��������أ�������д�����
				zsjgForm.setXh(form.getXh());
				XyzsglDao gldao = new XyzsglDao();
				if(!gldao.checkExistForSave2(zsjgForm).equals("")&&gldao.checkExistForSave2(zsjgForm) != null){
					XyzsglDao dao = new XyzsglDao();
					dao.delZsjgById(gldao.checkExistForSave2(zsjgForm));
				}
				zsjgForm.setSqbh(zssqForm.getSqbh());
				zsjgForm.setSjly("1");
				reuslt = zsjgService.runInsert(zsjgForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	//�������
	public String savePlsh(XyzsShForm t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		XyzsShForm model = new XyzsShForm();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		List<String> failXhs = new ArrayList<String>();
		//Ҫ��Ҫ����֤�д��о�
	
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplc(t.getSplc());
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqbh(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			model.setXn(t.getXn());

			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	
	public boolean cancel(XyzsShForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqbh());
		if (result) {
			XyzsglDao jgdao = new XyzsglDao();
		
			// ɾ��������е�������
			
			jgdao.delZsjgById(myForm.getSqbh());
		
		}
		return result;
	}

	public String cxshnew(String ywid, XyzsShForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		dao.updateSqjl(ywid, shzt);
		return cancelFlag;

	}

}
