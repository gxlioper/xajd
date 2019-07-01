package xsgzgl.gygl.xszsgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.User;
import xsgzgl.gygl.comm.GyglNewService;

public class XszsglService extends GyglNewService{
	
	XszsglDao dao = new XszsglDao();

	/**
	 * 获取已入住学生信息数据集
	 * @author zhanghui
	 */
	public ArrayList<String[]> getTsglZsxxList(XszsglForm myForm, HttpServletRequest request) 
		throws Exception{	
		return dao.getTsglZsxxList(myForm, request);
	}

	public String getBzxbz(String string) {
		// TODO 自动生成方法存根
		return dao.getBzxbz(string);
	}
	/**
	 * 设置走读备注
	 * @throws Exception 
	 */
	public boolean setBz(XszsglForm myForm, String[] xhstr) throws Exception {
		// TODO 自动生成方法存根
		boolean falg=false;
		//先删除
		dao.deleteBz(xhstr);
		//后插入
		
		return dao.insertBz(myForm,xhstr);
	}
	/**
	 * 获取学生住宿信息
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getXszsxx(String xh) {
		// TODO 自动生成方法存根
		
		return dao.getXszsxx(xh);
	}
	
	/**
	 * 学生住宿信息 自定义导出
	 * @param myForm
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getExportResultList(XszsglForm myForm,User user,HttpServletRequest request)
	throws Exception {
		// TODO 自动生成方法存根
		return dao.getExportPageList(myForm, user,request);
	}
	
}
