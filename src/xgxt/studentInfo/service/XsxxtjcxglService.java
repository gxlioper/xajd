package xgxt.studentInfo.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.GetDropDownList;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.studentInfo.dao.XsxxtjcxglDAO;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.studentInfo.model.XsxxtjcxglForm;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import common.Globals;

public class XsxxtjcxglService {
	XsxxtjcxglDAO dao = new XsxxtjcxglDAO();
	/**
	 * 查询学生干部信息
	 * */
	public List<HashMap<String, String>> queryXsbgList(XsxxtjcxglForm model){
		List<HashMap<String, String>> xsgbList = new ArrayList<HashMap<String,String>>();
		//判断是否有学生干部维护模块
		if(dao.checkExists("gnmkdmb", "dyym","xsgbxxwh.do")){
			xsgbList = dao.selectXsgbList(model);
		}
		return xsgbList;
	}
}
