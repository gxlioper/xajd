<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" defer="defer">
			function saveSpl(){
				var url="dtjs_dxbmgl_dxbmshlcszBc.do";
				var shl = jQuery('#shl').val();
				if (shl ==null || shl =="") {
					alertError("带*号内容为必填！");
					return false;
				}
				refreshForm(url);
			}
		</script>
		
	</head>
	<body >
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		<logic:empty name="shlcList">
			<div class="prompt" id="pts">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				尚未设置审核流，请到系统管理 - 审核流程管理中设置本模块的审核流程！
			</p>
		</div>
		</logic:empty>
		<html:form action="/dxbmgl_dxbmshlcsz" method="post" styleId="shlForm">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			
			<!-- 维护信息 -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="2">
							<span>审核流设置</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="30%">
							<font color="red">*</font>审核流程
						</th>
						<td width="">
							<html:select property="shl" styleId="shl" style="width:240px">
								<html:options collection="shlcList" labelProperty="lcxx" property="splc"/>
							</html:select>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan='2'>
							<div class="btn">
								<button type="button"  onclick="saveSpl();return false;" id="btn_bc"  <logic:empty name="shlcList">disabled='disabled'</logic:empty>>
									保存
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- 维护信息 end-->
		</html:form>
		<logic:notEmpty name="result">
			<logic:equal value="false" name="result">
				<script language="javascript">
				alertInfo("操作失败！");
				</script>
			</logic:equal>
			<logic:equal value="true" name="result">
				<script language="javascript">
				alertInfo("操作成功！");
				</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>