<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
		<script language="javascript">
	      function hiddenField() {
		     i = document.getElementsByTagName("select").length;
		    for (j = 0; j < i; j++) {
			  document.getElementsByTagName("select")[j].style.visibility = "hidden";
		    } 
	      }
	      function setTBGbed(){
	          totalBed.innerText="0";
	          boyBed.innerText="0";
	          girlBed.innerText="0";
	      }
	    </script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 宿舍分配 - 宿舍划分</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/zgdzdx_Gygl" method="post">
			<html:hidden name="zgdzdxForm" property="conditionSqlValue"
				styleId="conditionSqlValue" />
			<input type="hidden" name="oldCondiSqlValue" id="oldCondiSqlValue"
				value="<bean:write name="oldCondiSqlValue"/>" />
			<input type="hidden" name="userName" id="userName"
				value="<bean:write name="userName"/>" />
			<input type="hidden" name="xxdm" id="xxdm" value="" />
				<!-- 宿舍划分 -->
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="2">
								<span>宿舍划分</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<tr align="center">
							<th width="38%" align="left" rowspan="2">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;校区名：
								<html:select property="xiaoqu" style="width:180px"
									styleId="xiaoqu">
									<html:option value="">--请选择--</html:option>
									<html:options collection="xiaoQuList" property="dm"
										labelProperty="mc" />
								</html:select>
								<br>
								楼栋性别限定：
								<html:select property="xbxd" style="width:180px" styleId="xbxd"
									onchange="ldLbIinit()">
									<html:options collection="xbXdList" property="dm"
										labelProperty="mc" />
								</html:select>
								<br>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="red">*</font>楼栋名：
								<html:select property="lddm" styleId="lddm" style="width:180px"
									onfocus="beforSubmit();"
									onchange="SsIinit();lcLbIinit();YhfSsIinit();">
									<html:options collection="ldList" property="dm"
										labelProperty="mc" />
								</html:select>

								<br>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								层号：
								<html:select property="cs" styleId="cs" style="width:180px"
									onfocus="beforSubmit();" onchange="SsIinit();YhfSsIinit();">
									<html:options collection="lcList" property="dm"
										labelProperty="mc" />
								</html:select>
							</th>
							<td width="62%" align="left">
								<font color="red">*</font>年级：
								<html:select property="nj" styleId="nj" style="width:100px"
									onfocus="beforSubmit();" onchange="YhfSsIinit();getData();">
									<html:option value="">--请选择--</html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;&nbsp;
								<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" style="width:150px" styleId="xy"
									onfocus="beforSubmit();" onchange="YhfSsIinit();getData();">
									<html:option value="">--请选择--</html:option>
									<html:options collection="xyList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;&nbsp;&nbsp;未划分学生总数(人):
								<span id="allbody" style="width: 60px"> <bean:write
										name="allbody" scope="request" /> </span>(男):
								<span id="allboy" style="width: 60px"> <bean:write
										name="allboy" scope="request" /> </span>(女):
								<span id="allgirl" style="width: 60px"> <bean:write
										name="allgirl" scope="request" /> </span>
								<br>
								&nbsp;&nbsp;&nbsp;已划分床位总数(张):
								<span id="totalBed" style="width: 60px"> <bean:write
										name="totalBed" scope="request" /> </span> (男):
								<span id="boyBed" style="width: 60px"> <bean:write
										name="boyBed" scope="request" /> </span> (女):
								<span id="girlBed" style="width: 60px"> <bean:write
										name="girlBed" scope="request" /> </span> (混合):
								<span id="bgBed" style="width: 60px"> <bean:write
										name="bgBed" scope="request" /> </span>
							</td>
						</tr>
						</tbody>
					</table>
					<table class="permissionlist" border="0" align="center" style="width: 100%">
						<tr align="center">
							<td colspan="3">
								<table width="100%" align="center" class="">
									<tr align="center" bgcolor="#D0E0EE">
										<td align="center">
											未划分宿舍
										</td>
										<td>
										</td>
										<td align="center">
											已划分情况
										</td>
									</tr>
									<tr align="center">

										<td align="center" style="width:40%;" align="left">
											<font color="red" style="font-size:10px;">提示：按住Ctrl或Shift键(或按下鼠标左键上下移动)进行多选</font>
											<br>
											楼栋名称/层数/寝室号/总位数
											<html:select property="oracleItem" style="width:100%; " onmouseover="null"
												size="27" styleId="oracleList" multiple="multiple">
												<html:options collection="ssxxList" property="dm"
													labelProperty="mc" />
											</html:select>
										</td>

										<td>
											<span id="btn1">
												<table width="97%" align="center" class="tbstyle">
													<tr align="center">
														<td>
															按 房 间
															<br>
															<br>
															<button class="button2" onclick="afj_addColum()"
																style="width:50px;height: 20px" title="宿舍划分">
																&rarr;

															</button>
															<br>
															<br>
															<button class="button2" onclick="afj_delColum()"
																style="width:50px;height: 20px" title="宿舍释放">
																&larr;
															</button>
															<br>
															<br>
														</td>
													</tr>
												</table> <br />
												<table width="97%" align="center" class="tbstyle">
													<tr align="center">
														<td>
															按 楼 层
															<br>
															<br>
															<button class="button2" onclick="acs_ald_addColum('acs')"
																style="width:50px;height: 20px" title="宿舍划分">

																&rarr;
															</button>
															<br>
															<br>
															<button class="button2" onclick="acs_ald_delColum('acs')"
																style="width:50px;height: 20px" title="宿舍释放">
																&larr;
															</button>
															<br>
															<br>
														</td>
													</tr>
												</table> <br />
												<table width="97%" align="center" class="tbstyle">
													<tr align="center">
														<td>

															按 楼 栋
															<br>
															<br>
															<button class="button2" onclick="acs_ald_addColum('ald')"
																style="width:50px;height: 20px" title="宿舍划分">
																&rarr;
															</button>
															<br>
															<br>
															<button class="button2"
																onclick="acs_ald_delColum('ald');"
																style="width:50px;height: 20px" title="宿舍释放">
																&larr;
															</button>
															<br>
															<br>
														</td>
													</tr>
												</table> </span>
										</td>
										<td align="center" align="left">
											<font color="red" style="font-size:10px;">提示：按住Ctrl键或Shift键(或按下鼠标左键上下移动)进行多选</font>
											<br>
											年级/<bean:message key="lable.xsgzyxpzxy" />/楼栋名称/层数/寝室编号/总位数
											<html:select property="sql" style="width:100%;" size="27" onmouseover="null"
												styleId="sql" multiple="multiple">
												<html:options collection="ssYhfList" property="dm"
													labelProperty="mc" />
											</html:select>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						</table>
						<table class="formlist" border="0" align="center" style="width: 100%">
						<tfoot>
						<tr>
							<td align="center" colspan="2">
								<button name="button1"
									style="width:100px"
									onclick="if(confirm('确定要提交已划分情况列表数据？')){SsHfDataSave();}else{return false;}">
									确 定
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button name="button2"
									style="width:100px"
									onclick="refreshForm('/xgxt/zgdzdx_Gygl.do?method=roomCompartition')">
									重 置	
								</button>
							</td>
						</tr>
						</tfoot>
					</table>
		</html:form>
		<logic:notEmpty name="doFlag">
			<logic:equal name="doFlag" value="true">
				<script>				   
					alert("操作成功!");					
				</script>
			</logic:equal>
			<logic:equal name="doFlag" value="false">
				<script>				    
					alert("操作失败!");
				</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
	<script type="text/javascript">

	
	</script>
</html>
