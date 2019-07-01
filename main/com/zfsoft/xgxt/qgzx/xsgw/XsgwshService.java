/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-18 ����11:46:42 
 */
package com.zfsoft.xgxt.qgzx.xsgw;

import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import xgxt.form.User;

import com.zfsoft.xgxt.base.extend.AllExecute;
import com.zfsoft.xgxt.base.extend.IExecute;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import xsgzgl.qgzx.cssz.QgzxCsszService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ڹ���ѧ����ģ��
 * @�๦������:
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-6-18 ����11:46:42
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XsgwshService extends SuperServiceImpl<XsgwshForm, XsgwshDAO> {

	private XsgwshDAO dao = new XsgwshDAO();

	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * ��־û��ͨ�� ����֤Ȩ�����
	 */
	private final String _WTGSJ = "-1";

	public XsgwshService() {
		super.setDao(dao);
	}
	/**
	 * 
	 * @����:�Ƿ�����������������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-27 ����10:45:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splc
	 * @return
	 * boolean �������� 
	 */
	public Boolean isHaveSplcSj(String splc,String type) {
		List<HashMap<String, String>> list = dao.getSplcing(splc,type);
		return null == list || list.size() <= 0 ? false : true;
	}

	/**
	 * @����:TODO������˽��
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-26 ����02:25:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return boolean ��������
	 */
	@TransactionControl
	public boolean saveSh(XsgwshForm form, User user) throws Exception{

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
		model.setTzlj("qgzx_xsgwsh.do?method=xsgwshCx");
		model.setTzljsq("qgzx_wdgwsq.do?method=wdgwsqCx");
//		model.setZd2(form.getOldgwdm());

		boolean reuslt = false;
		
			String zhzt = shlc.runAuditingNotCommit(model);
			XsgwshForm upForm = new XsgwshForm();
			upForm.setSqbh(form.getSqbh());
			upForm.setShzt(zhzt);
			// ԭҵ��
			reuslt = dao.runUpdateNotCommit(upForm, form.getSqbh());
			if (zhzt.equals(Constants.OPEN)) {
				HashMap<String, String> xssqxxMap = dao.getXsSqxx(model
						.getYwid());
				reuslt = dao.bcGwxs(form.getGwdm(), xssqxxMap.get("xh"),
						xssqxxMap.get("sqbh"));
			}
		
		return reuslt;
	}
	public boolean zjZgxs(XsgwshForm form) throws SQLException {
		return dao.zjXsgw(form.getGwdm(),form.getXh());
	}
	/**
	 * 
	 * @����: �������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-19 ����05:29:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 *            ���id����
	 * @param shgws
	 *            ��˸�λ����
	 * @param showMessage
	 *            ��ʾ��Ϣ
	 * @param shyj
	 *            ������
	 * @param shzt
	 *            ���״̬
	 * @param user
	 *            �û�
	 * @return List<HashMap<String,String>> ��������
	 */
	@TransactionControl
	public List<HashMap<String, String>> plsh(String[] ids, String[] shgws,String[] bmlbs,
			String[] showMessage, final String shyj, final String shzt,
			final User user) throws Exception {
		List<Object> data = new ArrayList<Object>();
		List<HashMap<String, String>> message = new ArrayList<HashMap<String, String>>();
		int i = 0;
		Integer btgcgts = 0;// ��ͨ�������������
		// �ϲ�ҵ������
		for (String id : ids) {
			XsgwshForm xf = getModel(id);
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("id", id);
			hm.put("gwid", shgws[i]);
			hm.put("bmlb", bmlbs[i]);
			hm.put("message", showMessage[i]);
			hm.put("splc", xf.getSplc());
			// ����ǲ�ͨ����ֱ�ӽ�����˲���������¼��˲���������Ϣ
			if (Constants.SH_BTG.equals(shzt)) {
				xf.setGwid(hm.get("gwid"));
				xf.setBmlb(hm.get("bmlb"));
				xf.setShzt(shzt);
				xf.setShyj(shyj);
				xf.setOldgwdm(xf.getGwdm());
				if (!saveSh(xf, user)) {
					HashMap<String, String> mes = new HashMap<String, String>();
					mes.put("message", showMessage[i] + "[��˲���ʧ��!]");
					// ��Ӧǰ̨��ʾ��Ϣ��ʾ��������Ҫ����
					mes.put(AllExecute._EXECUTE_CHECK_TYPE, "btg");
					message.add(mes);
				} else {
					btgcgts++;
				}
			}
			data.add(hm);
			i++;
		}

		// ��ʼ��������������
		AllExecute ae = new AllExecute();
		message = ae.execute(new IExecute() {
			public String allCheck(Object data) {
				String message = "true";
				List<HashMap<String, String>> list = (List<HashMap<String, String>>) data;
				try {
					// ��ʱ�����м��𶼽�����֤
					message = checkTgrs(list);
				} catch (Exception e) {
					message += "����λ��������������";
					throw new RuntimeException("����λ��������������" + e.getMessage());
				}
				return message;
			}

			public String execute(Object data) {
				XsgwshForm xf = null;
				String message = null;
				try {
					HashMap<String, String> hm = (HashMap<String, String>) data;
					xf = getModel(hm.get("id"));
					xf.setGwid(hm.get("gwid"));
					xf.setBmlb(hm.get("bmlb"));
					xf.setShzt(shzt);
					xf.setShyj(shyj);
					xf.setOldgwdm(xf.getGwdm());
					// ҵ����֤
					message = yzjb(xf, false);
					if (AllExecute._EXECUTE_TRUE.equals(message)) {
						message = yzsh(xf).getString("message");
						if (AllExecute._EXECUTE_TRUE.equals(message)) {
							// ͨ���������ͨ��
							if (!saveSh(xf, user)) {
								message = "����ʧ�ܣ�";
							}
						}
					}
				} catch (Exception e) {
					throw new RuntimeException("��֤�Ƿ�������ͨ������" + e.getMessage());
				}
				return message;
			}

			public String message(Object data, String ywMessage) {
				HashMap<String, String> hm = (HashMap<String, String>) data;
				return hm.get("message");
			}
		}, data, false);
		// ���������������
		if (ae.isHaveError()) {
			// �������ݻع�
		}
		// ��¼�ɹ�����
		HashMap<String, String> cgts = new HashMap<String, String>();
		cgts.put("cgts", ae.getSuccessNumber().toString());
		message.add(cgts);
		return message;
	}

	public boolean saveLzSh(XsgwshForm form, User user) throws Exception{

		// ��˲���Model��ʼ��
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getSplc());
		// �����
		model.setShr(user.getUserName());
		// ������
		model.setShyj(form.getShyj());
		// ���״̬
		model.setShzt(form.getShjg());
//		model.setThgw(form.getThgw());
		// ��˸�λID
		model.setGwid(form.getGwid());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getSqbh());
		model.setSqrid(form.getXh());
		model.setTzlj("qgzx_xsgwsh.do?method=xslzshCx");
		model.setTzljsq("qgzx_xsgwsh.do?method=zgxsList");

		boolean reuslt = false;

		String zhzt = shlc.runAuditing(model);
		XsgwshForm upForm = new XsgwshForm();
		upForm.setSqbh(form.getSqbh());
		upForm.setShzt(zhzt);

		reuslt = dao.updateLzSh(upForm);
		if (zhzt.equals(Constants.YW_TG)) {
			reuslt = dao.updateGwxs(form.getGwdm(),form.getXh(),form.getSqly());
		}

		return reuslt;
	}
	public int lzplsh(String[] ids, String[] shgws,final String shyj, final String shzt,
											  final User user) throws Exception {

		Integer btgcgts = 0;// ��ͨ�������������

		for (int i=0;i<ids.length;i++) {
			XsgwshForm xf = dao.getLzModel(ids[i]);
			xf.setGwid(shgws[i]);
			xf.setShjg(shzt);
			xf.setShyj(shyj);

			boolean isSuccess = this.saveLzSh(xf,user);
			if(!isSuccess){
				btgcgts++;
			}
		}

		return btgcgts;
	}
	/**
	 * 
	 * @����:������ͨ���Ƿ񳬹���λ��������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-19 ����05:26:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return
	 * @throws Exception
	 *             String ��������
	 */
	public String checkTgrs(List<HashMap<String, String>> list)
			throws Exception {
		String id = null;
		String gwid = null;
		String bmlb = null;
		String message = "true";
		String splc=null;
		WdgwsqDAO wd = new WdgwsqDAO();
		WdgwsqService ws = new WdgwsqService();
		HashMap<String, String> map = ws.getCsszb();
		HashMap<String, Integer> tgsl = new HashMap<String, Integer>();
		String gwmc = null;
		for (HashMap<String, String> hm : list) {
			id = hm.get("id");
			gwid = hm.get("gwid");
			bmlb = hm.get("bmlb");
			gwmc = hm.get("message");
			splc=hm.get("splc");
			// ��ʱ��ҳ����ϣ�ҳ����ʾ��Ϣ��������Ҫ��Ӧ���ģ�����ٲ�ѯ���ݿ⣬Ч�ʺܵͣ�
			gwmc = gwmc.substring(gwmc.indexOf("[") + 1, gwmc.lastIndexOf("]"));
			XsgwshForm xf = getModel(id);
			xf.setGwmc(gwmc);

			// ��ǰ�û�����֤��λ �򲻽�����֤��
			String yzgw = null;
			if("1".equals(bmlb)){
			 	yzgw = map.get("rskzjb");
			} else if("5".equals(bmlb)){
				yzgw = map.get("yjrskzjb");
			}
			
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
				if (tgrs >= Integer.parseInt(xqrs) && !xqrs.equals("0")) {
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

	/**
	 * 
	 * @����:
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-18 ����11:12:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param shr
	 * @param shid
	 * @throws Exception void ��������
	 */
	public boolean cxRollBack(String shr, String shid) throws Exception {
		boolean isOk = true;
		HashMap<String, String> shxx = ShlcUtil.getShxx(shid);
		String ywid = shxx.get("ywid");
		String gwid = shxx.get("gwid");
		XsgwshForm xf = getModel(ywid);
		if (cxsqgw(shr, xf, xf.getSqbh(), xf.getGwdm(), gwid)) {
			isOk = dao.runUpdate(xf);
		}
		return isOk;
	}

	public boolean cxsqgw(String userid, XsgwshForm xf, String sqbh,
			String ywGwdm, String cxGwdm) {
		boolean isChange = false;
		HashMap<String, String> oldSpxx = dao.getSjTzShxx(sqbh, ywGwdm);
		WdgwsqDAO wd = new WdgwsqDAO(); 
		WdgwsqService ws = new WdgwsqService();
		HashMap<String, String> map = ws.getCsszb();
		String yzgw = map.get("rskzjb");
		if (null == cxGwdm) {
			return true;
		}
		//String yzxh = wd.getGwSpXh(xf.getSplc(), yzgw);
		//���������˸�λ
		cxGwdm=ShlcUtil.getUpSpgw(xf.getSplc(), cxGwdm); 
		//String cxxh = wd.getGwSpXh(xf.getSplc(), cxGwdm);
		//if (Integer.parseInt(cxxh)>=Integer.parseInt(yzxh)) {
			xf.setShgw(cxGwdm);
			isChange = true;
		//}else{
		//	isChange = true;
		//	xf.setShgw("-1");
		//}
		/*
		 * if (null != oldSpxx && oldSpxx.size() > 0) { String spgw =
		 * oldSpxx.get("gwid"); String splc=oldSpxx.get("lcid"); ShlcDao sd=new
		 * ShlcDao(); String lastGwid=sd.getLastGwid(splc); //��������һ������
		 * if(lastGwid.equals(cxGwdm)){
		 * //���ø�λidΪ�ϼ�����Ϊ���һ�����û�д�������ݣ����Լ�¼�ĵ������ϼ������У�
		 * cxGwdm=ShlcUtil.getUpSpgw(splc, cxGwdm); } //
		 * Ҫ�˻صĸ�λС�ڸ�������ĸ�λ����Ҫ����Ϣ���лع� if (cxGwdm.equals(spgw)) {
		 * xf.setGwdm(oldSpxx.get("zd2"));// �˻�Ϊԭ������ĸ�λ isChange = true; } }
		 */if (null != oldSpxx && oldSpxx.size() > 0) {
			xf.setGwdm(oldSpxx.get("zd2"));
			isChange = true;
		}
		return isChange;
	}

	/**
	 * 
	 * @����: �˻ظ�λ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-17 ����06:47:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xf
	 *            Ҫ�˻ظ��µ�form
	 * @param splc
	 *            ��Ӧ��������
	 * @param thgwdm
	 *            �˻ظ�λ����
	 * @param sqbh
	 *            ������
	 * @param nowGwdm
	 *            ��ǰ����ĸ�λid void ��������
	 */
	public boolean thsqgw(String userId, XsgwshForm xf, String splc,
			String thgwdm, String sqbh, String nowGwdm) {
		boolean isChange = false;
		Map<String, String> oldSpxx = dao.getOldShxx(userId, xf.getSplc(),
				sqbh, nowGwdm);
		if (null != oldSpxx && oldSpxx.size() > 0) {
			String spgw = oldSpxx.get("gwid");
			WdgwsqDAO dao = new WdgwsqDAO();
			String oldxh = dao.getGwSpXh(splc, spgw);
			String thxh = dao.getGwSpXh(splc, thgwdm);
			// Ҫ�˻صĸ�λС�ڸ�������ĸ�λ����Ҫ����Ϣ���лع�
			if (Integer.parseInt(thxh) < Integer.parseInt(oldxh)) {
				xf.setGwdm(oldSpxx.get("zd2"));// �˻�Ϊԭ������ĸ�λ
				isChange = true;
			}
		}
		return isChange;
	}

	/**
	 * @����:���ǰ��֤������Ч��
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-27 ����11:23:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * @throws SQLException
	 *             String ��������
	 */
	public JSONObject yzsh(XsgwshForm form) throws Exception {
		Map<String, String> resultmap = new HashMap<String, String>();
		WdgwsqService wdService = new WdgwsqService();
		WdgwsqForm model = new WdgwsqForm();
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
	/**
	 * 
	 * @����:������������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-13 ����03:09:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return boolean ��������
	 * @throws Exception
	 */
	public boolean resetRsKz() throws Exception {
		WdgwsqDAO sqdao = new WdgwsqDAO();
		return sqdao.resetRsKz(_WTGSJ);
	}

	/**
	 * 
	 * @����:��֤����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-26 ����11:08:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 *            ����model
	 * @param isTj
	 *            �Ƿ��ύʹ��
	 * @return
	 * @throws SQLException
	 *             String ��������
	 */
	public String yzjb(XsgwshForm model, boolean isTj) throws SQLException {
		WdgwsqService wdService = new WdgwsqService();
		String shgw = ShlcUtil.getDqGw(model.getSqbh());
		HashMap<String, String> map = wdService.getCsszb();
		String yzgw = "";
		if("1".equals(model.getBmlb()))
			yzgw = map.get("rskzjb");
		if("5".equals(model.getBmlb()))
			yzgw = map.get("yjrskzjb");


		String message = wdService.yzjb(model.getXh(), model.getSplc(), model
				.getGwdm(), yzgw, shgw, isTj);
		return message;
	}

	/**
	 * @����:���һ���������
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-10
	 * @param shlc
	 *            �������ID
	 * @param ssydsqid
	 *            ����ID
	 * @return boolean ��������
	 * @throws
	 */
	public boolean cancel(String shlc, String sqbh) throws Exception {
		XsgwshForm model = new XsgwshForm();
		model.setSqbh(sqbh);
		model.setShzt(Constants.SHZ);
		boolean result = dao.runUpdate(model, sqbh);
		if (result) {
			result = dao.delJgForSq(sqbh);
		}
		return result;
	}
	public boolean lzcancel(String shlc, String sqbh) throws Exception {
		XsgwshForm model = new XsgwshForm();
		model.setSqbh(sqbh);
		model.setShzt(Constants.SHZ);
		boolean result = dao.updateLzSh(model);
		if (result) {
			XsgwshForm xf = dao.getLzModel(sqbh);
			result = dao.updateCancelTgGwxs(xf.getGwdm(),xf.getXh());
		}
		return result;
	}

	public List<HashMap<String,String>> getZgxsList(XsgwshForm t, User user) throws Exception {
		return dao.getZgxsList(t,user);
	}

	public HashMap<String,String> getZgxx(String gwdm,String xh){
		return dao.getZgxx(gwdm,xh);
	}
	public boolean saveLzSq(XsgwshForm model) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.setSqsj(sdf.format(new Date()));
		model.setShzt(Constants.YW_SHZ);
		HashMap<String,String> splcMap = new QgzxCsszService().getSplc();
		model.setSplc(splcMap.get("xslzsplc"));
		String guid = UniqID.getInstance().getUniqIDHash();
		model.setSqbh(guid);
		boolean flag = dao.insertLzxx(model);
		if(flag){
			flag = shlc.runSubmit(guid, model.getSplc(), model.getXh(),
					"qgzx_xsgwsh.do?method=xslzshCx",
					"qgzx_wdgwsq.do?method=wdqggwCx");
		}
		return flag;
	}
	public int savePlLzSq(XsgwshForm model) throws Exception {

		String data = URLDecoder.decode(URLDecoder.decode(model.getJgData(),"UTF-8"),"UTF-8");
		List<Map> dataList = com.alibaba.fastjson.JSON.parseArray(data,Map.class);
		XsgwshForm t;
		int result = 0;
		for(int i=0;i<dataList.size();i++){
			Map a = dataList.get(i);
			t = new XsgwshForm();
			t.setGwdm((String)a.get("gwdm"));
			t.setXh((String)a.get("xh"));
			t.setSqly(model.getSqly());
			if(!this.saveLzSq(t)){
				result++;
			}
		}
		return result;
	}

	public List<HashMap<String,String>> getLzxsList(XsgwshForm model,User user)throws Exception {
		return dao.getLzxsList(model,user);
	}

	public HashMap<String,String> getLzModel(XsgwshForm model){
		return dao.getLzModel(model);
	}

	public List<HashMap<String,String>> getGzscList(XsgwshForm model) throws Exception {
		return dao.getGzscList(model);
	}
}
