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
import xsgzgl.pjpy.szgyyq.model.IvtltModel;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖(学生)_评奖项目_Ivt论坛_Service类
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

public class IvtltService extends CommService {

	IvtltDAO dao = new IvtltDAO();

	/**
	 * 保存Ivt论坛申请分
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveIvtltSqf(PjpyStuForm model, User user) {

		IvtltModel ivtltModel = model.getIvtltModel();

		// 学号
		String xh = ivtltModel.getXh();
		// 学年
		String xn = ivtltModel.getXn();
		// 学期
		String xq = ivtltModel.getXq();
		// 讲座题目
		String[] jztm = ivtltModel.getJztm();
		// 日期
		String[] xthdrq = ivtltModel.getXthdrq();
		// 进场登记
		String[] jcdj = ivtltModel.getJcdj();
		// 出场登记
		String[] ccdj = ivtltModel.getCcdj();
		// 申请分
		String[] sqf = ivtltModel.getSqf();

		String[] pkValue = null;

		// 构造参数
		List<String[]> params = new ArrayList<String[]>();
		if (jztm != null && jztm.length > 0) {

			pkValue = new String[jztm.length];

			for (int i = 0; i < jztm.length; i++) {

				jcdj[i] = Base.isNull(jcdj[i]) ? "" : unicode2Gbk(jcdj[i]);
				ccdj[i] = Base.isNull(ccdj[i]) ? "" : unicode2Gbk(ccdj[i]);

				String[] values = new String[] { xn, xq, xh,
						unicode2Gbk(jztm[i]), xthdrq[i], jcdj[i], ccdj[i],
						sqf[i] };

				params.add(values);
				pkValue[i] = xn + xq + xh + unicode2Gbk(jztm[i]) + xthdrq[i];
			}
		}

		boolean flag = dao.delIvtltSqf(model, pkValue, user);

		if (flag) {
			flag = dao.saveIvtltSqf(model, params, user);
		}

		return flag;
	}

	/**
	 * 删除Ivt论坛申请分
	 * 
	 * @author 伟大的骆
	 */
	public Boolean delIvtltSqf(PjpyStuForm model, User user) {

		IvtltModel ivtltModel = model.getIvtltModel();

		// 学号
		String xh = ivtltModel.getXh();
		// 学年
		String xn = ivtltModel.getXn();
		// 学期
		String xq = ivtltModel.getXq();
		// 讲座题目
		String[] jztm = ivtltModel.getJztm();
		// 日期
		String[] xthdrq = ivtltModel.getXthdrq();

		String pkValue = xn + xq + xh + unicode2Gbk(jztm[0]) + xthdrq[0];

		boolean flag = dao.delIvtltSqf(model, new String[] { pkValue }, user);

		return flag;
	}
	
	/**
	 * 获得申请信息列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getIvtltList(PjpyStuForm model) {
		return dao.getIvtltList(model);
	}
	
	/**
	 * 获得IVTLT论坛息列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getIvtltList(PjpyStuForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getIvtltList(model, user);
	}
	
	/**
	 * 获得特殊Html
	 * 
	 * @author 伟大的骆
	 */
	public String getIvtltHtml(SearchRsModel rsModel, PjpyStuForm model,
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
	 * 获得Ivt论坛信息
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getIvtltInfo(PjpyStuForm model,
			User user) {

		String sqid = model.getSqid();

		String[] colList = new String[] { "jztm", "xthdrq", "jcdj", "ccdj",
				"sqf" };
		HashMap<String, String> map = CommonQueryDAO.commonQueryOne(
				"szyq_ivtltb", colList, "id", sqid);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		list.add(map);

		return list;
	}
	
	/**
	 * 修改Ivt论坛信息
	 * 
	 * @author 伟大的骆
	 */
	public Boolean editIvtltInfo(PjpyStuForm model, User user) {
		
		boolean flag = false;
		
		try {
			flag = dao.editIvtltInfo(model, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		return flag;
	}
}
