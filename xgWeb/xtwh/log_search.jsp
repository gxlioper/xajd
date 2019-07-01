<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
			function CXsubmit(mustFill){
				var obj = mustFill.split("-");
				for(var i=0; i<obj.length; i++){
					if(document.getElementById(obj[i]).value == ""){
						alert("请输入完整的查询条件！");
						return false;
					}
				}
				
				if($("dateF").value>$("dateT").value){
					alert("开始操作日期不能大于结束操作日期!");
					return false;
				}
				document.forms[0].action = "/xgxt/log_search.do";
				document.forms[0].submit();
			}
			
		    function viewMoreinfo(doType){
		      var url ="logViewinfo.do?doType=view&pkValue=";
		      var pkValue ="";
		
		      if (doType == "view"){
		         pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url, 620, 400);
		      }
	    	}
			
		</script>
		</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>系统维护 - 操作日志 - 查询</a>
			</p>
		</div>
	

		<html:form action="/log_search" method="post">
		<div class="toolbox">
			<!-- 过滤条件 -->
		  <div class="searchtab">
		    <table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
		              <button type="button" class="btn_cx" id="search_go" 
		              	onclick="CXsubmit('dateF-dateT')">
		              	查 询
		              </button>
		              &nbsp;&nbsp;&nbsp;&nbsp;
		              <button type="button" class="btn_cz" id="btn_cz" 
		              	onclick="searchReset();return false;">
		              	重 置
		              </button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			  <tbody>
		      	<tr>
		      		<th>操作员</th>
		      		<td>
		      			<html:text property="yhm" styleId="yhm" style="width:60px;" />
		      		</td>
					<th>用户组</th>
		      		<td>
		      			<html:select property="zdm">
							<html:option value=""></html:option> 
							<html:options collection="yhzList" property="zdm" labelProperty="zmc"/> 
						</html:select>
		      		</td>
		      		
		        </tr>
		        <tr>
		        	<th><font color="red">*</font>操作日期</th>
		      		<td>
								<html:text style="cursor:hand; width:75px;" styleId="dateF"
									property="dateF"
									onclick="return showCalendar('dateF','y-mm-dd');"
									readonly="readonly" />
								-
								<html:text style="cursor:hand; width:75px;" styleId="dateT"
									property="dateT"
									onclick="return showCalendar('dateT','y-mm-dd');"
									readonly="readonly" />
		      		</td>
		      		<th>IP段</th>
		      		<td>
								<html:text property="ipF" style="width:80px;" styleId="ipF" />
								-
								<html:text property="ipT" style="width:80px;" styleId="ipT" />
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
			    </span>
			    </h3>
			   
			  <logic:notEmpty name="rs">
			  <table summary="" class="dateline" align="" width="100%">
			    <thead>
			      <tr>
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
								<tr onclick="rowOnClick(this)" 
									style="cursor:hand">
									<logic:iterate id="v" name="s" offset="0">
									<td align="center">
										<bean:write name="v" />
									</td>
									</logic:iterate>									
								</tr>
					</logic:iterate>
			    </tbody>
			  </table>
			  <!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
			      <script type="text/javascript">
					$('choose').className="hide";
				</script>
			  <!--分页显示-->
			  		<script type="text/javascript">
				$('choose').className="hide";
			</script>
			  </logic:notEmpty>
			</div>		
		</html:form>
	</body>
</html>
