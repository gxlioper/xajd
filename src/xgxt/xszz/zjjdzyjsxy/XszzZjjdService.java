package xgxt.xszz.zjjdzyjsxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.base.Excel2Oracle;
/**
 * Title: ѧ����������ϵͳ
 * Description: �㽭����ְҵ����ѧԺѧ������Service
 * Copyright: Copyright (c) 2008
 * Company: zfsoft
 * Author: ����
 * Version: 1.0
 * Time: 2008-07-22
 */
public class XszzZjjdService {

	XszzZjjdDAO dao = null;// ���ݲ���DAO

	/**
	 * ��ȡ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsxx(String pkVal) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getKnsxx(pkVal);
	}
	
	/**
	 * ��ȡѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getStu(xh);
	}
	
	/**
	 * ��ȡѧ�������������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuZzqk(String xh) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getStuZzqk(xh);
	}
	
	/**
	 * �õ�����������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getKnsSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getKnsSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * ����������������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsSqxx(KnsModel saveKnsModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveKnsSqxx(saveKnsModel, request);
	}
	
	/**
	 * �õ�������������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsSqb(KnsModel knsModel,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
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
		dao = new XszzZjjdDAO();
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
		dao = new XszzZjjdDAO();
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
		dao = new XszzZjjdDAO();
		return dao.getKnsTit();
	}
	
	/**
	 * ��������ѯ��� knsres ---- ������
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsRes(queryModel,request);
		}
		return resList;
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
		dao = new XszzZjjdDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zjjd_knsxx",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zjjd_knsxx");
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
		dao = new XszzZjjdDAO();
		return dao.saveKnsShxx(saveKnsModel, request);
	}
	
	/**
	 * ��ȡ������ѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjxx(String pkVal) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjzxjxx(pkVal);
	}
	
	/**
	 * �õ�������ѧ������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getGjzxjSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjzxjSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * ���������ѧ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxjSqxx(GjzxjModel gjzxjModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveGjzxjSqxx(gjzxjModel, request);
	}
	
	/**
	 * �õ�������ѧ��������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjSqb(GjzxjModel gjzxjModel,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjzxjSqb(gjzxjModel,request);
	}
	
	/**
	 * ɾ��������ѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjzxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.delGjzxjxx(cbVal, request);
	}
	
	/**
	 * �����޸Ĺ�����ѧ����˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjzxjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.modGjzxjxx(cbVal, shjg, request);
	}
	
	/**
	 * ������ѧ���ѯ��ͷ gjzxjtit ---- ������ѧ���ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxjTit() throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjzxjTit();
	}
	
	/**
	 * ������ѧ���ѯ��� gjzxjres ---- ������ѧ��
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxjRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxjRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * ������ѧ�𵼳� gjzxjExp ---- ������ѧ�𵼳�
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getGjzxjExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zjjd_gjzxjsqb",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zjjd_gjzxjsqb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ���������ѧ�������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxjShxx(GjzxjModel gjzxjModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveGjzxjShxx(gjzxjModel, request);
	}
	
	/**
	 * ��ù�����ѧ���б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getGjzxjList() throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjzxjList();
	}
	
	/**
	 * ��ȡ������־��ѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxjxx(String pkVal) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjlzjxjxx(pkVal);
	}
	
	/**
	 * �õ�������־��ѧ������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getGjlzjxjSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjlzjxjSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * ���������־��ѧ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxjSqxx(GjlzjxjModel gjlzjxjModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveGjlzjxjSqxx(gjlzjxjModel, request);
	}
	
	/**
	 * �õ�������־��ѧ��������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxjSqb(GjlzjxjModel gjlzjxjModel,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjlzjxjSqb(gjlzjxjModel,request);
	}
	
	/**
	 * ɾ��������־��ѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjlzjxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.delGjlzjxjxx(cbVal, request);
	}
	
	/**
	 * �����޸Ĺ�����־��ѧ����˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjlzjxjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.modGjlzjxjxx(cbVal, shjg, request);
	}
	
	/**
	 * ������־��ѧ���ѯ��ͷ gjlzjxjtit ---- ������־��ѧ���ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjlzjxjTit() throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjlzjxjTit();
	}
	
	/**
	 * ������־��ѧ���ѯ��� gjlzjxjres ---- ������־��ѧ��
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjlzjxjRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjlzjxjRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * ������־��ѧ�𵼳� gjlzjxjExp ---- ������־��ѧ�𵼳�
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getGjlzjxjExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_zjjd_gjlzjxj",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_zjjd_gjlzjxj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ���������־��ѧ�������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxjShxx(GjlzjxjModel gjlzjxjModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveGjlzjxjShxx(gjlzjxjModel, request);
	}
	
	/**
	 * ��ȡ��ʱ���Ѳ�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsbzxx(String pkVal) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getLsbzxx(pkVal);
	}
	
	/**
	 * �õ���ʱ���Ѳ�������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getLsbzSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getLsbzSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * �����ʱ���Ѳ����б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getLsbzList() throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getLsbzList();
	}
	
	/**
	 * ������ʱ���Ѳ���������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsbzSqxx(LsbzModel lsbzModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveLsbzSqxx(lsbzModel, request);
	}
	
	/**
	 * �õ���ʱ���Ѳ���������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsbzSqb(LsbzModel lsbzModel,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getLsbzSqb(lsbzModel,request);
	}
	
	/**
	 * ɾ����ʱ���Ѳ�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delLsbzxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.delLsbzxx(cbVal, request);
	}
	
	/**
	 * �����޸���ʱ���Ѳ�����˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modLsbzxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.modLsbzxx(cbVal, shjg, request);
	}
	
	/**
	 * ��ʱ���Ѳ�����ѯ��ͷ Lsbztit ---- ��ʱ���Ѳ�����ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLsbzTit() throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getLsbzTit();
	}
	
	/**
	 * ��ʱ���Ѳ�����ѯ��� Lsbzres ---- ��ʱ���Ѳ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsbzRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getLsbzRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * ��ʱ���Ѳ������� LsbzExp ---- ��ʱ���Ѳ�������
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getLsbzExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_zjjd_lsbz",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_zjjd_lsbz");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ��ʱ���Ѳ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsbzShxx(LsbzModel lsbzModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveLsbzShxx(lsbzModel, request);
	}
	
	/**
	 * ��ȡ������ѧ������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkxx(String pkVal) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjzxdkxx(pkVal);
	}
	
	/**
	 * �õ�������ѧ��������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getGjzxdkSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjzxdkSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * ���������ѧ����������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkSqxx(GjzxdkModel gjzxdkModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveGjzxdkSqxx(gjzxdkModel, request);
	}
	
	/**
	 * �õ�������ѧ����������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkSqb(GjzxdkModel gjzxdkModel,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjzxdkSqb(gjzxdkModel,request);
	}
	
	/**
	 * ɾ��������ѧ������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjzxdkxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.delGjzxdkxx(cbVal, request);
	}
	
	/**
	 * �����޸Ĺ�����ѧ������˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjzxdkxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.modGjzxdkxx(cbVal, shjg, request);
	}
	
	/**
	 * ������ѧ�����ѯ��ͷ gjzxdktit ---- ������ѧ�����ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxdkTit() throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getGjzxdkTit();
	}
	
	/**
	 * ������ѧ�����ѯ��� gjzxdkres ---- ������ѧ����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxdkRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxdkRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * ������ѧ����� gjzxdkExp ---- ������ѧ�����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getGjzxdkExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_zjjd_gjzxdk",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_zjjd_gjzxdk");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ���������ѧ���������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkShxx(GjzxdkModel gjzxdkModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveGjzxdkShxx(gjzxdkModel, request);
	}
	
	/**
	 * ��ȡѧ�Ѽ�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfjmxx(String pkVal) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getXfjmxx(pkVal);
	}
	
	/**
	 * �õ�ѧ�Ѽ�������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getXfjmSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getXfjmSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * ���ѧ�Ѽ����б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getXfjmList() throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getXfjmList();
	}
	
	/**
	 * ����ѧ�Ѽ���������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfjmSqxx(XfjmModel xfjmModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveXfjmSqxx(xfjmModel, request);
	}
	
	/**
	 * �õ�ѧ�Ѽ���������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfjmSqb(XfjmModel xfjmModel,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getXfjmSqb(xfjmModel,request);
	}
	
	/**
	 * ɾ��ѧ�Ѽ�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delXfjmxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.delXfjmxx(cbVal, request);
	}
	
	/**
	 * �����޸�ѧ�Ѽ�����˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modXfjmxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.modXfjmxx(cbVal, shjg, request);
	}
	
	/**
	 * ѧ�Ѽ����ѯ��ͷ Xfjmtit ---- ѧ�Ѽ����ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfjmTit() throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getXfjmTit();
	}
	
	/**
	 * ѧ�Ѽ����ѯ��� Xfjmres ---- ѧ�Ѽ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfjmRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXfjmRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * ѧ�Ѽ��⵼�� XfjmExp ---- ѧ�Ѽ��⵼��
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getXfjmExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_zjjd_xfjm",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_zjjd_xfjm");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ѧ�Ѽ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfjmShxx(XfjmModel xfjmModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveXfjmShxx(xfjmModel, request);
	}
	
	/**
	 * ��ȡѧ�ѻ�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfhjxx(String pkVal) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getXfhjxx(pkVal);
	}
	
	/**
	 * �õ�ѧ�ѻ�������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getXfhjSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getXfhjSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * ����ѧ�ѻ���������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhjSqxx(XfhjModel saveXfhjModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveXfhjSqxx(saveXfhjModel, request);
	}
	
	/**
	 * �õ�ѧ�ѻ���������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfhjSqb(XfhjModel xfhjModel,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getXfhjSqb(xfhjModel,request);
	}
	
	/**
	 * ɾ��ѧ�ѻ�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delXfhjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.delXfhjxx(cbVal, request);
	}
	
	/**
	 * �����޸�ѧ�ѻ�����˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modXfhjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.modXfhjxx(cbVal, shjg, request);
	}
	
	/**
	 * ѧ�ѻ��ɲ�ѯ��ͷ xfhjtit ---- ѧ�ѻ��ɲ�ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfhjTit() throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getXfhjTit();
	}
	
	/**
	 * ѧ�ѻ��ɲ�ѯ��� xfhjres ---- ѧ�ѻ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfhjRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXfhjRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * ѧ�ѻ��ɵ��� xfhjExp ---- ѧ�ѻ��ɵ���
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getXfhjExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_zjjd_xfhj",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_zjjd_xfhj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ����ѧ�ѻ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhjShxx(XfhjModel saveXfhjModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveXfhjShxx(saveXfhjModel, request);
	}
	
	/**
	 * ɾ���ſ���Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delFkxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		dao.delFkxx(cbVal, request);
	}
	
	/**
	 *�ſ���Ϣ��ѯ��ͷ fkxxtit ---- �ſ���Ϣ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getFkxxTit() throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getFkxxTit();
	}
	
	/**
	 * �ſ���Ϣ��ѯ��� fkxxres ---- �ſ���Ϣ���
	 * 
	 * @param queryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getFkxxRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZjjdDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getFkxxRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * ��ȡ�ſ���Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getFkxx(String pkVal) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.getFkxx(pkVal);
	}
	
	/**
	 * ����ſ���Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveFkxx(FkxxModel fkxxModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		return dao.saveFkxx(fkxxModel, request);
	}
	
	/**
	 * �ſ���Ϣ���� fkxxExp ---- �ſ���Ϣ����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getFkxxExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZjjdDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zjjd_zxdk_fkxx",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zjjd_zxdk_fkxx");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
}
