<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript">
			function saveForm(){

				if (jQuery.trim(jQuery("#zgh").val()) == ""){
					showAlert("�������Ŷ�ָ����ʦ����!");
					return false;
				}

				var url = "tdjs.do?method=sctd";
				
				jQuery("#cjtdForm").attr("action",url);
				jQuery("#cjtdForm").submit();
			}


			var flg = false;
			
			jQuery(function(){

				var demoHtml = "�밴���¸�ʽ����ָ����ʦ���ţ���д��ɺ�������һ���������Ŷ���Ϣ����ҳ�棩\r\n\r\n";
					demoHtml+="���磺\r\n";
					demoHtml+="20110019\r\n20100019\r\n20090026";
					demoHtml+="\r\n���ߣ�\r\n";
					demoHtml+="20110019 20100019 20090026";

				jQuery("#zgh").val(demoHtml);
				jQuery("#zgh").bind("focus",function(){
					
					if (jQuery(this).css("color") == "#999"){
						jQuery(this).val("");
						jQuery(this).css("color","");
					}
				});

				jQuery("#zgh").bind("blur",function(){
					if (jQuery(this).val() == ""){
						jQuery(this).val(demoHtml);
						jQuery(this).css("color","#999");
					}
				});
				
			})
		</script>
	</head>
	<body>
		<html:form action="/tdjs" method="post" styleId="cjtdForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>�Ŷ�����</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="20%">
								�꼶
							</th>
							<td width="80%">
								<html:select property="nj" styleId="nj">
									<html:options collection="njList" property="nj" labelProperty="nj"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>ָ����ʦ����
							</th>
							<td>
								<html:textarea property="zgh" styleId="zgh" style="width:95%;color:#999;" rows="20" >
								</html:textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										��һ��
									</button>
									<button type="button" type="button" onclick="iFClose();">
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

