<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
function checkFile() {
        var files = document.getElementById("file").value;
        alert("正在上传...");
        var temp = "/xgxt/jyglgetfile.do?act=up&dir="+files;
		document.forms[0].action = temp;
		document.forms[0].submit();
}
function checkFile2() {
        var files = document.getElementById("file2").value;
        alert("正在上传...");
        var temp = "/xgxt/jyglgetfile.do?act2=up&dir2="+files;
		document.forms[0].action = temp;
		document.forms[0].submit();
}
function downcheck(){
       alert("正在删除...");
}

</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>就业管理 - 下载管理 - 表格文件上传</a>
			</p>
		</div>

		<html:form action="jyglgetfile.do" method="post"
			enctype='multipart/form-data'>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="3">
								<span>请选择要上传的文件(不超过20M)</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								教师表格
							</th>
							<td>
								<input type="file" name="file" id="file" value="" />
							</td>
							<td>
								<button onclick="checkFile()">
									上 传
								</button>
							</td>
						</tr>
						<tr>
							<th>
								学生表格
							</th>
							<td>
								<input type="file" name="file2" id="file2" value="" />
							</td>
							<td>
								<button onclick="checkFile2()">
									上 传
								</button>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="3">
								<span>教师表格</span>
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
														文件名
													</td>
													<td align="center" width="30%">
														大小(Byte)
													</td>
													<td align="center" width="20%">
														操作
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
																	href="jyglteafiledown.do?dir=<bean:write name="s" property="wjpath"/>&act=down&filename=<bean:write name="s" property="wjname"/>">下载</a>/
																<a
																	href="jygldelfile.do?wjname=<bean:write name="s" property="wjname"/>&act=del">删除</a>
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
								<span>学生表格</span>
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
														文件名
													</td>
													<td align="center" width="30%">
														大小(Byte)
													</td>
													<td align="center" width="20%">
														操作
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
																	href="jyglstufiledown.do?dir=<bean:write name="s2" property="wjpath"/>&act=down&filename=<bean:write name="s2" property="wjname"/>">下载</a>/
																<a onclick="downcheck()"
																	href="jygldelfile.do?wjname=<bean:write name="s2" property="wjname"/>&act=del2">删除</a>
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
                      alert("上传成功！");
                    </script>
				</logic:equal>
				<logic:equal name="res" value="no">
					<script>
                      alert("上传失败！");
                    </script>
				</logic:equal>
				<logic:equal name="res" value="que">
					<script>
                      alert("请检查该文件是否存在！");
                    </script>
				</logic:equal>
			</logic:notEmpty>

			<logic:notEmpty name="act">
				<logic:equal name="act" value="ok">
					<script>
                      alert("删除成功！");
                    </script>
				</logic:equal>
				<logic:equal name="act" value="no">
					<script>
                      alert("删除失败！");
                    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>

</html>
