<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script>
		function showCjffInfo(url){
			if(curr_row==null){
				alert('请选择要查看的岗位!');
				return false;
			}
			var pk = document.forms[0].pk.value;
			var pkValue = curr_row.getElementsByTagName("input")[0].value;
			var gwxz = curr_row.cells[6].innerText;
			var yrdw = curr_row.cells[5].innerText;
			url += "&pk=";
			url += pk;
			url += "&pkValue=";
			url += pkValue;
			url += "&gwxz=";
			url += gwxz;
			url += "&yrdw=";
			url += yrdw;
			
			showTopWin(url,800,600);
			
		}
	</script>
</head>
	<body>
		<html:form action="/data_search" method="post">
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act" value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk" value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="day" name="day" value="<bean:write name="day" scope="request"/>" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>勤工助学 - 酬金发放 - 酬金发放审核</a>
				</p>
			</div>
			<div class="toolbox">
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>						  
				          <td colspan="6">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button type="button" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/work_pay_put.do?act=workPayPut&type=audit')">
									查询
								</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					  <tr>
						<th>年度</th>
						<td>
							<html:select property="nd" style="width:80px">
								<html:option value=""></html:option>
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
						<th>学年</th>
						<td>
							<html:select property="xn" style="width:100px">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th>学期</th>
						<td>
							<html:select property="xq" style="width:90px">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					  </tr>
					  <tr>
						<th>岗位名称</th>
						<td>
							<html:select property="gwdm" style="width:130px">
								<html:option value=""></html:option>
								<html:options collection="gwList" property="gwdm"
									labelProperty="gwdm" />
							</html:select>
						</td>
						<th></th>
						<td>
							
						</td>
						<th></th>
						<td>
							
						</td>
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
						<logic:notEmpty name="rs">
							<font color="blue">提示：双击可以查询详细页面并进行审核，单击表头可以排序！</font> 
				 		 </logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
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
						<tr onclick="rowOnClick(this)" style="cursor:hand" ondblclick="showCjffInfo('whlggwgl.do?method=cjffAudit')">
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
			    </tbody>
				</table>
				</div>
				<!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
			 	<!--分页显示-->
				</logic:notEmpty>
				</div>			
		</html:form>
	</body>
</html>

