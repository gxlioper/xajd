<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<title><bean:message key="lable.title" /></title>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
</head>
<body>
	<html:form action="jygl.do" method="post">	
	<input type="hidden" name="pkValue" value="${param.pkValue }" />
	<div class="tab_cur">
		<p class="location">
			<em>您的当前位置:</em><a>${title }</a>
		</p>
	</div>
	<div class="tab">
		<table width="100%" border="0" class="formlist">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>就业援助信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th width="20%">
					<div>
						<font color="red">*</font>学号
					</div>
				</th>
				<td width="30%">
					<html:text property="save_xh" styleId="xh" value="${rs.xh }" readonly="true"/>	
				</td>
				
				<th width="20%">姓名</th>
				<td width="30%">${rs.xm }</td>
			</tr>
			<tr>
				<th>政治面貌</th>
				<td>${rs.zzmmmc }</td>
				<th><bean:message key="lable.xb" /></th>
				<td>${rs.xymc }</td>
			</tr>
			<tr>
				<th>专业</th>
				<td>${rs.zymc }</td>
				<th>班级</th>
				<td>${rs.bjmc }</td>
			</tr>
			<tr>
				<th>推荐面试单位</th>
				<td><input type="text" name="save_tjdw" value="${rs.tjdw}" <logic:notEqual name="doType" value="modi">readonly="true"</logic:notEqual>></input></td>
				<th>援助结果</th>
				<td>
				<logic:equal value="modi" name="doType">
					<html:select property="save_yzjg" value="${rs.yzjg}">
						<html:option value=""></html:option>
						<html:options collection="yzjgList" property="xmdm" labelProperty="xmmc"/>
					</html:select>
				</logic:equal>
				
				<logic:notEqual value="modi" name="doType">
					${rs.yzjgmc}
				</logic:notEqual>
				</td>
			</tr>
			<tr>
				<th>学校审核</th>
				<td>
				<logic:equal value="shone" name="doType">
					<html:select property="save_xxsh" value="${rs.xxsh }">
						<html:option value="未审核">未审核</html:option>
						<html:option value="通过">通过</html:option>
						<html:option value="不通过">不通过</html:option>
					</html:select>
				</logic:equal>
				
				<logic:notEqual value="shone" name="doType">
					<input type="hidden" name="save_xxsh" value="未审核"/>
					${rs.xxsh }
				</logic:notEqual>
				</td>
				
				<th></th><td></td>
			</tr>
			<tr>
				<th>参与学生<br/>
					<font color="red">(限制在800字内)</font>
				</th>
				<td colspan="3">
					<textarea name='save_cyxs' <logic:notEqual name="doType" value="modi">readonly="true"</logic:notEqual>
						style="word-break:break-all;width:99%"
						onblur="checkLen(this,800)" rows='8'>${rs.cyxs}</textarea>
				</td>
			</tr>
			<tr>
				<th>备注<br/>
					<font color="red">(限制在400字内)</font>
				</th>
				<td colspan="3">
					<textarea name='save_bz' <logic:notEqual name="doType" value="modi">readonly="true"</logic:notEqual>
						style="word-break:break-all;width:99%"
						onblur="checkLen(this,400)" rows='8'>${rs.bz}</textarea>
				</td>
			</tr>
			</tbody>

			 <tfoot>
			    <tr>
			        <td colspan="4"><logic:notEqual value="view" name="doType"><div class="bz">"<span class="red">*</span>"为必填项</div></logic:notEqual>
			          <div class="btn">
				        <logic:notEqual value="view" name="doType">
				         <button class="button2" onclick="saveDataShowTips('jygl.do?method=jyyzmodi&doType=save',
							'xh','正在处理数据，请稍候！');">
							保存
						 </button>
						</logic:notEqual>
			          	  <button name="关闭" onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			   </tfoot>
		</table>
		</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			alert(document.getElementById('message').value);
			Close();
			if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
			  window.dialogArguments.document.getElementById('search_go').click(); 
			}
		</script>
	</logic:present>
</body>
</html>
