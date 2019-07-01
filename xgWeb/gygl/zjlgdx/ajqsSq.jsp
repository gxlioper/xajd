<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript">
		function Save(value){
		   var sqly = document.getElementById('sqly').value;
		   var sqsj = document.getElementById('sqsj').value;
		   var scsqsj = document.getElementById('scsqsj').value;
		   if(sqly.length > 1000){
		   		alert('申请理由不能大于1000字！');
		   	   	return false;
		   }
		   if(sqsj == scsqsj){
		   		alert('您所住寝室今天已经申请A级寝室,不能重复申请！');
		   	   	return false;
		   }
		   document.forms[0].action = "/xgxt/zjlg_gygl.do?method=ajqssq&doType=save&act="+value;
		   document.forms[0].submit();
		}		
		function toPrintOut(){//输出相应的打印页面 
			document.forms[0].action = "/xgxt/zjlg_gygl.do?method=viewSqxx&doType=print";
			document.forms[0].target = "_blank";
	        document.forms[0].submit();
	        document.forms[0].target = "_self";
		}
	</script>
</head>
<body>
	<logic:equal value="yes" name="info">
		<br/>
		<br/>
		<br/>
		<p align="center"><font color="red" size="3">该页面只允许学生访问！</font></p>
	</logic:equal>
	<logic:notEqual value="yes" name="info">
		<logic:equal value="no" name="rzxx">
			<br/>
			<br/>
			<br/>
			<p align="center"><font color="red" size="2">您还没有入住，不能申请A级寝室</font></p>
		</logic:equal>
		<logic:notEqual value="no" name="rzxx">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>公寓管理 - A级寝室管理 - 申请 - A级寝室</a>
				</p>
			</div>
			<html:form action="/zjlg_gygl" method="post">
				<input type="hidden" name="ssbh" value="<bean:write name="rs" property="ssbh"/>"
					id="ssbh" />
				<input type="hidden" name="sqsj" value="<bean:write name="rs" property="sqsj"/>"
					id="sqsj" />
				<input type="hidden" name="scsqsj" value="<bean:write name="rs" property="scsqsj"/>"
					id="scsqsj" />
				<div class="tab">
				<table class="formlist" width="90%">
					<thead><tr><th colspan="4"><span>信息维护</span></th></tr></thead>
				    <tbody>
					<tr>
						<th>
							楼栋
						</th>
						<td>
							<input type="hidden" name="lddm"
								value="<bean:write name="rs" property="lddm"/>" />
							<html:select name="rs" property="lddm" styleId="lddm" disabled="true">
								<html:options collection="ldList" property="lddm"
									labelProperty="ldmc" />
							</html:select>
						</td>
						<th>
							楼层
						</th>
						<td>
							<input type="hidden" name="lc"
								value="<bean:write name="rs" property="lc"/>" />
							<html:select name="rs" property="lc" styleId="lc" disabled="true">
								<html:options collection="lcList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							寝室
						</th>
						<td>
							<input type="hidden" name="qsh"
								value="<bean:write name="rs" property="qsh"/>" />
							<html:select name="rs" property="qsh" styleId="qsh" disabled="true">
								<html:options collection="qshList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
						<th>
							学年
						</th>
						<td>
							<bean:write name="rs" property="xn" />
						</td>

					</tr>
					<tr>
						<th>
							学期
						</th>
						<td>
							<bean:write name="rs" property="xq" />
						</td>
						<th>
							申请时间
						</th>
						<td>
							<bean:write name="rs" property="sqsj" />
						</td>
					</tr>
					<tr>
						<th>
							联系电话
						</th>
						<td>
							<input type="text" id="lxdh" name="lxdh" maxlength="50"
								style="180px;heigh:100%" onblur="chLeng(this,1000);"
								value="<bean:write name="rs" property="lxdh"/>" />
						</td>
						<th>
						</th>
						<td>
						</td>
					</tr>
					<tr>
						<th>
							申报理由<br/>
								<font color="red">(限1000字内)</font>
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="sqly" rows="10" styleId="sqly"
								style="word-break:break-all;width:99%" />
						</td>
					</tr>
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">
				          <div class="btn">
							<logic:equal name="commit" value="no">
								<button class="button2" onclick="Save('apply')" id="buttonSave">
									提交
								</button>
							</logic:equal>
							<logic:notEqual name="commit" value="no">
								<button class="button2" onclick="Save('modify')" id="buttonSave">
									保存
								</button>
							</logic:notEqual>
							<button class="button2" onclick="toPrintOut();">
									打印
							</button>
				          </div></td>
				      </tr>
				    </tfoot>
				</table>
				</div>

			</html:form>
		</logic:notEqual>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
			    alert('操作成功！');
			  </script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
			    alert('操作失败！');
			  </script>
		</logic:equal>
		<logic:equal value="yes" name="isajqs">
			<script type="text/javascript">
			    alert('您所在寝室现在已是A级寝室，不能再申请！');
			  </script>
		</logic:equal>
	</logic:notEqual>
</body>
</html>
