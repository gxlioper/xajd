<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<tbody>
	<%
	  List<HashMap<String,String>> jbxxList = (List<HashMap<String,String>>)request.getAttribute("jbxxList");
	%>
	<logic:present name="jbxxList">
		<tr>
			<logic:iterate id="j" name="jbxxList" indexId="i">
				<th width="16%">
					<logic:notEqual value="stu" name="userType">
						<logic:equal value="0" name="i">
							<logic:notEqual name="type" value="update">
								<font color="red">*</font>
							</logic:notEqual>
						</logic:equal>
					</logic:notEqual>
					${j.zdmc }
				</th>
				<td width="34%">
					<logic:equal value="stu" name="userType">
						<html:hidden property="xh" styleId="xh" value="${xh }"/>
						<logic:present name="jbxx">
							<bean:write name="jbxx" property="${j.zddm }"/>
						</logic:present>
					</logic:equal>
					
					<logic:notEqual value="stu" name="userType">
						<logic:equal value="0" name="i">
							<input type="text" name="xh" value="${jbxx.xh }" id="xh" style="width:120px;"/>
							<script type="text/javascript">
			jQuery(function($) {
			   var gotoPath="${path}";
			   $("#xh").blur(function(){
				     var xsxh=jQuery("#xh").val();
					selectStudent(xsxh,gotoPath)
				});
				$("#xh").keydown(function(event){
				     if(13==event.keyCode){
				      var xsxh=jQuery("#xh").val();
					selectStudent(xsxh,gotoPath);
					}
				});
				});
				function selectStudent(xsxh,gotoPath){
				
				if (gotoPath.split("?").length > 1){
					gotoPath = gotoPath+"&xh="+xsxh;
				} else {
					gotoPath = gotoPath+"?xh="+xsxh;
				}
				var api = frameElement.api;
					if (api){
						api.reload(api.get('parentDialog') ,gotoPath);
					} else{
						var W = api.opener;
						W.location=gotoPath;
				} 
			};

			function showSelectStudentDialog() {
			    var gotoPath = encodeURIComponent('${path}');
                showDialog('请选择一个学生',800,480,'xpj_pjjg.do?method=showStudents&goto='+gotoPath);
            }
		</script>
							<button class="btn_01" type="button"  
									onclick="showSelectStudentDialog();return false;">选择</button>
						</logic:equal>
						<logic:notEqual value="0" name="i">
							<logic:present name="jbxx">
								<bean:write name="jbxx" property="${j.zddm }"/>
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