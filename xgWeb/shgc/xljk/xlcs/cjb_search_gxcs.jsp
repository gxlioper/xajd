<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xljkFunction.js"></script>
	</head>
	<body>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="tips" scope="request" /></a>
				</p>
			</div>

	<html:form action="/xljk_xlcs_pcjgcx" method="post">
		<input type="hidden" name="search" id="search" value="1"/>
		<input type="hidden" name="condi" value="yz-tj-yzdf" id="condi"/>
		<logic:present name="tableName">
			<input type="hidden" id="tableName" name="tableName"
					value="<bean:write name="tableName"/>" />
		</logic:present>
		<logic:present name="tableName">			
			<input type="hidden" id="realTable" name="realTable"
					value="<bean:write name="realTable"/>" />
		</logic:present>			
		<logic:present name="sql">		
			<input type="hidden" id="sql" name="sql"
					value="<bean:write name="sql"/>" />	
			</logic:present>
		<logic:present name="yz">				
			<input type="hidden" id="yzB" name="yzB"
					value="<bean:write name="yz"/>" />
		</logic:present>
		<logic:present name="tj">				
			<input type="hidden" id="tjB" name="tjB"
					value="<bean:write name="tj"/>" />
		</logic:present>	
		<logic:present name="yzdf">				
			<input type="hidden" id="yzdfB" name="yzdfB"
					value="<bean:write name="yzdf"/>" />
		</logic:present>
		<input type="hidden" id="rowIndex" name="rowIndex"
					value="1" />
		<input type="hidden" id="currCondi" name="currCondi"
					value="0" />	
		
		
		
		<div class="toolbox">
				<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="changeToById();" class="btn_xg">
									<div id ="changeTj">改为按题分查询</div>
								</a>
							</li>
							<li>
								<a href="#"
									onclick="dataExport()"
									class="btn_dc"> 导出 </a>
							</li>
						</ul>
					</div>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0" id="tableadddel">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="getOkStudent();">
											查 询
										</button>
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
									因子
								</th>
								<td>
									<select style="width:150px" name="yz1" id="yz1">
										<option value=""></option>
										<option value="A">乐群性得分</option>
										<option value="B">聪慧性得分</option>
										<option value="C">稳定性得分</option>
										<option value="E">恃强性得分</option>
										<option value="F">兴奋性得分</option>
										<option value="G">有恒性得分</option>
										<option value="H">敢为性得分</option>
										<option value="I">敏感性得分</option>
										<option value="L">怀疑性得分</option>
										<option value="M">幻想性得分</option>
										<option value="N">世故性得分</option>
										<option value="O">忧虑性得分</option>
										<option value="Q1">实验性得分</option>
										<option value="Q2">独立性得分</option>
										<option value="Q3">自律性得分</option>
										<option value="Q4">紧张性得分</option>
									</select>
								</td>
								<th>
									查询条件
								</th>
								<td>
									<select name="tj1" style="width:150px" id="tj1">
										<option value=""></option>
										<option value="1">等于</option>
										<option value="2">大于</option>
										<option value="3">小于</option>
										<option value="4">大于等于</option>
										<option value="5">小于等于</option>
									</select>
								</td>
								<th>
									因子得分
								</th>
								<td>
									<select name="yzdf1" style="width:150px" id="yzdf1">
										<option value=""></option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
									</select>	
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
							<font color="blue">记录数：
						<bean:write name="rsNum" /> &nbsp;&nbsp;&nbsp;提示：单击表头可以排序</font>
						</logic:notEmpty>	
					 </span>
				</h3>

				<logic:notEmpty name="rs">
				<div class="con_overlfow">
					<table summary="" class="dateline tablenowrap" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:equal name="currCondi" value="0">
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this)" ondblclick=""
									style="cursor:hand">
									<td><bean:write name="s" property="r"/></td>
									<td><bean:write name="s" property="xh"/></td>
									<td><bean:write name="s" property="a"/></td>
									<td><bean:write name="s" property="b"/></td>
									<td><bean:write name="s" property="c"/></td>
									<td><bean:write name="s" property="e"/></td>
									<td><bean:write name="s" property="f"/></td>
									<td><bean:write name="s" property="g"/></td>
									<td><bean:write name="s" property="h"/></td>
									<td><bean:write name="s" property="i"/></td>
									<td><bean:write name="s" property="l"/></td>
									<td><bean:write name="s" property="m"/></td>
									<td><bean:write name="s" property="n"/></td>
									<td><bean:write name="s" property="o"/></td>
									<td><bean:write name="s" property="q1"/></td>
									<td><bean:write name="s" property="q2"/></td>
									<td><bean:write name="s" property="q3"/></td>
									<td><bean:write name="s" property="q4"/></td>
								</tr>
							</logic:iterate>
						</logic:equal>
						<logic:equal value="1" name="currCondi">
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this)" ondblclick=""
									style="cursor:hand">
									<td><bean:write name="s" property="r"/></td>
									<td><bean:write name="s" property="xh"/></td>
									<td><bean:write name="s" property="th"/></td>
									<td><bean:write name="s" property="yx"/></td>
									<td><bean:write name="s" property="df"/></td>
								</tr>
							</logic:iterate>
						</logic:equal>
						</tbody>
					</table>
					</div>
				</logic:notEmpty>
			</div>
	</html:form>
	<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
</body>
</html>