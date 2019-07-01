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
		<script type="text/javascript">
				
	
		function viewmrhf()
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    url = "viewmrhfinfo.do?method=mrhfinfo&jytype=jyweb&pkValue="+pkValue;
		    showTopWin(url, 470, 250);
		}
		
		function delone(){
		  var pkValue =  curr_row.getElementsByTagName("input")[0].value;	  
		  document.forms[0].action = "viewallmrhfinfo.do?method=allmrhfinfo&jytype=jyweb&doType=del&pkValue="+pkValue;
          document.forms[0].submit();

		}
		
		function refreshtheweb()
		{
			document.forms[0].action = "viewallmrhfinfo.do?method=allmrhfinfo&jytype=jyweb";
            document.forms[0].submit();
		}
		
		function savemrhf(){
		    var bt = document.getElementById("bt").value;
		    var hf = document.getElementById("hf").value;
		    
		    if(bt==""){
		     alert("标题不能为空！");
		     return false;
		    }
		    if(hf==""){
		     alert("回复内容不能为空！");
		     return false;
		    }
		    document.forms[0].action = "viewallmrhfinfo.do?method=allmrhfinfo&jytype=jyweb&doType=save";
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
		<html:form action="/viewallmrhfinfo" method="post">
			<input type="hidden" name="webtype" value="mrhf" />
			<div class="mainframe">
				<div class="ny_midframe">
					<div class="leftframe">
						<jsp:include flush="true" page="dwcontrl.jsp"></jsp:include>

						<div class="rdxw">
							<h1></h1>
						</div>
					</div>
					<div class="ny_rightframe">
						<div class="liebiao">
								<h3>
									当前位置：
									<a href="index.do">首页</a>选择 默认回复 共
									<FONT color="red">&nbsp;<b><bean:write name="rsNum" />
									</b>&nbsp;</FONT>条相关记录
								</h3>
							<table width="98%" align="center" class="tbborder">
								<tr height="25" class="btys">
									<td align="center">
										<font color="red">用户名</font>
									</td>
									<td align="center">
										<font color="red">单位名称</font>
									</td>
									<td align="center">
										<font color="red">标题</font>
									</td>
									<td align="center">
										<font color="red">操作</font>
									</td>
								</tr>
								<logic:iterate name="list" id="s">
									<tr height="25" onmouseover="rowOnClick2(this)">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name='v' />" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1">
											<td align="center">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<td align="center">
											<a href="#" onclick="viewmrhf();">
												查看
											</a>
											&nbsp;|&nbsp;
											<a href="#" onclick="delone();">
												删除
											</a>
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

							<div>
								<div id="admingl2" style="display:">
									<button onclick="setdisplay()">
										添加默认回复
									</button>
								</div>
								<div id="admingl" style="display: none">
									<button onclick="setdisplay()">
										关闭窗体
									</button>
									<table width="98%" align="center" class="tbborder">
										<tr>
											<td align="center">
												标题：
											</td>
											<td>
												<html:text name="rs" property="bt" style="width:150px"
													maxlength="10" />
											</td>
										</tr>
										<tr>
											<td align="center">
												回复：
											</td>
											<td>
												<html:textarea name="rs" property="hf" rows="8"
													style="width:100%" />
											</td>
										</tr>
									</table>
									<div>
										<button onclick="savemrhf();">
											提 交
										</button>
										&nbsp;&nbsp;
										<button type="reset">
											重 置
										</button>
									</div>
								</div>
							</div>
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
