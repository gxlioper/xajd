package xsgzgl.gygl.gybxgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xsgzgl.gygl.comm.GyglNewForm;

public class GybxglForm extends GyglNewForm {

	
	private static final long serialVersionUID = 1L;

	private String pk;			//������ѧ��+����ʱ��
	private String lddm;		//¥������
	private String qsh;			//���Һ�
	private String cwh;			//��λ��
	private String xh;			//ѧ��
	private String bxnr;		//��������
	private String bxsj;		//����ʱ��
	private String jjcd;		//�����̶�
	private String bxlb;		//�������
	private String qwwxsj_ks;	//����ά��ʱ��
	private String qwwxsj_js;	//����ά��ʱ��
	private String lxdh;		//��ϵ�绰
	private String clzt;		//����״̬
	private String bclyy;		//������ԭ��
	private String wxry;		//ά����Ա
	private String wxsj;		//ά��ʱ��
	private String wxfy;		//ά�޷���
	private String wxnr;		//ά������
	private String mycd;		//����̶�
	private String pj;			//����
	private String zbclyy;		//�ݲ�����ԭ��
	private String bxlbzxdm;
	private String yhm;
	private String fpzt;        //������ũ�ѡ�����״̬��
	private String fpbm;        //������ũ�ѡ����䲿�š�
	private String flag;        //��־����ѯʱ���������֡������˵����ǡ����䡿�˵�
	private String filepath;
	private String fpxq;        //��������ְҵ����ѧԺ������У����
	
	/**
	 * @return the fpxq
	 */
	public String getFpxq() {
		return fpxq;
	}
	/**
	 * @param fpxqҪ���õ� fpxq
	 */
	public void setFpxq(String fpxq) {
		this.fpxq = fpxq;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	/**
	 * @return the fpzt
	 */
	public String getFpzt() {
		return fpzt;
	}
	/**
	 * @param fpztҪ���õ� fpzt
	 */
	public void setFpzt(String fpzt) {
		this.fpzt = fpzt;
	}
	/**
	 * @return the fpbm
	 */
	public String getFpbm() {
		return fpbm;
	}
	/**
	 * @param fpbmҪ���õ� fpbm
	 */
	public void setFpbm(String fpbm) {
		this.fpbm = fpbm;
	}
	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}
	/**
	 * @param flagҪ���õ� flag
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	private ExportModel exportModel = new ExportModel();
	
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	public String getCwh() {
		return cwh;
	}
	public void setCwh(String cwh) {
		this.cwh = cwh;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getBxnr() {
		return bxnr;
	}
	public void setBxnr(String bxnr) {
		this.bxnr = bxnr;
	}
	public String getBxsj() {
		return bxsj;
	}
	public void setBxsj(String bxsj) {
		this.bxsj = bxsj;
	}
	public String getJjcd() {
		return jjcd;
	}
	public void setJjcd(String jjcd) {
		this.jjcd = jjcd;
	}
	public String getBxlb() {
		return bxlb;
	}
	public void setBxlb(String bxlb) {
		this.bxlb = bxlb;
	}
	public String getQwwxsj_ks() {
		return qwwxsj_ks;
	}
	public void setQwwxsj_ks(String qwwxsjKs) {
		qwwxsj_ks = qwwxsjKs;
	}
	public String getQwwxsj_js() {
		return qwwxsj_js;
	}
	public void setQwwxsj_js(String qwwxsjJs) {
		qwwxsj_js = qwwxsjJs;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getClzt() {
		return clzt;
	}
	public void setClzt(String clzt) {
		this.clzt = clzt;
	}
	public String getBclyy() {
		return bclyy;
	}
	public void setBclyy(String bclyy) {
		this.bclyy = bclyy;
	}
	public String getWxry() {
		return wxry;
	}
	public void setWxry(String wxry) {
		this.wxry = wxry;
	}
	public String getWxsj() {
		return wxsj;
	}
	public void setWxsj(String wxsj) {
		this.wxsj = wxsj;
	}
	public String getWxfy() {
		return wxfy;
	}
	public void setWxfy(String wxfy) {
		this.wxfy = wxfy;
	}
	public String getWxnr() {
		return wxnr;
	}
	public void setWxnr(String wxnr) {
		this.wxnr = wxnr;
	}
	public String getMycd() {
		return mycd;
	}
	public void setMycd(String mycd) {
		this.mycd = mycd;
	}
	public String getPj() {
		return pj;
	}
	public void setPj(String pj) {
		this.pj = pj;
	}
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	public void setZbclyy(String zbclyy) {
		this.zbclyy = zbclyy;
	}
	public String getZbclyy() {
		return zbclyy;
	}
	/**
	 * @return the bxlbzxdm
	 */
	public String getBxlbzxdm() {
		return bxlbzxdm;
	}
	/**
	 * @param bxlbzxdmҪ���õ� bxlbzxdm
	 */
	public void setBxlbzxdm(String bxlbzxdm) {
		this.bxlbzxdm = bxlbzxdm;
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
