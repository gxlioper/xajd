package xsgzgl.gygl.wsjc.jcrcgl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;
import xsgzgl.gygl.comm.GyglNewInit;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: qlj
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-6-14 下午03:45:48
 * </p>
 */
public class GyglJcrcglService extends BasicService {

	/**
	 * 设置结果查询表头
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr() {
		DAO dao = DAO.getInstance();
		String[] en = null;
		String[] cn = null;
		
		if (GyglNewInit.WSJC_XJQS){
			if("18180".equals(Base.xxdm)){
				en = new String[] { "", "xn", "xq","jcyf", "mc", "lxmc","kssj", "jssj", "bz", "tjztInfo"};
				cn = new String[] { "", "学年", "学期","检查月份", "名称", "类型","开始时间", "结束时间", "备注","提交状态" };
			}else{
				en = new String[] { "", "xn", "xq", "mc", "lxmc","kssj", "jssj", "bz", "tjztInfo"};
				cn = new String[] { "", "学年", "学期", "名称", "类型","开始时间", "结束时间", "备注","提交状态" };
			}
		} else {
			en = new String[] { "", "xn", "xq", "mc", "kssj", "jssj", "bz", "tjztInfo"};
			cn = new String[] { "", "学年", "学期", "名称", "开始时间", "结束时间", "备注","提交状态" };
		}
		if("12688".equals(Base.xxdm)){
			en = new String[] { "", "xn", "xq", "mc", "lxmc","kssj", "jssj", "bz", "tjztInfo","pfjbmc"};
			cn = new String[] { "", "学年", "学期", "名称", "类型","开始时间", "结束时间", "备注","提交状态" ,"级别"};
		}
		return dao.arrayToList(en, cn);
	}

	/**
	 * 初始化结果查询信息
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initJcrcglManage(RequestForm rForm, HttpServletRequest request) {
		DAO dao = DAO.getInstance();
		// 功能模块
		String gnmk = "gygl";
		// 访问路径
		String path = "gzdx_gygl_wsjc_jcrcgl.do";
		// ========================输出字段 begin=========================
		// 字段名
		String[] en = new String[] { "xn", "xq", "mc", "kssj", "jssj", "bz", "tjztInfo" };
		// 中文名
		String[] cn = new String[] { "xn", "xq", "mc", "kssj", "jssj", "bz", "tjztInfo" };
		// 表头
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);
		// ========================输出字段 end=========================
		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();
		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);
		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);
		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);
		// ===============通用设置 end ============
		rForm.setQtzdz(new String[] {});// 加载其他数据
		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(en);
		rForm.setTopTr(topTr);
		rForm.setSearch("true");
		rForm.setCommSetting(commSetting);
	}

	/**
	 * 获取卫生检查日程管理信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJcrcglList(GyglJcrcglForm model) throws Exception {
		GyglJcrcglDAO dao = new GyglJcrcglDAO();
		return dao.getJcrcglList(model);
	}

	/**
	 * 日程名称不可重复的检查
	 * 
	 * @param model
	 * @param Mc
	 * @return
	 * @throws IllegalArgumentException
	 *             , SecurityException, IllegalAccessException,
	 *             InvocationTargetException, NoSuchMethodException
	 */
	public boolean findInfo(BasicModel model, String Mc)
			throws IllegalArgumentException, SecurityException,IllegalAccessException, InvocationTargetException,NoSuchMethodException {
		GyglJcrcglDAO dao = new GyglJcrcglDAO();
		return dao.findInfo(model, Mc);
	}

	/**
	 * 起止时间不可重叠的检查
	 * 
	 * @param model
	 * @param Mc
	 * @return
	 * @throws IllegalArgumentException, SecurityException, IllegalAccessException,InvocationTargetException, NoSuchMethodException
	 */
	public String findQzsj(BasicModel model, String Kssj, String Jssj,String Xn, String Xq, String jclx) 
			throws IllegalArgumentException,SecurityException, IllegalAccessException,InvocationTargetException, NoSuchMethodException {
		GyglJcrcglDAO dao = new GyglJcrcglDAO();
		return dao.findQzsj(model, Kssj, Jssj, Xn, Xq, jclx);
	}

	/**
	 * 构建结果集
	 * 
	 * @author qlj
	 */
	public String createSearchHTML(SearchRsModel rsModel,List<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				int len = rs.length;
				if("12688".equals(Base.xxdm))
					len= rs.length-1;
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue' sfwh='"+replaceHtml(rs[len-2])+"' ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'");
//				if (rs[8] != "") {
//					html.append(" disabled='disabled'");
//				}
				html.append(" /> ");
				html.append("<input type='hidden' name='xh'  ");
				html.append("  id='xh_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[1]) + "'/> ");
				html.append("</td>");
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length-2; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 3) + "%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					if (j > len-4) {
						html.append(replaceHtml(rs[j+2]));
					}else{
						html.append(replaceHtml(rs[j]));
					}
					
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}

	/**
	 * 档案类型初始化
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initSave(BasicModel basicModel, HttpServletRequest request) {
		// 主键字段
		String[] save = { "xn", "xq","jcyf","mc", "kssj", "jssj", "bz","jclx" };
		// --------------表名------------
		basicModel.setTableName("xg_gygl_new_wsjc_jcrcb");
		// --------------需要保存的值--------------------
		HashMap<String, String> valueMap = getValueMap(request, save);
		basicModel.setValueMap(valueMap);
	}

	/**
	 * 档案类型初始化
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initModi(BasicModel basicModel, HttpServletRequest request) {
		String[] save = { "xn", "xq","jcyf", "mc", "kssj", "jssj", "bz","jclx" };
		String[] pk = { "guid" };
		basicModel.setPk(pk);
		basicModel.setTableName("xg_gygl_new_wsjc_jcrcb");
		HashMap<String, String> valueMap = getValueMap(request, save);
		valueMap.put("guid", request.getParameter("pkValue"));
		basicModel.setValueMap(valueMap);
	}

	/**
	 * 查询单条卫生检查日程信息
	 * 
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getJcrcglMap(GyglJcrcglForm model) {
		GyglJcrcglDAO dao = new GyglJcrcglDAO();
		return dao.getJcrcglMap(model);
	}
	
	/**
	 *  检查日程管理 自定义设置
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJcrcglExportList(GyglJcrcglForm model,User user) throws Exception {
		GyglJcrcglDAO dao = new GyglJcrcglDAO();
		return dao.getJcrcglExportList(model,user);
	}

	/**
	 * @描述:修改检查日程管理为提交状态
	 * @作者：cq [工号：785]
	 * @日期：2013-8-29 上午10:09:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean tjJcrcgl(String pkStr) {
		GyglJcrcglDAO dao = new GyglJcrcglDAO();
		if("10351".equals(Base.xxdm)||"10344".equals(Base.xxdm)||"12424".equals(Base.xxdm)){
			boolean flag = dao.tjJcrcgl(pkStr);
			if(flag){
				List<HashMap<String,String>> xswsf = dao.getXsfs(pkStr);
				flag=dao.bcXsWsf(xswsf);
			}
		   return flag;
		}
		else{
		return dao.tjJcrcgl(pkStr);
		}
	}
	
	/**
	 * 
	 * @描述: 提交同步更新到XG_QSFMX表（上海戏剧个性化）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-1-5 下午04:11:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pkStr
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSubmit(String pkStr) {
		GyglJcrcglDAO dao = new GyglJcrcglDAO();
		return dao.saveSubmit(pkStr);
		
	}
	
	/**
	 * 
	 * @描述: 取消提交同步删除对应数据XG_QSFMX表（上海戏剧个性化）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-1-5 下午04:11:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pkStr
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delCancel(String pkStr) {
		GyglJcrcglDAO dao = new GyglJcrcglDAO();
		return dao.delCancel(pkStr);
		
	}
	
	/**
	 * 
	 * @描述:取消提交检查日程管理
	 * @作者：cq [工号：785]
	 * @日期：2013-8-29 下午02:38:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pkStr
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean qxtjJcrcgl(String pkStr){
		GyglJcrcglDAO dao = new GyglJcrcglDAO();
		if("10351".equals(Base.xxdm)||"10344".equals(Base.xxdm)){
			boolean flag = dao.qxtjJcrcgl(pkStr);
			if(flag){
				flag=dao.scXsWsf(pkStr);
			}
		   return flag;
		}
		else{
		return dao.qxtjJcrcgl(pkStr);
		}
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-10 上午10:17:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateByqsForZjcm(String pkValues) throws Exception{
		String[] pkValue = pkValues.split("!!array!!");
		return new GyglJcrcglDAO().updateByqsForZjcm(pkValue);
	}
	
}