package xgxt.pjpy.nbty.jxj;

import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

/**
 * Title: ѧ����������ϵͳ Description:������һ�庮��ʹ��ѧ��Service Copyright: Copyright (c) 2010
 * Company: zfsoft Author: sjf Version: 1.0 Time: 2010-7-14
 */
public class QhtsNbtyService {
	
	// jxjdm��
	private final String jxjb = "jxjdmb";
	// �庮��ʹ�����
	private final String qhtsb = "nbty_qhtssqb";
	// ѧ����Ϣ��ͼ
	private final String xsxxb = "view_xsxxb"; 
	// �庮��ʹ��ͼ
	private final String qhtsview = "view_nbty_qhtsjxj";
	
	/**
	 * ���ݽ�ѧ�����ƻ�ý�ѧ����Ϣ
	 * @param jxjmc
	 * @return
	 */
	public Map<String, String> getJxjxx(String jxjmc) {
		DAO dao = DAO.getInstance();
		Map<String, String> map = dao.getMapNotOut("select jxjdm,jxjlb,jlje,jxjmc from jxjdmb where jxjmc=?", 
				new String[]{jxjmc});
		return map;
	}
	
	/**
	 * ����ѧ�Ż��ѧ����Ϣ
	 * @param xh
	 * @return
	 */
	public Map<String, String> getStuInfo(String xh){
		String[] colList = new String[]{"xh","xm", "xb", "csrq", "mz", "mzmc", "nj",
								"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc"};		
		Map<String, String> map = CommonQueryDAO.commonQueryOne(xsxxb, colList, "xh", xh);
		return map;
	}
	
	/**
	 * ��֤�Ƿ�ͨ��
	 * @param xh
	 * @param xn
	 * @return
	 */
	public boolean isAuditing(String xh, String xn){
		boolean isFlag = false;
		
		// ֻ���жϰ༶�Ƿ���ˣ�ѧԺ��ѧУ���ͨ��������Աһ���Ѿ�ͨ��	
		DAO dao =  DAO.getInstance();
		Map<String,String> map = dao.getMapNotOut("select bjsh from nbty_qhtssqb where xh=? and xn=?", new String[]{xh,xn});
		// �ж��Ƿ������
		if(map!=null){
			if("ͨ��".equals( map.get("bjsh"))){
				isFlag = true;
			}
		}
		return isFlag;
	}

	/**
	 * ��ȡ�庮��ʹ��Ϣ
	 * @param pkValue
	 * @return
	 */
	public Map<String, String> getQhtsxx(String pkValue) {
		
		String[] colList = new String[]{"xm","xb","xh","xn","csrq","mzmc","zymc","xymc","bjmc","nj","jcqk",
				"pddd","hxnhzzz","sqly","sfcjqgzx","sfsqzxdk","bjpy","bjsh","bjshsj","xysh","xyshyj",
				"xyshsj","xxsh","xxshsj","xxshyj","sqsj"};
		
		Map<String, String> map = CommonQueryDAO.commonQueryOne(qhtsview, colList, "xh||xn", pkValue);
		if(StringUtils.isNull(map.get("sqsj"))){
			map.put("sjsj", "&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;");
		}
		if(StringUtils.isNull(map.get("bjshsj"))){
			map.put("bjshsj", "&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;");
		}
		if(StringUtils.isNull(map.get("xyshsj"))){
			map.put("xyshsj", "&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;");
		}
		if(StringUtils.isNull(map.get("xxshsj"))){
			map.put("xxshsj", "&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;");
		}
		
		if("��".equalsIgnoreCase(map.get("sfcjqgzx")) || "��".equalsIgnoreCase("sfsqzxdk")){
			map.put("help", "��");
		}else{
			map.put("help", "��");
		}
		return map;
	}
}
