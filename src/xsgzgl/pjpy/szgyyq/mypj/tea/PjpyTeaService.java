package xsgzgl.pjpy.szgyyq.mypj.tea;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.pjlc.xmsh.PjpyXmshForm;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrForm;
import xgxt.utils.ExcelMethods;
import xgxt.utils.Pages;
import xsgzgl.pjpy.szgyyq.model.DshdModel;
import xsgzgl.pjpy.szgyyq.model.IvtltModel;
import xsgzgl.pjpy.szgyyq.model.ShsjModel;
import xsgzgl.pjpy.szgyyq.model.WthdModel;
import xsgzgl.pjpy.szgyyq.model.XsssModel;
import xsgzgl.pjpy.szgyyq.model.XstsModel;
import xsgzgl.pjpy.szgyyq.model.YybdModel;
import xsgzgl.pjpy.szgyyq.model.ZznlModel;
import xsgzgl.pjpy.szgyyq.mypj.PjpyMypjForm;
import xsgzgl.pjpy.szgyyq.model.FivesModel;
import xsgzgl.pjpy.szgyyq.mypj.PjpyMypjService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.DshdService;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuService;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖(老师)_Service类
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

public class PjpyTeaService extends PjpyMypjService {
	
	PjpyTeaDAO dao = new PjpyTeaDAO();
	
	/**
	 * 获得5S分信息列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getFivesInfoList(PjpyTeaForm model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// 班级学生
		ArrayList<String[]> xsList = dao.getBjxsList(model, user);

		// 5S分情况
		List<HashMap<String, String>> fivesList = dao.getFivesList(model,
				xsList, user);
		
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		if (xsList != null && xsList.size() > 0) {
			for (int i = 0; i < xsList.size(); i++) {
				String[] xs = xsList.get(i);
				String xh = xs[0];//学号
				String xm = xs[1];//姓名
				float sqfs = 0;//申请分
				float xyf = 0;//学院审核分
				float xxf = 0;//学校审核分
				
				if (fivesList != null && fivesList.size() > 0) {
					for (int j = 0; j < fivesList.size(); j++) {
						HashMap<String, String> map = fivesList.get(j);
						
						// 已处理
						String ycl = map.get("ycl");
						// 5S学号
						String fives_xh = map.get("xh");
						
						if(!"yes".equalsIgnoreCase(ycl) && fives_xh.equalsIgnoreCase(xh)){
							
							String jjf = map.get("jjf");//加减分
							String sqf = map.get("sqf");//申请分
							String xyshf = map.get("xyshf");//学院审核分
							String xysh = map.get("xysh");//学院审核
							String xxshf = map.get("xxshf");//学校审核分
							String xxsh = map.get("xxsh");//学校审核
							
							//申请分处理
							if("加分".equalsIgnoreCase(jjf)){
								sqfs+=Float.parseFloat(sqf);
							}else{
								sqfs-=Float.parseFloat(sqf);
							}
							
							//学院分处理
							if("通过".equalsIgnoreCase(xysh)){
								if("加分".equalsIgnoreCase(jjf)){
									xyf+=Float.parseFloat(xyshf);
								}else{
									xyf-=Float.parseFloat(xyshf);
								}
							}
							
							// 学校分处理
							if("通过".equalsIgnoreCase(xxsh)){
								if("加分".equalsIgnoreCase(jjf)){
									xxf+=Float.parseFloat(xxshf);
								}else{
									xxf-=Float.parseFloat(xxshf);
								}
							}
							
						}else{
							continue;
						}
					}
				}
				
				String[] rs = new String[5];
				rs[0] = xh;
				rs[1] = xm;
				rs[2] = String.valueOf(sqfs);
				rs[3] = String.valueOf(xyf);
				rs[4] = String.valueOf(xxf);
				
				list.add(rs);
			}
		}
		
		return list;
	}
	
	/**
	 * 获得5S分维护Html
	 * 
	 * @author 伟大的骆
	 */
	public String getFivesHtml(SearchRsModel rsModel, PjpyTeaForm model,
			ArrayList<String[]> rsArrList,User user) {

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
		//用户类型
		String yhlx = model.getYhlx();
		
		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				
				String xh = rs[0];
				
				String userName = user.getUserName();
				
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				spHtml.append("<td align=\"center\" width=\"5px\">");
				spHtml.append("<input type=\"checkbox\" name=\"checkVal\" ");
				spHtml.append("value=\"" + rs[0] + "\"/>");
				spHtml.append("</td>");
				
				for (int j = 0; j < rs.length; j++) {
					
					spHtml.append("<td align=\"center\" nowrap=\"nowrap\" style=\"width:20%;\" ");
					
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
				
				spHtml.append("</tr>");
			}
		}

		return spHtml.toString();
	}
	
	/**
	 * 获得已申请的5S分列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getFivesList(PjpyTeaForm model) {
		return dao.getFivesList(model);
	}

	/**
	 * 获得加减分原因列表
	 * 
	 * @return list
	 * @author luojw
	 */
	public List<HashMap<String, String>> getYyList() {
		return dao.getYyList();
	}
	
	/**
	 * 保存5S分
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveFives(PjpyTeaForm model, User user,HttpServletRequest request){

		FivesModel fivesModel = model.getFivesModel();
		
		String[] id = fivesModel.getId();
		String[] fzxm = fivesModel.getFzxm();
		String[] jjf = fivesModel.getJjf();
		String[] sqf = fivesModel.getSqf();
		String[] jfrq = fivesModel.getJfrq();
		String[] yy = fivesModel.getYy();
		String[] jfyy = fivesModel.getJfyy();

		ArrayList<String> add_fzxmList = new ArrayList<String>();
		ArrayList<String> add_jjfList = new ArrayList<String>();
		ArrayList<String> add_sqfList = new ArrayList<String>();
		ArrayList<String> add_jfrqList = new ArrayList<String>();
		ArrayList<String> add_yyList = new ArrayList<String>();
		ArrayList<String> add_jfyyList = new ArrayList<String>();
		
		
		ArrayList<String> edit_idList = new ArrayList<String>();
		ArrayList<String> edit_sqfList = new ArrayList<String>();
		
		if (id != null && id.length > 0) {
			for (int i = 0; i < id.length; i++) {
				if("no".equalsIgnoreCase(id[i])){
					add_fzxmList.add(fzxm[i]);
					add_jjfList.add(unicode2Gbk(jjf[i]));
					add_sqfList.add(sqf[i]);
					add_jfrqList.add(jfrq[i]);
					add_yyList.add(yy[i]);
					add_jfyyList.add(unicode2Gbk(jfyy[i]));
				}else{
					edit_idList.add(id[i]);
					edit_sqfList.add(sqf[i]);
				}
			}
		}

		boolean flag = false;
		
		try {
			
			// 新增操作
			fivesModel.setFzxm(add_fzxmList.toArray(new String[] {}));
			fivesModel.setJjf(add_jjfList.toArray(new String[] {}));
			fivesModel.setSqf(add_sqfList.toArray(new String[] {}));
			fivesModel.setJfrq(add_jfrqList.toArray(new String[] {}));
			fivesModel.setYy(add_yyList.toArray(new String[] {}));
			fivesModel.setJfyy(add_jfyyList.toArray(new String[]{}));
			flag = addFives(fivesModel, user);
			
			// 修改操作
			fivesModel.setId(edit_idList.toArray(new String[] {}));
			fivesModel.setSqf(edit_sqfList.toArray(new String[] {}));
			flag = dao.editFives(fivesModel, user);
			
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 增加5S分
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean addFives(FivesModel fivesModel, User user){

		
		// 5S表
		String tableName = "szyc_5sb";
		// 主键
		String pk = "1";
		// 主键值
		String[] pkValue = new String[] { "2" };
		// 单一字段
		String[] onezd = new String[] { "xn", "xq", "xh" };
		// 批量字段
		String[] arrzd = new String[] { "fzxm", "jjf", "yy", "jfrq", "sqf","jfyy" };
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);

		boolean flag = false;
		
		try {
			flag = saveInfoToDb(saveForm, fivesModel, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 删除5S分
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean delFives(PjpyTeaForm model, User user) {

		FivesModel fivesModel = model.getFivesModel();

		// 5S表
		String tableName = "szyc_5sb";
		// 主键
		String pk = "id";

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(fivesModel.getId());

		boolean flag = false;

		try {
			flag = delInfoInDb(saveForm, model, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 获得项目列表(分数审核)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getFsshXmList(PjpyTeaForm model) {

		//用户类型
		String yhlx = model.getYhlx();
		
		List<HashMap<String, String>> xmList = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> map = new HashMap<String, String>();

		// 读书活动
		map = new HashMap<String, String>();
		map.put("xmdm", "szyq_dshdjzb");
		map.put("xmmc", "读书活动");
		xmList.add(map);

		// 语言表达
		map = new HashMap<String, String>();
		map.put("xmdm", "szyq_yybdjzb");
		map.put("xmmc", "语言表达");
		xmList.add(map);

		// IVT论坛
		map = new HashMap<String, String>();
		map.put("xmdm", "szyq_ivtltb");
		map.put("xmmc", "IVT论坛");
		xmList.add(map);

		// 文体活动
		map = new HashMap<String, String>();
		map.put("xmdm", "szyq_xthddjb");
		map.put("xmmc", "文体活动");
		xmList.add(map);

		// 组织活动
		map = new HashMap<String, String>();
		map.put("xmdm", "szyc_zznlfzb");
		map.put("xmmc", "组织能力");
		xmList.add(map);

		// 社会实践
		map = new HashMap<String, String>();
		map.put("xmdm", "szyc_shsjfzb");
		map.put("xmmc", "社会实践");
		xmList.add(map);

		if("xy".equalsIgnoreCase(yhlx) || "xx".equalsIgnoreCase(yhlx)){
			// 5S分
			map = new HashMap<String, String>();
			map.put("xmdm", "szyc_5sb");
			map.put("xmmc", "5S");
			xmList.add(map);
		}
		
		return xmList;
	}
	
	/**
	 * 获得各个项目的结果查询列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getFsshList(PjpyTeaForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		//项目代码
		String xmdm = model.getXmdm();
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		if ("szyq_dshdjzb".equalsIgnoreCase(xmdm)) {// 读书活动
			DshdService service = new DshdService();
			//list = service.getDshdShList(model, user);
			list=getCommShList(model, user, xmdm);
		} else if ("szyq_yybdjzb".equalsIgnoreCase(xmdm)) {// 语言表达  下面的几个可以合并在一起
			list=getCommShList(model, user, xmdm);
		} else if ("szyq_ivtltb".equalsIgnoreCase(xmdm)) {// IVT论坛
			list=getCommShList(model, user, xmdm);
		} else if ("szyq_xthddjb".equalsIgnoreCase(xmdm)) {// 文体活动
			list=getCommShList(model, user, xmdm);
		} else if ("szyc_zznlfzb".equalsIgnoreCase(xmdm)) {// 组织活动
			list=getCommShList(model, user, xmdm);
		} else if ("szyc_shsjfzb".equalsIgnoreCase(xmdm)) {// 社会实践
			list=getCommShList(model, user, xmdm);
		} else if ("szyc_5sb".equalsIgnoreCase(xmdm)) {// 5S
			list=getFiveSShList(model, user, xmdm);
		}


		return list;
	}
	
	/**
	 * 获得特殊Html
	 * 
	 * @author 伟大的骆
	 */
	public String getFsshHtml(SearchRsModel rsModel, PjpyTeaForm model,
			ArrayList<String[]> rsArrList,User user) {
		
		// IE版本
		String ie = rsModel.getIe();
		// 学年
		String xn = model.getXn();
		// 学期
		String xq = model.getXq();
		// 项目
		String xmdm = model.getXmdm();
		//用户类型
		String yhlx = model.getYhlx();
		
		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			int show_num = 4;

			if ("szyc_5sb".equalsIgnoreCase(xmdm)) {
				if ("xy".equalsIgnoreCase(yhlx)) {
					show_num = 4;
				}
			} else {
				if ("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)) {
					show_num = 0;
				}
			}
			
			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				
				String xh = rs[0];
				
				// -------------------2011.11.22 qlj判断无需审核时 禁用复选框 begin -------------------------
				String shzt="";
				if ("xx".equalsIgnoreCase(yhlx)) {

					shzt = rs[rs.length-show_num - 1];
						
				}else if ("szyc_5sb".equalsIgnoreCase(xmdm) && "xy".equalsIgnoreCase(yhlx)) {

					shzt = rs[rs.length -show_num- 1];
						
				}else if("xy".equalsIgnoreCase(yhlx)){
					
					shzt = rs[rs.length -show_num- 1];
				}else if(!"szyc_5sb".equalsIgnoreCase(xmdm) && "bzr".equalsIgnoreCase(yhlx)){
					
					shzt = rs[rs.length -show_num- 2];
				}
				// -------------------2011.11.22 qlj判断无需审核时 禁用复选框 end -------------------------
				
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				spHtml.append("<td align=\"left\" width=\"5px\">");
				spHtml.append("<input type=\"checkbox\" name=\"checkVal\" ");
				
				if("无需审核".equalsIgnoreCase(shzt)){
					spHtml.append(" disabled='true' ");
				}
				spHtml.append("value=\"" + xh + "\"/>");
				spHtml.append("</td>");
				
				for (int j = 0; j < rs.length - show_num; j++) {
					spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");

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
				
				
				if (!"szyc_5sb".equalsIgnoreCase(xmdm)) {

					if ("xy".equalsIgnoreCase(yhlx)
							|| "xx".equalsIgnoreCase(yhlx)) {

						// 被投诉人
						String btsr = rs[rs.length - 4];
						// 投诉人
						String tsr = rs[rs.length - 3];
						// 投诉内容
						String tsnr = rs[rs.length - 2];
						// 投诉时间
						String tssj = rs[rs.length - 1];
						
						

						spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\"> ");
						
						//无投诉
						if(Base.isNull(tsr)){
							spHtml.append("无");
						}else{
							
							spHtml.append("<a href=\"#\" onclick=\"showXstsDiv('"+tsr+"','"+btsr+"','"+tsnr+"','"+tssj+"');return false;\">");
							spHtml.append("<font color=\"blue\">");
							spHtml.append("有");
							spHtml.append("</font>");
							spHtml.append("</a>");
						}					
						spHtml.append("</td >");
					}
				} else {
					if ("xx".equalsIgnoreCase(yhlx)) {
						// 被投诉人
						String btsr = rs[rs.length - 4];
						// 投诉人
						String tsr = rs[rs.length - 3];
						// 投诉内容
						String tsnr = rs[rs.length - 2];
						// 投诉时间
						String tssj = rs[rs.length - 1];

						spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\"> ");
						
						//无投诉
						if(Base.isNull(tsr)){
							spHtml.append("无");
						}else{
							
							spHtml.append("<a href=\"#\" onclick=\"showXstsDiv('"+tsr+"','"+btsr+"','"+tsnr+"','"+tssj+"');return false;\">");
							spHtml.append("<font color=\"blue\">");
							spHtml.append("有");
							spHtml.append("</font>");
							spHtml.append("</a>");
						}					
						spHtml.append("</td >");
					}
				}
				
				spHtml.append("</tr>");
			}
		}
		return spHtml.toString();
	}
	
	/**
	 * 保存审核分
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveShf(PjpyTeaForm model, User user){
		return dao.saveShf(model, user);
	}
	
	/**
	 * 保存审核状态
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveShzt(PjpyTeaForm model, User user){
		return dao.saveShzt(model, user);
	}
	
	/**
	 * 保存批量审核状态
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean savePlShzt(PjpyTeaForm model, User user){
		return dao.savePlShzt(model, user);
	}
	
	/**
	 * 获得分数审核列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getFsshInfoList(PjpyTeaForm model,User user) {
		
		PjpyStuService stuService = new PjpyStuService();
		
		PjpyStuForm stuModel = new PjpyStuForm();
		
		// 学号
		String xh = model.getXh();
		stuModel.setXh(xh);

		// 学年
		String xn = model.getXn();
		stuModel.setXn(xn);

		// 学期
		String xq = model.getXq();
		stuModel.setXq(xq);

		// 项目代码
		String xmdm = model.getXmdm();
		stuModel.setXmdm(xmdm);
		
		//申请信息
		List<HashMap<String, String>> infoList = stuService.getSqxxList(stuModel);
		//学生申诉列表
		List<HashMap<String, String>> xsssList = getXsssList(model, user);
		
		//用户类型
		String yhlx = model.getYhlx();
		
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		
		if (infoList != null && infoList.size() > 0) {
			for (int i = 0; i < infoList.size(); i++) {
				
				HashMap<String, String> map = infoList.get(i);
				String bzrsh = map.get("bzrsh");
				String xysh = map.get("xysh");
				String xxsh = map.get("xxsh");
				String id = map.get("id");
				
				// 申诉内容
				String ssnr = "";
				// 申诉时间
				String sssj = "";
				// 处理人
				String clr = "";
				// 处理意见
				String clyj = "";
				
				if (!Base.isNull(id)) {
					if (xsssList != null && xsssList.size() > 0) {
						for (int j = 0; j < xsssList.size(); j++) {
							HashMap<String, String> xsssInfo = xsssList.get(j);
							if (id.equalsIgnoreCase(xsssInfo.get("xmid"))) {
								ssnr = xsssInfo.get("ssnr");
								sssj = xsssInfo.get("sssj");
								clr = xsssInfo.get("clr");
								clyj = xsssInfo.get("clyj");
								break;
							}
						}
					}
				}
				
				map.put("ssnr", ssnr);
				map.put("sssj", sssj);
				map.put("clr", clr);
				map.put("clr", clyj);
				
				if ("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)) {// 班长或班主任
					if ("通过".equalsIgnoreCase(xysh)
							|| "不通过".equalsIgnoreCase(xysh)
							|| "通过".equalsIgnoreCase(xxsh)
							|| "不通过".equalsIgnoreCase(xxsh)) {
						map.put("kfsh", "no");
					} else {
						map.put("kfsh", "yes");
					}
					
					list.add(map);
					
				} else if ("xy".equalsIgnoreCase(yhlx)) {// 学院
					if ("通过".equalsIgnoreCase(xxsh)
							|| "不通过".equalsIgnoreCase(xxsh)) {
						map.put("kfsh", "no");
					} else {
						map.put("kfsh", "yes");
					}
					
					if ("szyc_5sb".equalsIgnoreCase(xmdm)) {
						list.add(map);
					} else {
						if ("通过".equalsIgnoreCase(bzrsh)) {
							list.add(map);
						}
					}
				}else if ("xx".equalsIgnoreCase(yhlx)) {// 学院
					if("通过".equalsIgnoreCase(xysh)){
						map.put("kfsh", "yes");
						list.add(map);
					}
				}
			}
		}
		
		return list;
	}
	
	/**
	 * 获得学生投诉信息
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXstsInfo(ArrayList<String[]> xhList,
			PjpyTeaForm model, User user) {
		return dao.getXstsInfo(xhList, model, user);
	}
	
	/**
	 * 获得学生申诉列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXsssList(PjpyTeaForm model,
			User user) {
		return dao.getXsssList(model, user);
	}
	
	/**
	 * 判断审核状态
	 * 
	 * @author 伟大的骆
	 */
	public String getShztInfo(List<HashMap<String, String>> shztList,
			String xh, String yhlx,String xmdm) {

		int bzrsh_tg = 0;
		int bzrsh_btg = 0;
		int bzrsh_wsh = 0;
		int bzrsh_xcs = 0;
		
		int xysh_tg = 0;
		int xysh_btg = 0;
		int xysh_wsh = 0;
		int xysh_xcs = 0;
		int xysh_th = 0;
		
		int xxsh_tg = 0;
		int xxsh_btg = 0;
		int xxsh_wsh = 0;
		int xxsh_th = 0;
		
		String bzrshqk = "";
		String xyshqk = "";
		String xxshqk = "";

		int sh_num = 0;
		
		for (int j = 0; j < shztList.size(); j++) {

			HashMap<String, String> map = shztList.get(j);

			String shxh = map.get("xh");
			String bzrsh = map.get("bzrsh");
			String xysh = map.get("xysh");
			String xxsh = map.get("xxsh");

			if (shxh.equalsIgnoreCase(xh)) {
				
				if ("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)) {//班主任 或 班长
					
					sh_num++;

					if ("通过".equalsIgnoreCase(bzrsh)) {
						bzrsh_tg++;
					} else if ("不通过".equalsIgnoreCase(bzrsh)) {
						bzrsh_btg++;
					} else if ("未审核".equalsIgnoreCase(bzrsh)) {
						bzrsh_wsh++;
					} else if ("需重审".equalsIgnoreCase(bzrsh)) {
						bzrsh_xcs++;
					}
				}
				

				if ("szyc_5sb".equalsIgnoreCase(xmdm)) {
					if ("xy".equalsIgnoreCase(yhlx)) {// 学院用户

						sh_num++;

						if ("通过".equalsIgnoreCase(xysh)) {
							xysh_tg++;
						} else if ("不通过".equalsIgnoreCase(xysh)) {
							xysh_btg++;
						} else if ("未审核".equalsIgnoreCase(xysh)) {
							xysh_wsh++;
						} else if ("需重审".equalsIgnoreCase(xysh)) {
							xysh_xcs++;
						} else if ("退回".equalsIgnoreCase(xysh)) {
							xysh_th++;
						}
					}
				} else {
					if ("xy".equalsIgnoreCase(yhlx)
							&& "通过".equalsIgnoreCase(bzrsh)) {// 学院用户

						sh_num++;

						if ("通过".equalsIgnoreCase(xysh)) {
							xysh_tg++;
						} else if ("不通过".equalsIgnoreCase(xysh)) {
							xysh_btg++;
						} else if ("未审核".equalsIgnoreCase(xysh)) {
							xysh_wsh++;
						} else if ("需重审".equalsIgnoreCase(xysh)) {
							xysh_xcs++;
						} else if ("退回".equalsIgnoreCase(xysh)) {
							xysh_th++;
						}
					}
				}

				if ("xx".equalsIgnoreCase(yhlx) && "通过".equalsIgnoreCase(xysh)) {// 学院用户

					sh_num++;
					
					if ("通过".equalsIgnoreCase(xxsh)) {
						xxsh_tg++;
					} else if ("不通过".equalsIgnoreCase(xxsh)) {
						xxsh_btg++;
					} else if ("未审核".equalsIgnoreCase(xxsh)) {
						xxsh_wsh++;
					} else if ("退回".equalsIgnoreCase(xxsh)) {
						xxsh_th++;
					}
				}
			}
		}

		if (bzrsh_wsh != 0) {
			bzrshqk = "存在未审核";
		} else if (bzrsh_xcs != 0) {
			bzrshqk = "存在需重审";
		} else if (bzrsh_tg == sh_num) {
			bzrshqk = "全部审核通过";
		} else if (bzrsh_btg == sh_num) {
			bzrshqk = "全部审核不通过";
		} else if (bzrsh_tg != 0) {
			bzrshqk = "部分审核通过";
		} 

		if (sh_num == 0) {
			xyshqk = "无需审核";
		} else if (xysh_wsh != 0) {
			xyshqk = "存在未审核";
		} else if (xysh_xcs != 0) {
			xyshqk = "存在需重审";
		} else if (xysh_tg == sh_num) {
			xyshqk = "全部审核通过";
		} else if (xysh_btg == sh_num) {
			xyshqk = "全部审核不通过";
		} else if (xysh_tg != 0) {
			xyshqk = "部分审核通过";
		} else if (xysh_th != 0) {
			xyshqk = "存在退回";
		} 

		if (sh_num == 0) {
			xxshqk = "无需审核";
		} else if (xxsh_wsh != 0) {
			xxshqk = "存在未审核";
		} else if (xxsh_tg == sh_num) {
			xxshqk = "全部审核通过";
		} else if (xxsh_btg == sh_num) {
			xxshqk = "全部审核不通过";
		} else if (xxsh_tg != 0) {
			xxshqk = "部分审核通过";
		} else if (xxsh_th != 0) {
			xxshqk = "存在退回";
		}
		
		String shzt = "";

		// 班主任或班长
		if ("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)) {
			shzt = bzrshqk;
		} else if ("xy".equalsIgnoreCase(yhlx)) {// 学院
			shzt = xyshqk;
		} else if ("xx".equalsIgnoreCase(yhlx)) {// 学校
			shzt = xxshqk;
		}

		return shzt;
	}
	
	/**
	 * 保存申诉处理
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveSscl(PjpyTeaForm model, User user){

		XsssModel xsssModel = model.getXsssModel();
		
		// 学年
		String xn = xsssModel.getXn();
		// 学期
		String xq = xsssModel.getXq();
		// 学号
		String xh = xsssModel.getXh();
		//项目ID
		String xmid = xsssModel.getXmid();
		// 项目类型
		String xmlx = xsssModel.getXmlx();

		// 评奖项目审核表
		String tableName = "xg_pjpy_szgyyq_xsssb";
		// 主键
		String pk = "xn||xq||xh||xmid";
		// 主键值
		String[] pkValue = new String[] { xn + xq + xh + xmid };
		// 修改字段
		String[] onezd = new String[] { "clr", "clyj", "clsj" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		// 处理人
		String clr = user.getUserName();
		xsssModel.setClr(clr);
		// 处理时间
		String clsj = dao.getNowTime("yyyy-mm-dd hh24:mi:ss");
		xsssModel.setClsj(clsj);
		
		boolean flag = false;
		
		try {
			flag = updateInfoInDb(saveForm, xsssModel, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 保存投诉处理
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveTscl(PjpyTeaForm model, User user){

		XstsModel xstsModel = model.getXstsModel();
		
		// 学年
		String xn = xstsModel.getXn();
		// 学期
		String xq = xstsModel.getXq();
		// 学号
		String xh = xstsModel.getXh();
		// 被投诉人
		String btsr = xstsModel.getBtsr();
		// 项目类型
		String xmlx = xstsModel.getXmlx();

		// 评奖项目审核表
		String tableName = "xg_pjpy_szgyyq_xstsb";
		// 主键
		String pk = "xn||xq||xh||btsr||xmlx";
		// 主键值
		String[] pkValue = new String[] { xn + xq + xh + btsr + xmlx };
		// 修改字段
		String[] onezd = new String[] { "clr", "clyj", "clsj" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		// 处理人
		String clr = user.getUserName();
		xstsModel.setClr(clr);
		// 处理时间
		String clsj = dao.getNowTime("yyyy-mm-dd hh24:mi:ss");
		xstsModel.setClsj(clsj);
		
		boolean flag = false;
		
		try {
			flag = updateInfoInDb(saveForm, xstsModel, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		return flag;
	}
	// ==================以上Made By 伟大的骆======================
	
	/**
	 * 获取综合测评列表
	 * @param model
	 * @return
	 * @author gaobb
	 * @throws Exception 
	 */
	public List<String[]> getZhcpList(PjpyTeaForm model,User user) throws Exception{
		return dao.getZhcpList(model, user);
	}
	
	/**
	 * 获取表头
	 * @param type
	 * @return
	 */
	public String[] getTopTr(String type){
		String[] top=new String[]{"学号","姓名","班级","5S模块分","语言表达分","读书活动分","IVT论坛分",
				"文体活动分","组织能力分","社会实践分","综合素质分","综合素质分排名"};
		return top;
	}
	
	/**
	 * 计算分数排名
	 * @param model
	 * @return
	 * @author gaobb
	 * @throws Exception 
	 */
	public boolean jsfspm(PjpyTeaForm model,HttpServletRequest request) throws Exception{
		return dao.jsfspm(model, request);
	}
	
	/**
	 * 导出综合测评班级汇总表数据
	 * @param model
	 * @param response
	 * @return
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	public boolean exportZhcpBjhzb(PjpyTeaForm model,HttpServletResponse response) throws Exception{
		String bjdm=model.getBjdm();
		if(Base.isNull(bjdm)){//若班级代码为空直接
			return false;
		}
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=exportData.xls"); 
		response.setContentType("application/vnd.ms-excel");
		
		HashMap<String,HashMap<String,String>> szb=dao.getSzgy_zhszycfsszb();
		String bjmc=dao.getBjmc(bjdm);
		String title=bjmc+"班学生综合素质养成课成绩汇总";
		String xthd="学团活动("+(Integer.parseInt(szb.get("yybd").get("zxmzgf"))
							+Integer.parseInt(szb.get("dshd").get("zxmzgf"))
							+Integer.parseInt(szb.get("ivtlt").get("zxmzgf"))
							+Integer.parseInt(szb.get("wthd").get("zxmzgf")))+"分)";
		String[] colNameCN=new String[]{"学号","姓名","5S("+szb.get("wsmk").get("zxmzgf")+"分)",
				"语言表达("+szb.get("yybd").get("zxmzgf")+"分)","读书活动("+szb.get("dshd").get("zxmzgf")+"分)",
				"IVT论坛("+szb.get("ivtlt").get("zxmzgf")+"分)","文体活动("+szb.get("wthd").get("zxmzgf")+"分)",
				"组织能力("+szb.get("zznl").get("zxmzgf")+"分)","社会实践("+szb.get("shsj").get("zxmzgf")+"分)",
				"总计","排名"};
		
		DAO dao = DAO.getInstance();
		List<String[]> list = new ArrayList<String[]>();
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		WritableSheet ws = wwb.createSheet(title, 0);
		
		ws.setColumnView(0, 12);
		ws.setColumnView(1, 15);
		ws.setColumnView(2, 10);
		ws.setColumnView(3, 13);
		ws.setColumnView(4, 13);
		ws.setColumnView(5, 13);
		ws.setColumnView(6, 15);
		ws.setColumnView(7, 15);
		ws.setColumnView(8, 15);
//		ws.setColumnView(9, 15);
//		ws.setColumnView(10, 15);
		
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		WritableCellFormat wcf3 = new WritableCellFormat(); // 构造单元格格式
		wcf3 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		WritableCellFormat titleFormat = ExcelMethods.getWcf(WritableFont.ARIAL, 11, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);; // 构造单元格格式
		//表头
		ws.mergeCells(0, 0, colNameCN.length-1, 0);
		ws.addCell(new Label(0, 0, title, titleFormat));
		//次级表头
		ws.mergeCells(0, 1, 0, 2);
		ws.addCell(new Label(0,1,colNameCN[0],wcf2));
		ws.mergeCells(1, 1, 1, 2);
		ws.addCell(new Label(1,1,colNameCN[1],wcf2));
		ws.mergeCells(2, 1, 2, 2);
		ws.addCell(new Label(2,1,colNameCN[2],wcf2));
		ws.mergeCells(3, 1, 6, 1);
		ws.addCell(new Label(3,1,xthd,wcf3));
		ws.mergeCells(7, 1, 7, 2);
		ws.addCell(new Label(7,1,colNameCN[7],wcf2));
		ws.mergeCells(8, 1, 8, 2);
		ws.addCell(new Label(8,1,colNameCN[8],wcf2));
		ws.mergeCells(9, 1, 9, 2);
		ws.addCell(new Label(9,1,colNameCN[9],wcf2));
		ws.mergeCells(10, 1, 10, 2);
		ws.addCell(new Label(10,1,colNameCN[10],wcf2));
		//三级
		ws.addCell(new Label(3,2,colNameCN[3],wcf2));
		ws.addCell(new Label(4,2,colNameCN[4],wcf2));
		ws.addCell(new Label(5,2,colNameCN[5],wcf2));
		ws.addCell(new Label(6,2,colNameCN[6],wcf2));
		try {
//			for (int m = 0; m < colNameCN.length; m++) {
//				ws.addCell(new Label(m, 1, colNameCN[m]));
//			}
			String[] outputs=new String[]{"xh","xm","wsmkf","yybdf","dshdf","ivtlt","wthd","zznl","shsj","zhszf","zhszfpm"};
			String sql="select a.*,b.xm from szgy_zhszcphzlsb a,view_xsjbxx b where a.xh=b.xh and a.xn=? and a.xq=? and b.bjdm=?";
			list = dao.rsToVator(sql, new String[]{Base.currXn,Base.currXq,bjdm},outputs );
			
			int k = outputs.length;
			for (int i = 0; i < list.size(); i++) {
				String[] tmp = list.get(i);
				for (int j = 0; j < k; j++) {
					ws.addCell(new Label(j, i + 3, tmp[j],wcf2));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据导出失败!");
		} finally {
			wwb.write();
			wwb.close();
		}
		return true;
	}
	/**
	 * 获取导出ivtlt的sql
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws BiffException 
	 */
	public String importIvtlt(String filePath,User user) throws Exception{
		File file = new File(filePath);
		
		String message = "导入失败！";
		
		boolean flag = false;
		
		// 导入数据
		if (!Base.isNull(filePath)) {
			String sql="insert into szyq_ivtltb(xh,xn,xq,jztm,xthdrq,jcdj,ccdj,pf,bzrsh,xysh,xxsh,xxshr) " +
					"values(?,?,?,?,?,?,?,?,?,?,?,?)";
			List<String[]> params = new ArrayList<String[]>();

			Sheet sourceSheet = Workbook.getWorkbook(new File(filePath)).getSheet(0);			
			int sourceRowCount = sourceSheet.getRows();//获得源excel的行数
			String[] row;
			for(int rownum=1;rownum<sourceRowCount;rownum++){//每条记录
				//添加要保存记录   start
				row = ExcelMethods.getOneRow(sourceSheet,rownum,12);
				String[] param=new String[]{row[2],row[0],row[1],row[7],row[8],row[9],row[10],row[11],
						"通过","通过","通过",user.getUserName()+"导入"};
				String[] topTr=new String[]{"学年","学期","学号","姓名","学院","专业","班级","讲座题目","日期","进场登记","出场登记","评分"};
				params.add(param);
			    //添加要保存记录   end
			}
			
			
			flag = saveArrDate(sql, params, "szyq_ivtltb", user);
			if(flag){
				message="导入成功！";
			}
			file.delete();
		}

		return message;
	}
	
	public ArrayList<String[]> getCommShList(PjpyTeaForm model, User user,
			String tableName) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		PjpyTeaService service = new PjpyTeaService();

		// 审核列表
		ArrayList<String[]> dshdList = dao
				.getCommShList(model, user, tableName);
		// 审核状态列表
		List<HashMap<String, String>> shztList = dao.getCommShList(dshdList,
				model, user, tableName);
		// 学生投诉信息
		List<HashMap<String, String>> xstsList = service.getXstsInfo(dshdList,
				model, user);

		ArrayList<String[]> list = new ArrayList<String[]>();

		String yhlx = model.getYhlx();
		String xmdm = model.getXmdm();

		if (dshdList != null && dshdList.size() > 0) {
			for (int i = 0; i < dshdList.size(); i++) {
				String[] rs = dshdList.get(i);
				String xh = rs[0];
				String xm = rs[1];
				String bjmc = rs[2];
				String sqf = fillZero(rs[3]);
				String bzrshf = fillZero(rs[4]);
				String xyshf = fillZero(rs[5]);
				String xxshf = fillZero(rs[6]);

				// 被投诉人
				String btsr = "";
				// 投诉人
				String tsr = "";
				// 投诉内容
				String tsnr = "";
				// 投诉时间
				String tssj = "";

				// 处理投诉信息
				if (xstsList != null && xstsList.size() > 0) {
					for (int j = 0; j < xstsList.size(); j++) {
						HashMap<String, String> xstsInfo = xstsList.get(j);
						if (xh.equalsIgnoreCase(xstsInfo.get("btsr"))) {
							btsr = xstsInfo.get("btsr");
							tsr = xstsInfo.get("tsr");
							tsnr = xstsInfo.get("tsnr");
							tssj = xstsInfo.get("tssj");
						}
					}
				}

				// 班主任或班长
				if ("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)) {

					// 审核状态
					String shzt = service.getShztInfo(shztList, xh, yhlx, xmdm);

					String[] value = new String[] { xh, xm, bjmc, sqf, bzrshf,
							shzt };
					list.add(value);
				} else if ("xy".equalsIgnoreCase(yhlx)) {// 学院
					// 审核状态
					String shzt = service.getShztInfo(shztList, xh, yhlx, xmdm);

					String[] value = new String[] { xh, xm, bjmc, sqf, bzrshf,
							xyshf, shzt, btsr, tsr, tsnr, tssj };

					list.add(value);
				} else if ("xx".equalsIgnoreCase(yhlx)) {// 学校
					// 审核状态
					String shzt = service.getShztInfo(shztList, xh, yhlx, xmdm);

					String[] value = new String[] { xh, xm, bjmc, sqf, bzrshf,
							xyshf, xxshf, shzt, btsr, tsr, tsnr, tssj };

					list.add(value);
				}
			}
		}

		return list;
	}
	
	/**
	 * 获取5S审核信息列表
	 * @param model
	 * @param user
	 * @param tableName
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getFiveSShList(PjpyTeaForm model, User user,String tableName)
		throws IllegalArgumentException, SecurityException,
				IllegalAccessException, InvocationTargetException,
				NoSuchMethodException {

	PjpyTeaService service = new PjpyTeaService();
	
	// 审核列表
	ArrayList<String[]> fiveSList = dao.getFiveSList(model, user,tableName);
	// 审核状态列表
	List<HashMap<String, String>> shztList = dao.getFiveSList(fiveSList,model, user,tableName);
	// 学生投诉信息
	List<HashMap<String, String>> xstsList = service.getXstsInfo(fiveSList, model,
			user);
	
	ArrayList<String[]> list = new ArrayList<String[]>();
	
	String yhlx = model.getYhlx();
	String xmdm = model.getXmdm();
	
	if (fiveSList != null && fiveSList.size() > 0) {
		for (int i = 0; i < fiveSList.size(); i++) {
			String[] rs = fiveSList.get(i);
			String xh = rs[0];
			String xm = rs[1];
			String bjmc = rs[2];
			String sqf = rs[3];
			String xyshf = rs[4];
			String xxshf = rs[5];
	
			// 被投诉人
			String btsr = "";
			// 投诉人
			String tsr = "";
			// 投诉内容
			String tsnr = "";
			// 投诉时间
			String tssj = "";
	
			//处理投诉信息
			if (xstsList != null && xstsList.size() > 0) {
				for (int j = 0; j < xstsList.size(); j++) {
					HashMap<String, String> xstsInfo = xstsList.get(j);			
					if(xh.equalsIgnoreCase(xstsInfo.get("btsr"))){
						btsr = xstsInfo.get("btsr");
						tsr = xstsInfo.get("tsr");
						tsnr = xstsInfo.get("tsnr");
						tssj = xstsInfo.get("tssj");
					}
				}
			}
			
			// 班主任或班长
			if ("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)) {
	
				// 审核状态
				String shzt = service.getShztInfo(shztList, xh, yhlx,xmdm);
	
				String[] value = new String[] { xh, xm, bjmc, sqf, shzt };
				list.add(value);
			} else if ("xy".equalsIgnoreCase(yhlx)) {// 学院
				// 审核状态
				String shzt =service.getShztInfo(shztList, xh, yhlx,xmdm);
	
				
				String[] value = new String[] { xh, xm, bjmc, sqf, xyshf, 
						shzt, btsr, tsr, tsnr, tssj };
				
				list.add(value);
			} else if ("xx".equalsIgnoreCase(yhlx)) {// 学校
				// 审核状态
				String shzt = service.getShztInfo(shztList, xh, yhlx,xmdm);
	
				String[] value = new String[] { xh, xm, bjmc, sqf, xyshf, 
						xxshf, shzt, btsr, tsr, tsnr, tssj };
	
				list.add(value);
			}
		}
	}
	
	return list;
	}
	

	/**
	 * 获取综合测评设定
	 * @param model
	 * @return
	 */
	public HashMap<String,String>getZhcpsdInfo(PjpyTeaForm model){
		
		return dao.getZhcpsdInfo(model);
	}
	
}