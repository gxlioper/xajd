package xsgzgl.dtjs.dtxxgl;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.util.MessageResources;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xsgzgl.dtjs.comm.DtjsCommService;

public class DtxxglService extends DtjsCommService{
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");

	private DtxxglDAO dao = new DtxxglDAO();
	
	/**
	 * 查询党团信息
	 * @param form
	 * @return
	 */
	public List<String[]> dtxxQuery(User user,DtxxglForm form,String type,HttpServletRequest request) throws Exception{
		
		/* 2013-08-24 zh 原先设计时管理页面和查询页面共用查询方法，不知道此处为何分离成调用两个查询方法
		 * 现修改回来，管理页面只查询出当前阶段数据，查询页面查询全部阶段数据 */
		/*if("manage".equalsIgnoreCase(type)){
			return dao.dtxxQuery(user,form,type,request);
		}else if("query".equalsIgnoreCase(type)){
			return dao.dtxxQuery2(user,form,type,request);
		}else{
			return null;
		}*/
		
		return dao.dtxxQuery(user,form,type,request);
	}
	/**
	学院查询统计
	 * 
	 */
	public List<String[]> xytjQuery(User user, DtxxglForm myForm, String type,
			HttpServletRequest request) throws Exception{
		// TODO 自动生成方法存根
		
		return dao.xytjQuery(user,myForm,type,request);
		
	}
	/**
	 * 批量删除学生党团信息
	 * @param pk
	 * @return
	 */
	public boolean dtxxDel(String[] pk){
		List<String[]> params = new ArrayList<String[]>();		
		for(String str : pk){
			params.add(new String[]{str});
		}
		return dao.dtxxDel(params);
	}
	
	/**
	 * 批量删除学生党团信息
	 * @param pk
	 * @return
	 */
	public boolean delDtxx(String[] pk){
		List<String[]> params = new ArrayList<String[]>();		
		for(String str : pk){
			params.add(new String[]{str});
		}
		return dao.delDtxx(params);
	}
	
	/**
	 * 获取基本设置中的党团建设阶段list
	 * */
	public List<HashMap<String,String>> getJdList() {
		return dao.getJdList();
	}
	
	/**
	 * 获取学生的党团建设阶段list
	 * */
	public List<HashMap<String,String>> getXsjdList(String xh){
		return dao.getXsjdList(xh);
	}
	/**
	 * 获取学生当前阶段的阶段其它信息
	 * */
	public HashMap<String,String> getXsjdOther(String xh){
		return dao.getXsjdOther(xh);
	}
	
	/**
	 * 获取学生当前阶段的阶段信息
	 * */
	public HashMap<String,String> getDqjd(String xh){
		return dao.getDqjd(xh);
	}
	
	/**
	 * 保存党团信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean saveDtxx(DtxxglForm model) throws Exception{
		return dao.saveDtxx(model);
	}
	
	/**
	 * 修改党团信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateDtxx(DtxxglForm model) throws Exception{
		return dao.updateDtxx(model);
	}
	
	/**
	 * 导入数据
	 * @param filePath
	 * @param request
	 * @return
	 */
	public String importData(HttpServletRequest request,HttpServletResponse response){
		return dao.importData(request,response);
	}
	
	/**
	 * 模板下载
	 * @param response
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	public String mbxz(HttpServletResponse response) throws Exception{
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		
		List<HashMap<String,String>> list=getJdList();
		//页签名称 start
		List<String> title=new ArrayList<String>();
		title.add("数据模板");
		title.add("阶段代码表");
		//页签名称 end
		//输出列列数 start
		List<Integer> ColumnNum=new ArrayList<Integer>();
		ColumnNum.add(2+list.size()*2);
		ColumnNum.add(2);
		//输出列列数 end
		//数据集合  start
		List<List<String[]>> dateList=new ArrayList<List<String[]>>();
		List<String[]> dateList1=new ArrayList<String[]>();
		List<String[]> dateList2=new ArrayList<String[]>();
		for(int i=0;i<list.size();i++){
			dateList2.add(new String[]{list.get(i).get("jddm"),list.get(i).get("jdmc")});
		}
		dateList.add(dateList1);
		dateList.add(dateList2);
		//数据集合 end
		//输出列名称集合 start
		List<String[]> colListCN=new ArrayList<String[]>();
		List<String> colListCN1=new ArrayList<String>();
		colListCN1.add("学号");
		colListCN1.add("当前阶段代码");
		
		for(int i=0;i<list.size();i++){
			colListCN1.add(list.get(i).get("jdmc")+"开始时间");
			colListCN1.add(list.get(i).get("jdmc")+"结束时间");
		}
		List<String> colListCN2=new ArrayList<String>();
		colListCN2.add("阶段代码");
		colListCN2.add("阶段名称");
		colListCN.add(colListCN1.toArray(new String[]{}));
		colListCN.add(colListCN2.toArray(new String[]{}));
		//输出列名称集合 end
		Excel2Oracle.exportDataMoreSheet(title,dateList,ColumnNum,colListCN, response.getOutputStream());
		return "";
	}
	
	/**
	 * 导出党团信息
	 * @param form
	 * @return
	 */
	public List<String[]> exportData(DtxxglForm form,String type,HttpServletResponse response,User user) throws Exception{
		if(type.equalsIgnoreCase("manage")){
			return dao.exportData(form,type,response,user);
		}else if(type.equalsIgnoreCase("query")){
			return dao.exportData2(form,type,response);
		}else
			return null;
		
	}
	
	/**
	 * 同步更新政治面貌
	 * @return
	 * @throws Exception 
	 */
	public boolean tbgxzzmm() throws Exception{
		return dao.tbgxzzmm();
	}
	
	/**
	 * 统计结果导出
	 * @param response
	 * @param tjlx
	 * @param jdArray
	 * @param tjList
	 * @param output
	 */
	public  void exportData(HttpServletResponse response, String tjlx,
			String[] jdArray, List tjList, String[] output,OutputStream os) throws Exception{
		// TODO 自动生成方法存根
		Date date=new Date();
		SimpleDateFormat formate=new SimpleDateFormat("yyyy.MM.dd");
		String currentDate=formate.format(date);
		WritableWorkbook wwb =ExcelMethods.getWritableWorkbook(response.getOutputStream());
		
		
		int cols=output.length;
		try{
			WritableSheet ws=wwb.createSheet("11", 0);
			WritableCellFormat wcFormate=new WritableCellFormat();
			
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;
			//格式
			wcFormate.setVerticalAlignment(vag);
			wcFormate.setAlignment(alignMent);
			//字体
			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(16);
			wcFormate.setFont(wfTytle);
			
			//标题
			ws.mergeCells(0, 0,cols-1, 3);
			ws.addCell(new Label(0,0,"湖州师范学生党团情况统计表",wcFormate));
			ws.mergeCells(cols-4,4,cols-1,4);
			wcFormate = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;
			wcFormate.setVerticalAlignment(vag);
			wcFormate.setAlignment(alignMent);
			wcFormate.setBorder(Border.ALL, BorderLineStyle.THIN);
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcFormate.setFont(wfTytle);
			wcFormate = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcFormate.setVerticalAlignment(vag);
			wcFormate.setAlignment(alignMent);
			wcFormate.setWrap(true);
//			 设置表格边框
			wcFormate.setBorder(Border.ALL, BorderLineStyle.THIN);
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcFormate.setFont(wfTytle);
			ws.addCell(new Label(cols-4,4,"统计时间："+currentDate,wcFormate));
			ws.mergeCells(0, 5,0, 6);
			Base.YXPZXY_KEY = message.getMessage("lable.xb");
			ws.addCell(new Label(0,5,Base.YXPZXY_KEY+"名称",wcFormate));
			ws.mergeCells(1, 5,1, 6);
			ws.addCell(new Label(1,5,"学生总人数",wcFormate));
			//int tmp=0;
			for (int i = 0; i < jdArray.length; i++) {
				ws.mergeCells(2+2*i, 5,3+2*i, 5);
				ws.addCell(new Label(2+2*i,5,jdArray[i],wcFormate));
				ws.addCell(new Label(2+2*i,6,"人数",wcFormate));
				ws.addCell(new Label(3+2*i,6,"比例",wcFormate));
			}
			for (int i = 0; i < tjList.size(); i++) {
				String[] str=(String[]) tjList.get(i);
				for (int j = 0; j < str.length; j++) {
					ws.addCell(new Label(j,i+7,str[j],wcFormate));
				}
				//tmp=i+5;
			}
//			ws.addCell(new Label(0,tmp+1,"统计时间",wcFormate));
//			ws.mergeCells(1, tmp+1,3, tmp+1);
//			ws.addCell(new Label(1,tmp+1,currentDate,wcFormate));

			
			
			
		}catch(Exception e){
			
		}finally{
			wwb.write();
			wwb.close();
		}
		
	}

	/**
	 * 自定义导出
	 * @param form
	 * @param type
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> dtxxglExportData(DtxxglForm form,String type,User user) throws Exception{
		if(type.equalsIgnoreCase("manage")){
			return dao.xsxxglExportData(form);
		}else if(type.equalsIgnoreCase("query")){
			return dao.xsdtxxcxExportData(form,user);
		}else
			return null;
		
	}
}
