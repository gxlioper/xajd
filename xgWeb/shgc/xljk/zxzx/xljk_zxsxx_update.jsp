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
			refreshForm('/xgxt/xljk_zxsxx_update.do?act=xljk_zxsxx&doType=update');			
		}
	</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>������ - ������ѯ - ��ѯʦ��Ϣ - �޸���ѯʦ��Ϣ</a>
			</p>
		</div>


		<html:form action="/xljk_zxsxx_update">
			<logic:present name="list" scope="request">
				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">
										"
										<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button onclick="checkText()">
											����
										</button>
										<button onclick="Close();return false;">
											�ر�
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									��ѯʦ���
								</th>
								<td width="35%">
									<html:text name="list" property="zxxbh" readonly="true" />
								</td>
								<th width="15%" nowrap="nowrap">
									�Ա�
								</th>
								<td width="35%">
									<html:select name="list" property="sex_dm" styleId="sex_dm">
										<html:option value=""></html:option>
										<html:options collection="sexList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									��ѯʦ����
								</th>
								<td>
									<html:text name="list" property="zxxxm" readonly="true" />
								</td>
								<logic:present name="showZxszc">
									<th>
										ר��
									</th>
									<td>
										<html:select name="list" property="zxszc" styleId="zxszc" style="width:70%">
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
								</th>
								<td colspan="3" style="height:96">
									<html:textarea name="list" property="zxxzg" rows="8" cols="60"></html:textarea>
								</td>
							</tr>
							<tr>
								<th>
									�� ��
								</th>
								<td colspan="3" style="height:96">
									<html:textarea name="list" property="jj" rows="8" cols="60"></html:textarea>
								</td>
							</tr>
						</tbody>
					</table>
			</logic:present>
			</div>
			<div id="tmpdiv">
			</div>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="ok">
					<script>
					alert("���³ɹ�!");
					window.dialogArguments.document.getElementById("search_go2").click();
					Close();
				</script>
				</logic:equal>
				<logic:equal name="message" value="no">
					<script>
					alert("����ʧ��!");
				</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
	</html>