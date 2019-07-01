package xgxt.pjpy.tyb.zhszcp.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import common.newp.ArrayUtil;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.form.SaveForm;
import xgxt.pjpy.tyb.zhszcp.dao.PjpyZcsjwhDAO;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZctjszModel;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZhszcpModel;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZhszfsModel;
import xgxt.utils.String.StringUtils;

public class PjpyZcsjwhService {

	PjpyZcsjwhDAO dao = new PjpyZcsjwhDAO();
	
	/* 查询综测代码信息
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> queryZctjdmList(PjpyZctjszModel model) {
		return dao.queryZctjdmList(model);
	}
	
	/**
	 * 查询综测数据维护表头
	 * 
	 * @param titleList
	 * @param pjzq
	 * @return
	 */
	public HashMap<String, String[]> queryZcsjwhTitle(
			List<HashMap<String, String>> titleList, String pjzq) {
		String[] en = { "pk", "r", "xh", "xm", "bjmc" };
		String[] cn = { "pk", "行号", "学号", "姓名", "班级" };
		String[] zqen = {};
		String[] zqcn = {};
		String[] zcen = {};
		String[] zccn = {};
		if ("xn".equalsIgnoreCase(pjzq)) {
			zqen = new String[] { "xn" };
			zqcn = new String[] { "学年" };
		} else if ("xq".equalsIgnoreCase(pjzq)) {
			zqen = new String[] { "xn", "xqmc" };
			zqcn = new String[] { "学年", "学期" };
		} else if ("nd".equalsIgnoreCase(pjzq)) {
			zqen = new String[] { "nd" };
			zqcn = new String[] { "年度" };
		}
		if (titleList != null && titleList.size() > 0) {
			zcen = new String[titleList.size()];
			zccn = new String[titleList.size()];
			for (int i = 0; i < titleList.size(); i++) {
				zcen[i] = titleList.get(i).get("MC");
				zccn[i] = titleList.get(i).get("MC");
			}
		}
		DAO mydao = DAO.getInstance();
		en = mydao.unionArray(en, zqen);
		cn = mydao.unionArray(cn, zqcn);
		en = mydao.unionArray(en, zcen);
		cn = mydao.unionArray(cn, zccn);
		HashMap<String, String[]> rs = new HashMap<String, String[]>();
		rs.put("en", en);
		rs.put("cn", cn);
		return rs;
	}
	
	/**
	 * 查询综测数据维护表头
	 * 
	 * @param titleList
	 * @param pjzq
	 * @return
	 */
	public List<HashMap<String, String>> queryZcsjwhTitle(PjpyZctjszModel model, String pjzq) {
		List<HashMap<String, String>> titleList = queryZctjdmList(model);
		HashMap<String, String[]> zctitleMap = queryZcsjwhTitle(titleList, pjzq);
		DAO mydao = DAO.getInstance();
		return mydao.arrayToList(zctitleMap.get("en"), zctitleMap.get("cn"));
	}
	
	public List<HashMap<String, String>> queryZcsjwhTitle(String pjzq) {
		HashMap<String, String[]> zctitleMap = queryBaseTitle(pjzq);
		DAO mydao = DAO.getInstance();
		return mydao.arrayToList(zctitleMap.get("en"), zctitleMap.get("cn"));
	}
	
	public HashMap<String, String[]> queryBaseTitle(String pjzq) {
		String[] en = { "pk", "r", "xh", "xm", "bjmc" };
		String[] cn = { "pk", "行号", "学号", "姓名", "班级" };
		String[] zqen = {};
		String[] zqcn = {};
		String[] zcen = {};
		String[] zccn = {};
		if ("xn".equalsIgnoreCase(pjzq)) {
			zqen = new String[] { "xn" };
			zqcn = new String[] { "学年" };
		} else if ("xq".equalsIgnoreCase(pjzq)) {
			zqen = new String[] { "xn", "xqmc" };
			zqcn = new String[] { "学年", "学期" };
		} else if ("nd".equalsIgnoreCase(pjzq)) {
			zqen = new String[] { "nd" };
			zqcn = new String[] { "年度" };
		}
		
		DAO mydao = DAO.getInstance();
		en = mydao.unionArray(en, zqen);
		cn = mydao.unionArray(cn, zqcn);
		en = mydao.unionArray(en, zcen);
		cn = mydao.unionArray(cn, zccn);
		HashMap<String, String[]> rs = new HashMap<String, String[]>();
		rs.put("en", en);
		rs.put("cn", cn);
		return rs;
	}
	/**
	 * 查询综测数据维护结果
	 * 
	 * @param model
	 * @param pjzq
	 * @param titleList
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZcsjwhResult(PjpyZhszcpModel model, String pjzq,
			PjpyZctjszModel zctjModel, boolean isPage)
			throws Exception {
		List<HashMap<String, String>> titleList = queryZctjdmList(zctjModel);
		HashMap<String, String[]> zctitleMap = queryZcsjwhTitle(titleList, pjzq);
		return dao.queryZcsjwhResult(model, titleList, zctitleMap.get("en"), isPage);
	}
	
	/**
	 * 获取周期查询字段
	 * @param pjzq
	 * @return
	 */
	public String getQueryZd(String pjzq) {
		String result = "";
		if ("xn".equalsIgnoreCase(pjzq)) {
			result = ",b.xn";
		} else if ("xq".equalsIgnoreCase(pjzq)) {
			result = ",b.xn,b.xqmc";
		} else if ("nd".equalsIgnoreCase(pjzq)) {
			result = ",b.nd";
		}
		return result;
	}
	
	public String getQueryZd(List<HashMap<String, String>> titleList) {
		StringBuilder result = new StringBuilder("");
		if (titleList != null && titleList.size() > 0) {
			for (int i=0;i<titleList.size();i++) {
				if (titleList.get(i) != null && StringUtils.isNotNull(titleList.get(i).get("mc"))) {
					result.append(",");
					result.append(titleList.get(i).get("mc")); 
				}
			}
		}
		return result.toString();
	}
	
	public String getQueryZd(List<HashMap<String, String>> titleList, String pjzq) {
		StringBuilder result = new StringBuilder("");
		if (titleList != null && titleList.size() > 0) {
			for (int i=0;i<titleList.size();i++) {
				if (titleList.get(i) != null && StringUtils.isNotNull(titleList.get(i).get("mc"))) {
					result.append("select ");
				}
			}
		}
		return result.toString();
	}
	
	/**
	 * 获取周期查询字段
	 * @param pjzq
	 * @return
	 */
	public String[] getQueryZdByArray(String pjzq) {
		String[] result = {};
		if ("xn".equalsIgnoreCase(pjzq)) {
			result = new String[]{"xn"};
		} else if ("xq".equalsIgnoreCase(pjzq)) {
			result = new String[]{"xn", "xqmc"};
		} else if ("nd".equalsIgnoreCase(pjzq)) {
			result = new String[]{"nd"};
		}
		return result;
	}
	
	/**
	 * 保存录入分
	 * @return
	 */
	public boolean savelrf(PjpyZhszfsModel model){
		boolean flag = false;
		SaveForm saveForm = new SaveForm();

		String pk = "xh||xn||xq||nd||dm";
		String xn = model.getXn();
		String xq = model.getXq();
		String nd = model.getNd();
		String[] xh = model.getXh();
		String[] dm = model.getDm();
		
//		List<String> list = new ArrayList<String>();
		
//		for(int i=0;i<xh.length;i++){
//			if(i>=1 && xh[i].equalsIgnoreCase(xh[i-1])){
//				continue;
//			}
//			list.add(xh[i] + xn + xq + nd);
//		}
//		
//		String[] pkValue = list.toArray(new String[]{});
		String[] pkValue = new String[xh.length];
		
		for(int i=0;i<xh.length;i++){
			pkValue[i] = xh[i] + xn + xq + nd + dm[i];
		}
		
		String[] arrzd = new String[] {"xh","dm","fs"};
		String[] onezd = new String[] {"xn","xq","nd","jb"};

		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setTableName("pjpy_zhszcpb");

		DAO dao = DAO.getInstance();

		try {
			flag = dao.saveData(saveForm, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 保存录入分
	 * @return
	 */
	public boolean dellrf(String[] pkValues){
		return dao.dellrf(pkValues);
	}
	
	public void dataExport(String head,String[] title, List<String[]> rs,  OutputStream os) throws IOException, WriteException{
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("数据导出", 0);
		try {
			excel.printTitle(ws, head, title.length-1, 400);
		
			for (int m = 0; m < title.length-1; m++) {
				ws.addCell(new Label(m, 1, title[m+1]));
			}
			
			int k = title.length-1;
			for (int i = 0; i < rs.size(); i++) {
				String[] tmp = (String[]) rs.get(i);
				for (int j = 0; j < k; j++) {
					ws.addCell(new Label(j, i + 2, tmp[j+1]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据导出失败!");
		} finally {
			wwb.write();
			wwb.close();
		}
		
	}
	
	/**
	 * 初始化综测分
	 * @return
	 */
	public boolean initZcf(PjpyZhszcpModel model){
		return dao.initZcf(model);
	}
}
