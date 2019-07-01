<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getZbrDAO.js'></script>
	<script language="javascript">
	function saveZbr(){
		if($("xh").value == ""){
			alert("学号不能为空，请确认！！");
			return false;
		}
		if($("zbid").value == ""){
			alert("值班ID不能为空，请确认！！");
			return false;
		}
		showTips('处理数据中，请等待......');
		refreshForm('/xgxt/zgddZbr.do?method=zbrUpdate&doType=save');
	}
	
	function checkID(obj,zbid){
		getZbrDAO.getZbid(zbid,function(data){
			if (data != null) {
				if(data != "0"){
					alert("该职工ID已经存在，请重新输入");
					obj.value="";
					obj.focus();
				}
			}
		});
	}
</script>
</head>
	<body onload="">
		<html:form action="/zgddZbr" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>当前所在位置：党团建设 - 数据维护 - 值班人管理</a>
				</p>
			</div>
			
			<logic:notEmpty name="rs">
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/zgddZbr.do?method=zbrUpdate&doType=stuInfo" />
				<fieldset>
					<div class="tab">
					<table width="100%" class="formlist">
					<thead>
						<tr>
						<th colspan="4"><span>值班人信息</span></th>
						</tr>
					</thead>
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
					          <div class="btn">
					         	<logic:notEqual name="doType" value="view">
								<button type="button" onclick="saveZbr();" id="buttonSave">
									保 存
								</button>
								</logic:notEqual>
								<button type="button" onclick="Close();return false;" id="buttonClose">
									关 闭
								</button>
					          </div></td>
					      </tr>
					    </tfoot>
					    
						<tbody>
						<tr>
							<th>
								<font color="red">*</font>学号
							</th>
							<td align="left" style="width:35%">
								<html:text name='rs' property="xh" readonly = "true"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<logic:equal name="doType" value="add">
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu" >
									选择
								</button>
								</logic:equal>
							</td>
							<th>
								姓名
							</th>
							<td align="left" style="width:35%">
								<html:text name='rs' property="xm" styleId="xm" readonly = "true"/>
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" readonly = "true" />
							</td>
							<th>
								年级
							</th>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj"  readonly = "true" />
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />：
							</th>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" readonly = "true"/>
							</td>
							<th>
								专业
							</th>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" readonly = "true"/>
							</td>
						</tr>
						<tr>
							<th>
								班级
							</th>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" readonly = "true"/>
							</td>
							<th>
								<font color="red">*</font>值班ID
							</th>
							<td align="left">
								<html:text name='rs' property="zbid" styleId="zbid" maxlength="10" onblur="checkID(this,this.value)"/>
							</td>
						</tr>
						<tr>
							<th>
								岗位
							</th>
							<td align="left">
								<html:text name='rs' property="gwmc" styleId="gwmc" maxlength="25"/>
							</td>
							<th>
								联系电话
							</th>
							<td align="left">
								<html:text name='rs' property="dh" styleId="dh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "/>
							</td>
						</tr>
						<tr>
							<th>
								银行账号
							</th>
							<td align="left" colspan="3">
								<html:text name='rs' property="yhzh" styleId="yhzh" style="width:95%" maxlength="50" onkeyup="value=value.replace(/[^\d]/g,'') "/>
							</td>
						</tr>
						</tbody>
					</table>
					</div>
				</fieldset>
			
			</logic:notEmpty>
			<logic:present name="result">
				<logic:equal name="result" value="yes">
					<script>
				    alert("提交成功！");
				    dialogArgumentsQueryChick();
					Close();
				    </script>
				</logic:equal>
				<logic:equal name="result" value="no">
					<script>
				    alert("提交失败！");
				    </script>				
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
