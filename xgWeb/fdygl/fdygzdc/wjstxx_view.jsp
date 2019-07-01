<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/fdyglFunction.js"></script>
			
	</head>
	<body >
		<html:form action="/wjstxxwh" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>辅导员管理 - 信息维护 - 辅导员工作调查问卷维护 - 问题选项详细信息</a>
				</p>
			</div>
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="id" name="id"
				value="<bean:write name="id" scope="request"/>" />
			<input type="hidden" id="search_go" onclick="refreshForm('/xgxt/wjstxxwh.do')"/>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span> 第 <bean:write name="id" scope="request"/> 道问题：
						 <bean:write name="stmc" scope="request"/></span></th>
			        </tr>
			    </thead>
			   </table>
			 </div>
			
			<div class="formbox">
			 <h3 class="datetitle_01">
			    <span>
			    	项目奖项&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
			 		 记录数：
						<bean:write name="rsNum" />
			 		 </logic:notEmpty>
			    </span>
			    </h3>
				<logic:notEmpty name="rs">
					 <table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
                              <logic:iterate id="tit" name="topTr" offset="0">
                                <td id="<bean:write name="tit" property="en"/>"
                                    onclick="tableSort(this)" nowrap>
                                    <bean:write name="tit" property="cn"/>
                                </td>
                              </logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate  name="rs" id="s">
						   <tr onclick="rowOnClick(this)" style="cursor:hand"
						       ondblclick="">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="1">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
			</logic:notEmpty>		
			</div>	
		</html:form>		
	</body>	
</html>
