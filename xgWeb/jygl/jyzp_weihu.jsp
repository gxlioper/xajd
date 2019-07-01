<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		function querygo(){
			 	document.forms[0].action = "/xgxt/zpxxquery.do?act=go&doType=query";
			 	document.forms[0].submit();
	    }
		function deleteuser(username) {	
			var url="/xgxt/deleteuser_jyzp.do?act=go&doType=del&username=";
			url=url+username;
			 document.forms[0].action = url;
			 document.forms[0].submit();
        }
		
		
		function adduser(){
		    var username = document.getElementById("username").value;
		    var gsmc = document.getElementById("gsmc").value;
		    
		    if(username==""){
		    alert("用户名不能为空！");
		    return false;
		    }
		    if(gsmc==""){
		    alert("请指定该用户的公司名称！");
		    return false;
		    }
		    		    
		    document.forms[0].action = "/xgxt/adduser_jyzp.do?act=go&doType=add";
		 	document.forms[0].submit();
		}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>就业管理 - 模块维护 - 就业招聘维护</a>
			</p>
		</div>
		<html:form action="/data_search" method="post">
			<div class="toolbox">
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go" onclick="adduser()">
											提 交
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
									<font color="red">*</font>用户名
								</th>
								<td>
									<html:text name="rs1" property="username" style="width:200px" />
								</td>
								<th>
									<font color="red">*</font>公司名称
								</th>
								<td>
									<logic:equal name="xxdm" value="10491">
										<html:select name="rs1" property="gsmc" styleId="gsmc"
											style="width:200px">
											<html:option value=""></html:option>
											<html:options collection="gsmcList" property="dwmc"
												labelProperty="dwmc" />
										</html:select>
									</logic:equal>
									<logic:notEqual name="xxdm" value="10491">
										<html:select name="rs1" property="gsmc" styleId="gsmc"
											style="width:200px">
											<html:option value=""></html:option>
											<html:options collection="gsmcList" property="gsmc"
												labelProperty="gsmc" />
										</html:select>
									</logic:notEqual>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty>
						</span>
					</h3>

					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<td align="center">
										操作
									</td>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand">
										<logic:iterate id="v" name="s" offset="1">
											<td align="center">
												<bean:write name="v" />
												<input type="hidden" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v" /></logic:iterate>" />
											</td>
										</logic:iterate>
										<td align="center" width="10%">
											<button class="button2"
												onclick="deleteuser(this.parentNode.parentNode.cells[1].innerText)"
												style="width:60px">
												删除
											</button>
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</logic:notEmpty>
				</div>
		</html:form>
		<logic:notEmpty name="delete">
			<logic:equal name="delete" value="ok">
				<script>
                       alert("删除成功！");
                    </script>
			</logic:equal>
			<logic:equal name="delete" value="no">
				<script>
                      alert("删除失败！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="add">
			<logic:equal name="add" value="ok">
				<script>
                       alert("提交成功！");
                    </script>
			</logic:equal>
			<logic:equal name="add" value="no">
				<script>
                      alert("提交失败！请检查是否重复提交");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
