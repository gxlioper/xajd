<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript">
function saveForm(){	
	 var checkids ="zzly";
	 if(!checkNotNull(checkids)){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����!");
		return false;
	 }
	var url = "ttgl_stcygl.do?method=stzz&type=save";
	ajaxSubFormWithFun("stcyglForm", url, function(data) {
		if (data["message"] == "����ɹ���") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlert(data["message"]);
		}
	});
}
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/ttgl_stcygl" method="post" styleId="stcyglForm" onsubmit="return false;">
		<input type="hidden" name="jgid" id="jgid" value="${jgid }"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;height: auto" >
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
							<span>ѧ����֯��Ϣ</span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<th width="20%">ѧ����֯ȫ��</th>
						<td width="30%">
								${rs.stqc }
						</td>
						<th width="20%">ѧ����֯���</th>
						<td width="30%">
								${rs.stjc }
						</td>
					</tr>
					<tr>
						<th width="20%">����ʱ��</th>
						<td width="30%">
								${rs.clsj}
						</td>
						<th width="20%">��֯����</th>
						<td width="30%">
								${rs.strs}
						</td>
					</tr>
					<tr>
						<th>ѧ����֯����</th>
						<td>
								${rs.styx}
						</td>
						<th>ѧ����֯���ں�</th>
						<td>
								${rs.gzh}
						</td>
					</tr>
					<tr>
						<th>ָ����ʦ</th>
						<td>
								${rs.zdlsxm }
						</td>
						<th>ָ����λ</th>
						<td>
								${rs.bmmc }
						</td>
					</tr>
					<tr>
						<th>�칫�ҵ�ַ</th>
						<td colspan="3">
								${rs.bgsdz}
						</td>
					</tr>
					<tr>
						<th>ѧ����֯������Դ</th>
						<td colspan="3">
							<logic:iterate id="item" collection="${xszzjflyList}">
								<input type="checkbox" name="jflyArray" value="${item.dm}" disabled>${item.mc}
							</logic:iterate>
						</td>
						<script type="text/javascript">
                            jQuery(function(){
                                var r = '${rs.jfly}';
                                var result = r.split(",");
                                for(var i=0;i<result.length;i++){
                                    jQuery("input[value='"+result[i]+"'").attr("checked","checked");
                                }
                            })
						</script>
					</tr>
					<tr>
						<th>��֯����</th>
						<td colspan="3" id="stlx">
								${rs.stlx }
						</td>
					</tr>
					<tr id="ndzzzt" style="display:none">
						<th>�����֯״̬</th>
						<td colspan="3">
								${rs.xn}
								${rs.ndzzztmc}
						</td>
					</tr>
					<tr>
						<th>��֯���</th>
						<td colspan="3">
								${rs.zzlbmc}
						</td>
					</tr>
					<tr>
						<th>ѧ����֯��ּ</th>
						<td colspan="3">
								${rs.stjs }
						</td>
					</tr>
					</tbody>
					<thead>
					<tr class="h">
						<th colspan="4">
							<span>ѧ����֯������</span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr class="h">
						<th colspan="7">
							<table width="100%" >
								<thead>
								<tr>
									<th width='10%' style="text-align:center">ѧ��</th>
									<th width='10%' style="text-align:center">����</th>
									<th width='10%' style="text-align:center">��Ժ</th>
									<th width='10%' style="text-align:center">ѧԺ</th>
									<th width='10%' style="text-align:center">רҵ</th>
									<th width='10%' style="text-align:center">�༶</th>
									<th width='10%' style="text-align:center">����</th>
									<th width='10%' style="text-align:center">�绰</th>
								</tr>
								</thead>
								<tbody id="tablebody">
								<logic:iterate id="i" name="fzrxxInfo" indexId="index">
									<tr name='deltr'>
										<td style='text-align:center'><input name='xh' type='hidden'  value='${i.xh}'/><label name = 'xhname'>${i.xh}</label></td>
										<td style='text-align:center'><label name = 'xm'>${i.xm}</label></td>
										<td style='text-align:center'><label name = 'symc'>${i.symc}</label></td>
										<td style='text-align:center'><label name = 'xymc'>${i.xymc}</label></td>
										<td style='text-align:center'><label name = 'zymc'>${i.zymc}</label></td>
										<td style='text-align:center'><label name = 'bjmc'>${i.bjmc}</label></td>
										<td style='text-align:center'><label name = 'fz'>������</label></td>
										<td style='text-align:center'><label name = 'sjhm'>${i.sjhm}</label></td>
									</tr>
								</logic:iterate>
								</tbody>
							</table>
						</th>
					</tr>
					</tbody>
					<thead>
					<tr class="h">
						<th colspan="4">
							<span>��֧��</span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr class="h">
						<th colspan="7">
							<table width="100%" >
								<thead>
								<tr>
									<th width='10%' style="text-align:center">ѧ��</th>
									<th width='10%' style="text-align:center">����</th>
									<th width='10%' style="text-align:center">��Ժ</th>
									<th width='10%' style="text-align:center">ѧԺ</th>
									<th width='10%' style="text-align:center">רҵ</th>
									<th width='10%' style="text-align:center">�༶</th>
									<th width='10%' style="text-align:center">����</th>
									<th width='10%' style="text-align:center">�绰</th>
								</tr>
								</thead>
								<tbody id="tablebody2">
								<logic:iterate id="i" name="tzsxxInfo" indexId="index">
									<tr name='deltr'>
										<td style='text-align:center'><input name='tzsxh' type='hidden'  value='${i.xh}'/><label name = 'xhname'>${i.xh}</label></td>
										<td style='text-align:center'><label name = 'xm'>${i.xm}</label></td>
										<td style='text-align:center'><label name = 'symc'>${i.symc}</label></td>
										<td style='text-align:center'><label name = 'xymc'>${i.xymc}</label></td>
										<td style='text-align:center'><label name = 'zymc'>${i.zymc}</label></td>
										<td style='text-align:center'><label name = 'bjmc'>${i.bjmc}</label></td>
										<td style='text-align:center'><label name = 'fz'>��֧��</label></td>
										<td style='text-align:center'><label name = 'sjhm'>${i.sjhm}</label></td>
									</tr>
								</logic:iterate>
								</tbody>
							</table>
						</th>
					</tr>
					</tbody>

				</table>
				</div>	
				<div style="height:30px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										�ύ���
									</button>
									<button type="button" onclick="iFClose();">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
    <script type="text/javascript">
        jQuery(function(){
            var ndzzzt = jQuery("#ndzzzt");
            if(jQuery("#stlx").html().trim() == "ѧ������"){
                ndzzzt.removeAttr('style')
            } else {
                ndzzzt.attr('style','display:none');
            }
        })
    </script>
</html>