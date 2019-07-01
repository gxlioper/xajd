<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/kqgl/zjsy/js/zjsykq.js"></script>
		<script type="text/javascript">
		</script>
	</head>		
	<body>	
		<html:form action="/zjsy_kqgl" method="post" styleId="ZjsyKqForm" onsubmit="return false;">
		<div style='tab;width:100%;height:410px;overflow-x:hidden;overflow-y:auto;' >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
							<span>考勤信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th><span class="red">*</span>学年学期</th>
							<td>
								<html:select property="xn" styleId="xn" value="${dqxn}">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
								<html:select property="xq" styleId="xq" value="${dqxq}">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>	
							</td>
						  <th><span class="red">*</span>月份</th>
							<td>
								<html:select property="yf" styleId="yf" value="${dqyf}">
									<html:options collection="yfList" property="yf" labelProperty="yf" />
								</html:select>&nbsp;月
							</td>
					    </tr>
					    <tr>
					        <th><span class="red">*</span>周次</th>
							<td>
								第
								<html:text property="zc" styleId="zc" style="width:20px;" maxlength="2" styleClass="text_nor" onblur="if(value != '') {value=parseInt(value,10)}" onkeyup="checkInputData(this);return false;"></html:text>
								周
							</td>
					        <th width="20%">
								<font color="red">*</font>班级
							</th>
							<td width="30%">
								<html:text style="width:145px" property="bjmc" onclick="bjSelect();"
									readonly="true" styleId="bjmc"></html:text>
								<input type="hidden" name="bjdm" id="bjdm" />
								<button class="btn_01" type="button"  
										onclick="bjSelect();return false;">选择</button>
							</td>
							
						</tr>
					    <tr>
					   		 <th><span class="red">*</span>应出勤人数</th>
								<td>
									<html:text property="cqrs" styleId="cqrs"  maxlength="5"
									onblur="if(value != '') {value=parseInt(value,10)}" onkeyup="checkInputData(this);return false;"></html:text>
								</td>
							<th><span class="red">*</span>病假人数</th>
							<td>
								<html:text property="bjrs" styleId="bjrs"  maxlength="5"
								onblur="if(value != '') {value=parseInt(value,10)}" onkeyup="checkInputData(this);return false;"></html:text>
							</td>
	
						</tr>
					    <tr>
					        <th><span class="red">*</span>事假人数</th>
							<td>
								<html:text property="sjrs" styleId="sjrs"  maxlength="5"
								onblur="if(value != '') {value=parseInt(value,10)}" onkeyup="checkInputData(this);return false;"></html:text>
							</td>
							<th><span class="red">*</span>旷课人数</th>
							<td colspan="3">
								<html:text property="kkrs" styleId="kkrs"  maxlength="5"
								onblur="if(value != '') {value=parseInt(value,10)}" onkeyup="checkInputData(this);return false;"></html:text>
							</td>
						</tr>
					    <tr>
							<th>
								旷课节数与累计节数
							</th>
							<td colspan="3">
								<html:textarea property='kkxq' style="width:98%" styleId="kkxq" rows='4' onblur="checkLen(this,100);return false;"/>
							</td>
			      		</tr>
			      		  <tr>
							<th>
								备注
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:98%" styleId="bz" rows='4' onblur="checkLen(this,100);return false;"/>
							</td>
			      		</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 15px"></div>
			<table width="100%" border="0" class="formlist"
				style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm('addKqdj','save');">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
			</table>
		</html:form>
	</body>
</html>