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
		<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="javascript">	     
     function rychSqSave(){
        var xh = "";
        var rychdm = "";
        var bz  = "";
        if($("xh")){
           xh = $("xh").value;
        }
        if($("rychdm")){
           rychdm = $("rychdm").value;
        }
        if($("bz")){
          bz = $("bz").value;
        }
        if(xh==""){
          alert("学号不能为空！");
          return false;
        }
        if(rychdm==""){
          alert("荣誉称号不能为空！");
          return false;
        }  
        if(bz.length>1000){
          alert("备注字数过大，限1000字内！");
          return false;
        }      
        refreshForm("/xgxt/zjcm_rych.do?method=rychSq&doType=save");
        if($("buttonSave")){
          $("buttonSave").disabled=true;
        }
     }
     function rychBbPrint(){
         var rychdm ="";
         if($("rychdm")){rychdm = $("rychdm").value;}
         var xh     = "";
         if($("xh")){xh = $("xh").value;}
         var xn  = "";
         if($("xn")){ xn = $("xn").value;}
         if(rychdm==""){
            alert("请选择荣誉称号！");
            return false;
         }
         var url = "/xgxt/zjcm_rych.do?method=rychDjb";
             url +="&xn="+xn;
         //"/xgxt/zjcm_rych.do?method=rychDjb&rychdm="+rychdm;
            // url += "&xh="+xh;
             
             //url +="&bz="+bz;
        document.forms[0].action = url;
	    document.forms[0].target = "_blank";
	    document.forms[0].submit();
	    document.forms[0].target = "_self";
     }
</script>
	<%--		<input type="hidden" id="printpk" value="${printpk }"/>--%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
		<html:form action="/zjcm_rych" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：评奖评优 - 荣誉称号申请 - 填写申请表
				</div>
			</div>

			<logic:equal name="rs" property="stuExists" value="no">
				<script>
			    alert("您输入的学号无效!");
			    </script>
			</logic:equal>
			<input type="hidden" id="disableEle" name="disableEle"
				value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url"
				value="/zjcm_rych.do?method=rychSq" />
			<input type="hidden" id="pm" name="pm"
				value="<bean:write name='rsCj' property="zhpm" />" />
			<input type="hidden" id="stab" name="stab" value="stab" />
			<%--					<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }" />--%>
			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>填写申请表</b>
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right" style="width: 10%">
						<font color="red">*</font>学号：
					</td>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="left">
							<html:text name='rs' property="xh" styleId="xh"
								onblur="dctStuXh()"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="left">
							<html:hidden name='rs' property="xh" styleId="xh" />
							<bean:write name='rs' property="xh" />
						</td>
					</logic:equal>

					<td align="right" style="width: 10%">
						学年：
					</td>
					<td align="left" style="width: 40%">
						<html:select name="rsOther" property="xn" disabled="true">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						姓名：
					</td>
					<td align="left">
						<bean:write name='rs' property="xm" />
					</td>
					<td align="right">
						学期：
					</td>
					<td align="left">
						<html:select name="rsOther" property="xq" disabled="true">
							<html:options collection="xqList" property="xqdm"
								labelProperty="xqmc" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						性别：
					</td>
					<td align="left">
						<bean:write name='rs' property="xb" />
					</td>
					<td align="right">
						<font color="red">*</font>荣誉称号：
					</td>
					<td align="left">
						<html:select property="rychdm" styleId="rychdm">
							<option value=""></option>
							<html:options collection="rychList" property="dm"
								labelProperty="mc" />
						</html:select>
					</td>		
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<bean:write name='rs' property="xymc" />
					</td>
					<td align="right">
						担任职务：
					</td>
					<td align="left">
						<bean:write name='rs' property="zw" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						专业：
					</td>
					<td align="left">
						<bean:write name='rs' property="zymc" />
					</td>
					<td align="right">
						政治面貌：
					</td>
					<td align="left">
						${rs.zzmmmc }
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						班级：
					</td>
					<td align="left">
						<bean:write name='rs' property="bjmc" />
					</td>
					<td align="right">
						早锻炼出勤率：
					</td>
					<td align="left">
						<html:text property="zdlcql" maxlength="10"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						综测排名：
					</td>
					<td align="left">
						<bean:write name='rsCj' property="zhpm" />/<bean:write name='rsOther' property="bjrs" />
					</td>
					<td align="right">
						综测分：
					</td>
					<td align="left">
						<bean:write name='rsCj' property="zhf" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						智育排名：
					</td>
					<td align="left">
						<bean:write name='rsCj' property="zyfpm" />/<bean:write name='rsOther' property="bjrs" />
					</td>
					<td align="right">
						智育分：
					</td>
					<td align="left">
						<bean:write name='rsCj' property="zyf" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						德育排名：
					</td>
					<td align="left">
						<bean:write name='rsCj' property="dyfpm" />/<bean:write name='rsOther' property="bjrs" />
					</td>
					<td align="right">
						德育分：
					</td>
					<td align="left">
						<bean:write name='rsCj' property="dyf" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						主要事迹：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" property="zysj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						备注：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" property="bz" onblur="chLeng(this,400)"/>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" id="buttonSave" onclick="if($('pm').value==''){alert('该生没有综合成绩和排名，不能申请')}else{rychSqSave();}">
					提  交 
				</button>
				&nbsp;&nbsp;
				<button type="button" class="button2" id="buttonSave" onclick="rychBbPrint()">
					报  表
				</button>
				&nbsp;&nbsp;
				<button type="button" id="btn_cj" class="button2" onclick="showTopWin('ahjg_xscjb.do?xh='+document.getElementById('xh').value,'500','400')">
							学 生 成 绩
				</button>
				&nbsp;&nbsp;
			</div>
			<logic:equal name="done" value="true">
				<script>
			          alert("申请成功！");
			        </script>
			</logic:equal>
			<logic:equal name="done" value="false">
				<script>
			          alert("申请失败！");
			        </script>
			</logic:equal>
			<logic:equal name="isExist" value="no">
				<script>
			        alert("该荣誉称号已申请,且已通过相关部门审核\n或正在审核中,不能再次申请！");			    
			        </script>
			</logic:equal>
			<logic:equal name="pass" value="no">
				<script>
			        alert("该生不满足该项荣誉称号申请条件！");			    
			        </script>
			</logic:equal>
		</html:form>
	</body>


</html>
