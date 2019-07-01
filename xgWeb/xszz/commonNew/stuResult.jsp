<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
			<script language="javascript" src="js/function.js"></script>
	
			<script language="javascript">
		        function result_query(){
		       		var temp = document.getElementById("gnmkmc").value;
		       		if(temp == null){
		       			alert("请先选择要查询的项目!");
		       		}else{
						document.forms[0].action = "/xgxt/new_common_xszz.do?method=stuResultCd&doType=query&act=go";
				 		document.forms[0].submit();
					}
			}
		
		function chkAssisOne() {
			if (curr_row == null) {
				return false;
			} else {
				url = curr_row.getElementsByTagName("input")[0].value;
				if (url==""){
					return false;
				}
				url = url.replace("&amp;","&");
				showTopWin(url, 750, 550);
				return true;
			}
		}
		    </script>
	</head>
	<body>
		<html:form action="/stu_result_cdopen" method="post">
			<div class="tab">
			<table width="100%"  border="0" class="formlist">
				<thead>
					<tr>
	    				<th colspan="6"><span>请选择要查询的项目</span></th>
	    			</tr>
				 </thead>
				 <tbody>
				<tr>
					<td align="center">
						<html:select name="rs1" property="gnmkmc" styleId="gnmkmc">
								<html:option value="">---------请选择--------</html:option>
								<html:options collection="list" property="gnmkmc"
									labelProperty="gnmkmc" />
							</html:select>
					</td>
				</tr>
				</tbody>
				<tfoot>
				<tr>
					<td align="right">
						<button  onclick=result_query()
							style="width:80px" value="确  定" />确  定</button>
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
				<fieldset>
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序</font> 
						</span>
					</h3>
					<table summary="" class="dateline" align="" width="100%">
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
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="chkAssisOne();">
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="hidden" value="<bean:write name="v"/>" />
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="1">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
			</logic:notEmpty>
			</div>
		</html:form>
	</body>
</html>

