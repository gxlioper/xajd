<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/qgzxgzkh.js"></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script>
		function save(){
			if(filedNotNull('xh-xmdm-nd-yf','-')){
				//判断记录是否存在
				var flag = false;
				for(var i=1; i<32; i++){
					var zgzsj = $('zgzsj'+i).value;
					if(zgzsj != null && zgzsj != ""){
						flag = true;
						if(flag){
							break;
						}
					}
				}
				if(flag){
					refreshForm('qgzxkh.do?method=addXsgzjlBatch');
				}else {
					alert ('请填写工作总时间！');
				}
			} else {
				alert ('请将带\*号的项目填写完整！');
				return false;
			}
		}
		
		function changeRecord(rq){
			rq = parseInt(rq);
			if(rq == '2'){
				ele('tr29').style.display='none';
				ele('tr30').style.display='none';
				ele('tr31').style.display='none';
			}else if(rq == '4' || rq =='6' || rq=='9' || rq=='11'){
				ele('tr29').style.display='';
				ele('tr30').style.display='';
				ele('tr31').style.display='none';
			}else{
				ele('tr29').style.display='';
				ele('tr30').style.display='';
				ele('tr31').style.display='';
			}
		}
	</script>
</head>
	<body>
		<html:form action="/qgzxkh.do">
			<input type="hidden" name="url" id="url" value="/qgzxkh.do?method=xsgzjlAddBatch"/>
			<input type="hidden" value="xh-xm-xb-nj-xymc-zymc-bjmc" id="getStuInfo" name="getStuInfo" />
			<input type="hidden" id="tableName" name="tableName" value="view_qgzx_xsxx"/>
			<input type="hidden" id="zd" name="zd" value="dlm"/>
			<input type="hidden" id="va" name="va" value="${userName }"/>
			<input type="hidden" id="lx" name="lx" value="勤工助学学生"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>勤工助学 - 考核 - 学生工作记录 - 添加信息</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生工作记录信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th><span class="red">*</span>学号</th>
						<td>
							<!-- 广州大学 -->
							<logic:equal name="xxdm" value="11078">
								<html:text name="rs" property="xh" styleId="xh" readonly="true" />
								<button type="button" onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
									选择
								</button>
							</logic:equal>
							<!-- 非广州大学 -->
							<logic:notEqual name="xxdm" value="11078">
								<html:text name="rs" property="xh" styleId="xh" onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
								<button type="button" class="btn_01" onclick="showTopWin('/xgxt/stu_info.do',750,550);" id="buttonFindStu">
									选择
								</button>
							</logic:notEqual>
						</td>
						<th>姓名</th>
						<td>
							<bean:write name="rs" property="xm" />
						</td>						
					</tr>
					<tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<bean:write name="rs" property="xymc" />
						</td>
						<th>性别</th>
						<td>
							<bean:write name="rs" property="xb" />
						</td>												
					</tr>
					<tr>
						<th>专业</th>
						<td>
							<bean:write name="rs" property="zymc" />
						</td>						
						<th>年级</th>
						<td>
							<bean:write name="rs" property="nj" />
						</td>						
					</tr>
					<tr>
						<th>班级</th>
						<td>
							<bean:write name="rs" property="bjmc" />
						</td>
						<th><span class="red">*</span>岗位</th>
						<td>
							<html:select property="xmdm" styleId="xmdm" style="width:180px">
								<html:options collection="gwList" property="xmdm"
									labelProperty="xmmc" />
							</html:select>
						</td>					
					</tr>
					<tr>						
						<th><span class="red">*</span>年度</th>
						<td>
							<html:select property="nd" styleId="nd" style="width:180px">
								<html:options collection="ndList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>	
						<th><span class="red">*</span>月份</th>
						<td>
							<html:select property="yf" styleId="yf" onchange="changeRecord(this.value)" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="yfList" property="yf" labelProperty="yf" />
							</html:select>
						</td>				
					</tr>
					</tbody>
				</table>
				<table class="formlist" width="100%">
					<thead>
					<tr><th colspan="5"><font color="red"><b>提示：不填写总工作时间将不保存记录</b></font></th></tr>
					<tr>
						<th>日期</th>
						<th>工作开始时间</th>
						<th>工作结束时间</th>
						<th><span class="red">*</span>总工作时间</th>
						<th>工作内容</th>
					</tr>
					</thead>
					<tbody>
					<logic:iterate id="rs" name="rqList">
					<tr id="tr${rs.rq}">
						<td>
							${rs.rq}
						</td>
						<td>
							<input type="text" name="gzkssj${rs.rq}" id="gzkssj${rs.rq}" maxlength="10" onkeyup="value=value.replace(/[^\d|:|：]/g,'')"/>
						</td>
						<td>
							<input type="text" name="gzjssj${rs.rq}" id="gzjssj${rs.rq}" maxlength="10" onkeyup="value=value.replace(/[^\d|:|：]/g,'')"/>
						</td>
						<td>
							<input type="text" name="zgzsj${rs.rq}" id="zgzsj${rs.rq}" maxlength="4" onkeyup="value=value.replace(/[^\d|.]/g,'')"/>
						</td>
						<td>
							<input type="text" name="gznr${rs.rq}" id="gznr${rs.rq}" maxlength="300"/>
						</td>
					</tr>
					</logic:iterate>
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="5"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
				            <button type="button"
								onclick="save();return false;"
								style="width:80px">
								保 存
							</button>
							<button type="button" onclick="window.close();return false;"
								style="width:80px">
								关 闭
							</button>
				          </div>
				        </td>
				      </tr>
				    </tfoot>
				</table>
				</div>

			 <logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
					alert("操作成功！");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();		
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
					alert("操作失败！");
					Close();
				</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
