<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSzPjpyDAO.js'></script>
	<script type="text/javascript">	

function ztbhSave(the_tab){
	var tabobj = document.getElementById(the_tab);
	var rowLen = tabobj.rows.length;
	var nullCout = 0;
	var xh = $("xh").value;
	var xn = $("xnV").value;
	var xq = $("xqV").value;
	
	if(xh == ""){
		alert("请确认所需操作学生");
		return false;
	}
	for(var i=1;i<=rowLen;i++){
		if($("lrrq"+i)){
			if($("fzxm"+i).value == ""){
				alert("第"+i+"行日期为空，请确定");
				return false;
			}
		}
		if($("fz"+i)){
			if($("fz"+i).value == ""){
				alert("第"+i+"行分值为空，请确定");
				return false;
			}
		}
	}

   showTips('处理数据中，请等待......');
   refreshForm('/xgxt/pjpyszyqwh.do?method=szyc_5sAdd&doType=save&xn='+xn+'&xq='+xq);
   $("buttonSave").disabled=true;
}

function toChk(shzt){
	var pk = $("pk").value;
	var num=$("num").value;
	var flg = true;
	if(num != null && num >0){
		for(var i=0;i<num;i++){
			if($("checkVal"+i)){
				if ($("checkVal"+i).checked){
					flg = false;
					break;
				}
			}
		}
	}
	if(flg){
		alert('请勾选要审核的信息!');
		return false;
	}
   showTips('处理数据中，请等待......');
   refreshForm('/xgxt/pjpyszyqwh.do?method=szyc_5sSh&doType=save&shzt='+shzt+'&pkValue='+pk);
   $("buttonSave").disabled=true;
}
	</script>
	</head>
	<body onload="">
		<html:form action="/pjpyszyqwh">
			<input type="hidden" id="url" name="url" value="/pjpy/szyqxy/zhszcp/5s_Add.jsp"/>
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xymc-zymc-bjmc"/>
			<input type="hidden" id="pk" name="pk" value="${pk}"/>
			<input type="hidden" id="xnV" name="xnV" value="${xnV}"/>
			<input type="hidden" id="xqV" name="xqV" value="${xqV}"/>
			<input type="hidden" id="msg" name="msg" value="${msg}"/>
			<input type="hidden" id="num" name="num" value="${rsNum}"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>综合素质-学生综合素质养成课-5s分审核</a>
				</p>
			</div>
			<table class="formlist" border="0" id="rsTable" align="center"
				style="width: 100%">
				<tr style="height: 23px">
					<th>
						学号
					</th>
					<td align="left">
						<html:text name='rs' property="xh" styleId="xh" readonly="true" />
					</td>
					<th>
						学年
					</th>
					<td align="left">
						<html:text name='rs' property="xn" styleId="xn" readonly="true"/>
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
							<html:text name='rs' property="xq" styleId="xq" readonly="true"/>
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
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<html:text name='rs' property="xymc" styleId="xymc" readonly="true"/>
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
						班级
					</th>
					<td align="left">
						<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true"/>
					</td>
				</tr>
			</table>
			<fieldset>
				<legend>
					记录数:
					${rsNum}
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="blue">参加活动列表</font>
				</legend>
				<table width="99%" class="formlist" >
					<thead style="height: 23px">
						<tr >
						    <th>
								
						    </th>
							<th nowrap="nowrap" style='width:5%'>
								序号
							</th>
							<th nowrap="nowrap" style='width:25%'>
								项目
							</th>
							<th nowrap="nowrap" style='width:12%'>
								加减分
							</th>
							<th nowrap="nowrap" style='width:10%'>
								分值
							</th>
							<th nowrap="nowrap" style='width:15%'>
								日期
							</th>
							<th nowrap="nowrap" style='width:30%'>
								加分原因
							</th>
							<th nowrap="nowrap" style='width:30%'>
								审核状态
							</th>
						</tr>
					</thead>
					<logic:iterate name="rsList" id="s" indexId="index">
						<tr>
							<td>
								<input type="checkbox" id="checkVal${index}" name="checkVal"
									value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
							</td>
							<logic:iterate id="v" name="s" offset="1">
								<td align=center>
									<bean:write name="v" />
								</td>		
							</logic:iterate>
						</tr>
					</logic:iterate>
				</table>
			</fieldset>
			<div align="right">
				<button type="button" class="button2" id="buttonSave" onclick="toChk('yes')">
					通过
				</button>
				<button type="button" class="button2" id="buttonSave" onclick="toChk('no')">
					不通过
				</button>
				<button type="button" class="button2" id="buttonClose" 
					onclick="window.close();window.dialogArguments.document.getElementById('search_go').click();">
					关闭
				</button>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:equal>
			</logic:present>
			<logic:present name="msg">
				<script>
					alert($("msg").value);
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
