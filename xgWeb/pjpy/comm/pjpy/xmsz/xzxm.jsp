<%@ page language="java" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/pjpyCommService.js'></script>
		<script type="text/javascript" src="js/pjpy/comm/pjpy/pjxmList.js"></script>
	</head>

	<body onload="initPjpy();">
		<html:form action="/pjpy_ty_jdsz" method="post">
			<input type="hidden" id="pjxn" value="${pjxtszModel.pjxn }"/>
			<input type="hidden" id="pjxq" value="${pjxtszModel.pjxq }"/>
			<input type="hidden" id="pjnd" value="${pjxtszModel.pjnd }"/>
		
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="if(''!=$('xmdm').value){saveUpdate('pjpy_ty_jdsz.do?method=jdsz','')}else{alertInfo('请选择项目名称！');}">
										下一步
									</button>

									<button type="button" id="buttonSave" onclick="window.close();return false;">
										关&nbsp;&nbsp;闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="2">
								<span>请选择您要设置的评奖项目</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="32%">
								项目类型
							</th>
							<td width="68%">
								<html:select property="xmlx" style="width:200px" styleId="xmlx">
									<html:option value=""></html:option>
									<html:options collection="xmlxList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								项目性质
							</th>
							<td>
								<html:select property="xmxz" style="width:200px" styleId="xmxz">
									<html:option value=""></html:option>
									<html:options collection="xmxzList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								项目范围
							</th>
							<td>
								<html:select property="xmfw" style="width:200px" styleId="xmfw">
									<html:option value=""></html:option>
									<html:options collection="xmfwList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>项目名称
							</th>
							<td>
								<html:select property="xmdm" style="width:200px" styleId="xmdm">
									<html:option value=""></html:option>
									<html:options collection="xmList" property="xmdm"
										labelProperty="xmmc" />
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
