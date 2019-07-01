package xgxt.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;

public class StatisticAllAction {

	public ActionForward allStatistic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//在全部或者指定的范围内查询你要查询的内容在学生中的比例
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String mkdm = request.getParameter("act");
		String userType = session.getAttribute("userType").toString();
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		String moduleName = "";
		String grade = "";
		String manCount = "0";
		String womanCount = "0";
		String sex = null;
		String manScale = "0%";
		String womanScale = "0%";
		String stuScale = "0%";

		//从条件表里查询可搜索模块的查询条件
		sql = "select xmmc,ssxmdyb from qjbltjb where ssmk=?";
		List mkList = dao.getList(sql, new String[] {mkdm}, new String[] { "xmmc", "ssxmdyb" });
		String realTable = DealString.toGBK(request.getParameter("belongToItem"));
		String nj = DealString.toGBK(request.getParameter("nj"));
		String xy = DealString.toGBK(request.getParameter("xy"));
		String zy = DealString.toGBK(request.getParameter("zy"));
		String bj = DealString.toGBK(request.getParameter("bj"));
		String rdsjdks = request.getParameter("rdsjdks");
		String rdsjdjs = request.getParameter("rdsjdjs");
		String userDep = session.getAttribute("userDep").toString();
		if(userType.equalsIgnoreCase("xy") && (xy == null || xy.equalsIgnoreCase(""))){
			xy = userDep;
			request.setAttribute("xydm", xy);
		}

		if(realTable!=""){
			ArrayList<String[]> vector = new ArrayList<String[]>();
			String[] input = null;
			//统计符合要求男，女学生数，总数
			StringBuffer sqlBuffer = new StringBuffer("select count(*) sum,max(b.xb) xb from ");
			sqlBuffer.append(realTable);
			if(StringUtils.isNotNull(rdsjdks) || StringUtils.isNotNull(rdsjdjs)){
				sqlBuffer.append(" a left join view_xsjbxx b on a.xh = b.xh where (b.nj = ? or ? = ' ') and (b.xydm = ? or ? = ' ') ");
				sqlBuffer.append("and (b.zydm = ? or ? = ' ') and (b.bjdm = ? or ? = ' ')");
				ArrayList<String> value = new ArrayList<String>();
				value.add(nj);
				value.add(nj+" ");
				value.add(xy);
				value.add(xy+" ");
				value.add(zy);
				value.add(zy+" ");
				value.add(bj);
				value.add(bj+" ");
				if(StringUtils.isNotNull(rdsjdks)){//入党时间段开始
					sqlBuffer.append(" and(to_number(a.rdsj)>=?)");
					value.add(rdsjdks);
				}				
				if(StringUtils.isNotNull(rdsjdjs)){//入党时间段结束
					sqlBuffer.append(" and(to_number(a.rdsj)<=?)");
					value.add(rdsjdjs);
				}
				sqlBuffer.append("group by b.xb order by b.xb");
				input = value != null ? value.toArray(new String[0]) : new String[]{};
			}else{
				sqlBuffer.append(" a left join view_xsjbxx b on a.xh = b.xh where (b.nj = ? or ? = ' ') and (b.xydm = ? or ? = ' ') ");
				sqlBuffer.append("and (b.zydm = ? or ? = ' ') and (b.bjdm = ? or ? = ' ') group by b.xb order by b.xb");
				input = new String[]{nj,nj+" ",xy,xy+" ",zy,zy+" ",bj,bj +" "};
			}
			
			sql =sqlBuffer.toString();
			vector = dao.rsToVator(sql, input,new String[]{"sum","xb"});
			if(vector!=null){
				for(int i = 0 ;i < vector.size();i++){
					sex =  ((String [])vector.get(i))[1]; 
					if (sex.equalsIgnoreCase("男")){
						manCount = ((String [])vector.get(i))[0]; 
					}else if (sex.equalsIgnoreCase("女")){
						womanCount = ((String [])vector.get(i))[0]; 
					}
				}
			}
			int manSum =  Integer.parseInt(manCount);
			int womanSum = Integer.parseInt(womanCount);
			int stuSum = manSum+womanSum;
			String stuCount = ((Integer)stuSum).toString();


			//得到总数后统计比例
			sql = "select count(*) sum from view_xsjbxx where (nj = ? or ? = ' ') and (xydm = ? or ? = ' ')"+
			"and (zydm = ? or ? = ' ') and (bjdm = ? or ? = ' ')";
			String[] allStuData = dao.getOneRs(sql, new String[] {nj,nj+" ",xy,xy+" ",zy,zy+" ",bj,bj +" "},new String[]{"sum"});
			String allCount = allStuData[0];
			int allSum = Integer.parseInt(allCount);
			if (allSum != 0){
				manScale = ((float)((manSum*10000)/allSum))/100 + "%";
				womanScale = ((float)((womanSum*10000)/allSum))/100 + "%";
				stuScale = ((float)((stuSum*10000)/allSum))/100 + "%";
			}

			//得到页面上显示的所查模块和查询年级的内容
			if(!bj.equalsIgnoreCase("")){
				sql = "select distinct bjmc from view_njxyzybj where bjdm = ? order by bjmc";
				String[] tmp = dao.getOneRs(sql, new String[] {bj},new String[]{"bjmc"});
				moduleName = tmp[0]; 
			}else if(!zy.equalsIgnoreCase("")){
				sql = "select distinct zymc from view_njxyzybj where zydm = ? order by zymc";
				String[] tmp = dao.getOneRs(sql, new String[] {zy},new String[]{"zymc"});
				moduleName = tmp[0]; 
			}else if(!xy.equalsIgnoreCase("")){
				sql = "select distinct xymc from view_njxyzybj where xydm = ? order by xymc";
				String[] tmp = dao.getOneRs(sql, new String[] {xy},new String[]{"xymc"});
				moduleName = tmp[0]; 
			}else{
				moduleName = "全校范围"; 
			}
			if(!nj.equalsIgnoreCase("")){
				grade = nj; 
			}else{
				grade = "没有指定";
			}

			map.put("moduleName", moduleName);
			map.put("grade", grade);
			map.put("manCount", manCount);
			map.put("womanCount", womanCount); 
			map.put("stuCount", stuCount); 
			map.put("manScale", manScale); 
			map.put("womanScale", womanScale); 
			map.put("stuScale", stuScale); 
		}
		request.setAttribute("mkList",mkList);
		request.setAttribute("mkdm", mkdm);
		request.setAttribute("userType", userType);
		request.setAttribute("rs", map);
		return new ActionForward("/sxjy/statistic/AllStatistic.jsp");
	}
}
