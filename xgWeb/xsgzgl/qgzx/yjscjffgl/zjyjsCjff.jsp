<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/yjscjffgl/js/yjscjffgl.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){																								
			changeGwmc();
		});
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/qgzxJfglYjscjff" method="post" styleId="YjsCjffForm">
			<html:hidden name="rs" property="disQg"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>学号
							</th>
							<td width="34%">
								<html:text  property="xh" styleId="xh"  maxlength="15"  styleClass="text_nor" ></html:text>
							</td>
							<th width="16%">
								<font color="red">*</font>姓名
							</th>
							<td width="34%">
								<html:text  property="xm" styleId="xm"  maxlength="50" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>增加酬金发放信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_ffxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>学年
							</th>
							<td width="34%">
								<html:select name="model" property="xn" onchange="changeGwmc();" styleId="xn" style="width:150px" >
									<html:options collection="xnList" labelProperty="xn" property="xn" />
								</html:select>
							</td>
							<th width="16%">
								用人部门
							</th>
							<td width="34%" >
								<logic:empty name="rs" property="disQg">
									<html:select name="model" property="yrbm" styleId="yrbm"   onchange="changeGwmc();" disabled="${rs.dis}" style="width:150px" >
										<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
									</html:select>
									<logic:equal value="true" name="rs" property="dis">
										<input type="hidden" value="${rs.yrbm}" id="yrbm" name="yrbm"/>
									</logic:equal>
								</logic:empty>
								<logic:notEmpty name="rs" property="disQg">
									<html:select name="model" property="yrbm" onchange="changeGwmc();" styleId="yrbm" disabled="true" style="width:150px" >
										<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
									</html:select>
									<input type="hidden" value="${rs.yrbm}" id="yrbm" name="yrbm"/>
								</logic:notEmpty>
							</td>
						</tr>
						<tr >
							<th width="16%">
								<font color="red">*</font>岗位
							</th>
							<td width="34%">
								<html:text  property="gwmc" styleId="gwmc" maxlength="20"  styleClass="text_nor"></html:text>
							</td>
							<th width="16%">
								<font color="red">*</font>发放年月
							</th>
							<td width="34%">
								<html:text  name="model" property="ffny" styleId="ffny" onclick="return showCalendar('ffny','yyyy-MM');" readonly="true" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>工时
							</th>
							<td width="34%">
								<html:text name="model" property="gs" styleId="gs" maxlength="5" styleClass="text_nor" onkeyup="checkInputData(this);" onblur="jsCjJe();" />
							</td>
							<th width="16%">
								<font color="red">*</font>金额
							</th>
							<td width="34%">
								<html:text name="model" property="je" styleId="je" maxlength="10" styleClass="text_nor" onkeyup="checkInputData(this);" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								工作内容
								<br/>
								<font color="red">限500字</font>
							</th>
							<td width="34%" colspan="3">
								<html:textarea name="model" property="gznr" styleId="gznr"  rows="5" style="width:99%"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				 <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4" >
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" id = "but_save" onclick="saveForm('zjyjsCjff')">
										保 存
									</button>
									<button type="button" type="button" id= "but_close" onclick="iFClose();">
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

