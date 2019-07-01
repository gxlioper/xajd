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
		<meta name="Copyright" content="������� zfsoft" />
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
				alert("��ѡ��һ��Ҫ�޸ĵļ�¼��");
				return false;
			}
			var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
			refreshForm(url+pkValue,w,h);
		}			
		
		function save(){
			var oldFlag = val("yesNoOld");
			var flag = val("yesNo");
			var xxdm = $("xxdm").value;
			//�ǹ��ݴ�ѧ
			if(xxdm != "11078"){
				if(oldFlag == flag){
					alert("��˽��û�з����ı䣡");
					return false;
				}
			}
			showTips('���������У���ȴ�......');
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
		
		//�ı���˽��
		function changeYesNo(value){
			if(value == 'ͨ��'){
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
				alert('�ø�λ�Ѿ�ָ����');
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
			//�ж�ָ���ĸ�λ�Ƿ��Ѿ���Ч
			var yzdgw = val('yzdgwdmgwsbsj');
			var xh = val('xh');
			cqkjFunc.checkZdgwExists(xh,yzdgw,function(data){
				if(data != null && data.gwdm != null && data.gwdm != ""){
					setVal('zdgw',data.gwdm);
					setVal('xmdm',data.gwsbsj);
				}
			});
			
		}
	
	//���ø�λ��Ϣ
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
				��ǰλ�ã��ڹ���ѧ - ��� - �ڹ���ѧ�������
			</div>
			</div>				
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
						<tr>
						<td colspan="4" align="center">
							ѧ���ڹ���ѧ�������
							</td>
							</tr>
						</thead>
						<tr>
						<td align="right">
							ѧ�ţ�
						</td>
						<td>
							<bean:write name="rs" property="xh"/>
							<html:hidden property="xh" name="rs"/>
						</td>
						<td align="right">
							ѧ�꣺
						</td>
						<td>
							<bean:write name="rs" property="xn"/>
							<html:hidden property="xn" name="rs"/>
						</td>
						</tr>
						<tr>
						<td align="right">
							������
						</td>
						<td>
							<bean:write name="rs" property="xm"/>
						</td>
						<td align="right">
							��ȣ�
						</td>
						<td>
							<bean:write name="rs" property="nd"/>
							<html:hidden property="nd" name="rs"/>
						</td>
						</tr>	
						<tr>
						<td align="right">
							�Ա�
						</td>
						<td>
							<bean:write name="rs" property="xb"/>
						</td>
						<td align="right">
							ѧ�ڣ�
						</td>
						<td>
							<bean:write name="rs" property="xqmc"/>
							<html:hidden property="xq" name="rs"/>
						</td>
						</tr>	
						<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />���ƣ�
						</td>
						<td>
							<bean:write name="rs" property="xymc"/>
						</td>
						<td align="right">
							��ϵ�绰��
						</td>
						<td>
							<bean:write name="rs" property="lxdh"/>
						</td>
						</tr>	
						
						<tr>
							<td align="right">
								�༶���ƣ�
							</td>
							<td>
								<bean:write name="rs" property="bjmc"/>
							</td>
							<td align="right">
								�����ţ�
							</td>
							<td>
								<bean:write name="rs" property="ssbh"/>
							</td>
						</tr>
						<tr>
						<td align="right">
							�к��س���
						</td>
						<td id="yhtcTd">
							<bean:write name="rs" property="yhtc"/>
						</td>	
						<td align="right">
							��λ���ʣ�
						</td>
						<td colspan="3">
							<bean:write name="rs" property="gwxzmc"/>
							<html:hidden name="rs" styleId="gwxzdm" property="gwxzdm"/>
						</td>
						</tr>
						<tr>
						<td align="right">
							ѧ���ɲμ��ڹ���ѧʱ�䣺
						</td>
						<logic:present name="kxList">
							<td colspan="3">
								<table border="0" cellpadding="0" cellspacing="0" align="center">
									<tr>
										<td align="center">
											ʱ��
										</td>
										<td>
											����һ
										</td>
										<td>
											���ڶ�
										</td>
										<td>
											������
										</td>
										<td>
											������
										</td>
										<td>
											������
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
							��ͥ���������
						</td>
						<td colspan="3">
							<bean:write name="rs" property="jjqk"/>
						</td>							
						</tr>	
						<tr>
						<td align="right">
							�ɴ��¹�����
						</td>
						<td colspan="3">
							<bean:write name="rs" property="kcsgz"/>
						</td>							
						</tr>
						<tr>
						<td align="right">
							��ע��
						</td>
						<td colspan="3" id="bzTd">
							<bean:write name="rs" property="bz"/>
						</td>							
						</tr>
						<tr>
						<td colspan="4" align="center" bgcolor="D0E0EE" onclick="ele('xscjTable').style.display = ele('xscjTable').style.display=='none' ? '' : 'none'">
							<b style="cursor:hand" title="��ʾ/����">ѧ���ɼ���Ϣ</b>
						</td>
						</tr>
						<tr style="display:none" id="xscjTable">
							<td colspan="4">
								<table  width="100%" class="tbstyle">
										<thead >
											<tr align="center">
												<td align="center">ѧ��</td>
												<td align="center">���</td>
												<td align="center">ѧ��</td>
											    <td align="center">�γ�����</td>
												<td align="center">�ɼ�</td>
												<td align="center">�����ɼ�</td>
												<td align="center">���޳ɼ�</td>
												<td align="center">�����ɼ�</td>
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
							<b style="cursor:hand" title="��ʾ/����">ѧ��Υ����Ϣ</b>
						</td>
						</tr>
						<tr style="display:none" id="xswjTable">
							<td colspan="4">
								<table  width="100%" class="tbstyle">
										<thead >
											<tr align="center">
												<td align="center">ѧ��</td>
												<td align="center">���</td>
												<td align="center">ѧ��</td>
											    <td align="center">�����ĺ�</td>
												<td align="center">Υ��ʱ��</td>
												<td align="center">����ԭ��</td>
												<td align="center">�������</td>
												<td align="center">���ּ���</td>
												<td align="center">����ĺ�</td>
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
						<!--���ݴ�ѧ-->
						<logic:equal value="11078" name="xxdm">
						<tr>					
						<td align="right">
							��ˣ�
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
							����Ա:
						</td>
						<td>
							<html:text property="fdyname" readonly="true" value="${fdyname}"/>
						</td>
						</logic:equal>
						</tr>	
						<tr>
						<td id="tdGw" style="display:none" align="right">
							��λ��
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
								ָ����ѧ���ĸ�λ��
							</td>
							<td colspan="3">
								<html:textarea property="gwdm" styleId="zdgw" rows="4" readonly="true" style="width:100%">
								</html:textarea>
								<input type="hidden" id="xmdm" name="xmdm"/>
							</td>
						</tr>
						</logic:equal>
						<!--end���ݴ�ѧ-->

						<!--�ǹ��ݴ�ѧ-->	
						<logic:notEqual value="11078" name="xxdm">
						<tr>					
						<td align="right">
							��ˣ�
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
						<!--end�ǹ��ݴ�ѧ-->									
					</table>
				</logic:notEmpty>
				<logic:equal value="yes" name="writeAble" scope="request">	
					<center>
						<div class="buttontool" id="btn" width="80%">
							<logic:equal value="11078" name="xxdm">
								<button type="button" class="button2" onclick="czgw()" style="width:80px">
									���ø�λ
								</button>
								&nbsp;&nbsp;&nbsp;
							</logic:equal>
							<!-- ���ݴ�ѧȨ���ж� -->
							<logic:equal name="xxdm" value="11078">
								<logic:notEqual name="cansee" value="no">
								<button type="button" class="button2" id="btn_add"
									onclick="save();return false;"
									style="width:80px">
									�� ��
								</button>
							&nbsp;&nbsp;&nbsp;
							</logic:notEqual>
							</logic:equal>
							<!-- ���ݴ�ѧȨ���ж� end -->
							
							<logic:notEqual name="xxdm" value="11078">
								<button type="button" class="button2" id="btn_add"
									onclick="save();return false;"
									style="width:80px">
									�� ��
								</button>
							&nbsp;&nbsp;&nbsp;
							</logic:notEqual>
							<button type="button" class="button2" id="btn_edit"
								onclick="Close();return false;"
								style="width:80px">
								�� ��
							</button>								
							</div>					
					</center>
				</logic:equal>

				<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ�!');
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
						alert("����ʧ�ܣ�");				
						Close();
					</script>
					</logic:empty>
				</logic:equal>	
			</logic:present>			
		</html:form>	
	</body>
</html>
