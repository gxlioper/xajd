<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<head>
		<!-- 头文件 -->
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
					<em>您的当前位置:</em><a>${title }</a>
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
								<span>思政队伍分班</span>
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
										保 存
									</button>
									<button onclick="refreshForm('setFdyBj.do')">
										撤 销
									</button>
									<button
										onclick="showTopWin('/xgxt/szdwfzjy.do?method=fdybjjcsz',600,480)">
										参数设置
									</button>
<%--									<button onclick="putLsjl()">--%>
<%--										转移到历史库--%>
<%--									</button>--%>
									<button onclick="impAndChkData();">
										导入数据
									</button>
									<button onclick="dataExport()">
										导出数据
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
					<!--  学生处的  -->
					<logic:equal value="0001" name="zdm">
						<!-- <tr>
							<td colspan="8">
									年级
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
									专业
									<html:select property="zydm" style="width:130px" onmouseover=""
										onchange="refreshForm('setFdyBj.do')">
										<option value=""></option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
							</td>
							<td colspan="3" rowspan="1">
								<html:radio property="szdwbb"  value="fdy"
									onclick="refreshForm('setFdyBj.do');changeFdyOrBzr()">辅导员编班</html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio property="szdwbb" value="bzr"
									onclick="refreshForm('setFdyBj.do');changeFdyOrBzr()">班主任编班</html:radio>
							</td> 
						</tr>-->
						<tr>
						<td colspan="8">
							<div >
								教师所属部门
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
								onclick="refreshForm('setFdyBj.do');changeFdyOrBzr()">辅导员编班</html:radio>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:radio property="szdwbb" value="bzr"
								onclick="refreshForm('setFdyBj.do');changeFdyOrBzr()">班主任编班</html:radio>
						</td>
					</tr>
					<tr>
						<td colspan="8">
						<div >
						职工号：
						<html:text  property="cxZgh" 
						/>
						姓名：
						<html:text property="cxXm" 
						/>
						<button onclick="refreshForm('setFdyBj.do')" >
							查询
						</button>
						</div>
						</td>
					</tr>
					<tr>
						<td align="center" valign="middle" width="5%" rowspan="4">
							<p>
								教
							</p>
							<p>
								师
							</p>
							<p>
								队
							</p>
							<p>
								伍
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
					<!--  非学生处的  -->
					<logic:notEqual value="0001" name="zdm">
						<!--<tr>  
							<td colspan="8">
									年级
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
									专业
									<html:select property="zydm" style="width:130px" onmouseover=""
										onchange="refreshForm('setFdyBj.do')">
										<option value=""></option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
							</td> 
							<td colspan="3" rowspan="1">
								<html:radio property="szdwbb" value="fdy"
									onclick="refreshForm('setFdyBj.do')">辅导员编班</html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio property="szdwbb" value="bzr"
									onclick="refreshForm('setFdyBj.do')">班主任编班</html:radio>
							</td>
						</tr>-->
						<tr>
						<td colspan="8">
							<div >
								教师所属部门
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
								onclick="refreshForm('setFdyBj.do')">辅导员编班</html:radio>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:radio property="szdwbb" value="bzr"
								onclick="refreshForm('setFdyBj.do')">班主任编班</html:radio>
						</td>
					</tr>
					<tr>
						<td colspan="8">
						<div >
						职工号：
						<html:text  property="zgh" 
						/>
						姓名：
						<html:text property="xm" 
						/>
						<button onclick="" >
							查询
						</button>
						</div>
						</td>
					</tr>
					<tr>
						<td align="center" valign="middle" width="5%" rowspan="4">
							<p>
								教
							</p>
							<p>
								师
							</p>
							<p>
								队
							</p>
							<p>
								伍
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
							职工号
						</th>
						<td width="15%">
							<bean:write name="fdyInfo" property="zgh" />
							<html:hidden name="fdyInfo" property="zgh" styleId="zgh" />
						</td>
						<th width="10%" align="center">
							所属部门
						</th>
						<td colspan="3">
							<bean:write name="fdyInfo" property="bmmc" />
						</td>
					</tr>
					<tr>
						<th align="center">
							姓名
						</th>
						<td>
							<bean:write name="fdyInfo" property="xm" />
						</td>
						<th align="center">
							职务
						</th>
						<td colspan="3">
							<bean:write name="fdyInfo" property="zwmc" />
						</td>
					</tr>
					<tr>
						<th align="center"">
							性别
						</th>
						<td>
							<bean:write name="fdyInfo" property="xb" />
						</td>
						<th align="center">
							联系电话
						</th>
						<td colspan="3">
							<bean:write name="fdyInfo" property="lxdh" />
						</td>
					</tr>
					<tr>
						<th align="center" height="50" 
							valign="middle">
							主要职责
						</th>
						<td colspan="5" valign="top">
							<textarea readonly="readonly" rows="5" cols="40" 
							style="word-break:break-all;98%" type="_moz">${fdyInfo.zyzz }</textarea>
						</td>
					</tr>
					<logic:equal value="0001" name="zdm">
						<tr>
							<td colspan="11">
									年级
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
									专业
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
									年级
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
									专业
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
								班
							</p>
							<p>
								级
							</p>
						</td>
						<logic:equal value="0001" name="zdm">
							<td colspan="4">
								班级列表
								<font color="green">(双击辅导员选择对应班级，蓝色的为未分配班级，红色为已分配班级，括号内为负责老师 )</font>
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
								班级列表
								<font color="green">(双击辅导员选择对应班级，蓝色的为未分配班级，红色为已分配班级，括号内为负责老师 )</font>
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
							负责班级
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
						<script>alert("保存成功！");</script>
					</logic:equal>
					<logic:equal value="no" name="ok" scope="request">
						<script>alert("保存失败，请重试！");</script>
					</logic:equal>
				</logic:present>
				<logic:present name="done" scope="request">
					<logic:equal value="yes" name="done" scope="request">
						<script>alert("转到历史库成功！");</script>
					</logic:equal>
					<logic:equal value="no" name="done" scope="request">
						<script>alert("转到历史库失败，请重试！");</script>
					</logic:equal>
				</logic:present>
				<script language="javascript" src="js/bottomButton.js"></script>
		</html:form>
	</body>
</html>
