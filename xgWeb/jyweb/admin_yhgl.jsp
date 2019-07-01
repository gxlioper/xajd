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
		

	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<html:form action="/yhgl" method="post">
			<jsp:include flush="true" page="head.jsp"></jsp:include>
            <input type="hidden" name="webtype" value="yhgl" />
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
								当前位置：<a href="index.do">首页</a>选择 用户管理 <font color="red"><font color="black">共</font><b>&nbsp;<bean:write name="rsNum" />&nbsp;</b><font color="black">条相关记录</font>&nbsp;&nbsp;&nbsp;提示：单击表头可以排序</font>
							</h3>
						
					<TABLE width="98%" align="center" class="tbborder">
						<TR>
								    <td align="left">
								      <html:text name="rs1" property="dwmc"  value="请点击录入单位名称" style="color:#999999" onfocus="this.value=''" styleId="mrdwmc"/>
								      <button onclick="searchtheinfo();" class="btn_search">单位搜索</button>&nbsp;&nbsp;&nbsp;			      
								    </td>
								    <td align="left">
								    	<button onclick="showTopWin('yhgl.do?method=yhgl&jytype=jyweb&doType=zpdw',600,500)" >所有发布招聘信息单位</button>
								    </td>					
						</TR>
					</TABLE>
					<table width="98%" align="center" class="tbborder" id="tb1">
						<thead>
							<tr align="center" style="cursor:hand" height="30"
								bgcolor="E6F4FF">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<FONT color="red"><bean:write name="tit" property="cn" />
										</FONT>
									</td>
								</logic:iterate>
								<td>
									<FONT color="red"> 操作 </FONT>
								</td>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr height="25" onmouseover="rowOnClick2(this)">
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="hidden" value="<bean:write name='v' />" />
								</logic:iterate>

								<logic:iterate id="v" name="s" offset="1" length="3">
									<td title="<bean:write name="v" />">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="4" length="1">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="5" length="1">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:equal name="usertype" value="admin" scope="session">
										<td>
										    <a onclick="viewyhxx(this)" href="#" target="_blank">查看详细
											</a>
											|
											<a onclick="delyhxx(this)" href="#">删除
											</a>
										</td>
								</logic:equal>
							</tr>
						</logic:iterate>
						<logic:present name="rs">
									<script language="javascript">
										changeView('tb1',0,10,'no','yes');
									</script>
								</logic:present>
					</table>
					<TABLE width="98%" align="center" class="tbborder">
								<tr>
									<TD align="right">
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
									</TD>
								</tr>						
					</TABLE>
</div>
					<h2></h2>
				</div>
			</div>
      </div>

			<jsp:include flush="true" page="foot.jsp"></jsp:include>
			<button onclick="refreshtheweb()" id="search_go" style="display: none"></button>
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
		function viewyhxx(obj){
		  var pkValue = curr_row.getElementsByTagName("input")[0].value;
           obj.href = "viewyhxxinfo.do?method=yhxxinfo&jytype=jyweb&doType=view&pkValue="+pkValue;
		}
		
		function delyhxx(obj){
		   var pkValue = curr_row.getElementsByTagName("input")[0].value;
           obj.href = "yhgl.do?method=yhgl&jytype=jyweb&doType=del&pkValue="+pkValue;
           
		}
		function refreshtheweb()
		{
			document.forms[0].action = "yhgl.do?method=yhgl&jytype=jyweb";
            document.forms[0].submit();
		}
		
		function searchtheinfo()
		{
		if($('mrdwmc').value=='请点击录入单位名称'){
			$('mrdwmc').value='';
		}
			document.forms[0].action = "yhgl.do?method=yhgl&jytype=jyweb&doType2=query";
            document.forms[0].submit();
		}
		</script>
</html>
