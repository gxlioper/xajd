<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
</head>
<body>
		<div style="overflow-x:hidden;overflow-y:auto;padding-right:18px;">
		<table class="formlist" width="95%">
			<thead>
				<tr>
					<th colspan="4">
						<span>ѧ����Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					ѧ��
				</th>
				<td align="left" width="30%" nowrap="nowrap">
					${rs.xh }
				</td>
				
				<th  align="right" width="20%">
					����			
				</th>
				<td  width="30%">
					${rs.xm }
				</td>
			</tr>
			<tr>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>${rs.xymc }</td>
				<th>רҵ</th>
				<td>${rs.zymc }</td>
			</tr>
			<tr>
				<th>�༶</th>
				<td>${rs.bjmc }</td>
				<th>�꼶</th>
				<td>${rs.nj }</td>
			</tr>
			<tr>
				<th>¥������</th>
				<td>${rs.ldmc }</td>
				<th>���Һ�</th>
				<td>${rs.qsh }</td>
			</tr>
			<tr>
				<th>��λ��</th>
				<td>${rs.cwh }</td>
				<th>���ҵ绰</th>
				<td>${rs.qsdh }</td>
			</tr>
			</tbody>
			</table>
			<table width="95%" border="0" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>��Ԣ������Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					Υ��ѧ��
				</th>
				<td align="left" width="30%">
					${rs.wjxn}
				</td>
					<th align="right" width="20%">
						Υ��ѧ��
					</th>
				<td align="left" width="30%">
					${rs.xqmc}
				</td>
			</tr>
			<tr>
				<th align="right" width="20%">
					���ɴ���				
				</th>
				<td align="left" width="30%">
					${rs.jldl}
				</td>
				<th align="right" width="20%">
					�������				
				</th>
				<td align="left" width="30%" >
					${rs.jldb}
				</td>
			</tr>
			<tr>
					<th width="20%">
						Υ��ʱ��				
					</th>
					<td align="left" width="30%">
						${rs.wjsj}
					</td>
				<th width="20%">
							
					</th>
					<td align="left" width="30%">
						
					</td>
			</tr>
			<tr>
					<th>
						��ע
					</th>
					<td colspan="3" style="word-wrap: break-word!important; word-break: break-all!important" >
						${rs.bz }
					</td>
			</tr>
			<tr>
					<th width="20%">
						������					
					</th>
					<td align="left" width="30%" colspan="3">
						${rs.cljgmc}
					</td>
			</tr>
			<logic:equal name="xxdm" value="70002">
				<tr>
					<th align="right" width="20%">
								������Ϣ
						</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//���ø��� 
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
			<logic:equal value="13033" name="xxdm">
			<tr>
					<th width="20%">
						�⳥���					
					</th>
					<td align="left" width="30%" colspan="3">
						${rs.ylzd1}
					</td>
			</tr>
			</logic:equal>
			<tr>
				<th width="20%">
						�������			
					</th>
					<td align="left"  colspan="3" style="word-wrap: break-word!important; word-break: break-all!important">
						${rs.dcqk}
					</td>
					</tr>
			<tr>
				<th width="20%">
						���״̬			
					</th>
					<td align="left" width="30%">
						${rs.shztmc}
					</td>
					<th width="20%">
						���ʱ��					
					</th>
					<td align="left" width="30%">
						${rs.shsj}
					</td>
			</tr>	
			<tr>
				<th width="20%">
						������			
					</th>
					<td align="left"  colspan="3" style="word-wrap: break-word!important; word-break: break-all!important">
						${rs.shyj}
					</td>
			</tr>		
			</tbody>
			</table>
			
			<table width="80%" border="0" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>�ѻ������Ϣ</span>
					</th>
				</tr>
			</thead>
			
				<tr>
				<td colspan="4">
					<div style="height:80px;overflow-x:hidden;overflow-y:auto;">
					<table class="formList" width="100%">
						<thead>
							<tr align="left">
								<td align="center">Υ��ѧ��</td>
								<td align="center">Υ��ѧ��</td>
								<td align="center">�������</td>
								<td align="center">Υ��ʱ��</td>
								<td align="center">������</td>
							</tr>
						</thead>
			<logic:empty name="rsArrList">
				<tr><td align="left" colspan="5">��ѧ������ʷΥ�ͼ�¼��</td></tr>
			</logic:empty>
			<logic:notEmpty name="rsArrList">
				<logic:iterate name="rsArrList" id="s">
					<tr>
						<!-- ��ʾ��Ϣ -->
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
		<div style="height:50px;">
		
		</div>
		<table width="95%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
</body>
</html>
