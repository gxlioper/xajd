/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-6 ����09:34:55 
 */  
package com.zfsoft.xgxt.qgzx.xsgwnew.sh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.qgzx.xsgwnew.sq.XsgwsqDao;
import com.zfsoft.xgxt.qgzx.xsgwnew.sq.XsgwsqForm;
import com.zfsoft.xgxt.qgzx.xsgwnew.sq.XsgwsqService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ڹ���ѧ
 * @�๦������: ѧ����λ���  
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-6-6 ����09:34:55 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsgwshService extends SuperServiceImpl<XsgwshnewForm, XsgwshDao>{
	
	private XsgwshDao dao = new XsgwshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * ��־û��ͨ�� ����֤Ȩ�����
	 */
	private final String _WTGSJ = "-1";
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-8 ����08:23:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param shr
	 * @param shid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cxRollBack(String shr, String shid) throws Exception {
		boolean isOk = true;
		HashMap<String, String> shxx = ShlcUtil.getShxx(shid);
		String ywid = shxx.get("ywid");
		String gwid = shxx.get("gwid");
		XsgwshnewForm xf = getModel(ywid);
		if (cxsqgw(shr, xf, xf.getSqbh(), xf.getGwdm(), gwid)) {
			isOk = dao.runUpdate(xf);
		}
		return isOk;
	}
	
	public boolean cxsqgw(String userid, XsgwshnewForm xf, String sqbh,
			String ywGwdm, String cxGwdm) {
		
		boolean isChange = false;
		HashMap<String, String> oldSpxx = dao.getSjTzShxx(sqbh, ywGwdm);
		if (null == cxGwdm) {
			return true;
		}
		cxGwdm=ShlcUtil.getUpSpgw(xf.getSplc(), cxGwdm); 
		xf.setShgw(cxGwdm);
		isChange = true;		
		if (null != oldSpxx && oldSpxx.size() > 0) {
			xf.setGwdm(oldSpxx.get("zd2"));
			isChange = true;
		}
		return isChange;
	}
	
	/**
	 * 
	 * @����: ���һ������
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-8 ����08:25:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param shlc
	 * @param sqbh
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancel(String shlc, String sqbh) throws Exception {
		
		XsgwshnewForm model = new XsgwshnewForm();
		model.setSqbh(sqbh);
		model.setShzt(Constants.SHZ);
		boolean result = dao.runUpdate(model, sqbh);
		if (result) {
			result = dao.delJgForSq(sqbh);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-13 ����09:25:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSh(XsgwshnewForm form, User user) {
		
		// ��˲���Model��ʼ��
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getSplc());
		// �����
		model.setShr(user.getUserName());
		// ������
		model.setShyj(form.getShyj());
		// ���״̬
		model.setShzt(form.getShzt());
		model.setThgw(form.getThgw());
		// ��˸�λID
		model.setGwid(form.getGwid());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getSqbh());
		model.setSqrid(form.getXh());
		model.setTzlj("qgzx_xsgwshnew_sh.do");
		model.setTzljsq("qgzx_xsgwsqnew_sq.do");
		model.setZd2(form.getOldgwdm());
		
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			XsgwshnewForm upForm = new XsgwshnewForm();
			upForm.setSqbh(form.getSqbh());
			upForm.setShzt(zhzt);
			
			// ���ͨ�������ֶβ���
			XsgwsqService ws = new XsgwsqService();
			HashMap<String, String> map = ws.getCsszb();
			String splc = form.getSplc();
			String yzgw = map.get("rskzjb");
			// ��ǰ�û�����֤��λ �򲻽�����֤��
			if (ws.isCheck(splc, yzgw, model.getGwid())) {
				// ������ǲ�ͨ���Ҳ����˻ز�����Ϊ�������� ��¼���id
				// ����Ϊ�쳣����
				if (Constants.SH_BTG.equals(model.getShzt())) {
					upForm.setShgw(_WTGSJ);
				} else if (Constants.SH_TH.equals(model.getShzt())) {
					// �Ƿ����˻ص���֤����֮ǰ�����������Ҫ���
					if (!ws.isCheck(splc, yzgw, model.getThgw())) {
						upForm.setShgw(_WTGSJ);
					} else {
						String spgw = new ShlcDao().getBeforeGwid(splc, model.getThgw());
						upForm.setShgw(spgw);
					}
				} else {
					// ����Ϊ�������ͨ������
					// upForm.setShgw(getModel(form.getSqbh()).getGwdm());
					upForm.setShgw(form.getGwid());
				}
			}
			// ��ͨ�������˻�ʱ�����������ĸ�λ
			if (!(Constants.SH_BTG.equals(model.getShzt()) || Constants.SH_TH
					.equals(model.getShzt()))) {
				// �������������λ�����ڸ�λ���������
				upForm.setGwdm(form.getGwdm());
			}
			// ������˻�����Ҫ��Ӧ�˻�ԭ�����λ
			// if (Constants.SH_TH.equals(model.getShzt())) {
			// thsqgw(user.getUserName(),upForm, form.getSplc(), form.getThgw(),
			// form.getSqbh(),
			// form.getGwdm());
			// }
			// ԭҵ��
			reuslt = dao.runUpdate(upForm, form.getSqbh());
			if (zhzt.equals(Constants.OPEN)) {
				HashMap<String, String> xssqxxMap = dao.getXsSqxx(model
						.getYwid());
				reuslt = dao.bcGwxs(xssqxxMap.get("gwdm"), xssqxxMap.get("xh"),
						xssqxxMap.get("sqbh"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	/**
	 * 
	 * @����: ��֤����
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-13 ����09:25:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param isTj
	 * @return
	 * @throws SQLException
	 * String �������� 
	 * @throws
	 */
	public String yzjb(XsgwshnewForm model, boolean isTj) throws SQLException {
		
		XsgwsqService wdService = new XsgwsqService();
		String shgw = ShlcUtil.getDqGw(model.getSqbh());
		HashMap<String, String> map = wdService.getCsszb();
		String yzgw = map.get("rskzjb");
		String message = wdService.yzjb(model.getXh(), model.getSplc(), model
				.getGwdm(), yzgw, shgw, isTj);
		
		return message;		
	}
	
	/**
	 * 
	 * @����:���ǰ��֤������Ч��
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-13 ����09:26:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * @throws Exception
	 * JSONObject �������� 
	 * @throws
	 */
	public JSONObject yzsh(XsgwshnewForm form) throws Exception {
		
		Map<String, String> resultmap = new HashMap<String, String>();
		XsgwsqService wdService = new XsgwsqService();
		XsgwsqForm model = new XsgwsqForm();
		
		model.setGwdm(form.getGwdm());
		model.setXh(form.getXh());
		model.setSplc(form.getSplc());
		
		int xszggwsl = wdService.getXszggwsl(model);
		HashMap<String, String> map = wdService.getCsszb();
		String xsgwsqsplc = map.get("xsgwsqsplc");
		String message = "";
		if (xszggwsl > 0) {
			message = "ѧ���Ѿ��ڸڣ��޷����ͨ��";
		}/*
		 * else if (xszgsl >= Integer.parseInt(xsgws) && Integer.valueOf(xsgws)
		 * != 0) { message = "��ѧ���Ѿ���" + xszgsl + "����λ������ѧ������λ��"; }
		 */
		else if (xsgwsqsplc == null || "".equals(xsgwsqsplc)) {
			message = "��û�ж����������̲��ܱ���";
		} else {
			// ��֤����ĸ�λ�Ƿ��Ѿ��ﵽ����
			if (model.getGwdm() != null && !model.getGwdm().equals("")) {
				message = wdService.yzgwxx(model.getGwdm(), form.getXh());
			} else {
				message = "true";
			}
		}
		resultmap.put("message", message);
		return JSONObject.fromObject(resultmap);
	}
	
	public String savePlsh(XsgwshnewForm t, User user) throws Exception {
			
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		List<String> failXhs = new ArrayList<String>();
		
		for (int i = 0, n = ids.length; i < n; i++) {
			XsgwshnewForm model = new XsgwshnewForm();
			model.setSqbh(ids[i]);
			model.setGwid(gwid[i]);
			model.setGwdm(t.getGwdm());
			model.setSplc(t.getSplc());
			model.setShyj(t.getShyj());
			model.setShzt(t.getShzt());
			model.setXh(xhs[i]);
			
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
	
	public String checkTgrs(List<HashMap<String, String>> list)
			throws Exception {
		
		String id = null;
		String gwid = null;
		String message = "true";
		String splc=null;
		
		XsgwsqDao wd = new XsgwsqDao();
		XsgwsqService ws = new XsgwsqService();
		HashMap<String, String> map = ws.getCsszb();
		HashMap<String, Integer> tgsl = new HashMap<String, Integer>();
		String gwmc = null;
		for (HashMap<String, String> hm : list) {
			id = hm.get("id");
			gwid = hm.get("gwid");
			gwmc = hm.get("message");
			splc=hm.get("splc");
			// ��ʱ��ҳ����ϣ�ҳ����ʾ��Ϣ��������Ҫ��Ӧ���ģ�����ٲ�ѯ���ݿ⣬Ч�ʺܵͣ�
			gwmc = gwmc.substring(gwmc.indexOf("[") + 1, gwmc.lastIndexOf("]"));
			XsgwshnewForm xf = getModel(id);
			xf.setGwmc(gwmc);
			// ��ǰ�û�����֤��λ �򲻽�����֤��
			String yzgw = map.get("rskzjb");
			HashMap<String, String> gwxx = wd.getGwxx(xf.getGwdm());
			String xqrs = gwxx.get("xqrs");// ����������
			if (!ws.isCheck(xf.getSplc(), yzgw, gwid)) {
				// ��¼��ǰ��λ��λ���ͨ������+1
				// ����¼����Ϊ���������֤����֮���Ȩ�� ���ͨ����Ӧ�ü���
				// Integer sl=tgsl.get(xf.getGwdm());
				// sl=null==sl?0:sl;
				// tgsl.put(xf.getGwdm(), sl+1);
				continue;
			} else {
				Integer tgrs = wd.getGwShtgRs(splc,gwid, xf.getGwdm());
				// ��ȡ��ǰ���ͨ������+�����ͨ������ Ϊʵ��ͨ������
				Integer jlrs = tgsl.get(xf.getGwdm());
				jlrs = null == jlrs ? 0 : jlrs;
				tgrs += jlrs;
				if (tgrs > Integer.parseInt(xqrs) && !xqrs.equals("0")) {
					message = "[" + xf.getGwmc() + "]�ѳ���λ��������";
					break;
				} else {
					// ��¼��ǰ��λ��λ���ͨ������+1
					Integer sl = tgsl.get(xf.getGwdm());
					sl = null == sl ? 0 : sl;
					tgsl.put(xf.getGwdm(), sl + 1);
				}
			}
		}
		return message;
	}
	
	
	
}
