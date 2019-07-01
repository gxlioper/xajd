<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript">
			function dataSave(url,mustFill){
				var eles = mustFill.split("-");
				for (i = 0; i < eles.length; i++) {
					if (document.getElementById(eles[i]).value == "") {
						alertInfo("必填字段未填完整！");
						return false;
					}
				}
				$('buttonSave').disabled = "disabled";
				document.forms[0].action = url;
				document.forms[0].submit();
			}
			
		</script>
		
	</head>
	<body>
		<html:form action="/zjjj_rcsw_qjgl" method="post">
			<input type="hidden" name="pkValue" value="${param.pkValue }"/>
			<input type="hidden" name="doType" value="${doType}"/>
				<div class="tab">
					<table width="100%" class="formlist">
						<thead>
							<tr>
								<th colspan="4"><a>学生请假信息</a></th>
							</tr>
						</thead>
						<tr>
							<th>
								<font color="red">*</font>学号
							</th>
							<td>
								<input type="text" id="xh" name="xh" value="${rs.xh }" readonly="readonly" />
							</td>

							<th>
								姓名
							</th>
							<td>
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>
								学年
							</th>
							<td>
								<input type="hidden" name="xn" value="${xn }" />
								${rs.xn }
							</td>
							
							<th>
								学期
							</th>
							<td>
								<input type="hidden" name="xq" value="${xq }" />
								${xqmc }
							</td>
						</tr>
						<tr>
							<th>
								院系
							</th>
							<td>
								${rs.xymc }
							</td>
							<th>
								专业
							</th>
							<td>
								${rs.zymc }
							</td>
							
						</tr>
						<tr>
							<th>
								班级
							</th>
							<td>
								${rs.bjmc }
							</td>
							<th>
								家庭地址
							</th>
							<td>
								${rs.jtdz }
							</td>
						</tr>
						<tr>
							<th>
								联系方式
							</th>
							<td>
								${rs.sjhm }
								<logic:notEqual value="" name="rs" property="lxdh">
									<logic:notEqual value="" name="rs" property="sjhm">
									/
									</logic:notEqual>
								</logic:notEqual>
								${rs.lxdh }
							</td>
							<th>家长联系方式</th>
							<td>
								<html:text property="jzdh" maxlength="20" value="${rs.jzdh}"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								请假开始时间
							</th>
							<td>
								<html:text property="qjkssj" styleId="qjkssj" onclick="" onkeydown="onlyBackSpace(this,event);" onblur="dateFormatChg(this)"
								onclick="return showCalendar('qjkssj','y-mm-dd');" style="cursor:hand " value="${rs.qjkssj}"></html:text>
							</td>
							<th>
								请假结束时间
							</th>
							<td>
								<html:text property="qjjssj" styleId="qjjssj" onclick="" onkeydown="onlyBackSpace(this,event);" onblur="dateFormatChg(this)"
								onclick="return showCalendar('qjjssj','y-mm-dd');" style="cursor:hand " value="${rs.qjjssj}"></html:text>
							</td>
						</tr>
						
						<tr>
							<th>
								<font color="red">*</font>请假天数
							</th>
							<td>
								<html:text property="qjts" styleId="qjts" maxlength="2" value="${rs.qjts}" onblur="checkInputData(this);" />天
							</td>
							<th>
								请假期间是否住校
							</th>
							<td>
								<html:select property="sfzx" value="${rs.sfzx }">
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								请假事由<br/>
								<font color="red">(限制录入200字)</font>
							</th>
							<td colspan="3">
								<html:textarea property="qjsy" style="width:99%" value="${rs.qjsy}" style="width: 95%;word-break:break-all;"
								 onblur="chLeng(this,200);" rows='5' />
							</td>
						</tr>
						<tr>
							<th>
								备注<br/>
								<font color="red">(限制录入100字)</font>
							</th>
							<td colspan="3">
								<html:textarea property="bz" style="width:99%"  onblur="chLeng(this,100);" value="${rs.bz}" style="width: 95%;word-break:break-all;"
									rows='5' />
							</td>
						</tr>
						
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
					          <div class="btn">
								  <button type="button" id="buttonSave"
									onclick="dataSave('/xgxt/cdty_rcsw_qjgl.do?method=qjxgUpdate&opera=update','qjkssj-qjjssj-qjts')">
									保存
								</button>
								 <button type="button" id="buttonClose"
									onclick="window.close();return false;">
									关闭
								</button>
					          </div></td>
					      </tr>
					   </tfoot>
				</table> 
			</div>
		</html:form>
		
		<logic:present name="message">
			<input type="hidden" id="message" value="${message }"/>
			<script type="text/javascript">
				alertInfo($('message').value, function(){
					Close();
					if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				   	window.dialogArguments.document.getElementById('search_go').click(); 
					}
				});
				
			</script>
		</logic:present>
	</body>
</html>
