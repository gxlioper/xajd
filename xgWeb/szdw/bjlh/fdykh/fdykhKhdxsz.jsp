<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		function fdyKhdxsz(url){
			var pkV=document.getElementsByName("checkVal");
			blog=false;
			var str = "";
			
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked){
					blog=true;
					str+="&checkVal="+pkV[i].value;
				}
			}
			if(blog){
				url+=str;
				
				showTopWin(url,800,600);
				hiddenMessage(true,true);
			}else{
				alertInfo("没有需要设置考核的记录!");
				return false;
			}
		}
		
		function searchRs(){
			allNotEmpThenGo('bjlh_fdykh_khdxsz.do');
		}
</script>

	</head>
	<body>
		<html:form action="/bjlh_fdykh" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<!-- 模块类型 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="fdyKhdxsz('bjlh_fdykh.do?method=fdykhKhdxszEdit&doType=sz');return false;" class="btn_sq"> 设置 </a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs" >
								<font color="blue"> 
									提示：单击表头可以排序;</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
				<logic:notEmpty name="rs">
			<div class="con_overlfow">
				 <table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="${tit }" onclick="tableSort(this)">
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand" align="left">
								<td align="center">
									<input type="checkbox" id="checkVal" name="checkVal" 
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
								</td>
								<logic:iterate id="v" name="s" offset="0" length="4">
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
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=bjlhFdykhForm"></jsp:include>
			  	<!--分页显示-->
			</logic:notEmpty>
			</div>
		</html:form>
		
	</body>
</html>