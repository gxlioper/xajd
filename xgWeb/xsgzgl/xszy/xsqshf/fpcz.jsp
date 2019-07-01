<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type='text/javascript'>
		function editFpcz(){
			var type = jQuery("#id").val()==""?"save":"update";
			var url = "xszyXsqshf.do?method=editFpcz&type="+type;
			ajaxSubFormWithFun("XszyQshfForm", url, function(data) {
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
	<body style="width: 100%">
		<html:form action="/xszyXsqshf" method="post" styleId="XszyQshfForm" onsubmit="return false;">
		<html:hidden property="lddm" styleId="lddm"/>
		<html:hidden property="qsh" styleId="qsh"/>
		<html:hidden property="id" styleId="id"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<tbody>
						</br>
						<tr>
						    <th>
						    <div align="left">
						    <font color="#FF9933">把您选择的记录分配至</font>
									<html:select property="ssyxdm" styleId="ssyxdm">
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
							</html:select>
						    </div>
							</th>
						</br>
						</tr>
					</tbody>
				
				 </table>
				 </div>
			    <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="editFpcz()">
									确定
								</button>
								<button type="button" onclick="Close();return false;">
									取消
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

