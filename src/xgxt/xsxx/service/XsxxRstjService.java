package xgxt.xsxx.service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Cell;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.ExcelMB;
import xgxt.utils.ExcelMethods;
import xgxt.xsxx.dao.XsxxRstjDAO;
import xgxt.xsxx.form.XsxxRstjForm;

/**
 * 
* 
* 类名称：XsxxRstjService 
* 类描述： 学生信息人数统计Service 
* 创建人：yijd 
* 创建时间：2012-7-05 上午09:20:00 
* 修改备注：  
* @version 
*
 */
public class XsxxRstjService {
	XsxxRstjDAO dao=new XsxxRstjDAO();
	
	/**
	 * 查询NJ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cxXsxxRstj() throws Exception{
		String[] nj=dao.cxXsxxNj();
		return dao.cxRstjXyzynj(nj);
	}
	/**
	 * 查询层次性别民族学生人数统计信息
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> ccxbmzXsxxRstj(String[] cc,String[] xb,String[] mz) throws Exception {
		return dao.ccxbmzXsxxRstj(cc,xb,mz);
	}
	
	/**
	 * 查询学生信息年级
	 * @return
	 * @throws Exception
	 */
	public String[] cxXsxxNj() throws Exception{
		//String
		return dao.cxXsxxNj();
	}
	/**
	 * yeyp   校区学院年级
	 * @param model
	 * @param os
	 * @throws Exception
	 */
	public void xqxynj(XsxxRstjForm model, OutputStream os) throws Exception {
		// 创建excel对象
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("学生人数统计", 0);
		
		String[] njs=dao.cxXsxxNj();
		String[] xqs=dao.cxXq();
		List<HashMap<String, String>> rsList=dao.cxRstjXqxynj(njs, xqs);
		
		int xygs=rsList.size();//学院个数
		int njgs=njs.length;
		try{
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			// 标题
			ws.mergeCells(0, 0, 0, 2); 
			ws.setRowView(0, 400); // 设置指定行高
			ws.addCell(new Label(0, 0,"学院", wcfTytle));
			
			int ejlen=njgs+1;//二级标题
			for (int i = 0; i < xqs.length; i++) {
				ws.mergeCells((i*ejlen+1), 0, ((i+1)*ejlen), 0); 
				ws.setRowView(0, 400); // 设置指定行高
				ws.addCell(new Label((i*ejlen+1), 0,xqs[i], wcfTytle));
				//年级
				ws.mergeCells((i*(ejlen)+1), 1, ((i+1)*(ejlen)-1), 1); 
				ws.setRowView(0, 400); // 设置指定行高
				ws.addCell(new Label((i*ejlen+1), 1,"年级", wcfTytle));
				
				//年级 合计
				ws.mergeCells((i+1)*(ejlen), 1, (i+1)*(ejlen), 2); 
				ws.setRowView(0, 400); // 设置指定行高
				ws.addCell(new Label((i+1)*(ejlen), 1,"合计", wcfTytle));
				
				for (int j = 0; j < njs.length; j++) {
					//年级号
					ws.addCell(new Label((i*ejlen+1+j), 2,njs[j], wcfTytle));
				}
			}
			
			for (int i = 0; i < rsList.size(); i++) {
				//学院名称
				ws.addCell(new Label(0, 3+i,rsList.get(i).get("xymc"), wcfTytle));
				for (int j = 0; j < xqs.length; j++) {
					for (int z = 0; z < njs.length; z++) {
						//年级得分
						ws.addCell(new Label(njgs*j+z+1+j, 3+i,rsList.get(i).get("xq_"+(j+1)+"_"+(z+1)), wcfTytle));
					}
					//年级合计
					ws.addCell(new Label(njgs*(j+1)+1+j, 3+i,rsList.get(i).get("xq_"+(j+1)), wcfTytle));
				}
			}
			//预计今年毕业任务
			ws.addCell(new Label(0, xygs+3,"预计今年毕业人数", wcfTytle));
			for (int i = 0; i < xqs.length; i++) {
				ws.mergeCells((njgs+1)*i+1, xygs+3, (njgs+1)*(i+1), xygs+3); 
				ws.setRowView(0, 400); // 设置指定行高
				ws.addCell(new Label((njgs+1)*(i)+1, xygs+3,"", wcfTytle));
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		//向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * yeyp   省市层次性别民族
	 * @param model
	 * @param os
	 * @throws Exception
	 */
	public void ssccxbmc(XsxxRstjForm model, OutputStream os) throws Exception {
		// 创建excel对象
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("学生人数统计", 0);
		
		
		//获得层次，性别，民族基础数据
		String [] ccmc= dao.cxXsxxCCmc();
		String [] cc= dao.cxXsxxCC();
		String [] xb= dao.cxXsxxXb();
		String [] mz= dao.cxXsxxMz();
		// 获取学生统计人数数据
		List<HashMap<String, String>> rsList=ccxbmzXsxxRstj(cc,xb,mz);
		//获得表头
		String [] columns = dao.uniteArrays(new String[]{"总计"},ccmc,new String[]{"总计"},xb,new String[]{"总计"},mz);

		
		try{
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			ws.addCell(new Label(0, 0,"项目", wcfTytle));
			ws.mergeCells(0, 0, 1, 1);
			ws.addCell(new Label(2, 0,"层次", wcfTytle));
			ws.mergeCells(2, 0, cc.length+2, 0);
			ws.addCell(new Label(cc.length+3, 0, "性别", wcfTytle));
			ws.mergeCells(cc.length+3, 0, xb.length+cc.length+3, 0);
			ws.addCell(new Label(cc.length+xb.length+4,0,"民族", wcfTytle));
			ws.mergeCells(cc.length+xb.length+4,0, xb.length+cc.length+mz.length+4, 0);
			for (int i = 0; i < columns.length; i++) {
				ws.addCell(new Label(i+2,1, columns[i], wcfTytle));
			}
			ws.addCell(new Label(0,2,"生源地省市", wcfTytle));
			ws.mergeCells(0,2,0,rsList.size()+1);
			for(int i = 0;i < rsList.size(); i++){
				//生源地市
				ws.addCell(new Label(1,i+2, rsList.get(i).get("sydsmc"), wcfTytle));
				//层次
				ws.addCell(new Label(2,i+2,rsList.get(i).get("rscc"), wcfTytle));
				for(int j = 0; j < cc.length;j++){
					ws.addCell(new Label( 3+j,i+2,rsList.get(i).get("zd_"+(j+1)), wcfTytle));
				}
				//性别
				ws.addCell(new Label( 3+cc.length,i+2,rsList.get(i).get("rsxb"), wcfTytle));
				for(int j = cc.length; j < cc.length+xb.length;j++){
					ws.addCell(new Label( 4+j,i+2,rsList.get(i).get("zd_"+(j+1)), wcfTytle));
				}
				//民族
				ws.addCell(new Label( 4+cc.length+xb.length,i+2,rsList.get(i).get("rsmz"), wcfTytle));
				for(int j = cc.length+xb.length; j < cc.length+xb.length+mz.length;j++){
					ws.addCell(new Label(5+j,i+2, rsList.get(i).get("zd_"+(j+1)), wcfTytle));
				}
				
			}
			//合并学院列
		} catch(Exception e){
			e.printStackTrace();
		}
		//向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * yijd   学院专业年级
	 * @param model
	 * @param os
	 * @throws Exception
	 */
	public void xyzynj(XsxxRstjForm model, OutputStream os) throws Exception {
		// 创建excel对象
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("学生人数统计", 0);
		//设置单元个宽度
		ws.setColumnView(3, 35);
		// 获取学生统计人数数据
		List<HashMap<String, String>> rsList=cxXsxxRstj();
		String[] njs=cxXsxxNj();
		
		int rows=rsList.size();
		try{
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			WritableCellFormat leftTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			// 标题
			ws.mergeCells(0, 0, 3, 1); 
			ws.setRowView(0, 400); // 设置指定行高
			ws.addCell(new Label(0, 0,"项目", wcfTytle));
			
			ws.mergeCells(4, 0, (njs.length+4), 0); 
			ws.setRowView(0, 400); // 设置指定行高
			ws.addCell(new Label(4, 0,"年级", wcfTytle));
			ws.addCell(new Label(4, 1,"总计", wcfTytle));
			
			for (int i = 0; i < njs.length; i++) {
				ws.addCell(new Label((i+5), 1,njs[i], wcfTytle));
			}
			for(int i=0;i<rsList.size();i++){
				ws.addCell(new Label(0, (i+2),"学院", wcfTytle));
				ws.addCell(new Label(1, (i+2),rsList.get(i).get("xymc"), wcfTytle));
				ws.addCell(new Label(2, (i+2),rsList.get(i).get("tjzy"), wcfTytle));
				ws.addCell(new Label(3, (i+2),rsList.get(i).get("zymc"), leftTytle));
				ws.addCell(new Label(4, (i+2),rsList.get(i).get("rs"), wcfTytle));
				for (int j = 0; j < njs.length; j++) {
					ws.addCell(new Label((j+5), (i+2),rsList.get(i).get("nj_"+(j+1)), wcfTytle));
				}
				
			}
			//合并学院列
			mergeCells(ws, "0", 0, 2, rows);
			//学院名称
			mergeCells(ws, "0", 1, 2, rows);
			//专业字样合并
			mergeCells(ws, "0", 2, 2, rows);
			//总计合并
			mergeCells(ws, "1", 1, 2, 3);
			//合计合并
			for (int i = 0; i < (rows-1); i++) {
				mergeCells(ws, "1", 2, (3+i), 2);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		//向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		
	}
	
	/**
	 * yijd   学院专业年级性别  列 行
	 * @param model
	 * @param os
	 * @throws Exception
	 */
	public void xyzynjxb(XsxxRstjForm model, OutputStream os) throws Exception {
		String[] nj=dao.cxXsxxNj();
		List<HashMap<String, String>> rsList= dao.cxRstjXqxynjxb(nj);
		
		// 创建excel对象
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("学生人数统计", 0);
		//设置单元个宽度
		ws.setColumnView(3, 35);
		// 获取学生统计人数数据
		String[] njs=cxXsxxNj();
		
		int rows=rsList.size();
		try{
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			WritableCellFormat leftTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			// 标题
			ws.mergeCells(0, 0, 3, 3); 
			ws.setRowView(0, 400); // 设置指定行高
			ws.addCell(new Label(0, 0,"项目", wcfTytle));
			
			ws.mergeCells(4, 0, ((njs.length*4)+4), 0); 
			ws.setRowView(0, 400); // 设置指定行高
			ws.addCell(new Label(4, 0,"年级", wcfTytle));
			
			ws.mergeCells(4, 1, 4, 3); 
			ws.setRowView(0, 400); // 设置指定行高
			ws.addCell(new Label(4, 1,"总计", wcfTytle));
			
			//ws.mergeCells(5, 1, 8, 1); 
			//ws.setRowView(0, 400); // 设置指定行高
			for (int i = 0; i < njs.length; i++) {
				ws.mergeCells(((i*4)+5), 1, (((i*4)+5)+3), 1); 
				ws.setRowView(0, 400); // 设置指定行高
				ws.addCell(new Label(((i*4)+5), 1,njs[i], wcfTytle));
				
				ws.mergeCells(((i*4)+5), 2, ((i*4)+5), 3); 
				ws.setRowView(0, 400); // 设置指定行高
				ws.addCell(new Label(((i*4)+5), 2,"合计", wcfTytle));
				
				ws.mergeCells(((i*4)+6), 2, ((i*4)+6)+2, 2); 
				ws.setRowView(0, 400); // 设置指定行高
				ws.addCell(new Label(((i*4)+6), 2,"性别", wcfTytle));
				
				ws.addCell(new Label(((i*4)+6), 3,"小计", wcfTytle));
				ws.addCell(new Label(((i*4)+7), 3,"男", wcfTytle));
				ws.addCell(new Label(((i*4)+8), 3,"女", wcfTytle));
			}
			for(int i=0;i<rsList.size();i++){
				ws.addCell(new Label(0, (i+4),"校区", wcfTytle));
				ws.addCell(new Label(1, (i+4),rsList.get(i).get("xqmc"), wcfTytle));
				ws.addCell(new Label(2, (i+4),rsList.get(i).get("tjxy"), wcfTytle));
				ws.addCell(new Label(3, (i+4),rsList.get(i).get("xymc"), leftTytle));
				ws.addCell(new Label(4, (i+4),rsList.get(i).get("rs"), wcfTytle));
				for (int j = 0; j < njs.length; j++) {
					ws.addCell(new Label(((j*4)+5), (i+4),rsList.get(i).get("nj_"+(j+1)), wcfTytle));
					ws.addCell(new Label(((j*4)+6), (i+4),rsList.get(i).get("nj_"+(j+1)), wcfTytle));
					ws.addCell(new Label(((j*4)+7), (i+4),rsList.get(i).get("nj_"+(j+1)+"_nan"), wcfTytle));
					ws.addCell(new Label(((j*4)+8), (i+4),rsList.get(i).get("nj_"+(j+1)+"_nv"), wcfTytle));
				}
				
			}
			//合并校区列
			mergeCells(ws, "0", 0, 4, rows);
			//学院字样合并
			mergeCells(ws, "0", 1, 4, rows);
			//学院名称合并
			mergeCells(ws, "0", 2, 4, rows);
			//总计合并
			mergeCells(ws, "1", 1, 4, 3);
			//合计合并
			for (int i = 0; i < (rows-1); i++) {
				mergeCells(ws, "1", 2, (5+i), 2);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		//向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		
	}
	
	/**
	 * 工具类  合并相邻的相同内容的 单元格
	 * @param sheet		
	 * @param type  0,列合并     1,行合并
	 * @param beginX	合并单元个开始坐标X
	 * @param beginY	合并单元个开始坐标Y
	 * @param length 	合并单元格数量
	 */
	private void mergeCells(WritableSheet sheet,String type, int beginX,
			int beginY, int length) throws Exception{
		String val="";
		String nextVal="";
		List<int[]> list=new ArrayList<int[]>();
		int indexBX=beginX;
		int indexBY=beginY;
		int indexEX=beginX;
		int indexEY=beginY;
		int[] mergeData=null;
		Cell cell=null;
		if("0".equals(type)){
			for (int i = 1; i <= length; i++) {
				cell=sheet.getCell(indexEX, indexEY);
				if("".equals(val)){
					val=cell.getContents();
				}else{
					nextVal=cell.getContents();
				}
				
				if(!val.equals(nextVal)){
					if(!"".equals(nextVal)){
						val=nextVal;
						mergeData=new int[]{indexBX,indexBY,indexEX,(indexEY-1)};
						list.add(mergeData);
						mergeData=null;
						//初始化开始位置
						indexBY=indexEY;
						
					}
				}
				if(i==length){
					mergeData=new int[]{indexBX,indexBY,indexEX,indexEY};
					list.add(mergeData);
				}
				indexEY++;
			}
			
		}else if("1".equals(type)){
			for (int i = 1; i <= length; i++) {
				cell=sheet.getCell(indexEX, indexEY);
				if("".equals(val)){
					val=cell.getContents();
				}else{
					nextVal=cell.getContents();
				}
				
				if(!val.equals(nextVal)){
					if(!"".equals(nextVal)){
						val=nextVal;
						mergeData=new int[]{indexBX,indexBY,(indexEX-1),indexEY};
						list.add(mergeData);
						mergeData=null;
						//初始化开始位置
						indexBX=indexEX;
						
					}
				}
				if(i==length){
					mergeData=new int[]{indexBX,indexBY,indexEX,indexEY};
					list.add(mergeData);
				}
				indexEX++;
			}
		}else{
			
		}
		// 合并单元格
		for (int i = (list.size()-1); i >=0 ; i--) {
			if(list.get(i)[0] == list.get(i)[2] && list.get(i)[1] == list.get(i)[3]){
				continue;
			}
			sheet.mergeCells(list.get(i)[0], list.get(i)[1], list.get(i)[2], list.get(i)[3]);
		}
	}
	
}
