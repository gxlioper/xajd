package xgxt.xsxx.dao;

import java.util.ArrayList;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.utils.String.StringUtils;

public class XxcshglDAO extends DAO{
	ArrayList<String> value = new ArrayList<String>();
	String insertXsxxb = StringUtils.joinStr("insert into xsxxb(xh,xm,xb,csrq,mz,zzmm,sfzh,pycc,jg,jgs,jgshi,jgx,syd,syds,",
							"sydshi,sydx,nj,xydm,zydm,bjdm,xz,xjztm,rxrq,byny,sfzc,sfzd,sfzx,sfbys,sfyby,lxdh,sjhm,ssbh,",
							"qsdh,qqhm,dzyx,yhdm,yhkh,ykth,xmpy,cym,sg,tz,tc,kslb,rxfs,pyfs,hkszd,hkshen,hkshi,hkxian)(select xh,xm,",
							"xb,csrq,mz,zzmm,sfzh,pycc,jgs||'/'||jgshi||'/'||jgx,jgs,jgshi,jgx,syds||'/'||sydshi||'/'||sydx,",
							"syds,sydshi,sydx,nj,xydm,zydm,bjdm,xz,xjztm,rxrq,bysj,sfzc,sfzd,sfzx,sfbys,sfyby,lxdh,sjhm,",
							"ssbh,qsdh,qqhm,dzyx,yhdm,yhkh,ykth,xmpy,cym,sg,tz,tc,kslb,rxfs,pyfs,",
							"hkshen||'/'||hkshi||'/'||hkxian,hkshen,hkshi,hkxian from xg_xsxx_xsxxlsb ");

	
	String insertXsfzxxb = StringUtils.joinStr("insert into xsfzxxb(xh,lxdh1,yb,jtszd,jjzk,jtcy1_xm,jtcy1_gx,jtcy1_nl,jtcy1_sfzh,jtcy1_mz,",
							"jtcy1_zzmm,jtcy1_zy,jtcy1_zw,jtcy1_gzdz,jtcy1_lxdh1,jtcy1_lxdh2,jtcy2_xm,jtcy2_gx,jtcy2_nl,",
							"jtcy2_sfzh,jtcy2_mz,jtcy2_zzmm,jtcy2_zy,jtcy2_zw,jtcy2_gzdz,jtcy2_lxdh1,jtcy2_lxdh2,",
							"jtcy3_xm,jtcy3_gx,jtcy3_nl,jtcy3_sfzh,jtcy3_mz,jtcy3_zzmm,jtcy3_zy,jtcy3_zw,jtcy3_gzdz,",
							"jtcy3_lxdh1,jtcy3_lxdh2)(select xh,jtlxdh1,jtyb,jtszd,jtjjzk,jtcy1_xm,jtcy1_gx,",
							"jtcy1_nl,jtcy1_sfzh,jtcy1_mz,jtcy1_zzmm,jtcy1_zy,jtcy1_zw,jtcy1_gzdz,jtcy1_lxdh1,",
							"jtcy1_lxdh2,jtcy2_xm,jtcy2_gx,jtcy2_nl,jtcy2_sfzh,jtcy2_mz,jtcy2_zzmm,jtcy2_zy,jtcy2_zw,",
							"jtcy2_gzdz,jtcy2_lxdh1,jtcy2_lxdh2,jtcy3_xm,jtcy3_gx,jtcy3_nl,jtcy3_sfzh,jtcy3_mz,",
							"jtcy3_zzmm,jtcy3_zy,jtcy3_zw,jtcy3_gzdz,jtcy3_lxdh1,jtcy3_lxdh2 from xg_xsxx_xsxxlsb ");
	
	/**
	 * 获取查询条件
	 * */
	public StringBuilder getWhereSql(StudentInfoForm model){
		StringBuilder sb = new StringBuilder(" where 1=1 ");
		String xh = model.getXh();
		String xm = model.getXm();
		String xydm = model.getYdqxydm();
		String zydm = model.getYdqzydm();
		String bjdm = model.getYdqbjdm();
		String nj = model.getYdqnj();
		String ydlbdm = model.getYdlbdm();
		String[] xtgwidArr = model.getXtgwidArr();
		String[] shztArr = model.getShztArr();
		String ydrqks = model.getQuerygreaterequal_ydrq();
		String ydrqjs = model.getQuerylessequal_ydrq();
		String ydjzrqks = model.getQuerygreaterequal_ydjzrq();
		String ydjzrqjs = model.getQuerylessequal_ydjzrq();
		
		if(!StringUtils.isNull(xh)){
			sb.append( " and xh like '%'||?||'%'");
			value.add(xh);
		}
		if(!StringUtils.isNull(xm)){
			sb.append( " and xm like '%'||?||'%'");
			value.add(xm);
		}
		if(!StringUtils.isNull(xydm)){
			sb.append( " and ydqxydm=?");
			value.add(xydm);
		}
		if(!StringUtils.isNull(zydm)){
			sb.append( " and ydqzydm=?");
			value.add(zydm);
		}
		if(!StringUtils.isNull(bjdm)){
			sb.append( " and ydqbjdm=?");
			value.add(bjdm);
		}
		if(!StringUtils.isNull(nj)){
			sb.append( " and ydqnj=?");
			value.add(nj);
		}
		if(!StringUtils.isNull(ydlbdm)){
			sb.append( " and ydlbdm=?");
			value.add(ydlbdm);
		}
		if(!StringUtils.isNull(ydrqks)){
			sb.append( " and to_number(substr(ydrq,0,4)||substr(ydrq,6,2)||substr(ydrq,9,2))>=?");
			value.add(ydrqks);
		}
		if(!StringUtils.isNull(ydrqjs)){
			sb.append( " and to_number(substr(ydrq,0,4)||substr(ydrq,6,2)||substr(ydrq,9,2))<=?");
			value.add(ydrqjs);
		}
		if(!StringUtils.isNull(ydjzrqks)){
			sb.append( " and to_number(substr(ydjzrq,0,4)||substr(ydjzrq,6,2)||substr(ydjzrq,9,2))>=?");
			value.add(ydjzrqks);
		}
		if(!StringUtils.isNull(ydjzrqjs)){
			sb.append( " and to_number(substr(ydjzrq,0,4)||substr(ydjzrq,6,2)||substr(ydjzrq,9,2))<=?");
			value.add(ydjzrqjs);
		}
		
		//审核条件
		if(xtgwidArr != null){
			for(int i=0; i<xtgwidArr.length; i++){
				if(shztArr != null && shztArr.length>i && StringUtils.isNotNull(shztArr[i])){
					sb.append(" and exists( select 1 from xg_xsxx_xjydxx_shb b where a.ydxh=b.ydxh and b.xtgwid='");
					sb.append(xtgwidArr[i]);
					sb.append("' and shzt=?)");				
					value.add(shztArr[i]);
				}
			}
		}
		return sb;
	}
	
	/**
	 * 将指定的学校部门信息从临时表保存到正式表中
	 * */
	public boolean saveXxbmlsxxdzsk(String[] pkV){
		String[] sqlArr = new String[pkV.length*3];
		int num = 0;
		//先将字段的数据在正式表中删除，在将选中的数据从临时表中插入到正式表,最后将选中的数据从临时表中删除
		for(String pkValue : pkV){
			sqlArr[num++] = "delete zxbz_xxbmdm where bmdm='" + DealString.replaceImmitStr(pkValue) + "'";
			sqlArr[num++] = "insert into zxbz_xxbmdm(select * from xg_xsxx_xxbmxxlsb where bmdm='" 
							+ DealString.replaceImmitStr(pkValue) 
							+ "')";
			sqlArr[num++] = "delete xg_xsxx_xxbmxxlsb where bmdm='" + DealString.replaceImmitStr(pkValue) + "'";
		}
		try{
			return checkBatch(runBatch(sqlArr));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 将全部的学校部门信息从临时表保存到正式表中
	 * */
	public boolean saveAllXxbmlsxxdzsk(String[] pkV){
		//先将正式表中在临时表中存在的数据删除，在将临时表中的所有数据插入到正式表中,最后删除临时表中的信息
		String[] sqlArr = {"delete from zxbz_xxbmdm a where exists(select 1 from xg_xsxx_xxbmxxlsb b where a.bmdm=b.bmdm)", 
						   "insert into zxbz_xxbmdm (select * from xg_xsxx_xxbmxxlsb)", 
						   "delete from xg_xsxx_xxbmxxlsb"};
		try {
			return checkBatch(runBatch(sqlArr));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 将指定的专业信息从临时表保存到正式表中
	 * */
	public boolean saveZyxxlsxxdzsk(String[] pkV){
		String[] sqlArr = new String[pkV.length*3];
		int num = 0;
		//先将字段的数据在正式表中删除，在将选中的数据从临时表中插入到正式表,最后将选中的数据从临时表中删除
		for(String pkValue : pkV){
			sqlArr[num++] = "delete bks_zydm where zydm='" + DealString.replaceImmitStr(pkValue) + "'";
			sqlArr[num++] = "insert into bks_zydm(select * from xg_xsxx_zyxxlsb where zydm='" 
							+ DealString.replaceImmitStr(pkValue) 
							+ "')";
			sqlArr[num++] = "delete xg_xsxx_zyxxlsb where zydm='" + DealString.replaceImmitStr(pkValue) + "'";
		}
		try{
			return checkBatch(runBatch(sqlArr));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 将全部的专业信息从临时表保存到正式表中
	 * */
	public boolean saveAllZyxxlsxxdzsk(String[] pkV){
		//先将正式表中在临时表中存在的数据删除，在将临时表中的所有数据插入到正式表中,最后删除临时表中的信息
		String[] sqlArr = {"delete from bks_zydm a where exists(select 1 from xg_xsxx_zyxxlsb b where a.zydm=b.zydm)", 
						   "insert into bks_zydm (select * from xg_xsxx_zyxxlsb)", 
						   "delete from xg_xsxx_zyxxlsb"};
		try {
			return checkBatch(runBatch(sqlArr));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 将指定的班级信息从临时表保存到正式表中
	 * */
	public boolean saveBjxxlsxxdzsk(String[] pkV){
		String[] sqlArr = new String[pkV.length*3];
		int num = 0;
		//先将字段的数据在正式表中删除，在将选中的数据从临时表中插入到正式表,最后将选中的数据从临时表中删除
		for(String pkValue : pkV){
			sqlArr[num++] = "delete bks_bjdm where bjdm='" + DealString.replaceImmitStr(pkValue) + "'";
			sqlArr[num++] = "insert into bks_bjdm(select * from xg_xsxx_bjxxlsb where bjdm='" 
							+ DealString.replaceImmitStr(pkValue) 
							+ "')";
			sqlArr[num++] = "delete xg_xsxx_bjxxlsb where bjdm='" + DealString.replaceImmitStr(pkValue) + "'";
		}
		try{
			return checkBatch(runBatch(sqlArr));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 将全部的班级信息从临时表保存到正式表中
	 * */
	public boolean saveAllBjxxlsxxdzsk(String[] pkV){
		//先将正式表中在临时表中存在的数据删除，在将临时表中的所有数据插入到正式表中,最后删除临时表中的信息
		String[] sqlArr = {"delete from bks_bjdm a where exists(select 1 from xg_xsxx_bjxxlsb b where a.bjdm=b.bjdm)", 
						   "insert into bks_bjdm (select * from xg_xsxx_bjxxlsb)", 
						   "delete from xg_xsxx_bjxxlsb"};
		try {
			return checkBatch(runBatch(sqlArr));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 将指定的班级信息从临时表保存到正式表中
	 * */
	public boolean saveXsxxlsxxdzsk(String[] pkV){
		String[] sqlArr = new String[pkV.length*3*2];
		int num = 0;		
		//先将字段的数据在正式表中删除，在将选中的数据从临时表中插入到正式表,最后将选中的数据从临时表中删除
		for(String pkValue : pkV){
			//先保存学生信息
			sqlArr[num++] = "delete xsxxb where xh='" + DealString.replaceImmitStr(pkValue) + "'";
			sqlArr[num++] = insertXsxxb+" where xh='" 
							+ DealString.replaceImmitStr(pkValue) 
							+ "')";
			//再保存家庭信息
			sqlArr[num++] = "delete xsfzxxb where xh='" + DealString.replaceImmitStr(pkValue) + "'";
			sqlArr[num++] = insertXsfzxxb +" where xh='" 
							+ DealString.replaceImmitStr(pkValue) 
							+ "')";
			//将临时表的信息删除
			sqlArr[num++] = "delete xg_xsxx_xsxxlsb where xh='" + DealString.replaceImmitStr(pkValue) + "'";
		}
		try{
			return checkBatch(runBatch(sqlArr));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 将全部的学生信息从临时表保存到正式表中
	 * */
	public boolean saveAllXsxxlsxxdzsk(String[] pkV){
		//先将正式表中在临时表中存在的数据删除，在将临时表中的所有数据插入到正式表中,最后删除临时表中的信息
		String[] sqlArr = {"delete from xsxxb a where exists(select 1 from xg_xsxx_xsxxlsb b where a.xh=b.xh)",
						   "delete from xsfzxxb a where exists(select 1 from xg_xsxx_xsxxlsb b where a.xh=b.xh)",
						   insertXsxxb+")",
						   insertXsfzxxb+")",
						   "delete from xg_xsxx_xsxxlsb"};
		try {
			return checkBatch(runBatch(sqlArr));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
