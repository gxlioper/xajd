package xgxt.action.service;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.util.MessageResources;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.form.CommanForm;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

public class DataManService {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	/**
	 * 学风建设导出条件
	 * */
	public String appendXfjsCondition(CommanForm model,String tableName,String sql){
		StringBuffer sb = new StringBuffer();
		if(!StringUtils.isNull(tableName) && ("view_pjpy_xfjs_bjccqkb".equalsIgnoreCase(tableName.toLowerCase()) || "view_pjpy_xfjs_xsjljcb".equalsIgnoreCase(tableName.toLowerCase()))){
			String ccrq = model.getCcrq();
			String jclxdm = model.getJclxdm();
			String wjlxdm = model.getWjlxdm();
			
			if(!StringUtils.isNull(ccrq)){
				sb.append(" and ccrq like '%");
				sb.append(ccrq.replace("'", "‘"));
				sb.append("%'");
			}
			
			if(!StringUtils.isNull(jclxdm)){
				sb.append(" and jclxdm ='");
				sb.append(jclxdm.replace("'", "‘"));
				sb.append("'");
			}
			
			if(!StringUtils.isNull(wjlxdm)){
				sb.append(" and wjlxdm ='");
				sb.append(wjlxdm.replace("'", "‘"));
				sb.append("'");
			}
		}
		sql += sb.toString();
		return sql;
	}
	
	/**
	 * 判断学号是否要模糊查询
	 * */
	public boolean checkXhEqOrLike(String tableName){
		boolean result = true;
		if(!StringUtils.isNull(tableName) 
				&& ("view_pjpy_xfjs_bjccqkb".equalsIgnoreCase(tableName.toLowerCase()) 
				|| "view_pjpy_xfjs_xsjljcb".equalsIgnoreCase(tableName.toLowerCase())
				|| "view_qgzxsqb".equalsIgnoreCase(tableName.toLowerCase())
				|| "view_czxx_ftypxxxb".equalsIgnoreCase(tableName.toLowerCase())
				|| "view_czxx_typxxxb".equalsIgnoreCase(tableName.toLowerCase())
				|| "view_yxtyxxb".equalsIgnoreCase(tableName.toLowerCase())
				)){
			result = false;
		}
		return result;
	}

	/** 
	 * @描述:同济大学浙江分院个性化导出功能
	 * @作者：cq[工号：785]
	 * @日期：2014-3-26 下午02:13:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param exporModel
	 * @param outputStream
	 * @param user
	 * void 返回类型 
	 * @throws 
	 */
	public void bbdcExport(CommanForm dataSearchForm, OutputStream os, User user)
			throws Exception {

		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		// 获奖名单统计列表
		List<HashMap<String, String>> list = getbbdcExport(dataSearchForm,user);
		int x=0,y=0;   //x:男生人数   y:女生人数
		for(HashMap<String, String> hm :list){
			if("男".equals(hm.get("xb"))){
				x++;
			}else if("女".equals(hm.get("xb"))){
				y++;
			}else{
				//这里未做处理。
			}
		}
		
		// 设置标题
		StringBuffer title = new StringBuffer();
		title.append("学生心理健康状况报表");
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		WritableSheet ws = wwb.createSheet( "月报表", 1);

		
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.createFont("宋体"), 12, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(1, 0, 18, 1);
		ex.printToOneCell_multy(ws, title.toString(), 1, 0, 12, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 450,
				Border.ALL);
		
		//第二行合并
		ws.mergeCells(1, 2, 2, 3);
		ws.mergeCells(3, 2, 6, 3);
		ws.mergeCells(7, 2, 8, 3);
		ws.mergeCells(9, 2, 10, 3);
		ws.mergeCells(11, 2, 12, 3);
		ws.mergeCells(13, 2, 14, 3);
		ws.mergeCells(15, 2, 16, 3);
		ws.mergeCells(17, 2, 18, 3);
		
		//第二行数据填充
		ws.addCell(new Label(1,2,"学生人数",wcf2));
		ws.addCell(new Label(3,2,String.valueOf(list.size()),wcf2));
		ws.addCell(new Label(7,2,"男生人数",wcf2));
		ws.addCell(new Label(9,2,String.valueOf(x),wcf2));
		ws.addCell(new Label(11,2,"女生人数",wcf2));
		ws.addCell(new Label(13,2,String.valueOf(y),wcf2));
		ws.addCell(new Label(15,2,"重点关注人数（男，女）",wcf2));
		ws.addCell(new Label(17,2,"",wcf2));
		
		//第三行合并
		ws.mergeCells(1, 4, 1, 14);
		ws.mergeCells(2, 4, 18, 14);
		
		//第三行数据填充
		ws.addCell(new Label(1,4,"学生整体情况",wcf2));
		ws.addCell(new Label(2,4,"",wcf2));
		
		//第四行合并
		ws.mergeCells(1, 15, 1, 16);
		ws.mergeCells(2, 15, 2, 16);
		ws.mergeCells(3, 15, 3, 16);
		ws.mergeCells(3, 15, 3, 16);
		ws.mergeCells(4, 15, 4, 16);
		ws.mergeCells(5, 15, 5, 16);
		ws.mergeCells(6, 15, 6, 16);
		ws.mergeCells(7, 15, 12, 16);
		ws.mergeCells(13, 15, 18, 16);
		
		
		//第四行数据填充
		ws.addCell(new Label(1,15,Base.YXPZXY_KEY,wcf2));
		ws.addCell(new Label(2,15,"姓名",wcf2));
		ws.addCell(new Label(3,15,"性别",wcf2));
		ws.addCell(new Label(4,15,"班级",wcf2));
		ws.addCell(new Label(5,15,"寝室",wcf2));
		ws.addCell(new Label(6,15,"问题类型",wcf2));
		ws.addCell(new Label(7,15,"心理或行为变化",wcf2));
		ws.addCell(new Label(13,15,"干预措施",wcf2));
		
		//第五行合并
		if(list.size()<7){
			ws.mergeCells(1,17,1,16+list.size()+7);
		}else{
			ws.mergeCells(1,17,1,16+list.size());
		}
		
		//第五行填充数据
		
		ws.addCell(new Label(1,17,"重点关注学生",wcf2));
		
		if (list != null && list.size() > 0) {
			
			for (int i = 0; i < list.size(); i++) {
		
				HashMap<String, String> map = list.get(i);
		
				ws.setColumnView(1,5);
				ws.setColumnView(2,10);
				ws.setColumnView(3,13);
				ws.setColumnView(4,10);
				ws.setColumnView(5,18);
				ws.setColumnView(6,18);
				
				ws.addCell(new Label(2, 17 + i, map.get("xm"), wcf2));// 姓名
				ws.addCell(new Label(3, 17 + i, map.get("xb"), wcf2));// 性别
				ws.addCell(new Label(4, 17 + i, map.get("bjmc"), wcf2));// 班级
				ws.addCell(new Label(5, 17 + i, map.get("ssbh"), wcf2));// 寝室
				ws.addCell(new Label(6, 17 + i, map.get("tbgxxslbmc"), wcf2));// 问题类型
				ws.addCell(new Label(7, 17 + i, map.get("zxqjzysj"), wcf2));// 心里或行为变化
				ws.addCell(new Label(13, 17 + i, map.get("xytbgxcs"), wcf2));// 敢于措施
				ws.mergeCells(7, 17 + i, 12, 17 + i);
				ws.mergeCells(13, 17 + i, 18, 17 + i);
			}
		
		}
		
		//如果数据集小于7行增加7行空白
		
		if(list.size()<7){
			for(int i = 0; i<7; i++){
				ws.addCell(new Label(2, 17 +list.size()+ i, "", wcf2));// 姓名
				ws.addCell(new Label(3, 17 +list.size()+ i, "", wcf2));// 性别
				ws.addCell(new Label(4, 17 +list.size()+ i, "", wcf2));// 班级
				ws.addCell(new Label(5, 17 +list.size()+ i, "", wcf2));// 寝室
				ws.addCell(new Label(6, 17 +list.size()+ i, "", wcf2));// 问题类型
				ws.addCell(new Label(7, 17 +list.size()+ i, "", wcf2));// 心里或行为变化
				ws.addCell(new Label(13, 17 +list.size()+ i, "", wcf2));// 敢于措施
				ws.mergeCells(7, 17 +list.size()+ i, 12, 17 + i);
				ws.mergeCells(13, 17 +list.size()+ i, 18, 17 + i);
			}
		}
		
		//最后一行合并
		if(list.size()<7){
			ws.mergeCells(1, 17+list.size()+7, 1, 17+list.size()+20+7);
			ws.mergeCells(2, 17+list.size()+7, 18, 17+list.size()+20+7);
		}else{
			ws.mergeCells(1, 17+list.size(), 1, 17+list.size()+20);
			ws.mergeCells(2, 17+list.size(), 18, 17+list.size()+20);
		}
		
		//最后一行填充数据
		if(list.size()<7){
			ws.addCell(new Label(1,17+list.size()+7,"对学校心理健康教育工作的建议和意见",wcf2));
			ws.addCell(new Label(2,17+list.size()+7,"",wcf2));
		}else{
			ws.addCell(new Label(1,17+list.size(),"对学校心理健康教育工作的建议和意见",wcf2));
			ws.addCell(new Label(2,17+list.size(),"",wcf2));
		}
		
		//侧面合并行
		if(list.size()<7){
			ws.mergeCells(0, 0, 0, 17+list.size()+20+7);
		}else{
			ws.mergeCells(0, 0, 0, 17+list.size()+20);
		}
		
		//最后一行数据填充
		ws.setColumnView(0,5);
		ws.addCell(new Label(0,0,"同济大学浙江学院学生心理健康状况报表",wcf2));
		
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * 
	 * @描述:同济大学浙江分院个性化导出功能
	 * @作者：cq [工号：785]
	 * @日期：2014-3-26 下午03:33:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getbbdcExport(CommanForm dataSearchForm, User user) {
		
		DAO dao = DAO.getInstance();
		

		StringBuilder sql = new StringBuilder();  
		
		
		sql.append("select * from (select xh||tbgxxslbdm 主键,rownum r,a.* from view_xytbgxxsxx a where 1=1 ) a where 1=1 ");
		
		if(!dataSearchForm.getNj().isEmpty()){
			sql.append(" and nj = '"+ dataSearchForm.getNj()+"'");
		}
		if(!dataSearchForm.getXh(). isEmpty()){
			sql.append(" and xh = '"+ dataSearchForm.getXh()+"'");
		}
		if(!dataSearchForm.getXm().isEmpty()){
			sql.append(" and xm = '"+ dataSearchForm.getXm()+"'");
		}
		if(!dataSearchForm.getNd().isEmpty()){
			sql.append(" and xn = '"+ dataSearchForm.getXn()+"'");
		}
		if(!dataSearchForm.getXq().isEmpty()){
			sql.append(" and nd = '"+ dataSearchForm.getNd()+"'");
		}
		if(!dataSearchForm.getXq().isEmpty()){
			sql.append(" and xq = '"+ dataSearchForm.getXq()+"'");
		}
		if(!dataSearchForm.getXydm().isEmpty()){
			sql.append(" and xydm = '"+ dataSearchForm.getXydm()+"'");
		}
		if(!dataSearchForm.getZydm().isEmpty()){
			sql.append(" and zydm = '"+ dataSearchForm.getZydm()+"'");
		}
		if(!dataSearchForm.getBjdm().isEmpty()){
			sql.append(" and bjdm = '"+ dataSearchForm.getBjdm()+"'");
		}
		if(!dataSearchForm.getLydm().isEmpty()){
			sql.append(" and lydm = '"+ dataSearchForm.getLydm()+"'");
		}
		
		
		String[] colArr=new String[] { "xm","xb","bjmc","ssbh","tbgxxslbmc","zxqjzysj","xytbgxcs"};
		
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(), 
				new String[]{} , colArr);

		return list;
	}
}
