package xgxt.rcsw.zjjs.rwzb;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.dtjs.czxx.dyxx.DyxxModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_���ݹ�ҵ԰��_�ҵ�����(��ʦ)_DAO��
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

public class ZjjsRwzbDAO extends CommDAO {
	
	public ArrayList<String[]> getRwzbList(String tableName, ZjjsRwzbForm model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj","rwlx" };
		String[] queryLikeList = new String[] { "xh", "xm" };
		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);

		String query = myQuery.getQueryString();

		String[] inputValue = myQuery.getInputList();

		return CommonQueryDAO.commonQuery(tableName, query, inputValue,
				colList, "", model);
	}
}
