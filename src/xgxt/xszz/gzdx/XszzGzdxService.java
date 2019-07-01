package xgxt.xszz.gzdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;

/**
 * Title: ѧ����������ϵͳ
 * Description: ���ݴ�ѧѧ������Service
 * Copyright: Copyright (c) 2009
 * Company: zfsoft
 * Author: ����
 * Version: 1.0
 * Time: 2009-12-24
 */
public class XszzGzdxService {

	XszzGzdxDAO dao = null;// ���ݲ���DAO

	private List<HashMap<String, String>> makeTitList(String[] enList, String[] cnList) {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * ��ȡѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzGzdxDAO();
		return dao.getStu(xh);
	}
	
	/**
	 * ɾ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delXsdkxx(String[] cbVal)
			throws Exception {
		dao = new XszzGzdxDAO();
		dao.delXsdkxx(cbVal);
	}
	
	/**
	 * ������Ϣ��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXsdkxxTit() throws Exception {
		String[] enList = new String[] { "pk", "xn", "dkcs", "xh", "xm", "xb",
				"sfzh", "xymc", "zymc", "bjmc", "dknj", "dkze", "htbh" };
		String[] cnList = new String[] { "����", "ѧ��", "�������", "ѧ��", "����", "�Ա�", "���֤��",
				Base.YXPZXY_KEY, "רҵ", "�༶","�����꼶",  "�����ܶ�", "��ͬ���" };
		return makeTitList(enList, cnList);
	}
	
	/**
	 * ������Ϣ��ѯ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXsdkxxRes(QueryModel queryModel,
			HttpServletRequest request, XszzGzdxActionForm actionForm)
			throws Exception {
		dao = new XszzGzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXsdkxxRes(queryModel, request, actionForm);
		}
		return resList;
	}
	
	/**
	 * ������Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXsdkxxResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		dao = new XszzGzdxDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getXsdkxxResNum(queryModel, request);
		}
		return sT;
	}
	
	/**
	 * ������Ϣ����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getXsdkxxExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new XszzGzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_gzdx_xsdkxxb", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_gzdx_xsdkxxb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ��ȡ������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsdkxx(String pkVal) throws Exception {
		dao = new XszzGzdxDAO();
		return dao.getXsdkxx(pkVal);
	}
	
	/**
	 * ��ȡ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsdkzxx(String pkVal) throws Exception {
		dao = new XszzGzdxDAO();
		return dao.getXsdkzxx(pkVal);
	}
	
	/**
	 * ���������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXsdkxx(XsdkxxbModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzGzdxDAO();
		return dao.saveXsdkxx(model, request);
	}
	
	/**
	 * ɾ����ҵ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delXsjyxx(String[] cbVal)
			throws Exception {
		dao = new XszzGzdxDAO();
		dao.delXsjyxx(cbVal);
	}
	
	/**
	 * ��ҵ��Ϣ��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXsjyxxTit() throws Exception {
		String[] enList = new String[] { "pk", "xh", "xm", "xb",
				"sfzh", "xymc", "zymc", "bjmc" };
		String[] cnList = new String[] { "����", "ѧ��", "����", "�Ա�", "���֤��",
				Base.YXPZXY_KEY, "רҵ", "�༶" };
		return makeTitList(enList, cnList);
	}
	
	/**
	 * ��ҵ��Ϣ��ѯ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXsjyxxRes(QueryModel queryModel,
			HttpServletRequest request, XszzGzdxActionForm actionForm)
			throws Exception {
		dao = new XszzGzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXsjyxxRes(queryModel, request, actionForm);
		}
		return resList;
	}
	
	/**
	 * ��ҵ��Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXsjyxxResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		dao = new XszzGzdxDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getXsjyxxResNum(queryModel, request);
		}
		return sT;
	}
	
	/**
	 * ��ҵ��Ϣ����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getXsjyxxExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new XszzGzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_gzdx_dkxsjyxxb", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_gzdx_dkxsjyxxb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ��ȡ��ҵ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsjyxx(String pkVal) throws Exception {
		dao = new XszzGzdxDAO();
		return dao.getXsjyxx(pkVal);
	}
	
	/**
	 * �����ҵ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXsjyxx(XsjyxxbModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzGzdxDAO();
		return dao.saveXsjyxx(model, request);
	}
	
	/**
	 * ɾ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delXshkxx(String[] cbVal)
			throws Exception {
		dao = new XszzGzdxDAO();
		dao.delXshkxx(cbVal);
	}
	
	/**
	 * ������Ϣ��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXshkxxTit() throws Exception {
		String[] enList = new String[] { "pk", "htbh", "xn", "dkcs", "dkze",
				"sfqbhqdk" };
		String[] cnList = new String[] { "����", "��ͬ���", "����ѧ��", "�������", "�����ܶ�",
				"�Ƿ���ǰ����" };
		return makeTitList(enList, cnList);
	}
	
	/**
	 * ������Ϣ��ѯ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXshkxxRes(QueryModel queryModel,
			HttpServletRequest request, XszzGzdxActionForm actionForm)
			throws Exception {
		dao = new XszzGzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXshkxxRes(queryModel, request, actionForm);
		}
		return resList;
	}
	
	/**
	 * ������Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getXshkxxResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		dao = new XszzGzdxDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getXshkxxResNum(queryModel, request);
		}
		return sT;
	}
	
	/**
	 * ������Ϣ����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getXshkxxExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new XszzGzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_gzdx_hkxxb", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_gzdx_hkxxb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ��ȡ������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXshkxx(String pkVal) throws Exception {
		dao = new XszzGzdxDAO();
		return dao.getXshkxx(pkVal);
	}
	
	/**
	 * ��ȡ������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsdkxxByHtbh(String pkVal) throws Exception {
		dao = new XszzGzdxDAO();
		return dao.getXsdkxxByHtbh(pkVal);
	}
	
	/**
	 * ���滹����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXshkxx(XshkxxbModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzGzdxDAO();
		return dao.saveXshkxx(model, request);
	}
	
	/**
	 * ɾ����ʱ������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delLsbt(String[] cbVal)
			throws Exception {
		dao = new XszzGzdxDAO();
		dao.delLsbt(cbVal);
	}
	
	/**
	 * ��ʱ������Ϣ��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLsbtTit() throws Exception {
		String[] enList = new String[] { "pk", "btsj", "xh", "xm", "xb",
				"sfzh", "xymc", "zymc", "bjmc", "nj", "btje" };
		String[] cnList = new String[] { "����", "����ʱ��", "ѧ��", "����", "�Ա�",
				"���֤��", Base.YXPZXY_KEY, "רҵ", "�༶", "�꼶", "�������" };
		return makeTitList(enList, cnList);
	}
	
	/**
	 * ��ʱ������Ϣ��ѯ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsbtRes(QueryModel queryModel,
			HttpServletRequest request, XszzGzdxActionForm actionForm)
			throws Exception {
		dao = new XszzGzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getLsbtRes(queryModel, request, actionForm);
		}
		return resList;
	}
	
	/**
	 * ��ʱ������Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getLsbtResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		dao = new XszzGzdxDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getLsbtResNum(queryModel, request);
		}
		return sT;
	}
	
	/**
	 * ��ʱ������Ϣ����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getLsbtExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new XszzGzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_gzdx_lsbt", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_gzdx_lsbt");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ��ȡ��ʱ������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsbt(String pkVal) throws Exception {
		dao = new XszzGzdxDAO();
		return dao.getLsbt(pkVal);
	}
	
	/**
	 * ������ʱ������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsbt(LsbtModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzGzdxDAO();
		return dao.saveLsbt(model, request);
	}
}
