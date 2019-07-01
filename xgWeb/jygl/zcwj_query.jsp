<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
			function querygo(){
				 	document.forms[0].action = "/xgxt/zcwjquery.do?act=go&doType=query";
				 	document.forms[0].submit();
		    }
		</script>
		<script language="javascript">
			function viewMoreinfo(doType){
			var url ="/xgxt/showmorezcwj.do?wjbt=";
			var pkValue ="";
			
			 if (doType == "view"){
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
				 url += pkValue;
				window.open(url);
			 }
			}		
		</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>就业管理 - 就业指导 - 政策文件查询</a>
			</p>
		</div>

		<html:form action="/data_search" method="post">
			<div class="toolbox">
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="8">
									<div class="btn">
										<button class="btn_cx" id="search_go" onclick="querygo()">
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
									文件标题
								</th>
								<td>
									<html:text name="rs1" property="wjbt" />
								</td>
								<th>
									文件类型
								</th>
								<td>
									<html:select name="rs1" property="wjlx" style="width:120px">
										<html:option value=""></html:option>
										<html:options collection="wjlxList" property="lxdm" labelProperty="lxmc"/>
									</html:select>
								</td>
								<th>
									发布人
								</th>
								<td>
									<html:text name="rs1" property="fbr" style="width:70px" />
								</td>
								<th>
									发布时间
								</th>
								<td>
									<html:select name="rs1" property="xjsj" style="width:90px">
										<html:option value=""></html:option>
										<html:option value="-1">当天</html:option>
										<html:option value="-2">近两天</html:option>
										<html:option value="-7">一周内</html:option>
										<html:option value="-15">半月内</html:option>
										<html:option value="-30">一月内</html:option>
										<html:option value="-90">三月内</html:option>
										<html:option value="-180">半年内</html:option>
										<html:option value="-365">一年内</html:option>
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty> 
							<logic:notEmpty name="rs">
								<font color="blue">提示：双击一行可以查看详细；单击表头可以排序</font>
							</logic:notEmpty> </span>
					</h3>

					<logic:notEmpty name="rs">
						<table class="dateline" width="100%" >
							<thead>
								<tr>
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
									<tr onmouseover="rowOnClick(this)" style="cursor:hand"
										ondblclick="viewMoreinfo('view')">
										<logic:iterate id="v" name="s" offset="1">
											<td>
												<bean:write name="v" />
												<input type="hidden" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
						<!--分页显示-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
						<!--分页显示-->
						<script type="text/javascript">
							$('choose').className="hide";
						</script>
					</logic:notEmpty>
				</div>
				
		</html:form>
	</body>
</html>

