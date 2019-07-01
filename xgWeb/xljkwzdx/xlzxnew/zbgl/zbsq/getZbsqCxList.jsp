<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xljkwzdx/xlzxnew/zbgl/zbsq/js/zbsq.js"></script>
		<script type="text/javascript">
		


	
	jQuery(function() {
		var gridSetting = {
				pager : "pager",
				url : "xlzxnew_zbsb.do?method=searchZbsbCx",
				colList : [
						{ label : 'sbsqid', name : 'sbsqid', index : 'sbsqid',key : true, hidden : true },
						{ label : 'sblx', name : 'sblx', index : 'sblx',hidden : true },
						{ label : 'sblxx', name : 'sblxx', index : 'sblxx',hidden : true },
						{ label : 'ѧ��', name : 'xn', index : 'xn', width : '15%'},
						{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '10%' },
						{ label : '�ܴ�', name : 'zbzc', index : 'zbzc', width : '10%' , formatter : link},
						{ label : '��ʼ����', name : 'zbksrq', index : 'zbksrq', width : '10%' },
						{ label : '��������', name : 'zbjsrq', index : 'zbjsrq', width : '10%' },
						{ label : '�ϱ�ʱ��', name : 'sbsj', index : 'sbsj', width : '10%' },
						{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '8%' },
						{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
						{ label : 'splcid', name : 'splcid', index : 'splcid', hidden : true },
						{ label : 'zbid', name : 'zbid', index : 'zbid', hidden : true }],
				 };
		 var map = {};
		 map["xn"] = jQuery("#xn").val();
		 map["xq"] = jQuery("#xq").val();
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
		if('${cssz.splc}'==null||'${cssz.splc}'==''){
			jQuery("#prompt_isopen").show();
			jQuery("#prompt_null_isopen").show();
			return false;
		}
	});

</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<div class="prompt" id="prompt_isopen" style="display:none;">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p id="prompt_null_isopen" style="display:none;">
				�������δ���û�����ְ�ڼ��ڣ�����ϵ����Ա��
			</p>
			<p id="prompt_false_isopen" style="display:none;">
				<bean:message key="lable.dqwkfsq_prompt" />
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<html:form action="/xlzxnew_zbsb" >
			<input type="hidden" id="query_type" value="0"/>
			<input type="hidden" id="nowtime" value="${nowtime}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<logic:notEmpty name="cssz">
						<logic:equal value="true" name="rzflag">
						<li>
							<a href="javascript:void(0);" class="btn_zj"
								onclick="sb();return false;">�ϱ�</a>
						</li>
						<li>
							<a href="javascript:void(0);"
								onclick="update();return false;" class="btn_xg"
							>�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύ</a>
						</li>
						</logic:equal>
						</logic:notEmpty>
					
						
						<li>
							<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr">����</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="lcinfo();return false;" class="btn_cs">���̸���</a>
						</li>
					</ul>
				</div>
				
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="searchtab">
					<table width="100%" border="0">
						<tr>
							<th width="10%">ѧ��</th>
							<td>
								<html:select property="xn" styleId="xn" style="width:155px">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th width="10%">ѧ��</th>
							<td>
								<html:select property="xq" styleId="xq" style="width:155px">
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
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
		</html:form>

		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��ѯ���&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
