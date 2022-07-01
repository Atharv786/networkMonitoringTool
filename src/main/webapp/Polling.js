/**
 * Created by atharv on 30/6/22.
 */


var xValues = ["Available", "Not Available"];
var yValues = [80, 10];
var barColors = [
    "#00aba9",
    "#b91d47"
];

new Chart("myChart", {
    type: "doughnut",
    data: {
        labels: xValues,
        datasets: [{
            backgroundColor: barColors,
            data: yValues
        }]
    },
    options: {
        title: {
            display: true,
        }
    }
});

var xValues = ["1", "2", "3", "4", "5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"];
var yValues = [55,9,44,44,45,90,49,49,49,49,49,49,49,49,49,49,49,49,49,49,49,49,49,49,49];
var barColors = ["#00aba9", "#00aba9","#00aba9","#00aba9","#00aba9","#00aba9","#00aba9","#00aba9","#00aba9","#00aba9","#00aba9","#00aba9","#00aba9","#00aba9","#00aba9","#00aba9","#00aba9","#00aba9","#00aba9","#00aba9","#00aba9","#00aba9","#00aba9","#00aba9"];

new Chart("myChart1", {
    type: "bar",
    data: {
        labels: xValues,
        datasets: [{
            backgroundColor: barColors,
            data: yValues
        }]
    },
    options: {
        legend: {display: false},
        title: {
            display: true,
        }
    }
});



var FreeMemory=90;
var UsedMemory=50;
var Buff=40;
var xValues = ["Free Memory(GB)", "Used Memory(GB)", " Buff/Cache(GB)"];
var yValues = [FreeMemory, UsedMemory, Buff];
var barColors = [
    "#00aba9",
    "#b91d47",
    "yellow"
];

new Chart("memoryChart", {
    type: "doughnut",
    data: {
        labels: xValues,
        datasets: [{
            backgroundColor: barColors,
            data: yValues
        }]
    },
    options: {
        title: {
            display: true,        }
    }
});



var IdleCpu=45;
var UsedCpu=55;

var xValues = ["Idle CPU(%)", "Used CPU(%)"];
var yValues = [IdleCpu, UsedCpu];
var barColors = [
    "#00aba9",
    "yellow"
];

new Chart("cpuUsage", {
    type: "doughnut",
    data: {
        labels: xValues,
        datasets: [{
            backgroundColor: barColors,
            data: yValues
        }]
    },
    options: {
        title: {
            display: true,        }
    }
});



var UsedDisk=70;
var FreeDisk=30;

var xValues = ["Used Disk Usage(GB)", "Free Disk Usage(GB)"];
var yValues = [UsedDisk, FreeDisk];
var barColors = [
    "#b91d47",
    "#00aba9"
];

new Chart("diskUsage", {
    type: "doughnut",
    data: {
        labels: xValues,
        datasets: [{
            backgroundColor: barColors,
            data: yValues
        }]
    },
    options:
        {
        title:
            {
            display: true,
        }
    }
});
