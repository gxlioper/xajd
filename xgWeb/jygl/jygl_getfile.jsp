<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
function checkFile() {
        var files = document.getElementById("file").value;
        alert("�����ϴ�...");
        var temp = "/xgxt/jyglgetfile.do?act=up&dir="+files;
		document.forms[0].action = temp;
		document.forms[0].submit();
}
function checkFile2() {
        var files = document.getElementById("file2").value;
        alert("�����ϴ�...");
        var temp = "/xgxt/jyglgetfile.do?act2=up&dir2="+files;
		document.forms[0].action = temp;
		document.forms[0].submit();
}
function downcheck(){
       alert("����ɾ��...");
}

</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ���ع��� - ����ļ��ϴ�</a>
			</p>
		</div>

		<html:form action="jyglgetfile.do" method="post"
			enctype='multipart/form-data'>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="3">
								<span>��ѡ��Ҫ�ϴ����ļ�(������20M)</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								��ʦ���
							</th>
							<td>
								<input type="file" name="file" id="file" value="" />
							</td>
							<td>
								<button onclick="checkFile()">
									�� ��
								</button>
							</td>
						</tr>
						<tr>
							<th>
								ѧ�����
							</th>
							<td>
								<input type="file" name="file2" id="file2" value="" />
							</td>
							<td>
								<button onclick="checkFile2()">
									�� ��
								</button>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="3">
								<span>��ʦ���</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="3">
								<div class="formbox">
									<logic:notEmpty name="fjList">
										<table summary="" class="dateline" align="" width="100%">
											<thead>
												<tr>
													<td align="center" width="50%">
														�ļ���
													</td>
													<td align="center" width="30%">
														��С(Byte)
													</td>
													<td align="center" width="20%">
														����
													</td>
												</tr>
											</thead>
											<tbody>
												<logic:present name="fjList">
													<logic:iterate id="s" name="fjList">
														<tr>
															<td>
																<bean:write name="s" property="wjname" />
															</td>
															<td align="center">
																<bean:write name="s" property="wjdx" />
															</td>
															<td align="center">
																<a
																	href="jyglteafiledown.do?dir=<bean:write name="s" property="wjpath"/>&act=down&filename=<bean:write name="s" property="wjname"/>">����</a>/
																<a
																	href="jygldelfile.do?wjname=<bean:write name="s" property="wjname"/>&act=del">ɾ��</a>
															</td>
														</tr>
													</logic:iterate>
												</logic:present>
											</tbody>
										</table>
									</logic:notEmpty>
								</div>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="3">
								<span>ѧ�����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="3">
								<div class="formbox">
									<logic:notEmpty name="fjList2">
										<table summary="" class="dateline" align="" width="100%">
											<thead>
												<tr>
													<td align="center" width="50%">
														�ļ���
													</td>
													<td align="center" width="30%">
														��С(Byte)
													</td>
													<td align="center" width="20%">
														����
													</td>
												</tr>
											</thead>
											<tbody>
												<logic:present name="fjList2">
													<logic:iterate id="s2" name="fjList2">
														<tr>
															<td>
																<bean:write name="s2" property="wjname" />
															</td>
															<td align="center">
																<bean:write name="s2" property="wjdx" />
															</td>
															<td align="center">
																<a
																	href="jyglstufiledown.do?dir=<bean:write name="s2" property="wjpath"/>&act=down&filename=<bean:write name="s2" property="wjname"/>">����</a>/
																<a onclick="downcheck()"
																	href="jygldelfile.do?wjname=<bean:write name="s2" property="wjname"/>&act=del2">ɾ��</a>
															</td>
														</tr>
													</logic:iterate>
												</logic:present>
											</tbody>
										</table>
									</logic:notEmpty>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<logic:notEmpty name="res">
				<logic:equal name="res" value="ok">
					<script>
                      alert("�ϴ��ɹ���");
                    </script>
				</logic:equal>
				<logic:equal name="res" value="no">
					<script>
                      alert("�ϴ�ʧ�ܣ�");
                    </script>
				</logic:equal>
				<logic:equal name="res" value="que">
					<script>
                      alert("������ļ��Ƿ���ڣ�");
                    </script>
				</logic:equal>
			</logic:notEmpty>

			<logic:notEmpty name="act">
				<logic:equal name="act" value="ok">
					<script>
                      alert("ɾ���ɹ���");
                    </script>
				</logic:equal>
				<logic:equal name="act" value="no">
					<script>
                      alert("ɾ��ʧ�ܣ�");
                    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>

</html>
