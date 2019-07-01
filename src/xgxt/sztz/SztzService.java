package xgxt.sztz;

import java.io.OutputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.ExcelMB;
import xgxt.audit.spgc.AuditingManage;
import xgxt.audit.spgc.AuditingModel;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

public class SztzService  extends AuditingManage{
	
	private SztzDAO dao = new SztzDAO();
	
	
	/**
	 * 素质拓展通用保存
	 */
	public boolean saveSztz(SaveForm form, SztzForm model,
			HttpServletRequest request) throws Exception {
		return dao.submitData(form, model, request);
	}
	
	/**
	 * 素质拓展更新
	 */
	public boolean updateXmsb(SztzForm model) throws Exception {
		
		boolean result = dao.updateXmsb(model);
	
			if (result) {
				return dao.saveXmjx(model);
			}
		
		return result;
	}
	
	/**
	 * 科目维护
	 * @param form
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveKmwh(SaveForm form, SztzForm model,
			HttpServletRequest request) throws Exception {

		boolean result = saveSztz(form, model, request);

		if (null != model.getHxnldm() && null != model.getHxnlmc()) {
			if (result) {
				return dao.saveHxnl(model);
			}
		}

		return result;
	}
	
	
	/**
	 * 根据科目代码查找相应的核心能力
	 * @param pkValue
	 * @return
	 */
	public List<HashMap<String,String>> getHxnl(String pkValue){
		
		return dao.getHxnl(pkValue);
	}
	

	/**
	 * 查找科目代码
	 * @param pkValue
	 * @return
	 */
	public List<HashMap<String,String>> getXmBykmdm(String pkValue){
		
		return dao.getXmBykmdm(pkValue);
	}
	
	/**
	 * 查询项目
	 * @param pkValue
	 * @return
	 */
	public List<HashMap<String,String>> getXmlist(String pkValue){
		
		return dao.getXmlist(pkValue);
	}
	
	/**
	 * 删除科目及相关核心能力
	 * @param kmdm
	 * @return
	 */
	public boolean delKmsz(String[] kmdm){
		
		if (null != kmdm && kmdm.length > 0){
			try {
				return dao.delKmsz(kmdm);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	
	/**
	 * 项目申报
	 * @param form
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXmsb(SaveForm form, SztzForm model,
			HttpServletRequest request) throws Exception {

		boolean result = saveSztz(form, model, request);
		
		if (null != model.getJxdm() && null != model.getJxmc()
				&& null != model.getJxfs()) {
			if (result) {
				return dao.saveXmjx(model);
			}
		}

		return result;
	}
	
	
	/**
	 * 项目奖项列表
	 * @param xmid
	 * @return
	 */
	public List<HashMap<String,String>> getXmjxList(String xmid){
		
		return dao.getXmjxList(xmid);
	}
	
	
	/**
	 * 删除项目
	 * @param xmid
	 * @return
	 */
	public boolean delXm(String[] xmid){
		
		if (null != xmid && xmid.length > 0){
			try {
				return dao.delXm(xmid);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	
	/**
	 * 加载当前学年、学期项目列表
	 * @return
	 */
	public List<HashMap<String,String>> getXmList(){
		
		return dao.getXmList();
	}
	
	
	/**
	 * 学分审核查询
	 */
	public List<String[]> xfshQuery(SztzForm model,User user) throws Exception{
		
		return dao.xfshQuery(model, user);
	}

	
	/**
	 * 项目审核查询
	 */
	public List<String[]> xmshQuery(SztzForm model,User user) throws Exception{
		
		return dao.xmshQuery(model, user);
	}
	

	/**
	 * 单个审核后的操作
	 */
	protected boolean auditingAfter(Object o) {
		AuditingModel model = (AuditingModel) o;
		//当前审核岗位，如果为空表示提交的是申请操作
		String xtgwid = model.getXtgwid();
		String shzt = "";
		
		if (StringUtils.isNull(xtgwid)){
			//申请记录提交到审批流后，把申请表中的审核状态改为审核中吧！
			shzt = "未审核";
		} else {
			//不是申请那就是审核操作
			if ("不通过".equalsIgnoreCase(model.getShzt())){
				//审核状态为不通过流程要停止的，申请表中审核状态改为“不通过”
				shzt = "不通过";
			} else if ("通过".equalsIgnoreCase(model.getShzt())){
				//通过的话看下当前审批岗位是哪一级，如果是最后一级的要把申请记录表中的审核状态改为通过，不是最后一级改为 “审核中”
				shzt = isLastAudit(model) ? "通过" : "审核中";
				//并且需要把异动信息提交到学生信息正式库
			} else if ("退回".equalsIgnoreCase(model.getShzt()) && "Applicant".equalsIgnoreCase(model.getNextPost()) ){
				//如果是退回操作，并且退回的目标是申请人
				shzt = "退回";
			}
		}
		
		//修改申请表中的审核状态
		if (StringUtils.isNotNull(shzt)){
			//String sql = "update xg_sztz_xssqb set shzt=? where id=?";
			String sql = "update "+ model.getSqjlb() +" set shzt=? where id=?";
			try {
				dao.runUpdate(sql, new String[]{shzt,model.getId()});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return super.auditingAfter(o);
	}
	
	
	/**
	 * 下拉列表
	 * @param flg
	 * @return
	 */
	protected List<HashMap<String,String>> getList(String flg){
		
		if ("shzt".equalsIgnoreCase(flg)){
			return dao.getChkList(32);
		} else if ("shjg".equalsIgnoreCase(flg)){
			return dao.getChkList(33);
		}
		
		return null;
	}
	
	
	/**
	 * 批量审核，每条记录审核后的操作
	 */
	protected boolean doAuditingAfter(Object o) {
		
		return auditingAfter(o);
	}
	
	
	/**
	 * 查询素质拓展申请信息
	 * @param id
	 * @return
	 */
	protected HashMap<String,String> getXmsqInfoById(String id){
		
		String sql = "select * from xg_view_sztz_xssqb where id=?";
		
		return dao.getMapNotOut(sql, new String[]{id});
	}
	
	
	/**
	 * 更新学生获取的奖项和获得的学分
	 * @param model
	 * @return
	 * @throws Exception
	 */
	protected boolean updateSdjx(SztzForm model) throws Exception{
		
		String sql = "update xg_sztz_xssqb set shjx=? , sdxf=? where id=?";
		
		return dao.runUpdate(sql, new String[]{model.getShjx(),model.getSdxf(),model.getId()});
	}
	
	
	/**
	 *素质拓展结果查询 
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	protected List<String[]> sztzJgcx(SztzForm model,User user) throws Exception{
		
		return dao.sztzJgcx(model, user);
	}
	
	
	/**
	 * 素质拓展记录删除
	 * @param model
	 * @return
	 */
	protected boolean delSztz(SztzForm model){
		
		String[] pkValues = model.getPrimarykey_cbv();
		
		if (null != pkValues && pkValues.length > 0){
			return dao.delSztz(pkValues);
		}
		
		return false;
	}
	
	
	/**
	 * 执行赋分查询
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	protected List<String[]> sztzXfcx(SztzForm model,User user) throws Exception{
		
		return dao.sztzXfcx(model, user);
	}
	
	
	/**
	 * 执行赋分保存
	 * @param pkValues
	 * @param sdxf
	 * @return
	 */
	public boolean saveZxff(String[] pkValues,String[] sdxf){
		if (null != pkValues && null != sdxf && pkValues.length > 0 && pkValues.length == sdxf.length){
			List<String[]> params = new ArrayList<String[]>();
			
			for (int i = 0 ; i < pkValues.length; i++){
				String[] tempArr = new String[2];
				tempArr[0] = sdxf[i];
				tempArr[1] = pkValues[i];
				params.add(tempArr);
			}
			
			String sql = "update xg_sztz_xssqb set sdxf=? where id=?";
			
			try {
				int[] result = dao.runBatch(sql, params);
				return dao.checkBatchResult(result);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} 
		
		return false;
	}
	
	
	/**
	 * 打印素质拓展成绩单
	 * @param model
	 * @param os
	 * @throws SQLException
	 */
	protected void printSztzCjd(SztzForm model,OutputStream os) throws SQLException {
		
		String title= "表2-学生毕业成绩单";
		
		HashMap<String,String> stuInfo = CommonQueryDAO.getStuInfo(model.getXh());
		//根据学号查询该学生被审核通过的素质拓展数据
		List<HashMap<String,String>> data = dao.getSztzCjdData(model.getXh());
		//获取该生应该输出哪些周期
		String[] zq = dao.getSztzCjdZq(model.getXh());
		//素质拓展分哪些所属科目、核心能力
		List<HashMap<String,String>> kmlx = dao.getSztzKmlx();
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("素质拓展成绩单", 0);
		
		try {
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(10,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			
			ws.mergeCells(0, 0, zq.length*2+2, 0);// 标题
			ws.addCell(new Label(0,0 , title, wcfTytle));
			ws.addCell(new Label(0,1 , "学生信息", wcfTytle));
			ws.mergeCells(1, 1, zq.length*2+2, 1);
			
			StringBuilder stu = new StringBuilder();
			stu.append("学号:")
			   .append(model.getXh())
			   .append(",姓名:")
			   .append(stuInfo.get("xm"))
			   .append(",班级名称:")
			   .append(stuInfo.get("bjmc"));
			ws.addCell(new Label(1,1 , stu.toString(), wcfTytle));
			
			ws.addCell(new Label(0,2 , "所属科目", wcfTytle));
			ws.addCell(new Label(1,2 , "核心能力", wcfTytle));
			
			if (null != zq && zq.length > 0){
				
				//先输出这个学生参与了哪些周期的素质拓展
				for (int i = 0 ; i < zq.length ; i++){
					ws.addCell(new Label(2*(i+1),2 , zq[i]+"  学分", wcfTytle));
					ws.addCell(new Label(2*(i+1)+1,2 , "补修成绩", wcfTytle));
				}
				//总评学分
				ws.addCell(new Label(zq.length*2+2,2 , "总评学分", wcfTytle));
				
				int[] hjfs = new int[zq.length];
				int[] zphj = new int[kmlx.size()];//总评合计
				//distinct 科目名称、核心能力
				for (int i = 0 ; i < kmlx.size() ; i++){
					ws.addCell(new Label(0,3+i ,kmlx.get(i).get("kmmc"), wcfTytle));
					ws.addCell(new Label(1,3+i ,kmlx.get(i).get("hxnlmc"), wcfTytle));
					int zpxf = 0;//总评学分
					int n = 0;
					for (int j = 0 ; j < zq.length ; j++){
						String sdxf = "";
						String cxxf = "";
						String cxcj = "";
						
						//该学生素质拓展数据
						for (int m = 0 ; m < data.size() ; m++){
							String kmmc = data.get(m).get("kmmc");
							String hxnlmc = data.get(m).get("hxnlmc");
							String xn = data.get(m).get("xn");
							String xq = data.get(m).get("xqmc");
							
							//循环找出对应周期、科目名称、核心能力的所得学分和重修学分
							if (kmlx.get(i).get("kmmc").equalsIgnoreCase(kmmc) 
									&& kmlx.get(i).get("hxnlmc").equalsIgnoreCase(hxnlmc)
									&& zq[j].trim().equalsIgnoreCase(xn+"("+xq+")")){
								sdxf = data.get(m).get("sdxf");
								cxxf = data.get(m).get("cxxf");
								
								int minfs = Integer.valueOf(data.get(m).get("minfs"));
								
								if (!cxxf.equals("0")){
									cxcj = Integer.valueOf(cxxf) < minfs ? "不合格" : "合格";
								}
								hjfs[j]+=Integer.valueOf(sdxf);
								zpxf += Integer.valueOf(sdxf); 
								n+=1;
								
								//输出所得学分、重修学分
								ws.addCell(new Label(2*(j+1),3+i , sdxf, wcfTytle));
								ws.addCell(new Label(2*(j+1)+1,3+i , cxcj, wcfTytle));
							}
						}
						if (n > 0){
							ws.addCell(new Label(2*(j+1)+2,3+i , String.valueOf(zpxf/n), wcfTytle));
							//hjfs[zq.length]+=(zpxf/n);
							zphj[i] = zpxf/n;
						}
					}
				}
				
				ws.addCell(new Label(0,kmlx.size()+3 , "合计", wcfTytle));
				ws.addCell(new Label(1,kmlx.size()+3 , "总分", wcfTytle));
				
				int n = 2;
				for (int m = 0 ; m < hjfs.length ; m++){
					ws.addCell(new Label(n,kmlx.size()+3 , String.valueOf(hjfs[m]), wcfTytle));
//					if (m != hjfs.length-1){
						ws.addCell(new Label(n+1,kmlx.size()+3 , "", wcfTytle));
//					}
					n+=2;
				}
				
				int zphjfs = 0;
				
				for (int x = 0 ; x < zphj.length ; x++){
					zphjfs+=zphj[x];
				}
				
				ws.addCell(new Label(n,kmlx.size()+3 , String.valueOf(zphjfs), wcfTytle));
			}
			
			
			//合并素质分类单元格
			ExcelMB.mergeCells(ws, kmlx.size(), 0, 3);
		}catch (Exception e) {
			e.printStackTrace();
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * 查询对应的审核记录
	 * @param id
	 * @return
	 */
	protected List<HashMap<String,String>> getShxxList(String id,String shjlb){
		
		return dao.getShxxList(id,shjlb);
	}

	
	/**
	 * 检查科目代码是否存在
	 * @param kmdm
	 * @return
	 */
	protected boolean checkKmdmExists(String kmdm) {
		
		
		return "0".equals(dao.checkKmdmExists(kmdm));
	}

	
	/**
	 * 本学年可申请的项目及已申请的信息
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getXmsqList(SztzForm model){
		
		return dao.getXmsqList(model);
	}
	
	public List<HashMap<String,String>> getXsxmsq(SztzForm model){
		
		return dao.getXsxmsq(model);
	}
	
	
	/**
	 * 获取参数设置
	 * @return
	 */
	public HashMap<String,String> getCssz(String xxdm){
			
		HashMap<String,String> map = dao.getCssz(xxdm);
		
		//判断是否在申报时间内
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			if(map.get("sbkssj")!=null && map.get("sbjssj")!=null && map.get("dqsj")!=null){
				Date sbkssj = sdf.parse(map.get("sbkssj"));
				Date sbjssj = sdf.parse(map.get("sbjssj"));
				Date dqsj = sdf.parse(map.get("dqsj"));
				map.put("isSbsj", String.valueOf(dqsj.after(sbkssj) && dqsj.before(sbjssj)));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * 每学期成绩单
	 * @param model
	 * @param os
	 * @throws SQLException
	 */
	protected void printMxqCjd(SztzForm model,OutputStream os) throws SQLException {
		//查询素质拓展每学期成绩单
		List<HashMap<String,String>> data = dao.getMxqCjd(model.getXh(),model.getXn(),model.getXq());
		//学生基本信息
		HashMap<String,String> stuInfo = CommonQueryDAO.getStuInfo(model.getXh());
		
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		String title=stuInfo.get("xm")+"学生"+model.getXn()+"学年"+model.getXq()+"学期素质教育成绩单";
		WritableSheet ws = wwb.createSheet(title, 0);
		
		try {
			//单元格样式
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					true, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);
			
//			ws.mergeCells(0, 0, 6, 0);
			ws.mergeCells(0, 0, 5, 0);
			ws.addCell(new Label(0,0 , title, wcfTytle));
			ws.addCell(new Label(0,1 , "学生信息", wcfTytle));
//			ws.mergeCells(1, 1, 6, 1);
			ws.mergeCells(1, 1, 5, 1);
			wcfTytle = ExcelMB.getWritableCellFormat(10,false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);
			StringBuilder stu = new StringBuilder();
			stu.append("学号:")
			   .append(model.getXh())
			   .append(",姓名:")
			   .append(stuInfo.get("xm"))
			   .append(",班级名称:")
			   .append(stuInfo.get("bjmc"));
			ws.addCell(new Label(1,1 , stu.toString(), wcfTytle));
			wcfTytle = ExcelMB.getWritableCellFormat(12,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);
			ws.setColumnView(0, 15);
			ws.setColumnView(1, 15);
			ws.setColumnView(2, 20);
			ws.setColumnView(3, 20);
			ws.setColumnView(4, 10);
			ws.setColumnView(5, 15);
			ws.addCell(new Label(0,2 , "所属科目", wcfTytle));
			ws.addCell(new Label(1,2 , "核心能力", wcfTytle));
			ws.addCell(new Label(2,2 , "项目名称", wcfTytle));
			ws.addCell(new Label(3,2 , "对应申报学期", wcfTytle));
			ws.addCell(new Label(4,2 , "成绩", wcfTytle));
			ws.addCell(new Label(5,2 , "重修成绩", wcfTytle));
//			ws.addCell(new Label(2,2 , "对应申报学期", wcfTytle));
//			ws.addCell(new Label(3,2 , "学分", wcfTytle));
//			ws.addCell(new Label(4,2 , "成绩", wcfTytle));
//			ws.addCell(new Label(5,2 , "重修学分", wcfTytle));
//			ws.addCell(new Label(6,2 , "重修成绩", wcfTytle));
			wcfTytle = ExcelMB.getWritableCellFormat(10,false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);
			int zxf = 0;
			String pjcj = "";
			Set<String> kmmc = new HashSet<String>();
			
			for (int i = 0 ; i < data.size() ; i++){
				kmmc.add(data.get(i).get("kmmc"));
				ws.addCell(new Label(0,3+i , data.get(i).get("kmmc"), wcfTytle));
				ws.addCell(new Label(1,3+i , data.get(i).get("hxnlmc"), wcfTytle));
				ws.addCell(new Label(2,3+i , data.get(i).get("xmmc"), wcfTytle));
				ws.addCell(new Label(3,3+i , data.get(i).get("xn")+"("+data.get(i).get("xqmc")+")", wcfTytle));
//				ws.addCell(new Label(2,3+i , data.get(i).get("xn")+"("+data.get(i).get("xqmc")+")", wcfTytle));
				
				String sdxf = data.get(i).get("sdxf");
				String sdcj = "";
				if(sdxf==null){
					sdxf ="0";
				}
				
				//判断成绩
				try{
					int fs = Integer.valueOf(sdxf);
					int minfs = Integer.valueOf(data.get(i).get("minfs"));
					int maxfs = Integer.valueOf(data.get(i).get("maxfs"));
					
					if (fs < minfs){
						sdcj = "不合格";
						pjcj = "不合格";
					} else if (fs > maxfs && "否".equals(data.get(i).get("sfcx"))){
						sdcj = "优秀";
					} else {
						sdcj = "合格";
					}
				} catch(Exception e){
					e.printStackTrace();
				}
				
				if ("是".equals(data.get(i).get("sfcx"))){
					ws.addCell(new Label(4,3+i , "", wcfTytle));
					ws.addCell(new Label(5,3+i , sdxf, wcfTytle));
//					ws.addCell(new Label(3,3+i , "", wcfTytle));
//					ws.addCell(new Label(4,3+i , "", wcfTytle));
//					ws.addCell(new Label(5,3+i , sdxf, wcfTytle));
//					ws.addCell(new Label(6,3+i , sdcj, wcfTytle));
				} else {
					zxf+=Integer.valueOf(sdxf);
					ws.addCell(new Label(4,3+i , sdxf, wcfTytle));
					ws.addCell(new Label(5,3+i , "", wcfTytle));
//					ws.addCell(new Label(3,3+i , sdxf, wcfTytle));
//					ws.addCell(new Label(4,3+i , sdcj, wcfTytle));
//					ws.addCell(new Label(5,3+i , "", wcfTytle));
//					ws.addCell(new Label(6,3+i , "", wcfTytle));
				}
			}
			wcfTytle = ExcelMB.getWritableCellFormat(12,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);
//			ws.addCell(new Label(0,3+data.size() , "合计", wcfTytle));
			ws.addCell(new Label(0,3+data.size() , "", wcfTytle));
			ws.addCell(new Label(1,3+data.size() , "总分", wcfTytle));
			ws.addCell(new Label(2,3+data.size() , "", wcfTytle));
			
			
//			ws.addCell(new Label(3,3+data.size() , String.valueOf(zxf), wcfTytle));
			ws.addCell(new Label(3,3+data.size() , "", wcfTytle));
			ws.addCell(new Label(4,3+data.size() , String.valueOf(zxf), wcfTytle));
			int pjxf = zxf/kmmc.size();
			pjxf = pjxf > 100 ? 100 : pjxf;
			if ("".equals(pjcj)){
				if (pjxf < 60){
					pjcj = "不合格";
				} else if (pjxf > 85){
					pjcj = "优秀";
				} else {
					pjcj = "合格";
				}
			}
//			ws.addCell(new Label(4,3+data.size() , pjcj, wcfTytle));
			ws.addCell(new Label(5,3+data.size() , "", wcfTytle));
//			ws.addCell(new Label(6,3+data.size() , "", wcfTytle));
			ExcelMB.mergeCells(ws, data.size()+3, 0, 3);
			ExcelMB.mergeCells(ws, data.size()+3, 1, 3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	public List<HashMap<String,String>> getMxqCjd(String xh){
		
		return dao.getMxqCjd(xh);
	}

	/**
	 * 项目打印
	 * @param outputStream
	 * @param model
	 */
	public void xmDy(ServletOutputStream outputStream, SztzForm model) throws Exception{
		// 导出报表的固定数据
		ArrayList<String[]> gdlist = xmDyTj(model);
		// 导出报表的项目打印信息
		HashMap<String,String> map = dao.getxmDyxx(model.getId());
		String title = "天津职业大学"+map.get("xn")+"学年第"+map.get("xqmc")+"学期素质教育项目考核成绩报告单";
		WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
		WritableSheet ws = wwb.createSheet(title, 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// 填充表头
		ExcelMB excel = new ExcelMB();
		excel.printTitle(ws, title, 10, 1000);// 标题
		ws.addCell(new Label(0,1,"项目名称",wcf2));
		ws.mergeCells(1,1,4,1);
		ws.addCell(new Label(1,1,map.get("xmmc"),wcf2));
		ws.addCell(new Label(5,1,"所属科目",wcf2));
		ws.mergeCells(6,1,9,1);
		ws.addCell(new Label(6,1,map.get("kmmc"),wcf2));
		
		ws.addCell(new Label(0,2,"项目级别",wcf2));
		ws.addCell(new Label(1,2,map.get("xymc"),wcf2));
		ws.addCell(new Label(2,2,"基础成绩",wcf2));
		ws.addCell(new Label(3,2,map.get("jcf"),wcf2));
		ws.addCell(new Label(4,2,"核心能力",wcf2));
		ws.addCell(new Label(5,2,map.get("hxnlmc"),wcf2));
		ws.addCell(new Label(6,2,"学时",wcf2));
		ws.addCell(new Label(7,2,map.get("xs"),wcf2));
		ws.addCell(new Label(8,2,"项目负责教师",wcf2));
		ws.addCell(new Label(9,2,map.get("fzr"),wcf2));
		
		ws.mergeCells(0,3,1,3);
		ws.addCell(new Label(0,3,"序号",wcf2));
		ws.mergeCells(2,3,3,3);
		ws.addCell(new Label(2,3,"学号",wcf2));
		ws.mergeCells(4,3,5,3);
		ws.addCell(new Label(4,3,"姓名",wcf2));
		ws.mergeCells(6,3,7,3);
		ws.addCell(new Label(6,3,"是否重修",wcf2));
		ws.mergeCells(8,3,9,3);
		ws.addCell(new Label(8,3,"所得学分(总评)",wcf2));
		// 填充内容
		if (gdlist != null && gdlist.size() > 0) {
			for (int i = 0; i < gdlist.size(); i++) {
				String[] info = gdlist.get(i);
				if (info != null && info.length > 0) {
					for (int j = 0; j < info.length; j++) {
						ws.mergeCells(j*2, i + 4,j*2+1, i + 4);
						ws.addCell(new Label(j*2, i + 4, info[j], wcf2));
					}
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * 项目打印统计
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	private ArrayList<String[]> xmDyTj(SztzForm model) throws Exception {
		return dao.xmDytj(model);
	}

	/**
	 * 获取项目名称
	 * @param xn
	 * @param xq
	 * @return
	 */
	public List<HashMap<String, String>> getXmmc(String xn, String xq) {
		String[] inputValue = new String[] { xn, xq };
		String[] outputValue = new String[] { "id","xmmc" };
		return dao.getXmmc(inputValue, outputValue);
	}

	/**
	 * 获取所的学分
	 * @param jxdm
	 * @param xmid 
	 * @return
	 */
	public List<HashMap<String, String>> getSdxf(String jxdm, String xmid) {
		String[] inputValue = new String[] { jxdm,xmid };
		String[] outputValue = new String[] { "jxfs" };
		return dao.getSdxf(inputValue, outputValue);
	}
	
}
