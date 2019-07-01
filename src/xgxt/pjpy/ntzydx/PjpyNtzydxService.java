package xgxt.pjpy.ntzydx;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.pjpy.PjpyTyForm;
import xgxt.pjpy.tyb.zhszcp.action.PjpyZhszcpwhActionForm;
import xgxt.pjpy.tyb.zhszcp.dao.PjpyZhszcpDAO;
import xgxt.pjpy.tyb.zhszcp.service.PjpyZhszcpService;
import xgxt.pjpy.zjjd.JxjpdxxModel;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

public class PjpyNtzydxService {
	
	/**
	 * ��ѧ�������ж�
	 * @param jxjpdModel
	 * @param lb
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> pdStuTjFlag(JxjpdxxModel model, String lb){
		String msg = "";//������ʾ��Ϣ
		boolean result = true;//�������
		HashMap<String, String> tjjcMap = new HashMap<String, String>();
		//����һ�Ƚ�ѧ����뱾ѧ��ÿѧ�ڶ������һ���ۺϲ�����ѧ��ͬ����ȡ�����Ҳ���
		PjpyNtzydxDAO dao = new PjpyNtzydxDAO();
		//ͬ�ȼ��۲⽱����
		int tdjzcjxs = dao.getXnzhcpJxjs(model);
		if(tdjzcjxs<2){
			result = false;
			msg = "ѧ���ڱ������λ��ͬ�ȼ������ۺϲ�����ѧ��ſ�������ý�ѧ��";
		}
		
		tjjcMap.put("message", msg);
		tjjcMap.put("result", String.valueOf(result));		
		return tjjcMap;
	}
	
	/**
	 * �ۺϲ���ͳ�Ʊ����ӡ
	 * @param model
	 * @param type
	 * @param os
	 * @throws Exception 
	 * */
	public void printZhcptjbb(PjpyZhszcpwhActionForm model, String type,OutputStream os) throws Exception{
		if("1".equalsIgnoreCase(type)){
			//ƽ��ѧ�ּ���ͳ��
			printPjxfjdbb(model,os);
		}else if("2".equalsIgnoreCase(type)){
			//�ۺϲ�������Ŀͳ��
			printZhcpxmfbb(model,os);
		}
	}
	
	/**
	 * ƽ��ѧ�ּ���ͳ�Ʊ��ӡ
	 * @param model
	 * @param os
	 * */
	public void printPjxfjdbb(PjpyZhszcpwhActionForm model, OutputStream os){
		String title = "�ۺϲ���ͳ�Ʊ�";
		PjpyNtzydxDAO dao = new PjpyNtzydxDAO();
		//ѧ���ɼ����۲��
		List<HashMap<String, String>> data = dao.queryXscjxx(model);
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("�ۺϲ���ͳ�Ʊ�", 0);

		try {
			excel.printTitle(ws, title.toString(), 7, 800);// ����	
			//���ݵ�Ԫ����ʽ
			WritableCellFormat dataWcf = ExcelMB.getWritableCellFormat(10,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ	
								

			//д��ͷ
			ws.addCell(new Label(0, 1, "���", dataWcf));
			ws.addCell(new Label(1, 1, "ϵ  ��", dataWcf));
			ws.addCell(new Label(2, 1, "��  ��", dataWcf));
			ws.addCell(new Label(3, 1, "ѧ  ��", dataWcf));
			ws.addCell(new Label(4, 1, "��  ��", dataWcf));
			ws.addCell(new Label(5, 1, "ƽ���ɼ�ѧ�ּ���", dataWcf));
			ws.addCell(new Label(6, 1, "�ۺϲ����ɼ�", dataWcf));
			
			
			
			//�������
			for (int i = 0; i < data.size(); i++) {
				ws.addCell(new Label(0, 2 + i, (i+1)+"", dataWcf));
				ws.addCell(new Label(1, 2 + i, data.get(i).get("xymc"), dataWcf));
				ws.addCell(new Label(2, 2 + i, data.get(i).get("bjmc"), dataWcf));
				ws.addCell(new Label(3, 2 + i, data.get(i).get("xh"), dataWcf));
				ws.addCell(new Label(4, 2 + i, data.get(i).get("xm"), dataWcf));
				ws.addCell(new Label(5, 2 + i, data.get(i).get("pjxfjd"), dataWcf));
				ws.addCell(new Label(6, 2 + i, data.get(i).get("zf"), dataWcf));			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * �ۺϲ���ͳ�Ʊ��ӡ
	 * @param model
	 * @param os
	 * @throws Exception 
	 * */
	public void printZhcpxmfbb(PjpyZhszcpwhActionForm model, OutputStream os) throws Exception{
		String title = "��ְͨҵ��ѧѧ���ۺ����ʲ����ɼ�ͳ�Ʊ�";
		String zczq = "xq";
		String jb = "4";
		HashMap<String, String> map = new HashMap<String, String>();
		PjpyZhszcpService service = new PjpyZhszcpService();
		PjpyNtzydxDAO dao = new PjpyNtzydxDAO();
		
		model.setQueryequals_xn(StringUtils.isNull(model.getQueryequals_xn()) ? Base.getJxjsqxn() : model.getQueryequals_xn());
		model.setQueryequals_xq(StringUtils.isNull(model.getQueryequals_xq()) ? Base.getJxjsqxq() : model.getQueryequals_xq());
		
		HashMap<String, String> titleMap = dao.getZhcpxmfTitle(model);
		//��ȡ��ѯ���ֶ���Ϣ
		HashMap<String, String[]> colMap = service.getZhszcpzfExpTitle(zczq,
																		jb, 
																		map, 
																		true, 
																		model);
		//��ѯ�ۺϲ���������
		List<String[]> data = dao.queryZhszcpzfForExp(zczq, 
				                                          jb, 
				                                          map, 
				                                          colMap.get("en"), 
				                                          model, 
				                                          false);
		
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("�ۺ����ʲ����ɼ�ͳ�Ʊ�", 0);

		try {
			excel.printTitle(ws, title.toString(), 20, 800);// ����			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(10,
					true, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			// ��������Ԫ����ʽ
			WritableCellFormat fbtLWcf = ExcelMB.getWritableCellFormat(10,
					true, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);
			// �������ҵ�Ԫ����ʽ
			WritableCellFormat fbtRWcf = ExcelMB.getWritableCellFormat(10,
					true, Alignment.RIGHT, VerticalAlignment.CENTRE,
					Border.NONE);
			//���ݵ�Ԫ����ʽ
			WritableCellFormat dataWcf = ExcelMB.getWritableCellFormat(10,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			ws.addCell(new Label(0, 1, titleMap.get("xymc")+titleMap.get("bjmc")+"��ϵ�����£�", fbtLWcf));
			ws.mergeCells(0, 1, 16, 1);
			ws.addCell(new Label(17, 1, model.getQueryequals_xn()+"ѧ���" + titleMap.get("xqmc")+ "ѧ��", fbtRWcf));
			ws.mergeCells(17, 1, 20, 1);
			
			//д��ͷ
			ws.addCell(new Label(0, 2, "", wcfTytle));
			ws.mergeCells(0, 2, 0, 4);
			ws.addCell(new Label(1, 2, "��  ��  ��  ��  30��", wcfTytle));
			ws.mergeCells(1, 2, 8, 2);
			ws.addCell(new Label(9, 2, "��  ��  ��  ��  60��", wcfTytle));
			ws.mergeCells(9, 2, 13, 2);
			ws.addCell(new Label(14, 2, "��  ��  ��  ��  10��", wcfTytle));
			ws.mergeCells(14, 2, 18, 2);
			ws.addCell(new Label(19, 2, "�ۺϲ����ܷ�", wcfTytle));
			ws.mergeCells(19, 2, 19, 4);
			ws.addCell(new Label(20, 2, "����", wcfTytle));
			ws.mergeCells(20, 2, 20, 4);
			
			ws.addCell(new Label(1, 3, "˼��	Ʒ��6��", wcfTytle));
			ws.mergeCells(1, 3, 1, 4);
			ws.addCell(new Label(2, 3, "��֯����5��", wcfTytle));
			ws.mergeCells(2, 3, 2, 4);
			ws.addCell(new Label(3, 3, "ѧϰ̬��4��", wcfTytle));
			ws.mergeCells(3, 3, 3, 4);
			ws.addCell(new Label(4, 3, "�������5��", wcfTytle));
			ws.mergeCells(4, 3, 4, 4);
			ws.addCell(new Label(5, 3, "�ճ���Ϊ", wcfTytle));
			ws.mergeCells(5, 3, 7, 3);
			ws.addCell(new Label(5, 4, "������", wcfTytle));
			ws.addCell(new Label(6, 4, "�ӷ�", wcfTytle));
			ws.addCell(new Label(7, 4, "����", wcfTytle));
			ws.addCell(new Label(8, 3, "С��", wcfTytle));
			ws.mergeCells(8, 3, 8, 4);
			ws.addCell(new Label(9, 3, "ѧϰ�ɼ�", wcfTytle));
			ws.mergeCells(9, 3, 9, 4);
			ws.addCell(new Label(10, 3, "������", wcfTytle));
			ws.mergeCells(9, 3, 11, 3);
			ws.addCell(new Label(10, 4, "������", wcfTytle));
			ws.addCell(new Label(11, 4, "�ӷ�", wcfTytle));
			ws.addCell(new Label(12, 4, "����", wcfTytle));
			ws.addCell(new Label(13, 3, "С��", wcfTytle));
			ws.mergeCells(13, 3, 13, 4);
			ws.addCell(new Label(14, 3, "�����γɼ�4��", wcfTytle));
			ws.mergeCells(14, 3, 14, 4);
			ws.addCell(new Label(15, 3, "�������2��", wcfTytle));
			ws.mergeCells(15, 3, 15, 4);
			ws.addCell(new Label(16, 3, "����״��2��", wcfTytle));
			ws.mergeCells(16, 3, 16, 4);
			ws.addCell(new Label(17, 3, "��������2��", wcfTytle));
			ws.mergeCells(17, 3, 17, 4);
			ws.addCell(new Label(18, 3, "С��", wcfTytle));
			ws.mergeCells(18, 3, 18, 4);
			
			//�������
			for (int i = 0; i < data.size(); i++) {
				float xj = 0;
				
				ws.addCell(new Label(0, 5 + i, data.get(i)[2], dataWcf));
				ws.addCell(new Label(1, 5 + i, data.get(i)[6], dataWcf));
				ws.addCell(new Label(2, 5 + i, data.get(i)[8], dataWcf));
				ws.addCell(new Label(3, 5 + i, data.get(i)[10], dataWcf));
				ws.addCell(new Label(4, 5 + i, data.get(i)[12], dataWcf));
				ws.addCell(new Label(5, 5 + i, "7��", dataWcf));
				ws.addCell(new Label(6, 5 + i, Integer.parseInt(StringUtils.isNull(data.get(i)[14]) ? "0" : data.get(i)[14]) >=7 ? Integer.parseInt(StringUtils.isNull(data.get(i)[14]) ? "0" : data.get(i)[14])-7+"" : "0", dataWcf));
				ws.addCell(new Label(7, 5 + i, data.get(i)[16], dataWcf));
				//С��
				xj = Float.parseFloat(StringUtils.isNull(data.get(i)[6]) ? "0" : data.get(i)[6]) 
				     + Float.parseFloat(StringUtils.isNull(data.get(i)[8]) ? "0" : data.get(i)[8])
				     + Float.parseFloat(StringUtils.isNull(data.get(i)[10]) ? "0" : data.get(i)[10])
				     + Float.parseFloat(StringUtils.isNull(data.get(i)[12]) ? "0" : data.get(i)[12])
				     //+ 7
				     + Float.parseFloat(StringUtils.isNull(data.get(i)[14]) ? "0" : data.get(i)[14])
					 - Float.parseFloat(StringUtils.isNull(data.get(i)[16]) ? "0" : data.get(i)[16]);
				     
				ws.addCell(new Label(8, 5 + i, xj+"", dataWcf));
				xj = 0;
				
				//ѧϰ�ɼ�
				ws.addCell(new Label(9, 5 + i, StringUtils.isNull(data.get(i)[18]) ? "0" : data.get(i)[18], dataWcf));
				ws.addCell(new Label(10, 5 + i, "5��", dataWcf));
				ws.addCell(new Label(11, 5 + i, Integer.parseInt(StringUtils.isNull(data.get(i)[20]) ? "0" : data.get(i)[20]) >=5 ? Integer.parseInt(StringUtils.isNull(data.get(i)[20]) ? "0" : data.get(i)[20])-5+"" : "0", dataWcf));
				ws.addCell(new Label(12, 5 + i, data.get(i)[22], dataWcf));
				//С��
				xj = Float.parseFloat(StringUtils.isNull(data.get(i)[18]) ? "0" : data.get(i)[18])
				     //+ 5
				     + Float.parseFloat(StringUtils.isNull(data.get(i)[20]) ? "0" : data.get(i)[20])
				     - Float.parseFloat(StringUtils.isNull(data.get(i)[22]) ? "0" : data.get(i)[22]);
				ws.addCell(new Label(13, 5 + i, xj+"", dataWcf));
				xj = 0;
				
				//�����ɼ�
				ws.addCell(new Label(14, 5 + i, data.get(i)[24], dataWcf));
				ws.addCell(new Label(15, 5 + i, data.get(i)[26], dataWcf));
				ws.addCell(new Label(16, 5 + i, data.get(i)[28], dataWcf));
				ws.addCell(new Label(17, 5 + i, data.get(i)[30], dataWcf));
				//С��
				xj = Float.parseFloat(StringUtils.isNull(data.get(i)[24]) ? "0" : data.get(i)[24])
				     + Float.parseFloat(StringUtils.isNull(data.get(i)[26]) ? "0" : data.get(i)[26])
				     + Float.parseFloat(StringUtils.isNull(data.get(i)[28]) ? "0" : data.get(i)[28])
				     + Float.parseFloat(StringUtils.isNull(data.get(i)[30]) ? "0" : data.get(i)[30]);
				ws.addCell(new Label(18, 5 + i, xj+"", dataWcf));
				xj = 0;
				
				ws.addCell(new Label(19, 5 + i, data.get(i)[32], dataWcf));
				ws.addCell(new Label(20, 5 + i, data.get(i)[33], dataWcf));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
