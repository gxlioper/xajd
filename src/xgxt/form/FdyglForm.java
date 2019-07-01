/*
 * 创建日期 2007-1-22  bat_zzj
 *
 */
package xgxt.form;

import org.apache.struts.action.ActionForm;

import xgxt.base.DealString;
import xgxt.utils.Pages;

/** 辅导员管理 */
public class FdyglForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mxdx;     
	private String nd;       //年度
	private String[] pfbl;   
	private String[] pfbz;
	private String pjh;
	private String pjnr;
	private String[] qtdm;
	private String qz;
	private String[] sfcp;
	private String xn;//学年
	private String xq;//学期
	private String xydm;//学期代码
	private String bzbh; //
	private String bzmc; //
	private String pfxm; 
	private String dyfz; 
	private String xssx;
	private String zgh; //职工号
	private String nj;//年级
	private String zydm;//专业代码
	private String fdyxm;//辅导员姓名
	private String zxm;//
	private String[] bjdm;//班级代码
	private String[] pjfs;//
	private String sfyqx;//
	private String xm;//姓名
	private String message;//
	private String idIndex;//问卷问题列
	private String bz;//问题备注
	private String stmc;//问题名称
	private String xxl;//问题选项列
	private String sfkf;//问卷是否开放
	private String bmdm;//部门代码
	private String pkValue = "";
	private String xb = "";//性别
	private String lxdh = "";//联系电话
	private String zw = "";//职务
	private String zzmm = "";//政治面貌
	private String xl = "";//学历
	private String csrq ="";//出生日期
	private String grhjqk ="";//个人获奖情况
	private String gzjl ="";//工作经历
	private String zyzz = "";//
	private String mz ="";//名族
	private String zc = "";//职称
	private String lxgzsj = "";//入校工作时间
	private String xsgzyjfx = "";//学生工作研究方向 
	private String jtzz = "";//家庭住址
	private String yddh = "";  //移动电话
	private String dzyx = "";// 电子邮箱 
	private String fblw = "";//发表论文
	private String kyjl = "";//科研经历 
	private String jg ="";//籍贯
	private String byyx = "";//
	private String sxzy = "";//
	private String yzbm = "";//
	private String bksxzy = "";  //本科所学专业
	private String bkbyyx = "";  //本科毕业院校
	private String ssbyzy = "";  //硕士所学专业
	private String ssbyyx = "";  //硕士毕业院校
	private String bsbyzy = "";  //博士所学专业
	private String bsbyyx = "";  //博士毕业院校
	private String xw = "";     //学位
	private String rwsj = ""; //
	private String gzfg = ""; //工作分工
	private String zwrzsj = "";//任职时间   
	private String jsrzsj = "";//技术任职时间    
	private String sjdw = ""; //上级单位    
	private String txdz ="";  //通讯地址
	private String bgdh = ""; //办公电话
	private String cz = "";   //传真
	private String jrgz = ""; //兼任工作
	private String djsj = "";     //定级时间
	private String zdm = "";   //组代码
	private String jtzb = "";  //具体指标
	private String stlbdm = "";   //试题类别名称
	private String scojzb =""; //中国矿业二级指标
	private String[] khqzdm;  //考核所属群组代码
	private String fz;  //考核题目分值
	/*下面为武汉理工大学特有Begin*/
	private String sfzh = "";     //身份证号
	private String cjgzrq = "";   //参加工作日期
	private String zhxwssxk = ""; //最后学位所属学科
	private String csgz = "";     //从事工作
	private String pxqk = "";     //培训情况
	private String sfbl = "";     //是否B类
	private String jb = "";       //级别
	private String szgzsj = "";   //从事思政工作时间
	private String szdwbb = "";
	private String sftp = "";     //是否投票
	private String xh = "";       //学号
	private String bj;//班级代码
	private String cxZgh;//查询的职工号
	private String cxXm;//查询的辅导员姓名
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
		sfzh = DealString.toGBK(sfzh);          //身份证号
		cjgzrq = DealString.toGBK(cjgzrq);      //参加工作日期
		sfbl = DealString.toGBK(sfbl);          //是否B类
		zhxwssxk = DealString.toGBK(zhxwssxk);  //最后学位所属学科
		csgz = DealString.toGBK(csgz);          //从事工作
		pxqk = DealString.toGBK(pxqk);          //培训情况
		bksxzy = DealString.toGBK(bksxzy);  //本科所学专业
		bkbyyx = DealString.toGBK(bkbyyx);  //本科毕业院校
		ssbyzy = DealString.toGBK(ssbyzy);  //硕士所学专业
		ssbyyx = DealString.toGBK(ssbyyx);  //硕士毕业院校
		bsbyzy = DealString.toGBK(bsbyzy);  //博士所学专业
		bsbyyx = DealString.toGBK(bsbyyx);  //博士毕业院校
		jb     = DealString.toGBK(jb);          //级别
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
