package xsgzgl.xtwh.general.commutil;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;

/**
 * <p>
 * Title: �W����������ϵ�y
 * </p>
 * <p>
 * Description: ϵ�y�S�o_ͨ�÷���_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ������
 * @version 1.0
 */

public class CommUtilService extends CommService {

	CommUtilDAO dao = new CommUtilDAO();

	/**
	 * �������ֵ�Ƿ����
	 * 
	 * @author ΰ�����
	 * 
	 */
	public boolean checkInputIsExisting(String table_name, String column_name,
			String column_value, String query) {

		column_value = unicode2Gbk(column_value);
		
		String value = dao.getOneValue(table_name, column_name, column_name,
				column_value);

		boolean flag = Base.isNull(value) ? false : true;

		return flag;
	}

	/**
	 * �����Ƿ����
	 * 
	 * @author ΰ�����
	 * 
	 */
	public boolean checkTableIsExisting(String table_name) {

		String[] value = null;

		try {
			value = dao.getTableZd(table_name);
		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean flag = (value != null && value.length > 0) ? true : false;

		return flag;
	}
}
