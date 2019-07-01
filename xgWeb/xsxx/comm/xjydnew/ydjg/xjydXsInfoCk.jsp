<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsxx/comm/xjydnew/js/xjydjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script language="javascript" defer="defer">
		jQuery(function() {
			jQuery("#myTbody").css("display","none");
			initShow();
		});
		function showTbody(obj,objTbody){
			if(obj.className=="up"){
				obj.className="down";
				obj.parentNode.parentNode.className="cur-tr";
				document.getElementById(objTbody).style.display="none";
			}else{
				obj.className="up";
				obj.parentNode.parentNode.className="";
				document.getElementById(objTbody).style.display="";
			}
		}
		
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/ydjg">
		<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="ydlbdm" name="xsydInfo" styleId="ydlbdm"/>
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
				<thead>
					<tr>
						<th colspan="4">
							<span>学籍异动信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="16%">
							学籍异动类别
						</th>
						<td align="left">
							<span style="color:red;">${xsydInfo.ydlbmc }</span>
						</td>
						<th align="right">
							学年/学期
						</th>
						<td align="left">
							
							${xsydInfo.xn } ${xsydInfo.xqmc } 
						</td>
					</tr>
					<tr>
						<th>学籍状态[异动]</th>
						<td colspan="3">
							<div >
								<table border="0" style="float:left">
									<tr>
										<th style="width:70px">原学籍类别</th>
										<td style="width:180px">${xsydInfo.ydqxjlbmc }</td>
									</tr>
									<tr>
										<th>是否有学籍</th>
										<td>&nbsp;${xsydInfo.ydqsfyxjmc }</td>
									</tr>
									<tr>
										<th>是否在校</th>
										<td>&nbsp;${xsydInfo.ydqsfzxmc }</td>
									</tr>
								</table>
								<img style="float:left;margin:30px 10px" src='images/ssyd/to.gif' ></img>
								<table border="0"  style="float:left">
									<tr>
										<th style="width:90px">异动后学籍类别</th>
										<td style="width:180px">${xsydInfo.ydlbmc }</td>
									</tr>
									<tr>
										<th>是否有学籍</th>
										<td >${xsydInfo.ydhsfyxjmc }</td>
									</tr>
									<tr>
										<th>是否在校</th>
										<td >${xsydInfo.ydhsfzxmc }</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr id="tzbj">
						<th>调整班级</th>
						<td colspan="3">
							<div >
								<table border="0"  style="float:left">
									<tr>
										<th style="width:70px;height:20px;">原年级</th>
										<td style="width:180px" id="ydqnj">&nbsp;${xsydInfo.ydqnj }</td>
									</tr>
									<tr>
										<th style="height:20px;">原<bean:message key="lable.xb" /></th>
										<td id="ydqxy">&nbsp;${xsydInfo.ydqxymc }</td>
									</tr>
									<tr>
										<th style="height:20px;">原专业</th>
										<td id="ydqzy">&nbsp;${xsydInfo.ydqzymc }</td>
									</tr>
									<tr>
										<th style="height:20px;">原班级</th>
										<td id="ydqbj">&nbsp;${xsydInfo.ydqbjmc }</td>
									</tr>
								</table>
								<img style="float:left;margin:55px 10px" src='images/ssyd/to.gif' ></img>
								<table border="0" style="float:left">
									<tr>
										<th style="width:90px;height:20px;">异动后年级</th>
										<td style="width:180px">${xsydInfo.ydhnj }
										</td>
									</tr>
									<tr>
										<th style="height:20px;">异动后<bean:message key="lable.xb" /></th>
										<td>${xsydInfo.ydhxymc }
										</td>
									</tr>
									<tr>
										<th style="height:20px;">异动后专业</th>
										<td>${xsydInfo.ydhzymc }
										</td>
									</tr>
									<tr>
										<th style="height:20px;">异动后班级</th>
										<td>${xsydInfo.ydhbjmc }
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					
					<logic:equal name="xxdm" value="10511">					
						<tr id="xzpd">
							<th align="right" width="10%">
								学制
							</th>
							<td align="left" colspan="3">
								${xsydInfo.xz }
							</td>
						</tr>
						<tr id="xxpd">
							<th align="right">
								来源学校/去向学校
							</th>
							<td align="left" colspan="3">
								${xsydInfo.xxmc }
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								异动原因
							</th>
							<td align="left" colspan="3">
								${xsydInfo.ydyymc }
							</td>
						</tr>
					</logic:equal>
					<tr>
						<th align="right" width="10%">
							附件信息
						</th>
						<td colspan="3">
							<div id="commonfileupload-list-0" style="padding: 5px;"></div>
							<script type="text/javascript">
								//调用附件 
								jQuery(function(){
									var gid = "${xsydInfo.filepath}";
									jQuery.MultiUploader_q({
										gid : gid,
										targetEl : 'commonfileupload-list-0'
										});
								});
							</script>
						</td>
					</tr>
					
					<tr>
						<th align="right" width="10%">
							申请理由&nbsp;					
						</th>
						<td colspan="3">
						${xsydInfo.sqly }
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							学籍异动文号&nbsp;
						</th>
						<td>
							<span style="color:blue;">${xsydInfo.xjydwh }</span>
						</td>
						
						<logic:notEqual name="xxdm" value="10511">
							<th align="right" width="10%">
								学籍异动时间&nbsp;
							</th>
							<td>
								${xsydInfo.xjydsj }
							</td>
						</logic:notEqual>
						
						<logic:equal name="xxdm" value="10511">
							<th align="right" width="10%">
								学籍异动审核时间&nbsp;
							</th>
							<td>
								${xsydInfo.xjydsj }
							</td>
						</logic:equal>
					</tr>
					<tr id="lrqzsj">
						<th>异动起止时间</th>
						<td colspan="3">
							${xsydInfo.sqkssj } &nbsp;至&nbsp; ${xsydInfo.sqjssj }
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							备注&nbsp;
						</th>
						<td colspan="3">
							${xsydInfo.xjydbz }
						</td>
					</tr>
				</tbody>
			</table>
			<br/>
			<table width="100%"  border="0" class="formlist">
			    <thead>
			      <tr>
			      	<th colspan="2">
			      	  <a href="#" class="down" onclick="showTbody(this,'myTbody');return false">更多学籍异动信息</a>
			   	    </th>
			      </tr>
			    </thead>
			</table>
			<div class="regform" style="padding-top:0px;">
				<div id="myTbody" style="padding-bottom:10px;">
					<logic:notEmpty name="xsYdList">
					  <logic:iterate id="s" name="xsYdList" indexId="indexId">
						<table width="100%" border="0" class="formlist">
						  	<tbody>
								<tr>
									<th align="right" width="16%">
										学籍异动类别
									</th>
									<td align="left" width="34%">
										<span style="color:red;"><bean:write name="s" property="ydlbmc"/></span>
									</td>
									<th align="right" width="16%">
										学年/学期
									</th>
									<td align="left" width="34%">
										<bean:write name="s" property="xn"/>  <bean:write name="s" property="xqmc"/>
									</td>
								</tr>
								
								<tr>
									<th>学籍状态[异动]</th>
									<td colspan="3">
										<div >
											<table border="0" style="float:left">
												<tr>
													<th style="width:70px">原学籍类别</th>
													<td style="width:180px"><bean:write name="s" property="ydqxjlbmc"/></td>
												</tr>
												<tr>
													<th>是否有学籍</th>
													<td>&nbsp;<bean:write name="s" property="ydqsfyxjmc"/></td>
												</tr>
												<tr>
													<th>是否在校</th>
													<td>&nbsp;<bean:write name="s" property="ydqsfzxmc"/></td>
												</tr>
											</table>
											<img style="float:left;margin:30px 10px" src='images/ssyd/to.gif' ></img>
											<table border="0"  style="float:left">
												<tr>
													<th style="width:90px">异动后学籍类别</th>
													<td style="width:180px"><bean:write name="s" property="ydlbmc"/></td>
												</tr>
												<tr>
													<th>是否有学籍</th>
													<td><bean:write name="s" property="ydhsfyxjmc"/></td>
												</tr>
												<tr>
													<th>是否在校</th>
													<td><bean:write name="s" property="ydhsfzxmc"/></td>
												</tr>
											</table>
										</div>
									</td>
								</tr>
								<logic:equal name="s" property="sftjbj" value="0">
									<tr>
										<th>调整班级</th>
										<td colspan="3">
											<div >
												<table border="0"  style="float:left">
													<tr>
														<th style="width:70px;height:20px;">原年级</th>
														<td style="width:180px" id="ydqnj">&nbsp;<bean:write name="s" property="ydqnj"/></td>
													</tr>
													<tr>
														<th style="height:20px;">原<bean:message key="lable.xb" /></th>
														<td id="ydqxy">&nbsp;<bean:write name="s" property="ydqxymc"/></td>
													</tr>
													<tr>
														<th style="height:20px;">原专业</th>
														<td id="ydqzy">&nbsp;<bean:write name="s" property="ydqzymc"/></td>
													</tr>
													<tr>
														<th style="height:20px;">原班级</th>
														<td id="ydqbj">&nbsp;<bean:write name="s" property="ydqbjmc"/></td>
													</tr>
												</table>
												<img style="float:left;margin:55px 10px" src='images/ssyd/to.gif' ></img>
												<table border="0" style="float:left">
													<tr>
														<th style="width:90px;height:20px;">异动后年级</th>
														<td style="width:180px"><bean:write name="s" property="ydhnj"/>
														</td>
													</tr>
													<tr>
														<th style="height:20px;">异动后<bean:message key="lable.xb" /></th>
														<td><bean:write name="s" property="ydhxymc"/>
														</td>
													</tr>
													<tr>
														<th style="height:20px;">异动后专业</th>
														<td><bean:write name="s" property="ydhzymc"/>
														</td>
													</tr>
													<tr>
														<th style="height:20px;">异动后班级</th>
														<td><bean:write name="s" property="ydhbjmc"/>
														</td>
													</tr>
												</table>
											</div>
										</td>
									</tr>
								
								</logic:equal>
								
								<logic:equal name="xxdm" value="10511">
								<logic:equal name="s" property="xzsfkq" value="1">					
									<tr>
										<th align="right" width="10%">
											学制
										</th>
										<td align="left" colspan="3">
											<bean:write name="s" property="xz"/>
										</td>
									</tr>
								</logic:equal>
								<logic:equal name="s" property="xxsfkq" value="1">	
									<tr>
										<th align="right">
											来源学校/去向学校
										</th>
										<td align="left" colspan="3">
											<bean:write name="s" property="xxmc"/>
										</td>
									</tr>
								</logic:equal>
									<tr>
										<th align="right" width="10%">
											异动原因
										</th>
										<td align="left" colspan="3">
											<bean:write name="s" property="ydyymc"/>
										</td>
									</tr>
								</logic:equal>
								<tr>
									<th align="right" width="10%">
										附件信息
									</th>
									<td colspan="3">
										<div id="commonfileupload-list-${indexId +1}" style="padding: 5px;"></div>
										<script type="text/javascript">
											//调用附件 
											jQuery(function(){
												var gid = '<bean:write name="s" property="filepath"/>';
												jQuery.MultiUploader_q({
													gid : gid,
													targetEl : 'commonfileupload-list-${indexId +1}'
													});
											});
										</script>
									</td>
								</tr>
								<tr>
									<th align="right" width="10%">
										申请理由&nbsp;					
									</th>
									<td colspan="3">
									<bean:write name="s" property="sqly"/>
									</td>
								</tr>
								<tr>
									<th align="right" width="10%">
										学籍异动文号&nbsp;
									</th>
									<td>
										<span style="color:blue;"><bean:write name="s" property="xjydwh"/></span>
									</td>
									<logic:notEqual name="xxdm" value="10511">
										<th align="right" width="10%">
											学籍异动时间&nbsp;
										</th>
										<td>
											<bean:write name="s" property="xjydsj"/>
										</td>
									</logic:notEqual>
									
									<logic:equal name="xxdm" value="10511">
										<th align="right" width="10%">
											学籍异动审核时间&nbsp;
										</th>
										<td>
											<bean:write name="s" property="xjydsj"/>
										</td>	
									</logic:equal>
									
								</tr>
								<logic:equal name="s" property="lrqzsj" value="1">
									<tr>
										<th>异动起止时间</th>
										<td colspan="3">
											<bean:write name="s" property="sqkssj"/> &nbsp;至&nbsp; <bean:write name="s" property="sqjssj"/>
										</td>
									</tr>
								</logic:equal>
								<tr>
									<th align="right" width="10%">
										备注&nbsp;
									</th>
									<td colspan="3">
										<bean:write name="s" property="xjydbz"/>
									</td>
								</tr>
							</tbody>
						</table>
					  	<br/>
						</logic:iterate>
					</logic:notEmpty>
					<logic:empty name="xsYdList">
						<span style="color:green; font-weight:bold; font-size:12px;padding-left:10px;"> 没有更多学籍异动信息。</span>			
					</logic:empty>
				</div>
			</div>				
		</div>
		<div style="height:35px"></div>	
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button"  onclick="iFClose();" id="buttonClose">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		</html:form>
	</body>
</html>
