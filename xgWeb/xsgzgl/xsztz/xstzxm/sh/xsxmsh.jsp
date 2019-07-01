<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/tzxm/sq/js/xsxmsqsearch.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${form.sqid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${form.splc}&shid=${form.shid}");
		});
		function saveSh(){
			var shzt = jQuery("#shjg").val();
			if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
				showAlert("请将必填项填写完整！");
				return false;
			}
			var url = "xmsqgl_xmsh.do?method=XsXmDgsh&type=save";
			ajaxSubFormWithFun("XsXmShForm",url,function(data){
				// if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	// }else{
		    		// showAlert(data["message"]);
		    		//}
				});
		   }
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/xmsqgl_xmsh" method="post" styleId="XsXmShForm">
				<html:hidden  name ="form" property="sqid" styleId="sqid"/>
				<html:hidden  name ="form" property="xh" styleId="xh"/>
				<html:hidden  name ="form" property="xq" styleId="xq"/>		
				<html:hidden  name ="form" property="xn" styleId="xn"/>		
				<html:hidden  name ="form" property="xmdm" styleId="xmdm"/>		
				<html:hidden  name ="form" property="splc" styleId="splc"/>
				<html:hidden  name ="form" property="sqsj" styleId="sqsj"/>
				<html:hidden  name ="form" property="shid" styleId="shid"/>
				<html:hidden  name ="form" property="shzt" styleId="shzt"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>项目名称</th>
							<td>
								${hdmap.xmmc}
							</td>
							<th>项目级别</th>
							<td id="xmjbmc" >
								${hdmap.xmjbmc}
                             </td>
						</tr>
						<tr>
							<th>学年</th>
							<td id="xn" >
								${hdmap.xn}
							</td>
							<th>学期</th>
							<td id="xq" >
								${hdmap.xqmc}
							</td>
						</tr>
						<tr>
							<th>申报部门</th>
							<td id="sbbmmc" >
								${hdmap.sbbmmc}
							</td>
							<th>联系方式</th>
							<td id="lxdh" name="lxdh">
								${hdmap.lxdh}
							</td>
						</tr>
						<tr>
							<th>所属科目</th>
							<td id="sskmmc" name="sskmmc">
								${hdmap.sskmmc}
							</td>
							<th>基础学分</th>
							<td id="jcxf" name="jcxf">
								${hdmap.jcxf}
							</td>
						</tr>
						<tr>
							<th>可参与人数</th>
							<td id="kcyrs" name="kcyrs">
								${hdmap.kcyrs}
							</td>
							<th>已申请人数</th>
							<td id="sqrs" name="sqrs">
								${hdmap.sqrs}
							</td>
						</tr>
						<tr>
							<th>已通过人数</th>
							<td id="tgrs" name="tgrs">
								${hdmap.tgrs}
							</td>
							<th>项目开始时间</th>
							<td id="xmkssj" name="xmkssj">
								${hdmap.xmkssj}
							</td>
						</tr>
						<tr>
							<th></font>申请理由</th>
							<td colspan="3">
								${form.sqly}
							</td>
						</tr>
					</tbody>
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
					<th >
						审核结果
					</th>
					<td id="shjgSpan">
						
					</td>
				</tr>
				
				<tr>
					<th width="20%">
						<font color="red">*&nbsp;</font> 审核意见
						<br />
						<font color="red">(限200字)</font>
					</th>
					<td colspan="3">
						<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=sztzxsxmsh&id=shyj" />
						<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
					</td>
				</tr>
			</tbody>	
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="保存"  onclick="saveSh();return false;">
									         保 存
								    </button>
									<button type="button" onclick="iFClose();">
										关闭
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