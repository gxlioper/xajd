package xgxt.dwjl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.GetTime;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���⽻��ģ��Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-01-14</p>
 */
public class DwjlService {
	
	DwjlDAO dao = new DwjlDAO();
	
	/**
	 * ��ȡѧ�����⽻��������Ϣ
	 * @param model
	 * @return HashMap< String, String>
	 * */
	public HashMap< String, String> getDwjlsqInfo(DwjlForm model){
		HashMap<String, String> map = new HashMap<String, String>();
		
		map = dao.getStuInfo(model.getXh());
		map.putAll(dao.getDwjlxmInfo(model.getXmdm()));
		map.put("bjdypm", model.getBjdypm());
		map.put("njzypm", model.getNjzypm());
		map.put("tcah", model.getTcah());
		map.put("shgzqk", model.getShgzqk());
		map.put("sqly", model.getSqly());
		return map;
	}
	
	/**
	 * ��ȡѧ��������⽻����ѧ����Ϣ
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getDwjljxjsqInfo(DwjlForm model){
		HashMap<String, String> map = new HashMap<String, String>();
		map = dao.getStuInfo(model.getXh());
		map.putAll(dao.getDwjlxmInfo(model.getXmdm()));
		map.put("sqje", model.getSqje());
		map.put("jlbx", model.getJlbx());
		return map;
	}
	
	/**
	 * ��ȡѧ��������⽻����ѧ����Ϣ
	 * @param xh
	 * @param xmdm
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getDwjljxjsqInfo(String xh, String xmdm){
		return dao.selectDwjljxjsq(xh,xmdm);
	}
	
	/**
	 * ��ѯ���⽻����Ϣ
	 * @param xmdm
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryDwjlxx(String xmdm){
		return dao.selectDwjlxx(xmdm);
	}
		
	/**
	 * ��ȡѧ������ĵ�λ��¼��Ŀ
	 * @param xh
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryXsdwjlxm(String xh){
		return dao.selectXsdwjlxmList(xh);
	}
	/**
	 * ��ȡѧ��������Ϣ
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuInfo(String xh){
		return dao.getStuInfo(xh);
	}
	
	/**
	 * ��ȡѧ�����������ѧ��Ϣ
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuCglxsq(DwjlForm model){
		return dao.getStuCglxsq(model);
	}
	
	/**
	 * ���������������ѧ��Ϣ
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean saveCglxsq(DwjlForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String tableName = "xscgsqb";
		String pk = "xh||sqrq";
		String xh = DealString.toGBK(model.getXh());
		String qqh = DealString.toGBK(model.getQqh());
		String xl = DealString.toGBK(model.getXl());
		String jdxx = DealString.toGBK(model.getJdxx());
		String dzyx = DealString.toGBK(model.getDzyx());
		String cet = DealString.toGBK(model.getCet());
		String tem = DealString.toGBK(model.getTem());
		String toeft = DealString.toGBK(model.getToeft());
		String jzxm = DealString.toGBK(model.getJzxm());
		String jzgzdw = DealString.toGBK(model.getJzgzdw());
		String jtdz = DealString.toGBK(model.getJtdz());
		String xx = DealString.toGBK(model.getXx());
		String gj = DealString.toGBK(model.getGj());
		String sfzzlxxx = DealString.toGBK(model.getSfzzlxxx());
		String lxdh = DealString.toGBK(model.getLxdh());
		String sqrq = DealString.toGBK(model.getSqrq());
		sqrq = sqrq == null || "".equalsIgnoreCase(sqrq) ? GetTime.getSystemTime() : sqrq;
		
		String pkValue = xh+sqrq;
		String[] columns = {"xh", "sqrq", "xl", "jdxx", "cet", "tem", "toeft", "jzxm", "jzgzdw", "jtdz", "xx", "gj", "qqh", "dzyx", "lxdh", "sfzzlxxx"};
		String[] values = {xh,sqrq,xl,jdxx,cet,tem,toeft,jzxm,jzgzdw,jtdz,xx,gj,qqh,dzyx,lxdh,sfzzlxxx};
		
		if(dao.checkExists(tableName, pk, pkValue)){
			//update			
			columns = new String[]{ "xl", "jdxx", "cet", "tem", "toeft", "jzxm", "jzgzdw", "jtdz", "xx", "gj", "qqh", "dzyx", "lxdh", "sfzzlxxx"};
			values = new String[]{xl,jdxx,cet,tem,toeft,jzxm,jzgzdw,jtdz,xx,gj,qqh,dzyx,lxdh,sfzzlxxx};
			flag = StandardOperation.update(tableName, columns, values, pk, pkValue, request);
		}else{
			//insert
			flag = StandardOperation.insert(tableName, columns, values, request);
		}		
		return flag;
	}
	
	/**
	 * ������ѧ�����ѯ��ͷ
	 * @return List
	 * */
	public List getCglxsqToptr(){
		String[] column = {"����","xh","xm", "xb","xymc","bjmc","gj","xx","sqrq","xysh","xxsh"};
		String[] colCN = dao.getColumnNameCN(column, "view_cgsqxx");		
		return dao.arrayToList(column, colCN);
	}
	
	/**
	 * ������ѧ������Ϣ��ѯ
	 * @param model
	 * @return List
	 * */
	public List selectCglxsq(DwjlForm model){
		return dao.selectCglxsq(model);
	}
	
	/**
	 * ɾ��ѧ��������ѧ�������
	 * @param pk
	 * @param request
	 * @return boolean
	 * @throws Excepton
	 * */
	public boolean cglxsqDel(String pk, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String[] pkValues = pk.split("!!");
		String message = "";
		for(int i=0; i<pkValues.length; i++){
			if(pkValues[i] != null && !"".equalsIgnoreCase(pkValues[i])){
				flag = StandardOperation.delete("xscgsqb", "xh||sqrq", pkValues[i], request);
				if(!flag){			
					message += "��" + i + "����¼ɾ��ʧ�ܣ�\n";
				}
			}
		}		
		request.setAttribute("mes", message);
		return flag;
	}
	
	/**
	 * ��ѯ������ѧ���������Ϣ
	 * @param model
	 * @return List
	 * */
	public List selectCglxsqsh(DwjlForm model){
		return dao.selectCglxsqsh(model);
	}
	
	/**
	 * ��ȡ����б�
	 * @param type
	 * @return List
	 * */
	public List getChkList(int type){
		return dao.getChkList(type);
	}
	
	/**
	 * ������ѧ��˽������
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean saveCglxsh(DwjlForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String xh = DealString.toGBK(model.getXh());
		String sqrq = DealString.toGBK(model.getSqrq());		
		String yesNo = DealString.toGBK(model.getYesNo());
		String userType = model.getUserType();
		String[] column = {};
		
		if(userType != null && "xy".equalsIgnoreCase(userType)){
			column = new String[]{"xysh"};
		}else if(userType != null && ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType))){
			column = new String[]{"xxsh"};
		}
		
		flag = StandardOperation.update("xscgsqb", column, new String[]{yesNo}, "xh||sqrq", xh+sqrq, request);
		return flag;
	}
	
	/**
	 * ������ѧ�����������
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean cglxsqplsh(DwjlForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
	    String pk = model.getPk();
	    String tableName = "xscgsqb";
	    String userType = model.getUserType();
	    String message = "";
	    String yesNo = model.getYesNo();
		String[] values = pk.split("!!");
		
		String[] columns = {};
		if(userType != null && "xy".equalsIgnoreCase(userType)){
			columns = new String[]{"xysh"};
		}else if(userType != null && ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType))){
			columns = new String[]{"xxsh"};
		}
		
		for(int i=0; i<values.length; i++){
			if(values[i] != null && !"".equalsIgnoreCase(values[i])){
				flag = StandardOperation.update(tableName, columns, new String[]{yesNo}, "xh||sqrq", values[i], request);
				if(!flag){
					message += "��" + (i+1) + "����¼���ʧ�ܣ�";
				}
			}
		}		
		return flag;
	}	
	
	/**
	 * ���⽻�������������
	 * @param model
	 * @param request
	 * @throws Exception
	 * */
	public boolean dwjlsqsh(DwjlForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String yesNo = model.getYesNo();
		String[] columns = {"xxsh","xxzs"};
		String tableName = "dwjlsqb";
		String[] pkValue = model.getPk().split("!!");
		yesNo = yesNo.equalsIgnoreCase("1") ? "ͨ��" : "��ͨ��";
		String message = "";
		String userType=model.getUserType();
		
		if ("xy".equalsIgnoreCase(userType)) {
			for (int i = 0; i < pkValue.length; i++) {
				if (pkValue[i] != null && !"".equalsIgnoreCase(pkValue[i])) {
					flag = StandardOperation.update(tableName,
							new String[] { "xysh" }, new String[] { yesNo },
							"xn||nd||xh||jlxmdm", pkValue[i], request);
					if (!flag) {
						message += "��" + (i + 1) + "����¼����ʧ��!\n";
					}
				}
			}
		} else {
			for (int i = 0; i < pkValue.length; i++) {
				if (pkValue[i] != null && !"".equalsIgnoreCase(pkValue[i])) {
					flag = StandardOperation.update(tableName, columns,
							new String[] { yesNo, yesNo },
							"xn||nd||xh||jlxmdm", pkValue[i], request);
					if (!flag) {
						message += "��" + (i + 1) + "����¼����ʧ��!\n";
					}
				}
			}
		}
		request.setAttribute("mesage", message);
		return flag;
	}
	
	/**
	 * ���⽻����ѧ�������������
	 * @param model
	 * @param request
	 * @throws Exception
	 * */
	public boolean dwjljxjsqsh(DwjlForm model, HttpServletRequest request)
			throws Exception {
		boolean flag = false;
		String yesNo = model.getYesNo();
		String[] columns = { "xxsh" };
		String tableName = "dwjljxjsqb";
		String[] pkValue = model.getPk().split("!!");
		yesNo = yesNo.equalsIgnoreCase("1") ? "ͨ��" : "��ͨ��";
		String message = "";
		String userType = model.getUserType();

		if ("xy".equalsIgnoreCase(userType)) {
			for (int i = 0; i < pkValue.length; i++) {
				if (pkValue[i] != null && !"".equalsIgnoreCase(pkValue[i])) {
					flag = StandardOperation.update(tableName,
							new String[] { "xysh" }, new String[] { yesNo },
							"xn||nd||xh||jlxmdm", pkValue[i], request);
					if (!flag) {
						message += "��" + (i + 1) + "����¼����ʧ��!\n";
					}
				}
			}
		} else {
			for (int i = 0; i < pkValue.length; i++) {
				if (pkValue[i] != null && !"".equalsIgnoreCase(pkValue[i])) {
					flag = StandardOperation.update(tableName, columns,
							new String[] { yesNo }, "xn||nd||xh||jlxmdm",
							pkValue[i], request);
					if (!flag) {
						message += "��" + (i + 1) + "����¼����ʧ��!\n";
					}
				}
			}
		}
		request.setAttribute("mesage", message);
		return flag;
	}
	
	/**
	 * ��ȡ���⽻����Ŀ�б�
	 * @return List
	 * */
	public List getDwjlxmList(){
		return dao.getDwjlxmList();
	}
	
	/**
	 * ��ȡ�����¼��Ŀ��Ϣ
	 * @param xn
	 * @param nd
	 * @param xq
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryDwjlxmdmList(String xn, 
			                                               String nd, 
			                                               String xq){
		return dao.queryDwjlxmList(xn, nd, xq);
	}
}
