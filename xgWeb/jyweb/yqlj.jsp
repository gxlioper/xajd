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
		<script type="text/javascript">
		function savetheinfo(){
		
		   var mc = document.getElementById("mc").value;
		   var wz = document.getElementById("wz").value;
		   if(mc==""){
		     alert("名称不能为空!");
		     return false;
		   }
		   if(mc==""){
		     alert("网址不能为空!");
		     return false;
		   }
		   document.forms[0].action = "yqlj.do?method=yqlj&doType=save&jytype=jyweb";
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
		<html:form action="/findpassword">
			<jsp:include flush="true" page="head.jsp"></jsp:include>
            <input type="hidden" name="webtype" value="yqlj" />
			<div class="mainframe">
				<div class="ny_midframe">
					<div class="leftframe">
						<jsp:include flush="true" page="contrl.jsp"></jsp:include>
						<div class="rdxw">
							<h1></h1>
						</div>
						<div class="yqlj">
							<h1></h1>
							<span></span>
						</div>
					</div>
					<div class="ny_rightframe">
						<div class="ny_con">
								<h3>
									当前位置：
									<a href="index.do">首页</a>选择 友情链接 共
									<font color="red"><b>&nbsp;<bean:write name="rsNum" />&nbsp;</b>
									</font>条相关记录
								</h3>
							<table width="98%" align="center" class="tbborder" id="tb1">
								<tr>
									<td>
										<font color="red">名称</font>
									</td>
									<td>
										<font color="red">网址</font>
									</td>
									<td>
										<font color="red">操作</font>
									</td>
								</tr>
								<logic:iterate name="yqlj" id="s">
									<tr onmouseover="rowOnClick2(this)">
										<td title="<bean:write name="s" property="mc" />">
											<bean:write name="s" property="mc" />
										</td>
										<td title="<bean:write name="s" property="wz" />">
											<bean:write name="s" property="wz" />
										</td>
										<td>
											<a
												href="yqlj.do?method=yqlj&jytype=jyweb&doType=del&pkValue=<bean:write name="s" property="mc" />">删除</a>
										</td>
									</tr>
								</logic:iterate>
								<logic:present name="yqlj">
									<script language="javascript">
										changeView('tb1',0,30,'no','yes');
										changeView('tb1',1,30,'no','yes');
									</script>
								</logic:present>
							</table>
							<br>
							<table width="98%" align="center" class="tbborder">

								<tr>
									<td colspan="2">
										<font color="red">添加链接</font>
									</td>
								</tr>
								<tr>
									<td width="20%">
										<div align="right">
											名称：
										</div>
									</td>
									<td width="80%">
										<div align="left">
											<input name="mc"  style="width:100%"/>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div align="right">
											网址：
										</div>
									</td>
									<td>
										<div align="left" >
											<input name="wz" style="width:100%" value="http://" />
										</div>
									</td>
								</tr>
							</table>
							<br>
							<button onclick="savetheinfo();">
								提交
							</button>
							<button type="reset" >
								关闭
							</button>
							<br>
							<br>

						</div>
						<h2></h2>
					</div>
				</div>
			</div>



			<jsp:include flush="true" page="foot.jsp"></jsp:include>
			<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				<script>
                   alert("提交成功！");
                </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                   alert("提交失败！请检查是否重名！");
               </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="delete">
			<logic:equal name="delete" value="ok">
				<script>
                      alert("记录删除成功！");
                    </script>
			</logic:equal>
			<logic:equal name="delete" value="no">
				<script>
                      alert("记录删除失败！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		</html:form>
	</body>
</html>
