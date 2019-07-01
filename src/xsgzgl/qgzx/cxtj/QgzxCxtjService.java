package xsgzgl.qgzx.cxtj;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.Arrays2;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.comm.BasicService;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.glygl.QgzxGlyglService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import common.Globals;

/**
 * 勤工助学-查询统计-酬金统计导出
 * 
 * @author yeyipin
 * @since 2012.9.19
 */
public class QgzxCxtjService extends BasicService {

	QgzxCxtjDAO qgzxCxtjDAO = new QgzxCxtjDAO();

	/**
	 * 获得表头
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type) {
		DAO dao = DAO.getInstance();
		String qgzq = QgCommUtilf.getQgzq();
	
	   String[] en = new String[] { "", "r", "xn", "bmmc", "gwmc", "gwxzmc",
			"xqrs", "zgrs", "tgrs" };
	   String[] cn = new String[] { "", "行号", "学年", "用人部门", "岗位名称", "岗位性质",
			"需求人数", "在岗人数", "退岗人数" };

		if("xq".equals(qgzq)){
			   en = new String[] { "", "r", "xn","xqmc", "bmmc", "gwmc", "gwxzmc",
						"xqrs", "zgrs", "tgrs" };
			   cn = new String[] { "", "行号", "学年","学期", "用人部门", "岗位名称", "岗位性质",
						"需求人数", "在岗人数", "退岗人数" };
		}
		
		if ("xsgw".equalsIgnoreCase(type)) {
			en = new String[] { "", "r", "xn", "yrbmmc","sgsj", "gwmc", "gwxzmc",
					"xh", "xm", "nj", "bjmc", "zgztmc","zymc","xymc","yhkh","zybjmc" };
			cn = new String[] { "", "行号", "学年", "用人部门","上岗时间", "岗位名称", "岗位性质", "学号",
					"姓名", "年级", "行政班级", "在岗状态", "专业名称", "学院名称", "银行卡号","专业班级" };
			if("xq".equals(qgzq)){
				en = new String[] { "", "r", "xn","xqmc", "yrbmmc","sgsj", "gwmc", "gwxzmc",
						"xh", "xm", "nj", "bjmc", "zgztmc","zymc","xymc","yhkh","zybjmc" };
				cn = new String[] { "", "行号", "学年","学期", "用人部门","上岗时间", "岗位名称", "岗位性质", "学号",
						"姓名", "年级", "行政班级", "在岗状态", "专业名称", "学院名称", "银行卡号","专业班级" };
			}
		} else if ("jfhb".equalsIgnoreCase(type)) {
			en = new String[] { "", "r", "nd", "bmmc", "hbzje", "yffje", "syje" };
			cn = new String[] { "", "行号", "年度", "用人部门", "划拨总金额<元>", "已发放金额<元>",
					"剩余金额<元>" };
			if("1".equals(new QgzxCsszService().getJfhbfs())){
				cn = new String[] { "", "行号", "年月", "用人部门", "划拨总金额<元>", "已发放金额<元>", "剩余金额<元>" };
			}
		} else if ("cjff".equalsIgnoreCase(type)) {
			if("13855".equals(Base.xxdm)){
			en = new String[] { "", "xh", "xm", "bjmc", "yrdwmc", "gwmc",
					"gwxzmc", "ygmc", "yhkh", "bz", "ny", "je", "khdj" };
			cn = new String[] { "", "学号", "姓名", "班级名称", "用人部门", "岗位名称", "岗位性质",
					"银行名称", "银行卡号", "备注", "发放年月", "金额<元>", "考核等级" };
			}
			else{
				en = new String[] { "", "xh", "xm", "bjmc", "yrdwmc", "gwmc",
						"gwxzmc", "ygmc", "yhkh", "bz", "ny", "je","zybjmc"};
				cn = new String[] { "", "学号", "姓名", "行政班级", "用人部门", "岗位名称", "岗位性质",
						"银行名称", "银行卡号", "备注", "发放年月", "金额<元>","专业班级"};
			}
		}
		return dao.arrayToList(en, cn);
	}

	/**
	 * 岗位信息查询
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> gwxxCx(QgzxCxtjForm model,User user) throws Exception {
		return qgzxCxtjDAO.gwxxCx(model,user);
	}
	public List<HashMap<String, String>> getExportList(QgzxCxtjForm t, User user)
			throws Exception {
		return qgzxCxtjDAO.getExportList(t,user);
	}
	public List<HashMap<String,String>> getGwRyList(String type,String gwdm){
		return qgzxCxtjDAO.getGwryList(type,gwdm);
	}
	/**
	 * 岗位信息查看
	 * 
	 * @param myForm
	 * @param request
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, String> gwxxCk(QgzxCxtjForm model)
			throws SQLException {
		// 岗位信息
		HashMap<String, String> rs = qgzxCxtjDAO.gwxxCk(model);
		// 获得在岗人员并生成html
		model.setZgzt("zg");
		List<HashMap<String, String>> zgryxxList = getGwxsxxList(model);
		rs.put("zgryHtml", createRyxxHtml(zgryxxList, model));
		// 获得退岗人员并生成html
		model.setZgzt("tg");
		List<HashMap<String, String>> tgryxxList = getGwxsxxList(model);
		rs.put("tgryHtml", createRyxxHtml(tgryxxList, model));
		return rs;
	}

	/**
	 * 根据岗位和在岗状态获得学生信息列表
	 * 
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	private List<HashMap<String, String>> getGwxsxxList(QgzxCxtjForm model)
			throws SQLException {
		String[] xhs = qgzxCxtjDAO.getRyxhList(model);
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> ryxxList = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < xhs.length; i++) {
			model.setXh(xhs[i]);
			HashMap<String, String> rs = qgzxCxtjDAO.getGwxsxx(model);
			if (rs != null && rs.size() != 0) {
				rs.put("r", String.valueOf(i + 1));
				// 判断是否困难生
				rs.put("sfkns", dao.isKns(xhs[i]) ? "是" : "否");
				ryxxList.add(rs);
			}

		}
		return ryxxList;
	}

	/**
	 * 创建人员信息HTML
	 * 
	 * @param zgryxxList
	 * @param model
	 * @return
	 */
	private String createRyxxHtml(List<HashMap<String, String>> rsArrList,
			QgzxCxtjForm model) {
		StringBuilder html = new StringBuilder();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				HashMap<String, String> rs = rsArrList.get(i);
				html.append("<tr><td>" + rs.get("r") + "</td>");
				html.append("<td>" + rs.get("xh") + "</td>");
				html.append("<td>" + rs.get("xm") + "</td>");
				html.append("<td>" + rs.get("bjmc") + "</td>");
				html.append("<td>" + rs.get("sfkns") + "</td>");
				html.append("<td>" + rs.get("sgsj") + "</td>");
				if ("tg".equalsIgnoreCase(model.getZgzt())) {
					html.append("<td>" + rs.get("tgsj") + "</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}

	/**
	 * 学生岗位查询
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> xsgwCx(QgzxCxtjForm model,User user) throws Exception {
		return qgzxCxtjDAO.xsgwCx(model,user);
	}
	public List<HashMap<String,String>> xsgzjlCx(QgzxCxtjForm model,User user) throws Exception {
		return qgzxCxtjDAO.xsgzjlCx(model,user);
	}
	public List<HashMap<String,String>> xsgzjlMxCx(QgzxCxtjForm model,User user) throws Exception {
		return qgzxCxtjDAO.xsgzjlMxCx(model,user);
	}
	public List<HashMap<String,String>> xsgzCx(QgzxCxtjForm model,User user) throws Exception {
		return qgzxCxtjDAO.xsgzCx(model,user);
	}
	public List<HashMap<String,String>> xsgzffCx(String xh) throws Exception {
		return qgzxCxtjDAO.xsgzffCx(xh);
	}
	public List<HashMap<String,String>> dwgzCx(QgzxCxtjForm model,User user) throws Exception {
		return qgzxCxtjDAO.dwgzCx(model,user);
	}
	/**
	 * 学生岗位查看
	 * 
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, String> xsgwCk(QgzxCxtjForm model)
			throws SQLException {
		// 岗位信息
		DAO dao = DAO.getInstance();
		HashMap<String, String> rs = qgzxCxtjDAO.xsgwCk(model);
		rs.put("sfkns", dao.isKns(model.getPkValue().split("!!@@!!")[1]) ? "是"
				: "否");
		return rs;
	}
	
	/**
	 * 学生岗位信息
	 */
	public HashMap<String, String> getXsgwxx(QgzxCxtjForm model) {
		return qgzxCxtjDAO.getXsgwxx(model);
	}
	
	/**
	 * 获取学生岗位信息列表
	 */
	public List<HashMap<String, String>> getXsgwxxList(String xh) {
		return qgzxCxtjDAO.getXsgwxxList(xh);
	}

	/**
	 * 经费划拨查询
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> jfhbCx(QgzxCxtjForm model) throws Exception {
		return qgzxCxtjDAO.jfhbCx(model);
	}

	/**
	 * 经费划拨查看
	 * 
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, String> jfhbCk(QgzxCxtjForm model)
			throws SQLException {
		HashMap<String, String> rs = qgzxCxtjDAO.jfhbCk(model);
		List<HashMap<String, String>> jfhbxmList = qgzxCxtjDAO
				.jfhbxmList(model);
		String jfhbList = createjfhbHtml(jfhbxmList, model);
		rs.put("jfhbList", jfhbList);
		return rs;
	}

	/**
	 * 创建经费划拨HTML
	 * 
	 * @param myForm
	 * @param list
	 * @return
	 */
	private String createjfhbHtml(List<HashMap<String, String>> list,
			QgzxCxtjForm model) {
		StringBuilder html = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			html.append("<tr><td width='5%'>");
			html.append(i + 1);
			html.append("</td><td width='15%'>");
			html.append(list.get(i).get("hbsj"));
			html.append("</td><td width='15%'>");
			html.append(list.get(i).get("hbje"));
			html.append("</td><td width='65%'>");
			if (list.get(i).get("bz") != null) {
				html.append(list.get(i).get("bz"));
			}
			html.append("</td></tr>");
		}

		return html.toString();
	}

	/**
	 * 酬金发放查询
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> cjffCx(QgzxCxtjForm model) throws Exception {
		return qgzxCxtjDAO.cjffCx(model);
	}

	/**
	 * 酬金发放查看
	 * 
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, String> cjffCk(QgzxCxtjForm model)
			throws SQLException {
		return qgzxCxtjDAO.cjffCk(model);
	}

	/**
	 * 创建页面
	 * 
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		html.append("<input type = 'hidden' name ='rsSize' id = 'rsSize' value='"+rsArrList.size()+"'/>");
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html
						.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				html
						.append("<td align=\"left\" nowrap=\"nowrap\" width=\"10px\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");

				// 学生岗位查询和酬金发放查询冲突
				if (13 == rs.length) {
					html
							.append("<a href='javascript:void(0);' class='name' onclick='zxsxxView(&quot;"
									+ replaceHtml(rs[1])
									+ "&quot;);return false;'>"
									+ replaceHtml(rs[1]) + "</a>");
					html.append("</td>");
				} else {
					html.append(replaceHtml(rs[1]));
				}

				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 2; j < rs.length; j++) {
					if(j == 9){
						html.append("<td id='bz"+i+"' title='"+replaceHtml(rs[j])+"' align=\"left\" nowrap=\"nowrap\" width=\""
								+ 100 / (rs.length - 2) + "%\" ");
					}else{
						html.append("<td align=\"left\" nowrap=\"nowrap\" width=\""
								+ 100 / (rs.length - 2) + "%\" ");
					}
					
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					// 增加学号链接 //学生岗位查询和酬金发放查询冲突
					if (j == 7 && 14 == rs.length) {
						html
								.append("<a href='javascript:void(0);' class='name' onclick='zxsxxView(&quot;"
										+ replaceHtml(rs[j])
										+ "&quot;);return false;'>"
										+ replaceHtml(rs[j]) + "</a>");
					} else if(j==9){
						if(rs[j].length()>12){
							html.append(replaceHtml(rs[j].substring(0, 11)+"..."));
							
						}else{
							html.append(replaceHtml(rs[j]));
						}
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
	 * 获得默认查询参数
	 * 
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getMrCs(QgzxCxtjForm model) {
		HashMap<String, String> rs = new HashMap<String, String>();
		User user = model.getUser();
		// 权限控制
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		// 如果不是勤工管理员
		if (!qgzxGlyglService.sfQggly(user.getUserName())) {
			rs.put("dis", "true");
		}
		rs.put("nd", StringUtil.isNull(model.getNd()) ? Base.currNd : model
				.getNd());
		rs.put("yf", model.getYf());
		rs.put("bmdm", StringUtil.isNull(model.getBmdm()) ? user.getUserDep()
				: model.getBmdm());
		return rs;
	}

	/**
	 * 获得月份列表
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getYfList() {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String[] dm = new String[] { "01", "02", "03", "04", "05", "06", "07",
				"08", "09", "10", "11", "12" };
		HashMap<String, String> map = null;
		for (int i = 0; i < dm.length; i++) {
			map = new HashMap<String, String>();
			map.put("yf", dm[i]);
			list.add(map);
		}
		return list;
	}

	/**
	 * 获得部门列表
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getBmList() {
		return qgzxCxtjDAO.getBmList();
	}

	/**
	 * 部门酬金发放统计
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> bmcjffTj(QgzxCxtjForm model) throws Exception {
		if (model.getBmdm() == null || "".equalsIgnoreCase(model.getBmdm())) {
			model.setBmdm("%");
		}
		if (null == model.getYf() || "".equalsIgnoreCase(model.getYf())) {
			// 年度
			return qgzxCxtjDAO.bmcjffTjByNd(model);
		} else {
			// 月份
			return qgzxCxtjDAO.bmcjffTjByYf(model);
		}
	}

	/**
	 * @描述:岗位酬金发放统计
	 * @作者：cp[工号：1352]
	 * @日期：2017-8-18 上午10:40:57
	 * @param model
	 * @return
	 * @throws Exception
	 * ArrayList<String[]> 返回类型 
	 * @throws
	 */
	public ArrayList<String[]> gwcjffTj(QgzxCxtjForm model) throws Exception {
		if (model.getBmdm() == null || "".equalsIgnoreCase(model.getBmdm())) {
			model.setBmdm("%");
		}
		if (model.getGwmc() == null || "".equalsIgnoreCase(model.getGwmc())) {
			model.setGwmc("%");
		}
		if (null == model.getYf() || "".equalsIgnoreCase(model.getYf())) {
			// 年度
			return qgzxCxtjDAO.gwcjffTjByNd(model);
		} else {
			// 月份
			return qgzxCxtjDAO.gwcjffTjByYf(model);
		}
	}
	/**
	 * 个人酬金发放统计
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> grcjffTj(QgzxCxtjForm model) throws Exception {
		if (model.getBmdm() == null || "".equalsIgnoreCase(model.getBmdm())) {
			model.setBmdm("%");
		}
		if (model.getGwmc() == null || "".equalsIgnoreCase(model.getGwmc())) {
			model.setGwmc("%");
		}
		if (null == model.getYf() || "".equalsIgnoreCase(model.getYf())) {
			// 年度
			return qgzxCxtjDAO.grcjffTjByNd(model);
		} else {
			// 月份
			return qgzxCxtjDAO.grcjffTjByYf(model);
		}
	}
	
	/**
	 * @描述：个人酬金发放统计月份导出
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年5月23日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * File 返回类型
	 */
	public File getjffTjyf(QgzxCxtjForm model) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		List<HashMap<String,String>> jtfftjYfList=qgzxCxtjDAO.getjffTjByYf(model);
		data.put("jtfftjYfList", jtfftjYfList);
		data.put("xn",Base.currXn);
		data.put("xxmc",Base.xxmc);
		data.put("nd", model.getNd());
		data.put("yf", model.getYf());
		File file = FreeMarkerUtil.getExcelFile(data, "classpath:templates/qgzx", "qgzxfyff_10277.xml", "个人酬金发放统计_"+model.getNd()+model.getYf());
		return file;
	}

	/**
	 * 部门统计
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String createSearchHTMLByBm(QgzxCxtjForm model,
			SearchRsModel rsModel, ArrayList<String[]> rsArrList, User user)
			throws Exception {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			if (model.getYf() == null || "".equalsIgnoreCase(model.getYf())) {
				html
						.append("<thead align=\"center\"><tr><td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">用人部门</td>");
				for (int i = 1; i < 13; i++) {
					html
							.append("<td align=\"middle\" colspan=\"3\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">"
									+ i + "月</td>");
				}
				html
						.append("<td align=\"middle\" colspan=\"3\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">合计</td></tr><tr>");
				for (int i = 0; i < 13; i++) {
					html
							.append("<td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">岗位数</td>");
					html
							.append("<td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">人次</td>");
					html
							.append("<td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">发放金额<元></td>");
				}
				html.append("</tr></thead><tr>");
			} else {
				html
						.append("<thead align=\"center\"><tr><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">用人部门</td>"
								+ "<td align=\"left\" nowrap=\"nowrap\" width=\"25%\" height=\"28.5px\">勤工人数</td>"
								+ "<td align=\"left\" nowrap=\"nowrap\" width=\"25%\" height=\"28.5px\">人次</td>"
								+ "<td align=\"left\" nowrap=\"nowrap\" width=\"25%\" height=\"28.5px\">总工时</td>"
								+ "<td align=\"left\" nowrap=\"nowrap\" width=\"25%\" height=\"28.5px\">总酬金<元></td>"
								+ "</tr></thead><tr>");
			}
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				for (int j = 0; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\""
							+ 100 / (rs.length) + "%\" ");
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}

	/**
	 * 
	 * @描述:岗位统计
	 * @作者：CP[工号：1352]
	 * @日期：2017-8-18 上午10:40:18
	 * @param model
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String createSearchHTMLByGw(QgzxCxtjForm model,
			SearchRsModel rsModel, ArrayList<String[]> rsArrList, User user)
			throws Exception {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			if (model.getYf() == null || "".equalsIgnoreCase(model.getYf())) {
				html.append("<thead align=\"center\"><tr><td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">用人部门</td>");
				html.append("<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">岗位</td>");
				for (int i = 1; i < 13; i++) {
					html.append("<td align=\"middle\" colspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">"
									+ i + "月</td>");
				}
				html.append("<td align=\"middle\" colspan=\"3\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">合计</td></tr><tr>");
				for (int i = 0; i < 13; i++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">人次</td>");
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">发放金额<元></td>");
				}
				html.append("</tr></thead><tr>");
			} else {
				html.append("<thead align=\"center\"><tr><td align=\"left\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">用人部门</td>"
								+ "<td align=\"left\" nowrap=\"nowrap\" width=\"25%\" height=\"28.5px\">岗位</td>"
								+ "<td align=\"left\" nowrap=\"nowrap\" width=\"25%\" height=\"28.5px\">勤工人数</td>"
								+ "<td align=\"left\" nowrap=\"nowrap\" width=\"25%\" height=\"28.5px\">人次</td>"
								+ "<td align=\"left\" nowrap=\"nowrap\" width=\"25%\" height=\"28.5px\">总工时</td>"
								+ "<td align=\"left\" nowrap=\"nowrap\" width=\"25%\" height=\"28.5px\">总酬金<元></td>"
								+ "</tr></thead><tr>");
			}
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				for (int j = 0; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\""
							+ 100 / (rs.length) + "%\" ");
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	/**
	 * 个人统计
	 * 
	 * @param model
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTMLByGr(QgzxCxtjForm model,
			SearchRsModel rsModel, ArrayList<String[]> rsArrList, User user) {

		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			if (model.getYf() == null || "".equalsIgnoreCase(model.getYf())) {
				if(model.getBmdm() != null && "%".equals(model.getBmdm())) {
					html
							.append("<thead align=\"center\"><tr><td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">学号</td>"
									+ "<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">姓名</td>"
									+ "<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">学院</td>"
									+ "<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">用人部门</td>"
									+ "<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">岗位名称</td>"
									+ "<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">银行名称</td>"
									+ "<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">银行卡号</td>"
									+ "<td align=\"middle\" colspan=\"13\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">明细统计</td></tr><tr>");
				}else {
					html
							.append("<thead align=\"center\"><tr><td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">学号</td>"
									+ "<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">姓名</td>"
									+ "<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">学院</td>"
									+ "<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">岗位名称</td>"
									+ "<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">银行名称</td>"
									+ "<td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">银行卡号</td>"
									+ "<td align=\"middle\" colspan=\"13\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">明细统计</td></tr><tr>");
				}
				for (int i = 1; i < 13; i++) {
					html
							.append("<td align=\"middle\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">"
									+ i + "月</td>");
				}
				html
						.append("<td align=\"middle\" nowrap=\"nowrap\" width=\"33%\" height=\"28.5px\">合计</td></tr>");
				html.append("</thead></tr>");
			} else {
				if(model.getBmdm() != null && "%".equals(model.getBmdm())) {
					html
							.append("<thead align=\"center\"><td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">学号</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">姓名</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">年级</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">学院</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">用人部门</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">岗位名称</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">专业</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">班级</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">银行名称</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">银行卡号</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">酬金<元></td></thead>");		
				}else {
					html
							.append("<thead align=\"center\"><td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">学号</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">姓名</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">年级</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">学院</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">岗位名称</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">专业</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">班级</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">银行名称</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">银行卡号</td>"
									+ "<td align=\"left\" nowrap=\"nowrap\" height=\"28.5px\">酬金<元></td></thead>");
				}
			}
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr>");
				if (i == rsArrList.size()
						&& (model.getYf() == null || "".equalsIgnoreCase(model
								.getYf()))) {

					for (int j = 3; j < rs.length; j++) {
						html
								.append("<td align=\"left\" nowrap=\"nowrap\" width=\""
										+ 100 / (rs.length) + "%\" ");
						if ("5.8".equalsIgnoreCase(ie)) {
							html.append("height=\"28.5px\"");
						} else {
							html.append("height=\"29px\"");
						}
						html.append(">");
						html.append(replaceHtml(rs[j]));
						html.append("</td>");
					}
				} else {
					for (int j = 0; j < rs.length; j++) {
						html
								.append("<td align=\"left\" nowrap=\"nowrap\" width=\""
										+ 100 / (rs.length) + "%\" ");
						if ("5.8".equalsIgnoreCase(ie)) {
							html.append("height=\"28.5px\"");
						} else {
							html.append("height=\"29px\"");
						}
						html.append(">");
						html.append(replaceHtml(rs[j]));
						html.append("</td>");
					}
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}

	/**
	 * 部门酬金发放导出
	 * 
	 * @param outputStream
	 * @param model
	 * @throws Exception
	 */
	public void bmcjffDc(ServletOutputStream outputStream, QgzxCxtjForm model)
			throws Exception {

		if (model.getYf() != null && !"".equalsIgnoreCase(model.getYf())) {
			String title = model.getNd() + "年度" + model.getYf() + "月份酬金发放统计";
			// 固定列
			String[] topTr = new String[] { "用人部门", "勤工人数", "人次","总工时", "总酬金" };

			// 导出报表的固定数据
			ArrayList<String[]> gdlist = bmcjffTj(model);

			WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(title, 0);
			WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
			wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true,
					Border.ALL);
			// 填充表头
			ExcelMB excel = new ExcelMB();
			excel.printTitle(ws, title, 5, 800);// 标题
			for (int i = 0; i < topTr.length; i++) {
				ws.setColumnView(i, 25);
				ws.addCell(new Label(i, 1, topTr[i], wcf2));
			}
			// 填充内容
			if (gdlist != null && gdlist.size() > 0) {
				for (int i = 0; i < gdlist.size(); i++) {
					String[] info = gdlist.get(i);
					if (info != null && info.length > 0) {
						for (int j = 0; j < info.length; j++) {
							ws.addCell(new Label(j, i + 2, info[j], wcf2));
						}
					}
				}
			}
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		} else {
			String title = model.getNd() + "年度部门报酬发放汇总表";

			// 导出报表的固定数据
			ArrayList<String[]> gdlist = bmcjffTj(model);

			WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(title, 0);
			WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
			wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true,
					Border.ALL);
			// 填充表头
			ExcelMB excel = new ExcelMB();
			excel.printTitle(ws, title, 40, 800);// 标题
			ws.mergeCells(0, 1, 0, 2);
			ws.addCell(new Label(0, 1, "用人部门", wcf2));
			for (int i = 1; i <= 13; i++) {
				if (i == 13) {
					ws.mergeCells((i - 1) * 3 + 1, 1, (i - 1) * 3 + 3, 1);
					ws.addCell(new Label((i - 1) * 3 + 1, 1, "合计", wcf2));
				} else {
					ws.mergeCells((i - 1) * 3 + 1, 1, (i - 1) * 3 + 3, 1);
					ws.addCell(new Label((i - 1) * 3 + 1, 1, i + "月", wcf2));
				}
			}
			for (int i = 1; i <= 13; i++) {
				ws.addCell(new Label((i - 1) * 3 + 1, 2, "岗位数", wcf2));
				ws.addCell(new Label((i - 1) * 3 + 2, 2, "人次", wcf2));
				ws.addCell(new Label((i - 1) * 3 + 3, 2, "发放金额", wcf2));
			}
			// 填充内容
			if (gdlist != null && gdlist.size() > 0) {
				for (int i = 0; i < gdlist.size(); i++) {
					String[] info = gdlist.get(i);
					if (info != null && info.length > 0) {
						for (int j = 0; j < info.length; j++) {
							ws.addCell(new Label(j, i + 3, info[j], wcf2));
						}
					}
				}
			}
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		}

	}
	/**
	 * 
	 * @描述:岗位酬金发放导出
	 * @作者：CP[工号：1352]
	 * @日期：2017-8-18 上午09:58:37
	 * @param outputStream
	 * @param model
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void gwcjffDc(ServletOutputStream outputStream, QgzxCxtjForm model)
	throws Exception {
		if (model.getYf() != null && !"".equalsIgnoreCase(model.getYf())) {
			String title = model.getNd() + "年度" + model.getYf() + "月份酬金发放统计";
			// 固定列
			String[] topTr = new String[] { "用人部门", "岗位", "勤工人数", "人次", "总工时",
					"总酬金" };

			// 导出报表的固定数据
			ArrayList<String[]> gdlist = gwcjffTj(model);

			WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(title, 0);
			WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
			wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true,
					Border.ALL);
			// 填充表头
			ExcelMB excel = new ExcelMB();
			excel.printTitle(ws, title, 5, 800);// 标题
			for (int i = 0; i < topTr.length; i++) {
				ws.setColumnView(i, 25);
				ws.addCell(new Label(i, 1, topTr[i], wcf2));
			}
			// 填充内容
			if (gdlist != null && gdlist.size() > 0) {
				for (int i = 0; i < gdlist.size(); i++) {
					String[] info = gdlist.get(i);
					if (info != null && info.length > 0) {
						for (int j = 0; j < info.length; j++) {
							ws.addCell(new Label(j, i + 2, info[j], wcf2));
						}
					}
				}
			}
	ExcelMethods.submitWritableWorkbookOperations(wwb);
} else {
	String title = model.getNd() + "年度岗位酬金发放汇总表";

	// 导出报表的固定数据
	ArrayList<String[]> gdlist = gwcjffTj(model);

	WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
	WritableSheet ws = wwb.createSheet(title, 0);
	WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
	wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
			Alignment.CENTRE, VerticalAlignment.CENTRE, true,
			Border.ALL);
	// 填充表头
	ExcelMB excel = new ExcelMB();
	excel.printTitle(ws, title, 40, 800);// 标题
	ws.mergeCells(0, 1, 0, 2);
	ws.mergeCells(1, 1, 1, 2);
	ws.addCell(new Label(0, 1, "用人部门", wcf2));
	ws.addCell(new Label(1, 1, "岗位", wcf2));
	for (int i = 1; i <= 13; i++) {
		if (i == 13) {
			ws.mergeCells((i - 1) * 2 + 2, 1, (i - 1) * 2 + 3, 1);
			ws.addCell(new Label((i - 1) * 2 + 2, 1, "合计", wcf2));
		} else {
			ws.mergeCells((i - 1) * 2 + 2, 1, (i - 1) * 2 + 3, 1);
			ws.addCell(new Label((i - 1) *2 + 2, 1, i + "月", wcf2));
		}
	}
	for (int i = 1; i <= 13; i++) {
		ws.addCell(new Label((i - 1) * 2 + 2, 2, "人次", wcf2));
		ws.addCell(new Label((i - 1) * 2 + 3, 2, "发放金额", wcf2));
	}
	// 填充内容
	if (gdlist != null && gdlist.size() > 0) {
		for (int i = 0; i < gdlist.size(); i++) {
			String[] info = gdlist.get(i);
			if (info != null && info.length > 0) {
				for (int j = 0; j < info.length; j++) {
					ws.addCell(new Label(j, i + 3, info[j], wcf2));
				}
			}
		}
	}
	ExcelMethods.submitWritableWorkbookOperations(wwb);
}

}
	/**
	 * 个人酬金发放导出
	 * 
	 * @param outputStream
	 * @param model
	 * @throws Exception
	 */
	public void grcjffDc(ServletOutputStream outputStream, QgzxCxtjForm model)
			throws Exception {
		
		String[] topTr = null;
		// 浙江传媒按月份导出
		if (Globals.XXDM_ZJCMXY.equals(Base.xxdm)
				&& !StringUtil.isNull(model.getYf())) {
			zjcmCjff(outputStream, model);

		} else if (!StringUtil.isNull(model.getYf())) {
			String title = model.getNd()
					+ "年度"
					+ (Base.isNull(model.getBmdm()) ? "所有" : getBmmc(model
							.getBmdm())) + "部门" + model.getYf() + "月份酬金发放统计";
			// 导出报表表头
			if(model.getBmdm() != null && "".equals(model.getBmdm())) {
				topTr = new String[] { "学号", "姓名", "年级", "学院", "用人部门", "岗位名称", "专业", "班级",
						"银行名称", "银行卡号", "酬金", "备注" };
			}else {
				topTr = new String[] { "学号", "姓名", "年级", "学院", "岗位名称", "专业", "班级",
						"银行名称", "银行卡号", "酬金", "备注" };
			}

			// 导出报表的固定数据
			ArrayList<String[]> gdlist = grcjffTj(model);
			ArrayList<String[]> gdlistN = new ArrayList<String[]>();
			for (String[] str : gdlist) {
				gdlistN.add(Arrays2.addObject(str, ""));
			}
			gdlist = gdlistN;
			WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(title, 0);
			WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
			wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true,
					Border.ALL);
			// 填充表头
			ExcelMB excel = new ExcelMB();
			if(model.getBmdm() != null && "%".equals(model.getBmdm())) {
				excel.printTitle(ws, title, 12, 800);// 标题
			}else {
				excel.printTitle(ws, title, 11, 800);// 标题
			}
			for (int i = 0; i < topTr.length; i++) {
				ws.setColumnView(i, 25);
				ws.addCell(new Label(i, 1, topTr[i], wcf2));
			}
			// 填充内容
			if (gdlist != null && gdlist.size() > 0) {
				for (int i = 0; i < gdlist.size(); i++) {
					String[] info = gdlist.get(i);
					if (info != null && info.length > 0) {
						for (int j = 0; j < info.length; j++) {
							ws.addCell(new Label(j, i + 2, info[j], wcf2));
						}
					}
				}
			}
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		} else {
			String title = model.getNd()
					+ "年度"
					+ (Base.isNull(model.getBmdm()) ? "所有" : getBmmc(model
							.getBmdm())) + "部门学生每月报酬发放明细汇总";
			ArrayList<String[]> gdlistN = new ArrayList<String[]>();
			ArrayList<String[]> gdlist = grcjffTj(model);
			for (String[] str : gdlist) {
				gdlistN.add(Arrays2.addObject(str, ""));
			}
			gdlist = gdlistN;
			WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(title, 0);
			WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
			wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true,
					Border.ALL);

			ExcelMB excel = new ExcelMB();
			excel.printTitle(ws, title, 17, 800);// 标题
			if(model.getBmdm() != null && "%".equals(model.getBmdm())) {
				ws.mergeCells(0, 1, 0, 2);
				ws.addCell(new Label(0, 1, "学号", wcf2));
				ws.mergeCells(1, 1, 1, 2);
				ws.addCell(new Label(1, 1, "姓名", wcf2));
				ws.mergeCells(2, 1, 2, 2);
				ws.addCell(new Label(2, 1, "学院", wcf2));
				ws.mergeCells(3, 1, 3, 2);
				ws.addCell(new Label(3, 1, "用人部门", wcf2));
				ws.mergeCells(4, 1, 4, 2);
				ws.addCell(new Label(4, 1, "岗位名称", wcf2));
				ws.mergeCells(5, 1, 5, 2);
				ws.addCell(new Label(5, 1, "银行名称", wcf2));
				ws.mergeCells(6, 1, 6, 2);
				ws.addCell(new Label(6, 1, "银行卡号", wcf2));
				ws.mergeCells(7, 1, 18, 1);
				ws.addCell(new Label(7, 1, "明细统计", wcf2));
				for (int i = 1; i < 14; i++) {
					ws.addCell(new Label(i + 6, 2, i + "月", wcf2));
				}
				ws.mergeCells(19, 1, 19, 2);
				ws.addCell(new Label(19, 1, "合计", wcf2));
				ws.mergeCells(20, 1, 20, 2);
				ws.addCell(new Label(20, 1, "备注", wcf2));
				
			}else {
				ws.mergeCells(0, 1, 0, 2);
				ws.addCell(new Label(0, 1, "学号", wcf2));
				ws.mergeCells(1, 1, 1, 2);
				ws.addCell(new Label(1, 1, "姓名", wcf2));
				ws.mergeCells(2, 1, 2, 2);
				ws.addCell(new Label(2, 1, "学院", wcf2));
				ws.mergeCells(3, 1, 3, 2);
				ws.addCell(new Label(3, 1, "岗位名称", wcf2));
				ws.mergeCells(4, 1, 4, 2);
				ws.addCell(new Label(4, 1, "银行名称", wcf2));
				ws.mergeCells(5, 1, 5, 2);
				ws.addCell(new Label(5, 1, "银行卡号", wcf2));
				ws.mergeCells(6, 1, 17, 1);
				ws.addCell(new Label(6, 1, "明细统计", wcf2));
				for (int i = 1; i < 13; i++) {
					ws.addCell(new Label(i + 5, 2, i + "月", wcf2));
				}
				ws.mergeCells(18, 1, 18, 2);
				ws.addCell(new Label(18, 1, "合计", wcf2));
				ws.mergeCells(19, 1, 19, 2);
				ws.addCell(new Label(19, 1, "备注", wcf2));
			}
			// 填充内容
			if (gdlist != null && gdlist.size() > 0) {
				for (int i = 0; i < gdlist.size(); i++) {
					String[] info = gdlist.get(i);
					if (i == gdlist.size()) {
						for (int j = 3; j < info.length; j++) {
							ws.addCell(new Label(j, i + 3, info[j], wcf2));
						}
					} else {
						for (int j = 0; j < info.length; j++) {
							ws.addCell(new Label(j, i + 3, info[j], wcf2));
						}
					}
				}
			}
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		}

	}

	/**
	 * 浙江传媒酬金发放导出汇总表（按月份）
	 * 
	 * @param outputStream
	 * @param model
	 * @throws IOException
	 * @throws Exception
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	private void zjcmCjff(ServletOutputStream outputStream, QgzxCxtjForm model)
			throws IOException, Exception, WriteException,
			RowsExceededException {
		String title = new StringBuilder("浙江传媒学院勤工助学经费发放审批汇总表（").append(
				model.getNd()).append("年").append(model.getYf()).append("月）")
				.toString();

		String[] topTr = new String[] { "序号", "银行卡号", "银行名称", "学号", "学生姓名",
				"用工部门/学院", "所属学院", "岗位性质", "贫困程度", "学历层次", "每 小时工价", "用工时间",
				"金额" };
		List<String[]> dataList = qgzxCxtjDAO.zjcmCjffByYf(model);

		WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
		WritableSheet ws = wwb.createSheet(title, 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// 填充表头
		ExcelMB excel = new ExcelMB();
		excel.printTitle(ws, title, 13, 800);// 标题

		for (int i = 0; i < topTr.length; i++) {
			// ws.setColumnView(i, 15);
			ws.addCell(new Label(i, 1, topTr[i], wcf2));
		}
		// 填充内容
		if (dataList != null && dataList.size() > 0) {
			for (int i = 0; i < dataList.size(); i++) {
				String[] info = dataList.get(i);
				if (info != null && info.length > 0) {
					for (int j = 0; j < info.length; j++) {
						ws.addCell(new Label(j, i + 2, info[j], wcf2));
					}
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * 根据部门代码获得部门名称
	 * 
	 * @param bmdm
	 * @return
	 */
	private String getBmmc(String bmdm) {
		return qgzxCxtjDAO.getBmmc(bmdm);
	}

	/**
	 * 岗位信息查自定义导出
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> gwxxCxExport(QgzxCxtjForm model,
			User user) throws Exception {
		return qgzxCxtjDAO.gwxxCxExport(model, user);
	}

	/**
	 * 学生岗位查询自定义导出
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xsgwCxExport(QgzxCxtjForm model,
			User user) throws Exception {
		return qgzxCxtjDAO.xsgwCxExport(model, user);
	}

	/**
	 * 经费划拨查询自定义导出
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> jfhbCxExport(QgzxCxtjForm model,
			User user) throws Exception {
		return qgzxCxtjDAO.jfhbCxExport(model, user);
	}

	/**
	 * 酬金发放查询自定义导出
	 * 
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> cjffCxExport(QgzxCxtjForm model,
			User user) throws Exception {
		return qgzxCxtjDAO.cjffCxExport(model, user);
	}
	
	/**
	 * @描述:北京林业大学下载申报表
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-19 上午09:30:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public void cjffCxExportData_10022(QgzxCxtjForm model, OutputStream os, User user) throws Exception {
		// 宋体,12,CENTRE
		WritableCellFormat s12CentreFormat = new WritableCellFormat();
		WritableFont s12CentreFont = new WritableFont(WritableFont.createFont("宋体"),12);
		s12CentreFormat.setFont(s12CentreFont);
		s12CentreFormat.setAlignment(Alignment.CENTRE);
		// 楷体,12,BOLD,CENTRE
		WritableCellFormat k12CentreBoldFormat = new WritableCellFormat();
		WritableFont k12CentreBoldFont = new WritableFont(WritableFont.createFont("楷体"),12);
		k12CentreBoldFont.setBoldStyle(WritableFont.BOLD);
		k12CentreBoldFormat.setFont(k12CentreBoldFont);
		k12CentreBoldFormat.setAlignment(Alignment.CENTRE);
		// 宋体,12,Wrap,CENTRE
		WritableCellFormat s12CentreWrapFormat = new WritableCellFormat();
		WritableFont s12CentreWrapFont = new WritableFont(WritableFont.createFont("宋体"),12);
		s12CentreWrapFormat.setFont(s12CentreWrapFont);
		s12CentreWrapFormat.setAlignment(Alignment.CENTRE);
		s12CentreWrapFormat.setWrap(true);
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		// 结果集
		List<HashMap<String, String>> dataList = qgzxCxtjDAO.cjffCxExportData_10022(model, user);
		// =============== 处理日期 begin =====================
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		String[] nd = model.getSearchModel().getSearch_tj_nd();
		String[] yf = model.getSearchModel().getSearch_tj_yf();
		Arrays.sort(yf);
		int ndInt = Integer.parseInt(nd[0]);
		int yfBeginInt = Integer.parseInt(yf[0]) - 1;
		int yfEndInt = Integer.parseInt(yf[yf.length - 1]) - 1;
		Calendar c = Calendar.getInstance();
		c.set(ndInt, yfBeginInt, 1);
		String yfBeginStr = format.format(c.getTime());
		c.set(Calendar.MONTH, yfEndInt);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		String yfEndStr = format.format(c.getTime());
		String ygqj = "用工期间：" + yfBeginStr + "至" + yfEndStr;
		// =============== 处理日期 end =====================
		// 用于验证是否相同的用人部门
		HashMap<String,String> tempMap = new HashMap<String,String>();
		int sheetNum = 0;
		WritableSheet ws = null;
		// 总列数
		int columns  = 7;
		int index  = 0; // 序号
		for (int m = 0; m < dataList.size(); m++) {
			HashMap<String,String> dataMap = dataList.get(m);
			if(!dataMap.get("yrdwdm").equals(tempMap.get("yrdwdm"))){
				tempMap = dataMap;
				ws = wwb.createSheet(tempMap.get("yrdwmc")+"申报表", sheetNum);
				sheetNum++;
				index = 0; // 重置序号
				// 设置，每个列宽度
				ws.setColumnView(0, 7); 
				ws.setColumnView(1, 15); 
				ws.setColumnView(2, 12); 
				ws.setColumnView(3, 34); 
				ws.setColumnView(4, 26); 
				ws.setColumnView(5, 27); 
				ws.setColumnView(6, 23); 
				// 第1行
				WritableCellFormat s18BoldCenterFormat = new WritableCellFormat();
				WritableFont s18BoldCenterFont = new WritableFont(WritableFont.createFont("宋体"),18);
				s18BoldCenterFont.setBoldStyle(WritableFont.BOLD);
				s18BoldCenterFormat.setFont(s18BoldCenterFont);
				s18BoldCenterFormat.setAlignment(Alignment.CENTRE);
				ws.addCell(new Label(0, 0, "北京林业大学勤工助学补助申报表", s18BoldCenterFormat));
				ws.mergeCells(0, 0, (columns - 1), 0);
				// 第2行
				WritableCellFormat r2Format = new WritableCellFormat();
				WritableFont r2Font = new WritableFont(WritableFont.createFont("宋体"),18);
				r2Font.setBoldStyle(WritableFont.BOLD);
				r2Format.setFont(r2Font);
				r2Format.setAlignment(Alignment.CENTRE);
				ws.addCell(new Label(0, 1, "", r2Format));
				// 第3行
				ws.addCell(new Label(0, 2, "用工部门：" + tempMap.get("yrdwmc") + "    " + ygqj + "    工作内容：", k12CentreBoldFormat));
				ws.mergeCells(0, 2, (columns - 1), 2);
				// 第4行
				ws.addCell(new Label(0, 3, "序  号", s12CentreFormat));
				ws.addCell(new Label(1, 3, "学  号", s12CentreFormat));
				ws.addCell(new Label(2, 3, "姓  名", s12CentreFormat));
				ws.addCell(new Label(3, 3, "班  级", s12CentreFormat));
				ws.addCell(new Label(4, 3, "手  机  号\n（没有手机写宿舍电话）", s12CentreWrapFormat));
				ws.addCell(new Label(5, 3, "工  作  量\n（单位：小时/天/月/次）", s12CentreWrapFormat));
				ws.addCell(new Label(6, 3, "备  注", s12CentreFormat));
			}
			// 学生信息
			tempMap = dataMap;
			index++;
			int rowNum = 3 + index;
			ws.addCell(new Label(0, rowNum, String.valueOf(index), s12CentreFormat));
			ws.addCell(new Label(1, rowNum, tempMap.get("xh"), s12CentreFormat));
			ws.addCell(new Label(2, rowNum, tempMap.get("xm"), s12CentreFormat));
			ws.addCell(new Label(3, rowNum, tempMap.get("bjmc"), s12CentreFormat));
			ws.addCell(new Label(4, rowNum, tempMap.get("qsdhsjhm"), s12CentreFormat));
			ws.addCell(new Label(5, rowNum, tempMap.get("gs"), s12CentreFormat));
			ws.addCell(new Label(6, rowNum, "", s12CentreFormat));
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//向客户端输出
	}

	/**
	 * 
	 * @描述:传媒个性化导出
	 * @作者：cq[工号：982]
	 * @日期：2013-12-26 下午05:09:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param os
	 * @throws Exception void 返回类型
	 * @throws
	 */
	public void cjffExpCm(QgzxCxtjForm model, OutputStream os, User user)
			throws Exception {

		// 学校名称
		String xxmc = "浙江传媒学院";

		// 获奖名单统计列表
		List<HashMap<String, String>> list = qgzxCxtjDAO.getCjffExpCm(model,
				user);

		// 设置标题
		StringBuffer title = new StringBuffer();
		title.append(xxmc);
		title.append("勤工助学经费发放审批汇总表");
		if (model.getFfnd() != null && model.getFfnd().length == 1
				&& StringUtils.isNotNull(model.getFfnd()[0])) {
			title.append("(" + model.getFfnd()[0] + "年");
			if (model.getFfyf() != null && model.getFfyf().length == 1
					&& StringUtils.isNotNull(model.getFfyf()[0])) {
				title.append(model.getFfyf()[0] + "月");
			}
			title.append(")");
		}
		// title.append(list != null && list.size() > 0 && list.get(0)!=null &&
		// list.get(0).get("xymc") !=null ? list.get(0).get("xymc") :"");

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("工资表", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 12, 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 17, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);

		// 合并单元格
		ws.mergeCells(1, 2, 3, 2);
		ws.mergeCells(8, 2, 10, 2);

		// 获得当前用户的信息
		HashMap<String, String> userInfo = qgzxCxtjDAO.getUserInfo(user);

		// 获得当前时间

		String Dete = GetTime.getTimeByFormat("yyyy-mm-dd");

		ex.printToOneCell_multy(ws, "部门或学院名称：" + userInfo.get("bmmc") + "（盖章）",
				1, 2, 10, true, Alignment.CENTRE, VerticalAlignment.CENTRE,
				true, 700, Border.NONE);
		ex.printToOneCell_multy(ws, "制表日期：" + Dete, 8, 2, 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);

		ws.addCell(new Label(0, 4, "序号", wcf2));
		ws.addCell(new Label(1, 4, "银行卡号", wcf2));
		ws.addCell(new Label(2, 4, "银行名称", wcf2));
		ws.addCell(new Label(3, 4, "学号", wcf2));
		ws.addCell(new Label(4, 4, "学生姓名", wcf2));
		ws.addCell(new Label(5, 4, "用工部门/学院", wcf2));
		ws.addCell(new Label(6, 4, "所属学院", wcf2));
		ws.addCell(new Label(7, 4, "岗位性质", wcf2));
		ws.addCell(new Label(8, 4, "贫困程度", wcf2));
		ws.addCell(new Label(9, 4, "学历层次", wcf2));
		ws.addCell(new Label(10, 4, "每小时工价", wcf2));
		ws.addCell(new Label(11, 4, "用工时间", wcf2));
		ws.addCell(new Label(12, 4, "金额", wcf2));

		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);

				ws.setColumnView(0, 5);
				ws.setColumnView(1, 20);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 13);
				ws.setColumnView(4, 10);
				ws.setColumnView(5, 18);
				ws.setColumnView(6, 18);
				ws.setColumnView(7, 10);
				ws.setColumnView(8, 10);
				ws.setColumnView(9, 10);
				ws.setColumnView(10, 7);
				ws.setColumnView(11, 5);
				ws.setColumnView(12, 7);

				ws.addCell(new Label(0, 5 + i, map.get("r"), wcf2));// 序号
				ws.addCell(new Label(1, 5 + i, map.get("yhkh"), wcf2));// 银行卡号
				ws.addCell(new Label(2, 5 + i, map.get("yhmc"), wcf2));// 银行名称
				ws.addCell(new Label(3, 5 + i, map.get("xh"), wcf2));// 学号
				ws.addCell(new Label(4, 5 + i, map.get("xm"), wcf2));// 学生姓名
				ws.addCell(new Label(5, 5 + i, map.get("yrdwmc"), wcf2));// 用工部门/学院
				ws.addCell(new Label(6, 5 + i, map.get("xymc"), wcf2));// 所属学院
				ws.addCell(new Label(7, 5 + i, map.get("gwxzmc"), wcf2));// 岗位性质
				ws.addCell(new Label(8, 5 + i, map.get("pkdj"), wcf2));// 贫困程度
				ws.addCell(new Label(9, 5 + i, map.get("xlmc"), wcf2));// 学历层次
				ws.addCell(new Label(10, 5 + i, map.get("mxsgj"), wcf2));// 每小时工价
				ws.addCell(new Label(11, 5 + i, map.get("gs"), wcf2));// 用工时间
				ws.addCell(new Label(12, 5 + i, map.get("je"), wcf2));// 金额
			}

		}

		// 底部单元格合并
		ws.mergeCells(1, 6 + list.size(), 3, 5 + list.size());
		ws.mergeCells(8, 6 + list.size(), 10, 5 + list.size());

		ex.printToOneCell_multy(ws, "制表人签名：", 1, 6 + list.size(), 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		ex.printToOneCell_multy(ws, "部门领导签名：", 8, 6 + list.size(), 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * 
	 * @描述:济南工程职业技术学院勤工助学考核个性化导出
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-6-30 下午05:39:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param os
	 * @param user
	 * @throws Exception void 返回类型
	 * @throws
	 */
	public void cjffExpJn(QgzxCxtjForm model, OutputStream os, User user)
			throws Exception {

		// 勤工助学酬金发放统计列表
		List<HashMap<String, String>> list = qgzxCxtjDAO.getCjffExpJn(model,
				user);

		// 设置标题
		StringBuffer title = new StringBuffer();
		title.append("济南工程职业技术学院学生勤工助学考核表");

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("考核表", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 10, 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 17, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);

		ws.addCell(new Label(0, 4, "序号", wcf2));
		ws.addCell(new Label(1, 4, "姓名", wcf2));
		ws.addCell(new Label(2, 4, "性别", wcf2));
		ws.addCell(new Label(3, 4, "院(系)", wcf2));
		ws.addCell(new Label(4, 4, "班级", wcf2));
		ws.addCell(new Label(5, 4, "岗位职责", wcf2));
		ws.addCell(new Label(6, 4, "工作时间", wcf2));
		ws.addCell(new Label(7, 4, "出勤时间", wcf2));
		ws.addCell(new Label(8, 4, "考核等级", wcf2));
		ws.addCell(new Label(9, 4, "学生签字", wcf2));
		ws.addCell(new Label(10, 4, "备注", wcf2));
		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);

				ws.setColumnView(0, 5);
				ws.setColumnView(1, 20);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 18);
				ws.setColumnView(4, 18);
				ws.setColumnView(5, 10);
				ws.setColumnView(6, 10);
				ws.setColumnView(7, 20);
				ws.setColumnView(8, 10);
				ws.setColumnView(9, 10);
				ws.setColumnView(10, 10);
				ws.addCell(new Label(0, 5 + i, map.get("r"), wcf2));// 序号
				ws.addCell(new Label(1, 5 + i, map.get("xm"), wcf2));// 姓名
				ws.addCell(new Label(2, 5 + i, map.get("xb"), wcf2));// 性别
				ws.addCell(new Label(3, 5 + i, map.get("xymc"), wcf2));// 学院名称
				ws.addCell(new Label(4, 5 + i, map.get("bjmc"), wcf2));// 班级名称
				ws.addCell(new Label(5, 5 + i, map.get("gwmc"), wcf2));// 岗位名称
				ws.addCell(new Label(6, 5 + i, map.get("gs") + "小时", wcf2));// 工作时间
				ws.addCell(new Label(7, 5 + i, "", wcf2));// 出勤时间
				ws.addCell(new Label(8, 5 + i, map.get("khdj"), wcf2));// 考核等级
				ws.addCell(new Label(9, 5 + i, "", wcf2));// 学生签字
				ws.addCell(new Label(10, 5 + i, map.get("bz"), wcf2));// 备注
			}
		}
		// 底部单元格合并
		ws.mergeCells(1, 6 + list.size(), 10, 6 + list.size());
		ws.mergeCells(1, 7 + list.size(), 10, 7 + list.size());
		ws.mergeCells(1, 8 + list.size(), 10, 8 + list.size());
		ws.setRowView(6 + list.size(), 500);
		ws.setRowView(7 + list.size(), 500);
		ws.setRowView(8 + list.size(), 500);
		ex.printToOneCell_multy(ws, "说明:", 0, 6 + list.size(), 10, true,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		ex
				.printToOneCell_multy(
						ws,
						"1、各用人单位应对在岗勤工助学学生严格进行考核，对工作不负责的学生，用人单位可随时报学生处勤工助学办公室，经核实后，终止其勤工助学活动，一年内不安排工作。",
						1, 6 + list.size(), 10, true, Alignment.LEFT,
						VerticalAlignment.CENTRE, true, 700, Border.NONE);
		ex
				.printToOneCell_multy(
						ws,
						"2、学生勤工助学的报酬由基本报酬和考核报酬按6:4比例组成。（40%部分由各用工单位按其表现决定，分为优秀、合格、不合格三等。优秀按100%发放，合格按60%发放，不合格不发放并终止勤工助学活动。）",
						1, 7 + list.size(), 10, true, Alignment.LEFT,
						VerticalAlignment.CENTRE, true, 700, Border.NONE);
		ex.printToOneCell_multy(ws, "3、请各用人部门详细记录上岗学生出勤情况，以作为考核依据。", 1,
				8 + list.size(), 10, true, Alignment.LEFT,
				VerticalAlignment.CENTRE, true, 700, Border.NONE);
		ex.printToOneCell_multy(ws, "考核单位(盖章)：", 1, 9 + list.size(), 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		ex.printToOneCell_multy(ws, "考核人：", 6, 9 + list.size(), 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * 
	 * @描述:嘉兴职业技术学院勤工助学工作统计及补贴发放清单
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-23 下午07:09:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param os
	 * @param user
	 * @throws Exception void 返回类型
	 * @throws
	 */
	public List<File> cjffExpJzy(QgzxCxtjForm model, User user)
			throws Exception {
		// 勤工助学酬金发放统计列表
		List<HashMap<String, String>> cjfflist = qgzxCxtjDAO.getCjffExpJzy(
				model, user);
		Map<String, List<HashMap<String, String>>> cjffFzMap = new HashMap<String, List<HashMap<String, String>>>();
		List<HashMap<String, String>> list = null;
		// 将查询出的酬金统计按发放年月分组
		for (HashMap<String, String> cjffMap : cjfflist) {
			if (cjffFzMap.containsKey(cjffMap.get("ffny"))) {
				cjffFzMap.get(cjffMap.get("ffny")).add(cjffMap);
			} else {
				list = new ArrayList<HashMap<String, String>>();
				list.add(cjffMap);
				cjffFzMap.put(cjffMap.get("ffny"), list);
			}
		}
		List<File> files = new ArrayList<File>();
		for (Map.Entry<String, List<HashMap<String, String>>> entry : cjffFzMap
				.entrySet()) {
			File file=cjffExpJzyModel(entry.getValue(), user);
			files.add(file);
		}
		return files;
	}
	/**
	 * 
	 * @描述:创建导出文件
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-25 上午10:45:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * File 返回类型 
	 * @throws
	 */
	private File createFile(List<HashMap<String, String>> list) {
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()) {
			tempDir.mkdir();
		}
		// 创建导出文件
		File file = new File(tempDir.getPath() + "/"
				+ list.get(0).get("nd")+"-"+list.get(0).get("yf")+"勤工助学工作统计及补贴发放清单" + ".xls");
		file.setWritable(true);
		return file;
	}
	/**
	 * 
	 * @描述:构建导出模板
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-25 上午10:45:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @param os
	 * @param user
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File cjffExpJzyModel(List<HashMap<String, String>> list, User user) throws Exception {
		File file = createFile(list);
		FileOutputStream outputStream = new FileOutputStream(file);
		//查询月份勤工助学总金额和总工时
		HashMap<String,String> jeAndGs =qgzxCxtjDAO.getJeAndGs(list.get(0).get("ffny"));
		// 设置标题
		StringBuffer title = new StringBuffer();
		title.append("嘉兴职业技术学院");
		title.append(list.get(0).get("nd")).append("年").append(list.get(0).get("yf")).append("月份");
		title.append("勤工助学工作统计及补贴发放清单");
		WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
		WritableSheet ws = wwb.createSheet("考核表", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 7, 1);
		ws.mergeCells(0, 2, 7, 3);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 15, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.ALL);
		ex
				.printToOneCell_multy(ws, "分院、部门名称（盖章）：", 0, 2, 10, true,
						Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
						Border.ALL);

		ws.addCell(new Label(0, 4, "序号", wcf2));
		ws.addCell(new Label(1, 4, "岗位名称", wcf2));
		ws.addCell(new Label(2, 4, "发放年月", wcf2));
		ws.addCell(new Label(3, 4, "工时(小时)", wcf2));
		ws.addCell(new Label(4, 4, "金额(元)", wcf2));
		ws.addCell(new Label(5, 4, "姓名", wcf2));
		ws.addCell(new Label(6, 4, "银行卡号", wcf2));
		ws.addCell(new Label(7, 4, "签名", wcf2));
		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);

				ws.setColumnView(0, 5);
				ws.setColumnView(1, 20);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 18);
				ws.setColumnView(4, 18);
				ws.setColumnView(5, 10);
				ws.setColumnView(6, 20);
				ws.setColumnView(7, 20);

				ws.addCell(new Label(0, 5 + i, i+1+"", wcf2));// 序号
				ws.addCell(new Label(1, 5 + i, map.get("gwmc"), wcf2));// 岗位名称
				ws.addCell(new Label(2, 5 + i, map.get("ffny"), wcf2));// 发放年月
				ws.addCell(new Label(3, 5 + i, map.get("gs"), wcf2));// 工时
				ws.addCell(new Label(4, 5 + i, map.get("je"), wcf2));// 金额
				ws.addCell(new Label(5, 5 + i, map.get("xm"), wcf2));// 姓名
				ws.addCell(new Label(6, 5 + i, map.get("yhkh"), wcf2));// 银行卡号
				ws.addCell(new Label(7, 5 + i, "", wcf2));// 签名
			}
		}
		// 底部单元格合并
		ws.mergeCells(0, 5 + list.size(), 2, 5 + list.size());
		ws.mergeCells(5, 5 + list.size(), 7, 5 + list.size());
		ws.mergeCells(0, 6 + list.size(), 1, 6 + list.size());
		ws.mergeCells(4, 6 + list.size(), 6, 6 + list.size());
		ex.printToOneCell_multy(ws, "合计:", 0, 5 + list.size(), 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.ALL);
		ex.printToOneCell_multy(ws, jeAndGs.get("zgs"), 3, 5 + list.size(), 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.ALL);
		ex.printToOneCell_multy(ws, jeAndGs.get("zje"), 4, 5 + list.size(), 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.ALL);
		ex.printToOneCell_multy(ws, "", 5, 5 + list.size(), 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.ALL);

		ex.printToOneCell_multy(ws, "制表人签名(手签)：", 0, 6 + list.size(), 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		ex.printToOneCell_multy(ws, "分院（部门）领导签名：", 4, 6 + list.size(), 10,
				true, Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		return file;
	}
	
	
	
	/**
	 * 获得岗位列表
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getGwList() {
		return qgzxCxtjDAO.getGwList();
	}
	
	/**
	 * 部门酬金发放查询
	 */
	public ArrayList<String[]> bmcjffCx(QgzxCxtjForm model) throws Exception {
		return qgzxCxtjDAO.bmcjffCx(model);
	}
	
	/**
	 * 部门酬金发放查询html
	 */
	public String createBmcjffcxSearchHTML(QgzxCxtjForm model,
			SearchRsModel rsModel, ArrayList<String[]> rsArrList, User user)
			throws Exception {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			if (model.getYf() == null || "".equalsIgnoreCase(model.getYf())) {
				html.append("<thead align=\"center\"><tr><td align=\"middle\" rowspan=\"2\" nowrap=\"nowrap\" width=\"\" height=\"28.5px\">用人部门</td>");
				for (int i = 1; i < 13; i++) {
					html.append("<td align=\"middle\" colspan=\"1\" nowrap=\"nowrap\" width=\"\" height=\"28.5px\">" + i + "月</td>");
				}
				html.append("</tr></thead><tr>");
			}
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				for (int j = 0; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\" ");
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					String ztmc = replaceHtml(rs[j]);
					if("未录入".equals(ztmc)){
						html.append("<font color='#b6b6b6'>" + ztmc + "</font>");
					}else{
						html.append(ztmc);
					}
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	/**
	 * 部门酬金发放查询 导出
	 */
	public void bmcjffCxDc(ServletOutputStream outputStream, QgzxCxtjForm model)
			throws Exception {

		if (model.getYf() != null && !"".equalsIgnoreCase(model.getYf())) {
			String yf = model.getYf().replaceAll("0(\\d)", "$1");
			String title = model.getNd() + "年度" + yf + "月份酬金发放情况";
			// 固定列
//			String[] topTr = new String[] { "用人部门", yf + "月"};
			String[] topTr = new String[] { "用人部门", "发放状态"};

			// 导出报表的固定数据
			ArrayList<String[]> gdlist = bmcjffCx(model);

			WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(title, 0);
			WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
			wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true,
					Border.ALL);
			// 填充表头
			ExcelMB excel = new ExcelMB();
			excel.printTitle(ws, title, 2, 800);// 标题
			for (int i = 0; i < topTr.length; i++) {
				ws.setColumnView(i, 25);
				ws.addCell(new Label(i, 1, topTr[i], wcf2));
			}
			// 填充内容
			if (gdlist != null && gdlist.size() > 0) {
				for (int i = 0; i < gdlist.size(); i++) {
					String[] info = gdlist.get(i);
					if (info != null && info.length > 0) {
						for (int j = 0; j < info.length; j++) {
							ws.addCell(new Label(j, i + 2, info[j], wcf2));
						}
					}
				}
			}
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		} else {
			String title = model.getNd() + "年度酬金发放情况";

			// 导出报表的固定数据
			ArrayList<String[]> gdlist = bmcjffCx(model);

			WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(title, 0);
			WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
			wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true,
					Border.ALL);
			// 填充表头
			ExcelMB excel = new ExcelMB();
			excel.printTitle(ws, title, 13, 800);// 标题
//			ws.mergeCells(0, 1, 0, 2);
			ws.addCell(new Label(0, 1, "用人部门", wcf2));
			for (int i = 1; i < 13; i++) {
				ws.addCell(new Label(i, 1, i + "月", wcf2));
			}
			// 填充内容
			if (gdlist != null && gdlist.size() > 0) {
				for (int i = 0; i < gdlist.size(); i++) {
					String[] info = gdlist.get(i);
					if (info != null && info.length > 0) {
						for (int j = 0; j < info.length; j++) {
							ws.addCell(new Label(j, i + 2, info[j], wcf2));
						}
					}
				}
			}
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		}

	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:返回excel文件
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-4-19 下午04:47:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * File 返回类型 
	 * @throws
	 */
	public File getExcelFile(QgzxCxtjForm model) throws Exception{
		List<HashMap<String,String>> dataList = new QgzxCxtjDAO().getGrcjffDcList(model);
		List<HashMap<String,String>> gdList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>> lsList = new ArrayList<HashMap<String,String>>();
		if(dataList != null && !dataList.isEmpty()){
			for (int i = 0; i < dataList.size(); i++) {
				HashMap<String, String> tempMap = dataList.get(i);
				if(tempMap.get("gwxzmc").contains("固定")){
					gdList.add(tempMap);
				}else{
					lsList.add(tempMap);
				}
			}
		}
		//系统缓存区创建一个文件
		String fileName = "固定（临时）用工发放清册样表";
		if("12036".equals(Base.xxdm)){
			String bmmc="";
			if("".equals(model.getBmdm()) || model.getBmdm()==null ){
				bmmc = "全部";
			} else if("yj".equals(model.getBmdm())){
				bmmc = "所有院级";
			}else if("xj".equals(model.getBmdm())){
				bmmc = "所有校级";
			}else{
				bmmc = new QgzxCxtjService().getBmmc(model.getBmdm());
			}
			fileName = model.getYf() + "月" + bmmc + fileName;

		}
		File tempFile = new File(System.getProperty("java.io.tmpdir")+"/"+fileName+DateUtils.getTime()+".xls");
		if(!tempFile.exists()){
			tempFile.createNewFile();
		}
		//创建一个excel文件
		WritableWorkbook wwb = Workbook.createWorkbook(tempFile);
		createSheet(wwb,"临时",model,lsList);
		createSheet(wwb,"固定",model,gdList);
		wwb.write();
		wwb.close();
		return tempFile;
	}
	
	/**
	 * 
	 * @描述: 创建excel页签
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-4-19 下午05:02:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param wwb
	 * void 返回类型 
	 * @throws
	 */
	public void createSheet(WritableWorkbook wwb,String gwlx,QgzxCxtjForm model,List<HashMap<String,String>> data) throws Exception{
		String date = DateUtils.getLyr();
		int index = (wwb.getSheets() != null && wwb.getSheets().length-1 >= 0) ? wwb.getSheets().length-1 : 0;
		WritableSheet ws = wwb.createSheet(gwlx, index);
		/**设置列宽*/
		ws.setColumnView(0, 9);
	    ws.setColumnView(1, 13);
	    ws.setColumnView(2, 40);
	    ws.setColumnView(3, 10);
	    ws.setColumnView(4, 10);
	    ws.setColumnView(5, 13);
	    ws.setColumnView(6, 13);
		WritableFont tileFont = new WritableFont(WritableFont.ARIAL, 13, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//设置标题字体
		WritableFont titleheadFont = new WritableFont(WritableFont.ARIAL, 12,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//设置正文字体
		WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 12,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//设置正文字体
		WritableFont bodyValFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//设置正文字体
		WritableCellFormat title_cf = new WritableCellFormat(tileFont);//设置标题单元格字体
		WritableCellFormat titlehead_cf = new WritableCellFormat(titleheadFont);
		WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//设置正文单元格字体
		WritableCellFormat bodyval_cf = new WritableCellFormat(bodyValFont);//设置正文单元格字体
		
		title_cf.setAlignment(jxl.format.Alignment.CENTRE);//设置标题单元格对齐
		title_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		//title_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//设置标题单元格边框
		titlehead_cf.setAlignment(jxl.format.Alignment.LEFT);
		titlehead_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		body_cf.setAlignment(jxl.format.Alignment.CENTRE);//设置正文单元格对齐
		body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//设置正文单元格边框
		body_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		bodyval_cf.setAlignment(jxl.format.Alignment.CENTRE);//设置正文单元格对齐
		bodyval_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//设置正文单元格边框
		bodyval_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

		String bmmc="";
		if("".equals(model.getBmdm()) || model.getBmdm()==null ){
			bmmc = "全部";
		} else if("yj".equals(model.getBmdm())){
			bmmc = "所有院级";
		}else if("xj".equals(model.getBmdm())){
			bmmc = "所有校级";
		}else{
			bmmc = new QgzxCxtjService().getBmmc(model.getBmdm());
		}


		String title =  "浙江交通职业技术学院"+model.getNd()+"年"+model.getYf()+"月"+"勤工助学"+gwlx+""+"用工劳务支付清单"+"("+bmmc+")";
		
		Label TitleLabel = new Label(0, 0, title, title_cf);
		ws.addCell(TitleLabel);
		ws.mergeCells(0, 0, 6, 0);
		Label titleheadbm = new Label(0, 1, "发放部门：学生处", titlehead_cf);
		ws.addCell(titleheadbm);
		ws.mergeCells(0, 1, 1, 1);
		Label titleheadrq = new Label(4, 1, "制单日期："+date, titlehead_cf);
		ws.addCell(titleheadrq);
		ws.mergeCells(4, 1, 6, 1);
		Label xmtitle =  new Label(0, 2, "姓名", body_cf);
		ws.addCell(xmtitle);
		Label xhtitle =  new Label(1, 2, "学号", body_cf);
		ws.addCell(xhtitle);
		Label yhkhtitle =  new Label(2, 2, "工行卡号", body_cf);
		ws.addCell(yhkhtitle);
		Label lwsjtitle =  new Label(3, 2, "劳务时间", body_cf);
		ws.addCell(lwsjtitle);
		Label sfstitle =  new Label(4, 2, "实发数", body_cf);
		ws.addCell(sfstitle);
		Label lkrtitle =  new Label(5, 2, "领款人签字", body_cf);
		ws.addCell(lkrtitle);
		Label bztitle =  new Label(6, 2, "备注", body_cf);
		ws.addCell(bztitle);
		/**设置行高*/
		ws.setRowView(0, 500);
		ws.setRowView(1, 350);
		ws.setRowView(2, 500);
		//列表内容
		double lwsj = 0.0;
		double je = 0.0; 
		if(data != null && !data.isEmpty()){
			for (int i = 0; i < data.size(); i++) {
				Label xmcontent =  new Label(0, 3+i,data.get(i).get("xm"), bodyval_cf);
				ws.addCell(xmcontent);
				Label xhcontent =  new Label(1, 3+i, data.get(i).get("xh"), bodyval_cf);
				ws.addCell(xhcontent);
				Label yhkhcontent =  new Label(2, 3+i, data.get(i).get("yhkh"), bodyval_cf);
				ws.addCell(yhkhcontent);
				Label lwsjcontent =  new Label(3, 3+i, data.get(i).get("gs"), bodyval_cf);
				ws.addCell(lwsjcontent);
				Label sfscontent =  new Label(4, 3+i, data.get(i).get("e"), bodyval_cf);
				ws.addCell(sfscontent);
				Label lkrcontent =  new Label(5, 3+i, "", bodyval_cf);
				ws.addCell(lkrcontent);
				Label bzcontent =  new Label(6, 3+i, "", bodyval_cf);
				ws.addCell(bzcontent);
				lwsj = lwsj + Double.parseDouble(data.get(i).get("gs"));
				je = je + Double.parseDouble(data.get(i).get("e"));
				ws.setRowView(3+i, 500);
			}
		}else{
			for (int i = 0; i < 17; i++) {
				Label xmcontent =  new Label(0, 3+i,"", bodyval_cf);
				ws.addCell(xmcontent);
				Label xhcontent =  new Label(1, 3+i, "", bodyval_cf);
				ws.addCell(xhcontent);
				Label yhkhcontent =  new Label(2, 3+i, "", bodyval_cf);
				ws.addCell(yhkhcontent);
				Label lwsjcontent =  new Label(3, 3+i, "", bodyval_cf);
				ws.addCell(lwsjcontent);
				Label sfscontent =  new Label(4, 3+i, "", bodyval_cf);
				ws.addCell(sfscontent);
				Label lkrcontent =  new Label(5, 3+i, "", bodyval_cf);
				ws.addCell(lkrcontent);
				Label bzcontent =  new Label(6, 3+i, "", bodyval_cf);
				ws.addCell(bzcontent);
				ws.setRowView(3+i, 500);
			}
		}
		//总数
		int cnt = ws.getRows();
		
		Label hj  =  new Label(0, cnt, "合计", bodyval_cf);
		ws.addCell(hj);
		ws.mergeCells(0, cnt, 2, cnt);
		Label gshj  =  new Label(3, cnt, lwsj+"", bodyval_cf);
		ws.addCell(gshj);
		Label jehj  =  new Label(4, cnt, je+"", bodyval_cf);
		ws.addCell(jehj);
		Label blank1  =  new Label(5, cnt, "", bodyval_cf);
		ws.addCell(blank1);
		Label blank2  =  new Label(6, cnt, "", bodyval_cf);
		ws.addCell(blank2);
		ws.setRowView(cnt, 500);
		
		Label blank3  =  new Label(0, cnt+2, "学生处负责人：                   用工部门负责人：                       用工部门经办人：", titlehead_cf);
		ws.addCell(blank3);
	}
	
	/**
	 * 
	 * @描述:获取部门名称
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-13 下午03:20:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bmdm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public String getBmmc2(String bmdm){
		return new QgzxCxtjDAO().getBmmc2(bmdm);
	}
}
