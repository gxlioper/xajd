<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/zjlgZbglDAO.js'></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript">	
	function saveZzgx(){
		var xh = $("xh").value;
		var zjsj = $("zjsj").value;
		var zjlx = $("zjlx").value;
		var zjmm = $("zjmm").value;
		
		if(xh==" "){
			alert("学号为空，请确认！");
			return false;
		}
		if(zjsj==""){
			alert("转接时间为空，请确认！");
			return false;
		}
		if(zjmm==""){
			alert("政治面貌为空，请确认！");
			return false;
		}
		if(zjlx==""){
			alert("转接类型为空，请确认！");
			return false;
		}
		var url = "/xgxt/zjlgDtjsZzgx.do?method=zzgxUpdate&doType=save";
		showTips('处理数据中，请等待......');
		$("buttonSave").disabled=true;
		$("buttonClose").disabled=true;
		refreshForm(url);
	}
	
	function getZbmc(){
		var xh = $("xh").value;
		var zbmc = $("zjdz").value;
		dwr.engine.setAsync(false);
		if(xh !=" " && zbmc == ""){
			zjlgZbglDAO.getZbmc(xh,function(data){
				$("zjdz").value=data;		
			});
		}
		dwr.engine.setAsync(true);
	}
	</script>
	</head>
	<body onload="getZbmc()">
		<html:form action="/zjlgDtjsZzgx">
		<input type="hidden" id="url" name="url" value="/zjlgDtjsZzgx.do?method=zzgxUpdate&doType=add"/>
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xymc-zymc-bjmc"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="title" /></a>
				</p>
			</div>
			<div class="tab">
			<table class="formlist" id="rsTable">
				<thead>
					<tr>
						<th colspan="4">
							<span>组织关系转接</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr style="height: 23px">
					<th>
						学号
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
						</logic:notEqual>
					</td>
					<th>
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<html:text name='rs' property="xymc" styleId="xymc" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						姓名
					</th>
					<td align="left">
						<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
					</td>
					<th>
						专业
					</th>
					<td align="left">
							<html:text name='rs' property="zymc" styleId="zymc" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						性别
					</th>
					<td align="left">
							<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
					</td>
					<th>
						班级
					</th>
					<td align="left">
						<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						出生日期
					</th>
					<td align="left">
							<html:text name='rs' property="csrq" styleId="csrq" readonly="true"/>
					</td>
					<th>
						民族
					</th>
					<td align="left">
						<html:text name='rs' property="mzmc" styleId="mzmc" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						<font color="red">*</font>政治面貌
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:select name="rs" property="zjmm" style="">
								<html:option value=""></html:option>
								<html:option value="ybdy">预备党员</html:option>
								<html:option value="zsdy">正式党员</html:option>
							</html:select>	
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name='rs' property="zjmm" styleId="zjmm"/>
							<html:select name="rs" property="zjmm" style="" disabled="true">
								<html:option value=""></html:option>
								<html:option value="ybdy">预备党员</html:option>
								<html:option value="zsdy">正式党员</html:option>
							</html:select>	
						</logic:notEqual>
					</td>
					<th>
						身份证号码
					</th>
					<td align="left">
						<html:text name='rs' property="sfzh" styleId="sfzh" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						联系电话
					</th>
					<td align="left">
						<html:text name='rs' property="lxdh" styleId="lxdh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
					<th>
						党费交至月份
					</th>
					<td align="left">
						<html:select name="rs" property="dfjzyf" style="">
							<html:option value=""></html:option>
							<html:options collection="yfList" property="yf" labelProperty="yf" />
						</html:select>	
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						介绍信编号
					</th>
					<td align="left">
						<html:text name='rs' property="jsxbh" styleId="jsxbh" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
					<th>
						有效期
					</th>
					<td align="left">
						<html:text name="rs" property="yxq" styleId="yxq" readonly="true"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('yxq','y-mm-dd');"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						<font color="red">*</font>转接类型
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:select name="rs" property="zjlx" style="">
								<html:option value=""></html:option>
								<html:option value="in">转入</html:option>
							</html:select>	
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="zjlx" style=""/>
							<html:select name="rs" property="zjlx" style="" disabled="true">
								<html:option value=""></html:option>
								<html:option value="in">转入</html:option>
								<html:option value="out">转出</html:option>
								<html:option value="zz">转正</html:option>
							</html:select>	
						</logic:notEqual>
					</td>
					<th>
						<font color="red">*</font>转接时间
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:text name="rs" property="zjsj" styleId="zjsj" readonly="true"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('zjsj','y-mm-dd');"/>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="zjsj" style=""/>
							<bean:write name="rs" property="zjsj"/>
						</logic:notEqual>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						原单位地址
					</th>
					<td align="left">
						<html:text name='rs' property="ydw" styleId="ydw" style="" maxlength="50"/>
					</td>
					<th>
						转接地址
					</th>
					<td align="left">
						<html:text name='rs' property="zjdz" styleId="zjdz" style="" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						备注
					</th>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="bz" styleId="bz" rows="5" style="width: 95%;word-break:break-all;" onblur="chLeng(this,60)"/>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
						  <logic:notEqual name="doType" value="view">
							<button type="button" id="buttonSave" onclick="saveZzgx()">
								保存
							</button>
						  </logic:notEqual>
						  <button type="button" id="buttonClose" name="关闭" onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
						dialogArgumentsQueryChick();
						window.close();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
