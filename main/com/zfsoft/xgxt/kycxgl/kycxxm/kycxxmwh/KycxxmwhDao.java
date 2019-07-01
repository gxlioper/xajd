package com.zfsoft.xgxt.kycxgl.kycxxm.kycxxmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;

import xgxt.form.User;

/**
 * 科研创新项目类别
 */
public class KycxxmwhDao extends SuperDAOImpl<KycxxmwhForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(KycxxmwhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(KycxxmwhForm t, User user)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();

		sql.append(" select rownum r,d.lbdm,d.lbmc,d.sqkssj,d.sqjssj,d.sqkg,");
		sql.append(" decode(e.lcxx, '', '无需审核', mc || '：' || replace(lcxx, ';', '->')) lcxx ");
		sql.append(" from xg_kycxgl_kycxxmlb d left join (select splc, mc, lcxx ");
		sql.append(" from (select splc,a.mc,to_char(WM_CONCAT(c.mc) over (partition by splc order by xh)) lcxx, xh,row_number() over(partition by splc order by xh desc ) as ddd ");
		sql.append(" from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c where djlx = 'kycxgl' and a.id = b.splc ");
		sql.append(" and b.spgw = c.id) b where ddd = 1 ) e on d.splc = e.splc where 1 = 1 ");
	
//		if (!StringUtil.isNull(t.getLbdm())) {
//			params.add(t.getLbdm());
//			sql.append(" and d.lbdm = ? ");
//		}
		if (!StringUtil.isNull(t.getLbmc())) {
			params.add(t.getLbmc());
			sql.append(" and d.lbmc like '%'||?||'%'");
		}
		return getPageList(t, sql.toString(), params.toArray(new String[] {}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() { 
		super.setTableName("xg_kycxgl_kycxxmlb");
		super.setKey("lbdm");
		super.setClass(KycxxmwhForm.class);
	}
	/**
	 * 查询
	 */
	public KycxxmwhForm getModel(KycxxmwhForm model) throws Exception {
		String lbdm = model.getLbdm();
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*, case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_kycxgl_kycxxmlb a where a.lbdm=? ");
		
		return super.getModel(sql.toString(), new String[]{ lbdm });
	}
	/**
	 * 查询可以申请的类别
	 */
	public List<HashMap<String,String>> getKycxxmwhOpenList() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.* from ( ");
		sql.append("select a.*, case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_kycxgl_kycxxmlb a ");
		sql.append(") a where a.isopen='true' ");
		return dao.getListNotOut(sql.toString(), new String[]{ });
	}
	/**
	 * 保存修改
	 */
	public boolean updateKycxxmwh(KycxxmwhForm model) throws Exception {
		String lbmc = model.getLbmc();
		String splc = model.getSplc();
		String lbdm = model.getLbdm();
		String sql = "update xg_kycxgl_kycxxmlb set lbmc=?,splc=? where lbdm=?";
		return dao.runUpdate(sql, new String[] { lbmc, splc, lbdm });
	}
	/**
	 * 保存修改申请开关
	 */
	public boolean updateKycxxmwhSqkg(KycxxmwhForm model) throws Exception {
		String sqkssj = model.getSqkssj();
		String sqjssj = model.getSqjssj();
		String sqkg = model.getSqkg();
		String lbdm = model.getLbdm();
		String sql = "update xg_kycxgl_kycxxmlb set sqkssj=?,sqjssj=?,sqkg=? where lbdm=?";
		return dao.runUpdate(sql, new String[] { sqkssj, sqjssj, sqkg, lbdm });
	}
	/**
	 * 验证唯一性
	 */
	public boolean checkKycxxmwhSave(KycxxmwhForm model) throws Exception {
		String lbmc = model.getLbmc();
		String lbdm = model.getLbdm();
		String pk = model.getLbdm();
		if ("save".equalsIgnoreCase(model.getType())){
			pk = "-1";
		}
		String sql = "select count(1) num from xg_kycxgl_kycxxmlb where (lbmc=? or lbdm=?) and lbdm<>? ";
		String num = dao.getOneRs(sql, new String[] { lbmc,lbdm,pk }, "num");
		return "0".equals(num);
	}
	/**
	 * 删除验证
	 */
	public String[] checkKycxxmwhDel(String values) throws Exception {
		String[] valuesArr = values.split(",");
		StringBuilder sql = new StringBuilder();
		sql.append("select b.lbmc from ( ");
		sql.append(" select distinct a.lbdm from ( ");
		sql.append("  select lbdm from xg_kycxgl_kycxxmsqb ");
		sql.append("  union ");
		sql.append("  select lbdm from xg_kycxgl_kycxxmjgb");
		sql.append(" ) a where ( ");
		for (int i = 0; i < valuesArr.length; i++) {
			sql.append(" a.lbdm = ? ");
			if(i < valuesArr.length - 1){
				sql.append(" or ");
			}
		}
		sql.append(" ) ");
		sql.append(") a left join xg_kycxgl_kycxxmlb b on a.lbdm=b.lbdm ");
		return dao.getRs(sql.toString(), valuesArr, "lbmc");
	}
	
}
