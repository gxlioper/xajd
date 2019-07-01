package xgxt.sztz.csmzzyjsxy;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.comm.CommService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.sztz.form.SztzForm;
import xgxt.utils.ExcelMethods;

public class SztzTjlgService {
	
	/**
	 * �����������չ��ͳ��
	 */
	public void tjlgTzcj(SztzForm sztzForm,
			HttpServletRequest request, WritableWorkbook wwb)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
			//����ѧ�� ��ȡѧ����Ϣ
			SztzTjlgDAO dao=new SztzTjlgDAO();
			XsxxglService xsService=new XsxxglService();
			HashMap<String,String>xsMap=xsService.selectStuinfo(sztzForm.getXh());
			xsMap.putAll(xsService.getStuJtxx(sztzForm.getXh()));
			//������չ����Ϣ
			List<String[]>tzcjList=dao.getTzcjTj(xsMap);
			
			List<String[]>getZfList=dao.getZfList(xsMap);
			//ѧ�ꡢѧ����Ϣ
			List<HashMap<String,String>>xnxqList=dao.getXnXqList(xsMap.get("nj"));
			try {
				// ����xls��SHEET����
				WritableSheet ws = wwb.getSheet(0);
				WritableCellFormat wcfTytle = new WritableCellFormat();
				// ���ö��뷽ʽ
				Alignment alignMent = Alignment.CENTRE;
				VerticalAlignment vag = VerticalAlignment.CENTRE;

				wcfTytle.setVerticalAlignment(vag);
				wcfTytle.setAlignment(alignMent);

				WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
				wfTytle.setBoldStyle(WritableFont.BOLD);
				wfTytle.setPointSize(16);
				wcfTytle.setFont(wfTytle);
				//�ϲ���Ԫ��(��ͷ)
				ws.mergeCells(0, 0,10, 0);
				ws.addCell(new Label(0,0,"�������ѧ�л���ϢѧԺ",wcfTytle));
				ws.mergeCells(0, 1,10, 1);
				ws.addCell(new Label(0,1,"ѧ������ѧ�ֿ�Ƭ",wcfTytle));
				wcfTytle = new WritableCellFormat();
				alignMent = Alignment.LEFT;
				vag = VerticalAlignment.CENTRE;

				wcfTytle.setVerticalAlignment(vag);
				wcfTytle.setAlignment(alignMent);
				 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

				wfTytle = new WritableFont(WritableFont.ARIAL);
				wfTytle.setBoldStyle(WritableFont.BOLD);
				wfTytle.setPointSize(10);
				wcfTytle.setFont(wfTytle);

				wcfTytle = new WritableCellFormat();
				alignMent = Alignment.CENTRE;
				vag = VerticalAlignment.CENTRE;

				wcfTytle.setVerticalAlignment(vag);
				wcfTytle.setAlignment(alignMent);
				wcfTytle.setWrap(true);
				//���ñ��߿�
				 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		

				wfTytle = new WritableFont(WritableFont.ARIAL);
				wfTytle.setPointSize(8);
				wcfTytle.setFont(wfTytle);
				
				
				// ===================ѧ����Ϣ ��һ��===========================
				ws.addCell(new Label(0,2,"����",wcfTytle));
				ws.mergeCells(1, 2,2, 2);
				ws.addCell(new Label(1,2,xsMap.get("xm"),wcfTytle));
				ws.addCell(new Label(3,2,"�Ա�",wcfTytle));
				ws.mergeCells(4, 2,5, 2);
				ws.addCell(new Label(4,2,xsMap.get("xb"),wcfTytle));
				ws.addCell(new Label(6,2,"���֤",wcfTytle));
				ws.addCell(new Label(7,2,xsMap.get("sfzh"),wcfTytle));
				ws.mergeCells(8, 2,9, 2);
				ws.addCell(new Label(8,2,"ѧ��",wcfTytle));
				ws.addCell(new Label(10,2,xsMap.get("xh"),wcfTytle));
				
				// ===================ѧ����Ϣ �ڶ���===========================
				ws.addCell(new Label(0,3,"�༶",wcfTytle));
				ws.mergeCells(1, 3,2, 3);
				ws.addCell(new Label(1,3,xsMap.get("bjmc"),wcfTytle));
				ws.addCell(new Label(3,3,"ϵ",wcfTytle));
				ws.mergeCells(4, 3,5,3);
				ws.addCell(new Label(4,3,xsMap.get("xymc"),wcfTytle));
				ws.addCell(new Label(6,3,"רҵ",wcfTytle));
				ws.mergeCells(7, 3,10, 3);
				ws.addCell(new Label(7,3,xsMap.get("zymc"),wcfTytle));
				// ===================ѧ����Ϣ ������===========================
				ws.addCell(new Label(0,4,"��ϵ�绰",wcfTytle));
				ws.mergeCells(1, 4,2, 4);
				ws.addCell(new Label(1,4,xsMap.get("lxdh"),wcfTytle));
				ws.addCell(new Label(3,4,"��ͥסַ",wcfTytle));
				ws.mergeCells(4, 4,10,4);
				ws.addCell(new Label(4,4,xsMap.get("jtdz"),wcfTytle));
				
				// ===================ѧ����Ϣ ������===========================
				ws.addCell(new Label(0,5,"�ʱ�",wcfTytle));
				ws.mergeCells(1, 5,2, 5);
				ws.addCell(new Label(1,5,xsMap.get("jtyb"),wcfTytle));
				ws.addCell(new Label(3,5,"��ѧʱ��",wcfTytle));
				ws.mergeCells(4, 5,5,5);
				ws.addCell(new Label(4,5,xsMap.get("rxrq"),wcfTytle));
				ws.addCell(new Label(6,5,"��ҵʱ��",wcfTytle));
				ws.mergeCells(7, 5,10,5);
				ws.addCell(new Label(7,5,xsMap.get("byny"),wcfTytle));
				
				ws.addCell(new Label(0,6,"���",wcfTytle));
				ws.addCell(new Label(1,6,"��Ŀ",wcfTytle));
				ws.addCell(new Label(2,6,"�ܷ�",wcfTytle));
				
				// ===================ѧ��ѧ���е����======================
				for(int i=0;i<xnxqList.size();i++){
					HashMap<String,String>xnxqMap=xnxqList.get(i);
				
					ws.addCell(new Label(3+2*i,6,xnxqMap.get("xn")+"ѧ��"+xnxqMap.get("oneXq")+"ѧ��",wcfTytle));
					
					ws.addCell(new Label(4+2*i,6,xnxqMap.get("xn")+"ѧ��"+xnxqMap.get("twoXq")+"ѧ��",wcfTytle));
					
				}
				
				// ====================�ϲ���Ԫ��==============================
				CommService commService=new CommService();
				int []hbArr=commService.getLines(tzcjList, 7, 0);
				for (int j = 0; j < hbArr.length; j++) {
					if (hbArr.length == 1) {
						ws.mergeCells(0, hbArr[j], 0, 7 + tzcjList.size() - 1);
					} else if (j == hbArr.length - 1) {
						ws.mergeCells(0, hbArr[j], 0, hbArr[j]);
					} else {
						ws.mergeCells(0, hbArr[j], 0, hbArr[j + 1] - 1);
					}
				}
				// ===================��չ�ɼ�======================
				for(int i=0;i<tzcjList.size();i++){
					String[]tzcjArr=tzcjList.get(i);
					for(int j=0;j<tzcjArr.length;j++){
						if("0".equalsIgnoreCase(tzcjArr[j]) && j!=2){
							ws.addCell(new Label(j,i+7," ",wcfTytle));
						}else{
							ws.addCell(new Label(j,i+7,tzcjArr[j],wcfTytle));
						}
					}
				}
				
				int len=tzcjList.size();
				
				// ===================��չ�ɼ��ּܷ���======================
				String[]zfArr=getZfList.get(0);
				
				ws.addCell(new Label(0,7+len," ",wcfTytle));
				ws.addCell(new Label(1,7+len,"�ϼ�",wcfTytle));
				for(int i=0;i<zfArr.length;i++){
					if("0".equalsIgnoreCase(zfArr[i])){
						ws.addCell(new Label(i+2,7+len," ",wcfTytle));
					}else{
						ws.addCell(new Label(i+2,7+len,zfArr[i],wcfTytle));
					}
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			// ��ͻ������
			ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ����PKֵ��ȡѧ��
	 * @param pk
	 * @return HashMap<String,String>
	 */
	public HashMap<String,String>getTzxsxx(String pk){
		SztzTjlgDAO dao=new SztzTjlgDAO();
		return dao.getTzxsxx(pk);
	}
	
}
