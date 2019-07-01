package xsgzgl.gygl.xszstj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import xgxt.utils.ExcelMethods;
import xsgzgl.gygl.comm.GyglNewService;

public class XszstjService extends GyglNewService{
	
	private XszstjDao dao=new XszstjDao();
	
	/**
	 * 获取学生住宿统计信息列表
	 * @return
	 */
	public List<HashMap<String, String>> getXszstjxxList(XszstjForm myForm,HttpServletRequest request) throws Exception{
		return dao.getXszstjxxList(myForm,request);
	}
	
	/**
	 * 获取学生住宿统计信息列表（详情）
	 * @return
	 */
	public List<HashMap<String, String>> getXszstjDetailxxList(XszstjForm myForm){
		return dao.getXszstjDetailxxList(myForm);
	}
	
	public ArrayList<String[]> getXszstjXsxxList(XszstjForm myForm, HttpServletRequest request) 
	throws Exception{	
		return dao.getXszstjXsxxList(myForm, request);
	}

	public Object getXyList(HttpServletRequest request) {
		return dao.getXyList(request);
	}

	/**
	 * 导出寝室分布统计表
	 * @author wujian
	 */
	public void expQsfbb(XszstjForm model, ServletOutputStream os,String xymc) throws Exception {
		String title = xymc+"学生寝室分布表";
		// 固定列
		String[] gdName = new String[] { "学院", "楼栋", "寝室","性别","寝室人数","备注","班级","辅导员"};
		// 导出报表表头
		List<HashMap<String, String>> topTr = getTopList(gdName);
		// 导出报表的固定数据
		ArrayList<String[]> gdlist = dao.expQsfbb(model,xymc);

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// 填充表头
		ExcelMB excel = new ExcelMB();
		excel.printTitle(ws, title, 8, 800);// 标题
		
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				ws.setColumnView(i, 25);
				HashMap<String, String> map = topTr.get(i);
				ws.addCell(new Label(i, 1, map.get("cn"), wcf2));
			}
		}
		// 填充内容
		if (gdlist != null && gdlist.size() > 0) {
			for (int i = 0; i < gdlist.size(); i++) {
				String[] info = gdlist.get(i);
				if (info != null && info.length > 0) {
					for (int j = 0; j < info.length; j++) {
						ws.addCell(new Label(j, i + 2, info[j], wcf2));
					}
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	public List<HashMap<String, String>> getXszstjxxExportList(XszstjForm myForm,HttpServletRequest request) throws Exception{
		return dao.getXszstjxxExportList(myForm,request);
	}
	
	public List<HashMap<String, String>> getTbtj() throws Exception{
		return dao.getTbtj();
	}

	/** 
	 * @描述:住宿概况统计
	 * @作者：cq [工号：785]
	 * @日期：2016-5-18 下午05:19:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getZsgktj() {
		return dao.getZsgktj();
	}
	
	/**
	 * 
	 * @描述:获取宿舍楼信息
	 * @作者：cq [工号：785]
	 * @日期：2016-5-19 上午10:30:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getSslInfo(){
		return dao.getSslInfo();
	}
	
	
	/**
	 * 
	 * @描述:获取宿舍楼层信息
	 * @作者：cq [工号：785]
	 * @日期：2016-5-19 上午10:30:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getSslcInfo(){
		return dao.getSslcInfo();
	}

	/** 
	 * @描述:各系宿舍分布图
	 * @作者：cq [工号：785]
	 * @日期：2016-6-1 上午10:27:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getGxsstj() throws Exception {
		return dao.getGxsstj();
	}

	/** 
	 *各系空床位信息
	 */
	public List<HashMap<String, String>> getGxkcwxx() throws Exception {
		return dao.getGxkcwxx();
	}
	
}