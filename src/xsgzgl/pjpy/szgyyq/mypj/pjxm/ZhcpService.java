package xsgzgl.pjpy.szgyyq.mypj.pjxm;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;

public class ZhcpService extends CommService{


	ZhcpDAO dao=new ZhcpDAO();

	
	/**
	 * 获得申请信息列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getZhcpList(PjpyStuForm model) {
		
		return dao.getZhcpList(model);
	}
	
	/**
	 * 获得语言表达信息列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getZhcpList(PjpyStuForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getZhcpList(model, user);
	}
	
	/**
	 * 获得特殊Html
	 * 
	 * @author 伟大的骆
	 */
	public String getZhcpHtml(SearchRsModel rsModel, PjpyStuForm model,
			ArrayList<String[]> rsArrList, User user) {

		// 操作项目
		String czxm = model.getCzxm();
		// IE版本
		String ie = rsModel.getIe();
		// 学年
		String xn = model.getXn();
		// 学期
		String xq = model.getXq();
		// 项目
		String xmdm = model.getXmdm();
		// 用户类型
		String yhlx = model.getYhlx();

		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				String xh = rs[0];

				String userName = user.getUserName();

				// 本人颜色
				String color = "";
				// 是否本人
				boolean flag = false;
				if (xh.equalsIgnoreCase(userName)) {
					flag = true;
					color = "pink";
				} else if ("bzr".equalsIgnoreCase(yhlx)
						|| "xy".equalsIgnoreCase(yhlx)
						|| "xx".equalsIgnoreCase(yhlx)) {
					flag = true;
				}

				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				for (int j = 0; j < rs.length -1; j++) {
					spHtml
							.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%;background-color:"
									+ color + "\" ");

					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						spHtml.append("height=\"28.5px\"");
					} else {
						spHtml.append("height=\"29px\"");
					}
					spHtml.append(">");

					spHtml.append(rs[j]);
					spHtml.append("</td>");
				}

		

				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%;background-color:"
								+ color + "\" > ");

				if (flag) {
					spHtml
							.append("<a href\"#\" onclick=\"showSqxxDetail('"
									+ xn
									+ "','"
									+ xq
									+ "','"
									+ xh
									+ "','"
									+ xmdm
									+ "','view');return false;\" style=\"cursor:hand\">");
					spHtml.append("<font color=\"blue\">查看</font>");
					spHtml.append("</a>");
				} else {
						spHtml.append("不可查看");
				}
				spHtml.append("</td>");

				spHtml.append("</tr>");
			}
		}

		return spHtml.toString();
	}
}
