/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-6 ����02:43:00 
 */
package xsgzgl.qgzx.mrgzkh.khsq;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.cjgl.QgzxCjglDAO;
import xsgzgl.qgzx.cssz.QgzxCsszDAO;
import xsgzgl.qgzx.mrgzkh.jcsz.GzkhJcszDao;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import xsgzgl.qgzx.mrgzkh.khsh.GzkhKhshForm;
import xsgzgl.qgzx.mrgzkh.khsh.GzkhKhshService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ÿ�չ������˹���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-6 ����02:43:00
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class GzkhKhsqService extends SuperServiceImpl<GzkhKhsqForm, GzkhKhsqDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	GzkhKhsqDao dao = new GzkhKhsqDao();

	/**
	 * 
	 * @����:����ѧ����λ��Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-12 ����10:28:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @param model
	 *            void ��������
	 * @throws
	 */
	public void setXsGwxx(HttpServletRequest request, GzkhKhsqForm model) {
		List<HashMap<String, String>> yrdwList = getYrbm(model);
		if (yrdwList.size() > 0 && "" != yrdwList.get(0).get("bmdm")) {
			if ("".equals(model.getYrdw()) || null == model.getYrdw()) {
				model.setYrdw(yrdwList.get(0).get("bmdm"));
			}
		}
		List<HashMap<String, String>> gwList = getGwxx(model);
		request.setAttribute("gwList", gwList);
		request.setAttribute("yrdwList", yrdwList);
	}

	public List<HashMap<String, String>> getXsGwxx(GzkhKhsqForm model) {
		return dao.getGwxx(model);
	}

	/**
	 * 
	 * @����:��ȡѧ���ڹ���λ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-7 ����09:02:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getGwxx(GzkhKhsqForm model) {
		return dao.getGwxx(model);
	}

	/**
	 * 
	 * @����:��ȡѧ���ڹ���λ��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-7 ����09:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getYrbm(GzkhKhsqForm model) {
		return dao.getYrbm(model);
	}

	/**
	 * 
	 * @����:��ȡ�ڸ�ѧ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-7 ����10:14:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getGzkhStuList(GzkhKhsqForm model, User user) throws Exception {
		return dao.getGzkhStuList(model, user);
	}

	/**
	 * 
	 * @����:�������뱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-7 ����02:26:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean saveKhsq(GzkhKhsqForm model, User user) throws Exception {
		QgzxCsszDAO csszDao = new QgzxCsszDAO();
//		HashMap<String, String> csszMap = csszDao.getCssz();
		GzkhJcszDao jcszDao = new GzkhJcszDao();
		String sqid = UniqID.getInstance().getUniqIDHash();
		String splc = dao.getShlcID();
		model.setGs(gsFormat(model.getGs()));
//		model.setCjbz(csszMap.get("cjbz"));
		model.setQxfw(jcszDao.getModel().getQxfw());
		model.setSqid(sqid);
		model.setSplc(splc);
		model.setCzyh(user.getUserName());
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		// ��������������趨��ʼֵ
		if ("submit".equals(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// �����
		} else {
			model.setShzt(Constants.YW_WTJ);// δ�ύ
		}
		// ����������Ϣ
		boolean result = dao.runInsert(model);
		// ���������Ϣ
		if (!"save".equals(model.getType())) {
			if (result) {
				result = shlc.runSubmit(sqid, splc, model.getXh(), "qgzx_mrgzkh_khsh.do", "qgzx_mrgzkh_khsq.do");
			}
		}

		if(!"stu".equals(user.getUserType())){//��ʦά��ѧ������ʱ����ֱ�ӽ�ȥ�ڹ��������
			GzkhKhshService gzkhKhshService = new GzkhKhshService();
			GzkhKhshForm form = new GzkhKhshForm();
			BeanUtils.copyProperties(form,model);
			form.setShjg("1");
			form.setShyj("ͬ��");
			String gwid = dao.getGwid(splc);//��ȡ��һ����������λ
			form.setGwid(gwid);
			form.setThgw("-1");
			result = gzkhKhshService.saveSh(form,user);
		}
		return result;
	}

	/**
	 * 
	 * @����:���������޸ı���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-7 ����02:26:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean saveEditKhsq(HttpServletRequest request, GzkhKhsqForm model) throws Exception {
		boolean result = false;
		// ����ύ���������״̬
		if ("submit".equalsIgnoreCase(model.getType()) || "tj".equalsIgnoreCase(model.getType())) {
			if ("tj".equalsIgnoreCase(model.getType())) {
				String values = request.getParameter("values");
				model.setSqid(values);
			} else {
				model.setGs(gsFormat(model.getGs()));
			}
			model.setShzt(Constants.YW_SHZ);// �����
			result = runUpdate(model);
			String splc = dao.getShlcID();
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getSqid(), splc, model.getXh(), "qgzx_mrgzkh_khsh.do", "qgzx_mrgzkh_khsq.do");
			}
		} else {
			model.setGs(gsFormat(model.getGs()));
			result = runUpdate(model);
		}
		return result;
	}

	/**
	 * 
	 * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-7 ����03:02:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}

	/**
	 * �ж��Ƿ��������¼
	 */
	public boolean isHaveSqJl(GzkhKhsqForm model, String czlx) throws Exception {
		String[] gwxx = model.getGwdm().split(",");
		model.setGwdm(gwxx[0]);
		model.setXn(gwxx[1]);
		return dao.isHaveSqJl(model, czlx);
	}

	/**
	 * 
	 * @����:��֤ѧ����д�·ݽ���ʱ�Ƿ񳬹��趨����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-15 ����05:34:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param czlx
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public String checkZjeAndGs(String xh, String xn, String gs, String gzrq, String cjbz, String sqid, String gwdm) throws Exception {

		QgzxCsszDAO csszDao = new QgzxCsszDAO();
		HashMap<String, String> gwxxMap = dao.getGwxx(gwdm);
		HashMap<String, String> csszMap = csszDao.getCssz();
		if (null == cjbz) {
			cjbz = csszMap.get("cjbz");
		}
		HashMap<String, String> xsCjxx = dao.getCjffXxOfStu(xh, xn, gzrq);
		String cjgs = xsCjxx.get("gs") == null ? "0" : xsCjxx.get("gs");
		String je = xsCjxx.get("je") == null ? "0" : xsCjxx.get("je");
		HashMap<String, String> sqjlMap = dao.getsqjl(xh, xn, gzrq, sqid);
		String gwZje = dao.getKhsqOfYf(xh, xn, gwdm, gzrq, sqid);
		String ffje = dao.getCjByGwdm(xh, xn, gwdm, gzrq);
		if ("gs".equals(csszMap.get("sxzd")) && (Double.parseDouble(cjgs) + Double.parseDouble(sqjlMap.get("zgs")) + Double.parseDouble(gs) > Double.parseDouble(csszMap.get("sxsz")))) {
			return MessageUtil.getText(MessageKey.QGZX_MRGZKH_GSOUT, new String[] { xh,gzrq.substring(0, 7), csszMap.get("sxsz") });
		}
		if (StringUtils.isNotNull(gwZje) && "yes".equals(csszMap.get("sfsdgwcjsx"))&&StringUtils.isNotNull(gwxxMap.get("gwcjsx"))
				&& (Double.parseDouble(gwZje) + Double.parseDouble(ffje) + Double.parseDouble(gs) * Double.parseDouble(cjbz) > Double.parseDouble(gwxxMap.get("gwcjsx")))) {
			return MessageUtil.getText(MessageKey.QGZX_MRGZKH_GWJESXOUT, new String[] { gzrq.substring(0, 7), gwxxMap.get("gwmc"), gwxxMap.get("gwcjsx") });
		}
		if ("je".equals(csszMap.get("sxzd"))
				&& (Double.parseDouble(je) + Double.parseDouble(sqjlMap.get("zje")) + Double.parseDouble(gs) * Double.parseDouble(cjbz) > Double.parseDouble(csszMap.get("sxsz")))) {
			return MessageUtil.getText(MessageKey.QGZX_MRGZKH_JEOUT, new String[] { xh,gzrq.substring(0, 7), csszMap.get("sxsz") });
		}
		return "true";
	}
	public String checkGs(String xh, String xn, String gs, String gzrq, String cjbz, String sqid, String gwdm) throws Exception {

		HashMap<String, String> sqjlMap = dao.getsqjl(xh, xn, gzrq, sqid);

		if (Double.parseDouble(sqjlMap.get("zgs")) + Double.parseDouble(gs) > 40) {
			return MessageUtil.getText(MessageKey.QGZX_MRGZKH_GSOUT, new String[] { xh,gzrq.substring(0, 7), "40" });
		}

		return "true";
	}

	public String gsFormat(String gs) {
		int lastStr = gs.indexOf(".");
		if (gs.length() - 1 == lastStr) {
			String[] gsArr = gs.split("\\.");
			gs = gsArr[0];
		}
		return gs;
	}

}
