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
        <base target="_self">
		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">

		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
				
	
		function newpage_dwhf(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    url = "viewdwhfinfo.do?method=dwhfinfo&doType=view&jytype=jyweb&pkValue="+pkValue;
		    showTopWin(url, 490, 300);
		    
		}
		
		function delone(){
		  var pkValue =  curr_row.getElementsByTagName("input")[0].value;
		  document.forms[0].action = "viewalldwhfinfo.do?method=alldwhfinfo&jytype=jyweb&doType=del&rowid="+pkValue;
          document.forms[0].submit();
		}
		
		function refreshtheweb()
		{
			document.forms[0].action = "viewalldwhfinfo.do?method=alldwhfinfo&jytype=jyweb";
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
		<html:form action="/viewalldwhfinfo" method="post">
			<div class="mainframe">
				<div class="ny_midframe">
				    <div class="leftframe">
						<jsp:include flush="true" page="left_search.jsp"></jsp:include>
					<div class="rdxw">
						<h1></h1>
					</div>
				</div>
					<div class="ny_rightframe">
						<div class="liebiao">
								<h3>
									当前位置：首页选择 学生登陆 选择查看单位回复 共
									<FONT color="red">&nbsp;<b><bean:write name="rsNum" />
									</b>&nbsp;</FONT>条相关记录
								</h3>
							<table width="93%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr height="25" class="btys">
									<td align="left">
										<font color="red">单位名称</font>
									</td>
									<td align="center">
										<font color="red">回复时间</font>
									</td>
									<td align="center">
										<font color="red">操作</font>
									</td>
								</tr>
								<logic:iterate name="dwhf" id="s">
									<tr height="25" onmouseover="rowOnClick2(this)">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name='v' />" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<td class="tubiao">
													<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<td align="center">
												<bean:write name="v" />
											</td>
											<td align="center">
											    <button onclick="newpage_dwhf()">查看</button>&nbsp;&nbsp;
											    <button onclick="delone()">删除</button>
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
							<TABLE width="99%" id="rsTable" class="tbstyle">
								<TR>
									<TD height=3></TD>
								</TR>
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
						</div>
						<h2></h2>
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
			</logic:notEmpty>
			<logic:notEmpty name="delete">
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
