package xgxt.rcgl.gzdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;

public class RcglGzdxService {
	
	//��ñ�ͷ
	public List<HashMap<String,String>> getTableTop(String lb){
		String[] ens = null;
		String[] cns = null;
		String xxdm=Base.xxdm;
		DAO dao = DAO.getInstance();
		if("yysh".equals(lb)){
			ens = new String[]{"guid","bmmc","cdmc","yyrq","yysjd","fzr","lxdh","sqr","xxsh"};
			cns = new String[]{"guid","ʹ�ò���","ԤԼ����","ԤԼʱ���","������","��ϵ�绰","������","ѧУ���"};
			if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
				ens = new String[]{"guid","bmmc","cdmc","sqsj","yyrq","yysjd","fzr","lxdh","sqr","shpf","xxsh"};
				cns = new String[]{"guid","ʹ�ò���","����ʱ��","ԤԼ����","ԤԼʱ���","������","��ϵ�绰","������","�������","ѧУ���"};
			}
		}else if("zbry".equals(lb)){
			ens = new String[]{"xh","xh","xm","xb","xymc","zymc","bjmc","bz"};
			cns = new String[]{"xh","ѧ��","����","�Ա�",Base.YXPZXY_KEY+"����","רҵ����","�༶����","��ע"};
		}else if("xsgly".equals(lb)){
			ens = new String[]{"xh","xh","xm","xb","xymc","zymc","bjmc","sfqy","bz"};
			cns = new String[]{"xh","ѧ��","����","�Ա�",Base.YXPZXY_KEY+"����","רҵ����","�༶����","�Ƿ�����","��ע"};
		}else if("zbsz".equals(lb)){
			ens = new String[]{"guid","bmmc","cdmc","yyrq","yysjd","fzr","lxdh","zbry","zbdh"};
			cns = new String[]{"guid","ʹ�ò���","��������","ԤԼ����","ԤԼʱ���","������","��ϵ�绰","ֵ����Ա","ֵ��绰"};
		}
		
		return dao.arrayToList(ens, cns);
	}
	
	/**
	 *��ò����б�
	 */
	public List<HashMap<String, String>> getXxbm_ser(){
		DAO dao = DAO.getInstance();
		return dao.getBmList();
	}
	
	/**
	 *���ԤԼ��Դ����
	 */
	public List<HashMap<String, String>> getZyyycd_ser(){
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.getZyyycd_db();
	}
	
	/**
	 *���ԤԼ�豸
	 */
	public List<HashMap<String, String>> getZyyysb_ser(){
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.getZyyysb_db();
	}
	
	/**
	 *������Դ����
	 */
	public boolean saveZyyysq_ser(RcglGzdxForm model,String userType,String userName){
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.saveZyyysq_db(model,userType,userName);
	}
	
	/**
	 *���Сʱlist
	 */
	public List<HashMap<String, String>> getHours_ser(){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for(int i=1;i<=24;i++){
			HashMap<String, String> map = new HashMap<String, String>();
			if(i<10){
				map.put("dm", "0"+i);
			}else{
				map.put("dm", ""+i);
			}
			list.add(map);
		}
		return list;
	}
	
	
	/**
	 * 2010.9.20 qlj
	 * �жϳ����Ƿ�
	 * ���Ա����
	 */
	public boolean checkCdkj(RcglGzdxForm model){
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.checkCdkj(model);
	}
	
	/**
	 * 2010.9.20 qlj
	 * �жϳ����Ƿ�
	 * ���Ա����
	 */
	public boolean checkSbkj(RcglGzdxForm model){
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.checkSbkj(model);
	}
	
	/**
	 *��÷���list
	 */
	public List<HashMap<String, String>> getMinutes_ser(){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for(int i=0;i<=50;i=i+10){
			HashMap<String, String> map = new HashMap<String, String>();
			if(i<10){
				map.put("dm", "0"+i);
			}else{
				map.put("dm", ""+i);
			}
			list.add(map);
		}
		return list;
	}
	
	/**
	 *ԤԼ���
	 */
	public boolean zyyySh_ser(String pkvals,String xxsh) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.zyyySh_db(pkvals, xxsh);
	}
	
	/**
	 *���ݴ�ѧ
	 *ԤԼ���
	 */
	public boolean zyyySh_gzdx(String pkvals,String xxsh,String shpf) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		//��˽��Ϊͨ��
		if("tg".equalsIgnoreCase(xxsh)){
			if(dao.shCheck(pkvals)){
				return dao.zyyySh_gzdx(pkvals, xxsh,shpf);
			}else{
				return false;
			}
		}else{
			return dao.zyyySh_gzdx(pkvals, xxsh,shpf);
		}
	}
	
	/**
	 *��ѯԤԼ�������
	 */
	public List<HashMap<String, String>> zyyyShQuery_ser(RcglGzdxForm model) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.zyyyShQuery_db(model);
	}
	
	/**
	 *��õ���ԤԼ��Ϣ
	 */
	public HashMap<String, String> getOneYyxx_ser(String pkval) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.getOneYyxx_db(pkval);
	}
	
	/**
	 *�޸ĵ���ԤԼ��Ϣ
	 */
	public boolean updateOneYyxx_ser(String pkval,RcglGzdxForm model) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.updateOneYyxx_db(pkval,model);
	}
	
	/**
	 *ɾ��ԤԼ��Ϣ
	 */
	public boolean deleteYyxx_ser(String pkvals) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.deleteYyxx_db(pkvals);
	}
	
	/**
	 *����Ƿ�Ϊѧ������Ա
	 */
	public String isXsgly_ser(String xh) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.isXsgly_db(xh);
	}
	
	/**
	 *��ѯֵ����Ա
	 */
	public List<HashMap<String, String>> queryZbry_ser(RcglGzdxForm model) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.queryZbry_db(model);
	}
	
	/**
	 *ɾ��ֵ����Ա
	 */
	public boolean deleteZbry_ser(String pkvals) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.deleteZbry_db(pkvals);
	}
	
	/**
	 *���ĳֵ����Ա��Ϣ
	 */
	public HashMap<String,String> getOneZbryxx_ser(String xh) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.getOneZbryxx_db(xh);
	}
	
	/**
	 *�޸�ĳֵ����Ա��Ϣ
	 */
	public boolean updateOneZbryxx_ser(RcglGzdxForm model) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.updateOneZbryxx_db(model);
	}
	
	/**
	 *����ĳֵ����Ա��Ϣ
	 */
	public boolean addOneZbryxx_ser(RcglGzdxForm model) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.addOneZbryxx_db(model);
	}
	
	/**
	 *���ĳѧ����Ϣ
	 */
	public HashMap<String,String> getOneXsxx_ser(String xh) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.getOneXsxx_db(xh);
	}
	
	/**
	 *����ֵ����Ա
	 */
	public boolean zbrysz_ser(String pkvals,String guid) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.zbrysz_db(pkvals,guid);
	}
	
	/**
	 *��ѯֵ����Ա
	 */
	public List<HashMap<String, String>> queryZbrysz_ser(RcglGzdxForm model) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.queryZbrysz_db(model);
	}
	
	/**
	 *ɾ��ѧ������Ա
	 */
	public boolean deleteXsgly_ser(String pkvals) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.deleteXsgly_db(pkvals);
	}
	
	/**
	 *��ѯѧ������Ա
	 */
	public List<HashMap<String, String>> queryXsgly_ser(RcglGzdxForm model) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.queryXsgly_db(model);
	}
	
	/**
	 *����ѧ������Ա��Ϣ
	 */
	public boolean addOneXsglyxx_ser(RcglGzdxForm model) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.addOneXsglyxx_db(model);
	}
	
	/**
	 *�޸�ѧ������Ա��Ϣ
	 */
	public boolean updateOneXsglyxx_ser(RcglGzdxForm model) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.updateOneXsglyxx_db(model);
	}
	
	/**
	 *���ѧ������Ա��Ϣ
	 */
	public HashMap<String,String> getOneXsglyxx_ser(String xh) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.getOneXsglyxx_db(xh);
	}
	
	/**
	 * ֪ͨ������ʾ
	 * */
	public String getHkxz_ser(String lb) {
		RcglGzdxDAO dao = new RcglGzdxDAO();
		return dao.getTzgg_db(lb);
	}
}
