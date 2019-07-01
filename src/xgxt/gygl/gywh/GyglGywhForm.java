package xgxt.gygl.gywh;

import xgxt.form.User;
import xgxt.gygl.GyglCommForm;

public class GyglGywhForm extends GyglCommForm {

	private static final long serialVersionUID = 1L;
	
	private User user;
	
	private String tableName;//表名
	
	private String realTable;//导入表;
	
	private String query;//条件
	
	private String []inputArr;//输入值
	
	private String []yqArr;//园区;
	
	private String []xqArr;//校区
	
	private String pkValue;//主键值
	
	private String bhgz="blxs";//编号规则(默认为补零)
	
	private String lcsz="15";
	
	private String qsh;//寝室号
	
	private String cws;//床位数
	
	private String []cwhArr;//
	
	private String fpbj;//分配标记
	
	private String qsdh;//寝室电话
	
	private String sfbz;//受费标准
	
	private String bls;//寝室号位数
	
	private String qss;//寝室数
	
	private String []qsslArr;//寝室数量
	
	private String []cwsArr;//寝室数量
	
	private String []sfbzArr;//寝室数量
	
	private String []fpbjArr;//寝室数量
	
	private String []csArr;//层数数组
	
	private String []xbArr;//性别数组
	
	private String []kfhzArr;//可否混住
	
	private String xb;//性别
	
	private String kfhz;//可否混住
	
	private String ssbh;//宿舍编号
	
	private String lddm;//楼栋代码
	
	private String sfdr;//是否导入
	
	private String dm;//校区代码
	
	private String xqdm;//校区代码
	
	private String xqmc;//校区名称
	
	private String yqdm;//园区代码
	
	private String yqmc;//园区名称
	
	private String mklx;// 模块类型;
	
	private String ldmc;//楼栋名称;
	
	private String cs="7";//楼层;
	
	private String xbxd;//性别限定
	
	private String bz;// 备注

	private String qsbz;// 寝室备注
	
	private String czxq;//存在校区
	
	private String czyq;//存在园区
	
	private String []cwbjArr;//床位类型
	
	private String []checkVal;
	
	private String []colList;//输出字段;
	
	private String []topTr;//表头
	
	private String cwgs;//床位个数
	
	private String wsxz;//位数选择
	
	private String scb;//删除表
	
	private String glb;//关联表
	
	private String path;//路径
	
	private String xh;//学号
	
	private String []rzqk;//入住情况
	
	private String fpdx;//分配对象
	
	private String[]rzqkHidArr;
	
	private String[]xbxdHidArr;
	
	private String ldHid;//楼栋代码隐藏域
	
	private String fpbm;//分配部门
	
	private String cwsSelect;
	
	public String getCwsSelect() {
		return cwsSelect;
	}

	public void setCwsSelect(String cwsSelect) {
		this.cwsSelect = cwsSelect;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getGlb() {
		return glb;
	}

	public void setGlb(String glb) {
		this.glb = glb;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getScb() {
		return scb;
	}

	public void setScb(String scb) {
		this.scb = scb;
	}

	public String getWsxz() {
		return wsxz;
	}

	public void setWsxz(String wsxz) {
		this.wsxz = wsxz;
	}

	public String getCwgs() {
		return cwgs;
	}

	public void setCwgs(String cwgs) {
		this.cwgs = cwgs;
	}

	public String[] getColList() {
		return colList;
	}

	public void setColList(String[] colList) {
		this.colList = colList;
	}

	public String getMklx() {
		return mklx;
	}

	public void setMklx(String mklx) {
		this.mklx = mklx;
	}

	public String[] getTopTr() {
		return topTr;
	}

	public void setTopTr(String[] topTr) {
		this.topTr = topTr;
	}

	public String getXqdm() {
		return xqdm;
	}

	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}

	public String getXqmc() {
		return xqmc;
	}

	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}

	public String getYqdm() {
		return yqdm;
	}

	public void setYqdm(String yqdm) {
		this.yqdm = yqdm;
	}

	public String getYqmc() {
		return yqmc;
	}

	public void setYqmc(String yqmc) {
		this.yqmc = yqmc;
	}

	public String getSfdr() {
		return sfdr;
	}

	public void setSfdr(String sfdr) {
		this.sfdr = sfdr;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String[] getInputArr() {
		return inputArr;
	}

	public void setInputArr(String[] inputArr) {
		this.inputArr = inputArr;
	}

	public String getLddm() {
		return lddm;
	}

	public void setLddm(String lddm) {
		this.lddm = lddm;
	}

	public String getSsbh() {
		return ssbh;
	}

	public void setSsbh(String ssbh) {
		this.ssbh = ssbh;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String getRealTable() {
		return realTable;
	}

	public void setRealTable(String realTable) {
		this.realTable = realTable;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getCs() {
		return cs;
	}

	public void setCs(String cs) {
		this.cs = cs;
	}

	public String getLdmc() {
		return ldmc;
	}

	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}

	public String getXbxd() {
		return xbxd;
	}

	public void setXbxd(String xbxd) {
		this.xbxd = xbxd;
	}

	public String getLcsz() {
		return lcsz;
	}

	public void setLcsz(String lcsz) {
		this.lcsz = lcsz;
	}

	public String[] getXqArr() {
		return xqArr;
	}

	public void setXqArr(String[] xqArr) {
		this.xqArr = xqArr;
	}

	public String[] getYqArr() {
		return yqArr;
	}

	public void setYqArr(String[] yqArr) {
		this.yqArr = yqArr;
	}

	public String getCws() {
		return cws;
	}

	public void setCws(String cws) {
		this.cws = cws;
	}

	public String getFpbj() {
		return fpbj;
	}

	public void setFpbj(String fpbj) {
		this.fpbj = fpbj;
	}

	public String getKfhz() {
		return kfhz;
	}

	public void setKfhz(String kfhz) {
		this.kfhz = kfhz;
	}

	public String getQsdh() {
		return qsdh;
	}

	public void setQsdh(String qsdh) {
		this.qsdh = qsdh;
	}

	public String getQsh() {
		return qsh;
	}

	public void setQsh(String qsh) {
		this.qsh = qsh;
	}

	public String getSfbz() {
		return sfbz;
	}

	public void setSfbz(String sfbz) {
		this.sfbz = sfbz;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String getBhgz() {
		return bhgz;
	}

	public void setBhgz(String bhgz) {
		this.bhgz = bhgz;
	}

	public String getBls() {
		return bls;
	}

	public void setBls(String bls) {
		this.bls = bls;
	}

	public String[] getCwsArr() {
		return cwsArr;
	}

	public void setCwsArr(String[] cwsArr) {
		this.cwsArr = cwsArr;
	}

	public String[] getFpbjArr() {
		return fpbjArr;
	}

	public void setFpbjArr(String[] fpbjArr) {
		this.fpbjArr = fpbjArr;
	}

	public String[] getQsslArr() {
		return qsslArr;
	}

	public void setQsslArr(String[] qsslArr) {
		this.qsslArr = qsslArr;
	}

	public String[] getSfbzArr() {
		return sfbzArr;
	}

	public void setSfbzArr(String[] sfbzArr) {
		this.sfbzArr = sfbzArr;
	}

	public String[] getCsArr() {
		return csArr;
	}

	public void setCsArr(String[] csArr) {
		this.csArr = csArr;
	}

	public String[] getKfhzArr() {
		return kfhzArr;
	}

	public void setKfhzArr(String[] kfhzArr) {
		this.kfhzArr = kfhzArr;
	}

	public String[] getXbArr() {
		return xbArr;
	}

	public void setXbArr(String[] xbArr) {
		this.xbArr = xbArr;
	}
	
	public String getQss() {
		return qss;
	}

	public void setQss(String qss) {
		this.qss = qss;
	}

	public String getQsbz() {
		return qsbz;
	}

	public void setQsbz(String qsbz) {
		this.qsbz = qsbz;
	}

	public String getCzxq() {
		return czxq;
	}

	public void setCzxq(String czxq) {
		this.czxq = czxq;
	}

	public String getCzyq() {
		return czyq;
	}

	public void setCzyq(String czyq) {
		this.czyq = czyq;
	}

	public String[] getCwhArr() {
		return cwhArr;
	}

	public void setCwhArr(String[] cwhArr) {
		this.cwhArr = cwhArr;
	}

	public String[] getCwbjArr() {
		return cwbjArr;
	}

	public void setCwbjArr(String[] cwbjArr) {
		this.cwbjArr = cwbjArr;
	}

	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public String[] getRzqk() {
		return rzqk;
	}

	public void setRzqk(String[] rzqk) {
		this.rzqk = rzqk;
	}

	public String getFpdx() {
		return fpdx;
	}

	public void setFpdx(String fpdx) {
		this.fpdx = fpdx;
	}

	public String[] getRzqkHidArr() {
		return rzqkHidArr;
	}

	public void setRzqkHidArr(String[] rzqkHidArr) {
		this.rzqkHidArr = rzqkHidArr;
	}

	public String[] getXbxdHidArr() {
		return xbxdHidArr;
	}

	public void setXbxdHidArr(String[] xbxdHidArr) {
		this.xbxdHidArr = xbxdHidArr;
	}

	public String getLdHid() {
		return ldHid;
	}

	public void setLdHid(String ldHid) {
		this.ldHid = ldHid;
	}

	public String getFpbm() {
		return fpbm;
	}

	public void setFpbm(String fpbm) {
		this.fpbm = fpbm;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
