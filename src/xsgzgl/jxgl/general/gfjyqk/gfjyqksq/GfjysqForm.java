/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package xsgzgl.jxgl.general.gfjyqk.gfjyqksq;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class GfjysqForm  extends ActionForm{
	private Pages pages = new Pages();
	// �߼���ѯ
	SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();

	private String type;
	
	private String xh;
	private String xn;
	private String xq;
	
	private String sqid;
	private String sqsj;
	private String gfqkfl;
	private String bydjsj;
	private String bydjdd;
	private String cjrwsj;
	
	private String rwpzdw;
	private String twfxsj;
	private String twpzdw;
	private String pjpysj;
	private String pjpydw;
	private String jcsj;	
	
	private String jcdw;
	private String cjhdsj;
	private String cjhddd;
	private String shzt;
	private String filepath;
	private String splc;
	private String bz;
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @return		: the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @param 		��pages the pages to set
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @return		: the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @param 		��searchModel the searchModel to set
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @return		: the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @param 		��type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @return		: the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @param 		��xh the xh to set
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @return		: the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @param 		��xn the xn to set
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @return		: the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @param 		��xq the xq to set
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @return		: the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @param 		��sqid the sqid to set
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @return		: the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @param 		��sqsj the sqsj to set
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @return		: the gfqkfl
	 */
	public String getGfqkfl() {
		return gfqkfl;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @param 		��gfqkfl the gfqkfl to set
	 */
	public void setGfqkfl(String gfqkfl) {
		this.gfqkfl = gfqkfl;
	}
	
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-3 ����08:48:37 
	 * @return		: the bydjsj
	 */
	public String getBydjsj() {
		return bydjsj;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-3 ����08:48:37 
	 * @param 		��bydjsj the bydjsj to set
	 */
	public void setBydjsj(String bydjsj) {
		this.bydjsj = bydjsj;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-3 ����08:48:37 
	 * @return		: the bydjdd
	 */
	public String getBydjdd() {
		return bydjdd;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-3 ����08:48:37 
	 * @param 		��bydjdd the bydjdd to set
	 */
	public void setBydjdd(String bydjdd) {
		this.bydjdd = bydjdd;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @return		: the cjrwsj
	 */
	public String getCjrwsj() {
		return cjrwsj;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @param 		��cjrwsj the cjrwsj to set
	 */
	public void setCjrwsj(String cjrwsj) {
		this.cjrwsj = cjrwsj;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @return		: the rwpzdw
	 */
	public String getRwpzdw() {
		return rwpzdw;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @param 		��rwpzdw the rwpzdw to set
	 */
	public void setRwpzdw(String rwpzdw) {
		this.rwpzdw = rwpzdw;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @return		: the twfxsj
	 */
	public String getTwfxsj() {
		return twfxsj;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @param 		��twfxsj the twfxsj to set
	 */
	public void setTwfxsj(String twfxsj) {
		this.twfxsj = twfxsj;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @return		: the twpzdw
	 */
	public String getTwpzdw() {
		return twpzdw;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @param 		��twpzdw the twpzdw to set
	 */
	public void setTwpzdw(String twpzdw) {
		this.twpzdw = twpzdw;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @return		: the pjpysj
	 */
	public String getPjpysj() {
		return pjpysj;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @param 		��pjpysj the pjpysj to set
	 */
	public void setPjpysj(String pjpysj) {
		this.pjpysj = pjpysj;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @return		: the pjpydw
	 */
	public String getPjpydw() {
		return pjpydw;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @param 		��pjpydw the pjpydw to set
	 */
	public void setPjpydw(String pjpydw) {
		this.pjpydw = pjpydw;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @return		: the jcsj
	 */
	public String getJcsj() {
		return jcsj;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @param 		��jcsj the jcsj to set
	 */
	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @return		: the jcdw
	 */
	public String getJcdw() {
		return jcdw;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @param 		��jcdw the jcdw to set
	 */
	public void setJcdw(String jcdw) {
		this.jcdw = jcdw;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @return		: the cjhdsj
	 */
	public String getCjhdsj() {
		return cjhdsj;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @param 		��cjhdsj the cjhdsj to set
	 */
	public void setCjhdsj(String cjhdsj) {
		this.cjhdsj = cjhdsj;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @return		: the cjhddd
	 */
	public String getCjhddd() {
		return cjhddd;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @param 		��cjhddd the cjhddd to set
	 */
	public void setCjhddd(String cjhddd) {
		this.cjhddd = cjhddd;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @return		: the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @param 		��shzt the shzt to set
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @return		: the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @param 		��filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @return		: the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @param 		��splc the splc to set
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @return		: the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-2 ����03:34:54 
	 * @param 		��bz the bz to set
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-4 ����03:11:34 
	 * @return		: the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-4 ����03:11:34 
	 * @param 		��exportModel the exportModel to set
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	
	
}
