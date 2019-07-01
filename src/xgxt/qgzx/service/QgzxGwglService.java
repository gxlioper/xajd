package xgxt.qgzx.service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.qgzx.dao.QgzxGwglDAO;
import xgxt.qgzx.form.QgzxForm;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 勤工助学模块岗位管理Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-05-11</p>
 */
public class QgzxGwglService {
	QgzxGwglDAO dao = new QgzxGwglDAO();
	
	/**
	 * 查询学生岗位申请详细信息
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getGwsqxx(QgzxForm model){
		String xh = model.getXh();
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.putAll(dao.getGwsqxx(model));//学生申请信息
		map.put("sfpk", dao.isKns(xh)==true ? "是" : "否");//困难生信息
		map.putAll(dao.getGwxx(model));//学生申请的岗位信息
		
		return map;
	}
	
	/**
	 * 根据表的字段获取对应的中文说明
	 * @param String[] colList
	 * @param String tableName
	 * @return String[]
	 * */
	public String[] getColumnNameCN(String[] colList,String tableName){
		return dao.getColumnNameCN(colList, tableName);
	}
	
	/**
	 * 查询学生岗位调整信息导出
	 * @param QgzxForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwtzxxForExport(QgzxForm model,String[] colList){
		return dao.selectXsgwtzxxForExport(model,colList);
	}
	
	/**
	 * 修改在岗学生信息
	 * @param QgzxForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * */
	public boolean modifyXsgwxxb(QgzxForm model,HttpServletRequest request) throws Exception{
		String[] colList = {"bdsj","lzsj","fjwjmc"};
		String[] colValue= {model.getBdsj(),model.getLzsj(),model.getFjwjmc()};
		if(StringUtils.isNull(model.getFjwjmc())){
			colList = new String[]{"bdsj","lzsj"};
			colValue= new String[]{model.getBdsj(),model.getLzsj()};
		}
		
		return StandardOperation.update("xsgwxxb", colList, colValue, "xh||gwdm||sqsj", model.getPkValue(), request);
	}
	
	/**
	 * 打印宁波天一职业技术学院岗位年度汇总表
	 * @param WritableWorkbook wwb
	 * @param String nd	 
	 * */
	public void printNbtyGwhzb(WritableWorkbook wwb,String nd){
		QgzxDao qgzxDao = new QgzxDao();
		nd = StringUtils.isNull(nd) ? qgzxDao.getSqsjInfo().get("nd") : nd;
		List<HashMap<String, String>> list = dao.selectXsgwhzxx(nd);
		try{
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;
			
			wcfTytle.setVerticalAlignment(vag);
		    wcfTytle.setAlignment(alignMent);
		 
		    WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		    wfTytle.setBoldStyle(WritableFont.BOLD);
		    wfTytle.setPointSize(20);
		    wcfTytle.setFont(wfTytle);
		 
		    ws.addCell(new Label(0,1,nd+ "学年度学生勤工助学用工岗位汇总审核表",wcfTytle));
		    
		    wcfTytle = new WritableCellFormat();
		    vag = VerticalAlignment.CENTRE;
		    wcfTytle.setVerticalAlignment(vag);
		    wcfTytle.setAlignment(alignMent);
		    wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		    wcfTytle.setWrap(true);
		    wfTytle = new WritableFont(WritableFont.ARIAL);
		    wfTytle.setPointSize(10);
		    wcfTytle.setFont(wfTytle);
		    //写入数据
		    for(int i=0; i<list.size();i++){
		    	HashMap<String, String> map  = list.get(i);
		    	String gwxzmc = map.get("gwxzmc");
		    	String tempGwxzmc = "";
		    	if("固定".equalsIgnoreCase(gwxzmc)){
		    		tempGwxzmc = "A";
		    	}
		    	if("临时".equalsIgnoreCase(gwxzmc)){
		    		tempGwxzmc = "B";
		    	}
		    	ws.addCell(new Label(0,i+3,(i+1)+"",wcfTytle));
		    	ws.addCell(new Label(1,i+3,map.get("yrdwmc"),wcfTytle));
		    	ws.addCell(new Label(2,i+3,map.get("gwdm"),wcfTytle));
		    	ws.addCell(new Label(3,i+3,tempGwxzmc,wcfTytle));
		    	ws.addCell(new Label(4,i+3,map.get("xyrs"),wcfTytle));
		    	ws.addCell(new Label(5,i+3,map.get("gznr"),wcfTytle));
		    	ws.addCell(new Label(6,i+3,(StringUtils.isNull(map.get("gzksrq")) ? "" : map.get("gzksrq"))+"至" + (StringUtils.isNull(map.get("gzjsrq")) ? "" : map.get("gzjsrq")),wcfTytle));
		    	ws.addCell(new Label(7,i+3,map.get("fzr"),wcfTytle));
		    	ws.addCell(new Label(8,i+3,map.get("lxdh"),wcfTytle));
		    }
		    ws.mergeCells(0, list.size()+3, 1, list.size()+3);
		    ws.addCell(new Label(0,list.size()+3,"人事处审批",wcfTytle)); 
		    ws.mergeCells(2, list.size()+3, 4, list.size()+3);
		    ws.addCell(new Label(2,list.size()+3,"签名                     年  月  日",wcfTytle));
		    ws.addCell(new Label(5,list.size()+3,Base.YXPZXY_KEY+"审核",wcfTytle));
		    ws.mergeCells(6, list.size()+3, 8, list.size()+3);
		    ws.addCell(new Label(6,list.size()+3,"签名                     年  月  日",wcfTytle));
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		
		 //向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	public ArrayList<String[]> accountGwpksbl(ArrayList<String[]> rs, int blIndex, int pkIndex){
		for(int i=0; i<rs.size(); i++){
//			String[] tmpArr = rs.get(i);
			float gwrs = dao.getGwrsCount(rs.get(i)[pkIndex]);
			float gwknsrs = dao.getGwknsrsCount(rs.get(i)[pkIndex]);
			String bl = "0.00";
			if(gwrs !=0){
				bl = new java.text.DecimalFormat("##0.00").format((gwknsrs/gwrs)*100);
			}
			if(rs.get(i).length>blIndex){
				rs.get(i)[blIndex] = bl+"%";
			}
		}
		return rs;
	}
	
	public void printYrdwyrqkb(User user,OutputStream os){
		WritableWorkbook wwb = null;
		HashMap<String, String> yrdwMap = dao.getYrdwInfo(user.getUserName());
		List<HashMap<String, String>> gwhzList = dao.getYrdwGwhzList(yrdwMap.get("yrdwdm"));
		try{
			wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("用工单位用人情况表", 0);
			WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式
			wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
			// 填充标题
			ExcelMB ex = new ExcelMB();
			ws.mergeCells(0, 0, 7, 1);
			ex.printToOneCell_multy(ws, "用工单位用人情况表", 0, 0, 20, true, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 650, Border.NONE);
			
			//写副标题
			ws.mergeCells(0, 2, 2, 2);							
			ws.addCell(new Label(0, 2, "用人单位：" + (StringUtils.isNull(yrdwMap.get("yrdwmc")) ? "" : yrdwMap.get("yrdwmc")), wcf));
			ws.mergeCells(3, 2, 5, 2);	
			ws.addCell(new Label(3, 2, "负责老师：" + (StringUtils.isNull(yrdwMap.get("lxr")) ? "" : yrdwMap.get("lxr")), wcf));
			ws.mergeCells(6, 2, 7, 2);
			ws.addCell(new Label(6, 2, "用人时间：", wcf));			
			
			ws.mergeCells(0, 3,1, 3);
			ws.addCell(new Label(0, 3, "用人岗位", wcf));
			ws.mergeCells(2, 3,3, 3);
			ws.addCell(new Label(2, 3, "用人数量", wcf));
			ws.mergeCells(4, 3,5, 3);
			ws.addCell(new Label(4, 3, "用人金额", wcf));
			ws.mergeCells(6, 3,7, 3);
			ws.addCell(new Label(6, 3, "备注", wcf));
			
			int base = 4;
			for(int j=0; j<gwhzList.size();j++){
				//写入学生酬金信息
				ws.mergeCells(0, base,1, base);
				ws.addCell(new Label(0, base, (StringUtils.isNull(gwhzList.get(j).get("gwdm")) ? "" : gwhzList.get(j).get("gwdm")), wcf));
				ws.mergeCells(2, base,3, base);
				ws.addCell(new Label(2, base, (StringUtils.isNull(gwhzList.get(j).get("rs")) ? "" : gwhzList.get(j).get("rs")), wcf));
				ws.mergeCells(4, base,5, base);
				ws.addCell(new Label(4, base, (StringUtils.isNull(gwhzList.get(j).get("cjje")) ? "" : gwhzList.get(j).get("cjje")), wcf));
				ws.mergeCells(6, base,7, base);
				ws.addCell(new Label(6, base, "", wcf));
				base++;	
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
			
		//写入到客户端
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
