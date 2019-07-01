package xgxt.jygl.njjs;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.utils.ExcelMethods;

public class NjjsJyglService {
	private NjjsJyglDAO jyglDao = new NjjsJyglDAO();		// 南京技师就业管理DAO
	
	/**
	 * 毕业去向汇总查询
	 * @param model
	 * @return
	 */
	public List<String[]> byqxhzQuery(NjjsJyglForm model){
		return jyglDao.byqxhzQuery(model);
	}
	
	/**
	 * 获取学历List
	 * @return
	 */
	public List<HashMap<String, String>> getXlList(){
		return jyglDao.getXlList();
	}
	
	/**
	 *获取培养层次（技能等级）List
	 * @return
	 */
	public List<HashMap<String, String>> getPyccList(){
		return jyglDao.getPyccList();
	}
	
	/**
	 * 获取培养方式List
	 * @return
	 */
	public List<HashMap<String, String>> getPyfsList(){
		return jyglDao.getPyfsList();
	}
	
	/**
	 * 获取毕业去向List
	 * @return
	 */
	public List<HashMap<String, String>> getByqxList(){
		return jyglDao.getByqxList();
	}
	
	/**
	 * 获取毕业年度List
	 * @return
	 */
	public List<HashMap<String, String>> getByndList(){
		return jyglDao.getByndList();
	}
	
	/**
	 * 毕业生学生管理
	 * @param model
	 * @return
	 */
	public List<String[]> xssbQuery(NjjsJyglModel model){
		return jyglDao.xssbQuery(model);
	}
	
	/**
	 * 毕业生查询
	 * @param model
	 * @return
	 */
	public List<String[]> bysQuery(NjjsJyglModel model){
		return jyglDao.bysQuery(model);
	}
	
	/**
	 * 上报学生查询
	 * @param model
	 * @return
	 */
	public List<String[]> sbxsQuery(NjjsJyglModel model){
		return jyglDao.sbxsQuery(model);
	}
	
	/**
	 * 学生实习查询
	 * @param model
	 * @return
	 */
	public List<String[]> xssxQuery(NjjsJyglModel model){
		return jyglDao.xssxQuery(model);
	}
	
	/**
	 * 保存学生上报
	 * @param model
	 * @return
	 */
	public boolean saveXssb(NjjsJyglModel model){
		return jyglDao.saveXssb(model);
	}
	
	/**
	 * 修改学生上报
	 * @param model
	 * @return
	 */
	public boolean updateXssb(NjjsJyglModel model){
		return jyglDao.updateXssb(model);
	}
	
	/**
	 * 保存毕业去向
	 * @param model
	 * @return
	 */
	public boolean saveByqx(NjjsJyglModel model){
		return jyglDao.saveByqx(model);
	}
	
	/**
	 * 保存学生实习
	 * @param model
	 * @return
	 */
	public boolean saveXssx(NjjsJyglModel model){
		return jyglDao.saveXssx(model);
	}
	
	/**
	 * 保存学生实习
	 * @param model
	 * @return
	 */
	public boolean updateXssx(NjjsJyglModel model){
		return jyglDao.updateXssx(model);
	}
	
	/**
	 * 获取学生信息
	 * @param xh
	 * @return
	 */
	public Map<String, String> getStuInfo(String xh){
		return jyglDao.getStuInfo(xh);
	}
	
	public Map<String, String> getXssxInfo(String xh){
		return jyglDao.getXssxInfo(xh);
	}
	
	/**
	 * 获取毕业生信息
	 * @param xh
	 * @return
	 */
	public Map<String, String> getBysInfo(String xh){
		return jyglDao.getBysInfo(xh);
	}
	
	public List<HashMap<String, String>> getTopTr(String lx,RequestForm rForm) {

		DAO dao = DAO.getInstance();
		
		String[]en=null;
		String[]cn=null;
		if("dxsxgl".equalsIgnoreCase(lx)){
			 en = new String[] { "xh", "xm", "xymc", "zymc", "bjmc", "jyfs","dwmc" };
			 cn = new String[] { "学号", "姓名", Base.YXPZXY_KEY, "专业", "班级", "就业方式","单位名称" };
		}else if("byqx".equalsIgnoreCase(lx)){
			 en = new String[]{"xh", "xm", "xb", "bjmc", "bynd", "byqxmc", "sfsb" };
			 cn = new String[] { "学号", "姓名", "性别", "班级", "毕业年度", "毕业去向", "是否已上报"};
		}else if("xssb".equalsIgnoreCase(lx)){
			 en = new String[]{"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc"};
			 cn = new String[]{"学号", "姓名", "性别", "年级", Base.YXPZXY_KEY, "专业", "班级"};
		}else if("xssx".equalsIgnoreCase(lx)){
			 en = new String[]{"xh", "xm", "nj", "xymc", "zymc", "bjmc", "sfsb"};
			 cn = new String[] {"学号", "姓名", "年级", Base.YXPZXY_KEY, "专业", "班级", "是否已录入"};
		}else if("sbxs".equalsIgnoreCase(lx)){
			en = new String[]{"xh", "xm", "nj", "xymc", "zymc", "bjmc", "bynd" };;
			cn = new String[] {"学号", "姓名", "年级", Base.YXPZXY_KEY, "专业", "班级", "毕业年度"};
		}
		
		rForm.setColList(en);
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 删除上报学生
	 * @param xhArr
	 * @return
	 */
	public boolean delSbxs(String[] xhArr){
		List<String[]> list = new ArrayList<String[]>();
		
		for(int i=0; i<xhArr.length; i++){
			list.add(new String[]{xhArr[i]});
		}
		
		return jyglDao.delSbxs(list);
	}
	
	/**
	 * 删除上报学生
	 * @param xhArr
	 * @return
	 */
	public boolean delByqx(String[] xhArr){
		List<String[]> list = new ArrayList<String[]>();
		
		for(int i=0; i<xhArr.length; i++){
			list.add(new String[]{xhArr[i]});
		}
		
		return jyglDao.delByqx(list);
	}
	
	/**
	 * 删除学生实习
	 * @param xhArr
	 * @return
	 */
	public boolean delXssx(String[] xhArr){
		List<String[]> list = new ArrayList<String[]>();
		
		for(int i=0; i<xhArr.length; i++){
			list.add(new String[]{xhArr[i]});
		}
		
		return jyglDao.delXssx(list);
	}
	
	/**
	 * 毕业去向导出
	 * @param model
	 * @param os
	 * @throws Exception 
	 */
	public void byqxdc(NjjsJyglModel model,OutputStream os) throws Exception{
		String bjmc = model.getBjmc();
		String title = Base.currNd+"年毕业生就业花名册";
		
		List<HashMap<String, String>> list = jyglDao.getByqxDcInfo(model);
		
		String[] colNameCN = new String[]{"序  号", "姓  名" , "性  别", "出生年月", "家庭住址及所属派出所", "就业单位", "联系电话"};
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(bjmc, 0);
		
		WritableCellFormat titleFormat = ExcelMethods.getWcf(WritableFont.ARIAL, 14, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.NONE); // 构造单元格格式
		
		WritableCellFormat cellFormat = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL); // 构造单元格格式
		
		WritableCellFormat headFormat = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.NONE); // 构造单元格格式
		
		ws.mergeCells(0, 0, colNameCN.length-1, 0);
		ws.mergeCells(0, 1, colNameCN.length-2, 1);
		
		ws.addCell(new Label(0, 0, title, titleFormat));
		ws.addCell(new Label(0, 1, "学校（盖章）：南京技师学院", headFormat));
		ws.addCell(new Label(colNameCN.length-1, 1, "班级："+bjmc, headFormat));
		
		try {
			for (int m = 0; m < colNameCN.length; m++) {
				ws.addCell(new Label(m, 2, colNameCN[m], cellFormat));
			}
			
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> map = list.get(i);
				ws.addCell(new Label(0, i + 3, map.get("count"), cellFormat));
				ws.addCell(new Label(1, i + 3, map.get("xm"), cellFormat));
				ws.addCell(new Label(2, i + 3, map.get("xb"), cellFormat));
				ws.addCell(new Label(3, i + 3, map.get("csrq"), cellFormat));
				ws.addCell(new Label(4, i + 3, map.get("jtszd"), cellFormat));
				ws.addCell(new Label(5, i + 3, map.get("jydw"), cellFormat));
				ws.addCell(new Label(6, i + 3, map.get("lxdh"), cellFormat));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据导出失败!");
		} finally {
			wwb.write();
			wwb.close();
		}
		
	}
	
		public void xssxExp(NjjsJyglModel model ,WritableWorkbook wwb)throws Exception {
		List<String[]> list = new ArrayList<String[]>();
		list = jyglDao.xssxExp(model);
		HashMap<String,String> bjxx = jyglDao.getBjxx(model);
		// 填报月份
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy年MM月");
		String tbyf = format.format(date);

		WritableSheet ws = wwb.createSheet("sheet1", 0);
		try {
	//		ws.setPageSetup(PageOrientation.LANDSCAPE.LANDSCAPE,PaperSize.A4,0.5d,0.5d);
	
			
			
			// 创建第一行
			Label titleCell = new Label(0, 0, bjxx.get("xymc")+bjxx.get("bjmc")+"班学生就业定向实习基本情况月报表         "+tbyf);
			
			WritableCellFormat wcFormat = new WritableCellFormat();
			wcFormat = new WritableCellFormat();
			wcFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			WritableFont font = new WritableFont(WritableFont.ARIAL, 16);
			font.setBoldStyle(WritableFont.BOLD);
			wcFormat.setFont(font);
			
			ws.mergeCells(0, 0, 12, 0);
			titleCell.setCellFormat(wcFormat);
			ws.addCell(titleCell);
	
			// 列表部分
			//表头字体样式
			wcFormat = new WritableCellFormat();
			wcFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			font = new WritableFont(WritableFont.ARIAL, 10);
			font.setBoldStyle(WritableFont.BOLD);
			wcFormat.setFont(font);
			
			String[] bt = new String[]{"序号","姓名","单位名称","就业方式","地址","性质","部门","岗位",
					"劳动保护","食宿条件","交通状况","学生联系电话","实习单位变动情况"};
			for(int i=0;i<bt.length;i++){
				titleCell = new Label(i, 1, bt[i]);
				titleCell.setCellFormat(wcFormat);
				ws.addCell(titleCell);
			}
			
			
			//内容字体样式
			wcFormat = new WritableCellFormat();
			wcFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			font = new WritableFont(WritableFont.ARIAL, 10);
			wcFormat.setFont(font);
			if (list.size() != 0) {
				for (int k=0;k<list.size();k++) {
					String[] map = list.get(k);
					for(int n=0;n<map.length;n++){
						titleCell = new Label(n, k+2, map[n]);
						titleCell.setCellFormat(wcFormat);
						ws.addCell(titleCell);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			wwb.write();
			wwb.close();
		}
		
	}
	
	/**
	 * 获取省List
	 * @return
	 */
	public List<HashMap<String, String>> getShenList(){
		return jyglDao.getShenList();
	}
	
	/**
	 * 获取市List
	 * @return
	 */
	public List<HashMap<String, String>> getShiList(String shen){
		return jyglDao.getShiList(shen);
	}
	
	/**
	 * 获取县List
	 * @return
	 */
	public List<HashMap<String, String>> getXianList(String shen,String shi){
		return jyglDao.getXianList(shen,shi);
	}
	
	/**
	 * 保存学生的家庭地址和联系电话
	 * @param xh
	 * @param jtdz
	 * @param lxdh
	 * @return
	 */
	public boolean saveXsJtdzAndLxdh(String xh,String jtdz,String lxdh){
		if(Base.isNull(jtdz)&&Base.isNull(lxdh)){
			return true;
		}
		return jyglDao.saveXsJtdzAndLxdh(xh,jtdz,lxdh);
	}
}
