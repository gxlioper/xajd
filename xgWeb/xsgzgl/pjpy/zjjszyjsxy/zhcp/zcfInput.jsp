<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
			function checkImpGs(){
				var flag = false;
				var impFilePath = jQuery("#impFilePath").val();
				
				if(impFilePath != ""){
					var filegs = impFilePath.substring(impFilePath.length-3,impFilePath.length);
					
					if(filegs != "xls"){
						alertError("��ѡ��������ݵ�Excelģ��");
					}else{
						flag = true;
					}
				}
				
				return flag;
			}
		
			function impDqfb(){
				refreshForm('zjjs_zhcp_ajax.do?method=inZcf&doType=imp&czxm=${generalPjpyGeneralForm.czxm}');
			}
		</script>
	</head>
	<body>
		<html:form action="/general_pjpy" enctype='multipart/form-data' method="post">
			<div class="tab">
				<table align="center" class="formlist">
					<tbody>
						<tr>
							<th>
								�����ļ�
							</th>
							<td>
								<input type="file" id="impFilePath" name="uploadFile"
									style="width:300px" contenteditable="false" />
							</td>
						</tr>
						<tr>
							<th>˵��</th>
							<td>
								1.��ʹ�ñ�����ģ�����ṩ��<font color="blue">EXCELģ��</font>���е��룬������ܳ������⡣</br>
								2.ģ���е�������Ϣ�����޸ģ���ֻ��Ҫά����Ҫ�����<font color="blue">����</font>��</br>
								3.�ڱ�ϵͳ���Ѵ���ĳѧ���ķ�������ִ�е��룬���ܻᱻ<font color="blue">����</font>��</br>
								4.������븲��ĳѧ����¼�Ļ�������ģ����ɾ����ѧ�����мɣ�
								&nbsp;&nbsp;���ÿշ���<font color="blue">��Ч</font>��</br>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" name="����" onclick="if(checkImpGs){impDqfb()}">
										ȷ��
									</button>
									<button type="button" name="ȡ��" onclick="window.close();return false;">
										ȡ ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<logic:present name="message">
				<script defer>
					alertInfo('${message}');
				</script>
			</logic:present>
			
		</html:form>
	</body>
</html>
