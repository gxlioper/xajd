<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script language="javascript" src="/xgxt/wjcf/shgc/shgcjs/shgcjs.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript">
			function plsh() {
				var checkBoxArr = document.getElementsByName("cbv");
				var flag = false;
				var len=0;
				var str = "";
				for(var i=0;i<checkBoxArr.length;i++){
					if(checkBoxArr[i].checked==true){
						flag = true;
						len++;
						str += checkBoxArr[i].value + "!@";
					}
				}
				
				if (!flag) {
					alert("请选择要审核的记录,单选,多选即可.");
					return false;
				} else {
					showTopWin('plshcxcf.do?pkString=' + str,450,350);
				}
			}
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		
		<html:form action="/wjcfcsmzwh" method="post">
			<div class="tab_cur" id="title_m">
				<p class="location">
				<em>您的当前位置：</em><a>
					<logic:equal value="10827" name="xxdm">
						<bean:message key="wjcf_csmz_cxcfsq" bundle="wjcfcsmz"/>
					</logic:equal>
					<logic:notEqual value="10827" name="xxdm">
						
						<logic:equal value="13022" name="xxdm">
							违纪处分 - 解除察看期审核 - 审核
						</logic:equal>
						<logic:notEqual value="13022" name="xxdm">
							违纪处分 - <logic:notEqual value="10653" name="xxdm"></logic:notEqual><logic:equal value="10827" name="xxdm">解除</logic:equal><logic:notEqual value="10827" name="xxdm">解除处分管理 - 解除</logic:notEqual>审核
						</logic:notEqual>
					</logic:notEqual>
				</a>
				</p>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="bmlb" name="bmlb" value="xy" />
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" name="isBzr" id="isBzr"  value="${bzrQx }"/>
			<input type="hidden" name="isFdy" id="isFdy"  value="${fdyQx }"/>
			<div class="toolbox">
				<!-- 按钮 -->
		
				<logic:equal value="yes" name="writeAble">
	
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="plsh()" class="btn_sh">审核</a>
							</li>
						</ul>
					</div>
					</logic:equal>
	
			
			
				<div class="searchtab">
				 <table width="100%" border="0">
				 <tfoot>
				 	<tr>
				 		<td colspan="8" >
							<input type="hidden" name="go" value="a" />
							<div class="btn">
								 <input type="hidden" name="go" value="a" />
		             			 <button type="button" class="btn_cx" id="search_go" 
									onclick="allNotEmpThenGo('wjcf_csmz_cxcfqry.do')">
									查 询
								 </button>
		                         &nbsp;&nbsp;&nbsp;&nbsp;
		                         <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
		              	           	 重 置
		                         </button>
		                    </div>
						</td>
					</tr>
				</tfoot>
				<tbody>
						<tr>
							<th>
								学年
							</th>
							<td>
								<html:select property="xn" style="width:110px" 
									styleId="xn" styleClass="select">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th>
								年级
							</th>
							<td>
								<html:select property="nj" styleId="nj" style="width:110px"
								styleClass="select"  onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj" labelProperty="nj"/>
								</html:select>
							</td>
							<th>
								学号
							</th>
							<td>
								<html:text property="xh" styleId="xh" maxlength="20" style="width:120px;inputtext"></html:text>
							</td>
							<th>
								姓名
							</th>
							<td>
								<html:text property="xm" styleId="xm" maxlength="15" style="width:120px;inputtext"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:select property="xydm"  styleId="xy" style="width:150px"
								styleClass="select" onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>
								专业
							</th>
							<td>
								<html:select property="zydm" style="width:150px"
								styleClass="select" styleId="zy" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm" 
									labelProperty="zymc"/>
								</html:select>
							</td>
							<th>
								班级
							</th>
							<td>
								<html:select property="bjdm" styleClass="select" styleId="bj" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>
								</html:select>
							</td>
							<th>
							审核
							</th>
							<td colspan="">
							<html:select property="xxsh" style="width:100px"
									styleId="sh">
									<html:option value=""></html:option>
									<html:options collection="shList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
					</thead>
				</table>
				</div>
			</div>
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs" >
			 		 		记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息并审核；单击表头可以排序</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
			<logic:notEmpty name="rs">

					 <table  id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
							<td>
								<input type="checkbox" id="cb" name="cb"
											onclick="selectAll()" />
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
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)"
								style="cursor:hand;background-color:
							    <logic:iterate id="v" name="s" offset="1" length="1">
							    <bean:write name="v"/>
							    </logic:iterate>
							    "
								ondblclick="chkPriseOne_shgc('/xgxt/wjcf_csmz_cxcf.do?pkVal=',650,540)">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<input type="checkbox" id="cbv" name="cbv"
														value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
								</td>								
								<logic:iterate id="v" name="s" offset="2">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
	
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				
			<div id="tmpdiv"></div>
		</html:form>
			<%--<script type="text/javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
			<logic:equal value="view" name="result">
			<script>
				alert("操作成功");
				window.document.getElementById('search_go').click();
			</script>
		</logic:equal>
		--%>
			
	</body>
</html>
