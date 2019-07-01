<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/bdzc/10511/js/bdzc.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/xsxx_xqbdzcgl" method="post" styleId="xqbdzcForm" style="width:99.9%">
			<html:hidden property="xn" />
			<html:hidden property="xq" />
		
			<div style="height: 15px"></div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="btn">
									<button name="关闭" onclick="Close()" type="button"
										id="buttonClose">
										关 闭
									</button>

								</div>
							</td>
						</tr>
					</tfoot>
				</table>	
			<div class='tab'>
				<table style="margin-bottom: 5px" width="100%" border="0" class="formlist">				
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<span class="red"></span>学号
							</th>
							<td>
								<a href="javascript:void(0);" class="name" 
									   onclick="showDialog('学生详细信息',700,500,'stu_info_details.do?xh=<bean:write name="jbxx" property="xh"/>')"
									   style="margin-left: 1px;"
									 ><bean:write name="jbxx" property="xh"/>
							</td>
							<th width="20%">
								姓名
							</th>
							<td width="30%">
								${jbxx.xm }
							</td>
						</tr>
							<th>
								性别
							</th>
							<td>
								${jbxx.xb }
							</td>
							<th>
								身份证号
							</th>
							<td>
								${jbxx.sfzh }
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td>
								${jbxx.zymc }
							</td>
							<th>
								班级
							</th>
							<td>
								${jbxx.bjmc }
							</td>
						</tr>
						<tr>
							<th>
								学院
							</th>
							<td>
								${jbxx.xymc }
							</td>
							<th>
								年级
							</th>
							<td>
								${jbxx.nj }
							</td>
						</tr>
						<tr>
							<th>
								政治面貌
							</th>
							<td>
								${jbxx.zzmmmc }
							</td>
							<th>
								民族
							</th>
							<td>
								${jbxx.mzmc }
							</td>
						</tr>
						<tr>
							<th>
								家庭地址
							</th>
							<td>
								${jbxx.jtdz }
							</td>
							<th>
								手机号码
							</th>
							<td>
								${jbxx.sjhm }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span><bean:message key="lable.bdzc"/>信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>学年/学期</th>
							<td>${xqbdxx.xn } / ${xqbdxx.xqmc }</td>
							<th><bean:message key="lable.bdzc"/>状态</th>
							<td>${xqbdxx.zcztmc }</td>
						</tr>					
						<logic:equal name="zczt" value="0">
						<tr>
							<th>未注册类别</th>
							<td>${xqbdxx.wbdlbmc }</td>
							<th>预计报到时间</th>
							<td>${xqbdxx.yjbdsj }</td>
						</tr>
						<tr>
							<th>未<bean:message key="lable.bdzc"/>原因</th>
							<td colspan="3">${xqbdxx.wbdyy }</td>
						</tr>
						</logic:equal>				
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>财务交费信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table width="95%">
										<tbody id="cwsjxx">
											<tr>
												<th>学期/学年</td>
												<th>费用名称</td>
												<th>应缴金额</td>
												<th>实缴金额</td>
												<th>欠费金额</td>
											</tr>
											<logic:present name="cwsjList">
													<logic:notEmpty name="cwsjList">
														<logic:iterate id="k" name="cwsjList">
															<tr>
																<td>${k.xqmc} / ${k.xn}</td>
																<td>${k.zd4 }</td>
																<td>${k.zd2 }</td>
																<td>${k.zd3 }</td>
																<td>${k.zd1 }</td>
															</tr>
														</logic:iterate>
													</logic:notEmpty>
													<logic:empty name="cwsjList">
														<tr>
															<td colspan="5" style="text-align:center;">未找到任何记录！</td>
														</tr>
													</logic:empty>
												</logic:present>
										</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>

