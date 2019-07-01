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
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��ѵ����_ͨ��_DAO��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */
public class JxglCommDAO extends CommDAO {
	
	DAO dao = DAO.getInstance();
	
	
	/**
	 * ��ѵ����
	 * @return
	 */
	public List<HashMap<String,String>> getJxjz(){
		
		String sql = "select jzdm,jzmc from jxjzdj order by jzdm";
		
		return dao.getListNotOut(sql, new String[]{});
	}
	
}
