package xsgzgl.pjpy.general.tjcx.zcbjmd;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmDao;

import jxl.Workbook;
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
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.tjcx.TjcxZcbjmdInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_ͳ�Ʒ���_�۲�༶����_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class TjcxZcbjmdService extends CommService implements
		TjcxZcbjmdInterface {

	TjcxZcbjmdDAO dao = new TjcxZcbjmdDAO();

	/**
	 * �����۲�༶����HTMl���еȼ����ԡ�
	 * 
	 * @author ΰ�����
	 * @throws IOException
	 */
	public void createZcbjmdDjksHTML(PjpyGeneralForm myForm,
			TjcxZcbjmdModel model, User user, HttpServletResponse response)
			throws IOException {

		response.setContentType("text/html;charset=gbk");

		// ================ǰ̨�����ǿ��жϣ����迼�ǲ���null�����=========================
		SearchModel searchModel = myForm.getSearchModel();
		// ѧ��
		String xn = searchModel.getSearch_tj_xn()[0];
		// ѧ��
		String xq = searchModel.getSearch_tj_xq()[0];
		// �༶����
		String bjdm = searchModel.getSearch_tj_bjNew()[0];

		ZcxmDao zcxmDao = new ZcxmDao();
		//ǰ�����۲���Ŀ
		List<HashMap<String,String>> zcxmList = zcxmDao.getFirstAndSecondZcxm();
		
		HashMap<String, Object> map = getZcbjmdInfoNew(xn,xq, bjdm,zcxmList);

		StringBuilder html = new StringBuilder();
		html.append("<table class=\"dateline\" width=\"100%\">");

		html.append("<thead>");
		// =================��һ��===================
		html.append("<tr>");
		html.append("<td width=\"60%\" colspan=\"2\">");
		html.append("�༶��");
		html.append(map.get("bjmc"));
		html.append("</td>");
		html.append("<td>");
		html.append("�༶������");
		html.append(map.get("bjrs"));
		html.append("��");
		html.append("</td>");
		html.append("</tr>");

		// =================�ڶ���===================
		html.append("<tr>");
		html.append("<td width=\"30%\">");
		html.append("һ�Ƚ�ѧ��");
		html.append(map.get("ydjxjrs"));
		html.append("��");
		html.append("</td>");
		html.append("<td width=\"30%\">");
		html.append("���Ƚ�ѧ��");
		html.append(map.get("edjxjrs"));
		html.append("��");
		html.append("</td>");
		html.append("<td>");
		html.append("���Ƚ�ѧ��");
		html.append(map.get("sdjxjrs"));
		html.append("��");
		html.append("</td>");
		html.append("</tr>");

		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<td colspan=\"3\">");
		// ==========ѧ���б� begin==============
		html
				.append("<div style=\"width:750px;height:400px;overflow-x:auto;overflow-y:auto;\">");
		html.append("<table class=\"dateline\" width=\"100%\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<td>ѧ��</td>");
		html.append("<td>����</td>");
		for (HashMap<String, String> hashMap : zcxmList) {
			html.append("<td>"+hashMap.get("xmmc")+"</td>");
			html.append("<td>����</td>");
		}
//		html.append("<td>��������</td>");
//		html.append("<td>��������</td>");
//		html.append("<td>��������</td>");
//		html.append("<td>��������</td>");
//		html.append("<td>��������</td>");
//		html.append("<td>��������</td>");
//		html.append("<td>��������</td>");
//		html.append("<td>�ܷ�</td>");
//		html.append("<td>�ۺ�����</td>");
		html.append("<td>��ѧ�𼰵��</td>");
		html.append("<td>����ѧ�����Ÿ�</td>");
		html.append("<td>��У�⽱ѧ�����</td>");
		html.append("<td>ѧϰ�ɼ�����������</td>");
		html.append("<td>Ӣ��������</td>");
		html.append("<td>������������</td>");
		html.append("<td>������Ƿѧ��</td>");
		html.append("<td>����У��ͨ��</td>");
		html.append("<td>�������</td>");
		html.append("<td>���ν���</td>");
		html.append("</tr>");
		html.append("</thead>");

		// �۲���Ϣ�б�
		List<HashMap<String, String>> zcxxList = (List<HashMap<String, String>>) map
				.get("zcxxList");
		html.append("<tbody>");

		if (zcxxList != null && zcxxList.size() > 0) {
			for (int i = 0; i < zcxxList.size(); i++) {
				HashMap<String, String> zcxx = zcxxList.get(i);
				html.append("<tr>");
				html.append("<td>" + zcxx.get("xh") + "</td>");
				html.append("<td>" + zcxx.get("xm") + "</td>");
				for(int j=0;j<zcxmList.size();j++){
					html.append("<td>" + zcxx.get("fs"+j) + "</td>");
					html.append("<td>" + zcxx.get("pm"+j) + "</td>");
				}
//				html.append("<td>" + zcxx.get("dyf") + "</td>");
//				html.append("<td>" + zcxx.get("dypm") + "</td>");
//				html.append("<td>" + zcxx.get("zyf") + "</td>");
//				html.append("<td>" + zcxx.get("zypm") + "</td>");
//				html.append("<td>" + zcxx.get("tyf") + "</td>");
//				html.append("<td>" + zcxx.get("nlf") + "</td>");
//				html.append("<td>" + zcxx.get("nlpm") + "</td>");
//				html.append("<td>" + zcxx.get("zcf") + "</td>");
//				html.append("<td>" + zcxx.get("zcpm") + "</td>");
				html.append("<td>" + zcxx.get("jxjmc") + "</td>");
				html.append("<td>" + zcxx.get("rychmc") + "</td>");
				html.append("<td></td>");
				html.append("<td>" + zcxx.get("bjgms") + "</td>");
				html.append("<td>" + zcxx.get("yydj") + "</td>");
				html.append("<td>" + zcxx.get("jsjdj") + "</td>");
				html.append("<td></td>");
				html.append("<td>" + zcxx.get("xjtb") + "</td>");
				html.append("<td>" + zcxx.get("wjcf") + "</td>");
				html.append("<td>" + zcxx.get("kkjs") + "</td>");
				html.append("</tr>");
			}
		}

		html.append("</tbody>");

		html.append("</table>");
		html.append("</div>");
		// ==========ѧ���б� end==============
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");

		html.append("</table>");
		response.getWriter().print(html.toString());
	}

	/**
	 * �����۲�༶����HTMl���޵ȼ����ԡ�
	 * 
	 * @author ΰ�����
	 * @throws IOException
	 */
	public void createZcbjmdNoDjksHTML(PjpyGeneralForm myForm,
			TjcxZcbjmdModel model, User user, HttpServletResponse response)
			throws IOException {

		response.setContentType("text/html;charset=gbk");

		// ================ǰ̨�����ǿ��жϣ����迼�ǲ���null�����=========================
		SearchModel searchModel = myForm.getSearchModel();
		// ѧ��
		String xn = searchModel.getSearch_tj_xn()[0];
		// �༶����
		String bjdm = searchModel.getSearch_tj_bj()[0];

		HashMap<String, Object> map = getZcbjmdInfo(xn, bjdm);

		StringBuilder html = new StringBuilder();
		html.append("<table class=\"dateline\" width=\"100%\">");

		html.append("<thead>");
		// =================��һ��===================
		html.append("<tr>");
		html.append("<td width=\"60%\" colspan=\"2\">");
		html.append("�༶��");
		html.append(map.get("bjmc"));
		html.append("</td>");
		html.append("<td>");
		html.append("�༶������");
		html.append(map.get("bjrs"));
		html.append("��");
		html.append("</td>");
		html.append("</tr>");

		// =================�ڶ���===================
		html.append("<tr>");
		html.append("<td width=\"30%\">");
		html.append("һ�Ƚ�ѧ��");
		html.append(map.get("ydjxjrs"));
		html.append("��");
		html.append("</td>");
		html.append("<td width=\"30%\">");
		html.append("���Ƚ�ѧ��");
		html.append(map.get("edjxjrs"));
		html.append("��");
		html.append("</td>");
		html.append("<td>");
		html.append("���Ƚ�ѧ��");
		html.append(map.get("sdjxjrs"));
		html.append("��");
		html.append("</td>");
		html.append("</tr>");

		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<td colspan=\"3\">");
		// ==========ѧ���б� begin==============
		html
				.append("<div style=\"width:750px;height:400px;overflow-x:auto;overflow-y:auto;\">");
		html.append("<table class=\"dateline\" width=\"100%\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<td>ѧ��</td>");
		html.append("<td>����</td>");
		html.append("<td>��������</td>");
		html.append("<td>��������</td>");
		html.append("<td>��������</td>");
		html.append("<td>��������</td>");
		html.append("<td>��������</td>");
		html.append("<td>��������</td>");
		html.append("<td>��������</td>");
		html.append("<td>�ܷ�</td>");
		html.append("<td>�ۺ�����</td>");
		html.append("<td>��ѧ�𼰵��</td>");
		html.append("<td>����ѧ�����Ÿ�</td>");
		html.append("<td>��У�⽱ѧ�����</td>");
		html.append("<td>ѧϰ�ɼ�����������</td>");
		// html.append("<td>Ӣ��������</td>");
		// html.append("<td>������������</td>");
		html.append("<td>������Ƿѧ��</td>");
		html.append("<td>����У��ͨ��</td>");
		html.append("<td>�������</td>");
		html.append("<td>���ν���</td>");
		html.append("</tr>");
		html.append("</thead>");

		// �۲���Ϣ�б�
		List<HashMap<String, String>> zcxxList = (List<HashMap<String, String>>) map
				.get("zcxxList");
		html.append("<tbody>");

		if (zcxxList != null && zcxxList.size() > 0) {
			for (int i = 0; i < zcxxList.size(); i++) {
				HashMap<String, String> zcxx = zcxxList.get(i);
				html.append("<tr>");
				html.append("<td>" + zcxx.get("xh") + "</td>");
				html.append("<td>" + zcxx.get("xm") + "</td>");
				html.append("<td>" + zcxx.get("dyf") + "</td>");
				html.append("<td>" + zcxx.get("dypm") + "</td>");
				html.append("<td>" + zcxx.get("zyf") + "</td>");
				html.append("<td>" + zcxx.get("zypm") + "</td>");
				html.append("<td>" + zcxx.get("tyf") + "</td>");
				html.append("<td>" + zcxx.get("nlf") + "</td>");
				html.append("<td>" + zcxx.get("nlpm") + "</td>");
				html.append("<td>" + zcxx.get("zcf") + "</td>");
				html.append("<td>" + zcxx.get("zcpm") + "</td>");
				html.append("<td>" + zcxx.get("jxjmc") + "</td>");
				html.append("<td>" + zcxx.get("rychmc") + "</td>");
				html.append("<td></td>");
				html.append("<td>" + zcxx.get("bjgms") + "</td>");
				// html.append("<td>" + zcxx.get("yydj") + "</td>");
				// html.append("<td>" + zcxx.get("jsjdj") + "</td>");
				html.append("<td></td>");
				html.append("<td>" + zcxx.get("xjtb") + "</td>");
				html.append("<td>" + zcxx.get("wjcf") + "</td>");
				html.append("<td>" + zcxx.get("kkjs") + "</td>");
				html.append("</tr>");
			}
		}

		html.append("</tbody>");

		html.append("</table>");
		html.append("</div>");
		// ==========ѧ���б� end==============
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");

		html.append("</table>");
		response.getWriter().print(html.toString());
	}

	/**
	 * �����۲�༶����
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void expZcbjmd(TjcxZcbjmdModel model, OutputStream os)
			throws Exception {

		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		// �༶
		String bjdm = model.getBjdm();
		// ����
		String lx = model.getLx();

		// �жϱ����Ƿ���ҪӢ�������ɼ�
		int num = ("djks".equalsIgnoreCase(lx)) ? 2 : 0;

		ZcxmDao zcxmDao = new ZcxmDao();
		//ǰ�����۲���Ŀ
		List<HashMap<String,String>> zcxmList = zcxmDao.getFirstAndSecondZcxm();
		
		HashMap<String, Object> map = getZcbjmdInfoNew(xn,xq, bjdm,zcxmList);
		

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("�ۺ����ʲ�����", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		WritableCellFormat wcf3 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.ALL);

		// ������
		ExcelMB ex = new ExcelMB();

		// ���ñ���
		StringBuffer title = new StringBuffer();
		title.append(xn);
		title.append("ѧ��");
		// title.append(xqmc);
		// title.append("ѧ��");
		// title.append(xymc);
		title.append("�ۺ����ʲ�����");

		ws.mergeCells(0, 0, 18 + num, 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 12, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 650,
				Border.NONE);

		// ����ͷ
		// --------------��һ��----------------
		ws.addCell(new Label(0, 2, "�༶��" + map.get("bjmc"), wcf3));
		ws.mergeCells(0, 2, 10, 2);
		ws.addCell(new Label(11, 2, "�༶������" + map.get("bjrs") + "��", wcf3));
		ws.mergeCells(11, 2, 18 + num, 2);
		// --------------�ڶ���----------------
		ws.addCell(new Label(0, 3, "һ�Ƚ�ѧ��" + map.get("ydjxjrs") + "��", wcf3));
		ws.mergeCells(0, 3, 5, 3);
		ws.addCell(new Label(6, 3, "���Ƚ�ѧ��" + map.get("edjxjrs") + "��", wcf3));
		ws.mergeCells(6, 3, 10, 3);
		ws.addCell(new Label(11, 3, "���Ƚ�ѧ��" + map.get("sdjxjrs") + "��", wcf3));
		ws.mergeCells(11, 3, 18 + num, 3);
		// --------------������----------------
		ws.addCell(new Label(11, 4, "������ǩ����", wcf3));
		ws.mergeCells(11, 4, 18 + num, 4);
		// --------------������----------------
		ws.addCell(new Label(0, 5, "ѧ��", wcf2));
		ws.addCell(new Label(1, 5, "����", wcf2));
//		ws.addCell(new Label(2, 5, "��������", wcf2));
//		ws.addCell(new Label(3, 5, "��������", wcf2));
//		ws.addCell(new Label(4, 5, "��������", wcf2));
//		ws.addCell(new Label(5, 5, "��������", wcf2));
//		ws.addCell(new Label(6, 5, "��������", wcf2));
//		ws.addCell(new Label(7, 5, "��������", wcf2));
//		ws.addCell(new Label(8, 5, "��������", wcf2));
//		ws.addCell(new Label(9, 5, "�ܷ�", wcf2));
//		ws.addCell(new Label(10, 5, "�ۺ�����", wcf2));
		int tmp=0;
		for (int i=0;i<zcxmList.size();i++) {
			HashMap<String, String> hashMap=zcxmList.get(i);
			ws.addCell(new Label(2*i+2, 5, hashMap.get("xmmc"), wcf2));
			ws.addCell(new Label(2*i+3, 5, "����", wcf2));
			tmp=2*i+3;
		}
//		ws.addCell(new Label(11, 5, "��ѧ�𼰵��(��Ϊ����ԭ������ѡ����ע��)", wcf2));
//		ws.addCell(new Label(10, 5, "�ۺ�����", wcf2));
		ws.addCell(new Label(tmp+1, 5, "��ѧ�𼰵��(��Ϊ����ԭ������ѡ����ע��)", wcf2));
		ws.addCell(new Label(tmp+2, 5, "����ѧ�����Ÿ�", wcf2));
		ws.addCell(new Label(tmp+3, 5, "��У�⽱ѧ�������������ѧ�ڻ���ҽ�ѧ�������", wcf2));
		ws.addCell(new Label(tmp+4, 5, "ѧϰ�ɼ�����������", wcf2));
		if ("djks".equalsIgnoreCase(lx)) {
			ws.addCell(new Label(tmp+5, 5, "Ӣ��������", wcf2));
			ws.addCell(new Label(tmp+6, 5, "������������", wcf2));
		}
		ws.addCell(new Label(tmp+5 + num, 5, "������Ƿѧ��(�Ѵ�����ע��)", wcf2));
		ws.addCell(new Label(tmp+6 + num, 5, "����У��ͨ��", wcf2));
		ws.addCell(new Label(tmp+7 + num, 5, "�������(������У�쿴����ע��)", wcf2));
		ws.addCell(new Label(tmp+8 + num, 5, "���ν���", wcf2));

		// �۲���Ϣ�б�
		List<HashMap<String, String>> zcxxList = (List<HashMap<String, String>>) map
				.get("zcxxList");

		if (zcxxList != null && zcxxList.size() > 0) {
			for (int i = 0; i < zcxxList.size(); i++) {
				HashMap<String, String> zcxx = zcxxList.get(i);
				// �۲���Ϣ
				ws.addCell(new Label(0, 6 + i, zcxx.get("xh"), wcf2));
				ws.addCell(new Label(1, 6 + i, zcxx.get("xm"), wcf2));
				for(int j=0;j<zcxmList.size();j++){
					ws.addCell(new Label(2*j+2, 6 + i, zcxx.get("fs"+j), wcf2));
					ws.addCell(new Label(2*j+3, 6 + i, zcxx.get("pm"+j), wcf2));
				}
//				ws.addCell(new Label(2, 6 + i, zcxx.get("dyf"), wcf2));
//				ws.addCell(new Label(3, 6 + i, zcxx.get("dypm"), wcf2));
//				ws.addCell(new Label(4, 6 + i, zcxx.get("zyf"), wcf2));
//				ws.addCell(new Label(5, 6 + i, zcxx.get("zypm"), wcf2));
//				ws.addCell(new Label(6, 6 + i, zcxx.get("tyf"), wcf2));
//				ws.addCell(new Label(7, 6 + i, zcxx.get("nlf"), wcf2));
//				ws.addCell(new Label(8, 6 + i, zcxx.get("nlpm"), wcf2));
//				ws.addCell(new Label(9, 6 + i, zcxx.get("zcf"), wcf2));
//				ws.addCell(new Label(10, 6 + i, zcxx.get("zcpm"), wcf2));
				// ������Ϣ
				ws.addCell(new Label(tmp+1, 6 + i, zcxx.get("jxjmc"), wcf2));
				ws.addCell(new Label(tmp+2, 6 + i, zcxx.get("rychmc"), wcf2));
				ws.addCell(new Label(tmp+3, 6 + i, "", wcf2));
				// �ɼ���Ϣ
				ws.addCell(new Label(tmp+4, 6 + i, zcxx.get("bjgs"), wcf2));
				if ("djks".equalsIgnoreCase(lx)) {
					ws.addCell(new Label(tmp+5, 6 + i, zcxx.get("yydj"), wcf2));
					ws.addCell(new Label(tmp+6, 6 + i, zcxx.get("jsjdj"), wcf2));
				}
				ws.addCell(new Label(tmp+5 + num, 6 + i, "", wcf2));
				// Υ����Ϣ
				ws.addCell(new Label(tmp+6 + num, 6 + i, zcxx.get("xjtb"), wcf2));
				ws.addCell(new Label(tmp+7 + num, 6 + i, zcxx.get("wjcf"), wcf2));
				ws.addCell(new Label(tmp+8 + num, 6 + i, zcxx.get("kkjs"), wcf2));
			}
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ����۲�������Ϣ����������
	 * 
	 * @author 
	 */
	private HashMap<String, Object> getZcbjmdInfoNew(String xn,String xq, String bjdm,List<HashMap<String, String>> zcxmList) {

		HashMap<String, Object> map = new HashMap<String, Object>();

		
		// �۲���Ϣ
		List<HashMap<String, String>> zcxxList = dao.getZcxxListNew(xn,xq, bjdm,zcxmList);

		// ѧԺ����
		String xymc = "";
		// �༶����
		String bjmc = "";
		// �༶����
		String bjrs = "0";

		map.put("xn", xn);
		map.put("xq", xq);
		map.put("zcxxList", zcxxList);

		if (zcxxList != null && zcxxList.size() > 0) {
			xymc = zcxxList.get(0).get("xymc");
			bjmc = zcxxList.get(0).get("bjmc");
			bjrs = String.valueOf(zcxxList.size());

//			// ����������Ϣ
//			setPjxx(map);
//			// ����������Ϣ
			setPjxxNew(map);
			setQtxxNew(map);
		}

		map.put("bjmc", bjmc);
		map.put("bjrs", bjrs);

		return map;
	}

	/**
	 * ����۲�༶������Ϣ
	 * 
	 * @author luojw
	 */
	private HashMap<String, Object> getZcbjmdInfo(String xn, String bjdm) {

		HashMap<String, Object> map = new HashMap<String, Object>();

		// �۲���Ϣ
		List<HashMap<String, String>> zcxxList = dao.getZcxxList(xn, bjdm);

		// ѧԺ����
		String xymc = "";
		// �༶����
		String bjmc = "";
		// �༶����
		String bjrs = "0";

		map.put("xn", xn);
		map.put("zcxxList", zcxxList);

		if (zcxxList != null && zcxxList.size() > 0) {
			xymc = zcxxList.get(0).get("xymc");
			bjmc = zcxxList.get(0).get("bjmc");
			bjrs = String.valueOf(zcxxList.size());

			// ����������Ϣ
			setPjxx(map);
			// ����������Ϣ
			setQtxx(map);
		}

		map.put("bjmc", bjmc);
		map.put("bjrs", bjrs);

		return map;
	}
	
	/**
	 * ����������Ϣ(������)
	 * 
	 * @author cmj
	 */
	@SuppressWarnings("unchecked")
	private void setPjxxNew(HashMap<String, Object> map) {

		// ѧ��
		String xn = (String) map.get("xn");
		String xq = (String) map.get("xq");
		// �۲���Ϣ�б�
		List<HashMap<String, String>> zcxxList = (List<HashMap<String, String>>) map
				.get("zcxxList");

		// ������Ϣ�б�
		List<HashMap<String, String>> pjxxList = dao.getPjxxListNew(zcxxList, xn,xq);
		// ���ֵ
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// һ�Ƚ�ѧ������
		int ydjxjrs = 0;
		// ���Ƚ�ѧ������
		int edjxjrs = 0;
		// ���Ƚ�ѧ������
		int sdjxjrs = 0;

		if (zcxxList != null && zcxxList.size() > 0) {
			for (int i = 0; i < zcxxList.size(); i++) {

				HashMap<String, String> zcxx = zcxxList.get(i);

				// ��ѧ������
				String jxjmc = "";
				// �����ƺ�����
				String rychmc = "";

				if (pjxxList != null && pjxxList.size() > 0) {
					for (int j = 0; j < pjxxList.size(); j++) {
						if (zcxx.get("xh").equalsIgnoreCase(
								pjxxList.get(j).get("xh"))) {
							String xmmc = pjxxList.get(j).get("xmmc");
							String xmlx = pjxxList.get(j).get("xmlx");

							if ("01".equalsIgnoreCase(xmlx)) {// ��ѧ��
								if ("һ�Ƚ�ѧ��".equalsIgnoreCase(xmmc)) {
									ydjxjrs++;
								} else if ("���Ƚ�ѧ��".equalsIgnoreCase(xmmc)) {
									edjxjrs++;
								} else if ("���Ƚ�ѧ��".equalsIgnoreCase(xmmc)) {
									sdjxjrs++;
								}
								jxjmc = Base.isNull(jxjmc) ? xmmc : (jxjmc
										+ "��" + xmmc);
							} else if ("02".equalsIgnoreCase(xmlx)) {// �����ƺ�
								rychmc = Base.isNull(rychmc) ? xmmc : (rychmc
										+ "��" + xmmc);
							}
						}
					}
				}

				zcxx.put("jxjmc", jxjmc);
				zcxx.put("rychmc", rychmc);

				list.add(zcxx);
			}
		}

		map.put("ydjxjrs", ydjxjrs);
		map.put("edjxjrs", edjxjrs);
		map.put("sdjxjrs", sdjxjrs);
		map.put("zcxxList", list);
	}

	/**
	 * ����������Ϣ
	 * 
	 * @author luojw
	 */
	@SuppressWarnings("unchecked")
	private void setPjxx(HashMap<String, Object> map) {

		// ѧ��
		String xn = (String) map.get("xn");
		// �۲���Ϣ�б�
		List<HashMap<String, String>> zcxxList = (List<HashMap<String, String>>) map
				.get("zcxxList");

		// ������Ϣ�б�
		List<HashMap<String, String>> pjxxList = dao.getPjxxList(zcxxList, xn);
		// ���ֵ
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// һ�Ƚ�ѧ������
		int ydjxjrs = 0;
		// ���Ƚ�ѧ������
		int edjxjrs = 0;
		// ���Ƚ�ѧ������
		int sdjxjrs = 0;

		if (zcxxList != null && zcxxList.size() > 0) {
			for (int i = 0; i < zcxxList.size(); i++) {

				HashMap<String, String> zcxx = zcxxList.get(i);

				// ��ѧ������
				String jxjmc = "";
				// �����ƺ�����
				String rychmc = "";

				if (pjxxList != null && pjxxList.size() > 0) {
					for (int j = 0; j < pjxxList.size(); j++) {
						if (zcxx.get("xh").equalsIgnoreCase(
								pjxxList.get(j).get("xh"))) {
							String xmmc = pjxxList.get(j).get("xmmc");
							String xmlx = pjxxList.get(j).get("xmlx");

							if ("01".equalsIgnoreCase(xmlx)) {// ��ѧ��
								if ("һ�Ƚ�ѧ��".equalsIgnoreCase(xmmc)) {
									ydjxjrs++;
								} else if ("���Ƚ�ѧ��".equalsIgnoreCase(xmmc)) {
									edjxjrs++;
								} else if ("���Ƚ�ѧ��".equalsIgnoreCase(xmmc)) {
									sdjxjrs++;
								}
								jxjmc = Base.isNull(jxjmc) ? xmmc : (jxjmc
										+ "��" + xmmc);
							} else if ("02".equalsIgnoreCase(xmlx)) {// �����ƺ�
								rychmc = Base.isNull(rychmc) ? xmmc : (rychmc
										+ "��" + xmmc);
							}
						}
					}
				}

				zcxx.put("jxjmc", jxjmc);
				zcxx.put("rychmc", rychmc);

				list.add(zcxx);
			}
		}

		map.put("ydjxjrs", ydjxjrs);
		map.put("edjxjrs", edjxjrs);
		map.put("sdjxjrs", sdjxjrs);
		map.put("zcxxList", list);
	}

	/**
	 * ����������Ϣ
	 * 
	 * @author luojw
	 */
	@SuppressWarnings("unchecked")
	private void setQtxx(HashMap<String, Object> map) {
		// ѧ��
		String xn = (String) map.get("xn");
		// �۲���Ϣ�б�
		List<HashMap<String, String>> zcxxList = (List<HashMap<String, String>>) map
				.get("zcxxList");

		// ������Ϣ�б�
		List<HashMap<String, String>> qtxxList = dao.getQtxxList(zcxxList, xn);
		// ���ֵ
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (zcxxList != null && zcxxList.size() > 0) {
			for (int i = 0; i < zcxxList.size(); i++) {

				HashMap<String, String> zcxx = zcxxList.get(i);

				// ����������
				String bjgms = "";
				// Ӣ��������
				String yydj = "";
				// ������������
				String jsjdj = "";
				// У��ͨ��
				String xjtb = "";
				// ����
				String kkjs = "";
				// Υ�ʹ���
				String wjcf = "";

				if (qtxxList != null && qtxxList.size() > 0) {
					for (int j = 0; j < qtxxList.size(); j++) {
						if (zcxx.get("xh").equalsIgnoreCase(
								qtxxList.get(j).get("xh"))) {
							String mc = qtxxList.get(j).get("mc");
							String lx = qtxxList.get(j).get("lx");

							if ("bjg".equalsIgnoreCase(lx)) {// �������Ŀ
								bjgms = mc;
							} else if ("yy3j".equalsIgnoreCase(lx)) {// CET3
								yydj = Base.isNull(yydj) ? ("CET3:" + mc)
										: (("CET3:" + mc) + "��" + yydj);
							} else if ("yy4j".equalsIgnoreCase(lx)) {// CET4
								yydj = Base.isNull(yydj) ? ("CET4:" + mc)
										: (("CET4:" + mc) + "��" + yydj);
							} else if ("yy6j".equalsIgnoreCase(lx)) {// CET6
								yydj = Base.isNull(yydj) ? ("CET6:" + mc)
										: (("CET6:" + mc) + "��" + yydj);
							} else if ("jsj1j".equalsIgnoreCase(lx)) {// �����1��
								jsjdj = Base.isNull(jsjdj) ? ("�����һ��:" + mc)
										: (("�����һ��:" + mc) + "��" + jsjdj);
							} else if ("jsj2j".equalsIgnoreCase(lx)) {// �����2��
								jsjdj = Base.isNull(jsjdj) ? ("���������:" + mc)
										: (("���������:" + mc) + "��" + jsjdj);
							} else if ("jsj3j".equalsIgnoreCase(lx)) {// �����3��
								jsjdj = Base.isNull(jsjdj) ? ("���������:" + mc)
										: (("���������:" + mc) + "��" + jsjdj);
							} else if ("xjtb".equalsIgnoreCase(lx)) {// У��ͨ��
								if (!Base.isNull(mc)) {
									xjtb = "��";
								}
							} else if ("kkjs".equalsIgnoreCase(lx)) {// У��ͨ��
								kkjs = mc;
							} else if ("wjcf".equalsIgnoreCase(lx)) {// Υ�ʹ���
								wjcf = Base.isNull(wjcf) ? mc
										: (mc + "��" + wjcf);
							}
						}
					}
				}

				zcxx.put("bjgms", bjgms);
				zcxx.put("yydj", yydj);
				zcxx.put("jsjdj", jsjdj);
				zcxx.put("xjtb", xjtb);
				zcxx.put("kkjs", kkjs);
				zcxx.put("wjcf", wjcf);
				list.add(zcxx);
			}
		}

		map.put("zcxxList", list);
	}
	/**
	 * ����������Ϣ(������)
	 * 
	 * @author cmj
	 */
	@SuppressWarnings("unchecked")
	private void setQtxxNew(HashMap<String, Object> map) {
		// ѧ��
		String xn = (String) map.get("xn");
		String xq = (String) map.get("xq");
		// �۲���Ϣ�б�
		List<HashMap<String, String>> zcxxList = (List<HashMap<String, String>>) map
				.get("zcxxList");

		// ������Ϣ�б�
		List<HashMap<String, String>> qtxxList = dao.getQtxxListNew(zcxxList, xn,xq);
		// ���ֵ
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (zcxxList != null && zcxxList.size() > 0) {
			for (int i = 0; i < zcxxList.size(); i++) {

				HashMap<String, String> zcxx = zcxxList.get(i);

				// ����������
				String bjgms = "";
				// Ӣ��������
				String yydj = "";
				// ������������
				String jsjdj = "";
				// У��ͨ��
				String xjtb = "";
				// ����
				String kkjs = "";
				// Υ�ʹ���
				String wjcf = "";

				if (qtxxList != null && qtxxList.size() > 0) {
					for (int j = 0; j < qtxxList.size(); j++) {
						if (zcxx.get("xh").equalsIgnoreCase(
								qtxxList.get(j).get("xh"))) {
							String mc = qtxxList.get(j).get("mc");
							String lx = qtxxList.get(j).get("lx");

							if ("bjg".equalsIgnoreCase(lx)) {// �������Ŀ
								bjgms = mc;
							} else if ("yy3j".equalsIgnoreCase(lx)) {// CET3
								yydj = Base.isNull(yydj) ? ("CET3:" + mc)
										: (("CET3:" + mc) + "��" + yydj);
							} else if ("yy4j".equalsIgnoreCase(lx)) {// CET4
								yydj = Base.isNull(yydj) ? ("CET4:" + mc)
										: (("CET4:" + mc) + "��" + yydj);
							} else if ("yy6j".equalsIgnoreCase(lx)) {// CET6
								yydj = Base.isNull(yydj) ? ("CET6:" + mc)
										: (("CET6:" + mc) + "��" + yydj);
							} else if ("jsj1j".equalsIgnoreCase(lx)) {// �����1��
								jsjdj = Base.isNull(jsjdj) ? ("�����һ��:" + mc)
										: (("�����һ��:" + mc) + "��" + jsjdj);
							} else if ("jsj2j".equalsIgnoreCase(lx)) {// �����2��
								jsjdj = Base.isNull(jsjdj) ? ("���������:" + mc)
										: (("���������:" + mc) + "��" + jsjdj);
							} else if ("jsj3j".equalsIgnoreCase(lx)) {// �����3��
								jsjdj = Base.isNull(jsjdj) ? ("���������:" + mc)
										: (("���������:" + mc) + "��" + jsjdj);
							} else if ("xjtb".equalsIgnoreCase(lx)) {// У��ͨ��
								if (!Base.isNull(mc)) {
									xjtb = "��";
								}
							} else if ("kkjs".equalsIgnoreCase(lx)) {// У��ͨ��
								kkjs = mc;
							} else if ("wjcf".equalsIgnoreCase(lx)) {// Υ�ʹ���
								wjcf = Base.isNull(wjcf) ? mc
										: (mc + "��" + wjcf);
							}
						}
					}
				}

				zcxx.put("bjgms", bjgms);
				zcxx.put("yydj", yydj);
				zcxx.put("jsjdj", jsjdj);
				zcxx.put("xjtb", xjtb);
				zcxx.put("kkjs", kkjs);
				zcxx.put("wjcf", wjcf);
				list.add(zcxx);
			}
		}

		map.put("zcxxList", list);
	}
}