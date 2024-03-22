<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<style type="text/css">
    /* div인데 클래스가 grid인 것 */
    div.grid {
        display: grid;
        grid-template-columns: 80px 1fr;
        grid-template-rows: 28px 28px 28px 320px 1fr;
        row-gap: 10px;
    }
    
    div.grid > div.btn-group {
        grid-column: 1 / 3;
    }

    div.grid div.rigth-align {
        text-align: right;
    }

    label {
        padding-left: 10px;
    }

    button, input, textarea {
        padding: 10px;
    }
    input[type="file"] {
    	padding: 0px;
    }
</style>
</head>
<body>
    <h1>게시글 작성</h1>							<!-- HTTP GET | POST  (POST -> 4GB가 최대  -->
    <!-- HTTP (TCP/IP) 프로토콜 사용 데이터를 bit 단위로 쪼개서 전송한다. 톰캣이 해줌 서버에서는 
    	어느 정도의 bit 가 쌓이면 조합한다. (천킹) 이것을 멀티파트 라고 한다.
    	그렇기 때문에  multipart 가 있어야 파일을 보낼 수 있다. -->
    <form action="/board/write" method="post" enctype="multipart/form-data">
        <div class="grid">
            <label for="subject">제목</label>
            <input id="subject" type="text" name="subject" />

            <label for="email">이메일</label>
            <input id="email" type="email" name="email" />
            
            <label for="file">첨부파일</label>
            <input id="file" type="file" name="file" />

            <label for="content">내용</label>
            <textarea id="content" name="content" style="height: 300px;"></textarea>
            <div class="btn-group">
                <div class="right-align">
                    <input type="submit" value="저장" />
                </div>
            </div>
        </div>
    </form>
</body>
</html>