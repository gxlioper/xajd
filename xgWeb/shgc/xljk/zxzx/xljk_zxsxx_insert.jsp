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
				alert ("请填写咨询师编号");
				document.all["zxxbh"].focus();
				return false;
			}
			if ( zxxbh.length>19){
				alert ("最多只能填写20个字符");
				document.all["zxxbh"].focus();
				return false;
			}
			var sex=document.all["sex_dm"].value;
			if ( sex==""){
				alert ("请填写性别");
				document.all["sex_dm"].focus();
				return false;
			}
			var zxxxm=document.all["zxxxm"].value;
			if ( zxxxm==""){
				alert ("请填写姓名");
				document.all["zxxxm"].focus();
				return false;
			}
			if ( zxxxm.length>10){
				alert ("最多只能填写10个汉字");
				document.all["zxxxm"].focus();
				return false;
			}
			var zxxzg=document.getElementById("zxxzg").value;
			if ( zxxzg.length>10){
				alert ("咨询师资格最多只能填写10个汉字");
				document.all["zxxzg"].focus();
				return false;
			}
			var jj=document.getElementById("jj").value;
			if ( jj.length>250){
				alert ("咨询师简介最多只能填写250个汉字");
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
					<em>您的当前位置:</em><a>心理健康 - 心理咨询 - 咨询师信息 - 增加咨询师信息</a>
				</p>
			</div>

	<html:form action="/xljk_zxsxx_add">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button onclick="checkText()">
										保 存
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				<tbody>
	
			<tr>
				<th>
					<font color="red">*</font>咨询师编号
				</th>
				<td>
					<html:text property="zxxbh" maxlength="15" onkeypress="noSpace(this);value=value.replace('+','＋')" onblur="noSpace(this);value=value.replace('+','＋')"/>
				</td>
				<th>
					<font color="red">*</font>咨询师姓名
				</th>
				<td>
					<html:text property="zxxxm" maxlength="20" onkeypress="noSpace(this);value=value.replace('+','＋')" onblur="noSpace(this)"/>
				</td>
			</tr>
			<tr>
				<th>
					<font color="red">*</font>性别
				</th>
				<td>
					<html:select property="sex_dm" styleId="sex_dm" style="width:70%">
						<html:option value=""></html:option>
						<html:options collection="sexList" property="en"
							labelProperty="cn" />
					</html:select>
				</td>
				
				
				<!-- 显示咨询师专长 -->
				<logic:present name="showZxszc">
				<th>
					专长
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
						资 格
				</td>
				<td colspan="3" style="height:96">
					<html:textarea property="zxxzg" rows="8" cols="60"></html:textarea>
				</td>
			</tr>
			<tr>
				<th>
						简 介
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
					alert("保存成功!");
					window.dialogArguments.document.getElementById("search_go2").click();
					Close();
				</script>
			</logic:equal>
			<logic:equal name="message" value="no">
				<script>
					alert("保存失败!咨询师编号已经存在！");
					document.getElementById("tmpdiv").innerHTML = "";	
				</script>
			</logic:equal>
		</logic:notEmpty>
	</html:form>
</body>
</html>
