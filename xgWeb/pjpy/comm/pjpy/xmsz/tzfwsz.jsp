<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script>
			jQuery(function(){
				var xmdm = jQuery('#xmdm').val();
				jQuery.ajax({
					type:'post',
					url:'pjpy_ty_ajax.do?method=getTzfw&xmdm='+xmdm,
					dataType:'json',
					success:function(data){
						if (null != data){
							jQuery.each(data,function(i,n){
								jQuery('input[value="'+n+'"]').attr('checked',true);
							});
						}
					}
				})
			});
			
			function saveCheck(){
				var tzxmdms=document.getElementsByName("tzxmdm");
				if(tzxmdms&&tzxmdms.length){
					for(var i=0;i<tzxmdms.length;i++){
						if(tzxmdms[i].checked){
							return true;
						}
					}
				}
				alertInfo("��ѧ��������ƺ�����ѡ������һ�");
				return false;
			}
		</script>
	</head>

	<body>
		<html:form action="/pjpy_ty_tzfw" method="post">
			<html:hidden property="xmdm" styleId="xmdm"/>
		
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<logic:notEqual name="doType" value="update">
										<button type="button" onclick="saveUpdate('pjpy_ty_tzfw.do?method=xzxm&xmdm=${pjpyTzfwszForm.xmdm }','')">
											��һ��
										</button>
									</logic:notEqual>
									<button type="button" type="reset" onclick="if(saveCheck()){saveUpdate('pjpy_ty_tzfw.do?method=tzfwsz&doType=save','');}">
										����
									</button>
									<button type="button" id="buttonSave" onclick="window.close();return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								��Ŀ����
							</th>
							<td>
								<html:select property="xmdm" style="width:200px" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xmList" property="xmdm"
										labelProperty="xmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>��ѧ��</th>
							<td>
								<logic:iterate id="x" name="xmList">
									<logic:notEqual value="02" name="x" property="xmlx">
										<logic:notEqual value="${pjpyTzfwszForm.xmdm }" name="x" property="xmdm">
											<input type="checkbox" name="tzxmdm" value="${x.xmdm }"/> ${x.xmmc }
										</logic:notEqual>									
									</logic:notEqual>
								</logic:iterate>
							</td>
						</tr>
						<tr>
							<th>�����ƺ�</th>
							<td>
								<logic:iterate id="x" name="xmList">
									<logic:notEqual value="01" name="x" property="xmlx">
										<logic:notEqual value="${pjpyTzfwszForm.xmdm }" name="x" property="xmdm">
											<input type="checkbox" name="tzxmdm" value="${x.xmdm }"/> ${x.xmmc }
										</logic:notEqual>									
									</logic:notEqual>
								</logic:iterate>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>

		<logic:present name="message">
			<input type="hidden" name="message" id="message" value="${message}"/>
			<input type="hidden" name="doType" id="doType" value="${doType}"/>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</logic:present>
		
		
	</body>
</html>
