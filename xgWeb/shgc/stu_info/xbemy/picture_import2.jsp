<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- ͷ�ļ� -->
<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
	<body style="overflow:scroll;overflow-y:hidden;overflow-x:hidden">		
		<script type="text/javascript">		
			function uploadFile() {
				var tmp = document.getElementById("file").value;
				if (tmp.value == "" || tmp.substring(tmp.length - 4, tmp.length).toLowerCase() != ".jpg") {
					alert("��ѡ����ļ����Ϸ���ֻ���ϴ�.jpg��ʽ��ͼƬ����ѡ�����" +  tmp.substring(tmp.length - 4, tmp.length).toLowerCase() +"��ʽ��ͼƬ��");
					document.getElementById("file").value = "";
					return false;
				} else {
					document.forms[0].action = "/xgxt/uploadPicture.do?method=uploadPicture2&doType=upload";
					if($("moditag"))
						document.forms[0].action +="&moditag="+document.getElementById("moditag").value; 
						document.forms[0].submit();
					return true;
				}
			}
			
			function divtmpclose() {
				parent.document.getElementById("tmpdiv1").innerHTML = "";
				try{
					parent.hiddenMessage(true,true);
				}catch(e){				
				}
			}
		</script>
		
		<html:form action='/uploadPicture.do' method='post' enctype='multipart/form-data'>
			<input type="hidden" name="id" value="${id}" />
			<input type="hidden" name="type" value="${type}" />
			<logic:present name="moditag">
				<input type="hidden" id="moditag" name="moditag"��value="${moditag}" />
			</logic:present>
			<logic:notPresent name="moditag">
				<input type="hidden" id="moditag" name="moditag" value="" />
			</logic:notPresent>

			<logic:present  name="dataImported">
				<script>
					alert("�����ɹ���");
					parent.document.getElementById("reloadF").click();
			    </script>
			</logic:present>

			<div class="tab">
				<table width="99%" border="0" class="formlist">
				    <thead>
				    	<tr>
				        	<th colspan="2"><span>�ϴ���Ƭ</span></th>
				        </tr>
				    </thead>
				    <tbody>
				      <tr>
				      	<th>��ѡ��Ҫ�����ͼƬ</th>
						<td>
							<input type="file" name="file" id="file" value="" />
						</td>
					  </tr>
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="4">
				          <div class="btn">
				            <button class="button2" onclick="uploadFile()">
								�ϴ�
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="divtmpclose()">
								�ر�
							</button>
				          </div>
				        </td>
				      </tr>
				    </tfoot>
				</table>
			</div>
		<logic:present name="errMsg">
			<script language="javascript">
				alert('<bean:write name="errMsg"/>');
			</script>
		</logic:present>
		</html:form>
	</body>
</html>
