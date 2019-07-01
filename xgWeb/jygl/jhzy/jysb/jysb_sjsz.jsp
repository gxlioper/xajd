<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html >
  <head>
    <title><bean:message key="lable.title" /></title>

<script language="javascript" src="js/jsFunction.js"></script>
<script type="text/javascript">
	function jysbxave(){
		 var kssj = document.getElementById("jysbkssj").value;
		 var jssj = document.getElementById("jysbjssj").value;
		 if(kssj>jssj){
			alert("开始时间不能大于结束时间");
			return false;
		 }
		 if(kssj == ""){
			alert("请填写就业上报开始时间");
			return false;
		 }
		 if(kssj == "jssj"){
			 alert("请填写就业上报结束时间");
			return false;
		 }
		 refreshForm('/xgxt/jysbsjsz.do?method=jysbsjsz&doType=save');
         $("buttonSave").disabled=true;
	}
</script>
  </head>
  <body>
    <div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：就业管理 - 参数设置 - 就业上报时间设置 
		</div>
	</div>
	<logic:equal name="view" value="no">
		<script language="javascript">
			alert('该页面您没权限访问！');
		</script>
	</logic:equal>
	<logic:notEqual name="view" value="no">
	<logic:equal name="flag" value="no">
		 <script language="javascript">
			alert('学校没设置申请时间前您不能对时间进行设置！');
		</script>
	</logic:equal>
	<logic:notEqual name="flag" value="no">
	<html:form action="/jhzyjysb">
		<input type="hidden" value="<bean:write name="userType"/>" id="userType"/>
		<div align=center style="font-size:15px">就业上报时间设置</div>
		<table border="0" class="tbstyle" align="center" style="width:60%">
		<tr align=center>
			<td style="width:50%">就业上报开始时间：</td>
			<td style="width:50%;text-align:left" ><input type="text" name="jysbkssj" id="jysbkssj" style="width:90%" onclick="return showCalendar('jysbkssj','y-mm-dd');" onblur="dateFormat(this)" readonly="readonly" style="cursor:hand " value="<bean:write name="rs" property="jysbkssj"/>"></td>
		</tr>
		<tr align=center>	
			<td style="width:50%">就业上报结束时间：</td>
			<td style="width:50%;text-align:left"><input type="text" name="jysbjssj" id="jysbjssj" style="width:90%" onclick="return showCalendar('jysbjssj','y-mm-dd');" onblur="dateFormat(this)" readonly="readonly" style="cursor:hand " value="<bean:write name="rs" property="jysbjssj"/>"></td>
		</tr>
		<br/>
	</table>
	<div class="buttontool" align=center>
		<button style="width:10%" id="buttonSave" onclick="jysbxave();" class="button2">保存</button>
	</div>
	</html:form>
	<logic:equal name="result" value="yes">
		 <script language="javascript">
			alert('保存成功！');
		</script>
	</logic:equal>
	<logic:equal name="result" value="no">
		 <script language="javascript">
			alert('保存失败！');
		</script>
	</logic:equal>
	</logic:notEqual>
	</logic:notEqual>
  </body>
</html:html>
