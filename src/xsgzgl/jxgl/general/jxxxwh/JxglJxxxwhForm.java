package xsgzgl.jxgl.general.jxxxwh;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xsgzgl.comm.form.CommForm;

/**
 * ��ѵ����-������Ϣά��-��ѵ��Ϣά��
 * @author yeyipin
 * @since 2012.10.10
 */
public class JxglJxxxwhForm extends CommForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private String pkValue;
	private String jxid;//��ѵID
	private String jxmc;//��ѵ����
	private String kssj;//��ʼʱ��
	private String jssj;//����ʱ��
	private String cjnj;//�μ��꼶
	private String jxzt;//��ѵ״̬
	private String sfhx;//�Ƿ�ѵ
	private String sfmx;//�Ƿ���ѵ
	private String cxqk;//��ѵ���
	private String ly;  //����
	private String fj;  //����
	private String type;
	private String cxz;
	
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
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
	public String getJxmc() {
		return jxmc;
	}
	public void setJxmc(String jxmc) {
		this.jxmc = jxmc;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getCjnj() {
		return cjnj;
	}
	public void setCjnj(String cjnj) {
		this.cjnj = cjnj;
	}
	public String getJxzt() {
		return jxzt;
	}
	public void setJxzt(String jxzt) {
		this.jxzt = jxzt;
	}
	public String getSfhx() {
		return sfhx;
	}
	public void setSfhx(String sfhx) {
		this.sfhx = sfhx;
	}
	public String getSfmx() {
		return sfmx;
	}
	public void setSfmx(String sfmx) {
		this.sfmx = sfmx;
	}
	public String getCxqk() {
		return cxqk;
	}
	public void setCxqk(String cxqk) {
		this.cxqk = cxqk;
	}
	public String getLy() {
		return ly;
	}
	public void setLy(String ly) {
		this.ly = ly;
	}
	public String getFj() {
		return fj;
	}
	public void setFj(String fj) {
		this.fj = fj;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCxz() {
		return cxz;
	}
	public void setCxz(String cxz) {
		this.cxz = cxz;
	}
}
