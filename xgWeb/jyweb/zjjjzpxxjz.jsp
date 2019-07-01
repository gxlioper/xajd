<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><%=session.getAttribute("xxmc")%>就业网</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="杭州正方电子工程有限公司 hzzfsoft@126.com" />
		<meta name="Copyright" content="Copyright" />
		<meta name="description" content="description" />
		<meta name="keywords" content="description" />

		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">


		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<style type="text/css">
			A:link    { color: #000000 }
			A:active  { color: orange }
			.tbstyle {
	border-collapse: collapse;
}

.tbstyle td {
	border: 1px #97B7DB solid;
	padding: 3px;
}
.tbstyle th {
	border: 1px #97B7DB solid;
	padding: 3px;
}
.tbstyle thead {
	background: #D0E0EE;
	color: #003366
}


		</style>
		<script type="text/javascript">
		function viewMorezpinfo(doType){	  	   
		  var pkValue =  curr_row.getElementsByTagName("input")[0].value;
		  var url ="viewzpinfo.do?method=jyzpinfo&doType=view&pkValue="+pkValue+"&jytype=jyweb"; 
		 if (doType == "view"){
		   showTopWin(url, 700, 650);
		  }
		}
		
		function newpage(obj){
		    var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewzpinfo.do?method=jyzpinfo&doType=view&pkValue="+pkValue+"&jytype=jyweb";
		}
		function newpage_news(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewnewsinfo.do?method=newsinfo&newsId="+pkValue+"&jytype=jyweb";
		}
		
		function newpage_zczp(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewzczpinfo.do?method=qitainfo&rowid="+pkValue+"&jytype=jyweb";
		}
		function newpage_ggl(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewgglinfo.do?method=qitainfo&rowid="+pkValue+"&jytype=jyweb";
		}
		function newpage_syjs(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewsyjsinfo.do?method=qitainfo&rowid="+pkValue+"&jytype=jyweb";
		}
		function newpage_qzxx(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "qzxxinfo.do?method=qzxxinfo&doType=view&pkValue="+pkValue+"&jytype=jyweb";
		}
		
		function newpage_tpxx(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewtpxxinfo.do?method=tpxxinfo&doType=view&pkValue="+pkValue+"&jytype=jyweb";
		}
		function userlogin(){
           var usertype =document.getElementById("userty").value;
           var yhm = document.getElementById("yhm").value;
           var mm = document.getElementById("mm").value;
           var yzm = document.getElementById("yzm").value;
           
           if(yhm==""){
           alert("请输入用户名！");
           return false;
           }
           
           if(mm==""){
           alert("请输入密码！");
           return false;
           }
           
           if(yzm==""){
           alert("请输入验证码！");
           return false;
           }
           
           if(yzm.length<4){
            alert("验证码的位数不正确！");
           return false;
           }
           
		   document.forms[0].action = "index.do?method=jyindex&doType=login&usertype="+usertype+"&jytype=jyweb";
		   document.forms[0].submit();
		}	
			
		function dowhat(){
		   document.forms[0].action = "adminoperation.do?method=adminoperation&jytype=jyweb";
		   document.forms[0].submit();
		}
		
		function wjdc(){
		   document.forms[0].action = "index.do?method=jyindex&doType2=wjdc&jytype=jyweb";
		   document.forms[0].submit();
		}
		
		function wjdcresult(){
		    url = "wjdcresult.do?method=wjdcresult&jytype=jyweb";
		    showTopWin(url, 450, 270);
		    
		}
		function findarticle(){
		    document.forms[0].action = "findarticle.do?method=findarticle&doType=find&jytype=jyweb";
		    document.forms[0].submit();
		}
		
		function admingl(){
		 	document.forms[0].action = "adminoperation.do?method=adminoperation&act=zxdt&jytype=jyweb";
		 	document.forms[0].submit();
        }
        function dwgl(){
            document.forms[0].action = "adminoperation.do?method=adminoperation&act=zpxx&jytype=jyweb";
		 	document.forms[0].submit();
        }
         function stugl(){
            document.forms[0].action = "adminoperation.do?method=adminoperation&act=grjldj&jytype=jyweb";
		 	document.forms[0].submit();
        }
        
	</script>



	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<form action="zpxxjz.do" method="post">
			<table style="1px #97B7DB solid" class="tbstyle">
				<thead height="20px" align="center">
					<td>公 司 名 称</td><td>招 聘 职 位</td>
				</thead>
				<logic:iterate id="s" name="rs">
				<tr height="20px" style="text-decoration:">
					<td title="<bean:write name="s" property="allgsmc" />">
							<font style="color:bule"></font><bean:write name="s" property="gsmc" /> 
					</td>
					<td>
						<a title="<bean:write name="s" property="allzpzw" />    浏览人次：<bean:write name="s" property="readtimes" />"
							href="viewzpinfo.do?method=jyzpinfo&jytype=jyweb&doType=view&pkValue=<bean:write name="s" property="rid"/>" target="_blank" style="vlink_color:red">
							<bean:write name="s" property="zpzw" /> </a>
						
					</td>
				</tr>
				</logic:iterate>
			</table>
		</form>
	</body>

</html>
