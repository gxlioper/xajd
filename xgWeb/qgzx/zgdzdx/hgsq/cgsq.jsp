<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="/xgxt/dwr/interface/cqkjFunc.js"></script>
</head>
	<body>
		<html:form action="/data_search" method="post">
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
		<input type="hidden" id="disableEle" name="disableEle" value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc-kh" />
		<input type="hidden" id="url" name="url" value="/qgzxCgsq.do" />
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>勤工助学 - 申请 - 辞岗申请</a>
			</p>
		</div>
			<logic:equal name="rs" property="stuExists" value="no">
				<script>
   				alert("您输入的学号无效!");
   				</script>
			</logic:equal>
			
			<logic:notEqual name="gwExists" value="yes">
				<script>
  					alert("必须是勤工助学在岗学生才能申请辞岗!");
  					</script>
			</logic:notEqual>

			<div class="tab">
			<table width="100%" id="rsT" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>填写辞岗申请表</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<th><span class="red">*</span>学号</th>
						<td>
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<th><span class="red">*</span>学号</th>
						<td align="left" width="300">
							<input type="hidden" name="xh" id="xh" value="${rs.xh}"/>
							<bean:write name='rs' property="xh" />
						</td>
					</logic:equal>
					<th>姓名</th>
					<td>
						<bean:write name='rs' property="xm" />
					</td>
				</tr>

				<tr>
					<th><span class="red">*</span>辞去岗位</th>
					<td>
						<input type="hidden" name="gwdmgwsbsj" id="gwdmgwsbsj" value="${rs.gwdmgwsbsj}"/>
						<bean:write name='rs' property='gwdm'/>
					</td>
					<th>政治面貌</th>
					<td>
						<bean:write name='rs' property="zzmmmc" />
					</td>
				</tr>
				<tr>
					<th>住址</th>
					<td>
						<bean:write name='rs' property="jtdz" />
					</td>
					<th>性别</th>
					<td>
						<bean:write name='rs' property="xb" />
					</td>
				</tr>
				<tr>
					<th>学年</th>
					<td>
						<html:text name="rs" property="xn" readonly="true"></html:text>
					</td>
					<th>年级</th>
					<td>
						<bean:write name='rs' property="nj" />
					</td>
				</tr>
				<tr>
					<th>学期</th>
					<td>
						${rs.xqmc}
						<html:hidden name="rs" property="xq"></html:hidden>
					</td>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<bean:write name='rs' property="xymc" />
					</td>
				</tr>
				<tr>
					<th>年度</th>
					<td>
						<html:text name="rs" property="nd" readonly="true"></html:text>
					</td>						
					<th>专业</th>
					<td>
						<bean:write name='rs' property="zymc" />
					</td>
				</tr>
				<tr>
					<th>负责人</th>
					<td>
						<input type="text" id="fzr" name="fzr" readonly="readonly"
							value="<bean:write name="rs" property="fzr"/>"/>
					</td>
					<th>班级</th>
					<td>
						<bean:write name='rs' property="bjmc" />
					</td>
				</tr>
				<tr>
					<th>联系电话</th>
					<td>
						<input type="text" id="gwlxdh" name="gwlxdh" readonly="readonly"
							value="<bean:write name="rs" property="gwlxdh"/>"
							disabled="disabled" />
					</td>
					<th>是否贫困生</th>
					<td width="25%">
						<bean:write name='rs' property="sfpks" />
					</td>
				</tr>
				<tr>
					<th>本人联系电话</th>
					<td colspan="4">
						<html:text property="lxdh" name="rs" styleId="lxdh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>辞岗理由</th>
					<td colspan="3">
						<html:textarea name='rs' property='sqly' styleId="sqly"
							style="width:99%" rows='5' onblur="chLeng(this,250)"/>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%" class="formlist">
							<tr>
								<td width="20">用<br/>人<br/>单<br/>位<br/>意<br/>见</td>
								<td valign="bottom">签名盖章：<br/><br/>日期：</td>
								<td width="20"><bean:message key="lable.xsgzyxpzxy" /><br/>学<br/>工<br/>组<br/>意<br/>见</td>
								<td valign="bottom">签名盖章：<br/><br/>日期：</td>
								<td width="20">勤<br/>工<br/>助<br/>学<br/>办<br/>公<br/>室<br/>意<br/>见</td>
								<td valign="bottom">签名盖章：<br/><br/>日期：</td>
							</tr>
						</table>
					</td>
				</tr>				
				<tr>
					<th>备注</th>
					<td colspan="3">
						<html:textarea name='rs' property='bz' styleId="bz"
							style="width:99%" rows='5' onblur="chLeng(this,60)"/>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			            <button type="button" class="button2" onclick="zgdzdx_cg_chkisDataExist('xh-sqly');">
							提 交 申 请
						</button>
						<logic:notPresent name="zdy">
						<button type="button" class="button2" onclick="expAppTab('rsT','勤工助学岗位申请表','')">
							打 印 预 览
						</button>
						</logic:notPresent>
						<logic:present name="zdy">
						<button type="button" class="button2"
							onclick="printReport('qgzx_bb_gwsqb.do?gwdm=')">
							打 印 预 览
						</button>
						</logic:present>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
			</div>

			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    					alert("申请成功！");
    				</script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
						var html_reason= '<bean:write name="reason"/>';
    					alert("申请失败！"+html_reason);
    				</script>
				</logic:equal>
			</logic:notEmpty>

		</html:form>
	</body>
</html>
