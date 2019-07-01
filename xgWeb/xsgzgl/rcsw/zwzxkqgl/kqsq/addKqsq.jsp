<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/kqsq.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/comm.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		function qqxszj(html){
			jQuery("#tbody_qqryxx").append(html);	
		}
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/zwzxKqsq" method="post" styleId="ZwzxKqsqForm" onsubmit="return false;">
		<html:hidden property="xn" styleId="xn" value="${xn}"/>
			<html:hidden property="xq" styleId="xq" value="${xq}"/>
			<input type="hidden" id="objStr" name="objStr"/>
			<input type="hidden" id="nj" name="nj"/>
			<input type="hidden" id="xy" name="xy"/>
			<input type="hidden" id="zy" name="zy"/>
			<%--四川信息个性化判断 --%>
			<input type="hidden" id="xxdm" name="xxdm" value = "${xxdm}"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span><font id="gnmkmc_prompt_span"></font>增加</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								学年
							</th>
							<td width="30%">
								${xn}
							</td>
							<th width="20%">
								<font color="red">*</font><span id="ccrq_span"></span>
							</th>
							<td width="30%">
								<html:text property="ccrq"
									onclick="return showCalendar('ccrq','y-mm-dd');" styleId="ccrq"
									readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								学期
							</th>
							<td width="30%">
								${xqmc}
							</td>
							<th width="20%">
								<font color="red">*</font><span id="cclx_span"></span>
							</th>
							<td width="30%">
								<html:select property="cclxdm" styleId="cclxdm">
									<html:option value=""></html:option>
									<html:options collection="cclxList" property="lxdm"
										labelProperty="lxmc" />
								</html:select>
							</td>
						</tr>
							<tr>
								<th width="20%">
									<font color="red">*</font>班级
								</th>
								<td width="30%">
									<html:text style="width:145px" property="bjmc" onclick="bjSelect('${path}');"
										readonly="true" styleId="bjmc"></html:text>
									<input type="hidden" name="bjdm" id="bjdm" />
									<button class="btn_01" type="button"  
											onclick="bjSelect('${path}');return false;">选择</button>
								</td>
								<th width="20%">
									<font color="red">*</font>应到人数
								</th>
								<td width="30%">
									<logic:equal name="xxdm" value="2297">
										<html:text property="ydrs" styleId="ydrs" maxlength="3" readonly="true"
										onchange="computeQqrs()"></html:text>
									</logic:equal>
									<logic:notEqual name="xxdm" value="2297">
										<html:text property="ydrs" styleId="ydrs" maxlength="3"
										onkeyup="value=value.replace(/[^\d]/g,'');computeQqrs()"></html:text>
									</logic:notEqual>
									
								
								</td>
						    </tr>
							<tr>
								<th width="20%">
									<font color="red">*</font>实到人数
								</th>
								<td width="30%">
									<html:text property="sdrs" styleId="sdrs" maxlength="3"
										onkeyup="value=value.replace(/[^\d]/g,'');computeQqrs()"></html:text>
								</td>
								<logic:equal name="xxdm" value="12970">
								<th width="20%">
									未到人数
								</th>
								</logic:equal>
								<logic:notEqual name="xxdm" value="12970">
								<th width="20%">
									缺勤人数
								</th>
								</logic:notEqual>
								<td width="30%">
									<html:text property="qqrs" readonly="true" styleId="qqrs"></html:text>
								</td>
							</tr>
						
						<tr>
							<th width="20%" id="jlf_th">
								纪律分
							</th>
							<td width="30%" id="jlf_td">
								<html:text property="jlf" styleId="jlf" maxlength="3" onblur="checkJlf(this)" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(\d*)?(?:\d*)?/ig,'$1$2$3')"></html:text>
							</td>																			
							<th width="20%">
								<span id="jlr_span"></span>
							</th>
							<td width="30%" id="jlr_td">
								${userNameReal }
							</td>																			
						</tr>

						<logic:equal name="xxdm" value="10704">
							<tr>
								<th>
									带班辅导员
								</th>
								<td id="dbfdy" colspan="3">
								</td>
							</tr>
						</logic:equal>

						<tr>
						    <th>
								备注</br><font color="red">(限500字)</font>
							</th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" cols="50" rows="4"
									style="width:100%" onblur="chLeng(this,500)"></html:textarea>
							</td>
						</tr>
						<logic:equal value="11647" name="xxdm">
						<tr>
							<th>
								除缺勤外的违纪人数
								<br />
								<div align="center">
									(如:吃饭,睡觉等...)
								</div>
							</th>
							<td align="left">
								<html:text property="wjrs" styleId="wjrs" maxlength="3"
									onkeyup="value=value.replace(/[^\d]/g,'');"></html:text>
							</td>
							</tr>
							</logic:equal>
					</tbody>
					<thead>
						<tr>
						<logic:equal name="xxdm" value="12970">
							<th colspan="4">
								<span>未到学生信息</span>
							</th>
						</logic:equal>
						<logic:notEqual name="xxdm" value="12970">
							<th colspan="4">
								<span>缺勤学生信息</span>
							</th>
						</logic:notEqual>
						</tr>
					</thead>
				 </table>
				 </div>
				 <div style="height:200px;overflow-y:auto;">
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					
					<thead>
						<tr>
							<td colspan="7">
							<button type="button" id="btn_getXsxx" onclick="bcZjRyxx();" style="display: none;"></button>
							<button type="button" onclick="addQqxs();return false;" class="btn_01">增加学生</button>
							<button type="button" onclick="delQqxs();return false;" class="btn_01">删除学生</button>
							</td>
						</tr>
						<tr>
							<td width='5%'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
							<td width='15%'>学号</td>
							<td width='15%'>姓名</td>
								<logic:notEqual name="xxdm" value="12970">
									<td width='15%'><font color="red">*</font>缺勤类型</td>
									<td width='15%'>旷课节数</td>
									<td width='35%'>缺勤备注</td>
								</logic:notEqual>
							<logic:equal name="xxdm" value="12970">
								<td width='35%'>未到原因</td>
							</logic:equal>
						</tr>
					</thead>
					<tbody id="tbody_qqryxx"><%--
						<logic:notEmpty name="rs" property="zgryHtml">
						</logic:notEmpty>
					--%></tbody>
				</table>
				</div>
			  <div style="height:35px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="saveKqsq('save');">
										保存草稿
									</button>
									<button type="button" onclick="saveKqsq('submit');">
										提交申请
									</button>
									<button type="button" onclick="iFClose();">
										关闭
									</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

