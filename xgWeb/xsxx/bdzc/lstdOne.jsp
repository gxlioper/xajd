<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getDtjsInfo.js'></script>
	<script type="text/javascript" src="js/check.js"></script>
</head>
<body>
	<html:form action="/bdzcgl" method="post">
		<input type="hidden" name="pkValue" value="${pkValue }"/>
		<input type="hidden" id="url" name="url" value="/bdzcgl.do?method=lstdOne" />
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-bjmc" />
		<input type="hidden" name="message" id="message" value="${message }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		
		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
			<tr>
				<th colspan="4"><span>绿色通道</span></th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th width="16%"><span class="red">*</span>学号</th>
				<td width="34%">
					
					<logic:equal value="" name="doType">
						<html:text  property="save_xh" styleId="xh"  value="${rs.xh}"
							onkeypress="autoFillStuInfo(event.keyCode,this)"
							onblur="chkIsStu(this,'view_xsjbxx','xh')"
						/>
					</logic:equal>
					<logic:notEqual value="" name="doType">
						<html:text  property="save_xh" styleId="xh"  value="${rs.xh}" readonly="true"
						/>
					</logic:notEqual>
					
					<logic:notEqual value="stu" name="userType">
						<logic:notEqual value="view" name="doType">
							<logic:notEqual value="modi" name="doType">
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',800,600);" class="btn_01" id="buttonFindStu">
										选择
									</button>
							</logic:notEqual>
						</logic:notEqual>
					</logic:notEqual>
				</td>
				<th  width="16%">姓名</td>
				<td>
					${rs.xm }
				</td>
			</tr>
			<tr>
				<th>年级</th>
				<td>
					${rs.nj }
				</td>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>
					${rs.xymc }
				</td>
			</tr>
			<tr>
				<th>专业</th>
				<td>
					${rs.zymc }
				</td>
				<th>班级</th>
				<td>
					${rs.bjmc }
				</td>
			</tr>
			
			<tr>
				<th>学年</th>
				<td>
					<html:hidden property="save_xn" value="${xn }${rs.xn }"/>
					<html:select property="save_xn" value="${xn }${rs.xn }" disabled="true">
						<html:options collection="xnList" property="xn" labelProperty="xn"/>
					</html:select>
				</td>
				<th>学期</th>
				<td>
					<html:hidden property="save_xq" value="${currXq }${rs.save_xq }"/>
					<html:select property="save_xq" value="${currXq }${rs.save_xq }" disabled="true">
						<html:option value=""></html:option>
						<html:options collection="xq" property="xqdm" labelProperty="xqmc"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>金额</th>
				<td>
					<html:text property="save_je" maxlength="10" style="width:80px" onblur="checkMoney(this);" name="rs"></html:text>
				</td>
				<th>助学贷款学年</th>
				<td>
					 <script>
					 	if (${rs.xz}-Number(${zxdk.fkxn })+1>4)
						 document.write('大四或已毕业学生没有<br/>对应的助学贷款记录!');
						else 
						 document.write(${rs.xz}-Number(${zxdk.fkxn })+1);
					 </script>	
				</td>
			</tr>
			<tr>
				<th>是否欠费</th>
				<td>
					<logic:present name="qfqk">
						<logic:equal value=""name="qfqk" property="sfqf">
							否
						</logic:equal>
						<logic:notEqual value=""name="qfqk" property="sfqf">
							${qfqk.sfqf }
						</logic:notEqual>
					</logic:present>
				</td>
				<th>欠费金额</th>
				<td>
					${qfqk.qfje }
				</td>
			</tr>
			</tbody>
			<thead>
			<tr>
				<th colspan="4"><span>助学贷款情况</span></th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th>合同编号</th>
				<td>
					${zxdk.htbh }
				</td>
				<th>贷款金额</th>
				<td>
					${zxdk.sqdkje }
				</td>
			</tr>
			<tr>
				<th>贷款期限(月)</th>
				<td>
					${zxdk.dkqxy }
				</td>
				<th>贷款期限</th>
				<td>
					${zxdk.dkqx }
				</td>
			</tr>
			<tr >
				<th>放款总金额</th>
				<td>
					${zxdk.fkzje }
				</td>
				<th>贷款余额</th>
				<td>
					${zxdk.dkye }
				</td>
			</tr>
			
			<tr >
				<th>学年</th>
				<th>
					放款金额
				</th>
				<th>是否放款</th>
				<th>
					提款时间
				</th>
			</tr>
			<tr >
				<td align="right">
					1
				</td>
				<td align="right">
					${zxdk.fk_xn1_je }
				</td>
				<td align="right">
					${zxdk.fk_xn1_sffk }
				</td>
				<td align="right">
					${zxdk.fk_xn1_tksj }
				</td>
			</tr>
			<tr >
				<td align="right">
					2
				</td>
				<td align="right">
					${zxdk.fk_xn2_je }
				</td>
				<td align="right">
					${zxdk.fk_xn2_sffk }
				</td>
				<td align="right">
					${zxdk.fk_xn2_tksj }
				</td>
			</tr>
			<tr >
				<td align="right">
					3
				</td>
				<td align="right">
					${zxdk.fk_xn3_je }
				</td>
				<td align="right">
					${zxdk.fk_xn3_sffk }
				</td>
				<td align="right">
					${zxdk.fk_xn3_tksj }
				</td>
			</tr>
			<tr >
				<td align="right">
					4
				</td>
				<td align="right">
					${zxdk.fk_xn4_je }
				</td>
				<td align="right">
					${zxdk.fk_xn4_sffk }
				</td>
				<td align="right">
					${zxdk.fk_xn4_tksj }
				</td>
			</tr>
			<logic:equal value="5" name="rs" property="xz">
				<tr >
					<td align="right">
						5
					</td>
					<td align="right">
						${zxdk.fk_xn5_je }
					</td>
					<td align="right">
						${zxdk.fk_xn5_sffk }
					</td>
					<td align="right">
						${zxdk.fk_xn5_tksj }
					</td>
				</tr>
			</logic:equal>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
		            <logic:equal value="view" name="doType">
						<button type="button" class="button2" id="buttonSave" onclick="window.close();return false;">
							关&nbsp;&nbsp;&nbsp;&nbsp;闭
						</button>
					</logic:equal>
					<logic:equal value="modi" name="doType">
						<button type="button" class="button2" id="buttonSave" onclick="saveUpdate('/xgxt/bdzcgl.do?method=lstdOne&doType=update','save_xh-save_xn-save_xq-save_je');">
							修&nbsp;&nbsp;&nbsp;&nbsp;改
						</button>
					</logic:equal>
					<logic:notEqual value="view" name="doType">
						<logic:notEqual value="modi" name="doType">
							<button type="button" class="button2" id="buttonSave" onclick="saveUpdate('/xgxt/bdzcgl.do?method=lstdOne&doType=save','save_xh-save_xn-save_xq-save_je');">
								保&nbsp;&nbsp;&nbsp;&nbsp;存
							</button>
						</logic:notEqual>
					</logic:notEqual>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
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