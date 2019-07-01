package xgxt.qgzx.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.qgzx.hngydx.dao.HngydxGwglDAO;
import xgxt.qgzx.xcxy.dao.QgzxXcxyDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import common.Globals;


/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �ڹ���ѧģ��Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 2.0</p>
 * <p>Time: 2008-06-05</p>
 */
public class QgzxService {	
	QgzxDao qgzxDao = new QgzxDao();
	
	/**
	 * ��ѯ��ǰ�¸�λ����ѧ����λ����ǰ�ĸ�λ��Ϣ
	 * @param ffList
	 * @param pk
	 * @param pkValue
	 * @return ArrayList
	 * */
	public List<HashMap<String, String>> queryTzqxsgw(List<HashMap<String, String>> ffList,String pk,String pkValue){
		ffList = qgzxDao.getTzqgw(pkValue);
		return ffList;
	}
	
	
	/**
	 * ��ѯ��𷢷�����·ݸ�λ����ѧ����λ����ǰ�ĸ�λ��Ϣ
	 * @param ffList
	 * @param pk
	 * @param pkValue
	 * @return ArrayList
	 * */
	public List<HashMap<String, String>> queryTzqxsgw(List<HashMap<String, String>> ffList,String pkValue,String nd,String yf){		
		ffList = qgzxDao.getTzqgw(pkValue,nd,yf);		
		return ffList;
	}
	
	/**
	 * ����ɽ��ѧ��ȡ��λҪ����Ϣ
	 * @param pk
	 * @param pkValue
	 * @return String
	 * */
	public String getGwyqInfo(String pk, String pkValue){	
		String sDemand = "";
		String sTemPk = "gwdm||gwsbsj";
		pkValue = qgzxDao.getGwPk(pk,pkValue);
		
		String[] demand = qgzxDao.getSqtjString(sTemPk, pkValue);
		if(demand.length>0){
			sDemand = demand[0];
		}
		return sDemand;
	}
	
	/**
	 * ��ѯѧ�������λ������Ϣ
	 * @param map
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> addOtherInfo(HashMap<String, String> map){
		String xxdm = StandardOperation.getXxdm();
		String sTsxx = "";	//��ʾ��Ϣ
		String xh = map.get("xh");
		String djsj = map.get("djsj");//�Ƿ񶮼����		
		String pkValue = "";
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)){
			//����ɽ
			//��ѯѧ�������ϸ�λ�����Ҫ�����Ŀ
			pkValue = qgzxDao.getGwPk("xh||gwdm||sqsj",map.get("xh||gwdm||sqsj"),"-");
			//��ʾ��Ϣ
			sTsxx = qgzxDao.isStudentCondOK(xh, pkValue, djsj);
			map.put("tsxx", sTsxx);
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
			//�人����ѧ ������ְҪ��
			map.putAll(qgzxDao.getRzyqInfo(map));			
		}	
		return map;
	}
	
	/**
	 * ��ѯѧ�������λ������Ϣ
	 * @param map
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> addOtherInfo(HashMap<String, String> map,String type){
		String xxdm = StandardOperation.getXxdm();
		String sGwyq = "";		
		String pkValue = "";
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)){
			//����ɽ
			//��ȡ��λ������� ����λҪ��
			pkValue = map.get("xmdm");
			String[] gwyq = qgzxDao.getSqtjString("gwdm||'-'||gwsbsj", pkValue);
			if(gwyq!=null && gwyq.length>0){
				sGwyq = gwyq[0].equalsIgnoreCase("0") ? "" : gwyq[0];
			}
			map.put("gwyq", sGwyq);
		}
		
		return map;
	}
	
	/**
	 * ��λ�������
	 * @param CommanForm model
	 * @param String type
	 * @param String userType
	 * @param String userName
	 * @return boolean
	 * */
	public boolean postBatchAudi(CommanForm model,String type,String userType,String userName){
		boolean flag = false;
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		HngydxGwglDAO gwDao = new HngydxGwglDAO();
		String sql = "";
		String zd = "";
		String yesNo = "";
		String[] value = model.getPkV();
		String[] sqlValue = null;
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HNGYDX)
				||Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//���Ϲ�ҵ�͹��ݴ�ѧ���˵�λ���
			if(gwDao.checkIsYrdw(userName)){
				userType = "yrdw";
			}
		}
		//�ж��û���ѧУ�������˵�λ
		if(userType.equalsIgnoreCase("xx")||userType.equalsIgnoreCase("admin")){
			zd = " shjg ";
		}
		if(userType.equalsIgnoreCase("yrdw")){
			zd = " yrdwsh ";
		}
		if(userType.equalsIgnoreCase("xy") 
				&& Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
;			zd=	" yrdwsh ";
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY)){//������ѧԺһ�����
			zd = " shjg ";
		}
		//�ж���˽����ͨ�����ǲ�ͨ��
		if(type.equalsIgnoreCase("pass")){
			yesNo = "ͨ��";
		}
		if(type.equalsIgnoreCase("nopass")){
			yesNo = "��ͨ��";
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){//�й����ʴ�ѧѧУһ�����
			zd = "shjg";
		}
		//���sql  
		for(int i=0; i<value.length; i++){
			if(zd!=null && !"".equalsIgnoreCase(zd)){
				sql += "update gwxxb set " + zd + "='" + yesNo +"',sqsyknss=syknss,sqsyrs=xyrs,spbcbz=jybcbz where gwdm||gwsbsj='" + value[i]+ "'";
				sql += "!!#!!";
			}
		}
		sqlValue = sql.split("!!#!!");
		try {
			//����ִ���޸�
			dao.runBatch(sqlValue);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag ;
	}
	
	/**
	 * ѧ����λ����������������ж�
	 * @param pkValue
	 * @param type
	 * @param userType
	 * @param userName
	 * @return boolean 
	 * */
	public HashMap<String, String> postStuBatchAudi(String pkValue,String type,String userType,String userName){
		boolean flag = false;
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		HngydxGwglDAO gwDao = new HngydxGwglDAO();
		HashMap<String, String> resultMap = new HashMap<String, String>();

		
		String sql = "";
		String sTempSql = "";
		String zd = "";
		String yesNo = "";
		String whereSql = "";
		String[] value = pkValue.split("!!SplitOneSplit!!");
		String[] sqlValue = null;	
		String[] tempValue = null;
		
				
		//����Ա
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX) || xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_HBJTZYJSXY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_WHSYFWXY)){			
			String num = dao.getOneRs("select count(1) count from fdyxxb where zgh='" + userName + "'", new String[]{},"count");
			if(Integer.parseInt(num)>0){
				zd = "fdyyj";
			}			
		}		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HNGYDX) ||xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY) || xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
			//���Ϲ�ҵ || �й�����ѧԺ ||������
			if(userType != null && "xy".equalsIgnoreCase(userType)){
				zd = "fdyyj";
			}
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_SZWBZYJSXY)){
			//������һְҵ����ѧԺ
			if(userType != null && "xy".equalsIgnoreCase(userType)){
				zd = "xyyj";
			}
		}
		//ѧУ���
		if(!(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)
//				|| xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY))){
			if(userType.equalsIgnoreCase("xx")||userType.equalsIgnoreCase("admin")){
				zd = " xxyj ";
			}			
		}
		
		//���˵�λ���
		if(!xxdm.equalsIgnoreCase(Globals.XXDM_YNYS) 
				&& !xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY) 
				&& !xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
			if(gwDao.checkIsYrdw(userName)){
				zd = "xyyj";
			}
		}
		// ============= begin ���ΰ 2009/3/31 ================
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			if (userType.equalsIgnoreCase("xy")) {
				whereSql = " and xxyj<>'ͨ��'";
				zd = "xyyj";
			}
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY)//������ѧԺ
				|| xxdm.equalsIgnoreCase(Globals.XXDM_ZJJSZYJSXY)){//�㽭����ְҵ����ѧԺ��2011.12.5 qlj��
			zd = "xxyj";
		}
		// ============= end ���ΰ 2009/3/31 ================
		//�ж���˽����ͨ�����ǲ�ͨ��
		if(type.equalsIgnoreCase("pass")){
			yesNo = "ͨ��";
		}
		if(type.equalsIgnoreCase("nopass")){
			yesNo = "��ͨ��";
		}		
		//���sql  
		String msg = "";//���������Ϣ
		for(int i=0; i<value.length; i++){
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
				//�й����ʴ�ѧ���˵�λһ�����
				sql += "update xsgwxxb set xxyj='" + yesNo + "' where xh||gwdm||sqsj='" + value[i]+ "'";
				sql += "!!#!!";
			}else if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(xxdm)){
				//�㽭�Ƽ�
//				if("xyyj".equalsIgnoreCase(zd) && "ͨ��".equalsIgnoreCase(yesNo)){
//					//ѧԺ���ͨ�� �ж��Ƿ��Ѿ��и�λ���ͨ����
//					boolean resultFlag = qgzxDao.checkYjbgwlq(value[i]);
//					if(resultFlag){
//						msg += "��" + (i+1) + "�����ݲ���ʧ�ܣ���ѧ���Ѿ��и�λ���ͨ���ˣ� \n" ;
//						flag = false;
//						continue;
//					}
//				}
				if(StringUtils.isNotNull(zd)){
					sql="update xsgwxxb set " + zd + "='" + yesNo + "' where xh||gwdm||sqsj='" + value[i]+ "'";
					try{
						flag = dao.runUpdate(sql,new String[]{});
					}catch(Exception e){
						flag = false;
						msg += "��" + (i+1) + "�����ݲ���ʧ�ܣ� \n" ;
					}
				}				
			}else{
				if("ͨ��".equalsIgnoreCase(yesNo)){
					//�ж���������
					String[] result = qgzxDao.checkPostStuAudi(value[i], zd);
					if(result != null && result.length>0 && StringUtils.isNull(result[0])){
						if(zd!=null && !"".equalsIgnoreCase(zd))
							try {
								sql = "update xsgwxxb set " + zd + "='" + yesNo + "' where xh||gwdm||sqsj='" + value[i]+ "'" + whereSql;
								dao.runUpdate(sql, new String[]{});
								sql="";
							} catch (Exception e) {
								e.printStackTrace();
							}
					}else{
						for(String str : result){
							if(StringUtils.isNotNull(str)){
								msg += str;
							}
						}
					}
				}else{
					if(zd!=null && !"".equalsIgnoreCase(zd)){				
						sql += "update xsgwxxb set " + zd + "='" + yesNo + "' where xh||gwdm||sqsj='" + value[i]+ "'" + whereSql;
						sql += "!!#!!";
					}
				}
			}
		}
		sqlValue = sql.split("!!#!!");
		if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC) || xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
			//�Ϻ����̼�����ѧ 
			tempValue = sTempSql.split("!!#!!");
		}
		try {
			//����ִ���޸�
			if(sqlValue!=null && !sqlValue.equals("")){
				dao.runBatch(sqlValue);
				if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
					//�Ϻ����̼�����ѧ 				
					dao.runBatch(tempValue);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultMap.put("flag", flag+"");
		resultMap.put("msg", msg);
		return resultMap ;
	}
	
	

	public HashMap<String, String> postStuBatchAudi(String pkValue,String type,String userType,String userName,String isFdy,String isBzr){
		boolean flag = false;
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		HngydxGwglDAO gwDao = new HngydxGwglDAO();
		HashMap<String, String> resultMap = new HashMap<String, String>();

		
		String sql = "";
		String sTempSql = "";
		String zd = "";
		String yesNo = "";
		String whereSql = "";
		String[] value = pkValue.split("!!SplitOneSplit!!");
		String[] sqlValue = null;	
		String[] tempValue = null;
		
				
		//����Ա
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX) || xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_HBJTZYJSXY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_WHSYFWXY)){			
			String num = dao.getOneRs("select count(1) count from fdyxxb where zgh='" + userName + "'", new String[]{},"count");
			if(Integer.parseInt(num)>0){
				zd = "fdyyj";
			}			
		}		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HNGYDX) ||xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY) || xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
			//���Ϲ�ҵ || �й�����ѧԺ ||������
			if(userType != null && "xy".equalsIgnoreCase(userType)){
				zd = "fdyyj";
			}
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_SZWBZYJSXY)){
			//������һְҵ����ѧԺ
			if(userType != null && "xy".equalsIgnoreCase(userType)){
				zd = "xyyj";
			}
		}
		//ѧУ���
		if(!(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)
//					|| xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY))){
			if(userType.equalsIgnoreCase("xx")||userType.equalsIgnoreCase("admin")){
				zd = " xxyj ";
			}			
		}
		
		//���˵�λ���
		if(!xxdm.equalsIgnoreCase(Globals.XXDM_YNYS) 
				&& !xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY) 
				&& !xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
			if(isFdy.equals("true")){
				zd="fdyyj";
			}else if(gwDao.checkIsYrdw(userName)){
				zd = "xyyj";
			}
		}
		// ============= begin ���ΰ 2009/3/31 ================
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			if (userType.equalsIgnoreCase("xy")) {
				whereSql = " and xxyj<>'ͨ��'";
				zd = "xyyj";
			}
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY)//������ѧԺ
				|| xxdm.equalsIgnoreCase(Globals.XXDM_ZJJSZYJSXY)){//�㽭����ְҵ����ѧԺ��2011.12.5 qlj��
			zd = "xxyj";
		}
		// ============= end ���ΰ 2009/3/31 ================
		//�ж���˽����ͨ�����ǲ�ͨ��
		if(type.equalsIgnoreCase("pass")){
			yesNo = "ͨ��";
		}
		if(type.equalsIgnoreCase("nopass")){
			yesNo = "��ͨ��";
		}		
		//���sql  
		String msg = "";//���������Ϣ
		for(int i=0; i<value.length; i++){
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
				//�й����ʴ�ѧ���˵�λһ�����
				sql += "update xsgwxxb set xxyj='" + yesNo + "' where xh||gwdm||sqsj='" + value[i]+ "'";
				sql += "!!#!!";
			}else if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(xxdm)){
				//�㽭�Ƽ�
//					if("xyyj".equalsIgnoreCase(zd) && "ͨ��".equalsIgnoreCase(yesNo)){
//						//ѧԺ���ͨ�� �ж��Ƿ��Ѿ��и�λ���ͨ����
//						boolean resultFlag = qgzxDao.checkYjbgwlq(value[i]);
//						if(resultFlag){
//							msg += "��" + (i+1) + "�����ݲ���ʧ�ܣ���ѧ���Ѿ��и�λ���ͨ���ˣ� \n" ;
//							flag = false;
//							continue;
//						}
//					}
				if(StringUtils.isNotNull(zd)){
					sql="update xsgwxxb set " + zd + "='" + yesNo + "' where xh||gwdm||sqsj='" + value[i]+ "'";
					try{
						flag = dao.runUpdate(sql,new String[]{});
					}catch(Exception e){
						flag = false;
						msg += "��" + (i+1) + "�����ݲ���ʧ�ܣ� \n" ;
					}
				}				
			}else{
				if("ͨ��".equalsIgnoreCase(yesNo)){
					//�ж���������
					String[] result = qgzxDao.checkPostStuAudi(value[i], zd);
					if(result != null && result.length>0 && StringUtils.isNull(result[0])){
						if(zd!=null && !"".equalsIgnoreCase(zd))
							try {
								sql = "update xsgwxxb set " + zd + "='" + yesNo + "' where xh||gwdm||sqsj='" + value[i]+ "'" + whereSql;
								dao.runUpdate(sql, new String[]{});
								sql="";
							} catch (Exception e) {
								e.printStackTrace();
							}
					}else{
						for(String str : result){
							if(StringUtils.isNotNull(str)){
								msg += str;
							}
						}
					}
				}else{
					if(zd!=null && !"".equalsIgnoreCase(zd)){				
						sql += "update xsgwxxb set " + zd + "='" + yesNo + "' where xh||gwdm||sqsj='" + value[i]+ "'" + whereSql;
						sql += "!!#!!";
					}
				}
			}
		}
		sqlValue = sql.split("!!#!!");
		if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC) || xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
			//�Ϻ����̼�����ѧ 
			tempValue = sTempSql.split("!!#!!");
		}
		try {
			//����ִ���޸�
			if(sqlValue!=null && !sqlValue.equals("")){
				dao.runBatch(sqlValue);
				if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
					//�Ϻ����̼�����ѧ 				
					dao.runBatch(tempValue);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultMap.put("flag", flag+"");
		resultMap.put("msg", msg);
		return resultMap ;
	}

	/**
	 * �ڹ���ѧ��У���б�
	 * @return List
	 * */
	public List<HashMap<String, String>> getXiaoquList(){
		return qgzxDao.getXiaoquList();
	}
	
	/**
	 * ��𷢷�
	 * @return List
	 * */
	public List<HashMap<String, String>> getGwxzCjList(){
		return qgzxDao.getGwxzCjList();
	}
	
	/**
	 * �ڹ���ѧ�и�λ�����б�
	 * @return List
	 * */
	public List<HashMap<String, String>> getGwxzList(){
		return qgzxDao.getGwxzList();
	}
	
	/**
	 * �ڹ���ѧ�����˵�λ�б�
	 * @return List
	 * */
	public List<HashMap<String, String>> getYrdwList(){
		return qgzxDao.getYrdwList();
	}
	/**
	 * �ڹ���ѧ�����˵�λ�б�
	 * @return List
	 * */
	public List<HashMap<String, String>> getYrdwList(String userName){
		return qgzxDao.getYrdwList(userName);
	}
	
	/**
	 * �ڹ���ѧ�����ö��б�
	 * @return List
	 * */
	public List<HashMap<String, String>> getXydList(){
		return qgzxDao.getXydList();
	}
	
	/**
	 * ��ȡ�ڹ���ѧ�����趨��Ϣ
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getSqsjInfo(){
		return qgzxDao.getSqsjInfo();
	}
	
	/**
	 * �й�����ѧԺѧ���������˻�ȡ��ϸʱ��
	 * */
	public HashMap<String, String> getDetailsDaytime(HashMap<String, String> map,String pkValue){
		double zsj = 0;
		for(int i=1; i<32; i++){			
			String values = map.get("day" + i);			
			String bValues = "";
			String aValues = "";
			if(values.indexOf(":")>0){
				aValues = values.substring(values.indexOf(":")+1,values.length());
				bValues = values.substring(0, values.indexOf(":"));
			}
			bValues = bValues == null || "".equalsIgnoreCase(bValues) ? "-" : bValues;
			aValues = aValues == null || "".equalsIgnoreCase(aValues) ? "-" : aValues;
			
			String ksxs = bValues.substring(0,bValues.indexOf("-"));
			String ksfz = bValues.substring(bValues.indexOf("-")+1,bValues.length());
			String jsxs = aValues.substring(0,aValues.indexOf("-"));
			String jsfz = aValues.substring(aValues.indexOf("-")+1,aValues.length());
			
			map.put("day"+i+"_ksxs",ksxs);
			map.put("day" + i + "_ksfz", ksfz);
			map.put("day" + i + "_jsxs", jsxs);
			map.put("day" + i + "_jsfz", jsfz);
			
			ksxs = ksxs == null || "".equalsIgnoreCase(ksxs) ? "0" : ksxs;
			ksfz = ksfz == null || "".equalsIgnoreCase(ksfz) ? "0" : ksfz;
			jsxs = jsxs == null || "".equalsIgnoreCase(jsxs) ? "0" : jsxs;
			jsfz = jsfz == null || "".equalsIgnoreCase(jsfz) ? "0" : jsfz;
			
			zsj += ((Integer.parseInt(jsxs)-Integer.parseInt(ksxs))*60 + (Integer.parseInt(jsfz)-Integer.parseInt(ksfz)))/60;			
		}			
		//��������ʱ����ܳ��
		String bcbz = qgzxDao.getBcbzByStuPk(pkValue);
		String je = qgzxDao.getZbcje(bcbz, zsj);
		
		map.put("spbcbz", bcbz);
		map.put("zcj", je);
		map.put("zsj", String.valueOf(zsj));
		return map;
	}
	

	/**
	 * �㶫Ů���ڹ���ѧ���ʱ����ӡ
	 * @param wwb
	 * @return void	 
	 * */
	@SuppressWarnings("unchecked")
	public void printPayRePortGdnzzyjsxy(WritableWorkbook wwb){		
		String xxmc = StandardOperation.getXxmc();
		String xxdm = StandardOperation.getXxdm();		
		String yf = qgzxDao.getCurrentYf();
		String nd = qgzxDao.getSqsjInfo().get("nd");
		String cjffsj = qgzxDao.getQgzxConf().get("cjffsj");
		
		if(cjffsj != null && !"".equalsIgnoreCase(cjffsj)){
			nd = cjffsj.substring(0,4);
			yf = cjffsj.substring(4,6);
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
			//�㽭����
			HashMap<String, String> map = qgzxDao.getZjjdCjffsj();
			nd = map.get("nd");
			yf = map.get("yf");
		}
//		yf=""+Integer.parseInt(yf);
		String totalMoney = qgzxDao.getTotalMoney(nd,yf);
		
		List list = qgzxDao.getPayInfo(nd,yf);		
		try{
		 WritableSheet ws = wwb.getSheet(0);
		 WritableCellFormat wcfTytle = new WritableCellFormat();
		 Alignment alignMent = Alignment.CENTRE;
		 VerticalAlignment vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(14);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,0,xxmc + "ѧ���ڹ���ѧ���ʷ��ű�" ,wcfTytle));	
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.LEFT;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,2,"ʱ�䣺" + (StringUtils.isNull(nd) ? "" : nd )+ "��" + yf + "�·�",wcfTytle));
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(10);
		 wcfTytle.setFont(wfTytle);
		 
		 
		 ws.addCell(new Label(0, 3, "���", wcfTytle));
		 ws.addCell(new Label(1, 3, "ѧ��", wcfTytle));
		 ws.addCell(new Label(2, 3, "����", wcfTytle));
		 ws.addCell(new Label(3, 3, "������λ", wcfTytle));
		 ws.addCell(new Label(4, 3, "����ʱ��", wcfTytle));
		 ws.addCell(new Label(5, 3, "��Ԫ��", wcfTytle));
		 
		 for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map = (HashMap<String, String>) list.get(i);
				ws.addCell(new Label(0, 4 + i, String.valueOf(i + 1),wcfTytle));
				ws.addCell(new Label(1, 4 + i, map.get("xh"), wcfTytle));
				ws.addCell(new Label(2, 4 + i, map.get("xm"), wcfTytle));
				ws.addCell(new Label(3, 4 + i, map.get("gwdm"), wcfTytle));
				ws.addCell(new Label(4, 4 + i, map.get("gzsj"), wcfTytle));
				ws.addCell(new Label(5, 4 + i, map.get("cjje"), wcfTytle));
				// totalMoney = map.get("totalMoney");
			}
		 totalMoney = totalMoney == null || "".equalsIgnoreCase(totalMoney)? "0" : totalMoney;
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
				ws.mergeCells(0, list.size() + 4, 6, list.size() + 4);
			} else {
				ws.mergeCells(0, list.size() + 4, 5, list.size() + 4);
		}
		 
		 ws.addCell(new Label(0,list.size()+4,"�ϼƽ��:" + totalMoney,wcfTytle));
		 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * �ڹ���ѧ���ʱ����ӡ
	 * @param wwb
	 * @return void	 
	 * */
	@SuppressWarnings("unchecked")
	public void printPayReport(WritableWorkbook wwb){		
		String xxmc = StandardOperation.getXxmc();
		String xxdm = StandardOperation.getXxdm();		
		String yf = qgzxDao.getCurrentYf();
		String nd = qgzxDao.getSqsjInfo().get("nd");
		String cjffsj = qgzxDao.getQgzxConf().get("cjffsj");
		
		if(cjffsj != null && !"".equalsIgnoreCase(cjffsj)){
			nd = cjffsj.substring(0,4);
			yf = cjffsj.substring(4,6);
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
			//�㽭����
			HashMap<String, String> map = qgzxDao.getZjjdCjffsj();
			nd = map.get("nd");
			yf = map.get("yf");
		}
//		yf=""+Integer.parseInt(yf);
		String totalMoney = qgzxDao.getTotalMoney(nd,yf);
		
		List list = qgzxDao.getPayInfo(nd,yf);		
		try{
		 WritableSheet ws = wwb.getSheet(0);
		 WritableCellFormat wcfTytle = new WritableCellFormat();
		 Alignment alignMent = Alignment.CENTRE;
		 VerticalAlignment vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(14);
		 wcfTytle.setFont(wfTytle);
		 ws.mergeCells(0, 0, 6 ,1);
		 ws.addCell(new Label(0,0,xxmc + "ѧ���ڹ���ѧ���ʷ��ű�" ,wcfTytle));	
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.LEFT;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 ws.mergeCells(0, 2, 6 ,2);
		 ws.addCell(new Label(0,2,"ʱ�䣺" + (StringUtils.isNull(nd) ? "" : nd )+ "��" + yf + "�·�",wcfTytle));
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(10);
		 wcfTytle.setFont(wfTytle);
		 
		 
		 ws.addCell(new Label(0, 3, "���", wcfTytle));
		 ws.addCell(new Label(1, 3, "ѧ��", wcfTytle));
		 ws.addCell(new Label(2, 3, "����", wcfTytle));
		 ws.addCell(new Label(3, 3, "���֤��", wcfTytle));
		 ws.addCell(new Label(4, 3, "������λ", wcfTytle));
		 ws.addCell(new Label(5, 3, "����ʱ��", wcfTytle));
		 ws.addCell(new Label(6, 3, "��Ԫ��", wcfTytle));
		 
		 for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map = (HashMap<String, String>) list.get(i);
				ws.addCell(new Label(0, 4 + i, String.valueOf(i + 1),wcfTytle));
				ws.addCell(new Label(1, 4 + i, map.get("xh"), wcfTytle));
				ws.addCell(new Label(2, 4 + i, map.get("xm"), wcfTytle));
				ws.addCell(new Label(3, 4 + i, map.get("sfzh"), wcfTytle));
				ws.addCell(new Label(4, 4 + i, map.get("gwdm"), wcfTytle));
				ws.addCell(new Label(5, 4 + i, map.get("gzsj"), wcfTytle));
				ws.addCell(new Label(6, 4 + i, map.get("cjje"), wcfTytle));
				// totalMoney = map.get("totalMoney");
			}
		 totalMoney = totalMoney == null || "".equalsIgnoreCase(totalMoney)? "0" : totalMoney;
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		
		ws.mergeCells(0, list.size() + 4, 6, list.size() + 4);
		
		 
		 ws.addCell(new Label(0,list.size()+4,"�ϼƽ��:" + totalMoney,wcfTytle));
		 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ������һְҵ����ѧԺ�ڹ���ѧ���ʱ����ӡ
	 * @param wwb
	 * @return void	 
	 * */
	public void printPayRePortNbtyzyjsxy(WritableWorkbook wwb,String userName){	
		String yf = qgzxDao.getCurrentYf();
		String nd = qgzxDao.getSqsjInfo().get("nd");
		String cjffsj = qgzxDao.getQgzxConf().get("cjffsj");
		String bmmc = qgzxDao.getYrdwmcByUser(userName);
		bmmc = StringUtils.isNull(bmmc) ? "ȫ��" : bmmc;
		
		if(cjffsj != null && !"".equalsIgnoreCase(cjffsj)){
			nd = cjffsj.substring(0,4);
			yf = cjffsj.substring(4,6);
		}
		String totalMoney = qgzxDao.getTotalMoney(nd,yf);
		float totalTime = 0;
		List<HashMap<String, String>> list = qgzxDao.getPayInfoOfNbty(nd,yf,userName);		
		try{
		 WritableSheet ws = wwb.getSheet(0);
		 WritableCellFormat wcfTytle = new WritableCellFormat();
		 Alignment alignMent = Alignment.CENTRE;
		 VerticalAlignment vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
//		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wcfTytle.setWrap(true);//�Զ�����
		 wfTytle.setPointSize(24);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,1,nd + "���" + yf + "�·�ѧ���ڹ���ѧ��𷢷ű�" ,wcfTytle));	
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.LEFT;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
//		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
//		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
//		 ws.addCell(new Label(2,2,bmmc,wcfTytle));//������һ����Ҫ��ʾ
		 ws.addCell(new Label(9,2,GetTime.getNowYear() + "��" + GetTime.getNowMonth() + "��" + GetTime.getNowDay() + "��",wcfTytle));//ʱ��
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(10);
		 wcfTytle.setFont(wfTytle);
		 
		 for(int i=0; i<list.size(); i++){
			 HashMap<String, String> map = new HashMap<String, String>();
			 map = (HashMap<String, String>) list.get(i);
			 ws.addCell(new Label(0,5+i,String.valueOf(i+1),wcfTytle));
			 ws.addCell(new Label(1,5+i,map.get("xm"),wcfTytle));
			 ws.addCell(new Label(2,5+i,map.get("bjmc"),wcfTytle));
			 ws.addCell(new Label(3,5+i,map.get("gznr"),wcfTytle));
			 if("��".equalsIgnoreCase(map.get("gzbx"))){
				 ws.addCell(new Label(4,5+i,"��",wcfTytle));
				 ws.addCell(new Label(5,5+i,"",wcfTytle));
				 ws.addCell(new Label(6,5+i,"",wcfTytle));
				 ws.addCell(new Label(7,5+i,"",wcfTytle));
			 }else if("��".equalsIgnoreCase(map.get("gzbx"))){
				 ws.addCell(new Label(4,5+i,"",wcfTytle));
				 ws.addCell(new Label(5,5+i,"��",wcfTytle));
				 ws.addCell(new Label(6,5+i,"",wcfTytle));
				 ws.addCell(new Label(7,5+i,"",wcfTytle));
			 }else if("�ϸ�".equalsIgnoreCase(map.get("gzbx"))){
				 ws.addCell(new Label(4,5+i,"",wcfTytle));
				 ws.addCell(new Label(5,5+i,"",wcfTytle));
				 ws.addCell(new Label(6,5+i,"��",wcfTytle));
				 ws.addCell(new Label(7,5+i,"",wcfTytle));
			 }else if("��".equalsIgnoreCase(map.get("gzbx"))){
				 ws.addCell(new Label(4,5+i,"",wcfTytle));
				 ws.addCell(new Label(5,5+i,"",wcfTytle));
				 ws.addCell(new Label(6,5+i,"",wcfTytle));				 
				 ws.addCell(new Label(7,5+i,"��",wcfTytle)); 
			 }else{
				 ws.addCell(new Label(4,5+i,"",wcfTytle));
				 ws.addCell(new Label(5,5+i,"",wcfTytle));
				 ws.addCell(new Label(6,5+i,"",wcfTytle));				 
				 ws.addCell(new Label(7,5+i,"",wcfTytle)); 
			 }
			 ws.addCell(new Label(8,5+i,map.get("gzsj"),wcfTytle));
			 ws.addCell(new Label(9,5+i,"",wcfTytle));
			 ws.addCell(new Label(10,5+i,map.get("cjje"),wcfTytle));
			 ws.addCell(new Label(11,5+i,"",wcfTytle));
			 totalTime += Float.parseFloat(StringUtils.isNull(map.get("gzsj")) ? "0" : map.get("gzsj"));
		 }
		 totalMoney = totalMoney == null || "".equalsIgnoreCase(totalMoney)? "0" : totalMoney;
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
//		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.mergeCells(0, list.size()+5, 7, list.size()+5);
		 ws.addCell(new Label(0,list.size()+5,"�ϼ�",wcfTytle));
		 ws.addCell(new Label(8,list.size()+5,totalTime+"",wcfTytle));
		 ws.addCell(new Label(9,list.size()+5,"",wcfTytle));
		 ws.addCell(new Label(10,list.size()+5,totalMoney,wcfTytle));
		 ws.addCell(new Label(11,list.size()+5,"",wcfTytle));
		 
		 ws.mergeCells(0, list.size()+5+1, 7, list.size()+5+1);
		 ws.addCell(new Label(0,list.size()+5+1,"���³��ϼƴ�д",wcfTytle));
		 ws.mergeCells(8, list.size()+5+1, 11, list.size()+5+1);
		 ws.addCell(new Label(8,list.size()+5+1,qgzxDao.getDxje(totalMoney),wcfTytle));
		 
		 ws.mergeCells(0, list.size()+5+2, 1, list.size()+5+2);
		 ws.addCell(new Label(0,list.size()+5+2,"�ù��������",wcfTytle));
		 ws.mergeCells(2, list.size()+5+2, 11, list.size()+5+2);
		 ws.addCell(new Label(2,list.size()+5+2,"ǩ��:              ��  ��  ��",wcfTytle));
		 
		 ws.mergeCells(0, list.size()+5+3, 1, list.size()+5+3);
		 ws.addCell(new Label(0,list.size()+5+3,"ѧ�����������������",wcfTytle));
		 ws.mergeCells(2, list.size()+5+3, 11, list.size()+5+3);
		 ws.addCell(new Label(2,list.size()+5+3,"ǩ��:              ��  ��  ��",wcfTytle));
		 
		 ws.mergeCells(0, list.size()+5+4, 1, list.size()+5+4);
		 ws.addCell(new Label(0,list.size()+5+4,"Ժ�쵼����",wcfTytle));
		 ws.mergeCells(2, list.size()+5+4, 11, list.size()+5+4);
		 ws.addCell(new Label(2,list.size()+5+4,"ǩ��:              ��  ��  ��",wcfTytle));
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * ������Ϣְҵ����ѧԺ�ڹ���ѧ���ʱ����ӡ
	 * @param wwb
	 * @return void	 
	 * */
	public void printPayReportCzxx(WritableWorkbook wwb){		
//		String xxmc = StandardOperation.getXxmc();
		String yf = qgzxDao.getCurrentYf();
		String nd = qgzxDao.getSqsjInfo().get("nd");
		String cjffsj = qgzxDao.getQgzxConf().get("cjffsj");
		
		if(cjffsj != null && !"".equalsIgnoreCase(cjffsj)){
			nd = cjffsj.substring(0,4);
			yf = cjffsj.substring(4,6);
		}
		String totalMoney = qgzxDao.getTotalMoney(nd,yf);
		List<HashMap<String, String>> list = qgzxDao.getPayInfo(nd,yf);		
		try{
		 WritableSheet ws = wwb.getSheet(0);
		 WritableCellFormat wcfTytle = new WritableCellFormat();
		 Alignment alignMent = Alignment.CENTRE;
		 VerticalAlignment vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(14);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,0,nd + "��" + yf + "���ڹ���ѧ��������" ,wcfTytle));	
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(10);
		 wcfTytle.setFont(wfTytle);
		 
		 //д��ͷ
		 WritableCellFormat wcf = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcf.setVerticalAlignment(vag);
		 wcf.setAlignment(alignMent);
		 wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 WritableFont font = new WritableFont(WritableFont.ARIAL);
		 font.setPointSize(10);
		 font.setBoldStyle(WritableFont.BOLD);
		 wcf.setFont(font);
		
		 int row = 0;
		 for(int i=0; i<list.size(); i++){
			 HashMap<String, String> map = new HashMap<String, String>();
			 map = (HashMap<String, String>) list.get(i);
			 if(i%43==0 && i!=0){
				 ws.addCell(new Label(0,2+row+i,"���",wcf));
				 ws.addCell(new Label(1,2+row+i,"ѧ��",wcf));
				 ws.addCell(new Label(2,2+row+i,"�༶",wcf));
				 ws.addCell(new Label(3,2+row+i,"����",wcf));
				 ws.addCell(new Label(4,2+row+i,"��λ",wcf));
				 ws.addCell(new Label(5,2+row+i,"���(Ԫ)",wcf));
				 row += 1; 				
			 }
			 ws.addCell(new Label(0,2+row+i,String.valueOf(i+1),wcfTytle));
			 ws.addCell(new Label(1,2+row+i,map.get("xh"),wcfTytle));
			 ws.addCell(new Label(2,2+row+i,map.get("bjmc"),wcfTytle));
			 ws.addCell(new Label(3,2+row+i,map.get("xm"),wcfTytle));
			 ws.addCell(new Label(4,2+row+i,map.get("gwdm"),wcfTytle));
			 ws.addCell(new Label(5,2+row+i,map.get("cjje"),wcfTytle));
		 }
		 totalMoney = totalMoney == null || "".equalsIgnoreCase(totalMoney)? "0" : totalMoney;
		 
		 ws.mergeCells(1, list.size()+2+row, 5, list.size()+2+row);
		 ws.addCell(new Label(0,list.size()+2+row,"�ϼ�:",wcfTytle));
		 ws.addCell(new Label(1,list.size()+2+row,totalMoney+"Ԫ",wcfTytle));
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.mergeCells(1, list.size()+4+row, 5, list.size()+4+row);
		 ws.addCell(new Label(1,list.size()+4+row,"��׼��                ��ˣ�                  �Ʊ����       ",wcfTytle));
		 
		 ws.mergeCells(4, list.size()+6+row, 5, list.size()+6+row);
		 ws.addCell(new Label(4,list.size()+6+row,"ʱ�䣺 " + GetTime.getNowYear() + "." + GetTime.getNowMonth()+"." + GetTime.getNowDay(),wcfTytle));
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * �㽭���ù��ʷ��ű��ӡ
	 * @param wwb
	 * @return void
	 * */
	@SuppressWarnings("unchecked")
	public void printPayReportZjjj(WritableWorkbook wwb){	
		String yf = qgzxDao.getCurrentYf();
		String nd = Base.currNd;
		List bmList = qgzxDao.getYrdwList(nd, yf);		
		try{
			 WritableSheet ws = wwb.getSheet(0);
			 WritableCellFormat wcfTytle = new WritableCellFormat();
			 Alignment alignMent = Alignment.CENTRE;
			 VerticalAlignment vag = VerticalAlignment.CENTRE;
			 
			 wcfTytle.setVerticalAlignment(vag);
			 wcfTytle.setAlignment(alignMent);
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
			 
			 WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			 wfTytle.setBoldStyle(WritableFont.BOLD);
			 wfTytle.setPointSize(14);
			 wcfTytle.setFont(wfTytle);
			 
			 
			 ws.addCell(new Label(0,0,yf + "�·�ѧ���ڹ���ѧ���ʷ��ű�" ,wcfTytle));
			 
			 wcfTytle = new WritableCellFormat();
			 alignMent = Alignment.CENTRE;
			 vag = VerticalAlignment.CENTRE;
			 
			 wcfTytle.setVerticalAlignment(vag);
			 wcfTytle.setAlignment(alignMent);
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
			 
			 wfTytle = new WritableFont(WritableFont.ARIAL);
			 wfTytle.setPointSize(10);
			 wcfTytle.setFont(wfTytle);
			 ws.removeRow(2);			 
			 ws.addCell(new Label(0,2,"����" ,wcfTytle));
			 ws.addCell(new Label(1,2,"��λ" ,wcfTytle));
			 ws.addCell(new Label(2,2,"����" ,wcfTytle));
			 ws.addCell(new Label(3,2,"�༶" ,wcfTytle));
			 ws.addCell(new Label(4,2,"���" ,wcfTytle));
			 ws.addCell(new Label(5,2,"ǩ��" ,wcfTytle));
			 
			 int bmLen = 3;  //����λ��
			 int gwLen = 3;  //��λλ��
			 int stuLen = 3; //
			 int len = 3;
			 for(int i=0; i<bmList.size(); i++){//ĳһ���˵�λ����Ϣ		 			
				 
				 //������Ϣ
				 HashMap<String, String> bmMap = (HashMap<String, String>)bmList.get(i);
				 String bmmc = bmMap.get("yrdwmc");
				 List gwList = qgzxDao.getGwList(nd , yf ,bmMap.get("yrdwdm"));
				 
				 for(int j=0; j<gwList.size(); j++){//ĳһ��λ����Ϣ
					 //��λ��Ϣ					 
					 HashMap<String, String> gwMap = (HashMap<String, String>)gwList.get(j);
					 String gwmc = gwMap.get("gwdm");
					 List stuList = qgzxDao.getStuOfPayinfo(nd,yf,gwMap.get("gwdm"),gwMap.get("gwsbsj"));	
					 
					 for(int k=0; k<stuList.size(); k++){//ĳһѧ������Ϣ
						 //ѧ����Ϣ
						 HashMap<String, String> stuMap = (HashMap<String, String>) stuList.get(k);	
						 List list = qgzxDao.getStuList(nd, yf, gwMap.get("gwdm"),gwMap.get("gwsbsj") ,stuMap.get("xh"));
						 for(int m=0; m<list.size(); m++){//ĳһѧ���ķ��ż�¼
							 HashMap<String, String> map = (HashMap<String, String>)list.get(m);
							 String je = map.get("cjje");
							 String bz = map.get("jebz");
							 bz = bz == null ? "" : bz;
							 //len += m;
							 if(!"".equalsIgnoreCase(bz)){
								 ws.addCell(new Label(4,len+m,je + "("+ bz + ")",wcfTytle));
							 }else{
								 ws.addCell(new Label(4,len+m,je,wcfTytle));
							 }							 
							 ws.addCell(new Label(5,len+m,"",wcfTytle));
							 
						 }
						 len += list.size()-1;
						 //����
						 ws.mergeCells(2, stuLen, 2, len);//��ֱ�ϲ�
						 ws.addCell(new Label(2,stuLen,stuMap.get("xm"),wcfTytle));
						 //�༶
						 ws.mergeCells(3, stuLen, 3, len);//��ֱ�ϲ�
						 ws.addCell(new Label(3,stuLen ,stuMap.get("bjmc"),wcfTytle));
						 len += list.size()>=1?1:0;
						 stuLen = len;						 
					 }
					 
					 ws.mergeCells(1, gwLen, 1, len-1);//��ֱ�ϲ�
					 //��λ����
					 ws.addCell(new Label(1,gwLen,gwmc,wcfTytle));
					 gwLen = len;
				 }
				 ws.mergeCells(0, bmLen, 0, len-1);//��ֱ�ϲ�
				 //���˵�λ����
				 ws.addCell(new Label(0,bmLen ,bmmc,wcfTytle));
				 bmLen = len;
			 }
		}catch(Exception e){
			e.printStackTrace();
		}
		 //��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ����ѧԺ�ڹ���ѧ��𷢷ű����ӡ
	 * @param wwb
	 * */
	@SuppressWarnings("unchecked")
	public void printPayReportXcxy(WritableWorkbook wwb){	
		String yf = qgzxDao.getCurrentYf();
		String nd = qgzxDao.getSqsjInfo().get("nd");
		String cjffsj = qgzxDao.getQgzxConf().get("cjffsj");
		QgzxXcxyDAO xcxyDao = new QgzxXcxyDAO();
		
		if(cjffsj != null && !"".equalsIgnoreCase(cjffsj)){
			nd = cjffsj.substring(0,4);
			yf = cjffsj.substring(4,6);
		}
		String totalMoney = qgzxDao.getTotalMoney(nd,yf);
		List list = xcxyDao.getPayInfoForXcxy(nd,yf);
		try{
		 WritableSheet ws = wwb.getSheet(0);
		 WritableCellFormat wcfTytle = new WritableCellFormat();
		 Alignment alignMent = Alignment.CENTRE;
		 VerticalAlignment vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(14);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,0,nd+"��" + yf + "���ڹ���ѧ���ʷ��ű�" ,wcfTytle));	
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(10);
		 wcfTytle.setFont(wfTytle);
		 
		 for(int i=0; i<list.size(); i++){
			 HashMap<String, String> map = new HashMap<String, String>();
			 map = (HashMap<String, String>) list.get(i);
			 ws.addCell(new Label(0,3+i,map.get("xm"),wcfTytle));
			 ws.addCell(new Label(1,3+i,map.get("xymc")+map.get("zymc"),wcfTytle));
			 ws.addCell(new Label(2,3+i,map.get("jcbz"),wcfTytle));
			 ws.addCell(new Label(3,3+i,map.get("gzsj"),wcfTytle));
			 ws.addCell(new Label(4,3+i,map.get("cjje"),wcfTytle));
			 ws.addCell(new Label(5,3+i,map.get("kh"),wcfTytle));
			 ws.addCell(new Label(6,3+i,map.get("bz"),wcfTytle));
		 }
		 totalMoney = totalMoney == null || "".equalsIgnoreCase(totalMoney)? "0" : totalMoney;
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ���ݴ�ѧ�¹��ʱ���ӡ
	 * @param WritableWorkbook wwb
	 * */
	public void printPayReportGzdx(WritableWorkbook wwb, 
			                       String userName, 
			                       String gwxzmc){
		String xxmc = StandardOperation.getXxmc();
		String yf = qgzxDao.getCurrentYf();
		String nd = qgzxDao.getSqsjInfo().get("nd");
		String cjffsj = qgzxDao.getQgzxConf().get("cjffsj");
		String bmmc = qgzxDao.getYrdwmcByUser(userName);
		bmmc = StringUtils.isNull(bmmc) ? "ȫ��" : bmmc;
		
		if(cjffsj != null && !"".equalsIgnoreCase(cjffsj)){
			nd = cjffsj.substring(0,4);
			yf = cjffsj.substring(4,6);
		}
		String totalMoney = qgzxDao.getTotalMoney(nd, yf, gwxzmc);
		List<HashMap<String, String>> list = qgzxDao.getPayInfoByGzdx(nd,yf,userName,gwxzmc);		
		try{
		 WritableSheet ws = wwb.getSheet(0);
		 WritableCellFormat wcfTytle = new WritableCellFormat();
		 Alignment alignMent = Alignment.CENTRE;
		 VerticalAlignment vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 
		 WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(14);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,0,xxmc + "ѧ���ڹ���ѧ" + yf +"��" + gwxzmc + "�����±���" ,wcfTytle));	
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.LEFT;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,2,"�ù����ţ�" + bmmc + "                                        ���ʱ�䣺" + nd + "��" + yf + "�·�",wcfTytle));
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(10);
		 wcfTytle.setFont(wfTytle);
		 
		 for(int i=0; i<list.size(); i++){
			 HashMap<String, String> map = new HashMap<String, String>();
			 map = (HashMap<String, String>) list.get(i);
			 ws.addCell(new Label(0,4+i,""+i,wcfTytle));
			 ws.addCell(new Label(1,4+i,map.get("yrdwmc"),wcfTytle));
			 ws.addCell(new Label(2,4+i,map.get("xm"),wcfTytle));
			 ws.addCell(new Label(3,4+i,map.get("xh"),wcfTytle));
			 ws.addCell(new Label(4,4+i,map.get("cjje"),wcfTytle));
			 ws.addCell(new Label(5,4+i,map.get("bz"),wcfTytle));
		 }
		 totalMoney = totalMoney == null || "".equalsIgnoreCase(totalMoney)? "0" : totalMoney;
		 String dxje = qgzxDao.getDxje(totalMoney);
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.LEFT;
		 vag = VerticalAlignment.CENTRE;
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.mergeCells(0, list.size()+4, 5, list.size()+4);
		 ws.addCell(new Label(0,list.size()+4,"�ϼ�(��д)���:" + dxje + "	                                                                      ����" + totalMoney ,wcfTytle));
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.LEFT;
		 vag = VerticalAlignment.CENTRE;
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
//		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(10);
		 wcfTytle.setFont(wfTytle);
		 ws.addCell(new Label(0,list.size()+6,"�������ܣ�",wcfTytle));
		 ws.addCell(new Label(2,list.size()+6,"��ˣ�",wcfTytle));
		 ws.addCell(new Label(4,list.size()+6,"�Ʊ��ˣ�",wcfTytle));
		 
		 ws.addCell(new Label(0,list.size()+10,"��ע��",wcfTytle));
		 ws.mergeCells(0, list.size()+11, 5, list.size()+11);
		 ws.addCell(new Label(0,list.size()+11,"1��	�ù����Ź�����ԱҪ��ʵ�Ǽ�ѧ���Ĺ���ʱ�䣬�ϱ�ʱ�������롶ѧ���ڹ��������Ӧ��",wcfTytle));
		 ws.mergeCells(0, list.size()+12, 5, list.size()+12);
		 ws.addCell(new Label(0,list.size()+12,"2��	�˱������ÿ�µ�25~28�ս���ѧ�����������ͼ��ݸ�¥����",wcfTytle));
		 ws.mergeCells(0, list.size()+13, 5, list.size()+13);
		 ws.addCell(new Label(0,list.size()+13,"3��	ѧ���μ��ڹ���ѧ�ǰ�ʱ�Ƴ꣨�����λ���⣩��ÿСʱ10Ԫ��ÿ�����ʱ��������40Сʱ��",wcfTytle));
		 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * �ڹ���ѧ���ʱ����ӡ(�㽭��ýѧԺ)
	 * @param wwb
	 * @return void	 
	 * */
	public void printPayReportZjcmxy(WritableWorkbook wwb){		
		String xxmc = StandardOperation.getXxmc();
		String yf = qgzxDao.getCurrentYf();
		String nd = qgzxDao.getSqsjInfo().get("nd");
		String cjffsj = qgzxDao.getQgzxConf().get("cjffsj");
		
		if(cjffsj != null && !"".equalsIgnoreCase(cjffsj)){
			nd = cjffsj.substring(0,4);
			yf = cjffsj.substring(4,6);
		}
		String totalMoney = qgzxDao.getTotalMoneyForZjcmxy(nd, yf);
		List<HashMap<String, String>> list = qgzxDao.getPayInfoByZjcmxy(nd,yf);		
		try{
		 WritableSheet ws = wwb.getSheet(0);
		 WritableCellFormat wcfTytle = new WritableCellFormat();
		 Alignment alignMent = Alignment.CENTRE;
		 VerticalAlignment vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(14);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,0,xxmc + "ѧ���ڹ���ѧ���ʷ��ű�" ,wcfTytle));	
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.LEFT;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,2,"ʱ�䣺" + nd + "��" + yf + "�·�",wcfTytle));
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(10);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,3,"���",wcfTytle));
		 ws.addCell(new Label(1,3,"���п���",wcfTytle));
		 ws.addCell(new Label(2,3,"ѧ������",wcfTytle));
		 ws.addCell(new Label(3,3,"�ù�����",wcfTytle));
		 ws.addCell(new Label(4,3,"����"+Base.YXPZXY_KEY,wcfTytle));
		 ws.addCell(new Label(5,3,"ƶ��/����/����/����",wcfTytle));
		 ws.addCell(new Label(6,3,"����",wcfTytle));
		 ws.addCell(new Label(7,3,"���Ž��",wcfTytle));
		 
		 for(int i=0; i<list.size(); i++){
			 HashMap<String, String> map = new HashMap<String, String>();
			 map = (HashMap<String, String>) list.get(i);
			 ws.addCell(new Label(0,4+i,String.valueOf(i+1),wcfTytle));
			 ws.addCell(new Label(1,4+i,map.get("kh"),wcfTytle));
			 ws.addCell(new Label(2,4+i,map.get("xm"),wcfTytle));
			 ws.addCell(new Label(3,4+i,map.get("yrdwmc"),wcfTytle));
			 ws.addCell(new Label(4,4+i,map.get("xymc"),wcfTytle));
			 ws.addCell(new Label(5,4+i,qgzxDao.getKnslx(map.get("xh")),wcfTytle));
			 ws.addCell(new Label(6,4+i,map.get("yhmc"),wcfTytle));
			 ws.addCell(new Label(7,4+i,map.get("cjje"),wcfTytle));
			// totalMoney = map.get("totalMoney");
		 }
		 totalMoney = totalMoney == null || "".equalsIgnoreCase(totalMoney)? "0" : totalMoney;
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.mergeCells(0, list.size()+4, 7, list.size()+4);
		 ws.addCell(new Label(0,list.size()+4,"�ϼƽ��:" + totalMoney,wcfTytle));
		 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ɾ����λ��Ϣ
	 * */
	public boolean delePost(String pk,HttpServletRequest request) throws Exception{
		boolean flag = false;
		String[] pkValues = pk.split("!!");
		String tableName = "gwxxb";
		String primaryKey = "gwdm||gwsbsj";
		String mes = "";
		
		for(int i=0; i<pkValues.length; i++){
			flag = StandardOperation.delete(tableName, primaryKey, pkValues[i], request);//ɾ����λ
			if(flag){
				flag = StandardOperation.delete("xsgwxxb", primaryKey, pkValues[i], request);//ɾ��ѧ�������λ��Ϣ
			}
			if(flag==false){
				mes += "��" + i + "������ɾ��ʧ��!";
			}
		}
		request.setAttribute("mes", mes);
		return flag;
	}
	
	/**
	 * ɾ��ѧ����λ��Ϣ
	 * */
	public boolean deleStuPost(String pk,HttpServletRequest request) throws Exception{
		boolean flag = false;
		String[] pkValues = pk.split("!!");
		String tableName = "xsgwxxb";
		String primaryKey = "xh||gwdm||sqsj";
		String mes = "";
		
		for(int i=1; i<pkValues.length; i++){
			flag = StandardOperation.delete(tableName, primaryKey, pkValues[i], request);//ɾ��ѧ����λ��Ϣ 			
			if(flag==false){
				mes += "��" + i + "������ɾ��ʧ��!";
			}
		}
		request.setAttribute("mes", mes);
		return flag;
	}
	
	/**
	 * ɾ��ѧ����λ��Ϣ
	 * */
	public boolean modiStuPost(String pk,HttpServletRequest request) throws Exception{
		boolean flag = false;
		String[] pkValues = pk.split("!!");
		String tableName = "xsgwxxb";
		String primaryKey = "xh||gwdm||sqsj";
		String mes = "";
		
		for(int i=1; i<pkValues.length; i++){
			flag = StandardOperation.update(tableName, new String[]{"sfyx"}, new String[]{"0"}, primaryKey, pkValues[i], request); 	//sfyx='0' ��Ч		
			if(flag==false){
				mes += "��" + i + "�����ݲ���ʧ��!";
			}
		}
		request.setAttribute("mes", mes);
		return flag;
	}
	
	/**
	 * �ж��Ƿ����ڹ���ѧ����ʱ����
	 * @return String
	 * @throws Exception 
	 * */
	public String getSqsjFlag() throws Exception{
		return qgzxDao.getSqsjFlag();
	}
	
	/**
	 * ��ȡ�ڹ���ѧ��������ѯ��ͷ
	 * @return List
	 * */
	public List<HashMap<String, String>> getXsgwxxToptr(){
		String xxdm = StandardOperation.getXxdm();
		String[] colList = null;
		
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
			//�Ϻ����̼�����ѧ
			colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "xyyj", "xxyj", "kh" };
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
			// ��������
			colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
					                 "bjmc", "gwdm", "sqsj", "sfpks", "xyyj", 
					                 "xxyj", "kh", "bh", "gh" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
			//�㽭����ְҵ����ѧԺ
			colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "xxyj" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)){
			//�й�����ѧԺ
			colList = new String[]{"����", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "fdyyj","xyyj", "xxyj"};
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_HNGYDX)){
			//���Ϲ�ҵ��ѧ
			colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "fdyyj", "xxyj", "xyyj"};
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
			//�й����ʴ�ѧ
			colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "lxdh", "xxyj" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
			//������ѧԺ
			//============begin ���ΰ 2009/4/1 =========
			colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "xyyj", "xxyj" };
			//============end ���ΰ 2009/4/1 =========
		}else if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//���ݴ�ѧ
			colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "sfyx", "xyyj", "xxyj" };
		}else if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(xxdm)){
			//�㽭�Ƽ�ѧԺ
			colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "yrdwmc", "gwdm", "sqsj", "lxdh", "xyyj", "xxyj", "��¼ȡ��λ" };
		}else if(Globals.XXDM_HBJTZYJSXY.equals(xxdm)){
			//������ͨ
			colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "fdyyj","xyyj", "xxyj" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_WHSYFWXY)){
			//�人��ҵ
			colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "fdyyj","xyyj", "xxyj" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJSZYJSXY)){
			//�㽭����
			colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj","xxyj" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJTZYJSXY)){
			//�㽭��ְͨҵ����ѧԺ
			colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj","fdyyj","xyyj", "xxyj" };
		} else {
			colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj","xyyj", "xxyj" };
		}
		String[] colCN = qgzxDao.getColumnNameCN(colList, "view_xsgwxx");
		
		return qgzxDao.arrayToList(colList, colCN);
	}
	
	/**
	 * ��ѯ��λ��ϸ��Ϣ
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getPostDetail(String pkValue){
		QgzxDao dao = new QgzxDao();
		HashMap<String, String> map = new HashMap<String, String>();
		map = dao.getPostDetail(pkValue);
		return map;
	}
	
	/**
	 * �ڹ���ѧ��������ѯ
	 * @param model
	 * @return List
	 * */
	public List<String[]> searchXsgwxx(CommanForm model){		
		return qgzxDao.querryXsgwxx(model);
	}
	
	/**
	 * ��ȡ�趨�ĸ�λ������ʹ�ñ���
	 * @return Double
	 * */
	public Double getKnsbl(){
		return qgzxDao.getKnsbl();		
	}
	
	/**
	 * ��ȡ��λ�Ŀ���ʱ��
	 * @param pkVal
	 * @return List
	 * */
	public List<HashMap<String, String>> getGzsjKxbz(String pkVal){
		return qgzxDao.getGzsjKxbz(pkVal);	
	}
	
	/**
	 * ���ҳ��������
	 * @return List	 
	 * */
	public List<HashMap<String, String>> getKxList(){
		List<HashMap<String, String>> kxList = new ArrayList<HashMap<String,String>>();
		String[] sj = {"��1-2��", "��3-4��", "����", "��5-6��", "��7-8��", "����" };
		for (int i = 0; i < 6; i++) {
			HashMap<String, String> map2 = new HashMap<String, String>();
			map2.put("sj", sj[i]);
			map2.put("sjIndex", String.valueOf(i));
			kxList.add(map2);
		}
		
		return kxList;
	}
	
	/**
	 * ��ȡ�·���Ϣ
	 * @return List
	 * */
	public List<HashMap<String, String>> getYfList(){
		QgzxDao dao = new QgzxDao();
		return dao.getYfList();
	}
	
	/**
	 * ��ȡ������Ϣ
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getRqList(){
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String,String>>();		
		for(int i=0; i<31; i++){
			HashMap< String, String> map = new HashMap<String, String>();
			map.put("rq", (i+1)+"");
			rs.add(map);
		}		
		return rs;
	}
	
	
	/**
	 * ��ȡ��λ����ϸ��Ϣ
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getWorkInfo(CommanForm model){
		QgzxDao dao = new QgzxDao();
		HashMap<String, String> tmpMap = new HashMap<String, String>();
		tmpMap = dao.getWorkInfo(model);//��λ�������Ϣ
		tmpMap.put("syjf", dao.getSyjf(model));//ʣ�ྭ��
		return tmpMap;
	}
	
	/**
	 * ��ȡĳ��λ�µ�ѧ��
	 * @param model
	 * @return List
	 * */
	public List<HashMap<String, String>> getStuByPost(CommanForm model){
		QgzxDao dao = new QgzxDao();
		return dao.getStuByPost(model);//��ȡĳ��λ�µ�ѧ��
	}
	
	/**
	 * ���ʲ�������
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean saveReWorkPay(CommanForm model, HttpServletRequest request){		
		boolean flag = false;		
		int len = 0;
		String mes = "";
		String count = model.getCount();
		count = count == null || "".equalsIgnoreCase(count) ? "0" : count;	
		String tableName = "xscjffb";
		String[] columns = {"xh", "gwdm", "sqsj", "nd", "yf", "xn", "cjje", "gzsj","bz"};	//����ʱ�伴��λ�걨ʱ��	
		String pk = "xh||gwdm||sqsj||nd||yf";
		len = Integer.parseInt(count);
		String xn = model.getXn();
		String nd = model.getNd();
		String yf = model.getYf();
		HashMap<String, String> pMap = new HashMap<String, String>();
		HashMap<String, String> sMap = new HashMap<String, String>();
		pMap = qgzxDao.getWorkInfo(model);
		
		
		String gwdm = pMap.get("gwdm");
		String sqsj = pMap.get("gwsbsj");
		for(int i=0; i<len; i++){
			String xh = request.getParameter("xh" + i);
			String cjje = request.getParameter("cjje" + i);
			String gzsj = request.getParameter("gzsj" + i);
			String bz = request.getParameter("bz" + i);
			String[] values = {xh,gwdm,sqsj,nd,yf,xn,cjje,gzsj,bz};
			if(cjje != null && !"".equalsIgnoreCase(cjje)){//����Ϊ��				
				String pkValue = xh + gwdm + sqsj + nd + yf;
				if(qgzxDao.checkExists(tableName, pk, pkValue)){
					sMap = qgzxDao.getStuInfo(xh);
					
					mes += sMap.get("xm") + "��" + nd + "��" + yf + "���Ѿ����Ź���𣬲���ʧ�ܣ���˲飡\n"; 
				}else{
					flag  = StandardOperation.insert(tableName, columns, values, request);
				}
			}
		}
		request.setAttribute("mes", mes);
		return flag;
	}
	
	public String checkPostAudi(String pkValue,String xhValue,String type,String userType,String userName){
		String message = "";
		HngydxGwglDAO gwDao = new HngydxGwglDAO();	
		String[] value = xhValue.split("!!SplitOneSplit!!");
		String zd = "";
		
		if(userType.equalsIgnoreCase("xx")||userType.equalsIgnoreCase("admin")){
			zd = " xxyj ";
		}
	
		//���˵�λ���
		if(gwDao.checkIsYrdw(userName)){
			zd = "xyyj";
		}
		if(type != null && "pass".equalsIgnoreCase(type)){
			for(int i=0; i<value.length; i++){
				if(qgzxDao.getDataCount("xsgwxxb", "xh||"+zd.trim(), value[i].trim()+"ͨ��")>0){
					message += value[i]+"�Ѿ��и�λ���ͨ��,��˲���ʧ��!\n";
				}
			}
		}
		if("".equalsIgnoreCase(message)){
			postStuBatchAudi(pkValue,type,userType,userName);
		}
		return message;
	}
	
	public String checkPostCount(String pkValue,String type){
		String message = "";
		String[] value = pkValue.split("!!SplitOneSplit!!");
		String[] gwmc = new String[value.length];
		String[] tmpGw = new String[value.length];
		String pk = "xh||gwdm||sqsj";
		boolean flag = false;

		if(type != null && "pass".equalsIgnoreCase(type)){
			for(int i=0; i<value.length; i++){
				flag = false;
				String gw = qgzxDao.getGwmc(pk, value[i]);				
				for(int j=0; j<gwmc.length; j++){
					if(gwmc[j] != null && gwmc[j].equalsIgnoreCase(gw)){
						flag = true;
					}
				}
				gwmc[i] = gw;
				if(flag == false){
					tmpGw[i] = gw;
				}
			}
		}
		for(int i=0; i<tmpGw.length; i++){
			if(tmpGw != null && !"".equalsIgnoreCase(tmpGw[i])){
				int gwsyrs = qgzxDao.getGwtgrs(tmpGw[i]);
				int gwyshrs = 0;
				for(int j=0; j<gwmc.length; j++){
					if(DealString.toString(gwmc[j]).equalsIgnoreCase(tmpGw[i])){
						gwyshrs += 1;
					}
				}
				if(gwyshrs>gwsyrs){
					message += qgzxDao.getGwdm(tmpGw[i]) + "��ʣ" + gwsyrs + "������밴�����������ύ!\n";
				}
			}
		}
		return message;
	}
	
	
	/**
	 * �й����ʴ�ѧ������ʱ��
	 * @return boolean
	 * */
	public boolean checkAudiTime(){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		String sql = "select shkssj, shjssj, to_char(sysdate,'YYYYMMDDHH24MISS')nowtime from gwsqsjb";
		HashMap<String, String> map = new HashMap<String, String>();
		map = dao.getMap(sql, new String[]{}, new String[]{"shkssj", "shjssj", "nowtime"});
		
		String shkssj = map.get("shkssj");
		String shjssj = map.get("shjssj");
		String nowtime = map.get("nowtime");
		
		shkssj = shkssj == null ? "" : shkssj;
		shjssj = shjssj == null ? "" : shjssj;
		
		if("".equalsIgnoreCase(shkssj) &&(!"".equalsIgnoreCase(shjssj) && Double.parseDouble(shjssj)<Double.parseDouble(nowtime)) ){
			flag = false;
		}else if("".equalsIgnoreCase(shjssj) && (!"".equalsIgnoreCase(shjssj) && Double.parseDouble(shkssj)>Double.parseDouble(nowtime))){
			flag = false;
		}else if("".equalsIgnoreCase(shkssj) && "".equalsIgnoreCase(shjssj)){
			flag = true;
		}else if((Double.parseDouble(shkssj)>Double.parseDouble(nowtime)) || Double.parseDouble(shjssj)<Double.parseDouble(nowtime)){
			flag = false;
		}else{
			flag = true;
		}
		return flag;
	}
	
	/**
	 * �ж�ѧ���������λ��ʣ���λ����
	 * @param pk
	 * @param pkValue
	 * @return int
	 * */
	public int getSygwrs(String pk, String pkValue){
		QgzxDao dao = new QgzxDao();
		return dao.getSygwrs(pk, pkValue);
	}
	
	/**
	 * ����������ȡ��λ��Ϣ
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getGwInfo(String pk, String pkValue){
		QgzxDao dao = new QgzxDao();		
		return dao.getGwInfo(pk,pkValue);
	}
	
	/**
	 * �жϼ�¼�Ƿ���� 
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue){
		return qgzxDao.checkExists(tableName, pk, pkValue);
	}
	
	/**
	 * �ж��Ƿ����ϱ���ʱ��֮��
	 * @param yrdwdm
	 * @return boolean
	 * */
	public boolean checkShsbsj(String yrdwdm){
		return qgzxDao.checkShsbsj(yrdwdm);
	}
	
	/**
	 * �������˵�λ��ȡ�����ϱ�ʱ����Ϣ 
	 * @param yrdwdm
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getKhsbsjByYrdw(String yrdwdm){
		return qgzxDao.getKhsbsjByYrdw(yrdwdm);
	}
	
	/**
	 * ��ȡ������ѯ�ֶε����
	 * @param tableName
	 * @return String 
	 * */
	public String getExpColumn(String tableName){
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		if(qgzxDao.checkExists("dcb", "xxdm||zdssb", xxdm+tableName)){
			String zd = qgzxDao.getExpColumn(xxdm, tableName);
			sql = "select " + zd + " from " + tableName + " a ";
		}else{
			sql = "select * from " + tableName + " a ";
		}
		
		return sql;
	}
	
	/**
	 * ��ȡ�����ѯ�ֶε����
	 * @param tableName
	 * @return String 
	 * */
	public String getImpColumn(String tableName){
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		if(qgzxDao.checkExists("drb", "xxdm||zdssb", xxdm+tableName)){
			String zd = qgzxDao.getImpColumn(xxdm, tableName);
			sql = "select " + zd + " from " + tableName;
		}else{
			sql = "select * from " + tableName;
		}		
		return sql;
	}
	
	/**
	 * ��ȡ����Ա�µ�רҵ
	 * @param zgh
	 * */
	public List<HashMap<String, String>> getZyListByFdy(String zgh){
		return qgzxDao.getZyByFdy(zgh);		
	}
	
	/**
	 * ��ȡ����Ա�µİ༶
	 * @param zgh
	 * */
	public List<HashMap<String, String>> getBjListByFdy(String zgh){
		return qgzxDao.getBjByFdy(zgh);
	}
	
	/**
	 * �ж�ѧ���Ƿ���������
	 * @param xh
	 * */
	public boolean isKns(String xh){
//		return true;
		return qgzxDao.isKns(xh);
	}
	
	/**
	 * �Ƿ�ƶ�����ֶδ����ݿ���ȡ��Ϊͨ�������ж�
	 * @param List<String[]> rs
	 * @return List<String[]> 
	 * */
	public List<String[]> changeRsValue(List<String[]> rs){
		List<String[]> tempRs = new ArrayList<String[]>();
		for(int i=0; i<rs.size(); i++){
			String[] values = rs.get(i);
			String xh = values.length>6 ? values[6] : "";
			if(Globals.XXDM_ZJJTZYJSXY.equalsIgnoreCase(Base.xxdm)){
				values[10] = isKns(xh) == true ? "��" : "��";
				tempRs.add(values);
			}else{
				values[9] = isKns(xh) == true ? "��" : "��";
				tempRs.add(values);
			}
		}
		rs = tempRs;	
		return rs;
	}
	
	/**
	 * ��ѯ���ͨ����λ�����б�
	 * @param String userName
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getGwmcList(String userName){
		return qgzxDao.selectGwmcList(userName);
	}
	
	/**
	 * �����Ƿ����ͨ��������ѯ��λ�����б�
	 * @param String userName
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getGwmcList(String userName,boolean isPass){
		return qgzxDao.selectGwmcList(userName,isPass);
	}
	
	/**
	 * ��ѯ���˵�λ��λ�����б�
	 * @param String sqdwdm
	 * @param boolean shFlag
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getYrdwGwList(String userName,String sqdwdm, String gwxzdm,boolean shFlag){
		return qgzxDao.getYrdwGwList(userName, sqdwdm, gwxzdm, shFlag);
	}
	
	/**
	 * ��ѯ���˵�λ��λ�����б�
	 * @param String sqdwdm
	 * @param boolean shFlag
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getNotUserGwList(String userName,String sqdwdm, String gwxzdm,boolean shFlag){
		return qgzxDao.getNotUserGwList(userName, sqdwdm, gwxzdm, shFlag);
	}
	
	/**
	 * ��ȡ�е���������
	 * @param String[] colList
	 * @param String tableName
	 * @return String[]
	 * */
	public String[] getColumnNameCN(String[] colList,String tableName){
		return qgzxDao.getColumnNameCN(colList, tableName);
	}
	
	/**
	 * ��ѯ��λ��ϸ��Ϣ
	 * @param String pk
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryGwxx(String pk, String pkValue){
		return qgzxDao.selectGwxx(pk,pkValue);
	}
	
	/**
	 * ��ѯ��λ��ϸ��Ϣ
	 * @param String pk
	 * @param String pkValue
	 * @param String userDep
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryGwxx(String pk, String pkValue,String userDep){
		return qgzxDao.selectGwxx(pk,pkValue,userDep);
	}
	
	/**
	 * ��ѯ�μӸ�λ��ѧ���б�
	 * @param String pk
	 * @param String pkValue
	 * @return List<String[]>
	 * */
	public List<HashMap<String, String>>  getCjgwxsList(String pk, String pkValue){
		return qgzxDao.selectCjgwxs(pk,pkValue);
	}	
	
	/**
	 * �ж��û��Ƿ������˵�λ�û�
	 * @param String userName
	 * @return boolean
	 * */
	public boolean isYrdwUser(String userName){
		return qgzxDao.isYrdwUser(userName);
	}
	
	/**
	 * ��ȡ���˵�λ�û������˵�λ����
	 * @param String userName
	 * @return boolean
	 * */
	public String getYrdwUser(String userName){
		HngydxGwglDAO dao=new HngydxGwglDAO();
		return dao.getYrdwdmByUser(userName);
	}
	
	/**
	 * ��ѯ��λ�б�
	 * */
	public List<HashMap<String, String>> getGwList(CommanForm model){
		return qgzxDao.selectGwList(model);
	}
	
	/**
	 * ��ѯѧ����λ�б�
	 * */
	public List<HashMap<String, String>> getStuGwList(CommanForm model){
		return qgzxDao.selectStuGwList(model);
	}
	
	/**
	 * ��ѯ�༶����¼�в�ͬ�û����͵ļ�¼�Ƿ��Ѿ������
	 * @param pkValue
	 * @return boolean
	 * */
	public HashMap<String, String> queryXsgwxxByPk(String pkValue){
		return qgzxDao.selectXsgwxxbByPk(pkValue);
	}
	
	/**
	 * ��ѯ�Ƴ귽ʽ�����б�
	 * @param boolean oneNull
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryJcfsList(boolean oneNull){
		return qgzxDao.selectJcfsdmbList(oneNull);
	}
	
	/**
	 * ��ѯ��λ������Ϣ�б�
	 * @param paramter
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getGwmcxxList(HashMap<String, String> paramter,String isLsgw){
		return qgzxDao.getGwmcxxList(paramter,isLsgw);
	}
	
	
	/**
	 * ��ѯ�Ǹ�λ�����˷����ĸ�λ������Ϣ�б�
	 * @param paramter
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getFgwfbrGwmcxxList(HashMap<String, String> paramter,String isLsgw){
		return qgzxDao.getFgwfbrGwmcxxList(paramter,isLsgw);
	}
	
	/**
	 * ��ȡ�б�
	 * @param type
	 * */
	public List<HashMap<String, String>> getChkList(int type){
		return qgzxDao.getChkList(type);
	}
	
	/**
	 * ��ȡְλ���ʴ����б�
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getZwxzdmList(){
		return qgzxDao.getZwxzdmList();
	}
	
	/**
	 * ��ȡ��λ��Ϣ��
	 */
	public HashMap<String,String>getGwxx(String pkValue){
		return qgzxDao.getGwxx(pkValue);
	}
	
	/**
	 * ����ѧ����λ���ͨ��������
	 * @return String
	 * */
	public String appendTggwtj(){
		String sql = "";
		if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)){
			//�㽭�Ƽ�ѧԺ
			sql = " and xxyj='ͨ��'";
		}
		
		return sql;
	}
	
	/**
	 * �ж��Ƿ����������λ
	 * @return boolean
	 * */
	public boolean checkSqdg(){
		boolean flag = false;
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(Base.xxdm)//�й����ʴ�ѧ
				|| Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)//�㽭�Ƽ�
				|| Globals.XXDM_MJXY.equalsIgnoreCase(Base.xxdm)){//����ѧԺ
			flag = true;
		}
		return flag;
	}
	
	/**
	 * ��ѯ�����λ��ѧ���б�
	 * @param String pk
	 * @param String pkValue
	 * @return List<String[]>
	 * */
	public List<HashMap<String, String>>  getSqgwxsList(String pk, String pkValue){
		return qgzxDao.selectSqgwxs(pk,pkValue);
	}
	
	public boolean saveYrdwshkz(CommanForm model) throws Exception{
		
		return qgzxDao.saveYrdwshkz(model);
	}
}