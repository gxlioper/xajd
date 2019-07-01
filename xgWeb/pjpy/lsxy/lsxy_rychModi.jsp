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
			
			function save(){
				var xxsh= val('xxsh');
				var xysh= val('xysh');
				
				if(xxsh != "未审核" || xysh != "未审核"){
					alert("信息已经在审核中，暂时不能修改");
					return false;
				}
				refreshForm('pjpyLsxy.do?method=rychModi&act=save');
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
			<input type="hidden" name="pkValue" id="pkValue"
				value="${rs.pkValue}" />
			<input type="hidden" name="xysh" id="xysh" value="${rs.xysh}" />
			<input type="hidden" name="xxsh" id="xxsh" value="${rs.xxsh}" />
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
								<span>修改申请信息</span>
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
									<button name="提交" onclick="save();return false;">
										保存
									</button>
									<button class="button2" id="btn_dy" onclick="print()">
										打印申请表
									</button>
									<button name="关闭" onclick="window.close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr style="height:23px">
							<th width="15%">
								<font color="red">*</font>学号
							</th>
							<td  width="35%">
								<html:text name='rs' property="save_xh" styleId="xh"
									readonly="true" />
							</td>
							<th width="15%">
								年度
							</th>
							<td align="left">
								<bean:write name="rs" property="nd" />
								<input type="hidden" name="save_nd" id="nd" value="${rs.nd}" />
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
								<input type="hidden" name="save_xn" id="xn" value="${rs.xn}" />
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
								<input type="hidden" name="save_xq" id="xq" value="${rs.xq}" />
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
							<td align="left">
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
								综合测评排名
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
								学习成绩排名
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
								体质健康成绩
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
								<html:select name='rs' property="rychdm" styleId="rychdm"
									styleClass="select" style="width:150px" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="rychList" property="rychdm"
										labelProperty="rychmc" />
								</html:select>
								<input type="hidden" id="save_rychdm" name="save_rychdm"
									value="${rs.rychdm}" />
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
									onblur="chLeng(this,'500')" name="rs"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- 保存后提示页面 -->
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
					alert('操作成功！');
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<input id="msg" name="" value="${message}" />
					<script>
					alert(document.getElementById("msg").value);
				</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
