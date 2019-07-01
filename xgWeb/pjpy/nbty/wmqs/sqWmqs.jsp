
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

		<meta name="Copyright" content="������� zfsoft" />
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
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>	
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/nbtyWmqssq.js"></script>
	<script language="javascript">	
	var lddm="";
	var zydm="";
	var xydm="";
	var bjdm="";
 	function getXyBjMc(){
	 	var qsh="";
	 	var lddm="";
	 	if($("qsh").value!="" && $("lddm").value!=""){
	 	qsh=$("qsh").value;
	 	lddm=$("lddm").value;
	 	nbtyWmqssq.getZsxxList(lddm,qsh,getData);
	 	}
	 	else
	 	{
	 	$("xydm").value="";
	 	$("bjdm").value="";
	 	$("zydm").value="";
	 	$("xymc").value="";
	 	$("bjmc").value="";
	 	$("zymc").value="";
	 	}
	 } 
	 function rychSqSave(){
	 	 if($("lddm").value=="" || $("lddm").value==null){
	 		alert("�Բ�������ѡ��¥����");
	 		return false;
	 	}	
	 	if($("qsh").value=="" || $("qsh").value==null){
	 		alert("�Բ�������ѡ�����ң�");
	 		return false;
	 	}	
        refreshForm("/xgxt/nbtyWmqs.do?method=sqWmqs&doType=save");
        if($("buttonSave")){
          $("buttonSave").disabled=true;
        }
     }
    function rychSqPrint(){
        window.open('nbtyWmqs.do?method=printWmqs&pkValue=${pkValue}');
        }	
</script>
	<%--		<input type="hidden" id="printpk" value="${printpk }"/>--%>
	<body>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
		   <script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
		   <html:form action="/nbtyWmqs" method="post">
		   	<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/nbtyJtjjkns.do?method=jtjjknsSq&doType=view" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="pkValue" id="pkValue" value="${pk}"/>
			<input type="hidden" name="save_sqsj" id="save_sqsj" value="${sqsj}"/>
			<html:hidden property="save_zydm" styleId="zydm" value="${map.zydm}"/>
			<html:hidden property="save_xydm" styleId="xydm" value="${map.xydm}"/>
			<html:hidden property="save_bjdm" styleId="bjdm" value="${map.bjdm}"/>
			<html:hidden property="haveQs" styleId="haveQs" value="${haveQs}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã��������� - �����ƺ����� - ������������
				</div>
			</div>

			<logic:present name="result">
			<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
			</logic:present>

			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>��������</b>
						</td>
					</tr>
				</thead>
				<tr>
				<td align="right" style="width: 10%">
						<font color="red">*</font>ѧ�꣺
					</td>
					<td>
					<bean:write name="xn"/>
					<html:hidden property="save_xn" value="${xn}"/>
					</td>
					
					<td align="right" style="width: 10%">
						<font color="red">*</font>¥�����ƣ�
					</td>
					<td align="left" style="width: 40%">
					<logic:notEqual name="userOnLine" value="student">
					<html:select  property="save_lddm" style="width:150px" styleId="lddm"  onchange="GetQshList()">	
							<html:option value=""></html:option>							
							<html:options collection="ldlbList" property="dm" labelProperty="mc" />
						</html:select>
					</logic:notEqual>
					<logic:equal name="userOnLine" value="student">
						<bean:write name="map" property="ldmc"/>
						<html:hidden property="save_lddm" styleId="lddm" value="${map.lddm}"/>
					</logic:equal>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<font color="red">*</font>���Һţ�
					</td>
					<td align="left" style="width: 40%">
					<logic:notEqual name="userOnLine" value="student">
						<html:select property="save_qsh" style="width:110px" styleId="qsh" onchange="getXyBjMc()">
									<logic:empty name="rs" >
										<html:option value=""></html:option>
									</logic:empty>
									<logic:notEmpty name="rs" >
										<html:option value="${rs.qsh}"></html:option>
									</logic:notEmpty>
						</html:select>
					</logic:notEqual>
					<logic:equal name="userOnLine" value="student">
						<bean:write name="map" property="qsh"/>
						<html:hidden property="save_qsh" styleId="qsh" value="${map.qsh}"/>
					</logic:equal>
					</td>
					<td align="right">
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<logic:notEmpty name="map">
							<html:text name="map" readonly="true" property="xymc"  styleId="xymc" />
							<html:hidden property="save_xymc" styleId="qsh" value="${map.xymc}"/>
						</logic:notEmpty>
						<logic:empty name="map">
							<html:text  property="save_xymc" readonly="true"  styleId="xymc" />
						</logic:empty>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<font color="red">*</font>רҵ��
					</td>
					<td align="left">
						<logic:notEmpty name="map">
							<html:text name="map" property="zymc" readonly="true"  styleId="zymc"/>
							<html:hidden property="save_zymc" styleId="zymc" value="${map.zymc}"/>
						</logic:notEmpty>
						<logic:empty name="map">
						<html:text property="save_zymc" readonly="true"  styleId="zymc"/>
						</logic:empty>
					</td>
					<td align="right">
						<font color="red">*</font>�༶��
					</td>
					<td align="left">
						<logic:notEmpty name="map">
							<html:text name="map" property="bjmc" readonly="true"  styleId="bjmc"/>
							<html:hidden property="save_bjmc" styleId="bjmc" value="${map.bjmc}"/>
						</logic:notEmpty>
						<logic:empty name="map">
						<html:text property="save_bjmc" readonly="true"  styleId="bjmc"/>
						</logic:empty>
					</td>		
				</tr>
				<tr style="height:22px">
					<td align="right">
						��Ҫ�¼���
						<br />
						<span class="style1">(��400��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" styleId="bz" property="save_zysj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��ע��
						<br />
						<span class="style1">(��400��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" styleId="bz" property="save_bz" onblur="chLeng(this,400)"/>
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn"  width="100%">	
				<logic:equal name="writeAbled" value="yes">
					<button type="button" class="button2" id="buttonSave" onclick="rychSqSave();" style="width:80px">
						��  �� 
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:equal>
					<button type="button" class="button2" onclick="rychSqPrint()" style="width:80px">
						��  ӡ
					</button>
			</div>
			<logic:equal name="done" value="true">
				<script>
			          alert("����ɹ���");
			        </script>
			</logic:equal>
			<!-- ������ʦ�Ĺ�Ͻ��Χ -->
			<logic:equal name="notU" value="yes">
				<script>
			          alert("�Բ���������ҵ�ѧ���������Ĺ�Ͻ��Χ��");
			    </script>
			</logic:equal>
			<!-- ����ʦ�Ĺ�Ͻ��Χ -->
			<logic:notEqual name="notU" value="yes">
			<logic:equal name="haveQs" value="no">
				<logic:equal name="userOnLine"  value="student" >
				 <script>
			          alert("�Բ�������û�з������ң��������룡");
			     </script>
			     </logic:equal>
			     <logic:notEqual name="userOnLine"  value="student" >
				 <script>
			          alert("�Բ��𣬸�����û����ס��Ա���������룡");
			     </script>
			     </logic:notEqual>
			</logic:equal>
			</logic:notEqual>
			<logic:equal name="done" value="false">
				<script>
			          alert("����ʧ�ܣ�");
			        </script>
			</logic:equal>
		</html:form>
		<logic:present name="result">
		<input type="hidden" id="message" value="${message}"/>
		</logic:present>
		<script type="text/javascript">
	    function getData(data)
		{
	 	$("xymc").value="";
	 	$("bjmc").value="";
	 	$("zymc").value="";
	 	$("xydm").value="";
	 	$("bjdm").value="";
	 	$("zydm").value="";
	 	var zydm="";
		var xydm="";
		var bjdm="";
	 	if(null!=data.xymc)
	 	$("xymc").value=data.xymc;
	 	if(null!=data.zymc)
	 	$("zymc").value=data.zymc;
	 	if(null!=data.bjmc)
	 	$("bjmc").value=data.bjmc;
	 	if(null!=data.xydm)
	 	xydm=data.xydm;
	 	if(null!=data.zydm)
	 	zydm=data.zydm;
	 	if(null!=data.bjdm)
	 	bjdm=data.bjdm;
	 	$("xydm").value=xydm;
	 	$("bjdm").value=bjdm;
	 	$("zydm").value=zydm;
	 	
	 }
		</script>
	</body>


</html>

