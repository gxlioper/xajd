<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/sxzzjygl/ztbhgl/ztbhjg/js/ztbhJg.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/provicecitylocal.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/comm.js"></script>
		<link rel="stylesheet" href="js/provicecitylocal.css" type="text/css"/>
		<script type='text/javascript'>
			jQuery(function(){
                onShow("add");
			})
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/ztbhgl_ztbhjg" method="post" styleId="ZtbhJgForm" onsubmit="return false;">
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
								学年
							</th>
							<td width="30%">
								${xn}
							</td>
							<th width="20%">
								<font color="red">*</font>填报日期
							</th>
							<td width="30%">
								<html:text property="hdrq" styleId="hdrq" readonly="true" value="${today}"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								学期
							</th>
							<td width="30%">
								${xq}
							</td>
							<th><span class="red">*</span>班会主题</th>
							<td>
								<html:text property="hdzt" styleId="hdzt" maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<span class="red">*</span>班会时间
							</th>
							<td width="30%">
								<html:text property="bhsj" onclick="return showCalendar('bhsj','yyyy-MM-dd HH:mm');" styleId="bhsj" readonly="true"></html:text>
							</td>
							<th><span class="red">*</span>地点</th>
							<td>
								<html:text property="dd" styleId="dd" maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<span class="red">*</span>班主任是否参加
							</th>
							<td colspan="3">
								<html:select property="bzrsfcj" styleId="bzrsfcj">
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
							</td>
						</tr>
					</tbody>
						<thead>
							<tr>
								<th colspan="4"><span>班级信息&nbsp;&nbsp;
                        <a class="name" href="javascript:void(0);" onclick="addTr();return false;">增加</a>
								</span></th>
							</tr>
						</thead>
						<tbody>
							<style type="text/css">
								#shlccx_table th{text-align: center;}
								#shlccx_table tr{text-align: center;}
							</style>
							<tr>
								<td colspan="4">
									<table id="shlccx_table" width="100%">
										<tr>
											<th><span class="red">*</span>行政班级</th>
											<th><span class="red">*</span>应到人数</th>
											<th><span class="red">*</span>实到人数</th>
											<th><span class="red">*</span>缺勤人数</th>
											<th><span class="red">*</span>书院</th>
											<th><span class="red">*</span>辅导员</th>
											<th>操作</th>
										</tr>
									</table>
								</td>
							</tr>
						</tbody>
					<thead>
					<tr>
						<th colspan="4">
							<span>班会内容</span>
						</th>
					</tr>
					</thead>
					<tbody>
						<tr><th width="20%"><span class="red">*</span>班会内容
								</br><font color="red">(限200字)</font></th>
							<td colspan="3">
								<html:textarea property="bhmd" styleId="bhmd"
											   onkeypress="checkLen(this,200);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
					</tr>
						<tr>
							<th width="20%"><span class="red">*</span>班会总结
							</br><font color="red">(限200字)</font></th>
							<td colspan="3">
								<html:textarea property="bhyc" styleId="bhyc"
											   onkeypress="checkLen(this,200);"
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
								<button type="button" onclick="ztbhJgSave();">
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

