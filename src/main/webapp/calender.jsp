<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>추가 서비스 요청</title>
    <link href="https://fonts.googleapis.com/css2?family=Caveat:wght@400..700&family=Gaegu&family=Jua&family=Nanum+Pen+Script&family=Playwrite+AU+SA:wght@100..400&family=Single+Day&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
    <style>
        body {
            position: relative;
            justify-content: center;
            align-items: center;
            height: 800px;
            width: 390px;
            margin: 0 auto;
            background-color: black;
        }

        h1 {
            margin-left: auto;
            margin-right: auto;
            text-align: center;
            color: white;
        }

        /* 달력 */
        .calendarBox {
            color: white;
        }

        #month {
            display: inline-block;
            position: relative;
            float: left;
            margin-left: 10px;
            margin-top: 20px;
            margin-bottom: 20px;
        }

        #month h2 {
            font-size: 20px;
            font-weight: normal;
            margin: 0px;
        }

        .icon1 {
            display: inline-block;
            position: relative;
            float: right;
            margin-right: 10px;
            width: 20px;
            margin-top: 23px;
            margin-bottom: 20px;
        }

        .calendarBox table {
            border-collapse: collapse;
            border: none;
            width: 100%;
        }

        .calendarBox th {
            border: none;
            height: 30px;
            padding: 8px;
            font-weight: normal;
            text-align: center;
        }

        .calendarBox td {
            border: none;
            height: 70px;
            padding: 8px;
            text-align: center;
        }

        /* 오늘 날짜 배경색 지정 */
        .calendarBox #today {
            background-color: rgba(255, 255, 255, 0.5);
            border-radius: 10%;
            color: black;
        }
    </style>
</head>
<body>
<h1 style="padding-top: 20px;">추가 서비스 요청</h1>
<div class="calendarBox">
    <div id="month"></div>
    <div id="calendar"></div>
</div>

<script>
    //이번 달 구하기
    month = document.getElementById("month");

    function printMonth() {

        // 현재 날짜를 나타내는 Date 객체 생성
        var currentDate = new Date();

        // 현재 월을 가져옴
        var currentMonth = currentDate.getMonth();

        //영어로 바꾸기
        var monthNames = [
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        ];

        var currentMonthName = monthNames[currentMonth];

        month.innerHTML = "<h2>" + currentMonthName + "</h2>";
    }


    //이번 달 달력 그리기
    calendar_box = document.getElementById("calendar");

    function printCalendar(y, m) {

        //① 현재 날짜와 현재 달에 1일의 날짜 객체를 생성합니다.
        var date = new Date(); //날짜 객체 생성
        var nowY = date.getFullYear(); //현재 연도
        var nowM = date.getMonth(); //현재 월
        var nowD = date.getDate(); //현재 일

        y = (y != undefined) ? y : nowY;
        m = (m != undefined) ? m-1 : nowM;

        /* 현재 월의 1일에 요일을 구합니다.
         그럼 그달 달력에 첫 번째 줄 빈칸의 개수를 구할 수 있습니다.*/
        var theDate = new Date(y, m, 1);
        var theDay = theDate.getDay();

        //② 현재 월에 마지막 일을 구해야 합니다.

        //1월부터 12월까지 마지막 일을 배열로 저장함.
        var last = [31,28,31,30,31,30,31,31,30,31,30,31];
        /*현재 연도가 윤년(4년 주기이고 100년 주기는 제외합니다.
        또는 400년 주기)일경우 2월에 마지막 날짜는 29가 되어야 합니다.*/
        if (y % 4 == 0 && y % 100 !=0 || y % 400 == 0) lastDate=last[1]=29;

        var lastDate = last[m]; //현재 월에 마지막이 몇일인지 구합니다.

        //③ 현재 월의 달력에 필요한 행의 개수를 구합니다.
        var row = Math.ceil((theDay+lastDate)/7); //필요한 행수

        //④ 달력 년도/월 표기 및  달력 테이블 생성
        calendar_box.innerHTML = "<h2>" + y + "." + (m+1) + "</h2>";

        //문자결합 연산자를 사용해 요일이 나오는 행을 생성
        var calendar = "<table border='1'>";
        calendar += "<tr>";
        calendar += "<th>MON</th>";
        calendar += "<th>TUE</th>";
        calendar += "<th>WED</th>";
        calendar += "<th>THU</th>";
        calendar += "<th>FRI</th>";
        calendar += "<th>SAT</th>";
        calendar += "<th>SUN</th>";
        calendar += "</tr>";

        console.log(nowD);

        var dNum = 1;
        //이중 for문을 이용해 달력 테이블을 생성
        for (var i = 1; i <= row; i++) {//행 생성 (tr 태그 생성)
            calendar += "<tr>";

            for (var k = 1; k <= 7; k++) {//열 생성 (td 태그 생성)
                /*행이 첫 줄이고 현재 월의 1일의 요일 이전은 모두 빈열로
                표기하고 날짜가 마지막 일보다 크면 빈열로 표기됩니다.*/
                if (i == 1 && k < theDay || dNum > lastDate) {
                    calendar += "<td> &nbsp; </td>";
                }
                else {
                    // 오늘 날짜에 대한 스타일 적용
                    if (dNum === nowD) {
                        calendar += "<td id='today'>" + dNum + "</td>";
                    } else {
                        calendar += "<td>" + dNum + "</td>";
                    }
                    dNum++;
                }
            }
            calendar += "<tr>";
        }

        //⑤ 문자로 결합된 달력 테이블을 문서에 출력
        calendar_box.innerHTML = calendar;
    }



    //함수 실행
    printMonth();
    printCalendar();
</script>
</body>
</html>