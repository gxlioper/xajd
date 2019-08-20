<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	</head>
	<tbody>
		<%
		List<HashMap<String,String>> jbxxList = (List<HashMap<String,String>>)request.getAttribute("jbxxList");
	%>
		<logic:present name="jbxxList">
			<tr>
				<logic:iterate id="j" name="jbxxList" indexId="i">
					<th width="16%">
						<logic:notEqual value="false" name="isJsOrPb">
							<logic:equal value="0" name="i">
								<logic:notEqual name="type" value="update">
									<font color="red">*</font>
								</logic:notEqual>
							</logic:equal>
						</logic:notEqual>
						${j.zdmc }
					</th>
					<td width="34%">
						<logic:equal value="false" name="isJsOrPb">
							<html:hidden property="xh" styleId="xh" />
							<logic:present name="jbxx">
								<bean:write name="jbxx" property="${j.zddm }" />
							</logic:present>
						</logic:equal>

						<logic:notEqual value="false" name="isJsOrPb">
							<logic:equal value="0" name="i">

									<input type="text" name="xh" value="${jbxx.xh }" readonly="true" 
										id="xh" style="width:120px;border: none;" />
									
									<script type="text/javascript">
										jQuery(function() {
											var gotoPath = "${gotopath}";
											jQuery("#xh").keydown(function(event) {
												if (13 == event.keyCode) {
													var xh = jQuery("#xh").val();
													selectStudent(xh, gotoPath);

												}
											});
											jQuery("#xh").blur(function() {
												var xsxh = jQuery("#xh").val();
												var fdkc = jQuery("#fdkc").val();
												selectStudent(xsxh,fdkc,gotoPath)
											});

											function selectStudent(xsxh,qdhdid, gotoPath) {
												// ===== 如果地址是GBK编码的（'%3D'表示'='），则进行解码 begin=========
												if (gotoPath.indexOf('%3D') >= 0) {
													gotoPath = decodeURIComponent(gotoPath, 'gbk');
												}
												// ===== 如果地址是GBK编码的（'%3D'表示'='），则进行解码 end=========
												if (gotoPath.split("?").length > 1) {
													gotoPath = gotoPath + "&xh=" + xsxh + "&fdkc=" + fdkc;
												} else {
													gotoPath = gotoPath + "?xh=" + xsxh + "&fdkc=" + fdkc;
												}
												var api = frameElement.api;
												if (api) {
													api.reload(api.get('parentDialog'), gotoPath);
												} else {
													var W = api.opener;
													W.location = gotoPath;
												}
											}
										});

										function showStudentDialog() {
											var fdkc = jQuery("#fdkc").val();
											var goto = encodeURIComponent('${gotopath}'+'&fdkc='+fdkc);
											showDialog('请选择一个学生', 800, 500, 'xsxx_xsgl.do?method=showStudentsForFdyy&goto=' + goto);
										}
									</script>
									<button class="btn_01" type="button" onclick="showStudentDialog();">选择</button>


							</logic:equal>
							<logic:notEqual value="0" name="i">
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="${j.zddm }" />
								</logic:present>
							</logic:notEqual>
						</logic:notEqual>
					</td>
					<%
					if ((i+1) % 2 == 0 && i != jbxxList.size()-1){
				%>
			</tr>
			<tr>
				<%
					}
				%>
				</logic:iterate>
			</tr>
		</logic:present>
	</tbody>
</html>
