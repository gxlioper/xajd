package xgxt.utils;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.pjpy.commonutils.PjpyCommonDAO;
import xgxt.pjpy.jgsdx.PjpyJgsdxDAO;
import xgxt.xtwh.xtwhCriterion.jsgl.JsglDAO;

public class RequestSetList{
	
	//学院，专业，班级，年级列表List
	public static void setXscxList(HttpServletRequest request){
		FormModleCommon.setNjXyZyBjList(request);
	}
	
	//学院，专业，班级，年级列表List
	public static void setXscxForFdyBzrList(HttpServletRequest request){
		FormModleCommon.setNjXyZyBjList(request);
	}
	
	//部门列表List
	public static void setBmList(HttpServletRequest request){
		DAO dao = DAO.getInstance();
		request.setAttribute("bmList", dao.getBmListAll());
	}
	
	//部门列表List(带拼音首字母的一级部门列表)
	public static void setYjbmList(HttpServletRequest request){
		DAO dao = DAO.getInstance();
		request.setAttribute("yjbmList", dao.getYjbmList());
	}
	
	//学院列表List
	public static void setXyList(HttpServletRequest request){
		request.setAttribute("xyList", Base.getXyList());
	}
	
	//学年列表List
	public static void setXnList(HttpServletRequest request){
		request.setAttribute("xnList", Base.getXnndList());
	}
	
	//学期列表List
	public static void setXqList(HttpServletRequest request){
		request.setAttribute("xqList", Base.getXqList());
	}
	
	//	月份列表List
	public static void setYfList(HttpServletRequest request){
		DAO dao = DAO.getInstance();
		request.setAttribute("yfList", dao.getYfList());
	}

	//	民族列表List
	public static void setMzList(HttpServletRequest request){
		DAO dao = DAO.getInstance();
		request.setAttribute("mzList", dao.getMzList());
	}
	
	//	政治面貌列表List
	public static void setZzmmList(HttpServletRequest request){
		DAO dao = DAO.getInstance();
		request.setAttribute("zzmmList", dao.getZzmmList());
	}
	
    //	辅导员List
	public static void setFdyList(HttpServletRequest request){
		String bmdm = request.getParameter("bmdm");
		request.setAttribute("fdyList", CommonQueryDAO.getFdyList(bmdm));
	}
	
	//	通用奖学金List
	public static void setJxjList(HttpServletRequest request) throws Exception{
		PjpyCommonDAO pjpyDAO = new PjpyCommonDAO();
		request.setAttribute("jxjList", pjpyDAO.getJxjList());
	}
	
	//	通用荣誉称号List
	public static void setRychList(HttpServletRequest request) throws Exception{
		PjpyJgsdxDAO dao = new PjpyJgsdxDAO();
		request.setAttribute("rychList",dao.getRychList());
	}
	
	//	用户组List
	public static void setYhzList(HttpServletRequest request){
		request.setAttribute("yhzList", CommonQueryDAO.getYhzList());
	}
	
	//	角色类型List
	public static void setJslxList(HttpServletRequest request){
		JsglDAO jsglDAO = new JsglDAO();
		request.setAttribute("jslxList", jsglDAO.getJslxList());
	}
	
	//  角色范围List
	public static void setJsczfwList(HttpServletRequest request){
		JsglDAO jsglDAO = new JsglDAO();
		request.setAttribute("jsczfwList", jsglDAO.getJsczfwList());
	}
	
	//  是否List   
	public static void setYesNoList(HttpServletRequest request){
		DAO dao = DAO.getInstance();
		request.setAttribute("yesNoList", dao.getChkList(2));
	}
	
	
	/**
	 * 籍贯列表（省）
	 * @param request
	 */
	public static void setJgList(HttpServletRequest request){
		DAO dao = DAO.getInstance();
		String sql = "select qxdm,qxmc from dmk_qx where qxdm like '__0000'";
		request.setAttribute("jgList", dao.getListNotOut(sql, new String[]{}));
	}
}
