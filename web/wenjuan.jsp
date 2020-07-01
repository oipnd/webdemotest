<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>问卷答题</title>

    <link rel="stylesheet" type="text/css" href="style.css" />

    <style type="text/css">
        .demo{width:760px; margin:60px auto 10px auto}
    </style>

    <script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
    <script type="text/javascript" src="fingerprint.js"></script>
    <script type="text/javascript" src="jquery.min.js"></script>
    <script type="text/javascript" src="quiz.js"></script>
    <script type="text/javascript" src="collect_no_ajax.js"></script>
    <script type="text/javascript">
        var questions={
            'questions': [
                { 'question': '我认为我自己爱说话', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'ext' },
                { 'question': '我认为我自己喜欢挑剔别人的毛病', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'agr' },
                { 'question': '我认为我自己工作很周密', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'con' },
                { 'question': '我认为我自己压抑而忧郁', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'neu' },
                { 'question': '我认为我自己具有独创性，会产生新点子', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'opn' },
                { 'question': '我认为我自己含蓄的', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'ext' },
                { 'question': '天空中有几个太阳', 'answers': ['三个', '五个', '十个', '九个', '一个'], 'correctAnswer': 1, 'key': 'ext12' },
                { 'question': '我认为我自己乐于助人，无私', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'agr' },
                { 'question': '我认为我自己可能有些粗心', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'con' },
                { 'question': '我认为我自己放松的，可以很好应对压力', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'neu' },
                { 'question': '我认为我自己对许多不同的事情感到好奇', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'opn' },
                { 'question': '我认为我自己精力充沛', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'ext' },
                // { 'question': '我认为我自己经常与他人发生争吵', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'agr' },
                // { 'question': '我认为我自己是个可信赖的人', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'con' },
                // { 'question': '我认为我自己可能会紧张', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'neu' },
                // { 'question': '我认为我自己有独创性，思想深刻', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'opn' },
                // { 'question': '我认为我自己具有很大的热情', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'ext' },
                // { 'question': '我认为我自己天性宽以待人', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'agr' },
                // { 'question': '我认为我自己倾向于缺乏条理', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'con' },
                // { 'question': '我认为我自己有很多忧虑', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'neu' },
                // { 'question': '我认为我自己想象力活跃', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'opn' },
                // { 'question': '我认为我自己比较安静', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'ext' },
                // { 'question': '我认为我自己大体上信任他人', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'agr' },
               //{ 'question': '我认为我自己比较懒惰', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'con' },
               //{ 'question': '我认为我自己情绪稳定，不容易急躁', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'neu' },
               //{ 'question': '我认为我自己善于创造', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'opn' },
               //{ 'question': '我认为我自己性格决断', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'ext' },
               //{ 'question': '我认为我自己可能会冷淡孤僻', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'agr' },
               //{ 'question': '我认为我自己坚持到任务完成', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'con' },
               //{ 'question': '我认为我自己可能会喜怒无常', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'neu' },
               //{ 'question': '我认为我自己重视艺术、美学的经历', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'opn' },
               //{ 'question': '我认为我自己有时羞怯、拘谨', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'ext' },
               //{ 'question': '我认为我自己几乎对每个人都很友善及体谅', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'agr' },
               //{ 'question': '我认为我自己做事有效率', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'con' },
               //{ 'question': '我认为我自己在紧张情境中仍保持冷静', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'neu' },
               //{ 'question': '我认为我自己喜欢从事常规性的工作，不喜欢不确定性', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'opn' },
               //{ 'question': '我认为我自己外向，好交际', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'ext' },
               //{ 'question': '我认为我自己有时对他人粗鲁', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'agr' },
               //{ 'question': '我认为我自己制定计划并加以贯彻', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'con' },
               //{ 'question': '我认为我自己容易紧张', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'neu' },
               //{ 'question': '我认为我自己喜欢反省、思考各种想法', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'opn' },
               //{ 'question': '我认为我自己没有多少艺术兴趣', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'ext' },
               //{ 'question': '我认为我自己喜欢与他人合作，而不是竞争', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'agr' },
               //{ 'question': '我认为我自己容易分心', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'con' },
               //{ 'question': '我认为我自己精通美术、音乐和文学', 'answers': ['非常不同意', '有点不同意', '无所谓', '有点同意', '非常同意'], 'correctAnswer': 1, 'key': 'neu' }

            ]
        };
        $(function(){
            $('#quiz-container').jquizzy({
                questions: questions.questions
            });
        });
    </script>
    <script type="text/javascript">
        function hidetext()
        {
            var mychar = document.getElementById("con").style.display ="none";
            var mychar = document.getElementById("tijiao").style.display ="none";

        }
        function showtext()
        {
            var mychar = document.getElementById("con").style.display ="block";
        }
    </script>

</head>

<body  >
<form id="con" style="text-align:center">
    <h1>请在完成输入信息后点击“Start”开始答题：</h1>
    <br>
    学号(必填):<br>
    <input onkeyup="this.value=this.value.replace(/\D/g,'')"
           onafterpaste="this.value=this.value.replace(/\D/g,'')"  maxlength="11"  type="text" name="sno" id="UID" placeholder="请输入学号：">
    <br>
    手机号码(选填):<br>
    <input onkeyup="this.value=this.value.replace(/\D/g,'')"
           onafterpaste="this.value=this.value.replace(/\D/g,'')"  maxlength="11" type="text" name="phoneno" id="phone"placeholder="请输入手机号：">
</form>

<br>
<br>
<form style="text-align:center">
    <input id="tijiao" type="button" onclick="init(),hidetext()" value="输入完成" />
</form>

<div class="demo">
    <div id='quiz-container'></div>
</div>

</body>
</html>

