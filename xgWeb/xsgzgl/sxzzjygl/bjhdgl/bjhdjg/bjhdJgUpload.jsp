<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/sxzzjygl/bjhdgl/bjhdjg/js/bjhdJg.js"></script>
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
		<html:form action="/bjhdgl_bjhdjg" method="post" styleId="BjhdJgForm" onsubmit="return false;">
			<html:hidden property="jgid" styleId="jgid"></html:hidden>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>班级活动</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								活动名称
							</th>
							<td width="30%">
								${model.hdmc}
							</td>
							<th>活动主题</th>
							<td>
								${model.hdzt}
							</td>
						</tr>
						<tr>
							<th width="20%">
								活动日期
							</th>
							<td width="30%">
								${model.hdrq}
							</td>
							<th width="20%">
								班级名称
							</th>
							<td width="30%">
								${bjmc}
							</td>
						</tr>
						
						<tr>
							<th width="20%">
								活动负责人
							</th>
							<td >
								${hdfzr}
							</td>
							<th width="20%">
								负责人联系方式
							</th>
							<td width="30%">
								${model.hdfzrlxdh}
							</td>
							
						</tr>
						<tr>
							<th width="20%">
								负责人老师
							</th>
							<td width="30%">
								${model.fzls}
							</td>
							<th width="20%">
								负责老师联系方式
							</th>
							<td width="30%">
								${model.fzlslxdh}
							</td>
						</tr>
						<tr>
							<th width="20%">
								活动预算
							</th>
							<td width="30%">
								${model.hdys}
							</td>

						</tr>
						<tr><th width="20%">活动预算依据及明细</th>
							<td colspan="3">
								<html:textarea property="hdysyjmx" styleId="hdysyjmx"
											   onkeypress="checkLen(this,500);"
											   style="width:99%;" readonly="true" rows="4">${model.hdysyjmx}</html:textarea>
							</td>
						</tr>
						<tr><th width="20%">活动概要</th>
							<td colspan="3">
								<html:textarea property="hdgy" styleId="hdgy"
											   onkeypress="checkLen(this,500);"
											   style="width:99%;" readonly="true" rows="4">${model.hdgy}</html:textarea>
							</td>
						</tr>
						<tr><th width="20%">活动实施方案</th>
							<td colspan="3">
								<html:textarea property="hdssfa" styleId="hdssfa"
											   onkeypress="checkLen(this,500);"
											   style="width:99%;" readonly="true" rows="4">${model.hdssfa}</html:textarea>
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
								<button type="button" onclick="bjhdJgSaveForUpload();">
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

