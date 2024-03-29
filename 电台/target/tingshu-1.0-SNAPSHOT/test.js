// 日志显示功能
let logElement = document.getElementById("log");

function log(message) {
    logElement.innerHTML += (message + "\n");
}

// 定义全局变量
let captureStream;//用来保存stream
let mediaRecorder;//用来保存录制器
let data = [];//用来保存录制好的数据
let audioBlob;//用来保存转成blog类型的数据

// 从 html 中获取标签
let startButton = document.getElementById("startButton");
let recordButton = document.getElementById("recordButton");
let stopButton = document.getElementById("stopButton");
let submitButton = document.getElementById("submitButton");
let preview = document.getElementById("preview");

// 为开始采集按钮绑定 点击（click）事件的动作
startButton.addEventListener("click", function () {
    log("点击了开始采集按钮 -> 会弹出授权请求");
    let promise = navigator.mediaDevices.getUserMedia({
        audio: true
    });

    promise.then(function (stream) {
        log("用户同意了授权 -> 记录了采集数据");
        captureStream = stream;
    });
});

// 为开始录制按钮绑定 点击（click）事件的动作
recordButton.addEventListener("click", function () {
    log("点击了开始录制按钮 -> 开始录制，每 3 秒收集一次数据");

    if (!captureStream) {
        log("错误：必须先点击开始采集按钮");
        return;
    }

    mediaRecorder = new MediaRecorder(captureStream);
    mediaRecorder.ondataavailable = function (event) {
        log("录制数据可用事件 -> 保存数据");
        data.push(event.data);
    };

    mediaRecorder.start(3000);
});

// 为停止录制按钮绑定 点击（click）事件的动作
stopButton.addEventListener("click", function () {
    log("点击了停止录制按钮 -> 停止录制");
    if (!mediaRecorder) {
        log("错误：必须先点击开始录制按钮");
        return;
    }

    mediaRecorder.onstop = function () {
        log("录制停止事件 -> 准备预览功能的数据流");
        audioBlob = new Blob(data, {
            type: "audio/webm"
        });
        preview.src = URL.createObjectURL(audioBlob);
    };

    mediaRecorder.stop();
});

// 为上传按钮绑定 点击（click）事件的动作
submitButton.addEventListener("click", function () {
    log("点击了上传按钮 -> 通过 form 表单，向服务器提交录制下来的数据");
    if (!audioBlob) {
        log("错误：必须先点击停止录制按钮");
        return;
    }

    let formData = new FormData();
    formData.set("audio", audioBlob);

    let xhr = new XMLHttpRequest();
    xhr.open("post", "/upload/audio");
    xhr.onload = function () {
        log("服务器应答事件 -> 打印应答信息");
        log(xhr.status);
        log(xhr.responseText);
    };

    xhr.send(formData);
});
