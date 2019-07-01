<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
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
			//保存注册
			function saveForm(zczt){
				var zcsj = jQuery("#zcsj").val();
				if(zcsj == ""){
					showAlert("注册时间不能为空！");
					return false;
				}
				 var url = "dtjs_tyzc.do?method=tyzcDgzc&type=save";
				 ajaxSubFormWithFun("tyzcForm",url,function(data){
			    	  if (data["message"]=="保存成功！"){
			    		  showAlert("注册成功！",{},{"clkFun":function(){
								if (parent.window){
									refershParent();
								}
							}});
			    	 }else{
			    		 showAlert(data["message"]);
			    	 }
			      });
			}
		</script>
	</head>
	<body>
		<html:form action="/dtjs_tyzc" method="post" styleId="tyzcForm">
			<input type="hidden" name="pk" value="${tyzcxx.pk}"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:271px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								学号
							</th>
							<td width="30%">
								${jbxx.xh }
							</td>
							<th width="20%">
								姓名
							</th>
							<td width="30%">
								${jbxx.xm }
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								${jbxx.xb }
							</td>
							<th>
								身份证号
							</th>
							<td>
								${jbxx.sfzh }
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td>
								${jbxx.nj }
							</td>
							<th>
								<bean:message key="lable.xb" />
							</th>
							<td>
								${jbxx.xymc }
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td>
								${jbxx.zymc }
							</td>
							<th>
								班级
							</th>
							<td>
								${jbxx.bjmc }
							</td>
						</tr>
						<tr>
							<th>
								政治面貌
							</th>
							<td>
								${jbxx.zzmmmc }
							</td>
							<th>
								联系方式
							</th>
							<td>
								${jbxx.sjhm }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>注册信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>学年</th>
							<td>${tyzcxx.xn }</td>
							<th>注册状态</th>
							<td>${tyzcxx.zcztmc }</td>
						</tr>
						<tr>
							<th><span class="red">*</span>注册时间</th>
							<td colspan="3">
								<input type="text" name="zcsj" value="${tyzcxx.zcsj}" id="zcsj"  style="250px" 
								onclick="return showCalendar('zcsj','yyyy-MM-dd',true);" 
								readonly="true"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										注 册
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
