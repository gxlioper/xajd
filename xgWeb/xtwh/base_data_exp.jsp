<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/sharedFunction.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getBaseData.js"></script>
		<script type="text/javascript"
			src="/xgxt/js/webServiceFunction/wsFunction.js"></script>
		<script type="text/javascript" src="/xgxt/js/bjlhdx/bjlhdxBaseData.js"></script>
		<script type="text/javascript">
		function jcsjdc(){
			$("tableName").value=$("jcsjsz").value;
			if($("jcsjsz").value==""){
				alertInfo("��ѡ����Ҫ�����Ļ�����");
				return false;
			}
			dataExport();
		}
		
		</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ϵͳά�� - ϵͳ��ʼ�� - �������ݳ�ʼ��</a>
			</p>
		</div>

		<html:form action="/chgPass" method="post">
			
				<input type="hidden" name="webSerTb" id="webSerTb"
					value="" />
				<input type="hidden" name="tableName" id="tableName"
					value="" />
				<div class="tab" id="displayCols" name="displayCols" >
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>�������ݵ���</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button" name="�ύ" onclick="jcsjdc()">
											���ݵ���
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th width="20%">
									��ѡ��������ݱ�
								</th>
								<td width="80%">
									<html:select property="jcsjsz" styleId="jcsjsz"  style="width:150px"
										>
										<html:option value=""></html:option>
										<html:options collection="jcsjszList" property="tableName"
										labelProperty="tableMc" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			
			<logic:present name="doresult">
				<logic:equal name="doresult" value="true">
					<script type="text/javascript">
      		alert("����ɹ���");
      	</script>
				</logic:equal>
				<logic:equal name="doresult" value="false">
					<script type="text/javascript">
      		alert("����ʧ�ܣ�");
      	</script>
				</logic:equal>
			</logic:present>
			<logic:present name="update">
				<logic:equal name="update" value="true">
					<script type="text/javascript">
      		alert("���³ɹ���");
      	</script>
				</logic:equal>
				<logic:equal name="update" value="false">
					<script type="text/javascript">
      		alert("����ʧ�ܣ�");
      	</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
