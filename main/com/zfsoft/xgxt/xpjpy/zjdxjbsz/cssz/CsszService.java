/**
 * @部门:学工产品1部
 * @日期：2017-3-21 上午09:19:17 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.zcxmdj.ZcxmdjDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.zcxmfz.ZcxmfzDao;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 新评奖评优_基本设置_参数设置
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-3-21 上午09:19:17 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszService extends SuperServiceImpl<CsszForm, CsszDao>{
	private static final String SplitChar = "#";
	public static final String OPEN = "1"; //开放
	public static final String CLOSE = "0"; //关闭
	private CsszDao dao = new CsszDao();

	public CsszService() {
		super.setDao(dao);
	}
	
	/**
	 * @描述: 评奖学年下拉框显示
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2017-12-5 上午11:21:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getPjzqList(){
		//评奖周期列表
		List<HashMap<String,String>> pjzqList = new ArrayList<HashMap<String,String>>();
		String xn = null;
		/*从2017-2018学年开始正式使用*/
		for (int i = 0; i < 6; i++){
			int xns = 2017;
			xn = String.valueOf(xns - 3 + i ) + "-" + String.valueOf(xns - 2 + i);
			HashMap<String,String> zqMap = new HashMap<String, String>();
			zqMap.put("pjzq", xn);
			pjzqList.add(zqMap);
		}
		return pjzqList;
	}
	
	public boolean updateCssz(String zdKey,String zdValue) throws Exception{
		if ("xn".equals(zdKey)){
			String[] zqArray = zdValue.split(SplitChar);
			return dao.updatePjzq(zqArray);
		} else {
			return dao.updateCssz(zdKey, zdValue);
		}
		
	}
	
	public CsszForm getCsszModel() throws Exception{
		CsszForm model = dao.getModel();
		if (model == null){
			//初始化参数设置
			model = new CsszForm();
			model.setPjkg(CLOSE);
			model.setXn(Base.currXn);
			boolean result = dao.runInsert(model);
		} else {
			String xn = model.getXn();
			//拼装评奖周期
			if (!StringUtil.isNull(xn)){
				model.setXn(xn);
			}
		}
		boolean isOpen = isOpen(model);
		model.setKgzt(String.valueOf(isOpen));
		return model;
	}
	
	public boolean isOpen(CsszForm model) throws Exception{
		if (model == null){
			model = dao.getModel();
		}
		//无参数设置数据 或者开关状态为关闭 ，为关闭状态
		if (model == null || CLOSE.equals(model.getPjkg())){
			return false;
		}
		//格式化开始时间、结束时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = null;
		Date endTime = null;
		String nowTime = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss");
		Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nowTime);
		
		if (StringUtil.isNull(model.getKssj()) && StringUtil.isNull(model.getJssj())){
			return OPEN.equals(model.getPjkg());
		} else if (!StringUtil.isNull(model.getKssj()) && !StringUtil.isNull(model.getJssj())){
			startTime=sdf.parse(model.getKssj()+":00");
			endTime=sdf.parse(model.getJssj()+":59");
			//当前时间早于结束时间 && 晚于开始时间  && 开关状态为开启
			return now.before(endTime) && now.after(startTime) && OPEN.equals(model.getPjkg());
		} else if (!StringUtil.isNull(model.getKssj())){
			startTime=sdf.parse(model.getKssj()+":00");
			return now.after(startTime);
		} else if (!StringUtil.isNull(model.getJssj())){
			endTime=sdf.parse(model.getJssj()+":59");
			return now.before(endTime);
		}
		return false;
	}
	
	/*
	      增加操作__返回一级项目List
	 */
	public List<HashMap<String, String>> getYjxmlist(String xn){
		ZcxmfzDao zcxmfzDao = new ZcxmfzDao();
		return zcxmfzDao.getYjxmlist(xn);
	}
	
	/** 
	 * @描述: 点击项目类型的等级按钮__增加数据保存
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-28 下午02:24:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param jxxxList
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveAddXmlxDj(CsszForm model, List<CsszForm> xmjxList) throws Exception{
		ZcxmdjDao zcxmdjdao = new ZcxmdjDao();
		ZcxmfzDao zcxmfzDao = new ZcxmfzDao();
		boolean result = true;
		//第一步，先插入综测项目、父级项目、项目类型 到xg_zjdx_pjpy_zcxmb中
		String xmdm = null;
		xmdm = UniqID.getInstance().getUniqIDHash();
		model.setXmdm(xmdm);
		model.setXn(dao.getModel().getXn());
		result = zcxmfzDao.runInsert(model);
		//第二步，判断类型为等级或分值
		if(null==xmjxList){
			return true;
		}
		List<String[]> jxxxList = new ArrayList<String[]>();
		String[] jxxx = null;
		for (CsszForm csszForm : xmjxList) {
			jxxx = new String[3];
			jxxx[0] = model.getXmdm();
			jxxx[1] = csszForm.getMc();
			jxxx[2] = csszForm.getPx();
			jxxxList.add(jxxx);
		}
		return zcxmdjdao.zcxmDjPlbc(jxxxList);
	}
	/*
	 * 删除综测项目表数据
	 */
	public int numDj(String[] values) throws Exception {
		return dao.numDj(values);
	}
	
	/*
	 * 删除综测项目选项表
	 */
	public int numFz(String[] values) throws Exception {
		return dao.numFz(values);
	}
	
	/*
	 * 增加保存验证综测项目表数据学年、项目名称不能重复
	 */
	public boolean isExistByZcxm (CsszForm model) throws Exception {
		String num = dao.isExistByZcxm(model);
		return Integer.valueOf(num) > 0;
	}
	
	/*
	 * 增加、修改保存验证checkBox的值是否有重复
	 */
	public boolean checkLxcf(List<CsszForm> xmjxList) {
		if(null==xmjxList){
			return false;
		}
		HashSet hashSet = new HashSet();
		for (CsszForm csszForm : xmjxList) {
			hashSet.add(csszForm.getMc());
		}
		if(!(hashSet.size()==xmjxList.size())){
			return true;
		}else{
			return false;
		}
	}
	
	/*
	 * 修改返回综测项目点击等级checkBox数据
	 */
	public List<HashMap<String, String>> getZcxmDjList(String xmdm) {
		return dao.getZcxmDjList(xmdm);
	}
	
	/*
	 * 修改返回页面__查找综测项目表数据，返回到model
	 */
	public HashMap<String, String> getZcxmDate(String xmdm) {
		return dao.getZcxmDate(xmdm);
	}
	
	/*
	 * 修改保存时，数据处理都是先删除、后插入
	 */
	public boolean saveUpdateXmlxDj(CsszForm model, List<CsszForm> xmjxList) throws Exception{
		ZcxmdjDao zcxmdjdao = new ZcxmdjDao();
		ZcxmfzDao zcxmfzDao = new ZcxmfzDao();
		boolean result = true;
		
		/*第1步：首先删除综测项目表的数据*/
		result = zcxmfzDao.delZcxmfz(model.getXmdm());
		
		/*第2步：插入综测项目表的综测项目、父级项目、项目类型字段*/
			/*1：如果点击等级，最小分值、最大分值、排序作为空插入表中*/
			/*2：如果点击分值，最小分值、最大分值、排序会插入表中*/
		result = zcxmfzDao.runInsert(model);
		
		/*第3步：再删除综测项目选项表的数据*/
		result = zcxmdjdao.delZcxmdj(model.getXmdm());
		
		/*第4步：点击等级，将会插入综测项目等级的checkBox的值*/
		/*判断checkBox的值是否为空*/
		if(null==xmjxList){
			return true;
		}
		/*插入checkBox的值*/
		List<String[]> jxxxList = new ArrayList<String[]>();
		String[] jxxx = null;
		for (CsszForm csszForm : xmjxList) {
			jxxx = new String[3];
			jxxx[0] = model.getXmdm();
			jxxx[1] = csszForm.getMc();
			jxxx[2] = csszForm.getPx();
			jxxxList.add(jxxx);
		}
		return zcxmdjdao.zcxmDjPlbc(jxxxList);
	}
	
	/*
	 * 选择周期__判断当前周期是否有综测记录
	 */
	public boolean getSfcz(){
		return dao.getSfcz();
	}
	
	/*评奖人员库执行初始化操作*/
	public void init() throws Exception {
		if(dao.initDel()){
			dao.init();
		}
	}
	
	/**
	 * @描述: 初始化综测项目结构
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-10 上午10:56:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pjzq
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void initZcxmList(String pjzq) throws Exception{
		String xn = null;
		
		if (!StringUtil.isNull(pjzq)){
			String[] zqArray = pjzq.split("#");
			xn = zqArray[0];
		}
		
		/*判断当前周期是否有综测结构，如果有 return;*/
		boolean isHave = !dao.getZcxmList(xn).isEmpty();
		if (isHave){
			return ;
		}
		
		/*判断系统中是否有初始过综测项目*/
		boolean isHaveZcxm = dao.getZcxmCount() > 0;
		/*如果有，拷贝上周期或最近*/
		if (isHaveZcxm){
			/*复制最近周期的综测结构*/
			copyZcxm(xn);
		}
	}
	
	/**
	 * @描述: 复制综测项目
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-11 上午11:57:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	private void copyZcxm(String xn) throws Exception{
		/*最近周期的综测项目结构*/
		List<HashMap<String,String>> zjzqZcxm = null;
		zjzqZcxm = dao.getZjzqZcxm();
		Map<String,List<HashMap<String,String>>>  resultMap = new HashMap<String, List<HashMap<String,String>>>();
		/*将最近周期的项目结构 封装成 key; 父级项目代码 value：子项目集*/
		for (HashMap<String,String> map : zjzqZcxm){
			String key = map.get("fjdm");
			List<HashMap<String,String>> list = resultMap.get(key);
			if (list == null){
				list = new ArrayList<HashMap<String,String>>();
			}
			list.add(map);
			resultMap.put(key, list);
		}
		/*构建新的项目结构*/
		UniqID uID = UniqID.getInstance();
		String zczfXmdm = uID.getUniqIDHash();
		HashMap<String,String> yjxm = resultMap.get("top").get(0);
		List<String[]> params = new ArrayList<String[]>();
		String[] zczf = new String[]{zczfXmdm,xn,yjxm.get("xmmc"),"top",yjxm.get("xmlx"),yjxm.get("px"),yjxm.get("zxfz"),yjxm.get("zdfz")};
		params.add(zczf);
		List<HashMap<String,String>> ejxmList = resultMap.get(yjxm.get("xmdm"));
		for (HashMap<String,String> ejxmMap : ejxmList){
			String ejxmdm = uID.getUniqIDHash();
			/*项目代码*/
			String[] ejxm = new String[]{ejxmdm,
										 xn,
										 ejxmMap.get("xmmc"),
										 zczfXmdm,ejxmMap.get("xmlx"),
										 ejxmMap.get("px"),
										 ejxmMap.get("zxfz"),
										 ejxmMap.get("zdfz")
									};
			params.add(ejxm);
		}
		dao.initZcxmList(params);
		/*获取最近综测周期*/
		String maxZczq = dao.maxZczq();
		/*构建综测项目选项表（等级） */
		dao.initZcxmxxList(xn,maxZczq);
	}
	
	/**
	 * @描述: 初始化参评组
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-11 下午03:36:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void initCpxz(User user) throws Exception{
		/*获取参数设置信息*/
		CsszDao cszzDao = new CsszDao();
		CsszForm csszForm = cszzDao.getModel();
		/*获取参数设置表中的当前周期（学年）*/
		String xn = csszForm.getXn();
		/*当前周期是否已经有分数提交记录表数据，如果有：return, 没有：初始化*/
		boolean isHaveFstjjl = dao.isHaveFstjjl(xn);
		if (isHaveFstjjl){
			return ;
		}
		/*初始化分数提交记录表,先清空当前周期的所有数据，再统一插入*/
		/*清空数据，学院用户只清理 用户所在学院数据*/
		dao.delFsTjjl(xn,user);
		/*插入数据*/
		dao.insertFstjjl(xn,user);
	}
	
	
	/**
	 * @描述: 获得参数配置
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-5 上午11:21:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param csdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCsz(String csdm){
		return dao.getCsz(csdm);
	}
	
	/**
	 * @描述: 检测综测项目是否被使用
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-7-24 上午11:19:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public String checkZcxmMade (String xn) throws Exception {
		return dao.checkZcxmMade(xn);
	}
	
	/**
	 * @描述: 获取参数设置信息表数据，以前调用的方法有点问题
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-12-6 下午02:39:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getCszzInfo() throws Exception{
		return dao.getCszzInfo();
	}
}
