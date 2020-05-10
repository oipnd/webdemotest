(function ($) {
    //making jquizzy as jquery function
    $.fn.jquizzy = function (settings) {
        var defaults = {
            questions: null,
            startImg: 'images/start.gif',
            endText: '已结束!',//end
            shortURL: null,
            sendResultsURL: 'http://localhost:8080/WebDemoTest/AnswerServlet',
            resultComments: {
                perfect: '恭喜您，您已完成全部问题！',
                excellent: '非常优秀!',
                good: '很好，发挥不错!',
                average: '一般般了。',
                bad: '太可怜了！',
                poor: '好可怕啊！',
                worst: '悲痛欲绝！'
            }
        };

        //merge the question array into defaults array
        var config = $.extend(defaults, settings);
        if (config.questions === null) {
            $(this).html('<div class="intro-container slide-container"><h2 class="qTitle">Failed to parse questions.</h2></div>');
            return;
        }
        var superContainer = $(this),
            answers = [],
            //loginFob='<div class="login"><h1>Login</h1><form method="post"><input type="text" name="u" placeholder="用户名" required="required"><input type="password" name="p" placeholder="密码" required="required"><button type="submit" class="btn btn-primary btn-block btn-large">登录</button></form></div>',
            introFob = '	<div class="intro-container slide-container"><a class="nav-start" href="#">说明：请选中最适合你的选项，表明你在多大程度上同意或不同意该描述。<br/><br/><span><img src="' + config.startImg + '"></span></a></div>	',
            exitFob = '<div class="results-container slide-container"><div class="question-number">' + config.endText + '</div><div class="result-keeper"></div><div class="personality" ></div></div><div class="notice">请选择一个选项！</div><div class="progress-keeper" ><div class="progress"></div></div>',
            contentFob = '',
            questionsIteratorIndex,
            answersIteratorIndex;
        superContainer.addClass('main-quiz-holder');
        // Displaying the question
        for (questionsIteratorIndex = 0; questionsIteratorIndex < config.questions.length; questionsIteratorIndex++) {
            contentFob += '<div class="slide-container"><div class="question-number">' + (questionsIteratorIndex + 1) + '/' + config.questions.length + '</div><div id="question-' + (questionsIteratorIndex + 1) + '" class="question">' + config.questions[questionsIteratorIndex].question + '</div><ul class="answers">';
            for (answersIteratorIndex = 0; answersIteratorIndex < config.questions[questionsIteratorIndex].answers.length; answersIteratorIndex++) {
                contentFob += '<li id=' + (questionsIteratorIndex + 1) + answersIteratorIndex + '>' + config.questions[questionsIteratorIndex].answers[answersIteratorIndex] + '</li>';
            }
            contentFob += '</ul><div class="nav-container">';
            //Display the Previous and Next Button
            if (questionsIteratorIndex !== 0) {
                contentFob += '<div class="prev"><a class="nav-previous" href="#">&lt; 上一题</a></div>';
            }
            if (questionsIteratorIndex < config.questions.length - 1) {
                contentFob += '<div class="next"><a class="nav-next" href="#">下一题 &gt;</a></div>';
            } else {
                contentFob += '<div class="next final"><a class="nav-show-result" href="#">完成</a></div>';
            }
            contentFob += '</div></div>';
            answers.push(config.questions[questionsIteratorIndex].correctAnswer);
        }
        superContainer.html(introFob + contentFob + exitFob);
        var progress = superContainer.find('.progress'),
            progressKeeper = superContainer.find('.progress-keeper'),
            notice = superContainer.find('.notice'),
            progressWidth = progressKeeper.width(),
            userAnswers = [],
            questionLength = config.questions.length,
            slidesList = superContainer.find('.slide-container');

        // Check user answer either it is correct or not
        function checkAnswers() {
            var resultArr = [],
                flag = false;
            for (i = 0; i < answers.length; i++) {
                //if (answers[i] == userAnswers[i]) {
                if (true) {
                    flag = true;
                } else {
                    flag = false;
                }
                resultArr.push(flag);
            }
            return resultArr;
        }

        //Round Function
        function roundReloaded(num, dec) {
            var result = Math.round(num * Math.pow(10, dec)) / Math.pow(10, dec);
            return result;
        }

        function flag() {
            console.log("hi");

        }
        //User Score
        function judgeSkills(score) {
            var returnString;
            if (score === 100) return config.resultComments.perfect;
            else if (score > 90) return config.resultComments.excellent;
            else if (score > 70) return config.resultComments.good;
            else if (score > 50) return config.resultComments.average;
            else if (score > 35) return config.resultComments.bad;
            else if (score > 20) return config.resultComments.poor;
            else return config.resultComments.worst;
        }

        var userAnswersArray = [];
        var questionumber = -1;
        //finding the personaltiy
        var extroversionSum = 0, extroversionCount = 0, extroversionMean = 0;

        var pleasantSum = 0, pleasantCount = 0, pleasantMean = 0;

        var responsibilitySum = 0, responsibilityCount = 0, responsibilityMean = 0;

        var neuroticismSum = 0, neuroticismCount = 0, neuroticismMean = 0;

        var opennessSum = 0, opennessCount = 0, opennessMean = 0;

        progressKeeper.hide();
        notice.hide();
        slidesList.hide().first().fadeIn(500);
        superContainer.find('li').click(function () {

            var thisLi = $(this);
            if (thisLi.hasClass('selected')) {
                thisLi.removeClass('selected');
            } else {
                thisLi.parents('.answers').children('li').removeClass('selected');
                thisLi.addClass('selected');
            }


            //number of options
            for (var itrator = 0; itrator < config.questions[questionumber].answers.length; itrator++) {
                if ($('li[id=' + (questionumber + 1) + itrator + ']').hasClass('selected')) {
                    userAnswersArray[questionumber] = itrator + 1;

                    var key = config.questions[questionumber].key
                    switch (key) {
                        case "ext":
                            extroversionSum = extroversionSum + (questionumber + 1); // calculating the sum of extroversion
                            extroversionCount = extroversionCount + 1; // total number of extroversion question
                            //console.log("ext");
                            break;

                        case "agr":
                            pleasantSum = pleasantSum + (questionumber + 1); // calculating the sum of pleasant
                            pleasantCount = pleasantCount + 1;// total number of pleasant question
                            //console.log("agr");
                            break;

                        case "con":
                            responsibilitySum = responsibilitySum + (questionumber + 1);//  calculating the sum of responsibility
                            responsibilityCount = responsibilityCount + 1;// total number of responsibility question
                            console.log("con");
                            break;

                        case "neu":
                            neuroticismSum = neuroticismSum + (questionumber + 1);//  calculating the sum of neuroticism
                            neuroticismCount = neuroticismCount + 1;// total number of neuroticism question
                            //console.log("neu");
                            break;

                        case "opn":
                            opennessSum = opennessSum + (questionumber + 1);//  calculating the sum of openness
                            opennessCount = opennessCount + 1;// total number of openness question
                            //console.log("opn");
                            break;

                        default:
                            break;
                    }
                }
            }
            //console.log(userAnswers);
        });


        superContainer.find('.nav-start').click(function () {

            $(this).parents('.slide-container').fadeOut(500,
                function () {
                    $(this).next().fadeIn(500);
                    progressKeeper.fadeIn(500);
                    questionumber = 0;
                });
            return false;
        });
        superContainer.find('.next').click(function () {
            if ($(this).parents('.slide-container').find('li.selected').length === 0) {
                notice.fadeIn(300);
                return false;
            }
            notice.hide();
            $(this).parents('.slide-container').fadeOut(500,
                function () {
                    $(this).next().fadeIn(500);
                    questionumber = questionumber + 1;
                });
            progress.animate({
                    width: progress.width() + Math.round(progressWidth / questionLength)
                },
                500);
            return false;
        });
        superContainer.find('.prev').click(function () {
            notice.hide();
            $(this).parents('.slide-container').fadeOut(500,
                function () {
                    $(this).prev().fadeIn(500);
                    questionumber = questionumber - 1;
                });
            progress.animate({
                    width: progress.width() - Math.round(progressWidth / questionLength)
                },
                500);
            return false;
        });
        superContainer.find('.final').click(function () {
            if ($(this).parents('.slide-container').find('li.selected').length === 0) {
                notice.fadeIn(300);
                for (var quest = 0; quest < questionumber.length; quest++) {
                    console.log(userAnswersArray[quest]);

                }
                return false;
            }

            superContainer.find('li.selected').each(function (index) {
                userAnswers.push($(this).parents('.answers').children('li').index($(this).parents('.answers').find('li.selected')) + 1);
            });


            if (config.sendResultsURL !== null) {


                // 发送答案数据
                var uid = document.getElementById("UID").value;
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
            progressKeeper.hide();
            var results = checkAnswers(),
                resultSet = '',
                trueCount = 0,
                shareButton = '',
                score,
                url;
            if (config.shortURL === null) {
                config.shortURL = window.location
            }
            ;
            for (var i = 0, toLoopTill = results.length; i < toLoopTill; i++) {
                if (results[i] === true) {
                    trueCount++;
                    isCorrect = true;
                }
                resultSet += '<div class="result-row">' + (results[i] === true ? "<div class='correct'>#" + (i + 1) + "<span></span></div>" : "<div class='wrong'>#" + (i + 1) + "<span></span></div>");
                resultSet += '<div class="resultsview-qhover">' + config.questions[i].question;
                resultSet += "<ul>";
                for (answersIteratorIndex = 0; answersIteratorIndex < config.questions[i].answers.length; answersIteratorIndex++) {
                    var classestoAdd = '';
                    if (config.questions[i].correctAnswer == answersIteratorIndex + 1) {
                        classestoAdd += 'right';
                    }
                    if (userAnswers[i] == answersIteratorIndex + 1) {
                        classestoAdd += ' selected';
                    }
                    resultSet += '<li class="' + classestoAdd + '">' + config.questions[i].answers[answersIteratorIndex] + '</li>';
                }
                resultSet += '</ul></div></div>';
            }
            //calculating the Mean value
            extroversionMean = (extroversionSum / extroversionCount).toFixed(0);
            pleasantMean = (pleasantSum / pleasantCount).toFixed(0);
            responsibilityMean = (responsibilitySum / responsibilityCount).toFixed(0);
            neuroticismMean = (neuroticismSum / neuroticismCount).toFixed(0);
            opennessMean = (opennessSum / opennessCount).toFixed(0);
            // var personalityResult = '<div class="personality-keeper"> 外向性: ' + extroversionMean + '</div>' + '<div> <br> </div>' + '<div class="personality-keeper"> 宜人性:  ' + pleasantMean + '</div>' + '<div> <br> </div>' + '<div class="personality-keeper"> 责任心:  ' + responsibilityMean + '</div>' + '<div> <br> </div>' +
            //     '<div class="personality-keeper">神经质:  ' + neuroticismMean + '</div>' + '<div> <br> </div>' + '<div class="personality-keeper"> 开放性:  ' + opennessMean + '</div>';
            //
            // score = roundReloaded(trueCount / questionLength * 100, 2);

            // resultSet = '<h2 class="qTitle">' + judgeSkills(score) + '<br/> 问卷完成百分比： ' + score + '</h2>' + shareButton + '<div class="jquizzy-clear"></div>' + resultSet + '<div class="jquizzy-clear"></div>';
            // superContainer.find('.result-keeper').html(resultSet).show(500);
            // superContainer.find(".personality").html(personalityResult).show(500);
            // superContainer.find('.resultsview-qhover').hide();
            // superContainer.find('.result-row').hover(function () {
            //         $(this).find('.resultsview-qhover').show();
            //     },
            //     function () {
            //         $(this).find('.resultsview-qhover').hide();
            //     });
            // $(this).parents('.slide-container').fadeOut(500,
            //     function () {
            //         $(this).next().fadeIn(500);
            //     });
            // return false;
        });
    };
})(jQuery);