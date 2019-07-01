<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
<script type="text/javascript">

	function saveXjbj() {
			refreshForm('ynys_xjbjmodisave.do');
			document.getElementById('btn_save').disabled = true;
	}	

</script>
</head>
<body onload="">
	<html:form action="/pjpyynyswh" method="post">
		<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
	
		
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a><bean:message key="pjpy_ynys_xjbjsq" bundle="pjpyynys" /></a>
			</p>
		</div>
			
		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
				<tr><th colspan="4" align="center">
					<span>单个修改</span>
				</th></tr>
			</thead>
			<tbody>
			<logic:equal value="10863" name="xxdm">
			<tr>
				<th align="right" style="width:15%">
					院(系)
				</th>
				<td align="left">
					<bean:write name="rs" property="xymc"/>
				</td>
				<th align="right" style="width:15%">
					年
				</th>
				<td align="left">
					<bean:write name="rs" property="nj"/>
				</td>
			</tr>
			<tr>
				<th align="right" style="width:15%">
					专业
				</th>
				<td align="left">
					<bean:write name="rs" property="zymc"/>
				</td>
				<th align="right" style="width:15%">
					班级
				</th>
				<td align="left">
					<bean:write name="rs" property="bjmc"/>
				</td>
			</tr>
			<tr>
				<th align="right" style="width:15%">
					学年
				</th>
				<td align="left">
					<html:select property="xn" styleId="xn">
						<html:options collection="xnList" property="xn" labelProperty="xn"/>
					</html:select>
				</td>
				<th align="right" style="width:15%">
					班主任
				</th>
				<td align="left">
					<html:text property="bzr" styleId="bzr"></html:text>
				</td>
			</tr>
			<tr>
				<th align="right" style="width:15%">
					班长
				</th>
				<td align="left">
					<html:text property="bzxm" styleId="bzxm"></html:text>
				</td>
				<th align="right" style="width:15%">
					班级人数
				</th>
				<td align="left">
					<html:text property="xsrs" styleId="xsrs"></html:text>
				</td>
			</tr>
			<tr>
				<th align="right" style="width:15%">
					团支书
				</th>
				<td align="left">
					<html:text property="tzs" styleId="tzs"></html:text>
				</td>
				<td align="right" style="width:15%">
					
				</td>
				<td align="left">
					
				</td>
			</tr>
			<tr>
				<th align="right" style="width:15%">
					班内有无不合格寝室
				</th>
				<td align="left">
					<html:radio property="bhgqs" value="0">有</html:radio>
					<html:radio property="bhgqs" value="1">无</html:radio>
				</td>
				<th align="right" style="width:15%">
					有无同学受过行政,党,团纪处分
				</th>
				<td align="left">
					<html:radio property="ywcf" value="0">有</html:radio>
					<html:radio property="ywcf" value="1">无</html:radio>
				</td>
			</tr>
			<tr>
				<th align="right" style="width:15%">
					班级集体荣誉
				</th>
				<td align="left" colspan="3">
					<html:textarea property="bjry" styleId="bjry" style="width:95%" rows="5"></html:textarea>
				</td>
				
			</tr>
			<tr>
				<th align="right" style="width:15%">
					主要成绩简介
				</th>
				<td align="left" colspan="3">
					<html:textarea property="zysj" styleId="zysj" style="width:95%" rows="5"></html:textarea>
				</td>
			</tr>
			</logic:equal>
			<logic:notEqual value="10863" name="xxdm">
				<logic:equal value="10690" name="xxdm">
				<tr height="22px">
				<th align="right" style="width:15%">
					院(系)
				</th>
				<td align="left">
					<bean:write name="rs" property="xymc"/>
				</td>
				<th align="right" style="width:15%">
					年级
				</th>
				<td align="left">
					<bean:write name="rs" property="nj"/>
				</td>
			</tr>
			<tr height="22px">
				<th align="right">
					专业
				</th>
				<td align="left">
					<bean:write name="rs" property="zymc"/>
				</td>
				<th align="right">
					班级
				</th>
				<td align="left">
					<bean:write name="rs" property="bjmc"/>
				</td>
			</tr>
			<tr height="22px">
				<th align="right">
					学年
				</th>
				<td align="left">
					<bean:write name="rs" property="xn"/>
				</td>
				<th align="right">
					班级荣誉
				</th>
				<td align="left">
					先进班集体
				</td>
			</tr>
			<tr height="22px">
				<th align="right">
					班级人数
				</th>
				<td align="left">
					<html:text property="bjrs" styleId="bjrs" maxlength="3"
						onkeypress="chkData1();" styleClass="input"></html:text>
				</td>
				<th align="right">
					班主
				</th>
				<td align="left">
					<html:text property="bzr" styleId="bzr" maxlength="15"
						styleClass="input"></html:text>
				</td>
			</tr>
			<tr height="22px">
				<th align="right">
					班级达标情况
				</th>
				<td align="left" colspan="3">
					<html:textarea property="bjdbqk" styleId="bjdbqk" rows="8"
						style="width:95%"></html:textarea>
				</td>
			</tr>
			<tr height="22px">
				<th align="right">
					班主任意见
				</th>
				<td align="left" colspan="3">
					<html:textarea property="bzryj" styleId="bzryj" rows="5"
						style="width:95%"></html:textarea>
				</td>
			</tr>
			<tr height="22px">
				<th align="right">
					辅导员意见
				</th>
				<td align="left" colspan="3">
					<html:textarea property="fdyyj" styleId="fdyyj" rows="5"
						style="width:95%"></html:textarea>
				</td>
			</tr>
				</logic:equal>
				<logic:notEqual value="10690" name="xxdm">
					<tr height="22px">
				<th align="right" style="width:15%">
					院(系)
				</th>
				<td align="left">
					<bean:write name="rs" property="xymc"/>
				</td>
				<th align="right" style="width:15%">
					年级
				</th>
				<td align="left">
					<bean:write name="rs" property="nj"/>
				</td>
			</tr>
			<tr height="22px">
				<th align="right" style="width:15%">
					专业
				</th>
				<td align="left">
					<bean:write name="rs" property="zymc"/>
				</td>
				<th align="right" style="width:15%">
					班级
				</th>
				<td align="left">
					<bean:write name="rs" property="bjmc"/>
				</td>
			</tr>
			<tr height="22px">
				<th align="right" style="width:15%">
					学年
				</th>
				<td align="left">
					<html:text property="xn" styleId="xn" readonly="true"></html:text>
				</td>
				<th align="right" style="width:15%">
					班主任
				</th>
				<td align="left">
					<html:text property="bzr" styleId="bzr"></html:text>
				</td>
			</tr>
			<tr height="22px">
				<th align="right" style="width:15%">
					班长
				</th>
				<td align="left">
					<html:text property="bzxm" styleId="bzxm"></html:text>
				</td>
				<th align="right" style="width:15%">
					班级人数
				</th>
				<td align="left">
					<html:text property="xsrs" styleId="xsrs"></html:text>
				</td>
			</tr>
			
			<tr height="22px">
				
				<logic:equal value="12682" name="xxdm">
				<th align="right" style="width:15%">
					集体称号
				</th>
				<td align="left" colspan="">
					<html:text property="jtch" styleId="jtch" style="width:200px"></html:text>
				</td>
				</logic:equal>
				<logic:notEqual value="12682" name="xxdm">
				<th align="right" style="width:15%">
					班级集体荣誉
				</th>
				<td align="left" colspan="">
					${rs.rychmc }
				</td>
				</logic:notEqual>
			</tr>
			<tr height="22px">
				<th align="right" style="width:15%">
					主要事迹
				</th>
				<td align="left" colspan="3">
					<html:textarea property="zysj" styleId="zysj" style="width:95%" rows="5"></html:textarea>
				</td>
			</tr>
			<logic:equal value="10355" name="xxdm">
			<tr height="22px">
				<th align="right" style="width:15%">
					备注<font color="red">(班级学生<br/>受处分,旷课,<br/>体育达标情况)</font>
				</th>
				<td align="left" colspan="3">
					<html:textarea property="bz" styleId="bz" 
					style="width:95%" rows="5" readonly="true"></html:textarea>
				</td>
			</tr>
			</logic:equal>
				</logic:notEqual>
			</logic:notEqual>
			</tbody>
			
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			         <logic:notPresent name="act">
			          		<button type="button" name="提交" id="btn_save"
			          		onclick="saveXjbj()">保 存</button>
			      	 </logic:notPresent>
			            <button type="button" name="关闭" id="btn_close" onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
