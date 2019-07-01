<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" >
		//预约
       function yuyue(bh) {	
		var url="/xgxt/xljk_zxszyAtion.do?act=xljk_zxszygl&doType=Xssq_Zycx&zxxbh="
		url=url+bh
		//alert(url);
		document.forms[0].action = url;
		document.forms[0].submit();
		return true;
      }
	</script>
	</head>
	<body onload="">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
	
	
		<html:form action="/xljk_xssqyy" method="post">
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
				
				
				<!-- 多功能操作区一 -->
			<div class="toolbox">
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/xljk_xssqyy.do?act=xljk_xssqyy&doType=Search')">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									咨询师编号
								</th>
								<td>
									<html:text property="zxxbh" style="width:105px"></html:text>
								</td>
								<th>
									咨询师姓名
								</th>
								<td>
									<html:text property="zxxxm" style="width:105px"></html:text>
								</td>
								<th>
									性别
								</th>
								<td>
									<html:select property="sex" style="width:105px" styleId="sex"
										onchange="">
										<html:option value=""></html:option>
										<html:options collection="sexList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</tr>
								</tbody>
					</table>
				</div>
			</div>
			
			
			
			
			
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> 
						<logic:notEmpty name="rs">
							<font color="blue">提示：双击一行可以查看详细信息;<logic:notEqual value="stu" name="userType" scope="session">单击表头可以排序</logic:notEqual></font>
						</logic:notEmpty>
						</span>
				</h3>
				<logic:notEmpty name="rs">
					<div class="con_overlfow">
					<table summary="" class="dateline tablenowrap" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td nowrap>
									申请预约
								</td>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s" offset="0">
							<tr onclick="rowOnClick(this);" style="cursor:hand"
								ondblclick="viewMore_LRH('View_Xssqzxs',this.cells[1].innerText)">
								<td>
									<button class="button2" style="width:40%" id=""
										onclick="yuyue(this.parentNode.parentNode.cells[1].innerText)">
										预约
									</button>
								</td>
								<td id="ZXXBH">
									<bean:write name="s" property="ZXXBH" />
								</td>
								<td>
									<bean:write name="s" property="ZXXXM" />
								</td>
								<td>
									<bean:write name="s" property="ZXXXB" />
								</td>
								<td>
									<bean:write name="s" property="ZXXZG" />
								</td>
								<logic:equal name="xxdm" value="10395">
								<td>
									<bean:write name="s" property="ZC" />
								</td>
								</logic:equal>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					</div>
				</logic:notEmpty>
			</div>
		</html:form>
	</body>
</html>
