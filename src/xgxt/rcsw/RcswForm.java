package xgxt.rcsw;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

public class RcswForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* 通用 */
	SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();

	Pages pages = new Pages();

	FormFile uploadFile;// 上传文件
	
	private String shjg;//审核结果
	
	private String sqkssj;//申请开始时间
	
	private String sqjssj;//申请结束时间
	
	private String shzt;//审核状态
	
	private String shyj;//审核意见
	
	private String shsj;//审核时间
	
	private String shr;//审核人
	
	private String shgw;//审核岗位 
	
	private String bblxdm;//补办类型代码

	private String[] checkVal;// 批处理
	
	private String[] xhzgh;// 学号职工号
	
	private String[] primarykey_checkVal;

	private String xh;// 学号

	private String querylike_xh;

	private String xm;// 姓名

	private String querylike_xm;

	private String xb;// 性别

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

	private String id;// ID

	private String pkValue;// 主键值

	private String bmdm;// 部门代码

	private String zgh;// '职工号';

	private String zw;// '职位';

	// ----------学生留言-------------
	private String lymc;// '留言名称';

	private String lylx;// '留言类型';

	private String lysj;// '留言时间';

	private String lynr;// '留言内容';

	private String lyr;// '留言人';

	// ----------老师回复-------------

	private String hfr;// '回复人';

	private String hfsj;// '回复时间';

	private String hfnr;// '回复内容';

	private String hfpj;// '回复评价';

	private String hfls;// '回复楼数';

	private String bjlyxx;// '编辑留言信息';

	private String czsj;// 置顶处置时间;

	// ----------实物发放-------------
	private String xmlx;// '项目类型';

	private String queryequals_xmlx;// '项目类型';

	private String[] ffrq;// '发放人群';

	private String ffsj;// '发放时间';

	private String querygreaterequal_ffsj;

	private String querylessequal_ffsj;
	
	private String ffbz;// '发放备注';

	private String ffr ;//'发放人';
	
	private String isff ;//'是否发放';
	
	private String lydx;//留言对象
	
	private String save_xxsh;
	private String save_xxshyj;
	private String save_xxshsj;
	private String querygreaterequal_sqsj;	     //申请时间
	private String querylessequal_sqsj;          //申请时间
	private String queryequals_bblxdm;           //补办类型代码
	private String querylike_ccqj;               //乘车区间
	private String querygreaterequal_djsj;       //登记时间
	private String querylessequal_djsj;          //登记时间
	private String save_ccqj;
	private String save_djsj;
	
	private String kssj;		//开始时间
							
	private String jssj;		//结束时间
	/*车次查询*/
	private String save_cc;//车次
	
	private String save_dqzt;//当前状态
	
	private String save_qdz;//起点站
	
	private String save_zdz;//终点站
	
	private String save_kcsj;//开车时间
	
	private String save_ddsj;//到达时间
	
	private String save_yxsj;//运行时间
	
	private String save_pj;//票价
	
	private String save_tkz;//停靠站
	
	private String queryequals_cc;//车次
	
	private String queryequals_qdz;//起点站
	
	private String queryequals_zdz;//终点站
	
	private String querylike_tkz;
	
	private String mycd;//满意程度
	
	private String pjyj;//评价意见
	
	private String save_mycd;
	
	private String save_pjyj;
	
	private String tableName;
	
	private String viewName;
	
	private String []stuInfo;
	
	private String show_xn;
	
	private String show_xq;
	
	private String show_nd;
	
	private String show_xmlx;
	
	private String show_ffsj;
	
	private String sfff;
	
	private String lqtz;
	
	private String queryequals_lqtz;
	
	private String save_lqtz;

	private String save_xn;
	
	private String save_xmlx;
	
	private String save_xq;
	
	private String save_ffsj;
	
	private String save_nd;
	
	private String save_ffdd;
	
	private String save_bz;
	
	private String save_ffrq;
	
	private String queryequals_sfqd;
	
	private String querylike_xhzgh;
	
	private String sfqd;
	
	private String userName;
	
	private String queryequals_lqcs;
	
	private String queryequals_ffcs;
	
	private String cxfw;
	
	private String xmdm;
	
	private String xmmc;
	
	private String save_xmmc;
	
	private String save_xmdm;
	
	private String save_ffjssj;
	
	private String ffjssj;
	
	private String querylike_xmmc;
	
	private String ffjskssj;
	
	private String ffjsjssj;
	
	private String []tzdxxh;
	
	private String dxtzsj;
	
	private String []sftz;
	
	private String sfzh;
	
	private String cxxx;
	
	private String pk;
	
	private String[] pkV;
	
	private User user;
	
	private String sfzc;
	
	private String sfzx;
	
	private String []xydmArr;
	
	private String []xymcArr;
	
	private String []zydmArr;
	
	private String []zymcArr;
	
	private String []bjdmArr;
	
	private String []bjmcArr;
	
	private String []njArr;
	
	private String []xmArr;
	
	private String zxyy;
	
	private String qdz;
	
	private String zdz;
	
	private String select_zxyy;
	
	private String jtdz;
	
	private String select_jbr;
	
	public String getSelect_jbr() {
		return select_jbr;
	}

	public void setSelect_jbr(String select_jbr) {
		this.select_jbr = select_jbr;
	}

	public String getSelect_zxyy() {
		return select_zxyy;
	}

	public void setSelect_zxyy(String select_zxyy) {
		this.select_zxyy = select_zxyy;
	}

	public String getZxyy() {
		return zxyy;
	}

	public void setZxyy(String zxyy) {
		this.zxyy = zxyy;
	}

	public String getSfzc() {
		return sfzc;
	}

	public void setSfzc(String sfzc) {
		this.sfzc = sfzc;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCxxx() {
		return cxxx;
	}

	public void setCxxx(String cxxx) {
		this.cxxx = cxxx;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String[] getSftz() {
		return sftz;
	}

	public void setSftz(String []sftz) {
		this.sftz = sftz;
	}

	public String getDxtzsj() {
		return dxtzsj;
	}

	public void setDxtzsj(String dxtzsj) {
		this.dxtzsj = dxtzsj;
	}

	public String[] getTzdxxh() {
		return tzdxxh;
	}

	public void setTzdxxh(String[] tzdxxh) {
		this.tzdxxh = tzdxxh;
	}

	public String getQuerylike_xmmc() {
		return querylike_xmmc;
	}

	public void setQuerylike_xmmc(String querylike_xmmc) {
		this.querylike_xmmc = querylike_xmmc;
	}

	public String getFfjssj() {
		return ffjssj;
	}

	public void setFfjssj(String ffjssj) {
		this.ffjssj = ffjssj;
	}

	public String getSave_ffjssj() {
		return save_ffjssj;
	}

	public void setSave_ffjssj(String save_ffjssj) {
		this.save_ffjssj = save_ffjssj;
	}

	public String getSave_xmdm() {
		return save_xmdm;
	}

	public void setSave_xmdm(String save_xmdm) {
		this.save_xmdm = save_xmdm;
	}

	public String getSave_xmmc() {
		return save_xmmc;
	}

	public void setSave_xmmc(String save_xmmc) {
		this.save_xmmc = save_xmmc;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getCxfw() {
		return cxfw;
	}

	public void setCxfw(String cxfw) {
		this.cxfw = cxfw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getQueryequals_sfqd() {
		return queryequals_sfqd;
	}

	public void setQueryequals_sfqd(String queryequals_sfqd) {
		this.queryequals_sfqd = queryequals_sfqd;
	}

	public String getSfqd() {
		return sfqd;
	}

	public void setSfqd(String sfqd) {
		this.sfqd = sfqd;
	}

	public String getSave_ffrq() {
		return save_ffrq;
	}

	public void setSave_ffrq(String save_ffrq) {
		this.save_ffrq = save_ffrq;
	}

	public String getLqtz() {
		return lqtz;
	}

	public void setLqtz(String lqtz) {
		this.lqtz = lqtz;
	}

	public String getQueryequals_lqtz() {
		return queryequals_lqtz;
	}

	public void setQueryequals_lqtz(String queryequals_lqtz) {
		this.queryequals_lqtz = queryequals_lqtz;
	}

	public String getSave_lqtz() {
		return save_lqtz;
	}

	public void setSave_lqtz(String save_lqtz) {
		this.save_lqtz = save_lqtz;
	}

	public String getSfff() {
		return sfff;
	}

	public void setSfff(String sfff) {
		this.sfff = sfff;
	}

	public String getShow_ffsj() {
		return show_ffsj;
	}

	public void setShow_ffsj(String show_ffsj) {
		this.show_ffsj = show_ffsj;
	}

	public String getShow_nd() {
		return show_nd;
	}

	public void setShow_nd(String show_nd) {
		this.show_nd = show_nd;
	}

	public String getShow_xmlx() {
		return show_xmlx;
	}

	public void setShow_xmlx(String show_xmlx) {
		this.show_xmlx = show_xmlx;
	}

	public String getShow_xn() {
		return show_xn;
	}

	public void setShow_xn(String show_xn) {
		this.show_xn = show_xn;
	}

	public String getShow_xq() {
		return show_xq;
	}

	public void setShow_xq(String show_xq) {
		this.show_xq = show_xq;
	}

	public String[] getStuInfo() {
		return stuInfo;
	}

	public void setStuInfo(String[] stuInfo) {
		this.stuInfo = stuInfo;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getSave_mycd() {
		return save_mycd;
	}

	public void setSave_mycd(String save_mycd) {
		this.save_mycd = save_mycd;
	}

	public String getSave_pjyj() {
		return save_pjyj;
	}

	public void setSave_pjyj(String save_pjyj) {
		this.save_pjyj = save_pjyj;
	}

	public String getMycd() {
		return mycd;
	}

	public void setMycd(String mycd) {
		this.mycd = mycd;
	}

	public String getPjyj() {
		return pjyj;
	}

	public void setPjyj(String pjyj) {
		this.pjyj = pjyj;
	}

	public String getQueryequals_cc() {
		return queryequals_cc;
	}

	public void setQueryequals_cc(String queryequals_cc) {
		this.queryequals_cc = queryequals_cc;
	}

	public String getQueryequals_qdz() {
		return queryequals_qdz;
	}

	public void setQueryequals_qdz(String queryequals_qdz) {
		this.queryequals_qdz = queryequals_qdz;
	}

	public String getQueryequals_zdz() {
		return queryequals_zdz;
	}

	public void setQueryequals_zdz(String queryequals_zdz) {
		this.queryequals_zdz = queryequals_zdz;
	}

	public String getQuerylike_tkz() {
		return querylike_tkz;
	}

	public void setQuerylike_tkz(String querylike_tkz) {
		this.querylike_tkz = querylike_tkz;
	}

	public String getFfr() {
		return ffr;
	}

	public void setFfr(String ffr) {
		this.ffr = ffr;
	}

	public String getQuerygreaterequal_ffsj() {
		return querygreaterequal_ffsj;
	}

	public void setQuerygreaterequal_ffsj(String querygreaterequal_ffsj) {
		this.querygreaterequal_ffsj = querygreaterequal_ffsj;
	}

	public String getQuerylessequal_ffsj() {
		return querylessequal_ffsj;
	}

	public void setQuerylessequal_ffsj(String querylessequal_ffsj) {
		this.querylessequal_ffsj = querylessequal_ffsj;
	}

	public String getCzsj() {
		return czsj;
	}

	public void setCzsj(String czsj) {
		this.czsj = czsj;
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

	public String getLylx() {
		return lylx;
	}

	public void setLylx(String lylx) {
		this.lylx = lylx;
	}

	public String getLymc() {
		return lymc;
	}

	public void setLymc(String lymc) {
		this.lymc = lymc;
	}

	public String getLynr() {
		return lynr;
	}

	public void setLynr(String lynr) {
		this.lynr = lynr;
	}

	public String getLyr() {
		return lyr;
	}

	public void setLyr(String lyr) {
		this.lyr = lyr;
	}

	public String getLysj() {
		return lysj;
	}

	public void setLysj(String lysj) {
		this.lysj = lysj;
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

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
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

	public String getHfnr() {
		return hfnr;
	}

	public void setHfnr(String hfnr) {
		this.hfnr = hfnr;
	}

	public String getHfpj() {
		return hfpj;
	}

	public void setHfpj(String hfpj) {
		this.hfpj = hfpj;
	}

	public String getHfr() {
		return hfr;
	}

	public void setHfr(String hfr) {
		this.hfr = hfr;
	}

	public String getHfsj() {
		return hfsj;
	}

	public void setHfsj(String hfsj) {
		this.hfsj = hfsj;
	}

	public String getBjlyxx() {
		return bjlyxx;
	}

	public void setBjlyxx(String bjlyxx) {
		this.bjlyxx = bjlyxx;
	}

	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getZw() {
		return zw;
	}

	public void setZw(String zw) {
		this.zw = zw;
	}

	public String getHfls() {
		return hfls;
	}

	public void setHfls(String hfls) {
		this.hfls = hfls;
	}

	public String[] getFfrq() {
		return ffrq;
	}

	public void setFfrq(String[] ffrq) {
		this.ffrq = ffrq;
	}

	public String getFfsj() {
		return ffsj;
	}

	public void setFfsj(String ffsj) {
		this.ffsj = ffsj;
	}

	public String getXmlx() {
		return xmlx;
	}

	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}

	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}

	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
	}

	public String getQueryequals_nj() {
		return queryequals_nj;
	}

	public void setQueryequals_nj(String queryequals_nj) {
		this.queryequals_nj = queryequals_nj;
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

	public String getQueryequals_xmlx() {
		return queryequals_xmlx;
	}

	public void setQueryequals_xmlx(String queryequals_xmlx) {
		this.queryequals_xmlx = queryequals_xmlx;
	}

	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}

	public void setPrimarykey_checkVal(String[] primarykey_checkVal) {
		this.primarykey_checkVal = primarykey_checkVal;
	}

	public String getQueryequals_nd() {
		return queryequals_nd;
	}

	public void setQueryequals_nd(String queryequals_nd) {
		this.queryequals_nd = queryequals_nd;
	}

	public String[] getXhzgh() {
		return xhzgh;
	}

	public void setXhzgh(String[] xhzgh) {
		this.xhzgh = xhzgh;
	}

	public String getFfbz() {
		return ffbz;
	}

	public void setFfbz(String ffbz) {
		this.ffbz = ffbz;
	}

	public String getIsff() {
		return isff;
	}

	public void setIsff(String isff) {
		this.isff = isff;
	}

	public String getSave_xxsh() {
		return save_xxsh;
	}

	public void setSave_xxsh(String save_xxsh) {
		this.save_xxsh = save_xxsh;
	}

	public String getSave_xxshyj() {
		return save_xxshyj;
	}

	public void setSave_xxshyj(String save_xxshyj) {
		this.save_xxshyj = save_xxshyj;
	}

	public String getSave_xxshsj() {
		return save_xxshsj;
	}

	public void setSave_xxshsj(String save_xxshsj) {
		this.save_xxshsj = save_xxshsj;
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

	public String getQueryequals_bblxdm() {
		return queryequals_bblxdm;
	}

	public void setQueryequals_bblxdm(String queryequals_bblxdm) {
		this.queryequals_bblxdm = queryequals_bblxdm;
	}

	public String getQuerylike_ccqj() {
		return querylike_ccqj;
	}

	public void setQuerylike_ccqj(String querylike_ccqj) {
		this.querylike_ccqj = querylike_ccqj;
	}

	public String getQuerygreaterequal_djsj() {
		return querygreaterequal_djsj;
	}

	public void setQuerygreaterequal_djsj(String querygreaterequal_djsj) {
		this.querygreaterequal_djsj = querygreaterequal_djsj;
	}

	public String getQuerylessequal_djsj() {
		return querylessequal_djsj;
	}

	public void setQuerylessequal_djsj(String querylessequal_djsj) {
		this.querylessequal_djsj = querylessequal_djsj;
	}

	public String getSave_ccqj() {
		return save_ccqj;
	}

	public void setSave_ccqj(String save_ccqj) {
		this.save_ccqj = save_ccqj;
	}

	public String getSave_djsj() {
		return save_djsj;
	}

	public void setSave_djsj(String save_djsj) {
		this.save_djsj = save_djsj;
	}

	public String getSave_cc() {
		return save_cc;
	}

	public void setSave_cc(String save_cc) {
		this.save_cc = save_cc;
	}

	public String getSave_ddsj() {
		return save_ddsj;
	}

	public void setSave_ddsj(String save_ddsj) {
		this.save_ddsj = save_ddsj;
	}

	public String getSave_dqzt() {
		return save_dqzt;
	}

	public void setSave_dqzt(String save_dqzt) {
		this.save_dqzt = save_dqzt;
	}

	public String getSave_kcsj() {
		return save_kcsj;
	}

	public void setSave_kcsj(String save_kcsj) {
		this.save_kcsj = save_kcsj;
	}

	public String getSave_pj() {
		return save_pj;
	}

	public void setSave_pj(String save_pj) {
		this.save_pj = save_pj;
	}

	public String getSave_qdz() {
		return save_qdz;
	}

	public void setSave_qdz(String save_qdz) {
		this.save_qdz = save_qdz;
	}

	public String getSave_tkz() {
		return save_tkz;
	}

	public void setSave_tkz(String save_tkz) {
		this.save_tkz = save_tkz;
	}

	public String getSave_yxsj() {
		return save_yxsj;
	}

	public void setSave_yxsj(String save_yxsj) {
		this.save_yxsj = save_yxsj;
	}

	public String getSave_zdz() {
		return save_zdz;
	}

	public void setSave_zdz(String save_zdz) {
		this.save_zdz = save_zdz;
	}

	public String getLydx() {
		return lydx;
	}

	public void setLydx(String lydx) {
		this.lydx = lydx;
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

	public String getSave_bz() {
		return save_bz;
	}

	public void setSave_bz(String save_bz) {
		this.save_bz = save_bz;
	}

	public String getSave_ffsj() {
		return save_ffsj;
	}

	public void setSave_ffsj(String save_ffsj) {
		this.save_ffsj = save_ffsj;
	}

	public String getSave_nd() {
		return save_nd;
	}

	public void setSave_nd(String save_nd) {
		this.save_nd = save_nd;
	}

	public String getSave_xmlx() {
		return save_xmlx;
	}

	public void setSave_xmlx(String save_xmlx) {
		this.save_xmlx = save_xmlx;
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

	public String getSave_ffdd() {
		return save_ffdd;
	}

	public void setSave_ffdd(String save_ffdd) {
		this.save_ffdd = save_ffdd;
	}

	public String getQuerylike_xhzgh() {
		return querylike_xhzgh;
	}

	public void setQuerylike_xhzgh(String querylike_xhzgh) {
		this.querylike_xhzgh = querylike_xhzgh;
	}

	public String getQueryequals_ffcs() {
		return queryequals_ffcs;
	}

	public void setQueryequals_ffcs(String queryequals_ffcs) {
		this.queryequals_ffcs = queryequals_ffcs;
	}

	public String getQueryequals_lqcs() {
		return queryequals_lqcs;
	}

	public void setQueryequals_lqcs(String queryequals_lqcs) {
		this.queryequals_lqcs = queryequals_lqcs;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public String getFfjsjssj() {
		return ffjsjssj;
	}

	public void setFfjsjssj(String ffjsjssj) {
		this.ffjsjssj = ffjsjssj;
	}

	public String getFfjskssj() {
		return ffjskssj;
	}

	public void setFfjskssj(String ffjskssj) {
		this.ffjskssj = ffjskssj;
	}

	public String getBblxdm() {
		return bblxdm;
	}

	public void setBblxdm(String bblxdm) {
		this.bblxdm = bblxdm;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getShgw() {
		return shgw;
	}

	public void setShgw(String shgw) {
		this.shgw = shgw;
	}

	public String getShr() {
		return shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

	public String getShsj() {
		return shsj;
	}

	public void setShsj(String shsj) {
		this.shsj = shsj;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String getSqjssj() {
		return sqjssj;
	}

	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}

	public String getSqkssj() {
		return sqkssj;
	}

	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}

	public String getShjg() {
		return shjg;
	}

	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	public String[] getPkV() {
		return pkV;
	}

	public void setPkV(String[] pkV) {
		this.pkV = pkV;
	}

	public String[] getBjdmArr() {
		return bjdmArr;
	}

	public void setBjdmArr(String[] bjdmArr) {
		this.bjdmArr = bjdmArr;
	}

	public String[] getBjmcArr() {
		return bjmcArr;
	}

	public void setBjmcArr(String[] bjmcArr) {
		this.bjmcArr = bjmcArr;
	}

	public String[] getNjArr() {
		return njArr;
	}

	public void setNjArr(String[] njArr) {
		this.njArr = njArr;
	}

	public String getSfzx() {
		return sfzx;
	}

	public void setSfzx(String sfzx) {
		this.sfzx = sfzx;
	}

	public String[] getXmArr() {
		return xmArr;
	}

	public void setXmArr(String[] xmArr) {
		this.xmArr = xmArr;
	}

	public String[] getXydmArr() {
		return xydmArr;
	}

	public void setXydmArr(String[] xydmArr) {
		this.xydmArr = xydmArr;
	}

	public String[] getXymcArr() {
		return xymcArr;
	}

	public void setXymcArr(String[] xymcArr) {
		this.xymcArr = xymcArr;
	}

	public String[] getZydmArr() {
		return zydmArr;
	}

	public void setZydmArr(String[] zydmArr) {
		this.zydmArr = zydmArr;
	}

	public String[] getZymcArr() {
		return zymcArr;
	}

	public void setZymcArr(String[] zymcArr) {
		this.zymcArr = zymcArr;
	}

	public String getQdz() {
		return qdz;
	}

	public void setQdz(String qdz) {
		this.qdz = qdz;
	}

	public String getZdz() {
		return zdz;
	}

	public void setZdz(String zdz) {
		this.zdz = zdz;
	}

	public String getJtdz() {
		return jtdz;
	}

	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
}
