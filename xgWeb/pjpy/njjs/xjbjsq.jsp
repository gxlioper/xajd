<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/xjbjService.js'></script>
		<script src="js/check.js"></script>
		<script type="text/javascript">
			
			function loadBjxx(){
				var bjdm = jQuery('#bjdm').val();
				
				if ('' != bjdm){
					xjbjService.getBjxxByBjdm(bjdm,function(data){
						if (data != null){
							jQuery('#bjrs').val(data.bjrs);
							jQuery('#bzxh').val(data.bzxh);
							jQuery('#bzr').val(data.bzr);
						}
						
					})
				}
			}
			
			function selectBj(){
				$('bjdm').value = $('bj').value;
				$('bjmc').value = DWRUtil.getText('bj');
				loadBjxx();
				closeWindown();
			}
		</script>
	</head>

	<body onload="loadBjxx();xyDisabled('xy');">
		<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
		<input type="hidden" id="userName" value="${userName }"/>
		<input type="hidden" id="userType" value="${userType }"/>
		<input type="hidden" id="userDep" value="${userDep }"/>
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		
		<logic:notEqual value="add" name="doType">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
		</logic:notEqual>

		<html:form action="/njjsXjbj.do?method=xjbjsq" method="post">
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
									<button name="保 存"
										onclick="saveUpdate('njjsXjbj.do?method=xjbjSave','yxlx-bjrs')">
										保 存
									</button>
									<logic:equal value="add" name="doType">
									<button name="关 闭"
										onclick="window.close();return false;">
										关 闭
									</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>优秀班集体申请</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>班级名称
							</th>
							<td width="34%">
								<html:hidden property="save_bjdm" styleId="bjdm" value="${stuInfo.bjdm }"/>
								
								<logic:equal value="stu" name="userType">
									${stuInfo.bjmc }
								</logic:equal>
								
								<logic:notEqual value="stu" name="userType">
									<html:text property="save_bjdm" styleId="bjmc" value="${stuInfo.bjdm }" readonly="true"/>
									<button class="btn_01" onclick="tipsWindown('系统提示','id:selectDiv','350','200','true','','true','id');">
										选择
									</button>
								</logic:notEqual>
							</td>
							<th width="16%">
								<font color="red">*</font>优秀班级类型
							</th>
							<td width="34%">
								<html:select property="save_yxlx" styleId="yxlx">
									<html:options collection="xjlxList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								班级人数
							</th>
							<td>
								<html:text property="save_bjrs" readonly="true" styleId="bjrs"></html:text>
							</td>
							<th>
								班长学号
							</th>
							<td>
								<html:text property="save_bzxh" readonly="true" styleId="bzxh"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								班主任
							</th>
							<td>
								<html:text property="save_bzrzgh" readonly="true" styleId="bzr"></html:text>
							</td>
							<th>
								
							</th>
							<td>

							</td>
						</tr>
						<tr>
							<th>
								申请人
							</th>
							<td>
								<html:text property="save_sqr" readonly="true" value="${userNameReal }"></html:text>
							</td>
							<th>
								申请时间
							</th>
							<td>
								<html:text property="save_sqsj" styleId="sqsj" readonly="true" value="${sqsj }"
										   onclick="return showCalendar('sqsj','yyyy-MM');"
								></html:text>
							</td>
						</tr>
						<tr>
							<th>
								主要事迹<br/>
								<font color="red">
									&lt;限500字&gt;
								</font>
							</th>
							<td colspan="3">
								<html:textarea property="save_zysj" style="width:95%" rows="5" onblur="checkLen(this,500)"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								备注<br/>
								<font color="red">
									(限200字)
								</font>
							</th>
							<td colspan="3">
								<html:textarea property="save_bz" style="width:95%" rows="5" onblur="checkLen(this,200)"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		
		
			<div id="selectDiv" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>请选择班级</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" style="width:180px"
										styleId="xy" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" styleId="zy"
										onchange="initBjList()" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bj"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button name="确定" onclick="selectBj()">
											确 定
										</button>
										<button name="取消" onclick="closeWindown();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</html:form>


		<logic:present name="message">
			<script defer>
				alert("${message}");
				if(window.dialogArguments && window.dialogArguments.document.getElementById("isPage")){
					window.dialogArguments.document.getElementById("isPage").value = 'yes';
				}
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
				
			</script>
		</logic:present>
	</body>
</html>
