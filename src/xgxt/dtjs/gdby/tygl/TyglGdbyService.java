package xgxt.dtjs.gdby.tygl;

import java.util.HashMap;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.base.BaseDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.date.DateUtils;

/**
 * Title: ѧ����������ϵͳ
 * Description:�㶫���� ���Ź���Service
 * Module:���Ž��� - ��Ա��Ϣ
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-7-30
 */
public class TyglGdbyService extends BasicExtendService{
	
	/**
	 * ��ȡ��������ѧ��������Ϣ�������ѧ��δ
	 * �ύ�뵳�����飬���ѧ����������
	 * @param xh
	 * @return
	 */
	public Map<String, String> getTyStuInfo(String xh){
		String tableName = "view_czxx_rdsq";
		String pk = "xh";
		Map<String, String> map = new HashMap<String, String>();
		String[] output = new String[]{"xh","xm","xymc","zymc",
				"bjmc","mzmc","sqsj","xb","nj"};
		BaseDAO dao = new BaseDAO();	
		
		boolean isExists = dao.checkExists(tableName, pk, xh);
		
		if(isExists){
			map = CommonQueryDAO.commonQueryOne(tableName, output, pk, xh);
			
			if(map != null){
				map.put("sqsj", DateUtils.getDayOfCn(map.get("sqsj")));
			}
		}else{
			String[] colList = new String[]{"xh","xm","xymc","zymc",
					"bjmc","mzmc","xb","nj"};
			map = CommonQueryDAO.commonQueryOne("view_xsxxb", colList, pk, xh);
		}
		
		map.put("isExists", String.valueOf(isExists));
		return map;
	}
	
	/**
	 * ��ȡ����ѧ���������Ϣ
	 * @param pkValue
	 * @return
	 */
	public Map<String, String> getTysqxx(String pkValue){
		String[] output = new String[]{"xh","xm","mzmc","xymc","zymc","bjmc","fdysh","xysh","xxsh","fdyshyj",
				"xyshyj","xxshyj","xn","xq","xb","nj","sqly","bz"};
		String pk = "xh||xn";
		
		Map<String, String> map = CommonQueryDAO.commonQueryOne("view_gdby_tytysq", output, pk, pkValue);
		return map;
	}

	public boolean isAuditing(String xh, String xn){
		boolean isFlag = false;
		
		// ֻ���жϰ༶�Ƿ���ˣ�ѧԺ��ѧУ���ͨ��������Աһ���Ѿ�ͨ��	
		DAO dao =  DAO.getInstance();
		Map<String,String> map = dao.getMapNotOut("select fdysh from gdby_tytysqb where xh=? and xn=?", new String[]{xh,xn});
		// �ж��Ƿ������
		if(map!=null){
			if("ͨ��".equals( map.get("fdysh"))){
				isFlag = true;
			}
		}
		return isFlag;
	}
}

