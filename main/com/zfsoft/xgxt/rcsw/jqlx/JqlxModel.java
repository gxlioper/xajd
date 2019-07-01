/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-30 ����10:26:16 
 */
package com.zfsoft.xgxt.rcsw.jqlx;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����-������У
 * @�๦������: ������Уʵ��
 * @���ߣ� 945
 * @ʱ�䣺 2013-12-30 ����10:26:16
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JqlxModel extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();

	private String sqid;// ����id
	private String xh;// ������
	private String sqsj;// ����ʱ��
	private String rzdz;// ��ס��ַ
	private String sqly;// ��������
	private String sqzt;// ����״̬
	private String lcid;// ����id
	private String lxkssj;// ��У��ʼʱ��
	private String lxjzsj;// ��У��ֹʱ��
	private String xn;// ѧ��
	private String xq;// ѧ��
	private String sjlx;// ��������0��ֱ��¼�룬1��������

	// for query
	private String xqmc;// ѧ������
	private String sjlymc;// ������Դ����

	// for check
	private String shzt;// ��˹��̵�״̬
	private String shid;// ID
	private String ywid;// ҵ��ID
	private String shr;// �����
	private String shsj;// ���ʱ��
	private String shyj;// ������
	private String gwid;// ��˸�λ
	private String shjg;// ��˽��
	private String thgw;// �˻ظ�λ
	private String fjxx;// ������Ϣ
	private String sfcnyf;// �Ƿ����ҹ��
	private String jzxm;// �ҳ�����
	private String jzlxdh;// �ҳ���ϵ�绰
	private String sflxgn;// �Ƿ���У����
	private String bz;// ��ע
	private String sfsyqzsw;//�Ƿ�ʳ������ʳ��
	private String yzqs;//ԭס����
	private String cwxx;//��λ��Ϣ
	private String lddm;//
	private String ldmc;
	private String qsh;//
	private String cwh;//
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private String[] lcids;

	private FormFile impFilePath;//�����ļ�
	
	//����ѧԺ���Ի��ֶ�
	private String lxkssj2;//��У��ʼʱ��2
	private String lxjzsj2;//��У��ֹʱ��2
	private String lxyy;//��Уԭ��
	private String lxyymc;//��Уԭ������
	private String dwlxr;//��ϵ��
	private String dwlxdh;//��ϵ�绰
	private String lxdw;//��ϵ��λ
	private String lsxq;//����У��--�㽭��ý���Ի�
	private String xxxqmc;//����У��У������--�㽭��ý���Ի�
	
	// �´���Ի��ֶ�
	private String lxsqlxdm; //��У�������ʹ���
	private String lxsqlxmc; //��У������������
	
	//�㽭��ҽҩ��ѧ���Ի�
	private String yqmc;
	private String lxxqmc;
	private String lxldmc;
	private String lxqs;
	private String lxxq;
	private String lxld;
	private String sfgcj;
	private String sqlxtj;
	
	
	/**
	 * @return the xxxqmc
	 */
	public String getXxxqmc() {
		return xxxqmc;
	}

	/**
	 * @param xxxqmcҪ���õ� xxxqmc
	 */
	public void setXxxqmc(String xxxqmc) {
		this.xxxqmc = xxxqmc;
	}

	/**
	 * @return the lsxq
	 */
	public String getLsxq() {
		return lsxq;
	}

	/**
	 * @param lsxqҪ���õ� lsxq
	 */
	public void setLsxq(String lsxq) {
		this.lsxq = lsxq;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

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

	public String getSqid() {
		return sqid;
	}

	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getRzdz() {
		return rzdz;
	}

	public void setRzdz(String rzdz) {
		this.rzdz = rzdz;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getSqzt() {
		return sqzt;
	}

	public void setSqzt(String sqzt) {
		this.sqzt = sqzt;
	}

	public String getLcid() {
		return lcid;
	}

	public void setLcid(String lcid) {
		this.lcid = lcid;
	}

	public String getLxkssj() {
		return lxkssj;
	}

	public void setLxkssj(String lxkssj) {
		this.lxkssj = lxkssj;
	}

	public String getLxjzsj() {
		return lxjzsj;
	}

	public void setLxjzsj(String lxjzsj) {
		this.lxjzsj = lxjzsj;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getSjlx() {
		return sjlx;
	}

	public void setSjlx(String sjlx) {
		this.sjlx = sjlx;
	}

	public String getXqmc() {
		return xqmc;
	}

	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getShid() {
		return shid;
	}

	public void setShid(String shid) {
		this.shid = shid;
	}

	public String getYwid() {
		return ywid;
	}

	public void setYwid(String ywid) {
		this.ywid = ywid;
	}

	public String getShr() {
		return shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

	public String getShsj() {
		return shsj;
	}

	public void setShsj(String shsj) {
		this.shsj = shsj;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String getGwid() {
		return gwid;
	}

	public void setGwid(String gwid) {
		this.gwid = gwid;
	}

	public String getShjg() {
		return shjg;
	}

	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	public String getThgw() {
		return thgw;
	}

	public void setThgw(String thgw) {
		this.thgw = thgw;
	}

	public String getSjlymc() {
		return sjlymc;
	}

	public void setSjlymc(String sjlymc) {
		this.sjlymc = sjlymc;
	}

	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}

	public String[] getGwids() {
		return gwids;
	}

	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}

	public String[] getXhs() {
		return xhs;
	}

	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}

	public String[] getLcids() {
		return lcids;
	}

	public void setLcids(String[] lcids) {
		this.lcids = lcids;
	}

	public String getFjxx() {
		return fjxx;
	}

	public void setFjxx(String fjxx) {
		this.fjxx = fjxx;
	}


	public String getSfcnyf() {
		return sfcnyf;
	}

	public void setSfcnyf(String sfcnyf) {
		this.sfcnyf = sfcnyf;
	}

	public String getJzxm() {
		return jzxm;
	}

	public void setJzxm(String jzxm) {
		this.jzxm = jzxm;
	}

	public String getJzlxdh() {
		return jzlxdh;
	}

	public void setJzlxdh(String jzlxdh) {
		this.jzlxdh = jzlxdh;
	}

	public String getSflxgn() {
		return sflxgn;
	}

	public void setSflxgn(String sflxgn) {
		this.sflxgn = sflxgn;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	/**
	 * @return the sfsyqzsw
	 */
	public String getSfsyqzsw() {
		return sfsyqzsw;
	}

	/**
	 * @param sfsyqzswҪ���õ� sfsyqzsw
	 */
	public void setSfsyqzsw(String sfsyqzsw) {
		this.sfsyqzsw = sfsyqzsw;
	}

	/**
	 * @return the yzqs
	 */
	public String getYzqs() {
		return yzqs;
	}

	/**
	 * @param yzqsҪ���õ� yzqs
	 */
	public void setYzqs(String yzqs) {
		this.yzqs = yzqs;
	}

	/**
	 * @return the cwxx
	 */
	public String getCwxx() {
		return cwxx;
	}

	/**
	 * @param cwxxҪ���õ� cwxx
	 */
	public void setCwxx(String cwxx) {
		this.cwxx = cwxx;
	}

	/**
	 * @return the lddm
	 */
	public String getLddm() {
		return lddm;
	}

	/**
	 * @param lddmҪ���õ� lddm
	 */
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}

	public String getLdmc() {
		return ldmc;
	}

	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}

	/**
	 * @return the qsh
	 */
	public String getQsh() {
		return qsh;
	}

	/**
	 * @param qshҪ���õ� qsh
	 */
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}

	/**
	 * @return the cwh
	 */
	public String getCwh() {
		return cwh;
	}

	/**
	 * @param cwhҪ���õ� cwh
	 */
	public void setCwh(String cwh) {
		this.cwh = cwh;
	}

	/**
	 * @return the impFilePath
	 */
	public FormFile getImpFilePath() {
		return impFilePath;
	}

	/**
	 * @param impFilePathҪ���õ� impFilePath
	 */
	public void setImpFilePath(FormFile impFilePath) {
		this.impFilePath = impFilePath;
	}

	/**
	 * @return the lxkssj2
	 */
	public String getLxkssj2() {
		return lxkssj2;
	}

	/**
	 * @param lxkssj2Ҫ���õ� lxkssj2
	 */
	public void setLxkssj2(String lxkssj2) {
		this.lxkssj2 = lxkssj2;
	}

	/**
	 * @return the lxjzsj2
	 */
	public String getLxjzsj2() {
		return lxjzsj2;
	}

	/**
	 * @param lxjzsj2Ҫ���õ� lxjzsj2
	 */
	public void setLxjzsj2(String lxjzsj2) {
		this.lxjzsj2 = lxjzsj2;
	}

	/**
	 * @return the lxyy
	 */
	public String getLxyy() {
		return lxyy;
	}

	/**
	 * @param lxyyҪ���õ� lxyy
	 */
	public void setLxyy(String lxyy) {
		this.lxyy = lxyy;
	}

	/**
	 * @return the dwlxr
	 */
	public String getDwlxr() {
		return dwlxr;
	}

	/**
	 * @param dwlxrҪ���õ� dwlxr
	 */
	public void setDwlxr(String dwlxr) {
		this.dwlxr = dwlxr;
	}

	/**
	 * @return the dwlxdh
	 */
	public String getDwlxdh() {
		return dwlxdh;
	}

	/**
	 * @param dwlxdhҪ���õ� dwlxdh
	 */
	public void setDwlxdh(String dwlxdh) {
		this.dwlxdh = dwlxdh;
	}

	/**
	 * @return the lxdw
	 */
	public String getLxdw() {
		return lxdw;
	}

	/**
	 * @param lxdwҪ���õ� lxdw
	 */
	public void setLxdw(String lxdw) {
		this.lxdw = lxdw;
	}

	/**
	 * @return the lxyymc
	 */
	public String getLxyymc() {
		return lxyymc;
	}

	/**
	 * @param lxyymcҪ���õ� lxyymc
	 */
	public void setLxyymc(String lxyymc) {
		this.lxyymc = lxyymc;
	}

	public String getLxsqlxdm() {
		return lxsqlxdm;
	}

	public void setLxsqlxdm(String lxsqlxdm) {
		this.lxsqlxdm = lxsqlxdm;
	}

	public String getLxsqlxmc() {
		return lxsqlxmc;
	}

	public void setLxsqlxmc(String lxsqlxmc) {
		this.lxsqlxmc = lxsqlxmc;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-23 ����02:01:46 
	 * @return		: the yqmc
	 */
	public String getYqmc() {
		return yqmc;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-23 ����02:01:46 
	 * @param 		��yqmc the yqmc to set
	 */
	public void setYqmc(String yqmc) {
		this.yqmc = yqmc;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-23 ����02:01:46 
	 * @return		: the lxxqmc
	 */
	public String getLxxqmc() {
		return lxxqmc;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-23 ����02:01:46 
	 * @param 		��lxxqmc the lxxqmc to set
	 */
	public void setLxxqmc(String lxxqmc) {
		this.lxxqmc = lxxqmc;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-23 ����02:01:46 
	 * @return		: the lxldmc
	 */
	public String getLxldmc() {
		return lxldmc;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-23 ����02:01:46 
	 * @param 		��lxldmc the lxldmc to set
	 */
	public void setLxldmc(String lxldmc) {
		this.lxldmc = lxldmc;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-23 ����02:01:46 
	 * @return		: the lxqs
	 */
	public String getLxqs() {
		return lxqs;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-23 ����02:01:46 
	 * @param 		��lxqs the lxqs to set
	 */
	public void setLxqs(String lxqs) {
		this.lxqs = lxqs;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-23 ����02:24:11 
	 * @return		: the lxxq
	 */
	public String getLxxq() {
		return lxxq;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-23 ����02:24:11 
	 * @param 		��lxxq the lxxq to set
	 */
	public void setLxxq(String lxxq) {
		this.lxxq = lxxq;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-23 ����02:24:11 
	 * @return		: the lxld
	 */
	public String getLxld() {
		return lxld;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-23 ����02:24:11 
	 * @param 		��lxld the lxld to set
	 */
	public void setLxld(String lxld) {
		this.lxld = lxld;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-2 ����03:09:49 
	 * @return		: the sfgcj
	 */
	public String getSfgcj() {
		return sfgcj;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-2 ����03:09:49 
	 * @param 		��sfgcj the sfgcj to set
	 */
	public void setSfgcj(String sfgcj) {
		this.sfgcj = sfgcj;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-2 ����03:57:58 
	 * @return		: the sqlxtj
	 */
	public String getSqlxtj() {
		return sqlxtj;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-2 ����03:57:58 
	 * @param 		��sqlxtj the sqlxtj to set
	 */
	public void setSqlxtj(String sqlxtj) {
		this.sqlxtj = sqlxtj;
	}
	
	
}
