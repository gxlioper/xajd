<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/sharedFunction.js"></script>
		<script type="text/javascript" src="js/dataImp.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript">
			function repval(obj) {
				obj.value = "";
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title}</a>
			</p>
		</div>
	
	
		<html:form action='/xtwhOther' method='post'enctype='multipart/form-data'>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ƭ��������</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="�� ��" onclick="checkFile1()">
										�� ��
									</button>
									
									<button type="button" name="ȡ ��" onclick="Close();return false;" id="btn_close" style="display:none">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="20%">
								��ѡ��Ҫ������ļ�
							</th>
							<td width="80%">
								<input type="file" name="file" id="file" class="text_nor" value="" contenteditable="false" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<font color="red">
									˵��:    ֻ�ܵ���zip��ʽ��ѹ���ļ����ļ���С���ܳ���50M��
									���ұ������Ƭ����ѹ���ĸ�Ŀ¼��,��Ƭ�ĸ�ʽΪ"ѧ��.jpg",
									����ͼƬ��С�벻Ҫ����100k,���ѧ������Ƭ�Ѵ��ڣ���Ѹ�������Ƭ����
								</font>
							</td>
						</tr>
					</tbody>
				</table>
				</div>
				<script  language="javascript">
					function checkFile1(){
						var tmp = $("file").value.toLowerCase();
						if(tmp==""){
						$("file").value = "";
						alert("��ѡ���·�������ϣ�������ѡ��");
						return false;
						}
						if(tmp.substring(tmp.length-3,tmp.length)!="zip"){
							alert("�ϴ��ļ��ĸ�ʽ������.zip��ʽ�ģ�");
							return false;
						}
						tmp = tmp.substring(0,tmp.lastIndexOf("\\"))+"\\";
						//alert(tmp);
						BatAlert.showTips("�ļ��ϴ��У����Ժ�...");
						refreshForm("picpldr.do?isFile=yes&filepath="+tmp);
					}
				</script>
			<logic:present name="rsMap">
			  <table id="tabPri" class="tbstyle" width="100%">
  				<thead>
					<tr ><td align ="center">���������</td></tr>
					
				</thead>	
				<tr><td width="100%"><span style="color:blue">�ɹ���Ϣ:</span><br/>
						<textarea rows="5" cols="73" style="width: 100%" ><bean:write name="sb"/></textarea>
						</td>
				</tr>
				<tr >
						<td><span style="color:red">ʧ����Ϣ:</span><br />
						<textarea rows="5" cols="73" style="width: 100%" ><bean:write name="sb1"/></textarea>
						</td>
				</tr>
				<tr >
						<td><span style="color:red">������Ϣ(֧�ָ�ʽ��.jpg .gif .png .bmp):</span><br/>
						<textarea rows="5" cols="73" style="width: 100%" ><bean:write name="sb2"/></textarea>
						</td>
				</tr>
				</table>
  </logic:present>
	<script>
		if(window.dialogArguments){
			ele('btn_close').display ="";
		}
	</script>
</html:form>
</body>
</html>
