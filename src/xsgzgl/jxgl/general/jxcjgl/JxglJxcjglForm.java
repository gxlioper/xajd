package xsgzgl.jxgl.general.jxcjgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.form.User;
import xsgzgl.comm.form.CommForm;


/**
 * ��ѵ����-��ѵ���˹���-��ѵ�ɼ�����
 * @author yeyipin
 * @since 2012.10.13
 */
public class JxglJxcjglForm  extends CommForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private String pkValue;
	private String jzid;//����ID
	private String jxid;//��ѵID
	private String len;//��ѵ�ȼ�����
	private String zd;//�ɼ��ֶ�
	private String zdm;//�ֶ���
	private String zdlx;//�ֶ�����
	private String source_table;//���ݱ�
	private String option_dm;//�����б����
	private String option_mc;//�����б�����
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
	public String getJxid() {
		return jxid;
	}
	public void setJxid(String jxid) {
		this.jxid = jxid;
	}
	public String getJzid() {
		return jzid;
	}
	public void setJzid(String jzid) {
		this.jzid = jzid;
	}
	public String getLen() {
		return len;
	}
	public void setLen(String len) {
		this.len = len;
	}
	public String getZd() {
		return zd;
	}
	public void setZd(String zd) {
		this.zd = zd;
	}
	public String getZdm() {
		return zdm;
	}
	public void setZdm(String zdm) {
		this.zdm = zdm;
	}
	public String getZdlx() {
		return zdlx;
	}
	public void setZdlx(String zdlx) {
		this.zdlx = zdlx;
	}
	public String getSource_table() {
		return source_table;
	}
	public void setSource_table(String sourceTable) {
		source_table = sourceTable;
	}
	public String getOption_dm() {
		return option_dm;
	}
	public void setOption_dm(String optionDm) {
		option_dm = optionDm;
	}
	public String getOption_mc() {
		return option_mc;
	}
	public void setOption_mc(String optionMc) {
		option_mc = optionMc;
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
