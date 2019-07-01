package xgxt.qgzx.service;

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

import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.qgzx.dao.QgzxXskhDAO;
import xgxt.qgzx.form.QgzxForm;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 勤工助学学生岗位管理Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 2.0</p>
 * <p>Time: 2009-11-30</p>
 */
public class QgzxXskhService {
	QgzxXskhDAO dao = new QgzxXskhDAO();
	
	/**
	 * 获取列的中文名称
	 * @param String[] colList
	 * @param String tableName
	 * @return String[]
	 * */
	public String[] getColumnNameCN(String[] colList,String tableName){
		return dao.getColumnNameCN(colList, tableName);
	}

	/**
	 * 获取学生工作记录信息查询表头信息
	 * @return List<HashMap<String,String>>
	 * */
	public List<HashMap<String,String>> getXsgzjlToptr(){
		String[] colList = {"主键","xh","xm","bjmc","gwdm","nd","yf","rq","zgzsj","lxdh"};
		String[] colListCN = dao.getColumnNameCN(colList, "view_qgzx_xsgzjlb");
		return dao.arrayToList(colList, colListCN);
	}
	
	/**
	 * 获取学生工作考核信息查询表头信息
	 * @return List<HashMap<String,String>>
	 * */
	public List<HashMap<String,String>> getXsgzkhToptr(){
		String[] colList = {"主键","xh","xm","bjmc","gwdm","nd","yf"};
		String[] colListCN = dao.getColumnNameCN(colList, "view_qgzx_xsgzkhb");
		return dao.arrayToList(colList, colListCN);
	}
		
	
	/**
	 * 查询学生工作记录信息
	 * @param QgzxForm model
	 * @return List<String[]>
	 * @throws Exception 
	 * */
	public List<String[]> queryXsgzjlxx(QgzxForm model) throws Exception{
		return dao.selectXsgzjl(model);
	}
	
	/**
	 * 查询学生工作记录信息导出
	 * @param QgzxForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * @throws Exception 
	 * */
	public List<String[]> queryXsgzjlxxForExport(QgzxForm model,String[] colList) throws Exception{
		return dao.selectXsgzjlForExport(model,colList);
	}
	
	/**
	 * 查询单条学生工作记录信息
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryXsgzjlxxOne(String pkValue){
		return dao.selectXsgzjlxxOne(pkValue);
	}
	
	/**
	 * 保存学生工作记录信息
	 * @param QgzxForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveXsgzjl(QgzxForm model,HttpServletRequest request) throws Exception{
		boolean result = false;
		String tableName = "view_qgzx_xsgzjlb";
		String pk = "xh||gwdm||gwsbsj||nd||yf||rq";
		String pkValue = DealString.toGBK(model.getXh())+DealString.toGBK(model.getGwdm()) + DealString.toGBK(model.getGwsbsj())+DealString.toGBK(model.getNd()) + DealString.toGBK(model.getYf()) + DealString.toGBK(model.getRq());
		
		
		String[] columns = new String[]{"gwdm","gwsbsj","gzjssj","gzkssj","gznr","nd","rq","xh","yf","zgzsj"};
		String[] values = FormModleCommon.modelToStrings(columns, model);
		if(dao.checkExists(tableName, pk,pkValue)){	
			//修改
			result = StandardOperation.update("qgzx_xsgzjlb", columns, values, pk, pkValue, request);
		}else{
			//增加
			result = StandardOperation.insert("qgzx_xsgzjlb", columns, values, request);
		}
		return result;
	}
	
	/**
	 * 批量插入学生工作记录信息
	 * @param QgzxForm model
	 * @param List<String[]> list
	 * @return boolean
	 * */
	public boolean saveXsgzjlBatch(QgzxForm model,List<String[]> list){
		return dao.insertXsgzjlBatch(model,list);		
	}
	
	/**
	 * 查询学生工作考核信息
	 * @param QgzxForm model
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgzkhxx(QgzxForm model){
		return dao.selectXsgzkhxx(model);
	}
	
	/**
	 * 查询学生工作考核信息
	 * @param QgzxForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgzkhxxForExport(QgzxForm model,String[] colList){
		return dao.selectXsgzkhxxForExport(model,colList);
	}
	
	
	/**
	 * 查询学生月工作记录信息
	 * @param String pkValue
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgzjlByYf(String pkValue){
		return dao.selectXsgzjlByYf(pkValue);
	}
	
	/**
	 * 查询学生月工作考核信息
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryXsgzkhxxOne(String pkValue){
		return dao.selectXsgzkhxxOne(pkValue);
	}
	
	/**
	 * 保存学生工作考核信息
	 * @param QgzxForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveXsgzkh(QgzxForm model,HttpServletRequest request) throws Exception{
		boolean result = false;
		String tableName = "view_qgzx_xsgzkhb";
		String pk = "xh||gwdm||gwsbsj||nd||yf";
		String pkValue = DealString.toGBK(model.getXh())+model.getGwdm()+model.getGwsbsj()+DealString.toGBK(model.getNd()) + DealString.toGBK(model.getYf());
		
		String[] columns = new String[]{"bz","ffcjje","gwdm","gwsbsj","nd","xh","yf","ygzsj","gzbx","gzpj"};
		String[] values = FormModleCommon.modelToStrings(columns, model);		
		if(dao.checkExists(tableName, pk,pkValue)){	
			//修改
			result = StandardOperation.update("qgzx_xsgzkhb", columns, values, pk, pkValue, request);
		}else{
			//增加
			result = StandardOperation.insert("qgzx_xsgzkhb", columns, values, request);
		}
		return result;
	}
	
	/**
	 * 查询学生申请的岗位信息
	 * @param String xh
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getGwList(String xh){
		return dao.selectGwList(xh);
	}
	
	/**
	 * 学生工作记录信息删除
	 * @param QgzxForm model
	 * @return boolean
	 * */
	public boolean delXsgzjl(QgzxForm model){
		return dao.deleteXsgzjl(model);
	}
	
	/**
	 * 打印学生工作考核表
	 * */
	public void printXskhb(WritableWorkbook wwb,String pkValue){
		//学生月考核信息
		HashMap<String, String> map = queryXsgzkhxxOne(pkValue);
		//学生月工作记录信息
		List<String[]> list = queryXsgzjlByYf(pkValue);
		String xxmc = StandardOperation.getXxmc();
		try{
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;
			 
			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			//wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
			 
			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);
			 
			ws.addCell(new Label(1,0,xxmc + "勤工助学考核表" ,wcfTytle));
			
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;
			 
			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			//wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);
			ws.addCell(new Label(8,2, map.get("nd") + "年" + map.get("yf") + "月",wcfTytle));
			
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;
			 
			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);			
			
			ws.addCell(new Label(3,3, map.get("xm"),wcfTytle));
			ws.addCell(new Label(5,3, map.get("xymc"),wcfTytle));
			ws.addCell(new Label(7,3, map.get("zymc"),wcfTytle));
			ws.addCell(new Label(7,4, map.get("lxdh"),wcfTytle));
			ws.addCell(new Label(3,5, map.get("gwdm"),wcfTytle));
			ws.addCell(new Label(9,3, map.get("kh"),wcfTytle));
			ws.addCell(new Label(3,4, map.get("ssbh"),wcfTytle));
			if(list.size()>0){
				wcfTytle = new WritableCellFormat();
				alignMent = Alignment.CENTRE;
				vag = VerticalAlignment.CENTRE;
				 
				wcfTytle.setVerticalAlignment(vag);
				wcfTytle.setAlignment(alignMent);
				wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
				
				wfTytle = new WritableFont(WritableFont.ARIAL);
				wfTytle.setPointSize(12);
				wcfTytle.setFont(wfTytle);
				
				//学生工作记录信息
				for(int i=0; i<list.size();i++){
					String[] val = list.get(i);
					if(val.length>0){
						String rq = StringUtils.isNull(val[0]) ? "0" : val[0];
						String gznr = StringUtils.isNull(val[4]) ? "" : val[4];
						String gzkssj = StringUtils.isNull(val[1]) ? "____" : val[1];
						String gzjssj = StringUtils.isNull(val[2]) ? "____" : val[2];
						if(Integer.parseInt(rq)>16){
							ws.addCell(new Label(7,Integer.parseInt(rq)-10, gznr,wcfTytle));
							ws.addCell(new Label(9,Integer.parseInt(rq)-10, gzkssj+"至"+gzjssj,wcfTytle));
						}else{
							ws.addCell(new Label(3,6+Integer.parseInt(rq), gznr,wcfTytle));
							ws.addCell(new Label(5,6+Integer.parseInt(rq), gzkssj+"至"+gzjssj,wcfTytle));
						}
					}
				}
			}
			
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;
			 
			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);
			
			ws.addCell(new Label(3,24, map.get("xm") + "同学实际工作时间为" + (StringUtils.isNull(map.get("ygzsj")) ? "" : map.get("ygzsj")) + "小时",wcfTytle));
			ws.addCell(new Label(3,28, "同意发放" + (StringUtils.isNull(map.get("ffcjje")) ? "" : map.get("ffcjje")) + "元",wcfTytle));
			ws.addCell(new Label(3,30, (StringUtils.isNull(map.get("bz")) ? "" : map.get("bz")),wcfTytle));
			
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.RIGHT;
			vag = VerticalAlignment.CENTRE;
			 
			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);
			ws.addCell(new Label(1,31,xxmc + "党委学工部制",wcfTytle));			
			 //向客户端输出
			ExcelMethods.submitWritableWorkbookOperations(wwb);
		}catch(Exception e){
			
		}
	}
}
