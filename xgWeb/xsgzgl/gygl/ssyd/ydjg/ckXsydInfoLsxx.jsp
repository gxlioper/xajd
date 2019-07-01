<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script language="javascript" defer="defer">
		jQuery(function() {
			jQuery("#myTbody").css("display","none");
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
		<div style='tab;width:100%;height:100%;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
				<thead>
					<tr>
						<th colspan="4">
							<span>ѧ��ס����Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="15%">
							������
						</th>
						<td align="left"  colspan="3">
							<span style="color:red;">${xsydInfo.ssydlxmc}</span>
						</td>
					</tr>
					<tr>
						<th align="right">
							ѧ��/ѧ��
						</th>
						<td align="left">
							${xsydInfo.xn} &nbsp; ${xsydInfo.xqmc}
						</td>
						<th align="right">
							��¼ʱ��
						</th>
						<td align="left">
							${xsydInfo.czsj}
						</td>
					</tr>
					<% 
						String ssydlx=((HashMap<String,String>)request.getAttribute("xsydInfo")).get("ssydlx");//�����춯����
					%>
					<tr>
						<th align="right" width="15%" id="yy">
							<% if("00".equals(ssydlx)){ %>����ԭ��<% } %>
							<% if("01".equals(ssydlx)){ %>�������ԭ��<% } %>
							<% if("03".equals(ssydlx)){ %>��סԭ��<% } %>
						</th>
						<td align="left">
							${xsydInfo.tsyymc}
						</td>
						<th align="right" id="sj">
							<% if("00".equals(ssydlx)){ %>����ʱ��<% } %>
							<% if("01".equals(ssydlx)){ %>�������ʱ��<% } %>
							<% if("03".equals(ssydlx)){ %>��סʱ��<% } %>
						</th>
						<td align="left" >
							${xsydInfo.tstzsj}
						</td>
					</tr>
					<tr>
						<th align="right" width="15%">
							<% if("00".equals(ssydlx)){ %>ԭ����<% } %>
							<% if("01".equals(ssydlx)){ %>�������<% } %>
							<% if("03".equals(ssydlx)){ %>������<% } %>
						</th>
						<td colspan="3">
							<% if("00".equals(ssydlx)){ %>
								<span style="color:blue;">${xsydInfo.tsqs}</span>
							<% } %>
							<% if("01".equals(ssydlx)){ %>
								<span style="color:blue;">${xsydInfo.tsqs} </span>
								<img style="vertical-align: text-bottom;" src='images/ssyd/to.gif' ></img>
								<span style="color:blue;">${xsydInfo.rzqs}</span>
							<% } %>
							<% if("03".equals(ssydlx)){ %>
								<span style="color:blue;">${xsydInfo.rzqs}</span>
							<% } %>
						</td>
					</tr>

					<logic:equal value="1" name ="zsfxs">
						<tr>
							<th align="right" style="width: 10%">
								ס�޷�
							</th>
							<td colspan="3">
								<logic:notEmpty name="xsydInfo" property="jflx">
									${xsydInfo.jflx} ${xsydInfo.zsfje} Ԫ
								</logic:notEmpty>
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="11647" name = "xxdm">
						<tr>
							<th align="right" style="width: 10%">
								������Ϣ
							</th>
							<td colspan="3">
									<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
									<script type="text/javascript">
										//���ø��� 
										jQuery(function(){
											var gid = "${xsydInfo.fjxx}";
											jQuery.MultiUploader_q({
												gid : gid
												});
										});
									</script>
								</td>
						</tr>
					</logic:equal>
					<tr>
						<th align="right" width="15%">
							��ע
						</th>
						<td colspan="3">
							${xsydInfo.bz}
						</td>
					</tr>
				</tbody>
			</table>
			<br/>
			<table width="100%"  border="0" class="formlist">
			    <thead>
			      <tr>
			      	<th colspan="2">
			      	  <a href="#" class="down" onclick="showTbody(this,'myTbody');return false">���������춯��Ϣ</a>
			   	    </th>
			      </tr>
			    </thead>
			</table>
			<div class="regform" style="padding-top:0px;">
				<div id="myTbody" style="padding-bottom:10px;">
					<logic:notEmpty name="xsYdList">
					  <logic:iterate id="s" name="xsYdList" indexId="index">
						<table width="100%" border="0" class="formlist">
						  <tbody>
								<tr>
									<th align="right" width="15%">
										������
									</th>
									<td align="left" colspan="3">
										<span style="color:red"><bean:write name="s" property="ssydlxmc"/></span>
									</td>
								</tr>
								<tr>
									<th align="right"  width="15%" >
										ѧ��/ѧ��
									</th>
									<td align="left"  width="35%">
										<bean:write name="s" property="xn"/>/<bean:write name="s" property="xqmc"/>
									</td>
									<th align="right"  width="15%" >
										��¼ʱ��
									</th>
									<td align="left"  width="35%">
										<bean:write name="s" property="czsj"/>
									</td>
								</tr>
								
								<tr>
									<th align="right" width="15%" id="yy">
										<%if(((HashMap<String,String>)s).get("ssydlx").equals("00")){ %>
										����ԭ��
										<%}else if(((HashMap<String,String>)s).get("ssydlx").equals("03")){ %>
										��סԭ��
										<%}else{ %>
										�������ԭ��
										<%} %>
									</th>
									<td align="left">
										<bean:write name="s" property="tsyymc"/>
									</td>
									<th align="right" id="sj">
										<%if(((HashMap<String,String>)s).get("ssydlx").equals("00")){ %>
										����ʱ��
										<%}else if(((HashMap<String,String>)s).get("ssydlx").equals("03")){ %>
										��סʱ��
										<%}else{ %>
										�������ʱ��
										<%} %>
									</th>
									<td align="left" >
										<bean:write name="s" property="tstzsj"/>
									</td>
								</tr>
								<tr>
									<th align="right" width="15%">
										<%if(((HashMap<String,String>)s).get("ssydlx").equals("00")){ %>
										ԭ����
										<%}else if(((HashMap<String,String>)s).get("ssydlx").equals("03")){ %>
										������
										<%}else{ %>
										�������
										<%} %>
									</th>
									<td colspan="3">
										<%if(((HashMap<String,String>)s).get("ssydlx").equals("00")){ %>
											<span style="color:blue;"><bean:write name="s" property="tsqs"/></span>
										<%}else if(((HashMap<String,String>)s).get("ssydlx").equals("03")){ %>
											<span style="color:blue;"><bean:write name="s" property="rzqs"/></span>
										<%}else{ %>
											<span style="color:blue;"><bean:write name="s" property="tsqs"/></span>
											<img style="vertical-align: text-bottom;" src='images/ssyd/to.gif' ></img>
											<span style="color:blue;"><bean:write name="s" property="rzqs"/></span>
										<% } %>
									</td>
								</tr>
								<logic:equal value="1" name ="zsfxs">
									<tr>
										<th align="right" style="width: 10%">
											ס�޷�
										</th>
										<td colspan="3">
											<logic:notEmpty name="s" property="jflx">
												<bean:write name="s" property="jflx"/> <bean:write name="s" property="zsfje"/> Ԫ
											</logic:notEmpty>
										</td>
									</tr>
								</logic:equal>
								<logic:equal value="11647" name = "xxdm">
									<tr>
										<th align="right" style="width: 10%">
											������Ϣ
										</th>
										<td colspan="3">
												<div id="commonfileupload-list-${index}" style="padding: 5px;"></div>
												<script type="text/javascript">
													//���ø��� 
													jQuery(function(){
														var gid = "${s.fjxx}";
														jQuery.MultiUploader_q({
															gid : '${s.fjxx}',
															targetEl : 'commonfileupload-list-${index}'
															});
													});
												</script>
											</td>
									</tr>
								</logic:equal>
								<tr>
									<th align="right" width="15%">
										��ע
									</th>
									<td colspan="3">
										<bean:write name="s" property="bz"/>
									</td>
								</tr>
						  </tbody>
						</table>
					  	<br/>
						</logic:iterate>
					</logic:notEmpty>
					<logic:empty name="xsYdList">
						<span style="color:green; font-weight:bold; font-size:12px;padding-left:10px;"> û�и��������춯��Ϣ��</span>			
					</logic:empty>
				</div>
			</div>				
		</div>
		<div style="height: 15px;"></div>
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button"  onclick="iFClose();" id="buttonClose">
									�� ��
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
