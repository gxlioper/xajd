<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
</head>
<body>
	
	<html:form action="ntzy_xyhd.do?method=xyhdsq" method="post">
	
	<div class="tab_cur">
		<p class="location">
			<em>您的当前位置:</em><a>
				<logic:equal value="modi" name="operation">共青团管理 - 校园活动单个修改</logic:equal>
				<logic:equal value="view" name="operation">共青团管理 - 校园活动单个查看</logic:equal>
			</a>
		</p>
	</div>
	
		<input type="hidden" name="pkValue" value="${param.pkValue }"/>
		
		<div class="div">
		<table class="formlist" width="93%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>填写申请表</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
					<th align="right" width="20%">
						<font color="red">*</font>申请单位
					</th>
					<td align="left">
						<html:hidden property="save_sqdw" styleId="sqdw" value="${rs.sqdw }" />
						${rs.sqdw }
					</td>
				<th width="20%">
					<div align="right">
						总负责人
					</div>
				</th>
				<td width="30%">
					<html:text property="save_zfzr" styleId="zfzr" value="${rs.zfzr}"></html:text>
				</td>
			</tr>
			<tr>
				<th>
					<div align="right">
						<font color="red">*</font>活动拟开始时间
					</div>
				</th>
				<td>
					<html:hidden property="save_kssj" styleId="kssj" value="${rs.kssj }" />
					${rs.kssj }
				</td>
				
				<th>
					<div align="right">
						地点
					</div>
				</th>
				<td>
					<html:text property="save_dd" styleId="dd" value="${rs.dd }"></html:text>
				</td>
			</tr>
			<tr>
				<th width="16%">
					<div align="right">
						活动内容
					</div>
				</th>
				<td width="34%">
					<html:text property="save_hdnr" styleId="hdnr" value="${rs.hdnr }"></html:text>
				</td>
				<th>
					<div align="right">
						参与人数
					</div>
				</th>
				<td>
					<html:text property="save_cyrs" styleId="cyrs" value="${rs.cyrs}" 
					onkeyup="checkInputData(this);" maxlength="5"></html:text>
				</td>
			</tr>
			<tr>	
				<th>
					<div align="right">
						现场负责人一
					</div>
				</th>
				<td>
					<html:text property="save_xcfzr1" styleId="xcfzr1" value="${rs.xcfzr1 }"></html:text>
				</td>
				
				<th>
					<div align="right">
						联系电话
					</div>
				</th>
				<td>
					<html:text property="save_fzr1dh" styleId="fzr1dh" value="${rs.fzr1dh }" 
					onkeyup="checkInputData(this);"></html:text>
				</td>
			</tr>
			
			<tr>	
				<th>
					<div align="right">
						现场负责人二
					</div>
				</th>
				<td>
					<html:text property="save_xcfzr2" styleId="xcfzr2" value="${rs.xcfzr2 }"></html:text>
				</td>
				
				<th>
					<div align="right">
						联系电话
					</div>
				</th>
				<td>
					<html:text property="save_fzr2dh" styleId="fzr2dh" value="${rs.fzr2dh }" 
					onkeyup="checkInputData(this);"></html:text>
				</td>
			</tr>
			
				<tr>	
				<th>
					<div align="right">
						<bean:message key="lable.xb" />审核
					</div>
				</th>
				<td>
					${rs.xysh }
				</td>
				
				<th>
					<div align="right">
						 学校审核
					</div>
				</th>
				<td>
					${rs.xxsh }
				</td>
			</tr>	

			<tr align="left" style="height:22px">
							<th align="right">
								活动方案简要描述
								<br />
							<font color="red">(限制在1000字内)</font>
							</th>
							<td colspan="7">
								<html:textarea property='save_hdfa' style="width:99%" value="${rs.hdfa }"
									onblur="checkLen(this,1000)" rows='8'/>
							</td>
			</tr>
			<tr align="left" style="height:22px">
							<th align="right">
								申请单位意见
								<br/>
								<font color="red">(限制在400字内)</font>
							</th>
							<td colspan="7">
								<html:textarea property='save_sqdwyj' style="width:99%" value="${rs.sqdwyj }"
									rows='7' onblur="checkLen(this,400)"/>
							</td>
						</tr>
				</tbody>
				
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			         <logic:equal name="operation" value="modi">
			          	<button type="button" name="提交" onclick="BatAlert.showTips('正在处理数据，请稍候！');saveData('ntzy_xyhd.do?method=xyhdViewAndModi&doType=save','sqdw-kssj');">保存</button>
			         </logic:equal>
			            <button type="button" name="关闭" onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
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
