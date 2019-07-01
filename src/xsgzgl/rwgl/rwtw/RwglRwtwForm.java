package xsgzgl.rwgl.rwtw;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.form.User;
import xsgzgl.comm.form.CommForm;

public class RwglRwtwForm extends CommForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;//
	private String pkValue;//主键
	private String doType;//操作类型
	private String rwsj;//入伍时间
	private String rwd;//入伍地文本框（通用）
	private String rwdwd;//入伍地下拉框（温大）
	private String rwzh;//入伍证号
	private String twsj;//退伍时间
	private String yzy;//原专业
	private String ybj;//原班级
	private String hjgx;//户籍关系
	private String hkszd;//户口所在地
	private String bz;//备注
	private String rwxn;//入伍学年，2013-05-31增加
	private String rwfs;//入伍方式
	
	private String sg;//身高
	private String tz;//体重
	private String zysl;//左眼视力
	private String yysl;//右眼视力
	
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
	public String getDoType() {
		return doType;
	}
	public void setDoType(String doType) {
		this.doType = doType;
	}
	public String getRwsj() {
		return rwsj;
	}
	public void setRwsj(String rwsj) {
		this.rwsj = rwsj;
	}
	/** 
	 * 入伍地文本框（通用）
	 */
	public String getRwd() {
		return rwd;
	}
	/** 
	 * 入伍地文本框（通用）
	 */
	public void setRwd(String rwd) {
		this.rwd = rwd;
	}
	/**
	 * 入伍地下拉框（温大）
	 */
	public String getRwdwd() {
		return rwdwd;
	}
	/**
	 * 入伍地下拉框（温大）
	 */
	public void setRwdwd(String rwdwd) {
		this.rwdwd = rwdwd;
	}
	public String getRwzh() {
		return rwzh;
	}
	public void setRwzh(String rwzh) {
		this.rwzh = rwzh;
	}
	public String getTwsj() {
		return twsj;
	}
	public void setTwsj(String twsj) {
		this.twsj = twsj;
	}
	public String getYzy() {
		return yzy;
	}
	public void setYzy(String yzy) {
		this.yzy = yzy;
	}
	public String getYbj() {
		return ybj;
	}
	public void setYbj(String ybj) {
		this.ybj = ybj;
	}
	public String getHjgx() {
		return hjgx;
	}
	public void setHjgx(String hjgx) {
		this.hjgx = hjgx;
	}
	public String getHkszd() {
		return hkszd;
	}
	public void setHkszd(String hkszd) {
		this.hkszd = hkszd;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getRwxn() {
		return rwxn;
	}
	public void setRwxn(String rwxn) {
		this.rwxn = rwxn;
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
	 * @return the rwfs
	 */
	public String getRwfs() {
		return rwfs;
	}
	/**
	 * @param rwfs要设置的 rwfs
	 */
	public void setRwfs(String rwfs) {
		this.rwfs = rwfs;
	}
	/**
	 * @return the sg
	 */
	public String getSg() {
		return sg;
	}
	/**
	 * @param sg要设置的 sg
	 */
	public void setSg(String sg) {
		this.sg = sg;
	}
	/**
	 * @return the tz
	 */
	public String getTz() {
		return tz;
	}
	/**
	 * @param tz要设置的 tz
	 */
	public void setTz(String tz) {
		this.tz = tz;
	}
	/**
	 * @return the zysl
	 */
	public String getZysl() {
		return zysl;
	}
	/**
	 * @param zysl要设置的 zysl
	 */
	public void setZysl(String zysl) {
		this.zysl = zysl;
	}
	/**
	 * @return the yysl
	 */
	public String getYysl() {
		return yysl;
	}
	/**
	 * @param yysl要设置的 yysl
	 */
	public void setYysl(String yysl) {
		this.yysl = yysl;
	}

}
