package xsgzgl.wjcf.general.cfsbgl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.wjcf.general.WjcfGeneralForm;
import xsgzgl.wjcf.general.inter.WjcfCfshInterface;

/**
 * 
 * 
 * 类名称：WjcfCfsbAction 
 * 类描述：违纪处分处分审核Service 
 * 创建人：xucy 
 * 创建时间：2012-6-20 下午01:18:00
 * 修改备注：
 * 
 * @version
 * 
 */
public class WjcfCfshService extends CommService implements WjcfCfshInterface {

	private WjcfCfshDao dao = new WjcfCfshDao();

	/**
	 * 构建html
	 * 
	 * @author xucy
	 * @throws 
	 */
	public String createWjcfCfshHTML(SearchRsModel rsModel,
			WjcfCfshModel model, ArrayList<String[]> rsArrList, User user) {

		// IE版本
		String ie = rsModel.getIe();
		String stylePath = rsModel.getStylePath();
		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				String disabled1 = rs[11];// 下级审核控制
				String disabled2 = rs[10];// 提交控制
				html.append("<tr onclick=\"rowOnClick(this);\">");

				html.append("<td  align=\"left\" nowrap=\"nowrap\" style=\"width:8px\">");

				html.append("<input type='checkbox' name='primarykey_checkVal' ");
				if (!Base.isNull(disabled1) || !Base.isNull(disabled2)) {
					html.append(" disabled=\"true\"");
				}

				html.append(" id='pkValue_" + i + "' ");
				html.append(" value='" + rs[0] + "'/> ");

				html.append("</td>");

				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length - 1; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					if (j == 1) {
						html.append(" style=\"width:10%\"");
					}
					if (j == 2) {
						html.append(" style=\"width:5%\"");
					}
					if (j == 3) {
						html.append(" style=\"width:10%\"");
					}
					if (j == 4) {
						html.append(" style=\"width:10%\"");
					}
					if (j == 5) {
						html.append(" style=\"width:10%\"");
					}
					if (j == 6) {
						html.append(" style=\"width:10%\"");
					}
					if (j == 7) {
						html.append(" style=\"width:10%\"");
					}
					if (j == 8) {
						html.append(" style=\"width:10%\"");
					}
					if (j == 9) {

						if ("未审核".equalsIgnoreCase(rs[9])) {

							html.append("<p><img src=\""
											+ stylePath
											+ "images/ico_dsh.gif\" width=\"52\" height=\"18\" /></p>");

						} else if ("通过".equalsIgnoreCase(rs[9])) {

							html.append("<p><img src=\""
											+ stylePath
											+ "images/ico_shtg.gif\" width=\"52\" height=\"18\" /></p>");

						} else if ("不通过".equalsIgnoreCase(rs[9])) {

							html.append("<p><img src=\""
											+ stylePath
											+ "images/ico_shbtg.gif\" width=\"52\" height=\"18\" /></p>");

						} else if ("退回".equalsIgnoreCase(rs[9])) {

							html.append("<p><img src=\""
											+ stylePath
											+ "images/ico_shth.gif\" width=\"52\" height=\"18\" /></p>");

						} else if ("需重审".equalsIgnoreCase(rs[9])) {

							html.append("<p><img src=\""
											+ stylePath
											+ "images/ico_shxcs.gif\" width=\"52\" height=\"18\" /></p>");

						}
					}
					if (j == 10) {
						html.append(" style=\"width:10%\"");
						html.append(">");
						if ("".equalsIgnoreCase(rs[10])) {
							html.append("<font color=\"blue\">未提交</font>");
						}else{
							html.append("<font color=\"red\">已提交</font>");
						}
						
					}
					
					if (j != 9&&j != 10) {
						html.append(">");
						html.append(rs[j]);
					}

					html.append("</td>");
				}

				html.append("</tr>");
			}
		}

		return html.toString();
	}

	/**
	 * 显示本用户岗位切换模式窗口
	 * 
	 * @author xucy
	 * @throws IOException
	 */
	public void showShgwDiv(String cflbdm, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		List<HashMap<String, String>> spgwList = dao.getSpgwList(cflbdm, user);

		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");

		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>审核岗位选择</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("岗位选择");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:96%' >");

		html.append(" <input type=\"hidden\" name=\"text_xmdm\" id=\"text_xmdm\" value=\""
						+ cflbdm + "\" /> ");
		for (int i = 0; i < spgwList.size(); i++) {

			HashMap<String, String> spgwMap = spgwList.get(i);
			html.append(" <input type=\"radio\" name=\"spgws\" ");
			if (i == 0) {
				html.append("  checked=\"true\" ");
			}
			html.append(" id=\"spgw_" + i + "\" value=\""
					+ spgwMap.get("spgw") + "\">");
			html.append(spgwMap.get("gwmc"));
			html.append("<br/>");
		}

		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");

		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td  colspan=\"2\">");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  id=\"btn_bc\" onclick=\"checkSpgw();return false;\">");
		html.append("确 定");
		html.append("</button>");

		html.append("<button type=\"button\"  id=\"btn_gb\" onclick=\"closeWindown();return false;\">");
		html.append("关 闭");
		html.append("</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");

		html.append("</table>");
		html.append("</div>");

		response.getWriter().print(html.toString());
	}

	/**
	 * 构建标题
	 * 
	 * @author xucy
	 * 
	 */
	public List<HashMap<String, String>> getWjcfCfshTop(WjcfCfshModel model,
			User user) {
		String[] en = new String[] { "pk", "xn", "xq", "xh", "xm", "cflb",
				"cfyy",  "cfsj", "sbr", "shjg","tjzt" };
		String[] cn = new String[] { "", "学年", "学期", "学号", "姓名", "处分类别",
				"处分原因","处分时间", "上报人", "审核结果","提交状态" };
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);
		return topTr;
	}


	/**
	 * 查询违纪处分审核列表
	 * 
	 * @author xucy
	 * 
	 */
	public ArrayList<String[]> getWjcfCfshList(WjcfGeneralForm myForm,
			WjcfCfshModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getWjcfList(myForm, model, user);
	}

	
	/**
	 * 查询一条记录
	 * 
	 * @author xucy
	 * 
	 */
	public HashMap<String, String> getCfsb(String cfid) throws Exception {
		return dao.getCfsb(cfid);
	}

	/**
	 * 查询学生已受违纪处分列表
	 * 
	 * @param form
	 * @return
	 *
	 *  
	 */
	public List<HashMap<String, String>> getYscfqk(String xh) {
		return dao.getYscfqk(xh);
	}

	/**
	 * 查询违纪处分审核列表
	 * 
	 * @param form
	 * @return
	 * 
	 */
	public List<HashMap<String, String>> getCfsh(String cfid) {
		return dao.getCfsh(cfid);
	}

	/**
	 * 查询违纪处分审核列表
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCfshxx(String cfid, String spgw) {
		return dao.getCfshxx(cfid, spgw);
	}

	/**
	 * 查询处分类别
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCflbList(WjcfCfshModel model,
			User user) {
		return dao.getCflbList(model, user);
	}

	/**
	 * 
	 * 根据下级岗位获取上级审批岗位
	 * 
	 * @author xucy
	 */
	public HashMap<String, String> getHigherUpSpMap(WjcfGeneralForm model,
			User user) {
		return dao.getHigherUpSpMap(model, user);
	}

	/**
	 * 
	 * 判断是否可修改
	 * 
	 * @author xucy
	 */
	public boolean sfKyxg(WjcfGeneralForm model, User user) {
		return dao.sfKyxg(model, user);
	}

	/**
	 * 
	 * 获取最高级岗位
	 * 
	 * @author xucy
	 */
	public HashMap<String, String> getMaxSpgw(WjcfGeneralForm model, User user)
			throws Exception {
		return dao.getMaxSpgw(model, user);
	}

	/**
	 * 
	 * 获取一条审核记录
	 * 
	 * @author xucy
	 */
	public HashMap<String, String> getOnesCfsh(String cfid, String spgw)
			throws Exception {
		return dao.getOnesCfsh(cfid, spgw);
	}


	/**
	 * 
	 * 审核(单个多个皆可)
	 * 
	 * @author xucy
	 */
	public boolean saveCfsh(WjcfGeneralForm form, User user) throws Exception {
		String shzt = form.getShzt();
		String pk[] = form.getCfId().split(",");

		boolean flag = dao.plXgDownshzt(pk, form, user);// 退回时修改下一级状态

		flag = dao.plXgshzt(pk, form, user);// 修改审核状态

		if ("th".equalsIgnoreCase(shzt) && flag) {// 退回时修改上一级状态

			flag = dao.plXgUpshzt(pk, form, user);
		}

		if (flag) {
			flag = dao.plXgshjg(pk, form, user);
		}
		return flag;
	}

	/**
	 * 
	 * 统计审核
	 * 
	 * @author xucy
	 */
	public List<HashMap<String, String>> tongjiList() {
		return dao.tongjiList();
	}

	/**
	 * 
	 *提交审核
	 * 
	 * @author xucy
	 */
	public boolean tjSh() throws Exception {

		boolean flag = dao.tjSh();// 提交审核通过的数据进入正式表

		if (flag) {// 修改审核是否提交状态
			flag = dao.xgSftj();
		}
		return flag;
	}


	/**
	 * 
	 *提交审核(直接提交审核)
	 * 
	 * @author xucy
	 */
	public boolean zjtjSh(WjcfGeneralForm form) throws Exception {
		String pks = form.getCfId();
		boolean flag  = false;
		if(null!=pks){
			String pk[] = pks.split(",");
			 flag = dao.tjShsj(pk);// 提交审核通过的数据进入正式表
			if (flag) {// 修改审核是否提交状态
				flag = dao.xgTjzt(pk);
			}
		}
		return flag;
	}

	
	/**
	 * 
	 *判断是否最高级用户
	 * 
	 * @author xucy
	 */
	public boolean isZgjyh(User user) throws Exception{
		return dao.isZgjyh(user);
	}
}
