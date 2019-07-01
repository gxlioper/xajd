package xsgzgl.pjpy.szgyyq.mypj.pjxm;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.szgyyq.model.ZznlModel;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖(学生)_评奖项目_组织能力_Service类
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

public class ZznlService extends CommService {

	ZznlDAO dao = new ZznlDAO();

	/**
	 * 保存组织能力申请分
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveZznlSqf(PjpyStuForm model, User user) {

		ZznlModel zznlModel = model.getZznlModel();

		// 学号
		String xh = zznlModel.getXh();
		// 学年
		String xn = zznlModel.getXn();
		// 学期
		String xq = zznlModel.getXq();
		// 活动主题
		String[] hdzt = zznlModel.getHdzt();
		// 活动日期
		String[] hdrq = zznlModel.getHdrq();
		// 活动等级
		String[] hddj = zznlModel.getHddj();
		// 申请分
		String[] sqf = zznlModel.getSqf();

		String[] pkValue = null;

		// 构造参数
		List<String[]> params = new ArrayList<String[]>();
		if (hdzt != null && hdzt.length > 0) {

			pkValue = new String[hdzt.length];

			for (int i = 0; i < hdzt.length; i++) {

				hddj[i] = Base.isNull(hddj[i].trim()) ? ""
						: unicode2Gbk(hddj[i]);

				String[] values = new String[] { xn, xq, xh,
						unicode2Gbk(hdzt[i]), hdrq[i], unicode2Gbk(hddj[i]),
						sqf[i] };

				params.add(values);
				pkValue[i] = xn + xq + xh + unicode2Gbk(hdzt[i]) + hdrq[i];
			}
		}

		boolean flag = dao.delZznlSqf(model, pkValue, user);

		if (flag) {
			flag = dao.saveZznlSqf(model, params, user);
		}

		return flag;
	}

	/**
	 * 删除组织能力申请分
	 * 
	 * @author 伟大的骆
	 */
	public Boolean delZznlSqf(PjpyStuForm model, User user) {

		ZznlModel zznlModel = model.getZznlModel();

		// 学号
		String xh = zznlModel.getXh();
		// 学年
		String xn = zznlModel.getXn();
		// 学期
		String xq = zznlModel.getXq();
		// 活动主题
		String[] hdzt = zznlModel.getHdzt();
		// 活动日期
		String[] hdrq = zznlModel.getHdrq();

		String pkValue = xn + xq + xh + unicode2Gbk(hdzt[0]) + hdrq[0];

		boolean flag = dao.delZznlSqf(model, new String[] { pkValue }, user);

		return flag;
	}
	
	/**
	 * 获得申请信息列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getZznlList(PjpyStuForm model) {
		return dao.getZznlList(model);
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
	public ArrayList<String[]> getZznlList(PjpyStuForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getZznlList(model, user);
	}
	
	/**
	 * 获得特殊Html
	 * 
	 * @author qlj
	 */
	public String getZznlHtml(SearchRsModel rsModel, PjpyStuForm model,
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
	 * 获得组织能力信息
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getZznlInfo(PjpyStuForm model,
			User user) {

		String sqid = model.getSqid();

		String[] colList = new String[] { "hdzt", "hdrq", "hddj", "sqf" };
		HashMap<String, String> map = CommonQueryDAO.commonQueryOne(
				"szyc_zznlfzb", colList, "id", sqid);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		list.add(map);

		return list;
	}
	
	/**
	 * 修改组织能力信息
	 * 
	 * @author 伟大的骆
	 */
	public Boolean editZznlInfo(PjpyStuForm model, User user) {
		
		boolean flag = false;
		
		try {
			flag = dao.editZznlInfo(model, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		return flag;
	}
}
