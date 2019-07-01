<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/xsgzgl/dtjs/dxbmgl/dxbmsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${data.sqid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${data.splc}&shid=${data.shid}");
			});

			function save_sh(){
				var shzt = jQuery("#shjg").val();
				if(jQuery("#shjg").val() == "0"){
					showAlertDivLayer("请选择审核状态！");
					return false;
				}
				jQuery("#shzt").val(shzt);
				if (jQuery("#shyj").val() == ""){
					showAlertDivLayer("请填写审核意见！");
					return false;
				}
				if (jQuery("#shyj").val().length>200){
					showAlertDivLayer("审核意见不能超过200字");
					return false;
				}
				var message;
				if(jQuery("#shjg").val() == "1"){
					message = "通过";
				}
				if(jQuery("#shjg").val() == "2"){
					message = "不通过";
				}
				if(jQuery("#shjg").val() == "3"){
					message = "退回";
				}
				//提交审核
				showConfirmDivLayer("您确定<font color='red'>[" + message + "]</font>该申请吗？",{"okFun":function(){
						zx(shzt,message);
					}});
				
			}

		
		</script>
	</head>
	<body>
	<html:form method="post" styleId="form" action="dxbmgl_dxbmsh.do">
		<%@ include file="/comm/hiddenValue.jsp"%>
		 <html:hidden   property="sqid"/>
		 <html:hidden name="data" property="splc" styleId="splc"/>
		 <html:hidden name="data"  property="shid" styleId="shid"/>
		 <html:hidden name="data"  property="shzt" styleId="shzt"/>
		<div style='tab;width:100%;height:430px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
				<thead>
						<tr>
							<th colspan="4">
								<span>党团信息申请</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<tr >
					<th width="16%">培训期次</th>
					<td width="34%">
						${sqxx.pxqc}
					</td>
					<th width="16%">发布人</th>
					<td width="34%">${sqxx.fbrxm}</td>
				</tr>
				<tr>
					<th width="16%">报名开始时间</th>
					<td>
						${sqxx.bmkssj}
					</td>
					<th width="16%">报名结束时间</th>
					<td>
						${sqxx.bmjssj}
					</td>
				</tr>
				<tr>
					<th width="16%">培训时间</th>
					<td colspan="3">
						${sqxx.pxsj}
					</td>
				</tr>
				<tr>
					<th width="16%">培训内容</th>
					<td colspan="3">						
						${sqxx.pxnr}
					</td>
				</tr>
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
			</tbody>
			<tr>
					<tr>
						<th>
							<font color="red">*</font>审核结果
						</th>
						<td colspan="3" id="shjgSpan">
							
						</td>
					</tr>
			</tr>
			<tr>
				<th width="20%" align="right">
					<font color="red">*&nbsp;</font> 审核意见&nbsp;
					<br />
					<font color="red">(限200字)</font>
				</th>
				<td colspan="3">
				    <jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=dtjs&id=shyj" />
					<textarea id="shyj" rows="4" name="shyj" style="word-break:break-all;width:100%" onblur="checkLen(this,200);">${rs.shyj}</textarea>
				</td>
			</tr>
		</table>
		</div>
		<div>
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<%--<button type="button" onclick="save_sh('1','通过');">
									通过
								</button>
								<button type="button" onclick="save_sh('2','不通过');">
									不通过
								</button>
								<button type="button" onclick="save_sh('3','退回');">
									退回
								</button>
								--%>
								<button type="button" name="保存" id="buttonClose" onclick="save_sh();return false;">
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
		</div>
		</html:form>
	</body>
</html>
