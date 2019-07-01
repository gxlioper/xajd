<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/qtsjFunction.js"/>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSzPjpyDAO.js'></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type="text/javascript">
		function jsfs(){
			if (confirm("将要计算综合素质分，确认吗？\n点击\"确定\"，计算数据；\n点击\"取消\"，将放弃计算！")) {
				showTips('处理数据中，请等待......');
				var url = "/xgxt/pjpyszyqwh.do?method=zhszfhzAutoAccount";
				refreshForm(url);
			}
		}
		</script>
	</head>
	<body>
		<html:form action="/pjpyszyqwh">
			<input type="hidden" id="xyV" name="xyV" value="" />
			<input type="hidden" id="zyV" name="zyV" value="" />
			<input type="hidden" id="bjV" name="bjV" value="" />
			
			<table class="formlist">
				<thead>
						<tr>
							<th colspan="2">
								<span>请选择相应计算范围</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								学年
							</th>
							<td>
								<html:select property="xn" disabled="true" styleClass="select"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								学期
							</th>
							<td>
								<html:select property="xq" styleClass="select" disabled="true"
									style="padding-left:80px" styleId="xq">								
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th><bean:message key="lable.xb" /></th>
							<td>
								<html:select property="xydm" onchange="initZyList();initBjList()"
									style="width:180px"  styleId="xy" onmouseover="">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>专业</th>
							<td>
								<html:select property="zydm" onchange="initBjList()" 
									style="width:180px" styleId="zy" onmouseover="">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>班级</th>
							<td>
								<html:select property="bjdm" 
									style="width:180px" styleId="bj"  onmouseover="">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" name="保存" onclick="jsfs();">
										计 算
									</button>
									<button type="button" name="取消" onclick="window.close();return false;">
										取 消
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				
				<input type="hidden" id="message" value="${message }" />
		</html:form>
		<logic:present name="message">
			<script type="text/javascript">
				alert(document.getElementById('message').value);
				Close();
				if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
		</logic:present>
	</body>
</html>
