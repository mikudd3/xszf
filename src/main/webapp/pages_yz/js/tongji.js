new Vue({
    el: '#app',
    data() {
        return {
            dataList: [
                {
                    electricity_fee: "100",
                    water_fee: 100,
                    rent_fee: 1000,
                    service_fee: "100",
                    season: "三",
                    house_id: 1001,
                    address: "",
                    all_fee: 1300,
                    zt: "未缴费",
                }
            ],//当前页要展示的列表数据
            formData: {},//表单数据
            addresslist: {},
            house_id: "",
            season: "",
            id: 0
        }
    },
    //钩子函数，VUE对象初始化完成后自动执行
    created() {
        this.getUser();
    },
    methods: {
        //获取名下租房的地址放入下拉列表
        getHouses() {
            axios({
                method: "post",
                url: "/service/gethousedata1",
                data: {
                    //取用户id确定其户下的租房
                    id: this.id,
                }
            }).then((res) => {
                if(res.data.code ==1){
                    this.addresslist = res.data.data.records;
                }else{
                    this.$message.error(res.data.msg);
                }
            })
        },
        tongji() {
            axios({
                method: "post",
                url: "/cost/tongji?house_id=" + this.formData.house_id,
            }).then((res) => {
                this.dataList = res.data.data;
                console.log(this.dataList)
                console.log(this.dataList.length)
                this.huitu();
            });
        },
        huitu() {
            console.log("1111111")
            // 获取div元素
            var chartContainer = document.getElementById('chart-container');
            // 初始化echarts实例
            const chart = echarts.init(chartContainer);
            //获取水电费数组
            const water_fees = this.dataList.map(item => item.water_fee);
            const electricity_fee = this.dataList.map(item => item.electricity_fee);
            console.log(water_fees);
            console.log(electricity_fee);
            xData=[];
            for (var i = 0; i < this.dataList.length; i++) {
                xData.push((i + 1).toString());
            }
            // 配置图表选项和数据
            var option = {
                title: {
                    text: '水电费折线图'
                },
                xAxis: {
                    type: 'category',
                    data: xData
                },
                yAxis: {
                    type: 'value'
                },
                tooltip: {
                    trigger: 'axis',
                    formatter: function (params) {
                        var tooltip = params[0].name + '<br/>';
                        for (var i = 0; i < params.length; i++) {
                            tooltip += params[i].marker + ' ' + params[i].seriesName + ': ' + params[i].value + '<br/>';
                        }
                        return tooltip;
                    },
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                series: [
                    {
                        name: '水费',
                        data: water_fees,
                        type: 'line'
                    },
                    {
                        name: '电费',
                        data: electricity_fee,
                        type: 'line'
                    }]
            };
            // 使用配置项显示图表
            chart.resize();
            chart.setOption(option);
        },
        getUser() {
            axios({
                url: "/user/getUser",
                method: "get",
            }).then(resp => {
                if (resp.data.code == 1) {
                    this.id = resp.data.data.id;
                    console.log(this.id)
                    this.getHouses();
                } else {
                    this.$message.error(resp.data.msg);
                }
            })
        },
    }
})