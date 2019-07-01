/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.ttgl.stgl.stgljg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

public class StglForm extends ActionForm{
	private Pages pages = new Pages();
	// 高级查询
	SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();

	private String type;
	private String jgid;
	private String stlx;
	private String stqc;
	private String stjc;
	private String styx;
	private String ywzddw;
	private String stzdls;
	private String stjs;
	private String gzh;
	private String filepath;
	
	private String[] xhArray;
	
	private String splc;
	private String shzt;
	private String sjly;
	
	private String sqid;
	private String stzt;

	private String strs;//组织人数
	private String[] jflyArray;//学生组织经费来源
	private String jfly;
	private String bgsdz;//办公室地址
	private String ndzzzt;//年度组织状态
	private String xn;//学年
	private String zzlb;//组织类别
	private String[] tzsxh;//团支书信息学号
    private String clsj;//成立时间

    public String getClsj() {
        return clsj;
    }

    public void setClsj(String clsj) {
        this.clsj = clsj;
    }

    public String getStrs() {
        return strs;
    }

    public void setStrs(String strs) {
        this.strs = strs;
    }

    public String[] getJflyArray() {
        return jflyArray;
    }

    public void setJflyArray(String[] jflyArray) {
        this.jflyArray = jflyArray;
    }

    public String getJfly() {
        return jfly;
    }

    public void setJfly(String jfly) {
        this.jfly = jfly;
    }

    public String getBgsdz() {
        return bgsdz;
    }

    public void setBgsdz(String bgsdz) {
        this.bgsdz = bgsdz;
    }

    public String getNdzzzt() {
        return ndzzzt;
    }

    public void setNdzzzt(String ndzzzt) {
        this.ndzzzt = ndzzzt;
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    public String getZzlb() {
        return zzlb;
    }

    public void setZzlb(String zzlb) {
        this.zzlb = zzlb;
    }

    public String[] getTzsxh() {
        return tzsxh;
    }

    public void setTzsxh(String[] tzsxh) {
        this.tzsxh = tzsxh;
    }

    /**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @return		: the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @param 		：pages the pages to set
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @return		: the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @param 		：searchModel the searchModel to set
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @return		: the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @param 		：exportModel the exportModel to set
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @return		: the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @param 		：type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @return		: the jgid
	 */
	public String getJgid() {
		return jgid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @param 		：jgid the jgid to set
	 */
	public void setJgid(String jgid) {
		this.jgid = jgid;
	}
	
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 下午01:56:52 
	 * @return		: the stlx
	 */
	public String getStlx() {
		return stlx;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 下午01:56:52 
	 * @param 		：stlx the stlx to set
	 */
	public void setStlx(String stlx) {
		this.stlx = stlx;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @return		: the stqc
	 */
	public String getStqc() {
		return stqc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @param 		：stqc the stqc to set
	 */
	public void setStqc(String stqc) {
		this.stqc = stqc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @return		: the stjc
	 */
	public String getStjc() {
		return stjc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @param 		：stjc the stjc to set
	 */
	public void setStjc(String stjc) {
		this.stjc = stjc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @return		: the styx
	 */
	public String getStyx() {
		return styx;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @param 		：styx the styx to set
	 */
	public void setStyx(String styx) {
		this.styx = styx;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @return		: the ywzddw
	 */
	public String getYwzddw() {
		return ywzddw;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @param 		：ywzddw the ywzddw to set
	 */
	public void setYwzddw(String ywzddw) {
		this.ywzddw = ywzddw;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @return		: the stzdls
	 */
	public String getStzdls() {
		return stzdls;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @param 		：stzdls the stzdls to set
	 */
	public void setStzdls(String stzdls) {
		this.stzdls = stzdls;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @return		: the stjs
	 */
	public String getStjs() {
		return stjs;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @param 		：stjs the stjs to set
	 */
	public void setStjs(String stjs) {
		this.stjs = stjs;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @return		: the gzh
	 */
	public String getGzh() {
		return gzh;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @param 		：gzh the gzh to set
	 */
	public void setGzh(String gzh) {
		this.gzh = gzh;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @return		: the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-30 上午11:05:38 
	 * @param 		：filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-31 上午09:33:06 
	 * @return		: the xhArray
	 */
	public String[] getXhArray() {
		return xhArray;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-31 上午09:33:06 
	 * @param 		：xhArray the xhArray to set
	 */
	public void setXhArray(String[] xhArray) {
		this.xhArray = xhArray;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-31 上午10:18:50 
	 * @return		: the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-31 上午10:18:50 
	 * @param 		：splc the splc to set
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-31 上午10:18:50 
	 * @return		: the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-31 上午10:18:50 
	 * @param 		：shzt the shzt to set
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-31 上午10:18:50 
	 * @return		: the sjly
	 */
	public String getSjly() {
		return sjly;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-31 上午10:18:50 
	 * @param 		：sjly the sjly to set
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午04:09:03 
	 * @return		: the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午04:09:03 
	 * @param 		：sqid the sqid to set
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 上午10:29:42 
	 * @return		: the stzt
	 */
	public String getStzt() {
		return stzt;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 上午10:29:42 
	 * @param 		：stzt the stzt to set
	 */
	public void setStzt(String stzt) {
		this.stzt = stzt;
	}
	
	
}
