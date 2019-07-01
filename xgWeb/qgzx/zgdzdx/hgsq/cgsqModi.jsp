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
		<input type="hidden" id="disableEle" name="disableEle" value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc-kh" />
		<input type="hidden" id="modi" name="modi" value="yes" />
		<input type="hidden" id="url" name="url" value="/qgzxcgsqModi.do" />
		<input type="hidden" id="isModi" name="isModi" value="modi"/>
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>勤工助学 - 岗位审核 - 修改申请表</a>
			</p>
		</div>

		<logic:empty name="rs">
			<br />
			<br />
			<p align="center">
				有错误发生！
			</p>
		</logic:empty>
		<logic:notEmpty name="rs">
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
					<th><span class="red">*</span>学号</th>
					<td>
						<input type="hidden" name="xh" id="xh" value="${rs.xh}"/>
						<bean:write name='rs' property="xh" />
					</td>
					<th>姓名</th>
					<td>
						<bean:write name='rs' property="xm" />
					</td>
				</tr>

				<tr>
					<th><span class="red">*</span>辞去岗位</th>
					<td>
						<input type="hidden" name="gwdmgwsbsj" id="gwdmgwsbsj"  value="${rs.gwdmgwsbsj}"/>
						<bean:write name='rs' property='gwdm'/>
					</td>
					<th>政治面貌</th>
					<td>
						<bean:write name='rs' property="zzmmmc" />
					</td>
				</tr>
				<tr>
					<th>家庭住址</th>
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
					<th>是否贫困生</th>
					<td>
						<html:text name="rs" property="sfpks" readonly="true"></html:text>
					</td>
					<th>班级</th>
					<td>
						<bean:write name='rs' property="bjmc" />
					</td>
				</tr>
				<tr>
					<th>本人联系电话</th>
					<td colspan="3">
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
			            <button type="button" class="button2" onclick="zgdzdx_cg_chkisDataExist('xh-xmdm');">
							修 改
						</button>
						<button type="button" class="button2" onclick="Close();return false;">
							关 闭
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		</logic:notEmpty>

		<logic:notEmpty name="inserted">
			<logic:equal name="inserted" value="ok">
				<script>
			   		alert("修改成功！");
			    </script>
			</logic:equal>
			<logic:equal name="inserted" value="no">
				<script>
					var html_reason= '<bean:write name="reason"/>';
   					alert("修改失败！"+html_reason);
   				</script>
			</logic:equal>
		</logic:notEmpty>

		</html:form>
	</body>
</html>
