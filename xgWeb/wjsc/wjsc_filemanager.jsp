<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript">
			function fileExpData(){
				document.forms[0].action = '/xgxt/fileExpData.do?act=fileExpData';
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
			}
		</script>
		<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>文件管理 - 管理 - 文件管理</a>
				</p>
		</div>
		<html:form action="/fileManager.do" method="post">
		<input type="hidden" id="tableName" name="tableName"
						value="view_scwjxx" />
					<input type="hidden" id="realTable" name="realTable"
						value="wjsc_scwjxxb" />
		<div class="toolbox">
			<div  class="buttonbox">
				<logic:equal value="yes" name="writeAble" scope="request">
					<ul>
						<li><a href="#" onclick="fileExpData()" class="btn_dc"> 导出 </a></li>
					</ul>
				</logic:equal>
			</div>
			<div class="searchtab">
			  <table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
		              <button class="btn_cx"  id="search_go"
							onclick="allNotEmpThenGo('/xgxt/fileManager.do?go=go')">
							查询
					  </button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
		      <tbody>
				<tr>
					<th align="left">
						<div align="left">
						文件名：
							<html:text property="wjm" styleId="wjm"></html:text>
						<!--接收人：
							<html:text property="jsr" styleId="jsr"></html:text>-->
						</th>
						<input type="text" name="ycwbk" id="ycwbk" style="display:none"/>
						</tr>
					</tbody>
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
			    </span>
			    </h3>
				<logic:notEmpty name="rs">
						  <table summary=""  class="dateline" align="" id="rsTable" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
							<logic:iterate name="rs" id="s">
									
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<logic:iterate name="s" id="v" offset="0" length="1">
										<td>
											<a href="fileView.do?wjh=${v}" target="_blank" style="color:blue">
												${v}
											</a>
										</td>
									</logic:iterate>
									<logic:iterate name="s" id="v"  offset="1">
										<td>
											${v }
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					<script language="javascript">
						var tab = document.getElementById('rsTable');
						var line = tab.getElementsByTagName('tr');
						for(var i=1;i<line.length;i++){
							var jsz = line[i].cells[2];
							var jsr = line[i].cells[3];
							jsz.title = jsz.innerText;
							jsr.title = jsr.innerText;
							if(jsz.innerText.length>10){
								jsz.innerText = jsz.innerText.substring(0,10)+'...';
							}
							if(jsr.innerText.length>10){
								jsr.innerText = jsr.innerText.substring(0,10)+'...';
							}
						}
					</script>
				</logic:notEmpty>
				<div id="tmpdiv"></div>
			</div>
		</html:form>
	</body>
</html>

