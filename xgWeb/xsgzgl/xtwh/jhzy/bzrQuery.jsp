<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script>
		//�����β�ѯ
		function bzrQuerry() {
			var yhm = document.getElementById("yhm").value;
			var xm = document.getElementById("xm").value;
			var zdm = document.getElementById("zdm").value;
			var querry=" where 1=1 ";
			if(yhm != "" && yhm != null){
				querry+=" and yhm like'%";
				querry+=yhm;
				querry+="%' ";
			}
			if(xm != "" && xm != null){
				querry+=" and xm like'%";
				querry+=xm;
				querry+="%' ";
			}
			if(zdm != "" && zdm != null){
				querry+=" and zdm like'%";
				querry+=zdm;
				querry+="%' ";
			}
			GetListData.getbzrInfo(querry,TjQuUserInfo);
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ϵͳά�� - Ȩ��ά�� - �����β�ѯ</a>
			</p>
		</div>

		<html:form action="/jhzy_xtwh" method="post">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�����β�ѯ</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="�ύ" onclick="bzrQuerry();">
										�� ��
									</button>
									<button type="button" name="�ر�" onclick="Close();return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="20%">
								�û���
							</th>
							<td width="80%">
								<input type="text" name="yhm" id="yhm" />
							</td>
						</tr>
						<tr>
							<th>
								�û�����
							</th>
							<td>
								<input type="text" name="xm" id="xm" />
							</td>
						</tr>
						<tr>
							<th>
								�û���
							</th>
							<td>
								<html:select property="zdm" styleId="zdm" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="zList" property="zdm"
										labelProperty="zmc" />
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
