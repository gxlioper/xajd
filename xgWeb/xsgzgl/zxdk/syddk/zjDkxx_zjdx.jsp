<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/syddk/js/dkxx.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
			function saveDksq(url){
				var xh = jQuery("#xh").val();
				var xn = jQuery("#xn").val();
				var dkje = jQuery("#dkje").val();
				var sqly = jQuery("#sqly").val();
				var dkyh = jQuery("#dkyh").val();
				var hzjym = jQuery("#hzjym").val();
				
				if (xh==""||xn == "" || dkje == ""  || sqly == ""){
					showAlertDivLayer("请将必填项填写完整。");
					return false;
				}
				
				if("国家开发银行"==dkyh.trim()&&""==hzjym.trim()){
					showAlertDivLayer("国家开发银行必须填写回执码");
					return false;
				}
				
				var dkzs=jQuery("#dkje").val();
				if(parseInt(dkzs)>parseInt(jQuery("#dkzesx").val())){
					showAlertDivLayer("贷款金额超过"+jQuery("#dkzesx").val()+"元!");
					jQuery("#dkje").focus();
						return false;
					}
				ajaxSubFormWithFun("xyddkForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						refershParent();
					}});
				});
			}
			
			jQuery(function(){
				
				var _getAfterXn = function(xn,c){
					
					var xnInfo = xn.split("-");
					var star = Number(jQuery.trim(xnInfo[0]))+c;
					var end = Number(jQuery.trim(xnInfo[1]))+c;
					
					return star + "-" + end;
				};
				
				
				jQuery("#xn").bind("change",function(){
					jQuery("#dkxxTable tr").remove();
					
					var xn = jQuery(this).val();
					var xz = jQuery("#xz").val();
										
					if (xn != "" && xz != ""){
						
						for (var i = 0 ; i < Number(xz); i++){
							var newXn = _getAfterXn(xn,i);
							
							var tr = jQuery("<tr></tr>");
							var td = jQuery("<td><input type='hidden' name='dkxn' value='"+newXn+"'/>"+newXn+"</td>");
							var td1 = jQuery("<td><input type='text' name='xf' maxlength='10' onkeyup='value=value.replace(/[^\\d]/g,\"\")'/></td>");
							var td2 = jQuery("<td><input type='text' name='zsf' maxlength='10' onkeyup='value=value.replace(/[^\\d]/g,\"\")'/></td>");
							var td3 = jQuery("<td><input type='text' name='shf' maxlength='10' onkeyup='value=value.replace(/[^\\d]/g,\"\")'/></td>");
							tr.append(td).append(td1).append(td2).append(td3);
							jQuery("#dkxxTable").append(tr);
						}
					}
				});
				changeDkyh();
			});

			function changeDkyh(){
				// 重置
				jQuery("#dkyh").val("");
				//取得数据表：xg_pjpy_new_pjxmb; 字段：xmmc																							
				var autoSetting = {
					dataTable:"xg_zxdk_syddk",
					dataField:"dkyh",
					scrollHeight:135										
				}
				// 模糊搜索下拉【项目名称】
				jQuery("#dkyh").setAutocomplete(autoSetting);
			}
			
		</script>
	</head>
	<body>
		<html:form action="/zxdkSyddk" method="post" styleId="xyddkForm">
			<input type="hidden" name="xz" id="xz" value="${jbxx.xz }"/>
			<html:hidden property="dkzesx" styleId="dkzesx" value="${cssz.dkzesx }"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
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
								<span>贷款信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:20%;"><font color="red">*</font>贷款学年</th>
							<td>
								<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
							<th style="width:20%;"><font color="red">*</font>贷款银行</th>
							<td>
								<html:text  property="dkyh" styleId="dkyh" maxlength="50" ></html:text>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>贷款金额（元）</th>
							<td>
								<html:text property="dkje" styleId="dkje" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th>回执检验码</th>
							<td colspan="3">
								<html:text property="hzjym" styleId="hzjym" maxlength="20" ></html:text>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<font color="red">注意：请按学年用款情况如实填写，无则留空。</font>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<table style="width:100%;text-align:center;" >
									<thead>
										<tr>
											<td align="center">学年</td>
											<td align="center">学费（元）</td>
											<td align="center">住宿费（元）</td>
											<td align="center">生活费（元）</td>
										</tr>
									</thead>
									<tbody id="dkxxTable"></tbody>
								</table>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>申请理由
								<br/><font color="red">(限输入500字)</font>
							</th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" 
											   onblur="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
						<tr>
							<th align="right" width="16%">
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
											accept : 'png|gif|jpg|zip|rar|doc|docx',
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
					</tbody>
				</table>
			</div>
			<div>
				<div style="height: 30px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveDksq('zxdkSyddk.do?method=save');">
										保    存
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