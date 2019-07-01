package xgxt.xszz.zgmsxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.comm.CommDAO;
import xgxt.form.RequestForm;


/**
 * Title: ѧ����������ϵͳ
 * Description:�й�����ѧԺѧ������Service
 * Copyright: Copyright (c) 2008
 * Company: zfsoft
 * Author: ����
 * Version: 1.0
 * Time: 2008-12-16
 */
public class XszzZgmsxyService {

	XszzZgmsxyDAO dao = null;// ���ݲ���DAO

	/**
	 * ��ȡѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getStu(xh);
	}
	
	/**
	 * ѧ������-��ȡѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStudent(String xh) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getStudent(xh);
	}
	/**
	 * ��ȡ�������϶���Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrdxx(String pkVal) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getKnsrdxx(pkVal);
	}
	
	/**
	 * �õ��������϶�����Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getKnsrdSqQx(String sUserType,String userDep,String xh) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getKnsrdSqQx(sUserType,userDep,xh);
	}
	
	/**
	 * �õ��������϶���˹涨ʱ��
	 * 
	 * @return String[]
	 * @throws Exception
	 */
	public String[] getKnsrdShsj(String userDep) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getKnsrdShsj(userDep);
	}
	
	/**
	 * �õ��������϶����Ȩ��
	 * 
	 * @return 
	 * @throws Exception
	 */
	public String getKnsrdShQx(String userType,String userDep) throws Exception {
		dao = new XszzZgmsxyDAO();
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
		dao = new XszzZgmsxyDAO();
		return dao.saveKnsrdSqxx(model, request);
	}
	
	/**
	 * �õ��������϶�������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsrdSqb(KnsrdModel model,HttpServletRequest request) throws Exception {
		dao = new XszzZgmsxyDAO();
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
		dao = new XszzZgmsxyDAO();
		dao.delKnsrdxx(cbVal, request);
	}
	
	/**
	 * �����޸��������϶���˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modKnsrdxx(String cbVal, String shType, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		dao.modKnsrdxx(cbVal, shType, shjg, request);
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
		dao = new XszzZgmsxyDAO();
		return dao.getKnsrdTit();
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
		dao = new XszzZgmsxyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsrdRes(queryModel,request);
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
		dao = new XszzZgmsxyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zgmsxy_knsxx",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zgmsxy_knsxx");
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
		dao = new XszzZgmsxyDAO();
		return dao.saveKnsrdShxx(model, request);
	}
	
	/**
	 * �õ���˽���б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getshList() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		
		HashMap<String, String> tHB = new HashMap<String, String>();
		tHB.put("shmc", "����");
		HashMap<String, String> tHC = new HashMap<String, String>();
		tHC.put("shmc", "��������");
		HashMap<String, String> tHD = new HashMap<String, String>();
		tHD.put("shmc", "������");
		HashMap<String, String> tHE = new HashMap<String, String>();
		tHE.put("shmc", "δ���");
		
		list.add(tHB);
		list.add(tHC);
		list.add(tHD);
		list.add(tHE);
		
		return list;
	}
	
	/**
	 * ɾ��������ѧ������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjzxdkxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
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
		dao = new XszzZgmsxyDAO();
		return dao.getGjzxdkTit();
	}
	
	/**
	 * ������ѧ�����ϱ���Ϣ��ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxdkSbxxTit() throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getGjzxdkSbxxTit();
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
		dao = new XszzZgmsxyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxdkRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * 2011.9.6 qlj(�޸�:����ҳ)���ط���
	 * ������ѧ�����ѯ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxdkRes(XszzZgmsxyActionForm model,
			QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxdkRes(model,queryModel,request);
		}
		return resList;
	}
	
	/**
	 * ������ѧ�����ϱ���Ϣ��ѯ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxdkSbxxRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxdkSbxxRes(queryModel,request);
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
		dao = new XszzZgmsxyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zgmsxy_gjzxdk",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zgmsxy_gjzxdk");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ������ѧ�����ϱ���Ϣ����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void gjzxdksbExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgmsxyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zgmsxy_gjzxdksbxx",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zgmsxy_gjzxdksbxx");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ��ҵ����Ϣ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getBysxxExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzZgmsxyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_zgmsxy_gjzxdkgrxx",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_zgmsxy_gjzxdkgrxx");
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
		dao = new XszzZgmsxyDAO();
		return dao.saveGjzxdkShxx(model, request);
	}
	
	/**
	 * �����޸Ĺ�����ѧ������˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjzxdkxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		dao.modGjzxdkxx(cbVal, shjg, request);
	}
	
	/**
	 * ��ҵ����Ϣ
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getBysxxRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getBysxxRes(queryModel,request);
		}
		return resList;
	}
	
	
	/**
	 * 2011.9.6 qlj(�޸�:��ҵ����Ϣģ��ӷ�ҳ����)
	 * ��ҵ����Ϣ
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getBysxxRes(XszzZgmsxyActionForm model,
			QueryModel queryModel, HttpServletRequest request) throws Exception {
		dao = new XszzZgmsxyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getBysxxRes(model,queryModel, request);
		}
		return resList;
	}
	
	/**
	 * ��ҵ����Ϣ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getBysxxTit() throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getBysxxTit();
	}
	/**
	 * ɾ����ҵ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delBysxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		dao.delBysxx(cbVal, request);
	}
	/**
	 * �����޸ı�ҵ����Ϣ��˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modBysxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		dao.modBysxx(cbVal, shjg, request);
	}
	/**
	 * ��ȡ��ҵ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getBysxx(String pkVal) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getBysxx(pkVal);
	}
	/**
	 * ���������ѧ�������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean savBysShxx(GjzxdkModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.saveGjzxjShxx(model, request);
	}
	
	/**
	 * ��ȡ������ѧ����������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkxx(String pkVal) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getGjzxdkxx(pkVal);
	}

	/**
	 * �õ�������ѧ��������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getGjzxdkSqQx(String sUserType, String userDep, String xh)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getGjzxdkSqQx(sUserType, userDep, xh);
	}

	/**
	 * ���������ѧ����������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkSqxx(GjzxdkModel model, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.saveGjzxdkSqxx(model, request);
	}

	/**
	 * ����ѧ������-ѧ��������Ϣ
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public boolean saveXsSqxx(GjzxdkModel model, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.saveXsSqxx(model, request);
	}
	
	/**
	 * ����ѧ������-ѧ�����������Ϣ
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public boolean saveXsSqshxx(GjzxdkModel model, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.saveXsSqshxx(model, request);
	}
	/**
	 * �õ���ҵ����Ϣ�ɼ�����Ȩ��
	 * 
	 * @param sUserType,userDep,xh
	 * @return 1 �����룻-1 ��������
	 * @throws Exception
	 */
	public String getGjzxdkSqQx(String sUserType, String xh)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getBysxxcjSqQx(sUserType, xh);
	}
	
	/**
	 * ��ȡ��ҵ��������Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getBysxxcjMap(String pkValue) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getBysxxcjMap(pkValue);
	}
	
	/**
	 * �����ҵ��������Ϣ
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public boolean saveByscjxx(QueryModel model, HttpServletRequest request) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.saveByscjxx(model, request);
	}
	

	public List<String[]>getSyddkList(XszzZgmsxyActionForm myForm) throws Exception{
		dao = new XszzZgmsxyDAO();
		return dao.getSyddkList(myForm);
	}
	
	/**
	 * ������Դ�ش�����Ϣ(2011.11.8 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveSyddk(XszzZgmsxyActionForm myForm) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.saveSyddk(myForm);
	}
	
	public List<HashMap<String, String>> getTopTr(String lx,RequestForm rForm) {

		DAO dao = DAO.getInstance();
		
		String[]en=null;
		String[]cn=null;
		if("syddk".equalsIgnoreCase(lx)){
			 en =new String[] { "xn", "xh", "xm", "nj", "xymc", "zymc", "bjmc", "xfbz",
				"sqje","ffje" };
			 cn =new String[] { "ѧ��", "ѧ��", "����", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶", "ѧ�ѱ�׼",
				"������","���Ž��" };
		}else if("gjzxdkff".equalsIgnoreCase(lx)){
			
			en =new String[] { "xn", "xh", "xm","hth", "nj", "xymc", "zymc", "bjmc", "ffje"};
		 cn =new String[] { "ѧ��", "ѧ��", "����","��ͬ��", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶", "���Ž��" };
		}
		rForm.setColList(en);
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * ɾ����Դ�ش�����Ϣ(2011.11.8 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delSyddk(XszzZgmsxyActionForm myForm) throws Exception {

		dao = new XszzZgmsxyDAO();
		return dao.delSyddk(myForm);
	}
	
	/**
	 * ��ȡ������Դ�ش����¼
	 * 
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getOneSyddk(XszzZgmsxyActionForm myForm) {
		dao = new XszzZgmsxyDAO();
		return dao.getOneSyddk(myForm);
	}
	
	/**
	 * �޸���Դ�ش�����Ϣ(2011.11.8 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateSyddk(XszzZgmsxyActionForm myForm) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.updateSyddk(myForm);
	}
	
	/**
	 * ��ȡ��Դ�ش�����Ϣ�б�(2011.11.8 qlj)
	 * 
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getZxdkFfList(XszzZgmsxyActionForm myForm)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getZxdkFfList(myForm);
	}
	
	/**
	 * ɾ����ѧ�������Ϣ(2011.11.9 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delZxdkFf(XszzZgmsxyActionForm myForm) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.delZxdkFf(myForm);
	}
	
	/**
	 * ѧ������-ѧ�������ѯ��ͷ
	 * 
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public List<HashMap<String, String>> getXssqTit() throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getXssqTit();
	}
	/**
	 *  ѧ������-ѧ�������ѯ���(�޸�:����ҳ)���ط���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public List<String[]> getXssqRes(XszzZgmsxyActionForm model,
			QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXssqRes(model,queryModel,request);
		}
		return resList;
	}
	
	/**
	 *  ѧ������-ѧ��������˲�ѯ���(�޸�:����ҳ)���ط���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public List<String[]> getXssqshRes(XszzZgmsxyActionForm model,
			QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXssqshRes(model,queryModel,request);
		}
		return resList;
	}
	/**
	 * �����޸�ѧ������-ѧ��������˽��
	 * 
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public void modXssqxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		dao.modXssqxx(cbVal, shjg, request);
	}
	/**
	 * ɾ��ѧ������-ѧ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public void delXssqxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzZgmsxyDAO();
		dao.delXssqxx(cbVal, request);
	}
	
	/**
	 * ��ȡѧ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2012-6-6
	 */
	public HashMap<String, String> getXssqxx(String pkVal) throws Exception {
		dao = new XszzZgmsxyDAO();
		return dao.getXssqxx(pkVal);
	}

}
