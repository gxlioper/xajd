<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/jxgl/general/jxqjgl/js/jxqjjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
		jQuery(function(){ 
			var qjkssjV = '${jxxxMap.kssj }';
			var qjjssjV = '${jxxxMap.jssj }';
					jQuery("#qjkssj").bind("focus", function (){
						var qjjssj = jQuery("#qjjssj").val();
						if(qjjssj == ''){
							qjjssj = qjjssjV;
						}
						WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:qjjssj,minDate:qjkssjV}); 
					}); 
					jQuery("#qjjssj").bind("focus", function (){
						var qjkssj = jQuery("#qjkssj").val();
						if(qjkssj == ''){
							qjkssj = qjkssjV;
						}
						WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:qjjssjV,minDate:qjkssj});
					}); 
				
				});
		
		</script>
	</head>
	<body>
		<html:form method="post" styleId="JxqjjgForm" action="/jxqjjg" enctype="multipart/form-data">
		<html:hidden property="xq" styleId="xq" />
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" id="jxkssj" value='${jxxxMap.kssj}'/>
		<input type="hidden" id="jxjssj" value='${jxxxMap.jssj}'/>
		<div style='tab;width:100%;height:465px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/selectStudent.jsp" %>
				<thead>
						<tr>
							<th colspan="4">
								<span>军训信息
								</span>
							</th>
						</tr>
				</thead>
				<tbody>
				<tr >
				<td colspan="4">
				<table width="100%" class="formlist" >
	            <tr >
				<th style="text-align:center;">军训名称</th>
				<th style="text-align:center;">军训开始时间</th>
				<th style="text-align:center;">军训结束时间</th>
				</tr>
				<tr>
				<td align="center">${jxxxMap.jxmc}</td>
				<td align="center">${jxxxMap.kssj}</td>
				<td align="center">${jxxxMap.jssj}</td>
				</tr>
				</table>
				</td>
				</tr>
				</tbody>
				<tbody>
				<thead>
						<tr>
							<th colspan="4">
								<span>请假信息
								</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<logic:equal name="xxdm" value="10026">
						<tr>
							<th align="right" width="10%">
								建制
							</th>
							<td align="left" colspan="3">
								${jzmc}
							</td>
						</tr>
					</logic:equal>
					<tr>
						<th align="right" width="10%">
							学年
						</th>
						<td align="left">
							<html:text property="xn" styleId="xn" style="width:120px;" maxlength="4" readonly="true"></html:text>
						</td>
						<th align="right">
							学期
						</th>
						<td align="left">
							<html:text property="xqmc" styleId="xqmc" style="width:120px;" maxlength="4" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>请假天数
						</th>
						<td align="left">
							<html:text property="qjts" styleId="qjts" style="width:120px;" maxlength="4" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(5)?(?:\d*)?/ig,'$1$2$3')"></html:text>&nbsp;&nbsp;天&nbsp;&nbsp;
						</td>
						<th align="right">
							<span class="red">*</span>请假类型
						</th>
						<td align="left">
								<html:select property="qjlx" styleId="qjlx" disabled="false" 
									style="width:125px;">
									<html:options collection="qjlxList" property="qjlxid"
										labelProperty="qjlxmc" />
								</html:select>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<logic:equal name="xxdm" value="10026">
								<span class="red">*</span>离营时间
							</logic:equal>
							<logic:notEqual name="xxdm" value="10026">
								<span class="red">*</span>请假开始时间
							</logic:notEqual>
						</th>
						<td align="left">
							<html:text property="qjkssj" styleId="qjkssj" style="width:120px;"/>
						</td>
						<th align="right">
							<logic:equal name="xxdm" value="10026">
								<span class="red">*</span>回营时间
							</logic:equal>
							<logic:notEqual name="xxdm" value="10026">
								<span class="red">*</span>请假结束时间
							</logic:notEqual>
						</th>
						<td align="left">
							<html:text property="qjjssj" styleId="qjjssj" style="width:120px;" />
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<logic:equal name="xxdm" value="10026">
								<span class="red">*</span>请假原因&nbsp;
							</logic:equal>	
							<logic:notEqual name="xxdm" value="10026">
								<span class="red">*</span>请假事由&nbsp;
							</logic:notEqual>
							<br />
							<font color="red">(限500字)</font>	
						</th>
						<td colspan="3">
							<html:textarea rows="4" property="qjsy" styleId="qjsy" style="width:97%" onblur="checkLen(this,500);"></html:textarea>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							附件信息
						</th>
						<td colspan="3">
							<html:hidden property="fjxx" styleId="fjxx" />
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
												elementid : 'fjxx'
												});
										});
									</script>
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
								<button type="button"  onclick="save('jxqjjg.do?method=save','xh-qjts-qjlx-qjkssj-qjjssj-qjsy','false');return false;" id="buttonSave">
									保 存
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
