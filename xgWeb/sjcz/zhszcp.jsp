<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>

	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>	
	<script type="text/javascript">
		function ckdata5(tmp,tp) {
			var tmps = document.getElementById(tmp).value;
			if (tp != '') {
			if (parseFloat(tmps) > parseFloat(tp)) {
				alert('����ɼ����ܳ���'+tp+'�֣�');
				document.getElementById(tmp).value = '';
				return;
			}
			}
		}
		// ͨ�ü����ܷ�
		function countzf() {
			var dcj = document.getElementById('dcj').value;
			var zcj = document.getElementById('zcj').value;
			var tcj = document.getElementById('tcj').value;
			if(dcj == null || dcj == '' || dcj == ' '){
				dcj = '0';
			}
			if(zcj == null || zcj == '' || zcj == ' '){
				zcj = '0';
			}
			if(tcj == null || tcj == '' || tcj == ' '){
				tcj = '0';
			}
			pjpyscjzJs.countZf(dcj,zcj,tcj,function (data) {
				if(parseFloat(data) == data) {
					data = Math.round(data * 100) / 100; 
				}
				document.getElementById('zpf').value=data;
			});
		}
	</script>
</head>
	<body onload="checkWinType();">
		<script type='text/javascript' src='/xgxt/dwr/interface/pjpyscjzJs.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="js/sharedFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<html:form action="/data_search" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a><span id="tipFollow"></span></a>
				</p>
			</div>
			
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("�������ѧ����Ч!");
    </script>
				</logic:equal>
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="userOnLine" name="userOnLine" value="<bean:write name="userOnLine" scope="session"/>" />
				<input type="hidden" id="url" name="url" value="/sjcz/zhszcp.jsp" />
				<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
				<logic:notPresent name="isgdby">
					<div class="tab">
					<table width="100%" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�ۺ����ʲ�����Ϣά��</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<td align="right">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left">
							<html:text name='rs' property="xh" maxlength="20"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu" >
								ѡ��
							</button>
						</td>
						<td align="right">
							<font color="red">*</font>���
						</td>
						<td align="left">
							<html:select name="rs" property="nd" style="width:90px"
								styleId="nd">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							����
						</td>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
						</td>
						<td align="right">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left">
							<html:select name="rs" property="xn" style="width:90px"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							�Ա�
						</td>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
						</td>
						<logic:notEqual value="13742" name="xxdm">
						<td align="right">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left">
							<html:select name="rs" property="xq" style="width:90px"
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
						</logic:notEqual>
					</tr>
					<!-- ������ -->
					<logic:equal value="yes" name="shownbng">
						<tr>
						<td align="right">
							�꼶
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" />
						</td>
						
								<%--<td align="right">
									�³ɼ�
								</td>
								<td align="left">
									<html:text name="rs" property="dcj" style="width:90px" onblur="ckdata(this);countdcj()" maxlength="5" styleId="dcj"></html:text>
								</td>
							
					--%>
					<td align="right">
											��������ɼ�
										</td>
										<td align="left">
											<input type="text" id="ddcj" name="ddcj" value="<bean:write name="rs" property="ddcj"/>" maxlength="5" onblur="ckdata(this);ckdata5('ddcj',10)"/>
										</td>
					</tr>
					<tr>
						<td align="right">
							
						</td>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xy" readonly="true"/>
						</td>
						
								<%--<td align="right">
									�ǳɼ�
								</td>
										<td align="left">
									<input type="text" name="zcj" onblur="ckdata(this);"
										value="<bean:write name="rs" property="zcj"/>"
										style="width:90px" id="zcj" maxlength="5" />
								</td>
					--%>
					<td align="right">
											��Ϊ��ʵ�ɼ�
										</td>
										<td align="left">
											<input type="text" id="xwcj" name="xwcj" value="<bean:write name="rs" property="xwcj"/>" maxlength="5" onblur="ckdata(this);ckdata5('xwcj',10)"/>
										</td>
					</tr>
					<tr>
						<td align="right">
							רҵ
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zy" readonly="true"/>
						</td>
						<%--<td align="right">
									��ɼ�
								</td>
								<td align="left">
									<html:text name="rs" property="tcj" maxlength="5" style="width:90px" onblur="ckdata(this);" styleId="tcj"></html:text>
								</td>
					--%>
					<td align="right">
											����԰���ɼ�
										</td>
										<td align="left">
											<input type="text" id="shqcj" name="shqcj" value="<bean:write name="rs" property="shqcj"/>" maxlength="5" onblur="ckdata(this);ckdata5('shqcj',5)"/>
										</td>
					</tr>
					</logic:equal>
					<logic:notPresent name="shownbng">
						<tr>
						<td align="right">
							�꼶
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
						</td>
						<logic:present name="showzszy">
							<td align="right">
								��������
							</td>
							<td align="left">
								<html:text name="rs" property="dcj" styleId="dcj" onblur="countCpzf()"/>
							</td>
						</logic:present>
						<logic:notPresent name="showzszy">
							<logic:present name="showhzy">
								<td align="right">
									�³ɼ�
								</td>
								<td align="left">
									<html:text name="rs" property="xydykpf" styleId="xydykpf" style="width:90px"></html:text>
								</td>
							</logic:present>
							<logic:notPresent name="showhzy">
								<logic:equal name="xxdm" value="10402" scope="session"><!--����ʦ�� -->
								<td align="right">
									˼�����������ܻ���
								</td>
								<td align="left">
									<html:text name="rs" property="dcj" style="width:90px"
										styleId="dcj" maxlength="5" onblur="checkInputCj(this)"/>
								</td>
								</logic:equal>
								<logic:notEqual value="10402" name="xxdm" scope="session">
								<td align="right">
									
									<logic:equal value="12682" name="xxdm">
									��Ϊ�淶
									</logic:equal>
									<logic:notEqual value="12682" name="xxdm">
										�³ɼ�
									</logic:notEqual>
								</td>
								<td align="left">
									<logic:present name="isAHJG">
										<html:text name="rs" property="dcj" style="width:90px" onblur="ckdata(this);countzf1();" maxlength="5" styleId="dcj"></html:text>
									</logic:present>
									<logic:notPresent name="isAHJG">
										<html:text name="rs" property="dcj" style="width:90px" onblur="ckdata(this);countzf()" maxlength="5" styleId="dcj"></html:text>
									</logic:notPresent>
								</td>
								</logic:notEqual>
							</logic:notPresent>
						</logic:notPresent>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xb" />
						</td>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xy" readonly="true"/>
						</td>
						<logic:present name="showzszy">
							<td align="right">
								��������
							</td>
							<td align="left">
								<html:text name="rs" property="zcj" styleId="zcj" onblur="countCpzf()" maxlength="5" onblur="checkInputCj(this)"/>
							</td>
						</logic:present>
						<logic:notPresent name="showzszy">
							<logic:present name="showhzy">
								<td align="right">
									��Ԣ�³ɼ�
								</td>
								<td align="left">
									<html:text name="rs" property="gydykpf" styleId="gydykpf" style="width:90px"></html:text>
								</td>
							</logic:present>
							<logic:notPresent name="showhzy">
							    <logic:equal name="xxdm" value="10402" scope="session"><!--����ʦ�� -->
							    <td align="right">
									ѧϰ�ܻ���
								</td>
								<td align="left">
									<input type="text" name="zcj"
										value="<bean:write name="rs" property="zcj"/>"
										style="width:90px" id="zcj" maxlength="5" onblur="checkInputCj(this)"/>
								</td>
							    </logic:equal>
							    <logic:notEqual name="xxdm" value="10402" scope="session">
								<td align="right">
									<logic:equal value="12682" name="xxdm">
									�Ļ��ɼ�
									</logic:equal>
									<logic:notEqual value="12682" name="xxdm">
										�ǳɼ�
									</logic:notEqual>
									
								</td>
									<logic:present name="isAHJG">
										<td align="left">
									<input type="text" name="zcj"
										value="<bean:write name="rs" property="zcj"/>"
										style="width:90px" id="zcj" maxlength="5" onblur="ckdata(this);countzf1();"/>
								</td>
									</logic:present>
									<logic:notPresent name="isAHJG">
										<td align="left">
									<input type="text" name="zcj"
										value="<bean:write name="rs" property="zcj"/>"
										style="width:90px" onblur="ckdata(this);countzf()" maxlength="5" id="zcj" maxlength="5" />
								</td>
									</logic:notPresent>
								</logic:notEqual>
							</logic:notPresent>
						</logic:notPresent>
					</tr>
					<tr>
						<td align="right">
							רҵ
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zy" readonly="true"/>
						</td>
						<logic:present name="showzszy">
							<td align="right">
								��������
							</td>
							<td align="left">
								<html:text name="rs" property="tcj" styleId="tcj" onblur="countCpzf()"/>
							</td>
						</logic:present>
						<logic:notPresent name="showzszy">
							<logic:present name="showhzy">
								<td align="right">
									�ǳɼ�
								</td>
								<td align="left">
									<input type="text" name="zcj"
										value="<bean:write name="rs" property="zcj"/>"
										style="width:90px" id="zcj"/>
								</td>
							</logic:present>
							<logic:notPresent name="showhzy">
								<logic:equal name="xxdm" value="10402" scope="session"><!--����ʦ�� -->
								<td align="right">
									���������ܻ���
								</td>
								<td align="left">
									<html:text name="rs" property="znszcj" style="width:90px"
										styleId="znszcj" maxlength="5" onblur="checkInputCj(this)"/>
								</td>
								</logic:equal>
								<logic:notEqual name="xxdm" value="10402" scope="session">
								<td align="right">
									�����ɼ�
								</td>
								<td align="left">
								<logic:present name="isAHJG">
									<html:text name="rs" property="tcj" maxlength="5" style="width:90px" onblur="ckdata(this);countzf1();" styleId="tcj"></html:text>
								</logic:present>
								<logic:notPresent name="isAHJG">
									<html:text name="rs" property="tcj" maxlength="5" style="width:90px" onblur="ckdata(this);countzf()" styleId="tcj"></html:text>
								</logic:notPresent>
								</td>
								</logic:notEqual>
							</logic:notPresent>
						</logic:notPresent>
					</tr>
					</logic:notPresent>
					<tr>
						<td align="right">
							�༶
						</td>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bj" readonly="true"/>
						</td>
						<!-- ������ -->
						<logic:equal value="yes" name="shownbng">
							<td align="right">
								��Ȩƽ����	
							</td>
							<td align="left">
							<input type="text" name="kcjqpfj" id="kcjqpfj" style="width:90px" onblur="ckdata(this)" value="<bean:write name="rs" property="kcjqpfj"/>" maxlength="5"/>
							<input type="hidden" name="zhszcpzf" id="zhszcpzf" value=""/>
							</td>
						</logic:equal>
						<!-- ���ս��� -->
						<logic:present name="isAHJG">
							<td align="right">
								�ۺ����ʲ�����	
							</td>
							<td align="left">
							<input type="text" name="zhszcpzf" id="cpf" style="width:90px" value="<bean:write name="rs" property="zhszcpzf"/>"/>
							</td>
						</logic:present>
						<logic:present name="showzszy">
							<td align="right">
								���ܼӷ�
							</td>
							<td align="left">
								<html:text name="rs" property="jnjf" styleId="jnf" onblur="countCpzf()"/>
							</td>
						</logic:present>
						<logic:present name="showhzy">
							<td align="right">
									��ɼ�
							</td>
							<td align="left">
								<html:text name="rs" property="tcj" styleId="tcj" maxlength="5" onblur="ckdata(this)" style="width:90px"></html:text>
							</td>
						</logic:present>
						
						<logic:equal name="xxdm" value="10402" scope="session"><!--����ʦ�� -->
						<td align="right">
							���������ܻ���
						</td>
						<td align="left">
							<html:text name="rs" property="tcj" style="width:90px"
								styleId="tcj" maxlength="5"  onblur="checkInputCj(this)"/>
						</td>
						</logic:equal>
						<logic:notEqual name="xxdm" value="10402" scope="session">
							<logic:notEqual value="10878" name="xxdm">
								<logic:notEqual value="12872" name="xxdm">
								
										<td align="right">
										�ܳɼ�
										</td>
										<td align="left">
											<html:text name="rs" property="zpf" readonly="true" styleId="zpf" maxlength="5" style="width:90px"></html:text>
										</td>
								</logic:notEqual>
							</logic:notEqual>
						</logic:notEqual>
					</tr>
					<logic:present name="showhzy">
						<tr>
							<td align="right">
							</td>
							<td>
							</td>
							<td align="right">
								ѧϰ����
							</td>
							<td align="left">
								<html:text name="rs" property="gzxxcx" styleId="gzxxcx" style="width:90px"></html:text>
							</td>
						</tr>
					</logic:present>
					<logic:present name="showzszy">
						<tr>
							<td align="right">
								�����ܷ�
							</td>
							<td align="left">
								<html:text name="rs" property="cpzf" styleId="cpf" readonly="true"/>
							</td>
						</tr>
					</logic:present>
					<logic:equal name="xxdm" value="10402" scope="session">
						<tr>
							<td align=right>�ӷ�</td>
							<td><html:text name="rs" property="jnjf" styleId="jnjf" onblur="checkInputCj(this)"/></td>
							<td align="right">
								�����ܷ�
							</td>
							<td align="left">
								<html:text name="rs" property="cpzf" styleId="cpzf" readonly="true"/>
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="yes" name="shownbng">
						<%--<tr align="center">
								<td bgcolor="#CCCCCC" colspan="4">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>�����ɼ���ϸ</strong>
											</div>
										</div>
									
							</td>
						</tr>
						--%><tr>
						<td colspan="4">
							<div id="child4" style="display:none" class="style2">
								<table width="100%" border="1" align="center" class="tbstyle">
									<tr style="width:20px">
										
										
									</tr>
									<tr style="width:20px">
										
										<td align="right">
											&nbsp;
										</td>
										<td align="left">
											&nbsp;
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					
					<tr align="center">
								<td bgcolor="#CCCCCC" colspan="4">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child5.style.display=(document.all.child5.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>�ۺ����ʳɼ�������</strong>
											</div>
										</div>
									
							</td>
						</tr>
						<tr>
						<td colspan="4">
							<div id="child5" style="display:none" class="style2">
								<table width="100%" border="1" align="center" class="tbstyle">
									<tr style="height:20px">
										<td align="right" style="width:120px">
											�ۺ����ʲ����ܷ�
										</td>
										<td align="left">
											${zhszcpzf }
										</td>
										<td align="right">
											�༶����
										</td>
										<td align="left" style="width:150px">
											${zhpm }
										</td>
									</tr>
									<tr style="">
										<td align="right" style="width:120px">
											���������ܷ�
										</td>
										<td align="left">
											${dcj }
										</td>
										<td align="right">
											�༶����
										</td>
										<td align="left" style="width:150px">
											${dcjpm }
										</td>
									</tr>
									<tr style="">
										<td align="right" style="width:120px">
											��Ȩ�ɼ�
										</td>
										<td align="left">
											
										</td>
										<td align="right">
											רҵ����
										</td>
										<td align="left" style="width:150px">
											${jpfpm }
										</td>
										</tr>
								</table>
							</div>
						</td>
					</tr>
					</logic:equal>
					<!-- ���ս��� -->
					<logic:present name="isAHJG">
						<tr align="center">
								<td bgcolor="#CCCCCC" colspan="4">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child0.style.display=(document.all.child0.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>˼����ֵ÷���ϸ</strong>
											</div>
										</div>
								</td>
						</tr>
						<tr>
							<td colspan="4">
								<div id="child0" style="display:none" class="style2">
								<table width="100%" border="1" align="center" class="tbstyle">
									<tr style="width:20px">
										<td align="right">
											�й���ʽ��Ա
										</td>
										<td align="left">
											<html:text name="rs" property="zgdy" styleId="zgdy" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
										<td align="right">
											�й�Ԥ����Ա
										</td>
										<td align="left">
											<html:text name="rs" property="zgybdy" styleId="zgybdy" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											���ڰ༶�Ƿ��Ƚ��༶
										</td>
										<td align="left">
											<html:text name="rs" property="xjbj" styleId="xjbj" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
										<td align="right">
											���������Ƿ���������
										</td>
										<td align="left">
											<html:text name="rs" property="wmss" styleId="wmss" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
									</tr>
								</table>
							</div>
							</td>
						</tr>
						<tr align="center">
								<td bgcolor="#CCCCCC" colspan="4">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child1.style.display=(document.all.child1.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>��Ṥ���÷���ϸ</strong>
											</div>
										</div>
								</td>
						</tr>
						<tr>
							<td colspan="4">
								<div id="child1" style="display:none" class="style2">
								<table width="100%" border="1" align="center" class="tbstyle">
									<tr style="width:20px">
										<td align="right">
											Уѧ������ϯ������ϯ<br>��ίίԱ������֧�����
										</td>
										<td align="left">
											<html:text name="rs" property="xsgb1" styleId="xsgb1" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
										<td align="right">
											Уѧ��������������ϵѧ������<br>����ϯ������֧ίԱ��Уѧ������
										</td>
										<td align="left">
											<html:text name="rs" property="xsgb2" styleId="xsgb2" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											ϵѧ��������������<br>�೤����֧�����
										</td>
										<td align="left">
											<html:text name="rs" property="xsgb3" styleId="xsgb3" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
										<td align="right">
											����ѧ���ɲ�
										</td>
										<td align="left">
											<html:text name="rs" property="xsgb4" styleId="xsgb4" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
									</tr>
								</table>
							</div>
							</td>
						</tr>
						<tr align="center">
								<td bgcolor="#CCCCCC" colspan="4">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child3.style.display=(document.all.child3.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>Ӣ������ˮƽ�÷���ϸ</strong>
											</div>
										</div>
								</td>
						</tr>
						<tr>
							<td colspan="4">
								<div id="child3" style="display:none" class="style2">
								<table width="100%" border="1" align="center" class="tbstyle">
									<tr style="width:20px">
										<td align="right">
											��ѧӢ���ġ�����
										</td>
										<td align="left">
											<html:text name="rs" property="wysp" styleId="wysp" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
										<td align="right">
											���Ҽ��������������
										</td>
										<td align="left">
											<html:text name="rs" property="jsjsp" styleId="jsjsp" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
									</tr>
								</table>
							</div>
							</td>
						</tr>
						<tr align="center">
								<td bgcolor="#CCCCCC" colspan="4">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>У��ѧ��÷���ϸ</strong>
											</div>
										</div>
								</td>
						</tr>
						<tr>
							<td colspan="4">
								<div id="child4" style="display:none" class="style2">
								<table width="100%" border="1" align="center" class="tbstyle">
									<tr style="width:20px">
										<td align="right">
											ѧУһ�Ƚ�ѧ��
										</td>
										<td align="left">
											<html:text name="rs" property="xjjxj1" styleId="xjjxj1" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
										<td align="right">
											ѧУ���Ƚ�ѧ��
										</td>
										<td align="left">
											<html:text name="rs" property="xjjxj2" styleId="xjjxj2" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											ѧУ���Ƚ�ѧ��
										</td>
										<td align="left">
											<html:text name="rs" property="xjjxj3" styleId="xjjxj3" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
										<td align="right">
											&nbsp;
										</td>
										<td align="left">
											&nbsp;
										</td>
									</tr>
								</table>
								</div>
							</td>
						</tr>
						<tr align="center">
								<td bgcolor="#CCCCCC" colspan="4">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child5.style.display=(document.all.child5.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>���������÷���ϸ</strong>
											</div>
										</div>
								</td>
						</tr>
						<tr>
							<td colspan="4">
								<div id="child5" style="display:none" class="style2">
								<table width="100%" border="1" align="center" class="tbstyle">
									<tr style="width:20px">
										<td align="right">
											���Ҽ�����һ��
										</td>
										<td align="left">
											<html:text name="rs" property="gjjl1" styleId="gjjl1" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
										<td align="right">
											���Ҽ���������
										</td>
										<td align="left">
											<html:text name="rs" property="gjjl2" styleId="gjjl2" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											���Ҽ���������
										</td>
										<td align="left">
											<html:text name="rs" property="gjjl3" styleId="gjjl3" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
										<td align="right">
											ʡ������һ��
										</td>
										<td align="left">
											<html:text name="rs" property="sjjl1" styleId="sjjl1" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											ʡ����������
										</td>
										<td align="left">
											<html:text name="rs" property="sjjl2" styleId="sjjl2" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
										<td align="right">
											ʡ����������
										</td>
										<td align="left">
											<html:text name="rs" property="sjjl3" styleId="sjjl3" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
									</tr>
									<tr style="width:20px">
										<td align="right">
											У����ѧ����<br>����ѧ���ɲ���������Ա(��)
										</td>
										<td align="left">
											<html:text name="rs" property="xjgr1" styleId="xjgr1" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
										<td align="right">
											�����������
										</td>
										<td align="left">
											<html:text name="rs" property="xjgr2" styleId="xjgr2" styleClass="inputtext" maxlength="5" onblur="ckdata(this);countLhdf();"></html:text>
										</td>
									</tr>
								</table>
								</div>
							</td>
						</tr>
						<tr align="">
							<td align="right">������׼�ܷ�</td>
							<td align="left"><html:text name="rs" property="zf" styleId="zf" styleClass="inputtext"></html:text></td>
							<td>&nbsp;<input type="hidden" name="zxq" id="zxq" value="<bean:write name="rs" property="zxq"/>"/> </td>
							<td>&nbsp;</td>
						</tr>
					</logic:present>
					<tr align="left">
						<td align="right">
							��ע
							<br/>
							<font color="red">������1000����&nbsp;&nbsp;</font>
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='bz' style="width:550px;overflow:auto" rows='5'  onkeyup="checkLen(this,1000)"/>
						</td>
					</tr>
					<tfoot>
					<tr><td colspan="4">
					<div class="btn">
						
					<logic:present name="showhzy">
						<button type="button" class=""
							onclick="if(checkXnNd('xn','nd') && judgeChar())dataDoSave('xn-xq-xh');" id="buttonSave">
							�� ��
						</button>
					</logic:present>
					<logic:notPresent name="showhzy">
						<logic:notEqual value="view" name="doType">
						<button type="button" class=""
							onclick="if(checkXnNd('xn','nd'))dataDoSave('xn-xq-xh');" id="buttonSave">
							�� ��
						</button>
						</logic:notEqual>
					</logic:notPresent>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="" onclick="Close();return false;"
						id="buttonClose">
						�� ��
					</button>
					</div>
					</td>
					</tr>
					</tfoot>
					</tbody>
				</table>
				</div>
				</logic:notPresent>
				<!-- �㶫����<bean:message key="lable.xsgzyxpzxy" /> -->
				<logic:equal value="yes" name="isgdby">
					<div class="tab">
					<table width="100%" class="formlist">
					<thead>
						<tr>
							<td colspan="4" align="center">
								�ۺ����ʲ�����Ϣά��
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left">
							<html:text name='rs' property="xh" readonly="readonly"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu" style="display:none">
								ѡ��
							</button>
						</td>
						<td align="right">
							<font color="red">*</font>���
						</td>
						<td align="left">
							<html:select name="rs" property="nd" style="width:90px"
								styleId="nd">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							����
						</td>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
						</td>
						<td align="right">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left">
							<html:select name="rs" property="xn" style="width:90px"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							�Ա�
						</td>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
						</td>
						<td align="right">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left">
							<html:select name="rs" property="xq" style="width:90px"
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							�꼶
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" readonly="true" />
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td align="right">
							
						</td>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xy" readonly="true"/>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td align="right">
							רҵ
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zy" readonly="true"/>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td align="right">
							�༶
						</td>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bj" readonly="true"/>
						</td>
						<td align="right">���гɼ�</td>
						<td align="left">
							<html:text name='rs' property="cxcj" styleId="cxcj" maxlength="6"></html:text>
						</td>
					</tr>
					<tr align="left">
						<td align="right">
							��ע
						</td>
						<td colspan="3">ea<html:textarea name='rs' property='bz' style="width:99%" rows='5' />
						</td>
					</tr>
					<tfoot>
					<tr><td colspan="4">
					<div class="btn">
						
					<logic:present name="showhzy">
						<button type="button" class=""
							onclick="if(checkXnNd('xn','nd') && judgeChar())dataDoSave('xn-xq-xh');" id="buttonSave">
							�� ��
						</button>
					</logic:present>
					<logic:notPresent name="showhzy">
						<button type="button" class=""
							onclick="if(checkXnNd('xn','nd'))dataDoSave('xn-xq-xh');" id="buttonSave">
							�� ��
						</button>
					</logic:notPresent>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="" onclick="Close();return false;"
						id="buttonClose">
						�� ��
					</button>
					</div>
					</td>
					</tr>
					</tfoot>
				</table>
				</logic:equal>
			
					
				
			</logic:notEmpty>

  			<logic:equal value="true" name="isSuccess">
			  <script type="text/javascript">
			    alert('�����ɹ���');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="isSuccess">
			  <script type="text/javascript">
			    alert('����ʧ�ܣ�');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>
		</html:form>
	</body>
	<script language="javascript">
	  //����ʦ���Ĳ����ּܷ���
	  initCpzf();
	  //��������������ɼ�
	  function countDycj(){
	  	var ddcj = document.getElementById('ddcj').value;
	  	var xwcj = document.getElementById('xwcj').value;
	  	var shqcj = document.getElementById('shqcj').value;
	  	if(ddcj == null || ddcj == "" || ddcj == " "){
			ddcj = '0';
			document.getElementById('dcj').value = "";
		}
		if(xwcj == null || xwcj == "" || xwcj == " "){
			xwcj = '0';
			document.getElementById('dcj').value = "";
		}
		if(shqcj == null || shqcj == "" || shqcj == " "){
			shqcj = '0';
			document.getElementById('dcj').value = "";
		}
		document.getElementById('dcj').value = parseFloat(ddcj)*10/100 + parseFloat(xwcj)*10/100 + parseFloat(shqcj)*5/100;
	  }
	  function countdcj(){
	  	var ddcj = document.getElementById('ddcj').value;
	  	var xwcj = document.getElementById('xwcj').value;
	  	var shqcj = document.getElementById('shqcj').value;
	  	var dcj = document.getElementById('dcj').value;
	  	if(ddcj == null || ddcj == "" || ddcj == " "){
			ddcj = '0';
			
		}
		if(xwcj == null || xwcj == "" || xwcj == " "){
			xwcj = '0';
			
		}
		if(shqcj == null || shqcj == "" || shqcj == " "){
			shqcj = '0';
			
		}
		var dcj1 = parseFloat(ddcj)*10/100 + parseFloat(xwcj)*10/100 + parseFloat(shqcj)*5/100;
		if (parseFloat(dcj) != 0 && parseFloat(dcj1) && parseFloat(dcj)!=parseFloat(dcj1)) {	
			alert('�����ɼ�����������ȷ�ϣ�');
			document.getElementById('dcj').value=parseFloat(ddcj)*10/100 + parseFloat(xwcj)*10/100 + parseFloat(shqcj)*5/100 ;
		}
	  }
	</script>
</html>
