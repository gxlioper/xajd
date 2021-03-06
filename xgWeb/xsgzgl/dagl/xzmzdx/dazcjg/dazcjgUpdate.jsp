<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dagl/xzmzdx/dazcjg/js/dazcjg.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var zcfs = ${zcfs};
				var yjzt = ${yjzt};
				if("1" == zcfs){
					/*隐藏字段*/
					jQuery("#mailedOne").show();
					jQuery("#mailedTwo").show();
					jQuery("#mailedThree").show();
					jQuery("#mailedFour").show();
					if("1" == yjzt){
						jQuery("#mailedFive").show();
						jQuery("#mailedSix").show();
					}else{
						jQuery("#mailedFive").hide();
						jQuery("#mailedSix").hide();
					}
					jQuery("#byoOne").hide();
					jQuery("#byoTwo").hide();
				}else{
					/*隐藏字段*/
					jQuery("#mailedOne").hide();
					jQuery("#mailedTwo").hide();
					jQuery("#mailedThree").hide();
					jQuery("#mailedFour").hide();
					jQuery("#mailedFive").hide();
					jQuery("#mailedSix").hide();
					jQuery("#byoOne").show();
					jQuery("#byoTwo").show();
				}
			});
		</script>
	</head>
	<body>
		<html:form action="/dagl_dazcjg" method="post" styleId="dazcjgForm">
		<input type="hidden" name="xh" id="xh" value="${dazcjgForm.xh}" />
		<input type="hidden" name="guid" id="guid" value="${dazcjgForm.guid}" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@include file="/xsgzgl/comm/bdpz/viewStudent.jsp"%>
					<thead>
						<tr>
							<th colspan="4">
								<span>档案转出结果信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>转出方式
							</th>
							<td colspan="3">
								<html:radio property="zcfs" value="1" onclick="changeZcfs()">邮寄</html:radio>
								<html:radio property="zcfs" value="2" onclick="changeZcfs()">自带</html:radio>
							</td>
						</tr>
						<tr id="mailedOne">
							<th>
								<font color="red">*</font>邮寄地址
							</th>
							<td>
								<html:text property="yjdz" styleId="yjdz" maxlength="64" style="width:150px" styleClass="text_nor" ></html:text>
							</td>
							<th>
								<font color="red">*</font>邮政编码
							</th>
							<td>
								<html:text property="yzbm" styleId="yzbm" maxlength="6" styleClass="text_nor" onkeyup="checkInputLxfx(this)" style="width:150px"></html:text>
							</td>
						</tr>
						<tr id="mailedTwo">
							<th>
								<font color="red">*</font>收件人
							</th>
							<td>
								<html:text property="sjr" styleId="sjr" maxlength="10" style="width:150px" styleClass="text_nor" ></html:text>
							</td>
							<th>
								<font color="red">*</font>收件人电话
							</th>
							<td>
								<html:text property="sjrdh" styleId="sjrdh" maxlength="12" style="width:150px" onkeyup="checkInputLxfx(this)" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr id="mailedThree">
							<th>
								<font color="red">*</font>单位名称
							</th>
							<td>
								<html:text property="dwmc" styleId="dwmc" maxlength="64" style="width:150px" styleClass="text_nor" ></html:text>
							</td>
							<th>
								<font color="red">*</font>单位地址
							</th>
							<td>
								<html:text property="dwdz" styleId="dwdz" maxlength="64" style="width:150px" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr id="mailedFour">
							<th>
								<font color="red">*</font>邮寄状态
							</th>
							<td colspan="3">
								<html:radio property="yjzt" value="1" onchange="changeYjzt()">已邮寄</html:radio>
								<html:radio property="yjzt" value="2" onchange="changeYjzt()">未邮寄</html:radio>
							</td>
						</tr>
						<tr id="mailedFive">
							<th>
								<font color="red">*</font>快递方式
							</th>
							<td>
								<html:text property="kdfs" styleId="kdfs" maxlength="32" style="width:150px" styleClass="text_nor" ></html:text>
							</td>
							<th>
								<font color="red">*</font>快递单号
							</th>
							<td>
								<html:text property="kddh" styleId="kddh" maxlength="32" style="width:150px" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr id="mailedSix">
							<th>
								<font color="red">*</font>邮寄时间
							</th>
							<td colspan="3">
								<html:text property="yjsj" styleId="yjsj" onclick="return showCalendar('yjsj','y-mm-dd',true,'');"  
									maxlength="10" style="width:150px" styleClass="text_nor"></html:text>
							</td>
						</tr>
						<tr id="byoOne">
							<th>
								<font color="red">*</font>自带档案承诺
							</th>
							<td>
								<logic:notEmpty name="dazccsszForm" property="uploadid">
									<html:checkbox property="zddacn" styleId="zddacn" value="1"/>
									我已阅并接受
									<a href="javascript:void(0);" onclick="window.open('common_upload.do?method=asyncDownload&fid=${dazccsszForm.uploadid}');return false;" class="name" style="margin-left: 0px;">
										《档案转出协议》
									</a>
								</logic:notEmpty>
							</td>
							<th>
								<font color="red">*</font>预期提档日期
							</th>
							<td>
								<html:text property="yqtdrq" styleId="yqtdrq" onclick="return showCalendar('yqtdrq','y-mm-dd',true,'');"  
									maxlength="10" style="width:150px" styleClass="text_nor"></html:text>
							</td>
						</tr>
						<tr id="byoTwo">
							<th>
								实际提档日期
							</th>
							<td>
								<html:text property="sjtdrq" styleId="sjtdrq" onclick="return showCalendar('sjtdrq','y-mm-dd',true,'');"  
									maxlength="10" style="width:150px" styleClass="text_nor"></html:text>
							</td>
							<th>
								实际提档人
							</th>
							<td>
								<html:text property="sjtdr" styleId="sjtdr" maxlength="16" style="width:150px" styleClass="text_nor" ></html:text>
							</td>
						</tr>
					</tbody>
				</table>
			</div>	
			<div style="height:30px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="dazcjgSave();">
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
		</html:form>
	</body>
</html>