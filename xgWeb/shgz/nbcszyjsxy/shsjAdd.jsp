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
		<title><bean:message key="lable.title" /></title>
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
	<script language="javascript">

</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/nbcsShgzgl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow">当前所在位置：社会工作 - 信息维护 - 社会实践</span>
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
                       alert("您输入的学号无效!");
                    </script>
				</logic:equal>
				<input type="hidden" id="act" name="act"
					value="<bean:write name="act" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url"
					value="/nbcsShgzgl.do?method=shsjAdd" />
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								社会活动信息维护
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							<font color="red">*</font>学号：
						</td>
						<td align="left">
							<html:text name='rs' property="xh" readonly="readonly"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<logic:equal value="add" name="act">
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
							</logic:equal>
						</td>
						<td align="right">
							<font color="red">*</font>学年：
						</td>
						<td align="left">
							<html:select name='rs' property="xn" style="width:100px"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							姓名：
						</td>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" />
						</td>
						<td align="right">
							<font color="red">*</font>学期：
						</td>
						<td align="left">
							<html:select name='rs' property="xq" style="width:100px"
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							性别：
						</td>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" />
						</td>
						<td align="right">
							<font color="red">*</font>社会活动性质：
						</td>
						<td align="left">
							<html:select name="rs" property="hdxz" styleId="hdxz">
								<html:option value=""></html:option>
								<html:option value="0">公益活动</html:option>
								<html:option value="1">困难生义务活动</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							年级：
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" />
						</td>
						<td align="right">
							实践天数：
						</td>
						<td align="left">
							<html:text name='rs' property="sjts" styleId="sjts" maxlength="2"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xy" />
						</td>
						<td align="right">
							考核结果：
						</td>
						<td align="left">
							<html:text name='rs' property="khjg" styleId="kh"  maxlength="30"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							专业：
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zy" />
						</td>
						<td align="right">
							班级：
						</td>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bj" />
						</td>
					</tr>
					<tr>
						<td align="right">
							所获荣誉：
							<br>
							<div style="color: red"><限300字>&nbsp;&nbsp;</div>
						</td>
						<td align="left" colspan="3">
							<html:textarea name='rs' property='shry' styleId="shry" style="width:99%"
								rows='5' />
						</td>
					</tr>
					<tr align="left">
						<td align="right"><font color="red">*</font>
							社会活动项目：
							<br>
							<div style="color: red"><限50字>&nbsp;&nbsp;</div>
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='hdxm' styleId='hdxm' style="width:99%"
								rows='5' />
						</td>
					</tr>
					<tr align="left">
						<td align="right">
							社会活动内容：
							<br>
							<div style="color: red"><限300字>&nbsp;&nbsp;</div>
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='hdnr' styleId='hdnr' style="width:99%"
								rows='5' />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
				<logic:notEqual value="view" name="act">
					<button class="button2" onclick="addSave('xh-xn-xq-hdxz-hdxm')"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:notEqual>	
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
					
				</div>
			</logic:notEmpty>
			<logic:equal value="true" name="done">
			  <script type="text/javascript">
				   alert("操作成功！");
				   Close();
				   dialogArgumentsQueryChick();
			  </script>
		   </logic:equal>
		   <logic:equal value="false" name="done">
			   <script type="text/javascript">
				   alert("操作失败,或系统中已存在与带\"*\"号项目相同的记录，请检查输入的数据后再提交！");
			   </script>
		   </logic:equal>
		</html:form>
		<script type="text/javascript">
		 function addSave(mustFill){	           
	          var eles = mustFill.split("-");
	          for (i = 0; i < eles.length; i++) {
		           if (document.getElementById(eles[i]).value == "") {
			           alert("请将带\"*\"号的项目输入完整！");
			           return false;
		           }		
	          }
	          if($("shry").value.length>300){
	              alert("荣誉称号字数不能超过300字！");
	              return false;
	          }
	          if($("hdxm").value.length>50){
	              alert("活动项目字数不能超过50字！");
	              return false;
	          }
	          if($("hdnr").value.length>300){
	              alert("活动内容字数不能超过300字！");
	              return false;
	          }
             
<%--	          var pkValue=$("lddm").value+lz;--%>
<%--	          getSztzData.getInfoEx("lzxxb","lddm||lz",pkValue,"sfzz='是'",function(str){--%>
<%--		         if(str){		         	--%>
<%--		            alert("该生目前正任职该楼楼长，不能重复添加！");		           		          			        --%>
<%--		         }else{--%>
              refreshForm("/xgxt/nbcsShgzgl.do?method=shsjAdd&doType=save");                
<%--		         }--%>
<%--    	      });	 	           --%>
	     }
		</script>
	</body>
</html>
