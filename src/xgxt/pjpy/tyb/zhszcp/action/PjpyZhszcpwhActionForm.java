package xgxt.pjpy.tyb.zhszcp.action;


import xgxt.pjpy.PjpyActionForm;
import xgxt.utils.Pages;

public class PjpyZhszcpwhActionForm extends PjpyActionForm {    
	
    private String querylike_xh;    
    private String querylike_xm;    
    private String queryequals_nj;    
    private String queryequals_xydm;    
    private String queryequals_zydm;    
    private String queryequals_bjdm;    
    private String save_zdid;    
    private String queryequals_zdid;    
    private String save_tabname;    
    private String queryequals_tabname;    
    private String save_zbid;    
    private String queryequals_zbid;    
    private String save_bcnr;    
    private String queryequals_bcnr; 
    private String queryequals_ejdm;
    private String queryequals_sjdm;
    private String queryequals_pmfs;
    private String queryequals_sidm;
    private String queryequals_jb;
    
    private String[] xhs;
    private String[] dms;
    private String[] fs;
    
    
	public String[] getDms() {
		return dms;
	}

	public void setDms(String[] dms) {
		this.dms = dms;
	}

	public String[] getXhs() {
		return xhs;
	}

	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	public String[] getFs() {
		return fs;
	}

	public void setFs(String[] fs) {
		this.fs = fs;
	}

	Pages pages = new Pages();

	public Pages getPages() {
		return pages;
	}

    public String getQuerylike_xh(){    
        return querylike_xh;    
    }    
    public void setQuerylike_xh(String querylike_xh){    
        this.querylike_xh = querylike_xh;    
    }    
    public String getQuerylike_xm(){    
        return querylike_xm;    
    }    
    public void setQuerylike_xm(String querylike_xm){    
        this.querylike_xm = querylike_xm;    
    }    
    public String getQueryequals_nj(){    
        return queryequals_nj;    
    }    
    public void setQueryequals_nj(String queryequals_nj){    
        this.queryequals_nj = queryequals_nj;    
    }    
    public String getQueryequals_xydm(){    
        return queryequals_xydm;    
    }    
    public void setQueryequals_xydm(String queryequals_xydm){    
        this.queryequals_xydm = queryequals_xydm;    
    }    
    public String getQueryequals_zydm(){    
        return queryequals_zydm;    
    }    
    public void setQueryequals_zydm(String queryequals_zydm){    
        this.queryequals_zydm = queryequals_zydm;    
    }    
    public String getQueryequals_bjdm(){    
        return queryequals_bjdm;    
    }    
    public void setQueryequals_bjdm(String queryequals_bjdm){    
        this.queryequals_bjdm = queryequals_bjdm;    
    }    
    public String getQueryequals_zdid(){    
        return queryequals_zdid;    
    }    
    public void setQueryequals_zdid(String queryequals_zdid){    
        this.queryequals_zdid = queryequals_zdid;    
    }    
    public String getSave_zdid(){    
        return save_zdid;    
    }    
    public void setSave_zdid(String save_zdid){    
        this.save_zdid = save_zdid;    
    }    
    public String getQueryequals_tabname(){    
        return queryequals_tabname;    
    }    
    public void setQueryequals_tabname(String queryequals_tabname){    
        this.queryequals_tabname = queryequals_tabname;    
    }    
    public String getSave_tabname(){    
        return save_tabname;    
    }    
    public void setSave_tabname(String save_tabname){    
        this.save_tabname = save_tabname;    
    }    
    public String getQueryequals_zbid(){    
        return queryequals_zbid;    
    }    
    public void setQueryequals_zbid(String queryequals_zbid){    
        this.queryequals_zbid = queryequals_zbid;    
    }    
    public String getSave_zbid(){    
        return save_zbid;    
    }    
    public void setSave_zbid(String save_zbid){    
        this.save_zbid = save_zbid;    
    }    
    public String getQueryequals_bcnr(){    
        return queryequals_bcnr;    
    }    
    public void setQueryequals_bcnr(String queryequals_bcnr){    
        this.queryequals_bcnr = queryequals_bcnr;    
    }    
    public String getSave_bcnr(){    
        return save_bcnr;    
    }    
    public void setSave_bcnr(String save_bcnr){    
        this.save_bcnr = save_bcnr;    
    }    
	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getQueryequals_ejdm() {
		return queryequals_ejdm;
	}

	public void setQueryequals_ejdm(String queryequals_ejdm) {
		this.queryequals_ejdm = queryequals_ejdm;
	}

	public String getQueryequals_sjdm() {
		return queryequals_sjdm;
	}

	public void setQueryequals_sjdm(String queryequals_sjdm) {
		this.queryequals_sjdm = queryequals_sjdm;
	}

	public String getQueryequals_pmfs() {
		return queryequals_pmfs;
	}

	public void setQueryequals_pmfs(String queryequals_pmfs) {
		this.queryequals_pmfs = queryequals_pmfs;
	}

	public String getQueryequals_sidm() {
		return queryequals_sidm;
	}

	public void setQueryequals_sidm(String queryequals_sidm) {
		this.queryequals_sidm = queryequals_sidm;
	}

	public String getQueryequals_jb() {
		return queryequals_jb;
	}

	public void setQueryequals_jb(String queryequals_jb) {
		this.queryequals_jb = queryequals_jb;
	}
}
