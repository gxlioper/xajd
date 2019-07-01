<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(val){
			var arrayList = val.split('-');
			for (var i=0; i<arrayList.length;i++) {
				if ($(arrayList[i])) {
					if (document.getElementById(arrayList[i]).value=='') {
						alert("请将带\"*\"号的项目输入完整！");
						return false;
					}
				}
			}
			document.getElementById('btn_save').disabled = true;
			document.forms[0].action = "/xgxt/zgmsxy_xszz.do?method=bysxxsqSave";
			document.forms[0].submit();
		}
		
	function ckinpdata(obj){
		obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
		var inputStr = obj.value;
		
		if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g))){
			alert('数据格式不正确，只能是数字！');
			obj.value = '';
			return false;
		}
		return true;
	}
	function print() {
		if (document.getElementById('xh').value=='') {
			alert('请输入学号！');
			return;
		}
		var xh = document.getElementById('xh').value;
		var	pkVal = document.getElementById('pkVal').value;
		window.open('/xgxt/zgmsxy_xszz.do?method=bysxxsqprint&xh='+xh+'&pkVal'+pkVal);
	}
	</script>
</head>

<body>
	<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>助学贷款 - 申请 - 毕业生信息</a>
				</p>
			</div>
		<html:form action="zgmsxy_xszz.do?method=bysxxsq" method="post">
			<input type="hidden" id="url" name="url"
				value="/zgmsxy_xszz.do?method=bysxxsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="${rs.pkVal }" />
			<div class="tab">
			<table class="formlist" width="90%">
				<tbody>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<th align="right" width="16%">
							<font color="red">*</font>学号
						</th>
						<td align="left" width="34%">
							<html:text name='rs' property="xh" styleId="xh"
								readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<logic:notEqual name="doType" value="modi">
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</logic:notEqual>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<th align="right" width="16%">
							<font color="red">*</font>学号
						</th>
						<td align="left" width="34%">
							<input type="text" id="xh" name="xh"
								value="<bean:write name='rs' property="xh" />" readonly="readonly"/>
						</td>
					</logic:equal>
					<th width="16%" align="right">
							姓名
					</th>
					<td width="34%" align="left">
						${rs.xm }
					</td>
				</tr>
				<tr>
					<th align="right">
							性别
					</th>
					<td align="left">
						${rs.xb }
					</td>
					<th align="right">
						毕业年月
					</th>
					<td align="left">
						${rs.byny }
					</td>
				</tr>
				<tr>
					<th align="right">
							身份证号
					</th>
					<td align="left">
						${rs.sfzh }
					</td>
					<th align="right">
						<font color="red">*</font>家庭住址
					</th>
					<td align="left">
						<input type="text" id="jtzz" maxlength="100" name="jtzz"
							
							value="<bean:write name="rs" property="jtzz"/>"/>
					</td>
				</tr>
				<tr>
					<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />名称
					</th>
					<td align="left">
						${rs.xymc }
					</td>
					<th align="right">
						邮政编码
					</th>
					<td align="left">
						<input type="text" id="yzbm" maxlength="6" name="yzbm"
							
							value="<bean:write name="rs" property="yzbm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
					</td>
				</tr>
				<tr>
					<th align="right">
							年级
					</th>
					<td align="left">
						${rs.nj }
					</td>
					<th align="right">
							父亲姓名
					</th>
					<td align="left">
						<input type="text" id="fqxm" maxlength="20" name="fqxm"
							
							value="<bean:write name="rs" property="fqxm"/>"/>
					</td>
				</tr>
				<tr>
					<th align="right">
							专业名称
					</th>
					<td align="left">
						${rs.zymc }
					</td>
					<th align="right">
						父亲联系电话
					</th>
					<td align="left">
						<input type="text" id="fqdh" maxlength="12" name="fqdh"
							
							value="<bean:write name="rs" property="fqdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
					</td>
				</tr>
				<tr>
					<th align="right">
							班级名称
					</th>
					<td align="left">
						${rs.bjmc }
					</td>
					<th align="right">
						父亲工作单位
					</th>
					<td align="left">
						<input type="text" id="fqgzdw" maxlength="50" name="fqgzdw"
							
							value="<bean:write name="rs" property="fqgzdw"/>"/>
					</td>
				</tr>
				<tr>
					<th align="right">
							母亲姓名
					</th>
					<td align="left">
						<input type="text" id="mqxm" maxlength="20" name="mqxm"
							
							value="<bean:write name="rs" property="mqxm"/>"/>
					</td>
					<th align="right">
						母亲联系电话
					</th>
					<td align="left">
						<input type="text" id="mqdh" maxlength="12" name="mqdh"
							value="<bean:write name="rs" property="mqdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
					</td>
				</tr>
				<tr>
					<th align="right">
						母亲工作单位
					</th>
					<td colspan="3" align="left">
						<input type="text" id="mqgzdw" maxlength="100" name="mqgzdw" style="width: 84%"
							value="<bean:write name="rs" property="mqgzdw"/>"/>
					</td>
				</tr>
				<tr>
					<th align="right">
						首次毕业去向
					</th>
					<td colspan="3" align="left">
						<input type="text" id="brjyqxhdw" maxlength="100" name="brjyqxhdw"
							style="width: 84%"
							value="<bean:write name="rs" property="brjyqxhdw"/>"/>
					</td>
				</tr>
				
				<tr>
					<th align="right">
						当前工作单位及地址
					</th>
					<td colspan="3" align="left">
						<input type="text" id="dqgzdwjdz" maxlength="100" name="dqgzdwjdz" style="width: 84%"
							value="<bean:write name="rs" property="dqgzdwjdz"/>"/>
					</td>
				</tr>
				<tr>
					<th align="right">
						当前工作单位邮编
					</th>
					<td colspan="" align="left">
						<input type="text" id="dqgzdwyb" maxlength="6" name="dqgzdwyb"
							value="<bean:write name="rs" property="dqgzdwyb"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
					</td>
					<th align="right">
						当前工作单位电话
					</th>
					<td colspan="" align="left">
						<input type="text" id="dqgzdwdh" maxlength="12" name="dqgzdwdh"
							value="<bean:write name="rs" property="dqgzdwdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
					</td>
				</tr>
				<tr>
					<th align="right">
						家庭电话
					</th>
					<td colspan="" align="left">
						<input type="text" id="jtgddh" maxlength="12" name="jtgddh"
							value="<bean:write name="rs" property="jtgddh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
					</td>
					<th align="right">
						<font color="red">*</font>手机
					</th>
					<td colspan="" align="left">
						<input type="text" id="lxdh" maxlength="12" name="lxdh"
							value="<bean:write name="rs" property="lxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
					</td>
				</tr>
				<tr>
					<th align="right">
						邮箱及QQ号
					</th>
					<td colspan="3" align="left">
						<input type="text" id="brdzyxjdzlxfs" maxlength="100" name="brdzyxjdzlxfs" style="width: 84%"
							value="<bean:write name="rs" property="brdzyxjdzlxfs"/>"/>
					</td>
				</tr>
				<tr>
					<th align="right">
						<font color="red">*</font>联系方式变更情况
					</th>
					<td colspan="3" align="left">
						<input type="text" id="lxfsbgqk" maxlength="200" name="lxfsbgqk"
							style="width: 84%"
							value="<bean:write name="rs" property="lxfsbgqk"/>"/>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">
			        <logic:equal name="sfksq" value="1">
			        	"<span class="red">*</span>"为必填项
			        </logic:equal>
			        
			      	  <logic:equal name="sfksq" value="-1">
			        		<span class="red">现在不在申请时间内！</span>
			          </logic:equal>
			        </div>
			          <div class="btn">
			          <logic:equal name="sfksq" value="1">
			          	<button type="button" name="提交" id="btn_save" onclick="yz('xh-lxdh-lxfsbgqk-jtzz');">提 交</button>
			            <button type="button" onclick="print()">
							打印预览
						</button>
					</logic:equal>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		</html:form>
		
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("保存成功！");
	         	Close();
	         	if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
   					 window.dialogArguments.document.getElementById('search_go').click(); 
  				}
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("保存失败！");
	         	Close();
	         	if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
    				window.dialogArguments.document.getElementById('search_go').click(); 
   				}
	         	</script>
				</logic:match>
			</logic:present>
			<logic:present name="isPASS">
				<logic:match value="is" name="isPASS">
					<script language="javascript">
	         			alert("已通过审核，不能申请！");
	         		</script>
				</logic:match>
			</logic:present>
			<logic:present name="isNull">
				<logic:match value="is" name="isNull">
					<script language="javascript">
	         			alert("您没有国家助学贷款信息！");
	         		</script>
				</logic:match>
			</logic:present>
</body>
</html>
