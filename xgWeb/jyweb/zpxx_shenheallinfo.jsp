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
				
		
		function newpage(obj){
		    var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewzpinfo.do?method=jyzpinfo&doType=view&jytype=jyweb&pkValue="+pkValue;
		}

		function delone(obj){
		    if(confirm("确定要删除该条招聘信息吗？")){
		       var pkValue =  curr_row.getElementsByTagName("input")[0].value;
		       obj.href = "zpshenheallinfo.do?method=zpshenheinfo&jytype=jyweb&doType=del&pkValue="+pkValue;
		    }else{
		       obj.href = "zpshenheallinfo.do?method=zpshenheinfo";
		    }
		}
		
		function shenhe(obj){
		  var pkValue =  curr_row.getElementsByTagName("input")[0].value;
		  obj.href = "zpshenhe.do?method=zpshenhe&doType=view&jytype=jyweb&pkValue="+pkValue;
		}
		
		function refreshtheweb()
		{
			document.forms[0].action = "zpshenheallinfo.do?method=zpshenheinfo&jytype=jyweb";
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
		<html:form action="/zpshenheallinfo" method="post">
			<input type="hidden" name="webtype" value="zpsh" />
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
									<a href="index.do">首页</a>选择 招聘信息审核 共
									<font color="red"><b>&nbsp;<bean:write name="rsNum" />&nbsp;</b>
									</font>条相关记录
								</h3>
							<div align="left">
							&nbsp;&nbsp;&nbsp;审核状态：
							<html:select name="rs1" property="xxsh" onchange="refreshtheweb();">
								<html:option value=""></html:option>
								<html:option value="未审核">未审核</html:option>
								<html:option value="通过">已通过</html:option>
								<html:option value="不通过">不通过</html:option>
							</html:select>
							&nbsp;&nbsp;&nbsp;信息来源：
							<html:select name="rs1" property="xxly" onchange="refreshtheweb();">
								<html:option value=""></html:option>
								<html:option value="xx">学校</html:option>
								<html:option value="dw">单位</html:option>
							</html:select>
							</div>
							<table width="98%" align="center" class="tbborder" id="tb1">
								<thead height="25" class="btys">
								<tr>
									<td>
										<font color="red">公司名称</font>
									</td>
									<td>
										<font color="red">招聘岗位</font>
									</td>
									<td>
										<font color="red">发布时间</font>
									</td>
									<td>
										<font color="red">审核结果</font>
									</td>
									<logic:equal name="usertype" value="admin" scope="session">
										<td>
											<font color="red">操作</font>
										</td>
									</logic:equal>
								</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr height="25" onmouseover="rowOnClick(this)">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name='v' />" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<td>
												<a target="_blank" onclick="newpage(this)" href="" title="<bean:write name="v" />"> 
												<bean:write name="v" /> </a>
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<td title="<bean:write name="v" />">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="3" length="1">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="4" length="1">
											<td>
												<bean:write name="v" />
											</td>
											<logic:equal name="usertype" value="admin" scope="session">
												<td>
													<a onclick="delone(this)" href="">删除 </a>|
													<a onclick="shenhe(this)" href="" target="_blank">审核</a>
												</td>
											</logic:equal>
										</logic:iterate>
									</tr>
								</logic:iterate>
								<logic:present name="rs">
									<script language="javascript">
										changeView('tb1',0,11,'yes','yes');
										changeView('tb1',1,11,'no','yes');
									</script>
								</logic:present>
							</table>
							<TABLE width="98%" align="center" class="tbborder">
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
									</TD>
								</TR>
							</TABLE>
							<br>
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
