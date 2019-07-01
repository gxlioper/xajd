<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxjbsz/xmsz/xmwh/js/xmwh.js"></script>
		<script type="text/javascript">
		var gridSetting = {
			caption : "��Ŀ�б�",
			pager : "pager",
			url : "xpjpy_xmwh.do?method=getXmwhList&type=query",
			colList : [
			    {label : '��Ŀ����',name : 'xmdm',index : 'xmdm',key : true,hidden : true}, 
			    {label : '���뿪ʼʱ��',name : 'sqkssj',index : 'sqkssj',hidden : true},
			    {label : '�������ʱ��',name : 'sqjssj',index : 'sqjssj',hidden : true},
			    {label : '��˿�ʼʱ��',name : 'shkssj',index : 'shkssj',hidden : true},
			    {label : '��˽���ʱ��',name : 'shjssj',index : 'shjssj',hidden : true},
			    {label : '�������Ʒ�Χ',name : 'rsfpfs',index : 'rsfpfs',hidden : true},
			    {label : '���', name : 'rowindex', index : 'rowindex', width : '2%'},
				{label : '��Ŀ����',name : 'xmmc',index : 'xmmc',width : '12%'},
				{label : '��Ŀ����',name : 'lxmc',index : 'lxmc',width : '8%'},
				{label : '��Ŀ����',name : 'xzmc',index : 'xzmc',width : '8%'},
				{label : '���',name : 'xmje',index : 'xmje',width : '5%'},
				{label : '��������',name : 'rssz',index : 'rssz',width : '8%',formatter:setRssz},
				{label : '��������',name : 'tjsz',index : 'tjsz',width : '12%',formatter:setTjsz},
				{label : '���뿪��',name : 'sqkg',index : 'sqkg',width : '10%',formatter:setSqkg},
				{label : '��˿���',name : 'shkg',index : 'shkg',width : '10%',formatter:setShkg}
			],
			sortname : "xssx",
			sortorder : "asc"
		}
		jQuery(function() {
			jQuery("#dataTable").initGrid(gridSetting);
		});
		</script>
	</head>
	<body>	
		<html:form action="/xpjpy_xmwh">
		<input type="hidden" name="currDate" id="currDate" value="${currDate}"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;"	onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<div  id="div_help" class="prompt" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					Ĭ����ʾ��ǰ�������ڣ�<font color="red">${pjzq}</font>����������Ŀ����</br>
					�������ã��˴����õ�ǰ��Ŀ�Ļ�<font color="red">��������</font>�����ݡ��������Ʒ�Χ��������Ӧ��Χ�ڻ��������ޣ��������Լ��������</br>
					�������ã��˴����õ�ǰ��Ŀ��<font color="red">������</font>�������뽱���ѧ����������Լ��������������ѧ�����ܻ�ý���</br>
					���ɼ�����ã��˴����õ�ǰ��Ŀ��<font color="red">���ɼ����Ŀ</font>,ѡ����Ŀ�뵱ǰ������Ŀ���ɼ��</br>
					����ƣ��ɸ��Ʒǵ�ǰ���ڵ�������Ŀ����ǰ����
				</span>
			</p>
			<a class="close" title="����"
			   onclick="this.parentNode.style.display='none';">
			</a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<div class="toolbox">
			<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
					<div class="buttonbox">
						<ul>
							<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
							<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
							<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>
							<li><a href="javascript:void(0);" onclick="jxfz();" class="btn_sz">�����</a></li>
							<li><a href="javascript:void(0);" onclick="rssz();" class="btn_sz">��������</a></li>
							<li><a href="javascript:void(0);" onclick="tjsz();" class="btn_sz">��������</a></li>
							<li><a href="javascript:void(0);" onclick="jdsz();" class="btn_sz">���ɼ������</a></li>
							<li><a href="javascript:void(0);" onclick="bbsz(1);" class="btn_sz">�ǼǱ�����</a></li>
							<li><a href="javascript:void(0);" onclick="bbsz(2);" class="btn_sz">�ϱ�������</a></li>
						</ul>
					</div>
				</logic:equal>
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="10%">��Ŀ����</th>
						<td>
							<input type="text" id="xmmc" name="xmmc" maxleng="50" style="width: 100px"/>
						</td>
						<th>��Ŀ����</th>
						<td>
							<html:select property="lxdm" styleId="lxdm" style="width:100px">
							<html:option value=""></html:option>
							<html:options collection="xmlxList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
						<th>��Ŀ����</th>
						<td>
							<html:select property="xzdm" styleId="xzdm" style="width:100px">
							<html:option value=""></html:option>
							<html:options collection="xmxzList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
						<th>���뿪��</th>
						<td>
							<html:select property="sqkg" styleId="sqkg" style="width:100px">
							<html:option value=""></html:option>
							<html:option value="0">δ����</html:option>
							<html:option value="1">�ѿ���</html:option>
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
		</div>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>
					<font color="blue">${pjzq}</font>&nbsp;&nbsp;��Ŀ�б�
			    </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>