<%@ page language="java" contentType="text/html; charset=GBK" %>
<%@ page  import="thauth.*"%>
<%@ page  import="java.util.Hashtable"%>
<%@ page  import="xgxt.base.UserLoginCheck"%>
<jsp:directive.page import="java.util.HashMap"/>

<%
				UserLoginCheck userCheck = new UserLoginCheck();
 				String APPID="ZFXG";
                String strUserIp=request.getRemoteAddr() ;// 获得IP
                String ticket =request.getParameter("bill");//接受到门户传来的票据
                Hashtable ht = Roam.thauthCheckTicket(ticket,APPID,strUserIp);//调用验证函数，验证票据是否有效
                if(ht==null){
				%>
				<jsp:forward page="login.jsp"/> <%--   要跳转到的错误处理页面,修改为相应验证系统的登录页面    --%>
				
				<%}
                 int code = ((Integer)ht.get(ThauthConst.THAUTH_CODE)).intValue();//获取返回结果中的code的值
                 if(code == 0){//判断code的值，如果是0则说明漫游成功
                    
                        //J0000（在职教工）
						//J0054（离退教工）
						//X0011（博士生）
						//X0021（硕士生）
						//X0031（本科生）
						//H0000（合同人员）
						//TMNIC（网络中心用户）
						//TMOAS（办公自动化系统用户）
						//TMTEA（临时教师）
                        String yhlb = (String)ht.get(ThauthConst.THAUTH_YHLB);
                        if(yhlb.equalsIgnoreCase("J0000") || yhlb.equalsIgnoreCase("J0054") || yhlb.equalsIgnoreCase("H0000") || yhlb.equalsIgnoreCase("TMNIC") || yhlb.equalsIgnoreCase("TMOAS") || yhlb.equalsIgnoreCase("TMTEA") ){
                            ht.put(ThauthConst.THAUTH_YHLB,new String("teacher"));
                        } else if(yhlb.equalsIgnoreCase("X0011") || yhlb.equalsIgnoreCase("X0021") || yhlb.equalsIgnoreCase("X0031")){
                            ht.put(ThauthConst.THAUTH_YHLB,"student");
                        }
                        HashMap<String,String> resultMap = userCheck.setAllLoginInfo(request,ht);
                        String errorMsg = resultMap.get("errorMsg");
                        if(errorMsg != null){%>
						<script type="text/javascript">                           
						    alert(<%= errorMsg %>);
						    window.location.href="login.jsp";
						</script>
                        <% } else {
								response.sendRedirect("main1.jsp");                        
                        }
 
                 }else{//如果漫游不成功，跳转到一个错误处理页面
				%>
				
				    <jsp:forward page="login.jsp"/> <%-- 要跳转到的错误处理页面,可以修改为相应验证系统的登录页面--%>
				<%
                 }
         
         
         //    把login.jsp改成跟门户的链接页面：window.location.href = 'index.jsp';
%>



