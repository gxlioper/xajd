package xgxt.xszz.jhzyjsxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.base.Excel2Oracle;

/**
 * Title: ѧ����������ϵͳ
 * Description: ��ְҵ����ѧԺѧ������Service
 * Copyright: Copyright (c) 2009
 * Company: zfsoft
 * Author: ����
 * Version: 1.0
 * Time: 2009-09-25
 */
public class XszzJhzyService {

	XszzJhzyDAO dao = null;// ���ݲ���DAO

	/**
	 * ��ȡѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getStu(xh);
	}

	/**
	 * ��ȡ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsxx(String pkVal) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getKnsxx(pkVal);
	}

	/**
	 * �õ�����������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getKnsSqQx(String zzType, String sUserType, String userDep,
			String xh, String nd) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getKnsSqQx(zzType, sUserType, userDep, xh, nd);
	}

	/**
	 * ����������������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsSqxx(KnssqbModel saveKnsModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.saveKnsSqxx(saveKnsModel, request);
	}

	/**
	 * �õ�������������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsSqb(KnssqbModel knsModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getKnsSqb(knsModel, request);
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
		dao = new XszzJhzyDAO();
		return dao.getKnsTit();
	}

	/**
	 * ��������ѯ��� knsres ---- ������
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsRes(QueryModel queryModel,
			HttpServletRequest request, XszzJhzyjsxyActionForm actionForm)
			throws Exception {
		dao = new XszzJhzyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsRes(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * ��������Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnsResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getKnsResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * ɾ����������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnsxx(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		dao.delKnsxx(cbVal, request);
	}

	/**
	 * �����޸���������˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modKnsxx(String[] cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
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
	public void getKnsExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_jhzy_kns", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_jhzy_kns");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * ���������������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsShxx(KnssqbModel saveKnsModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.saveKnsShxx(saveKnsModel, request);
	}

	/**
	 * ��ȡ�������ǼǱ���Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsdjbxx(String pkVal) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getKnsdjbxx(pkVal);
	}

	/**
	 * �����������ǼǱ���Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsdjbxx(KnsdjbModel saveKnsModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.saveKnsdjbxx(saveKnsModel, request);
	}

	/**
	 * �õ��������ǼǱ��ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsdjbdy(KnsdjbModel knsModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getKnsdjbdy(knsModel, request);
	}

	/**
	 * ɾ���������ǼǱ���Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnsdjb(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		dao.delKnsdjb(cbVal, request);
	}

	/**
	 * �������ǼǱ��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsdjbTit() throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getKnsdjbTit();
	}

	/**
	 * �������ǼǱ�
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsdjbRes(QueryModel queryModel,
			HttpServletRequest request, XszzJhzyjsxyActionForm actionForm)
			throws Exception {
		dao = new XszzJhzyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsdjbRes(queryModel, request, actionForm);
		}
		return resList;
	}

	/**
	 * �������ǼǱ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getKnsdjbResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getKnsdjbResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * �������ǼǱ���
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getKnsdjbExp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xg_zz_knsdjb", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xg_zz_knsdjb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * ��ȡ������ѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjxx(String pkVal) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getGjzxjxx(pkVal);
	}

	/**
	 * ���������ѧ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxjSqxx(GjzxjModel model, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		return dao.saveGjzxjSqxx(model, request);
	}

	/**
	 * �õ�������ѧ��������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjSqb(GjzxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getGjzxjSqb(model, request);
	}

	/**
	 * ɾ��������ѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjzxj(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		dao.delGjzxj(cbVal, request);
	}

	/**
	 * �����޸Ĺ�����ѧ����˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjzxj(String[] cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		dao.modGjzxj(cbVal, shjg, request);
	}

	/**
	 * ������ѧ���ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxjTit() throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getGjzxjTit();
	}

	/**
	 * ������ѧ���ѯ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxjRes(QueryModel queryModel,
			HttpServletRequest request,
			XszzJhzyjsxyActionForm jhzyjsxyActionForm) throws Exception {
		dao = new XszzJhzyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxjRes(queryModel, request, jhzyjsxyActionForm);
		}
		return resList;
	}

	/**
	 * ������ѧ���ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjzxjResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getGjzxjResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * ������ѧ�𵼳�
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getGjzxjExp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_jhzy_gjzxj", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_jhzy_gjzxj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * ���������ѧ�������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxjShxx(GjzxjModel model, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		return dao.saveGjzxjShxx(model, request);
	}

	/**
	 * �õ�������ѧ���б�
	 * 
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public ArrayList<HashMap<String, String>> getGjzxjList() throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getGjzxjList();
	}

	/**
	 * �õ�������ѧ��������Ȩ��
	 * 
	 * @param sUserType,userDep,xh,nd
	 * @return 1 �����룻-1 ��������
	 * @throws Exception
	 */
	public String getBkzxjjSqQx(String sUserType, String userDep, String xh,
			String nd) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getBkzxjjSqQx(sUserType, userDep, xh, nd);
	}

	/**
	 * �Ƿ�������
	 * 
	 * @throws Exception
	 */
	public boolean isKns(String xh) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.isKns(xh);
	}

	/**
	 * �в������Ŀ
	 * 
	 * @throws Exception
	 */
	public boolean isBjgkm(String nd, String xh) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.isBjgkm(nd, xh);
	}

	/**
	 * ������־��ѧ��
	 * 
	 * @throws Exception
	 */
	public void serv_saveGjlzjxjSq(XszzGjlzjxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		dao.saveGjlzjxjSq(model, request);
	}

	/**
	 * ��ȡ������־��ѧ�������Ϣ
	 */
	public HashMap<String, String> serv_getGjlzJxjInfo(String pkVlaue) {
		dao = new XszzJhzyDAO();
		String querry = " where nd||xh='" + pkVlaue + "' ";
		return dao.dao_getInfo("xszz_jhzy_gjlzjxj", querry);
	}

	/**
	 * ������־��ѧ��
	 */
	public List<HashMap<String, String>> ser_getGjlzJxjTit() throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getGjlzJxjTit();
	}

	/**
	 * ������־��ѧ���ѯ���
	 */
	public List<String[]> serv_getGjlzJxjRes(QueryModel queryModel,
			HttpServletRequest request,
			XszzJhzyjsxyActionForm jhzyjsxyActionForm) throws Exception {
		dao = new XszzJhzyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao
					.getGjlzJxjRes(queryModel, request, jhzyjsxyActionForm);
		}
		return resList;
	}

	/**
	 * ������־��ѧ���ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjlzJxjResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getGjlzJxjResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * �õ�������־��ѧ��������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxjSqb(XszzGjlzjxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getGjlzjxjSqb(model, request);
	}

	/**
	 * �����޸� ������־��ѧ����˽��
	 */
	public void serv_modGjlzJxjsxx(String[] cbVal, String shjg,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		dao.modGjlzjxjxx(cbVal, shjg, request);
	}

	/**
	 * ɾ����������Ϣ
	 */
	public void serv_delGjlzjxjxx(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		dao.delGjlzjxjxx(cbVal, request);
	}

	/**
	 * ������־��ѧ�����
	 * 
	 * @throws Exception
	 */
	public boolean serv_saveGjlzjxjSh(XszzGjlzjxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.saveGjlzjxjSh(model, request);
	}

	/**
	 * ������־��ѧ��������ӡ
	 */
	public HashMap<String, String> serv_getGjlzJxjb(XszzGjlzjxjModel model) {
		dao = new XszzJhzyDAO();
		return dao.getGjlzJxjb(model);
	}

	/**
	 * �����ȼ��б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZzdjList() throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getZzdjList();
	}

	/**
	 * ���������ѧ����������Ϣ���ɹ�����TRUE����֮����FALSE
	 * 
	 * @param saveModel
	 *            (���ݱ���ʵ��MODEL),request
	 * @return
	 * @throws Exception
	 */
	public boolean saveBkzxjjSqxx(BkzxjjModel model, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		return dao.saveBkzxjjSqxx(model, request);
	}

	/**
	 * ��ȡ������ѧ������ϸ
	 * 
	 * @param pkVal
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getBkzxjjXx(String pkVal) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getBkzxjjXx(pkVal);
	}

	/**
	 * ������ѧ�����ѯ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getBkzzjjRes(QueryModel queryModel,
			HttpServletRequest request,
			XszzJhzyjsxyActionForm jhzyjsxyActionForm) throws Exception {
		dao = new XszzJhzyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getBkzzjjRes(queryModel, request, jhzyjsxyActionForm);
		}
		return resList;
	}

	/**
	 * ������ѧ�����ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getBkzzjjResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getBkzzjjResNum(queryModel, request);
		}
		return sT;
	}

	/**
	 * �����޸İ�����ѧ��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modBkzxjjxx(String[] cbVal, String shjg,
			HttpServletRequest request) throws Exception {
		dao = new XszzJhzyDAO();
		dao.modBkzxjjxx(cbVal, shjg, request);
	}

	/**
	 * ɾ��������ѧ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delBkzxjj(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		dao.delBkzxjj(cbVal, request);
	}

	/**
	 * ���������������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveBkzxjjShxx(BkzxjjModel model, HttpServletRequest request)
			throws Exception {
		dao = new XszzJhzyDAO();
		return dao.saveBkzxjjShxx(model, request);
	}

	public String getZxjjXx(String zxjjdm) throws Exception {
		dao = new XszzJhzyDAO();
		return dao.getZxjjXx(zxjjdm);
	}
}
