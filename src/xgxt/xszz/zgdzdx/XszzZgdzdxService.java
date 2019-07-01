package xgxt.xszz.zgdzdx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.utils.CommonQueryDAO;
import xgxt.xszz.zgmsxy.XszzZgmsxyActionForm;
import xgxt.xszz.zgmsxy.XszzZgmsxyDAO;
import xgxt.form.User;

/**
 * Title: ѧ����������ϵͳ
 * Description:�й����ʴ�ѧѧ������Service
 * Copyright: Copyright (c) 2008
 * Company: zfsoft
 * Author: ����
 * Version: 1.0
 * Time: 2008-10-09
 */
public class XszzZgdzdxService {

	XszzZgdzdxDAO dao = null;// ���ݲ���DAO

	/**
	 * ��ȡѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getStu(xh);
	}
	
	/**
	 * ��ȡ��ͥ���������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJtqkdcxx(String pkVal) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getJtqkdcxx(pkVal);
	}
	
	/**
	 * �õ���ͥ�����������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getJtqkdcSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getJtqkdcSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * �����ͥ�������������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveJtqkdcSqxx(JtqkdcModel saveJtqkdcModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveJtqkdcSqxx(saveJtqkdcModel, request);
	}
	
	/**
	 * �õ���ͥ�������������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJtqkdcSqb(JtqkdcModel model,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getJtqkdcSqb(model,request);
	}
	
	/**
	 * ɾ����ͥ���������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delJtqkdcxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.delJtqkdcxx(cbVal, request);
	}
	
	/**
	 * �����޸ļ�ͥ���������˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modJtqkdcxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.modJtqkdcxx(cbVal, shjg, request);
	}
	
	/**
	 * ��ͥ��������ѯ��ͷ Jtqkdctit ---- ��ͥ��������ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJtqkdcTit() throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getJtqkdcTit();
	}
	
	/**
	 * ��ͥ��������ѯ��� Jtqkdcres ---- ��ͥ�������
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJtqkdcRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getJtqkdcRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * ��ͥ������鵼�� JtqkdcExp ---- ��ͥ������鵼��
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getJtqkdcExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zgdzdx_kns_jtqkdc",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zgdzdx_kns_jtqkdc");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * �����ͥ������������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveJtqkdcShxx(JtqkdcModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveJtqkdcShxx(model, request);
	}
	
	/**
	 * ��ȡ�������϶���Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrdxx(String pkVal) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getKnsrdxx(pkVal);
	}
	
	/**
	 * �õ��������϶�����Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getKnsrdSqQx(String sUserType,String userDep,String xh,String xn) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getKnsrdSqQx(sUserType,userDep,xh,xn);
	}
	
	/**
	 * �õ��������϶���˹涨ʱ��
	 * 
	 * @return String[]
	 * @throws Exception
	 */
	public String[] getKnsrdShsj(String userDep) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getKnsrdShsj(userDep);
	}
	
	/**
	 * �õ��������϶����Ȩ��
	 * 
	 * @return 
	 * @throws Exception
	 */
	public String getKnsrdShQx(String userType,String userDep) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getKnsrdShQx(userType,userDep);
	}
	
	/**
	 * �����������϶�������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrdSqxx(KnsrdModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveKnsrdSqxx(model, request);
	}
	
	/**
	 * �õ��������϶�������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrdSqb(KnsrdModel model,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getKnsrdSqb(model,request);
	}
	
	/**
	 * ɾ���������϶���Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnsrdxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.delKnsrdxx(cbVal, request);
	}
	
	/**
	 * ɾ��չ��Э����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delZqxyxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.delZqxyxx(cbVal, request);
	}
	
	/**
	 * ����Ϣת����ʷ��Ϣ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void addGjzxdkLsxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.addGjzxdkLsxx(cbVal, request);
	}
	
	/**
	 * ɾ������Э����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delHkxyxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.delHkxyxx(cbVal, request);
	}
	
	/**
	 * ɾ��չ�ں󻹿�Э����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delZqhhkxyxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.delZqhhkxyxx(cbVal, request);
	}
	
	/**
	 * �����޸��������϶���˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modKnsrdxx(String cbVal, String shType, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.modKnsrdxx(cbVal, shType, shjg, request);
	}
	
	/**
	 * �����޸�չ��Э����˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modZqxyxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.modZqxyxx(cbVal, shjg, request);
	}
	
	/**
	 * �����޸Ļ���Э����˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modHkxyxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.modHkxyxx(cbVal, shjg, request);
	}
	
	/**
	 * �����޸�չ�ں󻹿�Э����˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modZqhhkxyxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.modZqhhkxyxx(cbVal, shjg, request);
	}
	
	/**
	 * �������϶���ѯ��ͷ Knsrdtit ----�������϶���ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsrdTit() throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getKnsrdTit();
	}
	
	/**
	 * չ��Э���ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZqxyTit() throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getZqxyTit();
	}
	
	/**
	 * ����Э���ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getHkxyTit() throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getHkxyTit();
	}
	
	/**
	 * չ�ں󻹿�Э���ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZqhhkxyTit() throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getZqhhkxyTit();
	}
	
	/**
	 * �������϶���ѯ��� Knsrdres ---- �������϶�
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsrdRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsrdRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * չ��Э���ѯ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZqxyRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getZqxyRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * ����Э���ѯ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getHkxyRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getHkxyRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * չ�ں󻹿�Э���ѯ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZqhhkxyRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getZqhhkxyRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * �������϶����� KnsrdExp ---- �������϶�����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getKnsrdExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zgdzdx_kns_pksrd",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zgdzdx_kns_pksrd");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * չ��Э����Ϣ����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getZqxyExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zgdzdx_zqxy",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zgdzdx_zqxy");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ����Э����Ϣ����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getHkxyExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();
		queryModel.setXz("1");

		rs = dao.getExpDate(queryModel, "view_zgdzdx_hkjzqhhkxyxx",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zgdzdx_hkjzqhhkxyxx");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * չ�ں󻹿�Э����Ϣ����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getZqhhkxyExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();
		queryModel.setXz("2");

		rs = dao.getExpDate(queryModel, "view_zgdzdx_hkjzqhhkxyxx",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zgdzdx_hkjzqhhkxyxx");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * �����������϶������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsrdShxx(KnsrdModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveKnsrdShxx(model, request);
	}
	
	/**
	 * �õ����к�ͬ���˵��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getHtbh(String xydm) throws Exception {
		dao = new XszzZgdzdxDAO();
		StringBuffer sb = new StringBuffer("");
		String zdhth = "0001";
		
		ArrayList<HashMap<String, String>> list = dao.getHtbh(xydm);

		boolean b = true;
		for (HashMap<String, String> hp : list){
			if (b) {
				sb.append(hp.get("zxhth"));
				sb.append("-");
				b = false;
			} else {
				if (Integer.parseInt(hp.get("zxhth")) != (Integer.parseInt(zdhth)+1)){
					sb.append(zdhth);
					sb.append(",");
					sb.append(hp.get("zxhth"));
					sb.append("-");
				}
			}
			zdhth = hp.get("zdhth");
		}
		sb.append(zdhth);
		return sb.toString();
	}
	
	/**
	 * �õ����к�ͬ����б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getHtbhList(String xydm) throws Exception {
		dao = new XszzZgdzdxDAO();
		StringBuffer sb = new StringBuffer("!!OneSpile!!");
		
		ArrayList<HashMap<String, String>> list = dao.getHtbh(xydm);
		
		for (HashMap<String, String> hp : list){
			for (int i = Integer.parseInt(hp.get("zxhth")); i <= Integer.parseInt(hp.get("zdhth")); i++){
				sb.append(i);
				sb.append("!!OneSpile!!");
			}
		}
		return sb.toString();
	}
	
	/**
	 * �õ���˽���б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getshList() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		
		HashMap<String, String> tHA = new HashMap<String, String>();
		tHA.put("shmc", "һ������");
		HashMap<String, String> tHB = new HashMap<String, String>();
		tHB.put("shmc", "����");
		HashMap<String, String> tHC = new HashMap<String, String>();
		tHC.put("shmc", "��������");
		HashMap<String, String> tHD = new HashMap<String, String>();
		tHD.put("shmc", "������");
		HashMap<String, String> tHE = new HashMap<String, String>();
		tHE.put("shmc", "δ���");
		
		list.add(tHA);
		list.add(tHB);
		list.add(tHC);
		list.add(tHD);
		list.add(tHE);
		
		return list;
	}
	
	/**
	 * ɾ����Դ�ش�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delSyddk(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.delSyddk(cbVal, request);
	}
	
	/**
	 * ��Դ�ش����ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSyddkTit() throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getSyddkTit();
	}
	
	/**
	 * ��Դ�ش����ѯ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSyddkRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getSyddkRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * ��Դ�ش����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getSyddkExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zgdzdx_syddk",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zgdzdx_syddk");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ��ȡ������ѧ����������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkxx(String pkVal) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getGjzxdkxx(pkVal);
	}
	
	/**
	 * ��ȡ������ѧ������ʷ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdklsxx(String pkVal) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getGjzxdklsxx(pkVal);
	}
	
	/**
	 * ��ȡ������ѧ�����ͬ����
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getHtmc(String xh) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getHtmc(xh);
	}
	
	/**
	 * ��ȡ������ѧ�������ͨ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdktgxx(String pkVal) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getGjzxdktgxx(pkVal);
	}
	
	/**
	 * ��ȡ����Э����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> gethkxyxx(String pkVal) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.gethkxyxx(pkVal);
	}
	
	/**
	 * ��ȡչ�ں󻹿�Э����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getzqhhkxyxx(String pkVal) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getzqhhkxyxx(pkVal);
	}
	
	/**
	 * ��ȡչ��Э����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getzqxyxx(String pkVal) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getzqxyxx(pkVal);
	}
	
	
	
	/**
	 * ���������ѧ����������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkSqxx(GjzxdkModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveGjzxdkSqxx(model, request);
	}
	
	/**
	 * ���������ѧ������ϸ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkxx(GjzxdkModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveGjzxdkxx(model, request);
	}
	
	/**
	 * ���������ѧ������ʷ��ϸ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdklsxx(GjzxdkLsModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveGjzxdklsxx(model, request);
	}
	
	/**
	 * ͨ��ѧ����Ϣ�õ�������ѧ�����Զ���������
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxdkStu(HashMap<String, String> map) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getZxdkStu(map);
	}
	
	/**
	 * ͨ��ѧ�ŵõ�ѧ����������
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] getDkje(String xh) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getZxdkJe(xh);
	}
	
	/**
	 * ͨ��ѧ�ŵõ�ѧ��ÿ�������
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] getMndkje(String xh) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getMndkje(xh);
	}
	
	/**
	 * �õ�������ѧ����������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkSqb(HashMap<String, String> map,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getGjzxdkSqb(map,request);
	}
	
	/**
	 * ɾ��������ѧ������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjzxdkxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.delGjzxdkxx(cbVal, request);
	}
	
	/**
	 * ������ѧ�����ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxdkTit() throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getGjzxdkTit();
	}
	
	/**
	 * ������ѧ�����ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxdkTitT() throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getGjzxdkTitT();
	}
	
	/**
	 * ������ѧ������ʷ��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxdklsTit() throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getGjzxdklsTit();
	}
	
	/**
	 * ������ѧ�����ѯ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxdkRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxdkRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * ������ѧ�����ѯ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxdkResT(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxdkResT(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * ������ѧ������ʷ��ѯ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxdklsRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxdklsRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * ������ѧ����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getGjzxdkExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zdgzgx_gjzxdk",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zdgzgx_gjzxdk");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ������ѧ������ʷ��Ϣ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getGjzxdklsExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "zgdzdx_gjzxdk_ls",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("zgdzdx_gjzxdk_ls");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ���������ѧ���������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkShxx(GjzxdkModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveGjzxdkShxx(model, request);
	}
	
	/**
	 * �õ�ѧԺ�涨�����ͬ����б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getXyHtbhList(String xydm) throws Exception {
		dao = new XszzZgdzdxDAO();
		StringBuffer sb = new StringBuffer("!!OneSpile!!");
		
		String[] sT = dao.getXyHtbh(xydm);
		
		if (sT != null) {
			for (int i = Integer.parseInt(sT[0]); i <= Integer.parseInt(sT[1]); i++) {
				sb.append(i);
				sb.append("!!OneSpile!!");
			}
		}
		return sb.toString();
	}
	
	/**
	 * �õ��ѷ����ͬ����б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getYyHtbhList(String xh,String nd) throws Exception {
		dao = new XszzZgdzdxDAO();
		StringBuffer sb = new StringBuffer("!!OneSpile!!");
		
		ArrayList<HashMap<String, String>> list = dao.getYyHtbhList(xh,nd);
		
		for (HashMap<String, String> hp : list){
			if (null != hp.get("htbh") && hp.get("htbh").length() == 19){
				sb.append(hp.get("htbh").substring(15));
				sb.append("!!OneSpile!!");
			}
		}
		return sb.toString();
	}
	
	
	
	public List<HashMap<String, String>> getHtList(HashMap<String, String> map) throws Exception {
		dao = new XszzZgdzdxDAO();
		
		String[] sT = dao.getXyHtbh(map.get("xydm"));
		ArrayList<HashMap<String, String>> list = dao.getYyHtbhList(map.get("xh"),map.get("nd"));
		
		ArrayList<String> htList = new ArrayList<String>();
		boolean b = true;
		
		if (sT != null) {
			for (int i = Integer.parseInt(sT[0]); i <= Integer.parseInt(sT[1]); i++) {
				for (Iterator<HashMap<String, String>> it = list.iterator(); it.hasNext() && b; ){
					HashMap<String, String> hp = it.next();
					if (null != hp.get("htbh") && hp.get("htbh").length() == 19 && i == Integer.parseInt(hp.get("htbh").substring(15))) {
						b = false;
					}
				}
				
				if (b){
					String ht = "";
					switch (String.valueOf(i).length()) {
					case 1:
						ht = "000" + String.valueOf(i);
						break;
					case 2:
						ht = "00" + String.valueOf(i);
						break;
					case 3:
						ht = "0" + String.valueOf(i);
						break;
					case 4:
						ht = String.valueOf(i);
						break;
					default:
						break;
					}
					htList.add(ht);
				}
				b = true;
			}
		}
		String[] temp = htList.toArray(new String[] {});
		return DAO.getInstance().arrayToList(temp,temp);
	}
	
	/**
	 * �õ�����ȷ��������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getZlqrsSqQx(String sUserType,String userDep,String xh) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getZlqrsSqQx(sUserType,userDep,xh);
	}
	
	/**
	 * �õ�����Э������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getHkxyQx(String sUserType,String userDep,String xh) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getHkxyQx(sUserType,userDep,xh);
	}
	
	/**
	 * �õ�չ�ں󻹿�Э������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getZqhHkxyQx(String sUserType,String userDep,String xh) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getZqhHkxyQx(sUserType,userDep,xh);
	}
	
	/**
	 * �õ�չ��Э������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getZqxyQx(String sUserType,String userDep,String xh) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getZqxyQx(sUserType,userDep,xh);
	}
	
	/**
	 * ��������ȷ����������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveZlqrsSqxx(GjzxdkModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveZlqrsSqxx(model, request);
	}
	
	/**
	 * �õ�����ȷ����������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZlqrsSqb(HashMap<String, String> map,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getZlqrsSqb(map,request);
	}
	
	/**
	 * ���������ѧ������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkxx(GjzxdkModel model, String xz,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveGjzxdkxx(model, xz, request);
	}
	
	/**
	 * ����չ��Э����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveZqxyxx(ZqxyModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveZqxyxx(model, request);
	}
	
	/**
	 * ����ģ���ʽ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean insertTemplate(String topstr, String leftstr, String fontstr, String tmpname,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.insertTemplate(topstr, leftstr, fontstr, tmpname, request);
	}
	
	/**
	 * @author ZhouMi
	 * @describe �õ�����ѧ����Ŀ�б�
	 */
	public synchronized List<HashMap<String, String>> getJzxjxmList() throws Exception{
		dao = new XszzZgdzdxDAO();
		return dao.getJzxjxmList();
	}
	
	/**
	 * @author ZhouMi
	 * @describe ���潱��ѧ���������
	 */
	public void saveJzxjjtrs(String[] pks, String[] szrs, String[] sfyx,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.saveJzxjjtrs(pks, szrs, sfyx, request);
	}
	
	/**
	 * �õ�ģ���ʽ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getTeplateInf(String name) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getTeplateInf(name);
	}
	
	/**
	 * ��ȡ������ѧ�����ݴ�ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkju(String xh,String qs,String jjrq) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getGjzxdkju(xh,qs,jjrq);
	}

	/**
	 * ��ȡѧ������ѧ�������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJzxjInfo(String pkValue) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getJzxjInfo(pkValue);
	}
	
	/**
	 * ɾ���ۺϲ�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delZhcpxx(String[] cbVal) throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.delZhcpxx(cbVal);
	}
	
	/**
	 * �ۺϲ�����ѯ��ͷ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZhcpTit() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "pk", "xn", "xqmc", "xh", "xm", "nj",
				"xymc", "zymc", "bjmc", "zhszzcj", "zhszpm" };
		String[] cnList = new String[] { "����", "ѧ��", "ѧ��", "ѧ��", "����", "�꼶",
				Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "�ۺϲ����ܳɼ�", "�ۺϲ�������" };
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}// end for
		return topList;
	}
	
	/**
	 * �ۺϲ�����ѯ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZhcpRes(QueryModel queryModel,
			HttpServletRequest request, XszzZgdzdxActionForm actionForm)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getZhcpRes(queryModel, request, actionForm);
		}
		return resList;
	}
	
	/**
	 * �ۺϲ�����Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getZhcpResNum(QueryModel queryModel, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getZhcpResNum(queryModel, request);
		}
		return sT;
	}
	
	/**
	 * �ۺϲ�������
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getZhcpExp(QueryModel queryModel, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_jzxj_zhcpxx", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_jzxj_zhcpxx");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ��ȡ�ۺϲ�����ϸ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZhcpxx(String pkVal) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getZhcpxx(pkVal);
	}
	
	/**
	 * �����ۺϲ�����ϸ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveZhcpxx(ZhcpxxModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveZhcpxx(model, request);
	}
	
	/**
	 * ��ȡչ�ڻ�����ϸ��Ϣ
	 * 
	 * @return
	 * @author luojw
	 */
	public HashMap<String, String> getZqxyInfo(String xh) {
		dao = new XszzZgdzdxDAO();
		return dao.getZqxyInfo(xh);
	}
	
	/**
	 * ��ʼ��������ѧ����ʱ������
	 * @return
	 * @throws Exception
	 */
	public boolean xssjszReset() throws Exception{
		dao = new XszzZgdzdxDAO();
		return dao.xssjszReset();
	}
	

	/**
	 * ������ѧ����������������
	 * @return
	 * @throws SQLException
	 */
	public boolean xssjszSave(String[] pkValue,String kssj,String jssj) throws SQLException {
		dao = new XszzZgdzdxDAO();
		
		if (null != pkValue && pkValue.length >0) {
			return dao.xssjszSave(pkValue, kssj, jssj);
		} 
		
		return false;
	}
	
	/**
	 * �ж��Ƿ���ѧ����ʱ���
	 * @param xh
	 * @return
	 * @throws Exception 
	 */
	public boolean getGjzxdkSqQx(String xh) throws Exception {
		dao = new XszzZgdzdxDAO();
		
		String isXs = dao.isXs(xh);//�Ƿ�����
		
		if (Base.isNull(isXs) && !Base.isNull(dao.getLsSqsj(xh))) {//����
			return true;
		} else if (!Base.isNull(isXs) && !Base.isNull(dao.getXsSqsj(xh))){//����
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * ��ȡ�ſ����
	 * @param xh
	 * @return
	 */
	public String getFkcs(String xh) {
		dao = new XszzZgdzdxDAO();
		String isXs = dao.isXs(xh);
		
		if (!Base.isNull(isXs)) {
			return CommonQueryDAO.getStuInfo(xh).get("xz");//�����ſ��������ѧ��
		} else {
			String fkcs = dao.getLsFkcs(xh);
			if (!Base.isNull(fkcs) && Integer.valueOf(fkcs)>0) {
				return fkcs;
			}
		}
		
		return null;
	}
	
	
	/**
	 * ������ѧ�����������
	 * @param xh
	 * @return
	 * @throws Exception 
	 */
	public HashMap<String, String> getDkqx(String xh) throws Exception {
		dao = new XszzZgdzdxDAO();
		
		return dao.getDkqx(xh);
	}
	
	/**
	 * ��Դ�ش����ѯ��ͷ_�人��·
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSyddkTit_whtl() throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.getSyddkTit_whtl();
	}
	
	/**
	 * ��Դ�ش����ѯ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSyddkRes_whtl(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		List<String[]> resList = new ArrayList<String[]>();
//		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getSyddkRes_whtl(queryModel,request);
//		}
		return resList;
	}
	
	/**
	 * ɾ����Դ�ش�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delSyddk_whtl(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgdzdxDAO();
		dao.delSyddk_whtl(cbVal, request);
	}
	
	/**
	 * ������Դ�ش�����Ϣ_�人��·
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveSyddk_whtl(XszzZgdzdxActionForm myForm) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.saveSyddk_whtl(myForm);
	}
	
	/**
	 * �޸���Դ�ش�����Ϣ_�人��·
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateSyddk_whtl(XszzZgdzdxActionForm myForm) throws Exception {
		dao = new XszzZgdzdxDAO();
		return dao.updateSyddk_whtl(myForm);
	}
	
	/**
	 * ��ȡ������Դ�ش����¼_�人��·
	 * 
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getOneSyddk_whtl(XszzZgdzdxActionForm myForm) {
		dao = new XszzZgdzdxDAO();
		return dao.getOneSyddk_whtl(myForm);
	}
	
	/**
	 * ��ͥ������鵼�� JtqkdcExp ---- ��ͥ������鵼��
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getJtqkdcExp_whtl(User user,QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgdzdxDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(user,queryModel, "view_whtl_syddk",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_whtl_syddk");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ����ѧ������
	 * @param pk
	 * @return
	 */
	public String checkXszz(String pk){
		String num="0";
		DAO dao = DAO.getInstance();
		String sql="select count(1) num from whtl_syddk where nd=? and xh=?";
		num=dao.getOneRs(sql, new String[]{Base.currNd,pk}, "num");
		return num;
	}
}
