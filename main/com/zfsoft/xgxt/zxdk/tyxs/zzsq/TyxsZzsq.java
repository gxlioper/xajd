package com.zfsoft.xgxt.zxdk.tyxs.zzsq;

/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-4-8 ����11:29:07 
 */  



import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.model.SuperAuditModel;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����ѧ����������ģ��
 * @�๦������: �����
 * @���ߣ� ����Ӣ[����:1177]
 * @ʱ�䣺 2015-4-8 ����11:29:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TyxsZzsq extends SuperAuditModel {
	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private String  id ; 
	private String  xh  ;
	private String  xn   ;
	private String  lsgx  ;
	private String  fxjdxlcc ;
	private String  rwqrxsj   ;
	private String  rwsj   ;
	private String  tysj  ;
	private String  fxsj   ;
	private String  fxjdnx ;
	private String  sqxfzj  ;
	private String  dyzzxf  ;
	private String  dezzxf ;
	private String dszzxf ;
	private String  dsizzxf ;
	private String  sqsj  ;
	private String  shzt   ;
	private String  splcid ;
	private String sqly;
	private String nd;
	private String je;
	private String dkbj;
	private String yhdm;
	private String dkhth;
	private String dkkssj;
	private String dkjssj;
	private String dklx;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	//�������
	private FormFile formfile;
	private String filepath;
	
	//�����Ƽ�����Ի�
	private String fistyj;
	private String secondyj;
	private String thirdyj;
	private String fourthyj;
	private String xfyjzj;
	private String sfbb;
	/**
	 * @return the nd
	 */
	public String getNd() {
		return nd;
	}

	/**
	 * @param ndҪ���õ� nd
	 */
	public void setNd(String nd) {
		this.nd = nd;
	}

	/**
	 * @return the je
	 */
	public String getJe() {
		return je;
	}

	/**
	 * @param jeҪ���õ� je
	 */
	public void setJe(String je) {
		this.je = je;
	}

	/**
	 * @return the dkbj
	 */
	public String getDkbj() {
		return dkbj;
	}

	/**
	 * @param dkbjҪ���õ� dkbj
	 */
	public void setDkbj(String dkbj) {
		this.dkbj = dkbj;
	}

	/**
	 * @return the yhdm
	 */
	public String getYhdm() {
		return yhdm;
	}

	/**
	 * @param yhdmҪ���õ� yhdm
	 */
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}

	/**
	 * @return the dkhth
	 */
	public String getDkhth() {
		return dkhth;
	}

	/**
	 * @param dkhthҪ���õ� dkhth
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
	 * @param dkkssjҪ���õ� dkkssj
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
	 * @param dkjssjҪ���õ� dkjssj
	 */
	public void setDkjssj(String dkjssj) {
		this.dkjssj = dkjssj;
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
	 * @param splcidҪ���õ� splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}
	
	/**
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}
	/**
	 * @param sqlyҪ���õ� sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param idҪ���õ� id
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
	 * @param xhҪ���õ� xh
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
	 * @param xnҪ���õ� xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the lsgx
	 */
	public String getLsgx() {
		return lsgx;
	}
	/**
	 * @param lsgxҪ���õ� lsgx
	 */
	public void setLsgx(String lsgx) {
		this.lsgx = lsgx;
	}
	/**
	 * @return the fxjdxlcc
	 */
	public String getFxjdxlcc() {
		return fxjdxlcc;
	}
	/**
	 * @param fxjdxlccҪ���õ� fxjdxlcc
	 */
	public void setFxjdxlcc(String fxjdxlcc) {
		this.fxjdxlcc = fxjdxlcc;
	}
	/**
	 * @return the rwqrxsj
	 */
	public String getRwqrxsj() {
		return rwqrxsj;
	}
	/**
	 * @param rwqrxsjҪ���õ� rwqrxsj
	 */
	public void setRwqrxsj(String rwqrxsj) {
		this.rwqrxsj = rwqrxsj;
	}
	/**
	 * @return the rwsj
	 */
	public String getRwsj() {
		return rwsj;
	}
	/**
	 * @param rwsjҪ���õ� rwsj
	 */
	public void setRwsj(String rwsj) {
		this.rwsj = rwsj;
	}
	/**
	 * @return the tysj
	 */
	public String getTysj() {
		return tysj;
	}
	/**
	 * @param tysjҪ���õ� tysj
	 */
	public void setTysj(String tysj) {
		this.tysj = tysj;
	}
	/**
	 * @return the fxsj
	 */
	public String getFxsj() {
		return fxsj;
	}
	/**
	 * @param fxsjҪ���õ� fxsj
	 */
	public void setFxsj(String fxsj) {
		this.fxsj = fxsj;
	}
	/**
	 * @return the fxjdnx
	 */
	public String getFxjdnx() {
		return fxjdnx;
	}
	/**
	 * @param fxjdnxҪ���õ� fxjdnx
	 */
	public void setFxjdnx(String fxjdnx) {
		this.fxjdnx = fxjdnx;
	}
	/**
	 * @return the sqxfzj
	 */
	public String getSqxfzj() {
		return sqxfzj;
	}
	/**
	 * @param sqxfzjҪ���õ� sqxfzj
	 */
	public void setSqxfzj(String sqxfzj) {
		this.sqxfzj = sqxfzj;
	}
	/**
	 * @return the dyzzxf
	 */
	public String getDyzzxf() {
		return dyzzxf;
	}
	/**
	 * @param dyzzxfҪ���õ� dyzzxf
	 */
	public void setDyzzxf(String dyzzxf) {
		this.dyzzxf = dyzzxf;
	}
	/**
	 * @return the dezzxf
	 */
	public String getDezzxf() {
		return dezzxf;
	}
	/**
	 * @param dezzxfҪ���õ� dezzxf
	 */
	public void setDezzxf(String dezzxf) {
		this.dezzxf = dezzxf;
	}
	/**
	 * @return the dszzxf
	 */
	public String getDszzxf() {
		return dszzxf;
	}
	/**
	 * @param dszzxfҪ���õ� dszzxf
	 */
	public void setDszzxf(String dszzxf) {
		this.dszzxf = dszzxf;
	}
	/**
	 * @return the dsizzxf
	 */
	public String getDsizzxf() {
		return dsizzxf;
	}
	/**
	 * @param dsizzxfҪ���õ� dsizzxf
	 */
	public void setDsizzxf(String dsizzxf) {
		this.dsizzxf = dsizzxf;
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
	 * @return the sqlcid
	 */
	public String getSplcid() {
		return splcid;
	}
	/**
	 * @param sqlcidҪ���õ� sqlcid
	 */
	public void setSqlcid(String splcid) {
		this.splcid = splcid;
	}

	public FormFile getFormfile() {
		return formfile;
	}

	public void setFormfile(FormFile formfile) {
		this.formfile = formfile;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-13 ����11:03:40 
	 * @return		: the fistyj
	 */
	public String getFistyj() {
		return fistyj;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-13 ����11:03:40 
	 * @param 		��fistyj the fistyj to set
	 */
	public void setFistyj(String fistyj) {
		this.fistyj = fistyj;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-13 ����11:03:40 
	 * @return		: the secondyj
	 */
	public String getSecondyj() {
		return secondyj;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-13 ����11:03:40 
	 * @param 		��secondyj the secondyj to set
	 */
	public void setSecondyj(String secondyj) {
		this.secondyj = secondyj;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-13 ����11:03:40 
	 * @return		: the thirdyj
	 */
	public String getThirdyj() {
		return thirdyj;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-13 ����11:03:40 
	 * @param 		��thirdyj the thirdyj to set
	 */
	public void setThirdyj(String thirdyj) {
		this.thirdyj = thirdyj;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-13 ����11:03:40 
	 * @return		: the fourthyj
	 */
	public String getFourthyj() {
		return fourthyj;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-13 ����11:03:40 
	 * @param 		��fourthyj the fourthyj to set
	 */
	public void setFourthyj(String fourthyj) {
		this.fourthyj = fourthyj;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-13 ����11:03:40 
	 * @return		: the xfyjzj
	 */
	public String getXfyjzj() {
		return xfyjzj;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-13 ����11:03:40 
	 * @param 		��xfyjzj the xfyjzj to set
	 */
	public void setXfyjzj(String xfyjzj) {
		this.xfyjzj = xfyjzj;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-13 ����11:03:40 
	 * @return		: the sfbb
	 */
	public String getSfbb() {
		return sfbb;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-13 ����11:03:40 
	 * @param 		��sfbb the sfbb to set
	 */
	public void setSfbb(String sfbb) {
		this.sfbb = sfbb;
	}
	
}
