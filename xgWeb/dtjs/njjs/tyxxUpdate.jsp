<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jxgl/jxglFunction.js"></script>
	<script type="text/javascript">	
		function printBb(){
			var xh = $("xh").value;
			var xn = $("xn").value;
			var pkValue = xh+xn;
			window.open('gzdxJxgl.do?method=mhxPrint&pkValue='+pkValue);
		}
		
		function getInfo(cod,xh){
			var doType = $("doType").value;
			if (cod == 13 && xh != "") {
				var url = "/xgxt/njjsDtjs.do?method=tyxxUpdate";
				url+="&xh="+xh;
				url+="&doType="+doType;
				refreshForm(url);
			}
		}
	</script>
	</head>
	<body onload="">
		<html:form action="/njjsDtjs">
		<!-- 隐藏域 -->
		<input type="hidden" id="url" name="url" value="/xgxt/njjsDtjs.do?method=tyxxUpdate"/>
		<input type="hidden" id="tableName" name="tableName" value="view_xsjbxx"/>
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xymc-zymc-bjmc-nj"/>
		<input type="hidden" id="lx" name="lx" value="学生"/>
		<%@ include file="/jxgl/hiddenValue.jsp"%>
		<!-- 隐藏域 end-->
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="title" /></a>
				</p>
			</div>
			<div class="tab">
				<table class="formlist" border="0" id="rsTable" align="center"
					style="width: 100%">
				<thead>
					<tr>
						<th colspan="4"><span>学生信息</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							<font color="red">*</font>学号
						</th>
						<td>
							<logic:equal name="doType" value="add">
								<html:text name='rs' property="xh" styleId="xh" onkeydown="getInfo(event.keyCode,this.value)"/>
								<button type="button" onclick="sendXx();return false;"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</logic:equal>
							<logic:notEqual name="doType" value="add">
								<html:text name='rs' property="xh" styleId="xh" readonly="true"/>
							</logic:notEqual>
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
							性别
						</th>
						<td>
							${rs.xb }
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
							年级
						</th>
						<td>
							${rs.nj }
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
							出生年月
						</th>
						<td>
							${rs.csrq }
						</td>
						<th>
							联系电话
						</th>
						<td>
							${rs.lxdh }
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
							籍贯
						</th>
						<td colspan="3">
							${rs.jg }
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="4"><span>入团信息</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							入团时间
						</th>
						<td>
							<html:text name="rs" property="rtsj" styleId="rtsj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('rtsj','y-mm-dd');"/>		
						</td>
						<th>
							入党时间
						</th>
						<td>
							<html:text name="rs" property="rdsj" styleId="rdsj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('rdsj','y-mm-dd');"/>	
						</td>
					</tr>
					<tr>
						<th>
							入团地点
						</th>
						<td>
							<html:text name="rs" property="rtdd" maxlength="50"/>
						</td>
						<th>
							团员证编号
						</th>
						<td>
							<html:text name="rs" property="tyzbh" 
								onkeypress="return onlyNum(this,20)" 
								style="ime-mode:disabled"/>
						</td>
					</tr>
					<tr>
						<th>
							有无入团志愿书
						</th>
						<td>
							<html:select name="rs" property="ywrtzys" style="" styleId="ywrtzys">
								<html:option value=""></html:option>
								<html:options collection="ywlxList" property="en" labelProperty="cn" />
							</html:select>
						</td>
						<th>
							全日制教育学历
						</th>
						<td>
							<html:select name="rs" property="xl" style="" styleId="xl">
								<html:option value=""></html:option>
								<html:options collection="xlList" property="en" labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr style="height: 23px">
						<th>
							备注
							<font color="red">(限500字)</font>
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="bz" rows="5" styleId="bz"
								onblur="chLeng(this,500)" style="width: 90%"/>
						</td>
					</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
						  <logic:notEqual name="doType" value="view">
							<button type="button" name="提交" id="buttonSave" onclick="saveUpdate('/xgxt/njjsDtjs.do?method=tyxxUpdate&doType=save','xh');">保 存</button>
						  </logic:notEqual>
						  <button type="button" name="关闭" id="buttonClose"  onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		
			<div id="tmpdiv1"></div>
			<logic:present name="message">
				<script>
					if($("message")	&& $("message").value != ""){
						alert($("message").value);
						dialogArgumentsQueryChick();
						window.close();
					}	
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
