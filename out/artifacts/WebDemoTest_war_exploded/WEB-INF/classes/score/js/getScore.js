
var sendResultURL = "http://localhost:8080/WebDemoTest/AnswerServlet";

function getScore() {

    if (config.sendResultURL !== null) {


        // 发送答案数据
        var uid = document.getElementById("q1").value;
        var date = new Date();
        var folder = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();//年月日命名
        if (uid == null || uid == "" || uid == "undefined") { //游客模式
            fpath = "guest/" + new Fingerprint({canvas: true}).get() + "/" + folder;
        } else {
            fpath = uid + "/" + folder;
        }
        txt = date.getHours() + "-" + date.getMinutes() + "-" + date.getSeconds();

        var collate = [];
        for (r = 0; r < userAnswers.length; r++) {
            collate.push('{"questionNumber":"' + parseInt(r + 1, 10) + '", "userAnswer":"' + userAnswers[r] + '"}');
        }
        var answerjson = '{"answers": [' + collate.join(",") + ']}';
        var bType = inferBrowser();
        var data = {"uid": uid, "fpath": fpath, "txt": txt, "answers": answerjson, "bType": bType};
        $.ajax({
            type: 'POST',
            url: config.sendResultsURL,
            data: JSON.stringify(data),
            dataType: JSON,
            //beforeSend: function(request) {
            //    request.setRequestHeader("Content-type","application/json");
            //},
            complete: function () {
                console.log("OH HAI");
            }
        });
    }
}
