/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-4 ����05:06:08 
 */  
package com.zfsoft.xgxt.szdw.fdyrz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ�� 
 * @�๦������: TODO ����Ա��ְ���� ��ְ����
 * @���ߣ� zhangjw 
 * @ʱ�䣺 2013-6-4 ����04:56:01 
 * @�汾�� V5.8.16
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class FdyrzshService extends SuperServiceImpl<FdyrzsqForm, FdyrzshDAO>{

	private FdyrzshDAO dao = new FdyrzshDAO();
	
	private ShlcInterface shlc = new CommShlcImpl();
	
	public FdyrzshService(){
		super.setDao(dao);
	}
	/**
	 * @����:��ȡ�༶�б�
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-7 ����02:13:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getBjList(String sqid){
		return dao.getBjListBysqid(sqid);
	}
	public boolean fdyrzsh(FdyrzsqForm form,User user) throws Exception{
		ShxxModel model = new ShxxModel();
		model.setShlc(form.getSplc());
		model.setShr(user.getUserName());
		model.setShyj(form.getShyj());
		model.setShzt(form.getShzt());
		model.setGwid(form.getGwid());//
		model.setYwid(form.getSqid());
		model.setThgw(form.getThgw());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getSqid());
		model.setSqrid(form.getZgh());
		model.setTzlj("szdw_fdyrz_sh.do?method=gjcxRzsh");
		model.setTzljsq("szdw_fdyrz_sq.do?method=gjcxWdsq");
		if(form.getThgw()==null||form.getThgw().equals("")){
			
		}
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			FdyrzsqForm upForm = new FdyrzsqForm();
			upForm.setSqid(form.getSqid());
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdate(upForm, form.getSqid());
			if(zhzt.equals(Constants.OPEN)){
				/*HashMap<String,String> xssqxxMap = dao.getXsSqxx(model.getYwid());
				reuslt =dao.bcGwxs(xssqxxMap.get("gwdm"), xssqxxMap.get("xh"));*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	public boolean newCancelSh(FdyrzsqForm model){
		boolean resultFdyrzsq = false;
		try {
			model.setShzt(Constants.YW_SHZ);
			//�����ճ���Ϊ��Ϣά��
			resultFdyrzsq = dao.updateFdyrzsq(model)>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultFdyrzsq;
	}
	
	
	/**
	 * @throws Exception  
	 * @����:������˱���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-29 ����02:18:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String savePlsh(FdyrzsqForm t, User user) throws Exception {

		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			
			
			FdyrzsqForm model = new FdyrzsqForm();
			
			model.setSplc(splcs[i]);
			model.setShyj(t.getShyj());
			model.setShzt(t.getShzt());
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setZgh(xhs[i]);
			
			
			boolean isSuccess = fdyrzsh(model, user);

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
