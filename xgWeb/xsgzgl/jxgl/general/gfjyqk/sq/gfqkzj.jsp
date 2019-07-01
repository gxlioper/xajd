<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/jxgl/general/gfjyqk/sq/js/gfqksq.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		
	</head>
	<body>
		<html:form method="post" styleId="gfjysqForm" action="/gfjyqk_sq"
			enctype="multipart/form-data">
			<input type="hidden" id="xxdm" value="${xxdm}"/>
			<input type="hidden" id = "usertype" value = "${usertype}"/>
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/selectStudent.jsp"%>
					<thead>
						<tr>
							<th colspan="4">
								<span>国防教育情况</span>
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
								<span class="red">*</span>国防情况分类
							</th>
							<td align="left" colspan="3">
								<select name="gfqkfl" id="gfqkfl" style="width:150px;" onchange="change(this)">
									<option></option>
									<option value="1">兵役登记情况</option>
									<option value="2">参军入伍情况</option>
									<option value="3">退伍复学情况</option>
									<option value="4">评奖评优</option>
									<option value="5">奖惩情况</option>
									<option value="6">活动参加情况</option>
								</select>
							</td>
						</tr>
						<tr id="bydj" style="display:none">
							<th align="right" width="10%">
								<span class="red">*</span>兵役登记时间
							</th>
							    <td align="left">
										<html:text property="bydjsj" styleId="bydjsj" style="width:120px;"
										onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'','','','');" />
								</td>
							
							<th align="right">
								<span class="red">*</span>兵役登记地点
							</th>
								<td align="left">
									<html:text styleId="bydjdd" property="bydjdd"  maxlength="50"/>
								</td>
						</tr>
						
						<tr id="cjrw" style="display:none">
							<th align="right" width="10%">
								<span class="red">*</span>参军入伍时间
							</th>
							    <td align="left">
										<html:text property="cjrwsj" styleId="cjrwsj" style="width:120px;"
										onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'','','','');" />
								</td>
							
							<th align="right">
								<span class="red">*</span>入伍批准单位
							</th>
								<td align="left">
									<html:text styleId="rwpzdw" property="rwpzdw"  maxlength="50"/>
								</td>
						</tr>
						<tr id="twfx" style="display:none">
							<th align="right" width="10%">
								<span class="red">*</span>退伍复学时间
							</th>
							    <td align="left">
										<html:text property="twfxsj" styleId="twfxsj" style="width:120px;"
										onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'','','','');" />
								</td>
							
							<th align="right">
								<span class="red">*</span>退伍批准单位
							</th>
								<td align="left">
									<html:text styleId="twpzdw" property="twpzdw"  maxlength="50"/>
								</td>
						</tr>
						<tr id="pjpy" style="display:none">
							<th align="right" width="10%">
								<span class="red">*</span>评奖评优时间
							</th>
							    <td align="left">
										<html:text property="pjpysj" styleId="pjpysj" style="width:120px;"
										onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'','','','');" />
								</td>
							
							<th align="right">
								<span class="red">*</span>评奖评优单位
							</th>
								<td align="left">
									<html:text styleId="pjpydw" property="pjpydw"  maxlength="50"/>
								</td>
						</tr>
						
						<tr id="jc" style="display:none">
							<th align="right" width="10%">
								<span class="red">*</span>奖惩时间
							</th>
							    <td align="left">
										<html:text property="jcsj" styleId="jcsj" style="width:120px;"
										onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'','','','');" />
								</td>
							
							<th align="right">
								<span class="red">*</span>奖惩单位
							</th>
								<td align="left">
									<html:text styleId="jcdw" property="jcdw"  maxlength="50"/>
								</td>
						</tr>
						<tr id="cjhd" style="display:none">
							<th align="right" width="10%">
								<span class="red">*</span>参加活动时间
							</th>
							    <td align="left">
										<html:text property="cjhdsj" styleId="cjhdsj" style="width:120px;"
										onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'','','','');" />
								</td>
							
							<th align="right">
								<span class="red">*</span>参加活动地点
							</th>
								<td align="left">
									<html:text styleId="cjhddd" property="cjhddd"  maxlength="50"/>
								</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								备注&nbsp;
								<br />
								<font color="red">(限500字)</font>
							</th>
							<td colspan="3">
								<html:textarea rows="4" property="bz" styleId="bz"
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
					</tbody>
				</table>
			</div>
			<div style="height:50px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button"
										onclick="save('gfjyqk_sq.do?method=add&type=save');return false;"
										id="buttonSave">
										保存草稿
									</button>
									<button type="button"
										onclick="save('gfjyqk_sq.do?method=add&type=submit');return false;"
										id="buttonSave">
										提交申请
									</button>
									<button type="button" onclick="iFClose();" id="buttonClose">
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
