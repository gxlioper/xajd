<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>		
		<script type="text/javascript">
		     function yqzbMAddSave(mustFill){
		           var eles = mustFill.split("-");
		           for (i = 0; i < eles.length; i++) {
			           if (document.getElementById(eles[i]).value == "") {
				       alert("请将带\"*\"号的项目输入完整！");
				       return false;
			           }		
		           }
		           var yqdm=$("yqdm").value;
		           var xm=$("xm").value.replace(" ","");
		           var rq=$("rq").value; 
		           refreshForm("/xgxt/zgdzdx_Gygl.do?method=yqzbManageModi&doType=Save");			          	 	           
		     }
		</script>	
	</head>
	<body onload="loadView();">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 园区管理 - 园区值班情况</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/zgdzdx_Gygl" method="post">
			
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />	
			<input type="hidden" id="isview" name="isview"
				value="<bean:write name="isview" scope="request"/>" />			
						
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">			 
				<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>园区值班情况</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr>
						<td height="22" align="right" width="15%">
							<font color="red">*</font>园区：
						</td>
						<td height="22" align="left">
							<html:select name="rs"  property="yqdm"  style="width:120px" styleId="yqdm" >
								<html:options collection="yqList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
						<td height="22" align="right" width="15%">
							<font color="red">*</font>值班人：
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="xm"></html:text>
						</td>
					</tr>					
					<tr>
						<td height="22" align="right">
							<font color="red">*</font>日期：
						</td>
						<td height="22" align="left">
							<html:text name="rs"  property="rq" styleId="rq"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('rq','y-mm-dd');" readonly="true" />
						</td>
						<td height="22" align="right">
							
						</td>
						<td height="22" align="left">
						
					    </td>
					</tr>												
					<tr align="left">
							<td align="right">
								值班记录：
							</td>
							<td colspan="4">
								<html:textarea name="rs"  property='bz' style="width:99%"
									rows='8' />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr bgcolor="EEF4F9" align="center">
							<td colspan="4">
								<div class="btn">
								
										<button id="buttonSave" onclick="yqzbMAddSave('yqdm-xm-rq')"
											style="width: 80px">
											保	存
										</button>
								
									&nbsp;&nbsp;
									<button id="buttonClose" onclick="Close();return false;"
										style="width: 80px">
										关	闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				</logic:notEmpty>				
		</html:form>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
				alert("操作成功！");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
				alert("操作失败,或系统中已存在与带\"*\"号项目相同的记录，请检查输入的数据后再提交！");
			</script>
		</logic:equal>
	</body>

</html>
