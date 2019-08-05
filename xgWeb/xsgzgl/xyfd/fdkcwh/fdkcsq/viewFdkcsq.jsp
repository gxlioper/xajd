
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

        })

	</script>
</head>
<body style="width:100%">
<html:form action="/xyfd_fdkcsq" method="post" styleId="demoForm">
	<html:hidden name="model" property="sqid" styleId="sqid"/>
	<div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="4">
					<span>�����γ���Ϣ</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th width="20%">�γ�����</th>
				<td width="20%">
					${model.kcmc}
				</td>
				<th width="20%">���ε�λ</th>
				<td width="40%">
					${model.kkdw}
				</td>
			</tr>
			<tr>
				<th>ѧ��/��ʦ</th>
				<td>
					${model.xsjs}
				</td>
				<th>������ʦ</th>
				<td>
					<input type="hidden" name="fdjs" id="fdjs" value="${model.fdjs}" />
					${fdjsxm}
				</td>
			</tr>
			<tr>
				<th>������</th>
				<td colspan="3">
					${model.sqr}
				</td>
			</tr>
			<tr>
				<th>����ԭ��</th>
				<td colspan="3">
					${model.sqyy}
				</td>
			</tr>
			<tr>
				<th rowspan="7">����ʱ��</th>
				<th>��һ</th>
				<td colspan="2">
					<div align="center">
						<input type="checkbox" name="mond" value="16:30-17:30"/>16:30-17:30&nbsp
						<input type="checkbox" name="mond" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th>�ܶ�</th>
				<td colspan="2">
					<div align="center">
						<input type="checkbox" name="tuesd" value="16:30-17:30"/>16:30-17:30&nbsp
						<input type="checkbox" name="tuesd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th>����</th>
				<td colspan="2">
					<div align="center">
						<input type="checkbox" name="wedd" value="16:30-17:30"/>16:30-17:30&nbsp
						<input type="checkbox" name="wedd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th>����</th>
				<td colspan="2">
					<div align="center">
						<input type="checkbox" name="thurd" value="16:30-17:30"/>16:30-17:30&nbsp
						<input type="checkbox" name="thurd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th>����</th>
				<td colspan="2">
					<div align="center">
						<input type="checkbox" name="frid" value="16:30-17:30"/>16:30-17:30&nbsp
						<input type="checkbox" name="frid" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th>����</th>
				<td colspan="2">
					<div align="center">
						<input type="checkbox" name="satd" value="16:30-17:30"/>16:30-17:30&nbsp
						<input type="checkbox" name="satd" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<th>����</th>
				<td colspan="2">
					<div align="center">
						<input type="checkbox" name="sund" value="16:30-17:30"/>16:30-17:30&nbsp
						<input type="checkbox" name="sund" value="18:30-19:30"/>18:30-19:30
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="7">
					<logic:notEqual value="�������" name="shztmc">
						<table width="100%" border="0" class="formlist">
							<thead>
							<tr>
								<th colspan="4">
									<span>�����Ϣ</span>
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
	<div style="width: 100%;position:fixed;bottom:0;">
		<table width="100%" border="0" class="formlist">
			<tfoot>
			<tr>
				<td colspan="4" >
					<div class="btn">
						<button type="button" name="�� ��" onclick="iFClose();">
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

     