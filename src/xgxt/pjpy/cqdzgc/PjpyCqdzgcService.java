package xgxt.pjpy.cqdzgc;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.szdw.utils.MakeQuery;
import xgxt.utils.ExcelMethods;
import xgxt.xszz.commonN05.QueryModel;
import xgxt.xszz.commonN05.XszzCommonN05DAO;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class PjpyCqdzgcService {
	
	
	
	public void printPjpy(PjpyCqdzgcForm form,
			HttpServletRequest request, WritableWorkbook wwb)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		PjpyCqdzgcDao pjpyCqdzgc=new PjpyCqdzgcDao();
		
		//学业信息List
		List<HashMap<String,String>>xyxx=pjpyCqdzgc.getXyxx(form);
		int xyxxLength=xyxx.size();
		//学生品德基本分
		HashMap<String,String>pdjbf=pjpyCqdzgc.getXsPdjbf(form);
		
		//学生品德附加分
		List<HashMap<String,String>>pdfjf=pjpyCqdzgc.getXsPdfjf(form);
		int pdfjfLength=pdfjf.size();
		//学生品德扣分
		List<HashMap<String,String>>pdkf=pjpyCqdzgc.getXsPdkf(form);
		int pdkfLength=pdkf.size();
		//学生品德扣分
		HashMap<String,String>kjwtjbf=pjpyCqdzgc.getXsKjwtjbf(form);
		
		//学生品德扣分
		List<HashMap<String,String>>kjwtfjf=pjpyCqdzgc.getXsKjwtfjf(form);
		int kjwtfjfLength=kjwtfjf.size();
		//学生品德扣分
		HashMap<String,String>shsjjbf=pjpyCqdzgc.getXsShsjjbf(form);

		//学生品德扣分
		List<HashMap<String,String>>shsjfjf=pjpyCqdzgc.getXsShsjfjf(form);
		//学生学业分信息、
		HashMap<String,String>xsxyxx=pjpyCqdzgc.getXsxyxx(form);
		//学生综测信息
		HashMap<String,String>xszcxx=pjpyCqdzgc.getXszcxx(form);
		
		
		
		int shsjfjfLength=shsjfjf.size();
		int fjfMaxLength=0;
		int maxLength=0;
		if(pdfjfLength > kjwtfjfLength){
			fjfMaxLength=pdfjfLength;
		}else {
			fjfMaxLength=kjwtfjfLength;
		}
		
		if(fjfMaxLength<shsjfjfLength){
			fjfMaxLength=shsjfjfLength;
		}
		
		maxLength=fjfMaxLength+pdkfLength;
		if(xyxxLength>maxLength){
			maxLength=xyxxLength;
		}
		
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
			wfTytle.setPointSize(16);
			wcfTytle.setFont(wfTytle);
			ws.mergeCells(0, 0,9, 0);
			ws.addCell(new Label(0,0,"重庆电子工程职业学院学生综合测评表",wcfTytle));
			
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
			
			//各项附加分总和和扣分总和
			float pdfifSum=0.0f;
			float shsjfjfSum=0.0f;
			float kjwtfifSum=0.0f;
			float pdkfSum=0.0f;
			float zxf=0.0f;
			float zxfjd=0.0f;
			
			ws.mergeCells(0, 1,9, 1);
			ws.addCell(new Label(0,1,"姓名："+xszcxx.get("xm")+"            （院）系："+xszcxx.get("xymc")+"               专业："+xszcxx.get("zymc")+"               班级："+xszcxx.get("bjmc") ,wcfTytle));
			ws.mergeCells(0, 2,9, 2);
			ws.addCell(new Label(0,2,"    学年第    学期：      —      学年第    学期 ",wcfTytle));
			//加分合并单元格
			ws.mergeCells(3, 4,3,5+fjfMaxLength);
			ws.addCell(new Label(3,4,"加分",wcfTytle));
			
			//扣分合并单元格
			ws.mergeCells(3,6+fjfMaxLength, 3,6+fjfMaxLength+pdkfLength);
			ws.addCell(new Label(3,6+fjfMaxLength,"扣分",wcfTytle));
			ws.mergeCells(0, 3,2, 3);
			ws.addCell(new Label(0,3,"学业A",wcfTytle));
			ws.addCell(new Label(3,3,"项目",wcfTytle));
			ws.mergeCells(4, 3,5,3);
			ws.addCell(new Label(4,3,"品德C",wcfTytle));
			ws.mergeCells(6, 3,7,3);
			ws.addCell(new Label(6,3,"科技文体及学习能力D",wcfTytle));
			ws.mergeCells(8, 3,9,3);
			ws.addCell(new Label(8,3,"社会实践E",wcfTytle));

			ws.addCell(new Label(0,4,"科目",wcfTytle));
			ws.addCell(new Label(1,4,"学分",wcfTytle));
			ws.addCell(new Label(2,4,"学分绩点",wcfTytle));
			ws.addCell(new Label(4,4,"项目",wcfTytle));
			ws.addCell(new Label(5,4,"得分",wcfTytle));
			ws.addCell(new Label(6,4,"项目",wcfTytle));
			ws.addCell(new Label(7,4,"得分",wcfTytle));
			ws.addCell(new Label(8,4,"项目",wcfTytle));
			ws.addCell(new Label(9,4,"得分",wcfTytle));
			
			for(int i=0;i<maxLength;i++){
				
				//学业信息长度大于i（还没有输出完） 
				if(xyxxLength>i){
					ws.addCell(new Label(0,5+i,xyxx.get(i).get("kcmc"),wcfTytle));
					ws.addCell(new Label(1,5+i,xyxx.get(i).get("xf"),wcfTytle));
					ws.addCell(new Label(2,5+i,xyxx.get(i).get("jd"),wcfTytle));
					zxf+=Float.parseFloat(xyxx.get(i).get("xf"));
					zxfjd+=Float.parseFloat(xyxx.get(i).get("jd"));
				}else{
					//空的补空格
					ws.addCell(new Label(0,5+i,"     ",wcfTytle));
					ws.addCell(new Label(1,5+i,"     ",wcfTytle));
					ws.addCell(new Label(2,5+i,"     ",wcfTytle));
				}
				
				//附加分
				if(fjfMaxLength>i){
					//品德附加分
					if(pdfjfLength>i){
						ws.addCell(new Label(4,5+i,pdfjf.get(i).get("dm"),wcfTytle));
						ws.addCell(new Label(5,5+i,pdfjf.get(i).get("fs"),wcfTytle));
						pdfifSum+=Float.parseFloat(pdfjf.get(i).get("fs"));
						
						
					}else{
						ws.addCell(new Label(4,5+i,"  ",wcfTytle));
						ws.addCell(new Label(5,5+i,"  ",wcfTytle));
					}
					//科技文体附加分
					if(kjwtfjfLength>i){
						ws.addCell(new Label(6,5+i,kjwtfjf.get(i).get("dm"),wcfTytle));
						ws.addCell(new Label(7,5+i,kjwtfjf.get(i).get("fs"),wcfTytle));
						kjwtfifSum+=Float.parseFloat(kjwtfjf.get(i).get("fs"));
					}else{
						ws.addCell(new Label(6,5+i,"  ",wcfTytle));
						ws.addCell(new Label(7,5+i,"  ",wcfTytle));
					}
					
//					社会实践附加分
					if(shsjfjfLength>i){
						ws.addCell(new Label(8,5+i,shsjfjf.get(i).get("dm"),wcfTytle));
						ws.addCell(new Label(9,5+i,shsjfjf.get(i).get("fs"),wcfTytle));
						shsjfjfSum+=Float.parseFloat(shsjfjf.get(i).get("fs"));
					}else{
						ws.addCell(new Label(8,5+i,"  ",wcfTytle));
						ws.addCell(new Label(9,5+i,"  ",wcfTytle));
					}
				}else{
					//开始输出品德扣分
					if(fjfMaxLength+pdkfLength>i){
						ws.addCell(new Label(4,6+i,pdkf.get(i-fjfMaxLength).get("dm"),wcfTytle));
						ws.addCell(new Label(5,6+i,pdkf.get(i-fjfMaxLength).get("fs"),wcfTytle));
						ws.addCell(new Label(6,6+i,"   ",wcfTytle));
						ws.addCell(new Label(7,6+i,"   ",wcfTytle));
						ws.addCell(new Label(8,6+i,"   ",wcfTytle));
						ws.addCell(new Label(9,6+i,"   ",wcfTytle));
						pdkfSum+=Float.parseFloat(pdkf.get(i-fjfMaxLength).get("fs"));
					}else{
						ws.addCell(new Label(4,6+i,"  ",wcfTytle));
						ws.addCell(new Label(5,6+i,"  ",wcfTytle));
					}
				}
			}
			ws.addCell(new Label(4,5+fjfMaxLength,"合计",wcfTytle));
			ws.addCell(new Label(5,5+fjfMaxLength, ""+(pdfifSum>50 ? 50.0f : pdfifSum),wcfTytle));//品德附加分
			ws.addCell(new Label(6,5+fjfMaxLength,"合计",wcfTytle));
			ws.addCell(new Label(7,5+fjfMaxLength, ""+(kjwtfifSum>50 ? 50.0f : kjwtfifSum),wcfTytle));//科技文体附加分
			ws.addCell(new Label(8,5+fjfMaxLength,"合计",wcfTytle));
			ws.addCell(new Label(9,5+fjfMaxLength, ""+(shsjfjfSum>50 ? 50.0f : shsjfjfSum),wcfTytle));//社会实践附加分
	
			
			ws.addCell(new Label(4,6+fjfMaxLength+pdkfLength,"合计",wcfTytle));
			ws.addCell(new Label(5,6+fjfMaxLength+pdkfLength, ""+pdkfSum,wcfTytle));//品德扣分
			ws.addCell(new Label(6,6+fjfMaxLength+pdkfLength, "合计",wcfTytle));
			ws.addCell(new Label(7,6+fjfMaxLength+pdkfLength, "  ",wcfTytle));
			ws.addCell(new Label(8,6+fjfMaxLength+pdkfLength, "合计",wcfTytle));
			ws.addCell(new Label(9,6+fjfMaxLength+pdkfLength, "  ",wcfTytle));
			
			
			ws.addCell(new Label(0,7+maxLength,"所修总学分",wcfTytle));
			ws.addCell(new Label(1,7+maxLength,""+zxf,wcfTytle));
			ws.mergeCells(1, 7+maxLength,2,7+maxLength);
			ws.addCell(new Label(3,7+maxLength,"基本分",wcfTytle));
			ws.mergeCells(1, 7+maxLength,2,7+maxLength);
			//品德基本分
			float pjbf=pdjbf.get("fs")==null ? 0.0f:(Float.parseFloat(pdjbf.get("fs")));
			ws.addCell(new Label(4,7+maxLength,""+(pjbf>50 ? 50.0f : pjbf),wcfTytle));
			
			//科技文体基本分
			float kjbf=kjwtjbf.get("fs")==null ? 0.0f:(Float.parseFloat(kjwtjbf.get("fs")));
			ws.addCell(new Label(6,7+maxLength,""+(kjbf>50 ? 50.0f : kjbf),wcfTytle));
			
			//社会实践基本分
			float sjbf=shsjjbf.get("fs")==null ? 0.0f:(Float.parseFloat(shsjjbf.get("fs")));
			ws.addCell(new Label(8,7+maxLength,""+(sjbf>50 ? 50.0f : sjbf),wcfTytle));
			ws.addCell(new Label(9,7+maxLength,"  " ,wcfTytle));
			ws.addCell(new Label(0,8+maxLength,"总学分绩点",wcfTytle));
			ws.mergeCells(1, 8+maxLength,2,8+maxLength);
			ws.addCell(new Label(1,8+maxLength,""+zxfjd,wcfTytle));
			ws.addCell(new Label(3,8+maxLength,"汇总分",wcfTytle));
			float c=pjbf+pdfifSum-pdkfSum;
			ws.addCell(new Label(4,8+maxLength,"C=",wcfTytle));
			ws.addCell(new Label(5,8+maxLength,""+c,wcfTytle));
			float d=kjbf+kjwtfifSum;
			ws.addCell(new Label(6,8+maxLength,"D=",wcfTytle));
			ws.addCell(new Label(7,8+maxLength,""+d,wcfTytle));
			float e=sjbf+shsjfjfSum;
			ws.addCell(new Label(8,8+maxLength,"E=",wcfTytle));
			ws.addCell(new Label(9,8+maxLength,""+e,wcfTytle));
			ws.mergeCells(1, 9+maxLength,2,9+maxLength);
			ws.addCell(new Label(0,9+maxLength,"学期平均学分绩点",wcfTytle));
			ws.addCell(new Label(1,9+maxLength,xsxyxx.get("pjxfjd"),wcfTytle));
			
			ws.addCell(new Label(3,9+maxLength,"比率（%）",wcfTytle));

			ws.addCell(new Label(4,9+maxLength,"40%",wcfTytle));
			ws.addCell(new Label(5,9+maxLength,"  ",wcfTytle));
			ws.addCell(new Label(6,9+maxLength,"30%",wcfTytle));
			ws.addCell(new Label(7,9+maxLength,"  ",wcfTytle));
			ws.addCell(new Label(8,9+maxLength,"30%",wcfTytle));
			ws.addCell(new Label(9,9+maxLength,"  ",wcfTytle));
			//判断学生学业分是否为空
			float a=xsxyxx.get("fs")==null ? 0.0f : Float.parseFloat(xsxyxx.get("fs"));
			a=Float.parseFloat(new java.text.DecimalFormat("##0.00").format(a));
			ws.mergeCells(1, 10+maxLength,2,10+maxLength);
			ws.addCell(new Label(0,10+maxLength,"A=",wcfTytle));
			ws.addCell(new Label(1,10+maxLength,""+a,wcfTytle));
			ws.addCell(new Label(3,10+maxLength,"B=",wcfTytle));
			float b=c*0.4f+d*0.3f+e*0.3f;
			b=Float.parseFloat(new java.text.DecimalFormat("##0.00").format(b));
			ws.addCell(new Label(4,10+maxLength,""+b,wcfTytle));
			ws.addCell(new Label(5,10+maxLength," ",wcfTytle));	
			ws.addCell(new Label(6,10+maxLength,"  ",wcfTytle));
			ws.addCell(new Label(7,10+maxLength," ",wcfTytle));
			ws.addCell(new Label(8,10+maxLength," ",wcfTytle));
			ws.addCell(new Label(9,10+maxLength," ",wcfTytle));
			ws.addCell(new Label(0,11+maxLength,"总分=A×60%+B×40%",wcfTytle));
			ws.mergeCells(1,11+maxLength,9, 11+maxLength);
			ws.addCell(new Label(1,11+maxLength,""+(Float.parseFloat(new java.text.DecimalFormat("##0.00").format(a*0.6+b*0.4))),wcfTytle));
			ws.addCell(new Label(0,12+maxLength,"A学生智育成绩测评积分=（学期平均学分绩点+5）×100",wcfTytle));
			ws.mergeCells(1,12+maxLength,9, 12+maxLength);
			ws.addCell(new Label(1,12+maxLength,""+a,wcfTytle));
			ws.addCell(new Label(0,13+maxLength,"B德育成绩=C（基本分+附加分-扣分）×40%+D(基本分+附加分)×30%+E（基本分+附加分）×30%",wcfTytle));
			ws.mergeCells(1,13+maxLength,9, 13+maxLength);
			ws.addCell(new Label(1,13+maxLength,""+(new java.text.DecimalFormat("##0.00").format(c*0.4+d*0.3+e*0.4)),wcfTytle));
			ws.mergeCells(0,14+maxLength,9, 14+maxLength);
			ws.addCell(new Label(0,14+maxLength,"总分：F=A("+a+")×60%+B("+b+")×40%="+(new java.text.DecimalFormat("##0.00").format((a*0.6+b*0.4)))+"                                        班级排名："+(xszcxx.get("pm")==null?"":xszcxx.get("pm")),wcfTytle));
			ws.addCell(new Label(1,14+maxLength,""+a,wcfTytle));
			ws.mergeCells(0,15+maxLength,9, 15+maxLength);
			ws.addCell(new Label(0,15+maxLength,"班委签名：                         辅导员（班主任）：                        测评时间：      年   月   日",wcfTytle));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
