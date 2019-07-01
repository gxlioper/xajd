<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getDtjsInfo.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/xjydService.js'></script>
		<script type="text/javascript">
			function getShlcid(ydlb){
				if (ydlb != ''){
					xjydService.getShlcidByYdlb(ydlb,function(data){
						$('ydhxjztm').value = data.xjzt;
						$('xjzt').value = data.xjzt;
						$('ydhsfzx').value = data.sfzx;
						$('sfzx').value = data.sfzx;
					})
				}
			}
			
			function saveXjyd(){

				var ydqdm = $('save_ydqbdm').value;
				var bj = $('bj').value;
				var xjztm = $('save_ydqxjztm').value;
				var xjzt = $('xjzt').value;
				var ydqsfzx = $('save_ydqsfzx').value;
				var sfzx = $('sfzx').value;
								
				if(ydqdm==bj&&xjztm==xjzt&&ydqsfzx==sfzx){
					alertInfo('<font color="blue">"班级"、"学籍状态"</font>和<font color="blue">"是否在校"</font>未发生变化,请确认！');
					return false;
				}
				
				if(checkBtx('xh-ydlbm-nj-xy-zy-bj') && confirm('保存后异动信息将保存到学生信息，您确定要这么做吗?')){
					saveUpdate('xsxxXjyd.do?method=xjydAdd&doType=save','')
				}
			}
			function downXsxx(){
				refreshForm('xsxxXjyd.do?method=xjydsq&xh='+jQuery("#xh").val())
			}
			function getXh(){
				var url = 'general_xsxx_xjyd.do?method=getXh';	
				showTopWin(url,800,600);
			}
		</script>
	</head>
	<body onload="xyDisabled('xy');">
	
		<html:form action="/xsxxXjyd" method="post">
			<input type="hidden" id="isFdy"     name="isFdy"  	 value="${isFdy }" />
			<input type="hidden" id="userName"  name="userName"  value="${userName }" />
			<input type="hidden" id="userType"  name="userType"  value="${userType }" />
		
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="url" name="url" value="/xsxxXjyd.do?method=xjydAdd" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb" />		
			<input type="hidden" name="save_shzt" value="通过"/>
			<html:hidden property="save_id"/>
		
			<div class="tab">
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" name="保 存" onclick="saveXjyd()">
											保 存
									</button>
									<button type="button" name="关 闭" onclick="window.close();return false;">
											关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="4">
								<span>
									学生基本信息
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>学号
							</th>
							<td width="34%">
							<html:text property="save_xh" styleId="xh" value="${rs.xh}" 
										onkeypress="autoFillStuInfo(event.keyCode,this)"
										onblur="chkIsStu(this,'view_xsbfxx','xh')"/>
							<input  style="display: none" onclick="downXsxx();" id="disbutton"/>
							<button type="button" onclick="getXh();" class="btn_01" id="buttonFindStu">
								选择
							</button>
							</td>
							<th width="16%">
								姓名
							</th>
							<td width="34%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								${rs.xb }
							</td>
							<th>
								联系电话
							</th>
							<td>
								${rs.lxdh }
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>异动类别
							</th>
							<td>
								<html:select property="save_ydlbm" styleId="ydlbm" onchange="getShlcid(this.value)">
									<html:option value=""></html:option>
									<html:options collection="ydlbList" property="ydlbm" labelProperty="ydlbmc"/>
								</html:select>
							</td>
							<th>
								申请人
							</th>
							<td>
								<html:text property="save_sqr" value="${userNameReal }" readonly="true"></html:text>
								<html:hidden property="save_sqsj"/>
							</td>
						</tr>
						<tr>
							<th>
								学年
							</th>
							<td>
								<html:select property="save_xn" style="width:180px" 
									styleId="xn" styleClass="select" value="${xn }">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>	
							<th>
							</th>
							<td>
							</td>
						</tr>
						<tr>
							<th>
								申请理由
								<br/>
								<font color="red"><限400字></font>
							</th>
							<td colspan="3">
								<html:textarea property="save_ydsm" style="width:90%" rows="5" onblur="checkLen(this,400)" ></html:textarea>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="4">
								<span>
									学籍异动信息
								</span>
							</td>
						</tr>
					</thead>
					
					
					
					<tbody>
						<tr>
							<td colspan="2" align="center">当前所在班级及学籍状态</td>
							<td colspan="2" align="center">申请异动信息及学籍状态</td>
						</tr>
						<tr>
							<th>年级</th>
							<td>
								${rs.nj }
								<html:hidden property="save_ydqnj" value="${rs.nj }"/>
							</td>
							<th><font color="red">*</font>年级</th>
							<td>
								<html:select property="save_ydhnj" styleId="nj" value="${rs.nj }"
									onchange="initZyList();initBjList()" style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td>
								${rs.xymc }
								<html:hidden property="save_ydqxydm" value="${rs.xydm }"/>
								<html:hidden property="save_ydqxymc" value="${rs.xymc }"/>
							</td>
							<th><font color="red">*</font><bean:message key="lable.xsgzyxpzxy" /></th>
							<td>
								<html:hidden property="save_ydhxymc" styleId="ydhxymc"/><!-- 异动后学院名称 -->
								<html:select property="save_ydhxydm" value="${rs.xydm }"
									onchange="initZyList();initBjList();$('ydhxymc').value=DWRUtil.getText('xy')" styleId="xy"
									style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>专业</th>
							<td>
								${rs.zymc }
								<html:hidden property="save_ydqzydm" value="${rs.zydm }"/>
								<html:hidden property="save_ydqzymc" value="${rs.zymc }"/>
							</td>
							<th><font color="red">*</font>专业</th>
							<td>
								<html:hidden property="save_ydhzymc" styleId="ydhzymc"/><!-- 异动后专业名称 -->
								<html:select property="save_ydhzydm" value="${rs.zydm }"
									onchange="initBjList();$('ydhzymc').value=DWRUtil.getText('zy')" styleId="zy" style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>班级</th>
							<td>
								${rs.bjmc }
								<html:hidden property="save_ydqbdm" value="${rs.bjdm }" styleId="save_ydqbdm"/>
								<html:hidden property="save_ydqbjmc" value="${rs.bjmc }"/>
							</td>
							<th><font color="red">*</font>班级</th>
							<td>
								<html:hidden property="save_ydhbjmc" styleId="ydhbjmc"/><!-- 异动后班级名称 -->
								<html:select property="save_ydhbdm" styleId="bj"
									onchange="$('ydhbjmc').value=DWRUtil.getText('bj')"
									style="width:180px" value="${rs.bjdm }">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>学籍状态</th>
							<td>
								${rs.xjztm }
								<html:hidden property="save_ydqxjztm" value="${rs.xjztm }"/>
							</td>
							<th>学籍状态</th>
							<td>
								<html:hidden property="save_ydhxjztm" styleId="xjzt"/>
								<html:select property="save_ydhxjztm" style="width:180px" value="${rs.xjztm }" styleId="ydhxjztm" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>是否在校</th>
							<td>
								${rs.sfzx }
								<html:hidden property="save_ydqsfzx" value="${rs.sfzx }"/>
							</td>
							<th>是否在校</th>
							<td>
								<html:hidden property="save_ydhsfzx" styleId="sfzx"/>
								<html:select property="save_ydhsfzx" style="width:180px" value="${rs.sfzx }"  styleId="ydhsfzx" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="sfzxList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
		
		<logic:present name="message">
			<script>
				alert("${message}");
				if(window.dialogArguments){
					dialogArgumentsQueryChick();
					window.close();
				}
			</script>
		</logic:present>
	</body>
</html>
