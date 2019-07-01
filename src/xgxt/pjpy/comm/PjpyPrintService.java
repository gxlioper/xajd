package xgxt.pjpy.comm;

import java.io.File;
import java.util.Map;

import jxl.write.Label;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.action.Base;
import xgxt.utils.ExcelMethods;
import xgxt.xtwh.zpgl.XtwhZpglService;

public class PjpyPrintService {
	
	/**
	 * 校优秀毕业生打印
	 * @param wwb
	 * @param map
	 */
	public void printYxbys(WritableWorkbook wwb, Map<String,String> map) {


		try {
			WritableSheet ws = wwb.getSheet(0);
			//标题
			ws.addCell(new Label(1,0,"天津工业大学"+map.get("nd")+"届优秀毕业生登记表",ws.getCell(1, 0).getCellFormat()));
			//姓名
			ws.addCell(new Label(3,1,map.get("xm"),ws.getCell(3, 1).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="男";
			}else if(map.get("xb").equals("2")){
				xb="女";
			}else if(map.get("xb").equals("男")){
				xb="男";
			}else if(map.get("xb").equals("女")){
				xb="女";
			}
			//性别
			ws.addCell(new Label(5,1,xb,ws.getCell(5, 1).getCellFormat()));
			//民族
			ws.addCell(new Label(8,1,map.get("mzmc"),ws.getCell(8, 1).getCellFormat()));
			//政治面貌
			ws.addCell(new Label(10,1,map.get("zzmmmc"),ws.getCell(10, 1).getCellFormat()));
			//学院
			ws.addCell(new Label(3,2,map.get("xymc"),ws.getCell(3, 2).getCellFormat()));
			//学历
			ws.addCell(new Label(8,2,map.get("xlmc"),ws.getCell(8, 2).getCellFormat()));
			//学制
			ws.addCell(new Label(10,2,map.get("xz"),ws.getCell(10, 2).getCellFormat()));
			//专业、班级
			ws.addCell(new Label(3,3,map.get("zymc")+"、"+map.get("bjmc"),ws.getCell(3, 3).getCellFormat()));
			//出生年月
			ws.addCell(new Label(8,3,map.get("csrq"),ws.getCell(8, 3).getCellFormat()));
			//个人特长
			String grtc = "";
			if(map.get("个人特长")==null || map.get("个人特长").equals("null")){
				grtc = "";
			}else{
				grtc = map.get("个人特长");
			}
			ws.addCell(new Label(10,3,grtc,ws.getCell(10, 3).getCellFormat()));
			//家庭住址
			ws.addCell(new Label(3,4,map.get("jtdz"),ws.getCell(3, 4).getCellFormat()));
			//获奖情况
			String hjqk = "";
			if(map.get("获奖情况")==null || map.get("获奖情况").equals("null")){
				hjqk = "";
			}else{
				hjqk = map.get("获奖情况");
			}
			ws.addCell(new Label(2,5,hjqk,ws.getCell(2, 5).getCellFormat()));
			//论文发表
			String shgz = "";
			if(map.get("社会工作及论文发表情况")==null || map.get("社会工作及论文发表情况").equals("null")){
				shgz = "";
			}else{
				shgz = map.get("社会工作及论文发表情况");
			}
			ws.addCell(new Label(2,6,shgz,ws.getCell(2, 6).getCellFormat()));
			//综合评价
			String zhpj = "";
			if(map.get("综合评价")==null || map.get("综合评价").equals("null")){
				zhpj = "";
			}else{
				zhpj = map.get("综合评价");
			}
			ws.addCell(new Label(2,7,zhpj,ws.getCell(2, 7).getCellFormat()));
			//学院意见
			ws.addCell(new Label(2,9,map.get("xyyj"),ws.getCell(2, 9).getCellFormat()));
			//学校意见
			ws.addCell(new Label(7,9,map.get("xxyj"),ws.getCell(7, 9).getCellFormat()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
		
	
	
	
	/**
	 * 傲龙奖学生打印
	 * @param wwb
	 * @param map
	 */
	public void printAljxj(WritableWorkbook wwb, Map<String,String> map){
		
		WritableSheet ws = wwb.getSheet(0);
		
		try {
			//学校名称
			ws.addCell(new Label(2,4,Base.xxmc,ws.getCell(2, 4).getCellFormat()));
			//专业名称
			ws.addCell(new Label(2,5,map.get("zymc"),ws.getCell(2, 5).getCellFormat()));
			//班级名称
			ws.addCell(new Label(2,6,map.get("bjmc"),ws.getCell(2, 6).getCellFormat()));
			//封面上的姓名
			ws.addCell(new Label(2,7,map.get("xm"),ws.getCell(2, 7).getCellFormat()));
			//表格上的姓名
			ws.addCell(new Label(1,12,map.get("xm"),ws.getCell(1, 12).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="男";
			}else if(map.get("xb").equals("2")){
				xb="女";
			}else if(map.get("xb").equals("男")){
				xb="男";
			}else if(map.get("xb").equals("女")){
				xb="女";
			}
			//性别
			ws.addCell(new Label(3,12,xb,ws.getCell(3, 12).getCellFormat()));
			//民族名称
			ws.addCell(new Label(5,12,map.get("mzmc"),ws.getCell(5, 12).getCellFormat()));
			//出生日期
			ws.addCell(new Label(7,12,map.get("csrq"),ws.getCell(7, 12).getCellFormat()));
			
			File file = new XtwhZpglService().getPhotoFile(map.get("xh"));
			//照片
			if(file==null){}else{
			WritableImage wi=new WritableImage(8,12,1,3,file);   
			ws.addImage(wi);
			}
			//政治面貌
			ws.addCell(new Label(1,13,map.get("zzmmmc"),ws.getCell(1, 13).getCellFormat()));
			//担任职务
			ws.addCell(new Label(3,13,map.get("xszw"),ws.getCell(3, 13).getCellFormat()));
			//生源省市
			ws.addCell(new Label(5,13,map.get("syd"),ws.getCell(5, 13).getCellFormat()));
			//综合测评名次
			String zhcpmc = "";
			if(map.get("综合测评名次")==null || map.get("综合测评名次").equals("null")){
				zhcpmc = "";
			}else{
				zhcpmc = map.get("综合测评名次");
			}
			ws.addCell(new Label(2,15,zhcpmc,ws.getCell(2, 15).getCellFormat()));
			//专业名次
			String zymc = "";
			if(map.get("专业名次")==null || map.get("专业名次").equals("null")){
				zymc = "";
			}else{
				zymc = map.get("专业名次");
			}
			ws.addCell(new Label(5,15,zymc,ws.getCell(5, 15).getCellFormat()));
			//特长
			String tc = "";
			if(map.get("特长")==null || map.get("特长").equals("null")){
				tc = "";
			}else{
				tc = map.get("特长");
			}
			ws.addCell(new Label(8,15,tc,ws.getCell(8, 15).getCellFormat()));
			//大学期间获奖情况
			String hjqk = "";
			if(map.get("获奖情况")==null || map.get("获奖情况").equals("null")){
				hjqk = "";
			}else{
				hjqk = map.get("获奖情况");
			}
			ws.addCell(new Label(1,16,hjqk,ws.getCell(1, 16).getCellFormat()));
			//申请理由
			ws.addCell(new Label(0,23,map.get("sqly"),ws.getCell(0, 23).getCellFormat()));
			//班级辅导员意见
			String bjfdyyj = "";
			if(map.get("班级辅导员意见")==null || map.get("班级辅导员意见").equals("null")){
				bjfdyyj = "";
			}else{
				bjfdyyj = map.get("班级辅导员意见");
			}
			ws.addCell(new Label(1,27,bjfdyyj,ws.getCell(1, 27).getCellFormat()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * 桑麻奖学金打印
	 * @param wwb
	 * @param map
	 */
	public void printSmjxj(WritableWorkbook wwb, Map<String,String> map){
		
		WritableSheet ws = wwb.getSheet(0);
		
		try {
			//学校名称
			ws.addCell(new Label(3,4,Base.xxmc,ws.getCell(3, 4).getCellFormat()));
			//专业名称
			ws.addCell(new Label(3,5,map.get("zymc"),ws.getCell(3, 5).getCellFormat()));
			//班级名称
			ws.addCell(new Label(3,6,map.get("bjmc"),ws.getCell(3, 6).getCellFormat()));
			//封面上的姓名
			ws.addCell(new Label(3,7,map.get("xm"),ws.getCell(3, 7).getCellFormat()));
			//表格上的姓名
			ws.addCell(new Label(1,12,map.get("xm"),ws.getCell(1, 12).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="男";
			}else if(map.get("xb").equals("2")){
				xb="女";
			}else if(map.get("xb").equals("男")){
				xb="男";
			}else if(map.get("xb").equals("女")){
				xb="女";
			}
			//性别
			ws.addCell(new Label(3,12,xb,ws.getCell(3, 12).getCellFormat()));
			//民族名称
			ws.addCell(new Label(5,12,map.get("mzmc"),ws.getCell(5, 12).getCellFormat()));
			//出生日期
			ws.addCell(new Label(7,12,map.get("csrq"),ws.getCell(7, 12).getCellFormat()));
			
			File file = new XtwhZpglService().getPhotoFile(map.get("xh"));
			//照片
			if(file==null){}else{
			WritableImage wi=new WritableImage(8,12,1,3,file);   
			ws.addImage(wi);
			}
			//政治面貌
			ws.addCell(new Label(1,13,map.get("zzmmmc"),ws.getCell(1, 13).getCellFormat()));
			//担任职务
			ws.addCell(new Label(3,13,map.get("xszw"),ws.getCell(3, 13).getCellFormat()));
			//生源省市
			ws.addCell(new Label(5,13,map.get("syd"),ws.getCell(5, 13).getCellFormat()));
			//综合测评名次
			String zhcpmc = "";
			if(map.get("综合测评名次")==null || map.get("综合测评名次").equals("null")){
				zhcpmc = "";
			}else{
				zhcpmc = map.get("综合测评名次");
			}
			ws.addCell(new Label(2,15,zhcpmc,ws.getCell(2, 15).getCellFormat()));
			//专业名次
			String zymc = "";
			if(map.get("专业名次")==null || map.get("专业名次").equals("null")){
				zymc = "";
			}else{
				zymc = map.get("专业名次");
			}
			ws.addCell(new Label(5,15,zymc,ws.getCell(5, 15).getCellFormat()));
			//特长
			String tc = "";
			if(map.get("特长")==null || map.get("特长").equals("null")){
				tc = "";
			}else{
				tc = map.get("特长");
			}
			ws.addCell(new Label(8,15,tc,ws.getCell(8, 15).getCellFormat()));
			//申报等级
			String sbdj = "";
			if(map.get("申报等级")==null || map.get("申报等级").equals("null")){
				sbdj = "";
			}else{
				sbdj = map.get("申报等级");
			}
			ws.addCell(new Label(2,16,sbdj,ws.getCell(2, 16).getCellFormat()));
			//第几次获得桑麻奖学金
			String smjxj = "";
			if(map.get("第几次获得桑麻奖学金")==null || map.get("第几次获得桑麻奖学金").equals("null")){
				smjxj = "";
			}else{
				smjxj = map.get("第几次获得桑麻奖学金");
			}
			ws.addCell(new Label(7,16,smjxj,ws.getCell(7, 16).getCellFormat()));
			//大学期间获奖情况
			String hjqk = "";
			if(map.get("获奖情况")==null || map.get("获奖情况").equals("null")){
				hjqk = "";
			}else{
				hjqk = map.get("获奖情况");
			}
			ws.addCell(new Label(1,17,hjqk,ws.getCell(1, 17).getCellFormat()));
			//申请理由
			ws.addCell(new Label(0,21,map.get("sqly"),ws.getCell(0, 21).getCellFormat()));
			//审核意见
			ws.addCell(new Label(1,27,map.get("shyj1"),ws.getCell(1, 27).getCellFormat()));
			//班级辅导员意见
			String bjfdyyj = "";
			if(map.get("班级辅导员意见")==null || map.get("班级辅导员意见").equals("null")){
				bjfdyyj = "";
			}else{
				bjfdyyj = map.get("班级辅导员意见");
			}
			ws.addCell(new Label(1,28,bjfdyyj,ws.getCell(1, 28).getCellFormat()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 王克昌奖学金报表打印
	 * @param wwb
	 * @param map
	 */
	public void printWkqjxj(WritableWorkbook wwb, Map<String,String> map) {
		try {
			WritableSheet ws = wwb.getSheet(0);
			//学校名称
			ws.addCell(new Label(1,1,Base.xxmc,ws.getCell(1, 1).getCellFormat()));
			//奖学金名称
			ws.addCell(new Label(6,1,map.get("xmmc"),ws.getCell(1, 1).getCellFormat()));
			//姓名
			ws.addCell(new Label(1,2,map.get("xm"),ws.getCell(1, 2).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="男";
			}else if(map.get("xb").equals("2")){
				xb="女";
			}else if(map.get("xb").equals("男")){
				xb="男";
			}else if(map.get("xb").equals("女")){
				xb="女";
			}
			//性别
			ws.addCell(new Label(3,2,xb,ws.getCell(3, 2).getCellFormat()));
			//出生日期
			ws.addCell(new Label(5,2,map.get("csrq"),ws.getCell(5, 2).getCellFormat()));
			//年级
			ws.addCell(new Label(1,3,map.get("nj"),ws.getCell(1, 3).getCellFormat()));
			//学院名称
			ws.addCell(new Label(3,3,map.get("xymc"),ws.getCell(3, 3).getCellFormat()));
			//专业名称
			ws.addCell(new Label(5,3,map.get("zymc"),ws.getCell(5, 3).getCellFormat()));
			//插入照片
			XtwhZpglService service = new XtwhZpglService();
			File file = service.getPhotoFile(map.get("xh"));
			if(file==null){}else{
			WritableImage wi = new WritableImage(6, 2, 1, 4, file);//（x,y,x合并几个，y合并几个，获取的照片是个File类型的文件）
			ws.addImage(wi);
			}
			String ct="";
			if( map.get("特长")==null || map.get("特长").equals("null")){
				ct="";
			}else{
				ct=map.get("特长");
			}
			String zysj="";
			if(map.get("主要事迹")==null || map.get("主要事迹").equals("null")){
				zysj="";
			}else{
				zysj=map.get("主要事迹");
			}
			String xskjcg="";
			if(map.get("学术科技成果")==null || map.get("学术科技成果").equals("null")){
				xskjcg="";
			}else{
				xskjcg=map.get("学术科技成果");
			}
			String xnzcjpm="";
			if(map.get("学年总成绩在年级或专业排序位置")==null || map.get("学年总成绩在年级或专业排序位置").equalsIgnoreCase("null")){
				xnzcjpm="";
			}else{
				xnzcjpm=map.get("学年总成绩在年级或专业排序位置");
			}
			String xnzhcppm="";
			if(map.get("学年综合测评在年级或专业排序位置")==null || map.get("学年综合测评在年级或专业排序位置").equalsIgnoreCase("null")){
				xnzhcppm="";
			}else{
				xnzhcppm=map.get("学年综合测评在年级或专业排序位置");
			}
			//社会职务
			ws.addCell(new Label(1,4,map.get("xszw"),ws.getCell(1, 4).getCellFormat()));
			//特长
			ws.addCell(new Label(3,4,ct,ws.getCell(3, 4).getCellFormat()));
			//政治面貌
			ws.addCell(new Label(5,4,map.get("zzmmmc"),ws.getCell(5, 4).getCellFormat()));
			//家庭住址
			ws.addCell(new Label(1,5,map.get("jtdz"),ws.getCell(1, 5).getCellFormat()));
			//家庭邮编
			ws.addCell(new Label(5,5,map.get("jtyb"),ws.getCell(5, 5).getCellFormat()));
			//主要事迹
			ws.addCell(new Label(1,6,zysj,ws.getCell(1, 6).getCellFormat()));
			//学术科技成果
			ws.addCell(new Label(1,9,xskjcg,ws.getCell(1, 9).getCellFormat()));
			
			//学年总成绩在年级或专业排序位置
			ws.addCell(new Label(4,7,xnzcjpm,ws.getCell(4, 7).getCellFormat()));
			//学年综合测评在年级或专业排序位置
			ws.addCell(new Label(4,8,xnzhcppm,ws.getCell(4, 8).getCellFormat()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 纺织之光奖学金
	 * @param wwb
	 * @param map
	 */
	public void printFzzgjxj(WritableWorkbook wwb, Map<String,String> map) {
		try {
			
			WritableSheet ws = wwb.getSheet(0);
			
			
			XtwhZpglService service = new XtwhZpglService();
			File file = service.getPhotoFile(map.get("xh"));
			//插入照片
			if(file==null){}else{
			WritableImage wi = new WritableImage(7, 15, 1, 3, file);
			ws.addImage(wi);
		}
			//学校名称
			ws.addCell(new Label(3,6,Base.xxmc,ws.getCell(3, 6).getCellFormat()));
			//姓名
			ws.addCell(new Label(3,7,map.get("xm"),ws.getCell(3,6).getCellFormat()));
			
			//姓名
			ws.addCell(new Label(2,15,map.get("xm"),ws.getCell(2, 15).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="男";
			}else if(map.get("xb").equals("2")){
				xb="女";
			}else if(map.get("xb").equals("男")){
				xb="男";
			}else if(map.get("xb").equals("女")){
				xb="女";
			}
			//性别
			ws.addCell(new Label(4,15,xb,ws.getCell(4, 15).getCellFormat()));
			//出生日期
			ws.addCell(new Label(6,15,map.get("csrq"),ws.getCell(6, 15).getCellFormat()));
			//入学日期
			ws.addCell(new Label(6,16,map.get("rxrq"),ws.getCell(4, 15).getCellFormat()));
			//民族
			ws.addCell(new Label(2,16,map.get("mzmc"),ws.getCell(2, 15).getCellFormat()));
			//政治面貌
			ws.addCell(new Label(4,16,map.get("zzmmmc"),ws.getCell(4, 16).getCellFormat()));
			//学院
			ws.addCell(new Label(2,17,map.get("xymc"),ws.getCell(2, 17).getCellFormat()));
			//专业
			ws.addCell(new Label(5,17,map.get("zymc"),ws.getCell(5, 17).getCellFormat()));
			
			if(!Base.isNull(map.get("担任社会工作"))){
				ws.addCell(new Label(2,18,map.get("担任社会工作"),ws.getCell(2, 18).getCellFormat()));
			}
			
			if(!Base.isNull(map.get("何时何地受何表彰"))){
				ws.addCell(new Label(2,21,map.get("何时何地受何表彰"),ws.getCell(2, 21).getCellFormat()));
			}
			
			if(!Base.isNull(map.get("个人事迹"))){
				ws.addCell(new Label(0,27,map.get("个人事迹"),ws.getCell(0, 27).getCellFormat()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 帝棉针织奖学金申请表打印
	 * @param wwb
	 * @param map
	 */
	public void printDmzzjxj(WritableWorkbook wwb, Map<String,String> map) {
		try {
			WritableSheet ws = wwb.getSheet(0);
			String zypm="";
			if(map.get("专业名次")==null || map.get("专业名次").equals("null")){
				zypm="";
			}else{
				zypm=map.get("专业名次");
			}
			String sbdj="";
			if(map.get("申报等级")==null || map.get("申报等级").equals("null")){
				sbdj="";
			}else{
				sbdj=map.get("申报等级");
			}
			String dxhjqk="";
			if(map.get("在大学期间获奖情况")==null || map.get("在大学期间获奖情况").equals("null")){
				dxhjqk="";
			}else{
				dxhjqk=map.get("在大学期间获奖情况");
			}
			String shhdjl="";
			if(map.get("主要社会活动经历")==null || map.get("主要社会活动经历").equals("null")){
				shhdjl="";
			}else{
				shhdjl=map.get("主要社会活动经历");
			}
			String xb="";
			if(map.get("xb").equals("1")){
				xb="男";
			}else if(map.get("xb").equals("2")){
				xb="女";
			}else if(map.get("xb").equals("男")){
				xb="男";
			}else if(map.get("xb").equals("女")){
				xb="女";
			}
			//姓名
			ws.addCell(new Label(1,3,map.get("xm"),ws.getCell(1, 3).getCellFormat()));
			//性别
			ws.addCell(new Label(3,3,xb,ws.getCell(3, 3).getCellFormat()));
			//政治面貌
			ws.addCell(new Label(5,3,map.get("zzmmmc"),ws.getCell(5, 3).getCellFormat()));
			//专业名次
			ws.addCell(new Label(7,3,zypm,ws.getCell(7, 3).getCellFormat()));
			//学号
			ws.addCell(new Label(1,5,map.get("xh"),ws.getCell(1, 5).getCellFormat()));
			//民族
			ws.addCell(new Label(3,5,map.get("mzmc"),ws.getCell(3, 5).getCellFormat()));
			//出生年月
			ws.addCell(new Label(5,5,map.get("csrq"),ws.getCell(5, 5).getCellFormat()));
			//现任职务
			ws.addCell(new Label(7,5,map.get("xszw"),ws.getCell(7, 5).getCellFormat()));
			//专业
			ws.addCell(new Label(1,7,map.get("zymc")+map.get("bjmc"),ws.getCell(1, 7).getCellFormat()));
			//联系电话
			ws.addCell(new Label(5,7,map.get("lxdh"),ws.getCell(5, 7).getCellFormat()));
			//家庭地址
			ws.addCell(new Label(1,9,map.get("jtdz"),ws.getCell(1, 9).getCellFormat()));
			//邮编
			ws.addCell(new Label(7,9,map.get("jtyb"),ws.getCell(7, 9).getCellFormat()));
			//申报等级
			ws.addCell(new Label(9,9,sbdj,ws.getCell(9, 9).getCellFormat()));
			//在大学期间获奖情况
			ws.addCell(new Label(1,14,dxhjqk,ws.getCell(1, 14).getCellFormat()));
			//主要社会活动经历
			ws.addCell(new Label(1,16,shhdjl,ws.getCell(1, 16).getCellFormat()));
			
			//插入照片
			XtwhZpglService service = new XtwhZpglService();
			File file = service.getPhotoFile(map.get("xh"));
			if(file==null){
			}else{
				WritableImage wi = new WritableImage(8, 3, 2, 6, file);//（x,y,x合并几个，y合并几个，获取的照片是个File类型的文件）
				ws.addImage(wi);
			}
			//班级辅导员意见
			String bjfdyyj = "";
			if(map.get("班级辅导员意见")==null || map.get("班级辅导员意见").equals("null")){
				bjfdyyj = "";
			}else{
				bjfdyyj = map.get("班级辅导员意见");
			}
			ws.addCell(new Label(1,18,bjfdyyj,ws.getCell(1, 18).getCellFormat()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * 金百合奖学金申请表打印
	 * @param wwb
	 * @param map
	 */
	public void printJbhjxj(WritableWorkbook wwb, Map<String,String> map) {
		try {
			WritableSheet ws = wwb.getSheet(0);
			String zypm="";
			if(map.get("专业名次")==null || map.get("专业名次").equals("null")){
				zypm="";
			}else{
				zypm=map.get("专业名次");
			}
			String sbdj="";
			if(map.get("申报等级")==null || map.get("申报等级").equals("null")){
				sbdj="";
			}else{
				sbdj=map.get("申报等级");
			}
			String dxhjqk="";
			if(map.get("在大学期间获奖情况")==null || map.get("在大学期间获奖情况").equals("null")){
				dxhjqk="";
			}else{
				dxhjqk=map.get("在大学期间获奖情况");
			}
			String shhdjl="";
			if(map.get("主要社会活动经历")==null || map.get("主要社会活动经历").equals("null")){
				shhdjl="";
			}else{
				shhdjl=map.get("主要社会活动经历");
			}
			//姓名
			ws.addCell(new Label(1,3,map.get("xm"),ws.getCell(1, 3).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="男";
			}else if(map.get("xb").equals("2")){
				xb="女";
			}else if(map.get("xb").equals("男")){
				xb="男";
			}else if(map.get("xb").equals("女")){
				xb="女";
			}
			//性别
			ws.addCell(new Label(3,3,xb,ws.getCell(3, 3).getCellFormat()));
			//政治面貌
			ws.addCell(new Label(5,3,map.get("zzmmmc"),ws.getCell(5, 3).getCellFormat()));
			//专业名次
			ws.addCell(new Label(7,3,zypm,ws.getCell(7, 3).getCellFormat()));
			//学号
			ws.addCell(new Label(1,5,map.get("xh"),ws.getCell(1, 5).getCellFormat()));
			//民族
			ws.addCell(new Label(3,5,map.get("mzmc"),ws.getCell(3, 5).getCellFormat()));
			//出生年月
			ws.addCell(new Label(5,5,map.get("csrq"),ws.getCell(5, 5).getCellFormat()));
			//现任职务
			ws.addCell(new Label(7,5,map.get("xszw"),ws.getCell(7, 5).getCellFormat()));
			//专业
			ws.addCell(new Label(1,7,map.get("zymc")+map.get("bjmc"),ws.getCell(1, 7).getCellFormat()));
			//联系电话
			ws.addCell(new Label(5,7,map.get("lxdh"),ws.getCell(5, 7).getCellFormat()));
			//家庭地址
			ws.addCell(new Label(1,9,map.get("jtdz"),ws.getCell(1, 9).getCellFormat()));
			//邮编
			ws.addCell(new Label(7,9,map.get("jtyb"),ws.getCell(7, 9).getCellFormat()));
			//申报等级
			ws.addCell(new Label(9,9,sbdj,ws.getCell(9, 9).getCellFormat()));
			//在大学期间获奖情况
			ws.addCell(new Label(1,14,dxhjqk,ws.getCell(1, 14).getCellFormat()));
			//主要社会活动经历
			ws.addCell(new Label(1,16,shhdjl,ws.getCell(1, 16).getCellFormat()));
			//审核意见
			ws.addCell(new Label(1,18,map.get("shyj1"),ws.getCell(1, 18).getCellFormat()));
			//插入照片
			XtwhZpglService service = new XtwhZpglService();
			File file = service.getPhotoFile(map.get("xh"));
			if(file==null){
			}else{
				WritableImage wi = new WritableImage(8, 3, 2, 6, file);//（x,y,x合并几个，y合并几个，获取的照片是个File类型的文件）
				ws.addImage(wi);
			}
			//班级辅导员意见
			String bjfdyyj = "";
			if(map.get("班级辅导员意见")==null || map.get("班级辅导员意见").equals("null")){
				bjfdyyj = "";
			}else{
				bjfdyyj = map.get("班级辅导员意见");
			}
			ws.addCell(new Label(1,18,bjfdyyj,ws.getCell(1, 18).getCellFormat()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * 上纬奖学金申请表打印
	 * @param wwb
	 * @param map
	 */
	public void printSwjxj(WritableWorkbook wwb, Map<String,String> map) {
		try {
			WritableSheet ws = wwb.getSheet(0);
			String zypm="";
			if(map.get("专业名次")==null || map.get("专业名次").equals("null")){
				zypm="";
			}else{
				zypm=map.get("专业名次");
			}
			String sbdj="";
			if(map.get("申报等级")==null || map.get("申报等级").equals("null")){
				sbdj="";
			}else{
				sbdj=map.get("申报等级");
			}
			String dxhjqk="";
			if(map.get("在大学期间获奖情况")==null || map.get("在大学期间获奖情况").equals("null")){
				dxhjqk="";
			}else{
				dxhjqk=map.get("在大学期间获奖情况");
			}
			String shhdjl="";
			if(map.get("主要社会活动经历")==null || map.get("主要社会活动经历").equals("null")){
				shhdjl="";
			}else{
				shhdjl=map.get("主要社会活动经历");
			}
			//姓名
			ws.addCell(new Label(1,3,map.get("xm"),ws.getCell(1, 3).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="男";
			}else if(map.get("xb").equals("2")){
				xb="女";
			}else if(map.get("xb").equals("男")){
				xb="男";
			}else if(map.get("xb").equals("女")){
				xb="女";
			}
			//性别
			ws.addCell(new Label(3,3,xb,ws.getCell(3, 3).getCellFormat()));
			//政治面貌
			ws.addCell(new Label(5,3,map.get("zzmmmc"),ws.getCell(5, 3).getCellFormat()));
			//专业名次
			ws.addCell(new Label(7,3,zypm,ws.getCell(7, 3).getCellFormat()));
			//学号
			ws.addCell(new Label(1,5,map.get("xh"),ws.getCell(1, 5).getCellFormat()));
			//民族
			ws.addCell(new Label(3,5,map.get("mzmc"),ws.getCell(3, 5).getCellFormat()));
			//出生年月
			ws.addCell(new Label(5,5,map.get("csrq"),ws.getCell(5, 5).getCellFormat()));
			//现任职务
			ws.addCell(new Label(7,5,map.get("xszw"),ws.getCell(7, 5).getCellFormat()));
			//专业
			ws.addCell(new Label(1,7,map.get("zymc")+map.get("bjmc"),ws.getCell(1, 7).getCellFormat()));
			//联系电话
			ws.addCell(new Label(5,7,map.get("lxdh"),ws.getCell(5, 7).getCellFormat()));
			//家庭地址
			ws.addCell(new Label(1,9,map.get("jtdz"),ws.getCell(1, 9).getCellFormat()));
			//邮编
			ws.addCell(new Label(7,9,map.get("jtyb"),ws.getCell(7, 9).getCellFormat()));
			//申报等级
			ws.addCell(new Label(9,9,sbdj,ws.getCell(9, 9).getCellFormat()));
			//在大学期间获奖情况
			ws.addCell(new Label(1,14,dxhjqk,ws.getCell(1, 14).getCellFormat()));
			//主要社会活动经历
			ws.addCell(new Label(1,16,shhdjl,ws.getCell(1, 16).getCellFormat()));
			//审核意见
			ws.addCell(new Label(1,18,map.get("shyj1"),ws.getCell(1, 18).getCellFormat()));
			//插入照片
			XtwhZpglService service = new XtwhZpglService();
			File file = service.getPhotoFile(map.get("xh"));
			if(file==null){
			}else{
				WritableImage wi = new WritableImage(8, 3, 2, 6, file);//（x,y,x合并几个，y合并几个，获取的照片是个File类型的文件）
				ws.addImage(wi);
			}
			//班级辅导员意见
			String bjfdyyj = "";
			if(map.get("班级辅导员意见")==null || map.get("班级辅导员意见").equals("null")){
				bjfdyyj = "";
			}else{
				bjfdyyj = map.get("班级辅导员意见");
			}
			ws.addCell(new Label(1,18,bjfdyyj,ws.getCell(1, 18).getCellFormat()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * 天然奖学金申请表打印
	 * @param wwb
	 * @param map
	 */
	public void printTrjxj(WritableWorkbook wwb, Map<String,String> map) {

		WritableSheet ws = wwb.getSheet(0);
		
		try {
			//学校名称
			ws.addCell(new Label(3,4,Base.xxmc,ws.getCell(3, 4).getCellFormat()));
			//专业名称
			ws.addCell(new Label(3,5,map.get("zymc"),ws.getCell(3, 5).getCellFormat()));
			//班级名称
			ws.addCell(new Label(3,6,map.get("bjmc"),ws.getCell(3, 6).getCellFormat()));
			//封面上的姓名
			ws.addCell(new Label(3,7,map.get("xm"),ws.getCell(3, 7).getCellFormat()));
			//表格上的姓名
			ws.addCell(new Label(1,12,map.get("xm"),ws.getCell(1, 12).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="男";
			}else if(map.get("xb").equals("2")){
				xb="女";
			}else if(map.get("xb").equals("男")){
				xb="男";
			}else if(map.get("xb").equals("女")){
				xb="女";
			}
			//性别
			ws.addCell(new Label(3,12,xb,ws.getCell(3, 12).getCellFormat()));
			//民族名称
			ws.addCell(new Label(5,12,map.get("mzmc"),ws.getCell(5, 12).getCellFormat()));
			//出生日期
			ws.addCell(new Label(7,12,map.get("csrq"),ws.getCell(7, 12).getCellFormat()));
			
			File file = new XtwhZpglService().getPhotoFile(map.get("xh"));
			//照片
			if(file==null){
			}else{
				WritableImage wi=new WritableImage(8,12,1,7,file);   
				ws.addImage(wi);
			}
			//政治面貌
			ws.addCell(new Label(1,13,map.get("zzmmmc"),ws.getCell(1, 13).getCellFormat()));
			//学历
			ws.addCell(new Label(3,13,map.get("xlmc"),ws.getCell(3, 13).getCellFormat()));
			//专业
			ws.addCell(new Label(6,13,map.get("zymc"),ws.getCell(6, 13).getCellFormat()));
			//担任职务
			ws.addCell(new Label(1,15,map.get("xszw"),ws.getCell(1, 15).getCellFormat()));
			//专业排名
			String zypm="";
			if(map.get("专业排名")==null || map.get("专业排名").equals("null")){
				zypm="";
			}else{
				zypm=map.get("专业排名");
			}
			ws.addCell(new Label(3,15,zypm,ws.getCell(3, 15).getCellFormat()));
			//申请等级
			String sqdj="";
			if(map.get("申请等级")==null || map.get("申请等级").equals("null")){
				sqdj="";
			}else{
				sqdj=map.get("申请等级");
			}
			ws.addCell(new Label(6,15,sqdj,ws.getCell(6, 15).getCellFormat()));
			//家庭地址
			ws.addCell(new Label(1,17,map.get("jtdz"),ws.getCell(1, 17).getCellFormat()));
			//大学期间获奖情况
			String hjqk="";
			if(map.get("获奖情况")==null || map.get("获奖情况").equals("null")){
				hjqk="";
			}else{
				hjqk=map.get("获奖情况");
			}
			ws.addCell(new Label(1,19,hjqk,ws.getCell(1, 19).getCellFormat()));
			//个人主要事迹
			String grzysj="";
			if(map.get("个人主要事迹")==null || map.get("个人主要事迹").equals("null")){
				grzysj="";
			}else{
				grzysj=map.get("个人主要事迹");
			}
			ws.addCell(new Label(0,21,grzysj,ws.getCell(0, 21).getCellFormat()));
			//审核意见
			ws.addCell(new Label(1,28,map.get("shyj1"),ws.getCell(1, 28).getCellFormat()));
			//班级辅导员意见
			String bjfdyyj = "";
			if(map.get("班级辅导员意见")==null || map.get("班级辅导员意见").equals("null")){
				bjfdyyj = "";
			}else{
				bjfdyyj = map.get("班级辅导员意见");
			}
			ws.addCell(new Label(1,28,bjfdyyj,ws.getCell(1, 28).getCellFormat()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * 福田实业奖学金申请表打印
	 * @param wwb
	 * @param map
	 */
	public void printFtsyjxj(WritableWorkbook wwb, Map<String,String> map) {

		WritableSheet ws = wwb.getSheet(0);
		
		try {
			//学校名称
			ws.addCell(new Label(3,4,Base.xxmc,ws.getCell(3, 4).getCellFormat()));
			//专业名称
			ws.addCell(new Label(3,5,map.get("zymc"),ws.getCell(3, 5).getCellFormat()));
			//班级名称
			ws.addCell(new Label(3,6,map.get("bjmc"),ws.getCell(3, 6).getCellFormat()));
			//封面上的姓名
			ws.addCell(new Label(3,7,map.get("xm"),ws.getCell(3, 7).getCellFormat()));
			//表格上的姓名
			ws.addCell(new Label(1,12,map.get("xm"),ws.getCell(1, 12).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="男";
			}else if(map.get("xb").equals("2")){
				xb="女";
			}else if(map.get("xb").equals("男")){
				xb="男";
			}else if(map.get("xb").equals("女")){
				xb="女";
			}
			//性别
			ws.addCell(new Label(3,12,xb,ws.getCell(3, 12).getCellFormat()));
			//民族名称
			ws.addCell(new Label(5,12,map.get("mzmc"),ws.getCell(5, 12).getCellFormat()));
			//出生日期
			ws.addCell(new Label(7,12,map.get("csrq"),ws.getCell(7, 12).getCellFormat()));
			
			File file = new XtwhZpglService().getPhotoFile(map.get("xh"));
			//照片
			if(file==null){
			}else{
			WritableImage wi=new WritableImage(8,12,1,4,file);   
			ws.addImage(wi);
			}
			//政治面貌
			ws.addCell(new Label(1,13,map.get("zzmmmc"),ws.getCell(1, 13).getCellFormat()));
			//担任职务
			ws.addCell(new Label(3,13,map.get("xszw"),ws.getCell(3, 13).getCellFormat()));
			//生源省市
			ws.addCell(new Label(5,13,map.get("syd"),ws.getCell(5, 13).getCellFormat()));
			//德育规程操行评定等级
			String dygccxpddj="";
			if(map.get("德育规程操行评定等级")==null || map.get("德育规程操行评定等级").equals("null")){
				dygccxpddj="";
			}else{
				dygccxpddj=map.get("德育规程操行评定等级");
			}
			ws.addCell(new Label(3,15,dygccxpddj,ws.getCell(3, 15).getCellFormat()));
			//专业名次
			String zymc="";
			if(map.get("专业名次")==null || map.get("专业名次").equals("null")){
				zymc="";
			}else{
				zymc=map.get("专业名次");
			}
			ws.addCell(new Label(6,15,zymc,ws.getCell(6, 15).getCellFormat()));
			//外语计算机过级情况
			String wyjsjgjqk="";
			if(map.get("外语计算机过级情况")==null || map.get("外语计算机过级情况").equals("null")){
				wyjsjgjqk="";
			}else{
				wyjsjgjqk=map.get("外语计算机过级情况");
			}
			ws.addCell(new Label(3,16,wyjsjgjqk,ws.getCell(3, 16).getCellFormat()));
			//特长
			String tc="";
			if(map.get("特长")==null || map.get("特长").equals("null")){
				tc="";
			}else{
				tc=map.get("特长");
			}
			ws.addCell(new Label(6,16,tc,ws.getCell(6, 16).getCellFormat()));
			//大学期间获奖情况
			String hjqk="";
			if(map.get("获奖情况")==null || map.get("获奖情况").equals("null")){
				hjqk="";
			}else{
				hjqk=map.get("获奖情况");
			}
			ws.addCell(new Label(1,17,hjqk,ws.getCell(1, 17).getCellFormat()));
			//申请理由
			ws.addCell(new Label(0,21,map.get("sqly"),ws.getCell(0, 21).getCellFormat()));
			//审核意见
			ws.addCell(new Label(1,28,map.get("shyj1"),ws.getCell(1, 28).getCellFormat()));
			//班级辅导员意见
			String bjfdyyj = "";
			if(map.get("班级辅导员意见")==null || map.get("班级辅导员意见").equals("null")){
				bjfdyyj = "";
			}else{
				bjfdyyj = map.get("班级辅导员意见");
			}
			ws.addCell(new Label(1,28,bjfdyyj,ws.getCell(1, 28).getCellFormat()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * 汇川技术奖学金申请表打印
	 * @param wwb
	 * @param map
	 */
	public void printHcjsjxj(WritableWorkbook wwb, Map<String,String> map) {

		WritableSheet ws = wwb.getSheet(0);
		
		try {
			//学校名称
			ws.addCell(new Label(3,4,Base.xxmc,ws.getCell(3, 4).getCellFormat()));
			//专业名称
			ws.addCell(new Label(3,5,map.get("zymc"),ws.getCell(3, 5).getCellFormat()));
			//班级名称
			ws.addCell(new Label(3,6,map.get("bjmc"),ws.getCell(3, 6).getCellFormat()));
			//封面上的姓名
			ws.addCell(new Label(3,7,map.get("xm"),ws.getCell(3, 7).getCellFormat()));
			//表格上的姓名
			ws.addCell(new Label(1,12,map.get("xm"),ws.getCell(1, 12).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="男";
			}else if(map.get("xb").equals("2")){
				xb="女";
			}else if(map.get("xb").equals("男")){
				xb="男";
			}else if(map.get("xb").equals("女")){
				xb="女";
			}
			//性别
			ws.addCell(new Label(3,12,xb,ws.getCell(3, 12).getCellFormat()));
			//民族名称
			ws.addCell(new Label(5,12,map.get("mzmc"),ws.getCell(5, 12).getCellFormat()));
			//出生日期
			ws.addCell(new Label(7,12,map.get("csrq"),ws.getCell(7, 12).getCellFormat()));
			
			File file = new XtwhZpglService().getPhotoFile(map.get("xh"));
			//照片
			if(file==null){
			}else{
			WritableImage wi=new WritableImage(8,12,1,3,file);   
			ws.addImage(wi);
			}
			//政治面貌
			ws.addCell(new Label(1,13,map.get("zzmmmc"),ws.getCell(1, 13).getCellFormat()));
			//担任职务
			ws.addCell(new Label(3,13,map.get("xszw"),ws.getCell(3, 13).getCellFormat()));
			//生源省市
			ws.addCell(new Label(5,13,map.get("syd"),ws.getCell(5, 13).getCellFormat()));
			//申报等级
			ws.addCell(new Label(2,15,map.get("sbdj")+"级",ws.getCell(2, 15).getCellFormat()));
			//专业名次
			String zymc="";
			if(map.get("专业名次")==null || map.get("专业名次").equals("null")){
				zymc="";
			}else{
				zymc=map.get("专业名次");
			}
			ws.addCell(new Label(5,15,zymc,ws.getCell(5, 15).getCellFormat()));
			//特长
			String tc="";
			if(map.get("特长")==null || map.get("特长").equals("null")){
				tc="";
			}else{
				tc=map.get("特长");
			}
			ws.addCell(new Label(8,15,tc,ws.getCell(8, 15).getCellFormat()));
			//第几次获得汇川技术奖学金
			String hcjsjxj="";
			if(map.get("第几次获得汇川技术奖学金")==null || map.get("第几次获得汇川技术奖学金").equals("null")){
				hcjsjxj="";
			}else{
				hcjsjxj=map.get("第几次获得汇川技术奖学金");
			}
			ws.addCell(new Label(7,16,hcjsjxj,ws.getCell(7, 16).getCellFormat()));
			//大学期间获奖情况
			String hjqk="";
			if(map.get("获奖情况")==null || map.get("获奖情况").equals("null")){
				hjqk="";
			}else{
				hjqk=map.get("获奖情况");
			}
			ws.addCell(new Label(1,17,hjqk,ws.getCell(1, 17).getCellFormat()));
			//申请理由
			ws.addCell(new Label(0,21,map.get("sqly"),ws.getCell(0, 21).getCellFormat()));
			//审核意见
			ws.addCell(new Label(1,28,map.get("shyj1"),ws.getCell(1, 28).getCellFormat()));
			//等奖
			ws.addCell(new Label(1,31,"    经审核，该同学各方面表现符合评奖条件，同意该生获“汇川技术奖学金” "+
					map.get("sbdj")+" 等奖。",ws.getCell(1, 31).getCellFormat()));
			ws.addCell(new Label(1,36,"    经审核，该同学各方面表现符合评奖条件，同意该生获“汇川技术奖学金” "+
					map.get("sbdj")+" 等奖。",ws.getCell(1, 36).getCellFormat()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * 乌斯特奖学金申请表打印
	 * @param wwb
	 * @param map
	 */
	public void printWstjxj(WritableWorkbook wwb, Map<String,String> map) {

		WritableSheet ws = wwb.getSheet(0);
		
		try {
			//学校名称
			ws.addCell(new Label(3,4,Base.xxmc,ws.getCell(3, 4).getCellFormat()));
			//专业名称
			ws.addCell(new Label(3,5,map.get("zymc"),ws.getCell(3, 5).getCellFormat()));
			//班级名称
			ws.addCell(new Label(3,6,map.get("bjmc"),ws.getCell(3, 6).getCellFormat()));
			//封面上的姓名
			ws.addCell(new Label(3,7,map.get("xm"),ws.getCell(3, 7).getCellFormat()));
			//表格上的姓名
			ws.addCell(new Label(1,12,map.get("xm"),ws.getCell(1, 12).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="男";
			}else if(map.get("xb").equals("2")){
				xb="女";
			}else if(map.get("xb").equals("男")){
				xb="男";
			}else if(map.get("xb").equals("女")){
				xb="女";
			}
			//性别
			ws.addCell(new Label(3,12,xb,ws.getCell(3, 12).getCellFormat()));
			//民族名称
			ws.addCell(new Label(5,12,map.get("mzmc"),ws.getCell(5, 12).getCellFormat()));
			//出生日期
			ws.addCell(new Label(7,12,map.get("csrq"),ws.getCell(7, 12).getCellFormat()));
			
			File file = new XtwhZpglService().getPhotoFile(map.get("xh"));
			//照片
			if(file==null){
			}else{
			WritableImage wi=new WritableImage(8,12,1,4,file);   
			ws.addImage(wi);
			}
			//政治面貌
			ws.addCell(new Label(1,13,map.get("zzmmmc"),ws.getCell(1, 13).getCellFormat()));
			//学历
			ws.addCell(new Label(3,13,map.get("xlmc"),ws.getCell(3, 13).getCellFormat()));
			//专业
			ws.addCell(new Label(6,13,map.get("zymc"),ws.getCell(6, 13).getCellFormat()));
			//担任职务
			ws.addCell(new Label(1,14,map.get("xszw"),ws.getCell(1, 14).getCellFormat()));
			//专业排名
			String zypm="";
			if(map.get("专业排名")==null || map.get("专业排名").equals("null")){
				zypm="";
			}else{
				zypm=map.get("专业排名");
			}
			ws.addCell(new Label(3,14,zypm,ws.getCell(3, 14).getCellFormat()));
			//申报等级
			ws.addCell(new Label(6,14,map.get("sbdj"),ws.getCell(6, 14).getCellFormat()));
			//家庭地址
			ws.addCell(new Label(1,15,map.get("jtdz"),ws.getCell(1, 15).getCellFormat()));
			//大学期间获奖情况
			String hjqk="";
			if(map.get("获奖情况")==null || map.get("获奖情况").equals("null")){
				hjqk="";
			}else{
				hjqk=map.get("获奖情况");
			}
			ws.addCell(new Label(1,16,hjqk,ws.getCell(1, 16).getCellFormat()));
			//个人主要事迹
			String grzysj="";
			if(map.get("个人主要事迹")==null || map.get("个人主要事迹").equals("null")){
				grzysj="";
			}else{
				grzysj=map.get("个人主要事迹");
			}
			ws.addCell(new Label(0,20,grzysj,ws.getCell(0, 20).getCellFormat()));
			//审核意见
			ws.addCell(new Label(1,27,map.get("shyj1"),ws.getCell(1, 27).getCellFormat()));
			//班级辅导员意见
			String bjfdyyj = "";
			if(map.get("班级辅导员意见")==null || map.get("班级辅导员意见").equals("null")){
				bjfdyyj = "";
			}else{
				bjfdyyj = map.get("班级辅导员意见");
			}
			ws.addCell(new Label(1,27,bjfdyyj,ws.getCell(1, 27).getCellFormat()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * 校级优秀学生打印
	 * @param wwb
	 * @param map
	 */
	public void printXjyxxs(WritableWorkbook wwb, Map<String, String> map) {
		WritableSheet ws = wwb.getSheet(0);
		
		try {
			//学院名称
			ws.addCell(new Label(3,2,map.get("xymc"),ws.getCell(3, 2).getCellFormat()));
			//所在班级
			ws.addCell(new Label(7,2,map.get("bjmc"),ws.getCell(7, 2).getCellFormat()));
			
			File file = new XtwhZpglService().getPhotoFile(map.get("xh"));
			//照片
			if(file==null){
			}else{
			WritableImage wi=new WritableImage(9,2,1,3,file);   
			ws.addImage(wi);
			}
			//姓名
			ws.addCell(new Label(3,3,map.get("xm"),ws.getCell(3, 3).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="男";
			}else if(map.get("xb").equals("2")){
				xb="女";
			}else if(map.get("xb").equals("男")){
				xb="男";
			}else if(map.get("xb").equals("女")){
				xb="女";
			}
			//性别
			ws.addCell(new Label(6,3,xb,ws.getCell(6, 3).getCellFormat()));
			//出生日期
			ws.addCell(new Label(8,3,map.get("csrq"),ws.getCell(8, 3).getCellFormat()));
			//政治面貌
			ws.addCell(new Label(3,4,map.get("zzmmmc"),ws.getCell(3, 4).getCellFormat()));
			//社会职务
			ws.addCell(new Label(5,4,map.get("xszw"),ws.getCell(5, 4).getCellFormat()));
			//联系方式
			ws.addCell(new Label(3,5,map.get("jtdh"),ws.getCell(3, 5).getCellFormat()));
			//特长
			String tc="";
			if(map.get("特长")==null || map.get("特长").equals("null")){
				tc="";
			}else{
				tc=map.get("特长");
			}
			ws.addCell(new Label(8,5,tc,ws.getCell(8, 5).getCellFormat()));
			//所获荣誉
			String shry="";
			if(map.get("所获荣誉")==null || map.get("所获荣誉").equals("null")){
				shry="";
			}else{
				shry=map.get("所获荣誉");
			}
			ws.addCell(new Label(2,6,shry,ws.getCell(2, 6).getCellFormat()));
			//学年总成绩在年级或专业排序位置
			String xnzcjpm="";
			if(map.get("学年总成绩在年级或专业排序位置")==null || map.get("学年总成绩在年级或专业排序位置").equals("null")){
				xnzcjpm="";
			}else{
				xnzcjpm=map.get("学年总成绩在年级或专业排序位置");
			}
			ws.addCell(new Label(5,7,xnzcjpm,ws.getCell(5, 7).getCellFormat()));
			//学年综合测评在年级或专业排序位置
			String xnzhcp="";
			if(map.get("学年综合测评在年级或专业排序位置")==null || map.get("学年综合测评在年级或专业排序位置").equals("null")){
				xnzhcp="";
			}else{
				xnzhcp=map.get("学年综合测评在年级或专业排序位置");
			}
			ws.addCell(new Label(5,8,xnzhcp,ws.getCell(5, 8).getCellFormat()));
			//主要事迹
			String zysj="";
			if(map.get("主要事迹")==null || map.get("主要事迹").equals("null")){
				zysj="";
			}else{
				zysj=map.get("主要事迹");
			}
			ws.addCell(new Label(2,9,zysj,ws.getCell(2, 9).getCellFormat()));
			//学院意见
			ws.addCell(new Label(2,10,map.get("xyyj"),ws.getCell(2, 10).getCellFormat()));
			//学校意见
			ws.addCell(new Label(7,10,map.get("xxyj"),ws.getCell(7, 10).getCellFormat()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * 优秀学生打印
	 * @param wwb
	 * @param map
	 */
	public void printYxxs(WritableWorkbook wwb, Map<String, String> map) {
		WritableSheet ws = wwb.getSheet(0);
		
		try {
			//学校名称
			ws.addCell(new Label(3,2,Base.xxmc,ws.getCell(3, 2).getCellFormat()));
			//系、专业、年级
			ws.addCell(new Label(8,2,map.get("xymc")+"/"+map.get("zymc")+"/"+map.get("nj"),ws.getCell(8, 2).getCellFormat()));
			
			File file = new XtwhZpglService().getPhotoFile(map.get("xh"));
			//照片
			if(file==null){
			}else{
			WritableImage wi=new WritableImage(10,2,1,3,file);   
			ws.addImage(wi);
			}
			//姓名
			ws.addCell(new Label(3,3,map.get("xm"),ws.getCell(3, 3).getCellFormat()));
			String xb="";
			if(map.get("xb").equals("1")){
				xb="男";
			}else if(map.get("xb").equals("2")){
				xb="女";
			}else if(map.get("xb").equals("男")){
				xb="男";
			}else if(map.get("xb").equals("女")){
				xb="女";
			}
			//性别
			ws.addCell(new Label(7,3,xb,ws.getCell(7, 3).getCellFormat()));
			//出生日期
			ws.addCell(new Label(9,3,map.get("csrq"),ws.getCell(9, 3).getCellFormat()));
			//政治面貌
			ws.addCell(new Label(3,4,map.get("zzmmmc"),ws.getCell(3, 4).getCellFormat()));
			//社会职务
			ws.addCell(new Label(6,4,map.get("xszw"),ws.getCell(6, 4).getCellFormat()));
			//特长
			String tc="";
			if(map.get("特长")==null || map.get("特长").equals("null")){
				tc="";
			}else{
				tc=map.get("特长");
			}
			ws.addCell(new Label(9,4,tc,ws.getCell(9, 4).getCellFormat()));
			//永久通讯处
			String yjtxc="";
			if(map.get("永久通讯处")==null || map.get("永久通讯处").equals("null")){
				yjtxc="";
			}else{
				yjtxc=map.get("永久通讯处");
			}
			ws.addCell(new Label(4,5,yjtxc,ws.getCell(4, 5).getCellFormat()));
			//主要事迹
			String zysj="";
			if(map.get("主要事迹")==null || map.get("主要事迹").equals("null")){
				zysj="";
			}else{
				zysj=map.get("主要事迹");
			}
			ws.addCell(new Label(2,6,zysj,ws.getCell(2, 6).getCellFormat()));
			//学年总成绩在年级或专业排序位置
			String xnzcjpm="";
			if(map.get("学年总成绩在年级或专业排序位置")==null || map.get("学年总成绩在年级或专业排序位置").equals("null")){
				xnzcjpm="";
			}else{
				xnzcjpm=map.get("学年总成绩在年级或专业排序位置");
			}
			ws.addCell(new Label(6,7,xnzcjpm,ws.getCell(6, 7).getCellFormat()));
			//学年综合测评在年级或专业排序位置
			String xnzhcppm="";
			if(map.get("学年综合测评在年级或专业排序位置")==null || map.get("学年综合测评在年级或专业排序位置").equals("null")){
				xnzhcppm="";
			}else{
				xnzhcppm=map.get("学年综合测评在年级或专业排序位置");
			}
			ws.addCell(new Label(6,8,xnzhcppm,ws.getCell(6, 8).getCellFormat()));
			//学术科技成果简述
			String xskjcg="";
			if(map.get("学术科技成果简述")==null || map.get("学术科技成果简述").equals("null")){
				xskjcg="";
			}else{
				xskjcg=map.get("学术科技成果简述");
			}
			ws.addCell(new Label(2,9,xskjcg,ws.getCell(2, 9).getCellFormat()));
			//学院意见
			ws.addCell(new Label(2,10,map.get("shyj1"),ws.getCell(2, 10).getCellFormat()));
			//市评委会意见
			ws.addCell(new Label(8,10,map.get("shyj2"),ws.getCell(8, 10).getCellFormat()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
}

