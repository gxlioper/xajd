package com.zfsoft.xgxt.zhdj.xsdzbhdygl;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class DzbzwwhForm extends ActionForm {
    private String dm;
    private String mc;
    private String zwss;
    private String zwlx;
    private String type;
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private String[] dms;
    private String[] mcs;

    public String[] getDms() {
        return dms;
    }

    public void setDms(String[] dms) {
        this.dms = dms;
    }

    public SearchModel getSearchModel() {
        return searchModel;
    }

    public void setSearchModel(SearchModel searchModel) {
        this.searchModel = searchModel;
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


    /**
	 * @return the zwss
	 */
	public String getZwss() {
		return zwss;
	}

	/**
	 * @param zwss要设置的 zwss
	 */
	public void setZwss(String zwss) {
		this.zwss = zwss;
	}

	/**
	 * @return the zwlx
	 */
	public String getZwlx() {
		return zwlx;
	}

	/**
	 * @param zwlx要设置的 zwlx
	 */
	public void setZwlx(String zwlx) {
		this.zwlx = zwlx;
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

	/**
	 * @return the mcs
	 */
	public String[] getMcs() {
		return mcs;
	}

	/**
	 * @param mcs要设置的 mcs
	 */
	public void setMcs(String[] mcs) {
		this.mcs = mcs;
	}
    
    
}
