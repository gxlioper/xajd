package xsgzgl.jxgl.general.jxbxgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xsgzgl.comm.form.CommForm;

public class JxglJxbxglForm  extends CommForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private String pkValue;
	private String jxid;//军训ID
	private String bxlb;//表现类别
	private String jtbx;//具体表现
	private String len;//长度
	private String doType;//操作类型
	
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
	public String getBxlb() {
		return bxlb;
	}
	public void setBxlb(String bxlb) {
		this.bxlb = bxlb;
	}
	public String getJtbx() {
		return jtbx;
	}
	public void setJtbx(String jtbx) {
		this.jtbx = jtbx;
	}
	public String getLen() {
		return len;
	}
	public void setLen(String len) {
		this.len = len;
	}
	public String getDoType() {
		return doType;
	}
	public void setDoType(String doType) {
		this.doType = doType;
	}

}
