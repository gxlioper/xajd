package xgxt.jxgl.comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 军训管理_通用_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */
public class JxglCommDAO extends CommDAO {
	
	DAO dao = DAO.getInstance();
	
	
	/**
	 * 军训建制
	 * @return
	 */
	public List<HashMap<String,String>> getJxjz(){
		
		String sql = "select jzdm,jzmc from jxjzdj order by jzdm";
		
		return dao.getListNotOut(sql, new String[]{});
	}
	
}
