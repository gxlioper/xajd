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
		<script language="javascript" src="js/AjaxFunction.js"></script>

	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body >
		<html:form action="/addzxdt" method="post" >
			<jsp:include flush="true" page="head.jsp"></jsp:include>
			<input type="hidden" name="webtype" value="zxdt" />
			<div class="mainframe">
				<div class="ny_midframe">
					<div class="leftframe">
						<jsp:include flush="true" page="contrl.jsp"></jsp:include>
 					</div>
					<div class="ny_rightframe">
						<div class="ny_con">
							<h1>
								<h3>
									当前位置：
									<a href="index.do">首页</a>选择最新动态
									<font color="red">提示：点击标题可以查看详情,双击一行空白处可以修改内容。</font>
								</h3>

							</h1>
							<table width="98%" align="center" class="tbborder" id="tb1">
								<tr align="center" class="btys">
									<td>
										<font color="red">新闻标题</font>
									</td>
									<td>
										<font color="red">发布人</font>
									</td>
									<td>
										<font color="red">发布时间</font>
									</td>
									<td>
										<font color="red">操作</font>
									</td>
								</tr>
								<logic:iterate name="news" id="s1">
									<tr onmouseover="rowOnClick(this)" ondblclick="updateinfo()">
										<input type="hidden" name="newsid"
											value="<bean:write name="s1" property="newsid" />" />
										<td>
											<div align="left">
												<a title="<bean:write name="s1" property="allnewstitle" />" class="tubiao" target="_blank"
													href="viewnewsinfo.do?method=newsinfo&jytype=jyweb&newsid=<bean:write name="s1" property="newsid" />&rownum=<bean:write name="s1" property="r" />"
													 title="<bean:write name="s1" property="newstitle" />">
													<bean:write name="s1" property="newstitle" /> </a>
											</div>
										</td>
										<td align="center">
											<bean:write name="s1" property="puber" />
										</td>
										<td align="center">
											<bean:write name="s1" property="pubtime" />
										</td>
										<td align="center">
											<a href="#" onclick="updateinfo()">修改</a>&nbsp;&nbsp;&nbsp;<a href="" onclick="delmessage(this)">删除</a>
										</td>

									</tr>
								</logic:iterate>
								<logic:present name="news">
									<script language="javascript">
										changeView('tb1',0,25,'yes','yes');
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
							<table width="100%" align="center" class="tbborder">
								<tr>
									<td width="30">
										标题
									</td>
									<td>
										<DIV align="left">
											<input type="text" id="bt" name="bt" value="" maxlength="25"
												size="60%">
										</DIV>
									</td>
								</tr>
								<TR>
									<TD align=right>
										栏目
									</TD>
									<TD>
										<div align="left">
											<select name="wjlx">
												<option value="最新动态">
													最新动态
												</option>
											</select>
										</div>
									</TD>
								</TR>
								<TR>
									<TD align=right>
										内容
									</TD>
									<TD align=center>
										<INPUT type="hidden" name="content1" value="">
										<IFRAME ID="eWebEditor1" src="BatEditor" frameborder="0"
											scrolling="no" width="100%" height="350"></IFRAME>
									</TD>
								</TR>
								<TR>

									<TD colspan=2 align=center>
										<button onclick="addmessage();">
											提交
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="reset">
											重置
										</button>
									</TD>
								</TR>
							</table>
						</div>
						<h2></h2>
					</div>
				</div>
			</div>
			<jsp:include flush="true" page="foot.jsp"></jsp:include>
			<button onclick="refreshtheweb()" id="search_go"
				style="display: none" />
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
		function addmessage(){	  
		   var newsTitle = document.getElementById("bt").value;
		   
		   if(newsTitle==""){
		     alert("标题不能为空！");
		     return false;
		   }
		   
		   document.getElementById('content1').value = frames('eWebEditor1').getHTML();
	       document.forms[0].action = "addzxdt.do?method=addzxdt&jytype=jyweb&doType=save";
	       document.forms[0].submit();
		}
		
		function updateinfo(){
		var url ="updatenewsinfo.do?method=updatenewsinfo&jytype=jyweb&pkValue=";
		
		var pkValue ="";	 
		  pkValue = curr_row.getElementsByTagName("input")[0].value;
		  url += pkValue;
		  showTopWin(url, 650, 500);
		}
		
		
		function delmessage(obj){
		   var pkValue = curr_row.getElementsByTagName("input")[0].value;

           obj.href = "addzxdt.do?method=addzxdt&doType=del&jytype=jyweb&pkValue="+pkValue;
           
		}
		function refreshtheweb()
		{
			document.forms[0].action = "addzxdt.do?method=addzxdt&jytype=jyweb";
            document.forms[0].submit();
		}
		</script>
</html>
