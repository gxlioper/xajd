<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/rwdj/js/rwdj.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function() {
			});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form"
			action="/rwdj.do?method=add&type=save">
			<html:hidden property="xh" styleId="xh"/>
			<html:hidden property="rwdjid" />
			<div
				style='width: 100%; height: 460px; overflow-x: hidden; overflow-y: auto;'>
				<table width="" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>
					<thead>
						<tr>
							<th colspan="4">
								<span>入伍信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<font color="red">*</font>入伍途径
							</th>
							<td colspan="3">
								<html:select  property="rwtj" styleId="rwtj" style="width:155px">
									<html:options collection="rwtjList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<span class="red">*</span>入伍时间
							</th>
							<td>
								<html:text property="rwsj" styleId="rwsj" onfocus="return showCalendar(this.id,'yyyy-MM-dd');" maxlength="20"></html:text>
							</td>
							<th width="20%">
								<span class="red">*</span>学业情况
							</th>
							<td>
								<html:text property="xyqk" styleId="xyqk" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<span class="red">*</span>有无签入伍协议
							</th>
							<td>
								<div>
									<html:radio property="ywqrwxy" value="无">无</html:radio>
									<html:radio property="ywqrwxy" value="有">有</html:radio>
								</div>
							</td>
							<th width="20%">
								<span class="red">*</span>不及格门数
							</th>
							<td>
								<html:text property="bjgms" styleId="bjgms" maxlength="2" onblur="checkInt(this);"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<span class="red">*</span>婚姻状况
							</th>
							<td>
								<html:select property="hyzk" styleId="hyzk">
									<option value="">---请选择---</option>
									<html:option value="未婚">未婚</html:option>
									<html:option value="已婚">已婚</html:option>
								</html:select>
							</td>
							<th width="20%">
								<span class="red">*</span>从业类别
							</th>
							<td>
								<html:text property="cylb" styleId="cylb" maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<span class="red">*</span>户籍类别
							</th>
							<td>
								<html:select property="hjlb" styleId="hjlb">
									<option value="">---请选择---</option>
									<html:option value="家庭户口">家庭户口</html:option>
									<html:option value="集体户口">集体户口</html:option>
								</html:select>
							</td>
							<th width="20%">
								<span class="red">*</span>部队联系方式
							</th>
							<td>
								<html:text property="bdlxfs" styleId="bdlxfs" maxlength="20" onkeyup="checkInputLxfx(this);"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								父亲姓名
							</th>
							<td>
								<html:text property="fqxm" styleId="fqxm" maxlength="20"></html:text>
							</td>
							<th width="20%">
								父亲手机
							</th>
							<td>
								<html:text property="fqsj" styleId="fqsj" maxlength="20" onkeyup="checkInputData(this);"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								母亲姓名
							</th>
							<td>
								<html:text property="mqxm" styleId="mqxm" maxlength="20"></html:text>
							</td>
							<th width="20%">
								母亲手机
							</th>
							<td>
								<html:text property="mqsj" styleId="mqsj" maxlength="20" onkeyup="checkInputData(this);"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								其他联系人
							</th>
							<td>
								<html:text property="qtlxr" styleId="qtlxr" maxlength="20"></html:text>
							</td>
							<th width="20%">
								其他联系人方式
							</th>
							<td>
								<html:text property="qtlxrfs" styleId="qtlxrfs" maxlength="20" onkeyup="checkInputLxfx(this);"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								左眼视力
							</th>
							<td>
								<html:text property="zysl" styleId="zysl" maxlength="20" onkeyup="checkInputNum(this);"></html:text>
							</td>
							<th width="20%">
								右眼视力
							</th>
							<td>
								<html:text property="yysl" styleId="yysl" maxlength="20" onkeyup="checkInputNum(this);"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<span class="red">*</span>服役部队
							</th>
							<td>
								<html:text property="fybd" styleId="fybd" maxlength="20"></html:text>
							</td>
							<th width="20%">
								部队地址
							</th>
							<td>
								<html:text property="bddz" styleId="bddz" maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								优秀士兵
							</th>
							<td>
								<html:text property="yxsb" styleId="yxsb" maxlength="20"></html:text>
							</td>
							<th width="20%">
								复原时间
							</th>
							<td>
								<html:text property="fysj" styleId="fysj" onfocus="return showCalendar(this.id,'yyyy-MM-dd');" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								嘉奖
							</th>
							<td colspan="3">
								<html:textarea property="jj" styleId="jj" style="width:94%" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th width="20%">
								立功
							</th>
							<td colspan="3">
								<html:textarea property="lg" styleId="lg"  style="width:94%" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th width="20%">
								毕业时间
							</th>
							<td>
								<html:text property="bysj" styleId="bysj" onfocus="return showCalendar(this.id,'yyyy-MM-dd');" maxlength="20"></html:text>
							</td>
							<th width="20%">
								专接本时间
							</th>
							<td>
								<html:text property="zjbsj" styleId="zjbsj" onfocus="return showCalendar(this.id,'yyyy-MM-dd');"maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								 专接本后就读学院
							</th>
							<td>
								<html:text property="zjbhjdxy" styleId="zjbhjdxy" maxlength="25"></html:text>
							</td>
							<th width="20%">
								专接本后专业
							</th>
							<td>
								<html:text property="zjbhzy" styleId="zjbhzy" maxlength="25"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								 专接本后学号     
							</th>
							<td>
								<html:text property="zjbhxh" styleId="zjbhxh" maxlength="25" onkeyup="checkInputData(this);"></html:text>
							</td>
							<th width="20%">
								专接本后毕业时间   
							</th>
							<td>
								<html:text property="zjbhbysj" styleId="zjbhbysj" onfocus="return showCalendar(this.id,'yyyy-MM-dd');" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								 银行卡号     
							</th>
							<td>
								<html:text property="bjyhkh" styleId="bjyhkh" maxlength="20" onkeyup="checkInputData(this);"></html:text>
							</td>
							<th width="20%">
								银行卡名称     
							</th>
							<td>
								<html:text property="yhkmc" styleId="yhkmc" maxlength="25"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								银行卡地址     
							</th>
							<td>
								<html:text property="yhkdz" styleId="yhkdz" maxlength="50"></html:text>
							</td>
							<th width="20%">
								入伍后学费补偿 
							</th>
							<td>
								<html:text property="rwhxfbc" styleId="rwhxfbc" maxlength="20" onkeyup="checkInputNum(this);"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								退役后学费资助 
							</th>
							<td>
								<html:text property="tyhxfzz" styleId="tyhxfzz" maxlength="20" onkeyup="checkInputNum(this);"></html:text>
							</td>
							<th width="20%">
								就业后单位     
							</th>
							<td>
								<html:text property="jyhdw" styleId="jyhdw" maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								公务员      
							</th>
							<td>
								<html:text property="gwy" styleId="gwy" maxlength="20"></html:text>
							</td>
							<th width="20%">
								事业编          
							</th>
							<td>
								<html:text property="syb" styleId="syb" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								国企     
							</th>
							<td>
								<html:text property="gq" styleId="gq" maxlength="20"></html:text>
							</td>
							<th width="20%">
								非公经济        
							</th>
							<td>
								<html:text property="fgjj" styleId="fgjj" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								备注
							</th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" style="width:94%" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
					</tbody>
					</div>
					<div>
						<table width="100%" border="0" class="formlist"
							style="position: fixed; _position: absolute; bottom: 0;">
							<tfoot>
								<tr>
									<td colspan="4">
										<div class="bz">
											"
											<span class="red">*</span>"为必填项
										</div>
										<div class="btn">
											<button type="button"
												onclick="save('rwdj.do?method=update&type=save','xh-rwsj-xyqk-bjgms-hyzk-cylb-hjlb-bdlxfs-fybd');return false;"
												id="buttonSave">
												保 存
											</button>
											<button type="button" onclick="iFClose();" id="buttonClose">
												关 闭
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
					</html:form>
	</body>
</html>
