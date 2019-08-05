
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
	<script type="text/javascript" src="xsgzgl/xyfd/fdkcwh/fdkcsh/js/fdkcSh.js"></script>
	<script type="text/javascript">
        jQuery(function () {
            var mon = '${model.mon}';
            var tues = '${model.tues}';
            var wed = '${model.wed}';
            var thur = '${model.thur}';
            var fri = '${model.fri}';
            var sat = '${model.sat}';
            var sun = '${model.sun}';
            var mond = mon.split(",");
            var tuesd = tues.split(",");
            var wedd = wed.split(",");
            var thurd = thur.split(",");
            var frid = fri.split(",");
            var satd = sat.split(",");
            var sund = sun.split(",");
            if(mond!=null){
                for(var i=0;i<mond.length;i++){
                    jQuery("input:checkbox[name='mond'][value='"+mond[i]+"']").prop("checked","checked");
                }
            }
            if(tuesd!=null){
                for(var i=0;i<tuesd.length;i++){
                    jQuery("input:checkbox[name='tuesd'][value='"+tuesd[i]+"']").prop("checked","checked");
                }
            }
            if(wedd!=null){
                for(var i=0;i<wedd.length;i++){
                    jQuery("input:checkbox[name='wedd'][value='"+wedd[i]+"']").attr("checked","checked");
                }
            }
            if(thurd!=null){
                for(var i=0;i<thurd.length;i++){
                    jQuery("input:checkbox[name='thurd'][value='"+thurd[i]+"']").prop("checked","checked");
                }
            }
            if(frid!=null){
                for(var i=0;i<frid.length;i++){
                    jQuery("input:checkbox[name='frid'][value='"+frid[i]+"']").prop("checked","checked");
                }
            }
            if(satd!=null){
                for(var i=0;i<satd.length;i++){
                    jQuery("input:checkbox:checkbox[name='satd'][value='"+satd[i]+"']").prop("checked","checked");
                }
            }
            if(sund!=null){
                for(var i=0;i<sund.length;i++){
                    jQuery("input:checkbox[name='sund'][value='"+sund[i]+"']").prop("checked","checked");
                }
            }
            jQuery.each(jQuery("input:checkbox"),function (element,index) {
                jQuery(this).attr("disabled","disabled");
            })
            jQuery("#shlccx").load(
                "comm_spl.do?method=lccx&sqid=${model.sqid}&tt="
                + new Date().getTime());
            jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
        })

	</script>
</head>
<body style="width:100%">
<html:form action="/xyfd_fdkcsh" method="post" styleId="demoForm">
	<html:hidden name="model" property="sqid" styleId="sqid"/>
	<html:hidden name="model" property="splc" styleId="splc"/>
	<html:hidden name="model" property="shid" styleId="shid"/>
	<div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="4">
					<span>辅导课程信息</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th width="20%">课程名称</th>
				<td width="20%">
						${model.kcmc}
				</td>
				<th width="20%">开课单位</th>
				<td width="40%">
						${model.kkdw}
				</td>
			</tr>
			<tr>
				<th>学生/教师</th>
				<td>
						${model.xsjs}
				</td>
				<th>辅导教师</th>
				<td>
					<input type="hidden" name="fdjs" id="fdjs" value="${model.fdjs}" />
						${fdjsxm}
				</td>
			</tr>
			<tr>
				<th>申请人</th>
				<td colspan="3">
						${model.sqr}
				</td>
			</tr>
			<tr>
				<th>申请原因</th>
				<td colspan="3">
						${model.sqyy}
				</td>
			</tr>
			<tr>
				<th rowspan="7">答疑时间</th>
				<th>周一</th>
				<td colspan="2">
					<div align="center">
						<input type="checkbox" name="mond" value="16:30-17:30"/>16:30-17:30&nbsp
						<input type="checkbox" name="mond" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th>周二</th>
				<td colspan="2">
					<div align="center">
						<input type="checkbox" name="tuesd" value="16:30-17:30"/>16:30-17:30&nbsp
						<input type="checkbox" name="tuesd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th>周三</th>
				<td colspan="2">
					<div align="center">
						<input type="checkbox" name="wedd" value="16:30-17:30"/>16:30-17:30&nbsp
						<input type="checkbox" name="wedd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th>周四</th>
				<td colspan="2">
					<div align="center">
						<input type="checkbox" name="thurd" value="16:30-17:30"/>16:30-17:30&nbsp
						<input type="checkbox" name="thurd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th>周五</th>
				<td colspan="2">
					<div align="center">
						<input type="checkbox" name="frid" value="16:30-17:30"/>16:30-17:30&nbsp
						<input type="checkbox" name="frid" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th>周六</th>
				<td colspan="2">
					<div align="center">
						<input type="checkbox" name="satd" value="16:30-17:30"/>16:30-17:30&nbsp
						<input type="checkbox" name="satd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th>周日</th>
				<td colspan="2">
					<div align="center">
						<input type="checkbox" name="sund" value="16:30-17:30"/>16:30-17:30&nbsp
						<input type="checkbox" name="sund" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="7">
					<logic:notEqual value="无需审核" name="shztmc">
						<table width="100%" border="0" class="formlist">
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

						</table>
					</logic:notEqual>
				</td>
			</tr>
			</tbody>
			<thead>
			<tr>
				<th colspan="7">
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
				<td colspan="6">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=dtjs&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
	<div style="width: 100%;position:fixed;bottom:0;">
		<table width="100%" border="0" class="formlist">
			<tfoot>
			<tr>
				<td colspan="4" >
					<div class="btn">
						<button type="button" name="保存"  onclick="saveSh();return false;">
							保 存
						</button>
						<button type="button" name="关 闭" onclick="iFClose();">
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

