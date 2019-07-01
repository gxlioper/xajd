package xsgzgl.wjcf.xscfcx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.CommSetting;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.comm.BasicService;
import xsgzgl.wjcf.cfssgl.WjcfCfssglForm;
import xsgzgl.wjcf.cfssgl.WjcfCfssglServices;


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
 * Author: ltt
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-17 下午12:36:30
 * </p>
 */
public class WjcfXscfService  extends BasicService {

	/**
	 * 学生查询处分信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> xscfCx(WjcfXscfActionForm model)
			throws Exception {
		WjcfXscfDao dao = new WjcfXscfDao();
		return dao.xscfCx(model);
	}
	
	/**
	 * 设置结果查询表头
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr() {
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "xn", "xq", "cflbmc", "cfyymc", "fwsj",
				"fwwh", "fwjg", "cz" };
		String[] cn = new String[] { "", "学年", "学期", "处分类别", "处分原因", "发文时间","发文文号", "发文结果", "操作" };
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 初始化结果查询信息
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void initCfcxManage(RequestForm rForm, HttpServletRequest request) {
		DAO dao = DAO.getInstance();
		// 功能模块
		String gnmk = "wjcf";
		// 访问路径
		String path = "wjcfsjCx.do";
		// ========================输出字段 begin=========================
		// 字段名
		String[] en = new String[] { "cfid", "xn", "xq", "cflbmc", "cfyymc", "fwsj",
				"fwwh", "fwjg", "cz"  };
		// 中文名
		String[] cn = new String[] { "cfid", "xn", "xq", "cflbmc", "cfyymc", "fwsj",
				"fwwh", "fwjg", "cz" };
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
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"5%\" >");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'");
				html.append(" /> ");
				html.append("</td>");
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length-6; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length-5) + "%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"15%\" >");
				if ("处分成立".equalsIgnoreCase(rs[7])) {
					if (StringUtils.isNotNull(rs[9])) {
						if ("wsh".equalsIgnoreCase(rs[9])) {//未审核前可取消
							html.append("<a href='#' onclick='showView(this);return false;' ><font color=\"blue\">查看</font></a>&nbsp;&nbsp;&nbsp;<a href='#' onclick='qxss(this);return false;' ><font color=\"blue\">取消申诉</font></a>");
						} else {//已审核不能操作
							html.append("<a href='#' onclick='showView(this);return false;' ><font color=\"blue\">查看</font></a>&nbsp;&nbsp;&nbsp;<a href='#' onclick='return false;' disabled='true'><font color=\"blue\">取消申请</font></a>");
						}
					} else {
						if (StringUtils.isNotNull(rs[11]) && rs[11].contains("xs")) {//开关控制可申诉
							String[] array = StringUtils.isNotNull(rs[13]) ? rs[13].split("!!") : new String[]{};
							if (array != null && array.length == 2) {
								double time = !StringUtils.isNull(array[0]) ? Double.parseDouble(array[0]) : 0;
								double c = !StringUtils.isNull(array[1]) ? Double.parseDouble(array[1]) : 0;
								if (time <= c) {//在时间天内可申诉
									html.append("<a href='#' onclick='showView(this);return false;' ><font color=\"blue\">查看</font></a>&nbsp;&nbsp;&nbsp;<a href='#' onclick='cfsscl(this);return false;' ><font color=\"blue\">申诉</font></a>");
								} else {
									//不在时间天内不可申诉
									html.append("<a href='#' onclick='showView(this);return false;' ><font color=\"blue\">查看</font></a>&nbsp;&nbsp;&nbsp;<a href='#' onclick='return false;' disabled='true'><font color=\"blue\">申诉</font></a>");
								}
							} else {//未知情况，可以申诉
								html.append("<a href='#' onclick='showView(this);return false;' ><font color=\"blue\">查看</font></a>&nbsp;&nbsp;&nbsp;<a href='#' onclick='cfsscl(this);return false;' ><font color=\"blue\">申诉</font></a>");
							}
						} else {//开关控制不可申诉
							html.append("<a href='#' onclick='showView(this);return false;' ><font color=\"blue\">查看</font></a>&nbsp;&nbsp;&nbsp;<a href='#' onclick='return false;' disabled='true'>申诉</a>");
						}
					}
					
				} else if ("维持原处分".equalsIgnoreCase(rs[7])
						|| "更改处分".equalsIgnoreCase(rs[7])
						|| "撤消处分".equalsIgnoreCase(rs[7])
						|| "撤销处分".equalsIgnoreCase(rs[7])) {
					if (StringUtils.isNotNull(rs[10])) {
						if ("wsh".equalsIgnoreCase(rs[10])) {//未审核前可取消
							html.append("<a href='#' onclick='showView(this);return false;' ><font color=\"blue\">查看</font></a>&nbsp;&nbsp;&nbsp;<a href='#' onclick='qxjc(this);return false;' ><font color=\"blue\">取消解除申请</font> </a>");
						} else {//已审核不能操作
							html.append("<a href='#' onclick='showView(this);return false;' ><font color=\"blue\">查看</font></a>&nbsp;&nbsp;&nbsp;<a href='#' onclick='return false;' disabled='true'><font color=\"blue\">取消解除申请</font></a>");
						}
					} else {
						if (StringUtils.isNotNull(rs[12]) && rs[12].contains("xs")) {//开关控制可申请
							html.append("<a href='#' onclick='showView(this);return false;' ><font color=\"blue\">查看</font></a>&nbsp;&nbsp;&nbsp;<a href='#' onclick='cfjccl(this);return false;' ><font color=\"blue\">申请解除</font></a>");
						} else {//开关控制不可申请
							html.append("<a href='#' onclick='showView(this);return false;' ><font color=\"blue\">查看</font></a>");
						}
					}
				} else {
					html.append("<a href='#' onclick='showView(this);return false;' ><font color=\"blue\">查看</font></a>");
				}
				html.append("</td>");
				html.append("</tr>");
			}
		}
		return html.toString();
	}

	
	/**
	 * 处分申诉保存
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean xsssSave(WjcfXscfActionForm model) throws Exception{
		boolean result = false;
		WjcfXscfDao dao = new WjcfXscfDao();
		String fjmc = StringUtils.isNotNull(model.getFjmc()) ? model.getFjmc()
				.substring(model.getFjmc().lastIndexOf("\\")+1,
						model.getFjmc().length()) : "";
		model.setFjmc(fjmc);
		result = dao.xsssSave(model);
		if (result) {
			WjcfCfssglServices service = new WjcfCfssglServices();
			WjcfCfssglForm form = new WjcfCfssglForm(); 
			form.setCfid(model.getCfid());
			result = service.cfssshZj(form);
		}
		return result;
	}
	
	/**
	 * 保存申请解除信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cfjcSave(WjcfXscfActionForm model) throws Exception {
		boolean result = false;
		WjcfXscfDao dao = new WjcfXscfDao();
		result = dao.cfjcSave(model);
		if (result) {
			List<HashMap<String, String>> shlcList=dao.getJcshpList();
			List<WjcfCfssglForm> modelList=new ArrayList<WjcfCfssglForm>();
			WjcfCfssglForm cf=null;
			for (int i=0;i< shlcList.size();i++) {
				cf=new WjcfCfssglForm();
				cf.setCfid(model.getCfid());
				cf.setXtgwid(shlcList.get(i).get("spgw"));
				cf.setShzt("wsh");
				modelList.add(cf);
			}
			result = dao.cfssshZj(modelList);
		}
		return result;
	}
	
	/**
	 * 取消申诉
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean xsssCx(WjcfXscfActionForm model) throws Exception{
		boolean result = false;
		WjcfXscfDao dao = new WjcfXscfDao();
		result = dao.xsssCx(model);
		if (result) {
			result = dao.cfsssplSc(model.getCfid());
		}
		return result; 
	}
	
	/**
	 * 取消解除申请
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean jcsqCx(WjcfXscfActionForm model) throws Exception{
		WjcfXscfDao dao = new WjcfXscfDao();
		boolean result = false;
		result = dao.jcsqCx(model);
		if (result) {
			result = dao.cfjcsplSc(model.getCfid());
		}
		return result;
	}
}
