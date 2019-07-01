<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/xmsq/js/xmsq.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#dataTable").initGrid(gridSetting1);
			auotSetCanCheck
		});
		</script>
	</head>

	<body>
		<input type="hidden" name="curXn" id="curXn" value="${curXn}"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_txhd_xs_xmsq" method="post" styleId="form">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
					<div class="buttonbox">
						<ul>
							<logic:equal name="writeAble" value="yes">	
							<li id="li_cx" style="display:none;">
								<a href="javascript:void(0);" onclick="cxSqb();return false;" class="btn_xg"
						   			title="ֻ��ȡ��δ��˻��˻صļ�¼���ѱ�����˵Ĳ���ȡ����"
									>����</a>
							</li>
							</logic:equal>
							<li id="li_lc" style="display: none;">
									<a href="javascript:void(0);" onclick="viewLcgz();return false;" 
							   			title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   			class="btn_cs">���̸���</a>
							</li>
							<logic:equal name="writeAble" value="yes">	
							<li id="li_sq">
								<a href="javascript:void(0);" class="btn_zj"
								   onclick="xmsq();return false;" 
								   title="����ð�ť�����������дҳ�档">����</a>
							</li>
							
							<li id="li_xg" >
								<a href="javascript:void(0);" onclick="xgSqb();return false;" class="btn_xg"
						   			title="ֻ���޸�δ�ύ���˻صļ�¼���ѱ�����˵Ĳ����޸ġ�"
									>�޸�</a>
							</li>
							
							<li id="li_sc" >
								<a href="javascript:void(0);" onclick="delSqb();return false;" class="btn_sc"
						   			title="ֻ��ɾ��δ�ύ���˻صļ�¼���ѱ�����˵Ĳ�ɾ����"
									>ɾ��</a>
							</li>
							
							<li id="li_tj" >
								<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc"
						   			title="ֻ���ύδ�ύ���˻صļ�¼���ѱ�����˵Ĳ����ύ��"
									>�ύ</a>
							</li>
							</logic:equal>
						</ul>
					</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'wsq');"><span>δ������Ŀ</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysq');"><span>��������Ŀ</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>
					<li id="li_ts" ><font color="blue">${curXn}</font></li>&nbsp;��Ŀ�б�
				 </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>

		</div>
	</body>
</html>
