package xgxt.studentInfo.sjxy.shgz;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class ShgzForm extends ActionForm{
	Pages pages = new Pages();
	private String xh;    		 //学号
	private String ftwsh = "未审核";     	 //分团委审核
	private String xtwshsj="";     //校团委审核时间
	private String ftwshsj="";     //分团委审核时间
	private String ftwshyj="";     //分团委审核意见
	private String xtwshyj="";     //校团委审核意见
	private String xtwsh = "未审核";     	 //校团委审核
	private String[] rzkssj;	 //任职开始时间
	private String[] rzjssj;	//任职结束时间
	private String[] rzbm;		//任职部门
	private String[] zw;		//职务
	private String[] jdbm;		//鉴定部门
	private String[] hjkssj;	//获奖开始时间
	private String[] hjjssj;	//获奖结束时间
	private String[] jllb;		//奖励类别
	private String[] sjbm;		//授奖部门
	private String[] pkValues; //批量删除主键
	
	private String querylike_xm;			//查询_姓名
	private String querylike_xh;			//查询_学号
	private String queryequals_ftwsh;		//查询_分团委审核
	private String queryequals_xtwsh;      //查询_校团委审核
	private String queryequals_xydm;		//查询_学院
	private String queryequals_zydm;		//查询_专业
	private String queryequals_bjdm;		//查询_班级
	
	public String[] getHjjssj() {
		return hjjssj;
	}
	public void setHjjssj(String[] hjjssj) {
		this.hjjssj = hjjssj;
	}
	public String[] getHjkssj() {
		return hjkssj;
	}
	public void setHjkssj(String[] hjkssj) {
		this.hjkssj = hjkssj;
	}
	public String[] getJllb() {
		return jllb;
	}
	public void setJllb(String[] jllb) {
		this.jllb = jllb;
	}
	public String[] getRzjssj() {
		return rzjssj;
	}
	public void setRzjssj(String[] rzjssj) {
		this.rzjssj = rzjssj;
	}
	public String[] getRzkssj() {
		return rzkssj;
	}
	public void setRzkssj(String[] rzkssj) {
		this.rzkssj = rzkssj;
	}
	public String[] getSjbm() {
		return sjbm;
	}
	public void setSjbm(String[] sjbm) {
		this.sjbm = sjbm;
	}
	public String[] getJdbm() {
		return jdbm;
	}
	public void setJdbm(String[] jdbm) {
		this.jdbm = jdbm;
	}
	public String[] getRzbm() {
		return rzbm;
	}
	public void setRzbm(String[] rzbm) {
		this.rzbm = rzbm;
	}
	public String[] getZw() {
		return zw;
	}
	public void setZw(String[] zw) {
		this.zw = zw;
	}
	public String getFtwsh() {
		return ftwsh;
	}
	public void setFtwsh(String ftwsh) {
		this.ftwsh = ftwsh;
	}
	public String getFtwshsj() {
		return ftwshsj;
	}
	public void setFtwshsj(String ftwshsj) {
		this.ftwshsj = ftwshsj;
	}
	public String getFtwshyj() {
		return ftwshyj;
	}
	public void setFtwshyj(String ftwshyj) {
		this.ftwshyj = ftwshyj;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXtwsh() {
		return xtwsh;
	}
	public void setXtwsh(String xtwsh) {
		this.xtwsh = xtwsh;
	}
	public String getXtwshyj() {
		return xtwshyj;
	}
	public void setXtwshyj(String xtwshyj) {
		this.xtwshyj = xtwshyj;
	}
	public String getXtwshsj() {
		return xtwshsj;
	}
	public void setXtwshsj(String xtwshsj) {
		this.xtwshsj = xtwshsj;
	}
	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}
	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
	}
	public String getQueryequals_ftwsh() {
		return queryequals_ftwsh;
	}
	public void setQueryequals_ftwsh(String queryequals_ftwsh) {
		this.queryequals_ftwsh = queryequals_ftwsh;
	}
	public String getQueryequals_xtwsh() {
		return queryequals_xtwsh;
	}
	public void setQueryequals_xtwsh(String queryequals_xtwsh) {
		this.queryequals_xtwsh = queryequals_xtwsh;
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
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String[] getPkValues() {
		return pkValues;
	}
	public void setPkValues(String[] pkValues) {
		this.pkValues = pkValues;
	}
	
	
}
