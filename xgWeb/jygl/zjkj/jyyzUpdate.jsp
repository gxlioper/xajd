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
	<input type="hidden" id="url" value="/jygl.do?method=jyyzUpdate"/>
	<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm" />
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
						<span>填写申请信息</span>
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
					<html:text property="save_xh" styleId="xh" value="${rs.xh }" readonly="true" onkeypress="autoFillStuInfo(event.keyCode,this)"/>	
					<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
						class="button2" id="buttonFindStu">
						&gt;&gt;
					</button>
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
				<td><html:text property="save_tjdw"></html:text></td>
				<th>援助结果</th>
				<td><html:select property="save_yzjg">
					<html:option value=""></html:option>
					<html:options collection="yzjgList" property="xmdm" labelProperty="xmmc"/>
				</html:select></td>
			</tr>
			<tr>
				<th>参与学生<br/>
					<font color="red">(限制在800字内)</font>
				</th>
				<td colspan="3">
					<html:textarea property='save_cyxs'
						style="word-break:break-all;width:99%"
						onblur="checkLen(this,800)" rows='8'/>
				</td>
			</tr>
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
				         <button class="button2" onclick="saveDataShowTips('jygl.do?method=jyyzUpdate&doType=save',
							'xh','正在处理数据，请稍候！');">
							保存
						 </button>
						 <button onclick="reset()">重置</button>
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
</body>
</html>
