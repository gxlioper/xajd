<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script>
			function print(){
				var url = 'pjpyLsxy.do?method=printRychsqb';
				url += "&xh=" + val("xh");
				url += "&xn=" + val("xn");
				url += "&rychmc=" + selText("rychdm");
				url += "&zysj=" + val("zysj");
				showOpenWindow(url,800,600)
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/pjpyLsxy" method="post">
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			<input type="hidden" id="disableEle" name="disableEle"
				value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url"
				value="/pjpyLsxy.do?method=rychSq" />
			<logic:equal name="rs" property="stuExists" value="no">
				<script>
  	 				alert("您输入的学号无效!");
   				</script>
			</logic:equal>





			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>填写申请表</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button name="提交"
										onclick="saveinfo('pjpyLsxy.do?method=rychsqSave','xh-rychdm');">
										保存
									</button>
									<button class="button2" id="btn_dy" onclick="print()">
										打印申请表
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr style="height:23px">
							<th width="15%">
								<font color="red">*</font>学号：
							</th>
							<td width="35%">
								<logic:present name="showstu">
									<html:text name='rs' property="save_xh" styleId="xh"
										readonly="true"
										onkeypress="autoFillStuInfo(event.keyCode,this)" />
								</logic:present>
								<logic:notPresent name="showstu">
									<html:text name='rs' property="save_xh" styleId="xh"
										onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										选择
									</button>
								</logic:notPresent>
							</td>
							<th width="15%">
								年度
							</th>
							<td width="35%">
								<bean:write name="rs" property="nd" />
								<input type="hidden" name="save_nd" id="nd"
									value="<bean:write name="rs" property="nd" />" />
							</td>
						</tr>
						<tr style="height:23px">
							<th>
								姓名
							</th>
							<td align="left">
								<bean:write name="rs" property="xm" />
							</td>
							<th>
								学年
							</th>
							<td align="left">
								<bean:write name="rs" property="xn" />
								<input type="hidden" name="save_xn" id="xn"
									value="<bean:write name="rs" property="xn" />" />
							</td>
						</tr>
						<tr style="height:23px">
							<th>
								性别
							</th>
							<td align="left">
								<bean:write name="rs" property="xb" />
							</td>
							<th>
								学期
							</th>
							<td align="left">
								<bean:write name="rs" property="xq" />
								<input type="hidden" name="save_xq" id="xq"
									value="<bean:write name="rs" property="xq" />" />
							</td>

						</tr>
						<tr style="height:23px">
							<th>
								年级
							</th>
							<td align="left">
								<bean:write name="rs" property="nj" />
							</td>
							<th>
								综合测评成绩
							</th>
							<td>
								<bean:write name="rs" property="zf" />
							</td>

						</tr>
						<tr style="height:23px">
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<bean:write name="rs" property="xymc" />
							</td>
							<th>
								综合测评班级排名
							</th>
							<td align="left">
								<bean:write name="rs" property="zfpm" />
							</td>
						</tr>
						<tr style="height:23px">
							<th>
								专业
							</th>
							<td align="left">
								<bean:write name="rs" property="zymc" />
							</td>
							<th>
								同年级同专业综合测评排名
							</th>
							<td align="left">
								<bean:write name="rs" property="zyzfpm" />
							</td>

						</tr>
						<tr style="height:23px">
							<th>
								班级
							</th>
							<td align="left">
								<bean:write name="rs" property="bjmc" />
							</td>
							<th>
								学习成绩
							</th>
							<td align="left">
								<bean:write name="rs" property="cj" />
							</td>
						</tr>
						<tr style="height:23px">
							<th>
								班级人数
							</th>
							<td align="left">
								<bean:write name="rs" property="bjrs" />
							</td>
							<th>
								学习成绩班级排名
							</th>
							<td align="left">
								<bean:write name="rs" property="cjpm" />
							</td>
						</tr>
						<tr style="height:23px">
							<th>
								出生年月
							</th>
							<td align="left">
								<bean:write name="rs" property="csrq" />
							</td>
							<th>
								同年级同专业学习成绩排名
							</th>
							<td align="left">
								<bean:write name="rs" property="zycjpm" />
							</td>
						</tr>
						<tr style="height:23px">
							<th>
								籍 贯
							</th>
							<td align="left">
								<bean:write name="rs" property="jg" />
							</td>
							<th>
								体质健康平均成绩
							</th>
							<td align="left">
								<bean:write name="rs" property="tzjkcj" />
							</td>
						</tr>
						<tr style="height:23px">
							<th>
								政治面貌
							</th>
							<td align="left">
								<bean:write name="rs" property="zzmmmc" />
							</td>
							<th>
								是否有违纪受处分
							</th>
							<td align="left">
								<bean:write name="rs" property="sfwj" />
							</td>
						</tr>
						<tr style="height:23px">
							<th>
								<font color="red">*</font>荣誉称号
							</th>
							<td align="left" colspan="3">
								<html:select property="save_rychdm" styleId="rychdm"
									styleClass="select" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="rychList" property="rychdm"
										labelProperty="rychmc" />
								</html:select>
							</td>
						</tr>
						<tr style="height:23px">
							<th>
								申报材料
							</th>
							<td align="left" colspan="3">
								<html:textarea property="save_zysj" styleId="zysj"
									styleClass="inputtext" rows="5" style="width:98%"
									onblur="chLeng(this,'500')"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td align="left" colspan="3">
								<html:textarea property="save_bz" styleId="bz"
									style="width: 98%" styleClass="inputtext" rows="5"
									onblur="chLeng(this,'500')"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<!-- 保存后提示页面 -->
			<logic:present name="inserted">
				<logic:equal value="yes" name="inserted">
					<script>
					alert('操作成功！');
				</script>
				</logic:equal>
				<logic:equal value="no" name="inserted">
					<input id="msg" name="" value="${message}" type="hidden" />
					<script>
					alert(document.getElementById("msg").value);
				</script>
				</logic:equal>
			</logic:present>
			<logic:present name="failinfo">
				<input id="failinfoMsg" name="" value="${message}" type="hidden" />
				<script>
					alert(document.getElementById("failinfoMsg").value);
				</script>
			</logic:present>
			<logic:present name="cjFlag">
				<script>
				alert('学生成绩表中无该生课程成绩,请先导入成绩！');
			</script>
			</logic:present>
		</html:form>
	</body>
</html>
