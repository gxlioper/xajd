<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	
	<script	type="text/javascript">
		function cpwjSave(){
			saveData('bjlh_fdycpwj.do?method=cpwjglEdit&doType=${doType}_save','wjmc-fbrq');
		}
	</script>
</head>
<body onload="">
	<html:form action="/bjlh_fdycpwj" method="post">
		<html:hidden property="wjid" name="rs" styleId="wjid"/>
		<%--<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>床位信息维护</a>
			</p>
		</div>		
		--%>
		
		<div class="tab">
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>测评问卷维护</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="16%">
					<font color="red">*</font>学年
				</th>
				<td align="left" width="30%" nowrap="nowrap">
					<logic:equal value="add" name="doType">
						<html:select property="xn" name="rs">
								<html:options collection="addxnList" property="xn" labelProperty="xn" />
						</html:select>
					</logic:equal>
					<logic:equal value="update" name="doType">
						${rs.xn }
					</logic:equal>
					
				</td>
				<th width="16%"><font color="red">*</font>问卷名称</th>
				<td width="38%">
						<html:text property="wjmc" name="rs" styleId="wjmc" maxlength="100"></html:text>
				</td>
			</tr>

			<tr>
				
				<th width="16%">
					<font color="red">*</font>是否启用				
				</th>
				<td width="34%">
					<html:radio property="sfqy" name="rs" value="是">是</html:radio>
					<html:radio property="sfqy" name="rs" value="否">否</html:radio>
				</td>
				<th>
					<font color="red">*</font>发布日期
				</th>
				<td>
						<html:text property="fbrq" name="rs" styleId="fbrq" onclick="return showCalendar(this.id,'yyyy-MM-dd')"></html:text>
					<span id="ts_cwh" style="color: blue"></span>
				</td>
			</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button type="button" name="提交" id="buttonSave"  onclick="cpwjSave()">保存</button>
<%--			            <button type="button" name="关闭" id="buttonClose" onclick="window.close();return false;">关闭</button>--%>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
	<logic:present name="back">
		<script type="text/javascript">
			alertInfo("${back}",function(){
				if(window.dialogArguments){
					if(window.dialogArguments.document.getElementById("search_go")){
						dialogArgumentsQueryChick();
					}
					window.close();
				}
			});
		</script>
	</logic:present>
</body>
</html>
