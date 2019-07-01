package xgxt.xszz.zgkydx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;

import xgxt.base.Excel2Oracle;

/**
 * Title: ѧ����������ϵͳ
 * Description: �й���ҵ��ѧѧ������Service
 * Copyright: Copyright (c) 2008
 * Company: zfsoft
 * Author: ����
 * Version: 1.0
 * Time: 2008-08-13
 */
public class XszzZgkdService {

	XszzZgkdDAO dao = null;// ���ݲ���DAO

	/**
	 * ��ȡ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsxx(String pkVal) throws Exception {
		dao = new XszzZgkdDAO();
		return dao.getKnsxx(pkVal);
	}
	
	/**
	 * ��ȡѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzZgkdDAO();
		return dao.getStu(xh);
	}
	
	/**
	 * �õ�����������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getKnsSqQx(String sUserType,String userDep,String xh) throws Exception {
		dao = new XszzZgkdDAO();
		return dao.getKnsSqQx(sUserType,userDep,xh);
	}
	
	/**
	 * ����������������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsSqxx(KnsModel saveKnsModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgkdDAO();
		return dao.saveKnsSqxx(saveKnsModel, request);
	}
	
	/**
	 * �õ�������������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsSqb(KnsModel knsModel,HttpServletRequest request) throws Exception {
		dao = new XszzZgkdDAO();
		return dao.getKnsSqb(knsModel,request);
	}
	
	/**
	 * ɾ����������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnsxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgkdDAO();
		dao.delKnsxx(cbVal, request);
	}
	
	/**
	 * �����޸���������˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modKnsxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgkdDAO();
		dao.modKnsxx(cbVal, shjg, request);
	}
	
	/**
	 * ��������ѯ��ͷ knstit ---- ��������ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsTit() throws Exception {
		dao = new XszzZgkdDAO();
		return dao.getKnsTit();
	}
	
	/**
	 * ��������ѯ��� knsres ---- ������
	 * 
	 * @param queryModel,request,form
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsRes(QueryModel queryModel,HttpServletRequest request,ActionForm form)
			throws Exception {
		dao = new XszzZgkdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsRes(queryModel,request,form);
		}
		return resList;
	}
	
	/**
	 * ��������ѯ�����¼�� knsresNum ---- ��������¼��
	 * 
	 * @param queryModel,request,form
	 * @return
	 * @throws Exception
	 */
	public String getKnsResNum(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgkdDAO();
		String num = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			num = dao.getKnsResNum(queryModel,request);
		}
		return num;
	}
	
	/**
	 * ���������� knsExp ---- ����������
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getKnsExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgkdDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zgkydx_knsxx",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zgkydx_knsxx");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ���������������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsShxx(KnsModel saveKnsModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgkdDAO();
		return dao.saveKnsShxx(saveKnsModel, request);
	}
	
	/**
	 * ������Ⱥ����ҳ���ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTsrqszTit() throws Exception {
		dao = new XszzZgkdDAO();
		return dao.getTsrqszTit();
	}
	
	/**
	 * ������Ⱥ���� - ��ѯ������
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getTsrqKnsRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgkdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getTsrqKnsRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * �õ�������Ⱥ�б�
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTsrqList()
			throws Exception {
		dao = new XszzZgkdDAO();
		return dao.getTsrqList();
	}
	
	/**
	 * ����������Ⱥ������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void tsrqSave(String cbVal, String tsrqdm, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgkdDAO();
		dao.tsrqSave(cbVal, tsrqdm, request);
	}
	
	/**
	 * ������Ⱥ��ѯҳ���ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTsrqcxTit() throws Exception {
		dao = new XszzZgkdDAO();
		return dao.getTsrqcxTit();
	}
	
	/**
	 * ������Ⱥ��¼
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getTsrqRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgkdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getTsrqRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * ɾ��������Ⱥ��¼
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delTsrqxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgkdDAO();
		dao.delTsrqxx(cbVal, request);
	}
	
	/**
	 * ������Ⱥ���� tsrqExp ---- ������Ⱥ����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getTsrqExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgkdDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_zgkd_tsrq",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_zgkd_tsrq");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ��û���ά����ϸ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getHkxxmoreinfo(String pkValue, HttpServletRequest request)
			throws Exception {
		
		HashMap<String, String> map = new HashMap<String, String>();
		dao = new  XszzZgkdDAO();	
		map= dao.getHkxxmoreinfo(pkValue);
		return map;
	}
	
	/**
	 * ������Ϣ�޸�
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean  hkxxUpdate(String[] columns,String[] values,String pk,String pkValue, HttpServletRequest request)
			throws Exception {
		
       boolean judge = false;
       dao = new XszzZgkdDAO();
       judge = dao.updateHkxxinfo(columns, values, pk, pkValue, request);     
       return judge;
	}
	
	/**
	 * ������Ϣɾ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean  hkxxDel(String pk,String pkValue, HttpServletRequest request)
			throws Exception {
		
       boolean judge = false;
       dao = new XszzZgkdDAO();
       judge = dao.delHkxxinfo(pk, pkValue, request);     
       return judge;
	}
	
	/**
	 * ������Ϣȫ�����
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean  hkxxDelall(String tableName)
			throws Exception {
		
       boolean judge = false;
       dao = new XszzZgkdDAO();
       judge = dao.delallHkxxinfo(tableName);  
       return judge;
	}
	
	/**
	 * ������Ϣ�б��ѯ
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>>  getHkxxList(HashMap<String, String> map,ActionForm form,HttpServletRequest request)
			throws Exception {
		
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
 
		
		StringBuffer query = new StringBuffer();
		String xh = map.get("xh");
		String xm = map.get("xm");
		String nj = map.get("nj");
		String xydm = map.get("xydm");
		String zydm = map.get("zydm");
		String bjdm = map.get("bjdm");
		String hth = map.get("hth");


		if (!("".equals(xh))) {
			query.append(" and xh like '%");
			query.append(xh);
			query.append("%'");
		}
		if (!("".equals(xm))) {
			query.append(" and xm like '%");
			query.append(xm);
			query.append("%'");
		}
		if (!("".equals(nj))) {
			query.append(" and nj='");
			query.append(nj);
			query.append("'");
		}
		if (!("".equals(xydm))) {
			query.append(" and xydm= '");
			query.append(xydm);
			query.append("'");
		}
		if (!("".equals(zydm))) {
			query.append(" and zydm='");
			query.append(zydm);
			query.append("'");
		}
		if (!("".equals(bjdm))) {
			query.append(" and bjdm='");
			query.append(bjdm);
			query.append("'");
		}
		if (!("".equals(hth))) {
			query.append(" and hth like '%");
			query.append(hth);
			query.append("%'");
		}
		
		//String query1 = query.toString();
		XszzZgkdDAO dao1 = new XszzZgkdDAO();
		rs = dao1.getHkxsxx(query, form, request);
		
		
       return rs;
	}
	
	
	/**
	 * ������Ⱥ��������Ŀ����ҳ���ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTsrqxmTit() throws Exception {
		dao = new XszzZgkdDAO();
		return dao.getTsrqxmTit();
	}
	
	/**
	 * ������Ⱥ��Ŀ����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getTsrqxmRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgkdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getTsrqxmRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * �õ�������Ŀ��¼
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZzxmList() throws Exception {
		dao = new XszzZgkdDAO();
		return dao.getZzxmList();
	}
	
	/**
	 * ɾ��������Ⱥ��������Ŀ��¼
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delTsrqxmcxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgkdDAO();
		dao.delTsrqxmcxx(cbVal, request);
	}
	
	/**
	 * ����������Ⱥ��������Ŀ������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean tsrqxmSave(String zzxmdm, String tsrqdm, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgkdDAO();
		return dao.tsrqxmSave(zzxmdm, tsrqdm, request);
	}
}
