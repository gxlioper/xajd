package xsgzgl.pjpy.general;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.xml.XMLReader;
import xgxt.utils.String.StringUtils;
import xsgzgl.comm.form.CommForm;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_ͨ��_Form��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class PjpyGeneralForm extends CommForm {

	private static final long serialVersionUID = 1L;

	public static PjpyGeneralForm jbszModel;

	public static PjszPjxmModel pjxmModel;

	// �۲�����
	private String zczq = XMLReader.getFlowControl("pjpy", "zczq");

	// ��Ա��
	private String ryk = XMLReader.getFlowControl("pjpy", "ryk");
	// �۲���Ŀչʾ
	private String zcxmzs = XMLReader.getFlowControl("pjpy", "zcxmzs");

	private String operation;// ����

	List<HashMap<String, String>> tsrqList = null;// ������Ⱥ

	private String pjxn;// ����ѧ��

	private String pjxq;// ����ѧ��

	private String pjxqmc;// ����ѧ������

	private String pjnd;// �������

	private String czxm;// ������Ŀ

	private String xmdm;// �۲���Ŀ����

	private String xmmc;// ��Ŀ����

	private String bl;// �۲����

	private String bldm;// ��������

	private String[] pmjs;// �������㷽ʽ

	private String[] jslx;// ��������

	private String pjry_sql = " (select a.pjxn,"
			+ " a.pjxq,"
			+ " a.pjnd,"
			+ " a.xh,"
			+ " a.xm,"
			+ " a.xydm,"
			+ " a.zydm,"
			+ " a.bjdm,"
			+ " a.xb,"
			+ " b.xymc,"
			+ " c.zymc,"
			+ " d.bjmc,"
			+ " d.nj,"
			+ " a.sfysz"
			+ " from xg_pjpy_xsb a, xg_pjpy_xyb b, xg_pjpy_zyb c, xg_pjpy_bjb d"
			+ " where a.sfysz = '��'" + " and a.xydm = b.xydm"
			+ " and a.zydm = c.zydm" + " and a.bjdm = d.bjdm"
			+ " and a.pjxn = b.pjxn and a.pjxq = b.pjxq and a.pjnd = b.pjnd"
			+ " and a.pjxn = c.pjxn and a.pjxq = c.pjxq and a.pjnd = c.pjnd"
			+ " and a.pjxn = d.pjxn and a.pjxq = d.pjxq and a.pjnd = d.pjnd)";

	static {
		PjpyGeneralForm.setJbszModel();
	}

	// =================The Last==========================
	private String pjzq = "xn";// ��������

	private String zcpm = "3";// �۲�����

	private String zypm = "3";// ��������

	private String cpz = "no";// ������

	private String[] operate = { "001", "002", "003" };// ����

	private String xydm; // ѧԺ����

	private String zydm; // רҵ����

	private String bjdm; // �༶����

	private String nj; // �꼶����

	private String xh; // ѧ��

	private String xm; // ����

	public List<HashMap<String, String>> getTsrqList() {
		return tsrqList;
	}

	public void setTsrqList(List<HashMap<String, String>> tsrqList) {
		this.tsrqList = tsrqList;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String[] getOperate() {
		return operate;
	}

	public void setOperate(String[] operate) {
		this.operate = operate;
	}

	public String getCpz() {
		return cpz;
	}

	public void setCpz(String cpz) {
		this.cpz = cpz;
	}

	public String getPjnd() {
		return pjnd;
	}

	public void setPjnd(String pjnd) {
		this.pjnd = pjnd;
	}

	public String getPjxn() {
		return pjxn;
	}

	public void setPjxn(String pjxn) {
		this.pjxn = pjxn;
	}

	public String getPjxq() {
		return pjxq;
	}

	public void setPjxq(String pjxq) {
		this.pjxq = pjxq;
	}

	public String getPjxqmc() {
		return pjxqmc;
	}

	public void setPjxqmc(String pjxqmc) {
		this.pjxqmc = pjxqmc;
	}

	public static PjpyGeneralForm getJbszModel() {
		return jbszModel;
	}

	public static void setJbszModel() {

		jbszModel = new PjpyGeneralForm();

		DAO dao = DAO.getInstance();

		String xtsz_sql = "select a.*,(select b.xqmc from xqdzb b "
				+ "where b.xqdm=a.pjxq) pjxqmc from xg_pjpy_xtszb a where rownum=1";

		HashMap<String, String> map = dao.getMapNotOut(xtsz_sql,
				new String[] {});

		if (null != map && StringUtils.isNotNull(map.get("pjxn"))) {
			jbszModel.setPjxn(map.get("pjxn"));
			jbszModel.setPjnd(map.get("pjnd"));
			jbszModel.setPjxq(map.get("pjxq"));
			jbszModel.setPjxqmc(map.get("pjxqmc"));

			if (!Base.isNull(map.get("pjzq"))) {
				jbszModel.setPjzq(map.get("pjzq"));
			}

			if (!Base.isNull(map.get("zcpm"))) {
				jbszModel.setZcpm(map.get("zcpm"));
			}

			if (!Base.isNull(map.get("zypm"))) {
				jbszModel.setZypm(map.get("zypm"));
			}

			if (!Base.isNull(map.get("cpz"))) {
				jbszModel.setCpz(map.get("cpz"));
			}

			String tsrq_sql = "select a.tsrqdm,a.tsrqmc,a.condition from xg_pjpy_pjtsrqb a order by to_number(a.xssx)";

			List<HashMap<String, String>> list = dao.getList(tsrq_sql,
					new String[] {}, new String[] { "tsrqdm", "tsrqmc",
							"condition" });

			if (list != null && list.size() > 0) {

				int space = 3 - list.size() % 3;

				for (int i = 0; i < space; i++) {
					HashMap<String, String> spaceMap = new HashMap<String, String>();
					spaceMap.put("tsrqdm", "");
					spaceMap.put("tsrqmc", "");
					list.add(spaceMap);
				}
			}
			
			jbszModel.setTsrqList(list);
		}

		PjpyGeneralForm.jbszModel = jbszModel;

	}

	public String getCzxm() {
		return czxm;
	}

	public void setCzxm(String czxm) {
		this.czxm = czxm;
	}

	public String getZczq() {
		return zczq;
	}

	public void setZczq(String zczq) {
		this.zczq = zczq;
	}

	public String getRyk() {
		return ryk;
	}

	public void setRyk(String ryk) {
		this.ryk = ryk;
	}

	public String getPjry_sql() {
		return pjry_sql;
	}

	public void setPjry_sql(String pjry_sql) {
		this.pjry_sql = pjry_sql;
	}

	public String getBl() {
		return bl;
	}

	public void setBl(String bl) {
		this.bl = bl;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getBldm() {
		return bldm;
	}

	public void setBldm(String bldm) {
		this.bldm = bldm;
	}

	public String[] getPmjs() {
		return pmjs;
	}

	public void setPmjs(String[] pmjs) {
		this.pmjs = pmjs;
	}

	public String[] getJslx() {
		return jslx;
	}

	public void setJslx(String[] jslx) {
		this.jslx = jslx;
	}

	public String getPjzq() {
		return pjzq;
	}

	public void setPjzq(String pjzq) {
		this.pjzq = pjzq;
	}

	public String getZcpm() {
		return zcpm;
	}

	public void setZcpm(String zcpm) {
		this.zcpm = zcpm;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getZypm() {
		return zypm;
	}

	public void setZypm(String zypm) {
		this.zypm = zypm;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public static PjszPjxmModel getPjxmModel() {
		return pjxmModel;
	}

	public static void setPjxmModel(PjszPjxmModel pjxmModel) {
		PjpyGeneralForm.pjxmModel = pjxmModel;
	}

	public String getZcxmzs() {
		return zcxmzs;
	}

	public void setZcxmzs(String zcxmzs) {
		this.zcxmzs = zcxmzs;
	}
}
