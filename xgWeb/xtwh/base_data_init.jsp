<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/sharedFunction.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getBaseData.js"></script>
		<script type="text/javascript"
			src="/xgxt/js/webServiceFunction/wsFunction.js"></script>
		<script type="text/javascript" src="/xgxt/js/bjlhdx/bjlhdxBaseData.js"></script>
		<script type="text/javascript">
		function sjdr(){
			var realTable = document.getElementById('realTable').value;
			if(realTable==""){
				alert('请选择您要导入的表!');
				return false;
			}else{
				impAndChkData();
			}
		}
		function webSerSjdr(){
			var realTable = document.getElementById('glsjTable').value;
				if(realTable==""){
					alert('请选择您要导入的表!');
					return false;
				}else{
					impAndChkData();
				}
		}
		function xyChange(){
		    var msg = "该操作会根据xydzb里的数据变更所有的"+jQuery("#xbmc").val()+"代码相关的表\n确认要继续吗?";
			if (confirm(msg)) {
				refreshForm('/xgxt/base_data_init.do?type=xyChange');
			}
		}
		function zyChange(){
		    var msg = "该操作会根据zydzb里的数据变更所有的专业代码相关的表\n确认要继续吗?";
			if (confirm(msg)) {
				refreshForm('/xgxt/base_data_init.do?type=zyChange');
			}
		}
		function bjChange(){
		    var msg = "该操作会根据bjdzb里的数据变更所有的班级代码相关的表\n确认要继续吗?";
			if (confirm(msg)) {
				refreshForm('/xgxt/base_data_init.do?type=bjChange');
			}
		}
		</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>系统维护 - 系统初始化 - 基础数据初始化</a>
			</p>
		</div>

		<html:form action="/chgPass" method="post">
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>

			<logic:notEqual value="zfjw" name="webSerTb">
				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>基础数据初始化(学工维护数据)</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button" name="提交" onclick="sjdr()">
											数据导入
										</button>
										<button type="button" name="Submit2" onclick="xyChange()">
											<bean:message key="lable.xb" />代码变更
										</button>
										<button type="button" name="Submit2" onclick="zyChange()">
											专业代码变更
										</button>
										<button type="button" name="Submit2" onclick="bjChange()">
											班级代码变更
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th width="20%">
									请选择基础数据表
								</th>
								<td width="80%">
									<html:select property="realTable" style="width:180px"
										styleId="realTable">
										<html:option value=""></html:option>
										<html:options collection="jbsjTableList" property="tablename"
											labelProperty="tablecomment" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</logic:notEqual>
			<%--	以下是北京联合的数据同步功能		--%>
			<logic:equal value="true" name="webSerTb">
				<div class="tab" id="displayCols" name="displayCols" style="display:none">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>基础表数据同步</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button" name="提交" onclick="checkInputTime()">
											保存设置
										</button>
										<button type="button" name="Submit2" onclick="synchronizeData('glsjTable')">
											手动同步
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th width="20%">
									请选择基础数据表
								</th>
								<td width="80%">
									<html:select property="glsjTable" style="width:180px"
										onchange="selTable(this)" styleId="glsjTable">
										<html:option value=""></html:option>
										<html:options collection="glsjTableList" property="tablename"
											labelProperty="tablecomment" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>同步开始时间</th>
								<td>
									<input type="text" id="yearMonthDay" name="yearMonthDay"
													onblur="dateFormatChg(this)" style="cursor:hand;"
													onclick="return showCalendar('yearMonthDay','y-mm-dd');"
													value=""/>
									<input type="text" name="hh" id="hh" style="width:15%"
													maxlength="2" value="00"/>
												:
									<input type="text" name="mi" id="mi" style="width:15%"
													maxlength="2" value="00"/>
												:
									<input type="text" name="ss" id="ss" style="width:15%"
													maxlength="2" value="00"/>
								</td>
							</tr>
							<tr>
								<th>同步间隔时间</th>
								<td>
									<input type="text" name="day" id="day" style="width:10%"
													maxlength="3" value="000"/>
												天
												<input type="text" name="hour" id="hour" style="width:10%"
													maxlength="3" value="000"/>
												小时
												<input type="text" name="minute" id="minute"
													style="width:10%" maxlength="3" value="000"/>
												分钟
								</td>
							</tr>
							<tr>
								<th></th>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
			</logic:equal>
			<logic:equal value="zfjw" name="webSerTb">
				<input type="hidden" name="webSerTb" id="webSerTb"
					value="<bean:write name = "webSerTb"/>" />

				<div class="tab" id="displayCols" name="displayCols" >
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>基础表数据同步(教务webService取数据)</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button" name="提交" onclick="synchronizeData('glsjTable')">
											数据导入
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th width="20%">
									请选择基础数据表
								</th>
								<td width="80%">
									<html:select property="glsjTable" style="width:180px"
										styleId="glsjTable">
										<html:option value=""></html:option>
										<html:option value="xsxx">学生信息</html:option>
										<html:option value="xsqtxx">学生其他信息</html:option>
										<html:option value="cjxx">学生成绩信息</html:option>
										<html:option value="xyxx">
											<bean:message key="lable.xsgzyxpzxy" />信息</html:option>
										<html:option value="bjxx">班级信息</html:option>
										<html:option value="zyxx">专业信息</html:option>
										<html:option value="xjyd">学籍异动信息</html:option>
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</logic:equal>
			<logic:present name="doresult">
				<logic:equal name="doresult" value="true">
					<script type="text/javascript">
      		alert("保存成功！");
      	</script>
				</logic:equal>
				<logic:equal name="doresult" value="false">
					<script type="text/javascript">
      		alert("保存失败！");
      	</script>
				</logic:equal>
			</logic:present>
			<logic:present name="update">
				<logic:equal name="update" value="true">
					<script type="text/javascript">
      		alert("更新成功！");
      	</script>
				</logic:equal>
				<logic:equal name="update" value="false">
					<script type="text/javascript">
      		alert("更新失败！");
      	</script>
				</logic:equal>
			</logic:present>
		</html:form>
		
		<div id="showMes" name="showMes" style="display:none;">
			正在执行同步
		</div>
	</body>
</html>
