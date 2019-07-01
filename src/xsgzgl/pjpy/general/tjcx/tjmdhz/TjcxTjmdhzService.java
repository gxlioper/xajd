package xsgzgl.pjpy.general.tjcx.tjmdhz;

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
import xgxt.utils.date.MoneyFormat;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.tjcx.TjcxHjjehzInterface;
import xsgzgl.pjpy.general.inter.tjcx.TjcxTjmdhzInterface;

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

public class TjcxTjmdhzService extends CommService implements
		TjcxTjmdhzInterface {

	TjcxTjmdhzDAO dao = new TjcxTjmdhzDAO();

	/**
	 * ��ñ�ͷ�ļ�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getTjmdhzTop(TjcxTjmdhzModel model,
			User user) {
		
		DAO dao = DAO.getInstance();
		
		String[] en =null;
		
		String[] cn =null;
		
		// ѧԺ�Ƽ���������
		en = new String[] { "pjdj", "xm", "xh", "nj", "bjrs", "dycj",
					"zcf","zcfbjpm","pjfcj","tyzdf" };
		cn = new String[] { "�����ȼ�",  "����","ѧ��","�꼶", "�༶<����>", "�����ɼ�", "�۲�ɼ�",
					"�۲�����","ƽ���ɼ�","������ͷ�" };
		
		
		
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ý����
	 * 
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getTjmdhzList(PjpyGeneralForm myForm,
			TjcxTjmdhzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		ArrayList<String[]> list = dao.getTjmdhzList(myForm, model, user);

		return list;
	}

	/**
	 * ���������
	 * 
	 * @author ΰ�����
	 */
	public String createTjmdhzHTML(SearchRsModel rsModel,
			TjcxTjmdhzModel model, ArrayList<String[]> rsArrList, User user) {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * �����񽱽�����
	 * 
	 * @author qlj
	 */
	public void expTjmdhz(PjpyGeneralForm myForm,TjcxTjmdhzModel model,User user, OutputStream os)
			throws Exception {

		String xn = model.getXn();
		String[]xmmc = model.getXmmcArr();
		String[] nj = model.getNj();
		String[] xydm = model.getXydm();
		String[] zydm = model.getZydm();
		String[] bjdm = model.getBjdm();
		String nowTime = dao.getNowTime("YYYY��MM��DD��");// ���ϵͳʱ��
		
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
		title.append("ѧԺ��ѧ���Ƽ��������ܱ�");
		
		model.setType("exp");
		List<String[]>tjmdhzList=dao.getTjmdhzList(myForm, model, user);

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet( "��ѧ������ܱ�", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 10, 0);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 10, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 650,
				Border.NONE);
		
		ws.mergeCells(0, 1, 5, 1);
		
		ws.addCell(new Label(0, 1, "ϵ��", wcf2));
		
		ws.mergeCells(8, 1, 10, 1);
		ws.addCell(new Label(8, 1, "�Ʊ�ʱ�䣺    ��   ��   ��", wcf2));
		// ����ͷ
		ws.addCell(new Label(0, 2, "���", wcf2));
		ws.addCell(new Label(1, 2, "�����ȼ�", wcf2));
		ws.addCell(new Label(2, 2, "����", wcf2));
		ws.addCell(new Label(3, 2, "ѧ��", wcf2));
		ws.addCell(new Label(4, 2, "�꼶", wcf2));
		ws.addCell(new Label(5, 2, "�༶��������", wcf2));
		ws.addCell(new Label(6, 2, "Ʒ����Ϊ�����ɼ�", wcf2));
		ws.addCell(new Label(7, 2, "ѧ���ۺϲ����ɼ�", wcf2));
		ws.addCell(new Label(8, 2, "ѧ���ۺϲ����ɼ��༶����", wcf2));
		ws.addCell(new Label(9, 2, "���ޡ���ѡ��ƽ���֣�����������", wcf2));
		ws.addCell(new Label(10, 2, "��������ͷ�", wcf2));
		
		
		for(int i=0;i<tjmdhzList.size();i++){
			
			String[]tjmdArr=tjmdhzList.get(i);
			
			for(int j=0;j<tjmdArr.length;j++){
				
				ws.addCell(new Label(j, 3+i, tjmdArr[j], wcf2));
				
			}
			
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

}