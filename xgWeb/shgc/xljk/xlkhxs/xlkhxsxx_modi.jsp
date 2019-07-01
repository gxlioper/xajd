<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript">
			function Save(){
				var zdgcdxdm=document.all["zdgcdxdm"].value;
				if ( zdgcdxdm==""){
					alert ("请选择是否重点观察对象！");
					document.all["zdgcdxdm"].focus();
					return false;
				}
				document.all["modi_flag"].value="yes";
				underDealWith();
				refreshForm('/xgxt/xljk_xlkhxs.do?act=xljk_xlkhxs&doType=xlkhxs_modi');
			}
		</script>
	</head>
	<body>
		<html:form action="/xljk_xlkhxs" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>心理健康 - 心理困惑学生管理 - 心理困惑学生信息 - 修改信息</a>
				</p>
			</div>
			<input type="hidden" id="modi_flag" name="modi_flag" value="no" />
			<input type="hidden" id="xn_id" name="xn_id"
				value="<bean:write name="xn_id" scope="request"/>" />
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="6"><span>修改信息</span></th>
			        </tr>
			    </thead>
				 <tfoot>
			      <tr>
			        <td colspan="6"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          		<button name="修改" onclick="Save();return false;" id="buttonSave">修 改</button>
			           		<button name="关闭" onclick="Close();return false;" id="buttonClose">关 闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
				
				<tbody>
				<tr style="height:22px" name="aa" id="a1">
					<th align="right" colspan="2">
						学 号
					</th>
					<td align="left">
						<html:text property="xh" styleId="xh" onblur="" onkeypress=""
							readonly="true" />
					</td>

					<th align="right">
						姓 名
					</th>
					<td align="left" colspan="2">
						<html:text property="xm" styleId="xm" readonly="true" />
					</td>
				</tr>
				<tr style="height:22px" name="aa" id="a2">
					<th align="right" colspan="2" readonly="true">
						性 别
					</th>
					<td align="left">
						<html:text property="xb" styleId="xb" readonly="true" />
					</td>
					<th align="right">
						班 级
					</th>
					<td align="left" colspan="2">
						<html:text property="bjmc" styleId="bjmc" readonly="true" />
					</td>
				</tr>
				<tr style="height:22px" name="aa" id="a3">
					<th align="right" colspan="2">
						学 院
					</th>
					<td align="left">
						<html:text property="xymc" styleId="xymc" readonly="true" />
					</td>
					<th align="right">
						重点观察对象
					</th>
					<td align="left" colspan="2">
						<html:select property="zdgcdxdm" style="width:80px" styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="zdgcdxList" property="DMH"
								labelProperty="DMMC" />
						</html:select>
					</td>
				</tr>
				</tbody>
			</table>
			<div id="tmpdiv"></div>
		</html:form>
		<logic:notEmpty name="message">
			<logic:equal name="message" value="update_success">
				<script>
					alert("修改成功!");
					window.dialogArguments.document.getElementById("search_go1").click();
					Close();
				</script>
			</logic:equal>
			<logic:equal name="message" value="update_fail">
				<script>
					alert("修改失败!");
					document.getElementById("tmpdiv").innerHTML = "";
				</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
