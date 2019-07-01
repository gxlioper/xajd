package xgxt.utils;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.pjpy.commonutils.PjpyCommonDAO;
import xgxt.pjpy.jgsdx.PjpyJgsdxDAO;
import xgxt.xtwh.xtwhCriterion.jsgl.JsglDAO;

public class RequestSetList{
	
	//ѧԺ��רҵ���༶���꼶�б�List
	public static void setXscxList(HttpServletRequest request){
		FormModleCommon.setNjXyZyBjList(request);
	}
	
	//ѧԺ��רҵ���༶���꼶�б�List
	public static void setXscxForFdyBzrList(HttpServletRequest request){
		FormModleCommon.setNjXyZyBjList(request);
	}
	
	//�����б�List
	public static void setBmList(HttpServletRequest request){
		DAO dao = DAO.getInstance();
		request.setAttribute("bmList", dao.getBmListAll());
	}
	
	//�����б�List(��ƴ������ĸ��һ�������б�)
	public static void setYjbmList(HttpServletRequest request){
		DAO dao = DAO.getInstance();
		request.setAttribute("yjbmList", dao.getYjbmList());
	}
	
	//ѧԺ�б�List
	public static void setXyList(HttpServletRequest request){
		request.setAttribute("xyList", Base.getXyList());
	}
	
	//ѧ���б�List
	public static void setXnList(HttpServletRequest request){
		request.setAttribute("xnList", Base.getXnndList());
	}
	
	//ѧ���б�List
	public static void setXqList(HttpServletRequest request){
		request.setAttribute("xqList", Base.getXqList());
	}
	
	//	�·��б�List
	public static void setYfList(HttpServletRequest request){
		DAO dao = DAO.getInstance();
		request.setAttribute("yfList", dao.getYfList());
	}

	//	�����б�List
	public static void setMzList(HttpServletRequest request){
		DAO dao = DAO.getInstance();
		request.setAttribute("mzList", dao.getMzList());
	}
	
	//	������ò�б�List
	public static void setZzmmList(HttpServletRequest request){
		DAO dao = DAO.getInstance();
		request.setAttribute("zzmmList", dao.getZzmmList());
	}
	
    //	����ԱList
	public static void setFdyList(HttpServletRequest request){
		String bmdm = request.getParameter("bmdm");
		request.setAttribute("fdyList", CommonQueryDAO.getFdyList(bmdm));
	}
	
	//	ͨ�ý�ѧ��List
	public static void setJxjList(HttpServletRequest request) throws Exception{
		PjpyCommonDAO pjpyDAO = new PjpyCommonDAO();
		request.setAttribute("jxjList", pjpyDAO.getJxjList());
	}
	
	//	ͨ�������ƺ�List
	public static void setRychList(HttpServletRequest request) throws Exception{
		PjpyJgsdxDAO dao = new PjpyJgsdxDAO();
		request.setAttribute("rychList",dao.getRychList());
	}
	
	//	�û���List
	public static void setYhzList(HttpServletRequest request){
		request.setAttribute("yhzList", CommonQueryDAO.getYhzList());
	}
	
	//	��ɫ����List
	public static void setJslxList(HttpServletRequest request){
		JsglDAO jsglDAO = new JsglDAO();
		request.setAttribute("jslxList", jsglDAO.getJslxList());
	}
	
	//  ��ɫ��ΧList
	public static void setJsczfwList(HttpServletRequest request){
		JsglDAO jsglDAO = new JsglDAO();
		request.setAttribute("jsczfwList", jsglDAO.getJsczfwList());
	}
	
	//  �Ƿ�List   
	public static void setYesNoList(HttpServletRequest request){
		DAO dao = DAO.getInstance();
		request.setAttribute("yesNoList", dao.getChkList(2));
	}
	
	
	/**
	 * �����б�ʡ��
	 * @param request
	 */
	public static void setJgList(HttpServletRequest request){
		DAO dao = DAO.getInstance();
		String sql = "select qxdm,qxmc from dmk_qx where qxdm like '__0000'";
		request.setAttribute("jgList", dao.getListNotOut(sql, new String[]{}));
	}
}
