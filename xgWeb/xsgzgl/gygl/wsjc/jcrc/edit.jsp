<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function saveForm(){
				if (!checkNull("xn-xq-rcmc-fslx-kssj-jssj")){
					return false;
				}
				
				if (jQuery("input[name=xmdm]:checked").size() == 0){
					showAlertx("请选择检查项目。");
					return false;
				}
				
				var url = "wsjcJcrc.do?method=update";
				ajaxSubFormWithFun("form",url,function(data){
					showAlertx(data["message"],{},{clkFun:function(){
						refershParent();
					}});
				});
			}
			
			jQuery(function(){
				jQuery("#checkAllXmdm").bind("click",function(){
					if(jQuery(this).prop("checked")){
						jQuery("input[name=xmdm]").attr("checked","checked");
					} else {
						jQuery("input[name=xmdm]").attr("checked",false);
					}
				});
				
				<logic:iterate id="r" name="rcxmArr">
					jQuery("input[name=xmdm][value=${r}]").attr("checked","checked");
				</logic:iterate>
			});
			
		</script>
	</head>
	<body>
		<html:form action="/wsjcJcrc" method="post" styleId="form">
			<html:hidden property="id" />
			<div style='overflow-x:hidden;overflow-y:auto;height:440px;margin-bottom: 0px;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>检查日程</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="15%">
								<font color="red">*</font>学年
							</th>
							<td width="35%">
								<html:select property="xn" styleId="xn" >
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
							<th width="15%">
								<font color="red">*</font>学期
							</th>
							<td width="35%">
								<html:select property="xq" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>名称
							</th>
							<td>
								<html:text property="rcmc" maxlength="50" styleId="rcmc"></html:text>
							</td>
							<th>
								<font color="red">*</font>起止时间
							</th>
							<td>
								<html:text property="kssj" styleId="kssj" readonly="true" style="width:80px;" 
								onfocus="showCalendar('kssj','y-mm-dd',true,'jssj');" ></html:text>&nbsp;至&nbsp;
								<html:text property="jssj" styleId="jssj" readonly="true" style="width:80px;" 
								onfocus="showCalendar('jssj','y-mm-dd',false,'kssj');" ></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>类型
							</th>
							<td colspan="3">
								<html:select property="fslx" styleId="fslx">
									<html:option value=""></html:option>
									<html:option value="0">分数</html:option>
									<html:option value="1">等级</html:option>
									<html:option value="2">星级</html:option>
								</html:select>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>检查项目 </span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table class="formlist" style="width:100%;">
									<tr>
										<th style="width:10px;">
											<input type="checkbox" id="checkAllXmdm"/>
										</th>
										<th style="text-align: center;">检查项</th>
										<th style="text-align: center;">检查内容</th>
										<th style="text-align: center;">检查对象</th>
									</tr>
									<logic:iterate id="j" name="jcxmList">
										<tr>
											<td>
												<input type="checkbox" value="${j.xmdm }" name="xmdm"/>
											</td>
											<td>${j.xmmc }</td>
											<td>${j.xmnr }</td>
											<td>
												<logic:equal value="0" property="jcdx" name="j">
													寝室
												</logic:equal>
												<logic:equal value="1" property="jcdx" name="j">
													床位
												</logic:equal>
											</td>
										</tr>
									</logic:iterate>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<table  class="formlist">
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
		</html:form>
	</body>
</html>

