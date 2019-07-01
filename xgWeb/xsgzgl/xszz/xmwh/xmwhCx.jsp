<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/xmwh/js/xmwhCx.js"></script>
		<script type="text/javascript">
			function bbsz(type){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer("��ѡ����Ҫ���ñ����Ŀ��");
				} else {
					document.location.href="xszz_bbwh.do?method=bgylList&bblx="+type+"&xmdm="+rows[0]["xmdm"];
				}
			}
			
			function jfsz(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer("��ѡ��һ����Ҫ���õ���Ŀ��");
					return false;
				}
				
				var url = 'xszz_xmwh.do?method=xmwhJfsz&xmdm=' + rows[0]["xmdm"];
				showDialog("��������", 680, 500, url);
			}
			
		</script>
	</head>

	<body>	
		<html:form action="xszz_xmwh.do">
		<input type="hidden" name="currDate" id="currDate" value="${currDate}"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<div  id="div_help" class="prompt" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					Ĭ����ʾ��ǰ�������ڣ�<font color="red">${currXnXqmc}</font>����������Ŀ����</br>
					�������ã��˴����õ�ǰ��Ŀ�Ļ�<font color="red">��������</font>�����ݡ��������Ʒ�Χ��������Ӧ��Χ�ڻ��������ޣ��������Լ��������</br>
					�������ã��˴����õ�ǰ��Ŀ��<font color="red">������</font>����������Ŀ��ѧ����������Լ��������������ѧ�����ܻ����Ŀ</br>
					���ɼ�����ã��˴����õ�ǰ��Ŀ��<font color="red">���ɼ����Ŀ</font>,ѡ����Ŀ�뵱ǰ������Ŀ���ɼ��</br>
					��Ŀ�������ã��˴����õ�ǰ��Ŀ��<font color="red">�ɵ�����Ŀ</font>����ʦ����˽׶οɽ�ѧ������Ŀ����Ϊѡ����Ŀ</br>
					��Ŀ���ƣ��ɸ��Ʒǵ�ǰ���ڵ�������Ŀ����ǰ����
				</span>
			</p>
			<a class="close" title="����"
			   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<logic:equal name="writeAble" value="yes">	
			<div class="toolbox">
				<!-- ��ť -->
					<div class="buttonbox">
						<ul>
							<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
							<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
							<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>		
							<li><a href="javascript:void(0);" onclick="bbsz(1);" class="btn_sz">�ǼǱ�����</a></li>
							<li><a href="javascript:void(0);" onclick="bbsz(2);" class="btn_sz">�ϱ�������</a></li>						
							<li><a href="javascript:void(0);" onclick="jfsz();" class="btn_sz">��������</a></li>						
						</ul>
					</div>
					<div class="buttonbox">
						<ul>
							<li><a href="javascript:void(0);" onclick="jbsz();" class="btn_sz">��������</a></li>						
							<li><a href="javascript:void(0);" onclick="tjsz();" class="btn_sz">��������</a></li>						
							<li><a href="javascript:void(0);" onclick="rssz();" class="btn_sz">��������</a></li>						
							<li><a href="javascript:void(0);" onclick="jdsz();" class="btn_sz">���ɼ������</a></li>
							<li><a href="javascript:void(0);" onclick="shsz();" class="btn_sz">��˵�����Ŀ����</a></li>	
							<li><a href="javascript:void(0);" onclick="jxfz();" class="btn_sz">��Ŀ����</a></li>	
						</ul>
					</div>
			</div>
		<div class="searchtab" >
		</logic:equal>
		<logic:notEqual name="writeAble" value="yes">
			<div class="searchtab" >
		</logic:notEqual>
		<!-- �������� -->
			<table width="100%" border="0">
				<tr>
					<th width="10%">��������</th>
					<td width="10%">
						<html:select property="sqzqdm" styleId="sqzqdm">
							<html:options collection="XmzqList" property="zqdm" labelProperty="zqmc"/>
						</html:select>
					</td>
					<th width="10%">��Ŀ����</th>
					<td>
						<input type="text" id="xmmc" name="xmmc" maxleng="50"/>
					</td>
					<th width="10%">��Ŀ���</th>
					<td>
						<html:select property="lbdm" styleId="lbdm" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="xmlbList" property="dm"
							labelProperty="mc" />
						</html:select>
					</td>
					<td>
						<div class="btn">
							<button type="button" class="btn_cx" id="search_go" onclick="query()">
								�� ѯ
							</button>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ��Ŀ�б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
		</html:form>
	</body>
</html>
