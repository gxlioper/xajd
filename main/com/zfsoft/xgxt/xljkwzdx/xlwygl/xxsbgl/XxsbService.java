/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-26 ����03:23:23 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xxsbgl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;

import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xljkwzdx.xlwygl.xlsbjggl.XlsbjgForm;
import com.zfsoft.xgxt.xljkwzdx.xlwygl.xlsbjggl.XlsbjgService;
import com.zfsoft.xgxt.xljkwzdx.xlwygl.xxsbjggl.XxsbjgDao;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-5-26 ����03:23:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XxsbService extends SuperServiceImpl<XxsbForm, XxsbDao> {

	/**
	 * @���� ����������ӿ�
	 */
	private ShlcInterface shlc = new CommShlcImpl();
	
	private XlsbjgService xlsbjgService = new XlsbjgService();
	
	public static final String PATH_SH = "xljk_xlwygl_xxsbshgl.do";
	
	public static final String PATH_SQ = "xljk_xlwygl_xxsbgl.do";
	/**
	 * @throws Exception 
	 * 
	 * @����:������Ϣ�ϱ�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-27 ����03:50:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveXxsb(XxsbForm model) throws Exception{
		String type = model.getType();
		if(StringUtils.equals("save", type)){
			model.setSplcid("");
		}
		if(StringUtils.equals("update", type)){
			return runUpdate(model);
		}else if(StringUtils.equals("saveSubmit", type)){
			runUpdate(model);
			return submitXxsb(model);
		}
		boolean isSuccess = false;
		String uuid = UniqID.getInstance().getUniqIDHash().toUpperCase();
		model.setShzt(Constants.YW_WTJ);//�������״̬ δ�ύ
		model.setSbsqid(uuid);
		isSuccess = runInsert(model); //��������
		if(StringUtils.equals("submit", type) && isSuccess){
			if(isSuccess){
				isSuccess = shlc.runSubmit(model.getSbsqid(), model.getSplcid(), model.getXh(), PATH_SH, PATH_SQ);
				if(isSuccess){
					model.setShzt(Constants.YW_SHZ);
					isSuccess = runUpdate(model);
				}
			}
		}
		return isSuccess;
	}
	
	/**
	 * 
	 * @����:��ȡ�ϱ���Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-30 ����01:32:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sbsqid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String ,  String> getModelMap(String sbsqid){
		return dao.getModelMap(sbsqid);
	}
	
	/**
	 * 
	 * @����:�ύ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-25 ����02:52:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitXxsb(XxsbForm form) throws Exception{
		XxsbForm model = form;
		boolean isSuccess = false;
		if(model != null && StringUtils.isNotBlank(model.getSbsqid())){
			isSuccess = shlc.runSubmit(model.getSbsqid(), model.getSplcid(), model.getXh(), PATH_SH, PATH_SQ);
			if(isSuccess){
				model.setShzt(Constants.YW_SHZ);
				isSuccess = runUpdate(model);
			}
		}
		return isSuccess;
	}
	
	
	public XxsbService(){
		super.setDao(new XxsbDao());
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-25 ����03:34:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancel(String sbsqid) throws Exception{
		boolean isSuccess = false;
		XxsbForm model = null;
		if(sbsqid != null){
			model = getModel(sbsqid);
		}
		
		if(model != null){
			isSuccess = shlc.firstStepCancle(model.getSbsqid(), model.getSplcid());
		}
		
		if(isSuccess){
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(model.getSbsqid()))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			isSuccess = runUpdate(model);
		}
		
		return isSuccess;
	}
	
	
	
	public List<HashMap<String, String>> getSHPageList(XxsbForm t, User user)
		throws Exception {
		
		return dao.getSHPageList(t, user);
	}
	
	
	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ڣ�2014-4-28 ����01:49:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveSh(XxsbForm model, User user) throws Exception {
		
		XxsbForm dataModel = dao.getModel(model.getSbsqid());
		
		ShxxModel shxxModel = new ShxxModel();
		shxxModel.setYwid(model.getSbsqid());
		shxxModel.setShlc(model.getSplcid());
		shxxModel.setShr(user.getUserName());
		shxxModel.setShyj(model.getShyj());
		shxxModel.setShzt(model.getShjg());
		shxxModel.setThgw(model.getThgw());
		shxxModel.setGwid(model.getXtgwid());
		shxxModel.setSqrid(model.getXh());
		shxxModel.setTzlj(PATH_SH);
		shxxModel.setTzljsq(PATH_SQ);
		
		boolean reuslt = false;
		try {
			String zhzt  = shlc.runAuditing(shxxModel); //��˲���{����һ�����ݵ���˱���}
			dataModel.setShzt(zhzt);//��ȡ���״̬��־��������Model
			reuslt = dao.runUpdate(dataModel);//���������{�������������״̬��Ϣ}
			if(reuslt && Constants.SH_TG.equals(zhzt)){ //������ͨ�� ����һ�����ݵ������
				XlsbjgForm xlsbjgModel = new XlsbjgForm();
				xlsbjgModel.setXh(dataModel.getXh());
				xlsbjgModel.setSblx(dataModel.getSblx());
				xlsbjgModel.setSbsj(dataModel.getSbsj());
				xlsbjgModel.setSbzbid(dataModel.getSbzbid());
				xlsbjgModel.setZtqk(dataModel.getZtqk());
				xlsbjgModel.setXlxsxxqk(dataModel.getXlxsxxqk());
				xlsbjgModel.setBz(dataModel.getBz());
				xlsbjgModel.setSbsqid(dataModel.getSbsqid());
				xlsbjgModel.setSjly("1");
				xlsbjgModel.setXn(dataModel.getXn());
				xlsbjgModel.setXq(dataModel.getXq());
				xlsbjgModel.setSbjgid(UniqID.getInstance().getUniqIDHash().toUpperCase());
				reuslt = xlsbjgService.runInsert(xlsbjgModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reuslt;
	}
	
	
	/**
	 * @throws Exception  
	 * @����:�����������
	 * @���ߣ�zhangxiaobin [���ţ�1036]
	 * @���ڣ�2014-4-28 ����11:54:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String savePlsh(XxsbForm t, User user) throws Exception {
		
		
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			
			
			XxsbForm model = new XxsbForm();
			model.setSbsqid(ids[i]);
			model.setSplcid(splcs[i]);
			model.setXtgwid(gwid[i]);
			model.setXh(xhs[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			
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
	
	
	public HashMap<String,String> getXnXqmc(String zbid){
		return dao.getXnXqmc(zbid);	
	}
	
	public boolean cancel(XxsbForm myForm) throws Exception {
		
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSbsqid());
		// ɾ��������е�����
		XxsbjgDao jgDao = new XxsbjgDao();
		result = jgDao.deleteBySqid(myForm.getSbsqid());
		
		return result;	
	}
	
}
