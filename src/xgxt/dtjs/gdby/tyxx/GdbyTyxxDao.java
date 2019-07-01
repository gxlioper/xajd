package xgxt.dtjs.gdby.tyxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

import common.Globals;

public class GdbyTyxxDao extends DAO{
	
	
	public HashMap<String, String> selectStuDetail(String xh){
		String xxdm = StandardOperation.getXxdm();//ѧУ����
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select * from view_xsxxb where xh=?";
		String[] outputValue = {"xh","xm","xb","xymc","zymc",
				                "bjmc"};
		map = getMap(sql, new String[]{xh}, outputValue);
		HashMap<String, String> configer = selectXsxxglXsxx();//����������Ϣ
		if("yes".equalsIgnoreCase(configer.get("dzxxqdm")) && !Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			//ѧ��������Ϣȡ�����ҷǵ��ʴ�ѧ
			map.put("syd", dzxxdmToMc(map.get("syd")));
			map.put("jg", dzxxdmToMc(map.get("jg")));
		}
		
		return map;
	}

	/**
	 * ��ѯѧ����Ϣģ�����������Ϣ
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectXsxxglXsxx(){
		String sql = "select * from xsxxxgsjb";
		String[] colList = {"bdsj","bkjssj","bkkssj","bzrjssj","bzrkssj","cjbgdffsj","havexsqx","issz","jssj","kssj","kxsj","nd","xn","xq","xzf","xsxgxxsh","dzxxqdm"};
		return getMap(sql, new String[]{}, colList);
	}
	
	/**
	 * ���ݵ��������ȡ����
	 * @param dzdm
	 * @return String 
	 * */
	public String dzxxdmToMc(String dzdm){
		String dzmc = "";
		String sql = "";
		if(StringUtils.isNotNull(dzdm)){
			String[] dmArr = dzdm.split("/");
			for(String dm : dmArr){
				sql = "select qxmc mc from dmk_qx where qxdm=?";
				dzmc += getOneRs(sql, new String[]{dm}, "mc");
			}
		}
		
		return dzmc;
	}
	
	
	/**
	 * ��ȡ�춯�����б�
	 */
	public List<HashMap<String,String>> initTyYdLx(String ydzt){
		
		DAO dao = DAO.getInstance();
		HashMap<String,String>map=new HashMap<String,String>();
		
		String sql = "select ydlxmc mc,ydlxdm dm from gdby_dtjs_ydlxdmb where ydzt =?";
		map.put("dm", "");
		map.put("mc", "-��ѡ��-");
		List <HashMap<String,String>>xyList =new ArrayList<HashMap<String,String>>(); 
		xyList.add(map);
		xyList.addAll(dao.getList(sql, new String[] {ydzt}, new String[] {
			"mc","dm" }));
	    return xyList;
	}
	
	
	/**
	 * �޸��ڼ���Ϣ
	 */
	public void updateZjxx(String xh){
		
		DAO dao=DAO.getInstance();
		String sql="update gdby_dtjs_tyxxzb set sfzj=(case when (select ydzt from gdby_dtjs_tyxxydb where ydsj=( select  max(ydsj) from gdby_dtjs_tyxxydb where xh=? ) and xh=?)='ת��' then '��' else '��' end) where xh=?";
		try {
			dao.runUpdate(sql, new String[]{xh,xh,xh});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ�춯״̬List
	 */
	public List<HashMap<String,String>>ydztList(){
		DAO dao=DAO.getInstance();
		String sql="select ydzt mc,ydztdm dm from gdby_dtjs_tyydzt";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * ��ȡ�춯�����б�
	 */
	public List<HashMap<String,String>> ydlxList(){
		DAO dao = DAO.getInstance();
		String sql = "select ydlxmc mc,ydlxdm dm from gdby_dtjs_ydlxdmb";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
}
