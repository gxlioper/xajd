package xsgzgl.qgzx.gwgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.form.User;
import xsgzgl.comm.form.CommForm;
/**
 * �ڹ���ѧ-�ڹ���λ����-��λ��Ϣ����
 * @author yeyipin
 * @since 2012.7.17
 */
public class QgzxGwglForm extends CommForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private String pkValue;//����
	private String xn;//ѧ��
	private String gwxh;//��λ���
	private String yrbm;//���˲���
	private String gwdm;//��λ����
	private String gwmc;//��λ����
	private String gwxzdm;//��λ����
	private String xqrs;//��������
	private String knsrs;//����������
	private String fknsrs;//����ķ�����������
	private String gwms;//��λ����
	private String gwryyq;//��λ��ԱҪ��
	private String bz;//��ע
	private String gwyqryxg; //��λԤ����ԱЧ��
	
	private String doType;//��������
	private String zgzt;//�ڸ�״̬
	private String sgsj;//�ϸ�ʱ��
	private String tgsj;//�˸�ʱ��
	private String tgyy;//�˸�ԭ��
	private String shzt;//���״̬
	private String shyj;//������
	private String sqbhs;//������
	
	private String gwcjsx;//��λ�������
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
	 * @param exportModelҪ���õ� exportModel
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
	 * @param sqbhsҪ���õ� sqbhs
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
	 * @param gwcjsxҪ���õ� gwcjsx
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
	 * @param gwxhҪ���õ� gwxh
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
	 * @param gwyqryxgҪ���õ� gwyqryxg
	 */
	public void setGwyqryxg(String gwyqryxg) {
		this.gwyqryxg = gwyqryxg;
	}
	
}
