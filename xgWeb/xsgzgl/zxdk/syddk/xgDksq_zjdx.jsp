<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
			<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/syddk/js/dksq.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		
		<script type="text/javascript" defer="defer">

		jQuery(function(){
			//取得数据表：xg_pjpy_new_pjxmb; 字段：xmmc																							
			var autoSetting = {
				dataTable:"xg_zxdk_sydksqb",
				dataField:"yhdm",
				scrollHeight:135										
			}
			// 模糊搜索下拉【项目名称】
			jQuery("#yhdm").setAutocomplete(autoSetting);
		});
		</script>
	</head>
	<body>
		<html:form action="/zxdk_sydk" method="post" styleId="zxdkSydksqshForm" onsubmit="return false">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="splcid" styleId="xn"/>
			<html:hidden property="id" styleId="id"/>
			<html:hidden property="xh" styleId="xh"/>
			<html:hidden property="xn" styleId="xn"/>
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }" />
		    <input type="hidden" name="xz" id="xz" value="${jbxx.xz}"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;height:460px;" >
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
								<span>贷款信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:20%;">贷款学年</th>
							<td>
								<span>${zxdkSydksqshForm.xn}</span>
							</td>
							<th style="width:20%;"><font color="red">*</font>贷款银行</th>
							<td>
								<html:text  property="yhdm" styleId="yhdm" maxlength="50" ></html:text>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>贷款金额（元）</th>
							<td>
								<html:text property="dkje" styleId="dkje" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>回执检验码</th>
							<td colspan="3">
								<html:text property="hzjym" styleId="hzjym" maxlength="20" ></html:text>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>申请理由
								<br/><font color="red">(限输入500字)</font>
							</th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" 
											   onblur="checkLen(this,500);"
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
				<div style="height: 30px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveXgDksq('zxdk_sydk.do?method=update');">
										保存草稿
									</button>
									<button type="button" onclick="saveXgDksq('zxdk_sydk.do?method=updateAndSubmit');">
										提交申请
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