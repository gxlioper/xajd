package xgxt.xljk.zxzx.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import xgxt.base.DealString;

/**
 * MyEclipse Struts Creation date: 06-15-2007
 */
public class xljk_xssqyy_Form extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String errMsg;

	private String[] errMsgs;

	private String[] checkVal;// 批处理

	private String yydjb_xnid = "";

	private String yydjb_sfzyg_xnid = "";

	private String yydjb_jtcy_xnid = "";

	private String yydjb_zxyq_xnid = "";

	private String userType = "";

	private String tableName = "";

	private String realTable = "";

	private String pk = "";

	private String ZXXBH = "";// 咨询师编号

	private String zxxbh = "";

	private String zxxxm = "";// 咨询师姓名

	private String sex = "";// 咨询师性别

	private String xh = "";

	private String xsmm = "";

	private String xssrmm = "";// 外部输入的密码

	private String xm = "";

	private String csrq = "";

	private String jg = "";// 籍贯

	private String zxszy_xnid = "";

	private String jtjjzk = "";// 家庭经济状况

	private String qsdh = "";// 寝室电话

	private String sjhm = "";// 手机号码

	private String zymycd = "";// 专业满意程度

	private String zymycd_dm = "";// 专业满意程度代码

	private String zxnr = "";// 咨询内容

	private String qwmb = "";// 期望目标

	private String jgmc = "";// 其他咨询过的心理机构名称

	private String ysxm = "";// 其他咨询过的医生姓名

	private String qzsj = "";// 咨询的起止时间

	private String zdjg = "";// 诊断结果

	private String sffyyw = "";// 是否服用过或者正在服用药物

	private String gx = "";// 家庭成员关系

	private String csny = "";// 出生年月

	private String xl = "";// 学历

	private String zysf = "";// 职业身份

	private String jjcd = "";// 紧急程度

	private String zy = "";

	private String qt = "";

	private String xy = "";

	private String sjd = "";

	private String sjd_dm = "";

	private String dd = "";

	private String dd_dm = "";

	private String rq = "";

	private String flag = "";

	private String JTCY1_gx = "";

	private String JTCY1_csny = "";

	private String JTCY1_xl = "";

	private String JTCY1_zysf = "";

	private String JTCY2_gx = "";

	private String JTCY2_csny = "";

	private String JTCY2_xl = "";

	private String JTCY2_zysf = "";

	private String JTCY3_gx = "";

	private String JTCY3_csny = "";

	private String JTCY3_xl = "";

	private String JTCY3_zysf = "";

	private String JTCY4_gx = "";

	private String JTCY4_csny = "";

	private String JTCY4_xl = "";

	private String JTCY4_zysf = "";

	private String JTCY5_gx = "";

	private String JTCY5_csny = "";

	private String JTCY5_xl = "";

	private String JTCY5_zysf = "";

	private String sfzxg_flag = "";

	private String zxs_sex = "";

	private String xs_sex = "";

	private String yyfs = "";

	private String nj = "";

	private String userName = "";

	private String userDep = "";

	private String isFdy = "";

	private String isBzr = "";

	private HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getXs_sex() {
		return xs_sex;
	}

	public void setXs_sex(String xs_sex) {
		this.xs_sex = xs_sex;
	}

	public String getZxs_sex() {
		return zxs_sex;
	}

	public void setZxs_sex(String zxs_sex) {
		this.zxs_sex = zxs_sex;
	}

	public String getSfzxg_flag() {
		return sfzxg_flag;
	}

	public void setSfzxg_flag(String sfzxg_flag) {
		this.sfzxg_flag = sfzxg_flag;
	}

	public String getJTCY1_csny() {
		return JTCY1_csny;
	}

	public void setJTCY1_csny(String jtcy1_csny) {
		JTCY1_csny = jtcy1_csny;
	}

	public String getJTCY1_gx() {
		return JTCY1_gx;
	}

	public void setJTCY1_gx(String jtcy1_gx) {
		JTCY1_gx = jtcy1_gx;
	}

	public String getJTCY1_xl() {
		return JTCY1_xl;
	}

	public void setJTCY1_xl(String jtcy1_xl) {
		JTCY1_xl = jtcy1_xl;
	}

	public String getJTCY1_zysf() {
		return JTCY1_zysf;
	}

	public void setJTCY1_zysf(String jtcy1_zysf) {
		JTCY1_zysf = jtcy1_zysf;
	}

	public String getJTCY2_csny() {
		return JTCY2_csny;
	}

	public void setJTCY2_csny(String jtcy2_csny) {
		JTCY2_csny = jtcy2_csny;
	}

	public String getJTCY2_gx() {
		return JTCY2_gx;
	}

	public void setJTCY2_gx(String jtcy2_gx) {
		JTCY2_gx = jtcy2_gx;
	}

	public String getJTCY2_xl() {
		return JTCY2_xl;
	}

	public void setJTCY2_xl(String jtcy2_xl) {
		JTCY2_xl = jtcy2_xl;
	}

	public String getJTCY2_zysf() {
		return JTCY2_zysf;
	}

	public void setJTCY2_zysf(String jtcy2_zysf) {
		JTCY2_zysf = jtcy2_zysf;
	}

	public String getJTCY3_csny() {
		return JTCY3_csny;
	}

	public void setJTCY3_csny(String jtcy3_csny) {
		JTCY3_csny = jtcy3_csny;
	}

	public String getJTCY3_gx() {
		return JTCY3_gx;
	}

	public void setJTCY3_gx(String jtcy3_gx) {
		JTCY3_gx = jtcy3_gx;
	}

	public String getJTCY3_xl() {
		return JTCY3_xl;
	}

	public void setJTCY3_xl(String jtcy3_xl) {
		JTCY3_xl = jtcy3_xl;
	}

	public String getJTCY3_zysf() {
		return JTCY3_zysf;
	}

	public void setJTCY3_zysf(String jtcy3_zysf) {
		JTCY3_zysf = jtcy3_zysf;
	}

	public String getJTCY4_csny() {
		return JTCY4_csny;
	}

	public void setJTCY4_csny(String jtcy4_csny) {
		JTCY4_csny = jtcy4_csny;
	}

	public String getJTCY4_gx() {
		return JTCY4_gx;
	}

	public void setJTCY4_gx(String jtcy4_gx) {
		JTCY4_gx = jtcy4_gx;
	}

	public String getJTCY4_xl() {
		return JTCY4_xl;
	}

	public void setJTCY4_xl(String jtcy4_xl) {
		JTCY4_xl = jtcy4_xl;
	}

	public String getJTCY4_zysf() {
		return JTCY4_zysf;
	}

	public void setJTCY4_zysf(String jtcy4_zysf) {
		JTCY4_zysf = jtcy4_zysf;
	}

	public String getDd() {
		return dd;
	}

	public void setDd(String dd) {
		this.dd = dd;
	}

	public String getRq() {
		return rq;
	}

	public void setRq(String rq) {
		this.rq = rq;
	}

	public String getSjd() {
		return sjd;
	}

	public void setSjd(String sjd) {
		this.sjd = sjd;
	}

	public String getXy() {
		return xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}

	public String getZy() {
		return zy;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		this.JTCY1_csny = DealString.toGBK(request.getParameter("JTCY1_csny"));
		this.JTCY1_gx = DealString.toGBK(request.getParameter("JTCY1_gx"));
		this.JTCY1_xl = DealString.toGBK(request.getParameter("JTCY1_xl"));
		this.JTCY1_zysf = DealString.toGBK(request.getParameter("JTCY1_zysf"));
		this.JTCY2_csny = DealString.toGBK(request.getParameter("JTCY2_csny"));
		this.JTCY2_gx = DealString.toGBK(request.getParameter("JTCY2_gx"));
		this.JTCY2_xl = DealString.toGBK(request.getParameter("JTCY2_xl"));
		this.JTCY2_zysf = DealString.toGBK(request.getParameter("JTCY2_zysf"));
		this.JTCY3_csny = DealString.toGBK(request.getParameter("JTCY3_csny"));
		this.JTCY3_gx = DealString.toGBK(request.getParameter("JTCY3_gx"));
		this.JTCY3_xl = DealString.toGBK(request.getParameter("JTCY3_xl"));
		this.JTCY3_zysf = DealString.toGBK(request.getParameter("JTCY3_zysf"));
		this.JTCY4_csny = DealString.toGBK(request.getParameter("JTCY4_csny"));
		this.JTCY4_gx = DealString.toGBK(request.getParameter("JTCY4_gx"));
		this.JTCY4_xl = DealString.toGBK(request.getParameter("JTCY4_xl"));
		this.JTCY4_zysf = DealString.toGBK(request.getParameter("JTCY4_zysf"));
		this.JTCY5_csny = DealString.toGBK(request.getParameter("JTCY5_csny"));
		this.JTCY5_gx = DealString.toGBK(request.getParameter("JTCY5_gx"));
		this.JTCY5_xl = DealString.toGBK(request.getParameter("JTCY5_xl"));
		this.JTCY5_zysf = DealString.toGBK(request.getParameter("JTCY5_zysf"));
		this.jg = DealString.toGBK(request.getParameter("jg"));
		this.jgmc = DealString.toGBK(request.getParameter("jgmc"));
		this.jtjjzk = DealString.toGBK(request.getParameter("jtjjzk"));
		this.qsdh = DealString.toGBK(request.getParameter("qsdh"));
		this.qwmb = DealString.toGBK(request.getParameter("qwmb"));
		this.qzsj = DealString.toGBK(request.getParameter("qzsj"));
		this.rq = DealString.toGBK(request.getParameter("rq"));
		this.xs_sex = DealString.toGBK(request.getParameter("xs_sex"));
		this.zxs_sex = DealString.toGBK(request.getParameter("zxs_sex"));
		this.sffyyw = DealString.toGBK(request.getParameter("sffyyw"));
		this.sfzxg_flag = DealString.toGBK(request.getParameter("sfzxg_flag"));
		this.sjd = DealString.toGBK(request.getParameter("sjd"));
		this.sjhm = DealString.toGBK(request.getParameter("sjhm"));
		this.xh = request.getParameter("xh");
		this.xm = DealString.toGBK(request.getParameter("xm"));
		this.xy = DealString.toGBK(request.getParameter("xy"));
		this.ysxm = DealString.toGBK(request.getParameter("ysxm"));
		this.zdjg = DealString.toGBK(request.getParameter("zdjg"));
		this.zxnr = DealString.toGBK(request.getParameter("zxnr"));
		this.zxszy_xnid = DealString.toGBK(request.getParameter("zxszy_xnid"));
		this.zxxxm = DealString.toGBK(request.getParameter("zxxxm"));
		this.zymycd_dm = DealString.toGBK(request.getParameter("zymycd"));
		this.csrq = DealString.toGBK(request.getParameter("csrq"));
		this.qt = DealString.toGBK(request.getParameter("qt"));
		this.dd = DealString.toGBK(request.getParameter("dd"));
		this.yyfs = DealString.toGBK(request.getParameter("yyfs"));
		if (zymycd_dm != null && !zymycd_dm.equalsIgnoreCase("")) {
			if (zymycd_dm.equals("1")) {
				this.zymycd = "非常满意";
			}
			if (zymycd_dm.equals("2")) {
				this.zymycd = "较满意";
			}
			if (zymycd_dm.equals("3")) {
				this.zymycd = "一般";
			}
			if (zymycd_dm.equals("4")) {
				this.zymycd = "不满意";
			}
			if (zymycd_dm.equals("5")) {
				this.zymycd = "非常不满意";
			}
		}
		return null;
	}

	/**
	 * Method reset
	 * 
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getRealTable() {
		return realTable;
	}

	public void setRealTable(String realTable) {
		this.realTable = realTable;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getZXXBH() {
		return ZXXBH;
	}

	public void setZXXBH(String zxxbh) {
		ZXXBH = zxxbh;
	}

	public String getCsny() {
		return csny;
	}

	public void setCsny(String csny) {
		this.csny = csny;
	}

	public String getCsrq() {
		return csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getGx() {
		return gx;
	}

	public void setGx(String gx) {
		this.gx = gx;
	}

	public String getJg() {
		return jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}

	public String getJgmc() {
		return jgmc;
	}

	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}

	public String getJtjjzk() {
		return jtjjzk;
	}

	public void setJtjjzk(String jtjjzk) {
		this.jtjjzk = jtjjzk;
	}

	public String getQsdh() {
		return qsdh;
	}

	public void setQsdh(String qsdh) {
		this.qsdh = qsdh;
	}

	public String getQwmb() {
		return qwmb;
	}

	public void setQwmb(String qwmb) {
		this.qwmb = qwmb;
	}

	public String getQzsj() {
		return qzsj;
	}

	public void setQzsj(String qzsj) {
		this.qzsj = qzsj;
	}

	public String getSffyyw() {
		return sffyyw;
	}

	public void setSffyyw(String sffyyw) {
		this.sffyyw = sffyyw;
	}

	public String getSjhm() {
		return sjhm;
	}

	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXl() {
		return xl;
	}

	public void setXl(String xl) {
		this.xl = xl;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXsmm() {
		return xsmm;
	}

	public void setXsmm(String xsmm) {
		this.xsmm = xsmm;
	}

	public String getXssrmm() {
		return xssrmm;
	}

	public void setXssrmm(String xssrmm) {
		this.xssrmm = xssrmm;
	}

	public String getYsxm() {
		return ysxm;
	}

	public void setYsxm(String ysxm) {
		this.ysxm = ysxm;
	}

	public String getYydjb_jtcy_xnid() {
		return yydjb_jtcy_xnid;
	}

	public void setYydjb_jtcy_xnid(String yydjb_jtcy_xnid) {
		this.yydjb_jtcy_xnid = yydjb_jtcy_xnid;
	}

	public String getYydjb_sfzyg_xnid() {
		return yydjb_sfzyg_xnid;
	}

	public void setYydjb_sfzyg_xnid(String yydjb_sfzyg_xnid) {
		this.yydjb_sfzyg_xnid = yydjb_sfzyg_xnid;
	}

	public String getYydjb_xnid() {
		return yydjb_xnid;
	}

	public void setYydjb_xnid(String yydjb_xnid) {
		this.yydjb_xnid = yydjb_xnid;
	}

	public String getYydjb_zxyq_xnid() {
		return yydjb_zxyq_xnid;
	}

	public void setYydjb_zxyq_xnid(String yydjb_zxyq_xnid) {
		this.yydjb_zxyq_xnid = yydjb_zxyq_xnid;
	}

	public String getZdjg() {
		return zdjg;
	}

	public void setZdjg(String zdjg) {
		this.zdjg = zdjg;
	}

	public String getZxnr() {
		return zxnr;
	}

	public void setZxnr(String zxnr) {
		this.zxnr = zxnr;
	}

	public String getZxszy_xnid() {
		return zxszy_xnid;
	}

	public void setZxszy_xnid(String zxszy_xnid) {
		this.zxszy_xnid = zxszy_xnid;
	}

	public String getZymycd() {
		return zymycd;
	}

	public void setZymycd(String zymycd) {
		this.zymycd = zymycd;
	}

	public String getZymycd_dm() {

		return zymycd_dm;

	}

	public void setZymycd_dm(String zymycd_dm) {
		if (this.zymycd != "") {
			if (zymycd.equals("非常满意")) {
				this.zymycd_dm = "1";
			} else if (zymycd.equals("较满意")) {
				this.zymycd_dm = "2";
			} else if (zymycd.equals("一般")) {
				this.zymycd_dm = "3";
			} else if (zymycd.equals("不满意")) {
				this.zymycd_dm = "4";
			} else if (zymycd.equals("非常不满意")) {
				this.zymycd_dm = "5";
			}
		} else {
			this.zymycd_dm = zymycd_dm;
		}

	}

	public String getZysf() {
		return zysf;
	}

	public void setZysf(String zysf) {
		this.zysf = zysf;
	}

	public String getZxxxm() {
		return zxxxm;
	}

	public void setZxxxm(String zxxxm) {
		this.zxxxm = zxxxm;
	}

	public String getJTCY5_csny() {
		return JTCY5_csny;
	}

	public void setJTCY5_csny(String jtcy5_csny) {
		JTCY5_csny = jtcy5_csny;
	}

	public String getJTCY5_gx() {
		return JTCY5_gx;
	}

	public void setJTCY5_gx(String jtcy5_gx) {
		JTCY5_gx = jtcy5_gx;
	}

	public String getJTCY5_xl() {
		return JTCY5_xl;
	}

	public void setJTCY5_xl(String jtcy5_xl) {
		JTCY5_xl = jtcy5_xl;
	}

	public String getJTCY5_zysf() {
		return JTCY5_zysf;
	}

	public void setJTCY5_zysf(String jtcy5_zysf) {
		JTCY5_zysf = jtcy5_zysf;
	}

	public String getQt() {
		return qt;
	}

	public void setQt(String qt) {
		this.qt = qt;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getDd_dm() {
		return dd_dm;
	}

	public void setDd_dm(String dd_dm) {
		this.dd_dm = dd_dm;
	}

	public String getSjd_dm() {
		return sjd_dm;
	}

	public void setSjd_dm(String sjd_dm) {
		this.sjd_dm = sjd_dm;
	}

	public String getZxxbh() {
		return zxxbh;
	}

	public void setZxxbh(String zxxbh) {
		this.zxxbh = zxxbh;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String[] getErrMsgs() {
		return errMsgs;
	}

	public void setErrMsgs(String[] errMsgs) {
		this.errMsgs = errMsgs;
	}

	public String getYyfs() {
		return yyfs;
	}

	public void setYyfs(String yyfs) {
		this.yyfs = yyfs;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getIsBzr() {
		return isBzr;
	}

	public void setIsBzr(String isBzr) {
		this.isBzr = isBzr;
	}

	public String getIsFdy() {
		return isFdy;
	}

	public void setIsFdy(String isFdy) {
		this.isFdy = isFdy;
	}

	public String getUserDep() {
		return userDep;
	}

	public void setUserDep(String userDep) {
		this.userDep = userDep;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getJjcd() {
		return jjcd;
	}

	public void setJjcd(String jjcd) {
		this.jjcd = jjcd;
	}

}