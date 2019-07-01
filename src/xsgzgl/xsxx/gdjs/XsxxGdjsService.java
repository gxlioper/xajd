package xsgzgl.xsxx.gdjs;

import java.io.File;
import java.util.HashMap;
import java.util.List;


import jxl.write.Label;

import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.action.Base;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.ExcelMethods;
import xgxt.xtwh.zpgl.XtwhZpglService;
import xsgzgl.xsxx.xsxxhz.XsxxXxhzbService;

public class XsxxGdjsService extends XsxxXxhzbService{
	
	XsxxGdjsDAO dao=new XsxxGdjsDAO();
	/**
	 * 校优秀毕业生打印
	 * @param wwb
	 * @param map
	 */
	public void printXsxx(WritableWorkbook wwb,String xh) {

		XsxxglService stuService = new XsxxglService();
		// 学生基本信息
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		// 违纪处分信息
		List<HashMap<String,String>>wjcfInfo=dao.getWjcfInfo(xh);
		// 评奖评优信息
		List<HashMap<String,String>>pjpyInfo=dao.getPjpyInfo(xh);
//		 评奖评优信息
		List<HashMap<String,String>>xxjlInfo=dao.getXxjlInfo(xh);
		
		List<HashMap<String,String>>bzrpyInfo=dao.getBzrpyInfo(xh);
		
		HashMap<String,String>jtcyMap=dao.getJtcyInfo(xh);
		
		HashMap<String,String>byjdMap=dao.getByjdInfo(xh);
		
		StringBuilder xsjbxx=new StringBuilder();
		
		xsjbxx.append("广东建设职业技术学院(盖章)");
		xsjbxx.append("办学类型:普通高等教育");
		
		xsjbxx.append(" 专业:");
		xsjbxx.append(stuInfo.get("zymc"));
		
		xsjbxx.append(" 年级:");
		xsjbxx.append(stuInfo.get("nj"));
		
		xsjbxx.append(" 班级:");
		xsjbxx.append(stuInfo.get("bjmc"));
		
		xsjbxx.append(" 学制:");
		String xz=Base.isNull(stuInfo.get("xz")) ? "  ":stuInfo.get("xz");
		xsjbxx.append(xz);
		
		xsjbxx.append(" 学号:");
		xsjbxx.append(stuInfo.get("xh"));
	
		
		try {
			WritableSheet ws = wwb.getSheet(0);
			XtwhZpglService service = new XtwhZpglService();
			
			File file = service.getPhotoFile(stuInfo.get("xh"));
			//插入照片
			if(file==null){}else{
			WritableImage wi = new WritableImage(17, 0, 2, 5, file);
			ws.addImage(wi);
			}
			
			ws.addCell(new Label(0,2,xsjbxx.toString(),ws.getCell(0, 2).getCellFormat()));
			// --------------------------first line--------------------------------------
			//姓名
			ws.addCell(new Label(1,3,stuInfo.get("xm"),ws.getCell(1, 3).getCellFormat()));
			//性别
			ws.addCell(new Label(3,3,stuInfo.get("xb"),ws.getCell(3,3).getCellFormat()));
			//民族
			ws.addCell(new Label(5,3,stuInfo.get("mzmc"),ws.getCell(5,3).getCellFormat()));
			//入学时间
			ws.addCell(new Label(8,3,stuInfo.get("rxrq"),ws.getCell(8,3).getCellFormat()));	
			//出生年月
			ws.addCell(new Label(10,3,stuInfo.get("csrq"),ws.getCell(10, 3).getCellFormat()));	
			//政治面貌
			ws.addCell(new Label(13,3,stuInfo.get("zzmmmc"),ws.getCell(13, 3).getCellFormat()));
			//政治面貌
			ws.addCell(new Label(15,3,stuInfo.get("jg"),ws.getCell(15, 3).getCellFormat()));
			// --------------------------first line--------------------------------------
			
			
			//家庭地址
			ws.addCell(new Label(3,4,stuInfo.get("jtdz"),ws.getCell(3, 4).getCellFormat()));
//			家庭地址
			ws.addCell(new Label(10,4,stuInfo.get("jtyb"),ws.getCell(10, 4).getCellFormat()));
//			联系电话
			ws.addCell(new Label(13,4,stuInfo.get("lxdh"),ws.getCell(13, 4).getCellFormat()));
			
			ws.addCell(new Label(15,4,stuInfo.get("byny"),ws.getCell(15, 4).getCellFormat()));
			
		
			
			
			for(int i=0;i<xxjlInfo.size();i++){
				
				HashMap<String,String>xxjlMap=xxjlInfo.get(i);
				
				ws.addCell(new Label(1,6+i,xxjlMap.get("xxsj"),ws.getCell(1,6+i).getCellFormat()));
				
				ws.addCell(new Label(5,6+i,xxjlMap.get("dwmc"),ws.getCell(5,6+i).getCellFormat()));
			}
			
			ws.addCell(new Label(1,12,jtcyMap.get("jtcy1_gx"),ws.getCell(1, 12).getCellFormat()));
			ws.addCell(new Label(2,12,jtcyMap.get("jtcy1_xm"),ws.getCell(2, 12).getCellFormat()));
			ws.addCell(new Label(5,12,jtcyMap.get("zzmm1"),ws.getCell(5, 12).getCellFormat()));
			ws.addCell(new Label(7,12,jtcyMap.get("gzdwdz1"),ws.getCell(7, 12).getCellFormat()));
			
			ws.addCell(new Label(1,13,jtcyMap.get("jtcy2_gx"),ws.getCell(1, 13).getCellFormat()));
			ws.addCell(new Label(2,13,jtcyMap.get("jtcy2_xm"),ws.getCell(2, 13).getCellFormat()));
			ws.addCell(new Label(5,13,jtcyMap.get("zzmm2"),ws.getCell(5, 13).getCellFormat()));
			ws.addCell(new Label(7,13,jtcyMap.get("gzdwdz2"),ws.getCell(7, 13).getCellFormat()));
			
			ws.addCell(new Label(1,14,jtcyMap.get("jtcy3_gx"),ws.getCell(1, 14).getCellFormat()));
			ws.addCell(new Label(2,14,jtcyMap.get("jtcy3_xm"),ws.getCell(2, 14).getCellFormat()));
			ws.addCell(new Label(5,14,jtcyMap.get("zzmm3"),ws.getCell(5, 14).getCellFormat()));
			ws.addCell(new Label(7,14,jtcyMap.get("gzdwdz3"),ws.getCell(7, 14).getCellFormat()));
			
			ws.addCell(new Label(1,15,jtcyMap.get("jtcy4_gx"),ws.getCell(1, 15).getCellFormat()));
			ws.addCell(new Label(2,15,jtcyMap.get("jtcy4_xm"),ws.getCell(2, 15).getCellFormat()));
			ws.addCell(new Label(5,15,jtcyMap.get("zzmm4"),ws.getCell(5, 15).getCellFormat()));
			ws.addCell(new Label(7,15,jtcyMap.get("gzdwdz4"),ws.getCell(7, 15).getCellFormat()));
			
			ws.addCell(new Label(1,16,jtcyMap.get("jtcy5_gx"),ws.getCell(1, 16).getCellFormat()));
			ws.addCell(new Label(2,16,jtcyMap.get("jtcy5_xm"),ws.getCell(2, 16).getCellFormat()));
			ws.addCell(new Label(5,16,jtcyMap.get("zzmm5"),ws.getCell(5, 16).getCellFormat()));
			ws.addCell(new Label(7,16,jtcyMap.get("gzdwdz5"),ws.getCell(7, 16).getCellFormat()));
			
			
			if(pjpyInfo!=null && pjpyInfo.size()>5){
				
				StringBuilder pjxx=new StringBuilder();
				
				ws.mergeCells(0, 17,8, 21);
				
				for(int i=0;i<pjpyInfo.size();i++){
					
					HashMap<String,String>pjpyMap=pjpyInfo.get(i);
					
					pjxx.append(pjpyMap.get("pjxx"));
					pjxx.append(";");
					
				}
				
				ws.addCell(new Label(0,17,pjxx.toString(),ws.getCell(0,17).getCellFormat()));
				
			}else if(pjpyInfo!=null){
				
				
				for(int i=0;i<pjpyInfo.size();i++){
					ws.mergeCells(0, 17+i,8, 17+i);
					HashMap<String,String>pjpyMap=pjpyInfo.get(i);
					
					ws.addCell(new Label(0,17+i,pjpyMap.get("pjxx"),ws.getCell(0,17+i).getCellFormat()));
					
				}
				
			}
			
			// -------------------------违纪处分信息 begin------------------------------
			if(wjcfInfo!=null && wjcfInfo.size()>4){
				
				StringBuilder pjxx=new StringBuilder();
				
				ws.mergeCells(0, 23,8, 26);
				
				for(int i=0;i<pjpyInfo.size();i++){
					
					HashMap<String,String>wjcfMap=wjcfInfo.get(i);
					
					pjxx.append(wjcfMap.get("wjxx"));
					pjxx.append(";");
					
				}
				
				ws.addCell(new Label(0,23,pjxx.toString(),ws.getCell(0,23).getCellFormat()));
				
			}else if(wjcfInfo!=null){
				
				for(int i=0;i<wjcfInfo.size();i++){
					
					ws.mergeCells(0, 23+i,9, 23+i);
					
					HashMap<String,String>wjcfMap=wjcfInfo.get(i);
					
					ws.addCell(new Label(0,23+i,wjcfMap.get("wjxx"),ws.getCell(0,23+i).getCellFormat()));
					
				}
				
			}
			
			
			for(int i=0;i<bzrpyInfo.size();i++){
				HashMap<String,String>bzrpyMap=bzrpyInfo.get(i);
				if(i==2){
					ws.addCell(new Label(11,20,bzrpyMap.get("pyyj"),ws.getCell(11,20).getCellFormat()));
					ws.addCell(new Label(11,26,"班主任："+bzrpyMap.get("pyrxm"),ws.getCell(11,26).getCellFormat()));
				}else if(i==1){
					ws.addCell(new Label(11,13,bzrpyMap.get("pyyj"),ws.getCell(11,13).getCellFormat()));
					ws.addCell(new Label(11,19,"班主任："+bzrpyMap.get("pyrxm"),ws.getCell(11,19).getCellFormat()));
				}else if(i==0){
					ws.addCell(new Label(11,6,bzrpyMap.get("pyyj"),ws.getCell(11,6).getCellFormat()));
					ws.addCell(new Label(11,12,"班主任："+bzrpyMap.get("pyrxm"),ws.getCell(11,12).getCellFormat()));
				}
				
			}
			
			ws.addCell(new Label(15,6,byjdMap.get("byjd"),ws.getCell(15,6).getCellFormat()));
			
			// -------------------------违纪处分信息 end------------------------------
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
}
