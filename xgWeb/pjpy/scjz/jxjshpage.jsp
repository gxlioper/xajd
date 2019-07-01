<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyscjzJs.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
<script type="text/javascript">
<!--
	function save() {
		
		if (document.getElementById('ys').value==document.getElementById('yesNo').value&&document.getElementById('cj').value==document.getElementById('cxcj').value&&document.getElementById('yj').value==document.getElementById('shyj').value) {alert('未做任何修改！');return;} refreshForm('pjpy_scjz_jxjsh.do');
	}
//-->
</script>
<body>
	<html:form action="/pjpyscjzwh" method="post">
	<input type="hidden" name="act" value="save"/>
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
	<input type="hidden" name="failinfo" id="failinfo" value="${failinfo }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置：评奖评优 - 审核 - 奖学金审核
			</div>
		</div>
		<input type="hidden" name="pkValue" value="${pkValue }"/>
		<table class="tbstyle" width="100%"> 
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						单个审核
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right" width="25%">
					<font color="red">*</font>学号：
				</td>
				<td align="left" width="25%">
					${rs.xh }
				</td>
				<td align="right" width="25%">
					学年：
				</td>
				<td align="left" width="25%">
					${xn }
					<input type="hidden" name="xn" id="xn" value="${xn }">
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					姓名：
				</td>
				<td align="left">
					<bean:write name="rs" property="xm" />
				</td>
				<td align="right">
					学期：
				</td>
				<td align="left">
					${xqmc }
					
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					性别：
				</td>
				<td align="left">
					<bean:write name="rs" property="xb" />
				</td>
				<td align="right">
					<font color="red">*</font>奖学金：
				</td>
				<td align="left">
					${rs.jxjmc }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					年级：
				</td>
				<td align="left">
					<bean:write name="rs" property="nj" />
				</td>
				<td align="right">
					奖学金类别：
				</td>
				<td align="left">
					${rs.jxjlb }
					<input type="hidden" name="jlje" value="${rs.jlje }"/>
					<input type="hidden" name="jxjdm" value="${rs.jxjdm }"/>
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					系：
				</td>
				<td align="left">
					<bean:write name="rs" property="xymc" />
				</td>
				<td align="right">
					奖励金额：
				</td>
				<td align="left">
					${rs.jlje }
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					专业：
				</td>
				<td align="left">
					<bean:write name="rs" property="zymc" />
				</td>
				<td align="right">
					担任职务：
				</td>
				<td align="left">
					${rs.drzw }
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					班级：
				</td>
				<td align="left">
					<bean:write name="rs" property="bjmc" />
				</td>
				<td align="right">
					手机号码：
				</td>
				<td align="left">
					${rs.sjhm }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					外语水平：
				</td>
				<td align="left">
					${rs.wysp }
				</td>
				<td align="right">
					计算机水平：
				</td>
				<td align="left">
					${rs.jsjsp }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					银行卡所属：
				</td>
				<td align="left">
					${rs.khss }
				</td>
				<td align="right">
					卡号：
				</td>
				<td align="left">
					${rs.kh }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					单科最低成绩：
				</td>
				<td align="left">
					${rss.zdcj }
					
				</td>
				<td align="right">
					平均成绩：
				</td>
				<td align="left">
					${rss.pjcj }
					
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					学习成绩排序：
				</td>
				<td align="left" colspan="3">
					班级第&nbsp;${rss.bjpm }&nbsp;名,专业第&nbsp;${rss.zypm }&nbsp;名
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					有无处分：
				</td>
				<td align="left" colspan="3">
					<logic:empty name="cfList">
								<p align="">
									无
								</p>
							</logic:empty>
							<logic:notEmpty name="cfList">
								<logic:iterate name="cfList" id="s">
									<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>年&nbsp;&nbsp;
									<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>&nbsp;&nbsp;
									(<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>)
									<logic:iterate id="v" name="s" offset="3" length="1"><bean:write name="v"/></logic:iterate><br/>
								</logic:iterate>
							</logic:notEmpty>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					校风检查情况：
				</td>
				<td align="left" colspan="">
				&nbsp;
				</td>
				<td align="right">
					卫生通报情况
				</td>
				<td align="left" colspan="">
				&nbsp;	
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					操作成绩：
				</td>
				<td align="left" colspan="">
					
					<logic:equal value="true" name="isFdy">
										<html:select property="cxcj" styleId="cxcj" >
						<html:option value=""></html:option>
						<html:option value="优">优</html:option>
						<html:option value="良">良</html:option>
						<html:option value="中">中</html:option>
						<html:option value="及格">及格</html:option>
						<html:option value="不及格">不及格</html:option>
					</html:select>
					</logic:equal>
					<logic:notEqual value="true" name="isFdy">
					${rs.cxcj }
					<input type="hidden" id="cxcj" value="${rs.cxcj }"/>
					</logic:notEqual>
				</td>
				<td align="right">
					<logic:equal value="xy" name="userType"><logic:equal value="true" name="isFdy">辅导员</logic:equal><logic:notEqual value="true" name="isFdy">系</logic:notEqual> </logic:equal><logic:notEqual value="xy" name="userType">院（校）</logic:notEqual>审核：
				</td>
				<td align="left" colspan="">
					<html:select property="yesNo" styleId="yesNo">
						<html:option value=""></html:option>
						<html:option value="通过"></html:option>
						<html:option value="不通过"></html:option>
						<html:option value="未审核"></html:option>
					</html:select>
				</td>
				<input type="hidden" name="ys" id="ys" value="${ys }"/>
				<input type="hidden" name="yj" id="yj" value="${yj }"/>
				<input type="hidden" name="cj" id="cj" value="${cj }"/>
			</tr>
			<tr>
				<td align="right">
					<logic:equal value="xy" name="userType"><logic:equal value="true" name="isFdy">辅导员</logic:equal><logic:notEqual value="true" name="isFdy">系</logic:notEqual> </logic:equal><logic:notEqual value="xy" name="userType">院（校）</logic:notEqual>审核意见：
				</td>
				<td align="left" colspan="3"><html:textarea property="shyj" styleId="shyj" rows="3" style="width:95%"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<button class="button2" id="btn_save" 
						onclick="save();return false;"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert('操作成功！');
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert(''+document.getElementById('failinfo').value);
					
				</script>
			</logic:equal>
		</logic:present>
	</html:form>
</body>
