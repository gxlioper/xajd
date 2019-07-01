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
			refreshForm('/xgxt/xljk_zxsxx_update.do?act=xljk_zxsxx&doType=update');			
		}
	</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>心理健康 - 心理咨询 - 咨询师信息 - 修改咨询师信息</a>
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
										<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button onclick="checkText()">
											保存
										</button>
										<button onclick="Close();return false;">
											关闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									咨询师编号
								</th>
								<td width="35%">
									<html:text name="list" property="zxxbh" readonly="true" />
								</td>
								<th width="15%" nowrap="nowrap">
									性别
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
									咨询师姓名
								</th>
								<td>
									<html:text name="list" property="zxxxm" readonly="true" />
								</td>
								<logic:present name="showZxszc">
									<th>
										专长
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
									资 格
								</th>
								<td colspan="3" style="height:96">
									<html:textarea name="list" property="zxxzg" rows="8" cols="60"></html:textarea>
								</td>
							</tr>
							<tr>
								<th>
									简 介
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
					alert("更新成功!");
					window.dialogArguments.document.getElementById("search_go2").click();
					Close();
				</script>
				</logic:equal>
				<logic:equal name="message" value="no">
					<script>
					alert("更新失败!");
				</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
	</html>