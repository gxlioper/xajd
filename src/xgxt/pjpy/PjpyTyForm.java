package xgxt.pjpy;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.utils.Pages;

public class PjpyTyForm extends ActionForm {

	private static final long serialVersionUID = -9205711105806100577L;

	/* 通用 */
	Pages pages = new Pages();

	FormFile uploadFile;// 上传文件

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

	/* 评奖评优 测评小组 */
	private String[] cpxy;// 测评学院

	private String[] zwdm;// 职务代码

	private String zhfkg;// 综合分开关

	private String jxjkg;// 奖学金开关

	/* 评奖评优 智育分 */
	private String pycc;// 培养层次

	private String[] xfjdxh;// 同步者学号

	private String[] xfjd;// 学分绩点

	/* 评奖评优 综合分 */
	private String dyfbl;// 德育分比例

	private String zyfbl;// 智育分比例

	private String tyfbl;// 体育分比例

	private String nlfbl;// 能力分比例

	private String[] pjxh;// 评奖学号

	private String[] dyf;// 德育分

	private String[] zyf;// 智育分

	private String[] tyf;// 体育分

	private String[] nlf;// 能力分

	/* 评奖评优 文理科设置 */
	private String[] wlkbjdm;// 文理科班级代码

	private String bjlx;// 班级类型

	/* 评奖评优 条件设置 */
	private String jxjdm;// 奖学金代码

	private String queryequals_jxjdm;

	private String jxjlbdm;// 奖学金类别代码

	private String rychdm;// 荣誉称号代码

	private String tjzd;// 条件字段

	private String tjlx;// 条件类型

	private String tjz;// 条件值

	/* 评奖评优 兼得设置 */
	private String[] jxjjd;// 奖学金兼得

	private String[] rychjd;// 荣誉称号兼得

	private String[] fjdlx;// 非兼得类型

	private String[] fjddm;// 非兼得代码

	/* 评奖评优 校外奖学金 */
	private String xwjxjdm;// 校外奖学金代码

	/* 评奖评优 报表统计 */
	private String yhdm;// 银行代码

	private String yhlx;// 银行类型

	private String yhmc;// 银行名称

	/* 评奖评优 操行分维护 */

	private String xqdm;// 校区代码

	private String queryequals_xqdm;

	private String lddm;// 楼栋代码

	private String queryequals_lddm;

	private String cs;// 层数

	private String queryequals_cs;

	private String qsh;// 寝室号

	private String queryequals_qsh;

	private String qy;// '区域';

	private String lb;// '类别';

	private String xx;// '细项';

	private String[] jb1;// '级别1';

	private String[] jb2;// '级别2';

	private String[] jb3;// '级别3';

	private String[] jjf;// '加减分';

	private String[] fz;// '分值';

	private String[] rq;// '日期';

	private String[] sy;// '事由';

	private String[] shjg;// '审核结果';

	private String[] cxbz;// '操行备注';

	private String mrz;// '默认值';

	private String save_mrz;// '默认值';

	/* 评奖评优 人数设置 */

	private String bl;// 比例

	private String szlx;// '设置类型(jxj：奖学金；rych：荣誉称号)';

	private String queryequals_szlx;

	private String bmlx;// '部门类型(xy：学院；zy：专业; bj：班级)';

	private String[] szbm;// '设置部门代码';

	private String[] sznj;// '设置年级';

	private String szbl;// '设置比例';

	private String[] szrs;// '设置人数';

	/* =======================问卷调查============================= */

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

	/* =======================问卷调查 end========================= */

	/* =======================学生品行评价============================= */

	private String[] djr;// 答卷人

	private String[] fpxh;// 分配学号

	private String fpzt;// 分配状态

	private String queryequals_fpzt;

	private String bpjr;// 被评价人

	private String[] pjf;// 评价分
	
	/* =======================学生品行评价 end============================= */
	
	private String tjfs = "xq"; 
	
	private String ksrq;
	
	private String jsrq;
	
	public String getJsrq() {
		return jsrq;
	}

	public void setJsrq(String jsrq) {
		this.jsrq = jsrq;
	}

	public String getKsrq() {
		return ksrq;
	}

	public void setKsrq(String ksrq) {
		this.ksrq = ksrq;
	}

	public String getTjfs() {
		return tjfs;
	}

	public void setTjfs(String tjfs) {
		this.tjfs = tjfs;
	}

	public String[] getPjf() {
		return pjf;
	}

	public void setPjf(String[] pjf) {
		this.pjf = pjf;
	}

	public String getBpjr() {
		return bpjr;
	}

	public void setBpjr(String bpjr) {
		this.bpjr = bpjr;
	}

	public String[] getFpxh() {
		return fpxh;
	}

	public void setFpxh(String[] fpxh) {
		this.fpxh = fpxh;
	}

	public String[] getDjr() {
		return djr;
	}

	public void setDjr(String[] djr) {
		this.djr = djr;
	}

	public String getSfkq() {
		return sfkq;
	}

	public void setSfkq(String sfkq) {
		this.sfkq = sfkq;
	}

	public String[] getFpbj() {
		return fpbj;
	}

	public void setFpbj(String[] fpbj) {
		this.fpbj = fpbj;
	}

	public String[] getFpbh() {
		return fpbh;
	}

	public void setFpbh(String[] fpbh) {
		this.fpbh = fpbh;
	}

	public String getSave_stmc() {
		return save_stmc;
	}

	public void setSave_stmc(String save_stmc) {
		this.save_stmc = save_stmc;
	}

	public String getStmc() {
		return stmc;
	}

	public void setStmc(String stmc) {
		this.stmc = stmc;
	}

	public String getJlsj() {
		return jlsj;
	}

	public void setJlsj(String jlsj) {
		this.jlsj = jlsj;
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

	public String getSave_wjmc() {
		return save_wjmc;
	}

	public void setSave_wjmc(String save_wjmc) {
		this.save_wjmc = save_wjmc;
	}

	public String getWjmc() {
		return wjmc;
	}

	public void setWjmc(String wjmc) {
		this.wjmc = wjmc;
	}

	public String getBl() {
		return bl;
	}

	public void setBl(String bl) {
		this.bl = bl;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getBjlx() {
		return bjlx;
	}

	public void setBjlx(String bjlx) {
		this.bjlx = bjlx;
	}

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
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

	public String[] getCpxy() {
		return cpxy;
	}

	public void setCpxy(String[] cpxy) {
		this.cpxy = cpxy;
	}

	public String[] getDyf() {
		return dyf;
	}

	public void setDyf(String[] dyf) {
		this.dyf = dyf;
	}

	public String getDyfbl() {
		return dyfbl;
	}

	public void setDyfbl(String dyfbl) {
		this.dyfbl = dyfbl;
	}

	public String getFdyQx() {
		return fdyQx;
	}

	public void setFdyQx(String fdyQx) {
		this.fdyQx = fdyQx;
	}

	public String[] getFjddm() {
		return fjddm;
	}

	public void setFjddm(String[] fjddm) {
		this.fjddm = fjddm;
	}

	public String[] getFjdlx() {
		return fjdlx;
	}

	public void setFjdlx(String[] fjdlx) {
		this.fjdlx = fjdlx;
	}

	public String[] getFz() {
		return fz;
	}

	public void setFz(String[] fz) {
		this.fz = fz;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getJjf() {
		return jjf;
	}

	public void setJjf(String[] jjf) {
		this.jjf = jjf;
	}

	public String getJxjdm() {
		return jxjdm;
	}

	public void setJxjdm(String jxjdm) {
		this.jxjdm = jxjdm;
	}

	public String[] getJxjjd() {
		return jxjjd;
	}

	public void setJxjjd(String[] jxjjd) {
		this.jxjjd = jxjjd;
	}

	public String getJxjkg() {
		return jxjkg;
	}

	public void setJxjkg(String jxjkg) {
		this.jxjkg = jxjkg;
	}

	public String getJxjlbdm() {
		return jxjlbdm;
	}

	public void setJxjlbdm(String jxjlbdm) {
		this.jxjlbdm = jxjlbdm;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
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

	public String[] getNlf() {
		return nlf;
	}

	public void setNlf(String[] nlf) {
		this.nlf = nlf;
	}

	public String getNlfbl() {
		return nlfbl;
	}

	public void setNlfbl(String nlfbl) {
		this.nlfbl = nlfbl;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String[] getPjxh() {
		return pjxh;
	}

	public void setPjxh(String[] pjxh) {
		this.pjxh = pjxh;
	}

	public String getPycc() {
		return pycc;
	}

	public void setPycc(String pycc) {
		this.pycc = pycc;
	}

	public String[] getRq() {
		return rq;
	}

	public void setRq(String[] rq) {
		this.rq = rq;
	}

	public String getRychdm() {
		return rychdm;
	}

	public void setRychdm(String rychdm) {
		this.rychdm = rychdm;
	}

	public String[] getRychjd() {
		return rychjd;
	}

	public void setRychjd(String[] rychjd) {
		this.rychjd = rychjd;
	}

	public String[] getShjg() {
		return shjg;
	}

	public void setShjg(String[] shjg) {
		this.shjg = shjg;
	}

	public String[] getSy() {
		return sy;
	}

	public void setSy(String[] sy) {
		this.sy = sy;
	}

	public String getTjlx() {
		return tjlx;
	}

	public void setTjlx(String tjlx) {
		this.tjlx = tjlx;
	}

	public String getTjz() {
		return tjz;
	}

	public void setTjz(String tjz) {
		this.tjz = tjz;
	}

	public String getTjzd() {
		return tjzd;
	}

	public void setTjzd(String tjzd) {
		this.tjzd = tjzd;
	}

	public String[] getTyf() {
		return tyf;
	}

	public void setTyf(String[] tyf) {
		this.tyf = tyf;
	}

	public String getTyfbl() {
		return tyfbl;
	}

	public void setTyfbl(String tyfbl) {
		this.tyfbl = tyfbl;
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

	public String[] getWlkbjdm() {
		return wlkbjdm;
	}

	public void setWlkbjdm(String[] wlkbjdm) {
		this.wlkbjdm = wlkbjdm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String[] getXfjd() {
		return xfjd;
	}

	public void setXfjd(String[] xfjd) {
		this.xfjd = xfjd;
	}

	public String[] getXfjdxh() {
		return xfjdxh;
	}

	public void setXfjdxh(String[] xfjdxh) {
		this.xfjdxh = xfjdxh;
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

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getXwjxjdm() {
		return xwjxjdm;
	}

	public void setXwjxjdm(String xwjxjdm) {
		this.xwjxjdm = xwjxjdm;
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

	public String getYhdm() {
		return yhdm;
	}

	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}

	public String getYhlx() {
		return yhlx;
	}

	public void setYhlx(String yhlx) {
		this.yhlx = yhlx;
	}

	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String getZhfkg() {
		return zhfkg;
	}

	public void setZhfkg(String zhfkg) {
		this.zhfkg = zhfkg;
	}

	public String[] getZwdm() {
		return zwdm;
	}

	public void setZwdm(String[] zwdm) {
		this.zwdm = zwdm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String[] getZyf() {
		return zyf;
	}

	public void setZyf(String[] zyf) {
		this.zyf = zyf;
	}

	public String getZyfbl() {
		return zyfbl;
	}

	public void setZyfbl(String zyfbl) {
		this.zyfbl = zyfbl;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getMklx() {
		return mklx;
	}

	public void setMklx(String mklx) {
		this.mklx = mklx;
	}

	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}

	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
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

	public String getQueryequals_zydm() {
		return queryequals_zydm;
	}

	public void setQueryequals_zydm(String queryequals_zydm) {
		this.queryequals_zydm = queryequals_zydm;
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

	public String getCs() {
		return cs;
	}

	public void setCs(String cs) {
		this.cs = cs;
	}

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

	public String getQueryequals_cs() {
		return queryequals_cs;
	}

	public void setQueryequals_cs(String queryequals_cs) {
		this.queryequals_cs = queryequals_cs;
	}

	public String getQueryequals_lddm() {
		return queryequals_lddm;
	}

	public void setQueryequals_lddm(String queryequals_lddm) {
		this.queryequals_lddm = queryequals_lddm;
	}

	public String getQueryequals_qsh() {
		return queryequals_qsh;
	}

	public void setQueryequals_qsh(String queryequals_qsh) {
		this.queryequals_qsh = queryequals_qsh;
	}

	public String getQueryequals_xqdm() {
		return queryequals_xqdm;
	}

	public void setQueryequals_xqdm(String queryequals_xqdm) {
		this.queryequals_xqdm = queryequals_xqdm;
	}

	public String getXqdm() {
		return xqdm;
	}

	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}

	public String[] getJb1() {
		return jb1;
	}

	public void setJb1(String[] jb1) {
		this.jb1 = jb1;
	}

	public String[] getJb2() {
		return jb2;
	}

	public void setJb2(String[] jb2) {
		this.jb2 = jb2;
	}

	public String[] getJb3() {
		return jb3;
	}

	public void setJb3(String[] jb3) {
		this.jb3 = jb3;
	}

	public String getLb() {
		return lb;
	}

	public void setLb(String lb) {
		this.lb = lb;
	}

	public String getQy() {
		return qy;
	}

	public void setQy(String qy) {
		this.qy = qy;
	}

	public String getXx() {
		return xx;
	}

	public void setXx(String xx) {
		this.xx = xx;
	}

	public String[] getCxbz() {
		return cxbz;
	}

	public void setCxbz(String[] cxbz) {
		this.cxbz = cxbz;
	}

	public String getMrz() {
		return mrz;
	}

	public void setMrz(String mrz) {
		this.mrz = mrz;
	}

	public String getSave_mrz() {
		return save_mrz;
	}

	public void setSave_mrz(String save_mrz) {
		this.save_mrz = save_mrz;
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

	public String getQueryequals_jxjdm() {
		return queryequals_jxjdm;
	}

	public void setQueryequals_jxjdm(String queryequals_jxjdm) {
		this.queryequals_jxjdm = queryequals_jxjdm;
	}

	public String getBmlx() {
		return bmlx;
	}

	public void setBmlx(String bmlx) {
		this.bmlx = bmlx;
	}

	public String getSzbl() {
		return szbl;
	}

	public void setSzbl(String szbl) {
		this.szbl = szbl;
	}

	public String[] getSzbm() {
		return szbm;
	}

	public void setSzbm(String[] szbm) {
		this.szbm = szbm;
	}

	public String getSzlx() {
		return szlx;
	}

	public void setSzlx(String szlx) {
		this.szlx = szlx;
	}

	public String[] getSzrs() {
		return szrs;
	}

	public void setSzrs(String[] szrs) {
		this.szrs = szrs;
	}

	public String[] getSznj() {
		return sznj;
	}

	public void setSznj(String[] sznj) {
		this.sznj = sznj;
	}

	public String getQueryequals_lx() {
		return queryequals_lx;
	}

	public void setQueryequals_lx(String queryequals_lx) {
		this.queryequals_lx = queryequals_lx;
	}

	public String getQueryequals_szlx() {
		return queryequals_szlx;
	}

	public void setQueryequals_szlx(String queryequals_szlx) {
		this.queryequals_szlx = queryequals_szlx;
	}

	public String getQueryequals_stlx() {
		return queryequals_stlx;
	}

	public void setQueryequals_stlx(String queryequals_stlx) {
		this.queryequals_stlx = queryequals_stlx;
	}

	public String getSave_stlx() {
		return save_stlx;
	}

	public void setSave_stlx(String save_stlx) {
		this.save_stlx = save_stlx;
	}

	public String getStlx() {
		return stlx;
	}

	public void setStlx(String stlx) {
		this.stlx = stlx;
	}

	public String getQuerylike_stbh() {
		return querylike_stbh;
	}

	public void setQuerylike_stbh(String querylike_stbh) {
		this.querylike_stbh = querylike_stbh;
	}

	public String getSave_stbh() {
		return save_stbh;
	}

	public void setSave_stbh(String save_stbh) {
		this.save_stbh = save_stbh;
	}

	public String getStbh() {
		return stbh;
	}

	public void setStbh(String stbh) {
		this.stbh = stbh;
	}

	public String[] getBzda() {
		return bzda;
	}

	public void setBzda(String[] bzda) {
		this.bzda = bzda;
	}

	public String[] getDabh() {
		return dabh;
	}

	public void setDabh(String[] dabh) {
		this.dabh = dabh;
	}

	public String[] getDamc() {
		return damc;
	}

	public void setDamc(String[] damc) {
		this.damc = damc;
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

	public String getHaveda() {
		return haveda;
	}

	public void setHaveda(String haveda) {
		this.haveda = haveda;
	}

	public String getLxmc() {
		return lxmc;
	}

	public void setLxmc(String lxmc) {
		this.lxmc = lxmc;
	}

	public String getQueryequals_haveda() {
		return queryequals_haveda;
	}

	public void setQueryequals_haveda(String queryequals_haveda) {
		this.queryequals_haveda = queryequals_haveda;
	}

	public String getXsmc() {
		return xsmc;
	}

	public void setXsmc(String xsmc) {
		this.xsmc = xsmc;
	}

	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}

	public void setPrimarykey_checkVal(String[] primarykey_checkVal) {
		this.primarykey_checkVal = primarykey_checkVal;
	}

	public String getQuerygreaterequal_jlsj() {
		return querygreaterequal_jlsj;
	}

	public void setQuerygreaterequal_jlsj(String querygreaterequal_jlsj) {
		this.querygreaterequal_jlsj = querygreaterequal_jlsj;
	}

	public String getQuerylessequal_jlsj() {
		return querylessequal_jlsj;
	}

	public void setQuerylessequal_jlsj(String querylessequal_jlsj) {
		this.querylessequal_jlsj = querylessequal_jlsj;
	}

	public String getWjbh() {
		return wjbh;
	}

	public void setWjbh(String wjbh) {
		this.wjbh = wjbh;
	}

	public String getXhzgh() {
		return xhzgh;
	}

	public void setXhzgh(String xhzgh) {
		this.xhzgh = xhzgh;
	}

	public String[] getAllChooseDa() {
		return allChooseDa;
	}

	public void setAllChooseDa(String[] allChooseDa) {
		this.allChooseDa = allChooseDa;
	}

	public String[] getOneChooseDa() {
		return oneChooseDa;
	}

	public void setOneChooseDa(String[] oneChooseDa) {
		this.oneChooseDa = oneChooseDa;
	}

	public String[] getAllChooseBh() {
		return allChooseBh;
	}

	public void setAllChooseBh(String[] allChooseBh) {
		this.allChooseBh = allChooseBh;
	}

	public String[] getOneChooseBh() {
		return oneChooseBh;
	}

	public void setOneChooseBh(String[] oneChooseBh) {
		this.oneChooseBh = oneChooseBh;
	}

	public String getQueryequals_id() {
		return queryequals_id;
	}

	public void setQueryequals_id(String queryequals_id) {
		this.queryequals_id = queryequals_id;
	}

	public String getQuerylike_wjmc() {
		return querylike_wjmc;
	}

	public void setQuerylike_wjmc(String querylike_wjmc) {
		this.querylike_wjmc = querylike_wjmc;
	}

	public String getQueryequals_stss() {
		return queryequals_stss;
	}

	public void setQueryequals_stss(String queryequals_stss) {
		this.queryequals_stss = queryequals_stss;
	}

	public String getStss() {
		return stss;
	}

	public void setStss(String stss) {
		this.stss = stss;
	}

	public String getIsover() {
		return isover;
	}

	public void setIsover(String isover) {
		this.isover = isover;
	}

	public String getQueryequals_isover() {
		return queryequals_isover;
	}

	public void setQueryequals_isover(String queryequals_isover) {
		this.queryequals_isover = queryequals_isover;
	}

	public String getQueryequals_zjxx() {
		return queryequals_zjxx;
	}

	public void setQueryequals_zjxx(String queryequals_zjxx) {
		this.queryequals_zjxx = queryequals_zjxx;
	}

	public String getZjxx() {
		return zjxx;
	}

	public void setZjxx(String zjxx) {
		this.zjxx = zjxx;
	}

	public String[] getDanr() {
		return danr;
	}

	public void setDanr(String[] danr) {
		this.danr = danr;
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

	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	public String getQueryequals_sfkq() {
		return queryequals_sfkq;
	}

	public void setQueryequals_sfkq(String queryequals_sfkq) {
		this.queryequals_sfkq = queryequals_sfkq;
	}

	public String getFpzt() {
		return fpzt;
	}

	public void setFpzt(String fpzt) {
		this.fpzt = fpzt;
	}

	public String getQueryequals_fpzt() {
		return queryequals_fpzt;
	}

	public void setQueryequals_fpzt(String queryequals_fpzt) {
		this.queryequals_fpzt = queryequals_fpzt;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getLrsj() {
		return lrsj;
	}

	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getQuerygreaterequal_lrsj() {
		return querygreaterequal_lrsj;
	}

	public void setQuerygreaterequal_lrsj(String querygreaterequal_lrsj) {
		this.querygreaterequal_lrsj = querygreaterequal_lrsj;
	}

	public String getQuerylessequal_lrsj() {
		return querylessequal_lrsj;
	}

	public void setQuerylessequal_lrsj(String querylessequal_lrsj) {
		this.querylessequal_lrsj = querylessequal_lrsj;
	}
}
