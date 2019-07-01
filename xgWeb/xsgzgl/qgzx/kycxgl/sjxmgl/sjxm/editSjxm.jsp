<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/kycxgl/sjxmgl/sjxm/js/sjxm.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/kycxgl/sjxmgl/sjxm/js/comm.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		jQuery(function(){
			jQuery("#xh").attr({readonly:"readonly"});
			jQuery("#jhcyrs").attr({readonly:"readonly"});
		});
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/qgzx_kycxsjxmgl" method="post" styleId="SjxmglForm" onsubmit="return false;">
		<html:hidden property="xmid" styleId="xmid"/>
		<input type="hidden" id="gwxxStr" name="gwxxStr"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>实践项目</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
						    <th width="20%">
								<font color="red">*</font>项目负责人学号
							</th>
							<td width="30%">
								<html:text property="xh" styleId="xh" maxlength="50"></html:text>
								<button class="btn_01" onclick="showDialog('请选择一个学生',800,550,'qgzx_kycxkyxmgl.do?method=showStudents')" type="button" >选择</button>
							</td><th width="20%">
								姓名
							</th>
							<td width="30%" name='xsxm'>
							${SjxmglForm.xm}
							</td>
						</tr>
						<tr>
							<th width="20%">
								年级
							</th>
							<td width="30%" name='xsnj'>
							${SjxmglForm.nj}
							</td>
							
							<th width="20%">
								学院
							</th>
							<td width="30%" name='xsxy'>
							${SjxmglForm.xymc}
							</td>
						</tr>
						<tr>
						    <th width="20%">
								联系电话
							</th>
							<td width="30%" name='sjhm'>
							${SjxmglForm.sjhm}
							</td>
							<th width="20%">
								<font color="red">*</font>项目编号
							</th>
							<td width="30%">
								<html:text property="xmbh" styleId="xmbh" maxlength="10"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>项目名称
							</th>
							<td width="30%">
								<html:text property="xmmc" styleId="xmmc" maxlength="50"></html:text>
							</td>
								<th width="20%">
								<font color="red">*</font>项目属性
							</th>
							<td width="30%">
								<html:select property="xmsxdm" styleId="xmsxdm" style="width:130px">
									<html:options collection="xmsxList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							
						</tr>
						<tr>
							
							<th width="20%">
								<font color="red">*</font>项目所属单位
							</th>
							<td width="30%">
								<html:select property="xmssdw" styleId="xmssdw" style="width:150px">
									<html:options collection="bmList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th width="20%">
								计划参与人数
							</th>
							<td width="30%" colspan='3'>
								<html:text property="jhcyrs" styleId="jhcyrs"  maxlength="5"></html:text>
							</td>
						 </tr>
						 <tr>
							<th width="20%">
								<font color="red">*</font>项目开始时间
							</th>
							<td width="30%">
								<html:text property="kssj"
									onfocus="showCalendar('kssj','yyyyMMdd',true,'jssj');" styleId="kssj" ></html:text>
							</td>
							<th width="20%">
								<font color="red">*</font>项目结束时间
							</th>
							<td width="30%">
								<html:text property="jssj"
									onfocus="showCalendar('jssj','yyyyMMdd',false,'kssj');" styleId="jssj" ></html:text>
							</td>																		
						</tr>
					</tbody>
					<thead>
						<tr>
				         <th colspan="4">
						<span>项目岗位信息
							<img src="xsxx/fbgl/images/add-icons-1.gif" alt="增加" onclick="addXmgw(this);return false;"/>
						    <img src="xsxx/fbgl/images/delete-icons-2.gif" alt="删除" onclick="delXmgw(this);return false;"/>
							</span>
						</th>
				      </tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
							<div id='xmgwDiv' style="display:none">
						<table name='xmgwTab'>
						<tbody name='xmgwTbody'>
						<tr>
								<td>
								<input type="checkbox" id="checkbox"/>
								</td>
								<td>
								<html:select property="gwlb" styleId="gwlb" >
									<html:options collection="gwlbList" property="dm"
										labelProperty="mc" />
								</html:select>
								</td>
								
								<td>
								<input type='text' name="gwgzzy" style="width:400px" maxlength="250"/>
								</td>
								<td>
								<input type='text' name="zcyrs" maxlength='5' onkeyup="value=value.replace(/[^\d]/g,'');initCyrs();">
								</td>
								</tr>
								</tbody>
							</table>
						</div>
						<div class="con_overlfow">
							<table width="100%" border="0" class="datelist" style="margin:2px auto;">
									<thead>
										<tr>
											<td width='5%'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
										<td width='15%'>岗位类型</td>
										<td width='70%'>岗位工作摘要</td>
										<td width='15%'>参与总人数</td>
									</tr>
									</thead>
									<tbody id="tbody_xmgw">
									<logic:present name="xmgwList">
										<logic:iterate id="i" name="xmgwList" indexId="index01">
											<tr>
											    <td>
												<input type="checkbox" id="checkbox"/>
												</td>
												<td>
												<html:select  property="gwlb" styleId="gwlb_${gwlb}" value="${i.gwlb}">
												<html:options collection="gwlbList" labelProperty="mc" property="dm"/>
												</html:select>
											    </td>
											    <td>
												<html:text property="gwgzzy" styleId="gwgzzy_${index01}" value="${i.gwgzzy}" maxlength="250" style="width:400px"></html:text>
											    </td>
											    <td>
											    <html:text property="zcyrs" styleId="zcyrs" maxlength='5' value="${i.zcyrs}" onkeyup="value=value.replace(/[^\d]/g,'');initCyrs();"></html:text>
											    </td>
											    
											</tr>
										</logic:iterate>
									</logic:present>
									</tbody>
								</table>
								</div>
								
							</td>
						</tr>
					</tbody>
				 </table>
				 </div>
			  
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="saveSjxm('update');">
										保存
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

