<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
		<script type="text/javascript">
		
		function save(){
			var shlid = jQuery("#shlid").val();
			
			var shjg = jQuery('#shjg').val();

			var kgz = jQuery("input[name='kg']:checked").val();

			var url="xsxx_xnxj_jcszgl.do?method=cssz&type=save&spl="+shlid;
			
			//if(shjg > 0 && kgz == 'y'){
			//	showAlertDivLayer("有学生学年小结申请审核流程未结束时，不可修改审核流程！");
			//	return false;
			//}
			
			refreshForm(url);
		}
		
		
		function onShow() {
			var shjg = jQuery('#shjg').val();
			document.getElementById('shlc').style.display = "";
			var kg = document.getElementsByName("kg");
			for(var i=0;i<kg.length;i++){
				if(kg[i].checked&&kg[i].value=="n"){
					document.getElementById('shlc').style.display = "none";
				}
						
			}
		}
		jQuery(function(){
			onShow();
		})

		</script>
	</head>
	<body >
		<html:form action="/xsxx_xnxj_jcszgl" method="post">
		<input type="hidden" name="shjg" id="shjg" value="${rs.shjg }"/>
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
				</p>
			</div>
			
			<!-- 提示信息 end-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<span>
						开启学生学年小结开关，允许学生提交学年小结，当有学生学年小结申请审核流程未结束时，不可修改审核流程
					</span>
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->

			<div class="tab">
				<table width="100%" border="0" class="formlist">
				<thead>
						<tr>
							<th colspan="4">
								<span>参数设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
							<th>
								小结学年
							</th>
							<td>
								<html:select property="xjxn" styleId="xjxn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
					<tr>
						<th style="width:40%">
								<font color="red">*</font>
								学年小结填写开关
							</th>
							<td  style="width:60%">
								<html:radio property="kg" value="y" onclick="onShow()">开启</html:radio>
								<html:radio property="kg" value="n" onclick="onShow()">关闭</html:radio>
							</td>
							</tr>
							<tr id="shlc" >
							<th style="width:40%">
								<font color="red">*</font>
								审核流程
							</th>
								<logic:present name="rs">
<%--								<logic:equal name="rs" property="shjg" value="0">--%>
									<td  style="width:60%">
										<html:select property="spl" styleId="shlid"  style="width:240px">
											<html:option value="wxsh">无需审核</html:option>
											<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>
										</html:select>
									</td>
<%--							</logic:equal>--%>
<%--							<logic:notEqual value="0" name="rs" property="shjg">--%>
<%--								<td  style="width:60%" title="部分学生学年小结审核流程尚未结束，不能进行修改！">								--%>
<%--									<html:select property="spl" styleId="shlid"  style="width:240px" disabled="true">--%>
<%--										<html:option value="wxsh">无需审核</html:option>--%>
<%--										<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>--%>
<%--									</html:select>--%>
<%--								</td>--%>
<%--							</logic:notEqual>--%>
							</logic:present>
							<logic:notPresent name="rs">
								<td  style="width:60%">
									<html:select property="spl" styleId="shlid" style="width:240px">
										<html:option value="wxsh">无需审核</html:option>
										<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>
									</html:select>
								</td>
							</logic:notPresent>
						</tr>
						
					</tbody>
				</table>	
				<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									带"<font color="red">*</font>"为必填项
								</div>
								<div class="btn">
								<logic:equal name="writeAble" value="yes">
									<button type="button" class="button2" id="btn_bc"  onclick="save();return false;">
										保 存
									</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>

			<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alertInfo("操作失败！")
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alertInfo("操作成功！")
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
