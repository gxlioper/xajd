<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="/xgxt/dwr/interface/getPjpyDao.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	</head>
<body style="font-size:14px"  onload="">
	<html:form action="/zjlgPjpyylxfb" method="post">
		<input type="hidden" id="userType" name="userType"
			value="<bean:write name="userType" scope="session"/>" />
		<input type="hidden" id="isFdy" name="isFdy"
			value="<bean:write name="fdyQx" scope="session"/>" />
		<input type="hidden" id="userName" name="userName"
			value="<bean:write name="userName" scope="session"/>" />
		<input type="hidden" name="njV" id="njV"/>
		<input type="hidden" name="xyV" id="xyV"/>
		<input type="hidden" name="zyV" id="zyV"/>
		<input type="hidden" name="bjV" id="bjV"/>
		<input type="hidden" name="save_xn" value="${rs.xn }" />
		<input type="hidden" name="save_bjdm" value="${rs.bjdm }"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<input type="hidden" name="message" id="message" value="${message }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>评奖评优 - 审核 - 优良学风班级审核</a>
			</p>
		</div>
		<div class="tab">
		<table width="100%" class="formlist">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>单个审核</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th width="25%">
					班级代码
				</th>
				<td width="25%">
					${rs.bjdm }
				</td>
				<th width="25%">
					学年
				</th>
				<td width="25%">
					${rs.xn }
				</td>
			</tr>
			<tr>
				<th>
					年级
				</th>
				<td align="left">
					${rs.nj}
				</td>
				<th>
					班级名称
				</th>
				<td align="left">
					${rs.bjmc}
				</td>
			</tr>
			<tr>
				<th>
					<bean:message key="lable.xsgzyxpzxy" />
				</th>
				<td align="left">
					${rs.xymc}
				</td>
				<th>
					辅导员
				</th>
				<td align="left">
					${rs.fdyxm}
				</td>
			</tr>
			
			<tr>
				<th>
					年度考试课优秀率
				</th>
				<td align="left">
					${map.kskyxl }
				</td>
				<th>
					年度考试课及格率
				</th>
				<td align="left">
					${map.kskjgl }
				</td>
			</tr>
			<tr>
				<th>
					年度考查课优秀率
				</th>
				<td align="left">
					${map.kckyxl }
				</td>
				<th>
					年度考查课及格率
				</th>
				<td align="left">
					${map.kckjgl }
				</td>
			</tr>
			<tr>
				<th>
					一、二年级英语课程平均分
				</th>
				<td align="left">
					 ${map.ynj }&nbsp;/&nbsp;${map.enj }
				</td>
				<th>
					班级学生处分次数
				</th>
				<td align="left">
					 ${map.wjcs }
				</td>
			</tr>
			<tr>
				<th>
					班级A级寝室比率
				</th>
				<td align="left">
					  ${map.ajl }
				</td>
				<th>
					班级文明寝室比率
				</th>
				<td align="left">
					 ${map.wml }
				</td>
			</tr>
			<tr>
				<th>
					班级特色寝室比率
				</th>
				<td align="left">
					  ${map.tsl }
				</td>
				<th>
					
				</th>
				<td align="left">
					
				</td>
			</tr>
			<tr>
				<th>
					班级出勤率
				</th>
				<td align="left">
					${rs.cql}(%)
				</td>
				<th>
					三、四、五年级&nbsp;&nbsp;<br/>英语四级过425分比例
				</th>
				<td align="left">
					${rs.sjtgl}(%)
				</td>
			</tr>
			<tr>
				<th>
					计算机二级通过率
				</th>
				<td align="left">
					${rs.jsjtgl}(%)
				</td>
				<th>
					考研报考率
				</th>
				<td align="left">
					${rs.bkl}(%)
				</td>
			</tr>
			<tr>
				<th>
					考研上线率
				</th>
				<td align="left">
					${rs.sxl}(%)
				</td>
				<logic:equal value="xy" name="userType">
					<th>
						<bean:message key="lable.xsgzyxpzxy" />审核
					</th>
					<td align="left">
						<html:select property="save_xysh" styleId="xysh" value="${rs.xysh}">
							<html:option value="未审核">未审核</html:option>
							<html:option value="通过">通过</html:option>
							<html:option value="不通过">不通过</html:option>
						</html:select>
					</td>
				</logic:equal>
				<logic:notEqual value="xy" name="userType">
					<th>
						学校审核
					</th>
					<td align="left">
						<html:select property="save_xxsh" styleId="xxsh" value="${rs.xxsh}">
							<html:option value="未审核">未审核</html:option>
							<html:option value="通过">通过</html:option>
							<html:option value="不通过">不通过</html:option>
						</html:select>
					</td>
				</logic:notEqual>
			</tr>
			<logic:notEqual value="xy" name="userType">
			<tr>
				<th>
					班级评价排名
				</th>
				<td align="left">
					<html:text property="save_pjpm" styleId="save_pjpm" maxlength="3" onkeyup="chkIsNum(this)" style="width:50px" value="${rs.pjpm}"></html:text>
				</td>
				<th>
					
				</th>
				<td align="left">
					
				</td>
			</tr>
			</logic:notEqual>
			<tr>
				<th>
					班级学风建设&nbsp;<br/>主题班会活动
					<br/>
					<font color="red">(限制在1000以内)&nbsp;</font>
				</th>
				<td align="left" colspan="3">
					<html:textarea property="bhhd" styleId="bhhd" rows="5" style="width:99%" value="${rs.bhhd}" disabled="true"></html:textarea>
				</td>
			</tr>
			<tr>
				<th>
					任课教师对&nbsp;<br/>班级学风评价
					<br/>
					<font color="red">(限制在1000以内)&nbsp;</font>
				</th>
				<td align="left" colspan="3">
					<html:textarea property="jsbjpj" styleId="jsbjpj" rows="5" style="width:99%" disabled="true" value="${rs.jsbjpj}"></html:textarea>
				</td>
			</tr>
			<tr>
				<th>
					备注
					<br/>
					<font color="red">(限制在1000以内)&nbsp;</font>
				</th>
				<td align="left" colspan="3">
					<html:textarea property="bz" styleId="bz" rows="5" style="width:99%" onkeyup="checkLen(this,1000)" disabled="true" value="${rs.bz}"></html:textarea>
				</td>
			</tr>
			<logic:equal value="xy" name="userType">
			<tr>
				<th>
					<bean:message key="lable.xsgzyxpzxy" />审核意见
					<br/>
					<font color="red">(限制在1000以内)&nbsp;</font>
				</th>
				<td align="left" colspan="3">
					<html:textarea property="save_xyyj" styleId="save_xyyj" rows="5" style="width:99%" onkeyup="checkLen(this,1000)" value="${rs.xyyj}"></html:textarea>
				</td>
			</tr>
			</logic:equal>
			<logic:notEqual value="xy" name="userType">
				<tr>
				<th>
					学校审核意见
					<br/>
					<font color="red">(限制在1000以内)&nbsp;</font>
				</th>
				<td align="left" colspan="3">
					<html:textarea property="save_xxyj" styleId="save_xxyj" rows="5" style="width:99%" onkeyup="checkLen(this,1000)" value="${rs.xxyj}"></html:textarea>
				</td>
			</tr>
			</logic:notEqual>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
		             <logic:notEqual value="no" name="writable">
					  <button type="button" id="btn_save" onclick="saveinfo('pjpy_zjlg_ylxfbdgsh.do?act=save','')">
						保存
					  </button>
					 </logic:notEqual>
					  <button type="button" name="关闭" onclick="window.close();return false;">关闭</button>
		          </div></td>
		      </tr>
		    </tfoot>
		</table>
		</div>
	</html:form>
</body>
<logic:notEmpty name="result">
			<script>
				alert("" + $('message').value);
				if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
						Close();
						window.dialogArguments.document.getElementById('search_go').click();	
				}
			</script>
		</logic:notEmpty>
</html>
