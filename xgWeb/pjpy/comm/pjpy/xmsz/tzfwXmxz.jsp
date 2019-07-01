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
		<html:form action="/pjpy_ty_tzfw" method="post">
			<input type="hidden" id="pjxn" value="${pjxtszModel.pjxn }"/>
			<input type="hidden" id="pjxq" value="${pjxtszModel.pjxq }"/>
			<input type="hidden" id="pjnd" value="${pjxtszModel.pjnd }"/>
		
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="if(''!=$('xmdm').value){saveUpdate('pjpy_ty_tzfw.do?method=tzfwsz','')}">
										��һ��
									</button>

									<button type="button" id="buttonSave" onclick="window.close();return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="2">
								<span>��ѡ����Ҫ���õ�������Ŀ</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="32%">
								��Ŀ����
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
								��Ŀ����
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
								��Ŀ��Χ
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
								<font color="red">*</font>��Ŀ����
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
