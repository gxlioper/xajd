
package xgxt.pjpy.hbsf;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.pjpy.tablesservice.PjpyService;
import xgxt.utils.ExcelMethods;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 湖北师范学院评奖评优Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-10</p>
 */
public class PjpyHbsfService extends PjpyService{

	PjpyHbsfDAO dao = null;//数据操作DAO
	
	/**
	 * 获取奖学列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList() throws Exception {
		dao = new PjpyHbsfDAO();
		return dao.getJxjList();
	}
	
	/**
	 * 通过学号获取该生相关信息(姓名，性别，年龄，学院，专业，班级等)
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		dao = new PjpyHbsfDAO();
		return dao.getStuInfo(xh);
	}
	
	/**
	 * 通过奖学金代码获取奖学金金额级别
	 * @param jxjdm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjJelb(String jxjdm) throws Exception {
		dao = new PjpyHbsfDAO();
		return dao.getJxjJelb(jxjdm);
	}
	
	/**
	 * 获取奖学金申请学年年度
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjSqxnnd() throws Exception {
		dao = new PjpyHbsfDAO();
		return dao.getJxjSqxnnd();
	}
	
	/**
	 * 检查奖学金申请数据是否重复
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean chkDataExists(String pkValue) throws Exception {
		dao = new PjpyHbsfDAO();
		return dao.chkDataExists("xsjxjb", "xn||nd||xh||jxjdm", pkValue);
	}
	
	/**
	 * 奖学金申请保存
	 * @param jxjsqModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjsqSave(JxjsqSaveModel jxjsqModel, HttpServletRequest request) throws Exception {
		dao = new PjpyHbsfDAO();
		return dao.jxjsqSave(jxjsqModel, request);
	}
	
	/**
	 * 综合素质查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZhszcpTitle() throws Exception {
		dao = new PjpyHbsfDAO();
		return dao.getZhszcpTitle();
	}
	
	/**
	 * 综合素质查询结果
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZhszcpResult(ZhszcpQryModel zhszcpModel) throws Exception {
		dao = new PjpyHbsfDAO();
		return dao.getZhszcpResult(zhszcpModel);
	}
	
	/**
	 * 综合素质测评信息保存
	 * @param zhszcpModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean zhszcpSave(ZhszcpSaveModel zhszcpModel, HttpServletRequest request) throws Exception {
		dao = new PjpyHbsfDAO();
		return dao.zhszcpSave(zhszcpModel, request);
	}

	/**
	 * 综合素质测评批量删除
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String zhszcpDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new PjpyHbsfDAO();
		return dao.zhszcpDel(keys, request);
	}
	
	/**
	 * 通过主键获取综合素质详细信息
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZhszcpInfoByPk(String pkValue) throws Exception {
		dao = new PjpyHbsfDAO();
		return dao.getZhszcpInfoByPk(pkValue);
	}
	
	/**
	 * 综合素质测评导出数据
	 * @param wwb
	 * @param xydm
	 * @param zydm
	 * @param bjdm
	 * @throws Exception
	 */
	public void printZhszcp(WritableWorkbook wwb, String xydm, String zydm, String bjdm, String xn) throws Exception {
		dao = new PjpyHbsfDAO();
		//横项数据集
		List<HashMap<String, String>> resList = dao.getZhszcpInfoByBj(xydm, zydm, bjdm, xn);
		WritableSheet ws = wwb.getSheet(0);
		WritableCellFormat tStyle = new WritableCellFormat();
 	    WritableCellFormat wcfStyle = new WritableCellFormat();
	    Alignment alignMent = Alignment.CENTRE;
	    wcfStyle.setAlignment(alignMent);
	    tStyle.setAlignment(Alignment.CENTRE);
	    wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
	    HashMap<String, String> tmpMap = new HashMap<String, String>();
	    HashMap<String, String> tempMap = new HashMap<String, String>();
	    if (resList != null && resList.size() > 0) {
	    	tempMap = resList.get(0);//获取表头数据集
	    	ws.addCell(new Label(1,1,tempMap.get("xymc"),tStyle));
		    ws.addCell(new Label(6,1,tempMap.get("zymc"),tStyle));
		    ws.addCell(new Label(13,1,tempMap.get("bjmc"),tStyle));
		    ws.addCell(new Label(18,1,tempMap.get("xn"),tStyle));
 	    for (int i = 0; i < resList.size(); i++) {
 	    	tmpMap = resList.get(i);//获取每行数据集
	    	for (int j = 0; j < 1; j++) {//循环输出行数据
	    		ws.addCell(new Label(j,i+6,tmpMap.get("xh"),wcfStyle));
	    		ws.addCell(new Label(j+1,i+6,tmpMap.get("xm"),wcfStyle));
	    		ws.addCell(new Label(j+2,i+6,tmpMap.get("zpf"),wcfStyle));
	    		ws.addCell(new Label(j+3,i+6,tmpMap.get("sxddbx"),wcfStyle));
	    		ws.addCell(new Label(j+4,i+6,tmpMap.get("zzllxx"),wcfStyle));
	    		ws.addCell(new Label(j+5,i+6,tmpMap.get("ssjsqk"),wcfStyle));
	    		ws.addCell(new Label(j+6,i+6,tmpMap.get("shsjhd"),wcfStyle));
	    		ws.addCell(new Label(j+7,i+6,tmpMap.get("gbrzbx"),wcfStyle));
	    		ws.addCell(new Label(j+8,i+6,tmpMap.get("qttcsj"),wcfStyle));
	    		ws.addCell(new Label(j+9,i+6,tmpMap.get("dcj"),wcfStyle));
	    		ws.addCell(new Label(j+10,i+6,tmpMap.get("kcjqpfj"),wcfStyle));
	    		ws.addCell(new Label(j+11,i+6,tmpMap.get("kcjqpfjpm"),wcfStyle));
	    		ws.addCell(new Label(j+12,i+6,tmpMap.get("zytz"),wcfStyle));
	    		ws.addCell(new Label(j+13,i+6,tmpMap.get("yyjn"),wcfStyle));
	    		ws.addCell(new Label(j+14,i+6,tmpMap.get("jsjjn"),wcfStyle));
	    		ws.addCell(new Label(j+15,i+6,tmpMap.get("zyjn"),wcfStyle));
	    		ws.addCell(new Label(j+16,i+6,tmpMap.get("zyjn"),wcfStyle));
	    		ws.addCell(new Label(j+17,i+6,tmpMap.get("zyjn"),wcfStyle));
	    		ws.addCell(new Label(j+18,i+6,tmpMap.get("zcj"),wcfStyle));
	    		ws.addCell(new Label(j+19,i+6,tmpMap.get("tydb"),wcfStyle));
	    		ws.addCell(new Label(j+20,i+6,tmpMap.get("tyhd"),wcfStyle));
	    		ws.addCell(new Label(j+21,i+6,tmpMap.get("tsqk"),wcfStyle));
	    		ws.addCell(new Label(j+22,i+6,tmpMap.get("stszzf"),wcfStyle));
	    		ws.addCell(new Label(j+23,i+6,tmpMap.get("xljkhd"),wcfStyle));
	    		ws.addCell(new Label(j+24,i+6,tmpMap.get("xlszzk"),wcfStyle));
	    		ws.addCell(new Label(j+25,i+6,tmpMap.get("xlszzf"),wcfStyle));
	    		ws.addCell(new Label(j+26,i+6,tmpMap.get("tcj"),wcfStyle));
	    		ws.addCell(new Label(j+27,i+6,tmpMap.get("zhszcpzf"),wcfStyle));
	    		ws.addCell(new Label(j+28,i+6,tmpMap.get("zhcppm"),wcfStyle));
	    		ws.addCell(new Label(j+29,i+6,tmpMap.get("bjghkms"),wcfStyle));
	    		ws.addCell(new Label(j+30,i+6,tmpMap.get("cfjz"),wcfStyle));
	    		ws.addCell(new Label(j+31,i+6,tmpMap.get("cpjg"),wcfStyle));
	    		}
 	    	}
	    }
	    ExcelMethods.submitWritableWorkbookOperations(wwb);//输出到客户端
	}
}
