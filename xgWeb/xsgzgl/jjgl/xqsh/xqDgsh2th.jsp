<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript">
		function saveAudit(shzt){
			if(jQuery('#ztbz').val().length > 100){
				showAlert('审核意见字数超过100!');
				return false;
			}
			
			jQuery("#shzt").val(shzt);
			//提交审核
			showConfirmDivLayer("您确定要提交？",{"okFun" : function(){
				var url = "jjgl_xqshgl.do?method=audit";
				ajaxSubFormWithFun("jjglXqshForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				});
			}});
		}
		
		
		function xqshxg(shzt,text){
			var url = "jjgl_jjzg.do?method=submitAudit&tt="+new Date();
			jQuery("#zgshForm").ajaxSubmit( {
				url : url,
				type : "post",
				dataType : "json",
				success : function(data) {
					if (data["message"] == "保存成功！") {
						showAlert("<font color='red'>["+text+"]</font>操作成功！", {}, {
							"clkFun" : function() {
								if (parent.window) {
									refershParent();
								}
							}
						});
					} else {
						showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
					}
				}
			});
		}
	</script>
  </head>
  
  <body>
  		<html:form action="/jjgl_xqshgl" method="post" styleId="jjglXqshForm">
			<html:hidden property="xqid" value="${xqModelMap.xqid }"/>
			<html:hidden property="shzt" styleId="shzt"/>
			<div class='tab' style='width:100%;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>家教信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
					    	<th width="20%">发布人</th>
					    	<td width="30%">${xqModelMap.sqr }</td>
					    	<th width="20%">发布时间</th>
					    	<td width="30%">${xqModelMap.sqsj }</td>
					    </tr>
					    <tr>
					    	<th>家教学科</th>
					    	<td>${xqModelMap.jjxkmc }</td>
					    	<th>学科年级</th>
					    	<td>${xqModelMap.jjnjmc }</td>
					    </tr>
					    <tr>
					    	<th>家教时间</th>
					    	<td>${xqModelMap.jjsj }</td>
					    	<th>家教地点</th>
					    	<td>${xqModelMap.jjdd }</td>
					    </tr>
					    <tr>
					    	<th>家教老师要求</th>
					    	<td colspan="3">${xqModelMap.jjlsyq }</td>
					    </tr>
					    <tr>
					    	<th>备注</th>
					    	<td colspan="3">${xqModelMap.bz }</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>家教子女信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
					    	<th width="20%">姓名</th>
					    	<td width="30%">${znxxMap.xm }</td>
					    	<th width="20%">性别</th>
					    	<td width="30%">${znxxMap.xb }</td>
					    </tr>
					    <tr>
					    	<th width="20%">出生日期</th>
					    	<td width="30%">${znxxMap.csrq }</td>
					    	<th width="20%">年级</th>
					    	<td width="30%">${znxxMap.nj }</td>
					    </tr>
					    <tr>
					    	<th width="20%">在读学校</th>
					    	<td colspan="3">${znxxMap.zdxx }</td>
					    </tr>
					 </tbody>
					 <thead>
						<tr>
							<th colspan="5">
								<span>审核</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
					    	<th width="20%">审核意见<br/><font color="red">(限100字)</font></th>
					    	<td colspan="3">
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
									<button type="button" id="btqd" onclick="saveAudit('1');">
										通过
									</button>
									<button type="button" id="btqd" onclick="saveAudit('2');">
										不通过
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
