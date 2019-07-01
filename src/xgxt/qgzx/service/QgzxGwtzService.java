package xgxt.qgzx.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.daoActionLogic.StandardOperation;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.qgzx.form.QgzxForm;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �ڹ���ѧѧ����λ��������Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 2.0</p>
 * <p>Time: 2009-12-30</p>
 */
public class QgzxGwtzService {
	QgzxDao dao = new QgzxDao();
	
	/**
	 * ��λ������λ�滻 
	 * @param QgzxForm model
	 * @param HttpServletRequest request
	 * @return boolean 
	 * @throws Exception
	 * */
	public boolean gwtzGwth(QgzxForm model,HttpServletRequest request) throws Exception{
		//Ҫ��ĳ����λ��ѧ�����е�������"֮ǰѧ��"�ĵ�����ĸ�λ��Ϣ
		//��Ϊ"��ѧ��"�޸�ǰ�ĸ�λ��Ϣ��Ȼ����ѧ�����ݱ��浽���ݿ���
		//����ѧ�������ݱ��棨����µ���xsgwxxb����,����Ĭ�����˵�λ��ѧУ�������ͨ����
		boolean result = true;
		boolean operFlag = true;
		boolean delFlag =  true;
		String xxdm = StandardOperation.getXxdm();
		String shjg = "ͨ��";
		
		String oldXh = model.getOldXh();//���滻ѧ����ѧ��
		String tzhgzxn = model.getTzhgzxn();
		String tzhgzxq = model.getTzhgzxq();
		String tzhgznd = model.getTzhgznd();
		String tzhgwdm = model.getTzhgw();
		String tzhgwsbsj = model.getTzhgwsbsj();
		String tzhkcjqgzxsj = model.getTzhkcjqgzxsj();
		String tzqgzxn = model.getTzqgzxn();
		String tzqgzxq = model.getTzqgzxq();
		String tzqgznd = model.getTzqgznd();
		String tzqkcjqgzxsj = model.getTzqkcjqgzxsj();
		String xh = model.getXh();//�ϸ�ѧ����ѧ��
		String tzqgw = model.getTzqgw();
		String tzqgwsbsj = model.getTzqgwsbsj();
		String xn = model.getXn();
		String xq = model.getXq();
		String tzyy = model.getTzyy();
		String tzsj = model.getTzsj();
		
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//���ݴ�ѧ
			delFlag = false;
			if("xy".equalsIgnoreCase(model.getUserType())){//ѧԺ������λ��ҪѧУ���ͨ��
				operFlag = false;
				shjg = "δ���";
			}
		}
		
		//�ж��Ƿ񽫵�������Ϣֱ�Ӹ��µ���λ��Ϣ����
		if(operFlag){//�������
			//���ϸڵ�ѧ�����뵽ѧ����λ��Ϣ����
			result  = StandardOperation.insert("xsgwxxb", new String[] { "xn", "xq","nd", "gwdm", "gwsbsj", "kcjqgzxsj","xh","xyyj","xxyj","sfyx"},
					new String[] {tzhgzxn, tzhgzxq, tzhgznd, tzhgwdm, tzhgwsbsj,tzhkcjqgzxsj,xh,"ͨ��","ͨ��","��Ч"}, request);	
			if(!result){
				result = StandardOperation.update("xsgwxxb", 
						new String[] { "xn", "xq", "nd", "gwdm", "gwsbsj", "kcjqgzxsj", "xyyj", "xxyj", "sfyx" }, 
						new String[] { tzhgzxn, tzhgzxq, tzhgznd, tzhgwdm, tzhgwsbsj, tzhkcjqgzxsj,"ͨ��", "ͨ��", "1" },//sfyx='1' ��Ч 
						new String[] { "xh", "gwdm", "gwsbsj" }, new String[] {xh, tzqgw, tzqgwsbsj }, request);
			}			
		}
		
		//�ж��Ƿ����ɾ�����ݣ�����ɾ�������޸Ĳ���
		if(delFlag){
			//�����滻��ѧ����λ��Ϣ���ݴӸ�λ��Ϣ����ɾ��
			if(result) {			
				StandardOperation.delete("xsgwxxb","xh||gwdm||gwsbsj",oldXh + tzhgwdm + tzhgwsbsj,request);
			}
		}else{
			//�����滻��ѧ����λ��Ϣ�޸�Ϊ��Ч
			if(result){
				//���˸���Ϣ���뵽��λ������Ϣ����
				String[] colList = { "xh", "xn", "xq", "tzyy", "tzsj", "tzqgzxn", "tzqgzxq", "tzqgznd", "tzqgw", 
						"tzqgwsbsj", "tzqkcjqgzxsj", "tzhgzxn", "tzhgzxq", "tzhgznd", "tzhgw", "tzhgwsbsj", 
						"tzhkcjqgzxsj","shjg" };
				String[] valList = new String[] {oldXh, xn, xq, tzyy, tzsj, tzqgzxn, tzqgzxq, tzqgznd, tzqgw,
						tzqgwsbsj, tzqkcjqgzxsj, "", "", "", "�˸�", "", "", shjg };
				result = StandardOperation.insert("qgzx_gwtzb", colList, valList, request);
				
				if(!result){
					//��λ������Ϣ�Ѿ����ڣ��޸ĸ�λ������Ϣ
					result = StandardOperation.update("qgzx_gwtzb", colList, new String[] {
							oldXh, xn, xq, tzyy, tzsj, tzqgzxn, tzqgzxq, tzqgznd, tzqgw,
							tzqgwsbsj, tzqkcjqgzxsj, "", "", "",
							"�˸�", "", "",shjg }, "xh||xn||xq||tzqgw||tzsj", oldXh+xn+xq+tzqgw+tzsj, request);
				}	
//				StandardOperation.update("xsgwxxb", new String[]{"sfyx"}, new String[]{"��Ч"}, "xh||gwdm||gwsbsj", oldXh + tzhgwdm + tzhgwsbsj, request);
			}
		}
		
		//����λ������Ϣ���뵽��λ������Ϣ����
		String[] colList = { "xh", "xn", "xq", "tzyy", "tzsj", "tzqgzxn", "tzqgzxq", "tzqgznd", "tzqgw", 
				"tzqgwsbsj", "tzqkcjqgzxsj", "tzhgzxn", "tzhgzxq", "tzhgznd", "tzhgw", "tzhgwsbsj", 
				"tzhkcjqgzxsj","shjg" };
		String[] valList = new String[] {xh, xn, xq, tzyy, tzsj, "", "", "", "�޸�λ",
				"", "", tzhgzxn, tzhgzxq, tzhgznd, tzhgwdm, tzhgwsbsj, tzhkcjqgzxsj, shjg };
		result = StandardOperation.insert("qgzx_gwtzb", colList, valList, request);
		
		if(!result){
			//��λ������Ϣ�Ѿ����ڣ��޸ĸ�λ������Ϣ
			result = StandardOperation.update("qgzx_gwtzb", colList, new String[] {
					oldXh, xn, xq, tzyy, tzsj, tzqgzxn, tzqgzxq, tzqgznd, tzqgw,
					tzqgwsbsj, tzqkcjqgzxsj, "", "", "",
					"", "", "",shjg }, "xh||xn||xq||tzqgw||tzsj", oldXh+xn+xq+tzqgw+tzsj, request);
		}		
		return result;
	}
	
	/**
	 * ��λ����
	 * @param QgzxForm model
	 * @param HttpServletRequest request
	 * @return boolean 
	 * @throws Exception
	 * */
	public boolean gwtz(QgzxForm model,HttpServletRequest request) throws Exception{
		boolean result = false;
		boolean operFlag = true;
		String xxdm = StandardOperation.getXxdm();
		
		String pk = model.getPk();
		String pkValue = model.getPkValue();
		String realTable = "qgzx_gwtzb";
		String shjg = "ͨ��";
		
		String xh = model.getXh();
		String xn = model.getXn();
		String xq = model.getXq();
		String tzqgzxn = model.getTzqgzxn();
		String tzqgznd = model.getTzqgznd();
		String tzqgzxq = model.getTzqgzxq();
		String tzqkcjqgzxsj = model.getTzqkcjqgzxsj();
		String tzqgw = model.getTzqgw();
		String tzqgwsbsj = model.getTzqgwsbsj();
		String tzsj = model.getTzsj();
		String tzyy = model.getTzyy();
		String tzhgzxn = model.getTzhgzxn();
		String tzhgznd = model.getTzhgznd();
		String tzhgzxq = model.getTzhgzxq();
		String tzhgwdm = model.getTzhgw();
		String tzhgwsbsj = model.getTzhgwsbsj();
		String tzhkcjqgzxsj = model.getTzhkcjqgzxsj();
		
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//���ݴ�ѧ
			if("xy".equalsIgnoreCase(model.getUserType())){//ѧԺ�û�������λ��ҪѧУ���ͨ�� 
				operFlag = false;
				shjg = "δ���";
			}
		}
		
		//�ж�ѧ���Ƿ��ڵ���ǰ�ĸ�λ��
	    if(dao.checkExists("view_xsgwxx", "xh||gwdm||gwsbsj", xh+tzqgw+tzqgwsbsj)){
	    	//�ж��Ƿ�ֱ�ӽ�������Ϣ���뵽��λ��Ϣ����
	    	if(operFlag){
		    	//�޸�ѧ����λ��Ϣ
	    		//�Ƚ��Ѿ����ڵĵ�����ĸ�λɾ��
	    		result = StandardOperation.delete("xsgwxxb", "xh||gwdm||gwsbsj",xh+tzhgwdm+tzhgwsbsj, request);
	    		if(result){
			    	result = StandardOperation.update("xsgwxxb", 
			    			new String[] { "xn", "xq", "nd", "gwdm", "gwsbsj", "kcjqgzxsj","xyyj","xxyj","sfyx" }, 
			    			new String[] { tzhgzxn, tzhgzxq, tzhgznd, tzhgwdm, tzhgwsbsj, tzhkcjqgzxsj, "ͨ��", "ͨ��", "1" },//sfyx='1'��У
							new String[] { "xh", "gwdm", "gwsbsj" }, 
							new String[] { xh, tzqgw, tzqgwsbsj }, 
							request);
	    		}
	    	}
	    	
	    	//ɾ��ѧ���ĸ�λ������Ϣ
			if (StringUtils.isNull(pkValue)) {
				result = StandardOperation.delete(realTable, "xh||xn||xq||tzqgw||tzsj",xh+xn+xq+tzqgw+tzsj, request);
			} else {
				result = StandardOperation.delete(realTable, pk, pkValue, request);
			}
	    	//��������Ϣ���뵽��λ������Ϣ����
			if(result){
				String[] colList = { "xh", "xn", "xq", "tzyy", "tzsj", "tzqgzxn", "tzqgzxq", "tzqgznd", 
						"tzqgw", "tzqgwsbsj", "tzqkcjqgzxsj", "tzhgzxn", "tzhgzxq", "tzhgznd", "tzhgw", 
						"tzhgwsbsj", "tzhkcjqgzxsj","shjg" };
				result = StandardOperation.insert(realTable, colList, 
						new String[] {xh, xn, xq, tzyy, tzsj, tzqgzxn, tzqgzxq, tzqgznd, tzqgw,
						tzqgwsbsj, tzqkcjqgzxsj, tzhgzxn, tzhgzxq, tzhgznd,tzhgwdm, tzhgwsbsj,
						tzhkcjqgzxsj,shjg }, request);
			}
	    }else{
	    	request.setAttribute("msg", "ѧ�����ڵ���ǰ�ĸ�λ�ϣ�");
	    	result = false;
	    }
		return result;
	}
	
	/**
	 * ��λ�����������
	 * @param QgzxForm model
	 * @return boolean
	 * */
	public boolean adjustBatchAudi(QgzxForm model){
		boolean result = false;
		String shjg = model.getShjg();
		String[] pkV = model.getPkV();
		String[] gwtzArr = new String[pkV.length];
		String[] gwAddArr = new String[pkV.length];
		String[] gwDelArr = new String[pkV.length];
		String pk = "xn||xq||xh||tzqgw||tzsj";
		
		for(int i=0; i<pkV.length; i++){
			HashMap<String, String> map = dao.selectGwxzxxOne(pk,pkV[i]);
			String xh = map.get("xh") == null ? "" :  map.get("xh");
			String tzhgw = map.get("tzhgw") == null ? "" : map.get("tzhgw");
			String tzhgwsbsj = map.get("tzhgwsbsj") == null ? "" : map.get("tzhgwsbsj");
			String tzqgw = map.get("tzqgw") == null ? "" : map.get("tzqgw");
			String tzqgwsbsj = map.get("tzqgwsbsj") == null ? "" : map.get("tzqgwsbsj");
			String tzhxn = map.get("tzhgzxn") == null ? "" : map.get("tzhgzxn");
			String tzhnd = map.get("tzhgznd") == null ? "" : map.get("tzhgznd");
			String tzhxq = map.get("tzhgzxq") == null ? "" : map.get("tzhgzxq");
			String tzhkcjqgzxsj = map.get("tzhkcjqgzxsj") == null ? "" : map.get("tzhkcjqgzxsj");
			
			if("ͨ��".equalsIgnoreCase(shjg)){
				//���ͨ����ѧ���ĸ�λ��Ϣ���µ���λ��Ϣ����
				if(StringUtils.isNull(tzhgwsbsj)){
					gwDelArr[i] = "delete from xsgwxxb where xh='" + xh + "' and gwdm='" + tzhgw +"' and gwsbsj is null" ;;
				}else{
					gwDelArr[i] = "delete from xsgwxxb where xh='" + xh + "' and gwdm='" + tzhgw +"' and gwsbsj='" + tzhgwsbsj + "'" ;;
				}
				if("�˸�".equalsIgnoreCase(map.get("tzhgw"))){
					//���滻��ѧ��
					gwAddArr[i] = "update xsgwxxb set sfyx='0' where xh='" + xh+ "' and gwdm='" + tzqgw + "' and gwsbsj='" + tzqgwsbsj+"'";
				}else{
					gwAddArr[i] = "insert into xsgwxxb(xn,xq,nd,gwdm,gwsbsj,kcjqgzxsj,xh,xyyj,xxyj,sfyx) values('" + tzhxn + "', '" + tzhxq + "'," +
							"'" + tzhnd + "', '" + tzhgw + "','" + tzhgwsbsj +"','" + tzhkcjqgzxsj + "','" + xh + "'," +
									"'ͨ��','ͨ��','1')";
				}
			}
			//�޸ĸ�λ������Ϣ���е���˽��
			gwtzArr[i] = "update qgzx_gwtzb set shjg='" + shjg +"' where " + pk + "='" + pkV[i]+"'";
		}
		
		try{
			dao.runBatch(gwDelArr);
			dao.runBatch(gwAddArr);
			dao.runBatch(gwtzArr);
			result = true;
		}catch(Exception e){
			result = false;
		}
		
		return result;
	}
	
	/**
	 * ����������ѯѧ����λ������ϸ��Ϣ
	 * @param String pk
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryGwtzxxxx(String pk, String pkValue){
		return dao.selectGwxzxxOne(pk,pkValue);
	}
	
}
