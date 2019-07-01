<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxwdpj/xmsq/js/xmsq.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript">
            var gridSetting = {
                caption:"��Ŀ�����б�",
                pager:"pager",
                url:"xpjpy_xmsq.do?method=getXmsqListData",
                colList:[
                    {label:'key',name:'id',index:'id',key:true,hidden:true },
                    {label:'xmdm',name:'xmdm', index: 'xmdm',hidden:true},
                    {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
                    {label:'splc',name:'splc', index: 'splc',hidden:true},
                    {label:'ѧ��',name:'xh', index: 'xh',width:'6%',formatter:xhLink},
                    {label:'����',name:'xm', index: 'xm',width:'8%'},
                    {label:'ѧԺ',name:'xymc', index: 'xydm',width:'11%'},
                    {label:'�༶',name:'bjmc', index: 'bjdm',width:'11%'},
                    {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'9%'},
                    {label:'���뽱��',name:'xmmc', index: 'xmmc',width:'9%'},
                    {label:'���״̬',name:'shztmc', index: 'shzt',width:'4%'}
                ],
                sortname : "crsj",
                sortorder : "desc" }

				jQuery(function(){
					var map = getSuperSearch();
					gridSetting["params"] = map;
					jQuery("#dataTable").initGrid(gridSetting);
				})
		</script>
	</head>
	<body>
		<input type="hidden" name="isopen" id="isopen" value="${cssz.kgzt}"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title}</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<!-- ��ʾ��Ϣ -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					1��������δ�������룬ֻ���ύ�����˻ء��ļ�¼��<br/>
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<html:form action="/xpjpy_xmsq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="xmsqAdd();return false;">��������</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_xg" onclick="xmsqUpdate();return false;">�޸�</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_sc" onclick="xmsqDelete();return false;">ɾ��</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_shuc" onclick="xmsqSubmit();return false;">�ύ</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_xg" onclick="xmsqRevoke();return false;">����</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_cs" onclick="xmsqTrack();return false;">���̸���</a>
							</li>
							<logic:notEqual name="userType" value="stu">
								<li>
									<a href="javascript:void(0);" class="btn_dr" onclick="importPjjg();return false;" >����</a>
								</li>
							</logic:notEqual>
						</logic:equal>
						
						<li>
							<a href="javascript:void(0);" class="btn_dc" onclick="xmsqExport();return false;" >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="xmsqDownload();return false;" class="btn_down">���صǼǱ�</a>
						</li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>���������б�&nbsp;&nbsp;</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>