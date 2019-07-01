package xgxt.xszz.hzzyjsxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.base.Excel2Oracle;

/**
 * Title: ѧ����������ϵͳ
 * Description: ����ְҵ����ѧԺѧ������Service
 * Copyright: Copyright (c) 2008
 * Company: zfsoft
 * Author: ����
 * Version: 1.0
 * Time: 2008-07-08
 */
public class XszzHzzyService {

	XszzHzzyDAO dao = null;// ���ݲ���DAO

	/**
	 * ��Уѧ�������ѯ��ͷ zxxsdktit ---- ��Уѧ��������Ϣ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZxxsDkxxTit() throws Exception {
		dao = new XszzHzzyDAO();
		return dao.getZxxsDkxxTit();
	}

	/**
	 * ��Уѧ�������ѯ��� zxxsdkres ---- ��Уѧ��������
	 * 
	 * @param queryZxxsdkModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getShJxjRes(QueryModel queryZxxsdkModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzHzzyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryZxxsdkModel.getGo())) {
			resList = dao.getZxxsDkxxRes(queryZxxsdkModel,request);
		}
		return resList;
	}

	/**
	 * ��ȡ��Уѧ��������ϸ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getDkxx(String pkVal) throws Exception {
		dao = new XszzHzzyDAO();
		return dao.getDkxx(pkVal);
	}

	/**
	 * ��ȡѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzHzzyDAO();
		return dao.getStu(xh);
	}

	/**
	 * ���������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveDkxx(SaveZxxsdkModel saveZxxsdkModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzHzzyDAO();
		return dao.saveDkxx(saveZxxsdkModel, request);
	}

	/**
	 * ɾ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delDkxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzHzzyDAO();
		dao.delDkxx(cbVal, request);
	}

	/**
	 * ��Уѧ������� zxxsdkExp ---- ��Уѧ�������
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getZxxsDkxxExp(QueryModel queryZxxsdkModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzHzzyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryZxxsdkModel, "view_xszz_hzzyjsxy_zxxsdkxx",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_hzzyjsxy_zxxsdkxx");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ��ȡ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsxx(String pkVal) throws Exception {
		dao = new XszzHzzyDAO();
		return dao.getKnsxx(pkVal);
	}
	
	/**
	 * �õ�����������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getKnsSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzHzzyDAO();
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
		dao = new XszzHzzyDAO();
		return dao.saveKnsSqxx(saveKnsModel, request);
	}
	
	/**
	 * �õ�������������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsSqb(KnsModel knsModel,HttpServletRequest request) throws Exception {
		dao = new XszzHzzyDAO();
		return dao.getKnsSqb(knsModel,request);
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
		dao = new XszzHzzyDAO();
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
		dao = new XszzHzzyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * ɾ����������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnsxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzHzzyDAO();
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
		dao = new XszzHzzyDAO();
		dao.modKnsxx(cbVal, shjg, request);
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
		dao = new XszzHzzyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_hzzy_knssqb",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_hzzy_knssqb");
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
		dao = new XszzHzzyDAO();
		return dao.saveKnsShxx(saveKnsModel, request);
	}
	
	/**
	 * ��ȡ���ҽ�ѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjjxjxx(String pkVal) throws Exception {
		dao = new XszzHzzyDAO();
		return dao.getGjjxjxx(pkVal);
	}
	
	/**
	 * �õ����ҽ�ѧ������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getGjjxjSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzHzzyDAO();
		return dao.getGjjxjSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * ������ҽ�ѧ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjjxjSqxx(GjjxjModel gjjxjModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzHzzyDAO();
		return dao.saveGjjxjSqxx(gjjxjModel, request);
	}
	
	/**
	 * �õ����ҽ�ѧ��������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjjxjSqb(GjjxjModel gjjxjModel,HttpServletRequest request) throws Exception {
		dao = new XszzHzzyDAO();
		return dao.getGjjxjSqb(gjjxjModel,request);
	}
	
	/**
	 * ɾ�����ҽ�ѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjjxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzHzzyDAO();
		dao.delGjjxjxx(cbVal, request);
	}
	
	/**
	 * �����޸Ĺ��ҽ�ѧ����˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjjxjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzHzzyDAO();
		dao.modGjjxjxx(cbVal, shjg, request);
	}
	
	/**
	 * ���ҽ�ѧ���ѯ��ͷ gjjxjtit ---- ���ҽ�ѧ���ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjjxjTit() throws Exception {
		dao = new XszzHzzyDAO();
		return dao.getGjjxjTit();
	}
	
	/**
	 * ���ҽ�ѧ���ѯ��� gjjxjres ---- ���ҽ�ѧ��
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjjxjRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzHzzyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjjxjRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * ���ҽ�ѧ�𵼳� gjjxjExp ---- ���ҽ�ѧ�𵼳�
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getGjjxjExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzHzzyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_hzzy_xszz_gjjxj",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_hzzy_xszz_gjjxj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ������ҽ�ѧ�������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjjxjShxx(GjjxjModel gjjxjModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzHzzyDAO();
		return dao.saveGjjxjShxx(gjjxjModel, request);
	}
	
	/**
	 * ��ȡ������ѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjxx(String pkVal) throws Exception {
		dao = new XszzHzzyDAO();
		return dao.getGjzxjxx(pkVal);
	}
	
	/**
	 * �õ�������ѧ������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getGjzxjSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzHzzyDAO();
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
		dao = new XszzHzzyDAO();
		return dao.saveGjzxjSqxx(gjzxjModel, request);
	}
	
	/**
	 * �õ�������ѧ��������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjSqb(GjzxjModel gjzxjModel,HttpServletRequest request) throws Exception {
		dao = new XszzHzzyDAO();
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
		dao = new XszzHzzyDAO();
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
		dao = new XszzHzzyDAO();
		dao.modGjzxjxx(cbVal, shjg, request);
	}
	
	/**
	 * ������ѧ���ѯ��ͷ gjjxjtit ---- ������ѧ���ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxjTit() throws Exception {
		dao = new XszzHzzyDAO();
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
		dao = new XszzHzzyDAO();
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
		dao = new XszzHzzyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_hzzy_xszz_gjzxj",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_hzzy_xszz_gjzxj");
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
		dao = new XszzHzzyDAO();
		return dao.saveGjzxjShxx(gjzxjModel, request);
	}
}
