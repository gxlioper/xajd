<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxjbsz/xmsz/xmwh/js/xmwh.js"></script>
	</head>
	<body>
		<html:form action="/xpjpy_xmwh" method="post" styleId="zjdxXmwhForm">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<html:hidden property="xmdm" styleId="xmdm" />
			<html:hidden property="jdkzjb" styleId="jdkzjb" />
			<html:hidden property="rskzjb" styleId="rskzjb" />
			<html:hidden property="rsfpfs" styleId="rsfpfs" />
			<input type="hidden" name="spzt" id="spzt" value="${spzt}" />
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ŀ����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>��Ŀ����
							</th>
							<td>
								<html:text property="xmmc" styleId="xmmc" maxlength="20" styleClass="text_nor" style="width:155px;"/>
							</td>
							<th>Ӣ������</th>
							<td>
								<html:text property="ywmc" styleId="ywmc" maxlength="120" styleClass="text_nor" style="width:155px;"/>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>��Ŀ����</th>
							<td>
								<html:select property="lxdm" styleId="lxdm" style="width:160px">
									<html:option value=""></html:option>
									<html:options collection="xmlxList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
							<th><font color="red">*</font>��Ŀ����</th>
							<td>
								<html:select property="xzdm" styleId="xzdm" style="width:160px">
									<html:option value=""></html:option>
									<html:options collection="xmxzList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
						    <th>��Ŀ���</th>
							<td>
								<html:text property="xmje" styleId="xmje" maxlength="5" styleClass="text_nor" style="width:155px;" onblur="checkInputNum(this)"/>
							</td>
							<th>
								<font color="red">*</font>��ʾ˳��
							</th>
							<td>
								<html:text property="xssx" styleId="xssx" maxlength="3" onkeyup="checkInputData(this);" style="width:155px;"/>
							</td>
						</tr>
					</tbody>
				</table>
				<div style="height:1px"></div>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2" >
								<span>�������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th  width="20%">
								<font color="red">*</font>�������
							</th>
							<td  width="80%">
								<html:select property="shlc" styleId="shlc" style="width:450px;">
								<html:option value=""></html:option>
								<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�������Ƽ���
							</th>
							<td id="rskzjbTd" >
							</td>				
						</tr>
					</tbody>
				</table>
				<div style="height:1px"></div>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2" >
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th>�������䷽ʽ</th>
							<td>
								<input type="radio" name="rsfpfsView" value="xy">ѧԺ
							<%--&nbsp;--%>
							<%--<label>--%>
								<%--<input type="radio" name="rsfpfsView" value="njxy">�꼶+ѧԺ--%>
							<%--</label>--%>
							<%--&nbsp;--%>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�Ƿ���������
							</th>
							<td>
								<html:radio property="sfrssz" value="1">��</html:radio>
								<html:radio property="sfrssz" value="0">��</html:radio>
							</td>
						</tr>
						<tr>
							<th width="20%">��Ŀ˵��<br/>
								<font color="red">(��500��)</font>
							</th>
							<td width="80%">
								<html:textarea property="xmsm" styleId="xmsm"  style="width:98%"  rows="5" onblur="checkLen(this,500);"/>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								��Ŀ��ӡ��
							</th>
							<td colspan="3">
								<html:hidden property="xmdyb" styleId="xmdyb" />
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										jQuery.MultiUploader({
											maxcount : 3,
											//��׺
											accept : 'png|gif|jpg|zip|rar|doc|docx',
											//����ļ���С ��λM
											maxsize: 10,
											//��Ÿ������������id
											elementid : 'xmdyb'
										});
									});
								</script>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveFormUpdate();">
										�� ��
									</button>
									<button type="button" type="button" onclick="iFClose();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>