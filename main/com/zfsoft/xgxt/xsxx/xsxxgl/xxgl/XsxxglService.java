/**
 * @部门:学工产品事业部
 * @日期：2013-8-5 上午11:11:11
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

import java.io.File;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsDao;
import com.zfsoft.xgxt.xsxx.bysxxgl.bysxx.BysXxDao;
import com.zfsoft.xgxt.xsxx.bysxxgl.xxxgsq.BysXxXgSqService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxxg.XxxgService;
import common.Globals;
import common.newp.StringUtil;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息
 * @类功能描述: 学生信息管理
 * @作者： ligl
 * @时间： 2013-11-23 上午11:12:32
 * @版本： V1.0
 * @修改记录:
 */
public class XsxxglService extends SuperServiceImpl<XsxxglModel, XsxxglDao> {

	private XsxxglDao dao = new XsxxglDao();

	public XsxxglService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @描述:获取学生信息列表
	 * @作者：ligl
	 * @日期：2013-11-23 上午11:46:35
	 * @修改记录:
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxList(XsxxglModel model,
			User user) throws Exception {
		return dao.getPageList(model, user);
	}
	public List<HashMap<String, String>> getStuCjPageList(XsxxglModel model,
			User user) throws Exception {
		return dao.getStuCjPageList(model, user);
	}

	/**
	 * 获取学生信息列表
	 */
	public List<HashMap<String, String>> getXsxxAllList(XsxxglModel t,
			User user) throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.getPageList(t, user);
	}

	/**
	 * 
	 * @描述:查询结果字段配置列表
	 * @作者：ligl
	 * @日期：2013-11-25 上午09:27:04
	 * @修改记录:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getCxjgzdpzList() throws Exception {
		return dao.getCxjgzdpzList();
	}

	/**
	 * 
	 * 描述:密码初始化
	 * 
	 * @作者：ligl
	 * @日期：2013-11-25 下午01:57:00
	 * @修改记录:
	 * @param myForm
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean cshYhmm(String mm1, String values,String bz) throws Exception {
		return dao.cshYhmm(mm1, values,bz);
	}

	/**
	 * 
	 * @描述:删除学生信息
	 * @作者：ligl
	 * @日期：2013-11-26 上午11:10:18
	 * @修改记录:
	 * @param keys
	 * @return
	 * @throws Exception int 返回类型
	 * @throws
	 */
	public boolean delData(String keys) throws Exception {
		return dao.delData(keys);
	}

	/**
	 * @throws NullStringException
	 * 
	 * @描述:通过学号查询学生详细信息
	 * @作者：ligl
	 * @日期：2013-11-26 下午02:27:17
	 * @修改记录:
	 * @param xh
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getXsxxByXh(String xh) {
		HashMap<String, String> data = dao.getXsxxByXh(xh);
		/** 查询页面显示zw名称字段 */
		String zwdm = data.get("zw");
		if (StringUtils.equals("1", zwdm)) {
			data.put("zwmc", "校内");
		} else if (StringUtils.equals("2", zwdm)) {
			data.put("zwmc", "分级");
		} else if (StringUtils.equals("3", zwdm)) {
			data.put("zwmc", "社团");
		}
		
		
		//根据银行代码显示银行名称
		if(common.Globals.XXDM_JXKJSFXY.equals("11318")){
			data.put("yhmc2", dao.getYhmc(data.get("shgxzw2")));
			
		}
		
		if("10606".equals(Base.xxdm)){
			data.put("yhmc2", dao.getYhmc(data.get("shgxzw1")));
			data.put("yhmc3", dao.getYhmc(data.get("shgxgx2")));	
		}
		
		return data;

	}

	/**
	 * 
	 * @描述:通过学号查询学生详细信息
	 * @作者：ligl
	 * @日期：2013-11-26 下午02:27:17
	 * @修改记录:
	 * @param xh
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getXsxxByXhForUpdate(String xh) {
		
		HashMap<String, String> map = null;
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			//浙江大学
			map = dao.getXsxxByXhForUpdate_zjdx(xh);
		}else{
			map = dao.getXsxxByXhForUpdate(xh);
		}
		return map;
	}

	/**
	 * 
	 * @描述:通过学号查询学生成绩列表
	 * @作者：ligl
	 * @日期：2013-11-28 上午08:51:21
	 * @修改记录:
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getStuCjList(String xh) {
		return dao.getStuCjList(xh);
	}
	/**
	 * 
	 * @描述:获取学生学年课程成绩
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-16 下午07:34:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getStuCjOfXnList(String xh,String xn) {
		return dao.getStuCjOfXnList(xh,xn);
	}
	
	public List<HashMap<String, String>> getStuCjOfXnXqList(String xh,String xn,String xq) {
		return dao.getStuCjOfXnXqList(xh,xn,xq);
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-9-6 下午05:16:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getStuXFJDOfXnList(String xh,String xn) {
		return dao.getStuXFJDOfXnList(xh,xn);
	}
	
	/**
	 * 
	 * @描述:通过学号查询等级考试成绩
	 * @作者：ligl
	 * @日期：2013-11-28 上午10:14:30
	 * @修改记录:
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getStuDjcjList(String xh) {
		return dao.getStuDjcjList(xh);
	}

	/**
	 * 获取学生岗位信息列表
	 * 
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getStuQgzxXsgwxxList(String xh) {
		return dao.getStuQgzxXsgwxxList(xh);
	}

	/**
	 * 获取军训考核
	 * 
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getStuJxkhqk(String xh) {
		return dao.getStuJxkhqk(xh);
	}
	/**
	 * 获取学生酬金发放列表
	 * 
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getStuQgzxCjffList(String xh) {
		return dao.getStuQgzxCjffList(xh);
	}

	/**
	 * 
	 * @描述:获取公寓信息列表
	 * @作者：ligl
	 * @日期：2013-11-29 上午10:38:21
	 * @修改记录:
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getStuGyxxList(String xh) {
		return dao.getStuGyxxList(xh);
	}

	/**
	 * 获取楼栋辅导员信息
	 */
	public List<HashMap<String,String>> getGyfdyxx(String xh){
		return dao.getGyfdyxx(xh);
	}
	
	/**
	 * 获取楼栋管理员信息
	 */
	public List<HashMap<String,String>> getGyglyxx(String xh){
		return dao.getGyglyxx(xh);
	}
	
	/**
	 * 
	 * @描述:获取寝室物品列表
	 * @作者：ligl
	 * @日期：2013-11-29 上午10:38:21
	 * @修改记录:
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getQswpList(String xh) {
		return dao.getQswpList(xh);
	}

	/**
	 * 
	 * @描述: 获取公寓纪律处理信息
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-12 下午02:40:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getGyjlclxxList(String xh) {
		return dao.getGyjlclxxList(xh);
	}
	
	public List<HashMap<String, String>> getGyjlclxxAllList(String xh) {
		return dao.getGyjlclxxAllList(xh);
	}

	/**
	 * 
	 * @描述:获取公寓异动信息
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-12 下午03:05:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getGyydxxList(String xh) {
		return dao.getGyydxxList(xh);
	}

	/**
	 * 
	 * @描述:获取公寓评优信息
	 * @作者：1036
	 * @日期：2014-3-14 下午03:24:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getGypyxxList(String xh) {
		return dao.getGypyxxList(xh);
	}

	/**
	 * 
	 * @描述:根据学号查询违纪处分列表
	 * @作者：ligl
	 * @日期：2013-11-30 下午03:07:04
	 * @修改记录:
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getWjcfList(String xh) {
		return dao.getWjcfList(xh);
	}

	/**
	 * 通过表名获取表的所有字段
	 * 
	 * @param tableName
	 * @return
	 */
	public String[] getColumnByTable(String tableName) {
		return dao.getColumnByTable(tableName);
	}

	/**
	 * @描述:数据保存
	 * @作者：ligl
	 * @日期：2013-11-29 下午02:13:20
	 * @修改记录:
	 * @param myForm
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean saveRecord(XsxxglModel myForm,
			HashMap<String, String> valueMap) throws Exception {
		valueMap.put("xy", myForm.getXymc());

		// 省市县，拆分字段保存。保持原有表字段数据完整性。新开发学生信息增删改查申请审核等功能，拆分字段未使用
		// 生源地
		String syd = valueMap.get("syd");
		HashMap<String, String> sydMap = getSsx(syd);
		valueMap.put("syds", sydMap.get("sheng"));
		valueMap.put("sydshi", sydMap.get("shi"));
		valueMap.put("sydx", sydMap.get("xian"));

		boolean result = dao.saveInfo(valueMap);
		if (result) {
			result = dao.saveXsqtxx(myForm);
		}
		return result;
	}

	/**
	 * 
	 * @描述:修改操作
	 * @作者：ligl
	 * @日期：2013-12-3 上午08:52:48
	 * @修改记录:
	 * @param myForm
	 * @param valueMap
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean updateRecord(XsxxglModel myForm,
			HashMap<String, String> valueMap,
			HashMap<String, String> xsfzxxValueMap) throws Exception {
		valueMap.put("xy", myForm.getXymc());

		// 省市县，拆分字段保存。保持原有表字段数据完整性。新开发学生信息增删改查申请审核等功能，拆分字段未使用
		// 生源地
		String syd = valueMap.get("syd");
		HashMap<String, String> sydMap = getSsx(syd);
		valueMap.put("syds", sydMap.get("sheng"));
		valueMap.put("sydshi", sydMap.get("shi"));
		valueMap.put("sydx", sydMap.get("xian"));

		// 籍贯
		String jg = valueMap.get("jg");
		HashMap<String, String> jgMap = getSsx(jg);
		valueMap.put("jgs", jgMap.get("sheng"));
		valueMap.put("jgshi", jgMap.get("shi"));
		valueMap.put("jgx", jgMap.get("xian"));

		// 户口所在地
		String hkszd = valueMap.get("hkszd");
		HashMap<String, String> hkszdMap = getSsx(hkszd);
		valueMap.put("hkshen", hkszdMap.get("sheng"));
		valueMap.put("hkshi", hkszdMap.get("shi"));
		valueMap.put("hkxian", hkszdMap.get("xian"));
		
		// 出生地
		String csd = valueMap.get("csd");
		HashMap<String, String> csdMap = getSsx(csd);
		valueMap.put("csds", csdMap.get("sheng"));
		valueMap.put("csdshi", csdMap.get("shi"));
		valueMap.put("csdxian", csdMap.get("xian"));

		boolean result = dao.updateInfo(valueMap);
		if (result) {
			result = dao.updateInfoXsfzxx(xsfzxxValueMap);
		}

		return result;
	}

	/*
	 * 省市县，拆分字段保存。保持原有表字段数据完整性。新开发学生信息增删改查申请审核等功能，拆分字段未使用
	 */
	private HashMap<String, String> getSsx(String dm) {
		HashMap<String, String> ssx = new HashMap<String, String>();
		String sheng = "";
		String shi = "";
		String xian = "";
		if (dm != null && !dm.trim().equals("") && dm.length() >= 6) {
			String tmp0 = dm.substring(0, 2);
			sheng = tmp0 + "0000";
			String tmp1 = dm.substring(2, 4);
			if (!tmp1.equals("00")) {
				shi = tmp0 + tmp1 + "00";
			}
			String tmp2 = dm.substring(4, 6);
			if (!tmp2.equals("00")) {
				xian = dm;
			}
		}
		ssx.put("sheng", sheng);
		ssx.put("shi", shi);
		ssx.put("xian", xian);
		return ssx;
	}

	/**
	 * 
	 * @描述:学生，修改信息
	 * @作者：ligl
	 * @日期：2013-12-9 下午04:30:36
	 * @修改记录:
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean updateRecordForStu(String sqid, boolean th) throws Exception {
		return updateRecordForStu(sqid, null, th);
	}
	public boolean updateRecordForStu(String sqid, String xh, boolean th) throws Exception {
		return updateRecordForStu(sqid, xh, th, false);
	}
	public boolean updateRecordForStu(String sqid, String xh, boolean th, boolean isBysxx) throws Exception {
		return updateRecordForStu(sqid, xh, th, isBysxx, null);
	}
	/**
	 * 
	 * @描述:学生，修改信息
	 * @作者：ligl
	 * @日期：2013-12-9 下午04:30:36
	 * @修改记录:
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean updateRecordForStu(String sqid, String xh, boolean th, boolean isBysxx,List<HashMap<String, String>> xsxgzdList)
			throws Exception {
		List<HashMap<String, String>> xgzdList = null;
		if(xsxgzdList != null){
			xgzdList = xsxgzdList;
		} else {
			xgzdList = new XxxgService().getXgzdList(sqid);
		}
		
		String[] xsxxZds = dao.getColumnByTable("xsxxb");
		String[] xsfzxxZds = dao.getColumnByTable("xsfzxxb");
		String[] bysxxZds = dao.getColumnByTable("XG_BYSXX_BYSXXB");

		HashMap<String, String> valueMap = new HashMap<String, String>();
		HashMap<String, String> xsfzxxValueMap = new HashMap<String, String>();
		HashMap<String, String> bysxxValueMap = new HashMap<String, String>();

		String zd = null;
		String zdz = null;
		//家庭成员信息
		String jtcyxxXgqzJson = null;
		String jtcyxxZdzJson = null;
		
		//学生社会经历信息
		String xlshjlXgqzJson = null;
		String xlshjlZdzJson = null;

		//培训信息
		String pxxxXgqzJson = null;
		String pxxxZdzJson = null;

		//入学前获奖情况
		String rxqhjqkXgqzJson = null;
		String rxqhjqkZdzJson = null;

		//获奖情况（新）
		String hjqkxxNewXgqzJson = null;
		String hjqkxxNewZdzJson = null;
		
		//学生校外获奖情况（广州铁路职业技术学院）
		String xsxwhjqkXgqzJson = null;
		String xsxwhjqkZdzJson = null;
		
		//等级考试成绩（西安科技大学）
		String djkscjXgqzJson = null;
		String djkscjZdzJson = null;
		
		//学生干部经历（西安科技大学）
		String xsgbjlXgqzJson = null;
		String xsgbjlZdzJson = null;
		
		//社会实践情况（西安科技大学）
		String shsjqkXgqzJson = null;
		String shsjqkZdzJson = null;
		
		//社会实践情况（河南农业大学）
		String hnnyshsjXgqzJson = null;
		String hnnyshsjZdzJson = null;
		
		//出国出境交流信息（西安科技大学）
		String cgcjjlXgqzJson = null;
		String cgcjjlZdzJson = null;
		
		//发表论文（西安科技大学）
		String fblwXgqzJson = null;
		String fblwZdzJson = null;
		
		//科研项目（西安科技大学）
		String kyxmXgqzJson = null;
		String kyxmZdzJson = null;
		
		//其他成果（西安科技大学）
		String qtcgXgqzJson = null;
		String qtcgZdzJson = null;
		
		//获奖情况（西安科技大学）
		String hjqkXakjXgqzJson = null;
		String hjqkXakjZdzJson = null;

		//跟岗实习信息(吉林工业职业)
		String ggsxjlXgqzJson = null;
		String ggsxjlZdzJson = null;

		//顶岗实习信息(吉林工业职业)
		String dgsxjlXgqzJson = null;
		String dgsxjlZdzJson = null;
		
		//科学研究
		String kxyjXgqzJson=null;
		String kxyjZdzJson=null;
		//课题研究
		String ktyjXgqzJson=null;
		String ktyjZdzJson=null;
		//创新创业项目
		String cxcyxmXgqzJson=null;
		String cxcyxmZdzJson=null;
		//学科竞赛
		String xkjsXgqzJson=null;
		String xkjsZdzJson=null;
		//技能证书
		String jnzsXgqzJson=null;
		String jnzsZdzJson=null;

		//个人简历(华中师范大学)
		String grjlXgqzJson = null;
		String grjlZdzJson = null;

		//工作简历(华中师范大学)
		String gzjlXgqzJson = null;
		String gzjlZdzJson = null;

		//学生银行信息(梧州学院)
		String xsyhxxXgqzJson = null;
		String xsyhxxZdzJson = null;

		//个人奖励信息(北京中医药大学)
		String grhjxxXgqzJson = null;
		String grhjxxZdzJson = null;

		for (HashMap<String, String> xgzdMap : xgzdList) {
			zd = xgzdMap.get("zd");
			if (th) {
				zdz = xgzdMap.get("xgqz");
			} else {
				zdz = xgzdMap.get("zdz");
			}
			if (zd != null) {
				//家庭成员信息
				if (zd.equals(Constants.ZDYBD_JTCYXX)) {
					jtcyxxXgqzJson = xgzdMap.get("xgqz");
					jtcyxxZdzJson = xgzdMap.get("zdz");
				}
				//学生社会经历信息
				if (zd.equals(Constants.ZDYBD_XLSHJLXX)) {
					xlshjlXgqzJson = xgzdMap.get("xgqz");
					xlshjlZdzJson = xgzdMap.get("zdz");
				}
				//培训信息
				if (zd.equals("pxxxList")) {
					pxxxXgqzJson = xgzdMap.get("xgqz");
					pxxxZdzJson = xgzdMap.get("zdz");
				}
				//获奖情况
				if (zd.equals("rxqhjqkList")) {
					rxqhjqkXgqzJson = xgzdMap.get("xgqz");
					rxqhjqkZdzJson = xgzdMap.get("zdz");
				}
				//获奖情况(新)
				if (zd.equals("hjqkxxNewList")) {
					hjqkxxNewXgqzJson = xgzdMap.get("xgqz");
					hjqkxxNewZdzJson = xgzdMap.get("zdz");
				}
				//学生校外获奖情况（广州铁路职业技术学院）
				if (zd.equals("xsxwhjqkList")) {
					xsxwhjqkXgqzJson = xgzdMap.get("xgqz");
					xsxwhjqkZdzJson = xgzdMap.get("zdz");
				}
				//等级考试成绩（西安科技大学）
				if (zd.equals("djkscjList_10704")) {
					djkscjXgqzJson = xgzdMap.get("xgqz");
					djkscjZdzJson = xgzdMap.get("zdz");
				}
				//学生干部经历（西安科技大学）
				if (zd.equals("xsgbjlList_10704")) {
					xsgbjlXgqzJson = xgzdMap.get("xgqz");
					xsgbjlZdzJson = xgzdMap.get("zdz");
				}
				//社会实践情况（西安科技大学）
				if (zd.equals("shsjqkList_10704")) {
					shsjqkXgqzJson = xgzdMap.get("xgqz");
					shsjqkZdzJson = xgzdMap.get("zdz");
				}
				//社会实践情况（河南农业大学）
				if (zd.equals("shsjqkList_10466")) {
					hnnyshsjXgqzJson = xgzdMap.get("xgqz");
					hnnyshsjZdzJson = xgzdMap.get("zdz");
				}
				//出国出境交流信息（西安科技大学）
				if (zd.equals("cgcjjlList_10704")) {
					cgcjjlXgqzJson = xgzdMap.get("xgqz");
					cgcjjlZdzJson = xgzdMap.get("zdz");
				}
				//发表论文（西安科技大学）
				if (zd.equals("fblwList_10704")) {
					fblwXgqzJson = xgzdMap.get("xgqz");
					fblwZdzJson = xgzdMap.get("zdz");
				}
				//科研项目（西安科技大学）
				if (zd.equals("kyxmList_10704")) {
					kyxmXgqzJson = xgzdMap.get("xgqz");
					kyxmZdzJson = xgzdMap.get("zdz");
				}
				//其他成果（西安科技大学）
				if (zd.equals("qtcgList_10704")) {
					qtcgXgqzJson = xgzdMap.get("xgqz");
					qtcgZdzJson = xgzdMap.get("zdz");
				}
				if (zd.equals("hjqkList_10704")) {					
					hjqkXakjXgqzJson = xgzdMap.get("xgqz");
					hjqkXakjZdzJson = xgzdMap.get("zdz");
				}
				//跟岗实习信息
				if (zd.equals("ggsxjlList")) {
					ggsxjlXgqzJson = xgzdMap.get("xgqz");
					ggsxjlZdzJson = xgzdMap.get("zdz");
				}
				//顶岗实习信息
				if (zd.equals("dgsxjlList")) {
					dgsxjlXgqzJson = xgzdMap.get("xgqz");
					dgsxjlZdzJson = xgzdMap.get("zdz");
				}
				//科学研究
				if (zd.equals("kxyjList")) {
					kxyjXgqzJson= xgzdMap.get("xgqz");
					kxyjZdzJson = xgzdMap.get("zdz");
				}
				//课题研究
				if (zd.equals("ktyjList")) {
					ktyjXgqzJson= xgzdMap.get("xgqz");
					ktyjZdzJson = xgzdMap.get("zdz");
				}
				//创新创业项目
				if (zd.equals("cxcyxmList")) {
					cxcyxmXgqzJson= xgzdMap.get("xgqz");
					cxcyxmZdzJson = xgzdMap.get("zdz");
				}
				//学科竞赛
				if (zd.equals("xkjsList")) {
					xkjsXgqzJson= xgzdMap.get("xgqz");
					xkjsZdzJson = xgzdMap.get("zdz");
				}
				//技能证书
				if (zd.equals("jnzsList")) {
					jnzsXgqzJson= xgzdMap.get("xgqz");
					jnzsZdzJson = xgzdMap.get("zdz");
				}
				
				//个人简历
				if (zd.equals("grjlList")) {
					grjlXgqzJson = xgzdMap.get("xgqz");
					grjlZdzJson = xgzdMap.get("zdz");
				}
				//工作简历
				if (zd.equals("gzjlList")) {
					gzjlXgqzJson = xgzdMap.get("xgqz");
					gzjlZdzJson = xgzdMap.get("zdz");
				}
				//学生银行信息
				if (zd.equals("xsyhxxList")) {
					xsyhxxXgqzJson = xgzdMap.get("xgqz");
					xsyhxxZdzJson = xgzdMap.get("zdz");
				}
				//个人奖励信息
				if (zd.equals("grhjxxList")) {
					grhjxxXgqzJson = xgzdMap.get("xgqz");
					grhjxxZdzJson = xgzdMap.get("zdz");
				}
			}

			for (int i = 0; i < xsxxZds.length; i++) {
				String xsxxZd = xsxxZds[i];
				if (zd != null && zd.toLowerCase().equals(xsxxZd.toLowerCase())) {
					valueMap.put(zd, zdz);
				}
			}
			for (int i = 0; i < bysxxZds.length; i++) {
				String bysxxZd = bysxxZds[i];
				if (zd != null
						&& zd.toLowerCase().equals(bysxxZd.toLowerCase())) {
					bysxxValueMap.put(zd, zdz);
				}
			}
			for (int i = 0; i < xsfzxxZds.length; i++) {
				String xsfzxxZd = xsfzxxZds[i];
				if (zd != null
						&& zd.toLowerCase().equals(xsfzxxZd.toLowerCase())) {
					xsfzxxValueMap.put(zd, zdz);
				}
			}
		}
		boolean result = true;

		if (xh == null) {
			xh = new XxxgService().getXhBySqid(sqid);
		}
		if (xh != null) {
			if (!valueMap.isEmpty()) {
				valueMap.put("xh", xh);
				result = dao.updateInfo(valueMap);
			}
			// 华中师范大学更新毕业生信息
			if ("10511".equals(Base.xxdm) && result && isBysxx) {
				HashMap<String, String> bysxx = new BysXxXgSqService()
						.getXhBySqid(sqid);
				if (!bysxxValueMap.isEmpty() && !bysxx.isEmpty()) {
					bysxxValueMap.put("xh", bysxx.get("xh"));
					BysXxDao bysxxDao = new BysXxDao();
					result = bysxxDao.updateBysInfo(bysxxValueMap);
				}

			}
			if (result) {
				if (!xsfzxxValueMap.isEmpty()) {
					xsfzxxValueMap.put("xh", xh);
					result = dao.updateInfoXsfzxx(xsfzxxValueMap);
				}
			}

			List jtcyxxList = null;
			List xlshjlcxxList = null;
			List pxxxList = null;
			List rxqhjqkList = null;
			List hjqkList = null;
			List xsxwhjqkList = null;
			//河南农业大学
			List hnnyshsjList = null;
			//西安科技大学
			List djkscjList = null;	
			List xsgbjlList = null;
			List shsjqkList = null;
			List cgcjjlList = null;
			List fblwList = null;
			List kyxmList = null;
			List qtcgList = null;
			//西安科技大学：end
			List ggsxjlList = null;
			List dgsxjlList = null;
			List kxyjList=null;
			List ktyjList=null;
			List cxcyxmList=null;
			List xkjsList=null;
			List jnzsList=null;
			List grjlList = null;
			List gzjlList = null;
			List xsyhxxList = null;
			List grhjxxList = null;
			List hjqkXakjList = null;
			if (th) {// 退回
				if (jtcyxxXgqzJson != null) {
					jtcyxxXgqzJson = "{data:" + jtcyxxXgqzJson + "}";
					jtcyxxList = JsonUtil.jsonToList(jtcyxxXgqzJson,
							JtcyxxModel.class);
					result = updateJtcyxx(xh, jtcyxxList);// 家庭成员信息保存
				}

				if (xlshjlXgqzJson != null) {
					xlshjlXgqzJson = "{data:" + xlshjlXgqzJson + "}";
					xlshjlcxxList = JsonUtil.jsonToList(xlshjlXgqzJson,
							XlshjlModel.class);
					result = updateXlshjlxx(xh, xlshjlcxxList);// 学历社会经历信息保存
				}

				if (pxxxXgqzJson != null) {
					pxxxXgqzJson = "{data:" + pxxxXgqzJson + "}";
					pxxxList = JsonUtil.jsonToList(pxxxXgqzJson,
							PxxxModel.class);
					result = updatePxxx(xh, pxxxList);// 培训信息保存
				}

				if (rxqhjqkXgqzJson != null) {
					rxqhjqkXgqzJson = "{data:" + rxqhjqkXgqzJson + "}";
					rxqhjqkList = JsonUtil.jsonToList(rxqhjqkXgqzJson,
							HjqkModel.class);
					result = updateHjqk(xh, rxqhjqkList);// 获奖情况保存
				}

				if (hjqkxxNewXgqzJson != null) {
					hjqkxxNewXgqzJson = "{data:" + hjqkxxNewXgqzJson + "}";
					hjqkList = JsonUtil.jsonToList(hjqkxxNewXgqzJson,
							HjqkNewModel.class);
					result = updateHjqkNew(xh, hjqkList);// 获奖情况(新)保存
				}
				
				if (xsxwhjqkXgqzJson != null) {
					xsxwhjqkXgqzJson = "{data:" + xsxwhjqkXgqzJson + "}";
					xsxwhjqkList = JsonUtil.jsonToList(xsxwhjqkXgqzJson,
							XsxwhjqkModel.class);
					result = updateXsxwhjqk(xh, xsxwhjqkList);// 学生校外获奖情况（广州铁路职业技术学院）保存
				}
				// 河南农业大学
				if("10466".equals(Base.xxdm)){
					//社会实践
					if (hnnyshsjZdzJson != null) {
						hnnyshsjZdzJson = "{data:" + hnnyshsjZdzJson + "}";
						hnnyshsjList = JsonUtil.jsonToList(hnnyshsjZdzJson,
								ShsjqkModel.class);
						result = updateShsjqk(xh, hnnyshsjList);
					}
				}
				//西安科技大学
				if("10704".equals(Base.xxdm)){
					//等级考试信息保存
					if (djkscjXgqzJson != null) {
						djkscjXgqzJson = "{data:" + xsxwhjqkXgqzJson + "}";
						djkscjList = JsonUtil.jsonToList(djkscjXgqzJson,
								DjkscjModel.class);
						result = updateDjkscj(xh, djkscjList);
					}
					//学生干部经历保存
					if (xsgbjlXgqzJson != null) {
						xsgbjlXgqzJson = "{data:" + xsgbjlXgqzJson + "}";
						xsgbjlList = JsonUtil.jsonToList(xsgbjlXgqzJson,
								XsgbjlModel.class);
						result = updateXsgbjl(xh, xsgbjlList);
					}
					//社会实践情况保存
					if (shsjqkXgqzJson != null) {
						shsjqkXgqzJson = "{data:" + shsjqkXgqzJson + "}";
						shsjqkList = JsonUtil.jsonToList(shsjqkXgqzJson,
								ShsjqkModel.class);
						result = updateShsjqk(xh, shsjqkList);
					}
					
					//出国出境交流信息保存
					if (cgcjjlXgqzJson != null) {
						cgcjjlXgqzJson = "{data:" + cgcjjlXgqzJson + "}";
						cgcjjlList = JsonUtil.jsonToList(cgcjjlXgqzJson,
								CgcjjlModel.class);
						result = updateCgcjjl(xh, cgcjjlList);
					}
					//发表论文保存
					if (fblwXgqzJson != null) {
						fblwXgqzJson = "{data:" + fblwXgqzJson + "}";
						fblwList = JsonUtil.jsonToList(fblwXgqzJson,
								FblwModel.class);
						result = updateFblw(xh, fblwList);
					}
					//科研项目保存
					if (kyxmXgqzJson != null) {
						kyxmXgqzJson = "{data:" + kyxmXgqzJson + "}";
						kyxmList = JsonUtil.jsonToList(kyxmXgqzJson,
								KyxmModel.class);
						result = updateKyxm(xh, kyxmList);
					}
					//其他成果保存
					if (qtcgXgqzJson != null) {
						qtcgXgqzJson = "{data:" + qtcgXgqzJson + "}";
						qtcgList = JsonUtil.jsonToList(qtcgXgqzJson,
								QtcgModel.class);
						result = updateQtcg(xh, qtcgList);
					}
					//获奖情况保存
					if (hjqkXakjXgqzJson != null) {
						hjqkXakjXgqzJson = "{data:" + hjqkXakjXgqzJson + "}";
						hjqkXakjList = JsonUtil.jsonToList(hjqkXakjXgqzJson,
								HjqkModel.class);
						result = updateHjqkXakj(xh, hjqkXakjList);
					}
					
				}

				// 吉林工业职业技术学院
				if ("12903".equals(Base.xxdm)) {

					if (ggsxjlXgqzJson != null) {
						ggsxjlXgqzJson = "{data:" + ggsxjlXgqzJson + "}";
						ggsxjlList = JsonUtil.jsonToList(ggsxjlXgqzJson,
								GgsxjlModel.class);
						result = updateGgsxjl(xh, ggsxjlList);// 跟岗实习信息保存
					}

					if (dgsxjlXgqzJson != null) {
						dgsxjlXgqzJson = "{data:" + dgsxjlXgqzJson + "}";
						dgsxjlList = JsonUtil.jsonToList(dgsxjlXgqzJson,
								DgsxjlModel.class);
						result = updateDgsxjl(xh, dgsxjlList);// 顶岗实习信息保存
					}
				}
				//山西财经
				if("10125".equalsIgnoreCase(Base.xxdm)) {
					if (kxyjXgqzJson != null) {
						kxyjXgqzJson = "{data:" + kxyjXgqzJson + "}";
						kxyjList = JsonUtil.jsonToList(kxyjXgqzJson,KxyjModel.class);
						result = updateKxyj(xh, kxyjList);  //科学研究保存
					}
					if (ktyjXgqzJson != null) {
						ktyjXgqzJson = "{data:" + ktyjXgqzJson + "}";
						ktyjList = JsonUtil.jsonToList(ktyjXgqzJson,KtyjModel.class);
						result = updateKtyj(xh, ktyjList);  //课题研究保存
					}
					if (cxcyxmXgqzJson != null) {
						cxcyxmXgqzJson = "{data:" + cxcyxmXgqzJson + "}";
						cxcyxmList = JsonUtil.jsonToList(cxcyxmXgqzJson,CxcyxmModel.class);
						result = updateCxcyxm(xh, cxcyxmList);  //创新创业项目保存
					}
					if (xkjsXgqzJson != null) {
						xkjsXgqzJson = "{data:" + xkjsXgqzJson + "}";
						xkjsList = JsonUtil.jsonToList(xkjsXgqzJson,XkjsModel.class);
						result = updateXkjs(xh, xkjsList);  //学科竞赛保存
					}
					if (jnzsXgqzJson != null) {
						jnzsXgqzJson = "{data:" + jnzsXgqzJson + "}";
						jnzsList = JsonUtil.jsonToList(jnzsXgqzJson,JnzsModel.class);
						result = updateJnzs(xh, jnzsList);  //技能证书保存
					}
				}
				
				// 华中师范大学
				if ("10511".equals(Base.xxdm)) {

					if (grjlXgqzJson != null) {
						grjlXgqzJson = "{data:" + grjlXgqzJson + "}";
						grjlList = JsonUtil.jsonToList(grjlXgqzJson,
								GrjlModel.class);
						result = updateXsGrjl(xh, grjlList);// 个人简历保存
					}

					if (gzjlXgqzJson != null) {
						gzjlXgqzJson = "{data:" + gzjlXgqzJson + "}";
						gzjlList = JsonUtil.jsonToList(gzjlXgqzJson,
								GzjlModel.class);
						result = updateXsGzjl(xh, gzjlList);// 工作简历保存
					}
				}

				// 梧州学院
				if ("11354".equals(Base.xxdm)) {
					
					if (xsyhxxXgqzJson != null) {
						xsyhxxXgqzJson = "{data:" + xsyhxxXgqzJson + "}";
						xsyhxxList = JsonUtil.jsonToList(xsyhxxXgqzJson,
								XsyhxxModel.class);
						result = updateXsyhxx(xh, xsyhxxList);// 学生银行信息保存
					}
				}

				// 北京中医药大学
				if ("10026".equals(Base.xxdm)) {
					
					if (grhjxxXgqzJson != null) {
						grhjxxXgqzJson = "{data:" + grhjxxXgqzJson + "}";
						grhjxxList = JsonUtil.jsonToList(grhjxxXgqzJson,
								GrhjxxModel.class);
						result = updateGrhjxx(xh, grhjxxList);// 个人奖励信息保存
					}
				}
			} else {
				if (jtcyxxZdzJson != null) {
					jtcyxxZdzJson = "{data:" + jtcyxxZdzJson + "}";
					jtcyxxList = JsonUtil.jsonToList(jtcyxxZdzJson,
							JtcyxxModel.class);
					result = updateJtcyxx(xh, jtcyxxList);// 家庭成员信息保存
				}

				if (xlshjlZdzJson != null) {
					xlshjlZdzJson = "{data:" + xlshjlZdzJson + "}";
					xlshjlcxxList = JsonUtil.jsonToList(xlshjlZdzJson,
							XlshjlModel.class);
					result = updateXlshjlxx(xh, xlshjlcxxList);// 学历社会经历信息保存
				}

				if (pxxxZdzJson != null) {
					pxxxZdzJson = "{data:" + pxxxZdzJson + "}";
					pxxxList = JsonUtil
							.jsonToList(pxxxZdzJson, PxxxModel.class);
					result = updatePxxx(xh, pxxxList);// 培训信息保存
				}

				if (rxqhjqkZdzJson != null) {
					rxqhjqkZdzJson = "{data:" + rxqhjqkZdzJson + "}";
					rxqhjqkList = JsonUtil.jsonToList(rxqhjqkZdzJson,
							HjqkModel.class);
					result = updateHjqk(xh, rxqhjqkList);// 获奖情况保存
				}

				if (hjqkxxNewZdzJson != null) {
					hjqkxxNewZdzJson = "{data:" + hjqkxxNewZdzJson + "}";
					hjqkList = JsonUtil.jsonToList(hjqkxxNewZdzJson,
							HjqkNewModel.class);
					result = updateHjqkNew(xh, hjqkList);// 获奖情况(新)保存
				}
				
				if (xsxwhjqkZdzJson != null) {
					xsxwhjqkZdzJson = "{data:" + xsxwhjqkZdzJson + "}";
					xsxwhjqkList = JsonUtil.jsonToList(xsxwhjqkZdzJson,
							XsxwhjqkModel.class);
					result = updateXsxwhjqk(xh, xsxwhjqkList);// 学生校外获奖情况（广州铁路职业技术学院）保存
				}
				
				if("10466".equals(Base.xxdm)){
					//社会实践
					if (hnnyshsjZdzJson != null) {
						hnnyshsjZdzJson = "{data:" + hnnyshsjZdzJson + "}";
						hnnyshsjList = JsonUtil.jsonToList(hnnyshsjZdzJson,
								ShsjqkModel.class);
						result = updateShsjqk(xh, hnnyshsjList);
					}
				}
				//西安科技大学
				if("10704".equals(Base.xxdm)){
					//等级考试信息保存
					if (djkscjZdzJson != null) {
						djkscjZdzJson = "{data:" + djkscjZdzJson + "}";
						djkscjList = JsonUtil.jsonToList(djkscjZdzJson,
								DjkscjModel.class);
						result = updateDjkscj(xh, djkscjList);
					}
					//学生干部经历保存
					if (xsgbjlZdzJson != null) {
						xsgbjlZdzJson = "{data:" + xsgbjlZdzJson + "}";
						xsgbjlList = JsonUtil.jsonToList(xsgbjlZdzJson,
								XsgbjlModel.class);
						result = updateXsgbjl(xh, xsgbjlList);
					}
					//社会实践情况保存
					if (shsjqkZdzJson != null) {
						shsjqkZdzJson = "{data:" + shsjqkZdzJson + "}";
						shsjqkList = JsonUtil.jsonToList(shsjqkZdzJson,
								ShsjqkModel.class);
						result = updateShsjqk(xh, shsjqkList);
					}
					//出国出境交流信息保存
					if (cgcjjlZdzJson != null) {
						cgcjjlZdzJson = "{data:" + cgcjjlZdzJson + "}";
						cgcjjlList = JsonUtil.jsonToList(cgcjjlZdzJson,
								CgcjjlModel.class);
						result = updateCgcjjl(xh, cgcjjlList);
					}
					//发表论文保存
					if (fblwZdzJson != null) {
						fblwZdzJson = "{data:" + fblwZdzJson + "}";
						fblwList = JsonUtil.jsonToList(fblwZdzJson,
								FblwModel.class);
						result = updateFblw(xh, fblwList);
					}
					//科研项目保存
					if (kyxmZdzJson != null) {
						kyxmZdzJson = "{data:" + kyxmZdzJson + "}";
						kyxmList = JsonUtil.jsonToList(kyxmZdzJson,
								KyxmModel.class);
						result = updateKyxm(xh, kyxmList);
					}
					//其他成果保存
					if (qtcgZdzJson != null) {
						qtcgZdzJson = "{data:" + qtcgZdzJson + "}";
						qtcgList = JsonUtil.jsonToList(qtcgZdzJson,
								QtcgModel.class);
						result = updateQtcg(xh, qtcgList);
					}
				}

				// 吉林工业职业技术学院
				if ("12903".equals(Base.xxdm)) {

					if (ggsxjlZdzJson != null) {
						ggsxjlZdzJson = "{data:" + ggsxjlZdzJson + "}";
						ggsxjlList = JsonUtil.jsonToList(ggsxjlZdzJson,
								GgsxjlModel.class);
						result = updateGgsxjl(xh, ggsxjlList);// 跟岗实习信息保存
					}

					if (dgsxjlZdzJson != null) {
						dgsxjlZdzJson = "{data:" + dgsxjlZdzJson + "}";
						dgsxjlList = JsonUtil.jsonToList(dgsxjlZdzJson,
								DgsxjlModel.class);
						result = updateDgsxjl(xh, dgsxjlList);// 顶岗实习信息保存
					}
				}
				
				//山西财经
				if("10125".equalsIgnoreCase(Base.xxdm)) {
					if (kxyjZdzJson != null) {
						kxyjZdzJson = "{data:" + kxyjZdzJson + "}";
						kxyjList = JsonUtil.jsonToList(kxyjZdzJson,	KxyjModel.class);
						result = updateKxyj(xh, kxyjList);  //科学研究保存
					}
					if (ktyjZdzJson != null) {
						ktyjZdzJson = "{data:" + ktyjZdzJson + "}";
						ktyjList = JsonUtil.jsonToList(ktyjZdzJson,	KtyjModel.class);
						result = updateKtyj(xh, ktyjList);  //课题研究保存
					}
					if (cxcyxmZdzJson != null) {
						cxcyxmZdzJson = "{data:" + cxcyxmZdzJson + "}";
						cxcyxmList = JsonUtil.jsonToList(cxcyxmZdzJson,	CxcyxmModel.class);
						result = updateCxcyxm(xh, cxcyxmList);  //创新创业项目保存
					}
					if (xkjsZdzJson != null) {
						xkjsZdzJson = "{data:" + xkjsZdzJson + "}";
						xkjsList = JsonUtil.jsonToList(xkjsZdzJson,	XkjsModel.class);
						result = updateXkjs(xh, xkjsList);  //学科竞赛保存
					}
					if (jnzsZdzJson != null) {
						jnzsZdzJson = "{data:" + jnzsZdzJson + "}";
						jnzsList = JsonUtil.jsonToList(jnzsZdzJson,	JnzsModel.class);
						result = updateJnzs(xh, jnzsList);  //技能证书保存
					}
				}

				// 华中师范大学
				if ("10511".equals(Base.xxdm)) {

					if (grjlZdzJson != null) {
						grjlZdzJson = "{data:" + grjlZdzJson + "}";
						grjlList = JsonUtil.jsonToList(grjlZdzJson,
								GrjlModel.class);
						result = updateXsGrjl(xh, grjlList);// 个人简历保存
					}

					if (gzjlZdzJson != null) {
						gzjlZdzJson = "{data:" + gzjlZdzJson + "}";
						gzjlList = JsonUtil.jsonToList(gzjlZdzJson,
								GzjlModel.class);
						result = updateXsGzjl(xh, gzjlList);// 工作简历保存
					}
				}

				// 梧州学院
				if ("11354".equals(Base.xxdm)) {
					
					if (xsyhxxZdzJson != null) {
						xsyhxxZdzJson = "{data:" + xsyhxxZdzJson + "}";
						xsyhxxList = JsonUtil.jsonToList(xsyhxxZdzJson,
								XsyhxxModel.class);
						result = updateXsyhxx(xh, xsyhxxList);// 学生银行信息保存
					}
				}

				// 北京中医药大学
				if ("10026".equals(Base.xxdm)) {
					
					if (grhjxxZdzJson != null) {
						grhjxxZdzJson = "{data:" + grhjxxZdzJson + "}";
						grhjxxList = JsonUtil.jsonToList(grhjxxZdzJson,
								GrhjxxModel.class);
						result = updateGrhjxx(xh, grhjxxList);// 个人奖励信息保存
					}
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @描述:根据学号查询学籍异动列表
	 * @作者：ligl
	 * @日期：2013-11-30 下午03:07:04
	 * @修改记录:
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXjydList(String xh) {
		return dao.getXjydList(xh);
	}


	/**
	 * @描述:学生修改家庭成员时需要插入历史记录
	 * @作者：lgx [工号：1553]
	 * @日期：2019/2/25 11:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [xh, list, user]
	 * @return: boolean
	 */
	public boolean updateJtcyxx(String xh, List<JtcyxxModel> list,User user)
			throws Exception {
		return dao.updateJtcyxx(xh, list,user);
	}

	/**
	 *
	 * @描述:家庭成员信息修改
	 * @作者：ligl
	 * @日期：2013-12-19 下午06:43:30
	 * @修改记录:
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean updateJtcyxx(String xh, List<JtcyxxModel> list)
			throws Exception {
		return dao.updateJtcyxx(xh, list);
	}


	/**
	 * 
	 * @描述:通过学号查询家庭成员信息
	 * @作者：ligl
	 * @日期：2013-12-19 下午05:00:01
	 * @修改记录:
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getJtcyxxList(String xh)
			throws Exception {
		return dao.getJtcyxxList(xh);
	}

	/**
	 * 
	 * @描述:通过学号查询家庭成员信息，做显示用
	 * @作者：ligl
	 * @日期：2013-12-19 下午05:00:01
	 * @修改记录:
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getJtcyxxXsList(String xh)
			throws Exception {
		return dao.getJtcyxxXsList(xh);
	}
	
	public List<HashMap<String, String>> getJtcyList(String xh,int n)throws Exception {
		List<HashMap<String, String>> list=dao.getJtcyList(xh,String.valueOf(n));
		int m=n-list.size();
		for (int i = 0; i <m; i++) {
			list.add(new HashMap<String, String>());
		}
		return list;
	}

	/**
	 * 
	 * @描述: 通过学号获取学生学历社会经验信息
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-1-23 下午05:05:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXlshjlList(String xh) {
		return dao.getXlshjlList(xh);
	}

	/**
	 * @描述：学籍卡导出 学历社会经验只取4行
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年1月13日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getXlshjlList4line(String xh) {
		return dao.getXlshjlList4line(xh);
	}
	
	public List<HashMap<String, String>> getXlshjlList(String xh,int n) {
		List<HashMap<String, String>> list=dao.getXlshjlList(xh,String.valueOf(n));
		int m=n-list.size();
		for (int i = 0; i <m; i++) {
			list.add(new HashMap<String, String>());
		}
		return list;
	}
	
	/**
	 * 
	 * @描述:保存学生学历社会经验信息
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-1-24 上午09:15:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean updateXlshjlxx(String xh, List<XlshjlModel> list)
			throws Exception {
		return dao.updateXlshjlxx(xh, list);
	}

	/**
	 * 
	 * @描述:培训信息修改
	 * @作者：ligl
	 * @日期：2014-2-18 下午02:54:58
	 * @修改记录:
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean updatePxxx(String xh, List<PxxxModel> list) throws Exception {
		return dao.updatePxxx(xh, list);
	}

	/**
	 * 
	 * @描述:通过学号查询培训信息信息
	 * @作者：ligl
	 * @日期：2014-2-18 下午02:55:33
	 * @修改记录:
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPxxxList(String xh)
			throws Exception {
		return dao.getPxxxList(xh);
	}

	/**
	 * 
	 * @描述:获奖情况修改
	 * @作者：ligl
	 * @日期：2014-2-18 下午02:54:58
	 * @修改记录:
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean updateHjqk(String xh, List<HjqkModel> list) throws Exception {
		return dao.updateHjqk(xh, list);
	}

	/**
	 * 
	 * @描述:通过学号查询获奖信息
	 * @作者：ligl
	 * @日期：2014-2-18 下午02:55:33
	 * @修改记录:
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getHjqkList(String xh)
			throws Exception {
		return dao.getHjqkList(xh);
	}
	
	/**
	 * @描述:获奖情况修改（新）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-12-3 下午01:53:07
	 * @修改记录:
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean updateHjqkNew(String xh, List<HjqkNewModel> list) throws Exception {
		return dao.updateHjqkNew(xh, list);
	}
	
	/**
	 * 
	 * @描述:通过学号查询获奖情况（新）
	 * @作者：taogj[工号：1075]
	 * @日期：2017-10-23 上午10:24:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param n
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxHjqkNewList(String xh,int n)
	throws Exception {
		List<HashMap<String, String>> list = dao.getXsxxHjqkNewList(xh,String.valueOf(n));
		int m=n-list.size();
		for (int i = 0; i <m; i++) {
			list.add(new HashMap<String, String>());
		}
		return list;
	}
	
	/**
	 * @描述:通过学号查询获奖信息（新）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-12-3 下午01:53:07
	 * @修改记录:
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getHjqkNewList(String xh)
	throws Exception {
		return dao.getHjqkNewList(xh);
	}
	
	/**
	 * 
	 * @描述: 跟岗实习信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-17 上午10:11:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getGgsxjlList(String xh) throws Exception {
		return dao.getGgsxjlList(xh);
	}
	
	/**
	 * 
	 * @描述: 跟岗实习信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-17 上午11:07:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateGgsxjl(String xh, List<GgsxjlModel> list) throws Exception {
		return dao.updateGgsxjl(xh, list);
	}
	
	/**
	 * 
	 * @描述: 顶岗实习信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-17 上午10:11:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDgsxjlList(String xh) throws Exception {
		return dao.getDgsxjlList(xh);
	}
	
	/**
	 * 
	 * @描述: 顶岗实习信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-17 上午11:07:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateDgsxjl(String xh, List<DgsxjlModel> list) throws Exception {
		return dao.updateDgsxjl(xh, list);
	}
	
	/**
	 * 
	 * @描述: 个人简历（查看）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-4-19 下午02:25:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsGrjlList(String xh) throws Exception {
		return dao.getXsGrjlList(xh);
	}

	
	/**
	 * 
	 * @描述: 个人简历（修改）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-4-19 下午02:28:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXsGrjl(String xh, List<GrjlModel> list) throws Exception {
		return dao.updateXsGrjl(xh, list);
	}
	
	/**
	 * 
	 * @描述: 工作简历（查看）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-4-19 下午02:25:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsGzjlList(String xh) throws Exception {
		return dao.getXsGzjlList(xh);
	}
	

	
	/**
	 * 
	 * @描述: 工作简历（修改）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-4-19 下午02:28:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXsGzjl(String xh, List<GzjlModel> list) throws Exception {
		return dao.updateXsGzjl(xh, list);
	}
	
	/**
	 * 
	 * @描述: 学生银行信息（查看）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-5-16 下午03:40:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsyhxxList(String xh) throws Exception {
		return dao.getXsyhxxList(xh);
	}
	
	/**
	 * 
	 * @描述: 学生银行信息（修改）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-5-16 下午03:41:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXsyhxx(String xh, List<XsyhxxModel> list) throws Exception {
		return dao.updateXsyhxx(xh, list);
	}
	
	/**
	 * 
	 * @描述: 个人奖励信息（查看）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-6-6 下午03:46:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getGrhjxxList(String xh) throws Exception {
		return dao.getGrhjxxList(xh);
	}
	
	/**
	 * 
	 * @描述: 浙大学籍异动显示
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-6-27 下午04:49:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZdxjydList(String xh) throws Exception { 
		return dao.getZdxjydList(xh);
	}
	
	/**
	 * 
	 * @描述: 个人奖励信息（修改）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-6-6 下午03:46:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateGrhjxx(String xh, List<GrhjxxModel> list) throws Exception {
		return dao.updateGrhjxx(xh, list);
	}
	
	/**
	 * 
	 * @描述:获取学生干部信息列表
	 * @作者：张小彬[工号：1024]
	 * @日期：2014-4-15 上午10:28:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXsgbxxList(String xh)
			throws Exception {
		return dao.getXsgbxxList(xh);
	}

	/**
	 * 
	 * @描述:获取假期留校信息列表
	 * @作者：张小彬[工号：1024]
	 * @日期：2014-4-15 上午10:28:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getJqlxxxList(String xh)
			throws Exception {
		return dao.getJqlxxxList(xh);
	}

	/**
	 * 
	 * @描述:获取请假信息列表
	 * @作者：张小彬[工号：1024]
	 * @日期：2014-4-15 下午02:14:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getQjjgxxList(String xh)
			throws Exception {
		return dao.getQjjgxxList(xh);
	}

	/**
	 * 
	 * @描述:获取证件补办信息列表
	 * @作者：张小彬[工号：1024]
	 * @日期：2014-4-15 下午02:28:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getZjbbxxList(String xh)
			throws Exception {
		return dao.getZjbbxxList(xh);
	}

	/**
	 * 
	 * @描述:获取火车优惠卡信息列表
	 * @作者：张小彬[工号：1024]
	 * @日期：2014-4-15 下午02:28:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getHcyhkxxList(String xh)
			throws Exception {
		return dao.getHcyhkxxList(xh);
	}

	/**
	 * 
	 * @描述:获取学期报到注册信息列表
	 * @作者：张小彬[工号：1024]
	 * @日期：2014-4-15 下午02:28:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXqbdzcxxList(String xh)
			throws Exception {
		return dao.getXqbdzcxxList(xh);
	}

	/**
	 * 
	 * @描述:获取公寓违纪信息列表
	 * @作者：张小彬[工号：1024]
	 * @日期：2014-4-15 下午03:16:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getGywjxxList(String xh)
			throws Exception {
		return dao.getGywjxxList(xh);
	}

	/**
	 * 
	 * @描述:获取最后一周公寓评优并且在提交检查日程的最后一周（浙江理工个性化）
	 * @作者：cq [工号：785]
	 * @日期：2014-7-2 下午03:06:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getLgGypyxxList(String xh)
			throws Exception {
		return dao.getLgGypyxxList(xh);
	}

	/**
	 * 
	 * @描述:获取费用发放信息列表
	 * @作者：张小彬[工号：1024]
	 * @日期：2014-4-15 下午03:16:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getFyffxxList(String xh)
			throws Exception {
		return dao.getFyffxxList(xh);
	}

	/**
	 * 
	 * @描述:获取考勤信息列表
	 * @作者：陶钢军[工号：1075]
	 * @日期：2014-6-13 上午10:00:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getKqxxList(String xh)
			throws Exception {
		return dao.getKqxxList(xh);
	}
	
	public List<HashMap<String, String>> getZwzxKqxxList(String xh)
	throws Exception {
		return dao.getZwzxKqxxList(xh);
}
	/**
	 * 
	 * @描述:爱心超市物品申请信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-4 下午03:39:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getWpsqjgList(String xh)
	throws Exception {
		return dao.getWpsqjgList(xh);
}
	/**
	 * 
	 * @描述:绿色通道申请信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-4 下午03:40:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getLstdList(String xh)
	throws Exception {
		return dao.getWpsqjgList(xh);
}

	/**
	 * 
	 * @描述:获取公寓卫生分（理工个性化——获取最后一次录入分数）
	 * @作者：cq [工号：785]
	 * @日期：2014-6-23 上午11:03:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getGywsfList(String xh)
			throws Exception {
		return dao.getGywsfList(xh);
	}
	
	/**
	 * 获取公寓卫生分列表（所有记录）
	 */
	public List<HashMap<String, String>> getGywsfAllList(String xh) {
		return dao.getGywsfAllList(xh);
	}
	
	/**
	 * @描述:由于模板限制，需要把评奖 、学生干部查询结果合并
	 * @作者：江水才[工号：1150]
	 * @日期：2014-10-16 上午08:41:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String , String>> getPjXsgbxxList(String xh) throws Exception{
		return dao.getPjXsgbxxList(xh);
	}	
	
	/**
	 * 
	 * @描述: 判断是否低保
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-10-22 下午05:04:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public String getSfdb(String xh) {
		
		return dao.getSfdb(xh);
	}
	
	
	/**
	 * 
	 * @描述: 获取当前系统年月日
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-10-23 上午10:33:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getDqrq(String xh) {
		
		return dao.getDqrq(xh);
	}

	/**
	 * @throws Exception  
	 * @描述:浙江大学学园查询，个性格
	 * @作者：cq [工号：785]
	 * @日期：2015-5-11 上午11:41:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXycx(XsxxglModel model, User user) throws Exception {
		return dao.getXycx(model,user);
	}
	
	public List<HashMap<String, String>> getTyqntjList(XsxxglModel exporModel,User user) throws Exception{
		return dao.getTyqntjList(exporModel,user);
	}
	/**
	 * 
	 * @描述:团员基本信息统计表
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-8 上午09:43:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param resultList
	 * @param os
	 * @param user
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void tyqntjExport(List<HashMap<String,String>> resultList, OutputStream os, User user) throws Exception {
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("团员基本信息统计表", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 9, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// 填充标题
		ExcelMB ex = new ExcelMB();
		ex.printToOneCell_multy(ws, "团员基本信息统计表", 0, 0, 9, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		ex.printToOneCell_multy(ws, "单位：", 0, 1, 9, true,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		ws.mergeCells(0, 0, 9, 0);
		ws.mergeCells(0, 1, 9, 1);
		ws.mergeCells(0, 2, 0, 3);
		ws.mergeCells(1, 2, 1, 3);
		ws.mergeCells(2, 2, 2, 3);
		ws.mergeCells(3, 2, 3, 3);
		ws.mergeCells(4, 2, 5, 2);
		ws.mergeCells(6, 2, 6, 3);
		ws.mergeCells(7, 2, 8, 2);
		ws.mergeCells(9, 2, 9, 3);
		ws.mergeCells(10, 2, 10, 3);
		ws.mergeCells(11, 2, 11, 3);
		
		ws.addCell(new Label(0, 2, "年级", wcf2));
		ws.addCell(new Label(1, 2, "团支部数", wcf2));
		ws.addCell(new Label(2, 2, "学生总数", wcf2));
		ws.addCell(new Label(3, 2, "本科学生总数", wcf2));
		ws.addCell(new Label(4, 2, "团员数", wcf2));
		ws.addCell(new Label(4, 3, "男", wcf2));
		ws.addCell(new Label(5, 3, "女", wcf2));
		ws.addCell(new Label(6, 2, "专科学生人数", wcf2));
		ws.addCell(new Label(7, 2, "团员数", wcf2));
		ws.addCell(new Label(7, 3, "男", wcf2));
		ws.addCell(new Label(8, 3, "女", wcf2));
		ws.addCell(new Label(9, 2, "非团员数", wcf2));
	    int sumTzbs=0;
	    int sumXszs=0;
	    int sumBks=0;
	    int sumZks=0;
	    int sumbkty_man=0;
	    int sumbkty_woman=0;
	    int sumzkty_man=0;
	    int sumzkty_woman=0;
	    int sumzkty_ftys=0;
		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> map = resultList.get(i);
				sumTzbs = sumTzbs+Integer.parseInt(map.get("tzbs"));
				sumXszs = sumXszs+Integer.parseInt(map.get("xszs"));
				sumBks = sumBks+Integer.parseInt(map.get("bkxsrs"));
				sumZks=sumZks+Integer.parseInt(map.get("zkxsrs"));
				sumbkty_man = sumbkty_man+Integer.parseInt(map.get("bktys_man"));
				sumbkty_woman = sumbkty_woman+Integer.parseInt(map.get("bktys_woman"));
				sumzkty_man = sumzkty_man+Integer.parseInt(map.get("zktys_man"));
				sumzkty_woman = sumzkty_woman+Integer.parseInt(map.get("zktys_woman"));
				sumzkty_ftys = sumzkty_ftys+Integer.parseInt(map.get("ftys"));
				ws.setColumnView(0, 5);
				ws.setColumnView(1, 10);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 15);
				ws.setColumnView(4, 15);
				ws.setColumnView(5, 20);
				ws.setColumnView(6, 20);
				ws.setColumnView(7, 15);
				ws.setColumnView(8, 15);
				ws.setColumnView(9, 10);
				ws.addCell(new Label(0, 4 + i, map.get("nj"), wcf2));
				ws.addCell(new Label(1, 4 + i, map.get("tzbs"), wcf2));
				ws.addCell(new Label(2, 4 + i, map.get("xszs"), wcf2));
				ws.addCell(new Label(3, 4 + i, map.get("bkxsrs"), wcf2));
				ws.addCell(new Label(4, 4 + i, map.get("bktys_man"), wcf2));
				ws.addCell(new Label(5, 4 + i, map.get("bktys_woman"), wcf2));
				ws.addCell(new Label(6, 4 + i, map.get("zkxsrs"), wcf2));
				ws.addCell(new Label(7, 4 + i, map.get("zktys_man"), wcf2));
				ws.addCell(new Label(8, 4 + i, map.get("zktys_woman"), wcf2));
				ws.addCell(new Label(9, 4 + i, map.get("ftys"), wcf2));
			}
		}
		ws.addCell(new Label(0, 4 + resultList.size(), "合计", wcf2));
		ws.addCell(new Label(1, 4 + resultList.size(), sumTzbs+"", wcf2));
		ws.addCell(new Label(2, 4 + resultList.size(), sumXszs+"", wcf2));
		ws.addCell(new Label(3, 4 + resultList.size(), sumBks+"", wcf2));
		ws.addCell(new Label(4, 4 + resultList.size(), sumbkty_man+"", wcf2));
		ws.addCell(new Label(5, 4 + resultList.size(), sumbkty_woman+"", wcf2));
		ws.addCell(new Label(6, 4 + resultList.size(), sumZks+"", wcf2));
		ws.addCell(new Label(7, 4 + resultList.size(), sumzkty_man+"", wcf2));
		ws.addCell(new Label(8, 4 + resultList.size(), sumzkty_woman+"", wcf2));
		ws.addCell(new Label(9, 4 + resultList.size(), sumzkty_ftys+"", wcf2));
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-6-8 下午06:12:14
	 * @描述:浙江警察学院学生在校情况导出
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * File 返回类型 
	 * @throws
	 */
	public File getZjjcXsbxExcel(XsxxglModel model, User user) throws Exception{
		
		//生成Excle文件
		File tempxls = new File(System.getProperty("java.io.tmpdir")+"/"+System.currentTimeMillis()+".xls");
		WritableWorkbook wwb = Workbook.createWorkbook(tempxls);
		WritableSheet ws = wwb.createSheet("毕业生在校表现情况一览表", 0);
		
	    //设置表头样式
		WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式
		wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 9, true, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		//设置表头
		ws.mergeCells(0, 0, 0, 1);
		ws.mergeCells(1, 0, 1, 1);
		ws.mergeCells(2, 0, 2, 1);
		ws.mergeCells(3, 0, 3, 1);
		ws.mergeCells(4, 0, 4, 1);
		ws.mergeCells(5, 0, 5, 1);
		ws.mergeCells(6, 0, 6, 1);
		ws.mergeCells(7, 0, 7, 1);
		ws.mergeCells(8, 0, 8, 1);
		ws.mergeCells(9, 0, 9, 1);
		ws.mergeCells(10, 0, 10, 1);
		ws.mergeCells(11, 0, 11, 1);
		ws.mergeCells(12, 0, 12, 1);
		ws.mergeCells(13, 0, 21, 0);
		ws.mergeCells(22, 0, 30, 0);
		ws.mergeCells(31, 0, 31, 1);
		ws.mergeCells(32, 0, 32, 1);
		ws.mergeCells(33, 0, 33, 1);
		ws.mergeCells(34, 0, 34, 1);
		
		ws.addCell(new Label(0, 0, "序号", wcf));
		ws.addCell(new Label(1, 0, "生源地", wcf));
		ws.addCell(new Label(2, 0, "代码", wcf));
		ws.addCell(new Label(3, 0, "区队", wcf));
		ws.addCell(new Label(4, 0, "学号", wcf));
		ws.addCell(new Label(5, 0, "姓名", wcf));
		ws.addCell(new Label(6, 0, "性别", wcf));
		ws.addCell(new Label(7, 0, "民族", wcf));
		ws.addCell(new Label(8, 0, "出生年月", wcf));
		ws.addCell(new Label(9, 0, "政治面貌", wcf));
		ws.addCell(new Label(10, 0, "专业", wcf));
		ws.addCell(new Label(11, 0, "身份证号", wcf));
		ws.addCell(new Label(12, 0, "担任干部情况", wcf));
		ws.addCell(new Label(13, 0, "学习总成绩", wcf));
		ws.addCell(new Label(13, 1, "一", wcf));
		ws.addCell(new Label(14, 1, "二", wcf));
		ws.addCell(new Label(15, 1, "三", wcf));
		ws.addCell(new Label(16, 1, "四", wcf));
		ws.addCell(new Label(17, 1, "五", wcf));
		ws.addCell(new Label(18, 1, "六", wcf));
		ws.addCell(new Label(19, 1, "七", wcf));
		ws.addCell(new Label(20, 1, "平均分", wcf));
		ws.addCell(new Label(21, 1, "名次", wcf));
		ws.addCell(new Label(22, 0, "综合测评总成绩", wcf));
		ws.addCell(new Label(22, 1, "一", wcf));
		ws.addCell(new Label(23, 1, "二", wcf));
		ws.addCell(new Label(24, 1, "三", wcf));
		ws.addCell(new Label(25, 1, "四", wcf));
		ws.addCell(new Label(26, 1, "五", wcf));
		ws.addCell(new Label(27, 1, "六", wcf));
		ws.addCell(new Label(28, 1, "七", wcf));
		ws.addCell(new Label(29, 1, "平均分", wcf));
		ws.addCell(new Label(30, 1, "名次", wcf));
		ws.addCell(new Label(31, 0, "奖惩情况", wcf));
		ws.addCell(new Label(32, 0, "英语等级", wcf));
		ws.addCell(new Label(33, 0, "计算机等级", wcf));
		ws.addCell(new Label(34, 0, "备注", wcf));

		
        //设置数据集样式
    	WritableCellFormat cellFormat=new WritableCellFormat();
    	cellFormat = ExcelMethods.getWcf(WritableFont.ARIAL,9, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		List<HashMap<String,String>> result = dao.getXszxData(model, user);
		
		int rindex = 2;  //行
		int cindex = 0;  //列
		for(int i=0;i<result.size();i++){
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("rn"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("sydqmc"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("syd"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("bjmc"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("xh"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("xm"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("xb"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("mzmc"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("csrq"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zzmmmc"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zymc"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("sfzh"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zwmc"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("cj1"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("cj2"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("cj3"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("cj4"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("cj5"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("cj6"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("cj7"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("cjpjf"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("cjpm"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zpcj1"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zpcj2"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zpcj3"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zpcj4"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zpcj5"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zpcj6"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zpcj7"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zpcjpjf"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("zpcjpm"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("jcqk"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("yydj"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get("jsjdj"));
			addCell(ws,cindex++,rindex,cellFormat,result.get(i).get(""));
			rindex++;
			cindex=0;
		}
		wwb.write();
		wwb.close();
		return tempxls;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-6-8 下午06:12:14
	 * @描述:浙江警察学院学生在校情况导出
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * File 返回类型 
	 * @throws
	 */
	public File getZjjcZcfExcel(String xh) throws Exception{
		
		//生成Excle文件
		File tempxls = new File(System.getProperty("java.io.tmpdir")+"/"+System.currentTimeMillis()+".xls");
		WritableWorkbook wwb = Workbook.createWorkbook(tempxls);
		WritableSheet ws = wwb.createSheet("学生综合素质测评汇总表", 0);
		ws.setColumnView(0, 5);
		ws.setColumnView(5, 14);
		ws.setColumnView(6, 14);
		ws.setColumnView(7, 16);
		ws.setRowView(0, 460); 
		ws.setRowView(1, 380); 
		ws.setRowView(2, 380); 
	    //设置表头样式
		WritableCellFormat title = new WritableCellFormat(); // 构造单元格格式
		title = ExcelMethods.getWcf(WritableFont.ARIAL, 18, true, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.NONE);
		ws.mergeCells(1, 0, 7, 0);
	
		addCell(ws,1,0,title,"浙江警察学院学生综合素质测评汇总表");
		
		//设置中部样式
		WritableCellFormat center = new WritableCellFormat(); // 构造单元格格式
		center = ExcelMethods.getWcf(WritableFont.ARIAL, 14, false, Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.NONE);
		
		
		//设置中部样式
		WritableCellFormat content = new WritableCellFormat(); // 构造单元格格式
		content = ExcelMethods.getWcf(WritableFont.ARIAL, 12, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		//获取学生信息
		HashMap<String, String> xhs = dao.getXsxxByXh(xh);
		
		addCell(ws,1,1,center,"姓名:");
		addCell(ws,2,1,center,xhs.get("xm"));
		addCell(ws,3,1,center,"学号:");
		addCell(ws,4,1,center,xhs.get("xh"));
		addCell(ws,5,1,center,"大队:");
		addCell(ws,6,1,center,xhs.get("xymc"));
		addCell(ws,7,1,center,"区队:"+xhs.get("bjmc"));
		addCell(ws,1,2,content,"学期");
		addCell(ws,2,2,content,"项目");
		addCell(ws,3,2,content,"成绩");
		addCell(ws,4,2,content,"等级");
		addCell(ws,5,2,content,"区队意见");
		addCell(ws,6,2,content,"大队意见");
		addCell(ws,7,2,content,"本人签名");
		
		//学期的表头
		ws.mergeCells(1, 3, 1, 6);
		addCell(ws,1,3,content,"第           一          学           期");
		ws.mergeCells(1, 7, 1, 10);
		addCell(ws,1,7,content,"第           二          学           期");
		ws.mergeCells(1, 11, 1, 14);
		addCell(ws,1,11,content,"第           三          学           期");
		ws.mergeCells(1, 15, 1, 18);
		addCell(ws,1,15,content,"第           四          学           期");
		ws.mergeCells(1, 19, 1, 22);
		addCell(ws,1,19,content,"第           五          学           期");
		ws.mergeCells(1, 23, 1, 26);
		addCell(ws,1,23,content,"第           六         学           期");
		ws.mergeCells(1, 27, 1, 30);
		addCell(ws,1,27,content,"第           七         学           期");
		ws.mergeCells(1, 31, 1, 34);
		addCell(ws,1,31,content,"第           八         学           期");
		

		
        for(int i=0;i<32;i++){
        	if((i+1)%4==1){
        	  addCell(ws,2,i+3,content,"德育"); 
        	}else if((i+1)%4==2){
        	  addCell(ws,2,i+3,content,"智育");	
        	}else if((i+1)%4==3){
        	  addCell(ws,2,i+3,content,"体育");
        	}else{
        	  addCell(ws,2,i+3,content,"综合");	
        	}
        	addCell(ws,3,i+3,content,"");
        	addCell(ws,4,i+3,content,"");
        	addCell(ws,5,i+3,content,"");
        	addCell(ws,6,i+3,content,"");
        	addCell(ws,7,i+3,content,"");
        	ws.setRowView(i+3, 380); 
        }
        
        ws.mergeCells(1, 35, 2, 37);
        addCell(ws,1,35,content,"学生工作处         意见");
        ws.setRowView(37, 860); 
        ws.mergeCells(3, 35, 7, 37);
        addCell(ws,3,35,content,"");
        
        
		int rindex = 0;  //行
		int cindex = 3;  //列
		
		String[] xnlist = getAllxn(xhs.get("nj"));
		String[] xqlist = new String[]{"01","02"};
		ZcfsDao zcfsDao = new  ZcfsDao();
		//结果集
		List<HashMap<String,String>> zcf = zcfsDao.getAllZcfListByXh(xh);
		
		for(int i=0;i<xnlist.length;i++){
			for(int j=0;j<xqlist.length;j++){
				rindex =(rindex > 0) ? rindex+4:rindex+3;
				for(int m=0;m<zcf.size();m++){
					if(zcf.get(m).get("xn").equals(xnlist[i])&&
							zcf.get(m).get("xq").equals(xqlist[j])){
						if(zcf.get(m).get("xmmc").contains("德育")){
							 addCell(ws,cindex,rindex,content,zcf.get(m).get("fs"));
						}else if(zcf.get(m).get("xmmc").contains("智育")){
							addCell(ws,cindex,rindex+1,content,zcf.get(m).get("fs"));
						}else if(zcf.get(m).get("xmmc").contains("体育")){
							addCell(ws,cindex,rindex+2,content,zcf.get(m).get("fs"));
						}else if(zcf.get(m).get("xmmc").contains("综测总分")){
							addCell(ws,cindex,rindex+3,content,zcf.get(m).get("fs"));
						}
						
					}
				}
			}
		}
		wwb.write();
		wwb.close();
		return tempxls;
	}
	
	
	/**
	 * @throws Exception 
	 * 
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-6-10 下午04:12:14
	 * @描述:浙江警察学院学生在校情况导出
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * File 返回类型 
	 * @throws
	 */
	public File getZjjcZhqkExcel(XsxxglModel model, User user) throws Exception{
		
		//生成Excle文件
		File tempxls = new File(System.getProperty("java.io.tmpdir")+"/"+System.currentTimeMillis()+".xls");
		WritableWorkbook book = Workbook.createWorkbook(tempxls);
		WritableSheet sheet = book.createSheet("基本信息加简历加特征分班结果", 0);
		
	    //设置表头样式
		WritableCellFormat title = new WritableCellFormat(); // 构造单元格格式
		title = ExcelMethods.getWcf(WritableFont.ARIAL,10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, Border.ALL);
		sheet.mergeCells(0, 0, 45, 0);
		addCell(sheet,0,0,title,"学生在校表现综合情况一览表");
		sheet.setRowView(0, 460); 
		sheet.mergeCells(0, 1, 0, 2);
		addCell(sheet,0,1,title,"学号");
		sheet.mergeCells(1, 1, 1, 2);
		addCell(sheet,1,1,title,"姓名");
		sheet.mergeCells(2, 1, 3, 1);
		sheet.setRowView(1, 500); 
		sheet.setRowView(2, 800); 
		addCell(sheet,2,1,title,"生源地");
		addCell(sheet,2,2,title,"地市");
		addCell(sheet,3,2,title,"户籍地");
		sheet.mergeCells(4, 1, 4, 2);
		addCell(sheet,4,1,title,"性别");
		sheet.mergeCells(5, 1, 5, 2);
		addCell(sheet,5,1,title,"科类");
		sheet.mergeCells(6, 1, 6, 2);
		addCell(sheet,6,1,title,"专业区队");
		sheet.mergeCells(7, 1, 7, 2);
		addCell(sheet,7,1,title,"班级");
		sheet.mergeCells(8, 1, 13, 1);
		addCell(sheet,8,1,title,"担任干部");
		addCell(sheet,8,2,title,"第一学期");
		addCell(sheet,9,2,title,"第二学期");
		addCell(sheet,10,2,title,"第三学期");
		addCell(sheet,11,2,title,"第四学期");
		addCell(sheet,12,2,title,"第五学期");
		addCell(sheet,13,2,title,"第六学期");
		sheet.mergeCells(14, 1, 14, 2);
		addCell(sheet,14,1,title,"政治面貌");
		sheet.mergeCells(15, 1, 15, 2);
		addCell(sheet,15,1,title,"建表时间");
		sheet.setColumnView(15, 14); 
		sheet.mergeCells(16, 1, 16, 2);
		addCell(sheet,16,1,title,"发展时间");
		sheet.mergeCells(17, 1, 21, 1);
		addCell(sheet,17,1,title,"省统考");
		addCell(sheet,17,2,title,"驾驶考试");
		addCell(sheet,18,2,title,"计算机一级");
		addCell(sheet,19,2,title,"计算机二级");
		addCell(sheet,20,2,title,"英语四级");
		addCell(sheet,21,2,title,"英语六级");
		sheet.mergeCells(22, 1, 26, 1);
		addCell(sheet,22,1,title,"第一学期");
		sheet.mergeCells(27, 1, 31, 1);
		addCell(sheet,27,1,title,"第二学期");
		sheet.mergeCells(32, 1, 36, 1);
		addCell(sheet,32,1,title,"第三学期");
		sheet.mergeCells(37, 1, 41, 1);
		addCell(sheet,37,1,title,"第四学期");
		int cindex = 22;
		for(int i=0;i<4;i++){
			addCell(sheet,cindex++,2,title,"德育成绩");
			addCell(sheet,cindex++,2,title,"综合测评");
			addCell(sheet,cindex++,2,title,"奖学金");
			addCell(sheet,cindex++,2,title,"奖惩");
			addCell(sheet,cindex++,2,title,"评优");
		}
		sheet.mergeCells(42, 1, 42, 2);
		addCell(sheet,42,1,title,"身份证号");
		sheet.setColumnView(42, 20); 
		sheet.mergeCells(43, 1, 43, 2);
		addCell(sheet,43,1,title,"家庭地址");
		sheet.setColumnView(43, 48); 
		sheet.mergeCells(44, 1, 44, 2);
		addCell(sheet,44,1,title,"邮政编码");
		sheet.mergeCells(45, 1, 45, 2);
		addCell(sheet,45,1,title,"学生联系方式");
		sheet.setColumnView(45, 14); 
		
		//获取结果集
		List<HashMap<String, String>> result = dao.getZhqkData(model, user);
		
		//设置内容样式
		WritableCellFormat content = new WritableCellFormat(); // 构造单元格格式
		content = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		int rindex = 3;
		    cindex = 0;
		for(int i=0;i<result.size();i++){
			addCell(sheet,cindex++,rindex,content,result.get(i).get("xh"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("xm"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("ds"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("hjd"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("xb"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("kslbmc"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("zymc"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("bjmc"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("zwmc1"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("zwmc2"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("zwmc3"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("zwmc4"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("zwmc5"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("zwmc6"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("jdmc"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("kssj"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("fzsj"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("jstgqk"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("jsj1j"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("jsj2j"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("yysj"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("yylj"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("dyf1"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("zpcj1"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("hjnum1"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("jcqk1"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("rynum1"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("dyf2"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("zpcj2"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("hjnum2"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("jcqk2"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("rynum2"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("dyf3"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("zpcj3"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("hjnum3"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("jcqk3"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("rynum3"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("dyf4"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("zpcj4"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("hjnum4"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("jcqk4"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("rynum4"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("sfzh"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("jtdz"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("jtyb"));
			addCell(sheet,cindex++,rindex,content,result.get(i).get("sjhm"));
			rindex++;
			cindex = 0;
		}
		book.write();
		book.close();
		return tempxls;
	}
		
	public void addCell(WritableSheet sheet, int c, int r, WritableCellFormat cellFormat, String str) throws RowsExceededException, WriteException{
		Label label = new Label(c,r,str);
		label.setCellFormat(cellFormat);
		sheet.addCell(label);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-6-9下午06:12:14
	 * @描述:浙江警察学院获取学生所有学年
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param nj
	 * @return
	 * File 返回类型 
	 * @throws
	 */
	public String[] getAllxn(String nj) {
		if (StringUtil.isNull(nj)) {
			return new String[] {};
		}
		int num;
		try {
			num = Integer.parseInt(nj);
		} catch (NumberFormatException e) {
			return new String[] {};
		}
		String[] xnlist = new String[4];
		
		for (int i = 0; i < 4; i++) {
			xnlist[i] = (num + i) + "-" + (num + i + 1);
		}
		return xnlist;
	}
	
	
	/**
	 * 
	 * @描述:获取成都体院的成绩信息用于打印学籍卡
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-6-30 上午10:51:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getCdtyCjxxByxh(String xh){
		return dao.getCdtyCjxxByxh(xh);
	}
	
	
	/**
	 * 
	 * @描述:效率问题重写方法获取学生信息
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-9-23 上午10:51:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxListForYdxg(XsxxglModel model,
			User user) throws Exception {
		return dao.getXsxxListForYdxg(model, user);
	}
	public List<HashMap<String, String>> getXsxxListForYdxgOfAll(XsxxglModel model,
			User user) throws Exception {
		model.getPages().setPageSize(Integer.MAX_VALUE);
		return dao.getXsxxListForYdxg(model, user);
	}

	
	/**
	 * 查询学生证信息
	 */
	public HashMap<String, String> cxXsz(String csdm, User user) throws Exception{
		return dao.cxXsz(csdm, user);
	}
	
	/**
	 * 保存学生证信息
	 */
	public boolean bcXsz(String csdm, String csz, User user) throws Exception{
		return dao.bcXsz(csdm, csz, user);
	}
	
	/**
	 * 
	 * @描述: 取学生获奖情况最新4条（温州大学个性化）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-10-29 上午11:10:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getHjqkNewFourList(String xh) throws Exception {
		return dao.getHjqkNewFourList(xh);
	}
	
	/**
	 * @描述: 通过学号获取个人简历对日期进行分割--杭州汽车高级技工学校学籍卡
	 * @作者：孟威[工号：1186]
	 * @日期：2015-9-29 上午09:40:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
		public List<HashMap<String, String>> getGrjlList(String xh) {
			return dao.getGrjlList(xh);
		}
		/**
		 * @throws Exception 
		 * @描述: 通过学号抽取学业成绩1--3学年
		 * @作者：孟威[工号：1186]
		 * @日期：2015-10-15 上午09:23:15
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param xh
		 * @return
		 * List<HashMap<String,String>> 返回类型 
		 * @throws
		 */
		public List<HashMap<String, String>> getXycjoneList(String xh) throws Exception {
			return dao.getXycjoneList(xh);
		}
		/**
		 * @描述: 通过学号抽取学业成绩4--6学年
		 * @作者：孟威[工号：1186]
		 * @日期：2015-10-19 上午11:33:35
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param xh
		 * @return
		 * @throws Exception
		 * List<HashMap<String,String>> 返回类型 
		 * @throws
		 */
		public List<HashMap<String, String>> getXycjtwoList(String xh) throws Exception {
			return dao.getXycjtwoList(xh);
		}
		/**
		 * @描述: 浙江大学新生入学登记表_个性化！个人简历年月截取
		 * @作者：孟威[工号：1186]
		 * @日期：2015-12-4 上午11:49:22
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param xh
		 * @return
		 * List<HashMap<String,String>> 返回类型 
		 * @throws
		 */
			public List<HashMap<String, String>> getXxhgzjlList(String xh) {
				return dao.getXxhgzjlList(xh);
			}
		/**
		 * @描述:南通职业技术学院操行等第
		 * @作者：孟威[工号：1186]
		 * @日期：2016-4-1 上午11:30:25
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param xh
		 * @return
		 * List<HashMap<String,String>> 返回类型 
		 * @throws
		 */
			public List<HashMap<String, String>> getCxdt(String xh) {
				return dao.getCxdt(xh);
			}
		/**
		 * @描述: 南通科技职业学院个性化获奖情况
		 * @作者：孟威[工号：1186]
		 * @日期：2016-5-10 上午11:00:26
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param xh
		 * @return
		 * List<HashMap<String,String>> 返回类型 
		 * @throws
		 */
			public List<HashMap<String, String>> gethjqk(String xh) {
				return dao.gethjqk(xh);
			}
		/**
		 * @描述: 获取学生学年学期成绩
		 * @作者：孟威[工号：1186]
		 * @日期：2016-5-17 上午09:28:12
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param xn
		 * @param xn1
		 * @param xq
		 * @param xh
		 * @return
		 * @throws Exception
		 * List<HashMap<String,String>> 返回类型 
		 * @throws
		 */
		public List<HashMap<String, String>> getXnXqcj(String xn,String xn1,String xq,String xh) throws Exception {
			return dao.getXnXqcj(xn,xn1,xq,xh);
		}
		/**
		 * @描述: 获取学生课程学期和总学分
		 * @作者：孟威[工号：1186]
		 * @日期：2016-5-17 上午09:29:13
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param xh
		 * @return
		 * HashMap<String,String> 返回类型 
		 * @throws
		 */
		public HashMap<String, String> getKcxq(String xh){
			return dao.getKcxq(xh);
		}
		
		/**
		 * @描述: 等级考试成绩[南通科技]
		 * @作者：孟威[工号：1186]
		 * @日期：2016-5-26 下午07:45:32
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param xh
		 * @return
		 * HashMap<String,String> 返回类型 
		 * @throws
		 */
		public List<HashMap<String, String>> getDjkscj(String xh){
			return dao.getDjkscj(xh);
		}

		/**
		 * @描述: 毕业设计相关内容
		 * @作者：孟威[工号：1186]
		 * @日期：2016-5-25 下午07:45:32
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param xh
		 * @return
		 * HashMap<String,String> 返回类型 
		 * @throws
		 */
		public HashMap<String, String> getBysj(String xh){
			return dao.getBysj(xh);
		}
		
	/**
	 * 	
	 * @描述: 总学分平均绩点（浙大）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-5-23 上午10:02:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXfjdList(String xh,String nj)
			throws Exception {
		if(xgxt.utils.String.StringUtils.isNull(nj)) {
			nj = "0";
		}
		return dao.getXfjdList(xh,nj);
	}	
	
	/*
	 * 取对应学生年级
	 */
	public String getXsnj(String xh) {
		return dao.getXsnj(xh);
	}
	
	/**
	 * 
	 * @描述: 西安培华学生证打印获取学生信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-24 下午07:01:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXszdyxxXaph(String xh){
		return dao.getXszdyxxXaph(xh);
	}
	
	/**
	 * 
	 * @描述: 获奖附件上传的信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-27 下午03:01:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getFileData(String xh){
		return dao.getFileData(xh);
	}
	
	/**
	 * 
	 * @描述:个人奖励信息（修改）结果
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-10 下午07:09:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param list
	 * @param hjid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateGrhjxxByjg(String xh, List<GrhjxxModel> list,String[] hjid) throws Exception {
		
		return dao.updateGrhjxxByjg(xh, list, hjid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 插入密码修改日志表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-19 下午04:10:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ip
	 * @param czr
	 * @param xgmmxh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean runInsertMmXgLog(String ip,String czr,String xgmmxh) throws Exception{
		return dao.runInsertMmXgLog(ip, czr, xgmmxh);
	}
	
	/**
	 * @描述:华师大把个人简历和工作简历放到一起取值
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2016-10-21 下午10:08:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getGrjlShjlList(String xh) {
		return dao.getGrjlShjlList(xh);
	}

	/** 
	 * @描述：科学研究
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月15日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * Object 返回类型 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getKxyjList(String xh) throws Exception {
		return dao.getKxyjList(xh);
	}
	
	/**
	 * @描述：科学研究
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月15日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean updateKxyj(String xh, List<KxyjModel> list) throws Exception {
		return dao.updateKxyj(xh, list);
	}
	
	/** 
	 * @描述：课题研究
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月15日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * Object 返回类型 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getKtyjList(String xh) throws Exception {
		return dao.getKtyjList(xh);
	}
	
	/**
	 * @描述：课题研究
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月15日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean updateKtyj(String xh, List<KtyjModel> list) throws Exception {
		return dao.updateKtyj(xh, list);
	}
	
	/** 
	 * @描述：创新创业项目
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月15日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * Object 返回类型 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getCxcyxmList(String xh) throws Exception {
		return dao.getCxcyxmList(xh);
	}
	
	/**
	 * @描述：创新创业项目
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月15日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean updateCxcyxm(String xh, List<CxcyxmModel> list) throws Exception {
		return dao.updateCxcyxm(xh, list);
	}
	
	/** 
	 * @描述：学科竞赛
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月15日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * Object 返回类型 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getXkjsList(String xh) throws Exception {
		return dao.getXkjsList(xh);
	}
	
	/**
	 * @描述：学科竞赛
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月15日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean updateXkjs(String xh, List<XkjsModel> list) throws Exception {
		return dao.updateXkjs(xh, list);
	}
	
	/** 
	 * @描述：技能证书
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月15日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * Object 返回类型 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getJnzsList(String xh) throws Exception {
		return dao.getJnzsList(xh);
	}
	
	/**
	 * @描述：技能证书
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月15日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean updateJnzs(String xh, List<JnzsModel> list) throws Exception {
		return dao.updateJnzs(xh, list);
	}
	
	public List<HashMap<String, String>> getXsjfList(String xh) throws Exception {
		return dao.getXsjfList(xh);
	}

	/**
	 * @描述:保存学生（校外）获奖情况（广州铁路职业技术学院）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月27日 下午5:27:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param hjqkList
	 * @return
	 * boolean 返回类型 
	 * @throws Exception
	 */
	public boolean updateXsxwhjqk(String xh, List<XsxwhjqkModel> hjqkList) throws Exception {
		return dao.updateXsxwhjqk(xh,hjqkList);
	}

	/** 
	 * @描述:获取学生（校外）获奖情况（广州铁路职业技术学院）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月31日 上午9:31:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsxwhjqkList(String xh) {
		return dao.getXsxwhjqkList(xh);
	}
	
	/**
	 * @描述:保存等级考试成绩（西安科技大学）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年6月22日 上午10:57:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param djkscjList
	 * @return
	 * boolean 返回类型 
	 * @throws Exception
	 */
	public boolean updateDjkscj(String xh, List<DjkscjModel> djkscjList) throws Exception {
		return dao.updateDjkscj(xh,djkscjList);
	}

	/** 
	 * @描述:获取等级考试信息List(西安科技大学)
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年6月22日 上午10:25:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDjkscjList(String xh) {
		return dao.getDjkscjList(xh);
	}
	public List<HashMap<String, String>> getZcfsList(String xh) {
		return dao.getZcfsList(xh);
	}
	/** 
	 * @描述:获取学生干部经历List（西安科技大学）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年6月22日 下午7:58:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsgbjlList(String xh) {
		return dao.getXsgbjlList(xh);
	}

	/** 
	 * @描述:获取社会实践情况List（西安科技大学）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年6月22日 下午7:58:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getShsjqkList(String xh) {
		return dao.getShsjqkList(xh);
	}

	/** 
	 * @描述:获取出国出境交流信息List（西安科技大学）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年6月22日 下午7:58:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCgcjjlList(String xh) {
		return dao.getCgcjjlList(xh);
	}

	/**
	 * @描述:保存学生干部经历List（西安科技大学）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年6月22日 下午8:38:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xsgbjlList
	 * @return
	 * boolean 返回类型 
	 * @throws Exception
	 */
	public boolean updateXsgbjl(String xh, List<XsgbjlModel> xsgbjlList) throws Exception {
		return dao.updateXsgbjl(xh,xsgbjlList);
	}

	/**
	 * @描述:保存社会实践情况List（西安科技大学）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年6月22日 下午8:38:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param shsjqkList
	 * @return
	 * boolean 返回类型 
	 * @throws Exception
	 */
	public boolean updateShsjqk(String xh, List<ShsjqkModel> shsjqkList) throws Exception {
		return dao.updateShsjqk(xh,shsjqkList);
	}

	/**
	 * @描述:保存出国出境交流信息List（西安科技大学）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年6月22日 下午8:39:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param cgcjjlList
	 * @return
	 * boolean 返回类型 
	 * @throws Exception
	 */
	public boolean updateCgcjjl(String xh, List<CgcjjlModel> cgcjjlList) throws Exception {
		return dao.updateCgcjjl(xh,cgcjjlList);
	}

	/** 
	 * @描述:获取发表论文List（西安科技大学）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年6月22日 下午11:04:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFblwList(String xh) {
		return dao.getFblwList(xh);
	}

	/** 
	 * @描述:获取科研项目List（西安科技大学）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年6月22日 下午11:05:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getKyxmList(String xh) {
		return dao.getKyxmList(xh);
	}

	/** 
	 * @描述:获取其他成果List（西安科技大学）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年6月22日 下午11:05:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getQtcgList(String xh) {
		return dao.getQtcgList(xh);
	}

	/**
	 * @描述:保存发表论文List（西安科技大学）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年6月22日 下午11:29:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param fblwList
	 * @return
	 * boolean 返回类型 
	 * @throws Exception
	 */
	public boolean updateFblw(String xh, List<FblwModel> fblwList) throws Exception {
		return dao.updateFblw(xh,fblwList);
	}

	/**
	 * @描述:保存科研项目List（西安科技大学）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年6月22日 下午11:29:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param kyxmList
	 * @return
	 * boolean 返回类型 
	 * @throws Exception
	 */
	public boolean updateKyxm(String xh, List<KyxmModel> kyxmList) throws Exception {
		return dao.updateKyxm(xh,kyxmList);
	}

	/**
	 * @描述:保存其他成果List（西安科技大学）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年6月22日 下午11:29:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param qtcgList
	 * @return
	 * boolean 返回类型 
	 * @throws Exception
	 */
	public boolean updateQtcg(String xh, List<QtcgModel> qtcgList) throws Exception {
		return dao.updateQtcg(xh,qtcgList);
	}
	
	public boolean updateHjqkXakj(String xh, List<HjqkModel> hjqkList) throws Exception {
		return dao.updateHjqkListForXakj(xh, hjqkList);
	}
	
	/** 
	 * @描述:获取扶贫办信息（西安科技大学）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年6月23日 上午8:51:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getFpbxx(String xh) {
		return dao.getFpbxx(xh);
	}
	
	/**
	 * @描述：欠费情况
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年7月6日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getQfqk(String xh){
		return dao.getQfqk(xh);
	}
	
	/**
	 * @描述：一卡通消费情况 徐州工程
	 * @作者：zhuon[工号:1391]
	 * @日期：2017年8月18日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public List<HashMap<String, String>> getYktxfls(String xh){
		return dao.getYktxfls(xh);
	}
	//徐州医药 一卡通查询
	public List<HashMap<String, String>> getXsyktcuList(String xh){
		return dao.getXsyktcuList(xh);
	}
	/**
	 * @描述：图书借阅情况 杭州师范大学
	 * @作者：tgj[工号:1075]
	 * @日期：2017年11月28日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public List<HashMap<String, String>> getTsjyList(String xh){
		return dao.getTsjyList(xh);
	}
	
	/** 
	 * @描述:修改实习信息(南京高等职业技术学院)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-26 下午02:26:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param sxxxList
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateSxxx(String xh, List<SxxxModel> sxxxList) throws Exception {
		return dao.updateSxxx(xh,sxxxList);
	}
	
	/** 
	 * @描述:修改服务信息(南京高等职业技术学校)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-26 下午02:22:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param fwxxList
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateFwxx(String xh, List<FwxxModel> fwxxList) throws Exception{
		return dao.updateFwxx(xh, fwxxList);
	}
	
	/** 
	 * @描述:获取服务信息(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-26 下午05:04:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFwxxList(String xh){
		return dao.getFwxx(xh);
	}
	
	/** 
	 * @描述:获取实习信息(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-26 下午05:05:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getSxxxList(String xh){
		return dao.getSxxx(xh);
	}
	
	/**
	 * 查看成绩信息
	 * @param xh
	 * @param xkkh
	 * @return
	 */
	public HashMap<String, String> getCjxx(String xh,String xkkh){
		return dao.getCjxx(xh, xkkh);
	}
	
	/**
	 * 更新银行卡号
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean saveYhkh(XsxxglModel model) throws Exception{
		return dao.saveYhkh(model);
	}

	public List<HashMap<String, String>> getycsjList() {
		return dao.getycsjList();
	}

	public int[] updateXsxxb(List<HashMap<String, String>> rsList) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateXsxxb(rsList);
	}

	public List<HashMap<String, String>> getYcXgzdList(String sqid) {
		return dao.getYcXgzdList(sqid);
		}

	public String getxhforsqid(String sqid) {
		// TODO Auto-generated method stub
		return dao.getxhforsqid(sqid);
	}

	public boolean ycsjTs(List<HashMap<String, String>> sqidlist) throws Exception {
		List<HashMap<String, String>> xgzdList = null;
		boolean result = true;
		for (int i = 0; i < sqidlist.size(); i++) {
			String sqid= sqidlist.get(i).get("sqid");
			xgzdList = getYcXgzdList(sqid);
			HashMap<String, String> valueMap = new HashMap<String, String>();
			XsxxglDao dao = new XsxxglDao();
			String[] xsxxZds = dao.getColumnByTable("xsxxb");
			String zd =null;
			String zdz =null;
			//家庭成员信息
			String jtcyxxZdzJson = null;
			
			//学生社会经历信息
			String xlshjlZdzJson = null;
			for (HashMap<String, String> xgzdMap : xgzdList) {
				zd = xgzdMap.get("zd");
				zdz = xgzdMap.get("zdz");
				if (zd != null) {
					//家庭成员信息
					if (zd.equals(Constants.ZDYBD_JTCYXX)) {
						jtcyxxZdzJson = xgzdMap.get("zdz");
					}
					//学生社会经历信息
					if (zd.equals(Constants.ZDYBD_XLSHJLXX)) {
						xlshjlZdzJson = xgzdMap.get("zdz");
					}
				}
				for (int j = 0; j < xsxxZds.length; j++) {
					String xsxxZd = xsxxZds[j];
					if (zd != null && zd.toLowerCase().equals(xsxxZd.toLowerCase())) {
						valueMap.put(zd, zdz);
					}
				}
			}
		
			List jtcyxxList = null;
			List xlshjlcxxList = null;
			String xh = getxhforsqid(sqid);
			if (!valueMap.isEmpty()) {
				valueMap.put("xh", xh);
				result = dao.updateInfo(valueMap);
			}
			
			if (jtcyxxZdzJson != null) {
				jtcyxxZdzJson = "{data:" + jtcyxxZdzJson + "}";
				jtcyxxList = JsonUtil.jsonToList(jtcyxxZdzJson,
						JtcyxxModel.class);
				result = dao.updateJtcyxx(xh, jtcyxxList);// 家庭成员信息保存
			}

			if (xlshjlZdzJson != null) {
				xlshjlZdzJson = "{data:" + xlshjlZdzJson + "}";
				xlshjlcxxList = JsonUtil.jsonToList(xlshjlZdzJson,
						XlshjlModel.class);
				result = dao.updateXlshjlxx(xh, xlshjlcxxList);// 学历社会经历信息保存
			}
		}
		return result;
		
	
	}
	
	/**
	 * @description	：获取获奖信息，西安科技
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-29 上午11:18:08
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getHjqkListForXakj(String xh) throws Exception{
		return dao.getHjqkListForXakj(xh);
	}
	
	/**
	 * 
	 * @描述: 江苏省吴中中等专科学校更新实习状态(zd1)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-5 下午04:12:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateSxzt(String[] xhs,String zt) throws Exception{
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < xhs.length; i++) {
			params.add(new String[]{zt,xhs[i]});
		}
		return dao.updateSxzt(params);
	}

	public List<HashMap<String, String>> getxljkList(String xh) {
		// TODO Auto-generated method stub
		return dao.getxljkList(xh);
	}
	/**
	 * @throws Exception 
	 * @描述: 通过学号抽取学业成绩前39条
	 * @作者：姜舟[工号：1529]
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getCjoneList(String xh) throws Exception {
		return dao.getCjoneList(xh);
	}
	/**
	 * @描述: 通过学号抽取学业成绩40-78条
	 * @作者：姜舟[工号：1529]
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getCjtwoList(String xh) throws Exception {
		return dao.getCjtwoList(xh);
	}

	public boolean updateHwjl(String xh, List<HwjlModel> hwjlList) throws Exception {
		return dao.updateHwjl(xh,hwjlList);
	}

	public List<HashMap<String, String>> getHwjlList(String xh) {
		return dao.getHwjlList(xh);
	}
	public String getSfqnzyz(String xh){
		return dao.getSfqnzyz(xh);
	}
	
	/** 
	 * @描述:通过学号获取参与课题信息(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-4-17 下午02:02:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCyktxxList(String xh) {
		return dao.getCyktxxList(xh);
	}

	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018-4-17 下午02:13:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param cyktxxList
	 * @param id
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateCyktxxByjg(String xh, List<CyktxxModel> cyktxxList, String[] id) throws Exception {
		return dao.updateCyktxxByjg(xh, cyktxxList, id);
	}


	/**
	 * @描述:同班同学
	 * @作者：lgx [工号：1553]
	 * @日期：2019/2/21 10:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
    public List<HashMap<String,String>> getTbtxList(String xh,String bjdm) {
    	return dao.getTbtxList(xh,bjdm);
    }

    /**
     * @描述:同宿舍同学
     * @作者：lgx [工号：1553]
     * @日期：2019/2/21 10:41
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [xh]
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
	public List<HashMap<String,String>> getTsstxList(String xh) {
		return dao.getTsstxList(xh);
	}

	/**
	 * @描述:不及格科目成绩
	 * @作者：lgx [工号：1553]
	 * @日期：2019/2/21 13:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getBjgkmList(String xh) {
		return dao.getBjgkmList(xh);
	}
	/**
	 * @描述:其他科目成绩
	 * @作者：lgx [工号：1553]
	 * @日期：2019/2/21 13:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getQtkmList(String xh) {
		return dao.getQtkmList(xh);
	}

	/**
	 * @描述:知心谈话信息
	 * @作者：lgx [工号：1553]
	 * @日期：2019/2/21 14:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getZxthxxList(String xh) {
		return dao.getZxthxxList(xh);
	}

	/**
	 * @描述:家访信息
	 * @作者：lgx [工号：1553]
	 * @日期：2019/2/21 14:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getJfxxList(String xh) {
		return dao.getJfxxList(xh);
	}

	/**
	 * @描述:第二课堂-活动信息
	 * @作者：lgx [工号：1553]
	 * @日期：2019/2/21 14:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getHdxxList(String xh) {
		return dao.getHdxxList(xh);
	}

	/**
	 * @描述:四个一百信息
	 * @作者：lgx [工号：1553]
	 * @日期：2019/2/21 14:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getSgybxxList(String xh) {
		return dao.getSgybxxList(xh);
	}

	/**
	 * @描述:参加社团信息
	 * @作者：lgx [工号：1553]
	 * @日期：2019/2/21 14:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getCjstxxList(String xh) {
		return dao.getCjstxxList(xh);
	}
	/**
	 * @描述:毕业信息
	 * @作者：lgx [工号：1553]
	 * @日期：2019/2/21 14:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getByxxList(String xh) {
		return dao.getByxxList(xh);
	}

	/**
	 * @描述:表彰奖励
	 * @作者：lgx [工号：1553]
	 * @日期：2019/2/21 15:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [xh]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getBzjlList(String xh) {
		return dao.getBzjlList(xh);
	}


	/**
	 * @描述:删除的家庭成员信息保存至历史记录表
	 * @作者：lgx [工号：1553]
	 * @日期：2019/2/25 13:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [xh, jtcyxxList, user]
	 * @return: boolean
	 */
	public boolean jtcyDelLsjl(String xh,  List<JtcyxxModel> jtcyxxList, User user) throws Exception {
			return dao.jtcyDelLsjl(xh,jtcyxxList,user);
	}

	/**
	 * @描述:字段修改插入历史记录表
	 * @作者：lgx [工号：1553]
	 * @日期：2019/2/25 15:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [insLsjlList]
	 * @return: boolean
	 */
	public boolean insLsjl(List<HashMap<String, String>> list) throws SQLException {
		return dao.insLsjl(list);
	}

	/**
	 * @描述:根据地址代码获取地址名称
	 * @作者：lgx [工号：1553]
	 * @日期：2019/2/25 16:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [list]
	 * @return: java.lang.String
	 */
	public String getSzdmc(String  dz) {
		return dao.getSzdmc(dz);
	}

	/**
	 * @描述:字段历史记录信息
	 * @作者：lgx [工号：1553]
	 * @日期：2019/2/25 17:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [model]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getZdLsjList(XsxxglModel model) throws Exception {
		return dao.getZdLsjList(model);
	}

	/**
	 * @描述:家庭成员历史记录信息
	 * @作者：lgx [工号：1553]
	 * @日期：2019/2/25 17:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [model]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getJtcyLsjList(XsxxglModel model) throws Exception {
		return dao.getJtcyLsjList(model);
	}
}
