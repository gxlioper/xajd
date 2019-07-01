package xgxt.szdw.bjlh.fdykh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.szdw.bjlh.SzkhCssz;
import xgxt.szdw.bjlh.cssz.BjlhCsszDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

import com.zfsoft.utils.StringUtil;

public class BjlhFdykhDAO extends CommDAO {
	//辅导员考核对应表
	public List<String[]> getTableList(BjlhFdykhForm myForm,
			HttpServletRequest request) throws Exception{
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		//权限控制
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser(request, "","bmdm", null); 	//学院用户
		
		String sql = "select rownum r, a.* from (select a.*,c.khxzcy , nvl2(c.khxzcy , '是' , '否') sffz from " +
				"(select distinct(a.zgh),b.xm,b.szbm bmdm,b.bmmc from view_fdybjb a left join view_fdyxx b on a.zgh = b.zgh) a " +
				"left join (select a.zgh,to_char(wm_concat(distinct(b.xm))) khxzcy from XG_SZDW_BJLH_FDYKHRYB a " +
				"left join yhb b on a.khryzgh = b.yhm group by zgh) c on a.zgh = c.zgh) a where 1 = 1";

		String[] output = new String[] { "zgh", "xm", "bmmc","khxzcy"};
		return CommonQueryDAO.commonQuery(sql, searchTj+searchTjQx, inputV,
				output, myForm);
	}
	
	/**
	 * 查询用户
	 * @return
	 */
	public List<String[]> queryYh(BjlhFdykhForm myForm){
		String[]colList = {"yhm","xm" ,"bmmc"};
		
		List<String[]> list = new ArrayList<String[]>();
		
		StringBuilder sql = new StringBuilder();
		
		
		sql.append(" select rownum r,a.* from (select a.yhm,a.xm,b.bmmc,a.szbm bmdm")
		.append(" from yhb a left join ")
		.append(" zxbz_xxbmdm b on a.szbm=b.bmdm )a ");
		
		String[] colLikeList = new String[]{"yhm", "xm"};
		String[] columList = new String[]{"bmdm"};
		
		MakeQuery makeQuery = new MakeQuery();
		
		try {
			makeQuery.makeQuery(columList, colLikeList, myForm);
			String[] inputs = makeQuery.getInputList();
			
			sql.append(makeQuery.getQueryString());
			
			list = CommonQueryDAO.commonQuery(sql.toString(), "", inputs , colList, myForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//保存辅导员考核考核对象设置
	public boolean saveFdyKh(List<String[]> params, List<String[]> delParams) {
		DAO dao = DAO.getInstance();
		boolean flag = false;
		String sql = "insert into xg_szdw_bjlh_fdykhryb(khryzgh,zgh) values(?,?)";
		String delSql = "delete xg_szdw_bjlh_fdykhryb where zgh=?";
		
		try {
			flag = dao.checkBatchResult(dao.runBatch(delSql, delParams));
			
			if(flag){
				int[] rs = dao.runBatch(sql, params);
				flag = dao.checkBatchResult(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 获取辅导员考核列表
	 * @param model
	 * @return
	 */
	public List<String[]> getFdykhList(BjlhFdykhForm model) throws Exception{
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		List<String[]> list = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("select rownum r,a.*,(case when pfdx='xs' then '学生' else '考核小组' end) pfdxmc from xg_szdw_bjlh_fdykhb a where 1=1 ");
		
		String[] outputs = new String[]{"khbid","xn", "khbid", "khbmc", "pfdxmc", "sfqy"};
		list = CommonQueryDAO.commonQuery(sql.toString(), searchTj, inputV, outputs, model);
		
		return list;
	}
	
	/**
	 * 保存考核表信息
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public String saveKhbInfo(BjlhFdykhForm model,String type) throws Exception{
		DAO dao=new DAO();
		boolean b=false;
		String sql="";
		if("add".equals(type)){
			//首先验证对应年度评分对象是否已经存在
			sql="select count(1) num from xg_szdw_bjlh_fdykhb where xn=? and pfdx=? ";
			String num=dao.getOneRs(sql, new String[]{model.getXn(),model.getPfdx()}, "num");
			if(!"0".equals(num)){
				return model.getXn()+"年度中对应的评分对象考核表已存在！";
			}
			//数据保存
			sql="insert into xg_szdw_bjlh_fdykhb(khbid,xn,khbmc,pfdx,rq,sfqy) " +
					"values(seq_bjlh_fdycpwj.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'),?)";
			b=dao.runUpdate(sql, new String[]{model.getXn(),model.getKhbmc(),model.getPfdx(),"否"});
			return b?"保存成功！":"保存失败！";
		}else if("update".equals(type)){
			sql="update xg_szdw_bjlh_fdykhb set khbmc=? where khbid=?";
			b=dao.runUpdate(sql, new String[]{model.getKhbmc(),model.getKhbid()});
			return b?"修改成功！":"修改失败！";
		}
		return "参数错误";
	}
	
	/**
	 * 删除考核表信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteKhbInfo(BjlhFdykhForm model) throws Exception{
		DAO dao = DAO.getInstance();
		String khbid=model.getKhbid();
		String[] sqls=new String[2];
		sqls[0]="delete from xg_szdw_bjlh_fdykhb where khbid='"+khbid+"'";
		sqls[1]="delete from xg_szdw_bjlh_fdykhxmb where khbid='"+khbid+"'";
		return dao.saveArrDate(sqls);
	}
	
	/**
	 * 验证考核表权限
	 * @param model
	 * @return
	 */
	public String checkKhbQx(BjlhFdykhForm model){
		DAO dao = DAO.getInstance();
		String sql="select count(1) num from xg_szdw_bjlh_fdykhpfb where khbid=?";
		String num=dao.getOneRs(sql, new String[]{model.getKhbid()}, "num");
		return "0".equals(num)?"":"考核表已作答！";
	}
	
	/**
	 * 复制考核表
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean copyKhbInfo(BjlhFdykhForm model) throws Exception{
		DAO dao = DAO.getInstance();
		String khbid=model.getKhbid();
		String xn=model.getXn();
		String khbmc=model.getKhbmc();
		
		String[] sqls=new String[2];
		String sql="select count(1) num from xg_szdw_bjlh_fdykhb where xn=? and pfdx=? ";
		String num=dao.getOneRs(sql, new String[]{xn,model.getPfdx()}, "num");
		if(!"0".equals(num)){
			return false;
		}
		sqls[0]="insert into xg_szdw_bjlh_fdykhb(khbid,xn,khbmc,pfdx,rq,sfqy) "+
				"select seq_bjlh_fdycpwj.nextval,'"+xn+"','"+khbmc+"',pfdx,to_char(sysdate,'yyyy-mm-dd'),'否' " +
				"from xg_szdw_bjlh_fdykhb where khbid='"+khbid+"'";
		sqls[1]="insert into xg_szdw_bjlh_fdykhxmb(xmid,khbid,yjzb,ejzb,khnr,fzqj,pflx,xssx) "+
				"select seq_bjlh_fdycpwj.nextval,(select max(to_number(khbid)) from xg_szdw_bjlh_fdykhb),yjzb,ejzb,khnr,fzqj,pflx,xssx "+ 
				"from xg_szdw_bjlh_fdykhxmb where khbid='"+khbid+"'";
		return dao.saveArrDate(sqls);
	}

	/**
	 * 考核表启用状态维护
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String sfqyKhb(BjlhFdykhForm model) throws Exception{
		DAO dao=new DAO();
		String msg="";
		String sql="";
		if("是".equals(model.getSfqy())){
			sql="select count(1) num from xg_szdw_bjlh_fdykhb a where a.sfqy='是' " +
				"and exists (select 1 from xg_szdw_bjlh_fdykhb x where x.xn=a.xn and x.pfdx=a.pfdx and x.khbid=?)";
			if(!"0".equals(dao.getOneRs(sql, new String[]{model.getKhbid()}, "num"))){
				msg="本学年对应评分对象已有问卷启用！";
				return msg;
			}
		}
		sql="update xg_szdw_bjlh_fdykhb set sfqy=? where khbid=?";
		msg=dao.runUpdate(sql, new String[]{model.getSfqy(),model.getKhbid()})?"操作成功！":"操作失败！";
		return msg;
	}
	
	/**
	 * 获取辅导员考核项目列表
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getFdykhXmList(BjlhFdykhForm model){
		DAO dao=new DAO();
		String sql="select a.*,b.fz df from xg_szdw_bjlh_fdykhxmb a " +
				"left join XG_SZDW_BJLH_FDYKHPFB b on a.xmid = b.xmid and b.yhm=? and b.fdyid=? " +
				"where a.khbid=? order by to_number(xssx)";
		System.out.println(sql.toString());
		System.out.println(model.getYhm());
		System.out.println(model.getFdyid());
		System.out.println(model.getKhbid());
		
		return dao.getListNotOut(sql, new String[]{model.getYhm(),model.getFdyid(),model.getKhbid()});
	}
	
	/**
	 * 保存考核表项目信息
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public String saveKhbXmxx(BjlhFdykhForm model,String type) throws Exception{
		String msg="参数错误";
		String sql="";
		String[] input;
		if("add".equals(type)){
			sql="insert into xg_szdw_bjlh_fdykhxmb(xmid,khbid,yjzb,ejzb,khnr,fzqj,pflx,xssx) values(seq_bjlh_fdycpwj.nextval,?,?,?,?,?,?,?)";
			input=new String[]{model.getKhbid(),model.getYjzb(),model.getEjzb(),model.getKhnr(),model.getFzqj(),
					model.getPflx(),model.getXssx()};
		}else if("update".equals(type)){
			sql="update xg_szdw_bjlh_fdykhxmb set yjzb=?,ejzb=?,khnr=?,fzqj=?,pflx=?,xssx=? where khbid=? and xmid=?";
			input=new String[]{model.getYjzb(),model.getEjzb(),model.getKhnr(),model.getFzqj(),model.getPflx(),
					model.getXssx(),model.getKhbid(),model.getXmid()};
		}else{
			return msg;
		}
		DAO dao=new DAO();
		boolean b=dao.runUpdate(sql,input);
		msg=b?"操作成功！":"操作失败！";
		return msg;
	}
	
	/**
	 * 删除考核表项目信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean deleteKhbXmxx(BjlhFdykhForm model) throws Exception{
		DAO dao = DAO.getInstance();
		String sql="delete from xg_szdw_bjlh_fdykhxmb where khbid=? and xmid=?";
		return dao.runUpdate(sql, new String[]{model.getKhbid(),model.getXmid()});
	}
	
	/**
	 * 获取考核表项目信息单条记录
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getKhbXmxxOne(BjlhFdykhForm model){
		DAO dao = DAO.getInstance();
		String sql="select * from xg_szdw_bjlh_fdykhxmb where khbid=? and xmid=?";
		return dao.getMapNotOut(sql, new String[]{model.getKhbid(),model.getXmid()});
	}
	
	/**
	 * 获取考核表信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getKhbxx(BjlhFdykhForm model){
		DAO dao = DAO.getInstance();
		String sql="select * from xg_szdw_bjlh_fdykhb where khbid=?";
		return dao.getMapNotOut(sql, new String[]{model.getKhbid()});
	}
	
	/**
	 * 获取辅导员考核测评
	 * @param userName
	 * @param myForm
	 * @param request
	 * @return
	 */
	public List<String[]> getFdykhcpList(String userName, BjlhFdykhForm myForm,
			HttpServletRequest request) throws Exception {
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		String sql = "select rownum r, a.*  from (select a.xn, a.zgh,a.khbid, b.xm,b.szbm bmdm, b.bmmc, case " +
				"when nvl(c.numCount, '0') = '0' then '否' else '是' end sfkp, c.zf " +
				" from (select * from XG_SZDW_BJLH_FDYKHRYB " +
				"join (select * from XG_SZDW_BJLH_FDYKHB where pfdx='pfxz') on khryzgh = '"+userName+
				"') a left join view_fdyxx b on a.zgh = b.zgh " +
				"left join (select fdyid, yhm,khbid,count(*) numCount, sum(sjfz) zf from " +
				"(select a.*, case when b.pflx='减分' then to_char(-(to_number(a.fz))) else a.fz end sjfz " +
				"from XG_SZDW_BJLH_FDYKHPFB a left join XG_SZDW_BJLH_FDYKHXMB b on a.xmid = b.xmid)group by fdyid, yhm, khbid) c " +
				"on a.khryzgh = c.yhm and fdyid = b.zgh and a.khbid = c.khbid order by xn, zgh ) a where 1 = 1 ";

		String[] output = new String[] { "xn","zgh", "xm", "bmmc","sfkp","zf","khbid"};
		return CommonQueryDAO.commonQuery(sql, searchTj, inputV,
				output, myForm);
	}
	

	/**
	 * 获取统计
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getFdyKhTjInfo(BjlhFdykhForm myForm,HttpServletRequest request) throws Exception{
		HashMap<String,String> csMap=new BjlhCsszDAO().getCssz();//获取参数
		String jskhpfbl="";//教师考核评分比例
		String xskhpfbl="";//学生考核评分比例
		if("xs".equalsIgnoreCase(SzkhCssz.KHRY)){//只有学生考核时比例分配
			jskhpfbl="0";//教师考核评分比例
			xskhpfbl="100";//学生考核评分比例
		} else if("cpz".equalsIgnoreCase(SzkhCssz.KHRY)){//只有参评组考核时比例分配
			jskhpfbl="100";//教师考核评分比例
			xskhpfbl="0";//学生考核评分比例
		}else{
			jskhpfbl=csMap.get("jskhpfbl");//教师考核评分比例
			xskhpfbl=csMap.get("xskhpfbl");//学生考核评分比例
		}
		String xspfyxbl=csMap.get("xspfyxbl");//学生评分有效比例
		String sqlTmp="";
		if("fdy".equalsIgnoreCase(SzkhCssz.KHDX)){//只有学生考核时比例分配
			sqlTmp=" fdybjb a ";
		}else if("bzr".equalsIgnoreCase(SzkhCssz.KHDX)){
			sqlTmp=" bzrbbb a ";
		}else{
			sqlTmp=" (select * from fdybjb union select * from bzrbbb)a ";
		}
		
		String sql="select rownum r,a.xn||'!!one!!'||a.zgh pk,a.*,round((a.xspjf*("+xskhpfbl+"/100)+nvl(cpxzpjf,0)*("+jskhpfbl+"/100)),2) zf," +
				"nvl(xsdrrs,'0')||'/'||fdybjrs xspfqk from ( "+
			"select aa.*,bb.xsdrrs,round((case when bb.xsdrrs/aa.fdybjrs>("+xspfyxbl+"/100) then bb.xspjf else 0 end ),2) xspjf, "+
			"round(cc.cpxzpjf,2) cpxzpjf "+
			" from  "+
			"( "+
			"    select xx.xn,yy.* from (select distinct xn from xg_szdw_bjlh_fdykhb) xx,    "+	
			"    (select a.zgh,b.xm,b.szbm bmdm,b.bmmc,count(1) fdybjrs from "+sqlTmp+" left join view_fdyxx b on a.zgh = b.zgh   "+
			"    left join view_xsjbxx c on a.bjdm=c.bjdm "+
			"    group by a.zgh,b.xm,b.szbm,b.bmmc) yy "+
			") aa left join  "+
			"( "+
			"   select xn,khbid,fdyid,avg(fz) xspjf,count(1) xsdrrs from ( "+
			"      select c.xn,a.khbid, a.fdyid,a.yhm, sum(case when b.pflx='减分' then to_char(-(to_number(a.fz))) else a.fz end) fz "+
			"                      from xg_szdw_bjlh_fdykhpfb a,xg_szdw_bjlh_fdykhxmb b,xg_szdw_bjlh_fdykhb c "+
			"                     where  a.khbid=b.khbid and a.xmid=b.xmid and a.khbid=c.khbid and c.pfdx='xs' "+
			"                     group by c.xn,a.khbid,a.fdyid,a.yhm "+
			"   )group by xn,khbid,fdyid "+
			")bb on aa.zgh=bb.fdyid and aa.xn=bb.xn left join "+
			"( "+
			"   select xn,khbid,fdyid,avg(fz) cpxzpjf from ( "+
			"      select c.xn,a.khbid, a.fdyid,a.yhm,sum(case when b.pflx='减分' then to_char(-(to_number(a.fz))) else a.fz end) fz "+
			"                      from xg_szdw_bjlh_fdykhpfb a left join xg_szdw_bjlh_fdykhxmb   b on a.khbid = b.khbid and a.xmid = b.xmid "+
			"                     left join xg_szdw_bjlh_fdykhb   c on a.khbid = c.khbid where c.pfdx='pfxz' "+
			"                     group by c.xn,a.khbid,a.fdyid,a.yhm "+
			"   ) group by xn,khbid,fdyid "+
			") cc on aa.zgh=cc.fdyid and aa.xn=cc.xn "+
			") a where 1=1 ";
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		
		//权限控制
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser(request, "","bmdm", null); 	//学院用户
		HttpSession session = request.getSession();
		if("true".equalsIgnoreCase(session.getAttribute("fdyQx").toString())){			//辅导员用户
			searchTjQx = " and zgh = '"+session.getAttribute("userName").toString()+"' ";
		}
		String[] output;
		if("xs".equalsIgnoreCase(SzkhCssz.KHRY)){//只有学生考核
			output = new String[] {"pk","xn", "zgh", "xm","bmmc","xspfqk","xspjf","zf"};
		} else if("cpz".equalsIgnoreCase(SzkhCssz.KHRY)){//只有参评组考核
			output = new String[] {"pk","xn", "zgh", "xm","bmmc","cpxzpjf","zf"};
		}else{
			output = new String[] {"pk","xn", "zgh", "xm","bmmc","xspfqk","xspjf","cpxzpjf","zf"};
		}
		
		return CommonQueryDAO.commonQuery(sql, searchTj+searchTjQx, inputV,
				output, myForm);
		//下面是注释的原始sql方便调试
	}
	
	/**
	 * 获取统计(池州学院)
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getFdyKhTjInfoforCzxy(BjlhFdykhForm myForm,HttpServletRequest request) throws Exception{
		HashMap<String,String> csMap=new BjlhCsszDAO().getCssz();//获取参数
		String jskhpfbl=csMap.get("jskhpfbl");//教师考核评分比例
		String xskhpfbl=csMap.get("xskhpfbl");//学生考核评分比例
		String xspfyxbl=csMap.get("xspfyxbl");//学生评分有效比例
		
		String sql="select rownum r,x.* from (select a.xn||'!!one!!'||a.zgh pk,a.*,round((a.xspjf*("+xskhpfbl+"/100)+cpxzpjf*("+jskhpfbl+"/100)),2) zf,(select y.mc from xg_szdw_fdykhdjb y where b.khdj = y.dm) khdj,(select z.mc from xg_szdw_fdyjtdjb z where b.jtdj = z.dm) jtdj," +
				"nvl(xsdrrs,'0')||'/'||fdybjrs xspfqk from ( "+
			"select aa.*,bb.xsdrrs,round((case when bb.xsdrrs/aa.fdybjrs>("+xspfyxbl+"/100) then bb.xspjf else 0 end ),2) xspjf, "+
			"round(cc.cpxzpjf,2) cpxzpjf "+
			" from  "+
			"( "+
			"    select xx.xn,yy.* from (select distinct xn from xg_szdw_bjlh_fdykhb) xx,    "+	
			"    (select a.zgh,b.xm,b.szbm bmdm,b.bmmc,count(1) fdybjrs from view_fdybjb a left join view_fdyxx b on a.zgh = b.zgh   "+
			"    left join view_xsjbxx c on a.bjdm=c.bjdm "+
			"    group by a.zgh,b.xm,b.szbm,b.bmmc) yy "+
			") aa left join  "+
			"( "+
			"   select xn,khbid,fdyid,avg(fz) xspjf,count(1) xsdrrs from ( "+
			"      select c.xn,a.khbid, a.fdyid,a.yhm, sum(case when b.pflx='减分' then -to_number(a.fz) else to_number(a.fz) end) fz "+
			"                      from xg_szdw_bjlh_fdykhpfb a,xg_szdw_bjlh_fdykhxmb b,xg_szdw_bjlh_fdykhb c "+
			"                     where  a.khbid=b.khbid and a.xmid=b.xmid and a.khbid=c.khbid and c.pfdx='xs' "+
			"                     group by c.xn,a.khbid,a.fdyid,a.yhm "+
			"   )group by xn,khbid,fdyid "+
			")bb on aa.zgh=bb.fdyid and aa.xn=bb.xn left join "+
			"( "+
			"   select xn,khbid,fdyid,avg(fz) cpxzpjf from ( "+
			"      select c.xn,a.khbid, a.fdyid,a.yhm,sum(case when b.pflx='减分' then to_char(-(to_number(a.fz))) else a.fz end) fz "+
			"                      from xg_szdw_bjlh_fdykhpfb a left join xg_szdw_bjlh_fdykhxmb   b on a.khbid = b.khbid and a.xmid = b.xmid "+
			"                     left join xg_szdw_bjlh_fdykhb   c on a.khbid = c.khbid where c.pfdx='pfxz' "+
			"                     group by c.xn,a.khbid,a.fdyid,a.yhm "+
			"   ) group by xn,khbid,fdyid "+
			") cc on aa.zgh=cc.fdyid and aa.xn=cc.xn left join xg_szdw_fdykhbzb d on d.xn=aa.xn and d.zgh=aa.zgh "+
			") a left join xg_szdw_fdykhbzb b on a.zgh = b.zgh and a.xn = b.xn ) x where 1=1 ";
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		
		//权限控制
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser(request, "","bmdm", null); 	//学院用户
		HttpSession session = request.getSession();
		if("true".equalsIgnoreCase(session.getAttribute("fdyQx").toString())){			//辅导员用户
			searchTjQx = " and zgh = '"+session.getAttribute("userName").toString()+"' ";
		}
		
		String[] output = new String[] {"pk","xn", "zgh", "xm","bmmc","xspfqk","xspjf","cpxzpjf","zf","khdj","jtdj"};
		return CommonQueryDAO.commonQuery(sql, searchTj+searchTjQx, inputV,
				output, myForm);
		//下面是注释的原始sql方便调试
	}
	
	/**
	 * 获取统计(池州学院)导出
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getFdyKhTjInfoforCzxyNotFy(BjlhFdykhForm myForm,HttpServletRequest request) throws Exception{
		HashMap<String,String> csMap=new BjlhCsszDAO().getCssz();//获取参数
		String jskhpfbl=csMap.get("jskhpfbl");//教师考核评分比例
		String xskhpfbl=csMap.get("xskhpfbl");//学生考核评分比例
		String xspfyxbl=csMap.get("xspfyxbl");//学生评分有效比例
		
		String sql="select rownum r,x.* from (select a.xn||'!!one!!'||a.zgh pk,a.*,round((a.xspjf*("+xskhpfbl+"/100)+cpxzpjf*("+jskhpfbl+"/100)),2) zf,(select y.mc from xg_szdw_fdykhdjb y where b.khdj = y.dm) khdj,(select z.mc from xg_szdw_fdyjtdjb z where b.jtdj = z.dm) jtdj," +
				"nvl(xsdrrs,'0')||'/'||fdybjrs xspfqk from ( "+
			"select aa.*,bb.xsdrrs,round((case when bb.xsdrrs/aa.fdybjrs>("+xspfyxbl+"/100) then bb.xspjf else 0 end ),2) xspjf, "+
			"round(cc.cpxzpjf,2) cpxzpjf "+
			" from  "+
			"( "+
			"    select xx.xn,yy.* from (select distinct xn from xg_szdw_bjlh_fdykhb) xx,    "+	
			"    (select a.zgh,b.xm,b.szbm bmdm,b.bmmc,count(1) fdybjrs from fdybjb a left join view_fdyxx b on a.zgh = b.zgh   "+
			"    left join view_xsjbxx c on a.bjdm=c.bjdm "+
			"    group by a.zgh,b.xm,b.szbm,b.bmmc) yy "+
			") aa left join  "+
			"( "+
			"   select xn,khbid,fdyid,avg(fz) xspjf,count(1) xsdrrs from ( "+
			"      select c.xn,a.khbid, a.fdyid,a.yhm, sum(case when b.pflx='减分' then -to_number(a.fz) else to_number(a.fz) end) fz "+
			"                      from xg_szdw_bjlh_fdykhpfb a,xg_szdw_bjlh_fdykhxmb b,xg_szdw_bjlh_fdykhb c "+
			"                     where  a.khbid=b.khbid and a.xmid=b.xmid and a.khbid=c.khbid and c.pfdx='xs' "+
			"                     group by c.xn,a.khbid,a.fdyid,a.yhm "+
			"   )group by xn,khbid,fdyid "+
			")bb on aa.zgh=bb.fdyid and aa.xn=bb.xn left join "+
			"( "+
			"   select xn,khbid,fdyid,avg(fz) cpxzpjf from ( "+
			"      select c.xn,a.khbid, a.fdyid,a.yhm,sum(case when b.pflx='减分' then to_char(-(to_number(a.fz))) else a.fz end) fz "+
			"                      from xg_szdw_bjlh_fdykhpfb a left join xg_szdw_bjlh_fdykhxmb   b on a.khbid = b.khbid and a.xmid = b.xmid "+
			"                     left join xg_szdw_bjlh_fdykhb   c on a.khbid = c.khbid where c.pfdx='pfxz' "+
			"                     group by c.xn,a.khbid,a.fdyid,a.yhm "+
			"   ) group by xn,khbid,fdyid "+
			") cc on aa.zgh=cc.fdyid and aa.xn=cc.xn left join xg_szdw_fdykhbzb d on d.xn=aa.xn and d.zgh=aa.zgh "+
			") a left join xg_szdw_fdykhbzb b on a.zgh = b.zgh and a.xn = b.xn ) x where 1=1 ";
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		
		//权限控制
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser(request, "","bmdm", null); 	//学院用户
		HttpSession session = request.getSession();
		if("true".equalsIgnoreCase(session.getAttribute("fdyQx").toString())){			//辅导员用户
			searchTjQx = " and zgh = '"+session.getAttribute("userName").toString()+"' ";
		}
		
		String[] output = new String[] {"pk","xn", "zgh", "xm","bmmc","xspfqk","xspjf","cpxzpjf","zf","khdj","jtdj"};
		return CommonQueryDAO.commonQueryNotFy(sql, searchTj+searchTjQx, inputV,
				output, myForm);
		//下面是注释的原始sql方便调试
	}
	
	public List<HashMap<String,String>> getYhmxx(String yhm,String lx){
		DAO dao = DAO.getInstance();
		String query="";
		if(!StringUtil.isNull(lx)){
			query=" and b.lx='"+lx+"'";
		}
		String sql = "select c.zgh zgh,c.xm,c.szbm bmdm,c.bmmc from view_xsjbxx a left join (select a.*,'辅导员' lx from fdybjb a union select b.*,'班主任' lx from bzrbbb b) b on a.bjdm = b.bjdm " +
				"left join view_fdyxx c on b.zgh = c.zgh where a.xh = ? ";
		sql+=query;
		return dao.getList(sql, new String[] { yhm }, new String[]{"zgh","xm","bmmc","bmdm"});
	}
	
	// 获得参数设置表中的默认设置
	public HashMap<String, String> getMrsz(String pfdx) {
		DAO dao = DAO.getInstance();
		String sql = "select a.*,b.* from XG_SZDW_BJLH_CSSZB a left join " +
				"(select b.* from Xg_Szdw_Bjlh_Fdykhb b where b.xn=(select xn from XG_SZDW_BJLH_CSSZB) and b.sfqy = '是' and pfdx = ? ) b on 1=1";
		return dao.getMap(sql, new String[] {pfdx}, new String[]{"khbid","xn","khsfqd","khkssj","khjssj","khlrjzsj"});
	}

	public boolean saveFdykhpfb(String[] xmid, String[] df,BjlhFdykhForm myForm) throws Exception {
		DAO dao = DAO.getInstance();
		boolean flag = false;
		String khbid = myForm.getKhbid();
		String yhm = myForm.getYhm();
		String fdyid = myForm.getFdyid();
/*		String sql = "delete from XG_SZDW_BJLH_FDYKHPFB where khbid = ? and yhm = ? and fdyid = ? ";
		flag = dao.runUpdate(sql, new String[]{khbid,yhm,fdyid});
		if(flag){
			sql = "insert into XG_SZDW_BJLH_FDYKHPFB(xmid,fz,yhm,fdyid,khbid) values (?,?,?,?,?)";
			for(int i = 0; i < xmid.length;i ++){
				flag = dao.runUpdate(sql, new String[]{xmid[i],df[i],yhm,fdyid,khbid});
			}
		}*/
		
		List<String> sqls=new ArrayList<String>();
		sqls.add("delete from XG_SZDW_BJLH_FDYKHPFB where khbid = '"+khbid+"' and yhm = '"+yhm+"' and fdyid = '"+fdyid+"' ");
		for(int i = 0; i < xmid.length;i ++){
			sqls.add("insert into XG_SZDW_BJLH_FDYKHPFB(xmid,fz,yhm,fdyid,khbid) values ('"+xmid[i]+"','"+df[i]+"','"+yhm+"','"+fdyid+"','"+khbid+"')");
		}		
		
		return new CommDAO().saveArrDate(sqls.toArray(new String[]{}));
	}
	
	/**
	 * 获取考核表是否已作答问卷
	 * @param model
	 * @return
	 */
	public boolean getKhbSfzd(BjlhFdykhForm myForm){
		DAO dao = DAO.getInstance();
		String sql="select count(1) num from XG_SZDW_BJLH_FDYKHPFB a where khbid = ? and yhm = ? and fdyid = ?";
		String num= dao.getOneRs(sql, new String[]{myForm.getKhbid(),myForm.getYhm(),myForm.getFdyid()},"num");
		return "0".equals(num)?false:true;
	}

	public boolean getSfcgkhsj(String dqsj) {
		DAO dao = DAO.getInstance();
		String sql=" select khlrjzsj from xg_szdw_bjlh_csszb ";
		String khlrjzsj= dao.getOneRs(sql, new String[]{},"khlrjzsj");
		return Integer.valueOf(dqsj)>Integer.valueOf(khlrjzsj)?true:false;
	}

	public HashMap<String, String> fdycjTjWh(BjlhFdykhForm myForm) {
		DAO dao = DAO.getInstance();
		String sql = "select d.xn,d.zgh,xm,bmmc,(select y.mc from xg_szdw_fdykhdjb y where c.khdj = y.dm) khdj,khdj khdjdm,jtdj jtdjdm,(select z.mc from xg_szdw_fdyjtdjb z where c.jtdj = z.dm) jtdj,bz from (select ? xn,zgh,xm,bmmc from fdyxxb a left join zxbz_xxbmdm b on a.bmdm=b.bmdm where a.zgh=?) d left join xg_szdw_fdykhbzb c on d.xn=c.xn and d.zgh=c.zgh";
		return dao.getMap(sql, new String[] {myForm.getXn(),myForm.getZgh()}, new String[]{"xn","zgh","xm","bmmc","khdj","jtdj","khdjdm","jtdjdm","bz"});
	}

	/**
	 * 获取考核等级下拉值
	 * @param request
	 * @return
	 */
	public Object getKhDjList(HttpServletRequest request) {
		String sql = "select dm,mc from xg_szdw_fdykhdjb order by dm";
		return new DAO().getList(sql, new String[] {}, new String[] { "dm","mc" });
	}

	/**
	 * 获取考核津贴下拉值
	 * @param request
	 * @return
	 */
	public Object getJtDjList(HttpServletRequest request) {
		String sql = "select dm,mc from xg_szdw_fdyjtdjb order by dm";
		return new DAO().getList(sql, new String[] {}, new String[] { "dm","mc" });
	}

	/**
	 * 辅导员成绩统计维护保存
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public boolean fdycjTjWhBc(BjlhFdykhForm myForm) throws Exception {
		boolean flag = false;
		DAO dao = DAO.getInstance();
		String xn = myForm.getXn();
		String zgh = myForm.getZgh();
		String khdj = myForm.getKhdj();
		String jtdj = myForm.getJtdj();
		String bz = myForm.getBz();
		String sql = "delete from xg_szdw_fdykhbzb where xn=? and zgh=?";
		flag = dao.runUpdate(sql.toString(),new String[] { xn, zgh });
		if (flag == true) {
				sql = "insert into xg_szdw_fdykhbzb (xn,zgh,khdj,jtdj,bz) values (?,?,?,?,?)";
				flag = dao.runUpdate(sql.toString(), new String[] { xn,zgh,khdj,jtdj,bz});
		}
		return flag;
	}

	/** 
	 * @描述:(获取辅导员考核表个项目的平均分列表)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-9 下午05:06:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getKhXmPjfList(BjlhFdykhForm model) {
		String xn=model.getXn();
		String fdyid=model.getFdyid();
		StringBuilder sql=new StringBuilder();
		sql.append("select a.*,round(nvl(b.pjf,0),1)pjf from (select a.* from xg_szdw_bjlh_fdykhxmb a left join xg_szdw_bjlh_fdykhb b on a.khbid=b.khbid where xn=? and pfdx='xs' order by to_number(xssx))a ");
		sql.append(" left join(select a.*,a.zfs/b.rs pjf from(select khbid, xmid, sum(case when pflx = '减分' then -to_number(fz) else to_number(fz) end) zfs");
		sql.append(" from (select a.*,c.pflx from xg_szdw_bjlh_fdykhpfb a left join xg_szdw_bjlh_fdykhb b on a.khbid = b.khbid left join xg_szdw_bjlh_fdykhxmb c on a.xmid=c.xmid");
		sql.append(" where fdyid = ? and b.pfdx='xs' and xn = ?) group by xmid, khbid) a,");
		sql.append(" (select count(distinct yhm) rs from (select a.*, b.xn from xg_szdw_bjlh_fdykhpfb a left join xg_szdw_bjlh_fdykhb b on a.khbid = b.khbid");
		sql.append(" where fdyid = ? and b.pfdx='xs' and xn = ?))b)b on a.khbid=b.khbid and a.xmid=b.xmid");
		DAO dao = DAO.getInstance();
		return dao.getListNotOut(sql.toString(), new String[]{xn,fdyid,xn,fdyid,xn});
	}

	/**
	 * @param lx  
	 * @描述:获取学生需要考核的辅导员list
	 * @作者：cmj
	 * @日期：2013-12-13 上午10:08:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getKhFdyList(BjlhFdykhForm myForm, String lx) {
		DAO dao = DAO.getInstance();
		String query="";
		if(!StringUtil.isNull(lx)){
			query=" b.lx='"+lx+"'";
		}
		StringBuilder sql=new StringBuilder();
		sql.append("select c.zgh zgh,b.lx, c.xm, c.szbm bmdm, c.bmmc,(case when d.fdyid is null then '否' else '是' end)sfykh");
		sql.append("  from view_xsjbxx a");
		sql.append("  left join (select a.*, '辅导员' lx");
		sql.append("               from fdybjb a");
		sql.append("             union");
		sql.append("             select b.*, '班主任' lx from bzrbbb b) b");
		sql.append("    on a.bjdm = b.bjdm");
		sql.append("  left join view_fdyxx c");
		sql.append("    on b.zgh = c.zgh");
		sql.append("  left join (select distinct fdyid from XG_SZDW_BJLH_FDYKHPFB where khbid=? and yhm=?)d");
		sql.append("    on c.zgh=d.fdyid");
		sql.append(" where a.xh = ?");
		sql.append(query);
		return dao.getListNotOut(sql.toString(), new String[]{myForm.getKhbid(),myForm.getYhm(),myForm.getYhm()});
	}

	/** 
	 * @描述:导出条件
	 * @作者：陈敏杰
	 * @日期：2013-12-16 下午05:28:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getAllList(BjlhFdykhForm myForm,
			HttpServletRequest request) throws Exception{
		HashMap<String,String> csMap=new BjlhCsszDAO().getCssz();//获取参数
		String jskhpfbl="";//教师考核评分比例
		String xskhpfbl="";//学生考核评分比例
		if("xs".equalsIgnoreCase(SzkhCssz.KHRY)){//只有学生考核时比例分配
			jskhpfbl="0";//教师考核评分比例
			xskhpfbl="100";//学生考核评分比例
		} else if("cpz".equalsIgnoreCase(SzkhCssz.KHRY)){//只有参评组考核时比例分配
			jskhpfbl="100";//教师考核评分比例
			xskhpfbl="0";//学生考核评分比例
		}else{
			jskhpfbl=csMap.get("jskhpfbl");//教师考核评分比例
			xskhpfbl=csMap.get("xskhpfbl");//学生考核评分比例
		}
		String xspfyxbl=csMap.get("xspfyxbl");//学生评分有效比例
		String sqlTmp="";
		if("fdy".equalsIgnoreCase(SzkhCssz.KHDX)){//只有学生考核时比例分配
			sqlTmp=" fdybjb a ";
		}else if("bzr".equalsIgnoreCase(SzkhCssz.KHDX)){
			sqlTmp=" bzrbbb a ";
		}else{
			sqlTmp=" (select * from fdybjb union select * from bzrbbb)a ";
		}
		
		String sql="select rownum r,a.xn||'!!one!!'||a.zgh pk,a.*,round((a.xspjf*("+xskhpfbl+"/100)+cpxzpjf*("+jskhpfbl+"/100)),2) zf," +
				"nvl(xsdrrs,'0')||'/'||fdybjrs xspfqk from ( "+
			"select aa.*,bb.xsdrrs,round((case when bb.xsdrrs/aa.fdybjrs>("+xspfyxbl+"/100) then bb.xspjf else 0 end ),2) xspjf, "+
			"round(cc.cpxzpjf,2) cpxzpjf "+
			" from  "+
			"( "+
			"    select xx.xn,yy.* from (select distinct xn from xg_szdw_bjlh_fdykhb) xx,    "+	
			"    (select a.zgh,b.xm,b.szbm bmdm,b.bmmc,count(1) fdybjrs from "+sqlTmp+" left join view_fdyxx b on a.zgh = b.zgh   "+
			"    left join view_xsjbxx c on a.bjdm=c.bjdm "+
			"    group by a.zgh,b.xm,b.szbm,b.bmmc) yy "+
			") aa left join  "+
			"( "+
			"   select xn,khbid,fdyid,avg(fz) xspjf,count(1) xsdrrs from ( "+
			"      select c.xn,a.khbid, a.fdyid,a.yhm, sum(case when b.pflx='减分' then to_char(-(to_number(a.fz))) else a.fz end) fz "+
			"                      from xg_szdw_bjlh_fdykhpfb a,xg_szdw_bjlh_fdykhxmb b,xg_szdw_bjlh_fdykhb c "+
			"                     where  a.khbid=b.khbid and a.xmid=b.xmid and a.khbid=c.khbid and c.pfdx='xs' "+
			"                     group by c.xn,a.khbid,a.fdyid,a.yhm "+
			"   )group by xn,khbid,fdyid "+
			")bb on aa.zgh=bb.fdyid and aa.xn=bb.xn left join "+
			"( "+
			"   select xn,khbid,fdyid,avg(fz) cpxzpjf from ( "+
			"      select c.xn,a.khbid, a.fdyid,a.yhm,sum(case when b.pflx='减分' then to_char(-(to_number(a.fz))) else a.fz end) fz "+
			"                      from xg_szdw_bjlh_fdykhpfb a left join xg_szdw_bjlh_fdykhxmb   b on a.khbid = b.khbid and a.xmid = b.xmid "+
			"                     left join xg_szdw_bjlh_fdykhb   c on a.khbid = c.khbid where c.pfdx='pfxz' "+
			"                     group by c.xn,a.khbid,a.fdyid,a.yhm "+
			"   ) group by xn,khbid,fdyid "+
			") cc on aa.zgh=cc.fdyid and aa.xn=cc.xn "+
			") a where 1=1 ";
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		
		//权限控制
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser(request, "","bmdm", null); 	//学院用户
		HttpSession session = request.getSession();
		if("true".equalsIgnoreCase(session.getAttribute("fdyQx").toString())){			//辅导员用户
			searchTjQx = " and zgh = '"+session.getAttribute("userName").toString()+"' ";
		}
		String[] output = new String[] {"pk","xn", "zgh", "xm","bmmc","xspfqk","xspjf","cpxzpjf","zf"};
		DAO dao=DAO.getInstance();
		sql+=searchTj+searchTjQx;
		return dao.getList(sql, inputV, output);
	}
	
	public boolean checkFdykhpf(String xmid,String df) throws Exception{
		if(!df.matches("[\\d.]+") && !df.matches("[\\d]+")){
			return false;
		}
		
		DAO dao=DAO.getInstance();
		String fzqj = dao.getOneRs("select fzqj from xg_szdw_bjlh_fdykhxmb where xmid =?", new String[]{xmid}, "fzqj");
		if(fzqj.contains("-")){
			String[] qj = fzqj.split("-");			
			if(Float.valueOf(df) > Float.valueOf(qj[1]) || Float.valueOf(df) < Float.valueOf(qj[0])){
				return false;
			}
		}else{
			if(Float.valueOf(df) > Float.valueOf(fzqj)){
				return false;
			}
		}
		
		return true;
	}
	
}
