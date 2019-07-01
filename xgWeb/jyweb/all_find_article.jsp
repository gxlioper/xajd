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
				
	    function findarticle(){
		    document.forms[0].action = "findarticle.do?method=findarticle&jytype=jyweb&doType=find";
		    document.forms[0].submit();
		}
		
		function newpages(obj)
		{
		    var tablename = document.getElementById("tablename").value;
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewmorearticleinfo.do?method=viewmorearticleinfo&jytype=jyweb&tablename="+ tablename +"&pkValue="+pkValue;
		}
		function refreshtheweb()
		{
			document.forms[0].action = "findarticle.do?method=findarticle&jytype=jyweb&doType=find";
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
		<html:form action="/findarticle" method="post">
			<html:hidden name="tablename" property="tablename" />
			<div class="mainframe">
				<div class="ny_midframe">
					<div class="leftframe">
						<div class="wzss">
						  <h3>文章搜索</h3>
							<table width="95%" align="center">
								<tr>
									<td>
										关键字：
									</td>
									<td>
										<html:text name="rs1" property="gjz" style="width:100%;"									
											onkeypress="if(window.event.keyCode==13){ findarticle(); } " />
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;
									</td>
									<td>
										<html:select name="rs1" property="find" >
										<html:option value=""></html:option>
											<html:option  value="zxdt">
												最新动态</html:option>
											<html:option  value="tpxx">
												图片信息</html:option>
											<html:option  value="zcfg">
												政策法规</html:option>
											<html:option  value="ggl">
												公告栏</html:option>
											<html:option  value="zph">
												招聘会</html:option>
											<html:option  value="syjs">
												生源介绍</html:option>
											<html:option  value="xzzx">
												下载中心</html:option>
										</html:select>
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;
									</td>
									<td>
										<button onClick="findarticle();" class="btn_search">

										</button>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="ny_rightframe">
						<div class="liebiao">
							<h1>
								<h3>
									当前位置：首页选择 查询结果 共
									<FONT color="red">&nbsp;<b><bean:write name="rsNum" />
									</b>&nbsp;</FONT>条相关记录
								</h3>
							</h1>
							<table width="98%" align="center" class="tbborder">
								<thead>
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>" onclick="tableSort(this)" nowrap align="center">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr align="left" onmouseover="rowOnClick2(this)">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name='v' />" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1">
											<td>
												<a target="_blank" onclick="newpages(this)" href=""> <bean:write
														name="v" /> </a>
											</td>
										</logic:iterate>
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
				style="display: none" />
		</html:form>
	</body>
</html>
