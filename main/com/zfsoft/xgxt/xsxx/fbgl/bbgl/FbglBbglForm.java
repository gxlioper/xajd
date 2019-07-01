/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-2-17 ����09:44:17 
 */
package com.zfsoft.xgxt.xsxx.fbgl.bbgl;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-2-17 ����09:44:17
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglBbglForm extends ActionForm {
	private static final long serialVersionUID = -4479217565687401435L;
	private String bjdm;// varchar2(20) n �༶����
	private String zydm;// varchar2(8) y רҵ����
	private String bmdm;// varchar2(10) y ���Ŵ���
	private String bjmc;// varchar2(64) y �༶����
	private String nj;// number n �꼶
	private String bjbh;// number y �༶���
	private String sfytj;// varchar2(1) y �Ƿ����ύ
	private String bjrssx;// number y �༶��������
	private String pzgzid;
	private String pycc;// varchar2(64) y �������
	private String xz;// varchar2(4) y ѧ��
	private String isNewLsh;

	private String pk;
	private String xydm;
	private Map<String, String> rowData = new HashMap<String, String>();
	private String type;
	private String bbzt;//���״̬
	private String fbzt;//�ְ�״̬
	private String xhzt;//ѧ��״̬
	private String zymc;
	private String bmmc;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//ҳ�����ӻ�ȡ��һ����Ӧ���� ƫ��������һ�� ƫ����Ϊ1��
	private String skewing="0";
	public void clean() {
		this.bjdm = "";
		this.zydm = "";
		this.bmdm = "";
		this.nj = "";
		this.pycc = "";
		this.xz = "";
		this.bjrssx="";
		this.bmmc="";
		this.zymc="";
		this.isNewLsh="";
	}
	/**
	 * 
	 * @����: ͨ��pk��ȡ��Ӧpk���ֵ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-5 ����05:24:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String,String> getObject(String pk){
		Map<String,String> map=new HashMap<String, String>();
		if(pk.indexOf("-")!=-1){
			String object[]=pk.split("-");
			map.put("nj",object[0]);
			map.put("bmdm", object[1]);
			map.put("zydm", object[2]);
			map.put("pycc", object[3]);
			map.put("xz", object[4]);
		}
		return map;
	}
	/**
	 * @return the pzgzid
	 */
	public String getPzgzid() {
		return pzgzid;
	}

	/**
	 * @param pzgzidҪ���õ�
	 *            pzgzid
	 */
	public void setPzgzid(String pzgzid) {
		this.pzgzid = pzgzid;
	}

	/**
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}

	/**
	 * @param bjdmҪ���õ�
	 *            bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	/**
	 * @return the zydm
	 */
	public String getZydm() {
		return zydm;
	}

	/**
	 * @param zydmҪ���õ�
	 *            zydm
	 */
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	/**
	 * @return the bmdm
	 */
	public String getBmdm() {
		if (StringUtils.isNull(bmdm)) {
			bmdm = this.getXydm();
		}
		return bmdm;
	}

	/**
	 * @param bmdmҪ���õ�
	 *            bmdm
	 */
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	/**
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}

	/**
	 * @param bjmcҪ���õ�
	 *            bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	/**
	 * @return the nj
	 */
	public String getNj() {
		return nj;
	}

	/**
	 * @param njҪ���õ�
	 *            nj
	 */
	public void setNj(String nj) {
		this.nj = nj;
	}

	/**
	 * @return the bjbh
	 */
	public String getBjbh() {
		return bjbh;
	}

	/**
	 * @param bjbhҪ���õ�
	 *            bjbh
	 */
	public void setBjbh(String bjbh) {
		this.bjbh = bjbh;
	}

	/**
	 * @return the sfytj
	 */
	public String getSfytj() {
		return sfytj;
	}

	/**
	 * @param sfytjҪ���õ�
	 *            sfytj
	 */
	public void setSfytj(String sfytj) {
		this.sfytj = sfytj;
	}

	/**
	 * @return the bjrssx
	 */
	public String getBjrssx() {
		if (StringUtils.isNull(bjrssx)) {
			bjrssx = rowData.get("rssx");
		}
		return bjrssx;
	}

	/**
	 * @param bjrssxҪ���õ�
	 *            bjrssx
	 */
	public void setBjrssx(String bjrssx) {
		if("null".equals(bjrssx)||"������".equals(bjrssx)){
			bjrssx=null;
		}
		this.bjrssx = bjrssx;
	}

	/**
	 * @return the rowData
	 */
	public Map<String, String> getRowData() {
		return rowData;
	}

	/**
	 * @param rowDataҪ���õ�
	 *            rowData
	 */
	public void setRowData(Map<String, String> rowData) {
		this.rowData = rowData;
	}

	/**
	 * @return the pk
	 */
	public String getPk() {
		return pk;
	}

	/**
	 * @param pkҪ���õ�
	 *            pk
	 */
	public void setPk(String pk) {
		if(StringUtils.isNotNull(pk)){
			Map<String,String> map=getObject(pk);
			if(map.size()>0){
				setNj(map.get("nj"));
				setBmdm(map.get("bmdm"));
				setPycc(map.get("pycc"));
				setXz(map.get("xz"));
				setZydm(map.get("zydm"));
			}
		}
		this.pk = pk;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param typeҪ���õ�
	 *            type
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
	 * @param pagesҪ���õ�
	 *            pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}

	/**
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}

	/**
	 * @param searchModelҪ���õ�
	 *            searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	/**
	 * @return the xydm
	 */
	public String getXydm() {
		return xydm;
	}

	/**
	 * @param xydmҪ���õ�
	 *            xydm
	 */
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	/**
	 * @return the bbzt
	 */
	public String getBbzt() {
		return bbzt;
	}

	/**
	 * @param bbztҪ���õ�
	 *            bbzt
	 */
	public void setBbzt(String bbzt) {
		this.bbzt = bbzt;
	}

	/**
	 * @return the pycc
	 */
	public String getPycc() {
		return pycc;
	}

	/**
	 * @param pyccҪ���õ�
	 *            pycc
	 */
	public void setPycc(String pycc) {
		this.pycc = pycc;
	}

	/**
	 * @return the xz
	 */
	public String getXz() {
		return xz;
	}

	/**
	 * @param xzҪ���õ�
	 *            xz
	 */
	public void setXz(String xz) {
		this.xz = xz;
	}

	/**
	 * @return the skewing
	 */
	public String getSkewing() {
		return skewing;
	}

	/**
	 * @param skewingҪ���õ� skewing
	 */
	public void setSkewing(String skewing) {
		this.skewing = skewing;
	}
	/**
	 * @return the fbzt
	 */
	public String getFbzt() {
		return fbzt;
	}
	/**
	 * @param fbztҪ���õ� fbzt
	 */
	public void setFbzt(String fbzt) {
		this.fbzt = fbzt;
	}
	/**
	 * @return the xhzt
	 */
	public String getXhzt() {
		return xhzt;
	}
	/**
	 * @param xhztҪ���õ� xhzt
	 */
	public void setXhzt(String xhzt) {
		this.xhzt = xhzt;
	}
	/**
	 * @return the zymc
	 */
	public String getZymc() {
		return zymc;
	}
	/**
	 * @param zymcҪ���õ� zymc
	 */
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	/**
	 * @return the bmmc
	 */
	public String getBmmc() {
		return bmmc;
	}
	/**
	 * @param bmmcҪ���õ� bmmc
	 */
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	public String getIsNewLsh() {
		return isNewLsh;
	}
	public void setIsNewLsh(String isNewLsh) {
		this.isNewLsh = isNewLsh;
	}
	
}
