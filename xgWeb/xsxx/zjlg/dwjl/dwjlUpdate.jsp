<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type="text/javascript">	
	function saveDwjl(){
		var xh = $("xh").value;
		var xn = $("xn").value;
		var xq = $("xq").value;
		var jlxm = $("jlxm").value;
		var cgsj = $("cgsj").value;
		var hgsj = $("hgsj").value;
		if(xh==" "){
			alert("学号不能为空！");
			return false;
		}
		if(xn=="" || xq == ""){
			alert("学年或者学期不能为空！");
			return false;
		}
		if(jlxm==""){
			alert("请确定交流项目！");
			return false;
		}
		if(cgsj !="" && hgsj != ""){
			if(cgsj>hgsj){
				alert("出国时间大于回国时间，请确认！");
				return false;
			}
		}
		var url = "/xgxt/zjlgXsxxDwjl.do?method=dwjlUpdate&doType=save";
		showTips('处理数据中，请等待......');
		$("buttonSave").disabled=true;
		$("buttonClose").disabled=true;
		refreshForm(url);
	}
	</script>
	</head>
	<body onload="">
		<html:form action="/zjlgXsxxDwjl">
		<input type="hidden" id="url" name="url" value="/zjlgXsxxDwjl.do?method=dwjlUpdate&doType=add"/>
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
							<span>对外交流</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr style="height: 23px">
					<th>
						<font color="red">*</font>学号
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
						<font color="red">*</font>学年
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
						<html:select name="rs" property="xn" style="">
							<html:options collection="xnList" property="xn" labelProperty="xn" />
						</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
						<html:hidden name='rs' property="xn" styleId="xn"/>
						<html:select name="rs" property="xn" style="" disabled="true">
							<html:options collection="xnList" property="xn" labelProperty="xn" />
						</html:select>
						</logic:notEqual>
					</td>
					<th>
						<font color="red">*</font>学期
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
						<html:select name="rs" property="xq" style="">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
						</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
						<html:hidden name='rs' property="xq" styleId="xq"/>
						<html:select name="rs" property="xq" style="" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
						</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						出国（境）时间
					</th>
					<td align="left">
						<html:text name="rs" property="cgsj" styleId="cgsj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('cgsj','y-mm-dd');"/>
					</td>
					<th>
						回国（境）时间
					</th>
					<td align="left">
						<html:text name="rs" property="hgsj" styleId="hgsj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('hgsj','y-mm-dd');"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						<font color="red">*</font>交流项目
					</th>
					<td align="left" colspan="3">
						<logic:equal name="doType" value="add">
						<html:select name="rs" property="jlxm" style="">
							<html:option value=""></html:option>
							<html:options collection="jlxmList" property="xmdm" labelProperty="xmmc" />
						</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
						<html:hidden name='rs' property="jlxm" styleId="jlxm"/>
						<html:select name="rs" property="jlxm" style="" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="jlxmList" property="xmdm" labelProperty="xmmc" />
						</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						备注<br/>
						<font color="red">(限制字数150)</font>
					</th>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="bz" styleId="bz" rows="5" style="width: 95%;word-break:break-all;" onblur="chLeng(this,150)"/>
					</td>
				</tr>
				</tbody>
				<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
							  <logic:notEqual name="doType" value="view">
								<button type="button" id="buttonSave" onclick="saveDwjl()">
									保存
								</button>
							  </logic:notEqual>
							  <button type="button" name="关闭" id="buttonClose" onclick="window.close();return false;">关闭</button>
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
