<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
	<body>
		<center>
			<html:form action="/chgPriseBat" method="post">
				<input type="hidden" name="xxdm" id="xxdm" value="<bean:write name="xxdm"/>"/>
				<html:hidden name="rs" property="yrdwdm"/>
				<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em>
						<a>
							<%--长沙民政--%>
							<logic:equal value="10827" name="xxdm">
								学生义工 - 经费管理 - 经费划拨
							</logic:equal>
							<logic:notEqual value="10827" name="xxdm">
								勤工助学 - 经费管理 - 经费划拨
							</logic:notEqual>
						</a>
					</p>
				</div>
				<div class="tab">
		  		
				<fieldset>
					<legend>
						<span class="blue"><b>学年:<bean:write name="rs" property="xn"/>&nbsp;&nbsp;年度：<bean:write name="rs" property="nd"/>&nbsp;&nbsp;学期：<bean:write name="rs" property="xqmc"/>&nbsp;&nbsp;划拨类别：常规</b></span>
					</legend>
					<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<logic:present name="showbjlh">
								<th colspan="6">
									<span>各<bean:message key="lable.xsgzyxpzxy" />及单位勤工经费设定</span>
								</th>
							</logic:present>
							<logic:notPresent name="showbjlh">
								<th colspan="5">
									<span>各<bean:message key="lable.xsgzyxpzxy" />及单位勤工经费设定</span>
								</th>
							</logic:notPresent>
						</tr>
						<logic:present name="showbjlh">
							<tr>
								<th>所属单位</th>
								<th>所属部门</th>
								<th>岗位数</th>
								<th>用人量</th>
								<th>划拨经费</th>
								<th>岗位性质</th>
							</tr>
						</logic:present>
						</thead>
						<tbody>
						<logic:iterate name="yrdwList" id="s" indexId="index">
						<tr>
							<th><bean:write name="s" property="yrdwmc"/></th>
							<logic:present name="showbjlh">
								<th><bean:write name="s" property="xydm"/></th>
								<th><bean:write name="s" property="gws"/></th>
								<th><bean:write name="s" property="syrs"/></th>
							</logic:present>
							<td>
								<input type="text" name="<bean:write name="s" property="yrdwdm"/>" maxlength="8" onkeyup="value=value.replace(/[^\d]/g,'') "/>(元)
							</td>
							<logic:notPresent name="showbjlh">
								<th>岗位性质</th>
								<td>
									<select name="gwxz${index}" style="width:180px">
										<option value=""></option>
										<logic:iterate id="gwxz" name="gwxzList">
										<option value="<bean:write name="gwxz" property="gwxzdm"/>"><bean:write name="gwxz" property="gwxzmc"/> </option>
										</logic:iterate>
									</select>
								</td>
							</logic:notPresent>
							
							<logic:present name="showbjlh">
							<td>
								<select name="gwxz${index}" style="width:180px">
									<option value=""></option>
									<logic:iterate id="gwxz" name="gwxzList">
									<option value="<bean:write name="gwxz" property="gwxzdm"/>"><bean:write name="gwxz" property="gwxzmc"/> </option>
									</logic:iterate>
								</select>
							</td>
							</logic:present>
						</tr>
						</logic:iterate>
						</tbody>
						<tfoot>
						  <tr>
							<logic:present name="showbjlh">
								<td colspan="6">
									<div class="btn">
										<button type="button" onclick="if(identifyInt()){refreshForm('/xgxt/work_outlay_transfer.do?dotype=save')}">
											保存
										</button>
									</div>
								</td>
							</logic:present>
							<logic:notPresent name="showbjlh">
								<td colspan="5">
									<div class="btn">
										<button type="button"
											onclick="if(identifyInt()){if (confirm('确认要对经费划拨数据进行保存吗？')) {refreshForm('/xgxt/work_outlay_transfer.do?dotype=save')}}">
											保存
										</button>
									</div>
								</td>
							</logic:notPresent>								
							</tr>
					    </tfoot>
					</table>
			</fieldset>
			</div>
			<logic:notEmpty name="ok">
				<logic:equal name="ok" value="ok">
					<script>alert("保存成功!")</script>
				</logic:equal>
				<logic:equal name="ok" value="no">
					<script>alert("保存失败!")</script>
				</logic:equal>
			</logic:notEmpty>
	</html:form>
</body>
</html>


