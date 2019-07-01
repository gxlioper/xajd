<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true" ></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/xwhj/jg/js/hjjg.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript" defer="defer">
			jQuery(function(){
							
			});

			function on_change(){
				var jxlbdm = jQuery("#jxlbdm").val();
				var jsfs = jQuery("#jsfs").val();
				jQuery.post("xpj_jxmc.do?method=getJxdjList",{jxlbdm:jxlbdm,jsfs:jsfs},function(data){
					var html = "<option value=''></option>";
					  if(data && data.length > 0){
						  for(var i = 0; i < data.length; i++){
							html += '<option value='+data[i].jxdjdm+'>'+data[i].jxdjmc+'</option>';
						  }
					  }
					jQuery("#jxdjdm").html(html);
				  },'json');
			}

			function on_changeMc(){
				var jxlbdm = jQuery("#jxlbdm").val();
				var jsfs = jQuery("#jsfs").val();
				var jxdjdm = jQuery("#jxdjdm").val();
				jQuery.post("xpj_hjsq.do?method=getJxmcList",{jxlbdm:jxlbdm,jsfs:jsfs,jxdjdm:jxdjdm},function(data){
					var html = "<option value=''></option>";
					  if(data && data.length > 0){
						  for(var i = 0; i < data.length; i++){
							html += '<option value='+data[i].jxmcdm+'>'+data[i].jxmcmc+'</option>';
						  }
					  }
					jQuery("#jxmcdm").html(html);
				  },'json');
			}

			function on_changeJe(){
				var jxlbdm = jQuery("#jxlbdm").val();
				var jsfs = jQuery("#jsfs").val();
				var jxdjdm = jQuery("#jxdjdm").val();
				var jxmcdm = jQuery("#jxmcdm").val();
				jQuery.post("xpj_hjsq.do?method=getJe",{jxlbdm:jxlbdm,jsfs:jsfs,jxdjdm:jxdjdm,jxmcdm:jxmcdm},function(data){
					var html = "";
					  if(data && data.length > 0){	 
							html += data[0].je;		  
					  }
					jQuery("#je").html(html);
				  },'json');
			}
		</script>
	</head>
	<body>
		<html:form action="/xpj_hjjg" method="post" styleId="hjjgForm" onsubmit="return false">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">* </font>奖项类别
							</th>
							<td>
								<html:select property="jxlbdm" styleId="jxlbdm" onchange="on_change()" style="width:150px;">
									<html:option value=""></html:option>
									<html:options collection="jxlbList" property="jxlbdm" labelProperty="jxlbmc" />
								</html:select>
							</td>
							<th>
								<font color="red">* </font>竞赛方式
							</th>
							<td>
								<html:select property="jsfs" styleId="jsfs" onchange="on_change()" style="width:150px;">
									<html:option value=""></html:option>
									<html:option value="1">个人</html:option>
									<html:option value="2">团体</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">* </font>奖项等级
							</th>
							<td>
								<select name="jxdjdm" id="jxdjdm" style="width: 150px;" onchange="on_changeMc()">
								</select>
							</td>
							<th>
								<font color="red">* </font>奖项名次
							</th>
							<td>
								<select name="jxmcdm" id="jxmcdm" style="width: 150px;" onchange="on_changeJe()">
								</select>
							</td>	
						</tr>
						<tr>
							<th>
								金额
							</th>
							<td>
								<span name="je" id="je">
								</span>	
							</td>
							<th>
								获得时间
							</th>
							<td>
								<html:text property="hdsj" styleId="hdsj" onfocus="showCalendar('hdsj','y-mm-dd');"></html:text>	
							</td>	
						</tr>
						<tr>
							<th align="right">
								附件信息
							</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
								<input type="file" id="filepath_f" name="filepath" />
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										jQuery('#filepath_f').multiUploader({
											maxcount : 3,
											//后缀
											accept : 'png|gif|jpg|jpeg|doc|docx',
											//最大文件大小 单位M
											maxsize: 10,
											//存放附件的隐藏域的id
											elementid : 'filepath',

											eid : 'filepath_f'
											});
									});
								</script>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>申请理由
								<br/>
								<font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="sqly" styleId="sqly" style="width:95%;" rows="5" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:35px"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="saveHjjg('save');">
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

