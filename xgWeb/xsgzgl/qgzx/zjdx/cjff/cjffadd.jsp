<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/zjdx/cjff/js/cjff.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#yrdwdm").val('${yrdwdm}');
		})
		</script>
	</head>
	<body>
		<html:form action="/cjff_zjdx" method="post" styleId="CjffForm">
		<input type="hidden" name="sxsz" id="sxsz" value="${sxsz}" />
		<input type="hidden" name="cjbz" id="cjbz" value="${cjbz}" />
		<!-- 是否允许超过酬金上限 -->
		<input type="hidden" name="sfyxcgcjsx" id="sfyxcgcjsx" value="${sfyxcgcjsx}" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/qgzx/zjdx/cjff/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>酬金发放信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>发放年月</th>
							<td>
								<html:select property="ffndyf" styleId="ffndyf" style="width:98%">
									<html:options collection="yfList" property="yf" labelProperty="yf"/>
								</html:select>
								
							</td>
							<th><font color="red">*</font>用人单位</th>
							<td>
								<html:select property="yrdwdm" styleId="yrdwdm" style="width:98%">
									<html:options collection="yrdwList" property="yrdwdm" labelProperty="yrdwmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>岗位性质</th>
							<td>
								<html:select property="gwxzdm" styleId="gwxzdm" style="width:98%">
									<html:option value="">-----请选择-----</html:option>
									<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc"/> 
								</html:select>
							</td>
							<th><font color="red">*</font>岗位类别</th>
							<td>
								<html:select property="gwlbdm" styleId="gwlbdm" style="width:98%">
									<html:option value="">-----请选择-----</html:option>
									<html:options collection="gwlbList" property="gwlbdm" labelProperty="gwlbmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>校区</th>
							<td>
								<html:select property="xqdm" styleId="xqdm" style="width:98%">
									<html:option value="">-----请选择-----</html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>
								
							</td>
							<th><font color="red">*</font>酬金(元)</th>
							<td>
								<html:text property="bcje" styleId="bcje" maxlength="10" onkeyup="checkMoneyBykeyUpByone(this)" style="width:98%" onblur="cjchange()"/>							
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>工时(小时)</th>
							<td >
								<html:text property="gss" styleId="gss" readonly="true" style="width:98%"/>
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>工作内容
								</br><font color="red">(限500字)</font>
							</th>
							<td colspan="3">
								<html:textarea property="gznr" styleId="gznr" 
								   onkeyup="checkLen(this,500);" 
								   style="width:98%;" rows="5"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>备注
								</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" 
								   onkeyup="checkLen(this,500);" 
								   style="width:98%;" rows="5"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveForm('save');">
										保    存
									</button>
									<button type="button" onclick="saveForm('savesubmit');">
										提    交
									</button>
									<button type="button" onclick="iFClose();">
										关闭
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