package xsgzgl.szdw.general;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import xsgzgl.comm.form.CommForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ˼������_ͨ��_Form��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class SzdwGeneralForm extends CommForm {

	private static final long serialVersionUID = 1L;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	// ------------------˼�������� begin  2013.1.14 by qlj-----------------
	private String xb; // �Ա�
	
	private String bmdm; // ���Ŵ��� 
	
	private String sfdb; // �Ƿ����
	
	private String zgh; // ְ����

	private String zghs;
	// ------------------˼�������� end  2013.1.14 by qlj-----------------
	
	private String bzrdb;//�����δ���
	
	private String fdydb;//����Ա����
	
	private String jryx;//����Ժϵ
	private String khyh;//��������
	private String yhzh;//�����˺�
	private String hyzk;//����״��
	private String fdyzbs;//����Աֵ����
	private String fdyrzrq;//����Ա��ְ����
	private String zyjnzs;//ְҵ����֤��
	private String age;//����
	private String bmlb;//�������
	private String zzshen;//סַ��ʡ��
	private String zzshi;//סַ���У�
	private String zzxian;//סַ���أ�
	private String qqqh;//QQȺ��
	private String bjdm ;//�༶����
	private String bjmc ; //�༶����
	private String type;//����
	private String bbType;//�������
	private String sydm;//��Ժ

	public String getSydm() {
		return sydm;
	}

	public void setSydm(String sydm) {
		this.sydm = sydm;
	}

	public String getBbType() {
		return bbType;
	}

	public void setBbType(String bbType) {
		this.bbType = bbType;
	}

	public String getZghs() {
		return zghs;
	}

	public void setZghs(String zghs) {
		this.zghs = zghs;
	}

	@Override
	public Pages getPages() {
		return pages;
	}

	@Override
	public void setPages(Pages pages) {
		this.pages = pages;
	}

	@Override
	public SearchModel getSearchModel() {
		return searchModel;
	}

	@Override
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	/**
	 * @return the qqqh
	 */
	public String getQqqh() {
		return qqqh;
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
	 * @param qqqhҪ���õ� qqqh
	 */
	public void setQqqh(String qqqh) {
		this.qqqh = qqqh;
	}
	/**
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}
	/**
	 * @param bjdmҪ���õ� bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	/**
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}
	/**
	 * @param bjmcҪ���õ� bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	/**
	 * @return the zzshen
	 */
	public String getZzshen() {
		return zzshen;
	}

	/**
	 * @param zzshenҪ���õ� zzshen
	 */
	public void setZzshen(String zzshen) {
		this.zzshen = zzshen;
	}

	/**
	 * @return the zzshi
	 */
	public String getZzshi() {
		return zzshi;
	}

	/**
	 * @param zzshiҪ���õ� zzshi
	 */
	public void setZzshi(String zzshi) {
		this.zzshi = zzshi;
	}

	/**
	 * @return the zzxian
	 */
	public String getZzxian() {
		return zzxian;
	}

	/**
	 * @param zzxianҪ���õ� zzxian
	 */
	public void setZzxian(String zzxian) {
		this.zzxian = zzxian;
	}

	/**
	 * @return the yhzh
	 */
	public String getYhzh() {
		return yhzh;
	}

	/**
	 * @param yhzhҪ���õ� yhzh
	 */
	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}
	
	/**
	 * @return the khyh
	 */
	public String getKhyh() {
		return khyh;
	}

	/**
	 * @param khyhҪ���õ� khyh
	 */
	public void setKhyh(String khyh) {
		this.khyh = khyh;
	}

	/**
	 * @return the hyzk
	 */
	public String getHyzk() {
		return hyzk;
	}

	/**
	 * @param hyzkҪ���õ� hyzk
	 */
	public void setHyzk(String hyzk) {
		this.hyzk = hyzk;
	}

	/**
	 * @return the fdyzbs
	 */
	public String getFdyzbs() {
		return fdyzbs;
	}

	/**
	 * @param fdyzbsҪ���õ� fdyzbs
	 */
	public void setFdyzbs(String fdyzbs) {
		this.fdyzbs = fdyzbs;
	}

	/**
	 * @return the fdyrzrq
	 */
	public String getFdyrzrq() {
		return fdyrzrq;
	}

	/**
	 * @param fdyrzrqҪ���õ� fdyrzrq
	 */
	public void setFdyrzrq(String fdyrzrq) {
		this.fdyrzrq = fdyrzrq;
	}

	/**
	 * @return the zyjnzs
	 */
	public String getZyjnzs() {
		return zyjnzs;
	}

	/**
	 * @param zyjnzsҪ���õ� zyjnzs
	 */
	public void setZyjnzs(String zyjnzs) {
		this.zyjnzs = zyjnzs;
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param ageҪ���õ� age
	 */
	public void setAge(String age) {
		this.age = age;
	}

	
	

	
	private ExportModel exportModel = new ExportModel();
	private String path;
	

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getSfdb() {
		return sfdb;
	}

	public void setSfdb(String sfdb) {
		this.sfdb = sfdb;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getBzrdb() {
		return bzrdb;
	}

	public void setBzrdb(String bzrdb) {
		this.bzrdb = bzrdb;
	}

	public String getFdydb() {
		return fdydb;
	}

	public void setFdydb(String fdydb) {
		this.fdydb = fdydb;
	}

	public String getJryx() {
		return jryx;
	}

	public void setJryx(String jryx) {
		this.jryx = jryx;
	}

	public String getBmlb() {
		return bmlb;
	}

	public void setBmlb(String bmlb) {
		this.bmlb = bmlb;
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
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param pathҪ���õ� path
	 */
	public void setPath(String path) {
		this.path = path;
	}


}
