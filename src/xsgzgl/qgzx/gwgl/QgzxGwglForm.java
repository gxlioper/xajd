package xsgzgl.qgzx.gwgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.form.User;
import xsgzgl.comm.form.CommForm;
/**
 * 勤工助学-勤工岗位管理-岗位信息管理
 * @author yeyipin
 * @since 2012.7.17
 */
public class QgzxGwglForm extends CommForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private String pkValue;//主键
	private String xn;//学年
	private String gwxh;//岗位序号
	private String yrbm;//用人部门
	private String gwdm;//岗位代码
	private String gwmc;//岗位名称
	private String gwxzdm;//岗位性质
	private String xqrs;//需求人数
	private String knsrs;//困难生人数
	private String fknsrs;//允许的非困难生人数
	private String gwms;//岗位描述
	private String gwryyq;//岗位人员要求
	private String bz;//备注
	private String gwyqryxg; //岗位预期人员效果
	
	private String doType;//操作类型
	private String zgzt;//在岗状态
	private String sgsj;//上岗时间
	private String tgsj;//退岗时间
	private String tgyy;//退岗原因
	private String shzt;//审核状态
	private String shyj;//审核意见
	private String sqbhs;//申请编号
	
	private String gwcjsx;//岗位酬金上限
	private ExportModel exportModel = new ExportModel();
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getYrbm() {
		return yrbm;
	}

	public void setYrbm(String yrbm) {
		this.yrbm = yrbm;
	}

	public String getGwdm() {
		return gwdm;
	}

	public void setGwdm(String gwdm) {
		this.gwdm = gwdm;
	}

	public String getGwmc() {
		return gwmc;
	}

	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}

	public String getGwxzdm() {
		return gwxzdm;
	}

	public void setGwxzdm(String gwxzdm) {
		this.gwxzdm = gwxzdm;
	}

	public String getXqrs() {
		return xqrs;
	}

	public void setXqrs(String xqrs) {
		this.xqrs = xqrs;
	}

	public String getKnsrs() {
		return knsrs;
	}

	public void setKnsrs(String knsrs) {
		this.knsrs = knsrs;
	}

	public String getFknsrs() {
		return fknsrs;
	}

	public void setFknsrs(String fknsrs) {
		this.fknsrs = fknsrs;
	}

	public String getGwms() {
		return gwms;
	}

	public void setGwms(String gwms) {
		this.gwms = gwms;
	}

	public String getGwryyq() {
		return gwryyq;
	}

	public void setGwryyq(String gwryyq) {
		this.gwryyq = gwryyq;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getDoType() {
		return doType;
	}

	public void setDoType(String doType) {
		this.doType = doType;
	}

	public String getZgzt() {
		return zgzt;
	}

	public void setZgzt(String zgzt) {
		this.zgzt = zgzt;
	}

	public String getSgsj() {
		return sgsj;
	}

	public void setSgsj(String sgsj) {
		this.sgsj = sgsj;
	}

	public String getTgsj() {
		return tgsj;
	}

	public void setTgsj(String tgsj) {
		this.tgsj = tgsj;
	}

	public String getTgyy() {
		return tgyy;
	}

	public void setTgyy(String tgyy) {
		this.tgyy = tgyy;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}

	/**
	 * @param exportModel要设置的 exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	/**
	 * @return the sqbhs
	 */
	public String getSqbhs() {
		return sqbhs;
	}

	/**
	 * @param sqbhs要设置的 sqbhs
	 */
	public void setSqbhs(String sqbhs) {
		this.sqbhs = sqbhs;
	}

	/**
	 * @return the gwcjsx
	 */
	public String getGwcjsx() {
		return gwcjsx;
	}

	/**
	 * @param gwcjsx要设置的 gwcjsx
	 */
	public void setGwcjsx(String gwcjsx) {
		this.gwcjsx = gwcjsx;
	}

	/**
	 * @return the gwxh
	 */
	public String getGwxh() {
		return gwxh;
	}

	/**
	 * @param gwxh要设置的 gwxh
	 */
	public void setGwxh(String gwxh) {
		this.gwxh = gwxh;
	}

	/**
	 * @return the gwyqryxg
	 */
	public String getGwyqryxg() {
		return gwyqryxg;
	}

	/**
	 * @param gwyqryxg要设置的 gwyqryxg
	 */
	public void setGwyqryxg(String gwyqryxg) {
		this.gwyqryxg = gwyqryxg;
	}
	
}
