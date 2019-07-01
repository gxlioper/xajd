package xgxt.qgzx;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.utils.Pages;
public class QgzxTyForm extends ActionForm {
	private String yrdwdm;
	private String sqdw;
    private String save_sbsj;    
    private String queryequals_sbsj;    
    private String save_sbr;    
    private String queryequals_sbr;    
    private String save_fdysh;    
    private String queryequals_fdysh;    
    private String save_fdyshyj;    
    private String queryequals_fdyshyj;    
    private String save_fdyshsj;    
    private String queryequals_fdyshsj;    
    private String save_xysh;    
    private String save_xyshyj;    
    private String queryequals_xyshyj;    
    private String save_xyshsj;    
    private String queryequals_xyshsj;    
    private String save_xxsh;    
    private String save_xxshyj;    
    private String queryequals_xxshyj;    
    private String save_xxshsj;    
    private String queryequals_xxshsj;    
    private String querygreaterequal_sbsj;
    private String querylessequal_sbsj;

	private static final long serialVersionUID = -9205711105806100577L;

	/* 通用 */
	Pages pages = new Pages();

	FormFile uploadFile;// 上传文件

	private String fdyQx;// 辅导员权限

	private String bzrQx;// 班主任权限

	private String[] checkVal;// 批处理

	private String[] primarykey_checkVal;// 批处理

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

	private String bz;// 备注

	private String lx;// 类型

	private String queryequals_lx;

	private String id;// ID

	private String mklx;// 模块类型

	private String gwdm;// 岗位代码

	// ============临时岗位============
	
	private String[] lsgwmc;// '临时岗位名称';

	private String queryequals_lsgwmc;

	private String queryequals_gwdm;// 岗位代码

	private String[] lsgwsbsj;// '临时岗位申报时间';

	private String[] qgxh;// '勤工学号';

	private String gwsqsj;// '岗位申请时间';

	private String querygreaterequal_gwsqsj;

	private String querylessequal_gwsqsj;

	// ============临时岗位 end============

    public String getQueryequals_sbsj(){    
        return queryequals_sbsj;    
    }    
    public String getQuerygreaterequal_sbsj() {
		return querygreaterequal_sbsj;
	}
	public void setQuerygreaterequal_sbsj(String querygreaterequal_sbsj) {
		this.querygreaterequal_sbsj = querygreaterequal_sbsj;
	}
	public String getQuerylessequal_sbsj() {
		return querylessequal_sbsj;
	}
	public void setQuerylessequal_sbsj(String querylessequal_sbsj) {
		this.querylessequal_sbsj = querylessequal_sbsj;
	}
	public void setQueryequals_sbsj(String queryequals_sbsj){    
        this.queryequals_sbsj = queryequals_sbsj;    
    }    
    public String getSave_sbsj(){    
        return save_sbsj;    
    }    
    public void setSave_sbsj(String save_sbsj){    
        this.save_sbsj = save_sbsj;    
    }    
    public String getQueryequals_sbr(){    
        return queryequals_sbr;    
    }    
    public void setQueryequals_sbr(String queryequals_sbr){    
        this.queryequals_sbr = queryequals_sbr;    
    }    
    public String getSave_sbr(){    
        return save_sbr;    
    }    
    public void setSave_sbr(String save_sbr){    
        this.save_sbr = save_sbr;    
    }    
    public String getQueryequals_fdysh(){    
        return queryequals_fdysh;    
    }    
    public void setQueryequals_fdysh(String queryequals_fdysh){    
        this.queryequals_fdysh = queryequals_fdysh;    
    }    
    public String getSave_fdysh(){    
        return save_fdysh;    
    }    
    public void setSave_fdysh(String save_fdysh){    
        this.save_fdysh = save_fdysh;    
    }    
    public String getQueryequals_fdyshyj(){    
        return queryequals_fdyshyj;    
    }    
    public void setQueryequals_fdyshyj(String queryequals_fdyshyj){    
        this.queryequals_fdyshyj = queryequals_fdyshyj;    
    }    
    public String getSave_fdyshyj(){    
        return save_fdyshyj;    
    }    
    public void setSave_fdyshyj(String save_fdyshyj){    
        this.save_fdyshyj = save_fdyshyj;    
    }    
    public String getQueryequals_fdyshsj(){    
        return queryequals_fdyshsj;    
    }    
    public void setQueryequals_fdyshsj(String queryequals_fdyshsj){    
        this.queryequals_fdyshsj = queryequals_fdyshsj;    
    }    
    public String getSave_fdyshsj(){    
        return save_fdyshsj;    
    }    
    public void setSave_fdyshsj(String save_fdyshsj){    
        this.save_fdyshsj = save_fdyshsj;    
    }    
    public String getSave_xysh(){    
        return save_xysh;    
    }    
    public void setSave_xysh(String save_xysh){    
        this.save_xysh = save_xysh;    
    }    
    public String getQueryequals_xyshyj(){    
        return queryequals_xyshyj;    
    }    
    public void setQueryequals_xyshyj(String queryequals_xyshyj){    
        this.queryequals_xyshyj = queryequals_xyshyj;    
    }    
    public String getSave_xyshyj(){    
        return save_xyshyj;    
    }    
    public void setSave_xyshyj(String save_xyshyj){    
        this.save_xyshyj = save_xyshyj;    
    }    
    public String getQueryequals_xyshsj(){    
        return queryequals_xyshsj;    
    }    
    public void setQueryequals_xyshsj(String queryequals_xyshsj){    
        this.queryequals_xyshsj = queryequals_xyshsj;    
    }    
    public String getSave_xyshsj(){    
        return save_xyshsj;    
    }    
    public void setSave_xyshsj(String save_xyshsj){    
        this.save_xyshsj = save_xyshsj;    
    }    
    public String getSave_xxsh(){    
        return save_xxsh;    
    }    
    public void setSave_xxsh(String save_xxsh){    
        this.save_xxsh = save_xxsh;    
    }    
    public String getQueryequals_xxshyj(){    
        return queryequals_xxshyj;    
    }    
    public void setQueryequals_xxshyj(String queryequals_xxshyj){    
        this.queryequals_xxshyj = queryequals_xxshyj;    
    }    
    public String getSave_xxshyj(){    
        return save_xxshyj;    
    }    
    public void setSave_xxshyj(String save_xxshyj){    
        this.save_xxshyj = save_xxshyj;    
    }    
    public String getQueryequals_xxshsj(){    
        return queryequals_xxshsj;    
    }    
    public void setQueryequals_xxshsj(String queryequals_xxshsj){    
        this.queryequals_xxshsj = queryequals_xxshsj;    
    }    
    public String getSave_xxshsj(){    
        return save_xxshsj;    
    }    
    public void setSave_xxshsj(String save_xxshsj){    
        this.save_xxshsj = save_xxshsj;    
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

	public String getFdyQx() {
		return fdyQx;
	}

	public void setFdyQx(String fdyQx) {
		this.fdyQx = fdyQx;
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

	public String getGwsqsj() {
		return gwsqsj;
	}

	public void setGwsqsj(String gwsqsj) {
		this.gwsqsj = gwsqsj;
	}

	public String[] getLsgwmc() {
		return lsgwmc;
	}

	public void setLsgwmc(String[] lsgwmc) {
		this.lsgwmc = lsgwmc;
	}

	public String[] getLsgwsbsj() {
		return lsgwsbsj;
	}

	public void setLsgwsbsj(String[] lsgwsbsj) {
		this.lsgwsbsj = lsgwsbsj;
	}

	public String[] getQgxh() {
		return qgxh;
	}

	public void setQgxh(String[] qgxh) {
		this.qgxh = qgxh;
	}

	public String getGwdm() {
		return gwdm;
	}

	public void setGwdm(String gwdm) {
		this.gwdm = gwdm;
	}

	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}

	public void setPrimarykey_checkVal(String[] primarykey_checkVal) {
		this.primarykey_checkVal = primarykey_checkVal;
	}

	public String getQueryequals_lsgwmc() {
		return queryequals_lsgwmc;
	}

	public void setQueryequals_lsgwmc(String queryequals_lsgwmc) {
		this.queryequals_lsgwmc = queryequals_lsgwmc;
	}

	public String getQuerygreaterequal_gwsqsj() {
		return querygreaterequal_gwsqsj;
	}

	public void setQuerygreaterequal_gwsqsj(String querygreaterequal_gwsqsj) {
		this.querygreaterequal_gwsqsj = querygreaterequal_gwsqsj;
	}

	public String getQuerylessequal_gwsqsj() {
		return querylessequal_gwsqsj;
	}

	public void setQuerylessequal_gwsqsj(String querylessequal_gwsqsj) {
		this.querylessequal_gwsqsj = querylessequal_gwsqsj;
	}

	public String getQueryequals_gwdm() {
		return queryequals_gwdm;
	}

	public void setQueryequals_gwdm(String queryequals_gwdm) {
		this.queryequals_gwdm = queryequals_gwdm;
	}
	public String getYrdwdm() {
		return yrdwdm;
	}
	public void setYrdwdm(String yrdwdm) {
		this.yrdwdm = yrdwdm;
	}
	public String getSqdw() {
		return sqdw;
	}
	public void setSqdw(String sqdw) {
		this.sqdw = sqdw;
	}
}
