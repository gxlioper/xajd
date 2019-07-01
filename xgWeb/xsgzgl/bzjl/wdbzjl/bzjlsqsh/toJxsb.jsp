<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/sqsh/js/jxsb.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var bjdFont = jQuery("font[name=bjdFont]");
				
				if (bjdFont.size() > 0){
					jQuery("#saveButton").attr("disabled",true);
					jQuery("#saveButton").attr("class","disabled");
				}
				
				//条件验证
				if (jQuery("img[name=faidImg]").size() > 0){
					jQuery("#saveButton").attr("disabled",true);
					jQuery("#saveButton").attr("class","disabled");
				}
				
			});
		</script>
	</head>
	<body>
		<html:form action="/bzjl_sqsh" method="post" styleId="sqshForm">
			<html:hidden property="xh" />
			<html:hidden property="xmdm" />
		
			<div class='tab'>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" id="saveButton" onclick="saveJxsb();">
										保 存
									</button>
									
									<button type="button"  onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					
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
								学号
							</th>
							<td width="30%">
								${jbxx.xh }
							</td>
							<th width="20%">
								姓名
							</th>
							<td width="30%">
								${jbxx.xm }
							</td>
						</tr>
						<tr>
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
								年级
							</th>
							<td>
								${jbxx.nj }
							</td>
							<th>
								<bean:message key="lable.xb" />
							</th>
							<td>
								${jbxx.xymc }
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
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>上报奖项</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								项目名称
							</th>
							<td>
								${xmwhModel.xmmc }
							</td>
							<th>
								项目金额
							</th>
							<td>
								${xmwhModel.xmje }
							</td>
						</tr>
						<tr>
							<th>
								限制条件
							</th>
							<td colspan="3">
								
								<logic:notEmpty name="checkResult">
									<logic:iterate id="check" name="checkResult" indexId="i">
										<logic:equal value="true" name="check" property="result">
											<img src='images/ico_38.gif' title='符合条件'/> ${i+1 }、${check.sqts }<br/>
										</logic:equal>
										<logic:equal value="false" name="check" property="result">
											<img src='images/ico_39.gif' name='faidImg' title='不符合条件'/> ${i+1 }、${check.sqts }<br/>
										</logic:equal>
									</logic:iterate>
								</logic:notEmpty>
								<logic:empty name="checkResult">
									<font color='green'>无限制条件</font>
								</logic:empty>								
							</td>
						</tr>
						<tr>
							<th>
								已申请或上报奖项
							</th>
							<td colspan="3">
								<table width="99%" style="text-align: center;">
									<tr>
										<th style="text-align: center;">奖项</th>
										<th style="text-align: center;">金额</th>
										<th style="text-align: center;">是否与当前奖项兼得</th>
									</tr>
									<logic:present name="ysqPjxmList">
										<logic:iterate id="y" name="ysqPjxmList">
											<tr>
												<td>${y.xmmc }</td>
												<td>${y.xmje }</td>
												<td>
													<logic:equal value="yes" name="y" property="bkjd">
														<font color="red" name="bjdFont">不可兼得</font>
													</logic:equal>
													<logic:notEqual value="yes" name="y" property="bkjd">
														可兼得
													</logic:notEqual>
												</td>
											</tr>
										</logic:iterate>
										<logic:empty name="ysqPjxmList">
											<tr><td colspan="3">未找到任何记录！</td></tr>
										</logic:empty>
									</logic:present>
								</table>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>上报理由
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="sqly" styleId="sqly" style="width:99%;" rows="5" onkeypress="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>

