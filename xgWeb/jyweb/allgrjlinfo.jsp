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
        function refreshthegrjlweb()
		{
			document.forms[0].action = "viewallgrjl.do?method=allgrjlinfo&doType2=change&jytype=jyweb";
            document.forms[0].submit();
		}
        
        function qingkong()
		{
			document.forms[0].action = "viewallgrjl.do?method=allgrjlinfo&doType3=qingkong&jytype=jyweb";
            document.forms[0].submit();
		}
	
		function query()
		{
			document.forms[0].action = "viewallgrjl.do?method=allgrjlinfo&act=query&jytype=jyweb";
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
		<html:form action="/viewallgrjl" method="post">
			<jsp:include flush="true" page="head.jsp"></jsp:include>
            <input type="hidden" name="webtype" value="ckjl" />
			<div class="mainframe">
				<div class="ny_midframe">
					<div class="leftframe">
					    <jsp:include flush="true" page="dwcontrl.jsp"></jsp:include>
						<div class="sszw">
							<h1></h1>
							<table class="tbstyle_1" width="100%">
								<tr style="height: 25px">
									<td colspan="2">
										<bean:message key="lable.xsgzyxpzxy" />
										<html:select name="rs1" property="xymc" styleId="xymc"
											style="width:133px" onchange="refreshthegrjlweb();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xymc"
												labelProperty="xymc" />
										</html:select>
									</td>
								</tr>
								<tr style="height: 25px">
									<td colspan="2">
										专业
										<html:select name="rs1" property="zymc" styleId="zymc"
											style="width:133px">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zymc"
												labelProperty="zymc" />
										</html:select>
									</td>
								</tr>
								<tr style="height: 25px">
									<td>
										姓名：
									</td>
									<td align="left">
										<html:text name="rs1" property="xm" style="width:90px" />					
									</td>
								</tr>
								<tr style="height: 25px">
									<td>
										性别：
									</td>
									<td align="left">
										<html:select name="rs1" property="xb" style="width:90px">
											<html:option value=""></html:option>
											<html:option value="男">男</html:option>
											<html:option value="女">女</html:option>
										</html:select>
									</td>
								</tr>
								<tr style="height: 25px">
									<td>
										更新时间：
									</td>
									<td>
										<html:select name="rs1" property="xjsj" style="width:90px">
											<html:option value=""></html:option>
											<html:option value="-1">当天</html:option>
											<html:option value="-2">近两天</html:option>
											<html:option value="-7">一周内</html:option>
											<html:option value="-15">半月内</html:option>
											<html:option value="-30">一月内</html:option>
											<html:option value="-90">三月内</html:option>
											<html:option value="-180">半年内</html:option>
											<html:option value="-365">一年内</html:option>

										</html:select>
									</td>
								</tr>
								<tr align="center" style="height: 36px">
									<td colspan="2">
										<button onClick="query();">
											搜 索
										</button>
									 &nbsp;&nbsp;
										<button onClick="qingkong();">
											清 空
										</button>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="ny_rightframe">
						<div class="liebiao">
								<h3>
									当前位置：首页选择 个人简历列表 共
									<font color="red"><b>&nbsp;<bean:write name="rsNum" />&nbsp;</b>
									</font>条相关记录
									<font color="red">提示：单击表头可以排序</font>
								</h3>
							<table width="98%" align="center" class="tbborder">
								<thead>
									<tr align="center" style="cursor:hand" height="30"
										bgcolor="E6F4FF">
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)">
												<FONT color="red"><bean:write name="tit"
														property="cn" /> </FONT>
											</td>
										</logic:iterate>
										<td>
											<FONT color="red"> 操作 </FONT>
										</td>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr height="25" onmouseover="rowOnClick2(this)" align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name='v' />" />
										</logic:iterate>

										<logic:iterate id="v" name="s" offset="1" length="5">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="6" length="1">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<td>
											<a onclick="viewgrjl(this)" href="" target="_blank">查看详细 </a>
										</td>
									</tr>
								</logic:iterate>
							</table>
							<TABLE width="100%" id="rsTable" class="tbstyle">
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
			<button onclick="refreshtheweb()" id="search_go"
				style="display: none" ></button>
		</html:form>
	</body>
	<script type="text/javascript">
		function viewgrjl(obj){
		  var pkValue = curr_row.getElementsByTagName("input")[0].value;
           obj.href = "viewgrjlinfo.do?method=grjlinfo&jytype=jyweb&pkValue="+pkValue;
		
		}
		
		function refreshtheweb()
		{
			document.forms[0].action = "viewallgrjl.do?method=allgrjlinfo&jytype=jyweb";
            document.forms[0].submit();
		}
		</script>
		<logic:notEmpty name="nopass">
				<logic:equal name="nopass" value="nopass">
					<script>
                      alert("管理员还未对你进行身份验证，暂时无法查看学生简历！");
                    </script>
				</logic:equal>
		</logic:notEmpty>
</html>
