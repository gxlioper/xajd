<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/sxzzjygl/bjhdgl/bjhdsh/js/bjhdSh.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/comm/js/comm.js"></script>
		<script type="text/javascript">
		
			var colList = [
							{ label : 'key', name : 'sqid', index : 'sqid',key : true, hidden : true },
							{ label : 'splc', name : 'splc', index : 'splc',hidden : true },
                			{ label : 'sqr', name : 'sqr', index : 'sqr',hidden : true },
							{ label : '�����', name : 'hdmc', index : 'hdmc', width : '11%',formatter: hdLink},
							{ label : '�༶����', name : 'bjmc', index : 'bjmc', width : '10%' },
							{ label : '�����', name : 'hdzt', index : 'hdzt', width : '11%' },
                			{ label : '�Ԥ��', name : 'hdys', index : 'hdys', width : '6%' },
							{ label : '�����', name : 'hdrq', index : 'hdrq', width : '6%' },
							{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '6%' },
							{ label : '���״̬', name : 'shzt', index : 'shzt', hidden : true},
							{label:'���Id',name:'shid', index: 'shid',hidden:true},
							{label:'��λId',name:'gwid', index: 'gwid',hidden:true}
			              ];
			
			var gridSetting = {
					pager:"pager",
					url:"bjhdgl_bjhdsh.do?method=bjhdSh&type=query",
					colList:colList,
					sortname: "hdrq",
				 	sortorder: "asc",
				 	radioselect:false
			}
			
			jQuery(function(){
				var map = getSuperSearch();
				map["shlx"]="dsh";
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});

            //��鿴
            function hdLink(cellValue, rowObject) {
                return "<a href='javascript:void(0);' class='name' onclick='View(\""
                    + rowObject['sqid'] + "\");'>" + cellValue
                    + "</a>";
            }


            function View(sqid) {
                showDialog("���Ϣ", 900, 450, "bjhdgl_bjhdsq.do?method=getHdInfo&sqid=" + sqid );
            }
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/bjhdgl_bjhdsh">
			<input type="hidden" id="shlx" value="dsh"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="sh();return false;" 
							   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
							   class="btn_sh">���</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelSh();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_qxsh">����</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="lcgz();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>�Ѵ���</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="main_box">
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
