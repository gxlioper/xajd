<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${zbglSqshForm.id}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${zbglSqshForm.splcid}&shid=${zbglSqshForm.id}");
			});
			function saveAudit(){
				var shzt=jQuery("#shjg").val();
				jQuery("#shzt").val(shzt);
				if (jQuery("#shyj").val() == ""){
					showAlertDivLayer("请填写审核意见！");
					return false;
				}
				if (jQuery("#shyj").val().length>200){
					showAlertDivLayer("审核意见不能超过200字");
					return false;
				}
				var text=jQuery("#shjg").find("option:selected").text();
				//提交审核
				zx(shzt,text);
			}
			
			function zx(shzt,text){
				var url = "zbglSqsh.do?method=submitAudit&tt="+new Date();
				ajaxSubFormWithFun("form",url,function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				});
			}
		</script>
	</head>
	<body>
		<html:form action="/zbglSqsh" method="post" styleId="form">
			<html:hidden property="id"/>
			<html:hidden property="shzt"/>
			
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:460px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>征兵信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>独生子女</th>
							<td>
								${zbglSqshForm.dszn }
							</td>
							<th>婚姻状况</th>
							<td>
								${zbglSqshForm.hyzk }
							</td>
						</tr>
						<tr>
							<th>视力</th>
							<td>
								${zbglSqshForm.sl }
							</td>
							<th>身高</th>
							<td>
								${zbglSqshForm.sg } cm
							</td>
						</tr>
						<tr>
							<th>体重</th>
							<td>
								${zbglSqshForm.tz } kg
							</td>
							<th>应征地</th>
							<td>
								${zbglSqshForm.yzd }
							</td>
						</tr>
						<tr>
							<th>应征来源</th>
							<td>
								${zbglSqshForm.yzly }
							</td>
							<th>参军意愿</th>
							<td>
								${zbglSqshForm.cjyy }
							</td>
						</tr>
						<logic:equal name="xxdm" value="14073">
							<tr>
								<th>就读起止日期</th>
								<td>
									${zbglSqshForm.ylzd1}
									至
									${zbglSqshForm.ylzd2}
								</td>
								<th>学习类型</th>
								<td>
									${zbglSqshForm.ylzd3}
								</td>
							</tr>
							<tr>
								<th>学业情况</th>
								<td>
									${zbglSqshForm.ylzd4}
								</td>
								<th>学校名称</th>
								<td>
									${zbglSqshForm.ylzd5}
								</td>
							</tr>
							<tr>
								<th>学校资助部门地址</th>
								<td colspan="3">
									${zbglSqshForm.ylzd7}
								</td>
							</tr>
							<tr>
								<th>邮编</th>
								<td>
									${zbglSqshForm.ylzd8}
								</td>
								<th>院校所在地</th>
								<td>
									${zbglSqshForm.ylzd6}
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th>从业类别</th>
							<td>
								${zbglSqshForm.cylb }
							</td>
							<th>职业资格证书</th>
							<td>
								${zbglSqshForm.zgzs }
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="3" style="">
								${zbglSqshForm.bz }
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = "${zbglSqshForm.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
											});
									});
								</script>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>审核信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>审核结果
							</th>
							<td id="shjgSpan" colspan="3">
								
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*&nbsp;</font> 审核意见&nbsp;
								<br />
								<font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xss_zbgl&id=shyj" />
								<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);">${rs.shyj}</textarea>
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
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" id="btqd" onclick="saveAudit();">
										保 存
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