package xsgzgl.qgzx.cjffsq;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.upload.FormFile;
import xgxt.form.User;
import xsgzgl.comm.form.CommForm;
/**
 * 勤工助学-酬金管理-酬金信息管理
 * @author yeyipin
 * @since 2012.7.23
 */
public class QgzxCjffsqForm extends CommForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private String wbh;
	private String pkValue;//主键
	private String doType;//操作类型
	private String xn;//学年
	private String yrbm;//用人部门
	private String gwdm;//岗位代码
	private String gwmc;//岗位名称
	private String gwry;//岗位人员
	private String xm;//姓名
	private String zgzt;//在岗状态
	private String ffny;//月份
	private String tjzt;//提交状态
	private String gs;//工时
	private String je;//金额
	private String khdj;//考核等级
	private String bz;//备注
	private String cjsx;//酬金上限
	private String sxzd;
	private String sxsz;
	private String cjffr;//酬金发放人
	private String jcdlgs; //基础当量工时
	private String zhdlgs; //综合当量工时
	private String xq;//学期
	private String sqzt;//申请状态
	//武昌首义个性化字段
	private String jfhb;//经费划拨金额
	private String zc;//经费划拨金额
	
	
	
	private String shzt;//审核状态
	private String sqid;//申请id
	private String gwid;//岗位ID
	private String splc;//审批流程
	private String shyj;//审核意见
	private String thgw;//退回岗位
	private String shjg;//审核结果
	private String shid;
	private String[] sqids;
	private String[] gwids;
	private String[] splcs;
	private FormFile importFile;
	private ExportModel exportModel = new ExportModel();

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public FormFile getImportFile() {
		return importFile;
	}

	public void setImportFile(FormFile importFile) {
		this.importFile = importFile;
	}

	public String getSqzt() {
		return sqzt;
	}

	public void setSqzt(String sqzt) {
		this.sqzt = sqzt;
	}

	/**
	 * @return the shid
	 */
	public String getShid() {
		return shid;
	}
	/**
	 * @param shid要设置的 shid
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}
	/**
	 * @return the sqids
	 */
	public String[] getSqids() {
		return sqids;
	}
	/**
	 * @param sqids要设置的 sqids
	 */
	public void setSqids(String[] sqids) {
		this.sqids = sqids;
	}
	/**
	 * @return the gwids
	 */
	public String[] getGwids() {
		return gwids;
	}
	/**
	 * @param gwids要设置的 gwids
	 */
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	/**
	 * @return the splcs
	 */
	public String[] getSplcs() {
		return splcs;
	}
	/**
	 * @param splcs要设置的 splcs
	 */
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}
	/**
	 * @return the shjg
	 */
	public String getShjg() {
		return shjg;
	}
	/**
	 * @param shjg要设置的 shjg
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	/**
	 * @return the thgw
	 */
	public String getThgw() {
		return thgw;
	}
	/**
	 * @param thgw要设置的 thgw
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	/**
	 * @return the shyj
	 */
	public String getShyj() {
		return shyj;
	}
	/**
	 * @param shyj要设置的 shyj
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqid要设置的 sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	/**
	 * @return the shid
	 */
	/**
	 * @return the gwid
	 */
	public String getGwid() {
		return gwid;
	}
	/**
	 * @param gwid要设置的 gwid
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splc要设置的 splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @param shzt要设置的 shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getCjffr() {
		return cjffr;
	}
	public void setCjffr(String cjffr) {
		this.cjffr = cjffr;
	}
	//用于修改
	private String yffxh;
	private String yffgw;
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
	public String getDoType() {
		return doType;
	}
	public void setDoType(String doType) {
		this.doType = doType;
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
	
	public String getGwry() {
		return gwry;
	}
	public void setGwry(String gwry) {
		this.gwry = gwry;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getZgzt() {
		return zgzt;
	}
	public void setZgzt(String zgzt) {
		this.zgzt = zgzt;
	}
	public String getFfny() {
		return ffny;
	}
	public void setFfny(String ffny) {
		this.ffny = ffny;
	}
	public String getTjzt() {
		return tjzt;
	}
	public void setTjzt(String tjzt) {
		this.tjzt = tjzt;
	}
	public String getGs() {
		return gs;
	}
	public void setGs(String gs) {
		this.gs = gs;
	}
	public String getJe() {
		return je;
	}
	public void setJe(String je) {
		this.je = je;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @return the yffxh
	 */
	public String getYffxh() {
		return yffxh;
	}
	/**
	 * @param yffxh要设置的 yffxh
	 */
	public void setYffxh(String yffxh) {
		this.yffxh = yffxh;
	}
	/**
	 * @return the yffgw
	 */
	public String getYffgw() {
		return yffgw;
	}
	/**
	 * @param yffgw要设置的 yffgw
	 */
	public void setYffgw(String yffgw) {
		this.yffgw = yffgw;
	}
	/**
	 * @return the khdj
	 */
	public String getKhdj() {
		return khdj;
	}
	/**
	 * @param khdj要设置的 khdj
	 */
	public void setKhdj(String khdj) {
		this.khdj = khdj;
	}
	/**
	 * @return the cjsx
	 */
	public String getCjsx() {
		return cjsx;
	}
	/**
	 * @param cjsx要设置的 cjsx
	 */
	public void setCjsx(String cjsx) {
		this.cjsx = cjsx;
	}
	/**
	 * @return the sxzd
	 */
	public String getSxzd() {
		return sxzd;
	}
	/**
	 * @param sxzd要设置的 sxzd
	 */
	public void setSxzd(String sxzd) {
		this.sxzd = sxzd;
	}
	/**
	 * @return the sxsz
	 */
	public String getSxsz() {
		return sxsz;
	}
	/**
	 * @param sxsz要设置的 sxsz
	 */
	public void setSxsz(String sxsz) {
		this.sxsz = sxsz;
	}
	/**
	 * @return the wbh
	 */
	public String getWbh() {
		return wbh;
	}
	/**
	 * @param wbh要设置的 wbh
	 */
	public void setWbh(String wbh) {
		this.wbh = wbh;
	}
	/**
	 * @return the jcdlgs
	 */
	public String getJcdlgs() {
		return jcdlgs;
	}
	/**
	 * @param jcdlgs要设置的 jcdlgs
	 */
	public void setJcdlgs(String jcdlgs) {
		this.jcdlgs = jcdlgs;
	}
	/**
	 * @return the zhdlgs
	 */
	public String getZhdlgs() {
		return zhdlgs;
	}
	/**
	 * @param zhdlgs要设置的 zhdlgs
	 */
	public void setZhdlgs(String zhdlgs) {
		this.zhdlgs = zhdlgs;
	}
	/**
	 * @return the jfhb
	 */
	public String getJfhb() {
		return jfhb;
	}
	/**
	 * @param jfhb要设置的 jfhb
	 */
	public void setJfhb(String jfhb) {
		this.jfhb = jfhb;
	}
	/**
	 * @return the zc
	 */
	public String getZc() {
		return zc;
	}
	/**
	 * @param zc要设置的 zc
	 */
	public void setZc(String zc) {
		this.zc = zc;
	}

	
}
