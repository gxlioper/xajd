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
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script type="text/javascript">
		function modidata(url,w,h){
			if(curr_row == null){
				alert("请选择一行要修改的记录！");
				return false;
			}
			var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
			refreshForm(url+pkValue,w,h);
		}			
		
		function save(){
			var oldFlag = val("yesNoOld");
			var flag = val("yesNo");
			var xxdm = $("xxdm").value;
			//非广州大学
			if(xxdm != "11078"){
				if(oldFlag == flag){
					alert("审核结果没有发生改变！");
					return false;
				}
			}
			showTips('处理数据中，请等待......');
			refreshForm('qgzxZgdzdx.do?method=saveQgzxsqsh')
		}
		
		function stringFormat(){
			if(ele('bzTd')){	
				ele('bzTd').innerHTML = formatContentWidth(ele('bzTd').innerText,30);
			}
			if(ele('sqlyTd')){
				ele('sqlyTd').innerHTML = formatContentWidth(ele('sqlyTd').innerText,30);
			}
			if(ele('yhtcTd')){
				ele('yhtcTd').innerHTML = formatContentWidth(ele('yhtcTd').innerText,30);
			}
		}
		
		//改变审核结果
		function changeYesNo(value){
			if(value == '通过'){
				ele('tdGw').style.display = '';
				ele('trGw').style.display = '';
				ele('tdGwdm').style.display = '';
			}else{
				ele('tdGw').style.display = 'none';
				ele('trGw').style.display = 'none';
				ele('tdGwdm').style.display = 'none';
			}
		}
		
		function getValue(){
			var value = val("gwdm");
			var text = selText("gwdm");
			var xmdm = val("xmdm");
			var zdgw = val("zdgw");
			if(checkArrayEleRepeat(xmdm+"!!" + value,"!!")){
				alert('该岗位已经指定！');
				return false;
			}else{
				if(zdgw == null || zdgw == ""){
					setVal("zdgw", text);
				}else{
					setVal('zdgw',zdgw + "," + text);
				}
				
				setVal('xmdm',val("xmdm") + "!!" + value);
			}
		}
		
		function initValue(){
			//判断指定的岗位是否已经无效
			var yzdgw = val('yzdgwdmgwsbsj');
			var xh = val('xh');
			cqkjFunc.checkZdgwExists(xh,yzdgw,function(data){
				if(data != null && data.gwdm != null && data.gwdm != ""){
					setVal('zdgw',data.gwdm);
					setVal('xmdm',data.gwsbsj);
				}
			});
			
		}
	
	//重置岗位信息
	function czgw(){
		var xxdm = $("xxdm").value;
		var zdgw = $("zdgw").value;
		var xmdm = $("xmdm").value;
		
		if(zdgw != ""){
			$("zdgw").value = "";
		}
		if(xmdm != ""){
			$("xmdm").value = "";
		}
	}
	</script>

	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	
	<body onload="stringFormat();changeYesNo(val('yesNo'));initValue()">		
		<input type="hidden" id="userType" name="userType" value="${userType }"/>
		<input type="hidden" name="mes" id="mes" value="${mes}"/>
		<input type="hidden" name="yzdgw" id="yzdgw" value="${rs.zdgwdm}"/>
		<input type="hidden" name="yzdgwdmgwsbsj" id="yzdgwdmgwsbsj" value="${rs.zdgwdmgwsbsj}"/>
		<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }"/>
		<html:form action="/qgzxZgdzdx.do" method="post">	
			<div class="title">
				<div class="title_img" id="title_m">
				当前位置：勤工助学 - 审核 - 勤工助学申请审核
			</div>
			</div>				
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
						<tr>
						<td colspan="4" align="center">
							学生勤工助学申请审核
							</td>
							</tr>
						</thead>
						<tr>
						<td align="right">
							学号：
						</td>
						<td>
							<bean:write name="rs" property="xh"/>
							<html:hidden property="xh" name="rs"/>
						</td>
						<td align="right">
							学年：
						</td>
						<td>
							<bean:write name="rs" property="xn"/>
							<html:hidden property="xn" name="rs"/>
						</td>
						</tr>
						<tr>
						<td align="right">
							姓名：
						</td>
						<td>
							<bean:write name="rs" property="xm"/>
						</td>
						<td align="right">
							年度：
						</td>
						<td>
							<bean:write name="rs" property="nd"/>
							<html:hidden property="nd" name="rs"/>
						</td>
						</tr>	
						<tr>
						<td align="right">
							性别：
						</td>
						<td>
							<bean:write name="rs" property="xb"/>
						</td>
						<td align="right">
							学期：
						</td>
						<td>
							<bean:write name="rs" property="xqmc"/>
							<html:hidden property="xq" name="rs"/>
						</td>
						</tr>	
						<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />名称：
						</td>
						<td>
							<bean:write name="rs" property="xymc"/>
						</td>
						<td align="right">
							联系电话：
						</td>
						<td>
							<bean:write name="rs" property="lxdh"/>
						</td>
						</tr>	
						
						<tr>
							<td align="right">
								班级名称：
							</td>
							<td>
								<bean:write name="rs" property="bjmc"/>
							</td>
							<td align="right">
								宿舍编号：
							</td>
							<td>
								<bean:write name="rs" property="ssbh"/>
							</td>
						</tr>
						<tr>
						<td align="right">
							有何特长：
						</td>
						<td id="yhtcTd">
							<bean:write name="rs" property="yhtc"/>
						</td>	
						<td align="right">
							岗位性质：
						</td>
						<td colspan="3">
							<bean:write name="rs" property="gwxzmc"/>
							<html:hidden name="rs" styleId="gwxzdm" property="gwxzdm"/>
						</td>
						</tr>
						<tr>
						<td align="right">
							学生可参加勤工助学时间：
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
										</tr>
									</logic:iterate>
								</table>
								</td>
								<script language="javascript">
										for(var i=0;i<8;i++){
											for(var j=1;j<6;j++){
											 	if(document.getElementById("index"+i+j)){
											 		if(document.getElementById("index"+i+j).value==1){
														document.getElementById(i+""+j).checked="checked";
												    }
											 	}
											}
										}
									</script>							
						</logic:present>
						</tr>
						<tr>
						<td align="right">
							家庭经济情况：
						</td>
						<td colspan="3">
							<bean:write name="rs" property="jjqk"/>
						</td>							
						</tr>	
						<tr>
						<td align="right">
							可从事工作：
						</td>
						<td colspan="3">
							<bean:write name="rs" property="kcsgz"/>
						</td>							
						</tr>
						<tr>
						<td align="right">
							备注：
						</td>
						<td colspan="3" id="bzTd">
							<bean:write name="rs" property="bz"/>
						</td>							
						</tr>
						<tr>
						<td colspan="4" align="center" bgcolor="D0E0EE" onclick="ele('xscjTable').style.display = ele('xscjTable').style.display=='none' ? '' : 'none'">
							<b style="cursor:hand" title="显示/隐藏">学生成绩信息</b>
						</td>
						</tr>
						<tr style="display:none" id="xscjTable">
							<td colspan="4">
								<table  width="100%" class="tbstyle">
										<thead >
											<tr align="center">
												<td align="center">学年</td>
												<td align="center">年度</td>
												<td align="center">学期</td>
											    <td align="center">课程名称</td>
												<td align="center">成绩</td>
												<td align="center">补考成绩</td>
												<td align="center">重修成绩</td>
												<td align="center">总评成绩</td>
											</tr>
											</thead>
											<logic:iterate name="cjrs" id="s">
												<tr>
													<logic:iterate id="v" name="s">
														<td>
															<font color="red"><bean:write name="v" /></font>
														</td>
													</logic:iterate>
												</tr>
											</logic:iterate>
								</table>
							</td>
						</tr>	
						<tr>
						<td colspan="4" align="center" bgcolor="D0E0EE" onclick="ele('xswjTable').style.display = ele('xswjTable').style.display=='none' ? '' : 'none'">
							<b style="cursor:hand" title="显示/隐藏">学生违纪信息</b>
						</td>
						</tr>
						<tr style="display:none" id="xswjTable">
							<td colspan="4">
								<table  width="100%" class="tbstyle">
										<thead >
											<tr align="center">
												<td align="center">学年</td>
												<td align="center">年度</td>
												<td align="center">学期</td>
											    <td align="center">处分文号</td>
												<td align="center">违纪时间</td>
												<td align="center">处分原因</td>
												<td align="center">处分类别</td>
												<td align="center">处分级别</td>
												<td align="center">解除文号</td>
											</tr>
											</thead>
											<logic:iterate name="wjrs" id="s">
												<tr>
													<logic:iterate id="v" name="s">
														<td>
															<font color="red"><bean:write name="v" /></font>
														</td>
													</logic:iterate>
												</tr>
											</logic:iterate>
								</table>
							</td>
						</tr>
						<!--广州大学-->
						<logic:equal value="11078" name="xxdm">
						<tr>					
						<td align="right">
							审核：
						</td>
						<td>
							<html:select property="yesNo" name="rs" styleId="yesNo" onchange="changeYesNo(this.value)">
							<html:option value=""></html:option>
							<html:options collection="chkList" property="en" labelProperty="cn" />
							</html:select>
							<input type="hidden" value="${rs.yesNo}" id="yesNoOld"/>
						</td>
						<logic:equal name="isFdy" value="true">
						<td  align="right">
							辅导员:
						</td>
						<td>
							<html:text property="fdyname" readonly="true" value="${fdyname}"/>
						</td>
						</logic:equal>
						</tr>	
						<tr>
						<td id="tdGw" style="display:none" align="right">
							岗位：
						</td>	
						<td id="tdGwdm" style="display:none">
							<html:select property="kcjqgzxsj" styleId="gwdm" onchange="getValue()">
							<html:option value=""></html:option>
							<html:options collection="gwList" property="gwdmgwsbsj" labelProperty="gwdm"/>
							</html:select> 
						</td>
						</tr>
						<tr id="trGw" style="display:none">
							<td align="right">
								指定给学生的岗位：
							</td>
							<td colspan="3">
								<html:textarea property="gwdm" styleId="zdgw" rows="4" readonly="true" style="width:100%">
								</html:textarea>
								<input type="hidden" id="xmdm" name="xmdm"/>
							</td>
						</tr>
						</logic:equal>
						<!--end广州大学-->

						<!--非广州大学-->	
						<logic:notEqual value="11078" name="xxdm">
						<tr>					
						<td align="right">
							审核：
						</td>
						<td colspan="3">
							<html:select property="yesNo" name="rs" styleId="yesNo" onchange="changeYesNo()">
							<html:option value=""></html:option>
							<html:options collection="chkList" property="en" labelProperty="cn" />
							</html:select>
							<input type="hidden" value="${rs.yesNo}" id="yesNoOld"/>
						</td>							
						</tr>						
						</logic:notEqual>	
						<!--end非广州大学-->									
					</table>
				</logic:notEmpty>
				<logic:equal value="yes" name="writeAble" scope="request">	
					<center>
						<div class="buttontool" id="btn" width="80%">
							<logic:equal value="11078" name="xxdm">
								<button type="button" class="button2" onclick="czgw()" style="width:80px">
									重置岗位
								</button>
								&nbsp;&nbsp;&nbsp;
							</logic:equal>
							<!-- 广州大学权限判断 -->
							<logic:equal name="xxdm" value="11078">
								<logic:notEqual name="cansee" value="no">
								<button type="button" class="button2" id="btn_add"
									onclick="save();return false;"
									style="width:80px">
									保 存
								</button>
							&nbsp;&nbsp;&nbsp;
							</logic:notEqual>
							</logic:equal>
							<!-- 广州大学权限判断 end -->
							
							<logic:notEqual name="xxdm" value="11078">
								<button type="button" class="button2" id="btn_add"
									onclick="save();return false;"
									style="width:80px">
									保 存
								</button>
							&nbsp;&nbsp;&nbsp;
							</logic:notEqual>
							<button type="button" class="button2" id="btn_edit"
								onclick="Close();return false;"
								style="width:80px">
								关 闭
							</button>								
							</div>					
					</center>
				</logic:equal>

				<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功!');
						Close();
						window.dialogArguments.document.getElementById('search_go').click();
					</script>	
					</logic:equal>
					<logic:equal value="false" name="result">
					<logic:notEmpty name="mes">
					<script>
						alert(document.getElementById('mes').value);						
						Close();
					</script>
					</logic:notEmpty>
					<logic:empty name="mes">
					<script>
						alert("操作失败！");				
						Close();
					</script>
					</logic:empty>
				</logic:equal>	
			</logic:present>			
		</html:form>	
	</body>
</html>
