package xsgzgl.pjpy.szgyyq.mypj.pjxm;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.szgyyq.model.ShsjModel;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖(学生)_评奖项目_社会实践_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class ShsjService extends CommService {

	ShsjDAO dao = new ShsjDAO();
	
	/**
	 * 保存社会实践申请分
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveShsjSqf(PjpyStuForm model, User user) {

		ShsjModel shsjModel = model.getShsjModel();

		// 学号
		String xh = shsjModel.getXh();
		// 学年
		String xn = shsjModel.getXn();
		// 学期
		String xq = shsjModel.getXq();
		// 活动地点
		String[] hddd = shsjModel.getHddd();
		// 活动日期
		String[] hdrq = shsjModel.getHdrq();
		// 活动内容
		String[] hdnr = shsjModel.getHdnr();
		// 活动时间
		String[] hdsj = shsjModel.getHdsj();
		// 申请分
		String[] sqf = shsjModel.getSqf();

		String[] pkValue = null;

		// 构造参数
		List<String[]> params = new ArrayList<String[]>();
		if (hddd != null && hddd.length > 0) {

			pkValue = new String[hddd.length];

			for (int i = 0; i < hddd.length; i++) {

				String[] values = new String[] { xn, xq, xh,
						unicode2Gbk(hddd[i]), hdrq[i], unicode2Gbk(hdnr[i]),
						hdsj[i].trim(), sqf[i] };

				params.add(values);
				pkValue[i] = xn + xq + xh + unicode2Gbk(hddd[i]) + hdrq[i];
			}
		}

		boolean flag = dao.delShsjSqf(model, pkValue, user);

		if (flag) {
			flag = dao.saveShsjSqf(model, params, user);
		}

		return flag;
	}

	/**
	 * 删除社会实践申请分
	 * 
	 * @author 伟大的骆
	 */
	public Boolean delShsjSqf(PjpyStuForm model, User user) {

		ShsjModel shsjModel = model.getShsjModel();

		// 学号
		String xh = shsjModel.getXh();
		// 学年
		String xn = shsjModel.getXn();
		// 学期
		String xq = shsjModel.getXq();
		// 讲座题目
		String[] hddd = shsjModel.getHddd();
		// 日期
		String[] hdrq = shsjModel.getHdrq();

		String pkValue = xn + xq + xh + unicode2Gbk(hddd[0]) + hdrq[0];

		boolean flag = dao.delShsjSqf(model, new String[] { pkValue }, user);

		return flag;
	}
	
	/**
	 * 获得申请信息列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getShsjList(PjpyStuForm model) {
		return dao.getShsjList(model);
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
	public ArrayList<String[]> getShsjList(PjpyStuForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getShsjList(model, user);
	}
	
	/**
	 * 获得特殊Html
	 * 
	 * @author qlj
	 */
	public String getShsjHtml(SearchRsModel rsModel, PjpyStuForm model,
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
	
	/**
	 * 获得社会实践信息
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getShsjInfo(PjpyStuForm model,
			User user) {

		String sqid = model.getSqid();

		String[] colList = new String[] { "hddd", "hdrq", "hdnr", "hdsj", "sqf" };
		HashMap<String, String> map = CommonQueryDAO.commonQueryOne(
				"szyc_shsjfzb", colList, "id", sqid);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		list.add(map);

		return list;
	}
	
	/**
	 * 修改社会实践信息
	 * 
	 * @author 伟大的骆
	 */
	public Boolean editShsjInfo(PjpyStuForm model, User user) {
		
		boolean flag = false;
		
		try {
			flag = dao.editShsjInfo(model, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		return flag;
	}
}
