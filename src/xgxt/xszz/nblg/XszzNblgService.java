package xgxt.xszz.nblg;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.base.Excel2Oracle;
import xgxt.szdw.utils.MakeQuery;
import xgxt.utils.ExcelMethods;

/**
 * Title: ѧ����������ϵͳ
 * Description: ������ѧ������Service
 * Copyright: Copyright (c) 2009
 * Company: zfsoft
 * Author: ����
 * Version: 1.0
 * Time: 2009-01-13
 */
public class XszzNblgService {

	XszzNblgDAO dao = null;// ���ݲ���DAO

	/**
	 * ��ȡѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getStu(xh);
	}
	
	/**
	 * ��ȡ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsxx(String pkVal) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getKnsxx(pkVal);
	}
	
	/**
	 * �õ�����������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getKnsSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getKnsSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * ����������������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsSqxx(KnsModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveKnsSqxx(model, request);
	}
	
	/**
	 * �õ����Ѷ�������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsSqb(KnsModel model,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getKnsSqb(model,request);
	}
	
	/**
	 * ɾ����������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delKnsxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.delKnsxx(cbVal, request);
	}
	
	/**
	 * ��������ѯ��ͷ Knstit ----��������ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKnsTit() throws Exception {
		dao = new XszzNblgDAO();
		return dao.getKnsTit();
	}
	
	/**
	 * ��������ѯ��� Knsres ---- ������
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKnsRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getKnsRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * �����޸���������˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modKnsxx(String cbVal, String shType, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.modKnsxx(cbVal, shType, shjg, request);
	}
	
	/**
	 * ���������� KnsExp ---- ����������
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getKnsExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_nblg_kns",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_nblg_kns");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ���������������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveKnsShxx(KnsModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveKnsShxx(model, request);
	}
	
	/**
	 * ��ȡ��ʱ���Ѳ�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsknbzxx(String pkVal) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getLsknbzxx(pkVal);
	}
	
	/**
	 * �õ���ʱ���Ѳ�������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getLsknbzSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getLsknbzSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * ������ʱ���Ѳ���������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsknbzSqxx(LsknbzModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveLsknbzSqxx(model, request);
	}
	
	/**
	 * �õ���ʱ���Ѳ���������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getLsknbzSqb(LsknbzModel model,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getLsknbzSqb(model,request);
	}
	
	/**
	 * ɾ����ʱ���Ѳ�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delLsknbzxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.delLsknbzxx(cbVal, request);
	}
	
	/**
	 * �����޸���ʱ���Ѳ�����˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modLsknbzxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.modLsknbzxx(cbVal, shjg, request);
	}
	
	/**
	 * ��ʱ���Ѳ�����ѯ��ͷ Lsknbztit ----��ʱ���Ѳ�����ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLsknbzTit() throws Exception {
		dao = new XszzNblgDAO();
		return dao.getLsknbzTit();
	}
	
	/**
	 * ��ʱ���Ѳ�����ѯ��� Lsknbzres ---- ��ʱ���Ѳ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsknbzRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getLsknbzRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * ��ʱ���Ѳ������� LsknbzExp ---- ��ʱ���Ѳ�������
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getLsknbzExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_nblg_lsknbz",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_nblg_lsknbz");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ������ʱ���Ѳ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveLsknbzShxx(LsknbzModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveLsknbzShxx(model, request);
	}
	
	/**
	 * ��ȡ��ѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxjxx(String pkVal) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getZxjxx(pkVal);
	}
	
	/**
	 * �õ���ѧ������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getZxjSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getZxjSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * ������ѧ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxjSqxx(ZxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveZxjSqxx(model, request);
	}
	
	/**
	 * �õ���ѧ��������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxjSqb(ZxjModel model,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getZxjSqb(model,request);
	}
	
	/**
	 * ɾ����ѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delZxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.delZxjxx(cbVal, request);
	}
	
	/**
	 * �����޸���ѧ����˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modZxjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.modZxjxx(cbVal, shjg, request);
	}
	
	/**
	 * ��ѧ���ѯ��ͷ Zxjtit ----��ѧ���ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZxjTit() throws Exception {
		dao = new XszzNblgDAO();
		return dao.getZxjTit();
	}
	
	/**
	 * ��ѧ���ѯ��� Zxjres ---- ��ѧ��
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZxjRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getZxjRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * ��ѧ�𵼳� ZxjExp ---- ��ѧ�𵼳�
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getZxjExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_nblg_zxj",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_nblg_zxj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ������ѧ�������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxjShxx(ZxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveZxjShxx(model, request);
	}
	
	/**
	 * ��ȡ������ѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjxx(String pkVal) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjzxjxx(pkVal);
	}
	
	/**
	 * �õ�������ѧ������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getGjzxjSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjzxjSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * ���������ѧ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxjSqxx(GjzxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveGjzxjSqxx(model, request);
	}
	
	/**
	 * �õ�������ѧ��������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxjSqb(GjzxjModel model,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjzxjSqb(model,request);
	}
	
	/**
	 * ɾ��������ѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjzxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
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
		dao = new XszzNblgDAO();
		dao.modGjzxjxx(cbVal, shjg, request);
	}
	
	/**
	 * ������ѧ���ѯ��ͷ Gjzxjtit ----������ѧ���ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxjTit() throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjzxjTit();
	}
	
	/**
	 * ������ѧ���ѯ��� Gjzxjres ---- ������ѧ��
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxjRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxjRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * ������ѧ�𵼳� GjzxjExp ---- ������ѧ�𵼳�
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getGjzxjExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_nblg_gjzxj",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_nblg_gjzxj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ���������ѧ�������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxjShxx(GjzxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveGjzxjShxx(model, request);
	}
	
	/**
	 * ��ȡ������־��ѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxjxx(String pkVal) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjlzjxjxx(pkVal);
	}
	
	/**
	 * �õ�������־��ѧ������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getGjlzjxjSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjlzjxjSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * ���������־��ѧ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxjSqxx(GjlzjxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveGjlzjxjSqxx(model, request);
	}
	
	/**
	 * �õ�������־��ѧ��������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxjSqb(GjlzjxjModel model,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjlzjxjSqb(model,request);
	}
	
	/**
	 * ɾ��������־��ѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjlzjxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
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
		dao = new XszzNblgDAO();
		dao.modGjlzjxjxx(cbVal, shjg, request);
	}
	
	/**
	 * ������־��ѧ���ѯ��ͷ Gjlzjxjtit ----������־��ѧ���ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjlzjxjTit() throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjlzjxjTit();
	}
	
	/**
	 * ������־��ѧ���ѯ��� Gjlzjxjres ---- ������־��ѧ��
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjlzjxjRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjlzjxjRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * ������־��ѧ�𵼳� GjlzjxjExp ---- ������־��ѧ�𵼳�
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getGjlzjxjExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_nblg_gjlzjxj",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_nblg_gjlzjxj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ���������־��ѧ�������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxjShxx(GjlzjxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveGjlzjxjShxx(model, request);
	}

	/**
	 * ��ȡ�ʺ������ѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getChzxjxx(String pkVal) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getChzxjxx(pkVal);
	}
	
	/**
	 * �õ��ʺ������ѧ������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getChzxjSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getChzxjSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * ����ʺ������ѧ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveChzxjSqxx(ChzxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveChzxjSqxx(model, request);
	}
	
	/**
	 * �õ��ʺ������ѧ��������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getChzxjSqb(ChzxjModel model,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getChzxjSqb(model,request);
	}
	
	/**
	 * ɾ���ʺ������ѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delChzxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.delChzxjxx(cbVal, request);
	}
	
	/**
	 * �����޸Ĳʺ������ѧ����˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modChzxjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.modChzxjxx(cbVal, shjg, request);
	}
	
	/**
	 * �ʺ������ѧ���ѯ��ͷ Chzxjtit ----�ʺ������ѧ���ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getChzxjTit() throws Exception {
		dao = new XszzNblgDAO();
		return dao.getChzxjTit();
	}
	
	/**
	 * �ʺ������ѧ���ѯ��� Chzxjres ---- �ʺ������ѧ��
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getChzxjRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getChzxjRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * �ʺ������ѧ�𵼳� ChzxjExp ---- �ʺ������ѧ�𵼳�
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getChzxjExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_nblg_chzxj",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_nblg_chzxj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * �ʺ������ѧ���ӡ����  printChcszxj
	 * author ������
	 *data 2010-07-15
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public void printChcszxj(QueryModel queryModel,HttpServletRequest request,
			WritableWorkbook wwb) throws IllegalArgumentException, SecurityException, 
			IllegalAccessException, InvocationTargetException, NoSuchMethodException{	
		
		dao = new XszzNblgDAO();
		String[]colList={"xn","xh","xysh","xydm","xxsh","nj","zydm","bjdm"};
		StringBuffer strBuffer=MakeQuery.makeQuery(colList,queryModel);
		
		List<Map<String,String>>list=dao.getChcszxj(strBuffer);
		
		try{
		 WritableSheet ws = wwb.getSheet(0);
		 WritableCellFormat wcfTytle = new WritableCellFormat();
		 Alignment alignMent = Alignment.CENTRE;
		 VerticalAlignment vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 
		 WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(24);
		 wcfTytle.setFont(wfTytle);
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.LEFT;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
//		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(10);
		 wcfTytle.setFont(wfTytle);
		 
		 for(int i=0; i<list.size(); i++){
			 Map<String, String> map  =  list.get(i);
			 ws.addCell(new Label(0,3+i,String.valueOf(i+1),wcfTytle));
			 ws.addCell(new Label(1,3+i,map.get("xm"),wcfTytle));
			 ws.addCell(new Label(2,3+i,map.get("xb"),wcfTytle));
			 ws.addCell(new Label(3,3+i,map.get("nl"),wcfTytle));
			 ws.addCell(new Label(4,3+i,map.get("jtzz"),wcfTytle));
			 ws.addCell(new Label(5,3+i,map.get("sqly"),wcfTytle));
			 ws.addCell(new Label(6,3+i,map.get("xymc")+map.get("nj")+map.get("zymc"),wcfTytle));
			 ws.addCell(new Label(7,3+i,"",wcfTytle));
		 }
		 ws.mergeCells(0, list.size()+3+1, 7, list.size()+3+1);
		 
		 WritableFont wfTytlexx = new WritableFont(WritableFont.ARIAL);
		 WritableCellFormat wcfTytlexx = new WritableCellFormat();
		 wfTytlexx.setBoldStyle(WritableFont.NO_BOLD);
		 wcfTytlexx.setBorder(Border.NONE, BorderLineStyle.THIN);
		 wcfTytlexx.setFont(wfTytlexx);
		 wcfTytlexx.setVerticalAlignment(vag);
		 wcfTytlexx.setAlignment(alignMent);
		 
		 ws.addCell(new Label(0,list.size()+3+1,"���Ƽ���λ��������һְҵ����ѧԺ������",wcfTytlexx));
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * ����ʺ������ѧ�������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveChzxjShxx(ChzxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveChzxjShxx(model, request);
	}
	
	/**
	 * ��ȡ�����ܻ��У��У����ѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxszxjxx(String pkVal) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getZxszxjxx(pkVal);
	}
	
	/**
	 * �õ������ܻ��У��У����ѧ������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getZxszxjSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getZxszxjSqQx(sUserType,userDep,xh,nd);
	}
	
	
	
	/**
	 * �����ܻ���ѧ���ӡ����  printCszhzxj
	 * author ������
	 *data 2010-07-15
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public void printCszhzxj(QueryModel queryModel,HttpServletRequest request,WritableWorkbook wwb) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{	
		
		dao = new XszzNblgDAO();
		String[]colList={"xn","xh","xysh","xydm","xxsh","nj","zydm","bjdm"};
		StringBuffer strBuffer=MakeQuery.makeQuery(colList,queryModel);
		List<Map<String,String>>list=dao.getCszhzxj(strBuffer);
		try{
		 WritableSheet ws = wwb.getSheet(0);
		 WritableCellFormat wcfTytle = new WritableCellFormat();
		 Alignment alignMent = Alignment.CENTRE;
		 VerticalAlignment vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 
		 WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(24);
		 wcfTytle.setFont(wfTytle);
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.LEFT;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(10);
		 wcfTytle.setFont(wfTytle);
		 
		 for(int i=0; i<list.size(); i++){
			 Map<String, String> map  =  list.get(i);
			 ws.addCell(new Label(0,3+i,String.valueOf(i+1),wcfTytle));
			 ws.addCell(new Label(1,3+i,map.get("xm"),wcfTytle));
			 ws.addCell(new Label(2,3+i,map.get("xb"),wcfTytle));
			 ws.addCell(new Label(3,3+i,map.get("nl"),wcfTytle));
			 ws.addCell(new Label(4,3+i,map.get("jtzz"),wcfTytle));
			 ws.addCell(new Label(5,3+i,map.get("xymc")+map.get("nj")+map.get("zymc"),wcfTytle));
			 ws.addCell(new Label(6,3+i,map.get("sqly"),wcfTytle));
			 ws.addCell(new Label(7,3+i,map.get("zxjje"),wcfTytle));
			 ws.addCell(new Label(8,3+i,map.get("sfzh"),wcfTytle));
		 }
		
		 WritableFont wfTytlexx = new WritableFont(WritableFont.ARIAL);
		 WritableCellFormat wcfTytlexx = new WritableCellFormat();
		 wfTytlexx.setBoldStyle(WritableFont.NO_BOLD);
		 wcfTytlexx.setBorder(Border.NONE, BorderLineStyle.THIN);
		 wcfTytlexx.setFont(wfTytlexx);
		 wcfTytlexx.setVerticalAlignment(vag);
		 wcfTytlexx.setAlignment(alignMent);
		 
		 ws.mergeCells(0, list.size()+3+1, 8, list.size()+3+1);
		 ws.addCell(new Label(0,list.size()+3+1,"�������λ��������һְҵ����ѧԺ����",wcfTytlexx));
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * ��������ܻ��У��У����ѧ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxszxjSqxx(ZxszxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveZxszxjSqxx(model, request);
	}
	
	/**
	 * �õ������ܻ��У��У����ѧ��������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZxszxjSqb(ZxszxjModel model,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getZxszxjSqb(model,request);
	}
	
	/**
	 * ɾ�������ܻ��У��У����ѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delZxszxjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.delZxszxjxx(cbVal, request);
	}
	
	/**
	 * �����޸Ĵ����ܻ��У��У����ѧ����˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modZxszxjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.modZxszxjxx(cbVal, shjg, request);
	}
	
	/**
	 * �����ܻ��У��У����ѧ���ѯ��ͷ Zxszxjtit ----�����ܻ��У��У����ѧ���ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZxszxjTit() throws Exception {
		dao = new XszzNblgDAO();
		return dao.getZxszxjTit();
	}
	
	/**
	 * �����ܻ��У��У����ѧ���ѯ��� Zxszxjres ---- �����ܻ��У��У����ѧ��
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZxszxjRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getZxszxjRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * �����ܻ��У��У����ѧ�𵼳� ZxszxjExp ---- �����ܻ��У��У����ѧ�𵼳�
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getZxszxjExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_nblg_zxszxj",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_nblg_zxszxj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ��������ܻ��У��У����ѧ�������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxszxjShxx(ZxszxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveZxszxjShxx(model, request);
	}
	
	/**
	 * ��ȡ������ѧ�������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkxx(String pkVal) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjzxdkxx(pkVal);
	}
	
	/**
	 * �õ�������ѧ���������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getGjzxdkSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjzxdkSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * ���������ѧ�����������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkSqxx(GjzxdkModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveGjzxdkSqxx(model, request);
	}
	
	/**
	 * �õ�������ѧ�����������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjzxdkSqb(GjzxdkModel model,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjzxdkSqb(model,request);
	}
	
	/**
	 * ɾ��������ѧ�������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjzxdkxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.delGjzxdkxx(cbVal, request);
	}
	
	/**
	 * �����޸Ĺ�����ѧ�������˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjzxdkxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		dao.modGjzxdkxx(cbVal, shjg, request);
	}
	
	/**
	 * ������ѧ������ѯ��ͷ Gjzxdktit ----������ѧ������ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjzxdkTit() throws Exception {
		dao = new XszzNblgDAO();
		return dao.getGjzxdkTit();
	}
	
	/**
	 * ������ѧ������ѯ��� Gjzxdkres ---- ������ѧ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjzxdkRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzNblgDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjzxdkRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * ������ѧ����𵼳� GjzxdkExp ---- ������ѧ����𵼳�
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getGjzxdkExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_nblg_gjzxdk",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_nblg_gjzxdk");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ���������ѧ����������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjzxdkShxx(GjzxdkModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzNblgDAO();
		return dao.saveGjzxdkShxx(model, request);
	}
}
