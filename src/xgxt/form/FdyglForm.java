/*
 * �������� 2007-1-22  bat_zzj
 *
 */
package xgxt.form;

import org.apache.struts.action.ActionForm;

import xgxt.base.DealString;
import xgxt.utils.Pages;

/** ����Ա���� */
public class FdyglForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mxdx;     
	private String nd;       //���
	private String[] pfbl;   
	private String[] pfbz;
	private String pjh;
	private String pjnr;
	private String[] qtdm;
	private String qz;
	private String[] sfcp;
	private String xn;//ѧ��
	private String xq;//ѧ��
	private String xydm;//ѧ�ڴ���
	private String bzbh; //
	private String bzmc; //
	private String pfxm; 
	private String dyfz; 
	private String xssx;
	private String zgh; //ְ����
	private String nj;//�꼶
	private String zydm;//רҵ����
	private String fdyxm;//����Ա����
	private String zxm;//
	private String[] bjdm;//�༶����
	private String[] pjfs;//
	private String sfyqx;//
	private String xm;//����
	private String message;//
	private String idIndex;//�ʾ�������
	private String bz;//���ⱸע
	private String stmc;//��������
	private String xxl;//����ѡ����
	private String sfkf;//�ʾ��Ƿ񿪷�
	private String bmdm;//���Ŵ���
	private String pkValue = "";
	private String xb = "";//�Ա�
	private String lxdh = "";//��ϵ�绰
	private String zw = "";//ְ��
	private String zzmm = "";//������ò
	private String xl = "";//ѧ��
	private String csrq ="";//��������
	private String grhjqk ="";//���˻����
	private String gzjl ="";//��������
	private String zyzz = "";//
	private String mz ="";//����
	private String zc = "";//ְ��
	private String lxgzsj = "";//��У����ʱ��
	private String xsgzyjfx = "";//ѧ�������о����� 
	private String jtzz = "";//��ͥסַ
	private String yddh = "";  //�ƶ��绰
	private String dzyx = "";// �������� 
	private String fblw = "";//��������
	private String kyjl = "";//���о��� 
	private String jg ="";//����
	private String byyx = "";//
	private String sxzy = "";//
	private String yzbm = "";//
	private String bksxzy = "";  //������ѧרҵ
	private String bkbyyx = "";  //���Ʊ�ҵԺУ
	private String ssbyzy = "";  //˶ʿ��ѧרҵ
	private String ssbyyx = "";  //˶ʿ��ҵԺУ
	private String bsbyzy = "";  //��ʿ��ѧרҵ
	private String bsbyyx = "";  //��ʿ��ҵԺУ
	private String xw = "";     //ѧλ
	private String rwsj = ""; //
	private String gzfg = ""; //�����ֹ�
	private String zwrzsj = "";//��ְʱ��   
	private String jsrzsj = "";//������ְʱ��    
	private String sjdw = ""; //�ϼ���λ    
	private String txdz ="";  //ͨѶ��ַ
	private String bgdh = ""; //�칫�绰
	private String cz = "";   //����
	private String jrgz = ""; //���ι���
	private String djsj = "";     //����ʱ��
	private String zdm = "";   //�����
	private String jtzb = "";  //����ָ��
	private String stlbdm = "";   //�����������
	private String scojzb =""; //�й���ҵ����ָ��
	private String[] khqzdm;  //��������Ⱥ�����
	private String fz;  //������Ŀ��ֵ
	/*����Ϊ�人����ѧ����Begin*/
	private String sfzh = "";     //���֤��
	private String cjgzrq = "";   //�μӹ�������
	private String zhxwssxk = ""; //���ѧλ����ѧ��
	private String csgz = "";     //���¹���
	private String pxqk = "";     //��ѵ���
	private String sfbl = "";     //�Ƿ�B��
	private String jb = "";       //����
	private String szgzsj = "";   //����˼������ʱ��
	private String szdwbb = "";
	private String sftp = "";     //�Ƿ�ͶƱ
	private String xh = "";       //ѧ��
	private String bj;//�༶����
	private String cxZgh;//��ѯ��ְ����
	private String cxXm;//��ѯ�ĸ���Ա����
	/*End*/
	Pages pages = new Pages();

	public String getSzdwbb() {
		return szdwbb;
	}

	public void setSzdwbb(String szdwbb) {
		this.szdwbb = szdwbb;
	}

	public String getSzgzsj() {
		return szgzsj;
	}

	public void setSzgzsj(String szgzsj) {
		this.szgzsj = szgzsj;
	}

	public String getJb() {
		return jb;
	}

	public void setJb(String jb) {
		this.jb = jb;
	}

	public String getSfbl() {
		return sfbl;
	}

	public void setSfbl(String sfbl) {
		this.sfbl = sfbl;
	}

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getSfkf() {
		return sfkf;
	}

	public void setSfkf(String sfkf) {
		this.sfkf = sfkf;
	}

	public String getXxl() {
		return xxl;
	}

	public void setXxl(String xxl) {
		this.xxl = xxl;
	}

	public String getBzbh() {
		return bzbh;
	}

	public void setBzbh(String bzbh) {
		this.bzbh = bzbh;
	}

	public String getBzmc() {
		return bzmc;
	}

	public void setBzmc(String bzmc) {
		this.bzmc = bzmc;
	}

	public String getDyfz() {
		return dyfz;
	}

	public void setDyfz(String dyfz) {
		this.dyfz = dyfz;
	}

	public String getMxdx() {
		return mxdx;
	}

	public void setMxdx(String mxdx) {
		this.mxdx = mxdx;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String[] getPfbl() {
		return pfbl;
	}

	public void setPfbl(String[] pfbl) {
		this.pfbl = pfbl;
	}

	public String[] getPfbz() {
		return pfbz;
	}

	public void setPfbz(String[] pfbz) {
		this.pfbz = pfbz;
	}

	public String getPfxm() {
		return pfxm;
	}

	public void setPfxm(String pfxm) {
		this.pfxm = pfxm;
	}

	public String getPjh() {
		return pjh;
	}

	public void setPjh(String pjh) {
		this.pjh = pjh;
	}

	public String getPjnr() {
		return pjnr;
	}

	public void setPjnr(String pjnr) {
		this.pjnr = pjnr;
	}

	public String[] getQtdm() {
		return qtdm;
	}

	public void setQtdm(String[] qtdm) {
		this.qtdm = qtdm;
	}

	public String getQz() {
		return qz;
	}

	public void setQz(String qz) {
		this.qz = qz;
	}

	public String[] getSfcp() {
		return sfcp;
	}

	public void setSfcp(String[] sfcp) {
		this.sfcp = sfcp;
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

	public String getXssx() {
		return xssx;
	}

	public void setXssx(String xssx) {
		this.xssx = xssx;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getFdyxm() {
		return fdyxm;
	}

	public void setFdyxm(String fdyxm) {
		this.fdyxm = fdyxm;
	}

	public String[] getBjdm() {
		return bjdm;
	}

	public void setBjdm(String[] bjdm) {
		this.bjdm = bjdm;
	}

	public String[] getPjfs() {
		return pjfs;
	}

	public void setPjfs(String[] pjfs) {
		this.pjfs = pjfs;
	}

	public String getZxm() {
		return zxm;
	}

	public void setZxm(String zxm) {
		this.zxm = zxm;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSfyqx() {
		return sfyqx;
	}

	public void setSfyqx(String sfyqx) {
		this.sfyqx = sfyqx;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getIdIndex() {
		return idIndex;
	}

	public void setIdIndex(String idIndex) {
		this.idIndex = idIndex;
	}

	public String getStmc() {
		return stmc;
	}

	public void setStmc(String stmc) {
		this.stmc = stmc;
	}
	
	public void deal_Whlgdx_GBK(){
		bmdm = DealString.toGBK(bmdm);
		pkValue = DealString.toGBK(pkValue);
		zgh = DealString.toGBK(zgh);
		xm = DealString.toGBK(xm);
		xb = DealString.toGBK(xb);
		lxdh = DealString.toGBK(lxdh);
		zw = DealString.toGBK(zw);
		zzmm = DealString.toGBK(zzmm);
		xl = DealString.toGBK(xl);
		csrq = DealString.toGBK(csrq);
		grhjqk = DealString.toGBK(grhjqk);
		gzjl = DealString.toGBK(gzjl);
		bz = DealString.toGBK(bz);
		zyzz = DealString.toGBK(zyzz);
		mz = DealString.toGBK(mz);
		zc = DealString.toGBK(zc);
		xsgzyjfx = DealString.toGBK(xsgzyjfx);
		jtzz = DealString.toGBK(jtzz);
		yddh = DealString.toGBK(yddh);
		dzyx = DealString.toGBK(dzyx);
		fblw = DealString.toGBK(fblw);
		kyjl = DealString.toGBK(kyjl);
		jg = DealString.toGBK(jg);
		byyx = DealString.toGBK(byyx);
		sxzy = DealString.toGBK(sxzy);
		yzbm = DealString.toGBK(yzbm);
		xw = DealString.toGBK(xw);
		rwsj = DealString.toGBK(rwsj);
		gzfg = DealString.toGBK(gzfg);
		zwrzsj = DealString.toGBK(zwrzsj);
		jsrzsj = DealString.toGBK(jsrzsj);
		sjdw = DealString.toGBK(sjdw);
		txdz = DealString.toGBK(txdz);
		bgdh = DealString.toGBK(bgdh);
		cz = DealString.toGBK(cz);
		jrgz = DealString.toGBK(jrgz);
		sfzh = DealString.toGBK(sfzh);          //���֤��
		cjgzrq = DealString.toGBK(cjgzrq);      //�μӹ�������
		sfbl = DealString.toGBK(sfbl);          //�Ƿ�B��
		zhxwssxk = DealString.toGBK(zhxwssxk);  //���ѧλ����ѧ��
		csgz = DealString.toGBK(csgz);          //���¹���
		pxqk = DealString.toGBK(pxqk);          //��ѵ���
		bksxzy = DealString.toGBK(bksxzy);  //������ѧרҵ
		bkbyyx = DealString.toGBK(bkbyyx);  //���Ʊ�ҵԺУ
		ssbyzy = DealString.toGBK(ssbyzy);  //˶ʿ��ѧרҵ
		ssbyyx = DealString.toGBK(ssbyyx);  //˶ʿ��ҵԺУ
		bsbyzy = DealString.toGBK(bsbyzy);  //��ʿ��ѧרҵ
		bsbyyx = DealString.toGBK(bsbyyx);  //��ʿ��ҵԺУ
		jb     = DealString.toGBK(jb);          //����
	}

	public String getBgdh() {
		return bgdh;
	}

	public void setBgdh(String bgdh) {
		this.bgdh = bgdh;
	}

	public String getByyx() {
		return byyx;
	}

	public void setByyx(String byyx) {
		this.byyx = byyx;
	}

	public String getCsrq() {
		return csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getCz() {
		return cz;
	}

	public void setCz(String cz) {
		this.cz = cz;
	}

	public String getDzyx() {
		return dzyx;
	}

	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}

	public String getFblw() {
		return fblw;
	}

	public void setFblw(String fblw) {
		this.fblw = fblw;
	}

	public String getGrhjqk() {
		return grhjqk;
	}

	public void setGrhjqk(String grhjqk) {
		this.grhjqk = grhjqk;
	}

	public String getGzfg() {
		return gzfg;
	}

	public void setGzfg(String gzfg) {
		this.gzfg = gzfg;
	}

	public String getGzjl() {
		return gzjl;
	}

	public void setGzjl(String gzjl) {
		this.gzjl = gzjl;
	}

	public String getJg() {
		return jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}

	public String getJrgz() {
		return jrgz;
	}

	public void setJrgz(String jrgz) {
		this.jrgz = jrgz;
	}

	public String getJsrzsj() {
		return jsrzsj;
	}

	public void setJsrzsj(String jsrzsj) {
		this.jsrzsj = jsrzsj;
	}

	public String getJtzz() {
		return jtzz;
	}

	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}

	public String getKyjl() {
		return kyjl;
	}

	public void setKyjl(String kyjl) {
		this.kyjl = kyjl;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getLxgzsj() {
		return lxgzsj;
	}

	public void setLxgzsj(String lxgzsj) {
		this.lxgzsj = lxgzsj;
	}

	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String getRwsj() {
		return rwsj;
	}

	public void setRwsj(String rwsj) {
		this.rwsj = rwsj;
	}

	public String getSjdw() {
		return sjdw;
	}

	public void setSjdw(String sjdw) {
		this.sjdw = sjdw;
	}

	public String getSxzy() {
		return sxzy;
	}

	public void setSxzy(String sxzy) {
		this.sxzy = sxzy;
	}

	public String getTxdz() {
		return txdz;
	}

	public void setTxdz(String txdz) {
		this.txdz = txdz;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getXl() {
		return xl;
	}

	public void setXl(String xl) {
		this.xl = xl;
	}

	public String getXsgzyjfx() {
		return xsgzyjfx;
	}

	public void setXsgzyjfx(String xsgzyjfx) {
		this.xsgzyjfx = xsgzyjfx;
	}

	public String getXw() {
		return xw;
	}

	public void setXw(String xw) {
		this.xw = xw;
	}

	public String getYddh() {
		return yddh;
	}

	public void setYddh(String yddh) {
		this.yddh = yddh;
	}

	public String getYzbm() {
		return yzbm;
	}

	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}

	public String getZc() {
		return zc;
	}

	public void setZc(String zc) {
		this.zc = zc;
	}

	public String getZw() {
		return zw;
	}

	public void setZw(String zw) {
		this.zw = zw;
	}

	public String getZwrzsj() {
		return zwrzsj;
	}

	public void setZwrzsj(String zwrzsj) {
		this.zwrzsj = zwrzsj;
	}

	public String getZyzz() {
		return zyzz;
	}

	public void setZyzz(String zyzz) {
		this.zyzz = zyzz;
	}

	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	public String getCjgzrq() {
		return cjgzrq;
	}

	public void setCjgzrq(String cjgzrq) {
		this.cjgzrq = cjgzrq;
	}

	public String getCsgz() {
		return csgz;
	}

	public void setCsgz(String csgz) {
		this.csgz = csgz;
	}

	public String getPxqk() {
		return pxqk;
	}

	public void setPxqk(String pxqk) {
		this.pxqk = pxqk;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getZhxwssxk() {
		return zhxwssxk;
	}

	public void setZhxwssxk(String zhxwssxk) {
		this.zhxwssxk = zhxwssxk;
	}
	
	public String getDjsj() {
		return djsj;
	}

	public void setDjsj(String djsj) {
		this.djsj = djsj;
	}

	public String getBksxzy() {
		return bksxzy;
	}

	public void setBksxzy(String bksxzy) {
		this.bksxzy = bksxzy;
	}

	public String getBkbyyx() {
		return bkbyyx;
	}

	public void setBkbyyx(String bkbyyx) {
		this.bkbyyx = bkbyyx;
	}

	public String getBsbyyx() {
		return bsbyyx;
	}

	public void setBsbyyx(String bsbyyx) {
		this.bsbyyx = bsbyyx;
	}

	public String getSsbyyx() {
		return ssbyyx;
	}

	public void setSsbyyx(String ssbyyx) {
		this.ssbyyx = ssbyyx;
	}

	public String getBsbyzy() {
		return bsbyzy;
	}

	public void setBsbyzy(String bsbyzy) {
		this.bsbyzy = bsbyzy;
	}

	public String getSsbyzy() {
		return ssbyzy;
	}

	public void setSsbyzy(String ssbyzy) {
		this.ssbyzy = ssbyzy;
	}

	public String getZdm() {
		return zdm;
	}

	public void setZdm(String zdm) {
		this.zdm = zdm;
	}

	public String getJtzb() {
		return jtzb;
	}

	public void setJtzb(String jtzb) {
		this.jtzb = jtzb;
	}

	public String getStlbdm() {
		return stlbdm;
	}

	public void setStlbdm(String stlbdm) {
		this.stlbdm = stlbdm;
	}

	public String getScojzb() {
		return scojzb;
	}

	public void setScojzb(String scojzb) {
		this.scojzb = scojzb;
	}

	public String[] getKhqzdm() {
		return khqzdm;
	}

	public void setKhqzdm(String[] khqzdm) {
		this.khqzdm = khqzdm;
	}

	public String getFz() {
		return fz;
	}

	public void setFz(String fz) {
		this.fz = fz;
	}

	public String getSftp() {
		return sftp;
	}

	public void setSftp(String sftp) {
		this.sftp = sftp;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getBj() {
		return bj;
	}

	public void setBj(String bj) {
		this.bj = bj;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public void setCxZgh(String cxZgh) {
		this.cxZgh = cxZgh;
	}

	public String getCxZgh() {
		return cxZgh;
	}

	public void setCxXm(String cxXm) {
		this.cxXm = cxXm;
	}

	public String getCxXm() {
		return cxXm;
	}

}
