package xsgzgl.qgzx.gwglnew;

import java.util.List;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
/**
 * �ڹ���ѧ-�ڹ���λ����-��λ��Ϣ����
 */
public class QgzxGwglForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();

	private String type;
	
	
	private User user;
	private String pkValue;//����
	private String sqid;//����id
	private String xh;//����
	private String xhs;
	private String xn;//ѧ��
	private String gwxh;//��λ���
	private String yrbm;//���˲���
	private String yrdwdm;
	private String gwdm;//��λ����
	private String gwmc;//��λ����
	private String gwxzdm;//��������
	private String xqrs;//��Ƹ����
	private String knsrs;//����������
	private String fknsrs;//����ķ�����������
	private String gwms;//��λ����
	private String gwryyq;//��λ��ԱҪ����ƸҪ��
	private String bz;//��ע
	private String gwyqryxg; //��λԤ����ԱЧ��
	private String sqr;
	private String gwlb;//��λ���
	private String gssx;
	private String zpkssj;
	private String zpjssj;
	private String sfzd;
	private String sfxsgz;
	private String splc;
	private String fbsj;
	private String cq;
	private String yrdwid;
	private String fjid;// ����id

	public String getFjid() {
		return fjid;
	}

	public void setFjid(String fjid) {
		this.fjid = fjid;
	}

	public String getSqid() {
		return sqid;
	}

	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

	public String getYrdwid() {
		return yrdwid;
	}

	public void setYrdwid(String yrdwid) {
		this.yrdwid = yrdwid;
	}

	public String getFbsj() {
		return fbsj;
	}

	public void setFbsj(String fbsj) {
		this.fbsj = fbsj;
	}

	public String getCq() {
		return cq;
	}

	public void setCq(String cq) {
		this.cq = cq;
	}

	public String getSplc() {
		return splc;
	}

	public void setSplc(String splc) {
		this.splc = splc;
	}

	public String getGwlb() {
		return gwlb;
	}

	public void setGwlb(String gwlb) {
		this.gwlb = gwlb;
	}

	public String getGssx() {
		return gssx;
	}

	public void setGssx(String gssx) {
		this.gssx = gssx;
	}

	public String getZpkssj() {
		return zpkssj;
	}

	public void setZpkssj(String zpkssj) {
		this.zpkssj = zpkssj;
	}

	public String getZpjssj() {
		return zpjssj;
	}

	public void setZpjssj(String zpjssj) {
		this.zpjssj = zpjssj;
	}

	public String getSfzd() {
		return sfzd;
	}

	public void setSfzd(String sfzd) {
		this.sfzd = sfzd;
	}

	public String getSfxsgz() {
		return sfxsgz;
	}

	public void setSfxsgz(String sfxsgz) {
		this.sfxsgz = sfxsgz;
	}

	private String doType;//��������
	private String zgzt;//�ڸ�״̬
	private String sgsj;//�ϸ�ʱ��
	private String tgsj;//�˸�ʱ��
	private String tgyy;//�˸�ԭ��
	private String shzt;//���״̬
	private String shyj;//������
	private String sqbhs;//������
	
	private String gwcjsx;//��λ�������
	
	private String sfsgwsqsxz;//�Ƿ��ܸ�λ����������
	private String yxssz;//��Чʱ����
	private String gwkssj;//��λ��ʼʱ��
	private String gwjssj;//��λ����ʱ��
	private String sfyxgw;//�Ƿ���Ч��λ
	private String zxdwlb;//���˵�λ���
	private String zjly;//�ʽ���Դ
	/**
	 * �㽭��ҽҩ���Ի��ֶ�
	 */
	private String ssbm;//��������
	private String fzls;//������ʦ
	private String lxdh;//��ϵ�绰
	private String lsbgs;//��ʦ�칫��
	private String gzdd;//�����ص�
	private String gzsj;//����ʱ��
	private String gznr;//��������
	
	/**
	 * ���������Ի��ֶ�
	 */
	private String gwcjbz;//�����ص�
	private String jfhb;//����ʱ��
	private String zc;//��������
	
	private String xq;//ѧ��
	
	private String[] sqxy;
	private String sqxyms;
	
	//�㽭���θ��Ի��ֶ�
	private String gwshr;//��λ����˹���
	private String gwshrxm;//��λ�����xm
	
	//�㽭��ҽҩ���Ի��ֶ�
	private String ssxq;
	private String gdgcjbz;//�̶��ڳ���׼�������Ƽ���ѧ��
	private String gwlx;

	//���ݴ�ѧ
	private String lxr;//��ϵ��

	//ͨ���ֶ�����
	private String splcid;
	private String sjly;
	private String[] splcids;
	
	//����ֶ�
	 private String shid;
	 private String shjg;
	 private String gwid;
	 private String thgw;
	 private String shlc;
	 
	//���������
	 private String[] id;
	 private String[] gwids;
	 private String[] sqrs;
	 
	 //�㽭����ѧԺ���Ի�
	 private String lxphone;  //��ϵ�绰
	 private String[] zjs; //����model
	 private String sqsj;
	 private String nd;
	 
	 
	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String[] getZjs() {
		return zjs;
	}

	public void setZjs(String[] zjs) {
		this.zjs = zjs;
	}

	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	public String getLxphone() {
		return lxphone;
	}

	public void setLxphone(String lxphone) {
		this.lxphone = lxphone;
	}

	public String getGwlx() {
		return gwlx;
	}

	public void setGwlx(String gwlx) {
		this.gwlx = gwlx;
	}

	
	 

	public String getShlc() {
		return shlc;
	}

	public void setShlc(String shlc) {
		this.shlc = shlc;
	}

	
	public String[] getSplcids() {
		return splcids;
	}

	public void setSplcids(String[] splcids) {
		this.splcids = splcids;
	}

	 public String getYrdwdm() {
			return yrdwdm;
	}

	public void setYrdwdm(String yrdwdm) {
			this.yrdwdm = yrdwdm;
	}
	public String getSjly() {
		return sjly;
	}

	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	 public String getShid() {
		return shid;
	}

	public void setShid(String shid) {
		this.shid = shid;
	}

	public String getShjg() {
		return shjg;
	}

	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	public String getGwid() {
		return gwid;
	}

	public void setGwid(String gwid) {
		this.gwid = gwid;
	}

	public String getThgw() {
		return thgw;
	}

	public void setThgw(String thgw) {
		this.thgw = thgw;
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

	public String[] getSqrs() {
		return sqrs;
	}

	public void setSqrs(String[] sqrs) {
		this.sqrs = sqrs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getSqr() {
		return sqr;
	}

	public void setSqr(String sqr) {
		this.sqr = sqr;
	}


	public String getSplcid() {
		return splcid;
	}

	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}

	public String getSsxq() {
		return ssxq;
	}

	public void setSsxq(String ssxq) {
		this.ssxq = ssxq;
	}

	/**
	 * @return the gwshr
	 */
	public String getGwshr() {
		return gwshr;
	}

	/**
	 * @param gwshrҪ���õ� gwshr
	 */
	public void setGwshr(String gwshr) {
		this.gwshr = gwshr;
	}

	/**
	 * @return the gwshrxm
	 */
	public String getGwshrxm() {
		return gwshrxm;
	}

	/**
	 * @param gwshrxmҪ���õ� gwshrxm
	 */
	public void setGwshrxm(String gwshrxm) {
		this.gwshrxm = gwshrxm;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	/**
	 * @return the gzdd
	 */
	public String getGzdd() {
		return gzdd;
	}

	/**
	 * @param gzddҪ���õ� gzdd
	 */
	public void setGzdd(String gzdd) {
		this.gzdd = gzdd;
	}

	/**
	 * @return the gzsj
	 */
	public String getGzsj() {
		return gzsj;
	}

	/**
	 * @param gzsjҪ���õ� gzsj
	 */
	public void setGzsj(String gzsj) {
		this.gzsj = gzsj;
	}

	/**
	 * @return the gznr
	 */
	public String getGznr() {
		return gznr;
	}

	/**
	 * @param gznrҪ���õ� gznr
	 */
	public void setGznr(String gznr) {
		this.gznr = gznr;
	}

	/**
	 * @return the ssbm
	 */
	public String getSsbm() {
		return ssbm;
	}

	/**
	 * @param ssbmҪ���õ� ssbm
	 */
	public void setSsbm(String ssbm) {
		this.ssbm = ssbm;
	}

	/**
	 * @return the fzls
	 */
	public String getFzls() {
		return fzls;
	}

	/**
	 * @param fzlsҪ���õ� fzls
	 */
	public void setFzls(String fzls) {
		this.fzls = fzls;
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
	 * @return the lsbgs
	 */
	public String getLsbgs() {
		return lsbgs;
	}

	/**
	 * @param lsbgsҪ���õ� lsbgs
	 */
	public void setLsbgs(String lsbgs) {
		this.lsbgs = lsbgs;
	}

	/**
	 * @return the zxdwlb
	 */
	public String getZxdwlb() {
		return zxdwlb;
	}

	/**
	 * @param zxdwlbҪ���õ� zxdwlb
	 */
	public void setZxdwlb(String zxdwlb) {
		this.zxdwlb = zxdwlb;
	}

	/**
	 * @return the zjly
	 */
	public String getZjly() {
		return zjly;
	}

	/**
	 * @param zjlyҪ���õ� zjly
	 */
	public void setZjly(String zjly) {
		this.zjly = zjly;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getYrbm() {
		return yrbm;
	}

	public void setYrbm(String yrbm) {
		this.yrbm = yrbm;
	}

	public String getGwdm() {
		return gwdm;
	}

	public void setGwdm(String gwdm) {
		this.gwdm = gwdm;
	}

	public String getGwmc() {
		return gwmc;
	}

	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}

	public String getGwxzdm() {
		return gwxzdm;
	}

	public void setGwxzdm(String gwxzdm) {
		this.gwxzdm = gwxzdm;
	}

	public String getXqrs() {
		return xqrs;
	}

	public void setXqrs(String xqrs) {
		this.xqrs = xqrs;
	}

	public String getKnsrs() {
		return knsrs;
	}

	public void setKnsrs(String knsrs) {
		this.knsrs = knsrs;
	}

	public String getFknsrs() {
		return fknsrs;
	}

	public void setFknsrs(String fknsrs) {
		this.fknsrs = fknsrs;
	}

	public String getGwms() {
		return gwms;
	}

	public void setGwms(String gwms) {
		this.gwms = gwms;
	}

	public String getGwryyq() {
		return gwryyq;
	}

	public void setGwryyq(String gwryyq) {
		this.gwryyq = gwryyq;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getDoType() {
		return doType;
	}

	public void setDoType(String doType) {
		this.doType = doType;
	}

	public String getZgzt() {
		return zgzt;
	}

	public void setZgzt(String zgzt) {
		this.zgzt = zgzt;
	}

	public String getSgsj() {
		return sgsj;
	}

	public void setSgsj(String sgsj) {
		this.sgsj = sgsj;
	}

	public String getTgsj() {
		return tgsj;
	}

	public void setTgsj(String tgsj) {
		this.tgsj = tgsj;
	}

	public String getTgyy() {
		return tgyy;
	}

	public void setTgyy(String tgyy) {
		this.tgyy = tgyy;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
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
	 * @return the sqbhs
	 */
	public String getSqbhs() {
		return sqbhs;
	}

	/**
	 * @param sqbhsҪ���õ� sqbhs
	 */
	public void setSqbhs(String sqbhs) {
		this.sqbhs = sqbhs;
	}

	/**
	 * @return the gwcjsx
	 */
	public String getGwcjsx() {
		return gwcjsx;
	}

	/**
	 * @param gwcjsxҪ���õ� gwcjsx
	 */
	public void setGwcjsx(String gwcjsx) {
		this.gwcjsx = gwcjsx;
	}

	/**
	 * @return the gwxh
	 */
	public String getGwxh() {
		return gwxh;
	}

	/**
	 * @param gwxhҪ���õ� gwxh
	 */
	public void setGwxh(String gwxh) {
		this.gwxh = gwxh;
	}

	/**
	 * @return the gwyqryxg
	 */
	public String getGwyqryxg() {
		return gwyqryxg;
	}

	/**
	 * @param gwyqryxgҪ���õ� gwyqryxg
	 */
	public void setGwyqryxg(String gwyqryxg) {
		this.gwyqryxg = gwyqryxg;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the sfsgwsqsxz
	 */
	public String getSfsgwsqsxz() {
		return sfsgwsqsxz;
	}

	/**
	 * @param sfsgwsqsxzҪ���õ� sfsgwsqsxz
	 */
	public void setSfsgwsqsxz(String sfsgwsqsxz) {
		this.sfsgwsqsxz = sfsgwsqsxz;
	}

	/**
	 * @return the yxssz
	 */
	public String getYxssz() {
		return yxssz;
	}

	/**
	 * @param yxsszҪ���õ� yxssz
	 */
	public void setYxssz(String yxssz) {
		this.yxssz = yxssz;
	}

	/**
	 * @return the gwkssj
	 */
	public String getGwkssj() {
		return gwkssj;
	}

	/**
	 * @param gwkssjҪ���õ� gwkssj
	 */
	public void setGwkssj(String gwkssj) {
		this.gwkssj = gwkssj;
	}

	/**
	 * @return the gwjssj
	 */
	public String getGwjssj() {
		return gwjssj;
	}

	/**
	 * @param gwjssjҪ���õ� gwjssj
	 */
	public void setGwjssj(String gwjssj) {
		this.gwjssj = gwjssj;
	}

	/**
	 * @return the sfyxgw
	 */
	public String getSfyxgw() {
		return sfyxgw;
	}

	/**
	 * @param sfyxgwҪ���õ� sfyxgw
	 */
	public void setSfyxgw(String sfyxgw) {
		this.sfyxgw = sfyxgw;
	}

	/**
	 * @return the xhs
	 */
	public String getXhs() {
		return xhs;
	}

	/**
	 * @param xhsҪ���õ� xhs
	 */
	public void setXhs(String xhs) {
		this.xhs = xhs;
	}

	/**
	 * @return the gwcjbz
	 */
	public String getGwcjbz() {
		return gwcjbz;
	}

	/**
	 * @param gwcjbzҪ���õ� gwcjbz
	 */
	public void setGwcjbz(String gwcjbz) {
		this.gwcjbz = gwcjbz;
	}

	/**
	 * @return the jfhb
	 */
	public String getJfhb() {
		return jfhb;
	}

	/**
	 * @param jfhbҪ���õ� jfhb
	 */
	public void setJfhb(String jfhb) {
		this.jfhb = jfhb;
	}

	/**
	 * @return the zc
	 */
	public String getZc() {
		return zc;
	}

	/**
	 * @param zcҪ���õ� zc
	 */
	public void setZc(String zc) {
		this.zc = zc;
	}

	/**
	 * @return the sqxy
	 */
	public String[] getSqxy() {
		return sqxy;
	}

	/**
	 * @param sqxyҪ���õ� sqxy
	 */
	public void setSqxy(String[] sqxy) {
		this.sqxy = sqxy;
	}

	/**
	 * @return the sqxyms
	 */
	public String getSqxyms() {
		return sqxyms;
	}

	/**
	 * @param sqxymsҪ���õ� sqxyms
	 */
	public void setSqxyms(String sqxyms) {
		this.sqxyms = sqxyms;
	}

	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-10 ����05:45:06 
	 * @return		: the gdgcjbz
	 */
	public String getGdgcjbz() {
		return gdgcjbz;
	}

	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-10 ����05:45:06 
	 * @param 		��gdgcjbz the gdgcjbz to set
	 */
	public void setGdgcjbz(String gdgcjbz) {
		this.gdgcjbz = gdgcjbz;
	}

	
	
	
	
}
