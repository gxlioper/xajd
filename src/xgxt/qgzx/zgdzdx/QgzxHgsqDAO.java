package xgxt.qgzx.zgdzdx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.utils.String.StringUtils;

public class QgzxHgsqDAO extends DAO {
	DAO dao = DAO.getInstance();
	
	/**��ȡ��λ��������ʱ��*/
	public String[] getStuTime(){
		String sql = "select xn,nd,xq,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc from gwsqsjb a where rownum=1";
		return dao.getOneRs(sql, new String[] {}, new String[] { "xn", "nd", "xq","xqmc" });
	}
	
	/**��ȡ��λ�����б�*/
	public List<HashMap<String, String>> getGwNameList(String isLsgw){
		
		String query = "";
		
		if ("yes".equals(isLsgw)) {
			query = " and gwxz<>(select gwxzdm from gwxzdmb where gwxzmc='��ʱ��λ')";
		}
		
		String sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx a where SHJG='ͨ��' and gzjsrq>=to_char(sysdate,'yyyymmdd')"+query;
		return dao.getList(sql, new String[] {}, new String[] { "gwdm","gwdmgwsbsj" });
	}
	
	/**��ȡ��λ����*/
	public String[] getGwName(String tmp){
		String sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from gwxxb where gwdm||gwsbsj =?";
		return dao.getOneRs(sql, new String[] { tmp }, new String[] { "gwdm", "gwdmgwsbsj" });
	}
	
	/**��ȡѧ��ͨ��ѧԺ�Ƽ���¼*/
	public String getXytjcount(String xh,String xn,String nd,String xq){
		String sql = "select count(*) num from qgzxsqb where xh=? and xn=? and nd=? and xq=? and xxsh='ͨ��'";
		return dao.getOneRs(sql, new String[] { xh, xn, nd, xq }, "num");
	}
	
	/**��ȡѧ����λ��˼�¼*/
	public String getGwshcount(String xh,String xn,String nd,String xq){
		String sql = "select count(*)num from xsgwxxb where xh=? and xn=? and nd=? and xq=? and xxyj='ͨ��'";
		return dao.getOneRs(sql, new String[] {xh,xn,nd,xq}, "num");
	}
	
	/**��ȡѧ���ڸڸ�λ*/
	public String getGwdm(String xh,String xn,String nd,String xq){
		String sql = "select gwdm||'-'||gwsbsj gwdmgwsbsj from xsgwxxb where xh=? and xn=? and nd=? and xq=? and xxyj='ͨ��'";
		return dao.getOneRs(sql, new String[] {xh,xn,nd,xq}, "gwdmgwsbsj");
	}
	
	/**ɾ��ѧ���ڹ���ѧ��ʱ��
	 * @throws Exception */
	public void deleteQgzxTime(String xh) throws Exception{
		String sql = "delete from xsqgzxsjb where xh=?";
		dao.runUpdate(sql, new String[]{xh});
	}
	
	/**����ѧ���ڹ���ѧʱ��*/
	public void saveQgzxTime(String xh,String tmp1,String tmp2,String tmp3) throws Exception{
		String sql = "insert into xsqgzxsjb(xh,xq,sj,kxbz)values(?,?,?,?)";
		dao.insert(sql,new String[]{xh,tmp1,tmp2,tmp3});
	}
	
	/**����ѧ���ڹ���ѧ����������Ϣ*/
	public boolean hg_save(String[] tmp) throws SQLException{
		if(checkExists("zgdzdx_xshgxxb", "xh||gwdm||gwsbsj", tmp[0]+tmp[1]+tmp[2])){			
			return false;
		}
		String sql = "insert into zgdzdx_xshgxxb(xh,gwdm,gwsbsj,lxdh,xn,nd,xq,sqly,yhtc,hgdm,hgsj,bz)values(?,?,?,?,?,?,?,?,?,?,?,?)";
		return dao.insert(sql,tmp);
	}
	
	/**ͨ��ѧԺ�����ȡ��λ�б�*/
	public List<HashMap<String, String>> getGwListForXydm(String userName){
		String sql = "select distinct gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx a where exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm=? )";
		return dao.getList(sql, new String[] { userName }, new String[] {"gwdm", "gwdmgwsbsj" });
	}
	
	/**ͨ��ѧԺ�����ȡ��λ�б�*/
	public List<HashMap<String, String>> getGwList(String userName){
		QgzxDao qgzxDao = new QgzxDao();
		String sql = "select distinct gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx a ";
		if(qgzxDao.isYrdwUser(userName)){
			sql += " where exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm='" + userName + "')";
		}
		return dao.getList(sql, new String[] {}, new String[] { "gwdm", "gwdmgwsbsj" });
	}
	
	/**��ȡ��λ�����б�*/
	public List<HashMap<String, String>> getGwXzList(){
		String sql = "select gwxzdm,gwxzmc from gwxzdmb";
		return dao.getList(sql, new String[] {}, new String[] {"gwxzdm", "gwxzmc" });
	}
	
	/**��ȡ���뻻��ѧ���б�*/
	public ArrayList<String[]> getHgXs(StringBuffer querry,String[] colList){
		String sql = "select xh ||'-'|| gwdm ||'-'|| gwsbsj ||'-'|| hgdm ||'-'|| hgsj ����, rownum �к�,"
       +"a.*,(select xqmc from xqdzb b where a.xq = b.xqdm) xqmc "
       +"from view_zgdzdx_xshgxxb a" + querry.toString();
		System.out.println(sql);
		return dao.rsToVator(sql, new String[] {}, colList);
	}
	
	/**��ȡ����ѧ��������ϸ��Ϣ*/
	public HashMap<String, String> getQgzxOne(String pkValue){
		String [] tmp = pkValue.split("-");
		String sql = "select a.*,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc from view_zgdzdx_xshgxxb a where xh=? and gwdm=? and gwsbsj=? and hgdm=? and hgsj=?";
		return dao.getMapNotOut(sql, tmp);
	}
	
	/**ѧ��������Ϣ����
	 * @throws Exception */
	public boolean hgxx_update(String[] tmp) throws Exception{
		String sql = "update zgdzdx_xshgxxb set lxdh=?,xn=?,nd=?,xq=?,sqly=?,yhtc=?,"
			+ "hgdm=?,hgsj=?,bz=? where xh=? and gwdm=? and gwsbsj=? and hgdm=? and hgsj=?";
		return dao.runUpdate(sql, tmp);
	}
	
	/**ѧ���������
	 * @throws Exception */
	public boolean hgsqsh(String[] tmp,String userType,String type) throws Exception{
		String sql = "update zgdzdx_xshgxxb set "+userType+"yj = '"+type+"' where "
				+" xh=? and gwdm=? and gwsbsj=? and hgdm=? and hgsj=?";
		return dao.runUpdate(sql, tmp);
	}
	
	/**ѧ��������˺�������¼����Ϊ��ͨ��
	 * @throws Exception */
	public boolean hgsqshOther(String[] tmp,String userType,String type) throws Exception{
		String sql = "update zgdzdx_xshgxxb set "+userType+"yj = '"+type+"' where "
				+" xh=? and gwdm<>? and gwsbsj<>? and hgdm<>? and hgsj<>?";
		return dao.runUpdate(sql, tmp);
	}
	
	/**��ȡѧ���ڸ�λ��Ϣ*/
	public HashMap<String, String> getXsgwxx(String xh,String gwdm,String gwsbsj){
		String sql = "select * from xsgwxxb where xh=? and gwdm=? and gwsbsj=?";
		return dao.getMapNotOut(sql, new String[]{xh,gwdm,gwsbsj});
	}
	
	/**��ȡѧ��������Ϣ*/
	public HashMap<String, String> getXshgxx(String[] tmp){
		String sql = "select * from zgdzdx_xshgxxb where xh=? and gwdm=? and gwsbsj=? and hgdm=? and hgsj=?";
		return dao.getMapNotOut(sql, tmp);
	}
	
	/**����ѧ����λ��Ϣ��
	 * @throws Exception */
	public boolean updateXsgwxxb(HashMap<String, String> map,String xh,String gwdm,String gwsbsj) throws Exception{
		String[] tmp = {map.get("xyyj"),map.get("xxyj"),map.get("lxdh"),map.get("xn"),map.get("nd"),
				map.get("xq"),map.get("sqly"),map.get("yhtc"),map.get("hgdm"),map.get("hgsj"),
				map.get("bz"),xh,gwdm,gwsbsj};
		String sql = "update xsgwxxb set xyyj=?,xxyj=?,lxdh=?,xn=?,nd=?,xq=?,xssq=?,yhtc=?,gwdm=?,gwsbsj=?,bz=?"
			+ " where xh=? and gwdm=? and gwsbsj=?";
		return dao.runUpdate(sql, tmp);
	}
	
	/**������ڼ�¼
	 * @throws SQLException */
	public boolean insertDgjl(String xn,String xq,HashMap<String, String> gwmap,HashMap<String, String> hgmap) throws SQLException{
		String[] tmp = {xn,xq,hgmap.get("xh"),hgmap.get("sqsj"),hgmap.get("xn"),hgmap.get("xq"),hgmap.get("nd"),
				hgmap.get("hgdm"),hgmap.get("hgsj"),gwmap.get("xn"),gwmap.get("xq"),gwmap.get("nd"),
				gwmap.get("gwdm"),gwmap.get("gwsbsj")};
		String sql="insert into qgzx_gwtzb(xn,xq,xh,tzsj,tzhgzxn,tzhgzxq,tzhgznd,tzhgw,tzhgwsbsj,"
			+ "tzqgzxn,tzqgzxq,tzqgznd,tzqgw,tzqgwsbsj)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return dao.insert(sql, tmp);
	}
	
	/**����Ǹ���Ϣ
	 * @throws SQLException */
	public boolean cgxx_save(String[] tmp) throws SQLException{
		if(checkExists("zgdzdx_xscgxxb", "xh||gwdm||gwsbsj", tmp[0]+tmp[1]+tmp[2])){
			return false;
		}
		String sql = "insert into zgdzdx_xscgxxb(xh,gwdm,gwsbsj,lxdh,xn,nd,xq,sqly,bz)values(?,?,?,?,?,?,?,?,?)";
		return dao.insert(sql, tmp);
	}
	
	/**���´Ǹ���Ϣ
	 * @throws Exception */
	public boolean cgxx_update(String[] tmp) throws Exception{
		String sql = "update zgdzdx_xscgxxb set lxdh=?,sqly=?,bz=? where xh=? and gwdm=? and gwsbsj=?";
		return dao.runUpdate(sql,tmp);
	}
	
	/**��ȡ����Ǹ�ѧ���б�*/
	public ArrayList<String[]> getCgXs(StringBuffer querry,String[] colList){
		String sql = "select xh ||'-'|| gwdm ||'-'|| gwsbsj ����, rownum �к�,"
				     +"a.*,(select xqmc from xqdzb b where a.xq = b.xqdm) xqmc "
				     +"from view_zgdzdx_xscgxxb a" + querry.toString();
		System.out.println(sql);
		return dao.rsToVator(sql, new String[] {}, colList);
	}
	
	/**ѧ���Ǹ����
	 * @throws Exception */
	public boolean cgsqsh(String[] tmp,String userType,String type) throws Exception{
		String sql = "update zgdzdx_xscgxxb set "+userType+"yj = '"+type+"' where "
				+" xh=? and gwdm=? and gwsbsj=?";
		return dao.runUpdate(sql, tmp);
	}
	
	/**��ȡѧ���Ǹ���Ϣ*/
	public HashMap<String, String> getXscgxx(String[] tmp){
		String sql = "select * from zgdzdx_xscgxxb where xh=? and gwdm=? and gwsbsj=?";
		return dao.getMapNotOut(sql, tmp);
	}
	
	/**ɾ��ѧ����λ��Ϣ���дǸ�ѧ����¼
	 * @throws Exception */
	public boolean deleteXsgwxxb(String xh,String gwdm,String gwsbsj) throws Exception{
		String sql = "delete xsgwxxb where xh=? and gwdm=? and gwsbsj=?";
		return dao.runUpdate(sql, new String[] {xh,gwdm,gwsbsj});
	}
	
	/**������ڼ�¼
	 * @throws SQLException */
	public boolean insertDgjl(String xn,String xq,HashMap<String, String> gwmap) throws SQLException{
		boolean result = true;
		String[] tmp = {xn,xq,gwmap.get("xh"),gwmap.get("xn"),gwmap.get("xq"),gwmap.get("nd"),
				gwmap.get("gwdm"),gwmap.get("gwsbsj")};
		String tzhgw = "�ѴǸ�";
		String xxdm = StandardOperation.getXxdm();
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//���ݴ�ѧ
			tzhgw = "�˸�";
		}
		if(StringUtils.isNotNull(gwmap.get("xh"))){
			String sql="insert into qgzx_gwtzb(xn,xq,xh,tzhgw,"
				+ "tzqgzxn,tzqgzxq,tzqgznd,tzqgw,tzqgwsbsj,tzsj,shjg)values(?,?,?,'" + tzhgw + "',?,?,?,?,?,to_char(SYSDATE,'YYYYMMDDHH24MISS'),'ͨ��')";
			result = dao.insert(sql, tmp);
		}
		return result;
	}
	
	/**��ȡ����ѧ���Ǹ���ϸ��Ϣ*/
	public HashMap<String, String> getQgzxcgOne(String pkValue){
		String [] tmp = pkValue.split("-");
		String sql = "select a.*,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc from view_zgdzdx_xscgxxb a where xh=? and gwdm=? and gwsbsj=?";
		return dao.getMapNotOut(sql, tmp);
	}
	
	/**��ȡ���˵�λ��Ϣ*/
	public HashMap<String, String> getYrdwxx(String userName){
		String sql = "select * from yrdwdmb where dlm=?";
		return dao.getMapNotOut(sql, new String[] {userName});
	}
	
	/**�й����ʴ�ѧ��ȡ���˵�λ����ѧ����ʱ��Ϣ*/
	public ArrayList<String[]> getYrdwctxslsxx(String userName){
		String sql = "select xh,gwdm,gwsbsj,lxdh,bj,yhm from zgdzdx_yrdwghxslsb where yhm=? and bj='����'";
		return dao.rsToVator4(sql, new String[]{userName}, new String[]{"xh","gwdm","gwsbsj","lxdh","bj","yhm"});
	}
	/**�й����ʴ�ѧ��ȡ���˵�λ����ѧ����ʱ��Ϣ*/
	public ArrayList<String[]> getYrdwghxslsxx(String userName){
		String sql = "select xh,gwdm,gwsbsj,lxdh,bj,yhm from zgdzdx_yrdwghxslsb where yhm=? and bj='����'";
		return dao.rsToVator4(sql, new String[]{userName}, new String[]{"xh","gwdm","gwsbsj","lxdh","bj","yhm"});
	}
	/**�й����ʴ�ѧ��ȡ���˵�λ����ѧ����ʱ��Ϣ*/
	public ArrayList<String[]> getYrdwsqxslsxx(String userName){
		String sql = "select xh,gwdm,gwsbsj,lxdh,bj,yhm from zgdzdx_yrdwghxslsb where yhm=? and bj='����'";
		return dao.rsToVator4(sql, new String[]{userName}, new String[]{"xh","gwdm","gwsbsj","lxdh","bj","yhm"});
	}
	
	/**�й����ʴ�ѧ���˵�λ����ѧ����ʽ���ݱ���
	 * @throws Exception */
	public boolean insertYrdwghXs(String xh,String gwdm,String gwsbsj,String lxdh,String bj,
			String yhm,String xn,String nd,String xq,String sqly,String bz) throws Exception{
		String sql = "insert into zgdzdx_yrdwghxsb (xh,gwdm,gwsbsj,lxdh,bj,yhm,"
			+ "xn,nd,xq,sqly,bz)values(?,?,?,?,?,?,?,?,?,?,?)";
		return dao.insert(sql, new String[]{xh,gwdm,gwsbsj,lxdh,bj,yhm,xn,nd,xq,sqly,bz});
	}
	
	/**������ʱ���е�ѧ����¼
	 * @throws Exception */
	public boolean deleteYrdwghxslsb(String userName) throws Exception{
		String sql = "delete zgdzdx_yrdwghxslsb where yhm=?";
		return dao.runUpdate(sql, new String[]{userName});
	}
	
	/**��ȡ���˵�λ����ѧ���������Ƿ����ظ�δ��˼�¼*/
	public String getYrdwghXs(String xh,String gwdm,String gwsbsj,String xn,String nd,String xq){
		String sql = "select count(*) num from zgdzdx_yrdwghxsb where xh=? "
			+"and gwdm=? and gwsbsj=? and xn=? and nd=? and xq=?";
		return dao.getOneRs(sql, new String[] {xh,gwdm,gwsbsj,xn,nd,xq}, "num");
	}
	
	/**��ȡ���˵�λ����ѧ�������б�*/
	public ArrayList<String[]> getYrdwGhXs(StringBuffer querry,String[] colList){
		String sql = "select xh ||'-'|| gwdm ||'-'|| gwsbsj ||'-'|| bj ����, rownum �к�,"
       +"a.*,(select xqmc from xqdzb b where a.xq = b.xqdm) xqmc "
       +"from view_zgdzdx_yrdwghxsxxb a" + querry.toString();
		return dao.rsToVator(sql, new String[] {}, colList);
	}
	
	/**��ȡ�������˵�λ����ѧ����ϸ��Ϣ*/
	public HashMap<String, String> getYrdwGhXsOne(String pkValue){
		String [] tmp = pkValue.split("-");
		String sql = "select * from view_zgdzdx_yrdwghxsxxb where xh=? and gwdm=? and gwsbsj=? and bj=?";
		return dao.getMapNotOut(sql, tmp);
	}
	
	/**���˵�λ����ѧ�����
	 * @throws Exception */
	public boolean yrdwGhxssh(String[] tmp,String type) throws Exception{
		String sql = "update zgdzdx_yrdwghxsb set xxyj = '"+type+"' where "
				+" xh=? and gwdm=? and gwsbsj=? and bj=?";
		return dao.runUpdate(sql, tmp);
	}
	
	/**������ڼ�¼
	 * @throws SQLException */
	public boolean insertYrdwDgjl(String xn,String xq,HashMap<String, String> gwmap,HashMap<String, String> hgmap) throws SQLException{
		String[] tmp = {xn,xq,hgmap.get("xh"),hgmap.get("sqsj"),hgmap.get("xn"),hgmap.get("xq"),hgmap.get("nd"),
				hgmap.get("gwdm"),hgmap.get("gwsbsj"),gwmap.get("xn"),gwmap.get("xq"),gwmap.get("nd"),
				gwmap.get("gwdm"),gwmap.get("gwsbsj")};
		String sql="insert into qgzx_gwtzb(xn,xq,xh,tzsj,tzhgzxn,tzhgzxq,tzhgznd,tzhgw,tzhgwsbsj,"
			+ "tzqgzxn,tzqgzxq,tzqgznd,tzqgw,tzqgwsbsj)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return dao.insert(sql, tmp);
	}
	
	/**���˵�λ����ѧ������ѧ����λ��Ϣ��
	 * ���˵�λ���ڣ�ѧ�������ͬ�⣬��ѧ����λ��Ϣ���е���Ϣ��Ϊ��ͨ��,ѧ������ͨ���ڹ���ѧ״̬
	 * @throws Exception */
	public boolean updateXsgwxxb(String xh,String gwdm,String gwsbsj) throws Exception{
		String[] tmp = {xh,gwdm,gwsbsj};
		String sql = "update xsgwxxb set xxyj='��ͨ��' where xh=? and gwdm=? and gwsbsj=?";
		return dao.runUpdate(sql, tmp);
	}
	
	/**������ڼ�¼
	 * @throws SQLException */
	public boolean insertYrdwDgjl2(String xn,String xq,HashMap<String, String> gwmap,HashMap<String, String> hgmap) throws SQLException{
		String[] tmp = {xn,xq,hgmap.get("xh"),hgmap.get("sqsj"),hgmap.get("xn"),hgmap.get("xq"),hgmap.get("nd"),
				"","",gwmap.get("xn"),gwmap.get("xq"),gwmap.get("nd"),
				gwmap.get("gwdm"),gwmap.get("gwsbsj")};
		String sql="insert into qgzx_gwtzb(xn,xq,xh,tzsj,tzhgzxn,tzhgzxq,tzhgznd,tzhgw,tzhgwsbsj,"
			+ "tzqgzxn,tzqgzxq,tzqgznd,tzqgw,tzqgwsbsj)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return dao.insert(sql, tmp);
	}
	
	/**����ͨ�������λ��Ϣ��
	 * @throws SQLException */
	public boolean insertXsgwxxb(HashMap<String, String> hgmap) throws SQLException{
		String[] tmp = {hgmap.get("xh"),hgmap.get("gwdm"),hgmap.get("sqsj"),hgmap.get("xxyj"),
				hgmap.get("xn"),hgmap.get("nd"),hgmap.get("xq"),hgmap.get("gwsbsj")};
		String sql = "insert into xsgwxxb(xh,gwdm,sqsj,xxyj,xn,nd,xq,gwsbsj) values(?,?,?,?,?,?,?,?)";
		return dao.insert(sql, tmp);
	}
	
	/**ѧ��������˺�������¼����Ϊ��ͨ��
	 * @throws Exception */
	public boolean yrdwHgsqshOther(String[] tmp,String userType,String type) throws Exception{
		String sql = "update zgdzdx_xshgxxb set "+userType+"yj = '"+type+"' where "
				+" xh=? and gwdm<>? and gwsbsj<>?";
		return dao.runUpdate(sql, new String[]{tmp[0],tmp[1],tmp[2]});
	}
	
	/**��ȡ�������˵�λ����ѧ����ϸ��Ϣ*/
	public HashMap<String, String> getYrdwGhXsOne(String xh,String gwdm,String gwsbsj,String bj){
		String sql = "select * from view_zgdzdx_yrdwghxsxxb where xh=? and gwdm=? and gwsbsj=? and bj=?";
		return dao.getMapNotOut(sql, new String[]{xh,gwdm,gwsbsj,bj});
	}
	
	/**���´Ǹ�ѧ�����ڹ���ѧ���е����״̬
	 * @throws Exception */
	public boolean updateQgzxsqb(String xh) throws Exception{
		String sql = "update qgzxsqb set xysh='��ͨ��',xxsh='��ͨ��' where xh=?";
		return dao.runUpdate(sql, new String[] {xh});
	}
	
	/**
	 * �ж��û��Ƿ������˵�λ�û�
	 * @param String userName
	 * @return boolean
	 * */
	public boolean isYrdw(String userName){
		String sql = "select count(*) count from yrdwdmb where dlm=?";
		String result = getOneRs(sql, new String[]{userName},"count");
		result = StringUtils.isNull(result) ? "0" : result; 
		return Integer.parseInt(result) > 0 ? true : false; 
	}
	
	/**
	 * ����¼�Ƿ����
	 * @param String tableName
	 * @param String pk
	 * @param String pkValue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue){
		String sql = "select count(*) num from " + tableName + " where " + pk + "=?";
		String num = getOneRs(sql, new String[]{pkValue}, "num");
		return Integer.parseInt(StringUtils.isNull(num) ? "0" : num)>0 ? true : false;
	}
	
	/**
	 * ��ѯѧ���ڵĸ�λ�б�
	 * @param String xh
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String,String>> selectXsgwList(String xh){
		String[] outputValue = {"gwdm","gwdmgwsbsj"};
		String sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_xsgwxx where xh=? and sfyx='��Ч'";
		return getList(sql, new String[]{xh}, outputValue);
	}
	
	/**����ѧ����λ��Ϣ��
	 * @throws Exception */
	public boolean updateXsgwxx(HashMap<String, String> map,String xh,String gwdm,String gwsbsj) throws Exception{
		
		String[]sqlArr=new String[2];
		
		sqlArr[0]=" delete from xsgwxxb where xh='"+xh+"' and gwdm='"+map.get("hgdm")+"' and gwsbsj='"+map.get("hgsj")+"' ";
		sqlArr[1]=" insert into xsgwxxb(xh,xyyj,xxyj,lxdh,xn,nd,xq,xssq,yhtc,gwdm,gwsbsj) values('"+map.get("xh")+"','"+map.get("xyyj")+"'," +
				"'"+map.get("xxyj")+"','"+map.get("lxdh")+"','"+map.get("xn")+"','"+map.get("nd")+"','"+map.get("xq")+"'," +
						" '"+map.get("sqly")+"','"+map.get("yhtc")+"','"+map.get("hgdm")+"','"+map.get("hgsj")+"') ";	
		int []bool=dao.runBatch(sqlArr);
		boolean blog=true;
		if(bool[1]==0){
			blog=false;
		}
		return blog;
	}
}
