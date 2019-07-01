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
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">

	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script type="text/javascript"
		src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script>
		function change(){
			var num=document.getElementById("num").value;
			for(var i=1;i<(num*1+1);i++){
				document.getElementById("jtcy"+i).style.display='';
			}
			for(var j=1;j<11-num;j++){							
				document.getElementById("jtcy"+(num*1+j)).style.display='none';
			}
		}
		
		function initPage(){
				document.getElementById("num").value=1;
				change();
		}
		
		function checkPage(){
			var ssbh = document.getElementById("ssbh").value;
			var qsdh = document.getElementById("qsdh").value;
			var flag = false;
			getStuDetails.checkQsdhAndQsbh(ssbh,qsdh,function(data){
				if(data==""){
					flag = true;
					refreshForm('stu_info_add.do?method=modiStuInfo&act=save')
				}else{
					alert(data+",请核对准确！");
					flag =  false;
				}
			});			
			return flag;
		}
		
	function loadShi(shen,shi,xian){
		var shen = document.getElementById(shen).value;	
		getStuDetails.getShiList(shen,function(data){
			if (data.shiList != null) {
					var objId = shi;				
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.shiList,"shidm","shimc");
					}
				}else{
					showMsgWin("有错误出现：远程数据读取失败！");
				}	
			if (data.xianList !=null){
				var objId = xian;
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.xianList,"xiandm","xianmc");
					}
			}
		});
	}

	function loadXian(shi,xian){
		var shi = document.getElementById(shi).value;	
		getStuDetails.getXianList(shi,function(data){
			if (data != null) {
					var objId = xian;
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);							
						DWRUtil.addOptions(objId,data,"xiandm","xianmc");
					}
				}else{
					showMsgWin("有错误出现：远程数据读取失败！");
				}		
		});
	}
	function loadInit(){
		var xxdm = document.getElementById("xxdm").value;
		if(xxdm == "10690"){
			var sfdkVal = document.getElementById("sfdkVal").value;
			if(sfdkVal =="" || sfdkVal==null){
				document.getElementById("sfdkf").checked=true;
			}
		}
	
	}
	
	function saveInfo(){
		var xxdm = document.getElementById("xxdm").value;
		document.getElementById("jg").value="";
		document.getElementById("syd").value="";
		if(xxdm == "10690"){
			var jgs = document.getElementById("jgshen").value;
			var jgshi = document.getElementById("jgshi").value;
			var jgx = document.getElementById("jgxian").value;
			var syds = document.getElementById("sydshen").value;
			var sydshi = document.getElementById("sydshi").value;
			var sydx = document.getElementById("sydxian").value;
			var jg = document.getElementById("jg").value;
			var syd = "";
			if(jgs!="" && jgs!=null){
				jg += document.getElementById("jgshen").options[document.getElementById("jgshen").selectedIndex].text;
			}			
			if(jgshi!="" && jgshi!=null){
				jg += document.getElementById("jgshi").options[document.getElementById("jgshi").selectedIndex].text;
			}
			if(jgx!="" && jgx!=null){
				jg += document.getElementById("jgxian").options[document.getElementById("jgxian").selectedIndex].text;
			}
			
			if(syds!="" && syds!=null){
				syd += document.getElementById("sydshen").options[document.getElementById("sydshen").selectedIndex].text;
			}			
			if(sydshi!="" && sydshi!=null){
				syd += document.getElementById("sydshi").options[document.getElementById("sydshi").selectedIndex].text;
			}
			if(sydx!="" && sydx!=null){
				syd += document.getElementById("sydxian").options[document.getElementById("sydxian").selectedIndex].text;
			}
			document.getElementById("jg").value=jg;
			document.getElementById("syd").value=syd;
		}		
		refreshForm('stu_info_add.do?method=modiStuInfo&act=save');
	}
	</script>
	<body onload="loadInit();">
		<html:form action="/stu_info_add.do" method="post">
			<input type="hidden" id="url" name="url"
				value="/sjcz/modi_stu_info.jsp" />
			<input type="hidden" id="disableEle" name="disableEle"
				value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-nj-zymc-bjmc-xh" />
			<input type="hidden" id="sfdkVal" value="${rs.sfdk}" />
			<input type="hidden" id="xxdm" value="<bean:write name="xxdm"/>" />
			<html:hidden property="jg" styleId="jg" />
			<html:hidden property="syd" styleId="syd" />

			<div class="title">
				<logic:equal name="userOnLine" value="student" scope="session">
					<div class="title_img" id="title_m">
						当前位置：学生信息 - 信息修改 - 修改个人信息
					</div>
				</logic:equal>
				<logic:equal name="userOnLine" value="teacher" scope="session">
					<div class="title_img" id="title_m">
						当前位置：学生信息 - 信息修改 - 修改学生家庭信息
					</div>
				</logic:equal>
			</div>

			<logic:equal name="userType" value="admin" scope="session">
				<br />
				<br />
				<br />
				<p align="center">
					本页面只有学生和<bean:message key="lable.xsgzyxpzxy" />用户可以访问
				</p>
			</logic:equal>

			<logic:equal name="userType" value="xx" scope="session">
				<br />
				<br />
				<br />
				<p align="center">
					本页面只有学生和<bean:message key="lable.xsgzyxpzxy" />用户可以访问
				</p>
			</logic:equal>

			<logic:equal name="sqsjFlag" value="1">
				<script>
		   			 alert("不在设定时间范围内,暂不开放该功能!");
		    		 location.href="about:blank";
   			 	</script>
			</logic:equal>
			<logic:equal name="userOnLine" value="student" scope="session">
				<logic:present name="rs">
					<logic:equal name="dataSaved" value="ok" scope="request">
						<script>
    						alert("保存成功！");
    					</script>
					</logic:equal>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td colspan="4" align="center">
									学生基本信息
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right">
								学号：
							</td>
							<td align="left">
								<bean:write name="userName" scope="session" />
							</td>
							<td align="right">
								姓名：
							</td>
							<td align="left">
								<bean:write name="rs" property="xm" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</td>
							<td align="left">
								<bean:write name="rs" property="xymc" />
							</td>
							<td align="right">
								专业：
							</td>
							<td align="left">
								<bean:write name="rs" property="zymc" />
							</td>
						</tr>
						<tr>
							<td align="right">
								年级：
							</td>
							<td align="left">
								<bean:write name="rs" property="nj" />
							</td>
							<td align="right">
								班级：
							</td>
							<td align="left">
								<bean:write name="rs" property="bjmc" />
							</td>
						</tr>
						<tr>
							<td align="right">
								姓名拼音：
							</td>
							<td align="left">
								<html:text name="rs" property="xmpy" maxlength="64" />
							</td>
							<td align="right">
								身高：
							</td>
							<td align="left">
								<html:text name="rs" property="sg"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3" />
								(cm)
							</td>
						</tr>
						<tr>
							<td align="right">
								曾用名：
							</td>
							<td align="left">
								<html:text name="rs" property="cym" maxlength="16" />
							</td>
							<td align="right">
								体重：
							</td>
							<td align="left">
								<html:text name="rs" property="tz"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3" />
								(kg)
							</td>

						</tr>
						<tr>
							<td align="right">
								出生日期：
							</td>
							<td align="left">
								<html:text name="rs" property="csrq"
									onclick="return showCalendar('csrq','y-mm-dd');" styleId="csrq"
									readonly="true" />
							</td>
							<td align="right">
								特长：
							</td>
							<td align="left">
								<html:text name="rs" property="tc" maxlength="32" />
							</td>
						</tr>
						<tr>
							<td align="right">
								身份证号：
							</td>
							<td>
								<html:text name="rs" property="sfzh" styleId="sfzh"
									maxlength="20" />
							</td>
							<td align="right">
								培养层次：
							</td>
							<td align="left" colspan="2">
								<html:text name="rs" property="pycc" maxlength="32" />
							</td>
						</tr>

						<tr>
							<td align="right">
								政治面貌：
							</td>
							<td>
								<html:select name="rs" property="zzmm" styleId="zzmm"
									style="width:150px">
									<html:options collection="zzmmList" property="zzmmdm"
										labelProperty="zzmmmc" />
								</html:select>
							</td>
							<td align="right">
								民族：
							</td>
							<td>
								<html:select name="rs" property="mz" styleId="mz"
									style="width:150px">
									<html:options collection="mzList" property="mzdm"
										labelProperty="mzmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								籍贯：
							</td>
							<td colspan="3">
								<html:select name="rs" property="jgs" onchange="loadShi('jgshen','jgshi','jgxian')"
									styleId="jgshen">
									<html:option value="">--请选择--</html:option>
									<html:options collection="ssList" property="ssdm"
										labelProperty="ssmc" />
								</html:select>
								<html:select name="rs" property="jgshi" styleId="jgshi"
									onchange="loadXian('jgshi','jgxian')">
									<html:options collection="shiList" property="shidm"
										labelProperty="shimc" />
								</html:select>
								<html:select name="rs" property="jgx" styleId="jgxian">
									<html:options collection="xianList" labelProperty="xianmc"
										property="xiandm" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								生源地区：
							</td>
							<td colspan="3">
								<html:select name="rs" property="syds" onchange="loadShi('sydshen','sydshi','sydxian')"
									styleId="sydshen">
									<html:option value="">--请选择--</html:option>
									<html:options collection="ssList" property="ssdm"
										labelProperty="ssmc" />
								</html:select>
								<html:select name="rs" property="sydshi" styleId="sydshi"
									onchange="loadXian('sydshi','sydxian')">
									<html:options collection="shiList" property="shidm"
										labelProperty="shimc" />
								</html:select>
								<html:select name="rs" property="sydx" styleId="sydxian">
									<html:options collection="xianList" labelProperty="xianmc"
										property="xiandm" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								职务：
							</td>
							<td>
								<html:text name="rs" property="zw" maxlength="32" />
							</td>
							<td align="right">
								学习方向：
							</td>
							<td colspan="2">
								<html:text name="rs" property="xxfx" maxlength="32" />
							</td>
						</tr>
						<tr>
							<td align="right">
								入学时间：
							</td>
							<td align="left">
								<html:text name="rs" property="rxrq"
									onclick="return showCalendar('rxrq','y-mm-dd');" styleId="rxrq"
									maxlength="10" />
							</td>
							<td align="right">
								毕业时间：
							</td>
							<td align="left">
								<html:text name="rs" property="byny"
									onclick="return showCalendar('byny','y-mm-dd');" styleId="byny"
									maxlength="10" />
							</td>
						<tr>
							<td align="right">
								QQ号码：
							</td>
							<td>
								<html:text name="rs" property="qqhm" maxlength="13"
									onkeyup="value=value.replace(/[^\d]/g,'') " />
							</td>
							<td align="right">
								学制：
							</td>
							<td>
								<html:text name="rs" property="xz" />
							</td>
						</tr>						
						<tr>
							<td align="right">
								联系电话：
							</td>
							<td align="left">
								<input type="text" name="lxdh" id="lxdh"
									value="<bean:write name="rs" property="lxdh"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
							</td>
							<td align="right">
								手机号码：
							</td>
							<td align="left">
								<input type="text" name="sjhm" id="sjhm"
									value="<bean:write name="rs" property="sjhm"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="11" />
							</td>
						</tr>
						<tr>
							<td align="right">
								E-Mail：
							</td>
							<td align="left">
								<input type="text" name="dzyx" id="dzyx"
									value="<bean:write name="rs" property="dzyx"/>" />
							</td>
							<td align="right">
								家庭所在地：
							</td>
							<td align="left">
								<input type="text" name="jtszd" id="jtszd"
									value="<bean:write name="rs" property="jtszd"/>" maxlength="25" />
							</td>
						</tr>
						<tr>
							<td align="right">
								家庭年收入：
							</td>
							<td>
								<html:text name="rs" property="jtzsr"
									onkeyup="value=value.replace(/[^\d]/g,'') " styleId="jtzsr" />
								元/年
							</td>
							<td align="right">
								是否需要助学贷款：
							</td>
							<td>
								<html:radio name="rs" property="sfdk" value="是" styleId="sfdks">是</html:radio>
								<html:radio name="rs" property="sfdk" value="否" styleId="sfdkf">否</html:radio>
							</td>
						</tr>
						<tr height="80">
							<td align="right">
								校外住宿详细地址：
							</td>
							<td align="left" colspan="4">
								<html:textarea name="rs" property="xwzsxxdz" styleId="xwzsxxdz"
									style="width:100%;height:100%"></html:textarea>
							</td>
						</tr>
						<tr height="22">
							<td align="right">
								宿舍号：
							</td>
							<td align="left">
								<html:text property="ssbh" name="rs" styleId="ssbh" />
							</td>
							<td align="right">
								宿舍床号：
							</td>
							<td align="left">
								<html:text name="rs" property="ssch" styleId="ssch"/>
							</td>
						</tr>
						<tr height="22">
							<td align="right">
								住宿日期：
							</td>
							<td align="left" valign="middle">
								<html:text name="rs" property="zsrq" styleId="zsrq"
									readonly="true"
									onclick="return showCalendar('zsrq','y-mm-dd');" />
							</td>
							<td align="right">
								住宿截止日期：
							</td>
							<td align="left">
								<html:text name="rs" property="zsjzrq" styleId="zsjzrq"
									readonly="true"
									onclick="return showCalendar('zsjzrq','y-mm-dd');" />
							</td>
						</tr>
						<%--家庭信息--%>
						<thead>
							<tr>
								<td colspan="4" style="cursor:hand" align="center"
									onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
									学生家庭成员信息1
								</td>
							</tr>
						</thead>
						<tr id="jt1" style="display:none">
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<tr>
										<td align="right">
											姓名：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_xm"
												id="jtcy1_xm"
												value="<bean:write name="rs" property="jtcy1_xm"/>"
												maxlength="16" />
										</td>
										<td align="right">
											与本人关系：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_gx"
												id="jtcy1_gx"
												value="<bean:write name="rs" property="jtcy1_gx"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<td align="right">
											身份证号码：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_sfzh"
												id="jtcy1_sfzh"
												value="<bean:write name="rs" property="jtcy1_sfzh"/>"
												maxlength="20" />
										</td>
										<td align="right">
											出生日期：
										</td>
										<td align="left">
											<input onclick="return showCalendar('jtcy1_nl','y-mm-dd');"
												type="text" style="width:200px" name="jtcy1_nl"
												id="jtcy1_nl"
												value="<bean:write name="rs" property="jtcy1_nl"/>"
												readonly="readonly" />
										</td>
									</tr>
									<tr>
										<td align="right" width="15%">
											民族：
										</td>
										<td align="left" width="25%">
											<html:select name="rs" property="jtcy1_mz" styleId="jtcy1_mz"
												style="width:150px">
												<html:options collection="mzList" property="mzdm"
													labelProperty="mzmc" />
											</html:select>
										</td>
										<td align="right" width="15%">
											政治面貌：
										</td>
										<td align="left" width="30%">
											<html:select name="rs" property="jtcy1_zzmm"
												styleId="jtcy1_zzmm" style="width:150px">
												<html:options collection="zzmmList" property="zzmmdm"
													labelProperty="zzmmmc" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_zy"
												id="jtcy1_zy"
												value="<bean:write name="rs" property="jtcy1_zy"/>"
												maxlength="10" />
										</td>
										<td align="right">
											职务：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_zw"
												id="jtcy1_zw"
												value="<bean:write name="rs" property="jtcy1_zw"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位电话：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_lxdh2"
												id="jtcy1_lxdh2"
												value="<bean:write name="rs" property="jtcy1_lxdh2"/>"
												onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
										</td>
										<td align="right">
											个人电话：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_lxdh1"
												id="jtcy1_lxdh1"
												value="<bean:write name="rs" property="jtcy1_lxdh1"/>"
												onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
										</td>
									</tr>
									<tr>

										<td align="right">
											单位地址：
										</td>
										<td colspan="3" align="left">
											<input type="text" style="width:600px" name="jtcy1_gzdz"
												id="jtcy1_gzdz"
												value="<bean:write name="rs" property="jtcy1_gzdz"/>"
												maxlength="25" />
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位邮编：
										</td>
										<td colspan="3">
											<html:text name="rs" property="jtcy1_yzbm"
												styleId="jtcy1_yzbm" maxlength="10" style="width:600px"
												onkeyup="value=value.replace(/[^\d]/g,'') " />
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<thead>
							<tr>
								<td colspan="4" style="cursor:hand" align="center"
									onclick="document.getElementById('jt2').style.display=(document.getElementById('jt2').style.display==''?'none':'')">
									学生家庭成员信息2
								</td>
							</tr>
						</thead>
						<tr id="jt2" style="display:none">
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<tr>
										<td align="right">
											姓名：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_xm"
												id="jtcy2_xm"
												value="<bean:write name="rs" property="jtcy2_xm"/>"
												maxlength="16" />
										</td>
										<td align="right">
											与本人关系：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_gx"
												id="jtcy2_gx"
												value="<bean:write name="rs" property="jtcy2_gx"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<td align="right">
											身份证号码：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_sfzh"
												id="jtcy2_sfzh"
												value="<bean:write name="rs" property="jtcy2_sfzh"/>"
												maxlength="20" />
										</td>
										<td align="right">
											出生日期：
										</td>
										<td align="left">
											<input type="text"
												onclick="return showCalendar('jtcy2_nl','y-mm-dd');"
												style="width:200px" name="jtcy2_nl" id="jtcy2_nl"
												value="<bean:write name="rs" property="jtcy2_nl"/>"
												readonly="readonly" />
										</td>
									</tr>
									<tr>
										<td align="right" width="15%">
											民族：
										</td>
										<td align="left" width="25%">
											<html:select name="rs" property="jtcy2_mz" styleId="jtcy2_mz"
												style="width:150px">
												<html:options collection="mzList" property="mzdm"
													labelProperty="mzmc" />
											</html:select>
										</td>
										<td align="right" width="15%">
											政治面貌：
										</td>
										<td align="left" width="30%">
											<html:select name="rs" property="jtcy2_zzmm"
												styleId="jtcy2_zzmm" style="width:150px">
												<html:options collection="zzmmList" property="zzmmdm"
													labelProperty="zzmmmc" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_zy"
												id="jtcy2_zy"
												value="<bean:write name="rs" property="jtcy2_zy"/>"
												maxlength="10" />
										</td>
										<td align="right">
											职务：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_zw"
												id="jtcy2_zw"
												value="<bean:write name="rs" property="jtcy2_zw"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位电话：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_lxdh2"
												id="jtcy2_lxdh2"
												value="<bean:write name="rs" property="jtcy2_lxdh2"/>"
												onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
										</td>
										<td align="right">
											个人电话：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_lxdh1"
												id="jtcy2_lxdh1"
												value="<bean:write name="rs" property="jtcy2_lxdh1"/>"
												onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
										</td>
									</tr>
									<tr>
										<td align="right">
											单位地址：
										</td>
										<td colspan="3" align="left">
											<input type="text" style="width:600px" name="jtcy2_gzdz"
												id="jtcy2_gzdz"
												value="<bean:write name="rs" property="jtcy2_gzdz"/>"
												maxlength="25" />
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位邮编：
										</td>
										<td colspan="43">
											<html:text name="rs" property="jtcy2_yzbm"
												styleId="jtcy2_yzbm" maxlength="10" style="width:600px"
												onkeyup="value=value.replace(/[^\d]/g,'') " />
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<thead>
							<tr>
								<td colspan="4" style="cursor:hand" align="center"
									onclick="document.getElementById('jt3').style.display=(document.getElementById('jt3').style.display==''?'none':'')">
									学生家庭成员信息3
								</td>
							</tr>
						</thead>
						<tr id="jt3" style="display:none">
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<tr>
										<td align="right">
											姓名：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_xm"
												id="jtcy3_xm"
												value="<bean:write name="rs" property="jtcy3_xm"/>"
												maxlength="16" />
										</td>
										<td align="right">
											与本人关系：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_gx"
												id="jtcy3_gx"
												value="<bean:write name="rs" property="jtcy3_gx"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<td align="right">
											身份证号码：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_sfzh"
												id="jtcy3_sfzh"
												value="<bean:write name="rs" property="jtcy3_sfzh"/>"
												maxlength="20" />
										</td>
										<td align="right">
											出生日期：
										</td>
										<td align="left">
											<input type="text"
												onclick="return showCalendar('jtcy3_nl','y-mm-dd');"
												style="width:200px" name="jtcy3_nl" id="jtcy3_nl"
												value="<bean:write name="rs" property="jtcy3_nl"/>"
												readonly="readonly" />
										</td>
									</tr>
									<tr>
										<td align="right" width="15%">
											民族：
										</td>
										<td align="left" width="25%">
											<html:select name="rs" property="jtcy3_mz" styleId="jtcy3_mz"
												style="width:150px">
												<html:options collection="mzList" property="mzdm"
													labelProperty="mzmc" />
											</html:select>
										</td>
										<td align="right" width="15%">
											政治面貌：
										</td>
										<td align="left" width="30%">
											<html:select name="rs" property="jtcy3_zzmm"
												styleId="jtcy3_zzmm" style="width:150px">
												<html:options collection="zzmmList" property="zzmmdm"
													labelProperty="zzmmmc" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_zy"
												id="jtcy3_zy"
												value="<bean:write name="rs" property="jtcy3_zy"/>"
												maxlength="10" />
										</td>
										<td align="right">
											职务：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_zw"
												id="jtcy3_zw"
												value="<bean:write name="rs" property="jtcy3_zw"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位电话：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_lxdh2"
												id="jtcy3_lxdh2"
												value="<bean:write name="rs" property="jtcy3_lxdh2"/>"
												onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
										</td>
										<td align="right">
											个人电话：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_lxdh1"
												id="jtcy3_lxdh1"
												value="<bean:write name="rs" property="jtcy3_lxdh1"/>"
												onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
										</td>
									</tr>
									<tr>
										<td align="right">
											单位地址：
										</td>
										<td colspan="3" align="left">
											<input type="text" style="width:600px" name="jtcy3_gzdz"
												id="jtcy3_gzdz"
												value="<bean:write name="rs" property="jtcy3_gzdz"/>"
												maxlength="25" />
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位邮编：
										</td>
										<td colspan="43">
											<html:text name="rs" property="jtcy3_yzbm"
												styleId="jtcy3_yzbm" maxlength="10" style="width:600px"
												onkeyup="value=value.replace(/[^\d]/g,'') " />
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<thead>
							<tr>
								<td colspan="4" style="cursor:hand" align="center"
									onclick="document.getElementById('jt4').style.display=(document.getElementById('jt4').style.display==''?'none':'')">
									学生家庭成员信息4
								</td>
							</tr>
						</thead>
						<tr id="jt4" style="display:none">
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<tr>
										<td align="right">
											姓名：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy4_xm"
												id="jtcy4_xm"
												value="<bean:write name="rs" property="jtcy4_xm"/>"
												maxlength="16" />
										</td>
										<td align="right">
											与本人关系：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy4_gx"
												id="jtcy4_gx"
												value="<bean:write name="rs" property="jtcy4_gx"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<td align="right">
											身份证号码：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy4_sfzh"
												id="jtcy4_sfzh"
												value="<bean:write name="rs" property="jtcy4_sfzh"/>"
												maxlength="20" />
										</td>
										<td align="right">
											出生日期：
										</td>
										<td align="left">
											<input type="text"
												onclick="return showCalendar('jtcy4_nl','y-mm-dd');"
												style="width:200px" name="jtcy4_nl" id="jtcy4_nl"
												value="<bean:write name="rs" property="jtcy4_nl"/>"
												readonly="readonly" />
										</td>
									</tr>
									<tr>
										<td align="right" width="15%">
											民族：
										</td>
										<td align="left" width="25%">
											<html:select name="rs" property="jtcy4_mz" styleId="jtcy4_mz"
												style="width:150px">
												<html:options collection="mzList" property="mzdm"
													labelProperty="mzmc" />
											</html:select>
										</td>
										<td align="right" width="15%">
											政治面貌：
										</td>
										<td align="left" width="30%">
											<html:select name="rs" property="jtcy4_zzmm"
												styleId="jtcy4_zzmm" style="width:150px">
												<html:options collection="zzmmList" property="zzmmdm"
													labelProperty="zzmmmc" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy4_zy"
												id="jtcy4_zy"
												value="<bean:write name="rs" property="jtcy4_zy"/>"
												maxlength="10" />
										</td>
										<td align="right">
											职务：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy4_zw"
												id="jtcy4_zw"
												value="<bean:write name="rs" property="jtcy4_zw"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位电话：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy4_lxdh2"
												id="jtcy4_lxdh2"
												value="<bean:write name="rs" property="jtcy4_lxdh2"/>"
												onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
										</td>
										<td align="right">
											个人电话：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy4_lxdh1"
												id="jtcy4_lxdh1"
												value="<bean:write name="rs" property="jtcy4_lxdh1"/>"
												onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
										</td>
									</tr>
									<tr>
										<td align="right">
											单位地址：
										</td>
										<td colspan="3" align="left">
											<input type="text" style="width:600px" name="jtcy4_gzdz"
												id="jtcy4_gzdz"
												value="<bean:write name="rs" property="jtcy4_gzdz"/>"
												maxlength="25" />
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位邮编：
										</td>
										<td colspan="43">
											<html:text name="rs" property="jtcy4_yzbm"
												styleId="jtcy4_yzbm" maxlength="10" style="width:600px"
												onkeyup="value=value.replace(/[^\d]/g,'') " />
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<thead>
							<tr>
								<td colspan="4" style="cursor:hand" align="center"
									onclick="document.getElementById('jt5').style.display=(document.getElementById('jt5').style.display==''?'none':'')">
									学生家庭成员信息5
								</td>
							</tr>
						</thead>
						<tr id="jt5" style="display:none">
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<tr>
										<td align="right">
											姓名：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy5_xm"
												id="jtcy5_xm"
												value="<bean:write name="rs" property="jtcy5_xm"/>"
												maxlength="16" />
										</td>
										<td align="right">
											与本人关系：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy5_gx"
												id="jtcy5_gx"
												value="<bean:write name="rs" property="jtcy5_gx"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<td align="right">
											身份证号码：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy5_sfzh"
												id="jtcy5_sfzh"
												value="<bean:write name="rs" property="jtcy5_sfzh"/>"
												maxlength="20" />
										</td>
										<td align="right">
											出生日期：
										</td>
										<td align="left">
											<input type="text"
												onclick="return showCalendar('jtcy5_nl','y-mm-dd');"
												style="width:200px" name="jtcy5_nl" id="jtcy5_nl"
												value="<bean:write name="rs" property="jtcy5_nl"/>"
												readonly="readonly" />
										</td>
									</tr>
									<tr>
										<td align="right" width="15%">
											民族：
										</td>
										<td align="left" width="25%">
											<html:select name="rs" property="jtcy5_mz" styleId="jtcy5_mz"
												style="width:150px">
												<html:options collection="mzList" property="mzdm"
													labelProperty="mzmc" />
											</html:select>
										</td>
										<td align="right" width="15%">
											政治面貌：
										</td>
										<td align="left" width="30%">
											<html:select name="rs" property="jtcy5_zzmm"
												styleId="jtcy5_zzmm" style="width:150px">
												<html:options collection="zzmmList" property="zzmmdm"
													labelProperty="zzmmmc" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy5_zy"
												id="jtcy5_zy"
												value="<bean:write name="rs" property="jtcy5_zy"/>"
												maxlength="10" />
										</td>
										<td align="right">
											职务：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy5_zw"
												id="jtcy5_zw"
												value="<bean:write name="rs" property="jtcy5_zw"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位电话：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy5_lxdh2"
												id="jtcy5_lxdh2"
												value="<bean:write name="rs" property="jtcy5_lxdh2"/>"
												onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
										</td>
										<td align="right">
											个人电话：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy5_lxdh1"
												id="jtcy5_lxdh1"
												value="<bean:write name="rs" property="jtcy5_lxdh1"/>"
												onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
										</td>
									</tr>
									<tr>
										<td align="right">
											单位地址：
										</td>
										<td colspan="3" align="left">
											<input type="text" style="width:600px" name="jtcy5_gzdz"
												id="jtcy5_gzdz"
												value="<bean:write name="rs" property="jtcy5_gzdz"/>"
												maxlength="25" />
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位邮编：
										</td>
										<td colspan="43">
											<html:text name="rs" property="jtcy5_yzbm"
												styleId="jtcy5_yzbm" maxlength="10" style="width:600px"
												onkeyup="value=value.replace(/[^\d]/g,'') " />
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<logic:equal value="yes" name="writeAble">
									<div class="buttontool" align="center">
										<button class="button2" onclick="saveInfo()">
											保 存 信 息
										</button>
									</div>
								</logic:equal>
							</td>
						</tr>
					</table>
				</logic:present>
			</logic:equal>
			<!--以上是学生登陆-->

			<logic:equal name="userOnLine" value="teacher" scope="session">
				<logic:equal name="userType" value="xy" scope="session">
					<logic:present name="rs1">
						<logic:equal name="dataSaved" value="ok" scope="request">
							<script>
					    		alert("保存成功！");
					    	</script>
						</logic:equal>
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td colspan="4" align="center">
										学生基本信息
									</td>
								</tr>
							</thead>
							<tr>
								<td align="right">
									<font color="red">*</font>学号：
								</td>
								<td align="left">
									<html:text name="rs1" property="xh" styleId="xh"
										onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										选择
									</button>
								</td>
								<td align="right">
									姓名：
								</td>
								<td align="left">
									<bean:write name="rs1" property="xm" scope="request" />
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="lable.xsgzyxpzxy" />：
								</td>
								<td align="left">
									<bean:write name="rs1" property="xymc" />
								</td>
								<td align="right">
									专业：
								</td>
								<td align="left">
									<bean:write name="rs1" property="zymc" />
								</td>
							</tr>
							<tr>
								<td align="right">
									年级：
								</td>
								<td align="left">
									<bean:write name="rs1" property="nj" />
								</td>
								<td align="right">
									班级：
								</td>
								<td align="left">
									<bean:write name="rs1" property="bjmc" />
								</td>
							</tr>
							<tr>
							<td align="right">
								姓名拼音：
							</td>
							<td align="left">
								<html:text name="rs" property="xmpy" maxlength="64" />
							</td>
							<td align="right">
								身高：
							</td>
							<td align="left">
								<html:text name="rs" property="sg"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3" />
								(cm)
							</td>
						</tr>
						<tr>
							<td align="right">
								曾用名：
							</td>
							<td align="left">
								<html:text name="rs" property="cym" maxlength="16" />
							</td>
							<td align="right">
								体重：
							</td>
							<td align="left">
								<html:text name="rs" property="tz"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3" />
								(kg)
							</td>

						</tr>
						<tr>
							<td align="right">
								出生日期：
							</td>
							<td align="left">
								<html:text name="rs" property="csrq"
									onclick="return showCalendar('csrq','y-mm-dd');" styleId="csrq"
									readonly="true" />
							</td>
							<td align="right">
								特长：
							</td>
							<td align="left">
								<html:text name="rs" property="tc" maxlength="32" />
							</td>
						</tr>
						<tr>
							<td align="right">
								身份证号：
							</td>
							<td>
								<html:text name="rs" property="sfzh" styleId="sfzh"
									maxlength="20" />
							</td>
							<td align="right">
								培养层次：
							</td>
							<td align="left" colspan="2">
								<html:text name="rs" property="pycc" maxlength="32" />
							</td>
						</tr>

						<tr>
							<td align="right">
								政治面貌：
							</td>
							<td>
								<html:select name="rs" property="zzmm" styleId="zzmm"
									style="width:150px">
									<html:options collection="zzmmList" property="zzmmdm"
										labelProperty="zzmmmc" />
								</html:select>
							</td>
							<td align="right">
								民族：
							</td>
							<td>
								<html:select name="rs" property="mz" styleId="mz"
									style="width:150px">
									<html:options collection="mzList" property="mzdm"
										labelProperty="mzmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								籍贯：
							</td>
							<td colspan="3">
								<html:select name="rs" property="jgs" onchange="loadShi()"
									styleId="jgshen">
									<html:option value="">--请选择--</html:option>
									<html:options collection="ssList" property="ssdm"
										labelProperty="ssmc" />
								</html:select>
								<html:select name="rs" property="jgshi" styleId="jgshi"
									onchange="loadXian('jgshi','jgxian')">
									<html:options collection="shiList" property="shidm"
										labelProperty="shimc" />
								</html:select>
								<html:select name="rs" property="jgx" styleId="jgxian">
									<html:options collection="xianList" labelProperty="xianmc"
										property="xiandm" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								生源地区：
							</td>
							<td colspan="3">
								<html:select name="rs" property="syds" onchange="loadShi()"
									styleId="sydshen">
									<html:option value="">--请选择--</html:option>
									<html:options collection="ssList" property="ssdm"
										labelProperty="ssmc" />
								</html:select>
								<html:select name="rs" property="sydshi" styleId="sydshi"
									onchange="loadXian('sydshi','sydxian')">
									<html:options collection="shiList" property="shidm"
										labelProperty="shimc" />
								</html:select>
								<html:select name="rs" property="sydx" styleId="sydxian">
									<html:options collection="xianList" labelProperty="xianmc"
										property="xiandm" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								职务：
							</td>
							<td>
								<html:text name="rs" property="zw" maxlength="32" />
							</td>
							<td align="right">
								学习方向：
							</td>
							<td colspan="2">
								<html:text name="rs" property="xxfx" maxlength="32" />
							</td>
						</tr>
						<tr>
							<td align="right">
								入学时间：
							</td>
							<td align="left">
								<html:text name="rs" property="rxrq"
									onclick="return showCalendar('rxrq','y-mm-dd');" styleId="rxrq"
									maxlength="10" />
							</td>
							<td align="right">
								毕业时间：
							</td>
							<td align="left">
								<html:text name="rs" property="byny"
									onclick="return showCalendar('byny','y-mm-dd');" styleId="byny"
									maxlength="10" />
							</td>
						<tr>
							<td align="right">
								QQ号码：
							</td>
							<td>
								<html:text name="rs" property="qqhm" maxlength="13"
									onkeyup="value=value.replace(/[^\d]/g,'') " />
							</td>
							<td align="right">
								学制：
							</td>
							<td>
								<html:text name="rs" property="xz" />
							</td>
						</tr>						
						<tr>
							<td align="right">
								联系电话：
							</td>
							<td align="left">
								<input type="text" name="lxdh1" id="lxdh1"
									value="<bean:write name="rs" property="lxdh"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
							</td>
							<td align="right">
								手机号码：
							</td>
							<td align="left">
								<input type="text" name="sjhm" id="sjhm"
									value="<bean:write name="rs" property="sjhm"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="11" />
							</td>
						</tr>
						<tr>
							<td align="right">
								E-Mail：
							</td>
							<td align="left">
								<input type="text" name="dzyx" id="dzyx"
									value="<bean:write name="rs" property="dzyx"/>" />
							</td>
							<td align="right">
								家庭所在地：
							</td>
							<td align="left">
								<input type="text" name="jtszd" id="jtszd"
									value="<bean:write name="rs" property="jtszd"/>" maxlength="25" />
							</td>
						</tr>
						<tr>
							<td align="right">
								家庭年收入：
							</td>
							<td>
								<html:text name="rs" property="jtzsr"
									onkeyup="value=value.replace(/[^\d]/g,'') " styleId="jtzsr" />
								元/年
							</td>
							<td align="right">
								是否需要助学贷款：
							</td>
							<td>
								<html:radio name="rs" property="sfdk" value="是" styleId="sfdks">是</html:radio>
								<html:radio name="rs" property="sfdk" value="否" styleId="sfdkf">否</html:radio>
							</td>
						</tr>
						<tr height="80">
							<td align="right">
								校外住宿详细地址：
							</td>
							<td align="left" colspan="4">
								<html:textarea name="rs" property="xwzsxxdz" styleId="xwzsxxdz"
									style="width:100%;height:100%"></html:textarea>
							</td>
						</tr>
						<tr height="22">
							<td align="right">
								宿舍号：
							</td>
							<td align="left">
								<html:text property="ssbh" name="rs" styleId="ssbh" />
							</td>
							<td align="right">
								宿舍床号：
							</td>
							<td align="left">
								<html:text name="rs" property="ssch" styleId="ssch"/>
							</td>
						</tr>
						<tr height="22">
							<td align="right">
								住宿日期：
							</td>
							<td align="left" valign="middle">
								<html:text name="rs" property="zsrq" styleId="zsrq"
									readonly="true"
									onclick="return showCalendar('zsrq','y-mm-dd');" />
							</td>
							<td align="right">
								住宿截止日期：
							</td>
							<td align="left">
								<html:text name="rs" property="zsjzrq" styleId="zsjzrq"
									readonly="true"
									onclick="return showCalendar('zsjzrq','y-mm-dd');" />
							</td>
						</tr>
						<%--家庭信息--%>
						<thead>
							<tr>
								<td colspan="4" style="cursor:hand" align="center"
									onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
									学生家庭成员信息1
								</td>
							</tr>
						</thead>
						<tr id="jt1" style="display:none">
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<tr>
										<td align="right">
											姓名：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_xm"
												id="jtcy1_xm"
												value="<bean:write name="rs" property="jtcy1_xm"/>"
												maxlength="16" />
										</td>
										<td align="right">
											与本人关系：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_gx"
												id="jtcy1_gx"
												value="<bean:write name="rs" property="jtcy1_gx"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<td align="right">
											身份证号码：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_sfzh"
												id="jtcy1_sfzh"
												value="<bean:write name="rs" property="jtcy1_sfzh"/>"
												maxlength="20" />
										</td>
										<td align="right">
											出生日期：
										</td>
										<td align="left">
											<input onclick="return showCalendar('jtcy1_nl','y-mm-dd');"
												type="text" style="width:200px" name="jtcy1_nl"
												id="jtcy1_nl"
												value="<bean:write name="rs" property="jtcy1_nl"/>"
												readonly="readonly" />
										</td>
									</tr>
									<tr>
										<td align="right" width="15%">
											民族：
										</td>
										<td align="left" width="25%">
											<html:select name="rs" property="jtcy1_mz" styleId="jtcy1_mz"
												style="width:150px">
												<html:options collection="mzList" property="mzdm"
													labelProperty="mzmc" />
											</html:select>
										</td>
										<td align="right" width="15%">
											政治面貌：
										</td>
										<td align="left" width="30%">
											<html:select name="rs" property="jtcy1_zzmm"
												styleId="jtcy1_zzmm" style="width:150px">
												<html:options collection="zzmmList" property="zzmmdm"
													labelProperty="zzmmmc" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_zy"
												id="jtcy1_zy"
												value="<bean:write name="rs" property="jtcy1_zy"/>"
												maxlength="10" />
										</td>
										<td align="right">
											职务：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_zw"
												id="jtcy1_zw"
												value="<bean:write name="rs" property="jtcy1_zw"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位电话：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_lxdh2"
												id="jtcy1_lxdh2"
												value="<bean:write name="rs" property="jtcy1_lxdh2"/>"
												onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
										</td>
										<td align="right">
											个人电话：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_lxdh1"
												id="jtcy1_lxdh1"
												value="<bean:write name="rs" property="jtcy1_lxdh1"/>"
												onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
										</td>
									</tr>
									<tr>

										<td align="right">
											单位地址：
										</td>
										<td colspan="3" align="left">
											<input type="text" style="width:600px" name="jtcy1_gzdz"
												id="jtcy1_gzdz"
												value="<bean:write name="rs" property="jtcy1_gzdz"/>"
												maxlength="25" />
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位邮编：
										</td>
										<td colspan="3">
											<html:text name="rs" property="jtcy1_yzbm"
												styleId="jtcy1_yzbm" maxlength="10" style="width:600px"
												onkeyup="value=value.replace(/[^\d]/g,'') " />
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<thead>
							<tr>
								<td colspan="4" style="cursor:hand" align="center"
									onclick="document.getElementById('jt2').style.display=(document.getElementById('jt2').style.display==''?'none':'')">
									学生家庭成员信息2
								</td>
							</tr>
						</thead>
						<tr id="jt2" style="display:none">
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<tr>
										<td align="right">
											姓名：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_xm"
												id="jtcy2_xm"
												value="<bean:write name="rs" property="jtcy2_xm"/>"
												maxlength="16" />
										</td>
										<td align="right">
											与本人关系：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_gx"
												id="jtcy2_gx"
												value="<bean:write name="rs" property="jtcy2_gx"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<td align="right">
											身份证号码：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_sfzh"
												id="jtcy2_sfzh"
												value="<bean:write name="rs" property="jtcy2_sfzh"/>"
												maxlength="20" />
										</td>
										<td align="right">
											出生日期：
										</td>
										<td align="left">
											<input type="text"
												onclick="return showCalendar('jtcy2_nl','y-mm-dd');"
												style="width:200px" name="jtcy2_nl" id="jtcy2_nl"
												value="<bean:write name="rs" property="jtcy2_nl"/>"
												readonly="readonly" />
										</td>
									</tr>
									<tr>
										<td align="right" width="15%">
											民族：
										</td>
										<td align="left" width="25%">
											<html:select name="rs" property="jtcy2_mz" styleId="jtcy2_mz"
												style="width:150px">
												<html:options collection="mzList" property="mzdm"
													labelProperty="mzmc" />
											</html:select>
										</td>
										<td align="right" width="15%">
											政治面貌：
										</td>
										<td align="left" width="30%">
											<html:select name="rs" property="jtcy2_zzmm"
												styleId="jtcy2_zzmm" style="width:150px">
												<html:options collection="zzmmList" property="zzmmdm"
													labelProperty="zzmmmc" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_zy"
												id="jtcy2_zy"
												value="<bean:write name="rs" property="jtcy2_zy"/>"
												maxlength="10" />
										</td>
										<td align="right">
											职务：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_zw"
												id="jtcy2_zw"
												value="<bean:write name="rs" property="jtcy2_zw"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位电话：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_lxdh2"
												id="jtcy2_lxdh2"
												value="<bean:write name="rs" property="jtcy2_lxdh2"/>"
												onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
										</td>
										<td align="right">
											个人电话：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_lxdh1"
												id="jtcy2_lxdh1"
												value="<bean:write name="rs" property="jtcy2_lxdh1"/>"
												onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
										</td>
									</tr>
									<tr>
										<td align="right">
											单位地址：
										</td>
										<td colspan="3" align="left">
											<input type="text" style="width:600px" name="jtcy2_gzdz"
												id="jtcy2_gzdz"
												value="<bean:write name="rs" property="jtcy2_gzdz"/>"
												maxlength="25" />
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位邮编：
										</td>
										<td colspan="43">
											<html:text name="rs" property="jtcy2_yzbm"
												styleId="jtcy2_yzbm" maxlength="10" style="width:600px"
												onkeyup="value=value.replace(/[^\d]/g,'') " />
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<thead>
							<tr>
								<td colspan="4" style="cursor:hand" align="center"
									onclick="document.getElementById('jt3').style.display=(document.getElementById('jt3').style.display==''?'none':'')">
									学生家庭成员信息3
								</td>
							</tr>
						</thead>
						<tr id="jt3" style="display:none">
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<tr>
										<td align="right">
											姓名：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_xm"
												id="jtcy3_xm"
												value="<bean:write name="rs" property="jtcy3_xm"/>"
												maxlength="16" />
										</td>
										<td align="right">
											与本人关系：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_gx"
												id="jtcy3_gx"
												value="<bean:write name="rs" property="jtcy3_gx"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<td align="right">
											身份证号码：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_sfzh"
												id="jtcy3_sfzh"
												value="<bean:write name="rs" property="jtcy3_sfzh"/>"
												maxlength="20" />
										</td>
										<td align="right">
											出生日期：
										</td>
										<td align="left">
											<input type="text"
												onclick="return showCalendar('jtcy3_nl','y-mm-dd');"
												style="width:200px" name="jtcy3_nl" id="jtcy3_nl"
												value="<bean:write name="rs" property="jtcy3_nl"/>"
												readonly="readonly" />
										</td>
									</tr>
									<tr>
										<td align="right" width="15%">
											民族：
										</td>
										<td align="left" width="25%">
											<html:select name="rs" property="jtcy3_mz" styleId="jtcy3_mz"
												style="width:150px">
												<html:options collection="mzList" property="mzdm"
													labelProperty="mzmc" />
											</html:select>
										</td>
										<td align="right" width="15%">
											政治面貌：
										</td>
										<td align="left" width="30%">
											<html:select name="rs" property="jtcy3_zzmm"
												styleId="jtcy3_zzmm" style="width:150px">
												<html:options collection="zzmmList" property="zzmmdm"
													labelProperty="zzmmmc" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_zy"
												id="jtcy3_zy"
												value="<bean:write name="rs" property="jtcy3_zy"/>"
												maxlength="10" />
										</td>
										<td align="right">
											职务：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_zw"
												id="jtcy3_zw"
												value="<bean:write name="rs" property="jtcy3_zw"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位电话：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_lxdh2"
												id="jtcy3_lxdh2"
												value="<bean:write name="rs" property="jtcy3_lxdh2"/>"
												onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
										</td>
										<td align="right">
											个人电话：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_lxdh1"
												id="jtcy3_lxdh1"
												value="<bean:write name="rs" property="jtcy3_lxdh1"/>"
												onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
										</td>
									</tr>
									<tr>
										<td align="right">
											单位地址：
										</td>
										<td colspan="3" align="left">
											<input type="text" style="width:600px" name="jtcy3_gzdz"
												id="jtcy3_gzdz"
												value="<bean:write name="rs" property="jtcy3_gzdz"/>"
												maxlength="25" />
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位邮编：
										</td>
										<td colspan="43">
											<html:text name="rs" property="jtcy3_yzbm"
												styleId="jtcy3_yzbm" maxlength="10" style="width:600px"
												onkeyup="value=value.replace(/[^\d]/g,'') " />
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<thead>
							<tr>
								<td colspan="4" style="cursor:hand" align="center"
									onclick="document.getElementById('jt4').style.display=(document.getElementById('jt4').style.display==''?'none':'')">
									学生家庭成员信息4
								</td>
							</tr>
						</thead>
						<tr id="jt4" style="display:none">
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<tr>
										<td align="right">
											姓名：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy4_xm"
												id="jtcy4_xm"
												value="<bean:write name="rs" property="jtcy4_xm"/>"
												maxlength="16" />
										</td>
										<td align="right">
											与本人关系：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy4_gx"
												id="jtcy4_gx"
												value="<bean:write name="rs" property="jtcy4_gx"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<td align="right">
											身份证号码：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy4_sfzh"
												id="jtcy4_sfzh"
												value="<bean:write name="rs" property="jtcy4_sfzh"/>"
												maxlength="20" />
										</td>
										<td align="right">
											出生日期：
										</td>
										<td align="left">
											<input type="text"
												onclick="return showCalendar('jtcy4_nl','y-mm-dd');"
												style="width:200px" name="jtcy4_nl" id="jtcy4_nl"
												value="<bean:write name="rs" property="jtcy4_nl"/>"
												readonly="readonly" />
										</td>
									</tr>
									<tr>
										<td align="right" width="15%">
											民族：
										</td>
										<td align="left" width="25%">
											<html:select name="rs" property="jtcy4_mz" styleId="jtcy4_mz"
												style="width:150px">
												<html:options collection="mzList" property="mzdm"
													labelProperty="mzmc" />
											</html:select>
										</td>
										<td align="right" width="15%">
											政治面貌：
										</td>
										<td align="left" width="30%">
											<html:select name="rs" property="jtcy4_zzmm"
												styleId="jtcy4_zzmm" style="width:150px">
												<html:options collection="zzmmList" property="zzmmdm"
													labelProperty="zzmmmc" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy4_zy"
												id="jtcy4_zy"
												value="<bean:write name="rs" property="jtcy4_zy"/>"
												maxlength="10" />
										</td>
										<td align="right">
											职务：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy4_zw"
												id="jtcy4_zw"
												value="<bean:write name="rs" property="jtcy4_zw"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位电话：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy4_lxdh2"
												id="jtcy4_lxdh2"
												value="<bean:write name="rs" property="jtcy4_lxdh2"/>"
												onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
										</td>
										<td align="right">
											个人电话：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy4_lxdh1"
												id="jtcy4_lxdh1"
												value="<bean:write name="rs" property="jtcy4_lxdh1"/>"
												onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
										</td>
									</tr>
									<tr>
										<td align="right">
											单位地址：
										</td>
										<td colspan="3" align="left">
											<input type="text" style="width:600px" name="jtcy4_gzdz"
												id="jtcy4_gzdz"
												value="<bean:write name="rs" property="jtcy4_gzdz"/>"
												maxlength="25" />
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位邮编：
										</td>
										<td colspan="43">
											<html:text name="rs" property="jtcy4_yzbm"
												styleId="jtcy4_yzbm" maxlength="10" style="width:600px"
												onkeyup="value=value.replace(/[^\d]/g,'') " />
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<thead>
							<tr>
								<td colspan="4" style="cursor:hand" align="center"
									onclick="document.getElementById('jt5').style.display=(document.getElementById('jt5').style.display==''?'none':'')">
									学生家庭成员信息5
								</td>
							</tr>
						</thead>
						<tr id="jt5" style="display:none">
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<tr>
										<td align="right">
											姓名：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy5_xm"
												id="jtcy5_xm"
												value="<bean:write name="rs" property="jtcy5_xm"/>"
												maxlength="16" />
										</td>
										<td align="right">
											与本人关系：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy5_gx"
												id="jtcy5_gx"
												value="<bean:write name="rs" property="jtcy5_gx"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<td align="right">
											身份证号码：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy5_sfzh"
												id="jtcy5_sfzh"
												value="<bean:write name="rs" property="jtcy5_sfzh"/>"
												maxlength="20" />
										</td>
										<td align="right">
											出生日期：
										</td>
										<td align="left">
											<input type="text"
												onclick="return showCalendar('jtcy5_nl','y-mm-dd');"
												style="width:200px" name="jtcy5_nl" id="jtcy5_nl"
												value="<bean:write name="rs" property="jtcy5_nl"/>"
												readonly="readonly" />
										</td>
									</tr>
									<tr>
										<td align="right" width="15%">
											民族：
										</td>
										<td align="left" width="25%">
											<html:select name="rs" property="jtcy5_mz" styleId="jtcy5_mz"
												style="width:150px">
												<html:options collection="mzList" property="mzdm"
													labelProperty="mzmc" />
											</html:select>
										</td>
										<td align="right" width="15%">
											政治面貌：
										</td>
										<td align="left" width="30%">
											<html:select name="rs" property="jtcy5_zzmm"
												styleId="jtcy5_zzmm" style="width:150px">
												<html:options collection="zzmmList" property="zzmmdm"
													labelProperty="zzmmmc" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy5_zy"
												id="jtcy5_zy"
												value="<bean:write name="rs" property="jtcy5_zy"/>"
												maxlength="10" />
										</td>
										<td align="right">
											职务：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy5_zw"
												id="jtcy5_zw"
												value="<bean:write name="rs" property="jtcy5_zw"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位电话：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy5_lxdh2"
												id="jtcy5_lxdh2"
												value="<bean:write name="rs" property="jtcy5_lxdh2"/>"
												onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
										</td>
										<td align="right">
											个人电话：
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy5_lxdh1"
												id="jtcy5_lxdh1"
												value="<bean:write name="rs" property="jtcy5_lxdh1"/>"
												onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="13" />
										</td>
									</tr>
									<tr>
										<td align="right">
											单位地址：
										</td>
										<td colspan="3" align="left">
											<input type="text" style="width:600px" name="jtcy5_gzdz"
												id="jtcy5_gzdz"
												value="<bean:write name="rs" property="jtcy5_gzdz"/>"
												maxlength="25" />
										</td>
									</tr>
									<tr>
										<td align="right">
											工作单位邮编：
										</td>
										<td colspan="43">
											<html:text name="rs" property="jtcy5_yzbm"
												styleId="jtcy5_yzbm" maxlength="10" style="width:600px"
												onkeyup="value=value.replace(/[^\d]/g,'') " />
										</td>
									</tr>
								</table>
							</td>
						</tr>
						</table>
						<br />
						<logic:equal value="yes" name="writeAble">
							<div class="buttontool" align="center">
								<logic:notPresent name="cWrite">
									<button class="button2"
										onclick="refreshForm('stu_info_add.do?method=modiStuInfo&act=save');this.disabled=true;">
										保 存 信 息
									</button>
								</logic:notPresent>
								<logic:present name="cWrite">
									<button class="button2" disabled="disabled">
										保 存 信 息
									</button>
								</logic:present>
							</div>
						</logic:equal>

					</logic:present>
				</logic:equal>
			</logic:equal>
			<logic:equal value="true" name="result">
				<script>
					alert("操作成功!");
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("操作失败!");
				</script>
			</logic:equal>

		</html:form>
	</body>
</html>
