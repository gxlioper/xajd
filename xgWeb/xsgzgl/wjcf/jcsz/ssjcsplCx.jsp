<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" defer="defer">
		function saveSpl(){
			var url="wjcfJcsz_ssjcspl.do?method=ssjcsplBc";
			var ssspl = jQuery('#ssspl').val();
			var jcspl = jQuery('#jcspl').val();
			if (ssspl =="" || jcspl =="" ||ssspl==null||jcspl==null) {
				alertError("带*号内容为必填！");
				return false;
			}
			refreshForm(url);
		}
		function onShow() {
			var ssjg = jQuery('#ssjg').val();
			var sqjg = jQuery('#sqjg').val();
			if (ssjg != "" && ssjg != "0" && sqjg !="" && sqjg != "0") {
				jQuery('#btn_bc').attr('disabled', 'disabled');
			}
			if (ssjg =="0" && sqjg=="0") {
				document.getElementById("pts").style.display="none";
			}
		}
		
		jQuery(function(){
			onShow();
		})		
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
				尚未设置审批流，请到系统管理 - 审批流程管理中设置本模块的审批流程！
			</p>
		</div>
		</logic:empty>
		<!-- 提示信息 end-->
<%--		<div class="prompt" id="pts">--%>
<%--			<h3>--%>
<%--				<span>提示：</span>--%>
<%--			</h3>--%>
<%--			<p>--%>
<%--				部分学生申诉（解除）审核流程尚未结束，不能进行修改！--%>
<%--			</p>--%>
<%--		</div>--%>
		
		
		
		<!-- 提示信息 end-->
		
		<html:form action="/wjcfJcsz_ssjcspl" method="post">
			<input type="hidden" name="ssjg" id="ssjg" value="${rs.ssjg }"/>
			<input type="hidden" name="sqjg" id="sqjg" value="${rs.sqjg}"/>
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			
			<!-- 维护信息 -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="2">
							<span>申诉审批流设置</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="30%">
							<font color="red">*</font>
							审批流程
						</th>
						<td width="">
							<logic:present name="rs">
							<logic:equal name="rs" property="ssjg" value="0">
							
									<html:select name="rs" property="ssspl" styleId="ssspl" style="width:240px" value="${rs.ssspl}">
										<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>
									</html:select>
									
								</logic:equal>
								<logic:notEqual value="0" name="rs" property="ssjg">
								<html:select name="rs" property="spl" styleId="spl" style="width:240px" value="${rs.ssspl}" disabled="true">
										<html:options collection="shlcList" labelProperty="lcxx" property="splc"/>
									</html:select>
								<input type="hidden" name="ssspl" id="ssspl" value="${rs.ssspl }"/>
								</logic:notEqual>
								</logic:present>
								
								<logic:notPresent name="rs">
									<html:select name="rs" property="ssspl" styleId="ssspl" style="width:240px" value="${rs.ssspl}">
										<html:options collection="shlcList" labelProperty="lcxx" property="splc"/>
									</html:select>
								</logic:notPresent>
						</td>
					</tr>
				</tbody>
				<thead onclick="">
					<tr>
						<th colspan="2">
							<span>处分<bean:message key="wjcf.text" />审批流设置</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="30%">
							<font color="red">*</font>
							审批流程
						</th>
						<td width="">
							
							<logic:present name="rs">
							
<%--								审核流程已经记录在申请表当中，这里不做控制  20140512 785--%>
<%--								<logic:equal name="rs" property="sqjg" value="0">--%>
									<html:select name="rs" property="jcspl" styleId="jcspl" style="width:240px" value="${rs.jcspl}">
											<html:options collection="shlcList" labelProperty="lcxx" property="splc"/>
									</html:select>
<%--								</logic:equal>--%>
<%--								<logic:notEqual value="0" name="rs" property="sqjg">--%>
<%--									<html:select name="rs" property="cflbdm" styleId="cflbdm" style="width:240px" value="${rs.jcspl}" disabled="true">--%>
<%--											<html:options collection="shlcList" labelProperty="lcxx" property="splc"/>--%>
<%--									</html:select>--%>
<%--									<input type="hidden" name="jcspl" id="jcspl" value="${rs.jcspl }"/>--%>
<%--								</logic:notEqual> --%>
								
							</logic:present>
								
							<logic:notPresent name="rs">
								<html:select name="rs" property="jcspl" styleId="jcspl" style="width:240px" value="${rs.jcspl}">
									<html:options collection="shlcList" labelProperty="lcxx" property="splc"/>
								</html:select>
							</logic:notPresent>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan='2'>
							<div class="btn">
								<logic:equal value="yes" name="writeAble">
									<!-- 保存 -->
									<button type="button"  onclick="saveSpl();return false;" id="btn_bc"  <logic:empty name="shlcList">disabled='disabled'</logic:empty>>
										保存
									</button>
								</logic:equal>
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