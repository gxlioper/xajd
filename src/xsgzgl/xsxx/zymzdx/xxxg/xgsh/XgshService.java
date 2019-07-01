package xsgzgl.xsxx.zymzdx.xxxg.xgsh;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.XsxxGeneralService;
import xsgzgl.xsxx.general.inter.xxxg.XxxgXgshInterface;
import xsgzgl.xsxx.general.jcsz.XsxxJcszService;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;
import xsgzgl.xsxx.general.xxxg.xgsh.XgshDAO;
import xsgzgl.xsxx.general.xxxg.xgsh.XgshModel;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 學生信息_信息修改_修改審核_通用_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XgshService extends XsxxGeneralService implements
		XxxgXgshInterface {

	XgshDAO dao = new XgshDAO();

	/**
	 * 获得表头文件【修改審核】
	 * 
	 * @date 2013-01-24
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXgshTop(XgshModel model, User user) {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pk", "xh", "xm", "xb", "nj", "bjdm",
				"sqsj", "shzt" };
		String[] cn = new String[] { "", "学号", "姓名", "性别", "年级", "班级",
				"申请修改时间", "审核状态" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得结果集【修改審核】
	 * 
	 * @date 2013-01-24
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getXgshList(XsxxGeneralForm myForm,
			XgshModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getXgshList(myForm, model, user);
	}

	/**
	 * 构建结果集【修改審核】
	 * 
	 * @date 2013-01-24
	 * @author 伟大的骆
	 */
	public String createXgshHTML(SearchRsModel rsModel, XgshModel model,
			ArrayList<String[]> rsArrList, User user) {
		
		// IE版本
		String ie = rsModel.getIe();
		// 样式路径
		String stylePath=rsModel.getStylePath();
		
		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {
			
			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				
				String sqid = rs[0];
				String xh = rs[1];
				String xm = rs[2];
				String xb = rs[3];
				String nj = rs[4];
				String bjmc = rs[5];
				String xgsj = rs[6];
				String shzt = rs[7];
				
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				spHtml.append("<td align=\"left\" width=\"5px\">");
				spHtml.append("<input type=\"checkbox\" name=\"primarykey_checkVal\" ");
				spHtml.append("value=\"" + sqid + "\"/>");
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(xh);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(xm);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(xb);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(nj);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(bjmc);
				spHtml.append("</td>");
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append(xgsj);
				spHtml.append("</td>");
				
				String pic_name = "";
				
				if ("wsh".equalsIgnoreCase(shzt)) {
					pic_name = "dsh";
				} else if ("tg".equalsIgnoreCase(shzt)) {
					pic_name = "shtg";
				} else if ("btg".equalsIgnoreCase(shzt)) {
					pic_name = "shbtg";
				} else if ("th".equalsIgnoreCase(shzt)) {
					pic_name = "shth";
				} else if ("xcs".equalsIgnoreCase(shzt)) {
					pic_name = "shxcs";
				}
				
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" width=\"\">");
				spHtml.append("<p><img src=\""+stylePath+"images/ico_"+pic_name+".gif\" width=\"52\" height=\"18\" /></p>");
				spHtml.append("</td>");
				
				spHtml.append("</tr>");
			}
		}
		return spHtml.toString();
	}
	
	/**
	 * 获得學生修改信息
	 * 
	 * @date 2013-01-24
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getXgshInfo(XgshModel model, User user) {

		// 學生信息表字段
		String[] xsxxb = { "xh", "xm", "xb", "csrq", "nj", "xz", "xymc",
				"zzmmmc", "zymc", "mzmc", "bjmc", "xjztm", "rxrq", "sfzh",
				"jgmc", "hkszdmc", "sydmc", "sjhm", "dzyx", "qqhm", "jtdh",
				"jtyb", "jtszd", "jtcy1_xm", "jtcy1_gx", "jtcy1_gzdz",
				"jtcy1_lxdh2", "jtcy1_lxdh1", "jtcy2_xm", "jtcy2_gx",
				"jtcy2_gzdz", "jtcy2_lxdh2", "jtcy2_lxdh1", "jtcy3_xm",
				"jtcy3_gx", "jtcy3_gzdz", "jtcy3_lxdh2", "jtcy3_lxdh1", "xmpy",
				"cym", "sg", "tz", "tc", "jkzk", "pyccmc", "sfzd", "kslbmc",
				"rxfsmc", "pyfsmc", "bz","yhdm","yhkh","zw","zd1","zd2","zd3","zd4","zd5","grjl"};

		// 申請ID
		String sqid = model.getSqid();

		List<HashMap<String, String>> list = dao.getXgxxList(sqid, xsxxb);

		HashMap<String, String> map = new HashMap<String, String>();
		// 學生信息
		HashMap<String, String> xsxx = list.get(0);
		// 修改信息
		HashMap<String, String> xgxx = list.get(1);

		for (int i = 0; i < xsxxb.length; i++) {

			String zd = xsxxb[i];
			String xsxx_zd = xsxx.get(zd);
			String xgxx_zd = xgxx.get(zd);
			
			if(zd.equalsIgnoreCase("jtyb")){
				int a=0;
				a++;
			}
			if (Base.isNull(xgxx_zd) || Base.isNull(xgxx_zd.trim())) {
				map.put(zd, xsxx_zd);
			} else if (!Base.isNull(xgxx_zd)) {
				//把有改成无  格式为 "strLiTT"，以LiTT结尾
				
				if(xgxx_zd.endsWith("LiTT")){
					xgxx_zd = xgxx_zd+"空";
				}
				String[] value = xgxx_zd.split("LiTT");
				if(value.length==1){
					map.put(zd, xgxx_zd);
				} else {		
					if("null".equalsIgnoreCase(value[0]) || StringUtils.isNull(value[0])){
						value[0]="空";
					}
					String msg = "修改前信息为：" + value[0];
					StringBuilder html = new StringBuilder();
					html.append("<a ");
					html.append("title=\""+msg+"\"");
					html.append(">");
					html.append("<font color=\"red\">");
					if("null".equalsIgnoreCase(value[1])){
						html.append("空");
					}else{
						html.append(value[1]);
					}
					html.append("</font>");
					html.append("</a>");
					
					map.put(zd, html.toString());
				}
			}
			/*
			 * else if (!Base.isNull(xgxx_zd)) {
						
				String[] value = xgxx_zd.split("LiTT");
				
				if(0 !=(xgxx_zd.indexOf("LiTT", 0))){
					if(value.length == 1){
						value = new String []{value[0],"空"};
					}
				}
					
				if(value.length==1){
					map.put(zd, xgxx_zd);
				} else {				
					String msg = "修改前信息为：" + value[0];
					StringBuilder html = new StringBuilder();
					html.append("<a ");
					html.append("title=\""+msg+"\"");
					html.append(">");
					html.append("<font color=\"red\">");
					html.append(value[1]);			
					html.append("</font>");
					html.append("</a>");
					
					map.put(zd, html.toString());
				}
			}
			 */
		}

		return map;
	}

	/**
	 * 保存審核狀態
	 * 
	 * @date 2013-01-25
	 * @author 伟大的骆
	 */
	public boolean saveShzt(XgshModel model, User user) {

		XsxxJcszService jcszService = new XsxxJcszService();
//		// 審核相關信息
//		HashMap<String, Object> shxgInfo = jcszService.getShlcByYh(user
//				.getUserName());
//		// 審批崗位列表
//		List<HashMap<String, String>> spgwList = (List<HashMap<String, String>>) shxgInfo
//				.get("spgwList");
		XsxxtyglService service = new XsxxtyglService();
		HashMap<String, String> csszMap = service.getCsszjg();
		List<HashMap<String, String>> spgwList = jcszService.getSpgwBySplc(csszMap.get("shlid"));
		// 申請ID
		String sqid = model.getSqid();
		// 審核意見
		String shyj = model.getShyj();
		// 審核狀態
		String shzt = model.getShzt();
		// 崗位ID
		String gwid = model.getGwid();
		// 主鍵
		String[] pkValue = model.getPkValue();

		// 最大級別
		boolean isMax = gwid.equalsIgnoreCase(getSpgwByType(spgwList, "max")) ? true : false;

		// 最小級別
		boolean isMin = gwid.equalsIgnoreCase(getSpgwByType(spgwList, "min")) ? true
				: false;

		// 崗位級別
		int gwjb = 0;
		// 审批岗位
		List<String> spgw = new ArrayList<String>();
		
		for (int i = 0; i < spgwList.size(); i++) {
			
			spgw.add(spgwList.get(i).get("spgw"));
			
			if (gwid.equalsIgnoreCase(spgwList.get(i).get("spgw"))) {
				gwjb = i;
			}
		}
		
		boolean flag = false;

		try {

			// 保存本審核崗位的審核狀態
			flag = dao.updateXgshb(pkValue, gwid, shzt, shyj, user);

			// 保存待办工作
			saveDbgz(pkValue, gwid, spgw.toArray(new String[] {}), shzt);
			
			if ("th".equalsIgnoreCase(shzt) && !isMin) {// 本級別退回，將前一級別設置為需重審
				shzt = "xcs";
				gwid = spgwList.get(gwjb - 1).get("spgw");
				dao.updateXgshb(pkValue, gwid, shzt, user);
			} else if ("tg".equalsIgnoreCase(shzt) && !isMax) {// 本級別通過，將后一級別設置為需重審
				shzt = "wsh";
				gwid = spgwList.get(gwjb + 1).get("spgw");
				dao.updateXgshb(pkValue, gwid, shzt, user);
			}

			String shjg = "shz";

			if ("tg".equalsIgnoreCase(shzt) && isMax) {// 最高級通過
				shjg = "tg";
			} else if ("th".equalsIgnoreCase(shzt) && isMin) {// 最小級別退回
				shjg = "th";
			}

			// 保存申請表的審核結果
			if (flag) {
				flag = dao.updateXgsqb(pkValue, shjg, user);
			}
			
			// 最高級用戶審核通過則更新學生信息庫
			if ("tg".equalsIgnoreCase(shzt) && isMax) {
				submitXxxg(pkValue, user);
			}
			
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 保存待办工作
	 * 
	 * @date 2013-02-01
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void saveDbgz(String[] sqid, String gwid, String[] spgw, String shzt)
			throws Exception {

		// 申请ID
		String[] id = sqid;
		// 当前岗位ID
		String curShgw = gwid;
		// 当前序列
		int index = StringUtils.getStrIndexInArray(curShgw, spgw);

		for (String str : id) {
			Job job = null;
			if ("tg".equals(shzt)) {
				String nextShgw = index != spgw.length - 1
						&& spgw[index + 1] != null ? spgw[index + 1] : null;
				job = Job.getJobInstance(str, nextShgw,
						"general_xsxx.do?method=xgshSearch&spgw="+nextShgw, "学生信息修改");
			} else if ("th".equals(shzt)) {
				if (index != 0) {
					String nextShgw = index != 0 ? spgw[index - 1] : null;
					job = Job.getJobInstance(str, nextShgw,
							"general_xsxx.do?method=xgshSearch&spgw="+nextShgw, "学生信息修改");
				} else {
					job = Job.getJobInstance(str, "学生信息修改");
				}
			}

			MyJobsManager manager = new MyJobsImpl();
			manager.updateJob(job);
		}
	}
	
	/**
	 * 获得審核信息列表
	 * 
	 * @date 2013-01-25
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXgshList(XgshModel model, User user) {
		XsxxtyglService service = new XsxxtyglService();
		XsxxJcszService jcszService = new XsxxJcszService();
//		// 審核相關信息
//		HashMap<String, Object> shxgInfo = jcszService.getShlcByYh(user
//				.getUserName());
//		// 審批崗位列表
//		List<HashMap<String, String>> spgwList = (List<HashMap<String, String>>) shxgInfo
//				.get("spgwList");
		
		HashMap<String, String> csszMap = service.getCsszjg();
		List<HashMap<String, String>> spgwList = jcszService.getSpgwBySplc(csszMap.get("shlid"));
		// 申請ID
		String sqid = model.getSqid();
		// 崗位ID
		String gwid = model.getGwid();

		List<HashMap<String, String>> shList = dao.getXgshList(sqid);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < spgwList.size(); i++) {

			// 審批崗位
			String spgw = spgwList.get(i).get("spgw");
			// 崗位名稱
			String gwmc = spgwList.get(i).get("gwmc");
			// 審核狀態
			String shzt = "";
			// 審核人
			String shr = "";
			// 審核時間
			String shsj = "";
			// 審核意見
			String shyj = "";
			// 是否顯示
			String sfxs = "yes";
			// 本級意見
			String bjyj = "no";
			
			for (int j = 0; j < shList.size(); j++) {
				if (spgw.equalsIgnoreCase(shList.get(j).get("gwid"))) { 
					shzt = shList.get(j).get("shzt");
					shr = shList.get(j).get("shrxm");
					shsj = shList.get(j).get("shsj");
					shyj = shList.get(j).get("shyj");

					if ("wsh".equalsIgnoreCase(shzt)
						|| "xcs".equalsIgnoreCase(shzt)) {
						sfxs = "no";
					}

					if (shList.get(j).get("gwid").equalsIgnoreCase(gwid)) {
						bjyj = "yes";
					}
					
					break;
				}
			}

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("gwmc", gwmc);
			map.put("shzt", shzt);
			map.put("shr", shr);
			map.put("shsj", shsj);
			map.put("shyj", shyj);
			map.put("sfxs", sfxs);
			map.put("bjyj", bjyj);
			
			list.add(map);
		}

		return list;
	}

	/**
	 * 提交學生修改信息
	 * 
	 * @date 2013-01-24
	 * @author 伟大的骆
	 */
	public void submitXxxg(String[] sqid, User user) {

		// 學生信息表字段
		String[] xsxxb = { "xm", "xb", "csrq", "xz", "zzmm", "yhdm","yhkh",
				 "mz", "xjztm", "rxrq", "sfzh", "jg", "hkszd",
				"syd", "sjhm", "dzyx", "qqhm", "jtyb", "xmpy", "cym", "sg",
				"tz", "tc", "jkzk", "pycc", "sfzd", "kslb", "rxfs", "bz","jtdh","pyfs",
				"zw","zd1","zd2","zd3","zd4","zd5","grjl"};

		// 學生輔助信息表字段
		String[] xsfzxxb = { "lxdh1", "jtszd", "jtcy1_xm", "jtcy1_gx",
				"jtcy1_gzdz", "jtcy1_lxdh2", "jtcy1_lxdh1", "jtcy2_xm",
				"jtcy2_gx", "jtcy2_gzdz", "jtcy2_lxdh2", "jtcy2_lxdh1",
				"jtcy3_xm", "jtcy3_gx", "jtcy3_gzdz", "jtcy3_lxdh2",
				"jtcy3_lxdh1" };
			
		try {
			//提交更新學生信息表
			dao.updateXsxxb(sqid, xsxxb);
			//提交更新學生輔助信息表
			dao.updateXsfzxxb(sqid, xsfzxxb);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * 初始化功能参数
	 * 
	 * @date 2013-01-25
	 * @author 伟大的骆
	 */
	public void initParameter() {

		String path = "xsxx_general_xxxg_xgsh.do";

		// 高级查询是否配置
		boolean isSearch = isExists("xg_search_szb", "path", path);

		if (!isSearch) {
			initSearch();
		}
	}
	
	/**
	 * 初始化查询条件
	 * 
	 * @date 2013-01-25
	 * @author 伟大的骆
	 */
	private void initSearch() {

		List<String[]> params = new ArrayList<String[]>();

		String path = "xsxx_general_xxxg_xgsh.do";
		String[] tj = new String[] { "xh", "xm", "nj", "xy", "zy", "bj",
				"shztTwo" };
		String[] mc = new String[] { "学号", "姓名", "年级", "院系", "专业", "班级", "审核状态" };
		String[] lx = new String[] { "mhcx", "mhcx", "djcx", "djcx", "djcx",
				"djcx", "djcx" };
		String[] zd = new String[] { "xh", "xm", "nj", "xydm", "zydm", "bjdm",
				"shztmc" };
		String[] ssmk = new String[] { "xsxx", "xsxx", "xsxx", "xsxx", "xsxx",
				"xsxx", "xsxx" };
		String[] xssx = new String[] { "1", "2", "1", "2", "3", "4", "5" };

		for (int i = 0; i < tj.length; i++) {
			String[] value = new String[] { path, tj[i], mc[i], lx[i], zd[i],
					ssmk[i], xssx[i] };
			params.add(value);
		}

		try {
			dao.initSearch(params);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}
	
	//获取最大最小岗位
	public String getSpgwByType(List<HashMap<String, String>> splcList,String type){
		String spgw_min = "";
		String spgw_max = "";
		if(null != splcList && splcList.size()>0){
			spgw_min = splcList.get(0).get("spgw");
			spgw_max = splcList.get(0).get("spgw");
			int xh_min = Integer.valueOf(splcList.get(0).get("xh"));
			int xh_max = Integer.valueOf(splcList.get(0).get("xh"));
			for(int i=1;i<splcList.size();i++){
				int xh = Integer.valueOf(splcList.get(i).get("xh"));
				if(xh < xh_min){
					spgw_min=splcList.get(i).get("spgw");
					xh_min=Integer.valueOf(splcList.get(i).get("xh"));
				}
				if(xh > xh_max){
					spgw_max=splcList.get(i).get("spgw");
					xh_max=Integer.valueOf(splcList.get(i).get("xh"));
				}
				
			}
		}
		if("min".equalsIgnoreCase(type)){
			return spgw_min;
		}else{
			return spgw_max;
		}
	}
}