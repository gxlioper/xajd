<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
		
			function uploadFile() {
				var tmp = document.getElementById("file").value;
				if (tmp.value == "" || tmp.substring(tmp.length - 4, tmp.length).toLowerCase() != ".jpg") {
					alert("��ѡ����ļ����Ϸ���ֻ���ϴ�.jpg��ʽ��ͼƬ����ѡ�����" +  tmp.substring(tmp.length - 4, tmp.length).toLowerCase() +"��ʽ��ͼƬ��");
					document.getElementById("file").value = "";
					return false;
				} else {
					var xsxh =  document.getElementById("pk").value;
					if(xsxh==""){
						alert("û���ҵ��ϴ���Ƭ��ѧ�� ���뷵����һҳ");
					}else{
						document.forms[0].action = "/xgxt/xsgbxx.do?method=sczp&act=add";
						document.forms[0].submit();
						var btnlist = document.getElementsByTagName("input")
						for(var i = 0;i<btnlist.length;i++){
							document.getElementsByTagName("input")[i].disabled= true;
						}
						var btnlist = document.getElementsByTagName("button")
						for(var i = 0;i<btnlist.length;i++){
							document.getElementsByTagName("button")[i].disabled= true;
						}
					}
				}
			}
		</script>
		<html:form action='/uploadPicture.do' method='post'
			enctype='multipart/form-data'>
			<logic:present name="moditag">
				<input type="hidden" id="moditag" name="moditag"
					value="<bean:write name="moditag"/>" />
			</logic:present>
			<logic:present name="pk">
				<input type="hidden" id="pk" name="pk" value="<bean:write name="pk"/>" />
			</logic:present>
			<logic:notPresent name="pk">
				<input type="hidden" id="pk" name="pk" value="" />
			</logic:notPresent>
			<logic:notPresent name="moditag">
				<input type="hidden" id="moditag" name="moditag" value="" />
			</logic:notPresent>
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��Ṥ��  - ѧ���ɲ�  -�ϴ���Ƭ</a>
				</p>
			</div>
			<logic:notEmpty name="dataImported">
				<script>
				alert("�����ɹ���");
				window.close();
				</script>
			</logic:notEmpty>
			<div class="tab">
							<table width="100%"  border="0" class="formlist">
							 <thead>
			    				<tr>
			        				<th colspan="6"><span>��ѡ��Ҫ�ϴ�����Ƭ(ֻ����Ӣ����ĸ����������)</span></th>
			        			</tr>
			   				 </thead>
			   				 <tbody>
							<tr>
								<td align="center">
									<input type="file" name="file" id="file" value="" />
								</td>
							</tr>
							</tbody>
							<tfoot>
								 <tr>
						        <td colspan="6"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
						          <div class="btn">
						          	<button class="button2" onclick="uploadFile()">
										�ϴ�
									</button>
									<button name="ȡ��" onclick="Close();return false;" id="buttonClose">�� ��</button>					           
						          </div>
						          </td>
						      </tr>
							</tfoot>
					</table>
					<br />
				</div>
			</div>
		</html:form>
	</body>
</html>
