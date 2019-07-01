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
	 * 根据勤工助学主键获取学生勤工信息
	 * @param pkValue
	 * @return
	 */
	public HashMap<String,String>getQgzxInfo(String pkValue){
		
		return dao.getQgzxInfo(pkValue);
		
	}
	
	
	/**
	 * 导出数据excel
	 * @param myForm
	 * @param message
	 * @param request
	 * @param response
	 */
	public void printQggwInfo(CommanForm myForm,HttpServletRequest request, WritableWorkbook wwb)
			throws Exception,IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
	
		String title = Base.xxmc+"院内勤工助学固定岗位需求申报表";
		
		String qsxn=myForm.getQsxn();
		
		String zzxn=myForm.getZzxn();
		
		List<HashMap<String,String>> rs= dao.getQgzxGwInfo(myForm);
		
		WritableCellFormat wcfTytle = new WritableCellFormat();
		WritableSheet ws = wwb.createSheet(title+"("+GetTime.getSystemTime()+")", 0);
		try {			
			
			//标题
			wcfTytle = ExcelMB.getWritableCellFormat(16,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
			ws.mergeCells(0, 0,6, 0);
			ws.addCell(new Label(0,0,title,wcfTytle));
			
			//第一行
			wcfTytle = ExcelMB.getWritableCellFormat(10,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
			ws.mergeCells(0, 1,6, 1);
			ws.addCell(new Label(0,1,"（"+qsxn+"学年）",wcfTytle));
			

			//第二行
			wcfTytle = ExcelMB.getWritableCellFormat(10,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
			ws.mergeCells(0, 2,6, 2);
			ws.addCell(new Label(0,2,"填报部门（盖章）：              填报人：        填报时间：   年  月  日",wcfTytle));
			
			//第三行
			ws.addCell(new Label(0,3,"序号",wcfTytle));
			ws.addCell(new Label(1,3,"岗位名称",wcfTytle));
			ws.addCell(new Label(2,3,"岗位职责",wcfTytle));
			ws.addCell(new Label(3,3,"岗位要求",wcfTytle));
			ws.addCell(new Label(4,3,"用工时间(小时/人/月)",wcfTytle));
			ws.addCell(new Label(5,3,"拟需求人数",wcfTytle));
			ws.addCell(new Label(6,3,"备注",wcfTytle));
	
		
			//总计
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
			
//			第二行
			wcfTytle = ExcelMB.getWritableCellFormat(10,true, Alignment.LEFT, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
			ws.mergeCells(0, 4+rs.size(),6, 4+rs.size());
			ws.addCell(new Label(0,4+rs.size(),"注：本表需于每年10月1日前报至学生资助管理中心。",wcfTytle));
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
