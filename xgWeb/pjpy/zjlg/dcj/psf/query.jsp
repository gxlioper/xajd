<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
	<script type="text/javascript" src="js/pjpy/pjpy_zjlg.js"></script>
	<script type="text/javascript" src="js/jxglFunction.js"></script>
	<script language="javascript">	
	function savePsf() {
		if($('rsTable')){
			if (confirm('确认要保存当前页所录入的数据吗?')) {
				refreshForm('pjpy_zjlg_psfwh.do?act=save');
			}
		}else {
			alert('请先录入数据再保存！');
		}
	}
		
	</script>
	</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/zjlgPjpydcj" method="post">
			<input type="hidden" name="xyV" id="xyV" value=""/>
			<input type="hidden" name="zyV" id="zyV" value=""/>
			<input type="hidden" name="bjV" id="bjV" value=""/>
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" id="userName" name="userName" value="${userName }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="message" name="message" value="${message }" />
			<input type="hidden" id="pt" name="pt" value="${message }" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>评奖评优 - 信息维护 - 德育平时分维护</a>
				</p>
			</div>
			
			<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox">	
						<ul>
							<logic:equal value="true" name="fdyQx">
							<li><a href="#" class="btn_sz" onclick="showTopWin('pjpy_zjlg_psfblsz.do',400,300)">平时分比例设置</a></li>
							</logic:equal>
							<li><a href="#" class="btn_zj" onclick="savePsf()">保存</a></li>
							<logic:notEqual value="true" name="fdyQx">
							<li><a href="#" class="btn_shtg" onclick="shdata('pjpy_zjlg_psfwh.do?act=sh&jg=tg')">审核通过</a></li>
							<li><a href="#" class="btn_shbtg" onclick="shdata('pjpy_zjlg_psfwh.do?act=sh&jg=btg')">审核不通过</a></li>
							</logic:notEqual>
							<li><a href="#" class="btn_dr" onclick="impAndChkData()">导入</a></li>
							<li><a href="#" class="btn_dc" onclick="dataExport()">导出</a></li>								
						</ul>
					</div>
					</div>
				</logic:equal>
				
				<div class="searchtab">	
					<table width="100%" class="">
						<tbody>
							<tr>
								<th>年级</th>
								<td><html:select property="nj" style="width:80px"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select></td>
								<th>学年</th>
								<td><html:select property="xn" style="width:100px">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select></td>
								<th></th><td>${xn }</td>
							</tr>
							<tr>
								<th>学号</th>
								<td><html:text property="xh" style="width:85px" maxlength="20"></html:text></td>
								<th>姓名</th>
								<td><html:text property="xm" style="width:85px" maxlength="20"></html:text>								
								</td>
								<th></th><td></td>
							</tr>
							<tr>
								<th><bean:message key="lable.xsgzyxpzxy" /></th>
								<td><html:select property="xydm" styleId="xy" style="width:150px"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select></td>
								<th>专业</th>
								<td><html:select property="zydm" styleId="zy" style="width:150px"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select></td>
								<th>班级</th>
								<td><html:select property="bjdm" styleId="bj" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
						<tr>
							<td colspan="6">
							<div class="btn">
							<input type="hidden" name="go" value="a" />
							<button type="button" id="search_go" onclick="allNotEmpThenGo('pjpy_zjlg_psfwh.do?act=qry')">
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
							查询结果&nbsp;&nbsp;<font color="blue">提示：单击表头可以排序;若遇到不可审核的记录，请先维护其分数。</font> 
							<logic:present name="blrs">
								<font color="red">
									<logic:notEmpty name="blrs" property="zpfbl">
										自我评议分所占比例:${blrs.zpfbl }%
									</logic:notEmpty>
									<logic:notEmpty name="blrs" property="bjfbl">
										班级评议分所占比例:${blrs.bjfbl }%
									</logic:notEmpty>
								</font>
							</logic:present>
						</span>
					</h3>
					<table width="100%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px" />
									</td>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:equal value="true" name="bzrQx">
							<logic:iterate name="rs" id="s" indexId="index">
							<tr onclick="rowOnClick(this);" style="cursor:hand;">
							<td align="center">
								<input type="checkbox" id="cbv" name="cbv" style="height:17.5px"
									value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
								<input type="hidden" id="cbv" name="primary_xh" style="height:17.5px"
									value="<logic:iterate id="v" name="s" offset="3" length="1"><bean:write name="v"/></logic:iterate>"/>
								<input type="hidden" name="save_xysh" value="<logic:iterate id="v" name="s" offset="10" length="1"><bean:write name="v"/></logic:iterate>"/>
							</td>
							<logic:iterate id="v" name="s" offset="2" length="5">
								<td align="center">
									<bean:write name="v" />
								</td>
							</logic:iterate>
							<td align="center">
								<input type="text" name="save_zwpyf" maxlength="4" style="width: 50px;height:17.5px"
								<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>
								value="<logic:iterate id="v" name="s" offset="7" length="1"><bean:write name="v"/></logic:iterate>" onkeyup="ckinpdata(this)"/>
							</td>
							<td align="center">
								<input type="text" name="save_bjpyf" maxlength="4" style="width: 50px;height:17.5px"
								<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>
								value="<logic:iterate id="v" name="s" offset="8" length="1"><bean:write name="v"/></logic:iterate>" onkeyup="ckinpdata(this)"/>
							</td>
							<td align="center">
								<input type="text" name="save_xyfjf" maxlength="4" readonly="readonly" style="width: 50px;height:17.5px;color:#999999'"
								value="<logic:iterate id="v" name="s" offset="9" length="1"><bean:write name="v"/></logic:iterate>" onkeyup="ckinpdata(this)"/>
							</td>
							<td align="center">
								<logic:iterate id="v" name="s" offset="10" length="1"><bean:write name="v"/></logic:iterate>
							</td>
						</tr>
						</logic:iterate>
						</logic:equal>
						<logic:notEqual value="true" name="bzrQx">
							<logic:iterate name="rs" id="s" indexId="index">
							<tr onclick="rowOnClick(this);" style="cursor:hand;">
							<td align="center">
								<logic:iterate id="cz" name="s" offset="11" length="1">
									<logic:notEmpty name="cz">
										<input type="checkbox" id="cbv" name="primarykey_cbv" style="height:17.5px"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									</logic:notEmpty>
									<logic:empty name="cz">
										<input type="checkbox" id="cbv" name="primarykey_cbv" style="height:17.5px" disabled="disabled"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									</logic:empty>
								</logic:iterate>
								<input type="hidden" id="cbv" name="primary_xh" style="height:17.5px"
									value="<logic:iterate id="v" name="s" offset="3" length="1"><bean:write name="v"/></logic:iterate>"/>
								<input type="hidden" name="save_xysh" value="<logic:iterate id="v" name="s" offset="10" length="1"><bean:write name="v"/></logic:iterate>"/>
							</td>
							<logic:iterate id="v" name="s" offset="2" length="5">
								<td align="center">
									<bean:write name="v" />
								</td>
							</logic:iterate>
							<td align="center">
								<input type="text" name="save_zwpyf" maxlength="4" style="width: 50px;height:17.5px"
								value="<logic:iterate id="v" name="s" offset="7" length="1"><bean:write name="v"/></logic:iterate>" onkeyup="ckinpdata(this)"/>
							</td>
							<td align="center">
								<input type="text" name="save_bjpyf" maxlength="4" style="width: 50px;height:17.5px"
								value="<logic:iterate id="v" name="s" offset="8" length="1"><bean:write name="v"/></logic:iterate>" onkeyup="ckinpdata(this)"/>
							</td>
							<td align="center">
								<input type="text" name="save_xyfjf" maxlength="4" style="width: 50px;height:17.5px"
								value="<logic:iterate id="v" name="s" offset="9" length="1"><bean:write name="v"/></logic:iterate>" onkeyup="ckinpdata(this)"/>
							</td>
							<td align="center">
								<logic:iterate id="v" name="s" offset="10" length="1"><bean:write name="v"/></logic:iterate>
							</td>
						</tr>
						</logic:iterate>
						</logic:notEqual>
					</table>
					
				<!--分页显示-->
			    	 <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=dycjActionForm"></jsp:include>
				<!--分页显示-->
				</logic:notEmpty>
		
				<div id="tmpdiv"></div>
			</div>
		</html:form>
		<logic:notEmpty name="result">
			<script>
				alert("" + $('message').value);
			</script>
		</logic:notEmpty>
	</body>
	</html>
