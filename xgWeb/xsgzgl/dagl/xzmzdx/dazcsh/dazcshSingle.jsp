<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splcid}&shid=${model.shid}",function(){
				});

				var zcfs = "${rs.zcfsmc}";
				if("邮寄" == zcfs){
					/*隐藏字段*/
					jQuery("#mailedOne").show();
					jQuery("#mailedTwo").show();
					jQuery("#mailedThree").show();
					jQuery("#byoOne").hide();
				}else{
					/*隐藏字段*/
					jQuery("#mailedOne").hide();
					jQuery("#mailedTwo").hide();
					jQuery("#mailedThree").hide();
					jQuery("#byoOne").show();
				}
			});
			
			/**
			 * 单个审核保存
			 * @return
			 */
			function dazcshSingleSave(){
				var shjg = jQuery("#shjg").val();
				if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
					showAlert("请将带<font color='red'>*</font>项填写完整！");
					return false;
				}
				var url = "dagl_dazcsh.do?method=dazcshSingleSave";
				ajaxSubFormWithFun("dazcshForm",url,function(data){
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
	<html:form action="/dagl_dazcsh" method="post" styleId="dazcshForm">
		<html:hidden name="model" property="sqid" styleId="sqid"/>
		<html:hidden name="model" property="xh" styleId="xh"/>		
		<html:hidden name="model" property="splcid" styleId="splcid" value="${rs.splcid}"/>
		<html:hidden name="model" property="shid" styleId="shid"/>
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
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
							<span>项目信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
						<tr>
							<th>转出方式</th>
							<td colspan="3">${rs.zcfsmc}</td>
						</tr>
						
						<tr id="mailedOne">
							<th>邮寄地址</th>
							<td>${rs.yjdz}</td>
							<th>邮政编码</th>
							<td>${rs.yzbm}</td>
						</tr>
						
						<tr id="mailedTwo">
							<th>收件人</th>
							<td>${rs.sjr}</td>
							<th>收件人电话</th>
							<td>${rs.sjrdh}</td>
						</tr>
						
						<tr id="mailedThree">
							<th>单位名称</th>
							<td>${rs.dwmc}</td>
							<th>单位地址</th>
							<td>${rs.dwdz}</td>
						</tr>
						
						<tr id="byoOne">
							<th>自带档案承诺</th>
							<td>
								我已阅并接受
								<a href="javascript:void(0);" onclick="window.open('common_upload.do?method=asyncDownload&fid=${dazccsszForm.uploadid}');return false;" class="name" style="margin-left: 0px;">
									《档案转出协议》
								</a>
							</td>
							<th>预期提档日期</th>
							<td>${rs.yqtdrq}</td>
						</tr>
				</tbody>
				<thead>
						<tr>
							<th colspan="4">
								<span>审批信息</span>
							</th>
						</tr>
				</thead>				
				<tbody>
					<tr>
						<td colspan="4" id="shlccx">
						
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
					<th>
						<font color="red">*</font>审核结果
					</th>
					<td id="shjgSpan" colspan="3">&lt;
						
					</td>
				</tr>
				<tr>
					<th width="20%">
						<font color="red">*&nbsp;</font> 审核意见
						<br />
						<font color="red">(限200字)</font>
					</th>
					<td colspan="3">
						<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=sbsh&id=shyj" />
						<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
					</td>
				</tr>
			</tbody>
			</table>
		</div>
		<div style="height: 50px"></div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" name="保存" onclick="dazcshSingleSave();return false;">
									保 存
								</button>
								<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
	</html:form>
</body>
</html>