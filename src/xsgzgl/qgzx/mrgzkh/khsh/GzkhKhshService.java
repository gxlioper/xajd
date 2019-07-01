/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-7 ����04:13:52 
 */
package xsgzgl.qgzx.mrgzkh.khsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.cjgl.QgzxCjglDAO;
import xsgzgl.qgzx.cssz.QgzxCsszDAO;
import xsgzgl.qgzx.mrgzkh.khjg.GzkhKhjgDao;
import xsgzgl.qgzx.mrgzkh.khjg.GzkhKhjgForm;
import xsgzgl.qgzx.mrgzkh.khjg.GzkhKhjgService;
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
 * @ģ������: ÿ�չ������˹���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-7 ����04:13:52
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class GzkhKhshService extends SuperServiceImpl<GzkhKhshForm, GzkhKhshDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	private GzkhKhshDao dao = new GzkhKhshDao();
	private GzkhKhjgDao khjgDao = new GzkhKhjgDao();

	/**
	 * 
	 * @����:���������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-8 ����08:42:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getKhshInfo(GzkhKhshForm t) {
		return dao.getKhshInfo(t);

	}

	/**
	 * 
	 * @����:��˱���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-8 ����08:51:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return boolean ��������
	 * @throws
	 */
	public boolean saveSh(GzkhKhshForm form, User user) {
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
		model.setYwid(form.getSqid());
		model.setSqrid(form.getXh());
		model.setTzlj("qgzx_mrgzkh_khsh.do");
		model.setTzljsq("qgzx_mrgzkh_khsq.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditing(model);
			GzkhKhshForm khshForm = new GzkhKhshForm();
			khshForm.setSqid(form.getSqid());
			khshForm.setShzt(zhzt);
//			khshForm.setYxgs(form.getYxgs());
			reuslt = dao.runUpdate(khshForm, form.getSqid());
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				GzkhKhjgForm khjgForm = new GzkhKhjgForm();
				GzkhKhjgService khjgService = new GzkhKhjgService();
				GzkhKhsqForm khsqForm = new GzkhKhsqDao().getModel(form.getSqid());
				BeanUtils.copyProperties(khjgForm, StringUtils.formatData(khsqForm));
//				khjgForm.setGs(khsqForm.getYxgs());
//				khjgService.Cjcl(khjgForm);
//				khjgForm.setId(khsqForm.getSqid());
				khjgForm.setSqid(khsqForm.getSqid());
				khjgForm.setSjly("1");
				reuslt = khjgService.runInsert(khjgForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}

	/**
	 * @throws Exception
	 * 
	 * @����:������˱���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-8 ����03:22:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return String ��������
	 * @throws
	 */
	public String savePlsh(GzkhKhshForm t, User user) throws Exception {
		GzkhKhsqDao khsqDao = new GzkhKhsqDao();
		GzkhKhshForm model = new GzkhKhshForm();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] yxgss = t.getYxgss();
		String[] gss = t.getGss();
		List<String> failXhs = new ArrayList<String>();
		// ���ж�ѡ���ѧ����ʱ����λ����Ƿ񳬹�����
		/*List<String> gsOutMessage = new ArrayList<String>();
		List<String> jeOutMessage = new ArrayList<String>();
		List<String> gwjeOutMessage = new ArrayList<String>();
		List<String> gwjeCheckMessage = new ArrayList<String>();
		HashMap<String, String> messageMap = new HashMap<String, String>();
		HashMap<String, String> gwjeMessageMap = new HashMap<String, String>();*/
		for (int i = 0, n = ids.length; i < n; i++) {
			GzkhKhsqForm sqForm = khsqDao.getModel(ids[i]);
			model.setGwdm(sqForm.getGwdm());
			model.setCjbz(sqForm.getCjbz());
			model.setGzrq(sqForm.getGzrq());
			model.setSqid(ids[i]);
			model.setXh(xhs[i]);
			model.setXn(t.getXn());
//			model.setYxgs(String.valueOf(Double.parseDouble(yxgss[i])));
			//��֤
//			plshCheck(model, gsOutMessage, jeOutMessage, gwjeOutMessage, gwjeCheckMessage, messageMap, gwjeMessageMap);
		}

		/*if (!messageMap.isEmpty()) {
			if ("gs".equals(messageMap.get("lx"))) {
				return MessageUtil.getText(MessageKey.QGZX_MRGZKH_GSOUT, new String[] { messageMap.get("message"), messageMap.get("gzrq"), messageMap.get("sxsz") });
			} else if ("je".equals(messageMap.get("lx"))) {
				return MessageUtil.getText(MessageKey.QGZX_MRGZKH_JEOUT, new String[] { messageMap.get("message"), messageMap.get("gzrq"), messageMap.get("sxsz") });
			}
		}
		if (!gwjeMessageMap.isEmpty()) {
			return MessageUtil.getText(MessageKey.QGZX_MRGZKH_GWJESX_PL_OUT, new String[] { gwjeMessageMap.get("message") });
		}*/
		for (int i = 0, n = ids.length; i < n; i++) {
			String gs = null;
			if (null == gss[i] || "".equals(gss[i])) {
				gs = "";
				model.setGs(gs);

			} else {
				gs = gss[i];
				model.setGs(String.valueOf(Double.parseDouble(gs)));
			}

			model.setSplc(t.getSplc());
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			model.setXn(t.getXn());
//			model.setYxgs(String.valueOf(Double.parseDouble(yxgss[i])));

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
	 * @����:���������֤��ʱ��λ�������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-22 ����11:51:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * 
	 * @return
	 * @throws Exception
	 *             String ��������
	 * @throws
	 */
	public void plshCheck(GzkhKhshForm model, List<String> gsOutMessage, List<String> jeOutMessage, List<String> gwjeOutMessage, List<String> gwjeCheckMessage, HashMap<String, String> messageMap,
			HashMap<String, String> gwjeMessageMap) throws Exception {
		GzkhKhsqDao khsqDao = new GzkhKhsqDao();
		QgzxCsszDAO csszDao = new QgzxCsszDAO();
		HashMap<String, String> csszMap = csszDao.getCssz();
		QgzxCjglDAO cjglDao = new QgzxCjglDAO();
		HashMap<String, String> gwxxMap = cjglDao.getGwxx(model.getGwdm());
		HashMap<String, String> xsCjxx = khsqDao.getCjffXxOfStu(model.getXh(), model.getXn(), model.getGzrq());

		String cjgs = xsCjxx.get("gs") == null ? "0" : xsCjxx.get("gs");
		String je = xsCjxx.get("je") == null ? "0" : xsCjxx.get("je");
		HashMap<String, String> sqjlMap = khsqDao.getsqjl(model.getXh(), model.getXn(), model.getGzrq(), model.getSqid());
		// ��λ���
		String gwZje = khsqDao.getKhsqOfYf(model.getXh(), model.getXn(), model.getGwdm(), model.getGzrq(), model.getSqid());
		// ���Ž��
		String ffje = khsqDao.getCjByGwdm(model.getXh(), model.getXn(), model.getGwdm(), model.getGzrq());
		if ("gs".equals(csszMap.get("sxzd")) && (Double.parseDouble(cjgs) + Double.parseDouble(sqjlMap.get("zgs")) + Double.parseDouble(model.getYxgs()) > Double.parseDouble(csszMap.get("sxsz")))) {
			if (!gsOutMessage.contains(model.getXh())) {
				gsOutMessage.add(model.getXh());
			}
		}
		if ("je".equals(csszMap.get("sxzd"))
				&& (Double.parseDouble(je) + Double.parseDouble(sqjlMap.get("zje")) + Double.parseDouble(model.getYxgs()) * Double.parseDouble(model.getCjbz()) > Double.parseDouble(csszMap
						.get("sxsz")))) {
			if (!jeOutMessage.contains(model.getXh())) {
				gsOutMessage.add(model.getXh());
			}
		}
		if (StringUtils.isNotNull(gwZje) && "yes".equals(csszMap.get("sfsdgwcjsx")) && StringUtils.isNotNull(gwxxMap.get("gwcjsx"))
				&& (Double.parseDouble(gwZje) + Double.parseDouble(ffje) + Double.parseDouble(model.getYxgs()) * Double.parseDouble(model.getCjbz()) > Double.parseDouble(gwxxMap.get("gwcjsx")))) {
			String gwjeOut = "[" + model.getXh() + model.getGwdm() + model.getGzrq().substring(0, 7) + "]";
			
			if (!gwjeOutMessage.contains(gwjeOut)) {
				gwjeOutMessage.add(gwjeOut);
				gwjeCheckMessage.add(model.getXh());
			}
		}
		StringBuffer message = new StringBuffer();
		if (!gsOutMessage.isEmpty()) {
			for (int j = 0; j < gsOutMessage.size(); j++) {
				if (j != 0) {
					message.append(",");
				}
				message.append(gsOutMessage.get(j));
			}
			messageMap.put("lx", "gs");
			messageMap.put("message", message.toString());
			messageMap.put("gzrq", model.getGzrq().substring(0, 7));
			messageMap.put("sxsz", csszMap.get("sxsz"));

		}
		if (!jeOutMessage.isEmpty()) {
			for (int j = 0; j < jeOutMessage.size(); j++) {
				if (j != 0) {
					message.append(",");
				}
				message.append(jeOutMessage.get(j));
			}
			messageMap.put("lx", "je");
			messageMap.put("message", message.toString());
			messageMap.put("gzrq", model.getGzrq().substring(0, 7));
			messageMap.put("sxsz", csszMap.get("sxsz"));

		}
		if (!gwjeCheckMessage.isEmpty()) {
			for (int j = 0; j < gwjeCheckMessage.size(); j++) {
				if (j != 0) {
					message.append(",");
				}
				message.append(gwjeCheckMessage.get(j));
			}
			gwjeMessageMap.put("lx", "gwje");
			gwjeMessageMap.put("message", message.toString());
			gwjeMessageMap.put("gzrq", model.getGzrq().substring(0, 7));
			gwjeMessageMap.put("sxsz", csszMap.get("sxsz"));

		}

	}

	/**
	 * 
	 * @����:���һ������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-8 ����03:22:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean cancel(GzkhKhshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
		if (result) {
			GzkhKhjgForm jgForm = khjgDao.getKhjgById(myForm.getSqid());
			// ɾ��������е����ݲ����¼���ѧ�����
			GzkhKhjgDao jgdao = new GzkhKhjgDao();
			jgdao.delKhjgById(myForm.getSqid());
			result = new GzkhKhjgService().xsCjxxJs(jgForm);
		}
		return result;
	}

	public String cxshnew(String ywid, GzkhKhshForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();

		// �ж��������
		HashMap<String, String> shxx = ShlcDao.getDqjbByCondition(ywid, user.getUserName(), model.getShlc(), "cx");

		// ���ǰһ������Ч��ʱ
		String tzhgs = shxx.get("zd2") == null ? "" : shxx.get("zd2");
		String shzt = Constants.YW_SHZ;

		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		// �ع�������еĹ�ʱ
		dao.updateSqjl(ywid, shzt, tzhgs);
		return cancelFlag;

	}

}
