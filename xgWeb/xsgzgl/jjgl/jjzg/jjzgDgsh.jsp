<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${jjzgForm.sqid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${jjzgForm.splcid}&shid=${data.shid}");
		});
		
		function saveAudit(){
			var shzt=jQuery("#shjg").val();
			jQuery("#shzt").val(shzt);
			if (jQuery("#shyj").val() == ""){
				showAlertDivLayer("请填写审核意见！");
				return false;
			}
			if (jQuery("#shyj").val().length>200){
				showAlertDivLayer("审核意见不能超过200字");
				return false;
			}
			var text=jQuery("#shjg").find("option:selected").text();
			//提交审核
			showConfirmDivLayer("您确定<font color='red'>[" + text + "]</font>该申请吗？",{"okFun":function(){
					zx(shzt,text);
			}});
		}
		
		
		function zx(shzt,text){
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
		<html:form action="/jjgl_jjzg" method="post" styleId="zgshForm">
			<html:hidden property="id" value="${jjzgForm.sqid }"/>
			<html:hidden property="xh" value="${userName }"/>
			<html:hidden property="shzt"/>
		
			<div class='tab' style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr style="height: 45px;">
							<th width="15%">学号</th>
							<td width="25%">${jbxx.xh }</td>
							<th width="15%">姓名</th>
							<td width="25%">${jbxx.xm }</td>
							<td rowspan="3" align="center">
								<img src="xsxx_xsgl.do?method=showPhoto&xh=${jbxx.xh }" width="100" height="120" >
							</td>
					    </tr>
					    <tr style="height: 45px;">
					    	<th>性别</th>
					    	<td>${jbxx.xb }</td>
					    	<th>年级</th>
					    	<td>${jbxx.nj }</td>
					    </tr>
					    <tr style="height: 45px;">
					    	<th>学院</th>
					    	<td>${jbxx.xymc }</td>
					    	<th>班级</th>
					    	<td>${jbxx.bjmc }</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>家教信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr style="height: 45px;">
					    	<th>擅长科目</th>
					    	<td colspan="4">
					    		${jjzgForm.xkmca } 、${jjzgForm.xkmcb } 、${jjzgForm.xkmcc }
					    	</td>
					    </tr>
						<tr style="height: 45px;">
					    	<th>针对年级</th>
					    	<td>
					    		${jjzgForm.jjnjmc }
					    	</td>
					    	<th>联系电话</th>
					    	<td colspan="2">
					    		${jjzgForm.lxdh }
					    	</td>
					    </tr>
					    <tr style="height: 45px;">
					    	<th>备注</th>
					    	<td colspan="4">
					    		${jjzgForm.bz }
					    	</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>审核信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>审核结果
							</th>
							<td colspan="4" id="shjgSpan">
								
							</td>
						</tr>
						<tr>
						<th width="20%">
								<font color="red">*&nbsp;</font> 审核意见&nbsp;
								<br />
								<font color="red">(限200字)</font>
							</th>
							<td colspan="4">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=jjzg&id=shyj" />
								<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);">${rs.shyj}</textarea>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>审核信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="5" id="shlccx">
							
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
									<button type="button" id="btqd" onclick="saveAudit();">
										保 存
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
