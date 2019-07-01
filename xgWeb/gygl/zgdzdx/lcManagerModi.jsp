<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>		
		<script type="text/javascript">
	     function lcMModiSave(mustFill){
	           var eles = mustFill.split("-");
	           for (i = 0; i < eles.length; i++) {
		           if (document.getElementById(eles[i]).value == "") {
			       alert("请将带\"*\"号的项目输入完整！");
			       return false;
		           }		
	           }	         
              refreshForm("/xgxt/zgdzdx_Gygl.do?method=lcManagerModi&doType=save");		       	           
	     }
	     function showLzRq(){
	          var sfzz = $("sfzz").value;
	          if(sfzz=="否"){
	             $("showlz").style.display="";
	          }else{
	             $("showlz").style.display="none";
	          }
	     }
		</script>
	</head>
	
	<body onload="chckXh();showLzRq();loadView();">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 园区管理 - 层长信息</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<html:form action="/zgdzdx_Gygl" method="post">
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />
			<input type="hidden" id="isview" name="isview"
				value="<bean:write name="isview" scope="request"/>" />			
						 
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>层长信息</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr>
						<th height="22" align="right">
							<font color="red">*</font>园区：
						</th>
						<td height="22" align="left">
							<html:select name="rs" property="yqdm" style="width:150px" styleId="yqdm" 
										onchange="refreshForm('/xgxt/zgdzdx_Gygl.do?method=lcManagerAdd')">
									<html:options collection="yqList" property="dm"
										labelProperty="mc" />
							</html:select>			
							</td>
						<th height="22" align="right">
							<font color="red">*</font>楼栋：
						</th>
						<td height="22" align="left">
							<html:select name="rs" property="lddm" style="width:120px"
										onchange="refreshForm('/xgxt/zgdzdx_Gygl.do?method=lcManagerAdd')" styleId="lddm">										
									<html:options collection="ldList" property="dm"
											labelProperty="mc" />
							</html:select>								
						</td>
					</tr>
					<tr>
						<th height="22" align="right">
							园区负责人：
						</th>
						<td height="22" align="left">
							<bean:write name="rsfzr" property="xm"/>
						</td>
						<th height="22" align="right">
							<font color="red">*</font>楼层：
						</th>
						<td height="22" align="left">
							<html:select name="rs"  property="cs" style="width:120px" styleId="cs">									
									<html:options collection="lcList" property="dm"
											labelProperty="mc" />								
							</html:select>								
						</td>
					</tr>
					<tr>
						<th height="22" align="right">
							负责人电话：
						</th>
						<td height="22" align="left">
							<bean:write name="rsfzr" property="lxdh"/>
						</td>
						<th height="22" align="right">
							<font color="red">*</font>层长(学号)：
						</th>
						<td height="22" align="left">
							<html:text name="rs" property="cz" onblur="chckXh()"></html:text>
						</td>
					</tr>						
					<tr>
						<th height="22" align="right">
							楼长：
						</th>
						<td height="22" align="left">
							<bean:write name="rslz" property="lz"/>
						</td>
						<th height="22" align="right">
							<font color="red">*</font>任职日期：
						</th>
						<td height="22" align="left">
						<html:text name="rs" property="rzrq" styleId="rzrq"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('rzrq','y-mm-dd');" readonly="true" />
					    </td>												
					</tr>		
					<tr>						
						<th height="22" align="right">
							楼长电话：
						</th>
						<td height="22" align="left">
							<bean:write  name="rslz" property="lxdh"/>
						</td>
						<th height="22" align="right">
							层长电话：
						</th>
						<td height="22" align="left">
							<html:text name="rs" property="lxdh"></html:text>
						</td>
					</tr>
					<tr>
						<th height="22" align="right">
							层长电子邮件：
						</th>
						<td height="22" align="left">
							<html:text name="rs" property="dzyx"></html:text>
						</td>
						
						<th height="22" align="right">
							是否在职：
						</th>
						<td height="22" align="left">
							<html:select name="rs" property="sfzz"  style="width:120px" styleId="sfzz" onchange="showLzRq()">
								<html:option value="是">是</html:option>
								<html:option value="否">否</html:option>
							</html:select>							
						</td>
					</tr>
				<tr style="display: none" id="showlz">
					<th height="22" align="right">
						离职日期：
					</th>
					<td height="22" align="left">
						<html:text name='rs' property="lzrq" styleId="lzrq"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="if($('rzrq').value.replace(' ','')==''){return false;}else{return showCalendar('lzrq','y-mm-dd');}"
							readonly="true" />
					</td>
					<th height="22" align="right">

					</th>
					<td height="22" align="left">

					</td>
				</tr>
				<tr align="left">
							<th align="right">
								备注：
							</th>
							<td colspan="4">
								<html:textarea  name="rs" property='bz' style="width:99%"
									rows='5' />
							</td>
						</tr>
					</tbody>
					<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
								<button id="buttonSave" 
									onclick="lcMModiSave('yqdm-lddm-cs-cz-rzrq')"
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
