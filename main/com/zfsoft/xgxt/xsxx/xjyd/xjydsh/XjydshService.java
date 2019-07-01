package com.zfsoft.xgxt.xsxx.xjyd.xjydsh;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import xgxt.action.Base;
import xgxt.form.User;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xjyd.xjydjg.XjydjgForm;
import com.zfsoft.xgxt.xsxx.xjyd.xjydjg.XjydjgService;
import com.zfsoft.xgxt.xsxx.xjyd.xjydsq.XjydsqForm;
import com.zfsoft.xgxt.xsxx.xjyd.xjydsq.XjydsqService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����
 * @�๦������:ѧ���춯���
 * @���ߣ� qilm
 * @ʱ�䣺2013-9-29
 * @�汾�� V1.0
 */
public class XjydshService extends SuperServiceImpl<XjydshForm, XjydshDAO> {
	/**
	 * ������Դ��1[������]
	 */
	private static final String SJLY_SPL = "1";
	
	private XjydshDAO dao = new XjydshDAO();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public XjydshService() {
		super.setDao(dao);
	}
	/**
	 * @����:ѧ���춯���
	 * @���ߣ�qilm
	 * @���ڣ�2013-9-29
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean ydsh(XjydshForm form,User user) throws Exception{
		
		// ��˲���Model��ʼ��
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getSplcid());
		// �����
		model.setShr(user.getUserName());
		// ������
		model.setShyj(form.getShyj());
		// ���״̬
		model.setShzt(form.getShjg());
		// �˻ظ�λ
		model.setThgw(form.getThgw());
		// ��˸�λID
		model.setGwid(form.getGwid());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getXjydsqid());
		model.setSqrid(form.getXh());
		model.setTzlj("xjyd_xjydsh.do");
		model.setTzljsq("xjyd_xjydsq.do");
		
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			XjydshForm upForm = new XjydshForm();
			upForm.setShzt(zhzt);
			result = dao.runUpdate(upForm, form.getXjydsqid());
			
			// ���һ�����ͨ��ʱ
			if(result && zhzt.equals(Constants.YW_TG)){
				
				// ����ѧ���춯�����
				result = insertYdjg(form);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return result;
	}
	
	/**
	 * 
	 * @����:����ѧ���춯�����
	 * @���ߣ�qilm
	 * @���ڣ�2013-9-29
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	private boolean insertYdjg(XjydshForm form) throws Exception{
		
		boolean bolFlg = false;
		XjydjgForm ydjgForm = new XjydjgForm();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		XjydjgService xjydjgService = new XjydjgService();
		XjydsqService xjydsqService = new XjydsqService();
		
		// �춯���
		XjydsqForm  ydsqForm = xjydsqService.getModel(form.getXjydsqid());
		String guid = UniqID.getInstance().getUniqIDHash();
		ydjgForm.setXjydjgid(guid);
		ydjgForm.setXh(ydsqForm.getXh());
		ydjgForm.setJrsj(date);
		ydjgForm.setXn(ydsqForm.getXn());
		ydjgForm.setXq(ydsqForm.getXq());
		ydjgForm.setYdlbdm(ydsqForm.getYdlbdm());
		ydjgForm.setFilepath(ydsqForm.getFilepath());
		
		// �춯ǰ
		ydjgForm.setYdqnj(ydsqForm.getYdqnj());
		ydjgForm.setYdqxydm(ydsqForm.getYdqxydm());
		ydjgForm.setYdqzydm(ydsqForm.getYdqzydm());
		ydjgForm.setYdqbjdm(ydsqForm.getYdqbjdm());
		ydjgForm.setYdqxjlb(ydsqForm.getYdqxjlb());
		ydjgForm.setYdqxjlbmc(ydsqForm.getYdqxjlbmc());
		ydjgForm.setYdqsfyxjmc(ydsqForm.getYdqsfyxjmc());
		ydjgForm.setYdqsfzxmc(ydsqForm.getYdqsfzxmc());
		
		// �춯��(��˺��趨)
		ydjgForm.setYdhnj(form.getYdhnj());
		ydjgForm.setYdhxydm(form.getYdhxydm());
		ydjgForm.setYdhzydm(form.getYdhzydm());
		ydjgForm.setYdhbjdm(form.getYdhbjdm());
		ydjgForm.setYdhxjlb(ydsqForm.getYdlbdm());
		
		// ����ʦ�����Ի��ֶ�
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			ydjgForm.setXz(form.getXz());
			ydjgForm.setYdyydm(form.getYdyydm());
			ydjgForm.setSfsfs(form.getSfsfs());
			ydjgForm.setLyqxxxdm(form.getLyqxxxdm());
			ydjgForm.setDqztdm(form.getDqztdm());
		}
		
		// �趨������Դ
		ydjgForm.setSjly(SJLY_SPL);					
		ydjgForm.setXjydsqid(ydsqForm.getXjydsqid());

		// ��������
		ydjgForm.setSqly(ydsqForm.getSqly());
		ydjgForm.setSqr(ydsqForm.getSqr());
		
		// �ĺ�ʱ�䱸ע
		ydjgForm.setXjydwh(form.getXjydwh());
		ydjgForm.setXjydsj(form.getXjydsj());
		ydjgForm.setXjydbz(form.getXjydbz());
		
		// ������ֹʱ��
		ydjgForm.setSqkssj(form.getSqkssj());
		ydjgForm.setSqjssj(form.getSqjssj());
		
		String xjydjgidTemp = xjydjgService.queryExistId(ydjgForm, "add");
		if (!"".equals(xjydjgidTemp)){
			ydjgForm.setXjydjgid(xjydjgidTemp);
			bolFlg = xjydjgService.runUpdate(ydjgForm);
		}else{
			bolFlg = xjydjgService.runInsert(ydjgForm);
		}
		if(bolFlg){
			// ����ѧ����Ϣ
			bolFlg = xjydjgService.updateXsxx(ydjgForm);
		}
		
		return bolFlg;
	}
	
	/** 
	 * @����:���һ���������
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-10
	 * @param shlc �������ID
	 * @param ssydsqid ����ID
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean cancel(String shlc, String xjydsqid) throws Exception{
		
		XjydjgService ydjgService = new XjydjgService();
		
		// �������������״̬
		XjydshForm upForm = new XjydshForm();
		upForm.setXjydsqid(xjydsqid);
		// ����ǰ����ȡ�����¼�����״̬
		String oldShzt = getModel(upForm).getShzt();
		upForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(upForm, xjydsqid);
		
		if(result && Constants.YW_TG.equals(oldShzt)){

			XjydjgForm myForm = ydjgService.getModelBySqid(xjydsqid);
			// �ع�ѧ����Ϣ
			result = ydjgService.rollBackXsxx(myForm.getXjydjgid());
			if(result){
				
				// ɾ���������Ӧ��ѧ���춯�����
				int count = ydjgService.runDeleteYdjg(xjydsqid);
				if(count > 0 ){
				}else{
					result = false;
				}
			}
		}
		return result;
		
	}

	/**
	 * 
	 * @����:�Ƿ������һ����λ
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-11 ����03:35:05
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isZhgw(String gwid ,String splc) {
		ArrayList<HashMap<String, String>> spgws = dao.getSplcgw(splc);
		String spgw=spgws.get(spgws.size()-1).get("spgw");
		return gwid.equalsIgnoreCase(spgw);
	}
}
