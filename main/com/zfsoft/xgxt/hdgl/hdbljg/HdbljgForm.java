/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.hdgl.hdbljg;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @className	�� HdbljgForm
 * @description	�� TODO(��������������)
 * @author 		��������1282��
 * @date		�� 2018-1-22 ����08:54:52
 * @version 	V1.0 
 */

public class HdbljgForm extends ActionForm {
	
	
	/**
	 * @fields ��serialVersionUID : TODO
	 */
	
	private static final long serialVersionUID = 1L;
	private String jgid;
	private String sqid;
	private String xh;
	private String xn;
	private String xq;
	private String hdmc;
	private String hdsj;
	private String hdxs;
	private String hdlx;
	private String hddd;
	private String cjlx;
	private String zdzw;
	private String hdzw;
	private String hdjx;
	private String hdxf;
	private String fjpath;
	private String sjly;
	private String bz;
	private String sqsj;
	private String type;
	private String hdlxmc;
	private String xqmc;
	private String hdbq;
	private String hdbqmc;//���ǩ���ƣ���Ϊ����ǰ�˵��߼�������ǿ�м����form����Ϊ�˹��鿴ʹ��
	private String nlbq;//������ǩ
	private String nlbqmc;//������ǩ���ƣ���Ϊ����ǰ�˵��߼�������ǿ�м����form����Ϊ�˹��鿴ʹ��
	private String jzlx;//�γ̼���
	private String jzlxmc;//�����������ƣ���Ϊ����ǰ�˵��߼�������ǿ�м����form����Ϊ�˹��鿴ʹ��
	private String zxkclx;//��ѡ�γ�����
	private String zxkclxmc;
	private String xsxxlx;//������������
	private String hdkclx;//��γ����ͣ��������׶Σ������൥�׶Σ�
	private String hddf;//����
	private String[] hdbqs;
	private String[] nlbqs;
	private String zyxss;//־ԸСʱ��

	private String zbf;//���췽
	private String zjrxm;//����������
	private String zjrdw;//�����˵�λ
	private String zjrzc;//������ְ��
	private String zjrzw;//������ְ��
	private String zjrjs;//�����˽���
	private String jzjb;//��������
	private String ly;//���Դ�����ڲ�¼ѡ�����л��
	private String hdid;//���Դid���������������ڲ�¼ѡ�����л��

	public String getJzjb() {
		return jzjb;
	}

	public void setJzjb(String jzjb) {
		this.jzjb = jzjb;
	}

	public String getZbf() {
		return zbf;
	}

	public void setZbf(String zbf) {
		this.zbf = zbf;
	}

	public String getZjrxm() {
		return zjrxm;
	}

	public void setZjrxm(String zjrxm) {
		this.zjrxm = zjrxm;
	}

	public String getZjrdw() {
		return zjrdw;
	}

	public void setZjrdw(String zjrdw) {
		this.zjrdw = zjrdw;
	}

	public String getZjrzc() {
		return zjrzc;
	}

	public void setZjrzc(String zjrzc) {
		this.zjrzc = zjrzc;
	}

	public String getZjrzw() {
		return zjrzw;
	}

	public void setZjrzw(String zjrzw) {
		this.zjrzw = zjrzw;
	}

	public String getZjrjs() {
		return zjrjs;
	}

	public void setZjrjs(String zjrjs) {
		this.zjrjs = zjrjs;
	}

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();

	public String getHddf() {
		return hddf;
	}

	public void setHddf(String hddf) {
		this.hddf = hddf;
	}

	public String getXsxxlx() {
		return xsxxlx;
	}

	public void setXsxxlx(String xsxxlx) {
		this.xsxxlx = xsxxlx;
	}

	public String getHdkclx() {
		return hdkclx;
	}

	public void setHdkclx(String hdkclx) {
		this.hdkclx = hdkclx;
	}

	public String getZxkclx() {
		return zxkclx;
	}

	public void setZxkclx(String zxkclx) {
		this.zxkclx = zxkclx;
	}

	public String getZxkclxmc() {
		return zxkclxmc;
	}

	public void setZxkclxmc(String zxkclxmc) {
		this.zxkclxmc = zxkclxmc;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @return		: the jgid
	 */
	public String getJgid() {
		return jgid;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @param 		��jgid the jgid to set
	 */
	public void setJgid(String jgid) {
		this.jgid = jgid;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @return		: the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @param 		��sqid the sqid to set
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @return		: the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @param 		��xh the xh to set
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @return		: the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @param 		��xn the xn to set
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @return		: the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @param 		��xq the xq to set
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @return		: the hdmc
	 */
	public String getHdmc() {
		return hdmc;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @param 		��hdmc the hdmc to set
	 */
	public void setHdmc(String hdmc) {
		this.hdmc = hdmc;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @return		: the hdsj
	 */
	public String getHdsj() {
		return hdsj;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @param 		��hdsj the hdsj to set
	 */
	public void setHdsj(String hdsj) {
		this.hdsj = hdsj;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @return		: the hdxs
	 */
	public String getHdxs() {
		return hdxs;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @param 		��hdxs the hdxs to set
	 */
	public void setHdxs(String hdxs) {
		this.hdxs = hdxs;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @return		: the hdlx
	 */
	public String getHdlx() {
		return hdlx;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @param 		��hdlx the hdlx to set
	 */
	public void setHdlx(String hdlx) {
		this.hdlx = hdlx;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @return		: the hddd
	 */
	public String getHddd() {
		return hddd;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @param 		��hddd the hddd to set
	 */
	public void setHddd(String hddd) {
		this.hddd = hddd;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @return		: the cjlx
	 */
	public String getCjlx() {
		return cjlx;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @param 		��cjlx the cjlx to set
	 */
	public void setCjlx(String cjlx) {
		this.cjlx = cjlx;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @return		: the zdzw
	 */
	public String getZdzw() {
		return zdzw;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @param 		��zdzw the zdzw to set
	 */
	public void setZdzw(String zdzw) {
		this.zdzw = zdzw;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @return		: the hdzw
	 */
	public String getHdzw() {
		return hdzw;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @param 		��hdzw the hdzw to set
	 */
	public void setHdzw(String hdzw) {
		this.hdzw = hdzw;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @return		: the hdjx
	 */
	public String getHdjx() {
		return hdjx;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @param 		��hdjx the hdjx to set
	 */
	public void setHdjx(String hdjx) {
		this.hdjx = hdjx;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @return		: the hdxf
	 */
	public String getHdxf() {
		return hdxf;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @param 		��hdxf the hdxf to set
	 */
	public void setHdxf(String hdxf) {
		this.hdxf = hdxf;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @return		: the fjpath
	 */
	public String getFjpath() {
		return fjpath;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @param 		��fjpath the fjpath to set
	 */
	public void setFjpath(String fjpath) {
		this.fjpath = fjpath;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @return		: the sjly
	 */
	public String getSjly() {
		return sjly;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @param 		��sjly the sjly to set
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @return		: the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:06:32 
	 * @param 		��bz the bz to set
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:10:28 
	 * @return		: the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����09:10:28 
	 * @param 		��sqsj the sqsj to set
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����10:49:29 
	 * @return		: the hdbqs
	 */
	public String[] getHdbqs() {
		return hdbqs;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-22 ����10:49:29 
	 * @param 		��hdbqs the hdbqs to set
	 */
	public void setHdbqs(String[] hdbqs) {
		this.hdbqs = hdbqs;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-23 ����10:59:08 
	 * @return		: the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-23 ����10:59:08 
	 * @param 		��type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-23 ����02:40:32 
	 * @return		: the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-23 ����02:40:32 
	 * @param 		��pages the pages to set
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-23 ����02:40:32 
	 * @return		: the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-23 ����02:40:32 
	 * @param 		��searchModel the searchModel to set
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-23 ����02:40:32 
	 * @return		: the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-23 ����02:40:32 
	 * @param 		��exportModel the exportModel to set
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-23 ����04:30:57 
	 * @return		: the hdlxmc
	 */
	public String getHdlxmc() {
		return hdlxmc;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-23 ����04:30:57 
	 * @param 		��hdlxmc the hdlxmc to set
	 */
	public void setHdlxmc(String hdlxmc) {
		this.hdlxmc = hdlxmc;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-23 ����04:33:53 
	 * @return		: the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-23 ����04:33:53 
	 * @param 		��xqmc the xqmc to set
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-23 ����05:52:47 
	 * @return		: the hdbq
	 */
	public String getHdbq() {
		return hdbq;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-23 ����05:52:47 
	 * @param 		��hdbq the hdbq to set
	 */
	public void setHdbq(String hdbq) {
		this.hdbq = hdbq;
	}
	/**
	 * @return the jzlx
	 */
	public String getJzlx() {
		return jzlx;
	}
	/**
	 * @param jzlxҪ���õ� jzlx
	 */
	public void setJzlx(String jzlx) {
		this.jzlx = jzlx;
	}
	/**
	 * @return the nlbqs
	 */
	public String[] getNlbqs() {
		return nlbqs;
	}
	/**
	 * @param nlbqsҪ���õ� nlbqs
	 */
	public void setNlbqs(String[] nlbqs) {
		this.nlbqs = nlbqs;
	}
	/**
	 * @return the nlbq
	 */
	public String getNlbq() {
		return nlbq;
	}
	/**
	 * @param nlbqҪ���õ� nlbq
	 */
	public void setNlbq(String nlbq) {
		this.nlbq = nlbq;
	}
	/**
	 * @return the nlbqmc
	 */
	public String getNlbqmc() {
		return nlbqmc;
	}
	/**
	 * @param nlbqmcҪ���õ� nlbqmc
	 */
	public void setNlbqmc(String nlbqmc) {
		this.nlbqmc = nlbqmc;
	}
	/**
	 * @return the hdbqmc
	 */
	public String getHdbqmc() {
		return hdbqmc;
	}
	/**
	 * @param hdbqmcҪ���õ� hdbqmc
	 */
	public void setHdbqmc(String hdbqmc) {
		this.hdbqmc = hdbqmc;
	}
	/**
	 * @return the jzlxmc
	 */
	public String getJzlxmc() {
		return jzlxmc;
	}
	/**
	 * @param jzlxmcҪ���õ� jzlxmc
	 */
	public void setJzlxmc(String jzlxmc) {
		this.jzlxmc = jzlxmc;
	}

	public String getLy() {
		return ly;
	}

	public void setLy(String ly) {
		this.ly = ly;
	}

	public String getHdid() {
		return hdid;
	}

	public void setHdid(String hdid) {
		this.hdid = hdid;
	}

	public String getZyxss() {
		return zyxss;
	}

	public void setZyxss(String zyxss) {
		this.zyxss = zyxss;
	}
}
