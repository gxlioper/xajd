package xgxt.action;

import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.*;

import xgxt.base.DealString;

import org.apache.struts.action.*;

import common.Globals;

import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.*;
import xgxt.utils.Arrays2;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.xsgygl.dao.gyglDao;
import xgxt.DAO.*;

public class DataAction extends Action {
	//DAO dao = DAO.getInstance();
	String writeAble = "";
	public ActionForward execute(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		CommanForm chkUser = (CommanForm) form;
		try {
			//判断用户读写权
			writeAble = Base.getWriteAble(request);

			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				chkUser.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}
			HttpSession session = request.getSession();
			if (session == null) {
				request.setAttribute("errMsg", "sadfsdf");
				return mapping.findForward("false");
			}
			ActionForward myActFwd = null;
			String userType = session.getAttribute("userType").toString(); //直接获得用户类型，此方式获取的类型为两类：1、"stu"学生2、"teacher"教师
			String myAct = mapping.getParameter();
			if ("modiData".equalsIgnoreCase(myAct)) {
				myActFwd = modiData(mapping, form, request, response);
			} else if(myAct.equalsIgnoreCase("absence")){
				myActFwd = stuAbsence(mapping,form,request,response);
			} else if(myAct.equalsIgnoreCase("bbsqb")){
				myActFwd = bbsqFun(mapping,form,request,response);
			} else if ("zhszcpblsz".equalsIgnoreCase(myAct)){
				myActFwd = zhszcpblsz(mapping, form, request, response);
			} else if ("savablsz".equalsIgnoreCase(myAct)){
				myActFwd = savablsz(mapping, form, request, response);
			} else if ("xjbjsq".equalsIgnoreCase(myAct)){
				myActFwd = xjbjsq(mapping, form, request, response);
			} else if ("requestPage".equalsIgnoreCase(myAct)){
				myActFwd = requestPage(mapping, form, request, response);
			} else if ("gydykp".equalsIgnoreCase(myAct)){                 //[公寓管理] 学生德育考评
				if(userType.equalsIgnoreCase("stu")){//学生无操作权限
					request.setAttribute("errMsg", "学生无权访问该页面！");
				    return new ActionForward("/errMsg.do", false);
				}
				myActFwd = gydykp(mapping, form, request, response);
			} else if ("detailData".equalsIgnoreCase(myAct)){
				myActFwd = detailData(mapping, form, request, response);
			} else if ("modiData3".equalsIgnoreCase(myAct)){
				myActFwd = modiData3(mapping, form, request, response);
			} else if ("wmqspb".equalsIgnoreCase(myAct)){
				if(userType.equalsIgnoreCase("stu")){//学生无操作权限        //[公寓管理]文明寝室评比 
					request.setAttribute("errMsg", "学生无权访问该页面！");
				    return new ActionForward("/errMsg.do", false);
				}
				myActFwd = wmqspb(mapping, form, request, response);
			}
			request.setAttribute("writeAble", writeAble);
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			chkUser.setErrMsg("数据连接中断，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		}
	}


	private ActionForward modiData(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		ActionForward newFwd = new ActionForward();
		CommanForm dataSearchForm = (CommanForm) form;
//		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String pk = request.getParameter("pk");
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String realTable = request.getParameter("realTable");
		String tableName = request.getParameter("tableName");
		String doType = request.getParameter("doType");
		String from = request.getParameter("from");
		String sql = "";
		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			dataSearchForm.setErrMsg("sdf");
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("del")) {
			// 删除数据
			sql = "delete from " + realTable + " where " + pk + "='" + pkValue
			+ "'";
			dao.runUpdate(sql, new String[] {});
			if (realTable.equalsIgnoreCase("knssqb") 
					|| realTable.equalsIgnoreCase("xsjxjzxjsqb")
					|| realTable.equalsIgnoreCase("xpjjzxdksqb")
					|| realTable.equalsIgnoreCase("xsbzb")
					|| realTable.equalsIgnoreCase("wszxjsqb")) {
				newFwd = new ActionForward(
						"/assis_result_t.do?go=go&act=" + from, false);
			} 
		} else if (doType.equalsIgnoreCase("save")) {
			// 保存数据
		} else {
			// 生成页面
			sql = "select * from " + tableName;
			String[] record = null;
			String[] colList = dao.getColumnName(sql);
			sql += (" where " + pk + "='" + pkValue + "'");
			String rec = dao.getStringToSplit(sql, new String[] {}, colList);
			if (rec.equalsIgnoreCase("Error")) {
				record = new String[colList.length];
			} else {
				String[] tmp = rec.split("!!SplitSignOne!!");
				if (tmp.length != 2) {
					record = new String[colList.length + 1];
				} else {
					record = tmp[1].split("!!SplitSignTwo!!");
				}
			}
			if (record.length != colList.length + 1) {
				record = new String[colList.length + 1];
			}
			HashMap<String, String> map = new HashMap<String, String>();
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), record[i + 1]);
			}	
			if(realTable.equalsIgnoreCase("xsjxjzxjsqb")){
				String[] pages = null;
				String[] titNames = null;
				String jxjlbType = request.getParameter("jxjlbType");
				String titName = request.getParameter("titName");

				if (jxjlbType.equalsIgnoreCase("gjjzxj")){///是国家奖助学金
					pages = new String[]{"国家奖学金","国家助学金"};
					titNames = new String[]{"gjjxj","gjzxj"}; 
				} else if (jxjlbType.equalsIgnoreCase("szfjzxj")){
					pages = new String[]{"省政府奖学金","省政府助学金"};
					titNames = new String[]{"szfjxj","szfzxj"};
				}

				if (titName == null )  	titName = titNames[0];

				request.setAttribute("titName", titName);
				List pagesList = dao.arrayToList(titNames, pages);
				request.setAttribute("pages", pagesList);

				HashMap<String, String> map2 = new HashMap<String, String>();
				request.setAttribute("titName", titName);
				request.setAttribute("sfksq", "1");

				if(titName.equalsIgnoreCase("gjjxj")){
					sql = "select * from view_xsjxjzxjsq where 1=2";
					String[] outString = dao.getColumnName(sql);
					sql = "select * from view_xsjxjzxjsq where "+pk+"='"+pkValue+"'";
					String[] outValue = dao.getOneRs(sql, new String[]{}, outString);
					for(int i=0;i<outValue.length;i++){
						if(outValue[i] != null){
							if(outString[i].indexOf("JTCY")<0){
								map2.put(outString[i].toLowerCase(), outValue[i]);
							} else if(outString[i].indexOf("NL")<0){
								map2.put(outString[i], outValue[i]);
							} else {
								map2.put(outString[i].substring(0, 5)+"_nl", outValue[i]);
							}
						} else {
							map2.put(outString[i], "");
						}
					}
					request.setAttribute("rs", map2);
					newFwd = new ActionForward("/xszz/xsjxjzxjsq.jsp",false);
					return newFwd;
				} else if(titName.equalsIgnoreCase("gjzxj")){
					sql = "select * from view_xsjxjzxjsq where 1=2";
					String[] outString = dao.getColumnName(sql);
					sql = "select * from view_xsjxjzxjsq where "+pk+"='"+pkValue+"'";
					String[] outValue = dao.getOneRs(sql, new String[]{}, outString);
					for(int i=0;i<outValue.length;i++){
						if(outValue[i] != null){
							if(outString[i].indexOf("JTCY")<0){
								map2.put(outString[i].toLowerCase(), outValue[i]);
							} else if(outString[i].indexOf("NL")<0){
								map2.put(outString[i], outValue[i]);
							} else {
								map2.put(outString[i].substring(0, 5)+"_nl", outValue[i]);
							}
						} else {
							map2.put(outString[i], "");
						}
					}
					request.setAttribute("rs", map2);
					newFwd = new ActionForward("/xszz/xsjxjzxjsq.jsp",false);
					return newFwd;
				} else if(titName.equalsIgnoreCase("szfjxj")){
					sql = "select * from view_xsjxjzxjsq where 1=2";
					String[] outString = dao.getColumnName(sql);
					sql = "select * from view_xsjxjzxjsq where "+pk+"='"+pkValue+"'";
					String[] outValue = dao.getOneRs(sql, new String[]{}, outString);
					for(int i=0;i<outValue.length;i++){
						if(outValue[i] != null){
							if(outString[i].indexOf("JTCY")<0){
								map2.put(outString[i].toLowerCase(), outValue[i]);
							} else if(outString[i].indexOf("NL")<0){
								map2.put(outString[i], outValue[i]);
							} else {
								map2.put(outString[i].substring(0, 5)+"_nl", outValue[i]);
							}
						} else {
							map2.put(outString[i], "");
						}
					}
					request.setAttribute("rs", map2);
					newFwd = new ActionForward("/xszz/szfjxjzxjsq.jsp",false);
					return newFwd;
				} else if(titName.equalsIgnoreCase("szfzxj")){
					sql = "select * from view_xsjxjzxjsq where 1=2";
					String[] outString = dao.getColumnName(sql);
					sql = "select * from view_xsjxjzxjsq where "+pk+"='"+pkValue+"'";
					String[] outValue = dao.getOneRs(sql, new String[]{}, outString);
					for(int i=0;i<outValue.length;i++){
						if(outValue[i] != null){
							if(outString[i].indexOf("JTCY")<0){
								map2.put(outString[i].toLowerCase(), outValue[i]);
							} else if(outString[i].indexOf("NL")<0){
								map2.put(outString[i], outValue[i]);
							} else {
								map2.put(outString[i].substring(0, 5)+"_nl", outValue[i]);
							}
						} else {
							map2.put(outString[i], "");
						}
					}
					request.setAttribute("rs", map2);
					newFwd = new ActionForward("/xszz/szfjxjzxjsq.jsp",false);
					return newFwd;
				} 
			} else if(realTable.equalsIgnoreCase("xpjjzxdksqb")){
				sql = "select * from view_xpjjzxdksq where 1=2";
				String[] outString = dao.getColumnName(sql);
				sql = "select * from view_xpjjzxdksq where "+pk+"='"+pkValue+"'";
				String[] outValue = dao.getOneRs(sql, new String[]{}, outString);
				for(int i = 0;i < outValue.length; i++){
					if(outValue[i] != null){
						map.put(outString[i].toLowerCase(), outValue[i]);
					} else map.put(outString[i].toLowerCase(), "");
				}
				request.setAttribute("rs", map);
				request.setAttribute("sfksq", "true");
				request.setAttribute("titName", realTable);
				newFwd = new ActionForward("/xszz/xpjjsq.jsp",false);
				return newFwd;
			} else if(realTable.equalsIgnoreCase("xsbzb")){
				String bzlb = request.getParameter("bzlb");
				sql = "select * from view_xsbzxx where 1=2";
				String[] outString = dao.getColumnName(sql);
				sql = "select * from view_xsbzxx where "+pk+"='"+pkValue+"'";
				String[] outValue = dao.getOneRs(sql, new String[]{}, outString);
				for(int i = 0;i < outValue.length; i++){
					if(outValue[i] != null){
						map.put(outString[i].toLowerCase(), outValue[i]);
					} else map.put(outString[i].toLowerCase(), "");
				}
				request.setAttribute("rs", map);
				request.setAttribute("sfksq", "true");
				request.setAttribute("titName", realTable);
				if(bzlb.equalsIgnoreCase("xfbz")){
					request.setAttribute("tips", "当前所在位置：学生资助 - 申请 - 学费补助");
					request.setAttribute("aa", "aa");
					newFwd = new ActionForward("/xszz/xfbz.jsp",false);
				} else if(bzlb.equalsIgnoreCase("lsknbz")){
					request.setAttribute("tips", "当前所在位置：学生资助 - 申请 - 临时困难补助");
					request.setAttribute("bzlb", "lsknbz");
					request.setAttribute("aa", "aa");
					request.setAttribute("sfksq", true);
					newFwd = new ActionForward("/xszz/knbz.jsp",false);
				} else if(bzlb.equalsIgnoreCase("xndxj")){
					request.setAttribute("tips", "当前所在位置：学生资助 - 申请 - 校内贷学金");
					request.setAttribute("bzlb", "xndxj");
					request.setAttribute("sfksq", "yes");
					newFwd = new ActionForward("/xszz/xndxjSq.jsp",false);
				}

				return newFwd;
			} else if(realTable.equalsIgnoreCase("wszxjsqb")){
				String wszxjdm = DealString.toGBK(request.getParameter("wszxjdm"));
				String wszxjmc = "";
				sql = "select zxjdm wszxjdm from wszxjdmb where zxjmc=?";
				if(wszxjdm == null || wszxjdm == ""){
					wszxjmc = DealString.toGBK(request.getParameter("wszxjmc").trim());
					wszxjdm  = dao.getOneRs(sql, new String[]{ wszxjmc }, new String[]{"wszxjdm"})[0];
				}
				sql = "select * from VIEW_XSWSZXJSQXX where 1=2";
				String[] outString = dao.getColumnName(sql);
				sql = "select * from VIEW_XSWSZXJSQXX where "+pk+"='"+pkValue+"'";
				String[] outValue = dao.getOneRs(sql, new String[]{}, outString);
				for(int i = 0;i < outValue.length; i++){
					if(outValue[i] != null){
						map.put(outString[i].toLowerCase(), outValue[i]);
					} else map.put(outString[i].toLowerCase(), "");
				}
				sql = "select zxjdm wszxjdm,zxjmc wszxjmc from wszxjdmb";
				List wszxjList = dao.getList(sql, new String[]{}, new String[]{"wszxjdm","wszxjmc"});
				request.setAttribute("wszxjList", wszxjList);

				request.setAttribute("wszxjdm", wszxjdm);
				request.setAttribute("rs", map);
				request.setAttribute("sfksq", "true");
				request.setAttribute("titName", realTable);
				request.setAttribute("tips", "当前所在位置：学生资助 - 申请 - 外设助学金");
				newFwd = new ActionForward("/xszz/wszxjsq.jsp",false);
				return newFwd;
			}

			String a = request.getQueryString();
			String b = request.getRequestURL().toString();
			b = b.substring(b.lastIndexOf('/'), b.length());
			b = b + "?" + a;
			request.setAttribute("url", b);
			request.setAttribute("pkValue", pkValue);// 发送表主键
			request.setAttribute("njList", Base.getNjList());// 发送年级列表
			request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
			request.setAttribute("xqList", Base.getXqList());// 发送学期列表
			request.setAttribute("rs", map);
			request.setAttribute("doType", doType);
			newFwd = new ActionForward("/sjcz/" + realTable + ".jsp", false);
		}

		return newFwd;
	}

	private ActionForward stuAbsence(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
//		HashMap<String, String> map = new HashMap<String, String>();
		Vector<String> vector = new Vector<String>();
		String xh = session.getAttribute("userName").toString();
		String[] outString = new String[]{"xh","xm","xn","xq","kkrq","kkkc","kkjs","cljg"};
		String sql = "select xh,xm,xn,xq,kkrq,kkkc,kkjs,cljg from view_kkqkxx where xh=?";
		List rs = dao.getVectorList(sql, new String[]{ xh }, outString);
		if(rs != null){
			request.setAttribute("rs", rs); 
		}
		List<String> outStringList = Arrays.asList(dao.getColumnNameCN(outString, "view_kkqkxx"));
		vector.addAll(outStringList);
		request.setAttribute("title", vector);
		return mapping.findForward("success");
	}

	private ActionForward bbsqFun(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm myForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userOnLine").toString();
		String bblx = request.getParameter("bblx");//补办类型
		String doType = request.getParameter("doType");
		String sql = "";
		String[] cols = new String[]{};
		String[] rsVals = new String[]{};
		HashMap<String, String> map = new HashMap<String, String>();
		List<HashMap<String, String>> pages = new ArrayList<HashMap<String, String>>();
		String[] pages_en = {"xsz","ykt","hcpyh"};//学生证、一卡通、火车票优惠
		String[] pages_cn = {"学生证","一卡通","火车票优惠"};
		if((bblx == null) || bblx.equals("")){
			bblx = pages_en[0];
		}
		for(int i=0;i<pages_en.length;i++){
			HashMap<String, String> temmap = new HashMap<String, String>();
			temmap.put("en", pages_en[i]);
			temmap.put("cn", pages_cn[i]);
			pages.add(temmap);
		}
		if(doType == null || doType.trim().length()<1){
			if (userType.equalsIgnoreCase("student")){//学生用户
				String xh = session.getAttribute("userName").toString();
				if(bblx.equalsIgnoreCase("xsz")){
					sql = "select * from view_xszbbsq where xh=?";
					cols = dao.getColumnName(sql);
					rsVals = dao.getOneRs(sql, new String []{xh}, cols);
				} else if(bblx.equalsIgnoreCase("ykt")){
					sql = "select * from view_yktbbsq where xh=?";
					cols = dao.getColumnName(sql);
					rsVals = dao.getOneRs(sql, new String []{xh}, cols);
				} else if(bblx.equalsIgnoreCase("hcpyh")){
					sql = "select * from view_hcpyhbbsq  where xh=?";
					cols = dao.getColumnName(sql);
					rsVals = dao.getOneRs(sql, new String []{xh}, cols);
				}
				for(int i=0;i<cols.length;i++){
					map.put(cols[i], (rsVals[i]==null ? "" : rsVals[i]));
				}

			} else {//教师用户
				String xh = request.getParameter("xh");
				if(xh == null) xh="";
				String maxSqsj = "";
				if(bblx.equalsIgnoreCase("xsz")){
					maxSqsj = dao.getOneRs("select max(sqsj) sqsj from view_xszbbsq where xh='"+xh+"'", new String[]{}, "sqsj");
					if(maxSqsj == null ||maxSqsj.equals("")){
						sql = "select * from view_xszbbsq a where xh=?";
					} else {
						sql = "select * from view_xszbbsq a where xh=? and sqsj=(select max(sqsj) from view_xszbbsq b where a.xh=b.xh)";  
					}    				  
					cols = dao.getColumnName("select * from view_xszbbsq");
					Arrays2.toLower(cols);
					rsVals = dao.getOneRs(sql, new String[]{xh}, cols);

				} else if(bblx.equalsIgnoreCase("ykt")){
					maxSqsj = dao.getOneRs("select max(sqsj) sqsj from view_yktbbsq where xh='"+xh+"'", new String[]{}, "sqsj");
					if(maxSqsj == null ||maxSqsj.equals("")){
						sql = "select * from view_yktbbsq a where xh=?";
					} else {
						sql = "select * from view_yktbbsq a where xh=? and sqsj=(select max(sqsj) from view_yktbbsq b where a.xh=b.xh)";  
					}   

					cols = dao.getColumnName("select * from view_yktbbsq");
					Arrays2.toLower(cols);
					rsVals = dao.getOneRs(sql, new String[]{xh}, cols);
				} else if(bblx.equalsIgnoreCase("hcpyh")){

					maxSqsj = dao.getOneRs("select max(sqsj) sqsj from view_hcpyhbbsq where xh='"+xh+"'", new String[]{}, "sqsj");
					if(maxSqsj == null ||maxSqsj.equals("")){
						sql = "select * from view_hcpyhbbsq a where xh=?";
					} else {
						sql = "select * from view_hcpyhbbsq a where xh=? and sqsj=(select max(sqsj) from view_hcpyhbbsq b where a.xh=b.xh)";  
					}

					cols = dao.getColumnName("select * from view_hcpyhbbsq");
					Arrays2.toLower(cols);
					rsVals = dao.getOneRs(sql, new String[]{xh}, cols);
				}
				if(rsVals == null){
					rsVals = new String[cols.length];
				}
				for(int i=0;i<cols.length;i++){
					map.put(cols[i], (rsVals[i]==null ? " " : rsVals[i]));
				}
			}
		} else if(doType.equalsIgnoreCase("save")){
			String xh = myForm.getXh();
			if(bblx.equalsIgnoreCase("xsz")){
				String rxsj = myForm.getRxsj();
				String jtdz = DealString.toGBK(myForm.getJtdz());
				String sqly = DealString.toGBK(myForm.getSqly());
				String jg = DealString.toGBK(myForm.getJg());

				sql = "insert into xszbbsqb(XH,JG,RXSJ,JTDZ,SQLY) values(?,?,?,?,?)";
				boolean dors = dao.runUpdate(sql, new String[]{xh,jg,rxsj,jtdz,sqly});
				request.setAttribute("dors", dors);
			} else if(bblx.equalsIgnoreCase("ykt")){
				String  lxfs = myForm.getLxfs();
				String  zjh = myForm.getZjh();
				String  gsqk = myForm.getGsqk();
				String  blqk= myForm.getBlqk();
				String  slr= DealString.toGBK(myForm.getSlr());
				String  slrgh = myForm.getSlrgh();
				String  gzbc = DealString.toGBK(myForm.getGzbc());
				String  slbh = myForm.getSlbh();
				String  bbfys = myForm.getBbfys();
				String  xkbl = myForm.getXkbl();
				String  xxyhd = myForm.getXxyhd();
				String  yclhzjxgbm = myForm.getYclhzjxgbm();
				String  bjf = myForm.getBjf();
				String  yy = DealString.toGBK(myForm.getYy());
				String  dlr = DealString.toGBK(myForm.getDlr());
				String  dlrzjh = myForm.getZjh();
				String  yktbz = DealString.toGBK(myForm.getYktbz());
				String  bbyy = DealString.toGBK(myForm.getBbyy());
				String xbyy = DealString.toGBK(myForm.getXbyy());
				sql = "insert into yktbbsqb( xh,lxfs,zjh,gsqk, blqk, slr, slrgh, gzbc, slbh, bbfys, xkbl, xxyhd, yclhzjxgbm, bjf, yy, dlr, dlrzjh, yktbz,bbyy,xbyy) " +
				"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				boolean dors =
					dao.runUpdate(sql, new String[]{ xh,lxfs,zjh,gsqk, blqk, slr, slrgh, gzbc, slbh, bbfys, xkbl, xxyhd, yclhzjxgbm, bjf, yy, dlr, dlrzjh, yktbz,bbyy,xbyy});
				System.out.println(Arrays.asList(new String[]{ xh,lxfs,zjh,gsqk, blqk, slr, slrgh, gzbc, slbh, bbfys, xkbl, xxyhd, yclhzjxgbm, bjf, yy, dlr, dlrzjh, yktbz,bbyy,xbyy}));
				request.setAttribute("dors", dors);
			} else if(bblx.equalsIgnoreCase("hcpyh")){
				String hczdz = DealString.toGBK(myForm.getHczdz());
				String jtdz = DealString.toGBK(myForm.getJtdz());
				String sqly = DealString.toGBK(myForm.getSqly());
				String hdzj = DealString.toGBK(myForm.getHdzj());
				String yzxtzhd = DealString.toGBK(myForm.getYzxtzhd());
				String ff = myForm.getFf();
				String qt = myForm.getQt();
				String hcpbz = myForm.getHcpbz();
				sql = "insert into hcpyhbbsqb( xh, hczdz, jtdz, sqly, hdzj, yzxtzhd, ff, qt, hcpbz) values( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				boolean dors = dao.runUpdate(sql, new String[]{xh, hczdz, jtdz, sqly, hdzj, yzxtzhd, ff, qt, hcpbz});
				request.setAttribute("dors", dors);
			}
		}
		request.setAttribute("rs", map);
		request.setAttribute("pages", pages);
		request.setAttribute("bblx", bblx);
		return mapping.findForward("success");
	}

	
	private ActionForward zhszcpblsz(ActionMapping mapping,ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> map1 = new HashMap<String, String>();

		sql = "select * from zhcpfblszb where sfzds='0'";
		String tag = dao.returntag(sql, new String [] {});
		if ("empty".equalsIgnoreCase(tag)){
			sql = "insert into zhcpfblszb (XYDYKPF,GYDYKPF,ZYCJ,TYCJ,GZXXCX,SFZDS) values('0','0','0','0','0','0')";
			dao.runUpdate(sql, new String[] {});
		}
		sql = "select * from zhcpfblszb where sfzds='1'";
		tag = dao.returntag(sql, new String [] {});
		if ("empty".equalsIgnoreCase(tag)){
			sql = "insert into zhcpfblszb (XYDYKPF,GYDYKPF,ZYCJ,TYCJ,GZXXCX,SFZDS) values('0','0','0','0','0','1')";
			dao.runUpdate(sql, new String[] {});
		}

		sql = "select * from zhcpfblszb where sfzds='0'";
		map = dao.getMap(sql, new String[] {}, new String[]{"XYDYKPF","GYDYKPF","ZYCJ","TYCJ","GZXXCX","SFZDS"});
		sql = "select XYDYKPF XYDYKPF1,GYDYKPF GYDYKPF1,ZYCJ ZYCJ1,TYCJ TYCJ1,GZXXCX GZXXCX1,SFZDS SFZDS1 from zhcpfblszb where sfzds='1'";
		map1 = dao.getMap(sql, new String[] {}, new String[]{"XYDYKPF1","GYDYKPF1","ZYCJ1","TYCJ1","GZXXCX1","SFZDS1"});

		request.setAttribute("rs", map);
		request.setAttribute("rs1", map1);
		return mapping.findForward("success");
	}
	
	private ActionForward savablsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "";
		String XYDYKPF = request.getParameter("XYDYKPF");
		String GYDYKPF = request.getParameter("GYDYKPF");
		String ZYCJ = request.getParameter("ZYCJ");
		String TYCJ = request.getParameter("TYCJ");
		String GZXXCX = request.getParameter("GZXXCX");
		String SFZDS = request.getParameter("SFZDS");
		String XYDYKPF1 = request.getParameter("XYDYKPF1");
		String GYDYKPF1 = request.getParameter("GYDYKPF1");
		String ZYCJ1 = request.getParameter("ZYCJ1");
		String TYCJ1 = request.getParameter("TYCJ1");
		String GZXXCX1 = request.getParameter("GZXXCX1");
		String SFZDS1 = request.getParameter("SFZDS1");

		sql = "update zhcpfblszb set XYDYKPF=?,GYDYKPF=?,ZYCJ=?,TYCJ=?,GZXXCX=? where SFZDS=?";
		dao.runUpdate(sql, new String [] {XYDYKPF,GYDYKPF,ZYCJ,TYCJ,GZXXCX,SFZDS});
		dao.runUpdate(sql, new String [] {XYDYKPF1,GYDYKPF1,ZYCJ1,TYCJ1,GZXXCX1,SFZDS1});
		request.setAttribute("message", "设置成功");
		request.setAttribute("result", "view");
		return mapping.findForward("success");
	}
	
	private ActionForward xjbjsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws   Exception{
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userOnLine").toString();
		String sql = "";
		CommanForm applyForm = (CommanForm) form;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = applyForm.getXh();
		String xxdm = StandardOperation.getXxdm();
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
			request.setAttribute("showhzy", "showhzy");
		}
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		}
		sql = "select * from view_xsjbxx where xh=?";
		String[] colList = dao
		.getColumnName("select * from view_xsjbxx where 1=2");
		String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]);
			}
		}
		sql = "select * from rychdmb";
		List rychList = dao.getList(sql, new String[] {}, new String[] {
				"rychdm", "rychmc" });
		request.setAttribute("rychList", rychList);
		sql = "select jxjsqxn,jxjsqnd from xtszb where rownum=1";
		String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
				"jxjsqxn", "jxjsqnd" });
		map.put("xn", tmp[0]);
		map.put("nd", tmp[1]);
		map.put("stuExists", "yes");
		map.put("userType", userType);
		String[] qtxx = new String[] { "xh","drzw","tydbqk","byzx","jtdz","syd","brjl","zysj","hjqk"};
		sql = "select * from xsrychxxb where xh=?";
		String[] qtxxfs = dao.getOneRs(sql, new String[] {xh}, qtxx);
		if(qtxxfs == null){
			qtxxfs = new String[qtxx.length];
		}
		for(int i = 1;i < qtxx.length; i++){
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
		request.setAttribute("rs",map);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}
	
	private ActionForward requestPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		DAO dao = DAO.getInstance();
		CommanForm dataSearchForm = (CommanForm) form;
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		String nj = dataSearchForm.getNj();
		String xy = dataSearchForm.getXydm();
		String zy = dataSearchForm.getZydm();
		if (xy == null){
			xy = "";
		}
		if (zy == null){
			zy = "";
		}
		sql = "select jxdm,jxmc from jxjxdmb";
		List xmdmList = dao.getList(sql, new String[] {}, new String[] {
				"jxdm", "jxmc" });
		map.put("nj", nj);
		map.put("xydm", xy);
		map.put("zydm", zy);
		request.setAttribute("jxList", xmdmList);
		getCommList(request, dao, nj, xy, zy);
		request.setAttribute("doType",
				(request.getParameter("doType") == null) ? " " : request
						.getParameter("doType"));
		request.setAttribute("pkValue",
				(request.getParameter("pkValue") == null) ? " " : request
						.getParameter("pkValue"));
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward gydykp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException{
		CommanForm dataSearchForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userName     = session.getAttribute("userName").toString();
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String[] colList = null;
		//String[] colListCN = null;
		String sql = "";// sql语句
		StringBuffer sqlV = new StringBuffer();
		String rsNum = "";
		//String pk = "xh";
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String tips = "公寓管理 - 信息维护 - 住宿信息维护";
		String lddm = dataSearchForm.getLddm();
		String qsh = dataSearchForm.getQsh();
		String nj = dataSearchForm.getNj();
		String xydm = dataSearchForm.getXydm();
		String zydm = dataSearchForm.getZydm();
		String bjdm = dataSearchForm.getBjdm();
		String xh = dataSearchForm.getXh();
		String xm = DealString.toGBK(dataSearchForm.getXm());
		String lc = dataSearchForm.getLc();
		dataSearchForm.setXm(xm);
	// String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
//		String userName = session.getAttribute("userName").toString();
		String xxdm = StandardOperation.getXxdm();
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
			request.setAttribute("showhzy","showhzy");
		}		
		//公寓辅导员判断begin			
//		String lddmStr = gyglDao.getLddmxXx(userName);
//		String isGyFdy = "no";
//		if(!Base.isNull(lddmStr)){
//			lddm = lddmStr; 
//			dataSearchForm.setLddm(lddm);
//			isGyFdy = "yes";					
//		}
//		request.setAttribute("isGyFdy",isGyFdy);
		//公寓辅导员判断end					
		sql = "select lddm,ldmc from sslddmb order by  lddm ";
		List ldList = dao.getList(sql, new String[] {}, new String[] { "lddm",
				"ldmc" });
		request.setAttribute("ldList", ldList);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			querry.append((Base.isNull(xh)) ? " and 1=1 " : " and xh = '" + xh+ "'");
			querry.append((Base.isNull(nj)) ? " and 1=1 " : " and nj = '" + nj+ "'");
			querry.append((Base.isNull(xm)) ? " and 1=1 " : " and xm like '%" + xm+ "%'");
			querry.append((Base.isNull(xydm)) ? " and 1=1 " : " and xydm = '"+ xydm + "'");
			querry.append((Base.isNull(zydm)) ? " and 1=1 " : " and zydm = '"+ zydm + "'");
			querry.append((Base.isNull(bjdm)) ? " and 1=1 " : " and bjdm = '"+ bjdm + "'");
			querry.append(Base.isNull(lddm) ? " and 1=1 " : " and lddm = '" + lddm+ "' ");
			querry.append(Base.isNull(qsh)||"qsh".equalsIgnoreCase(qsh) ? " and 1=1 " : " and qsh = '" + qsh+ "' ");
			querry.append(Base.isNull(lc)||"lc".equalsIgnoreCase(lc)?"":" and cs ='"+lc+"' ");
			querry.append(" and (sfyby <> '是'  or sfyby is null) ");
			
			if(session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")){
				//辅导员登录
				querry.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='" + userName + "')");
			}	

			
			colList = new String[] { "xh", "xm", "xb", "bjmc","ldmc","qsh","qsdh","zf" };
			sqlV.append(" select xh,xm,xb,bjdm,bjmc,lddm,zydm,xydm,nj,qsh,ldmc,qsdh,xn,xq,zf,cs from ( select a.xh,a.xm,a.xb,a.bjdm,a.bjmc,a.zydm,a.xydm,a.nj, a.sfyby,");
			sqlV.append(" (select c.lddm from xszsxxb b,ssxxb c where b.ssbh=c.ssbh and a.xh=b.xh)lddm, (select c.qsh from xszsxxb b,ssxxb c where b.ssbh=c.ssbh and a.xh=b.xh)qsh,");		
			sqlV.append(" (select cs from xszsxxb b,ssxxb c where  b.ssbh=c.ssbh and a.xh=b.xh)cs,(select ldmc from xszsxxb b,ssxxb c,sslddmb d  where c.lddm=d.lddm and b.ssbh=c.ssbh and a.xh=b.xh)ldmc, ");
			sqlV.append(" (select c.qsdh from xszsxxb b,ssxxb c where b.ssbh=c.ssbh and a.xh=b.xh)qsdh,b.xn,b.xq,nvl(80+jf-kf,'80') zf ");
			sqlV.append(" from view_xsxxb a left join ( select a.xh,a.xn,a.xq,a.jf,b.kf  from(select xh,xn,xq,nvl(sum (ryjf),'0' ) jf from xsjlb a group by xh,xn,xq) a,(select xh,xn,xq,nvl(sum (rykf),'0' ) kf ");
			sqlV.append(" from xscfb group by xh,xn,xq) b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq union select xh,xn,xq,nvl(sum (ryjf),'0' ) jf,0 kf from  ");
			sqlV.append(" xsjlb a where not exists(select 1 from xscfb b where a.xh=b.xh and  a.xn=b.xn and a.xq=b.xq) group by xh,xn,xq union select xh,xn,xq,0 jf,");
			sqlV.append(" nvl(sum (rykf),'0' ) kf from xscfb a where not exists(select 1 from xsjlb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) group by xh,xn,xq ");
			sqlV.append(" )b on a.xh=b.xh  and xn=? and xq=?  order by zf desc ) ").append(querry);

			rs.addAll(dao.rsToVator(sqlV.toString(), new String[] {Base.currXn,Base.currXq}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		gyglDao.getLdLcQshList(request);
		getCommList(request, dao, nj, xydm, zydm);
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		//request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		request.setAttribute("xxdm", xxdm);
		return mapping.findForward("success");
	}

	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<获取公共列表>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void getCommList(HttpServletRequest request, DAO dao, String nj, String xy, String zy) {
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		nj = nj==null ? "":nj;
		String bjKey = xy+"!!"+zy+"!!"+nj;
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xqList", Base.getXqList());// 发送学期列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));// 发送专业列表
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));// 发送班级列表
		if(session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")){
			//辅导员登录
			request.setAttribute("bjList", Fdypd.getFdybjList (userName));// 发送班级列表
			request.setAttribute("zyList", Fdypd.getFdyZyList (userName));// 发送班级列表
		}			
	}

	private ActionForward detailData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "";
		ActionForward myFwd = new ActionForward();
		String [] colList = null;
		String doType = request.getParameter("act");
		String pk = request.getParameter("pk");
		String pkValueA = request.getParameter("pkValueA");
		String tabName = request.getParameter("pkValueB");
		String xxdm = StandardOperation.getXxdm();
		String userType = "xx";
		Boolean done = false;
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
			request.setAttribute("showhzy", "showhzy");	
		}
		String xh = request.getParameter("xh");//信息添加时，获得所选学号相关信息	
		if(xh!=null&&!xh.equalsIgnoreCase("")){
			String[] colListV = new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","lddm","qsh"};
			String[] rsV = dao.getOneRs("select xh,xm,xb,nj,xymc,zymc,bjmc,lddm,qsh from view_xszsxx where xh=?", new String[] { xh }, colListV);
			if (rsV != null) {
				for (int i = 0; i < colListV.length; i++) {
					map.put(colListV[i], rsV[i]);
				}
			}			
		}			
		if(doType.equalsIgnoreCase("add")){//添加时默认系统设置年月
			map.put("xn",Base.currXn);
			map.put("xq",Base.currXq);
		}else if(doType.equalsIgnoreCase("modi")
				||doType.equalsIgnoreCase("view")
				||doType.equalsIgnoreCase("save")){
			if(!Base.isNull(tabName)){
				if(tabName.equalsIgnoreCase("xsjlb")){//学生奖励信息
					if(doType.equalsIgnoreCase("save")){//保存修改后信息
						String jlnr = DealString.toGBK(request.getParameter("jlnr"));
						String ryjf = DealString.toGBK(request.getParameter("ryjf"));
						String bz = DealString.toGBK(request.getParameter("bz"));						
						done = StandardOperation.update("xsjlb", new String[]{"jlnr","ryjf","bz"},new String[]{jlnr,ryjf,bz},pk, pkValueA, request);
					    if(done){
					    	request.setAttribute("done", "ok");
					    }else{
					    	request.setAttribute("done", "no");
					    }
					}
					//页面信息显示
					sql = "select xh,xm,xb,nj,xymc,bjmc,zymc,xn,xqmc xq,ldmc,qsh,substr(sj,1,8)sj,jlnr,ryjf,bz from view_xsjlb where " + pk + "= '" + pkValueA + "' ";
					colList = dao.getColumnName(sql);
					for(int i=0;i<colList.length;i++){
						colList[i] = colList[i].toLowerCase();
					}
					map = dao.getMap(sql, new String[] {}, colList);
					sql = "select jldm,jlmc from xsjldmb";
					List xsjlList = dao.getList(sql, new String[]{},new String[]{"jldm","jlmc"});
					
					request.setAttribute("xsjlList", xsjlList);					
					request.setAttribute("pk",pk);
					request.setAttribute("pkValueA", pkValueA);
					request.setAttribute("tabName", tabName);
					request.setAttribute("doType",doType);
					request.setAttribute("rs", map);
					return new ActionForward("/gygl/xsjlb.jsp");
				}else if(!Base.isNull(tabName)||tabName.equalsIgnoreCase("xscfb")){//学生惩罚信息
					if(doType.equalsIgnoreCase("save")){//保存修改后信息
						String cfnr = DealString.toGBK(request.getParameter("cfnr"));
						String rykf = DealString.toGBK(request.getParameter("rykf"));
						String bz = DealString.toGBK(request.getParameter("bz"));						
						done = StandardOperation.update("xscfb", new String[]{"cfnr","rykf","bz"},new String[]{cfnr,rykf,bz},pk, pkValueA, request);
					    if(done){
					    	request.setAttribute("done", "ok");
					    }else{
					    	request.setAttribute("done", "no");
					    }
					}
//					页面信息显示
					sql = "select xh,xm,xb,nj,xymc,bjmc,zymc,xn,xqmc xq,ldmc,qsh,substr(sj,1,8)sj,cfnr,rykf,bz from view_xscfb where " + pk + "= '" + pkValueA + "' ";
					colList = dao.getColumnName(sql);
					for(int i=0;i<colList.length;i++){
						colList[i] = colList[i].toLowerCase();
					}
					map = dao.getMap(sql, new String[] {}, colList);
					sql = "select cfdm,cfmc from xscfdmb";
					List xscfList = dao.getList(sql, new String[]{},new String[]{"cfdm","cfmc"});
					request.setAttribute("xscfList", xscfList);	
					request.setAttribute("pk",pk);
					request.setAttribute("pkValueA", pkValueA);
					request.setAttribute("tabName", tabName);
					request.setAttribute("doType",doType);
					request.setAttribute("rs", map);					
					return new ActionForward("/gygl/xscfb.jsp");
				}
			}
		}else if(doType.equalsIgnoreCase("del")){
			StandardOperation.delete(tabName, pk, pkValueA, request);
			return new ActionForward("/stu_gydykp_info.do?Pkxh="+xh);
		}
		
		request.setAttribute("userType", userType);
		request.setAttribute("pkValue", pkValueA);
		request.setAttribute("doType", doType);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("tabName", tabName);
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("rs", map);
		myFwd = new ActionForward("/gygl/xsjlcfxx_add.jsp", false);
		return myFwd;
	}

	private ActionForward modiData3(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		DAO dao = DAO.getInstance();
		CommanForm comForm = (CommanForm) form;
		String xh = comForm.getXh();
		String xn = comForm.getXn();
		String xq = comForm.getXq();
		String lddm = comForm.getLddm();
		String qsh = comForm.getQsh();
		String ssbh = lddm + '-' + qsh;
		boolean del = false;
		String jcs = request.getParameter("jcs");//提交的奖惩信息记录数	   	    
		String[] myJc = new String[]{"jc","sj","xf","nr","bz"}; 
		for(int i=1;i<=Integer.parseInt(jcs);i++){//循环操作数据       
			String jc = request.getParameter(myJc[0]+i);
			String sj = request.getParameter(myJc[1]+i);
			String jcf = request.getParameter(myJc[2]+i);
			String nr =DealString.toGBK(request.getParameter(myJc[3]+i));
			String bz = DealString.toGBK(request.getParameter(myJc[4]+i));	    	
			String xnId = dao.getOneRs("select to_char(sysdate,'misssss') hms from dual ",new String[]{},"hms");//关键字SJ"+"xnId(同一天内可有多条奖惩记录)	    	
			if("jl".equalsIgnoreCase(jc)&&sj.length()>=8){//操作学生奖励信息数据	    			    		
				del = StandardOperation.delete("xsjlb", new String[]{"xh","xn","xq","sj"}, new String [] {xh,xn,xq,qsh,sj+xnId}, request);
				if(del){	    			
					StandardOperation.insert("xsjlb", new String[]{"xh","xn","xq","qsh","sj","ssbh","jlnr","ryjf","bz"}, new String [] {xh,xn,xq,qsh,sj+xnId,ssbh,nr,jcf,bz}, request);
				}
			}else if("cf".equalsIgnoreCase(jc)&&sj.length()>=8){//操作学生惩罚信息数据
				del = StandardOperation.delete("xscfb", new String[]{"xh","xn","xq","sj"}, new String [] {xh,xn,xq,qsh,sj+xnId}, request);
				if(del){
					StandardOperation.insert("xscfb",new String[]{"xh","xn","xq","qsh","sj","ssbh","cfnr","rykf","bz"}, new String[]{xh,xn,xq,qsh,sj+xnId,ssbh,nr,jcf,bz}, request)	;	
				}
			}
		}
		if(del){
			request.setAttribute("done","ok");
		}else{
			request.setAttribute("done","no");
		}
		request.setAttribute("xh",xh);
		request.setAttribute("xxdm",dao.getXxdm());
		return mapping.findForward("success");
	}

	private ActionForward wmqspb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException{
		DAO dao = DAO.getInstance();
		CommanForm comForm = (CommanForm) form;
		Vector<Object> rs = new Vector<Object>();
		String rsNum = "0";
		String sql = "";
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1 ");				
		String pk = "xn||xq||ssbh";
		String tableName = "view_wmqspbxx";
		String act = request.getParameter("go");
		String realTable = "wmqspbb";
        
		String xn = comForm.getXn();
		String xq = comForm.getXq();
		String lddm = comForm.getLddm();
		String qsh = comForm.getQsh();	
		String pysj = comForm.getPysj();
		String qslb = comForm.getQslb();
		String nj   = comForm.getNj();
		String xydm = comForm.getXydm();
		String zydm = comForm.getZydm();
		String bjdm = comForm.getBjdm();
		String lc = comForm.getLc();
		String [] colList = null;
		String userType = "xx";
		String fzld = "";
		String xxdm = StandardOperation.getXxdm();
		String userName = request.getSession().getAttribute("userName").toString();
		String dxq = request.getParameter("dxq");
		String dxq1 = request.getParameter("writeAble");
		String tips = "公寓管理 - 信息维护 - 文明寝室评比";	
		if(xn==null){
			comForm.setXn(Base.currXn);
		}
		if(xq==null){
			comForm.setXq(Base.currXq);
		}
		if (!("".equalsIgnoreCase(dxq) || dxq == null)) {
			writeAble = dxq;
		}else{
			if(!("".equalsIgnoreCase(dxq1) || dxq1 == null)){
				writeAble = dxq1;
			}
		}
		//公寓辅导员判断begin			
//		String lddmStr = gyglDao.getLddmxXx(userName);
//		String isGyFdy = "no";
//		if(!Base.isNull(lddmStr)){
//			lddm = lddmStr; 
//			comForm.setLddm(lddm);
//			isGyFdy = "yes";					
//		}
//		if(xn==null){
//			comForm.setXn(Base.currXn);
//		}
//		request.setAttribute("isGyFdy",isGyFdy);
		//公寓辅导员判断end	
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
			//值班人员登录，默认其值班楼栋
			sql = "select szlddm from zbrydmb where zbrydm = ?";
			fzld = dao.getOneRs(sql, new String[]{userName}, "szlddm");
			if(!("".equalsIgnoreCase(fzld) || fzld == null)){
				lddm = fzld;
				userType = "xy";							
			}
			sql = "select lc dm,lc mc from (select distinct substr(qsh,1,1) lc from ssxxb) order by lc";
			List lcList = dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
			request.setAttribute("lcList", lcList);
			
			sql = "select qsh dm ,qsh mc from (select distinct qsh from ssxxb )order by qsh";
			List qshList = dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
			request.setAttribute("qshList", qshList);
			
			request.setAttribute("showhzy", "showhzy");
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)
				||xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)
				||xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)
				||Globals.XXDM_HHGXY.equalsIgnoreCase(xxdm)){
			request.setAttribute("showlb","showlb");
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)){//浙江经济职业技术学院
			tips = "公寓管理 - 信息维护 - 星级文明寝室";
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){//中国地质大学
			tips = "公寓管理 - 精神文明建设 - 文明寝室";
		}
		if(Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)){//浙江大学宁波理工学院
			tips = "公寓管理 - 信息维护 - 特殊寝室信息";
			request.setAttribute("shownblgxy", "shownblgxy");
		}


//		StringBuffer cxtj = new StringBuffer();
//		cxtj.append(" where 1=1");
//		if("".equalsIgnoreCase(lddm) || lddm==null){
//			cxtj.append(" and 1=1");
//		}else{
//			if(Check_Input_Value.check2(lddm)){
//				cxtj.append(" and lddm='");
//				cxtj.append(lddm);
//				cxtj.append("'");
//			}
//		}

		if(xn!=null&&!xn.equalsIgnoreCase("")) 
			query.append(" and xn = '" + xn +"'");
		if(xq!=null&&!xq.equalsIgnoreCase("")) 
			query.append(" and xq = '" + xq +"'");
		if(lddm!=null&&!lddm.equalsIgnoreCase(""))
			query.append(" and lddm = '" + lddm + "'");
		if(!Base.isNull(qsh)&&!"qsh".equalsIgnoreCase(qsh))
			query.append(" and qsh = '" + qsh + "'");
		if(pysj!=null&&!"".equalsIgnoreCase(pysj))
			query.append(" and pysj ='" + pysj + "'");
		if(qslb!=null&&!"".equalsIgnoreCase(qslb))
            query.append(" and qslb='" + qslb + "'");
		if (!Base.isNull(lc)&& !lc.equalsIgnoreCase("lc"))
			query.append(" and cs ='"+lc+"' ");
        StringBuffer query2 = new StringBuffer("");
		if(!Base.isNull(nj)||!Base.isNull(xydm)||!Base.isNull(zydm)||!Base.isNull(bjdm)){
        	query2.append(" and exists (select 1 from view_xszsxx b where v.ssbh=b.ssbh ");
        	query2.append(Base.isNull(nj)?"":" and nj='"+nj+"'");
        	query2.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"'");
        	query2.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"'");
        	query2.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"'");
        	query2.append(" )");
        }
		
		//----------2010/6/23 edit by luojw ----------------
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){//中国地大
			String lbmc = comForm.getLbmc();
			if(!Base.isNull(lbmc)){
	            query.append(" and lbmc='" + lbmc + "'");
			}
		}
		// ----------end ----------------
		
		sql = " select " + pk + " 主键,rownum 行号,v.* from " + tableName + " v"+ query+query2;
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
			colList = new String[] { "主键", "行号", "xn", "xqmc", "ldmc", "qsh",  "pysj","lbmc","qsjje","cy" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)){			
			colList = new String[] { "主键", "行号", "xn", "xqmc", "ldmc", "qsh",  "pysj","lbmc","cy" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){//中国地大
			colList = new String[] { "主键", "行号", "xn", "xqmc", "ldmc", "qsh",  "pysj","lbmc","cy" };
		}else{
			colList = new String[] { "主键", "行号", "xn", "xqmc", "ldmc", "qsh","pysj","cy" };
		}
		
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);

		if(act!=null&&act.equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator2(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		} 
        
		sql = "select lbdm,lbmc from qslbdmb";
		List qslbList = dao.getList(sql,new String[]{},new String[]{"lbdm","lbmc"});
		request.setAttribute("qslbList", qslbList);
//		
//		sql = "select lddm,ldmc from sslddmb order by  lddm ";
//		List ldList = dao.getList(sql, new String[] {}, new String[] { "lddm", "ldmc" });
//		request.setAttribute("ldList", ldList);
//		
//		sql = "select distinct qsh from ssxxb order by qsh";
//		List ssList = dao.getList(sql, new String[] {},
//				new String[] { "qsh" });
//		request.setAttribute("ssList", ssList);
//		
//		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
//		request.setAttribute("xqList", Base.getXqList());// 发送学期列表
		gyglDao.getLdLcQshList(request);
		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("tips", tips);
		request.setAttribute("topTr", topTr);
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("act", "view");// 发送数据查询类型
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		request.setAttribute("xxdm", xxdm);
		return mapping.findForward("success");
	}
}
