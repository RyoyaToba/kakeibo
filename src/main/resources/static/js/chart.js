`use strict`

// 画面初期表示では、支出は合計金額を表示する
window.addEventListener('load', function() {
    retrieveSumPrice();
    retrieveBankInfos(document.getElementById('bank-name').options[bankNameElement.selectedIndex].value);
})

/* ============= 支出に関わる関数はここから================ */
// selectElementを取得して変数に代入
const selectElement = document.getElementById('item-name');

selectElement.addEventListener('change', function() {

    // 選択された option の値とテキストを取得
    const selectedText = selectElement.options[selectElement.selectedIndex].text;

    console.log("選択されたItem:", selectedText);

    if (selectedText == "合計") {
        retrieveSumPrice();
    } else {
        retrieveItemPrice(selectedText);
    }
})

/* グラフの作成 */
let lineChart = null;
let createChart = (data) => {
    let lineCtx = document.getElementById("lineChart");

    // 既存のチャートインスタンスが存在する場合は破棄
    if (lineChart) {
        lineChart.destroy();
    }

    // 線グラフの設定
    let lineConfig = {
        type: 'line',
        data: {
            labels: Object.keys(data),
            datasets: [{
                label: selectElement.options[selectElement.selectedIndex].text,
                data: Object.values(data),
                borderColor: '#f88',
            }],
        },
        options: {
            scales: {
                y: {
                    suggestedMin: 5000,
                    suggestedMax: 30000,
                    ticks: {
                        stepSize: 5000
                    }
                }
            },
        },
        responsive: true,
        maintainAspectRatio: false,
    };

    // グローバル変数 lineChart に新しいチャートインスタンスを代入
    lineChart = new Chart(lineCtx, lineConfig);
};

// 支出の合計金額を取得する
let retrieveSumPrice = () => {
    $.ajax({
        url: "/sumPrice",
        type: "POST",
        contentType: "application/json",
        dataType: "json"
    }).done(function(data) {
        console.log(data);
        createChart(data);
    }).fail(function() {
        console.log('fail');
    })
}

// 選択されたアイテムの月別支出を取得する
let retrieveItemPrice = (selectedText) => {
    $.ajax({
        url: "/itemPrices",
        type: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify({ selectedText: selectedText }), // データをJSON形式で送信
    }).done(function(data) {
        console.log(data);
        createChart(data);
    }).fail(function() {
        console.log('fail');
    })
}
/* ============= 支出に関する関数はここまで =============*/


/* ============= 貯蓄に関する関数はここから =============*/
const bankNameElement = document.getElementById('bank-name');

bankNameElement.addEventListener('change', function() {

    // 選択された option の値とテキストを取得
    const selectedText = bankNameElement.options[bankNameElement.selectedIndex].value;

    console.log("選択された口座:", selectedText);

    retrieveBankInfos(selectedText);
})

/* グラフの作成 */
let lineChart2 = null;

let createChart2 = (data) => {
    let lineCtx = document.getElementById("lineChart2");

    // 既存のチャートインスタンスが存在する場合は破棄
    if (lineChart2) {
        lineChart2.destroy();
    }

    // 線グラフの設定
    let lineConfig = {
        type: 'line',
        data: {
            labels: Object.keys(data),
            datasets: [{
                label: bankNameElement.options[bankNameElement.selectedIndex].text,
                data: Object.values(data),
                borderColor: '#f88',
            }],
        },
        options: {
            scales: {
                y: {
                    suggestedMin: 5000,
                    suggestedMax: 30000,
                    ticks: {
                        stepSize: 5000
                    }
                }
            },
        },
        responsive: true,
        maintainAspectRatio: false,
    };

    // グローバル変数 lineChart に新しいチャートインスタンスを代入
    lineChart2 = new Chart(lineCtx, lineConfig);
};

// 選択されたアイテムの月別支出を取得する
let retrieveBankInfos = (selectedText) => {
    $.ajax({
        url: "/balance-monthly/bankInfos",
        type: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify({ selectedText: selectedText }), // データをJSON形式で送信
    }).done(function(data) {
        console.log(data);
        createChart2(data);
    }).fail(function() {
        console.log('fail');
    })
}
