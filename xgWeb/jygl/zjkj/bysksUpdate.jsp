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
	<script type="text/javascript">
		function checkLqdw(){
			var sfsx = $('sfsx').value;

			if('是' == sfsx){
				$('lqdw').disabled = '';
			}else{
				$('lqdw').value = '';
				$('lqdw').disabled = 'disabled';
			}
		}
	</script>
</head>
<body onload="checkLqdw();">
	<html:form action="jygl.do" method="post">
	<input type="hidden" name="save_mk" value="${mk }"/>
	<input type="hidden" name="mk" value="${mk }"/>
	<input type="hidden" id="url" value="/jygl.do?method=byskssb"/>
	
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
						<span>填写申请表</span>
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
					<button onclick="showTopWin('/xgxt/jygl.do?method=syxxData',800,500);" id="buttonFindStu">
						&gt;&gt;
					</button>
				</td>
				
				<th width="20%">姓名</th>
				<td width="30%">${rs.xm }</td>
			</tr>
			<tr>
				<th>毕业年份</th>
				<td>${rs.bynf }</td>
				<th><bean:message key="lable.xb" /></th>
				<td>${rs.xymc }</td>
			</tr>
			<tr>
				<th>专业</th>
				<td>${rs.zymc }</td>
				<th>班级</th>
				<td>${rs.bjmc }</td>
			</tr>
			<logic:equal value="ky" name="mk">
				<tr>
					<th>报考学校</th>
					<td><html:text property="save_bkmb" styleId="bkmb" maxlength="30"></html:text></td>
					<th>考试分数</th>
					<td><html:text property="save_fs" styleId="fs" maxlength="10" onkeyup="checkInputNum(this);"></html:text></td>
				</tr>
				<tr>
					<th>是否上线</th>
					<td><html:select property="save_sfsx" styleId="sfsx" onchange="checkLqdw();">
						<html:option value=""></html:option>
						<html:option value="是">是</html:option>
						<html:option value="否">否</html:option>
					</html:select></td>
					<th>是否需调节</th>
					<td><html:select property="save_sfxtj" styleId="sfxtj">
						<html:option value=""></html:option>
						<html:option value="是">是</html:option>
						<html:option value="否">否</html:option>
					</html:select></td>
				</tr>
				<tr>
					<th>录取学校</th>
					<td><html:text maxlength="30" property="save_lqdw" styleId="lqdw" disabled=""></html:text></td>
					<th></th>
					<td></td>
				</tr>
			</logic:equal>
			
			<logic:equal value="kg" name="mk">
				<tr>
					<th>报考类型</th>
					<td><html:text property="save_bklx" styleId="bklx" maxlength="10"></html:text></td>
					<th>报考单位</th>
					<td><html:text property="save_bkmb" styleId="bkmb" maxlength="30"></html:text></td>
				
				</tr>
				<tr>
					<th>考试分数</th>
					<td><html:text property="save_fs" styleId="fs" maxlength="10" onkeyup="checkInputNum(this);"></html:text></td>
					<th>是否上线</th>
					<td><html:select property="save_sfsx" styleId="sfsx" onchange="checkLqdw();">
						<html:option value=""></html:option>
						<html:option value="是">是</html:option>
						<html:option value="否">否</html:option>
					</html:select></td>
				</tr>
				<tr>
					<th>录用单位</th>
					<td><html:text property="save_lqdw" styleId="lqdw" disabled="" maxlength="30"></html:text></td>
					<th></th>
					<td></td>
				</tr>
			</logic:equal>	
			<tr>
				<th>备注<br/>
					<font color="red">(限制在400字内)</font>
				</th>
				<td colspan="3">
					<html:textarea property='save_bz'
						style="word-break:break-all;width:99%"
						onblur="checkLen(this,400)" rows='8'/>
				</td>
			</tr>
			</tbody>
			
			 <tfoot>
			    <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
				         <button class="button2" onclick="saveDataShowTips('jygl.do?method=byskssb&doType=save',
							'xh','正在处理数据，请稍候！');">
							保存
						 </button>
						 <button onclick="reset()">重置</button>
			          	<button class="button2" onclick="location='jygl_kykgsq.do'">
							返回
						</button>
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
		</script>
	</logic:present>
	<logic:present name="msg">
		<input type="hidden" id="msg" value="${msg }" />
		<script type="text/javascript">
			alert(document.getElementById('msg').value);
		</script>
	</logic:present>
</body>
</html>
