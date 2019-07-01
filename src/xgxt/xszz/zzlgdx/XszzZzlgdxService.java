package xgxt.xszz.zzlgdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;

/**
 * Title: ѧ����������ϵͳ
 * Description: �㽭����ѧService
 * Copyright: Copyright (c) 2009
 * Company: zfsoft
 * Author: ����
 * Version: 1.0
 * Time: 2009-12-17
 */
public class XszzZzlgdxService {

	XszzZzlgdxDAO dao = null;// ���ݲ���DAO

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
		dao = new XszzZzlgdxDAO();
		return dao.getStu(xh);
	}
	
	/**
	 * ɾ����ѧ������Ϣ
	 * 
	 * @param pkT,request
	 * @return
	 * @throws Exception
	 */
	public void delZxdk(String[] pk,HttpServletRequest request){
		dao = new XszzZzlgdxDAO();
		dao.delZxdk(pk, request);
	}
	
	/**
	 * ��ѧ������Ϣ��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZxdkTit() throws Exception {
		String[] enList = new String[] { "pk", "xh", "xm", "sfzh", "xymc",
				"zymc", "bjmc", "dkhth", "dkje" };
		String[] cnList = new String[] { "����", "ѧ��", "����", "���֤��", Base.YXPZXY_KEY, "רҵ",
				"�༶", "��ͬ��", "������" };
		return makeTitList(enList, cnList);
	}
	
	/**
	 * ��ѧ������Ϣ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZxdkRes(QueryModel queryModel,
			HttpServletRequest request, XszzZzlgdxActionForm actionForm)
			throws Exception {
		dao = new XszzZzlgdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getZxdkRes(queryModel, request, actionForm);
		}
		return resList;
	}
	
	/**
	 * ��ѧ������Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getZxdkResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		dao = new XszzZzlgdxDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getZxdkResNum(queryModel, request);
		}
		return sT;
	}
	
	/**
	 * ��ȡ��ѧ������ϸ��Ϣ
	 * 
	 * @param guid
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxdkXx(String guid) throws Exception {
		dao = new XszzZzlgdxDAO();
		return dao.getZxdkXx(guid);
	}
	
	/**
	 * ������ѧ������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] saveZxdkxx(ZxdkModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZzlgdxDAO();
		return dao.saveZxdkxx(model, request);
	}
	
	/**
	 * ��ѧ�����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getZxdkExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new XszzZzlgdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_zzlg_zxdk", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_zzlg_zxdk");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
}
