/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-25 ����11:56:38 
 */  
package xsgzgl.gygl.xyzsgl.sq;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2015-5-25 ����11:56:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XyzsSqForm extends ActionForm {
	 private String sqbh;      
	 private String xh;
	 private String sqsjstart;
	 private String sqsjend;  
	 private String sqsj;        
	 private String shzt;  
	 private String splc;  
	 private String xxdz;       
	 private String lxdh;       
	 private String parentslxdy; 
	 private String xl;        
	 private String zwjzyy;   
	 private String bz;
	 private String xn;
	 private String filepath;
	 private SearchModel searchModel = new SearchModel();
	 private static final long serialVersionUID = 1L;
	 private String type;
	 private ExportModel exportModel = new ExportModel();
	 private Pages pages = new Pages();
	 
	 
	 /**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}

	/**
	 * @param filepathҪ���õ� filepath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	
	/**
	 * @param xnҪ���õ� xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	
	/**
	 * @return the sqbh
	 */
	public String getSqbh() {
		return sqbh;
	}
	
	/**
	 * @param sqbhҪ���õ� sqbh
	 */
	public void setSqbh(String sqbh) {
		this.sqbh = sqbh;
	}
	
	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}
	
	/**
	 * @param xhҪ���õ� xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	
	/**
	 * @return the sqsjstart
	 */
	public String getSqsjstart() {
		return sqsjstart;
	}
	
	/**
	 * @param sqsjstartҪ���õ� sqsjstart
	 */
	public void setSqsjstart(String sqsjstart) {
		this.sqsjstart = sqsjstart;
	}
	
	/**
	 * @return the sqsjend
	 */
	public String getSqsjend() {
		return sqsjend;
	}
	
	/**
	 * @param sqsjendҪ���õ� sqsjend
	 */
	public void setSqsjend(String sqsjend) {
		this.sqsjend = sqsjend;
	}
	
	/**
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	
	/**
	 * @param sqsjҪ���õ� sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	
	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	
	/**
	 * @param shztҪ���õ� shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	
	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	
	/**
	 * @param splcҪ���õ� splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	
	/**
	 * @return the xxdz
	 */
	public String getXxdz() {
		return xxdz;
	}
	
	/**
	 * @param xxdzҪ���õ� xxdz
	 */
	public void setXxdz(String xxdz) {
		this.xxdz = xxdz;
	}
	
	/**
	 * @return the lxdh
	 */
	public String getLxdh() {
		return lxdh;
	}
	
	/**
	 * @param lxdhҪ���õ� lxdh
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	
	/**
	 * @return the parentslxdy
	 */
	public String getParentslxdy() {
		return parentslxdy;
	}
	
	/**
	 * @param parentslxdyҪ���õ� parentslxdy
	 */
	public void setParentslxdy(String parentslxdy) {
		this.parentslxdy = parentslxdy;
	}
	
	/**
	 * @return the xl
	 */
	public String getXl() {
		return xl;
	}
	
	/**
	 * @param xlҪ���õ� xl
	 */
	public void setXl(String xl) {
		this.xl = xl;
	}
	
	/**
	 * @return the zwjzyy
	 */
	public String getZwjzyy() {
		return zwjzyy;
	}
	
	/**
	 * @param zwjzyyҪ���õ� zwjzyy
	 */
	public void setZwjzyy(String zwjzyy) {
		this.zwjzyy = zwjzyy;
	}
	
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	
	/**
	 * @param bzҪ���õ� bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	/**
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	
	/**
	 * @param searchModelҪ���õ� searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
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
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}
	
	/**
	 * @param pagesҪ���õ� pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
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
