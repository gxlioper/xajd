package xgxt.pjpy.zgkd;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.ExcelMethods;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class PjpyZgkdZhszcpService {

	PjpyZgkdZhszcpDAO dao = PjpyZgkdZhszcpDAO.getInstance();
	
	public static PjpyZgkdZhszcpService service = new PjpyZgkdZhszcpService();
	
	public static PjpyZgkdZhszcpService getInstance() {
		return service;
	}
	
	/**
	 * 素质等级代码列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSzdjList() throws Exception {
		return dao.getSzdjList();
	}
	
	public HashMap<String, String> getStuInfo(String xh) {
		return dao.getStuInfo(xh);
	}
	
	public List<HashMap<String, String>> szfTitle()throws Exception {
		String[] enList = new String[]{"pk","num","xh","xm","bjmc","xn","pdcpdf","kcxxcjdf","kpm","pd","ddf","sxcpdf","jbszcpdj","jpm",""};
		String[] cnList = new String[]{"pk", "行号", "学号", "姓名", "班级", "学年", "课程学习测评分","排名","品德测评得分","身心测评得分", "基本素质测评分","排名", "发展素质测评分","排名", "基本素质等级"};
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		for (int i=0;i<enList.length;i++) {
			HashMap<String, String> tmp = new HashMap<String, String>();
			tmp.put("en", enList[i]);
			tmp.put("cn", cnList[i]);
			rs.add(tmp);
		}
		return rs;
	}
	
	public boolean szfSave(ZhszcpModel model, HttpServletRequest request)
	throws Exception {
		return dao.szfSave(model, request);
	}
	
	public boolean szfUpdate(ZhszcpModel model, String pkValue,
			HttpServletRequest request) throws Exception {
		return dao.szfUpdate(model, pkValue, request);
	}
	
	public HashMap<String, String> szfView(String pkValue) throws Exception {
		return dao.szfView(pkValue);
	}
	
	public String szfDelete(String[] keys, HttpServletRequest request) throws Exception {
		return dao.szfDelete(keys, request);
	}
	
	/**
	 * 查询结果
	 * @param model
	 * @param isFdy
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> szfList(ZhszcpModel model, String isFdy, String userName, PjpyZgkdZhszcpActionForm dataSearchForm) throws Exception {
		return dao.szfList(model, isFdy, userName, dataSearchForm);
	}
	public int szfListNum(ZhszcpModel model, String isFdy, String userName) throws Exception {
		return dao.szfListNum(model, isFdy, userName);
	}
	public void szcpHz(ZhszcpModel model, WritableWorkbook wwb) throws Exception {
		ArrayList<ArrayList<String[]>> rs = dao.szcpHz(model);//获取学院学生的综合素质测评数据
		WritableSheet ws = wwb.getSheet(0);
		String xymc = dao.getXymc(model.getXydm());
		WritableCellFormat tStyle = new WritableCellFormat();
 	    WritableCellFormat wcfStyle = new WritableCellFormat();
	    Alignment alignMent = Alignment.CENTRE;//设置居中
	    wcfStyle.setAlignment(alignMent);
	    tStyle.setAlignment(Alignment.CENTRE);
	    tStyle.setBorder(Border.ALL, BorderLineStyle.THIN);//设置加边框
	    WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);//设置字体
		wfTytle.setBoldStyle(WritableFont.BOLD);//设置字体加粗
		wfTytle.setPointSize(15);//设置字体大小
		tStyle.setFont(wfTytle);
	    wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
	    ws.addCell(new Label(0,0,xymc + "素质测评结果汇总表",tStyle));
		if (rs != null && rs.size()>0) {
			int m = 2;
			for (int i=0;i<rs.size();i++) {
				ArrayList<String[]> rsOneList = rs.get(i);//获取每个专业数据
				
				if (rsOneList != null && rsOneList.size()>0) {
					for (int k=0;k<rsOneList.size();k++) {
						String[] tmp = rsOneList.get(k);//获取第行数据
						int j = 0;
						if (tmp != null && tmp.length>0) {
							for (int l=3;l<tmp.length;l++) {
								ws.addCell(new Label(j,m,tmp[l],wcfStyle));//j++,
								//System.out.print(j + ""  + (m));
								j++;
							}
						}
						m++;
					}
				}
				m++;
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//输出到客户端
	}
	
	/**
	 * 查询学生综合素质信息
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> szcpInfo(String xh) throws Exception {
		return dao.szcpInfo(xh);
	}
	
	/**
	 * 保存综合素质测评排名方式
	 * @param pmfs
	 * @param request
	 * @return
	 */
	public boolean saveZhszcpPmfs(String pmfs, HttpServletRequest request) throws Exception {
		return dao.saveZhszcpPmfs(pmfs, request);
	}
	
	/**
	 * 查询综合素质测评排名方式
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> queryZhszcpPmfs() throws Exception {
		return dao.queryZhszcpPmfs();
	}
}
