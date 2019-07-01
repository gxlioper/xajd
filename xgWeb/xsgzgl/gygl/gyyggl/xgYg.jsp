<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="hqgl/rcgl/js/lfdj.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type="text/javascript">
			function saveForm(){
				var str = "xm-xb-sfzh-nl-lxdh";
				var flag = checkNotNull(str);
				if (!flag) {
					alertError("带\'*\'号字段必须填写！");
					return false;
				}
	            var val=jQuery('input:radio[name="zgzt"]:checked').val();
	            if(val==null){
	            	alertError("请选择是否在岗！");
	                return false;
	            }
					var url = "gyglnew_gyygxxgl.do?method=xgYg&type=save";
					ajaxSubFormWithFun("gyygxxglForm",url,function(data){
						showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
					});
				
			}
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/gyglnew_gyygxxgl" method="post" styleId="gyygxxglForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>修改外聘人员</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="20%">
								编号
							</th>
							<td width="30%">
								<input type="hidden" name="ygbh" id = "ygbh" value="${gyygxxglForm.ygbh }"/>
								${gyygxxglForm.ygbh }
							</td>
							<th width="20%">
								<font color="red">*</font>姓名
							</th>
							<td width="30%">
								<html:text property="xm" styleId="xm" maxlength="10" styleClass="text_nor" />
							</td>
							</tr>
							<tr>
							<th width="20%">
								<font color="red">*</font>性别
							</th>
							<td  width="30%">
								<html:select property="xb" styleId="xb" style="width:150px">
										<html:option value="1">男</html:option>
										<html:option value="2">女</html:option>
									</html:select>
							</td>
							<th width="20%">
								<font color="red">*</font>年龄
							</th>
							<td width="30%">
								<html:text property="nl"  styleId="nl" maxlength="3" onblur="checkAge(this)" styleClass="text_nor" />
							</td>
							</tr>
							<tr>
							<th width="20%">
								聘前职业状况
							</th>
							<td width="30%">
								<html:text property="pqzyzk" styleId="pqzyzk" maxlength="20" styleClass="text_nor" />
							</td>
							<th width="20%">
								<font color="red">*</font>现岗
							</th>
							<td width="30%">
								<html:select  property="zwdm" styleId="zwdm"
									style="width:150px">
									<html:options collection="zwdmList" property="zwdm"
										labelProperty="zwmc" />
								</html:select>
							</td>
							</tr>
							<tr>
							<th width="20%">
								聘用日期
							</th>
							<td width="30%">
								<html:text property="pyrq" styleId="pyrq"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('pyrq','y-mm-dd');" />
							</td>
							<th width="20%">
								<font color="red">*</font>身份证号
							</th>
							<td width="30%">
								<html:text property="sfzh" styleId="sfzh" onblur="checkSfzh(this)" maxlength="18" styleClass="text_nor" />
							</td>
							</tr>
							<tr>
							<th width="20%">
								<font color="red">*</font>联系电话
							</th>
							<td width="30%">
								<html:text property="lxdh"  styleId="lxdh" onblur="checkPhone(this);"  maxlength="20" styleClass="text_nor" />
							</td>
							<th width="20%">
								工资标准
							</th>
							<td width="30%">
								<html:text property="gzbz" styleId="gzbz" maxlength="10" styleClass="text_nor" />
							</td>
							</tr>
							<tr>
								<th width="20%">
									<font color="red">*</font>是否在岗
								</th>
								<td width="30%" colspan="3">
									<html:radio property="zgzt" value="0" styleId="zgzt">否</html:radio>
									&nbsp;&nbsp;
									<html:radio property="zgzt" value="1" styleId="zgzt">是</html:radio>
								</td>
							</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
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
			</div>
		</html:form>
	</body>
</html>

