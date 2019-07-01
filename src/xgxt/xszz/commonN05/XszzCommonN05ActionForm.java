package xgxt.xszz.commonN05;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.utils.Pages;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description: N05ActionForm
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: 周觅
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2009-10-13
 * </p>
 */

public class XszzCommonN05ActionForm extends ActionForm {

	FormFile uploadFile;// 上传文件
	
	// 年度
	private String nd;

	// 收费项目
	private String[] xfsfxm;

	// 应缴金额
	private String[] xfyjje;

	// 实缴金额
	private String[] xfsjje;

	// 是否欠费
	private String[] xfsfqf;

	// 减免金额
	private String[] xfjmje;

	private String save_rdbh;

	private String queryequals_rdbh;

	private String save_sqdc;

	private String queryequals_sqdc;

	private String save_spdc;

	private String queryequals_spdc;

	private String save_pddd;

	private String queryequals_pddd;

	private String save_gczm;

	private String queryequals_gczm;

	private String save_ssmzzm;

	private String queryequals_ssmzzm;

	private String save_lsznzm;

	private String queryequals_lsznzm;

	private String save_yfjtzm;

	private String queryequals_yfjtzm;

	private String save_qtzm;

	private String queryequals_qtzm;

	private String save_bjpy;

	private String queryequals_bjpy;

	private String save_xygsjg;

	private String queryequals_xygsjg;

	private String save_jmje;

	private String queryequals_jmje;

	/**
	 * 
	 */
	private String querylike_xh;

	private String querylike_xm;

	private String queryequals_nj;

	private String queryequals_xydm;

	private String queryequals_zydm;

	private String queryequals_bjdm;

	private String save_xn;

	private String queryequals_xn;

	private String save_dydd;

	private String queryequals_dydd;

	private String save_hjjl;

	private String queryequals_hjjl;

	private String save_ljbjgbxkms;

	private String queryequals_ljbjgbxkms;

	private String save_sqyy;

	private String queryequals_sqyy;

	private String save_hjje;

	private String queryequals_hjje;

	private String save_yjdycjfje;

	private String queryequals_yjdycjfje;

	private String save_yjdycjfsj;

	private String queryequals_yjdycjfsj;

	private String save_yjdecjfje;

	private String queryequals_yjdecjfje;

	private String save_yjdecjfsj;

	private String queryequals_yjdecjfsj;

	private String save_tkz;

	private String queryequals_tkz;

	private String save_zdshbzz;

	private String queryequals_zdshbzz;

	private String save_shfzz;

	private String queryequals_shfzz;

	private String save_zxzm;

	private String queryequals_zxzm;

	private String save_jdzm;

	private String queryequals_jdzm;

	private String save_mzjzm;

	private String queryequals_mzjzm;

	private String save_jtszdzdshbzx;

	private String queryequals_jtszdzdshbzx;

	private String save_xsjjknbzqk;

	private String queryequals_xsjjknbzqk;

	private String save_fmsfzxxfhj;

	private String queryequals_fmsfzxxfhj;

	private String save_fdysh;

	private String queryequals_fdysh;

	private String save_fdyshsj;

	private String queryequals_fdyshsj;

	private String save_fdyshyj;

	private String queryequals_fdyshyj;

	private String save_xysh;

	private String queryequals_xysh;

	private String save_xyshsj;

	private String queryequals_xyshsj;

	private String save_xyshyj;

	private String queryequals_xyshyj;

	private String save_xxsh;

	private String queryequals_xxsh;

	private String save_xxshsj;

	private String queryequals_xxshsj;

	private String save_xxshyj;

	private String queryequals_xxshyj;

	private String queryequals_xb;

	private String querylike_sfzh;

	private String save_yjjfsj;

	private String querygreaterequal_sqsj;

	private String querylessequal_sqsj;

	private String save_yjjfje;

	private String save_cjqgzxsj;

	private static final long serialVersionUID = 1L;

	private String[] pk;

	private String[] checkVal;// 批处理

	private boolean fdy;

	private String userName;// 用户名

	private String userType;

	private String userDep;

	private String pkcolumn;

	private String shjg;

	private String shyj;

	Pages pages = new Pages();

	private String xn;

	private String xh;

	private String xm;

	private String xb;

	private String mzmc;

	private String zzmmmc;

	private String csrq;

	private String xz;

	private String nj;

	private String sfzh;

	private String xydm;

	private String xymc;

	private String zydm;

	private String zymc;

	private String bjdm;

	private String bjmc;

	private String jtrjnsr;

	private String zxlxdh;

	private String sqly;

	private String sqsj;

	private String fdysh;

	private String fdyshsj;

	private String fdyshyj;

	private String xysh;

	private String xyshsj;

	private String xyshyj;

	private String xxsh;

	private String xxshsj;

	private String xxshyj;

	private String rxrq;

	private String lxdh;

	private String gxnbxkcs;

	private String yxkcs;

	private String lhkcs;

	private String cjpm;

	private String zhkpcj;

	private String zhkppm;

	private String hjqk;

	private String yjjl;

	private String xjjl;

	private String sjjl;

	private String chhzjl;

	private String jthk;

	private String jtzrks;

	private String jtyzsr;

	private String rjysr;

	private String srly;

	private String jtzz;

	private String chjl;

	private String yzbm;

	private String jtcy1_xm;

	private String jtcy1_nl;

	private String jtcy1_gx;

	private String jtcy1_dw;

	private String jtcy2_xm;

	private String jtcy2_nl;

	private String jtcy2_gx;

	private String jtcy2_dw;

	private String jtcy3_xm;

	private String jtcy3_nl;

	private String jtcy3_gx;

	private String jtcy3_dw;

	private String jtcy4_xm;

	private String jtcy4_nl;

	private String jtcy4_gx;

	private String jtcy4_dw;

	private String jtcy5_xm;

	private String jtcy5_nl;

	private String jtcy5_gx;

	private String jtcy5_dw;

	private String zxjdm;

	private String zxjdj;

	private String zxjje;

	private String xxcj;

	private String xmb;

	private String shjb;

	private String sfkns;

	private String xmmc;

	private String rdqk;

	private String ymcs;

	private String sxnzhcpmc;

	private String sxnzhcprs;

	private String bkmc;

	private String llk;

	private String lxfs;

	private String ywqgzx;

	private String jtlxfs;

	private String hjje;

	private String hjqx;

	private String sqje;

	private String kndj;

	private String km1;

	private String cj1;

	private String km2;

	private String cj2;

	private String km3;

	private String cj3;

	private String km4;

	private String cj4;

	private String km5;

	private String cj5;

	private String xsyhm;

	private String xshm;

	private String xszh;

	private String xqmc;

	private String qfqk;

	private String jtdz;

	private String xq;

	private String hzqk;

	private String jmje;

	private String sqjmje;

	private String pzjmje;

	private String pzje;

	private String xmdm;

	private String xmje;

	private String jg;

	private String zxjmc;

	private String pxqk;

	private String jtxxdz;

	private String jtjxxqkknzy;

	private String zxxxf;

	private String jtcy5_zk;

	private String sfjxf;

	private String rxqhk;

	private String byxx;

	private String xszxlxdh;

	private String jtqzqk;

	private String jtcy4_zk;

	private String jtcyycjnmrndnlrqk;

	private String jbrbgdh_dh;

	private String jtcy5_gz;

	private String yjzsf;

	private String jtcy1_gz;

	private String jtcy2_sr;

	private String jbrbgdh_qx;

	private String jtcstfywqk;

	private String jtcy3_gz;

	private String jtcy5_sr;

	private String jtrks;

	private String jtcy1_sr;

	private String ddzdshbz;

	private String jtcy4_sr;

	private String jtcy3_zk;

	private String jtcy2_gz;

	private String txsj;

	private String jtcy4_gz;

	private String jtcy3_sr;

	private String sfjzsf;

	private String sfgc;

	private String jtcy1_zk;

	private String jtcy2_zk;

	private String jbrxm;

	private String jttgshf;

	private String grtc;

	private String sfdbh;

	private String mzbm_xxtxdz;

	private String jtcysyqk;

	private String sflszn;

	private String qtqk;

	private String mzbm_yzbm;

	private String jtrjysr;

	private String bxnyhzzqk;

	private String sfdq;

	private String jtcszrzhqk;

	private String jtlxdh;

	private String yjxf;

	private String txsj1;

	private String txsj2;

	private String hjxf;

	private String hjxzfqx;

	private String hjyy;

	private String hjzsf;

	private String sfhdgjzxdk;

	private String ywsqgjzxdk;

	private String sxnbjzhcppm;

	private String xy_sxnzhcppm;

	private String qtsm;

	private String jtcy2_zy;

	private String jtcy3_zy;

	private String bkks;

	private String xy_sftjsq;

	private String zxfscf;

	private String jtcy5_zy;

	private String jtcy1_zy;

	private String xy_sfyknzm;

	private String jtcy4_zy;

	private String sqyy;

	private String xy_sfkns;

	private String xy_bjmzpytgl;

	private String bxnhdhzzz;

	private String jtrk;

	// 是否低保
	private String sfdb;

	// 缓缴其他费用
	private String hjqtfy;

	private String fdyshr;

	private String xyshr;

	// 上传地址
	private String scdz;

	private String hjsj1;//获奖时间1
	
	private String hjsj2;//获奖时间2
	
	private String hjsj3;//获奖时间3
	
	private String hjsj4;//获奖时间4
	
	private String hjmc1;//获奖名称1
	
	private String hjmc2;//获奖名称2
	
	private String hjmc3;//获奖名称3
	
	private String hjmc4;//获奖名称4
	
	private String bjdw1;//颁奖单位
	
	private String bjdw2;
	
	private String bjdw3;
	
	private String bjdw4;
	
	public String getFdyshr() {
		return fdyshr;
	}

	public void setFdyshr(String fdyshr) {
		this.fdyshr = fdyshr;
	}

	public String getXyshr() {
		return xyshr;
	}

	public void setXyshr(String xyshr) {
		this.xyshr = xyshr;
	}

	public String getQueryequals_xb() {
		return queryequals_xb;
	}

	public void setQueryequals_xb(String queryequals_xb) {
		this.queryequals_xb = queryequals_xb;
	}

	public String getQuerylike_sfzh() {
		return querylike_sfzh;
	}

	public void setQuerylike_sfzh(String querylike_sfzh) {
		this.querylike_sfzh = querylike_sfzh;
	}

	public String getQuerylike_xh() {
		return querylike_xh;
	}

	public void setQuerylike_xh(String querylike_xh) {
		this.querylike_xh = querylike_xh;
	}

	public String getQuerylike_xm() {
		return querylike_xm;
	}

	public void setQuerylike_xm(String querylike_xm) {
		this.querylike_xm = querylike_xm;
	}

	public String getQueryequals_nj() {
		return queryequals_nj;
	}

	public void setQueryequals_nj(String queryequals_nj) {
		this.queryequals_nj = queryequals_nj;
	}

	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}

	public void setQueryequals_xydm(String queryequals_xydm) {
		this.queryequals_xydm = queryequals_xydm;
	}

	public String getQueryequals_zydm() {
		return queryequals_zydm;
	}

	public void setQueryequals_zydm(String queryequals_zydm) {
		this.queryequals_zydm = queryequals_zydm;
	}

	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}

	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
	}

	public String getQueryequals_xn() {
		return queryequals_xn;
	}

	public void setQueryequals_xn(String queryequals_xn) {
		this.queryequals_xn = queryequals_xn;
	}

	public String getSave_xn() {
		return save_xn;
	}

	public void setSave_xn(String save_xn) {
		this.save_xn = save_xn;
	}

	public String getQueryequals_dydd() {
		return queryequals_dydd;
	}

	public void setQueryequals_dydd(String queryequals_dydd) {
		this.queryequals_dydd = queryequals_dydd;
	}

	public String getSave_dydd() {
		return save_dydd;
	}

	public void setSave_dydd(String save_dydd) {
		this.save_dydd = save_dydd;
	}

	public String getQueryequals_hjjl() {
		return queryequals_hjjl;
	}

	public void setQueryequals_hjjl(String queryequals_hjjl) {
		this.queryequals_hjjl = queryequals_hjjl;
	}

	public String getSave_hjjl() {
		return save_hjjl;
	}

	public void setSave_hjjl(String save_hjjl) {
		this.save_hjjl = save_hjjl;
	}

	public String getQueryequals_ljbjgbxkms() {
		return queryequals_ljbjgbxkms;
	}

	public void setQueryequals_ljbjgbxkms(String queryequals_ljbjgbxkms) {
		this.queryequals_ljbjgbxkms = queryequals_ljbjgbxkms;
	}

	public String getSave_ljbjgbxkms() {
		return save_ljbjgbxkms;
	}

	public void setSave_ljbjgbxkms(String save_ljbjgbxkms) {
		this.save_ljbjgbxkms = save_ljbjgbxkms;
	}

	public String getQueryequals_sqyy() {
		return queryequals_sqyy;
	}

	public void setQueryequals_sqyy(String queryequals_sqyy) {
		this.queryequals_sqyy = queryequals_sqyy;
	}

	public String getSave_sqyy() {
		return save_sqyy;
	}

	public void setSave_sqyy(String save_sqyy) {
		this.save_sqyy = save_sqyy;
	}

	public String getQueryequals_hjje() {
		return queryequals_hjje;
	}

	public void setQueryequals_hjje(String queryequals_hjje) {
		this.queryequals_hjje = queryequals_hjje;
	}

	public String getSave_hjje() {
		return save_hjje;
	}

	public void setSave_hjje(String save_hjje) {
		this.save_hjje = save_hjje;
	}

	public String getQueryequals_yjdycjfje() {
		return queryequals_yjdycjfje;
	}

	public void setQueryequals_yjdycjfje(String queryequals_yjdycjfje) {
		this.queryequals_yjdycjfje = queryequals_yjdycjfje;
	}

	public String getSave_yjdycjfje() {
		return save_yjdycjfje;
	}

	public void setSave_yjdycjfje(String save_yjdycjfje) {
		this.save_yjdycjfje = save_yjdycjfje;
	}

	public String getQueryequals_yjdycjfsj() {
		return queryequals_yjdycjfsj;
	}

	public void setQueryequals_yjdycjfsj(String queryequals_yjdycjfsj) {
		this.queryequals_yjdycjfsj = queryequals_yjdycjfsj;
	}

	public String getSave_yjdycjfsj() {
		return save_yjdycjfsj;
	}

	public void setSave_yjdycjfsj(String save_yjdycjfsj) {
		this.save_yjdycjfsj = save_yjdycjfsj;
	}

	public String getQueryequals_yjdecjfje() {
		return queryequals_yjdecjfje;
	}

	public void setQueryequals_yjdecjfje(String queryequals_yjdecjfje) {
		this.queryequals_yjdecjfje = queryequals_yjdecjfje;
	}

	public String getSave_yjdecjfje() {
		return save_yjdecjfje;
	}

	public void setSave_yjdecjfje(String save_yjdecjfje) {
		this.save_yjdecjfje = save_yjdecjfje;
	}

	public String getQueryequals_yjdecjfsj() {
		return queryequals_yjdecjfsj;
	}

	public void setQueryequals_yjdecjfsj(String queryequals_yjdecjfsj) {
		this.queryequals_yjdecjfsj = queryequals_yjdecjfsj;
	}

	public String getSave_yjdecjfsj() {
		return save_yjdecjfsj;
	}

	public void setSave_yjdecjfsj(String save_yjdecjfsj) {
		this.save_yjdecjfsj = save_yjdecjfsj;
	}

	public String getQueryequals_tkz() {
		return queryequals_tkz;
	}

	public void setQueryequals_tkz(String queryequals_tkz) {
		this.queryequals_tkz = queryequals_tkz;
	}

	public String getSave_tkz() {
		return save_tkz;
	}

	public void setSave_tkz(String save_tkz) {
		this.save_tkz = save_tkz;
	}

	public String getQueryequals_zdshbzz() {
		return queryequals_zdshbzz;
	}

	public void setQueryequals_zdshbzz(String queryequals_zdshbzz) {
		this.queryequals_zdshbzz = queryequals_zdshbzz;
	}

	public String getSave_zdshbzz() {
		return save_zdshbzz;
	}

	public void setSave_zdshbzz(String save_zdshbzz) {
		this.save_zdshbzz = save_zdshbzz;
	}

	public String getQueryequals_shfzz() {
		return queryequals_shfzz;
	}

	public void setQueryequals_shfzz(String queryequals_shfzz) {
		this.queryequals_shfzz = queryequals_shfzz;
	}

	public String getSave_shfzz() {
		return save_shfzz;
	}

	public void setSave_shfzz(String save_shfzz) {
		this.save_shfzz = save_shfzz;
	}

	public String getQueryequals_zxzm() {
		return queryequals_zxzm;
	}

	public void setQueryequals_zxzm(String queryequals_zxzm) {
		this.queryequals_zxzm = queryequals_zxzm;
	}

	public String getSave_zxzm() {
		return save_zxzm;
	}

	public void setSave_zxzm(String save_zxzm) {
		this.save_zxzm = save_zxzm;
	}

	public String getQueryequals_jdzm() {
		return queryequals_jdzm;
	}

	public void setQueryequals_jdzm(String queryequals_jdzm) {
		this.queryequals_jdzm = queryequals_jdzm;
	}

	public String getSave_jdzm() {
		return save_jdzm;
	}

	public void setSave_jdzm(String save_jdzm) {
		this.save_jdzm = save_jdzm;
	}

	public String getQueryequals_mzjzm() {
		return queryequals_mzjzm;
	}

	public void setQueryequals_mzjzm(String queryequals_mzjzm) {
		this.queryequals_mzjzm = queryequals_mzjzm;
	}

	public String getSave_mzjzm() {
		return save_mzjzm;
	}

	public void setSave_mzjzm(String save_mzjzm) {
		this.save_mzjzm = save_mzjzm;
	}

	public String getQueryequals_jtszdzdshbzx() {
		return queryequals_jtszdzdshbzx;
	}

	public void setQueryequals_jtszdzdshbzx(String queryequals_jtszdzdshbzx) {
		this.queryequals_jtszdzdshbzx = queryequals_jtszdzdshbzx;
	}

	public String getSave_jtszdzdshbzx() {
		return save_jtszdzdshbzx;
	}

	public void setSave_jtszdzdshbzx(String save_jtszdzdshbzx) {
		this.save_jtszdzdshbzx = save_jtszdzdshbzx;
	}

	public String getQueryequals_xsjjknbzqk() {
		return queryequals_xsjjknbzqk;
	}

	public void setQueryequals_xsjjknbzqk(String queryequals_xsjjknbzqk) {
		this.queryequals_xsjjknbzqk = queryequals_xsjjknbzqk;
	}

	public String getSave_xsjjknbzqk() {
		return save_xsjjknbzqk;
	}

	public void setSave_xsjjknbzqk(String save_xsjjknbzqk) {
		this.save_xsjjknbzqk = save_xsjjknbzqk;
	}

	public String getQueryequals_fmsfzxxfhj() {
		return queryequals_fmsfzxxfhj;
	}

	public void setQueryequals_fmsfzxxfhj(String queryequals_fmsfzxxfhj) {
		this.queryequals_fmsfzxxfhj = queryequals_fmsfzxxfhj;
	}

	public String getSave_fmsfzxxfhj() {
		return save_fmsfzxxfhj;
	}

	public void setSave_fmsfzxxfhj(String save_fmsfzxxfhj) {
		this.save_fmsfzxxfhj = save_fmsfzxxfhj;
	}

	public String getQueryequals_fdysh() {
		return queryequals_fdysh;
	}

	public void setQueryequals_fdysh(String queryequals_fdysh) {
		this.queryequals_fdysh = queryequals_fdysh;
	}

	public String getSave_fdysh() {
		return save_fdysh;
	}

	public void setSave_fdysh(String save_fdysh) {
		this.save_fdysh = save_fdysh;
	}

	public String getQueryequals_fdyshsj() {
		return queryequals_fdyshsj;
	}

	public void setQueryequals_fdyshsj(String queryequals_fdyshsj) {
		this.queryequals_fdyshsj = queryequals_fdyshsj;
	}

	public String getSave_fdyshsj() {
		return save_fdyshsj;
	}

	public void setSave_fdyshsj(String save_fdyshsj) {
		this.save_fdyshsj = save_fdyshsj;
	}

	public String getQueryequals_fdyshyj() {
		return queryequals_fdyshyj;
	}

	public void setQueryequals_fdyshyj(String queryequals_fdyshyj) {
		this.queryequals_fdyshyj = queryequals_fdyshyj;
	}

	public String getSave_fdyshyj() {
		return save_fdyshyj;
	}

	public void setSave_fdyshyj(String save_fdyshyj) {
		this.save_fdyshyj = save_fdyshyj;
	}

	public String getQueryequals_xysh() {
		return queryequals_xysh;
	}

	public void setQueryequals_xysh(String queryequals_xysh) {
		this.queryequals_xysh = queryequals_xysh;
	}

	public String getSave_xysh() {
		return save_xysh;
	}

	public void setSave_xysh(String save_xysh) {
		this.save_xysh = save_xysh;
	}

	public String getQueryequals_xyshsj() {
		return queryequals_xyshsj;
	}

	public void setQueryequals_xyshsj(String queryequals_xyshsj) {
		this.queryequals_xyshsj = queryequals_xyshsj;
	}

	public String getSave_xyshsj() {
		return save_xyshsj;
	}

	public void setSave_xyshsj(String save_xyshsj) {
		this.save_xyshsj = save_xyshsj;
	}

	public String getQueryequals_xyshyj() {
		return queryequals_xyshyj;
	}

	public void setQueryequals_xyshyj(String queryequals_xyshyj) {
		this.queryequals_xyshyj = queryequals_xyshyj;
	}

	public String getSave_xyshyj() {
		return save_xyshyj;
	}

	public void setSave_xyshyj(String save_xyshyj) {
		this.save_xyshyj = save_xyshyj;
	}

	public String getQueryequals_xxsh() {
		return queryequals_xxsh;
	}

	public void setQueryequals_xxsh(String queryequals_xxsh) {
		this.queryequals_xxsh = queryequals_xxsh;
	}

	public String getSave_xxsh() {
		return save_xxsh;
	}

	public void setSave_xxsh(String save_xxsh) {
		this.save_xxsh = save_xxsh;
	}

	public String getQueryequals_xxshsj() {
		return queryequals_xxshsj;
	}

	public void setQueryequals_xxshsj(String queryequals_xxshsj) {
		this.queryequals_xxshsj = queryequals_xxshsj;
	}

	public String getSave_xxshsj() {
		return save_xxshsj;
	}

	public void setSave_xxshsj(String save_xxshsj) {
		this.save_xxshsj = save_xxshsj;
	}

	public String getQueryequals_xxshyj() {
		return queryequals_xxshyj;
	}

	public void setQueryequals_xxshyj(String queryequals_xxshyj) {
		this.queryequals_xxshyj = queryequals_xxshyj;
	}

	public String getSave_xxshyj() {
		return save_xxshyj;
	}

	public void setSave_xxshyj(String save_xxshyj) {
		this.save_xxshyj = save_xxshyj;
	}

	public String getShjg() {
		return shjg;
	}

	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String getPkcolumn() {
		return pkcolumn;
	}

	public void setPkcolumn(String pkcolumn) {
		this.pkcolumn = pkcolumn;
	}

	public boolean isFdy() {
		return fdy;
	}

	public void setFdy(boolean fdy) {
		this.fdy = fdy;
	}

	public String getBkks() {
		return bkks;
	}

	public void setBkks(String bkks) {
		this.bkks = bkks;
	}

	public String getBxnhdhzzz() {
		return bxnhdhzzz;
	}

	public void setBxnhdhzzz(String bxnhdhzzz) {
		this.bxnhdhzzz = bxnhdhzzz;
	}

	public String getJtcy1_zy() {
		return jtcy1_zy;
	}

	public void setJtcy1_zy(String jtcy1_zy) {
		this.jtcy1_zy = jtcy1_zy;
	}

	public String getJtcy2_zy() {
		return jtcy2_zy;
	}

	public void setJtcy2_zy(String jtcy2_zy) {
		this.jtcy2_zy = jtcy2_zy;
	}

	public String getJtcy3_zy() {
		return jtcy3_zy;
	}

	public void setJtcy3_zy(String jtcy3_zy) {
		this.jtcy3_zy = jtcy3_zy;
	}

	public String getJtcy4_zy() {
		return jtcy4_zy;
	}

	public void setJtcy4_zy(String jtcy4_zy) {
		this.jtcy4_zy = jtcy4_zy;
	}

	public String getJtcy5_zy() {
		return jtcy5_zy;
	}

	public void setJtcy5_zy(String jtcy5_zy) {
		this.jtcy5_zy = jtcy5_zy;
	}

	public String getJtrk() {
		return jtrk;
	}

	public void setJtrk(String jtrk) {
		this.jtrk = jtrk;
	}

	public String getQtsm() {
		return qtsm;
	}

	public void setQtsm(String qtsm) {
		this.qtsm = qtsm;
	}

	public String getSqyy() {
		return sqyy;
	}

	public void setSqyy(String sqyy) {
		this.sqyy = sqyy;
	}

	public String getSxnbjzhcppm() {
		return sxnbjzhcppm;
	}

	public void setSxnbjzhcppm(String sxnbjzhcppm) {
		this.sxnbjzhcppm = sxnbjzhcppm;
	}

	public String getXy_bjmzpytgl() {
		return xy_bjmzpytgl;
	}

	public void setXy_bjmzpytgl(String xy_bjmzpytgl) {
		this.xy_bjmzpytgl = xy_bjmzpytgl;
	}

	public String getXy_sfkns() {
		return xy_sfkns;
	}

	public void setXy_sfkns(String xy_sfkns) {
		this.xy_sfkns = xy_sfkns;
	}

	public String getXy_sftjsq() {
		return xy_sftjsq;
	}

	public void setXy_sftjsq(String xy_sftjsq) {
		this.xy_sftjsq = xy_sftjsq;
	}

	public String getXy_sfyknzm() {
		return xy_sfyknzm;
	}

	public void setXy_sfyknzm(String xy_sfyknzm) {
		this.xy_sfyknzm = xy_sfyknzm;
	}

	public String getXy_sxnzhcppm() {
		return xy_sxnzhcppm;
	}

	public void setXy_sxnzhcppm(String xy_sxnzhcppm) {
		this.xy_sxnzhcppm = xy_sxnzhcppm;
	}

	public String getZxfscf() {
		return zxfscf;
	}

	public void setZxfscf(String zxfscf) {
		this.zxfscf = zxfscf;
	}

	public String getHjxf() {
		return hjxf;
	}

	public void setHjxf(String hjxf) {
		this.hjxf = hjxf;
	}

	public String getHjxzfqx() {
		return hjxzfqx;
	}

	public void setHjxzfqx(String hjxzfqx) {
		this.hjxzfqx = hjxzfqx;
	}

	public String getHjyy() {
		return hjyy;
	}

	public void setHjyy(String hjyy) {
		this.hjyy = hjyy;
	}

	public String getHjzsf() {
		return hjzsf;
	}

	public void setHjzsf(String hjzsf) {
		this.hjzsf = hjzsf;
	}

	public String getSfhdgjzxdk() {
		return sfhdgjzxdk;
	}

	public void setSfhdgjzxdk(String sfhdgjzxdk) {
		this.sfhdgjzxdk = sfhdgjzxdk;
	}

	public String getYwsqgjzxdk() {
		return ywsqgjzxdk;
	}

	public void setYwsqgjzxdk(String ywsqgjzxdk) {
		this.ywsqgjzxdk = ywsqgjzxdk;
	}

	public String getTxsj1() {
		return txsj1;
	}

	public void setTxsj1(String txsj1) {
		this.txsj1 = txsj1;
	}

	public String getTxsj2() {
		return txsj2;
	}

	public void setTxsj2(String txsj2) {
		this.txsj2 = txsj2;
	}

	public String getJtlxdh() {
		return jtlxdh;
	}

	public void setJtlxdh(String jtlxdh) {
		this.jtlxdh = jtlxdh;
	}

	public String getYjxf() {
		return yjxf;
	}

	public void setYjxf(String yjxf) {
		this.yjxf = yjxf;
	}

	public String getBxnyhzzqk() {
		return bxnyhzzqk;
	}

	public void setBxnyhzzqk(String bxnyhzzqk) {
		this.bxnyhzzqk = bxnyhzzqk;
	}

	public String getByxx() {
		return byxx;
	}

	public void setByxx(String byxx) {
		this.byxx = byxx;
	}

	public String getDdzdshbz() {
		return ddzdshbz;
	}

	public void setDdzdshbz(String ddzdshbz) {
		this.ddzdshbz = ddzdshbz;
	}

	public String getGrtc() {
		return grtc;
	}

	public void setGrtc(String grtc) {
		this.grtc = grtc;
	}

	public String getJbrbgdh_dh() {
		return jbrbgdh_dh;
	}

	public void setJbrbgdh_dh(String jbrbgdh_dh) {
		this.jbrbgdh_dh = jbrbgdh_dh;
	}

	public String getJbrbgdh_qx() {
		return jbrbgdh_qx;
	}

	public void setJbrbgdh_qx(String jbrbgdh_qx) {
		this.jbrbgdh_qx = jbrbgdh_qx;
	}

	public String getJbrxm() {
		return jbrxm;
	}

	public void setJbrxm(String jbrxm) {
		this.jbrxm = jbrxm;
	}

	public String getJtcstfywqk() {
		return jtcstfywqk;
	}

	public void setJtcstfywqk(String jtcstfywqk) {
		this.jtcstfywqk = jtcstfywqk;
	}

	public String getJtcszrzhqk() {
		return jtcszrzhqk;
	}

	public void setJtcszrzhqk(String jtcszrzhqk) {
		this.jtcszrzhqk = jtcszrzhqk;
	}

	public String getJtcy1_gz() {
		return jtcy1_gz;
	}

	public void setJtcy1_gz(String jtcy1_gz) {
		this.jtcy1_gz = jtcy1_gz;
	}

	public String getJtcy1_sr() {
		return jtcy1_sr;
	}

	public void setJtcy1_sr(String jtcy1_sr) {
		this.jtcy1_sr = jtcy1_sr;
	}

	public String getJtcy1_zk() {
		return jtcy1_zk;
	}

	public void setJtcy1_zk(String jtcy1_zk) {
		this.jtcy1_zk = jtcy1_zk;
	}

	public String getJtcy2_gz() {
		return jtcy2_gz;
	}

	public void setJtcy2_gz(String jtcy2_gz) {
		this.jtcy2_gz = jtcy2_gz;
	}

	public String getJtcy2_sr() {
		return jtcy2_sr;
	}

	public void setJtcy2_sr(String jtcy2_sr) {
		this.jtcy2_sr = jtcy2_sr;
	}

	public String getJtcy2_zk() {
		return jtcy2_zk;
	}

	public void setJtcy2_zk(String jtcy2_zk) {
		this.jtcy2_zk = jtcy2_zk;
	}

	public String getJtcy3_gz() {
		return jtcy3_gz;
	}

	public void setJtcy3_gz(String jtcy3_gz) {
		this.jtcy3_gz = jtcy3_gz;
	}

	public String getJtcy3_sr() {
		return jtcy3_sr;
	}

	public void setJtcy3_sr(String jtcy3_sr) {
		this.jtcy3_sr = jtcy3_sr;
	}

	public String getJtcy3_zk() {
		return jtcy3_zk;
	}

	public void setJtcy3_zk(String jtcy3_zk) {
		this.jtcy3_zk = jtcy3_zk;
	}

	public String getJtcy4_gz() {
		return jtcy4_gz;
	}

	public void setJtcy4_gz(String jtcy4_gz) {
		this.jtcy4_gz = jtcy4_gz;
	}

	public String getJtcy4_sr() {
		return jtcy4_sr;
	}

	public void setJtcy4_sr(String jtcy4_sr) {
		this.jtcy4_sr = jtcy4_sr;
	}

	public String getJtcy4_zk() {
		return jtcy4_zk;
	}

	public void setJtcy4_zk(String jtcy4_zk) {
		this.jtcy4_zk = jtcy4_zk;
	}

	public String getJtcy5_gz() {
		return jtcy5_gz;
	}

	public void setJtcy5_gz(String jtcy5_gz) {
		this.jtcy5_gz = jtcy5_gz;
	}

	public String getJtcy5_sr() {
		return jtcy5_sr;
	}

	public void setJtcy5_sr(String jtcy5_sr) {
		this.jtcy5_sr = jtcy5_sr;
	}

	public String getJtcy5_zk() {
		return jtcy5_zk;
	}

	public void setJtcy5_zk(String jtcy5_zk) {
		this.jtcy5_zk = jtcy5_zk;
	}

	public String getJtcysyqk() {
		return jtcysyqk;
	}

	public void setJtcysyqk(String jtcysyqk) {
		this.jtcysyqk = jtcysyqk;
	}

	public String getJtcyycjnmrndnlrqk() {
		return jtcyycjnmrndnlrqk;
	}

	public void setJtcyycjnmrndnlrqk(String jtcyycjnmrndnlrqk) {
		this.jtcyycjnmrndnlrqk = jtcyycjnmrndnlrqk;
	}

	public String getJtqzqk() {
		return jtqzqk;
	}

	public void setJtqzqk(String jtqzqk) {
		this.jtqzqk = jtqzqk;
	}

	public String getJtrjysr() {
		return jtrjysr;
	}

	public void setJtrjysr(String jtrjysr) {
		this.jtrjysr = jtrjysr;
	}

	public String getJtrks() {
		return jtrks;
	}

	public void setJtrks(String jtrks) {
		this.jtrks = jtrks;
	}

	public String getJttgshf() {
		return jttgshf;
	}

	public void setJttgshf(String jttgshf) {
		this.jttgshf = jttgshf;
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

	public String getSfdbh() {
		return sfdbh;
	}

	public void setSfdbh(String sfdbh) {
		this.sfdbh = sfdbh;
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

	public String getSfjxf() {
		return sfjxf;
	}

	public void setSfjxf(String sfjxf) {
		this.sfjxf = sfjxf;
	}

	public String getSfjzsf() {
		return sfjzsf;
	}

	public void setSfjzsf(String sfjzsf) {
		this.sfjzsf = sfjzsf;
	}

	public String getSflszn() {
		return sflszn;
	}

	public void setSflszn(String sflszn) {
		this.sflszn = sflszn;
	}

	public String getTxsj() {
		return txsj;
	}

	public void setTxsj(String txsj) {
		this.txsj = txsj;
	}

	public String getXszxlxdh() {
		return xszxlxdh;
	}

	public void setXszxlxdh(String xszxlxdh) {
		this.xszxlxdh = xszxlxdh;
	}

	public String getYjzsf() {
		return yjzsf;
	}

	public void setYjzsf(String yjzsf) {
		this.yjzsf = yjzsf;
	}

	public String getZxxxf() {
		return zxxxf;
	}

	public void setZxxxf(String zxxxf) {
		this.zxxxf = zxxxf;
	}

	public String getJg() {
		return jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}

	public String getJtjxxqkknzy() {
		return jtjxxqkknzy;
	}

	public void setJtjxxqkknzy(String jtjxxqkknzy) {
		this.jtjxxqkknzy = jtjxxqkknzy;
	}

	public String getJtxxdz() {
		return jtxxdz;
	}

	public void setJtxxdz(String jtxxdz) {
		this.jtxxdz = jtxxdz;
	}

	public String getPxqk() {
		return pxqk;
	}

	public void setPxqk(String pxqk) {
		this.pxqk = pxqk;
	}

	public String getZxjmc() {
		return zxjmc;
	}

	public void setZxjmc(String zxjmc) {
		this.zxjmc = zxjmc;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getXmje() {
		return xmje;
	}

	public void setXmje(String xmje) {
		this.xmje = xmje;
	}

	public String getPzje() {
		return pzje;
	}

	public void setPzje(String pzje) {
		this.pzje = pzje;
	}

	public String getPzjmje() {
		return pzjmje;
	}

	public void setPzjmje(String pzjmje) {
		this.pzjmje = pzjmje;
	}

	public String getSqjmje() {
		return sqjmje;
	}

	public void setSqjmje(String sqjmje) {
		this.sqjmje = sqjmje;
	}

	public String getHzqk() {
		return hzqk;
	}

	public void setHzqk(String hzqk) {
		this.hzqk = hzqk;
	}

	public String getJmje() {
		return jmje;
	}

	public void setJmje(String jmje) {
		this.jmje = jmje;
	}

	public String getJtdz() {
		return jtdz;
	}

	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}

	public String getQfqk() {
		return qfqk;
	}

	public void setQfqk(String qfqk) {
		this.qfqk = qfqk;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getXqmc() {
		return xqmc;
	}

	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}

	public String getXshm() {
		return xshm;
	}

	public void setXshm(String xshm) {
		this.xshm = xshm;
	}

	public String getXsyhm() {
		return xsyhm;
	}

	public void setXsyhm(String xsyhm) {
		this.xsyhm = xsyhm;
	}

	public String getXszh() {
		return xszh;
	}

	public void setXszh(String xszh) {
		this.xszh = xszh;
	}

	public String getCj1() {
		return cj1;
	}

	public void setCj1(String cj1) {
		this.cj1 = cj1;
	}

	public String getCj2() {
		return cj2;
	}

	public void setCj2(String cj2) {
		this.cj2 = cj2;
	}

	public String getCj3() {
		return cj3;
	}

	public void setCj3(String cj3) {
		this.cj3 = cj3;
	}

	public String getCj4() {
		return cj4;
	}

	public void setCj4(String cj4) {
		this.cj4 = cj4;
	}

	public String getCj5() {
		return cj5;
	}

	public void setCj5(String cj5) {
		this.cj5 = cj5;
	}

	public String getKm1() {
		return km1;
	}

	public void setKm1(String km1) {
		this.km1 = km1;
	}

	public String getKm2() {
		return km2;
	}

	public void setKm2(String km2) {
		this.km2 = km2;
	}

	public String getKm3() {
		return km3;
	}

	public void setKm3(String km3) {
		this.km3 = km3;
	}

	public String getKm4() {
		return km4;
	}

	public void setKm4(String km4) {
		this.km4 = km4;
	}

	public String getKm5() {
		return km5;
	}

	public void setKm5(String km5) {
		this.km5 = km5;
	}

	public String getKndj() {
		return kndj;
	}

	public void setKndj(String kndj) {
		this.kndj = kndj;
	}

	public String getSqje() {
		return sqje;
	}

	public void setSqje(String sqje) {
		this.sqje = sqje;
	}

	public String getHjje() {
		return hjje;
	}

	public void setHjje(String hjje) {
		this.hjje = hjje;
	}

	public String getHjqx() {
		return hjqx;
	}

	public void setHjqx(String hjqx) {
		this.hjqx = hjqx;
	}

	public String getBkmc() {
		return bkmc;
	}

	public void setBkmc(String bkmc) {
		this.bkmc = bkmc;
	}

	public String getJtlxfs() {
		return jtlxfs;
	}

	public void setJtlxfs(String jtlxfs) {
		this.jtlxfs = jtlxfs;
	}

	public String getLlk() {
		return llk;
	}

	public void setLlk(String llk) {
		this.llk = llk;
	}

	public String getLxfs() {
		return lxfs;
	}

	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}

	public String getSxnzhcpmc() {
		return sxnzhcpmc;
	}

	public void setSxnzhcpmc(String sxnzhcpmc) {
		this.sxnzhcpmc = sxnzhcpmc;
	}

	public String getSxnzhcprs() {
		return sxnzhcprs;
	}

	public void setSxnzhcprs(String sxnzhcprs) {
		this.sxnzhcprs = sxnzhcprs;
	}

	public String getYmcs() {
		return ymcs;
	}

	public void setYmcs(String ymcs) {
		this.ymcs = ymcs;
	}

	public String getYwqgzx() {
		return ywqgzx;
	}

	public void setYwqgzx(String ywqgzx) {
		this.ywqgzx = ywqgzx;
	}

	public String getRdqk() {
		return rdqk;
	}

	public void setRdqk(String rdqk) {
		this.rdqk = rdqk;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getSfkns() {
		return sfkns;
	}

	public void setSfkns(String sfkns) {
		this.sfkns = sfkns;
	}

	public String getShjb() {
		return shjb;
	}

	public void setShjb(String shjb) {
		this.shjb = shjb;
	}

	public String getXmb() {
		return xmb;
	}

	public void setXmb(String xmb) {
		this.xmb = xmb;
	}

	public String getXxcj() {
		return xxcj;
	}

	public void setXxcj(String xxcj) {
		this.xxcj = xxcj;
	}

	public String getChhzjl() {
		return chhzjl;
	}

	public void setChhzjl(String chhzjl) {
		this.chhzjl = chhzjl;
	}

	public String getJtcy1_dw() {
		return jtcy1_dw;
	}

	public void setJtcy1_dw(String jtcy1_dw) {
		this.jtcy1_dw = jtcy1_dw;
	}

	public String getJtcy1_gx() {
		return jtcy1_gx;
	}

	public void setJtcy1_gx(String jtcy1_gx) {
		this.jtcy1_gx = jtcy1_gx;
	}

	public String getJtcy1_nl() {
		return jtcy1_nl;
	}

	public void setJtcy1_nl(String jtcy1_nl) {
		this.jtcy1_nl = jtcy1_nl;
	}

	public String getJtcy1_xm() {
		return jtcy1_xm;
	}

	public void setJtcy1_xm(String jtcy1_xm) {
		this.jtcy1_xm = jtcy1_xm;
	}

	public String getJtcy2_dw() {
		return jtcy2_dw;
	}

	public void setJtcy2_dw(String jtcy2_dw) {
		this.jtcy2_dw = jtcy2_dw;
	}

	public String getJtcy2_gx() {
		return jtcy2_gx;
	}

	public void setJtcy2_gx(String jtcy2_gx) {
		this.jtcy2_gx = jtcy2_gx;
	}

	public String getJtcy2_nl() {
		return jtcy2_nl;
	}

	public void setJtcy2_nl(String jtcy2_nl) {
		this.jtcy2_nl = jtcy2_nl;
	}

	public String getJtcy2_xm() {
		return jtcy2_xm;
	}

	public void setJtcy2_xm(String jtcy2_xm) {
		this.jtcy2_xm = jtcy2_xm;
	}

	public String getJtcy3_dw() {
		return jtcy3_dw;
	}

	public void setJtcy3_dw(String jtcy3_dw) {
		this.jtcy3_dw = jtcy3_dw;
	}

	public String getJtcy3_gx() {
		return jtcy3_gx;
	}

	public void setJtcy3_gx(String jtcy3_gx) {
		this.jtcy3_gx = jtcy3_gx;
	}

	public String getJtcy3_nl() {
		return jtcy3_nl;
	}

	public void setJtcy3_nl(String jtcy3_nl) {
		this.jtcy3_nl = jtcy3_nl;
	}

	public String getJtcy3_xm() {
		return jtcy3_xm;
	}

	public void setJtcy3_xm(String jtcy3_xm) {
		this.jtcy3_xm = jtcy3_xm;
	}

	public String getJtcy4_dw() {
		return jtcy4_dw;
	}

	public void setJtcy4_dw(String jtcy4_dw) {
		this.jtcy4_dw = jtcy4_dw;
	}

	public String getJtcy4_gx() {
		return jtcy4_gx;
	}

	public void setJtcy4_gx(String jtcy4_gx) {
		this.jtcy4_gx = jtcy4_gx;
	}

	public String getJtcy4_nl() {
		return jtcy4_nl;
	}

	public void setJtcy4_nl(String jtcy4_nl) {
		this.jtcy4_nl = jtcy4_nl;
	}

	public String getJtcy4_xm() {
		return jtcy4_xm;
	}

	public void setJtcy4_xm(String jtcy4_xm) {
		this.jtcy4_xm = jtcy4_xm;
	}

	public String getJtcy5_dw() {
		return jtcy5_dw;
	}

	public void setJtcy5_dw(String jtcy5_dw) {
		this.jtcy5_dw = jtcy5_dw;
	}

	public String getJtcy5_gx() {
		return jtcy5_gx;
	}

	public void setJtcy5_gx(String jtcy5_gx) {
		this.jtcy5_gx = jtcy5_gx;
	}

	public String getJtcy5_nl() {
		return jtcy5_nl;
	}

	public void setJtcy5_nl(String jtcy5_nl) {
		this.jtcy5_nl = jtcy5_nl;
	}

	public String getJtcy5_xm() {
		return jtcy5_xm;
	}

	public void setJtcy5_xm(String jtcy5_xm) {
		this.jtcy5_xm = jtcy5_xm;
	}

	public String getJthk() {
		return jthk;
	}

	public void setJthk(String jthk) {
		this.jthk = jthk;
	}

	public String getJtyzsr() {
		return jtyzsr;
	}

	public void setJtyzsr(String jtyzsr) {
		this.jtyzsr = jtyzsr;
	}

	public String getJtzrks() {
		return jtzrks;
	}

	public void setJtzrks(String jtzrks) {
		this.jtzrks = jtzrks;
	}

	public String getJtzz() {
		return jtzz;
	}

	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}

	public String getRjysr() {
		return rjysr;
	}

	public void setRjysr(String rjysr) {
		this.rjysr = rjysr;
	}

	public String getSrly() {
		return srly;
	}

	public void setSrly(String srly) {
		this.srly = srly;
	}

	public String getYzbm() {
		return yzbm;
	}

	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}

	public String getZxjdj() {
		return zxjdj;
	}

	public void setZxjdj(String zxjdj) {
		this.zxjdj = zxjdj;
	}

	public String getZxjdm() {
		return zxjdm;
	}

	public void setZxjdm(String zxjdm) {
		this.zxjdm = zxjdm;
	}

	public String getZxjje() {
		return zxjje;
	}

	public void setZxjje(String zxjje) {
		this.zxjje = zxjje;
	}

	public String getCjpm() {
		return cjpm;
	}

	public void setCjpm(String cjpm) {
		this.cjpm = cjpm;
	}

	public String getGxnbxkcs() {
		return gxnbxkcs;
	}

	public void setGxnbxkcs(String gxnbxkcs) {
		this.gxnbxkcs = gxnbxkcs;
	}

	public String getHjqk() {
		return hjqk;
	}

	public void setHjqk(String hjqk) {
		this.hjqk = hjqk;
	}

	public String getLhkcs() {
		return lhkcs;
	}

	public void setLhkcs(String lhkcs) {
		this.lhkcs = lhkcs;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getRxrq() {
		return rxrq;
	}

	public void setRxrq(String rxrq) {
		this.rxrq = rxrq;
	}

	public String getSjjl() {
		return sjjl;
	}

	public void setSjjl(String sjjl) {
		this.sjjl = sjjl;
	}

	public String getXjjl() {
		return xjjl;
	}

	public void setXjjl(String xjjl) {
		this.xjjl = xjjl;
	}

	public String getYjjl() {
		return yjjl;
	}

	public void setYjjl(String yjjl) {
		this.yjjl = yjjl;
	}

	public String getYxkcs() {
		return yxkcs;
	}

	public void setYxkcs(String yxkcs) {
		this.yxkcs = yxkcs;
	}

	public String getZhkpcj() {
		return zhkpcj;
	}

	public void setZhkpcj(String zhkpcj) {
		this.zhkpcj = zhkpcj;
	}

	public String getZhkppm() {
		return zhkppm;
	}

	public void setZhkppm(String zhkppm) {
		this.zhkppm = zhkppm;
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

	public String getCsrq() {
		return csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getFdysh() {
		return fdysh;
	}

	public void setFdysh(String fdysh) {
		this.fdysh = fdysh;
	}

	public String getFdyshsj() {
		return fdyshsj;
	}

	public void setFdyshsj(String fdyshsj) {
		this.fdyshsj = fdyshsj;
	}

	public String getFdyshyj() {
		return fdyshyj;
	}

	public void setFdyshyj(String fdyshyj) {
		this.fdyshyj = fdyshyj;
	}

	public String getJtrjnsr() {
		return jtrjnsr;
	}

	public void setJtrjnsr(String jtrjnsr) {
		this.jtrjnsr = jtrjnsr;
	}

	public String getMzmc() {
		return mzmc;
	}

	public void setMzmc(String mzmc) {
		this.mzmc = mzmc;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String[] getPk() {
		return pk;
	}

	public void setPk(String[] pk) {
		this.pk = pk;
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

	public String getXxsh() {
		return xxsh;
	}

	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}

	public String getXxshsj() {
		return xxshsj;
	}

	public void setXxshsj(String xxshsj) {
		this.xxshsj = xxshsj;
	}

	public String getXxshyj() {
		return xxshyj;
	}

	public void setXxshyj(String xxshyj) {
		this.xxshyj = xxshyj;
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

	public String getXyshsj() {
		return xyshsj;
	}

	public void setXyshsj(String xyshsj) {
		this.xyshsj = xyshsj;
	}

	public String getXyshyj() {
		return xyshyj;
	}

	public void setXyshyj(String xyshyj) {
		this.xyshyj = xyshyj;
	}

	public String getXz() {
		return xz;
	}

	public void setXz(String xz) {
		this.xz = xz;
	}

	public String getZxlxdh() {
		return zxlxdh;
	}

	public void setZxlxdh(String zxlxdh) {
		this.zxlxdh = zxlxdh;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserDep() {
		return userDep;
	}

	public void setUserDep(String userDep) {
		this.userDep = userDep;
	}

	public String getSfdb() {
		return sfdb;
	}

	public void setSfdb(String sfdb) {
		this.sfdb = sfdb;
	}

	public String getHjqtfy() {
		return hjqtfy;
	}

	public void setHjqtfy(String hjqtfy) {
		this.hjqtfy = hjqtfy;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String getQueryequals_bjpy() {
		return queryequals_bjpy;
	}

	public void setQueryequals_bjpy(String queryequals_bjpy) {
		this.queryequals_bjpy = queryequals_bjpy;
	}

	public String getQueryequals_gczm() {
		return queryequals_gczm;
	}

	public void setQueryequals_gczm(String queryequals_gczm) {
		this.queryequals_gczm = queryequals_gczm;
	}

	public String getQueryequals_jmje() {
		return queryequals_jmje;
	}

	public void setQueryequals_jmje(String queryequals_jmje) {
		this.queryequals_jmje = queryequals_jmje;
	}

	public String getQueryequals_lsznzm() {
		return queryequals_lsznzm;
	}

	public void setQueryequals_lsznzm(String queryequals_lsznzm) {
		this.queryequals_lsznzm = queryequals_lsznzm;
	}

	public String getQueryequals_pddd() {
		return queryequals_pddd;
	}

	public void setQueryequals_pddd(String queryequals_pddd) {
		this.queryequals_pddd = queryequals_pddd;
	}

	public String getQueryequals_qtzm() {
		return queryequals_qtzm;
	}

	public void setQueryequals_qtzm(String queryequals_qtzm) {
		this.queryequals_qtzm = queryequals_qtzm;
	}

	public String getQueryequals_rdbh() {
		return queryequals_rdbh;
	}

	public void setQueryequals_rdbh(String queryequals_rdbh) {
		this.queryequals_rdbh = queryequals_rdbh;
	}

	public String getQueryequals_spdc() {
		return queryequals_spdc;
	}

	public void setQueryequals_spdc(String queryequals_spdc) {
		this.queryequals_spdc = queryequals_spdc;
	}

	public String getQueryequals_sqdc() {
		return queryequals_sqdc;
	}

	public void setQueryequals_sqdc(String queryequals_sqdc) {
		this.queryequals_sqdc = queryequals_sqdc;
	}

	public String getQueryequals_ssmzzm() {
		return queryequals_ssmzzm;
	}

	public void setQueryequals_ssmzzm(String queryequals_ssmzzm) {
		this.queryequals_ssmzzm = queryequals_ssmzzm;
	}

	public String getQueryequals_xygsjg() {
		return queryequals_xygsjg;
	}

	public void setQueryequals_xygsjg(String queryequals_xygsjg) {
		this.queryequals_xygsjg = queryequals_xygsjg;
	}

	public String getQueryequals_yfjtzm() {
		return queryequals_yfjtzm;
	}

	public void setQueryequals_yfjtzm(String queryequals_yfjtzm) {
		this.queryequals_yfjtzm = queryequals_yfjtzm;
	}

	public String getSave_bjpy() {
		return save_bjpy;
	}

	public void setSave_bjpy(String save_bjpy) {
		this.save_bjpy = save_bjpy;
	}

	public String getSave_gczm() {
		return save_gczm;
	}

	public void setSave_gczm(String save_gczm) {
		this.save_gczm = save_gczm;
	}

	public String getSave_jmje() {
		return save_jmje;
	}

	public void setSave_jmje(String save_jmje) {
		this.save_jmje = save_jmje;
	}

	public String getSave_lsznzm() {
		return save_lsznzm;
	}

	public void setSave_lsznzm(String save_lsznzm) {
		this.save_lsznzm = save_lsznzm;
	}

	public String getSave_pddd() {
		return save_pddd;
	}

	public void setSave_pddd(String save_pddd) {
		this.save_pddd = save_pddd;
	}

	public String getSave_qtzm() {
		return save_qtzm;
	}

	public void setSave_qtzm(String save_qtzm) {
		this.save_qtzm = save_qtzm;
	}

	public String getSave_rdbh() {
		return save_rdbh;
	}

	public void setSave_rdbh(String save_rdbh) {
		this.save_rdbh = save_rdbh;
	}

	public String getSave_spdc() {
		return save_spdc;
	}

	public void setSave_spdc(String save_spdc) {
		this.save_spdc = save_spdc;
	}

	public String getSave_sqdc() {
		return save_sqdc;
	}

	public void setSave_sqdc(String save_sqdc) {
		this.save_sqdc = save_sqdc;
	}

	public String getSave_ssmzzm() {
		return save_ssmzzm;
	}

	public void setSave_ssmzzm(String save_ssmzzm) {
		this.save_ssmzzm = save_ssmzzm;
	}

	public String getSave_xygsjg() {
		return save_xygsjg;
	}

	public void setSave_xygsjg(String save_xygsjg) {
		this.save_xygsjg = save_xygsjg;
	}

	public String getSave_yfjtzm() {
		return save_yfjtzm;
	}

	public void setSave_yfjtzm(String save_yfjtzm) {
		this.save_yfjtzm = save_yfjtzm;
	}

	public String getQuerygreaterequal_sqsj() {
		return querygreaterequal_sqsj;
	}

	public void setQuerygreaterequal_sqsj(String querygreaterequal_sqsj) {
		this.querygreaterequal_sqsj = querygreaterequal_sqsj;
	}

	public String getQuerylessequal_sqsj() {
		return querylessequal_sqsj;
	}

	public void setQuerylessequal_sqsj(String querylessequal_sqsj) {
		this.querylessequal_sqsj = querylessequal_sqsj;
	}

	public String getSave_cjqgzxsj() {
		return save_cjqgzxsj;
	}

	public void setSave_cjqgzxsj(String save_cjqgzxsj) {
		this.save_cjqgzxsj = save_cjqgzxsj;
	}

	public String getSave_yjjfje() {
		return save_yjjfje;
	}

	public void setSave_yjjfje(String save_yjjfje) {
		this.save_yjjfje = save_yjjfje;
	}

	public String getSave_yjjfsj() {
		return save_yjjfsj;
	}

	public void setSave_yjjfsj(String save_yjjfsj) {
		this.save_yjjfsj = save_yjjfsj;
	}

	public String getChjl() {
		return chjl;
	}

	public void setChjl(String chjl) {
		this.chjl = chjl;
	}

	public String[] getXfjmje() {
		return xfjmje;
	}

	public void setXfjmje(String[] xfjmje) {
		this.xfjmje = xfjmje;
	}

	public String[] getXfsfqf() {
		return xfsfqf;
	}

	public void setXfsfqf(String[] xfsfqf) {
		this.xfsfqf = xfsfqf;
	}

	public String[] getXfsfxm() {
		return xfsfxm;
	}

	public void setXfsfxm(String[] xfsfxm) {
		this.xfsfxm = xfsfxm;
	}

	public String[] getXfsjje() {
		return xfsjje;
	}

	public void setXfsjje(String[] xfsjje) {
		this.xfsjje = xfsjje;
	}

	public String[] getXfyjje() {
		return xfyjje;
	}

	public void setXfyjje(String[] xfyjje) {
		this.xfyjje = xfyjje;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getScdz() {
		return scdz;
	}

	public void setScdz(String scdz) {
		this.scdz = scdz;
	}

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getBjdw1() {
		return bjdw1;
	}

	public void setBjdw1(String bjdw1) {
		this.bjdw1 = bjdw1;
	}

	public String getBjdw2() {
		return bjdw2;
	}

	public void setBjdw2(String bjdw2) {
		this.bjdw2 = bjdw2;
	}

	public String getBjdw3() {
		return bjdw3;
	}

	public void setBjdw3(String bjdw3) {
		this.bjdw3 = bjdw3;
	}

	public String getBjdw4() {
		return bjdw4;
	}

	public void setBjdw4(String bjdw4) {
		this.bjdw4 = bjdw4;
	}

	public String getHjmc1() {
		return hjmc1;
	}

	public void setHjmc1(String hjmc1) {
		this.hjmc1 = hjmc1;
	}

	public String getHjmc2() {
		return hjmc2;
	}

	public void setHjmc2(String hjmc2) {
		this.hjmc2 = hjmc2;
	}

	public String getHjmc3() {
		return hjmc3;
	}

	public void setHjmc3(String hjmc3) {
		this.hjmc3 = hjmc3;
	}

	public String getHjmc4() {
		return hjmc4;
	}

	public void setHjmc4(String hjmc4) {
		this.hjmc4 = hjmc4;
	}

	public String getHjsj1() {
		return hjsj1;
	}

	public void setHjsj1(String hjsj1) {
		this.hjsj1 = hjsj1;
	}

	public String getHjsj2() {
		return hjsj2;
	}

	public void setHjsj2(String hjsj2) {
		this.hjsj2 = hjsj2;
	}

	public String getHjsj3() {
		return hjsj3;
	}

	public void setHjsj3(String hjsj3) {
		this.hjsj3 = hjsj3;
	}

	public String getHjsj4() {
		return hjsj4;
	}

	public void setHjsj4(String hjsj4) {
		this.hjsj4 = hjsj4;
	}
}
