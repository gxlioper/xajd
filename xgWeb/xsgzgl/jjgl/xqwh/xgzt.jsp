<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript">
		function jjztxgSubmit(){
			if(jQuery('#ztbz').val().length > 100){
				showAlert('修改原因字数超过100!');
				return false;
			}
			//提交
			showConfirm("您确定要提交？",{"okFun" : function(){
				var url = "jjgl_xqwhgl.do?method=changeJJztSubmit";
				ajaxSubFormWithFun("jjglXqwhForm",url,function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				});
			}});
		}
	</script>
  </head>
  
  <body>
		<html:form action="/jjgl_xqwhgl" method="post" styleId="jjglXqwhForm">
			<html:hidden property="xqid" value="${xqxxMap.xqid }"/>

			<div class='tab' style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>状态信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="30%">当前状态</th>
					    	<td  width="70%">${xqxxMap.jjztmc}</td>
					    	
						</tr>
						<tr>
							<th >家教操作</th>
					    	<td >
					    		<html:select property="jjzt" styleId="jjzt" value="${xqxxMap.jjzt}" style="width:70%">
					    			<logic:present name="xqxxMap">					    			
					    			<logic:equal value="0" name="xqxxMap" property="jjzt">
					    				<html:option value="4">关闭家教</html:option>
					    			</logic:equal>
					    			<logic:equal value="1" name="xqxxMap" property="jjzt">
					    				<html:option value="0">退家教</html:option>
					    				<html:option value="3">交协议书</html:option>
					    				<html:option value="4">关闭家教</html:option>
					    			</logic:equal>
					    			<logic:equal value="2" name="xqxxMap" property="jjzt">
										<html:option value="0">退家教</html:option>
										<html:option value="3">交协议书</html:option>
										<html:option value="4">关闭家教</html:option>
					    			</logic:equal>
					    			<logic:equal value="3" name="xqxxMap" property="jjzt">
					    				<html:option value="4">关闭家教</html:option>
					    			</logic:equal>
					    			</logic:present>
					    		</html:select>
					    		
					    	</td>
						</tr>
						<tr>
					    	<th width="30%">修改原因<br/><font color="red">(限100字)</font></th>
					    	<td >
					    		<html:textarea styleId="ztbz" property="ztbz" rows="3" style="width:90%"></html:textarea>
					    	</td>
					    </tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" id="btqd" onclick="jjztxgSubmit();">
										提交
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
