/**
 * @部门:学工产品事业部
 * @日期：2017-3-13 下午02:00:46 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.pjjg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 新评奖评优_我的评奖_评奖结果
 * @作者：  Meng.Wei[工号:1186]
 * @时间： 2017-3-13 下午02:00:46 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PjjgService extends SuperServiceImpl<PjjgForm, PjjgDao>implements Constants {
	private PjjgDao dao = new PjjgDao();

	public PjjgService() {
		super.setDao(dao);
	}
	
	/**
	 * @描述: 项目类型list
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-14 下午03:55:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlx() throws Exception {
		return dao.getXmlx();
	}
	
	/**
	 * @描述: 项目性质list
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-14 下午03:56:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmxz() throws Exception {
		return dao.getXmxz();
	}
	
	/** 
	 * @描述: 增加保存判断
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-14 下午03:56:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistByPjjgAdd(PjjgForm model) throws Exception {
		String num = dao.checkExistForAddSave(model);
		return Integer.valueOf(num) > 0;
	}
	
	/**
	 * @描述: 修改保存判断
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-14 下午03:56:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistByPjjgUpdate(PjjgForm model) throws Exception {
		String num = dao.checkExistForUpdateSave(model);
		return Integer.valueOf(num) > 0;
	}
	
	/**
	 * @描述: 评奖信息单独查询
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-14 下午03:57:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getPjjgInfo(String xh, String xn){
		return dao.getPjjgInfo(xh, xn);
	}
	
	/**
	 * @描述: 查看单个评奖
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-14 下午03:58:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOnePjjgList(String id) {

		return dao.getOnePjjgList(id);
	}
	
	/**
	 * @描述: 增加学生用
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-22 上午11:24:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZjXs(PjjgForm model, User user) throws Exception{
		return dao.getZjXs(model, user);
	}
	
	/**
	 * @描述: 查看已通过人数
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-5 上午11:59:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rskzfw
	 * @param xmdm
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getShtgrs(String rskzfw, String xmdm,
			String xn) {
		if (StringUtils.isNull(rskzfw)) {
			return null;
		}
		if (RSKZFW_XY.equals(rskzfw)) {
			return dao.getZzmeByXy(xmdm, xn);
		}else {
			return dao.getZzmeByQx(xmdm, xn);
		}
	}
	/**
	 * @描述: 获取平均成绩
	 * @日期： 2018-1-3 下午05:10:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return
	 * String 返回类型
	 * @throws
	 */
	public String getAverage(List<HashMap<String, String>> list) {
		if (null == list || list.size() <= 0) {
			return "";
		}
		int kcs = 0;
		Float zfs = new Float(0);
		/*循环格式化数据*/
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
	 * @描述: 获取平均成绩
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2018-1-3 下午05:24:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * String 返回类型
	 * @throws
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
	 * @描述: 评奖取最新四条
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-3 下午05:47:26
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
	 * 空的list
	 */
	public void addBlankList(List<HashMap<String,String>> list, int blankSize){
		for (int i = 0 ; i < blankSize ; i++){
			list.add(new HashMap<String, String>());
		}
	}

	/**
	 * @描述: 格式化登记表数据
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-4 上午10:32:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> formatForDjb(List<HashMap<String, String>> list) {
		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();

		int cols = 3;// 列
		int row = 7;// 行
		int i = 0;// 列自增变量
		int j = 0;// 自增变量

		/*如果不足行数 补空行*/
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
	 * @描述: 提供给学生信息显示评奖的接口 用于下载登记表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-4 上午11:13:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型
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
	 * @描述: 获取评奖结果list
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPjpyInfoList(String xh) {
		List<HashMap<String, String>> list = dao.getHjqkByXhMap(xh);
		return list;
	}
	

	/**
	 *  生成优秀学生.
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-01-04 17:04
	 * @param
	 * @return boolean
	 * @throw Exception
	 */
	public boolean scyxxs() throws Exception {
		return dao.scyxxs("pro_zjdx_pjpy_scyxxs");
	}
	
	/**
	 * @描述: 取学生最近一条申请信息，主要是取
	 *【外语水平、宿舍电话、担任社会工作职务、个人学习经历、参加科研情况、对设奖单位的认识】
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-31 下午05:01:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getLatestSqxx(String xh) throws Exception{
		return dao.getLatestSqxx(xh);
	}
}
