new Vue({
    el: '#app',
    data() {
        return {
            currentPage: 1,
            pageSize: 5,
            totalCount: 0,
            pagination: {},
            // 对应的数据
            dataList: [{}],//当前页要展示的列表数据
            formData: {},//表单数据
            dialogFormVisible: false,//控制表单是否可见
            dialogFormVisible4Edit: false,//编辑表单是否可见
            id:'',
        }
    },
    //钩子函数，VUE对象初始化完成后自动执行
    created() {
        this.getUser();
    },
    methods: {
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
            //获取登录信息——用户id
            // var user = JSON.parse(sessionStorage.getItem("userJson"));
            axios({
                method: "post",
                url: "/hetong/page",
                data: {
                    currentPage: this.currentPage,
                    pageSize: this.pageSize,
                    //用户id
                    id: this.id,
                }
            }).then((res) => {
                if (res.data.code == 1) {
                    this.dataList = res.data.data.records;
                    this.totalCount = res.data.data.total;
                } else {
                    this.$message.error(res.data.msg);
                }
            })

        },
        //下载合同
        getHetong(row) {
            axios({
                method: "post",
                url: "/hetong/yzgethetong",
                //合同id
                data: {id: row.id}
            }).then((res) => {
                if (res.data.code == 1) {
                    //创建a标签并下载路径中的文件+res.data.data.records.text
                    const link = document.createElement('a');
                    link.href = 'http://localhost:8080/image/img/house1.1.jpg';
                    //value中设置文件名以及类型
                    link.setAttribute('download', 'hetong.jpg');
                    link.click();
                } else {
                    this.$message.error(res.data.msg);
                }
            })
        },
        handleCurrentChange(val) {
            this.currentPage = val;
            this.getAll();
        },
        handleSizeChange(val) {
            this.pageSize = val;
            this.getAll();
        }
    }
})