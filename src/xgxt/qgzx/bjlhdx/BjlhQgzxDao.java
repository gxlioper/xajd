package xgxt.qgzx.bjlhdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �������ϴ�ѧ�ڹ���ѧģ��DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-11-17</p>
 */
public class BjlhQgzxDao extends DAO{
	/**
	 * ��ȡ��λ�����б�
	 * @return List
	 * */
	public List getGwxzList(){
		String sql = "select distinct gwxzdm,gwxzmc from gwxzdmb";
		return getList(sql, new String[]{}, new String[]{"gwxzdm","gwxzmc"});
	}
	
	/**
	 * ����λ�����Ƿ���
	 * @param pkValue
	 * @return String
	 * */
	@SuppressWarnings("unchecked")
	public String checkGwrs(String pkValue, String userType){
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		List xhValue = null;
		int iKnsCount = 0;
		String sMess = "";
		String xh  = "";
//		pkValue = pkValue;
		
			
		String sql = "select sqsyrs,sqsyknss,gwdm from view_gwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_xsgwxx where xh||gwdm||sqsj=?)";
		map = dao.getMap(sql, new String[]{pkValue}, new String[]{"sqsyrs","sqsyknss","gwdm"});
		
		//��ѯѧ��
		sql = "select xh from view_xsgwxx where xh||gwdm||sqsj=?";
		xh = getOneRs(sql, new String[]{pkValue}, "xh");
		
		//ѧУ���ͨ��������
		sql = "select count(*)count from view_xsgwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_xsgwxx where xh||gwdm||sqsj=? ) and xn||nd||xq = (select xn||nd||xq from view_xsgwxx where xh||gwdm||sqsj=? ) and "+ userType+"='ͨ��'";
		map.putAll(dao.getMap(sql, new String[]{pkValue,pkValue}, new String[]{"count"}));
		
		//ѧУ���ͨ����������
		sql = "select xh from view_xsgwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_xsgwxx where xh||gwdm||sqsj=?) and " + userType + "='ͨ��'";
		xhValue = dao.getList(sql, new String[]{pkValue}, new String[]{"xh"});
		for(int j=0 ; j<xhValue.size(); j++){
			HashMap<String, String> tmpMap = (HashMap<String, String>)xhValue.get(j);	
			if(dao.isKns(tmpMap.get("xh"))){
				iKnsCount += 1;
			}
		}
		String sqsyrs = map.get("sqsyrs");
		String count = map.get("count");
		String sqsyknss = map.get("sqsyknss");
		
		sqsyrs = sqsyrs == null || "".equalsIgnoreCase(sqsyrs) ? "0" : sqsyrs;
		count = count == null || "".equalsIgnoreCase(count) ? "0" : count;
		sqsyknss = sqsyknss == null || "".equalsIgnoreCase(sqsyknss) ? "0" : sqsyknss;		
		
		if(Integer.parseInt(sqsyrs)<= Integer.parseInt(count)){
			sMess += xh + "���ʧ�ܣ���λ" + map.get("gwdm") + "ʹ����������";
		}
		if(Integer.parseInt(sqsyrs)-Integer.parseInt(sqsyknss)<= Integer.parseInt(count) - iKnsCount){
			if(!dao.isKns(xh)){
				sMess +=  xh + "���ʧ�ܣ���λ" + map.get("gwdm") + ",ֻ�����������ſ�ͨ��!";
			}
		}
		
		return sMess;
	}
	
	/**
	 * ��ȡʱ�������б�����
	 * @return List
	 * */
	@SuppressWarnings("unchecked")
	public List getQgzxsjList(){
		List arraylist = new ArrayList();
		String[] gwsqsj= getOneRs("select mxsbc,mtzgxs from gwsqsjb", new String[]{},new String[]{"mxsbc","mtzgxs"});
		if(gwsqsj != null && gwsqsj.length != 0){
			double i = 0;
			if(gwsqsj[1] != null){
				i = Float.parseFloat(gwsqsj[1]);
			}
			while(i>=0.5){
				HashMap<String, String> sj = new HashMap<String, String>();
				sj.put("sj", String.valueOf(i));
				arraylist.add(sj);
				i = i - 0.5;
			}
		}
		return arraylist;
	}
}
