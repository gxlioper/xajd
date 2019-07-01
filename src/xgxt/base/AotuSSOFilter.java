package xgxt.base;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.action.CommanAction;
import xgxt.utils.Fdypd;

import com.zfsoft.utils.StringUtil;
import common.GlobalsVariable;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 免单点过滤器
 * @类功能描述: 免单点过滤器
 * @作者： wanghj [工号：1004]
 * @时间： 2013-12-1 下午05:34:21 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class AotuSSOFilter implements Filter {

	
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		boolean  aotoSSOFlag = true;
		HttpSession session = ((HttpServletRequest)request).getSession();
		String userName = (String) session.getAttribute("userName");
		if(StringUtils.isNotEmpty(userName)){
			CommanAction test = new CommanAction();
		    String [] returnStrings = test.userLoginForPage(userName);
		    if(!StringUtil.isNull(returnStrings[4])){
		    	aotoSSOFlag = false;
		    }
		}
		if(aotoSSOFlag){
			String curUser = "";
			CommanAction commA = new CommanAction();
			try {
				if (commA.checkXxdmExists(GlobalsVariable.XTWH_TYSFRZ_DR)) {//东软认证
					curUser = commA.drSsorz((HttpServletRequest)request, (HttpServletResponse)response);
				} else if (commA.checkXxdmExists(GlobalsVariable.XTWH_TYSFRZ_JZ)) {//金智认证
					curUser = commA.jzSsorz((HttpServletRequest)request, curUser);
				} 
			} catch (Exception e) {
				e.printStackTrace();
			}
			//curUser="zf01";//    测试数据------------------------------------------
			if(StringUtils.isNotEmpty(curUser)){
				//获取用户名后调用原来的加载用户信息的类
				System.out.print("当前登录：您好，"+curUser);
			    CommanAction test = new CommanAction();
			    String [] returnStrings = test.userLoginForPage(curUser);
			    if(!StringUtil.isNull(returnStrings[4])){
				    request.setAttribute("errMsg",returnStrings[0]);
				    session.setAttribute("pjzq", returnStrings[1]); //学校的评奖周期,xn
					session.setAttribute("userOnLine", returnStrings[2]); //用户类型（学生、老师）
					session.setAttribute("userName", returnStrings[3]); //登陆名
					session.setAttribute("userDep", returnStrings[4]); //用户部门
					session.setAttribute("userNameReal", returnStrings[5]); //用户姓名
					session.setAttribute("xxmc", returnStrings[6]);
					session.setAttribute("xxdm", returnStrings[7]);
					session.setAttribute("LoginXn", returnStrings[8]);
					session.setAttribute("LoginXq", returnStrings[9]);
					session.setAttribute("LoginZc", returnStrings[10]);
					session.setAttribute("openType",returnStrings[11]);
					session.setAttribute("bmmc", returnStrings[12]);
					session.setAttribute("userType", returnStrings[14]);
					session.setAttribute("istysfrz", "yes");
					if(returnStrings[13].equalsIgnoreCase("yes")){
						session.setAttribute("isFdy", true);
					}else{
						 session.setAttribute("isFdy", false);
				    }
				    if(returnStrings[15].equalsIgnoreCase("yes")){
						session.setAttribute("isBzr", true);
					}else{
						 session.setAttribute("isBzr", false);
				    }    
				    if(returnStrings[16].equalsIgnoreCase("yes")){
						session.setAttribute("fdyQx", true);
					}else{
						 session.setAttribute("fdyQx", false);
				    }    
				    if(returnStrings[17].equalsIgnoreCase("yes")){
						session.setAttribute("bzrQx", true);
					}else{
						 session.setAttribute("bzrQx", false);
				    }
				    //		 版本
				    String edition = Base.getEdition();
						// 是否需要高级查询
				    String superSearch = Base.getSuperSearch();
				
					session.setAttribute("edition", edition);
					session.setAttribute("superSearch", superSearch); 
					session.setAttribute("isFdy", Fdypd.isFdy(curUser));
				    session.setAttribute("fdyQx", Fdypd.fdybjck(curUser));
				    session.setAttribute("isFdyUser", Fdypd.isFdy(curUser));//是否是辅导员
				    
				    //是否公寓管理员
				    String gyglySql = "select count(1) num from xg_gygl_new_gyfdyb where yhm = ?";
				    DAO dao = DAO.getInstance();
					String num = dao.getOneRs(gyglySql, new String[] { curUser }, "num");
					String gyglyQx = !Base.isNull(num) && !"0".equalsIgnoreCase(num) ? "yes"
							: "no";
					session.setAttribute("gyglyQx", gyglyQx);
					
					//是否书院用户
					String sySql = "select sydm from fdyxxb where zgh=?";
					String sydm = dao.getOneRs(sySql, new String[] { userName },
							"sydm");
					String syQx = !Base.isNull(sydm) ? "yes" : "no";
					
					session.setAttribute("syQx", syQx);// 是否是书院用户
					
					// 是否兼任学院用户
					String sfjryx= Fdypd.checkSfjrXy(curUser).get("sfjryx");
					// 非空取数据
					if(!Base.isNull(sfjryx)){
						session.setAttribute("sfjryx", sfjryx);
					}else{
						session.setAttribute("fdyQx", false);
						session.setAttribute("bzrQx", false);
						session.setAttribute("isFdy", false);
						session.setAttribute("isBzr", false);
					}
			    }
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
