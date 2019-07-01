package xgxt.pjpy.tyb.zhszcp.service;

import java.util.HashMap;
import java.util.List;

import common.GlobalsVariable;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.pjpy.PjpyTyService;
import xgxt.pjpy.lsxy.LsxyPjpyDAO;
import xgxt.pjpy.tyb.zhszcp.action.PjpyZhcpjxjActionForm;
import xgxt.pjpy.tyb.zhszcp.action.PjpyZhszcpwhActionForm;
import xgxt.pjpy.tyb.zhszcp.dao.PjpyZhcpjxjDAO;
import xgxt.pjpy.tyb.zhszcp.dao.PjpyZhszcpDAO;
import xgxt.pjpy.zjjd.JxjpdxxModel;
import xgxt.pjpy.zjkjxy.PjpyZjkjxyDAO;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;


public class PjpyZhcpjxjService {
	PjpyZhcpjxjDAO zcjxjDao = new PjpyZhcpjxjDAO();
	
	/**
	 * ��ȡ�ۺϲ���ʱ����Ϣ
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getZhcpSqsjMap(){
		HashMap<String, String> map = new HashMap<String, String>();
		PjpyTyService service = new PjpyTyService();
		String zczq = service.getZhcpSqzq();//��������
		
		if("xq".equalsIgnoreCase(zczq)){
			//ѧ��
			map.put("xn", Base.getJxjsqxn());
			map.put("xq", Base.getJxjsqxq());
			map.put("nd", "��");
			map.put("sqsj", "��");
		}else if("xn".equalsIgnoreCase(zczq)){
			//ѧ��
			map.put("xn", Base.getJxjsqxn());
			map.put("xq", "��");
			map.put("nd", "��");
			map.put("sqsj", "��");
		}else if("nd".equalsIgnoreCase(zczq)){
			//���
			map.put("xn", "��");
			map.put("xq", "��");
			map.put("nd", Base.getJxjsqnd());
			map.put("sqsj", "��");
		}else{
			//������
			map.put("xn", "��");
			map.put("xq", "��");
			map.put("nd", "��");
			map.put("sqsj", GetTime.getNowTime2());
		}		
		return map;
	}
	
	/**
	 * ��ѧ�����
	 * @param pkValue
	 * @param shzd
	 * @return String
	 * */
	public String auditingJxj(HashMap<String, String[]> pkValue, 
			                  String shzd,
			                  String tableName){
		DAO dao = DAO.getInstance();
		String resultMsg = "";
		String[] pkV = pkValue.get("cbv");
		String key = GlobalsVariable.PJPY_ZHCPJXJ;
		//����
		String pk = "xh||xn||nd||xq";
		
		for(int i=0; i<pkV.length; i++){
			//�ж������Ƿ���
			String msg = checkJxjrs(pk,pkV[i], shzd,key);
			if(StringUtils.isNotNull(msg)){
				if(pkV.length<2){
					resultMsg += "����ʧ�ܣ�" + msg + "\n";
				}else{
					resultMsg += "��" + (i+1) + "�в���ʧ�ܣ�" + msg + "\n";
				}
				
			}else{
				try{
					dao.runUpdate("update " + tableName + " set " + shzd + "='ͨ��' where " + pk + "=?", 
							      new String[]{pkV[i]});
				} catch (Exception e) {
					if(pkV.length<2){
						resultMsg += "����ʧ�ܣ�\n";
					}else{
						resultMsg += "��" + (i+1) + "�в���ʧ�ܣ�\n";
					}                     
				}
			}
		}
		
		return resultMsg;
	}
	
	/**
	 * �жϽ�ѧ�������Ƿ񳬹�����
	 * @param pkV
	 * @param shzd
	 * @return String
	 * */
	public String checkJxjrs(String pk,String pkV,String shzd,String key){
		String result = "";
		PjpyZjkjxyDAO zjkjDao = new PjpyZjkjxyDAO();
		HashMap<String, String> rsfpMap = zcjxjDao.getRsfpMap(pk,pkV,shzd,key);
			
		//��ѯ��ѧ������������ֵ
		String zjz = rsfpMap.get("pkV");
		//�Ѿ����ͨ��������������˼���Χ�����ͨ����������
		int tgrs = Integer.parseInt(rsfpMap.get("tgrs"));
		
		//��ѧ������������������
		String jxjrs = zjkjDao.getJxjrs(zjz,shzd,key);
		
		if(StringUtils.isNull(jxjrs)){
			result = "";
		}else{
			int fprs = Integer.parseInt(jxjrs);
			if(fprs<=tgrs){
				result = "���ͨ�������Ѿ��ﵽ����������";
			}
		}
		return result;
	}
	
	/**
	 * ��ȡ�ۺ����ʲ�����ѧ�𵼳����ݵı�ͷ����Ϣ
	 * @param pjzq
	 * @param jb
	 * @param xmdmMap
	 * @return HashMap<String, String[]>
	 * */
	public HashMap<String, String[]> getZhcpjxjsbTitle(String pjzq,
			String jb, HashMap<String, String> map, boolean isQuery,
			PjpyZhcpjxjActionForm model) throws Exception {
		HashMap<String, String[]> rs = new HashMap<String, String[]>();
		PjpyZhszcpDAO dao = new PjpyZhszcpDAO();
		String viewName = "view_pjpy_zhcpjxjsb";//��ͼ
		String[] en = {"xh","xm","nj","xymc","zymc","bjmc"};//Ĭ��Ҫ��������ѧ��������Ϣ��ص��ֶ�
		
		if (isQuery) {
			en = new String[]{"pk", "xh","xm","bjmc"};
		}
		
		String[] cn = {};//�е���������
		
		//��������
		if("xn".equalsIgnoreCase(pjzq)){
			en = StringUtils.joinStrArr(en, new String[]{"xn"});
		}else if("xq".equalsIgnoreCase(pjzq)){
			en = StringUtils.joinStrArr(en, new String[]{"xn", "xqmc"});
		}else if("nd".equalsIgnoreCase(pjzq)){
			en = StringUtils.joinStrArr(en, new String[]{"nd"});
		}
		en = StringUtils.joinStrArr(en,new String[]{"jxjmc","xysh","xxsh","ƽ��ѧ�ּ���"});
		//��ȡĬ��Ҫ�����е���������
		cn = dao.getColumnNameCN(en, viewName);
		
		//Ҫ��������Ŀ
		if(!"1".equalsIgnoreCase(jb)){//��һ��
			String xmdm = map.get(jb);//��Ŀ����
			String[] xmdmArray = new String[]{};
			if ("2".equalsIgnoreCase(jb)) {
				if (StringUtils.isNotNull(model.getQueryequals_ejdm())) {
					xmdm = map.get("2");
					xmdmArray = new String[]{xmdm};
				}
				
			} else if ("3".equalsIgnoreCase(jb)) {
				if (StringUtils.isNotNull(model.getQueryequals_sjdm())) {
					xmdm = map.get("3");
					xmdmArray = new String[]{xmdm};
				} else if (StringUtils.isNotNull(model.getQueryequals_ejdm())) {
					xmdm = map.get("2");
					xmdmArray = dao
							.getArray(
									"select dm from pjpy_zctjszb where fdm = ? and dmjb='3'",
									new String[] { xmdm }, "dm");
				}
				
			} else if ("4".equalsIgnoreCase(jb)) {
				if (StringUtils.isNotNull(model.getQueryequals_sidm())) {
					xmdm = map.get("4");	
					xmdmArray = new String[]{xmdm};
				} else if (StringUtils.isNotNull(model.getQueryequals_sjdm())) {
					xmdm = map.get("3");
					xmdmArray = dao
							.getArray(
									"select dm from pjpy_zctjszb where fdm = ? and dmjb='4'",
									new String[] { xmdm }, "dm");
				} else if (StringUtils.isNotNull(model.getQueryequals_ejdm())) {
					xmdm = map.get("2");
					xmdmArray = dao
							.getArray(
									"select dm from pjpy_zctjszb a where exists (select 1 from pjpy_zctjszb b where b.fdm = ? and b.dmjb='3' and a.fdm=b.dm)",
									new String[] { xmdm }, "dm");
				}
			}
			
			cn = StringUtils.joinStrArr(cn,  dao.getZhcpxmMc(jb,xmdmArray));
		}
		//Ĭ�ϵ����ܷ��ֶ�
		cn = StringUtils.joinStrArr(cn, new String[]{"�ܷ�","�ܷ�����"});//�ܷ�
		
		//���������к�
//		if (en != null && cn != null && en.length == cn.length) {
//			String[] addEn = new String[en.length + 1];
//			String[] addCn = new String[cn.length + 1];
//			addEn[0] = "r";
//			addCn[0] = "�к�";
//			for (int i=0;i<en.length;i++) {
//				addEn[i + 1] = en[i];
//				addCn[i + 1] = cn[i];
//			}
//		}
		rs.put("en", en);
		rs.put("cn", cn);
		
		return rs;
	}
	
	/**
	 * ��ѯ�ۺϲ����ܷ���Ϣ
	 * @param pjzq
	 * @param jb
	 * @param map
	 * @param model
	 * @return List<String[]>
	 * @throws Exception 
	 * */
	public List<String[]> queryZhcpjxjsb(String pjzq, String jb,
			HashMap<String, String> map, String[] output,
			PjpyZhcpjxjActionForm model, boolean isPage) throws Exception {		
		return zcjxjDao.queryZhcpjxjsb(pjzq, jb, map, output, model, isPage);
	}
	
	/**
	 * �����ۺϲ�����ѧ���ϱ���Ϣ
	 * @param pkValues
	 * @param jxjdm
	 * @param user
	 * @return boolean
	 * */
	public boolean zhcpjxjSb(String[] pkValues, String jxjdm, User user){
		return zcjxjDao.saveZhcpjxjsb(pkValues, jxjdm, user);
	}
	
	/**
	 * ȡ��ѧԺ�ۺϲ�����ѧ���ϱ�
	 * @param pkValues
	 * @param user
	 * @return boolean
	 * */
	public boolean zhcpjxjQxsb(String[] pkValues, User user){
		return zcjxjDao.delZhcpjxjsb(pkValues, user);
	}
	
	/**
	 * ��ȡ������Ϣ
	 * @param model
	 * @param xmlx
	 * @return List<HashMap<String, String>>
	 * **/
	public List<HashMap<String, String>> getTjList(JxjpdxxModel model, String xmlx){
		LsxyPjpyDAO dao = new LsxyPjpyDAO();
		return dao.getTjList(model, xmlx);
	}
	
	/**
	 * ��ȡ������Ϣ
	 * @param model
	 * @param xmlx
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getTjxxList(PjpyZhcpjxjActionForm model,String xmlx){
		return zcjxjDao.selectTjxxList(model,xmlx);
	}
}
