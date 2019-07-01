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
	<input type="hidden" name="pkValue" value="${param.pkValue }"/>
	
	<div class="tab_cur">
		<p class="location">
			<em>您的当前位置:</em><a>当前所在位置：共青团管理 - 校园活动单个审核</a>
		</p>
	</div>
	<div class="tab">
		<table class="formlist" width="93%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>校园活动申请信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
					<th align="right" width="20%">
						<font color="red">*</font>申请单位
					</th>
					<td align="left">
						<input type="hidden" name="save_sqdw" value="${rs.sqdw }"/>
						${rs.sqdw }
					</td>
				<th width="20%">
					<div align="right">
						总负责人
					</div>
				</th>
				<td width="30%">
						${rs.zfzr }
				</td>

			</tr>
			<tr>
				<th>
					<div align="right">
						<font color="red">*</font>活动拟开始时间
					</div>
				</th>
				<td>
					<input type="hidden" name="save_kssj" value="${rs.kssj }"/>
					${rs.kssj }
				</td>
				
				<th>
					<div align="right">
						地点
					</div>
				</th>
				<td>
					${rs.dd }
				</td>
			</tr>
			<tr>
				<th width="16%">
					<div align="right">
						活动内容
					</div>
				</th>
				<td width="34%">
					${rs.hdnr }
				</td>
				<th>
					<div align="right">
						参与人数
					</div>
				</th>
				<td>
					${rs.cyrs }
				</td>
			</tr>
			<tr>	
				<th>
					<div align="right">
						现场负责人一
					</div>
				</th>
				<td>
						${rs.xcfzr1 }
				</td>
				
				<th>
					<div align="right">
						联系电话
					</div>
				</th>
				<td>
						${rs.fzr1dh }
				</td>
			</tr>
			
			<tr>	
				<th>
					<div align="right">
						现场负责人二
					</div>
				</th>
				<td>
					${rs.xcfzr2}
				</td>
				
				<th>
					<div align="right">
						联系电话
					</div>
				</th>
				<td>
					${rs.fzr2dh }
				</td>
			</tr>		
	
			<tr>	
				<th>
					<div align="right">
						<bean:message key="lable.xb" />审核
					</div>
				</th>
				<td>
					<logic:equal value="xy" name="userType">
						<html:select property="save_xysh" value="${rs.xysh}">
							<html:option value="未审核">未审核</html:option>
							<html:option value="通过">通过</html:option>
							<html:option value="不通过">不通过</html:option>
						</html:select>
					</logic:equal>
					<logic:notEqual value="xy" name="userType">
						${rs.xysh }
					</logic:notEqual>
				</td>
				
				<th align="right">
					学校审核
				</th>
				<td>
					<logic:equal value="xx" name="userType">
						<html:select property="save_xxsh" value="${rs.xxsh}">
							<html:option value="未审核">未审核</html:option>
							<html:option value="通过">通过</html:option>
							<html:option value="不通过">不通过</html:option>
						</html:select>
					</logic:equal>
					<logic:notEqual value="xx" name="userType">
						${rs.xxsh }
					</logic:notEqual>
				</td>
			</tr>	
			
			<tr align="left" style="height:22px">
							<th align="right">
								活动方案简要描述
								<br />
							<font color="red">(限制在1000字内)</font>
							</th>
							<td colspan="7">
								<html:textarea property='save_hdfa' style="width:99%" readonly="true" value="${rs.hdfa}"
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
								<html:textarea property='save_sqdwyj' style="width:99%" readonly="true" value="${rs.sqdwyj}"
									rows='7' onblur="checkLen(this,400)"/>
							</td>
						</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button type="button" name="提交" 
			          		onclick="BatAlert.showTips('正在处理数据，请稍候!');saveData('ntzy_xyhd.do?method=xyhdshone&doType=save','sqdw-kssj');">保 存</button>
			            <button type="button" name="关闭" onclick="window.close();return false;">关闭</button>
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
