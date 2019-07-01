package com.zfsoft.xgxt.rcsw.qjgl.xjsqsh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SpringLayout.Constraints;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglDao;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.jg.XyzsglService;
import xsgzgl.gygl.xyzsgl.sh.XyzsShForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgForm;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgService;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybsb.YbsbForm;
import com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.jcsz.XlzxSbService;

public class XjsqshService extends SuperServiceImpl<XjsqshForm,XjsqshDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * @throws Exception 
	 * 
	 * @����: ������������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-21 ����05:14:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveForm(XjsqshForm xjsqForm) throws Exception{
		boolean rs = true;
		xjsqForm.setXjsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		if("submit".equals(xjsqForm.getType())){
			xjsqForm.setShzt(Constants.YW_SHZ);
		}else{
			xjsqForm.setShzt(Constants.YW_WTJ);
		}
		xjsqForm.setSplc(new XjsqcsszService().getModel().getSplc());
		if(StringUtils.isNotNull(xjsqForm.getYwid())){
			rs = dao.runUpdate(xjsqForm);
		}else{
			xjsqForm.setYwid(UniqID.getInstance().getUniqIDHash().toUpperCase());
			rs = dao.runInsert(xjsqForm);
		}
		if("submit".equals(xjsqForm.getType())){
			if (rs) {
				rs = shlc.runSubmit(xjsqForm.getYwid(), xjsqForm.getSplc(),xjsqForm.getXjr(), "xg_qjgl_xjsh.do", "xg_qjgl_xjsq.do");
			}
		}
		return rs;
	}
	
	/**
	 * 
	 *��˱���
	 */
	public boolean saveSh(XjsqshForm form, User user) {
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
		model.setYwid(form.getYwid());
		model.setSqrid(form.getXh());
		model.setTzlj("xg_qjgl_xjsh.do");
		model.setTzljsq("xg_qjgl_xjsq.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			form.setShzt(zhzt);
			reuslt = dao.runUpdate(form,form.getYwid());
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				QjjgForm jgForm = new QjjgForm();
				form = dao.getModel(form.getYwid());
				BeanUtils.copyProperties(jgForm, StringUtils.formatData(form));
				jgForm.setXjzt("1");
				//�����ֱ�Ӹ��ǽ����ֱ��ά��������(������������������������ȼ���)
				new QjjgService().runUpdate(jgForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	//�������
	public String savePlsh(XjsqshForm t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		XjsqshForm model = new XjsqshForm();
		String[] ids = t.getIds();
		String[] gwids = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();
		List<String> failXhs = new ArrayList<String>();
		//Ҫ��Ҫ����֤�д��о�
	
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSplc(t.getSplc());
			model.setYwid(ids[i]);
			model.setGwid(gwids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			model.setXjr(xhs[i]);
			model.setSplc(splcs[i]);
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
	
	/**
	 * 
	 * @����: ���һ������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-25 ����01:43:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancel(XjsqshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getYwid());
		if (result) {
			//�ÿս�����еļ�¼
			dao.cxLastSh(myForm.getQjjgid());
		
		}
		return result;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-25 ����01:45:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String cxshnew(String ywid, XjsqshForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		model.setShzt(shzt);
		dao.runUpdate(model,ywid);
		return cancelFlag;

	}
	
	//����
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	public XjsqshForm getModelOfqjjgid(String qjjgid) throws Exception{
		return dao.getModelOfqjjgid(qjjgid);
		
	}
	
	/**
	 * 
	 * @����: �ύ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-27 ����05:17:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitBusi(XjsqshForm model, User user)  throws Exception {
		String splc = new XjsqcsszService().getModel().getSplc();
        if(Constants.YW_YTH.equals(model.getShzt())){
        	splc = model.getSplc();
		}
        model.setShzt(Constants.YW_SHZ);
		model.setSplc(splc);
		boolean flag = dao.runUpdate(model);
		if(flag){
			 shlc.runSubmit(model.getYwid(), splc, model.getXjr(), "xg_xlzxnew_ybsh.do", "xg_xlzxnew_ybsb.do");
		}
		return flag;
	}
	
	/**
	 * ɾ��
	 * @throws SQLException 
	 */
	@Override
	public int runDelete(String[] values) throws SQLException{
		return dao.runDelete(values);
	}
	
	/**
	 * 
	 * @����: �ж��Ƿ����ɾ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-4 ����02:53:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isCanDelete(String[] values){
		return dao.isCanDelete(values);
	}
}
