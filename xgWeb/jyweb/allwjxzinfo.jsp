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
				
	
		function newpage_ggl(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewgglinfo.do?method=wjxzinfo&jytype=jyweb&rowid="+pkValue;
		}
		function newpage_syjs(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewsyjsinfo.do?method=allwjxzinfo&jytype=jyweb&rowid="+pkValue;
		}
		
		function refreshtheweb()
		{
			document.forms[0].action = "viewallwjxzinfo.do?method=allwjxzinfo&jytype=jyweb";
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
		<jsp:include flush="true" page="head.jsp"></jsp:include>
		<html:form action="/viewallwjxzinfo" method="post">
			<div class="mainframe">
				<div class="ny_midframe">
					<div class="leftframe">
						<jsp:include flush="true" page="left_search.jsp"></jsp:include>

						
					</div>
					<div class="ny_rightframe">
						<div class="liebiao">
								<h3>
									当前位置：首页选择 下载中心 共
									<FONT color="red">&nbsp;<b><bean:write name="rsNum" />
									</b>&nbsp;</FONT>条相关记录
								</h3>
							<table width="98%" align="center" class="tbborder" id="tb1">
								<tr height="25" class="btys">
									<td align="center">
										文件号
									</td>
									<td align="center">
										文件名
									</td>
									<td align="center">
										上传部门
									</td>
									<td align="center">
										上传人
									</td>
									<td align="center">
										上传时间
									</td>
									<td align="center">
									操作
									</td>
								</tr>
								<logic:iterate id="list" name="rs">
									<tr height="25" onmouseover="rowOnClick2(this)">
										<td class="tubiao" title="<bean:write name="list" property="wjh" />">
											<bean:write name="list" property="wjh" />
										</td>
										<td align="center" title="<bean:write name="list" property="wjm" />">
											<bean:write name="list" property="wjm" />
										</td>
										<td align="center">
											&nbsp;
											<bean:write name="list" property="wjffbm" />
											&nbsp;
										</td>
										<td align="center">
											<bean:write name="list" property="ffr" />
										</td>
										<td align="center">
											<bean:write name="list" property="wjffsj" />
										</td>
										<td align="center">
											<a
												href="viewwjxzinfo.do?method=wjxzsinfo&jytype=jyweb&wjh=<bean:write name="list" property="wjh"/>"
												target="_blank">查看详细</a>&nbsp;
											<logic:equal name="usertype" value="admin" scope="session">|&nbsp;
								<a
													href="viewallwjxzinfo.do?method=allwjxzsinfo&jytype=jyweb&doType=del&pkValue=<bean:write name="list" property="wjh"/>">删除</a>
											</logic:equal>

										</td>
									</tr>
								</logic:iterate>
								<logic:present name="rs">
									<script language="javascript">
										changeView('tb1',0,12,'no','yes');
										changeView('tb1',1,12,'no','yes');
									</script>
								</logic:present>
							</table>
							<TABLE width="98%" id="rsTable" class="tbborder">
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
									</TD>
								</TR>
							</TABLE>
						</div>
					</div>
				</div>
			</div>
			<jsp:include flush="true" page="foot.jsp"></jsp:include>
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
			<button onclick="refreshtheweb()" id="search_go"
				style="display: none" ></button>
		</html:form>
	</body>
</html>
