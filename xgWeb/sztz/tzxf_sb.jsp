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
	<base target="_self">
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
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript">
	function tzxmInfoTo(){
    if($('xh').value==""){
        alert("学号不能为空！");
        return false;
    }else{
        var url = "/xgxt/sztz_xm_query.do?xh="+$('xh').value+"&url="+$('url').value;        
        showTopWin(url,750,550);            
    }
    }
    function xfsbSave(){
    	 var elements = new Array();
	     elements = $("xn").value.split("-");
	     var nd_val = $("nd").value;
	     if (!inArray(nd_val, elements)) {
			alert("学年、年度不一致,请检查您要保存的数据！");
			return false;
		 }      
         SztzDataDoSaveApply('/xgxt/sztz_applySave.do','xn-nd-xq-xmdm','sztz_xfsbb','xh-nd-xn-xq-xmdm-lydm');      
    }
    
    </script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/sztz_xf_sb" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：素质拓展 - 拓展学分个人申报 - 填写申报表
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:equal value="1" name="sqsjTag">
				<br />
				<br />
				<p align="center">
					<font color="red">不在设定时间范围内,暂不开放申报!</font>
				</p>
			</logic:equal>
			<logic:notEqual value="1" name="sqsjTag">
				<logic:notEmpty name="rs">
					<logic:equal name="rs" property="stuExists" value="no">
						<script>
    					alert("您输入的学号无效!");
    				</script>
					</logic:equal>
					<logic:equal name="dataSaved" value="ok" scope="request">
						<script>
    					alert("保存成功！");
    				</script>
					</logic:equal>
					<input type="hidden" id="disableEle" name="disableEle"
						value="xm-xb-xy-nj-zy-bj" />
					<input type="hidden" id="getStuInfo" name="getStuInfo"
						value="xm-xb-xymc-nj-zymc-bjmc" />
					<input type="hidden" id="url" name="url" value="/sztz_xf_sb.do" />
					<fieldset>
						<legend>
							填写申报表
						</legend>
						<table width="100%" id="rsT" class="tbstyle">
							<thead>
								<tr style="height:22px">
									<td colspan="4" align="center">
										<b>填写申报表</b>
									</td>
								</tr>
							</thead>
							<tr style="height:22px">
								<logic:equal name="userOnLine" value="teacher" scope="session">
									<td align="right" style="width: 5%">
										<font color="red">*</font>学号：
									</td>
									<td align="left" style="width: 20%">
										<html:text name='rs' property="xh" styleId="xh"
											readonly="true"
											onkeypress="if(event.keyCode == 13) return false;" />
										<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
											class="btn_01" id="buttonFindStu">
											选择
										</button>
									</td>
								</logic:equal>
								<logic:equal name="userOnLine" value="student" scope="session">
									<td align="right" style="width: 8%">
										<font color="red">*</font>学号：
									</td>
									<td align="left" style="width: 27%">
										<input type="text" id="xh" name="xh"
											value="<bean:write name='rs' property="xh" />"
											readonly="readonly" />
									</td>
								</logic:equal>
								<td align="right">
									<font color="red">*</font>拓展活动(项目)：
								</td>
								<td align="left">
									<input type="hidden" name="xmdm"
										value="<bean:write name="xmdm" scope="request"/>" id="xmdm">
									<html:text name='rs' property="xmmc" styleId="xmmc"
										readonly="true" style="width:250px" />
									<button onclick="tzxmInfoTo()" class="button2">
										选择
									</button>
								</td>
								<%--								<td align="right" style="width: 10%">--%>
								<%--									<font color="red">*</font>年度：--%>
<%--								</td>--%>
<%--								<td align="left" style="width: 20%">--%>
<%--									<html:select property="nd"--%>
<%--										style="width:90px;background-color:#FFFFFF" styleId="nd">--%>
<%--										<html:options collection="xnList" property="nd"--%>
<%--											labelProperty="nd" />--%>
<%--									</html:select>--%>
<%--								</td>--%>
							</tr>
							<tr style="height:22px">
								<td align="right">
									姓名：
								</td>
								<td align="left">
									<bean:write name='rs' property="xm" />
								</td>
								<td align="right">
									<font color="red">*</font>学年：
								</td>
								<td align="left">
									<html:text name="rs" property="xn" readonly="true"  styleId="xn"></html:text>
								</td>
								<%--								<td align="right">--%>
<%--									<font color="red">*</font>学年：--%>
<%--								</td>--%>
<%--								<td align="left">--%>
<%--									<html:select property="xn"--%>
<%--										onchange="refreshForm('/xgxt/sztz_xf_sb.do')"--%>
<%--										style="width:90px;background-color:#FFFFFF" styleId="xn">--%>
<%--										<html:options collection="xnList" property="xn"--%>
<%--											labelProperty="xn" />--%>
<%--									</html:select>--%>
<%--								</td>--%>
							</tr>
							<tr style="height:22px">
								<td align="right">
									性别：
								</td>
								<td align="left">
									<bean:write name='rs' property="xb" />
								</td>
								<td align="right">
									<font color="red">*</font>学期：
								</td>
								<td align="left">
									<html:text name="rs" property="xqmc" readonly="true" styleId="xqmc"></html:text>									
                                    <input type="hidden" id="xq" name="xq" value="${rs.xq}" />
								</td>								
<%--								<td align="right">--%>
<%--									<font color="red">*</font>学期：--%>
<%--								</td>--%>
<%--								<td align="left">--%>
<%--									<html:select property="xq"--%>
<%--										onchange="refreshForm('/xgxt/sztz_xf_sb.do')"--%>
<%--										style="width:90px;background-color:#FFFFFF" styleId="xq">--%>
<%--										<html:option value=""></html:option>--%>
<%--										<html:options collection="xqList" property="xqdm"--%>
<%--											labelProperty="xqmc" />--%>
<%--									</html:select>--%>
<%--								</td>--%>
							</tr>
							<tr style="height:22px">
								<td align="right">
									年级：
								</td>
								<td align="left">
									<input type="hidden" id="xsnj" name="xsnj"
										value="<bean:write name='rs' property="nj" />" />
									<bean:write name='rs' property="nj" />
								</td>
								<td align="right" style="width: 10%">
									<font color="red">*</font>年度：
								</td>
								<td align="left" style="width: 20%">
									<html:select property="nd"
										style="width:90px;background-color:#FFFFFF" styleId="nd">
										<html:options collection="xnList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
								<%--								<td align="right">--%>
<%--									<font color="red">*</font>拓展活动(项目)：--%>
<%--								</td>--%>
<%--								<td align="left">--%>
<%--									<html:select property="xmdm" styleId="xmdm"--%>
<%--										onchange="refreshForm('/xgxt/sztz_xf_sb.do')"--%>
<%--										style="background-color:#FFFFFF">--%>
<%--										<html:option value=""></html:option>--%>
<%--										<html:options collection="tzxmList" property="xmdm"--%>
<%--											labelProperty="xmmc" />--%>
<%--									</html:select>--%>
<%--							 <input type="hidden" name="xmdm" value="<bean:write name="xmdm" scope="request"/>">--%>
<%--						     <html:text name='rs' property="xmmc" styleId="xmmc" readonly="true" style="width:250px"/>--%>
<%--							  <button onclick="tzxmInfoTo()"class="button2" >--%>
<%--										选择--%>
<%--							  </button>--%>
<%--								</td>--%>
							</tr>
							<tr style="height:22px">
								<td align="right">
									<bean:message key="lable.xsgzyxpzxy" />：
								</td>
								<td align="left">
									<bean:write name='rs' property="xymc" />
								</td>
								<td align="right">
									所属科目：
								</td>
								<td align="left">
									<html:text name="rs" property="kmm" readonly="true"
										style="cursor:hand" />
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
									<font color="red">*</font>申报理由：
								</td>
								<td>
									<html:select property="lydm" styleId="lydm"
										onchange="refreshForm('/xgxt/sztz_xf_sb.do')"
										style="background-color:#FFFFFF">
										<html:option value=""></html:option>
										<html:options collection="lyList" property="lydm"
											labelProperty="lymc" />
									</html:select>
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
									拓展活动获奖类别：
								</td>
								<td align="left">
									<html:select property="jbdm"
										style="width:90px;background-color:#FFFFFF" styleId="jbdm"
										onchange="refreshForm('/xgxt/sztz_xf_sb.do')">
										<html:option value=""></html:option>
										<html:options collection="hjjbList" property="jbdm"
											labelProperty="jbmc" />
									</html:select>
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									活动级别：
								</td>
								<td align="left">
									<html:select property="cjtzxmjb"
										style="width:90px;background-color:#FFFFFF" styleId="cjtzxmjb">
										<html:option value=""></html:option>
										<html:options collection="cjxmjbList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<td align="right">
									所获学分：
								</td>
								<td align="left">
									<html:text name="rs" property="xf" readonly="true"
										styleId="pxjssj" onkeypress="return sztzNumInputValue(this,4,event)"
										onblur="chkInput(this,event)" maxlength="4" style="width:90px;" />
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									参加性质：
								</td>
								<td align="left" colspan="3" width="90%">
									<html:select property="tzxmcjsf"
										style="width:90px;background-color:#FFFFFF" styleId="tzxmcjsf">
										<html:option value=""></html:option>
										<html:options collection="tzxmcjsfList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									理由：
								</td>
								<td colspan="3">
									<bean:write name="rs" property="lynr" />
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									成果描述：
								</td>
								<td colspan="3">
									<html:textarea property='cgms' styleId="cgms" style="width:99%"
										rows='5' />
								</td>
							</tr>
						</table>
						<div class="buttontool" align="center">
							<button class="button2" id="buttonSave"
								onclick="xfsbSave()">
								提 交 申 请
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2"
								onclick="expAppTab('rsT','素质拓展学分个人申报表','')">
								打 印 报 表
							</button>
						</div>
					</fieldset>
				</logic:notEmpty>
				<logic:present name="isPASS">
					<logic:match value="is" name="isPASS">
						<script>
    					alert("该年度、学年、学期该拓展项目成绩申报\n正在审核中或已通过，不能再申报！");
    				</script>
					</logic:match>
				</logic:present>
			</logic:notEqual>
		</html:form>
	</body>
</html>
