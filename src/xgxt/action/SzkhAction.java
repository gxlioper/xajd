/*
 * 创建日期 2006-12-22
 *
 *  要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package xgxt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.form.CommanForm;

public class SzkhAction extends Action {
    
	//DAO dao = DAO.getInstance();
    private boolean isNull(String str) {
	return ((str == null) || str.equalsIgnoreCase(""));
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	CommanForm fdyForm = (CommanForm) form;
	try {
	    int i = Base.chkTimeOut(request.getSession());
	    if (i <= 2) {
		fdyForm.setErrMsg("登陆超时，请重新登陆！");
		return new ActionForward("/login.jsp", false);
	    }

	    HttpSession session = request.getSession();
	    if (session == null) {
		request.setAttribute("errMsg", "sadfsdf");
		return mapping.findForward("false");
	    }
	    ActionForward myActFwd = null;
	    String myAct = mapping.getParameter();
	    if (myAct.equalsIgnoreCase("setCpdx")) {
		myActFwd = setCpdx(mapping, form, request, response);
	    } else if (myAct.equalsIgnoreCase("setPjConf")) {
		myActFwd = setPjConf(mapping, form, request, response);
	    } else if (myAct.equalsIgnoreCase("pjzbsz")) {
		myActFwd = pjzbsz(mapping, form, request, response);
	    }
	    return myActFwd;
	} catch (Exception e) {
	    e.printStackTrace();
	    fdyForm.setErrMsg("数据连接中断，请重新登陆！");
	    return new ActionForward("/login.jsp", false);
	}
    }

    private ActionForward setCpdx(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
    DAO dao = DAO.getInstance();
	CommanForm szkhForm = (CommanForm) form;
	String xn = dao.getConf(0);
	String nd = dao.getConf(4);
	String xq = dao.getConf(1);
	String xydm = szkhForm.getXydm();
	String act = request.getParameter("act");
	act = isNull(act) ? " " : act;
	String sql = "";
	if (!act.equalsIgnoreCase("all") & !act.equalsIgnoreCase("none")) {
	    dao.runProcuder("{call chgFdySfCp(?)}", new String[] { act });
	} else if (act.equalsIgnoreCase("all")) {
	    sql = "insert into fdypfb(xn,nd,xq,zgh) select ?,?,?,zgh from fdyxxb where zgh not in(select zgh from fdypfb where xn=? and nd=? and xq=?)";
	    dao.runUpdate(sql, new String[] { xn, nd, xq, xn, nd, xq });
	} else if (act.equalsIgnoreCase("none")) {
	    sql = "delete fdypfb where xn=? and nd=? and xq=?";
	    dao.runUpdate(sql, new String[] { xn, nd, xq });
	}
	xydm = isNull(xydm) ? "%" : xydm;
	// sql = "select zgh,xm,xb,zwmc,bmmc,color,(case color when '#000000'
        // then '参评' else '不参评' end) sfcp from "
	sql = "select zgh,xm,xb,zwmc,bmmc,lxdh,(case when exists(select *"
		+ " from view_fdypf b where a.zgh=b.zgh and xn=? and nd=? and"
		+ " xq=?) then '参评' else '不参评' end ) color from "
		+ "view_fdyxx a where bmdm like ? order by color desc ,bmmc,xm";
	String[] outSql = new String[] { "zgh", "xm", "xb", "zwmc", "bmmc",
		"color", "lxdh" };
	String[] inSql = new String[] { xn, nd, xq, xydm };
	List rs = dao.getList(sql, inSql, outSql);
	request.setAttribute("rs", rs);
	request.setAttribute("writeAble", "yes");
	request.setAttribute("bmList", dao.getXyList());
	szkhForm.setXn(xn);
	szkhForm.setNd(nd);
	szkhForm.setXq(xq);
	request.setAttribute("rsNum", Integer.toString(rs.size()));
	return mapping.findForward("success");
    }

    private ActionForward setPjConf(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
    DAO dao = DAO.getInstance();
	CommanForm pjForm = (CommanForm)form;
	String act = request.getParameter("act");
	String sql = "";
	if(!isNull(act) && act.equalsIgnoreCase("save")){
	    String[] qtdm = pjForm.getQtdm();
	    String[] sfcp = pjForm.getSfcp();
	    String[] pfbl = pjForm.getPfbl();
	    String sfkpj = request.getParameter("sfkpj");
	    Vector<HashMap<String, Object>> sqlToExe = new Vector<HashMap<String, Object>>(); 
	    HashMap<String, Object> mapTmp;
	    String[] tmp;
	    sfkpj = (isNull(sfkpj))?"否":DealString.toGBK(sfkpj);
	    if ((qtdm == null) || (sfcp == null) || (pfbl == null)
		    || (qtdm.length != sfcp.length) || (qtdm.length != pfbl.length)
		    || (pfbl.length != sfcp.length)) {
		request.setAttribute("succ", "no");
	    } else {
		for(int i = 0; i <qtdm.length ; i++){
		    mapTmp = new HashMap<String, Object>();
		    sql = "update pjqtb set sfcp=?,pfbl=? where qtdm=?";
		    tmp = new String[]{DealString.toGBK(sfcp[i]), pfbl[i],qtdm[i]};
		    mapTmp.put("sqlTxt",sql);
		    mapTmp.put("sqlVal",tmp);
		    sqlToExe.add(mapTmp);
		}
		mapTmp = new HashMap<String, Object>();
		sql = "update xtszb set sfkpj=?";
		tmp = new String[]{sfkpj};
		mapTmp.put("sqlTxt",sql);
		mapTmp.put("sqlVal",tmp);
		sqlToExe.add(mapTmp);
		boolean succ = dao.runUpdate(sqlToExe);
		if(succ){
		    request.setAttribute("succ", "yes");
	    	}else{
	    	    request.setAttribute("succ", "no");
	    	}
	    }
	}
	sql = "select * from pjqtb order by qtdm";
	String[] in = new String[] {};
	List cpqtList = dao.getList(sql, in, new String[] { "qtdm",
		"qtmc", "sfcp", "pfbl" });
	sql = "select nvl(sfkpj,'否') sfkpj from xtszb where rownum=1";
	String sfkpj = dao.getOneRs(sql,in,new String[]{"sfkpj"})[0];
	
	request.setAttribute("sfkpj",sfkpj);
	request.setAttribute("yesNoList",dao.getChkList(2));
	request.setAttribute("cpqtList", cpqtList);
	return mapping.findForward("success");
    }

    private ActionForward pjzbsz(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response) {
    DAO dao = DAO.getInstance();
	String[] getPara = { "xh", "pjzb", "qz", "mxdx" };
	String sql = "select xh,pjzb,qz,mxdx from pjzbb";
	List rslist = dao.getList(sql, new String[] {}, getPara);
	
	String act = request.getParameter("act");
	sql = "select qtmc,qtdm from pjqtb where sfcp='是'";
	List mxdxList = dao.getList(sql, new String[] {}, new String[]{"qtdm","qtmc"});
	
	if(!isNull(act) && act.equalsIgnoreCase("add")){
	    request.setAttribute("mxdxList",mxdxList);
	    return new ActionForward("/sjcz/pjzbOne.jsp",false);
	}else if(!isNull(act) && act.equalsIgnoreCase("modi")){
	    String zbh = request.getParameter("id");
	    sql = "select xh,pjzb,qz,mxdx from pjzbb where xh=?";
	    String[] oneInfo = new String[]{"xh","pjzb","qz","mxdx"};
	    String[] res = dao.getOneRs(sql, new String[]{zbh}, oneInfo);
	    if((res == null) || (res.length != oneInfo.length)){
		request.setAttribute("find","no");
	    }else{
		request.setAttribute("find","yes");
		for(int i = 0; i < oneInfo.length; i++){
		    request.setAttribute(oneInfo[i],res[i]);
		}
	    }
	    request.setAttribute("mxdxList",mxdxList);	    
	    return new ActionForward("/sjcz/pjzbOne.jsp",false);
	}else if(!isNull(act) && act.equalsIgnoreCase("save")){
	    
	}else if(!isNull(act) && act.equalsIgnoreCase("del")){
	    
	}
	
	request.setAttribute("rslist", rslist);
	request.setAttribute("rsNum", Integer.toString(rslist.size()));
	return mapping.findForward("success");
    }

}
