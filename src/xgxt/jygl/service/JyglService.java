package xgxt.jygl.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.text.*;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.base.DealString;
import xgxt.jygl.dao.XsJyglDao;
import xgxt.jygl.form.JyglForm;
import xgxt.jygl.model.JyglModel;
import xgxt.utils.ExcelMethods;
import xgxt.xljk.lrh_Util.model.stu_info_model;

public class JyglService {
	
	/** 
	 * Method getTableTop_ser 获得表头
	 * @param tableName 表名
	 * @param cols 字段名
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>> getTableTop_ser(String tableName,String[] cols){
		XsJyglDao dao = new XsJyglDao();
		return dao.getTableTop_db(tableName, cols);
	}
	
	/** 
	 * Method getHyxx_ser 就业促进会会员信息
	 * @param model JyglModel对象
	 * @return List<String[]>
	 */
	public List<String[]> getHyxx_ser(JyglModel model){
		XsJyglDao dao = new XsJyglDao();
		return dao.getHyxx_db(model);
	}
	/** 
	 * Method addHyxx_ser 就业促进会会员信息增加
	 * @param model JyglModel对象
	 * @return String
	 * @throws SQLException 
	 */
	public String addHyxx_ser(JyglModel model) throws SQLException{
		XsJyglDao dao = new XsJyglDao();
		return dao.addHyxx_db(model);
	}
	/** 
	 * Method copy_value 复制值
	 * @param model stu_info_model对象
	 * @return JyglForm
	 */
	public JyglForm copy_value(stu_info_model stu_mod) {
		JyglForm myform = new JyglForm();
		myform.setXh(stu_mod.getXH());
		myform.setHyxh(stu_mod.getXH());
		myform.setXm(stu_mod.getXM());
		myform.setXb(stu_mod.getXB());
		myform.setXymc(stu_mod.getXYMC());
		myform.setBjmc(stu_mod.getBJMC());
		myform.setHyxm(stu_mod.getXM());
		return myform;
	}
	/** 
	 * Method deal_toGBK 转码
	 * @param form JyglForm对象
	 * @return void
	 */
	public void deal_toGBK(JyglForm form) {
		form.setXh(DealString.toGBK(form.getXh()));
		form.setHyxh(DealString.toGBK(form.getHyxh()));
		form.setXm(DealString.toGBK(form.getXm()));
		form.setXb(DealString.toGBK(form.getXb()));
		form.setXymc(DealString.toGBK(form.getXymc()));
		form.setBjmc(DealString.toGBK(form.getBjmc()));
		form.setHyxm(DealString.toGBK(form.getHyxm()));
		form.setRq(DealString.toGBK(form.getRq()));
		form.setZcr(DealString.toGBK(form.getZcr()));
		form.setXs(DealString.toGBK(form.getXs()));
		form.setHdxs(DealString.toGBK(form.getHdxs()));
		form.setQthdxs(DealString.toGBK(form.getQthdxs()));
		form.setRs(DealString.toGBK(form.getRs()));
		form.setHdjl(DealString.toGBK(form.getHdjl()));
		form.setHdxg(DealString.toGBK(form.getHdxg()));
		form.setDd(DealString.toGBK(form.getDd()));
		form.setZt(DealString.toGBK(form.getZt()));
		form.setCyxs(DealString.toGBK(form.getCyxs()));
		form.setZw(DealString.toGBK(form.getZw()));
		form.setBz(DealString.toGBK(form.getBz()));
	}	
	/** 
	 * Method oneRecord_ser 获得一条记录的信息
	 * @param tableName 表或视图名
	 * @param col 字段名
	 * @param value 字段值
	 * @return HashMap<String,String>
	 */
	public HashMap<String,String> oneRecord_ser(String tableName,String col,String value) {
		XsJyglDao dao = new XsJyglDao();
		return dao.oneRecord_db(tableName, col, value);
	}
	/** 
	 * Method modiHyxx_db 修改会员信息
	 * @param model JyglModel对象
	 * @return String
	 */
	public String modiHyxx_ser(JyglModel model)
		throws Exception {
		XsJyglDao dao = new XsJyglDao();
		return dao.modiHyxx_db(model);
	}
	/** 
	 * Method delete_ser 删除
	 * @param tableName 表名
	 * @param tableName 主键
	 * @param value 主键值
	 * @return boolean
	 */
	public boolean delete_ser(String tableName,String pk,String value)
		throws Exception {
		XsJyglDao dao = new XsJyglDao();
		return dao.delete_db(tableName,pk,value);
	}
	
	/** 
	 * Method getHdqk_ser 就业促进会会员信息
	 * @param rq 日期
	 * @return List<String[]>
	 */
	public List<String[]> getHdqk_ser(String rq){
		XsJyglDao dao = new XsJyglDao();
		return dao.getHdqk_db(rq);
	}
	/** 
	 * Method getHdxs_ser 就业促进会活动形式
	 * @param tableName 表名
	 * @param dmCol 代码字段名
	 * @param mcCol 名称字段名
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>> getHdxs_ser(String tableName,String dmCol,String mcCol){
		XsJyglDao dao = new XsJyglDao();
		return dao.getHdxs_db(tableName, dmCol, mcCol);
	}
	
	/** 
	 * Method addHdqk_ser 就业促进会会员信息增加
	 * @param model JyglModel对象
	 * @return boolean
	 * @throws SQLException 
	 */
	public boolean addHdqk_ser(JyglModel model) throws SQLException{
		XsJyglDao dao = new XsJyglDao();
		return dao.addHdqk_db(model);
	}
	
	/** 
	 * Method modiHdqk_ser 修改活动信息
	 * @param model JyglModel对象
	 * @return String
	 */
	public boolean modiHdqk_ser(JyglModel model)
		throws Exception {
		XsJyglDao dao = new XsJyglDao();
		return dao.modiHdqk_db(model);
	}
	/** 
	 * Method getRedFlag 修改活动信息
	 * @param String byqxdm
	 * @return String
	 */
	public HashMap<String,String> getRedFlag(String byqxdm){
		HashMap<String,String> map = new HashMap<String,String>();
		if("01".equals(byqxdm)||"02".equals(byqxdm)||"03".equals(byqxdm)||"12".equals(byqxdm)
			||"04".equals(byqxdm)||"13".equals(byqxdm)||"14".equals(byqxdm)
			||"15".equals(byqxdm)||"16".equals(byqxdm)||"17".equals(byqxdm)){
			map.put("byqxdm", "yes");
		}
		if("01".equals(byqxdm)||"13".equals(byqxdm)||"14".equals(byqxdm)
			||"15".equals(byqxdm)||"17".equals(byqxdm)){
			map.put("zgbm", "yes");
		}
		if("01".equals(byqxdm)||"02".equals(byqxdm)||"03".equals(byqxdm)||"12".equals(byqxdm)
			||"04".equals(byqxdm)||"13".equals(byqxdm)||"14".equals(byqxdm)
			||"15".equals(byqxdm)||"17".equals(byqxdm)){
			map.put("zzjgdm", "yes");
		}
		if("01".equals(byqxdm)||"02".equals(byqxdm)||"03".equals(byqxdm)||"12".equals(byqxdm)
			||"04".equals(byqxdm)||"13".equals(byqxdm)||"14".equals(byqxdm)
			||"15".equals(byqxdm)||"17".equals(byqxdm)){
			map.put("dwmc", "yes");
		}
		if("01".equals(byqxdm)||"02".equals(byqxdm)||"03".equals(byqxdm)||"12".equals(byqxdm)
			||"04".equals(byqxdm)||"13".equals(byqxdm)||"14".equals(byqxdm)
			||"15".equals(byqxdm)||"16".equals(byqxdm)||"17".equals(byqxdm)){
			map.put("zzmmdm", "yes");
		}
		if("01".equals(byqxdm)||"02".equals(byqxdm)||"03".equals(byqxdm)||"12".equals(byqxdm)
			||"04".equals(byqxdm)||"13".equals(byqxdm)||"14".equals(byqxdm)
			||"15".equals(byqxdm)||"16".equals(byqxdm)||"17".equals(byqxdm)){
			map.put("lxdz", "yes");
		}
		if("01".equals(byqxdm)||"02".equals(byqxdm)||"03".equals(byqxdm)||"12".equals(byqxdm)
			||"04".equals(byqxdm)||"13".equals(byqxdm)||"14".equals(byqxdm)
			||"15".equals(byqxdm)||"16".equals(byqxdm)||"17".equals(byqxdm)){
			map.put("yb", "yes");
		}
		if("01".equals(byqxdm)||"02".equals(byqxdm)||"03".equals(byqxdm)||"12".equals(byqxdm)
			||"04".equals(byqxdm)||"13".equals(byqxdm)||"14".equals(byqxdm)
			||"15".equals(byqxdm)||"16".equals(byqxdm)||"17".equals(byqxdm)){
			map.put("dh", "yes");
		}
		if("01".equals(byqxdm)||"02".equals(byqxdm)||"03".equals(byqxdm)||"12".equals(byqxdm)
			||"04".equals(byqxdm)||"13".equals(byqxdm)||"14".equals(byqxdm)
			||"15".equals(byqxdm)||"16".equals(byqxdm)||"17".equals(byqxdm)){
			map.put("jzzhlbbzwdm", "yes");
		}
		if("01".equals(byqxdm)||"02".equals(byqxdm)||"03".equals(byqxdm)||"12".equals(byqxdm)
			||"04".equals(byqxdm)||"13".equals(byqxdm)||"14".equals(byqxdm)
			||"15".equals(byqxdm)||"16".equals(byqxdm)||"17".equals(byqxdm)){
			map.put("sydzgbm", "yes");
		}
		if("01".equals(byqxdm)||"02".equals(byqxdm)||"03".equals(byqxdm)||"12".equals(byqxdm)
			||"04".equals(byqxdm)||"13".equals(byqxdm)||"14".equals(byqxdm)
			||"15".equals(byqxdm)||"16".equals(byqxdm)||"17".equals(byqxdm)){
			map.put("dwxzdm", "yes");
		}
		if("01".equals(byqxdm)||"13".equals(byqxdm)||"14".equals(byqxdm)
			||"15".equals(byqxdm)||"17".equals(byqxdm)){
			map.put("dwxzdm2", "yes");
		}
		if("01".equals(byqxdm)||"04".equals(byqxdm)||"16".equals(byqxdm)){
			map.put("dajsd", "yes");
		}
		if("01".equals(byqxdm)||"13".equals(byqxdm)||"14".equals(byqxdm)
			||"15".equals(byqxdm)||"17".equals(byqxdm)){
			map.put("dwdz", "yes");
		}
		if("01".equals(byqxdm)||"04".equals(byqxdm)||"16".equals(byqxdm)){
			map.put("dajsddz", "yes");
		}
		if("01".equals(byqxdm)||"13".equals(byqxdm)||"14".equals(byqxdm)
			||"15".equals(byqxdm)||"17".equals(byqxdm)){
			map.put("dwdh", "yes");
		}
		if("01".equals(byqxdm)||"04".equals(byqxdm)||"16".equals(byqxdm)){
			map.put("dajsdyb", "yes");
		}
		if("01".equals(byqxdm)||"13".equals(byqxdm)||"14".equals(byqxdm)
			||"15".equals(byqxdm)||"17".equals(byqxdm)){
			map.put("dwyb", "yes");
		}
		if("01".equals(byqxdm)||"13".equals(byqxdm)||"14".equals(byqxdm)
			||"15".equals(byqxdm)||"17".equals(byqxdm)){
			map.put("dqlx", "yes");
		}
		if("01".equals(byqxdm)){
			map.put("wyj", "yes");
		}
		if("01".equals(byqxdm)||"13".equals(byqxdm)||"14".equals(byqxdm)
			||"15".equals(byqxdm)||"17".equals(byqxdm)){
			map.put("hyfl", "yes");
		}
		if("01".equals(byqxdm)){
			map.put("dynypjgz", "yes");
		}
		if("01".equals(byqxdm)||"13".equals(byqxdm)||"14".equals(byqxdm)
			||"15".equals(byqxdm)||"17".equals(byqxdm)){
			map.put("zydk", "yes");
		}
		return map;
	}
	
	/** 
	 * Method getTabTopMap 修改活动信息
	 * @param String[] keys
	 * @param String[] values
	 * @return String
	 */
	public HashMap<String,String> getTabTopMap(String[] keys,String[] values){
		HashMap<String,String> map = new HashMap<String,String>();
		if(keys != null){
			for(int i=0;i<keys.length;i++){
				map.put(keys[i], values[i]);
			}
		}
		return map;
	}
	
	/** 
	 * Method getJyphbList 获得就业排行list
	 * @param String nd
	 * @return List<HashMap<String,Object>>
	 */
	public List<HashMap<String,Object>> getJyphbList_ser(String nd){
		XsJyglDao dao = new XsJyglDao();
		return dao.getJyphbList_db(nd);
	}
	
	/** 
	 * Method getXjspList_ser 获得薪金水平list
	 * @param String nd
	 * @return List<HashMap<String,Object>>
	 */
	public List<HashMap<String,Object>> getXjspList_ser(String nd){
		XsJyglDao dao = new XsJyglDao();
		return dao.getXjspList_db(nd);
	}
	
	/** 
	 * Method getZydklList_ser 获得专业对口list
	 * @param String nd
	 * @return List<HashMap<String,Object>>
	 */
	public List<HashMap<String,Object>> getZydklList_ser(String nd){
		XsJyglDao dao = new XsJyglDao();
		return dao.getZydklList_db(nd);
	}
	
	/**
	 * 毕业生就业地域分布情况打印
	 * @param wwb
	 * @return void	 
	 * @throws Exception 
	 * */
	@SuppressWarnings("unchecked")
	public void printDyfb(WritableWorkbook wwb, String bynd) throws Exception {
		XsJyglDao dao = new XsJyglDao();
		List list = dao.dao_Dyfb(bynd);
		
		try {
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);

			ws.addCell(new Label(0, 0, bynd+"年毕业生就业地域分布情况表", wcfTytle));

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
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map = (HashMap<String, String>) list.get(i);
				String jyrs = map.get("jyrs");
				String sqrs = map.get("sq");
				String jqrs = map.get("jq");
				String wdrs = map.get("wd");
				
				float sqbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(sqrs)/ Float.parseFloat(jyrs);
				float jqbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(jqrs)/Float.parseFloat(jyrs);
				float wdbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(wdrs)/Float.parseFloat(jyrs);

				ws.addCell(new Label(0, 4 + (2 * i), map.get("xymc"),
								wcfTytle));
				ws.addCell(new Label(1, 4 + (2 * i), map.get("byrs"),
								wcfTytle));
				ws.addCell(new Label(2, 4 + (2 * i), map.get("jyrs"),
								wcfTytle));
				ws.addCell(new Label(3, 4 + (2 * i), "人数", wcfTytle));
				ws.addCell(new Label(3, 4 + (2 * i) + 1, "百分比", wcfTytle));
				ws.addCell(new Label(4, 4 + (2 * i), map.get("sq"), wcfTytle));
				ws.addCell(new Label(4, 4 + (2 * i) + 1, String.valueOf(new DecimalFormat("#,##0.00").format(sqbfb*100))+"%", wcfTytle));
				ws.addCell(new Label(5, 4 + (2 * i), map.get("jq"), wcfTytle));
				ws.addCell(new Label(5, 4 + (2 * i) + 1, String.valueOf(new DecimalFormat("#,##0.00").format(jqbfb*100))+"%", wcfTytle));
				ws.addCell(new Label(6, 4 + (2 * i), map.get("wd"), wcfTytle));
				ws.addCell(new Label(6, 4 + (2 * i) + 1, String.valueOf(new DecimalFormat("#,##0.00").format(wdbfb*100))+"%", wcfTytle));
				
				ws.mergeCells(0, 4 + (2 * i), 0, 4 + (2 * i) + 1);
				ws.mergeCells(1, 4 + (2 * i), 1, 4 + (2 * i) + 1);
				ws.mergeCells(2, 4 + (2 * i), 2, 4 + (2 * i) + 1);

			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * 毕业生就业单位性质情况打印
	 * @param wwb
	 * @return void	 
	 * @throws Exception 
	 * */
	@SuppressWarnings("unchecked")
	public void printXzqk(WritableWorkbook wwb, String bynd) throws Exception {
		XsJyglDao dao = new XsJyglDao();
		List list = dao.dao_Xzqk(bynd);
		
		try {
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);

			ws.addCell(new Label(0, 0, bynd+"年毕业生就业单位性质情况表", wcfTytle));

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
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map = (HashMap<String, String>) list.get(i);
				String jyrs = map.get("jyrs");
				String gyrs = map.get("gy");
				String jtrs = map.get("jt");
				String myrs = map.get("my");
				String syrs = map.get("sy");
				String gfzrs = map.get("gfz");
				String hzrs = map.get("hz");
				String dzrs = map.get("dz");
				
				float gybfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(gyrs)/ Float.parseFloat(jyrs);
				float jtbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(jtrs)/Float.parseFloat(jyrs);
				float mybfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(myrs)/Float.parseFloat(jyrs);
				float sybfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(syrs)/ Float.parseFloat(jyrs);
				float gfzbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(gfzrs)/Float.parseFloat(jyrs);
				float hzbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(hzrs)/Float.parseFloat(jyrs);
				float dzbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(dzrs)/ Float.parseFloat(jyrs);

				ws.addCell(new Label(0, 4 + (2 * i), map.get("xymc"),
								wcfTytle));
				ws.addCell(new Label(1, 4 + (2 * i), map.get("byrs"),
								wcfTytle));
				ws.addCell(new Label(2, 4 + (2 * i), map.get("jyrs"),
								wcfTytle));
				ws.addCell(new Label(3, 4 + (2 * i), "人数", wcfTytle));
				ws.addCell(new Label(3, 4 + (2 * i) + 1, "百分比", wcfTytle));
				
				ws.addCell(new Label(4, 4 + (2 * i), map.get("gy"), wcfTytle));
				ws.addCell(new Label(4, 4 + (2 * i) + 1, String.valueOf(new DecimalFormat("#,##0.00").format(gybfb*100))+"%", wcfTytle));
				
				ws.addCell(new Label(5, 4 + (2 * i), map.get("jt"), wcfTytle));
				ws.addCell(new Label(5, 4 + (2 * i) + 1, String.valueOf(new DecimalFormat("#,##0.00").format(jtbfb*100))+"%", wcfTytle));
				
				ws.addCell(new Label(6, 4 + (2 * i), map.get("my"), wcfTytle));
				ws.addCell(new Label(6, 4 + (2 * i) + 1, String.valueOf(new DecimalFormat("#,##0.00").format(mybfb*100))+"%", wcfTytle));
				
				ws.addCell(new Label(7, 4 + (2 * i), map.get("sy"), wcfTytle));
				ws.addCell(new Label(7, 4 + (2 * i) + 1, String.valueOf(new DecimalFormat("#,##0.00").format(sybfb*100))+"%", wcfTytle));
				
				ws.addCell(new Label(8, 4 + (2 * i), map.get("gfz"), wcfTytle));
				ws.addCell(new Label(8, 4 + (2 * i) + 1, String.valueOf(new DecimalFormat("#,##0.00").format(gfzbfb*100))+"%", wcfTytle));
				
				ws.addCell(new Label(9, 4 + (2 * i), map.get("hz"), wcfTytle));
				ws.addCell(new Label(9, 4 + (2 * i) + 1, String.valueOf(new DecimalFormat("#,##0.00").format(hzbfb*100))+"%", wcfTytle));
				
				ws.addCell(new Label(10, 4 + (2 * i), map.get("dz"), wcfTytle));
				ws.addCell(new Label(10, 4 + (2 * i) + 1, String.valueOf(new DecimalFormat("#,##0.00").format(dzbfb*100))+"%", wcfTytle));
				
				ws.mergeCells(0, 4 + (2 * i), 0, 4 + (2 * i) + 1);
				ws.mergeCells(1, 4 + (2 * i), 1, 4 + (2 * i) + 1);
				ws.mergeCells(2, 4 + (2 * i), 2, 4 + (2 * i) + 1);

			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * 毕业生就业行业分布情况打印
	 * @param wwb
	 * @return void	 
	 * @throws Exception 
	 * */
	@SuppressWarnings("unchecked")
	public void printHyfb(WritableWorkbook wwb, String bynd) throws Exception {
		XsJyglDao dao = new XsJyglDao();
		List list = dao.dao_Hyfb(bynd);
		
		try {
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);

			ws.addCell(new Label(0, 0, bynd+"年毕业生就业行业分布情况表", wcfTytle));

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
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map = (HashMap<String, String>) list.get(i);
				String jyrs = map.get("jyrs");
				
				String zzyrs = map.get("zzy");
				String shfwrs = map.get("shfw");
				String ydtxrs = map.get("ydtx");
				String jrbxrs = map.get("jrbx");
				String jykjrs = map.get("jykj");
				String pflszrs = map.get("pfls");
				String jzyrs = map.get("jzy");
				String jtysrs = map.get("jtys");
				String gjjgrs = map.get("gjjg");
				String qtrs = map.get("qt");

				
				float zzybfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(zzyrs)/ Float.parseFloat(jyrs);
				float shfwbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(shfwrs)/Float.parseFloat(jyrs);
				float ydtxbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(ydtxrs)/Float.parseFloat(jyrs);
				float jrbxbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(jrbxrs)/ Float.parseFloat(jyrs);
				float jykjbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(jykjrs)/Float.parseFloat(jyrs);
				float pflsbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(pflszrs)/Float.parseFloat(jyrs);
				float jzybfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(jzyrs)/Float.parseFloat(jyrs);
				float jtysbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(jtysrs)/ Float.parseFloat(jyrs);
				float gjjgbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(gjjgrs)/Float.parseFloat(jyrs);
				float qtbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(qtrs)/ Float.parseFloat(jyrs);

				ws.addCell(new Label(0, 4 + (2 * i), map.get("xymc"),
								wcfTytle));
				ws.addCell(new Label(1, 4 + (2 * i), map.get("byrs"),
								wcfTytle));
				ws.addCell(new Label(2, 4 + (2 * i), map.get("jyrs"),
								wcfTytle));
				ws.addCell(new Label(3, 4 + (2 * i), "人数", wcfTytle));
				ws.addCell(new Label(3, 4 + (2 * i) + 1, "百分比", wcfTytle));

				ws.addCell(new Label(4, 4 + (2 * i), map.get("zzy"), wcfTytle));
				ws.addCell(new Label(4, 4 + (2 * i) + 1, String.valueOf(new DecimalFormat("#,##0.00").format(zzybfb*100))+"%", wcfTytle));
				
				ws.addCell(new Label(5, 4 + (2 * i), map.get("shfw"), wcfTytle));
				ws.addCell(new Label(5, 4 + (2 * i) + 1, String.valueOf(new DecimalFormat("#,##0.00").format(shfwbfb*100))+"%", wcfTytle));
				
				ws.addCell(new Label(6, 4 + (2 * i), map.get("ydtx"), wcfTytle));
				ws.addCell(new Label(6, 4 + (2 * i) + 1, String.valueOf(new DecimalFormat("#,##0.00").format(ydtxbfb*100))+"%", wcfTytle));
				
				ws.addCell(new Label(7, 4 + (2 * i), map.get("jrbx"), wcfTytle));
				ws.addCell(new Label(7, 4 + (2 * i) + 1, String.valueOf(new DecimalFormat("#,##0.00").format(jrbxbfb*100))+"%", wcfTytle));
				
				ws.addCell(new Label(8, 4 + (2 * i), map.get("jykj"), wcfTytle));
				ws.addCell(new Label(8, 4 + (2 * i) + 1,String.valueOf(new DecimalFormat("#,##0.00").format(jykjbfb*100))+"%", wcfTytle));
				
				ws.addCell(new Label(9, 4 + (2 * i), map.get("pfls"), wcfTytle));
				ws.addCell(new Label(9, 4 + (2 * i) + 1, String.valueOf(new DecimalFormat("#,##0.00").format(pflsbfb*100))+"%", wcfTytle));
				
				ws.addCell(new Label(10, 4 + (2 * i), map.get("jzy"), wcfTytle));
				ws.addCell(new Label(10, 4 + (2 * i) + 1, String.valueOf(new DecimalFormat("#,##0.00").format(jzybfb*100))+"%", wcfTytle));
				
				ws.addCell(new Label(11, 4 + (2 * i), map.get("jtys"), wcfTytle));
				ws.addCell(new Label(11, 4 + (2 * i) + 1, String.valueOf(new DecimalFormat("#,##0.00").format(jtysbfb*100))+"%", wcfTytle));
				
				ws.addCell(new Label(12, 4 + (2 * i), map.get("gjjg"), wcfTytle));
				ws.addCell(new Label(12, 4 + (2 * i) + 1, String.valueOf(new DecimalFormat("#,##0.00").format(gjjgbfb*100))+"%", wcfTytle));
				
				ws.addCell(new Label(13, 4 + (2 * i), map.get("qt"), wcfTytle));
				ws.addCell(new Label(13, 4 + (2 * i) + 1, String.valueOf(new DecimalFormat("#,##0.00").format(qtbfb*100))+"%", wcfTytle));
				
				ws.mergeCells(0, 4 + (2 * i), 0, 4 + (2 * i) + 1);
				ws.mergeCells(1, 4 + (2 * i), 1, 4 + (2 * i) + 1);
				ws.mergeCells(2, 4 + (2 * i), 2, 4 + (2 * i) + 1);

			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 毕业生就业地域分布情况list
	 * @param String bynd	 
	 * @throws Exception 
	 * */
	@SuppressWarnings("unchecked")
	public List getDyfbList(String bynd) throws Exception {
		XsJyglDao dao = new XsJyglDao();
		List list = dao.dao_Dyfb(bynd);
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map = (HashMap<String, String>) list.get(i);
			String jyrs = map.get("jyrs");
			String sqrs = map.get("sq");
			String jqrs = map.get("jq");
			String wdrs = map.get("wd");
			
			float sqbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(sqrs)/ Float.parseFloat(jyrs);
			float jqbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(jqrs)/Float.parseFloat(jyrs);
			float wdbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(wdrs)/Float.parseFloat(jyrs);
			map.put("sqbfb", String.valueOf(new DecimalFormat("#,##0.00").format(sqbfb*100))+"%");
			map.put("jqbfb", String.valueOf(new DecimalFormat("#,##0.00").format(jqbfb*100))+"%");
			map.put("wdbfb", String.valueOf(new DecimalFormat("#,##0.00").format(wdbfb*100))+"%");
		}
		return list;
	}
	
	/**
	 * 毕业生就业单位性质情况list
	 * @param String bynd	 
	 * @throws Exception 
	 * */
	@SuppressWarnings("unchecked")
	public List getDwxzqkList(String bynd) throws Exception {
		XsJyglDao dao = new XsJyglDao();
		List list = dao.dao_Xzqk(bynd);
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map = (HashMap<String, String>) list.get(i);
			String jyrs = map.get("jyrs");
			String gyrs = map.get("gy");
			String jtrs = map.get("jt");
			String myrs = map.get("my");
			String syrs = map.get("sy");
			String gfzrs = map.get("gfz");
			String hzrs = map.get("hz");
			String dzrs = map.get("dz");
			
			float gybfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(gyrs)/ Float.parseFloat(jyrs);
			float jtbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(jtrs)/Float.parseFloat(jyrs);
			float mybfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(myrs)/Float.parseFloat(jyrs);
			float sybfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(syrs)/ Float.parseFloat(jyrs);
			float gfzbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(gfzrs)/Float.parseFloat(jyrs);
			float hzbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(hzrs)/Float.parseFloat(jyrs);
			float dzbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(dzrs)/ Float.parseFloat(jyrs);
			map.put("gybfb", String.valueOf(new DecimalFormat("#,##0.00").format(gybfb*100))+"%");
			map.put("jtbfb", String.valueOf(new DecimalFormat("#,##0.00").format(jtbfb*100))+"%");
			map.put("mybfb", String.valueOf(new DecimalFormat("#,##0.00").format(mybfb*100))+"%");
			map.put("sybfb", String.valueOf(new DecimalFormat("#,##0.00").format(sybfb*100))+"%");
			map.put("gfzbfb", String.valueOf(new DecimalFormat("#,##0.00").format(gfzbfb*100))+"%");
			map.put("hzbfb", String.valueOf(new DecimalFormat("#,##0.00").format(hzbfb*100))+"%");
			map.put("dzbfb", String.valueOf(new DecimalFormat("#,##0.00").format(dzbfb*100))+"%");
		}
		return list;
	}
	
	/**
	 * 毕业生就业行业分布情况list
	 * @param String bynd	 
	 * @throws Exception 
	 * */
	@SuppressWarnings("unchecked")
	public List getHyfbList(String bynd) throws Exception {
		XsJyglDao dao = new XsJyglDao();
		List list = dao.dao_Hyfb(bynd);
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map = (HashMap<String, String>) list.get(i);
			String jyrs = map.get("jyrs");
			
			String zzyrs = map.get("zzy");
			String shfwrs = map.get("shfw");
			String ydtxrs = map.get("ydtx");
			String jrbxrs = map.get("jrbx");
			String jykjrs = map.get("jykj");
			String pflszrs = map.get("pfls");
			String jzyrs = map.get("jzy");
			String jtysrs = map.get("jtys");
			String gjjgrs = map.get("gjjg");
			String qtrs = map.get("qt");

			
			float zzybfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(zzyrs)/ Float.parseFloat(jyrs);
			float shfwbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(shfwrs)/Float.parseFloat(jyrs);
			float ydtxbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(ydtxrs)/Float.parseFloat(jyrs);
			float jrbxbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(jrbxrs)/ Float.parseFloat(jyrs);
			float jykjbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(jykjrs)/Float.parseFloat(jyrs);
			float pflsbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(pflszrs)/Float.parseFloat(jyrs);
			float jzybfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(jzyrs)/Float.parseFloat(jyrs);
			float jtysbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(jtysrs)/ Float.parseFloat(jyrs);
			float gjjgbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(gjjgrs)/Float.parseFloat(jyrs);
			float qtbfb = "0".equalsIgnoreCase(jyrs) ? 0 : Float.parseFloat(qtrs)/ Float.parseFloat(jyrs);
			map.put("zzybfb", String.valueOf(new DecimalFormat("#,##0.00").format(zzybfb*100))+"%");
			map.put("shfwbfb", String.valueOf(new DecimalFormat("#,##0.00").format(shfwbfb*100))+"%");
			map.put("ydtxbfb", String.valueOf(new DecimalFormat("#,##0.00").format(ydtxbfb*100))+"%");
			map.put("jrbxbfb", String.valueOf(new DecimalFormat("#,##0.00").format(jrbxbfb*100))+"%");
			map.put("jykjbfb", String.valueOf(new DecimalFormat("#,##0.00").format(jykjbfb*100))+"%");
			map.put("pflsbfb", String.valueOf(new DecimalFormat("#,##0.00").format(pflsbfb*100))+"%");
			map.put("jzybfb", String.valueOf(new DecimalFormat("#,##0.00").format(jzybfb*100))+"%");
			map.put("jtysbfb", String.valueOf(new DecimalFormat("#,##0.00").format(jtysbfb*100))+"%");
			map.put("gjjgbfb", String.valueOf(new DecimalFormat("#,##0.00").format(gjjgbfb*100))+"%");
			map.put("qtbfb", String.valueOf(new DecimalFormat("#,##0.00").format(qtbfb*100))+"%");
		}
		return list;
	}
	/**
	 * 毕业生就业专业对口率统计打印
	 * @param wwb
	 * @return void	 
	 * @throws Exception 
	 * */
	@SuppressWarnings("unchecked")
	public void printZydkl(WritableWorkbook wwb, String bynd) throws Exception {
		XsJyglDao dao = new XsJyglDao();
		List list = dao.dao_Zydkl(bynd);
		
		try {
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);

			ws.addCell(new Label(0, 0, bynd+"年毕业生就业专业对口率统计表", wcfTytle));

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
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			
			int sum = 0;
			int allCyrs = 0;
			int allBzyrs = 0;
			int allKzyrs = 0;
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map = (HashMap<String, String>) list.get(i);
				
				String cyrs = map.get("cyrs");
				String bzyrs = map.get("bzyrs");
				String kzyrs = map.get("kzyrs");
				String xymc1 = map.get("xymc");
				String xymc2 = "";
				String sumcyrs = "0";
				String sumbzyrs = "0";
				String sumkzyrs = "0";
				
				allCyrs+=Integer.parseInt(cyrs);
				allBzyrs+=Integer.parseInt(bzyrs);
				allKzyrs+=Integer.parseInt(kzyrs);
				boolean isSum = false;
				
				if (i > 0) {
					HashMap<String, String> mapV = (HashMap<String, String>) list.get(i - 1);
					sumcyrs = mapV.get("sumcyrs");
					sumbzyrs = mapV.get("sumbzyrs");
					sumkzyrs = mapV.get("sumkzyrs");
					xymc2=mapV.get("xymc");
				}
				
				if(!xymc2.equals(xymc1)&& !"".equalsIgnoreCase(xymc2)){
					isSum = true;
				}
				float zydkl = "0".equalsIgnoreCase(cyrs)
						|| "0".equalsIgnoreCase(bzyrs) ? 0 : Float
						.parseFloat(bzyrs)
						/ Float.parseFloat(cyrs);

				if (isSum) {
					float sumzydkl = "0".equalsIgnoreCase(sumcyrs)
							|| "0".equalsIgnoreCase(sumbzyrs) ? 0 : Float
							.parseFloat(sumbzyrs)
							/ Float.parseFloat(sumcyrs);

					WritableFont wf = new WritableFont(WritableFont.TIMES); // 构造字体格式
					WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式
					wf.setBoldStyle(WritableFont.BOLD); // 设置字体格式(非粗体)
					wf.setUnderlineStyle(UnderlineStyle.NO_UNDERLINE); // 设置字体格式(无下划线)
					wf.setPointSize(10); // 设置字体格式(大小)
					wcf.setFont(wf); // 设置指定字体格式的单元格格式
					wcf.setAlignment(Alignment.CENTRE); // 指定格式设置字符左右居中
					wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 指定格式设置字符上下居中	
					wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
					
					ws.addCell(new Label(0, 4 + i + sum, xymc2, wcf));
					ws.addCell(new Label(1, 4 + i + sum, "小计", wcf));
					ws.addCell(new Label(2, 4 + i + sum, sumcyrs, wcf));
					ws.addCell(new Label(3, 4 + i + sum, sumbzyrs, wcf));
					ws.addCell(new Label(4, 4 + i + sum, sumkzyrs, wcf));
					ws.addCell(new Label(5, 4 + i + sum,String.valueOf((zydkl==0)?"-":new DecimalFormat("#,##0.00").format(sumzydkl*100))+"%", wcf));
					sum++;
				} 
				
				ws.addCell(new Label(0, 4 + i + sum, map.get("xymc"),wcfTytle));
				ws.addCell(new Label(1, 4 + i + sum, map.get("zymc"),wcfTytle));		
				ws.addCell(new Label(2, 4 + i + sum, map.get("cyrs"), wcfTytle));
				ws.addCell(new Label(3, 4 + i + sum, map.get("bzyrs"), wcfTytle));
				ws.addCell(new Label(4, 4 + i + sum, map.get("kzyrs"), wcfTytle));
				ws.addCell(new Label(5, 4 + i + sum, String.valueOf((zydkl==0)?"-":new DecimalFormat("#,##0.00").format(zydkl*100))+"%", wcfTytle));

				if (i == list.size() - 1) {
					float sumzydkl = "0".equalsIgnoreCase(sumcyrs) || "0".equalsIgnoreCase(sumbzyrs) ? 0 : Float.parseFloat(sumbzyrs)
							/ Float.parseFloat(sumcyrs);
					float allzydkl = allCyrs == 0 || allBzyrs == 0 ? 0 : Float.parseFloat(String.valueOf(allBzyrs))
							/ Float.parseFloat(String.valueOf(allCyrs));
					sum++;
					
					WritableFont wf = new WritableFont(WritableFont.TIMES); // 构造字体格式
					WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式
					wf.setBoldStyle(WritableFont.BOLD); // 设置字体格式(非粗体)
					wf.setUnderlineStyle(UnderlineStyle.NO_UNDERLINE); // 设置字体格式(无下划线)
					wf.setPointSize(10); // 设置字体格式(大小)
					wcf.setFont(wf); // 设置指定字体格式的单元格格式
					wcf.setAlignment(Alignment.CENTRE); // 指定格式设置字符左右居中
					wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 指定格式设置字符上下居中	
					wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
					
					ws.addCell(new Label(0, 4 + i + sum, xymc2, wcf));
					ws.addCell(new Label(1, 4 + i + sum, "小计", wcf));
					ws.addCell(new Label(2, 4 + i + sum, sumcyrs, wcf));
					ws.addCell(new Label(3, 4 + i + sum, sumbzyrs, wcf));
					ws.addCell(new Label(4, 4 + i + sum, sumkzyrs, wcf));
					ws.addCell(new Label(5, 4 + i + sum, String.valueOf((zydkl == 0) ? "-" : new DecimalFormat("#,##0.00").format(sumzydkl * 100))
							+ "%", wcf));
					
					ws.addCell(new Label(0, 4 + i + sum + 1, "合计", wcf));
					ws.addCell(new Label(1, 4 + i + sum + 1, "", wcf));
					ws.addCell(new Label(2, 4 + i + sum + 1, String.valueOf(allCyrs), wcf));
					ws.addCell(new Label(3, 4 + i + sum + 1, String.valueOf(allBzyrs), wcf));
					ws.addCell(new Label(4, 4 + i + sum + 1, String.valueOf(allKzyrs), wcf));	
					
					WritableFont wf1 = new WritableFont(WritableFont.TIMES); // 构造字体格式
					WritableCellFormat wcf1 = new WritableCellFormat(); // 构造单元格格式
					wf1.setBoldStyle(WritableFont.BOLD); // 设置字体格式(非粗体)
					wf1.setUnderlineStyle(UnderlineStyle.NO_UNDERLINE); // 设置字体格式(无下划线)
					wf1.setColour(Colour.RED);
					wf1.setPointSize(10); // 设置字体格式(大小)
					wcf1.setFont(wf1); // 设置指定字体格式的单元格格式
					wcf1.setAlignment(Alignment.CENTRE); // 指定格式设置字符左右居中
					wcf1.setVerticalAlignment(VerticalAlignment.CENTRE); // 指定格式设置字符上下居中	
					wcf1.setBorder(Border.ALL, BorderLineStyle.THIN);
					
					ws.addCell(new Label(5, 4 + i + sum + 1, String.valueOf((allzydkl == 0) ? "-" : new DecimalFormat("#,##0.00").format(allzydkl * 100))
							+ "%", wcf1));
				}
			}
			
			int m = 1;
			boolean n = false;
			
			for (int i = 0; i <= list.size() + sum; i++) {
				String a3 = "";
				String a4 = "";
				
				WritableCell a1 = ws.getWritableCell(0, 4 + i);
				
				if (i > 0) {
					WritableCell a2 = ws.getWritableCell(0, 4 + i - 1);
					a4 = a2.getContents();
				}
				a3 = a1.getContents();
				
				if (a3.equals(a4)) {
					m++;
					n = true;
				}
				
				if ((!a3.equals(a4)) && n) {
					ws.mergeCells(0, 4 + i - m, 0, 4 + i - 1);
					n = false;
					m = 1;
				}

			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 毕业生薪金统计表打印
	 * @param wwb
	 * @return void	 
	 * @throws Exception 
	 * */
	@SuppressWarnings("unchecked")
	public void printByxj(WritableWorkbook wwb, String bynd) throws Exception {
		XsJyglDao dao = new XsJyglDao();
		List list = dao.getXjspList_db(bynd);
		
		try {
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);

			ws.addCell(new Label(0, 0, bynd+"年毕业生薪金统计表", wcfTytle));

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
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			
			int num = 0;
			int sumZyrs = 0;
			int sumPjgz = 0;
			int sumXj1 = 0;
			int sumXj2 = 0;
			int sumXj3 = 0;
			int sumXj4 = 0;
			int sumXj5 = 0;
			int sumXj6 = 0;
			
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> map = (HashMap<String, Object>) list.get(i);	
				List<HashMap<String,String>> xyList = new ArrayList<HashMap<String,String>>();
				
				xyList = (List<HashMap<String, String>>) map.get("xyzymap");
				
				for (int j = 0; j < xyList.size(); j++) {
					HashMap<String, String> rs = (HashMap<String, String>) xyList.get(j);
					
					sumZyrs += Integer.parseInt(rs.get("zyrs"));
					sumXj1 += Integer.parseInt(rs.get("xj1"));
					sumXj2 += Integer.parseInt(rs.get("xj2"));
					sumXj3 += Integer.parseInt(rs.get("xj3"));
					sumXj4 += Integer.parseInt(rs.get("xj4"));
					sumXj5 += Integer.parseInt(rs.get("xj5"));
					sumXj6 += Integer.parseInt(rs.get("xj6"));
					
					ws.addCell(new Label(0, 4 + j + num, String.valueOf(rs.get("xymc")), wcfTytle));
					ws.addCell(new Label(1, 4 + j + num, String.valueOf(rs.get("zymc")), wcfTytle));
					ws.addCell(new Label(2, 4 + j + num, String.valueOf(rs.get("zyrs")), wcfTytle));
					ws.addCell(new Label(3, 4 + j + num, String.valueOf(rs.get("xj1")), wcfTytle));
					ws.addCell(new Label(4, 4 + j + num, String.valueOf(rs.get("xj2")), wcfTytle));
					ws.addCell(new Label(5, 4 + j + num, String.valueOf(rs.get("xj3")), wcfTytle));
					ws.addCell(new Label(6, 4 + j + num, String.valueOf(rs.get("xj4")), wcfTytle));
					ws.addCell(new Label(7, 4 + j + num, String.valueOf(rs.get("xj5")), wcfTytle));
					ws.addCell(new Label(8, 4 + j + num, String.valueOf(rs.get("xj6")), wcfTytle));
					ws.addCell(new Label(9, 4 + j + num, String.valueOf(rs.get("zydynypjgz")), wcfTytle));
				}
				
				sumPjgz += (Integer)map.get("xydynypjgz");
				
				WritableFont wf = new WritableFont(WritableFont.TIMES); // 构造字体格式
				WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式
				wf.setBoldStyle(WritableFont.BOLD); // 设置字体格式(非粗体)
				wf.setUnderlineStyle(UnderlineStyle.NO_UNDERLINE); // 设置字体格式(无下划线)
				wf.setPointSize(10); // 设置字体格式(大小)
				wcf.setFont(wf); // 设置指定字体格式的单元格格式
				wcf.setAlignment(Alignment.CENTRE); // 指定格式设置字符左右居中
				wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 指定格式设置字符上下居中	
				wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
				
				ws.addCell(new Label(0, 4 + xyList.size() + num, String.valueOf(map.get("xymc")), wcf));
				ws.addCell(new Label(1, 4 + xyList.size() + num, "小结", wcf));
				ws.addCell(new Label(2, 4 + xyList.size() + num, String.valueOf(map.get("xyrs")), wcf));
				ws.addCell(new Label(3, 4 + xyList.size() + num, String.valueOf(map.get("xj1")), wcf));
				ws.addCell(new Label(4, 4 + xyList.size() + num, String.valueOf(map.get("xj2")), wcf));
				ws.addCell(new Label(5, 4 + xyList.size() + num, String.valueOf(map.get("xj3")), wcf));
				ws.addCell(new Label(6, 4 + xyList.size() + num, String.valueOf(map.get("xj4")), wcf));
				ws.addCell(new Label(7, 4 + xyList.size() + num, String.valueOf(map.get("xj5")), wcf));
				ws.addCell(new Label(8, 4 + xyList.size() + num, String.valueOf(map.get("xj6")), wcf));
				ws.addCell(new Label(9, 4 + xyList.size() + num, String.valueOf(map.get("xydynypjgz")), wcf));
				
				ws.mergeCells(0, 4 + num, 0, 4 + num + xyList.size());
				
				if (i == list.size() - 1) {
					
					int pjxydynypjgz = sumPjgz / list.size();
					
					ws.addCell(new Label(0, 4 + xyList.size() + num + 1, "合计", wcf));
					ws.addCell(new Label(1, 4 + xyList.size() + num + 1, "", wcf));
					ws.addCell(new Label(2, 4 + xyList.size() + num + 1, String.valueOf(sumZyrs), wcf));
					ws.addCell(new Label(3, 4 + xyList.size() + num + 1, String.valueOf(sumXj1), wcf));
					ws.addCell(new Label(4, 4 + xyList.size() + num + 1, String.valueOf(sumXj2), wcf));
					ws.addCell(new Label(5, 4 + xyList.size() + num + 1, String.valueOf(sumXj3), wcf));
					ws.addCell(new Label(6, 4 + xyList.size() + num + 1, String.valueOf(sumXj4), wcf));
					ws.addCell(new Label(7, 4 + xyList.size() + num + 1, String.valueOf(sumXj5), wcf));
					ws.addCell(new Label(8, 4 + xyList.size() + num + 1, String.valueOf(sumXj6), wcf));
					ws.addCell(new Label(9, 4 + xyList.size() + num + 1, String.valueOf(pjxydynypjgz), wcf));
				}
				
				num += xyList.size() + 1;
				
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 毕业生就业排行榜打印
	 * @param wwb
	 * @return void	 
	 * @throws Exception 
	 * */
	@SuppressWarnings("unchecked")
	public void printJyphb(WritableWorkbook wwb, String bynd) throws Exception {
		XsJyglDao dao = new XsJyglDao();
		List list = dao.getJyphbList_db(bynd);
		
		try {
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);

			ws.addCell(new Label(0, 0, bynd+"年就业排行榜表", wcfTytle));

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
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			
			float sumZyrs = 0;
			float sumQyrs = 0;
			float sumJyrs = 0;
			int num = 0;
			
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> map = (HashMap<String, Object>) list.get(i);	
				List<HashMap<String,String>> xyList = new ArrayList<HashMap<String,String>>();
				
				xyList = (List<HashMap<String, String>>) map.get("xyzymap");
				
				for (int j = 0; j < xyList.size(); j++) {
					HashMap<String, String> rs = (HashMap<String, String>) xyList.get(j);
					
					sumZyrs += Float.parseFloat(rs.get("zyrs"));
					sumQyrs += Float.parseFloat(rs.get("qyrs"));
					sumJyrs += Float.parseFloat(rs.get("jyrs"));
					
					ws.addCell(new Label(0, 3 + j + num, String.valueOf(rs.get("xymc")), wcfTytle));
					ws.addCell(new Label(1, 3 + j + num, String.valueOf(rs.get("zymc")), wcfTytle));
					ws.addCell(new Label(2, 3 + j + num, String.valueOf(rs.get("zyrs")), wcfTytle));
					ws.addCell(new Label(3, 3 + j + num, String.valueOf(rs.get("qyrs")), wcfTytle));
					ws.addCell(new Label(4, 3 + j + num, String.valueOf(rs.get("zyqyl")), wcfTytle));
					ws.addCell(new Label(5, 3 + j + num, String.valueOf(rs.get("qylmc")), wcfTytle));
					ws.addCell(new Label(6, 3 + j + num, String.valueOf(rs.get("jyrs")), wcfTytle));
					ws.addCell(new Label(7, 3 + j + num, String.valueOf(rs.get("zyjyl")), wcfTytle));
					ws.addCell(new Label(8, 3 + j + num, String.valueOf(rs.get("jylmc")), wcfTytle));
				}		
				
				WritableFont wf = new WritableFont(WritableFont.TIMES); // 构造字体格式
				WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式
				wf.setBoldStyle(WritableFont.BOLD); // 设置字体格式(非粗体)
				wf.setUnderlineStyle(UnderlineStyle.NO_UNDERLINE); // 设置字体格式(无下划线)
				wf.setPointSize(10); // 设置字体格式(大小)
				wcf.setFont(wf); // 设置指定字体格式的单元格格式
				wcf.setAlignment(Alignment.CENTRE); // 指定格式设置字符左右居中
				wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 指定格式设置字符上下居中	
				wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
				
				ws.addCell(new Label(0, 3 + xyList.size() + num, String.valueOf(map.get("xymc")), wcf));
				ws.addCell(new Label(1, 3 + xyList.size() + num, "小结", wcf));
				ws.addCell(new Label(2, 3 + xyList.size() + num, String.valueOf(map.get("xyrs")), wcf));
				ws.addCell(new Label(3, 3 + xyList.size() + num, String.valueOf(map.get("xyqyrs")), wcf));
				ws.addCell(new Label(4, 3 + xyList.size() + num, String.valueOf(map.get("xyqyl")), wcf));
				ws.addCell(new Label(5, 3 + xyList.size() + num, String.valueOf(map.get("qylmc")), wcf));
				ws.addCell(new Label(6, 3 + xyList.size() + num, String.valueOf(map.get("xyjyrs")), wcf));
				ws.addCell(new Label(7, 3 + xyList.size() + num, String.valueOf(map.get("xyjyl")), wcf));
				ws.addCell(new Label(8, 3 + xyList.size() + num, String.valueOf(map.get("jylmc")), wcf));
				
				ws.mergeCells(0, 3 + num, 0, 3 + num + xyList.size());
				ws.mergeCells(5, 3 + num, 5, 3 + num + xyList.size());
				ws.mergeCells(8, 3 + num, 8, 3 + num + xyList.size());
				
				if (i == list.size() - 1) {
					
					float jybfb = sumZyrs == 0 ? 0 : sumJyrs/sumZyrs;
					float qybfb = sumZyrs == 0 ? 0 : sumQyrs/sumZyrs;
					
					ws.addCell(new Label(0, 4 + xyList.size() + num, "合计", wcf));
					ws.addCell(new Label(1, 4 + xyList.size() + num, "", wcf));
					ws.addCell(new Label(2, 4 + xyList.size() + num, String.valueOf(sumZyrs), wcf));
					ws.addCell(new Label(3, 4 + xyList.size() + num, String.valueOf(sumQyrs), wcf));
					ws.addCell(new Label(4, 4 + xyList.size() + num, new DecimalFormat("#,##0.00").format(qybfb * 100)+"%", wcf));
					ws.addCell(new Label(5, 4 + xyList.size() + num, "", wcf));
					ws.addCell(new Label(6, 4 + xyList.size() + num, String.valueOf(sumJyrs), wcf));
					ws.addCell(new Label(7, 4 + xyList.size() + num, new DecimalFormat("#,##0.00").format(jybfb * 100)+"%", wcf));
					ws.addCell(new Label(8, 4 + xyList.size() + num, "", wcf));
				}

				num += xyList.size() + 1;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 获得就业排行榜总计
	 * @param List
	 * @return HashMap<String,String>	 
	 * @throws Exception 
	 * */
	public HashMap<String,String> getJyphTotalMap(List<HashMap<String,Object>> list){
		HashMap<String,String> map = new HashMap<String,String>();
		int xyrs,xyqyrs,xyjyrs,xxrs = 0,xxqyrs = 0,xxjyrs = 0;
		if(list != null){
			for(int i = 0;i<list.size();i++){
				xyrs = Integer.parseInt(list.get(i).get("xyrs").toString());
				xyqyrs = Integer.parseInt(list.get(i).get("xyqyrs").toString());
				xyjyrs = Integer.parseInt(list.get(i).get("xyqyrs").toString());
				xxrs += xyrs;
				xxqyrs += xyqyrs;
				xxjyrs += xyjyrs;
			}
			map.put("xxqyl", String.valueOf(new DecimalFormat("#,##0.00").format(((float)xxqyrs/xxrs)*100))+"%");
			map.put("xxjyl", String.valueOf(new DecimalFormat("#,##0.00").format(((float)xxjyrs/xxrs)*100))+"%");
			map.put("xxrs", xxrs+"");
			map.put("xxqyrs", xxqyrs+"");
			map.put("xxjyrs", xxjyrs+"");
		}
		return map;
	}
	
	/**
	 * 获得就业薪金水平总计
	 * @param List
	 * @return HashMap<String,String>	 
	 * @throws Exception 
	 * */
	public HashMap<String,String> getXjspTotalMap(List<HashMap<String,Object>> list){
		HashMap<String,String> map = new HashMap<String,String>();
		int xj1,xj2,xj3,xj4,xj5,xj6;
		int zxj1 = 0,zxj2 = 0,zxj3 = 0,zxj4 = 0,zxj5 = 0,zxj6 = 0,xyrs,xxrs = 0;
		float xydynypjzgz,xxdynypjzgz = 0;
		if(list != null){
			for(int i = 0;i<list.size();i++){
				xyrs = Integer.parseInt(list.get(i).get("xyrs").toString());
				xj1 = Integer.parseInt(list.get(i).get("xj1").toString());
				xj2 = Integer.parseInt(list.get(i).get("xj2").toString());
				xj3 = Integer.parseInt(list.get(i).get("xj3").toString());
				xj4 = Integer.parseInt(list.get(i).get("xj4").toString());
				xj5 = Integer.parseInt(list.get(i).get("xj5").toString());
				xj6 = Integer.parseInt(list.get(i).get("xj6").toString());
				xydynypjzgz = Float.parseFloat(list.get(i).get("xydynypjgz") == null ?"0":list.get(i).get("xydynypjgz").toString());
				xxrs += xyrs;
				zxj1 += xj1;
				zxj2 += xj2;
				zxj3 += xj3;
				zxj4 += xj4;
				zxj5 += xj5;
				zxj6 += xj6;
				xxdynypjzgz += xydynypjzgz * xyrs;
			}
			map.put("xxrs", xxrs+"");
			map.put("zxj1", zxj1+"");
			map.put("zxj2", zxj2+"");
			map.put("zxj3", zxj3+"");
			map.put("zxj4", zxj4+"");
			map.put("zxj5", zxj5+"");
			map.put("zxj6", zxj6+"");
			map.put("xxdynypjgz", (int)(xxdynypjzgz/xxrs)+"");
		}
		return map;
	}
	/**
	 * 获得就业专业对口总计
	 * @param List
	 * @return HashMap<String,String>	 
	 * @throws Exception 
	 * */
	public HashMap<String,String> getZydkTotalMap(List<HashMap<String,Object>> list){
		HashMap<String,String> map = new HashMap<String,String>();
		int xyrs,xybzyrs,xykzyrs;
		int xxrs = 0,xxbzyrs = 0,xxkzyrs = 0;
		if(list != null){
			for(int i = 0;i<list.size();i++){
				xyrs = Integer.parseInt(list.get(i).get("xyrs").toString());
				xybzyrs = Integer.parseInt(list.get(i).get("xybzyrs").toString());
				xykzyrs = Integer.parseInt(list.get(i).get("xykzyrs").toString());
				xxrs += xyrs;
				xxbzyrs += xybzyrs;
				xxkzyrs += xykzyrs;
			}
			map.put("xxrs", xxrs+"");
			map.put("xxbzyrs", xxbzyrs+"");
			map.put("xxkzyrs", xxkzyrs+"");
			map.put("xxzylkl", String.valueOf(new DecimalFormat("#,##0.00").format(((float)xxbzyrs/xxrs)*100))+"%");
		}
		return map;
	}
}


