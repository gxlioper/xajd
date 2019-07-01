package xsgzgl.jxgl.general.jxxxwh;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xsgzgl.comm.form.CommForm;

/**
 * 军训管理-基础信息维护-军训信息维护
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
	private String jxid;//军训ID
	private String jxmc;//军训名称
	private String kssj;//开始时间
	private String jssj;//结束时间
	private String cjnj;//参加年级
	private String jxzt;//军训状态
	private String sfhx;//是否缓训
	private String sfmx;//是否免训
	private String cxqk;//参训情况
	private String ly;  //理由
	private String fj;  //附件
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
