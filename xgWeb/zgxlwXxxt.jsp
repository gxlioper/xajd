<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="xgxt.action.Base"%>
<%@page import="org.apache.struts.action.ActionForward"%>
<%@page import="xgxt.DAO.DAO"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="xgxt.utils.MD5"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中国心理网学生数据集成接口</title>
</head>
  <body onload="init();">
<%--	    '该页面向中国心理网学校系统发送学生数据，用于学生数据集成--%>
<%--	    '不用于管理员集成，管理员请到中国心理网登录--%>
<%----%>
<%--	    '技术支持：江鸿宾--%>
<%--	    'QQ:33080907--%>
<%--	    'Mobile:133-1150-2251--%>
<%----%>
<%--	    '该页面必须采用UTF-8编码字符集--%>
<%--	    '学生在中国心理网学校管理系统初始密码为其学号--%>
<%--	    '以Get方式提交时，汉字必须URL编码--%>
<%--	    'MD5加密采用32位，结果为小写字母--%>
<%----%>
<%--	    '如果数据库中不存在则添加该学生资料（登录密码无需指定，自动为学号），如果已经存在则更新学生资料--%>
	 <form target="about:blank" id="form1" name="form1" method="post" action="http://psy.com.cn/student/receive.asp">
	    <%
	    	boolean b=false;
	        String erroMessage="";
	    	int i = Base.chkTimeOut(session);
	    	if (i >0 ) {//非超时
				String userName = session.getAttribute("userName")==null?"":session.getAttribute("userName").toString();
				String userType = session.getAttribute("userType")==null?"":session.getAttribute("userType").toString();
				if("stu".equals(userType)&&!Base.isNull(userName)){//是学生用户并且学号不为空
			        DAO dao = DAO.getInstance();
					String sql="select * from view_xsxxb where xh=?";
					HashMap<String,String> rs=dao.getMapNotOut(sql,new String[]{userName});
					if(rs!=null&&!rs.isEmpty()){//结果不为空
						//'*1、学校ID，由中国心理网指定
						String SchoolID="29021";
						//'*2、加密Key，仅由中国心理网和学校用户共同持有，请不要泄漏给第三方，可以进入学校管理后台自行修改
						String  Key="xinhaisoftshouti";
						//'*3、学号，英文、数字或其组合，不能出现汉字、符号等特殊字符
						String Num=Base.chgNull(rs.get("xh"),"",0);
						//'4、姓名，汉字，如果空缺则以学号姓名，以Get方式提交时需URL编码
						String Nick=Base.chgNull(rs.get("xm"),"",0);
						//'5、性别，男生为1，女生为0，如果空缺或数据不规范，作男生处理
						String Sex=Base.chgNull(rs.get("xb"),"",0);
						if("男".equals(Sex)||"1".equals(Sex)){
							Sex="1";
						}else if("女".equals(Sex)||"2".equals(Sex)){
							Sex="0";
						}else{
							Sex="1";
						}
						//'6、出生日期，以yyyy-mm-dd格式存储的合法日期，或者用8位数字表示，即4位年份、2位月份、2位日期，如果此项空缺或格式不正确将默认为当前日期
						String Birth=Base.chgNull(rs.get("csrq"),"",0);;
						//'7、班级，进入中国心理网学校管理后台设置班级，然后对应相应班级的ID，如果空缺或者没有对应则将学生加入到学校层次内
						String Dept=Base.chgNull(rs.get("bjdm"),"",0);;
						//'*8、发送日期，请用系统函数Date()取值，系统只接受发送当天的链接，其他视为非法请求
						Date currentTime = new Date();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						String SendDate=formatter.format(currentTime);
						//'把以上字串组合并用MD5加密，注意顺序不能颠倒，注意：姓名不参加组合
						String KeyStr_temp=SchoolID + Num + Sex + Birth + Dept + SendDate + Key;
						String KeyStr=MD5.md5(KeyStr_temp).toLowerCase();//用户名将BYTE转为STRING
						%>
							<input type="button" name="button" id="button" onclick="document.forms[0].submit();" value="心理系统" />
							<input type="hidden" name="SchoolID" value="<%= SchoolID %>" />
							<input type="hidden" name="Num" value="<%= Num %>" />
							<input type="hidden" name="Nick" value="<%= Nick %>" />
							<input type="hidden" name="Sex" value="<%= Sex %>" />
							<input type="hidden" name="Birth" value="<%= Birth %>" />
							<input type="hidden" name="Dept" value="<%= Dept %>" />
							<input type="hidden" name="SendDate" value="<%= SendDate %>" />
							<input type="hidden" name="KeyStr" value="<%= KeyStr %>" />
						<%
						b=true;
					}else{
						b=false;
						erroMessage="获取信息失败！";
					}
				}else{
					b=false;
					erroMessage="中国心理网学校系统只能学生访问！";
				}
			}else{
				b=false;
				erroMessage="登陆超时！";
			}
	    %>
	</form>
  </body>
</html>
	    <script>
	    function init(){
	    <%if(!b){ %>
	    	alert("<%=erroMessage%>");
	    <%}else{ %>
	    	alert("请点击【心理系统】按钮打开心理系统页面！");
		    document.forms[0].submit();
	    <%} %>
		}
		</script>
