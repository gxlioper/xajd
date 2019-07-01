package xgxt.wjdc;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.utils.Pages;

public class WjdcForm extends ActionForm {

	private static final long serialVersionUID = -9205711105806100577L;

	/* 通用 */
	Pages pages = new Pages();

	FormFile uploadFile;// 上传文件
	
	private String jwy;//卷尾语
	
	private String wjid;//问卷编号
	
	private String sxdwmc;//实习单位名称
	
	private String gzdd;//工作地点
	
	private String gzgw;//工作岗位
	
	private String lxfs;//联系方式

	private String tableName;// 操作表

	private String fdyQx;// 辅导员权限

	private String bzrQx;// 班主任权限

	private String[] checkVal;// 批处理

	private String[] primarykey_checkVal;

	private String xysh;// 学院审核

	private String queryequals_xysh;

	private String xxsh;// 学校审核

	private String queryequals_xxsh;

	private String userName;// 用户名

	private String xh;// 学号

	private String querylike_xh;

	private String xm;// 姓名

	private String querylike_xm;

	private String xb;// 性别

	private String queryequals_xb;

	private String nj;// 年级

	private String queryequals_nj;

	private String xn;// 学年

	private String queryequals_xn;

	private String save_xn;

	private String xq;// 学期

	private String queryequals_xq;

	private String save_xq;

	private String nd;// 年度

	private String save_nd;;

	private String queryequals_nd;

	private String xydm;// 学院代码

	private String xymc;// 学院名称

	private String queryequals_xydm;

	private String zydm;// 专业代码

	private String queryequals_zydm;

	private String zymc;// 专业名称

	private String bjdm;// 班级代码

	private String queryequals_bjdm;

	private String bjmc;// 班级名称

	private String zzmm;// 政治面貌

	private String bz;// 备注

	private String save_bz;

	private String lx;// 类型

	private String queryequals_lx;

	private String id;// ID

	private String queryequals_id;

	private String mklx;// 模块类型

	private String queryequals_mklx;

	private String bl;// 比例

	private String wjmc;// '问卷名称';

	private String querylike_wjmc;

	private String save_wjmc;

	private String jlsj;// '建立时间';

	private String querygreaterequal_jlsj;

	private String querylessequal_jlsj;

	private String save_jlsj;

	private String stbh;// '试题编号';

	private String save_stbh;

	private String stss;// '试题所属';

	private String queryequals_stss;

	private String querylike_stbh;

	private String stlx;// '试题类型';

	private String save_stlx;

	private String queryequals_stlx;

	private String stmc;// '试题名称';

	private String save_stmc;

	private String zjxx;// 组卷信息

	private String queryequals_zjxx;

	private String[] dabh;// '答案编号';

	private String[] damc;// '答案名称';

	private String[] bzda;// '标准答案';

	private String[] dabhoneChoose;// '单选题答案编号';

	private String[] damconeChoose;// '单选题答案名称';

	private String[] bzdaoneChoose;// '单选题标准答案';

	private String[] dabhallChoose;// '多选题答案编号';

	private String[] damcallChoose;// '多选题答案名称';

	private String[] bzdaallChoose;// '多选题标准答案';

	private String lxmc;// '类型名称';

	private String xsmc;// '题目';

	private String haveda;// '标准答案有无';

	private String queryequals_haveda;

	private String[] fpbh;// 分配编号

	private String xhzgh;// '学号职工号';

	private String wjbh;// '问卷编号';

	private String[] oneChooseBh;// 单选题编号

	private String[] allChooseBh;// 多选题编号

	private String[] questionsBh;// 问答题答案

	private String[] oneChooseDa;// 单选题答案

	private String[] allChooseDa;// 多选题答案

	private String[] questionsDa;// 问答题答案

	private String[] danr;// 答案内容

	private String[] fpbj;// 分配班级

	private String isover;// 是否完成

	private String queryequals_isover;

	private String sfkq;// 是否开启

	private String queryequals_sfkq;

	private String zgh;// '职工号';

	private String lrsj;// '录入时间';

	private String querygreaterequal_lrsj;

	private String querylessequal_lrsj;

	private String[] djr;// 答卷人

	private String[] fpxh;// 分配学号

	private String fpzt;// 分配状态

	private String queryequals_fpzt;

	private String bpjr;// 被评价人

	private String[] pjf;// 评价分

	private String[] zjsx;// 组卷顺序

	private String kyxg;// '可以修改';

	private String dawk;// '答案为空';

	private String queryequals_kyxg;

	private String queryequals_dawk;

	private String wzjg;// '文字结果';

	private String tbjg;// '图表结果';

	public String getQueryequals_dawk() {
		return queryequals_dawk;
	}

	public void setQueryequals_dawk(String queryequals_dawk) {
		this.queryequals_dawk = queryequals_dawk;
	}

	public String getQueryequals_kyxg() {
		return queryequals_kyxg;
	}

	public void setQueryequals_kyxg(String queryequals_kyxg) {
		this.queryequals_kyxg = queryequals_kyxg;
	}

	public String[] getZjsx() {
		return zjsx;
	}

	public void setZjsx(String[] zjsx) {
		this.zjsx = zjsx;
	}

	public String[] getAllChooseBh() {
		return allChooseBh;
	}

	public void setAllChooseBh(String[] allChooseBh) {
		this.allChooseBh = allChooseBh;
	}

	public String[] getAllChooseDa() {
		return allChooseDa;
	}

	public void setAllChooseDa(String[] allChooseDa) {
		this.allChooseDa = allChooseDa;
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

	public String getBl() {
		return bl;
	}

	public void setBl(String bl) {
		this.bl = bl;
	}

	public String getBpjr() {
		return bpjr;
	}

	public void setBpjr(String bpjr) {
		this.bpjr = bpjr;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String[] getBzda() {
		return bzda;
	}

	public void setBzda(String[] bzda) {
		this.bzda = bzda;
	}

	public String[] getBzdaallChoose() {
		return bzdaallChoose;
	}

	public void setBzdaallChoose(String[] bzdaallChoose) {
		this.bzdaallChoose = bzdaallChoose;
	}

	public String[] getBzdaoneChoose() {
		return bzdaoneChoose;
	}

	public void setBzdaoneChoose(String[] bzdaoneChoose) {
		this.bzdaoneChoose = bzdaoneChoose;
	}

	public String getBzrQx() {
		return bzrQx;
	}

	public void setBzrQx(String bzrQx) {
		this.bzrQx = bzrQx;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String[] getDabh() {
		return dabh;
	}

	public void setDabh(String[] dabh) {
		this.dabh = dabh;
	}

	public String[] getDabhallChoose() {
		return dabhallChoose;
	}

	public void setDabhallChoose(String[] dabhallChoose) {
		this.dabhallChoose = dabhallChoose;
	}

	public String[] getDabhoneChoose() {
		return dabhoneChoose;
	}

	public void setDabhoneChoose(String[] dabhoneChoose) {
		this.dabhoneChoose = dabhoneChoose;
	}

	public String[] getDamc() {
		return damc;
	}

	public void setDamc(String[] damc) {
		this.damc = damc;
	}

	public String[] getDamcallChoose() {
		return damcallChoose;
	}

	public void setDamcallChoose(String[] damcallChoose) {
		this.damcallChoose = damcallChoose;
	}

	public String[] getDamconeChoose() {
		return damconeChoose;
	}

	public void setDamconeChoose(String[] damconeChoose) {
		this.damconeChoose = damconeChoose;
	}

	public String[] getDanr() {
		return danr;
	}

	public void setDanr(String[] danr) {
		this.danr = danr;
	}

	public String[] getDjr() {
		return djr;
	}

	public void setDjr(String[] djr) {
		this.djr = djr;
	}

	public String getFdyQx() {
		return fdyQx;
	}

	public void setFdyQx(String fdyQx) {
		this.fdyQx = fdyQx;
	}

	public String[] getFpbh() {
		return fpbh;
	}

	public void setFpbh(String[] fpbh) {
		this.fpbh = fpbh;
	}

	public String[] getFpbj() {
		return fpbj;
	}

	public void setFpbj(String[] fpbj) {
		this.fpbj = fpbj;
	}

	public String[] getFpxh() {
		return fpxh;
	}

	public void setFpxh(String[] fpxh) {
		this.fpxh = fpxh;
	}

	public String getFpzt() {
		return fpzt;
	}

	public void setFpzt(String fpzt) {
		this.fpzt = fpzt;
	}

	public String getHaveda() {
		return haveda;
	}

	public void setHaveda(String haveda) {
		this.haveda = haveda;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsover() {
		return isover;
	}

	public void setIsover(String isover) {
		this.isover = isover;
	}

	public String getJlsj() {
		return jlsj;
	}

	public void setJlsj(String jlsj) {
		this.jlsj = jlsj;
	}

	public String getLrsj() {
		return lrsj;
	}

	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getLxmc() {
		return lxmc;
	}

	public void setLxmc(String lxmc) {
		this.lxmc = lxmc;
	}

	public String getMklx() {
		return mklx;
	}

	public void setMklx(String mklx) {
		this.mklx = mklx;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String[] getOneChooseBh() {
		return oneChooseBh;
	}

	public void setOneChooseBh(String[] oneChooseBh) {
		this.oneChooseBh = oneChooseBh;
	}

	public String[] getOneChooseDa() {
		return oneChooseDa;
	}

	public void setOneChooseDa(String[] oneChooseDa) {
		this.oneChooseDa = oneChooseDa;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String[] getPjf() {
		return pjf;
	}

	public void setPjf(String[] pjf) {
		this.pjf = pjf;
	}

	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}

	public void setPrimarykey_checkVal(String[] primarykey_checkVal) {
		this.primarykey_checkVal = primarykey_checkVal;
	}

	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}

	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
	}

	public String getQueryequals_fpzt() {
		return queryequals_fpzt;
	}

	public void setQueryequals_fpzt(String queryequals_fpzt) {
		this.queryequals_fpzt = queryequals_fpzt;
	}

	public String getQueryequals_haveda() {
		return queryequals_haveda;
	}

	public void setQueryequals_haveda(String queryequals_haveda) {
		this.queryequals_haveda = queryequals_haveda;
	}

	public String getQueryequals_id() {
		return queryequals_id;
	}

	public void setQueryequals_id(String queryequals_id) {
		this.queryequals_id = queryequals_id;
	}

	public String getQueryequals_isover() {
		return queryequals_isover;
	}

	public void setQueryequals_isover(String queryequals_isover) {
		this.queryequals_isover = queryequals_isover;
	}

	public String getQueryequals_lx() {
		return queryequals_lx;
	}

	public void setQueryequals_lx(String queryequals_lx) {
		this.queryequals_lx = queryequals_lx;
	}

	public String getQueryequals_nd() {
		return queryequals_nd;
	}

	public void setQueryequals_nd(String queryequals_nd) {
		this.queryequals_nd = queryequals_nd;
	}

	public String getQueryequals_nj() {
		return queryequals_nj;
	}

	public void setQueryequals_nj(String queryequals_nj) {
		this.queryequals_nj = queryequals_nj;
	}

	public String getQueryequals_sfkq() {
		return queryequals_sfkq;
	}

	public void setQueryequals_sfkq(String queryequals_sfkq) {
		this.queryequals_sfkq = queryequals_sfkq;
	}

	public String getQueryequals_stlx() {
		return queryequals_stlx;
	}

	public void setQueryequals_stlx(String queryequals_stlx) {
		this.queryequals_stlx = queryequals_stlx;
	}

	public String getQueryequals_stss() {
		return queryequals_stss;
	}

	public void setQueryequals_stss(String queryequals_stss) {
		this.queryequals_stss = queryequals_stss;
	}

	public String getQueryequals_xb() {
		return queryequals_xb;
	}

	public void setQueryequals_xb(String queryequals_xb) {
		this.queryequals_xb = queryequals_xb;
	}

	public String getQueryequals_xn() {
		return queryequals_xn;
	}

	public void setQueryequals_xn(String queryequals_xn) {
		this.queryequals_xn = queryequals_xn;
	}

	public String getQueryequals_xq() {
		return queryequals_xq;
	}

	public void setQueryequals_xq(String queryequals_xq) {
		this.queryequals_xq = queryequals_xq;
	}

	public String getQueryequals_xxsh() {
		return queryequals_xxsh;
	}

	public void setQueryequals_xxsh(String queryequals_xxsh) {
		this.queryequals_xxsh = queryequals_xxsh;
	}

	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}

	public void setQueryequals_xydm(String queryequals_xydm) {
		this.queryequals_xydm = queryequals_xydm;
	}

	public String getQueryequals_xysh() {
		return queryequals_xysh;
	}

	public void setQueryequals_xysh(String queryequals_xysh) {
		this.queryequals_xysh = queryequals_xysh;
	}

	public String getQueryequals_zjxx() {
		return queryequals_zjxx;
	}

	public void setQueryequals_zjxx(String queryequals_zjxx) {
		this.queryequals_zjxx = queryequals_zjxx;
	}

	public String getQueryequals_zydm() {
		return queryequals_zydm;
	}

	public void setQueryequals_zydm(String queryequals_zydm) {
		this.queryequals_zydm = queryequals_zydm;
	}

	public String getQuerygreaterequal_jlsj() {
		return querygreaterequal_jlsj;
	}

	public void setQuerygreaterequal_jlsj(String querygreaterequal_jlsj) {
		this.querygreaterequal_jlsj = querygreaterequal_jlsj;
	}

	public String getQuerygreaterequal_lrsj() {
		return querygreaterequal_lrsj;
	}

	public void setQuerygreaterequal_lrsj(String querygreaterequal_lrsj) {
		this.querygreaterequal_lrsj = querygreaterequal_lrsj;
	}

	public String getQuerylessequal_jlsj() {
		return querylessequal_jlsj;
	}

	public void setQuerylessequal_jlsj(String querylessequal_jlsj) {
		this.querylessequal_jlsj = querylessequal_jlsj;
	}

	public String getQuerylessequal_lrsj() {
		return querylessequal_lrsj;
	}

	public void setQuerylessequal_lrsj(String querylessequal_lrsj) {
		this.querylessequal_lrsj = querylessequal_lrsj;
	}

	public String getQuerylike_stbh() {
		return querylike_stbh;
	}

	public void setQuerylike_stbh(String querylike_stbh) {
		this.querylike_stbh = querylike_stbh;
	}

	public String getQuerylike_wjmc() {
		return querylike_wjmc;
	}

	public void setQuerylike_wjmc(String querylike_wjmc) {
		this.querylike_wjmc = querylike_wjmc;
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

	public String[] getQuestionsBh() {
		return questionsBh;
	}

	public void setQuestionsBh(String[] questionsBh) {
		this.questionsBh = questionsBh;
	}

	public String[] getQuestionsDa() {
		return questionsDa;
	}

	public void setQuestionsDa(String[] questionsDa) {
		this.questionsDa = questionsDa;
	}

	public String getSave_bz() {
		return save_bz;
	}

	public void setSave_bz(String save_bz) {
		this.save_bz = save_bz;
	}

	public String getSave_jlsj() {
		return save_jlsj;
	}

	public void setSave_jlsj(String save_jlsj) {
		this.save_jlsj = save_jlsj;
	}

	public String getSave_nd() {
		return save_nd;
	}

	public void setSave_nd(String save_nd) {
		this.save_nd = save_nd;
	}

	public String getSave_stbh() {
		return save_stbh;
	}

	public void setSave_stbh(String save_stbh) {
		this.save_stbh = save_stbh;
	}

	public String getSave_stlx() {
		return save_stlx;
	}

	public void setSave_stlx(String save_stlx) {
		this.save_stlx = save_stlx;
	}

	public String getSave_stmc() {
		return save_stmc;
	}

	public void setSave_stmc(String save_stmc) {
		this.save_stmc = save_stmc;
	}

	public String getSave_wjmc() {
		return save_wjmc;
	}

	public void setSave_wjmc(String save_wjmc) {
		this.save_wjmc = save_wjmc;
	}

	public String getSave_xn() {
		return save_xn;
	}

	public void setSave_xn(String save_xn) {
		this.save_xn = save_xn;
	}

	public String getSave_xq() {
		return save_xq;
	}

	public void setSave_xq(String save_xq) {
		this.save_xq = save_xq;
	}

	public String getSfkq() {
		return sfkq;
	}

	public void setSfkq(String sfkq) {
		this.sfkq = sfkq;
	}

	public String getStbh() {
		return stbh;
	}

	public void setStbh(String stbh) {
		this.stbh = stbh;
	}

	public String getStlx() {
		return stlx;
	}

	public void setStlx(String stlx) {
		this.stlx = stlx;
	}

	public String getStmc() {
		return stmc;
	}

	public void setStmc(String stmc) {
		this.stmc = stmc;
	}

	public String getStss() {
		return stss;
	}

	public void setStss(String stss) {
		this.stss = stss;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWjbh() {
		return wjbh;
	}

	public void setWjbh(String wjbh) {
		this.wjbh = wjbh;
	}

	public String getWjmc() {
		return wjmc;
	}

	public void setWjmc(String wjmc) {
		this.wjmc = wjmc;
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

	public String getXhzgh() {
		return xhzgh;
	}

	public void setXhzgh(String xhzgh) {
		this.xhzgh = xhzgh;
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

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getXsmc() {
		return xsmc;
	}

	public void setXsmc(String xsmc) {
		this.xsmc = xsmc;
	}

	public String getXxsh() {
		return xxsh;
	}

	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
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

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getZjxx() {
		return zjxx;
	}

	public void setZjxx(String zjxx) {
		this.zjxx = zjxx;
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

	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	public String getQueryequals_mklx() {
		return queryequals_mklx;
	}

	public void setQueryequals_mklx(String queryequals_mklx) {
		this.queryequals_mklx = queryequals_mklx;
	}

	public String getDawk() {
		return dawk;
	}

	public void setDawk(String dawk) {
		this.dawk = dawk;
	}

	public String getKyxg() {
		return kyxg;
	}

	public void setKyxg(String kyxg) {
		this.kyxg = kyxg;
	}

	public String getTbjg() {
		return tbjg;
	}

	public void setTbjg(String tbjg) {
		this.tbjg = tbjg;
	}

	public String getWzjg() {
		return wzjg;
	}

	public void setWzjg(String wzjg) {
		this.wzjg = wzjg;
	}

	public String getJwy() {
		return jwy;
	}

	public void setJwy(String jwy) {
		this.jwy = jwy;
	}

	public String getGzdd() {
		return gzdd;
	}

	public void setGzdd(String gzdd) {
		this.gzdd = gzdd;
	}

	public String getGzgw() {
		return gzgw;
	}

	public void setGzgw(String gzgw) {
		this.gzgw = gzgw;
	}

	public String getLxfs() {
		return lxfs;
	}

	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}

	public String getSxdwmc() {
		return sxdwmc;
	}

	public void setSxdwmc(String sxdwmc) {
		this.sxdwmc = sxdwmc;
	}

	public String getWjid() {
		return wjid;
	}

	public void setWjid(String wjid) {
		this.wjid = wjid;
	}
}