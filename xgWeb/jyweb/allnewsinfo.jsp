<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
			document.forms[0].action = "viewallnewsinfo.do?method=allnewsinfo&jytype=jyweb";
            document.forms[0].submit();
		}
		
		function delone(obj){
		  var pkValue =  curr_row.getElementsByTagName("input")[0].value;
		  obj.href = "viewallnewsinfo.do?method=allnewsinfo&jytype=jyweb&doType=del&newsId="+pkValue;
		}
		function newpage_news(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewnewsinfo.do?method=newsinfo&jytype=jyweb&newsId="+pkValue;
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
		<html:form action="/viewallnewsinfo" method="post">
			<div class="mainframe">
				<div class="ny_midframe">
					<div class="leftframe">
						<jsp:include flush="true" page="left_search.jsp"></jsp:include>
<%--						<div class="default_box">--%>
<%--							<h3><em>热点新闻</em></h3>--%>
<%--							<ul></ul>--%>
<%--						</div>--%>
					</div>
					<div class="ny_rightframe">
						<div class="ny_con02">
								<h3>
									当前位置：
									<a href="index.do">首页</a>选择 最新动态 共
									<FONT color="red">&nbsp;<b><bean:write name="rsNum" />
									</b>&nbsp;</FONT>条相关记录
								</h3>
							<table width="98%" align="center" class="tbborder">
								<thead>
								<tr align="center" class="btys">
									<td>
										新闻标题
									</td>
									<td>
										发布时间
									</td>
								</tr>
								</thead>
								<logic:iterate name="news" id="s1">
									<tr>
										<td>
											<div align="left">
												<a class="tubiao" target="_blank"
													href="viewnewsinfo.do?method=newsinfo&jytype=jyweb&newsid=<bean:write name="s1" property="newsid" />&rownum=<bean:write name="s1" property="r" />"><bean:write
														name="s1" property="newstitle" /> </a>
											</div>
										</td>
										<td align="center">
											<bean:write name="s1" property="pubtime" />
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
