package xgxt.comm.search;

import xgxt.action.Base;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 高级查询_model
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class SearchModel {

	// ===================通用条件=======================

	private String path = null;// 所在路径

	private String input_mhcx = null;// 输入模糊查询
	
	private String plcx_xh = null;// 批量查询_学号
	private String plcx_xm = null;// 批量查询_姓名
	private String mhcx_lx = "all";// 模糊查询类型

	private String input_xh = null;// 输入学号

	private String arrange = null;// 排序字段

	private String fashion = null;// 排序方式

	private String[] search_tj_mhcx = null;// 模糊查询条件

	private String[] search_tj_xh = null;// 学号条件

	private String[] search_tj_xm = null;// 姓名条件

	private String[] search_tj_xb = null;// 性别条件

	private String[] search_tj_nj = null;// 年级条件

	private String[] search_tj_xy = null;// 学院条件

	private String[] search_tj_zy = null;// 专业条件

	private String[] search_tj_bj = null;// 班级条件

	private String[] search_tj_sfpkx = null; //是否贫困县
	
	private String[] search_tj_sfbgb = null; //是否班干部
	
	private String[] search_tj_sfsfs = null; //是否师范生  是/否
	
	private String[] search_tj_xsfsfs = null; //是否师范生   非师范生/师范生

	private String[] search_tj_sfgk=null; //是否挂科

	private String[] search_tj_sfxn=null;

	private String[] search_tj_ffzt = null; //发放状态
	
	// ====================年级，学院，专业，班级START
	// 显示所有【学籍版追加】=================================

	private String[] search_tj_njNew = null;// 年级条件

	private String[] search_tj_bmNew = null;// 部门条件

	private String[] search_tj_xyNew = null;// 学院条件

	private String[] search_tj_zyNew = null;// 专业条件

	private String[] search_tj_bjNew = null;// 班级条件

	// ====================年级，学院，专业，班级 END
// ====================在校生年级、学院、专业、班级、学制、学籍类别
	
	// 年级条件列表
	private String[] search_tj_zxsNj= null;

	// 学院条件列表
	private String[] search_tj_zxsXy= null;

	// 专业条件列表
	private String[] search_tj_zxsZy= null;

	// 班级条件列表
	private String[] search_tj_zxsBj= null;
	
	// 学制条件列表
	private String[] search_tj_zxsXz= null;
	
	// 学籍类别条件列表
	private String[] search_tj_zxsXjlb= null;

	//====================在校生年级、学院、专业、班级、学制、学籍类别END
	
// ====================非在校生年级、学院、专业、班级、学制、学籍类别
	
	// 年级条件列表
	private String[] search_tj_fzxsNj= null;

	// 学院条件列表
	private String[] search_tj_fzxsXy= null;

	// 专业条件列表
	private String[] search_tj_fzxsZy= null;

	// 班级条件列表
	private String[] search_tj_fzxsBj= null;
	
	// 学制条件列表
	private String[] search_tj_fzxsXz= null;
	
	// 学籍类别条件列表
	private String[] search_tj_fzxsXjlb= null;

	//====================非在校生年级、学院、专业、班级、学制、学籍类别END
	// 显示所有【学籍版追加】=================================

	private String[] search_tj_bm = null;// 部门条件
	private String[] search_tj_sy = null;// 书院
	private String[] search_tj_xn = null;// 学年条件

	private String[] search_tj_pdxn = null;// 学年条件

	private String[] search_tj_rwxn = null;// 入伍学年条件

	private String[] search_tj_xq = null;// 学期条件

	private String[] search_tj_pdxq = null;// 学期条件

	private String[] search_tj_xqmc = null;// 学期名称条件

	private String[] search_tj_nd = null;// 年度条件

	private String[] search_tj_yf = null;// 月份条件

	private String[] search_tj_kssj = null;// 开始时间

	private String[] search_tj_jssj = null;// 结束时间
	
	private String[] search_tj_ksnum = null;// 开始数值
	
	private String[] search_tj_jsnum = null;// 结束数值

	private String[] search_tj_sf = null;// 是否条件

	private String[] search_tj_sflx = null;// 是否条件

	private String[] search_tj_sfhg = null;// 是否合格条件

	private String[] search_tj_shzt = null;// 审核状态

	private String[] search_tj_shztx = null;// 审核状态(新)
	
	private String[] search_tj_shztxbjpy = null;// 审核状态(班级评议)
	
	private String[] search_tj_shztbjpyjg = null;// 审核状态(班级评议结果查询)
	
	private String[] search_tj_shztdm = null;// 审核状态

	private String[] search_tj_shjg = null;// 审核结果

	private String[] search_tj_shjg2 = null;// 审核结果
	
	private String[] search_tj_shjg3 = null;// 审核结果
	
	private String[] search_tj_ylbxzt = null;// 医疗保险状态
	
	private String[] search_tj_yjnum = null;// 应交年数
	
	private String[] search_tj_bynd = null;// 毕业年度

	private String[] search_tj_rddc = null;// 学生资助--认定档次

	private String[] search_tj_sjdc = null;// 学生资助--认定档次

	private String[] search_tj_zhzt = null;// 新版审核状态

	private String[] search_tj_zzxm = null;// 资助项目
	
	private String[] search_tj_zzxm1 = null;//同资助项目，防止相同PATH,TJ,LX违反唯一约束

	private String[] search_tj_tjzt = null;// 新评奖-综合测评-提交状态

	private String[] search_tj_cpz = null; // 新评奖-参评组

	private String[] search_tj_lx = null; // 类型
	
	private String[] search_tj_pfzt = null;// 评分状态

	private String[] search_tj_zzmm = null;// 政治面貌

	private String[] search_tj_xl = null;// 学历

	private String[] search_tj_xw = null;// 学位

	private String[] search_tj_zc = null;// 职称

	private String[] search_tj_shztOne = null;// 審核狀態1

	private String[] search_tj_shztTwo = null;// 審核狀態2

	private String[] search_tj_sheng = null;// 省 三级联动 市、区县

	private String[] search_tj_shi = null;// 市

	private String[] search_tj_qu = null;// 区、县

	private String[] search_tj_kqlx = null;
	
	private String[] search_tj_cclx = null;//抽查类型
	
	private String[] search_tj_qqlx = null;//缺勤类型
	
	private String[] search_tj_hjxz= null;//缺勤类型
	private String[] search_tj_jxkqlx= null;//军训考勤类型
	private String[] search_tj_jxkqlb= null;//军训考勤类别
	// ===================通用条件 end======================
	private String[] search_tj_djh= null;//西安交大学业辅导-登记号
	private String[] search_tj_xmjb= null;//项目级别
	private String[] search_tj_sskm= null;//所属科目
	
	private String[] search_tj_stlb= null;//社团类别
	private String[] search_tj_stxmlb= null;//项目类别
	private String[] search_tj_stxm = null;//社团项目名称
	
	private String[] search_tj_ystlb= null;//艺术团类别
	private String[] search_tj_ystxmlb= null;//艺术团项目类别
	private String[] search_tj_ystxmmc = null;//艺术团项目名称
	
	private String[] search_tj_tnzt = null;//状态
	
	private String[] search_tj_ghzt = null;
	private String[] search_tj_xljkgxlx = null;
	private String[] search_tj_xslb = null;
	private String[] search_tj_xljkgzlx = null;
	private String[] search_tj_qyztmc = null;
	/*浙江旅游请假类型*/
	private String[] search_tj_qjlxmc = null;
	private String[] search_tj_qjtslx = null;
	//请假开始节次
	private String[] search_tj_qjksjc = null;
	//请假结束节次
	private String[] search_tj_qjjsjc = null;
	//加扣分类别
	private String[] search_tj_lb = null;
	//奖惩分类别
	private String[] search_tj_jclb = null;
	//活动频率
	private String[] search_tj_hdpl = null;
	//专职辅导员
	private String[] search_tj_zjz = null;
	//审定状态
	private String[] search_tj_sdzt = null;
	//浙江旅游综合分导入审定状态
	private String[] search_tj_zjlysdzt = null;
	//浙江旅游综合分审核审定状态
	private String[] search_tj_zjlyshsdzt = null;
	//项目模块代码
	private String[] search_tj_xmmkdm = null;
	//计分项目代码
	private String[] search_tj_jfxmdm = null;
	//预约周次
	private String[] search_tj_yyzc = null;
	
/*日常事务--学生行为考核--基本分管理 */
	private String[] search_tj_bzrcpdj = null;
	private String[] search_tj_xscpdj = null;
	
	//素质拓展新
	private String[] search_tj_rdzt = null;
	private String[] search_tj_csms = null;
	
	//勤工助学学生岗位审核新增高级查询条件
	private String[] search_tj_ysjxjxmlx = null;//浙江大学.院设奖学金项目类型
	
	//北京中医药-个人获奖附件上传-奖项级别高级查询条件
	private String[] search_tj_jxjb;
	//北京中医药-个人获奖附件上传-奖项类别高级查询条件
	private String[] search_tj_jxlbnew;
	
	//浙江旅游-医疗保险-参续保类别
	private String[] search_tj_cxblb;
	
	//浙江旅游-医疗保险-审核标志
		private String[] search_tj_shbz;
	
	//上海体育学院学生信息增加来源地区，兴趣爱好，担任职务，是否清真
	private String[] search_tj_lydq;
	private String[] search_tj_xqah;
	private String[] search_tj_drzw;
	private String[] search_tj_sfqz;
	//上海戏剧 评奖评优-综测维护-其他奖项设置 奖项代码 高级查询条件
	private String[] search_tj_jxdmx;
	
	//华师大 助学贷款-国家助学贷款-放款操作，是否放贷高级查询条件
	private String[] search_tj_fkzt;
	//日常事务-活动管理-活动类型
	private String[] search_tj_hdlx;
	//公寓管理-水电费统计-季度
	private String[] search_tj_jd;
	//政治面貌新[党组织关系转出-华科大武昌分校]
	private String[] search_tj_zzmmnew;
	//是否省内[党组织关系转出-华科大武昌分校]
	private String[] search_tj_sfsn;
	//所在党支部[党组织关系转出-华科大武昌分校]
	private String[] search_tj_szdzb;
	//心理咨询-心理筛查结果[是否需要约谈]
	private String[] search_tj_sfxyyt;
	//心理咨询-心理筛查结果[是否已参加约谈]
	private String[] search_tj_sfyyt;
	//心理健康咨询-谈话记录-特殊学生维护，（困难类型）【西安科技大学个性化,预警程度】
	private String[] search_tj_knlx;
	//心理健康咨询-咨询师管理-咨询师维护，（擅长领域）【湖南城市学院个性化,擅长领域】
	private String[] search_tj_scly;
	//操作类型【离校名单维护记录-重庆三峡】
	private String[] search_tj_czlx;
	//角色类型【金华职业-文明寝室】
	private String[] search_tj_js;
	//检查类型【金华职业-文明寝室】
	private String[] search_tj_jjlx;
	//提交状态【金华职业-文明寝室】
	private String[] search_tj_tjztwpqs;
	//退宿调整时间，手机端高级查询条件
	private String[] search_tj_tstzsj;
	//项目类别代码
	private String[] search_tj_xmlb;
	//浙大记实考评 项目大类
	private String[] search_tj_xmdl;
	//青岛酒店管理职业技术学院_发文结果(可通用)
	private String[] search_tj_fwjg;
		
	//金华职业（服务类型）
	private String[] search_tj_fwlx;
	//普测结果
	private String[] search_tj_pcjg;
	//是否关注
	private String[] search_tj_sfgz;
	//江西航空职业技术学院
	private String[] search_tj_lxnew;
	//江苏吴中中专
	private String[] search_tj_sfsxzt;
	//湘潭大学 
	private String[] search_tj_sfgcj;
	//西安交通大学
	private String[] search_tj_hdxs;

	private String[] search_tj_yjyy;

	private String[] search_tj_fdjslx;

	private String[] search_tj_alzt;

	private String[] search_tj_aljb;
	
	private String[] search_tj_hdzl;

	private String[] search_tj_hdjxzt;
	
	private String[] search_tj_cqxxxjzt;
	
	private String[] search_tj_stzt;
	
	private String[] search_tj_smnj;
	
	private String[] search_tj_smlx;

	private String[] search_tj_xsgbzw;//学生干部职务

	public String[] getSearch_tj_xsgbzw() {
		return search_tj_xsgbzw;
	}

	public void setSearch_tj_xsgbzw(String[] search_tj_xsgbzw) {
		this.search_tj_xsgbzw = search_tj_xsgbzw;
	}

	//西藏民族大学-学生信息-档案管理-转出方式(zcfs)
	private String[] search_tj_zcfs;
	private String[] search_tj_dazcxx;

	//金华职业-文明寝室-寝室考勤类别
	private String[] search_tj_qskqlb;
	public String[] getSearch_tj_qskqlb() {
		return search_tj_qskqlb;
	}

	public void setSearch_tj_qskqlb(String[] search_tj_qskqlb) {
		this.search_tj_qskqlb = search_tj_qskqlb;
	}

	//西北工业大学
	private String[] search_tj_grsblx;
	private String[] search_tj_bjsblx;
	private String[] search_tj_newstype;

	private String[] search_tj_dwlb;//单位类别
	private String[] search_tj_gzxz;//工作性质
	private String[] search_tj_gwlx;//岗位类型
	private String[] search_tj_jldj;
	private String[] search_tj_pm;
	private String[] search_tj_sfkhtg;
	private String[] search_tj_jb;
	private String[] search_tj_thlx;
	private String[] search_tj_sfzdgz;
	private String[] search_tj_jfxz;
	private String[] search_tj_bz;
	private String[] search_tj_jszgzt;
	private String[] search_tj_sfzg;
	private String[] search_tj_sfzb;
	private String[] search_tj_sblx;
	private String[] search_tj_kcxz;
	private String[] search_tj_fsbj;
	private String[] search_tj_jjlxdm;//家具类型代码
	private String[] search_tj_sfzdgzxs;
	private String[] search_tj_sfgmbx;//是否购买保险

	public String[] getSearch_tj_sfgmbx() {
		return search_tj_sfgmbx;
	}

	public void setSearch_tj_sfgmbx(String[] search_tj_sfgmbx) {
		this.search_tj_sfgmbx = search_tj_sfgmbx;
	}

	public String[] getSearch_tj_sfzdgzxs() {
		return search_tj_sfzdgzxs;
	}

	public void setSearch_tj_sfzdgzxs(String[] search_tj_sfzdgzxs) {
		this.search_tj_sfzdgzxs = search_tj_sfzdgzxs;
	}

	public String[] getSearch_tj_jjlxdm() {
		return search_tj_jjlxdm;
	}

	public void setSearch_tj_jjlxdm(String[] search_tj_jjlxdm) {
		this.search_tj_jjlxdm = search_tj_jjlxdm;
	}

	public String[] getSearch_tj_fsbj() {
		return search_tj_fsbj;
	}

	public void setSearch_tj_fsbj(String[] search_tj_fsbj) {
		this.search_tj_fsbj = search_tj_fsbj;
	}

	public String[] getSearch_tj_kcxz() {
		return search_tj_kcxz;
	}

	public void setSearch_tj_kcxz(String[] search_tj_kcxz) {
		this.search_tj_kcxz = search_tj_kcxz;
	}

	public String[] getSearch_tj_sblx() {
		return search_tj_sblx;
	}

	public void setSearch_tj_sblx(String[] search_tj_sblx) {
		this.search_tj_sblx = search_tj_sblx;
	}

	public String[] getSearch_tj_sfzg() {
		return search_tj_sfzg;
	}

	public void setSearch_tj_sfzg(String[] search_tj_sfzg) {
		this.search_tj_sfzg = search_tj_sfzg;
	}

	public String[] getSearch_tj_sfzb() {
		return search_tj_sfzb;
	}

	public void setSearch_tj_sfzb(String[] search_tj_sfzb) {
		this.search_tj_sfzb = search_tj_sfzb;
	}

	public String[] getSearch_tj_jszgzt() {
		return search_tj_jszgzt;
	}

	public void setSearch_tj_jszgzt(String[] search_tj_jszgzt) {
		this.search_tj_jszgzt = search_tj_jszgzt;
	}

	public String[] getSearch_tj_bz() {
		return search_tj_bz;
	}

	public void setSearch_tj_bz(String[] search_tj_bz) {
		this.search_tj_bz = search_tj_bz;
	}

	public String[] getSearch_tj_jfxz() {
		return search_tj_jfxz;
	}

	public void setSearch_tj_jfxz(String[] search_tj_jfxz) {
		this.search_tj_jfxz = search_tj_jfxz;
	}

	public String[] getSearch_tj_sfzdgz() {
		return search_tj_sfzdgz;
	}

	public void setSearch_tj_sfzdgz(String[] search_tj_sfzdgz) {
		this.search_tj_sfzdgz = search_tj_sfzdgz;
	}

	public String[] getSearch_tj_thlx() {
		return search_tj_thlx;
	}

	public void setSearch_tj_thlx(String[] search_tj_thlx) {
		this.search_tj_thlx = search_tj_thlx;
	}

	public String[] getSearch_tj_jb() {
		return search_tj_jb;
	}

	public void setSearch_tj_jb(String[] search_tj_jb) {
		this.search_tj_jb = search_tj_jb;
	}

	public String[] getSearch_tj_sfkhtg() {
		return search_tj_sfkhtg;
	}

	public void setSearch_tj_sfkhtg(String[] search_tj_sfkhtg) {
		this.search_tj_sfkhtg = search_tj_sfkhtg;
	}

	public String[] getSearch_tj_jldj() {
		return search_tj_jldj;
	}

	public void setSearch_tj_jldj(String[] search_tj_jldj) {
		this.search_tj_jldj = search_tj_jldj;
	}

	public String[] getSearch_tj_pm() {
		return search_tj_pm;
	}

	public void setSearch_tj_pm(String[] search_tj_pm) {
		this.search_tj_pm = search_tj_pm;
	}

	public String[] getSearch_tj_gzxz() {
		return search_tj_gzxz;
	}

	public void setSearch_tj_gzxz(String[] search_tj_gzxz) {
		this.search_tj_gzxz = search_tj_gzxz;
	}

	public String[] getSearch_tj_gwlx() {
		return search_tj_gwlx;
	}

	public void setSearch_tj_gwlx(String[] search_tj_gwlx) {
		this.search_tj_gwlx = search_tj_gwlx;
	}

	public String[] getSearch_tj_dwlb() {
		return search_tj_dwlb;
	}

	public void setSearch_tj_dwlb(String[] search_tj_dwlb) {
		this.search_tj_dwlb = search_tj_dwlb;
	}

	public String[] getSearch_tj_newstype() {
		return search_tj_newstype;
	}

	public void setSearch_tj_newstype(String[] search_tj_newstype) {
		this.search_tj_newstype = search_tj_newstype;
	}

	public String[] getSearch_tj_grsblx() {
		return search_tj_grsblx;
	}

	public void setSearch_tj_grsblx(String[] search_tj_grsblx) {
		this.search_tj_grsblx = search_tj_grsblx;
	}

	public String[] getSearch_tj_bjsblx() {
		return search_tj_bjsblx;
	}

	public void setSearch_tj_bjsblx(String[] search_tj_bjsblx) {
		this.search_tj_bjsblx = search_tj_bjsblx;
	}

	private String[] search_tj_hdlx10699;

	private String[] search_tj_sfdb = null;//是否达标

	public String[] getSearch_tj_sfdb() {
		return search_tj_sfdb;
	}

	public void setSearch_tj_sfdb(String[] search_tj_sfdb) {
		this.search_tj_sfdb = search_tj_sfdb;
	}

	public String[] getSearch_tj_hdlx10699() {
		return search_tj_hdlx10699;
	}

	public void setSearch_tj_hdlx10699(String[] search_tj_hdlx10699) {
		this.search_tj_hdlx10699 = search_tj_hdlx10699;
	}

	private String[] search_tj_drhd;

	public String[] getSearch_tj_drhd() {
		return search_tj_drhd;
	}

	public void setSearch_tj_drhd(String[] search_tj_drhd) {
		this.search_tj_drhd = search_tj_drhd;
	}

	public String[] getSearch_tj_djh() {
		return search_tj_djh;
	}

	public void setSearch_tj_djh(String[] search_tj_djh) {
		this.search_tj_djh = search_tj_djh;
	}

	/**
	 * @return the search_tj_dazcxx
	 */
	public String[] getSearch_tj_dazcxx() {
		return search_tj_dazcxx;
	}

	/**
	 * @param searchTjDazcxx要设置的 search_tj_dazcxx
	 */
	public void setSearch_tj_dazcxx(String[] searchTjDazcxx) {
		search_tj_dazcxx = searchTjDazcxx;
	}

	/**
	 * @return the search_tj_zcfs
	 */
	public String[] getSearch_tj_zcfs() {
		return search_tj_zcfs;
	}

	/**
	 * @param searchTjZcfs要设置的 search_tj_zcfs
	 */
	public void setSearch_tj_zcfs(String[] searchTjZcfs) {
		search_tj_zcfs = searchTjZcfs;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-14 上午09:19:57 
	 * @return		: the search_tj_smnj
	 */
	public String[] getSearch_tj_smnj() {
		return search_tj_smnj;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-14 上午09:19:57 
	 * @param 		：searchTjSmnj the search_tj_smnj to set
	 */
	public void setSearch_tj_smnj(String[] searchTjSmnj) {
		search_tj_smnj = searchTjSmnj;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-14 上午09:19:57 
	 * @return		: the search_tj_smlx
	 */
	public String[] getSearch_tj_smlx() {
		return search_tj_smlx;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-14 上午09:19:57 
	 * @param 		：searchTjSmlx the search_tj_smlx to set
	 */
	public void setSearch_tj_smlx(String[] searchTjSmlx) {
		search_tj_smlx = searchTjSmlx;
	}

	/**
	 * @return the search_tj_scly
	 */
	public String[] getSearch_tj_scly() {
		return search_tj_scly;
	}

	/**
	 * @param searchTjScly要设置的 search_tj_scly
	 */
	public void setSearch_tj_scly(String[] searchTjScly) {
		search_tj_scly = searchTjScly;
	}

	/**
	 * @return the search_tj_cqxxxjzt
	 */
	public String[] getSearch_tj_cqxxxjzt() {
		return search_tj_cqxxxjzt;
	}

	/**
	 * @param searchTjCqxxxjzt要设置的 search_tj_cqxxxjzt
	 */
	public void setSearch_tj_cqxxxjzt(String[] searchTjCqxxxjzt) {
		search_tj_cqxxxjzt = searchTjCqxxxjzt;
	}

	public String[] getSearch_tj_sfsxzt() {
		return search_tj_sfsxzt;
	}

	public void setSearch_tj_sfsxzt(String[] searchTjSfsxzt) {
		search_tj_sfsxzt = searchTjSfsxzt;
	}

	public String[] getSearch_tj_lxnew() {
		return search_tj_lxnew;
	}

	public void setSearch_tj_lxnew(String[] searchTjLxnew) {
		search_tj_lxnew = searchTjLxnew;
	}

	public String[] getSearch_tj_clzb() {
		return search_tj_clzb;
	}

	public void setSearch_tj_clzb(String[] searchTjClzb) {
		search_tj_clzb = searchTjClzb;
	}

	public String[] getSearch_tj_bxxz() {
		return search_tj_bxxz;
	}

	public void setSearch_tj_bxxz(String[] searchTjBxxz) {
		search_tj_bxxz = searchTjBxxz;
	}

	private String[] search_tj_clzb;
	private String[] search_tj_bxxz;
	
	public String[] getSearch_tj_pcjg() {
		return search_tj_pcjg;
	}

	public void setSearch_tj_pcjg(String[] searchTjPcjg) {
		search_tj_pcjg = searchTjPcjg;
	}

	public String[] getSearch_tj_sfgz() {
		return search_tj_sfgz;
	}

	public void setSearch_tj_sfgz(String[] searchTjSfgz) {
		search_tj_sfgz = searchTjSfgz;
	}
	
	private String[] search_tj_sfbm;
	public String[] getSearch_tj_sfbm() {
		return search_tj_sfbm;
	}

	public void setSearch_tj_sfbm(String[] searchTjSfbm) {
		search_tj_sfbm = searchTjSfbm;
	}

	public String[] getSearch_tj_xmlb() {
		return search_tj_xmlb;
	}

	public void setSearch_tj_xmlb(String[] searchTjXmlb) {
		search_tj_xmlb = searchTjXmlb;
	}

	public String[] getSearch_tj_xmdl() {
		return search_tj_xmdl;
	}

	public void setSearch_tj_xmdl(String[] searchTjXmdl) {
		search_tj_xmdl = searchTjXmdl;
	}

	//日常事务 保险类别
	private String[] search_tj_rcswbxlb;
	//日常事务 保险 是否办理
	private String[] search_tj_bxsfbl;


	private String[] search_tj_zt;
	public String[] getSearch_tj_zt() {
		return search_tj_zt;
	}

	public void setSearch_tj_zt(String[] search_tj_zt) {
		this.search_tj_zt = search_tj_zt;
	}

	private String[] search_tj_zwlx;

	public String[] getSearch_tj_zwlx() {
		return search_tj_zwlx;
	}

	public void setSearch_tj_zwlx(String[] search_tj_zwlx) {
		this.search_tj_zwlx = search_tj_zwlx;
	}

	/**
	 * @return the search_tj_bxsfbl
	 */
	public String[] getSearch_tj_bxsfbl() {
		return search_tj_bxsfbl;
	}

	/**
	 * @param search_tj_bxsfbl要设置的 search_tj_bxsfbl
	 */
	public void setSearch_tj_bxsfbl(String[] search_tj_bxsfbl) {
		this.search_tj_bxsfbl = search_tj_bxsfbl;
	}

	/**
	 * @return the search_tj_rcswbxlb
	 */
	public String[] getSearch_tj_rcswbxlb() {
		return search_tj_rcswbxlb;
	}

	/**
	 * @param search_tj_rcswbxlb要设置的 search_tj_rcswbxlb
	 */
	public void setSearch_tj_rcswbxlb(String[] search_tj_rcswbxlb) {
		this.search_tj_rcswbxlb = search_tj_rcswbxlb;
	}


	private String[] search_tj_jtgj;
	

	public String[] getSearch_tj_tstzsj() {
		return search_tj_tstzsj;
	}

	public void setSearch_tj_tstzsj(String[] searchTjTstzsj) {
		search_tj_tstzsj = searchTjTstzsj;
	}

	public String[] getSearch_tj_jjlx() {
		return search_tj_jjlx;
	}

	public void setSearch_tj_jjlx(String[] searchTjJjlx) {
		search_tj_jjlx = searchTjJjlx;
	}

	public String[] getSearch_tj_tjztwpqs() {
		return search_tj_tjztwpqs;
	}

	public void setSearch_tj_tjztwpqs(String[] searchTjTjztwpqs) {
		search_tj_tjztwpqs = searchTjTjztwpqs;
	}

	/**
	 * @return the search_tj_sfsn
	 */
	public String[] getSearch_tj_sfsn() {
		return search_tj_sfsn;
	}

	/**
	 * @return the search_tj_szdzb
	 */
	public String[] getSearch_tj_szdzb() {
		return search_tj_szdzb;
	}


	public String[] getSearch_tj_js() {
		return search_tj_js;
	}

	public void setSearch_tj_js(String[] searchTjJs) {
		search_tj_js = searchTjJs;
	}

	/**
	 * @param searchTjSzdzb要设置的 search_tj_szdzb
	 */
	public void setSearch_tj_szdzb(String[] searchTjSzdzb) {
		search_tj_szdzb = searchTjSzdzb;
	}

	/**
	 * @param searchTjSfsn要设置的 search_tj_sfsn
	 */
	public void setSearch_tj_sfsn(String[] searchTjSfsn) {
		search_tj_sfsn = searchTjSfsn;
	}

	//公寓管理-水电费统计-季度
	public String[] getSearch_tj_jd() {
		return search_tj_jd;
	}

	//公寓管理-水电费统计-季度
	public void setSearch_tj_jd(String[] searchTjJd) {
		search_tj_jd = searchTjJd;
	}
	
	/**
	 * @return the search_tj_fkzt
	 */
	public String[] getSearch_tj_fkzt() {
		return search_tj_fkzt;
	}

	/**
	 * @param searchTjFkzt要设置的 search_tj_fkzt
	 */
	public void setSearch_tj_fkzt(String[] searchTjFkzt) {
		search_tj_fkzt = searchTjFkzt;
	}

	/**
	 * @return the search_tj_jxdmx
	 */
	public String[] getSearch_tj_jxdmx() {
		return search_tj_jxdmx;
	}

	/**
	 * @return the search_tj_zzmmnew
	 */
	public String[] getSearch_tj_zzmmnew() {
		return search_tj_zzmmnew;
	}

	/**
	 * @param searchTjZzmmnew要设置的 search_tj_zzmmnew
	 */
	public void setSearch_tj_zzmmnew(String[] searchTjZzmmnew) {
		search_tj_zzmmnew = searchTjZzmmnew;
	}

	/**
	 * @param searchTjJxdmx要设置的 search_tj_jxdmx
	 */
	public void setSearch_tj_jxdmx(String[] searchTjJxdmx) {
		search_tj_jxdmx = searchTjJxdmx;
	}

	/**
	 * @return the search_tj_lydq
	 */
	public String[] getSearch_tj_lydq() {
		return search_tj_lydq;
	}

	/**
	 * @param searchTjLydq要设置的 search_tj_lydq
	 */
	public void setSearch_tj_lydq(String[] searchTjLydq) {
		search_tj_lydq = searchTjLydq;
	}

	/**
	 * @return the search_tj_xqah
	 */
	public String[] getSearch_tj_xqah() {
		return search_tj_xqah;
	}

	/**
	 * @param searchTjXqah要设置的 search_tj_xqah
	 */
	public void setSearch_tj_xqah(String[] searchTjXqah) {
		search_tj_xqah = searchTjXqah;
	}

	/**
	 * @return the search_tj_drzw
	 */
	public String[] getSearch_tj_drzw() {
		return search_tj_drzw;
	}

	/**
	 * @param searchTjDrzw要设置的 search_tj_drzw
	 */
	public void setSearch_tj_drzw(String[] searchTjDrzw) {
		search_tj_drzw = searchTjDrzw;
	}

	/**
	 * @return the search_tj_sfqz
	 */
	public String[] getSearch_tj_sfqz() {
		return search_tj_sfqz;
	}

	/**
	 * @param searchTjSfqz要设置的 search_tj_sfqz
	 */
	public void setSearch_tj_sfqz(String[] searchTjSfqz) {
		search_tj_sfqz = searchTjSfqz;
	}



	
	/**
	 * @return the search_tj_cxblb
	 */
	public String[] getSearch_tj_cxblb() {
		return search_tj_cxblb;
	}

	/**
	 * @param search_tj_cxblb要设置的 search_tj_cxblb
	 */
	public void setSearch_tj_cxblb(String[] search_tj_cxblb) {
		this.search_tj_cxblb = search_tj_cxblb;
	}
	
	/**
	 * @return the search_tj_shbz
	 */
	public String[] getSearch_tj_shbz() {
		return search_tj_shbz;
	}

	/**
	 * @param search_tj_shbz要设置的 search_tj_shbz
	 */
	public void setSearch_tj_shbz(String[] search_tj_shbz) {
		this.search_tj_shbz = search_tj_shbz;
	}

	/**
	 * @return the search_tj_ysjxjxmlx
	 */
	public String[] getSearch_tj_ysjxjxmlx() {
		return search_tj_ysjxjxmlx;
	}

	/**
	 * @return the search_tj_jxjb
	 */
	public String[] getSearch_tj_jxjb() {
		return search_tj_jxjb;
	}

	/**
	 * @param searchTjJxjb要设置的 search_tj_jxjb
	 */
	public void setSearch_tj_jxjb(String[] searchTjJxjb) {
		search_tj_jxjb = searchTjJxjb;
	}

	/**
	 * @return the search_tj_jxlbnew
	 */
	public String[] getSearch_tj_jxlbnew() {
		return search_tj_jxlbnew;
	}

	/**
	 * @param searchTjJxlbnew要设置的 search_tj_jxlbnew
	 */
	public void setSearch_tj_jxlbnew(String[] searchTjJxlbnew) {
		search_tj_jxlbnew = searchTjJxlbnew;
	}

	/**
	 * @param searchTjYsjxjxmlx要设置的 search_tj_ysjxjxmlx
	 */
	public void setSearch_tj_ysjxjxmlx(String[] searchTjYsjxjxmlx) {
		search_tj_ysjxjxmlx = searchTjYsjxjxmlx;
	}



	/**
	 * 勤工日期
	 */
	private String[] search_tj_qgrq;
	/**
	 * 勤工明细
	 */
	private String[] search_tj_qgmx;
	/**
	 * @return the search_tj_rdzt
	 */
	public String[] getSearch_tj_rdzt() {
		return search_tj_rdzt;
	}

	/**
	 * @return the search_tj_qgrq
	 */
	public String[] getSearch_tj_qgrq() {
		return search_tj_qgrq;
	}

	/**
	 * @param searchTjQgrq要设置的 search_tj_qgrq
	 */
	public void setSearch_tj_qgrq(String[] searchTjQgrq) {
		search_tj_qgrq = searchTjQgrq;
	}

	/**
	 * @return the search_tj_qgmx
	 */
	public String[] getSearch_tj_qgmx() {
		return search_tj_qgmx;
	}

	/**
	 * @param searchTjQgmx要设置的 search_tj_qgmx
	 */
	public void setSearch_tj_qgmx(String[] searchTjQgmx) {
		search_tj_qgmx = searchTjQgmx;
	}

	/**
	 * @param searchTjRdzt要设置的 search_tj_rdzt
	 */
	public void setSearch_tj_rdzt(String[] searchTjRdzt) {
		search_tj_rdzt = searchTjRdzt;
	}

	/**
	 * @return the search_tj_csms
	 */
	public String[] getSearch_tj_csms() {
		return search_tj_csms;
	}

	/**
	 * @param searchTjCsms要设置的 search_tj_csms
	 */
	public void setSearch_tj_csms(String[] searchTjCsms) {
		search_tj_csms = searchTjCsms;
	}


	/**
	 * @return the search_tj_bzrcpdj
	 */
	public String[] getSearch_tj_bzrcpdj() {
		return search_tj_bzrcpdj;
	}

	/**
	 * @param searchTjBzrcpdj要设置的 search_tj_bzrcpdj
	 */
	public void setSearch_tj_bzrcpdj(String[] searchTjBzrcpdj) {
		search_tj_bzrcpdj = searchTjBzrcpdj;
	}

	/**
	 * @return the search_tj_xscpdj
	 */
	public String[] getSearch_tj_xscpdj() {
		return search_tj_xscpdj;
	}

	/**
	 * @param searchTjXscpdj要设置的 search_tj_xscpdj
	 */
	public void setSearch_tj_xscpdj(String[] searchTjXscpdj) {
		search_tj_xscpdj = searchTjXscpdj;
	}

	/**
	 * @return the search_tj_zjz
	 */
	public String[] getSearch_tj_zjz() {
		return search_tj_zjz;
	}

	/**
	 * @return the search_tj_sdzt
	 */
	public String[] getSearch_tj_sdzt() {
		return search_tj_sdzt;
	}

	/**
	 * @param searchTjSdzt要设置的 search_tj_sdzt
	 */
	public void setSearch_tj_sdzt(String[] searchTjSdzt) {
		search_tj_sdzt = searchTjSdzt;
	}

	/**
	 * @return the search_tj_zjlysdzt
	 */
	public String[] getSearch_tj_zjlysdzt() {
		return search_tj_zjlysdzt;
	}

	/**
	 * @param searchTjZjlysdzt要设置的 search_tj_zjlysdzt
	 */
	public void setSearch_tj_zjlysdzt(String[] searchTjZjlysdzt) {
		search_tj_zjlysdzt = searchTjZjlysdzt;
	}

	/**
	 * @return the search_tj_zjlyshsdzt
	 */
	public String[] getSearch_tj_zjlyshsdzt() {
		return search_tj_zjlyshsdzt;
	}

	/**
	 * @param searchTjZjlyshsdzt要设置的 search_tj_zjlyshsdzt
	 */
	public void setSearch_tj_zjlyshsdzt(String[] searchTjZjlyshsdzt) {
		search_tj_zjlyshsdzt = searchTjZjlyshsdzt;
	}

	/**
	 * @return the search_tj_xmmkdm
	 */
	public String[] getSearch_tj_xmmkdm() {
		return search_tj_xmmkdm;
	}

	/**
	 * @param searchTjXmmkdm要设置的 search_tj_xmmkdm
	 */
	public void setSearch_tj_xmmkdm(String[] searchTjXmmkdm) {
		search_tj_xmmkdm = searchTjXmmkdm;
	}

	/**
	 * @return the search_tj_jfxmdm
	 */
	public String[] getSearch_tj_jfxmdm() {
		return search_tj_jfxmdm;
	}

	/**
	 * @param searchTjJfxmdm要设置的 search_tj_jfxmdm
	 */
	public void setSearch_tj_jfxmdm(String[] searchTjJfxmdm) {
		search_tj_jfxmdm = searchTjJfxmdm;
	}

	/**
	 * @param searchTjZjz要设置的 search_tj_zjz
	 */
	public void setSearch_tj_zjz(String[] searchTjZjz) {
		search_tj_zjz = searchTjZjz;
	}

	/**
	 * @return the search_tj_jclb
	 */
	public String[] getSearch_tj_jclb() {
		return search_tj_jclb;
	}

	/**
	 * @param searchTjJclb要设置的 search_tj_jclb
	 */
	public void setSearch_tj_jclb(String[] searchTjJclb) {
		search_tj_jclb = searchTjJclb;
	}

	/**
	 * @return the search_tj_lb
	 */
	public String[] getSearch_tj_lb() {
		return search_tj_lb;
	}

	/**
	 * @param searchTjLb要设置的 search_tj_lb
	 */
	public void setSearch_tj_lb(String[] searchTjLb) {
		search_tj_lb = searchTjLb;
	}

	/**
	 * @return the search_tj_kcmc
	 */
	public String[] getSearch_tj_kcmc() {
		return search_tj_kcmc;
	}

	/**
	 * @param searchTjKcmc要设置的 search_tj_kcmc
	 */
	public void setSearch_tj_kcmc(String[] searchTjKcmc) {
		search_tj_kcmc = searchTjKcmc;
	}



	//请假课程名称
	private String[] search_tj_kcmc = null;
	/*浙江旅游end*/
	/*北京中医药大学岗位高级查询条件*/
	private String[] search_tj_gw = null;
	/**
	 * @return the search_tj_qjksjc
	 */
	public String[] getSearch_tj_qjksjc() {
		return search_tj_qjksjc;
	}

	/**
	 * @param searchTjQjksjc要设置的 search_tj_qjksjc
	 */
	public void setSearch_tj_qjksjc(String[] searchTjQjksjc) {
		search_tj_qjksjc = searchTjQjksjc;
	}

	/**
	 * @return the search_tj_qjjsjc
	 */
	public String[] getSearch_tj_qjjsjc() {
		return search_tj_qjjsjc;
	}

	/**
	 * @param searchTjQjjsjc要设置的 search_tj_qjjsjc
	 */
	public void setSearch_tj_qjjsjc(String[] searchTjQjjsjc) {
		search_tj_qjjsjc = searchTjQjjsjc;
	}

	/**
	 * @return the search_tj_qjtslx
	 */
	public String[] getSearch_tj_qjtslx() {
		return search_tj_qjtslx;
	}

	/**
	 * @param searchTjQjtslx要设置的 search_tj_qjtslx
	 */
	public void setSearch_tj_qjtslx(String[] searchTjQjtslx) {
		search_tj_qjtslx = searchTjQjtslx;
	}


	
	// ===================学生信息=======================
	
	//华中师范大学受助资金类别
	private String[] search_tj_ytlb = null;
	/**
	 * @return the search_tj_gw
	 */
	public String[] getSearch_tj_gw() {
		return search_tj_gw;
	}

	/**
	 * @param searchTjGw要设置的 search_tj_gw
	 */
	public void setSearch_tj_gw(String[] searchTjGw) {
		search_tj_gw = searchTjGw;
	}

	/**
	 * @return the search_tj_qjlxmc
	 */
	public String[] getSearch_tj_qjlxmc() {
		return search_tj_qjlxmc;
	}

	/**
	 * @param searchTjQjlxmc要设置的 search_tj_qjlxmc
	 */
	public void setSearch_tj_qjlxmc(String[] searchTjQjlxmc) {
		search_tj_qjlxmc = searchTjQjlxmc;
	}

	public String[] getSearch_tj_shztOne() {
		return search_tj_shztOne;
	}

	public void setSearch_tj_shztOne(String[] searchTjShztOne) {
		search_tj_shztOne = searchTjShztOne;
	}
	
	
	/**
	 * @return the search_tj_xljkgxlx
	 */
	public String[] getSearch_tj_xljkgxlx() {
		return search_tj_xljkgxlx;
	}

	/**
	 * @param search_tj_xljkgxlx要设置的 search_tj_xljkgxlx
	 */
	public void setSearch_tj_xljkgxlx(String[] search_tj_xljkgxlx) {
		this.search_tj_xljkgxlx = search_tj_xljkgxlx;
	}

	/**
	 * @return the search_tj_xslb
	 */
	public String[] getSearch_tj_xslb() {
		return search_tj_xslb;
	}

	/**
	 * @param searchTjXslb要设置的 search_tj_xslb
	 */
	public void setSearch_tj_xslb(String[] searchTjXslb) {
		search_tj_xslb = searchTjXslb;
	}

	/**
	 * @return the search_tj_xljkgzlx
	 */
	public String[] getSearch_tj_xljkgzlx() {
		return search_tj_xljkgzlx;
	}

	/**
	 * @param search_tj_xljkgzlx要设置的 search_tj_xljkgzlx
	 */
	public void setSearch_tj_xljkgzlx(String[] search_tj_xljkgzlx) {
		this.search_tj_xljkgzlx = search_tj_xljkgzlx;
	}

	/**
	 * @return the search_tj_sfsfs
	 */
	public String[] getSearch_tj_sfsfs() {
		return search_tj_sfsfs;
	}

	/**
	 * @param searchTjSfsfs要设置的 search_tj_sfsfs
	 */
	public void setSearch_tj_sfsfs(String[] searchTjSfsfs) {
		search_tj_sfsfs = searchTjSfsfs;
	}
	
	/**
	 * @return the search_tj_xsfsfs
	 */
	public String[] getSearch_tj_xsfsfs() {
		return search_tj_xsfsfs;
	}

	/**
	 * @param searchTjXsfsfs要设置的 search_tj_xsfsfs
	 */
	public void setSearch_tj_xsfsfs(String[] searchTjXsfsfs) {
		search_tj_xsfsfs = searchTjXsfsfs;
	}

	/**
	 * @return the search_tj_sfbgb
	 */
	public String[] getSearch_tj_sfbgb() {
		return search_tj_sfbgb;
	}

	/**
	 * @param searchTjsfbgb要设置的 search_tj_sfbgb
	 */
	public void setSearch_tj_sfbgb(String[] searchTjSfbgb) {
		search_tj_sfbgb = searchTjSfbgb;
	}

	public String[] getSearch_tj_shztTwo() {
		return search_tj_shztTwo;
	}

	public void setSearch_tj_shztTwo(String[] searchTjShztTwo) {
		search_tj_shztTwo = searchTjShztTwo;
	}

	private String[] search_tj_pycc = null;// 培养层次

	private String[] search_tj_xz = null;// 学制条件

	private String[] search_tj_xj = null;// 学籍条件
	
	private String[] search_tj_lnjdhk = null;// 户口性质
	
	private String[] search_tj_bxxs = null;// 办学形式
	
	private String[] search_tj_zjxy = null;// 宗教信仰
	
	private String[] search_tj_sfzx = null;// 是否在校条件

	private String[] search_tj_sfyby = null;// 是否已毕业条件

	private String[] search_tj_sfjf = null;// 是否缴费

	private String[] search_tj_zm = null;// 政治面貌条件

	private String[] search_tj_mz = null;// 民族条件

	private String[] search_tj_hk = null;// 户口条件

	private String[] search_tj_zdlb = null;// 转档类别

	private String[] search_tj_ydlb = null;// 异动类别

	private String[] search_tj_prov = null;// 省


	private String[] search_tj_bmlx = null;//报名类型

	private String[] search_tj_sfdsh = null;// 单双号

	private String[] search_tj_stxj = null;// 社团星级

	private String[] search_tj_sfytj = null;// 是否已提交

	public String[] getSearch_tj_bmlx() {
		return search_tj_bmlx;
	}

	public void setSearch_tj_bmlx(String[] search_tj_bmlx) {
		this.search_tj_bmlx = search_tj_bmlx;
	}

	private String[] search_tj_dazxqk = null; // 档案在校情况

	private String[] search_tj_qyzt = null; // 启用状态

	private String[] search_tj_whzt = null; // 维护状态

	private String[] search_tj_sfzblx = null; // 是否征兵离校

	private String[] search_tj_zd1 = null; // 其他信息字段1

	private String[] search_tj_zd3 = null; // 其他信息字段3

	private String[] search_tj_zd5 = null; // 其他信息字段5

	private String[] search_tj_zd24 = null; // 其他信息字段24

	private String[] search_tj_zd26 = null; // 其他信息字段26

	private String[] search_tj_zd8 = null; // 其他信息字段8

	private String[] search_tj_zd9 = null; // 其他信息字段9

	private String[] search_tj_zd10 = null; // 其他信息字段10

	private String[] search_tj_zd11 = null; // 其他信息字段11

	private String[] search_tj_zd13 = null; // 其他信息字段13

	private String[] search_tj_zd15 = null; // 其他信息字段15

	private String[] search_tj_zd17 = null; // 其他信息字段17

	private String[] search_tj_zd16 = null; // 其他信息字段16

	private String[] search_tj_zd21 = null; // 其他信息字段21

	private String[] search_tj_rxfs = null; // 入学方式

	private String[] search_tj_xjlb = null;// 学籍类别

	private String[] search_tj_xjlbmc = null;// 学籍类别名称

	private String[] search_tj_bmlb = null;// 部门类别名称

	private String[] search_tj_zczt = null; // 华师大学期注册状态

	private String[] search_tj_txzt = null; // 华师大学年小结填写状态
	// ===================学生信息 end======================

	// ===================日常事务=======================

	private String[] search_tj_sftz = null;// 是否通知

	private String[] search_tj_sqjg = null;// 申请结果

	private String[] search_tj_qjlx = null;// 请假类型名称

	private String[] search_tj_qjlb = null;// 请假类型类别

	private String[] search_tj_fyffXm = null;// 费用发放项目
	
	private String[] search_tj_txhdlb = null;// 团学活动类别
	
	private String[] search_tj_lstdLy = null;// 绿色通道理由
	// ===================浙江商业begin=======================
	
	private String[] search_tj_zjsyzc = null;// 考勤周次
	
	// ===================浙江商业end=======================

	// ===================日常事务 end=======================

	// =================金华职业学生资助=====================
	private String[] search_tj_jhshzt = null;
	private String[] search_tj_tjdc = null;
	private String[] search_tj_zxjtjdc = null;

	// ===================公寓管理=======================

	private String[] search_tj_xqdm = null;// 校区代码

	private String[] search_tj_yqdm = null;// 园区代码

	private String[] search_tj_lddm = null;// 楼栋代码

	private String[] search_tj_cs = null;// 层数

	private String[] search_tj_qsh = null;// 寝室号

	private String[] search_tj_kfhz = null;// 可否混住

	private String[] search_tj_rzzt = null;// 入住状态

	private String[] search_tj_cwfp = null;// 床位分配状态

	private String[] search_tj_xbxd = null;// 性别限定

	private String[] search_tj_rzqk = null;// 入住情况

	private String[] search_tj_ch = null; // 层号

	private String[] search_tj_tsyy = null; // 退宿原因

	private String[] search_tj_ld = null;// 楼栋代码

	private String[] search_tj_sffp = null; // 是否分配

	private String[] search_tj_sfrz = null; // 是否入住

	private String[] search_tj_ldxb = null; // 楼栋性别

	private String[] search_tj_jjcd = null; // 紧急程度

	private String[] search_tj_gybxlb = null; // 报修类别

	private String[] search_tj_clzt = null; // 处理状态

	private String[] search_tj_clztdm = null; // 处理状态代码

	/**
	 * @return the search_tj_zyjrddj
	 */
	public String[] getSearch_tj_zyjrddj() {
		return search_tj_zyjrddj;
	}

	/**
	 * @param searchTjZyjrddj要设置的 search_tj_zyjrddj
	 */
	public void setSearch_tj_zyjrddj(String[] searchTjZyjrddj) {
		search_tj_zyjrddj = searchTjZyjrddj;
	}

	private String[] search_tj_jclx = null; // 卫生检查类型

	private String[] search_tj_zbry = null; // 值班人员

	private String[] search_tj_gygllx = null; // 公寓管理类型

	private String[] search_tj_gyjllbdldm = null;// 公寓纪律类别大类代码

	private String[] search_tj_gyjllbdm = null;// 公寓纪律类别代码

	private String[] search_tj_gyjlcflb = null;// 公寓纪律类别大类代码

	private String[] search_tj_pylb = null;// 评优类别

	private String[] search_tj_gyyjfl = null;// 公寓意见分类

	private String[] search_tj_gyyjxfkqk = null; // 反馈情况
	
	private String[] search_tj_ydlx = null;// 宿舍异动类型
	
	private String[] search_tj_ydlxjg = null;// 宿舍异动类型结果

	// ===================公寓管理 end=======================

	// ===================评奖评优=======================

	private String[] search_tj_xmdm = null;// 项目代码

	private String[] search_tj_sfpf = null;// 是否评分

	private String[] search_tj_sfqr = null;// 是否确认

	private String[] search_tj_sfsh = null;// 是否评分

	private String[] search_tj_sftj = null;// 是否确认

	private String[] search_tj_xmxz = null;// 项目性质

	private String[] search_tj_xmlx = null;// 项目类型

	private String[] search_tj_zczq = null;// 综测周期

	private String[] search_tj_pjzq = null;// 评奖周期

	private String[] search_tj_pjlsxm = null;// 评奖历史项目

	private String[] search_tj_hdry = null;// 获得荣誉

	private String[] search_tj_lxdm = null; // 特殊学生类型

	private String[] search_tj_xmmc = null; // 评奖项目名称

	private String[] search_tj_pjsqxm = null; // 评奖申请项目名称
	
	private String[] search_tj_jxsqzd = null; /*评奖项目名称-浙大新评奖*/

	private String[] search_tj_xxmlx = null; // 新评奖项目类型
	
	private String[] search_tj_xxmxz = null;// 新项目性质
	
	private String[] search_tj_thjlGzdj = null;// 谈话记录-关注等级
	
	private String[] search_tj_jxlb = null;// 奖项类别
	
	private String[] search_tj_jsfs = null;// 竞赛方式
	
	private String[] search_tj_jxdj = null;// 奖项等级
	
	private String[] search_tj_hjjxmc = null;// 奖项名次
	
	// ====================上海海洋-爱心超市=====================
	private String[] search_tj_wplb = null;// 物品类别
	private String[] search_tj_wpmc = null;// 物品名称
	
	// ====================上海海洋-爱心超市=====================

	// ====================天津职大=====================
	private String[] search_tj_sfzs = null;// 
	private String[] search_tj_sfsqrd = null;// 
	private String[] search_tj_sfssmz = null;// 
	private String[] search_tj_sfdgsx = null;// 
	private String[] search_tj_sflspx = null;// 
	private String[] search_tj_sfzjxy = null;// 
	private String[] search_tj_sfjjkn = null;// 
	private String[] search_tj_stsfcj = null;// 
	private String[] search_tj_sfxxkn = null;// 
	private String[] search_tj_sfxlkr = null;// 
	private String[] search_tj_sfjtkr = null;// 
	private String[] search_tj_sfyqtkr = null;// 

	// ====================天津职大=====================

	// ===================评奖评优end=======================

	// ===================违纪处分 begin================
	private String[] search_tj_cflb = null;// 违纪处分类别

	private String[] search_tj_cfyy = null;// 违纪处分原因

	private String[] search_tj_cflbmc = null;// 违纪处分类别名称

	private String[] search_tj_cfyymc = null;// 违纪处分原因名称

	private String[] search_tj_ssjg = null;// 申述结果
	// ===================违纪处分 end================

	// ===================党团建设=======================
	private String[] search_tj_jddm = null;// 阶段代码
	// ===================党团建设end=======================

	// ===================学校个性化 start=======================
	// --上海出版印刷--20111108--zhanghui--start--------------------
	private String[] search_tj_jtknlx = null; // 家庭困难类型
	// --上海出版印刷--20111108--zhanghui--end--------------------
	//浙江警察学院【大队维护】大队条件
	
	private String[] search_tj_knssqxz = null; //困难生申请性质
	
	private String[] search_tj_zjjcDd = null; 
	
	// ===================学校个性化 end=======================

	// ===================学校个性化 start=======================
	// --苏州工业园区--20111114--伟大的骆--start--------------------
	private String[] search_tj_cz = null; // 操作类型
	// --苏州工业园区--20111114--伟大的骆--end--------------------

	// --苏州工业园区--20111121--qlj--start--------------------
	private String[] search_tj_shztlx = null; // 审核状态
	// --苏州工业园区--20111121--qlj--end--------------------

	// ---------浙江建设---2012.3.1------qph----
	private String[] search_tj_sfhq = null;// 是否进修生
	// -----------------------------
	//西安科技大学
	private String[] search_tj_gfqkfl = null;
	//南通科技
	private String[] search_tj_kslb = null;
	// ---------[思政队伍]北京联合---2012.3.30------zh----
	private String[] search_tj_mc = null; // 名称
	private String[] search_tj_rq = null; // 日期
	private String[] search_tj_zw = null; // 职务
	// -----------------------------
	// ---------贵州大学【文明寝室】---2012.4.1------zh-----------
	private String[] search_tj_shzt1 = null; // 辅导员审核状态
	private String[] search_tj_shzt2 = null; // 学校审核状态
	private String[] search_tj_shzt3 = null; // 学校审核状态
	// -----------------------------------------------------------

	// ------------------学生资助 begin-----------------------
	private String[] search_tj_shzt4 = null; // 学校审核状态

	private String[] search_tj_shzt5 = null; // 学校审核状态

	private String[] search_tj_zzxmlb = null; // 资助项目类别
	// ------------------学生资助 end-----------------------

	// ====================勤工助学 start=================================
	private String[] search_tj_gwzt = null;
	private String[] search_tj_gwxz = null;
	private String[] search_tj_zgzt = null;
	private String[] search_tj_qgbm = null;
	private String[] search_tj_sfkns = null;
	private String[] search_tj_qgshzt = null;
	private String[] search_tj_qgxssq = null;
	private String[] search_tj_qgsybm = null;
	private String[] search_tj_qgsybmsq = null;
	// ====================勤工助学 end===================================
	// ====================心理健康咨询 start==============================

	private String[] search_tj_zgstatus = null;

	private String[] search_tj_weekday = null;

	private String[] search_tj_yystatus = null;

	private String[] search_tj_gzzt = null;
	private String[] search_tj_ybqk = null;
	// ====================心理健康咨询end=================================

	// ====================军训管理 start=================================
	private String[] search_tj_jxnj = null;
	private String[] search_tj_jxzt = null;
	private String[] search_tj_cxqk = null;
	private String[] search_tj_jxxx = null;
	private String[] search_tj_bxlb = null;
	private String[] search_tj_jtbx = null;
	private String[] search_tj_sfhd = null;

	private String[] search_tj_sfybz = null;
	// 军训建制团id
	private String[] search_tj_tid = null;
	// 军训建制营id
	private String[] search_tj_yid = null;
	// 军训建制连id
	private String[] search_tj_lid = null;
	// 军训建制排id
	private String[] search_tj_pid = null;
	// 军训建制班
	private String[] search_tj_bid = null;
	// 军训建制宿舍
	private String[] search_tj_ssid = null;
	
	//
	private String[] search_tj_jxdm = null;

	private String[] search_tj_cxdjdm = null;

	// ====================军训管理 end===================================

	// ====================思政队伍 begin=================================
	// 用户类型
	private String[] search_tj_yhsf = null;
	// 是否已编班
	private String[] search_tj_bbzt = null;

	// ====================思政队伍 end===================================

	// ====================日常行为维护 begin=================================
	// 用户类型
	private String[] search_tj_rcxwshzt = null;
	private String[] search_tj_rcxwdl = null;
	private String[] search_tj_rcxwlb = null;
	// private String[] search_tj_rcxwjlsj = null;

	// ====================日常行为维护end===================================
	
	private String[] search_tj_rcxwlbnew = null;
	private String[] search_tj_rcxwdlnew = null;
	private String[] search_tj_rcxwxlnew = null;
	
	private String[] search_tj_zxbk = null;
	
	private String[] search_tj_jydwxz = null;
	private String[] search_tj_jyxs = null;
	private String[] search_tj_sshy = null;
	private String[] search_tj_pxlx = null;
	private String[] search_tj_gslx = null;
	
	private String[] search_tj_hfzt = null;
	
	private String[] search_tj_kycxxmlb = null;
	
	private String[] search_tj_wjcddm = null;
	
	private String[] search_tj_wjgabz = null;
	
	private String[] search_tj_pjzt = null;
	
	private String[] search_tj_zxztnew = null;

	private String[] search_tj_qzxgzt = null;// 强制修改字段

	private String[] search_tj_bjdl = null;// 班级大类
	// ======================请假管理==============================//
	private String[] search_tj_xjzt = null;// 销假状态
	private String[] search_tj_qjlxdm = null;// 请假类型
	private String[] search_tj_qjzt = null;// 请假状态

	// -----------------------公寓在岗状态---------------------------//
	private String[] search_tj_gyzgzt = null;// 销假状态

	// =====================潍坊学院：==========================
	// 公寓物品维护
	private String[] search_tj_gywpdl = null;// 物品大类
	private String[] search_tj_gywplb = null;// 物品类别
	// 补助类型
	private String[] search_tj_bzlx = null;// 补助类型
	// =====================潍坊学院：==========================

	// ====================学生证补办 begin=================================
	// 用户类型
	private String[] search_tj_sfbbhcyhk = null;
	private String[] search_tj_xszbblx = null;

	// ====================日常行为维护end===================================

	// ======================入伍管理=============================//
	private String[] search_tj_rwfs = null; // 入伍方式
	// ======================入伍管理=============================//

	// ====================日常大学生医疗保险 begin=================================
	private String[] search_tj_cbzkmc = null;

	// ====================日常大学生医疗保险end===================================

	// 分班管理
	private String[] search_tj_xhqk = null; // 学号情况
	private String[] search_tj_fbqk = null; // 分班情况
	private String[] search_tj_syzt = null; // 使用状态
	private String[] search_tj_sfnz = null; // 是否内置
	private String[] search_tj_gzlx = null; // 规则类型
	// 集体评奖
	private String[] search_tj_jxmc = null; // 学号情况

	// ====================温大心理健康 begin=================================
	private String[] search_tj_zxsstatus = null; //咨询师在岗状态
	private String[] search_tj_zxszg = null; //咨询师任职资质
	private String[] search_tj_yyzt = null; //预约状态
	private String[] search_tj_zxzt = null; //咨询状态
	
	private String[] search_tj_yw = null;
	private String[] search_tj_zblx = null;
	private String[] search_tj_gzdj = null;
	private String[] search_tj_xlwtlx = null;
	// ====================温大心理健康 end===================================
	

	// ====================北京中医药学情分类===================================
	private String[] search_tj_xqfl = null;
	
	private String[] search_tj_xqfl1 = null;
	
	
		// ====================苏州卫生保险类型===================================
	private String[] search_tj_bxlx = null;

	private String[] search_tj_shztHb = null;// 培养层次
	private String[] search_tj_shztZj = null;// 培养层次

	//====================新疆工业职业技术学院===================================
	private String[] search_tj_xxqk;
	private String[] search_tj_zsqk;
	private String[] search_tj_qj;
	private String[] search_tj_zsqj;
	private String[] search_tj_zybj;

	private String[] search_tj_kclbdm;


	public String[] getSearch_tj_kclbdm() {
		return search_tj_kclbdm;
	}

	public void setSearch_tj_kclbdm(String[] search_tj_kclbdm) {
		this.search_tj_kclbdm = search_tj_kclbdm;
	}

	public String[] getSearch_tj_zybj() {
		return search_tj_zybj;
	}

	public void setSearch_tj_zybj(String[] search_tj_zybj) {
		this.search_tj_zybj = search_tj_zybj;
	}

	public String[] getSearch_tj_stxj() {
		return search_tj_stxj;
	}

	public void setSearch_tj_stxj(String[] search_tj_stxj) {
		this.search_tj_stxj = search_tj_stxj;
	}

	public String[] getSearch_tj_xxqk() {
		return search_tj_xxqk;
	}

	public void setSearch_tj_xxqk(String[] search_tj_xxqk) {
		this.search_tj_xxqk = search_tj_xxqk;
	}

	public String[] getSearch_tj_zsqk() {
		return search_tj_zsqk;
	}

	public void setSearch_tj_zsqk(String[] search_tj_zsqk) {
		this.search_tj_zsqk = search_tj_zsqk;
	}

	public String[] getSearch_tj_qj() {
		return search_tj_qj;
	}

	public void setSearch_tj_qj(String[] search_tj_qj) {
		this.search_tj_qj = search_tj_qj;
	}

	public String[] getSearch_tj_zsqj() {
		return search_tj_zsqj;
	}

	public void setSearch_tj_zsqj(String[] search_tj_zsqj) {
		this.search_tj_zsqj = search_tj_zsqj;
	}

	public String[] getSearch_tj_shztHb() {
		return search_tj_shztHb;
	}

	public void setSearch_tj_shztHb(String[] search_tj_shztHb) {
		this.search_tj_shztHb = search_tj_shztHb;
	}

	public String[] getSearch_tj_shztZj() {
		return search_tj_shztZj;
	}

	public void setSearch_tj_shztZj(String[] search_tj_shztZj) {
		this.search_tj_shztZj = search_tj_shztZj;
	}

	/**
	 * @return the search_tj_bxlx
	 */
	public String[] getSearch_tj_bxlx() {
		return search_tj_bxlx;
	}

	/**
	 * @param searchTjBxlx要设置的 search_tj_bxlx
	 */
	public void setSearch_tj_bxlx(String[] searchTjBxlx) {
		search_tj_bxlx = searchTjBxlx;
	}
	
	
	
	// ====================北京中医药咨询时间段===================================
	private String[] search_tj_sjd = null;
	

	/**
	 * @return the search_tj_sjd
	 */
	public String[] getSearch_tj_sjd() {
		return search_tj_sjd;
	}

	/**
	 * @param searchTjSjd要设置的 search_tj_sjd
	 */
	public void setSearch_tj_sjd(String[] searchTjSjd) {
		search_tj_sjd = searchTjSjd;
	}

	// ====================北京中医药教师身份===================================
	private String[] search_tj_jssf = null;
	/**
	 * @return the search_tj_jssf
	 */
	public String[] getSearch_tj_jssf() {
		return search_tj_jssf;
	}

	/**
	 * @param searchTjJssf要设置的 search_tj_jssf
	 */
	public void setSearch_tj_jssf(String[] searchTjJssf) {
		search_tj_jssf = searchTjJssf;
	}
	// ====================北京中医药问题紧急程度===================================
	private String[] search_tj_wtjjcd = null;
	/**
	 * @return the search_tj_wtjjcd
	 */
	public String[] getSearch_tj_wtjjcd() {
		return search_tj_wtjjcd;
	}

	/**
	 * @param searchTjWtjjcd要设置的 search_tj_wtjjcd
	 */
	public void setSearch_tj_wtjjcd(String[] searchTjWtjjcd) {
		search_tj_wtjjcd = searchTjWtjjcd;
	}

	private String[] search_tj_yxzt = null;
	
	//专业奖认定等级
	private String[] search_tj_zyjrddj = null;
	

	public String[] getSearch_tj_gwzt() {
		return search_tj_gwzt;
	}
	
	//是否寝室长（浙江旅游职业学院）
	private String[] search_tj_qsz = null;
	
	//六困生
	private String[] search_tj_lks = null;
	
	//浙江传媒（提交状态）
	private String[] search_tj_zjcmtjzt = null;
	
	//重庆移通 素质拓展 板块归属
	private String[] search_tj_bkgs = null;
	//开、关
	private String[] search_tj_kg = null;
	
	private String[] search_tj_yxzt1=null;
	
	/**==============浙大开始==============*/
	/*浙大-勤工助学-岗位类别*/
	private String[] search_tj_gwlb = null;
	/*浙大-勤工助学-校区*/
	private String[] search_tj_xiaoq = null;
	/*浙大-学习评价-短学期*/
	private String[] search_tj_dxq = null;
	/**==============浙大结束==============*/
	
	
	
	/**
	 * @return the search_tj_yxzt1
	 */
	public String[] getSearch_tj_yxzt1() {
		return search_tj_yxzt1;
	}

	/**
	 * @return the search_tj_dxq
	 */
	public String[] getSearch_tj_dxq() {
		return search_tj_dxq;
	}

	/**
	 * @param searchTjDxq要设置的 search_tj_dxq
	 */
	public void setSearch_tj_dxq(String[] searchTjDxq) {
		search_tj_dxq = searchTjDxq;
	}

	/**
	 * @param search_tj_yxzt1要设置的 search_tj_yxzt1
	 */
	public void setSearch_tj_yxzt1(String[] search_tj_yxzt1) {
		this.search_tj_yxzt1 = search_tj_yxzt1;
	}

	/**
	 * @return the search_tj_bkgs
	 */
	public String[] getSearch_tj_bkgs() {
		return search_tj_bkgs;
	}

	/**
	 * @param search_tj_bkgs要设置的 search_tj_bkgs
	 */
	public void setSearch_tj_bkgs(String[] search_tj_bkgs) {
		this.search_tj_bkgs = search_tj_bkgs;
	}

	/**
	 * @return the search_tj_gwlb
	 */
	public String[] getSearch_tj_gwlb() {
		return search_tj_gwlb;
	}

	/**
	 * @param searchTjGwlb要设置的 search_tj_gwlb
	 */
	public void setSearch_tj_gwlb(String[] searchTjGwlb) {
		search_tj_gwlb = searchTjGwlb;
	}

	/**
	 * @return the search_tj_xiaoq
	 */
	public String[] getSearch_tj_xiaoq() {
		return search_tj_xiaoq;
	}

	/**
	 * @param searchTjXiaoq要设置的 search_tj_xiaoq
	 */
	public void setSearch_tj_xiaoq(String[] searchTjXiaoq) {
		search_tj_xiaoq = searchTjXiaoq;
	}

	public String[] getSearch_tj_lks() {
		return search_tj_lks;
	}

	public void setSearch_tj_lks(String[] searchTjLks) {
		search_tj_lks = searchTjLks;
	}

	public String[] getSearch_tj_bmlb() {
		return search_tj_bmlb;
	}

	public void setSearch_tj_bmlb(String[] searchTjBmlb) {
		search_tj_bmlb = searchTjBmlb;
	}

	public String[] getSearch_tj_xjlbmc() {
		return search_tj_xjlbmc;
	}

	public void setSearch_tj_xjlbmc(String[] searchTjXjlbmc) {
		search_tj_xjlbmc = searchTjXjlbmc;
	}

	public String[] getSearch_tj_xjlb() {
		return search_tj_xjlb;
	}

	public void setSearch_tj_xjlb(String[] searchTjXjlb) {
		search_tj_xjlb = searchTjXjlb;
	}

	public String[] getSearch_tj_yhsf() {
		return search_tj_yhsf;
	}

	public void setSearch_tj_yhsf(String[] searchTjYhsf) {
		search_tj_yhsf = searchTjYhsf;
	}

	public String[] getSearch_tj_bbzt() {
		return search_tj_bbzt;
	}

	public void setSearch_tj_bbzt(String[] search_tj_bbzt) {
		this.search_tj_bbzt = search_tj_bbzt;
	}

	public String[] getSearch_tj_pylb() {
		return search_tj_pylb;
	}

	public void setSearch_tj_pylb(String[] searchTjPylb) {
		search_tj_pylb = searchTjPylb;
	}

	public String[] getSearch_tj_qgshzt() {
		return search_tj_qgshzt;
	}

	public void setSearch_tj_qgshzt(String[] searchTjQgshzt) {
		search_tj_qgshzt = searchTjQgshzt;
	}

	public String[] getSearch_tj_qgxssq() {
		return search_tj_qgxssq;
	}

	public void setSearch_tj_qgxssq(String[] searchTjQgxssq) {
		search_tj_qgxssq = searchTjQgxssq;
	}
	
	public String[] getSearch_tj_pycc() {
		return search_tj_pycc;
	}
	
	public void setSearch_tj_pycc(String[] search_tj_pycc) {
		this.search_tj_pycc = search_tj_pycc;
	} 

	public String[] getSearch_tj_zgstatus() {
		return search_tj_zgstatus;
	}

	public void setSearch_tj_zgstatus(String[] search_tj_zgstatus) {
		this.search_tj_zgstatus = search_tj_zgstatus;
	}

	public String[] getSearch_tj_weekday() {
		return search_tj_weekday;
	}

	public void setSearch_tj_weekday(String[] search_tj_weekday) {
		this.search_tj_weekday = search_tj_weekday;
	}

	public String[] getSearch_tj_yystatus() {
		return search_tj_yystatus;
	}

	public void setSearch_tj_yystatus(String[] search_tj_yystatus) {
		this.search_tj_yystatus = search_tj_yystatus;
	}

	/**
	 * @return the search_tj_gzzt
	 */
	public String[] getSearch_tj_gzzt() {
		return search_tj_gzzt;
	}

	/**
	 * @param search_tj_gzzt要设置的
	 *            search_tj_gzzt
	 */
	public void setSearch_tj_gzzt(String[] search_tj_gzzt) {
		this.search_tj_gzzt = search_tj_gzzt;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-28 上午10:17:00 
	 * @return		: the search_tj_ybqk
	 */
	public String[] getSearch_tj_ybqk() {
		return search_tj_ybqk;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-28 上午10:17:00 
	 * @param 		：searchTjYbqk the search_tj_ybqk to set
	 */
	public void setSearch_tj_ybqk(String[] searchTjYbqk) {
		search_tj_ybqk = searchTjYbqk;
	}

	public String[] getSearch_tj_sfkns() {
		return search_tj_sfkns;
	}

	public void setSearch_tj_sfkns(String[] searchTjSfkns) {
		search_tj_sfkns = searchTjSfkns;
	}

	public String[] getSearch_tj_sfhd() {
		return search_tj_sfhd;
	}

	public void setSearch_tj_sfhd(String[] searchTjSfhd) {
		search_tj_sfhd = searchTjSfhd;
	}

	public String[] getSearch_tj_jxxx() {
		return search_tj_jxxx;
	}

	public void setSearch_tj_jxxx(String[] searchTjJxxx) {
		search_tj_jxxx = searchTjJxxx;
	}

	public String[] getSearch_tj_bxlb() {
		return search_tj_bxlb;
	}

	public void setSearch_tj_bxlb(String[] searchTjBxlb) {
		search_tj_bxlb = searchTjBxlb;
	}

	public String[] getSearch_tj_jtbx() {
		return search_tj_jtbx;
	}

	public void setSearch_tj_jtbx(String[] searchTjJtbx) {
		search_tj_jtbx = searchTjJtbx;
	}

	public String[] getSearch_tj_cxqk() {
		return search_tj_cxqk;
	}

	public void setSearch_tj_cxqk(String[] searchTjCxqk) {
		search_tj_cxqk = searchTjCxqk;
	}

	public String[] getSearch_tj_gwxz() {
		return search_tj_gwxz;
	}

	public String[] getSearch_tj_zgzt() {
		return search_tj_zgzt;
	}

	public String[] getSearch_tj_qgbm() {
		return search_tj_qgbm;
	}

	public void setSearch_tj_gwxz(String[] searchTjGwxz) {
		search_tj_gwxz = searchTjGwxz;
	}

	public void setSearch_tj_gwzt(String[] searchTjGwzt) {
		search_tj_gwzt = searchTjGwzt;
	}

	public void setSearch_tj_zgzt(String[] searchTjZgzt) {
		search_tj_zgzt = searchTjZgzt;
	}

	public void setSearch_tj_qgbm(String[] searchTjQgbm) {
		search_tj_qgbm = searchTjQgbm;
	}

	public String[] getSearch_tj_jxnj() {
		return search_tj_jxnj;
	}

	public void setSearch_tj_jxnj(String[] searchTjJxnj) {
		search_tj_jxnj = searchTjJxnj;
	}

	public String[] getSearch_tj_jxzt() {
		return search_tj_jxzt;
	}

	public void setSearch_tj_jxzt(String[] searchTjJxzt) {
		search_tj_jxzt = searchTjJxzt;
	}

	// ===================学校个性化 end=======================
	public String[] getSearch_tj_xbxd() {
		return search_tj_xbxd;
	}

	public String[] getSearch_tj_mc() {
		return search_tj_mc;
	}

	public void setSearch_tj_mc(String[] searchTjMc) {
		search_tj_mc = searchTjMc;
	}

	public String[] getSearch_tj_rq() {
		return search_tj_rq;
	}

	public void setSearch_tj_rq(String[] searchTjRq) {
		search_tj_rq = searchTjRq;
	}

	public void setSearch_tj_xbxd(String[] search_tj_xbxd) {
		this.search_tj_xbxd = search_tj_xbxd;
	}

	public String[] getSearch_tj_cwfp() {
		return search_tj_cwfp;
	}

	public void setSearch_tj_cwfp(String[] search_tj_cwfp) {
		this.search_tj_cwfp = search_tj_cwfp;
	}

	public String[] getSearch_tj_rzzt() {
		return search_tj_rzzt;
	}

	public void setSearch_tj_rzzt(String[] search_tj_rzzt) {
		this.search_tj_rzzt = search_tj_rzzt;
	}

	public String[] getSearch_tj_kfhz() {
		return search_tj_kfhz;
	}

	public void setSearch_tj_kfhz(String[] search_tj_kfhz) {
		this.search_tj_kfhz = search_tj_kfhz;
	}

	public String[] getSearch_tj_sftz() {
		return search_tj_sftz;
	}

	public void setSearch_tj_sftz(String[] search_tj_sftz) {
		this.search_tj_sftz = search_tj_sftz;
	}

	public String[] getSearch_tj_sqjg() {
		return search_tj_sqjg;
	}

	public void setSearch_tj_sqjg(String[] search_tj_sqjg) {
		this.search_tj_sqjg = search_tj_sqjg;
	}

	public String[] getSearch_tj_prov() {
		return search_tj_prov;
	}

	public void setSearch_tj_prov(String[] search_tj_prov) {
		this.search_tj_prov = search_tj_prov;
	}

	public String[] getSearch_tj_zdlb() {
		return search_tj_zdlb;
	}

	public void setSearch_tj_zdlb(String[] search_tj_zdlb) {
		this.search_tj_zdlb = search_tj_zdlb;
	}

	// 拆分所录入的模糊查询
	public void setSearch_tj_mhcx(String mhcx) {

		if (!Base.isNull(input_mhcx)) {
			this.search_tj_mhcx = input_mhcx.split(" ");
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String[] getSearch_tj_bj() {
		return search_tj_bj;
	}

	public void setSearch_tj_bj(String[] search_tj_bj) {
		this.search_tj_bj = search_tj_bj;
	}

	public String[] getSearch_tj_xh() {
		return search_tj_xh;
	}

	public void setSearch_tj_xh(String[] search_tj_xh) {
		this.search_tj_xh = search_tj_xh;
	}

	public String[] getSearch_tj_xm() {
		return search_tj_xm;
	}

	public void setSearch_tj_xm(String[] search_tj_xm) {
		this.search_tj_xm = search_tj_xm;
	}

	public String[] getSearch_tj_xy() {
		return search_tj_xy;
	}

	public void setSearch_tj_xy(String[] search_tj_xy) {
		this.search_tj_xy = search_tj_xy;
	}

	public String[] getSearch_tj_zy() {
		return search_tj_zy;
	}


	public void setSearch_tj_zy(String[] search_tj_zy) {
		this.search_tj_zy = search_tj_zy;
	}

	public String[] getSearch_tj_nj() {
		return search_tj_nj;
	}

	public void setSearch_tj_nj(String[] search_tj_nj) {
		this.search_tj_nj = search_tj_nj;
	}

	public String[] getSearch_tj_xb() {
		return search_tj_xb;
	}

	public void setSearch_tj_xb(String[] search_tj_xb) {
		this.search_tj_xb = search_tj_xb;
	}

	public String[] getSearch_tj_nd() {
		return search_tj_nd;
	}

	public void setSearch_tj_nd(String[] search_tj_nd) {
		this.search_tj_nd = search_tj_nd;
	}

	public String[] getSearch_tj_xn() {
		return search_tj_xn;
	}

	public void setSearch_tj_xn(String[] search_tj_xn) {
		this.search_tj_xn = search_tj_xn;
	}

	public String[] getSearch_tj_rwxn() {
		return search_tj_rwxn;
	}

	public void setSearch_tj_rwxn(String[] search_tj_rwxn) {
		this.search_tj_rwxn = search_tj_rwxn;
	}

	public String[] getSearch_tj_xq() {
		return search_tj_xq;
	}

	public void setSearch_tj_xq(String[] search_tj_xq) {
		this.search_tj_xq = search_tj_xq;
	}

	public String getInput_mhcx() {
		return input_mhcx;
	}

	public void setInput_mhcx(String input_mhcx) {
		this.input_mhcx = input_mhcx;
	}

	public String[] getSearch_tj_mhcx() {
		return search_tj_mhcx;
	}

	public void setSearch_tj_mhcx(String[] search_tj_mhcx) {
		this.search_tj_mhcx = search_tj_mhcx;
	}

	public String[] getSearch_tj_mz() {
		return search_tj_mz;
	}

	public void setSearch_tj_mz(String[] search_tj_mz) {
		this.search_tj_mz = search_tj_mz;
	}

	public String[] getSearch_tj_sfyby() {
		return search_tj_sfyby;
	}

	public void setSearch_tj_sfyby(String[] search_tj_sfyby) {
		this.search_tj_sfyby = search_tj_sfyby;
	}

	public String[] getSearch_tj_sfzx() {
		return search_tj_sfzx;
	}

	public void setSearch_tj_sfzx(String[] search_tj_sfzx) {
		this.search_tj_sfzx = search_tj_sfzx;
	}

	public String[] getSearch_tj_xj() {
		return search_tj_xj;
	}

	public void setSearch_tj_xj(String[] search_tj_xj) {
		this.search_tj_xj = search_tj_xj;
	}

	public String[] getSearch_tj_xz() {
		return search_tj_xz;
	}

	public void setSearch_tj_xz(String[] search_tj_xz) {
		this.search_tj_xz = search_tj_xz;
	}

	public String[] getSearch_tj_zm() {
		return search_tj_zm;
	}

	public void setSearch_tj_zm(String[] search_tj_zm) {
		this.search_tj_zm = search_tj_zm;
	}

	public String[] getSearch_tj_hk() {
		return search_tj_hk;
	}

	public void setSearch_tj_hk(String[] search_tj_hk) {
		this.search_tj_hk = search_tj_hk;
	}

	public String[] getSearch_tj_sf() {
		return search_tj_sf;
	}

	public void setSearch_tj_sf(String[] search_tj_sf) {
		this.search_tj_sf = search_tj_sf;
	}

	public String[] getSearch_tj_sflx() {
		return search_tj_sflx;
	}

	public void setSearch_tj_sflx(String[] search_tj_sflx) {
		this.search_tj_sflx = search_tj_sflx;
	}

	/**
	 * @return the search_tj_sfhg
	 */
	public String[] getSearch_tj_sfhg() {
		return search_tj_sfhg;
	}

	/**
	 * @param searchTjSfhg要设置的 search_tj_sfhg
	 */
	public void setSearch_tj_sfhg(String[] searchTjSfhg) {
		search_tj_sfhg = searchTjSfhg;
	}

	public String[] getSearch_tj_jssj() {
		return search_tj_jssj;
	}

	public void setSearch_tj_jssj(String[] search_tj_jssj) {
		this.search_tj_jssj = search_tj_jssj;
	}

	public String[] getSearch_tj_ksnum() {
		return search_tj_ksnum;
	}

	public void setSearch_tj_ksnum(String[] searchTjKsnum) {
		search_tj_ksnum = searchTjKsnum;
	}

	public String[] getSearch_tj_jsnum() {
		return search_tj_jsnum;
	}

	public void setSearch_tj_jsnum(String[] searchTjJsnum) {
		search_tj_jsnum = searchTjJsnum;
	}

	public String[] getSearch_tj_kssj() {
		return search_tj_kssj;
	}

	public void setSearch_tj_kssj(String[] search_tj_kssj) {
		this.search_tj_kssj = search_tj_kssj;
	}

	public String[] getSearch_tj_ydlb() {
		return search_tj_ydlb;
	}

	public void setSearch_tj_ydlb(String[] search_tj_ydlb) {
		this.search_tj_ydlb = search_tj_ydlb;
	}

	public String getInput_xh() {
		return input_xh;
	}

	public void setInput_xh(String input_xh) {
		this.input_xh = input_xh;
	}

	public String getMhcx_lx() {
		return mhcx_lx;
	}

	public void setMhcx_lx(String mhcx_lx) {
		this.mhcx_lx = mhcx_lx;
	}

	public String[] getSearch_tj_sfjf() {
		return search_tj_sfjf;
	}

	public void setSearch_tj_sfjf(String[] search_tj_sfjf) {
		this.search_tj_sfjf = search_tj_sfjf;
	}

	public String[] getSearch_tj_qgsybm() {
		return search_tj_qgsybm;
	}

	public void setSearch_tj_qgsybm(String[] search_tj_qgsybm) {
		this.search_tj_qgsybm = search_tj_qgsybm;
	}
	
	public String[] getSearch_tj_qgsybmsq() {
		return search_tj_qgsybmsq;
	}
	
	public void setSearch_tj_qgsybmsq(String[] search_tj_qgsybmsq) {
		this.search_tj_qgsybmsq = search_tj_qgsybmsq;
	}

	public String[] getSearch_tj_cs() {
		return search_tj_cs;
	}

	public void setSearch_tj_cs(String[] search_tj_cs) {
		this.search_tj_cs = search_tj_cs;
	}

	public String[] getSearch_tj_lddm() {
		return search_tj_lddm;
	}

	public void setSearch_tj_lddm(String[] search_tj_lddm) {
		this.search_tj_lddm = search_tj_lddm;
	}

	public String[] getSearch_tj_qsh() {
		return search_tj_qsh;
	}

	public void setSearch_tj_qsh(String[] search_tj_qsh) {
		this.search_tj_qsh = search_tj_qsh;
	}

	public String[] getSearch_tj_xqdm() {
		return search_tj_xqdm;
	}

	public void setSearch_tj_xqdm(String[] search_tj_xqdm) {
		this.search_tj_xqdm = search_tj_xqdm;
	}

	public String[] getSearch_tj_yqdm() {
		return search_tj_yqdm;
	}

	public void setSearch_tj_yqdm(String[] search_tj_yqdm) {
		this.search_tj_yqdm = search_tj_yqdm;
	}

	public String[] getSearch_tj_shzt() {
		return search_tj_shzt;
	}

	public void setSearch_tj_shzt(String[] search_tj_shzt) {
		this.search_tj_shzt = search_tj_shzt;
	}

	public String[] getSearch_tj_rzqk() {
		return search_tj_rzqk;
	}

	public void setSearch_tj_rzqk(String[] search_tj_rzqk) {
		this.search_tj_rzqk = search_tj_rzqk;
	}

	public String[] getSearch_tj_bm() {
		return search_tj_bm;
	}

	public void setSearch_tj_bm(String[] search_tj_bm) {
		this.search_tj_bm = search_tj_bm;
	}

	public String[] getSearch_tj_sfpf() {
		return search_tj_sfpf;
	}

	public void setSearch_tj_sfpf(String[] search_tj_sfpf) {
		this.search_tj_sfpf = search_tj_sfpf;
	}

	public String[] getSearch_tj_sfqr() {
		return search_tj_sfqr;
	}

	public void setSearch_tj_sfqr(String[] search_tj_sfqr) {
		this.search_tj_sfqr = search_tj_sfqr;
	}

	public String[] getSearch_tj_sfsh() {
		return search_tj_sfsh;
	}

	public void setSearch_tj_sfsh(String[] search_tj_sfsh) {
		this.search_tj_sfsh = search_tj_sfsh;
	}

	public String[] getSearch_tj_sftj() {
		return search_tj_sftj;
	}

	public void setSearch_tj_sftj(String[] search_tj_sftj) {
		this.search_tj_sftj = search_tj_sftj;
	}

	public String[] getSearch_tj_ch() {
		return search_tj_ch;
	}

	public void setSearch_tj_ch(String[] search_tj_ch) {
		this.search_tj_ch = search_tj_ch;
	}

	public String[] getSearch_tj_shjg() {
		return search_tj_shjg;
	}

	public void setSearch_tj_shjg(String[] search_tj_shjg) {
		this.search_tj_shjg = search_tj_shjg;
	}

	public String[] getSearch_tj_tsyy() {
		return search_tj_tsyy;
	}

	public void setSearch_tj_tsyy(String[] search_tj_tsyy) {
		this.search_tj_tsyy = search_tj_tsyy;
	}

	public String[] getSearch_tj_ld() {
		return search_tj_ld;
	}

	public void setSearch_tj_ld(String[] search_tj_ld) {
		this.search_tj_ld = search_tj_ld;
	}

	public String[] getSearch_tj_sffp() {
		return search_tj_sffp;
	}

	public void setSearch_tj_sffp(String[] searchTjSffp) {
		search_tj_sffp = searchTjSffp;
	}

	public String[] getSearch_tj_sfrz() {
		return search_tj_sfrz;
	}

	public void setSearch_tj_sfrz(String[] searchTjSfrz) {
		search_tj_sfrz = searchTjSfrz;
	}

	public String[] getSearch_tj_ldxb() {
		return search_tj_ldxb;
	}

	public void setSearch_tj_ldxb(String[] searchTjLdxb) {
		search_tj_ldxb = searchTjLdxb;
	}

	public String[] getSearch_tj_jtknlx() {
		return search_tj_jtknlx;
	}

	public void setSearch_tj_jtknlx(String[] searchTjJtknlx) {
		search_tj_jtknlx = searchTjJtknlx;
	}

	public String[] getSearch_tj_cz() {
		return search_tj_cz;
	}

	public void setSearch_tj_cz(String[] search_tj_cz) {
		this.search_tj_cz = search_tj_cz;
	}

	public String[] getSearch_tj_lx() {
		return search_tj_lx;
	}

	public void setSearch_tj_lx(String[] search_tj_lx) {
		this.search_tj_lx = search_tj_lx;
	}

	public String[] getSearch_tj_xmdm() {
		return search_tj_xmdm;
	}

	public void setSearch_tj_xmdm(String[] search_tj_xmdm) {
		this.search_tj_xmdm = search_tj_xmdm;
	}

	public String[] getSearch_tj_jjcd() {
		return search_tj_jjcd;
	}

	public void setSearch_tj_jjcd(String[] searchTjJjcd) {
		search_tj_jjcd = searchTjJjcd;
	}

	/**
	 * @return the search_tj_gybxlb
	 */
	public String[] getSearch_tj_gybxlb() {
		return search_tj_gybxlb;
	}

	/**
	 * @param search_tj_gybxlb要设置的
	 *            search_tj_gybxlb
	 */
	public void setSearch_tj_gybxlb(String[] search_tj_gybxlb) {
		this.search_tj_gybxlb = search_tj_gybxlb;
	}

	public String[] getSearch_tj_clzt() {
		return search_tj_clzt;
	}

	public void setSearch_tj_clzt(String[] searchTjClzt) {
		search_tj_clzt = searchTjClzt;
	}

	public String[] getSearch_tj_shztlx() {
		return search_tj_shztlx;
	}

	public void setSearch_tj_shztlx(String[] search_tj_shztlx) {
		this.search_tj_shztlx = search_tj_shztlx;
	}

	public String[] getSearch_tj_zbry() {
		return search_tj_zbry;
	}

	public void setSearch_tj_zbry(String[] searchTjZbry) {
		search_tj_zbry = searchTjZbry;
	}

	public String[] getSearch_tj_gygllx() {
		return search_tj_gygllx;
	}

	public void setSearch_tj_gygllx(String[] searchTjGygllx) {
		search_tj_gygllx = searchTjGygllx;
	}

	public String[] getSearch_tj_gyjllbdldm() {
		return search_tj_gyjllbdldm;
	}

	public void setSearch_tj_gyjllbdldm(String[] searchTjGyjllbdldm) {
		search_tj_gyjllbdldm = searchTjGyjllbdldm;
	}

	public String[] getSearch_tj_qjlx() {
		return search_tj_qjlx;
	}

	public void setSearch_tj_qjlx(String[] search_tj_qjlx) {
		this.search_tj_qjlx = search_tj_qjlx;
	}

	public String[] getSearch_tj_qjlb() {
		return search_tj_qjlb;
	}

	public void setSearch_tj_qjlb(String[] search_tj_qjlb) {
		this.search_tj_qjlb = search_tj_qjlb;
	}

	public String[] getSearch_tj_jddm() {
		return search_tj_jddm;
	}

	public void setSearch_tj_jddm(String[] searchTjJddm) {
		search_tj_jddm = searchTjJddm;
	}

	public String[] getSearch_tj_sfhq() {
		return search_tj_sfhq;
	}

	public void setSearch_tj_sfhq(String[] search_tj_sfhq) {
		this.search_tj_sfhq = search_tj_sfhq;
	}

	public String[] getSearch_tj_shzt4() {
		return search_tj_shzt4;
	}

	public void setSearch_tj_shzt4(String[] searchTjShzt4) {
		search_tj_shzt4 = searchTjShzt4;
	}

	public String[] getSearch_tj_shzt5() {
		return search_tj_shzt5;
	}

	public void setSearch_tj_shzt5(String[] searchTjShzt5) {
		search_tj_shzt5 = searchTjShzt5;
	}

	public String[] getSearch_tj_zzxmlb() {
		return search_tj_zzxmlb;
	}

	public void setSearch_tj_zzxmlb(String[] search_tj_zzxmlb) {
		this.search_tj_zzxmlb = search_tj_zzxmlb;
	}

	public String[] getSearch_tj_shzt1() {
		return search_tj_shzt1;
	}

	public void setSearch_tj_shzt1(String[] searchTjShzt1) {
		search_tj_shzt1 = searchTjShzt1;
	}

	public String[] getSearch_tj_shzt2() {
		return search_tj_shzt2;
	}

	public void setSearch_tj_shzt2(String[] searchTjShzt2) {
		search_tj_shzt2 = searchTjShzt2;
	}

	public String[] getSearch_tj_shzt3() {
		return search_tj_shzt3;
	}

	public void setSearch_tj_shzt3(String[] searchTjShzt3) {
		search_tj_shzt3 = searchTjShzt3;
	}

	public String[] getSearch_tj_dazxqk() {
		return search_tj_dazxqk;
	}

	public void setSearch_tj_dazxqk(String[] search_tj_dazxqk) {
		this.search_tj_dazxqk = search_tj_dazxqk;
	}

	public String[] getSearch_tj_qyzt() {
		return search_tj_qyzt;
	}

	public void setSearch_tj_qyzt(String[] search_tj_qyzt) {
		this.search_tj_qyzt = search_tj_qyzt;
	}

	public String[] getSearch_tj_whzt() {
		return search_tj_whzt;
	}

	public void setSearch_tj_whzt(String[] search_tj_whzt) {
		this.search_tj_whzt = search_tj_whzt;
	}

	public String[] getSearch_tj_sfytj() {
		return search_tj_sfytj;
	}

	public void setSearch_tj_sfytj(String[] search_tj_sfytj) {
		this.search_tj_sfytj = search_tj_sfytj;
	}

	public String[] getSearch_tj_sfdsh() {
		return search_tj_sfdsh;
	}

	public void setSearch_tj_sfdsh(String[] search_tj_sfdsh) {
		this.search_tj_sfdsh = search_tj_sfdsh;
	}

	public String[] getSearch_tj_knssqxz() {
		return search_tj_knssqxz;
	}

	public void setSearch_tj_knssqxz(String[] search_tj_knssqxz) {
		this.search_tj_knssqxz = search_tj_knssqxz;
	}

	public String[] getSearch_tj_xmxz() {
		return search_tj_xmxz;
	}

	public void setSearch_tj_xmxz(String[] searchTjXmxz) {
		search_tj_xmxz = searchTjXmxz;
	}

	public String[] getSearch_tj_xmlx() {
		return search_tj_xmlx;
	}

	public void setSearch_tj_xmlx(String[] searchTjXmlx) {
		search_tj_xmlx = searchTjXmlx;
	}

	public String[] getSearch_tj_zw() {
		return search_tj_zw;
	}

	public void setSearch_tj_zw(String[] search_tj_zw) {
		this.search_tj_zw = search_tj_zw;
	}

	public String[] getSearch_tj_zd1() {
		return search_tj_zd1;
	}

	public void setSearch_tj_zd1(String[] search_tj_zd1) {
		this.search_tj_zd1 = search_tj_zd1;
	}

	public String[] getSearch_tj_zd10() {
		return search_tj_zd10;
	}

	public void setSearch_tj_zd10(String[] search_tj_zd10) {
		this.search_tj_zd10 = search_tj_zd10;
	}

	public String[] getSearch_tj_zd11() {
		return search_tj_zd11;
	}

	public void setSearch_tj_zd11(String[] search_tj_zd11) {
		this.search_tj_zd11 = search_tj_zd11;
	}

	public String[] getSearch_tj_zd13() {
		return search_tj_zd13;
	}

	public void setSearch_tj_zd13(String[] search_tj_zd13) {
		this.search_tj_zd13 = search_tj_zd13;
	}

	public String[] getSearch_tj_zd15() {
		return search_tj_zd15;
	}

	public void setSearch_tj_zd15(String[] search_tj_zd15) {
		this.search_tj_zd15 = search_tj_zd15;
	}

	public String[] getSearch_tj_zd16() {
		return search_tj_zd16;
	}

	public void setSearch_tj_zd16(String[] search_tj_zd16) {
		this.search_tj_zd16 = search_tj_zd16;
	}

	public String[] getSearch_tj_zd17() {
		return search_tj_zd17;
	}

	public void setSearch_tj_zd17(String[] search_tj_zd17) {
		this.search_tj_zd17 = search_tj_zd17;
	}

	public String[] getSearch_tj_zd21() {
		return search_tj_zd21;
	}

	public void setSearch_tj_zd21(String[] search_tj_zd21) {
		this.search_tj_zd21 = search_tj_zd21;
	}

	public String[] getSearch_tj_zd24() {
		return search_tj_zd24;
	}

	public void setSearch_tj_zd24(String[] search_tj_zd24) {
		this.search_tj_zd24 = search_tj_zd24;
	}

	public String[] getSearch_tj_zd26() {
		return search_tj_zd26;
	}

	public void setSearch_tj_zd26(String[] search_tj_zd26) {
		this.search_tj_zd26 = search_tj_zd26;
	}

	public String[] getSearch_tj_zd3() {
		return search_tj_zd3;
	}

	public void setSearch_tj_zd3(String[] search_tj_zd3) {
		this.search_tj_zd3 = search_tj_zd3;
	}

	public String[] getSearch_tj_zd5() {
		return search_tj_zd5;
	}

	public void setSearch_tj_zd5(String[] search_tj_zd5) {
		this.search_tj_zd5 = search_tj_zd5;
	}

	public String[] getSearch_tj_zd8() {
		return search_tj_zd8;
	}

	public void setSearch_tj_zd8(String[] search_tj_zd8) {
		this.search_tj_zd8 = search_tj_zd8;
	}

	public String[] getSearch_tj_zd9() {
		return search_tj_zd9;
	}

	public void setSearch_tj_zd9(String[] search_tj_zd9) {
		this.search_tj_zd9 = search_tj_zd9;
	}

	public String[] getSearch_tj_sfzblx() {
		return search_tj_sfzblx;
	}

	public void setSearch_tj_sfzblx(String[] searchTjSfzblx) {
		search_tj_sfzblx = searchTjSfzblx;
	}

	public String[] getSearch_tj_zczq() {
		return search_tj_zczq;
	}

	public void setSearch_tj_zczq(String[] search_tj_zczq) {
		this.search_tj_zczq = search_tj_zczq;
	}

	public String[] getSearch_tj_pjzq() {
		return search_tj_pjzq;
	}

	public void setSearch_tj_pjzq(String[] searchTjPjzq) {
		search_tj_pjzq = searchTjPjzq;
	}

	public String[] getSearch_tj_cflb() {
		return search_tj_cflb;
	}

	public void setSearch_tj_cflb(String[] searchTjCflb) {
		search_tj_cflb = searchTjCflb;
	}

	public String[] getSearch_tj_cfyy() {
		return search_tj_cfyy;
	}

	public void setSearch_tj_cfyy(String[] searchTjCfyy) {
		search_tj_cfyy = searchTjCfyy;
	}

	public String[] getSearch_tj_pjlsxm() {
		return search_tj_pjlsxm;
	}

	public String[] getSearch_tj_hdry() {
		return search_tj_hdry;
	}

	public void setSearch_tj_hdry(String[] searchTjHdry) {
		search_tj_hdry = searchTjHdry;
	}

	public String[] getSearch_tj_ssjg() {
		return search_tj_ssjg;
	}

	public void setSearch_tj_ssjg(String[] searchTjSsjg) {
		search_tj_ssjg = searchTjSsjg;
	}

	public void setSearch_tj_pjlsxm(String[] searchTjPjlsxm) {
		search_tj_pjlsxm = searchTjPjlsxm;
	}

	public String[] getSearch_tj_shjg2() {
		return search_tj_shjg2;
	}

	public void setSearch_tj_shjg2(String[] search_tj_shjg2) {
		this.search_tj_shjg2 = search_tj_shjg2;
	}
	
	public String[] getSearch_tj_shjg3() {
		return search_tj_shjg3;
	}

	public void setSearch_tj_shjg3(String[] search_tj_shjg3) {
		this.search_tj_shjg3 = search_tj_shjg3;
	}

	public String[] getSearch_tj_ylbxzt() {
		return search_tj_ylbxzt;
	}

	public void setSearch_tj_ylbxzt(String[] searchTjYlbxzt) {
		search_tj_ylbxzt = searchTjYlbxzt;
	}

	public String[] getSearch_tj_yjnum() {
		return search_tj_yjnum;
	}

	public void setSearch_tj_yjnum(String[] searchTjYjnum) {
		search_tj_yjnum = searchTjYjnum;
	}
	
	public String[] getSearch_tj_bynd() {
		return search_tj_bynd;
	}

	public void setSearch_tj_bynd(String[] searchTjBynd) {
		search_tj_bynd = searchTjBynd;
	}

	public String[] getSearch_tj_yf() {
		return search_tj_yf;
	}

	public void setSearch_tj_yf(String[] searchTjYf) {
		search_tj_yf = searchTjYf;
	}

	public String[] getSearch_tj_cflbmc() {
		return search_tj_cflbmc;
	}

	public void setSearch_tj_cflbmc(String[] searchTjCflbmc) {
		search_tj_cflbmc = searchTjCflbmc;
	}

	public String[] getSearch_tj_cfyymc() {
		return search_tj_cfyymc;
	}

	public void setSearch_tj_cfyymc(String[] searchTjCfyymc) {
		search_tj_cfyymc = searchTjCfyymc;
	}

	public String[] getSearch_tj_sfybz() {
		return search_tj_sfybz;
	}

	public void setSearch_tj_sfybz(String[] searchTjSfybz) {
		search_tj_sfybz = searchTjSfybz;
	}

	public String[] getSearch_tj_tid() {
		return search_tj_tid;
	}

	public void setSearch_tj_tid(String[] searchTjTid) {
		search_tj_tid = searchTjTid;
	}

	public String[] getSearch_tj_yid() {
		return search_tj_yid;
	}

	public void setSearch_tj_yid(String[] searchTjYid) {
		search_tj_yid = searchTjYid;
	}

	public String[] getSearch_tj_lid() {
		return search_tj_lid;
	}

	public void setSearch_tj_lid(String[] searchTjLid) {
		search_tj_lid = searchTjLid;
	}

	public String[] getSearch_tj_pid() {
		return search_tj_pid;
	}

	public void setSearch_tj_pid(String[] searchTjPid) {
		search_tj_pid = searchTjPid;
	}
	
	public String[] getSearch_tj_bid() {
		return search_tj_bid;
	}

	public void setSearch_tj_bid(String[] searchTjBid) {
		search_tj_bid = searchTjBid;
	}

	public String[] getSearch_tj_ssid() {
		return search_tj_ssid;
	}

	public void setSearch_tj_ssid(String[] searchTjSsid) {
		search_tj_ssid = searchTjSsid;
	}

	public String[] getSearch_tj_jxdm() {
		return search_tj_jxdm;
	}

	public void setSearch_tj_jxdm(String[] searchTjJxdm) {
		search_tj_jxdm = searchTjJxdm;
	}

	public String[] getSearch_tj_rxfs() {
		return search_tj_rxfs;
	}

	public void setSearch_tj_rxfs(String[] searchTjRxfs) {
		search_tj_rxfs = searchTjRxfs;
	}

	public String[] getSearch_tj_xqmc() {
		return search_tj_xqmc;
	}

	public void setSearch_tj_xqmc(String[] searchTjXqmc) {
		search_tj_xqmc = searchTjXqmc;
	}

	public void setSearch_tj_xl(String[] search_tj_xl) {
		this.search_tj_xl = search_tj_xl;
	}

	public String[] getSearch_tj_xl() {
		return search_tj_xl;
	}

	public void setSearch_tj_xw(String[] search_tj_xw) {
		this.search_tj_xw = search_tj_xw;
	}

	public String[] getSearch_tj_xw() {
		return search_tj_xw;
	}

	public void setSearch_tj_zc(String[] search_tj_zc) {
		this.search_tj_zc = search_tj_zc;
	}

	public String[] getSearch_tj_zc() {
		return search_tj_zc;
	}

	public void setSearch_tj_jhshzt(String[] search_tj_jhshzt) {
		this.search_tj_jhshzt = search_tj_jhshzt;
	}

	public String[] getSearch_tj_jhshzt() {
		return search_tj_jhshzt;
	}

	public void setSearch_tj_tjdc(String[] search_tj_tjdc) {
		this.search_tj_tjdc = search_tj_tjdc;
	}

	public String[] getSearch_tj_tjdc() {
		return search_tj_tjdc;
	}

	public void setSearch_tj_zxjtjdc(String[] search_tj_zxjtjdc) {
		this.search_tj_zxjtjdc = search_tj_zxjtjdc;
	}

	public String[] getSearch_tj_zxjtjdc() {
		return search_tj_zxjtjdc;
	}

	public String[] getSearch_tj_gyjlcflb() {
		return search_tj_gyjlcflb;
	}

	public void setSearch_tj_gyjlcflb(String[] searchTjGyjlcflb) {
		search_tj_gyjlcflb = searchTjGyjlcflb;
	}

	public String[] getSearch_tj_gyjllbdm() {
		return search_tj_gyjllbdm;
	}

	public void setSearch_tj_gyjllbdm(String[] searchTjGyjllbdm) {
		search_tj_gyjllbdm = searchTjGyjllbdm;
	}

	public String[] getSearch_tj_clztdm() {
		return search_tj_clztdm;
	}

	public void setSearch_tj_clztdm(String[] searchTjClztdm) {
		search_tj_clztdm = searchTjClztdm;
	}

	/**
	 * @return the search_tj_jclx
	 */
	public String[] getSearch_tj_jclx() {
		return search_tj_jclx;
	}

	/**
	 * @param searchTjJclx要设置的 search_tj_jclx
	 */
	public void setSearch_tj_jclx(String[] searchTjJclx) {
		search_tj_jclx = searchTjJclx;
	}

	public String[] getSearch_tj_shztdm() {
		return search_tj_shztdm;
	}

	public void setSearch_tj_shztdm(String[] searchTjShztdm) {
		search_tj_shztdm = searchTjShztdm;
	}

	public String getArrange() {
		return arrange;
	}

	public void setArrange(String arrange) {
		this.arrange = arrange;
	}

	public String getFashion() {
		return fashion;
	}

	public void setFashion(String fashion) {
		this.fashion = fashion;
	}

	public String[] getSearch_tj_zzmm() {
		return search_tj_zzmm;
	}

	public void setSearch_tj_zzmm(String[] searchTjZzmm) {
		search_tj_zzmm = searchTjZzmm;
	}

	public String[] getSearch_tj_qzxgzt() {
		return search_tj_qzxgzt;
	}

	public void setSearch_tj_qzxgzt(String[] searchTjQzxgzt) {
		search_tj_qzxgzt = searchTjQzxgzt;
	}

	public String[] getSearch_tj_rddc() {
		return search_tj_rddc;
	}

	public void setSearch_tj_rddc(String[] searchTjRddc) {
		search_tj_rddc = searchTjRddc;
	}

	/**
	 * @return the search_tj_sjdc
	 */
	public String[] getSearch_tj_sjdc() {
		return search_tj_sjdc;
	}

	/**
	 * @param search_tj_sjdc要设置的
	 *            search_tj_sjdc
	 */
	public void setSearch_tj_sjdc(String[] searchTjSjdc) {
		this.search_tj_sjdc = searchTjSjdc;
	}

	public String[] getSearch_tj_zhzt() {
		return search_tj_zhzt;
	}

	public void setSearch_tj_zhzt(String[] searchTjZhzt) {
		search_tj_zhzt = searchTjZhzt;
	}

	public String[] getSearch_tj_zzxm() {
		return search_tj_zzxm;
	}

	public void setSearch_tj_zzxm(String[] searchTjZzxm) {
		search_tj_zzxm = searchTjZzxm;
	}
	
	public String[] getSearch_tj_zzxm1() {
		return search_tj_zzxm1;
	}

	public void setSearch_tj_zzxm1(String[] searchTjZzxm1) {
		search_tj_zzxm1 = searchTjZzxm1;
	}
	
	public String[] getSearch_tj_pfzt() {
		return search_tj_pfzt;
	}

	public void setSearch_tj_pfzt(String[] searchTjPfzt) {
		search_tj_pfzt = searchTjPfzt;
	}

	public String[] getSearch_tj_sheng() {
		return search_tj_sheng;
	}

	public void setSearch_tj_sheng(String[] searchTjSheng) {
		search_tj_sheng = searchTjSheng;
	}

	public String[] getSearch_tj_shi() {
		return search_tj_shi;
	}

	public void setSearch_tj_shi(String[] searchTjShi) {
		search_tj_shi = searchTjShi;
	}

	public String[] getSearch_tj_qu() {
		return search_tj_qu;
	}

	public void setSearch_tj_qu(String[] searchTjQu) {
		search_tj_qu = searchTjQu;
	}

	public void setSearch_tj_cxdjdm(String[] search_tj_cxdjdm) {
		this.search_tj_cxdjdm = search_tj_cxdjdm;
	}

	public String[] getSearch_tj_cxdjdm() {
		return search_tj_cxdjdm;
	}

	public String[] getSearch_tj_tjzt() {
		return search_tj_tjzt;
	}

	public void setSearch_tj_tjzt(String[] search_tj_tjzt) {
		this.search_tj_tjzt = search_tj_tjzt;
	}

	/**
	 * @return the search_tj_sfzs
	 */
	public String[] getSearch_tj_sfzs() {
		return search_tj_sfzs;
	}

	/**
	 * @param searchTjSfzs要设置的
	 *            search_tj_sfzs
	 */
	public void setSearch_tj_sfzs(String[] searchTjSfzs) {
		search_tj_sfzs = searchTjSfzs;
	}

	/**
	 * @return the search_tj_sfsqrd
	 */
	public String[] getSearch_tj_sfsqrd() {
		return search_tj_sfsqrd;
	}

	/**
	 * @param searchTjSfsqrd要设置的
	 *            search_tj_sfsqrd
	 */
	public void setSearch_tj_sfsqrd(String[] searchTjSfsqrd) {
		search_tj_sfsqrd = searchTjSfsqrd;
	}

	/**
	 * @return the search_tj_sfssmz
	 */
	public String[] getSearch_tj_sfssmz() {
		return search_tj_sfssmz;
	}

	/**
	 * @param searchTjSfssmz要设置的
	 *            search_tj_sfssmz
	 */
	public void setSearch_tj_sfssmz(String[] searchTjSfssmz) {
		search_tj_sfssmz = searchTjSfssmz;
	}

	/**
	 * @return the search_tj_sfdgsx
	 */
	public String[] getSearch_tj_sfdgsx() {
		return search_tj_sfdgsx;
	}

	/**
	 * @param searchTjSfdgsx要设置的
	 *            search_tj_sfdgsx
	 */
	public void setSearch_tj_sfdgsx(String[] searchTjSfdgsx) {
		search_tj_sfdgsx = searchTjSfdgsx;
	}

	/**
	 * @return the search_tj_sflspx
	 */
	public String[] getSearch_tj_sflspx() {
		return search_tj_sflspx;
	}

	/**
	 * @param searchTjSflspx要设置的
	 *            search_tj_sflspx
	 */
	public void setSearch_tj_sflspx(String[] searchTjSflspx) {
		search_tj_sflspx = searchTjSflspx;
	}

	/**
	 * @return the search_tj_sfzjxy
	 */
	public String[] getSearch_tj_sfzjxy() {
		return search_tj_sfzjxy;
	}

	/**
	 * @param searchTjSfzjxy要设置的
	 *            search_tj_sfzjxy
	 */
	public void setSearch_tj_sfzjxy(String[] searchTjSfzjxy) {
		search_tj_sfzjxy = searchTjSfzjxy;
	}

	/**
	 * @return the search_tj_sfjjkn
	 */
	public String[] getSearch_tj_sfjjkn() {
		return search_tj_sfjjkn;
	}

	/**
	 * @param searchTjSfjjkn要设置的
	 *            search_tj_sfjjkn
	 */
	public void setSearch_tj_sfjjkn(String[] searchTjSfjjkn) {
		search_tj_sfjjkn = searchTjSfjjkn;
	}

	/**
	 * @return the search_tj_stsfcj
	 */
	public String[] getSearch_tj_stsfcj() {
		return search_tj_stsfcj;
	}

	/**
	 * @param searchTjStsfcj要设置的
	 *            search_tj_stsfcj
	 */
	public void setSearch_tj_stsfcj(String[] searchTjStsfcj) {
		search_tj_stsfcj = searchTjStsfcj;
	}

	/**
	 * @return the search_tj_sfxxkn
	 */
	public String[] getSearch_tj_sfxxkn() {
		return search_tj_sfxxkn;
	}

	/**
	 * @param searchTjSfxxkn要设置的
	 *            search_tj_sfxxkn
	 */
	public void setSearch_tj_sfxxkn(String[] searchTjSfxxkn) {
		search_tj_sfxxkn = searchTjSfxxkn;
	}

	/**
	 * @return the search_tj_sfxlkr
	 */
	public String[] getSearch_tj_sfxlkr() {
		return search_tj_sfxlkr;
	}

	/**
	 * @param searchTjSfxlkr要设置的
	 *            search_tj_sfxlkr
	 */
	public void setSearch_tj_sfxlkr(String[] searchTjSfxlkr) {
		search_tj_sfxlkr = searchTjSfxlkr;
	}

	/**
	 * @return the search_tj_sfjtkr
	 */
	public String[] getSearch_tj_sfjtkr() {
		return search_tj_sfjtkr;
	}

	/**
	 * @param searchTjSfjtkr要设置的
	 *            search_tj_sfjtkr
	 */
	public void setSearch_tj_sfjtkr(String[] searchTjSfjtkr) {
		search_tj_sfjtkr = searchTjSfjtkr;
	}

	/**
	 * @return the search_tj_sfyqtkr
	 */
	public String[] getSearch_tj_sfyqtkr() {
		return search_tj_sfyqtkr;
	}

	/**
	 * @param searchTjSfyqtkr要设置的
	 *            search_tj_sfyqtkr
	 */
	public void setSearch_tj_sfyqtkr(String[] searchTjSfyqtkr) {
		search_tj_sfyqtkr = searchTjSfyqtkr;
	}

	public String[] getSearch_tj_cpz() {
		return search_tj_cpz;
	}

	public void setSearch_tj_cpz(String[] search_tj_cpz) {
		this.search_tj_cpz = search_tj_cpz;
	}

	/**
	 * @param search_tj_lxdm要设置的
	 *            search_tj_lxdm
	 */
	public void setSearch_tj_lxdm(String[] search_tj_lxdm) {
		this.search_tj_lxdm = search_tj_lxdm;
	}

	/**
	 * @return the search_tj_lxdm
	 */
	public String[] getSearch_tj_lxdm() {
		return search_tj_lxdm;
	}

	public String[] getSearch_tj_xmmc() {
		return search_tj_xmmc;
	}

	public void setSearch_tj_xmmc(String[] searchTjXmmc) {
		search_tj_xmmc = searchTjXmmc;
	}

	public String[] getSearch_tj_pjsqxm() {
		return search_tj_pjsqxm;
	}

	public void setSearch_tj_pjsqxm(String[] searchTjPjsqxm) {
		search_tj_pjsqxm = searchTjPjsqxm;
	}

	public String[] getSearch_tj_xxmlx() {
		return search_tj_xxmlx;
	}

	public void setSearch_tj_xxmlx(String[] searchTjXxmlx) {
		search_tj_xxmlx = searchTjXxmlx;
	}
	
	public String[] getSearch_tj_xxmxz() {
		return search_tj_xxmxz;
	}

	public void setSearch_tj_xxmxz(String[] searchTjXxmxz) {
		search_tj_xxmxz = searchTjXxmxz;
	}
	
	public String[] getSearch_tj_thjlGzdj() {
		return search_tj_thjlGzdj;
	}

	public void setSearch_tj_thjlGzdj(String[] searchTjThjlGzdj) {
		search_tj_thjlGzdj = searchTjThjlGzdj;
	}

	/**
	 * @return the search_tj_rcxwshzt
	 */
	public String[] getSearch_tj_rcxwshzt() {
		return search_tj_rcxwshzt;
	}

	/**
	 * @param search_tj_rcxwshzt要设置的
	 *            search_tj_rcxwshzt
	 */
	public void setSearch_tj_rcxwshzt(String[] search_tj_rcxwshzt) {
		this.search_tj_rcxwshzt = search_tj_rcxwshzt;
	}

	/**
	 * @return the search_tj_rcxwdl
	 */
	public String[] getSearch_tj_rcxwdl() {
		return search_tj_rcxwdl;
	}

	/**
	 * @param search_tj_rcxwdl要设置的
	 *            search_tj_rcxwdl
	 */
	public void setSearch_tj_rcxwdl(String[] search_tj_rcxwdl) {
		this.search_tj_rcxwdl = search_tj_rcxwdl;
	}
	
	/**
	 * @return the search_tj_kycxxmlb
	 */
	public String[] getSearch_tj_kycxxmlb() {
		return search_tj_kycxxmlb;
	}

	/**
	 * @param searchTjKycxxmlb要设置的 search_tj_kycxxmlb
	 */
	public void setSearch_tj_kycxxmlb(String[] searchTjKycxxmlb) {
		search_tj_kycxxmlb = searchTjKycxxmlb;
	}
	
	/**
	 * @return the search_tj_wjcddm
	 */
	public String[] getSearch_tj_wjcddm() {
		return search_tj_wjcddm;
	}

	/**
	 * @param searchTjWjcddm要设置的 search_tj_wjcddm
	 */
	public void setSearch_tj_wjcddm(String[] searchTjWjcddm) {
		search_tj_wjcddm = searchTjWjcddm;
	}
	
	/**
	 * @return the search_tj_wjgabz
	 */
	public String[] getSearch_tj_wjgabz() {
		return search_tj_wjgabz;
	}

	/**
	 * @param searchTjWjgabz要设置的 search_tj_wjgabz
	 */
	public void setSearch_tj_wjgabz(String[] searchTjWjgabz) {
		search_tj_wjgabz = searchTjWjgabz;
	}

	/**
	 * @return the search_tj_pjzt
	 */
	public String[] getSearch_tj_pjzt() {
		return search_tj_pjzt;
	}

	/**
	 * @param searchTjPjzt要设置的 search_tj_pjzt
	 */
	public void setSearch_tj_pjzt(String[] searchTjPjzt) {
		search_tj_pjzt = searchTjPjzt;
	}

	/**
	 * @return the search_tj_zxztnew
	 */
	public String[] getSearch_tj_zxztnew() {
		return search_tj_zxztnew;
	}

	/**
	 * @param searchTjZxztnew要设置的 search_tj_zxztnew
	 */
	public void setSearch_tj_zxztnew(String[] searchTjZxztnew) {
		search_tj_zxztnew = searchTjZxztnew;
	}

	/**
	 * @return the search_tj_rcxwlb
	 */
	public String[] getSearch_tj_rcxwlb() {
		return search_tj_rcxwlb;
	}

	/**
	 * @param search_tj_rcxwlb要设置的
	 *            search_tj_rcxwlb
	 */
	public void setSearch_tj_rcxwlb(String[] search_tj_rcxwlb) {
		this.search_tj_rcxwlb = search_tj_rcxwlb;
	}
	

	public String[] getSearch_tj_rcxwlbnew() {
		return search_tj_rcxwlbnew;
	}

	public void setSearch_tj_rcxwlbnew(String[] searchTjRcxwlbnew) {
		search_tj_rcxwlbnew = searchTjRcxwlbnew;
	}

	public String[] getSearch_tj_rcxwdlnew() {
		return search_tj_rcxwdlnew;
	}

	public void setSearch_tj_rcxwdlnew(String[] searchTjRcxwdlnew) {
		search_tj_rcxwdlnew = searchTjRcxwdlnew;
	}

	public String[] getSearch_tj_rcxwxlnew() {
		return search_tj_rcxwxlnew;
	}

	public void setSearch_tj_rcxwxlnew(String[] searchTjRcxwxlnew) {
		search_tj_rcxwxlnew = searchTjRcxwxlnew;
	}

	public String[] getSearch_tj_zxbk() {
		return search_tj_zxbk;
	}

	public void setSearch_tj_zxbk(String[] searchTjZxbk) {
		search_tj_zxbk = searchTjZxbk;
	}
	
	public String[] getSearch_tj_jydwxz() {
		return search_tj_jydwxz;
	}

	public void setSearch_tj_jydwxz(String[] searchTjJydwxz) {
		search_tj_jydwxz = searchTjJydwxz;
	}

	public String[] getSearch_tj_jyxs() {
		return search_tj_jyxs;
	}

	public void setSearch_tj_jyxs(String[] searchTjJyxs) {
		search_tj_jyxs = searchTjJyxs;
	}

	public String[] getSearch_tj_sshy() {
		return search_tj_sshy;
	}

	public void setSearch_tj_sshy(String[] searchTjSshy) {
		search_tj_sshy = searchTjSshy;
	}

	public String[] getSearch_tj_pxlx() {
		return search_tj_pxlx;
	}

	public void setSearch_tj_pxlx(String[] searchTjPxlx) {
		search_tj_pxlx = searchTjPxlx;
	}
	
	public String[] getSearch_tj_gslx() {
		return search_tj_gslx;
	}

	public void setSearch_tj_gslx(String[] searchTjGslx) {
		search_tj_gslx = searchTjGslx;
	}

	public String[] getSearch_tj_hfzt() {
		return search_tj_hfzt;
	}

	public void setSearch_tj_hfzt(String[] searchTjHfzt) {
		search_tj_hfzt = searchTjHfzt;
	}

	public void setSearch_tj_gywpdl(String[] search_tj_gywpdl) {
		this.search_tj_gywpdl = search_tj_gywpdl;
	}

	public String[] getSearch_tj_gywpdl() {
		return search_tj_gywpdl;
	}

	public void setSearch_tj_gywplb(String[] search_tj_gywplb) {
		this.search_tj_gywplb = search_tj_gywplb;
	}

	public String[] getSearch_tj_gywplb() {
		return search_tj_gywplb;
	}

	public void setSearch_tj_bzlx(String[] search_tj_bzlx) {
		this.search_tj_bzlx = search_tj_bzlx;
	}

	public String[] getSearch_tj_bzlx() {
		return search_tj_bzlx;
	}

	/**
	 * @return the search_tj_bjdl
	 */
	public String[] getSearch_tj_bjdl() {
		return search_tj_bjdl;
	}

	/**
	 * @param search_tj_bjdl要设置的
	 *            search_tj_bjdl
	 */
	public void setSearch_tj_bjdl(String[] search_tj_bjdl) {
		this.search_tj_bjdl = search_tj_bjdl;
	}

	/**
	 * @return the search_tj_xjzt
	 */
	public String[] getSearch_tj_xjzt() {
		return search_tj_xjzt;
	}

	/**
	 * @param searchTjXjzt要设置的
	 *            search_tj_xjzt
	 */
	public void setSearch_tj_xjzt(String[] searchTjXjzt) {
		search_tj_xjzt = searchTjXjzt;
	}

	/**
	 * @return the search_tj_qjlxdm
	 */
	public String[] getSearch_tj_qjlxdm() {
		return search_tj_qjlxdm;
	}

	/**
	 * @param searchTjQjlxdm要设置的
	 *            search_tj_qjlxdm
	 */
	public void setSearch_tj_qjlxdm(String[] searchTjQjlxdm) {
		search_tj_qjlxdm = searchTjQjlxdm;
	}

	/**
	 * @return the search_tj_gyzgzt
	 */
	public String[] getSearch_tj_gyzgzt() {
		return search_tj_gyzgzt;
	}

	/**
	 * @param searchTjGyzgzt要设置的
	 *            search_tj_gyzgzt
	 */
	public void setSearch_tj_gyzgzt(String[] searchTjGyzgzt) {
		search_tj_gyzgzt = searchTjGyzgzt;
	}

	/**
	 * @return the search_tj_qjzt
	 */
	public String[] getSearch_tj_qjzt() {
		return search_tj_qjzt;
	}

	/**
	 * @param searchTjQjzt要设置的
	 *            search_tj_qjzt
	 */
	public void setSearch_tj_qjzt(String[] searchTjQjzt) {
		search_tj_qjzt = searchTjQjzt;
	}

	/**
	 * @return the search_tj_shztx
	 */
	public String[] getSearch_tj_shztx() {
		return search_tj_shztx;
	}

	/**
	 * @param searchTjShztx要设置的
	 *            search_tj_shztx
	 */
	public void setSearch_tj_shztx(String[] searchTjShztx) {
		search_tj_shztx = searchTjShztx;
	}

	public String[] getSearch_tj_shztxbjpy() {
		return search_tj_shztxbjpy;
	}

	public void setSearch_tj_shztxbjpy(String[] searchTjShztxbjpy) {
		search_tj_shztxbjpy = searchTjShztxbjpy;
	}
		public String[] getSearch_tj_shztbjpyjg() {
		return search_tj_shztbjpyjg;
	}

	public void setSearch_tj_shztbjpyjg(String[] searchTjShztbjpyjg) {
		search_tj_shztbjpyjg = searchTjShztbjpyjg;
	}
	/**
	 * @return the search_tj_sfbbhcyhk
	 */
	public String[] getSearch_tj_sfbbhcyhk() {
		return search_tj_sfbbhcyhk;
	}

	/**
	 * @return the search_tj_zczt
	 */
	public String[] getSearch_tj_zczt() {
		return search_tj_zczt;
	}

	/**
	 * @param searchTjSfbbhcyhk要设置的
	 *            search_tj_sfbbhcyhk
	 */
	public void setSearch_tj_sfbbhcyhk(String[] searchTjSfbbhcyhk) {
		search_tj_sfbbhcyhk = searchTjSfbbhcyhk;
	}

	/**
	 * @param searchTjZczt要设置的
	 *            search_tj_zczt
	 */
	public void setSearch_tj_zczt(String[] searchTjZczt) {
		search_tj_zczt = searchTjZczt;
	}

	/**
	 * @return the search_tj_xszbblx
	 */
	public String[] getSearch_tj_xszbblx() {
		return search_tj_xszbblx;
	}

	/**
	 * @param searchTjXszbblx要设置的
	 *            search_tj_xszbblx
	 */
	public void setSearch_tj_xszbblx(String[] searchTjXszbblx) {
		search_tj_xszbblx = searchTjXszbblx;
	}

	/**
	 * @return the search_tj_txzt
	 */
	public String[] getSearch_tj_txzt() {
		return search_tj_txzt;
	}

	/**
	 * @param searchTjTxzt要设置的
	 *            search_tj_txzt
	 */
	public void setSearch_tj_txzt(String[] searchTjTxzt) {
		search_tj_txzt = searchTjTxzt;
	}

	public String[] getSearch_tj_njNew() {
		return search_tj_njNew;
	}

	public void setSearch_tj_njNew(String[] searchTjNjNew) {
		search_tj_njNew = searchTjNjNew;
	}

	public String[] getSearch_tj_xyNew() {
		return search_tj_xyNew;
	}

	public void setSearch_tj_xyNew(String[] searchTjXyNew) {
		search_tj_xyNew = searchTjXyNew;
	}

	public String[] getSearch_tj_zyNew() {
		return search_tj_zyNew;
	}

	public void setSearch_tj_zyNew(String[] searchTjZyNew) {
		search_tj_zyNew = searchTjZyNew;
	}

	public String[] getSearch_tj_bjNew() {
		return search_tj_bjNew;
	}

	public void setSearch_tj_bjNew(String[] searchTjBjNew) {
		search_tj_bjNew = searchTjBjNew;
	}

	public String[] getSearch_tj_bmNew() {
		return search_tj_bmNew;
	}

	public void setSearch_tj_bmNew(String[] searchTjBmNew) {
		search_tj_bmNew = searchTjBmNew;
	}

	/**
	 * @return the search_tj_rwfs
	 */
	public String[] getSearch_tj_rwfs() {
		return search_tj_rwfs;
	}

	/**
	 * @param searchTjRwfs要设置的
	 *            search_tj_rwfs
	 */
	public void setSearch_tj_rwfs(String[] searchTjRwfs) {
		search_tj_rwfs = searchTjRwfs;
	}

	/**
	 * @return the search_tj_cbzkmc
	 */
	public String[] getSearch_tj_cbzkmc() {
		return search_tj_cbzkmc;
	}

	/**
	 * @param searchTjCbzkmc要设置的
	 *            search_tj_cbzkmc
	 */
	public void setSearch_tj_cbzkmc(String[] searchTjCbzkmc) {
		search_tj_cbzkmc = searchTjCbzkmc;
	}

	/**
	 * @return the search_tj_xhqk
	 */
	public String[] getSearch_tj_xhqk() {
		return search_tj_xhqk;
	}

	/**
	 * @return the search_tj_gyyjfl
	 */
	public String[] getSearch_tj_gyyjfl() {
		return search_tj_gyyjfl;
	}

	/**
	 * @param searchTjXhqk要设置的
	 *            search_tj_xhqk
	 */
	public void setSearch_tj_xhqk(String[] searchTjXhqk) {
		search_tj_xhqk = searchTjXhqk;
	}

	/**
	 * @return the search_tj_fbqk
	 */
	public String[] getSearch_tj_fbqk() {
		return search_tj_fbqk;
	}

	/**
	 * @param searchTjFbqk要设置的
	 *            search_tj_fbqk
	 */
	public void setSearch_tj_fbqk(String[] searchTjFbqk) {
		search_tj_fbqk = searchTjFbqk;
	}

	/**
	 * @return the search_tj_syzt
	 */
	public String[] getSearch_tj_syzt() {
		return search_tj_syzt;
	}

	/**
	 * @param searchTjSyzt要设置的
	 *            search_tj_syzt
	 */
	public void setSearch_tj_syzt(String[] searchTjSyzt) {
		search_tj_syzt = searchTjSyzt;
	}

	/**
	 * @return the search_tj_sfnz
	 */
	public String[] getSearch_tj_sfnz() {
		return search_tj_sfnz;
	}

	/**
	 * @param searchTjSfnz要设置的
	 *            search_tj_sfnz
	 */
	public void setSearch_tj_sfnz(String[] searchTjSfnz) {
		search_tj_sfnz = searchTjSfnz;
	}

	/**
	 * @return the search_tj_gzlx
	 */
	public String[] getSearch_tj_gzlx() {
		return search_tj_gzlx;
	}

	/**
	 * @param searchTjGzlx要设置的
	 *            search_tj_gzlx
	 */
	public void setSearch_tj_gzlx(String[] searchTjGzlx) {
		search_tj_gzlx = searchTjGzlx;
	}

	/**
	 * @param searchTjGyyjfl要设置的
	 *            search_tj_gyyjfl
	 */
	public void setSearch_tj_gyyjfl(String[] searchTjGyyjfl) {
		search_tj_gyyjfl = searchTjGyyjfl;
	}

	/**
	 * @return the search_tj_gyyjxfkqk
	 */
	public String[] getSearch_tj_gyyjxfkqk() {
		return search_tj_gyyjxfkqk;
	}

	/**
	 * @param searchTjGyyjxfkqk要设置的
	 *            search_tj_gyyjxfkqk
	 */
	public void setSearch_tj_gyyjxfkqk(String[] searchTjGyyjxfkqk) {
		search_tj_gyyjxfkqk = searchTjGyyjxfkqk;
	}

	public String[] getSearch_tj_fyffXm() {
		return search_tj_fyffXm;
	}

	public void setSearch_tj_fyffXm(String[] searchTjFyffXm) {
		search_tj_fyffXm = searchTjFyffXm;
	}

	/**
	 * @return the search_tj_zxsstatus
	 */
	public String[] getSearch_tj_zxsstatus() {
		return search_tj_zxsstatus;
	}

	/**
	 * @param search_tj_zxsstatus要设置的 search_tj_zxsstatus
	 */
	public void setSearch_tj_zxsstatus(String[] search_tj_zxsstatus) {
		this.search_tj_zxsstatus = search_tj_zxsstatus;
	}

	/**
	 * @return the search_tj_zxszg
	 */
	public String[] getSearch_tj_zxszg() {
		return search_tj_zxszg;
	}

	/**
	 * @param search_tj_zxszg要设置的 search_tj_zxszg
	 */
	public void setSearch_tj_zxszg(String[] search_tj_zxszg) {
		this.search_tj_zxszg = search_tj_zxszg;
	}

	/**
	 * @return the search_tj_yyzt
	 */
	public String[] getSearch_tj_yyzt() {
		return search_tj_yyzt;
	}

	/**
	 * @param search_tj_yyzt要设置的 search_tj_yyzt
	 */
	public void setSearch_tj_yyzt(String[] search_tj_yyzt) {
		this.search_tj_yyzt = search_tj_yyzt;
	}

	/**
	 * @return the search_tj_zxzt
	 */
	public String[] getSearch_tj_zxzt() {
		return search_tj_zxzt;
	}

	/**
	 * @param search_tj_zxzt要设置的 search_tj_zxzt
	 */
	public void setSearch_tj_zxzt(String[] search_tj_zxzt) {
		this.search_tj_zxzt = search_tj_zxzt;
	}

	/**
	 * @return the search_tj_jxmc
	 */
	public String[] getSearch_tj_jxmc() {
		return search_tj_jxmc;
	}

	/**
	 * @param searchTjJxmc要设置的
	 *            search_tj_jxmc
	 */
	public void setSearch_tj_jxmc(String[] searchTjJxmc) {
		search_tj_jxmc = searchTjJxmc;
	}

	/**
	 * @return the search_tj_pdxn
	 */
	public String[] getSearch_tj_pdxn() {
		return search_tj_pdxn;
	}

	/**
	 * @param searchTjPdxn要设置的
	 *            search_tj_pdxn
	 */
	public void setSearch_tj_pdxn(String[] searchTjPdxn) {
		search_tj_pdxn = searchTjPdxn;
	}

	/**
	 * @return the search_tj_pdxq
	 */
	public String[] getSearch_tj_pdxq() {
		return search_tj_pdxq;
	}

	/**
	 * @param searchTjPdxq要设置的
	 *            search_tj_pdxq
	 */
	public void setSearch_tj_pdxq(String[] searchTjPdxq) {
		search_tj_pdxq = searchTjPdxq;
	}

	/**
	 * @return the search_tj_yw
	 */
	public String[] getSearch_tj_yw() {
		return search_tj_yw;
	}

	/**
	 * @param searchTjYw要设置的 search_tj_yw
	 */
	public void setSearch_tj_yw(String[] searchTjYw) {
		search_tj_yw = searchTjYw;
	}

	/**
	 * @return the search_tj_zblx
	 */
	public String[] getSearch_tj_zblx() {
		return search_tj_zblx;
	}

	/**
	 * @param searchTjZblx要设置的 search_tj_zblx
	 */
	public void setSearch_tj_zblx(String[] searchTjZblx) {
		search_tj_zblx = searchTjZblx;
	}

	/**
	 * @return the search_tj_gzdj
	 */
	public String[] getSearch_tj_gzdj() {
		return search_tj_gzdj;
	}

	/**
	 * @param searchTjGzdj要设置的 search_tj_gzdj
	 */
	public void setSearch_tj_gzdj(String[] searchTjGzdj) {
		search_tj_gzdj = searchTjGzdj;
	}

	/**
	 * @return the search_tj_xlwtlx
	 */
	public String[] getSearch_tj_xlwtlx() {
		return search_tj_xlwtlx;
	}

	/**
	 * @param searchTjXlwtlx要设置的 search_tj_xlwtlx
	 */
	public void setSearch_tj_xlwtlx(String[] searchTjXlwtlx) {
		search_tj_xlwtlx = searchTjXlwtlx;
	}
	
	/**
	 * @return the search_tj_sfpkx
	 */
	public String[] getSearch_tj_sfpkx() {
		return search_tj_sfpkx;
	}

	/**
	 * @param searchTjSfpkx要设置的 search_tj_sfpkx
	 */
	public void setSearch_tj_sfpkx(String[] searchTjSfpkx) {
		search_tj_sfpkx = searchTjSfpkx;
	}

	public String[] getSearch_tj_kqlx() {
		return search_tj_kqlx;
	}

	public void setSearch_tj_kqlx(String[] searchTjKqlx) {
		search_tj_kqlx = searchTjKqlx;
	}
	
	public String[] getSearch_tj_txhdlb() {
		return search_tj_pycc;
	}

	public void setSearch_tj_txhdlb(String[] search_tj_pycc) {
		this.search_tj_pycc = search_tj_pycc;
	}
	
	public String[] getSearch_tj_ydlx() {
		return search_tj_ydlx;
	}

	public void setSearch_tj_ydlx(String[] search_tj_ydlx) {
		this.search_tj_ydlx = search_tj_ydlx;
	}

	public String[] getSearch_tj_ydlxjg() {
		return search_tj_ydlxjg;
	}

	public void setSearch_tj_ydlxjg(String[] search_tj_ydlxjg) {
		this.search_tj_ydlxjg = search_tj_ydlxjg;
	}
	
	public String[] getSearch_tj_lstdLy() {
		return search_tj_lstdLy;
	}

	public void setSearch_tj_lstdLy(String[] searchTjLstdLy) {
		search_tj_lstdLy = searchTjLstdLy;
	}

	/**
	 * @return the search_tj_wplb
	 */
	public String[] getSearch_tj_wplb() {
		return search_tj_wplb;
	}

	/**
	 * @param searchTjWplb要设置的 search_tj_wplb
	 */
	public void setSearch_tj_wplb(String[] searchTjWplb) {
		search_tj_wplb = searchTjWplb;
	}

	/**
	 * @return the search_tj_wpmc
	 */
	public String[] getSearch_tj_wpmc() {
		return search_tj_wpmc;
	}

	/**
	 * @param searchTjWpmc要设置的 search_tj_wpmc
	 */
	public void setSearch_tj_wpmc(String[] searchTjWpmc) {
		search_tj_wpmc = searchTjWpmc;
	}

	/**
	 * @return the search_tj_ghzt
	 */
	public String[] getSearch_tj_ghzt() {
		return search_tj_ghzt;
	}

	/**
	 * @param search_tj_ghzt要设置的 search_tj_ghzt
	 */
	public void setSearch_tj_ghzt(String[] search_tj_ghzt) {
		this.search_tj_ghzt = search_tj_ghzt;
	}

	/**
	 * @return the search_tj_cclx
	 */
	public String[] getSearch_tj_cclx() {
		return search_tj_cclx;
	}

	/**
	 * @param searchTjCclx要设置的 search_tj_cclx
	 */
	public void setSearch_tj_cclx(String[] searchTjCclx) {
		search_tj_cclx = searchTjCclx;
	}

	/**
	 * @return the search_tj_qqlx
	 */
	public String[] getSearch_tj_qqlx() {
		return search_tj_qqlx;
	}

	/**
	 * @param searchTjQqlx要设置的 search_tj_qqlx
	 */
	public void setSearch_tj_qqlx(String[] searchTjQqlx) {
		search_tj_qqlx = searchTjQqlx;
	}

	public String[] getSearch_tj_hjxz() {
		return search_tj_hjxz;
	}

	public void setSearch_tj_hjxz(String[] searchTjHjxz) {
		search_tj_hjxz = searchTjHjxz;
	}
	//浙江警察大队维护高级查询条件---start
	public String[] getSearch_tj_zjjcDd() {
		return search_tj_zjjcDd;
	}

	public void setSearch_tj_zjjcDd(String[] searchTjZjjcDd) {
		search_tj_zjjcDd = searchTjZjjcDd;
	}
	//浙江警察大队维护高级查询条件---end



	//浙江商业考勤周次高级查询条件---beign
	public String[] getSearch_tj_zjsyzc() {
		return search_tj_zjsyzc;
	}

	public void setSearch_tj_zjsyzc(String[] searchTjZjsyzc) {
		search_tj_zjsyzc = searchTjZjsyzc;
	}

	/**
	 * @return the search_tj_jxkqlx
	 */
	public String[] getSearch_tj_jxkqlx() {
		return search_tj_jxkqlx;
	}

	/**
	 * @param searchTjJxkqlx要设置的 search_tj_jxkqlx
	 */
	public void setSearch_tj_jxkqlx(String[] searchTjJxkqlx) {
		search_tj_jxkqlx = searchTjJxkqlx;
	}

	/**
	 * @return the search_tj_jxkqlb
	 */
	public String[] getSearch_tj_jxkqlb() {
		return search_tj_jxkqlb;
	}

	/**
	 * @param searchTjJxkqlb要设置的 search_tj_jxkqlb
	 */
	public void setSearch_tj_jxkqlb(String[] searchTjJxkqlb) {
		search_tj_jxkqlb = searchTjJxkqlb;
	}

	/**
	 * @return the search_tj_xmjb
	 */
	public String[] getSearch_tj_xmjb() {
		return search_tj_xmjb;
	}

	/**
	 * @param searchTjXmjb要设置的 search_tj_xmjb
	 */
	public void setSearch_tj_xmjb(String[] searchTjXmjb) {
		search_tj_xmjb = searchTjXmjb;
	}

	/**
	 * @return the search_tj_sskm
	 */
	public String[] getSearch_tj_sskm() {
		return search_tj_sskm;
	}

	/**
	 * @param searchTjSskm要设置的 search_tj_sskm
	 */
	public void setSearch_tj_sskm(String[] searchTjSskm) {
		search_tj_sskm = searchTjSskm;
	}

	public String[] getSearch_tj_stlb() {
		return search_tj_stlb;
	}

	public void setSearch_tj_stlb(String[] searchTjStlb) {
		search_tj_stlb = searchTjStlb;
	}

	public String[] getSearch_tj_stxmlb() {
		return search_tj_stxmlb;
	}

	public void setSearch_tj_stxmlb(String[] searchTjStxmlb) {
		search_tj_stxmlb = searchTjStxmlb;
	}
	
	/**
	 * @return the search_tj_stxm
	 */
	public String[] getSearch_tj_stxm() {
		return search_tj_stxm;
	}

	/**
	 * @param searchTjStxm要设置的 search_tj_stxm
	 */
	public void setSearch_tj_stxm(String[] searchTjStxm) {
		search_tj_stxm = searchTjStxm;
	}

	/**
	 * @return the search_tj_qyztmc
	 */
	public String[] getSearch_tj_qyztmc() {
		return search_tj_qyztmc;
	}

	/**
	 * @param searchTjQyztmc要设置的 search_tj_qyztmc
	 */
	public void setSearch_tj_qyztmc(String[] searchTjQyztmc) {
		search_tj_qyztmc = searchTjQyztmc;
	}


	/**
	 * @return the search_tj_ytlb
	 */
	public String[] getSearch_tj_ytlb() {
		return search_tj_ytlb;
	}

	/**
	 * @param searchTjYtlb要设置的 search_tj_ytlb
	 */
	public void setSearch_tj_ytlb(String[] searchTjYtlb) {
		search_tj_ytlb = searchTjYtlb;
	}

	/**
	 * @return the search_tj_xqfl
	 */
	public String[] getSearch_tj_xqfl() {
		return search_tj_xqfl;
	}

	/**
	 * @param searchTjXqfl要设置的 search_tj_xqfl
	 */
	public void setSearch_tj_xqfl(String[] searchTjXqfl) {
		search_tj_xqfl = searchTjXqfl;
	}

	/**
	 * @return the search_tj_xqfl1
	 */
	public String[] getSearch_tj_xqfl1() {
		return search_tj_xqfl1;
	}

	/**
	 * @param searchTjXqfl1要设置的 search_tj_xqfl1
	 */
	public void setSearch_tj_xqfl1(String[] searchTjXqfl1) {
		search_tj_xqfl1 = searchTjXqfl1;
	}


	/**
	 * @return the search_tj_ffzt
	 */
	public String[] getSearch_tj_ffzt() {
		return search_tj_ffzt;
	}

	/**
	 * @param searchTjFfzt要设置的 search_tj_ffzt
	 */
	public void setSearch_tj_ffzt(String[] searchTjFfzt) {
		search_tj_ffzt = searchTjFfzt;
	}

	/**
	 * @return the search_tj_yxzt
	 */
	public String[] getSearch_tj_yxzt() {
		return search_tj_yxzt;
	}

	/**
	 * @param searchTjYxzt要设置的 search_tj_yxzt
	 */
	public void setSearch_tj_yxzt(String[] searchTjYxzt) {
		search_tj_yxzt = searchTjYxzt;
	}

	/**
	 * @return the search_tj_qsz
	 */
	public String[] getSearch_tj_qsz() {
		return search_tj_qsz;
	}

	/**
	 * @param searchTjQsz要设置的 search_tj_qsz
	 */
	public void setSearch_tj_qsz(String[] searchTjQsz) {
		search_tj_qsz = searchTjQsz;
	}

	
    private String[] search_tj_yqmc = null;
	private String[] search_tj_hkxz = null;
	/**
	 * @return the search_tj_hkxz
	 */
	public String[] getSearch_tj_hkxz() {
		return search_tj_hkxz;
	}

	/**
	 * @param searchTjHkxz要设置的 search_tj_hkxz
	 */
	public void setSearch_tj_hkxz(String[] searchTjHkxz) {
		search_tj_hkxz = searchTjHkxz;
	}
	/**
	 * @return the search_tj_yqmc
	 */
	public String[] getSearch_tj_yqmc() {
		return search_tj_yqmc;
	}

	/**
	 * @param searchTjYqmc要设置的 search_tj_yqmc
	 */
	public void setSearch_tj_yqmc(String[] searchTjYqmc) {
		search_tj_yqmc = searchTjYqmc;
	}

	/**
	 * @return the search_tj_ystlb
	 */
	public String[] getSearch_tj_ystlb() {
		return search_tj_ystlb;
	}

	/**
	 * @param searchTjYstlb要设置的 search_tj_ystlb
	 */
	public void setSearch_tj_ystlb(String[] searchTjYstlb) {
		search_tj_ystlb = searchTjYstlb;
	}

	/**
	 * @return the search_tj_ystxmlb
	 */
	public String[] getSearch_tj_ystxmlb() {
		return search_tj_ystxmlb;
	}

	/**
	 * @param searchTjYstxmlb要设置的 search_tj_ystxmlb
	 */
	public void setSearch_tj_ystxmlb(String[] searchTjYstxmlb) {
		search_tj_ystxmlb = searchTjYstxmlb;
	}

	/**
	 * @return the search_tj_ystxmmc
	 */
	public String[] getSearch_tj_ystxmmc() {
		return search_tj_ystxmmc;
	}

	/**
	 * @param searchTjYstxmmc要设置的 search_tj_ystxmmc
	 */
	public void setSearch_tj_ystxmmc(String[] searchTjYstxmmc) {
		search_tj_ystxmmc = searchTjYstxmmc;
	}
	
	/**
	 * @return the search_tj_tnzt
	 */
	public String[] getSearch_tj_tnzt() {
		return search_tj_tnzt;
	}

	/**
	 * @param searchTjTnzt要设置的 search_tj_tnzt
	 */
	public void setSearch_tj_tnzt(String[] searchTjTnzt) {
		search_tj_tnzt = searchTjTnzt;
	}

	/**
	 * @return the search_tj_zxsNj
	 */
	public String[] getSearch_tj_zxsNj() {
		return search_tj_zxsNj;
	}

	/**
	 * @param searchTjZxsNj要设置的 search_tj_zxsNj
	 */
	public void setSearch_tj_zxsNj(String[] searchTjZxsNj) {
		search_tj_zxsNj = searchTjZxsNj;
	}

	/**
	 * @return the search_tj_zxsXyTj
	 */
	public String[] getSearch_tj_zxsXy() {
		return search_tj_zxsXy;
	}

	public String[] getSearch_tj_sfxyyt() {
		return search_tj_sfxyyt;
	}

	public void setSearch_tj_sfxyyt(String[] searchTjSfxyyt) {
		search_tj_sfxyyt = searchTjSfxyyt;
	}

	public String[] getSearch_tj_sfyyt() {
		return search_tj_sfyyt;
	}

	public void setSearch_tj_sfyyt(String[] searchTjSfyyt) {
		search_tj_sfyyt = searchTjSfyyt;
	}

	/**
	 * @param searchTjZxsXyTj要设置的 search_tj_zxsXyTj
	 */
	public void setSearch_tj_zxsXy(String[] searchTjZxsXy) {
		search_tj_zxsXy = searchTjZxsXy;
	}

	/**
	 * @return the search_tj_zxsZyTj
	 */
	public String[] getSearch_tj_zxsZy() {
		return search_tj_zxsZy;
	}

	/**
	 * @param searchTjZxsZyTj要设置的 search_tj_zxsZyTj
	 */
	public void setSearch_tj_zxsZy(String[] searchTjZxsZy) {
		search_tj_zxsZy = searchTjZxsZy;
	}

	/**
	 * @return the search_tj_zxsBjTj
	 */
	public String[] getSearch_tj_zxsBj() {
		return search_tj_zxsBj;
	}

	/**
	 * @param searchTjZxsBjTj要设置的 search_tj_zxsBjTj
	 */
	public void setSearch_tj_zxsBj(String[] searchTjZxsBj) {
		search_tj_zxsBj = searchTjZxsBj;
	}

	/**
	 * @return the search_tj_zxsXzTj
	 */
	public String[] getSearch_tj_zxsXz() {
		return search_tj_zxsXz;
	}

	/**
	 * @param searchTjZxsXzTj要设置的 search_tj_zxsXzTj
	 */
	public void setSearch_tj_zxsXz(String[] searchTjZxsXz) {
		search_tj_zxsXz = searchTjZxsXz;
	}

	/**
	 * @return the search_tj_zxsXjlbTj
	 */
	public String[] getSearch_tj_zxsXjlb() {
		return search_tj_zxsXjlb;
	}

	/**
	 * @param searchTjZxsXjlbTj要设置的 search_tj_zxsXjlbTj
	 */
	public void setSearch_tj_zxsXjlb(String[] searchTjZxsXjlb) {
		search_tj_zxsXjlb = searchTjZxsXjlb;
	}

	/**
	 * @return the search_tj_fzxsNj
	 */
	public String[] getSearch_tj_fzxsNj() {
		return search_tj_fzxsNj;
	}

	/**
	 * @param searchTjFzxsNj要设置的 search_tj_fzxsNj
	 */
	public void setSearch_tj_fzxsNj(String[] searchTjFzxsNj) {
		search_tj_fzxsNj = searchTjFzxsNj;
	}

	/**
	 * @return the search_tj_fzxsXyTj
	 */
	public String[] getSearch_tj_fzxsXy() {
		return search_tj_fzxsXy;
	}

	/**
	 * @param searchTjFzxsXyTj要设置的 search_tj_fzxsXyTj
	 */
	public void setSearch_tj_fzxsXy(String[] searchTjFzxsXy) {
		search_tj_fzxsXy = searchTjFzxsXy;
	}

	/**
	 * @return the search_tj_fzxsZyTj
	 */
	public String[] getSearch_tj_fzxsZy() {
		return search_tj_fzxsZy;
	}

	/**
	 * @param searchTjFzxsZyTj要设置的 search_tj_fzxsZyTj
	 */
	public void setSearch_tj_fzxsZy(String[] searchTjFzxsZy) {
		search_tj_fzxsZy = searchTjFzxsZy;
	}

	/**
	 * @return the search_tj_fzxsBjTj
	 */
	public String[] getSearch_tj_fzxsBj() {
		return search_tj_fzxsBj;
	}

	/**
	 * @param searchTjFzxsBjTj要设置的 search_tj_fzxsBjTj
	 */
	public void setSearch_tj_fzxsBj(String[] searchTjFzxsBj) {
		search_tj_fzxsBj = searchTjFzxsBj;
	}

	/**
	 * @return the search_tj_fzxsXzTj
	 */
	public String[] getSearch_tj_fzxsXz() {
		return search_tj_fzxsXz;
	}

	/**
	 * @param searchTjFzxsXzTj要设置的 search_tj_fzxsXzTj
	 */
	public void setSearch_tj_fzxsXz(String[] searchTjFzxsXz) {
		search_tj_fzxsXz = searchTjFzxsXz;
	}

	/**
	 * @return the search_tj_fzxsXjlbTj
	 */
	public String[] getSearch_tj_fzxsXjlb() {
		return search_tj_fzxsXjlb;
	}

	/**
	 * @param searchTjFzxsXjlbTj要设置的 search_tj_fzxsXjlbTj
	 */
	public void setSearch_tj_fzxsXjlb(String[] searchTjFzxsXjlb) {
		search_tj_fzxsXjlb = searchTjFzxsXjlb;
	}

	/**
	 * @return the search_tj_zjcmtjzt
	 */
	public String[] getSearch_tj_zjcmtjzt() {
		return search_tj_zjcmtjzt;
	}

	/**
	 * @param searchTjZjcmtjzt要设置的 search_tj_zjcmtjzt
	 */
	public void setSearch_tj_zjcmtjzt(String[] searchTjZjcmtjzt) {
		search_tj_zjcmtjzt = searchTjZjcmtjzt;
	}
	

	
	

   //====================假期返校状态start===================================
	private String[] search_tj_fxzt = null;// 返校状态
	
	/**
	 * @return the search_tj_fxzt
	 */
	public String[] getSearch_tj_fxzt() {
		return search_tj_fxzt;
	}
	
	/**
	 * @param searchTjFxzt要设置的 search_tj_fxzt
	 */
	public void setSearch_tj_fxzt(String[] searchTjFxzt) {
		search_tj_fxzt = searchTjFxzt;
	}

	public String getPlcx_xh() {
		return plcx_xh;
	}

	public void setPlcx_xh(String plcxXh) {
		plcx_xh = plcxXh;
	}

	public String getPlcx_xm() {
		return plcx_xm;
	}

	public void setPlcx_xm(String plcxXm) {
		plcx_xm = plcxXm;
	}
	
	//====================假期返校状态end===================================
	
	/**
	 * @return the search_tj_hdpl
	 */
	public String[] getSearch_tj_hdpl() {
		return search_tj_hdpl;
	}
	
	

	/**
	 * @param searchTjHdpl要设置的 search_tj_hdpl
	 */
	public void setSearch_tj_hdpl(String[] searchTjHdpl) {
		search_tj_hdpl = searchTjHdpl;
	}
	
	/**
	 * @return the search_tj_yyzc
	 */
	public String[] getSearch_tj_yyzc() {
		return search_tj_yyzc;
	}

	/**
	 * @param searchTjYyzc要设置的 search_tj_yyzc
	 */
	public void setSearch_tj_yyzc(String[] searchTjYyzc) {
		search_tj_yyzc = searchTjYyzc;
	}
	
	public String[] getSearch_tj_jxlb() {
		return search_tj_jxlb;
	}

	public void setSearch_tj_jxlb(String[] searchTjJxlb) {
		search_tj_jxlb = searchTjJxlb;
	}

	public String[] getSearch_tj_jsfs() {
		return search_tj_jsfs;
	}

	public void setSearch_tj_jsfs(String[] searchTjJsfs) {
		search_tj_jsfs = searchTjJsfs;
	}

	public String[] getSearch_tj_jxdj() {
		return search_tj_jxdj;
	}

	public void setSearch_tj_jxdj(String[] searchTjJxdj) {
		search_tj_jxdj = searchTjJxdj;
	}

	public String[] getSearch_tj_hjjxmc() {
		return search_tj_hjjxmc;
	}

	public void setSearch_tj_hjjxmc(String[] searchTjHjjxmc) {
		search_tj_hjjxmc = searchTjHjjxmc;
	}

	public String[] getSearch_tj_hdlx() {
		return search_tj_hdlx;
	}

	public void setSearch_tj_hdlx(String[] search_tj_hdlx) {
		this.search_tj_hdlx = search_tj_hdlx;
	}

	/**
	 * @return the search_tj_kg
	 */
	public String[] getSearch_tj_kg() {
		return search_tj_kg;
	}

	/**
	 * @param search_tj_kg要设置的 search_tj_kg
	 */
	public void setSearch_tj_kg(String[] search_tj_kg) {
		this.search_tj_kg = search_tj_kg;
	}

	/**
	 * @return the search_tj_czlx
	 */
	public String[] getSearch_tj_czlx() {
		return search_tj_czlx;
	}

	/**
	 * @param search_tj_czlx要设置的 search_tj_czlx
	 */
	public void setSearch_tj_czlx(String[] search_tj_czlx) {
		this.search_tj_czlx = search_tj_czlx;
	}

	/**
	 * @return the search_tj_jxsqzd
	 */
	public String[] getSearch_tj_jxsqzd() {
		return search_tj_jxsqzd;
	}

	/**
	 * @param searchTjJxsqzd要设置的 search_tj_jxsqzd
	 */
	public void setSearch_tj_jxsqzd(String[] searchTjJxsqzd) {
		search_tj_jxsqzd = searchTjJxsqzd;
	}

	/**
	 * @return the search_tj_jtgj
	 */
	public String[] getSearch_tj_jtgj() {
		return search_tj_jtgj;
	}

	/**
	 * @param searchTjJtgj要设置的 search_tj_jtgj
	 */
	public void setSearch_tj_jtgj(String[] searchTjJtgj) {
		search_tj_jtgj = searchTjJtgj;
	}

	/**
	 * @return the search_tj_fwjg
	 */
	public String[] getSearch_tj_fwjg() {
		return search_tj_fwjg;
	}

	/**
	 * @param searchTjFwjg要设置的 search_tj_fwjg
	 */
	public void setSearch_tj_fwjg(String[] searchTjFwjg) {
		search_tj_fwjg = searchTjFwjg;
	}

	/**
	 * @return the search_tj_knlx
	 */
	public String[] getSearch_tj_knlx() {
		return search_tj_knlx;
	}

	/**
	 * @param searchTjKnlx要设置的 search_tj_knlx
	 */
	public void setSearch_tj_knlx(String[] searchTjKnlx) {
		search_tj_knlx = searchTjKnlx;
	}
	
	/**
	 * @return the search_tj_kslb
	 */
	public String[] getSearch_tj_kslb() {
		return search_tj_kslb;
	}

	/**
	 * @param searchTjKslb要设置的 search_tj_kslb
	 */
	public void setSearch_tj_kslb(String[] searchTjKslb) {
		search_tj_kslb = searchTjKslb;
	}

	/**
	 * @return the search_tj_fwlx
	 */
	public String[] getSearch_tj_fwlx() {
		return search_tj_fwlx;
	}

	/**
	 * @param searchTjFwlx要设置的 search_tj_fwlx
	 */
	public void setSearch_tj_fwlx(String[] searchTjFwlx) {
		search_tj_fwlx = searchTjFwlx;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-7 上午11:04:05 
	 * @return		: the search_tj_lnjdhk
	 */
	public String[] getSearch_tj_lnjdhk() {
		return search_tj_lnjdhk;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-7 上午11:04:05 
	 * @param 		：searchTjLnjdhk the search_tj_lnjdhk to set
	 */
	public void setSearch_tj_lnjdhk(String[] searchTjLnjdhk) {
		search_tj_lnjdhk = searchTjLnjdhk;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-12 上午09:56:20 
	 * @return		: the search_tj_bxxs
	 */
	public String[] getSearch_tj_bxxs() {
		return search_tj_bxxs;
	}

	public void setSearch_tj_bxxs(String[] searchTjBxxs) {
		search_tj_bxxs = searchTjBxxs;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-12 下午01:54:39 
	 * @return		: the search_tj_zjxy
	 */
	public String[] getSearch_tj_zjxy() {
		return search_tj_zjxy;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-12 下午01:54:39 
	 * @param 		：searchTjZjxy the search_tj_zjxy to set
	 */
	public void setSearch_tj_zjxy(String[] searchTjZjxy) {
		search_tj_zjxy = searchTjZjxy;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-8 上午11:33:49 
	 * @return		: the search_tj_gfqkfl
	 */
	public String[] getSearch_tj_gfqkfl() {
		return search_tj_gfqkfl;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-8 上午11:33:49 
	 * @param 		：searchTjGfqkfl the search_tj_gfqkfl to set
	 */
	public void setSearch_tj_gfqkfl(String[] searchTjGfqkfl) {
		search_tj_gfqkfl = searchTjGfqkfl;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-11 上午11:51:10 
	 * @return		: the search_tj_sfgcj
	 */
	public String[] getSearch_tj_sfgcj() {
		return search_tj_sfgcj;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-11 上午11:51:10 
	 * @param 		：searchTjSfgcj the search_tj_sfgcj to set
	 */
	public void setSearch_tj_sfgcj(String[] searchTjSfgcj) {
		search_tj_sfgcj = searchTjSfgcj;
	}

	public String[] getSearch_tj_hdxs() {
		return search_tj_hdxs;
	}

	public void setSearch_tj_hdxs(String[] searchTjHdxs) {
		search_tj_hdxs = searchTjHdxs;
	}

	public String[] getSearch_tj_yjyy() {
		return search_tj_yjyy;
	}

	public void setSearch_tj_yjyy(String[] searchTjYjyy) {
		this.search_tj_yjyy = searchTjYjyy;
	}

	public String[] getSearch_tj_fdjslx() {
		return search_tj_fdjslx;
	}

	public void setSearch_tj_fdjslx(String[] searchTjFdjslx) {
		this.search_tj_fdjslx = searchTjFdjslx;
	}

	public String[] getSearch_tj_alzt() {
		return search_tj_alzt;
	}

	public void setSearch_tj_alzt(String[] searchTjAlzt) {
		this.search_tj_alzt = searchTjAlzt;
	}

	public String[] getSearch_tj_aljb() {
		return search_tj_aljb;
	}

	public void setSearch_tj_aljb(String[] searchTjAljb) {
		this.search_tj_aljb = searchTjAljb;
	}

	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-24 上午11:55:44 
	 * @return		: the search_tj_hdzl
	 */
	public String[] getSearch_tj_hdzl() {
		return search_tj_hdzl;
	}

	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-24 上午11:55:44 
	 * @param 		：searchTjHdzl the search_tj_hdzl to set
	 */
	public void setSearch_tj_hdzl(String[] searchTjHdzl) {
		search_tj_hdzl = searchTjHdzl;
	}

	public String[] getSearch_tj_hdjxzt() {
		return search_tj_hdjxzt;
	}

	public void setSearch_tj_hdjxzt(String[] search_tj_hdjxzt) {
		this.search_tj_hdjxzt = search_tj_hdjxzt;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-29 下午06:01:26 
	 * @return		: the search_tj_sy
	 */
	public String[] getSearch_tj_sy() {
		return search_tj_sy;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-29 下午06:01:26 
	 * @param 		：searchTjSy the search_tj_sy to set
	 */
	public void setSearch_tj_sy(String[] searchTjSy) {
		search_tj_sy = searchTjSy;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-9 下午05:05:18 
	 * @return		: the search_tj_stzt
	 */
	public String[] getSearch_tj_stzt() {
		return search_tj_stzt;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-9 下午05:05:18 
	 * @param 		：searchTjStzt the search_tj_stzt to set
	 */
	public void setSearch_tj_stzt(String[] searchTjStzt) {
		search_tj_stzt = searchTjStzt;
	}

	/**
	 * tanhao1693
	 * @return
	 */
	public String[] getSearch_tj_sfgk() {
		return search_tj_sfgk;
	}

	public void setSearch_tj_sfgk(String[] search_tj_sfgk) {
		this.search_tj_sfgk = search_tj_sfgk;
	}

	public String[] getSearch_tj_sfxn() {
		return search_tj_sfxn;
	}

	public void setSearch_tj_sfxn(String[] search_tj_sfxn) {
		this.search_tj_sfxn = search_tj_sfxn;
	}
}


