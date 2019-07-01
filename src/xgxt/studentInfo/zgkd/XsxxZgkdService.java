package xgxt.studentInfo.zgkd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �й���ҵ��ѧѧ����ϢService
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-09-03</p>
 */
public class XsxxZgkdService {
	XsxxZgkdDAO dao = new XsxxZgkdDAO();
	
	/**
	 * ��ȡѧ����Ϣ�������ֶ� 
	 * @return List
	 * */
	public List<HashMap<String, String>> getXsxxzdList(){	
		String xxdm = StandardOperation.getXxdm();
		return dao.getXsxxzdList(xxdm);
	}
	
	/**
	 * ��ȡѧ����ͥ��Ϣ�������ֶ� 
	 * @return List
	 * */
	public List<HashMap<String, String>> getJtxxzdList(){
		String xxdm = StandardOperation.getXxdm();
		return dao.getJtxxzdList(xxdm);
	}
	
	/**
	 * �����û����ͻ�ȡ��ά�����ֶ��б�
	 * @param yhjs
	 * @param tableName
	 * @return List
	 * */
	public List getZdByYhList(String yhjs,String tableName){
		return dao.getWhzdByYh(yhjs,tableName);
	}
	
	/**
	 * ��ȡ�û��б�
	 * @return List
	 * */
	public List<HashMap<String, String>> getYhList(){
		String xxdm = StandardOperation.getXxdm();
		return dao.getYhList(xxdm);
	}
	
	/**
	 * ����ѧ����Ϣ�޸�Ȩ����Ϣ
	 * @param model
	 * @return boolean
	 * */
	public boolean savePower(XsxxZgkdForm model,HttpServletRequest request){
		return dao.savePower(model,request);
	}
	
	/**
	 * ��ȡ�����б�
	 * */
	public List getMzList(){
		return dao.getMzList();
	}
	
	/**
	 * ��ȡ������ò�б�
	 * */
	public List getZzmmList(){
		return dao.getZzmmList();
	}
	
	/**
	 * ��ѯ��ѧ����ѧ����ά�����ֶ�
	 * @param yhjs
	 * @return String
	 * */
	public String getzdNoXh(String yhjs,String tableName){
		return dao.getzdNoXh(yhjs,tableName);
	}
	
	/**
	 * ��ѯ��ѧ����ѧ����ά�����ֶ�
	 * @param yhjs
	 * @return String
	 * */
	public String getzdNoXh(String yhjs){
		return dao.getzdNoXh(yhjs);
	}
	
	/**
	 * ��ѯѧ��������Ϣ�ͼ�ͥ��Ϣ
	 * @param xh
	 * @return List
	 * */
	public HashMap<String, String> getStuAndFamily(String xh,String yhjs){
		String xxdm = StandardOperation.getXxdm();
		return dao.getStuAndFamily(xh,yhjs,xxdm);
	}
	
	/**
	 * �ж��Ƿ����޸�ѧ����Ϣ��ʱ�����
	 * @return String
	 * */
	public String isSqqjFlag(){
		return dao.isSqqjFlag();
	}
	
	/**
	 * ����ѧ���޸ĵ���Ϣ
	 * @param yhjs
	 * @param xh
	 * @param request
	 * @return boolean
	 * */
	public boolean saveStuinfoModi(String yhjs,String xh, HttpServletRequest request){
		boolean flag = false;
		String tableName = "xsxx_xsxgxxb";
		String[] zdCol = dao.getZdinfo(yhjs);
		String[] zdVal = new String[zdCol.length];
		String[] zd = new String[zdCol.length+1];
		String[] val = new String[zdVal.length+1];
		
		for(int i=0; i<zdCol.length; i++){
			zdVal[i] = DealString.toGBK(request.getParameter(zdCol[i]));
			zd[i] = zdCol[i];
			val[i] = zdVal[i];
		}
		try {			
			zd[zd.length-1] = "xh";
			val[val.length-1] = xh;
			flag = StandardOperation.delete(tableName, "xh", xh, request);
			flag = StandardOperation.insert(tableName, zd, val, request);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * ����ѧԺ�޸ĵ���Ϣ
	 * @param yhjs
	 * @param xh
	 * @param request
	 * @return boolean
	 * */
	public boolean saveStuinfoByXy(String yhjs,String xh, HttpServletRequest request){
		boolean flag = false;
		String tableName = "xsxxb";
		String[] columns = dao.getZdInfoByTab(yhjs, tableName);
		String[] zdCol = null;
		boolean hasXh = false;
		for(int i=0; i<columns.length ; i++){
			if(columns[i].equalsIgnoreCase("xh")){
				hasXh = true;
			}
		}
		if(!hasXh){
			zdCol = new String[columns.length+1];
		}else{
			zdCol = new String[columns.length];
		}
		for(int i=0; i<columns.length ; i++){
			zdCol[i] = columns[i];
		}
		
		if(!hasXh){
			zdCol[columns.length] = "xh";
		}
		String[] zdVal = new String[zdCol.length];
		
		for(int i=0; i<zdCol.length; i++){
			zdVal[i] = DealString.toGBK(request.getParameter(zdCol[i]));
		}
		try {	
			//����ѧ��������Ϣ
			if(!dao.isExists(xh, tableName)){
				if(!dao.isExists(xh, "view_xsjbxx")){
					flag = StandardOperation.insert(tableName, zdCol, zdVal, request);
					
				}else{
					flag = StandardOperation.update(tableName, "insert into xsxxb(xh,xm,xb,nj,xydm,zydm,bjdm,xz,xy,zymc,bjmc)(select xh,xm,xb,nj,xydm,zydm,bjdm,xz,xymc,zymc,bjmc from view_xsjbxx where xh='"+xh+"')", request);
				}
			}
			flag = StandardOperation.update(tableName, zdCol, zdVal, "xh", xh, request);
			//if(!"xx".equalsIgnoreCase(yhjs)){
				//����ѧ����ͥ��Ϣ
				tableName = "xsfzxxb";
				String[] result = dao.getZdInfoByTab(yhjs, tableName);//������ѧ���ֶ� 
				zdCol = new String[result.length+1];
				for(int i=0; i<result.length; i++){
					zdCol[i] = result[i];
				}
				
				zdCol[result.length] = "xh";
				zdVal = new String[zdCol.length];
				String[] zd = new String[zdCol.length];
				String[] val = new String[zdCol.length];
				for(int i=0; i<zdCol.length; i++){//��ȡҳ���ֶε�ֵ
					zdVal[i] = DealString.toGBK(request.getParameter(zdCol[i]));
					zd[i] = zdCol[i];
					val[i] = zdVal[i];
				}
				
				if(!dao.isExists(xh, tableName)){//ѧ����ͥ��Ϣ�в����ڸ�ѧ������Ϣ
					flag = StandardOperation.insert(tableName, zd, val, request);
				}else{
					flag = StandardOperation.update(tableName, zd, val, "xh", xh, request);
				}
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if("student".equalsIgnoreCase(yhjs) && flag){//ѧ��ȷ����Ϣ
			try{
				flag = StandardOperation.update("xsxxb", new String[]{"xsqrxxbz"},new String[]{"��"}, "xh", xh, request);
			}catch(Exception e){
				flag = false;
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	/**
	 * ����ѧԺ�޸ĵ���Ϣ
	 * @param yhjs
	 * @param xh
	 * @param request
	 * @return boolean
	 * */
	public boolean saveStuinfoByXy(String xh, HttpServletRequest request){
		XsxxglService xsxxglService = new XsxxglService();
		boolean dzxxqdm = "yes".equalsIgnoreCase(xsxxglService.getXsxxCs().get("dzxxqdm"));
		boolean flag = false;
		String tableName = "xsxxb";
		String[] columns = dao.getZdInfoByTab("student", tableName);
		String[] zdCol = null;
		boolean hasXh = false;
		for(int i=0; i<columns.length ; i++){
			if(columns[i].equalsIgnoreCase("xh")){
				hasXh = true;
			}
		}
		if(!hasXh){
			zdCol = new String[columns.length+1];
		}else{
			zdCol = new String[columns.length];
		}
		for(int i=0; i<columns.length ; i++){
			zdCol[i] = columns[i];
		}
		
		if(!hasXh){
			zdCol[columns.length] = "xh";
		}
		String[] zdVal = new String[zdCol.length];
		if(dzxxqdm){//��ַ��Ϣȡ����
			for(int i=0; i<zdCol.length; i++){
				if("syd".equalsIgnoreCase(zdCol[i])){
					zdVal[i] = StringUtils.joinStr(request.getParameter("syshen"),
							   "/" ,
							   request.getParameter("syshi"),
							   "/", 
							   request.getParameter("syxian"));
				}else if("jg".equalsIgnoreCase(zdCol[i])){
					zdVal[i] = StringUtils.joinStr(request.getParameter("jgshen"),
							   "/" ,
							   request.getParameter("jgshi"),
							   "/", 
							   request.getParameter("jgxian"));
				}else if("hkszd".equalsIgnoreCase(zdCol[i])){
					zdVal[i] = StringUtils.joinStr(request.getParameter("hkshen"),
							   "/" ,
							   request.getParameter("hkshi"),
							   "/", 
							   request.getParameter("hkxian"));
				}else{
					zdVal[i] = DealString.toGBK(request.getParameter(zdCol[i]));
				}
			}
		}else{
			for(int i=0; i<zdCol.length; i++){
				zdVal[i] = DealString.toGBK(request.getParameter(zdCol[i]));
			}
		}
		
		try {	
			if(zdCol != null && zdCol.length>0){
				//����ѧ��������Ϣ
				if(!dao.isExists(xh, tableName)){
					if(!dao.isExists(xh, "view_xsjbxx")){
						flag = StandardOperation.insert(tableName, zdCol, zdVal, request);
						
					}else{
						flag = StandardOperation.update(tableName, "insert into xsxxb(xh,xm,xb,nj,xydm,zydm,bjdm,xz,xy,zymc,bjmc,xjztm)(select xh,xm,xb,nj,xydm,zydm,bjdm,xz,xymc,zymc,bjmc,xjztm from view_xsjbxx where xh='"+xh+"')", request);
					}
				}
				flag = StandardOperation.update(tableName, zdCol, zdVal, "xh", xh, request);
			}
			//����ѧ����ͥ��Ϣ
			tableName = "xsfzxxb";
			String[] result = dao.getZdInfoByTab("student", tableName);//������ѧ���ֶ� 
			zdCol = new String[result.length];
			for(int i=0; i<result.length; i++){
				zdCol[i] = result[i];
			}
			zdVal = new String[zdCol.length];
			String[] zd = new String[zdCol.length];
			String[] val = new String[zdCol.length];
			for(int i=0; i<zdCol.length; i++){//��ȡҳ���ֶε�ֵ
				zdVal[i] = DealString.toGBK(request.getParameter(zdCol[i]));
				zd[i] = zdCol[i];
				val[i] = zdVal[i];
			}
			if(zd != null && zd.length>0){
				if(!dao.isExists(xh, tableName)){//ѧ����ͥ��Ϣ�в����ڸ�ѧ������Ϣ
					//=============2011.7.4 edit by luojw===========================
					List<String> zdList = new ArrayList<String>();
					for(int i=0;i<zd.length;i++){
						zdList.add(zd[i]);
					}
					zdList.add("xh");
					
					List<String> valList = new ArrayList<String>();
					for (int i = 0; i < val.length; i++) {
						valList.add(val[i]);
					}
					valList.add(xh);
					
					flag = StandardOperation.insert(tableName, zdList.toArray(new String[]{}), valList.toArray(new String[]{}), request);
				}else{
					flag = StandardOperation.update(tableName, zd, val, "xh", xh, request);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	/**
	 * ����ѧ����Ϣ
	 * @param yhjs
	 * @param xh
	 * @param request
	 * @return boolean
	 * */
	public boolean saveStuinfoByXy(String yhjs,String xh,String tableName, HttpServletRequest request){
		XsxxglService xsxxglService = new XsxxglService();
		boolean dzxxqdm = false;//"yes".equalsIgnoreCase(xsxxglService.getXsxxCs().get("dzxxqdm"));
		boolean flag = false;
		String[] zdCol = dao.getZdInfoByTab(yhjs, tableName);
		String[] zdVal = new String[zdCol.length+1];
		
		zdCol = StringUtils.joinStrArr(zdCol, new String[]{"xh"});
		
		if(dzxxqdm){//��ַ��Ϣȡ����
			for(int i=0; i<zdCol.length; i++){
				if("syd".equalsIgnoreCase(zdCol[i])){
					zdVal[i] = StringUtils.joinStr(request.getParameter("syshen"),
							   "/" ,
							   request.getParameter("syshi"),
							   "/", 
							   request.getParameter("syxian"));
				}else if("jg".equalsIgnoreCase(zdCol[i])){
					zdVal[i] = StringUtils.joinStr(request.getParameter("jgshen"),
							   "/" ,
							   request.getParameter("jgshi"),
							   "/", 
							   request.getParameter("jgxian"));
				}else{
					zdVal[i] = DealString.toGBK(request.getParameter(zdCol[i]));
				}
			}
		}else{
			for(int i=0; i<zdCol.length; i++){
				zdVal[i] = DealString.toGBK(request.getParameter(zdCol[i]));
			}
		}
		try {	
			//����ѧ��������Ϣ
			if(tableName != null && tableName.equalsIgnoreCase("xsxxb")){
				if(!dao.isExists(xh, tableName)){
					if(!dao.isExists(xh, "view_xsjbxx")){
						flag = StandardOperation.insert(tableName, zdCol, zdVal, request);
					}else{
						flag = StandardOperation.update(tableName, "insert into xsxxb(xh,xm,xb,nj,xydm,zydm,bjdm,xz,xy,zymc,bjmc)(select xh,xm,xb,nj,xydm,zydm,bjdm,xz,xymc,zymc,bjmc from view_xsjbxx where xh='"+xh+"')", request);
					}
				}
				flag = StandardOperation.update(tableName, zdCol, zdVal, "xh", xh, request);
			}
			//����ѧ����ͥ��Ϣ
			zdCol = dao.getZdInfoByTab(yhjs, tableName);//������ѧ���ֶ�
			zdVal = new String[zdCol.length];
			String[] zd = new String[zdCol.length+1];
			String[] val = new String[zdCol.length+1];
			for(int i=0; i<zdCol.length; i++){//��ȡҳ���ֶε�ֵ
				zdVal[i] = DealString.toGBK(request.getParameter(zdCol[i]));
				zd[i] = zdCol[i];
				val[i] = zdVal[i];
			}
			zd[zd.length-1] = "xh";
			val[zd.length-1] = xh;
			
			if(tableName !=null && tableName.equalsIgnoreCase("xsfzxxb")){
				if(!dao.isExists(xh, tableName)){//ѧ����ͥ��Ϣ�в����ڸ�ѧ������Ϣ
					flag = StandardOperation.insert(tableName, zd, val, request);
				}else{
					flag = StandardOperation.update(tableName, zd, val, "xh", xh, request);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * ����ѧ��ѧϰ������Ϣ
	 * @param xh
	 * @param request
	 * */
	public boolean saveXsxxjlxx(String xh, HttpServletRequest request){
		String tableName = "xsxx_xsxxjlb";
		boolean flag = false;
		String[] input = {"xh","jl1_qzny","jl1_xxjgzdw","jl1_xxhrzw","jl2_qzny",
				          "jl2_xxjgzdw","jl2_xxhrzw","jl3_qzny","jl3_xxjgzdw",
				          "jl3_xxhrzw","jl4_qzny","jl4_xxjgzdw","jl4_xxhrzw",
				          "jl5_qzny","jl5_xxjgzdw","jl5_xxhrzw","jl6_qzny",
				          "jl6_xxjgzdw","jl6_xxhrzw"};
		String[] value = new String[input.length];
		for(int i=0; i<value.length; i++){
			value[i] = request.getParameter(input[i]);
		}
		value[0] = xh;
		if(!dao.isExists(xh, tableName)){//ѧ����ͥ��Ϣ�в����ڸ�ѧ������Ϣ
			flag = StandardOperation.insert(tableName, input, value, request);
		}else{
			try {
				flag = StandardOperation.update(tableName, input, value, "xh", xh, request);
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				flag = false;
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	/**
	 * ��ѯѧ���޸ĵ���Ϣ
	 * 
	 * @param model
	 * @return List
	 * @throws Exception
	 */
	public List<String[]> getStuInfoAudi(XsxxZgkdForm model,
			HttpServletRequest request) throws Exception {
		return dao.selectModiStuinfo(model, request);
	}
	
	/**
	 * ��ȡ��ѯ�����ͷ
	 * @return List 
	 * */
	public List getTopTr(){
		return dao.getTopTr();
	}
	
	/**
	 * ��ȡ����б�
	 * @param int
	 * @return List  
	 * */
	public List getChkList(int type){
		return dao.getChkList(type);
	}
	
	/**
	 * ��ȡѧ���޸�ǰ����Ϣ
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getNewValue(String xh){
		HashMap<String, String> map = dao.getNewValue(xh);
		XsxxglDAO xsxxglDao = new XsxxglDAO();
		if ("new".equalsIgnoreCase(Base.getEdition())){
			//�°�
			//������ת��������
			map.put("pycc", xsxxglDao.dmToMc("dtjs_xsccb", "xsccdm", "xsccmc", map.get("pycc")));
			
			//�����ᡢ��Դ�ء��������ڵز��Ϊʡ���С���
			String[] arrDq = {};
			if(StringUtils.isNotNull(map.get("jg"))){
				arrDq = map.get("jg").split("/");
			}
			map.put("jgshen", (arrDq != null && arrDq.length>=1)?arrDq[0]:"");
			map.put("jgshi", (arrDq != null && arrDq.length>=2)?arrDq[1]:"");
			map.put("jgxian", (arrDq != null && arrDq.length>=3)?arrDq[2]:"");
			
			//��Դ��
			if(StringUtils.isNotNull(map.get("syd"))){
				arrDq = map.get("syd").split("/");
			}else{
				arrDq = new String[]{};
			}
			map.put("syshen", (arrDq != null && arrDq.length>=1)?arrDq[0]:"");
			map.put("syshi", (arrDq != null && arrDq.length>=2)?arrDq[1]:"");
			map.put("syxian", (arrDq != null && arrDq.length>=3)?arrDq[2]:"");
			//�������ڵ�		
			if(StringUtils.isNotNull(map.get("hkszd"))){
				arrDq = map.get("hkszd").split("/");
			}
			map.put("hkshen",  (arrDq != null && arrDq.length>=1)?arrDq[0]:"");
			map.put("hkshi",  (arrDq != null && arrDq.length>=2)?arrDq[1]:"");
			map.put("hkxian",  (arrDq != null && arrDq.length>=3)?arrDq[2]:"");
			//����ת��������
			map.put("syd", xsxxglDao.dzxxdmToMc(map.get("syd")));
			map.put("jg", xsxxglDao.dzxxdmToMc(map.get("jg")));
			map.put("hkszd", xsxxglDao.dzxxdmToMc(map.get("hkszd")));
		}
		return map;
	}
	
	/**
	 * ��ȡѧ���޸�ǰ����Ϣ
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getNewValue(String xh,boolean dmtomc){
		HashMap<String, String> map = dao.getNewValue(xh);
		XsxxglDAO xsxxglDao = new XsxxglDAO();
		if ("new".equalsIgnoreCase(Base.getEdition())){
			//�°�
			if(dmtomc){
				//������ת��������
				map.put("pycc", xsxxglDao.dmToMc("dtjs_xsccb", "xsccdm", "xsccmc", map.get("pycc")));
				map.put("syd", xsxxglDao.dzxxdmToMc(map.get("syd")));
				map.put("jg", xsxxglDao.dzxxdmToMc(map.get("jg")));
				map.put("hkszd", xsxxglDao.dzxxdmToMc(map.get("hkszd")));
			}else{			
				//�����ᡢ��Դ�ء��������ڵز��Ϊʡ���С���
				String[] arrDq = {};
				if(StringUtils.isNotNull(map.get("jg"))){
					arrDq = map.get("jg").split("/");
				}
				map.put("jgshen", (arrDq != null && arrDq.length>=1)?arrDq[0]:"");
				map.put("jgshi", (arrDq != null && arrDq.length>=2)?arrDq[1]:"");
				map.put("jgxian", (arrDq != null && arrDq.length>=3)?arrDq[2]:"");
				
				//��Դ��
				if(StringUtils.isNotNull(map.get("syd"))){
					arrDq = map.get("syd").split("/");
				}else{
					arrDq = new String[]{};
				}
				map.put("syshen", (arrDq != null && arrDq.length>=1)?arrDq[0]:"");
				map.put("syshi", (arrDq != null && arrDq.length>=2)?arrDq[1]:"");
				map.put("syxian", (arrDq != null && arrDq.length>=3)?arrDq[2]:"");
				//�������ڵ�		
				if(StringUtils.isNotNull(map.get("hkszd"))){
					arrDq = map.get("hkszd").split("/");
				}
				map.put("hkshen",  (arrDq != null && arrDq.length>=1)?arrDq[0]:"");
				map.put("hkshi",  (arrDq != null && arrDq.length>=2)?arrDq[1]:"");
				map.put("hkxian",  (arrDq != null && arrDq.length>=3)?arrDq[2]:"");
			}
		}
		return map;
	}
	
	/**
	 * ��ȡѧ���޸ĺ����Ϣ 
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getOldValue(String xh){
		HashMap<String, String> map = dao.getOldValue(xh);
		XsxxglDAO xsxxglDao = new XsxxglDAO();
		if ("new".equalsIgnoreCase(Base.getEdition())){
			//�°�
			map.put("syd", xsxxglDao.dzxxdmToMc(map.get("syd")));
			map.put("jg", xsxxglDao.dzxxdmToMc(map.get("jg")));
			map.put("hkszd", xsxxglDao.dzxxdmToMc(map.get("hkszd")));
			//������ת��������
			map.put("pycc", xsxxglDao.dmToMc("dtjs_xsccb", "xsccdm", "xsccmc", map.get("pycc")));
		}
		return map;
	}
	
	/**
	 * ������˽��
	 * @param xh
	 * @param yesNo
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveAuditing(String xh, String yesNo,HttpServletRequest request) throws Exception{
		boolean flag = false;
		String yhjs = "student";
		String tabName = "xsxxb";
		if("true".equalsIgnoreCase(request.getSession().getAttribute("fdyQx").toString()) 
				&& Globals.XXDM_BJQNZZXY.equalsIgnoreCase(Base.xxdm)){
			//�޸���˽��
			flag = StandardOperation.update("xsxx_xsxgxxb", new String[]{"fdysh"}, new String[]{yesNo}, "xh", xh, request);
		}else if("xy".equalsIgnoreCase(request.getSession().getAttribute("userType").toString())
				&& Globals.XXDM_BJQNZZXY.equalsIgnoreCase(Base.xxdm)){
			//ѧԺ�û������
			return true;
		}else{
			if(yesNo.equalsIgnoreCase("ͨ��")){
				//�޸�ѧ��������Ϣ
				String[] cols = dao.getZdInfoByTab(yhjs,tabName);
				String[] value = dao.getOneRs("select * from xsxx_xsxgxxb where xh=?", new String[]{xh}, cols);
				if(!dao.isExists(xh, tabName)){
					//���ѧ����xsxxb��û�м�¼���Ȳ��� 
					StandardOperation.update(tabName, "insert into xsxxb(xh,xm,xb,nj,xz,xjztm,xydm,zydm,bjdm)(select xh,xm,xb,nj,xz,xjztm,xydm,zydm,bjdm from view_xsjbxx where xh='"+xh+"')", request);
				}
				flag = StandardOperation.update(tabName, cols, value, "xh", xh, request);
				
				//�޸�ѧ����ͥ��Ϣ
				tabName = "xsfzxxb";
				cols = dao.getZdInfoByTab(yhjs, tabName);
				value = dao.getOneRs("select * from xsxx_xsxgxxb where xh=?", new String[]{xh}, cols);
				if(!dao.isExists(xh, tabName)){
					//���ѧ����xsfzxxb��û�м�¼���Ȳ��� 
					StandardOperation.update(tabName, "insert into xsfzxxb(xh) values '"+xh+"'", request);
				}
				flag = StandardOperation.update(tabName, cols, value, "xh", xh, request);
				
				if(!Globals.XXDM_BJQNZZXY.equalsIgnoreCase(Base.xxdm)){
					//ɾ���޸ĵ���Ϣ
					flag = StandardOperation.delete("xsxx_xsxgxxb", "xh", xh, request);
				}else{
					//ѧ����Ϣ�޸����ͨ����Ͳ������޸���
					//�޸���˽��
					flag = StandardOperation.update("xsxx_xsxgxxb", new String[]{"shjg"}, new String[]{yesNo}, "xh", xh, request);
				}
			}else{
				//�޸���˽��
				flag = StandardOperation.update("xsxx_xsxgxxb", new String[]{"shjg"}, new String[]{yesNo}, "xh", xh, request);
			}
		}
		return flag;
	}
	
	/**
	 * �������
	 * @param PkValue
	 * @param yesNo
	 * @param request
	 * @return boolean
	 * */
	public boolean saveAutiBatch(String[] PkValue, String yesNo, HttpServletRequest request) throws Exception{
		boolean flag = false;
		for(int i=0; i<PkValue.length; i++){
			flag = saveAuditing(PkValue[i],yesNo,request);
		}
		return flag;
	}
	
	/**
	 * ��ȡƶ�����ȼ�
	 * */
	public String getPksdj(String xh){
		return dao.getPksdj(xh);
	}
	
	/**
	 * �ж�ѧУ�Ƿ����ͨ��
	 * @param xh
	 * @return String
	 * */
	public String getXxshjg(String xh){
		return dao.getXxshjg(xh);
	}
	
	/**
	 * �����޸ĵ��ֶ�
	 * */
	public boolean saveXgzdxx(String xh, String xgzd, User user){
		boolean flag = dao.saveXgzdxx(xh,xgzd,user);
		return flag;
	}
	
	/**
	 * ��ȡ�޸ĵ��ֶ���Ϣ
	 * */
	public HashMap<String, String> getXgzdxx(String xh){		
		return dao.selectXgzdxx(xh);
	}
}
