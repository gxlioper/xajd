<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/gzsd/tjgzpz/js/fbglgzpztj.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/gzsd/gzyl.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
			jQuery(function($) {
				$("#template").hide();
				$("#gzdm").bind("change",function(){
					//������������
					loadTjgz($(this).val());
					$("select[name=tjpz]").change();
				});
				$("#gzdm").change();
				$("input[name=tjcheckbox]").live("click",function(){
					if(jQuery(this).is(":checked")){
						jQuery(this).parents("tr").attr("style","background: none repeat scroll 0% 0% rgb(203, 228, 248);");
					}else{
						jQuery(this).parents("tr").removeAttr("style");
					}
				});
			});
		</script>
	</head>
	<body>
		<html:form method="post"
		 styleId="form" action="/fbglgzpztj" >
		<div style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;' >
			<table width="100%" border="0" class="formlist">
				<thead>
						<tr>
							<th colspan="2">
								<span>��������</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="20%">
							<span class="red">*</span>��������
						</th>
						<td align="left">
							<html:hidden property="pzgzid" styleId="pzgzid"/>
							<html:text property="pzgzmc" styleId="pzgzmc"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="red">*</span>��������
						</th>
						<td align="left">
							<html:select property="gzdm" styleId="gzdm" 
									style="width:125px;">
									<html:options collection="gzlist" property="gzdm"
										labelProperty="gzmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="red">*</span>����״̬
						</th>
						<td align="left">
							<html:radio property="qyzt" value="0">ͣ��</html:radio>
							<html:radio property="qyzt" value="1">����</html:radio>
						</td>
					</tr>
				</tbody>
			</table>
			<div id="gztj" ng-app="gzpzYlDiv">
			</div>
		</div>
		<div id="template">
				<table width="100%" border="0" class="formlist">
					<thead>
							<tr>
								<th colspan="2" align="right">
									<span name="tjgzmc"></span>
									<div style="margin-left: 400px; font-weight:blod;">Ԥ���� <font name="gzyl" color="red"></font></div>
								</th>
							</tr>
					</thead>
					<tbody class="gztjTbody">
						<tr name="mytools">
							<th align="right" width="2%" style="color: blue;">
							</th>
							<td align="left">
								<img src="xsxx/fbgl/images/add-icons-1.gif" alt="����" onclick="addTjgz(this);return false;"/>
								<img src="xsxx/fbgl/images/delete-icons-2.gif" alt="ɾ��" onclick="delTjgz(this);return false;"/>
								<img src="xsxx/fbgl/images/up-icons-3.gif" alt="����" onclick="upTjgz(this);return false;"/>
								<img src="xsxx/fbgl/images/down-icons-4.gif" alt="����" onclick="downTjgz(this);return false;"/>
								<%--<img src="xsxx/fbgl/images/close-icons.gif" alt="ɾ������" onclick="delAllTjgz(this);return false;"/>
							--%></td>
						</tr>
						<tr name="templateTr">
							<th align="right">
								<input type="checkbox" name="tjcheckbox"/>
							</th>
							<td align="left">
							</td>
						</tr>
					</tbody>
				</table>
		</div>
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="2">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button"  onclick="save('fbgltjgz.do?method=saveGzpz','pzgzmc');return false;" id="buttonSave">
									��  ��
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button"  onclick="iFClose();" id="buttonClose">
									��  ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		</html:form>
	</body>
</html>
