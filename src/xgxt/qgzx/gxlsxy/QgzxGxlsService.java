package xgxt.qgzx.gxlsxy;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.form.CommanForm;
import xgxt.gygl.tjfx.GyglTjfxForm;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xsgzgl.comm.BasicService;

public class QgzxGxlsService extends BasicService{

	QgzxGxlsDAO dao=new QgzxGxlsDAO();
	
	/**
	 * �����ڹ���ѧ������ȡѧ���ڹ���Ϣ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String,String>getQgzxInfo(String pkValue){
		
		return dao.getQgzxInfo(pkValue);
		
	}
	
	
	/**
	 * ��������excel
	 * @param myForm
	 * @param message
	 * @param request
	 * @param response
	 */
	public void printQggwInfo(CommanForm myForm,HttpServletRequest request, WritableWorkbook wwb)
			throws Exception,IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
	
		String title = Base.xxmc+"Ժ���ڹ���ѧ�̶���λ�����걨��";
		
		String qsxn=myForm.getQsxn();
		
		String zzxn=myForm.getZzxn();
		
		List<HashMap<String,String>> rs= dao.getQgzxGwInfo(myForm);
		
		WritableCellFormat wcfTytle = new WritableCellFormat();
		WritableSheet ws = wwb.createSheet(title+"("+GetTime.getSystemTime()+")", 0);
		try {			
			
			//����
			wcfTytle = ExcelMB.getWritableCellFormat(16,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// ��Ԫ����ʽ
			ws.mergeCells(0, 0,6, 0);
			ws.addCell(new Label(0,0,title,wcfTytle));
			
			//��һ��
			wcfTytle = ExcelMB.getWritableCellFormat(10,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// ��Ԫ����ʽ
			ws.mergeCells(0, 1,6, 1);
			ws.addCell(new Label(0,1,"��"+qsxn+"ѧ�꣩",wcfTytle));
			

			//�ڶ���
			wcfTytle = ExcelMB.getWritableCellFormat(10,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// ��Ԫ����ʽ
			ws.mergeCells(0, 2,6, 2);
			ws.addCell(new Label(0,2,"����ţ����£���              ��ˣ�        �ʱ�䣺   ��  ��  ��",wcfTytle));
			
			//������
			ws.addCell(new Label(0,3,"���",wcfTytle));
			ws.addCell(new Label(1,3,"��λ����",wcfTytle));
			ws.addCell(new Label(2,3,"��λְ��",wcfTytle));
			ws.addCell(new Label(3,3,"��λҪ��",wcfTytle));
			ws.addCell(new Label(4,3,"�ù�ʱ��(Сʱ/��/��)",wcfTytle));
			ws.addCell(new Label(5,3,"����������",wcfTytle));
			ws.addCell(new Label(6,3,"��ע",wcfTytle));
	
		
			//�ܼ�
			for(int i =0;i<rs.size();i++){
				
				HashMap<String,String>qggwMap=rs.get(i);
				ws.addCell(new Label(0,4+i, String.valueOf(i+1),wcfTytle));
				ws.addCell(new Label(1,4+i,qggwMap.get("gwdm"),wcfTytle));
				ws.addCell(new Label(2,4+i,qggwMap.get("gznr"),wcfTytle));
				ws.addCell(new Label(3,4+i,qggwMap.get("gwtsyq"),wcfTytle));
				ws.addCell(new Label(4,4+i,qggwMap.get("gzsj"),wcfTytle));
				ws.addCell(new Label(5,4+i,qggwMap.get("xyrs"),wcfTytle));
				ws.addCell(new Label(6,4+i,qggwMap.get("bz"),wcfTytle));
			}
			
//			�ڶ���
			wcfTytle = ExcelMB.getWritableCellFormat(10,true, Alignment.LEFT, VerticalAlignment.CENTRE,Border.ALL);// ��Ԫ����ʽ
			ws.mergeCells(0, 4+rs.size(),6, 4+rs.size());
			ws.addCell(new Label(0,4+rs.size(),"ע����������ÿ��10��1��ǰ����ѧ�������������ġ�",wcfTytle));
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
