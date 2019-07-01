<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxzhcp/xyrssz/xyrssz.js"></script>
		<script type="text/javascript">
		var gridSetting = {
			caption : "��Ŀ�б�",
			pager : "pager",
			url : "xpjpy_xyrssz.do?method=getXyrsszList&type=query",
			colList : [
					    {label : '��Ŀ����',name : 'xmdm',index : 'xmdm',key : true,hidden : true}, 
					    {label : '���뿪ʼʱ��',name : 'sqkssj',index : 'sqkssj',hidden : true},
					    {label : '�������ʱ��',name : 'sqjssj',index : 'sqjssj',hidden : true},
					    {label : '��˿�ʼʱ��',name : 'shkssj',index : 'shkssj',hidden : true},
					    {label : '��˽���ʱ��',name : 'shjssj',index : 'shjssj',hidden : true},
					    {label : '�������Ʒ�Χ',name : 'rsfpfs',index : 'rsfpfs',hidden : true},
						{label : '��Ŀ����',name : 'xmmc',index : 'xmmc',width : '20%'}, 
						{label : '��Ŀ����',name : 'lxmc',index : 'lxmc',width : '12%'},
						{label : '��Ŀ����',name : 'xzmc',index : 'xzmc',width : '12%'},
						{label : '���',name : 'xmje',index : 'to_number(xmje)',width : '8%'},
						{label : '��������',name : 'rssz',index : 'rssz',width : '8%',formatter:setRsszXy}
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
		<html:form action="/xpjpy_xyrssz">
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
				</span>
			</p>
			<a class="close" title="����"
			   onclick="this.parentNode.style.display='none';">
			</a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<div class="toolbox">
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
					<font color="blue">${pjzq}&nbsp;&nbsp;</font>��ѧ���������
					��ѧԺ��ѧ���ܶ�<font color="red">${jxjjeMap.jxjze}</font>Ԫ�� 
					�ѵ������<font color="red">${jxjjeMap.jxsjze}</font> Ԫ��
			 	</span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>