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
			alertInfo("备注字数不能超过60个",function(){});
			return false;
		}
		if(checkXnNd('xn','nd'))dataDoSave('xn-xq-xh-ffsj-jbr');
	}
	function ffupdate(){
		var bz = document.getElementById("bz").value;
		if(bz.length>60){
			alertInfo("备注字数不能超过60个",function(){});
			return false;
		}
		
		dataCanModi(true);
	}
	
	
	
</script>
	</head>
	<body onload="checkWinType();dataManLoad();">
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title" style="display:none">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>日常事务-学生证管理-发放 </a>
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
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/sjcz/xszffb.jsp" />
				<input type="hidden" id="userOnLine" name="userOnLine"
					value="${userOnLine }" />
				<div class="tab">
					<table width="100%" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>发放用户维护</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th align="right">
									<font color="red">*</font>学号
								</th>
								<td>
									<html:text name='rs' property="xh" readonly="readonly"
										styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do?tableName=xszffb',750,550);"
										class="btn_01" id="buttonFindStu" style="display:none">
										选择
									</button>
								</td>
								<th align="right">
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
								<th align="right">
									姓名
								</th>
								<td align="left">
									<html:text name='rs' property="xm" styleId="xm" />
								</td>
								<th align="right">
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
								<td>
									<html:text name='rs' property="xb" styleId="xb" />
								</td>
								<th>
									<font color="red">*</font>学期
								</th>
								<td>
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
									年级
								</th>
								<td>
									<html:text name='rs' property="nj" styleId="nj" />
								</td>
								<th>
									<font color="red">*</font>发放时间
								</th>
								<td>
									<html:text name='rs' property="ffsj" styleId="ffsj"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('ffsj','y-mm-dd');" />
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:text name='rs' property="xymc" styleId="xy" />
								</td>
								<th >
									<font color="red">*</font>经办人
								</th>
								<td>
									<html:select name="rs"  property="jbr"  style="width:90px"
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
								<td>
									<html:text name='rs' property="zymc" styleId="zy" />
								</td>
								<th>
									班级
								</th>
								<td>
									<html:text name='rs' property="bjmc" styleId="bj" />
								</td>
							</tr>
							<!-- 
						<tr>
							<td align="right">
								班级：
							</td>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" />
							</td>
							<td align="right">
								学制：
							</td>
							<td align="left">
								<html:text name='rs' property="xz" styleId="xz"/>
							</td>
						</tr>
						 -->
							<!--  
						<tr>
							<td align="right">
								身份证号：
							</td>
							<td align="left">
								<html:text name='rs' property="sfzh" styleId="sfzh" />
							</td>
							<td align="right">
								火车站名：
							</td>
							<td align="left">
								<html:text name="rs" property="hczm" />
							</td>
						</tr>
						-->
							<tr align="left">
								<th>
									备注<br/>(<font color="red">限60字</font>)
								</th>
								<td colspan="3">
									<html:textarea name='rs' property='bz' style="word-break:break-all;width:99%"
										rows='5' />
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">
										"
										<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button" onclick="ffupdate();return false;" id="buttonModi">
											修 改
										</button>
										<button type="button" onclick="savefa();return false;" id="buttonSave">
											保 存
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" onclick="Close();return false;" id="buttonClose">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</logic:notEmpty>
			<logic:equal value="true" name="isSuccess">
				<script>
					alertInfo('操作成功',function(t){
						if(t=="ok"){
							dialogArgumentsQueryChick();
						close();
						}
					});
					
				</script>
			</logic:equal>
			<logic:equal value="false" name="isSuccess">
				<script>
					alert('操作失败',function(t){
						if(t=="ok"){
							dialogArgumentsQueryChick();
						close();
						}
					});
				</script>
			</logic:equal>
		</html:form>
	</body>
</html>
