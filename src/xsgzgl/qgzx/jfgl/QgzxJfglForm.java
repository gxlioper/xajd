package xsgzgl.qgzx.jfgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xgxt.form.User;
import xsgzgl.comm.form.CommForm;
/**
 * �ڹ���ѧ-�ڹ����ѹ���-������Ϣ����
 * @author yeyipin
 * @since 2012.7.16
 */
public class QgzxJfglForm extends CommForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ExportModel exportModel = new ExportModel();//����
	private User user;
	
	private String pkValue;//����
	private String doType;//����
	private String nd;//���
	private String bmdm;//���Ŵ���
	private String bm;//����
	private String hbsj;//����ʱ��
	private String hbje;//�������
	private String bz;//��ע
	private String xn;//ѧ��
	private String type;//����
	private String xq;//ѧ��
	private String[] xns;//ѧ������
	private String[] xqs;//ѧ������
	//�㽭��ְͨҵ����ѧԺ
	private String sourcemonth;
	private String targetmonth;
	public String[] getXns() {
		return xns;
	}
	public String getSourcemonth() {
		return sourcemonth;
	}
	public void setSourcemonth(String sourcemonth) {
		this.sourcemonth = sourcemonth;
	}
	public String getTargetmonth() {
		return targetmonth;
	}
	public void setTargetmonth(String targetmonth) {
		this.targetmonth = targetmonth;
	}
	public void setXns(String[] xns) {
		this.xns = xns;
	}
	public String[] getXqs() {
		return xqs;
	}
	public void setXqs(String[] xqs) {
		this.xqs = xqs;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}
	public String getHbsj() {
		return hbsj;
	}
	public void setHbsj(String hbsj) {
		this.hbsj = hbsj;
	}
	public String getHbje() {
		return hbje;
	}
	public void setHbje(String hbje) {
		this.hbje = hbje;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getPkValue(){
		return pkValue;
	}
	public void setDoType(String doType) {
		this.doType = doType;
	}
	public String getDoType(){
		return doType;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param typeҪ���õ� type
	 */
	public void setType(String type) {
		this.type = type;
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
	
	
	
}
