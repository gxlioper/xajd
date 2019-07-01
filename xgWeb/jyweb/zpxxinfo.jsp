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
		
		function toujl(){
		
		   document.forms[0].action = "toujl.do?method=jyzpinfo&doType=view&act=toujl&jytype=jyweb";
		   document.forms[0].submit();
		
		}
		
		function savetheone(){
		
		   document.forms[0].action = "viewzpinfo.do?method=jyzpinfo&doType=view&act2=save&jytype=jyweb";
		   document.forms[0].submit();
		
		}
		</script>

	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body onload="textAreaFormat('gwzz','showdiv1');textAreaFormat('zwyq','showdiv2');textAreaFormat('gsjj','showdiv3');">
		<jsp:include flush="true" page="head.jsp"></jsp:include>
		<html:form action="/toujl" method="post">
			<html:hidden name="rs" property="zpzw" />
			<html:hidden name="rs" property="gsmc" />
			<html:hidden name="rs" property="pkValue" />
			<html:hidden name="rs" property="fbsj" />
			<div class="mainframe">
				<div class="jy_midframe">
					<h1>
					</h1>
					<div class="view_zp">
						<p class="head">
							<bean:write name="rs" property="gsmc" />
						</p>
						<table width="90%" align="center" class="grxx">
						<thead>
						  <tr>
						    <td colspan="4">招聘单位基本情况 </td>
						  </tr>
						</thead>
						
							<tr>
								<td width="15%">
									单位性质：
								</td>
								<td width="35%">
									<bean:write name="rs" property="dwxz" />
								</td>
								<td width="15%">
									单位地址：
								</td>
								<td width="35%">
									<bean:write name="rs" property="dwdz" />
								</td>
							</tr>
							<tr>
								<td>
									电子邮箱：
								</td>
								<td>
									<logic:notEqual name="yhm" value="">
										<bean:write name="rs" property="email" />
									</logic:notEqual>
									<logic:equal name="yhm" value="">
										<font color="#999999">该信息只有注册用户才可查看</font>
									</logic:equal>
								</td>
								<td>
									联系电话：
								</td>
								<td>
									<logic:notEqual name="yhm" value="">
										<bean:write name="rs" property="lxdh" />
									</logic:notEqual>
									<logic:equal name="yhm" value="">
										<font color="#999999">该信息只有注册用户才可查看</font>
									</logic:equal>
									
								</td>
							</tr>
							<tr>
								<td>
									联系人：
								</td>
								<td>
									<logic:notEqual name="yhm" value="">
										<bean:write name="rs" property="lxr" />
									</logic:notEqual>
									<logic:equal name="yhm" value="">
										<font color="#999999">该信息只有注册用户才可查看</font>
									</logic:equal>				
								</td>
								<td>
									移动电话：
								</td>
								<td>		
									<logic:notEqual name="yhm" value="">
										<bean:write name="rs" property="yddh" />
									</logic:notEqual>
									<logic:equal name="yhm" value="">
										<font color="#999999">该信息只有注册用户才可查看</font>
									</logic:equal>	
								</td>
							</tr>
							<tr>
								<td>
									传真：
								</td>
								<td>								
									<logic:notEqual name="yhm" value="">
										<bean:write name="rs" property="cz" />
									</logic:notEqual>
									<logic:equal name="yhm" value="">
										<font color="#999999">该信息只有注册用户才可查看</font>
									</logic:equal>	
								</td>
								<td>
									行业分类：
								</td>
								<td>
									<bean:write name="rs" property="hyfl" />
								</td>
							</tr>
							<tr>
								<td>
									公司网址：
								</td>
								<td>
									<bean:write name="rs" property="gswz" />
								</td>				
							</tr>
						</table>
						<table width="90%" align="center" class="grxx">
						<thead>
						  <tr>
						    <td>招聘单位简介</td>
						  </tr>
						</thead>
						  <tr>
						    <td class="con word-break">
						    	<html:textarea name="rs" property="gsjj" styleId="gsjj" style="display:none"/>
								<div name="showdiv3" id="showdiv3"></div>
							</td>
						  </tr>
						</table>  
						<table width="90%" align="center" class="grxx">
						<thead>
						  <tr>
						    <td colspan="4">招聘岗位：<FONT color="red"><bean:write name="rs" property="zpzw" /></FONT></td>
						  </tr>
						</thead>
							<tr>
								<td width="15%">招聘人数：</td>
								<td width="35%"><bean:write name="rs" property="zprs" /></td>
								<td width="15%">性别要求：</td>
								<td width="35%"><bean:write name="rs" property="xb" /></td>
							</tr>
							<tr>
							  <td>试用期薪水：</td>
							  <td><bean:write name="rs" property="syxs" /></td>
							  <td>转正后薪水：</td>
							  <td><bean:write name="rs" property="zzxs" /></td>
							</tr>
							<tr>
								<td>学历要求：</td>
								<td><bean:write name="rs" property="xlyq" /></td>
								<td>外语要求：</td>
								<td><bean:write name="rs" property="wyyq" /></td>
							</tr>
							<tr>
								<td>工作地点：</td>
								<td><bean:write name="rs" property="gzdd" /></td>
								<td>面试时间：</td>
								<td>
									<logic:notEqual name="yhm" value="">
										<bean:write name="rs" property="mssj" />
									</logic:notEqual>
									<logic:equal name="yhm" value="">
										<font color="#999999">该信息只有注册用户才可查看</font>
									</logic:equal>
								</td>
							</tr>
							<tr>
								<td>面试地点：</td>
								<td>
									<logic:notEqual name="yhm" value="">
										<bean:write name="rs" property="msdd" />
									</logic:notEqual>
									<logic:equal name="yhm" value="">
										<font color="#999999">该信息只有注册用户才可查看</font>
									</logic:equal>
								</td>
								<td>面试携带</td>
								<td><bean:write name="rs" property="msxd" /></td>
							</tr>
							<tr>
							<td>
								职位要求：
							</td>
							<td colspan="3" class="word-break">&nbsp;
								<html:textarea name="rs" property="zwyq" styleId="zwyq" style="display:none"/>
								<div name="showdiv2" id="showdiv2"></div>
							</td>
							</tr>
							<tr>
								<td>
									岗位职责：
								</td>
								<td colspan="3" class="word-break">&nbsp;
								    <html:textarea name="rs" property="gwzz" styleId="gwzz" style="display:none"/>
									<div name="showdiv1" id="showdiv1"></div>
									<div name="showdiv" id="showdiv"></div>
								</td>
							</tr>
							<tr>
								<td>
									发布时间：
								</td>
								<td colspan="3">
									<bean:write name="rs" property="fbsj" />
								</td>
							</tr>
							<tr>
								<td colspan="4" align=center>
									<logic:equal name="usertype" value="xs" scope="session">
										<div align="center">
											<logic:equal name="fbrjs" value="dw">
												<button onclick="toujl();">
													投简历
												</button>
											</logic:equal>
											<button onclick="savetheone()">
												收藏该条信息
											</button>
										</div>
									</logic:equal>
								</td>
							</tr>
						</table>
					</div>
					<h3>
					</h3>
				</div>
			</div>
			<jsp:include flush="true" page="foot.jsp"></jsp:include>
		</html:form>

		<logic:notEmpty name="nogrjl">
			<logic:equal name="nogrjl" value="nogrjl">
				<script>
                      alert("对不起，你还未登记个人简历！请先在主页登录框进行简历登记..");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="chongfu">
			<logic:equal name="chongfu" value="chongfu">
				<script>
                      alert("对不起，不能重复投简历！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="toujl">
			<logic:equal name="toujl" value="ok">
				<script>
                      alert("简历已投送，请静候通知！");
                    </script>
			</logic:equal>
			<logic:equal name="toujl" value="no">
				<script>
                      alert("对不起，未找到你的相关资料，简历投送失败！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				<script>
                      alert("已将该信息放入我的文件夹！");
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("对不起，未找到相关资料，收藏信息失败！");
                    </script>
			</logic:equal>
			<logic:equal name="save" value="chongfu">
				<script>
                      alert("对不起，你已经收藏过这条信息！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
<%--	<script type="text/javascript">--%>
<%--	   var gwzzObj = document.getElementById('gwzz');--%>
<%--	   var showdivObj = document.getElementById('showdiv');--%>
<%--	   showdivObj.innerHTML=gwzzObj.value;--%>
<%--	</script>--%>

	</body>
</html>
