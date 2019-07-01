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
		function addmessage(){
		   var wjbt = document.getElementById("bt").value;
		   if(wjbt==""){
		   alert("标题不能为空！");
		   return false;
		   }
		
		   document.getElementById('content1').value = frames('eWebEditor1').getHTML();
	       document.forms[0].action = "addmessage.do?method=addmessage&doType=save&jytype=jyweb";
	       document.forms[0].submit();
		}
		
		function delmessage(obj){
		   var pkValue = curr_row.getElementsByTagName("input")[0].value;

           obj.href = "addmessage.do?method=addmessage&doType=del&jytype=jyweb&pkValue="+pkValue;
           
		}
		function updateinfo(){
		var url ="updatemessageinfo.do?method=updateMessageInfo&jytype=jyweb&pkValue=";
		
		var pkValue ="";	 
		  pkValue = curr_row.getElementsByTagName("input")[0].value;
		  url += pkValue;
		  showTopWin(url, 650, 500);
		}
		function newpage_message(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewmessageinfo.do?method=qitainfo&jytype=jyweb&rowid="+pkValue;
		}
		
		function refreshtheweb()
		{
			document.forms[0].action = "addmessage.do?method=addmessage&jytype=jyweb";
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
		<html:form action="/addmessage" method="post">
			<jsp:include flush="true" page="head.jsp"></jsp:include>
            <input type="hidden" name="webtype" value="lmgx" />
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
									当前位置：<a href="index.do">首页</a>选择 栏目内容更新
									<font color="red">提示：单击表头可以排序;双击一行空白处可以修改内容。</font>
								</h3>
							<table  width="98%" align="center" class="tbborder">
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
									<tr height="25" onmouseover="rowOnClick2(this)" ondblclick="updateinfo()">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name='v' />" />
										</logic:iterate>

										<logic:iterate id="v" name="s" offset="1" length="1">
											<td>
												<a target="_blank" onclick="newpage_message(this)" href="">
													<bean:write name="v" /> </a>
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<td>
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
													<a onclick="updateinfo()" href="#">修改</a>&nbsp;&nbsp;&nbsp;<a onclick="delmessage(this)" href="#">删除</a>
												</td>
											</logic:equal>
										</logic:iterate>

									</tr>
								</logic:iterate>
							</table>
							<TABLE  width="98%" align="center" class="tbborder">
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
									</TD>
								</TR>
							</TABLE>



							<br>
							<table  width="100%" align="center" class="tbborder">
								<tr>
									<td width="30">
										标题
									</td>
									<td>
										<DIV align="left">
											<input type="text" id="wjbt" name="bt" value=""
												maxlength="25" size="60%">
										</DIV>
									</td>
								</tr>
								<TR width="30">
									<TD >
										栏目
									</TD>
									<TD>
										<div align="left">
											<select name="wjlx">
												<option value="">
												</option>
												<option value="公告栏">
													公告栏
												</option>
												<option value="校园专场招聘会">
													校园专场招聘会
												</option>
												<option value="生源介绍">
													生源介绍
												</option>
												<option value="国家级文件">
													国家级文件
												</option>
												<option value="市级文件">
													市级文件
												</option>
												<option value="校园专场招聘会">
													校级文件
												</option>
											</select>
										</div>
									</TD>
								</TR>
								<TR>
									<TD >
										内容
									</TD>
									<TD align=center >
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
							<h2></h2>
						</div>
					</div>
				</div>
				<jsp:include flush="true" page="foot.jsp"></jsp:include>
				<button onclick="refreshtheweb()" id="search_go" style="display: none">
				</button>
			</div>
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
</html>
