<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="js/pjpy/pjpy_dwr.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getCpzfp.js'></script>
		<script language="javascript" src="js/pjpy/pjpy_zjlg.js"></script>
	</head>
	<body onload="xyDisabled('xy');chgDispconf('dispFlag')">
		<html:form action="/zjlgPjpy" method="post">
			<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a><bean:write name="title" scope="request" /></a>
			</p>
			</div>
			<input type="hidden" id="xxk" name = "xxk" value="rych"/>
			<input type="hidden" id="isFdy" value=""/>
			<input type="hidden" id="zyV" name="zyV"/>
			<input type="hidden" id="bjV" name="bjV"/>
			<input type="hidden" id="xq" name="xq" value="${xq }"/>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="method" name="method" value="cssz" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<logic:equal value="yes" name="writeAble">
				<div class="toolbox">
				<div class="buttonbox">	
					<ul>
					<logic:notEqual name="userType" value="xy" scope="session">
						<li><a href="#" class="btn_csh" onclick="chkRychBatForZjlg()">批量设置</a></li>
						<li><a href="#" class="btn_sq" onclick="rychDataInitForZjlg()">初始化数据</a></li>
						<li><a href="#" class="btn_sz" onclick="viewTempDiv('调整学年','tmpdiv','400','',null)">调整学年</a></li>
					</logic:notEqual>
					</ul>
				</div>
				</div>
			</logic:equal>			
			<div class="comp_title">
				<ul>
				<logic:notEqual value="10657" name="xxdm">
					<li id="zxszy_sjd"><a href="#" onclick="$('go').value='';refreshForm('zjlgPjpy.do?xxk=jxj')">
							<span>奖学金</span>
						</a>
					</li>
					<li id="zxszy_dd"  class="ha"><a href="#" onclick="$('go').value='';refreshForm('zjlgPjpy.do?xxk=rych')">
							<span>荣誉称号</span>
						</a>
					</li>
				</logic:notEqual>
				<logic:equal value="10657" name="xxdm">
					<li id="zxszy_sjd"><a href="#" onclick="$('go').value='';refreshForm('guizdxPjpy.do?method=rsszXxManage&mklx=jxj')"">
							<span>奖学金</span>
						</a>
					</li>
					<li id="zxszy_dd"  class="ha"><a href="#" onclick="$('go').value='';refreshForm('guizdxPjpy.do?method=rsszXxManage&mklx=rych')">
							<span>荣誉称号</span>
						</a>
					</li>
				</logic:equal>
					<li id="xlxhhd_hdxs"><a href="#" onclick="$('go').value='';refreshForm('zjlgPjpy.do?xxk=xjbj')">
							<span>先进班级</span>
						</a>
					</li>
				</ul>
			</div>
			
			<div class="searchtab">
				<table width="100%" class="" border="0">
					<tbody>
						<tr>
							<th>学年</th>
							<td><html:select property="xn" style="width:120px" disabled="true"
									styleId="xn" onchange="initZyList();initBjList();">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select></td>
							<th>荣誉称号</th>
							<td><html:select property="rychdm" style="width:150px"
										styleId="rychdm">
										<html:option value=""></html:option>
										<html:options collection="rychList" property="dm"
											labelProperty="mc" />
								</html:select></td>
							<th>显示方式</th>
							<td><html:select property="bmlb" style="width:70px"
									styleId="dispFlag" onchange="chgDispconf('dispFlag');">
									<html:option value="xydm"><bean:message key="lable.xsgzyxpzxy" /></html:option>
									<html:option value="zydm">专业</html:option>
									<html:option value="bjdm">班级</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td><html:select property="xydm" style="width:160px" styleId="xy"
									onchange="initZyList();initBjList();">
									<html:option value="">全部</html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select></td>
							<th>专业</th>
							<td><html:select property="zydm" style="width:160px;" styleId="zy"
									onchange="initBjList();">
									<html:option value="">全部</html:option>
									<html:options collection="zyList" property="zydm" labelProperty="zymc" />
								</html:select></td>
							<th>班级 </th>
							<td><html:select property="bjdm" style="width:140px" styleId="bj">
									<html:option value="">全部</html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
							<div class="btn">
							<input type="hidden" name="go" value="a" />
							<button type="button" id="search_go" onclick="refreshForm('/xgxt/zjlgPjpy.do')">
								查询
							</button>
							 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
								重置
							 </button>
							</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			
			<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
							<font color="red">未找到任何记录！</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font> 
						</span>
					</h3>
					<table width="100%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" align="center" style="cursor:hand"
								ondblclick="if(curr_row.cells[4].innerText==''){alert('尚未进行批量设置生成建议人数，不能调整人数！');return false;}rychrstz()">

								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
					
					<script type="text/javascript">
						$('choose').className="hide";
					</script>
					<!--分页显示-->
			     	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zjlgPjpyForm"></jsp:include>
					<!--分页显示-->
				</logic:notEmpty>
			</div>
			<div id="tmpdiv1"></div>
			<div  id="tmpdiv" style="display:none">
			           <table width='300' class='formlist'>
			           <thead>
			           <tr height='10'>
			           <td colspan='2'>
			           <span>调整学年</span>
			           </td>
			           </tr>
			           </thead>
			           <tbody>
			           <tr height='30'>
			           <th align='right'>
			                                                         评奖评优学年调整为
			           </th>
			           <td align='left'>
			          <html:select property="jxjxn" style="width:150px" 
									styleId="select_jxjxn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
			           </td>
			           </tr>
			           <tbody>
			           <tfoot><tr><td colspan='2' align='right'>
			           <button type="button" onclick=refreshForm('zjlgPjpy.do?method=tzjxjxn');>确定</button>
			           </td></tr></tfoot>
			           </table>
			
			
			
			</div>
		</html:form>
	</body>
</html>
