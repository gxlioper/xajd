<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
</head>

<body>
	<div class="tab_cur">
		<p class="location">
			<em>您的当前位置:</em><a>评奖评优 - 参数设置 - 评奖方式</a>
		</p>
	</div>
		<html:form action="/zjlgPjpy" method="post">
			<logic:present name="updated">
				<logic:equal value="yes" name="updated">
					<script language="javascript">
	         	alert("保存成功！");
	         	</script>
				</logic:equal>
				<logic:equal value="no" name="updated">
					<script language="javascript">
	         	alert("保存失败！");
	         	</script>
				</logic:equal>
			</logic:present>
			<div class="tab">
			<table class="formlist" width="100%">
				<tbody>
				<tr>
					<th>
						<b>年级</b>
					</th>
					<td>
						<b>评奖方式</b>
					</td>
				</tr>
				<logic:iterate id="s" name = "rs">
							<tr>
							<th>
								<bean:write name = "s" property="nj"/>
								<input type = "hidden" name = "njz" value = "<bean:write name = "s" property="nj"/>" />
							</th>
							<td>
								<html:select name = "s" property="pjfsz"> 
									<html:option value=""></html:option>
									<html:option value="5">学习质量分</html:option>
									<html:option value="100">学分加权平均分</html:option>
								</html:select>
							</td>
							</tr>
				</logic:iterate>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
						  <button type="button" onclick="refreshForm('zjlgPjpy.do?method=savePjfs');" id="buttonSave">
							保 存
						</button>
						  <button type="button" name="关闭" onclick="window.close();return false;" id="buttonClose">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		</html:form>
</body>
</html>
