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
	private String pkValue;//����
	private String doType;//��������
	private String rwsj;//����ʱ��
	private String rwd;//������ı���ͨ�ã�
	private String rwdwd;//������������´�
	private String rwzh;//����֤��
	private String twsj;//����ʱ��
	private String yzy;//ԭרҵ
	private String ybj;//ԭ�༶
	private String hjgx;//������ϵ
	private String hkszd;//�������ڵ�
	private String bz;//��ע
	private String rwxn;//����ѧ�꣬2013-05-31����
	private String rwfs;//���鷽ʽ
	
	private String sg;//���
	private String tz;//����
	private String zysl;//��������
	private String yysl;//��������
	
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
	 * ������ı���ͨ�ã�
	 */
	public String getRwd() {
		return rwd;
	}
	/** 
	 * ������ı���ͨ�ã�
	 */
	public void setRwd(String rwd) {
		this.rwd = rwd;
	}
	/**
	 * ������������´�
	 */
	public String getRwdwd() {
		return rwdwd;
	}
	/**
	 * ������������´�
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
	 * @param exportModelҪ���õ� exportModel
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
	 * @param rwfsҪ���õ� rwfs
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
	 * @param sgҪ���õ� sg
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
	 * @param tzҪ���õ� tz
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
	 * @param zyslҪ���õ� zysl
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
	 * @param yyslҪ���õ� yysl
	 */
	public void setYysl(String yysl) {
		this.yysl = yysl;
	}

}
