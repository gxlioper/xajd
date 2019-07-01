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
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script type="text/javascript">
	function getDw(){
		var pk = document.getElementById("xmdm").value;
		if(pk!=null && pk!=""){
		getOtherData.getDwmc(pk,function(data){
			document.getElementById("sqdw").value = data[0];
			document.getElementById("fzr").value = data[1];
			document.getElementById("gwlxdh").value = data[2];
		});
		}		
	}
	function getFzr(){
		var pk = document.getElementById("xmdm").value;
		if(pk!=null && pk!=""){
			getOtherData.getDwmc(pk,function(data){
				if($("fzr")){
					document.getElementById("fzr").value = data[1];
				}
				if($("gwlxdh")){
					document.getElementById("gwlxdh").value = data[2];
				}
				if($("gzsj")){
					document.getElementById("gzsj").innerText = data[3];
				}
				if($("gznr")){
					document.getElementById("gznr").innerText = data[4];
				}	
				if($("ryyq")){
					document.getElementById("ryyq").innerText = data[5];
				}			
			});			
		}		
	}
	</script>
	<body>
		<html:form action="/qgzxZjcm" method="post">
			<div class="title">
				<logic:equal name="do" value="no">
					<div class="title_img" id="title_m">
						当前位置：勤工助学 - 岗位申请 - 填写申请表
					</div>
				</logic:equal>
				<logic:equal name="do" value="yes">
					<div class="title_img" id="title_m">
						当前位置：勤工助学 - 岗位申请 - 修改申请表
					</div>
				</logic:equal>
			</div>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc-kh" />
				<input type="hidden" id="url" name="url" value="/post_stu_apply.do" />
				<input type="hidden" id="do" name="do" value="${do}" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<logic:equal name="do" value="no">
									<b>填写申请表</b>
								</logic:equal>
								<logic:equal name="do" value="yes">
									<b>修改申请表</b>
								</logic:equal>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left">
								<bean:write name='rs' property="xh" />
								<input type="hidden" name="xh" id="xh" value="<bean:write name='rs' property="xh" />">
							</td>
						</logic:equal>
						<td align="right">

							<font color="red">*</font>岗位名称：
						</td>
						<logic:equal value="modi" name="doType">
							<td align="left">
								<input type="hidden" id="isModi" name="isModi"
									value="<bean:write name="doType" scope="request"/>" />
								<input type="hidden" name="xmdmmodi" id="xmdmmodi"
									value="<bean:write name='rs' property='xmdm'/>">
								<html:select name="rs" property="xmdm" styleId="xmdm"
									style="width:150px" disabled="true" onchange="">
									<html:option value=""></html:option>
									<html:options collection="gwList" property="gwdmgwsbsj"
										labelProperty="gwdm" />
								</html:select>

							</td>
						</logic:equal>
						<logic:notEqual value="modi" name="doType">
							<td align="left">
								<html:select name="rs" property="xmdm" styleId="xmdm"
									style="width:150px" onchange="getFzr();">
									<html:option value=""></html:option>
									<html:options collection="gwList" property="gwdmgwsbsj"
										labelProperty="gwdm" />
								</html:select>
							</td>
						</logic:notEqual>
					</tr>

					<tr style="height:22px">
						<td align="right">
							姓名：
						</td>
						<td align="left">
							<bean:write name="rs" property="xm" />
						</td>
						<td align="right">
							年度：
						</td>
						<td align="left">
							<html:text name="rs1" property="nd" readonly="true" styleId="nd"></html:text>
							<html:hidden property="nd" name="rs1"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							性别：
						</td>
						<td align="left">
							<bean:write name="rs" property="xb" />
						</td>
						<td align="right">
							学年：
						</td>
						<td align="left">							
							<html:text name="rs1" property="xn" readonly="true" styleId="xn"></html:text>
							<html:hidden property="xn" name="rs1"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							年级：
						</td>
						<td align="left">
							<bean:write name="rs" property="nj" />
						</td>
						<td align="right">
							学期：
						</td>
						<td align="left">
							<html:text name="rs1" property="xqmc" readonly="true" styleId="xqmc"></html:text>
							<html:hidden property="xq" name="rs1"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<bean:write name="rs" property="xymc" />
						</td>
						<td align="right">
							计算机水平：
						</td>
						<td align="left">
							<html:text name='rs' property="jsjsp" styleId="jsjsp" maxlength="250"/>
						</td>
					</tr>				
					
					<tr>
						<td align="right">
							专业：
						</td>
						<td>
							<bean:write name="rs" property="zymc" />
						</td>						
						<td align="right">
							是否服从分派：
						</td>
						<td align="left">
							<html:radio property="sffcfp" value="1" styleId="sffcfp1">是</html:radio>				
							<html:radio property="sffcfp" value="0" styleId="sffcfp0">否</html:radio>				
						</td>							
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td>
							<bean:write name="rs" property="bjmc" />
						</td>
						<td align="right">
							联系电话：
						</td>
						<td align="left">
							<html:text name='rs' property="lxdh" styleId="lxdh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "/>
						</td>	
					</tr>
					<tr>
						<td align="right">
							民族：
						</td>
						<td>
							<bean:write name="rs" property="mzmc" />
						</td>
						<td align="right" rowspan="3">
							其它特长：
						</td>
						<td align="left" rowspan="3">
							<html:textarea property="yhtc" name="rs" styleId="yhtc" style="width:100%;"/>					
						</td>
					</tr>	
					<tr>
						<td align="right">
							政治面貌：
						</td>
						<td>
							<bean:write name="rs" property="zzmmmc" />
						</td>						
					</tr>				
					<tr>
						<td align="right">
							寝室号：
						</td>
						<td>
							<bean:write name="rs" property="ldmc" /><bean:write name="rs" property="qsh" />
						</td>						
					</tr>
					<tr>
						<td align="right">
							个人情况：
						</td>					
						<td align="left" colspan="3">
							<html:textarea property="mqqgzxqk" name="rs" styleId="mqqgzxqk" style="width:100%;height:45px"/>					
						</td>
					</tr>	
					<tr>
					<td align="right">
						可参加勤工助学时间：
					</td>
					<logic:present name="kxList">
							<td colspan="3">
								<table border="0" cellpadding="0" cellspacing="0" align="center">
									<tr>
										<td align="center">
											时间
										</td>
										<td>
											星期一
										</td>
										<td>
											星期二
										</td>
										<td>
											星期三
										</td>
										<td>
											星期四
										</td>
										<td>
											星期五
										</td>
										<td>
											星期六
										</td>
										<td>
											星期日
										</td>
									</tr>
									<logic:iterate id="kxsj" name="kxList">
										<tr>
											<td>
												${kxsj.sj}
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}1" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}1"
													value="${kxsj.mon}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}2" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}2"
													value="${kxsj.tue}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}3" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}3"
													value="${kxsj.wed}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}4" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}4"
													value="${kxsj.thu}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}5" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}5"
													value="${kxsj.fri}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}6" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}6"
													value="${kxsj.sat}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}7" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}7"
													value="${kxsj.sun}" />
											</td>

										</tr>
									</logic:iterate>
								</table>
								</td>
								<script language="javascript">
										for(var i=0;i<8;i++){
											for(var j=1;j<8;j++){
											 	if(document.getElementById("index"+i+j)){
											 		if(document.getElementById("index"+i+j).value==1){
														document.getElementById(i+""+j).checked="checked";
												    }
											 	}
											}
										}
									</script>							
						</logic:present>
						<logic:notPresent name="kxList">
							<input type="hidden" name="sfwh" value="sfwh" />
							<td colspan="3" align="center">
								<table border="0" cellpadding="0" cellspacing="0" align="center"
									class="tbstyle">
									<tbody id="tbSj">
										<tr>
											<td align="center">
												时间
											</td>
											<td>
												星期一
											</td>
											<td>
												星期二
											</td>
											<td>
												星期三
											</td>
											<td>
												星期四
											</td>
											<td>
												星期五
											</td>
											<td>
												星期六
											</td>
											<td>
												星期日
											</td>
										</tr>
										<logic:iterate id="kxsj" name="whkxList">
											<tr>
												<td>
													${kxsj.sj}
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}1" value="1" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}2" value="1" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}3" value="1" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}4" value="1" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}5" value="1" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}6" value="1" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}7" value="1" />
												</td>
											</tr>
										</logic:iterate>
									</tbody>
								</table>
							</td>
						</logic:notPresent>
					</tr>					
				</table>
				<div class="buttontool" align="center">
					<logic:equal name="do" value="no" scope="request">
						<button type="button" class="button2"
							onclick="saveGwsq('qgzxZjcm.do?method=saveGwsq','xh-xmdm')">
							提 交 申 请
						</button>
					</logic:equal>
					<logic:equal name="do" value="yes" scope="request">
						<button type="button" class="button2"
							onclick="saveGwsq('qgzxZjcm.do?method=saveGwsq','xh-xmdm')">
							保 存 申 请
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:equal>					
					<button type="button" class="button2" onclick="printGwsq();">
							打 印 预 览
					</button>	
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				
				</div>
			<logic:notEmpty name="result">
				<logic:equal name="result" value="true">
					<script>
				    alert("申请成功！");
				    </script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<logic:present name="cannot">
						<input type="hidden" id=cannot name="cannot" value="${cannot}" />
						<script>
				          alert(document.getElementById("cannot").value);
				      </script>
					</logic:present>
					<logic:notPresent name="cannot">
						<logic:present name="isHmd">
							<script>
						   alert("你已经被列入黑名单,暂时无法申请勤工助学岗位！");
						</script>
						</logic:present>
						<logic:notPresent name="isHmd">
							<script>
					    	alert("申请失败！");
					    </script>
						</logic:notPresent>
					</logic:notPresent>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
