<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" >
		function saveForm(){
			var cxdj=jQuery("#cxdj").val();
			var cxpy=jQuery("#cxpy").val();
			var bzr=jQuery("#bzr").val();
			var xxdm = jQuery("#xxdm").val();
			if(cxdj==""||cxdj==null){
				alertInfo("请选择操行等级！",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
				return false;
			}
			if("13943" == xxdm) {
				if(jQuery("#sqsj").val()=="" || jQuery("#sqsj").val()==null){
					alertInfo("请填写时间！",function(tag){
						if(tag=="ok"){
							return false;
						}
					});
					return false;
				}
			}
			if(bzr==""||bzr==null){
				alertInfo("请填写班主任！",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
				return false;
			}
			if(cxpy==""){
				alertInfo("请填写操行评语！",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
				return false;
			}
			var url = "xsxx_gygl_cxcxpy.do?method=updateCxpy&type=save";
		      ajaxSubFormWithFun("cxpyForm",url,function(data){
		    	 if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
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
		<html:form action="/xsxx_gygl_cxcxpy.do?method=updateCxpy" styleId="cxpyForm" method="post">
		<html:hidden property="pk"/>
		<%@ include file="/comm/hiddenValue.jsp"%>

			<div style="height:320px; width:100%; woverflow-x:hidden;overflow-y:auto;padding-right:0;">

				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生评定信息</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="saveForm()">
										保存
									</button>
									<button type="button" onclick="refreshParent2();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody id="tbody_gwxx">
						<tr>
							<th width="16%">
								学号
							</th>
							<td width="34%">
								<bean:write name="cxpyForm" property="xh"/>
							</td>
							<th width="16%">
								姓名
							</th>
							<td width="34%">
								<bean:write name="cxpyForm" property="xm"/>
							</td>
						</tr>
						<tr>
							<th width="16%">
								年级
							</th>
							<td width="34%">
								<bean:write name="cxpyForm" property="nj"/>
							</td>
							<th width="16%">
								<bean:message key="lable.xb" />
							</th>
							<td width="34%">
								<bean:write name="cxpyForm" property="xymc"/>
							</td>
						</tr>
						<tr>
							<th width="16%">
								专业
							</th>
							<td width="34%">
								<bean:write name="cxpyForm" property="zymc"/>
							</td>
							<th width="16%">
								班级
							</th>
							<td width="34%">
								<bean:write name="cxpyForm" property="bjmc"/>
							</td>
						</tr>
						<tr>
							<th width="16%">
								学年
							</th>
							<td width="34%">
								<html:select style="width:100px" property="xn" styleId="xn" onchange="removeXs()" disabled="true">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
							<logic:notEqual name="xxdm" value="13943">
								<th width="16%">
									学期
								</th>
								
								<td width="34%">
									<html:select style="width:100px" property="xq"  styleId="xq" onchange="removeXs()" disabled="true">
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
									</html:select>
								</td>
							</logic:notEqual>
							<logic:equal name="xxdm" value="13943">
								<th width="16%"><span class="red">*</span>评语日期</th>
								<td width="34%">
									<html:text property="sqsj" styleId="sqsj" onfocus="showCalendar('sqsj','yyyy-MM-dd HH:mm:ss');"></html:text>
								</td>
							</logic:equal>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>操行等级
							</th>
							
							<td width="34%">
								<html:select style="width:100px" property="cxdj" styleId="cxdj">
									<html:options collection="cxdjList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							<th width="16%">
								<font color="red">*</font>班主任
							</th>
							<td width="34%">
								<html:text property="bzr" styleId="bzr" maxlength="5"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>操行评语
								<br/><font color="red">(限制字数150)</font>
							</th>
							<td width="90%" colspan="3">
								<html:textarea onblur="chLengs(this,150);" rows="5" styleId="cxpy" property="cxpy" style="width:99%"></html:textarea>
							</td>
							
						</tr>
						
					</tbody>
				</table>
			</div>
			<%--<div >
				<table border="0" class="formlist">
					
				</table>
			</div>--%>
		</html:form>
		<logic:notEmpty name="message">
			<script type="text/javascript">
			alertInfo("${message }",function(tag){
			if(tag=="ok"){
				refreshParent2();
			}
			});
			</script>
		</logic:notEmpty>
	</body>
</html>

