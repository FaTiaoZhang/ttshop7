<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<div id="itemListToolbar">
    <div style="padding: 5px; background-color: #fff;">
        <label>商品标题：</label>
        <input class="easyui-textbox" type="text" id="title">
        <label>商品状态：</label>
        <select id="status" class="easyui-combobox">
            <option value="0">全部</option>
            <option value="1">正常</option>
            <option value="2">下架</option>
        </select>
        <!--http://www.cnblogs.com/wisdomoon/p/3330856.html-->
        <!--注意：要加上type="button",默认行为是submit-->
        <!--注意：不管<a>还是<button>只要添加class="easyui-linkbutton"，那么他们的外观样式是一样的-->
        <button onclick="searchForm()" type="button" class="easyui-linkbutton">搜索</button>
    </div>
    <div>
        <button onclick="add()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</button>
        <button onclick="edit()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</button>
        <button onclick="remove()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</button>
        <button onclick="down()" class="easyui-linkbutton" data-options="iconCls:'icon-down',plain:true">下架</button>
        <button onclick="up()" class="easyui-linkbutton" data-options="iconCls:'icon-up',plain:true">上架</button>
    </div>
</div>

<%--容器放好--%>
<table id="dgItems"></table>

<%--通过js代码来渲染容器--%>
<script>
    //点击搜索按钮动作
    function searchForm() {
        $('#dgItems').datagrid('load',{
            title:$('#title').val(),
            status:$('#status').combobox('getValue')
        });
    }
    //各个按钮的JS动作
    function add() {
        ttshop.addTabs('新增商品', 'item-add');
    }

    function edit() {
        console.log('edit');
    }

    function down() {
        console.log('down');
    }

    function up() {
        console.log('up');
    }

    function remove() {
        //debugger; //尤其可以使用这种嵌套的页面
        //取到客户选中的记录集合
        var rows = $('#dgItems').datagrid('getSelections');
//            console.log(rows);
        if (rows.length == 0) {
            $.messager.alert('警告', '请选中至少一条记录！', 'warning');
            return;
        }
        $.messager.confirm('确认', '您确定要删除记录吗？', function (r) {
            if (r) {
                //客户已经点击“确定”按钮
                //定义一个空的数组，用来存放ID的集合
                var ids = [];
                //遍历的是客户选中的记录集合
                for (var i = 0; i < rows.length; i++) {
                    ids.push(rows[i].id);
                }
                //发出ajax请求
                //$.ajax() $.post() $.get()
                $.post(
                    //url，提交给后台谁去处理
                    'items/batch',
                    //data，提交什么到后台，ids
                    {'ids[]': ids},
                    //callback,相当于$.ajax中success
                    function (data) {
                        if (data > 0) {
                            $('#dgItems').datagrid('reload');
                        }
                    }
                );
            }

        });
    }


    //初始化数据表格代码
    $('#dgItems').datagrid({
        //数据表格的标题
        title: '查询商品列表',
        //显示行号
        rownumbers: true,
        //允许多列排序
        multiSort: true,
        //添加工具栏
        toolbar: '#itemListToolbar',
        //初始化页面数据条数
        pageSize: 20,
        //在设置分页属性的时候 初始化页面大小选择列表
        pageList: [20, 50, 100],
        //请求服务器端数据
        url: 'items',
        //请求方式，默认是POST
        method: 'get',
        //是否显示分页工具栏
        pagination: true,
        //自适应父容器
        fit: true,
        //列属性
        columns: [[
            {field: 'ck', checkbox: true},
            {field: 'id', title: '编号', sortable: true},
            {field: 'title', title: '标题', sortable: true},
            {field: 'sellPoint', title: '卖点'},
            {field: 'catName', title: '商品类别名称', width: 100},
            {
                field: 'status', title: '商品状态', width: 100, formatter: function (value, row, index) {
//                console.group("商品状态");
//                console.log(value);
//                console.log(row);
//                console.log(index);
//                console.groupEnd();
                switch (value) {
                    case 1:
                        return '正常';
                        break;
                    case 2:
                        return '下架';
                        break;
                    case 3:
                        return '删除';
                        break;
                    default:
                        return '未知';
                        break;
                }
            }
            },
            {
                field: 'created', title: '创建时间', formatter: function (value, rows, index) {
                return moment(value).format('L');
            }
            },
            {
                field: 'updated', title: '更新时间', formatter: function (value, rows, index) {
                return value;
            }
            },
            {
                field: 'price', title: '商品价格', formatter: function (value) {
                return value / 100;
            }
            }
        ]]
    });
</script>
