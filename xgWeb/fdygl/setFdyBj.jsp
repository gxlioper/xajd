<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<head>
		<!-- ͷ�ļ� -->
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/commanFunction.js"></script>
		<script language="javascript" src="js/fdyglFunction.js"></script>
		<script>
			function dataExport() {
				document.forms[0].action = "expData.do";
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
			}
			
			function changeFdyOrBzr(){
			
				var szdwbb=document.getElementsByName("szdwbb");
				
				for(var i=0;i<szdwbb.length;i++){
					
					if(szdwbb[i].checked){
						if(szdwbb[i].value=="fdy"){
							$("tableName").value="view_fdybjdz";
							$("realTable").value="fdybjb";
						}else {
							$("tableName").value="xg_view_szdw_bzrbjdz";
							$("realTable").value="bzrbbb";
						}
						
					}
				}
			}
		</script>
	</head>

	<body onload="initSetFdyBj();xyDisabled('xy');changeFdyOrBzr();">
		<html:form action="/setPfbz" method="post">
			<div class="tab_cur" id="title_m">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>

			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="session"/>"/>
			<input type="hidden" id="tableName" name="tableName" value="view_fdybjdz"/>
			<input type="hidden" id="realTable" name="realTable" value="fdybjb"/>
			<input type="hidden" id="qx" value="${qx }"/>
			<input type="hidden" id="fpfs" name="fpfs" value="<bean:write name="fpfs" />"/>
			<input type="hidden" id="zdm" value="${zdm }"/>
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="session"/>"/>
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="11">
								<span>˼������ְ�</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<logic:equal value="yes" name="writeAble" scope="request">
						</logic:equal>
						<tr>
							<td colspan="11">
								<div class="btn">
									<button onclick="saveFdyBj()">
										�� ��
									</button>
									<button onclick="refreshForm('setFdyBj.do')">
										�� ��
									</button>
									<button
										onclick="showTopWin('/xgxt/szdwfzjy.do?method=fdybjjcsz',600,480)">
										��������
									</button>
<%--									<button onclick="putLsjl()">--%>
<%--										ת�Ƶ���ʷ��--%>
<%--									</button>--%>
									<button onclick="impAndChkData();">
										��������
									</button>
									<button onclick="dataExport()">
										��������
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
					<!--  ѧ������  -->
					<logic:equal value="0001" name="zdm">
						<!-- <tr>
							<td colspan="8">
									�꼶
									<html:select property="nj" onchange="refreshForm('setFdyBj.do')" style="width:130px"
										onmouseover="">
										<option value=""></option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									<bean:message key="lable.xsgzyxpzxy" />
									<html:select property="xydm" styleId="xy" style="width:130px" onmouseover=""
										onchange="refreshForm('setFdyBj.do')">
										<option value=""></option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									רҵ
									<html:select property="zydm" style="width:130px" onmouseover=""
										onchange="refreshForm('setFdyBj.do')">
										<option value=""></option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
							</td>
							<td colspan="3" rowspan="1">
								<html:radio property="szdwbb"  value="fdy"
									onclick="refreshForm('setFdyBj.do');changeFdyOrBzr()">����Ա���</html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio property="szdwbb" value="bzr"
									onclick="refreshForm('setFdyBj.do');changeFdyOrBzr()">�����α��</html:radio>
							</td> 
						</tr>-->
						<tr>
						<td colspan="8">
							<div >
								��ʦ��������
								<html:select property="bmdm" onmouseover=""  style="width:250px"
									onchange="">
									<option value=""></option>
									<html:options collection="bmList" property="bmdm"
										labelProperty="bmmc" />
								</html:select>

							</div>
						</td>
						<td colspan="3" rowspan="1">
							<html:radio property="szdwbb"  value="fdy"
								onclick="refreshForm('setFdyBj.do');changeFdyOrBzr()">����Ա���</html:radio>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:radio property="szdwbb" value="bzr"
								onclick="refreshForm('setFdyBj.do');changeFdyOrBzr()">�����α��</html:radio>
						</td>
					</tr>
					<tr>
						<td colspan="8">
						<div >
						ְ���ţ�
						<html:text  property="cxZgh" 
						/>
						������
						<html:text property="cxXm" 
						/>
						<button onclick="refreshForm('setFdyBj.do')" >
							��ѯ
						</button>
						</div>
						</td>
					</tr>
					<tr>
						<td align="center" valign="middle" width="5%" rowspan="4">
							<p>
								��
							</p>
							<p>
								ʦ
							</p>
							<p>
								��
							</p>
							<p>
								��
							</p>
						</td>
						<td width="40%" rowspan="4" colspan="4">
							<html:select property="fdyxm" size="10" styleId="fdyxmList"
								style="width:100% " ondblclick="getContInfo()"  onmouseover="null">
								<html:options collection="wdbFdyList" property="zgh"
									labelProperty="xm" style="color:blue"/>
								<html:options collection="dbFdyList" property="zgh"
									labelProperty="xm" style="color:red"/>
							</html:select>
						</td>
					</logic:equal>
					<!--  ��ѧ������  -->
					<logic:notEqual value="0001" name="zdm">
						<!--<tr>  
							<td colspan="8">
									�꼶
									<html:select property="nj" onchange="refreshForm('setFdyBj.do')" style="width:130px"
										onmouseover="">
										<option value=""></option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									<bean:message key="lable.xsgzyxpzxy" />
									<html:select property="xydm" styleId="xydm" style="width:130px" onmouseover=""
										onchange="refreshForm('setFdyBj.do')" disabled="true">
										<option value=""></option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									רҵ
									<html:select property="zydm" style="width:130px" onmouseover=""
										onchange="refreshForm('setFdyBj.do')">
										<option value=""></option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
							</td> 
							<td colspan="3" rowspan="1">
								<html:radio property="szdwbb" value="fdy"
									onclick="refreshForm('setFdyBj.do')">����Ա���</html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio property="szdwbb" value="bzr"
									onclick="refreshForm('setFdyBj.do')">�����α��</html:radio>
							</td>
						</tr>-->
						<tr>
						<td colspan="8">
							<div >
								��ʦ��������
								<html:select property="bmdm" onmouseover=""  style="width:250px"
									onchange="refreshForm('setFdyBj.do')">
									<option value=""></option>
									<html:options collection="bmxyList" property="bmdm"
										labelProperty="bmmc" />
								</html:select>
							</div>
						</td>
						<td colspan="3" rowspan="1">
							<html:radio property="szdwbb" value="fdy"
								onclick="refreshForm('setFdyBj.do')">����Ա���</html:radio>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:radio property="szdwbb" value="bzr"
								onclick="refreshForm('setFdyBj.do')">�����α��</html:radio>
						</td>
					</tr>
					<tr>
						<td colspan="8">
						<div >
						ְ���ţ�
						<html:text  property="zgh" 
						/>
						������
						<html:text property="xm" 
						/>
						<button onclick="" >
							��ѯ
						</button>
						</div>
						</td>
					</tr>
					<tr>
						<td align="center" valign="middle" width="5%" rowspan="4">
							<p>
								��
							</p>
							<p>
								ʦ
							</p>
							<p>
								��
							</p>
							<p>
								��
							</p>
						</td>
						<td width="40%" rowspan="4" colspan="4">
							<html:select property="fdyxm" size="10" styleId="fdyxmList"
								style="width:100% " ondblclick="getContInfo()"  onmouseover="null">
								<html:options collection="wdbFdyList" property="zgh"
									labelProperty="xm" style="color:blue"/>
								<html:options collection="dbFdyList" property="zgh"
									labelProperty="xm" style="color:red"/>
							</html:select>
						</td>
					</logic:notEqual>
						<th align="center" width="9%">
							ְ����
						</th>
						<td width="15%">
							<bean:write name="fdyInfo" property="zgh" />
							<html:hidden name="fdyInfo" property="zgh" styleId="zgh" />
						</td>
						<th width="10%" align="center">
							��������
						</th>
						<td colspan="3">
							<bean:write name="fdyInfo" property="bmmc" />
						</td>
					</tr>
					<tr>
						<th align="center">
							����
						</th>
						<td>
							<bean:write name="fdyInfo" property="xm" />
						</td>
						<th align="center">
							ְ��
						</th>
						<td colspan="3">
							<bean:write name="fdyInfo" property="zwmc" />
						</td>
					</tr>
					<tr>
						<th align="center"">
							�Ա�
						</th>
						<td>
							<bean:write name="fdyInfo" property="xb" />
						</td>
						<th align="center">
							��ϵ�绰
						</th>
						<td colspan="3">
							<bean:write name="fdyInfo" property="lxdh" />
						</td>
					</tr>
					<tr>
						<th align="center" height="50" 
							valign="middle">
							��Ҫְ��
						</th>
						<td colspan="5" valign="top">
							<textarea readonly="readonly" rows="5" cols="40" 
							style="word-break:break-all;98%" type="_moz">${fdyInfo.zyzz }</textarea>
						</td>
					</tr>
					<logic:equal value="0001" name="zdm">
						<tr>
							<td colspan="11">
									�꼶
									<html:select property="nj" onchange="refreshForm('setFdyBj.do')" style="width:130px"
										onmouseover="">
										<option value=""></option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									<bean:message key="lable.xsgzyxpzxy" />
									<html:select property="xydm" styleId="xy" style="width:130px" onmouseover=""
										onchange="refreshForm('setFdyBj.do')">
										<option value=""></option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									רҵ
									<html:select property="zydm" style="width:130px" onmouseover=""
										onchange="refreshForm('setFdyBj.do')">
										<option value=""></option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
							</td>
						</tr>
					</logic:equal>
					<logic:notEqual value="0001" name="zdm">
						<tr>
							<td colspan="11">
									�꼶
									<html:select property="nj" onchange="refreshForm('setFdyBj.do')" style="width:130px"
										onmouseover="">
										<option value=""></option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									<bean:message key="lable.xsgzyxpzxy" />
									<html:select property="xydm" styleId="xydm" style="width:130px" onmouseover=""
										onchange="refreshForm('setFdyBj.do')" disabled="true">
										<option value=""></option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									רҵ
									<html:select property="zydm" style="width:130px" onmouseover=""
										onchange="refreshForm('setFdyBj.do')">
										<option value=""></option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
							</td>
						</tr>
					</logic:notEqual>
					<tr>
						<td align="center" valign="middle">
							<p>
								��
							</p>
							<p>
								��
							</p>
						</td>
						<logic:equal value="0001" name="zdm">
							<td colspan="4">
								�༶�б�
								<font color="green">(˫������Աѡ���Ӧ�༶����ɫ��Ϊδ����༶����ɫΪ�ѷ���༶��������Ϊ������ʦ )</font>
								<br>
								<select name="bjmc" id="bjFlist" size="13" style="width:100%">
									<logic:iterate name="bjList" id="bj">
										<option value="${bj.bjdm }" title="${bj.bjmc }"style="color:blue">${bj.bjmc }</option>
									</logic:iterate>
									<logic:iterate name="yyzBjList" id="bj">
										<option value="${bj.bjdm }" title="${bj.bjmc }" style="color:red">${bj.bjmc }</option>
									</logic:iterate>
								</select>
								<!--  
								<html:select name="fdyInfo" property="bjmc" styleId="bjFlist"
									ondblclick="addFdyBj()" size="13" multiple="multiple"  onmouseover="null"
									style="width:100%">
									
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" style="color:blue" labelName=""/>
									<html:options collection="yyzBjList" property="bjdm"
										labelProperty="bjmc" style="color:red" />
								</html:select>-->
							</td>
						</logic:equal>
						<logic:notEqual value="0001" name="zdm">
							<td colspan="4">
								�༶�б�
								<font color="green">(˫������Աѡ���Ӧ�༶����ɫ��Ϊδ����༶����ɫΪ�ѷ���༶��������Ϊ������ʦ )</font>
								<br>
								<select name="bjmc" id="bjFlist" size="13" style="width:100%">
									<logic:iterate name="bjList" id="bj">
										<option value="${bj.bjdm }" title="${bj.bjmc }"style="color:blue">${bj.bjmc }</option>
									</logic:iterate>
									<logic:iterate name="yyzBjList" id="bj">
										<option value="${bj.bjdm }" title="${bj.bjmc }" style="color:red">${bj.bjmc }</option>
									</logic:iterate>
								</select>
								<!--  
								<html:select name="fdyInfo" property="bjmc" styleId="bjFlist"
									ondblclick="addFdyBj()" size="13" multiple="multiple"  onmouseover="null"
									style="width:100%">
									<html:options collection="bjListxy" property="bjdm"
										labelProperty="bjmc" style="color:blue" />
									<html:options collection="yyzBjList" property="bjdm"
										labelProperty="bjmc" style="color:red" />
								</html:select>
								-->
							</td>
						</logic:notEqual>
						<td nowrap>
							&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
							<button style="width:50%" class="btn" id="addFdyBjB"
								onclick="addFdyBj()">
								&gt;&nbsp;&gt;
							</button>
							<br>
							<br>
							<br>
							&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
							<button  style="width:50%" class="btn" id="delFdyBjB"
								onclick="delFdyBj()">
								&lt;&nbsp;&lt;
							</button>
						</td>
						<td colspan="5">
							����༶
							<br>
							<html:select name="fdyInfo" property="bjlist" size="13"
								ondblclick="delFdyBj()" styleId="bjTlist" style="width:100%"
								multiple="multiple"  onmouseover="null">
								<html:options collection="fdyBjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
					</tbody>
				</table>
				<logic:present name="ok" scope="request">
					<logic:equal value="ok" name="ok" scope="request">
						<script>alert("����ɹ���");</script>
					</logic:equal>
					<logic:equal value="no" name="ok" scope="request">
						<script>alert("����ʧ�ܣ������ԣ�");</script>
					</logic:equal>
				</logic:present>
				<logic:present name="done" scope="request">
					<logic:equal value="yes" name="done" scope="request">
						<script>alert("ת����ʷ��ɹ���");</script>
					</logic:equal>
					<logic:equal value="no" name="done" scope="request">
						<script>alert("ת����ʷ��ʧ�ܣ������ԣ�");</script>
					</logic:equal>
				</logic:present>
				<script language="javascript" src="js/bottomButton.js"></script>
		</html:form>
	</body>
</html>
