<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/wyjl.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		
		<script type="text/javascript">
			function saveWyjl(url){
				var xh= jQuery("#xh").val();
				var wyxq = jQuery("#wyxq").val();
				var wysj = jQuery("#wysj").val();

				
				// 检查学号是否存在
				if (xh==""){
					showAlertDivLayer("请选择学生!");
					return false;
				}

				// 检查违约时间是否为空
				if (wysj==""){
					showAlertDivLayer("违约时间不允许为空!");
					return false;
				}

				// 检查违约详情是否存在
				if (wyxq==""){
					showAlertDivLayer("违约详情不允许为空!");
					return false;
				}
				
				jQuery.post("zxdkWyjl.do?method=getCountByXh",{xh:xh},function(data){
					if (Number(data) == 0){
						ajaxSubFormWithFun("wyjlForm",url,function(data){
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								refershParent();
							}});
						});
					} else {
						showAlertDivLayer("该学生已存在违约记录，请确认！");
					}
				},"json");
				
				
			}
			
		</script>
	</head>
	<body>
		<html:form action="/zxdkWyjl" method="post" styleId="wyjlForm">
			<html:hidden property="splcid" value="${cssz.xydshlc }"/>
			<html:hidden property="dkzesx" styleId="dkzesx" value="${cssz.dkzesx }"/>
			<input type="hidden" id="dqxn" value="${dqxn }" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
				</table>
			</div>
			<div class="tab"  id="content" style="margin-top: 5px; margin-bottom: -10px; overflow-x:hidden;" >
			</div>
			<table width="100%" border="0" class="formlist" style="margin-bottom: 35px;">
				<thead>
					<tr>
						<th colspan="4">
							<span>违约信息</span>
						</th>
					</tr>
				</thead>
				<tr>
					<th width="15%">手机号码</th>
					<td width="35%">
						<html:text  property="sjhm" styleId="sjhm" maxlength="25"  styleClass="text_nor"></html:text>
					</td>
					<th width="15%">QQ号码</th>
					<td width="35%">
						<html:text  property="qqhm" styleId="qqhm" maxlength="25"  styleClass="text_nor"></html:text>
					</td>
				</tr>
				<tr>
					<th width="15%">微信</th>
					<td width="35%">
						<html:text  property="wxhm" styleId="wxhm" maxlength="25"  styleClass="text_nor"></html:text>
					</td>
					<th width="15%">邮箱</th>
					<td width="35%">
						<html:text  property="dzyx" styleId="dzyx" maxlength="25"  styleClass="text_nor"></html:text>
					</td>
				</tr>
				<tr>
					<th width="15%">是否联系到</th>
					<td>
						<html:select property="wyzt" styleId="wyzt" style="width:155px">
						<html:options collection="wyztList" property="wyzt"
							labelProperty="wyzt" />
						</html:select>
					</td>
					<th>
						<font color="red">* </font>违约时间
						
					</th>
					<td>
						<html:text property="wysj" styleId="wysj" onfocus="return showCalendar(this.id,'yyyy-MM');" maxlength="20"></html:text>
					</td>
				</tr>
				<tr>
					<th width="15%">
						<span class="red">*</span>违约详情
						<br />
						<font color="red">(限200字)</font>
					</th>
					<td colspan="3">
						<html:textarea  property="wyxq" styleId="wyxq" rows="5" cols="75" onblur="checkLen(this,200);"></html:textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">
						备注
						<br />
						<font color="red">(限500字)</font>
					</th>
					<td colspan="3">
						<html:textarea  property="bz" styleId="bz" rows="5" cols="75" onblur="checkLen(this,500);"></html:textarea>
					</td>
				</tr>
			</table>
			<div>
				<table class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveWyjl('zxdkWyjl.do?method=save');">
										保    存
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