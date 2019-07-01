<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/checkUtils.js"></script>
		<script type="text/javascript">
		function setCl() {
			var bbyy = $('bbyy').value;
			
			if ('遗失' == bbyy) {
				DWRUtil.setValue('sxcl','刊登遗失启事的报纸');
				DWRUtil.setValue('save_sxcl','刊登遗失启事的报纸');
				$('sm').innerText = '遗失补办原则：根据江苏省教育厅的规定,'+
				'在遗失的当地登报（必须是公开发行的报纸）申明‘遗失启事’。'+
				'毕业生查询原协议书编号，到市级以上公开发行的报刊上'+
				'刊登原协议书声明作废的遗失启事，需刊登姓名、毕业院校及就业协议书编号，'+
				'例：×××遗失毕业生就业协议书，××学校 ，号码×××××，声明作废。';
			} else if ('更换' == bbyy) {
				DWRUtil.setValue('sxcl','原推荐表');
				DWRUtil.setValue('save_sxcl','原推荐表');
				$('sm').innerText ='《毕业生双向选择就业推荐表》中的内容以11月'+
				'份制作时毕业生信息为准。之后毕业生信息发生变动，学生只需另附相关材料说'+
				'明即可，《推荐表》将不做更改。《推荐表》除因时间推移而造成的个人信息变动'+
				'的毕业生，在就业网络联盟网站（www.91job.gov.cn）更改个人信息。';
			} else {
				DWRUtil.setValue('sxcl','');
				DWRUtil.setValue('save_sxcl','');
				$('sm').innerText = '注意：申请还需下载《推荐表补办申请表》进行填写，凭上述'+
				'补办所需材料到院（系）进行《推荐表补办申请表》审核。院（系）审核完毕并'+
				'签字后，打印新的推荐表，凭补办所需材料、《申请表》和新《推荐表》到就业'+
				'管理科盖章。如果更换《推荐表》，原《推荐表》必须由就业管理科收回后，方可在新《推荐表》上盖章。';
			}
		}
	</script>
	</head>
	<body>
		<html:form action="/jygl" method="post">
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" id="message" value="${message }" />
			<input type="hidden" name="save_sqsj" value="${now }" />
			<input type="hidden" name="pkValue" value="${pkValue }" />
			<input type="hidden" name="njV" id="njV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="url" id="url"
				value="jygl.do?method=tjbBb" />
			<input type="hidden" name="xh" id="xh"
				value="${xh }" />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>补办信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								学号
							</th>
							<td width="34%">
								<html:text value="${rs.xsxh }" property="save_xh" styleId="xh"
									readonly="true"></html:text>
								<logic:notEqual value="stu" name="userType">
									<button
										onclick="showTopWin('jygl.do?method=jyglData',750,550);"
										class="btn_01" id="buttonFindStu">
										选择
									</button>
								</logic:notEqual>
							</td>
							<th width="16%">
								姓名
							</th>
							<td width="34%">
								${rs.name }
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
								身份证号
							</th>
							<td>
								${rs.id }
							</td>
						</tr>
						<tr>
							<th>
								学生类别
							</th>
							<td>
								${rs.xslb }
							</td>
							<th>
								辅导员
							</th>
							<td>
								${rs.fdy }
							</td>
						</tr>
						<tr>
							<th>
								入学时间
							</th>
							<td>
								${rs.nd }
							</td>
							<th>
								毕业时间
							</th>
							<td>
								${rs.bynd }
							</td>
						</tr>
						<tr>
							<th>
								协议书编号
							</th>
							<td>
								<html:text property="save_xysbh" value="${rs.xysbh }"/>
							</td>
							<th>
								单位名称
							</th>
							<td>
								${rs.jydw }
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>补办原因
							</th>
							<td>
								<logic:equal value="view" name="doType">
									<html:select property="save_bbyy" styleId="bbyy"
										onchange="setCl();" value="${rs.bbyy }" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="yyList" property="en"
											labelProperty="cn" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="view" name="doType">
									<html:select property="save_bbyy" styleId="bbyy"
										onchange="setCl();" value="${rs.bbyy }">
										<html:option value=""></html:option>
										<html:options collection="yyList" property="en"
											labelProperty="cn" />
									</html:select>
								</logic:notEqual>

							</td>
							<th>
								所需材料
							</th>
							<td>
								<html:hidden property="save_sxcl" styleId="save_sxcl"
									value="${rs.sxcl }" />
								<html:select styleId="sxcl" property="save_sxcl" disabled="true"
									value="${rs.sxcl }">
									<html:option value=""></html:option>
									<html:options collection="clList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								友情提示
							</th>
							<td colspan="3">
								<div style="color:red;width:80%;" id="sm">
									注意：申请还需下载《协议书补办申请表》进行填写，凭上述补办所需材料到院（系）进行《协议书补办申请表》审核。院（系）审核完毕并签字后，凭补办所需材料和《申请表》到就业管理科申请领取新的就业协议书。
								</div>
							</td>
						</tr>
<%--						<logic:equal value="view" name="doType">--%>
<%--							<tr>--%>
<%--								<th>--%>
<%--									<bean:message key="lable.xsgzyxpzxy" />--%>
<%--									意见--%>
<%--								</th>--%>
<%--								<td colspan="3">--%>
<%--									<html:textarea property="save_xyyj" style="width:80%" rows="8"--%>
<%--										onblur="checkLeng(this,500)" value="${ rs.xyyj}"></html:textarea>--%>
<%--								</td>--%>
<%--							</tr>--%>
<%--						</logic:equal>--%>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:notEqual value="view" name="doType">
										<button class="button2" id="buttonSave"
											onClick="saveUpdate('jygl.do?method=tjbBb&doType=save','save_xh-save_bbyy-save_sxcl');">
											申请补办
										</button>
									</logic:notEqual>
									<logic:equal value="view" name="doType">
										<logic:equal value="xy" name="userType">
											<button class="button2" id="buttonSave"
												onClick="saveUpdate('jygl.do?method=showTjb&save_xysh=通过&doType=save','');">
												通&nbsp;&nbsp;过
											</button>
											<button class="button2" id="buttonSave"
												onClick="saveUpdate('jygl.do?method=showTjb&save_xysh=不通过&doType=save','');">
												不通过
											</button>
										</logic:equal>
										<button class="button2" id="buttonSave"
											onClick="window.close();">
											关&nbsp;&nbsp;闭
										</button>
									</logic:equal>
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
</html>
