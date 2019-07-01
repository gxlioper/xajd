/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-7 ����10:25:12 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.sh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import xgxt.form.User;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.cdgl.cdxxwh.CdxxwhForm;
import com.zfsoft.xgxt.rcsw.cdgl.cdxxwh.CdxxwhService;
import com.zfsoft.xgxt.rcsw.cdgl.jg.CdjgForm;
import com.zfsoft.xgxt.rcsw.cdgl.jg.CdjgService;
import com.zfsoft.xgxt.rcsw.cdgl.sq.CdsqAction;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: �ڶ�֤�����-SERVICE��
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-7 ����10:25:12 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CdshService extends SuperServiceImpl<CdshForm,CdshDao> {
	/**
	 * @���� ������Ϣ����ӿ�
	 */
	private CdxxwhService cdxxwhService = new CdxxwhService();
	
	private ShlcInterface shlc = new CommShlcImpl();

	public CdshService(){
		setDao(new CdshDao());
	}


	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-28 ����01:49:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveSh(CdshForm model, User user) throws Exception {
		
		CdshForm dataModel = dao.getModel(model.getSqid());
		
		ShxxModel shxxModel = new ShxxModel();
		shxxModel.setYwid(model.getSqid());
		shxxModel.setShlc(model.getSplcid());
		shxxModel.setShr(user.getUserName());
		shxxModel.setShyj(model.getShyj());
		shxxModel.setShzt(model.getShjg());
		shxxModel.setThgw(model.getThgw());
		shxxModel.setGwid(model.getXtgwid());
		shxxModel.setSqrid(model.getXh());
		shxxModel.setTzlj(CdshAction.PATH);
		shxxModel.setTzljsq(CdsqAction.PATH);
		
		boolean reuslt = false;
		try {
		
			String zhzt  = shlc.runAuditing(shxxModel); //��˲���{����һ�����ݵ���˱���}
			
			dataModel.setShzt(zhzt);//��ȡ���״̬��־��������Model
			
			reuslt = dao.runUpdate(dataModel);//���������{�������������״̬��Ϣ}
			
			if(reuslt && Constants.SH_TG.equals(zhzt)){ //������ͨ�� ����һ�����ݵ������
				CdjgForm cdjgModel = new CdjgForm();
				cdjgModel.setXh(dataModel.getXh());
				cdjgModel.setCdid(dataModel.getCdid());
				cdjgModel.setBmlbdm(dataModel.getBmlbdm());
				cdjgModel.setSqsj(dataModel.getSqsj());
				cdjgModel.setSqly(dataModel.getSqly());
				cdjgModel.setSqsjdkssj(dataModel.getSqsjdkssj());
				cdjgModel.setSqsjdjssj(dataModel.getSqsjdjssj());
				cdjgModel.setSjly("1");
				cdjgModel.setSplcid(dataModel.getSplcid());
				cdjgModel.setSqid(dataModel.getSqid());
				cdjgModel.setJgid(dataModel.getSqid());//�Ż�����������ݣ�ʹjgid��sqid����һ��
				cdjgModel.setCyrs(dataModel.getCyrs());
				cdjgModel.setFzrxm(dataModel.getFzrxm());
				cdjgModel.setFzrlxfs(dataModel.getFzrlxfs());
				cdjgModel.setXfgfsyxy(dataModel.getXfgfsyxy());
				new CdjgService().runInsert(cdjgModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reuslt;
	}
	
	
	/**
	 * @throws Exception 
	 * 
	 * @����:У�������ʱ��� > 1.�����������ʱ�����û�б����������룡
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-25 ����10:41:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkSqSjd(CdshForm model) throws Exception{
		
		CdshForm xmodel = getModel(model.getSqid());
		
		boolean booleaVal = true;
		
		CdxxwhForm cdxxModel = null ;
		if(xmodel != null && StringUtils.isNotBlank(xmodel.getCdid())){
			cdxxModel = cdxxwhService.getModel(xmodel.getCdid());
		}

		//�����������ʱ�����û�б�����������
		if(cdxxModel != null){
			List<HashMap<String , String>> yscdsqList = dao.getYxCdsq(cdxxModel.getCdid()); //��ȡ�Ѿ�����ĳ���ʹ���б�
			String sqkssj = xmodel.getSqsjdkssj();
			String sqjssj = xmodel.getSqsjdjssj();
			for (HashMap<String, String> cdsq : yscdsqList) {
				String sqsjdkssj = cdsq.get("sqsjdkssj");
				String sqsjdjssj = cdsq.get("sqsjdjssj");
				if(DateUtils.checkTimepriodDuplicate(sqkssj, sqjssj, sqsjdkssj, sqsjdjssj)){
					booleaVal = false;
					break;
				}
			}
		}
		
		return booleaVal;
	}

	/**
	 * @throws Exception  
	 * @����:�����������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-28 ����11:54:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String savePlsh(CdshForm t, User user) throws Exception {
		
		
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			
			
			CdshForm model = new CdshForm();
			model.setSqid(ids[i]);
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
	
	/**
	 * 
	 * @����: �鿴ʹ���е���������
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2014-10-23 ����01:49:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splcid
	 * @return
	 * Boolean �������� 
	 * @throws
	 */
	public Boolean isHaveSplcSj(String splcid, String cdid) {
		List<HashMap<String, String>> list = dao.getSplcing(splcid,cdid);
		return null == list || list.size() <= 0 ? false : true;
	}
	
}
