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
	<script type="text/javascript">
	
	function addly(){
	
	 var yzm =document.getElementById("yzm").value;
	 document.getElementById('content1').value = frames('eWebEditor1').getHTML();
	 
	 var wjnr = document.getElementById('content1').value;
	 
	 
	 
	 if(yzm==""){
	 alert("请输入验证码!");
	 return false;
	 }
	 if(yzm.length!=4){
	 alert("验证码位数不对!");
	 return false;
	 }
	 
	 if(wjnr==""){
	 alert("对不起，不能发表空信息！");
	 return false;
	 }
	 
	
	
	
	document.forms[0].action = "savelybinfo.do?method=lybinfo&doType=save&jytype=jyweb";
	document.forms[0].submit();

	}
	 function delone(obj){
		  var pkValue =  curr_row.getElementsByTagName("input")[0].value;
		  obj.href = "lybinfo.do?method=lybinfo&jytype=jyweb&doType=del&pkValue="+pkValue;
	}
	
	function delall(){
	     if(confirm("确定要清空留言板所有数据？")){
		    document.forms[0].action = "lybdelall.do?method=lybinfo&jytype=jyweb&doType=delall";
	        document.forms[0].submit();
	        return true;
	     }else{
	        return false;
	     }
	}
	function fuserip(obj){
	     var pkValue =  curr_row.getElementsByTagName("input")[0].value;
	    
		    document.forms[0].action = "savelybinfo.do?method=lybinfo&jytype=jyweb&doType=fip&pkValue="+pkValue;
	        document.forms[0].submit();
	}
	
	function refreshtheweb()
		{
			document.forms[0].action = "lybinfo.do?method=lybinfo&jytype=jyweb";
            document.forms[0].submit();
		}
	
	</script>

	<body>
		<html:form action="/savelybinfo" method="post">
			<jsp:include flush="true" page="head.jsp"></jsp:include>
			<div class="mainframe">
				<div class="jy_midframe">
					<h1>
						留言板
					</h1>
					<table width="98%" align="center" class="tbborder">
						<logic:iterate id="s" name="rs">
							<tr height="10" bgcolor="#4795E5">
								<td colspan="2">
									&nbsp;
								</td>
							</tr>
							<tr height="30" onmouseover="rowOnClick2(this)">
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="hidden" value="<bean:write name='v' />" />
								</logic:iterate>
								<td rowspan="2" width="20%">
									<logic:iterate id="v" name="s" offset="1" length="1">
										第<font color="red"><bean:write name="v" /> </font>个发言者
                         </logic:iterate>
									<br>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<img style="width:100px" src="<bean:write name="v" />" />
									</logic:iterate>
								</td>
								<td>
									&nbsp;&nbsp;
									<logic:iterate id="v" name="s" offset="6" length="1">
								来自:&nbsp;&nbsp;<font color="#0F03AF"><bean:write name="v" />
										</font>
									</logic:iterate>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;
									<logic:iterate id="v" name="s" offset="3" length="1">
                                 发布人:&nbsp;&nbsp;<font color="#0F03AF"><bean:write
												name="v" /> </font>
									</logic:iterate>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;
								
									<logic:iterate id="v" name="s" offset="4" length="1">
                                 发布时间：&nbsp;&nbsp;<font color="#0F03AF"><bean:write
												name="v" /> </font>
									</logic:iterate>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:equal name="usertype" value="admin" scope="session">
										<a onclick="delone(this)" href=""><font color="red">删除</font>
										</a>|
									    <a onclick="fuserip(this)" href=""><font color="red">封IP</font>
										</a>
									</logic:equal>
								</td>
							</tr>
							<tr height="100" valign="top">
								<td>
									<br />
									<DIV align="left">
										<logic:iterate id="v" name="s" offset="5" length="1">
											<bean:write name="v" filter="false" />
										</logic:iterate>
									</DIV>
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
					
					<logic:equal name="usertype" value="admin" scope="session">
                     <br>
						<div align="right">
							<button onclick="delall();">
								全部清空
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
					</logic:equal>
    <br>
<br>
					<table width="98%" align="center" class="tbborder">
						<tr>
							<td align="right" >
								发布人：
							</td>
							<td>
								<DIV align="left">
									<input name="yhm" value="" maxlength="10">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 请输入右边图片中的文字：
									<input name="yzm" type="text" class="textbox"
										style="width:80px" maxlength="4"
										onkeypress="if(event.keyCode==13)dl();" />
									<img src="yzm.jsp" border="0" align="absmiddle" />
								</DIV>
							</td>
						</tr>
						<TR>
							<TD align=right width="100">
								编辑内容：
							</TD>
							<TD align=center colspan="3">
								<INPUT type="hidden" name="content1" value="">
								<IFRAME ID="eWebEditor1" src="BatEditor" frameborder="0"
									scrolling="no" width="100%" height="350"></IFRAME>
							</TD>
						</TR>
						<TR>
							<TD colspan=4 align=center>
								<button onclick="addly();">
									提交
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="reset">
									关闭
								</button>
							</TD>
						</TR>
					</table>
					<h3></h3>
				</div>
			</div>
			<jsp:include flush="true" page="foot.jsp"></jsp:include>
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
			<logic:notEmpty name="add">
				<logic:equal name="add" value="no">
					<script>
                      alert("验证码错误！");
                   </script>
				</logic:equal>
				<logic:equal name="add" value="fip">
					<script>
                      alert("对不起，你已经被封了ID,IP和IQ!");
                   </script>
				</logic:equal>
			</logic:notEmpty>
			
			<button onclick="refreshtheweb()" id="search_go" style="display: none"></button>
		</html:form>
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
		<logic:notEmpty name="delall">
			<logic:equal name="delall" value="ok">
				<script>
                      alert("所有记录已清空！");
                    </script>
			</logic:equal>
			<logic:equal name="delall" value="no">
				<script>
                      alert("清空记录失败！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="fip">
			<logic:equal name="fip" value="ok">
				<script>
                      alert("该用户ip地址已封！");
                    </script>
			</logic:equal>
			<logic:equal name="fip" value="no">
				<script>
                      alert("封ip地址失败！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
