package xsgzgl.wjcf.cfssgl;

import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

/**
 * 
 * 
 * 类名称：WjcfJcszDao 
 * 类描述：违纪处分申诉管理Dao 
 * 创建人：yijd 
 * 创建时间：2012-6-19 上午09:20:00 
 * 修改备注：
 * 
 * @version
 * 
 */
public class WjcfCfssglDao extends DAO {
	private DAO dao=DAO.getInstance();
	/**
	 * 违纪处分 处分申诉管理
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public List<String[]> cfssglCx(WjcfCfssglForm model,String userType,User user) throws Exception {
		//高级查询
		SearchModel searchModel=model.getSearchModel();
		String searchTj=SearchService.getSearchTj(searchModel);	
		String []input=SearchService.getTjInput(searchModel);
		
		StringBuffer sql=new StringBuffer();
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		sql.append(" select a.*, ");
		sql.append(" rownum r from (select a.*, ");
		sql.append(" b.ssjg, ");
		sql.append(" (case ");
		sql.append(" when b.ssjg is null or b.ssjg = 'wsh' then ");
		sql.append(" '未审核' ");
		sql.append(" when b.ssjg = 'shz' then ");
		sql.append(" '审核中' ");
		sql.append(" when b.ssjg = 'wcycf' then ");
		sql.append(" '维持原处分' ");
		sql.append(" when b.ssjg = 'cxcf' then ");
		sql.append(" '撤销处分' ");
		sql.append(" when b.ssjg = 'ggcf' then ");
		sql.append(" '更改处分' ");
		sql.append(" end) ssshzt ");
		sql.append(" from (select a.xh, ");
		sql.append(" b.xm, ");
		sql.append(" b.xydm, ");
		sql.append(" b.bjdm, ");
		sql.append(" b.zydm, ");
		sql.append(" a.xn, ");
		sql.append(" b.nj, ");
		sql.append(" f.xqmc, ");
		sql.append(" f.xqdm xq, ");
		sql.append(" a.cflbmc, ");
		sql.append(" a.cfyymc, ");
		sql.append(" c.cflbdm, ");
		sql.append(" d.cfyydm, ");
		sql.append(" a.sswh, ");
		sql.append(" a.sssj, ");
		sql.append(" a.cfid, ");
		sql.append(" (case when to_number(to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd')-to_date(a.cfsj,'yyyy-MM-dd')) <= c.ssslgzr ");
		sql.append(" and instr(c.sfkss,?) >0 then 'y' else 'n' end) ssyz ");
		sql.append(" from xg_wjcf_wjcfb a left join view_xsxxb b on a.xh = b.xh left join xg_wjcf_cflbdmb c ");
		sql.append(" on a.cflbmc = c.cflbmc left join xg_wjcf_cfyydmb d on a.cfyymc=d.cfyymc ");
		sql.append(" left join xqdzb f ");
		sql.append(" on a.xq=f.xqdm ");
		sql.append(" where c.sfkss <> 'no') a ");
		sql.append(" left join xg_wjcf_wjcfssb b ");
		sql.append("  on a.cfid = b.cfid) a where 1=1 ");
		
		sql.append(searchTj);
		
		//用户类别数据权限
		String[] yhlbsjqx=new String[]{};
		String[] outputValue = new String[] { "xh", "xm", "xn","xqmc",
				"cflbmc", "cfyymc", "sswh", "sssj", "ssjg","ssshzt" ,"cfid" ,"ssyz" };
		String[] inputValue=arrayAppendArray(new String[]{userType},input);
		inputValue=arrayAppendArray(inputValue,yhlbsjqx);
		sql.append(searchTjByUser);
		return (ArrayList<String[]>)CommonQueryDAO
		.commonPageQuery(model.getPages(), sql.toString(), inputValue,outputValue);
	}
	
	
	/**
	 * 违纪处分 查看申诉信息 tl
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> cfssglCk(String pkValue) throws Exception {
		StringBuffer sql=new StringBuffer();
		sql.append(" select a.*, b.sqsj, b.sqly,b.fjmc ssfjmc ");
		sql.append(" from (select a.cfid, ");
		sql.append(" b.xh, ");
		sql.append(" b.xm, ");
		sql.append(" b.xb, ");
		sql.append(" b.nj, ");
		sql.append(" b.xymc, ");
		sql.append(" b.zymc, ");
		sql.append(" b.bjmc, ");
		sql.append(" a.xn, ");
		sql.append(" c.xqmc xq, ");
		sql.append(" a.cflbmc, ");
		sql.append(" a.cfyymc, ");
		sql.append(" a.cfwh, ");
		sql.append(" a.wjsj, ");
		sql.append(" a.cfsj, ");
		sql.append(" a.wjssjg, ");
		sql.append(" a.bz, ");
		sql.append(" a.sssj, ");
		sql.append(" a.sswh, ");
		sql.append(" a.fjmc cffjmc ");
		sql.append(" from xg_wjcf_wjcfb a, view_xsxxb b ,xqdzb c ");
		sql.append(" where a.cfid = ? ");
		sql.append(" and a.xh = b.xh  and a.xq=c.xqdm) a ");
		sql.append(" left join xg_wjcf_wjcfssb b ");
		sql.append(" on a.cfid = b.cfid ");
		String[] inputValue=new String[]{pkValue};
		String[] outputValue = new String[] { "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc", "xn", "xq", "cflbmc", "cfyymc", "cfwh", "wjsj",
				"cfsj", "wjssjg", "bz", "sswh", "sqsj", "sqly","cffjmc","ssfjmc","sssj" };
		return dao.getMap(sql.toString(), inputValue, outputValue);
	}
	
	
	/**
	 * 违纪处分 查看申诉信息 只含申诉
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> cfssCk(String pkValue) throws Exception {
		StringBuffer sql=new StringBuffer();
		sql.append(" select cfid,sqsj,sqly,ssjg from xg_wjcf_wjcfssb where cfid=? " );
		String[] inputValue=new String[]{pkValue};
		String[] outputValue = new String[] { "cfid","sqsj","sqly","ssjg" };
		return dao.getMap(sql.toString(), inputValue, outputValue);
	}
	
	
	/**
	 * 根据处分ID 查询申述审核信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> ssshxxCk(String pkValue)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.shyj, a.shsj, a.mc, b.xm shr, ");
		
		sql.append(" case when a.shzt = 'ggcf' then  '更改处分' ");
		
		sql.append("  when a.shzt = 'wcycf' then '维持原处分' ");
		
		sql.append("  when a.shzt = 'cxcf' then '撤销处分' ");
		
		sql.append("  when a.shzt = 'tg' then '通过' ");
		
		sql.append(" when a.shzt = 'btg' then '不通过' ");
		
		sql.append(" when a.shzt = 'th' then '退回' ");
		
		sql.append(" when a.shzt = 'wsh' then '未审核'  else a.shzt  end shjg,a.cfggw");
		
		sql.append(" from (select a.shyj, a.shr, a.shsj, b.mc, a.shzt, a.cfggw ");
		sql.append(" from xg_wjcf_wjcfssshb a, ");
		sql.append(" xg_xtwh_spgw b, ");
		sql.append(" (select * ");
		sql.append(" from xg_xtwh_spbz a ");
		sql.append(" where exists (select b.ssspl ");
		sql.append(" from xg_wjcf_ssjcsplb b ");
		sql.append(" where a.splc = b.ssspl)) c ");
		sql.append(" where a.cfid = ? ");
		sql.append(" and a.xtgwid = b.id ");
		sql.append(" and a.xtgwid = c.spgw ");
		sql.append(" order by c.xh) a ");
		sql.append(" left join yhb b ");
		sql.append(" on a.shr = b.yhm ");

		String[] inputValue = new String[] { pkValue };
		String[] outputValue = new String[] { "shsj", "shr", "shyj", "mc" ,"shjg","cfggw"};
		return dao.getList(sql.toString(), inputValue, outputValue);
	}
	
	/**
	 * 保存处分申诉信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cfssZj(WjcfCfssglForm model,InputStream is) throws Exception{
		StringBuffer insertSql=new StringBuffer();
		insertSql.append(" insert into xg_wjcf_wjcfssb(fj,cfid,sqsj,sqly,ssjg,fjmc) values(?,?,?,?,?,?) ");
		boolean flag = dao.updateBlob(insertSql.toString(), new String[]{model.getCfid(),GetTime.getNowTime2(),model.getSqly(),model.getSsjg(),model.getSsfjmc()
				},is.available(), is);
		

		return flag;
	}
	
	/**
	 * 修改处分申诉信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cfssXg(WjcfCfssglForm model,InputStream is) throws Exception{
		boolean	flag = false;
		String sql ="";
		String fjmc = model.getSsfjmc();
		if (StringUtils.isNotNull(fjmc)) {
					 sql = " update xg_wjcf_wjcfssb set fj=?, sqly=?,fjmc=? where cfid=? " ;
					flag =  dao.updateBlob(sql, new String[]{ model.getSqly(),model.getSsfjmc(),
							model.getCfid()},model.getSsfj().getInputStream().available(), 
							model.getSsfj().getInputStream());
		}else{
			sql = "update xg_wjcf_wjcfssb set sqly=? where cfid=?" ;
			flag =  dao.runUpdate(sql, new String[]{model.getSqly(),model.getCfid()});
		}

		return flag;
	}
	
	/**
	 * 删除违纪处分申诉附件
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean cfssSc(List<String[]> pkValue)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" delete xg_wjcf_wjcfssb where cfid=? ");
		int[] rows = dao.runBatch(sql.toString(), pkValue);
		boolean flag = dao.checkBatchResult(rows);
		return flag;
	}
	
	/**
	 * 增加申诉审核
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cfssshZj(List<WjcfCfssglForm> modelList) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into xg_wjcf_wjcfssshb(cfid,xtgwid,shzt,shsj,shyj,shr,sswh,sssj) ");
		sql.append(" values(?,?,?,?,?,?,?,?) ");
		List<String[]> inputValue=new ArrayList<String[]>();
		String[] input=null;
		for (WjcfCfssglForm cf : modelList) {
			input=new String[8];
			input[0]=cf.getCfid();
			input[1]=cf.getXtgwid();
			input[2]=cf.getShzt();
			input[3]=cf.getShsj();
			input[4]=cf.getShyj();
			input[5]=cf.getShr();
			input[6]=cf.getSswh();
			input[7]=cf.getSssj();
			inputValue.add(input);
		}
		
		int[] rows = dao.runBatch(sql.toString(), inputValue);
		boolean flag = dao.checkBatchResult(rows);
		return flag;
		
	}
	
	/**
	 * 查询审核流程   根据处分ID
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String[] shlcCx()throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append(" select b.spgw from xg_wjcf_ssjcsplb a,xg_xtwh_spbz b where a.ssspl=b.splc order by b.xh ");
		return dao.getArray(sql.toString(), new String[]{}, "spgw");
	}
	
	/**
	 * 申诉解除审批岗位
	 * @return
	 * @throws Exception
	 */
	public String[] getSsjcSpgw()throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append(" select b.spgw from xg_wjcf_ssjcsplb a,xg_xtwh_spbz b where a.jcspl=b.splc order by b.xh ");
		return dao.getArray(sql.toString(), new String[]{}, "spgw");
	}
	
	
	
	/**
	 * 查询审批岗位  根据用户名
	 * @param yhm 用户id
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> spgwCx(String yhm) throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append(" select c.spyh,d.id spgw,d.mc gwmc ");
		sql.append(" from xg_wjcf_ssjcsplb a,xg_xtwh_spbz b,xg_xtwh_spgwyh c,xg_xtwh_spgw d where a.ssspl=b.splc and b.spgw=c.spgw ");
		sql.append(" and c.spyh=? and c.spgw=d.id ");
		String[] inputValue=new String[]{yhm};
		String[] outputValue=new String[]{"spyh","spgw","gwmc"};
		return dao.getList(sql.toString(), inputValue, outputValue);
	}
	
	/**
	 * 删除违纪处分申诉审核   根据处分ID
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cfssshSc(WjcfCfssglForm model)throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append(" delete xg_wjcf_wjcfssshb where cfid=? ");
		String[] inputValue=new String[]{model.getCfid()};
		return dao.runUpdate(sql.toString(), inputValue);
		
	}
	
	
	/**
	 * 违纪处分 处分申诉审核查询
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public List<String[]> cfssshCx(WjcfCfssglForm model,String userType,User user) throws Exception {
		//高级查询
		SearchModel searchModel=model.getSearchModel();
		
		StringBuffer sql=new StringBuffer();
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		
		String searchTj=SearchService.getSearchTj(searchModel);	
		String []input=SearchService.getTjInput(searchModel);
		sql.append(" select a.*, ");
		sql.append(" rownum r from ( ");
		
		sql.append(" select a.*,  ");
		sql.append(" (case ");
		sql.append(" when b.xjgw is not null or a.sftj='1' then ");
		sql.append(" 'disabled' ");
		sql.append(" else ");
		sql.append(" '' ");
		sql.append(" end) xjgw ");
		sql.append(" from (select a.xtgwid, ");
		sql.append(" b.xn, ");
		sql.append(" c.xydm, ");
		sql.append(" c.zydm, ");
		sql.append(" c.bjdm, ");
		sql.append(" b.xh, ");
		sql.append(" c.xm, ");
		sql.append(" c.nj, ");
		sql.append(" b.cflbmc, ");
		sql.append(" e.cflbdm, ");
		sql.append(" b.cfyymc, ");
		sql.append(" f.cfyydm, ");
		sql.append(" a.sswh, ");
		sql.append(" a.sssj, ");
		sql.append(" d.ssjg, ");
		sql.append(" b.cfid, ");
		sql.append(" g.xqmc, ");
		sql.append(" g.xqdm xq, ");
		sql.append(" a.shzt, ");
		sql.append(" a.sftj ");
		sql.append(" from xg_wjcf_wjcfssshb a ");
		sql.append(" left join xg_wjcf_wjcfb b ");
		sql.append(" on a.cfid = b.cfid ");
		sql.append(" left join view_xsxxb c ");
		sql.append(" on b.xh = c.xh ");
		sql.append(" left join xg_wjcf_wjcfssb d ");
		sql.append(" on a.cfid = d.cfid ");
		sql.append(" left join xg_wjcf_cflbdmb e ");
		sql.append(" on b.cflbmc = e.cflbmc ");
		sql.append(" left join xg_wjcf_cfyydmb f ");
		sql.append(" on b.cfyymc = f.cfyymc ");
		sql.append(" left join xqdzb g ");
		sql.append(" on b.xq=g.xqdm ");
		sql.append(" where a.xtgwid = ?) a ");
		sql.append(" left join (select a.cfid, a.xtgwid xjgw ");
		sql.append(" from xg_wjcf_wjcfssshb a ");
		sql.append(" where a.xtgwid = ? ");
		sql.append(" and (a.shzt = 'tg' or a.shzt = 'btg' or a.shzt = 'wcycf' or a.shzt = 'ggcf' or a.shzt = 'cxcf')) b ");
		sql.append(" on a.cfid = b.cfid ");

		sql.append(" ) a where 1=1 ");
		
		//自定义查询条件
		String[] inp=new String[]{model.getXtgwid(),model.getXjSpgw()};
		if(model!=null && model.getXtgwid()!=null){
			if(!model.getXtgwid().equals(model.getSjSpgw())){
				if(model.getSjSpgw()!=null && !"".equals(model.getSjSpgw())){
					sql.append(" and a.cfid in (select cfid from xg_wjcf_wjcfssshb where xtgwid=? and shzt='tg') ");
					//sql.append(" and a.xtgwid=? ");
					inp=arrayAppendArray(inp,new String[]{model.getSjSpgw()});
				}else{
					//sql.append(" and a.xtgwid=? ");
				}
			}else{
				//sql.append(" and a.xtgwid=? ");
				//inp=arrayAppendArray(inp,new String[]{model.getXtgwid()});
			}
		}
		
		
		//高级查询条件
		sql.append(searchTj);
		
		//用户类别数据权限
		String[] outputValue = new String[] {"xn","xqmc","xh","xm","cflbmc","cfyymc","sswh","sssj","ssjg","cfid","shzt","xjgw" };
		String[] inputValue=arrayAppendArray(inp,input);
		sql.append(searchTjByUser);
		return (ArrayList<String[]>)CommonQueryDAO
		.commonPageQuery(model.getPages(), sql.toString(), inputValue,outputValue);
	}
	
	/**
	 * 获取当前审批流程的 上一个环节审批岗位，
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> splcSjSpgw(WjcfCfssglForm model)throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append(" select a.splc, a.id, a.spgw, a.xh ");
		sql.append(" from xg_xtwh_spbz a, ");
		sql.append(" (select decode(xh, 1, 1, xh - 1) xh, a.splc ");
		sql.append(" from xg_xtwh_spbz a, XG_WJCF_SSJCSPLB b ");
		sql.append(" where a.splc = b.ssspl ");
		sql.append(" and a.spgw = ?) b ");
		sql.append(" where a.splc = b.splc ");
		sql.append(" and a.xh = b.xh ");
		
		String[] inputValue=new String[]{model.getXtgwid()};
		String[] outputValue=new String[]{"id","splc","xh","spgw"};
		return dao.getMap(sql.toString(), inputValue, outputValue);
	}
	
	
	/**
	 * 获取当前审批流程的 下一个环节审批岗位，
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> splcXjSpgw(WjcfCfssglForm model)throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append(" select a.splc, a.id, a.spgw, a.xh ");
		sql.append(" from xg_xtwh_spbz a, ");
		sql.append(" (select (case ");
		sql.append(" when a.xh < b.xh then ");
		sql.append(" to_char(to_number(a.xh) + 1) ");
		sql.append(" when a.xh = b.xh then ");
		sql.append(" to_char(to_number(a.xh)) ");
		sql.append(" else ");
		sql.append(" to_char(to_number(a.xh)) ");
		sql.append(" end) xh, ");
		sql.append(" a.splc ");
		sql.append(" from (select xh, a.splc ");
		sql.append(" from xg_xtwh_spbz a, XG_WJCF_SSJCSPLB b ");
		sql.append(" where a.splc = b.ssspl ");
		sql.append(" and a.spgw = ?) a, ");
		sql.append(" (select max(xh) xh, a.splc ");
		sql.append(" from xg_xtwh_spbz a, XG_WJCF_SSJCSPLB b ");
		sql.append(" where a.splc = b.ssspl ");
		sql.append(" group by a.splc) b ");
		sql.append(" where a.splc = b.splc) b ");
		sql.append(" where a.splc = b.splc ");
		sql.append(" and a.xh = b.xh ");

		
		String[] inputValue=new String[]{model.getXtgwid()};
		String[] outputValue=new String[]{"id","splc","xh","spgw"};
		return dao.getMap(sql.toString(), inputValue, outputValue);
	}
	
	/**
	 * 获取当前审批流程的 上一个环节审批信息， 如果是第一个环节    审批岗位就获取第一个   根据信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> splcSjsh(WjcfCfssglForm model)throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append(" select a.cfid, ");
		sql.append(" a.shzt, ");
		sql.append(" a.shsj, ");
		sql.append(" a.shyj, ");
		sql.append(" a.shr, ");
		sql.append(" a.sftj, ");
		sql.append(" a.sswh, ");
		sql.append(" a.sssj, ");
		sql.append(" b.id, ");
		sql.append(" b.splc, ");
		sql.append(" b.xh, ");
		sql.append(" b.spgw ");
		sql.append(" from xg_wjcf_wjcfssshb a, ");
		sql.append(" (select a.splc, a.id, a.spgw, a.xh ");
		sql.append(" from xg_xtwh_spbz a, ");
		sql.append(" (select decode(xh, 1, 1, xh - 1) xh, a.splc ");
		sql.append(" from xg_xtwh_spbz a, XG_WJCF_SSJCSPLB b ");
		sql.append(" where a.splc = b.ssspl ");
		sql.append(" and a.spgw = ? ) b ");
		sql.append(" where a.splc = b.splc ");
		sql.append(" and a.xh = b.xh) b ");
		sql.append(" where a.cfid = ? ");
		sql.append(" and a.xtgwid = b.spgw ");
		
		String[] inputValue=new String[]{model.getXtgwid(),model.getCfid()};
		String[] outputValue=new String[]{"cfid","shzt","shsj","shyj","shr","sftj","sswh","sssj"
				,"id","splc","xh","spgw"};
		return dao.getMap(sql.toString(), inputValue, outputValue);
	}
	
	
	/**
	 * 获取当前审批流程的 下一个环节审批岗位， 如果是最后一个环节  审批岗位就获取空   根据岗位
	 * @param model
	 * @return
	 * @throws Exception  退回
	 */
	public HashMap<String, String> splcXjsh(WjcfCfssglForm model)throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append(" select a.cfid, ");
		sql.append(" a.shzt, ");
		sql.append(" a.shsj, ");
		sql.append(" a.shyj, ");
		sql.append(" a.shr, ");
		sql.append(" a.sftj, ");
		sql.append(" a.sswh, ");
		sql.append(" a.sssj, ");
		sql.append(" b.id, ");
		sql.append(" b.splc, ");
		sql.append(" b.xh, ");
		sql.append(" b.spgw ");
		sql.append(" from xg_wjcf_wjcfssshb a, ");
		sql.append(" (select a.splc, a.id, a.spgw, a.xh ");
		sql.append(" from xg_xtwh_spbz a, ");
		sql.append(" (select (case ");
		sql.append(" when a.xh < b.xh then ");
		sql.append(" to_char(to_number(a.xh) + 1) ");
		sql.append(" when a.xh = b.xh then ");
		sql.append(" to_char(to_number(a.xh)) ");
		sql.append(" else ");
		sql.append(" to_char(to_number(a.xh)) ");
		sql.append(" end) xh, ");
		sql.append(" a.splc ");
		sql.append(" from (select xh, a.splc ");
		sql.append(" from xg_xtwh_spbz a, XG_WJCF_SSJCSPLB b ");
		sql.append(" where a.splc = b.ssspl ");
		sql.append(" and a.spgw = ?) a, ");
		sql.append(" (select max(xh) xh, a.splc ");
		sql.append(" from xg_xtwh_spbz a, XG_WJCF_SSJCSPLB b ");
		sql.append(" where a.splc = b.ssspl ");
		sql.append(" group by a.splc) b ");
		sql.append(" where a.splc = b.splc) b ");
		sql.append(" where a.splc = b.splc ");
		sql.append(" and a.xh = b.xh) b ");
		sql.append(" where a.cfid = ? ");
		sql.append(" and a.xtgwid = b.spgw ");
		
		
		String[] inputValue=new String[]{model.getXtgwid(),model.getCfid()};
		String[] outputValue=new String[]{"cfid","shzt","shsj","shyj","shr","sftj","sswh","sssj"
				,"id","splc","xh","spgw"};
		return dao.getMap(sql.toString(), inputValue, outputValue);
	}
	
	/**
	 * 工具类   用于合并数组
	 * @param beginArray
	 * @param endArray
	 * @return
	 */
	public String[] arrayAppendArray(String[] beginArray,String[] endArray){
		if(beginArray==null || endArray==null){
			return null;
		}
		String[] newArray=new String[(beginArray.length+endArray.length)];
		int p=0;
		for (int i = 0; i < beginArray.length; i++) {
			newArray[p]=beginArray[i];
			p++;
		}
		for (int i = 0; i < endArray.length; i++) {
			newArray[p]=endArray[i];
			p++;
		}
		return newArray;
	}
	
	/**
	 * 审批流程顶级   最高级     根据当前审批
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> spldjCx(WjcfCfssglForm model)throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append(" select c.splc, c.xh,c.spgw,c.id ");
		sql.append(" from xg_wjcf_wjcfssshb a, xg_wjcf_ssjcsplb b, xg_xtwh_spbz c ");
		sql.append(" where c.splc = b.ssspl ");
		sql.append(" and a.xtgwid = c.spgw ");
		sql.append(" and a.cfid = ? ");
		//sql.append(" and a.xtgwid = ? ");
		sql.append(" and c.xh = (select max(to_number(xh)) xh ");
		sql.append(" from xg_xtwh_spbz a, xg_wjcf_ssjcsplb b ");
		sql.append(" where a.splc = b.ssspl) ");

		String[] inputValue=new String[]{model.getCfid()};
		String[] outputValue=new String[]{"splc","xh","spgw","id"};
		return dao.getMap(sql.toString(), inputValue, outputValue);
	}
	
	
	/**
	 * 审批流程等级   第一级     根据当前审批
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> splyjCx(WjcfCfssglForm model)throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append(" select c.splc, c.xh,c.spgw,c.id ");
		sql.append(" from xg_wjcf_wjcfssshb a, xg_wjcf_ssjcsplb b, xg_xtwh_spbz c ");
		sql.append(" where c.splc = b.ssspl ");
		sql.append(" and a.xtgwid = c.spgw ");
		sql.append(" and a.cfid = ? ");
		sql.append(" and c.xh = '1' ");

		String[] inputValue=new String[]{model.getCfid()};
		String[] outputValue=new String[]{"splc","xh","spgw","id"};
		return dao.getMap(sql.toString(), inputValue, outputValue);
	}
	/**
	 * 修改申诉审核修改
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean ssshXg(WjcfCfssglForm model) throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append(" update xg_wjcf_wjcfssshb set shzt=?,shsj=to_char(sysdate,'yyyy-MM-dd'),shyj=?, ");
		sql.append(" shr=?,sftj=?,sswh=?,sssj=?,cfggw=? ");
		sql.append(" where cfid=? and xtgwid=? ");
		String[] input=new String[]{model.getShzt(),model.getShyj(),model.getShr(),model.getSftj()
				,model.getSswh(),model.getSssj(),model.getCfggw(),model.getCfid(),model.getXtgwid()};
		return dao.runUpdate(sql.toString(), input);
	}
	
	/**
	 * 修改申诉审核修改  -- 审核状态
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean ssshXgShzt(WjcfCfssglForm model) throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append(" update xg_wjcf_wjcfssshb set shzt=? ");
		sql.append(" where cfid=? and xtgwid=? ");
		String[] input=new String[]{model.getShzt(),model.getCfid(),model.getXtgwid()};
		return dao.runUpdate(sql.toString(), input);
	}
	
	/**
	 * 批量修改
	 * @param modelList
	 * @return
	 * @throws Exception
	 */
	public boolean ssshPlxg(List<WjcfCfssglForm> modelList) throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append(" update xg_wjcf_wjcfssshb set shzt=?,shsj=to_char(sysdate,'yyyy-MM-dd'),shyj=?, ");
		sql.append(" shr=?,sftj=?,sswh=?,sssj=?,cfggw=? ");
		sql.append(" where cfid=? and xtgwid=? ");
		
		List<String[]> inputValue=new ArrayList<String[]>();
		String[] input=null;
		for (int i = 0; i < modelList.size(); i++) {
			input=new String[]{modelList.get(i).getShzt(),modelList.get(i).getShyj(),modelList.get(i).getShr(),modelList.get(i).getSftj()
					,modelList.get(i).getSswh(),modelList.get(i).getSssj(),modelList.get(i).getCfggw(),modelList.get(i).getCfid(),modelList.get(i).getXtgwid()};
			inputValue.add(input);
		}
		int[] rows=dao.runBatch(sql.toString(), inputValue);
		boolean flag = dao.checkBatchResult(rows);
		return flag;
	}
	
	
	/**
	 * 批量修改申诉审核修改  -- 审核状态
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean ssshPlxgShzt(List<WjcfCfssglForm> modelList) throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append(" update xg_wjcf_wjcfssshb set shzt=? ");
		sql.append(" where cfid=? and xtgwid=? ");
		
		List<String[]> inputValue=new ArrayList<String[]>();
		String[] input=null;
		for (int i = 0; i < modelList.size(); i++) {
			input=new String[]{modelList.get(i).getShzt(),modelList.get(i).getCfid(),modelList.get(i).getXtgwid()};
			inputValue.add(input);
		}
		int[] rows=dao.runBatch(sql.toString(), inputValue);
		boolean flag = dao.checkBatchResult(rows);
		return flag;
	}
	
	/**
	 * 查询申诉审核信息 根据处分id
	 * @param model  包含处分ID的model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> ssshCxGjCfid(WjcfCfssglForm model) throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append(" select a.cfid, ");
		sql.append(" a.shzt, ");
		sql.append(" (case ");
		sql.append(" when a.shzt is null or a.shzt = 'wsh' then ");
		sql.append(" '未审核' ");
		sql.append(" when a.shzt = 'shz' then ");
		sql.append(" '审核中' ");
		sql.append(" when a.shzt = 'tg' then ");
		sql.append(" '通过' ");
		sql.append(" when a.shzt = 'btg' then ");
		sql.append(" '不通过' ");
		sql.append(" when a.shzt = 'th' then ");
		sql.append(" '退回' ");
		sql.append(" when a.shzt = 'wcycf' then ");
		sql.append(" '维持原处分' ");
		sql.append(" when a.shzt = 'cxcf' then ");
		sql.append(" '撤销处分' ");
		sql.append(" when a.shzt = 'ggcf' then ");
		sql.append(" '更改处分' ");
		sql.append(" end) shztzw, ");
		sql.append(" a.shsj, ");
		sql.append(" a.shyj, ");
		sql.append(" a.shr, ");
		sql.append("(select m.xm from yhb m where a.shr = m.yhm) shrmc,");
		sql.append(" a.sftj, ");
		sql.append(" a.sswh, ");
		sql.append(" a.sssj, ");
		sql.append(" b.id, ");
		sql.append(" b.splc, ");
		sql.append(" b.xh, ");
		sql.append(" b.spgw, ");
		sql.append(" b.gwmc ");
		sql.append(" from xg_wjcf_wjcfssshb a, ");
		sql.append(" (select a.id, a.splc, a.xh, a.spgw, c.mc gwmc ");
		sql.append(" from xg_xtwh_spbz a, xg_wjcf_ssjcsplb b, xg_xtwh_spgw c ");
		sql.append(" where a.splc = b.ssspl ");
		sql.append(" and a.spgw = c.id) b ");
		sql.append(" where a.cfid = ? ");
		sql.append(" and a.xtgwid = b.spgw ");
		sql.append(" order by b.xh ");
		
		String[] inputValue=new String[]{model.getCfid()};
		String[] outputValue=new String[]{"cfid","shzt","shsj","shyj","shrmc","sftj","sswh","sssj"
				,"id","splc","xh","spgw","gwmc","shztzw"};
		return dao.getList(sql.toString(), inputValue, outputValue);
	}
	
	/**
	 * 查看申诉审核根据 cfid 和 gwjb
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> ssshCkGjCfidGwjb(WjcfCfssglForm model) throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append(" select a.cfid, ");
		sql.append(" a.xtgwid, ");
		sql.append(" a.shzt, ");
		sql.append(" a.shsj, ");
		sql.append(" a.shyj, ");
		sql.append(" a.shr, ");
		sql.append(" a.sftj, ");
		sql.append(" a.sswh, ");
		sql.append(" a.sssj ");
		sql.append(" from xg_wjcf_wjcfssshb a ");
		sql.append(" where a.cfid = ? ");
		sql.append(" and a.xtgwid = ? ");
		String[] inputValue=new String[]{model.getCfid(),model.getXtgwid()};
		String[] outputValue=new String[]{"cfid","xtgwid","shzt","shsj","shyj","shr","sftj","sswh","sssj"};
		return dao.getMap(sql.toString(), inputValue, outputValue);
	}
	
	/**
	 * 申诉审核 审核完毕  统计
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> ssshTj() throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append(" select (case ");
		sql.append(" when a.shzt = 'wcycf' then ");
		sql.append(" tgsl ");
		sql.append(" else ");
		sql.append(" 0 ");
		sql.append(" end) wcycf, ");
		sql.append(" (case ");
		sql.append(" when a.shzt = 'cxcf' then ");
		sql.append(" tgsl ");
		sql.append(" else ");
		sql.append(" 0 ");
		sql.append(" end) cxcf, ");
		sql.append(" (case ");
		sql.append(" when a.shzt = 'ggcf' then ");
		sql.append(" tgsl ");
		sql.append(" else ");
		sql.append(" 0 ");
		sql.append(" end) ggcf ");
		sql.append(" from (select count(c.shzt) tgsl, c.shzt ");
		sql.append(" from xg_wjcf_wjcfssshb c ");
		sql.append(" where exists ");
		sql.append(" (select 1 ");
		sql.append(" from xg_xtwh_spbz a, ");
		sql.append(" (select max(xh) xh, a.splc ");
		sql.append(" from xg_xtwh_spbz a ");
		sql.append(" where exists (select 1 ");
		sql.append(" from xg_wjcf_ssjcsplb b ");
		sql.append(" where a.splc = b.ssspl) ");
		sql.append(" group by a.splc) b ");
		sql.append(" where a.xh = b.xh ");
		sql.append(" and a.splc = b.splc ");
		sql.append(" and c.xtgwid = a.spgw) ");
		sql.append(" and (c.shzt = 'wcycf' or c.shzt = 'cxcf' or c.shzt = 'ggcf') ");
		sql.append(" and c.sftj='0' ");
		sql.append(" group by c.shzt) a ");

		String[] inputValue=new String[]{};
		String[] outputValue=new String[]{"wcycf","cxcf","ggcf"};
		return dao.getList(sql.toString(), inputValue, outputValue);
	}
	
	/**
	 * 申诉审核   需提交查询
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> ssshCxXtj() throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append(" select c.cfid, c.xtgwid, c.shzt, c.sssj, c.sswh, c.sftj ,c.cfggw");
		sql.append(" from xg_wjcf_wjcfssshb c ");
		sql.append(" where exists (select 1 ");
		sql.append(" from xg_xtwh_spbz a, ");
		sql.append(" (select max(xh) xh, a.splc ");
		sql.append(" from xg_xtwh_spbz a ");
		sql.append(" where exists (select 1 ");
		sql.append(" from xg_wjcf_ssjcsplb b ");
		sql.append(" where a.splc = b.ssspl) ");
		sql.append(" group by a.splc) b ");
		sql.append(" where a.xh = b.xh ");
		sql.append(" and a.splc = b.splc ");
		sql.append(" and c.xtgwid = a.spgw) ");
		sql.append(" and (c.shzt = 'wcycf' or c.shzt = 'cxcf' or c.shzt = 'ggcf') ");
		sql.append(" and c.sftj = '0' ");
		String[] inputValue=new String[]{};
		String[] outputValue=new String[]{"cfid", "xtgwid", "shzt", "sssj", "sswh", "sftj","cfggw"};
		return dao.getList(sql.toString(), inputValue, outputValue);
	}
	
	/**
	 * 申诉审核   需提交查询(直接提交)
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> ssshXtj(String[] params) throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append(" select c.cfid, c.xtgwid, c.shzt, c.sssj, c.sswh, c.sftj ,c.cfggw");
		sql.append(" from xg_wjcf_wjcfssshb c ");
		sql.append(" where exists (select 1 ");
		sql.append(" from xg_xtwh_spbz a, ");
		sql.append(" (select max(xh) xh, a.splc ");
		sql.append(" from xg_xtwh_spbz a ");
		sql.append(" where exists (select 1 ");
		sql.append(" from xg_wjcf_ssjcsplb b ");
		sql.append(" where a.splc = b.ssspl) ");
		sql.append(" group by a.splc) b ");
		sql.append(" where a.xh = b.xh ");
		sql.append(" and a.splc = b.splc ");
		sql.append(" and c.xtgwid = a.spgw) ");
		sql.append(" and (c.shzt = 'wcycf' or c.shzt = 'cxcf' or c.shzt = 'ggcf') ");
		sql.append(" and c.sftj = '0' and c.cfid in (");
		for (int i = 0; i < params.length; i++) {
			if (i != 0) {
				sql.append(",");
			}
			sql.append("?");
		}
		sql.append(" ) ");
		String[] outputValue=new String[]{"cfid", "xtgwid", "shzt", "sssj", "sswh", "sftj","cfggw"};
		return dao.getList(sql.toString(), params, outputValue);
	}
	
	/**
	 * 提交批量修改  提交
	 * @param modelList
	 * @return
	 * @throws Exception
	 */
	public boolean ssshXgTj(List<WjcfCfssglForm> modelList) throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append(" update xg_wjcf_wjcfssshb c ");
		sql.append(" set c.sftj = ? ");
		sql.append(" where exists ");
		sql.append(" (select 1 ");
		sql.append(" from xg_xtwh_spbz a, ");
		sql.append(" (select max(xh) xh, a.splc ");
		sql.append(" from xg_xtwh_spbz a ");
		sql.append(" where exists (select 1 ");
		sql.append(" from xg_wjcf_ssjcsplb b ");
		sql.append(" where a.splc = b.ssspl) ");
		sql.append(" group by a.splc) b ");
		sql.append(" where a.xh = b.xh ");
		sql.append(" and a.splc = b.splc ");
		sql.append(" and c.xtgwid = a.spgw) ");
		sql.append(" and (c.shzt = 'wcycf' or c.shzt = 'cxcf' or c.shzt = 'ggcf') ");
		sql.append(" and c.sftj='0' ");
		
		List<String[]> inputValue=new ArrayList<String[]>();
		String[] input=null;
		for (int i = 0; i < modelList.size(); i++) {
			input=new String[]{modelList.get(i).getSftj()};
			inputValue.add(input);
		}
		
		int[] rows=dao.runBatch(sql.toString(), inputValue);
		boolean flag = dao.checkBatchResult(rows);
		return flag;
	}
	
	/**
	 * 修改 处分申诉  申诉结果
	 * @param modelList
	 * @return
	 * @throws Exception
	 */
	public boolean cfssXgSsjg(List<WjcfCfssglForm> modelList) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" update xg_wjcf_wjcfssb set ssjg=? where cfid=? ");
		
		List<String[]> inputValue=new ArrayList<String[]>();
		String[] input=null;
		for (int i = 0; i < modelList.size(); i++) {
			input=new String[]{modelList.get(i).getSsjg(),modelList.get(i).getCfid()};
			inputValue.add(input);
		}
		
		int[] rows = dao.runBatch(sql.toString(), inputValue);
		boolean flag = dao.checkBatchResult(rows);
		return flag;
	}
	
	/**
	 * 违纪处分修改    提交
	 * @param modelList
	 * @return
	 * @throws Exception
	 */
	public boolean wjcfXgTj(List<WjcfCfssglForm> modelList)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" update xg_wjcf_wjcfb set sssj=?,sswh=?,ssjg=?,cfggw=? where cfid=? ");
		
		List<String[]> inputValue=new ArrayList<String[]>();
		String[] input=null;
		for (int i = 0; i < modelList.size(); i++) {
			input=new String[]{modelList.get(i).getSssj(),
					modelList.get(i).getSswh(),modelList.get(i).getSsjg(),modelList.get(i).getCfggw(),
					modelList.get(i).getCfid()};
			inputValue.add(input);
		}
		
		int[] rows = dao.runBatch(sql.toString(), inputValue);
		boolean flag = dao.checkBatchResult(rows);
		return flag;
	} 
	
	/**
	 * 查询附件信息
	 * @param form
	 * @return
	 */
	public Blob fjCx(String sql, String[] inputList, String column) {
		return dao.getOneLong(sql, inputList, column);
	}
	
	/**
	 * 判断是否最高级用户
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public boolean isZgjyh(User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.spgw, a.gwmc, c.spyh from (select b.spgw,");
		sql.append("(select c.mc from xg_xtwh_spgw c where b.spgw = c.id) gwmc ");
		sql.append(" from xg_wjcf_ssjcsplb a  left join xg_xtwh_spbz b on a.ssspl = b.splc) a,");
		sql.append(" (select * from xg_xtwh_spgwyh where spyh = ? ) c where a.spgw = c.spgw and a.spgw = ");
		sql.append(" (select spgw  from xg_wjcf_ssjcsplb a  left join xg_xtwh_spbz b on a.ssspl = b.splc where xh = (select a.xh ");
		sql.append(" from (select splc, max(xh) xh from xg_xtwh_spbz where splc in (select b.splc from xg_wjcf_ssjcsplb a");
		sql.append(" left join xg_xtwh_spbz b on a.ssspl = b.splc) group by splc) a)) ");
		List<HashMap<String, String>> map =  dao.getListNotOut(sql.toString(), new String[] {user.getUserName()});
		if(null!=map&&map.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 *  处分申诉维护 自定义导出
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cfssglCxExport(WjcfCfssglForm model,String userType,User user) throws Exception {
		//高级查询
		SearchModel searchModel=model.getSearchModel();
		String searchTj=SearchService.getSearchTj(searchModel);	
		String []input=SearchService.getTjInput(searchModel);
		
		StringBuffer sql=new StringBuffer();
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		sql.append(" select a.*, ");
		sql.append(" rownum r from (select a.*, ");
		sql.append(" b.ssjg, ");
		sql.append(" (case ");
		sql.append(" when b.ssjg is null or b.ssjg = 'wsh' then ");
		sql.append(" '未审核' ");
		sql.append(" when b.ssjg = 'shz' then ");
		sql.append(" '审核中' ");
		sql.append(" when b.ssjg = 'wcycf' then ");
		sql.append(" '维持原处分' ");
		sql.append(" when b.ssjg = 'cxcf' then ");
		sql.append(" '撤销处分' ");
		sql.append(" when b.ssjg = 'ggcf' then ");
		sql.append(" '更改处分' ");
		sql.append(" end) ssshzt ");
		sql.append(" from (select a.xh, ");
		sql.append(" b.xm, ");
		sql.append(" b.xydm, ");
		sql.append(" b.bjdm, ");
		sql.append(" b.zydm, ");
		sql.append(" a.xn, ");
		sql.append(" b.nj, ");
		sql.append(" f.xqmc, ");
		sql.append(" f.xqdm xq, ");
		sql.append(" a.cflbmc, ");
		sql.append(" a.cfyymc, ");
		sql.append(" c.cflbdm, ");
		sql.append(" d.cfyydm, ");
		sql.append(" a.sswh, ");
		sql.append(" a.sssj, ");
		sql.append(" a.cfid, ");
		sql.append(" (case when to_number(to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd')-to_date(a.cfsj,'yyyy-MM-dd')) <= c.ssslgzr ");
		sql.append(" and instr(c.sfkss,?) >0 then 'y' else 'n' end) ssyz ");
		sql.append(" from xg_wjcf_wjcfb a left join view_xsxxb b on a.xh = b.xh left join xg_wjcf_cflbdmb c ");
		sql.append(" on a.cflbmc = c.cflbmc left join xg_wjcf_cfyydmb d on a.cfyymc=d.cfyymc ");
		sql.append(" left join xqdzb f ");
		sql.append(" on a.xq=f.xqdm ");
		sql.append(" where c.sfkss <> 'no') a ");
		sql.append(" left join xg_wjcf_wjcfssb b ");
		sql.append("  on a.cfid = b.cfid) a where 1=1 ");
		
		sql.append(searchTj);
		
		//用户类别数据权限
		String[] yhlbsjqx=new String[]{};
		
		String[] inputValue=arrayAppendArray(new String[]{userType},input);
		inputValue=arrayAppendArray(inputValue,yhlbsjqx);
		sql.append(searchTjByUser);
		return (List<HashMap<String, String>>)CommonQueryDAO
		.commonPageQueryForExportMap(model.getPages(), sql.toString(), inputValue);
	}
}
