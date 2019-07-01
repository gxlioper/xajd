package xsgzgl.xszz.jhzy.cssz;

import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xsgzgl.comm.form.CommForm;

public class XszzCsszActionForm extends CommForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String jtqkdzkssj;
	private String jtqkdzjssj;
	private String knssqkssj;
	private String knssqjssj;
	private String knsshkssj;
	private String knsshjssj;
	private String lzjxjsqkssj;
	private String lzjxjsqjssj;
	private String lzjxjshkssj;
	private String lzjxjshjssj;
	private String gjzxjsqkssj;
	private String gjzxjsqjssj;
	private String gjzxjshkssj;
	private String gjzxjshjssj;
	private String jtqktzzt;
	private String knssqzt;
	private String knsshzt;
	private String gjlzjxjsqzt;
	private String gjlzjxjshzt;
	private String gjzxjsqzt;
	private String gjzxjshzt;
	private String xn;
	private boolean isKns;
	private boolean isJtqkdc;

	private static XszzCsszActionForm csszForm;
	static {
		csszForm = new XszzCsszActionForm();
		DAO dao = DAO.getInstance();
		HashMap<String, String> rs = dao.getMapNotOut(
				"select * from xg_xszz_jhzy_csszb where rownum <2",
				new String[] {});
		csszForm.setXn(rs != null ? rs.get("xn") : "");
	}

	public static XszzCsszActionForm getCsszForm() {
		return csszForm;
	}

	public static void setCsszForm(XszzCsszActionForm csszForm) {
		XszzCsszActionForm.csszForm = csszForm;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getJtqkdzkssj() {
		return jtqkdzkssj;
	}

	public void setJtqkdzkssj(String jtqkdzkssj) {
		this.jtqkdzkssj = jtqkdzkssj;
	}

	public String getJtqkdzjssj() {
		return jtqkdzjssj;
	}

	public void setJtqkdzjssj(String jtqkdzjssj) {
		this.jtqkdzjssj = jtqkdzjssj;
	}

	public String getKnssqkssj() {
		return knssqkssj;
	}

	public void setKnssqkssj(String knssqkssj) {
		this.knssqkssj = knssqkssj;
	}

	public String getKnssqjssj() {
		return knssqjssj;
	}

	public void setKnssqjssj(String knssqjssj) {
		this.knssqjssj = knssqjssj;
	}

	public String getKnsshkssj() {
		return knsshkssj;
	}

	public void setKnsshkssj(String knsshkssj) {
		this.knsshkssj = knsshkssj;
	}

	public String getKnsshjssj() {
		return knsshjssj;
	}

	public void setKnsshjssj(String knsshjssj) {
		this.knsshjssj = knsshjssj;
	}

	public String getLzjxjsqkssj() {
		return lzjxjsqkssj;
	}

	public void setLzjxjsqkssj(String lzjxjsqkssj) {
		this.lzjxjsqkssj = lzjxjsqkssj;
	}

	public String getLzjxjsqjssj() {
		return lzjxjsqjssj;
	}

	public void setLzjxjsqjssj(String lzjxjsqjssj) {
		this.lzjxjsqjssj = lzjxjsqjssj;
	}

	public String getLzjxjshkssj() {
		return lzjxjshkssj;
	}

	public void setLzjxjshkssj(String lzjxjshkssj) {
		this.lzjxjshkssj = lzjxjshkssj;
	}

	public String getLzjxjshjssj() {
		return lzjxjshjssj;
	}

	public void setLzjxjshjssj(String lzjxjshjssj) {
		this.lzjxjshjssj = lzjxjshjssj;
	}

	public String getGjzxjsqkssj() {
		return gjzxjsqkssj;
	}

	public void setGjzxjsqkssj(String gjzxjsqkssj) {
		this.gjzxjsqkssj = gjzxjsqkssj;
	}

	public String getGjzxjsqjssj() {
		return gjzxjsqjssj;
	}

	public void setGjzxjsqjssj(String gjzxjsqjssj) {
		this.gjzxjsqjssj = gjzxjsqjssj;
	}

	public String getGjzxjshkssj() {
		return gjzxjshkssj;
	}

	public void setGjzxjshkssj(String gjzxjshkssj) {
		this.gjzxjshkssj = gjzxjshkssj;
	}

	public String getGjzxjshjssj() {
		return gjzxjshjssj;
	}

	public void setGjzxjshjssj(String gjzxjshjssj) {
		this.gjzxjshjssj = gjzxjshjssj;
	}

	public String getJtqktzzt() {
		XszzCsszService service = new XszzCsszService();
		try {
			boolean result = service.getJtqkdzsjzt();
			jtqktzzt = result ? "" : "尚未开放学生家庭情况调查，不能进行任何操作！";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return jtqktzzt;
	}

	public void setJtqktzzt(String jtqktzzt) {
		this.jtqktzzt = jtqktzzt;
	}

	public String getKnssqzt() {
		XszzCsszService service = new XszzCsszService();
		try {
			boolean result = service.getKnssqzt();
			knssqzt = result ? "" : "尚未开放困难生申请，不能进行任何操作！";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return knssqzt;
	}

	public void setKnssqzt(String knssqzt) {
		this.knssqzt = knssqzt;
	}

	public String getKnsshzt() {
		return knsshzt;
	}

	public void setKnsshzt(String knsshzt) {
		this.knsshzt = knsshzt;
	}

	public String getGjlzjxjsqzt() {
		XszzCsszService service = new XszzCsszService();
		try {
			boolean result = service.getGjlzjxjsqzt();
			gjlzjxjsqzt = result ? "" : "尚未开放国家励志奖学金申请，不能进行任何操作！";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return gjlzjxjsqzt;
	}

	public void setGjlzjxjsqzt(String gjlzjxjsqzt) {
		this.gjlzjxjsqzt = gjlzjxjsqzt;
	}

	public String getGjlzjxjshzt() {
		return gjlzjxjshzt;
	}

	public void setGjlzjxjshzt(String gjlzjxjshzt) {
		this.gjlzjxjshzt = gjlzjxjshzt;
	}

	public String getGjzxjsqzt() {
		XszzCsszService service = new XszzCsszService();
		try {
			boolean result = service.getGjzxjsqzt();
			gjzxjsqzt = result ? "" : "尚未开放国家助学金申请，不能进行任何操作！";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return gjzxjsqzt;
	}

	public void setGjzxjsqzt(String gjzxjsqzt) {
		this.gjzxjsqzt = gjzxjsqzt;
	}

	public String getGjzxjshzt() {
		return gjzxjshzt;
	}

	public void setGjzxjshzt(String gjzxjshzt) {
		this.gjzxjshzt = gjzxjshzt;
	}

	// ======================是否家庭情況調查 begin=================================

	public boolean getIsJtqkdc(String xh, String xn) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select count(1) num ");
		sql.append("from xg_xszz_jhzy_jtqkdzb ");
		sql.append("where xn = ?");
		sql.append(" and xh = ? ");

		String num = dao.getOneRs(sql.toString(), new String[] { xn, xh },
				"num");

		boolean isJtqkdc = ((Base.isNull(num) || "0".equalsIgnoreCase(num))) ? false
				: true;

		return isJtqkdc;
	}

	public void setIsJtqkdc(boolean isJtqkdc) {
		this.isJtqkdc = isJtqkdc;
	}

	// ======================是否家庭情況調查 end=================================

	// ======================是否困难生 begin=================================

	public boolean getIsKns(String xh, String xn) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select count(1) num ");
		sql.append("from xg_xszz_jhzy_knssqb ");
		sql.append("where xn = ?");
		sql.append(" and xh = ? ");
		sql.append(" and shzt = 'tg' ");
		sql.append(" and xxtjdc <> '不困难' ");
		
		String num = dao.getOneRs(sql.toString(), new String[] { xn, xh },
				"num");

		boolean isKns = ((Base.isNull(num) || "0".equalsIgnoreCase(num))) ? false
				: true;

		return isKns;
	}

	public void setIsKns(boolean isKns) {
		this.isKns = isKns;
	}

	// ======================是否困难生 end=================================

}
