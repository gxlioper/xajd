package xsgzgl.pjpy.szgyyq.mypj.pjxm;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;

public class FiveSService extends CommService{

	FiveSDAO dao=new FiveSDAO();
	
	
	/**
	 * 获得申请信息列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getFiveSList(PjpyStuForm model) {
		return dao.getFiveSList(model);
	}
	
	
	/**
	 * 获得社会实践列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getFiveSList(PjpyStuForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getFiveSList(model, user);
	}
	
	/**
	 * 获得特殊Html
	 * 
	 * @author qlj
	 */
	public String getFiveSHtml(SearchRsModel rsModel, PjpyStuForm model,
			ArrayList<String[]> rsArrList, User user) {

		// 操作项目
//		String czxm = model.getCzxm();
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
				}else if ("bzr".equalsIgnoreCase(yhlx)
						|| "xy".equalsIgnoreCase(yhlx)
						|| "xx".equalsIgnoreCase(yhlx)) {
					flag = true;
				}

				spHtml
						.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				for (int j = 0; j < rs.length - 6; j++) {
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

				// 操作
				String cz = rs[rs.length - 1];
				// 投诉内容
				String tsnr = rs[rs.length - 2];
				// 处理意见
				String clyj = rs[rs.length - 3];
				// 处理人姓名
				String clrxm = rs[rs.length - 4];

				spHtml
						.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%;background-color:"
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
					if ("bz".equalsIgnoreCase(yhlx)) {
						spHtml.append("不可查看");
					} else {
						spHtml.append("<a href\"#\" onclick=\"showXstsDiv('"
								+ cz + "','" + xh + "','" + tsnr + "','"
								+ clrxm + "','" + clyj
								+ "');return false;\" style=\"cursor:hand\">");
						spHtml.append("<font color=\"blue\">" + cz + "</font>");
						spHtml.append("</a>");
					}
				}
				spHtml.append("</td>");

				spHtml.append("</tr>");
			}
		}

		return spHtml.toString();
	}
}
