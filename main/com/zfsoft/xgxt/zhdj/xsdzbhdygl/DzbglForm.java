package com.zfsoft.xgxt.zhdj.xsdzbhdygl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class DzbglForm extends ActionForm {
    private String dzbhjid;
    private String dzbid;
    private String dzbmc;
    private String jcdwdm;
    private String dzbsj;
    private String sjlxdh;
    private String zzwy;
    private String zzwylxdh;
    private String xcwy;
    private String xcwylxdh;
    private String jjwy;
    private String jjwylxdh;
    private String clsj;
    private String hjsj;
    private String sjxm;
    private String zzwyxm;
    private String xcwyxm;
    private String jjwyxm;
    private String dm;
    private String mc;
    private String xydm;
    private String xymc;
    private String xm;
    private String zgh;
    private String bmdm;
    private String bmmc;
    private String zzmm;
    private String zzmmmc;
    private String zwlx;
    private String lxdh;
    private String cyHtml;
    private String type;
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();
   
    private String dzblx;

    private String[] zwmcs;
    private String[] zwdms;
    private String[] xhs;
    private String[] xsmcs;
    private String[] xsdhs;
    
    private String[] jgzwmcs;
    private String[] jgzwdms;
    private String[] lxrzghs;
    private String[] jzgmcs;
    private String[] jzgdhs;
    
    
    
    
    /**
	 * @return the jgzwmcs
	 */
	public String[] getJgzwmcs() {
		return jgzwmcs;
	}

	/**
	 * @param jgzwmcs要设置的 jgzwmcs
	 */
	public void setJgzwmcs(String[] jgzwmcs) {
		this.jgzwmcs = jgzwmcs;
	}

	/**
	 * @return the lxrzghs
	 */
	public String[] getLxrzghs() {
		return lxrzghs;
	}

	/**
	 * @param lxrzghs要设置的 lxrzghs
	 */
	public void setLxrzghs(String[] lxrzghs) {
		this.lxrzghs = lxrzghs;
	}

	/**
	 * @return the jzgmcs
	 */
	public String[] getJzgmcs() {
		return jzgmcs;
	}

	/**
	 * @param jzgmcs要设置的 jzgmcs
	 */
	public void setJzgmcs(String[] jzgmcs) {
		this.jzgmcs = jzgmcs;
	}

	/**
	 * @return the jzgdhs
	 */
	public String[] getJzgdhs() {
		return jzgdhs;
	}

	/**
	 * @param jzgdhs要设置的 jzgdhs
	 */
	public void setJzgdhs(String[] jzgdhs) {
		this.jzgdhs = jzgdhs;
	}

	public ExportModel getExportModel() {
        return exportModel;
    }

    public void setExportModel(ExportModel exportModel) {
        this.exportModel = exportModel;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getCyHtml() {
        return cyHtml;
    }

    public void setCyHtml(String cyHtml) {
        this.cyHtml = cyHtml;
    }

    public String getDzbhjid() {
        return dzbhjid;
    }

    public void setDzbhjid(String dzbhjid) {
        this.dzbhjid = dzbhjid;
    }

    public String getZwlx() {
        return zwlx;
    }

    public void setZwlx(String zwlx) {
        this.zwlx = zwlx;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getZgh() {
        return zgh;
    }

    public void setZgh(String zgh) {
        this.zgh = zgh;
    }

    public String getBmdm() {
        return bmdm;
    }

    public void setBmdm(String bmdm) {
        this.bmdm = bmdm;
    }

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }

    public String getZzmm() {
        return zzmm;
    }

    public void setZzmm(String zzmm) {
        this.zzmm = zzmm;
    }

    public String getZzmmmc() {
        return zzmmmc;
    }

    public void setZzmmmc(String zzmmmc) {
        this.zzmmmc = zzmmmc;
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

    public String getDm() {
        return dm;
    }

    public void setDm(String dm) {
        this.dm = dm;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getSjxm() {
        return sjxm;
    }

    public void setSjxm(String sjxm) {
        this.sjxm = sjxm;
    }

    public String getZzwyxm() {
        return zzwyxm;
    }

    public void setZzwyxm(String zzwyxm) {
        this.zzwyxm = zzwyxm;
    }

    public String getXcwyxm() {
        return xcwyxm;
    }

    public void setXcwyxm(String xcwyxm) {
        this.xcwyxm = xcwyxm;
    }

    public String getJjwyxm() {
        return jjwyxm;
    }

    public void setJjwyxm(String jjwyxm) {
        this.jjwyxm = jjwyxm;
    }

    public String getDzbid() {
        return dzbid;
    }

    public void setDzbid(String dzbid) {
        this.dzbid = dzbid;
    }

    public String getDzbmc() {
        return dzbmc;
    }

    public void setDzbmc(String dzbmc) {
        this.dzbmc = dzbmc;
    }

    public String getJcdwdm() {
        return jcdwdm;
    }

    public void setJcdwdm(String jcdwdm) {
        this.jcdwdm = jcdwdm;
    }

    public String getDzbsj() {
        return dzbsj;
    }

    public void setDzbsj(String dzbsj) {
        this.dzbsj = dzbsj;
    }

    public String getSjlxdh() {
        return sjlxdh;
    }

    public void setSjlxdh(String sjlxdh) {
        this.sjlxdh = sjlxdh;
    }

    public String getZzwy() {
        return zzwy;
    }

    public void setZzwy(String zzwy) {
        this.zzwy = zzwy;
    }

    public String getZzwylxdh() {
        return zzwylxdh;
    }

    public void setZzwylxdh(String zzwylxdh) {
        this.zzwylxdh = zzwylxdh;
    }

    public String getXcwy() {
        return xcwy;
    }

    public void setXcwy(String xcwy) {
        this.xcwy = xcwy;
    }

    public String getXcwylxdh() {
        return xcwylxdh;
    }

    public void setXcwylxdh(String xcwylxdh) {
        this.xcwylxdh = xcwylxdh;
    }

    public String getJjwy() {
        return jjwy;
    }

    public void setJjwy(String jjwy) {
        this.jjwy = jjwy;
    }

    public String getJjwylxdh() {
        return jjwylxdh;
    }

    public void setJjwylxdh(String jjwylxdh) {
        this.jjwylxdh = jjwylxdh;
    }

    public String getClsj() {
        return clsj;
    }

    public void setClsj(String clsj) {
        this.clsj = clsj;
    }

    public String getHjsj() {
        return hjsj;
    }

    public void setHjsj(String hjsj) {
        this.hjsj = hjsj;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Pages getPages() {
        return pages;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
    }

    public SearchModel getSearchModel() {
        return searchModel;
    }

    public void setSearchModel(SearchModel searchModel) {
        this.searchModel = searchModel;
    }

	/**
	 * @return the dzblx
	 */
	public String getDzblx() {
		return dzblx;
	}

	/**
	 * @param dzblx要设置的 dzblx
	 */
	public void setDzblx(String dzblx) {
		this.dzblx = dzblx;
	}

	/**
	 * @return the zwmcs
	 */
	public String[] getZwmcs() {
		return zwmcs;
	}

	/**
	 * @param zwmcs要设置的 zwmcs
	 */
	public void setZwmcs(String[] zwmcs) {
		this.zwmcs = zwmcs;
	}

	/**
	 * @return the xhs
	 */
	public String[] getXhs() {
		return xhs;
	}

	/**
	 * @param xhs要设置的 xhs
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}

	/**
	 * @return the xsmcs
	 */
	public String[] getXsmcs() {
		return xsmcs;
	}

	/**
	 * @param xsmcs要设置的 xsmcs
	 */
	public void setXsmcs(String[] xsmcs) {
		this.xsmcs = xsmcs;
	}

	/**
	 * @return the xsdhs
	 */
	public String[] getXsdhs() {
		return xsdhs;
	}

	/**
	 * @param xsdhs要设置的 xsdhs
	 */
	public void setXsdhs(String[] xsdhs) {
		this.xsdhs = xsdhs;
	}

	/**
	 * @return the zwdms
	 */
	public String[] getZwdms() {
		return zwdms;
	}

	/**
	 * @param zwdms要设置的 zwdms
	 */
	public void setZwdms(String[] zwdms) {
		this.zwdms = zwdms;
	}

	/**
	 * @return the jgzwdms
	 */
	public String[] getJgzwdms() {
		return jgzwdms;
	}

	/**
	 * @param jgzwdms要设置的 jgzwdms
	 */
	public void setJgzwdms(String[] jgzwdms) {
		this.jgzwdms = jgzwdms;
	}
	
	
    
}
