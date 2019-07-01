package xsgzgl.gygl.gyjldmgl;

import xsgzgl.gygl.comm.GyglNewForm;

public class GyjldmglForm extends GyglNewForm{

	private static final long serialVersionUID = 1L;
	
	private String jldldm;//纪律大类代码
	private String jldlmc;//纪律大类名称
	private String jllbdm;//纪律类别代码
	private String jllbmc;//纪律类别名称
	private String cflbdm;//纪律处分代码
	private String cflbmc;//记录处分名称
	private String jllbkf;//记录类别扣分
	
	//查询条件
	private String query_jldldm;//纪律大类代码
	private String query_jldlmc;//纪律大类名称
	private String query_jllbdm;//纪律类别代码
	private String query_jllbmc;//纪律类别名称
	private String query_cflbdm;//处分类别代码
	private String query_cflbmc;//处分类别名称
	
	//浙江旅游个性化代码维护
	private String gyjllbdldm;//旅游公寓加扣分代码
	private String gyjllbdlmc;//旅游公寓加扣分名称
	private String lb;//旅游公寓加扣分类别，加分：jf,扣分：kf
	private String fz;//分值
	private String type;//类型
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type要设置的 type
	 */
	public void setType(String type) {
		this.type = type;
	}
	public String getJldldm() {
		return jldldm;
	}
	public void setJldldm(String jldldm) {
		this.jldldm = jldldm;
	}
	public String getJldlmc() {
		return jldlmc;
	}
	public void setJldlmc(String jldlmc) {
		this.jldlmc = jldlmc;
	}
	public String getJllbdm() {
		return jllbdm;
	}
	public void setJllbdm(String jllbdm) {
		this.jllbdm = jllbdm;
	}
	public String getJllbmc() {
		return jllbmc;
	}
	public void setJllbmc(String jllbmc) {
		this.jllbmc = jllbmc;
	}
	public String getQuery_jldldm() {
		return query_jldldm;
	}
	public void setQuery_jldldm(String queryJldldm) {
		query_jldldm = queryJldldm;
	}
	public String getQuery_jldlmc() {
		return query_jldlmc;
	}
	public void setQuery_jldlmc(String queryJldlmc) {
		query_jldlmc = queryJldlmc;
	}
	public String getQuery_jllbdm() {
		return query_jllbdm;
	}
	public void setQuery_jllbdm(String queryJllbdm) {
		query_jllbdm = queryJllbdm;
	}
	public String getQuery_jllbmc() {
		return query_jllbmc;
	}
	public void setQuery_jllbmc(String queryJllbmc) {
		query_jllbmc = queryJllbmc;
	}
	
	public String getCflbdm() {
		return cflbdm;
	}
	public void setCflbdm(String cflbdm) {
		this.cflbdm = cflbdm;
	}
	public String getCflbmc() {
		return cflbmc;
	}
	public void setCflbmc(String cflbmc) {
		this.cflbmc = cflbmc;
	}
	public String getQuery_cflbdm() {
		return query_cflbdm;
	}
	public void setQuery_cflbdm(String queryCflbdm) {
		query_cflbdm = queryCflbdm;
	}
	public String getQuery_cflbmc() {
		return query_cflbmc;
	}
	public void setQuery_cflbmc(String queryCflbmc) {
		query_cflbmc = queryCflbmc;
	}
	public String getJllbkf() {
		return jllbkf;
	}
	public void setJllbkf(String jllbkf) {
		this.jllbkf = jllbkf;
	}
	
	/*浙江旅游start*/
	/**
	 * @return the gyjllbdldm
	 */
	public String getGyjllbdldm() {
		return gyjllbdldm;
	}
	/**
	 * @param gyjllbdldm要设置的 gyjllbdldm
	 */
	public void setGyjllbdldm(String gyjllbdldm) {
		this.gyjllbdldm = gyjllbdldm;
	}
	/**
	 * @return the gyjllbdlmc
	 */
	public String getGyjllbdlmc() {
		return gyjllbdlmc;
	}
	/**
	 * @param gyjllbdlmc要设置的 gyjllbdlmc
	 */
	public void setGyjllbdlmc(String gyjllbdlmc) {
		this.gyjllbdlmc = gyjllbdlmc;
	}
	/**
	 * @return the lb
	 */
	public String getLb() {
		return lb;
	}
	/**
	 * @param lb要设置的 lb
	 */
	public void setLb(String lb) {
		this.lb = lb;
	}
	/**
	 * @return the fz
	 */
	public String getFz() {
		return fz;
	}
	/**
	 * @param fz要设置的 fz
	 */
	public void setFz(String fz) {
		this.fz = fz;
	}
	/*浙江旅游end*/	
}
