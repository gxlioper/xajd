<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/commit.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,800,600);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}
		
		function choiceDisabled(usertype,ele) {
   			if($("userType")){
				if ($("userType").value == usertype) {
					var tmp = ele.split("-");
					for (i = 0; i < tmp.length; i++) {
						if(document.getElementById(tmp[i])){
			  		 		document.getElementById(tmp[i]).disabled = true;
						}
					}
  		 		}  
			}
		}
		
	function isSh(){
		if(curr_row != null){
			var xxsh = curr_row.getElementsByTagName('input')[1].value;
			var userType = $('userType').value;
			
			if(userType == "xy" && xxsh == "通过"){
				alert("您选择的学生已被上级审核通过，您无权修改");
			}else{
				modi('jygl.do?method=jyyzmodi&doType=modi');
			}
		}else{
				alert('请选择要修改的数据行！');
		}
	}
	</script>
	</head>
	<body onload="choiceDisabled('xy','xy');">
		<html:form action="jygl.do" method="post">
			<input type="hidden" name="mk" value="${mk }"/>
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="userName" value="${userName }"/>
			<input type="hidden" name="tableName" value="${tableName }"/>
			<input type="hidden" name="realTable" value="${realTable }"/>
			<input type="hidden" name="doType" value="${doType }" />
			<logic:equal value="xy" name="userType">
				<input type="hidden" name="queryequals_xydm" value="${userDep}"/>	
			</logic:equal>
			
			<div class="tab_cur">
				<p class="location">
					<em>当前所在位置：</em><a>${title }</a>
				</p>
			</div>
				
			<logic:equal value="yes" name="writeAble">
				<div class="toolbox">
					<div class="buttonbox">
						<ul>
						<logic:equal value="cx" name="mk">
							<li><a href="#" class="btn_zj" onclick="showTopWin('jygl_jyyzsq.do',800,600)">增加</a></li>
							<li><a href="#" class="btn_xg" onclick="isSh();">修改</a></li>
							<li><a href="#" class="btn_sc" onclick="dataBatch('jygl.do?method=jyyzManage&doType=del&go=go')">删除</a></li>
							<li><a href="#" class="btn_dr" onclick="impAndChkData()">导入</a></li>
							<li><a href="#" class="btn_dc" onclick="expData('jygl.do?method=jyyzManage&doType=expData')">导出</a></li>
						</logic:equal>

						<logic:equal value="sh" name="mk">
							<li><a href="#" class="btn_sh" onclick="modi('jygl.do?method=jyyzmodi&doType=shone');">单个审核</a></li>
							<li><a href="#" class="btn_shtg" onclick="batchData('primarykey_cbv','jygl.do?method=jyyzsh&shjg=tg&go=go','sh')">审核通过</a></li>
							<li><a href="#" class="btn_shbtg" onclick="batchData('primarykey_cbv','jygl.do?method=jyyzsh&shjg=btg&go=go','sh')">审核不通过</a></li>
						</logic:equal>
						</ul>
					</div>
				</div>
			</logic:equal>
				
			<div class="searchtab">
				<table width="100%" border="0">
					<tbody>
						<tr>
							<th>学号</th>
							<td><html:text property="querylike_xh" styleId="xh"></html:text></td>
							<th>姓名</th>
							<td><html:text property="querylike_xm" styleId="xm"></html:text></td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>援助结果</th>
							<td><html:select property="queryequals_yzjg">
								<html:option value=""></html:option>
								<html:options collection="yzjgList" labelProperty="xmmc" property="xmdm"/>
							</html:select></td>
							<th>审核</th>
							<td>
								<html:select property="queryequals_xxsh">
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xb" /></th>
								<td><html:select property="queryequals_xydm" style="width:180px"
									styleId="xy" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select></td>
								<th>专业</th>
								<td><html:select property="queryequals_zydm" style="width:180px"
									styleId="zy" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select></td>
								<th>班级</th>
								<td><html:select property="queryequals_bjdm" style="width:180px"
									styleId="bj">
									<html:option value=""></html:option>
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
	              		<button class="btn_cx" id="search_go" 
	              		onclick="allNotEmpThenGo('/xgxt/jygl.do?method=jyyzManage&go=go')">
	              		查 询
	              		</button>
	             		 <button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
	              			重 置
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
					<table summary="" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="3">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);" class="<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>"
								ondblclick="modi('jygl.do?method=jyyzmodi&doType=view');"
								align="left" style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">
									<input type="checkbox" name="primarykey_cbv" id="pkV" ${v } value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3" length="6">
									<td>
										${v }
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="9">
									<td>
										<input type="hidden" value="${v }" />
										${v }
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
					
				<!--分页显示-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jyglActionForm"></jsp:include>
				  <!--分页显示-->
			</logic:notEmpty>
			</div>
		</html:form>
		<logic:present name="result">
			<input type="hidden" id="message" value="${message }" />
			<script type="text/javascript">
				alert(document.getElementById('message').value);
			</script>
		</logic:present>
	</body>
</html>
