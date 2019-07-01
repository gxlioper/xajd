<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/khgl/khxmgl/js/khxmgl.js"></script>	
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
		
		</script>
	</head>
	<body>
		<html:form action="/khglKhxmgl" method="post"
			styleId="KhxmglForm" onsubmit="return false;">
			<input type="hidden" id="xmid" name="xmid" value="${rs.xmid }"/> 
			<input type="hidden" id="khlx" name="khlx" value="${rs.khlx }"/> 
			<input type="hidden" id="pfdxJson" name="pfdxJson"/>
			<div style='tab;width:100%;overflow:hidden;'>
				<table align="center" class="formlist">
						<thead>
						<tr>
							<th colspan="4">
								<span>考核项目信息</span>
							</th>
						</tr>
						</thead>
					<tbody>
						<tr>
							<th width="20%">
								项目名称
							</th>
							<td width="30%">
								${rs.xmmc}
							</td>
							<th width="20%">
								考核对象
							</th>
							<td width="30%">
								${rs.khdxmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								评分开始时间
							</th>
							<td width="30%">
							${rs.kssj}
							</td>
							<th width="20%">
								评分结束时间
							</th>
							<td width="30%">
								${rs.jssj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								项目描述
							</th>
							<td colspan="3">
								${rs.xmms}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>
									评分对象信息&nbsp;&nbsp;
									 <a onclick="addRow();" href="javascript:void(0);">
									 	<img src="xsxx/fbgl/images/add-icons-1.gif" />
									 </a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<div  id="pfdxDiv"  style="width:100%;height:350px;overflow-y:auto;vertical-align: top;">
								<table name="tab_pfdx" class="dateline" width="100%" id="tab_orign" style="display:none">
									<tr><td colspan="4" >
									<span style="float:left">
									<a  style="text-align: left;" onclick="showPfzmx(this,'orign');" class="up"
											href="javascript:void(0);"> <font color="blue">点击展开/收起</font>
										</a>
										</span>
										<span style="float:right">
										<a style="text-align: right"  onclick="delRow(this);"
											href="javascript:void(0);"><img src="xsxx/fbgl/images/close-icons.gif" />
										</a>
										</span>
										</td>
										</tr>
										<tbody id="pfzTbody_orign" class="pfdxTbody">
										<tr name="pfzTr">
											<th width="20%">
												<span class="red">*</span>评分组
											</th>
											<td width="30%">
												<html:select  property="pfzid" styleId="pfzid_orign" onchange="changePffw(this)">
												<html:options collection="pfzList" labelProperty="pfzmc" property="pfzid"/>
												</html:select>
											</td>
											<th name="pffwTh" width="20%">
												<span class="red">*</span>评分组范围
											</th>
											<td name="pffwTd" width="30%">
												<html:select  property="sjfwdm" styleId="sjfwdm_orign">
												<html:options collection="pffwList" labelProperty="sjfw" property="sjfwdm"/>
												</html:select>
											</td>
										</tr>
										<tr>
											<th width="20%">
												<span class="red">*</span>考核表
											</th>
											<td colspan="3">
												<html:select  property="khbid" styleId="khbid_orign">
												<html:options collection="khbList" labelProperty="khbmc" property="khbid"/>
												</html:select>
											</td>
										</tr>
										<tr>
											<th width="20%">
												<span class="red">*</span>计算方式
											</th>
											<td width="30%">
												<html:select  property="jsfs" styleId="jsfs" onchange="changeJsfs(this)" >
												<html:options collection="jsfsList" labelProperty="mc" property="dm"/>
												</html:select>
											</td>
											<th width="20%">
												<span class="red">*</span>所占权重
											</th>
											<td width="30%">
												<html:text property="szqz" styleId="szqz_orign" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>%
											</td>
										</tr>
										<tr style="display:none">
											<th width="20%">
												<span class="red">*</span>计算方式百分比
											</th>
											<td colspan="3">
												<html:text property="jsfsbfb" styleId="jsfsbfb_orign" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>%
											</td>
										</tr>
										</tbody>
									</table>
								<logic:empty name="pfdxList">
								<table name="tab_pfdx" class="dateline" width="100%" id="tab_orign_1">
									<tr><td colspan="4" >
									<span style="float:left">
									<a  style="text-align: left;" onclick="showPfzmx(this,'orign');" class="up"
											href="javascript:void(0);"> <font color="blue">点击展开/收起</font>
										</a>
										</span>
										<span style="float:right">
										<a style="text-align: right"  onclick="delRow(this);"
											href="javascript:void(0);"><img src="xsxx/fbgl/images/close-icons.gif" />
										</a>
										</span>
										</td>
										</tr>
										<tbody id="pfzTbody_orign" class="pfdxTbody">
										<tr name="pfzTr">
											<th width="20%">
												<span class="red">*</span>评分组
											</th>
											<td width="30%">
												<html:select  property="pfzid" styleId="pfzid_orign" onchange="changePffw(this)">
												<html:options collection="pfzList" labelProperty="pfzmc" property="pfzid"/>
												</html:select>
											</td>
											<th name="pffwTh" width="20%">
												<span class="red">*</span>评分组范围
											</th>
											<td name="pffwTd" width="30%">
												<html:select  property="sjfwdm" styleId="sjfwdm_orign">
												<html:options collection="pffwList" labelProperty="sjfw" property="sjfwdm"/>
												</html:select>
											</td>
										</tr>
										<tr>
											<th width="20%">
												<span class="red">*</span>考核表
											</th>
											<td colspan="3">
												<html:select  property="khbid" styleId="khbid_orign">
												<html:options collection="khbList" labelProperty="khbmc" property="khbid"/>
												</html:select>
											</td>
										</tr>
										<tr>
											<th width="20%">
												<span class="red">*</span>计算方式
											</th>
											<td width="30%">
												<html:select  property="jsfs" styleId="jsfs" onchange="changeJsfs(this)">
												<html:options collection="jsfsList" labelProperty="mc" property="dm"/>
												</html:select>
											</td>
											<th width="20%">
												<span class="red">*</span>所占权重
											</th>
											<td width="30%">
												<html:text property="szqz" styleId="szqz_orign" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>%
											</td>
										</tr>
										<tr style="display:none">
											<th width="20%">
												<span class="red">*</span>计算方式百分比
											</th>
											<td colspan="3">
												<html:text property="jsfsbfb" styleId="jsfsbfb_orign" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>%
											</td>
										</tr>
										</tbody>
										
									</table>
								</logic:empty>
								<logic:iterate id="i" name="pfdxList" indexId="index01">
									<table name="tab_pfdx" class="dateline" width="100%" id="tab_${index01}">
									<tr><td colspan="4" >
									<span style="float:left">
									<a  style="text-align: left;" onclick="showPfzmx(this,${index01});" class="up"
											href="javascript:void(0);"> <font color="blue">点击展开/收起</font>
										</a>
										</span>
										<span style="float:right">
										<a style="text-align: right"  onclick="delRow(this);"
											href="javascript:void(0);"><img src="xsxx/fbgl/images/close-icons.gif" />
										</a>
										</span>
										</td>
										</tr>
										<tbody id="pfzTbody_${index01}" class="pfdxTbody">
										<tr name="pfzTr">
											<th width="20%">
												<span class="red">*</span>评分组
											</th>
											<td width="30%">
												<html:select  property="pfzid" styleId="pfzid_${index01}" value="${i.pfzid}" onchange="changePffw(this)">
												<html:options collection="pfzList" labelProperty="pfzmc" property="pfzid"/>
												</html:select>
											</td>
											<th name="pffwTh" width="20%">
											<logic:equal name='i' property='sfnz' value="2">
												<span class="red">*</span>评分组范围
											</logic:equal>
											</th>
											<td name="pffwTd" width="30%">
											<logic:equal name='i' property='sfnz' value="2">
												<html:select  property="sjfwdm" styleId="sjfwdm_${index01}" value="${i.sjfwdm}">
												<html:options collection="pffwList${index01}" labelProperty="sjfw" property="sjfwdm"/>
												</html:select>
											</logic:equal>
											<logic:notEqual name='i' property='sfnz' value="2">
												<html:select  property="sjfwdm" styleId="sjfwdm_${index01}" value="${i.sjfwdm}" style="display:none" >
												<html:options collection="pffwList${index01}" labelProperty="sjfw" property="sjfwdm"/>
												</html:select>
											</logic:notEqual>
											</td>
										</tr>
										<tr>
											<th width="20%">
												<span class="red">*</span>考核表
											</th>
											<td colspan="3">
												<html:select  property="khbid" styleId="khbid_${index01}" value="${i.khbid}">
												<html:options collection="khbList" labelProperty="khbmc" property="khbid"/>
												</html:select>
											</td>
										</tr>
										<tr>
											<th width="20%">
												<span class="red">*</span>计算方式
											</th>
											<td width="30%">
											<html:select property="jsfs" styleId="jsfs_${index01}" value="${i.jsfs}" onchange="changeJsfs(this)">
												<html:options collection="jsfsList" labelProperty="mc" property="dm"/>
											</html:select>
											</td>
											<th width="20%">
												<span class="red">*</span>所占权重
											</th>
											<td width="30%">
												<html:text property="szqz" styleId="szqz_${index01}" value="${i.szqz}" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>%
											</td>
										</tr>
										<logic:equal name='i' property='jsfs' value="2">
										<tr>
											<th width="20%">
												<span class="red">*</span>计算方式百分比
											</th>
											<td colspan="3">
												<html:text property="jsfsbfb" styleId="jsfsbfb_${index01}" value="${i.jsfsbfb}" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>%
											</td>
										</tr>
										</logic:equal>
										<logic:notEqual name='i' property='jsfs' value="2">
										<tr style="display:none">
											<th width="20%">
												<span class="red">*</span>计算方式百分比
											</th>
											<td colspan="3">
												<html:text property="jsfsbfb" styleId="jsfsbfb_${index01}" value="${i.jsfsbfb}" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>%
											</td>
										</tr>
										</logic:notEqual>
										</tbody>
									</table>
									</logic:iterate>
								</div>
							</td>
						</tr>
						
					</tbody>
					</table>
			      </div>
			      <div style="height: 35px"></div>
			      <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" type="button"
										onclick="savePfdxSz();return false;">
										保 存
									</button>
									<button type="button" type="button"
										onclick="iFClose();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		
		</html:form>
	</body>
</html>

