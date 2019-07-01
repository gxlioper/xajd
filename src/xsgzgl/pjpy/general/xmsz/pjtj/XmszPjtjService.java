package xsgzgl.pjpy.general.xmsz.pjtj;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.tjsz.PjpyTjszMethod;
import xgxt.utils.String.StringUtils;
import xsgzgl.pjpy.general.inter.xmsz.XmszPjtjInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目设置_评奖条件_通用_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XmszPjtjService extends CommService implements XmszPjtjInterface {

	XmszPjtjDAO dao = new XmszPjtjDAO();

	//-----------------------评奖条件设置 begin-------------------------
	
	/**
	 * 初始化评奖条件设置
	 * 
	 * @author 伟大的骆
	 */
	public void defaultPjtjSetting(XmszPjtjModel model, User user,
			HttpServletResponse response) throws IOException {
		
		// 项目代码
		String xmdm = model.getXmdm();
		// 条件列表
		List<HashMap<String, String>> list = dao.getPjtjList(xmdm);
		
		response.setContentType("text/html;charset=gbk");
		
		StringBuilder html = new StringBuilder();
		
		// 评奖条件列表
		List<HashMap<String, String>> pjtjList = model.getPjtjList();

		// 班级大类列表
		List<HashMap<String, String>> bjdlList = model.getBjdlList();
			
		if (list != null && list.size() > 0) {
			
			html.append("<input type=\"hidden\" id=\"hidden_num\" value=\""+list.size()+"\"/>");
			
			for (int i = 0; i < list.size(); i++) {
				
				String tjdm = list.get(i).get("tjdm");// 条件代码
				String tjz = list.get(i).get("tjz");// 条件值
				String tjfw = list.get(i).get("tjfw");// 条件范围
				String pjtjz = list.get(i).get("pjtjz");// 评奖条件值
				String gx = list.get(i).get("gx");// 关系
				String tsgs = list.get(i).get("tsgs");// 特殊格式
				
				html.append("<div id=\"div_tjsz_" + i + "\">");
				html.append("<table width=\"100%\" border=\"0\">");
				html.append("<tr>");
				
				html.append("<td width=\"5%\">");//checkBox
				html.append("<input type=\"checkbox\" name=\"checkVal\" value=\""+i+"\"/>");
				html.append("</td>");

				html.append("<td width=\"40%\">");//条件
				html.append("");
				html.append("<select name=\"array_tjdm_sz\" ");
				html.append("onchange=\"defaultPjtjInfo(this.value,'"+i+"')\" ");
				html.append("id=\"select_tjdm_"+i+"\">");
				if (pjtjList != null && pjtjList.size() > 0) {
					for (int j = 0; j < pjtjList.size(); j++) {
						html.append("<option ");
						html.append("value=\"" + pjtjList.get(j).get("tjdm")+ "\" ");
						if(tjdm.equalsIgnoreCase(pjtjList.get(j).get("tjdm"))){
							html.append("selected=\"selected\"");
						}
						html.append(">");
						html.append(pjtjList.get(j).get("tjmc"));
						html.append("</option>");
					}
				}
				html.append("</select>");
				html.append("</td>");

				html.append("<td width=\"10%\">");//关系
				html.append("<select name=\"array_gx_sz\" id=\"select_gx_"+i+"\">");
				
				if(!Base.isNull(pjtjz)){
					html.append("<option value=\"=\">=<option>");
				}else{
					html.append("<option value=\">\" ");
					if(">".equalsIgnoreCase(gx)){
						html.append("selected=\"selected\"");
					}
					html.append(">></option>");
					
					html.append("<option value=\">=\" ");
					if(">=".equalsIgnoreCase(gx)){
						html.append("selected=\"selected\"");
					}
					html.append(">>=</option>");
					
					html.append("<option value=\"=\" ");
					if("=".equalsIgnoreCase(gx)){
						html.append("selected=\"selected\"");
					}
					html.append(">=</option>");
					
					html.append("<option value=\"<=\" ");
					if("<=".equalsIgnoreCase(gx)){
						html.append("selected=\"selected\"");
					}
					html.append("><=</option>");
					
					html.append("<option value=\"<\" ");
					if("<".equalsIgnoreCase(gx)){
						html.append("selected=\"selected\"");
					}
					html.append("><</option>");
				}
				html.append("</select>");
				html.append("</td>");

				html.append("<td width=\"10%\">");//条件值
				html.append("<input type=\"text\" name=\"array_tjz_sz\" ");	
				html.append("onkeydown=\"return onlyNum(this,5)\" ");
				html.append("onmousedown=\"return onlyNum(this,5)\" ");
				html.append("maxlength=\"5\"");
				html.append("style=\"width:40px;ime-mode:disabled\" ");
				html.append("value=\""+tjz+"\" ");
				html.append("id=\"input_tjz_"+i+"\" maxLength=\"10\"/>");
				if(Base.isNull(tsgs)){
					html.append("<span id=\"span_tsgs_"+i+"\"></span>");
				}else{
					html.append("<span id=\"span_tsgs_"+i+"\">"+tsgs+"</span>");
				}
				html.append("</td>");

				html.append("<td width=\"\">");//启用范围级别
				html.append("<select name=\"array_tjfw_sz\" id=\"select_tjfw_"+i+"\">");
				html.append("<option value=\"all\" ");
				if("all".equalsIgnoreCase(tjfw)){
					html.append("selected=\"selected\" ");
				}
				html.append(">全体</option>");
				if (bjdlList != null && bjdlList.size() > 0) {
					for (int j = 0; j < bjdlList.size(); j++) {
						html.append("<option ");
						html.append("value=\"" + bjdlList.get(j).get("dm")+ "\" ");
						if(tjfw.equalsIgnoreCase(bjdlList.get(j).get("dm"))){
							html.append("selected=\"selected\"");
						}
						html.append(">");
						html.append(bjdlList.get(j).get("mc"));
						html.append("</option>");
					}
				}
				html.append("</select>");
				html.append("</td>");
				
				html.append("</tr>");
				html.append("</table>");
				html.append("</div>");
			}
		}
		
		response.getWriter().print(html.toString());
	}
	
	/**
	 * 获得评奖条件信息
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getPjtjInfo(XmszPjtjModel model, User user) {

		//条件代码
		String tjdm = model.getTjdm();
		
		HashMap<String, String> map = dao.getPjtjInfo(tjdm);

		return map;
	}
	
	/**
	 * 获得评奖条件
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getPjtjList() {

		List<HashMap<String, String>> list = dao.getPjtjList();

		return list;
	}
	
	/**
	 * 删除评奖条件
	 * 
	 * @author 伟大的骆
	 */
	public Boolean deletePjtj(XmszPjtjModel model, User user) {

		boolean flag = false;

		// 项目代码
		String xmdm = model.getXmdm();

		try {
			flag = dao.deleteTjszb(xmdm, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 保存评奖条件
	 * 
	 * @author 伟大的骆
	 */
	public Boolean savePjtj(XmszPjtjModel model, User user) {

		boolean flag = false;
		// 项目代码
		String xmdm = model.getXmdm();
		// 项目代码
		String[] tjdm = model.getTjdm_sz();
		// 项目代码
		String[] tjfw =  model.getTjfw_sz();
		// 项目代码
		String[] gx = model.getGx_sz();
		// 项目代码
		String[] tjz = model.getTjz_sz();

		flag = deletePjtj(model, user);

		if (flag) {
			try {
				flag = dao.insertTjszb(xmdm, tjdm, tjfw, gx, tjz, user);
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	//-----------------------评奖条件设置 end-------------------------
	
	/**
	 * 获得评奖条件设置
	 * 
	 * @author 伟大的骆
	 */
	public String getPjtjMessage(XmszPjtjModel model, User user) {

		// 项目代码
		String xmdm = model.getXmdm();
		// 项目名称
		String xmmc = model.getXmmc();
		// 学号
		String[] xh = model.getXh();

		// 评奖条件列表
		List<HashMap<String, String>> pjtjList = dao.getPjtjList(xmdm);

		String message = "";

		if (pjtjList != null && pjtjList.size() > 0) {
			for (int i = 0; i < pjtjList.size(); i++) {
				HashMap<String, String> map = pjtjList.get(i);
				message = getStuPjzg(xh, map);

				if (!Base.isNull(message)) {
					break;
				}
			}
		}

		return message;
	}

	/**
	 * 获得学生评奖资格
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String getStuPjzg(String[] xh, Map<String, String> map) {

		// 条件类型
		String tjlx = map.get("tjlx");
		// 条件说明
		String tjms = map.get("tjms");
		
		// 无资格评奖学号
		String noPjzgXh = "";

		if ("logicRelation".equalsIgnoreCase(tjlx)) {// 逻辑关系
			noPjzgXh = judgeLogicRelation(xh, map);
		} else if ("logicReverse".equalsIgnoreCase(tjlx)) {// 反向逻辑关系
			noPjzgXh = judgeLogicReverse(xh, map);
		} else if ("minRelation".equalsIgnoreCase(tjlx)) {// 最小分关系
			noPjzgXh = judgeMinRelation(xh, map);
		} else if ("avgRelation".equalsIgnoreCase(tjlx)) {// 平均分关系
			noPjzgXh = judgeAvgRelation(xh, map);
		} else if ("instanceReverse".equalsIgnoreCase(tjlx)) {// 某情况关系
			noPjzgXh = judgeInstanceReverse(xh, map);
		} else {// 调用方法单独处理
			try {
				PjpyTjszMethod pjpyTjszMethod = new PjpyTjszMethod();
				Class myClass = pjpyTjszMethod.getClass();
				// map.put("xh", xh);
				noPjzgXh = (String) myClass.getMethod(tjlx,
						(Class[]) new Class[] { HashMap.class }).invoke(
						pjpyTjszMethod, map);
			} catch (Exception e) {
				System.out.println("评奖条件设置,method:" + tjlx + "遇到问题;");
				e.printStackTrace();
			}
		}

		// 提示信息
		String message = "";
		
		// 不满足条件的话
		if (!Base.isNull(noPjzgXh)) {
			DAO dao = DAO.getInstance();
			String xm = dao.getOneValue("view_xsjbxx", "xm", "xh", noPjzgXh);
			noPjzgXh = "申请该项目需要：" + tjms + "," + xm + "(" + noPjzgXh
					+ ")不满足申请条件！";
		}
		
		return message;
	}

	/**
	 * 判断逻辑关系
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeLogicRelation(String[] xh, Map<String, String> map) {

		String message = "";

		// 条件说明
		String tjms = map.get("tjms");
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// 比较值
		List<HashMap<String, String>> bjzList = dao.getBjz(xh, map, "");
		String noPjzgXh = compareTo(xh, bjzList, tjz, gx, true);

		return message;
	}

	/**
	 * 判断反向逻辑关系
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeLogicReverse(String[] xh, Map<String, String> map) {

		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// 比较值
		List<HashMap<String, String>> bjzList = dao.getBjz(xh, map, "");

		String noPjzgXh = compareTo(xh, bjzList, tjz, gx, false);
		
		return noPjzgXh;

	}
	
	/**
	 * 判断小值关系
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeMinRelation(String[] xh, Map<String, String> map) {	

		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;
		
		// 比较值
		List<HashMap<String, String>> bjzList = dao.getBjz(xh, map, "Min");

		String noPjzgXh  = compareTo(xh, bjzList, tjz, gx, true);
		
		return noPjzgXh;
	}
	
	/**
	 * 判断平均值关系
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeAvgRelation(String[] xh, Map<String, String> map) {

		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// 比较值
		List<HashMap<String, String>> bjzList = dao.getBjz(xh, map, "AVG");

		String noPjzgXh = compareTo(xh, bjzList, tjz, gx, true);

		return noPjzgXh;
	}
	
	/**
	 * 判断象关系
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	private String judgeInstanceReverse(String[] xh, Map<String, String> map) {

		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// 比较值
		List<HashMap<String, String>> bjzList = dao.getBjz(xh, map, "count");

		String noPjzgXh  = compareTo(xh, bjzList, tjz, gx, true);
		
		return noPjzgXh;
	}
	
	/**
	 * 目标值和确定值比较
	 * 
	 * @param bjz
	 * @param tjz
	 * @param gx
	 * @return
	 */
	private String compareTo(String[] xh,
			List<HashMap<String, String>> bjzList, String tjz, String gx,
			boolean relation) {

		boolean flag = false;

		// 无资格学号
		String noPjzgXh = "";

		for (int i = 0; i < bjzList.size(); i++) {

			HashMap<String, String> bjzMap = bjzList.get(i);
			String bjz = bjzMap.get("bjz");

			// 比较值和条件值非空
			if (StringUtils.isNotNull(bjz) && StringUtils.isNotNull(tjz)) {
				// 关系为"="
				if ("=".equalsIgnoreCase(gx)) {
					if("是".equalsIgnoreCase(tjz) && !"0".equalsIgnoreCase(bjz)){
						bjz = "是";
					}
					flag = bjz.equalsIgnoreCase(tjz) ? true : false;
				}
				// 关系为">"
				else if (">".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) > 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) > 0 ? true : false;
					}
				}
				// 关系为">="
				else if (">=".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) > 0
								|| bbjz.compareTo(btjz) == 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) > 0
								|| btjz.compareTo(bbjz) == 0 ? true : false;
					}
				}
				// 关系为"<"
				else if ("<".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) < 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) < 0 ? true : false;
					}

				}
				// 关系为"<="
				else if ("<=".equalsIgnoreCase(gx)) {

					BigDecimal bbjz = new BigDecimal(bjz);
					BigDecimal btjz = new BigDecimal(tjz);

					if (relation) {
						flag = bbjz.compareTo(btjz) < 0
								|| bbjz.compareTo(btjz) == 0 ? true : false;
					} else {
						flag = btjz.compareTo(bbjz) < 0
								|| btjz.compareTo(bbjz) == 0 ? true : false;
					}

				}
			}

			if (!flag) {
				noPjzgXh = bjzMap.get("xh");
				break;
			}
		}

		return noPjzgXh;
	}

}
