new Vue({
    el: '#app',
    data() {
        return {
            currentPage: 1,
            pageSize: 5,
            totalCount: 0,
            pagination: {},
            // 对应的数据
            dataList: [{
                house_id: "张三",
                season: 1,
                water_fee: 51,
                electricity_fee: 51111,
                rent_fee: 51.5,
                service_fee: 1,
                zt: "已提交",
                all_fee: 0,
                address:""
            }],//当前页要展示的列表数据
            formData: {},//表单数据
            season: "",
            dialogFormVisible: false,//控制表单是否可见
            id: 0,

        }
    },
    //钩子函数，VUE对象初始化完成后自动执行
    created() {
        this.getUser();
    },mounted(){

    },
    methods: {
        see(){
            this.dialogFormVisible=true;
            //数据请求
            axios({
                method: "post",
                url: "/cost/gettj",
                data: {
                    id: this.id,
                }
            }).then((res) => {
                console.log(res)
                if(res.data.code == 1){
                    const datas = res.data.data;
                    const month = datas.map(item => item.month);
                    const total = datas.map(item => item.total);
                    // 获取div元素
                    var chartContainer = document.getElementById('chart-container');

                    // 初始化echarts实例
                    var myChart = echarts.init(chartContainer);

                    // 配置图表
                    var options = {
                        title: {
                            text: '折线统计图'
                        },
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data: ['系列一']
                        },
                        xAxis: {
                            name: "时间",
                            type: 'category',
                            data: month
                        },
                        yAxis: {
                            name: "元",
                            type: 'value'
                        },
                        series: [{
                            name: '收入',
                            type: 'line',
                            data: total
                        }]
                    };
                    // 渲染图表
                    myChart.setOption(options);

                    // 配置图表选项和数据
                    // 获取div元素
                }else{
                    this.$message.error(res.data.msg);
                }
            })

        },

        getUser() {
            axios({
                url: "/user/getUser",
                method: "get",
            }).then(resp => {
                if (resp.data.code == 1) {
                    this.id = resp.data.data.id;
                    console.log(this.id)
                    this.getAll();
                } else {
                    this.$message.error(resp.data.msg);
                }
            })
        },
        //列表
        getAll() {
            axios({
                //作为房东的分页查询
                method: "post",
                url: "/cost/fdpage",
                data: {
                    currentPage: this.currentPage,
                    pageSize: this.pageSize,
                    house_no: this.house_no,
                    season: this.season,
                    id: this.id
                }
            }).then((res) => {
                if (res.data.code == 1) {
                    this.dataList = res.data.data.records;
                    this.totalCount = res.data.data.total;
                    // this.totalCount.all_fee;
                    //计算总费用
                    console.log(this.totalCount)
                } else {
                    this.$message.error(res.data.msg);
                }
            })
        },

    }
})