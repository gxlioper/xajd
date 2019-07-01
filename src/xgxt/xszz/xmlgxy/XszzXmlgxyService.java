package xgxt.xszz.xmlgxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.utils.ExcelMethods;

/**
 * Title: ѧ����������ϵͳ
 * Description: ������ѧԺѧ������Service
 * Copyright: Copyright (c) 2009
 * Company: zfsoft
 * Author: ����
 * Version: 1.0
 * Time: 2009-12-10
 */
public class XszzXmlgxyService {

	XszzXmlgxyDAO dao = null;// ���ݲ���DAO

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
	 * �õ�����Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getSqQx(String zzType, String sUserType, String xh) throws Exception {
		dao = new XszzXmlgxyDAO();
		return dao.getSqQx(zzType, sUserType, xh);
	}
	
	/**
	 * ��ȡѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzXmlgxyDAO();
		return dao.getStu(xh);
	}

	/**
	 * ��ȡ������־��ѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjlzjxjxx(String pkVal) throws Exception {
		dao = new XszzXmlgxyDAO();
		return dao.getGjlzjxjxx(pkVal);
	}
	
	/**
	 * ���������־��ѧ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjlzjxjSqxx(GjlzjxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzXmlgxyDAO();
		return dao.saveGjlzjxjSqxx(model, request);
	}
	
	/**
	 * ɾ��������־��ѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjlzjxjxx(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzXmlgxyDAO();
		dao.delGjlzjxjxx(cbVal, request);
	}
	
	/**
	 * �����޸Ĺ�����־��ѧ����˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjlzjxjxx(String[] cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzXmlgxyDAO();
		dao.modGjlzjxjxx(cbVal, shjg, request);
	}
	
	/**
	 * ������־��ѧ���ѯ��ͷ Gjlzjxjtit ---- ������־��ѧ���ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjlzjxjTit() throws Exception {
		String[] enList = new String[] { "disabled", "bgcolor", "pk", "xn",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"xysh", "xxsh" };
		String[] cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��",
				"ѧ��", "����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ʱ��",
				Base.YXPZXY_KEY+"���", "ѧУ���" };
		return makeTitList(enList, cnList);
	}

	/**
	 * ������־��ѧ���ѯ��� Gjlzjxjres ---- ������־��ѧ��
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjlzjxjRes(QueryModel queryModel,
			HttpServletRequest request, XszzXmlgxyActionForm actionForm) throws Exception {
		dao = new XszzXmlgxyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjlzjxjRes(queryModel, request, actionForm);
		}
		return resList;
	}
	
	/**
	 * ������־��ѧ����Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjlzjxjResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzXmlgxyDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getGjlzjxjResNum(queryModel, request);
		}
		return sT;
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
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		dao = new XszzXmlgxyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_xmlg_gjlzjxj", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_xmlg_gjlzjxj");
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
		dao = new XszzXmlgxyDAO();
		return dao.saveGjlzjxjShxx(model, request);
	}
	
	/**
	 * ������־��ѧ��ʾ����
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void gjlzjxjgsmd(WritableWorkbook wwb, String xn) throws Exception {
		dao = new XszzXmlgxyDAO();
		List<HashMap<String, String>> yxList = dao.getGjlzjxjGsmdXyList(xn);
		List<HashMap<String, String>> xsList = dao.getGjlzjxjGsmdXsList(xn);
		String title = xn + "ѧ�������־��ѧ��ѧ����ʾ����";
		WritableSheet ws = wwb.createSheet(title, 0);
		WritableCellFormat wcf1 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf1 = ExcelMethods.getWcf(WritableFont.ARIAL, 12, false,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.NONE);
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 7, 1);
		ex.printToOneCell_multy(ws, title, 0, 0, 10, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		int m = 0;
		// ���ձ����ʽ���Excel
		if (yxList.size() > 0) {
			for (int i = 0; i < yxList.size(); i++) {
				String xymc = Base.isNull(yxList.get(i).get("xymc")) ? ""
						: yxList.get(i).get("xymc");
				String cout = yxList.get(i).get("cout");
				ws.mergeCells(0, 2 + i + m, 7, 2 + i + m);
				ws.addCell(new Label(0, 2 + i + m, xymc + cout + "��", wcf1));
				int[] p = new int[] { 0, 3, 6 };
				int[] q = new int[] { 1, 4, 7 };
				int n = 0;
				int num = 0;
				for (int j = 0; j < xsList.size(); j++) {
					if (n > 2) {
						n = 0;
					}
					String xymcV = xsList.get(j).get("xymc");
					xymcV = Base.isNull(xymcV) ? " " : xymcV;
					if (xymcV.equalsIgnoreCase(xymc)) {
						ws.addCell(new Label(p[n], 3 + i + m, xsList.get(j)
								.get("bjmc")));
						ws.addCell(new Label(q[n], 3 + i + m, xsList.get(j)
								.get("xm")));
						n++;
						num++;
						if (Integer.parseInt(cout) < 2
								|| num == Integer.parseInt(cout) || n > 2) {
							m++;
						}
					} else {
						n = 0;
					}
				}
				m++;
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//������ͻ���
	}
	
	/**
	 * ��ȡ���ҽ�ѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getGjjxjxx(String pkVal) throws Exception {
		dao = new XszzXmlgxyDAO();
		return dao.getGjjxjxx(pkVal);
	}
	
	/**
	 * ������ҽ�ѧ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjjxjSqxx(GjjxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzXmlgxyDAO();
		return dao.saveGjjxjSqxx(model, request);
	}
	
	/**
	 * ɾ�����ҽ�ѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delGjjxjxx(String[] cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzXmlgxyDAO();
		dao.delGjjxjxx(cbVal, request);
	}
	
	/**
	 * �����޸Ĺ��ҽ�ѧ����˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modGjjxjxx(String[] cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzXmlgxyDAO();
		dao.modGjjxjxx(cbVal, shjg, request);
	}
	
	/**
	 * ���ҽ�ѧ���ѯ��ͷ Gjjxjtit ---- ���ҽ�ѧ���ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getGjjxjTit() throws Exception {
		String[] enList = new String[] { "disabled", "bgcolor", "pk", "xn",
				"xh", "xm", "xb", "sfzh", "xymc", "zymc", "bjmc", "sqsj",
				"xysh", "xxsh" };
		String[] cnList = new String[] { "disabled", "bgcolor", "����", "ѧ��",
				"ѧ��", "����", "�Ա�", "���֤��", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����ʱ��",
				Base.YXPZXY_KEY+"���", "ѧУ���" };
		return makeTitList(enList, cnList);
	}

	/**
	 * ���ҽ�ѧ���ѯ��� Gjjxjres ---- ���ҽ�ѧ��
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGjjxjRes(QueryModel queryModel,
			HttpServletRequest request, XszzXmlgxyActionForm actionForm)
			throws Exception {
		dao = new XszzXmlgxyDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getGjjxjRes(queryModel, request, actionForm);
		}
		return resList;
	}
	
	/**
	 * ���ҽ�ѧ����Ϣ��ѯ�����
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public String getGjjxjResNum(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzXmlgxyDAO();
		String sT = "0";
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			sT = dao.getGjjxjResNum(queryModel, request);
		}
		return sT;
	}
	
	/**
	 * ���ҽ�ѧ�𵼳� GjjxjExp ---- ���ҽ�ѧ�𵼳�
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getGjjxjExp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		dao = new XszzXmlgxyDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_xmlg_gjjxj", request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_xmlg_gjjxj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ������ҽ�ѧ�������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGjjxjShxx(GjjxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzXmlgxyDAO();
		return dao.saveGjjxjShxx(model, request);
	}
	
}
