<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type='text/javascript' src="js/uicomm.js"></script>
	<script type='text/javascript' src="js/String.js"></script>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
	<script type="text/javascript">
	function changeDcqk(){
		var sfcl = jQuery("#sfcl").val();
		if(sfcl=="是"){
			jQuery("#div_dcqk").show();
		}else if(sfcl=="否"){
			jQuery("#div_dcqk").hide();
		}

	}

	function sfshCh(obj){
		if(obj.value=='tg'){
			jQuery("#cljgRs").css("display","");
		}else{
			jQuery("#cljgRs").css("display","none");
		}
	}
	function changeTqs(obj){
		if(obj.checked){
			jQuery('input[type=checkbox][name=xhs]').attr('checked',jQuery(obj).attr('checked'));
			jQuery('input[type=checkbox][name=th]').attr('checked',jQuery(obj).attr('checked'));
			jQuery("#xs_body").css("display","");
		}else{
			jQuery("#xs_body").css("display","none");	
			}
		}

	function saveShzt(){
		var xh=jQuery("#xh").val();
		var wjsj=jQuery("#wjsj").val();
		var shzt=jQuery("#shzt").val();
		var gyjllbdm=jQuery("#gyjllbdm").val();
		jQuery("#gyjl_div_pkValue").val(wjsj+"!!shen!!"+xh+"!!shen!!"+gyjllbdm);
		
		confirmInfo("确定要审核该记录吗?",function(tag){

			if(tag=="ok"){
				allNotEmpThenGo('gyglnew_gyjlgl_gyjlclsh.do?doType=sh&act='+shzt);
			}else {
				return false;
			}
		});
	}
	
	</script>
</head>
<body>
	<html:form action="/gyglnew_gyjlgl" method="post">
		
		<div class="tab"  style="width:100%;overflow-x:hidden;overflow-y:auto;">
		<html:hidden property="div_pkValue" styleId="gyjl_div_pkValue"/>
		<%@ include file="/comm/hiddenValue.jsp"%>			
		<table class="formlist" width="95%">
			<thead>
				<tr>
					<th colspan="4">
						<span>学生信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					学号
				</th>
				<td align="left" width="30%" nowrap="nowrap">
					<html:hidden name="rs" property="xh" styleId="xh"/>
					<html:hidden name="rs" property="wjsj" styleId="wjsj"/>
					<html:hidden name="rs" property="gyjllbdm" styleId="gyjllbdm"/>
					<input type="hidden" name="jlsj" id="jlsj" value="${rs.wjsj }"/>
					${rs.xh }
				</td>
				
				<th  align="right" width="20%">
					姓名			
				</th>
				<td  width="30%">
					${rs.xm }
				</td>
			</tr>
			<tr>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>${rs.xymc }</td>
				<th>专业</th>
				<td>${rs.zymc }</td>
			</tr>
			<tr>
				<th>班级</th>
				<td>${rs.bjmc }</td>
				<th>年级</th>
				<td>${rs.nj }</td>
			</tr>
			<tr>
				<th>住宿寝室</th>
				<td>${rs.zsqs }</td>
				<th>寝室电话</th>
				<td>${rs.qsdh }</td>
			</tr>
			</tbody>
			</table>
			<table width="100%" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>
							<logic:equal value="11799" name="xxdm">
								公寓奖惩信息
							</logic:equal>
							<logic:notEqual value="11799" name="xxdm">						
								公寓纪律信息
							</logic:notEqual>
						</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					违纪学年
				</th>
				<td align="left" width="30%">
					${rs.wjxn}
				</td>
				<th align="right" width="20%">
					违纪学期
				</th>
				<td align="left" width="30%">
					${rs.xqmc}
				</td>
			</tr>
			<tr>
				<th >
					<logic:equal value="11799" name="xxdm">
						奖惩大类
					</logic:equal>
					<logic:notEqual value="11799" name="xxdm">						
						纪律大类
					</logic:notEqual>
									
				</th>
				<td >
					${rs.jldl}
				</td>
				<th >
					<logic:equal value="11799" name="xxdm">
						奖惩类别
					</logic:equal>
					<logic:notEqual value="11799" name="xxdm">						
						纪律类别
					</logic:notEqual>									
				</th>
				<td  >
					${rs.jldb}
				</td>
			</tr>
			<tr>
					<th >
						违纪时间				
					</th>
					<td >
						${rs.wjsj}
					</td>
					<th >
					</th>
					<td >
					</td>
			</tr>
			<logic:equal name="xxdm" value="70002">
				<tr>
					<th align="right" >
								附件信息
						</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = "${rs.fileid}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
										});
									});
								</script>
							</td>
				</tr>
			</logic:equal>
			<tr>	
					<th>
						备注
					</th>
					<td colspan="3" style="word-break:break-all;" width="650px">
						${rs.bz }
					</td>
			</tr>
			<tr>
				<th align="right" width="20%">
					处理结果
				</th>
				<td align="left" width="30%" id="cljg" >
					${rs.cljgmc}
				</td>
				<%--重庆邮电大学个性化 --%>
				<logic:equal value="13627" name="xxdm">
					<th align="right" width="20%">
						处理时间
					</th>
					<td align="left" width="30%" id="cljg" >
						${rs.ylzd3}
					</td>
				</logic:equal>
			</tr>
			<logic:equal value="13033" name="xxdm">
				<tr>
					<th width="20%">
						赔偿金额
					</th>
					<td align="left" width="30%" colspan="3">
						${rs.ylzd1}&nbsp;&nbsp;元&nbsp;&nbsp;
					</td>
				</tr>
			</logic:equal>
			<tr>
					<th>
						调查情况
						<br />
					</th>
					<td colspan="3" style="word-break:break-all;" width="650px">
						${rs.dcqk}
					</td>
			</tr>
			<tr>
				<th width="20%">
					审核状态
				</th>
				<td align="left" width="30%" >
					<html:select name="rs" property="shzt" style="width:150px" styleId="shzt">
<%--						<html:option value="wsh">未审核</html:option>--%>
						<html:option value="tg">通过</html:option>
						<html:option value="btg">不通过</html:option>
						<html:option value="th">退回</html:option>
					</html:select>
				</td>
				<th>审核时间</th>
				<td>${rs.shsj }</td>
			</tr>
			<tr>
				<th width="20%">
					审核意见
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=gyjlxx&id=shyj" />
					<textarea id="shyj" rows="3" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="chLeng(this,500);">${rs.shyj}</textarea>
				</td>
			</tr>
			
			</tbody>
			</table>
			
			<table width="100%" border="0" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>
						<logic:equal value="11799" name="xxdm">
							已获奖惩信息
						</logic:equal>
						<logic:notEqual value="11799" name="xxdm">						
							已获纪律信息
						</logic:notEqual>	
							
						</span>
					</th>
				</tr>
			</thead>
			
				<tr>
				<td colspan="4">
					<div style="height:70px;overflow-x:hidden;overflow-y:auto;">
					<table class="formList" width="100%">
						<thead>
							<tr align="left">
								<td align="center">违纪学年</td>
								<td align="center">违纪学期</td>
								<td align="center">
								<logic:equal value="11799" name="xxdm">
									奖惩类别
								</logic:equal>
								<logic:notEqual value="11799" name="xxdm">						
									纪律类别
								</logic:notEqual>									
								</td>
								<td align="center">违纪时间</td>
								<td align="center">处理结果</td>
							</tr>
						</thead>
			<logic:empty name="rsArrList">
				<tr><td align="left" colspan="5">该学生无历史违纪记录！</td></tr>
			</logic:empty>
			<logic:notEmpty name="rsArrList">
				<logic:iterate name="rsArrList" id="s">
					<tr>
						<!-- 显示信息 -->
						<logic:iterate id="v" name="s" offset="0" length="5">
							<td align="center">
								${v }
							</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
			</logic:notEmpty>
		</table>
		</div>
		</td>
		</tr>
		</table>
		</div>
		<table width="100%" border="0" class="formlist">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="保存" id="buttonClose" onclick="saveShzt();return false;">
									保存
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
