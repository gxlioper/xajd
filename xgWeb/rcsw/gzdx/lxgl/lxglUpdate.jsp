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
		<html:form action="/rcsw_gzdx_lxgl" method="post">
			<input type="hidden" name="pkValue" value="${param.pkValue }"/>
			<input type="hidden" name="doType" value="${doType}"/>
				<div class="tab">
					<table width="100%" class="formlist">
						<thead>
							<tr>
								<th colspan="4"><a>学生留校信息</a></th>
							</tr>
						</thead>
						<tr>
							<th>
								学号
							</th>
							<td>
								${rs.xh }
								<input type="hidden" id="xh" name="xh" value="${rs.xh }" />
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
								联系电话
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
						</tr>
						<tr>
							<th>原寝室号</th>
							<td>
								<html:text property="qsh" styleId="qsh" maxlength="25" value="${rs.qsh }"></html:text>
							</td>
							<th>
								是否年夜饭
							</th>
							<td>
								<html:select property="sfnyf" value="${rs.sfnyf }">
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								留校开始时间
							</th>
							<td>
								${rs.kssj }
							</td>
							<th>
								留校结束时间
							</th>
							<td>
								${rs.jssj }
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>天数
							</th>
							<td>
								<html:text property="ts" styleId="ts" maxlength="2" value="${rs.ts}" onblur="checkInputData(this);" />天
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>家长联系方式</th>
							<td colspan="3">
								<html:text property="jzlxfs" maxlength="100" value="${rs.jzlxfs}"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								留校原因<br/>
								<font color="red">(限制录入400字)</font>
							</th>
							<td colspan="3">
								<html:textarea property="lxyy" style="width:99%" value="${rs.lxyy}" style="width: 95%;word-break:break-all;"
								 onblur="chLeng(this,400);" rows='5' />
							</td>
						</tr>
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
					          <div class="btn">
								  <button type="button" id="buttonSave"
									onclick="dataSave('/xgxt/rcsw_gzdx_lxgl.do?method=lxglUpdate&opera=update','ts')">
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
