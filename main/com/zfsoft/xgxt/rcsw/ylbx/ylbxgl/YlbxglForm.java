package com.zfsoft.xgxt.rcsw.ylbx.ylbxgl;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 医疗保险管理
 */
public class YlbxglForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	// 分页
	Pages pages = new Pages();

	// 高级查询
	SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();

	FormFile uploadFile;// 上传文件

	private String yclx;//异常数据类型
	
	private String[] primarykey_checkVal;// CheckBox
	
	private String type; //类型
	
	private String jgid ;//主键
	private String xh ; //学号
	private String xn ; //学年
	private String xq ; //学期
	private String sqsj ;//申请时间
	private String czyh ;//操作用户
	private String sjly ;//是否通过审核流增加[1:走审核流增加，0：不需要走审核流增加]
	private String sqid ;//申请id
	private String zd1 ;
	private String zd2 ;
	private String zd3 ;
	private String zd4 ;
	private String zd5 ;
	private String zd6 ;
	private String zd7 ;
	private String zd8 ;
	private String zd9 ;
	private String zd10 ;
	private String zd11 ;
	private String zd12 ;
	private String zd13 ;
	private String zd14 ;
	private String zd15 ;
	private String zd16 ;
	private String zd17 ;
	private String zd18 ;
	private String zd19 ;
	private String zd20 ;
	private String zd21 ;
	private String zd22 ;
	private String zd23 ;
	private String zd24 ;
	private String zd25 ;
	private String zd26 ;
	private String zd27 ;
	private String zd28 ;
	private String zd29 ;
	private String zd30 ;
	
	private String yjnum ; // 应交年数
	private String xsnum ; // 选择学生数
	private String bynd ; // 毕业年度
	private String ylbxzt; //医疗保险状态
	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param pages要设置的 pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @param searchModel要设置的 searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @param exportModel要设置的 exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @return the uploadFile
	 */
	public FormFile getUploadFile() {
		return uploadFile;
	}
	/**
	 * @param uploadFile要设置的 uploadFile
	 */
	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	/**
	 * @return the yclx
	 */
	public String getYclx() {
		return yclx;
	}
	/**
	 * @param yclx要设置的 yclx
	 */
	public void setYclx(String yclx) {
		this.yclx = yclx;
	}
	/**
	 * @return the primarykey_checkVal
	 */
	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}
	/**
	 * @param primarykeyCheckVal要设置的 primarykey_checkVal
	 */
	public void setPrimarykey_checkVal(String[] primarykeyCheckVal) {
		primarykey_checkVal = primarykeyCheckVal;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type要设置的 type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the jgid
	 */
	public String getJgid() {
		return jgid;
	}
	/**
	 * @param jgid要设置的 jgid
	 */
	public void setJgid(String jgid) {
		this.jgid = jgid;
	}
	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xh要设置的 xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @param sqsj要设置的 sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	/**
	 * @return the czyh
	 */
	public String getCzyh() {
		return czyh;
	}
	/**
	 * @param czyh要设置的 czyh
	 */
	public void setCzyh(String czyh) {
		this.czyh = czyh;
	}
	/**
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}
	/**
	 * @param sjly要设置的 sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqid要设置的 sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	/**
	 * @return the zd1
	 */
	public String getZd1() {
		return zd1;
	}
	/**
	 * @param zd1要设置的 zd1
	 */
	public void setZd1(String zd1) {
		this.zd1 = zd1;
	}
	/**
	 * @return the zd2
	 */
	public String getZd2() {
		return zd2;
	}
	/**
	 * @param zd2要设置的 zd2
	 */
	public void setZd2(String zd2) {
		this.zd2 = zd2;
	}
	/**
	 * @return the zd3
	 */
	public String getZd3() {
		return zd3;
	}
	/**
	 * @param zd3要设置的 zd3
	 */
	public void setZd3(String zd3) {
		this.zd3 = zd3;
	}
	/**
	 * @return the zd4
	 */
	public String getZd4() {
		return zd4;
	}
	/**
	 * @param zd4要设置的 zd4
	 */
	public void setZd4(String zd4) {
		this.zd4 = zd4;
	}
	/**
	 * @return the zd5
	 */
	public String getZd5() {
		return zd5;
	}
	/**
	 * @param zd5要设置的 zd5
	 */
	public void setZd5(String zd5) {
		this.zd5 = zd5;
	}
	/**
	 * @return the zd6
	 */
	public String getZd6() {
		return zd6;
	}
	/**
	 * @param zd6要设置的 zd6
	 */
	public void setZd6(String zd6) {
		this.zd6 = zd6;
	}
	/**
	 * @return the zd7
	 */
	public String getZd7() {
		return zd7;
	}
	/**
	 * @param zd7要设置的 zd7
	 */
	public void setZd7(String zd7) {
		this.zd7 = zd7;
	}
	/**
	 * @return the zd8
	 */
	public String getZd8() {
		return zd8;
	}
	/**
	 * @param zd8要设置的 zd8
	 */
	public void setZd8(String zd8) {
		this.zd8 = zd8;
	}
	/**
	 * @return the zd9
	 */
	public String getZd9() {
		return zd9;
	}
	/**
	 * @param zd9要设置的 zd9
	 */
	public void setZd9(String zd9) {
		this.zd9 = zd9;
	}
	/**
	 * @return the zd10
	 */
	public String getZd10() {
		return zd10;
	}
	/**
	 * @param zd10要设置的 zd10
	 */
	public void setZd10(String zd10) {
		this.zd10 = zd10;
	}
	/**
	 * @return the zd11
	 */
	public String getZd11() {
		return zd11;
	}
	/**
	 * @param zd11要设置的 zd11
	 */
	public void setZd11(String zd11) {
		this.zd11 = zd11;
	}
	/**
	 * @return the zd12
	 */
	public String getZd12() {
		return zd12;
	}
	/**
	 * @param zd12要设置的 zd12
	 */
	public void setZd12(String zd12) {
		this.zd12 = zd12;
	}
	/**
	 * @return the zd13
	 */
	public String getZd13() {
		return zd13;
	}
	/**
	 * @param zd13要设置的 zd13
	 */
	public void setZd13(String zd13) {
		this.zd13 = zd13;
	}
	/**
	 * @return the zd14
	 */
	public String getZd14() {
		return zd14;
	}
	/**
	 * @param zd14要设置的 zd14
	 */
	public void setZd14(String zd14) {
		this.zd14 = zd14;
	}
	/**
	 * @return the zd15
	 */
	public String getZd15() {
		return zd15;
	}
	/**
	 * @param zd15要设置的 zd15
	 */
	public void setZd15(String zd15) {
		this.zd15 = zd15;
	}
	/**
	 * @return the zd16
	 */
	public String getZd16() {
		return zd16;
	}
	/**
	 * @param zd16要设置的 zd16
	 */
	public void setZd16(String zd16) {
		this.zd16 = zd16;
	}
	/**
	 * @return the zd17
	 */
	public String getZd17() {
		return zd17;
	}
	/**
	 * @param zd17要设置的 zd17
	 */
	public void setZd17(String zd17) {
		this.zd17 = zd17;
	}
	/**
	 * @return the zd18
	 */
	public String getZd18() {
		return zd18;
	}
	/**
	 * @param zd18要设置的 zd18
	 */
	public void setZd18(String zd18) {
		this.zd18 = zd18;
	}
	/**
	 * @return the zd19
	 */
	public String getZd19() {
		return zd19;
	}
	/**
	 * @param zd19要设置的 zd19
	 */
	public void setZd19(String zd19) {
		this.zd19 = zd19;
	}
	/**
	 * @return the zd20
	 */
	public String getZd20() {
		return zd20;
	}
	/**
	 * @param zd20要设置的 zd20
	 */
	public void setZd20(String zd20) {
		this.zd20 = zd20;
	}
	/**
	 * @return the zd21
	 */
	public String getZd21() {
		return zd21;
	}
	/**
	 * @param zd21要设置的 zd21
	 */
	public void setZd21(String zd21) {
		this.zd21 = zd21;
	}
	/**
	 * @return the zd22
	 */
	public String getZd22() {
		return zd22;
	}
	/**
	 * @param zd22要设置的 zd22
	 */
	public void setZd22(String zd22) {
		this.zd22 = zd22;
	}
	/**
	 * @return the zd23
	 */
	public String getZd23() {
		return zd23;
	}
	/**
	 * @param zd23要设置的 zd23
	 */
	public void setZd23(String zd23) {
		this.zd23 = zd23;
	}
	/**
	 * @return the zd24
	 */
	public String getZd24() {
		return zd24;
	}
	/**
	 * @param zd24要设置的 zd24
	 */
	public void setZd24(String zd24) {
		this.zd24 = zd24;
	}
	/**
	 * @return the zd25
	 */
	public String getZd25() {
		return zd25;
	}
	/**
	 * @param zd25要设置的 zd25
	 */
	public void setZd25(String zd25) {
		this.zd25 = zd25;
	}
	/**
	 * @return the zd26
	 */
	public String getZd26() {
		return zd26;
	}
	/**
	 * @param zd26要设置的 zd26
	 */
	public void setZd26(String zd26) {
		this.zd26 = zd26;
	}
	/**
	 * @return the zd27
	 */
	public String getZd27() {
		return zd27;
	}
	/**
	 * @param zd27要设置的 zd27
	 */
	public void setZd27(String zd27) {
		this.zd27 = zd27;
	}
	/**
	 * @return the zd28
	 */
	public String getZd28() {
		return zd28;
	}
	/**
	 * @param zd28要设置的 zd28
	 */
	public void setZd28(String zd28) {
		this.zd28 = zd28;
	}
	/**
	 * @return the zd29
	 */
	public String getZd29() {
		return zd29;
	}
	/**
	 * @param zd29要设置的 zd29
	 */
	public void setZd29(String zd29) {
		this.zd29 = zd29;
	}
	/**
	 * @return the zd30
	 */
	public String getZd30() {
		return zd30;
	}
	/**
	 * @param zd30要设置的 zd30
	 */
	public void setZd30(String zd30) {
		this.zd30 = zd30;
	}
	/**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xn要设置的 xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xq要设置的 xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getYjnum() {
		return yjnum;
	}
	public void setYjnum(String yjnum) {
		this.yjnum = yjnum;
	}
	public String getXsnum() {
		return xsnum;
	}
	public void setXsnum(String xsnum) {
		this.xsnum = xsnum;
	}
	/**
	 * @return the bynd
	 */
	public String getBynd() {
		return bynd;
	}
	/**
	 * @param bynd要设置的 bynd
	 */
	public void setBynd(String bynd) {
		this.bynd = bynd;
	}
	public String getYlbxzt() {
		return ylbxzt;
	}
	public void setYlbxzt(String ylbxzt) {
		this.ylbxzt = ylbxzt;
	}
	
}
