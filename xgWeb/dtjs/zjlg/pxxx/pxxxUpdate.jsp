<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript"  src="js/sztzFunction.js"></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/zjlgZbglDAO.js'></script>
	<script type="text/javascript">	
	function savePxxx(){
		var xh = $("xh").value;
		var xn = $("xn").value;
		var xq = $("xq").value;
		var nd = $("nd").value;
		var pxxmdm = $("pxxmdm").value;
		var pxkssj = $("pxkssj").value;
		var pxjssj = $("pxjssj").value;
		if(xh==" "){
			alert("学号为空，请确认！");
			return false;
		}
		if(xn=="" || xq == ""|| nd == ""){
			alert("年度，学年,学期不能为空！");
			return false;
		}
		if(pxxmdm==""){
			alert("请确定培训项目！");
			return false;
		}
		if(pxkssj !="" && pxjssj != ""){
			if(pxkssj>pxjssj){
				alert("开始时间大于结束时间，请确认！");
				return false;
			}
		}
		var url = "/xgxt/zjlgDtjsPxxx.do?method=pxxxUpdate&doType=save";
		showTips('处理数据中，请等待......');
		$("buttonSave").disabled=true;
		$("buttonClose").disabled=true;
		refreshForm(url);
	}
	
	function getZbmc(){
		var xh = $("xh").value;
		var zbmc = $("zbmc").value;
		dwr.engine.setAsync(false);
		if(xh !=" " && zbmc == ""){
			zjlgZbglDAO.getZbmc(xh,function(data){
				$("zbmc").value=data;		
			});
		}
		dwr.engine.setAsync(true);
	}
	</script>
	</head>
	<body onload="getZbmc()">
		<html:form action="/zjlgDtjsPxxx">
		<input type="hidden" id="url" name="url" value="/zjlgDtjsPxxx.do?method=pxxxUpdate&doType=add"/>
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
							<span>培训信息</span>
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
						年度
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:select name="rs" property="nd" style="" styleId="nd">
								<html:options collection="ndList" property="nd" labelProperty="nd" />
							</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name='rs' property="nd" styleId="nd"/>
							<html:select name="rs" property="nd" style="" styleId="nd" disabled="true">
								<html:options collection="ndList" property="nd" labelProperty="nd" />
							</html:select>
						</logic:notEqual>
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
						学年
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:select name="rs" property="xn" style="" styleId="xn">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name='rs' property="xn" styleId="xn"/>
							<html:select name="rs" property="xn" style="" styleId="xn" disabled="true">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>
						</logic:notEqual>
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
						学期
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:select name="rs" property="xq" style="" styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name='rs' property="xq" styleId="xq"/>
							<html:select name="rs" property="xq" style="" styleId="xq" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						所属支部
					</th>
					<td align="left">
						<html:text name='rs' property="zbmc" styleId="zbmc" readonly="true"/>
					</td>
					<th>
						培训项目
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:select name="rs" property="pxxmdm" style="" styleId="pxxmdm">
								<html:option value=""></html:option>
								<html:options collection="pxxmList" property="pxxmdm" labelProperty="pxxmmc" />
							</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name='rs' property="pxxmdm" styleId="pxxmdm"/>
							<html:select name="rs" property="pxxmdm" style="" styleId="pxxmdm" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="pxxmList" property="pxxmdm" labelProperty="pxxmmc" />
							</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
							<html:text name='rs' property="xymc" styleId="xymc" readonly="true"/>
					</td>
					<th>
						培训结果
					</th>
					<td align="left">
						<html:text name='rs' property="pxjg" styleId="pxjg" maxlength="4"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						专业
					</th>
					<td align="left">
						<html:text name='rs' property="zymc" styleId="zymc" readonly="true"/>
					</td>
					<th>
						开始时间
					</th>
					<td align="left">
						<html:text name="rs" property="pxkssj" styleId="pxkssj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('pxkssj','y-mm-dd');"/>	
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						班级
					</th>
					<td align="left">
						<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true"/>
					</td>
					<th>
						结束时间
					</th>
					<td align="left">
						<html:text name="rs" property="pxjssj" styleId="pxjssj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('pxjssj','y-mm-dd');"/>
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
							<button type="button" id="buttonSave" onclick="savePxxx()">
								保存
							</button>
						  </logic:notEqual>
						  <button type="button" name="关闭" id="buttonClose" onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
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
