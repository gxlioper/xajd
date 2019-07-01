<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="java.util.HashMap" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
			function initBox(obj){
				if(obj.checked){
					jQuery("[name='zcxm']").attr("checked","checked");
				}else{
					jQuery("[name='zcxm']").attr("checked",false);
				}
			}
			function saveAuding(){
				if (jQuery("#shyj").val() == ""){
					showAlert("请将必填项填写完整！");
					return false;
				}
				
				var params = "";
				var xmdms = document.getElementsByName("zcxm");
				for(var i=0;i<xmdms.length;i++){
					if(xmdms[i].checked){
						params+=xmdms[i].value+",";
					}
				}
				if(params.length>0){
					params=params.substring(0,params.length-1);
				}
				jQuery("#xmdms").val(params);
				
				var url = "bzjl_sqsh.do?method=doPlsh";
				ajaxSubFormWithFun("sqshForm",url,function(data){
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
		<html:form action="/bzjl_sqsh" method="post" styleId="sqshForm">
			<html:hidden property="xn" styleId="xn"/>
			<html:hidden property="xq" styleId="xq"/>
			<html:hidden property="bjdms" styleId="bjdms"/>
			<input type="hidden" id="xmdms" name="xmdms"/>
			<div class='tab'>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveAuding();">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								奖项名称
							</th>
							<td width="80%" colspan="3">
								<input type="checkbox" checked="checked" onclick="initBox(this);"><font color="blue">全选</font>
								
								<%
								List<HashMap<String,String>> pjxmList = (List<HashMap<String,String>>)request.getAttribute("pjxmList");
								for(int x = 0;x<pjxmList.size();x++){
									HashMap<String,String> e = (HashMap<String,String>)pjxmList.get(x);
									if(x%4==0){
										%>
										<br>
										<%
									}
									%>
									<input type="checkbox" name="zcxm" value='<%=e.get("xmdm")%>' checked="checked" /> <%=e.get("xmmc") %>
									<%
								}
								%>
								
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>审核意见</th>
							<td colspan="3">
								<html:textarea property="shyj" styleId="shyj" onkeypress="checkLen(this,500);" style="width:99%;" rows="5"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>

