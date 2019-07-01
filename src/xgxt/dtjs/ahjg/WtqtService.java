package xgxt.dtjs.ahjg;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.write.WriteException;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.form.CommanForm;

public class WtqtService {

	WtqtDao dao = new WtqtDao();

	public HashMap<String, String> getXsxxInfo(String xh) {
		return dao.getXsxxInfo(xh);
	}
	
	public boolean saveStuxx(Model model,HttpServletRequest request) {
		return dao.saveStuxx(model,request);
	}
	
	public boolean save_modify(Model model,HttpServletRequest request) {
		return dao.save_modify(model,request);
	}

	public boolean issave(Model model) {
		return dao.issave(model);
	}
	
	public ArrayList<String[]> getQueryList(Model model,CommanForm myform) {
		return dao.getQueryList(model,myform);
	}
	public String[] getwtqtList(String xh,String xn,String xq) {
		return dao.getwtqtList(xh,xn,xq);
	}
	public void getExpList(Model model, OutputStream os) {
		try {
			dao.getExpList(model,os);
		} catch (WriteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}
	public HashMap<String, String> getViewList(String xh) {
		return dao.getViewList(xh);
	}
	
	public String getAllDelList(String pks,HttpServletRequest request) throws Exception {
		String whichpk = dao.getAllDelList(pks, request);
		return whichpk;
	}
	
	public int queryListNum(Model model) {
		return dao.queryListNum(model);
	}

		public List<HashMap<String, String>> queryTitle() throws Exception {
			String[] enList = new String[]{"xh","xn","xq","xm","xymc","bjmc","xz"};
			String[] cnList = new String[]{"学号","学年","学期","姓名",Base.YXPZXY_KEY+"名称", "班级名称","学制"};
			return dao.getTitle(enList, cnList);
		}

	public void formToGBK(Object model) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// 把form或者model里所有String类型的值转码一次返回页面
		Class myClass = model.getClass();
		Method[] methods = myClass.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			String methodOne = methods[i].getName();
			String methodType = methods[i].getReturnType().getName();
			if (methodOne.length() > 3
					&& methodOne.substring(0, 3).equalsIgnoreCase("get")
					&& methodType.equalsIgnoreCase("java.lang.String")) {
				String setMethod = "set" + methodOne.substring(3);
				String newValue = DealString.toGBK((String) myClass.getMethod(
						methodOne, (Class[]) null).invoke(model,
						(Object[]) null));
				myClass.getMethod(setMethod, new Class[] { String.class }).invoke(model, newValue);
			}
		}
	}
	public List<HashMap<String, String>> getWtqt() {
		return dao.getWtqt();
	}
}
