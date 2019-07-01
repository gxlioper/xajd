<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="pjpy/jtpj/js/jtpjsz.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				autoSetPjdwMc();
				var kqqk=jQuery("#sfkqkg").val();
				if(kqqk=="1"){
					jQuery("#kqxx").show();
				}else{
					jQuery("#kqxx").hide();
				}
			});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/jtpjsz">
			<input type="hidden" id="jtpjdw" value="${data.jtpjdw}" />
			<input type="hidden" id="sfkqkg" value="${data.sfkfsq}" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div>
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
								奖项名称
							</th>
							<td colspan="3">
								${data.jxmc}
							</td>
						</tr>
						<tr>
							<th width="">
								奖项说明
							</th>
							<td colspan="3">
								${data.jxsm}
							</td>
						</tr>
						<tr>
							<th width="">
								集体评奖单位
								<br />
							</th>
							<td colspan="3">
								<logic:equal name="data" property="jtpjdw" value="xy">以<font color='blue'><bean:message key="lable.xb" /></font>为单位</logic:equal>
								<logic:equal name="data" property="jtpjdw" value="bj">以<font color='blue'>班级</font>为单位</logic:equal>
								<logic:equal name="data" property="jtpjdw" value="qs">以<font color='blue'>寝室</font>为单位</logic:equal>
								<logic:equal name="data" property="jtpjdw" value="qt">其他</logic:equal>
								
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
								${data.sqxn} ${sqxqmc}
							</td>
							<th width="20%">
								奖项评定周期
							</th>
							<td>
								${data.pdxn} ${pdxqmc}
							</td>
						</tr>
						<logic:equal name="iswzdx" value="1">
							<tr>
								<th width="">
									学生可申请人员设定
								</th>
								<td colspan="3">
									${zwmc}
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th width="">
								申请开关
							</th>
							<td colspan="3">
								${sfkfsqmc}
							</td>
						</tr>
						<tbody id="kqxx">
							<tr>
								<th width="">
									申请开放时间
								</th>
								<td colspan="3">
									${data.sqkfkssj} 
									<logic:notEmpty name="data" property="sqkfjssj">
										～
									</logic:notEmpty>
									<logic:empty name="data" property="sqkfjssj">
										<logic:notEmpty name="data" property="sqkfkssj">
											～
										</logic:notEmpty>
									</logic:empty>
									 ${data.sqkfjssj}
								</td>
							</tr>
							<tr>
								<th>
									审核流程
								</th>
								<td colspan="3">
									${splcmc}
								</td>
							</tr>
						</tbody>
					</tbody>
				</table>
			</div>
			<div style="hight: 15px"></div>
			<div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
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
