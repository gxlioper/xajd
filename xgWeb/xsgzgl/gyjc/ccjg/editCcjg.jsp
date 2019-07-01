<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/ccjg/js/ccjg.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/comm/js/comm.js"></script>
		<script type='text/javascript'>
		jQuery(function(){
			jQuery(".jclxSel").each(function(){
			var jclxid=jQuery(this).val();
			var pfid=jQuery(this).next().val();
			jQuery.ajaxSetup({async:false});
			var pfhtml =getPfbz(jclxid);
			jQuery(this).parent().next().html(pfhtml);
			jQuery(this).parent().next().find("select").val(pfid);
			jQuery.ajaxSetup({async:true});
				
			});
		});
		</script>		
	</head>
	<body>
		<html:form action="/gyjc_ccjgcx" method="post" styleId="ccjgForm" onsubmit="return false;">
		<html:hidden property="guid" styleId="guid" value="${model.guid}"/>
		<html:hidden property="lddm" styleId="lddm" value="${model.lddm}"/>
		<html:hidden property="xydm" styleId="xydm" value="${model.xydm}"/>
		<html:hidden property="qsh" styleId="qsh" value="${model.qsh}"/>
		<html:hidden property="js" styleId="js" value="xx"/>
		<input type="hidden" value="1,2,3" name="jjlx"/>
		<input type="hidden"  name="pfid" id="pfid"/>
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="4">
				<span>������Ϣ</span>
				</th>
			</tr>
			</thead>
			<tbody>
			    <tr>
					<th>¥��</th>
					<td id="ldmc" width="30%">${model.ldmc}</td>
					<th width="20%">���</th>
					<td id="ch">${model.ch}</td>
				</tr>
				<tr>
					<th width="20%">����</th>
					<td id="qshTd" width="30%">${model.qsh}</td>
					<th width="20%">ѧԺ</th>
					<td id="xymc">${model.xymc}</td>
				</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
					<span>�������&nbsp;&nbsp;
					<a class="name" href="javascript:;" onclick="addConMoreUpdateTr('1','wsjc');">���Ӽ����</a>
					</span>
					</th>
				</tr>
			</thead>
			  <tbody id="wsjcTbody">
			  <tr>
			  <th><div align="center">��������</div></th>
			  <th colspan="2"><div align="center">Ҫ��</div></th>
			  <th ><div align="center">����</div></th>
			  </tr>
			  <logic:iterate id="i" name="ccjgList" indexId="index01">
					<logic:equal name="i" property="jjlx" value="1">
					<tr>
						<td>
						<html:select style="width:80%" styleClass='jclxSel' property="wsjcPFid"  value="${i.dm}" onchange="changeJclx(this,'wsjc')">
								<html:options collection="wsjcList" property="dm" labelProperty="mc"/>
						</html:select>
						<input type='hidden' value="${i.pfid}"/>
						</td>
						<td class="pfTd" colspan='2'>
						</td>
						<td align='center'><a class='name' href='javascript:;' onclick='deleteTr(this)'>ɾ��</a></td>
					</tr>
					</logic:equal>
				</logic:iterate>
			  </tbody>
			  <thead>
				<tr>
					<th colspan="4">
					<span>��ȫ���&nbsp;&nbsp;
					<a class="name" href="javascript:;" onclick="addConMoreUpdateTr('2','aqjc');">���Ӽ����</a>
					</span>
					</th>
				</tr>
			</thead>
			  <tbody id="aqjcTbody">
			  <tr>
			  <th><div align="center">��Ŀ</div></th>
			  <th colspan="2"><div align="center">Ҫ��</div></th>
			  <th ><div align="center">����</div></th>
			  </tr>
			  <logic:iterate id="i" name="ccjgList" indexId="index01">
					<logic:equal name="i" property="jjlx" value="2">
					<tr>
						<td>
						<html:select style="width:80%" styleClass='jclxSel' property="aqjcPFid"  value="${i.dm}" onchange="changeJclx(this,'aqjc')">
								<html:options collection="aqjcList" property="dm" labelProperty="mc"/>
						</html:select>
						<input type='hidden' value="${i.pfid}"/>
						</td>
						<td class="pfTd" colspan='2'>
						</td>
						<td align='center'><a class='name' href='javascript:;' onclick='deleteTr(this)'>ɾ��</a></td>
					</tr>
					</logic:equal>
				</logic:iterate>
			  </tbody>
			 <thead>
				<tr>
					<th colspan="4">
					<span>���ɼ��&nbsp;&nbsp;
					<a class="name" href="javascript:;" onclick="addConMoreUpdateTr('3','jljc');">���Ӽ����</a>
					</span>
					</th>
				</tr>
			</thead>
			  <tbody id="jljcTbody">
			  <tr>
			  <th><div align="center">��Ŀ</div></th>
			  <th colspan="2"><div align="center">Ҫ��</div></th>
			  <th ><div align="center">����</div></th>
			  </tr>
			  <logic:iterate id="i" name="ccjgList" indexId="index01">
					<logic:equal name="i" property="jjlx" value="3">
					<tr>
						<td>
						<html:select style="width:80%" styleClass='jclxSel' property="jljcPFid"  value="${i.dm}" onchange="changeJclx(this,'jljc')">
								<html:options collection="jljcList" property="dm" labelProperty="mc"/>
						</html:select>
						<input type='hidden' value="${i.pfid}"/>
						</td>
						<td class="pfTd" colspan='2'>
						</td>
						<td align='center'><a class='name' href='javascript:;' onclick='deleteTr(this)'>ɾ��</a></td>
					</tr>
					</logic:equal>
				</logic:iterate>
			  </tbody>
			  <thead>
				<tr>
					<th colspan="4">
					<span>������Ϣ
					</span>
					</th>
				</tr>
			</thead>
			  <tbody>
			  <tr><th><span class="red">*</span>�������
			  </th>
			  <td colspan="3">
				<html:text property="jcrq" styleId="jcrq" value="${model.jcrq}"
					onfocus="showCalendar('jcrq','y-mm-dd',true);" />
			</td>
			  </tr>
			  </tbody>
			   <thead>
				<tr>
					<th colspan="4">
					<span>������Ϣ<html:hidden property="fjid" styleId="fjid" />
					</span>
							<button type="button" onclick="addFj();return false;" class="btn_01">����</button>
					</th>
				</tr>
			</thead>
			  <tbody>
			  <tr>
				<td colspan="4">
					<table width="100%">
						<thead>
							<tr>
								<th width="50%" colspan="2">
									�ļ�
								</th>
								<th width="50%"  colspan="2">
									����
								</th>
							</tr>
						</thead>
						<tbody id="tbody_fj">
							<logic:iterate id="i" name="yscwjList">
								<tr>
									<td colspan="2">${i.originalname}</td>
									<td colspan="2"><a  href="javascript:void(0)" onclick="downloadFile(this)">����</a>
										<a hhref="javascript:void(0)" onclick="delFile(this)">ɾ��</a>
										<input type="hidden" value="${i.fid}"  name="fid"/>
									</td>
								</tr>
							</logic:iterate>
							
						</tbody>
					</table>
				</td>			  
			  </tr>
			  </tbody>
			  </table>
				<div style="height:35px"></div>
			    <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button" onclick="saveCcjg('save')">
									�� ��
								</button>
								<button type="button" onclick="Close();return false;">
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