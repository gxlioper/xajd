<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
	function savefa(){
		var bz = document.getElementById("bz").value;
		if(bz.length>60){
			alert("备注字数不能超过60个");
			return false;
		}
		if(checkXnNd('xn','nd'))dataDoSave('xn-xq-xh-bbsj-jbr-sflq');
	}
	function ffupdate(){
		var bz = document.getElementById("bz").value;
		if(bz.length>60){
			alert("备注字数不能超过60个");
			return false;
		}
		
		dataCanModi(true)
	}
	</script>
</head>
	<body onload="checkWinType();dataManLoad();">
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/data_search" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>详细信息查看</a>
				</p>
			</div>
		
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("您输入的学号无效!");
    </script>
				</logic:equal>
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle"
					value="sqsj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc-sfzh" />
				<input type="hidden" id="userOnLine" name="userOnLine"
					value="${userOnLine}" />
				<input type="hidden" id="url" name="url" value="/sjcz/xszbbb.jsp" />
				<div class="tab">
					<table width="100%" class="formlist">
						<thead>
						<tr>
							<th colspan="4">
								<span>补办信息维护</span>
							</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<th>
								<font color="red">*</font>学号
							</th>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu" style="display:none">
									选择
								</button>
							</td>
							<th>
								<font color="red">*</font>年度
							</th>
							<td align="left">
								<html:select name="rs" property="nd" style="width:90px"
									styleId="nd">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								姓名
							</th>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" />
							</td>
							<th>
								<font color="red">*</font>学年
							</th>
							<td align="left">
								<html:select name="rs" property="xn" style="width:90px"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" />
							</td>
							<th>
								<font color="red">*</font>学期
							</th>
							<td align="left">
								<html:select name="rs" property="xq" style="width:90px"
									styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								学制
							</th>
							<td align="left">
								<html:text name='rs' property="xz" styleId="xz" readonly="true"/>
							</td>
							<th>
								出生日期
							</th>
							<td align="left">
								<html:text name='rs' property="csrq" styleId="csrq" readonly="true"/>
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" />
							</td>
							<th>
								申请时间
							</th>
							<td align="left">
								<%--								<html:text name='rs' property="sqsj" styleId="sqsj" />--%>
								<html:text name='rs' property="sqsj" styleId="sqsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('sqsj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" />
							</td>
							<th>
								<font color="red">*</font>经办人
							</th>
							<td align="left">
								<html:select name="rs" property="jbr" style="width:90px"
									styleId="jbr">
									<html:option value=""></html:option>
									<html:options collection="jbrList" property="jbrgh"
										labelProperty="jbrxm" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" />
							</td>
							<th>
								<font color="red">*</font>补办时间：
							</th>
							<td align="left">
								<html:text name='rs' property="bbsj" styleId="bbsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('bbsj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th>
								班级
							</th>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" />
							</td>
							<th>
								<font color="red">*</font>是否领取
							</th>
							<td align="left">
								<html:select name="rs" property="sflq" style="width:90px"
									styleId="sflq">
									<html:option value=""></html:option>
									<html:options collection="ynList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								身份证号
							</th>
							<td align="left">
								<html:text name='rs' property="sfzh" styleId="sfzh"
									readonly="true" />
							</td>
							<th>
								生源地
							</th>
							<td align="left">
								<html:text name='rs' property="syd" styleId="syd" />
							</td>
						</tr>

						<tr>
							<th>
								补办原因
							</th>
							<td align="left">
								<html:text name='rs' property="bbyy" styleId="bbyy"
									style="width: 200px" />
							</td>
							<th>
								火车站名
							</th>
							<td align="left">
								<html:text name='rs' property="hczm" styleId="hczm"
									style="width: 200px" />
							</td>
						</tr>
						<tr align="left">
							<th>
								备注
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="word-break:break-all;width:99%"
									rows='5' />
							</td>
						</tr>
						</tbody>
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
					          <div class="btn">
					          <button type="button"  onclick="ffupdate();" id="buttonModi">
									修 改
								</button>
								<button type="button" onclick="savefa();" id="buttonSave">
									保 存
								</button>
					            <button type="button" name="关闭" id="buttonClose" onclick="window.close();return false;">关闭</button>
					          </div></td>
					      </tr>
					    </tfoot>
					</table>
				</div>
			</logic:notEmpty>
			<jsp:include flush="true" page="/sjcz/include/modiPageJudge.jsp"></jsp:include>
		</html:form>
	</body>
</html>
