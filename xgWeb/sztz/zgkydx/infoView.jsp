<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<base target="_self">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript">
		jQuery(function(){
			onShow();
		})
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzDao.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>	
		<html:form action="/zgkydx_sztz" method="post">
			<input type="hidden" name="pkValue" value="<bean:write name="pkValue"/>">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�������չ - ��չ��Ϣ��� - ��� -�鿴
				</div>
			</div>
			<div id="rsT">
				<table width="100%" class="tbstyle">

					<tr style="height:22px">
						<td colspan="8" align="center">
							<b>������չ��</b> (
							<bean:write name="rsxs" property="xn" />&nbsp;
							<bean:write name="rsxs" property="xqmc" />
							ѧ��)
						</td>
					</tr>

					<tr>
						<td width="13%">
							�� ��
						</td>
						<td width="12%">
							<bean:write name="rsxs" property="xm" />
						</td>
						<td width="7%">
							�Ա�
						</td>
						<td width="4%">
							<bean:write name="rsxs" property="xb" />
						</td>
						<td width="4%">
							�� ��
						</td>
						<td width="22%">
							<bean:write name="rsxs" property="mzmc" />
						</td>
						<td width="13%">
							��������
						</td>
						<td width="25%">
							<bean:write name="rsxs" property="csrq" />
						</td>
					</tr>
					<tr>
						<td>
							������
						</td>
						<td>
							<bean:write name="rsxs" property="hkszd" />
						</td>
						<td colspan="3">
							������ò
						</td>
						<td>
							<bean:write name="rsxs" property="zzmmmc" />
						</td>
						<td>
							ѧ У
						</td>
						<td>
							<bean:write name="xxmc" />
						</td>
					</tr>
					<tr>
						<td>
							Ժ ( ϵ ) רҵ
						</td>
						<td colspan="5">
							<bean:write name="rsxs" property="xymc" />
							&nbsp;&nbsp;
							<bean:write name="rsxs" property="zymc" />
						</td>
						<td>
							��Уʱ��
						</td>
						<td>
							<bean:write name="rsxs" property="rxrq" />
						</td>
					</tr>
					<tr>
						<td>
							֤����
						</td>
						<td colspan="5">
							<bean:write name="zsbh" />
						</td>
						<td>
							��֤ʱ��
						</td>
						<td>
							&nbsp;
						</td>
					</tr>

				</table>
				<!--  <table width="100%" class="tbstyle">
					<tbody width="100%" class="tbstyle" id="jj">

					</tbody>	
				</table>-->
				<logic:iterate name="kmXmList" id="s">
					<table width="100%" class="tbstyle">
						<tr>
							<td colspan="8">
								<b> <bean:write name="s" property="kmm" />
								</b> 
								<bean:write name="s" property="sm" />
								<br>
							</td>
						</tr>
						<logic:iterate id="b" name="s" property="rsList">	
							<tr>
								<td>
									<bean:write name="b" property="hdnrmc" />: 
								</td>
							</tr>
							<tr>
								<td>
									<table width="100%" class="tbstyle">
										<tbody width="100%" class="tbstyle" id="jj<bean:write name="b" property="hdnrdm" />">

										</tbody>	
									</table>
								</td>
							</tr>
						</logic:iterate>
					</table>
				</logic:iterate>
			</div>
			<div class="buttontool" align="center">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;">
					�� ��
				</button>
			</div>
		</html:form>

	</body>
<script type="text/javascript">
function onShow(){	          
	var pkValue = document.getElementById("pkValue").value;
	dwr.engine.setAsync(false);	
	getSztzDao.dao_getKmmc(function(data1){
		if(data1!=null && data1!=""){
			getSztzDao.dao_getKmdm(function(data2){
				for(var i=0;i<data1.length;i++){
					//DWRUtil.addRows("jj",['dd'],["��Ŀ����:"+data1[i]]);		
					var kmdm = data2[i];
					getSztzDao.dao_getHdmc(kmdm,function(data3){
						if(data3!=null && data3!=""){
							getSztzDao.dao_getHddm(kmdm,function(data4){
								for(var j=0;j<data3.length;j++){
									//DWRUtil.addRows("jj",['dd'],["���������:"+data3[j]]);	
									var hddm = data4[j];
									var id = "jj"+hddm;
									getSztzDao.dao_getNrmc(pkValue,hddm,function(data6){
										if(data6!=null && data6!=""){
										
											getSztzDao.dao_getSxmc(kmdm,hddm,function(data5){
												if(data5!=null && data5!=""){
													DWRUtil.addRows(id,['dd'],data5);	
												}
											});
											
											for(var k=0;k<data6.length;k++){
												var addNr = new Array();
												var nr = new Array();
												addNr = data6[k].split("!!@@!!");
												for(var l=0;l<addNr.length-1;l++){
													var sxdm = addNr[l];
													getSztzDao.dao_getXlmc(sxdm,function(data7){
														if(data7 != null && data7!=""){
															nr[l]= data7[0];
														}else{
															nr[l]= addNr[l];	
														}
													});													
												}
												DWRUtil.addRows(id,['dd'],nr);	
											}
										}
									});
								}
							});
						}	
					});
				}
			});
		}
	});	
	dwr.engine.setAsync(true);				
}
</script>
</html>

