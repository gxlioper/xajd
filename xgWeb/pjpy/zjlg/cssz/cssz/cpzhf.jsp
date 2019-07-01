<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script language="javascript" src="js/pjpy/pjpy_zjlg.js"></script>
		<script type="text/javascript" src="js/pjpy/pjpy_dwr.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getCpzfp.js'></script>
	</head>
	<body onload="checkWinType();xyDisabled('xy');getCpzBjList();" style="position: absolute">
		<html:form action="/zjlgPjpy" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>评奖评优 - 参数设置 - 奖学金 - 参评组划分</a>
				</p>
			</div>
			<input type="hidden" id="bjdms" name="bjdms"/>
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="tab">
				<table width="100%" class="formlist">
					<thead>
						<tr><th colspan="4"><span>第<bean:write name="xn"/>学年参评组划分</span></th></tr>
					</thead>
					<tbody>
						<tr>
 							<td width="60%" colspan="2">
 								学年
							<html:select property="xn" style="width:120px" disabled="true"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
 							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 							年级
							<html:select property="nj" style="width:90px"
										onchange="initZyList();initWfpBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
							</html:select>
							<br/>
 							&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />
							<html:select property="xydm" style="width:180px" styleId="xy" 
									onchange="initZyList();initCpzList();initWfpBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
							</html:select>
							<input type="hidden" name="xyV" value=""/>
							&nbsp;&nbsp;专业
							<html:select property="zydm" style="width:180px" styleId="zy"
									onchange="initWfpBjList();">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
							</html:select>
							<input type="hidden" name="zyV" value=""/>
		 					</td> 
					 		<td width="7%">
					 					划分	
					 		</td> 
					 		<td width="30%" id = "cpzxx">
					 				已划分情况：请选择参评组
					 		</td> 
     				</tr> 
				    <tr>
				 		<td width="30%" >
				 				参评组代码/名称&nbsp;&nbsp;<font color="red" style="font-size:12px;">(提示：请先选择<bean:message key="lable.xsgzyxpzxy" />)</font>
				 		</td> 
				 		<td width="30%" >
				 				未分配班级&nbsp;&nbsp;<font color="red" style="font-size:12px;">(提示：可进行多选)</font>	
				 		</td> 
				 		<td width="7%" >
				 				划分操作
				 		</td> 
				 		<td width="30%" >
				 				已分配班级
				 		</td> 
				     </tr> 
     
				     <tr>
				 		<td width="30%" >
											<html:select property="cpzdm" size="20" style="width:100% "  styleId="cpz"
													onchange="getCpzBjList();initWfpBjList();">
													<html:options collection="cpzList" property="cpzdm"
														labelProperty="xscpz" />
											</html:select>
											<input type="hidden" name="cpzV" value=""/>
				 		</td> 
				 		<td width="30%" >
											<html:select property="wfpbjdm"  size="20" styleId="fdyxmList" style="width:100% " onmouseover="" multiple="multiple"   styleId="wfpbj">
													<html:options collection="wfpBjList" property="bjdm"
														labelProperty="bjmc"/>
											</html:select>
											<input type="hidden" name="wfpbjV" value=""/>
				 		</td> 
				 		<td valign="top" width="7%">
											<br/>
											<br/>
											<br/>
											<font color="blue">&nbsp;&nbsp;&nbsp;分配</font>
											<br/>
											<button type="button" class="button2" onclick="addBjBatchColum()"
												style="width:50px;height: 20px" title="分配">
												&rarr;
											</button>
											<br/>
											<br/>
											<br/>
											<br/>
											<font color="blue">&nbsp;&nbsp;&nbsp;释放</font>
											<br/>
											<button type="button" class="button2" onclick="delBjBatchColum()"
												style="width:50px;height: 20px" title="释放">
												&larr;
											</button>
						</td>
				 		<td width="30%" >
											<html:select property="yfpbjdm" size="20"  styleId="fdyxmList" style="width:100% "   multiple="multiple"  styleId="yfpbj">
													<html:options collection="cpzBjList" property="bjdm"
														labelProperty="bjmc"/>
											</html:select>
											<input type="hidden" name="yfpbjV" value=""/>
				 		</td> 
				     </tr> 
				 </tbody>
				 <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
					         <div class="btn">
								<button type="button" onclick="pjpyCpzfpzj();" id="buttonSave">
									新增参评组
								</button>
								<button type="button" onclick="if($('cpz').value==''){alert('请选择参评组')}else{if(confirm('删除的同时会释放已分配给该参评组的班级\n确定要删除吗?')){refreshForm('zjlgPjpy.do?method=delCpz')}}"
									 id="buttonSave">
									删除参评组
								</button>
								<button type="button" class="button2" onclick="saveYfbbj()" id="buttonSave">
									保 存
								</button>
								<button type="button" onclick="Close();return false;" id="buttonClose">
									关 闭
								</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
	</div>
	<div id="tmpdiv1">
	</div>
	<logic:present name="inserted">
		<logic:equal name="inserted" value="ok">
			<script>
			    alert("提交成功！");
			</script>
		</logic:equal>
		<logic:equal name="inserted" value="no">
			<script>
				alert("提交失败！");
			</script>
		</logic:equal>
	</logic:present>
	</html:form>
	</body>
</html>
