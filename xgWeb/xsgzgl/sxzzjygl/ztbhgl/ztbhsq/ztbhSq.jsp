<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/sxzzjygl/ztbhgl/ztbhsq/js/ztbhSq.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/provicecitylocal.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<link rel="stylesheet" href="js/provicecitylocal.css" type="text/css"/>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/ztbhgl_ztbhsq" method="post" styleId="ZtbhSqForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>主题班会</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<span class="red">*</span>活动名称
							</th>
							<td width="30%">
								<html:text property="hdmc" styleId="hdmc" maxlength="100"></html:text>
							</td>
							<th><span class="red">*</span>活动主题</th>
							<td>
								<html:text property="hdzt" styleId="hdzt" maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>活动日期
							</th>
							<td width="30%">
								<html:text property="hdrq" onclick="return showCalendar('hdrq','yyyy-MM-dd HH:mm');" styleId="hdrq" ></html:text>
							</td>
							<th width="20%">
								<font color="red">*</font>班级名称
							</th>
							<td width="30%">
								<input type="text" name="bjmc" value="" id="bjmc" style="width:159px;" readonly="readonly"
									   class="text_nor"  >
								<input type="hidden" name="bjdm" id="bjdm" value=""/>
								<button class="btn_01" type="button"
										onclick="showDialog('请选择一个班级',800,500,'ztbhgl_ztbhsq.do?method=getBj');">选择
								</button>
							</td>
						</tr>
						
						<tr>
							<th width="20%">
								<font color="red">*</font>活动负责人
							</th>
							<td >
								<input type="text" name="hdfzrxm" value="" id="hdfzrxm" style="width:120px;" readonly="readonly"
									   class="text_nor">
								<input type="hidden" name="hdfzr" id="hdfzr" value=""/>
								<button class="btn_01" type="button"
										onclick="showDialog('请选择一个学生',800,500,'ztbhgl_ztbhsq.do?method=getXx');">选择
								</button>
							</td>
							<th width="20%">
								<font color="red">*</font>负责人联系方式
							</th>
							<td width="30%" >
								<html:text property="hdfzrlxdh" styleId="hdfzrlxdh" maxlength="20"></html:text>
							</td>
							
						</tr>
						<tr>
							<th width="20%">
								负责人老师
							</th>
							<td width="30%">
								<html:text property="fzls" styleId="fzls" maxlength="20"></html:text>
							</td>
							<th width="20%">
								负责老师联系方式
							</th>
							<td width="30%">
								<html:text property="fzlslxdh" styleId="fzlslxdh" maxlength="20"></html:text>
							</td>
						</tr>
						<tr><th width="20%">班会目的
								</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="bhmd" styleId="bhmd"
											   onkeypress="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
					</tr>
						<tr><th width="20%">班会议程（议题）
							</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="bhyc" styleId="bhyc"
											   onkeypress="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								附件
							</th>
							<td  colspan="3">
								<html:hidden property="fj" styleId="fj" />
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
                                            elementid : 'fj',

                                            eid : 'filepath_f'
                                        });
                                    });
								</script>
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
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="ztbhSqSaveForAdd('save');">
										保存草稿
									</button>
									<button type="button" onclick="ztbhSqSaveForAdd('submit');">
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
		</html:form>
	</body>
</html>

