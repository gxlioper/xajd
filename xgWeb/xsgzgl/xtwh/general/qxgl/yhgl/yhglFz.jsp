<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：lt -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" defer="defer">
		jQuery(function(){
			var num = jQuery("#selectId").val().split("!!array!!").length;
			jQuery("#yh_num_td").html(num);
			if(num == 1){
				var zdms = '${yhxxMap.zdm}';
				var zdmCheckboxs = jQuery("input[type=checkbox][name=zdm]");
				for(var i = 0; i < zdmCheckboxs.length; i++){
					var temp = zdmCheckboxs.eq(i);
					if(zdms.indexOf(temp.val()) >= 0){
						temp.attr("checked","checked");
					}
				}
			}
		});
				//批量修改用户所属组，启用状态
		function yhfz(act){
			var url = "xtwh_qxgl_yhgl_ajax.do?method=yhfz";			
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			var ifGly = "";
			var selectId=jQuery("#selectId").val();
				var parameter = {};
				var array = new Array();
				var zdmCheckboxs = jQuery("input[type=checkbox][name=zdm]:checked");
						if(zdmCheckboxs.length == 0){
							alertError("请选择至少一个组！");
							return false;
						}
						var i = 0;					
						parameter["array_primarykey_checkVal"]=selectId;
						var zdms = new Array();
						for(var i = 0; i < zdmCheckboxs.length; i++){
							zdms.push(zdmCheckboxs.eq(i).val());
						}
						parameter["str_zdm"]=zdms.join(",");
						//parameter["str_qx"]=jQuery("[name=qx]:checked").val();
						parameter["str_qx"]="1";
						confirmInfo("确定要修改选中用户的所在组吗？",function(ok){
							if(ok=="ok"){
								jQuery.post(url,parameter,function(result){


									 showAlert(result+"！",{},{"clkFun":function(){
							    				if (parent.window){
							    					refershParent();
							    				}
						    			}});
								});					
							}else{
								return false;
							}
						});
				}
		</script>
		</head>
		<body>
<!-- 用户所属组修改弹出层 -->
		<html:form action="/xtwh_qxgl_yhgl" method="post">
		<input type="hidden" id="selectId" value="${selectId}"/>
	<div id="yhfz" style="tab;overflow-x:hidden;overflow-y:auto;height:320px;margin-bottom: 0px;" >
		<table width="100%" border="0" class="formlist">
				
		<thead>
			<tr>
				<th colspan="2">
					<span>用户分组</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr height='30'>
				<th style="width: 25%;">
					选中用户数量
				</th>
				<td id="yh_num_td">
				</td>
			</tr>			
			<tr height='30'>
				<th style="width: 25%;">
					<font color="red">*</font>分组
				</th>
				<td>
<%--					<html:select property="zdm" style="width:200px" styleId="select_zdm_fz">--%>
<%--						<html:options collection="yhzList" property="dm" labelProperty="mc" />--%>
<%--					</html:select>--%>
					
					<logic:present name="yhzList">
						<logic:iterate id="o" name="yhzList" indexId="i">
							<html:checkbox property="zdm" value="${o.dm}">${o.mc}</html:checkbox>
							<br/>
						</logic:iterate>								
					</logic:present>
				</td>
			</tr>			
			<!-- <tr height='30'>
				<th>
					启用状态
				</th>
				<td>
					<input type="radio" id="qx_qy" name="qx" value="1" checked="checked"/>启用
					<input type="radio" id="qx_bqy" name="qx" value="0" />停用
				</td>
			</tr>
			 -->
		</tbody>
		</table>
		</div>
		
		<div>
			<table class="formlist">
				<tfoot>
						<tr>
							<td colspan="4">
					"<span class="red">*</span>"为必填项
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div class="btn">
						<button type="button"  onclick="yhfz()">
							保 存
						</button>
						<button type="button"  onclick="iFClose();return false;">
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