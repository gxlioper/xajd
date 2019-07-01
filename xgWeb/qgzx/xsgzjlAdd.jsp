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
			var pkValue = val('xh') + val('xmdm') + val('nd') + val('yf') + val('rq');
			if(filedNotNull('xh-xmdm-nd-yf-rq-zgzsj','-')){
				//判断记录是否存在
				qgzxgzkh.checkExists('qgzx_xsgzjlb',"xh||gwdm||'-'||gwsbsj||nd||yf||rq",pkValue,function(data){
					if(data == true){
						alert("您添加的记录已经存在！");
						return false;
					}else{
						refreshForm('qgzxkh.do?method=addXsgzjl');
					}
				});
			} else {
				alert ('请将带\*号的项目填写完整！');
				return false;
			}
		}
	</script>
</head>
	<body>
		<html:form action="/qgzxkh.do?method=xsgzjlAdd" method="post">
			<input type="hidden" name="url" id="url" value="/qgzxkh.do?method=xsgzjlAdd"/>
			<input type="hidden" value="xh-xm-xb-nj-xymc-zymc-bjmc" id="getStuInfo" name="getStuInfo" />
			<input type="hidden" id="tableName" name="tableName" value="view_qgzx_gzdx_zgxsxx"/>
			<logic:notEqual name="userType" value="xx">
				<logic:notEqual name="userType" value="admin">
					<input type="hidden" id="zd" name="zd" value="dlm"/>
					<input type="hidden" id="va" name="va" value="${userName }"/>	
				</logic:notEqual>
			</logic:notEqual>
			<input type="hidden" id="lx" name="lx" value="勤工助学学生"/>
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>勤工助学 - 考核 - 学生工作记录 - 添加信息</a>
				</p>
			</div>
			<div class="tab">
		  	<table width="100%" border="0" class="formlist">
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
								<button type="button" class="btn_01" onclick="showTopWin('/xgxt/stu_info.do',750,550);return false;" id="buttonFindStu">
									选择
								</button>
							</logic:notEqual>
						</td>
						<th><span class="red">*</span>岗位</th>
						<td>
							<html:select property="xmdm" styleId="xmdm">
								<html:options collection="gwList" property="xmdm"
									labelProperty="xmmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>姓名</th>
						<td>
							<bean:write name="rs" property="xm" />
						</td>
						<th><span class="red">*</span>年度</th>
						<td>
							<html:select property="nd" styleId="nd">
								<html:options collection="ndList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>						
					</tr>
					<tr>
						<th>性别</th>
						<td>
							<bean:write name="rs" property="xb" />
						</td>
						<th><span class="red">*</span>月份</th>
						<td>
							<html:select property="yf" styleId="yf">
								<html:option value=""></html:option>
								<html:options collection="yfList" property="yf" labelProperty="yf" />
							</html:select>
						</td>						
					</tr>
					<tr>
						<th>年级</th>
						<td>
							<bean:write name="rs" property="nj" />
						</td>
						<th><span class="red">*</span>日期</th>
						<td> 
							<html:select property="rq" styleId="rq">
								<html:option value=""></html:option>
								<html:options collection="rqList" property="rq" labelProperty="rq" />
							</html:select>
						</td>						
					</tr>
					<tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<bean:write name="rs" property="xymc" />
						</td>
						<th>工作开始时间</th>
						<td>
							<html:text property="gzkssj" maxlength="10" onkeyup="value=value.replace(/[^\d|:|：]/g,'')"></html:text>
						</td>						
					</tr>
					<tr>
						<th>专业</th>
						<td>
							<bean:write name="rs" property="zymc" />
						</td>
						<th>工作结束时间</th>
						<td>
							<html:text property="gzjssj" maxlength="10" onkeyup="value=value.replace(/[^\d|:|：]/g,'')"></html:text>
						</td>						
					</tr>
					<tr>
						<th>班级</th>
						<td>
							<bean:write name="rs" property="bjmc" />
						</td>
						<th><span class="red">*</span>总工作时间</th>
						<td>
							<html:text property="zgzsj" maxlength="4" onkeyup="value=value.replace(/[^\d|.]/g,'')" ></html:text>
						</td>
					</tr>
					<tr>
						<th>工作内容</th>
						<td colspan="3">
							<html:textarea property="gznr" styleId="gznr" cols="80" rows="4" onblur="chLeng(this,'300')"></html:textarea>
						</td>
					</tr>
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
				            <button type="button" class="button2"
								onclick="save();return false;"
								style="width:80px">
								保 存
							</button>
							<button type="button" class="button2" onclick="window.close();return false;"
								style="width:80px">
								关 闭
							</button>
				          </div>
				        </td>
				      </tr>
				    </tfoot>
				</table>
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
