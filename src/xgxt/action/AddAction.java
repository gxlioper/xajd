/*
 * 创建日期 2007-4-11  bat_zzj
 *
 */
package xgxt.action;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.sql.CLOB;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.utils.CheckPower;
import xgxt.utils.String.StringUtils;

import common.Globals;

public class AddAction extends Action {

	//DAO dao = DAO.getInstance();

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String userType;

//		String userDep;

		String sUName;

//		String logMsg;

		String writeAble;

//		String currXn= dao.getConf(2);

//		String currNd=dao.getConf(3);

//		String currXq=dao.getConf(6);
		HttpSession session = request.getSession();
		try {
			/** 在线检测 */
			int i = Base.chkTimeOut(session);
			if (i <= 2) {
				request.setAttribute("errMsg", "登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}
			boolean isStu = true;
			userType = session.getAttribute("userType")== null?"":session.getAttribute("userType").toString();
			isStu = (userType.equalsIgnoreCase("stu"));
			sUName = (String) session.getAttribute("userName");
//			userDep = session.getAttribute("userDep").toString();

			ActionForward myActFwd = null;
			String act = mapping.getParameter();
			String power;
			int p = -1;

			if (act.equalsIgnoreCase("addStuInfo")) {
				power = "stu_info_query.do";
				p = Base.chkUPower(sUName, power, isStu);
				if (p == -1) {
					request.setAttribute("errMsg", "无权访问该页面！");
					return new ActionForward("/errMsg.do", false);
				}
				myActFwd = addStuInfo(mapping, form, request, response, p);
			} else if (act.equalsIgnoreCase("yxbys")) {
				p = 1;
				myActFwd = mapping.findForward("success");
			} else if (act.equalsIgnoreCase("rychqsb")) {
				p = 1;
				myActFwd = rychPri(mapping, form, request, response, p);
			} else if (act.equalsIgnoreCase("xy_priseConf")) {
				power = "prise_conf_rs.do";
				p = Base.chkUPower(sUName, power, isStu);
				if (p == -1) {
					request.setAttribute("errMsg", "无权访问该页面！");
					return new ActionForward("/errMsg.do", false);
				} 
				myActFwd = xy_priseConf(mapping, form, request, response, p);
			} else if ("getNewsFile".equalsIgnoreCase(act)) {// 新闻发布
				myActFwd = getNewsFile(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("xy_priseConf_one")) {
				power = "prise_conf_rs.do";
				p = Base.chkUPower(sUName, power, isStu);
				if (p == -1) {
					request.setAttribute("errMsg", "无权访问该页面！");
					return new ActionForward("/errMsg.do", false);
				}
				myActFwd = xy_priseConf_one(mapping, form, request, response, p);
			} else if (act.equalsIgnoreCase("syzy_zhszcp")) {
				power = "syzy_zhszcp.do";
				p = Base.chkUPower(sUName, power, isStu);
				if (p == -1) {
					request.setAttribute("errMsg", "无权访问该页面！");
					return new ActionForward("/errMsg.do", false);
				}
				myActFwd = syzy_zhszcp(mapping, form, request, response, p);
			} else if (act.equalsIgnoreCase("topicPub")) {
				p = 1;
				myActFwd = topicPub(mapping, form, request, response, p);
			} else if (act.equalsIgnoreCase("topicObject")) {
				p = 1;
				myActFwd = topicObject(mapping, form, request, response, p);
			} else if (act.equalsIgnoreCase("topicList")) {
				p = 1;
				myActFwd = topicList(mapping, form, request, response, p);
			} else if (act.equalsIgnoreCase("topicRead")) {
				p = 1;
				myActFwd = topicRead(mapping, form, request, response, p);
			} else if (act.equalsIgnoreCase("topicReply")) {
				p = 1;
				myActFwd = topicReply(mapping, form, request, response, p);
			} 
			writeAble = (p == 1) ? "yes" : "no";
			request.setAttribute("writeAble", writeAble);
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errMsg", "出现灾难性故障，" + e.toString());
			return new ActionForward("/errMsg.do", false);
		}
	}



	private ActionForward topicReply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, int power)
	throws Exception {
		DAO dao = DAO.getInstance();
		ActionForward newFwd = new ActionForward();
		String sql = "";
		HttpSession session = request.getSession();
		String gtr = session.getAttribute("userName").toString();
		String gtbh = String.valueOf(System.currentTimeMillis());
		String sszt = request.getParameter("ztbh");
		String gtzw = request.getParameter("hfzw");
		gtzw = gtzw.replaceAll("<", "&lt;");
		gtzw = gtzw.replaceAll(">", "&gt;");
		gtzw = gtzw.replaceAll("\n", "<br>");

		sql = " insert into wsbggtb (sszt,gtr,gtbh,gtzw) values (?,?,?,?) ";
		dao.runUpdate(sql, new String[]{ sszt,gtr,gtbh,gtzw });

		request.setAttribute("ztbh", sszt);
		newFwd = new ActionForward("/topic_read.do?ztbh="+sszt, false);
		return newFwd;
	}

	private ActionForward topicRead(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, int power)
	throws Exception {    	   	
		DAO dao = DAO.getInstance();
		String ztbh = request.getParameter("ztbh");
		String sql = " select * from view_wsbgztxx ";
		String[] outPut = dao.getColumnName(sql);
		String[] outValue = dao.getOneRs(sql+" where ztbh = ? ", new String[]{ ztbh }, outPut);

		HashMap<String, String> map = new HashMap<String, String>();

		if(outValue!=null){
			for(int i=0;i<outPut.length;i++){
				if(outValue[i]!=null)
					map.put(outPut[i].toLowerCase(), outValue[i]);
				else 
					map.put(outPut[i].toLowerCase(), "");
			}
		} else {
			for(int i=0;i<outPut.length;i++){
				map.put(outPut[i].toLowerCase(), "");
			}
		}

		sql = " select ztzw from view_wsbgztxx  ";
		CLOB ztzw = dao.getOneClob(sql+" where ztbh = ? ", new String[]{ ztbh }, "ztzw");
		String ztzwxx = ztzw.getSubString(1, Integer.parseInt(String.valueOf(ztzw.length())));    	
		map.put("ztzw", ztzwxx);

		request.setAttribute("rs", map);

		sql = " select * from wsbggtb ";
		outPut = dao.getColumnName(sql);
		List<HashMap<String, String>> gtList = dao.getList(sql+" where sszt = ? ", new String[]{ ztbh }, outPut);

		if(gtList!=null){
			List<HashMap<String, String>> gtxxList = new ArrayList<HashMap<String, String>>();
			for(int i=0;i<gtList.size();i++){    			
				map = (HashMap<String, String>)gtList.get(i);
				String gtbh = map.get("GTBH");
				String sszt = map.get("SSZT");
				String gtr = map.get("GTR");
				String gtsj = map.get("GTSJ");
				sql = " select gtzw from wsbggtb where gtbh = ? ";
				CLOB gtzw = dao.getOneClob(sql, new String[]{ gtbh }, "gtzw");
				String gtzwxx = gtzw.getSubString(1, Integer.parseInt(String.valueOf(gtzw.length())));    	
				map.put("gtzw", gtzwxx);
				map.put("gtbh", gtbh);
				map.put("sszt", sszt);
				map.put("gtr", gtr);
				map.put("gtsj", gtsj);
				map.put("rownum", String.valueOf(i+2));
				gtxxList.add(map);

			}
			request.setAttribute("gtxxList", gtxxList);
		}



		return mapping.findForward("success");
	}
	/** 新闻发布 */
	private ActionForward getNewsFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// --------2010/4/23 luojw -------------
		// String dir = servlet.getServletContext().getRealPath("batEditor/" +
		// Configuration.FILE_UPLOAD_DIR);
		String dir = servlet.getServletContext().getRealPath("WEB-INF/upLoad");
		// String dir = servlet.getServletContext().getRealPath("upload");

		CommanForm dataImp = (CommanForm) form;
		FormFile file = dataImp.getFile();

		if (uploadFile(dataImp)) {
			File f = new File(dir);
			if (!f.exists()) {
				f.mkdir();
			}
			Timestamp date = new Timestamp(System.currentTimeMillis());
			String fName = date.toString().substring(0, 19);
			fName = fName.replaceAll("-", "").replaceAll(" ", "").replaceAll(
					":", "");

			if (file == null) {
				return mapping.findForward("false");
			}
			String fType = file.getFileName().substring(
					file.getFileName().length() - 4,
					file.getFileName().length());
			int size = file.getFileSize();
			InputStream in = file.getInputStream();
			String filePath = dir + "/" + fName + fType;
			OutputStream out = new FileOutputStream(filePath);
			int bytesRead = 0;
			byte[] buffer = new byte[size];
			while ((bytesRead = in.read(buffer, 0, size)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
			out.close();
			in.close();
			request.setAttribute("back", fName + fType);
		}
		return mapping.findForward("success");
	}

	private Boolean uploadFile(CommanForm dataImp)
			throws FileNotFoundException, IOException {
		// --------2010/4/23 luojw -------------
		String dir = servlet.getServletContext().getRealPath(
				"batEditor/WEB-INF/upLoad");
		// String dir = servlet.getServletContext().getRealPath("upload");

		File f = new File(dir);
		if (!f.exists()) {
			f.mkdir();
		}
		Timestamp date = new Timestamp(System.currentTimeMillis());
		String fName = date.toString().substring(0, 19);
		fName = fName.replaceAll("-", "").replaceAll(" ", "").replaceAll(":",
				"");

		FormFile file = dataImp.getFile();
		if (file == null) {
			return false;
		}
		String fType = file.getFileName().substring(
				file.getFileName().length() - 4, file.getFileName().length());
		int size = file.getFileSize();
		InputStream in = file.getInputStream();
		String filePath = dir + "/" + fName + fType;
		OutputStream out = new FileOutputStream(filePath);
		int bytesRead = 0;
		byte[] buffer = new byte[size];
		while ((bytesRead = in.read(buffer, 0, size)) != -1) {
			out.write(buffer, 0, bytesRead);
		}
		out.close();
		in.close();

		return true;
	}
	
	private ActionForward topicList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, int power)
	throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();

		String yhm = session.getAttribute("userName").toString();

		String sql = " select * from view_wsbgztxx w";
		String[] colName = dao.getColumnName(sql);
//		String[] colNameCn = dao.getColumnNameCN(colName, "view_wsbgztxx");
		String query = " where w.dxzyh like  '%!!SplitSignOne!!||"+yhm+"%' or w.fbr = ?";
		List ztList = dao.getList(sql+query, new String[]{ yhm }, colName);
		request.setAttribute("ztList", ztList);
		return mapping.findForward("success");
	}

	private ActionForward topicObject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, int power)
	throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		CommanForm comForm = (CommanForm) form;
		String act = request.getParameter("act");
		String sql = "";
		String[] input = new String[] {};
		String[] output = new String[] {};
		String query = " where 1=1 ";
		boolean ok = false;
		String dxzsx = comForm.getDxzsx();
		String dxzmc = DealString.toGBK(request.getParameter("dxzmc"));
		String dxzms = DealString.toGBK(request.getParameter("dxzms"));

		request.setAttribute("dxzmc", dxzmc);
		request.setAttribute("dxzms", dxzms);

		if(act!=null&&act.equalsIgnoreCase("rscb")){
			String zd = dxzsx.equalsIgnoreCase("group")?"zdm":"szbm";
			String yhxx = zd=="zdm"?"选择的组内没有用户":"选择的部门没有用户";
			String[] rscb = comForm.getRscb();
			if(rscb!=null){
				int len = rscb.length;
				String val = "";
				for(int i=0;i<len;i++){
					val = rscb[i];
					query += ((i==0)?" and ":" or ")+ zd +" = "+ val;
				}
				sql = " select yhm,xm from yhb " + query ;
				input = new String[] { };
				output = new String[] { "yhm", "xm" };
				List yhList = dao.getList(sql, input, output);
				if(yhList.size()!=0) 
					request.setAttribute("yhList", yhList);
				else 
					request.setAttribute("yhxx", yhxx);
			}
		} else {
			HashMap<String, String> map = new HashMap<String, String>();
			String dxz = request.getParameter("dxz");
			if(dxz!=null&&!dxz.equalsIgnoreCase("")){
				String[] dx = dxz.split("!!SplitSignOne!!");
				if(dx!=null){
					int len = dx.length;
					String dxzyh = "";
					List<String> list = new ArrayList<String>();
					for(int i=1;i<len;i++){
						dxzyh += "!!SplitSignOne!!"+dx[i];
						list.add(dx[i]);
					}
					String cjr = session.getAttribute("userName").toString();

					sql = " delete from wsbgdxzb where dxzmc = ? ";
					dao.runUpdate(sql,new String[]{dxzmc});
					sql = " insert into wsbgdxzb(dxzmc,dxzms,dxzyh,cjr) values(?,?,?,?) ";
					ok = dao.runUpdate(sql, new String[]{dxzmc,dxzms,dxzyh,cjr});

					if(ok) {
						request.setAttribute("dxzyhList", list);
						map.put("dxzmc", dxzmc);
						map.put("dxzms", dxzms);
						map.put("cjr", cjr);
						request.setAttribute("rs", map);
					}
				}
			}
		}

		if(dxzsx!=null&&dxzsx.equalsIgnoreCase("group")){
			sql = "select zdm val,zmc lab from yhzb where zmc<>'系统管理员' order by zdm";
			ok = true;
		} else if(dxzsx!=null&&dxzsx.equalsIgnoreCase("bm")){
			sql = "select bmdm val,bmmc lab from ZXBZ_XXBMDM";
			ok = true;
		}

		if(ok){
			input = new String[] {};
			output = new String[] { "val", "lab" };
			List rsList = dao.getList(sql, input, output);
			if(rsList.size()!=0) 
				request.setAttribute("rsList", rsList);
			else 
				request.setAttribute("rsxx", "none");

		}

		String tips = "网上办公 - 信息维护 - 对象组维护";
		request.setAttribute("tips", tips);

		return mapping.findForward("success");
	}

	private ActionForward topicPub(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, int power)
	throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sql = "";
		boolean ok = false;

		String act = request.getParameter("act");
		if(act!=null&&act.equalsIgnoreCase("save")){
			String ztbt = DealString.toGBK(request.getParameter("ztbt"));
			String ztzw = DealString.toGBK(request.getParameter("ztzw"));
			String ztdx = DealString.toGBK(request.getParameter("dxzmc"));
			String ztbh = String.valueOf(System.currentTimeMillis());
			String fbr = session.getAttribute("userName").toString();
			sql = " insert into wsbgztb(ztbt,ztzw,ztdx,ztbh,fbr) values(?,?,?,?,?) ";
			ok = dao.runUpdate(sql, new String[]{ ztbt,ztzw,ztdx,ztbh,fbr });
			if(ok){
				HashMap<String, String> map = new HashMap<String, String>();
				sql = " select ztbt,ztdx,ztbh,fbr,fbsj from wsbgztb where ztbh = ? ";
				String[] output = new String[]{ "ztbt","ztdx","ztbh","fbr","fbsj" };
				String[] outValue = dao.getOneRs(sql, new String[]{ ztbh }, output);
				for(int i=0;i<output.length;i++){
					map.put(output[i], outValue[i]);
				}
				map.put("ztzw", ztzw);
				request.setAttribute("rs", map);
				request.setAttribute("inserted", "ok");
			} else {
				request.setAttribute("inserted", "no");
			}
		}

		sql = " select distinct dxzmc from wsbgdxzb ";
		List dxzList = dao.getList(sql, new String[]{}, new String[]{"dxzmc"});
		request.setAttribute("dxzList", dxzList);
		return mapping.findForward("success");
	}

	private ActionForward addStuInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, int power)
	throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm szkhForm = (CommanForm) form;
		String xydm = szkhForm.getXydm();
		xydm = Base.chgNull(xydm, "%", 0);

		String xh = request.getParameter("xh");
		String bz = Base.chgNull(request.getParameter("bz")," ",1);
		String sql;
		if (power == 0) {
			request.setAttribute("errMsg", "无权做此操作！");
			return new ActionForward("/errMsg.do", false);
		}

		if (Base.isNull(xh)) {
			request.setAttribute("errMsg", "内部错误，请重试！");
			return mapping.findForward("success");
		}
		request.setAttribute("xh",xh);
		if(bz.equalsIgnoreCase(" ")){
			sql = "select count(*) num from xsfzxxb where xh=?";
			bz = dao.getOneRs(sql,new String[]{xh},new String[]{"num"})[0];
			if(bz.equalsIgnoreCase("0")){
				sql = "insert into xsfzxxb(xh,bz) values(?,' ')";
				dao.runUpdate(sql,new String[]{xh});
			}
			sql = "select nvl(bz,' ') bz from xsfzxxb where xh=?";
			bz = dao.getOneRs(sql,new String[]{xh},new String[]{"bz"})[0];
			request.setAttribute("bz",bz);
			return mapping.findForward("success");
		}
		sql = "update xsfzxxb set bz=? where xh=?";
		request.setAttribute("bz",bz);
		boolean res = dao.runUpdate(sql,new String[]{bz,xh});
		if(res){
			request.setAttribute("errMsg", "操作成功！");
		}else{
			request.setAttribute("errMsg", "操作失败！");
		}
		return mapping.findForward("success");
	}

	private ActionForward rychPri(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, int power)
	throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String xh = request.getParameter("xh");
		String rych = request.getParameter("rychdm");
		String xxdm = dao.getXxdm();
		String pkValue = request.getParameter("pkValue");
		if (userType.equalsIgnoreCase("stu")) {
			xh = session.getAttribute("userName").toString();
		}
		String sql = "select jxjsqxn,jxjsqnd from xtszb where rownum=1";
//		String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
//				"jxjsqxn", "jxjsqnd" });
//		String xn = tmp[0];
//		String nd = tmp[1];
//		tmp = dao.getOneRs("select rychmc from rychdmb where rychdm=?",new String[]{rych},new String[]{"rychmc"});
//		String rychmc;
//		if(tmp == null){
//			rychmc = " ";
//		}else{
//			rychmc = tmp[0];
//		}
		sql = "select * from view_xsxxb where xh=?";
		HashMap<String, String> map = dao.getMap(sql, new String[] { StringUtils.isNull(xh) ? "" : xh.trim() },
				new String[] { "xm", "xb", "xymc", "zymc", "bjmc", "csrq", "mzmc", "syd", "zzmmmc" });
		if(xxdm.equalsIgnoreCase(Globals.XXDM_XCXY)){
			map = dao.getMapNotOut("select a.sqsj,a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.rychmc,a.xyyj,a.xxyj,a.xyshsj,a.xxshsj,(select b.drzw from xsrychxxb b where a.xh=b.xh) drzw,b.csrq,b.mzmc,b.syd,b.zzmmmc,(select b.hjqk from xsrychxxb b where a.xh=b.xh) hjqk,(select b.zysj from xsrychxxb b where a.xh=b.xh) zysj from view_xsrychb a left join view_xsxxb b on a.xh=b.xh where a.xh||a.xn||a.xq||a.rychdm=?", new String[]{pkValue});
			if (map == null || map.size()==0) {
				map = dao.getMapNotOut("select a.sqsj,a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.rychmc,a.xyyj,a.xxyj,a.xyshsj,a.xxshsj,(select b.drzw from xsrychxxb b where a.xh=b.xh) drzw,b.csrq,b.mzmc,b.syd,b.zzmmmc,(select b.hjqk from xsrychxxb b where a.xh=b.xh) hjqk,(select b.zysj from xsrychxxb b where a.xh=b.xh) zysj from view_xsrychb a left join view_xsxxb b on a.xh=b.xh where a.xn||a.nd||a.xh||a.rychdm=?", new String[]{pkValue});
				
			}
			String xysj = map.get("xyshsj");
			String xxsj = map.get("xxshsj");
			String sqsj = map.get("sqsj");
			if (!StringUtils.isNull(sqsj)) {
				map.put("sn", sqsj.substring(0, 4));
				map.put("sy", sqsj.substring(4, 6));
				map.put("sr", sqsj.substring(6, 8));
			}
			if (!StringUtils.isNull(xysj)) {
				map.put("xyn", xysj.substring(0, 4));
				map.put("xyy", xysj.substring(4, 6));
				map.put("xyr", xysj.substring(6, 8));
			}
			if (!StringUtils.isNull(xxsj)) {
				map.put("xxn", xxsj.substring(0, 4));
				map.put("xxy", xxsj.substring(4, 6));
				map.put("xxr", xxsj.substring(6, 8));
			}
//		     String rychmc = dao.getOneRs("select rychmc from rychdmb where rychdm = ?",new String[]{rych},"rychmc");
		     String tableType = request.getParameter("tabTyp");	
		     request.setAttribute("xxmc",StandardOperation.getXxmc());
		     if(tableType!=null&&tableType.equalsIgnoreCase("yxbysdjb")){//优秀毕业生登记表
		    	 
		    	  request.setAttribute("map", map);
		    	  return new ActionForward("/pjpy/xcxy/xcxy_yxbysdjb.jsp",false);
		     }else{//优秀毕业生推进审批表		    		 
		    	 
		    	  request.setAttribute("map", map);
		    	  return new ActionForward("/pjpy/xcxy/xcxy_yxbysspb.jsp",false);
		     }		  
		     
		}
		String[] qtxx = new String[] { "xh","drzw","tydbqk","byzx","jtdz","syd","brjl","zysj","hjqk" };
		sql = "select * from xsrychxxb where xh=?";
		String[] qtxxfs = dao.getOneRs(sql, new String[] {xh}, qtxx);
		if(qtxxfs == null){
			qtxxfs = new String[qtxx.length];
		}
		for(int i = 0;i < qtxx.length; i++){
			map.put(qtxx[i],qtxxfs[i]);
		}
		String[] tt = dao.getOneRs("select sjhm,wysp from xsfzxxb where xh=?",new String[]{xh},new String[]{"sjhm","wysp"});
		String sjhm = "";
		String wysp = "";
		if(tt != null && tt.length == 2){
			sjhm = tt[0];
			wysp = tt[1];
		}
		map.put("sjhm", sjhm);
		map.put("wysp", wysp);
		tt = dao.getOneRs("select xyyj,xxyj,zysj from xsrychb where xh=?",new String[]{xh},new String[]{"xyyj","xxyj","zysj"});
		String xyyj = "";
		String xxyj = "";
		if(tt != null && tt.length == 3){
			xyyj = tt[0];
			xxyj = tt[1];
			if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
				map.put("zysj",  tt[2]);
			}
		}
		String xxmc = dao.getOneRs("select xxmc from xtszb where rownum=1", new String[] {}, new String [] {"xxmc"})[0];
		map.put("xyyj", xyyj);
		map.put("xxyj", xxyj);
		map.put("xxmc", xxmc);
		request.setAttribute("map", map);
		if(rych.equalsIgnoreCase(Base.sydm)){
			return new ActionForward("/sqb/syxbys.jsp",false);
		}else if(rych.equalsIgnoreCase(Base.xydm)){
			return new ActionForward("/sqb/xyxbys.jsp",false);
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
			return mapping.findForward("hzyrych");
		}
		return mapping.findForward("success");
	}

	private ActionForward xy_priseConf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, int power)
	throws Exception {
		DAO dao = DAO.getInstance();
//		String userType;

		String userDep;

//		String sUName;

//		String logMsg;

		String writeAble;

		String currXn= dao.getConf(2);

		String currNd=dao.getConf(3);

		String currXq=dao.getConf(6);
		currXq = currXq == null ? "" : currXq;
		HttpSession session = request.getSession();
//		userType = session.getAttribute("userType").toString();
		userDep = session.getAttribute("userDep").toString();

		String sql;
		String pjzq = StandardOperation.getCollegePriseCycle();
		String tmp = "";
		if("xq".equalsIgnoreCase(pjzq)){
			sql = "select count(*) num from xyfpjxjfs where xn=? and nd=? and xqdm=? and bmdm=?";
			tmp = dao.getOneRs(sql,new String[]{currXn,currNd,currXq,userDep},"num");
		} else {
			sql = "select count(*) num from xyfpjxjfs where xn=? and nd=? and bmdm=?";
			tmp = dao.getOneRs(sql,new String[]{currXn,currNd,userDep},"num");
		}		

		String fpfs = request.getParameter("fpfs");
		request.setAttribute("xn", currXn);
		request.setAttribute("nd", currNd);
		sql = "select bmmc from zxbz_xxbmdm where bmdm=?";
		String bmmc = dao.getOneRs(sql, new String[] { userDep },"bmmc");
		request.setAttribute("xymc", bmmc);
		if(tmp.equalsIgnoreCase("0")){
			if(fpfs == null){
				return mapping.findForward("success");
			}
			if("xq".equalsIgnoreCase(pjzq)){//评奖周期为学期
				dao.runProcuder("{call initXyJxjRs_xq(?,?,?,?,?)}",new String[]{currXn,currNd,currXq,userDep,fpfs});
			} else {//评奖周期为学年时
				dao.runProcuder("{call initXyJxjRs(?,?,?,?)}",new String[]{currXn,currNd,userDep,fpfs});
			}
		} else {
			if("xq".equalsIgnoreCase(pjzq)){//评奖周期为学期
				sql = "select fpfs from xyfpjxjfs where xn=? and nd=? and xqdm=? and bmdm=? and rownum=1";
				fpfs = dao.getOneRs(sql,new String[]{currXn,currNd,currXq,userDep},"fpfs");
			} else {//评奖周期为学年时
				sql = "select fpfs from xyfpjxjfs where xn=? and nd=? and bmdm=? and rownum=1";
				fpfs = dao.getOneRs(sql,new String[]{currXn,currNd,userDep},"fpfs");
			}
		}
		request.setAttribute("fpfs",fpfs);
		String[] colList;
		String[] colListCN;
		String jxjdm = Base.chgNull(request.getParameter("xmdm"), "%", 0);
		String nj = Base.chgNull(request.getParameter("nj"), "%", 0);
		String zydm = Base.chgNull(request.getParameter("zydm"), "%", 0);
		String bjdm = Base.chgNull(request.getParameter("bjdm"), "%", 0);
		String[] ipS;
		if (fpfs.equalsIgnoreCase("zy")) {
			colList = new String[] { "主键", "行号", "nj", "bmmc", "jxjmc", "cprs","jsrs",
			"rstz" };
			colListCN = new String[] { "主键", "行号", "年级", "专业名称", "奖学金", "参评人数", "建议人数",
			"人数调整" };
			sql = "select a.*,rownum 行号 from (select xn||nd||nj||fpbm||a.jxjdm 主键,nj,zymc bmmc,jxjmc,cprs,jsrs,rstz from xyjxjfpb a,"
				+ "jxjdmb b,(select distinct zydm,zymc from view_njxyzybj) c where xn=? and nd=? and bmdm=? and "
				+ "a.jxjdm=b.jxjdm and a.fpbm=c.zydm and a.jxjdm like ? and nj like ? and zydm like ? order by jxjmc,nj,zymc) a";
			ipS = new String[] { currXn, currNd, userDep, jxjdm, nj, zydm };
		} else {
			colList = new String[] { "主键", "行号", "nj", "bmmc", "jxjmc", "cprs","jsrs",
			"rstz" };
			colListCN = new String[] { "主键", "行号", "年级", "班级名称", "奖学金", "参评人数", "建议人数",
			"人数调整" };
			sql = "select a.*,rownum 行号 from (select xn||nd||nj||fpbm||a.jxjdm 主键,nj,bjmc bmmc,jxjmc,cprs,jsrs,rstz from xyjxjfpb a,"
				+ "jxjdmb b,(select distinct bjdm,bjmc from view_njxyzybj) c where xn=? and nd=? and bmdm=?"
				+ " and a.jxjdm=b.jxjdm and a.fpbm=c.bjdm and a.jxjdm like ? and nj like ? and bjdm like ? order by jxjmc,nj,bjmc) a";
			ipS = new String[] { currXn, currNd, userDep, jxjdm, nj, bjdm };
		}
		String sqlje = "";
		String jsje = "";
		String tzje = "";
		if ("%".equalsIgnoreCase(jxjdm)) {
			sqlje = "select sum(to_number(jsje)) jsje from xyjxjrs where bmdm=? and bmlb='xydm' and xn=? and nd=?";
			jsje = dao
			.getOneRs(
					sqlje,
					new String[] {userDep,Base.getJxjsqxn(),Base.getJxjsqnd()}, "jsje");
			tzje = dao.getOneRs("select sum(rstz*(select b.jlje from jxjdmb b where a.jxjdm=b.jxjdm)) tzje from xyjxjfpb a where bmdm=? and xn=? and nd=? and rstz is not null", new String[]{userDep,Base.getJxjsqxn(),Base.getJxjsqnd()}, "tzje");
		} else {
			sqlje = "select sum(to_number(jsje)) jsje from xyjxjrs where bmdm=? and bmlb='xydm' and jxjdm=? and xn=? and nd=?";
			jsje = dao
			.getOneRs(
					sqlje,
					new String[] {userDep,jxjdm,Base.getJxjsqxn(),Base.getJxjsqnd()}, "jsje");
			tzje = dao.getOneRs("select sum(rstz*(select b.jlje from jxjdmb b where a.jxjdm=b.jxjdm)) tzje from xyjxjfpb a where bmdm=? and xn=? and nd=? and jxjdm=? and rstz is not null", new String[]{userDep,Base.getJxjsqxn(),Base.getJxjsqnd(),jxjdm}, "tzje");
		}
		//学校设置金额及学院调整金额
		
		request.setAttribute("jsje", jsje);
		
		request.setAttribute("tzje", tzje);
		List topTr = dao.arrayToList(colList, colListCN);
		request.setAttribute("topTr", topTr);
		String rsNum = "0";
		Vector<Object> rs = new Vector<Object>();
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, ipS, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			sql = "select jxjrs from view_xyjxjrs where jxjdm = ? and bmlb = ? and xn = ? and nd = ? and bmdm = ?";
			String [] jxjrs = dao.getOneRs(sql, new String []{jxjdm,"xydm",currXn,currNd,userDep}, new String []{"jxjrs"});
			if(jxjrs==null || jxjrs[0] == null){
				request.setAttribute("jxjrs", "未设置");
			}else{
				request.setAttribute("jxjrs", jxjrs[0]);
			}
			sql = "select sum(rstz) ysrs from xyjxjfpb a where xn=? and nd=? and bmdm=? and a.jxjdm = ?";
			String [] yszjxjrs = dao.getOneRs(sql, new String []{currXn,currNd,userDep,jxjdm}, new String []{"ysrs"});
			if(jxjrs==null || jxjrs[0] == null){
				request.setAttribute("jxjysrs", "");
			}else{
				request.setAttribute("jxjysrs", yszjxjrs[0]);
			}
		}
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
//		String xxmc=request.getSession().getAttribute("xxmc").toString();
		String xxdm = StandardOperation.getXxdm();
		if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLYDX)){
			sql = "select jxjdm,jxjmc from jxjdmb_tmp_bjly";
		}else{
			sql = "select jxjdm,jxjmc from jxjdmb order by jxjdm";
		}
		List jxjList = dao.getList(sql, new String[] {}, new String[] {
				"jxjdm", "jxjmc" });

		zydm = Base.isNull(zydm) ? "all" : zydm;

		String[] rs7 = new String[2];
//		String rs1;
//		String rs2;
//		String rs3;
//		String rs4;
//		String rs5;
//		String rs6;
//		String rs11;
//		String rs21;
//		String rs31;
//		String rs41;
//		String rs51;
//		String rs61;
//		if (userDep.equalsIgnoreCase("410000")) {// 处理竺院
//
//			String sql1 = "select nvl(sum(cprs*jxjbl),'0') rsNum1 from xyjxjrs where bmdm=? " +
//			"and jxjdm=? and xn=? and nd=? and bmlb='zydm'";
//			rs1 = dao.getOneRs(sql1, new String[] { "3027", "0000000001",
//					currXn, currNd }, "rsNum1");
//			rs2 = dao.getOneRs(sql1, new String[] { "3027", "0000000002",
//					currXn, currNd }, "rsNum1");
//			rs3 = dao.getOneRs(sql1, new String[] { "3027", "0000000003",
//					currXn, currNd }, "rsNum1");
//			rs4 = dao.getOneRs(sql1, new String[] { "3027", "0000000009",
//					currXn, currNd }, "rsNum1");
//			rs5 = dao.getOneRs(sql1, new String[] { "3027", "0000000010",
//					currXn, currNd }, "rsNum1");
//			rs6 = dao.getOneRs(sql1, new String[] { "3027", "0000000011",
//					currXn, currNd }, "rsNum1");
//			sql1 = "select nvl(sum(rstz),'0') rsNum1 from xyjxjfpb where fpbm=? and jxjdm=? and xn=? and nd=?";
//			rs11 = dao.getOneRs(sql1, new String[] { "3027", "0000000001",
//					currXn, currNd }, "rsNum1");
//			rs21 = dao.getOneRs(sql1, new String[] { "3027", "0000000002",
//					currXn, currNd }, "rsNum1");
//			rs31 = dao.getOneRs(sql1, new String[] { "3027", "0000000003",
//					currXn, currNd }, "rsNum1");
//			rs41 = dao.getOneRs(sql1, new String[] { "3027", "0000000009",
//					currXn, currNd }, "rsNum1");
//			rs51 = dao.getOneRs(sql1, new String[] { "3027", "0000000010",
//					currXn, currNd }, "rsNum1");
//			rs61 = dao.getOneRs(sql1, new String[] { "3027", "0000000011",
//					currXn, currNd }, "rsNum1");
//
//			sql1 = "select nvl(sum(cprs*jxjbl),'0') rsNum1 from xyjxjrs where bmdm in (select zydm from bks_zydm where bmdm=?) and bmdm <>? " +
//			"and jxjdm=? and xn=? and nd=? and bmlb='zydm'";
//			String rs12 = dao.getOneRs(sql1, new String[] { userDep,"3027", "0000000001",
//					currXn, currNd }, "rsNum1");
//			String rs22 = dao.getOneRs(sql1, new String[] { userDep,"3027", "0000000002",
//					currXn, currNd }, "rsNum1");
//			String rs32 = dao.getOneRs(sql1, new String[] { userDep,"3027", "0000000003",
//					currXn, currNd }, "rsNum1");
//			String rs42 = dao.getOneRs(sql1, new String[] { userDep,"3027", "0000000009",
//					currXn, currNd }, "rsNum1");
//			String rs52 = dao.getOneRs(sql1, new String[] { userDep,"3027", "0000000010",
//					currXn, currNd }, "rsNum1");
//			String rs62 = dao.getOneRs(sql1, new String[] { userDep,"3027", "0000000011",
//					currXn, currNd }, "rsNum1");
//			sql1 = "select nvl(sum(rstz),'0') rsNum1 from xyjxjfpb where bmdm=? and fpbm<>? and jxjdm=? and xn=? and nd=?";
//			String rs13 = dao.getOneRs(sql1, new String[] {  userDep,"3027", "0000000001",
//					currXn, currNd }, "rsNum1");
//			String rs23 = dao.getOneRs(sql1, new String[] {  userDep,"3027", "0000000002",
//					currXn, currNd }, "rsNum1");
//			String rs33 = dao.getOneRs(sql1, new String[] {  userDep,"3027", "0000000003",
//					currXn, currNd }, "rsNum1");
//			String rs43 = dao.getOneRs(sql1, new String[] {  userDep,"3027", "0000000009",
//					currXn, currNd }, "rsNum1");
//			String rs53 = dao.getOneRs(sql1, new String[] {  userDep,"3027", "0000000010",
//					currXn, currNd }, "rsNum1");
//			String rs63 = dao.getOneRs(sql1, new String[] {  userDep,"3027", "0000000011",
//					currXn, currNd }, "rsNum1");
//			String[] rs71 = new String[]{"0","0"};
//			try {
//				rs7[0] = String.valueOf(Float.parseFloat(rs1) * 1500
//						+ Float.parseFloat(rs2) * 1000 + Float.parseFloat(rs3)
//						* 500 + Float.parseFloat(rs4) * 500
//						+ Float.parseFloat(rs5) * 500 + Float.parseFloat(rs6)
//						* 500);
//				rs7[1] = String.valueOf(Float.parseFloat(rs11) * 1500
//						+ Float.parseFloat(rs21) * 1000
//						+ Float.parseFloat(rs31) * 500 + Float.parseFloat(rs41)
//						* 500 + Float.parseFloat(rs51) * 500
//						+ Float.parseFloat(rs61) * 500);
//				if (Float.parseFloat(rs7[0]) - Float.parseFloat(rs7[1]) > 500) {
//					request.setAttribute("bgColor", "#0000FF");
//				} else if (Float.parseFloat(rs7[0]) - Float.parseFloat(rs7[1]) > 0) {
//					request.setAttribute("bgColor", "#000000");
//				} else {
//					request.setAttribute("bgColor", "#FF0000");
//				}
//				rs71[0] = String.valueOf(Float.parseFloat(rs12) * 1500
//						+ Float.parseFloat(rs22) * 1000 + Float.parseFloat(rs32)
//						* 500 + Float.parseFloat(rs42) * 500
//						+ Float.parseFloat(rs52) * 500 + Float.parseFloat(rs62)
//						* 500);
//				rs71[1] = String.valueOf(Float.parseFloat(rs13) * 1500
//						+ Float.parseFloat(rs23) * 1000
//						+ Float.parseFloat(rs33) * 500 + Float.parseFloat(rs43)
//						* 500 + Float.parseFloat(rs53) * 500
//						+ Float.parseFloat(rs63) * 500);
//				if (Float.parseFloat(rs71[0]) - Float.parseFloat(rs71[1]) > 500) {
//					request.setAttribute("bgColor1", "#0000FF");
//				} else if (Float.parseFloat(rs71[0]) - Float.parseFloat(rs71[1]) > 0) {
//					request.setAttribute("bgColor1", "#000000");
//				} else {
//					request.setAttribute("bgColor1", "#FF0000");
//				}
//			} catch (NumberFormatException e) {
//				rs71 = new String[]{"0","0"};
//			}
//			request.setAttribute("rs12", rs12);
//			request.setAttribute("rs22", rs22);
//			request.setAttribute("rs32", rs32);
//			request.setAttribute("rs42", rs42);
//			request.setAttribute("rs52", rs52);
//			request.setAttribute("rs62", rs62);
//			request.setAttribute("rs72", rs71[0]);
//			request.setAttribute("rs13", rs13);
//			request.setAttribute("rs23", rs23);
//			request.setAttribute("rs33", rs33);
//			request.setAttribute("rs43", rs43);
//			request.setAttribute("rs53", rs53);
//			request.setAttribute("rs63", rs63);
//			request.setAttribute("rs73", rs71[1]);
////		}else{
////
////			String sql1 = "select nvl(sum(cprs*jxjbl),'0') rsNum1 from xyjxjrs where bmdm=? and jxjdm=? and xn=? and nd=? and bmlb='xydm'";
////			rs1 = dao.getOneRs(sql1, new String[] { userDep, "0000000001",
////					currXn, currNd },"rsNum1");
////			rs2 = dao.getOneRs(sql1, new String[] { userDep, "0000000002",
////					currXn, currNd },"rsNum1");
////			rs3 = dao.getOneRs(sql1, new String[] { userDep, "0000000003",
////					currXn, currNd },"rsNum1");
////			rs4 = dao.getOneRs(sql1, new String[] { userDep, "0000000009",
////					currXn, currNd },"rsNum1");
////			rs5 = dao.getOneRs(sql1, new String[] { userDep, "0000000010",
////					currXn, currNd },"rsNum1");
////			rs6 = dao.getOneRs(sql1, new String[] { userDep, "0000000011",
////					currXn, currNd },"rsNum1");
////			sql1 = "select nvl(sum(rstz),'0') rsNum1 from xyjxjfpb where bmdm=? and jxjdm=? and xn=? and nd=?";
////			rs11 = dao.getOneRs(sql1, new String[] { userDep, "0000000001",
////					currXn, currNd },"rsNum1");
////			rs21 = dao.getOneRs(sql1, new String[] { userDep, "0000000002",
////					currXn, currNd },"rsNum1");
////			rs31 = dao.getOneRs(sql1, new String[] { userDep, "0000000003",
////					currXn, currNd },"rsNum1");
////			rs41 = dao.getOneRs(sql1, new String[] { userDep, "0000000009",
////					currXn, currNd },"rsNum1");
////			rs51 = dao.getOneRs(sql1, new String[] { userDep, "0000000010",
////					currXn, currNd },"rsNum1");
////			rs61 = dao.getOneRs(sql1, new String[] { userDep, "0000000011",
////					currXn, currNd },"rsNum1");
////			try {
////				rs7[0] = String.valueOf(Float.parseFloat(rs1) * 1500
////						+ Float.parseFloat(rs2) * 1000
////						+ Float.parseFloat(rs3) * 500 + Float.parseFloat(rs4)
////						* 500 + Float.parseFloat(rs5) * 500
////						+ Float.parseFloat(rs6) * 500);
////				rs7[1] = String.valueOf(Float.parseFloat(rs11) * 1500
////						+ Float.parseFloat(rs21) * 1000
////						+ Float.parseFloat(rs31) * 500 + Float.parseFloat(rs41)
////						* 500 + Float.parseFloat(rs51) * 500
////						+ Float.parseFloat(rs61) * 500);
////				if (Float.parseFloat(rs7[0]) - Float.parseFloat(rs7[1]) > 500) {
////					request.setAttribute("bgColor", "#0000FF");
////				} else if (Float.parseFloat(rs7[0]) - Float.parseFloat(rs7[1]) > 0) {
////					request.setAttribute("bgColor", "#000000");
////				} else {
////					request.setAttribute("bgColor", "#FF0000");
////				}
////			} catch (NumberFormatException e) {
////
////			}
////
////		}
//
		sql = "select distinct bjdm,bjmc from view_njxyzybj where xydm=? and nj like ? order by bjdm";
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
		writeAble = CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_conf_rs.do") ? "yes" : "no";
//		request.setAttribute("rs1", rs1);
//		request.setAttribute("rs2", rs2);
//		request.setAttribute("rs3", rs3);
//		request.setAttribute("rs4", rs4);
//		request.setAttribute("rs5", rs5);
//		request.setAttribute("rs6", rs6);
//		request.setAttribute("rs7", rs7[0]);
//		request.setAttribute("rs11", rs11);
//		request.setAttribute("rs21", rs21);
//		request.setAttribute("rs31", rs31);
//		request.setAttribute("rs41", rs41);
//		request.setAttribute("rs51", rs51);
//		request.setAttribute("rs61", rs61);
		request.setAttribute("rs71", rs7[1]);
		request.setAttribute("njList", dao.getNjList());
		request.setAttribute("jxjList", jxjList);
		request.setAttribute("zyList", dao.getZyList(userDep));// 发送专业列表
		request.setAttribute("bjList", dao.getList(sql,new String[]{userDep,nj},new String[]{"bjdm","bjmc"}));
		request.setAttribute("writeAble", writeAble);
		return mapping.findForward("success");
	}

	private ActionForward xy_priseConf_one(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, int power)
	throws Exception {
		DAO dao = DAO.getInstance();
//		String userType;

		String userDep;

//		String sUName;

//		String logMsg;

//		String writeAble;

		String currXn= dao.getConf(2);

		String currNd=dao.getConf(3);

//		String currXq=dao.getConf(6);
		HttpSession session = request.getSession();
//		userType = session.getAttribute("userType").toString();
		userDep = session.getAttribute("userDep").toString();
		String sql;
		String chged = null;
		if(request.getAttribute("chged") != null){
			chged = request.getAttribute("chged").toString();
		}
		if(chged != null){
			request.setAttribute("chged",chged);
		}
		String pk = request.getParameter("pk");
		sql = "select fpfs from xyfpjxjfs where xn=? and nd=? and bmdm=? and rownum=1";
		String fpfs = dao.getOneRs(sql, new String[] { currXn, currNd, userDep }, "fpfs");
		sql = "select nvl(rstz,'0') rstz,jxjdm,nj,fpbm from xyjxjfpb where xn||nd||nj||fpbm||jxjdm=?";
		String[] rs = dao.getOneRs(sql, new String[] { pk }, new String[] {
				"rstz", "jxjdm","nj","fpbm" });
		String jxjrs = rs[0];
		String jxjdm = rs[1];
		String nj = rs[2];
		String fpbm = rs[3];
		String fpbmmc;
		sql = "select jxjmc from jxjdmb where jxjdm=?";
		String jxjmc = dao.getOneRs(sql, new String[] { jxjdm }, "jxjmc");
		String bmmc = "";
		if (fpfs.equalsIgnoreCase("zy")) {
			sql = "select distinct zymc bmmc from view_njxyzybj where zydm=? and rownum=1";
		}else{
			sql = "select distinct bjmc bmmc from view_njxyzybj where bjdm=? and rownum=1";
		}
		fpbmmc = dao.getOneRs(sql,new String[]{fpbm},"bmmc");
		request.setAttribute("jxjdm", jxjdm);
		request.setAttribute("jxjmc", jxjmc);
		request.setAttribute("fpbm", fpbm);
		request.setAttribute("pk", pk);
		request.setAttribute("fpbmmc", fpbmmc);
		request.setAttribute("bmmc", bmmc);
		request.setAttribute("jxjrs", jxjrs);
		request.setAttribute("nj", nj);
		request.setAttribute("xn", currXn);
		request.setAttribute("nd", currNd);
		return mapping.findForward("success");
	}


	private ActionForward syzy_zhszcp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, int power)
	throws Exception {

//		String sql;
//		String chged = null;
		return mapping.findForward("success");
	}
}
