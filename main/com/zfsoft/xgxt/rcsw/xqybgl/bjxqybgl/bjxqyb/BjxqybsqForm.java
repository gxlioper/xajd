/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-23 ����06:50:45 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqyb;


import com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.comm.BjxqybForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ҽҩ_ѧ���±�����ģ��
 * @�๦������: TODO(������ҽҩ_ѧ���±�_�༶�±�) 
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-3-23 ����06:50:45 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class BjxqybsqForm extends BjxqybForm {
			
		/** 
		 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
		 */ 
		private static final long serialVersionUID = 1L;
		
		private String sqid;//����id
		private String xn;//ѧ��
		private String xq;//ѧ��
		private String yf;//�·�		
		private String bygzkzqk;//���¹�����չ���
		private String xsgzrd;//ѧ����ע�ȵ�
		private String xssxdt;//ѧ��˼�붯̬
		private String xstsjgzjy;//ѧ�����󼰹�������	
		private String txsj;//��дʱ��
		private String txr;//��д��
		private String shzt;//���״̬:0:δ�ύ,1�ύ
		private String splc;//��������
		private String bjdm;
		private String xymc;
		private String bjmc;
		private String type;
		private String xqmc;			
		private String nj;		
		private String zymc;		
		private String xydm;	
		private String bjrs;//�༶����		

		/**
		 * @return the xydm
		 */
		public String getXydm() {
			return xydm;
		}
		/**
		 * @param xydmҪ���õ� xydm
		 */
		public void setXydm(String xydm) {
			this.xydm = xydm;
		}
		/**
		 * @return the bjrs
		 */
		public String getBjrs() {
			return bjrs;
		}
		/**
		 * @param bjrsҪ���õ� bjrs
		 */
		public void setBjrs(String bjrs) {
			this.bjrs = bjrs;
		}
		/**
		 * @return the nj
		 */
		public String getNj() {
			return nj;
		}
		/**
		 * @param njҪ���õ� nj
		 */
		public void setNj(String nj) {
			this.nj = nj;
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
		 * @return the xqmc
		 */
		public String getXqmc() {
			return xqmc;
		}
		/**
		 * @param xqmcҪ���õ� xqmc
		 */
		public void setXqmc(String xqmc) {
			this.xqmc = xqmc;
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
		 * @return the xymc
		 */
		public String getXymc() {
			return xymc;
		}
		/**
		 * @param xymcҪ���õ� xymc
		 */
		public void setXymc(String xymc) {
			this.xymc = xymc;
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
		 * @return the sqid
		 */
		public String getSqid() {
			return sqid;
		}
		/**
		 * @param sqidҪ���õ� sqid
		 */
		public void setSqid(String sqid) {
			this.sqid = sqid;
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
		 * @return the xq
		 */
		public String getXq() {
			return xq;
		}
		/**
		 * @param xqҪ���õ� xq
		 */
		public void setXq(String xq) {
			this.xq = xq;
		}
		/**
		 * @return the yf
		 */
		public String getYf() {
			return yf;
		}
		/**
		 * @param yfҪ���õ� yf
		 */
		public void setYf(String yf) {
			this.yf = yf;
		}
		/**
		 * @return the bygzkzqk
		 */
		public String getBygzkzqk() {
			return bygzkzqk;
		}
		/**
		 * @param bygzkzqkҪ���õ� bygzkzqk
		 */
		public void setBygzkzqk(String bygzkzqk) {
			this.bygzkzqk = bygzkzqk;
		}
		/**
		 * @return the xsgzrd
		 */
		public String getXsgzrd() {
			return xsgzrd;
		}
		/**
		 * @param xsgzrdҪ���õ� xsgzrd
		 */
		public void setXsgzrd(String xsgzrd) {
			this.xsgzrd = xsgzrd;
		}
		/**
		 * @return the xssxdt
		 */
		public String getXssxdt() {
			return xssxdt;
		}
		/**
		 * @param xssxdtҪ���õ� xssxdt
		 */
		public void setXssxdt(String xssxdt) {
			this.xssxdt = xssxdt;
		}
		/**
		 * @return the xstsjgzjy
		 */
		public String getXstsjgzjy() {
			return xstsjgzjy;
		}
		/**
		 * @param xstsjgzjyҪ���õ� xstsjgzjy
		 */
		public void setXstsjgzjy(String xstsjgzjy) {
			this.xstsjgzjy = xstsjgzjy;
		}
		/**
		 * @return the txsj
		 */
		public String getTxsj() {
			return txsj;
		}
		/**
		 * @param txsjҪ���õ� txsj
		 */
		public void setTxsj(String txsj) {
			this.txsj = txsj;
		}
		/**
		 * @return the txr
		 */
		public String getTxr() {
			return txr;
		}
		/**
		 * @param txrҪ���õ� txr
		 */
		public void setTxr(String txr) {
			this.txr = txr;
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
		 * @return the splc
		 */
		public String getSplc() {
			return splc;
		}
		/**
		 * @param splcҪ���õ� splc
		 */
		public void setSplc(String splc) {
			this.splc = splc;
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
		

}
