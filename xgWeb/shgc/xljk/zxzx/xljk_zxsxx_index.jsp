<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/jsFunction.js">
	</script>
	<script type="text/javascript">
	function getZxsxx(){
		var url='/xgxt/xljk_zxsxx_pp.do?method=zxsPp';
		showTopWin(url,800,530);
	}
	</script>
	<body onload="">
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/xljk_zxsxx" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>心理健康 - 心理咨询 - 咨询师信息</a>
				</p>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
				
				
			<div class="toolbox">
			 <!-- 按钮 -->
			 <logic:notEqual value="stu" name="userType" scope="session">
			 <logic:equal value="yes" name="writeAble" scope="request">
			 <div class="buttonbox" id="btn">
			    <ul>
				  <li> <a href="#" onclick="showTopWin('/xgxt/xljk_zxsxx_add.do?act=xljk_zxsxx',580,450);" class="btn_zj"> 增加 </a> </li>
			      <li> <a href="#" onclick="viewMore_LRH('update','00')" class="btn_xg"> 修改 </a> </li>
				  <li> <a href="#" onclick="viewMore_LRH('del','00')" class="btn_sc"> 删除 </a> </li>
					<logic:present name="showzxspp">
						<li> <a href="#"  onclick="getZxsxx();" class="btn_sx"> 咨询师匹配 </a> </li>
					</logic:present>
			    </ul>
			 </div>
			 </logic:equal>
			 </logic:notEqual>
			<div class="searchtab">
				<table width="100%" border="0">
				      <tfoot>
				        <tr>
				          <td colspan="6">
				            <div class="btn">
				              <input type="hidden" name="go" value="a" />
				              <button class="btn_cx" id="search_go" 
				              	onclick="refreshForm('/xgxt/xljk_zxsxx.do?act=xljk_zxsxx&doType=Search')">
				              	查 询
				              </button>
				              <button  id="search_go2" style="display:none"
									onclick="refreshForm('/xgxt/xljk_zxsxx.do?act=xljk_zxsxx&doType=Search')">
							  </button>
				              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
				              	重 置
				              </button>
				            </div>
				          </td>
				        </tr>
		      </tfoot>
				
				
					<tbody>
						<tr>
							<th align="left">
								咨询师编号
							</th>
							<td>
								<html:text property="zxxbh" style="width:105px"></html:text>
							</td>
							<th>
								咨询师姓名
							</th>
							<td>
								<html:text property="zxxxm" style="width:105px"></html:text>
							</td>
							<th>
								性别
							</th>
							<td>
								<html:select property="sex_dm" style="width:105px" styleId="sex_dm"
									onchange="">
									<html:option value=""></html:option>
									<html:options collection="sexList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
				</div>
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
						<bean:write name="rsNum" />&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息；<logic:notEqual value="stu" name="userType" scope="session">单击表头可以排序</logic:notEqual></font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>

			<logic:notEmpty name="rs">
					 <table summary="" class="dateline" align="" width="100%">
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
							<logic:iterate name="rs" id="s" offset="0" >
									<tr onclick="rowOnClick(this);" style="cursor:hand" 
									ondblclick="viewMore_LRH('View_Zxsxx',this.cells[0].innerText)">
										<td id="ZXXBH">
											<bean:write name="s" property="ZXXBH" />
										</td>
										<td>
											<bean:write name="s" property="ZXXXM" />
										</td>
										<td>
											<bean:write name="s" property="ZXXXB" />
										</td>
										<td>
											<bean:write name="s" property="ZXXZG" />
										</td>
										<logic:equal name="xxdm" value="10395">
											<td>
												<bean:write name="s" property="ZC" />
											</td>
										</logic:equal>
									</tr>
							</logic:iterate>
						</tbody>
					</table>
			</logic:notEmpty>	
			</div>
			</html:form>	
	</body>
</html>
