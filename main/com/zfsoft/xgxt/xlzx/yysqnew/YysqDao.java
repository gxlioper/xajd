package com.zfsoft.xgxt.xlzx.yysqnew;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xlzx.zxsgly.ZxsglyDao;
import com.zfsoft.xgxt.xlzx.zxswh.ZxspbDao;
import com.zfsoft.xgxt.xlzx.zxswh.ZxspbService;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** 
 * ������ѯ����ģ��
 */

public class YysqDao extends SuperDAOImpl<YysqForm> {
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(YysqForm.class);
		super.setKey("id");
		super.setTableName("XG_XLZX_YYSQB");
		
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YysqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YysqForm t, User user)
			throws Exception {
		
		
		// TODO �Զ����ɷ������
		return null;
	}
	
	/** 
	 * ԤԼ��ѯ������ѯҳ��
	 */
	public List<HashMap<String, String>> queryYyfkList(YysqForm model,User user) throws Exception {

		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t.* from ( ");
		sql.append(" select a.*,a.yyqssj||'-'||a.yyjssj yyzxsd,a.zxqssj||'-'||a.zxjssj apzxsd, ");
		sql.append(" case when b.xh is null then '��' else '��' end sfxlwjgamc, ");
		sql.append(" case when b.xh is null then 'z' else 'a' end sfxlwjga "); // ���ó���ĸ,��Ϊ�˰�Σ������ѧ��������ǰ��
		if("10026".equals(Base.xxdm)){
			sql.append(" ,c.sjdmc sjddmzxmc ");
		}
		sql.append(" from view_new_dc_xlzx_yysq a ");
		sql.append(" left join (select * from view_xg_xljk_xlwjga_dgxs where wjgabz='1') b on a.xh=b.xh ");
		if("10026".equals(Base.xxdm)){
			sql.append(" left join XG_XLZX_SJDB c  on c.sjddm=a.sjddmzx ");
		}
		sql.append(" ) t where 1=1 ");
		sql.append(searchTj);
//		
//		boolean isZxs = isZxs(user.getUserName());
//		if(isZxs){
//			sql.append(" and t.zgh='"+user.getUserName()+"' ");
//		}
		sql.append(SearchService.getSearchQxfwForXlzxs(user.getUserName()));
//		
		return getPageList(model, sql.toString(), inputV);
	}
	/**
	 * ����ѯʦ
	 */
	public boolean isZxs(String zgh){
		String sql = " select count(1) num from xg_view_xlzx_zxsxx where datazt = '1' and status='1' and zgh=? ";
		String num = dao.getOneRs(sql, new String[]{ zgh }, "num");
		return !"0".equals(num);
	}
	/**
	 * ����ԤԼ��Ų�ѯԤԼ��ѯ����
	 */
	public HashMap<String,String> getYyzxDetail(String id) throws Exception {
		StringBuilder sql = new StringBuilder();
		String pbfs = new ZxspbService().getPbfs();
		sql.append(" select t.*,rownum r from (select a.id,a.xh,a.xsxb,a.xsxm,a.xsage,a.nj,a.xymc,a.zymc,a.bjmc,a.zgh,a.zxsxm,a.zxsxb,a.zxsage,a.zxszg,a.zxsjj,a.kjdrs,a.bmdm,a.bmmc,a.lxdh,a.address,a.yystatus,");
		sql.append(" a.status,a.statusmc,a.yysbyy,a.yyzxrq,a.yyzxzt,a.yyzxxq,a.qssj yyqssj,a.jssj yyjssj,a.xstell,a.bz yybz,b.zxrq,b.qssj zxqssj,b.jssj zxjssj,b.zxdz,b.xspjzt,b.xspj,b.zxsfk,b.zxtell,b.bz zxbz,");
		sql.append(" a.qxztzt,a.qxztjl,a.qxztyy,a.sczxhgb,a.zjzt,a.bczxwt,a.zxhzt,");
		sql.append(" a.qxztztmc,a.qxztjlmc,a.qxztyymc,a.sczxhgbmc,a.zjztmc,");
		sql.append(" b.id zxid,b.yyid zxyyid, ");
		sql.append(" b.xlcsjg,b.ywyw,b.zyzlls,b.zxcs,b.sfja,b.sczxsj,b.bczxnr,b.bcjjwt,b.zxgsfs,b.zxfknr, ");
		sql.append(" decode(b.ywyw,'1','��','��') ywywmc, ");
		sql.append(" decode(b.sfja,'1','��','��') sfjamc, ");
		sql.append(" (case  b.xspjzt when  '1' then '������' when '2' then '������' else '' end) pjztmc,b.status  zxzt,");
		sql.append(" (case  b.status when  '1' then '����ѯ' when '2' then '����ѯ' when '3' then 'δ��ѯ' else '' end) zxztmc");
		sql.append(" ,a.xn ");
		sql.append(" ,a.xqmc ");
		sql.append(" ,b.apzxs, c.xm apzxsxm,a.yyfs,a.yyfsmc ");
		if(StringUtils.isNotNull(pbfs) && "2".equals(pbfs)){
			sql.append(" ,x.sjdmc,a.sjddm,b.sjddm sjddmzx,y.sjdmc sjdmczx ");
		}
		sql.append(" from xg_view_xlzx_yysq a left join xg_xlzx_xlzxb b on a.id = b.yyid left join fdyxxb c on b.apzxs=c.zgh");
		if(StringUtils.isNotNull(pbfs) && "2".equals(pbfs)){
			sql.append(" left join xg_xlzx_sjdb x on a.sjddm = x.sjddm");
			sql.append(" left join xg_xlzx_sjdb y on b.sjddm = y.sjddm");
		}
		sql.append(" ) t where 1=1 and id = ?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	/**
	 * ��ѯ��ʷ��Ϣ
	 */
	public List<HashMap<String, String>> getZxlsxxList(String xh,String id) throws Exception {
		if(StringUtil.isNull(id)){
			id = "-1";
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,rownum r from (select a.id,a.xh,a.xsxb,a.xsxm,a.xsage,a.nj,a.xymc,a.zymc,a.bjmc,a.zgh,a.zxsxm,a.zxsxb,a.zxsage,a.zxszg,a.zxsjj,a.kjdrs,a.bmdm,a.bmmc,a.lxdh,a.address,a.yystatus,");
		sql.append(" a.status,a.statusmc,a.yysbyy,a.yyzxrq,a.yyzxzt,a.yyzxxq,a.qssj yyqssj,a.jssj yyjssj,a.xstell,a.bz yybz,b.zxrq,b.qssj zxqssj,b.jssj zxjssj,b.zxdz,b.xspjzt,b.xspj,b.zxsfk,b.zxtell,b.bz zxbz,");
		sql.append(" b.id zxid,b.yyid zxyyid, ");
		sql.append(" b.xlcsjg,b.ywyw,b.zyzlls,b.zxcs,b.sfja,b.sczxsj,b.bczxnr,b.bcjjwt,b.zxgsfs, ");
		sql.append(" decode(b.ywyw,'1','��','��') ywywmc, ");
		sql.append(" decode(b.sfja,'1','��','��') sfjamc, ");
		sql.append(" (case  b.xspjzt when  '1' then '������' when '2' then '������' else '' end) pjztmc,b.status  zxzt,");
		sql.append(" (case  b.status when  '1' then '����ѯ' when '2' then '����ѯ' when '3' then 'δ��ѯ' else '' end) zxztmc");
		sql.append(" ,a.xn ");
		sql.append(" ,a.xqmc ");
		sql.append(" ,b.apzxs, c.xm apzxsxm ");
		sql.append(" from xg_view_xlzx_yysq a left join xg_xlzx_xlzxb b on a.id = b.yyid left join fdyxxb c on b.apzxs=c.zgh) t where 1=1 and xh = ? and zxyyid <> ? and zxzt='2' order by zxrq desc ");
		
		return dao.getListNotOut(sql.toString(), new String[]{xh,id});
	}
	/**
	 * ������ѯʦ������ڲ�ѯԤԼ���������Ϣ
	 */
	public List<HashMap<String, String>> getYysqByZghAnddDate(String yyzxrq,String zgh){
		String[] inputValue = {yyzxrq,zgh};
		String[] outValue = {"xh","zgh","status","yyzxrq"};
		String pbfs = new ZxspbDao().getPbfs();
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh,zgh,status,yyzxrq from xg_xlzx_yysqb t where datazt='1' and (status='2'  ");
		if(StringUtils.isNotNull(pbfs) && ("2").equals(pbfs)){
			sql.append("or status = '1'");
		}
		sql.append(") and yyzxrq=? and zgh=? ");
		return dao.getList(sql.toString(),  inputValue, outValue);
	}
	
	//��������ԤԼ��ѯ��Ϣ��ѯҳ�棨��ҳ��
	public List<HashMap<String, String>> queryYyzxInfoList(YysqForm model,User user)
	throws Exception {
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		
		/*sql.append(" select t.*,rownum r from (select a.id,a.xh,a.xsxb,a.xsxm,a.xsage,a.nj,a.xymc,a.zymc,a.bjmc,a.zgh,a.zxsxm,a.zxsxb,a.zxsage,a.zxszg,a.zxsjj,a.kjdrs,a.bmdm,a.bmmc,a.lxdh,a.address,a.yystatus,");
		sql.append(" a.status,a.statusmc,a.yysbyy,a.yyzxrq,a.yyzxzt,a.yyzxxq,a.qssj yyqssj,a.jssj yyjssj,a.xstell,a.bz yybz,b.zxrq,b.qssj zxqssj,b.jssj zxjssj,b.zxdz,b.xspjzt,b.xspj,b.zxsfk,b.zxtell,b.bz zxbz,");
		sql.append(" (case  b.xspjzt when  '1' then '������' when '2' then '������' else '' end) pjztmc,b.status  zxzt,");
		sql.append(" (case  b.status when  '1' then '����ѯ' when '2' then '����ѯ' when '3' then 'δ��ѯ' else '' end) zxztmc");
		sql.append(" from xg_view_xlzx_yysq a left join xg_xlzx_xlzxb b on a.id = b.yyid ) t where 1=1 ");*/
		String pbfs = new ZxspbService().getPbfs();
		sql.append(" select t.* from ( ");
		
		
		sql.append(" select distinct a.*,a.yyqssj||'-'||a.yyjssj yyzxsd,a.zxqssj||'-'||a.zxjssj apzxsd, ");
		sql.append(" case when b.xh is null then '��' else '��' end sfxlwjgamc, ");
		sql.append(" case when b.xh is null then 'z' else 'a' end sfxlwjga,x.xqdm,"); // ���ó���ĸ,��Ϊ�˰�Σ������ѧ��������ǰ��
		sql.append(" (select xqmc from dm_zju_xq xq where x.xqdm = xq.dm) xqmc,");
		sql.append(" (select sjdmc from xg_xlzx_sjdb sjd where x.sjddm = sjd.sjddm) sjdmczx,");
		sql.append(" (select sjdmc from xg_xlzx_sjdb yysjd where y.sjddm = yysjd.sjddm) sjdmc,");
		sql.append(" decode(a.qdzt,'yqd','��ǩ��','wqd','δǩ��','qj','���','cd','�ٵ�',a.qdzt) qdztmc");
		sql.append(" from view_new_dc_xlzx_yysq a ");
		sql.append(" left join (select * from view_xg_xljk_xlwjga_dgxs where wjgabz='1') b on a.xh=b.xh ");
		sql.append(" left join xg_xlzx_zxspbb pb");
		sql.append(" on a.ZXRQ = pb.pbdate");
		sql.append(" left join xg_xlzx_zxspbsjb x");
		sql.append(" on  pb.id = x.pbid and a.sjddmzx =x.sjddm  and a.APZXS = x.zxs");
		sql.append(" left join xg_xlzx_zxspbsjb y");
		sql.append(" on pb.id = y.pbid and a.sjddm =y.sjddm  and a.ZGH =y.zxs ");
		sql.append(" ) t where 1=1 ");
		
		if(user.getUserStatus().equals("stu")){
			sql.append(" and xh='"+user.getUserName()+"'");
		}else if(!user.getUserStatus().equals("stu")){
			if(!new ZxsglyDao().isZxsGly(user.getUserName())){
				sql.append(" and apzxs='"+user.getUserName()+"'");
			}
		}
		//sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	//��������ԤԼ��ѯ��Ϣ��ѯҳ�棨��ҳ��
	public List<HashMap<String, String>> queryYyzxInfoListJg(YysqForm model,User user)
	throws Exception {
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		
		/*sql.append(" select t.*,rownum r from (select a.id,a.xh,a.xsxb,a.xsxm,a.xsage,a.nj,a.xymc,a.zymc,a.bjmc,a.zgh,a.zxsxm,a.zxsxb,a.zxsage,a.zxszg,a.zxsjj,a.kjdrs,a.bmdm,a.bmmc,a.lxdh,a.address,a.yystatus,");
		sql.append(" a.status,a.statusmc,a.yysbyy,a.yyzxrq,a.yyzxzt,a.yyzxxq,a.qssj yyqssj,a.jssj yyjssj,a.xstell,a.bz yybz,b.zxrq,b.qssj zxqssj,b.jssj zxjssj,b.zxdz,b.xspjzt,b.xspj,b.zxsfk,b.zxtell,b.bz zxbz,");
		sql.append(" (case  b.xspjzt when  '1' then '������' when '2' then '������' else '' end) pjztmc,b.status  zxzt,");
		sql.append(" (case  b.status when  '1' then '����ѯ' when '2' then '����ѯ' when '3' then 'δ��ѯ' else '' end) zxztmc");
		sql.append(" from xg_view_xlzx_yysq a left join xg_xlzx_xlzxb b on a.id = b.yyid ) t where 1=1 ");*/
		
		sql.append(" select t.* from ( ");
		sql.append(" select distinct a.*,a.yyqssj||'-'||a.yyjssj yyzxsd,a.zxqssj||'-'||a.zxjssj apzxsd, ");
		sql.append(" case when b.xh is null then '��' else '��' end sfxlwjgamc, ");
		sql.append(" case when b.xh is null then 'z' else 'a' end sfxlwjga,x.xqdm, "); // ���ó���ĸ,��Ϊ�˰�Σ������ѧ��������ǰ��
		sql.append(" (select xqmc from dm_zju_xq xq where x.xqdm = xq.dm) xqmc,");
		sql.append(" (select sjdmc from xg_xlzx_sjdb sjd where x.sjddm = sjd.sjddm) sjdmczx,");
		sql.append(" (select sjdmc from xg_xlzx_sjdb yysjd where y.sjddm = yysjd.sjddm) sjdmc");
		sql.append(" from view_new_dc_xlzx_yysq a ");
		sql.append(" left join (select * from view_xg_xljk_xlwjga_dgxs where wjgabz='1') b on a.xh=b.xh ");
		sql.append(" left join xg_xlzx_zxspbb pb");
		sql.append(" on a.ZXRQ = pb.pbdate");
		sql.append(" left join xg_xlzx_zxspbsjb x");
		sql.append(" on  pb.id = x.pbid and a.sjddmzx =x.sjddm  and a.APZXS = x.zxs");
		sql.append(" left join xg_xlzx_zxspbsjb y");
		sql.append(" on pb.id = y.pbid and a.sjddm =y.sjddm  and a.ZGH =y.zxs ");
		sql.append(" ) t where 1=1 and zxzt='2' ");
		
		if(user.getUserStatus().equals("stu")){
			sql.append(" and xh='"+user.getUserName()+"'");
		}else if(!user.getUserStatus().equals("stu")){
			//sql.append(" and apzxs='"+user.getUserName()+"'");
		}
		//sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	
	
	
	//ԤԼ������Ϣ����
	public HashMap<String,String> getYysqInfoById(String id)
	throws Exception {

		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * ");
		sql.append(" from xg_view_xlzx_yysq t where 1=1 and t.id = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	/**
	 * ����ID��ѯԤԼ���������Ϣ
	 */
	public HashMap<String,String> getYysqById(String id){
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select * from xg_xlzx_yysqb t where datazt='1' and id=? ");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	/**
	 * ��ѯԤԼ���������Ϣ
	 */
	public HashMap<String,String> getYysqByCn(String xh,String zgh,String yyzxrq){
		StringBuilder inputSbu=new StringBuilder();
		StringBuilder sql=new StringBuilder();
		sql.append(" select * from xg_xlzx_yysqb t where datazt='1' and t.status in ('1','2') ");
		if(!StringUtil.isNull(xh)){
			sql.append(" and xh=?");
			inputSbu.append(xh).append(",");
		}
		if(!StringUtil.isNull(zgh)){
			sql.append(" and zgh=?");
			inputSbu.append(zgh).append(",");
		}
		if(!StringUtil.isNull(yyzxrq)){
			sql.append(" and yyzxrq=?");
			inputSbu.append(yyzxrq).append(",");
		}
		//ʲôҲû�޸ģ���return
		if(inputSbu.lastIndexOf(",")==-1){
			return null;
		}
		//ȥ�����һ��","
		String inputStr = inputSbu.substring(0, inputSbu.lastIndexOf(","));
		String[] inputValue = inputStr.split(",");
		return dao.getMapNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * ����ѧ�Ų�ѯԤԼ���������Ϣ
	 */
	public HashMap<String,String> getYysqByXh(String xh){
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select * from xg_xlzx_yysqb t where datazt='1' ");
		if(!StringUtil.isNull(xh)){
			sql.append(" and xh=?");
		}
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * ����ѧ�����ڲ�ѯԤԼ���������Ϣ
	 */
	public HashMap<String,String> getYysqByXhAnddDate(String yyzxrq,String xh){
		String[] inputValue = new String[]{yyzxrq,xh};
		StringBuilder sql=new StringBuilder();
		sql.append(" select * from xg_xlzx_yysqb t where datazt='1' and t.status in ('1','2') and yyzxrq=? and xh=? ");
		return dao.getMapNotOut(sql.toString(), inputValue);
	}
	/**
	 * ����ѧ������id��ѯԤԼ���������Ϣ
	 */
	public HashMap<String,String> getYysqByXhAnddDateId(String yyzxrq,String xh,String id){
		String[] inputValue = new String[]{yyzxrq,xh,id};
		StringBuilder sql=new StringBuilder();
		StringBuilder sqlzcx = new StringBuilder();
		String pbfs = new ZxspbService().getPbfs();
		//�Ű෽ʽΪ2Ҳ����ʱ���ԤԼ�����Ի�����
		if(StringUtils.isNotNull(pbfs) && ("2").equals(pbfs)){
			sqlzcx.append(" (select t.ID,");
			sqlzcx.append(" t.XH,");
			sqlzcx.append(" decode(nvl(t.ZXRQ, '0'), '0', t.YYZXRQ, t.ZXRQ) yyzxrq,");
			sqlzcx.append(" decode(nvl(t.ZXRQ, '0'), '0', t.sjddm, t.sjddmzx) sjddm,");
			sqlzcx.append(" decode(nvl(t.ZXRQ,'0'), '0', t.ZGH, t.APZXS)zgh,");
			sqlzcx.append(" t.STATUS");
			sqlzcx.append(" from view_new_dc_xlzx_yysq t)");
			sqlzcx.append(" ");
		}else{
			sqlzcx.append(" xg_xlzx_yysqb");
		}
		sql.append(" select * from "+sqlzcx.toString()+" t where 1=1  ");
		if(StringUtils.isNull(pbfs) || ("1").equals(pbfs)){
			sql.append("  and datazt='1'");
		}
		sql.append("  and t.status in ('1','2') and yyzxrq=? and xh=? and id<>?");
		return dao.getMapNotOut(sql.toString(), inputValue);
	}
	
	
	
	/**
	 * ����ѧ�Ų�ѯѧ��������Ϣ
	 */
	public HashMap<String,String> getStuInfoByXh(String xh){
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select t.xh,t.xm,(case t.xb when '1' then'��' when '2' then 'Ů' else  t.xb end) xb,t.nj,t.jg,");
		sql.append(" (select c.qxmc from dmk_qx c where c.qxdm = substr(t.jg, 0, 2)||'0000') ||");
		sql.append(" (select d.qxmc from dmk_qx d where d.qxdm = substr(t.jg, 0, 4)||'00' and t.jg <> substr(t.jg, 0, 2)||'0000') ||");
		sql.append(" (select e.qxmc from dmk_qx e where e.qxdm = t.jg and t.jg <> substr(t.jg, 0, 2)||'0000' and t.jg <> substr(t.jg, 0, 4)||'00') jgmc,");
		sql.append(" (to_char(sysdate, 'yyyy') - substr(substr(t.sfzh,7,8), 0, 4)) age,t.xy,t.zymc,t.bjmc,t.bjdm,t.zydm,t.xydm,t.mz,(select mzmc from mzdmb c where t.mz = c.mzdm) mzmc,t.sjhm from view_xsjbxx t where t.xh=? ");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	
	/**
	 * ������ԤԼ�����
	 */
	public boolean saveYysqInfo(YysqForm model)
			throws Exception {
		String[] input = {model.getId(),model.getXh(),model.getXstell(),model.getZgh(),model.getYyzxzt(),model.getYyzxxq(),model.getStatus(),model.getYysbyy(),model.getYyzxrq(),model.getBz(),model.getQssj(),model.getJssj(),Base.currXn,Base.currXq};
		
		boolean flag = false;
		String sql = " insert into xg_xlzx_yysqb (id,xh,xstell,zgh,yyzxzt,yyzxxq,status,yysbyy,yyzxrq,createsj,bz,qssj,jssj,xn,xq) values(?,?,?,?,?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?,?)";
		flag = dao.insert(sql, input);
		return flag;
	}
	
	/**
	 * ɾ��ԤԼ������Ϣ
	 */
	public int delYysqByZgh(String[] zgh) throws Exception {
		if (zgh == null || zgh.length == 0){
			throw new NullPointerException();
		}
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_xlzx_yysqb");
		sql.append(" where  ");
		for (int i = 0 , n = zgh.length ; i < n ; i++){
			sql.append("zgh=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		return dao.runDelete(sql.toString(), zgh);
	}
	
	public boolean  updateYysqStatus(YysqForm model) throws Exception{
		
		HashMap<String,String>  yysqInfoList = this.getYysqById(model.getId());
		if(model.getId()==null || model.getId().equals("") || yysqInfoList==null || yysqInfoList.size()==0){
			return false;	
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" update xg_xlzx_yysqb set status=? where id=?");
		//ȥ�����һ��","
		boolean flag = dao.runUpdate(sql.toString(), new String []{model.getStatus(),model.getId()});
		return flag;
	}
	
	/** 
	 * �޸�ԤԼ�����
	 */
	public boolean updateYysqInfo(YysqForm model) throws Exception{
		StringBuffer inputS = new StringBuffer();
		HashMap<String,String>  yysqInfoList = this.getYysqById(model.getId());
		if(model.getId()==null || model.getId().equals("") || yysqInfoList==null || yysqInfoList.size()==0){
			return false;	
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" update xg_xlzx_yysqb set ");
		if(!StringUtil.isNull(model.getXstell())){
			sql.append(" xstell = ? ,");
			inputS.append(model.getXstell()+",");
		}
		if(!StringUtil.isNull(model.getYyzxzt())){
			sql.append(" yyzxzt = ? ,");
			inputS.append(model.getYyzxzt()+",");
		}
		if(!StringUtil.isNull(model.getYyzxxq())){
			sql.append(" yyzxxq = ? ,");
			inputS.append(model.getYyzxxq()+",");
		}
		if(!StringUtil.isNull(model.getYysbyy())){
			sql.append(" yysbyy =? ,");
			inputS.append(model.getYysbyy()+",");
		}
		if(!StringUtil.isNull(model.getYyzxrq())){
			sql.append(" yyzxrq = ? ,");
			inputS.append(model.getYyzxrq()+",");
		}
		if(!StringUtil.isNull(model.getQssj())){
			sql.append(" qssj = ? ,");
			inputS.append(model.getQssj()+",");
		}
		if(!StringUtil.isNull(model.getJssj())){
			sql.append(" jssj =? ,");
			inputS.append(model.getJssj()+",");
		}
		if(!StringUtil.isNull(model.getStatus())){
			sql.append(" status = ? ,");
			inputS.append(model.getStatus()+",");
		}
		if(!StringUtil.isNull(model.getBz())){
			sql.append(" bz = ? ,");
			inputS.append(model.getBz()+",");
		}
		if(!StringUtil.isNull(model.getDatazt())){
			sql.append(" datazt = ? ,");
			inputS.append(model.getDatazt()+",");
		}
//		if(!StringUtil.isNull(model.getZgh())){
//			sql.append(" zgh = ? ,");
//			inputS.append(model.getZgh()+",");
//		}

		//ʲôҲû�޸ģ���return
		if(sql.lastIndexOf(",")==-1){
			return false;
		}
		//ȥ�����һ��","
		String sqls = sql.substring(0, sql.lastIndexOf(","))+" where id = ?";
		String inputValues = inputS.toString()+model.getId();
		String[] inputValue = inputValues.split(",");
		boolean flag = dao.runUpdate(sqls, inputValue);
		return flag;
	}

	/**
	 * ɾ��ԤԼ����ʦɾ����
	 */
	public int delYyzxInfo(String[] values) throws Exception {
		if (values == null || values.length == 0){
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from ");
		sql.append(" xg_xlzx_yysqb ");
		sql.append(" where ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			
			sql.append(" id = ? ");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(") ");
		
		return dao.runDelete(sql.toString(), values);
	}

	/**
	 * ɾ����ѯ ��¼��
	 */
	public int delXlzxInfo(String[] values) throws Exception {
		if (values == null || values.length == 0){
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from ");
		sql.append(" xg_xlzx_xlzxb ");
		sql.append(" where ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			
			sql.append(" yyid = ? ");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(") ");
		
		return dao.runDelete(sql.toString(), values);
	}
	
	/**
	 * ����ѧ�����ڲ�ѯԤԼ���������Ϣ[��ʱ�����֤]
	 */
	public HashMap<String,String> getYysqByXhAnddDateForSjd(String yyzxrq,String xh,String id){
		List<String> paraList = new ArrayList<String>();
		paraList.add(yyzxrq);
		paraList.add(xh);
		StringBuilder sql=new StringBuilder();
		sql.append(" select * from (  ");
		sql.append(" select t.ID, t.XH, decode(nvl(t.ZXRQ, '0'), '0', t.YYZXRQ, t.ZXRQ) yyzxrq, t.STATUS");
		sql.append(" from view_new_dc_xlzx_yysq t");
		sql.append(" )t");
		sql.append("  where  t.status in ('1','2') and yyzxrq=? and xh=?");
		if(StringUtils.isNotNull(id)){
			sql.append(" and id != ?");
			paraList.add(id);
		}
		return dao.getMapNotOut(sql.toString(), paraList.toArray(new String[]{}));
	}

	/**
	 *  ��ѯ��ѯ���������б�.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-11-27 15:30
	 * @param
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw
	 */
	public List<HashMap<String,String>> getZxwtlxList() {
		String sql = "SELECT dm zxwtlxdm,mc zxwtlxmc FROM XG_XLJK_XLZXCL_ZXWTLXDMB order by dm";
		return dao.getListNotOut(sql,new String[]{});
	}

	public boolean updateQdzt(YysqForm model) throws Exception {
		String sql = "update xg_xlzx_yysqb set qdzt=? , qdztbz=? where id=?";
		return dao.runUpdate(sql,new String[]{model.getQdzt(),model.getQdztbz(),model.getId()});
	}

	public HashMap<String,String> getJzxx(String sqid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,decode(a.jzxb,'1','��','2','Ů',a.jzxb) jzxbmc, ");
		sql.append("decode(a.xssfzx,'1','��','0','��',a.xssfzx) xssfzxmc,");
		sql.append("decode(a.fdysfzx,'1','��','0','��',a.fdysfzx) fdysfzxmc");
		sql.append(" from  xg_xlzx_jzxxb a where a.sqid=?");
		return dao.getMapNotOut(sql.toString(),new String[]{sqid});
	}
}
