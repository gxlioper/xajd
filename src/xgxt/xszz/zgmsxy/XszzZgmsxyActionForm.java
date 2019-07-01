
package xgxt.xszz.zgmsxy;

import org.apache.struts.action.ActionForm;

import xgxt.form.User;
import xgxt.utils.Pages;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 中国美术学院ActionForm</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-12-16</p>
 */
public class XszzZgmsxyActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Pages pages=new Pages();
	
	User user=new User();

	private String dkyh;//贷款银行
	
	private String dknx;//贷款年限
	
	private String dkyhdd;//贷款银行地点
	
	private String xn;
	private String xh;
	private String xm;
	private String xb;
	private String sfzh;
	private String csny;
	private String mzmc;
	private String zzmmmc;
	private String nj;
	private String xydm;
	private String xymc;
	private String zydm;
	private String zymc;
	private String bjdm;
	private String bjmc;
	private String rxqhk;
	private String byxx;
	private String jtrks;
	private String jtrjnsr;
	private String qs;
	private String xslxdh;
	private String grtc;
	private String sfgc;
	private String sfdq;
	private String sflszn;
	private String jt_xxtxdz;
	private String jt_yzbm;
	private String jt_lxdh;
	private String jtcy1_xm;
	private String jtcy1_nl;
	private String jtcy1_gx;
	private String jtcy1_gzdw;
	private String jtcy1_zy;
	private String jtcy1_sr;
	private String jtcy1_jkzk;
	private String jtcy2_xm;
	private String jtcy2_nl;
	private String jtcy2_gx;
	private String jtcy2_gzdw;
	private String jtcy2_zy;
	private String jtcy2_sr;
	private String jtcy2_jkzk;
	private String jtcy3_xm;
	private String jtcy3_nl;
	private String jtcy3_gx;
	private String jtcy3_gzdw;
	private String jtcy3_zy;
	private String jtcy3_sr;
	private String jtcy3_jkzk;
	private String jtcy4_xm;
	private String jtcy4_nl;
	private String jtcy4_gx;
	private String jtcy4_gzdw;
	private String jtcy4_zy;
	private String jtcy4_sr;
	private String jtcy4_jkzk;
	private String jtcy5_xm;
	private String jtcy5_nl;
	private String jtcy5_gx;
	private String jtcy5_gzdw;
	private String jtcy5_zy;
	private String jtcy5_sr;
	private String jtcy5_jkzk;
	private String jtcy6_xm;
	private String jtcy6_nl;
	private String jtcy6_gx;
	private String jtcy6_gzdw;
	private String jtcy6_zy;
	private String jtcy6_sr;
	private String jtcy6_jkzk;
	private String jtqnsr;
	private String xybxnyhzzqk;
	private String jtzszrzhqk;
	private String jtzstfywsj;
	private String jtcyycjssldl;
	private String jtcysyqk;
	private String jtqzqk;
	private String qtqk;
	private String dddbbz;
	private String mzbm_xxtxdz;
	private String mzbm_yzbm;
	private String mzbm_lxdh;
	private String sqly;
	private String sqsj;
	private String mzpyjg;
	private String csly;
	private String xysh;
	private String xyshyj;
	private String xxsh;
	private String xxshyj;
	
	private String nd;

	private String sqje;
	private String hth ;
	private String dkqx;
	private String htffje;
	private String sjffrq;
	private String dkzl;
	private String htye;
	private String grzhdkzh;
	private String hzdwmc;
	private String jqbj;
	private String jqlx;
	private String zhkzh ;
	private String dkxt;
	private String wyys;
	private String schkr;
	private String sffk;
	
	private String lxdh;          
	private String jtzz;          
	private String yzbm;          
	private String fqxm;          
	private String fqgzdw;        
	private String fqysr;         
	private String fqdh;          
	private String mqxm;          
	private String mqgzdw;        
	private String mqysr;         
	private String mqdh;          
	private String brjyqxhdw;
	private String jtgddh;        
	private String brdzyxjdzlxfs ;
	private String dqgzdwjdz;     
	private String dqgzdwyb;      
	private String dqgzdwdh;      
	private String lxfsbgqk;      
	private String queryequals_xydm;
	private String xfbz;//学费标准
	private String jtdz;//家庭地址
	private String sjhm;//手机号码
	private String[]pkV;
	private String ffje;//发放金额
	private String fqsfzh;//父亲身份证号
	private String mqsfzh;//母亲身份证号
	private String lxdh2;//联系电话2
	private String lxdh3;//联系电话3
	private String sfzyxzzrq;//身份证有效截止日期（格式：20120606）
	private String dkhj;//贷款金额
	private String xq1;//第一学期
	private String xq2;//第二学期
	private String xq3;//第三学期
	private String xq4;//第四学期
	private String xq5;//第五学期
	private String xfje;//学费金额
	private String zsfje;//住宿费金额
	private String qq;//QQ
	private String email;//Email
	private String yxsh;//院系审核
	private String yxshyj;//院系审核意见
	//江西理工大学个性化begin
	private String dkxn;//贷款学年
	private String xf;//学费
	private String zsf;//住宿费
	//江西理工大学个性化end
	
	private String jzrxm;     //见证人姓名
	private String jzrlxdh;     //见证人联系电话
	private String yjzrgx;     //与见证人关系
	private String jzrzz;     //见证人住址
	private String dyxnzsf;     //第一学年住宿费
	private String dexnshf;     //第二学年生活费
	private String dsixnzsf;     //第四学年住宿费
	private String dsixnshf;     //第四学年生活费
	private String dexnzsf;     //第二学年住宿费
	private String dyxnshf;     //第一学年生活费
	private String dsanxnshf;     //第三学年生活费
	private String dsanxnzsf;     //第三学年住宿费
	private String dwuxnshf;     //第五学年生活费
	private String dwuxnzsf;     //第五学年住宿费
	
	private String dyxnxf;    // 第一学年学费
	private String dexnxf;    // 第二学年学费
	private String dsanxnxf;  // 第三学年学费
	private String dsixnxf;   // 第四学年学费
	private String dwuxnxf;   // 第五学年学费
	
	private boolean bzrQx;
	
	private boolean fdyQx;
	
	public boolean isBzrQx() {
		return bzrQx;
	}
	public void setBzrQx(boolean bzrQx) {
		this.bzrQx = bzrQx;
	}
	public boolean isFdyQx() {
		return fdyQx;
	}
	public void setFdyQx(boolean fdyQx) {
		this.fdyQx = fdyQx;
	}
	public String[] getPkV() {
		return pkV;
	}
	public void setPkV(String[] pkV) {
		this.pkV = pkV;
	}
	public String getJtdz() {
		return jtdz;
	}
	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}
	public String getSjhm() {
		return sjhm;
	}
	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}
	public String getXfbz() {
		return xfbz;
	}
	public void setXfbz(String xfbz) {
		this.xfbz = xfbz;
	}
	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}
	public void setQueryequals_xydm(String queryequals_xydm) {
		this.queryequals_xydm = queryequals_xydm;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getByxx() {
		return byxx;
	}
	public void setByxx(String byxx) {
		this.byxx = byxx;
	}
	public String getCsly() {
		return csly;
	}
	public void setCsly(String csly) {
		this.csly = csly;
	}
	public String getCsny() {
		return csny;
	}
	public void setCsny(String csny) {
		this.csny = csny;
	}
	public String getDddbbz() {
		return dddbbz;
	}
	public void setDddbbz(String dddbbz) {
		this.dddbbz = dddbbz;
	}
	public String getGrtc() {
		return grtc;
	}
	public void setGrtc(String grtc) {
		this.grtc = grtc;
	}
	public String getJt_lxdh() {
		return jt_lxdh;
	}
	public void setJt_lxdh(String jt_lxdh) {
		this.jt_lxdh = jt_lxdh;
	}
	public String getJt_xxtxdz() {
		return jt_xxtxdz;
	}
	public void setJt_xxtxdz(String jt_xxtxdz) {
		this.jt_xxtxdz = jt_xxtxdz;
	}
	public String getJt_yzbm() {
		return jt_yzbm;
	}
	public void setJt_yzbm(String jt_yzbm) {
		this.jt_yzbm = jt_yzbm;
	}
	public String getJtcy1_gx() {
		return jtcy1_gx;
	}
	public void setJtcy1_gx(String jtcy1_gx) {
		this.jtcy1_gx = jtcy1_gx;
	}
	public String getJtcy1_gzdw() {
		return jtcy1_gzdw;
	}
	public void setJtcy1_gzdw(String jtcy1_gzdw) {
		this.jtcy1_gzdw = jtcy1_gzdw;
	}
	public String getJtcy1_jkzk() {
		return jtcy1_jkzk;
	}
	public void setJtcy1_jkzk(String jtcy1_jkzk) {
		this.jtcy1_jkzk = jtcy1_jkzk;
	}
	public String getJtcy1_nl() {
		return jtcy1_nl;
	}
	public void setJtcy1_nl(String jtcy1_nl) {
		this.jtcy1_nl = jtcy1_nl;
	}
	public String getJtcy1_sr() {
		return jtcy1_sr;
	}
	public void setJtcy1_sr(String jtcy1_sr) {
		this.jtcy1_sr = jtcy1_sr;
	}
	public String getJtcy1_xm() {
		return jtcy1_xm;
	}
	public void setJtcy1_xm(String jtcy1_xm) {
		this.jtcy1_xm = jtcy1_xm;
	}
	public String getJtcy1_zy() {
		return jtcy1_zy;
	}
	public void setJtcy1_zy(String jtcy1_zy) {
		this.jtcy1_zy = jtcy1_zy;
	}
	public String getJtcy2_gx() {
		return jtcy2_gx;
	}
	public void setJtcy2_gx(String jtcy2_gx) {
		this.jtcy2_gx = jtcy2_gx;
	}
	public String getJtcy2_gzdw() {
		return jtcy2_gzdw;
	}
	public void setJtcy2_gzdw(String jtcy2_gzdw) {
		this.jtcy2_gzdw = jtcy2_gzdw;
	}
	public String getJtcy2_jkzk() {
		return jtcy2_jkzk;
	}
	public void setJtcy2_jkzk(String jtcy2_jkzk) {
		this.jtcy2_jkzk = jtcy2_jkzk;
	}
	public String getJtcy2_nl() {
		return jtcy2_nl;
	}
	public void setJtcy2_nl(String jtcy2_nl) {
		this.jtcy2_nl = jtcy2_nl;
	}
	public String getJtcy2_sr() {
		return jtcy2_sr;
	}
	public void setJtcy2_sr(String jtcy2_sr) {
		this.jtcy2_sr = jtcy2_sr;
	}
	public String getJtcy2_xm() {
		return jtcy2_xm;
	}
	public void setJtcy2_xm(String jtcy2_xm) {
		this.jtcy2_xm = jtcy2_xm;
	}
	public String getJtcy2_zy() {
		return jtcy2_zy;
	}
	public void setJtcy2_zy(String jtcy2_zy) {
		this.jtcy2_zy = jtcy2_zy;
	}
	public String getJtcy3_gx() {
		return jtcy3_gx;
	}
	public void setJtcy3_gx(String jtcy3_gx) {
		this.jtcy3_gx = jtcy3_gx;
	}
	public String getJtcy3_gzdw() {
		return jtcy3_gzdw;
	}
	public void setJtcy3_gzdw(String jtcy3_gzdw) {
		this.jtcy3_gzdw = jtcy3_gzdw;
	}
	public String getJtcy3_jkzk() {
		return jtcy3_jkzk;
	}
	public void setJtcy3_jkzk(String jtcy3_jkzk) {
		this.jtcy3_jkzk = jtcy3_jkzk;
	}
	public String getJtcy3_nl() {
		return jtcy3_nl;
	}
	public void setJtcy3_nl(String jtcy3_nl) {
		this.jtcy3_nl = jtcy3_nl;
	}
	public String getJtcy3_sr() {
		return jtcy3_sr;
	}
	public void setJtcy3_sr(String jtcy3_sr) {
		this.jtcy3_sr = jtcy3_sr;
	}
	public String getJtcy3_xm() {
		return jtcy3_xm;
	}
	public void setJtcy3_xm(String jtcy3_xm) {
		this.jtcy3_xm = jtcy3_xm;
	}
	public String getJtcy3_zy() {
		return jtcy3_zy;
	}
	public void setJtcy3_zy(String jtcy3_zy) {
		this.jtcy3_zy = jtcy3_zy;
	}
	public String getJtcy4_gx() {
		return jtcy4_gx;
	}
	public void setJtcy4_gx(String jtcy4_gx) {
		this.jtcy4_gx = jtcy4_gx;
	}
	public String getJtcy4_gzdw() {
		return jtcy4_gzdw;
	}
	public void setJtcy4_gzdw(String jtcy4_gzdw) {
		this.jtcy4_gzdw = jtcy4_gzdw;
	}
	public String getJtcy4_jkzk() {
		return jtcy4_jkzk;
	}
	public void setJtcy4_jkzk(String jtcy4_jkzk) {
		this.jtcy4_jkzk = jtcy4_jkzk;
	}
	public String getJtcy4_nl() {
		return jtcy4_nl;
	}
	public void setJtcy4_nl(String jtcy4_nl) {
		this.jtcy4_nl = jtcy4_nl;
	}
	public String getJtcy4_sr() {
		return jtcy4_sr;
	}
	public void setJtcy4_sr(String jtcy4_sr) {
		this.jtcy4_sr = jtcy4_sr;
	}
	public String getJtcy4_xm() {
		return jtcy4_xm;
	}
	public void setJtcy4_xm(String jtcy4_xm) {
		this.jtcy4_xm = jtcy4_xm;
	}
	public String getJtcy4_zy() {
		return jtcy4_zy;
	}
	public void setJtcy4_zy(String jtcy4_zy) {
		this.jtcy4_zy = jtcy4_zy;
	}
	public String getJtcy5_gx() {
		return jtcy5_gx;
	}
	public void setJtcy5_gx(String jtcy5_gx) {
		this.jtcy5_gx = jtcy5_gx;
	}
	public String getJtcy5_gzdw() {
		return jtcy5_gzdw;
	}
	public void setJtcy5_gzdw(String jtcy5_gzdw) {
		this.jtcy5_gzdw = jtcy5_gzdw;
	}
	public String getJtcy5_jkzk() {
		return jtcy5_jkzk;
	}
	public void setJtcy5_jkzk(String jtcy5_jkzk) {
		this.jtcy5_jkzk = jtcy5_jkzk;
	}
	public String getJtcy5_nl() {
		return jtcy5_nl;
	}
	public void setJtcy5_nl(String jtcy5_nl) {
		this.jtcy5_nl = jtcy5_nl;
	}
	public String getJtcy5_sr() {
		return jtcy5_sr;
	}
	public void setJtcy5_sr(String jtcy5_sr) {
		this.jtcy5_sr = jtcy5_sr;
	}
	public String getJtcy5_xm() {
		return jtcy5_xm;
	}
	public void setJtcy5_xm(String jtcy5_xm) {
		this.jtcy5_xm = jtcy5_xm;
	}
	public String getJtcy5_zy() {
		return jtcy5_zy;
	}
	public void setJtcy5_zy(String jtcy5_zy) {
		this.jtcy5_zy = jtcy5_zy;
	}
	public String getJtcy6_gx() {
		return jtcy6_gx;
	}
	public void setJtcy6_gx(String jtcy6_gx) {
		this.jtcy6_gx = jtcy6_gx;
	}
	public String getJtcy6_gzdw() {
		return jtcy6_gzdw;
	}
	public void setJtcy6_gzdw(String jtcy6_gzdw) {
		this.jtcy6_gzdw = jtcy6_gzdw;
	}
	public String getJtcy6_jkzk() {
		return jtcy6_jkzk;
	}
	public void setJtcy6_jkzk(String jtcy6_jkzk) {
		this.jtcy6_jkzk = jtcy6_jkzk;
	}
	public String getJtcy6_nl() {
		return jtcy6_nl;
	}
	public void setJtcy6_nl(String jtcy6_nl) {
		this.jtcy6_nl = jtcy6_nl;
	}
	public String getJtcy6_sr() {
		return jtcy6_sr;
	}
	public void setJtcy6_sr(String jtcy6_sr) {
		this.jtcy6_sr = jtcy6_sr;
	}
	public String getJtcy6_xm() {
		return jtcy6_xm;
	}
	public void setJtcy6_xm(String jtcy6_xm) {
		this.jtcy6_xm = jtcy6_xm;
	}
	public String getJtcy6_zy() {
		return jtcy6_zy;
	}
	public void setJtcy6_zy(String jtcy6_zy) {
		this.jtcy6_zy = jtcy6_zy;
	}
	public String getJtcysyqk() {
		return jtcysyqk;
	}
	public void setJtcysyqk(String jtcysyqk) {
		this.jtcysyqk = jtcysyqk;
	}
	public String getJtcyycjssldl() {
		return jtcyycjssldl;
	}
	public void setJtcyycjssldl(String jtcyycjssldl) {
		this.jtcyycjssldl = jtcyycjssldl;
	}
	public String getJtqnsr() {
		return jtqnsr;
	}
	public void setJtqnsr(String jtqnsr) {
		this.jtqnsr = jtqnsr;
	}
	public String getJtqzqk() {
		return jtqzqk;
	}
	public void setJtqzqk(String jtqzqk) {
		this.jtqzqk = jtqzqk;
	}
	public String getJtrjnsr() {
		return jtrjnsr;
	}
	public void setJtrjnsr(String jtrjnsr) {
		this.jtrjnsr = jtrjnsr;
	}
	public String getJtrks() {
		return jtrks;
	}
	public void setJtrks(String jtrks) {
		this.jtrks = jtrks;
	}
	public String getJtzstfywsj() {
		return jtzstfywsj;
	}
	public void setJtzstfywsj(String jtzstfywsj) {
		this.jtzstfywsj = jtzstfywsj;
	}
	public String getJtzszrzhqk() {
		return jtzszrzhqk;
	}
	public void setJtzszrzhqk(String jtzszrzhqk) {
		this.jtzszrzhqk = jtzszrzhqk;
	}
	public String getMzbm_lxdh() {
		return mzbm_lxdh;
	}
	public void setMzbm_lxdh(String mzbm_lxdh) {
		this.mzbm_lxdh = mzbm_lxdh;
	}
	public String getMzbm_xxtxdz() {
		return mzbm_xxtxdz;
	}
	public void setMzbm_xxtxdz(String mzbm_xxtxdz) {
		this.mzbm_xxtxdz = mzbm_xxtxdz;
	}
	public String getMzbm_yzbm() {
		return mzbm_yzbm;
	}
	public void setMzbm_yzbm(String mzbm_yzbm) {
		this.mzbm_yzbm = mzbm_yzbm;
	}
	public String getMzmc() {
		return mzmc;
	}
	public void setMzmc(String mzmc) {
		this.mzmc = mzmc;
	}
	public String getMzpyjg() {
		return mzpyjg;
	}
	public void setMzpyjg(String mzpyjg) {
		this.mzpyjg = mzpyjg;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getQs() {
		return qs;
	}
	public void setQs(String qs) {
		this.qs = qs;
	}
	public String getQtqk() {
		return qtqk;
	}
	public void setQtqk(String qtqk) {
		this.qtqk = qtqk;
	}
	public String getRxqhk() {
		return rxqhk;
	}
	public void setRxqhk(String rxqhk) {
		this.rxqhk = rxqhk;
	}
	public String getSfdq() {
		return sfdq;
	}
	public void setSfdq(String sfdq) {
		this.sfdq = sfdq;
	}
	public String getSfgc() {
		return sfgc;
	}
	public void setSfgc(String sfgc) {
		this.sfgc = sfgc;
	}
	public String getSflszn() {
		return sflszn;
	}
	public void setSflszn(String sflszn) {
		this.sflszn = sflszn;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXslxdh() {
		return xslxdh;
	}
	public void setXslxdh(String xslxdh) {
		this.xslxdh = xslxdh;
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String getXxshyj() {
		return xxshyj;
	}
	public void setXxshyj(String xxshyj) {
		this.xxshyj = xxshyj;
	}
	public String getXybxnyhzzqk() {
		return xybxnyhzzqk;
	}
	public void setXybxnyhzzqk(String xybxnyhzzqk) {
		this.xybxnyhzzqk = xybxnyhzzqk;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getXysh() {
		return xysh;
	}
	public void setXysh(String xysh) {
		this.xysh = xysh;
	}
	public String getXyshyj() {
		return xyshyj;
	}
	public void setXyshyj(String xyshyj) {
		this.xyshyj = xyshyj;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getZzmmmc() {
		return zzmmmc;
	}
	public void setZzmmmc(String zzmmmc) {
		this.zzmmmc = zzmmmc;
	}
	public String getBrdzyxjdzlxfs() {
		return brdzyxjdzlxfs;
	}
	public void setBrdzyxjdzlxfs(String brdzyxjdzlxfs) {
		this.brdzyxjdzlxfs = brdzyxjdzlxfs;
	}
	public String getBrjyqxhdw() {
		return brjyqxhdw;
	}
	public void setBrjyqxhdw(String brjyqxhdw) {
		this.brjyqxhdw = brjyqxhdw;
	}
	public String getDqgzdwdh() {
		return dqgzdwdh;
	}
	public void setDqgzdwdh(String dqgzdwdh) {
		this.dqgzdwdh = dqgzdwdh;
	}
	public String getDqgzdwjdz() {
		return dqgzdwjdz;
	}
	public void setDqgzdwjdz(String dqgzdwjdz) {
		this.dqgzdwjdz = dqgzdwjdz;
	}
	public String getDqgzdwyb() {
		return dqgzdwyb;
	}
	public void setDqgzdwyb(String dqgzdwyb) {
		this.dqgzdwyb = dqgzdwyb;
	}
	public String getFqdh() {
		return fqdh;
	}
	public void setFqdh(String fqdh) {
		this.fqdh = fqdh;
	}
	public String getFqgzdw() {
		return fqgzdw;
	}
	public void setFqgzdw(String fqgzdw) {
		this.fqgzdw = fqgzdw;
	}
	public String getFqxm() {
		return fqxm;
	}
	public void setFqxm(String fqxm) {
		this.fqxm = fqxm;
	}
	public String getFqysr() {
		return fqysr;
	}
	public void setFqysr(String fqysr) {
		this.fqysr = fqysr;
	}
	public String getJtgddh() {
		return jtgddh;
	}
	public void setJtgddh(String jtgddh) {
		this.jtgddh = jtgddh;
	}
	public String getJtzz() {
		return jtzz;
	}
	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getLxfsbgqk() {
		return lxfsbgqk;
	}
	public void setLxfsbgqk(String lxfsbgqk) {
		this.lxfsbgqk = lxfsbgqk;
	}
	public String getMqdh() {
		return mqdh;
	}
	public void setMqdh(String mqdh) {
		this.mqdh = mqdh;
	}
	public String getMqgzdw() {
		return mqgzdw;
	}
	public void setMqgzdw(String mqgzdw) {
		this.mqgzdw = mqgzdw;
	}
	public String getMqxm() {
		return mqxm;
	}
	public void setMqxm(String mqxm) {
		this.mqxm = mqxm;
	}
	public String getMqysr() {
		return mqysr;
	}
	public void setMqysr(String mqysr) {
		this.mqysr = mqysr;
	}
	public String getYzbm() {
		return yzbm;
	}
	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}
	public String getDkqx() {
		return dkqx;
	}
	public void setDkqx(String dkqx) {
		this.dkqx = dkqx;
	}
	public String getDkxt() {
		return dkxt;
	}
	public void setDkxt(String dkxt) {
		this.dkxt = dkxt;
	}
	public String getDkzl() {
		return dkzl;
	}
	public void setDkzl(String dkzl) {
		this.dkzl = dkzl;
	}
	public String getGrzhdkzh() {
		return grzhdkzh;
	}
	public void setGrzhdkzh(String grzhdkzh) {
		this.grzhdkzh = grzhdkzh;
	}
	public String getHtffje() {
		return htffje;
	}
	public void setHtffje(String htffje) {
		this.htffje = htffje;
	}
	public String getHth() {
		return hth;
	}
	public void setHth(String hth) {
		this.hth = hth;
	}
	public String getHtye() {
		return htye;
	}
	public void setHtye(String htye) {
		this.htye = htye;
	}
	public String getHzdwmc() {
		return hzdwmc;
	}
	public void setHzdwmc(String hzdwmc) {
		this.hzdwmc = hzdwmc;
	}
	public String getJqbj() {
		return jqbj;
	}
	public void setJqbj(String jqbj) {
		this.jqbj = jqbj;
	}
	public String getJqlx() {
		return jqlx;
	}
	public void setJqlx(String jqlx) {
		this.jqlx = jqlx;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getSchkr() {
		return schkr;
	}
	public void setSchkr(String schkr) {
		this.schkr = schkr;
	}
	public String getSffk() {
		return sffk;
	}
	public void setSffk(String sffk) {
		this.sffk = sffk;
	}
	public String getSjffrq() {
		return sjffrq;
	}
	public void setSjffrq(String sjffrq) {
		this.sjffrq = sjffrq;
	}
	public String getSqje() {
		return sqje;
	}
	public void setSqje(String sqje) {
		this.sqje = sqje;
	}
	public String getWyys() {
		return wyys;
	}
	public void setWyys(String wyys) {
		this.wyys = wyys;
	}
	public String getZhkzh() {
		return zhkzh;
	}
	public void setZhkzh(String zhkzh) {
		this.zhkzh = zhkzh;
	}
	public String getDknx() {
		return dknx;
	}
	public void setDknx(String dknx) {
		this.dknx = dknx;
	}
	public String getDkyh() {
		return dkyh;
	}
	public void setDkyh(String dkyh) {
		this.dkyh = dkyh;
	}
	public String getDkyhdd() {
		return dkyhdd;
	}
	public void setDkyhdd(String dkyhdd) {
		this.dkyhdd = dkyhdd;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getFfje() {
		return ffje;
	}
	public void setFfje(String ffje) {
		this.ffje = ffje;
	}
	public String getFqsfzh() {
		return fqsfzh;
	}
	public void setFqsfzh(String fqsfzh) {
		this.fqsfzh = fqsfzh;
	}
	public String getMqsfzh() {
		return mqsfzh;
	}
	public void setMqsfzh(String mqsfzh) {
		this.mqsfzh = mqsfzh;
	}
	public String getLxdh2() {
		return lxdh2;
	}
	public void setLxdh2(String lxdh2) {
		this.lxdh2 = lxdh2;
	}
	public String getLxdh3() {
		return lxdh3;
	}
	public void setLxdh3(String lxdh3) {
		this.lxdh3 = lxdh3;
	}
	public String getSfzyxzzrq() {
		return sfzyxzzrq;
	}
	public void setSfzyxzzrq(String sfzyxzzrq) {
		this.sfzyxzzrq = sfzyxzzrq;
	}
	public String getDkhj() {
		return dkhj;
	}
	public void setDkhj(String dkhj) {
		this.dkhj = dkhj;
	}
	public String getXq1() {
		return xq1;
	}
	public void setXq1(String xq1) {
		this.xq1 = xq1;
	}
	public String getXq2() {
		return xq2;
	}
	public void setXq2(String xq2) {
		this.xq2 = xq2;
	}
	public String getXq3() {
		return xq3;
	}
	public void setXq3(String xq3) {
		this.xq3 = xq3;
	}
	public String getXq4() {
		return xq4;
	}
	public void setXq4(String xq4) {
		this.xq4 = xq4;
	}
	public String getXq5() {
		return xq5;
	}
	public void setXq5(String xq5) {
		this.xq5 = xq5;
	}
	public String getXfje() {
		return xfje;
	}
	public void setXfje(String xfje) {
		this.xfje = xfje;
	}
	public String getZsfje() {
		return zsfje;
	}
	public void setZsfje(String zsfje) {
		this.zsfje = zsfje;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getYxsh() {
		return yxsh;
	}
	public void setYxsh(String yxsh) {
		this.yxsh = yxsh;
	}
	public String getYxshyj() {
		return yxshyj;
	}
	public void setYxshyj(String yxshyj) {
		this.yxshyj = yxshyj;
	}
	public String getDkxn() {
		return dkxn;
	}
	public void setDkxn(String dkxn) {
		this.dkxn = dkxn;
	}
	public String getXf() {
		return xf;
	}
	public void setXf(String xf) {
		this.xf = xf;
	}
	public String getZsf() {
		return zsf;
	}
	public void setZsf(String zsf) {
		this.zsf = zsf;
	}
	public String getDexnshf() {
		return dexnshf;
	}
	public void setDexnshf(String dexnshf) {
		this.dexnshf = dexnshf;
	}
	public String getDexnzsf() {
		return dexnzsf;
	}
	public void setDexnzsf(String dexnzsf) {
		this.dexnzsf = dexnzsf;
	}
	public String getDsanxnshf() {
		return dsanxnshf;
	}
	public void setDsanxnshf(String dsanxnshf) {
		this.dsanxnshf = dsanxnshf;
	}
	public String getDsixnshf() {
		return dsixnshf;
	}
	public void setDsixnshf(String dsixnshf) {
		this.dsixnshf = dsixnshf;
	}
	public String getDsixnzsf() {
		return dsixnzsf;
	}
	public void setDsixnzsf(String dsixnzsf) {
		this.dsixnzsf = dsixnzsf;
	}
	public String getDyxnshf() {
		return dyxnshf;
	}
	public void setDyxnshf(String dyxnshf) {
		this.dyxnshf = dyxnshf;
	}
	public String getDyxnzsf() {
		return dyxnzsf;
	}
	public void setDyxnzsf(String dyxnzsf) {
		this.dyxnzsf = dyxnzsf;
	}
	public String getJzrlxdh() {
		return jzrlxdh;
	}
	public void setJzrlxdh(String jzrlxdh) {
		this.jzrlxdh = jzrlxdh;
	}
	public String getJzrxm() {
		return jzrxm;
	}
	public void setJzrxm(String jzrxm) {
		this.jzrxm = jzrxm;
	}
	public String getJzrzz() {
		return jzrzz;
	}
	public void setJzrzz(String jzrzz) {
		this.jzrzz = jzrzz;
	}
	public String getYjzrgx() {
		return yjzrgx;
	}
	public void setYjzrgx(String yjzrgx) {
		this.yjzrgx = yjzrgx;
	}
	public String getDsanxnzsf() {
		return dsanxnzsf;
	}
	public void setDsanxnzsf(String dsanxnzsf) {
		this.dsanxnzsf = dsanxnzsf;
	}
	public String getDexnxf() {
		return dexnxf;
	}
	public void setDexnxf(String dexnxf) {
		this.dexnxf = dexnxf;
	}
	public String getDsanxnxf() {
		return dsanxnxf;
	}
	public void setDsanxnxf(String dsanxnxf) {
		this.dsanxnxf = dsanxnxf;
	}
	public String getDsixnxf() {
		return dsixnxf;
	}
	public void setDsixnxf(String dsixnxf) {
		this.dsixnxf = dsixnxf;
	}
	public String getDyxnxf() {
		return dyxnxf;
	}
	public void setDyxnxf(String dyxnxf) {
		this.dyxnxf = dyxnxf;
	}
	public String getDwuxnshf() {
		return dwuxnshf;
	}
	public void setDwuxnshf(String dwuxnshf) {
		this.dwuxnshf = dwuxnshf;
	}
	public String getDwuxnzsf() {
		return dwuxnzsf;
	}
	public void setDwuxnzsf(String dwuxnzsf) {
		this.dwuxnzsf = dwuxnzsf;
	}
	public String getDwuxnxf() {
		return dwuxnxf;
	}
	public void setDwuxnxf(String dwuxnxf) {
		this.dwuxnxf = dwuxnxf;
	}
	
}
