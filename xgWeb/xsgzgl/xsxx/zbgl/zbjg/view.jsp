<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
	</head>
	<body>
		<html:form action="/zbglZbjg" method="post" styleId="form">
			
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:420px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>征兵信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>独生子女</th>
							<td>
								${zbglZbjgForm.dszn }
							</td>
							<th>婚姻状况</th>
							<td>
								${zbglZbjgForm.hyzk }
							</td>
						</tr>
						<tr>
							<th>视力</th>
							<td>
								${zbglZbjgForm.sl }
							</td>
							<th>身高</th>
							<td>
								${zbglZbjgForm.sg } cm
							</td>
						</tr>
						<tr>
							<th>体重</th>
							<td>
								${zbglZbjgForm.tz } kg
							</td>
							<th>应征地</th>
							<td>
								${zbglZbjgForm.yzd }
							</td>
						</tr>
						<tr>
							<th>应征来源</th>
							<td>
								${zbglZbjgForm.yzly }
							</td>
							<th>参军意愿</th>
							<td>
								${zbglZbjgForm.cjyy }
							</td>
						</tr>
						<logic:equal name="xxdm" value="14073">
							<tr>
								<th>就读起止日期</th>
								<td>
									${zbglZbjgForm.ylzd1}
									至
									${zbglZbjgForm.ylzd2}
								</td>
								<th>学习类型</th>
								<td>
									${zbglZbjgForm.ylzd3}
								</td>
							</tr>
							<tr>
								<th>学业情况</th>
								<td>
									${zbglZbjgForm.ylzd4}
								</td>
								<th>学校名称</th>
								<td>
									${zbglZbjgForm.ylzd5}
								</td>
							</tr>
							<tr>
								<th>学校资助部门地址</th>
								<td colspan="3">
									${zbglZbjgForm.ylzd7}
								</td>
							</tr>
							<tr>
								<th>邮编</th>
								<td>
									${zbglZbjgForm.ylzd8}
								</td>
								<th>院校所在地</th>
								<td>
									${zbglZbjgForm.ylzd6}
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th>从业类别</th>
							<td>
								${zbglZbjgForm.cylb }
							</td>
							<th>职业资格证书</th>
							<td>
								${zbglZbjgForm.zgzs }
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="3" style="">
								${zbglZbjgForm.bz }
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = "${zbglZbjgForm.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
											});
									});
								</script>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
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