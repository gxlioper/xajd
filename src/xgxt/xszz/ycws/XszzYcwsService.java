package xgxt.xszz.ycws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.base.Excel2Oracle;

/**
 * Title: ѧ����������ϵͳ
 * Description: �γ�����ѧ������Service
 * Copyright: Copyright (c) 2009
 * Company: zfsoft
 * Author: ����
 * Version: 1.0
 * Time: 2009-06-09
 */
public class XszzYcwsService {

	XszzYcwsDAO dao = null;// ���ݲ���DAO

	/**
	 * ��ȡѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzYcwsDAO();
		return dao.getStu(xh);
	}
	
	/**
	 * ɾ�������϶���Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnrdxx(String[] pk, HttpServletRequest request)
			throws Exception {
		dao = new XszzYcwsDAO();
		dao.delKnrdxx(pk, request);
	}
	
	/**
	 * �����϶���ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnrdTit() throws Exception {
		dao = new XszzYcwsDAO();
		return dao.getKnrdTit();
	}
	
	/**
	 * �����϶���ѯ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnrdRes(QueryModel queryModel,HttpServletRequest request,XszzYcwsActionForm xszzYcwsActionForm)
			throws Exception {
		dao = new XszzYcwsDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnrdRes(queryModel,request,xszzYcwsActionForm);
		}
		return resList;
	}
	
	/**
	 * �����϶���ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnrdResNum(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzYcwsDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getKnrdResNum(queryModel,request);
		}
		return sT;
	}
	
	/**
	 * �õ�����ֵ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKg() throws Exception {
		dao = new XszzYcwsDAO();
		return dao.getKg();
	}
	
	/**
	 * �����϶���Ϣ����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getKnsrdExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzYcwsDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_ycws_knrdxx",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_ycws_knrdxx");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ��ȡ�����϶���Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrdxx(String pkVal) throws Exception {
		dao = new XszzYcwsDAO();
		return dao.getKnsrdxx(pkVal);
	}
	
	/**
	 * ���������϶���Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnrd(KnrdModel model, String act,
			HttpServletRequest request) throws Exception {
		dao = new XszzYcwsDAO();
		return dao.saveKnrd(model, act, request);
	}
	
	/**
	 * ɾ������������Ŀ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnzzxmxx(String[] pk, HttpServletRequest request)
			throws Exception {
		dao = new XszzYcwsDAO();
		dao.delKnzzxmxx(pk, request);
	}
	
	/**
	 * ����������Ŀ��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnzzxmTit() throws Exception {
		dao = new XszzYcwsDAO();
		return dao.getKnzzxmTit();
	}
	
	/**
	 * ����������Ŀ��ѯ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnzzxmRes(QueryModel queryModel,HttpServletRequest request,XszzYcwsActionForm xszzYcwsActionForm)
			throws Exception {
		dao = new XszzYcwsDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnzzxmRes(queryModel,request,xszzYcwsActionForm);
		}
		return resList;
	}
	
	/**
	 * ����������Ŀ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnzzxmResNum(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzYcwsDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getKnzzxmResNum(queryModel,request);
		}
		return sT;
	}
	
	/**
	 * ����������Ŀ��Ϣ����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getKnzzxmExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzYcwsDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_ycws_knzzxm",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_ycws_knzzxm");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ��ȡ����������Ŀ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnzzxmxx(String pkVal) throws Exception {
		dao = new XszzYcwsDAO();
		return dao.getKnzzxmxx(pkVal);
	}
	
	/**
	 * ��������������Ŀ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnzzxm(KnzzxmModel model, String act,
			HttpServletRequest request) throws Exception {
		dao = new XszzYcwsDAO();
		return dao.saveKnzzxm(model, act, request);
	}
}
