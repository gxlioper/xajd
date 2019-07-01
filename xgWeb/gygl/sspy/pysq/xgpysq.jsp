<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="gygl/sspy/pysq/js/pysq.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				getSscyxx();
			});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="sspysqForm" action="/sspysq">
		<input type="hidden" id="lddm" value="${lddm }"/>
		<input type="hidden" id="qsh" value="${qsh }"/>
		<html:hidden property="sqid"  styleId="sqid"/>
		<html:hidden property="shzt" styleId="shzt" />
		<html:hidden property="xh" styleId="xh" />
		<html:hidden property="splc"/>
		<div style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
							<span>床位信息</span>
						</th>
					</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right">
								楼栋名称
							</th>
							<td align="left">
								${cwxxData.ldmc}
							</td>
							<th align="right">
								寝室号
							</th>
							<td align="left">
								${cwxxData.qsh}
							</td>
						</tr>
						<tr>
							<th align="right" >
								床位号
							</th>
							<td align="left" id="td_cwh">
								${cwxxData.cwh}
							</td>
							<th align="right">
								寝室电话
							</th>
							<td align="left">
								${cwxxData.qsdh}
							</td>
						</tr>
						<tr>
							<th align="right" >
								收费标准
							</th>
							<td align="left">
								${cwxxData.sfbz}
							</td>
							<th align="right">
								所属年级
							</th>
							<td align="left">
								${cwxxData.nj}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								所属<bean:message key="lable.xb" />
							</th>
							<td align="left">
								${cwxxData.xymc}
							</td>
							<th align="right">
								所属班级
							</th>
							<td align="left">
								${cwxxData.bjmc}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>宿舍成员</span>
							</th>
						</tr>
					</thead>
					<thead>
						<tr>
							<th  width="20%">
								<div align="center" >学号</div>
							</th>
							<th>
								<div align="center" >姓名</div>
							</th>
							<th>
								<div align="center" >班级</div>
							</th>
							<th>
								<div align="center" >入住床位</div>
							</th>
						</tr>
					</thead>
					<tbody id="xsList">
					</tbody>
					
				<thead>
						<tr>
							<th colspan="4">
								<span>申请评优项目</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="10%">
								学年
							</th>
							<td align="left">
								${dqxn}
							</td>
							<th align="right">
								学期
							</th>
							<td align="left">
								${dqxq}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>评奖项目
							</th>
							<td align="left" colspan="3">
								<html:select property="pyxmdm" styleId="pyxmdm" disabled="false" >
							<html:options collection="pyxmList" property="pyxmdm"
								labelProperty="pyxmmc" />
						</html:select>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>申请理由&nbsp;
								<br />
								<font color="red">(限500字)</font>
							</th>
							<td colspan="5">
								<html:textarea rows="4" property="sqly" styleId="sqly"
									style="width:97%" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
							<html:hidden property="filepath" styleId="filepath" />
							<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
							<script type="text/javascript">
										//调用附件 
										jQuery(function(){
											jQuery.MultiUploader({
												maxcount : 3,
												//后缀
												accept : 'png|gif|jpg|zip|rar|doc|docx',
												//最大文件大小 单位M
												maxsize: 10,
												//存放附件的隐藏域的id
												elementid : 'filepath'
												});
										});
									</script>
						</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								备注&nbsp;
								<br />
								<font color="red">(限500字)</font>
							</th>
							<td colspan="5">
								<html:textarea rows="4" property="bz" styleId="bz"
									style="width:97%" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						
					</tbody>
			</table>
		</div>
		<div style="height:25px"></div> 
		<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button"
										onclick="saveXg('sspysq.do?method=update&type=update');return false;"
										id="buttonSave">
										保存草稿
									</button>
									<button type="button"
										onclick="saveXg('sspysq.do?method=update&type=submit');return false;"
										id="buttonSave">
										提交申请
									</button>
								<button type="button"  onclick="iFClose();" id="buttonClose">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		</html:form>
	</body>
</html>
