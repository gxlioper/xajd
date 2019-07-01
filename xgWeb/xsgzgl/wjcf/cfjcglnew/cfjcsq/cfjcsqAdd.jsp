<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='/xgxt/js/check.js'></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/wjcf/cfjcglnew/cfjcsq/js/cfjcsq.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
		//下载
		function fjxz(){
			var url="wjcf_cfsh.do?method=fjxz";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		</script>
	</head>
	
	<body onload="showCfqxFlag('${map.cflbdm }');">
		<html:form method="post" styleId="cfjcsqForm" action="/wjcf_cfjcsq"  >
			<html:hidden property="cfid" styleId="cfid"/>
			<input type="hidden" id="text" value="<bean:message key="wjcf.text" />"/>
			<input type="hidden" name="fjmc" id="fjmc" value="${map.fjmc }"/>
			<input id="xxdm" type="hidden" value="${xxdm}"/>
			<div
				style='width: 100%; height: 460px; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/wjcf/cfsbglnew/cfsb/selectStudent.jsp"%>
					<thead>
						<tr>
							<th colspan="4">
								<span>处分上报信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="20%">
								学年学期
							</th>
							<td align="left" width="30%">						
								${map.xn }${map.xqmc }		
							</td>
							<th align="right" width="20%">
								违纪时间
							</th>
							<td align="left" width="30%">
								${map.wjsj }
							</td>
						</tr>
						<tr>
							<th align="right">
								处分原因
							</th>
							<td align="left">
								${map.cfyymc }
							</td>
							<th align="right">
								处分类别
							</th>
							<td align="left">
								${map.cflbmc }&nbsp;&nbsp;&nbsp;&nbsp;<span id="showCfqx" style="color: red"></span>
							</td>
						</tr>
						<tr>
							<th align="right">
								处分建议
							</th>
							<td align="left" colspan="3">
								${map.cfyj }
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								学生检讨书
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
                                    //调用附件
                                    jQuery(function(){
                                        var gid = "${map.filepath}";
                                        jQuery.MultiUploader_q({
                                            gid : gid,
                                            targetEl : 'commonfileupload-list-0'
                                        });
                                    });
								</script>
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								考场违纪记录单
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-2" style="padding: 5px;"></div>
								<script type="text/javascript">
                                    //调用附件
                                    jQuery(function(){
                                        var gid = "${map.filepath2}";
                                        jQuery.MultiUploader_q({
                                            gid : gid,
                                            targetEl : 'commonfileupload-list-2'
                                        });
                                    });
								</script>
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								夹带纸条
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-3" style="padding: 5px;"></div>
								<script type="text/javascript">
                                    //调用附件
                                    jQuery(function(){
                                        var gid = "${map.filepath3}";
                                        jQuery.MultiUploader_q({
                                            gid : gid,
                                            targetEl : 'commonfileupload-list-3'
                                        });
                                    });
								</script>
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								申辩会议记录
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-4" style="padding: 5px;"></div>
								<script type="text/javascript">
                                    //调用附件
                                    jQuery(function(){
                                        var gid = "${map.filepath4}";
                                        jQuery.MultiUploader_q({
                                            gid : gid,
                                            targetEl : 'commonfileupload-list-4'
                                        });
                                    });
								</script>
							</td>
						</tr>
						<tr>
							<th align="right">
								违纪事实经过
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								${map.wjssjg }
							</td>
						</tr>
						<tr>
							<th align="right">
								处分文号
							</th>
							<td align="left">
								${map.cfwh }
							</td>
							<th align="right">
								处分时间
							</th>
							<td align="left">
								${map.cfsj }
							</td>
						</tr>
						<logic:present name="map" property="cfdqsj">
							<tr>
								<th align="right">
									处分到期时间
								</th>
								<td align="left"  colspan="3">
									${map.cfdqsj }
								</td>
							</tr>
						</logic:present>
						<tr>
							<th align="right">
								备注
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								${map.bz }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span><bean:message key="wjcf.text" />信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:notEqual name="xxdm" value="12865">
							<tr>
								<th align="right">
									<font color="red">*</font><bean:message key="wjcf.text" />处分建议
									<br />
									<font color="red"><B>(限1000字)</B>
									</font>
								</th>
								<td align="left" colspan="3">
									<html:textarea property='sqly' styleId="sqly" style="width:600px" rows='5'
										onblur="checkLen(this,1000)" />
								</td>
							</tr>
							<tr>
								<th align="right">
									<font color="red">*</font>班级意见
									<br />
									<font color="red"><B>(限1000字)</B>
									</font>
								</th>
								<td align="left" colspan="3">
									<html:textarea property='bjyj' styleId="bjyj" style="width:600px" rows='5'
												   onblur="checkLen(this,1000)" />
								</td>
							</tr>
							<tr>
								<th align="right">
									<font color="red">*</font>情况说明
									<br />
									<font color="red"><B>(限1000字)</B>
									</font>
								</th>
								<td align="left" colspan="3">
									<html:textarea property='qksm' styleId="qksm" style="width:600px" rows='5'
												   onblur="checkLen(this,1000)" />
								</td>
							</tr>
						</logic:notEqual>
						<tr>
							<th>
								<font color="red">*</font>跟踪教育记录表
							</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath"/>
								<input type="file" id="filepath_f" name="filepath" />
								<script type="text/javascript">
                                    //调用附件
                                    jQuery(function(){
                                        jQuery('#filepath_f').multiUploader({
                                            maxcount : 1,
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
						<tr>
							<th>
								<font color="red">*</font>成绩单
							</th>
							<td colspan="3">
								<html:hidden property="filepath2" styleId="filepath2"/>
								<input type="file" id="filepath_f2" name="filepath2" />
								<script type="text/javascript">
                                    //调用附件
                                    jQuery(function(){
                                        jQuery('#filepath_f2').multiUploader({
                                            maxcount : 1,
                                            //后缀
                                            accept : 'png|gif|jpg|zip|rar|doc|docx',
                                            //最大文件大小 单位M
                                            maxsize: 10,
                                            //存放附件的隐藏域的id
                                            elementid : 'filepath2',
                                            eid : 'filepath_f2'
                                        });
                                    });
								</script>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="save('wjcf_cfjcsq.do?method=cfjcsqSave&type=save')" id="buttonSave">
										保存草稿
									</button>
									&nbsp;&nbsp;
									<button type="button" onclick="save('wjcf_cfjcsq.do?method=cfjcsqSave&type=submit')" id="buttonSave">
										提交申请
									</button>
									&nbsp;&nbsp;
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