package xgxt.xszz.xcxy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.upload.FormFile;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.GetTime;
import xgxt.utils.SearchUtils;

public class XcxyXszzDao {
	
	DAO dao = DAO.getInstance();
	/*
	 * 获得资助项目list
	 */
	public List<String[]> getZzxm_db(String[] colList,XcxyXszzForm form){
		StringBuffer query = new StringBuffer(" where 1=1 ");// sql条件
		query.append(SearchUtils.likeSql("xmdm", form.getXmdm()));
		query.append(SearchUtils.likeSql("xmmc", form.getXmmc()));
		String sql ="select xmdm pk,a.* from xszz_xmdmb a"+query.toString();
		return dao.rsToVator(sql,new String[]{},colList);
	}
	
	/*
	 * 获得指定xmdm的项目信息和时间
	 */
	public String[] getOneZzxm_db(String pk,String[] colList){
		String sql ="select a.*,b.zzkssj,b.zzjssj from xszz_xmdmb a left join xszz_zzsjb b on a.xmdm=b.xmdm where a.xmdm=?";
		return dao.getOneRs(sql, new String[]{pk}, colList);
	}
	
	/*
	 * 检查唯一性
	 */
	public String checkOnlyOne_db(String pk,String pkValue,String tableName){
		String sql = "select "+pk+" from "+tableName+" where "+pk+"=?";
		return dao.getOneRs(sql, new String[]{pkValue}, pk);
	}
	
	/*
	 * 资助项目增加
	 */
	public String add_db(XcxyXszzForm form){
		String sql = "insert into xszz_xmdmb (xmdm,xmmc,xmlc,bbys) values(?,?,?,?)";
		boolean flag = false;
		try {
			flag = dao.runUpdate(sql, new String[]{form.getXmdm(),form.getXmmc(),form.getXmlc(),form.getBbys()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag == true ? "yes" : "no";
	}
	
	/*
	 * 资助项目修改
	 */
	public String modify_db(XcxyXszzForm form){
		String sql = "update xszz_xmdmb  set xmmc =?,xmlc=?,bbys=? where xmdm=?";
		boolean flag = false;
		try {
			flag = dao.runUpdate(sql, new String[]{form.getXmmc(),form.getXmlc(),form.getBbys(),form.getXmdm()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag == true ? "yes" : "no";
	}
	
	/*
	 * 资助项目删除
	 */
	public void delete_db(String pkStr) throws SQLException{
		String[] pkT = pkStr.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length*3];
		int x = 0;
		for (int i = 0; i < pkT.length; i++) {
			sqlT[x] = "delete xszz_common_new_zzsjb where xmdm='"+pkT[i]+"'";
			x++;
			sqlT[x] = "delete xszz_common_new_zzjeb where xmdm='"+pkT[i]+"'";
			x++;
			sqlT[x] = "delete xszz_xmdmb where xmdm='"+pkT[i]+"'";
			x++;
		}
		dao.runBatch(sqlT);
	}
	
	/*
	 * 获得所有资助项目list
	 */
	public List<HashMap<String,String>> getAllZzxm_db(){
		String sql = "select xmdm,xmmc from xszz_xmdmb";
		return dao.getList(sql, new String[]{}, new String[]{"xmdm","xmmc"});
	}
	
	/*
	 * 查询资助项目金额
	 */
	public List<String[]> queryZzxmJe_db(String[] colList,XcxyXszzForm form){
		StringBuffer query = new StringBuffer("");// sql条件
		query.append(SearchUtils.likeSql("a.xmdm", form.getXmdm()));
		query.append(SearchUtils.likeSql("a.xmmc", form.getXmmc()));
		String sql ="select a.xmdm||b.zzje pk,a.xmmc,b.zzje from xszz_xmdmb a,xszz_zzjeb b where a.xmdm=b.xmdm"+query.toString();
		return dao.rsToVator(sql,new String[]{},colList);
	}
	
	/*
	 * 资助项目增加
	 */
	public String addZzje_db(XcxyXszzForm form){
		String sql = "insert into xszz_zzjeb (xmdm,zzje) values(?,?)";
		boolean flag = false;
		try {
			flag = dao.runUpdate(sql, new String[]{form.getXmdm(),form.getZzje()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag == true ? "yes" : "no";
	}
	
	/*
	 * 资助项目修改
	 */
	public String modifyZzje_db(XcxyXszzForm form){
		String sql = "update xszz_zzjeb  set zzje =? where xmdm||zzje=?";
		boolean flag = false;
		try {
			flag = dao.runUpdate(sql, new String[]{form.getZzje(),form.getTemp()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag == true ? "yes" : "no";
	}
	
	/*
	 * 资助项目金额删除
	 */
	public void deleteZzje_db(String pkStr) throws SQLException{
		String[] pkT = pkStr.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		int x = 0;
		for (int i = 0; i < pkT.length; i++) {
			sqlT[x] = "delete xszz_zzjeb where xmdm||zzje='"+pkT[i]+"'";
			x++;
		}
		dao.runBatch(sqlT);
	}
	
	/*
	 * 获得指定xmdm||zzje的项目金额
	 */
	public String[] getOneZzje_db(String pk,String[] colList){
		String sql ="select * from xszz_zzjeb where xmdm||zzje=?";
		return dao.getOneRs(sql, new String[]{pk}, colList);
	}
	
	/*
	 * 获得指定资助项目的金额
	 */
	public List<HashMap<String,String>> getZzxmJe_db(String xmdm){
		String sql ="select b.xmdm,b.xmmc,a.zzje from xszz_xmdmb b left join xszz_zzjeb a on a.xmdm=b.xmdm where b.xmdm=?";
		return dao.getList(sql, new String[]{xmdm}, new String[]{"xmdm","xmmc","zzje"});
	}
	
	/*
	 * 查询资助项目时间
	 */
	public List<String[]> queryZzxmSj_db(String[] colList,XcxyXszzForm form){
		StringBuffer query = new StringBuffer("where 1=1 ");// sql条件
		query.append(SearchUtils.likeSql("a.xmdm", form.getXmdm()));
		query.append(SearchUtils.likeSql("a.xmmc", form.getXmmc()));
		String sql ="select a.xmdm,a.xmmc,b.zzkssj,b.zzjssj from xszz_xmdmb a left join xszz_zzsjb b on a.xmdm=b.xmdm "+query.toString();
		return dao.rsToVator(sql,new String[]{},colList);
	}
	
	/*
	 * 获得指定xmdm的项目时间
	 */
	public String[] getOneZzsj_db(String pk,String[] colList){
		String sql ="select a.xmdm,zzkssj,zzjssj from xszz_xmdmb a left join xszz_zzsjb b on a.xmdm=b.xmdm where a.xmdm=?";
		return dao.getOneRs(sql, new String[]{pk}, colList);
	}
	
	/*
	 * 单个资助时间设定
	 */
	public String setZzsj_db(XcxyXszzForm form){
		String sql = "select xmdm from xszz_zzsjb where xmdm=?";
		String xmdm = dao.getOneRs(sql, new String[]{form.getXmdm()}, "xmdm");
		if(Base.isNull(xmdm)){
			sql = "insert into xszz_zzsjb (zzkssj,zzjssj,xmdm) values(?,?,?)";
		}else{
			sql = "update xszz_zzsjb  set zzkssj =?,zzjssj=? where xmdm=?";
		}
		boolean flag = false;
		try {
			flag = dao.runUpdate(sql, new String[]{form.getZzkssj(),form.getZzjssj(),form.getXmdm()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag == true ? "yes" : "no";
	}
	
	/*
	 * 初始化资助时间
	 */
	public void clearZzsj_db() throws Exception{
		String sql ="delete from xszz_zzsjb";
		dao.runUpdate(sql, new String[]{});
	}
	
	/*
	 * 批量设置资助时间
	 */
	public void saveManyZzsj_db(String str,XcxyXszzForm form) throws Exception{
		String sql = "delete from xszz_zzsjb where instr(?,'!@!'||xmdm||'!@!') >0";
		boolean flag = dao.runUpdate(sql, new String[]{str});
		if(flag){
			sql = "insert into xszz_zzsjb(xmdm,zzkssj,zzjssj) (select xmdm,? zzkssj,? zzjssj from xszz_xmdmb where instr (?,'!@!'||xmdm||'!@!') >0)";
		}
		dao.runUpdate(sql,new String[]{form.getZzkssj(),form.getZzjssj(),str});
	}
	
	/*
	 * 校验是否在项目申请时间范围内
	 */
	public String checkSfksq_db(HashMap<String,String> map){
		String nowtime = GetTime.getSystemTime();
		if(nowtime.compareTo(map.get("zzkssj")) >= 0 && nowtime.compareTo(map.get("zzjssj")) <= 0){
			return "yes";
		}else{
			return "no";
		}
	}
	
	/*
	 * 获得奖学金学生的基本信息
	 */
	public HashMap<String,String> getJxjXsxx_db(String xxmc,XcxyXszzForm form){
		HashMap<String,String> map = new HashMap<String,String>();
		StringBuffer sb = new StringBuffer();
		sb.append("select a.xh,xm,csrq,xb,nj||'-09-01' rxrq,mzmc,xymc,zymc,bjmc,b.chjl,nvl(b.sfzh,a.sfzh) sfzh,a.xz,a.ssbh,a.zzmmmc,b.xl,");
		sb.append("nvl(b.jg,a.jg) jg,nvl(b.lxdh,a.lxdh) lxdh,nvl(b.jtdz,a.jtdz) jtdz,nvl(b.yhkh,a.yhkh) yhkh,nvl(b.ykth,a.ykth) ykth,b.fkh,b.sqly ");
		sb.append(",b.wjsclj from view_xsxxb a left join xszz_jxjsqb b on a.xh=b.xh and xn=? and b.xmdm=? where a.xh=?");
		List<HashMap<String,String>> list = dao.getListNotOut(sb.toString(), new String[]{Base.currXn,form.getXmdm(),form.getXh()});
		if(list != null && list.size() > 0){
			map = list.get(0);
			map.put("xxmc", xxmc);
		}
		return map;
	}
	
	/*
	 * 获得助学金学生的基本信息
	 */
	public HashMap<String,String> getZxjXsxx_db(XcxyXszzForm form){
		HashMap<String,String> map = new HashMap<String,String>();
		StringBuffer sb = new StringBuffer();
		sb.append("select a.xh,xm,csrq,xb,nj||'-09-01' rxrq,mzmc,xymc,zymc,bjmc,b.chjl,nvl(b.sfzh,a.sfzh) sfzh,a.xz,a.ssbh,a.zzmmmc,");
		sb.append("b.xl,nvl(b.jg,a.jg) jg,nvl(b.hkxz,a.hkxz) hkxz,b.jtzrs,b.jtysr,b.rjysr,b.srly,b.yzbm,nvl(b.jtdz,a.jtdz) jtdz,");
		sb.append("b.jtcy1_xm,b.jtcy1_nl,b.jtcy1_gx,b.jtcy1_dw,b.jtcy2_xm,b.jtcy2_nl,b.jtcy2_gx,b.jtcy2_dw,b.jtcy3_xm,");
		sb.append("b.jtcy3_nl,b.jtcy3_gx,b.jtcy3_dw,b.jtcy4_xm,b.jtcy4_nl,b.jtcy4_gx,b.jtcy4_dw,nvl(b.lxdh,a.lxdh) lxdh,");
		sb.append("nvl(b.yhkh,a.yhkh) yhkh,nvl(b.ykth,a.ykth) ykth,b.fkh,b.sqly,b.wjsclj from view_xsxxb a left join xszz_zxjsqb b ");
		sb.append("on a.xh=b.xh and xn=? and b.xmdm=? where a.xh=?");
		List<HashMap<String,String>> list = dao.getListNotOut(sb.toString(), new String[]{Base.currXn,form.getXmdm(),form.getXh()});
		if(list != null && list.size() > 0){
			map = list.get(0);
		}
		return map;
	}
	
	/*
	 * 获得励志奖学金学生的基本信息
	 */
	public HashMap<String,String> getLzjxjXsxx_db(XcxyXszzForm form){
		HashMap<String,String> map = new HashMap<String,String>();
		StringBuffer sb = new StringBuffer();
		sb.append("select a.xh,xm,csrq,xb,nj||'-09-01' rxrq,mzmc,xymc,zymc,bjmc,b.chjl,nvl(b.sfzh,a.sfzh) sfzh,a.xz,a.ssbh,a.zzmmmc,");
		sb.append("b.xl,nvl(b.jg,a.jg) jg,nvl(b.hkxz,a.hkxz) hkxz,b.jtzrs,b.jtysr,b.rjysr,b.srly,b.yzbm,b.jtdz,nvl(b.lxdh,a.lxdh) lxdh,");
		sb.append("nvl(b.yhkh,a.yhkh) yhkh,nvl(b.ykth,a.ykth) ykth,b.fkh,b.sqly,b.wjsclj from view_xsxxb a left join xszz_lzjxjsqb b ");
		sb.append("on a.xh=b.xh and xn=? and b.xmdm=? where a.xh=?");
		List<HashMap<String,String>> list = dao.getListNotOut(sb.toString(), new String[]{Base.currXn,form.getXmdm(),form.getXh()});
		if(list != null && list.size() > 0){
			map = list.get(0);
		}
		return map;
	}
	
	/*
	 * 学生成绩过渡
	 */
	public List<String[]> getCjList_db(String xh,String xn,String xq) throws Exception {
		String sql = "select xn,(select xqmc from xqdzb where xqdm=a.xq) xq,kcmc,kcxz,cj,bkcj from view_cjb a where xh=? and xn=? and a.xq=?";
		return dao.rsToVator(sql, new String[] { xh, xn,xq }, new String[] { "xn", "xq", "kcmc", "kcxz","cj","bkcj" });
	}
	
	/*
	 * 学生成绩学分
	 */
	public List<String[]> getCjListByxf_db(String xh,String xn,String xq) throws Exception {
		String sql = "select xn,(select xqmc from xqdzb where xqdm=a.xq) xq,kcmc,kclx,zpcj2,bkcj from view_cjb a where xh=? and xn=? and a.xq=?";
		return dao.rsToVator(sql, new String[] { xh,xn,xq}, new String[] { "xn", "xq", "kcmc", "kclx","zpcj2","bkcj" });
	}
	
	/*
	 * 获得学生成绩的汇总
	 */
	public HashMap<String,String> getXscjHz_db(String xh,String xn,String xq){
		String sql = "select pjcj,cxcj,zcj,pm,pjcjpm from view_xcxy_pjxscjb where xh=? and xn=? and xq=?";	
		List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{xh,xn,xq});
		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	
	/*
	 * 保存学生资助申请
	 */
	public String saveJzxjsq_db(XcxyXszzForm form) throws Exception, IOException{
		String xmlc  = form.getXmlc();
		String sql = "";
		String jg = form.getJg();
		String xl = form.getXl();
		String jtdz = form.getJtdz();
		String chjl = form.getChjl();
		String sqly = form.getSqly();
		String hkxz = form.getHkxz();
		String srly = form.getSrly();
		String jtcy1_xm = form.getJtcy1_xm();
		String jtcy1_gx = form.getJtcy1_gx();
		String jtcy1_dw = form.getJtcy1_dw();
		String jtcy2_xm = form.getJtcy2_xm();
		String jtcy2_gx = form.getJtcy2_gx();
		String jtcy2_dw = form.getJtcy2_dw();
		String jtcy3_xm = form.getJtcy3_xm();
		String jtcy3_gx = form.getJtcy3_gx();
		String jtcy3_dw = form.getJtcy3_dw();
		String jtcy4_xm = form.getJtcy4_xm();
		String jtcy4_gx = form.getJtcy4_gx();
		String jtcy4_dw = form.getJtcy4_dw();
		String[] colValues = null;
		String tabName = "";
		String pathName = "";
		String dir = "/upload";		
		if("国家奖学金".equals(xmlc)){
			tabName ="xszz_jxjsqb";		
		}else if("国家助学金".equals(xmlc)){
			tabName ="xszz_zxjsqb";
		}else if("国家励志奖学金".equals(xmlc)){
			tabName ="xszz_lzjxjsqb";
		}
		sql = "select xysh from "+tabName+" where xh=? and xn=? and xmdm=?";
		String xysh = dao.getOneRs(sql, new String[] { form.getXh(),
				Base.currXn, form.getXmdm() }, "xysh");
		if (!Base.isNull(xysh) && xysh.equals("未审核")) {
			sql = "delete from " + tabName + " where xh=? and xn=? and xmdm=?";
			dao.runUpdate(sql, new String[] { form.getXh(), Base.currXn,
					form.getXmdm() }, "xysh");
		} else if (!Base.isNull(xysh)) {
			return "checking";
		}
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdir();
		}
		FormFile file = form.getUploadFile();
		if (!Base.isNull(file.getFileName())) {
			int size = file.getFileSize();
			pathName = dir + "/" + Base.currXn + form.getXh() + "!@!"
					+ file.getFileName();
			File scfile = new File(pathName);
			if (!scfile.exists()) {
				scfile.createNewFile();
				InputStream in = file.getInputStream();
				OutputStream out = new FileOutputStream(pathName);
				int bytesRead = 0;
				byte[] buffer = new byte[size];
				while ((bytesRead = in.read(buffer, 0, size)) != -1) {
					out.write(buffer, 0, bytesRead);
				}
				out.close();
				in.close();
			}
		}
		if ("国家奖学金".equals(xmlc)) {
			colValues = new String[] { form.getXh(), Base.currXn, Base.currNd,
					chjl, form.getSfzh(), xl, jg, form.getXmdm(),
					form.getLxdh(), jtdz, form.getYhkh(), form.getYkth(),
					form.getFkh(), sqly, pathName };
			sql = "insert into xszz_jxjsqb(xh,xn,nd,chjl,sfzh,xl,jg,xmdm,lxdh,jtdz,yhkh,ykth,fkh,sqly,wjsclj) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			dao.runUpdate(sql, colValues);
		} else if ("国家助学金".equals(xmlc)) {
			colValues = new String[] { form.getXh(), Base.currXn, Base.currNd,
					chjl, form.getSfzh(), xl, jg, form.getXmdm(),
					form.getLxdh(), jtdz, form.getYhkh(), form.getYkth(),
					form.getFkh(), sqly, hkxz, form.getJtzrs(),
					form.getJtysr(), form.getRjysr(), srly, form.getYzbm(),
					jtcy1_xm, form.getJtcy1_nl(), jtcy1_gx, jtcy1_dw, jtcy2_xm,
					form.getJtcy2_nl(), jtcy2_gx, jtcy2_dw, jtcy3_xm,
					form.getJtcy3_nl(), jtcy3_gx, jtcy3_dw, jtcy4_xm,
					form.getJtcy4_nl(), jtcy4_gx, jtcy4_dw, pathName };
			sql = "insert into xszz_zxjsqb(xh,xn,nd,chjl,sfzh,xl,jg,xmdm,lxdh,jtdz,yhkh,ykth,fkh,sqly,hkxz,jtzrs,jtysr,rjysr,srly,yzbm,"
					+ "jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_dw,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_dw,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_dw,"
					+ "jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_dw,wjsclj) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			dao.runUpdate(sql, colValues);
		} else if ("国家励志奖学金".equals(xmlc)) {
			colValues = new String[] { form.getXh(), Base.currXn, Base.currNd,
					chjl, form.getSfzh(), xl, jg, form.getXmdm(),
					form.getLxdh(), jtdz, form.getYhkh(), form.getYkth(),
					form.getFkh(), sqly, hkxz, form.getJtzrs(),
					form.getJtysr(), form.getRjysr(), srly, form.getYzbm(),
					pathName };
			sql = "insert into xszz_lzjxjsqb(xh,xn,nd,chjl,sfzh,xl,jg,xmdm,lxdh,jtdz,yhkh,ykth,fkh,sqly,hkxz,jtzrs,jtysr,rjysr,srly,yzbm,wjsclj) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			dao.runUpdate(sql, colValues);
		}
		return "yes";
	}
	
	/*
	 * 拼查询条件
	 */
	public String getQuery(XcxyXszzForm form){
		StringBuffer sb = new StringBuffer(" ");
		if(!Base.isNull(form.getXn())){
			sb.append(" and xn='");
			sb.append(form.getXn());
			sb.append("'");
		}
		if(!Base.isNull(form.getNj())){
			sb.append(" and nj='");
			sb.append(form.getNj());
			sb.append("'");
		}
		if(!Base.isNull(form.getXydm())){
			sb.append(" and xydm='");
			sb.append(form.getXydm());
			sb.append("'");
		}
		if(!Base.isNull(form.getZydm())){
			sb.append(" and zydm='");
			sb.append(form.getZydm());
			sb.append("'");
		}
		if(!Base.isNull(form.getBjdm())){
			sb.append(" and zydm='");
			sb.append(form.getBjdm());
			sb.append("'");
		}
		if(!Base.isNull(form.getXh())){
			sb.append(" and xh like '%");
			sb.append(form.getXh());
			sb.append("%'");
		}
		if(!Base.isNull(form.getXm())){
			sb.append(" and xm like '%");
			sb.append(form.getXm());
			sb.append("%'");
		}
		if(!Base.isNull(form.getXxsh())){
			sb.append(" and xxsh='");
			sb.append(form.getXxsh());
			sb.append("'");
		}
		if(!Base.isNull(form.getXysh())){
			sb.append(" and xysh='");
			sb.append(form.getXysh());
			sb.append("'");
		}
		if(!Base.isNull(form.getXmdm())){
			sb.append(" and xmdm='");
			sb.append(form.getXmdm());
			sb.append("'");
		}
		return sb.toString();
	}
	
	/*
	 * 审核查询
	 */
	public List<String[]> queryShInfo_db(String[] cols,XcxyXszzForm form,String tableName,String userType){	
		String sql = "";
		if(!Base.isNull(form.getXmdm())){
			if("xy".equals(userType)){
				sql ="select xh||xn||xmdm pk,a.* from "+tableName+" a where 1=1 "+getQuery(form);
			}else{
				if("query".equals(form.getAct())){
					sql ="select xh||xn||xmdm pk,a.* from "+tableName+" a where 1=1 "+getQuery(form);
				}else{
					sql ="select xh||xn||xmdm pk,a.* from "+tableName+" a where a.xysh='通过' "+getQuery(form);
				}
			}
		}else{
			sql = "select * from (select xh||xn||xmdm pk,xmdm,xn,xh,xm,nj,xymc,zymc,bjmc,xmmc,xysh,xxsh from view_xszz_jxjsqb where 1=1 "+getQuery(form)+
				  "union select xh||xn||xmdm pk,xmdm,xn,xh,xm,nj,xymc,zymc,bjmc,xmmc,xysh,xxsh from view_xszz_lzjxjsqb  where 1=1 "+getQuery(form)+
				  "union select xh||xn||xmdm pk,xmdm,xn,xh,xm,nj,xymc,zymc,bjmc,xmmc,xysh,xxsh from view_xszz_zxjsqb where 1=1 "+getQuery(form)+") order by xn,xmdm,xymc,zymc";
		}
				
		return dao.rsToVator(sql, new String[]{}, cols);
	}
	
	/*
	 * 批量保存学生资助申请
	 */
	public String excutePlsh_db(XcxyXszzForm form,String userType){
		String[] array = getOneZzxm_db(form.getXmdm(),new String[]{"xmlc"});
		String sql = "";
		try{
			if(array != null && array.length > 0){
				if("xy".equals(userType)){
					form.setXysh(DealString.toGBK(form.getXysh()));
					form.setXyshyj(DealString.toGBK(form.getXyshyj()));
					if("不通过".equals(form.getXysh())){
						form.setZzje("");
					}
					if("国家奖学金".equals(array[0])){
						sql = "update xszz_jxjsqb set xysh=?,xyshyj=?,zzje=? where instr(?,'!@!'||xh||xn||xmdm||'!@!') > 0 and xxsh='未审核'";
					}else if("国家助学金".equals(array[0])){
						sql = "update xszz_zxjsqb set xysh=?,xyshyj=?,zzje=?  where instr(?,'!@!'||xh||xn||xmdm||'!@!') > 0 and xxsh='未审核'";
					}else if("国家励志奖学金".equals(array[0])){
						sql = "update xszz_lzjxjsqb set xysh=?,xyshyj=?,zzje=?  where instr(?,'!@!'||xh||xn||xmdm||'!@!') > 0 and xxsh='未审核'";
					}
					dao.runUpdate(sql, new String[]{form.getXysh(),form.getXyshyj(),form.getZzje(),form.getTemp()});
				}else{
					form.setXxsh(DealString.toGBK(form.getXxsh()));
					form.setXxshyj(DealString.toGBK(form.getXxshyj()));
					if("不通过".equals(form.getXxsh())){
						form.setZzje("");
					}
					if("国家奖学金".equals(array[0])){
						sql = "update xszz_jxjsqb set xxsh=?,xxshyj=?,zzje=?  where instr(?,'!@!'||xh||xn||xmdm||'!@!') > 0";
					}else if("国家助学金".equals(array[0])){
						sql = "update xszz_zxjsqb set xxsh=?,xxshyj=?,zzje=?  where instr(?,'!@!'||xh||xn||xmdm||'!@!') > 0";
					}else if("国家励志奖学金".equals(array[0])){
						sql = "update xszz_lzjxjsqb set xxsh=?,xxshyj=?,zzje=?  where instr(?,'!@!'||xh||xn||xmdm||'!@!') > 0";
					}
					dao.runUpdate(sql, new String[]{form.getXxsh(),form.getXxshyj(),form.getZzje(),form.getTemp()});
				}
				return "yes";
			}else{
				return "no";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "no";
		}
	}
	
	/*
	 * 获得学生资助申请单个审核展现的信息
	 */
	public HashMap<String,String> getXssqInfo_db(XcxyXszzForm form){
		String[] array = getOneZzxm_db(form.getXmdm(), new String[]{"xmlc"});
		HashMap<String,String> map = new HashMap<String,String>();
		String sql ="";
		if(array != null){
			if("国家奖学金".equals(array[0])){
				sql = "select * from view_xszz_jxjsqb where xh||xn||xmdm=?";
			}else if("国家助学金".equals(array[0])){
				sql = "select * from view_xszz_zxjsqb where xh||xn||xmdm=?";
			}else if("国家励志奖学金".equals(array[0])){
				sql = "select * from view_xszz_lzjxjsqb where xh||xn||xmdm=?";
			}
		}	
		List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{form.getTemp()});
		if(list != null && list.size() > 0){
			map = list.get(0);
		}
		map.put("xmlc", array[0]);
		return map;
	}
	
	/*
	 * 获得学生所有资助获奖信息
	 */
	public List<HashMap<String,String>> getXsAllPrise_db(String xh,String xn){
		String query = " and xxsh=? and xh=? ";
		String[] inputcol = null;
		String sql = "";
		if(!Base.isNull(xn)){
			query += " and xn=? ";
			inputcol = new String[]{"通过",xh,xn,"通过",xh,xn,"通过",xh,xn};
			sql = "select xn,xmmc mc from view_xszz_jxjsqb where 1=1 "+query+" union select xn,xmmc mc from " +
			"view_xszz_zxjsqb where 1=1 "+query+" union select xn,xmmc mc from view_xszz_lzjxjsqb where 1=1 "+query;
		}else{
			inputcol = new String[]{"通过",xh,"通过",xh,"通过",xh,"通过",xh,"通过",xh};
			sql = "select xn,xmmc mc from view_xszz_jxjsqb where 1=1 "+query+" union select xn,xmmc mc from " +
			"view_xszz_zxjsqb where 1=1 "+query+" union select xn,xmmc mc from view_xszz_lzjxjsqb where 1=1 "+query
			+"union select xn,jxjmc mc from view_xsjxjb where 1=1 "+query+" union select xn,rychmc mc from view_xsrychb "+
			"where 1=1 "+query;
		}	
		return dao.getList(sql, inputcol, new String[]{"xn","mc"});
	}
	/*
	 * 获得学生违纪处分情况
	 */
	public List<HashMap<String,String>> getXsAllWjcf_db(String xh,String xn,String xq){	
		String query = "";
		String[] inputcol = null;
		if(!Base.isNull(xq)){
			query += " and xq=?";
			inputcol = new String[]{xn,xh,"通过",xq};
		}else{
			inputcol = new String[]{xn,xh,"通过"};
		}
		String sql = "select xn,cflbmc,cfyymc from view_wjcf where xn=? and xh=? and xxsh=? "+query;
		return dao.getList(sql, inputcol, new String[]{"xn","cflbmc","cfyymc"});
	}
	
	/*
	 * 单个保存学生资助申请审核
	 */
	public String excuteDgsh_db(XcxyXszzForm form,String userType){
		String sql = "";
		String[] array = getOneZzxm_db(form.getXmdm(), new String[] { "xmlc" });
		try {
			if (array != null && array.length > 0) {
				if ("xy".equals(userType)) {
					form.setXysh(DealString.toGBK(form.getXysh()));
					form.setXyshyj(DealString.toGBK(form.getXyshyj()));
					if ("不通过".equals(form.getXysh())) {
						form.setZzje("");
					}
					if ("国家奖学金".equals(array[0])) {
						sql = "update xszz_jxjsqb set xysh=?,xyshyj=?,zzje=? where xh||xn||xmdm=? ";
					} else if ("国家助学金".equals(array[0])) {
						sql = "update xszz_zxjsqb set xysh=?,xyshyj=?,zzje=?  where xh||xn||xmdm=? ";
					} else if ("国家励志奖学金".equals(array[0])) {
						sql = "update xszz_lzjxjsqb set xysh=?,xyshyj=?,zzje=?  where xh||xn||xmdm=? ";
					}
					dao.runUpdate(sql, new String[] { form.getXysh(),
							form.getXyshyj(), form.getZzje(), form.getTemp() });
				} else {
					form.setXxsh(DealString.toGBK(form.getXxsh()));
					form.setXxshyj(DealString.toGBK(form.getXxshyj()));
					if ("不通过".equals(form.getXxsh())) {
						form.setZzje("");
					}
					if ("国家奖学金".equals(array[0])) {
						sql = "update xszz_jxjsqb set xxsh=?,xxshyj=?,zzje=?  where xh||xn||xmdm=?";
					} else if ("国家助学金".equals(array[0])) {
						sql = "update xszz_zxjsqb set xxsh=?,xxshyj=?,zzje=?  where  xh||xn||xmdm=?";
					} else if ("国家励志奖学金".equals(array[0])) {
						sql = "update xszz_lzjxjsqb set xxsh=?,xxshyj=?,zzje=?  where xh||xn||xmdm=?";
					}
					dao.runUpdate(sql, new String[] { form.getXxsh(),
							form.getXxshyj(), form.getZzje(), form.getTemp() });
				}
				return "yes";
			} else {
				return "no";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}	
	}
	
	/*
	 * 数据导出sql
	 */
	public String getExpSql_db(XcxyXszzForm form,String tableName,String condsql){
		String query = "";
		if("yes".equals(condsql)){
			query = " and xysh='通过' ";
		}
		String sql = "select * from "+tableName+" where 1=1 "+query+getQuery(form);
		return sql;
	}
	
	/*
	 * 获得学生的基本信息
	 */
	public HashMap<String,String> getXsxx_db(String xxmc,XcxyXszzForm form){
		HashMap<String,String> map = new HashMap<String,String>();
		StringBuffer sb = new StringBuffer();
		sb.append("select xh,xm,csrq,xb,nj||'-09-01' rxrq,mzmc,xymc,zymc,bjmc,xz,ssbh,zzmmmc ");
		sb.append("from view_xsxxb where xh=?");
		List<HashMap<String,String>> list = dao.getListNotOut(sb.toString(), new String[]{form.getXh()});
		if(list != null && list.size() > 0){
			map = list.get(0);
			map.put("xxmc", xxmc);
		}
		return map;
	}
	
	/*
	 * 获得学生成绩信息
	 */
	public HashMap<String,String> getXscj_db(XcxyXszzForm form){
		StringBuffer sb = new StringBuffer();
		HashMap<String,String> map = new HashMap<String,String>();
		sb.append("select pjcjpm||'/'||zyrs pjcjpm,zcj,zcjpm||'/'||zyrs zcjpm from (select a.xh,(rank() over (partition by xn,a.xq,b.zydm order ");
		sb.append("by to_number(pjcj) desc nulls last)) pjcjpm,a.zcj,(rank() over (partition by xn,a.xq,b.zydm order by to_number(zcj) desc nulls last)) ");
		sb.append("zcjpm,(select count(xh) from view_xsjbxx where zydm=(select zydm from view_xsjbxx where xh=a.xh) group by zydm) zyrs  from ");
		sb.append("xcxy_pjxscjb a,(select xh,zydm from  view_xsjbxx a where exists(select 1 from view_xsjbxx where xh=? and zydm=a.zydm)) b where a.xh=b.xh and a.xn=? and a.xq=? ) where xh=?");
		List<HashMap<String,String>> list = dao.getListNotOut(sb.toString(), new String[]{form.getXh(),form.getXn(),form.getXq(),form.getXh()});
		if(list != null && list.size() > 0){
			map = list.get(0);
		}
		return map;
	}
	/*
	 * 获得在校学生数，本科，少数民族，农村户口人数
	 */
	public String[] getXsrs_db(String nd,String xmdm,String tableName){
		StringBuffer sb = new StringBuffer();
		sb.append("select (select count(xh) from view_xsxxb where sfyby is null or sfyby='否') zxrs,");
		sb.append("(select count(xh) from "+tableName+" where xl='本科' and nd=? and xmdm=? and xxsh='通过') bksrs,(select count(xh)");
		sb.append("from "+tableName+" where mzmc<>'汉族' and mzmc<>'未确定' and nd=? and xmdm=? and xxsh='通过') ssmzrs,");
		sb.append("(select count(xh) from "+tableName+" where hkxz='农村' and nd=? and xmdm=? and xxsh='通过') nzhkrs from dual");
		return dao.getOneRs(sb.toString(), new String[]{nd,xmdm,nd,xmdm,nd,xmdm}, new String[]{"zxrs","bksrs","ssmzrs","nzhkrs"});	
	}
	
	/*
	 * 获得助学金人数和金额
	 */
	public List<String[]> getZxjrsAndJe_db(String nd,String xmdm,String tableName){
		StringBuffer sb = new StringBuffer();
		sb.append("select count(xh) rs,sum(nvl(zzje,0)) je from  ");
		sb.append(tableName);
		sb.append(" where nd=? and xmdm=? and xxsh='通过' group by zzje order by zzje");
		return dao.rsToVatorNotOut(sb.toString(), new String[]{nd,xmdm});	
	}
	
	/*
	 * 获得助学金人数上报
	 */
	public List<HashMap<String,String>> getZxjSb_db(XcxyXszzForm form,String userType){
		StringBuffer sb = new StringBuffer();
		sb.append("select rownum,a.* from (select xm,xb,sfzh,xymc,zymc,xh,decode(nvl(mzmc,'汉族'),'汉族','','未确定','','1') mz,decode(hkxz,'农村','1','') hk,");
		sb.append("decode(xl,'本科','1','') xl,(select dc from (select rownum dc,zzje from xszz_zzjeb where xmdm=? ");
		sb.append("order by zzje,rownum) where zzje=a.zzje) dc,zzje from ");
		sb.append(form.getTableName());
		sb.append(" a where nd=? and xmdm=? and xxsh='通过'");
		if("xy".equals(userType)){
			sb.append(" and xydm='");
			sb.append(form.getXydm());
			sb.append("' ");
		}
		sb.append("order by xydm,zydm) a");
		return dao.getListNotOut(sb.toString(), new String[]{form.getXmdm(),form.getNd(),form.getXmdm()});	
	}
	/*
	 * 获得院系国家奖学金/励志奖学金学院发放列表
	 */
	public List<HashMap<String, String>> getXxFfList(String nd,
			String tableName, String userDep,String xmdm) {
		String sql = "select xm,xb,nj || zymc zymc,sfzh,yhkh,zzje from "
				+ tableName + " where nd = ? and xydm=? and xxsh = '通过' and xmdm=?";
		String[] inputcol = new String[] { nd, userDep,xmdm };

		return dao.getList(sql, inputcol, new String[] { "xm", "xb", "zymc",
				"sfzh", "yhkh", "zzje" });
	}
	
	/*
	 * 获得院系国家奖学金/励志奖学金初审列表
	 */
	public List<HashMap<String, String>> getXxCsList(String nd,
			String tableName, String userDep,String xmdm) {
		String sql = "select xm,sfzh,xymc,zymc,xh,xb,decode(mzmc,'未确定','',mzmc) mzmc,rxrq from "
				+ tableName + " where nd = ? and xydm like ? and xxsh = '通过' and xmdm=? order by xymc,zymc";
		String[] inputcol = new String[] { nd, userDep, xmdm };

		return dao.getList(sql, inputcol, new String[] { "xh", "xm", "xb",
				"xymc", "zymc", "sfzh", "mzmc", "rxrq" });
	}
	
	/*
	 * 获得院系国家奖学金/励志奖学金初审列表
	 */
	public List<HashMap<String, String>> getXxFfList(String nd,
			String tableName,String xmdm) {
		String sql = "select xymc, zzje, num, (zzje * num) zje from"
				+ " (select t.xymc, t.zzje, count(xh) num from " + tableName
				+ " t where t.nd = ? and xxsh = '通过' and xmdm=? group by xymc, zzje)";
		String[] inputcol = new String[] { nd,xmdm };

		return dao.getList(sql, inputcol, new String[] { "xymc", "zzje", "num",
				"zje" });
	}
	
	/*
	 * 获得学生处助学金列表
	 */
	public List<HashMap<String, String>> getXscZxjList(String nd,String xmdm,String tableName) {
		String sql = "select xymc, one+two+three num,one,two,three,to_char(zje/10000,'0.00') zje"
				+ " from(select xymc,sum(one)one,sum(two)two,sum(three)three,sum(zzje)zje"
				+ " from(select t.xymc,t.zzje,case when t.jedj=1then 1 else 0 end one,"
				+ " case when t.jedj=2 then 1 else 0 end two,case when t.jedj=3 then"
				+ " 1 else 0 end three from(select a.*,b.jedj from "+tableName+" a,"
				+ " (select t.zzje,rownum jedj from(select distinct(t.zzje) from"
				+ " xszz_zzjeb t where t.xmdm=? order by zzje)t)b where a.zzje=b.zzje and a.nd=?"
				+ " and a.xxsh='通过' and a.xmdm=?)t)t group by xymc)";
		String[] inputcol = new String[] { xmdm,nd,xmdm };

		return dao.getList(sql, inputcol, new String[] { "xymc", "num", "one",
				"two", "three", "zje" });
	}	
	/**
	 * 广州助学金名单打印信息
	 * @return
	 */
	public List<HashMap<String,String>> getGzzxjmdbPtInfo(String nd,String xmdm,String tableName,String userType,String xydm){
		DAO dao  = DAO.getInstance();
		String[]colList = new String[]{"r","xm","xb","mzmc","jtdz","jdqk"};
		String query = "";
		if("xy".equals(userType)){
			query = " and xydm='"+xydm+"' ";
		}
		String sql = "select rownum r,xm,xb,decode(mzmc,'未确定','',mzmc) mzmc,jtdz,xymc||'、'||zymc||'、'||bjmc jdqk from "+tableName+" where nd=? and xxsh='通过' and xmdm=? "+query;
		return dao.getList(sql,new String[]{nd,xmdm},colList);
		
	}
	/**
	 * 广州助学金发放打印信息
	 * @return
	 */
	public List<HashMap<String,String>> getJxjxyffbPtInfo(String nd,String xmdm,String tableName,String userType,String xydm){
		DAO dao  = DAO.getInstance();
		String[]colList = new String[]{"r","xm","xymc","njzy","zzje"};
		String query = "";
		if("xy".equals(userType)){
			query = " and xydm='"+xydm+"' ";
		}
		String sql = "select rownum r,xm,xymc,nj||zymc njzy,zzje from "+tableName+" where nd=? and xxsh='通过' and xmdm=? "+query;
		return dao.getList(sql,new String[]{nd,xmdm},colList);
		
	}
	/**
	 * 加拿大福慧基金会励志奖学金发放表
	 * @return
	 */
	public List<HashMap<String,String>> getJndlzjxjffbPtInfo(String nd,String xmdm,String tableName,String userType,String xydm){
		DAO dao  = DAO.getInstance();
		String[]colList = new String[]{"r","xm","xymc","njzy","zzje"};
		String query = "";
		if("xy".equals(userType)){
			query = " and xydm='"+xydm+"' ";
		}
		String sql = "select rownum r,xm,xymc,nj||zymc njzy,zzje from "+tableName+" where nd=? and xxsh='通过' and xmdm=? "+query;
		return dao.getList(sql,new String[]{nd,xmdm},colList);
		
	}
	/**
	 * 加拿大福慧基金会励志奖学金发放表
	 * @return
	 */
	public List<HashMap<String,String>> getJndlzjxjmdbPtInfo(String nd,String xmdm,String tableName,String userType,String xydm){
		DAO dao  = DAO.getInstance();
		String[]colList = new String[]{"r","xm","xymc","njzy","zzje"};
		String query = "";
		if("xy".equals(userType)){
			query = " and xydm='"+xydm+"' ";
		}
		String sql = "select rownum r,xm,xymc,nj||zymc njzy,zzje from "+tableName+" where nd=? and xxsh='通过' and xmdm=? "+query;
		return dao.getList(sql,new String[]{nd,xmdm},colList);
		
	}
	
	/**
	 * 国家助学金发放表打印信息(院系统计)
	 * @return
	 */
	public List<HashMap<String,String>> getXsZxjList_yx(String nd,String xmdm,String xydm,String tableName){
		DAO dao  = DAO.getInstance();
		String[]colList = new String[]{"rownum","xm","zymc","sfzh","yhkh","dc","zzje"};
		StringBuffer sql = new StringBuffer();
		sql.append(" select rownum,a.* from (select xm,sfzh,yhkh,zymc, (select dc from (select " );
		sql.append("rownum dc,zzje from xszz_zzjeb where xmdm=? order by zzje,rownum) where zzje=a.zzje) dc,zzje from  ");
		sql.append(tableName);
		sql.append(" a where nd=? and xydm=? and xmdm=? and xxsh='通过' order by zymc)a");
		return dao.getList(sql.toString(),new String[]{xmdm,nd,xydm,xmdm},colList);
	}

}
