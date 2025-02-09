<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>추가 서비스 요청</title>
    <link href="https://fonts.googleapis.com/css2?family=Caveat:wght@400..700&family=Gaegu&family=Jua&family=Nanum+Pen+Script&family=Playwrite+AU+SA:wght@100..400&family=Single+Day&display=swap"
          rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
</head>
<style>
    div {
        padding: 20px;
    }

    div.bbq {
        position: absolute;
        left: 5%;
        text-align: center;
    }

    div.service {
        height: 500px;
        width: 450px;
    }

    div.cover {
        position: absolute;
        right: 5%;
        text-align: center;
    }

    tbody td:hover {
        cursor: pointer;
    }

    div.admin {
        position: absolute;
        right: 5%;
        top: 5%;
    }

    div.admin:hover {
        cursor: pointer;
    }

    div.selectRoom {
    }

    div.room {
        display: inline-block;
        width: 120px;
        height: 50px;
        margin: 10px;
        padding: 10px;
        text-align: center;
        border: 1px solid black;
        border-radius: 10px;
    }
    div.room:hover {
        cursor: pointer;
        box-shadow: 1px 1px gray;
    }
    div.checked {
        background-color: greenyellow;
    }
</style>
<%
    Date today = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String todayStr = sdf.format(today);
%>
<script type="text/javascript">
    $(function (){
        listRooms();
    });
    function listRooms(){
        $.ajax({
            type: "get",
            dataType: "json",
            url: "./crud/listRoom.jsp",
            success: function(res){
                let s="";
                $.each(res, function(id, ele){
                    s+="<div link='"+ ele.link +"' roomId='" + ele.id + "' class='room'>"+ele.roomName+"</div>";
                });
                $(".rooms").html(s);
            }
        });/*
        $(".submit").click(function (){

        });*/
        $(document).on("click",".room",function(){
            $(".room.checked").removeClass("checked"); // 기존 checked 클래스 제거
            $(this).addClass("checked"); // 현재 클릭된 요소에 checked 추가
            $(".show").attr("href",$(this).attr("link")); // 상세보기의 link 변환
            $.ajax({ // 방의 종류에 따라 기준인원, 최대인원, 추가 가능한 이불의 수가 변경
                type: "get",
                dataType: "json",
                data: {"id":$(this).attr("roomId")},
                url: "./crud/findRoom.jsp",
                success: function(res){
                    let stdNum = res.stdPeople;
                    let maxNum = res.maxPeople;
                    $(".std_num").html(stdNum);
                    $(".max_num").html(maxNum);
                    let s="";
                    for(let i=1; i<=maxNum-stdNum; i++){
                        s+="<option value='"+ i +"'>"+ i + "명</option>";
                    }
                    $("#cover").html(s)
                }
            });
            $.ajax({ //
                type: "get",
                dataType: "json",
                data: {"id":$(this).attr("roomId"), "date":<%=todayStr%>},
                success: function (res){
                    if(!res.message==""){
                        alert(res.message);
                    }else{

                    }
                }
            })
        });
    }
</script>
<body>
<div class="admin alert alert-warning"><b>관리자 페이지</b></div>
<h1 style="padding: 20px;">추가 서비스 요청</h1>
<div class="selectRoom">
    <span>&nbsp;예약한 방을 선택해주세요.</span><br>
    <div class="rooms">
        <div class="room">111</div>
        <div class="room">222</div>
    </div>
    <button style="margin-bottom: 10px;" href="" type="button" class="btn btn-sm btn-success show" onclick="window.open($(this).attr('href'))">상세보기</button>
</div>
<div class="bbq service" style="border-right: 2px solid black; border-left: 2px solid black">
    <h4><b>숯불 신청하기</b></h4>
    <br>
    <table class="table table-striped table-bordered">
        <thead>
        <tr class="table-light">
            <th colspan="6" style="background-color: skyblue">
                <%
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.HOUR_OF_DAY, 15); // 오후 3시 설정
                    cal.set(Calendar.MINUTE, 0);
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH) + 1;
                    int date = cal.get(Calendar.DATE);
                %>
                <%= year %>/<%= month %>/<%= date %>
            </th>
        </tr>
        </thead>
        <tbody>
        <%
            for (int i = 1; i <= 30; i++) {
                if (i % 6 == 1) {
                    out.println("<tr>");
                }
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                out.println("<td class='time'>" + hour + "시 " + (minute < 10 ? "0" + minute : minute) + "분</td>");
                if (i % 6 == 0) {
                    out.println("</tr>");
                }
                cal.add(Calendar.MINUTE, 10);
            }
        %>
        </tbody>
    </table>
</div>
<div class="cover service" style="border-left: 2px solid black;border-right: 2px solid black;">
    <h4><b>추가 이불 신청</b></h4>
    <br>
    기본 인원 : <span class="std_num">0</span><br>
    최대 인원 : <span class="max_num">0</span>
    <form>
        <select id="cover" style="width: 100px">
            <option value="1" selected>0개</option>
        </select>
        <br>
        <button type="button" class="btn btn-success submit" style="margin-top: 5px;">신청하기</button>
    </form>
</div>
</body>
</html>