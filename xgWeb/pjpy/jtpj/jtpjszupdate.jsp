<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="pjpy/jtpj/js/jtpjsz.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				autoSetSelectSqr();
				autoSetSqkg();
				jQuery("[name=sfkfsq]").bind("click",autoSetSqkg);
			});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/jtpjsz">
			<html:hidden property="ksqxslx" styleId="ksqxslx" />
			<html:hidden property="jxid" styleId="jxid" />
			<html:hidden property="sqxn" styleId="sqxn" />
			<html:hidden property="sqxq" styleId="sqxq" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>集体奖项信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<span class="red">*</span>奖项名称
							</th>
							<td colspan="3">
								<html:text property="jxmc" styleId="jxmc" style="width:35%"
									maxlength="25"></html:text>
							</td>
						</tr>
						<tr>
							<th width="">
								奖项说明<br/>
								<font color="red">(限500字)</font>
							</th>
							<td colspan="3">
								<html:textarea property="jxsm" rows="4" styleId="jxsm" style="width:96%;" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th width="">
								<span class="red">*</span>集体评奖单位
								<br />
								<font color="red">(选定后不能修改)</font>
							</th>
							<td colspan="3">
							<logic:equal name="sfkxg" value="1">
								<html:radio property="jtpjdw" value="xy" styleId="xy" >
									<label style='cursor:pointer' for="xy">以<font
										color="blue"><bean:message key="lable.xb" /></font>为单位</label>
								</html:radio>
								<html:radio property="jtpjdw" value="bj" styleId="bj">
									<label style='cursor:pointer' for="bj">以<font
										color="blue">班级</font>为单位</label>
								</html:radio>
								<logic:equal name="xxdm" value="12309">	
								<html:radio property="jtpjdw" value="qs" styleId="qs" >
									<label style='cursor:pointer' for="qs">以<font
										color="blue">寝室</font>为单位</label>
								</html:radio>
								</logic:equal>
								<logic:equal name="xxdm" value="10466">	
								<html:radio property="jtpjdw" value="qs" styleId="qs" >
									<label style='cursor:pointer' for="qs">以<font
										color="blue">寝室</font>为单位</label>
								</html:radio>
								</logic:equal>
								<html:radio property="jtpjdw" value="qt" styleId="qt">
									<label style='cursor:pointer' for="qt">其他</label>
								</html:radio>
							</logic:equal>
							<logic:notEqual name="sfkxg" value="1">
							 <html:radio property="jtpjdw" value="xy" styleId="xy" disabled="true">
									<label style='cursor:pointer' for="xy">以<font
										color="blue"><bean:message key="lable.xb" /></font>为单位</label>
								</html:radio>
								<html:radio property="jtpjdw" value="bj" styleId="bj" disabled="true">
									<label style='cursor:pointer' for="bj">以<font
										color="blue">班级</font>为单位</label>
								</html:radio>
								<logic:equal name="xxdm" value="12309">
								<html:radio property="jtpjdw" value="qs" styleId="qs" disabled="true">
									<label style='cursor:pointer' for="qs">以<font
										color="blue">寝室</font>为单位</label>
								</html:radio>
								</logic:equal>
								<logic:equal name="xxdm" value="10466">	
								<html:radio property="jtpjdw" value="qs" styleId="qs" >
									<label style='cursor:pointer' for="qs">以<font
										color="blue">寝室</font>为单位</label>
								</html:radio>
								</logic:equal>
								<html:radio property="jtpjdw" value="qt" styleId="qt" disabled="true">
									<label style='cursor:pointer' for="qt">其他</label>
								</html:radio>
							
							</logic:notEqual>
								
							</td>
						</tr>
						<thead>
							<tr>
								<th colspan="4">
									<span>申请审核设置</span>
								</th>
							</tr>
						</thead>
						<tr>
							<th width="">
								申请周期
							</th>
							<td>
								${dqxn} ${dqxq}
							</td>
							<th width="">
								<span class="red">*</span>奖项评定周期
								<br /><font color="red">(选定后不能修改)</font>
							</th>
							<td>
								<logic:equal name="sfkxg" value="1">
									<html:select property="pdxn" styleId="pdxn" style="width:">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									<html:select property="pdxq" styleId="pdxq" style="width:">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</logic:equal>
								<logic:notEqual name="sfkxg" value="1">
									<html:select property="pdxn" styleId="pdxn" disabled="true" style="width:">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									<html:select property="pdxq" styleId="pdxq" disabled="true" style="width:">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</logic:notEqual>
							</td>
						</tr>
						<logic:equal name="iswzdx" value="1">
							<tr>
								<th width="">
									学生可申请人员设定
								</th>
								<td colspan="3">
									<logic:iterate name="zwList" id="zw">
										<label style='cursor:pointer'>
										<html:checkbox property="ksqxslx" value="${zw.zwid}">${zw.zwmc}</html:checkbox></label>
									</logic:iterate>
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th width="">
								<span class="red">*</span>申请开关
							</th>
							<td colspan="3">
								
								<html:radio property="sfkfsq" value="1" styleId="sfkfsq1"><label style='cursor:pointer' for="sfkfsq1">开启</label></html:radio>
								
								<html:radio property="sfkfsq" value="0" styleId="sfkfsq2"><label style='cursor:pointer' for="sfkfsq2">关闭</label></html:radio>
							</td>
						</tr>
					<tbody id="kqxx">
						<tr>
							<th width="">
								申请开放时间
							</th>
							<td colspan="3">
								<html:text property="sqkfkssj" styleId="sqkfkssj"
									style="width:120px;"
									onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'sqkfjssj');" />
									～
								<html:text property="sqkfjssj" styleId="sqkfjssj"
									style="width:120px;"
									onfocus="return showCalendar(this.id,'yyyy-MM-dd',false,'sqkfkssj');" />
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>审核流程
							</th>
							<td colspan="3">
								<html:select property="splcid" styleId="splcid" disabled="false"
									style="width:280px;">
									<html:option value=""></html:option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
					</tbody>
					</tbody>
				</table>
			</div>
			<div style="height: 30px"></div>
			<div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button"
										onclick="save('jtpjsz.do?method=update&type=save','jxmc-pdxn-sfkfsq');return false;"
										id="buttonSave">
										保 存
									</button>
									<button type="button" onclick="iFClose();" id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>
