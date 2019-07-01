package xsgzgl.pjpy.general.tjcx.shmddc;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.general.tjcx.PjpyTjcxDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_审核名单导出_通用_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 程强
 * @version 1.0
 */

public class TjcxShmddcDAO extends PjpyTjcxDAO {
	
	/**
	 * 导出审核名单
	 * 
	 * @author 程强
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws Exception
	 */
	public List<String[]> getShmddcList(TjcxShmddcModel model,String[] arrNj, String[] arrXydm,
			String[] arrZydm, String[] arrBjdm, String[] arrShzt, String xmdm,String spgw,String mhcxz,String mhcxlx)
			throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DAO dao = DAO.getInstance();
		String[] inputValue = new String[]{xmdm,spgw};
		String[] outputValue = new String[] {"xymc","bjmc","xh","xm","xb","sydqmc","zzmm","sfzh","mc","zd1","chjqk","xnqtjx","djkscj","xmmc","ywcf","nfasxw","bysjjzqk",
				"sqly","yjshzt","yjshyj","yjxm","shzt","ejshyj","ejxm","sjshzt","sjshyj","sjxm"};
		String query="";
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from(select a.pjxn,a.pjxq,a.pjnd,a.xmdm,a.sqly,d.zd1,l.nj,l.xydm,l.xymc,l.zydm,l.zymc,l.bjdm,l.bjmc,b.cpz cpzdm,");
		sql.append("b.cpz cpzmc,a.xh,b.xm,(case c.xb when '1' then '男' when '2' then '女'else c.xb end) xb, ");
		
		//省市地区名称
		sql.append(" (select a.qxmc from dmk_qx a where a.qxdm = substr(c.syd, 0, 2)||'0000') || ");
		sql.append(" (select d.qxmc from dmk_qx d where d.qxdm = substr(c.syd, 0, 4)||'00' and c.syd<>substr(c.syd, 0, 2)||'0000') || ");
		sql.append(" (select e.qxmc from dmk_qx e where e.qxdm = c.syd and c.syd<>substr(c.syd, 0, 2)||'0000' and c.syd<>substr(c.syd, 0, 4)||'00') sydqmc, ");
		
		sql.append(" n.zzmmmc zzmm,c.sfzh,d.cpzpm,d.cpzpm || '/' || m.cpzrs mc,");
		sql.append(" e.chjqk,c.zd2 xnqtjx,f.djkscj,g.xmmc,case when h.xh is null then '无' else '有'end ywcf,'' nfasxw,");
		sql.append("'' bysjjzqk,i.spgw,i.shzt yjshzt,i.shyj yjshyj,i.shsj yjshsj,i.xm yjxm,j.shzt shzt,j.shyj ejshyj,");
		
		//评奖人员申请表
		sql.append("j.shsj ejshsj,j.xm   ejxm,k.shzt sjshzt,k.shyj sjshyj,k.shsj sjshsj,k.xm sjxm from xg_pjpy_pjxmsqb a ");
		
		//评奖人员库表
		sql.append("left join xg_pjpy_pjrykb b on a.xh = b.xh ");
		
		//学生信息表
		sql.append("left join xsxxb c on a.xh = c.xh ");
		
		//综合测评表
		sql.append("left join xg_pjpy_zhcpb d on a.xh = d.xh and a.pjxn = d.xn and a.pjxq = d.xq and a.pjnd = d.nd ");
		
		//评奖历史信息表
		sql.append("left join (select xh, max(xmmc) chjqk from (select xh, to_char(WM_CONCAT(xmmc || chjcs || '次') ");
		sql.append(" over(partition by xh order by xh)) xmmc from (select xh, xmmc, count(1) chjcs from xg_pjpy_pjlsxxb ");
		sql.append("group by xh, xmmc)) group by xh) e on a.xh = e.xh ");
		
		//学生登记考试表
		sql.append("left join (select xh, max(djkscj) djkscj from (select xh,to_char(WM_CONCAT(djksmc || ' ' || cj || '分') ");
		sql.append(" over(partition by xh order by xh)) djkscj from (select xh, djksmc, max(cj) cj from xsdjksb ");
		sql.append(" group by xh, djksmc)) group by xh) f on a.xh = f.xh ");
		
		//评奖项目维护表
		sql.append("left join xg_pjpy_pjxmwhb g on a.xmdm = g.xmdm ");
		
		//违纪处分表
		sql.append("left join (select xh from xg_wjcf_wjcfb where ssjg is null or (ssjg is not null and ssjg <> 'cxcf')) h on a.xh = h.xh ");
		
		//评奖项目审核表
		sql.append("left join (select pjxn, pjxq, pjnd, xh, xmdm, shzt, shyj, sftj, shsj,xm,spgw from (select a.pjxn,");
		sql.append("a.pjxq,a.pjnd,a.xh,a.xmdm,case when shzt = 'tg' then '通过'when shzt = 'wsh' then '未审核' when ");
		sql.append("shzt = 'btg' then '不通过' when shzt = 'xcs' then '需重审' when shzt = 'th' then '退回' end shzt,");
		sql.append("a.shyj,a.sftj,a.shsj,b.lcid,c.xh xl,d.xm,xtgwid spgw from xg_pjpy_pjxmshb a ");
		
		//评奖项目维护表
		sql.append("left join xg_pjpy_pjxmwhb b on a.xmdm = b.xmdm ");
		
		//审批步骤表
		sql.append("left join xg_xtwh_spbz c on a.xtgwid = c.spgw and b.lcid = c.splc ");
		sql.append("left join yhb d on a.shr=d.yhm) where xl = '1') i on a.xh = i.xh and a.pjxn = i.pjxn and a.pjxq = i.pjxq");
		sql.append(" and a.pjnd = i.pjnd and a.xmdm = i.xmdm ");
		sql.append("left join (select pjxn, pjxq, pjnd, xh, xmdm, shzt, shyj, sftj, shsj,xm from (select a.pjxn,a.pjxq, ");
		sql.append("a.pjnd,a.xh,a.xmdm,case when shzt = 'tg' then '通过'when shzt = 'wsh' then '未审核'when shzt = 'btg' then ");
		sql.append("'不通过'when shzt = 'xcs' then  '需重审' when shzt = 'th' then '退回'end shzt,a.shyj,a.sftj,a.shsj, ");
		sql.append("b.lcid,c.xh xl,d.xm from xg_pjpy_pjxmshb a  ");
		sql.append("left join xg_pjpy_pjxmwhb b on a.xmdm = b.xmdm ");
		sql.append("left join xg_xtwh_spbz c on a.xtgwid = c.spgw and b.lcid = c.splc ");
		sql.append("left join yhb d on a.shr=d.yhm) where xl = '2') j on a.xh = j.xh and a.pjxn = j.pjxn and a.pjxq = j.pjxq ");
		sql.append("and a.pjnd = j.pjnd and a.xmdm = j.xmdm ");
		sql.append("left join (select pjxn, pjxq, pjnd, xh, xmdm, shzt, shyj, sftj, shsj,xm from (select a.pjxn,a.pjxq,a.pjnd,a.xh,");
		sql.append("a.xmdm,case when shzt = 'tg' then '通过' when shzt = 'wsh' then '未审核'when shzt = 'btg' then '不通过'");
		sql.append(" when shzt = 'xcs' then '需重审' when shzt = 'th' then '退回' end shzt,a.shyj,a.sftj,a.shsj,");
		sql.append("b.lcid,c.xh xl,d.xm from xg_pjpy_pjxmshb a ");
		sql.append("left join xg_pjpy_pjxmwhb b on a.xmdm = b.xmdm ");
		sql.append("left join xg_xtwh_spbz c on a.xtgwid = c.spgw and b.lcid = c.splc ");
		sql.append("left join yhb d on a.shr=d.yhm) where xl = '3')k on a.xh = k.xh and a.pjxn = k.pjxn ");
		sql.append(" and a.pjxq = k.pjxq and a.pjnd = k.pjnd and a.xmdm = k.xmdm ");
		sql.append("left join view_njxyzybj_all l on l.bjdm = b.bjdm ");
		sql.append("left join (select cpz, count(1) cpzrs from xg_pjpy_pjrykb where sfcp = 'yes' group by cpz) m ");
		sql.append("  on b.cpz = m.cpz left join zzmmdmb n on c.zzmm=n.zzmmdm  where a.xmdm = ? and ");
		sql.append(" exists (select 1 from xg_pjpy_pjxmshb z where a.xmdm =z.xmdm and a.pjxn=z.pjxn ");
		sql.append("and a.pjnd=z.pjnd and a.pjxq=z.pjxq and a.xh=z.xh and z.xtgwid = ?  ");

		sql.append(" ) order by cpzpm ) where 1=1 ");
		
		if(null!=arrNj&&arrNj.length>0){
			sql.append(" and ( ");
			for(int i = 0 ; i<arrNj.length;i++){
				if(i!=arrNj.length-1){
					sql.append(" nj = "+arrNj[i]+" or");
				}else{
					sql.append(" nj = "+arrNj[i]+" ");
				}
			}
			sql.append(" )");
		}
		if(null!=arrXydm&&arrXydm.length>0){
			sql.append(" and ( ");
			for(int i = 0 ; i<arrXydm.length;i++){
				if(i!=arrXydm.length-1){
					sql.append(" xydm = "+arrXydm[i]+" or");
				}else{
					sql.append(" xydm = "+arrXydm[i]+" ");
				}
			}
			sql.append(" )");
		}
		if(null!=arrZydm&&arrZydm.length>0){
			sql.append(" and ( ");
			for(int i = 0 ; i<arrZydm.length;i++){
				if(i!=arrZydm.length-1){
					sql.append(" zydm = "+arrZydm[i]+" or");
				}else{
					sql.append(" zydm = "+arrZydm[i]+" ");
				}
			}
			sql.append(" )");
		}
		if(null!=arrBjdm&&arrBjdm.length>0){
			sql.append(" and ( ");
			for(int i = 0 ; i<arrBjdm.length;i++){
				if(i!=arrBjdm.length-1){
					sql.append(" bjdm = "+arrBjdm[i]+" or");
				}else{
					sql.append(" bjdm = "+arrBjdm[i]+" ");
				}
			}
			sql.append(" )");
		}
		
		if("all".equals(mhcxlx)&&null!=mhcxz){
			String ss[] =  mhcxz.split(" ");
			sql.append(" and (( ");
			for(int i = 0 ; i<ss.length;i++){
				if(i!=ss.length-1){
					sql.append(" xh like '%"+ss[i]+"%' or ");
					//sql.append("xm like '%"+ss[i]+"%' ");
			}else{
				sql.append(" xh like '%"+ss[i]+"%'  ");
			}
			}
			sql.append(" ) or (");
			for(int i = 0 ; i<ss.length;i++){
				if(i!=ss.length-1){
					sql.append(" xm like '%"+ss[i]+"%' or ");
			}else{
				sql.append(" xm like '%"+ss[i]+"%' ");
			}
			}
			sql.append(" ))");
		}
		if("xh".equals(mhcxlx)&&null!=mhcxz){
			String ss[] =  mhcxz.split(" ");
			sql.append(" and ( ");
			for(int i = 0 ; i<ss.length;i++){
				if(i!=ss.length-1){
					sql.append(" xh like '%"+ss[i]+"%' or ");
			}else{
				sql.append(" xh like '%"+ss[i]+"%'  ");
			}
			}
			sql.append(" )");
		}
		if("xm".equals(mhcxlx)&&null!=mhcxz){
			String ss[] =  mhcxz.split(" ");
			sql.append(" and ( ");
			for(int i = 0 ; i<ss.length;i++){
				if(i!=ss.length-1){
					sql.append(" xm like '%"+ss[i]+"%' or ");
			}else{
				sql.append(" xm like '%"+ss[i]+"%'  ");
			}
			}
			sql.append(" )");
		}
		String xh = dao.getOneRs("select xh from xg_xtwh_spbz where spgw = ? and splc = (select lcid from xg_pjpy_pjxmwhb where xmdm =?)" 		
				, new String[]{spgw,xmdm}, "xh");
		
		if("2".equals(xh)){
			sql.append(" and yjshzt ='通过' ");
		}
		if("3".equals(xh)){
			sql.append("  and  shzt = '通过'");
		}
		if(null!=arrShzt&&arrShzt.length>0){
			if("1".equals(xh)){
				sql.append(" and ( ");
				for(int i = 0 ; i<arrShzt.length;i++){
					if(i!=arrShzt.length-1){
						sql.append(" yjshzt = '"+arrShzt[i]+"' or");
					}else{
						sql.append(" yjshzt = '"+arrShzt[i]+"' ");
					}
				}
				sql.append(" )");
			}
			if("2".equals(xh)){
				sql.append(" and ( ");
				for(int i = 0 ; i<arrShzt.length;i++){
					if(i!=arrShzt.length-1){
						sql.append(" shzt = '"+arrShzt[i]+"' or");
					}else{
						sql.append(" shzt = '"+arrShzt[i]+"' ");
					}
				}
				sql.append(" )");
			}
			if("3".equals(xh)){
				sql.append(" and ( ");
				for(int i = 0 ; i<arrShzt.length;i++){
					if(i!=arrShzt.length-1){
						sql.append(" sjshzt = '"+arrShzt[i]+"' or");
					}else{
						sql.append(" sjshzt = '"+arrShzt[i]+"' ");
					}
				}
				sql.append(" )");
			}
		}
		
		return CommonQueryDAO.commonQueryNotFy(sql.toString(), query, inputValue,outputValue, model);
	}
	
	/**
	 * 根据班级代码获取班级名称
	 * @param zydm
	 * @return
	 */
	public String getXmmc(String xmdm){
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select xmmc from xg_pjpy_pjxmwhb  a " +			
				"where xmdm = ? ", new String[]{xmdm}, "xmmc");
	}
	
}
