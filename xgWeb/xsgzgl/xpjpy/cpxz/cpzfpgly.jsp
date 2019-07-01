<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/cpxz/js/cpzfpgly.js"></script>
		<script type="text/javascript">
            var gridSetting;
            var gridSetting2;
            jQuery(function(){
            gridSetting = {
                caption:"",
                pager:"pager",
                url:"xpj_cpxz.do?method=fpgly&type=query&cpzdms="+jQuery("#cpzdms").val(),
                colList:[
                    {label:'ְ����',name:'zgh', index: 'zgh',width:'35%'},
                    {label:'����',name:'xm', index: 'xm',width:'30%'},
                    {label:'�Ա�',name:'xb', index: 'xb',width:'10%'},
                    {label:'��Ժ',name:'symc', index: 'symc',width:'30%'},
                ],
                params:{sffp:"0"},
                sortname: "zgh",
                sortorder: "asc",
                radioselect:false
            }

            gridSetting2 = {
                caption:"",
                pager:"pager",
                url:"xpj_cpxz.do?method=fpgly&type=query&cpzdms="+jQuery("#cpzdms").val(),
                colList:[
                    {label:'ְ����',name:'zgh', index: 'zgh',width:'35%'},
                    {label:'����',name:'xm', index: 'xm',width:'30%'},
                    {label:'�Ա�',name:'xb', index: 'xb',width:'10%'},
                    {label:'��Ժ',name:'symc', index: 'symc',width:'30%'},
                ],
                params:{sffp:"1"},
                sortname: "zgh",
                sortorder: "asc",
                radioselect:false
            }
			var map = getSuperSearch();
			map["sffp"]="0";
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		</script>
	</head>

	<body>
		<div class="prompt" id="div_help">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				ѡ�ж��������ʱ��Ϊ�˱����ͻֻ��δ����ҳǩ��
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<html:form action="/gyglnew_ldgl">
			<input type="hidden" id="sffp" value="0"/>
			<input type="hidden" id="cpzdms" name="cpzdms" value="${cpzdms}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li id="li_bc">
							<a href="javascript:void(0);" onclick="saveFp();return false;" 
							   title=""
							   class="btn_ccg">����</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelFp();return false;" 
							   title=""
							   class="btn_qxsh">ȡ������</a>
						</li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>δ����</span></a></li>
			        <logic:equal value="true" name="isShow">
			        	<li><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>�ѷ���</span></a></li>
			        </logic:equal>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��ѯ���&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
