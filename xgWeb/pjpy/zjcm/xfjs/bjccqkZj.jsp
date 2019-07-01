<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
		<script language="javascript" src="js/xgutil.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/xfjs.js'></script>
		<script type="text/javascript">
			/*
			 隐藏年级、<bean:message key="lable.xsgzyxpzxy" />、专业、班级列表 
			*/
			function hideItems(){
				var items = document.getElementById("items");
				
				DWRUtil.setValue('save_bjdm',$('bj').value);
				DWRUtil.setValue('bjmc',DWRUtil.getText('bj'));
				
				
				//获取班级人数
				xfjs.selectBjrs($('bjdm').value,function(data){
					setVal('ydrs',data);
					computeQqrs();
				});
				
				hiddenMessage(true,true);
				
			}
			
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
			
			function bjccqkZj(){
				//判断必填字段是否填写完整
				var notNullArray = ['xn','xq','ccrq','save_bjdm','jclxdm','ydrs','sdrs','fdyclsj'];
				var flag = filedNotNull(notNullArray);
				if(!flag) {alert("请将带\"*\"号的项目输入完整！"); return false};
				//实到人数不能大于应到人数
				var sdrs = val('sdrs') == null || val('sdrs')=="" ? "0" : val('sdrs');
				var ydrs = val('ydrs') == null || val('ydrs')=="" ? "0" : val('ydrs');
				if(toInt(ydrs)-toInt(sdrs) <0){
					alert("实到人数大于应到人数！");
					return false;
				}
				//判断记录是否存在
				var value = val('xn')+val('xq')+val('ccrq')+val('bjdm')+val('jclxdm')+val('ccyhlx');			
				xfjs.bjccqkExists(value,function(data){
					if(data){
						alert("您添加的记录已经存在！");
						return false;
					}else{
						$("buttonSave").disabled=true;
						refreshForm('/xgxt/pjpyxfjs.do?method=bjccqkSave');	
					}
				});
			}
			
			function showSelect(){
				viewTempDiv('请选择班级','items',300,220);
				
				$('select_nj').id = "nj";
				$('select_xy').id = "xy";
				$('select_zy').id = "zy";
				$('select_bj').id = "bj";
			}
			
		</script>
	</head>
	<body>
		
		<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="title" /></a>
				</p>
		</div>
		
		<html:form action="/pjpyxfjs" method="post">
			<input type="hidden" name="njV" id="njV"/>
			<input type="hidden" name="xyV" id="xyV"/>
			<input type="hidden" name="zyV" id="zyV"/>
			<input type="hidden" name="bjV" id="bjV"/>
			<input type="hidden" name="ccyhlx" id="ccyhlx" value="${ccyhlx}"/>

			<div id="items" name="items" class="open_win01" style="display:none;">
				<table  class='formlist'>
					<tbody>
					<tr>
						<th>
							年级
						</th>
						<td>
							<html:select property="nj" onchange="initZyList();initBjList();"
								styleId="select_nj" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td>
							<html:select property="xydm" onchange="initZyList();initBjList();"
								styleId="select_xy" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							专业
						</th>
						<td>
							<html:select property="zydm" onchange="initBjList();" styleId="select_zy" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							班级
						</th>
						<td>
							<html:select property="bjdm" styleId="select_bj" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
					</tbody>
					<tfoot>
					<tr>
						<td colspan="2" >
							<div class='btn'>
								<button type="button" class="button2" onclick="hideItems();">
									确 定
								</button>
							</div>
						</td>
					</tr>
					</tfoot>
				</table>
			</div>
		
		
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
									<button type="button" class="button2"
										onclick="bjccqkZj()"
										id="buttonSave"
										>
										保 存
									</button>
									<button type="button" class="button2"
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
					<html:text property="xn" readonly="true" styleId="xn"></html:text>
				</td>
				<th>
					<font color="red">*</font>抽查日期
				</th>
				<td align="left">
					<html:text property="ccrq" onclick="return showCalendar('ccrq','y-mm-dd');" styleId="ccrq" readonly="true"></html:text>
				</td>
			</tr>
			<tr>
				<th>
					<font color="red">*</font>学期
				</th>
				<td align="left">
					<html:select property="xq" disabled="true" styleId="tempxq">
						<html:option value=""></html:option>
						<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
					</html:select>
					<html:hidden property="xq" styleId="xq"/>
				</td>
				<th>
					<font color="red">*</font>抽查类型
				</th>
				<td align="left">
					<html:select property="jclxdm" styleId="jclxdm">
						<html:option value=""></html:option>
						<html:options collection="jclxList" property="jclxdm" labelProperty="jclxmc"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<th>
					<font color="red">*</font>班级
				</th>
				<td align="left">
					<html:text property="bjmc" onclick="showSelect();" readonly="true" styleId="bjmc"></html:text>
					<input type="hidden" name="save_bjdm" styleId="save_bjdm"/>
				</td>
				<th>
					<font color="red">*</font>应到人数
				</th>
				<td align="left">
					<html:text property="ydrs" styleId="ydrs" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'');computeQqrs()"></html:text>
				</td>
			</tr>
			<tr>
				<th>
					<font color="red">*</font>实到人数
				</th>
				<td align="left">
					<html:text property="sdrs" styleId="sdrs" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'');computeQqrs()"></html:text>
				</td>
				<th>
					缺勤人数
				</th>
				<td align="left">
					<html:text property="qqrs" readonly="true" styleId="qqrs"></html:text>
				</td>
			</tr>
			<tr>
				<th>
					除缺勤外的违纪人数<br/><div align="center">(如:吃饭,睡觉等...)</div>
				</th>
				<td align="left">
					<html:text property="wjrs" styleId="wjrs" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'');"></html:text>
				</td>
				<th>
					<font color="red">*</font>处理时间
				</th>
				<td align="left">
					<html:text property="fdyclsj" readonly="true" styleId="fdyclsj" onclick="return showCalendar('fdyclsj','y-mm-dd');"></html:text>
				</td>
			</tr>
			<tr>
				<th>
					备注
				</th>
				<td align="left" colspan="3">
					<html:textarea property="bz" styleId="bz" cols="50" rows="4" style="width:100%" onblur="chLeng(this,600)"></html:textarea>
				</td>
			</tr>
			</tbody>
		</table>
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
