package xgxt.pjpy.hhgxy.pjtj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.ExcelMethods;

public class PjpyHhgxyPjtjService {
	/**
	 * 学生成绩统计打印
	 * @return
	 * @throws Exception
	 */
	public void pytj(WritableWorkbook wwb, PjpyModel model) throws Exception {
		PjpyHhgxyPjtjDAO dao=new PjpyHhgxyPjtjDAO();
		//获取科目代码，科目名称
		ArrayList<HashMap<String, String>> kmmcList =(ArrayList)dao.getKmmc(model);
		//获取科目代码，科目名称
		ArrayList<HashMap<String, String>> xscjList =(ArrayList)dao.getXscj(model);
		//获取综合测评成绩
		ArrayList<HashMap<String, String>> zccjList =(ArrayList)dao.getZccj(model);
		//判断学校类型
		String xxlx=model.getXxlx();
		
		HashMap<String,String>hashMap=dao.getXqmc(model);
		int xscjLength=kmmcList.size();
		try {
			// 创建xls中SHEET对象
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			// 设置对齐方式
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
		
			
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);
			ws.mergeCells(0, 0,10+xscjLength, 0);
			ws.addCell(new Label(0,0,"学年："+model.getXn()+"          学期："+hashMap.get("xqmc"),wcfTytle));
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setWrap(true);
//			 设置表格边框
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			
			//表头长度
			int titleLength=8+kmmcList.size();	
			//课程数目
			
			ws.addCell(new Label(0,1,  "序号", wcfTytle));
			ws.addCell(new Label(1,1,  "学号", wcfTytle));
			ws.addCell(new Label( 2,1, "姓名", wcfTytle));
			//科目名称
			for(int i = 0 ; i < xscjLength ; i++){
				//学生课程名称
				Map<String, String> xscjMap = kmmcList.get(i);
				ws.addCell(new Label(3+i,1, xscjMap.get("kcmc"), wcfTytle));
			}
			ws.addCell(new Label(3+xscjLength,1, "平均成绩", wcfTytle));
			
			//是否通用学校判断
			if(!"tyxx".equalsIgnoreCase(xxlx)){
				//淮海工学院特殊
				ws.addCell(new Label(4+xscjLength,1, "学分绩点加权平均成绩", wcfTytle));
				ws.addCell(new Label(5+xscjLength,1, "德育操行分", wcfTytle));
				ws.addCell(new Label(6+xscjLength,1, "智育操行分", wcfTytle));
				ws.addCell(new Label(7+xscjLength,1, "体育操行分", wcfTytle)); 
				ws.addCell(new Label(8+xscjLength,1, "综合测评成绩", wcfTytle));
				ws.addCell(new Label(9+xscjLength,1, "奖学金等级", wcfTytle));
				ws.addCell(new Label(10+xscjLength,1, "备注", wcfTytle));
			}else {
				ws.addCell(new Label(4+xscjLength,1, "备注", wcfTytle));
			}
			
			for(int j = 0; j < zccjList.size(); j++){
				for(int k = 0; k<kmmcList.size();k++){
				
				
					 ws.addCell(new Label(k+3, 2+j,"   ", wcfTytle));
					
				}
			}
			//补0
			
			for(int i = 0 ; i < zccjList.size(); i++){
				for(int k = 0; k<kmmcList.size();k++){
					ws.addCell(new Label(3+k,i+2, ""+0, wcfTytle));
				}
			}
		
			for(int j = 0; j < zccjList.size(); j++){
				//综合测评信息
				HashMap<String,String>zccjMap = zccjList.get(j);
				ws.addCell(new Label( 0, j+2,""+(j+1), wcfTytle));
				ws.addCell(new Label( 1, j+2,zccjMap.get("xh"), wcfTytle));
				ws.addCell(new Label( 2, j+2, zccjMap.get("xm"), wcfTytle));
				float sum=0.0f;
				for(int k = 0; k<kmmcList.size();k++){
					
					HashMap<String,String>kmmcMap=kmmcList.get(k);
					
					for(int i=0;i< xscjList.size(); i++){
						 
						HashMap<String,String>xscjMap=xscjList.get(i);
						
						if(xscjMap.get("kcsbm").equalsIgnoreCase(kmmcMap.get("kcsbm"))
								&& zccjMap.get("xh").equalsIgnoreCase(xscjMap.get("xh"))){
							
							if(!"".equalsIgnoreCase(xscjMap.get("cj"))&& xscjMap.get("cj")!=null){
								ws.addCell(new Label(k+3, 2+j, xscjMap.get("cj"), wcfTytle));
								sum+=Float.parseFloat(xscjMap.get("cj"));
								sum=Float.parseFloat(new java.text.DecimalFormat("##0.00").format(sum));
							}else{
								ws.addCell(new Label(k+3, 2+j,"0", wcfTytle));
							}
						}
					}
				}
				//平均成绩
				ws.addCell(new Label(3+xscjLength,j+2, "	"+Float.parseFloat(new java.text.DecimalFormat("##0.00").format(sum/xscjLength)), wcfTytle));
				
				if(!"tyxx".equalsIgnoreCase(xxlx)){
					ws.addCell(new Label(4+xscjLength,j+2, "	", wcfTytle));	
					ws.addCell(new Label(5+xscjLength,j+2, zccjMap.get("dcj"), wcfTytle));
					ws.addCell(new Label(6+xscjLength,j+2, zccjMap.get("zcj"), wcfTytle));
					ws.addCell(new Label(7+xscjLength,j+2, zccjMap.get("tcj"), wcfTytle));
					ws.addCell(new Label(8+xscjLength,j+2, zccjMap.get("zpf"), wcfTytle));
					ws.addCell(new Label(9+xscjLength,j+2, "  ", wcfTytle));
					ws.addCell(new Label(10+xscjLength,j+2, zccjMap.get("bz"), wcfTytle));
				}else{
					ws.addCell(new Label(4+xscjLength,j+2, zccjMap.get("bz"), wcfTytle));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

}
