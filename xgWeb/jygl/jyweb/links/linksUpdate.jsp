<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/jyweb" method="post" enctype="multipart/form-data">
			<input type="hidden" name="pkValue" value="${pkValue }" />
			<input type="hidden" name="file" value="${rs.tpdz }" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>友情链接维护</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<logic:present name="doType">
										<logic:notEqual value="view" name="doType">
											<button name="提交" id="saveButton"
												onclick="saveUpdate('jywebUseCheckSession.do?method=linksUpdate&doType=modify','ljmc-ljwz-xssx');">
												保存
											</button>
										</logic:notEqual>
									</logic:present>
									<logic:notPresent name="doType">
										<button name="提交"  id="saveButton"
											onclick="saveUpdate('jywebUseCheckSession.do?method=linksUpdate&doType=save','ljmc-ljwz-xssx');">
											保存
										</button>
									</logic:notPresent>
									<button onclick="window.close();return false;">
											关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="14%">
								<span class="red">*</span>链接名称
							</th>
							<td width="86%">
								<html:text property="ljmc" maxlength="20" value="${rs.ljmc}"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>链接网址
							</th>
							<td>
								 <div class="pos" style="z-index:1">
								 	<html:text property="ljwz" maxlength="50" value="${rs.ljwz}"
								 		onkeypress="checkURL(this)" 
						        		onblur="checkURL(this)"
								 	></html:text>
						       		<div id="urlErrow" class="hide" >
								        <p>网址格式不正确</p>
								    </div>
						        </div>
								
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>显示顺序
							</th>
							<td>
								<html:text property="xssx" value="${rs.xssx}"
									onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								图片
							</th>
							<td>
								<html:file property="file" />
								<span class="red">(宽123*高37,限1M)</span>
							</td>
						</tr>
						
					</tbody>
				</table>
			</div>
		</html:form>

		<logic:present name="message">
			<script>
 			alert("${message}${errMsg}");
 			if(window.dialogArguments){
 				window.close();
 				dialogArgumentsQueryChick();
 			}
 		</script>
		</logic:present>

	</body>
</html>
