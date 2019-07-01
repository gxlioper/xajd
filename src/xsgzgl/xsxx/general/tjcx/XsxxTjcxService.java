package xsgzgl.xsxx.general.tjcx;


import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.form.User;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


import com.zfsoft.utils.StringUtil;

public class XsxxTjcxService {

	private XsxxTjcxDao dao = new XsxxTjcxDao();
	
	/**
	 * 按年级统计全部在校生人数及比例
	 * @return
	 */
	public List<HashMap<String,String>> getTjcxByNj(User user){
		
		return dao.getTjcxByNj(user);
	}
	
	/**
	 * 按年级、学院统计在校生人数及比例
	 * @param nj
	 * @return
	 */
	public List<HashMap<String,String>> getTjcxByNjXy(String nj,User user){
		
		String[] njArr = null;
		
		if (!StringUtil.isNull(nj)){
			njArr = nj.split("!!@!!");
		}
		
		return dao.getTjcxByNjXy(njArr,user);
	}
	
	
	
	/**
	 * 按年级、学院统计专业总数
	 * @param nj
	 * @return
	 */
	public Map<String,String> getZyzsByXy(String nj,User user){
		
		String[] njArr = null;
		
		if (!StringUtil.isNull(nj)){
			njArr = nj.split("!!@!!");
		}
		
		List<HashMap<String,String>> zyzsList = dao.getZyzsByNjXy(njArr,user);
		
		Map<String,String> zyzsMap = new HashMap<String,String>();
		
		for (HashMap<String,String> map : zyzsList){
			zyzsMap.put(map.get("xydm"), map.get("zyzs"));
		}
		
		
		return zyzsMap;
	}
	
	
	
	/**
	 * 按年级、学院统计班级总数
	 * @param nj
	 * @return
	 */
	public Map<String,String> getBjzsByXy(String nj,User user){

		String[] njArr = null;
		
		if (!StringUtil.isNull(nj)){
			njArr = nj.split("!!@!!");
		}
		
		List<HashMap<String,String>> bjzsList = dao.getBjzsByNjXy(njArr,user);
		
		Map<String,String> zyzsMap = new HashMap<String,String>();
		
		for (HashMap<String,String> map : bjzsList){
			zyzsMap.put(map.get("xydm"), map.get("bjzs"));
		}
		
		return zyzsMap;
	}
	
	
	/**
	 * 按年级、学院、专业统计
	 * @param nj
	 * @param xydm
	 * @return
	 */
	public List<HashMap<String,String>> getTjcxByZy(XsxxTjcxForm model,User user){
		
		String[] nj = null;
		String[] xydm = null;
		
		if (!StringUtil.isNull(model.getNj())){
			nj = model.getNj().split("!!@!!");
		}
		
		if (!StringUtil.isNull(model.getXydm())){
			xydm=model.getXydm().split("!!@!!");
		}
		
		return dao.getTjcxByZy(nj,xydm);
	}
	
	
	/**
	 * 按专业统计班级总数
	 * @param nj
	 * @return
	 */
	public Map<String,String> getBjzsByZy(XsxxTjcxForm model,User user){
		
		String[] nj = null;
		String[] xydm = null;
		
		if (!StringUtil.isNull(model.getNj())){
			nj = model.getNj().split("!!@!!");
		}
		
		if (!StringUtil.isNull(model.getXydm())){
			xydm=model.getXydm().split("!!@!!");
		}
		
		List<HashMap<String,String>> bjzsList = dao.getBjzsByZy(nj ,xydm);
		
		Map<String,String> zyzsMap = new HashMap<String,String>();
		
		for (HashMap<String,String> map : bjzsList){
			zyzsMap.put(map.get("zydm"), map.get("bjzs"));
		}
		
		return zyzsMap;
	}
	
	

	/**
	 * 按班级统计查询
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getTjcxByBj(XsxxTjcxForm model){
		
		String[] nj = null;
		String[] xydm = null;
		String[] zydm = null;
		
		if (!StringUtil.isNull(model.getNj())){
			nj = model.getNj().split("!!@!!");
		}
		
		if (!StringUtil.isNull(model.getXydm())){
			xydm=model.getXydm().split("!!@!!");
		}
		
		if (!StringUtil.isNull(model.getZydm())){
			zydm=model.getZydm().split("!!@!!");
		}
		return dao.getTjcxByBj(nj, xydm, zydm);
	}
	
	/**
	 * 按班级统计查询
	 * @param model
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public ArrayList<String[]> tjxsByBjdm(XsxxTjcxForm model) throws 
		IllegalArgumentException, SecurityException, 
		IllegalAccessException, 
		InvocationTargetException, NoSuchMethodException{
		return dao.tjxsByBjdm(model);
	}
	
	/**
	 * 按学院统计全部在校生人数及比例
	 * @return
	 */
	public List<HashMap<String,String>> getTjcxByXy(User user){
		
		return dao.getTjcxByXy(user);
	}
	
	/**
	 * 按学院统计专业总数
	 * @return
	 */
	public Map<String,String> getZyzsByXy(User user){
		List<HashMap<String,String>> bjzsList =  dao.getZyzsByXy(user);
		Map<String,String> bjzsMap = new HashMap<String,String>();
		for (HashMap<String,String> map : bjzsList){
			bjzsMap.put(map.get("xydm"), map.get("zyzs"));
		}
		return bjzsMap;
	}
	
	/**
	 * 按学院统计班级
	 * @return
	 */
	public Map<String,String> getBjzsByXy(User user){
		
		List<HashMap<String,String>> bjzsList =  dao.getBjzsByXy(user);
		Map<String,String> bjzsMap = new HashMap<String,String>();
		for (HashMap<String,String> map : bjzsList){
			bjzsMap.put(map.get("xydm"), map.get("bjzs"));
		}
		return bjzsMap;
	}
	
	public List<HashMap<String, String>> getXyList(User user){
		return dao.getXyList(user);
	}
	
	public List<HashMap<String, String>> getNjList(User user) {
		return dao.getNjList(user);
	}
	
	/**
	 * 导出数据
	 */
	public void getDcsjList(XsxxTjcxForm actionForm, OutputStream ops,String type,User user)throws Exception {
			
		List<String[]> list = new ArrayList<String[]>();
		list = dao.getDcsjList(actionForm,type,user);
		WritableWorkbook wwb = Workbook.createWorkbook(ops);
		if("nj".equals(type)){
			Label titleCell  =  new Label(0, 0, Base.xxmc+"学生信息统计结果");
			
			WritableSheet ws = wwb.createSheet("导出结果", 0);
					
			//创建第一行
			WritableCellFormat wcFormat = new WritableCellFormat();
			WritableFont font = new WritableFont(WritableFont.ARIAL, 15);
			font.setBoldStyle(WritableFont.BOLD);
			wcFormat.setFont(font);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			titleCell.setCellFormat(wcFormat);
			ws.mergeCells(0, 0, 5, 0);
			ws.addCell(titleCell);
			if(null!=list&&list.size()>0){
				
			String[] sT = {"年级","学生总数","男生数","女生数","男生比例","女生比例"};
				for (int i = 0; i < sT.length; i++) {
					titleCell = new Label(i, 1, sT[i]);
					wcFormat = new WritableCellFormat();
					font = new WritableFont(WritableFont.ARIAL, 10);
					font.setBoldStyle(WritableFont.BOLD);
					wcFormat.setFont(font);
					wcFormat.setAlignment(Alignment.CENTRE);
					wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
					titleCell.setCellFormat(wcFormat);
					ws.addCell(titleCell);
					}
				int n = 1;
				for (String[] rs : list) {
					for (int i = 0; i < sT.length; i++) {
						titleCell = new Label(i, n+1, rs[i]);
						wcFormat = new WritableCellFormat();
						font = new WritableFont(WritableFont.ARIAL, 10);
						wcFormat.setFont(font);
						wcFormat.setAlignment(Alignment.CENTRE);
						wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
						titleCell.setCellFormat(wcFormat);
						ws.addCell(titleCell);
						}
					n++;
					}
					}
				}
			if("njxy".equals(type)){
				String njs = "";
				if(!StringUtil.isNull(actionForm.getNj())){
					String [] Arrnj =  actionForm.getNj().split("!!@!!");
					for(int i = 0;i < Arrnj.length;i++){
						if(i!=Arrnj.length-1){
							njs+=Arrnj[i]+"，";
						}else{
							njs+=Arrnj[i]+"年级";
						}
					}
				}
				Label titleCell  =  new Label(0, 0, Base.xxmc+njs+"学生信息统计结果");
				
				WritableSheet ws = wwb.createSheet("导出结果", 0);
						
				//创建第一行
				WritableCellFormat wcFormat = new WritableCellFormat();
				WritableFont font = new WritableFont(WritableFont.ARIAL, 15);
				font.setBoldStyle(WritableFont.BOLD);
				wcFormat.setFont(font);
				wcFormat.setAlignment(Alignment.CENTRE);
				wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
				titleCell.setCellFormat(wcFormat);
				ws.mergeCells(0, 0, 7, 1);
				ws.addCell(titleCell);
				if(null!=list&&list.size()>0){

				String[] sT = {"学院名称","学生总数","男生数","女生数","男生比例","女生比例","专业总数","班级总数"};
				for (int i = 0; i < sT.length; i++) {
						titleCell = new Label(i, 2, sT[i]);
						wcFormat = new WritableCellFormat();
						font = new WritableFont(WritableFont.ARIAL, 10);
						font.setBoldStyle(WritableFont.BOLD);
						wcFormat.setFont(font);
						wcFormat.setAlignment(Alignment.CENTRE);
						wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
						titleCell.setCellFormat(wcFormat);
						ws.addCell(titleCell);
					}
				int n = 1;
				for (String[] rs : list) {
					for (int i = 0; i < sT.length; i++) {
						titleCell = new Label(i, n+2, rs[i]);
						wcFormat = new WritableCellFormat();
						font = new WritableFont(WritableFont.ARIAL, 10);
						wcFormat.setFont(font);
						wcFormat.setAlignment(Alignment.CENTRE);
						wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
						titleCell.setCellFormat(wcFormat);
						ws.addCell(titleCell);
						}
						n++;
					}
					}
				}
			if("njxyzy".equals(type)){
				String njs = "";
				String xys = "";
				if(!StringUtil.isNull(actionForm.getNj())){
					String [] Arrnj =  actionForm.getNj().split("!!@!!");
					for(int i = 0;i < Arrnj.length;i++){
						if(i!=Arrnj.length-1){
							njs+=Arrnj[i]+"，";
						}else{
							njs+=Arrnj[i]+"年级";
						}
					}
				}
				if(!StringUtil.isNull(actionForm.getXydm())){
					String [] Arrnj =  actionForm.getXydm().split("!!@!!");
					for(int i = 0;i < Arrnj.length;i++){
						if(i!=Arrnj.length-1){
							xys+=dao.getXymc(Arrnj[i])+"，";
						}else{
								xys+=dao.getXymc(Arrnj[i]);
						}
					}
				}
				Label titleCell  =  new Label(0, 0, Base.xxmc+njs+xys+"学生信息统计结果");
				
				WritableSheet ws = wwb.createSheet("导出结果", 0);
						
				//创建第一行
				WritableCellFormat wcFormat = new WritableCellFormat();
				WritableFont font = new WritableFont(WritableFont.ARIAL, 15);
				font.setBoldStyle(WritableFont.BOLD);
				wcFormat.setFont(font);
				wcFormat.setAlignment(Alignment.CENTRE);
				wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
				titleCell.setCellFormat(wcFormat);
				ws.mergeCells(0, 0, 6, 1);
				ws.addCell(titleCell);
				if(null!=list&&list.size()>0){

				String[] sT = {"专业名称","学生总数","男生数","女生数","男生比例","女生比例","班级总数"};
				for (int i = 0; i < sT.length; i++) {
						titleCell = new Label(i, 2, sT[i]);
						wcFormat = new WritableCellFormat();
						font = new WritableFont(WritableFont.ARIAL, 10);
						font.setBoldStyle(WritableFont.BOLD);
						wcFormat.setFont(font);
						wcFormat.setAlignment(Alignment.CENTRE);
						wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
						titleCell.setCellFormat(wcFormat);
						ws.addCell(titleCell);
					}
				int n = 1;
				for (String[] rs : list) {
					for (int i = 0; i < sT.length; i++) {
						titleCell = new Label(i, n+2, rs[i]);
						wcFormat = new WritableCellFormat();
						font = new WritableFont(WritableFont.ARIAL, 10);
						wcFormat.setFont(font);
						wcFormat.setAlignment(Alignment.CENTRE);
						wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
						titleCell.setCellFormat(wcFormat);
						ws.addCell(titleCell);
						}
						n++;
					}
					}
				}
			if("njxyzybj".equals(type)){
				String njs = "";
				String xys = "";
				String zys = "";
				if(!StringUtil.isNull(actionForm.getNj())){
					String [] Arrnj =  actionForm.getNj().split("!!@!!");
					for(int i = 0;i < Arrnj.length;i++){
						if(i!=Arrnj.length-1){
							njs+=Arrnj[i]+"，";
						}else{
							njs+=Arrnj[i]+"年级";
						}
					}
				}
				if(!StringUtil.isNull(actionForm.getXydm())){
					String [] Arrnj =  actionForm.getXydm().split("!!@!!");
					for(int i = 0;i < Arrnj.length;i++){
						if(i!=Arrnj.length-1){
							xys+=dao.getXymc(Arrnj[i])+"，";
						}else{
								xys+=dao.getXymc(Arrnj[i]);
						}
					}
				}
				if(!StringUtil.isNull(actionForm.getZydm())){
					String [] Arrnj =  actionForm.getZydm().split("!!@!!");
					for(int i = 0;i < Arrnj.length;i++){
						if(i!=Arrnj.length-1){
							zys+=dao.getZymc(Arrnj[i])+"，";
						}else{
							zys+=dao.getZymc(Arrnj[i])+"专业";
						}
					}
				}
				Label titleCell  =  new Label(0, 0, Base.xxmc+njs+xys+zys+"学生信息统计结果");
				
				WritableSheet ws = wwb.createSheet("导出结果", 0);
						
				//创建第一行
				WritableCellFormat wcFormat = new WritableCellFormat();
				WritableFont font = new WritableFont(WritableFont.ARIAL, 15);
				font.setBoldStyle(WritableFont.BOLD);
				wcFormat.setFont(font);
				wcFormat.setAlignment(Alignment.CENTRE);
				wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
				titleCell.setCellFormat(wcFormat);
				ws.mergeCells(0, 0, 5, 1);
				ws.addCell(titleCell);
				if(null!=list&&list.size()>0){

				String[] sT = {"班级名称","学生总数","男生数","女生数","男生比例","女生比例"};
				for (int i = 0; i < sT.length; i++) {
						titleCell = new Label(i, 2, sT[i]);
						wcFormat = new WritableCellFormat();
						font = new WritableFont(WritableFont.ARIAL, 10);
						font.setBoldStyle(WritableFont.BOLD);
						wcFormat.setFont(font);
						wcFormat.setAlignment(Alignment.CENTRE);
						wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
						titleCell.setCellFormat(wcFormat);
						ws.addCell(titleCell);
					}
				int n = 1;
				for (String[] rs : list) {
					for (int i = 0; i < sT.length; i++) {
						titleCell = new Label(i, n+2, rs[i]);
						wcFormat = new WritableCellFormat();
						font = new WritableFont(WritableFont.ARIAL, 10);
						wcFormat.setFont(font);
						wcFormat.setAlignment(Alignment.CENTRE);
						wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
						titleCell.setCellFormat(wcFormat);
						ws.addCell(titleCell);
						}
						n++;
					}
					}
				}
			if("xs".equals(type)){
				String njs = "";
				String xys = "";
				String zys = "";
				String bjs = "";
				if(!StringUtil.isNull(actionForm.getNj())){
					String [] Arrnj =  actionForm.getNj().split("!!@!!");
					for(int i = 0;i < Arrnj.length;i++){
						if(i!=Arrnj.length-1){
							njs+=Arrnj[i]+"，";
						}else{
							njs+=Arrnj[i]+"年级";
						}
					}
				}
				if(!StringUtil.isNull(actionForm.getXydm())){
					String [] Arrnj =  actionForm.getXydm().split("!!@!!");
					for(int i = 0;i < Arrnj.length;i++){
						if(i!=Arrnj.length-1){
							xys+=dao.getXymc(Arrnj[i])+"，";
						}else{
								xys+=dao.getXymc(Arrnj[i]);
						}
					}
				}
				if(!StringUtil.isNull(actionForm.getZydm())){
					String [] Arrnj =  actionForm.getZydm().split("!!@!!");
					for(int i = 0;i < Arrnj.length;i++){
						if(i!=Arrnj.length-1){
							zys+=dao.getZymc(Arrnj[i])+"，";
						}else{
							zys+=dao.getZymc(Arrnj[i])+"专业";
						}
					}
				}
				if(!StringUtil.isNull(actionForm.getBjdm())){
					String [] Arrnj =  actionForm.getBjdm().split("!!@!!");
					for(int i = 0;i < Arrnj.length;i++){
						if(i!=Arrnj.length-1){
							bjs+=dao.getBjmc(Arrnj[i])+"，";
						}else{
							bjs+=dao.getBjmc(Arrnj[i])+"班级";
						}
					}
				}
				Label titleCell  =  new Label(0, 0, Base.xxmc+njs+xys+zys+bjs+"学生信息统计结果");
				
				WritableSheet ws = wwb.createSheet("导出结果", 0);
						
				//创建第一行
				WritableCellFormat wcFormat = new WritableCellFormat();
				WritableFont font = new WritableFont(WritableFont.ARIAL, 15);
				font.setBoldStyle(WritableFont.BOLD);
				wcFormat.setFont(font);
				wcFormat.setAlignment(Alignment.CENTRE);
				wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
				titleCell.setCellFormat(wcFormat);
				ws.mergeCells(0, 0, 6, 1);
				ws.addCell(titleCell);
				if(null!=list&&list.size()>0){

				String[] sT = {"学号","姓名","性别","身份证号","民族","政治面貌","联系电话"};
				for (int i = 0; i < sT.length; i++) {
						titleCell = new Label(i, 2, sT[i]);
						wcFormat = new WritableCellFormat();
						font = new WritableFont(WritableFont.ARIAL, 10);
						font.setBoldStyle(WritableFont.BOLD);
						wcFormat.setFont(font);
						wcFormat.setAlignment(Alignment.CENTRE);
						wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
						titleCell.setCellFormat(wcFormat);
						ws.addCell(titleCell);
					}
				int n = 1;
				for (String[] rs : list) {
					for (int i = 0; i < sT.length; i++) {
						titleCell = new Label(i, n+2, rs[i]);
						wcFormat = new WritableCellFormat();
						font = new WritableFont(WritableFont.ARIAL, 10);
						wcFormat.setFont(font);
						wcFormat.setAlignment(Alignment.CENTRE);
						wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
						titleCell.setCellFormat(wcFormat);
						ws.addCell(titleCell);
						}
						n++;
					}
					}
				}
			wwb.write();
			wwb.close();
			}

}
