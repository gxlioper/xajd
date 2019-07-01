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
	    function refreshtheweb()
		{
			document.forms[0].action = "viewalltpxxinfo.do?method=alltpxxinfo&jytype=jyweb";
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
		<html:form action="/viewalltpxxinfo" method="post">
		<div class="mainframe">
			<div class="ny_midframe">
				<div class="leftframe">
					<jsp:include flush="true" page="left_search.jsp"></jsp:include>

					
				</div>
				<div class="ny_rightframe">
					<div class="liebiao">
							<h3>
								当前位置：首页选择 图片信息 共
								&nbsp;<b><bean:write name="rsNum" />
								</b>&nbsp;条相关记录
							</h3>
						<table  width="98%" align="center" class="tbborder">
							<thead><tr height="25" class="btys">
								<td align="center">
									标题
								</td>
								<td align="center">
									发布人
								</td>
								<td align="center">
									发布时间
								</td>
								<td align="center">
									操作
								</td>
							</tr></thead>
							<logic:iterate id="list" name="rs">
								<tr height="25" onmouseover="rowOnClick2(this)">
									<td >
										<bean:write name="list" property="bt" />
									</td>
									<td align="center">
										<bean:write name="list" property="fbr" />
									</td>
									<td align="center">
										<bean:write name="list" property="fbsj" />
									</td>
									<td align="center">
										<a
											href="viewtpxxinfo.do?method=tpxxinfo&jytype=jyweb&doType=view&pkValue=<bean:write name="list" property="rid"/>"
											target="_blank">查看详细</a> &nbsp;
										<logic:equal name="usertype" value="admin" scope="session">
										|&nbsp;
								        <a href="viewalltpxxinfo.do?method=alltpxxinfo&jytype=jyweb&doType=del&pkValue=<bean:write name="list" property="rid"/>">删除</a>
										</logic:equal>
									</td>
								</tr>
							</logic:iterate>
						</table>
						<TABLE width="98%" align="center" class="tbborder">
							<TR>
								<TD>
									<jsp:include flush="true"
										page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
								</TD>
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
			<logic:equal name="delete" value="no">
				<script>
                      alert("记录删除失败！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				<script>
                      alert("文件上传成功！");
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("文件上传失败！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<button onclick="refreshtheweb()" id="search_go" style="display: none"></button>
		</html:form>
	</body>
</html>
