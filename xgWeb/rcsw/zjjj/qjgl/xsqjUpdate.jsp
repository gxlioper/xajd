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
			function dataSave(url,mustFill){
				var eles = mustFill.split("-");
				for (i = 0; i < eles.length; i++) {
					if (document.getElementById(eles[i]).value == "") {
						alertInfo("必填字段未填完整！");
						return false;
					}
				}
				
				var kssj = eval($('qjkssj').value);
				var jssj = eval($('qjjssj').value);
				
				if(kssj>jssj){
					alertInfo("请假开始时间不能大于结束时间！");
					return false;
				}
				
				$('buttonSave').disabled = "disabled";
				document.forms[0].action = url;
				document.forms[0].submit();
			}
			
		</script>
		
		<style type="text/css">
			talbe{
				border-collapse:collapse;
			}
		</style>
	</head>
	<body>
		<html:form action="/zjjj_rcsw_qjgl" method="post">
			<input type="hidden" name="url" value="/zjjj_rcsw_qjgl.do?method=xsqjUpdate"/>
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xh" />
			
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
							<span>请假信息</span>
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
						学年
					</th>
					<td>
						<input type="hidden" name="xn" value="${xn }" />
						${xn }
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
					<th>寝室号</th>
					<td>${rs.qsh }</td>
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
					<th><font color="red">*</font>家长联系方式</th>
					<td>
						<html:text property="jzdh" maxlength="20" styleId="jzdh"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>请假开始时间
					</th>
					<td>
						<html:text property="qjkssj" styleId="qjkssj" onclick="" onkeydown="onlyBackSpace(this,event);" onblur="dateFormatChg(this)"
						onclick="return showCalendar('qjkssj','y-mm-dd');" style="cursor:hand "></html:text>
					</td>
					<th>
						<font color="red">*</font>请假结束时间
					</th>
					<td>
						<html:text property="qjjssj" styleId="qjjssj" onclick="" onkeydown="onlyBackSpace(this,event);" onblur="dateFormatChg(this)"
						onclick="return showCalendar('qjjssj','y-mm-dd');" style="cursor:hand "></html:text>
					</td>
				</tr>
				
				<tr>
					<th>
						<font color="red">*</font>请假天数
					</th>
					<td>
						<html:text property="qjts" styleId="qjts" maxlength="2" onblur="checkInputData(this);" />天
					</td>
					<th>
						请假期间是否住校
					</th>
					<td>
						<html:select property="sfzx">
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						请假事由及去向<br/>
						<font color="red">(限制录入200字)</font>
					</th>
					<td colspan="3">
<%--						<font color="red">(以下请假事由中必须包含请假课程总节数）</font>--%>
						<font color="red">(如一天内，请详细注明是第几节课和相对应的课程名称）</font>
						<html:textarea property="qjsy" style="width: 95%;word-break:break-all;"
						 onblur="chLeng(this,200);" rows='5' />
					</td>
				</tr>
				<tr>
					<th>
						备注<br/>
						<font color="red">(限制录入100字)</font>
					</th>
					<td colspan="3"><html:textarea property="bz" style="width: 95%;word-break:break-all;"  onblur="chLeng(this,100);" 
							rows='5' />
					</td>
				</tr>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
						  <logic:notEqual name="doType" value="view">
							<button type="button" id="buttonSave" name="提交" onclick="dataSave('/xgxt/zjjj_rcsw_qjgl.do?method=xsqjUpdate&doType=save','xh-qjts-qjkssj-qjjssj-jzdh')">提交</button>
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
