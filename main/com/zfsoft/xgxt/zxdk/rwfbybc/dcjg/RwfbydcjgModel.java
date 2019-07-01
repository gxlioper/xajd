/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-9-7 ����04:59:20 
 */
package com.zfsoft.xgxt.zxdk.rwfbybc.dcjg;

import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ���������-�������
 * @���ߣ� ChenQ[����:856]
 * @ʱ�䣺 2015-9-7 ����04:59:20
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class RwfbydcjgModel extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String id;
	private String xh;
	private String xn;
	private String yjxf;
	private String yjxfmc;
	private String yhdm;
	private String yhmc;
	private String dkhth;
	private String dkkssj;
	private String dkjssj;
	private String dclb;
	private String dclbmc;
	private String filepath;
	private String xfje;
	private String dkbj;
	private String dcje;
	private String sqsj;
    private String sjly;
    /*��ʦ����Ի�*/
    private String dklx;
    
    /*�㽭��ѧ���Ի�*/
    private String rwqsfsqdc; //����ǰ�Ƿ���������۴���
    private String rwnf;      //�������
    private String twnf;      //�������
    private FormFile importFile; //���Ի������ļ�
    
	//����������Ŵ���List
    private List<DcjgffcsModel> ffcsList = new AutoArrayList(DcjgffcsModel.class);

	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
    
	//�����Ƽ���ѧ���Ի��ֶ�
	private String rwqxl;
	private String rwqyd;
	private String rxrq;
	private String rwsj;
	private String xfbc;
	private String sfbb;
	private String bz;
	
	 /**
	 * @return the importFile
	 */
	public FormFile getImportFile() {
		return importFile;
	}

	/**
	 * @param importFileҪ���õ� importFile
	 */
	public void setImportFile(FormFile importFile) {
		this.importFile = importFile;
	}
	 
	/**
	 * @return the dklx
	 */
	public String getDklx() {
		return dklx;
	}

	/**
	 * @param dklxҪ���õ� dklx
	 */
	public void setDklx(String dklx) {
		this.dklx = dklx;
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param idҪ���õ�
	 *            id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}

	/**
	 * @param xhҪ���õ�
	 *            xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}

	/**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}

	/**
	 * @param xnҪ���õ�
	 *            xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}

	/**
	 * @return the yjxf
	 */
	public String getYjxf() {
		return yjxf;
	}

	/**
	 * @param yjxfҪ���õ�
	 *            yjxf
	 */
	public void setYjxf(String yjxf) {
		this.yjxf = yjxf;
	}

	/**
	 * @return the yjxfmc
	 */
	public String getYjxfmc() {
		return yjxfmc;
	}

	/**
	 * @param yjxfmcҪ���õ�
	 *            yjxfmc
	 */
	public void setYjxfmc(String yjxfmc) {
		this.yjxfmc = yjxfmc;
	}

	/**
	 * @return the yhdm
	 */
	public String getYhdm() {
		return yhdm;
	}

	/**
	 * @param yhdmҪ���õ�
	 *            yhdm
	 */
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}

	/**
	 * @return the yhmc
	 */
	public String getYhmc() {
		return yhmc;
	}

	/**
	 * @param yhmcҪ���õ�
	 *            yhmc
	 */
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	/**
	 * @return the dkhth
	 */
	public String getDkhth() {
		return dkhth;
	}

	/**
	 * @param dkhthҪ���õ�
	 *            dkhth
	 */
	public void setDkhth(String dkhth) {
		this.dkhth = dkhth;
	}

	/**
	 * @return the dkkssj
	 */
	public String getDkkssj() {
		return dkkssj;
	}

	/**
	 * @param dkkssjҪ���õ�
	 *            dkkssj
	 */
	public void setDkkssj(String dkkssj) {
		this.dkkssj = dkkssj;
	}

	/**
	 * @return the dkjssj
	 */
	public String getDkjssj() {
		return dkjssj;
	}

	/**
	 * @param dkjssjҪ���õ�
	 *            dkjssj
	 */
	public void setDkjssj(String dkjssj) {
		this.dkjssj = dkjssj;
	}

	/**
	 * @return the dclb
	 */
	public String getDclb() {
		return dclb;
	}

	/**
	 * @param dclbҪ���õ�
	 *            dclb
	 */
	public void setDclb(String dclb) {
		this.dclb = dclb;
	}

	/**
	 * @return the dclbmc
	 */
	public String getDclbmc() {
		return dclbmc;
	}

	/**
	 * @param dclbmcҪ���õ�
	 *            dclbmc
	 */
	public void setDclbmc(String dclbmc) {
		this.dclbmc = dclbmc;
	}

	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}

	/**
	 * @param filepathҪ���õ�
	 *            filepath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * @return the xfje
	 */
	public String getXfje() {
		return xfje;
	}

	/**
	 * @param xfjeҪ���õ�
	 *            xfje
	 */
	public void setXfje(String xfje) {
		this.xfje = xfje;
	}

	/**
	 * @return the dkbj
	 */
	public String getDkbj() {
		return dkbj;
	}

	/**
	 * @param dkbjҪ���õ�
	 *            dkbj
	 */
	public void setDkbj(String dkbj) {
		this.dkbj = dkbj;
	}

	/**
	 * @return the dcje
	 */
	public String getDcje() {
		return dcje;
	}

	/**
	 * @param dcjeҪ���õ�
	 *            dcje
	 */
	public void setDcje(String dcje) {
		this.dcje = dcje;
	}


	/**
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}

	/**
	 * @param sqsjҪ���õ�
	 *            sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	/**
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}

	/**
	 * @param sjlyҪ���õ� sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	/**
	 * @return the rwqsfsqdc
	 */
	public String getRwqsfsqdc() {
		return rwqsfsqdc;
	}

	/**
	 * @param rwqsfsqdcҪ���õ� rwqsfsqdc
	 */
	public void setRwqsfsqdc(String rwqsfsqdc) {
		this.rwqsfsqdc = rwqsfsqdc;
	}

	/**
	 * @return the rwnf
	 */
	public String getRwnf() {
		return rwnf;
	}

	/**
	 * @param rwnfҪ���õ� rwnf
	 */
	public void setRwnf(String rwnf) {
		this.rwnf = rwnf;
	}

	/**
	 * @return the twnf
	 */
	public String getTwnf() {
		return twnf;
	}

	/**
	 * @param twnfҪ���õ� twnf
	 */
	public void setTwnf(String twnf) {
		this.twnf = twnf;
	}

	/**
	 * @return the ffcsList
	 */
	public List<DcjgffcsModel> getFfcsList() {
		return ffcsList;
	}

	/**
	 * @param ffcsListҪ���õ� ffcsList
	 */
	public void setFfcsList(List<DcjgffcsModel> ffcsList) {
		this.ffcsList = ffcsList;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-17 ����09:31:21 
	 * @return		: the rwqxl
	 */
	public String getRwqxl() {
		return rwqxl;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-17 ����09:31:21 
	 * @param 		��rwqxl the rwqxl to set
	 */
	public void setRwqxl(String rwqxl) {
		this.rwqxl = rwqxl;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-17 ����09:31:21 
	 * @return		: the rwqyd
	 */
	public String getRwqyd() {
		return rwqyd;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-17 ����09:31:21 
	 * @param 		��rwqyd the rwqyd to set
	 */
	public void setRwqyd(String rwqyd) {
		this.rwqyd = rwqyd;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-17 ����09:31:21 
	 * @return		: the rxrq
	 */
	public String getRxrq() {
		return rxrq;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-17 ����09:31:21 
	 * @param 		��rxrq the rxrq to set
	 */
	public void setRxrq(String rxrq) {
		this.rxrq = rxrq;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-17 ����09:31:21 
	 * @return		: the rwsj
	 */
	public String getRwsj() {
		return rwsj;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-17 ����09:31:21 
	 * @param 		��rwsj the rwsj to set
	 */
	public void setRwsj(String rwsj) {
		this.rwsj = rwsj;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-17 ����09:31:21 
	 * @return		: the xfbc
	 */
	public String getXfbc() {
		return xfbc;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-17 ����09:31:21 
	 * @param 		��xfbc the xfbc to set
	 */
	public void setXfbc(String xfbc) {
		this.xfbc = xfbc;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-17 ����09:31:21 
	 * @return		: the sfbb
	 */
	public String getSfbb() {
		return sfbb;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-17 ����09:31:21 
	 * @param 		��sfbb the sfbb to set
	 */
	public void setSfbb(String sfbb) {
		this.sfbb = sfbb;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-17 ����09:31:21 
	 * @return		: the bz
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-17 ����09:31:21 
	 * @param 		��bz the bz to set
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	
}
