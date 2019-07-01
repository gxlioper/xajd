package xgxt.pjpy.xnmz;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.DAO.DBPool;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;

public class XnmzAction extends DispatchAction{
	public ActionForward directToPage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		XnmzForm myForm =(XnmzForm) form;
		DAO dao = DAO.getInstance();
		XnmzDAO mydao = new XnmzDAO();
		String xn = request.getParameter("xn");
		String[] blArr = mydao.getBlArr(xn, dao);
		if(blArr != null ){
			myForm.setCjbl(blArr[0]);
			myForm.setQtbl(blArr[1]);
		}
		return mapping.findForward("success");
	}
	
	public ActionForward saveJsBl(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		boolean result = false;
		String xn = request.getParameter("xn");
		XnmzForm myForm =(XnmzForm) form;
		String  cjbl = DealString.toGBK(myForm.getCjbl());
		String  qtbl = DealString.toGBK(myForm.getQtbl());
		String[] columns = {"xn","cjbl","qtbl"};
		String[] values = {xn,cjbl,qtbl};
		try{
			if(StandardOperation.delete("zhcpblszb", new String[]{"xn"}, new String[]{xn}, request))
				  result = StandardOperation.insert("zhcpblszb", columns, values, request);
			request.setAttribute("result", result);
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
	
	public ActionForward test(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		DataSource ds = DBPool.getPool();
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select xh from xsxxb where xh=? and xy=?";
		String sql1 = "select xh from xsxxb where xh='aXh' and xy='aXy'";
		String xh = "2003150130";
		String xy = "中医学院";
		try{
			long t0 = System.currentTimeMillis();
			Connection conn = ds.getConnection();
			long t1 = System.currentTimeMillis();
			System.out.println("get the connection: "+(t1-t0));
			pstmt = conn.prepareStatement(sql);
			long t2 = System.currentTimeMillis();
			System.out.println("get preparedStatement: "+(t2-t1));
			pstmt.setString(1, xh);
			pstmt.setString(2, xy);
			long t3 = System.currentTimeMillis();
			System.out.println("set preparedStatement string: "+(t3-t2));
			rs = pstmt.executeQuery();
			long t4 = System.currentTimeMillis();
			System.out.println("preparedStatement execute: "+(t4-t3));
			rs.next();
			System.out.println(rs.getString(1));
			pstmt.close();
			sql1.replace("aXh", xh);
			sql1.replace("aXy", xy);
			long t5 = System.currentTimeMillis();
			stmt = conn.createStatement();
			long t6 = System.currentTimeMillis();
			System.out.println("create Statement: "+(t6-t5));
			long t7 = System.currentTimeMillis();
			rs = stmt.executeQuery(sql);
			long t8 = System.currentTimeMillis();
			System.out.println("execute Statement: "+(t8-t7));
			rs.next();
			System.out.println(rs.getString(1));
			
			long t9 = System.currentTimeMillis();
			rs.close();
			stmt.close();
			
			conn.close();
			long t10 = System.currentTimeMillis();
			System.out.println("close all the resource:"+(t10-t9));
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}
