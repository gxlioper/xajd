package xgxt.xszz.nbty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.pjpy.tginterface.PjpyCommonInterface;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.NullStringException;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.commonN05.XszzCommonN05Service;

/**
 * Title: ѧ����������ϵͳ
 * Description:������һ����Service
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-6-25
 */
public class XszzNbtyService {
	
	private final String tableName = "nbty_gjzxdksqb";
	private final String viewName = "view_nbty_gjzxdksq";
	/**
	 * ͨ��ѧ�Ż��ѧ���ļ�ͥ��Ϣ��ѧ����Ϣ
	 * @param xh
	 * @return
	 */
	public Map<String, String> getStuInfo(String xh){
		// ѧ����Ϣ��keyΪ�����ֶΣ�valueΪ��Ӧ���е�ֵ
		Map<String,String> stuInf = new HashMap<String,String>();
		
		if (!Base.isNull(xh)) {
			// ��Ҫ��ȡ��ѧ����Ϣ�ֶ�
			String[] colList1 = new String[] { "xh", "xm", "xb", "csrq", "nj",
					 "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "sfzh",
					 "byny", "lxdh", "xz", "ssbh", "qsdh"};
			// ��Ҫ��ȡ�ļ�ͥ��Ϣ�ֶ�
			String[] colList2 = new String[]{"jtszd","jtyb", "lxdh1", "jtcy1_xm", "jtcy1_gx", "jtcy1_gzdz", 
					"jtcy1_lxdh1", "jtcy2_xm", "jtcy2_gx", "jtcy2_gzdz", "jtcy2_lxdh1",
					"jtcy3_xm", "jtcy3_gx","jtcy3_gzdz","jtcy3_lxdh1"};
			
			// ��ѯѧ���Ƿ���Υ�ʹ�����Ϣ
			PjpyCommonInterface pjInterface = new PjpyCommonInterface();
			HashMap<String, String> rs = new HashMap<String, String>();
			rs.put("xh", xh);
			// ͳһ�ŵ�ѧ����ϢMap��
	        stuInf.put("ywwjcf", pjInterface.queryStuCfxx(rs).size()>0 ? "��" : "��");
			stuInf.putAll(CommonQueryDAO.commonQueryOne("view_xsxxb", colList1, "xh",xh));
			stuInf.putAll(CommonQueryDAO.commonQueryOne("view_xsjtxx",colList2,"xh",xh));
		}
		
		return stuInf;
	}
	
	/**
	 * �ж��Ƿ����ڱ���ˣ�ͨ���жϸ���Ա����ֶΣ�����Ա�Ѿ�����ˣ��������
	 * @param tableName
	 * @param fields
	 * @param pkVal
	 * @return
	 */
	public boolean isAuditing(String xh, String xn){
		boolean isFlag = false;
		
		// ֻ���жϸ���Ա�Ƿ���ˣ�ѧԺ��ѧУ���ͨ��������Աһ���Ѿ�ͨ��
		String[] colList = new String[]{"fdysh"};
		String querry = " where xh='"+xh+"' and "+ "xn='"+ xn+"'";
		
		// ��ȡ����Ա����ֶ�
		Map<String, String> map = CommonQueryDAO.dao_getInfo(tableName, colList, querry);
		
		// �ж��Ƿ������
		if(map!=null){
			if("ͨ��".equals( map.get("fdysh"))){
				isFlag = true;
			}
		}
		return isFlag;
	}
	
	/**
	 * ��userType �ֳ����࣬����������Ա��ѧԺ��ѧУ
	 * @param session
	 * @return
	 */
	public String getUserType(HttpSession session){
		boolean isFdy = Boolean.valueOf(session.getAttribute("isFdy").toString());
		String userType = (String)session.getAttribute("userType");
		
		if(isFdy){
			userType = "fdy";
		}else if("xy".equalsIgnoreCase(userType)){
			userType = "xy";
		}else if("admin".equalsIgnoreCase(userType) || ("xx".equalsIgnoreCase(userType))){
			userType = "xx";
		}
		return userType;
	}
	
	/** ���ĳѧ�������й�����ѧ������Ϣ
	 * @param xh
	 * @return
	 */
	public List<String[]> getXsgjzxdkxx(String xh){
		String query = " where xh=?";
		String[] colList = new String[]{"xh","xn","xm","xb","bjmc",
				"dkje","hkfs","fdysh","xysh","xxsh"};
		
		List<String[]> list = CommonQueryDAO.commonQueryNotFy(viewName, query, new String[]{xh}, colList, "");
		return list;
	}
	
	/**
	 * �õ�������ѧ������Ϣ
	 * @param pkValue
	 * @return
	 */
	public Map<String, String> getGjzxdkxx(String pkValue){
		Map<String, String> map = new HashMap<String, String>();
		if(!StringUtils.isNull(pkValue)){
			// ��Ҫ��õ��ֶ�
			String[] colList = new String[]{"xh", "xn", "jzr1", "jzr2", "sfzh1", "sfzh2", "xxqkztpj",
					"bjgbxkms", "sxnpddd", "ywblxyjl", "dkje", "hkfs", "fdysh", "xysh", "xxsh", "fdypy",
					"xyshyj", "xxshyj", "xm", "xb", "nj", "csrq", "xz", "sfzh", "xymc", "zymc", "bjmc",
					"sjhm", "ssbh", "qsdh","lxdh", "lxdh1", "jtszd", "jtyb", "byny", "jtcy1_xm", "jtcy1_gx",
					"jtcy1_gzdz", "jtcy1_lxdh1", "jtcy2_xm", "jtcy2_gx", "jtcy2_gzdz", "jtcy2_lxdh1",
					"jtcy3_xm","jtcy3_gx","jtcy3_gzdz","jtcy3_lxdh1","fdyshsj", "sqsj", "xyshsj", "xxshsj"};
			
			// ��ȡ����ֵΪpkValue�Ĵ�����Ϣ
			map = CommonQueryDAO.commonQueryOne(viewName, colList, "xh||xn", pkValue);
			// ��Ӵ�����Ϣ
			PjpyCommonInterface pjInterface = new PjpyCommonInterface();
			HashMap<String, String> rs = new HashMap<String, String>();
			rs.put("xh", map.get("xh"));
			map.put("ywwjcf", pjInterface.queryStuCfxx(rs).size()>0 ? "��" : "��");
		}
		return map;
	}
	
	/**
	 * ��map����ļ�ͥ��Ա1���ͼ�ͥ��Ա2,��ͥ��Ա3 ת��Ϊ��ĸ��
	 * @param stuInf
	 * @throws NullStringException 
	 */
	public void changeRelationToParents(Map<String, String> stuInf) throws NullStringException{
		String[] relation = new String[]{stuInf.get("jtcy1_gx"), stuInf.get("jtcy2_gx"), stuInf.get("jtcy3_gx")};
		for(int i=0;i<relation.length;i++){
			int temp = i+1;
			String xm = "jtcy" + temp + "_xm";
			String gzdz = "jtcy" + temp + "_gzdz";
			String lxdh = "jtcy" + temp + "_lxdh1";
			
			// �жϼ�ͥ��Ա1�Ƿ�Ϊ����
			if(relation[i] != null && (relation[i].contains("��") || relation[i].contains("��"))){
				// stuInf�з��븸����Ϣ
				stuInf.put("fqxm", stuInf.get(xm));
				stuInf.put("fqdw", stuInf.get(gzdz));
				stuInf.put("fqsj", stuInf.get(lxdh));
			}
			
			// stuInf�з���ĸ����Ϣ
			if(relation[i] != null && (relation[i].contains("ĸ") || relation[i].contains("��"))){
				// stuInf�з���ĸ����Ϣ
				stuInf.put("mqxm", stuInf.get(xm));
				stuInf.put("mqdw", stuInf.get(gzdz));
				stuInf.put("mqsj", stuInf.get(lxdh));
			}
			
		}
	
	}
	
	
	/**
	 * ���ѧ��ÿһѧ��������ϸ��Ϣ
	 * @param xh
	 */
	public Map<String, Map<String, String>> getMxndkxx(String xh){ 
		// ��ø�ѧ��������ѧ��
		XszzCommonN05Service service = new XszzCommonN05Service();
		List<HashMap<String, String>> list = service.getStuZxxn(xh);
		
		// ���ڼ�������ܶ�
		double amount = 0;
		
		// ���ÿѧ�����ϸ��Ϣ��keyΪ�ڼ�ѧ�꣬valueΪ��ѧ�����ϸ��Ϣ,
		Map<String, Map<String, String>> dkxx = new HashMap<String, Map<String, String>>();
		// ��ÿ��ѧ������ϸ��Ϣ
		for(int i=0; i<list.size(); i++){
			
			String key = "xn" + (i+1);
			// ûѧ�����ϸ��Ϣ
			Map<String, String> map = null;
			String xn = list.get(i).get("xn");
			String[] colList = new String[]{"dkje","xxsh"};
			if(StringUtils.isNotNull(xn)){
				String querry = " where xh='"+xh+"' and "+ "xn='"+ xn+"'";
				map = CommonQueryDAO.dao_getInfo(tableName, colList, querry);
				
				// �������,����ͨ�����������룬δ����
				if(map!=null && !map.isEmpty()){
					amount += Double.parseDouble(map.get("dkje"));
					if("ͨ��".equalsIgnoreCase(map.get("xxsh"))){
						map.put("sqzk", "������ɹ�");
					}else{
						map.put("sqzk", "����������");
					}
				}else{
					map = new HashMap<String, String>();
					map.put("sqzk", "δ����");
					map.put("dkje", "0");
				}
			}
			
			dkxx.put(key, map);
		}
		
		// ��������ܶ�
		Map<String, String> dkze = new HashMap<String, String>();
		dkze.put("dkze", String.valueOf(amount));
		dkxx.put("dkze",dkze);
		return dkxx;
	}
}
