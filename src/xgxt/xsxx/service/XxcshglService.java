package xgxt.xsxx.service;

import java.util.HashMap;
import java.util.List;

import common.GlobalsVariable;

import xgxt.form.User;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.xsxx.dao.XsxxXjydglDAO;
import xgxt.xsxx.dao.XxcshglDAO;
import xgxt.xsxx.form.XsxxcshForm;
import xgxt.xtwh.common.service.XtlcglService;


public class XxcshglService {
	XxcshglDAO dao = new XxcshglDAO();
	
	/**
	 * 获取要初始化数据的模块
	 * */
	public List<HashMap<String, String>> getInitDataModule(){
		String[] mkmc = {"部门信息", "专业信息", "班级信息", "学生信息"};
		String[] dyym = {"xxcshgl.do?method=xxbmcsh", "xxcshgl.do?method=zyxxcsh", "xxcshgl.do?method=bjxxcsh", 
						 "xxcshgl.do?method=xsxxcsh"};
		
		return dao.arrayToList(dyym, mkmc);
	}
	
	/**
	 * 获取初始化标记
	 * */
	public boolean getCshFlag(String module){
		//TODO 设置功能完成后修改此处
		return true;
	}
	
	/**
	 * 学校部门信息初始化连接接口
	 * */
	public String xxbmLjjk(XsxxcshForm model){
		String message = "操作成功";
		//TODO 接口设置完成后修改此处
		return message;
	}
	
	/**
	 * 学校部门信息标记为正确数据
	 * */
	public String xxbmBjwzqsj(XsxxcshForm model){
		String message = "操作成功！";
		//将选中的数据插入到学校部门正式表中
		boolean result = dao.saveXxbmlsxxdzsk(model.getPrimarykey_cbv());
		if (!result){
			message = "操作失败！";
		}
		return message;
	}
	
	/**
	 * 学校部门信息全部标记为正确数据
	 * */
	public String xxbmQbbjwzqsj(XsxxcshForm model){
		String message = "操作成功";
		//将全部数据插入到学校部门正式表中
		boolean result = dao.saveAllXxbmlsxxdzsk(model.getPrimarykey_cbv());
		if (!result){
			message = "操作失败！";
		}
		return message;
	}
	
	/**
	 * 专业信息初始化连接接口
	 * */
	public String zyxxLjjk(XsxxcshForm model){
		String message = "操作成功";
		//TODO 接口设置完成后修改此处
		return message;
	}
	
	/**
	 * 专业信息标记为正确数据
	 * */
	public String zyxxBjwzqsj(XsxxcshForm model){
		String message = "操作成功！";
		//将选中的数据插入到专业正式表中
		boolean result = dao.saveZyxxlsxxdzsk(model.getPrimarykey_cbv());
		if (!result){
			message = "操作失败！";
		}
		return message;
	}
	
	/**
	 * 专业信息全部标记为正确数据
	 * */
	public String zyxxQbbjwzqsj(XsxxcshForm model){
		String message = "操作成功";
		//将全部数据插入到专业正式表中
		boolean result = dao.saveAllZyxxlsxxdzsk(model.getPrimarykey_cbv());
		if (!result){
			message = "操作失败！";
		}
		return message;
	}
	
	
	/**
	 * 班级信息初始化连接接口
	 * */
	public String bjxxLjjk(XsxxcshForm model){
		String message = "操作成功";
		//TODO 接口设置完成后修改此处
		return message;
	}
	
	/**
	 * 班级信息标记为正确数据
	 * */
	public String bjxxBjwzqsj(XsxxcshForm model){
		String message = "操作成功！";
		//将选中的数据插入到专业正式表中
		boolean result = dao.saveBjxxlsxxdzsk(model.getPrimarykey_cbv());
		if (!result){
			message = "操作失败！";
		}
		return message;
	}
	
	/**
	 * 班级信息全部标记为正确数据
	 * */
	public String bjxxQbbjwzqsj(XsxxcshForm model){
		String message = "操作成功";
		//将全部数据插入到专业正式表中
		boolean result = dao.saveAllBjxxlsxxdzsk(model.getPrimarykey_cbv());
		if (!result){
			message = "操作失败！";
		}
		return message;
	}
	
	/**
	 * 学生信息初始化连接接口
	 * */
	public String xsxxLjjk(XsxxcshForm model){
		String message = "操作成功";
		//TODO 接口设置完成后修改此处
		return message;
	}
	
	/**
	 * 学生信息标记为正确数据
	 * */
	public String xsxxBjwzqsj(XsxxcshForm model){
		String message = "操作成功！";
		//将选中的数据插入到专业正式表中
		boolean result = dao.saveXsxxlsxxdzsk(model.getPrimarykey_cbv());
		if (!result){
			message = "操作失败！";
		}
		return message;
	}
	
	/**
	 * 学生信息全部标记为正确数据
	 * */
	public String xsxxQbbjwzqsj(XsxxcshForm model){
		String message = "操作成功";
		//将全部数据插入到专业正式表中
		boolean result = dao.saveAllXsxxlsxxdzsk(model.getPrimarykey_cbv());
		if (!result){
			message = "操作失败！";
		}
		return message;
	}
}
