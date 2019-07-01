/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 上午10:34:17 
 */
package com.zfsoft.xgxt.bzjl.wdbzjl.bzjljg;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsDao;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import common.GlobalsVariable;
import common.exception.SystemException;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xgxt.utils.date.MoneyFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 我的评奖-评奖结果
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-30 上午10:34:17
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class BzjljgService extends SuperServiceImpl<BzjljgModel, BzjljgDao> implements
		Constants {

	private BzjljgDao dao = new BzjljgDao();

	public BzjljgService() {
		super.setDao(dao);
	}

	/**
	 *
	 * @描述:获取同级专业人数
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-11-29 下午05:06:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 *            学号
	 * @param xn
	 *            学年
	 * @param xq
	 *            学期
	 * @return String 返回类型
	 * @throws
	 */
	public String getTjzyrs(String xh, String xn, String xq) {
		if (StringUtils.isNull(xh)) {
			return "";
		}

		return dao.getTjzyrs(xh, xn, xq);

	}

	public String getTbjrs(String xh) {
		if (StringUtils.isNull(xh)) {
			return "";
		}

		return dao.getTbjrs(xh);
	}

	/**
	 * 生成优秀奖学金（浙江大学）
	 */
	public boolean scyxjxj(User user) throws Exception {
		return dao.scyxjxj(user);
	}

	/**
	 * @描述:唯一性判断（学号，学年，学期，项目名称）
	 * @作者：cq [工号：785]
	 * @日期：2013-8-7 上午11:07:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param save
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean isExistByPjxmjg(BzjljgModel model, String type)
			throws Exception {
		if ("save".equalsIgnoreCase(type)) {
			String num = dao.checkExistForSave(model);
			return Integer.valueOf(num) > 0;
		} else {
			String num = dao.checkExistForUpdate(model);
			return Integer.valueOf(num) > 0;
		}

	}

	/**
	 *
	 * @描述:获取平均成绩
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-10 上午09:33:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return Integer 返回类型
	 */
	public String getAverage(List<HashMap<String, String>> list) {
		if (null == list || list.size() <= 0) {
			return "";
		}
		int kcs = 0;
		Float zfs = new Float(0);
		// 循环格式化数据
		for (HashMap<String, String> hm : list) {
			kcs++;
			String cj = hm.get("cj");
			if (StringUtils.isNotNull(cj)) {
				zfs += Float.parseFloat(cj);
			}
		}
		Float pjfs = zfs / kcs;
		return pjfs.toString();
	}

	/**
	 *
	 * @描述:格式化登记表数据
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-9 下午05:49:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> formatForDjb(
			List<HashMap<String, String>> list) {
		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();

		int cols = 3;// 列
		int row = 7;// 行
		int i = 0;// 列自增变量
		int j = 0;// 自增变量

		// 如果不足行数 补空行
		int m = list.size() - cols * row;
		if (m < 0) {
			for (int n = 0; n < Math.abs(m); n++) {
				HashMap<String, String> addMap = new HashMap<String, String>();
				addMap.put("kcmc", "");
				addMap.put("cj", "");
				list.add(addMap);
			}
		}
		// 循环格式化数据
		for (HashMap<String, String> hm : list) {
			i++;
			map.put("kcmc" + i, hm.get("kcmc"));// 存放每列的课程名称 对应key为 kcmc+自增i
			map.put("cj" + i, hm.get("cj"));// 存放每列的课程成绩 对应key为 cj+自增i
			// 整除
			if (i % cols == 0) { // cols列数为一行
				newList.add(map);
				i = 0;
				map = new HashMap<String, String>();
			}
			if (cols * row < j) {// 超过总行数跳出
				break;
			}
			j++;
		}
		return newList;
	}

	/**
	 * @描述:单个查看评奖
	 * @作者：cq [工号：785]
	 * @日期：2013-8-8 下午02:35:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return Object 返回类型
	 * @throws
	 */
	public Map<String, String> getOnePjxmjgList(String id) {

		return dao.getOnePjxmjgList(id);
	}

	// /**
	// * @描述:返回项目代码和名称List
	// * @作者：cq [工号：785]
	// * @日期：2013-8-14 上午09:36:20
	// * @修改记录: 修改者名字-修改日期-修改内容
	// * @return
	// * List<HashMap<String,String>> 返回类型
	// * @throws
	// */
	// public List<HashMap<String, String>> getxmmc() {
	//
	// return dao.getxmmc();
	// }

	/**
	 *
	 * @描述: 查询历年统过人数
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-14 上午09:19:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rskzfw
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getShtgrs(String rskzfw, String xmdm,
			String xn, String xq) {

		if (StringUtils.isNull(rskzfw)) {
			return null;
		}
		if (RSKZFW_NJXY.equals(rskzfw)) {
			return dao.getZzmeByNjxy(xmdm, xn, xq);
		} else if (RSKZFW_NJZY.equals(rskzfw)) {
			return dao.getZzmeByNjzy(xmdm, xn, xq);
		} else if (RSKZFW_XY.equals(rskzfw)) {
			return dao.getZzmeByXy(xmdm, xn, xq);
		} else if (RSKZFW_CPZ.equals(rskzfw)) {
			return dao.getZzmeByCpz(xmdm, xn, xq);
		} else if (RSKZFW_BJ.equals(rskzfw)) {
			return dao.getZzmeByBj(xmdm, xn, xq);
		} else {
			return dao.getZzmeByQx(xmdm, xn, xq);
		}
	}

	/**
	 * @描述:根据项目名称查询历年分配名额
	 * @作者：ligl
	 * @日期：2013-8-14 下午03:30:38
	 * @修改记录:
	 * @param xmmc
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> tjrs(String xmmc) throws Exception {
		if (xmmc == null) {
			logger.debug("参数不能为空！");
			throw new SystemException("参数不能为空！");
		} else {
			xmmc = xmmc.trim();
		}
		List<HashMap<String, String>> result = dao.tjrs(xmmc);
		return result;
	}

	/**
	 *
	 * @描述: 提供给学生信息显示评奖的接口
	 * @作者：屈朋辉[工号：445]
	 * @日期：2013-9-3 下午02:54:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return List<HashMap<String,Object>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, Object>> getPjpyInfo(String xh) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		String[] title = { "学年", "学期", "奖项", "奖项金额", "申请时间" };
		List<String[]> rs = new ArrayList<String[]>();
		rs.add(title);
		rs.addAll(dao.getHjqkByXh(xh));
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "获奖情况");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, rs);
		list.add(map);

		ZcfsDao zcfsDao = new ZcfsDao();
		String[] zcfTitle = { "学年", "学期", "综测项目", "综测分数", "参评组排名", "年级专业排名",
				"班级排名" };
		List<String[]> zcfRs = new ArrayList<String[]>();
		zcfRs.add(zcfTitle);
		zcfRs.addAll(zcfsDao.getZcfsByXh(xh));
		HashMap<String, Object> zcfMap = new HashMap<String, Object>();
		zcfMap.put(GlobalsVariable.XSXX_KTEYS_GNMK, "综测分数");
		zcfMap.put(GlobalsVariable.XSXX_KTEYS_CXJG, zcfRs);
		list.add(zcfMap);

		return list;
	}

	/**
	 * 计算平均分
	 * @param arr 全部数据
	 * @param xsnum 小数保留几位
	 */
	public String getPjf(String[] arr, int xsnum) {
		BigDecimal sum = new BigDecimal("0");
		for (String num : arr) {
			if(StringUtils.isNotNull(num)){
				sum = sum.add(new BigDecimal(num));
			}
		}
		return sum.divide(new BigDecimal(arr.length), xsnum, BigDecimal.ROUND_HALF_UP).toString();
	}
	/**
	 * 计算平均分
	 * @param list 全部数据
	 * @param xsnum 小数保留几位
	 */
	public String getPjf(List<HashMap<String, String>> list, int xsnum) {
		BigDecimal sum = new BigDecimal("0");
		int i = 0;
		for (HashMap<String, String> map : list) {
			if(map.get("kcmc") != null){
				if(map.get("cj") != null){
					sum = sum.add(new BigDecimal(map.get("cj")));
				}
				i++;
			}
		}
		if(i == 0){
			return "";
		}
		return sum.divide(new BigDecimal(i), xsnum, BigDecimal.ROUND_HALF_UP).toString();
	}

	/**
	 *
	 * @描述: 提供给学生信息显示评奖的接口
	 * @作者：cmj[工号：913]
	 * @日期：2013-9-3 下午02:54:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return List<HashMap<String,Object>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPjpyInfoMap(String xh) {
		return dao.getHjqkByXhMap(xh);
	}

	/**
	 *
	 * @描述:获取评奖结果list
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPjpyInfoList(String xh) {
		List<HashMap<String, String>> list = dao.getHjqkByXhMap(xh);
		return list;
	}
	public List<HashMap<String, String>> getPjpyInfoList(String xh,String xn) {
		List<HashMap<String, String>> list = dao.getHjqkByXhXnMap(xh,xn);
		return list;
	}

	/**
	 *
	 * @描述:提供给学生信息显示评奖的接口 用于下载登记表
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-22 下午01:47:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPjpyInfoMapForDjb(String xh) {
		List<HashMap<String, String>> list = dao.getHjqkByXhMap(xh);
		int ts;
		if (null == list) {
			ts = 0;
		}
		ts = 4 - list.size();
		for (int i = 0; i < ts; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("xmmc", "");
			list.add(map);
		}
		return list;
	}

	/**
	 * 空的list
	 */
	public void addBlankList(List<HashMap<String,String>> list, int blankSize){
		for (int i = 0 ; i < blankSize ; i++){
			list.add(new HashMap<String, String>());
		}
	}
	public HashMap<String,String> getDjbZcfListByXhXn(String xh, String xn, String xmmcLike){
		ZcfsService zcfService = new ZcfsService();
		HashMap<String,String> rs = new HashMap<String,String>();
		List<HashMap<String,String>> zcfList = zcfService.getZcfListByXnXh(xh, xn);
		String sxqdycj = ""; // 本学年上学期xmmcLike考核成绩
		String xxqdycj = ""; // 本学年下学期xmmcLike考核成绩
		String bxncjbjpm = ""; // 本学年成绩班级排名
		for (HashMap<String, String> zcfMap : zcfList) {
			String xmmc = zcfMap.get("xmmc").trim();
			String xq = zcfMap.get("xq").trim();
			String fjdm = zcfMap.get("fjdm").trim();
			if(xmmc.contains(xmmcLike)){
				if("01".equals(xq)){
					sxqdycj = zcfMap.get("fs");
				}else if("02".equals(xq)){
					xxqdycj = zcfMap.get("fs");
				}
			}
			if(CsszService.XQKG.equals(xq) && "N".equals(fjdm)){
				bxncjbjpm = zcfMap.get("bjpm");
			}
		}
		String xndypjcj = getPjf(new String[]{ sxqdycj, xxqdycj }, 2); // 本学年xmmcLike考核平均成绩
		rs.put("xndypjcj", xndypjcj);
		rs.put("sxqdycj", sxqdycj);
		rs.put("xxqdycj", xxqdycj);
		rs.put("bxncjbjpm", bxncjbjpm);
		return rs;
	}

	/**
	 * 根据学生查询学生获奖情况（根据学年分组）
	 */
	public List<HashMap<String, String>> getPjjgGroupByXn(String xh){
		return dao.getPjjgGroupByXn(xh);
	}

	/**
	 *
	 * @描述:根据学号查询获奖情况
	 * @作者：ligl
	 * @日期：2013-11-30 下午03:07:04
	 * @修改记录:
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getHjqkList(String xh) {
		return dao.getHjqkList(xh);
	}

	/**
	 *
	 * @描述:获取平均成绩
	 * @作者：HongLin[工号：707]
	 * @日期：2013-11-21 上午09:33:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return Integer 返回类型
	 */
	public String getBjgcjNum(String xh, String xn, String xq) {
		Map<String, String> map = dao.getBjgcjNum(xh, xn, xq);
		String bjgcjs = "";
		if (null != map && null != map.get("num")) {
			bjgcjs = map.get("num");
			if ("0".equals(bjgcjs)) {
				bjgcjs = "";
			}
		}
		return bjgcjs;
	}

	/**
	 *
	 * @描述:华中师范 大学个性化证书编码获取(证书编码规则：年度+项目代码+流水号：默认从0001开始(按年级、学院))
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-3 下午01:38:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return String 返回类型
	 * @throws
	 */
	public String getZsbm(BzjljgModel model) {
		StringBuffer zsbm = new StringBuffer();
		List<HashMap<String, String>> zsbmList = dao.getZsbm(model);
		if (zsbmList.size() > 0) {
			int zsbh=0;
			zsbh = Integer.parseInt(zsbmList.get(0).get("zsbm"))+1;
			StringBuffer zsbhstr=new StringBuffer();
			if(zsbh>99&&zsbh<1000){
				zsbhstr.append("0").append(zsbh);
			}
			else if(zsbh>9&&zsbh<100){
				zsbhstr.append("00").append(zsbh);
			}
			else{
				zsbhstr.append("000").append(zsbh);
			}
			zsbm.append(model.getXn()).append(model.getXmdm()).append(zsbhstr);
		} else {
			zsbm.append(model.getXn()).append(model.getXmdm()).append("0001");
		}
		return zsbm.toString();
	}
	/**
	 *
	 * @描述:根据项目名称获取项目代码
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-3 下午03:22:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * string 返回类型
	 * @throws
	 */
	public String getXmdm(BzjljgModel model){
		return dao.getXmdm(model);
	}

	/**
	 * @描述: 查询荣誉证书模板
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-21 上午10:13:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param csdm
	 * @param user
	 * @return
	 * HashMap 返回类型
	 */
	public HashMap<String, String> cxRyzs(String csdm, User user) throws Exception{
		return dao.cxRyzs(csdm, user);
	}

	/**
	 * @描述: 保存荣誉证书模板
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-21 上午10:13:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param csdm
	 * @param csz
	 * @param user
	 * @return
	 * boolean 返回类型
	 */
	public boolean bcRyzs(String csdm, String csz, User user) throws Exception{
		return dao.bcRyzs(csdm, csz, user);
	}

	/**
	 *
	 * @描述:根据学号查询奖项汇总
	 * @作者：cq [工号：785]
	 * @日期：2014-8-25 下午05:21:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * Map<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getJxSum (String xh){
		return dao.getJxSum(xh);
	}
	/**
	 * @描述: 浙江大学获奖情况总金额显示（个性化）
	 * @作者：孟威[工号：1186]
	 * @日期：2016-6-12 上午10:01:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getPjzje (String xh){
		return dao.getPjzje(xh);
	}
	/**
	 *
	 * @描述:获取班级信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-20 下午03:16:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getBjxx (String bjdm){
		return dao.getBjxx(bjdm);
	}


	/**
	 * @throws Exception
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：cq [工号：785]
	 * @日期：2015-5-22 下午04:05:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * List<File> 返回类型
	 * @throws
	 */
	public List<File> getRyzsJxjFile_10264(String[] values) throws Exception {

		List<File> files = new ArrayList<File>();

		//查询结果
		List<HashMap<String, String>> priJgList = dao.getPriJg(values);
		List<HashMap<String, String>> priJgFzList =null;
		Map<String,List<HashMap<String, String>>> priJgMap = new HashMap<String,List<HashMap<String, String>>>();
		for (HashMap<String, String> hashMap : priJgList) {
			if(priJgMap.containsKey(hashMap.get("xh"))){
				priJgMap.get(hashMap.get("xh")).add(hashMap);
			}else{
				priJgFzList= new ArrayList<HashMap<String, String>>();
				priJgFzList.add(hashMap);
				priJgMap.put(hashMap.get("xh"), priJgFzList);
			}
		}

		for (Map.Entry<String, List<HashMap<String, String>>> entry :priJgMap.entrySet()) {
			File file=getJgFile_10264(entry.getKey(),entry.getValue());

			files.add(file);
		}

		return files;

	}



	/**
	 *
	 * @描述:海洋个性化打印
	 * @作者：cq [工号：785]
	 * @日期：2015-5-22 下午04:46:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param xmmc
	 * @return
	 * @throws Exception
	 * File 返回类型
	 * @throws
	 */
	private File getJgFile_10264(String id, List<HashMap<String, String>> xmList)
			throws Exception {

		Map<String,Object> data = new HashMap<String,Object>();
		data.put("mxMap", xmList.get(0));
		data.put("xmList", xmList);
		data.put("date", DateUtils.getLyr());

		File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//pjpy", "zm_10264.xml", "证明" +"["+id+" "+xmList.get(0).get("xm")+"]" );
		return wordFile;

		}

	/**
	 *
	 * @描述:山东畜牧兽医职业学院【优秀毕业生推荐表】获学生基本信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-6-4 下午06:12:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	private List<HashMap<String, String>> getXsxxlist_12947(String[] values){
		return dao.getXsxxlist(values);
	}

	//山东畜牧兽医职业学院个性化打印(社会奖学金汇总表)
	public File getXmGxhDy_12947_shjxjhzexcel(String[] parameters)
		throws Exception {
	Map<String,Object> data = new HashMap<String,Object>();
	List<HashMap<String, String>> xsxxlist = dao.getShjxjHzbxxList(parameters);
	HashMap<String, String> totalxx = dao.getshjxjTotal(parameters);
	data.put("xsxxlist",xsxxlist);
	data.putAll(totalxx);
	File excelFile = FreeMarkerUtil.getExcelFile(data,"classpath://templates//pjpy//excel", "sdxmsy_12947_shjxjhzb.xml", "社会奖学金学生汇总表");
	return excelFile;

    }

	//山东畜牧兽医职业学院个性化打印(省励志奖学金汇总表)
	public File getXmGxhDy_12947_slzjhzexcel(String[] parameters,User user)
		throws Exception {
	Map<String,Object> data = new HashMap<String,Object>();
	List<HashMap<String, String>> xsxxlist = dao.getShjxjHzbxxList(parameters);
	data.put("xsxxlist",xsxxlist);
	String jbr = user.getRealName();
	String username = user.getUserName();
	String lxdh = (dao.getlxfs(username)).get("lxdh");
    data.put("jbr", jbr);
    data.put("lxdh", lxdh);
    data.put("xx",Base.xxmc);
    SimpleDateFormat datenow = new SimpleDateFormat("yyyy-MM-dd");
    String date = datenow.format(new Date());
    String year = date.substring(0, 4);
    String month = date.substring(5, 7);
    String day = date.substring(8, 10);
    data.put("year", year);
    data.put("month", month);
    data.put("day", day);
    data.put("xn", Base.currXn);
	File excelFile = FreeMarkerUtil.getExcelFile(data,"classpath://templates//pjpy//excel", "sdxmsy_12947_slzjhz.xml", "省励志奖学金学生汇总表");
	return excelFile;

    }
	/**
	 *
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-17 上午08:41:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getSpxxInfo(String guid){
		if(StringUtils.isNull(guid)){
			return null;
		}
		return dao.getSpxxInfo(guid);
	}

	public String getPjxxByXhXnXq(String xh,String xn,String xq){
		return dao.getPjxxByXhXnXq(xh, xn, xq);
	}


	/**
	 * 传媒个性化 取学生必修课情况
	 */

	public HashMap<String, String> getBxk(String xh,String xn){
		return dao.getBxk(xh, xn);
	}

	public HashMap<String, String> getPm(String xh,String xn){
		return dao.getPm(xh, xn);
	}
	public HashMap<String, String> getCjPm(String xh,String xn){
		return dao.getCjPm(xh, xn);
	}


	/**
	 *
	 * @描述: 评奖信息单独查询
	 * @作者：cq [工号：785]
	 * @日期：2015-11-20 下午04:05:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getPjjgInfo(String xh, String xn, String xq){
		return dao.getPjjgInfo(xh, xn, xq);
	}


	/**
	 *
	 * @描述:結果增加學生
	 * @作者：cq [工号：785]
	 * @日期：2015-11-20 下午06:14:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型
	 * @throws
	 */

	public List<HashMap<String, String>> getZjXs(BzjljgModel model, User user) throws Exception{
		return dao.getZjXs(model, user);
	}

	/**
	 *
	 * @描述:根据学号查询老版评奖信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-1-18 上午11:09:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getHjqkListOld(String xh) {
		return dao.getHjqkListOld(xh);
	}

	/**
	 *
	 * @描述:德育等第信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-1-21 上午10:15:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getDyddList(String xh) {
		return dao.getDyddList(xh);
	}

	/**
	 *
	 * @描述: 所有审核级别审核意见汇总
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-16 上午08:36:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @param xmmc
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getAllShyjList(String xh, String xn, String xq, String xmmc) {
		return dao.getAllShyjList(xh, xn, xq, xmmc);
	}
	/**
	 * @描述: 北京林业大学学生获奖数据
	 * @作者：孟威[工号：1186]
	 * @日期：2016-5-11 下午09:30:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPjjgList(String xh,String xn) {
		return dao.getPjjgList(xh,xn);
	}

	/**
	 *
	 * @描述: 取该学生评奖所有奖项（湘潭等级报表需要）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-5-27 上午09:22:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * String 返回类型
	 * @throws
	 */
	public String getXmmcAllByPjjg(String xh) throws Exception{
		return dao.getXmmcAllByPjjg(xh);
	}

	/**
	 *
	 * @描述: 必修课成绩班级排名平均分
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-6-29 下午03:35:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getPjfRank(BzjljgModel model, String xq, String kcxz){
		return dao.getPjfRank(model, xq, kcxz);
	}

	/**
	 *
	 * @描述:综测平均成绩排名
	 * @作者：张昌路[工号：982]
	 * @日期：2015-6-29 下午04:55:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param xq
	 * @param kcxz
	 * @return
	 * HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getZcPjfRank(BzjljgModel model){
		HashMap<String, String> map01 =  dao.getZcPjfRank(model, "01");
		HashMap<String, String> map02 =  dao.getZcPjfRank(model, "02");
		map01.put("zcpjf02", map02.get("zcpjf"));
		map01.put("zcpm02", map02.get("zcpm"));
		return map01;
	}

	/**
	 *
	 * @描述:获取学生四六级考试map
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-6-29 下午05:14:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public HashMap<String,String> getDjksMap(String xh){
		HashMap<String, String> mapcet4 =  dao.getDjksMap(xh, "CET4","四级");
		HashMap<String, String> mapcet6 =  dao.getDjksMap(xh, "CET6","六级");
		mapcet4.put("djksmc6", mapcet6.get("djksmc"));
		mapcet4.put("cj6", mapcet6.get("cj"));
		mapcet4.put("sj6", mapcet6.get("sj"));
		return mapcet4;
	}

	/**
	 *
	 * @描述:根据学年取出该学生在该学年的获奖情况
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-6-30 上午10:32:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPjjgByxn(BzjljgModel model){
		return dao.getPjjgByxn(model);
	}

	/**
	 *
	 * @描述:cjb学年学期list
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-6-30 下午04:23:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param lx
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXnXqlist(String xh,String lx){
		return dao.getXnXqlist(xh,lx);
	}

	public List<HashMap<String, String>> getJsjdjkslist(String xh,String ksmc,String ksmc1){
		return dao.getJsjdjkslist(xh, ksmc, ksmc1);
	}

	/**
	 *
	 * @描述:获取某奖项获奖次数及获奖时间
	 * @作者：张昌路[工号：982]
	 * @日期：2015-6-30 下午04:53:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param ksmc
	 * @param ksmc1
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>>getHjrycssj(String xh,String xmmc){
		return dao.getHjrycssj(xh, xmmc);
	}

	/**
	 *
	 * @描述:必修课平均成绩排名
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-6-29 下午03:36:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getZyPjfRank(BzjljgModel model, String kcxz){
		return dao.getZyPjfRank(model, kcxz);
	}

	/**
	 *
	 * @描述:获取专业人数
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-1 下午02:55:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zydm
	 * @return
	 * HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getZyRs(String zydm){
		return dao.getZyRs(zydm);
	}

	public List<HashMap<String, String> > getzzxmjg(String xh,String xn){
		return dao.getzzxmjg(xh, xn);
	}

	/**
	 *
	 * @描述:获取奖项申请次数
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-2 下午03:11:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getSqcs(BzjljgModel model){
		return dao.getSqcs(model);
	}

	/**
	 *
	 * @描述:获取奖项获得次数
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-2 下午03:18:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getJxcs(BzjljgModel model){
		return dao.getJxcs(model);
	}

	public List<HashMap<String, String>> getXnList(String xh){
		return dao.getXnList(xh);
	}

	/**
	 *
	 * @描述:必修课平均成绩排名
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-3  下午03:36:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getNjPjfRank(BzjljgModel model, String nj, String kcxz){
		return dao.getNjPjfRank(model, nj, kcxz);
	}

	/**
	 *
	 * @描述:获取年级人数
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-3 下午02:55:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zydm
	 * @return
	 * HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getNjRs(String nj){
		return dao.getNjRs(nj);
	}

	/*
	 * @描述: 华中农业评奖取最新四条
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2016-10-26 上午10:00:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getHznydxPjpyMap(String xh) {
		return dao.getHznydxPjpyMap(xh);
	}

	/**
	 * @描述：通用sql方法，取最新n条获奖信息，不足n条填空
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月6日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param n
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getHjqkByXhMap(String xh,int n) {
		List<HashMap<String, String>> list = dao.getHjqkByXhMap(xh,String.valueOf(n));
		int m=n-list.size();
		for (int i = 0; i <m; i++) {
			list.add(new HashMap<String, String>());
		}
		return list;
	}

	/**
	 * @描述：导出汇总表
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年3月24日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * File 返回类型
	 */
	public File getHzbPrint(SearchModel searchmodel)throws Exception {
		//有且必须有一个xn和一个xmmc
		String[] xns=searchmodel.getSearch_tj_xn();
		String[] xmmcs=searchmodel.getSearch_tj_xmmc();
		String xn=(xns==null?"":xns[0]);
		String xmmc=(xmmcs==null?"":xmmcs[0]);
		List<HashMap<String,String>> wshlist=dao.getPjjgList_10279(xn,xmmc);
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("wshlist", wshlist);
		data.put("xn", xn);
		data.put("xmmc", xmmc);
		File excelFile = FreeMarkerUtil.getExcelFile(data,"classpath://templates//pjpy//excel", "pjpy_hzb_"+"10279"+".xml", xn+"学年汇总表");
		return excelFile;
	}

	public List<HashMap<String, String>> getPjjghzList(BzjljgModel model, User user)  throws Exception{
		return dao.getPjjghzList(model,user);
	}



	public List<HashMap<String, String>> getPjjghzMdList(BzjljgModel model)  throws Exception{
		return dao.getPjjghzMdList(model);
	}
	/**
	 * @描述：通用sql方法，取出4条获奖信息
	 * @作者：姜舟[工号:1529]
	 * @日期：2017年9月21日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param n
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 * */
	//国家奖学金
	public HashMap<String, String> getZjlyByXhMap(String xh,String xn) {
		return dao.getZjlyByXhMap(xh,xn);
	}
	//取排名
	public HashMap<String, String> getZjlyByPm(String xh,String xn){
		return dao.getZjlyByPm(xh, xn);
	}
	//励志奖学金
	public HashMap<String, String> getZjlylzByXhMap(String xh,String xn){
		return dao.getZjlylzByXhMap(xh, xn);
	}
	//取学习成绩
	public HashMap<String, String> getZjlyXxqkCj(String xh,String xn){
		return dao.getZjlyXxqkCj(xh, xn);
	}
	//浙江省政府奖学金
	public HashMap<String, String> getZjszfByXhMap(String xh,String xn){
		return dao.getZjszfByXhMap(xh, xn);
	}
	//浙江旅游职业学院奖学金
	public HashMap<String, String> getZjlyzyxyfByXhMap(String xh,String xn){
		return dao.getZjlyzyxyfByXhMap(xh, xn);
	}
	//浙江省优秀毕业生
	public HashMap<String, String> getZjlySjyxbys(String xh,String xn){
		return dao.getZjlySjyxbys(xh, xn);
	}
	//浙江旅游学院优秀毕业生
	public HashMap<String, String> getZjlyxyyxbys(String xh,String xn){
		boolean flag = dao.isExist(xh,xn);
		if (flag) {
			return dao.getZjlyxyyxbys(xh, xn);
		}else {
			return dao.getZjlySjyxbys(xh, xn);
		}

	}

	//浙江中医药 获得奖学金等级
    public String getJxjmcByXhXn(String xh, String xn) {
		return dao.getJxjmcByXhXn(xh,xn);
    }

    /**
	 * @描述: 青岛滨海学院个性化取-学年综合成绩
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-16 下午06:25:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getXnzhcj(String xh,String xn){
		return dao.getXnzhcj(xh,xn);
	}

	/**
	 * @描述: 根据学号取当前评奖周期的参评班级人数
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-18 下午07:05:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * String 返回类型
	 * @throws
	 */
	public String getXsszcpbjRsForxh (String xh,String xn){
		return dao.getXsszcpbjRsForxh(xh,xn);
	}

	/**
	 * @描述: 算表格中出的金额总数
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-11-3 下午08:41:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * String 返回类型
	 * @throws
	 */
	public String getExcleZje (BzjljgModel model) throws Exception{
		return dao.getExcleZje(model);
	}
	/**
	 * @描述：通用sql方法，去除优良中等及格课程门数
	 * @作者：姜舟[工号:1529]
	 * @日期：2017年11月9日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param n
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 * */
	//西安科技大学本学年获奖情况
	public List<HashMap<String, String>> getHjqk(String xh,String xn){
		return dao.getHjqk(xh, xn);
	}
	//西安科技大学，优良中及以及必修课门数
	public HashMap<String,String> getXakjdxylzjbxkms(String xh,String xn){
		return dao.getXakjdxylzjbxkms(xh, xn);
	}
	/**
	 *
	 * @描述:获取成绩List
	 * @作者：姜舟[工号：1529]
	 * @日期：2017-11-17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getCjsxqList(String xn, String xh){
		return dao.getCjsxqList(xn,xh);
	}
	public List<HashMap<String, String>> getCjxsqList(String xn, String xh){
		return dao.getCjxsqList(xn,xh);
	}
	//徐州医药
	public String getkssj (String xh) throws Exception{
		return dao.getkssj(xh);
	}
	public String gettyrs (String cpbjdm) throws Exception{
		return dao.gettyrs(cpbjdm);
	}

	public List<HashMap<String, String>> getHjqkInfoMap(String xh) {
		return dao.getHjqkInfoMap(xh);
	}
	public HashMap<String,String> getCjfsList(String xh, String xn){
		return dao.getCjfsList(xh,xn);
	}
	//重庆工商大学
	public List<HashMap<String, String>> getShrList(String xh, String xn, String xq, String xmdm) {
		return dao.getShrList(xh, xn, xq, xmdm);
	}

	/**
	 * @description	： 获取表彰名单word
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-12-27 下午02:54:07
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public File createBzmdWord(BzjljgModel model) throws Exception{
		String path = System.getProperty("java.io.tmpdir");

		File file = new File(path+File.separator+"表彰名单.doc");

		Calendar calendar = Calendar.getInstance();

		int year = calendar.get(Calendar.YEAR);

		int month = calendar.get(Calendar.MONTH) + 1;


		if(!file.exists()){
			file.createNewFile();
		}

		//获取全部表彰名单列表
		List<HashMap<String,String>> bzmdList = dao.getBzmdList(model);
		//获取学院获奖人数列表
		List<HashMap<String,String>> xyhjrsList = dao.getXyhjrsList(model);
		//获取班级获奖人数列表
		List<HashMap<String,String>> bjhjrsList = dao.getBjhjrsList(model);

		if(bzmdList != null && bzmdList.size() > 0){
			String xn = xyhjrsList.get(0).get("xn");
			String xqmc = xyhjrsList.get(0).get("xqmc");

			List<HashMap<String,Object>> list = getBzmdByXy(xyhjrsList, bjhjrsList);
			Document document = new Document(PageSize.A4);
			RtfWriter2.getInstance(document, new FileOutputStream(file));
			//设置中文字体
			BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			//设置标题字体
			Font titleFont = new Font(bfChinese,32,Font.BOLD);
			//设置学校名称字体
			Font xxmcFont = new Font(bfChinese,20,Font.BOLD);
			//设置正文字体
			Font bodyFont = new Font(bfChinese,14,Font.NORMAL);

			document.open();

			Paragraph blankParagraph = new Paragraph("");

			document.add(blankParagraph);

			//设置学校名
			Paragraph xxParagraph = new Paragraph(Base.xxmc, xxmcFont);

			xxParagraph.setAlignment(Element.ALIGN_CENTER);

			document.add(xxParagraph);

			document.add(blankParagraph);

			Paragraph xnxqParagraph = new Paragraph(xn+" 学年"+xqmc,titleFont);

			xnxqParagraph.setAlignment(Element.ALIGN_CENTER);

			document.add(xnxqParagraph);

			document.add(blankParagraph);

			Paragraph bzmdParagraph_1 = new Paragraph("表",titleFont);

			bzmdParagraph_1.setAlignment(Element.ALIGN_CENTER);

			document.add(bzmdParagraph_1);

			document.add(blankParagraph);

			Paragraph bzmdParagraph_2 = new Paragraph("彰",titleFont);

			bzmdParagraph_2.setAlignment(Element.ALIGN_CENTER);

			document.add(bzmdParagraph_2);

			document.add(blankParagraph);

			Paragraph bzmdParagraph_3 = new Paragraph("名",titleFont);

			bzmdParagraph_3.setAlignment(Element.ALIGN_CENTER);

			document.add(bzmdParagraph_3);

			document.add(blankParagraph);

			Paragraph bzmdParagraph_4 = new Paragraph("单",titleFont);

			bzmdParagraph_4.setAlignment(Element.ALIGN_CENTER);

			document.add(bzmdParagraph_4);


			if(list.size() > 1){
				document.add(blankParagraph);
				document.add(blankParagraph);
				document.add(blankParagraph);
				Paragraph bzmdParagraph_date = new Paragraph(year+"年"+month+"月",xxmcFont);
				bzmdParagraph_date.setAlignment(Element.ALIGN_CENTER);
				document.add(bzmdParagraph_date);
			}

			document.add(blankParagraph);



			for(int i = 0;i < 3;i++){
				document.add(blankParagraph);
			}

			if(list.size() == 1){
				String xymc = (String) list.get(0).get("xymc");
				Paragraph xymc_paragraph = new Paragraph(xymc,xxmcFont);
				xymc_paragraph.setAlignment(Element.ALIGN_CENTER);
				xymc_paragraph.setSpacingAfter(12);
				document.add(xymc_paragraph);

				//document.add(blankParagraph);
				Paragraph bzmdParagraph_date = new Paragraph(year+"年"+month+"月",xxmcFont);
				bzmdParagraph_date.setAlignment(Element.ALIGN_CENTER);
				document.add(bzmdParagraph_date);
			}

			Font hzFont = new Font(bfChinese,26,Font.BOLD);

			for(int i = 0;i < list.size();i++){
				//每个学院，另起一页
				document.newPage();

				//输出学院表头
				HashMap<String,Object> map = list.get(i);
				String xymc = (String) map.get("xymc");
				Paragraph xyPara = new Paragraph(xymc + "表彰学生汇总",hzFont);
				xyPara.setAlignment(Element.ALIGN_CENTER);
				xyPara.setSpacingAfter(12);
				document.add(xyPara);

				//输出奖项列表
				List<HashMap<String,String>> jxList = (List<HashMap<String, String>>) map.get("xyhjList");

				//List<HashMap<String,String>> bjhjList = (List<HashMap<String, String>>) map.get("bjhjList");

				for(HashMap<String,String> mapp : jxList){
					Paragraph jxParagraph = new Paragraph(mapp.get("xmmc")+":"+mapp.get("rs")+"人",xxmcFont);
					jxParagraph.setIndentationLeft(5f);
					jxParagraph.setSpacingAfter(8);
					jxParagraph.setSpacingBefore(8);
					document.add(jxParagraph);
				}

				document.add(blankParagraph);

				Font topicFont = new Font(bfChinese,16,Font.BOLD);

				Paragraph topicXnXqPara = new Paragraph(xn.replaceAll("-", "/") + "学年" + xqmc,topicFont);
				topicXnXqPara.setAlignment(Element.ALIGN_CENTER);
				topicXnXqPara.setSpacingAfter(8);
				topicXnXqPara.setSpacingBefore(8);
				document.add(topicXnXqPara);



				Paragraph xytjPara = new Paragraph(xymc + "学生各类表彰统计名单",topicFont);
				xytjPara.setAlignment(Element.ALIGN_CENTER);
				xytjPara.setSpacingAfter(8);
				xytjPara.setSpacingBefore(8);
				document.add(xytjPara);
				//document.add(blankParagraph);

				for(HashMap<String,String> mapp : jxList){
					document.add(blankParagraph);
					String xmmc = mapp.get("xmmc");
					Paragraph jxParagraph = new Paragraph(xmmc+" ("+mapp.get("rs")+"人)：",bodyFont);
					jxParagraph.setIndentationLeft(5f);
					jxParagraph.setSpacingBefore(4);
					jxParagraph.setSpacingAfter(4);
					document.add(jxParagraph);

					Iterator<HashMap<String,String>> bjIt = bzmdList.iterator();

					StringBuilder md = new StringBuilder();
					String bjmc = new String();
					int num = 0;
					List<HashMap<String,String>> bdList = new ArrayList<HashMap<String,String>>();
					while(bjIt.hasNext()){
						HashMap<String,String> mdMap = (HashMap<String, String>) bjIt.next();
						if(xymc.equals(mdMap.get("xymc"))){
							if(xmmc.equals(mdMap.get("xmmc"))){
								if(StringUtils.isNull(bjmc)){
									bjmc = mdMap.get("bjmc");
									if(bjmc.equals(mdMap.get("bjmc"))){
										md.append(mdMap.get("xm"));
										md.append(" ");
										num++;
										bjIt.remove();
									}else{
										bdList.add(mdMap);
										bjIt.remove();
										continue;
									}
								}else{
									if(!bjmc.equals(mdMap.get("bjmc"))){
										bdList.add(mdMap);
										bjIt.remove();
										continue;
									}else{
										md.append(mdMap.get("xm"));
										md.append(" ");
										num++;
										bjIt.remove();
									}
								}
							}else{
								break;
							}
						}else{
							break;
						}
					}
					//添加班级名单
					Paragraph bdParagraph = new Paragraph(bjmc+": "+md.toString()+" "+num,bodyFont);
					bdParagraph.setIndentationLeft(5f);
					bdParagraph.setSpacingBefore(4);
					bdParagraph.setSpacingAfter(4);
					document.add(bdParagraph);

					while(bdList.size() > 0){
						num = 0;
						md = new StringBuilder();
						while(bdList.size() > 0){
							Iterator<HashMap<String,String>> it = bdList.iterator();
							HashMap<String,String> mdMap = (HashMap<String, String>) it.next();
							bjmc = new String();
							if(StringUtils.isNull(bjmc)){
								bjmc = mdMap.get("bjmc");
								if(bjmc.equals(mdMap.get("bjmc"))){
									md.append(mdMap.get("xm"));
									md.append(" ");
									num++;
									it.remove();
								}else{
									break;
								}
							}
						}
						//添加班级名单
						Paragraph bddParagraph = new Paragraph(bjmc+": "+md.toString()+" "+num,bodyFont);
						bddParagraph.setIndentationLeft(5f);
						bddParagraph.setSpacingBefore(4);
						bddParagraph.setSpacingAfter(4);
						document.add(bddParagraph);
					}
				}
			}

			document.close();
		}
		return file;
	}

	/**
	 * @description	：获取表彰名单（根据学院和奖项分类）
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-12-28 上午09:28:35
	 * @param bzmdList
	 * @param xyhjrsList
	 * @param bjhjrsList
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,Object>> getBzmdByXy(List<HashMap<String,String>> xyhjrsList,List<HashMap<String,String>> bjhjrsList) throws Exception{
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> map = new HashMap<String, Object>();
		List<HashMap<String, String>> xyhjlist = new ArrayList<HashMap<String,String>>();

		for(int i = xyhjrsList.size();i>0;){
			String xydm = null;
			String xymc = null;
			//String bj_xmmc = null;
			//String bjmc = null;
			//每次循环开始重新new一个学院获奖map
			map = new HashMap<String, Object>();
			//每次循环开始重新new学院获奖详细列表列表
			xyhjlist = new ArrayList<HashMap<String,String>>();
			Iterator<HashMap<String,String>> xyit = xyhjrsList.iterator();
			while(xyit.hasNext()){
				HashMap<String,String> xyMap = xyit.next();
				if(xydm == null){
					xydm = xyMap.get("xydm");
					xymc = xyMap.get("xymc");
					map.put("xymc", xymc);
				}
				if(xydm.equals(xyMap.get("xydm"))){
					//取得学院的奖项名称
					String xmmc = xyMap.get("xmmc");
					//取得学院的获奖人数
					String rs = xyMap.get("xyhjrs");
					HashMap<String,String> mapp = new HashMap<String, String>();
					//放入奖项名称
					mapp.put("xmmc", xmmc);
					//放入奖项对应的获奖人数
					mapp.put("rs", rs);
					xyhjlist.add(mapp);
					xyit.remove();
					i--;
				}
			}
			map.put("xyhjList", xyhjlist);
			list.add(map);
		}
		return list;

	}

	/**
	 * @description	： 能否导出表彰名单
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-4 下午02:25:35
	 * @return
	 * @throws Exception
	 */
	public boolean isExportBzMd(BzjljgModel model) throws Exception{
		List<HashMap<String,String>> bzmdList = dao.getBzmdList(model);
		return null != bzmdList && bzmdList.size() > 0 ? true:false;
	}

	/**
	 *
	 * @描述: 奖学金汇总账号
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-2-24 下午04:02:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * File 返回类型
	 * @throws
	 */
	public File getJxjhzzhList(BzjljgModel model, User user) throws Exception{

		String path = System.getProperty("java.io.tmpdir");
		String[] tj_xn = model.getSearchModel().getSearch_tj_xn();
		String[] tj_xq = model.getSearchModel().getSearch_tj_xq();
		String xqmc = Base.getXqmcForXqdm(tj_xq[0]);
		File file = new File(path+File.separator+"奖学金账号汇总表.xls");

		if(!file.exists()){
			file.createNewFile();
		}
		WritableWorkbook  wwb = Workbook.createWorkbook(file);
		  // 创建工作表
        WritableSheet ws = wwb.createSheet("奖学金账号汇总表", 0);
        //表头
        WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 13,
                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
                jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
        WritableCellFormat wcf_title1 = new WritableCellFormat(wf_title); // 单元格定义
        wcf_title1.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
        wcf_title1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置
        Label labelTitle= new Label(0, 0, tj_xn[0]+"学年"+xqmc+"奖学金账号汇总表",wcf_title1);
        ws.addCell(labelTitle);
        //合并表头
        ws.mergeCells(0, 0, 8, 0);

        //表格字体
        WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 11,
        WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
        jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
        WritableCellFormat wcf_table = new WritableCellFormat(wf_table); // 单元格定义
        wcf_table.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
        wcf_table.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置

        Label labelXuh = new Label(0,1,"序号",wcf_table);
        Label labelXh = new Label(1, 1,"学号",wcf_table);
        Label labelSfzh = new Label(2, 1,"身份证号",wcf_table);
        Label labelXm = new Label(3, 1,"姓名",wcf_table);
        Label labelBj = new Label(4, 1,"班级",wcf_table);
        Label labelHjdj = new Label(5, 1,"获奖等级",wcf_table);
        Label labelJe = new Label(6, 1,"金额",wcf_table);
        Label labelBz = new Label(7, 1,"备注",wcf_table);
        Label labelQr = new Label(8, 1,"确认",wcf_table);

        ws.addCell(labelXuh);
        ws.addCell(labelXh);
        ws.addCell(labelSfzh);
        ws.addCell(labelXm);
        ws.addCell(labelBj);
        ws.addCell(labelHjdj);
        ws.addCell(labelJe);
        ws.addCell(labelBz);
        ws.addCell(labelQr);

        // 设置列的宽度
        ws.setColumnView(0, 8);
        ws.setColumnView(1, 15);
        ws.setColumnView(2, 25);
        ws.setColumnView(3, 10);
        ws.setColumnView(4, 15);
        ws.setColumnView(5, 15);
        ws.setColumnView(6, 10);
        ws.setColumnView(7, 10);
        ws.setColumnView(8, 10);

      //获取数据
		List<HashMap<String,String>> pjjgList = dao.getJxjhzzhList(model, user);
		if(pjjgList != null && !pjjgList.isEmpty() && pjjgList.get(0) !=  null){
			for (int i = 0; i < pjjgList.size(); i++) {
				HashMap<String,String> tempMap = pjjgList.get(i);
				Label labelTemp0 = new Label(0, i+2,tempMap.get("rn"),wcf_table);
				Label labelTemp1 = new Label(1, i+2,tempMap.get("xh"),wcf_table);
				Label labelTemp2 = new Label(2, i+2,tempMap.get("sfzh"),wcf_table);
				Label labelTemp3 = new Label(3, i+2,tempMap.get("xm"),wcf_table);
				Label labelTemp4 = new Label(4, i+2,tempMap.get("bjmc"),wcf_table);
				Label labelTemp5 = new Label(5, i+2,tempMap.get("xmmc"),wcf_table);
				Label labelTemp6 = new Label(6, i+2,tempMap.get("xmje"),wcf_table);
				Label labelTemp7 = new Label(7, i+2,"",wcf_table);//tempMap.get("sqly")
				Label labelTemp8= new Label(8, i+2,"",wcf_table);
				ws.addCell(labelTemp0);
			    ws.addCell(labelTemp1);
			    ws.addCell(labelTemp2);
			    ws.addCell(labelTemp3);
			    ws.addCell(labelTemp4);
			    ws.addCell(labelTemp5);
			    ws.addCell(labelTemp6);
			    ws.addCell(labelTemp7);
			    ws.addCell(labelTemp8);
			}

		}
		  wwb.write();
		  wwb.close();
		  return file;

	}

	/**
	 * @throws Exception
	 * @throws IOException
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-3-9 下午02:27:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param resultList
	 * @return
	 * File 返回类型
	 * @throws
	 */
	public File getCwybFile(List<HashMap<String, String>> resultList,HashMap<String, String> hashMap) throws IOException, Exception {
		//画Excel
		String fileName = System.currentTimeMillis() + ".xls";
		File file = new File(System.getProperty("java.io.tmpdir"),fileName);


		if(!file.exists()){
			file.createNewFile();
		}

		//创建工作簿
		WritableWorkbook  wwb = Workbook.createWorkbook(file);

		//设置相关格式
		WritableFont tileFont = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//设置标题字体
		WritableFont headFont = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//设置表头字体
		WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//设置正文字体


		WritableCellFormat title_cf = new WritableCellFormat(tileFont);//设置标题单元格字体
		WritableCellFormat head_cf = new WritableCellFormat(headFont);//设置正文表头字体
		WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//设置正文单元格字体
		WritableCellFormat body_bz_cf = new WritableCellFormat(bodyFont);//设置正文单元格字体
		WritableCellFormat body_sp_cf = new WritableCellFormat(bodyFont);//设置正文单元格（审批）字体
		WritableCellFormat body_hj_cf = new WritableCellFormat(headFont);//设置正文单元格（合计）字体
		WritableCellFormat body_rq_cf = new WritableCellFormat(bodyFont);//设置正文单元格（日期）字体
		body_cf.setWrap(true);
		body_rq_cf.setAlignment(jxl.format.Alignment.LEFT);
		title_cf.setAlignment(jxl.format.Alignment.CENTRE);//设置标题单元格对齐
		title_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//		title_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//设置标题单元格边框
//		title_cf.setBackground(Colour.YELLOW);	//设置标题背景色
		body_hj_cf.setAlignment(jxl.format.Alignment.LEFT);
		body_hj_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//设置垂直对齐
		body_hj_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		head_cf.setAlignment(jxl.format.Alignment.CENTRE);
		head_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//设置表头水平对齐
		head_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		body_sp_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//设置水平对齐
		body_sp_cf.setAlignment(jxl.format.Alignment.CENTRE);

		body_cf.setAlignment(jxl.format.Alignment.CENTRE);//设置正文单元格对齐
		body_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//设置水平对齐
		body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//设置正文单元格边框
		body_bz_cf.setAlignment(jxl.format.Alignment.LEFT);
		body_bz_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
			//List<Map<String,String>> list = iterator.next();	//班级对应的相关学生列表
			String xmmc = hashMap.get("xmmc");	//班级名称
			String xn = hashMap.get("xn");		//学年
			String title = xn+"年普通高等学校"+xmmc+"发放财务用表";	//标题
			if(StringUtils.isNull(xmmc)) xmmc = "未知项目";

			//创建工作表
			WritableSheet sheet = wwb.createSheet(xmmc, 0);
			//设置各列列宽
			sheet.setColumnView(0, 10);
			sheet.setColumnView(1, 15);
			sheet.setColumnView(2, 25);
			sheet.setColumnView(3, 15);
			sheet.setColumnView(4, 25);
			sheet.setColumnView(5, 15);
			sheet.setColumnView(6, 10);
			sheet.setRowView(3, 500, false);

			//合并单元格
			//第一个参数：要合并的单元格最左上角的列号，
			//第二个参数：要合并的单元格最左上角的行号，
			//第三个参数：要合并的单元格最右角的列号，
			//第四个参数：要合并的单元格最右下角的行
			sheet.mergeCells(0, 0, 6, 2);	//xx年普通高等学校xx发放财务用表
			//创建标题及表头
			Label t_0_0 = new Label(0, 0,title,title_cf);
			Label h_0_2 = new Label(0,3,"序号",head_cf);
			Label h_1_2 = new Label(1,3,"学生姓名",head_cf);
			Label h_2_2 = new Label(2,3,"专业",head_cf);
			Label h_3_2 = new Label(3,3,"学号",head_cf);
			Label h_3_3 = new Label(4,3,"身份证号",head_cf);
			Label h_4_3 = new Label(5,3,"金额（元）",head_cf);
			Label h_5_3 = new Label(6,3,"备注",head_cf);

			sheet.addCell(t_0_0);
			sheet.addCell(h_0_2);
			sheet.addCell(h_1_2);
			sheet.addCell(h_2_2);
			sheet.addCell(h_3_2);
			sheet.addCell(h_3_3);
			sheet.addCell(h_4_3);
			sheet.addCell(h_5_3);

			//遍历创建单元格
			int size = resultList.size();
			if(size>0){
				for(int j=0;j<size;j++){
					Map<String,String> map = resultList.get(j);
					Label xuhao = new Label(0, j+4,""+(j+1)+"", body_cf);	//序号
					Label xm = new Label(1, j+4, map.get("xm"), body_cf);		//学生姓名
					Label zy = new Label(2, j+4, map.get("zymc"), body_cf);		//专业
					Label xh = new Label(3, j+4, map.get("xh"), body_cf);		//学号
					Label sfzh = new Label(4, j+4, map.get("sfzh"), body_cf);		//身份证号
					Label je = new Label(5, j+4, map.get("xmje"), body_cf);		//金额（元）
					Label bz = new Label(6, j+4, map.get("bz"), body_cf);		//备注
					sheet.addCell(xuhao);
					sheet.addCell(xm);
					sheet.addCell(zy);
					sheet.addCell(xh);
					sheet.addCell(sfzh);
					sheet.addCell(je);
					sheet.addCell(bz);
					sheet.setRowView(j+4, 500, false);
				}
				sheet.mergeCells(0, size+4, 2, size+4);//合计（小写）标题
				sheet.mergeCells(3, size+4, 6, size+4);//合计（小写）值
				sheet.mergeCells(0, size+5, 2, size+5);//合计（大写）标题
				sheet.mergeCells(3, size+5, 6, size+5);//合计（大写）值
				sheet.mergeCells(0, size+8, 3, size+8);//审批
				sheet.mergeCells(0, size+12, 3, size+12);//审核
				sheet.mergeCells(0, size+15, 3, size+15);//制表
				sheet.mergeCells(0, size+16, 3, size+16);//日期
				Label hj_xx = new Label(0,size+4,"合计（小写）",head_cf);
				Label hj_dx = new Label(0,size+5,"合计（大写）",head_cf);
				if(hashMap.get("hjje") != null && !"".equals(hashMap.get("hjje"))){
					double d = Double.parseDouble(hashMap.get("hjje"));
					DecimalFormat df = new DecimalFormat("#,##0.00");
					hashMap.put("hjje_1", "￥"+String.valueOf(df.format(d)));
				}else{
					hashMap.put("hjje_1", "");
				}

				Label hjxx = new Label(3,size+4,hashMap.get("hjje_1"),body_hj_cf);
				Label hjdx = new Label(3,size+5,MoneyFormat.format(hashMap.get("hjje")),body_hj_cf);
				Label sp = new Label(0,size+8,"院长审批：                                         分管领导审批：",body_sp_cf);
				Label sh = new Label(0,size+12,"审核：                                                 财务主管：",body_sp_cf);
				Label zb = new Label(0,size+15,"制表：",body_rq_cf);
				Label rq = new Label(0,size+16,"日期：",body_rq_cf);

				sheet.addCell(hj_xx);
				sheet.addCell(hj_dx);
				sheet.addCell(hjxx);
				sheet.addCell(hjdx);
				sheet.addCell(sp);
				sheet.addCell(sh);
				sheet.addCell(zb);
				sheet.addCell(rq);

				sheet.setRowView(size+4, 400, false);
				sheet.setRowView(size+5, 400, false);
			}

		//如果数据为空
		if(resultList==null||resultList.size()==0){
			//创建工作表
			WritableSheet sheetNull = wwb.createSheet("本次导出数据为空", 0);
			sheetNull.setColumnView(0, 15);
			Label msg = new Label(0, 0,"暂无数据！");
			sheetNull.addCell(msg);
		}

		wwb.write();
		wwb.close();

		return file;
	}

	/**
	 * @throws Exception
	 * @描述:获取财务用表所需信息(苏州卫生职业技术学院)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-3-9 下午03:20:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getCwybList(BzjljgModel model, User user) throws Exception {
		return dao.getCwybList(model, user);
	}
	public String getCwybSum(BzjljgModel model, User user) throws Exception {
		return dao.getCwybSum(model, user);
	}
	
	/**
	 * 
	 * @描述: 获奖信息List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-21 下午05:19:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @param n
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getHjxxList(String xh,String xn,String xq,String n){
		List<HashMap<String,String>> hjxxList = dao.getHjxxList(xh, xn, xq, n);
		int m = hjxxList == null ? 0 : hjxxList.size();
		for (int i = 0; i < Integer.parseInt(n)-m; i++) {
			hjxxList.add(new HashMap<String, String>());
		}
		return hjxxList;
	}
	
	/**
	 * 
	 * @描述: 获取审核时间
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-21 下午05:49:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getHjshsjList(String xmdm,String days){
		return dao.getHjshsjList(xmdm, days);
		
	}

	public String getXymcBydm(String xydm) {
		return dao.getXymcBydm(xydm);
	}
	
	//中国美院 学生旷课补考违纪信息
	public HashMap<String,String> getKkbkxx(String xh){
		return dao.getKkbkxx(xh);
	}
}
