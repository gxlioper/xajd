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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body onload="">
		<html:form action="/zjgszy_gygl" method="post">
			<input type="hidden" id="disableEle" name="disableEle"
				value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url"
				value="/zjgszy_gygl.do?method=gyTzzAdd" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置： 公寓管理 - 信息维护 - 公寓团总支
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							公寓团总支信息
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" width="15%">
						<font color="red">*</font>学号：
					</td>
					<td align="left" width="25%">
						<html:text name='rs' property="xh" styleId="xh"
							onblur="dctStuXh()"
							onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							选择
						</button>
					</td>
					<td align="right" width="15%">
						<font color="red">*</font>楼栋：
					</td>
					<td align="left">
						<html:select property="lddm" style="width:150px">
							<html:option value=""></html:option>
							<html:options collection="ldList" property="lddm"
								labelProperty="ldmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						姓名：
					</td>
					<td align="left">
						<bean:write name='rs' property="xm" />
					</td>
					<td align="right">
						部门：
					</td>
					<td align="left">
						<html:text property="bm" styleId="bm"  maxlength="25"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						性别：
					</td>
					<td align="left">
						<bean:write name='rs' property="xb" />
					</td>
					<td align="right">
						<font color="red">*</font>职务：
					</td>
					<td align="left">
						<html:text property="zw" styleId="zw"  maxlength="10"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						年级：
					</td>
					<td align="left">
						<bean:write name='rs' property="nj" />
					</td>
					<td align="right">
						<font color="red">*</font>任职日期：
					</td>
					<td align="left">
						<html:text property="rzrq" styleId="rzrq" style="cursor:hand;" onblur="dateFormatChg(this)"
							onclick="return showCalendar('rzrq','y-mm-dd');" readonly="true" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />名称：
					</td>
					<td align="left">
						<bean:write name='rs' property="xymc" />
					</td>
					<td align="right">
						离职日期：
					</td>
					<td align="left">
						<html:text property="lzrq" styleId="lzrq" style="cursor:hand;" onblur="dateFormatChg(this)"
							onclick="if($('rzrq').value.replace(' ','')==''){return false;}else{return showCalendar('lzrq','y-mm-dd');}"
							readonly="true" />
					</td>
				</tr>
				<tr>
					<td align="right">
						专业名称：
					</td>
					<td align="left">
						<bean:write name='rs' property="zymc" />
					</td>
					<td align="right">
						联系电话：
					</td>
					<td align="left">
						<html:text property="lxdh" styleId="lxdh"  maxlength="25"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						班级名称：
					</td>

					<td align="left">
						<bean:write name='rs' property="bjmc" />
					</td>
					<td align="right">

					</td>
					<td align="left">
					</td>
				</tr>
				<tr>
					<td align="right">
						备注：
					</td>
					<td align="left" colspan="3">
						<html:textarea property='bz' styleId="bz" style="width:500px"
							rows='5' />
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="tzzSave('xh-lddm-zw-rzrq')"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonSave">
					关 闭
				</button>
			</div>
		</html:form>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
	          alert("操作成功！");
	          Close();
			  window.dialogArguments.document.getElementById('search_go').click();
	    </script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
	          alert("操作失败,或库中已存在与带\"*\"号项目相同的记录，请检查输入的数据后再提交！");
	    </script>
		</logic:equal>
	</body>
	<script type="text/javascript">
     function tzzSave(mustFill){
        var lzrq = document.getElementById('lzrq').value.replace(" ","");
	    var rzrq = document.getElementById('rzrq').value.replace(" ","");
	    if(rzrq!=""&&lzrq!=""
	           &&rzrq>=lzrq){
		   alert('任职日期应早于离职日期！');
		   return false;
	    } 
	    if($("bz").value.length>200){
	      alert("备注字数不能大于200字！");
	      return false;
	    }	    
	    var eles = mustFill.split("-");
	    for (i = 0; i < eles.length; i++) {
		    if (document.getElementById(eles[i]).value == "") {
			alert("请将带\"*\"号的项目输入完整！");
			return false;
		    }		
	    }
	    refreshForm("/xgxt/zjgszy_gygl.do?method=gyTzzAdd&doType=save");
     }
  </script>
</html>
