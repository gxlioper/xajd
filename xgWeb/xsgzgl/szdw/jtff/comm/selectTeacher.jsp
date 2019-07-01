<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	</head>
<tbody>
	<%
		List<HashMap<String,String>> jbxxList = (List<HashMap<String,String>>)request.getAttribute("jbxxList");
	%>
	<logic:present name="jbxxList">
		<tr>
			<logic:iterate id="j" name="jbxxList" indexId="i">
				<th width="16%">
						<logic:equal value="0" name="i">
							<logic:notEqual name="type" value="update">
								<font color="red">*</font>
							</logic:notEqual>
						</logic:equal>
					${j.zdmc }
				</th>
				<td width="34%">
				<logic:equal value="tea" name="userType">
						<html:hidden property="zgh" styleId="zgh"/>
						<logic:present name="jbxx">
							<bean:write name="jbxx" property="${j.zddm }"/>
						</logic:present>
					</logic:equal>
				<logic:notEqual  value="tea" name="userType">
						<logic:equal value="0" name="i">
							
							<logic:notEqual name="type" value="update">
								<input type="text" name="zgh" value="${jbxx.zgh }" id="zgh" style="width:120px;"/>
								
								<script type="text/javascript">
			jQuery(function(){
			 var gotoPath="${path}";
			jQuery("#zgh").keydown(function(event){
				     if(13==event.keyCode){
				      var zgh=jQuery("#zgh").val();
					  selectTeacher(zgh,gotoPath);
					
					}
				});
			  jQuery("#zgh").blur(function(){
				     var v_zgh=jQuery("#zgh").val();
					selectTeacher(v_zgh,gotoPath)
				});
				function selectTeacher(v_zgh,gotoPath){
			
				if (gotoPath.split("?").length > 1){
					gotoPath = gotoPath+"&zgh="+v_zgh;
				} else {
					gotoPath = gotoPath+"?zgh="+v_zgh;
				}
				var api = frameElement.api;
					if (api){
						api.reload(api.get('parentDialog') ,gotoPath);
					} else{
						var W = api.opener;
						W.location=gotoPath;
				} 
				}
			});
		</script>
								<button class="btn_01" type="button"  
										onclick="showDialog('请选择一个职工',800,500,'jtff_util.do?method=showTeachers&jtlb=${jtlb}&goto=${path}');">选择</button>
							</logic:notEqual>
							
							<logic:equal name="type" value="update">
								<input type="hidden" name="zgh" value="${jbxx.zgh }" id="zgh"/>
								${jbxx.zgh }
							</logic:equal>
								
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
</html>