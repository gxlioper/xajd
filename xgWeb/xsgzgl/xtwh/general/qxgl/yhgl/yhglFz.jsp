<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�lt -->
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
				//�����޸��û������飬����״̬
		function yhfz(act){
			var url = "xtwh_qxgl_yhgl_ajax.do?method=yhfz";			
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			var ifGly = "";
			var selectId=jQuery("#selectId").val();
				var parameter = {};
				var array = new Array();
				var zdmCheckboxs = jQuery("input[type=checkbox][name=zdm]:checked");
						if(zdmCheckboxs.length == 0){
							alertError("��ѡ������һ���飡");
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
						confirmInfo("ȷ��Ҫ�޸�ѡ���û�����������",function(ok){
							if(ok=="ok"){
								jQuery.post(url,parameter,function(result){


									 showAlert(result+"��",{},{"clkFun":function(){
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
<!-- �û��������޸ĵ����� -->
		<html:form action="/xtwh_qxgl_yhgl" method="post">
		<input type="hidden" id="selectId" value="${selectId}"/>
	<div id="yhfz" style="tab;overflow-x:hidden;overflow-y:auto;height:320px;margin-bottom: 0px;" >
		<table width="100%" border="0" class="formlist">
				
		<thead>
			<tr>
				<th colspan="2">
					<span>�û�����</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr height='30'>
				<th style="width: 25%;">
					ѡ���û�����
				</th>
				<td id="yh_num_td">
				</td>
			</tr>			
			<tr height='30'>
				<th style="width: 25%;">
					<font color="red">*</font>����
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
					����״̬
				</th>
				<td>
					<input type="radio" id="qx_qy" name="qx" value="1" checked="checked"/>����
					<input type="radio" id="qx_bqy" name="qx" value="0" />ͣ��
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
					"<span class="red">*</span>"Ϊ������
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div class="btn">
						<button type="button"  onclick="yhfz()">
							�� ��
						</button>
						<button type="button"  onclick="iFClose();return false;">
							�� ��
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