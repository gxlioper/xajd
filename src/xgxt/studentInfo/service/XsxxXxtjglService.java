package xgxt.studentInfo.service;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.studentInfo.dao.XsxxXxtjglDAO;

public class XsxxXxtjglService {
	XsxxXxtjglDAO dao = new XsxxXxtjglDAO();
	
	private static String[] XYXXSXXTJEN = {"xymc","zrs","boyrs","girlrs","zxrs","zxboyrs","zxgirlrs"};
	private static String[] XYXXSXXTJCN = {Base.YXPZXY_KEY+"����",Base.YXPZXY_KEY+"������",Base.YXPZXY_KEY+"������",Base.YXPZXY_KEY+"Ů����",Base.YXPZXY_KEY+"��У����",Base.YXPZXY_KEY+"��У������",Base.YXPZXY_KEY+"��УŮ����"};
	
	/**
	 * ��ȡѧԺѧ����Ϣͳ�Ʊ�ͷ
	 * @return List<HashMap<String, String>> 
	 * */
	public List<HashMap<String, String>> getXyXsxxtjTopTr(){		
		return dao.arrayToList(XYXXSXXTJEN, XYXXSXXTJCN);
	}
	
	/**
	 * ѧԺѧ����Ϣͳ�Ʋ�ѯ
	 * @return List<String[]>
	 * */
	public List<String[]> queryXyXsxxtj(){		
		return dao.selectXyXsxxtj(XYXXSXXTJEN,5);
	}
	
	/**
	 * ȫ��ѧԺѧ����Ϣͳ�Ʋ�ѯ
	 * */
	public List<String[]> queryAllXyXsxxtj(){		
		return dao.selectXyXsxxtj(XYXXSXXTJEN,-1);
	}
}
