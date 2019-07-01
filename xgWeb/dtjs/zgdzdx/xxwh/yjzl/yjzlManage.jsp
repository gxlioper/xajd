<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/sxjyFunction.js"></script>
	<script language="javascript">

</script>
</head>
	<body onload="">
		<center>
			<html:form action="/zgdzdxXxwh" method="post">
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope = "session"/>" />
			<input type="hidden" id="method" name="method"
				value="yjzlManage" />
			<input type="hidden" id="writeAble" name="writeAble"
				value="<bean:write name="writeAble"/>" />
			<input type="hidden" id="title" name="title"
				value="<bean:write name = "title" />" />
				<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				</div>
				<div class="compTab">
				<div class="comp_title">
				<ul>
					<li id="001m"
						onclick="$('go').value='';refreshForm('zgdzdxXxwh.do?method=fblwManage')">
						<a><span>发表论文</span></a>
					</li>
					<li id="002m" onclick="$('go').value='';refreshForm('zgdzdxXxwh.do?method=kyxmManage')">
						<a><span>科研项目</span></a>
					</li> 
					<li id="003m" onclick="$('go').value='';refreshForm('zgdzdxXxwh.do?method=fdyzzManage')">
						<a><span>辅导员著作</span></a>
					</li>
					<li id="004m" 
						class="ha"
						onclick="$('go').value='';refreshForm('zgdzdxXxwh.do?method=yjzlManage')">
						<a><span>研究资料</span></a>
					</li>
				</ul>
				</div>
  				</div>
  				
				<logic:equal value="yes" name="writeAble">
						<div class="toolbox">
						<div class="buttonbox">	
							<ul>
								<li><a href="#" class="btn_zj" onclick="showTopWin('/xgxt/zgdzdxXxwh.do?method=yjzlUpdata',800,600)">增加</a></li>
								<li><a href="#" class="btn_xg" onclick="if(curr_row == null){alert('请选择要修改的行!');return false;}else{showTopWin('/xgxt/zgdzdxXxwh.do?method=yjzlUpdata&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value,800,600)}">修改</a></li>
								<li><a href="#" class="btn_sc" onclick="if(curr_row == null){alert('请选择要删除的行!');return false;}else{refreshForm('/xgxt/zgdzdxXxwh.do?method=yjzlDelete&go=go&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value)}">删除</a></li>
								<li><a href="#" class="btn_dr" onclick="impAndChkData()">导入</a></li>
								<li><a href="#" class="btn_dc" onclick="dataExport()">导出</a></li>
								<li><a href="#" class="btn_down" onclick="dataStencilExport()">下载模板</a></li>
							</ul>
						</div>
						</div>
				</logic:equal>
				
				<div class="searchtab">
					<table width="100%" class="" border="0">
						<tbody>
							<tr>
								<th>
									书名</th>
								<td><html:text property="sm" style="width:180px"/>
								</td>
							</tr>
						</tbody>
						
						<tfoot>
		        		<tr>
		          			<td colspan="2">
		            		<div class="btn">
		              		<input type="hidden" name="go" value="a" />
		              		<button type="button" id="search_go" 
		              		onclick="allNotEmpThenGo('/xgxt/zgdzdxXxwh.do');">
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
					    		查询结果&nbsp;&nbsp;
									<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font> 
						    </span>
						    </h3>
						<table width="100%" id="rsTable" class="dateline">
							<thead>
								<tr style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1" length="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
							<logic:iterate name="rs" id="s">
							<logic:equal value="yes" name="writeAble">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="showTopWin('/xgxt/zgdzdxXxwh.do?method=yjzlUpdata&pk='+curr_row.cells[0].getElementsByTagName('input')[0].value,800,600)">	
									<td align="left">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v" />" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:equal>
							<logic:notEqual value="yes" name="writeAble">
								<tr onclick="rowOnClick(this)" style="cursor:hand">	
									<td align="left">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v" />" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:notEqual>
							</logic:iterate>
							</tbody>
						</table>
				</logic:notEmpty>
				</div>
				
			
				<logic:notEmpty name="update">
				<logic:equal name="update" value="yes">
					<script>
                      alert("删除成功!");
                    </script>
				</logic:equal>
				<logic:equal name="update" value="no">
					<script>
                      alert("删除失败");
                    </script>
				</logic:equal>
				</logic:notEmpty>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
