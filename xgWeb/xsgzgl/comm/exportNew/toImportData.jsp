<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/comm/message.js"></script>
		<script type="text/javascript">
			jQuery(function(){
			});
			//��������
			function importData(){
				if(notNullImportTable() && notNullImportFile()){
					var tips = importLoading();
					var url ="import.do?method=importData";
					jQuery("#importForm").ajaxSubmit({
						url:url,
						type:"post",
						dataType:"json",
						data:"",
						success:function(data){
							tips.close();
							if(data != null){
								var drms = jQuery("#drms").val();
								setImportRsulte(data["importModel"],drms);
							}else{
								setImportRsulte(null);
							}

							//�ص����뺯��
							callbackImportFunction();
						}
					});
				}
			}
			//���ص��뺯��
			function callbackImportFunction(){
				if(window.parent){
					if(window.parent.importFunction){
						window.parent.importFunction();
					}else{
						//ˢ�²�ѯҳ��
						jQuery(parent.window.document).find('#search_go').click();
					}
				}
			}

		</script> 
	</head>
	<body>
		<html:form styleId="importForm" action="/import.do?method=toImportData"
			method="post" enctype="multipart/form-data">
			<html:hidden property="drmkdm" styleId="drmkdm"/>
			<!-- isImport: 0:��������  1:�ѵ������� -->
			<input type="hidden" id="isImport" value="0" />
			<!-- ������������ -->
			<input type="hidden" id="errorDataNum"  name="errorDataNum" value="0" />
			<input type="hidden" id="drms" name="drms" value="0" />
			<input type="hidden" id="drzs" name="drzs" value="0" />
			<input type="hidden" id="cgs" name="cgs" value="0" />
			<input type="hidden" id="cws" name="cws" value="0" />
			<div class="tab">
				<table width="100%" border="0" class=" formlist" cellpadding="0"
					cellspacing="0">
					<thead>
						<tr>
							<td colspan="4">
								<span>��������</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								ѡ��ģ��
							</th>
							<td colspan="3">
								<logic:notEmpty name="impTableList"><%--
									<s:if test="%{impTableList.size() == 1}">
										<s:property value="impTableList[0].drbzwmc"/>
										<s:hidden name="drbm" id="drbm" value="%{impTableList[0].drbm}"></s:hidden>
									</s:if>
									<s:else>--%>
								<html:select property="drbm" styleId="drbm" disabled="false"
									style="width:280px;">
									<options value="-1">��ѡ��</options>
									<html:options collection="impTableList" property="drbm"
										labelProperty="drbzwmc" />
								</html:select>		
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<th>
								����ģ��
							</th>
							<td colspan="3">
								<a href="javascript:void(0);" class="name" onclick="downloadTemplate();" >����EXCELģ��</a>
								&nbsp;&nbsp;
								<a href="javascript:void(0);" class="name" onclick="downloadTemplate('dbf');" >����DBFģ��</a>
							</td>
						</tr>
						<tr>
							<th>
								�ϴ�����
							</th>
							<td colspan="3">
								<html:file property="importFile" styleId="importFile" style="width:300px;"/>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button name="btn_tj" onclick="importData();" type="button">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				
				<table width="100%" border="0" class=" formlist" cellpadding="0" style="margin-top: 5px;"
					cellspacing="0">
					<thead>
						<tr>
							<td colspan="4">
								<span>������</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								���ͳ��
							</th>
							<td colspan="3" id="importResult">
								��
							</td>
						</tr>
						<tr>
							<th>
								ʧ������
							</th>
							<td colspan="3">
								<a href="javascript:void(0);" class="name" onclick="downloadErrorData();">����ʧ������</a>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button name="btn_tj" onclick="iFClose();" type="button">
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
