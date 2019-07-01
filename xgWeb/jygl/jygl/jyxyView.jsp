<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
<script type="text/javascript" src="js/checkUtils.js"></script>
<body>
	<html:form action="/jygl" method="post">
		<input type="hidden" id="url" value="/jygl.do?method=jyxywh">
		<input type="hidden" id="userType" name="userType"
			value="${userType }" />
		<input type="hidden" id="userName" name="userName"
			value="${userName }" />
		<input type="hidden" id="message" value="${message }">
		<input type="hidden" name="pkValue" value="${pkValue }">
		<input type="hidden" name="njV" id="njV">
		<input type="hidden" name="xyV" id="xyV">
		<input type="hidden" name="zyV" id="zyV">
		<input type="hidden" name="bjV" id="bjV">

		<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>基本信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="16%">
							<span class="red">*</span>学号
						</th>
						<td width="30%">
							${rs.xh }
							<html:hidden property="save_xh" value="${rs.xh }" />
						</td>
						<th width="16%">
							性别
						</th>
						<td width="30%">
							${rs.xb}
						</td>
					</tr>
					<tr>
						<th>
							姓名
						</th>
						<td>
							${rs.xm }
						</td>
						<th>
							身份证号
						</th>
						<td>
							${rs.sfzh }
						</td>
					</tr>
					<tr>
						<th>
							学校
						</th>
						<td>
							${rs.xxmc }
						</td>
						<th>
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td>
							${rs.xymc }
						</td>
					</tr>
					<tr>
						<th>
							专业
						</th>
						<td>
							${rs.zymc }
						</td>
						<th>
							班级
						</th>
						<td>
							${rs.bjmc }
						</td>
					</tr>
					<tr>
						<th>
							学历
						</th>
						<td>
							${rs.xl }
						</td>
						<th>
							生源地
						</th>
						<td>
							${rs.sydq }${rs.syds }${rs.sydx }
						</td>
					</tr>
					<tr>
						<th>
							入学年份
						</th>
						<td>
							${rs.rxnf }
						</td>
						<th>
							毕业年份
						</th>
						<td>
							${rs.bynf }
						</td>
					</tr>
					<tr>
						<th>
							手机号码
						</th>
						<td>
							${rs.sjhm }
						</td>
						<th>
							Email
						</th>
						<td>
							${rs.dzyx }
						</td>
					</tr>
					<tr>
						<th>
							民族
						</th>
						<td>
							${rs.mzmc }
						</td>
						<th>
							政治面貌
						</th>
						<td>
							${rs.zzmmmc }
						</td>
					</tr>
					<tr>
						<th>
							联系电话
						</th>
						<td>
							${rs.lxdh }
						</td>
						<th>
							邮编
						</th>
						<td>
							${rs.yzbh }
						</td>
					</tr>
					<tr>
						<th>
							联系地址
						</th>
						<td colspan="3">
							${rs.lxdz }
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="4">
							<span>就业协议</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							<font color="red">*</font>就业标志
						</th>
						<td>
							<bean:write property="save_jybzmc" name="rs" />
						</td>
						<th>
							<font color="red">*</font>用人单位名称
						</th>
						<td>
							<bean:write property="save_dwmc" name="rs" />
						</td>
						
					</tr>
					<tr>
						<th>
							<font color="red">*</font>用人单位代码
						</th>
						<td>
							<bean:write property="save_dwdm" name="rs" />
						</td>
						<th>
							<font color="red">*</font>用人单位性质
						</th>
						<td>
							<bean:write property="save_dwxzmc" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>主管单位名称
						</th>
						<td>
							<bean:write property="save_zgdwmc" name="rs" />
						</td>
						<th>
							<font color="red">*</font>主管单位代码
						</th>
						<td>
							<bean:write property="save_zgdwdm" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>单位隶属部门
						</th>
						<td>
							<bean:write property="save_lsbmmc" name="rs" />
						</td>
						<th></th>
						<td></td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>用人单位所在地
						</th>
						<td colspan="3">
							<bean:write property="save_yrdwszd" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>报到地区
						</th>
						<td colspan="3">
							<bean:write property="save_bddq" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>报到开始时间
						</th>
						<td>
							<bean:write property="save_bdkssj" name="rs" />
						</td>
						<th>
							<font color="red">*</font>报到结束时间
						</th>
						<td>
							<bean:write property="save_bdjssj" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							下基层情况
						</th>
						<td>
							<bean:write property="save_xjcqkmc" name="rs" />
						</td>
						<th>
							<font color="red">*</font>档案投递单位
						</th>
						<td>
							<bean:write property="save_datddw" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							档案投递地址
						</th>
						<td colspan="3">
							<bean:write property="save_datddz" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							户口迁移地址
						</th>
						<td colspan="3">
							<bean:write property="save_hkqydz" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							单位联系人
						</th>
						<td>
							<bean:write property="save_dwlxr" name="rs" />
						</td>
						<th>
							单位联系电话
						</th>
						<td>
							<bean:write property="save_dwlxdh" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							专业是否对口
						</th>
						<td>
							<bean:write property="save_sfdk" name="rs" />
						</td>
						<th>
							就业行业
						</th>
						<td>
							<bean:write property="save_jyhymc" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							就业备注一
						</th>
						<td colspan="3">
							<bean:write property="save_jybz1" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							就业备注二
						</th>
						<td colspan="3">
							<bean:write property="save_jybz2" name="rs" />
						</td>
					</tr>
					<tr>
						<th>
							就业备注三
						</th>
						<td colspan="3">
							<bean:write property="save_jybz3" name="rs" />
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="4">
							<span>审核信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<logic:equal value="view" name="doType">
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								审核
							</th>
							<td>
								${rs.xysh }
							</td>
							<th>
								学校审核
							</th>
							<td>
								${rs.xxsh }
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								审核意见
							</th>
							<td colspan="3">
								${rs.xyshyj }
							</td>
						</tr>
						<tr>
							<th>
								学校审核意见
							</th>
							<td colspan="3">
								${rs.xxshyj }
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="sh" name="doType">
						<logic:equal value="xy" name="userType">
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
									意见
								</th>
								<td colspan="3" style="word-break:break-all;">
									<html:textarea property="save_xyshyj" name="rs"
										style="width:80%" rows="8" onblur="checkLen(this,500)"></html:textarea>
								</td>
							</tr>
							<tr>
								<th>
									审核人
								</th>
								<td>
									${userNameReal }
									<html:hidden property="save_xyshr" value="${userName }" />
								</td>
								<th>
									审核时间
								</th>
								<td>
									${now }
									<html:hidden property="save_xyshsj" value="${now }" />
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual value="xy" name="userType">
							<tr>
								<th>
									学校意见
								</th>
								<td colspan="3" style="word-break:break-all;">
									<html:textarea property="save_xxshyj" name="rs"
										style="width:80%" rows="8" onblur="checkLen(this,500)"></html:textarea>
								</td>
							</tr>
							<tr>
								<th>
									审核人
								</th>
								<td>
									${userNameReal }
									<html:hidden property="save_xxshr" value="${userName }" />
								</td>
								<th>
									审核时间
								</th>
								<td>
									${now }
									<html:hidden property="save_xxshsj" value="${now }" />
								</td>
							</tr>
						</logic:notEqual>
					</logic:equal>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"
								<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<logic:equal value="sh" name="doType">
									<logic:equal value="xy" name="userType">
										<logic:equal value="退回" name="rs" property="xxsh">
											<button id="buttonSave"
												onClick="saveUpdate('/xgxt/jygl.do?method=jyxyShow&save_xysh=通过&save_xxsh=需重审&doType=save','');">
												通过
											</button>
										</logic:equal>
										<logic:notEqual value="退回" name="rs" property="xxsh">
											<button id="buttonSave"
												onClick="saveUpdate('/xgxt/jygl.do?method=jyxyShow&save_xysh=通过&doType=save','');">
												通过
											</button>
										</logic:notEqual>
										<button id="buttonSave"
											onClick="saveUpdate('/xgxt/jygl.do?method=jyxyShow&save_xysh=不通过&doType=save','');">
											不通过
										</button>
										<button id="buttonSave"
											onClick="saveUpdate('/xgxt/jygl.do?method=jyxyShow&save_xysh=退回&doType=save','');">
											退回
										</button>
									</logic:equal>
									<logic:notEqual value="xy" name="userType">
										<button id="buttonSave"
											onClick="saveUpdate('/xgxt/jygl.do?method=jyxyShow&save_xxsh=通过&doType=save',' ');">
											通过
										</button>
										<button id="buttonSave"
											onClick="saveUpdate('/xgxt/jygl.do?method=jyxyShow&save_xxsh=不通过&doType=save',' ');">
											不通过
										</button>
										<button id="buttonSave"
											onClick="saveUpdate('/xgxt/jygl.do?method=jyxyShow&save_xxsh=退回&doType=save',' ');">
											退回
										</button>
									</logic:notEqual>
								</logic:equal>
								<button name="关闭" onClick="window.close();">
									关闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</html:form>
	<logic:present name="result">
		<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
	</logic:present>
</body>
