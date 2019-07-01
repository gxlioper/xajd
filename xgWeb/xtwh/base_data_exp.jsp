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
				alertInfo("请选择需要导出的基础表！");
				return false;
			}
			dataExport();
		}
		
		</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>系统维护 - 系统初始化 - 基础数据初始化</a>
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
									<span>基础数据导出</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button" name="提交" onclick="jcsjdc()">
											数据导出
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th width="20%">
									请选择基础数据表
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
      		alert("保存成功！");
      	</script>
				</logic:equal>
				<logic:equal name="doresult" value="false">
					<script type="text/javascript">
      		alert("保存失败！");
      	</script>
				</logic:equal>
			</logic:present>
			<logic:present name="update">
				<logic:equal name="update" value="true">
					<script type="text/javascript">
      		alert("更新成功！");
      	</script>
				</logic:equal>
				<logic:equal name="update" value="false">
					<script type="text/javascript">
      		alert("更新失败！");
      	</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
