<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/comm/commFunction.js"></script>
	<script type="text/javascript">	
	</script>
	</head>
	
	<body onload="">
		<html:form action="/zjjsRcswRwzb">
		<input type="hidden" id="doType" name="doType" value="${doType }"/>
		<input type="hidden" id="url" name="url" value="zjjsRcswRwzb.do?method=rwzbUpdate&doType=add"/>
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xymc-zymc-bjmc-nj"/>
		<input type="hidden" id="tableName" name="tableName" value="view_xsjbxx"/>
		<input type="hidden" id="lx" name="lx" value="学生"/>
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>入伍征兵</span>
						</th>
					</tr>
				</thead>
				
				<tfoot>
			      <tr>
			        <td colspan="4">
			        	<logic:equal name="doType" value="add">
			          <div class="bz">"<span class="red">*</span>"为必填项</div>
			          </logic:equal>
			          <div class="btn">
			          <logic:notEqual name="doType" value="view">
			          		<button type="button" name="提交" 
			          		onclick="saveUpdate('zjjsRcswRwzb.do?method=rwzbUpdate&doType=save','xh')">保 存</button>
			          </logic:notEqual>
			            <button type="button" name="关闭" onclick="window.close();return false;">关 闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
				
				<tbody>
				<tr >
					<th align="right" width="20%">
						<logic:equal name="doType" value="add">
						<font color="red">*</font>
						</logic:equal>
						学号
					</th>
					<td align="left"  width="30%">
						<logic:equal name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" style="width:100px"/>
							<button type="button" onclick="sendXx();return false;"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
						</logic:notEqual>
					</td>
					<th  width="20%">
						姓名
					</th>
					<td align="left"  width="30%">
						${rs.xm }
					</td>
				</tr>
				<tr>
					<th align="right">
						年级
					</th>
					<td align="left">
						${rs.nj }
					</td>
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						${rs.xymc }
					</td>
				</tr>
				<tr>
					<th align="right">
						专业
					</th>
					<td align="left">
						${rs.zymc }
					</td>
					<th align="right">
						班级
					</th>
					<td align="left">
						${rs.bjmc }
					</td>
				</tr>
				<tr>
					<th align="right">
						是否减免学费
					</th>
					<td align="left">
						<html:select name="rs" property="sfjmxf" style="width:60px" styleId="sfjmxf">
							<html:option value=""></html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>
					</td>
					<th align="right">
						补助金额
					</th>
					<td align="left">
						<html:text name="rs" property="bzje" styleId="bzje"
							onkeyup="checkInputNum(this)" 
							onblur="checkInputNum(this)" maxlength="5" 
							style="width : 100px;ime-mode:disabled;"
						/>
					</td>
				</tr>
				<tr>
         		<th>入伍类型</th>
         			<td>
         				<html:select name="rs" property="rwlx" style="width:60px" styleId="rwlx">
							<html:option value=""></html:option>
							<html:option value="入伍">入伍</html:option>
							<html:option value="退伍">退伍</html:option>
						</html:select>
					</td>
					<th></th>
					<td></td>
         		</tr>	  
				<tr >
					<th align="right">
						备注<br/><font color="red">(限500字)</font>
					</th>
					<td align="left" colspan="3"><br />
						<html:textarea name='rs' property="bz" styleId="bz"
							rows="5" style="width: 100%;word-break:break-all;" onblur="chLeng(this,500)"/>
					</td>
				</tr>
			</tbody>
			</table>
			</div>
			<div id="tmpdiv1"></div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alertInfo('操作成功！',function(){
							dialogArgumentsQueryChick();
							window.close();
						});
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alertInfo('操作失败！');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
