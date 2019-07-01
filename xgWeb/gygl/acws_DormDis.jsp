<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xsgyglFunction.js"></script>
	</head>
	<body>	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 宿舍分配 - 按床位数划分</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/acws_DormDis" method="post">
		    <input type="hidden" id="nj" name="nj"
							value="<bean:write name="nj" scope="request"/>" />
			 <input type="hidden" id="addordel" name="addordel"
							value="<bean:write name="addordel" scope="request"/>" />	
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span> 
							 <logic:equal value="add" name="addordel"><font color="bule">床位划分</font></logic:equal>
			      			 <logic:equal value="del" name="addordel"><font color="bule">床位释放</font></logic:equal>
			      			 </span>
						</th>
					</tr>
				</thead>		
				<tbody>			
					<tr>
						<td colspan="4" align="center">
						    <br/>
							 选&nbsp;择&nbsp;床&nbsp;位&nbsp;数:
							 <html:select property="cws" style="width:70px" styleId="cws">
							  <html:options collection="cwsList" labelProperty="cn" property="en"/>
							 </html:select>
							 <p>
							 <p>
						</td>
					</tr>	
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
									<button id="buttonSave" 
										onclick="AcwsFpSend();return false;"
										style="width: 80px">
										确  定
									</button>
								&nbsp;&nbsp;
								<button id="buttonClose" onclick="Close();return false;"
									style="width: 80px">
									关	闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>		
			</table>
		</html:form>		
  </body>
</html>

