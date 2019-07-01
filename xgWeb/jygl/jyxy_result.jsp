<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		function viewMoreinfo1(doType){
		var url ="/xgxt/JyglJyxyViewMoreinfo.do?doType=view&pkValue=";
		var pkValue ="";
		
		 if (doType == "view"){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showTopWin(url, 700, 600);
		 }
		}
		
		
		function jyxyupdate(doType){
		var url ="/xgxt/turnjyxyupdate.do?whichArea=result&doType=first&pkValue=";
		var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("请选择要修改的数据！\n（单击相应的行）");
			return false;
			 } else {
			if (confirm("确定要查看或修改该行数据吗？")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url, 900, 600);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}
		</script>
	</head>
	
	<body>
		<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>就业管理 - 就业协议方案 - 审核结果查询</a>
				</p>
			</div>
		<div class="formbox">
			<h3 class="datetitle_01">
				<span> 查询结果&nbsp;&nbsp;
					<logic:empty name="rs">
						<font color="red">未找到任何记录！</font>
					</logic:empty> 
					<logic:notEmpty name="rs">
							记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;
	                       登陆用户：
					    <bean:write name="rs1" property="userName" />
					    &nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息并修改；单击表头可以排序</font>
					</logic:notEmpty> </span>
			</h3>
			<logic:notEmpty name="rs">
			<logic:equal name="ableToView" value="teacher">
			<table summary="" class="dateline" align="" id="rsTable" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="jyxyupdate('update')">
								<td style="display:none">
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="hidden" value="<bean:write name="v"/>" />
								</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
					<script type="text/javascript">
							$('choose').className="hide";
					</script>
				</logic:equal>
				<logic:equal name="ableToView" value="student">
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="jyxyupdate('update')">
								<td style="display:none">
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="hidden" value="<bean:write name="v"/>" />
								</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
				<script type="text/javascript">
						$('choose').className="hide";
				</script>
				</logic:equal>
				
		</logic:notEmpty>
		</div>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

