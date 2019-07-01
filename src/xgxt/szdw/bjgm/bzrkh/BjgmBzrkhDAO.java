package xgxt.szdw.bjgm.bzrkh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

/**
 * 
* 
* 类名称：BjgmBzrkhDAO 
* 类描述：北京工贸班主任考核DAO
* 创建人：yijd 
* 创建时间：2012-6-1 上午09:20:00 
* 修改备注：  
* @version 
*
 */
public class BjgmBzrkhDAO extends CommDAO {
	private DAO dao=new DAO();
	//查询班主任考核信息列表
	public List<HashMap<String, String>> queryBzrkhxx(BjgmBzrkhForm model,HttpServletRequest request) throws Exception{
	//String sql=" select a.*,rownum r from view_xg_szdw_bjgm_bzrkhb a where 1=1 ";
		String sql = "select a.*,rownum r from "
				+ "(select "
				+ "vbzr.bmdm,"
				+ "vbzr.bmmc,"
				+ "vbzr.xb,"
				+ "vbzr.xn, "
				+ "vbzr.xq, "
				+ "vbzr.zgh, "
				+ "vbzr.xm as xm, "
				+ "vbzr.cq, "
				+ "vbzr.cl, "
				+ "vbzr.nj, "
				+ "vbzr.xsk, "
				+ "vbzr.grdf, "
				+ "sum(to_number(nvl(vbzr.wsaq, 0)) + to_number(nvl(vbzr.bb, 0)) + "
				+ "to_number(nvl(vbzr.sq, 0)) + to_number(nvl(vbzr.jl, 0)) + "
				+ "(to_number(nvl(vbzr.rs, 0)) * 2) - to_number(nvl(vbzr.kjc, 0))+to_number(nvl(vbzr.jli,0))) as bjdf, "
				+ "count(*) as bjzs, "
				+ "trunc(sum(to_number(nvl(vbzr.wsaq, 0)) + to_number(nvl(vbzr.bb, 0)) + "
				+ "to_number(nvl(vbzr.sq, 0)) + to_number(nvl(vbzr.jl, 0)) + "
				+ "(to_number(nvl(vbzr.rs, 0)) * 2) - to_number(nvl(vbzr.kjc, 0))+to_number(nvl(vbzr.jli,0))) / "
				+ "count(*)+to_number(nvl(vbzr.grdf, 0)),1) as grzf "
				+ "from VIEW_XG_SZDW_BJGM_BZRKHB vbzr where 1=1 " 
				+ "group by vbzr.bmdm,vbzr.bmmc,vbzr.xb,vbzr.xn,vbzr.xq,vbzr.zgh, "
				+ "vbzr.xm,vbzr.cq,vbzr.cl,vbzr.nj, "
				+ "vbzr.xsk,vbzr.grdf order by vbzr.xn desc, vbzr.xq desc) a where 1=1";
	// 高级查询条件
	String searchTj = SearchService.getSearchTj(model.getSearchModel());
	// 高级查询输入值
	String[] inputV = SearchService.getTjInput(model.getSearchModel());
	String[] outputV={"bmdm","bmmc","xb","xn","xq","zgh","xm","cq","cl","nj","xsk","grdf","bjdf","bjzs","grzf"};
	
	return CommonQueryDAO.commonQueryforList(sql, searchTj, inputV, outputV, model);
	}
	//查询班级信息根据职工号
	public List<HashMap<String, String>> queryBjxxByZgh(BjgmBzrkhForm model) throws Exception{
		String sql=" select bjdm.bjdm,bjdm.bjmc ,bjdm.xydm ,bjdm.zymc ,bjdm.xydm ,bjdm.xymc ,bjdm.nj" +
					" from view_njxyzybj bjdm where "+
					" exists ( "+
					" select bjdm from fdybjb bj where bj.zgh=? and bjdm.bjdm=bj.bjdm "+
					" union "+
					" select bjdm from bzrbbb bb where bb.zgh=? and bjdm.bjdm=bb.bjdm) ";
		if(model==null){
			return null;
		}
		String[] outputValue={"bjdm","bjmc","xydm","zymc","xydm","xymc","nj"};
		String[] inputValue={model.getZgh(),model.getZgh()};
		return dao.getArrayList(sql, inputValue, outputValue);
	}
	
	//老师审核批量保存
	public int[] insertBzrshs(BjgmBzrkhForm form) throws Exception{
		String[] sql={};
		if(form!=null){
			if(form.getBb()!=null){
				sql=new String[form.getBb().length];
				for (int i = 0; i < form.getBb().length; i++) {
					sql[i]="insert into xg_szdw_bjgm_bzrkhb(xn,xq,zgh,wsaq,bb,sq,jl," +
							"rs,jli,cq,cl,kjc,nj,xsk,bjdm)"
							+"values('"+form.getXn()+"','"+form.getXq()+"','"+form.getZgh()
							+"','"+form.getWsaq()[i]+"','"+form.getBb()[i]+"','"+form.getSq()[i]+"'" +
							",'"+form.getJl()[i]+"','"+form.getRs()[i]+"','"+form.getJli()[i]+"'," +
							"'"+form.getCq()+"','"+form.getCl()+"','"+form.getKjc()[i]+"'," +
							"'"+form.getNj()+"','"+form.getXsk()+"','"+form.getBjdms()[i]+"')";
				}
			}else{
				sql=new String[1];
				sql[0]="insert into xg_szdw_bjgm_bzrkhb(xn,xq,zgh,wsaq,bb,sq,jl," +
				"rs,jli,cq,cl,kjc,nj,xsk,bjdm)"
				+"values('"+form.getXn()+"','"+form.getXq()+"','"+form.getZgh()
				+"','','','','','',''," +
				"'"+form.getCq()+"','"+form.getCl()+"',''," +
				"'"+form.getNj()+"','"+form.getXsk()+"','')";
			}
		}
		int[] rows=dao.runBatch(sql);
		return rows;//dao.insert(sql, input);
	}
	
	//查询数据集合  根据学年学期职工号查询
	public List<HashMap<String, String>> getBjgmBzrkh(BjgmBzrkhForm model){
		String sql="select * from view_xg_szdw_bjgm_bzrkhb bzrkh " +
				"where bzrkh.xn=? and bzrkh.xq=? and bzrkh.zgh=? ";
		String[] inputValue={model.getXn(),model.getXq(),model.getZgh()};
		String[] outputValue={"xn","xq","zgh","bjdm","bjmc","wsaq","bb","sq","jl","rs","jli",
				"kjc","bjdf","xm","cq","cl","nj","xsk","grdf","grzf"};
		return dao.getList(sql, inputValue, outputValue);
		
	}
	
	//查询数据集合  根据学年学期职工号查询 用于更新
	public List<HashMap<String, String>> getBjgmBzrkhOnUpdate(BjgmBzrkhForm model) {
		String sql = "select a.bjdm,a.bjmc,b.xn,b.xq,b.zgh,b.wsaq,b.bb,b.sq,b.jl,b.rs,b.jli,b.kjc,b.bjdf, " +
				" b.xm,b.cq,b.cl,b.nj,b.xsk,b.grdf,b.grzf from " +
				"(select bjdm.bjdm,bjdm.bjmc from view_njxyzybj bjdm where exists " +
				"(select bjdm from fdybjb bj where bj.zgh = ? and bjdm.bjdm = bj.bjdm " +
				"union select bjdm from bzrbbb bb where bb.zgh = ? and bjdm.bjdm = bb.bjdm)) a " +
				"left join (select * from view_xg_szdw_bjgm_bzrkhb bzrkh where bzrkh.xn = ? " +
				"and bzrkh.xq = ? and bzrkh.zgh = ?) b on a.bjdm = b.bjdm";
		String[] inputValue = {model.getZgh(), model.getZgh(),model.getXn(), model.getXq(), model.getZgh() };
		String[] outputValue={"xn","xq","zgh","bjdm","bjmc","wsaq","bb","sq","jl","rs","jli",
				"kjc","bjdf","xm","cq","cl","nj","xsk","grdf","grzf"};
		return dao.getList(sql, inputValue, outputValue);

	}
	
	//删除班主任考核信息
	public boolean deleteBjgmBzrkh(List<String[]> ids) throws Exception{
		String sql=" delete xg_szdw_bjgm_bzrkhb where xn=? and xq=? and zgh=? ";
		int[] rows=dao.runBatch(sql, ids);
		boolean flag = dao.checkBatchResult(rows);
		return flag;
	}
	
	//批量修改
	public boolean updateBjgmBzrkh(BjgmBzrkhForm model,User user) throws Exception{
		if(model==null){
			return false;
		}
		String tableName="xg_szdw_bjgm_bzrkhb";
		String sql="update xg_szdw_bjgm_bzrkhb bjgm set wsaq=?,bb=?,sq=?,jl=?,rs=?, " +
					"jli=?,cq=?,cl=?,kjc=?,nj=?,xsk=? " +
					"where xn=? and xq=? and zgh=? and bjdm=? ";
		String[] input = null;
		List<String[]> list = new ArrayList<String[]>();
		for (int i = 0; i < model.getBb().length; i++) {
			input = new String[] { model.getWsaq()[i], model.getBb()[i],
					model.getSq()[i], model.getJl()[i], model.getRs()[i],
					model.getJli()[i], model.getCq(), model.getCl(),
					model.getKjc()[i], model.getNj(), model.getXsk(),
					model.getXn(), model.getXq(), model.getZgh(),
					model.getBjdms()[i] };
			list.add(input);
		}
		return saveArrDate(sql, list, tableName, user);
		
	}
	
	//清除多余班级考核分数
	public int[] deleteBjgmBzrkhByFutile(BjgmBzrkhForm model) throws Exception{
		String sql="delete xg_szdw_bjgm_bzrkhb bzrkh where  bzrkh.xn = ?" +
				" and bzrkh.xq = ? and bzrkh.zgh = ? and " +
				"not exists(select bjdm.bjdm,bjdm.bjmc from view_njxyzybj bjdm " +
				"where bjdm.bjdm=bzrkh.bjdm and exists " +
				"(select bjdm from fdybjb bj where bj.zgh = ? " +
				"and bjdm.bjdm = bj.bjdm union select bjdm " +
				"from bzrbbb bb where bb.zgh = ? and bjdm.bjdm = bb.bjdm)) ";
		List<String[]> params=new ArrayList<String[]>();
		if(model!=null){
			String[] param={model.getXn(),model.getXq(),model.getZgh(),model.getZgh(),model.getZgh()};
			params.add(param);
		}
		return dao.runBatch(sql, params);
	}
	
	
}
