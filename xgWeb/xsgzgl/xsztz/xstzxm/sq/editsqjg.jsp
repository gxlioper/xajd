<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xstzxm/sq/js/xsxmsqsearch.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
	
			jQuery(function(){
				jQuery("#selhd").unbind('click').bind('click', function(){
			            var url = "xmsqgl_xmsq.do?method=getXmSelectList&xh="+jQuery("tbody").eq(0).find("tr:eq(0) td:eq(0) a").text()+"&flag=sq";;
			            showDialog('请选择项目',600,400,url);
			   });
			});
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/xmsqgl_xmsq" method="post" styleId="XsXmSqForm">
			<input type="hidden" id="xmdm" name="xmdm" value="${hdmap.xmdm}"/>
			<input type="hidden" name="splc" id="splc" value="${hdmap.splc}"/>
			<input type="hidden" name="sqid" id="sqid" value="${hdmap.sqid}"/>
			<html:hidden   property="xh" />
			<html:hidden   property="xq" />		
			<html:hidden   property="xn" />		
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
							<th><font color="red">*</font>项目名称</th>
							<td>
								<input name="xmmc" id="xmmc" readonly="readonly" value="${hdmap.xmmc}"/>
								<button class="btn_01" id="selhd" type="button" >选择</button>
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
							<th><font color="red">*</font>申请理由</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" 
								   onkeyup="checkzs();return false;" 
								   style="width:99%;" rows="3"></html:textarea>
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
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" id="bc" onclick="saveSqjgUpdate('update');return false;">
										保存草稿
									</button>
									<button type="button" onclick="saveSqjgUpdate('updatesubmit');return false;">
										提交申请
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