
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type='text/javascript' src="js/check.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
	<script type="text/javascript">
        jQuery(function(){
            var mon = '${rs.mon}';
            var tues = '${rs.tues}';
            var wed = '${rs.wed}';
            var thur = '${rs.thur}';
            var fri = '${rs.fri}';
            var sat = '${rs.sat}';
            var sun = '${rs.sun}';
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
                "comm_spl.do?method=lccx&sqid=${rs.sqid}&tt="
                + new Date().getTime());
        })
        function selectFds() {
            showDialog("辅导室列表",700,500,"xyfd_fdjswh.do?method=selectFds");
        }

	</script>
</head>
<body style="width:100%">
<html:form action="/xyfd_fdjswh" method="post" styleId="demoForm">
	<html:hidden name="rs" property="sqid" styleId="sqid"/>
	<div style='width:100%;height:500px;overflow-x:hidden;overflow-y:auto;'>
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="7">
					<span>朋辈志愿者信息</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th width="10%">姓名</th>
				<td width="20%">
						${xsxxlist.xm}
				</td>
				<th width="10%">性别</th>
				<td width="20%">
					<logic:equal value="1" name="xsxxlist" property="xb">
						<label>男</label>
					</logic:equal>
					<logic:equal value="2" name="xsxxlist" property="xb">
						<label>女</label>
					</logic:equal>
				</td>
				<th width="12%">学号</th>
				<td width="20%">
						${xsxxlist.xh}
					<input name="xh" id="xh" value="${xsxxlist.xh}" style="display: none"/>
				</td>
				<th rowspan="2">
					<div align="center">
						<img id="zhaopian" src="xsxx_xsgl.do?method=showPhoto&xh=${xsxxlist.xh}" alt="" style="height: 133px;width: 100px;" border="0"/>
					</div>
				</th>
			</tr>
			<tr>
				<th>书院</th>
				<td>
						${xsxxlist.symc}
				</td>
				<th>学院</th>
				<td>
						${xsxxlist.xymc}
				</td>
				<th>班级</th>
				<td>
						${xsxxlist.zybjmc}
				</td>
			</tr>
			<tr>
				<th>专业排名</th>
				<td>
						${zypmlist.pm0}
				</td>
				<th>学生干部任职</th>
				<td>
						${rs.xsgbrz}
				</td>
				<th>奖（助）学金及表彰奖励</th>
				<td colspan="2">
						${jlxx}
				</td>
			</tr>
			<tr>
				<th>联系电话</th>
				<td colspan="3">
						${rs.lxdh}
				</td>
				<th>E-mail</th>
				<td colspan="2">
						${rs.dzyx}
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>辅导科目</th>
				<td colspan="3">
						${rs.fdkm}
				</td>
				<th><span class="red">*</span>辅导室</th>
				<td colspan="2">
					<html:hidden name="rs" property="fds" styleId="fds"/>
						${fdsxx.fdsmc}
				</td>
			</tr>
			<tr>
				<th><span class="red">*</span>附件</th>
				<td colspan="6">
					<input type="hidden" id="fjid" name="fjid" value="${rs.fjid}" />
					<div id="fjidDiv"></div>
					<script type="text/javascript">
                        //调用附件
                        jQuery(function() {
                            jQuery('#fjidDiv').multiUploader_q({
                                gid: jQuery('#fjid').val(),
                                uid: 'fjuid${n}'
                            });
                        });
					</script>
				</td>
			</tr>
			<tr>
				<th rowspan="7">答疑时间</th>
				<th colspan="2">周一</th>
				<td colspan="4">
					<div align="center">
						<input type="checkbox" name="mond" value="16:30-17:30"/>16:30-17:30
						<input type="checkbox" name="mond" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th colspan="2">周二</th>
				<td colspan="4">
					<div align="center">
						<input type="checkbox" name="tuesd" value="16:30-17:30"/>16:30-17:30
						<input type="checkbox" name="tuesd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th colspan="2">周三</th>
				<td colspan="4">
					<div align="center">
						<input type="checkbox" name="wedd" value="16:30-17:30"/>16:30-17:30
						<input type="checkbox" name="wedd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th colspan="2">周四</th>
				<td colspan="4">
					<div align="center">
						<input type="checkbox" name="thurd" value="16:30-17:30"/>16:30-17:30
						<input type="checkbox" name="thurd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th colspan="2">周五</th>
				<td colspan="4">
					<div align="center">
						<input type="checkbox" name="frid" value="16:30-17:30"/>16:30-17:30
						<input type="checkbox" name="frid" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th colspan="2">周六</th>
				<td colspan="4">
					<div align="center">
						<input type="checkbox" name="satd" value="16:30-17:30"/>16:30-17:30
						<input type="checkbox" name="satd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th colspan="2">周日</th>
				<td colspan="4">
					<div align="center">
						<input type="checkbox" name="sund" value="16:30-17:30"/>16:30-17:30
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
		</table>
	</div>
	<div style="position:fixed;bottom:0;width: 100%">
		<table width="100%" border="0" class="formlist">
			<tfoot>
			<tr>
				<td colspan="4" >
					<div class="btn">
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

