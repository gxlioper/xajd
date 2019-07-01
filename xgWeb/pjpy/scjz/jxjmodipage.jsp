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
		var jxjdm = document.getElementById('jxjdm').value;
		var zdcj = document.getElementById('zdcj').value;
		var pjcj = document.getElementById('pjcj').value;
		if (document.getElementById('xh').value==''||jxjdm=='') {
			alert('请将带*号的内容填完整！');
			return;	
		}
		pjpyZjsyzy.jxjsqTj(jxjdm,zdcj,pjcj,function(data){
 		if (data) {
 			saveinfo('pjpy_scjz_jxjmodi.do?act=save','xh-jxjdm');
			BatAlert.showTips('正在操作，请等待...');
 		} else {
 			alert('该生单科成绩或平均成绩未达到申请条件！');
 			return;
 		}
 	});
	}
	function getJxjleandje() {
		var jxjdm = document.getElementById('jxjdm').value;
		pjpyscjzJs.getJxjlbandje(jxjdm,function(data){
			if (data != '' && data != null) {
				var array = data.split("-");
				document.getElementById('jxjlb').innerText=array[0];	
				document.getElementById('jlje').innerText=array[1];	
			}
		});
	}
//-->
</script>
<body>
	<html:form action="/pjpyscjzwh" method="post">
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置：评奖评优 - 奖学金申请 - 填写申请表
			</div>
		</div>
		<table class="tbstyle" width="100%"> 
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						单个修改
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right" width="25%">
					<font color="red">*</font>学号：
				</td>
				<td align="left" width="25%">
						<html:text name='rs' property="xh" styleId="xh" readonly="true"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
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
					<input type="hidden" name="xq" id="xq" value="${xqmc }">
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
					<html:select property="jxjdm" styleId="jxjdm" onchange="getJxjleandje()">
						<html:option value=""></html:option>
						<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
					</html:select>
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
<%--					<div id="jxjlb"></div>--%>
					<html:text property="jxjlb" styleId="jxjlb" readonly="true"></html:text>
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
				<html:text property="jlje" styleId="jlje" readonly="true"></html:text>
<%--					<div id="jlje"></div>--%>
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
					<html:text property="drzw" styleId="drzw"></html:text>
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
					<html:text property="sjhm" styleId="sjhm" maxlength="12" onblur="ckinpjedata(this)"></html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					外语水平：
				</td>
				<td align="left">
					<html:select property="wysp" styleId="wysp" styleId="khss" style="width:150px">
						<html:option value=""></html:option>
						<html:option value="公共英语二级">公共英语二级</html:option>
						<html:option value="公共英语三级">公共英语三级</html:option>
						<html:option value="公共英语四级">公共英语四级</html:option>
						<html:option value="公共英语六级">公共英语六级</html:option>
						<html:option value="专业外语四级">专业外语四级</html:option>
					</html:select>
				</td>
				<td align="right">
					计算机水平：
				</td>
				<td align="left">
					<html:select property="jsjsp" styleId="jsjsp" styleId="khss" style="width:150px">
						<html:option value=""></html:option>
						<html:option value="计算机一级">计算机一级</html:option>
						<html:option value="计算机二级">计算机二级</html:option>
					</html:select>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					银行卡所属：
				</td>
				<td align="left">
					<html:select property="khss" styleId="khss" style="width:150px">
						<html:option value="工行卡号">工行卡号</html:option>
						<html:option value="邮政卡号">邮政卡号</html:option>
						<html:option value="饭卡号">饭卡号</html:option>
					</html:select>
				</td>
				<td align="right">
					卡号：
				</td>
				<td align="left">
					<html:text property="kh" styleId="kh" style="width:200px" maxlength="20" onblur="ckinpjedata(this)"></html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					单科最低成绩：
				</td>
				<td align="left">
					${rss.zdcj }
					<input type="hidden" id="zdcj" value="${rs.zdcj }"/>
				</td>
				<td align="right">
					平均成绩：
				</td>
				<td align="left">
					${rss.pjcj }
					<input type="hidden" id="pjcj" value="${rs.pjcj }"/>
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
			<tr >
				<td align="right">
					申请书&nbsp;&nbsp;<br/>
					<font color="red">（在校学习、实训、社会实践及获奖情况，限1000字内）</font>
				</td>
				<td align="left" colspan="3">
					<html:textarea property="sqly" styleId="sqly"
						styleClass="inputtext" rows="8" style="width:98%"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<button class="button2" id="btn_save" 
						onclick="save();return false;" <logic:notEmpty name="cfflag">disabled</logic:notEmpty> <logic:notEmpty name="cjflag">disabled</logic:notEmpty>
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
	
			<div align="center">
				<logic:notEmpty name="cfflag">
					<span class="style1" align="center">${cfflag }</span><br/>
				</logic:notEmpty>
				<logic:notEmpty name="cjflag">
					<span class="style1" align="center">${cjflag }</span>
				</logic:notEmpty>
			</div>
				<!-- 保存后提示页面 -->	
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert('操作成功！');
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert('操作失败！');
				</script>
			</logic:equal>
		</logic:present>
		<logic:present name="failinfo">
				<script>
					alert('该生不符奖学金申请条件！');
				</script>
			</logic:present>
		<logic:present name="cjFlag">
			<script>
					alert('学生成绩表中无该生课程成绩,请先导入成绩！');
				</script>
		</logic:present>
	</html:form>
</body>
