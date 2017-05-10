<%--
  Created by IntelliJ IDEA.
  User: 47
  Date: 2016/6/5
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="Entity.Book" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>书目大厅</title>
    <link href="./dict/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="./dict/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="./dict/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="./dict/jquery.json.min.js"></script>
</head>
<body>

<%@include file="template/UserNavbar.jsp" %>

<script type="text/javascript">
    $(function(){
        function showBook(){
            $.ajax({
                url:"showAllBook",
                success:function(data){
                    $("#content").html(data);
                    $("div#hidden2").hide();
                    $('[data-toggle="popover"]').popover({
                        html: true,
                        title: '书籍详情',
                        trigger: 'hover focus',
                        content: function () {
                            return $("div#hidden2").html();
                        }
                    });

                }
            });
        }
        $("#buyBook").on("click", showBook);
        $("#showCart").on("click", function(){
            $.ajax({
                url:"showCart",
                success:function(data){
                    $("#content").html(data);
                }
            });
        });
        $("#showOrder").on("click",function(){
            $.ajax({
                url:"showOrder",
                success:function(data){
                    $("#content").html(data);
                }
            });
        });
        showBook();
    });
</script>

<style type="text/css">
    .slide-image {
        width: 100%;
    }
</style>
<!-- Page Content -->
<div class="container" style="padding-top:70px;">

    <div class="row">

        <div class="col-md-3">
            <p class="lead">欢迎来到书目大厅</p>
            <div class="list-group">
                <a href="#" class="list-group-item" id="buyBook">我要买书</a>
                <a href="#" class="list-group-item" id="showCart">我的购物车</a>
                <a href="#" class="list-group-item" id="showOrder">我的订单</a>
                <%--<a href="Info.jsp" class="list-group-item">个人信息</a>--%>

            </div>
        </div>

        <div class="col-md-9" id="content">

        </div>

    </div>

</div>
<!-- /.container -->

<div class="container">
    <hr>
    <!-- Footer -->
    <footer>
        <div class="row">
            <div class="col-lg-12">
                <p align="center">Copyright &copy; C.C.Bookstore 2016</p>
            </div>
        </div>
    </footer>

</div>

<script type="text/javascript">
    function showInfo(info){
        $("#modal-content").text(info);
        $("#modal").modal("show");
    }
</script>

<!-- /.container -->
<div id="modal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">信息</h4>
            </div>
            <div class="modal-body">
                <p id="modal-content"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">确认</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>
