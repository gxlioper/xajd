
package com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwsh;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwjg.RcxwjgDao;
import com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwjg.RcxwjgForm;
import com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwjg.RcxwjgService;
import com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwxxwh.RcxwxxwhDao;
import com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwxxwh.RcxwxxwhForm;

/** 
 * �ճ���Ϊ���  
 */
public class RcxwshService extends SuperServiceImpl<RcxwshForm, RcxwshDao> {

	private RcxwshDao dao = new RcxwshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public RcxwshService() {
		super.setDao(dao);
	}
	
	/**
	 * ��ѯ��ȡ������Ϣ
	 */
	public HashMap<String, String> getSplcInfo(RcxwshForm model) {
		if (StringUtil.isNull(model.getId())) {
			logger.error("����ID����Ϊ�գ�");
			throw new NullPointerException();
		}
		return dao.getSplcInfo(model);
	}
	
	/**
	 * �����ճ���Ϊ���
	 * @throws Exception 
	 */
	@TransactionControl
	public boolean saveSh(RcxwshForm form,User user) throws Exception{

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
		model.setYwid(form.getId());
		model.setSqrid(form.getXh());
		model.setTzlj("rcsw_rcxwwhnew_rcxwsh.do");
		model.setTzljsq("rcsw_rcxwwhnew_rcxwxxwh.do");
		// ====== ����������ֵ begin =========
		model.setZd1("�������ֵ");
		model.setZd2(form.getShfz());
		if("01".equals(form.getRcxwfzlx())){
			model.setZd3("+"+form.getShfz());
		}else{
			model.setZd3("-"+form.getShfz());
		}
		// ====== ����������ֵ end =========
		boolean reuslt = false;
		
			String zhzt = shlc.runAuditingNotCommit(model);
			RcxwshForm upForm = new RcxwshForm();
			upForm.setId(form.getId());
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdateNotCommit(upForm, form.getId());
			//���״̬Ϊͨ�������ճ���Ϊ������б����������
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				
				RcxwxxwhForm xwModel = new RcxwxxwhForm();
				xwModel.setId(form.getId());
				RcxwxxwhForm shModel = new RcxwxxwhDao().getModel(xwModel);
				
				RcxwjgForm rcxwjgForm = new RcxwjgForm();
        		BeanUtils.copyProperties(rcxwjgForm, shModel);
        		rcxwjgForm.setId(form.getId());
        		rcxwjgForm.setSjly("1");
        		rcxwjgForm.setRcxwxxid(form.getId());
        		rcxwjgForm.setFz(form.getShfz()); // �����������ֵΪ׼
        		RcxwjgDao xxwhDao = new RcxwjgDao();
        		xxwhDao.runInsertNotCommit(rcxwjgForm);	
			}	
		
		return reuslt;
	}
	@TransactionControl
	public boolean plsh(String shzt,String shyj,String info,User user) throws Exception{
		
		ShlcDao shlcDao = new ShlcDao();
		
		JSONArray array = JSONArray.fromString(info);
		boolean result = false;
		for (int i = 0 ; i < array.length() ; i++){
			JSONObject json = (JSONObject) array.get(i);
			
			RcxwshForm form = new RcxwshForm();
			form.setSplc(json.getString("splc"));
			form.setShyj(shyj);
			form.setShjg(shzt);
			form.setGwid(ShlcUtil.getDqGw(json.getString("id")));
			form.setId(json.getString("id"));
			form.setXh(json.getString("xh"));
			// ========== ��˷�ֵ���� begin ============
			HashMap<String,String> infoList = getSplcInfo(form);
			List<HashMap<String, String>> shyjList = shlcDao.getShyjList(json.getString("id"), "desc");
			String shfz = infoList.get("sqfz");
			if(shyjList.size() > 0){
				HashMap<String, String> shyjMap = shyjList.get(0);
				shfz = shyjMap.get("zd2");
			}
			form.setShfz(shfz);
			form.setRcxwfzlx(infoList.get("rcxwfzlx"));
			// ========== ��˷�ֵ���� end ============
			result = saveSh(form, user);
			if (!result){
				break;
			}
		}
		return result;
	}
	
	
	/**
	 * �����ճ���Ϊ���
	 */
	public boolean cancelSh(RcxwshForm form,User user){
		boolean reuslt = false;
		try {
			reuslt = shlc.runCancel(user.getUserName(),form.getId(),form.getSplc(),form.getGwid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	public boolean newCancelSh(RcxwshForm model){
		boolean resultRcww = false;
		boolean resultRcxwjg = false;
		try {
			//�����ճ���Ϊ��Ϣά��
			resultRcww = dao.updateRcxwxx(model.getId(), Constants.YW_SHZ);
			if(resultRcww){
				String shzt = model.getShzt();
				if(shzt != null && shzt.equals("2")){
					resultRcxwjg = true;
				}else{
					//ɾ���ճ���Ϊ����еļ�¼
					resultRcxwjg = dao.deleteRcxwjg(model.getId());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultRcxwjg;
	}

}
