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
		<base target="_self">
		<script language="javascript" src="js/function.js"></script>


	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<html:form action="/wjdcgl" method="post">
			<jsp:include flush="true" page="head.jsp"></jsp:include>
            <input type="hidden" name="webtype" value="wjdc" />
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
									当前位置：<a href="index.do">首页</a>选择 问卷调查管理
								</h3>
							<table width="98%" align="center" class="tbborder">
								<thead>
									<tr>
										<td colspan="5" height="25">
											<FONT color="black"><bean:write name="bt"
													property="bt" /> <input type="hidden" name="bt"
													value="<bean:write name="bt"
													property="bt" />" />
											</FONT>
										</td>
									</tr>
								</thead>
								<logic:empty name="wjdclist">
								<div align="center"><font color="black"><b>暂时没有调查项目！</b></font></div>
								</logic:empty>
								<logic:notEmpty name="wjdclist">
									<tr align="center" style="cursor:hand" height="30"
										bgcolor="E6F4FF">
										<td>
											序号
										</td>
										<td>
											选项
										</td>
										<td>
											人数
										</td>
										<td>
											比例
										</td>
										<td>
											<FONT color="red"> 操作 </FONT>
										</td>
									</tr>
									<logic:iterate name="wjdclist" id="v">
										<tr height="25" align="center">
											<td width="10%">
												<bean:write name="v" property="rownum" />
											</td>
											<td width="30%">
												<div align="left">
													&nbsp;&nbsp;
													<bean:write name="v" property="choice" />
												</div>
											</td>
											<td width="10%" align="center">
												<bean:write name="v" property="times" />
											</td>
											<td width="40%">
												<div align="left">
													&nbsp;&nbsp;
													<img src="jyweb/images/bl02.gif"
														width="<bean:write name="v" property="bili"/>"
														height="10px" />
													<font color="red"><bean:write name="v"
															property="bili" /> %</font>
												</div>
											</td>
											<td width="10%" align="center">
												<div align="center">
													<a
														href="wjdcgl.do?method=wjdcgl&jytype=jyweb&doType=del&pkValue=<bean:write name="v" property="choice"/>">删除
													</a>
												</div>
											</td>
										</tr>
									</logic:iterate>
								</logic:notEmpty>
							</table>
							<br>
							<div align="right">
								<button onclick="toaddchoice();">
									增加选项
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button onclick="newwjdc();">
									重新发起问卷
								</button>
								&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						<h2></h2>
					</div>
				</div>
			</div>

			<jsp:include flush="true" page="foot.jsp"></jsp:include>
			<button onclick="refreshtheweb()" id="query_go" style="display: none" ></button>
		</html:form>
		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				<script>
    alert("提交成功！");
    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
    alert("提交失败！");
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
	</body>
	<script type="text/javascript">
		
		function refreshtheweb(){
		    document.forms[0].action = "wjdcgl.do?method=wjdcgl&jytype=jyweb";
		    document.forms[0].submit();
		}
		
		
		function toaddchoice(){
		var pkValue = document.getElementById("bt").value;
		    url = "addwjdcchoice.do?method=addwjdcchoice&jytype=jyweb&act=first&pkValue="+pkValue;
		    showTopWin(url, 400, 200);
		    
		}
		
		function newwjdc(){	
		   if (confirm("重新发起后，原记录将全部清空！\n\n 确定要重新发布问卷调查吗？")) {
		       url = "newwjdc.do?method=newwjdc&jytype=jyweb&act=first";
		       showTopWin(url, 400, 250); 
				return true;
			} else {
				return false;
			}
		
		}
		</script>
</html>
