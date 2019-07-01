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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<title><bean:message key="lable.title" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta http-equiv="Content-Language" content="GBK" />
<meta name="Copyright" content="正方软件 zfsoft" />
</head>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<FORM method="POST" name="myform" action="/XsgyglDispatch.do?method=xsYjx">
		<input type="hidden" name="id" id="id" value="<bean:write name="yjid" scope="request"/>">
			<div class="title">
				<div class="title_img" id="title_m">
					学生意见箱
				</div>
			</div>
  				<fieldset>		
				<legend>
					添加信息
				</legend>
				<TABLE class="tbstyle" width="100%">
					<TR>
						<TD align=right width="100">
							意见标题：<br>
							(限50字)
						</TD>
						<TD align=left colspan="3">
							<html:text name="rs" property="title" styleId="title" style="width:600px" maxlength="50"></html:text>
						</TD>
					</TR>
					<TR>
						<TD align=right width="100">
							意见内容：<br>
							(限一千字)
						</TD>
						<TD colspan="3">
							<html:textarea name="rs" property="content" styleId="content" cols="95" rows="15"></html:textarea>
						</TD>
					</TR>
					<TR>
						<TD colspan=4 align=center>
							<button class="button2" onclick="yjpub()">
								发表
							</button>
							<INPUT type=reset name=b2 value="重填" class="button2">
						</TD>
					</TR>
				</TABLE>
			</fieldset>
			 <fieldset>
			   <legend> &nbsp;&nbsp;发 布 意 见 列 表&nbsp;&nbsp;</legend> 
			   		<logic:empty name="yjList">
			   		<p align="center">未曾发布过任何意见</p>
			   		</logic:empty>
			   		<logic:notEmpty name="yjList">	      			
  					<table border="0"  width="100%" class="tbstyle">  
  					    <thead>
  					    <tr><td  onclick="tableSort(this)">标题</td><td  onclick="tableSort(this)">发表人</td><td  onclick="tableSort(this)">发表时间</td><td  onclick="tableSort(this)">有无回复</td><td>操作</td></tr>
  					    </thead>
    					<logic:iterate id="list" name="yjList"> 
    						<tr onmouseover="rowOnClick(this)" > 
      							<td> <a href="/xgxt/XsgyglDispatch.do?method=viewYjXx&id=<bean:write name="list" property="id"/>" target=_black><bean:write name="list" property="title"/>    							
      							</a> </td> 					
      							<td > <bean:write name="list" property="xh" /> </td> 
       							<td > <bean:write name="list" property="pubdate" /> </td>
       							<td > <bean:write name="list" property="ywhf" /> </td>
       							<td>
       							<logic:equal value="无" name="list" property="ywhf">
       							<a href="#"  onclick="if(confirm('确实要修改该条记录吗？')){location='/xgxt/XsgyglDispatch.do?method=xsYjx&doType=modi&id=<bean:write name="list" property="id"/>';}" >修改</a>
       							/<a href="#" onclick="if(confirm('确实要删除该条记录吗？')){location='/xgxt/XsgyglDispatch.do?method=xsYjx&doType=del&id=<bean:write name="list" property="id"/>';}" >删除</a>
       							</logic:equal>
       							</td>
    						</tr> 
    					</logic:iterate> 
  					</table>  									
  					</logic:notEmpty>		
  				</fieldset>	 
			<logic:equal value="yes" name="done">
				<script type="text/javascript">
	           alert("发布成功！");
	        </script>
			</logic:equal>
			<logic:equal value="no" name="done">
				<script type="text/javascript">
	          alert("发布失败！");
	        </script>
			</logic:equal>
		</FORM>
	</body>
	<script type="text/javascript">
	   function yjpub(){
	       if($('title').value==''||$('content').value==''){
	          alert('所有项目不能为空！');
	          return false;
	       }
	       if($('title').value.length>50){
	          alert("标题字数不能大于50字！");
	          return false;
	       }
	       if($('content').value.length>1000){
	          alert("内容字数不能大于1000字！");
	          return false;
	       }
	       showTips('意见发表中，请稍候...');
	       refreshForm('/xgxt/XsgyglDispatch.do?method=xsYjx&doType=pub')	   	    
	 }
	
	</script>
</html>

