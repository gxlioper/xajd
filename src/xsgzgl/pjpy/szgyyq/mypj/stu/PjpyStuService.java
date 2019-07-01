package xsgzgl.pjpy.szgyyq.mypj.stu;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xsgzgl.pjpy.szgyyq.model.XsssModel;
import xsgzgl.pjpy.szgyyq.model.XstsModel;
import xsgzgl.pjpy.szgyyq.mypj.PjpyMypjService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.DshdService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.FiveSService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.IvtltService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.ShsjService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.WthdService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.YybdService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.ZhcpService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.ZznlService;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖(学生)_Service类
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

public class PjpyStuService extends PjpyMypjService {

	PjpyStuDAO dao = new PjpyStuDAO();

	/**
	 * 获得项目列表(分数申请)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getFssqXmList(PjpyStuForm model) {

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

		return xmList;
	}	
	
	/**
	 * 创建结果集
	 * 
	 * @author 伟大的骆
	 * 
	 * @throws IOException
	 */
	public String getSqxmInfo(PjpyStuForm model) {

		// 学号
		String xh = model.getXh();
		// 项目代码
		String xmdm = model.getXmdm();
		// 学年
		String xn = Base.currXn;
		// 学期
		String xq = Base.currXq;
		
		List<HashMap<String,String>> list = dao.getSqxmInfo(model);
		
		StringBuilder html = new StringBuilder();
		
		if (list != null && list.size() > 0) {
			
			HashMap<String, String> map = list.get(0);
			// 申请分
			String sqf = map.get("sqf");
			// 审核分
			String shf = map.get("shf");
			// 基础分
			String jcf = map.get("jcf");
			// 总分
			String zf = map.get("zf");
			
			html.append("<span class=\"formbox\">");
			html.append("该项目信息：");
			
			html.append("基础分(");
			html.append(jcf);
			html.append("分) ");
			
			html.append("总分(");
			html.append(zf);
			html.append("分)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			
			html.append("你的申请情况：");
			html.append("已申请分(");
			if("0".equalsIgnoreCase(sqf)){
				html.append(sqf);
			}else{
				html.append("<a href=\"#\" onclick=\"showSqxxDetail('"+xn+"','"+xq+"','"+xh+"','"+xmdm+"','edit');return false;\">");
				html.append("<font color=\"blue\">");
				html.append(sqf);
				html.append("</font>");
				html.append("</a>");
			}
			html.append("分) ");
			
			html.append("最终审核分(");
			html.append(shf);
			html.append("分) ");
			
			html.append("</span>");

		}

		return html.toString();
	}
	
	/**
	 * 获得申请信息列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getSqxxList(PjpyStuForm model) {

		List<HashMap<String, String>> xmList = new ArrayList<HashMap<String, String>>();

		// 项目代码
		String xmdm = model.getXmdm();

		if ("szyq_dshdjzb".equalsIgnoreCase(xmdm)) {// 读书活动
			DshdService service = new DshdService();
			xmList = service.getDshdList(model);
		} else if ("szyq_yybdjzb".equalsIgnoreCase(xmdm)) {// 语言表达
			YybdService service =new YybdService();
			xmList = service.getYybdList(model);
		} else if ("szyq_ivtltb".equalsIgnoreCase(xmdm)) {// IVT论坛
			IvtltService service =new IvtltService();
			xmList = service.getIvtltList(model);
		} else if ("szyq_xthddjb".equalsIgnoreCase(xmdm)) {// 文体活动
			WthdService service =new WthdService();
			xmList = service.getWthdList(model);
		} else if ("szyc_zznlfzb".equalsIgnoreCase(xmdm)) {// 组织活动
			ZznlService service =new ZznlService();
			xmList = service.getZznlList(model);
		} else if ("szyc_shsjfzb".equalsIgnoreCase(xmdm)) {// 社会实践
			ShsjService service =new ShsjService();
			xmList = service.getShsjList(model);
		} else if ("szyc_5sb".equalsIgnoreCase(xmdm)) {// 5s
			FiveSService service =new FiveSService();
			xmList = service.getFiveSList(model);
		} else if ("szgy_zhszcphzlsb".equalsIgnoreCase(xmdm)) {// 综合素质分
			ZhcpService service =new ZhcpService();
			xmList = service.getZhcpList(model);
		}

		int rownum = 10;
		int rsnum = (xmList != null && xmList.size() > 0) ? xmList.size() : 0;

		// 补行
		if ((rownum - rsnum) > 0) {
			for (int i = 0; i < (rownum - rsnum); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("sqf", "&nbsp;");

				xmList.add(map);
			}
		}

		return xmList;
	}
	
	/**
	 * 保存学生申诉
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveXsss(PjpyStuForm model, User user,HttpServletRequest request){

		XsssModel xsssModel = model.getXsssModel();
		
		// 学号
		String xh = xsssModel.getXh();
		// 项目ID
		String xmid = xsssModel.getXmid();
		
		// 评奖项目审核表
		String tableName = "xg_pjpy_szgyyq_xsssb";
		// 主键
		String pk = "xh||xmid";
		// 主键值
		String[] pkValue = new String[] { xh + xmid };
		// 修改字段
		String[] onezd = new String[] { "xn", "xq", "xh", "xmlx", "xmid",
				"ssnr" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		boolean flag = false;
		
		try {
			flag = saveInfoToDb(saveForm, xsssModel, request);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 保存学生投诉
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveXsts(PjpyStuForm model, User user,HttpServletRequest request){

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
		String[] onezd = new String[] { "xn", "xq", "xh", "btsr", "xmlx",
				"tsnr" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		boolean flag = false;
		
		try {
			flag = saveInfoToDb(saveForm, xstsModel, request);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 获得项目列表(结果查询)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getJgcxXmList(PjpyStuForm model) {

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

		// 5S分
		map = new HashMap<String, String>();
		map.put("xmdm", "szyc_5sb");
		map.put("xmmc", "5S");
		xmList.add(map);

		// 综合素质测分
		map = new HashMap<String, String>();
		map.put("xmdm", "szgy_zhszcphzlsb");
		map.put("xmmc", "综合素质分");
		xmList.add(map);
		
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
	public ArrayList<String[]> getJgcxList(PjpyStuForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		//项目代码
		String xmdm = model.getXmdm();
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		if ("szyq_dshdjzb".equalsIgnoreCase(xmdm)) {// 读书活动
			DshdService service = new DshdService();
			list = service.getDshdList(model, user);
		} else if ("szyq_yybdjzb".equalsIgnoreCase(xmdm)) {// 语言表达
			YybdService service = new YybdService();
			list = service.getYybdList(model, user);
		} else if ("szyq_ivtltb".equalsIgnoreCase(xmdm)) {// IVT论坛
			IvtltService service = new IvtltService();
			list = service.getIvtltList(model, user);
		} else if ("szyq_xthddjb".equalsIgnoreCase(xmdm)) {// 文体活动
			WthdService service = new WthdService();
			list = service.getWthdList(model, user);
		} else if ("szyc_zznlfzb".equalsIgnoreCase(xmdm)) {// 组织活动
			ZznlService service = new ZznlService();
			list = service.getZznlList(model, user);
		} else if ("szyc_shsjfzb".equalsIgnoreCase(xmdm)) {// 社会实践
			ShsjService service = new ShsjService();
			list = service.getShsjList(model, user);
		} else if ("szyc_5sb".equalsIgnoreCase(xmdm)) {// 5s
			FiveSService service = new FiveSService();
			list = service.getFiveSList(model, user);
		} else if ("szgy_zhszcphzlsb".equalsIgnoreCase(xmdm)) {// 综测总分
			ZhcpService service = new ZhcpService();
			list = service.getZhcpList(model, user);
		}
		
		return list;
	}
	
	/**
	 * 获得结果查询Html
	 * 
	 * @author 伟大的骆
	 */
	public String getJgcxHtml(SearchRsModel rsModel, PjpyStuForm model,
			ArrayList<String[]> rsArrList,User user) {
		
		//项目代码
		String xmdm = model.getXmdm();
		String html = "";
		
		if ("szyq_dshdjzb".equalsIgnoreCase(xmdm)) {// 读书活动
			DshdService service = new DshdService();
			html = service.getDshdHtml(rsModel, model, rsArrList,user);
		} else if ("szyq_yybdjzb".equalsIgnoreCase(xmdm)) {// 语言表达
			YybdService service = new YybdService();
			html = service.getYybdHtml(rsModel, model, rsArrList,user);
		} else if ("szyq_ivtltb".equalsIgnoreCase(xmdm)) {// IVT论坛
			IvtltService service = new IvtltService();
			html = service.getIvtltHtml(rsModel, model, rsArrList,user);
		} else if ("szyq_xthddjb".equalsIgnoreCase(xmdm)) {// 文体活动
			WthdService service = new WthdService();
			html = service.getWthdHtml(rsModel, model, rsArrList,user);
		} else if ("szyc_zznlfzb".equalsIgnoreCase(xmdm)) {// 组织活动
			ZznlService service = new ZznlService();
			html = service.getZznlHtml(rsModel, model, rsArrList,user);
		} else if ("szyc_shsjfzb".equalsIgnoreCase(xmdm)) {// 社会实践
			ShsjService service = new ShsjService();
			html = service.getShsjHtml(rsModel, model, rsArrList,user);
		} else if ("szyc_5sb".equalsIgnoreCase(xmdm)) {// 5s
			FiveSService service = new FiveSService();
			html = service.getFiveSHtml(rsModel, model, rsArrList,user);
		} else if ("szgy_zhszcphzlsb".equalsIgnoreCase(xmdm)) {// 5s
			ZhcpService service = new ZhcpService();
			html = service.getZhcpHtml(rsModel, model, rsArrList,user);
		}

		return html;
	}
	
	/**
	 * 获得我的申诉信息列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getMyssList(PjpyStuForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		ArrayList<String[]> list = dao.getMyssList(model, user);

		return list;
	}
	
	/**
	 * 获得我的申诉Html
	 * 
	 * @author 伟大的骆
	 */
	public String getMyssHtml(SearchRsModel rsModel, PjpyStuForm model,
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
		
		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				for (int j = 0; j < rs.length-8; j++) {
					spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%;\" ");
					
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						spHtml.append("height=\"28.5px\"");
					} else {
						spHtml.append("height=\"29px\"");
					}
					
					if(j == 1){
						String ssnr = rs[rs.length-3];
						spHtml.append(" title=\""+ssnr+"\" ");
					}
					spHtml.append(">");
					
					spHtml.append(rs[j]);
					spHtml.append("</td>");
				}
				
				// 申诉状态
				String lx = rs[rs.length-1];
	
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%;\" > ");
				if("未处理".equalsIgnoreCase(lx)){
					spHtml.append("未处理");
				}else{
					
					String ssnr = rs[rs.length-3];
					String clyj = rs[rs.length-2];
					String sssj = rs[rs.length-11];
					String clr = rs[rs.length-9];
					String clsj = rs[rs.length-8];
					
					spHtml.append("<a href\"#\" onclick=\"showXsssDiv('"+ssnr+"','"+sssj+"','"+clr+"','"+clyj+"','"+clsj+"');return false;\" style=\"cursor:hand\">");
					spHtml.append("<font color=\"blue\">" + lx + "</font>");
					spHtml.append("</a>");
				}	
				spHtml.append("</td>");
				
				spHtml.append("</tr>");
			}
		}

		return spHtml.toString();
	}
	
	/**
	 * 获得我的投诉信息列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getMytsList(PjpyStuForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		ArrayList<String[]> list = dao.getMytsList(model, user);

		return list;
	}
	
	/**
	 * 获得我的投诉Html
	 * 
	 * @author 伟大的骆
	 */
	public String getMytsHtml(SearchRsModel rsModel, PjpyStuForm model,
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
		
		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				for (int j = 0; j < rs.length-7; j++) {
					spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%;\" ");
					
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						spHtml.append("height=\"28.5px\"");
					} else {
						spHtml.append("height=\"29px\"");
					}
					
					if(j == 3){
						String tsnr = rs[rs.length-3];
						spHtml.append(" title=\""+tsnr+"\" ");
					}
				
					spHtml.append(">");
					
					spHtml.append(rs[j]);
					spHtml.append("</td>");
				}
				
				// 申诉状态
				String lx = rs[rs.length-1];
	
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%;\" > ");
				if("未处理".equalsIgnoreCase(lx)){
					spHtml.append("未处理");
				}else{
					
					String tsnr = rs[rs.length-3];
					String clyj = rs[rs.length-2];
					
					String tssj = rs[rs.length-9];
					String clr = rs[rs.length-8];
					String clsj = rs[rs.length-6];
					
					spHtml.append("<a href\"#\" onclick=\"showXstsDiv('"+tsnr+"','"+tssj+"','"+clr+"','"+clyj+"','"+clsj+"');return false;\" style=\"cursor:hand\">");
					spHtml.append("<font color=\"blue\">" + lx + "</font>");
					spHtml.append("</a>");
				}	
				spHtml.append("</td>");
				
				spHtml.append("</tr>");
			}
		}

		return spHtml.toString();
	}
	
	/**
	 * 检验可否操作
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean checkKfcl(PjpyStuForm model) {

		// 申请ID
		String sqid = model.getSqid();

		// 项目ID
		String xmdm = model.getXmdm();

		String pk = "id||bzrsh";
		String pkValue = sqid + "未审核";

		boolean flag = isExists(xmdm, pk, pkValue);

		return flag;

	}
	
	/**
	 *  删除申请记录
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean deleteSqjl(PjpyStuForm model,User user) {

		// 申请ID
		String sqid = model.getSqid();

		// 项目ID
		String xmdm = model.getXmdm();

		boolean flag = false;
		
		// 评奖项目审核表
		String tableName = xmdm;
		// 主键
		String pk = "id";
		// 主键值
		String[] pkValue = new String[] { sqid };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		
		try {
			flag = delInfoInDb(saveForm, model, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;

	}
}