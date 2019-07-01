<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="initCheck();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>

		<html:form action="/csmz_sztz.do?method=tzxm_sh" method="post">
			<input type="hidden" name="pkValue" id="pkValue"
				value="<bean:write name="pkValue" scope="request"/>">
			<input type="hidden" name="userType" id="userType"
				value="<bean:write name="userType" scope="request"/>">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${tips }</a>
				</p>
			</div>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
 				<tfoot>
			      <tr>
			      	<td colspan="4">
			          <div class="btn">
			             <button name="关闭" onclick="Close();return false;" id="buttonClose">关 闭</button>
			          </div>
			          </td>
			      </tr>
			    </tfoot>
					<div align="right" class="btn">
						
						<logic:equal value="up" name="view">
							<button type="button" id="up" value="上一条" disabled="true">上一条</button>
						</logic:equal>
						<logic:notEqual value="up" name="view">
							<button type="button" id="up" value="上一条"
								onclick="changerecord('up','/xgxt/csmz_sztz.do?method=tzxmShView');">上一条</button>
						</logic:notEqual>
						&nbsp; &nbsp;
						<logic:equal value="next" name="view">
							<button type="button" id="next" value="下一条" disabled="true">下一条</button>
						</logic:equal>
						<logic:notEqual value="next" name="view">
							<button type="button" id="next" value="下一条"
								onclick="changerecord('next','/xgxt/csmz_sztz.do?method=tzxmShView');">下一条</button>
						</logic:notEqual>
						&nbsp; &nbsp;&nbsp; &nbsp;
						<logic:equal value="true" name="sel">
							<button type="checkbox" id="selected" onclick="shot(this);"
								checked="true" >选中</button>
						</logic:equal>
						<logic:notEqual value="true" name="sel">
							<input type="checkbox" id="selected" onclick="shot(this);" />&nbsp;选中
					</logic:notEqual>
					</div>
					<tr style="height:22px">
						<td colspan="4" align="center">
							单个拓展项目申报审核
						</td>
					</tr>
				<tbody>
				<tr style="height:22px">
					<th align="right" width="15%">
						学年
					</th>
					<td align="left" width="35%">
						<bean:write name="rs" property="xn" />
					</td>
					<th align="right" width="15%">
						学期
					</th>
					<td align="left" width="35%">
						<bean:write name="rs" property="xqmc" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						项目名称
					</th>
					<td align="left" nowrap>
						<bean:write name="rs" property="xmmc" />
					</td>
					<th align="right">
						所属科目
					</th>
					<td align="left" nowrap>
						<bean:write name="rs" property="kmm" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						项目级别
					</th>
					<td align="left">
						<bean:write name="rs" property="xmjb" />
					</td>
					<th align="right">
						申请部门
					</th>
					<td align="left" nowrap>
						<bean:write name="rs" property="bmmc" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						主办时间
					</th>
					<td align="left">
						<bean:write name="rs" property="zbsj" />
					</td>
					<th align="right">
						组织单位
					</th>
					<td align="left" nowrap>
						<bean:write name="rs" property="zzdw" />
					</td>
				</tr>
				<logic:equal value="11417" name="xxdm">
					<!-- 北京联合大学学校代码 -->
					<tr valign="middle">
						<th align="right">
							项目负责人
							<br>
							(申报人)
						</th>
						<td align="left">
							<bean:write name="rs" property="xmsbr" />
						</td>
						<th align="right">
							所属部门
						</th>
						<td align="left">
							<bean:write name="rs" property="szbm" />
						</td>
					</tr>
				</logic:equal>
				<tr style="height:22px">
					<th align="right">
						项目描述
					</th>
					<td align="left" colspan="3" nowrap>
						<textarea rows="8" cols="80" readonly="true" type="_moz"><bean:write name="rs" property="xmms" /></textarea>

					</td>
				</tr>
				</tbody>
			</table>
			</div>
			<div class="formbox">
			<h3 class="datetitle_01">
			    <span>
			    	项目奖励&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			    </span>
			</h3>
			
			 <table summary="" class="dateline" align="" width="100%">
				<thead>
					<tr>
						<td align="center">
							<div class="btn"align="right">
								<button id="xxbut" onclick="showHide()">
									隐藏
								</button>
							</div>
						</td>
					</tr>
				</thead>
				<tr id="ly">
					<td>
						<fieldset>
							<logic:empty name="rsJx">
								<br />
								<br />
								<p align="center">
									未找到任何记录！
								</p>
							</logic:empty>
							<logic:notEmpty name="rsJx">
								<legend>
									记录数：
									<bean:write name="reNum" />
								</legend>

								 <table summary="" class="dateline" align="" width="99%">
									<thead>
										<tr align="center">
											<th>
												奖项名
											</th>
											<th>
												${fsclin}
											</th>
										</tr>
									</thead>
									<logic:iterate name="rsJx" id="s">
										<tr style="cursor:hand">
											<td>
												<logic:iterate id="v" name="s" offset="0" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="1">
												<td>
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</table>
							</logic:notEmpty>
						</fieldset>
					</td>
				</tr>
			</table>
		</html:form>
		<logic:equal value="yes" name="done">
			<script language="javascript">
	         	alert("操作成功！");
	         	Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
	         </script>
		</logic:equal>
		<logic:equal value="no" name="done">
			<script language="javascript">
	         	alert("操作失败！");
	         	Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
	        </script>
		</logic:equal>
	</body>
	<script type="text/javascript">
		function showHide(){		
		   if(document.getElementById('ly').style.display==''){
		      document.getElementById('ly').style.display='none';
		      document.getElementById('xxbut').value='显示'
		   }else{
		      document.getElementById('ly').style.display='';
		      document.getElementById('xxbut').value='隐藏'
		   }		
		}		
</script>
</html>
