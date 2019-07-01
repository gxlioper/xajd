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
				
	
		function newpage_qzxx(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "qzxxinfo.do?method=qzxxinfo&jytype=jyweb&doType=view&pkValue="+pkValue;
		}
		
		
		function delone(obj){
		  var pkValue =  curr_row.getElementsByTagName("input")[0].value;
		  obj.href = "viewallqzxxinfo.do?method=allqzxxinfo&jytype=jyweb&doType=del&pkValue="+pkValue;
		}
		
		function refreshtheweb()
		{
			document.forms[0].action = "viewallqzxxinfo.do?method=allqzxxinfo&jytype=jyweb&doType2=change";
            document.forms[0].submit();
		}
		
		function qingkong()
		{
			document.forms[0].action = "viewallqzxxinfo.do?method=allqzxxinfo&jytype=jyweb&doType3=qingkong";
            document.forms[0].submit();
		}
	
		function query()
		{
			document.forms[0].action = "viewallqzxxinfo.do?method=allqzxxinfo&jytype=jyweb&act=query";
            document.forms[0].submit();
		}
	
		function   document.onclick(){   
           showSel(false)   
        }   
        function   showSel(flag){   
        document.all.hyfl.style.display=(flag?"block":"none")   
        window.event.cancelBubble=true;   
        }   
        function   selText(obj){   
        obj.value=(document.all.hyfl.options[document.all.hyfl.selectedIndex].text)    
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
		<html:form action="/viewallqzxxinfo" method="post">
			<div class="mainframe">
				<div class="ny_midframe">
					<div class="leftframe">
						<div class="default_box">
							<h3><em>搜索职位</em></h3>
							<table>
								<tr>
									<td colspan="2">
										<bean:message key="lable.xsgzyxpzxy" />：
										<html:select name="rs1" property="xymc" styleId="xymc"
											style="width:113px" onchange="refreshtheweb();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xymc"
												labelProperty="xymc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										专业：
										<html:select name="rs1" property="zymc" styleId="zymc"
											style="width:113px">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zymc"
												labelProperty="zymc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td>
										性别：
									</td>
									<td>
										<html:select name="rs1" property="xb" style="width:100%">
											<html:option value=""></html:option>
											<html:option value="男">男</html:option>
											<html:option value="女">女</html:option>
										</html:select>
									</td>
								</tr>
								<tr>
									<td>
										求职行业：
									</td>
									<td>
										<table border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td>
													<html:text name="rs1" property="iData" style="width:78px"
														readonly="true" styleClass="inputtext" />
													<%--<input name=iData size="13"
														style="margin:0;border-right-width:0px;height:20;" />
													--%>
													<input type=button onclick=showSel(true) value="6"
														style="margin:0;cursor:hand;font-family:Webdings;width:18px;height:18px;vertical-align:baseline;padding:1px;line-height:5px" />
												</td>
											</tr>
											<tr>
												<td>
													<html:select name="rs1" property="hyfl" size="10"
														style="position:absolute;display:none"
														onchange="selText(iData)" styleClass="inputtext">
														<html:option value=""></html:option>
														<html:options collection="hyflList" property="hyfl"
															labelProperty="hyfl" />
													</html:select>
												</td>
											</tr>
										</table>

									</td>
								</tr>
								<tr>
									<td>
										求职意向：
									</td>
									<td>
										<html:text name="rs1" property="qzyx" style="width:100%"  styleClass="inputtext" />

									</td>
								</tr>
								<tr>
									<td>
										发布时间：
									</td>
									<td>
										<html:select name="rs1" property="xjsj" style="width:100%">
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
								<tr align="center">
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
									当前位置：首页选择 求职信息 共
									<FONT color="red">&nbsp;<b><bean:write name="rsNum" />
									</b>&nbsp;</FONT>条相关记录
								</h3>
							<table width="98%" align="center" class="tbborder" id="tb1">
								<tr height="25" class="btys">
									<td align="center">
										专业
									</td>
									<td align="center">
										性别
									</td>
									<td align="center">
										求职意向
									</td>
									<td align="center">
										发布时间
									</td>
									<td align="center">
										操作
									</td>
								</tr>
								<logic:iterate name="rs" id="s">
									<tr height="25" onmouseover="rowOnClick2(this)">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name='v' />" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="3">
											<td align="center">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<td>
										<div align="center">
											<a onclick="newpage_qzxx(this)" href="" target="_blank">查看详细</a>
											<logic:equal name="usertype" value="admin" scope="session">
									|&nbsp;<a onclick="delone(this)" href="">删除 </a>
											</logic:equal>
											</div>
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
