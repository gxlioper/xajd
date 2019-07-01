/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-25 ����03:26:51 
 */  
package com.zfsoft.xgxt.zxdk.xyddk;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��ѧ����-У԰�ش���
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-9-25 ����03:26:51 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XyddkDao extends SuperDAOImpl<XyddkModel> {

	private static final String YSH = "ysh";
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	protected void setTableInfo() {
		super.setClass(XyddkModel.class);
		super.setKey("id");
		super.setTableName("xg_zxdk_xydksqb");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	public List<HashMap<String, String>> getPageList(XyddkModel t)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	public List<HashMap<String, String>> getPageList(XyddkModel t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append(" select t1.*,t2.nj,t2.xm,t2.sfzh,t2.xz,t2.sjhm,t2.xmsj sjdh,t2.dzyx,t3.xydm,t3.xymc,t3.zydm,t3.zymc,t2.zybj,t3.bjmc zybjmc,t4.bjdm,t4.bjmc,t6.sydm,t6.symc, ");
		sql.append(" decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t1.shzt) shztmc ");
		sql.append(" from xg_zxdk_xydksqb t1 ");
		sql.append(" left join xsxxb t2 on t1.xh = t2.xh left join view_njxyzybj_all t3 on t3.bjdm = t2.zybj ");
		sql.append(" left join view_njxyzybj_all t4 on t2.bjdm = t4.bjdm ");
		sql.append(" left join XG_XTWH_SYBJGLB t5 on t2.bjdm = t5.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t6 on t5.sydm = t6.sydm ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
	
	/**
	 * 
	 * @����: ��˲�ѯ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-28 ����08:54:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getAudingList(XyddkModel t, User user)
			throws Exception {
		
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");	
 		String shgwzByUser = SearchService.getShgwzByUser(user, "t2","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t2.* from (select t1.*,");
		sql.append("row_number() over(partition by id order by shsj desc) rn ");
		sql.append("from (select b.*,'[' || c.mc || ':' || ");
		sql.append("decode(b.lczt,'0','�����','1','ͨ��','2','��ͨ��','3','�˻�','4','������','5','�����',b.lczt) || ']' shztmc,");
		sql.append("c.gwz from (select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zybj,t2.zybjmc,t2.sydm1 sydm,t2.symc1 symc,");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj,b.shzt as lczt,");
		sql.append("b.shsj,b.gwid as xtgwid,b.zd2 as rddc,b.guid as shid ");
		sql.append("from xg_zxdk_xydksqb t1 ");
		sql.append("left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join xg_xtwh_shztb b on t1.id = b.ywid ");
		sql.append(") b left join xg_xtwh_spgw c on b.xtgwid = c.id where b.xtgwid in");
		sql.append("(select spgw from xg_xtwh_spgwyh where spyh = '").append(user.getUserName()).append("')  ");
		
		if (YSH.equals(t.getShzt())){
			sql.append("and b.lczt not in ('0', '4')) t1 ) t2 where rn = 1 ");
		} else{
			sql.append(" and b.lczt in ('0', '4')) t1) t2 where rn = 1 ");
		}
		
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(shgwzByUser);
		
		return super.getPageList(t, sql.toString(), inputV);
	}
	

	/** 
	 * @����:����ID��ѯ��ѧ������Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-12-25 ����03:08:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap �������� 
	 * @throws 
	 */
	public HashMap<String, String> getDkxxSq(String id) {
		
		String sql = "select * from xg_zxdk_xydksqb where id = ? ";
		
		return dao.getMapNotOut(sql, new String[]{id});
		
	}
	
	
	
	/**
	 * 
	 * @����: ��ѧ�š�ѧ���ѯ��¼����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2015-3-23 ����05:10:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCountByXhAndXn(XyddkModel t){
		
		StringBuilder sql = new StringBuilder("select count(1) c from xg_zxdk_xydksqb where xh = ? ");
		List<String> params  = new ArrayList<String>();
		params.add(t.getXh());
		if(!StringUtil.isNull(t.getXn())){
			sql.append(" and xn = ?");
			params.add(t.getXn());
		}
		
		if (!StringUtil.isNull(t.getId())){
			sql.append(" and id <> ?");
			params.add(t.getId());
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "c");
	}
	
	
	/**
	 * 
	 * @����:��ѯѧ���Ѵ�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-11-26 ����03:48:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getYdkqx(String xh){
		
		String sql = "select sum(dkqx) dkqx from xg_zxdk_xydksqb where xh = ? ";
		
		return dao.getOneRs(sql, new String[]{}, "dkqx");
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: 
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-15 ����11:42:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * booleanf �������� 
	 * @throws
	 */
	public boolean delBeforeShtg(XyddkModel model) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_zxdk_xydkjgb where xh = ? and xn = ?");
		return dao.runUpdate(sql.toString(), new String[]{model.getXh(),model.getXn()});
	}
	
	/**
	 * 
	 * @����: ��ѯ�ϼ�������Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-22 ����03:07:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @param gwid
	 * @param splc
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public  HashMap<String, String> getShlcInfo(String sqid,String gwid,String splc) {
		String sjgw = this.getUpSpgw(splc, gwid);
		StringBuilder sql = new StringBuilder();
		//sql.append("  select b.mc,c.xm shr,a.shsj,a.shzt,a.shyj,a.gwid,a.guid from xg_xtwh_shztb a left join xg_xtwh_spgw b  on a.gwid = b.id left join yhb c on a.shr=c.yhm where a.ywid = ?  order by a.shsj  ");
		sql.append("  select * from xg_xtwh_shztb where ywid = ? and gwid = ? ");
		return DAO.getInstance().getMapNotOut(sql.toString(), new String[] { sqid,sjgw});
	}
	
	/**
	 * 
	 * @����: ��ȡ��ǰ��˸�λ�ϼ���λ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-18 ����03:15:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splc
	 * @param gwid
	 * @return
	 * String �������� 
	 */
	public  String getUpSpgw(String splc,String gwid){
		String xh=ShlcUtil.getGwSpXh(splc, gwid);
		if(StringUtils.isNull(xh)){
			return null;
		}
		Integer xhI=Integer.parseInt(xh)-1;
		StringBuffer sb=new StringBuffer();
		sb.append("select spgw from xg_xtwh_spbz where splc=? and xh=?");
		return DAO.getInstance().getOneRs(sb.toString(), new String[]{splc,xhI.toString()}, "spgw");
	}
	
	/**
	 * 
	 * @����: ��ȡѧ����Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-8 ����04:36:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXsxxByHsd(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select xz,nj,t1.jesx from view_xsxxb t");
		sql.append(" left join XG_ZXDK_DKSXB t1");
		sql.append(" on t.PYCC = t1.xlccdm ");
		sql.append(" where t.xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @����: �Ƿ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-10 ����11:19:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public boolean  getWjmSameRs(String xh,String gid){
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) num ");
		sql.append(" from XG_COMM_FILEUPLOAD_DATA t");
		sql.append(" where t.gid in (select filepath");
		sql.append(" from xg_zxdk_xydksqb");
		sql.append(" where filepath is not null");
		sql.append(" and xh = ?) and t.ORIGINALNAME in(");
		sql.append(" select ORIGINALNAME from  XG_COMM_FILEUPLOAD_DATA where gid = ?");
		sql.append(" )");
		String count = dao.getOneRs(sql.toString(), new String[]{xh,gid}, "num");
		return ( StringUtils.isNotNull(count) && !("0").equals(count) ) ? true : false;
	}
	
	/**
	 * 
	 * @����: �����Ƿ������ͬѧ����ظ���¼
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-10 ����02:10:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xns
	 * @param xh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean getXnXhSameRs(String[] xns,String xh,String id){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) num ");
		sql.append(" from xg_hsd_zxdk_ndkb " );
		sql.append(" where xh = ?");
		paraList.add(xh);
		sql.append(" and xn in(");
		for (int i = 0; i < xns.length; i++) {
			sql.append("?");
			if(i != xns.length -1){
				sql.append(",");
			}
			paraList.add(xns[i]);
			
		}
		sql.append(" )");
		/**
		 * �ж�id�Ƿ�Ϊ�գ�id��Ϊ�վ����޸ģ������ų�����
		 */
		if(StringUtils.isNotNull(id)){
			sql.append(" and id != ?");
			paraList.add(id);
		}
		String count = dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}), "num");
		return ( StringUtils.isNotNull(count) && !("0").equals(count) ) ? true : false;
	}
	
	/**
	 * 
	 * @����: ��������xg_hsd_zxdk_ndkb
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-10 ����03:40:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param parameter
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveRsBatch(List<String[]> parameter){
		StringBuffer sql = new StringBuffer();
		boolean flag = true;
		sql.append(" insert into xg_hsd_zxdk_ndkb(id,xh,xn,nzsfdk,nxfdk,nshfdk,nzsfyje,nxfyje,dkzt)");
		sql.append(" values(?,?,?,?,?,?,?,?,?)");
		try {
			int[] rs = dao.runBatch(sql.toString(), parameter);
			for (int i = 0; i < rs.length; i++) {
				flag = (rs[i] == Statement.EXECUTE_FAILED) ? false : true;
				if (!flag)
					break;
			}
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			return false;
		}
		return flag;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ����ɾ��xg_hsd_zxdk_ndkb�еļ�¼���޸�ʱʹ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-11 ����10:13:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delRs(String id) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from xg_hsd_zxdk_ndkb where id = ?");
		return dao.runUpdate(sql.toString(), new String[]{id});
	}
	
	/**
	 * 
	 * @����: �޸�ʱ�ж��Ƿ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-10 ����11:19:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public boolean  getWjmSameRsUpdate(String xh,String gid){
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) num ");
		sql.append(" from XG_COMM_FILEUPLOAD_DATA t");
		sql.append(" where t.gid in (select filepath");
		sql.append(" from xg_zxdk_xydksqb");
		sql.append(" where filepath is not null and filepath != ? ");
		sql.append(" and xh = ?) and t.ORIGINALNAME in(");
		sql.append(" select ORIGINALNAME from  XG_COMM_FILEUPLOAD_DATA where gid = ?");
		sql.append(" )");
		String count = dao.getOneRs(sql.toString(), new String[]{gid,xh,gid}, "num");
		return ( StringUtils.isNotNull(count) && !("0").equals(count) ) ? true : false;
	}
	
	/**
	 * 
	 * @����: ����id���Ҹü�¼��Ӧ�Ĵ������¼
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-11 ����05:19:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getNdkbList(String id){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from  xg_hsd_zxdk_ndkb where id = ?");
		return dao.getListNotOut(sql.toString(), new String[]{id});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ��ʦ�����idɾ��������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-14 ����09:51:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delNdkb(String[] ids) throws Exception{
		StringBuffer sql = new StringBuffer();
		ArrayList<String> paraList = new ArrayList<String>();
		sql.append(" delete from xg_hsd_zxdk_ndkb where id in(");
		for (int i = 0; i < ids.length; i++) {
			sql.append("?");
			paraList.add(ids[i]);
			if(i != ids.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����: ɾ���Ŵ�������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-15 ����06:59:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delFdb(String[] ids) throws Exception{
		StringBuffer sql = new StringBuffer();
		ArrayList<String> paraList = new ArrayList<String>();
		sql.append(" delete from xg_zxdk_hsd_xydfdb where id in ( select jgid from  xg_hsd_zxdk_ndkb where id in(");
		for (int i = 0; i < ids.length; i++) {
			sql.append("?");
			paraList.add(ids[i]);
			if(i != ids.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		
		sql.append(")");
		return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����: ���������ļ����Ƿ�����ͬ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-14 ����03:04:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public boolean checkWjmIsSame(String gid){
		StringBuffer sql = new StringBuffer();
		sql.append("  select ORIGINALNAME from  XG_COMM_FILEUPLOAD_DATA where gid = ?");
		List<HashMap<String, String>> fileNameList =  dao.getListNotOut(sql.toString(), new String[]{gid});
		boolean flag = true;
		for (int i = 0; i < fileNameList.size()-1; i++) {
			for (int j = i+1; j < fileNameList.size(); j++) {
				if(fileNameList.get(i).get("originalname").equals(fileNameList.get(j).get("originalname"))){
					flag = false;
					break;
				}
			}
			if(flag){
				break;
			}
		}
		return flag;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ����Ŵ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-15 ����07:09:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean InsertIntoFdb(String id) throws Exception{
		StringBuffer sql = new StringBuffer();
		String htbh = new DkjgDao().getModel(id).getHtbh();
		sql.append(" delete from xg_zxdk_hsd_xydfdb where id = (select jgid from XG_HSD_ZXDK_NDKB t where id = ?  and dkzt = 'xind') ");
		dao.runUpdate(sql.toString(),new String[]{id});
		sql = new StringBuffer();
		sql.append(" insert into xg_zxdk_hsd_xydfdb(id,xh,xn,xq,htbh)");
		sql.append(" select jgid id,xh,xn,?, '"+htbh+"' from XG_HSD_ZXDK_NDKB t where id = ?  and dkzt = 'xind'");
		return dao.runUpdate(sql.toString(), new String[]{Base.currXq,id});
	}
	
	/**
	 * 
	 * @����: ��֤��ѧ���Ƿ������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-5-31 ����04:42:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIfKsqCurrXn(String xn,String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("  select count(1) cnt  from xg_zxdk_xydksqb where xn = ? and xh = ? ");
		String cnt = dao.getOneRs(sql.toString(), new String[]{xn,xh}, "cnt");
		return "0".equals(cnt) ? true : false; 
		
	}
}
