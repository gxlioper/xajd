<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/sxzzjygl/bjhdgl/bjhdsh/js/bjhdSh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
	<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${sqid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${splc}&shid=${shid}");

		});
		
	</script>
</head>
<body>
	<html:form action="/bjhdgl_bjhdsh" method="post" styleId="BjhdShForm">
		<html:hidden  property="sqid" styleId="sqid"/>
		<html:hidden  property="splc" styleId="splc"/>
		<html:hidden  property="shid" styleId="shid"/>
		<html:hidden  property="gwid" styleId="gwid"/>
		<html:hidden  property="sqr" styleId="sqr"/>
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
			<table width="100%" border="0" class="formlist">
				<thead>
				<tr>
					<th colspan="4">
						<span>�༶�</span>
					</th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<th width="20%">
						�����
					</th>
					<td width="30%">
							${model.hdmc}
					</td>
					<th>�����</th>
					<td>
							${model.hdzt}
					</td>
				</tr>
				<tr>
					<th width="20%">
						�����
					</th>
					<td width="30%">
							${model.hdrq}
					</td>
					<th width="20%">
						�༶����
					</th>
					<td width="30%">
							${model.bjmc}
					</td>
				</tr>

				<tr>
					<th width="20%">
						�������
					</th>
					<td >
							${model.hdfzr}
					</td>
					<th width="20%">
						��������ϵ��ʽ
					</th>
					<td width="30%">
							${model.hdfzrlxdh}
					</td>

				</tr>
				<tr>
					<th width="20%">
						��������ʦ
					</th>
					<td width="30%">
							${model.fzls}
					</td>
					<th width="20%">
						������ʦ��ϵ��ʽ
					</th>
					<td width="30%">
							${model.fzlslxdh}
					</td>
				</tr>
				<tr>
					<th width="20%">
						�Ԥ��
					</th>
					<td width="30%">
							${model.hdys}
					</td>

				</tr>
				<tr><th width="20%">�Ԥ�����ݼ���ϸ</th>
					<td colspan="3">
						<textarea style="width:99%;" readonly="true" rows="4">${model.hdysyjmx}</textarea>
					</td>
				</tr>
				<tr><th width="20%">���Ҫ</th>
					<td colspan="3">
						<textarea style="width:99%;" readonly="true" rows="4">${model.hdgy}</textarea>
					</td>
				</tr>
				<tr><th width="20%">�ʵʩ����</th>
					<td colspan="3">
						<textarea style="width:99%;" readonly="true" rows="4">${model.hdssfa}</textarea>
					</td>
				</tr>
				<tr>
					<th align="right">
						����
					</th>
					<td colspan="3">
						<div id="commonfileupload-list-0" style="padding: 5px;"></div>
						<script type="text/javascript">
                            //���ø���
                            jQuery(function(){
                                var gid = "${fj}";
                                jQuery.MultiUploader_q({
                                    gid : gid,
                                    targetEl : 'commonfileupload-list-0'
                                });
                            });
						</script>
					</td>
				</tr>
			<thead>
					<tr>
						<th colspan="4">
							<span>־Ը����������</span>
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
						<span>�����Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
					<tr>
						<th >
							��˽��
						</th>
						<td id="shjgSpan" colspan="3">
							
						</td>
					</tr>
			</tr>
			<tr>
				<th width="20%">
					<font color="red">*&nbsp;</font> ������
					<br />
					<font color="red">(��200��)</font>
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=zyfwgl&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
				</td>
			</tr>
			</tbody>
			</table>
		</div>
		<div style="height: 30px"></div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="����"  onclick="saveForDgsh();return false;">
									�� ��
								</button>
								<button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		</html:form>
</body>
</html>
