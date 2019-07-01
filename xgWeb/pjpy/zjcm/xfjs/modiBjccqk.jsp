<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/xfjs.js'></script>
	<script type="text/javascript">
		/**
		*计算缺勤人数
		*/
		function computeQqrs(){
			var ydrs = val("ydrs");
			var sdrs = val("sdrs");
			
			ydrs = ydrs == "" ? "0" : ydrs;
			sdrs = sdrs == "" ? "0" : sdrs;
			
			setVal("ydrs",ydrs);
			setVal("sdrs",sdrs);
			setVal("qqrs",toInt(ydrs)-toInt(sdrs));			
		}
		
		function bjccqkXg(){
			//判断必填字段是否填写完整
			var notNullArray = ['xn','xq','ccrq','bjdm','jclxdm','ydrs','sdrs','fdyclsj'];
			var flag = filedNotNull(notNullArray);
			if(!flag) {alert("请将带\"*\"号的项目输入完整！"); return false};
			//实到人数不能大于应到人数
			var sdrs = val('sdrs') == null || val('sdrs')=="" ? "0" : val('sdrs');
			var ydrs = val('ydrs') == null || val('ydrs')=="" ? "0" : val('ydrs');
			if(toInt(ydrs)-toInt(sdrs) <0){
				alert("实到人数大于应到人数！");
				return false;
			}
			//保存数据
			$("buttonSave").disabled=true;
			refreshForm('/xgxt/pjpyxfjs.do?method=modiBjccqk&type=save');	
		}
	</script>
	</head>
	<body>
		
		<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>评奖评优 - 学风建设 - 学生抽查情况 - 班级抽查情况修改</a>
				</p>
		</div>
		
		
		<html:form action="/pjpyxfjs" method="post">
			<input type="hidden" name="njV" id="njV"/>
			<input type="hidden" name="xyV" id="xyV"/>
			<input type="hidden" name="zyV" id="zyV"/>
			<input type="hidden" name="bjV" id="bjV"/>
			<input type="hidden" name="ccyhlx" id="ccyhlx" value="${rs.ccyhlx}"/>
			<input type="hidden" name="pk" id="pk" value="${rs.pk}"/>
			
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>班级学风抽查</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" 
										onclick="bjccqkXg()"
										id="buttonSave"
										>
										保 存
									</button>
									<button type="button" 
										onclick="window.close();return false;"
										>
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				<tbody>
				<tr>
					<th>
						<font color="red">*</font>学年
					</th>
					<td align="left">
						<html:text property="xn" name="rs" readonly="true"></html:text>
					</td>
					<th>
						<font color="red">*</font>抽查日期
					</th>
					<td align="left">
						<html:text property="ccrq" name="rs" styleId="ccrq" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>学期
					</th>
					<td align="left">
						<html:text property="xqmc" name="rs" readonly="true"></html:text>
						<html:hidden property="xq" name="rs" styleId="xq"/>
					</td>
					<th>
						<font color="red">*</font>抽查类型
					</th>
					<td align="left">
						<html:text property="jclxmc" name="rs" readonly="true"></html:text>
						<html:hidden property="jclxdm" name="rs"></html:hidden>
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>班级
					</th>
					<td align="left">
						<html:text property="bjmc" name="rs" readonly="true"></html:text>
						<html:hidden property="bjdm" name="rs" styleId="bjdm"/>
					</td>
					<th>
						<font color="red">*</font>应到人数
					</th>
					<td align="left">
						<html:text property="ydrs" name="rs" styleId="ydrs" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'');computeQqrs()"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>实到人数
					</th>
					<td align="left">
						<html:text property="sdrs" name="rs" styleId="sdrs" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'');computeQqrs()"></html:text>
					</td>
					<th>
						缺勤人数
					</th>
					<td align="left">
						<html:text property="qqrs" name="rs" readonly="true" styleId="qqrs"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						除缺勤外的违纪人数<br/><div align="center">(如:吃饭,睡觉等...)</div>
					</th>
					<td align="left">
						<html:text property="wjrs" name="rs" styleId="wjrs" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'');"></html:text>
					</td>
					<th>
						<font color="red">*</font>处理时间
					</th>
					<td align="left">
						<html:text property="fdyclsj" name="rs" readonly="true" styleId="fdyclsj" onclick="return showCalendar('fdyclsj','y-mm-dd');"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						备注
					</th>
					<td align="left" colspan="3" style="word-break:break-all;">
						<html:textarea property="bz" name="rs" cols="50" rows="4" style="width:100%" onblur="chLeng(this,600)"></html:textarea>
					</td>
				</tr>
				</tbody>
			</table>
		</div>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
				window.close();
				if(window.dialogArguments){
					window.dialogArguments.document.getElementById('search_go').click();
				}	
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
			</script>
		</logic:equal>		
		</html:form>
	</body>
</html>
