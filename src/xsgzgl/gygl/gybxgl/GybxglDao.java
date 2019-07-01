package xsgzgl.gygl.gybxgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.comm.GyglNewDAO;
import xsgzgl.gygl.comm.GyglNewService;

public class GybxglDao extends GyglNewDAO{
	DAO dao = DAO.getInstance();
	/**
	 * 获取退宿信息数据集
	 * @author zhanghui
	 */
	public ArrayList<String[]> getGybxglInfoList(GybxglForm myForm, String[] colList,HttpServletRequest request) 
		throws Exception{		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		////权限控制
		//GyglNewService gyglNewService = new GyglNewService();
		//String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//公寓辅导员	
		
		//权限控制
		String searchTjQx="";
		
		SearchService searchService=new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a","xydm", "bjdm"); 	//学院用户
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//公寓辅导员		
		
		if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//用户为公寓辅导员
			searchTjQx = searchTjByGyfdy;
		}else{//用户非公寓辅导员，可能是学校管理员，可能是学院管理员
			searchTjQx = searchTjByUser;
		}
		/*String sql = "select rownum r,a.* from (select a.xh||a.bxsj pk,a.*,a.qwwxsj_ks||'-'||a.qwwxsj_js qwwxsj," +
		"b.xm,b.xydm,b.zydm,b.bjdm,b.nj,c.ldmc,c.qsdh, (case when length(a.bxnr) > 10 then substr(a.bxnr, 1, 10) || '...' else a.bxnr end) subbxnr " +
		"from xg_gygl_new_gybxb a left join ( select a.xh,a.xm,(case a.xb when '1' then '男' when '2' then '女' else a.xb end) xb,a.nj,a.xydm,a.zydm,a.bjdm from xsxxb a ) b on a.xh = b.xh " +
		"left join view_xg_gygl_new_cwxx c on a.lddm=c.lddm and a.qsh=c.qsh and a.cwh=c.cwh) a where 1=1 ";*/
		//String sql = "select rownum r ,a.* from VIEW_NEW_DC_GYGL_BXSQGL a where 1=1 ";
		StringBuilder sql = new StringBuilder();
		if(isHasPzRy()){
			sql.append("select rownum r ,a.* from ( ");
			sql.append("select * from VIEW_NEW_DC_GYGL_BXSQGL a where exists ");
			sql.append("(select 1 from XG_GYGL_NEW_GYBXRYB b where");
			sql.append("  a.BXLB=b.bxdm and yhm='");
			sql.append(myForm.getYhm());
			sql.append("') ) a where 1=1 ");
		}else{
			//报修分配
			if("bxfp".equals(myForm.getFlag())){
				sql.append("select rownum r ,a.* from (select a.* from view_new_dc_gygl_bxsqgl_bxfp a where 1=1 order by fpzt asc,bxsj desc)a where 1=1");
			}else if(!"bxfp".equals(myForm.getFlag()) &&"12727".equals(Base.xxdm)){
				sql.append("select rownum r ,a.* from (select a.* from  view_new_dc_gygl_bxsqgl_bxfp  a order by clzt asc,bxsj desc)a where 1=1 and fpbm ="+"'"+(String)request.getSession().getAttribute("userDep")+"'");

			}else{
				sql.append("select rownum r ,a.* from (select a.* from VIEW_NEW_DC_GYGL_BXSQGL a order by clzt asc,bxsj desc)a where 1=1 ");
			}
		}
		return  commonQuery(sql.toString(),searchTj+searchTjQx,inputV,colList,myForm);
	}
	
	/**
	 * 我的报修只能查看自己的数据
	 * @param myForm
	 * @param colList
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGybxglSelfList(GybxglForm myForm, String[] colList, User user, String searchTjByGyfdy) 
		throws Exception{		
	String userName = user.getUserName();
	String sql = "select rownum r,a.* from (select a.xh||a.bxsj pk,a.*,(select mc from gygl_bxlbdmb where dm = a.bxlb) bxlbmc,a.qwwxsj_ks||'-'||a.qwwxsj_js qwwxsj," +
			"b.xm,b.xymc,b.zymc,b.bjmc,b.nj,c.ldmc,c.qsdh ,d.bxlbzxmc,(case when length(a.bxnr) > 18 then substr(a.bxnr, 1, 18) || '...' else a.bxnr end) subbxnr " +
			"from xg_gygl_new_gybxb a left join view_xsjbxx b on a.xh = b.xh " +
			"left join view_xg_gygl_new_cwxx c on a.lddm=c.lddm and a.qsh=c.qsh and a.cwh=c.cwh left join gygl_bxlbzxb d on a.bxlbzxdm=d.bxlbzxdm) a where 1=1 ";		
	
	String tj = "";
	if("stu".equals(user.getUserType())){
		tj = " and xh='"+userName+"' ";
	}else{
		tj = searchTjByGyfdy;
	}
	return  commonQuery(sql,tj,new String[]{},colList,myForm);
}
	
	/**
	 * 查看学生信息
	 * @param pk
	 * @return
	 */
	public HashMap<String,String> viewXsxx(String xh){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.xh,b.lddm,b.ldmc,b.qsh,b.cwh,b.qsdh,a.xm,a.xymc,a.zymc,a.bjmc,a.nj ")
			.append("from view_xsjbxx a left join view_xg_gygl_new_cwxx b on a.xh = b.xh where a.xh=?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}	
	
	/**
	 * 查看学生信息以及相关报修信息
	 * @param pk
	 * @return
	 */
	public HashMap<String,String> viewXsxx(String pk,String xh){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.xh,b.lddm,b.ldmc,b.qsh,b.cwh,b.qsdh,a.xm,a.xymc,a.zymc,a.bjmc,a.nj,")
			.append("c.bxnr,c.jjcd,c.bxlb,(select mc from gygl_bxlbdmb where dm = c.bxlb) bxlbmc,c.lxdh,c.qwwxsj_ks,c.qwwxsj_js,c.clzt,c.fpzt,c.fpbm,c.fpxq,c.bclyy,c.wxnr,c.wxry,")
			.append("c.wxsj,c.wxfy,c.mycd,c.pj,c.zbclyy, ")
			.append(" b.ldmc||'，'||b.qsh||'，'||b.cwh||'号床' zzqsmc,d.bxlbzxdm,d.bxlbzxmc,c.filepath ")
			.append("from view_xsjbxx a left join view_xg_gygl_new_cwxx b on a.xh = b.xh ")
			.append("left join xg_gygl_new_gybxb c on a.xh=c.xh and c.xh||c.bxsj=? left join gygl_bxlbzxb d on c.bxlbzxdm=d.bxlbzxdm ")
			.append("where a.xh=?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{pk,xh});
	}	
	
	public boolean gybxglAdd(GybxglForm myForm) throws Exception{
		String xh = myForm.getXh();
		String lddm = myForm.getLddm();
		String qsh = myForm.getQsh();
		String cwh = myForm.getCwh();
		String bxnr = myForm.getBxnr();
		String jjcd = myForm.getJjcd();
		String bxlb = myForm.getBxlb();
		String lxdh = myForm.getLxdh();
		String qwwxsj_ks = myForm.getQwwxsj_ks();
		String qwwxsj_js = myForm.getQwwxsj_js();
		String bxlbzxdm = myForm.getBxlbzxdm();
		String filepath = myForm.getFilepath();
		return dao.insert("insert into xg_gygl_new_gybxb (lddm,qsh,cwh,xh,bxnr,jjcd,bxlb,lxdh,qwwxsj_ks,qwwxsj_js,bxlbzxdm,filepath) " +
				"values(?,?,?,?,?,?,?,?,?,?,?,?)", new String[]{lddm,qsh,cwh,xh,bxnr,jjcd,bxlb,lxdh,qwwxsj_ks,qwwxsj_js,bxlbzxdm,filepath});
	}
	
	public boolean gybxglModi(GybxglForm myForm) throws Exception{		
		StringBuilder sql = new StringBuilder();
		String[] inputs = new String[]{myForm.getBxnr(), myForm.getJjcd(),myForm.getBxlb(), myForm.getLxdh(),
							myForm.getQwwxsj_ks(), myForm.getQwwxsj_js(), myForm.getBxlbzxdm(),myForm.getFilepath(),myForm.getPk()};
		
		sql.append("update xg_gygl_new_gybxb set bxnr=?,jjcd=?,bxlb=?,lxdh=?,qwwxsj_ks=?,qwwxsj_js=?,bxlbzxdm=?,filepath=? where xh||bxsj=? ");
		
		return dao.runUpdate(sql.toString(), inputs);
	}
	
	public boolean gybxglUpdate(GybxglForm myForm) throws Exception{		
		StringBuilder sql = new StringBuilder();
		String[] inputs = new String[]{myForm.getClzt(), myForm.getWxsj(), myForm.getWxry(),
							myForm.getWxfy(), myForm.getBclyy(), myForm.getWxnr(), myForm.getZbclyy(), myForm.getPk()};
		
		sql.append("update xg_gygl_new_gybxb set clzt=?,wxsj=?,wxry=?,wxfy=?,bclyy=?,wxnr=?,zbclyy=? ")
			.append("where xh||bxsj=? ");
		
		return dao.runUpdate(sql.toString(), inputs);
	}
	
	public boolean delGybx(List<String[]> params) throws Exception{
		String sql = "delete xg_gygl_new_gybxb where xh||bxsj=?";
		
		return dao.checkBatchResult(dao.runBatch(sql,params));
	}
	
	
	public boolean pjUpdate(List<String[]> params) throws SQLException{
		String sql = "update xg_gygl_new_gybxb set mycd=?,pj=? where xh||bxsj=?";
		return dao.checkBatchResult(dao.runBatch(sql,params));
	}
	
	/**
	 * 公寓报修管理 自定义导出
	 * @param myForm
	 * @param colList
	 * @param request
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getGybxglInfoExportList(GybxglForm myForm, String[] colList,HttpServletRequest request) 
	throws Exception{		
	// 高级查询条件
	String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
	// 高级查询输入值
	String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
	////权限控制
	//GyglNewService gyglNewService = new GyglNewService();
	//String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//公寓辅导员	
	
	//权限控制
	String searchTjQx="";
	
	SearchService searchService=new SearchService();
	String searchTjByUser = searchService.getSearchTjByUser(request, "a","xydm", "bjdm"); 	//学院用户
	GyglNewService gyglNewService = new GyglNewService();
	String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//公寓辅导员		
	
	if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//用户为公寓辅导员
		searchTjQx = searchTjByGyfdy;
	}else{//用户非公寓辅导员，可能是学校管理员，可能是学院管理员
		searchTjQx = searchTjByUser;
	}
	
	
	
	/*String sql = "select rownum r,a.* from (select a.xh||a.bxsj pk,a.*,a.qwwxsj_ks||'-'||a.qwwxsj_js qwwxsj," +
	"b.xm,b.xydm,b.zydm,b.bjdm,b.nj,c.ldmc,c.qsdh from xg_gygl_new_gybxb a left join "+Base.xsxxb+" b on a.xh = b.xh " +
	"left join view_xg_gygl_new_cwxx c on a.lddm=c.lddm and a.qsh=c.qsh and a.cwh=c.cwh) a where 1=1 ";*/
	//判断是否为报修分配查询
	StringBuilder sql = new StringBuilder();
	if("bxfp".equals(myForm.getFlag())){
		sql.append("select rownum r ,a.* from (select a.* from view_new_dc_gygl_bxsqgl_bxfp a where 1=1 order by fpzt asc,bxsj desc)a where 1=1");
	}else if(!"bxfp".equals(myForm.getFlag()) &&"12727".equals(Base.xxdm)){
		sql.append("select rownum r ,a.* from (select a.* from  view_new_dc_gygl_bxsqgl_bxfp a order by clzt asc,bxsj desc)a where 1=1 and fpbm ="+"'"+(String)request.getSession().getAttribute("userDep")+"'");

	}else{
		sql.append("select rownum r ,a.* from (select a.* from VIEW_NEW_DC_GYGL_BXSQGL a order by clzt asc,bxsj desc)a where 1=1 ");
	}
	
	
	return  commonQueryforExportList(sql.toString(),searchTj+searchTjQx,inputV,colList,myForm);
	
}
	
	
	public List<HashMap<String, String>> getBxlbList(){
		
		String sql = "select dm bxlbdm,mc bxlbmc from gygl_bxlbdmb order by dm";
		
		return dao.getListNotOut(sql, new String[]{});
	}
	
	public List<HashMap<String, String>> getBxlbzxList(String bxlb){
		
		String sql = "select * from gygl_bxlbzxb where bxlbdm=? order by bxlbzxdm";
		
		return dao.getListNotOut(sql, new String[]{bxlb});
	}
	
	public boolean isHasPzRy(){
		String sql = "select count(1) num from XG_GYGL_NEW_GYBXRYB";
		String num = dao.getOneRs(sql, new String[]{},"num");
		return Integer.parseInt(num)>0 ? true:false;
	}
	
	/**
	 * 
	 * @描述:获取分配部门list
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-15 上午10:30:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getfpbmList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select bmdm,bmmc from zxbz_xxbmdm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述:分配保存
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-15 下午01:54:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean gybxglfpUpdate(GybxglForm myForm) throws Exception{		
		StringBuilder sql = new StringBuilder();
		String[] inputs = new String[]{myForm.getFpzt(),myForm.getFpbm(),myForm.getPk()};
		if("12688".equals(Base.xxdm)){
			inputs = new String[]{myForm.getFpzt(),myForm.getFpbm(),myForm.getFpxq(),myForm.getPk()};
			sql.append("update xg_gygl_new_gybxb set fpzt=?,fpbm=?,fpxq=? ")
			.append("where xh||bxsj=? ");
		}else{
			sql.append("update xg_gygl_new_gybxb set fpzt=?,fpbm=? ")
			.append("where xh||bxsj=? ");
		}
		
		
		return dao.runUpdate(sql.toString(), inputs);
	}
	
	/**
	 * 查看学生信息以及相关报修信息
	 * @param pk
	 * @return
	 */
	public HashMap<String,String> viewfpXsxx(String pk,String xh){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.xh,b.lddm,b.ldmc,b.qsh,b.cwh,b.qsdh,a.xm,a.xymc,a.zymc,a.bjmc,a.nj,")
			.append("c.bxnr,c.jjcd,c.bxlb,(select mc from gygl_bxlbdmb where dm = c.bxlb) bxlbmc,c.lxdh,c.qwwxsj_ks,c.qwwxsj_js,c.clzt,c.bclyy,c.wxnr,c.wxry,")
			.append("c.wxsj,c.wxfy,c.mycd,c.pj,c.zbclyy, ")
			.append(" b.ldmc||'，'||b.qsh||'，'||b.cwh||'号床' zzqsmc,d.bxlbzxdm,d.bxlbzxmc,c.fpxq,y.xqmc fpxqmc,x.bmmc fpbmmc,decode(c.fpzt,'0','未分配','1','已分配','未分配') fpztmc,c.fpbm,c.fpzt,c.filepath ")
			.append("from view_xsjbxx a left join view_xg_gygl_new_cwxx b on a.xh = b.xh ")
			.append("left join xg_gygl_new_gybxb c on a.xh=c.xh and c.xh||c.bxsj=? left join gygl_bxlbzxb d on c.bxlbzxdm=d.bxlbzxdm ")
			.append(" left join zxbz_xxbmdm x")
			.append(" on c.fpbm = x.bmdm ")
			.append(" left join dm_zju_xq y")
			.append(" on c.fpxq = y.dm ")
			.append(" where a.xh=?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{pk,xh});
	}	
	
	/**
	 * 
	 * @描述:获取分配校区list
	 * @作者：lgx[工号：1553]
	 * @日期：2018-5-14 下午2:18:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getfpxqList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select dm,xqmc from DM_ZJU_XQ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
}
