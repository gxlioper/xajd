package xsgzgl.qgzx.cssz;

import xsgzgl.comm.form.CommForm;
/**
 * 勤工助学-基础设置-参数设置
 * @author yeyipin
 * @since 2012.7.16
 */
public class QgzxCsszForm extends CommForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cjbz;//酬金标准
	private String xsgws;//学生岗位数
	private String sxsz;//上限数值
	private String sxzd;//上限字段
	private String sfyxffje;//是否允许发放金额
	private String sfjfhb;//是否通过金飞划拨控制酬金发放
	private String sfkfgwsq;//是否开放岗位申请
	private String cjsx;//酬金上限
	private String ksyf;//酬金发放开始月份
	private String jsyf;//酬金发放结束月份
	private String kssj;//酬金发放开始时间
	private String jssj;//酬金发放结束时间
	private String gwsqkssj;//岗位申请开始时间
	private String gwsqjssj;//岗位申请结束时间
	private String xsxsgws;//学生可申请岗位数
	private String sfkfxsgwsq;//是否开放学生岗位申请
	private String xsgwsqkssj;//学生岗位申请开始时间
	private String xsgwsqjssj;//学生岗位申请结束时间
	private String xsgwsqsplc;//学生校级岗位申请审批流程
	private String kcxs;	//申请时，是否显示课程时间
	
	private String rskzjb;//人数控制级别
	private String qxfw;//权限范围
	private String yjqxfwkz;
	
	private String sfsdgwcjsx;//是否设定岗位酬金上限(是:yes  否:no)
	private String gwzgcjsx;//岗位最高酬金上限[元/月 （每个岗位每人每月酬金上限）]
	private String sfkgggwcjsx;//用人单位申请岗位时可否更改岗位酬金上限(是:yes  否:no)
	private String dsfxy; //第三方协议
	private String sfxyzgsc;//是否只有勤工资格库内学生可申请(是:yes  否:no)
	private String sqfs; // 申请方式(山西财经大学)
	private String pkscjbz;//贫困生酬金标准(温州大学)
	private String pkscjzgsx;//贫困生酬金最高上限
	
	private String yrdwgwsqxn;//用人单位岗位申请学年
	
	private String gdgcjbz;//固定岗酬金标准（西安科技大学）
	//审批流参数设置
	private String sqkg;
	private String splc;
	private String sqkssj;
	private String sqjssj;
	private String sfyxcgcjsx;//是否允许超过酬金上限（浙大个性化）
	private String yrdwsplc;//校内岗位发布审批流程（原：用人单位审批流程）
	
	private String xsyjgwsqsplc;//学生院级岗位申请审批流程
	
	private String yjrskzjb;//院级人数控制级别
	private String yjqxfw;//院级权限范围

	private String id;
	private String sfkfxwgwsq;//校外岗位申请开关
	private String xwgwsqkssj;//校外岗位申请开始时间
	private String xwgwsqjssj;//校外岗位申请结束时间
	private String xwgwsplc;//校外岗位发布审批流程
	private String xwxsgwsqsplc;//学生校外岗位申请审批流程
	private String gzscwhsplc;//工作时长维护审批流程
	private String xslzsplc;//学生离职审批流程
	private String gzsqkg;//工资申报开关
	private String gzsqkssj;//工资申报开始时间
	private String gzsqjssj;//工资申报结束时间
	private String gzsqsplc;//工资申报审批流程

	private String gzscsx;//工作时长上限

	public String getGzscsx() {
		return gzscsx;
	}

	public void setGzscsx(String gzscsx) {
		this.gzscsx = gzscsx;
	}

	public String getYjqxfwkz() {
		return yjqxfwkz;
	}

	public void setYjqxfwkz(String yjqxfwkz) {
		this.yjqxfwkz = yjqxfwkz;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSfkfxwgwsq() {
		return sfkfxwgwsq;
	}

	public void setSfkfxwgwsq(String sfkfxwgwsq) {
		this.sfkfxwgwsq = sfkfxwgwsq;
	}

	public String getXwgwsqkssj() {
		return xwgwsqkssj;
	}

	public void setXwgwsqkssj(String xwgwsqkssj) {
		this.xwgwsqkssj = xwgwsqkssj;
	}

	public String getXwgwsqjssj() {
		return xwgwsqjssj;
	}

	public void setXwgwsqjssj(String xwgwsqjssj) {
		this.xwgwsqjssj = xwgwsqjssj;
	}

	public String getXwgwsplc() {
		return xwgwsplc;
	}

	public void setXwgwsplc(String xwgwsplc) {
		this.xwgwsplc = xwgwsplc;
	}

	public String getXwxsgwsqsplc() {
		return xwxsgwsqsplc;
	}

	public void setXwxsgwsqsplc(String xwxsgwsqsplc) {
		this.xwxsgwsqsplc = xwxsgwsqsplc;
	}

	public String getGzscwhsplc() {
		return gzscwhsplc;
	}

	public void setGzscwhsplc(String gzscwhsplc) {
		this.gzscwhsplc = gzscwhsplc;
	}

	public String getXslzsplc() {
		return xslzsplc;
	}

	public void setXslzsplc(String xslzsplc) {
		this.xslzsplc = xslzsplc;
	}

	public String getGzsqkg() {
		return gzsqkg;
	}

	public void setGzsqkg(String gzsqkg) {
		this.gzsqkg = gzsqkg;
	}

	public String getGzsqkssj() {
		return gzsqkssj;
	}

	public void setGzsqkssj(String gzsqkssj) {
		this.gzsqkssj = gzsqkssj;
	}

	public String getGzsqjssj() {
		return gzsqjssj;
	}

	public void setGzsqjssj(String gzsqjssj) {
		this.gzsqjssj = gzsqjssj;
	}

	public String getGzsqsplc() {
		return gzsqsplc;
	}

	public void setGzsqsplc(String gzsqsplc) {
		this.gzsqsplc = gzsqsplc;
	}

	/**
	 * @return the yjrskzjb
	 */
	public String getYjrskzjb() {
		return yjrskzjb;
	}
	/**
	 * @param yjrskzjb要设置的 yjrskzjb
	 */
	public void setYjrskzjb(String yjrskzjb) {
		this.yjrskzjb = yjrskzjb;
	}
	/**
	 * @return the yjqxfw
	 */
	public String getYjqxfw() {
		return yjqxfw;
	}
	/**
	 * @param yjqxfw要设置的 yjqxfw
	 */
	public void setYjqxfw(String yjqxfw) {
		this.yjqxfw = yjqxfw;
	}
	/**
	 * @return the xsyjgwsqsplc
	 */
	public String getXsyjgwsqsplc() {
		return xsyjgwsqsplc;
	}
	/**
	 * @param xsyjgwsqsplc要设置的 xsyjgwsqsplc
	 */
	public void setXsyjgwsqsplc(String xsyjgwsqsplc) {
		this.xsyjgwsqsplc = xsyjgwsqsplc;
	}
	public String getYrdwsplc() {
		return yrdwsplc;
	}
	public void setYrdwsplc(String yrdwsplc) {
		this.yrdwsplc = yrdwsplc;
	}
	/**
	 * @return the yrdwgwsqxn
	 */
	public String getYrdwgwsqxn() {
		return yrdwgwsqxn;
	}
	/**
	 * @param yrdwgwsqxn要设置的 yrdwgwsqxn
	 */
	public void setYrdwgwsqxn(String yrdwgwsqxn) {
		this.yrdwgwsqxn = yrdwgwsqxn;
	}
	/**
	 * @return the sfyxcgcjsx
	 */
	public String getSfyxcgcjsx() {
		return sfyxcgcjsx;
	}
	/**
	 * @param sfyxcgcjsx要设置的 sfyxcgcjsx
	 */
	public void setSfyxcgcjsx(String sfyxcgcjsx) {
		this.sfyxcgcjsx = sfyxcgcjsx;
	}
	/**
	 * @return the sqkg
	 */
	public String getSqkg() {
		return sqkg;
	}
	/**
	 * @param sqkg要设置的 sqkg
	 */
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splc要设置的 splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @return the sqkssj
	 */
	public String getSqkssj() {
		return sqkssj;
	}
	/**
	 * @param sqkssj要设置的 sqkssj
	 */
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	/**
	 * @return the sqjssj
	 */
	public String getSqjssj() {
		return sqjssj;
	}
	/**
	 * @param sqjssj要设置的 sqjssj
	 */
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	public String getPkscjbz() {
		return pkscjbz;
	}
	public void setPkscjbz(String pkscjbz) {
		this.pkscjbz = pkscjbz;
	}
	public String getPkscjzgsx() {
		return pkscjzgsx;
	}
	public void setPkscjzgsx(String pkscjzgsx) {
		this.pkscjzgsx = pkscjzgsx;
	}
	public String getSqfs() {
		return sqfs;
	}
	public void setSqfs(String sqfs) {
		this.sqfs = sqfs;
	}
	public String getXsgws() {
		return xsgws;
	}
	public void setXsgws(String xsgws) {
		this.xsgws = xsgws;
	}
	public String getSxsz() {
		return sxsz;
	}
	public void setSxsz(String sxsz) {
		this.sxsz = sxsz;
	}
	public String getSxzd() {
		return sxzd;
	}
	public void setSxzd(String sxzd) {
		this.sxzd = sxzd;
	}
	public String getSfyxffje() {
		return sfyxffje;
	}
	public void setSfyxffje(String sfyxffje) {
		this.sfyxffje = sfyxffje;
	}
	public String getSfjfhb() {
		return sfjfhb;
	}
	public void setSfjfhb(String sfjfhb) {
		this.sfjfhb = sfjfhb;
	}
	public String getCjbz() {
		return cjbz;
	}
	public void setCjbz(String cjbz) {
		this.cjbz = cjbz;
	}
	public String getCjsx() {
		return cjsx;
	}
	public void setCjsx(String cjsx) {
		this.cjsx = cjsx;
	}
	public String getKsyf() {
		return ksyf;
	}
	public void setKsyf(String ksyf) {
		this.ksyf = ksyf;
	}
	public String getJsyf() {
		return jsyf;
	}
	public void setJsyf(String jsyf) {
		this.jsyf = jsyf;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getSfkfgwsq() {
		return sfkfgwsq;
	}
	public void setSfkfgwsq(String sfkfgwsq) {
		this.sfkfgwsq = sfkfgwsq;
	}
	public String getGwsqkssj() {
		return gwsqkssj;
	}
	public void setGwsqkssj(String gwsqkssj) {
		this.gwsqkssj = gwsqkssj;
	}
	public String getGwsqjssj() {
		return gwsqjssj;
	}
	public void setGwsqjssj(String gwsqjssj) {
		this.gwsqjssj = gwsqjssj;
	}
	public String getXsxsgws() {
		return xsxsgws;
	}
	public void setXsxsgws(String xsxsgws) {
		this.xsxsgws = xsxsgws;
	}
	public String getSfkfxsgwsq() {
		return sfkfxsgwsq;
	}
	public void setSfkfxsgwsq(String sfkfxsgwsq) {
		this.sfkfxsgwsq = sfkfxsgwsq;
	}
	public String getXsgwsqkssj() {
		return xsgwsqkssj;
	}
	public void setXsgwsqkssj(String xsgwsqkssj) {
		this.xsgwsqkssj = xsgwsqkssj;
	}
	public String getXsgwsqjssj() {
		return xsgwsqjssj;
	}
	public void setXsgwsqjssj(String xsgwsqjssj) {
		this.xsgwsqjssj = xsgwsqjssj;
	}
	public String getXsgwsqsplc() {
		return xsgwsqsplc;
	}
	public void setXsgwsqsplc(String xsgwsqsplc) {
		this.xsgwsqsplc = xsgwsqsplc;
	}
	/**
	 * @return the rskzjb
	 */
	public String getRskzjb() {
		return rskzjb;
	}
	/**
	 * @param rskzjb要设置的 rskzjb
	 */
	public void setRskzjb(String rskzjb) {
		this.rskzjb = rskzjb;
	}
	/**
	 * @return the qxfw
	 */
	public String getQxfw() {
		return qxfw;
	}
	/**
	 * @param qxfw要设置的 qxfw
	 */
	public void setQxfw(String qxfw) {
		this.qxfw = qxfw;
	}
	/**
	 * @return the sfsdgwcjsx
	 */
	public String getSfsdgwcjsx() {
		return sfsdgwcjsx;
	}
	/**
	 * @param sfsdgwcjsx要设置的 sfsdgwcjsx
	 */
	public void setSfsdgwcjsx(String sfsdgwcjsx) {
		this.sfsdgwcjsx = sfsdgwcjsx;
	}
	/**
	 * @return the gwzgcjsx
	 */
	public String getGwzgcjsx() {
		return gwzgcjsx;
	}
	/**
	 * @param gwzgcjsx要设置的 gwzgcjsx
	 */
	public void setGwzgcjsx(String gwzgcjsx) {
		this.gwzgcjsx = gwzgcjsx;
	}
	/**
	 * @return the sfkgggwcjsx
	 */
	public String getSfkgggwcjsx() {
		return sfkgggwcjsx;
	}
	/**
	 * @param sfkgggwcjsx要设置的 sfkgggwcjsx
	 */
	public void setSfkgggwcjsx(String sfkgggwcjsx) {
		this.sfkgggwcjsx = sfkgggwcjsx;
	}
	public String getDsfxy() {
		return dsfxy;
	}
	public void setDsfxy(String dsfxy) {
		this.dsfxy = dsfxy;
	}
	/**
	 * @return the sfxyzgsc
	 */
	public String getSfxyzgsc() {
		return sfxyzgsc;
	}
	/**
	 * @param sfxyzgsc要设置的 sfxyzgsc
	 */
	public void setSfxyzgsc(String sfxyzgsc) {
		this.sfxyzgsc = sfxyzgsc;
	}
	/**
	 * @return the kcxs
	 */
	public String getKcxs() {
		return kcxs;
	}
	/**
	 * @param kcxs要设置的 kcxs
	 */
	public void setKcxs(String kcxs) {
		this.kcxs = kcxs;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-9 下午04:19:42 
	 * @return		: the gdgcjbz
	 */
	public String getGdgcjbz() {
		return gdgcjbz;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-9 下午04:19:42 
	 * @param 		：gdgcjbz the gdgcjbz to set
	 */
	public void setGdgcjbz(String gdgcjbz) {
		this.gdgcjbz = gdgcjbz;
	}
	
}
