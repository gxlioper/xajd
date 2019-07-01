package xsgzgl.pjpy.szgyyq.pjhz;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.base.Excel2Oracle;
import xgxt.comm.CommService;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_评奖汇总_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjpyPjhzService extends CommService {

	PjpyPjhzDAO dao = new PjpyPjhzDAO();
	
	/**
	 * 获取评奖混总成绩列表
	 * @param request
	 * @param form
	 * @return
	 */
	public List<String[]> getPjhzList(HttpServletRequest request,PjpyPjhzForm form){
		
		String[] jwcjkm=dao.getJwcjkm(form);
		if(jwcjkm==null||jwcjkm.length==0){
			return null;
		}
		String sql=getPjhzSql(form, jwcjkm);
		String[] inputValue=new String[]{form.getXn(),form.getXq(),form.getBjdm()};
		String[] outputValue=getOutputValue(jwcjkm);
		
		List<String[]> pjhzlist=dao.getPjhzList(sql, inputValue, outputValue);
		int count=pjhzlist.size()-form.pages.getPageSize();
		for(int i=0;i<count;i++){
			pjhzlist.add(new String[]{});
		}
		
		request.setAttribute("rs", pjhzlist);
		request.setAttribute("topTr", getTop(jwcjkm));
		return pjhzlist;
	}
	
	/**
	 * 获取表头
	 * @param jwcjkm
	 * @return
	 */
	public String[] getTop(String[] jwcjkm){
		String[] top=new String[8+jwcjkm.length];
		
		top[0]="学号";
		top[1]="姓名";
		
		for(int i=0;i<jwcjkm.length;i++){
			top[i+2]=jwcjkm[i];
		}
		
		top[jwcjkm.length+8-6]="学分平均分";
		top[jwcjkm.length+8-5]="学分排名";
		top[jwcjkm.length+8-4]="素质平均分";
		top[jwcjkm.length+8-3]="素质排名";
		top[jwcjkm.length+8-2]="综合平均分";
		top[jwcjkm.length+8-1]="综合排名";
		
		return top;
	}
	/**
	 * 获取输出的列
	 * @param jwcjkm
	 * @return
	 */
	public String[] getOutputValue(String[] jwcjkm){
		String[] top=new String[8+jwcjkm.length];
		
		top[0]="xh";
		top[1]="xm";
		
		for(int i=0;i<jwcjkm.length;i++){
			top[i+2]="jwkmcj"+i;
		}
		
		top[jwcjkm.length+8-6]="jwkmcjpjf";
		top[jwcjkm.length+8-5]="jwkmcjpjfpm";
		top[jwcjkm.length+8-4]="zhszf";
		top[jwcjkm.length+8-3]="zhszfpm";
		top[jwcjkm.length+8-2]="zhpjf";
		top[jwcjkm.length+8-1]="zhpjfpm";
		
		return top;
	}
	
	/**
	 * 获取评奖汇总sql
	 * @param form
	 * @param jwcjkm
	 * @return
	 */
	public String getPjhzSql(PjpyPjhzForm form,String[] jwcjkm){
		StringBuffer sql=new StringBuffer();//最终评奖汇总sql
		//成绩表数据处理
		StringBuffer sql_cjb=new StringBuffer(); //成绩表sql
		StringBuffer sql_cjb_col=new StringBuffer();//成绩表行列转换部分
		StringBuffer sql_cjb_group=new StringBuffer();//成绩表按学号分配整理成一条记录
		StringBuffer sql_cjb_jwcjzf=new StringBuffer("to_char((");//成绩表单个学生平均分
		sql_cjb_col.append("select a.xn,a.xq,a.xh,b.bjdm,b.xm, ");
		
		for(int i=0;i<jwcjkm.length;i++){
			sql_cjb_col.append("(case when a.kcmc='"+jwcjkm[i]+"' then a.cj end) jwkmcj"+i+" ");
			sql_cjb_group.append("sum(jwkmcj"+i+") jwkmcj"+i);
			sql_cjb_jwcjzf.append("nvl(jwkmcj"+i+",0)");
			if(i<jwcjkm.length-1){
				sql_cjb_col.append(",");
				sql_cjb_group.append(",");
				sql_cjb_jwcjzf.append("+");
			}
		}
		
		sql_cjb_col.append(" from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn=? and a.xq=? and b.bjdm=? and a.kcxz in("+form.getKcxz()+") ");
		sql_cjb_jwcjzf.append(")/"+jwcjkm.length+",'990.99') jwkmcjpjf ");
		
		sql_cjb.append("select a.*, ");
		sql_cjb.append(sql_cjb_jwcjzf);
		sql_cjb.append(" from ( ");
			sql_cjb.append(" select a.xn,a.xq,a.xh,a.bjdm,a.xm, ");
			sql_cjb.append(sql_cjb_group);
			sql_cjb.append(" from ( ");
				sql_cjb.append(sql_cjb_col);
			sql_cjb.append(" ) a group by xn,xq,bjdm,xh,xm");
		sql_cjb.append(" ) a ");
		
		//评奖汇总sql拼接
		sql.append("select a.*,(rank() over(partition by a.xn,a.xq order by to_number(zhpjf) desc nulls last)) zhpjfpm " + 
					"from ( " +
					"select a.*,(rank() over(partition by a.xn,a.xq order by to_number(jwkmcjpjf) desc nulls last)) jwkmcjpjfpm, " +
					"b.zhszf,b.zhszfpm,to_char((nvl(a.jwkmcjpjf,0)*0.8+nvl(b.zhszf,0)*0.2),'990.99') zhpjf " +
					"from  " +
					"(");
		sql.append(sql_cjb);
		sql.append(" ) a left join szgy_zhszcphzlsb b on a.xh=b.xh ");
		sql.append(") a");
		return sql.toString();
	}
	
	/**
	 * 导出评奖汇总
	 * @param response
	 * @param form
	 * @return
	 * @throws IOException 
	 */
	public String exportPjhzList(HttpServletResponse response,PjpyPjhzForm form)
	throws Exception{
		
		String[] jwcjkm=dao.getJwcjkm(form);
		if(jwcjkm==null||jwcjkm.length==0){
			return "没有数据可导出";
		}
		String sql=getPjhzSql(form, jwcjkm);
		String[] inputValue=new String[]{form.getXn(),form.getXq(),form.getBjdm()};
		String[] outputValue=getOutputValue(jwcjkm);
		
//		List<String[]> pjhzlist=dao.getPjhzList(sql, inputValue, outputValue);
		
		String bjmc=dao.getBJmc(form.getBjdm());
		String title=form.getXn()+"-"+form.getXq()+bjmc+"综合测评汇总表";
		String[] topTr=getTop(jwcjkm);
		
		//开始导出数据
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		new Excel2Oracle().exportData(sql, inputValue, outputValue, topTr, response.getOutputStream(), "export.xls", title);
		return null;
	}

}