package xgxt.rcgl.nbzy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.szdw.utils.MakeQuery;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

public class MpglDAO extends DAO {
	
	public ArrayList<String[]> commonQuery(String tableName,String query,String [] inPutList,
			String [] colList,String sql) {
		DAO dao = DAO.getInstance();
    //    查询用 获得数组的通用方法
		ArrayList<String[]> rs = null;
		//取到colList的长度
		int size = colList.length-1;
		if(sql.equalsIgnoreCase("")){
			StringBuffer sqlBuffer = new StringBuffer("select ");
			for(int i = 0; i<(size);i++){
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName); 
			sqlBuffer.append(query);
			rs = dao.rsToVator(sqlBuffer.toString(), inPutList, colList);
		}else{
			rs = dao.rsToVator(sql, inPutList, colList);
		}
		return rs;
	}
	
	public HashMap<String, String> commonQueryOne(String tableName,String [] colList,String pk,String pkValue) {
	    //    通过主键查询单个数据用 获得HashMap<String, String>形式的通用方法
			DAO dao = DAO.getInstance();
			HashMap<String, String> map = new HashMap<String, String>();
			int size = colList.length-1;
			if(pkValue.equalsIgnoreCase("")){
				for(int i=0;i<size;i++){
					map.put(colList[i], "");
				}
			}else{
			StringBuffer sqlBuffer = new StringBuffer("select ");
			for(int i = 0; i<size;i++){
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
				}
				sqlBuffer.append(colList[size]);
				sqlBuffer.append(" from ");
				sqlBuffer.append(tableName); 
				sqlBuffer.append(" where ");
				sqlBuffer.append(pk);
				sqlBuffer.append("=?");
				String [] rsTmp = dao.getOneRs(sqlBuffer.toString(), new String []{ pkValue }, colList);
				for(int i=0;i<=size;i++){
					map.put(colList[i], (rsTmp!=null)? rsTmp[i]:"");
				}
			}
			return map;
		}

	//户口信息查询
	public List<HashMap<String, String>> queryHkxx(MpglForm form) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "select a.*,rownum r from view_xshkgl a "+MakeQuery.makeCommanQuery(
				new String[]{"nj","xydm","zydm","bjdm","hkzt"},new String[]{"xh","xm"},form)+" order by nj,xydm,zydm,bjdm,xh ";
		sql = "select * from ("+sql+") where r<="+ (form.getPages().getStart()
					   + form.getPages().getPageSize()) + " and r> "+ form.getPages().getStart();
		return dao.getListNotOut(sql, new String[]{});
	}
	
	//户口信息查询总记录数
	public String queryHkxxCount(MpglForm form) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "select count(*) count from view_xshkgl a "+MakeQuery.makeCommanQuery(
				new String[]{"nj","xydm","zydm","bjdm","hkzt"},new String[]{"xh","xm"},form);
		return dao.getOneRs(sql, new String[]{},"count");
	}
	
	//获得学生的的户口信息
	public HashMap<String,String> getXsHkxx_db(String xh) {
		DAO dao = DAO.getInstance();
		String sql = "select * from view_xshkgl where xh=?";
		try {
			List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{xh});
			if(list != null && list.size() > 0){
				return list.get(0);
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//获得学生的的基本信息
	public HashMap<String,String> getXsJbxx_db(String xh) {
		DAO dao = DAO.getInstance();
		String sql = "select a.xh,xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx a left join xshkgl b on a.xh=b.xh where a.xh=?";
		try {
			List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{xh});
			if(list != null && list.size() > 0){
				return list.get(0);
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//增加学生的的户口信息
	public boolean saveXsHkxx_db(MpglForm form) {
		DAO dao = DAO.getInstance();
		String hkdm = DealString.toGBK(form.getHkdm());
		String hkzt = DealString.toGBK(form.getHkzt());
		String qcdz = DealString.toGBK(form.getQcdz());
		String qyzhm = DealString.toGBK(form.getQyzhm());
		String sflq = DealString.toGBK(form.getSflq());
		String lqrxm = DealString.toGBK(form.getLqrxm());
		String lqrq = DealString.toGBK(form.getLqrq());
		String[] values = new String[]{form.getXh(),hkdm,hkzt,form.getQcsj(),qcdz,qyzhm,sflq,lqrxm,lqrq};
		String sql = "insert into xshkgl (xh,hkdm,hkzt,qcsj,qcdz,qyzhm,sflq,lqrxm,lqrq) values (?,?,?,?,?,?,?,?,?)";
		try {
			return dao.runUpdate(sql, values);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//修改学生的的户口信息
	public boolean updateXsHkxx_db(MpglForm form) {
		DAO dao = DAO.getInstance();
		String hkdm = DealString.toGBK(form.getHkdm());
		String hkzt = DealString.toGBK(form.getHkzt());
		String qcdz = DealString.toGBK(form.getQcdz());
		String qyzhm = DealString.toGBK(form.getQyzhm());
		String sflq = DealString.toGBK(form.getSflq());
		String lqrxm = DealString.toGBK(form.getLqrxm());
		String lqrq = DealString.toGBK(form.getLqrq());
		String[] values = new String[]{hkdm,hkzt,form.getQcsj(),qcdz,qyzhm,sflq,lqrxm,lqrq,form.getXh()};
		String sql = "update xshkgl set hkdm=?,hkzt=?,qcsj=?,qcdz=?,qyzhm=?,sflq=?,lqrxm=?,lqrq=? where xh=?";
		try {
			return dao.runUpdate(sql, values);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//删除学生的的户口信息
	public boolean deleteXsHkxx_db(String pkvals) {
		DAO dao = DAO.getInstance();
		String sql = "delete from xshkgl where instr(?,'!@!'||xh||'!@!') > 0";
		 try {
			return dao.runUpdate(sql, new String[]{pkvals});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//获得学生的的基本信息
	public HashMap<String,String> getXsSfzJbxx_db(String xh) {
		DAO dao = DAO.getInstance();
		String sql = "select a.xh,xm,xb,nj,xymc,zymc,bjmc,(select xqmc from xqdzb where xqdm=nvl(b.xq,?)) xqmc,nvl(b.xn,?) xn, xq,b.sqsj,b.sqly,b.bbzt," +
				"b.sflq,b.lqrxm,b.lqrq from view_xsjbxx a left join sfzbbsqb b on a.xh=b.xh and b.fdysh='未审核' and b.xn=? and b.xq=? where a.xh=?  and rownum=1 order by sqsj desc";
		try {
			List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{Base.currXq,Base.currXn,Base.currXn,Base.currXq,xh});
			if(list != null && list.size() > 0){
				return list.get(0);
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//学生是否为常住户口
	public boolean isCzhk_db(String xh) {
		DAO dao = DAO.getInstance();
		String sql = "select hkzt from xshkgl where xh=?";
		String hkzt = dao.getOneRs(sql, new String[]{xh},"hkzt");
		if(!Base.isNull(hkzt) && "常住人口".equals(hkzt)){
			return true;
		}else{
			return false;
		}

	}
	//学生补办申请增加(学生)
	public boolean saveXsBbsq_db(MpglForm form) {
		DAO dao = DAO.getInstance();
		String sqly = DealString.toGBK(form.getSqly());
		String sql = "";
		String[] col = null;
		if(Base.isNull(form.getSqsj())){
			sql = "insert into sfzbbsqb (xh,xn,xq,sqly) values (?,?,?,?)";
			col = new String[]{form.getXh(),Base.currXn,Base.currXq,sqly};
		}else{
			sql = "update sfzbbsqb set sqly=? where xh=? and sqsj=?";
			col = new String[]{sqly,form.getXh(),form.getSqsj()};
		}	
		try {
			return dao.runUpdate(sql, col);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//获得学生的的基本信息(教师)
	public HashMap<String,String> getXsxx_db(String xh) {
		DAO dao = DAO.getInstance();
		String sql = "select a.xh,xm,xb,nj,xymc,zymc,bjmc,? xn,? xqmc from view_xsjbxx a  where a.xh=?";
		try {
			List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{Base.currXn,Base.getDqxqmc(),xh});
			if(list != null && list.size() > 0){
				return list.get(0);
			}else{
				return new HashMap<String, String>();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new HashMap<String, String>();
		}
	}
	
	//学生补办申请增加(教师)
	public boolean saveXsBbsqByTeacher_db(MpglForm form) {
		DAO dao = DAO.getInstance();
		String sqly = DealString.toGBK(form.getSqly());
		String bbzt = DealString.toGBK(form.getBbzt());
		String sflq = DealString.toGBK(form.getSflq());
		String lqrxm = DealString.toGBK(form.getLqrxm());
		String lqrq = DealString.toGBK(form.getLqrq());
		String[] col = new String[]{form.getXh(),Base.currXn,Base.currXq,sqly,bbzt,sflq,lqrxm,lqrq};
		String sql = "insert into sfzbbsqb (xh,xn,xq,sqly,bbzt,sflq,lqrxm,lqrq) values (?,?,?,?,?,?,?,?)";	
		try {
			return dao.runUpdate(sql, col);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//获得学生的的身份证补办信息
	public HashMap<String,String> getXsSfzbbxx_db(String pk) {
		DAO dao = DAO.getInstance();
		String sql = "select * from view_sfzbbsqb where xh||sqsj=?";
		try {
			List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{pk});
			if(list != null && list.size() > 0){
				return list.get(0);
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//修改学生的的身份证申请信息
	public boolean updateXsSfzsqxx_db(MpglForm form) {
		DAO dao = DAO.getInstance();
		String sqly = DealString.toGBK(form.getSqly());
		String bbzt = DealString.toGBK(form.getBbzt());
		String sflq = DealString.toGBK(form.getSflq());
		String lqrxm = DealString.toGBK(form.getLqrxm());
		String lqrq = DealString.toGBK(form.getLqrq());
		String[] values = new String[]{sqly,bbzt,sflq,lqrxm,lqrq,form.getXh(),form.getSqsj()};
		String sql = "update sfzbbsqb set sqly=?,bbzt=?,sflq=?,lqrxm=?,lqrq=? where xh=? and sqsj=?";
		try {
			return dao.runUpdate(sql, values);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//删除学生身份证申请信息
	public boolean deleteXsSfzsqxx_db(String pkvals) {
		DAO dao = DAO.getInstance();
		String sql = "delete from sfzbbsqb where instr(?,'!@!'||xh||sqsj||'!@!') > 0";
		 try {
			return dao.runUpdate(sql, new String[]{pkvals});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//身份证补办审核查询
	public List<HashMap<String, String>> querySfzbbSh_db(MpglForm form,
			                                             String userName,
			                                             String userType) 
			                                             throws Exception {
		DAO dao = DAO.getInstance();
		Pages page = form.getPages();
		String query = " ";
		String whereSql = MakeQuery.makeCommanQuery(
							new String[]{"xn","xq","xydm","zydm","bjdm","bbzt"},
							new String[]{"xh","xm"},
							form).toString();
		String[] colval = null;
		if("fdy".equals(userType)){
			query = " and exists(select 1 from fdybjb where zgh=? and bjdm = a.bjdm)";
			colval = new String[]{userName};
		}else{
			query = " and fdysh ='通过'";
			colval = new String[]{};
		}
		
		String sql = StringUtils.joinStr("select a.* from (",
										 "select rownum r, xh||sqsj pk,a.* from view_sfzbbsqb a ",
										 whereSql,
				                         query,
				                         "  order by xn,xq,xydm,zydm,bjdm,xh ",
				                         ") a where r>",
				                         page.getStart()+"",
				                         " and r<=",
				                         (page.getStart() + page.getPageSize())+"",
				                         "");
		page.setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) num from view_sfzbbsqb a " + whereSql + query, colval, "num")));
		return dao.getListNotOut(sql, colval);
	}
	
	//身份证补办审核查询
	public List<HashMap<String, String>> querySfzbb_db(MpglForm form,
			                                             String userName,
			                                             String userType) 
			                                             throws Exception {
		DAO dao = DAO.getInstance();
		Pages page = form.getPages();
		String query = " ";
		String whereSql = MakeQuery.makeCommanQuery(
							new String[]{"xn","xq","xydm","zydm","bjdm","bbzt"},
							new String[]{"xh","xm"},
							form).toString();
		String[] colval = null;
		if("fdy".equals(userType)){
			query = " and exists(select 1 from fdybjb where zgh=? and bjdm = a.bjdm)";
			colval = new String[]{userName};
		}else{
			colval = new String[]{};
		}
		
		String sql = StringUtils.joinStr("select a.* from (",
										 "select rownum r, xh||sqsj pk,a.* from view_sfzbbsqb a ",
										 whereSql,
				                         query,
				                         "  order by xn,xq,xydm,zydm,bjdm,xh ",
				                         ") a where r>",
				                         page.getStart()+"",
				                         " and r<=",
				                         (page.getStart() + page.getPageSize())+"",
				                         "");
		page.setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) num from view_sfzbbsqb a " + whereSql + query, colval, "num")));
		return dao.getListNotOut(sql, colval);
	}
	
	//身份证补办审核
	public boolean sfzbbSh_db(String pkvals,String fdysh) {
		DAO dao = DAO.getInstance();
		String sh = "";
		String bbzt = "";
		String sql = "update sfzbbsqb set fdysh=? ,bbzt=? where instr(?,'!@!'||xh||sqsj||'!@!') > 0";
		if("tg".equals(fdysh)){
			sh = "通过";
			bbzt = "正在补办中";			
		}else{
			sh = "不通过";
		}
		
		try {
			return dao.runUpdate(sql, new String[]{sh, bbzt, pkvals});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//身份证补办审核结果查询（学生）
	public List<HashMap<String, String>> sfzbbShxscx_db(String xh) {
		DAO dao = DAO.getInstance();
		String sql = "select a.*,xh||sqsj pk from view_sfzbbsqb a where xh=? order by sqsj desc";
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	/**
	 * 获得户口须知
	 * */
	public String getHkxz(String lb) {
		DAO dao = DAO.getInstance();
		String sql = "select nr from xzb where dm=?";
		return dao.getOneRs(sql, new String[]{lb}, "nr");
	}
}
