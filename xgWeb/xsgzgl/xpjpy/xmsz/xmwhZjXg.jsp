<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/xmsz/js/xmwhZjXg.js"></script>
	</head>
	<body>
		<html:form action="/xpj_xmwh"  styleId="form1"  method="post">
			<div class="prompt" style="display:none;">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<font color="red">
						��ǰ��Ŀ����ѧ�����룬����������޸�
					</font>
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<html:hidden property="xmdm" styleId="xmdm" />
			<html:hidden property="jdkzjb" styleId="jdkzjb" />
			<html:hidden property="rskzjb" styleId="rskzjb" />
			<html:hidden property="rsfpfs" styleId="rsfpfs" />
			<input type="hidden" name="rsfpfsOld" id="rsfpfsOld" value="${xmwhModel.rsfpfs}" />

			<input type="hidden" name="spzt" id="spzt" value="${spzt }" />
			<input type="hidden" name="xmxz" id="xmxz" value="${xmxz}" />
			<input type="hidden" name="shlcOld" id="shlcOld"  value="${xmwhModel.shlc}" />

			<div style='tab;width:100%;height:400px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ŀ����</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th>
								<font color="red">*</font>��Ŀ����
							</th>
							<td>
								<html:text property="xmmc" styleId="xmmc" maxlength="20"
									styleClass="text_nor" />
							</td>
							<th>Ӣ������</th>
							<td>
								<html:text property="xmywmc" styleId="xmywmc" maxlength="100"
									styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>��Ŀ����</th>
							<td>
								<html:select property="lxdm" styleId="lxdm" style="width:155px">
								<html:options collection="xmlxList" property="dm"
									labelProperty="mc" />
								</html:select>
							</td>
							<th>��Ŀ����</th>
							<td>
								<span id="xz"></span>
							</td>
						</tr>
						<tr>
						    <th>��Ŀ���</th>
							<td>
								<html:text property="xmje" styleId="xmje" maxlength="5"
									styleClass="text_nor" />
							</td>
							<th>
								<font color="red">*</font>��ʾ˳��
							</th>
							<td>
								<html:text property="xsxh" styleId="xsxh" maxlength="3" onkeyup="checkInputData(this);" />
							</td>
						</tr>
					</tbody>
				</table>

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
								<html:select property="shlc" styleId="shlc" >
								<html:option value=""></html:option>
								<html:options collection="shlcList" property="splc"
									labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>�������Ƽ���
							</th>
							<td width="80%" id="rskzjbTd" >
							</td>				
						</tr>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>�������䷽ʽ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<td colspan="2">
							<logic:notEqual value="10335" name="xxdm"> 
								<label>
									<input type="radio" name="rsfpfsView" value="xx">ѧУ
								</label>
							</logic:notEqual>
								&nbsp;
								<label>
									<input type="radio" name="rsfpfsView" value="xy"><bean:message key="lable.xb" />
								</label>
								&nbsp;								<label>
									<input type="radio" name="rsfpfsView" value="njxy">�꼶+<bean:message key="lable.xb" />
								</label>
								&nbsp;	
								<logic:notEqual value="10335" name="xxdm"> 							
								<label>
									<input type="radio" name="rsfpfsView" value="njzy">�꼶+רҵ
								</label>
								&nbsp;								<label>
									<input type="radio" name="rsfpfsView" value="bj">�༶
								</label>
								&nbsp;
								<label>
									<input type="radio" name="rsfpfsView" value="cpz">������
								</label>	
								</logic:notEqual>							
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>ѧ����������
							</th>
							<td colspan="3">
								<html:select property="pycc" styleId="pycc">
									<html:option value="all">ȫ��</html:option>>
									<html:options collection="pyccList" property="pyccdm"
												  labelProperty="pyccmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="20%">��Ŀ˵��<br/>
								<font color="red">(����¼��500��)</font></th>
							<td width="80%">
								<html:textarea property="xmsm" styleId="xmsm"  style="width:98%"  rows="5"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table border="0" class="formlist">
				
				<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										�� ��
									</button>
									<button type="button" onclick="iFClose();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			
		</html:form>
	</body>
	</body>
</html>

