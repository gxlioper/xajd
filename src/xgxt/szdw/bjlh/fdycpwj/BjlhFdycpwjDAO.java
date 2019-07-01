package xgxt.szdw.bjlh.fdycpwj;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zfsoft.utils.StringUtil;
import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.szdw.bjlh.fdykh.BjlhFdykhForm;
import xgxt.utils.CommonQueryDAO;

public class BjlhFdycpwjDAO extends CommDAO {
	
	/**
	 * 获取测评问卷列表
	 * @param model
	 * @return
	 */
	public List<String[]> getCpwjList(BjlhFdycpwjForm model,HttpServletRequest request) throws Exception{
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		List<String[]> list = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("select rownum r,a.* from (select a.*,(select count(*) from xg_szdw_bjlh_fdycpwjdab where wjid = a.wjid) num from xg_szdw_bjlh_fdycpwjb a)a where 1=1 ");
		//权限控制
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser(request, "","bmdm", null); 	//学院用户
		
		String[] outputs = new String[]{"wjid","xn", "wjid", "wjmc", "sfqy", "fbrq", "fbr","num"};
		list = CommonQueryDAO.commonQuery(sql.toString(), searchTj, inputV, outputs, model);
		
		return list;
		
	}
	
	/**
	 * 获取测评问卷单条记录
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getCpwjOne(BjlhFdycpwjForm model){
		DAO dao=new DAO(); 
		String sql="select * from xg_szdw_bjlh_fdycpwjb where wjid=?";
		return dao.getMapNotOut(sql, new String[]{model.getWjid()});
	}
	
	/**
	 * 保存测评问卷信息
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public boolean saveCpwjInfo(BjlhFdycpwjForm model,String type) throws Exception{
		String sql;
		String[] inputV;
		DAO dao=new DAO();
		if("add".equals(type)){
			sql="insert into xg_szdw_bjlh_fdycpwjb(xn,wjid,wjmc,sfqy,fbrq,fbr) " +
					"values(?,seq_bjlh_fdycpwj.nextval,?,?,to_char(sysdate,'yyyy-mm-dd'),?)";
			inputV=new String[]{model.getXn(),model.getWjmc(),"否",model.getFbr()};
		}else{
			sql="update xg_szdw_bjlh_fdycpwjb set wjmc=? where wjid=?";
			inputV=new String[]{model.getWjmc(),model.getWjid()};
		}
		return dao.runUpdate(sql, inputV);
	}
	
	/**
	 * 删除测评问卷信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String deleteCpwjInfo(BjlhFdycpwjForm model) throws Exception{
		DAO dao=new DAO();
		String sql="delete from xg_szdw_bjlh_fdycpwjb where wjid=?";
		boolean b=dao.runUpdate(sql,new String[]{model.getWjid()});
		if(b){
			sql ="delete from xg_szdw_bjlh_fdycpwjstb where wjid=?";
			b=dao.runUpdate(sql,new String[]{model.getWjid()});
		}
		if(b){
			sql ="delete from xg_szdw_bjlh_fdycpwjstxxb a where exists (select 1 from xg_szdw_bjlh_fdycpwjstb b where a.stid=b.stid and b.wjid=?)";
			b=dao.runUpdate(sql,new String[]{model.getWjid()});
		}
		return b?"操作成功！":"操作失败";
	}
	
	/**
	 * 验证测评问卷权限
	 * @param model
	 * @return
	 */
	public String checkCpwjQx(BjlhFdycpwjForm model){
		String msg="";
		String sql="select count(1) num from xg_szdw_bjlh_fdycpwjdab where wjid=?";
		String num=new DAO().getOneRs(sql, new String[]{model.getWjid()},"num");
		if(!"0".equals(num)){
			msg="问卷已有人作答！";
		}
		return msg;
	}
	
	/**
	 * 复制测评问卷信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public synchronized boolean copyCpwjInfo(BjlhFdycpwjForm model) throws Exception{
		String wjid=model.getWjid();
		String sql="select * from xg_szdw_bjlh_fdycpwjstb where wjid=?";
		List<HashMap<String,String>> stList=getCpwjStList(model);//试题列表
		ArrayList<String> sqls=new ArrayList<String>();
		//复制问卷基本信息
		sqls.add("insert into xg_szdw_bjlh_fdycpwjb(wjid,wjmc,sfqy,fbrq,fbr,xn) " +
				"select seq_bjlh_fdycpwj.nextval,'"+model.getWjmc()+"','否',to_char(sysdate,'yyyy-mm-dd'),'"+
				model.getFbr()+"','"+model.getXn()+"' " +
				"from xg_szdw_bjlh_fdycpwjb a where a.wjid='"+wjid+"'");
		if(stList!=null&&stList.size()>0){
			String stid;//试题id
			for(int i=0;i<stList.size();i++){
				stid=stList.get(i).get("stid");
				//复制试题
				sqls.add("insert into xg_szdw_bjlh_fdycpwjstb(stid,wjid,stmc,stlx,xssx,dhxxgs,xxgs) "+
                         "select seq_bjlh_fdycpwj.nextval,(select max(to_number(wjid)) from xg_szdw_bjlh_fdycpwjb)," +
                         "stmc,stlx,xssx,dhxxgs,xxgs from xg_szdw_bjlh_fdycpwjstb a where a.wjid='"+wjid+"' and a.stid='"+stid+"'");
				//复制试题选项
				sqls.add("insert into xg_szdw_bjlh_fdycpwjstxxb(xxid,stid,xxnr,xssx) " +
						"select seq_bjlh_fdycpwj.nextval,(select max(to_number(stid)) from xg_szdw_bjlh_fdycpwjstb)," +
						"xxnr,xssx from xg_szdw_bjlh_fdycpwjstxxb a where stid='"+stid+"'");
			}
		}
		boolean b=new CommDAO().saveArrDate(sqls.toArray(new String[]{}));
		return b;
	}
	
	/**
	 * 是否启用测评问卷
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String sfqyCpwj(BjlhFdycpwjForm model) throws Exception{
		DAO dao=new DAO();
		String msg="";
		String sql="";
		if("是".equals(model.getSfqy())){
			sql="select count(1) num from xg_szdw_bjlh_fdycpwjb a where a.sfqy='是' " +
				"and exists (select 1 from xg_szdw_bjlh_fdycpwjb x where x.xn=a.xn and x.wjid=?)";
			if(!"0".equals(dao.getOneRs(sql, new String[]{model.getWjid()}, "num"))){
				msg="本学年已有问卷启用！";
				return msg;
			}
		}
		sql="update xg_szdw_bjlh_fdycpwjb set sfqy=? where wjid=?";
		msg=dao.runUpdate(sql, new String[]{model.getSfqy(),model.getWjid()})?"操作成功！":"操作失败！";
		return msg;
	}
	
	/**
	 * 获取测评问卷试题列表
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getCpwjStList(BjlhFdycpwjForm model){
		DAO dao=new DAO();
		String sql="select a.*,rownum r from (select a.*," +
				"(case when stlx='1' then '单选题' when stlx='2' then '简答题' else '' end) stlxmc " +
				"from xg_szdw_bjlh_fdycpwjstb a where wjid=? order by to_number(xssx))a";
		return dao.getListNotOut(sql, new String[]{model.getWjid()});
	}
	
	/**
	 * 保存测评文件试题信息
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public synchronized boolean saveCpwjStxx(BjlhFdycpwjForm model,String type) throws Exception{
		boolean b=false;
		DAO dao=new DAO();
		ArrayList<String> sqls=new ArrayList<String>();
		String sql;
		if("add".equals(type)){//增加
			sql="select seq_bjlh_fdycpwj.nextval stid from dual";
			String stid=dao.getOneRs(sql, new String[]{}, "stid");
			model.setStid(stid);
			sqls.add("insert into xg_szdw_bjlh_fdycpwjstb(stid,wjid,stmc,stlx,xssx,dhxxgs,xxgs) " +
					"values('"+stid+"','"+model.getWjid()+"','"+model.getStmc()+"','"+model.getStlx()+"','"+
					model.getXssx()+"','"+model.getDhxxgs()+"','"+model.getXxgs()+"')");
			if("1".equals(model.getStlx())){//单选题才有选项
				for(int i=0;i<model.getXxnrs().length;i++){
					sqls.add("insert into xg_szdw_bjlh_fdycpwjstxxb(xxid,stid,xxnr,xssx) " +
							"values(seq_bjlh_fdycpwj.nextval,'"+stid+"','"+model.getXxnrs()[i]+"','"+i+"')");
				}
			}
		}else if("update".equals(type)){//修改
			sqls.add("update xg_szdw_bjlh_fdycpwjstb set stmc='"+model.getStmc()+"',stlx='"+model.getStlx()+"',xssx='"+
					model.getXssx()+"',dhxxgs='"+model.getDhxxgs()+"',xxgs='"+model.getXxgs()+"' where wjid='"+
					model.getWjid()+"' and stid='"+model.getStid()+"'");
			sqls.add("delete from xg_szdw_bjlh_fdycpwjstxxb where stid='"+model.getStid()+"'");
			if("1".equals(model.getStlx())){//单选题才有选项
				for(int i=0;i<model.getXxnrs().length;i++){
					sqls.add("insert into xg_szdw_bjlh_fdycpwjstxxb(xxid,stid,xxnr,xssx) " +
							"values(seq_bjlh_fdycpwj.nextval,'"+model.getStid()+"','"+model.getXxnrs()[i]+"','"+i+"')");
				}
			}
		}else if("delete".equals(type)){//删除
			sqls.add("delete from xg_szdw_bjlh_fdycpwjstb where stid='"+model.getStid()+"'");
			sqls.add("delete from xg_szdw_bjlh_fdycpwjstxxb where stid='"+model.getStid()+"'");
		}
		return new CommDAO().saveArrDate(sqls.toArray(new String[]{}));
	}
	
	/**
	 * 获取测评问卷试题信息列表
	 * @param model
	 * @return
	 */
//	public List<HashMap<String,String>> getCpwjStxxList(BjlhFdycpwjForm model){
//		String sql="select * from xg_szdw_bjlh_fdycpwjstb where wjid=? order by";
//		return new DAO().getListNotOut(sql, new String[]{model.getWjid()});
//	}
	
	/**
	 * 获取选项列表
	 * @param model
	 * @param type
	 * @return
	 */
	public List<HashMap<String,String>> getXxlist(BjlhFdycpwjForm model,String type){
		String sql;
		String[] input;
		if("st".equals(type)){//获取一个试题的选项
			sql="select a.*,chr(ascii('A')+xssx) zfbm from xg_szdw_bjlh_fdycpwjstxxb a where stid=? order by to_number(xssx)";
			input=new String[]{model.getStid()};
		}else{//获取问卷所有试题的选项
			sql="select a.*,chr(ascii('A')+a.xssx) zfbm from xg_szdw_bjlh_fdycpwjstxxb a,xg_szdw_bjlh_fdycpwjstb b " +
				"where a.stid=b.stid and b.wjid=? order by to_number(b.xssx),to_number(a.xssx)";
			input=new String[]{model.getWjid()};
		}
		return new DAO().getListNotOut(sql, input);
	}
	
	/**
	 * 获取测评问卷试题单条记录
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getCpwjStxxOne(BjlhFdycpwjForm model){
		DAO dao=new DAO(); 
		String sql="select * from xg_szdw_bjlh_fdycpwjstb where wjid=? and stid=?";
		return dao.getMapNotOut(sql, new String[]{model.getWjid(),model.getStid()});
	}
	
	/**
	 * 获取问卷试题答案集合
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getWjStDaList(BjlhFdycpwjForm model){
		String sql="select a.stid||'-'||nvl(a.xxid,'') stxx,a.wbda,b.stlx from xg_szdw_bjlh_fdycpwjdab a " +
				"left join xg_szdw_bjlh_fdycpwjstb b on a.stid=b.stid " +
				"where a.xh=? and a.wjid=? and a.fdyid=?";
		return new DAO().getList(sql, new String[]{model.getXh(),model.getWjid(),model.getFdyid()}, new String[]{"stxx","wbda","stlx"});
	}
	
	/**
	 * 获取学生是否已作答问卷
	 * @param model
	 * @return
	 */
	public boolean getXsWjstSfzd(BjlhFdycpwjForm model){
		String sql="select count(1) num from xg_szdw_bjlh_fdycpwjdab a where a.xh=? and a.wjid=?";
		String num= new DAO().getOneRs(sql, new String[]{model.getXh(),model.getWjid()},"num");
		return "0".equals(num)?false:true;
	}
	
	public List<HashMap<String,String>> getWjstTjInfoList(BjlhFdycpwjForm model){
		String sql="select a.*,chr(ascii('A')+a.xxxssx) zfbm,b.dtrs,nvl(c.xxrs,'0') xxrs,round(nvl(c.xxrs,'0')/b.dtrs,4)*100||'%' xxrsbfb from "+
			"(select a.xxid,a.stid,a.xxnr,a.xssx xxxssx,b.xssx stxssx,b.wjid from xg_szdw_bjlh_fdycpwjstxxb a,xg_szdw_bjlh_fdycpwjstb b "+ 
			"where b.wjid=? and a.stid=b.stid) a "+
			"left join  "+
			"(select wjid,stid,count(1) dtrs from xg_szdw_bjlh_fdycpwjdab a where wjid=? and fdyid=? and xxid is not null "+
			"group by wjid,stid) b on a.wjid=b.wjid and a.stid=b.stid "+
			"left join  "+
			"(select wjid,stid,xxid,count(1) xxrs from xg_szdw_bjlh_fdycpwjdab a where wjid=? and fdyid=? and xxid is not null "+
			"group by wjid,stid,xxid) c "+
			"on a.wjid=c.wjid and a.stid=c.stid and a.xxid=c.xxid "+
			"order by to_number(a.stxssx),to_number(a.xxxssx)";
		return new DAO().getListNotOut(sql, new String[]{model.getWjid(),model.getWjid(),model.getFdyid(),model.getWjid(),model.getFdyid()});
//		return new DAO().getListNotOut(sql,new String[]{});
	}
	
	/**
	 * 获取辅导员信息
	 * @param xh
	 * @return
	 */
	public HashMap<String,String> getFdyInfo(String xh){
//		String sql="select zgh,xm from fdybjb a left join yhb b on a.zgh=b.yhm "+
//				   "where b.qx='1' and exists (select 1 from view_xsjbxx x where x.bjdm=a.bjdm and x.xh=?)";
		
		String sql="select a.zgh,c.xm,b.xymc from (select * from fdybjb union select * from bzrbbb) a,view_xsjbxx b,yhb c where a.bjdm=b.bjdm and a.zgh=c.yhm and b.xh=?";
		HashMap<String,String> fdyxx= new DAO().getMapNotOut(sql, new String[]{xh});
		if(fdyxx==null){
			fdyxx=new HashMap<String, String>();
		}
		return fdyxx;
	}
	
	// 获得辅导员自评表未有的学年
	public List<HashMap<String, String>> getAddXnList() {
		List<HashMap<String, String>> allList = Base.getXnndList();
		List<HashMap<String, String>> list = new DAO().getList(
				"select xn from xg_szdw_bjlh_fdycpwjb", new String[] {},
				new String[] { "xn" });
		;
		return removeXn(allList, list);
	}

	private List<HashMap<String, String>> removeXn(
			List<HashMap<String, String>> allList,
			List<HashMap<String, String>> list) {
		List<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();

		for (int i = 0; i < allList.size(); i++) {
			HashMap<String, String> allMap = allList.get(i);
			boolean res = true; 
			for (int j = 0; j < list.size(); j++) {
				if (allMap.get("xn").equalsIgnoreCase(list.get(j).get("xn"))) {
					res= false;
					break;
				}
			}
			if(res)
			{
				result.add(allMap);
			}
		}
		return result;

	}
	
	/**
	 * 获取测评问卷统计查询列表
	 * @param model
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]> getCpwjTjQueryList(BjlhFdycpwjForm model,HttpServletRequest request) throws Exception{
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		String sql="select rownum r,a.xn||'!!one!!'||a.wjid||'!!one!!'||a.zgh pk,a.* from ("+
					"	select a.xn,a.bmdm,a.bmmc,a.zgh,a.xm,a.fdybjzrs,b.wjid,case when b.drrs >0 then b.drrs else 0 end drrs from ( "+
					"    select xx.xn,yy.* from (select distinct xn from xg_szdw_bjlh_fdycpwjb) xx,"+
					"    (select a.zgh,b.xm,b.szbm bmdm,b.bmmc,count(1) fdybjzrs from fdybjb a left join view_fdyxx b on a.zgh = b.zgh   "+
					"    left join view_xsjbxx c on a.bjdm=c.bjdm "+
					"    group by a.zgh,b.xm,b.szbm,b.bmmc) yy "+
					"	) a "+
					"left join "+ 
					"(select c.xn,a.wjid,fdyid,count(distinct a.xh) drrs from xg_szdw_bjlh_fdycpwjdab a," +
					"view_xsjbxx b,xg_szdw_bjlh_fdycpwjb c where a.xh=b.xh and a.wjid=c.wjid group by c.xn,a.wjid,fdyid) b "+
					"on a.zgh=b.fdyid and a.xn=b.xn "+ 
//					"left join xg_szdw_bjlh_fdycpwjb c on c.wjid=b.wjid " +
				") a where 1=1 ";
		//权限控制
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser(request, "","bmdm", null); 	//学院用户
		HttpSession session = request.getSession();
		if("true".equalsIgnoreCase(session.getAttribute("fdyQx").toString())){			//辅导员用户
			searchTjQx = " and zgh = '"+session.getAttribute("userName").toString()+"' ";
		}
		
		String[] outputs=new String[]{"pk","xn","zgh","xm","bmmc","fdybjzrs","drrs"};
		List<String[]> list = CommonQueryDAO.commonQuery(sql, searchTj+searchTjQx, inputV, outputs, model);
		return list;
	}
	
	/**
	 * 获得参数设置表中的默认设置
	 */
	public HashMap<String, String> getMrsz() {
		DAO dao=new DAO(); 
		String sql = "select a.*,b.*,c.* from XG_SZDW_BJLH_CSSZB a," +
				"(select b.wjid,b.wjmc,b.sfqy,b.fbr,b.fbrq from xg_szdw_bjlh_fdycpwjb b where b.xn=(select xn from XG_SZDW_BJLH_CSSZB) and b.sfqy = '是') b," +
				"(select b.khbid from xg_szdw_bjlh_fdykhb b where b.xn = (select xn from XG_SZDW_BJLH_CSSZB) and b.sfqy = '是' and b.pfdx='xs') c";
		return dao.getMapNotOut(sql, new String[]{});
	}

	/** 
	 * @描述:获取学生需要考核的辅导员list
	 * @作者：cmj
	 * @日期：2013-12-13 上午10:08:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCpFdyList(BjlhFdycpwjForm myForm,
			String lx) {
		DAO dao = DAO.getInstance();
		String query="";
		if(!StringUtil.isNull(lx)){
			query=" b.lx='"+lx+"'";
		}
		StringBuilder sql=new StringBuilder();
		sql.append("select c.zgh zgh,b.lx, c.xm, c.szbm bmdm, c.bmmc,(case when d.fdyid is null ");
		if(Globals.XXDM_BJLHDX .equalsIgnoreCase(Base.xxdm)){
			sql.append(" or e.fdyid is null");
		}
		sql.append(" then '否' else '是' end)sfycp from view_xsjbxx a");
		sql.append("  left join (select a.*, '辅导员' lx");
		sql.append("               from fdybjb a");
		sql.append("             union");
		sql.append("             select b.*, '班主任' lx from bzrbbb b) b");
		sql.append("    on a.bjdm = b.bjdm");
		sql.append("  left join view_fdyxx c");
		sql.append("    on b.zgh = c.zgh");
		sql.append("  left join (select distinct fdyid from xg_szdw_bjlh_fdycpwjdab where wjid=? and xh=?)d");
		sql.append("    on c.zgh=d.fdyid");
		if(Globals.XXDM_BJLHDX .equalsIgnoreCase(Base.xxdm)){
			sql.append(" left join (select distinct fdyid from xg_szdw_bjlh_fdykhpfb where khbid=? and yhm=?) e ");
			sql.append("    on c.zgh=e.fdyid");
		}
		sql.append(" where a.xh = ?");
		sql.append(query);
		if(Globals.XXDM_BJLHDX .equalsIgnoreCase(Base.xxdm)){
			return dao.getListNotOut(sql.toString(), new String[]{myForm.getWjid(),myForm.getXh(),myForm.getKhbid(),myForm.getXh(),myForm.getXh()});
		}else{
			return dao.getListNotOut(sql.toString(), new String[]{myForm.getWjid(),myForm.getXh(),myForm.getXh()});
		}
	}
	
	/**
	 * 获取测评问卷是否已作答问卷
	 * @param model
	 * @return
	 */
	public boolean getCpwjSfzd(BjlhFdycpwjForm myForm){
		DAO dao = DAO.getInstance();
		String sql="select count(1) num from xg_szdw_bjlh_fdycpwjdab a where wjid = ? and xh = ? and fdyid = ?";
		String num= dao.getOneRs(sql, new String[]{myForm.getWjid(),myForm.getXh(),myForm.getFdyid()},"num");
		return "0".equals(num)?false:true;
	}

}
