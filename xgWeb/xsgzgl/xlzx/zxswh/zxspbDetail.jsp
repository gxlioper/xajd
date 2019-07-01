<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.ArrayList" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript"  src="xsgzgl/xlzx/zxswh/js/zxspbDetail.js"></script>
	<style type="text/css">
	.demo_data2 {
	    border: 1px solid #DEDEDE;
		display: inline;
	    float: left;
	    height: 35px;
	    margin: 4px 5px 0;
	    padding: 8px;
	    width: 260px;
	}
	</style>
	</head>
	<body onload="init();">
	
		<html:form action="/xlzx_zxspb" method="post">
		<!-- <input type="hidden" id="url" name="url" value="xlzx_zxspb.do?method=zxspbDetail" /> -->
			<input type="hidden" name="pbid" id="pbid" value="${zxspbList.id}" />
			<input type="hidden" name="zghs" id="zghs" value="${zxspbList.zgh}" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="date" id="date" value="${date}" />
			<logic:iterate name="zxsList" id="zxs" indexId="index">
				<input type="hidden" name="zxsZgh" value="${zxs.zgh}"/>
				<input type="hidden" name="zxsXm" value="${zxs.xm}"/>
				<input type="hidden" name="zxsXb" value="${zxs.xb}"/>
				<input type="hidden" name="zxsBmmc" value="${zxs.bmmc}"/>
				<input type="hidden" name="zxsCheckboxzt" value="${zxs.checkboxzt}"/>
			</logic:iterate>
			<div style='width:100%;higth:450px;overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<tbody id="tbody_jbxx">
						<tr style="height:35px">
							<th width="16%">
								咨询日期
							</th>
							<td colspan="3">${date} &nbsp;&nbsp;${weekday}
							</td>
						</tr>
						<logic:equal name="doType" value="add">
						<tr style="height:35px">
							<th width="16%">
								每<span style="color:blue">周${week}</span>安排<br />相同咨询师
							</th>
							<td >
								<input type="radio"  id="sfCopyPb" name="sfCopyPb"  value="1"  onclick="sfqyCopyPb();"/>是
								<input type="radio"  id="sfCopyPb" name="sfCopyPb"  value="2"  onclick="sfqyCopyPb();" checked="true"/>否
							</td>
							<th name="copyQzrqName">起止时间
							</th>
							<td name="copyQzrqName">
								<input type="hidden" name="pbqssj" id="pbqssj" style="width:100px"  value="${date}" />${date}&nbsp;至&nbsp;
								<span class="red">*</span><html:text property="pbjssj" styleId="pbjssj" style="width:30%"  value="${afterMonthDate}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'${date}'})" />
							</td>
						</tr>
						</logic:equal>
						<tr >
							<th align="right">
									咨询师
							</th>
							<td align="left" id = "zxsInfos" colspan="3">
							</td>
						</tr>
						<tr style="height:35px">
							<th>
								预约说明<br/>
								<logic:notEqual name="doType" value="view"><font color="red"><B>(限500字)</B></font></logic:notEqual>
							</th>
							<td colspan="3">
								<logic:notEqual name="doType" value="view">
									<html:textarea  property='bz' styleId="bz" style="width:90%" value="${zxspbList.bz }" onblur="checkLen(this,500);"
										rows='4' />
								</logic:notEqual>
								<logic:equal name="doType" value="view">
									${zxspbList.bz }
								</logic:equal>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 30px;"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td>
								<logic:notEqual name="doType" value="view">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
								</logic:notEqual>
								<div class="btn">
									<button id="buttonSave" onclick="saveZxsPbInfo();return false;">
										保 存
									</button>
									<button onclick="Close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>

