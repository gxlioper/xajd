package xgxt.rcsw.gzdx;

import org.apache.struts.upload.FormFile;

import xgxt.utils.Pages;

public class RcswGzdxModel { /* 通用 */
	Pages pages = new Pages();

	FormFile uploadFile;// 上传文件

	private String[] checkVal;//批处理
	
	private String xh;// 学号

	private String xm;// 姓名

	private String xb;// 性别

	private String nj;// 年级

	private String xn;// 学年

	private String xq;// 学期

	private String nd;// 年度

	private String xydm;// 学院代码

	private String xymc;// 学院名称

	private String zydm;// 专业代码

	private String zymc;// 专业名称

	private String bjdm;// 班级代码

	private String bjmc;// 班级名称

	private String bz;// 备注

	private String lx;// 类型

	private String id;// ID

	private String pkValue;// 主键值
	
	private String bmdm;//部门代码
	
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

	private String bjlyxx;// '编辑留言信息';

	private String czsj;//置顶处置时间;
	
	private String hfls;// '回复楼数';
	
	private String lydx;//留言对象
	
	public String getLydx() {
		return lydx;
	}

	public void setLydx(String lydx) {
		this.lydx = lydx;
	}

	public String getHfls() {
		return hfls;
	}

	public void setHfls(String hfls) {
		this.hfls = hfls;
	}

	public String getBjlyxx() {
		return bjlyxx;
	}

	public void setBjlyxx(String bjlyxx) {
		this.bjlyxx = bjlyxx;
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

	public String getCzsj() {
		return czsj;
	}

	public void setCzsj(String czsj) {
		this.czsj = czsj;
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
}
