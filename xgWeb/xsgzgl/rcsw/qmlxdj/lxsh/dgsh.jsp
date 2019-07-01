<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/qmlxdj/lxsh/js/lxsh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/provicecitylocal.js"></script>
		<link rel="stylesheet" href="js/provicecitylocal.css" type="text/css"/>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#shlccx").load(
					"comm_spl.do?method=lccx&sqid=${rs.sqid}&tt="
							+ new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${rs.splcid}&shid=${shid}");
				proviceCiyyLocalMain({type:"view",id:"mddssx",flag:"wxxdz"});
			});
			function saveSh(){
		      var shzt = jQuery("#shjg").val();
		      if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
			    showAlert("请将必填项填写完整！");
			    return false;
		      }
		      var url = "qmlxsh.do?method=Dgsh&type=save";
		      ajaxSubFormWithFun("LxdjForm",url,function(data){
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
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/qmlxdj" method="post" styleId="LxdjForm">
		<html:hidden name="rs" property="sqid" styleId="sqid"/>
		<html:hidden name="rs" property="xh" styleId="xh"/>		
		<html:hidden name="rs" property="splcid" styleId="splcid"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:520px;margin-bottom:0px;" >
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
								<span>离校趋向信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>学年</th>
							<td>
								${rs.xn}
							</td>
							<th>学期</th>
							<td>
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th>是否离校</th>
							<td>
									${rs.sflxdm}
							</td>
							<th>离校类型</th>
							<td>
									${rs.lxlxmc}
							</td>
						</tr>
						<tr>
							<th>监护人姓名</th>
							<td>
								${rs.jhrxm}
							</td>
							<th>监护人联系方式</th>
							<td>
								${rs.jhrlxfs}
							</td>
						</tr>
						<tr>
							<th>是否与监护人联系</th>
							<td>
								${rs.sflx}
							</td>
							<th>离校时间</th>
							<td>
								${rs.lxsj}
							</td>
						</tr>
						<tr>
							<th>离校交通工具</th>
							<td>
								${rs.lxgj}
							</td>
							<th>离校车次/航班</th>
							<td>
								${rs.lxcchb}
							</td>
						</tr>
						<tr>
							<th>目的地</th>
							<td colspan="3">
								<html:hidden name="rs"  property="mddssx" styleId="mddssx"/>
							</td>
						</tr>
						<tr>
							<th>返校时间</th>
							<td>
								${rs.fxsj}
							</td>
							<th>返校交通工具</th>
							<td>
								${rs.fxgj}
							</td>
						</tr>
						<tr>
							<th>返校车次/航班</th>
							<td>
								${rs.fxcchb}
							</td>
						</tr>
						<tr>
							<th>备注</th>
							<td colspan="3">
								${rs.bz}
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
						<td id="shjgSpan" colspan="3">
							
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
						<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=rcswlxdj&id=shyj" />
						<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
					</td>
				</tr>
				</table>
				
				</div>	
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
			<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
	</body>
	
</html>