<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xszz/xszzComm.js"></script>
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	</head>

	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/commXszz">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden name="rs" property="rssx"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<!-- 隐藏域 end-->
			<div class="tab">		
			<!-- 项目基本情况 -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>项目基本情况</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr style="height: 23px">
						<th  width="15%">
							项目名称
						</th>
						<td  width="35%">
							<html:hidden name="rs" property="xmdm"/>
							<html:select name="rs" property="xmdm" style="" styleId="xmdm" disabled="true">
								<html:options collection="zzxmList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
						<th  width="15%">
							人数控制级别
						</th>
						<td  width="35%">
							<html:hidden name="rs" property="kzjb"/>
							<html:select name="rs" property="kzjb" style="" styleId="kzjb" onchange="" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="kzjbList" property="en" labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr style="height: 23px">
						<th  width="">
							学年
						</th>
						<td align="left" width="">
							<html:hidden name="rs" property="xn"/>
							<html:select name="rs" property="xn" style="width:120px" styleId="xn" disabled="true">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>
						</td>
						<th  width="">
							学期
						</th>
						<td align="left" width="">
							<html:hidden name="rs" property="xq"/>
							<html:select name="rs" property="xq" style="" styleId="xq" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr style="height: 23px">
						<th width="">
							年度
						</th>
						<td width="">
							<html:hidden name="rs" property="nd"/>
							<html:select name="rs" property="nd" style="" styleId="nd" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="ndList" property="nd" labelProperty="nd" />
							</html:select>
						</td>
						<th width="">
							分配方式
						</th>
						<td >
							<html:radio name="rs" property="fpfs" styleId="blRad" onclick="" value="比例"/>比例
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<html:radio name="rs" property="fpfs" styleId="rsRad" onclick="" value="人数	"/>人数		
						</td>
					</tr>
					<tr style="height: 23px">
						<th>
							项目说明：
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="xmsm" rows="4" style="width : 600px" readonly="true"></html:textarea>
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center">
							<logic:notEmpty name="rs" property="rssx">
								<font color="blue">(注：该项目的人数上限为${rs.rssx }人)</font>
							</logic:notEmpty>
							<logic:empty name="rs" property="rssx">
								<font color="blue">(该项目无人数上限)</font>
							</logic:empty>							
						<td>
					</tr>
				</tbody>
			</table>
			<!-- 学院人数设置 -->
			<logic:equal name="rs" property="kzjb" value="<bean:message key="lable.xb" />">
				<%@ include file="rssz/xyRssz.jsp"%>
			</logic:equal>
			<!-- 专业人数设置 -->
			<logic:equal name="rs" property="kzjb" value="专业">
				<%@ include file="rssz/zyRssz.jsp"%>
			</logic:equal>
			<!-- 班级人数设置 -->
			<logic:equal name="rs" property="kzjb" value="班级">
				<%@ include file="rssz/bjRssz.jsp"%>
			</logic:equal>
			<!-- 条件设置 end -->
			<table border="0" class="formlist" align="center" style="width: 100%">
				<tfoot>
					<tr>
						<td align="center">
							<div class="btn">
								<!-- 非查看 -->
								<logic:notEqual name="doType" value="view">
									<button type="button" id="buttonSave" onclick="showRsszDiv()" style="width: 80px">
										保	存
									</button>
									&nbsp;&nbsp;
								</logic:notEqual>
								<button type="button" id="buttonSave" onclick="window.close();return false;" style="width: 80px">
									关	闭
								</button> 
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<div id="tmpdiv1"></div>
			<!-- 提示信息 -->
			<%@ include file="other/tsxx.jsp"%>
		</html:form>
	</body>
</html>