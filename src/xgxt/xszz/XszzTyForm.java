package xgxt.xszz;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.utils.Pages;

public class XszzTyForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* 通用 */
	Pages pages = new Pages();

	FormFile uploadFile;// 上传文件

	private String save_bzrshsj;
	
	private String save_fdyshsj;
	
	private String iskns;//是否困难生认定，贫困生认定项目单独抽出了
	
	private boolean flg;//字段设置后要更新内存中的项目字段，by 屈朋辉
	
	private String pjcj;

	private String pjcjtj;
	
	private String jqpjf;
	
	private String jqpjftj;
	
	private String jqpjjd;
	
	private String jqpjjdtj;
	
	private String[] bkjdxm;

	private String queryequals_isxb;

	private String save_xq;

	private String save_nd;

	private String isxb;// 是否新办

	private String save_bjgkms;// 不及格科目数

	private String mklx;// 模块类型

	private String[] checkVal;// 批处理

	private String[] primarykey_checkVal;

	private String pkValue;// 主键值

	private String xysh;// 学院审核

	private String queryequals_xysh;

	private String xxsh;// 学校审核

	private String szbm;// 所在部门

	private String queryequals_xxsh;

	private String userName;// 用户名

	private String userDep;// 所在部门

	private String zgh;// 职工号

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

	private String xq;// 学期

	private String queryequals_xq;

	private String nd;// 年度

	private String queryequals_nd;

	private String xydm;// 学院代码

	private String queryequals_xydm;

	private String xymc;// 学院名称

	private String zydm;// 专业代码

	private String queryequals_zydm;

	private String zymc;// 专业名称

	private String bjdm;// 班级代码

	private String queryequals_bjdm;

	private String bjmc;// 班级名称

	private String bz;// 备注

	private String lx;// 类型

	private String queryequals_lx;
	
	private String shsj1;
	
	private String shsj2;
	
	private String shsj3;

	private String id;// ID

	private String sqly;// '申请理由';

	private String xyshsj;// '学院审核时间';

	private String xyshyj;// '学院审核意见';

	private String xxshsj;// '学校审核时间';

	private String xxshyj;// '学校审核意见';

	private String bjsh;// '班级审核';

	private String queryequals_bjsh;

	private String bjshyj;// '班级审核意见';

	private String bjshsj;// '班级审核时间';

	private String save_xh;// '学号';

	private String save_xn;// '学年';

	private String save_xysh;// '学院审核';

	private String save_xyshsj;// '学院审核时间';

	private String save_xyshyj;// '学院审核意见';

	private String save_xxsh;// '学校审核';

	private String save_xxshsj;// '学校审核时间';

	private String save_xxshyj;// '学校审核意见';

	private String save_bjsh;// '班级审核';

	private String save_bjshyj;// '班级审核意见';

	private String save_bjshsj;// '班级审核时间';

	private String save_bz;// '备注';

	private String shzd;// 审核字段

	private String bl;// 比例

	private String kssj;// 开始时间

	private String jssj;// 结束时间

	// ======================贵州大学========================================
	/* 副食补助 */
	private String[] zyxmList;// 专业项目

	private String[] bzzy;// 补助专业

	private String[] all_xh;// 全部学号

	private String xmdm;// '项目代码';

	private String ffje;// '发布金额';

	private String bzlx;// '补助类型';

	private String queryequals_bzlx;

	private String yf;// '月份';

	private String queryequals_yf;

	private String querygreaterequal_yf;

	private String querylessequal_yf;

	// ======================海南大学========================================
	/* 参数设置 */
	private String[] szxy;// '学院代码';

	private String kg;// '开关';

	private String xmmc;// '项目名称';

	private String[] xmjb;// '项目级别';

	private String[] xmbl;// '项目比例';

	private String[] xmrs;// '项目人数';

	/* 经济困难生认定 */
	private String knsjb;// '困难生级别';

	private String queryequals_knsjb;

	private String jtrs;// '家庭人数';

	private String jtnzsr;// '家庭年总收入';

	private String jtsrly;// '家庭收入主要来源';

	private String jtyzsr;// '家庭月总收入';

	private String jtdh;// '家庭电话';

	private String jtyb;// '家庭邮编';

	private String jtrjsr;// '家庭人均月收入';

	private String jtfzqk;// '家庭负债情况';

	private String jtdd;// '家庭住址';

	private String jtdz;// '家庭地址';

	private String sfge;// '是否孤儿';

	private String sfcj;// '是否残疾';

	private String sfdb;// '是否低保户';

	private String sflszn;// '是否烈士子女';

	private String sfzrch;// '是否遭受自然灾害';

	private String sfjthb;// '家庭成员是否长期患重病';

	private String sfpkzm;// '是否有贫困证明';

	private String jtdxrs;// '家中读大学人数';

	private String dydj;// '德育评定等级';

	private String xsjtjjqk;// '家庭经济情况';

	private String brjdqzmbqk;// '本人获奖情况';

	private String qtqk;// '其他情况';

	private String sy;// '生源';

	private String scdz;// '贫困证明上传地址';

	private String sfncpkdq;// '是否农村贫困地区';

	private String sfczxgjt;// '是否城镇下岗家庭';

	private String sffmxcj;// '是否父母新残疾';

	private String sfhzdjb;// '是否患重大疾病';

	private String sfdqjt;// '是否单亲家庭';

	private String sfgr;// '是否孤儿';

	private String sfzrzh;// '是否自然灾害';

	private String sfjtrkd;// '是否家庭人口多';

	private String sfqt;// '是否其它';

	private String qtnr;// '其它内容';

	private String pkyyxxsm;// '贫困原因详细说明';
	
	private String mzbm_xxtxdz;//'民政部门详细通讯地址';

	private String mzbm_yzbm;//'民政部门邮政编码';

	private String mzbm_lxdh;//'民政部门联系电话';
	
	private String save_jtrs;// '家庭人数';

	private String save_jtyb;// '家庭邮编';

	private String save_jtnzsr;// '家庭年总收入';

	private String save_jtsrly;// '家庭收入主要来源';

	private String save_jtyzsr;// '家庭月总收入';

	private String save_jtdh;// '家庭电话';

	private String save_jtrjsr;// '家庭人均月收入';

	private String save_jtfzqk;// '家庭负债情况';

	private String save_jtdd;// '家庭住址';

	private String save_jtdz;// '家庭地址';

	private String save_sfge;// '是否孤儿';

	private String save_sfcj;// '是否残疾';

	private String save_sfdb;// '是否低保户';

	private String save_sflszn;// '是否烈士子女';

	private String save_sfzrch;// '是否遭受自然灾害';

	private String save_sfjthb;// '家庭成员是否长期患重病';

	private String save_sfpkzm;// '是否有贫困证明';

	private String save_jtdxrs;// '家中读大学人数';

	private String save_dydj;// '德育评定等级';

	private String save_xsjtjjqk;// '家庭经济情况';

	private String save_brjdqzmbqk;// '本人获奖情况';

	private String save_qtqk;// '其他情况';

	private String save_scdz;// '贫困证明上传地址';

	private String save_sy;// '生源';

	private String save_sfncpkdq;// '是否农村贫困地区';

	private String save_sfczxgjt;// '是否城镇下岗家庭';

	private String save_sffmxcj;// '是否父母新残疾';

	private String save_sfhzdjb;// '是否患重大疾病';

	private String save_sfdqjt;// '是否单亲家庭';

	private String save_sfgr;// '是否孤儿';

	private String save_sfzrzh;// '是否自然灾害';

	private String save_sfjtrkd;// '是否家庭人口多';

	private String save_sfqt;// '是否其它';

	private String save_qtnr;// '其它内容';

	private String save_pkyyxxsm;// '贫困原因详细说明';
	
	private String save_xxtxdz;//'民政部门详细通讯地址';

	private String save_yzbm;//'民政部门邮政编码';

	private String save_lxdh;//'民政部门联系电话';
	
	// =====================南京技师=================================
	private String xfsfxm;// '收费项目';

	private String querylike_xfsfxm;

	private String xfyjje;// '应缴金额';

	private String xfsjje;// '实缴金额';

	private String xfsfqf;// '是否欠费';

	private String queryequals_xfsfqf;

	// =====================广州大学 条件设置=================================

	private String tjzd;// 条件字段

	private String tjlx;// 条件类型

	private String tjz;// 条件值

	// ======================我是公用的~========================================

	/* ======================项目相关=================================== */

	private String xssx;// '显示顺序';

	private String querylike_xmmc;// '项目名称';

	private String xmlb;// '项目类别';

	private String queryequals_xmlb;

	private String pdsj;// '评定时间';

	private String queryequals_pdsj;

	private String xmb;// '项目表';

	private String mrxm;// '默认项目';

	private String xxdm;// '学校代码';

	private String xmsm;// '项目说明';

	private String sfje;// '是否涉及金额';

	private String sffj;// '是否分级';

	private String kgzt;// '开关状态';

	private String shjb;// '审核级别';

	private String bzrsh;// '班主任审核';

	private String fdysh;// '辅导员审核';

	// private String xysh;//'学院审核';
	// private String xxsh;//'学校审核';

	private String rskz;// '人数控制';

	private String bzrkz;// '班主任控制';

	private String fdykz;// '辅导员控制';

	private String xykz;// '学院控制';

	private String xxkz;// '学校控制';

	private String kzjb;// '控制级别';

	private String sqzq;// '申请周期';

	private String rssx;// '人数上限';

	private String[] kzr;// '控制人';

	private String[] shr;// '审核人';

	private String lssh;// '老师审核';

	private String jelx;// '金额类型';

	private String[] fjxmdm;// '项目代码';

	private String[] fjmc;// '级别名称';

	private String[] nojemc;// '不需金额项目';

	private String[] qdjemc;// '确定金额项目';

	private String[] qjjemc;// '区间金额项目';

	private String[] fjxxje;// '下限金额';

	private String[] fjsxje;// '上限金额';

	private String[] fjqdje;// '确定金额';

	private String nofjje;// '无分级确定金额';

	private String nofjsx;// '无分级上限金额';

	private String nofjxx;// '无分级下限金额';

	private String[] xmtjb;// '项目条件表';

	private String[] xmtj;// '项目条件';

	private String[] xmtjz;// '项目条件值';

	private String queryequals_xmdm;

	private String queryequals_mrxm;

	private String queryequals_sqzq;

	private String queryequals_sfje;

	private String queryequals_sffj;

	private String queryequals_jelx;

	private String queryequals_shjb;

	private String queryequals_rskz;

	private String[] zzxmdm;// 资助项目代码

	private String[] zzxmkg;// 资助项目开关

	private String bmjb;// '部门级别';

	private String[] bmdm;// '部门代码';

	private String[] szrs;// '设置人数';

	private String fpfs;// 分配方式

	private String[] iscz;// '是否存在';

	private String[] sznj;// '是否存在';

	private String fprs;// '分配人数';

	/* ======================学生操作=================================== */

	private String sqsj;// '申请时间';

	private String sqsjCn;// '申请时间';

	/* ======================家庭情况调查=================================== */

	private String zd;// '字段';

	private String path;// '路径';

	// private String sqsj;//'申请时间';
	// private String jtrjsr;//'家庭人均收入';
	private String sfgc;// '是否孤残';

	private String sfdq;// '是否单亲';

	private String lszn;// '烈士子女';

	private String yhzzqk;// '已获资助情况';

	private String jtszqk;// '家庭受灾情况';

	private String tfsjqk;// '突发事件情况';

	private String cjnmqk;// '残疾年迈情况';

	private String jtsyqk;// '家庭失业情况';

	private String jtqzqk;// '家庭欠债情况';

	private String jtqtqk;// '家庭其他情况';

	private String jthk;// 家庭户口

	private String srly;// 收入来源

	// private String bzrsh;//'班主任审核';
	private String bzrshsj;// '班主任审核时间';

	private String bzrshyj;// '班主任审核意见';

	// private String fdysh;//'辅导员审核';
	private String fdyshsj;// '辅导员审核时间';

	private String fdyshyj;// '辅导员审核意见';

	private String save_hdbh;// 合同编号

	private String querylike_hdbh;

	// private String xysh;//'学院审核';
	// private String xyshsj;//'学院审核时间';
	// private String xyshyj;//'学院审核意见';
	// private String xxsh;//'学校审核';
	// private String xxshsj;//'学校审核时间';
	// private String xxshyj;//'学校审核意见';
	// private String jtrs ;//'家庭人数';
	// private String jtnzsr ;//'家庭年总收入';
	// private String jtyzsr ;//'家庭月总收入';

	private String jtrjysr;// '家庭人均月收入';

	// private String jtsrly ;//'家庭收入来源';
	private String jtddxrs;// '家庭读大学人数';

	private String jtjbjjqk;// '家庭基本经济情况';

	private String dypddj;// '德育评定等级';

	private String brhjqk;// '本人获奖情况';

	private String tpsc;// '图片上传';

	private String[] jb;// '级别';

	private String[] jbmc;// '级别名称';

	private String xmzzje;// 项目资助金额

	private String xmzzjb;// 项目资助级别

	private String save_xmzzje;

	private String save_xmzzjb;

	private String shpk;// '审核主键';

	private String shzt1;// '审核状态1';

	private String shzt2;// '审核状态2';

	private String shzt3;// '审核状态3';

	private String shzt1yj;// '审核状态1意见';

	private String shzt2yj;// '审核状态2意见';

	private String shzt3yj;// '审核状态3意见';

	private String gx;// '关系';

	private String[] cyxm;// '成员姓名';

	private String[] cynl;// '成员年龄';

	private String[] cygx;// '成员关系';

	private String[] cygzdw;// '工作单位';

	private String[] cyzy;// '职业';

	private String[] cydh;// '联系电话';

	private String[] cynsr;// '年收入';

	private String[] cynzc;// '年支出';

	private String[] cyyb;// '邮编';

	private String[] cyysr;// '月收入';

	private String[] cyjkzk;// '健康状况';

	private String save_xmdm;

	private String save_sqsj;

	private String sqsm;// 申请说明

	private String zje;// '总金额';

	private String xnje;// '学年金额';

	private String tjbbdm;// 统计报表代码

	private String bjpm;// '班级排名';

	private String sbxqxfjd;// '上半学期学分绩点';

	private String xbxqxfjd;// '下半学期学分绩点';

	private String bxkms;// '必修课门数';

	private String yxms;// '优秀门数';

	private String lhms;// '良好门数';

	private String zycjpm;// '专业成绩排名';

	private String zhcpcj;// '综合测评成绩';

	private String zcbjpm;// '综测班级排名';

	private String wysp;// '外语水平';

	private String jsjsp;// '计算机水平';

	private String hjrq1;// '获奖日期1';

	private String hjmc1;// '获奖名称1';

	private String bjdw1;// '颁奖单位1';

	private String hjrq2;// '获奖日期2';

	private String hjmc2;// '获奖名称2';

	private String bjdw2;// '颁奖单位2';

	private String hjrq3;// '获奖日期3';

	private String hjmc3;// '获奖名称3';

	private String bjdw3;// '颁奖单位3';

	private String hjrq4;// '获奖日期4';

	private String hjmc4;// '获奖名称4';

	private String bjdw4;// '颁奖单位4';

	private String xf;// '学分';

	private String kyqk;// '科研情况';

	// ======================我是公用的~========================================
	
	private String widthType;
	
	public String getWidthType() {
		return widthType;
	}

	public void setWidthType(String widthType) {
		this.widthType = widthType;
	}

	public String getSqsm() {
		return sqsm;
	}

	public String getTjbbdm() {
		return tjbbdm;
	}

	public void setTjbbdm(String tjbbdm) {
		this.tjbbdm = tjbbdm;
	}

	public void setSqsm(String sqsm) {
		this.sqsm = sqsm;
	}

	public String getSave_sqsj() {
		return save_sqsj;
	}

	public void setSave_sqsj(String save_sqsj) {
		this.save_sqsj = save_sqsj;
	}

	public String getSave_xmdm() {
		return save_xmdm;
	}

	public void setSave_xmdm(String save_xmdm) {
		this.save_xmdm = save_xmdm;
	}

	public String[] getZzxmdm() {
		return zzxmdm;
	}

	public void setZzxmdm(String[] zzxmdm) {
		this.zzxmdm = zzxmdm;
	}

	public String[] getZzxmkg() {
		return zzxmkg;
	}

	public void setZzxmkg(String[] zzxmkg) {
		this.zzxmkg = zzxmkg;
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

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
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

	public String getSave_bz() {
		return save_bz;
	}

	public void setSave_bz(String save_bz) {
		this.save_bz = save_bz;
	}

	public String getSave_xh() {
		return save_xh;
	}

	public void setSave_xh(String save_xh) {
		this.save_xh = save_xh;
	}

	public String getSave_xn() {
		return save_xn;
	}

	public void setSave_xn(String save_xn) {
		this.save_xn = save_xn;
	}

	public String getSave_xxsh() {
		return save_xxsh;
	}

	public void setSave_xxsh(String save_xxsh) {
		this.save_xxsh = save_xxsh;
	}

	public String getSave_xxshsj() {
		return save_xxshsj;
	}

	public void setSave_xxshsj(String save_xxshsj) {
		this.save_xxshsj = save_xxshsj;
	}

	public String getSave_xxshyj() {
		return save_xxshyj;
	}

	public void setSave_xxshyj(String save_xxshyj) {
		this.save_xxshyj = save_xxshyj;
	}

	public String getSave_xysh() {
		return save_xysh;
	}

	public void setSave_xysh(String save_xysh) {
		this.save_xysh = save_xysh;
	}

	public String getSave_xyshsj() {
		return save_xyshsj;
	}

	public void setSave_xyshsj(String save_xyshsj) {
		this.save_xyshsj = save_xyshsj;
	}

	public String getSave_xyshyj() {
		return save_xyshyj;
	}

	public void setSave_xyshyj(String save_xyshyj) {
		this.save_xyshyj = save_xyshyj;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
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

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
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

	public String getFfje() {
		return ffje;
	}

	public void setFfje(String ffje) {
		this.ffje = ffje;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String[] getZyxmList() {
		return zyxmList;
	}

	public void setZyxmList(String[] zyxmList) {
		this.zyxmList = zyxmList;
	}

	public String getBzlx() {
		return bzlx;
	}

	public void setBzlx(String bzlx) {
		this.bzlx = bzlx;
	}

	public String[] getBzzy() {
		return bzzy;
	}

	public void setBzzy(String[] bzzy) {
		this.bzzy = bzzy;
	}

	public String getYf() {
		return yf;
	}

	public void setYf(String yf) {
		this.yf = yf;
	}

	public String getQueryequals_bzlx() {
		return queryequals_bzlx;
	}

	public void setQueryequals_bzlx(String queryequals_bzlx) {
		this.queryequals_bzlx = queryequals_bzlx;
	}

	public String getQueryequals_yf() {
		return queryequals_yf;
	}

	public void setQueryequals_yf(String queryequals_yf) {
		this.queryequals_yf = queryequals_yf;
	}

	public String[] getAll_xh() {
		return all_xh;
	}

	public void setAll_xh(String[] all_xh) {
		this.all_xh = all_xh;
	}

	public String getQuerygreaterequal_yf() {
		return querygreaterequal_yf;
	}

	public void setQuerygreaterequal_yf(String querygreaterequal_yf) {
		this.querygreaterequal_yf = querygreaterequal_yf;
	}

	public String getQuerylessequal_yf() {
		return querylessequal_yf;
	}

	public void setQuerylessequal_yf(String querylessequal_yf) {
		this.querylessequal_yf = querylessequal_yf;
	}

	public String getBrjdqzmbqk() {
		return brjdqzmbqk;
	}

	public void setBrjdqzmbqk(String brjdqzmbqk) {
		this.brjdqzmbqk = brjdqzmbqk;
	}

	public String getDydj() {
		return dydj;
	}

	public void setDydj(String dydj) {
		this.dydj = dydj;
	}

	public String getJtdd() {
		return jtdd;
	}

	public void setJtdd(String jtdd) {
		this.jtdd = jtdd;
	}

	public String getJtdh() {
		return jtdh;
	}

	public void setJtdh(String jtdh) {
		this.jtdh = jtdh;
	}

	public String getJtdxrs() {
		return jtdxrs;
	}

	public void setJtdxrs(String jtdxrs) {
		this.jtdxrs = jtdxrs;
	}

	public String getJtfzqk() {
		return jtfzqk;
	}

	public void setJtfzqk(String jtfzqk) {
		this.jtfzqk = jtfzqk;
	}

	public String getJtnzsr() {
		return jtnzsr;
	}

	public void setJtnzsr(String jtnzsr) {
		this.jtnzsr = jtnzsr;
	}

	public String getJtrjsr() {
		return jtrjsr;
	}

	public void setJtrjsr(String jtrjsr) {
		this.jtrjsr = jtrjsr;
	}

	public String getJtrs() {
		return jtrs;
	}

	public void setJtrs(String jtrs) {
		this.jtrs = jtrs;
	}

	public String getJtsrly() {
		return jtsrly;
	}

	public void setJtsrly(String jtsrly) {
		this.jtsrly = jtsrly;
	}

	public String getJtyzsr() {
		return jtyzsr;
	}

	public void setJtyzsr(String jtyzsr) {
		this.jtyzsr = jtyzsr;
	}

	public String getQtqk() {
		return qtqk;
	}

	public void setQtqk(String qtqk) {
		this.qtqk = qtqk;
	}

	public String getScdz() {
		return scdz;
	}

	public void setScdz(String scdz) {
		this.scdz = scdz;
	}

	public String getSfcj() {
		return sfcj;
	}

	public void setSfcj(String sfcj) {
		this.sfcj = sfcj;
	}

	public String getSfdb() {
		return sfdb;
	}

	public void setSfdb(String sfdb) {
		this.sfdb = sfdb;
	}

	public String getSfge() {
		return sfge;
	}

	public void setSfge(String sfge) {
		this.sfge = sfge;
	}

	public String getSfjthb() {
		return sfjthb;
	}

	public void setSfjthb(String sfjthb) {
		this.sfjthb = sfjthb;
	}

	public String getSflszn() {
		return sflszn;
	}

	public void setSflszn(String sflszn) {
		this.sflszn = sflszn;
	}

	public String getSfpkzm() {
		return sfpkzm;
	}

	public void setSfpkzm(String sfpkzm) {
		this.sfpkzm = sfpkzm;
	}

	public String getSfzrch() {
		return sfzrch;
	}

	public void setSfzrch(String sfzrch) {
		this.sfzrch = sfzrch;
	}

	public String getXsjtjjqk() {
		return xsjtjjqk;
	}

	public void setXsjtjjqk(String xsjtjjqk) {
		this.xsjtjjqk = xsjtjjqk;
	}

	public String getSave_brjdqzmbqk() {
		return save_brjdqzmbqk;
	}

	public void setSave_brjdqzmbqk(String save_brjdqzmbqk) {
		this.save_brjdqzmbqk = save_brjdqzmbqk;
	}

	public String getSave_dydj() {
		return save_dydj;
	}

	public void setSave_dydj(String save_dydj) {
		this.save_dydj = save_dydj;
	}

	public String getSave_jtdd() {
		return save_jtdd;
	}

	public void setSave_jtdd(String save_jtdd) {
		this.save_jtdd = save_jtdd;
	}

	public String getSave_jtdh() {
		return save_jtdh;
	}

	public void setSave_jtdh(String save_jtdh) {
		this.save_jtdh = save_jtdh;
	}

	public String getSave_jtdxrs() {
		return save_jtdxrs;
	}

	public void setSave_jtdxrs(String save_jtdxrs) {
		this.save_jtdxrs = save_jtdxrs;
	}

	public String getSave_jtfzqk() {
		return save_jtfzqk;
	}

	public void setSave_jtfzqk(String save_jtfzqk) {
		this.save_jtfzqk = save_jtfzqk;
	}

	public String getSave_jtnzsr() {
		return save_jtnzsr;
	}

	public void setSave_jtnzsr(String save_jtnzsr) {
		this.save_jtnzsr = save_jtnzsr;
	}

	public String getSave_jtrjsr() {
		return save_jtrjsr;
	}

	public void setSave_jtrjsr(String save_jtrjsr) {
		this.save_jtrjsr = save_jtrjsr;
	}

	public String getSave_jtrs() {
		return save_jtrs;
	}

	public void setSave_jtrs(String save_jtrs) {
		this.save_jtrs = save_jtrs;
	}

	public String getSave_jtsrly() {
		return save_jtsrly;
	}

	public void setSave_jtsrly(String save_jtsrly) {
		this.save_jtsrly = save_jtsrly;
	}

	public String getSave_jtyzsr() {
		return save_jtyzsr;
	}

	public void setSave_jtyzsr(String save_jtyzsr) {
		this.save_jtyzsr = save_jtyzsr;
	}

	public String getSave_qtqk() {
		return save_qtqk;
	}

	public void setSave_qtqk(String save_qtqk) {
		this.save_qtqk = save_qtqk;
	}

	public String getSave_scdz() {
		return save_scdz;
	}

	public void setSave_scdz(String save_scdz) {
		this.save_scdz = save_scdz;
	}

	public String getSave_sfcj() {
		return save_sfcj;
	}

	public void setSave_sfcj(String save_sfcj) {
		this.save_sfcj = save_sfcj;
	}

	public String getSave_sfdb() {
		return save_sfdb;
	}

	public void setSave_sfdb(String save_sfdb) {
		this.save_sfdb = save_sfdb;
	}

	public String getSave_sfge() {
		return save_sfge;
	}

	public void setSave_sfge(String save_sfge) {
		this.save_sfge = save_sfge;
	}

	public String getSave_sfjthb() {
		return save_sfjthb;
	}

	public void setSave_sfjthb(String save_sfjthb) {
		this.save_sfjthb = save_sfjthb;
	}

	public String getSave_sflszn() {
		return save_sflszn;
	}

	public void setSave_sflszn(String save_sflszn) {
		this.save_sflszn = save_sflszn;
	}

	public String getSave_sfpkzm() {
		return save_sfpkzm;
	}

	public void setSave_sfpkzm(String save_sfpkzm) {
		this.save_sfpkzm = save_sfpkzm;
	}

	public String getSave_sfzrch() {
		return save_sfzrch;
	}

	public void setSave_sfzrch(String save_sfzrch) {
		this.save_sfzrch = save_sfzrch;
	}

	public String getSave_xsjtjjqk() {
		return save_xsjtjjqk;
	}

	public void setSave_xsjtjjqk(String save_xsjtjjqk) {
		this.save_xsjtjjqk = save_xsjtjjqk;
	}

	public String getBjsh() {
		return bjsh;
	}

	public void setBjsh(String bjsh) {
		this.bjsh = bjsh;
	}

	public String getBjshsj() {
		return bjshsj;
	}

	public void setBjshsj(String bjshsj) {
		this.bjshsj = bjshsj;
	}

	public String getBjshyj() {
		return bjshyj;
	}

	public void setBjshyj(String bjshyj) {
		this.bjshyj = bjshyj;
	}

	public String getQueryequals_bjsh() {
		return queryequals_bjsh;
	}

	public void setQueryequals_bjsh(String queryequals_bjsh) {
		this.queryequals_bjsh = queryequals_bjsh;
	}

	public String getSave_bjsh() {
		return save_bjsh;
	}

	public void setSave_bjsh(String save_bjsh) {
		this.save_bjsh = save_bjsh;
	}

	public String getSave_bjshsj() {
		return save_bjshsj;
	}

	public void setSave_bjshsj(String save_bjshsj) {
		this.save_bjshsj = save_bjshsj;
	}

	public String getSave_bjshyj() {
		return save_bjshyj;
	}

	public void setSave_bjshyj(String save_bjshyj) {
		this.save_bjshyj = save_bjshyj;
	}

	public String getKg() {
		return kg;
	}

	public void setKg(String kg) {
		this.kg = kg;
	}

	public String[] getSzxy() {
		return szxy;
	}

	public void setSzxy(String[] szxy) {
		this.szxy = szxy;
	}

	public String[] getXmbl() {
		return xmbl;
	}

	public void setXmbl(String[] xmbl) {
		this.xmbl = xmbl;
	}

	public String[] getXmjb() {
		return xmjb;
	}

	public void setXmjb(String[] xmjb) {
		this.xmjb = xmjb;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String[] getXmrs() {
		return xmrs;
	}

	public void setXmrs(String[] xmrs) {
		this.xmrs = xmrs;
	}

	public String getKnsjb() {
		return knsjb;
	}

	public void setKnsjb(String knsjb) {
		this.knsjb = knsjb;
	}

	public String getQueryequals_lx() {
		return queryequals_lx;
	}

	public void setQueryequals_lx(String queryequals_lx) {
		this.queryequals_lx = queryequals_lx;
	}

	public String getQueryequals_knsjb() {
		return queryequals_knsjb;
	}

	public void setQueryequals_knsjb(String queryequals_knsjb) {
		this.queryequals_knsjb = queryequals_knsjb;
	}

	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}

	public void setPrimarykey_checkVal(String[] primarykey_checkVal) {
		this.primarykey_checkVal = primarykey_checkVal;
	}

	public String getShzd() {
		return shzd;
	}

	public void setShzd(String shzd) {
		this.shzd = shzd;
	}

	public String getQueryequals_xfsfqf() {
		return queryequals_xfsfqf;
	}

	public void setQueryequals_xfsfqf(String queryequals_xfsfqf) {
		this.queryequals_xfsfqf = queryequals_xfsfqf;
	}

	public String getXfsfqf() {
		return xfsfqf;
	}

	public void setXfsfqf(String xfsfqf) {
		this.xfsfqf = xfsfqf;
	}

	public String getXfsfxm() {
		return xfsfxm;
	}

	public void setXfsfxm(String xfsfxm) {
		this.xfsfxm = xfsfxm;
	}

	public String getXfsjje() {
		return xfsjje;
	}

	public void setXfsjje(String xfsjje) {
		this.xfsjje = xfsjje;
	}

	public String getXfyjje() {
		return xfyjje;
	}

	public void setXfyjje(String xfyjje) {
		this.xfyjje = xfyjje;
	}

	public String getQuerylike_xfsfxm() {
		return querylike_xfsfxm;
	}

	public void setQuerylike_xfsfxm(String querylike_xfsfxm) {
		this.querylike_xfsfxm = querylike_xfsfxm;
	}

	public String getBzrsh() {
		return bzrsh;
	}

	public void setBzrsh(String bzrsh) {
		this.bzrsh = bzrsh;
	}

	public String getFdysh() {
		return fdysh;
	}

	public void setFdysh(String fdysh) {
		this.fdysh = fdysh;
	}

	public String getMrxm() {
		return mrxm;
	}

	public void setMrxm(String mrxm) {
		this.mrxm = mrxm;
	}

	public String getRskz() {
		return rskz;
	}

	public void setRskz(String rskz) {
		this.rskz = rskz;
	}

	public String getRssx() {
		return rssx;
	}

	public void setRssx(String rssx) {
		this.rssx = rssx;
	}

	public String getSffj() {
		return sffj;
	}

	public void setSffj(String sffj) {
		this.sffj = sffj;
	}

	public String getSqzq() {
		return sqzq;
	}

	public void setSqzq(String sqzq) {
		this.sqzq = sqzq;
	}

	public String getXxdm() {
		return xxdm;
	}

	public void setXxdm(String xxdm) {
		this.xxdm = xxdm;
	}

	public String getSfje() {
		return sfje;
	}

	public void setSfje(String sfje) {
		this.sfje = sfje;
	}

	public String getBzrkz() {
		return bzrkz;
	}

	public void setBzrkz(String bzrkz) {
		this.bzrkz = bzrkz;
	}

	public String getFdykz() {
		return fdykz;
	}

	public void setFdykz(String fdykz) {
		this.fdykz = fdykz;
	}

	public String getKgzt() {
		return kgzt;
	}

	public void setKgzt(String kgzt) {
		this.kgzt = kgzt;
	}

	public String getKzjb() {
		return kzjb;
	}

	public void setKzjb(String kzjb) {
		this.kzjb = kzjb;
	}

	public String getXmb() {
		return xmb;
	}

	public void setXmb(String xmb) {
		this.xmb = xmb;
	}

	public String getXmsm() {
		return xmsm;
	}

	public void setXmsm(String xmsm) {
		this.xmsm = xmsm;
	}

	public String getXxkz() {
		return xxkz;
	}

	public void setXxkz(String xxkz) {
		this.xxkz = xxkz;
	}

	public String getXykz() {
		return xykz;
	}

	public void setXykz(String xykz) {
		this.xykz = xykz;
	}

	public String[] getKzr() {
		return kzr;
	}

	public void setKzr(String[] kzr) {
		this.kzr = kzr;
	}

	public String[] getShr() {
		return shr;
	}

	public void setShr(String[] shr) {
		this.shr = shr;
	}

	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String getShjb() {
		return shjb;
	}

	public void setShjb(String shjb) {
		this.shjb = shjb;
	}

	public String getLssh() {
		return lssh;
	}

	public void setLssh(String lssh) {
		this.lssh = lssh;
	}

	public String getJelx() {
		return jelx;
	}

	public void setJelx(String jelx) {
		this.jelx = jelx;
	}

	public String[] getFjmc() {
		return fjmc;
	}

	public void setFjmc(String[] fjmc) {
		this.fjmc = fjmc;
	}

	public String[] getFjqdje() {
		return fjqdje;
	}

	public void setFjqdje(String[] fjqdje) {
		this.fjqdje = fjqdje;
	}

	public String[] getFjsxje() {
		return fjsxje;
	}

	public void setFjsxje(String[] fjsxje) {
		this.fjsxje = fjsxje;
	}

	public String[] getFjxmdm() {
		return fjxmdm;
	}

	public void setFjxmdm(String[] fjxmdm) {
		this.fjxmdm = fjxmdm;
	}

	public String[] getFjxxje() {
		return fjxxje;
	}

	public void setFjxxje(String[] fjxxje) {
		this.fjxxje = fjxxje;
	}

	public String[] getNojemc() {
		return nojemc;
	}

	public void setNojemc(String[] nojemc) {
		this.nojemc = nojemc;
	}

	public String[] getQdjemc() {
		return qdjemc;
	}

	public void setQdjemc(String[] qdjemc) {
		this.qdjemc = qdjemc;
	}

	public String[] getQjjemc() {
		return qjjemc;
	}

	public void setQjjemc(String[] qjjemc) {
		this.qjjemc = qjjemc;
	}

	public String getNofjje() {
		return nofjje;
	}

	public void setNofjje(String nofjje) {
		this.nofjje = nofjje;
	}

	public String getNofjsx() {
		return nofjsx;
	}

	public void setNofjsx(String nofjsx) {
		this.nofjsx = nofjsx;
	}

	public String getNofjxx() {
		return nofjxx;
	}

	public void setNofjxx(String nofjxx) {
		this.nofjxx = nofjxx;
	}

	public String[] getXmtj() {
		return xmtj;
	}

	public void setXmtj(String[] xmtj) {
		this.xmtj = xmtj;
	}

	public String[] getXmtjz() {
		return xmtjz;
	}

	public void setXmtjz(String[] xmtjz) {
		this.xmtjz = xmtjz;
	}

	public String getQueryequals_jelx() {
		return queryequals_jelx;
	}

	public void setQueryequals_jelx(String queryequals_jelx) {
		this.queryequals_jelx = queryequals_jelx;
	}

	public String getQueryequals_mrxm() {
		return queryequals_mrxm;
	}

	public void setQueryequals_mrxm(String queryequals_mrxm) {
		this.queryequals_mrxm = queryequals_mrxm;
	}

	public String getQueryequals_rskz() {
		return queryequals_rskz;
	}

	public void setQueryequals_rskz(String queryequals_rskz) {
		this.queryequals_rskz = queryequals_rskz;
	}

	public String getQueryequals_sffj() {
		return queryequals_sffj;
	}

	public void setQueryequals_sffj(String queryequals_sffj) {
		this.queryequals_sffj = queryequals_sffj;
	}

	public String getQueryequals_sfje() {
		return queryequals_sfje;
	}

	public void setQueryequals_sfje(String queryequals_sfje) {
		this.queryequals_sfje = queryequals_sfje;
	}

	public String getQueryequals_shjb() {
		return queryequals_shjb;
	}

	public void setQueryequals_shjb(String queryequals_shjb) {
		this.queryequals_shjb = queryequals_shjb;
	}

	public String getQueryequals_sqzq() {
		return queryequals_sqzq;
	}

	public void setQueryequals_sqzq(String queryequals_sqzq) {
		this.queryequals_sqzq = queryequals_sqzq;
	}

	public String getQueryequals_xmdm() {
		return queryequals_xmdm;
	}

	public void setQueryequals_xmdm(String queryequals_xmdm) {
		this.queryequals_xmdm = queryequals_xmdm;
	}

	public String[] getBmdm() {
		return bmdm;
	}

	public void setBmdm(String[] bmdm) {
		this.bmdm = bmdm;
	}

	public String getBmjb() {
		return bmjb;
	}

	public void setBmjb(String bmjb) {
		this.bmjb = bmjb;
	}

	public String[] getSzrs() {
		return szrs;
	}

	public void setSzrs(String[] szrs) {
		this.szrs = szrs;
	}

	public String getFpfs() {
		return fpfs;
	}

	public void setFpfs(String fpfs) {
		this.fpfs = fpfs;
	}

	public String getBl() {
		return bl;
	}

	public void setBl(String bl) {
		this.bl = bl;
	}

	public String[] getIscz() {
		return iscz;
	}

	public void setIscz(String[] iscz) {
		this.iscz = iscz;
	}

	public String[] getSznj() {
		return sznj;
	}

	public void setSznj(String[] sznj) {
		this.sznj = sznj;
	}

	public String getFprs() {
		return fprs;
	}

	public void setFprs(String fprs) {
		this.fprs = fprs;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String[] getXmtjb() {
		return xmtjb;
	}

	public void setXmtjb(String[] xmtjb) {
		this.xmtjb = xmtjb;
	}

	public String getBrhjqk() {
		return brhjqk;
	}

	public void setBrhjqk(String brhjqk) {
		this.brhjqk = brhjqk;
	}

	public String getBzrshsj() {
		return bzrshsj;
	}

	public void setBzrshsj(String bzrshsj) {
		this.bzrshsj = bzrshsj;
	}

	public String getBzrshyj() {
		return bzrshyj;
	}

	public void setBzrshyj(String bzrshyj) {
		this.bzrshyj = bzrshyj;
	}

	public String getCjnmqk() {
		return cjnmqk;
	}

	public void setCjnmqk(String cjnmqk) {
		this.cjnmqk = cjnmqk;
	}

	public String getDypddj() {
		return dypddj;
	}

	public void setDypddj(String dypddj) {
		this.dypddj = dypddj;
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

	public String getJtddxrs() {
		return jtddxrs;
	}

	public void setJtddxrs(String jtddxrs) {
		this.jtddxrs = jtddxrs;
	}

	public String getJtjbjjqk() {
		return jtjbjjqk;
	}

	public void setJtjbjjqk(String jtjbjjqk) {
		this.jtjbjjqk = jtjbjjqk;
	}

	public String getJtqtqk() {
		return jtqtqk;
	}

	public void setJtqtqk(String jtqtqk) {
		this.jtqtqk = jtqtqk;
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

	public String getJtsyqk() {
		return jtsyqk;
	}

	public void setJtsyqk(String jtsyqk) {
		this.jtsyqk = jtsyqk;
	}

	public String getJtszqk() {
		return jtszqk;
	}

	public void setJtszqk(String jtszqk) {
		this.jtszqk = jtszqk;
	}

	public String getLszn() {
		return lszn;
	}

	public void setLszn(String lszn) {
		this.lszn = lszn;
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

	public String getTfsjqk() {
		return tfsjqk;
	}

	public void setTfsjqk(String tfsjqk) {
		this.tfsjqk = tfsjqk;
	}

	public String getTpsc() {
		return tpsc;
	}

	public void setTpsc(String tpsc) {
		this.tpsc = tpsc;
	}

	public String getYhzzqk() {
		return yhzzqk;
	}

	public void setYhzzqk(String yhzzqk) {
		this.yhzzqk = yhzzqk;
	}

	public String getSqsjCn() {
		return sqsjCn;
	}

	public void setSqsjCn(String sqsjCn) {
		this.sqsjCn = sqsjCn;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getUserDep() {
		return userDep;
	}

	public void setUserDep(String userDep) {
		this.userDep = userDep;
	}

	public String[] getJb() {
		return jb;
	}

	public void setJb(String[] jb) {
		this.jb = jb;
	}

	public String[] getJbmc() {
		return jbmc;
	}

	public void setJbmc(String[] jbmc) {
		this.jbmc = jbmc;
	}

	public String getSave_xmzzje() {
		return save_xmzzje;
	}

	public void setSave_xmzzje(String save_xmzzje) {
		this.save_xmzzje = save_xmzzje;
	}

	public String getXmzzje() {
		return xmzzje;
	}

	public void setXmzzje(String xmzzje) {
		this.xmzzje = xmzzje;
	}

	public String getSave_xmzzjb() {
		return save_xmzzjb;
	}

	public void setSave_xmzzjb(String save_xmzzjb) {
		this.save_xmzzjb = save_xmzzjb;
	}

	public String getXmzzjb() {
		return xmzzjb;
	}

	public void setXmzzjb(String xmzzjb) {
		this.xmzzjb = xmzzjb;
	}

	public String getShpk() {
		return shpk;
	}

	public void setShpk(String shpk) {
		this.shpk = shpk;
	}

	public String getShzt1() {
		return shzt1;
	}

	public void setShzt1(String shzt1) {
		this.shzt1 = shzt1;
	}

	public String getShzt1yj() {
		return shzt1yj;
	}

	public void setShzt1yj(String shzt1yj) {
		this.shzt1yj = shzt1yj;
	}

	public String getShzt2() {
		return shzt2;
	}

	public void setShzt2(String shzt2) {
		this.shzt2 = shzt2;
	}

	public String getShzt2yj() {
		return shzt2yj;
	}

	public void setShzt2yj(String shzt2yj) {
		this.shzt2yj = shzt2yj;
	}

	public String getShzt3() {
		return shzt3;
	}

	public void setShzt3(String shzt3) {
		this.shzt3 = shzt3;
	}

	public String getShzt3yj() {
		return shzt3yj;
	}

	public void setShzt3yj(String shzt3yj) {
		this.shzt3yj = shzt3yj;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getMklx() {
		return mklx;
	}

	public void setMklx(String mklx) {
		this.mklx = mklx;
	}

	public String[] getCydh() {
		return cydh;
	}

	public void setCydh(String[] cydh) {
		this.cydh = cydh;
	}

	public String[] getCygx() {
		return cygx;
	}

	public void setCygx(String[] cygx) {
		this.cygx = cygx;
	}

	public String[] getCygzdw() {
		return cygzdw;
	}

	public void setCygzdw(String[] cygzdw) {
		this.cygzdw = cygzdw;
	}

	public String[] getCyjkzk() {
		return cyjkzk;
	}

	public void setCyjkzk(String[] cyjkzk) {
		this.cyjkzk = cyjkzk;
	}

	public String[] getCynl() {
		return cynl;
	}

	public void setCynl(String[] cynl) {
		this.cynl = cynl;
	}

	public String[] getCynsr() {
		return cynsr;
	}

	public void setCynsr(String[] cynsr) {
		this.cynsr = cynsr;
	}

	public String[] getCyxm() {
		return cyxm;
	}

	public void setCyxm(String[] cyxm) {
		this.cyxm = cyxm;
	}

	public String[] getCyzy() {
		return cyzy;
	}

	public void setCyzy(String[] cyzy) {
		this.cyzy = cyzy;
	}

	public String getGx() {
		return gx;
	}

	public void setGx(String gx) {
		this.gx = gx;
	}

	public String[] getCynzc() {
		return cynzc;
	}

	public void setCynzc(String[] cynzc) {
		this.cynzc = cynzc;
	}

	public String getJthk() {
		return jthk;
	}

	public void setJthk(String jthk) {
		this.jthk = jthk;
	}

	public String getSrly() {
		return srly;
	}

	public void setSrly(String srly) {
		this.srly = srly;
	}

	public String getXnje() {
		return xnje;
	}

	public void setXnje(String xnje) {
		this.xnje = xnje;
	}

	public String getZje() {
		return zje;
	}

	public void setZje(String zje) {
		this.zje = zje;
	}

	public String getSzbm() {
		return szbm;
	}

	public void setSzbm(String szbm) {
		this.szbm = szbm;
	}

	public String getQuerylike_hdbh() {
		return querylike_hdbh;
	}

	public void setQuerylike_hdbh(String querylike_hdbh) {
		this.querylike_hdbh = querylike_hdbh;
	}

	public String getSave_hdbh() {
		return save_hdbh;
	}

	public void setSave_hdbh(String save_hdbh) {
		this.save_hdbh = save_hdbh;
	}

	public String[] getCyyb() {
		return cyyb;
	}

	public void setCyyb(String[] cyyb) {
		this.cyyb = cyyb;
	}

	public String[] getCyysr() {
		return cyysr;
	}

	public void setCyysr(String[] cyysr) {
		this.cyysr = cyysr;
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

	public String getXmlb() {
		return xmlb;
	}

	public void setXmlb(String xmlb) {
		this.xmlb = xmlb;
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

	public String getBjpm() {
		return bjpm;
	}

	public void setBjpm(String bjpm) {
		this.bjpm = bjpm;
	}

	public String getBxkms() {
		return bxkms;
	}

	public void setBxkms(String bxkms) {
		this.bxkms = bxkms;
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

	public String getHjrq1() {
		return hjrq1;
	}

	public void setHjrq1(String hjrq1) {
		this.hjrq1 = hjrq1;
	}

	public String getHjrq2() {
		return hjrq2;
	}

	public void setHjrq2(String hjrq2) {
		this.hjrq2 = hjrq2;
	}

	public String getHjrq3() {
		return hjrq3;
	}

	public void setHjrq3(String hjrq3) {
		this.hjrq3 = hjrq3;
	}

	public String getHjrq4() {
		return hjrq4;
	}

	public void setHjrq4(String hjrq4) {
		this.hjrq4 = hjrq4;
	}

	public String getJsjsp() {
		return jsjsp;
	}

	public void setJsjsp(String jsjsp) {
		this.jsjsp = jsjsp;
	}

	public String getKyqk() {
		return kyqk;
	}

	public void setKyqk(String kyqk) {
		this.kyqk = kyqk;
	}

	public String getLhms() {
		return lhms;
	}

	public void setLhms(String lhms) {
		this.lhms = lhms;
	}

	public String getSbxqxfjd() {
		return sbxqxfjd;
	}

	public void setSbxqxfjd(String sbxqxfjd) {
		this.sbxqxfjd = sbxqxfjd;
	}

	public String getWysp() {
		return wysp;
	}

	public void setWysp(String wysp) {
		this.wysp = wysp;
	}

	public String getXbxqxfjd() {
		return xbxqxfjd;
	}

	public void setXbxqxfjd(String xbxqxfjd) {
		this.xbxqxfjd = xbxqxfjd;
	}

	public String getYxms() {
		return yxms;
	}

	public void setYxms(String yxms) {
		this.yxms = yxms;
	}

	public String getZcbjpm() {
		return zcbjpm;
	}

	public void setZcbjpm(String zcbjpm) {
		this.zcbjpm = zcbjpm;
	}

	public String getZhcpcj() {
		return zhcpcj;
	}

	public void setZhcpcj(String zhcpcj) {
		this.zhcpcj = zhcpcj;
	}

	public String getZycjpm() {
		return zycjpm;
	}

	public void setZycjpm(String zycjpm) {
		this.zycjpm = zycjpm;
	}

	public String getXf() {
		return xf;
	}

	public void setXf(String xf) {
		this.xf = xf;
	}

	public String getQueryequals_xmlb() {
		return queryequals_xmlb;
	}

	public void setQueryequals_xmlb(String queryequals_xmlb) {
		this.queryequals_xmlb = queryequals_xmlb;
	}

	public String getQuerylike_xmmc() {
		return querylike_xmmc;
	}

	public void setQuerylike_xmmc(String querylike_xmmc) {
		this.querylike_xmmc = querylike_xmmc;
	}

	public String getPdsj() {
		return pdsj;
	}

	public void setPdsj(String pdsj) {
		this.pdsj = pdsj;
	}

	public String getQueryequals_pdsj() {
		return queryequals_pdsj;
	}

	public void setQueryequals_pdsj(String queryequals_pdsj) {
		this.queryequals_pdsj = queryequals_pdsj;
	}

	public String getZd() {
		return zd;
	}

	public void setZd(String zd) {
		this.zd = zd;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getXssx() {
		return xssx;
	}

	public void setXssx(String xssx) {
		this.xssx = xssx;
	}

	public String getSave_bjgkms() {
		return save_bjgkms;
	}

	public void setSave_bjgkms(String save_bjgkms) {
		this.save_bjgkms = save_bjgkms;
	}

	public String getIsxb() {
		return isxb;
	}

	public void setIsxb(String isxb) {
		this.isxb = isxb;
	}

	public String getSave_nd() {
		return save_nd;
	}

	public void setSave_nd(String save_nd) {
		this.save_nd = save_nd;
	}

	public String getSave_xq() {
		return save_xq;
	}

	public void setSave_xq(String save_xq) {
		this.save_xq = save_xq;
	}

	public String getQueryequals_isxb() {
		return queryequals_isxb;
	}

	public void setQueryequals_isxb(String queryequals_isxb) {
		this.queryequals_isxb = queryequals_isxb;
	}

	public String getPjcj() {
		return pjcj;
	}

	public void setPjcj(String pjcj) {
		this.pjcj = pjcj;
	}

	public String getPjcjtj() {
		return pjcjtj;
	}

	public void setPjcjtj(String pjcjtj) {
		this.pjcjtj = pjcjtj;
	}

	public String getSave_sy() {
		return save_sy;
	}

	public void setSave_sy(String save_sy) {
		this.save_sy = save_sy;
	}

	public String getSy() {
		return sy;
	}

	public void setSy(String sy) {
		this.sy = sy;
	}

	public String getJtdz() {
		return jtdz;
	}

	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}

	public String getSave_jtdz() {
		return save_jtdz;
	}

	public void setSave_jtdz(String save_jtdz) {
		this.save_jtdz = save_jtdz;
	}

	public String getJtyb() {
		return jtyb;
	}

	public void setJtyb(String jtyb) {
		this.jtyb = jtyb;
	}

	public String getSave_jtyb() {
		return save_jtyb;
	}

	public void setSave_jtyb(String save_jtyb) {
		this.save_jtyb = save_jtyb;
	}

	public String getQtnr() {
		return qtnr;
	}

	public void setQtnr(String qtnr) {
		this.qtnr = qtnr;
	}

	public String getSfczxgjt() {
		return sfczxgjt;
	}

	public void setSfczxgjt(String sfczxgjt) {
		this.sfczxgjt = sfczxgjt;
	}

	public String getSfdqjt() {
		return sfdqjt;
	}

	public void setSfdqjt(String sfdqjt) {
		this.sfdqjt = sfdqjt;
	}

	public String getSffmxcj() {
		return sffmxcj;
	}

	public void setSffmxcj(String sffmxcj) {
		this.sffmxcj = sffmxcj;
	}

	public String getSfgr() {
		return sfgr;
	}

	public void setSfgr(String sfgr) {
		this.sfgr = sfgr;
	}

	public String getSfhzdjb() {
		return sfhzdjb;
	}

	public void setSfhzdjb(String sfhzdjb) {
		this.sfhzdjb = sfhzdjb;
	}

	public String getSfjtrkd() {
		return sfjtrkd;
	}

	public void setSfjtrkd(String sfjtrkd) {
		this.sfjtrkd = sfjtrkd;
	}

	public String getSfncpkdq() {
		return sfncpkdq;
	}

	public void setSfncpkdq(String sfncpkdq) {
		this.sfncpkdq = sfncpkdq;
	}

	public String getSfqt() {
		return sfqt;
	}

	public void setSfqt(String sfqt) {
		this.sfqt = sfqt;
	}

	public String getSfzrzh() {
		return sfzrzh;
	}

	public void setSfzrzh(String sfzrzh) {
		this.sfzrzh = sfzrzh;
	}

	public String getSave_qtnr() {
		return save_qtnr;
	}

	public void setSave_qtnr(String save_qtnr) {
		this.save_qtnr = save_qtnr;
	}

	public String getSave_sfczxgjt() {
		return save_sfczxgjt;
	}

	public void setSave_sfczxgjt(String save_sfczxgjt) {
		this.save_sfczxgjt = save_sfczxgjt;
	}

	public String getSave_sfdqjt() {
		return save_sfdqjt;
	}

	public void setSave_sfdqjt(String save_sfdqjt) {
		this.save_sfdqjt = save_sfdqjt;
	}

	public String getSave_sffmxcj() {
		return save_sffmxcj;
	}

	public void setSave_sffmxcj(String save_sffmxcj) {
		this.save_sffmxcj = save_sffmxcj;
	}

	public String getSave_sfgr() {
		return save_sfgr;
	}

	public void setSave_sfgr(String save_sfgr) {
		this.save_sfgr = save_sfgr;
	}

	public String getSave_sfhzdjb() {
		return save_sfhzdjb;
	}

	public void setSave_sfhzdjb(String save_sfhzdjb) {
		this.save_sfhzdjb = save_sfhzdjb;
	}

	public String getSave_sfjtrkd() {
		return save_sfjtrkd;
	}

	public void setSave_sfjtrkd(String save_sfjtrkd) {
		this.save_sfjtrkd = save_sfjtrkd;
	}

	public String getSave_sfncpkdq() {
		return save_sfncpkdq;
	}

	public void setSave_sfncpkdq(String save_sfncpkdq) {
		this.save_sfncpkdq = save_sfncpkdq;
	}

	public String getSave_sfqt() {
		return save_sfqt;
	}

	public void setSave_sfqt(String save_sfqt) {
		this.save_sfqt = save_sfqt;
	}

	public String getSave_sfzrzh() {
		return save_sfzrzh;
	}

	public void setSave_sfzrzh(String save_sfzrzh) {
		this.save_sfzrzh = save_sfzrzh;
	}

	public String getPkyyxxsm() {
		return pkyyxxsm;
	}

	public void setPkyyxxsm(String pkyyxxsm) {
		this.pkyyxxsm = pkyyxxsm;
	}

	public String getSave_pkyyxxsm() {
		return save_pkyyxxsm;
	}

	public void setSave_pkyyxxsm(String save_pkyyxxsm) {
		this.save_pkyyxxsm = save_pkyyxxsm;
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

	public String getSave_lxdh() {
		return save_lxdh;
	}

	public void setSave_lxdh(String save_lxdh) {
		this.save_lxdh = save_lxdh;
	}

	public String getSave_xxtxdz() {
		return save_xxtxdz;
	}

	public void setSave_xxtxdz(String save_xxtxdz) {
		this.save_xxtxdz = save_xxtxdz;
	}

	public String getSave_yzbm() {
		return save_yzbm;
	}

	public void setSave_yzbm(String save_yzbm) {
		this.save_yzbm = save_yzbm;
	}

	public boolean isFlg() {
		return flg;
	}

	public void setFlg(boolean flg) {
		this.flg = flg;
	}

	public String getIskns() {
		return iskns;
	}

	public void setIskns(String iskns) {
		this.iskns = iskns;
	}

	public String getSave_bzrshsj() {
		return save_bzrshsj;
	}

	public void setSave_bzrshsj(String save_bzrshsj) {
		this.save_bzrshsj = save_bzrshsj;
	}

	public String getSave_fdyshsj() {
		return save_fdyshsj;
	}

	public void setSave_fdyshsj(String save_fdyshsj) {
		this.save_fdyshsj = save_fdyshsj;
	}

	public String getShsj1() {
		return shsj1;
	}

	public void setShsj1(String shsj1) {
		this.shsj1 = shsj1;
	}

	public String getShsj2() {
		return shsj2;
	}

	public void setShsj2(String shsj2) {
		this.shsj2 = shsj2;
	}

	public String getShsj3() {
		return shsj3;
	}

	public void setShsj3(String shsj3) {
		this.shsj3 = shsj3;
	}

	public String getJqpjf() {
		return jqpjf;
	}

	public void setJqpjf(String jqpjf) {
		this.jqpjf = jqpjf;
	}

	public String getJqpjftj() {
		return jqpjftj;
	}

	public void setJqpjftj(String jqpjftj) {
		this.jqpjftj = jqpjftj;
	}

	public String getJqpjjd() {
		return jqpjjd;
	}

	public void setJqpjjd(String jqpjjd) {
		this.jqpjjd = jqpjjd;
	}

	public String getJqpjjdtj() {
		return jqpjjdtj;
	}

	public void setJqpjjdtj(String jqpjjdtj) {
		this.jqpjjdtj = jqpjjdtj;
	}

	public String[] getBkjdxm() {
		return bkjdxm;
	}

	public void setBkjdxm(String[] bkjdxm) {
		this.bkjdxm = bkjdxm;
	}

}
