package xsgzgl.xljk.tsxswh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;

public class TsxswhDAO {
	DAO dao = DAO.getInstance();

	public List<String[]> getTableList(TsxswhForm myForm,
			HttpServletRequest request) throws Exception {
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		//权限控制
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser(request, "","xydm", null); 	//学院用户
		HttpSession session = request.getSession();
		if("true".equalsIgnoreCase(session.getAttribute("fdyQx").toString())){			//辅导员用户
			searchTjQx = " and zgh = '"+session.getAttribute("userName").toString()+"' ";
		}
		
		StringBuffer sql = new StringBuffer(
				"select * from (select a.xn,a.xq xqdm,(select xqmc from xqdzb where xqdm = a.xq) xqmc," +
				"(select zgh from fdybjb where bjdm = b.bjdm) zgh,a.xh,a.zywt,a.brth,a.lxjs,a.sbsj,a.bz,b.xm,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.nj," +
				"a.xn||'!!!SplitOne!!!'||a.xq||'!!!SplitOne!!!'||a.xh pkValue,rownum r from XG_XLJK_ZJZYY_TSXSXXB a " +
				"left join view_xsxxb b on a.xh = b.xh order by a.xn,a.xq,a.xh) where 1=1 ");
		//联合主键
		String[] output = new String[] { "xh","xm","xn","xqmc","nj","bjmc","sbsj","pkValue"};
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV,
				output, myForm);
	}
	/**
	 * 保存特殊学生信息
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public boolean saveTsxsXX(TsxswhForm myForm,HttpServletRequest request) throws Exception {
		boolean flag = false;
		String xn  = myForm.getXn();
		String xq = myForm.getXq();
		String xh = myForm.getXh();
		String zywt = myForm.getZywt();
		String brth = myForm.getBrth();
		String lxjs = myForm.getLxjs();
		String sbsj = myForm.getSbsj();
		String bz = myForm.getBz();
		String sql = null;
		String num = "0";
		sql = "select count(*) num from XG_XLJK_ZJZYY_TSXSXXB where xn = ? and xq = ? and xh = ?";
		num = dao.getOneRs(sql, new String[]{xn,xq,xh}, "num");
		if(num.equals("0")){
			sql = "insert into XG_XLJK_ZJZYY_TSXSXXB (xn,xq,xh,zywt,brth,lxjs,sbsj,bz) values(?,?,?,?,?,?,?,?)";
			flag = dao.runUpdate(sql, new String[]{xn,xq,xh,zywt,brth,lxjs,sbsj,bz});
		}else{
			sql = "update XG_XLJK_ZJZYY_TSXSXXB set zywt = ? ,brth = ? ,lxjs = ? ,sbsj = ? ,bz = ? where xn = ? and xq = ? and xh = ? ";
			flag = dao.runUpdate(sql, new String[]{zywt,brth,lxjs,sbsj,bz,xn,xq,xh});
		}
		return flag;
	}
	/**
	 * 删除特殊学生信息
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean deleteTsxsXX(List<String[]> params) throws Exception {
		boolean flag = false;
		String sql = "delete from XG_XLJK_ZJZYY_TSXSXXB where xn = ? and xq = ? and xh = ? ";		
		int[] rs = dao.runBatch(sql, params);
		flag = dao.checkBatchResult(rs);
		return flag;
	}
	/**
	 * 获得一个学生心理信息hashmap
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> getOneTsxsXX(String[] pkValue) {
		HashMap<String,String> map = new HashMap<String,String>();
		String sql = "select count(*) num from XG_XLJK_ZJZYY_TSXSXXB where xn = ? and xq =? and xh = ?";
		String num = dao.getOneRs(sql, pkValue, "num");
		if(!"0".equalsIgnoreCase(num)){
			sql = "select a.*,b.xm,b.xb,b.zymc,b.bjmc,a.xn||'!!!SplitOne!!!'||a.xq||'!!!SplitOne!!!'||a.xh pkValue from XG_XLJK_ZJZYY_TSXSXXB a  left join view_xsxxb b on a.xh = b.xh where a.xn = ? and a.xq = ? and a.xh = ? ";
			map = dao.getMap(sql, pkValue, new String[]{"xn", "xq", "xh","xm","xb","zymc","bjmc", "zywt", "brth",	"lxjs","sbsj","bz","pkValue"});
		}
		return map;
	}
	/**
	 * 获得一个学生的基本信息hashmap
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getXsxx(String xh) {
		String sql = "select * from view_xsxxb where xh = ? ";
		return dao.getMap(sql, new String[]{xh}, new String[]{"xm","xb","zymc","bjmc"});
	}

}
