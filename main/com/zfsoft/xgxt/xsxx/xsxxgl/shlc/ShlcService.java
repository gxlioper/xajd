/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:06:43 
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.shlc;

import java.util.HashMap;
import java.util.List;

import xsgzgl.xsxx.general.jcsz.XsxxJcszService;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxxg.XgsqModel;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxxg.XxxgService;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������:ѧ����Ϣ
 * @�๦������: �޸��������
 * @���ߣ� ligl
 * @ʱ�䣺 2013-12-11 ����01:36:08
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class ShlcService extends SuperServiceImpl<ShlcModel, ShlcDao> {
	ShlcDao dao = new ShlcDao();
	private ShlcInterface shlc = new CommShlcImpl();
	//private TranscationManager transcationManager = new TranscationManager();

	public ShlcService() {
		this.setDao(dao);
	}

	/**
	 * @throws Exception 
	 * 
	 * @����:�����޸����
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-11 ����01:36:22
	 * @�޸ļ�¼:
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean saveXgsh(ShxxModel model) throws Exception{	
		boolean result = true;
		String shlid = null;
		HashMap<String, String> splMap = new XsxxJcszService().getOnesCssz();
		shlid = splMap.get("shlid");
		// �������ID
		model.setShlc(shlid);
		// ���״̬
		model.setShzt(model.getShjg());
		model.setTzlj("xsxx_xsxxxg_xgsh.do");
		model.setTzljsq("xsxx_xsxxxg_xgsq.do");
		String shzt = shlc.runAuditing(model);
		if (shzt != null) {
			XxxgService xxxgService = new XxxgService();
			XgsqModel xgsqModel = new XgsqModel();
			xgsqModel.setSqid(model.getYwid());
			xgsqModel.setShjg(shzt);
			result = xxxgService.updateXgsqZt(xgsqModel);
			
			if(!result){
				throw new SystemException(MessageKey.SYS_AUD_ERROR);
			}
			
			if (shzt.equals(Constants.SH_TG)) {
				// �޸�ѧ����Ϣ
				String sqid = model.getYwid();
				result = new XsxxglService().updateRecordForStu(sqid, false);
			}
			if(!result){
				throw new SystemException(MessageKey.SYS_AUD_ERROR);
			}
		}
	 	
		return result;
	}
	public boolean savePlxgsh(ShxxModel model, String dataJson) throws Exception
			 {
		boolean isTh =false;
		boolean result = true;
		String shlid = null;
		if("3".equals(model.getShjg())&&"3".equals(model.getThgw())){//�˻���һ��
			isTh=true;
		}
		HashMap<String, String> splMap = new XsxxJcszService().getOnesCssz();
		shlid = splMap.get("shlid");
		// �������ID
		model.setShlc(shlid);
		// ���״̬
		model.setShzt(model.getShjg());
		model.setTzlj("xsxx_xsxxxg_xgsh.do");
		model.setTzljsq("xsxx_xsxxxg_xgsq.do");
		
		XxxgService xxxgService = new XxxgService();
		XgsqModel xgsqModel = new XgsqModel();
		dataJson = "{data:" + dataJson + "}";
		List list = JsonUtil.jsonToList(dataJson);
		String gwid = null;
		String ywid = null;
		String xh = null;
		for (Object object : list) {
			net.sf.ezmorph.bean.MorphDynaBean bean = (net.sf.ezmorph.bean.MorphDynaBean) object;
			gwid = (String) bean.get("gwid");
			if(isTh){//��ѯ�˻����ϼ���gwid
				String spgw = new com.zfsoft.xgxt.comm.shlc.dao.ShlcDao().getBeforeGwid(model.getShlc(), gwid);
				model.setThgw(spgw);
			}
			ywid = (String) bean.get("ywid");
			xh = (String) bean.get("xh");
			model.setGwid(gwid);
			model.setYwid(ywid);
			model.setSqrid(xh);
			
			String shzt = shlc.runAuditing(model);
			if (shzt != null) {
				xgsqModel.setSqid(model.getYwid());
				xgsqModel.setShjg(shzt);
				result = xxxgService.updateXgsqZt(xgsqModel);
				if(!result){
					throw new SystemException(MessageKey.SYS_AUD_ERROR);
				}
				if (shzt.equals(Constants.SH_TG)) {
					// �޸�ѧ����Ϣ
				
					String sqid = model.getYwid();
					result = new XsxxglService()
							.updateRecordForStu(sqid, false);
					
				}
				if(!result){
					throw new SystemException(MessageKey.SYS_AUD_ERROR);
				}
			}
			
		}
		return result;
	}

}
