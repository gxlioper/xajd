<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript">
	jQuery(function(){
		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
		jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
	});
	function saveSh(){
		var shzt = jQuery("#shjg").val();
		if (jQuery("#shzt").val() == "" || jQuery("#shyj").val() == ""){
			showAlert("请将必填项填写完整！");
			return false;
		}
		var url = "gzjlsh.do?method=gzjlDgsh&type=save";
		ajaxSubFormWithFun("GzjlshForm",url,function(data){
			 if(data["message"]=="保存成功！"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
	    	 }else{
	    		 showAlert(data["message"]);
	    		}
			});
	}
	</script>
</head>
<body>
	<html:form action="/gzjlsh" method="post" styleId="GzjlshForm">
		<html:hidden name="model" property="sqid" styleId="sqid"/>
		<html:hidden name="model" property="zgh" styleId="zgh"/>		
		<html:hidden name="model" property="splc" styleId="splc"/>
		<div style="height:510px;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">			
			<thead>
				<tr>
					<th colspan="4">
						<span>基本信息</span>
					</th>
				</tr>
			</thead>
			<%@ include file="/xsgzgl/szdw/gzjl/comm/viewTeacher.jsp" %>
			
			<thead>
				<tr>
					<th colspan="4">
						<span>工作记录</span>
					</th>
				</tr>
		    </thead>
					<tbody>
						<tr>
							<th>工作时间</th>
							<td>
							${rs.gzsj}
							</td>
							<th>记录时间</th>
							<td>
							${rs.jlsj}
							</td>
						</tr>
						<tr>
							<th>工作类别</th>
							<td>
								${rs.gzlbmc}
							</td>
							<th>类别编号</th>
							<td>
							${rs.lbdm}
							</td>
						</tr>
						<logic:equal value="11842" name="xxdm">
							<tr>
								<th>六困生</th>
								<td>
								${rs.lksmc}
								</td>
							</tr>
						</logic:equal>
						<tr>						
							<th>工作摘要</th>
							<td colspan="3">
								${rs.gzzy}
							</td>
						</tr>
							<tr>						
								<th>备注</th>
								<td colspan="3">
									${rs.bz}
								</td>
							</tr>
					</tbody>
							<logic:present name="thdxList">
								<thead>
									<tr>
										<th colspan="4">
											<span>谈话对象</span>
										</th>
									</tr>
						  		</thead>
							</logic:present>
							<logic:equal value="11842" name="xxdm">
			   <logic:present name="thdxList">
			   <tr>
			   	<td colspan="4">
			   		<div style="height:200px;overflow-y:auto;">
					<table width="100%" class="datelist" style="margin:2px auto;border:1px solid  hidden;">					
					<thead>
						<tr>
							<td width='15%'>学号</td>
							<td width='10%'>姓名</td>
							<td width='10%'>性别</td>
							<td width='20%'>学院</td>
							<td width='20%'>专业</td>
							<td width='25%'>班级</td>
						</tr>
					</thead>
					<tbody id="tbody_dhryxx">
						<logic:iterate id="i" name="thdxList" indexId="index01">
							<tr>
								<td name="xhArr">${i.xh}</td>
								<td>${i.xm}</td>
								<td>${i.xb}</td>						
								<td>${i.xymc}</td>
								<td>${i.zymc}</td>
								<td >${i.bjmc}</td>						
							</tr>
						</logic:iterate>
					</tbody>
				</table>
				</div>
			   	</td>
			   </tr> 
							
			</logic:present>
			</logic:equal>
					
			<thead>
					<tr>
						<th colspan="4">
							<span>审批信息</span>
						</th>
					</tr>
			</thead>				
			<tbody>
				<tr>
					<td colspan="4" id="shlccx">
					
					</td>
				</tr>
			
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>审核信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
					<tr>
						<th >
							审核结果
						</th>
						<td id="shjgSpan">
							
						</td>
					</tr>
			</tr>
			<tr>
				<th width="20%">
					<font color="red">*&nbsp;</font> 审核意见
					<br />
					<font color="red">(限200字)</font>
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=gzjlgl&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
				</td>
			</tr>
			</tbody>
			</table>
		</div>
		<div style="height: 20px"></div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="保存"  onclick="saveSh();return false;">
									保 存
								</button>
								<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>
