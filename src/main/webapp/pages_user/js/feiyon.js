new Vue({
    el: '#app',
    data() {
        return {
            currentPage: 1,
            pageSize: 5,
            totalCount: 0,
            pagination: {},
            dataList: [
                {
                    water_fee: 100,
                    electricity_fee: "100",
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
            dialogFormVisible: false,//控制表单是否可见
            dialogFormVisible1: false,
            house_no: "",
            season: "",
            id: 0
        }
    },
    //钩子函数，VUE对象初始化完成后自动执行
    created() {
        this.getUser();
    },
    methods: {
        tongji(row) {
            this.dialogFormVisible1 = true;
            this.$nextTick(() => {
                var option = {
                    series: [
                        {
                            type: 'pie',
                            data: [
                                {
                                    value: row.water_fee,
                                    name: '水费'
                                },
                                {
                                    value: row.electricity_fee,
                                    name: '电费'
                                },
                                {
                                    value: row.rent_fee,
                                    name: '租金'
                                },
                                {
                                    value: row.service_fee,
                                    name: '维修费用'
                                }
                            ],
                            label: {
                                show: true,
                                formatter: '{b}: {c} ({d}%)'
                            }
                        }
                    ]
                };
                var chart = echarts.init(document.getElementById('chart'));
                chart.setOption(option);
            });
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
        //查询所有
        getAll() {
            axios({
                method: "post",
                url: "/cost/yhPage",
                data: {
                    currentPage: this.currentPage,
                    pageSize: this.pageSize,
                    house_no: this.house_no,
                    season: this.season,
                    id: this.id
                }
            }).then((res) => {
                if (res.data.code == 1) {
                    let r = res.data;
                    this.dataList = r.data.records;
                    this.totalCount = r.data.total;
                    console.log(this.totalCount)
                } else {
                    this.$message.error(res.data.msg);
                }
            })
        },
        handleCreate() {
            this.dialogFormVisible = true;
        }
    }
})