package xgxt.xszz.nbty;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**
 * 国家助学贷款ActionForm
 * 
 */
public class GjzxdkForm extends ActionForm{
	
	private Pages pages = new Pages();
	private String xb;			//性别
	private String csrq;		//出生日期
	private String zymc;		//专业
	private String mjmc;		//班级
	private String xz;			//学制
	private String xn;    		//学年
	private String bysj;		//毕业时间
	private String ssbh;		//寝室编号
	private String qsdh;		//寝室电话
	private String jtdz;		//家庭地址
	private String jtdh;		//家庭电话
	private String jtyb;		//邮编
	private String lxdh;		//联系电话
	private String fqxm;		//父亲姓名
	private String fqdw;		//父亲单位
	private String fqsj;		//父亲手机
	private String mqxm;		//母亲姓名
	private String mqdw;		//母亲单位
	private String mqsj;		//母亲手机
	
	
	private String save_xh;     						//学号
	private String save_xn;							//学年
	private String save_jzr1;     						//见证人1
	private String save_sfzh1;    						//见证人1身份证号
	private String save_jzr2;      					//见证人2
	private String save_sfzh2;   						//见证人2身份证号
	private String save_sxnpddd;  						//上学年品德等第
	private String save_xxqkztpj = "优";  				//就读期学习情况总体评价
	private String save_ywblxyjl = "无";  				//有无不良信用记录
	private String save_hkfs = "等额本息还款法";     	//还款方式
	private String save_dkje;     						//贷款金额
	private String save_bjgbxkms;  					//就读期间累计不及格必修课目数
	private String save_xysh;     						//学院审核
	private String save_xxsh;     						//学校审核
	private String save_fdypy;     					//班主任评语
	private String save_xxshyj;    					//学校审核意见
	private String save_fdysh;    						//班主任审核
	private String save_xyshyj;    					//学院审核意见
	
	private String querylike_xm;			//查询_姓名
	private String queryequals_nj;			//查询_年级
	private String queryequals_nd;         //查询_年度
	private String queryequals_xn;         //查询_学年
	private String querylike_xh;			//查询_学号
	private String queryequals_xydm;		//查询_学院
	private String queryequals_zydm;		//查询_专业
	private String queryequals_bjdm;		//查询_班级
	private String querylike_sfzh;			//查询_身份证号
	private String[] primarykey_cbv;       //多选框主键

	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getBysj() {
		return bysj;
	}
	public void setBysj(String bysj) {
		this.bysj = bysj;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getFqdw() {
		return fqdw;
	}
	public void setFqdw(String fqdw) {
		this.fqdw = fqdw;
	}
	public String getFqsj() {
		return fqsj;
	}
	public void setFqsj(String fqsj) {
		this.fqsj = fqsj;
	}
	public String getFqxm() {
		return fqxm;
	}
	public void setFqxm(String fqxm) {
		this.fqxm = fqxm;
	}
	public String getJtdh() {
		return jtdh;
	}
	public String[] getPrimarykey_cbv() {
		return primarykey_cbv;
	}
	public void setPrimarykey_cbv(String[] primarykey_cbv) {
		this.primarykey_cbv = primarykey_cbv;
	}
	public void setJtdh(String jtdh) {
		this.jtdh = jtdh;
	}
	public String getJtdz() {
		return jtdz;
	}
	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}
	public String getJtyb() {
		return jtyb;
	}
	public void setJtyb(String jtyb) {
		this.jtyb = jtyb;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getMjmc() {
		return mjmc;
	}
	public void setMjmc(String mjmc) {
		this.mjmc = mjmc;
	}
	public String getMqdw() {
		return mqdw;
	}
	public void setMqdw(String mqdw) {
		this.mqdw = mqdw;
	}
	public String getMqsj() {
		return mqsj;
	}
	public void setMqsj(String mqsj) {
		this.mqsj = mqsj;
	}
	public String getMqxm() {
		return mqxm;
	}
	public void setMqxm(String mqxm) {
		this.mqxm = mqxm;
	}
	public String getQsdh() {
		return qsdh;
	}
	public void setQsdh(String qsdh) {
		this.qsdh = qsdh;
	}
	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}
	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
	}
	public String getQueryequals_nd() {
		return queryequals_nd;
	}
	public void setQueryequals_nd(String queryequals_nd) {
		this.queryequals_nd = queryequals_nd;
	}
	public String getQueryequals_nj() {
		return queryequals_nj;
	}
	public void setQueryequals_nj(String queryequals_nj) {
		this.queryequals_nj = queryequals_nj;
	}
	public String getQueryequals_xn() {
		return queryequals_xn;
	}
	public void setQueryequals_xn(String queryequals_xn) {
		this.queryequals_xn = queryequals_xn;
	}
	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}
	public void setQueryequals_xydm(String queryequals_xydm) {
		this.queryequals_xydm = queryequals_xydm;
	}
	public String getQueryequals_zydm() {
		return queryequals_zydm;
	}
	public void setQueryequals_zydm(String queryequals_zydm) {
		this.queryequals_zydm = queryequals_zydm;
	}
	public String getQuerylike_sfzh() {
		return querylike_sfzh;
	}
	public void setQuerylike_sfzh(String querylike_sfzh) {
		this.querylike_sfzh = querylike_sfzh;
	}
	public String getQuerylike_xh() {
		return querylike_xh;
	}
	public void setQuerylike_xh(String querylike_xh) {
		this.querylike_xh = querylike_xh;
	}
	public String getQuerylike_xm() {
		return querylike_xm;
	}
	public void setQuerylike_xm(String querylike_xm) {
		this.querylike_xm = querylike_xm;
	}
	public String getSave_bjgbxkms() {
		return save_bjgbxkms;
	}
	public void setSave_bjgbxkms(String save_bjgbxkms) {
		this.save_bjgbxkms = save_bjgbxkms;
	}
	public String getSave_dkje() {
		return save_dkje;
	}
	public void setSave_dkje(String save_dkje) {
		this.save_dkje = save_dkje;
	}
	public String getSave_fdypy() {
		return save_fdypy;
	}
	public void setSave_fdypy(String save_fdypy) {
		this.save_fdypy = save_fdypy;
	}
	public String getSave_fdysh() {
		return save_fdysh;
	}
	public void setSave_fdysh(String save_fdysh) {
		this.save_fdysh = save_fdysh;
	}
	public String getSave_hkfs() {
		return save_hkfs;
	}
	public void setSave_hkfs(String save_hkfs) {
		this.save_hkfs = save_hkfs;
	}
	public String getSave_jzr1() {
		return save_jzr1;
	}
	public void setSave_jzr1(String save_jzr1) {
		this.save_jzr1 = save_jzr1;
	}
	public String getSave_jzr2() {
		return save_jzr2;
	}
	public void setSave_jzr2(String save_jzr2) {
		this.save_jzr2 = save_jzr2;
	}
	public String getSave_sfzh1() {
		return save_sfzh1;
	}
	public void setSave_sfzh1(String save_sfzh1) {
		this.save_sfzh1 = save_sfzh1;
	}
	public String getSave_sfzh2() {
		return save_sfzh2;
	}
	public void setSave_sfzh2(String save_sfzh2) {
		this.save_sfzh2 = save_sfzh2;
	}
	public String getSave_sxnpddd() {
		return save_sxnpddd;
	}
	public void setSave_sxnpddd(String save_sxnpddd) {
		this.save_sxnpddd = save_sxnpddd;
	}
	public String getSave_xh() {
		return save_xh;
	}
	public void setSave_xh(String save_xh) {
		this.save_xh = save_xh;
	}
	public String getSave_xn() {
		return save_xn;
	}
	public void setSave_xn(String save_xn) {
		this.save_xn = save_xn;
	}
	public String getSave_xxqkztpj() {
		return save_xxqkztpj;
	}
	public void setSave_xxqkztpj(String save_xxqkztpj) {
		this.save_xxqkztpj = save_xxqkztpj;
	}
	public String getSave_xxsh() {
		return save_xxsh;
	}
	public void setSave_xxsh(String save_xxsh) {
		this.save_xxsh = save_xxsh;
	}
	public String getSave_xxshyj() {
		return save_xxshyj;
	}
	public void setSave_xxshyj(String save_xxshyj) {
		this.save_xxshyj = save_xxshyj;
	}
	public String getSave_xysh() {
		return save_xysh;
	}
	public void setSave_xysh(String save_xysh) {
		this.save_xysh = save_xysh;
	}
	public String getSave_xyshyj() {
		return save_xyshyj;
	}
	public void setSave_xyshyj(String save_xyshyj) {
		this.save_xyshyj = save_xyshyj;
	}
	public String getSave_ywblxyjl() {
		return save_ywblxyjl;
	}
	public void setSave_ywblxyjl(String save_ywblxyjl) {
		this.save_ywblxyjl = save_ywblxyjl;
	}
	public String getSsbh() {
		return ssbh;
	}
	public void setSsbh(String ssbh) {
		this.ssbh = ssbh;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXz() {
		return xz;
	}
	public void setXz(String xz) {
		this.xz = xz;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	
}
