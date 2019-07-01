package xsgzgl.pjpy.general.tjcx.hjjehz;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.MoneyFormat;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.tjcx.TjcxHjjehzInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_ͳ�Ʒ���_�񽱽�����_ͨ��_Service��
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

public class TjcxHjjehzService extends CommService implements
		TjcxHjjehzInterface {

	TjcxHjjehzDAO dao = new TjcxHjjehzDAO();

	/**
	 * ��ñ�ͷ�ļ�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getHjjehzTop(TjcxHjjehzModel model,
			User user) {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "xn", "xh", "xm", "nj", "bjmc", "xmlx",
				"xmmc", "hdsj", "xmje" };
		String[] cn = new String[] { "ѧ��", "ѧ��", "����", "�꼶", "�༶����", "��Ŀ����",
				"��Ŀ����", "���ʱ��", "��Ŀ���" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ý����
	 * 
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getHjjehzList(PjpyGeneralForm myForm,
			TjcxHjjehzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		ArrayList<String[]> list = dao.getHjjehzList(myForm, model, user);

		return list;
	}

	/**
	 * ���������
	 * 
	 * @author ΰ�����
	 */
	public String createHjjehzHTML(SearchRsModel rsModel,
			TjcxHjjehzModel model, ArrayList<String[]> rsArrList, User user) {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * �����񽱽�����
	 * 
	 * @author ΰ�����
	 */
	public void expHjjehz(TjcxHjjehzModel model, OutputStream os)
			throws Exception {

		String xn = model.getXn();
		String[]xmmc = model.getXmmcArr();
		String[] nj = model.getNj();
		String[] xydm = model.getXydm();
		String[] zydm = model.getZydm();
		String[] bjdm = model.getBjdm();
		String nowTime = dao.getNowTime("YYYY��MM��DD��");// ���ϵͳʱ��
		
		List<HashMap<String,String>>xmxzList=dao.getXmxzList();
		
		List<HashMap<String,String>>xmmcList=dao.getXmmcBylx("02", xn);
		
		// ѧ������
		String xqmc = getOneValue("xqdzb", "xqmc", "xqdm", "02");

		// ��ѧ������
		//String jxjmc = xmmc;

		// �����������
		String yhmc = "�й�ũҵ����";

		// ���ñ���
		StringBuffer title = new StringBuffer();
		title.append(xn);
		title.append("ѧ��");
		title.append(xqmc);
		title.append("ѧ��");
		//title.append(jxjmc);
		title.append("��ѧ�����Ž�����ϸ��");

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet( "��ѧ������ܱ�", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 6+xmxzList.size()+xmmcList.size(), 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 12, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 650,
				Border.NONE);

		// ����ͷ
		ws.addCell(new Label(0, 2, "���", wcf2));
		ws.addCell(new Label(1, 2, "ѧ������", wcf2));
		ws.addCell(new Label(2, 2, "Ժϵ", wcf2));
		ws.addCell(new Label(3, 2, "רҵ", wcf2));
		
		
		for(int i=0;i<xmxzList.size();i++){
			HashMap<String,String>xmxzMap=xmxzList.get(i);
			ws.addCell(new Label(4+i, 2, xmxzMap.get("xzmc")+"�����", wcf2));
		}
		
		for(int i=0;i<xmmcList.size();i++){
			HashMap<String,String>xmmcMap=xmmcList.get(i);
			ws.addCell(new Label(4+xmxzList.size()+i, 2, xmmcMap.get("xmmc")+"�����", wcf2));
		}
		
		ws.addCell(new Label(4+xmxzList.size()+xmmcList.size(), 2, "���", wcf2));
		ws.addCell(new Label(5+xmxzList.size()+xmmcList.size(), 2, yhmc + "����", wcf2));
		ws.addCell(new Label(6+xmxzList.size()+xmmcList.size(), 2, "�ϼ�", wcf2));

		// ��ѧ����ͳ���б�
		List<HashMap<String, String>> list = dao.getJxjjeList(xn, xmmc, nj,
				xydm, zydm, bjdm);

		int zjje = 0;// �ܼƽ��

		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);

				ws.addCell(new Label(0, 3 + i, String.valueOf(i + 1), wcf2));// ���
				ws.addCell(new Label(1, 3 + i, map.get("xm"), wcf2));// ����
				ws.addCell(new Label(2, 3 + i, map.get("xymc"), wcf2));// ѧԺ
				ws.addCell(new Label(3, 3 + i, map.get("zymc"), wcf2));// רҵ
				
				for(int j=0;j<xmxzList.size();j++){
					HashMap<String,String>xmxzMap=xmxzList.get(j);
					ws.addCell(new Label(4+j, 3 + i, map.get("jxjmc_"+j), wcf2));
				}
				
				for(int j=0;j<xmmcList.size();j++){
					HashMap<String,String>xmxzMap=xmxzList.get(j);
					ws.addCell(new Label(4+xmxzList.size()+j, 3 + i, map.get("rychmc_"+j), wcf2));
				}
				
				ws.addCell(new Label(4+xmxzList.size()+xmmcList.size(), 3 + i, map.get("je"), wcf2));// ���
				ws.addCell(new Label(5+xmxzList.size()+xmmcList.size(), 3 + i, map.get("yhkh"), wcf2));// ���п���
				ws.addCell(new Label(6+xmxzList.size()+xmmcList.size(), 3 + i, map.get("zj"), wcf2));// �ϼ�

				// �ۼӽ������ܼƽ��
				if (!Base.isNull(map.get("je"))) {
					zjje += Integer.parseInt(map.get("je"));
				}
			}

			// ��β��Ϣ
			if(zjje==0){
				ws.addCell(new Label(0, list.size() + 3, "�ϼƣ���"));// �ܼƽ��
				ws.mergeCells(0, list.size() + 3,6+xmxzList.size()+xmmcList.size(), list.size() + 3);
			}else{
				ws.addCell(new Label(0, list.size() + 3, "�ϼƣ�"
						+ MoneyFormat.format(""+zjje), wcf2));// �ܼƽ��
				ws.mergeCells(0, list.size() + 3, 6+xmxzList.size()+xmmcList.size(), list.size() + 3);
			}

			WritableCellFormat wcf3 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
			wcf3 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.LEFT, VerticalAlignment.CENTRE, true, null);

			StringBuffer msg = new StringBuffer();
			msg.append("�Ʊ��ˣ�");
			msg.append("                         ");
			msg.append("֤���ˣ�");
			msg.append("                          ");
			msg.append("�Ʊ�ʱ�䣺" + nowTime);

			ws.addCell(new Label(0, list.size() + 5, msg.toString(), wcf3));// �ܼƽ��,֤����,�Ʊ�ʱ��
			ws.mergeCells(0, list.size() + 5, 6+xmxzList.size()+xmmcList.size(), list.size() + 5);

			ws.addCell(new Label(0, list.size() + 7, "�����쵼ǩ�֣�", wcf3));// �����쵼ǩ��
			ws.mergeCells(0, list.size() + 7, 6+xmxzList.size()+xmmcList.size(), list.size() + 7);

			ws.addCell(new Label(0, list.size() + 9, "У�쵼ǩ�֣�", wcf3));// У�쵼ǩ��
			ws.mergeCells(0, list.size() + 9, 6+xmxzList.size()+xmmcList.size(), list.size() + 9);

			for (int i = 0; i < list.size(); i++) {
				WritableCell h = ws.getWritableCell(6+xmxzList.size()+xmmcList.size(), 3 + i);// �ϼ�
				//System.out.println(i + "@@@" + h.getContents());
				
				String value = h.getContents();
			
				String rowspan = value.split("luojw")[1];
				String zj = value.split("luojw")[2];
				String row = list.get(i).get("num");
				
				if ("1".equalsIgnoreCase(row)) {
					ws.mergeCells(6+xmxzList.size()+xmmcList.size(), 3 + i, 6+xmxzList.size()+xmmcList.size(), 3 + i
							+ Integer.parseInt(rowspan) - 1);
					ws.addCell(new Label(6+xmxzList.size()+xmmcList.size(), 3 + i, zj, wcf2));// �ϼ�
				}
			}
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ��ý����
	 * 
	 * @author ��ǿ
	 */
	public ArrayList<String[]> getCMHJMDHZList(PjpyGeneralForm myForm,
			TjcxHjjehzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		ArrayList<String[]> list = dao.getCMHJMDHZList(myForm, model, user);

		return list;
	}
	
	/**
	 * ��ñ�ͷ�ļ�
	 * 
	 * @author ��ǿ
	 */
	public List<HashMap<String, String>> getCMHJMDHZTop(TjcxHjjehzModel model,
			User user) {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "bjmc","xm","jxj","shxs","yxxsgb","dxjhdz",
				"ffje", "yhkh", "bz" };
		String[] cn = new String[] { "�༶����", "����", "Ժ�ڽ�ѧ��", "����ѧ��", "����ѧ���ɲ�", "���",
				"���Ž��", "���п���", "��ע" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}
	
	/**
	 * �����񽱽�����
	 * 
	 * @author ��ǿ
	 */
	public void expCmhjmchz(TjcxHjjehzModel model, OutputStream os)
			throws Exception {

		String xn = model.getXn();
		String[] nj = model.getNj();
		String[] xydm = model.getXydm();
		String[] zydm = model.getZydm();
		String[] bjdm = model.getBjdm();
		
		
		
		// ѧ������
		String xqmc = "��һ";

		// ѧУ����
		String xxmc = "�㽭��ýѧԺ";


		// ������ͳ���б�
		List<HashMap<String, String>> list = dao.getCmhjmdList(xn, nj,
				xydm, zydm, bjdm);
		
		// ���ñ���
		StringBuffer title = new StringBuffer();
		title.append(xxmc);
		title.append(xn);
		title.append("ѧ��");
		title.append(xqmc);
		title.append("ѧ��");
		title.append(list != null && list.size() > 0 && list.get(0)!=null && list.get(0).get("xymc") !=null ? list.get(0).get("xymc") :"");
		title.append("�������������ܱ�");

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet( "��ѧ������ܱ�", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 9, 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 12, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);

		// ����ͷ
		ws.addCell(new Label(0, 2, "�༶", wcf2));
		ws.addCell(new Label(1, 2, "����", wcf2));
		ws.addCell(new Label(2, 2, "Ժ�ڽ�ѧ�����ߣ�һ���������ȣ�", wcf2));
		ws.addCell(new Label(3, 2, "����ѧ�������", wcf2));
		ws.addCell(new Label(4, 2, "����ѧ���ɲ������", wcf2));
		ws.addCell(new Label(5, 2, "��������", wcf2));
		ws.addCell(new Label(6, 2, "Ժ�⽱ѧ������", wcf2));
		ws.addCell(new Label(7, 2, "���Ž��", wcf2));
		ws.addCell(new Label(8, 2, "ѧ�����п���", wcf2));
		ws.addCell(new Label(9, 2, "��ע", wcf2));
		
		

		int zjje = 0;// �ܼƽ��

		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);

				ws.addCell(new Label(0, 3 + i, map.get("bjmc"), wcf2));// ���
				ws.addCell(new Label(1, 3 + i, map.get("xm"), wcf2));// ����
				ws.addCell(new Label(2, 3 + i, map.get("jxj"), wcf2));// ѧԺ
				ws.addCell(new Label(3, 3 + i, map.get("shxs"), wcf2));// רҵ
				ws.addCell(new Label(4, 3 + i, map.get("yxxsgb"), wcf2));// ѧԺ
				ws.addCell(new Label(5, 3 + i, map.get("dxjhdz"), wcf2));// ѧԺ
				ws.addCell(new Label(6, 3 + i, "", wcf2));// ѧԺ
				ws.addCell(new Label(7, 3 + i, map.get("ffje"), wcf2));// ѧԺ
				ws.addCell(new Label(8, 3 + i, map.get("yhkh"), wcf2));// ѧԺ
				ws.addCell(new Label(9, 3 + i, map.get("bz"), wcf2));// ѧԺ
				
				
				// �ۼӽ������ܼƽ��
				if (!Base.isNull(map.get("ffje"))) {
					zjje += Integer.parseInt(map.get("ffje"));
				}
			}

		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

}