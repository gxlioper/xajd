
package com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl;

import com.zfsoft.xgxt.jyglnew.jygl.comm.JyglForm;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����������Ŀ����ģ��
 * @�๦������: ����������Ŀ����ʵ��
 * @���ߣ� Lu.Yao[����:1271]
 * @ʱ�䣺 2017-10-16 ����03:08:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class ZzyrxmglActionForm extends JyglForm {

	private static final long serialVersionUID = 156208654779020468L;
	
	//������Ϣ��
	private String id;
	private String fdxxid;//������Ϣid
	private String fdfbid;//��������id
	private String shzt;//���״̬ 0:��ͬ��   1:ͬ��
	private String fdrxh;//������ѧ��
	private String fdrxm;//����������
	private String fdrxymc;//������ѧԺ����
	private String fdrlxdh;//��������ϵ�绰
	private String bfdrxh;//��������ѧ��
	private String bfdrxm;//������������
	private String bfdrxymc;//��������ѧԺ����
	private String bfdrlxdh;//����������ϵ�绰
	private String xspjjg;//ѧ�����۽��
	private String lspjjg;//��ʦ���۽��
	private String tysj;//ͬ��ʱ��
	
	private String fdrfdy;//�����˸���Ա����
	private String bfdrfdy;//�������˸���Ա����
	private String xscshr;//ѧ��������˹���
	private String fdyshzt;//�����˸���Ա���״̬
	private String bfdyshzt;//�������˸���Ա���״̬
	private String xscshzt;//ѧ�������״̬
	private String shrxz;//���������
	
	//����������
	private String fbrxh;//������ѧ��
	private String fbrxm;//����������
	private String fbrxydm;//ѧԺ����
	private String fbrxymc;//ѧԺ����
	private String fdkm;//������Ŀ
	private String fdsj;//����ʱ��
	private String xdrs;//�޶�����
	private String fblx;//����  0:��������  1:��Ҫ����
	private String fbbz;//��ע
	private String sfsq;//�Ƿ񸨵�
	private String sffd;//�Ƿ�����
	
	//��������ѧԺ������
	private String kfxydm;//ѧԺ����
	private String kfxymc;//ѧԺ����
	
	//������¼��
	private String fdrq;//��������
	private String fdnr;//��������
	private String fdbz;//������ע
	private String fdlx;//����  0:��������д   1:����������д
	
	private String xydm;//
	private String zydm;//
	private String bjdm;//
	private String bj;//
	
	private String fdjssj;
	private String gs;
	private String fddd;
	private String fdpj;
	
	public String getFdrlxdh() {
		return fdrlxdh;
	}
	public void setFdrlxdh(String fdrlxdh) {
		this.fdrlxdh = fdrlxdh;
	}
	public String getBfdrlxdh() {
		return bfdrlxdh;
	}
	public void setBfdrlxdh(String bfdrlxdh) {
		this.bfdrlxdh = bfdrlxdh;
	}
	public String getFdxxid() {
		return fdxxid;
	}
	public void setFdxxid(String fdxxid) {
		this.fdxxid = fdxxid;
	}
	public String getFdfbid() {
		return fdfbid;
	}
	public void setFdfbid(String fdfbid) {
		this.fdfbid = fdfbid;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getFdrxh() {
		return fdrxh;
	}
	public void setFdrxh(String fdrxh) {
		this.fdrxh = fdrxh;
	}
	public String getBfdrxh() {
		return bfdrxh;
	}
	public void setBfdrxh(String bfdrxh) {
		this.bfdrxh = bfdrxh;
	}
	public String getXspjjg() {
		return xspjjg;
	}
	public void setXspjjg(String xspjjg) {
		this.xspjjg = xspjjg;
	}
	public String getLspjjg() {
		return lspjjg;
	}
	public void setLspjjg(String lspjjg) {
		this.lspjjg = lspjjg;
	}
	public String getTysj() {
		return tysj;
	}
	public void setTysj(String tysj) {
		this.tysj = tysj;
	}
	public String getFbrxh() {
		return fbrxh;
	}
	public void setFbrxh(String fbrxh) {
		this.fbrxh = fbrxh;
	}
	public String getFdkm() {
		return fdkm;
	}
	public void setFdkm(String fdkm) {
		this.fdkm = fdkm;
	}
	public String getFdsj() {
		return fdsj;
	}
	public void setFdsj(String fdsj) {
		this.fdsj = fdsj;
	}
	public String getXdrs() {
		return xdrs;
	}
	public void setXdrs(String xdrs) {
		this.xdrs = xdrs;
	}
	public String getFblx() {
		return fblx;
	}
	public void setFblx(String fblx) {
		this.fblx = fblx;
	}
	public String getFbbz() {
		return fbbz;
	}
	public void setFbbz(String fbbz) {
		this.fbbz = fbbz;
	}
	
	public String getFbrxm() {
		return fbrxm;
	}
	public void setFbrxm(String fbrxm) {
		this.fbrxm = fbrxm;
	}
	public String getFbrxydm() {
		return fbrxydm;
	}
	public void setFbrxydm(String fbrxydm) {
		this.fbrxydm = fbrxydm;
	}
	public String getFbrxymc() {
		return fbrxymc;
	}
	public void setFbrxymc(String fbrxymc) {
		this.fbrxymc = fbrxymc;
	}
	public String getKfxydm() {
		return kfxydm;
	}
	public void setKfxydm(String kfxydm) {
		this.kfxydm = kfxydm;
	}
	public String getKfxymc() {
		return kfxymc;
	}
	public void setKfxymc(String kfxymc) {
		this.kfxymc = kfxymc;
	}
	public String getFdrq() {
		return fdrq;
	}
	public void setFdrq(String fdrq) {
		this.fdrq = fdrq;
	}
	public String getFdnr() {
		return fdnr;
	}
	public void setFdnr(String fdnr) {
		this.fdnr = fdnr;
	}
	public String getFdbz() {
		return fdbz;
	}
	public void setFdbz(String fdbz) {
		this.fdbz = fdbz;
	}
	public String getFdlx() {
		return fdlx;
	}
	public void setFdlx(String fdlx) {
		this.fdlx = fdlx;
	}
	public String getFdrxm() {
		return fdrxm;
	}
	public void setFdrxm(String fdrxm) {
		this.fdrxm = fdrxm;
	}
	public String getFdrxymc() {
		return fdrxymc;
	}
	public void setFdrxymc(String fdrxymc) {
		this.fdrxymc = fdrxymc;
	}
	public String getBfdrxm() {
		return bfdrxm;
	}
	public void setBfdrxm(String bfdrxm) {
		this.bfdrxm = bfdrxm;
	}
	public String getBfdrxymc() {
		return bfdrxymc;
	}
	public void setBfdrxymc(String bfdrxymc) {
		this.bfdrxymc = bfdrxymc;
	}
	public String getSfsq() {
		return sfsq;
	}
	public void setSfsq(String sfsq) {
		this.sfsq = sfsq;
	}
	public String getSffd() {
		return sffd;
	}
	public void setSffd(String sffd) {
		this.sffd = sffd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getBj() {
		return bj;
	}
	public void setBj(String bj) {
		this.bj = bj;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-11-27 ����09:27:58 
	 * @return		: the fdjssj
	 */
	public String getFdjssj() {
		return fdjssj;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-11-27 ����09:27:58 
	 * @param 		��fdjssj the fdjssj to set
	 */
	public void setFdjssj(String fdjssj) {
		this.fdjssj = fdjssj;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-11-27 ����09:27:58 
	 * @return		: the gs
	 */
	public String getGs() {
		return gs;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-11-27 ����09:27:58 
	 * @param 		��gs the gs to set
	 */
	public void setGs(String gs) {
		this.gs = gs;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-11-27 ����09:27:58 
	 * @return		: the fddd
	 */
	public String getFddd() {
		return fddd;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-11-27 ����09:27:58 
	 * @param 		��fddd the fddd to set
	 */
	public void setFddd(String fddd) {
		this.fddd = fddd;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-11-27 ����09:27:58 
	 * @return		: the fdpj
	 */
	public String getFdpj() {
		return fdpj;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-11-27 ����09:27:58 
	 * @param 		��fdpj the fdpj to set
	 */
	public void setFdpj(String fdpj) {
		this.fdpj = fdpj;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-12-19 ����02:29:16 
	 * @return		: the fdrfdy
	 */
	public String getFdrfdy() {
		return fdrfdy;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-12-19 ����02:29:16 
	 * @param 		��fdrfdy the fdrfdy to set
	 */
	public void setFdrfdy(String fdrfdy) {
		this.fdrfdy = fdrfdy;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-12-19 ����02:29:16 
	 * @return		: the bfdrfdy
	 */
	public String getBfdrfdy() {
		return bfdrfdy;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-12-19 ����02:29:16 
	 * @param 		��bfdrfdy the bfdrfdy to set
	 */
	public void setBfdrfdy(String bfdrfdy) {
		this.bfdrfdy = bfdrfdy;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-12-19 ����02:29:16 
	 * @return		: the xscshr
	 */
	public String getXscshr() {
		return xscshr;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-12-19 ����02:29:16 
	 * @param 		��xscshr the xscshr to set
	 */
	public void setXscshr(String xscshr) {
		this.xscshr = xscshr;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-12-19 ����02:29:16 
	 * @return		: the fdyshzt
	 */
	public String getFdyshzt() {
		return fdyshzt;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-12-19 ����02:29:16 
	 * @param 		��fdyshzt the fdyshzt to set
	 */
	public void setFdyshzt(String fdyshzt) {
		this.fdyshzt = fdyshzt;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-12-19 ����02:29:16 
	 * @return		: the bfdyshzt
	 */
	public String getBfdyshzt() {
		return bfdyshzt;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-12-19 ����02:29:16 
	 * @param 		��bfdyshzt the bfdyshzt to set
	 */
	public void setBfdyshzt(String bfdyshzt) {
		this.bfdyshzt = bfdyshzt;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-12-19 ����02:29:16 
	 * @return		: the xscshzt
	 */
	public String getXscshzt() {
		return xscshzt;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-12-19 ����02:29:16 
	 * @param 		��xscshzt the xscshzt to set
	 */
	public void setXscshzt(String xscshzt) {
		this.xscshzt = xscshzt;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-12-21 ����04:32:22 
	 * @return		: the shrxz
	 */
	public String getShrxz() {
		return shrxz;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-12-21 ����04:32:22 
	 * @param 		��shrxz the shrxz to set
	 */
	public void setShrxz(String shrxz) {
		this.shrxz = shrxz;
	}
	
	
}
