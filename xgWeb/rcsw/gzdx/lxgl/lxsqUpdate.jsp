<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			//提交申请
			function dataSave(url,mustFill){
				var eles = mustFill.split("-");
				for (i = 0; i < eles.length; i++) {
					if (document.getElementById(eles[i]).value == "") {
						alertInfo("必填字段未填完整！");
						return false;
					}
				}
				
				var kssj = eval($('kssj').value);
				var jssj = eval($('jssj').value);
				
				if(kssj>jssj){
					alertInfo("开始时间不能大于结束时间！");
					return false;
				}
				
				$('buttonSave').disabled = "disabled";
				document.forms[0].action = url;
				document.forms[0].submit();
			}
			//修改申请信息
			function dataUpdate(url,mustFill){
				var eles = mustFill.split("-");
				for (i = 0; i < eles.length; i++) {
					if (document.getElementById(eles[i]).value == "") {
						alertInfo("必填字段未填完整！");
						return false;
					}
				}
				
				var kssj = eval($('kssj').value);
				var jssj = eval($('jssj').value);
				
				if(kssj>jssj){
					alertInfo("开始时间不能大于结束时间！");
					return false;
				}
				
				$('buttonUpdate').disabled = "disabled";
				document.forms[0].action = url;
				document.forms[0].submit();
			}
		</script>
		
		<style type="text/css">
			talbe{
				border-collapse:collapse;
			}
			
			table th{
				width:20%
			}
			table td{
				width:30%
			}
		</style>
	</head>
	<body>
		<html:form action="/rcsw_gzdx_lxgl" method="post">
			<input type="hidden" name="url" value="/rcsw_gzdx_lxgl.do?method=lxsqUpdate"/>
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xh" />
			<input type="hidden" id="pkValue" name="pkValue" value="${rs.xh }"/>
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			<div class="tab">
			<table width="100%" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>留校信息</span>
						</th>
					</tr>
				</thead>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<th>
							<font color="red">*</font>学号
						</th>
						<td>
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this);" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',800,600);" 
								class="btn_01" id="buttonFindStu" >选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<th>
							<font color="red">*</font>学号
						</th>
						<td>
							<input type="text" id="xh" name="xh" value="${rs.xh }" readonly="readonly" />
						</td>
					</logic:equal>
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
						<logic:notEqual value="" name="rs" property="sjhm">
							<logic:notEqual value="" name="rs" property="lxdh">
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
						是否吃年夜饭
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
						<font color="red">*</font>留校开始时间
					</th>
					<td>
						<html:text property="kssj" styleId="kssj" onclick="" onkeydown="onlyBackSpace(this,event);" onblur="dateFormatChg(this);"
						onclick="return showCalendar('kssj','y-mm-dd');" style="cursor:hand" readonly="true" value="${rs.kssj}"></html:text>
					</td>
					<th>
						<font color="red">*</font>留校结束时间
					</th>
					<td>
						<html:text property="jssj" styleId="jssj" onclick="" onkeydown="onlyBackSpace(this,event);" onblur="dateFormatChg(this);"
						onclick="return showCalendar('jssj','y-mm-dd');" style="cursor:hand" readonly="true" value="${rs.jssj}"></html:text>
						
					</td>
				</tr>
				
				<tr>
					<th>
						<font color="red">*</font>留校天数
					</th>
					<td>
						<html:text property="ts" styleId="ts" maxlength="2" onblur="checkInputData(this);" value="${rs.ts}"/>天
					</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>家长联系方式</th>
					<td colspan="3">
						<html:text property="jzlxfs" styleId="jzlxfs" maxlength="150" style="width:88%" value="${rs.jzlxfs}"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						申请留校原因<br/>
						<font color="red">(限制录入400字)</font>
					</th>
					<td colspan="3">
						<html:textarea property="lxyy" style="width: 95%;word-break:break-all;"
						 onblur="chLeng(this,400);" rows='8' value="${rs.lxyy}"/>
					</td>
				</tr>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项<logic:equal value="yes" name="issh"><span class="red">申请已经审核，无法再进行修改！</span></logic:equal></div>
			          <div class="btn">
						  <logic:notEqual name="doType" value="view">
						  	<!-- 判断是否已经申请 -->
							<logic:equal value="yes" name="issq">
								<!-- 判断是否已经审核，审核则不显示 -->
								<logic:equal value="yes" name="issh">
									<button type="button" id="buttonUpdate" disabled="disabled"
									onclick="dataUpdate('/xgxt/rcsw_gzdx_lxgl.do?method=lxsqxgUpdate&opera=update','xh-ts-kssj-jssj')">
										修改
									</button>
								</logic:equal>
								<logic:notEqual value="yes" name="issh">
									<button type="button" id="buttonUpdate"
									onclick="dataUpdate('/xgxt/rcsw_gzdx_lxgl.do?method=lxsqxgUpdate&opera=update','xh-ts-kssj-jssj')">
										修改
									</button>
								</logic:notEqual>
							</logic:equal>
							<logic:notEqual value="yes" name="issq">
							<button type="button" id="buttonSave" name="提交" onclick="dataSave('/xgxt/rcsw_gzdx_lxgl.do?method=lxsqUpdate&doType=save','xh-ts-kssj-jssj')">提交</button>
							</logic:notEqual>
						  </logic:notEqual>
			          </div></td>
			      </tr>
			    </tfoot>
			</table> 
		</div>
		</html:form>
		
		<logic:present name="message">
			<input type="hidden" id="message" value="${message }"/>
			<script type="text/javascript" defer="defer">
				alertInfo($('message').value);
			</script>
		</logic:present>
	</body>
</html>
