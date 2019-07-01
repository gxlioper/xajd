package xgxt.xljk.mjxy;

import java.lang.reflect.InvocationTargetException;
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

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.dtjs.ntzy.gqtgl.NtzyWpjyForm;
import xgxt.dtjs.ntzy.gqtgl.NtzyWpjyModel;
import xgxt.form.SaveForm;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.MakeQuery;
import xgxt.xljk.mjxy.xlkh.XljkMjxyXlkhModel;
import xgxt.xljk.mjxy.xlkh.XljkMjxyXlkhxsForm;
import xgxt.xsxx.cslg.xsjl.XsxxCslgXsjlDao;
import xgxt.xsxx.cslg.xsjl.XsxxCslgXsjlForm;

public class XljkMjxyService {
	
	/**
	 * 获取报送部门列表
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getBsbmList(){
		XljkMjxyDAO dao=new XljkMjxyDAO();
		return dao.getBsbmList();
	}
	
	/**
	 * 获取心理状态列表
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getXlztList(){
		XljkMjxyDAO dao=new XljkMjxyDAO();
		return dao.getXlztList();
	}
	
	/**
	 * 根据pkValue获取学号
	 */
	public String getXh(String pkValue){
		XljkMjxyDAO dao=new XljkMjxyDAO();
		HashMap<String,String>map= dao.getXh(pkValue);
		return map.get("xh");
	}
	
	
	/**
	 * 闽江学院心理困惑学生批量操作
	 */
	public void plSave(HttpServletRequest request,XljkMjxyXlkhxsForm form) throws Exception{
		GhxyNjzrwhService ghxyNjzrwhService = new GhxyNjzrwhService();
	
		//进行数据操作 的表名
		String realTable ="xljk_mjxy_xlkhzxjlb";
		
		
		//保存查询出的所有信息的XH主键
		String xh=form.getSave_xh();
		String []zxcx=form.getZxcx();//咨询次序
		String []zxsj=form.getZxsj();//咨询时间
		String []zxls=form.getZxls();//咨询老师
		String []zxjl=form.getZxjl();//咨询记录
		String []sfcxzx=form.getSfcxzx();//是否持续咨询
		
		String []xhArr=new String[zxcx.length];
		String []pkValue =new String[zxcx.length];
		//判断操作是否成功
		boolean reslut = false;
		
		for(int i=0;i<zxcx.length;i++){
			pkValue[i]=xh+zxsj;
			xhArr[i]=xh;
		}
		
		String pk = "xh || zxsj ";
		String[] arrzd = new String[] {"xh","zxcx","zxsj","zxls","zxjl","sfcxzx"};
	
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		
				
		XljkMjxyXlkhModel model=new XljkMjxyXlkhModel();
		model.setXh(xhArr);
		model.setSfcxzx(sfcxzx);
		model.setZxcx(zxcx);
		model.setZxjl(zxjl);
		model.setZxls(zxls);
		model.setZxsj(zxsj);
		reslut = ghxyNjzrwhService.saveTyxx(saveForm, model);
		request.setAttribute("result", reslut);
			
	}
	
	/**
	 * 闽江学院心理困惑学生批量操作
	 * 前先删除数据
	 * @throws Exception 
	 */
	public boolean deleteSql(String pkValue) throws Exception{
		XljkMjxyDAO dao=new XljkMjxyDAO();
		return dao.deleteSql(pkValue);
	}
	
	/**
	 * 删除没有与主表关联的数据
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteFbxx() throws Exception{
		XljkMjxyDAO dao=new XljkMjxyDAO();
		return dao.deleteFbxx();
	}
	
	/**
	 * ;导出
	 */
	public void expData(XljkMjxyXlkhxsForm myForm,
			HttpServletRequest request, WritableWorkbook wwb)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		XljkMjxyDAO dao=new XljkMjxyDAO();
		MakeQuery makeQuery=new MakeQuery();
		String []colLikeList={"xh","xm"};
		String []colList={"zydm","bjdm","xydm","nj"};
		
	
		myForm.setXm(myForm.getQuerylike_xm());
		myForm.setXh(myForm.getQuerylike_xh());
		myForm.setNj(myForm.getQueryequals_nj());
		myForm.setBjdm(myForm.getQueryequals_bjdm());
		myForm.setXydm(myForm.getQueryequals_xydm());
		myForm.setZydm(myForm.getQueryequals_zydm());
		makeQuery.makeQuery(colList, colLikeList, myForm);
		List<HashMap<String,String>>list=dao.getTjList(makeQuery.getInputList(),makeQuery.getQueryString().toString()+getSql(myForm).toString());
		
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
			
			ws.addCell(new Label(0,0,"编号",wcfTytle));
			ws.addCell(new Label(1,0,"咨询次序",wcfTytle));
			ws.addCell(new Label(2,0,"咨询时间",wcfTytle));
			ws.addCell(new Label(3,0,"咨询老师",wcfTytle));
			ws.addCell(new Label(4,0,"咨询记录",wcfTytle));
			ws.addCell(new Label(5,0,"是否持续咨询",wcfTytle));
			
			for(int i=0; i <list.size();i++){
				HashMap<String,String>hashMap=list.get(i);
				ws.addCell(new Label(0,i+1,hashMap.get("xh"),wcfTytle));
				ws.addCell(new Label(1,i+1,hashMap.get("zxcx"),wcfTytle));
				ws.addCell(new Label(2,i+1,hashMap.get("zxsj"),wcfTytle));
				ws.addCell(new Label(3,i+1,hashMap.get("zxls"),wcfTytle));
				ws.addCell(new Label(4,i+1,hashMap.get("zxjl"),wcfTytle));
				ws.addCell(new Label(5,i+1,hashMap.get("sfcxzx"),wcfTytle));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 拼接SQL
	 */
	public StringBuilder getSql(XljkMjxyXlkhxsForm myForm){
		String userType=myForm.getUserType();
		String isFdy=myForm.getIsFdy();
		String isBzr=myForm.getIsBzr();
		String userName=myForm.getUserName();
		String userDep=myForm.getUserDep();
		  // 用户为辅导员
		StringBuilder sql=new StringBuilder();
		if ("true".equalsIgnoreCase(isFdy) && !"true".equalsIgnoreCase(isBzr)) {
			sql.append(" and exists (select 1 from view_xsjbxx b  ");
			sql.append(" where a.xh = b.xh and exists (select 1 ");
			sql.append(" from fdybjb c where c.bjdm = b.bjdm ");
			sql.append(" and c.zgh = '" + userName + "')) ");
		}
				// 用户为班主任
		if ("true".equalsIgnoreCase(isBzr) && !"true".equalsIgnoreCase(isFdy)) {
			sql.append(" and exists (select 1 from view_xsjbxx b  ");
			sql.append(" where a.xh = b.xh and exists (select 1 ");
			sql.append(" from bzrbbb c where c.bjdm = b.bjdm ");
			sql.append(" and c.zgh = '" + userName + "')) ");
		}
				// 用户为班主任兼辅导员
		if ("true".equalsIgnoreCase(isBzr) && "true".equalsIgnoreCase(isFdy)) {
			sql.append(" and (exists (select 1 from view_xsjbxx b  ");
			sql.append(" where a.xh = b.xh and exists (select 1 ");
			sql.append(" from fdybjb c where c.bjdm = b.bjdm ");
			sql.append(" and c.zgh = '" + userName + "')) ");
			sql.append(" or exists (select 1 from view_xsjbxx b  ");
			sql.append(" where a.xh = b.xh and exists (select 1 ");
			sql.append(" from bzrbbb c where c.bjdm = b.bjdm ");
			sql.append(" and c.zgh = '" + userName + "'))) ");
		}
				// 用户学院用户
		if ("xy".equalsIgnoreCase(userType)) {
			sql.append(" and exists (select 1 from view_xsjbxx b  ");
			sql.append(" where a.xh = b.xh and b.xydm = '" + userDep + "') ");
		}
		return sql;
		

	}
	
	public List<HashMap<String,String>> getKhxsjlList(String xh){
		XljkMjxyDAO dao=new XljkMjxyDAO();
		return dao.getKhxsjlList(xh);
	} 
	
}
