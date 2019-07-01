package com.zfsoft.xgxt.xsxx.xsgl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.xlzx.tsxsgl.TsxsService;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息
 * @类功能描述: 学籍异动
 * @作者： Qilm[工号:964]
 * @时间： 2013-11-28 上午09:40:48
 * @版本： V5.12.20
 */
public class XsxxService extends SuperServiceImpl<XsxxForm, XsxxDao> {

	private XsxxDao dao = new XsxxDao();

	public XsxxService() {
		super.setDao(dao);
	}

	public List<HashMap<String, String>> getPageListForGygl(XsxxForm model,
			User user, String searchTjByGyfdy) throws Exception {
		return dao.getPageListForGygl(model, user, searchTjByGyfdy);
	}
	
	public List<HashMap<String, String>> getPageListForGyglSsyd(XsxxForm model,
			User user, String searchTjByGyfdy) throws Exception {
		return dao.getPageListForGyglSsyd(model, user, searchTjByGyfdy);
	}
	
	/**
	 * 学生（困难生申请，不包括评议人员）
	 */
	public List<HashMap<String, String>> showStudentsKnsrdsqBjpy(XsxxForm model, User user)
	throws Exception {
		return dao.showStudentsKnsrdsqBjpy(model, user);
	}

	/**
	 * 查询学生基本信息
	 * 
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getXsjbxx(String xh) {

		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}

		return dao.getXsjbxx(xh);
	}

	/**
	 * 通过学号查询学生
	 * @param model
	 * @param xhs
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getPageList(XsxxForm model,String xhs[])
			throws Exception{
		return dao.getPageList(model,xhs);
	}


	/**
	 * 
	 * @描述:心理辅导录入用 温州大学
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-6-5 下午04:30:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListForXlfdlr(XsxxForm model,
			User user) throws Exception {
		return dao.getPageListForXlfdlr(model, user);
	}

	/**
	 * 
	 * @描述:心理辅导录入用 温州大学
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-6-5 下午04:30:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListForXxsbjggl(XsxxForm model,
			User user, String sblx) throws Exception {

		return dao.getPageListForXxsbjggl(model, user, sblx);
	}

	/**
	 * 
	 * @描述:家教老师库
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-6-5 下午04:30:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListForJjlsk(XsxxForm model,
			User user) throws Exception {
		return dao.getPageListForJjlsk(model, user);
	}
	
	/**
	 * 
	 * @描述:获取党团模块学生信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-23 下午05:15:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @deprecated
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getPageListForDtgl(XsxxForm model,
			User user) throws Exception {
		return dao.getPageListForDtgl(model, user);
	}

	/**
	 * 查询学生详细基本信息
	 * 
	 * @param xh
	 * @return
	 * @throws Exception 
	 */
	public HashMap<String, String> getXsjbxxMore(String xh){

		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}

		try {
			return dao.getXsjbxxMore(xh);
		} catch (Exception e) {
			logger.error("select to_char(to_date(a.csrq, 'yyyy-mm-dd')) from xsxxb a where xh ='"+xh+"'");
			throw new NullPointerException();
		}
	}

	/**
	 * 查询学生详细基本信息(在校生)
	 * 
	 * @param xh
	 * @return
	 * @throws Exception 
	 */
	public HashMap<String, String> getXsjbxxZjsMore(String xh){

		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}

		try {
			return dao.getXsjbxxZjsMore(xh);
		} catch (Exception e) {
			logger.error("select to_char(to_date(a.csrq, 'yyyy-mm-dd')) from xsxxb a where xh ='"+xh+"'");
			throw new NullPointerException();
		}
	}
	/**
	 * 按学号查询学生详细基本信息-带住宿信息
	 * 
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getXsjbxxMoreWithZSXX(String xh) {

		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}

		return dao.getXsjbxxMoreWithZSXX(xh);
	}

	/**
	 * 查询党团管理的学生详细基本信息
	 * 
	 * @param xh
	 * @deprecated
	 * @return
	 */
	public HashMap<String, String> getXsjbxxMoreForDtgl(String xh) {

		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}

		return dao.getXsjbxxMoreForDtgl(xh);
	}

	/**
	 * 
	 * @描述:查询照片流
	 * @作者：Penghui.Qu
	 * @日期：2013-5-20 上午10:41:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return InputStream 返回类型
	 * @throws
	 */
	public InputStream getPhotoStream(String xh) {

		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}

		InputStream is = dao.getPhotoStream(xh);

		return is;
	}
	/**
	 * 
	 * @描述:查询高考照片流
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-2 上午10:52:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * InputStream 返回类型 
	 * @throws
	 */
	public InputStream getGkPhotoStream(String xh) {

		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}

		InputStream is = dao.getGkPhotoStream(xh);

		return is;
	}

	/**
	 * 
	 * @描述:默认照片的Base64编码
	 * @作者：屈朋辉[工号：445]
	 * @日期：2013-9-29 下午04:02:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @return
	 * @throws IOException
	 *             String 返回类型
	 * @throws
	 */
	public String getDefaultPhotoBase64(HttpServletRequest request)
			throws IOException {

		String defaultPhotoPath = request.getSession().getServletContext()
				.getRealPath("/images/type_pic.gif");

		File file = new File(defaultPhotoPath);

		return FileUtil.getBASE64(file);
	}
	
	/**
	 * @描述：获取照片的Base64编码，若没有则获取默认照片
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年3月10日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param defaultPhotoPath
	 * @return
	 * @throws IOException
	 * String 返回类型
	 */
	public String getPhotoBase64(String xh,String defaultPhotoPath)throws Exception {
		InputStream is = getPhotoStream(xh);
		if (is==null){
			File file = new File(defaultPhotoPath);
			return FileUtil.getBASE64(file);
		}
		File photoFile = FileUtil.inputstreamToFile(is, "doc");
		String photo = FileUtil.getBASE64(photoFile);
		if (StringUtil.isNull(photo)){
			File file = new File(defaultPhotoPath);
			return FileUtil.getBASE64(file);
		}
		
		return photo;
	}

	/**
	 * @描述:判断学号在学生信息当中是否存在（包含在校生）
	 * @作者：cq [工号：785]
	 * @日期：2013-7-30 下午05:15:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public boolean getCheckStuExists(String xh) {

		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}

		return dao.getCheckStuExists(xh);

	}

	/**
	 * 
	 * @描述:根据学号和学年加载学生成绩
	 * @作者：cq [工号：785]
	 * @日期：2013-9-30 下午01:54:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getCjList(String xh, String xn,
			String xq) {

		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}

		return dao.getCjlist(xh, xn, xq);
	}
	
	/**
	 * @描述:根据学号和学年加载学生成绩（最高分、最低分、平均分、补考课数、学习成绩） 浙江旅游职业学院
	 * @作者：江水才[工号：1150]
	 * @日期：2014-12-13 下午01:22:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getXxcj(String xh, String xn, String xq) {
		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}
		return dao.getXxcj(xh, xn, xq);
	}

	/**
	 * @描述:心理健康-特殊學生信息
	 * @作者：Qilm[工号：964]
	 * @日期：2013-10-25 上午08:58:10
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListForTsxs(XsxxForm model,
			User user) throws Exception {

		List<HashMap<String, String>> lst = dao.getPageListForTsxs(model, user);
		TsxsService tsxsSv = new TsxsService();
		if (lst != null & lst.size() > 0) {
			for (HashMap<String, String> thjlInfo : lst) {

				// 获取困难类型名称
				if (thjlInfo.get("knlxdm") != null
						&& !"".equals(thjlInfo.get("knlxdm"))) {
					String knlxmc = tsxsSv.getKnlxMc(thjlInfo.get("knlxdm"));
					thjlInfo.put("knlxmc", knlxmc);
				}
			}
		}

		return lst;
	}

	/**
	 * 
	 * @描述: 取得选择的学生学号列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-4 下午02:40:52
	 * @param xzxsKey
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getSelectedStudents(String xzxsKey)
			throws Exception {
		return dao.getSelectedStudents(xzxsKey);
	}

	/**
	 * @描述:取得选择的学生学号数
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-4 下午02:41:14
	 * @param xzxsKey
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws Exception
	 */
	public int getSelectedCount(String xzxsKey) throws Exception {
		return dao.getSelectedCount(xzxsKey);
	}

	/**
	 * @描述: 选择学生列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-4 下午03:25:02
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getSelectStudentsList(XsxxForm model,
			User user) throws Exception {
		return dao.getSelectStudentsList(model, user);
	}

	/**
	 * @描述: 选择学生(全部)
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-4 下午03:26:34
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSelectAllStudentsList(
			XsxxForm model, User user) throws Exception {

		return dao.getSelectAllStudentsList(model, user);
	}

	/**
	 * @描述: 插入选择
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-4 下午04:38:48
	 * @param values
	 * @param xzxsKey
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean runInsertSelect(String values, String xzxsKey) {
		boolean result = false;
		try {
			result = dao.insertSelect(values, xzxsKey);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return result;
	}

	/**
	 * @描述: 批量插入选择
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-5 上午10:29:33
	 * @param model
	 * @param user
	 * @param xzxsKey
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean runInsertSelect(XsxxForm model, User user, String xzxsKey) {
		boolean result = false;
		try {
			result = dao.insertSelect(model, user, xzxsKey);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return result;
	}

	/**
	 * @描述: 批量删除选择
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-5 上午11:12:44
	 * @param model
	 * @param user
	 * @param xzxsKey
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean runDelSelect(XsxxForm model, User user, String xzxsKey) {
		boolean result = false;
		try {
			result = dao.delSelect(model, user, xzxsKey);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return result;
	}

	/**
	 * @描述: 批量删除选择
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-5 上午11:14:36
	 * @param values
	 * @param xzxsKey
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean runDelSelect(String values, String xzxsKey) {
		boolean result = false;
		try {
			result = dao.delSelect(values, xzxsKey);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return result;
	}

	/**
	 * @描述: 删除选择(所有）
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-5 上午11:14:36
	 * @param xzxsKey
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean runDelSelectAll(String xzxsKey) {
		boolean result = false;
		try {
			result = dao.delSelectAll(xzxsKey);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return result;
	}

	/**
	 * @描述: 高级查询模式(全部学生)
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-13 上午11:45:56
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListAll(XsxxForm model,
			User user) throws Exception {
		return dao.getPageListAll(model, user);
	}

	/**
	 * 
	 * @描述:根据学号获取班主任信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-6-16 下午07:49:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getBzrxxByXh(String xh) {
		return dao.getBzrxxByXh(xh);
	}

	/**
	 * 
	 * @描述:根据班级代码获取班主任信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-6-16 下午07:49:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getBzrxxByBjdm(String bjdm) {
		return dao.getBzrxxByBjdm(bjdm);
	}
 
	/**
	 * @描述: 浙江警察根据学号,学年加载学生平均成绩
	 * @作者：ChenQ [工号：856]
	 * @日期：2015-6-08 下午02:02:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public String  getZjjcPjcj(String xh, String xn,
			String xq) {
		return dao.getZjjcPjcj(xh, xn, xq);
	}
	
	
	/**
	 * @描述: 浙江警察根据学号,学年加载学生考察或其他最低成绩
	 * @作者：ChenQ [工号：856]
	 * @日期：2015-6-08 下午02:02:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public String  getZjjcZdcj(String xh, String xn,
			String xq) {
		return dao.getZjjcZdcj(xh, xn, xq);
	}
	
	/**
	 * 
	 * @描述:华中农业，根据学号获取平均成绩及排名
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-6-11 上午10:26:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型 
	 * @throws
	 */
	public HashMap<String,String>  getHznyPjcjWithPm(String xh, String xn,
			String xq) {
		return dao.getHznyPjcjWithPm(xh, xn, xq);
	}
	
	/*
	 * 东北石油学生证打印省市县截取
	 */
	public HashMap<String, String> getXsjtSsx(String xh) {
		return dao.getXsjtSsx(xh);
	}
	
	/*
	 * 东北石油学生证打印最新火车区间截取
	 */
	public HashMap<String, String> getXsHcxx(String xh) {
		return dao.getXsHcxx(xh);
	}


	/**
	 * @throws Exception  
	 * @描述:得到已住宿学生
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-29 下午04:15:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> showStudentsForZzdgl(XsxxForm model,
			User user) throws Exception {
		return dao.showStudentsForZzdgl(model,user);
	}
	
	/** 
	 * @描述:得到已无息借款学生
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-2 上午11:05:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> showStudentsForXnwxjkhk(XsxxForm model,
			User user) throws Exception {
		return dao.showStudentsForXnwxjkhk(model,user);
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2015-11-25 上午10:25:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> transformYear_Month(String csrq){
		return dao.transformYear_Month(csrq);
	}
	
	/**
	 * 
	 * @描述:获取家庭成员信息(备用)
	 * @作者：yxy[工号：1206]
	 * @日期：2015-11-25 下午01:36:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJtcyxxList(String xh){
		return dao.getJtcyxxList(xh);
	}
	
	/**
	 * 
	 * @描述:获取父亲信息
	 * @作者：yxy[工号：1206]
	 * @日期：2015-11-25 下午01:39:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getFather(String xh){
		return dao.getFather(xh);
	}
	
	/**
	 * 
	 * @描述:获取母亲信息
	 * @作者：yxy[工号：1206]
	 * @日期：2015-11-25 下午01:39:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getMother(String xh){
		return dao.getMother(xh);
	}
	
	/**
	 * 
	 * @描述: 永平自立贷学金已贷人员过滤
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-5-3 下午04:52:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsForHkxx(XsxxForm model,
			User user) throws Exception {
		return dao.showStudentsForHkxx(model,user);
	}
	
	/**
	 * @描述: 国家助学贷款（校园地贷款）已贷款人员过滤
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2016-8-22 下午02:15:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsForXydHkwh(XsxxForm model,
			User user) throws Exception {
		return dao.showStudentsForXydHkwh(model,user);
	}
	
	/**
	 * @描述: 生源地已贷人员过滤
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2016-8-22 下午02:15:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsForSydHkwh(XsxxForm model,
			User user) throws Exception {
		return dao.showStudentsForSydHkwh(model,user);
	}
	
	/**
	 * 
	 * @描述: 毕业还款人员信息过滤
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-5-3 下午04:52:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsForByHkxx(XsxxForm model,
			User user) throws Exception {
		return dao.showStudentsForByHkxx(model,user);
	}
	
	/** 
	 * @描述:特殊学生批量增加页面(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-26 上午09:29:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param xn
	 * @param xq
	 * @param lxdm
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> showStudentsForTsxsByTy(XsxxForm model,String xn,String xq,String lxdm,
			User user) throws Exception {
		return dao.showStudentsForTsxsByTy(model,xn,xq,lxdm,user);
	}
	
	/**
	 * @描述: 浙江大学新酬金发放查询所有学生
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-1-20 下午05:39:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsCjffAdd(XsxxForm model) throws Exception {
		return dao.showStudentsCjffAdd(model);
	}
	
	/**
	 * @描述: 党组织关系转出学生页面查询
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-9 上午08:55:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsFordzzgxzc(XsxxForm model, User user)
	throws Exception {
		return dao.showStudentsFordzzgxzc(model, user);
	}
	
	/** 
	 * @描述:西安科技大学学生岗位申请过滤(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-20 上午09:58:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> showStudentsForXiAnKjGwSq(XsxxForm model, User user) throws Exception{
		return dao.showStudentsForXiAnKjGwSq(model, user);
	}
	
	/** 
	 * @描述:获取辅导员信息（去一条数据）(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-17 下午01:53:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getFdyxxByXh(String xh) {
		return dao.getFdyxxByXh(xh);
	}
	
	/**
	 * @throws Exception  
	 * @描述:获取全部学生列表(寝室换人专用，重庆工商大学)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-23 上午10:54:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> showStudentsForQshr(XsxxForm model,User user) throws Exception{
		return dao.getStudentsForQshr(model,user);
	}
	
	/**
	 * @description	： 选择学生列表（团干部）
	 * @author 		： lj（1282）
	 * @date 		：2018-5-16 上午10:45:43
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> showStudentsForTgb(XsxxForm model, User user) throws Exception {
		return dao.showStudentsForTgb(model,user);
	}
}
