<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link id="csss"/>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script type="text/javascript" src="js/commit.js"></script>
	
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('请选择要操作的数据行！');
				return false;
			}
		}
		
	
		function choiceDisabled(ele) {
			var tmp = ele.split("-");
			for (i = 0; i < tmp.length; i++) {
				if (document.getElementById(tmp[i])) {
					document.getElementById(tmp[i]).disabled = true;
				}
			}
		}
		
</script>
</head>
	<body>
		<html:form action="/xysf_fdykywh.do?method=kyxxcx" method="post">
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" name="pkValue" value="${param.pkValue }"/>
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="userName" value="${userName }"/>
			<input type="hidden" name="tableName" value="${tableName }"/>
			<input type="hidden" name="realTable" value="${realTable }"/>
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
				
			<logic:equal value="yes" name="writeAble">
				<div class="toolbox">
				<div class="buttonbox">	
					<ul>
						<li><a href="#" class="btn_xg" onclick="modi('xysf_fdykywh.do?method=kyxxView&doType=update',700,500);">修改</a></li>
						<li><a href="#" class="btn_sc" onclick="batchData('primarykey_cbv','xysf_fdykywh.do?method=kyxxcx&doType=del&go=go','del');">删除</a></li>
						<li><a href="#" class="btn_dr" onclick="impAndChkData()">导入</a></li>
						<li><a href="#" class="btn_dc" onclick="wjcfDataExport('xysf_fdykywh.do?method=dateExport&mk=fdyky');">导出</a></li>								
					</ul>
				</div>
				</div>
			</logic:equal>
			
			<div class="searchtab">
				<table width="100%" class="" border="0">
					<tbody>
						<tr>
							<th>职工号</th>
							<td><html:text property="querylike_zgh" styleId="zgh"></html:text></td>
							<th>姓名</th>
							<td><html:text property="querylike_xm" styleId="xm"></html:text></td>
							<th>所在部门</th>
							<td><html:select property="queryequals_bmdm" style="width:180px"
									styleId="bmdm">
									<html:option value=""></html:option>
									<html:options collection="bmList" property="bmdm"
										labelProperty="bmmc" />
								</html:select>
							</td>
						</tr>
						
						<tr>
							<th>文章名称</th>
							<td><html:text property="querylike_wzmc" styleId="wzmc" /></td>
							
						</tr>
						
						<logic:equal value="true" name="isSzdw">
							<input type="hidden" name="querylike_zgh" value="${userName }"/>
							<script type="text/javascript">
								choiceDisabled('zgh-xm-bmdm');
							</script>
						</logic:equal>
						
						<logic:notEqual value="true" name="isSzdw">
						<logic:equal value="xy" name="userType">
							<input type="hidden" name="queryequals_bmdm" value="${userDep }"/>
							<script type="text/javascript">
								choiceDisabled('bmdm');
							</script>
						</logic:equal>
						</logic:notEqual>
					</tbody>
					<tfoot>
		        		<tr>
		          			<td colspan="6">
		            		<div class="btn">
		              		<input type="hidden" name="go" value="a" />
		              		<button type="button" id="search_go" 
		              		onclick="allNotEmpThenGo('/xgxt/xysf_fdykywh.do?method=kyxxcx');">
		              		查 询
		              		</button>
		             		 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
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
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('xysf_fdykywh.do?method=kyxxView&doType=view',700,500);"
								align="center"
								style="cursor:hand">
								<td>
									<input type="checkbox" name="primarykey_cbv" 
										id="pkV" value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"
										/>
								</td>
							
								<logic:iterate id="v" name="s" offset="1">
									<td>${v }</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
				<!--分页显示-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=dyjstForm"></jsp:include>
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
