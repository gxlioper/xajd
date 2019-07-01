/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-20 ����03:15:51 
 */  
package xsgzgl.qgzx.zjdx.cjff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.jfgl.QgzxJfglService;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-12-20 ����03:15:51 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CjffDao extends SuperDAOImpl<CjffForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CjffForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CjffForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String searchTjByQggly = this.getQgglySjfwfByUser(user);
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t.*,");
		sql.append(" t1.xqmc,");
		sql.append(" decode(t.sftj, '1', '���ύ', 'δ�ύ') tjzt,");
		sql.append(" t2.gwxzmc,");
		sql.append(" t3.gwlbmc,");
		sql.append(" t4.bmmc yrdwmc,t4.bmdm xydm,");
		sql.append(" substr(ffndyf,0,4) nd,");
		sql.append(" substr(ffndyf,6,2) yf,");
		sql.append(" t5.xm lrrxm");
		sql.append(" from XG_QGZX_ZJDX_CJFFB t");
		sql.append(" left join dm_zju_xq t1");
		sql.append(" on t.xqdm = t1.dm");
		sql.append(" left join XG_QGZX_GWXZDMB t2");
		sql.append(" on t.gwxzdm = t2.gwxzdm");
		sql.append(" left join XG_QGZX_GWLBDMB t3");
		sql.append(" on t.gwlbdm = t3.gwlbdm");
		sql.append(" left join ZXBZ_XXBMDM t4");
		sql.append(" on t.yrdwdm = t4.bmdm");
		sql.append(" left join fdyxxb t5");
		sql.append(" on t.lrr = t5.zgh");
		sql.append(" )t where 1= 1  ");
//		sql.append(searchTjByUser);
		sql.append(searchTjByQggly);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(CjffForm.class);
		this.setKey("id");
		this.setTableName("XG_QGZX_ZJDX_CJFFB");
	}
	
	/**
	 * 
	 * @����: ���˵�λList
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����10:11:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYrdwList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select bmdm yrdwdm,bmmc yrdwmc from ZXBZ_XXBMDM t order by t.bmmc ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @����: ��𷢷ţ����˵�λ�����б����Ȩ�޲�ͬ���в�ͬ��չʾ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-1-13 ����09:15:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYrdwyList(User user){
		StringBuffer sql = new StringBuffer();
		QgzxJfglService service = new QgzxJfglService();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select bmdm yrdwdm,bmpy||bmmc yrdwmc from ( ");
		sql.append(" select bmdm,bmmc,F_PINYIN(substr(bmmc,0,1)) bmpy ");
		sql.append(" from zxbz_xxbmdm ) ");
		//��������ڹ�����Ա
		if(!service.sfQggly(user.getUserName())){
			sql.append(" where bmdm = ? ");
			paraList.add(user.getUserDep());
		}
		sql.append(" order by bmmc ");
		return dao.getListNotOut(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����:��λ����List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����10:14:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGwxzList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from XG_QGZX_GWXZDMB order by gwxzmc ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����: ��λ���List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����10:15:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGwlbList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from XG_QGZX_GWLBDMB order by gwlbmc ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 *
	 * @����:У��List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����10:16:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXqList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select dm xqdm,xqmc from dm_zju_xq order by xqmc ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����: ����������Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����10:47:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getCsszMap(){
		StringBuffer sql =  new StringBuffer();
		sql.append(" select * from xg_qgzx_csszb ");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����: ��ȡ�·�List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����11:38:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYfList(String ksyf,String jsyf){
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT TO_CHAR(ADD_MONTHS(TO_DATE(?,'YYYY-MM'),ROWNUM-1 ");
		sql.append(" ),'YYYY-MM') yf ");
		sql.append(" FROM DUAL ");
		sql.append(" CONNECT BY ROWNUM<=months_between ");
		sql.append(" (to_date(?, 'yyyy-mm'),to_date(?, 'yyyy-mm'))+1");
		sql.append(" ");
		sql.append(" ");
		return dao.getListNotOut(sql.toString(), new String[]{ksyf,jsyf,ksyf});
	}
	
	/**
	 * 
	 * @����: ��֤�Ƿ��ڳ�𷢷ſ���ʱ�����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����02:22:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsInKfsjd(int kssj,int jssj){
		//�Ȼ�ȡ��ǰ����
		int nowtime = Integer.parseInt( GetTime.getTimeByFormat("dd"));
		if(nowtime >= kssj && nowtime <= jssj){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * @����: ��֤�����Ƿ����ظ���Ψһ��(xh,ffndyf,yrdwdm)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����04:21:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param ffndyf
	 * @param yrdwdm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotExists(String xh,String ffndyf,String yrdwdm,String id){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) num from XG_QGZX_ZJDX_CJFFB where xh = ? and ffndyf = ? and yrdwdm = ?");
		paraList.add(xh);
		paraList.add(ffndyf);
		paraList.add(yrdwdm);
		if(StringUtils.isNotNull(id)){
			sql.append(" and id != ?");
			paraList.add(id);
		}
		String num = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "num");
		return (num).equals("0") ? true : false;
	}
	
	/**
	 * 
	 * @����: ��֤ѧ�ţ������Ƿ���ȷ
	 * ���ѧ�Ŵ���view_xsxxb�У�������֤view_xsxxb�е������������xm�Ƿ����;��������ڣ����õ���xmΪ�գ�ֱ�ӷ���true
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����04:43:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkXhXmIsTrue(String xh,String xm){
		StringBuffer sql = new StringBuffer();
		sql.append(" select xm from view_xsxxb where xh = ? ");
		String xmstr = dao.getOneRs(sql.toString(), new String[]{xh}, "xm");
		if(StringUtils.isNull(xmstr)){
			return true;
		}else{
			if(xm.trim().equals(xmstr)){
				return true;
			}else{
				return false;
			}
		}
	}
	
	/**
	 * 
	 * @����: ��ȡѧ��������Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����04:56:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXsxxck(String id){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.xh,");
		sql.append(" t.xm,");
		sql.append(" t1.xb,");
		sql.append(" t1.SFZH,");
		sql.append(" t1.NJ,");
		sql.append(" t1.XYMC,");
		sql.append(" t1.ZYMC,");
		sql.append(" t1.BJMC,");
		sql.append(" t1.ZZMMMC,");
		sql.append(" t1.SJHM ");
		sql.append(" from XG_QGZX_ZJDX_CJFFB t");
		sql.append(" left join view_xsxxb t1");
		sql.append(" on t.xh = t1.XH");
		sql.append(" where t.id = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	/**
	 * 
	 * @����: ҵ�����Ϣ�鿴
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����05:44:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getYwbdxxCk(String id){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append(" select t.*,");
		sql.append(" t1.xqmc,");
		sql.append(" decode(t.sftj, '1', '���ύ', 'δ�ύ') tjzt,");
		sql.append(" t2.gwxzmc,");
		sql.append(" t3.gwlbmc,");
		sql.append(" t4.bmmc yrdwmc,");
		sql.append(" substr(ffndyf,0,4) nd,");
		sql.append("  substr(ffndyf,6,2) yf");
		sql.append(" from XG_QGZX_ZJDX_CJFFB t");
		sql.append(" left join dm_zju_xq t1");
		sql.append(" on t.xqdm = t1.dm");
		sql.append(" left join XG_QGZX_GWXZDMB t2");
		sql.append(" on t.gwxzdm = t2.gwxzdm");
		sql.append(" left join XG_QGZX_GWLBDMB t3");
		sql.append(" on t.gwlbdm = t3.gwlbdm");
		sql.append(" left join ZXBZ_XXBMDM t4");
		sql.append(" on t.yrdwdm = t4.bmdm");
		sql.append(" )t where  id = ?  ");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	/**
	 * @throws Exception 
	 *
	 * @����: �ύ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-22 ����09:27:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean submit(String[] id) throws Exception{
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append(" update XG_QGZX_ZJDX_CJFFB set sftj = ? where id in (");
		paraList.add("1");
		for (int i = 0; i < id.length; i++) {
			sql.append("?");
			if(i != id.length-1){
				sql.append(",");
			}
			paraList.add(id[i]);
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(),paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����: ���˵�λ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-23 ����02:43:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yrdwdm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public String getYrdwdm(String yrdwmc,User user){
		StringBuffer sql = new StringBuffer();
		sql.append(" select bmdm from ZXBZ_XXBMDM where bmmc = ? ");
		if( user != null){
			sql.append(this.getQgglySjfwfByUserBydr(user));
		}
		return dao.getOneRs(sql.toString(), new String[]{yrdwmc}, "bmdm");
	}
	
	/**
	 * 
	 * @����: ��ȡУ������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-23 ����02:48:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xqmc
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXqdm(String xqmc){
		StringBuffer sql = new StringBuffer();
		sql.append(" select dm xqdm from dm_zju_xq where xqmc = ? ");
		return dao.getOneRs(sql.toString(), new String[]{xqmc}, "xqdm");
	}
	
	/**
	 * 
	 * @����: ��λ������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-23 ����02:52:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xqmc
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getGwlbdm(String gwlbmc){
		StringBuffer sql = new StringBuffer();
		sql.append(" select gwlbdm from XG_QGZX_GWLBDMB where gwlbmc = ? ");
		return dao.getOneRs(sql.toString(), new String[]{gwlbmc}, "gwlbdm");
	}
	
	/**
	 * 
	 * @����: ��λ���ʴ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-23 ����02:52:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xqmc
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getGwxzdm(String gwxzmc){
		StringBuffer sql = new StringBuffer();
		sql.append(" select gwxzdm from Xg_Qgzx_Gwxzdmb where gwxzmc = ? ");
		return dao.getOneRs(sql.toString(), new String[]{gwxzmc}, "gwxzdm");
	}
	
	/**
	 * 
	 * @����:�������浼������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-23 ����04:52:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paralist
	 * @return
	 * @throws Exception
	 * int[] �������� 
	 * @throws
	 */
	public int[] saveDrDataIntoDb(List<String[]> paralist) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into XG_QGZX_ZJDX_CJFFB( ");
		sql.append(" xh, ");
		sql.append(" xm,");
		sql.append(" ffndyf,");
		sql.append(" yrdwdm,");
		sql.append(" xqdm,");
		sql.append(" gwlbdm,");
		sql.append(" gwxzdm,");
		sql.append(" gss,");
		sql.append(" bcje,");
		sql.append(" gznr,");
		sql.append(" bz,");
		sql.append(" sftj,");
		sql.append(" lrr,");
		sql.append(" lrsj");
		sql.append(" )values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
 		return dao.runBatch(sql.toString(), paralist);
 		
	}
	
	/**
	 * 
	 * @����: ���ѻ�������֤
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-6 ����10:13:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param nd
	 * @param yrdwdm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public HashMap<String, String> checkIsFhJfhb(String nd,String yrdwdm,String bcje,String id){
		if(nd.length() >= 7){
			nd = nd.substring(0, 4);
		}
		List<String> paraList = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.yrdwdm, t.nd, (nvl(t.hbje,0) - nvl(t1.bcje,0)) syje");
		sql.append(" from (select sum(hbje) hbje, nd, yrdwdm");
		sql.append(" from xg_qgzx_jfhbb");
		sql.append(" where nd = ? ");
		paraList.add(nd);
		
		
		sql.append(" group by nd, yrdwdm) t");
		sql.append(" left join (select ffnd, sum(bcje) bcje, yrdwdm");
		sql.append(" from (select substr(ffndyf, 0, 4) ffnd, bcje, yrdwdm");
		sql.append(" from xg_qgzx_zjdx_cjffb");
		sql.append(" where ffndyf like ?");
		paraList.add("%"+nd+"-%");
		if(StringUtils.isNotNull(id)){
			sql.append(" and id != ?");
			paraList.add(id);
		}
		sql.append(" ) ");
		sql.append(" group by ffnd, yrdwdm) t1");
		sql.append(" on t.yrdwdm = t1.yrdwdm");
		sql.append(" where t.yrdwdm = ?");
		paraList.add(yrdwdm);
		String syje = "0";
		HashMap<String,String> dataMap = dao.getMapNotOut(sql.toString(), paraList.toArray(new String[]{}));
		if(!dataMap.isEmpty() && StringUtils.isNotNull(dataMap.get("syje"))){
			syje = dataMap.get("syje");
		}
		boolean rs = (Float.parseFloat(syje) >= Float.parseFloat(bcje)) ? true :false;
		dataMap.put("rs", rs+"");
		dataMap.put("syje", syje);
		return dataMap;
	}
	
	/**
	 * 
	 * @����: ���ѻ�������֤����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-6 ����10:13:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param nd
	 * @param yrdwdm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public HashMap<String, String> checkIsFhJfhbDr(String nd,String yrdwmc,String bcje){
		if(nd.length() >= 7){
			nd = nd.substring(0, 4);
		}
		
		List<String> paraList = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.yrdwdm, t.nd, (t.hbje - nvl(t1.bcje,0)) syje");
		sql.append(" from (select sum(hbje) hbje, nd, yrdwdm");
		sql.append(" from xg_qgzx_jfhbb");
		sql.append(" where nd = ? ");
		paraList.add(nd);
		sql.append(" group by nd, yrdwdm) t");
		sql.append(" left join (select ffnd, sum(bcje) bcje, yrdwdm");
		sql.append(" from (select substr(ffndyf, 0, 4) ffnd, bcje, yrdwdm");
		sql.append(" from xg_qgzx_zjdx_cjffb");
		sql.append(" where ffndyf like ?)");
		paraList.add("%"+nd+"-%");
		sql.append(" group by ffnd, yrdwdm) t1");
		sql.append(" on t.yrdwdm = t1.yrdwdm");
		sql.append(" where t.yrdwdm = (select bmdm from ZXBZ_XXBMDM where bmmc = ?)");
		paraList.add(yrdwmc);
		
		String syje = "0";
		HashMap<String,String> dataMap = dao.getMapNotOut(sql.toString(), paraList.toArray(new String[]{}));
		if(!dataMap.isEmpty()){
			syje = dataMap.get("syje");
		}
		boolean rs = (Float.parseFloat(syje) >= Float.parseFloat(bcje)) ? true :false;
		dataMap.put("rs", rs+"");
		dataMap.put("syje", syje);
		return dataMap;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ȡ���ύ��¼
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-6 ����04:54:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelTjjl(String[] ids) throws Exception{
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append(" update XG_QGZX_ZJDX_CJFFB set SFTJ = '0' where id in (");
		for (int i = 0; i < ids.length; i++) {
			sql.append("?");
			if(i != ids.length-1){
				sql.append(",");
			}
			paraList.add(ids[i]);
		}
		sql.append(") ");
		return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����: ��ȡ�����ܽ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-9 ����09:04:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param nd
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getSumHbje(String nd,	User user){
		StringBuffer sql = new StringBuffer();
		sql.append("  select  sum(hbje) hbje from  xg_qgzx_jfhbb  where nd  = ? ");
		sql.append(this.getQgglySjfwfByUser(user));
		String num = dao.getOneRs(sql.toString(), new String[]{nd}, "hbje");	
		return StringUtils.isNotNull(num) ? num : "0";
	}
	
	/**
	 * 
	 * @����: ��ȡ���ύ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-9 ����09:12:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param nd
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getYtjWtjje(String nd,String sftj,User user){	
		StringBuffer sql = new StringBuffer();
		sql.append("  select  sum(bcje) bcje  from xg_qgzx_zjdx_cjffb  where sftj = ? and substr(ffndyf,0,4) = ? ");
		sql.append(this.getQgglySjfwfByUser(user));
		String num = dao.getOneRs(sql.toString(), new String[]{sftj,nd}, "bcje");
		return StringUtils.isNotNull(num) ? num : "0";
	}
	
	/**
	 * 
	 * @����: �ڹ�����Ա���ݷ�Χ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-13 ����02:01:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getQgglySjfwfByUser(User user){
		QgzxJfglService service = new QgzxJfglService();
		StringBuffer sql = new StringBuffer();
		if(service.sfQggly(user.getUserName())){
			sql.append(" and (1=1)");
		}else{
			sql.append(" and  yrdwdm = '"+user.getUserDep()+"'");
		}
		return sql.toString();
	}
	
	/**
	 * 
	 * @����: �ڹ�����Ա���ݷ�Χ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-13 ����02:01:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getQgglySjfwfByUserBydr(User user){
		QgzxJfglService service = new QgzxJfglService();
		StringBuffer sql = new StringBuffer();
		if(service.sfQggly(user.getUserName())){
			sql.append(" and (1=1)");
		}else{
			sql.append(" and  bmdm = '"+user.getUserDep()+"'");
		}
		return sql.toString();
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ����δ�ύ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-13 ����05:02:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateWtjsj() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" update XG_QGZX_ZJDX_CJFFB set  sftj = '1' where sftj = '0' ");
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����: ��֤ѧ�ţ������Ƿ���ȷ
	 * ���ѧ�Ŵ���view_xsxxb�У�������֤view_xsxxb�е������������xm�Ƿ����;��������ڣ����õ���xmΪ�գ�ֱ�ӷ���true
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����04:43:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkXhXmIsTrueDr(String xh,String xm){
		StringBuffer sql = new StringBuffer();
		sql.append(" select xm from view_xsxxb where xh = ? ");
		String xmstr = dao.getOneRs(sql.toString(), new String[]{xh}, "xm");
		if(StringUtils.isNull(xmstr)){
			return false;
		}else{
			if(xm.equals(xmstr)){
				return true;
			}else{
				return false;
			}
		}
	}
	
}
