<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script>
	function checkText(){
			var zxxbh=document.all["zxxbh"].value;
			if ( zxxbh==""){
				alert ("����д��ѯʦ���");
				document.all["zxxbh"].focus();
				return false;
			}
			if ( zxxbh.length>19){
				alert ("���ֻ����д20���ַ�");
				document.all["zxxbh"].focus();
				return false;
			}
			var sex=document.all["sex_dm"].value;
			if ( sex==""){
				alert ("����д�Ա�");
				document.all["sex_dm"].focus();
				return false;
			}
			var zxxxm=document.all["zxxxm"].value;
			if ( zxxxm==""){
				alert ("����д����");
				document.all["zxxxm"].focus();
				return false;
			}
			if ( zxxxm.length>10){
				alert ("���ֻ����д10������");
				document.all["zxxxm"].focus();
				return false;
			}
			var zxxzg=document.getElementById("zxxzg").value;
			if ( zxxzg.length>10){
				alert ("��ѯʦ�ʸ����ֻ����д10������");
				document.all["zxxzg"].focus();
				return false;
			}
			var jj=document.getElementById("jj").value;
			if ( jj.length>250){
				alert ("��ѯʦ������ֻ����д250������");
				document.all["jj"].focus();
				return false;
			}
			underDealWith();
			refreshForm('/xgxt/xljk_zxsxx_add.do?act=xljk_zxsxx&doType=Insert');
		}

	</script>
</head>
<body>

			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>������ - ������ѯ - ��ѯʦ��Ϣ - ������ѯʦ��Ϣ</a>
				</p>
			</div>

	<html:form action="/xljk_zxsxx_add">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button onclick="checkText()">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				<tbody>
	
			<tr>
				<th>
					<font color="red">*</font>��ѯʦ���
				</th>
				<td>
					<html:text property="zxxbh" maxlength="15" onkeypress="noSpace(this);value=value.replace('+','��')" onblur="noSpace(this);value=value.replace('+','��')"/>
				</td>
				<th>
					<font color="red">*</font>��ѯʦ����
				</th>
				<td>
					<html:text property="zxxxm" maxlength="20" onkeypress="noSpace(this);value=value.replace('+','��')" onblur="noSpace(this)"/>
				</td>
			</tr>
			<tr>
				<th>
					<font color="red">*</font>�Ա�
				</th>
				<td>
					<html:select property="sex_dm" styleId="sex_dm" style="width:70%">
						<html:option value=""></html:option>
						<html:options collection="sexList" property="en"
							labelProperty="cn" />
					</html:select>
				</td>
				
				
				<!-- ��ʾ��ѯʦר�� -->
				<logic:present name="showZxszc">
				<th>
					ר��
				</th>
				<td>
					<html:select property="zxszc" styleId="zxszc" style="width:70%">
						<html:options collection="zxszcList" property="dm"
							labelProperty="mc" />
					</html:select>
				</td>
				</logic:present>
				<logic:notPresent name="showZxszc">
					<th></th>
					<td></td>
				</logic:notPresent>
			</tr>
			<tr>
				<th>
						�� ��
				</td>
				<td colspan="3" style="height:96">
					<html:textarea property="zxxzg" rows="8" cols="60"></html:textarea>
				</td>
			</tr>
			<tr>
				<th>
						�� ��
				</td>
				<td colspan="3" style="height:96">
					<html:textarea property="jj" rows="8" cols="60"></html:textarea>
				</td>
			</tr>
			</tbody>
		</table>
		</div>
		<div id="tmpdiv">
		</div>
		<logic:notEmpty name="message">
			<logic:equal name="message" value="ok">
				<script>
					alert("����ɹ�!");
					window.dialogArguments.document.getElementById("search_go2").click();
					Close();
				</script>
			</logic:equal>
			<logic:equal name="message" value="no">
				<script>
					alert("����ʧ��!��ѯʦ����Ѿ����ڣ�");
					document.getElementById("tmpdiv").innerHTML = "";	
				</script>
			</logic:equal>
		</logic:notEmpty>
	</html:form>
</body>
</html>
