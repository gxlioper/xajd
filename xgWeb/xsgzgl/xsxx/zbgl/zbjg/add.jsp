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
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
			function saveForm(url){
				var checkStr = "xh-dszn-hyzk-sl-sg-tz-yzd-yzly-cjyy";
				<logic:equal name="xxdm" value="14073">
					checkStr += "-ylzd1-ylzd2-ylzd3-ylzd4-ylzd5-ylzd6-ylzd7-ylzd8"; 
				</logic:equal>
				if (checkNull(checkStr)){
					ajaxSubFormWithFun("form",url,function(data){
						showAlert(data["message"],{},{"clkFun":function(){
							refershParent();
						}});
					});
				}
			}
		</script>
	</head>
	<body>
		<html:form action="/zbglZbjg" method="post" styleId="form">
			<html:hidden property="xn" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:466px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>征兵信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><span class="red">*</span>独生子女</th>
							<td>
								<html:select property="dszn" styleId="dszn">
									<html:option value=""></html:option>
									<html:option value="独生子女">独生子女</html:option>
									<html:option value="非独生子女">非独生子女</html:option>
								</html:select>
							</td>
							<th><span class="red">*</span>婚姻状况</th>
							<td>
								<html:select property="hyzk" styleId="hyzk">
									<html:option value=""></html:option>
									<html:option value="已婚">已婚</html:option>
									<html:option value="未婚">未婚</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>视力</th>
							<td>
								<html:text property="sl" maxlength="20" styleId="sl"></html:text>
							</td>
							<th><span class="red">*</span>身高(cm)</th>
							<td>
								<html:text property="sg" maxlength="3" styleId="sg" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>体重(kg)</th>
							<td>
								<html:text property="tz" maxlength="3" styleId="tz" onkeyup="checkInputNum(this);"></html:text>
							</td>
							<th><span class="red">*</span>应征地</th>
							<td>
								<html:text property="yzd" maxlength="50" styleId="yzd"></html:text>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>应征来源</th>
							<td>
								<html:text property="yzly" maxlength="50" styleId="yzly"></html:text>
							</td>
							<th><span class="red">*</span>参军意愿</th>
							<td>
								<html:text property="cjyy" maxlength="50" styleId="cjyy"></html:text>
							</td>
						</tr>
						<logic:equal name="xxdm" value="14073">
							<tr>
								<th><span class="red">*</span>就读起止日期</th>
								<td>
									<html:text  style="width:70px" styleId="ylzd1" property="ylzd1" onclick="return showCalendar('ylzd1','yyyy-MM-dd',true,'ylzd2');"  readonly="true"></html:text>
									至
									<html:text  style="width:70px" styleId="ylzd2" property="ylzd2" onclick="return showCalendar('ylzd2','yyyy-MM-dd',false,'ylzd1');" readonly="true"></html:text>
								</td>
								<th><span class="red">*</span>学习类型</th>
								<td>
									<html:text property="ylzd3" maxlength="50" styleId="ylzd3"></html:text>
								</td>
							</tr>
							<tr>
								<th><span class="red">*</span>学业情况</th>
								<td>
									<html:text property="ylzd4" maxlength="100" styleId="ylzd4"></html:text>
								</td>
								<th><span class="red">*</span>学校名称</th>
								<td>
									<html:text property="ylzd5" maxlength="50" styleId="ylzd5"></html:text>
								</td>
							</tr>
							<tr>
								<th><span class="red">*</span>学校资助部门地址</th>
								<td colspan="3">
									<html:text property="ylzd7" maxlength="100" styleId="ylzd7" style="width: 488px;"></html:text>
								</td>
							</tr>
							<tr>
								<th><span class="red">*</span>邮编</th>
								<td>
									<html:text property="ylzd8" maxlength="10" styleId="ylzd8" onkeyup="checkInputNum(this);"></html:text>
								</td>
								<th><span class="red">*</span>院校所在地</th>
								<td>
									<html:text property="ylzd6" maxlength="100" styleId="ylzd6"></html:text>
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th>从业类别</th>
							<td>
								<html:text property="cylb" maxlength="50"></html:text>
							</td>
							<th>职业资格证书</th>
							<td>
								<html:text property="zgzs" maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								备注
								<br/><span class="red">(限400字)</span>
							</th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" 
											   onblur="checkLen(this,400);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
						<tr>
							<th align="right" width="16%">
								附件信息
							</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
								<input type="file" id="filepath_f" name="filepath" />
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										jQuery('#filepath_f').multiUploader({
											maxcount : 3,
											//后缀
											accept : 'png|gif|jpg|zip|rar|doc|docx',
											//最大文件大小 单位M
											maxsize: 10,
											//存放附件的隐藏域的id
											elementid : 'filepath',

											eid : 'filepath_f'
											});
									});
								</script>
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
									<button type="button" onclick="saveForm('zbglZbjg.do?method=save');">
										保存
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

