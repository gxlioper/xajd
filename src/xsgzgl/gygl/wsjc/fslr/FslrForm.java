package xsgzgl.gygl.wsjc.fslr;

import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.CommForm;
import xgxt.form.User;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-6-28 ����11:29:22
 * </p>
 */
public class FslrForm extends CommForm {

	private static final long serialVersionUID = 9219083873367155307L;
	private User user;

	private String guid;

	private String xn; // ѧ��

	private String xq; // ѧ��

	private String mc; // ����

	private String kssj; // ��ʼʱ��

	private String jssj; // ����ʱ��

	private String bz; // ��ע

	private String xqmc;// ѧ������

	private String qss;// ������

	private String ypfqss;// ������������

	private String wpfqss;// δ����������

	private String ld; // ¥����

	private String ldmc;// ¥������

	private String qsh;// ���Һ�

	private String shnj;// �����꼶

	private String shxy;// ����ѧԺ

	private String cws;// ��λ��

	private String rzrs;// ��ס����

	private String fz;// ��ֵ

	private String jcrq; // �������

	private String jcbm;// ��鲿��

	private String jcry;// �����Ա
	private FormFile impFilePath;
	
	private String kfyj;
	private String searchTjByGyfdy;//��Ԣ����ԱȨ������
	
	//�ƶ��������������
	private String[] pfbzs;//���ֱ�ע����
	private String[] fss;//��������
	private String[] djs;//�ȼ�����
	private String[] lddms; //¥����������
	private String[] qshs;//���Һ�
	//����о���ó�׸��Ի��ֶ�
	private String[] pfbznames;
	private String[] fjpaths;
	//����о���ó�׸��Ի��ֶ� end
	public String[] getFjpaths() {
		return fjpaths;
	}

	public void setFjpaths(String[] fjpaths) {
		this.fjpaths = fjpaths;
	}

	
	public String[] getPfbznames() {
		return pfbznames;
	}

	public void setPfbznames(String[] pfbznames) {
		this.pfbznames = pfbznames;
	}

	public String[] getLddms() {
		return lddms;
	}

	public void setLddms(String[] lddms) {
		this.lddms = lddms;
	}

	public String[] getQshs() {
		return qshs;
	}

	public void setQshs(String[] qshs) {
		this.qshs = qshs;
	}

	public String[] getPfbzs() {
		return pfbzs;
	}

	public void setPfbzs(String[] pfbzs) {
		this.pfbzs = pfbzs;
	}

	public String[] getFss() {
		return fss;
	}

	public void setFss(String[] fss) {
		this.fss = fss;
	}

	public String[] getDjs() {
		return djs;
	}

	public void setDjs(String[] djs) {
		this.djs = djs;
	}

	public String getSearchTjByGyfdy() {
		return searchTjByGyfdy;
	}

	public void setSearchTjByGyfdy(String searchTjByGyfdy) {
		this.searchTjByGyfdy = searchTjByGyfdy;
	}

	private ExportModel exportModel = new ExportModel();
	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public String getJcrq() {
		return jcrq;
	}

	public void setJcrq(String jcrq) {
		this.jcrq = jcrq;
	}

	public String getJcbm() {
		return jcbm;
	}

	public void setJcbm(String jcbm) {
		this.jcbm = jcbm;
	}

	public String getJcry() {
		return jcry;
	}

	public void setJcry(String jcry) {
		this.jcry = jcry;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
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

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
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

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getXqmc() {
		return xqmc;
	}

	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}

	public String getQss() {
		return qss;
	}

	public void setQss(String qss) {
		this.qss = qss;
	}

	public String getYpfqss() {
		return ypfqss;
	}

	public void setYpfqss(String ypfqss) {
		this.ypfqss = ypfqss;
	}

	public String getWpfqss() {
		return wpfqss;
	}

	public void setWpfqss(String wpfqss) {
		this.wpfqss = wpfqss;
	}

	public String getLd() {
		return ld;
	}

	public void setLd(String ld) {
		this.ld = ld;
	}

	public String getLdmc() {
		return ldmc;
	}

	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}

	public String getQsh() {
		return qsh;
	}

	public void setQsh(String qsh) {
		this.qsh = qsh;
	}

	public String getShnj() {
		return shnj;
	}

	public void setShnj(String shnj) {
		this.shnj = shnj;
	}

	public String getShxy() {
		return shxy;
	}

	public void setShxy(String shxy) {
		this.shxy = shxy;
	}

	public String getCws() {
		return cws;
	}

	public void setCws(String cws) {
		this.cws = cws;
	}

	public String getRzrs() {
		return rzrs;
	}

	public void setRzrs(String rzrs) {
		this.rzrs = rzrs;
	}

	public String getFz() {
		return fz;
	}

	public void setFz(String fz) {
		this.fz = fz;
	}

	public void setImpFilePath(FormFile impFilePath) {
		this.impFilePath = impFilePath;
	}

	public FormFile getImpFilePath() {
		return impFilePath;
	}

	/**
	 * @return the kfyj
	 */
	public String getKfyj() {
		return kfyj;
	}

	/**
	 * @param kfyjҪ���õ� kfyj
	 */
	public void setKfyj(String kfyj) {
		this.kfyj = kfyj;
	}
	
	
}